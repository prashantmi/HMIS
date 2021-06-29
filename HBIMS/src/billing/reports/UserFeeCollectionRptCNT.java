package billing.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


public class UserFeeCollectionRptCNT extends CSRFGardTokenAction {
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified
	 * (org.apache.struts.action.ActionMapping,
	 *  org.apache.struts.action.ActionForm, 
	 *  javax.servlet.http.HttpServletRequest, 
	 *  javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
   {
		generateToken(request);

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		UserFeeCollectionRptDATA.initReportPage(formBean,request);
		//UserFeeCollectionRptDATA.getCounterDetails(formBean,request,response);
		//UserFeeCollectionRptDATA.getClerkDetails(formBean,request,response);
	//	UserFeeCollectionRptDATA.getDeptDetails(formBean,request,response);
		
				String target = "reportPage";
				
			return mapping.findForward(target);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
//////////////////This method is used for Group details values/////////////////////
	public ActionForward CNTCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		UserFeeCollectionRptDATA.getCounterDetails(formBean,request,response);
		
		return null;
	}
	
	
	public ActionForward CASHCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		UserFeeCollectionRptDATA.getClerkDetails(formBean,request,response);
		
		return null;
	}
	
	
	public ActionForward DEEPTCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		UserFeeCollectionRptDATA.getDeptDetails(formBean,request,response);
		
		return null;
	}
	public ActionForward GROUPCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		UserFeeCollectionRptDATA.getGroupDetails(formBean,request,response);
		
		return null;
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
//////////////////This method is used for Tariff details values/////////////////////
	
	public ActionForward TARIFFCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		UserFeeCollectionRptDATA.getTariffDetails(formBean,request,response);
		
		return null;
	}
	
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		UserFeeCollectionRptFB formBean = (UserFeeCollectionRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		UserFeeCollectionRptDATA.showReport(formBean, request, response);
		
		
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
