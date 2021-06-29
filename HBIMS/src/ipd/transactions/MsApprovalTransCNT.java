package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionutil.GenericController;
import ipd.IpdTransConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MsApprovalTransCNT extends GenericController {


	public MsApprovalTransCNT() {
		super(new MsApprovalTransUTL(), "/transactions/MsApprovalTransCNT");
	}

	public ActionForward ADD(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		
				
		String strtarget = "add";
		return _mapping.findForward(strtarget);
	}
	
	public ActionForward verifyCrNo(ActionMapping _mapping,
			ActionForm _formBean, HttpServletRequest _request,
			HttpServletResponse _response) throws HisException {
		MsApprovalTransFB msApprovalTransFB = (MsApprovalTransFB) _formBean;
		MsApprovalTransDATA.verifyCrNo(msApprovalTransFB, _request);
		return null;
	}
	
	public ActionForward GO(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws Exception {
		generateToken(_request);
		//WebUTIL.refreshTransState(_request);
		//verifyCrNo(_mapping, _form, _request, _response);
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		if (!formBean.getStrMsgType().equals("2"))
		{
			MsApprovalTransDATA.setDeptComboValues(formBean,_request,_response);
			//MsApprovalTransDATA.setUnitComboValues(formBean,_request,_response);
			MsApprovalTransDATA.status(formBean, _request);
		}
			
		String strtarget = "add";
		return _mapping.findForward(strtarget);
	}

	public ActionForward APPROVE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.msapprovalList(formBean, _request);

		String strtarget = "approve";
		return _mapping.findForward(strtarget);
	}

	public ActionForward ALLOTEMENT(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.msallote(formBean, _request);
		String strtarget = "allotement";
		return _mapping.findForward(strtarget);
	}
	
	public ActionForward WARD(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response) {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.privateWardCombo(formBean, _request, _response);
		return null;
	}
	
	public ActionForward ROOM(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response) {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.ROOM(formBean, _request, _response);
		return null;
	}

	public ActionForward BED(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response) {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.BED(formBean, _request, _response);
		return null;
	}
	
	public ActionForward MODECNCEL(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());

		MsApprovalTransDATA.cancel(formBean, _request);
		String strtarget = "cancel";
		return _mapping.findForward(strtarget);
	}
	
	public ActionForward VIEW(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		String strtarget = "";
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.view(formBean, _request);
		strtarget = "view";
		return _mapping.findForward(strtarget);
	}

	public ActionForward INSERT(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws Exception, SQLException {
		validateToken(_request,_response);
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.insert(formBean, _request);
		if (!formBean.getStrMsgType().equals("1"))
			return LIST(_mapping, _form, _request, _response);
		return _mapping.findForward("add");
	}

	public ActionForward SAVEAPPROVE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws Exception, SQLException {
		
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.updatemsapproval(formBean, _request);
		if (!formBean.getStrMsgType().equals("1"))
			return LIST(_mapping, _form, _request, _response);
		return _mapping.findForward("approve");
	}

	public ActionForward SAVEALLOCATE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws Exception, SQLException {
	
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.savewardallotement(formBean, _request);
		if (!formBean.getStrMsgType().equals("1"))
			return LIST(_mapping, _form, _request, _response);
		return _mapping.findForward("allotement");
	}
	
	public ActionForward CANCELAPPROVAL(ActionMapping _mapping,
			ActionForm _form, HttpServletRequest _request,
			HttpServletResponse _response) throws HisException, SQLException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _form;
		formBean.setStrHospitalCode(_request.getSession().getAttribute("HOSPITAL_CODE").toString());
		MsApprovalTransDATA.cancelapproval(formBean, _request);
		if (!formBean.getStrMsgType().equals("1"))
			return LIST(_mapping, _form, _request, _response);
		return _mapping.findForward("cancel");
	}
	
	
	
	
	
	
	
	// provide ordered list of approved patient in popup window
	public ActionForward PATLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		MsApprovalTransDATA.PATLIST((MsApprovalTransFB) form);

		String strtarget = "patlist";
		return mapping.findForward(strtarget);
	}

	public ActionForward GOWARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;

		MsApprovalTransDATA.msallote(formBean, request);
		String strtarget = "allotement";
		return mapping.findForward(strtarget);
	}

	// Action in case of approve mode
	

	// Ward allotement
	

	public ActionForward DEPTUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		MsApprovalTransDATA.DEPTUNIT(formBean, request, response); // defined
		// in DATA
		// class
		return null;
	}

	
/*
	public ActionForward ROOMDETAIL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.ROOMDETAIL(formBean, request, response); // defined
		// in
		// DATA
		// class
		return null;
	}
*/
	public ActionForward OCCUPATION(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.DEPTUNIT(formBean, request, response); // defined
		// in DATA
		// class
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	// insert in msapproval and occupation detail


	// Update approve status as approved



/*
	public ActionForward PRIORITY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;

		if (request.getParameter("chk") != null)
			MsApprovalTransDATA.prioritydata(request.getParameter("chk"),
					formBean);

		String strtarget = "priority";
		return mapping.findForward(strtarget);
	}
*/
	public ActionForward APPROVLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		if (request.getParameter("chk") != null)
			MsApprovalTransDATA.chkFun(formBean, request.getParameter("chk"));
			MsApprovalTransDATA.listValues(formBean,request,response);

		String strtarget = "list";
		return mapping.findForward(strtarget);
	}

	public ActionForward LISTAPPROVEWAIT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		formBean.setStrHospitalCode((String)request.getAttribute("HOSPITAL_CODE"));
		formBean.setStrSeatId((String)request.getAttribute("SEATID"));
		MsApprovalTransDATA.generateApproveList(formBean);

		return mapping.findForward("list");

	}

	public ActionForward LISTNOTAPPROVED(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.notapprovedlist(formBean);

		return mapping.findForward("list");

	}
/*
	public ActionForward LISTCANCELMODE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.cancellist(formBean);

		return mapping.findForward("list");

	}
*/
	public ActionForward APPROVEDWAITREPORT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		// MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		return mapping.findForward("approvedwaitreport");
	}

	public ActionForward NOTAPPROVEDREPORT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		// MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		return mapping.findForward("notapprovedreport");
	}

	public ActionForward CANCELREPORT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		// MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		return mapping.findForward("cancelreport");
	}
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

		MsApprovalTransDATA.showReport(formBean, request, response);
		
		
	}
	public ActionForward UNITCOMBO(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) _Form;
		MsApprovalTransDATA.setUnitComboValues(formBean, _Request, _Response);
		return null;
	}
	public ActionForward DUPLICATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.checkDupicate(request, response, formBean);
		return null;
	}
	/*public ActionForward BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String strTarget = "";
		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.initBedStatus(formBean, request);*/
		/*strTarget = "bedstatus1";
		if(IpdTransConfig.getAdmissionAdviceMode().equals("0"))*/
			//strTarget = "bedstatus";
		/*
		 * if(formBean.isDeptFound()==false) { strTarget="bedstatus1"; } else {
		 * 
		 * strTarget="bedstatus1"; }
		 */

		/*return mapping.findForward(strTarget);
	}*/
	public ActionForward MODEWARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		MsApprovalTransFB formBean = (MsApprovalTransFB) form;
		MsApprovalTransDATA.initWardCombo(request, response, formBean);
		
		return null;
	}

}