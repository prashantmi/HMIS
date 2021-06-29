package new_investigation.transactions.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.invListingReportNewFB;
import new_investigation.transactions.controller.fb.jQueryDataTableParamModel;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.invListingReportNewUTIL;
import new_investigation.vo.template.invListingReportNewVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class invListingReportRdNewACT extends CSRFGardTokenAction{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		invListingReportNewFB fb=(invListingReportNewFB)form;
		jQueryDataTableParamModel dtparam = new jQueryDataTableParamModel(request); 
		HttpSession session=WebUTIL.getSession(request);
		
		fb.setIsRadioDignoProcess("1");
		fb.setStartIndex(dtparam.getiDisplayStart());
		fb.setRowLimit(dtparam.getiDisplayLength());
		//invListingReportNewUTIL.getInvestigaationListingNew(fb, request);
		return mapping.findForward("NEW");
	}
	
	/* Added By PashantMi Server Side Processing Ajax*/
	public ActionForward NEW_AJAX_SERVER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		invListingReportNewFB fb=(invListingReportNewFB)form;
		jQueryDataTableParamModel dtparam = new jQueryDataTableParamModel(request); 
		int sEcho= Integer.parseInt(dtparam.getsEcho());
		
		HttpSession session=WebUTIL.getSession(request);
		fb.setIsRadioDignoProcess("1");
		fb.setStartIndex(dtparam.getiDisplayStart());
		fb.setRowLimit(dtparam.getiDisplayLength());
		
		List<invListingReportNewVO> lst = invListingReportNewUTIL.getInvestigaationListingNew_Ajax_Server(fb, request, dtparam);
		
		int iTotalRecords=0;
		int iTotalDisplayRecords=0;
		
		if(lst.size()>0) {
		iTotalDisplayRecords=iTotalRecords=Integer.parseInt(lst.get(0).getTotalRow());
		}
		int ids=Integer.parseInt(dtparam.getiDisplayStart());
		int idl =Integer.parseInt(dtparam.getiDisplayLength());
		
		/*//Pagination
		 * if(lst.size()< ids + idl) {} lst = lst.subList(ids, lst.size()); else lst =
		 * lst.subList(ids,ids + idl);}
		 */
		int j=ids;
		
		
		 //Generate JSON response
		try {
		    JsonObject jsonResponse = new JsonObject();
		    
		    jsonResponse.addProperty("sEcho", sEcho);
		    jsonResponse.addProperty("iTotalRecords", iTotalRecords);
		    jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
		    
		    // Gson gson = new Gson();
		    //JsonElement data =   ;
		    JsonArray row=null;
		    JsonArray rowContainer= new JsonArray();
		    if(lst.size()>0) {
		    	
		    	
		    for(invListingReportNewVO c : lst){
		        j++;
		        row= new JsonArray();
		        row.add(new JsonPrimitive(j));
		        row.add(new JsonPrimitive(c.getPuk_fromreqno()==null?"NA":c.getPuk_fromreqno()));
		        row.add(new JsonPrimitive(c.getPatType()==null?"NA":c.getPatType()));
		        row.add(new JsonPrimitive(c.getPat_fromreqno()==null?"NA":c.getPat_fromreqno()));
		        row.add(new JsonPrimitive(c.getAge_gender()==null?"NA":c.getAge_gender()));
		        row.add(new JsonPrimitive(c.getGroupName()==null?"NA":c.getGroupName()));
		        row.add(new JsonPrimitive(c.getTestName()==null?"NA":c.getTestName()));
		        row.add(new JsonPrimitive(c.getSampleName()==null?"NA":c.getSampleName()));
		        row.add(new JsonPrimitive(c.getSample_num()==null?"NA":c.getSample_num()));
		        row.add(new JsonPrimitive(c.getLab_num()==null?"NA":c.getLab_num()));
		        row.add(new JsonPrimitive(c.getStatus()==null?"NA":c.getStatus()));
		        row.add(new JsonPrimitive(c.getLabName()==null?"NA":c.getLabName()));
		        row.add(new JsonPrimitive(c.getReq_date()==null?"NA":c.getReq_date()));
		        row.add(new JsonPrimitive(c.getDeptName()==null?"NA":c.getDeptName()));
		        row.add(new JsonPrimitive(c.getWardName()==null?"NA":c.getWardName()));
		        		        
		        rowContainer.add(row);
		    	}   
		    }
		   
		    jsonResponse.add("aaData", rowContainer);
		
		    response.setContentType("application/Json");
		    response.getWriter().print(jsonResponse.toString());
		} catch (JsonIOException e) {
		    e.printStackTrace();
		    response.setContentType("text/html");
		    try {
				response.getWriter().print(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Added By PashantMi Client Side Processing With Ajax*/
	public ActionForward NEW_AJAX_CLIENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		invListingReportNewFB fb=(invListingReportNewFB)form;
		HttpSession session=WebUTIL.getSession(request);
		fb.setIsRadioDignoProcess("1");

		List<invListingReportNewVO> lst = invListingReportNewUTIL.getInvestigaationListingNew_Ajax_Client(fb, request);
		
		int j=0;
		
		 //Generate JSON response
		try {
		    JsonObject jsonResponse = new JsonObject();
		    
		    JsonArray row=null;
		    JsonArray rowContainer= new JsonArray();
		    if(lst.size()>0) {
		    	
		    	
		    for(invListingReportNewVO c : lst){
		        j++;
		        row= new JsonArray();
		        row.add(new JsonPrimitive(j));
		        row.add(new JsonPrimitive(c.getPuk_fromreqno()==null?"NA":c.getPuk_fromreqno()));
		        row.add(new JsonPrimitive(c.getPatType()==null?"NA":c.getPatType()));
		        row.add(new JsonPrimitive(c.getPat_fromreqno()==null?"NA":c.getPat_fromreqno()));
		        row.add(new JsonPrimitive(c.getAge_gender()==null?"NA":c.getAge_gender()));
		        row.add(new JsonPrimitive(c.getGroupName()==null?"NA":c.getGroupName()));
		        row.add(new JsonPrimitive(c.getTestName()==null?"NA":c.getTestName()));
		        row.add(new JsonPrimitive(c.getSampleName()==null?"NA":c.getSampleName()));
		        row.add(new JsonPrimitive(c.getSample_num()==null?"NA":c.getSample_num()));
		        row.add(new JsonPrimitive(c.getLab_num()==null?"NA":c.getLab_num()));
		        row.add(new JsonPrimitive(c.getStatus()==null?"NA":c.getStatus()));
		        row.add(new JsonPrimitive(c.getLabName()==null?"NA":c.getLabName()));
		        row.add(new JsonPrimitive(c.getReq_date()==null?"NA":c.getReq_date()));
		        row.add(new JsonPrimitive(c.getDeptName()==null?"NA":c.getDeptName()));
		        row.add(new JsonPrimitive(c.getWardName()==null?"NA":c.getWardName()));
		        
		        rowContainer.add(row);
		    	}
		    
		    }
		   
		    jsonResponse.add("aaData", rowContainer);
		
		    response.setContentType("application/Json");
		    response.getWriter().print(jsonResponse.toString());
		} catch (JsonIOException e) {
		    e.printStackTrace();
		    response.setContentType("text/html");
		    try {
				response.getWriter().print(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public ActionForward GETDATEWISELISTINGRD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		invListingReportNewFB fb=(invListingReportNewFB)form;
		jQueryDataTableParamModel dtparam = new jQueryDataTableParamModel(request); 
		HttpSession session=WebUTIL.getSession(request);
		
		fb.setIsRadioDignoProcess("1");
		fb.setStartIndex(dtparam.getiDisplayStart());
		fb.setRowLimit(dtparam.getiDisplayLength());
		//invListingReportNewUTIL.getInvestigaationListingNew(fb, request);
		return mapping.findForward("NEW");
	}
}
