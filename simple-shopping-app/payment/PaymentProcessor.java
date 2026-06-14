package payment;

public class PaymentProcessor {
    private PaymentService paymentService;

    public PaymentProcessor(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean processPayment(double amount) {
        return paymentService.processPayment(amount);
    }
}
