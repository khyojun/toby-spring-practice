package tobyspring.practicetobyspring.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tobyspring.practicetobyspring.provider.WebApiExRateProvider;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);


        // 환율정보 가져온다

        assertThat(payment.getExRate()).isNotNull();
        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).
            isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화환산금액의 유효시간 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore((LocalDateTime.now().plusMinutes(30)));
    }
}