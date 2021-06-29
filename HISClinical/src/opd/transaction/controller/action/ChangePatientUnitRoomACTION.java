
package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.ChangePatientUnitRoomFB;
import opd.transaction.controller.util.ChangePatientUnitRoomUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChangePatientUnitRoomACTION extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		ChangePatientUnitRoomFB fb = (ChangePatientUnitRoomFB) form;
		fb.reset(mapping,request);
		
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ChangePatientUnitRoomUTIL.getEssentials(fb,request);		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		System.out.println("inside SAVE");
		 ChangePatientUnitRoomFB fb = (ChangePatientUnitRoomFB) form;
		 ChangePatientUnitRoomUTIL.changePatientRoom(fb,request);	
		 return mapping.findForward("NEW");			
	}
}

