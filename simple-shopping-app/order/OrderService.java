package order;

import cart.Cart;
import notification.NotificationService;
import payment.PaymentProcessor;

public class OrderService {
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;

    public OrderService(PaymentProcessor paymentProcessor, NotificationService notificationService) {
        this.paymentProcessor = paymentProcessor;
        this.notificationService = notificationService;
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public boolean placeOrder(Cart cart) {
        boolean paymentSuccessful = paymentProcessor.processPayment(cart.getTotalPrice());

        if (paymentSuccessful) {
            notificationService.sendNotification("Order placed successfully.");
            cart.clear();
            return true;
        }

        notificationService.sendNotification("Payment failed. Order could not be placed.");
        return false;
    }
}
