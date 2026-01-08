package lk.bluesky.atm.service;

import lk.bluesky.atm.exception.AccountNotFoundException;
import lk.bluesky.atm.model.Account;
import lk.bluesky.atm.model.Transaction;
import lk.bluesky.atm.repository.AccountRepository;
import lk.bluesky.atm.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // VIEW TRANSACTION HISTORY
    public List<Transaction> getTransactionHistory(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));

        return transactionRepository.findByAccount(account);
    }
}
