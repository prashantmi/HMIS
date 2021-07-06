import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class classmain {
	
	 public static void main(String ar[]) throws IOException {
		 getRAILWAYUMIDDATA1();
	}
	 
	 static public String getRAILWAYUMIDDATA1() throws IOException {

			String baseUrl = "https://umidapi.digitalir.in";
			String param=	"client_id=1fda9847696d717a13d3c4c326df65834658c02054e11229da84da68ef532c021&client_secret_token=9c4e6c6d60c7ab702f61c246b2eg6146646acebc978442875f8b26d03487e8e1&request_type=get_details_by_umid_no&umid_no=24187927191A" ;

			URL object = new URL(baseUrl);

			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept", "application/x-www-form-urlencoded");
			con.setRequestMethod("POST");
			
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(param.toString());
			wr.flush();

			// display what returns the POST request

			StringBuilder sb = new StringBuilder("");
			
			int HttpResult = con.getResponseCode();
			System.out.println(HttpResult);
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				//System.out.println(":::::::::::::::::" + sb.toString());
			} else {
				//System.out.println(con.toString());
				
				//System.out.println(con.getResponseMessage());
			}

			return sb.toString();
			
		}
	 public void test() {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource("https://umidapi.digitalir.in");
			String response = service.type(MediaType.APPLICATION_XML)
					.accept(MediaType.TEXT_PLAIN).entity(data).post(String.class);
	 }

}
