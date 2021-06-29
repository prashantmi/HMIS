package bmed.transactions.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.transactions.controller.config.HemDeskCONFIG;
import bmed.transactions.controller.data.HemDeskDATA;
import bmed.transactions.controller.fb.HemDeskFB;
import bmed.transactions.controller.util.HemDeskUTIL;

public class HemDeskACTION extends GenericController {
	static HemDeskCONFIG masterObj = new HemDeskCONFIG();

	public HemDeskACTION() {
		super(masterObj, "/transactions/HemDeskACTION");
	}

	/**
	 * Add method is used to forward control to add page Controller with mode
	 * "FIRSTDATA"
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward SCHEDULE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=initializeSchedule&strComplaintId="
				+ request.getParameter("chk").split("\\@")[0]
				+ "&strIsHemDesk=1");
		acFwd.setContextRelative(true);
		return acFwd;

	}

	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GRIEVANCES(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException {
		HemDeskFB formBean_p = (HemDeskFB) form_p;
		String strReqType = null;
		strReqType = HemDeskUTIL.initializeGrievances(formBean_p, request_p);
		// System.out.println("Req Type:"+strReqType);
		// String tmp = request_p.getParameter("chk");

		/*
		 * if((tmp.split("\\@")[3]).split("\\$")[0].equals("1")) { return
		 * mapping_p.findForward("grevancesInternal"); } else { return
		 * mapping_p.findForward("grevancesExternal"); }
		 */

		if (strReqType.equals("1")) {
			return mapping_p.findForward("grevancesInternal");
		} else {
			return mapping_p.findForward("grevancesExternal");
		}

	}

	/**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param mapping_p
	 *            the mapping
	 * @param form_p
	 *            the form
	 * @param request_p
	 *            the request
	 * @param response_p
	 *            the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 * 
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward SAVEINTERNAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		HemDeskFB hemDeskFB = (HemDeskFB) form;
		HemDeskUTIL.save(hemDeskFB, request);
		if (hemDeskFB.getStrHemDesk().equals("0")) {

			String strUrl = "/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=unspecified";

			ActionForward acFwd = new ActionForward();
			acFwd.setPath(strUrl);
			acFwd.setContextRelative(true);
			return acFwd;
		} else {
			return this.LIST(mapping, form, request, response);
		}

	}

	/**
	 * GETENGGITEMSUBTYPE method is used to get Engineering Item Sub Type by
	 * using Ajax
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETENGGITEMSUBTYPE(ActionMapping mapping_p,
			ActionForm form_p, HttpServletRequest request_p,
			HttpServletResponse response_p) throws HisException {
		HemDeskFB formBean_p = (HemDeskFB) form_p;
		HemDeskUTIL.getEnggItemSubType(formBean_p, request_p, response_p);
		return null;
	}

	/**
	 * GETSERVICEENGG method is used to get Engineering Item Sub Type by using
	 * Ajax
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETSERVICEENGG(ActionMapping mapping_p,
			ActionForm form_p, HttpServletRequest request_p,
			HttpServletResponse response_p) throws HisException {
		HemDeskFB formBean_p = (HemDeskFB) form_p;
		HemDeskUTIL.getServiceEnggName(formBean_p, request_p, response_p);
		return null;
	}

	/**
	 * GETSERVICEENGG method is used to get Engineering Item Sub Type by using
	 * Ajax
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETESCLEVEL(ActionMapping mapping_p,
			ActionForm form_p, HttpServletRequest request_p,
			HttpServletResponse response_p) throws HisException {
		HemDeskFB formBean_p = (HemDeskFB) form_p;
		HemDeskUTIL.getEscLevel(formBean_p, request_p, response_p);
		return null;
	}

	/**
	 * GETSERVICEENGG method is used to get Engineering Item Sub Type by using
	 * Ajax
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETDETAILS(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException {
		HemDeskFB formBean_p = (HemDeskFB) form_p;
		HemDeskUTIL.getDetails(formBean_p, request_p, response_p);
		return null;
	}

	/**
	 * 
	 */

	public ActionForward CANCELTODESK(ActionMapping mapping_p,
			ActionForm form_p, HttpServletRequest request_p,
			HttpServletResponse response_p) {
		ActionForward acFwd = new ActionForward();
		String strPath = "";
		if (request_p.getParameter("strPath") != null) {
			strPath = request_p.getParameter("strPath").concat("?hmode=CANCEL");
			acFwd.setPath(strPath);
			acFwd.setContextRelative(true);

		}
		return acFwd;
	}

	/**
	 * This method used to initialize reminder reply.
	 * 
	 * @param _mapping
	 * @param _form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward initializeReminderReply(ActionMapping _mapping,
			ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=initializeReminderReply&strHiddenComplaintId="
				+ request.getParameter("chk").split("\\@")[0]
				+ "&strIsHemDesk=1");
		acFwd.setContextRelative(true);
		return acFwd;

	}

	/**
	 * This method used to initialize close process.
	 * 
	 * @param _mapping
	 * @param _form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward initializeClose(ActionMapping _mapping,
			ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=initializeClose&strHiddenComplaintId="
				+ request.getParameter("chk").split("\\@")[0]
				+ "&strIsHemDesk=1");
		acFwd.setContextRelative(true);
		return acFwd;

	}

	public ActionForward initializeView(ActionMapping _mapping,
			ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=viewDetails&strComplaintId="
				+ request.getParameter("chk").split("\\@")[0]
				+ "&strIsHemDesk=1");
		acFwd.setContextRelative(true);
		return acFwd;

	}
	
	
	public ActionForward ATTEND(ActionMapping _mapping,
			ActionForm _form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=initializeAttended&strHiddenComplaintId="
				+ request.getParameter("chk").split("\\@")[0]
				+ "&strIsHemDesk=1");
		acFwd.setContextRelative(true);
		return acFwd;

	}
	
	public ActionForward GETUPLOADEDFILE(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)
	throws HisException 
    {		
		HemDeskFB fb = (HemDeskFB) form_p;
		HemDeskDATA.getUploadedFile(fb, request_p, response_p);
		return null;
	}
	
	public ActionForward CANCELPAGE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		HemDeskFB formBean_p = (HemDeskFB) _form;
		System.out.println("formBean_p.getStrHemDesk()"+formBean_p.getStrHemDesk());
		
		if ("0".equals(formBean_p.getStrHemDesk())) {

			ActionForward acFwd = new ActionForward();
			acFwd.setPath("/bmed/transactions/ComplaintMaintenanceStatusTransACTION.cnt?hmode=unspecified");
			acFwd.setContextRelative(true);
			return acFwd;
			
			

		} else {
			return this.LIST(_mapping, formBean_p, request, response);
		}

	}

}
