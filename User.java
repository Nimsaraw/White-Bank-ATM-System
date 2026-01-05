package lk.bluesky.atm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String pin;  // stored as encrypted value

    public User() {}

    public User(String fullName, String cardNumber, String pin) {
        this.fullName = fullName;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
}
