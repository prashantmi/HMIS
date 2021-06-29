package new_investigation.masters.controller.action;

import java.io.IOException;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.ItemLabTestMappingMstFB;
import new_investigation.masters.controller.fb.ItemLabTestMappingMstFB;
import new_investigation.masters.controller.utl.FilmMstUTL;
import new_investigation.masters.controller.utl.ItemLabTestMappingMstUTL;
import new_investigation.masters.controller.utl.LabConsumableMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

		

	public class ItemLabTestMappingMstACT extends GenericController {

		String target = null;
		String message="";
		public ItemLabTestMappingMstACT()
		{

			super(new ItemLabTestMappingMstUTL(),"/masters/ItemLabTestMappingMstACT");

		}
	
 
		public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			WebUTIL.refreshTransState(request);
			ControllerUTIL.setSysdate(request);
			ItemLabTestMappingMstUTL.getEssentials(fb, request);
			fb.setEntry("manual");
			//FilmMstUTL.fetchFilmD(fb, request);
			return mapping.findForward("ADD");
		}

		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			ItemLabTestMappingMstUTL.SaveDetails(fb, request);
			fb.reset(mapping, request);
			
		   return mapping.findForward("ADD");
		}


		public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			ItemLabTestMappingMstUTL.fetchDetails(fb, request);
			ItemLabTestMappingMstUTL.getEssentials(fb, request);
			
			return mapping.findForward("ADD"); 
		}
	  
		public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			if(ItemLabTestMappingMstUTL.modifySaveDetails(fb, request)){
				
				 return this.LIST(mapping,fb, request, response);
			}
			fb.setHmode("MODIFY");
			
			return mapping.findForward("ADD"); 
		}

		
		public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			ItemLabTestMappingMstUTL.getEssentials(fb, request);
			ItemLabTestMappingMstUTL.reFetchDetails(fb, request);
			return mapping.findForward("ADD"); 


		}
		public ActionForward AJAX_LOT_DUPLICATE_CHECK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			StringBuffer strBuff=ItemLabTestMappingMstUTL.lotNoDuplicacyCheck(fb, request);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strBuff.toString());
			return null;
		}
		public ActionForward AJAX_ITEM_COMBO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException
		{
			ItemLabTestMappingMstFB fb = (ItemLabTestMappingMstFB) form;
			StringBuffer strBuff=ItemLabTestMappingMstUTL.getItemCombo(fb, request);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strBuff.toString());
			return null;
		}

}
