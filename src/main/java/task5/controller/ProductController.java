package task5.controller;

import task5.model.Product;
import task5.service.ProductService;
import task5.factory.ProductFactory;

import java.util.List;

public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void addProduct(String name, double price) {
        Product product = ProductFactory.createProduct(name, price);
        service.addProduct(product);
        System.out.println("Product is added: " + product);
    }

    public void listProducts() {
        List<Product> products = service.listProducts();
        if (products.isEmpty()) {
            System.out.println("List of products is empty.");
        } else {
            System.out.println("Products catalog:");
            products.forEach(System.out::println);
        }
    }

    public void removeProduct(int id) {
        service.removeProduct(id);
        System.out.println("Product with id " + id + " deleted.");
    }
}
