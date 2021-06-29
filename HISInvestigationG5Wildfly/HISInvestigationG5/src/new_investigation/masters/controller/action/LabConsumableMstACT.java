package new_investigation.masters.controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.LabConfigratorMstFB;
import new_investigation.masters.controller.fb.TestMstFB;
import new_investigation.masters.controller.fb.LabConsumableMstFB;
import new_investigation.masters.controller.utl.FilmMstUTL;
import new_investigation.masters.controller.utl.LabConfigratorMstUTIL;
import new_investigation.masters.controller.utl.LabConsumableMstUTL;
import new_investigation.masters.controller.utl.TestMstUTL;
import new_investigation.transactions.controller.fb.SampleCollectionFB;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.masterutil.GenericController;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class LabConsumableMstACT extends GenericController
{

	String target = null;
    String message="";
	public LabConsumableMstACT()
	{

		super(new LabConsumableMstUTL(),"/masters/labConsumableMstACT");

	}
    
	
	   
		
		
 
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConsumableMstFB fb = (LabConsumableMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabConsumableMstUTL.getEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConsumableMstFB fb = (LabConsumableMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabConsumableMstUTL.saveDetails(fb, request);
		LabConsumableMstUTL.getEssentials(fb, request);
		//FilmMstUTL.fetchFilmD(fb, request);
		fb.reset(mapping, request);
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConsumableMstFB fb = (LabConsumableMstFB) form;
		WebUTIL.refreshTransState(request);
		LabConsumableMstUTL.fetchDetails(fb, request);
		LabConsumableMstUTL.getEssentials(fb, request);
		return this.ADD(mapping, form, request, response) ;
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConsumableMstFB fb = (LabConsumableMstFB) form;
		LabConsumableMstUTL.refetchDetails(fb, request);
		LabConsumableMstUTL.getEssentials(fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConsumableMstFB fb = (LabConsumableMstFB) form;
		if(LabConsumableMstUTL.saveModifyDetails(fb, request)){
			
			 return this.LIST(mapping,fb, request, response);
		}
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
			
	}
}
