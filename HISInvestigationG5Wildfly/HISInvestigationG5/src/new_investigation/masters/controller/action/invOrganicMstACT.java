package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.TestMstFB;
import new_investigation.masters.controller.fb.filmMstFB;
import new_investigation.masters.controller.fb.invOrganicMstFB;
import new_investigation.masters.controller.utl.FilmMstUTL;
import new_investigation.masters.controller.utl.TestMstUTL;
import new_investigation.masters.controller.utl.invOrganicMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;


public class invOrganicMstACT extends GenericController {


		String target = null;
	    String message="";
	    
		public invOrganicMstACT()
		{

			super(new invOrganicMstUTL(),"/masters/organicprocess");

		}
	 
		public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicMstFB fb = (invOrganicMstFB) form;
			WebUTIL.refreshTransState(request);
			ControllerUTIL.setSysdate(request);
			
			//invOrganicMstUTL.fetchOrgaicName(fb, request);
			return mapping.findForward("ADD");
		}

		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicMstFB fb = (invOrganicMstFB) form;
			
		    boolean hasFlag=  invOrganicMstUTL.saveOrganism(fb, request);
		     if(hasFlag){
		    	 fb.reset(mapping, request);
		     }
			return mapping.findForward("ADD");
		}


			public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
				invOrganicMstFB fb = (invOrganicMstFB) form;
			WebUTIL.refreshTransState(request);
			invOrganicMstUTL.fetchOrganism(fb, request);
			return mapping.findForward("ADD"); 
		}
	  
		public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicMstFB fb = (invOrganicMstFB) form;
			boolean hasFlag= invOrganicMstUTL.savemodifyOrganism(fb, request);
			if(hasFlag){
				/*LIST(mapping, fb, request, response);
				return mapping.findForward("LIST");
*/
				 return this.LIST(mapping,fb, request, response);
			}
			else
				fb.setHmode("MODIFY");
				return mapping.findForward("ADD"); 
		}

		
			public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
				invOrganicMstFB fb = (invOrganicMstFB) form;
				invOrganicMstUTL.reFetchOrganism(fb, request);
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD"); 


		}
		
		
	}
	
