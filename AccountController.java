package lk.bluesky.atm.controller;

import lk.bluesky.atm.dto.AmountRequest;
import lk.bluesky.atm.dto.TransferRequest;
import lk.bluesky.atm.model.Account;
import lk.bluesky.atm.model.User;
import lk.bluesky.atm.service.AccountService;
import lk.bluesky.atm.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final AuthService authService;

    public AccountController(AccountService accountService, AuthService authService) {
        this.accountService = accountService;
        this.authService = authService;
    }

    // ✅ Get balance
    @GetMapping("/balance")
    public BigDecimal getBalance(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) throw new RuntimeException("User not logged in");

        Account account = accountService.getAccountByUser(user);
        return account.getBalance();
    }

    // ✅ Deposit money
    @PostMapping("/deposit")
    public String deposit(@RequestBody AmountRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) throw new RuntimeException("User not logged in");

        Account account = accountService.getAccountByUser(user);
        accountService.deposit(account, request.getAmount());

        return "Deposit successful. New balance: " + account.getBalance();
    }

    // ✅ Withdraw money
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody AmountRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) throw new RuntimeException("User not logged in");

        Account account = accountService.getAccountByUser(user);
        accountService.withdraw(account, request.getAmount());

        return "Withdrawal successful. New balance: " + account.getBalance();
    }

    // ✅ Transfer money to another account
    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request, HttpSession session) {
        User sender = (User) session.getAttribute("user");
        if (sender == null) throw new RuntimeException("User not logged in");

        Account senderAccount = accountService.getAccountByUser(sender);
        Account recipientAccount = accountService
                .getAccountByUser(senderAccount.getUser()); // Optionally fetch by account number if needed

        if (senderAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct from sender
        accountService.withdraw(senderAccount, request.getAmount());

        // Add to recipient
        accountService.deposit(recipientAccount, request.getAmount());

        return "Transfer successful. Your new balance: " + senderAccount.getBalance();
    }
}
