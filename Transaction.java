package lk.bluesky.atm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String type; // DEPOSIT, WITHDRAW, TRANSFER

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    public Transaction() {}

    // Getters & Setters
    public Long getId() { return id; }
    public Account getAccount() { return account; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }

    public void setAccount(Account account) { this.account = account; }
    public void setType(String type) { this.type = type; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}
