package comp31.asst2.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comp31.asst2.models.entities.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product,Integer> {
    
    List<Product> findAll();

    Optional<Product> findById(Integer id);
}
