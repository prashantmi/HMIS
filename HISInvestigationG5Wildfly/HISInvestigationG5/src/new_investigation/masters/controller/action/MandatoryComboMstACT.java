/***************************Start of program*****************************\
 ## Copyright Information				:	C-DAC, Noida  
 ## Project Name						:	MANDATORY COMBO MASTER
 ## Name of Developer		 			:	Puneet Singh Khurana
 ## Module Name							:	New Investigation
 ## Process/Database Object Name		:
 ## Purpose								:	This is used to define the mandatory combo values for mandatory Code
 											whose Mandatory Type is Combo i.e. 2
 ## Date of Creation					: 	13-Feb-2015
 ## Modification Log					:				
 ##		Modify Date						: 
 ##		Reason	(CR/PRS)				: 
 ##		Modify By						: 
/****************************************************************************/

package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.MandatoryComboMstFB;
import new_investigation.masters.controller.utl.MandatoryComboMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MandatoryComboMstACT extends GenericController
{

	String target = null;

	public MandatoryComboMstACT()
	{

		super(new MandatoryComboMstUTIL(),"/masters/MandatoryComboMstACTION");

	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryComboMstFB mandatorycombo_fb = (MandatoryComboMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		MandatoryComboMstUTIL.fetchMandatoryCombo(mandatorycombo_fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryComboMstFB mandatorycombo_fb = (MandatoryComboMstFB) form;
		boolean hasFlag=  MandatoryComboMstUTIL.saveMandatoryCombo(mandatorycombo_fb, request);
		if(hasFlag){
			
			
			mandatorycombo_fb.reset(mapping, request);
			mandatorycombo_fb.setHmode("ADD");

            return this.ADD(mapping, mandatorycombo_fb, request, response);

		}
		mandatorycombo_fb.setMandatoryValue(null);
		mandatorycombo_fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}



	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryComboMstFB mandatorycombo_fb = (MandatoryComboMstFB) form;
		WebUTIL.refreshTransState(request);
		MandatoryComboMstUTIL.fetchCheckListMandatoryCombo(mandatorycombo_fb, request);
		return mapping.findForward("ADD"); 
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryComboMstFB mandatorycombo_fb = (MandatoryComboMstFB) form;
		boolean hasFlag= MandatoryComboMstUTIL.savemodifyMandatoryCombo(mandatorycombo_fb, request);
		if(hasFlag){
            return this.LIST(mapping, mandatorycombo_fb, request, response);
         }
           else
           {
       		mandatorycombo_fb.setHmode("MODIFY");
       		return mapping.findForward("ADD");
           }
	}


	public ActionForward DISPLAYDATA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryComboMstFB mandatorycombo_fb = (MandatoryComboMstFB) form;
		String mandatoryCode=mandatorycombo_fb.getMandatoryCode();
		MandatoryComboMstUTIL.fetchdisplaydataMandatoryCombo(mandatorycombo_fb, request);
		mandatorycombo_fb.setMandatoryCode(mandatoryCode);
		mandatorycombo_fb.setMandatoryValue(null);
		mandatorycombo_fb.setHmode("ADD");
		return mapping.findForward("ADD"); 
	}


	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MandatoryComboMstFB testparacombo_fb = (MandatoryComboMstFB) form;
		MandatoryComboMstUTIL.reFetchCheckListMandatoryCombo(testparacombo_fb, request);
		testparacombo_fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}







}
