package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
//import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
//import hisglobal.vo.PatientDetailVO;



import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PatientTransferTransBSCNT extends  CSRFGardTokenAction{
	boolean fDesk = false;
	boolean fSaveInDesk = false;
	public String bsid = "";

	public PatientTransferTransBSCNT() {
		// super(new PatientTransferTransUTL(),
		// "/billing/transactions/PatientTransferTransBSCNT");

	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException{
		fDesk = false;
		fSaveInDesk = false;
		return this.APPROVED(mapping, form, request, response);
	}

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		String target = "approval";
		if(fDesk)
			target = "addDesk";
		if(fSaveInDesk){
			return CANCELTODESK(mapping,form,request,response);
		}
		return mapping.findForward(target);
	}

	// ////////////////////////// CANCEL//////////////////////////////////
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		return this.APPROVED(mapping, form, request, response);
	}

	// ////////////////////////////SAVE///////////////////////////////////

	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strInsert = request.getParameter("strTempVal");
		formBean.setStrTransferTypeBelonging(request.getParameter("strTransferTypeBelonging"));
		formBean.setStrTransferTypeIssue(request.getParameter("strTransferTypeIssue"));
		//System.out.println("save...String->" + strInsert);
		formBean.setStrInsert(strInsert);
		PatientTransferTransDATA.insert(formBean);
		formBean.setStrCrNo("");
		if(fDesk)
    		fSaveInDesk = true;
		return this.APPROVED(mapping, form, request, response);
	}
	
	
	/*
	 *   Save Data Of Patient Movement from IPD DESK
	 *   IN Case Of IPD DESK Only
	 */
	
	public ActionForward SAVEIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strInsert = request.getParameter("strTempVal");
		formBean.setStrTransferTypeBelonging(request.getParameter("strTransferTypeBelonging"));
		formBean.setStrTransferTypeIssue(request.getParameter("strTransferTypeIssue"));
		//System.out.println("save...String->" + strInsert);
		formBean.setStrInsert(strInsert);
		PatientTransferTransDATA.insert(formBean);
		formBean.setStrCrNo("");
		
		
		if(fDesk)
    		fSaveInDesk = true;
		
		/*   
		 *  Returns To Patient Movement Page if Error Occurs 
		 *  Else Success Page Will Show the Success Message
		 *  (successIPD.jsp)
		 */
		
		if(formBean.getStrMsgType().equals("1"))
		return this.APPROVED(mapping, form, request, response);
		else
		return mapping.findForward("messageIpdDesk");
	}
	
	
	// ////////////////////////GO FUNCTION//////////////////////////
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		String strTarget = "approval";
		boolean retVal = false;
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientTransferTransDATA.getRsnRmk(formBean);
		
		 ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
		 formBean.setStrHospChange(hisProp.getString("HOSPITAL_CHANGE"));
		 formBean.setStrTime(PatientTransferTransHLP.now());
		 retVal = PatientTransferTransDATA.admstat(formBean);
		 System.out.println("Go.retVal--------->"+retVal);
		if (retVal == true) {
			PatientTransferTransDATA.initOnlineReq(request
					.getParameter("strTempVal"), formBean);
		} else {
			formBean.setStrCrNo("");
		}
		if(fDesk)
			strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}
	
	
	/*
	 *  This Method is used when User Clicks
	 *  Movement Button From IPD Desk 
     */
	
	public ActionForward GOIPDDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		
		String strTarget = "addDeskIpd";
		boolean retVal = false;
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		HttpSession hs=request.getSession();
		/*PatientDetailVO voDP = (PatientDetailVO)hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		formBean.setStrCrNo(voDP.getPatCrNo());
		formBean.setStrAdmNo(voDP.getPatAdmNo());
		formBean.setStrDepartment(voDP.getDepartmentCode());
		formBean.setStrWard(voDP.getWardCode());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrRoom(voDP.getRoomCode());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());*/
		
		formBean.setStrCrNo(request.getParameter("patCrNo"));
		formBean.setStrAdmNo(request.getParameter("patAdmNo"));
		formBean.setStrDepartment(request.getParameter("departmentUnitCode").substring(0, 3));
		formBean.setStrWard(request.getParameter("wardCode"));
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrRoom(request.getParameter("roomCode"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientTransferTransDATA.getRsnRmk(formBean);
		 ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
		 formBean.setStrHospChange(hisProp.getString("HOSPITAL_CHANGE"));
		 formBean.setStrTime(PatientTransferTransHLP.now());
		 retVal = PatientTransferTransDATA.admstat(formBean);
		if (retVal == true)
		{
			PatientTransferTransDATA.initOnlineReq(request.getParameter("strTempVal"), formBean);
		}
		else
		{
			formBean.setStrCrNo("");
		}
	
		return mapping.findForward(strTarget);
	}
	

	public ActionForward transOf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException 
	{
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strValmode = (String) request.getParameter("modName");
		PatientTransferTransDATA.department(formBean);
		PatientTransferTransDATA.getServiceType(formBean, request, response);
		PatientTransferTransDATA.initBedStatus(formBean, request);
		String sid = PatientTransferTransDATA.AjaxResponse(formBean,strValmode, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(sid);
		return null;
	}
	
	
	
	/*
	 * 
	 *  In Case Of IPD Desk Only
	 *  Ajax Request.
	 */
	public ActionForward transOfIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException 
	{
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strValmode = (String) request.getParameter("modName");
		PatientTransferTransDATA.department(formBean);
		PatientTransferTransDATA.getServiceType(formBean, request, response);
		PatientTransferTransDATA.initBedStatus(formBean, request);
		String sid = PatientTransferTransDATA.AjaxResponseIPD(formBean,strValmode, request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(sid);
		return null;
	}
	
	
	public ActionForward GETSERVICENAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		PatientTransferTransFB formBean = (PatientTransferTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String serviceTypeId=request.getParameter("serviceTypeId");
		formBean.setStrServiceTypeId(serviceTypeId);
		PatientTransferTransDATA.getServiceName(formBean,request,response);
		return null;
		
	}
	
	public ActionForward SERVICEVALIDATION(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		PatientTransferTransFB formBean = (PatientTransferTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientTransferTransDATA.serviceValidation(formBean,request,response);
		return null;
		
	}
	
	public ActionForward TRANSFERADVISEDBY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws HisException, Exception, SQLException
	{
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		String strValmode = (String) request.getParameter("curDept");
		formBean.setStrCurrentDeptUnitRoom(strValmode);
		PatientTransferTransDATA.getTransferAdvisedBy(formBean, request,response);
		
		
		return null;
	}
	public ActionForward UNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	//	System.out.println("INSIDE UNIT");
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		PatientTransferTransDATA.unit(formBean, request, response); // defined
																	// in DATA
																	// class
		return null;
	}

	public ActionForward WARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
	//	String strAjax = (String) request.getParameter("modWardTpe");
	//	System.out.println("dept^depUnit^wardTyp->" + strAjax);
		PatientTransferTransDATA.ward(formBean, request, response); // defined
																	// in DATA
																	// class
		return null;
	}
	
	
	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientTransferTransDATA.room(formBean, request, response); // defined
																	// in DATA
																	// class
		return null;
	}
	public ActionForward ROOMCOMBO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientTransferTransDATA.roomCombo(formBean, request, response); // defined
																	// in DATA
																	// class
		return null;
	}
	public ActionForward BED(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientTransferTransDATA.bed(formBean, request, response); // defined
																	// in DATA
																	// class
		return null;
	}

	public ActionForward ServRoom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		PatientTransferTransDATA.servRoom(formBean, request, response); // defined
																		// in
																		// DATA
																		// class
		return null;
	}

	public ActionForward ConsltntID(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		PatientTransferTransDATA.consltntID(formBean, request, response); // defined
																			// in
																			// DATA
																			// class
		return null;
	}

	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	public ActionForward ADDNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fDesk = true;
		PatientTransferTransFB formBean = (PatientTransferTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
		return GO(mapping, form, request, response);
	}
	
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskFinalTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	/*
	 * public ActionForward BEDSTATUS(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException {
	 * 
	 * PatientTransferTransFB formBean = (PatientTransferTransFB) form;
	 * formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
	 * formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	 * PatientTransferTransDATA.beddetail(formBean,request,response); String
	 * strtarget = "bedstatus"; return mapping.findForward(strtarget); }
	 */
}
