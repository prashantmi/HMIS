package ipd;

import hisglobal.exceptions.HisException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IpdCNT extends DispatchAction {

	/* Used To get The Blank Listing Poup*/
	
	public ActionForward PATIENTLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String target = "patlist";
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setUsrArg(request.getParameter("patList"));
		formBean.setUsrFuncName(request.getParameter("usrFuncName"));
		formBean.setGblCRValue(request.getParameter("gblCRValue"));
		return mapping.findForward(target);
	}
	
	/* Used To get the Data(HLP) */
	
	public ActionForward FETCHPATIENTLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{

		IpdFB formBean = (IpdFB) form;		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.getPatientListingDtls(request, response, formBean);
		
		return null;
	}
	/* Used To get the Data(HLP) in Admission Modification Process To Show List On Admission Modification Page */
	public ActionForward FETCHPATIENTLISTINGMODIFICATION(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{

		IpdFB formBean = (IpdFB) form;		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.getPatientListingDtlsModification(request, response, formBean);
		
		return null;
	}
	
	public ActionForward FETCHPATIENTLISTINGMODIFICATIONBS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{

		IpdFB formBean = (IpdFB) form;		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		IpdDATA.getPatientListingDtlsModification_BS(request, response, formBean);
		
		return null;
	}
	public ActionForward  BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
             
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.beddetail(formBean,request,response);	
		String strtarget = "bedstatus";
	    return mapping.findForward(strtarget);
	}
	public ActionForward  BEDSTATUS1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
             
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.beddetail1(formBean,request,response);	
		String strtarget = "bedstatus";
	   return mapping.findForward(strtarget);
	}
	public ActionForward  BEDSTATUSIPD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
             
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.beddetailIPD(formBean,request,response);	
		String strtarget = "bedstatus";
	    return mapping.findForward(strtarget);
	}
	public ActionForward BEDDETAILS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws HisException,SQLException
	{
		IpdFB formBean = (IpdFB) form;
	//	System.out.println("in global CNT..BEDDETAILS");
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.beddetail(formBean,request,response);
		return null;
	}
	/* Called from Patient Admission Transaction*/
	public ActionForward BEDDETAILSPATADMISSION(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws HisException,SQLException
	{
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.beddetailPatAdmission(formBean,request,response);
		return null;
	}
	public ActionForward BEDPROPERTIES(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	throws HisException,SQLException
	{
		IpdFB formBean = (IpdFB) form;
	//	System.out.println("in global CNT..BEDDETAILS");
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.bedProperties(formBean,request,response);
		return null;
	}
	public ActionForward  WARDSTATISTICS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
             
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.wardStatistics(formBean,request,response);	
		String strtarget = "wardstatistics";
	    return mapping.findForward(strtarget);
	}
	
	public ActionForward  BEDSTATUSDASHBOARD(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		IpdFB formBean = (IpdFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.bedstatusdasboard(formBean,request,response);
		String strtarget = "bedstatusdash";
	    return mapping.findForward(strtarget);
	}
	
	public ActionForward  BEDSTATUSPATADM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
        
		IpdFB formBean = (IpdFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.beddetail2(formBean,request,response);	
		String strtarget = "bedstatus";
		return mapping.findForward(strtarget);
	}
	
	public ActionForward FETCHPATIENTLISTINGBS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{

		IpdFB formBean = (IpdFB) form;		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.getPatientListingDtls_BS(request, response, formBean);
		
		return null;
	}
	
	public ActionForward JOBTRACKING(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		IpdFB formBean = (IpdFB) form;		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdDATA.jobtracking(request, response, formBean);
		
		return null;
	}	
	
}
