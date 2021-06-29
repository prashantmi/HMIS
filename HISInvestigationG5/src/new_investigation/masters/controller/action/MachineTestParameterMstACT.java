
package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.MachineTestParameterMstFB;
import new_investigation.masters.controller.fb.MandatoryComboMstFB;
import new_investigation.masters.controller.fb.TestParaComboMstFB;
import new_investigation.masters.controller.utl.MachineTestParameterMstUTIL;
import new_investigation.masters.controller.utl.MandatoryComboMstUTIL;
import new_investigation.masters.controller.utl.TestParaComboMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MachineTestParameterMstACT extends GenericController
{

	String target = null;

	public MachineTestParameterMstACT()
	{

		super(new MachineTestParameterMstUTIL(),"/masters/MachineTestParameterMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MachineTestParameterMstFB parameterMachineCombo_fb = (MachineTestParameterMstFB) form;
		MachineTestParameterMstUTIL.fetchParaMachineCombo(parameterMachineCombo_fb, request);
		MachineTestParameterMstUTIL.fetchParaTestCombo(parameterMachineCombo_fb, request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETPARAMETER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MachineTestParameterMstFB parameterMachineCombo_fb = (MachineTestParameterMstFB) form;
		MachineTestParameterMstUTIL.displayDetails(parameterMachineCombo_fb, request);
		MachineTestParameterMstUTIL.displayParameterDetails(parameterMachineCombo_fb, request);

					return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MachineTestParameterMstFB _fb = (MachineTestParameterMstFB) form;
		MachineTestParameterMstUTIL.saveDetails(_fb, request);

		_fb.setMachineCode("-1");
		_fb.setTestCode("-1");
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MachineTestParameterMstFB _fb = (MachineTestParameterMstFB) form;
		WebUTIL.refreshTransState(request);
		MachineTestParameterMstUTIL.fetchModifyDetails(_fb, request);
			return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MachineTestParameterMstFB _fb = (MachineTestParameterMstFB) form;
		boolean hasFlag= MachineTestParameterMstUTIL.savemodifyMachineTestParaCombo(_fb, request);
		if(hasFlag){
            return this.LIST(mapping, _fb, request, response);
         }
           else
           {
       		
		_fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
           }
	}
	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MachineTestParameterMstFB machinetestparacombo_fb = (MachineTestParameterMstFB) form;
		MachineTestParameterMstUTIL.reFetchCheckMachineListTestParaCombo(machinetestparacombo_fb, request);
		machinetestparacombo_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}

	}
	

