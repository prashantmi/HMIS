package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BillPrintTransCNT extends DispatchAction {
	public String bsid = "";

	public BillPrintTransCNT() {
	}

	/** *********************UNSPECIFIED************************** */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException

	{
		return this.APPROVED(mapping, form, request, response);
	}

	/** *********************DISCOUNT APPROVED************************** */

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		String target = "approval";
		return mapping.findForward(target);
	}

	/** *************************CANCEL********************************* */

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward acFwd = new ActionForward();

		acFwd.setPath("/hisglobal/initPage.jsp");

		acFwd.setContextRelative(true);

		return acFwd;

	}

	/** *************************INITIALPAGE********************************* */

	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		// String target = "initial";
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/startup/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;

	}

	/** *************************GO FUNCTION********************************* */

	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		// System.out.println("cnt go");
		String strTarget = "approval";
		BillPrintTransFB formBean = (BillPrintTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
			BillPrintTransDATA.initOnlineReq( formBean);
		
			return mapping.findForward(strTarget);
	

	}
	
	
	public ActionForward PRINT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
	//	String strTarget = "index";
		BillPrintTransFB formBean = (BillPrintTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		Boolean retValue = BillPrintTransDATA.printBill(formBean , request);
		if(retValue)
		{	
			//return mapping.findForward(strTarget);
			return this.APPROVED(mapping, form, request, response);
		}
		return null;
	}
	
	
	
}
