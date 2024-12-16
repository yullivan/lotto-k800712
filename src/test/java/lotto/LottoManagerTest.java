package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LottoManagerTest {

    private LottoManager lottoManager;
    private Scanner scanner;
    private int totalGames;

    @BeforeEach
    public void setUp() {
        lottoManager = new LottoManager();
        scanner = new Scanner(System.in);
    }

    @Test
    public void testGenerateLottoNumbers() {

        String input = "1,2,3,4,5,6";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        List<LottoNumber> lottoNumbers = lottoManager.generateLottoNumbers(5, 1, scanner);

        assertEquals(5, lottoNumbers.size(), "Total lotto numbers should be 5");
        assertEquals(1, lottoNumbers.get(0).getLotttoNumber1());
        assertEquals(2, lottoNumbers.get(0).getLotttoNumber2());
        assertEquals(3, lottoNumbers.get(0).getLotttoNumber3());
        assertEquals(4, lottoNumbers.get(0).getLotttoNumber4());
        assertEquals(5, lottoNumbers.get(0).getLotttoNumber5());
        assertEquals(6, lottoNumbers.get(0).getLotttoNumber6());
    }

    @Test
    public void testGetWinningNumbers() {

        String input = "1,2,3,4,5,6";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        LottoNumber winningNumbers = lottoManager.getWinningNumbers(scanner);

        assertEquals(1, winningNumbers.getLotttoNumber1());
        assertEquals(2, winningNumbers.getLotttoNumber2());
        assertEquals(3, winningNumbers.getLotttoNumber3());
        assertEquals(4, winningNumbers.getLotttoNumber4());
        assertEquals(5, winningNumbers.getLotttoNumber5());
        assertEquals(6, winningNumbers.getLotttoNumber6());
    }

    @Test
    public void testCalculateStatistics() {
        List<LottoNumber> userLottoNumbers = new ArrayList<>();
        userLottoNumbers.add(new LottoNumber(1, 2, 3, 4, 5, 6));
        userLottoNumbers.add(new LottoNumber(7, 8, 9, 10, 11, 12));

        LottoNumber winningNumbers = new LottoNumber(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outputStream));

        lottoManager.calculateStatistics(userLottoNumbers, winningNumbers, bonusBall, totalGames);
        
        String output = outputStream.toString();

        assertTrue(output.contains("당첨 통계"));
    }
    @Test
    public void testCalculateProfitRate() {
        // 주어진: 매치 수와 해당 매치 수에 맞는 티켓 수의 맵
        Map<Integer, Integer> resultMap = new HashMap<>();
        resultMap.put(3, 2); // 3개 번호 일치 -> 2장
        resultMap.put(4, 1); // 4개 번호 일치 -> 1장
        resultMap.put(5, 0); // 5개 번호 일치 -> 없음
        resultMap.put(6, 0); // 6개 번호 일치 -> 없음

        // 각 매치 수에 대한 상금 가정
        int buyAmount = 1000; // 총 구매 금액 (예: 1000원)

        // 수행: 수익률 계산
        double profitRate = calculateProfitRate(resultMap, buyAmount);

        // 검증: 수익률 계산이 올바른지 확인
        assertEquals(100.0, profitRate, "수익률이 올바르게 계산되어야 합니다.");
        
    }

    private double calculateProfitRate(Map<Integer, Integer> result, int buy) {
        int totalWinningAmount = 0;

        // 각 매치 수에 대한 상금 예시
        totalWinningAmount += result.getOrDefault(3, 0) * 5000;   // 3개 일치 -> 각각 5000원
        totalWinningAmount += result.getOrDefault(4, 0) * 50000;  // 4개 일치 -> 각각 50000원

        double profitRate = ((double) (totalWinningAmount - buy) / buy) * 100;

        // 손실이 발생할 경우 최소값을 0으로 설정
        return Math.max(profitRate, -100);
    }
}


