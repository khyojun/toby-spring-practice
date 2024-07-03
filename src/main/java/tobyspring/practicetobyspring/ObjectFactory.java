package tobyspring.practicetobyspring;

public class ObjectFactory {

    public PaymentService paymentService(){
        return new PaymentService(getExProvider());
    }

    public ExRateProvider getExProvider(){
        return new SimpleExRateProvider();
    }

}
