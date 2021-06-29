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


public class FeeCollectionDetailsRptCNT extends CSRFGardTokenAction{

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

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		FeeCollectionDetailsRptDATA.initReportPage(formBean,request);
		//FeeCollectionDetailsRptDATA.getCounterDetails(formBean,request,response);
		//FeeCollectionDetailsRptDATA.getClerkDetails(formBean,request,response);
		//FeeCollectionDetailsRptDATA.getDeptDetails(formBean,request,response);
		//FeeCollectionDetailsRptDATA.getTariffDetailsCombo(formBean,request,response);
		
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

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		FeeCollectionDetailsRptDATA.getCounterDetails(formBean,request,response);
		
		return null;
	}
	
	
	public ActionForward CASHCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		FeeCollectionDetailsRptDATA.getClerkDetails(formBean,request,response);
		
		return null;
	}
	
	
	public ActionForward DEEPTCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		FeeCollectionDetailsRptDATA.getDeptDetails(formBean,request,response);
		
		return null;
	}
	public ActionForward GROUPCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		FeeCollectionDetailsRptDATA.getGroupDetails(formBean,request,response);
		
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

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		FeeCollectionDetailsRptDATA.getTariffDetails(formBean,request,response);
		
		return null;
	}
	
	public ActionForward TARIFFMULTISELCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		FeeCollectionDetailsRptDATA.getTariffMulSelComboDetails(formBean,request,response);
		
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

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		FeeCollectionDetailsRptDATA.showReport(formBean, request, response);
		
		
	}
	
	
	public ActionForward HOSPSERVICECMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		FeeCollectionDetailsRptFB formBean = (FeeCollectionDetailsRptFB) form;
		//formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
		FeeCollectionDetailsRptDATA.getHospitalServiceDetails(formBean,request, response);
		
		return null;
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