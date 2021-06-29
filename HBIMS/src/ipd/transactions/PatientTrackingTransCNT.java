package ipd.transactions;

import hisglobal.exceptions.HisException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientTrackingTransCNT extends DispatchAction 
{
	/**
	 * forwards control to the Page PatientTracking_ipdTrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		String target = "add";
		PatientTrackingTransFB formBean = (PatientTrackingTransFB) form;
		formBean.setStrCase("1");//Admission No Wise
		return mapping.findForward(target);
	}
	
	/**
	 * forwards control to the Page PatientTracking_ipdTrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatientTrackingTransFB formBean = (PatientTrackingTransFB) form;
		boolean retVal=true;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		if(formBean.getStrCase().equals("1"))//Admission No Wise
			retVal=PatientTrackingTransDATA.patStatusCode(formBean);
		if(retVal==true)
		{
			if(formBean.getStrCase().equals("2"))//CR No Wise
				PatientTrackingTransDATA.admissionList(formBean);
			else
				PatientTrackingTransDATA.detail(formBean);			
		}
		String target = "add";
		return mapping.findForward(target);
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
			PatientTrackingTransFB formBean = (PatientTrackingTransFB) form;
			formBean.setStrAdmnNo("");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
	}
	
	public ActionForward GETMOVDETAILS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		PatientTrackingTransFB formBean = (PatientTrackingTransFB) form;
		boolean retVal=true;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		if(request.getParameter("admNo")!=null)
		{
			formBean.setStrAdmnNo(request.getParameter("admNo"));
			retVal=PatientTrackingTransDATA.patStatusCode(formBean);
			if(retVal==true)
			{
				PatientTrackingTransDATA.movDetails(formBean,request,response);			
			}
		}
		return null;
	}
	public ActionForward INITIALPAGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws HisException
	{
				ActionForward acFwd = new ActionForward();
				acFwd.setPath("/hisglobal/initPage.jsp");
				acFwd.setContextRelative(true);
				return acFwd;
	}
}