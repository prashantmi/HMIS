package billing.masters.controller.action;
import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import billing.masters.controller.fb.GblConfigTypeFB;
import billing.masters.controller.util.GblConfigTypeUtil;

public class  GblConfigTypeACTION extends DispatchAction
{

	String target = null;

	
	
	public ActionForward unspecified(ActionMapping mapping
            ,ActionForm form
            ,HttpServletRequest request
            ,HttpServletResponse response )throws HisException, SQLException
			{
			GblConfigTypeFB fb = (GblConfigTypeFB)form;
			fb.setHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			GblConfigTypeUtil.getGblConfigData(request, response , fb);
			return mapping.findForward("new");
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







