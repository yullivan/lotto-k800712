package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumber {

    private int lotttoNumber1;
    private int lotttoNumber2;
    private int lotttoNumber3;
    private int lotttoNumber4;
    private int lotttoNumber5;
    private int lotttoNumber6;

    public LottoNumber(int lotttoNumber1,
                       int lotttoNumber2,
                       int lotttoNumber3,
                       int lotttoNumber4,
                       int lotttoNumber5,
                       int lotttoNumber6) {
        this.lotttoNumber1 = lotttoNumber1;
        this.lotttoNumber2 = lotttoNumber2;
        this.lotttoNumber3 = lotttoNumber3;
        this.lotttoNumber4 = lotttoNumber4;
        this.lotttoNumber5 = lotttoNumber5;
        this.lotttoNumber6 = lotttoNumber6;
    }

    public LottoNumber(){
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 45 ; i++) {
            numbers.add(i);
            Collections.shuffle(numbers);

            List<Integer> LottoNumber = numbers.subList(0, 6);
            Collections.sort(LottoNumber);

            this.lotttoNumber1 = LottoNumber.get(0);
            this.lotttoNumber2 = LottoNumber.get(1);
            this.lotttoNumber3 = LottoNumber.get(2);
            this.lotttoNumber4 = LottoNumber.get(3);
            this.lotttoNumber5 = LottoNumber.get(4);
            this.lotttoNumber6 = LottoNumber.get(5);
        }
    }

    public int getLotttoNumber1() {
        return lotttoNumber1;
    }

    public int getLotttoNumber2() {
        return lotttoNumber2;
    }

    public int getLotttoNumber3() {
        return lotttoNumber3;
    }

    public int getLotttoNumber4() {
        return lotttoNumber4;
    }

    public int getLotttoNumber5() {
        return lotttoNumber5;
    }

    public int getLotttoNumber6() {
        return lotttoNumber6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lotttoNumber1 == that.lotttoNumber1 && lotttoNumber2 == that.lotttoNumber2 && lotttoNumber3 == that.lotttoNumber3 && lotttoNumber4 == that.lotttoNumber4 && lotttoNumber5 == that.lotttoNumber5 && lotttoNumber6 == that.lotttoNumber6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotttoNumber1, lotttoNumber2, lotttoNumber3, lotttoNumber4, lotttoNumber5, lotttoNumber6);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "lotttoNumber1=" + lotttoNumber1 +
                ", lotttoNumber2=" + lotttoNumber2 +
                ", lotttoNumber3=" + lotttoNumber3 +
                ", lotttoNumber4=" + lotttoNumber4 +
                ", lotttoNumber5=" + lotttoNumber5 +
                ", lotttoNumber6=" + lotttoNumber6 +
                '}';
    }
}
