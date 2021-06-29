package bmed.transactions.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.transactions.controller.config.HemComplaintApprovalDeskCONFIG;
import bmed.transactions.controller.data.HemComplaintApprovalDeskDATA;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.transactions.controller.fb.HemComplaintApprovalDeskFB;
import bmed.transactions.controller.util.ComplaintMaintenanceStatusUTIL;

public class HemComplaintApprovalDeskACTION extends GenericController
{
	static HemComplaintApprovalDeskCONFIG masterObj = new HemComplaintApprovalDeskCONFIG();
	
    public HemComplaintApprovalDeskACTION() 
    {
    	super(masterObj,"/transactions/HemComplaintApprovalDeskACTION");
    }

    
    /**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward VERIFY(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	{	
		generateToken(request_p);
		HemComplaintApprovalDeskFB HemComplaintApprovalDeskFB = (HemComplaintApprovalDeskFB) form_p;
		HemComplaintApprovalDeskDATA.initializeItemComplaintApproval(HemComplaintApprovalDeskFB,request_p);
		
		return mapping_p.findForward("verify");

	}
	
	/**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward VIEW(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	{	
		generateToken(request_p);
		HemComplaintApprovalDeskFB HemComplaintApprovalDeskFB = (HemComplaintApprovalDeskFB) form_p;
		HemComplaintApprovalDeskDATA.initializeItemComplaintApproval(HemComplaintApprovalDeskFB,request_p);
		return mapping_p.findForward("view");

	}
	/**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward SAVE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p) throws Exception
	{	
		validateToken(request_p, response_p);
		HemComplaintApprovalDeskFB HemComplaintApprovalDeskFB = (HemComplaintApprovalDeskFB) form_p;
		
		boolean retValue = false;	
	   	retValue =  HemComplaintApprovalDeskDATA.saveData(HemComplaintApprovalDeskFB,request_p);
	      
	    if (retValue)
	    	return this.CANCEL(mapping_p, form_p, request_p, response_p);
		else
			return this.VERIFY(mapping_p, form_p, request_p, response_p);
       
	}
	
	
	/**
	 * 
	 */
	public ActionForward GETUPLOADEDFILE(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)
	throws HisException 
    {		
		HemComplaintApprovalDeskFB fb = (HemComplaintApprovalDeskFB) form_p;
		HemComplaintApprovalDeskDATA.getUploadedFile(fb, request_p, response_p);
		return null;
	}
		
	public ActionForward CANCELPAGE(ActionMapping mapping_p, ActionForm form_p,	HttpServletRequest request_p, HttpServletResponse response_p)
	{
		ActionForward acFwd = new ActionForward();
		String strPath = "";
		if(request_p.getParameter("strPath")!= null)
		{
			strPath = request_p.getParameter("strPath").concat("?hmode=CANCEL");
			acFwd.setPath(strPath);
	        acFwd.setContextRelative(true);
	        
		}
		return acFwd;
	}
}
