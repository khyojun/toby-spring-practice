package tobyspring.practicetobyspring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {

    @Bean
    public PaymentService paymentService(){
        return new PaymentService(getExProvider());
    }

    @Bean
    public ExRateProvider getExProvider(){
        return new SimpleExRateProvider();
    }

}
