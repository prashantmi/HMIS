
package opd.master.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.DeskMasterFB;
import opd.master.controller.util.AddModifyMenuToGlobalDeskMasterUTIL;

public class AddModifyMenuToGlobalDeskACTION extends CSRFGardTokenAction
{
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeskMasterFB fb = (DeskMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
	
		AddModifyMenuToGlobalDeskMasterUTIL.setDesktype(fb,request);//Function to get the value of desk type from list page to Add Page
		AddModifyMenuToGlobalDeskMasterUTIL.getisDefaultGlobal(fb,request);
		if(fb.getCheckForDefault().equals("1"))
		{
			fb.setHmode("DEFAULT");
			Status status = new Status();
			status.add(Status.CODE_RECORD_FOUND, "Default Global Desk Already exist");
			WebUTIL.setStatus(request, status);
			//return mapping.findForward("LIST");
		}
		else
		{	AddModifyMenuToGlobalDeskMasterUTIL.setEssential(fb,request);
		    //fb.setHmode("ADD");
		   
		}
		 return mapping.findForward("SAME");
		
	}
	
	public ActionForward ADDDESKDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMasterFB fb = (DeskMasterFB)form;

		AddModifyMenuToGlobalDeskMasterUTIL.setEssential(fb,request);
		
		fb.setHmode("ADDDESKDETAIL");
		return mapping.findForward("SAME");
	}

	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);

		DeskMasterFB fb = (DeskMasterFB)form;

		if(AddModifyMenuToGlobalDeskMasterUTIL.SaveMenuToGlobalDeskMaster(fb,request))
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping,request);
			return mapping.findForward("LIST");
		}
		else
		{
			
			return mapping.findForward("SAME");
		}
			
			
		/*{		
			WebUTIL.refreshTransState(request);
			fb.reset(mapping,request);
		
			AddModifyMenuToGlobalDeskMasterUTIL.setDesktype(fb,request);//Function to get the value of desk type from list page to Add Page
			AddModifyMenuToGlobalDeskMasterUTIL.getisDefaultGlobal(fb,request);// Function to check whether Is Default checkbox should be enabled or disabled
			AddModifyMenuToGlobalDeskMasterUTIL.setEssential(fb,request);
						
			Status status = new Status();
			status.add(Status.NEW, "Record Saved Successfully", "");
			WebUTIL.setStatus(request, status);
		}
		return mapping.findForward("SAME"); */
	}

	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeskMasterFB fb = (DeskMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);

		AddModifyMenuToGlobalDeskMasterUTIL.FetchMenusOfDesk(fb,request);
		
		fb.setHmode("MODIFY");
		return mapping.findForward("SAME");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeskMasterFB fb = (DeskMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);

		AddModifyMenuToGlobalDeskMasterUTIL.FetchMenusOfDesk(fb,request); 
		
		fb.setHmode("VIEW");
		return mapping.findForward("SAME");
	}

	public ActionForward LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		return mapping.findForward("LIST");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);

		DeskMasterFB fb = (DeskMasterFB)form;
		if(AddModifyMenuToGlobalDeskMasterUTIL.UpdateMenuToDeskMaster(fb,request))
		{
			fb.setHmode("MODIFYSAVE");
			return mapping.findForward("LIST");
		}
		else
		{
			fb.setHmode("MODIFY");
			return mapping.findForward("SAME");
		}
	}
	
	
	
}

