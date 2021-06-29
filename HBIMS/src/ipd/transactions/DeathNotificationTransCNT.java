package ipd.transactions;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeathNotificationTransCNT extends DispatchAction {
	boolean fDesk = false;

	/**
	 * forwards control to the Page deathnotification_add.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		saveToken(request);
		fDesk = false;
		String target = "add";
		return mapping.findForward(target);
	}

	/**
	 * used to bring data on main screen
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		saveToken(request);
		
		boolean retVal = false;
		DeathNotificationTransFB formBean = (DeathNotificationTransFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		retVal = DeathNotificationTransDATA.patAdmStatus(formBean);
		if (retVal == true)
			DeathNotificationTransDATA.initDeathNotification(formBean);
		else
			formBean.setStrCrNo("");
		DeathNotificationTransDATA.isPregnant(formBean);
		String strTarget = "";
		if (!fDesk)
			strTarget = "add";
		else
			strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}

	/**
	 * used to insert data into database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	// ////////////this method is used to insert the patient
	// details//////////////////
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		saveToken(request);
		DeathNotificationTransFB formBean = (DeathNotificationTransFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		DeathNotificationTransDATA.insert(formBean);
		formBean.setStrCrNo("");
		String strTarget = "";
		if (!fDesk)
			strTarget = "add";
		else 
			return CANCELTODESK(mapping,form,request,response);
		return mapping.findForward(strTarget);
	}

	/**
	 * used to insert data into database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	// ///////////////This method is used to call the menu
	// page///////////////////////
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	/**
	 * used to insert data into database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	// ///////////////This method is used to check the patient wthether male or
	// female in case of pregnancy///////////////////////
	public ActionForward IsPregnant(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		DeathNotificationTransFB formBean = (DeathNotificationTransFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		DeathNotificationTransDATA.isPregnant(formBean);
		return null;
	}

	public ActionForward ADDNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		fDesk = true;
		DeathNotificationTransFB formBean = (DeathNotificationTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		return GO(mapping, form, request, response);
	}

	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/ipd/transactions/NursingDeskFinalTransCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}

}
