/**
##		Date					: 13.02.2019
##		Reason	(CR/PRS)		: SINGLE PAGE DISCHARGE NEW PROCESS 
##		Created By				: Vasu
*/

package ehr.ot.presentation;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.allergies.presentation.EHRSection_AllergiesFB;
import ehr.allergies.presentation.EHRSection_AllergiesUTL;
import ehr.ot.presentation.EHRSection_OTDetailsFB;
import ehr.ot.presentation.EHRSection_OTDetailsUTL;

public class EHRSection_OTDetailsACT extends CSRFGardTokenAction
{
		
	public ActionForward PATCLINICALDOC_ENC_OT_DTL_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_OTDetailsFB fb = (EHRSection_OTDetailsFB) form;
		fb.reset(mapping, request);
		EHRSection_OTDetailsUTL.getOTEssentials(fb, request);
		return mapping.findForward("OT");
		
	}
	
	public ActionForward PATCLINICALDOC_ENC_OT_DTL_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		//generateToken(request);
		EHRSection_OTDetailsFB fb = (EHRSection_OTDetailsFB) form;
		//fb.reset(mapping, request);
		if(EHRSection_OTDetailsUTL.saveOperationDetails(request,response,fb))
		{
			EHRSection_OTDetailsUTL.getOTEssentials(fb, request);
		}
		return null;
		//return mapping.findForward("OT");
		
	}
	
	//Added by Vasu on 30.July.2019
	public ActionForward DELETE_PREV_PAT_OPERATIONS_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		EHRSection_OTDetailsFB fb = (EHRSection_OTDetailsFB) form;
		EHRSection_OTDetailsUTL.deletePreviousOperations(fb, request,response);
		return null;
	}
	
}