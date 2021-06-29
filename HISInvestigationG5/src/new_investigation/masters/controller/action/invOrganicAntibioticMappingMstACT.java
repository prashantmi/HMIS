package new_investigation.masters.controller.action;

import hisglobal.masterutil.GenericController;







import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.TestMstFB;
import new_investigation.masters.controller.fb.filmMstFB;
import new_investigation.masters.controller.fb.invOrganicAntibioticMappingMstFB;
import new_investigation.masters.controller.utl.FilmMstUTL;
import new_investigation.masters.controller.utl.GlobalLabCannedMstUTIL;
import new_investigation.masters.controller.utl.TestMstUTL;
import new_investigation.masters.controller.utl.invAntibioticMstUTL;
import new_investigation.masters.controller.utl.invOrganicAntibioticMappingMstUTL;
import new_investigation.masters.controller.utl.invOrganicMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;


public class invOrganicAntibioticMappingMstACT extends GenericController {


		String target = null;
	    String message="";
	    
		public invOrganicAntibioticMappingMstACT()
		{

			super(new invOrganicAntibioticMappingMstUTL(),"/masters/organicantibioticprocess");

		}
	 
		
		
		
		public ActionForward GETLABCANNED(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicAntibioticMappingMstFB labcanned_fb = (invOrganicAntibioticMappingMstFB) form;
			invOrganicAntibioticMappingMstUTL.getGlobalLabCanned(labcanned_fb, request);
			labcanned_fb.setHmode("GETLABCANNED");
			return mapping.findForward("ADD");
		}
		
		
		
		public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicAntibioticMappingMstFB fb = (invOrganicAntibioticMappingMstFB) form;
			WebUTIL.refreshTransState(request);
			ControllerUTIL.setSysdate(request);
			
			invOrganicAntibioticMappingMstUTL.fetchOrganicAntibioticD(fb, request);
			return mapping.findForward("ADD");
		}

		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicAntibioticMappingMstFB fb = (invOrganicAntibioticMappingMstFB) form;
			
		    boolean hasFlag=  invOrganicAntibioticMappingMstUTL.saveOrganicAntibiotic(fb, request);
		     if(hasFlag){
		    	 fb.reset(mapping, request);
		     }
			return mapping.findForward("ADD");
		}


		public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicAntibioticMappingMstFB labcanned_fb = (invOrganicAntibioticMappingMstFB) form;
			WebUTIL.refreshTransState(request);
			invOrganicAntibioticMappingMstUTL.fetchOrganicAntibiotic(labcanned_fb, request);
			return mapping.findForward("ADD"); 
		}
		
	  
		public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			invOrganicAntibioticMappingMstFB labcanned_fb = (invOrganicAntibioticMappingMstFB) form;
			boolean hasFlag= invOrganicAntibioticMappingMstUTL.saveOrganicAntibiotic(labcanned_fb, request);
			if(hasFlag){

	            return this.LIST(mapping, labcanned_fb, request, response);
	         }
	           else
	           {
	       				labcanned_fb.setHmode("MODIFY");
	       				return mapping.findForward("ADD");
	           }
			}

		
			public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
				invOrganicAntibioticMappingMstFB fb = (invOrganicAntibioticMappingMstFB) form;
				invOrganicAntibioticMappingMstUTL.reFetchOrganicAntibiotic(fb, request);
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD"); 


		}
		
				
	}
	
