package billing.transactions;
/*
 * IPD Bill Management New ACTION
 * 
 * author:Debashis Sardar
 * 
 * dated:12th Mar 2013
 */
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


public class IpdBillManagementTransNewCNT extends CSRFGardTokenAction{

	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB) form;
		formBean.setStrCrNo("");
		String target = "ipdBillManagementNew";
	
		return mapping.findForward(target);

    }
	
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB) form;
		  
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		String strTarget = "ipdBillManagementNew";
		IpdBillManagementTransNewDATA.getAdmissionDtls(formBean,request);
		
		return mapping.findForward(strTarget);
		}
	
	public ActionForward GOADM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		IpdBillManagementTransFB formBean = (IpdBillManagementTransFB) form;
		  
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		String strTarget = "ipdBillManagementNew";
		IpdBillManagementTransNewDATA.getPatientDtls(formBean,request);
		String strAccStatus=formBean.getChk()[0].split("@")[11];
		if(formBean.getChk()[0].equals("0") && !strAccStatus.equals("4"))
		{
			formBean.setStrCrNo("");
			formBean.setStrErrMsg("No Data Found/Account Not Opened/Patient Not Accepted in Ward!");
			return mapping.findForward(strTarget);
		}
		else
		{
			ActionForward acFwd = new ActionForward();
			formBean.setStrIpdBillManagementMode("2");
			//request.getSession().setAttribute("USERVALUE", "0");// Clerk Wise
			formBean.setStrIsCalledFromIpdBillNew("1");
			
			String finalBillFlag=formBean.getChk()[0].split("@")[12];
			if(finalBillFlag.equals("91"))
				request.getSession().setAttribute("USERVALUE", "1");// Auditor Wise
			else
				request.getSession().setAttribute("USERVALUE", "0");// Clerk Wise
			formBean.setStrAcctStatMode(strAccStatus);
			String[] StrCombo={strAccStatus,"1","1"};
			formBean.setCombo(StrCombo);
			if(strAccStatus.equals("1"))
			    acFwd.setPath("/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=ADDSERVICE");
			else if(strAccStatus.equals("2"))
			    acFwd.setPath("/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=ADDSERVICE");
			else if(strAccStatus.equals("0"))
			{
				/*formBean.setStrCrNo("");
				formBean.setStrErrMsg("Patient Account has been Closed!");
				return mapping.findForward(strTarget);*/
				acFwd.setPath("/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=BILLAPPROVAL");
			}
			else if(strAccStatus.equals("5"))
			{
				formBean.setStrCrNo("");
				formBean.setStrErrMsg("Patient Bill has been Approved and sent for Final Adjustment!");
				return mapping.findForward(strTarget);
			}
			else if(strAccStatus.equals("6"))
			{
				formBean.setStrCrNo("");
				formBean.setStrErrMsg("Account Cancelled. IPD Billing Not Allowed!");
				return mapping.findForward(strTarget);
			}
			else if(strAccStatus.equals("3"))
			{
				formBean.setStrCrNo("");
				formBean.setStrErrMsg("Account on-Hold. IPD Billing Not Allowed!");
				return mapping.findForward(strTarget);
			}
			else
				acFwd.setPath("/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=UPDATEACCOUNTSTATUS");
			acFwd.setContextRelative(true);
			return acFwd;
		}
		
	}
	
	
	
		
	
}
