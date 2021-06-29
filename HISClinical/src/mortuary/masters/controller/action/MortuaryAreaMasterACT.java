package mortuary.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.masters.controller.hlp.MortuaryAreaMasterFB;
import mortuary.masters.controller.hlp.MortuaryAreaMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MortuaryAreaMasterACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		MortuaryAreaMasterFB fb=(MortuaryAreaMasterFB)form;
		
		fb.reset(mapping, request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		fb.setMortuaryCode(fb.getControls()[0]);
		WebUTIL.refreshTransState(request);
		MortuaryAreaMasterUTL.getEssentialForMortuaryAreaMst(fb,request); 
		return mapping.findForward("NEW");
	}
	
	//Getting the List of Employee on the Basis of Department
/*	public ActionForward EMP_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryAreaMasterFB fb=(MortuaryAreaMasterFB)form;
		MortuaryAreaMasterUTL.getEmployeeListBasedOnDept(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Getting the List of Block on the Basis of Building
	/*public ActionForward BLOCK_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryAreaMasterFB fb=(MortuaryAreaMasterFB)form;
		MortuaryAreaMasterUTL.getBlockList(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Getting the List of Floor on the Basis of Block
	/*public ActionForward FLOOR_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryAreaMasterFB fb=(MortuaryAreaMasterFB)form;
		MortuaryAreaMasterUTL.getFloorList(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Getting the List of Room on the Basis of Floor
	/*public ActionForward ROOM_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryAreaMasterFB fb=(MortuaryAreaMasterFB)form;
		MortuaryAreaMasterUTL.getRoomList(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MortuaryAreaMasterFB fb=(MortuaryAreaMasterFB)form;
		boolean exist=MortuaryAreaMasterUTL.saveMortuaryAreaMaster(fb,request);
		if(exist)
		{
			fb.setHmode(fb.getTempMode());
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		generateToken(request);
		MortuaryAreaMasterFB fb = (MortuaryAreaMasterFB) form;
		WebUTIL.refreshTransState(request); 
		fb.setTempMode(fb.getHmode());
		MortuaryAreaMasterUTL.getDataForModify(fb,request);
		MortuaryAreaMasterUTL.getEssentialForMortuaryAreaMst(fb,request);
		//MortuaryAreaMasterUTL.getEmployeeListBasedOnDept(fb, request);
		/*MortuaryAreaMasterUTL.getBlockList(fb, request);
		MortuaryAreaMasterUTL.getFloorList(fb, request);
		MortuaryAreaMasterUTL.getRoomList(fb, request);*/
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		MortuaryAreaMasterFB _fb = (MortuaryAreaMasterFB) form;
		_fb.setTempMode(_fb.getHmode());
		flag=MortuaryAreaMasterUTL.saveModMortuaryAreaMaster(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("NEW");}
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MortuaryAreaMasterFB fb = (MortuaryAreaMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.setTempMode(fb.getHmode());
		MortuaryAreaMasterUTL.getDataForModify(fb,request);
		MortuaryAreaMasterUTL.getEssentialForMortuaryAreaMst(fb,request);
		//MortuaryAreaMasterUTL.getEmployeeListBasedOnDept(fb, request);
		/*MortuaryAreaMasterUTL.getBlockList(fb, request);
		MortuaryAreaMasterUTL.getFloorList(fb, request);
		MortuaryAreaMasterUTL.getRoomList(fb, request);*/
		return mapping.findForward("NEW");
	}

}
