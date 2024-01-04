package comp31.asst2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comp31.asst2.models.entities.Account;
import comp31.asst2.models.entities.Product;
import comp31.asst2.services.LoginService;
import comp31.asst2.services.ProductsService;
import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {

    LoginService loginService;
    ProductsService productsService;

    public MainController(LoginService loginService, ProductsService productsSerivce) {
        this.loginService = loginService;
        this.productsService = productsSerivce;
    }

    @GetMapping("/")
    String getHome(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/admin")
    String getAdmin(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if(user == null || user.getRole() != "admn") {
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("products", productsService.findAll());
            model.addAttribute("accounts", loginService.findAllAccounts());
            return "admin";
        }
    }

    @GetMapping("/login")
    String getLogin(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if(user == null) {
            model.addAttribute("accounts", new Account());
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/login") 
    String postLogin(Account account, HttpSession session) {
        Account user = loginService.findByEmailIgnoreCaseAndPassword(account.getEmail(), account.getPassword());

        if(user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logoff") 
    String getLogoff(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    String getRegister(Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user == null || user.getRole() == "admn") {
            model.addAttribute("account", new Account());
            model.addAttribute("user", user);
            return "register";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/register")
    String postRegister(Account account, HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if(loginService.findByEmail(account.getEmail()) != null) {
            model.addAttribute("emailError", "Account with this email already exists.");
            model.addAttribute("user", user);
            model.addAttribute("account", account);
            return "register";
        } else if(loginService.findByPhoneNumber(account.getPhoneNumber()) != null) {
            model.addAttribute("phoneError", "Account with this phone number already exists.");
            model.addAttribute("user", user);
            model.addAttribute("account", account);
            return "register";
        } 

        if(account.getRole() == null) {
            account.setRole("cstmr");
        }
        loginService.addAccount(account);
        if(user != null && user.getRole() == "admn") {
            return "redirect:/admin";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-profile")
    String getProfile(HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("edit", false);
        model.addAttribute("account", user);
        model.addAttribute("user", user);
        model.addAttribute("orders", user.getOrders());
        
        return "account";
    }

    @GetMapping("/edit-user-verify")
    String getEditAccountVerify(@RequestParam("id") Integer id, HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("user", user);
        
        if(user.getRole() == "admn") {
            model.addAttribute("edit", true);
            model.addAttribute("account", loginService.findByClientId(id));
            model.addAttribute("orders", user.getOrders());
            return "account";
        } else if(id == user.getAccountId()) {
                return "verifyPassword";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/edit-user-verify")
    String postEditAccountVerify(Account account, HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        Account verifyAccount = loginService.findByEmailIgnoreCaseAndPassword(user.getEmail(), account.getPassword());
        model.addAttribute("user", user);
        if (verifyAccount != null) {
            model.addAttribute("edit", true);
            model.addAttribute("account", user);
            model.addAttribute("orders", user.getOrders());
            return "account";
        } else {
            Account newAccount = new Account();
            model.addAttribute("verifyError", "Incorrect Password");
            model.addAttribute("account", newAccount);
            model.addAttribute("user", user);
            return "verifyPassword";        
        }
    }

    @GetMapping("/delete-user")
    String getDeleteVerify(@RequestParam("id") Integer id, HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if(user.getRole() == "admn" || id == user.getAccountId()) {
            model.addAttribute("user", user);
            model.addAttribute("account", loginService.findByClientId(id));
            return "deleteVerify";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/save-user")
    String postSaveUser(@RequestParam("id") Integer id, Account newAccount, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        Account oldAccount = loginService.findByClientId(id);
        loginService.updateAccount(oldAccount, newAccount);

        if(user.getAccountId() == id) {
            session.setAttribute("user", loginService.findByClientId(id));
            return "redirect:/user-profile";
        } else {
            String returnString = "redirect:/Account?id=" + id;
            return returnString;
        }
    }

    @PostMapping("/delete-verify")
    String postDeleteVerify(@RequestParam("id") Integer id, Account account, HttpSession session, Model model) {
        Account user = (Account) session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        Account verifyAccount1 = loginService.findByClientId(id);
        Account verifyAccount2 = loginService.findByEmailIgnoreCaseAndPassword(verifyAccount1.getEmail(), account.getPassword());
        if (verifyAccount2 != null) {
            loginService.deleteAccount(verifyAccount2);
            if(user.getAccountId() == verifyAccount2.getAccountId()) {
                return "redirect:/logoff";
            } else{
                return "redirect:/admin";
            }
        } else {
            model.addAttribute("verifyError", "Invalid Password");
            model.addAttribute("account", loginService.findByClientId(id));
            model.addAttribute("user", user);
            return "deleteVerify";        
        }
    }

    @GetMapping("/Account")
    String getAccount(@RequestParam("id") Integer id, Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user != null && user.getRole() == "admn") {
            Account account = loginService.findByClientId(id);
            model.addAttribute("edit", false);
            model.addAttribute("account", account);
            model.addAttribute("user", user);
            return "account";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/Product")
    String getProducts(@RequestParam("id") Integer id, Model model, HttpSession session) {
        Account user = (Account) session.getAttribute("user");
        if (user != null && user.getRole() == "admn") {
            Product product = productsService.findById(id);
            model.addAttribute("product", product);
            model.addAttribute("user", user);
            return "product";
        } else {
            return "redirect:/";
        }
    }
}
