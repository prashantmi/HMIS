package ipd.transactions;

import ipd.IpdTransConfig;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatBelongingTransCNT extends DispatchAction {
	boolean fDesk = false;

	/**
	 * forwards control to the Page patientBelonging_ipdTrans.jsp
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
	 * forwards control to the Page patientBelonging_ipdTrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	/*
	 * This method is used to open the details of corresponding patient CR no
	 * which we enter in the screen
	 */

	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
		PatBelongingTransFB formBean = (PatBelongingTransFB) form;
		boolean retVal = false;
		formBean.setStrRemarksMandatoryFlag(IpdTransConfig.getMandatoryRemarksBelonging());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		// if(!fDesk){
		formBean.setStrAdmnNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
		retVal = PatBelongingTransDATA.patAdmStatus(formBean,request);
		// }else
		// retVal=true;
		if (retVal == true){
			formBean.setStrAdmnNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
			formBean.setStrDeptUnitCode(request.getParameterValues("combo")[1]);
			formBean.setStrWardCode(request.getParameterValues("combo")[2]);
			PatBelongingTransDATA.detail(formBean);
		}else
			formBean.setStrCrNo("");
		String strTarget = "";
		if (!fDesk)
			strTarget = "add";
		else
			strTarget = "addDesk";
		return mapping.findForward(strTarget);
	}

	/**
	 * This function is used to insert details into database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	// ////////This method is used to insert the Patient Belonging
	// Details///////////
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		saveToken(request);
		PatBelongingTransFB formBean = (PatBelongingTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		PatBelongingTransDATA.insert(formBean);
		formBean.setStrCrNo("");
		String strTarget = "";
		if (!fDesk)
			strTarget = "add";
		else {
			return CANCELTODESK(mapping,form,request,response);
		}
		return mapping.findForward(strTarget);
	}

	/**
	 * This function is used to update details into database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	// ////////This method is used to update the Patient Belonging
	// Details///////////
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		saveToken(request);
		PatBelongingTransFB formBean = (PatBelongingTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrAdmnNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
		formBean.setStrDeptUnitCode(request.getParameterValues("combo")[1]);
		formBean.setStrWardCode(request.getParameterValues("combo")[2]);
		PatBelongingTransDATA.update(formBean);
		formBean.setStrCrNo("");
		String strTarget = "";
		if (!fDesk)
			strTarget = "add";
		else {
			return CANCELTODESK(mapping,form,request,response);
		}
		return mapping.findForward(strTarget);
	}

	/**
	 * This function is used to update details into database
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	// ////////This method is used to go back the control to menu
	// page///////////
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	public ActionForward ADDNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		fDesk = true;
		PatBelongingTransFB formBean = (PatBelongingTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		formBean.setStrValue("0");
		return GO(mapping, form, request, response);
	}
	
	public ActionForward ADDRETURNNURSINGDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		fDesk = true;
		PatBelongingTransFB formBean = (PatBelongingTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@")
				.split("@")[0]);
		formBean.setStrValue("1");		
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
