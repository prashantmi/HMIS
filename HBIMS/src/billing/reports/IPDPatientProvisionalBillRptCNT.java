package billing.reports;

	import hisglobal.exceptions.HisException;

	import java.sql.SQLException;

	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.CSRFGardTokenAction;


	public class IPDPatientProvisionalBillRptCNT extends CSRFGardTokenAction {
		
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
	     {
	     
			generateToken(request);
			IPDPatientProvisionalBillRptFB formBean = (IPDPatientProvisionalBillRptFB) form;
			formBean.setStrCrNo("");
			String target = "reportPage";
		
			return mapping.findForward(target);
		 }
		
	
			public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
		     {
		     
				IPDPatientProvisionalBillRptFB formBean = (IPDPatientProvisionalBillRptFB) form;
				IPDPatientProvisionalBillRptDATA.getPatientDtls(formBean,request);
				if(formBean.getChk()[0].equals("0"))
				{
					formBean.setStrCrNo("");
					formBean.setStrErrMsg("No Data Found/Account Not Opened/Patient Not Accepted in Ward/Bill Settlement Done/Final Bill Already Generated!");
					return mapping.findForward("reportPage");
				}
				else
				{
					if(formBean.getStrPatAcctStatus().equals("1") || formBean.getStrPatAcctStatus().equals("2"))
					{
						IPDPatientProvisionalBillRptDATA.showReport(formBean,request,response);
						return null;
					}
					else
					{
						formBean.setStrCrNo("");
						formBean.setStrErrMsg("Bill Settlement Done/Final Bill Already Generated!.Please Refer Final Bill");
						return mapping.findForward("reportPage");
					}
				   
				}
			 }
			
			public ActionForward SHOWRPT_IPDNURDESK(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
		     {
		     
				
				IPDPatientProvisionalBillRptFB formBean = (IPDPatientProvisionalBillRptFB) form;
				formBean.setStrCrNo(request.getParameter("patCrNo"));
				IPDPatientProvisionalBillRptDATA.getPatientDtls(formBean,request);
				if(formBean.getChk()[0].equals("0"))
				{
					formBean.setStrCrNo("");
					formBean.setStrErrMsg("No Data Found/Account Not Opened/Patient Not Accepted in Ward/Bill Settlement Done/Final Bill Already Generated!");
					return mapping.findForward("reportPage");
				}
				else
				{

					if(formBean.getStrPatAcctStatus().equals("1") || formBean.getStrPatAcctStatus().equals("2"))
					{
						IPDPatientProvisionalBillRptDATA.showReport(formBean,request,response);
					    return null;
					}
					else
					{
						formBean.setStrCrNo("");
						formBean.setStrErrMsg("Bill Settlement Done/Final Bill Already Generated!.Please Refer Final Bill");
						return mapping.findForward("reportPage");
					}
				}
			 }
			
			
			public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
			{
			    ActionForward acFwd = new ActionForward();
				acFwd.setPath("/hisglobal/initPage.jsp");
				acFwd.setContextRelative(true);
				return acFwd;
			}
	}
			


