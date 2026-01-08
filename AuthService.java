package lk.bluesky.atm.service;

import lk.bluesky.atm.exception.AuthenticationException;
import lk.bluesky.atm.exception.InvalidPinException;
import lk.bluesky.atm.model.User;
import lk.bluesky.atm.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String cardNumber, String pin) {
        User user = userRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new AuthenticationException("Invalid card number"));

        if (!passwordEncoder.matches(pin, user.getPin())) {
            throw new InvalidPinException("Invalid PIN");
        }

        return user;
    }
}
