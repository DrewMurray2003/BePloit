package comp31.asst2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import comp31.asst2.models.entities.Account;
import comp31.asst2.models.entities.Order;
import comp31.asst2.models.entities.OrderItem;
import comp31.asst2.models.entities.Product;
import comp31.asst2.models.repositories.AccountRepo;
import comp31.asst2.models.repositories.OrderRepo;
import comp31.asst2.models.repositories.ProductRepo;

@Component
public class Initialize implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {

    }

    public Initialize(AccountRepo accounts, ProductRepo products, OrderRepo orders) {
        Product product1 = new Product("HDMI Cord", 5.99, "Cord", 50, "https://m.media-amazon.com/images/I/71zU+DNvNrL._AC_UF894,1000_QL80_.jpg");
        Product product2 = new Product("USB Cable", 3.49, "Cable", 30, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/HN862?wid=2000&hei=2000&fmt=jpeg&qlt=95&.v=1562973642996");
        Product product3 = new Product("Wireless Mouse", 19.99, "Mouse", 20, "https://i.dell.com/is/image/DellContent//content/dam/ss2/product-images/peripherals/input-devices/dell/mouse/ms3320w/global-spi/ng/dell-wireless-mouse-ms3320w-details-hero-500-ng.psd?fmt=jpg");
        Product product4 = new Product("External Hard Drive", 79.99, "Storage", 10, "https://i.ebayimg.com/images/g/Na0AAOSwK6VkX7QD/s-l1200.webp");
        Product product5 = new Product("Bluetooth Headphones", 39.99, "Headphones", 15, "https://i.ebayimg.com/images/g/FAwAAOSwB8dj5eQr/s-l1200.webp");
        Product product6 = new Product("Gaming Keyboard", 69.99, "Keyboard", 8, "https://i.dell.com/is/image/DellContent/content/dam/images/products/electronics-and-accessories/dell/keyboards/aw510k/aw510k-ckb-3060rf-gy.psd?fmt=pjpg&pscan=auto&scl=1&wid=3724&hei=2419&qlt=100,1&resMode=sharp2&size=3724,2419&chrss=full&imwidth=5000");
        Product product7 = new Product("Smartphone Stand", 9.99, "Accessory", 25, "https://m.media-amazon.com/images/I/61ODQF95u0L._AC_UF894,1000_QL80_.jpg");
        Product product8 = new Product("Coffee Maker", 49.99, "Appliance", 12, "https://m.media-amazon.com/images/I/71LB1AbsorL.jpg");
        Product product9 = new Product("Fitness Tracker", 29.99, "Wearable", 18, "https://m.media-amazon.com/images/I/71smqRr0pmL._AC_UF1000,1000_QL80_.jpg");
        Product product10 = new Product("Desk Lamp", 14.99, "Lighting", 22, "https://i.ebayimg.com/images/g/6O0AAOSw97df4lQk/s-l1200.webp");
        products.saveAll(List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10));
        Account account0 = new Account("Admin", "Admin", "Admin", "(111) 111-1111", "Admin", "admn");
        Account account1 = new Account("Drew", "Murray", "drew.murray2@example.com", "(123) 456-7890", "coolPassword52", "cstmr");
        Account account2 = new Account("Bob", "Smith", "bob.smith42@example.com", "(987) 654-3210", "securePwd123", "cstmr");
        Account account3 = new Account("Charlie", "Davis", "charlie.davis8@example.com", "(555) 123-4567", "mySecretPass", "emp");
        Account account4 = new Account("David", "Miller", "david.miller27@example.com", "(111) 222-3333", "strongP@ss", "cstmr");
        Account account5 = new Account("Eva", "Clark", "eva.clark90@example.com", "(999) 888-7777", "EvaPass123", "emp");
        Account account6 = new Account("Frank", "Anderson", "frank.anderson14@example.com", "(777) 555-1234", "AndersonPwd", "cstmr");
        Account account7 = new Account("Grace", "Wilson", "grace.wilson5@example.com", "(333) 444-5555", "secret123", "emp");
        Account account8 = new Account("Henry", "Moore", "henry.moore63@example.com", "(666) 777-8888", "HenryPwd456", "cstmr");
        Account account9 = new Account("Ivy", "Baker", "ivy.baker22@example.com", "(123) 987-6543", "IvyPass789", "emp");
        Account account10 = new Account("Jack", "Parker", "jack.parker18@example.com", "(555) 444-3333", "securePass456", "cstmr");
        Account account11 = new Account("Olivia", "Taylor", "olivia.taylor77@example.com", "(777) 888-9999", "p@ssOlivia", "emp");
        Account account12 = new Account("Liam", "Brown", "liam.brown33@example.com", "(222) 111-4444", "BrownPwd123", "cstmr");
        Account account13 = new Account("Sophia", "Anderson", "sophia.anderson89@example.com", "(888) 999-6666", "sophiaPass", "emp");
        Account account14 = new Account("Noah", "Harris", "noah.harris55@example.com", "(444) 555-2222", "Noah1234", "cstmr");
        Account account15 = new Account("Ava", "Roberts", "ava.roberts21@example.com", "(111) 222-3333", "AvaPwd567", "emp");
        Account account16 = new Account("Mia", "Johnson", "mia.johnson46@example.com", "(999) 888-7777", "johnsonPass", "cstmr");
        Account account17 = new Account("Lucas", "Baker", "lucas.baker14@example.com", "(333) 444-5555", "LucasPwd789", "emp");
        Account account18 = new Account("Isabella", "Clark", "isabella.clark98@example.com", "(666) 777-8888", "IsabellaPass", "cstmr");
        Account account19 = new Account("Ethan", "White", "ethan.white63@example.com", "(123) 987-6543", "WhitePwd123", "emp");
        Account account20 = new Account("Emma", "Turner", "emma.turner27@example.com", "(555) 444-3333", "TurnerPass456", "cstmr");
        OrderItem orderItem1 = new OrderItem(product1, 2);
        OrderItem orderItem2 = new OrderItem(product2, 3);
        OrderItem orderItem3 = new OrderItem(product5, 1);
        OrderItem orderItem4 = new OrderItem(product6, 4);
        Order order1 = new Order();
        Order order2 = new Order();
        order1.addItems(List.of(orderItem1, orderItem2));
        order2.addItems(List.of(orderItem3, orderItem4));
        account0.addOrder(order1);
        account0.addOrder(order2);
        accounts.saveAll(List.of(account0, account1, account2, account3, account4, account5, account6, account7, account8, account9, account10, account11, account12, account13, account14, account15, account16, account17, account18, account19, account20));    
    }
    
}
