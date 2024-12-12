package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoManager {

    public List<LottoNumber> generateLottoNumbers(int totalGames, int manualCount, Scanner scanner) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        // 수동 번호 수집
        for (int i = 0; i < manualCount; i++) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            String[] inputNumbers = scanner.next().split(",");
            LottoNumber manualLottoNumber = new LottoNumber(
                    Integer.parseInt(inputNumbers[0]),
                    Integer.parseInt(inputNumbers[1]),
                    Integer.parseInt(inputNumbers[2]),
                    Integer.parseInt(inputNumbers[3]),
                    Integer.parseInt(inputNumbers[4]),
                    Integer.parseInt(inputNumbers[5])
            );
            lottoNumbers.add(manualLottoNumber);
        }

        // 자동 번호 생성
        for (int i = 0; i < (totalGames - manualCount); i++) {
            lottoNumbers.add(new LottoNumber());
        }

        // 생성된 모든 번호 출력
        System.out.println(manualCount + "장 수동, " + (totalGames - manualCount) + "장 자동으로 구매했습니다.");
        for (LottoNumber lottoNumber : lottoNumbers) {
            System.out.println(lottoNumber);
        }

        return lottoNumbers;
    }

    public LottoNumber getWinningNumbers(Scanner scanner) {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] winningInput = scanner.next().split(",");

        return new LottoNumber(
                Integer.parseInt(winningInput[0]),
                Integer.parseInt(winningInput[1]),
                Integer.parseInt(winningInput[2]),
                Integer.parseInt(winningInput[3]),
                Integer.parseInt(winningInput[4]),
                Integer.parseInt(winningInput[5])
        );
    }

    public int getBonusBall(Scanner scanner) {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

    public void calculateStatistics(List<LottoNumber> lottoNumbers, LottoNumber winningNumbers, int bonusBall, int totalGames ) {
        int[] stats = new int[6]; // 일치 개수에 따른 통계 배열

        for (LottoNumber userLotto : lottoNumbers) {
            int matchCount = countMatches(userLotto, winningNumbers);
            boolean bonusMatch = userLotto.contains(bonusBall);

            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            stats[rank.ordinal()]++;
        }

        displayStatistics(stats,totalGames);
    }

    private int countMatches(LottoNumber userLotto, LottoNumber winningLotto) {
        int matchCount = 0;

        if (userLotto.getLotttoNumber1() == winningLotto.getLotttoNumber1()) matchCount++;
        if (userLotto.getLotttoNumber2() == winningLotto.getLotttoNumber2()) matchCount++;
        if (userLotto.getLotttoNumber3() == winningLotto.getLotttoNumber3()) matchCount++;
        if (userLotto.getLotttoNumber4() == winningLotto.getLotttoNumber4()) matchCount++;
        if (userLotto.getLotttoNumber5() == winningLotto.getLotttoNumber5()) matchCount++;
        if (userLotto.getLotttoNumber6() == winningLotto.getLotttoNumber6()) matchCount++;

        return matchCount;
    }

    private void displayStatistics(int[] stats, int totalGames) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.values()) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCountOfMatch(), rank.getWinningMoney(), stats[rank.ordinal()]);
        }

        double totalReturnRate = calculateReturnRate(stats,totalGames);
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", totalReturnRate);
    }

    private double calculateReturnRate(int[] stats, int totalGames) {
        int totalWinningAmount = 0;

        for (Rank rank : Rank.values()) {
            totalWinningAmount += stats[rank.ordinal()] * rank.getWinningMoney();
        }

        int totalInvestment = totalGames  * 1000;

        if (totalInvestment == 0) {
            return 1;
        }

        int profit = totalWinningAmount - totalInvestment;
        return (profit / totalInvestment) * 100;
    }
}


