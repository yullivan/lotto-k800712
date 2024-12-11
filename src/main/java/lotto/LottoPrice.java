package lotto;

import java.util.Objects;

public class LottoPrice {

    private int price;

    public LottoPrice(int price) throws IllegalAccessException {

        if (this.price % 1000 != 0) {
            throw new IllegalAccessException("구매단위는 1,000원 입니다.");
        }
        if (this.price > 100000) {
            throw new IllegalAccessException("최대 구매 금액은 100,000원 입니다.");
        }
        this.price = this.price;
    }

    public int getPrice() {
        return price;
    }
}