package lk.bluesky.atm.repository;

import lk.bluesky.atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by ATM card number
    Optional<User> findByCardNumber(String cardNumber);

    // Check if card number already exists
    boolean existsByCardNumber(String cardNumber);
}
