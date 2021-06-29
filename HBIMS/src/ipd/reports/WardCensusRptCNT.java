package ipd.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class WardCensusRptCNT extends DispatchAction {
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.DispatchAction#unspecified
	 * (org.apache.struts.action.ActionMapping, 
	 * org.apache.struts.action.ActionForm, 
	 * javax.servlet.http.HttpServletRequest,
	 *  javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		WardCensusRptFB formBean = (WardCensusRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		WardCensusRptDATA.initReportPage(formBean,request);
		
				String target = "reportPage";
				
			return mapping.findForward(target);
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws HisException
	 * @throws SQLException
	 */
/////////////////////This Method is used for showing report/////////////////////////////
	
	public void SHOWRPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		WardCensusRptFB formBean = (WardCensusRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		WardCensusRptDATA.showReport(formBean, request, response);
		
		
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
/////////////////////This Method is used for DeptCombo Values/////////////////////////////
		
public ActionForward DEPTCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

	WardCensusRptFB formBean = (WardCensusRptFB) form;
		//formBean.setStrHospitalCode(request.getParameter("hospCode").toString());
	WardCensusRptDATA.getdeptComboDetails(formBean,request, response);
		
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
/////////////////////This Method is used for Unit Combo Values/////////////////////////////
	
	public ActionForward UNITCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		WardCensusRptFB formBean = (WardCensusRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		WardCensusRptDATA.getUnitWardDetails(formBean,request, response);
		
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
/////////////////////This Method is used for Ward Combo Values/////////////////////////////
	
	public ActionForward WARDCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		
		WardCensusRptFB formBean = (WardCensusRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		WardCensusRptDATA.getWardDetailsCombo(formBean,request, response);
		
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
/////////////////////This Method is used for Room Combo Values/////////////////////////////
	
	public ActionForward ROOMCMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		
		WardCensusRptFB formBean = (WardCensusRptFB) form;
		//formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		WardCensusRptDATA.getRoomDetails(formBean,request, response);
		
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
