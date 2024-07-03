package tobyspring.practicetobyspring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.practicetobyspring.payment.PaymentService;
import tobyspring.practicetobyspring.provider.CachedExRateProvider;
import tobyspring.practicetobyspring.provider.ExRateProvider;
import tobyspring.practicetobyspring.provider.WebApiExRateProvider;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService(){
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public ExRateProvider cachedExRateProvider(){
        return new CachedExRateProvider(getExProvider());
    }


    @Bean
    public ExRateProvider getExProvider(){
        return new WebApiExRateProvider();
    }

}
