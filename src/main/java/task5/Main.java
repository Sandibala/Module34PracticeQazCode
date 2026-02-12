package task5;

import task5.controller.ProductController;
import task5.repo.ProductRepository;
import task5.service.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepository();
        ProductService service = new ProductService(repository);
        ProductController controller = new ProductController(service);
        controller.addProduct("Mac", 1500.0);
        controller.addProduct("Iphone", 800.0);
        controller.addProduct("AppleWatch", 300.0);
        controller.listProducts();
        controller.removeProduct(2);
        controller.listProducts();
    }
}
