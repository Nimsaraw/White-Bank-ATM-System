package lk.bluesky.atm.controller;

import lk.bluesky.atm.dto.LoginRequest;
import lk.bluesky.atm.model.User;
import lk.bluesky.atm.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * LOGIN endpoint using Card Number + PIN
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        try {
            User user = authService.login(request.getCardNumber(), request.getPin());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("❌ Invalid card number or PIN");
            }

            // Store minimal info in session
            session.setAttribute("LOGGED_USER_ID", user.getId());

            return ResponseEntity.ok("✅ Login successful. Welcome " + user.getFullName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Login failed: " + e.getMessage());
        }
    }

    /**
     * LOGOUT endpoint
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("✅ Logged out successfully");
    }
}
