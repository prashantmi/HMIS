package new_opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_opd.transaction.controller.fb.SearchemrActionFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import HisWeb.dao.SearchEMRUtilDao;



public class SearchemrAction extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		//MainClass.getPatinetEHRDtls("", "", "", "", "", "", "");
		SearchemrActionFB formBean = (SearchemrActionFB) form;
		
		
		formBean.setStrDeptValues(SearchEMRUtilDao.getDeptDtl(request));
		formBean.setStrSearchParaValues(SearchEMRUtilDao.getSearchParaDtl(request));
		return mapping.findForward("INITIAL");
	}

}
