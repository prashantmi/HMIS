package startup;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public class SecurityUtil
{
	private static final String KEY_STRING =	"197.255.248.97.234.56.100.241";
	
	public static String encrypt( String source ) 
	{
		try
		{
			Key key = getKey();
			Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			desCipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cleartext = source.getBytes();
			byte[] ciphertext = desCipher.doFinal(cleartext);
			return getString( ciphertext );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateKey()
	{
		try
		{
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			SecretKey desKey = keygen.generateKey();
			byte[] bytes = desKey.getEncoded();
			return getString( bytes );
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decrypt( String source )
	{
		try
		{
			Key key = getKey();
			Cipher desCipher = 	Cipher.getInstance("DES/ECB/PKCS5Padding");
			byte[] ciphertext = getBytes( source );
			desCipher.init(Cipher.DECRYPT_MODE, key);
			byte[] cleartext = desCipher.doFinal(ciphertext);
			return new String( cleartext );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private static Key getKey()
	{
		try
		{
			byte[] bytes = getBytes( KEY_STRING );
			DESKeySpec pass = new DESKeySpec( bytes ); 
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES"); 
			SecretKey s = skf.generateSecret(pass); 
			return s;
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getString( byte[] bytes )
	{
		StringBuffer sb = new StringBuffer();
		for( int i=0; i<bytes.length; i++ )
		{
			byte b = bytes[ i ];
			sb.append( ( int )( 0x00FF & b ) );
			if( i+1 <bytes.length )
			{
				sb.append( "." );
			}
		}
		return sb.toString();
	}
	
	private static byte[] getBytes( String str )
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StringTokenizer st = new StringTokenizer( str, ".", false );
		while( st.hasMoreTokens() )
		{
			int i = Integer.parseInt( st.nextToken() );
			bos.write( ( byte )i );
		}
		return bos.toByteArray();
	}
	
	/*public static void main( String[] args )
	{
		
		String str= SecurityUtil.encrypt( "U.S." );
		String str2= SecurityUtil.decrypt( str);
		System.out.println("enc  "+str +"   length "+str.length());
		System.out.println("desc  "+str2);
	}*/
} 