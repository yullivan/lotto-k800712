package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoNumber {

    private final List<Integer> numbers;

    public LottoNumber(int number1, int number2, int number3, int number4, int number5, int number6) {
        this.numbers = new ArrayList<>();
        Collections.addAll(this.numbers, number1, number2, number3, number4, number5, number6);
    }

    public LottoNumber() {
        List<Integer> allNumbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            allNumbers.add(i);
        }

        Collections.shuffle(allNumbers);

        this.numbers = allNumbers.subList(0, 6);
        Collections.sort(this.numbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int getLotttoNumber1() { return numbers.get(0); }
    public int getLotttoNumber2() { return numbers.get(1); }
    public int getLotttoNumber3() { return numbers.get(2); }
    public int getLotttoNumber4() { return numbers.get(3); }
    public int getLotttoNumber5() { return numbers.get(4); }
    public int getLotttoNumber6() { return numbers.get(5); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return "로또 번호: " + numbers.toString();
    }
}
