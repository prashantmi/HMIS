
package HisWeb.webservice; 

import javax.ws.rs.FormParam;
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;

import org.json.JSONException;

import HisWeb.dao.InsertPaytmResponseDao;
import HisWeb.dao.PatientDtlsForPrescriptonDao;
import HisWeb.dao.ReceiptDtls;
import HisWeb.dao.billappDao;
import HisWeb.dao.walletWebserviceDao;


@Path("/UserService") 

public class UserService {   
   @GET 
   @Path("/users/{mode}/{crno}") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getUsers( @PathParam("mode") String mode,@PathParam("crno") String crno) throws JSONException{ 
	   String response=null;
	   if(mode.equalsIgnoreCase("4"))
	   {
	    response=walletWebserviceDao.getwalletDataSummary(mode, crno);
	   }else if(mode.equalsIgnoreCase("5")||mode.equalsIgnoreCase("6")||mode.equalsIgnoreCase("7"))
	   {
		   response=walletWebserviceDao.getPatValidation(mode, crno);
	   }else
	   {
		 response=walletWebserviceDao.getPatWalletData(mode, crno);
	   }
	   return response;
   }  
   
   
   
   @GET
   @Path("/investigation") 
   @Produces(MediaType.APPLICATION_JSON) 
   public Response getInvestigationDtls(  @QueryParam("hosp_code") String hosp_code ,@QueryParam("crno") String crno) throws JSONException{ 
	   String response=null;
	   response=walletWebserviceDao.getInvestigationTransSummary(hosp_code, crno);
	   return Response.status(200).entity(response).build();
	  }  
   
   @GET
   @Path("/insertBillService") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getInvestigationDtls1(  @QueryParam("jsonstring") String jsonstring) throws JSONException{ 
	   String response=null;
	   ////System.out.println("jsonstring"+jsonstring+" json");
	   response=billappDao.insertBillAppService(jsonstring);
	   return response;
	}  
   
   @GET
   @Path("/appReciept") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getDownloadReciept(  
		   @QueryParam("billNo") String billNo ,
		   @QueryParam("hospcode") String hospcode,
		   @QueryParam("crNo") String crNo,
		   @QueryParam("receiptNo") String receiptNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("billNo"+billNo+" hospcode"+hospcode+" crNo"+crNo+" receiptNo"+receiptNo );
	   response=ReceiptDtls.getReceiptDtls(billNo, hospcode, crNo, receiptNo);
	   return response;
	} 
   
   @GET
   @Path("/insertpaytmresponse") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String insertPaytmResponse(@QueryParam("jsonstring") String jsonstring ,@QueryParam("crno") String crno  ,@QueryParam("mobile_no") String mobile_no  ,@QueryParam("accno") String accno   ) throws JSONException{ 
	   String response=null;
	   //System.out.println("jsonstring"+jsonstring);
	   //System.out.println("cr no ::"+crno);
	   //System.out.println("Mobile No ::"+mobile_no);
	   //System.out.println("acc No ::"+accno);
	   response=InsertPaytmResponseDao.insertBillAppService(jsonstring ,crno , mobile_no,accno);
	   return response;
	}  
   
   
   //343058
   
   
   
   @POST
   @Path("/getImageData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getImageData(  
		   @FormParam("ImageData") String ImageData ,
		   @FormParam("DeptValue") String DeptValue
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("ImageData::::::::::: \n"+ ImageData);
	   //System.out.println("DeptValue::::::::::: \n"+ DeptValue);
	   response=billappDao.insertImageData(ImageData , DeptValue);
	   return "ImageData";
	} 
   
   
   @POST
   @Path("/getPatData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getEpisodeDtls(  
		   @FormParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatineDetails("1", CrNo);
	   return response;
	} 
   
   @GET
   @Path("/getPatData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getEpisodeDtls1(  
		   @QueryParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatineDetails("1", CrNo);
	   return response;
	} 
   
   
   @POST
   @Path("/getUserPatData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getUserEpisodeDtls(  
		   @FormParam("CrNo") String CrNo,@FormParam("seatid") String seatid
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   //System.out.println("seatid::::::::::: \n"+seatid);
	   response=PatientDtlsForPrescriptonDao.getUserPatineDetails("1", CrNo,seatid);
	   return response;
	} 
   
