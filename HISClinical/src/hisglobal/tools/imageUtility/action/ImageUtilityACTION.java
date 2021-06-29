package hisglobal.tools.imageUtility.action;

import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.tools.imageUtility.util.ImageUtilityUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ImageUtilityACTION extends DispatchAction{

	
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		
		 return  this.INIT(mapping,form,request,response);
	}
	
	public ActionForward INIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		
		/*ImageUtilityFB formBean=(ImageUtilityFB)form;
		ImageUtilityUTIL util=new ImageUtilityUTIL();
		
		util.populateFormbeanWithUserConfigxml(request,formBean);*/
		
		return null;
	}
	public ActionForward IMAGELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		System.out.println("INSIDE IMAGELIST");
		ImageUtilityFB formBean=(ImageUtilityFB)form;
		ImageUtilityUTIL util=new ImageUtilityUTIL();
		//util.populateImageList(request,formBean);
		
		//util.populateFormbeanWithUserConfigxml(request,formBean);
		
		return mapping.findForward("IMAGELIST");
	}
	public ActionForward VIDEOLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		
		System.out.println("INSIDE VIDEOLIST");
		ImageUtilityFB formBean=(ImageUtilityFB)form;
		ImageUtilityUTIL util=new ImageUtilityUTIL();
		
		//util.populateVideoList(request,formBean);
		//util.populateFormbeanWithUserConfigxml(request,formBean);
		
		return mapping.findForward("VIDEOLIST");
	}

	
}
