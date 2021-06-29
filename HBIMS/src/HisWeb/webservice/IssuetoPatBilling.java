package HisWeb.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;

import HisWeb.dao.IssuetoPatBillingDAO;
@Path("/issuetoPatBill") 

public class IssuetoPatBilling {
	@GET
	//@Path("/insertissuetopatBill") 
	@Path("/issue/{mode}/{dept}/{chargetypeid}/{serviceid}/{reqno}/{cat}/{episode}/{crno}/{lfaccno}/{trf}/{trfbatch}/{trfrate}/{qty}/{name}/{add}/{contactno}/{age}/{ageunit}/{gender}/{refdoc}/{refdoccontactno}/{seatid}/{hospcode}/{wardcode}/{admno}/{visitno}") 
	@Produces(MediaType.APPLICATION_JSON) 
	
	   public String issuetopatBill( @PathParam("mode") String mode,@PathParam("dept") String dept,@PathParam("chargetypeid") String chargetypeid,@PathParam("serviceid") String serviceid,@PathParam("reqno") String reqno,@PathParam("cat") String cat,@PathParam("episode") String episode,@PathParam("crno") String crno,@PathParam("lfaccno") String lfaccno,@PathParam("trf") String trf,@PathParam("trfbatch") String trfbatch,@PathParam("trfrate") String trfrate,@PathParam("qty") String qty,@PathParam("name") String name,@PathParam("add") String add,@PathParam("contactno") String contactno,@PathParam("age") String age,@PathParam("ageunit") String ageunit,@PathParam("gender") String gender,@PathParam("refdoc") String refdoc,@PathParam("refdoccontactno") String refdoccontactno,@PathParam("seatid") String seatid,@PathParam("hospcode") String hospcode,@PathParam("wardcode") String wardcode,@PathParam("admno") String admno,@PathParam("visitno") String visitno)  throws JSONException{ 
		   String response=null;
		   
		   response=IssuetoPatBillingDAO.issuetopatbill(mode, dept,chargetypeid,serviceid,reqno,cat,episode,crno,lfaccno,trf,trfbatch,trfrate,qty,name,add,contactno,age,ageunit,gender,refdoc,refdoccontactno,seatid,hospcode,wardcode,admno,visitno);
		   return response;
	   } 
}
