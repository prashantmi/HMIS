package HisWeb.webservice;

import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;

import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.Document;

import HisWeb.dao.EHRDetailsDAO;
import HisWeb.util.PDFCreator;
import hisglobal.utility.HisUtil;

@Path("/patdata")
public class EHRDetails {
	
	
	/*   @POST
	   @Path("/getPatEHR") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getEHRDetails(  
			   @FormParam("Modval") String Modval,
			   @FormParam("CrNo") String CrNo,
			   @FormParam("episodeCode") String episodeCode,
			   @FormParam("visitNo") String visitNo,
			   @FormParam("seatId") String seatId,
			   @FormParam("Entrydate") String Entrydate,
			   @FormParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   System.out.println("CrNo::::::::::: \n"+ CrNo);
		   response=EHRDetailsDAO.getPatinetEHRDtls(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} */
	   
	   @GET
	   @Path("/getPatData") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getEHRDetails1(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   //System.out.println("CrNo::::::::::: \n"+ CrNo);
		   response=EHRDetailsDAO.getPatinetEHRDtls(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} 
	   
	   @GET
	   @Path("/getVitalData") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getVitalDtlsDtls(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   //System.out.println("CrNo::::::::::: \n"+ CrNo);
		   response=EHRDetailsDAO.getPatinetEHRDtls(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} 
	   
	  /* @GET
	   @Path("/getPatDataForPastPrescription") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getEHRDetailsForPastPrescription(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   System.out.println("CrNo::::::::::: \n"+ CrNo);
		   response=EHRDetailsDAO.getPatientEHRDtlsForPastPrescription(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} */

	   
	   @GET
	   @Path("/getPatDataForPastPrescription") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getEHRDetailsForPastPrescription(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   //System.out.println("CrNo::::::::::: \n"+ CrNo);
		   response=EHRDetailsDAO.getPatientEHRDtlsForPastPrescription(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} 
	   
	   
	   @GET
	   @Path("/getPatientEHRDtlsForTemplatePrescription") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getPatientEHRDtlsForTemplatePrescription(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   //System.out.println("CrNo::::::::::: \n"+ CrNo);
		   EHRDetailsDAO.getPatientEHRDtlsForTemplatePrescription(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} 
	   // getPatientEHRDtlsForTemplatePrescription
	   @GET
	   @Path("/getPatVitalDataForDetailedPrescription") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getPatVitalDataForDetailedPrescription(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   //System.out.println("CrNo::::::::::: \n"+ CrNo);
		   response=EHRDetailsDAO.getPatVitalDataDtlsForDetailedPrescription(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		   return response;
		} 
	   
	   @GET
	   @Path("/digi") 
	   @Produces(MediaType.TEXT_PLAIN)
	   public Response getPdf(  
	
	  @QueryParam("Modval") String Modval,
	  
	  @QueryParam("CrNo") String CrNo,
	  
	  @QueryParam("episodeCode") String episodeCode,
	  
	  @QueryParam("visitNo") String visitNo,
	  
	  @QueryParam("seatId") String seatId,
	  
	  @QueryParam("Entrydate") String Entrydate,
	  
	  @QueryParam("hosp_code") String hosp_code
	 
			   ) throws Exception{ 
		  
		 //  System.out.println("CrNo::::::::::: \n"+ CrNo);
		  String data= EHRDetailsDAO.getpdfForDigiLocaker(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		 String Data1= EHRDetailsDAO.getHospitalHeaderName(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code);
		  org.json.JSONArray ja=new  org.json.JSONArray(data);
		  org.json.JSONArray ja1=new  org.json.JSONArray(Data1);
		  
		//System.out.println(" ja.length()"+ ja.length());
		  //System.out.println(data);
		   for(int i=0 ; i<ja.length() ;i++) {
			   org.json.JSONObject jsonObject=new  org.json.JSONObject(ja.get(i).toString());
			   //System.out.println(jsonObject);
			//   JSONObject obj=new JSONObject(Data1);
			   PDFCreator.createPdf( jsonObject.get("HRSTR_JSON").toString(),  jsonObject.get("VITAL_DATA").toString() , ja1.get(i).toString() ,jsonObject.get("USER_NAME").toString() , jsonObject.get("DATASAVE_TIME").toString()   );  
		   }
		
		   String filePath = HisUtil.getParameterFromHisPathXML("CREDIT_BILL_PATH")+"/print.pdf" ;
	        //System.out.println("Sending file: " + filePath);
	        byte[] fileBytes=null;
	        try {
	            File file = new File(filePath);
	            FileInputStream fis = new FileInputStream(file);
	            BufferedInputStream inputStream = new BufferedInputStream(fis);
	            fileBytes = new byte[(int) file.length()];
	            inputStream.read(fileBytes);
	            inputStream.close();
	             
	          //  return fileBytes;
	        } catch (IOException ex) {
	            System.err.println(ex);
	            throw new WebServiceException(ex);
	        }
	        String response="";
	        if(ja.length() > 0) {
	        	 response=Base64.getEncoder().encodeToString(fileBytes);
	        }else {
	        	response= "No Data Found";
	        }
	        
	        //System.out.println(response);
		  return Response.ok().entity(  response) .header("Access-Control-Allow-Origin","*") .build();
		 
		   
		  // return response;
		} 
	   
	   @GET
	   @Path("/getAdmissionAdvice") 
	   @Produces(MediaType.APPLICATION_JSON) 
	   public String getAdmissionAdvice(  
			   @QueryParam("Modval") String Modval,
			   @QueryParam("CrNo") String CrNo,
			   @QueryParam("episodeCode") String episodeCode,
			   @QueryParam("visitNo") String visitNo,
			   @QueryParam("seatId") String seatId,
			   @QueryParam("Entrydate") String Entrydate,
			   @QueryParam("hosp_code") String hosp_code
			   ) throws Exception{ 
		   String response=null;
		   //System.out.println("CrNo::::::::::: \n"+ CrNo);
		   return EHRDetailsDAO.getAdmissionAdvice(Modval, CrNo , episodeCode , visitNo , seatId , Entrydate , hosp_code).toString();
		   //return response;
		} 
}
