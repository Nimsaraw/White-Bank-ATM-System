package lk.bluesky.atm.repository;

import lk.bluesky.atm.model.Transaction;
import lk.bluesky.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Get all transactions for a specific account
    List<Transaction> findByAccount(Account account);
}
