package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.TestMstFB;
import new_investigation.masters.controller.fb.filmMstFB;
import new_investigation.masters.controller.utl.FilmMstUTL;
import new_investigation.masters.controller.utl.TestMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

public class FilmMstACT extends GenericController {

	String target = null;
    String message="";
	public FilmMstACT()
	{

		super(new FilmMstUTL(),"/masters/filmMstACTION");

	}
 
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		filmMstFB fb = (filmMstFB) form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		
		FilmMstUTL.fetchFilmD(fb, request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		filmMstFB fb = (filmMstFB) form;
	    boolean hasFlag=  FilmMstUTL.saveFilm(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
		return mapping.findForward("ADD");
	}


	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		filmMstFB fb = (filmMstFB) form;
		WebUTIL.refreshTransState(request);
		FilmMstUTL.fetchFilm(fb, request);
		return mapping.findForward("ADD"); 
	}
  
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		filmMstFB fb = (filmMstFB) form;
		boolean hasFlag= FilmMstUTL.savemodifyFilm(fb, request);
		if(hasFlag){
			/*LIST(mapping, fb, request, response);
			return mapping.findForward("LIST");*/

			 return this.LIST(mapping,fb, request, response);
		}
		else
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD"); 
	}

	
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		filmMstFB fb = (filmMstFB) form;
		FilmMstUTL.reFetchFilm(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 


	}
	
	
}
