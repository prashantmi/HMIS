package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.StockEntryOfBloodFB;
import inpatient.transaction.controller.utl.StockEntryOfBloodUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class StockEntryOfBloodACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		StockEntryOfBloodFB fb = (StockEntryOfBloodFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		StockEntryOfBloodUTL.setEssentials(fb, request);
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		StockEntryOfBloodFB fb = (StockEntryOfBloodFB) form;
		StockEntryOfBloodUTL.saveExternalBloodStockDtl(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
}
