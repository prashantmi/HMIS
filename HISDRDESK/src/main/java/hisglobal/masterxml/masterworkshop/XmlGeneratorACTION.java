package hisglobal.masterxml.masterworkshop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class XmlGeneratorACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, Action form, HttpServletRequest request,
			HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, Action form, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("inside new:::");
		return mapping.findForward("NEW");
	}

	/*
	 * public ActionForward (ActionMapping mapping,Action form,HttpServletRequest request,HttpServletResponse response){
	 * System.out.println("inside new:::"); return mapping.findForward("NEW"); }
	 */
}
