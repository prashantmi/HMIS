package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.LabConfigratorMstFB;
import new_investigation.masters.controller.utl.LabConfigratorMstUTIL;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class LabConfigratorMstACT extends GenericController {
	
	public LabConfigratorMstACT()
	{

		super(new LabConfigratorMstUTIL(),"/masters/LabConfigratorMstACT");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConfigratorMstFB fb = (LabConfigratorMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabConfigratorMstUTIL.fetchLabConfigrator(fb, request);
		fb.setLabType("1");
		fb.setSampleNumberConfig("1");
		fb.setLabNumberConfig("1");
		fb.setNumberofTests("9999");

		fb.setLabWorkingDays("1111111"); //for weekdays
		return mapping.findForward("ADD");
	}

	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConfigratorMstFB fb = (LabConfigratorMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LabConfigratorMstUTIL.populate(fb, request);
		fb.setHmode("ADD");

		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConfigratorMstFB fb = (LabConfigratorMstFB) form;
		boolean hasFlag=LabConfigratorMstUTIL.saveLabConfigrator(fb, request);
		if(hasFlag){
			fb.reset(mapping, request);
		}
		//LabConfigratorMstUTIL.fetchLabConfigrator(fb, request);
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConfigratorMstFB fb = (LabConfigratorMstFB) form;
		WebUTIL.refreshTransState(request);
		LabConfigratorMstUTIL.fetchModifyLabConfigrator(fb, request);
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConfigratorMstFB fb = (LabConfigratorMstFB) form;
		boolean hasFlag= LabConfigratorMstUTIL.saveModifyLabConfigrator(fb, request);
		if(hasFlag){
            return this.LIST(mapping, fb, request, response);
		}
		else
			return mapping.findForward("MODIFY"); 
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LabConfigratorMstFB fb = (LabConfigratorMstFB) form;
		LabConfigratorMstUTIL.reFetchModify(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
}
