package cdac.authentication;

import java.util.Hashtable;

import javax.naming.*;
import javax.naming.ldap.*;


import javax.naming.directory.*;

public class Ldap
{
    public static void main(String[]args)
    {
        Hashtable<String, String> environment = new Hashtable<String, String>();

        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //environment.put(Context.PROVIDER_URL, "ldap://uataiimsmanglagiri.dcservices.in"); //ldap://<hostname>:389
        environment.put(Context.PROVIDER_URL, "ldap://uataiimsmanglagiri.dcservices.in:389"); //<hostname>
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
       // environment.put(Context.SECURITY_PRINCIPAL, "uid=ldapuser2,ou=People,dc=aiims,dc=in"); //<Login DN>
       // environment.put(Context.SECURITY_CREDENTIALS, "test123"); //<password>
        //environment.put(Context.SECURITY_PRINCIPAL, "cn=manager,dc=aiims,dc=in"); //<Login DN>
        //environment.put(Context.SECURITY_CREDENTIALS, "dataaiims2019"); //<password>
         environment.put(Context.SECURITY_PRINCIPAL, "cn=Garima Gotra,ou=People,dc=aiims,dc=in"); //<Login DN>
         environment.put(Context.SECURITY_CREDENTIALS, "radha123"); //<password>
        NamingEnumeration results = null;
        DirContext context = null;

        try 
        {
            context = new InitialDirContext(environment);
            System.out.println("Connected..");
            System.out.println(context.getEnvironment());
            //context.close();
            
			
			/*
			 * SearchControls controls = new SearchControls();
			 * controls.setReturningAttributes(new String[] { "cn", "Email","Password" });
			 * controls.setSearchScope(SearchControls.SUBTREE_SCOPE); //results
			 * =context.search("", "(ou=person)", controls); results =
			 * context.search("ou=People,dc=aiims,dc=in", "(uid=ldapuser2)", controls);
			 * while (results.hasMore()) { SearchResult searchResult = (SearchResult)
			 * results.next(); Attributes attributes = searchResult.getAttributes();
			 * Attribute attr = attributes.get("uid"); String cn = (String) attr.get();
			 * Attribute attrmail = attributes.get("password"); String email = (String)
			 * attrmail.get(); System.out.println(" Person Common Name = " + cn);
			 * System.out.println(" Person Mail = " + email); }
			 */
			  
			  String userName="Garima Gotra";
			  SearchControls searchControls = new SearchControls();
			  //NamingEnumeration<SearchResult> answer = context.search("ou=People,dc=aiims,dc=in", "uid=" + userName, searchControls);
			  NamingEnumeration<SearchResult> answer = context.search("ou=People,dc=aiims,dc=in", "cn=" + userName, searchControls);
	            if (answer.hasMore()) {
	                Attributes attrs = answer.next().getAttributes();
	                System.out.println(attrs.get("cn"));
	                System.out.println(attrs.get("postalCode"));
	                System.out.println(attrs.get("mail"));
	                System.out.println(attrs.get("Title"));
	                System.out.println(attrs.get("mobile"));
	                System.out.println(attrs.get("userPassword"));
	               // Attribute userPassword = attrs.get("userPassword");
	               // String pwd = new String((byte[]) userPassword.get());
	               // System.out.println(pwd);
	          
	                
	            } else {
	                System.out.println("user not found.");
	}
			 
        } 
        catch (AuthenticationNotSupportedException exception) 
        {
        	exception.printStackTrace();
        	System.out.println("The authentication is not supported by the server");
        }

        catch (AuthenticationException exception)
        {
        	exception.printStackTrace();
            System.out.println("Incorrect password or username");
        }

        catch (NamingException exception)
        {
        	exception.printStackTrace();
        	System.out.println("Error when trying to create the context");
        }
        finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                }
            }
            if (context != null) {
                try {
                	context.close();
                } catch (Exception e) {
                }
            }
    }
    }
}