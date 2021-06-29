package new_investigation.transactions.controller.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.invListingReportNewFB;
import new_investigation.transactions.controller.fb.invStatusDashboardFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.invListingReportNewUTIL;
import new_investigation.transactions.controller.utl.invStatusDashboardUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class invStatusDashboardACT extends CSRFGardTokenAction{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
		generateToken(request);
		invStatusDashboardFB fb=(invStatusDashboardFB)form;
		HttpSession session=WebUTIL.getSession(request);
		invStatusDashboardUTIL.getStatusDashoardRecord(fb, request);
		/*DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		fb.setLastFetchedTime(dateFormat.format(date));*/
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETSAMPLELIST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		invStatusDashboardFB fb=(invStatusDashboardFB)objForm_p;
		invStatusDashboardUTIL.getSampleList(fb,objRequest_p);
		return objMapping_p.findForward("GETSAMPLELIST");
	}
}
