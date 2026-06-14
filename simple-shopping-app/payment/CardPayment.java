package payment;

import java.util.Scanner;


public class CardPayment implements PaymentService {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        if (requestCardNumber().equals(this.cardNumber)) {
            System.out.println("Payment of $" + amount + " successful.");
            return true;
        } else {
            System.out.println("Payment failed. Invalid card number.");
            return false;
        }
    }

    public String requestCardNumber() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your card number: ");
        String cardNumber = scanner.nextLine();

        return cardNumber;
    }
}
