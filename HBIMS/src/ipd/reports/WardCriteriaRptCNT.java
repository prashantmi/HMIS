package ipd.reports;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

	
public class WardCriteriaRptCNT extends DispatchAction {
		
		/* (non-Javadoc)
		 * @see org.apache.struts.actions.DispatchAction#unspecified
		 * (org.apache.struts.action.ActionMapping, 
		 * org.apache.struts.action.ActionForm, 
		 * javax.servlet.http.HttpServletRequest, 
		 * javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

					String target = "reportPage";
				return mapping.findForward(target);
		}
		
		public ActionForward SHOWRPT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			WardCriteriaRptFB formBean = (WardCriteriaRptFB) form;
			formBean.setStrHospitalCode("101"); 
			WardCriteriaRptDATA.showReport(formBean, request, response);
			
			return null;
		}
	}

