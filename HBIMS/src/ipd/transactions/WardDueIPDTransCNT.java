/**
 * 
 */
package ipd.transactions;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author pankaj kumar
 *
 */
public class WardDueIPDTransCNT extends DispatchAction  {
	public ActionForward unspecified(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		WardDueIPDTransDATA.initDetail((WardDueIPDTransFB)_Form, _Request);
		return _Mapping.findForward("returnItem");
	}
	
	public ActionForward GETUNITCOMBO(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		WardDueIPDTransDATA.getUnitCombo((WardDueIPDTransFB)_Form, _Request, _Response);
		return null;
	}
	
	public ActionForward GETWARDCOMBO(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		WardDueIPDTransDATA.getWardCombo((WardDueIPDTransFB)_Form, _Request, _Response);
		return null;
	}
	
	public ActionForward GETPATIENTLIST(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		WardDueIPDTransDATA.getPatientList((WardDueIPDTransFB)_Form, _Request, _Response);
		return null;
	}
	
	public ActionForward GETPATIENTDUELIST(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		WardDueIPDTransDATA.getPatientDueList((WardDueIPDTransFB)_Form, _Request, _Response);
		return null;
	}

	public ActionForward SAVE(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		WardDueIPDTransDATA.save((WardDueIPDTransFB)_Form, _Request);
		WardDueIPDTransDATA.initDetail((WardDueIPDTransFB)_Form, _Request);
		return _Mapping.findForward("returnItem");
	}
	
	public ActionForward INITIALPAGE(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
}
