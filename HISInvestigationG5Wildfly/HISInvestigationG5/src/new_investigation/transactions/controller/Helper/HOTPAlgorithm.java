package new_investigation.transactions.controller.Helper;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import new_investigation.InvestigationConfig;
import bbpubliclogin.expiringmap.ExpirationPolicy;
import bbpubliclogin.expiringmap.ExpiringMap;


/**
 * This class contains static methods that are used to calculate the
 * One-Time Password (OTP) using
 * JCE to provide the HMAC-SHA-1.
 *
 * @author Loren Hart
 * @version 1.0
 */
public class HOTPAlgorithm {

	
	private static Map<String, String> otpValidatorMap = ExpiringMap.builder()
			.expiration(InvestigationConfig.OTP_EXPIRY_TIME, TimeUnit.MINUTES)
			.build();
	
    private HOTPAlgorithm() {
    }

    // These are used to calculate the check-sum digits.
    //                                0  1  2  3  4  5  6  7  8  9
    private static final int[] doubleDigits = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};

    /**
     * Calculates the checksum using the credit card algorithm.
     * This algorithm has the advantage that it detects any single
     * mistyped digit and any single transposition of
     * adjacent digits.
     *
     * @param num the number to calculate the checksum for
     * @param digits number of significant places in the number
     *
     * @return the checksum of num
     */
    public static int calcChecksum(long num, int digits) {
        boolean doubleDigit = true;
        int total = 0;
        while (0 < digits--) {
            int digit = (int) (num % 10);
            num /= 10;
            if (doubleDigit) {
                digit = doubleDigits[digit];
            }
            total += digit;
            doubleDigit = !doubleDigit;
        }
        int result = total % 10;
        if (result > 0) {
            result = 10 - result;
        }
        return result;
    }

    /**
     * This method uses the JCE to provide the HMAC-SHA-1 algorithm.
     * HMAC computes a Hashed Message Authentication Code and
     * in this case SHA1 is the hash algorithm used.
     *
     * @param keyBytes   the bytes to use for the HMAC-SHA-1 key
     * @param text       the message or text to be authenticated.
     *
     * @throws NoSuchAlgorithmException if no provider makes
     *       either HmacSHA1 or HMAC-SHA-1
     *       digest algorithms available.
     * @throws InvalidKeyException
     *       The secret provided was not a valid HMAC-SHA-1 key.
     *
     */
    public static byte[] hmac_sha1(byte[] keyBytes, byte[] text)
            throws NoSuchAlgorithmException, InvalidKeyException {
        //        try {
        Mac hmacSha1;
        try {
            hmacSha1 = Mac.getInstance("HmacSHA1");
        } catch (NoSuchAlgorithmException nsae) {
            hmacSha1 = Mac.getInstance("HMAC-SHA-1");
        }
        SecretKeySpec macKey =
                new SecretKeySpec(keyBytes, "RAW");
        hmacSha1.init(macKey);
        return hmacSha1.doFinal(text);
    //        } catch (GeneralSecurityException gse) {
    //            throw new UndeclaredThrowableException(gse);
    //        }
    }
    private static final int[] DIGITS_POWER // 0 1  2   3    4     5      6       7        8
            = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    /**
     * This method generates an OTP value for the given
     * set of parameters.
     *
     * @param secret       the shared secret
     * @param movingFactor the counter, time, or other value that
     *                     changes on a per use basis.
     * @param codeDigits   the number of digits in the OTP, not
     *                     including the checksum, if any.
     * @param addChecksum  a flag that indicates if a checksum digit
     *                     should be appended to the OTP.
     * @param truncationOffset the offset into the MAC result to
     *                     begin truncation.  If this value is out of
     *                     the range of 0 ... 15, then dynamic
     *                     truncation  will be used.
     *                     Dynamic truncation is when the last 4
     *                     bits of the last byte of the MAC are
     *                     used to determine the start offset.
     * @throws NoSuchAlgorithmException if no provider makes
     *                     either HmacSHA1 or HMAC-SHA-1
     *                     digest algorithms available.
     * @throws InvalidKeyException
     *                     The secret provided was not
     *                     a valid HMAC-SHA-1 key.
     *
     * @return A numeric String in base 10 that includes
     */
