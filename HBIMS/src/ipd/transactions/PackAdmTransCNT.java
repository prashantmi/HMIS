package ipd.transactions;

import hisglobal.exceptions.HisException;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PackAdmTransCNT extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
	 	PackAdmTransFB formBean = (PackAdmTransFB)form;
	 	IpdConfigUtil util = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	 	//formBean.setStrAdmissionMode(util.getStrAdmissionOnline());
		saveToken(request);
		String target = "pckadm";
		
		return mapping.findForward(target);
	}
	
	/**
	 * forwards control to the Page patientAdmission_add_ipdTrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward GOPatient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
	 	PackAdmTransFB formBean = (PackAdmTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PackAdmTransDATA.initPatientAdmission(formBean);
		
		
		/*
		 * If Patient is not admitted the screen would not open 
		 */
		if(!formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()))
		{
			
			formBean.setStrMsgString("Patient is not admitted");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
		}
		/*
		 * If Patient is died the screen would not open 
		 */
		
		//else if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode()))
		else if(formBean.getStrIsDead().equals("1"))
		{
			formBean.setStrMsgString("Patient is DEAD!!!");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}
		
		/*
		 * If advice is expired died the screen would not open 
		 */
		
		/*else if(formBean.getStrAdviceStatus().equals("0"))
		{
			formBean.setStrMsgString("Either Patient Advice is not Generated Or Expired");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}*/
		else
		{
			PackAdmTransDATA.admitlist(formBean, request, response);
			return this.unspecified(mapping, formBean, request, response);
		}
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		PackAdmTransFB formBean = (PackAdmTransFB) form;
		formBean.setStrCrNo("");
		formBean.setStrMsgString("");
		formBean.setStrMsg("");
		formBean.setStrWarningMsg("");
		/*formBean.setStrPatientCrNo("");
		formBean.setStrSaveFlag("");*/
		return this.unspecified(mapping, form, request, response);
	}
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		saveToken(request);
		PackAdmTransFB formBean = (PackAdmTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PackAdmTransDATA.insert(formBean,request);
		formBean.setStrCrNo("");
		return this.unspecified(mapping, form, request, response);
	}
    /*public ActionForward PACKADM(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException
	{
		
    	PackAdmTransFB formBean = (PackAdmTransFB) form;
		formBean.setStrCrNo(request.getParameter("chk").replace("$", "@").split("@")[0]);
		formBean.setStrAdmNo(request.getParameter("chk").replace("$", "@").split("@")[1]);
		if(request.getParameter("chk").replace("$", "@").split("@").length>=4)
		//formBean.setStrhmoveno(request.getParameter("chk").replace("$", "@").split("@")[3]);
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		PackAdmTransDATA.admitlist(formBean, request, response);

		//return CANCELTODESK(mapping,form,request,response);
		String target = "notreported";
		return mapping.findForward(target);
	}*/

}
