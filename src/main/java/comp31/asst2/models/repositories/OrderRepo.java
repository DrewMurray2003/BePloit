package comp31.asst2.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import comp31.asst2.models.entities.Account;
import comp31.asst2.models.entities.Order;

public interface OrderRepo extends CrudRepository<Order,Integer>{

    List<Order> findByAccount(Account account);
    
}
