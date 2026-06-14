import java.util.Scanner;

import cart.Cart;
import cart.CartItem;
import notification.EmailNotification;
import notification.NotificationComposite;
import notification.SMSNotification;
import order.OrderService;
import payment.CardPayment;
import payment.PaymentProcessor;
import product.KeyBoard;
import product.Laptop;
import product.Mouse;
import product.Phone;
import product.Product;
import stock.Stock;

public class ECommerceApp {
    private Cart cart;
    private OrderService orderService;
    private Stock stock;

    public ECommerceApp() {
        cart = new Cart();

        NotificationComposite multiNotifier = new NotificationComposite();
        multiNotifier.addNotificationService(new EmailNotification());
        multiNotifier.addNotificationService(new SMSNotification());

        orderService = new OrderService(
                new PaymentProcessor(new CardPayment("12345")),
                multiNotifier);

        stock = new Stock();
        stock.addProduct(new Laptop(980), 10);
        stock.addProduct(new Phone(600), 15);
        stock.addProduct(new KeyBoard(69), 20);
        stock.addProduct(new Mouse(4969), 25);
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Please add items to your cart before checking out.");
        }

        orderService.placeOrder(cart);
    }

    public void addItemToCart(Scanner scanner) {
        stock.showCatalog();
        Product selectedProduct = selectProduct(scanner);
        int quantity = selectProductQuantity(scanner);

        if (stock.isInStock(selectedProduct, quantity)) {
            cart.addItem(new CartItem(selectedProduct, quantity));
            stock.reduceStock(selectedProduct, quantity);
            System.out.println("Added to cart: " + selectedProduct.getName() + " x" + quantity);
        } else {
            System.out.println("Sorry, not enough stock for " + selectedProduct.getName());
        }
    }

    public Product selectProduct(Scanner scanner) {
        System.out.println("What do you want: ");
        int productIndex = scanner.nextInt();
        return stock.indexToProduct(productIndex);

    }

    public int selectProductQuantity(Scanner scanner) {
        System.out.println("How many do you want: ");
        return scanner.nextInt();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Show Catalog");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    stock.showCatalog();
                    break;
                case 2:
                    addItemToCart(scanner);
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    System.out.println("Thank you for shopping with us!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ECommerceApp app = new ECommerceApp();
        app.menu();
    }
}