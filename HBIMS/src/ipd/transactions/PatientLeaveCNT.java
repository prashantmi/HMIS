package ipd.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientLeaveCNT extends DispatchAction
{
	public String bsid="";
	boolean fDesk = false;
	boolean fLeaveDesk = false;
	public PatientLeaveCNT()
	{
	  //super(new PatientLeaveUTL(), "/billing/transactions/PatientLeaveCNT");
	}
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException
	{
		fDesk = false;
		fLeaveDesk = false;
		return this.APPROVED(mapping, form, request, response);
	}

	//////////////////////DISCOUNT APPROVED///////////////////////////

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException
	{
    	PatientLeaveFB formBean = (PatientLeaveFB)form;
		formBean.setStrRemarksOthersOfflineMandatoryFlag(IpdTransConfig.getMandatoryAdviceAtLeaveOffline());
		String strTarget = "approval";
		if(fDesk||fLeaveDesk)
	    	strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}
	//////////////////////////// CANCEL//////////////////////////////////
    public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
        return this.APPROVED(mapping, form, request, response);
	}

    public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
    	PatientLeaveFB formBean = (PatientLeaveFB)form;
    	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
    	if(formBean.getStrAdmStatCode().equals("12"))
    	  PatientLeaveDATA.insertLJ(formBean);
    	else if(formBean.getStrAdmStatCode().equals("15"))
    	  PatientLeaveDATA.upadateLeaveApprovalDtl(formBean);	
    	else
    	  formBean.setStrErrMsgString("Invalid CR No or Patient Not Admitted");	 
    	formBean.setStrCrNo("");
    	if(fDesk)
	    	return CANCELTODESK(mapping, form, request, response);
    	if(fLeaveDesk)
    		return CANCELTOLEAVEDESK(mapping, form, request, response);
    	return this.APPROVED(mapping, form, request, response);//.APPROVED(mapping, form, request, response);
	}

	//////////////////////////GO FUNCTION//////////////////////////
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		
		String strTarget = "approval";
		boolean retVal=false;
		PatientLeaveFB formBean = (PatientLeaveFB) form;
		formBean.setStrRemarksOthersOfflineMandatoryFlag(IpdTransConfig.getMandatoryAdviceAtLeaveOffline());
		formBean.setStrRemarksOthersJoinMandatoryFlag(IpdTransConfig.getMandatoryRemarksOthersLeaveJoin());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	    PatientLeaveDATA.getRsnRmk(formBean);
	    retVal=PatientLeaveDATA.admstat(formBean);
	    if(retVal==true)
	    {	
	     if(formBean.getStrAdmStatCode().equals("15"))
	        PatientLeaveDATA.setLeaveDtl(formBean);
	    }
	    else
	    {
	    	formBean.setStrCrNo("");
	    }
	    if(fDesk||fLeaveDesk)
	    	strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}
	
	/*
	 *  This Method is used when User Clicks
	 *  Leave Request Button From IPD Desk 
     */
	
	public ActionForward GOIPDDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		
		String strTarget = "approval";
		boolean retVal=false;
		PatientLeaveFB formBean = (PatientLeaveFB) form;
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
		formBean.setStrRemarksOthersOfflineMandatoryFlag(IpdTransConfig.getMandatoryAdviceAtLeaveOffline());
		formBean.setStrRemarksOthersJoinMandatoryFlag(IpdTransConfig.getMandatoryRemarksOthersLeaveJoin());
		formBean.setStrCheckFlagType("1");
		PatientLeaveDATA.getRsnRmk(formBean);
	    retVal=PatientLeaveDATA.admstat(formBean);
	    if(retVal==true)
	    {	
	     if(formBean.getStrAdmStatCode().equals("15"))
	        PatientLeaveDATA.setLeaveDtl(formBean);
	    }
	    else
	    {
	    	formBean.setStrCrNo("");
	    }
	    //if(fDesk||fLeaveDesk)
	    	strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}
	
	public ActionForward transOf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException,Exception, SQLException
	{
		String sid=null;
		PatientLeaveFB formBean = (PatientLeaveFB)form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strValmode = (String) request.getParameter("modName");
		String isBedVacant= (String) request.getParameter("isBedVacant");
		formBean.setStrIsBedVacant(isBedVacant);
		PatientLeaveDATA.initBedStatus(formBean,request);
		formBean.setStrjHLP(request.getParameter("jHLP"));
		if(formBean.getStrjHLP().equals("1"))
		{
		   sid = PatientLeaveDATA.AjaxResponse(formBean,strValmode,request);
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().print(sid);
		} 
		return null;
	}

	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		PatientLeaveFB formBean = (PatientLeaveFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientLeaveDATA.room(formBean,request,response);		//defined in DATA class
		return null;
	}
	
	public ActionForward BED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{		
		PatientLeaveFB formBean = (PatientLeaveFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientLeaveDATA.bed(formBean,request,response);		//defined in DATA class
		return null;
	}
	public ActionForward  BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		PatientLeaveFB formBean = (PatientLeaveFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientLeaveDATA.beddetail(formBean,request,response);
		    String strtarget = "bedstatus";
			return mapping.findForward(strtarget);
	}
	public ActionForward INITIALPAGE(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
    {
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/hisglobal/initPage.jsp");
				acFwd.setContextRelative(true);
				return acFwd;
	} 
	
	public ActionForward ADDNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException{
		fDesk = true;
		PatientLeaveFB formBean = (PatientLeaveFB) form;
		IpdConfigUtil ipdUtil =new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strLeavereqType=ipdUtil.getStrLeaveReqType();
		formBean.setStrLeaveReqType(strLeavereqType);
		formBean.setStrTmpLabel("1");
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		formBean.setChk(request.getParameter("chk"));
		return GO(mapping, form, request, response);
	}
	
	public ActionForward ADDLEAVEDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException{
		fLeaveDesk = true;
		PatientLeaveFB formBean = (PatientLeaveFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		return GO(mapping, form, request, response);
	}
	
	public ActionForward CANCELTOLEAVEDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/LeaveDeskTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		if(fLeaveDesk)
			return CANCELTOLEAVEDESK(mapping, form, request, response);
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskFinalTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public ActionForward LEAVEREQUEST(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveRequestTransCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward SAVEIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
    	PatientLeaveFB formBean = (PatientLeaveFB)form;
    	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
    	if(formBean.getStrAdmStatCode().equals("12"))
    	  PatientLeaveDATA.insertLJ(formBean);
    	else if(formBean.getStrAdmStatCode().equals("15"))
    	  PatientLeaveDATA.upadateLeaveApprovalDtl(formBean);	
    	else
    	  formBean.setStrErrMsgString("Invalid CR No or Patient Not Admitted");	 
    	formBean.setStrCrNo("");
    	/*if(fDesk)
	    	return CANCELTODESK(mapping, form, request, response);
    	if(fLeaveDesk)
    		return CANCELTOLEAVEDESK(mapping, form, request, response);
    	return this.APPROVED(mapping, form, request, response);//.APPROVED(mapping, form, request, response);*/
    	if(formBean.getStrMsgType().equals("1"))
    		return this.APPROVED(mapping, form, request, response);
    	else
    		return mapping.findForward("messageIpdDesk");
	}
}
