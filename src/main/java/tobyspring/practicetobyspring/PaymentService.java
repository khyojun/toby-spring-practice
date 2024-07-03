package tobyspring.practicetobyspring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public  class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new SimpleExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
        throws IOException {
        //환율 가져오기
        BigDecimal exRate = exRateProvider.getExRate(currency);

        //금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        //유효기간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);


        return new Payment(orderId,currency,foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }


}
