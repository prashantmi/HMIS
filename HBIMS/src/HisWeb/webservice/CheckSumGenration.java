package HisWeb.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import HisWeb.dao.walletWebserviceDao;
import HisWeb.util.CheckSumGenrationService;
@Path("/pay") 
public class CheckSumGenration {

	   @GET
	   @Path("/paytm") 
	   @Produces(value={"application/json", "application/json"})
	   public String getUsers( @QueryParam("amount") String amount) throws Exception{ 
		   String response=null;
		
			 response=CheckSumGenrationService.getcheckSum(amount);
		   
		   return response;
	   }  
}
