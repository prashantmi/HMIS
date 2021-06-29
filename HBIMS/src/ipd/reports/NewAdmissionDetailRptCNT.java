
	package ipd.reports;
/*
 * author : Debashis sardar
 * 
 * New Admission Detail User Wise ACTION
 * 
 * date: 30/12/2011
 */
	import hisglobal.exceptions.HisException;
	import java.sql.SQLException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	import org.apache.struts.actions.DispatchAction;

	public class NewAdmissionDetailRptCNT extends DispatchAction {
		
		/** (non-Javadoc)
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
	     
			NewAdmissionDetailRptFB formBean = (NewAdmissionDetailRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			NewAdmissionDetailRptDATA.initReportPage(formBean);
			
					String target = "reportPage";
					
				return mapping.findForward(target);
		}
		
		/** 
		 * To show Report page
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @throws HisException
		 * @throws SQLException
		 */
		public void SHOWRPT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			NewAdmissionDetailRptFB formBean = (NewAdmissionDetailRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			NewAdmissionDetailRptDATA.showReport(formBean, request, response);
			
			
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
		public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		{
		    ActionForward acFwd = new ActionForward();
			acFwd.setPath("/hisglobal/initPage.jsp");
			acFwd.setContextRelative(true);
			return acFwd;
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
		public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException 
	   {
	     
			NewAdmissionDetailRptFB formBean = (NewAdmissionDetailRptFB) form;
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			NewAdmissionDetailRptDATA.initReportPage(formBean);
			
					String target = "reportPage";
					
				return mapping.findForward(target);
		}

	}


