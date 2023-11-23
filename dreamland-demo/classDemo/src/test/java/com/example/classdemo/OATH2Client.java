
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OATH2Client {
    private String clientID;
    private String clientSecret;

    public OATH2Client(String clientID, String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    public String generateClientSecret() {
        return base64Encode(random.generateRandomBytes());
    }

    public String generateOATH(String clientSecret) {
        String secret = this.clientSecret != null ? this.clientSecret : clientSecret;
        byte[] seed = random.generateRandomBytes();
        byte[] counter = random.generateRandomBytes();
        return sha512Hash((seed + counter + secret).getBytes());
    }

    private String base64Encode(byte[] bytes) {
        try {
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Base64EncoderException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String sha512Hash(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(data);
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private Random random = new Random();
}