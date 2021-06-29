package ipd.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;
import ipd.IpdConfigUtil;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientLeaveRequestTransCNT extends DispatchAction
{
	boolean fDesk = false;
	boolean fSaveInDesk = false;
	boolean fLeaveDesk = false;
	public String bsid="";
	public PatientLeaveRequestTransCNT()
	{
	  //super(new PatientLeaveRequestTransUTL(), "/billing/transactions/PatientLeaveRequestTransCNT");
	}
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException
	{
		fDesk = false;
		fLeaveDesk = false;
		fSaveInDesk = false;
		return this.APPROVED(mapping, form, request, response);
	}

	//////////////////////DISCOUNT APPROVED///////////////////////////

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException
	{
		String target = "approval";
		if(fDesk||fLeaveDesk)
			target = "addDesk";
		if(fSaveInDesk){
			return CANCELTODESK(mapping,form,request,response);
		}
		if(fLeaveDesk)
			return CANCELTOLEAVEDESK(mapping,form,request,response);
		return mapping.findForward(target);
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
    	PatientLeaveRequestTransFB formBean = (PatientLeaveRequestTransFB)form;
    	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
    	PatientLeaveRequestTransDATA.insertLJ(formBean);
    	formBean.setStrCrNo("");
    	if(fDesk)
    		return CANCELTODESK(mapping, form, request, response);
    	if(fLeaveDesk)
			return CANCELTOLEAVEDESK(mapping,form,request,response);
    	return this.APPROVED(mapping, form, request, response);//.APPROVED(mapping, form, request, response);
	}

	//////////////////////////GO FUNCTION//////////////////////////
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		
		String strTarget = "approval";
	    boolean retVal=false;
		PatientLeaveRequestTransFB formBean = (PatientLeaveRequestTransFB) form;
		IpdConfigUtil ipdUtil =new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strLeavereqType=ipdUtil.getStrLeaveReqType();
		formBean.setStrLeaveReqType(strLeavereqType);
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		retVal=PatientLeaveRequestTransDATA.admstat(formBean);
		if(retVal==true)
		{	
		 PatientLeaveRequestTransDATA.getLeaveRequestValidate(formBean);
		}
		else
			formBean.setStrCrNo("");
		if(fDesk||fLeaveDesk)
			strTarget = "addDesk";
		 return mapping.findForward(strTarget);
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
			throws HisException, SQLException {
		fDesk = true;
		PatientLeaveRequestTransFB formBean = (PatientLeaveRequestTransFB) form;
		IpdConfigUtil ipdUtil =new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strLeavereqType=ipdUtil.getStrLeaveReqType();
		
		
		if(strLeavereqType.equals("2"))//Both
		{
				if(request.getParameter("strCheckbox")!=null && request.getParameter("strCheckbox").equals("0"))//Online
				{
					formBean.setStrTmpLabel("0");
					formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
					formBean.setChk(request.getParameter("chk"));
					return GO(mapping, form, request, response);
				}
				else if(request.getParameter("strCheckbox")!=null && request.getParameter("strCheckbox").equals("1"))//Offline
				{
					formBean.setStrTmpLabel("1");
					formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
					formBean.setChk(request.getParameter("chk"));
					return OFFLINELEAVE(mapping, form, request, response);
				}
				else
				{
					formBean.setStrTmpLabel("1");
					formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
					formBean.setChk(request.getParameter("chk"));
					return OFFLINELEAVE(mapping, form, request, response);
				}		
		}
		else
		{
			if(strLeavereqType.equals("1"))//Offline
			{
				
				formBean.setStrTmpLabel("1");
				formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
				formBean.setChk(request.getParameter("chk"));
				return OFFLINELEAVE(mapping, form, request, response);
			}
			else
			{
				formBean.setStrTmpLabel("0");
				formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
				formBean.setChk(request.getParameter("chk"));
				return GO(mapping, form, request, response);
			}
		}
		
	}
	
	public ActionForward ADDLEAVEDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fLeaveDesk = true;
		PatientLeaveRequestTransFB formBean = (PatientLeaveRequestTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
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
	public ActionForward OFFLINELEAVE(ActionMapping _mapping, ActionForm form,
			HttpServletRequest _request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/PatientLeaveCNT.cnt?hmode=ADDNURSINGDESK");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
