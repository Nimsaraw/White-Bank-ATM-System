package lk.bluesky.atm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Password encoder bean to encrypt/decrypt PINs
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security filter chain configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity in testing
                .csrf(csrf -> csrf.disable())

                // Allow all requests (no authentication required for now)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                )

                // Disable default Spring Security login form
                .formLogin(form -> form.disable());

        return http.build();
    }
}
