package tobyspring.practicetobyspring.provider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import tobyspring.practicetobyspring.payment.ExRateProvider;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private BigDecimal cachedExRate;
    private LocalDateTime cachedExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        if(cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())){
            cachedExRate = target.getExRate(currency);
            cachedExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("Cache updated: " + cachedExRate + " " + cachedExpiryTime);
        }
        return cachedExRate;
    }
}
