/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:30-MAR-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import new_investigation.masters.controller.fb.BookMarkMstFB;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.masters.controller.utl.BookMarkMstUTIL;
import new_investigation.masters.controller.utl.LabTestSampleGlobalMstUTIL;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class BookMarkMstACT extends GenericController {

	
	public BookMarkMstACT()
	{

		super(new BookMarkMstUTIL(),"/masters/BookMarkMstACT");

	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BookMarkMstFB fb = (BookMarkMstFB) form;		
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		fb.setBookmarkType("1");
		return mapping.findForward("ADD");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BookMarkMstFB fb = (BookMarkMstFB) form;
	    boolean hasFlag=  BookMarkMstUTIL.saveBookMark(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	     fb.setHmode("ADD");
	     fb.setBookmarkName("");
	     fb.setBookmarkType("1");
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BookMarkMstFB fb = (BookMarkMstFB) form;		
		WebUTIL.refreshTransState(request);
		
		BookMarkMstUTIL.fetchModifyBookMark(fb, request);
		return mapping.findForward("ADD"); 
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BookMarkMstFB fb = (BookMarkMstFB) form;
		boolean hasFlag= BookMarkMstUTIL.saveModifyBookMark(fb, request);
		if(hasFlag){
	    	 fb.reset(mapping, request);
	    	 return this.LIST(mapping,fb, request, response);
	     }
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BookMarkMstFB fb = (BookMarkMstFB) form;
		BookMarkMstUTIL.reFetchCheckListMacro(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
}
