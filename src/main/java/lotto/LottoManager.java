package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void calculateStatistics(List<LottoNumber> lottoNumbers, LottoNumber winningNumbers, int bonusBall, int totalGames) {

        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int i = 3; i <= 6; i++) {
            resultMap.put(i, 0);
        }

        for (LottoNumber userLotto : lottoNumbers) {
            int matchCount = countMatches(userLotto, winningNumbers);
            if (matchCount >= 3) {
                resultMap.put(matchCount, resultMap.get(matchCount) + 1);
            }
        }
        displayStatistics(resultMap, totalGames);

        double buyAmount = totalGames * 1000;
        double totalWinningAmount = calculateTotalWinningAmount(resultMap);

        double profit = totalWinningAmount - buyAmount;

        if (buyAmount > 0) {
            double profitRate = (profit / buyAmount) * 100;
            System.out.printf("총 수익률은 %.2f%%입니다.\n", profitRate);

            System.out.printf("총 이익은 %.2f입니다.\n", profit);

            double profitPercentage = (profit / buyAmount) * 100;
            System.out.printf("이익률은 %.2f%%입니다.\n", profitPercentage);
        }
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

    private void displayStatistics(Map<Integer, Integer> resultMap, int totalGames) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.values()) {
            int count = resultMap.getOrDefault(rank.getCountOfMatch(), 0);
            System.out.printf("%d개 일치 (%d원)- %d개\n", rank.getCountOfMatch(), rank.getWinningMoney(), count);
        }
    }

    public double calculateProfitRate(Map<Integer, Integer> resultMap, int buyAmount) {
        int totalWinningAmount = calculateTotalWinningAmount(resultMap);

        if (buyAmount <= 0) {
            return 0;
        }

        double profit = totalWinningAmount - buyAmount;

        return (profit / buyAmount) * 100;
    }

    private int calculateTotalWinningAmount(Map<Integer, Integer> resultMap) {
        int totalWinningAmount = 0;

        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            int matchCount = entry.getKey();
            int ticketCount = entry.getValue();
            Rank rank = Rank.valueOf(matchCount, false);
            totalWinningAmount += rank.getWinningMoney() * ticketCount;
        }

        return totalWinningAmount;
    }
}