//    static public String generateOTP(byte[] secret,
//            long movingFactor,
//            int codeDigits,
//            boolean addChecksum,
//            int truncationOffset)
//            throws NoSuchAlgorithmException, InvalidKeyException {
//        // put movingFactor value into text byte array
//        String result = null;
//        int digits = addChecksum ? (codeDigits + 1) : codeDigits;
//        byte[] text = new byte[8];
//        for (int i = text.length - 1; i >= 0; i--) {
//            text[i] = (byte) (movingFactor & 0xff);
//            movingFactor >>= 8;
//        }
//
//        // compute hmac hash
//        byte[] hash = hmac_sha1(secret, text);
//
//        // put selected bytes into result int
//        int offset = hash[hash.length - 1] & 0xf;
//        if ((0 <= truncationOffset) &&
//                (truncationOffset < (hash.length - 4))) {
//            offset = truncationOffset;
//        }
//        int binary =
//                ((hash[offset] & 0x7f) << 24) |
//                ((hash[offset + 1] & 0xff) << 16) |
//                ((hash[offset + 2] & 0xff) << 8) |
//                (hash[offset + 3] & 0xff);
//
//        int otp = binary % DIGITS_POWER[codeDigits];
//        if (addChecksum) {
//            otp = (otp * 10) + calcChecksum(otp, codeDigits);
//        }
//        result = Integer.toString(otp);
//        while (result.length() < digits) {
//            result = "0" + result;
//        }
//        return result;
//    }
    
    static public String generateOTP(String username) throws NoSuchAlgorithmException, InvalidKeyException {
        // put movingFactor value into text byte array
    	int codeDigits = 6;
    	boolean addChecksum = false;
    	String pass = "12345678901234567890";
        String result = null;
        int digits = addChecksum ? (codeDigits + 1) : codeDigits;
        
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        
        
        
      byte[] secret = pass.getBytes();
        
      int truncationOffset = sr.nextInt();  
      
      
        long movingFactor = 0; 
       
        movingFactor = sr.nextLong();
        
        byte[] text = new byte[8];
        for (int i = text.length - 1; i >= 0; i--) {
            text[i] = (byte) (movingFactor & 0xff);
            movingFactor >>= 8;
        }

        // compute hmac hash
        byte[] hash = hmac_sha1(secret, text);

        // put selected bytes into result int
        int offset = hash[hash.length - 1] & 0xf;
        if ((0 <= truncationOffset) &&
                (truncationOffset < (hash.length - 4))) {
            offset = truncationOffset;
        }
        int binary =
                ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);

        int otp = binary % DIGITS_POWER[codeDigits];
        if (addChecksum) {
            otp = (otp * 10) + calcChecksum(otp, codeDigits);
        }
        result = Integer.toString(otp);
        while (result.length() < digits) {
            result = "0" + result;
        }
        
       
        addOtpToValidationMap(username, result);
        
        return result;
    }
    
    /**
     * Add OTP to an expiry map
     * @param username
     * @param otp
     */
    
    public static void addOtpToValidationMap(String username, String otp){
    	otpValidatorMap.put(username,otp);
    }
    
    /**
     * Check if OTP is valid
     * @param crNo
     * @param otp
     * @return
     */
    
    public static int getOTPValidationStatus(String username, String otp){
    	int status=2;
    	String correctOTP= otpValidatorMap.get(username);
    	if(correctOTP==null)
    		status=3; // otp has expired
    	else if(correctOTP.equalsIgnoreCase(otp))
    		status=1; // otp is valid
    	else 
    		status=2; // otp is invalid
    	return status;
    }
    
    
    public static String getResendOTP(String username){
    	String otp = null;
    	String correctOTP= otpValidatorMap.get(username);
    	if(correctOTP==null)
    	{
    	//OTP has expired, generate new OTP
    	 try {
			otp = generateOTP(username);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}    		
    	else {
    		otp = correctOTP;
    		
    		((ExpiringMap) otpValidatorMap).setExpirationPolicy(username, ExpirationPolicy.CREATED);
    		((ExpiringMap) otpValidatorMap).setExpiration(username, InvestigationConfig.OTP_EXPIRY_TIME, TimeUnit.MINUTES);
    	//	map.setExpirationPolicy(username, ExpirationPolicy.CREATED);
    	}
    		
    	return otp;
    }	
    
//    public static void main(String[] args) {
//    String pass = "12345678901234567890";
//    byte[] code = pass.getBytes();
//    
//    try {
//    	for (int i = 0; i < 10; i++) {
//    System.out.println(HOTPAlgorithm.generateOTP("test"));
//    	}           
//        System.out.println(HOTPAlgorithm.getOTPValidationStatus("test", "129330"));
//    }
//        
//    catch (Exception e) {
//    System.out.println("Exception = " + e);
//    
//    
//    }
//    }

     
}