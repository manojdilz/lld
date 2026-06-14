package cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getTotalValue).sum();
    }

    public void viewCart() {
        if (isEmpty()) {
            System.out.println("Your cart is empty.\n");
            return;
        }

        System.out.println("--- Your Cart ---");
        for (CartItem item : items) {
            System.out.println(item.getProduct().getName() + " x" + item.getQuantity());
        }
    }
}
