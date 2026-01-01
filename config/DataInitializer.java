package lk.bluesky.atm.config;

import lk.bluesky.atm.model.Account;
import lk.bluesky.atm.model.User;
import lk.bluesky.atm.repository.AccountRepository;
import lk.bluesky.atm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      AccountRepository accountRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User user = new User();
                user.setFullName("Test ATM User");
                user.setCardNumber("4000000000000001");
                user.setPin(passwordEncoder.encode("1234"));
                userRepository.save(user);

                Account account = new Account();
                account.setAccountNumber("100000000001");
                account.setBalance(new BigDecimal("50000.00"));
                account.setUser(user);
                accountRepository.save(account);

                System.out.println("✅ ATM Test User Created");
                System.out.println("👉 Card Number: 4000000000000001");
                System.out.println("👉 PIN: 1234");
            }
        };
    }
}
