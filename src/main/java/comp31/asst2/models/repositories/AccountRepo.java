package comp31.asst2.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import comp31.asst2.models.entities.Account;

@Repository
public interface AccountRepo extends CrudRepository<Account,Integer> {
    
    List<Account> findAll();

    List<Account> findAllByOrderByLastNameAsc();

    Account findByEmailIgnoreCase(String email);

    Account findByPhoneNumber(String phoneNumber);

    List<Account> findByRole(String role);

    Account findByEmailIgnoreCaseAndPassword(String email, String password);
    
}
