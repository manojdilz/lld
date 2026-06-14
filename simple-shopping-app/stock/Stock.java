package stock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import product.Product;

public class Stock {
    private final Map<Product, Integer> productStock = new HashMap<>();

    public boolean isInStock(Product product, int quantity) {
        return productStock.getOrDefault(product, 0) >= quantity;
    }

    public void addToStock(Product product, int quantity) {
        int currentStock = productStock.getOrDefault(product, 0);
        productStock.put(product, currentStock + quantity);
    }

    public void reduceStock(Product product, int quantity) {
        if (isInStock(product, quantity)) {
            int currentStock = productStock.get(product);
            productStock.put(product, currentStock - quantity);
        }
    }

    public void addProduct(Product product, int quantity) {
        if (!productStock.containsKey(product)) {
            productStock.put(product, quantity);
        }
    }

    public List<Product> getAvailableProducts() {
        return productStock.keySet().stream().toList();
    }

    public Product indexToProduct(int index) {
        return getAvailableProducts().get(index - 1);
    }

    public void showCatalog() {
        System.out.println("--- Available Products ---");
        int index = 1;
        for (Product product : getAvailableProducts()) {
            System.out.println(index + ". " + product.getName() + " - $" + product.getPrice());
            index++;
        }
        System.out.println("");
    }
}
