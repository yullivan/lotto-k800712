package lotto;

import java.util.Objects;

public class Number {

    private int number;

    public Number(int number) throws IllegalAccessException {

        if (number < 1 || number > 45){

            throw new IllegalAccessException("NUMBER_ERROR");
        }
        this.number = number;

    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "number=" + number +
                '}';
    }
}


