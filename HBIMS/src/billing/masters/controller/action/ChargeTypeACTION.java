package billing.masters.controller.action;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.BillConfigUtil;
import billing.masters.controller.util.ChargeTypeUTIL;







public class ChargeTypeACTION extends GenericController
{

	String target = null;

	public ChargeTypeACTION ()
	{

		super(new ChargeTypeUTIL(), "masters/ChargeTypeACTION");

		
	}

	/**
	 * forwards control to the Add Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
		
	}


}




