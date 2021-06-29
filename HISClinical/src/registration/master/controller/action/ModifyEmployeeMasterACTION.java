package registration.master.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import registration.RegistrationConfig;
import registration.master.controller.fb.EmployeeMasterFB;
import registration.master.controller.util.EmployeeMstUTIL;

public class ModifyEmployeeMasterACTION extends DispatchAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		 System.out.println("inside new of ModifydeptMasterACTION");
		 EmployeeMasterFB fb = (EmployeeMasterFB) form;	 
		 
		 WebUTIL.refreshTransState(request);
		 EmployeeMstUTIL.modifyEmployeeMasterEssentials(request,fb);
		 return mapping.findForward("NEW");
	}	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("inside SAVE");
		 EmployeeMasterFB fb = (EmployeeMasterFB) form;	 
		 if(EmployeeMstUTIL.updateEmployeeMaster(fb, request))
			 return mapping.findForward("LIST");	
		 else
		 {
			 fb.setChkFlag("1");
			 return mapping.findForward("NEW");
		 }
		
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		 return mapping.findForward("LIST");		
	}
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		 return mapping.findForward("LIST");		
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EmployeeMasterFB fb = (EmployeeMasterFB) form;	 
		//fb.reset(mapping, request);		
		WebUTIL.setAttributeInSession(request,RegistrationConfig.VIEWORMODIFY,"modify");
		return mapping.findForward("NEW");		
	}

}
