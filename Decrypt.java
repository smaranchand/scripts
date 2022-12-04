import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Input Format: [Cipher_Text] [Secret_Key] [IV]");
            return;
        }

        String encrypted = args[0];
        String secretKey = args[1];
        String iv = args[2];

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
            byte[] decoded = Base64.getDecoder().decode(encrypted);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(cipher.doFinal(decoded));
            System.out.println("Decrypted: " + new String(outputStream.toByteArray()));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid base64 data");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

