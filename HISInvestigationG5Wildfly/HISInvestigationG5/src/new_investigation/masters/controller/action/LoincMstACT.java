package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.fb.LoincMstFB;
import new_investigation.masters.controller.utl.LoincMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class LoincMstACT extends GenericController {

	public LoincMstACT()
	{
		super(new LoincMstUTIL(),"/masters/LoincMstACT");
	}
	public String mode=null;
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LoincMstUTIL.fetchLoincMst(fb, request);
		//fb.setLoincTime(fb.getTestCode().split("#")[2]);
		mode="ADD";
		fb.setMode("ADD");
		return mapping.findForward("ADD");
	}
	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;	
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LoincMstUTIL.fetchTestPara(fb,request);
		fb.setLoincTime(fb.getTestCode().split("#")[2]);
		fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}
	/*public ActionForward GETUOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;	
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LoincMstUTIL.fetchUOM(fb,request);
		fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}*/
	public ActionForward GETLOINC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;	
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LoincMstUTIL.getLoinc(fb,request);
		fb.setMode(fb.getHmode());
		fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}
	public ActionForward GETSEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;	
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		LoincMstUTIL.getSearch(fb,request);
		fb.setMode("GETLOINC");
		if(mode=="MODIFY")
		{
			fb.setHmode("MODIFY");
			return mapping.findForward("MODIFY");	
		}
		else
		{
		fb.setHmode("ADD");
		return mapping.findForward("ADD");
		}
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;
	    boolean hasFlag=  LoincMstUTIL.saveLoinc(fb, request);
	     if(hasFlag){
	    	 fb.reset(mapping, request);
	     }
	     fb.setHmode("ADD");
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;	
		WebUTIL.refreshTransState(request);
		LoincMstUTIL.fetchModifyLoinc(fb,request);
		mode="MODIFY";
		return mapping.findForward("ADD");
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;	
		boolean hasFlag= LoincMstUTIL.saveModifyLoinc(fb, request);
		if(hasFlag){
	    	 fb.reset(mapping, request);
	    	 return this.LIST(mapping,fb, request, response);
	     }
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;
		LoincMstUTIL.reFetchLoinc(fb, request);
		fb.setHmode("MODIFY");
		return mapping.findForward("ADD"); 
	}
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		LoincMstFB fb=(LoincMstFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		fb.setLoincProperty(fb.getLoincProperty());
		fb.setMode(fb.getHmode());
		if(mode=="MODIFY")
		{
			fb.setHmode("MODIFY");
			return mapping.findForward("ADD");	
		}
		else
		{
			fb.setMode("GETLOINC");
		fb.setHmode("ADD");
		return mapping.findForward("ADD");
		}
	}
}
