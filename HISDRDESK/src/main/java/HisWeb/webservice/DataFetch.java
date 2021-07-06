package HisWeb.webservice;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import HisWeb.dao.TemplateDao;
import new_opd.transaction.controller.util.OPDTemplateMstUtil;


@Path("/Templatefetch")
public class DataFetch {


	
	@POST
	@Path("/Save")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistrationDepartments(@FormParam("JsonData") String JsonData ,@Context HttpServletRequest request ) throws JSONException, ParseException{
	//	opdDrDeskUtil.SaveDrDesk(JsonData);
		System.out.println("JsonData"+JsonData);
		
		String Data="";   //SearchEMRUtilDao.getSearchEmrDtls(JsonData);
		
		//TemplateUtil.getParamsFromSimpleForm((HttpServletRequest) request);
		TemplateDao.SaveTemplateData(JsonData);
		//this.InsertFormattedData(FormattedJsonDataArray);
		 return Response.ok()
	               .entity(JsonData)
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}	

	@POST
	@Path("/getHtml")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHtml(@FormParam("JsonData") String JsonData ,@Context HttpServletRequest request ) throws JSONException, ParseException{
	//	opdDrDeskUtil.SaveDrDesk(JsonData);
		System.out.println("JsonData"+JsonData);
		JSONObject object = new JSONObject(JsonData);
		JSONObject js=new JSONObject();
		String Data=OPDTemplateMstUtil.getHtmlFileFromFTP(object.getString("strTemplateCode"), object.getString("strHospitaCode"));
		
		//TemplateUtil.getParamsFromSimpleForm((HttpServletRequest) request);
		//TemplateDao.SaveTemplateData(JsonData);
		//this.InsertFormattedData(FormattedJsonDataArray);
		js.put("strData", Data);
		System.out.println(Data);
		 return Response.ok()
	               .entity(js.toString())
	               .header("Access-Control-Allow-Origin", "*")
	               .build();
		//return JsonData;
	}	
	



}
