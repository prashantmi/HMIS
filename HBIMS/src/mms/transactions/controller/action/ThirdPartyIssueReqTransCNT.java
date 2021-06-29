package mms.transactions.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.presentation.CSRFGardTokenAction;
import mms.transactions.controller.data.ThirdPartyIssueReqTransDATA;
import mms.transactions.controller.fb.ThirdPartyIssueReqTransFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
/**
 * Developer : Kapil
 * Version : 1.0
 * Date : 23/Jan/2009
 *  
*/
public class ThirdPartyIssueReqTransCNT extends CSRFGardTokenAction{
	
	/**
	 * This method is used to reset all the values 
	 * on clicking of clear button.
	 * @param _mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward CLEAR(ActionMapping _mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException, SQLException 
	{
		ThirdPartyIssueReqTransFB fb = (ThirdPartyIssueReqTransFB) form;
		
		//fb.setStrMsgString("");
		fb.setStrNormalMsg("");
		String strTarget=request.getParameter("mode");
		if(strTarget.equals("requestPage"))
		return this.REQUEST(_mapping, form, request, response);
		return null;
	}
	
	/**
	 * This method is used to open the page in which 
	 * request is issue to the store name.
	 * @param _mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	
	public ActionForward REQUEST(ActionMapping _mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		ThirdPartyIssueReqTransFB fb = (ThirdPartyIssueReqTransFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		ThirdPartyIssueReqTransDATA.getThirdPartyCmb(fb, request);
		ThirdPartyIssueReqTransDATA.getGroupCmb(fb, request);
		
		return _mapping.findForward("requestPage");
	}
	
	public ActionForward CANCEL_REQUEST(ActionMapping _mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		ThirdPartyIssueReqTransFB fb = (ThirdPartyIssueReqTransFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		ThirdPartyIssueReqTransDATA.CANCEL_REQUEST(fb, request , response);
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/ThirdPartyIssueDeskCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
	    return acFwd;
		//return LISTPAGE(_mapping, form, request, response);
	}
	
	/**
	 * This method is used to populate the group details
	 * on request page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	
/*	public ActionForward GROUPCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ThirdPartyIssueReqTransFB fb = (ThirdPartyIssueReqTransFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		ThirdPartyIssueReqTransDATA.getGroupCmb(fb, request, response);
		
		return null;
	}*/
	
	
	/**
	 * This method is used to insert the details.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	
	public ActionForward INSERTWITHNEWMODE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
         validateToken(request, response);
		ThirdPartyIssueReqTransFB fb = (ThirdPartyIssueReqTransFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		ThirdPartyIssueReqTransDATA.insertNew(fb, request, response);
		
		return this.REQUEST(mapping, form, request, response);
	}
	
	/**
	 * This method is used to insert the details.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        validateToken(request, response);
		ThirdPartyIssueReqTransFB fb = (ThirdPartyIssueReqTransFB) form;
		fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		ThirdPartyIssueReqTransDATA.insert(fb, request, response);
		
		return this.REQUEST(mapping, form, request, response);
	}
	
	public ActionForward CANCELPAGE(ActionMapping _mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/ThirdPartyIssueDeskCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
