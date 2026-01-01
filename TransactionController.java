package lk.bluesky.atm.controller;

import lk.bluesky.atm.model.Transaction;
import lk.bluesky.atm.model.User;
import lk.bluesky.atm.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("LOGGED_USER");
        if (user == null) {
            throw new RuntimeException("Unauthorized access. Please login.");
        }
        return user;
    }

    // VIEW TRANSACTION HISTORY
    @GetMapping("/history/{accountNumber}")
    public List<Transaction> viewHistory(
            @PathVariable String accountNumber,
            HttpSession session
    ) {
        getSessionUser(session);
        return transactionService.getTransactionHistory(accountNumber);
    }
}
