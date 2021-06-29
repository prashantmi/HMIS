package cdac.authentication;

import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to discover a server's supported SASL mechanisms.
 *
 * usage: java ServerSasl
 */
public class ServerSasl {
    public static void main(String[] args) {

	try {
	    // Create initial context
	    DirContext ctx = new InitialDirContext();

	    // Read supportedSASLMechanisms from root DSE
	    Attributes attrs = ctx.getAttributes(
	"ldap://uataiimsmanglagiri.dcservices.in:389", new String[]{"supportedSASLMechanisms"});

	    System.out.println(attrs);

	    // Close the context when we're done
	    ctx.close();
	} catch (NamingException e) {
	    e.printStackTrace();
	}
    }
}
