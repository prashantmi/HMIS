package HisWeb.webservice;

import java.text.ParseException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import HisWeb.dao.SearchEMRUtilDao;
import HisWeb.util.opdDrDeskUtil;


@Path("/search")
public class EMRSearchService {
	
	@POST
	@Path("/emr")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistrationDepartments(@FormParam("JsonData") String JsonData ) throws JSONException, ParseException{
	//	opdDrDeskUtil.SaveDrDesk(JsonData);
		System.out.println("JsonData"+JsonData);
		
		String Data=SearchEMRUtilDao.getSearchEmrDtls(JsonData);
		//this.InsertFormattedData(FormattedJsonDataArray);
		 return Response.ok()
	               .entity(Data)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}	

}
