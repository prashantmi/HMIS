package mortuary.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.masters.controller.hlp.MortuaryMasterFB;
import mortuary.masters.controller.hlp.MortuaryMasterUTL;
import mrd.masters.controller.fb.RackMstFB;
import mrd.masters.controller.util.RackMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MortuaryMasterACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		MortuaryMasterFB fb=(MortuaryMasterFB)form;
		fb.reset(mapping, request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		fb.setMortuaryType(MortuaryConfig.MORTUARY_TYPE_NORMAL);
		WebUTIL.refreshTransState(request);
		MortuaryMasterUTL.getEssentialForMortuaryMst(fb,request); 
		//MortuaryMasterUTL.getEmployeeListBasedOnDept(fb, request);
		return mapping.findForward("NEW");
	}
	
	//Getting the List of Employee on the Basis of Department
	public ActionForward EMP_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryMasterFB fb=(MortuaryMasterFB)form;
		MortuaryMasterUTL.getEmployeeListBasedOnDept(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	//Getting the List of Block on the Basis of Building
	/*public ActionForward BLOCK_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryMasterFB fb=(MortuaryMasterFB)form;
		MortuaryMasterUTL.getBlockList(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Getting the List of Floor on the Basis of Block
	/*public ActionForward FLOOR_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryMasterFB fb=(MortuaryMasterFB)form;
		MortuaryMasterUTL.getFloorList(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Getting the List of Room on the Basis of Floor
	/*public ActionForward ROOM_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MortuaryMasterFB fb=(MortuaryMasterFB)form;
		MortuaryMasterUTL.getRoomList(fb, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}*/
	
	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MortuaryMasterFB fb=(MortuaryMasterFB)form;
		boolean exist=MortuaryMasterUTL.saveMortuaryMaster(fb,request);
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
		MortuaryMasterFB fb = (MortuaryMasterFB) form;
		WebUTIL.refreshTransState(request); 
		
		MortuaryMasterUTL.getDataForModify(fb,request);
		MortuaryMasterUTL.getEssentialForMortuaryMst(fb,request);
		MortuaryMasterUTL.getEmployeeListBasedOnDept(fb, request);
		/*MortuaryMasterUTL.getBlockList(fb, request); 
		MortuaryMasterUTL.getFloorList(fb, request);
		MortuaryMasterUTL.getRoomList(fb, request);*/
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		MortuaryMasterFB _fb = (MortuaryMasterFB) form;
		flag=MortuaryMasterUTL.saveModMortuaryMaster(_fb, _request);
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
		MortuaryMasterFB fb = (MortuaryMasterFB) form;
		WebUTIL.refreshTransState(request);
		MortuaryMasterUTL.getDataForModify(fb,request);
		MortuaryMasterUTL.getEssentialForMortuaryMst(fb,request);
		MortuaryMasterUTL.getEmployeeListBasedOnDept(fb, request);
		MortuaryMasterUTL.getBlockList(fb, request);
		MortuaryMasterUTL.getFloorList(fb, request);
		MortuaryMasterUTL.getRoomList(fb, request);
		return mapping.findForward("NEW");
	}

}
