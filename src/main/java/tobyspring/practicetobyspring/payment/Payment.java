package tobyspring.practicetobyspring.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long orderId;
    private String currency;
    private BigDecimal foreignCurrencyAmount;
    private BigDecimal exRate;
    private BigDecimal convertedAmount; // 절대 double float 금액에 사용하지 않기! BigDecimal 사용할 것 keyword: 부동소수점 관련 찾기
    private LocalDateTime validUntil;


    @Override
    public String toString() {
        return "Payment{" +
            "orderId=" + orderId +
            ", currency='" + currency + '\'' +
            ", foreignCurrencyAmount=" + foreignCurrencyAmount +
            ", exRate=" + exRate +
            ", convertedAmount=" + convertedAmount +
            ", validUntil=" + validUntil +
            '}';
    }

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount,
        BigDecimal exRate,
        BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exRate = exRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    } // 같은 종류의 타입이 나올 경우 실수를 할 수 있음. 생성자를 사용하였을 경우에

    public static Payment createPrepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount,
        BigDecimal exRate) {

        //금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        //유효기간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate,convertedAmount,validUntil);
    }


    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }
}
