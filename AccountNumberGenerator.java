package lk.bluesky.atm.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random random = new Random();

    public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