   @GET
   @Path("/getUserPatData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getUserEpisodeDtls1(  
		   @QueryParam("CrNo") String CrNo,@QueryParam("seatid") String seatid
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   //System.out.println("seatid::::::::::: \n"+seatid);
	   response=PatientDtlsForPrescriptonDao.getUserPatineDetails("1", CrNo,seatid);
	   return response;
	} 
   
   
   
   @POST
   @Path("/getUserPatDataViewPrescription") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getUserEpisodeDtlsViewPrescription(  
		   @FormParam("CrNo") String CrNo,@FormParam("seatid") String seatid
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   //System.out.println("seatid::::::::::: \n"+seatid);
	   response=PatientDtlsForPrescriptonDao.getUserPatineDetails("2", CrNo,seatid);
	   return response;
	} 
   
   @GET
   @Path("/getUserPatDataViewPrescription") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getUserEpisodeDtlsViewPrescription1(  
		   @QueryParam("CrNo") String CrNo,@QueryParam("seatid") String seatid
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   //System.out.println("seatid::::::::::: \n"+seatid);
	   response=PatientDtlsForPrescriptonDao.getUserPatineDetails("2", CrNo,seatid);
	   return response;
	} 
   
   
   
   //343058
   
   
   @POST
   @Path("/getPatDataViewPrescription") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getEpisodeDtlsViewPrescription(  
		   @FormParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatineDetailsViewPrescription("2", CrNo);
	   return response;
	} 
   
   @GET
   @Path("/getPatDataViewPrescription") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getEpisodegetEpisodeDtls1ViewPrescription(  
		   @QueryParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatineDetailsViewPrescription("2", CrNo);
	   return response;
	} 
   
   
   @POST
   @Path("/getPatDataCheckPrescription") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String checkPrescriptionStatus(  
		   @FormParam("CrNo") String CrNo,@FormParam("episode_code") String episode_code,@FormParam("visit_no") String visit_no
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.checkPatinePrescriptionDetails("1", CrNo,episode_code,visit_no);
	   //System.out.println(response);
	   return response;
	} 
   
   @GET
   @Path("/getPatDataCheckPrescription") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String checkPrescriptionStatus1(  
		   @QueryParam("CrNo") String CrNo,@QueryParam("episode_code") String episode_code,@FormParam("visit_no") String visit_no
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.checkPatinePrescriptionDetails("1", CrNo,episode_code,visit_no);
	   return response;
	} 
   
   
   
   
   @GET
   @Path("/retriveImageData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String retriveImageData(  
		   @QueryParam("DeptValue") String DeptValue
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ DeptValue);
	   response=PatientDtlsForPrescriptonDao.retriveImageData("2", DeptValue);
	   return response;
	} 
   
   @POST
   @Path("/retriveImageData") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String retriveImageData1(  
		   @FormParam("DeptValue") String DeptValue
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ DeptValue);
	   response=PatientDtlsForPrescriptonDao.retriveImageData("2", DeptValue);
	   return response;
	  
   }
	   
   
   @POST
   @Path("/getPatientDtls") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getPatientDtls(  
		   @FormParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatientDetails(CrNo);
	   return response;
	} 
   
   @GET
   @Path("/getPatientDtls") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getPatientDtls1(  
		   @QueryParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatientDetails(CrNo);
	   return response;
	} 
   
   

   @GET
   @Path("/getAdmission") 
   @Produces(MediaType.APPLICATION_JSON) 
   public String getAdmissionAdviceData(  
		   @QueryParam("CrNo") String CrNo
		   ) throws Exception{ 
	   String response=null;
	   //System.out.println("CrNo::::::::::: \n"+ CrNo);
	   response=PatientDtlsForPrescriptonDao.getPatientDetails(CrNo);
	   return response;
	} 
   
   
   
}