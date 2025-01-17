package tobyspring.practicetobyspring.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import tobyspring.practicetobyspring.payment.ExRateData;
import tobyspring.practicetobyspring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
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
}
