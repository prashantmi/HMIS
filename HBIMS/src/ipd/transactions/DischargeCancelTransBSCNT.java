package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DischargeCancelTransBSCNT extends CSRFGardTokenAction {
	/**
	 * forwards control to the Page visitorpassGeneration_ipdTrans.jsp
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
		generateToken(request);
		String target = "add";
		return mapping.findForward(target);
	}
	
	/**
	 * forwards control to the Page visitorpassGeneration_ipdTrans.jsp
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
		   generateToken(request);
		DischargeCancelTransFB formBean = (DischargeCancelTransFB) form;
		boolean retVal=false;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		retVal=DischargeCancelTransDATA.patStatusCode(formBean);		
		if(retVal==true){
		 DischargeCancelTransDATA.getAdmDetails_BS(request,formBean);
		 DischargeCancelTransDATA.getRsnRmk(formBean);
		 DischargeCancelTransDATA.detail(formBean);
		}
		 
			return this.unspecified(mapping, form, request, response);
	  }
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
			
		
		 DischargeCancelTransFB formBean = (DischargeCancelTransFB) form;
			formBean.setStrAdmnNo("");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
	}
	
	public ActionForward INITIALPAGE(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
            throws HisException{
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/hisglobal/initPage.jsp");
				acFwd.setContextRelative(true);
				return acFwd;
		}
	
	public ActionForward transOf_BS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException,Exception
	{
		DischargeCancelTransFB formBean = (DischargeCancelTransFB)form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String strValmode = (String) request.getParameter("modName");
		//DischargeCancelTransDATA.department(formBean);
		DischargeCancelTransDATA.initBedStatus(formBean,request);
		String sid = DischargeCancelTransDATA.AjaxResponse_BS(formBean,strValmode,request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(sid);
		return null;
	}

public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{		
	    DischargeCancelTransFB formBean = (DischargeCancelTransFB)form;
	    formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	    DischargeCancelTransDATA.room(formBean,request,response);		
		return null;
	}
	
	public ActionForward BED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{		
		DischargeCancelTransFB formBean = (DischargeCancelTransFB)form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DischargeCancelTransDATA.bed(formBean,request,response);		
		return null;
	}

	public ActionForward  BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
             
		DischargeCancelTransFB formBean = (DischargeCancelTransFB)form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DischargeCancelTransDATA.beddetail(formBean,request,response);
		    String strtarget = "bedstatus";
			return mapping.findForward(strtarget);
	}
	
	/**
	 * This function is used to insert details into database
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		DischargeCancelTransFB formBean = (DischargeCancelTransFB)form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DischargeCancelTransDATA.insert(formBean);
		formBean.setStrAdmnNo("");
		formBean.setStrCrNo("");
		String target = "add";
		return mapping.findForward(target);
	}
}
