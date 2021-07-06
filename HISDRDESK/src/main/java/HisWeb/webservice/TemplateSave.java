package HisWeb.webservice;

import java.text.ParseException;
import java.util.Base64;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import new_opd.transaction.controller.util.OPDTemplateMstUtil;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import HisWeb.dao.SearchEMRUtilDao;
import HisWeb.dao.TemplateDao;
import HisWeb.util.TemplateSaveUtil;
import HisWeb.util.TemplateUtil;

@Path("/Template")
public class TemplateSave {

	
	@POST
	@Path("/Save")   
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegistrationDepartments(@FormParam("JsonData") String JsonData ,@Context HttpServletRequest request , @Context HttpServletResponse response ) throws JSONException, ParseException{
	//	opdDrDeskUtil.SaveDrDesk(JsonData);
		System.out.println("JsonData"+JsonData);
		
		String Data="";   //SearchEMRUtilDao.getSearchEmrDtls(JsonData);
		
		//TemplateUtil.getParamsFromSimpleForm((HttpServletRequest) request);
		TemplateDao.SaveTemplateData(JsonData);
		String getData = TemplateSaveUtil.GetData(JsonData ,response);
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
	
   @POST
   @Path("/printTemplate") 
   @Produces(MediaType.APPLICATION_JSON) 
	   public String printData( @FormParam("printData") String printData,  @FormParam("myJSON") String myJSON , @FormParam("varid") String varid ,
			   @FormParam("imgs") String imgs ,
		    @Context HttpServletRequest request
		   ) throws Exception{ 
	  JSONObject resObj = new JSONObject();
	  resObj.put("base64",OPDTemplateMstUtil.getPrintBase64(printData.toString(),request , myJSON , varid,imgs));
	  return resObj.toString();
	} 
	
   	@POST
    @Path("/printpdf") 
    @Produces(MediaType.APPLICATION_JSON) 
 public String printData1( @FormParam("fileName") String fileName , @FormParam("hosp_code") String hosp_code ,
		    @Context HttpServletRequest request
		   ) throws Exception{
   	 JSONObject obj = new JSONObject();
  // 	 System.out.println("Test::::"+Base64.encodeBase64(OPDTemplateMstUtil.getPdfFileFromFTP(fileName, hosp_code).getBytes()).toString());
   		return  obj.put("base64",OPDTemplateMstUtil.getPdfFileFromFTP(fileName, hosp_code)).toString() ;
   	}
   	
   	
   	@POST
    @Path("/printpdf1") 
    @Produces(MediaType.APPLICATION_JSON) 
 public String printData2( @FormParam("fileName") String fileName , @FormParam("hosp_code") String hosp_code ,
		    @Context HttpServletRequest request
		   ) throws Exception{
   	 JSONObject obj = new JSONObject();
  // 	 System.out.println("Test::::"+Base64.encodeBase64(OPDTemplateMstUtil.getPdfFileFromFTP(fileName, hosp_code).getBytes()).toString());
    // String base64encodedString = OPDTemplateMstUtil.getPdfFileFromFTP1(fileName, hosp_code);
     //System.out.println("base64encodedString\n"+ Base64.encodeBase64String(base64encodedString.getBytes()));
   	String base64encodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(OPDTemplateMstUtil.getPdfFileFromFTP1(fileName, hosp_code));	
   	 
   	 return  obj.put("base64",base64encodedString).toString() ;
   	}
   	
}
