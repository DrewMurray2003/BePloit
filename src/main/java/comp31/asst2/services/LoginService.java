package comp31.asst2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import comp31.asst2.models.entities.Account;
import comp31.asst2.models.repositories.AccountRepo;

@Service
public class LoginService {
    
    AccountRepo accounts;

    public LoginService(AccountRepo accounts) {
        this.accounts=accounts;
    }

    public void updateAccount(Account oldAccount, Account newAccount) {
        oldAccount.setEmail(newAccount.getEmail());
        oldAccount.setFirstName(newAccount.getFirstName());
        oldAccount.setLastName(newAccount.getLastName());
        oldAccount.setPhoneNumber(newAccount.getPhoneNumber());
        
        accounts.save(oldAccount);
    }

    public void deleteAccount(Account account) {
        accounts.delete(account);
    }

    public Account findByEmail(String email) {
        return accounts.findByEmailIgnoreCase(email);
    }

    public Account findByPhoneNumber(String phoneNumber) {
        return accounts.findByPhoneNumber(phoneNumber);
    }

    public List<Account> findAllAccounts() {
        return accounts.findAll();
    }

    public Account findByClientId(Integer id) {
        Optional<Account> account = accounts.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            return null;
        }
    }

    public List<Account> findAllOrderByLastNameAsc() {
        return accounts.findAllByOrderByLastNameAsc();
    }

    public Account findByEmailIgnoreCaseAndPassword(String email, String password) {
        return accounts.findByEmailIgnoreCaseAndPassword(email, password);
    }

    public void addAccount(Account newAccount) {
        accounts.save(newAccount);
    }

}
