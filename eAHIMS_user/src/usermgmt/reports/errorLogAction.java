package usermgmt.reports;
import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.List;
import java.util.ArrayList;



public class errorLogAction extends DispatchAction
{


	errorLogUtil myUtil = new errorLogUtil();



	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		errorLogActionForm myForm = (errorLogActionForm)form;
		System.out.println("INSIDE unspecified");
		try
		{
		request.setAttribute("deptComboList",myUtil.getDeptCombo());
		request.setAttribute("sysdate",myUtil.getSysdate());
		request.setAttribute("toDate",myForm.getToDate());
		request.setAttribute("fromDate",myForm.getFromDate());
		
		}
		catch(Exception e)
		{
			System.out.println("Exception is "+e);
		}
		return mapping.findForward("init");
	}




	public ActionForward SAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("INSIDE SAME");
		errorLogActionForm myForm = (errorLogActionForm)form;
		try
		{
			request.setAttribute("sysdate",myUtil.getSysdate());
			request.setAttribute("deptComboList",myUtil.getDeptCombo());
			request.setAttribute("toDate",myForm.getToDate());
			request.setAttribute("fromDate",myForm.getFromDate());
		}
		catch(Exception e)
		{
			System.out.println("Exception in deptComboList ...... "+e);
		}

		try
		{	//request.setAttribute("patherror", myForm.getErrorLogPath());
			request.setAttribute("errorDetails",myUtil.getErrorDetails(myForm.getErrorLogPath(),myForm.getFromDate(),myForm.getToDate()));
		}
		catch(Exception e)
		{
			System.out.println("Exception in errorDetails......... "+e);
		}


		return mapping.findForward("same");
	}



}
