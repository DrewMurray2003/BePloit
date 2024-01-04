package comp31.asst2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import comp31.asst2.models.entities.Product;
import comp31.asst2.models.repositories.ProductRepo;

@Service
public class ProductsService {
    
    ProductRepo products;

    public ProductsService(ProductRepo productRepo) {
        this.products = productRepo;
    }

    public List<Product> findAll() {
        return products.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> product = products.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }

}
