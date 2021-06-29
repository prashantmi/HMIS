package ipd.transactions;
/*
 * Patient Attendance ACTION
 * 
 * author: Debashis Sardar
 * 
 * dated: 16/02/2012 
 */
import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientAttendanceBSCNT extends CSRFGardTokenAction{
	public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
    
	  public static  String now(String frmt)
	    {
	      HisUtil util=null;
	      String a="";
	      util=new HisUtil("transaction","PatientAttendanceCNT");
	      try{
	       a= util.getASDate(frmt);
	      }
	      catch(Exception e){
	    	
	      }
	      return a;
	    }

	  /**
		 * action initially called 
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return ActionForward object with target
		 * @throws HisException
		 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			return this.GO(mapping, form, request, response);
	}
	
	/**
	 * action called to forward control to the Page PatientAttendance.jsp
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	generateToken(request);
		PatientAttendanceFB formBean = (PatientAttendanceFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAttendanceDATA.getServiceType(formBean,request,response);
		return mapping.findForward("PatientAttendance");
		
	}
	
	/**
	 * action called to get service name combo
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return 
	 * @throws HisException
	 */
	public ActionForward GETSERVICENAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
		PatientAttendanceFB formBean = (PatientAttendanceFB)form;
		formBean.setStrErrMsgString("");
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String serviceTypeId=request.getParameter("serviceTypeId");
		formBean.setStrServiceTypeId(serviceTypeId);
		PatientAttendanceDATA.getServiceName(formBean,request,response);
		
			return null;
		
	}
	
	/**
	 * action to get patient details 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * 
	 */
	public ActionForward GETPATIENTLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
			
		PatientAttendanceFB fb = (PatientAttendanceFB)form;	
		fb.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PatientAttendanceDATA.department(fb);
		PatientAttendanceDATA.getPatientList_BS(request,fb,response);
		return null;
	}
	
	/**
	 * action to get Unit Combo
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * 
	 */
	public ActionForward UNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientAttendanceFB formBean = (PatientAttendanceFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		PatientAttendanceDATA.unit(formBean, request, response); 
		
		return null;
	}
	
	/**
	 * action to get Ward Combo
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * 
	 */
	public ActionForward WARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientAttendanceFB formBean = (PatientAttendanceFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		PatientAttendanceDATA.ward(formBean, request, response); 
																																	
		return null;
	}
	
	/**
	 * action to get Room Combo
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * 
	 */
	public ActionForward ROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PatientAttendanceFB formBean = (PatientAttendanceFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		PatientAttendanceDATA.room(formBean, request, response); 
																																	
		return null;
	}
	
	/**
	 * action to accept patient 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @throws Exception 
	 * 
	 */
	public ActionForward ACCEPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
			//validateToken(request, response);
		PatientAttendanceFB fb = (PatientAttendanceFB)form;	
		fb.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
	   PatientAttendanceDATA.accept(request,fb,response);
		
		return null;
	}
	
	/**
	 * action to reject patient 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @throws Exception 
	 * 
	 */
	public ActionForward REJECT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
			
		PatientAttendanceFB fb = (PatientAttendanceFB)form;	
		fb.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		PatientAttendanceDATA.reject(request,fb,response);		
		return null;
	}
	
	/**
	 * action to transfer patient back to ADT
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward object with target
	 * @throws Exception 
	 */
	public ActionForward TRANSFER(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PatientAttendanceFB formBean = (PatientAttendanceFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		PatientAttendanceDATA.transfer(formBean, request, response); 
		
		return this.unspecified(mapping, form, request, response);
	}
	
	/**
	 * action called to when Clear button is clicked
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward object with target
	 */
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PatientAttendanceFB formBean = (PatientAttendanceFB) form;
		formBean.reset(mapping,request);
		if(formBean.getStrAcceptedFlag().equals("1"))
			formBean.setStrErrMsgString("Patient(s) Accepted Successfully."); // displaying save message after saving successfully using ACCEPT mode
		if(formBean.getStrAcceptedFlag().equals("2"))
			formBean.setStrErrMsgString("Patient(s) Rejected Successfully.");
		if(formBean.getStrAcceptedFlag().equals("3"))
				formBean.setStrErrMsgString("Exception While Saving Data !!! ");
				
	   return this.unspecified(mapping, form, request, response);
	}
	
	/**
	 * action called to when Clear button is clicked for Attendance View Process
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward object with target
	 */
	public ActionForward CLEARVIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PatientAttendanceFB formBean = (PatientAttendanceFB) form;
		formBean.reset(mapping,request);		
	   return this.VIEW(mapping, form, request, response);
	}
	
	/**
	 * action called to forward control to the Page PatientAttendanceView.jsp for View Process
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		PatientAttendanceFB formBean = (PatientAttendanceFB)form;
		formBean.setStrCtDate(now(DATE_FORMAT_NOW));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAttendanceDATA.getServiceType(formBean,request,response);
		
			return mapping.findForward("PatientAttendanceView");
		
	}
	
	/**
	 * action called to show patient list for View Process
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return 
	 */
	public ActionForward PATIENTSTATUSVIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
			
		PatientAttendanceFB fb = (PatientAttendanceFB)form;	
		fb.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		fb.setStrSeatId(request.getSession().getAttribute("SEATID")	.toString());
		PatientAttendanceDATA.getPatientStatusView_BS(request,fb,response);		
		return null;
	}
	
	/**
	 * action mainly called to when Cancel button is clicked
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to HIS welcome page
	 * @throws Exception 
	 */
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
    }	
	
	//Anonymous 
	//no need of formBean For Simpler ajax pulls.
	public ActionForward GETBLKHRS(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res)
	{
		PatientAttendanceDATA.getBedBlkHrs(req,res);
		return null;
	}
}
