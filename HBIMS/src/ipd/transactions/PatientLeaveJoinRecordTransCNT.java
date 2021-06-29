package ipd.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;
import ipd.IpdTransConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientLeaveJoinRecordTransCNT extends DispatchAction
{
	boolean fDesk = false;
	boolean fSaveInDesk = false;
	boolean fLeaveDesk = false;
	boolean fSaveInLeaveDesk = false;
	public String bsid="";
	public PatientLeaveJoinRecordTransCNT()
	{
	  //super(new PatientLeaveJoinRecordTransUTL(), "/billing/transactions/PatientLeaveJoinRecordTransCNT");
	}
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException
	{
		fDesk = false;
		fSaveInDesk = false;
		fSaveInLeaveDesk = false;
		fLeaveDesk = false;
		return this.APPROVED(mapping, form, request, response);
	}

	//////////////////////DISCOUNT APPROVED///////////////////////////

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException
	{		
		String strTarget = "";
    	if (!(fDesk||fLeaveDesk))
			strTarget = "approval";
		else
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
    	PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB)form;
    	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
    	PatientLeaveJoinRecordTransDATA.upadateLeaveApprovalDtl(formBean);
    	formBean.setStrCrNo("");
    	if(fSaveInDesk)
    		return this.CANCELTODESK(mapping, form, request, response);
    	if(fSaveInLeaveDesk)
    		return this.CANCELTOLEAVEDESK(mapping, form, request, response);
    	return this.APPROVED(mapping, form, request, response);
	}
    
    public ActionForward SAVEIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
    	PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB)form;
    	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
    	PatientLeaveJoinRecordTransDATA.upadateLeaveApprovalDtl(formBean);
    	formBean.setStrCrNo("");
    	/*if(fSaveInDesk)
    		return this.CANCELTODESK(mapping, form, request, response);
    	if(fSaveInLeaveDesk)
    		return this.CANCELTOLEAVEDESK(mapping, form, request, response);
    	return this.APPROVED(mapping, form, request, response);*/
    	if(formBean.getStrMsgType().equals("1"))
    		return this.APPROVED(mapping, form, request, response);
    	else
    		return mapping.findForward("messageIpdDesk");
	}

	//////////////////////////GO FUNCTION//////////////////////////
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		
		String strTarget = "approval";
		boolean retVal=false;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrRemarksOthersJoinMandatoryFlag(IpdTransConfig.getMandatoryRemarksOthersLeaveJoin());
		formBean.setStrRemarksOthersOnlineMandatoryFlag(IpdTransConfig.getMandatoryAdviceAtLeaveRecord());
		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//String tmp=request.getParameter("strTempLeaveJoin");
	//	formBean.setStrTempLeaveJoin(tmp);
		retVal=PatientLeaveJoinRecordTransDATA.admstat(formBean);
		if(retVal==true)
		{	
	      retVal=PatientLeaveJoinRecordTransDATA.setLeaveDtl(formBean);
	      if(retVal==true)
	        PatientLeaveJoinRecordTransDATA.getRsnRmk(formBean);
	      else
	    	formBean.setStrCrNo("");
		}
		else
		  formBean.setStrCrNo("");	
		if (!(fDesk||fLeaveDesk))
			strTarget = "approval";
		else
			strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}
	
	/*
	 *  This Method is used when User Clicks
	 *  Join Record Button From IPD Desk 
     */
	
	public ActionForward GOIPDDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		
		String strTarget = "approval";
		boolean retVal=false;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
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
		formBean.setStrRemarksOthersJoinMandatoryFlag(IpdTransConfig.getMandatoryRemarksOthersLeaveJoin());
		formBean.setStrRemarksOthersOnlineMandatoryFlag(IpdTransConfig.getMandatoryAdviceAtLeaveRecord());
		formBean.setStrCheckFlagType("1");
		retVal=PatientLeaveJoinRecordTransDATA.admstat(formBean);
		if(retVal==true)
		{	
	      retVal=PatientLeaveJoinRecordTransDATA.setLeaveDtl(formBean);
	      if(retVal==true)
	        PatientLeaveJoinRecordTransDATA.getRsnRmk(formBean);
	      else
	    	formBean.setStrCrNo("");
		}
		else
		  formBean.setStrCrNo("");	
		/*if (!(fDesk||fLeaveDesk))
			strTarget = "approval";
		else*/
			strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}
	
	public ActionForward transOf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException,Exception, SQLException
	{
		String sid=null;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB)form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strValmode = (String) request.getParameter("modName");
		String isBedVacant= (String) request.getParameter("isBedVacant");
		formBean.setStrIsBedVacant(isBedVacant);
		PatientLeaveJoinRecordTransDATA.initBedStatus(formBean,request);
		sid = PatientLeaveJoinRecordTransDATA.AjaxResponse(formBean,strValmode,request);
		   response.setHeader("Cache-Control", "no-cache");
		   response.getWriter().print(sid);
		return null;
	}

/*	public ActionForward WARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strAjax=	 (String) request.getParameter("modWardTpe");
		System.out.println("dept^depUnit^wardTyp->"+strAjax);
		PatientLeaveJoinRecordTransDATA.ward(formBean,request,response);		//defined in DATA class
		return null;
	}*/

	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientLeaveJoinRecordTransDATA.room(formBean,request,response);		//defined in DATA class
		return null;
	}
	public ActionForward BED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{		
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientLeaveJoinRecordTransDATA.bed(formBean,request,response);		//defined in DATA class
		return null;
	}
	public ActionForward  BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
             
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientLeaveJoinRecordTransDATA.beddetail(formBean,request,response);
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
	
	public ActionForward ADDLEAVENURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fDesk = true;
		fSaveInDesk = true;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		formBean.setStrTempLeaveJoin("leave");
		formBean.setStrTempLeaveJoinLabel("Leave");
		return GO(mapping, form, request, response);
	}
	
	public ActionForward ADDJOINNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fDesk = true;
		fSaveInDesk = true;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
		formBean.setStrTempLeaveJoin("join");
		formBean.setStrTempLeaveJoinLabel("Join");
		return GO(mapping, form, request, response);
	}
	
	public ActionForward ADDLEAVEONLEAVEDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fLeaveDesk = true;
		fSaveInLeaveDesk = true;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		formBean.setStrTempLeaveJoin("leave");
		formBean.setStrTempLeaveJoinLabel("Leave");
		return GO(mapping, form, request, response);
	}
	
	public ActionForward ADDJOINLEAVEDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fLeaveDesk = true;
		fSaveInLeaveDesk = true;
		PatientLeaveJoinRecordTransFB formBean = (PatientLeaveJoinRecordTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		formBean.setStrTempLeaveJoin("join");
		formBean.setStrTempLeaveJoinLabel("Join");
		return GO(mapping, form, request, response);
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
	public ActionForward CANCELTOLEAVEDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/LeaveDeskTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
