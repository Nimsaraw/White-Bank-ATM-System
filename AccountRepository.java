package lk.bluesky.atm.repository;

import lk.bluesky.atm.model.Account;
import lk.bluesky.atm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Find an account by its associated user.
     * @param user the User entity
     * @return Optional containing the Account if found
     */
    Optional<Account> findByUser(User user);

    /**
     * Find an account by account number.
     * @param accountNumber the account number string
     * @return Optional containing the Account if found
     */
    Optional<Account> findByAccountNumber(String accountNumber);
}
