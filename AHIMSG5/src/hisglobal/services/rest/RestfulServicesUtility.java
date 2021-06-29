/**********************************************************
 Project:	   AHIMS_G5	
 File:         RestfulServicesUtility.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hisglobal.services.rest;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.WebTarget;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

public class RestfulServicesUtility
{
	private static HostnameVerifier getHostnameVerifier()
	{
		return new HostnameVerifier() {

			@Override
			public boolean verify(String hostname,
					javax.net.ssl.SSLSession sslSession)
			{
				return true;
			}
		};
	}

	private static SSLContext getSSLContext()
	{
		javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {

			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
					throws java.security.cert.CertificateException
			{
				return;
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] arg0, String arg1)
					throws java.security.cert.CertificateException
			{
				return;
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers()
			{
				return null;
			}
		};
		SSLContext ctx = null;
		try
		{
			ctx = SSLContext.getInstance("SSL");
			ctx.init(null, new javax.net.ssl.TrustManager[] { x509 }, null);
		}
		catch (java.security.GeneralSecurityException ex)
		{
		}
		return ctx;
	}

	// RESTful Jersy
	/*public static WebResource getResource(String serviceServerURL, String serviceModuleContext)
	{
		ClientConfig config = new DefaultClientConfig(); // SSL	Configuration
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
		Client client = Client.create(config);
		WebResource resource = null;

		String serviceUrl = serviceServerURL + serviceModuleContext + "/services/rest";
		//e.g. serviceUrl = "http://localhost:8080"  +  "/"  +  "AHIMS"  +  "/services/rest";
		resource = client.resource(serviceUrl);

		return resource;
	}*/

	// Through JAX-RS RESEasy
	public static WebTarget getWebTarget(String serviceServerURL, String serviceModuleContext)
	{
		// 1. Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// 2. Generate BASIC scheme object and add it to the local auth cache
		AuthScheme basicAuth = new BasicScheme();
		authCache.put(new HttpHost("sippycups.bluemonkeydiamond.com"), basicAuth);
		// 3. Add AuthCache to the execution context
		BasicHttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		// 4. Create client executor and proxy
		DefaultHttpClient httpClient = new DefaultHttpClient();
		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient,
		localContext);
		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
		
		/*Client client = ClientFactory.newClient();
		WebTarget target = client.target("http://example.com/base/uri");
		ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
		SimpleClient simple = rtarget.proxy(SimpleClient.class);
		client.putBasic("hello world");*/
		
		String serviceUrl = serviceServerURL + serviceModuleContext + "/services/restful";
		ResteasyWebTarget target = client.target(serviceUrl);//"http://example.com/base/uri");

//		ResteasyClient client = new ResteasyClientBuilder().build();
//		SimpleClient simple = target.proxy(SimpleClient.class);
//		client.putBasic("hello world");

		return target;
	}
}
