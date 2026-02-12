package task5.factory;

import task5.model.Product;

public class ProductFactory {
    private static int counter = 1;

    public static Product createProduct(String name, double price) {
        return new Product(counter++, name, price);
    }
}
