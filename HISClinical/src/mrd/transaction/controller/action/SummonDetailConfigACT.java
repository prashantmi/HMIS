package mrd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SummonDetailConfigACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//SummonDetailConfigFB fb=(SummonDetailConfigFB)form;
		if(MrdConfig.MANUAL_SUMMON_DETAIL_FLAG.equals(MrdConfig.ENABLE))
		{
			return mapping.findForward("DESK");
		}
		else
		{
			return mapping.findForward("SUMMONPROCESS");
		}
		
	}
}
