package task5.service;

import task5.model.Product;
import task5.repo.ProductRepository;

import java.util.List;

public class ProductService {
        private final ProductRepository repository;

        public ProductService(ProductRepository repository) {
            this.repository = repository;
        }

        public void addProduct(Product product) {
            repository.addProduct(product);
        }

        public List<Product> listProducts() {
            return repository.getAllProducts();
        }

        public void removeProduct(int id) {
            repository.removeProduct(id);
        }
}
