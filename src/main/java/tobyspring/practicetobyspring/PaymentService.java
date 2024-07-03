package tobyspring.practicetobyspring;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
        throws IOException {
        //환율 가져오기
        BigDecimal exRate = getExRate(currency);

        //금액 계산
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        //유효기간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);


        return new Payment(orderId,currency,foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    private static BigDecimal getExRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/"+ currency);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();// 이거 거의 사용 안함.

        BufferedReader br = new BufferedReader(
            new InputStreamReader(urlConnection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);

        BigDecimal exRate = data.rates().get("KRW");
        return exRate;
    }


    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD",BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}