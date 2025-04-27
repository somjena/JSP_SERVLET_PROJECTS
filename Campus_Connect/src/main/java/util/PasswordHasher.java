package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    // In production, consider using BCrypt instead
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] salt = getSalt();
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    private static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static boolean verifyPassword(String inputPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedInputPassword = md.digest(inputPassword.getBytes());

            String hashedInputPasswordStr = Base64.getEncoder().encodeToString(hashedInputPassword);
            return hashedInputPasswordStr.equals(parts[1]);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to verify password", e);
        }
    }
}