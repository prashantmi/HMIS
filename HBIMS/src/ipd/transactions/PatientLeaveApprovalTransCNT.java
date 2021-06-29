package ipd.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientLeaveApprovalTransCNT extends DispatchAction
{
	public String bsid="";
	public PatientLeaveApprovalTransCNT()
	{
	  //super(new PatientLeaveApprovalTransUTL(), "/billing/transactions/PatientLeaveApprovalTransCNT");
	}
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws HisException
	{
		return this.APPROVED(mapping, form, request, response);
	}


	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException
	{
		String target = "approval";
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
    	PatientLeaveApprovalTransFB formBean = (PatientLeaveApprovalTransFB)form;
    	formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
    	PatientLeaveApprovalTransDATA.upadateLeaveApprovalDtl(formBean);
    	formBean.setStrCrNo("");
    	return this.APPROVED(mapping, form, request, response);
	}

	//////////////////////////GO FUNCTION//////////////////////////
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException
	{
		//String strTarget = "approval";
		
		boolean retVal=false;
		PatientLeaveApprovalTransFB formBean = (PatientLeaveApprovalTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		retVal=PatientLeaveApprovalTransDATA.admstat(formBean);
		if(retVal==true)
		{		
		 retVal=PatientLeaveApprovalTransDATA.setLeaveDtl(formBean);
		 if(retVal==true)
		   PatientLeaveApprovalTransDATA.getRsnRmk(formBean);
		 else
		  formBean.setStrCrNo("");	
		}
		else
		{
		  formBean.setStrCrNo("");		
		}
		return this.APPROVED(mapping, form, request, response);
	}

	public ActionForward INITIALPAGE(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
    {
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/hisglobal/initPage.jsp");
				acFwd.setContextRelative(true);
				return acFwd;
	} 
}
