package Investigation_webservice.bussiness;

import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.lowagie.tools.encrypt_pdf;

public class dummy1 {

    private static Cipher cipher;
    private static SecretKeySpec key;
    private static AlgorithmParameterSpec spec;
    public static final String SEED_16_CHARACTER = "U1MjU1M0FDOUZ.Qz";

    public void AESCrypt() throws Exception {
        // hash password with SHA-256 and crop the output to 128-bit for key
        
    }
    
    public static AlgorithmParameterSpec getIV() {
        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        IvParameterSpec ivParameterSpec;
        ivParameterSpec = new IvParameterSpec(iv);

        return ivParameterSpec;
    }

    public static String encrypt(String plainText) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedText = DatatypeConverter.printBase64Binary(encrypted);
           System.out.println("encryptedText "+encryptedText);
        return encryptedText;
    }

    public static String decrypt(String cryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] bytes = DatatypeConverter.parseBase64Binary(cryptedText);
        byte[] decrypted = cipher.doFinal(bytes);
        String decryptedText = new String(decrypted, "UTF-8");
        //String decryptedText = DatatypeConverter.printBase64Binary(decrypted);
        System.out.println(decryptedText);
        return decryptedText;
    }

    
    
    
    public static void main(String args[])
    {
    	
    	try {
    		MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(SEED_16_CHARACTER.getBytes("UTF-8"));
            byte[] keyBytes = new byte[16];
            System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);

            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            key = new SecretKeySpec(keyBytes, "AES");
            spec = getIV();
			String x = encrypt("chandan");
			decrypt(x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}