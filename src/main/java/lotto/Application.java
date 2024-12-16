package lotto;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int purchaseAmount = 0;

        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            purchaseAmount = scanner.nextInt();

            if (purchaseAmount % 1000 == 0 && purchaseAmount > 0) {
                break; // 조건을 만족하면 루프 종료
            } else {
                System.out.println("잘못된 금액입니다. 1,000원 단위로 다시 입력해 주세요.");
            }
        }

        int numberOfGames = purchaseAmount / 1000;

        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualCount = scanner.nextInt();

        LottoManager lottoManager = new LottoManager();
        List<LottoNumber> lottoNumbers = lottoManager.generateLottoNumbers(numberOfGames, manualCount, scanner);

        LottoNumber winningNumbers = lottoManager.getWinningNumbers(scanner);
        int bonusBall = lottoManager.getBonusBall(scanner);


        int totalgames = 0;
        lottoManager.calculateStatistics(lottoNumbers, winningNumbers, bonusBall, totalgames);
    }
}