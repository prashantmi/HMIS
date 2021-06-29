package billing.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ProvisionalFinalBillRptCNT extends DispatchAction {
	
	
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
	     {
	     
			ProvisionalFinalBillRptFB formBean = (ProvisionalFinalBillRptFB) form;
			ProvisionalFinalBillRptDATA.initReportPage(formBean,request);
			ProvisionalFinalBillRptDATA.getWARDCMB(formBean,request,response);
		    String target = "reportPage";
			return mapping.findForward(target);
		 }
		
	
			public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws HisException, SQLException 
		     {
		     
				ProvisionalFinalBillRptFB formBean = (ProvisionalFinalBillRptFB) form;
				ProvisionalFinalBillRptDATA.showReport(formBean,request,response);
			    return null;
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
			


