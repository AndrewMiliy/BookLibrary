package repositories;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
public class HashedPassword {
    public static String hashIntellijId(String intellijId) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(intellijId.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception (e.g., print an error message or throw a custom exception)
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String intellijId = "your_intellij_id";
        String hashedIntellijId = hashIntellijId(intellijId);
        System.out.println("Hashed intellijid: " + hashedIntellijId);
    }
}
