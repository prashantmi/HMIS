package ehr.ImageExam;

/**
 * @author  CDAC
 */


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.tools.tag.GenericTemplateTag;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import ehr.EHRConfig;
import ehr.questionnaire.presentation.EHRSection_QuestionnaireFB;
import ehr.questionnaire.vo.EHRSection_QuestionnaireVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class EHRSection_ImageExamACT extends CSRFGardTokenAction
{
	
	// the default action called when a page is loaded for the first time
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.PATCLINICALDOC_PAT_IMG_EXAM_SELECT(mapping, form, request, response);
	}

	// Setting Essentials for New Page
	public ActionForward PATCLINICALDOC_PAT_IMG_EXAM_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		//OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//fb.reset(mapping, request);
		//OpdImageExamTabUTIL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward AJAX_GETIMAGELIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//fb.reset(mapping, request);
		EHRSection_ImageExamUTL.AJAX_GETIMAGELIST(fb, request,response);
		return null;
	}

	// Starting Image Editor
	/*public ActionForward GETEDITOR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		EHRSection_ImageExamUTL.getEditorEssentials(fb, request,response);
		//OpdImageExamTabUTIL.createEntryForImage(fb, request);
		return mapping.findForward("NEW");
	}*/

	// Saving Patient Image Exam
	public ActionForward SAVEIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PrintWriter out = response.getWriter();
	    response.setContentType("text/plain");
	    EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
	    StringBuffer jb = new StringBuffer();
	    String line = null;
	    //String image64=null;
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	        jb.append(line);

	    String img64 = jb.toString(); 
	    String res[] = img64.split("%");
	           img64 = res[0];
	          // System.out.println(img64.l);
	           fb.setImageName(res[2]);
	           fb.setRemark(res[1]);
	           //image64=res[3];
	    //check if the image is really a base64 png, maybe a bit hard-coded
	    /*if(img64 != null && img64.startsWith("data:image/png;base64,")){*/
	       if(img64 != null && (img64.startsWith("data:image/png;base64,")||img64.startsWith("data:image/jpeg;base64,")||img64.startsWith("data:image/jpg;base64,"))){
	        //Remove Content-type declaration
	        img64 = img64.substring(img64.indexOf(',') + 1);            
	    }else{
	        response.setStatus(403);
	        out.print("Format of image is not correct!");
	    }   
		//ObjectMapper mapper = new ObjectMapper();
		
 		//String data = request.getParameter("data");
		
	       EHRSection_ImageExamUTL.SAVEIMAGE(img64,fb,request,response);
        
        
      return null; 
	}

	// Cancelling The Image Save Process
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.PATCLINICALDOC_PAT_IMG_EXAM_SELECT(mapping, form, request, response);
		
		/*OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		Status status = new Status();
		ControllerUTIL.setSysdate(request);
		status.add(Status.TRANSINPROCESS);
		OpdImageExamTabUTIL.removeEntryForImage(fb, request);
		WebUTIL.setStatus(request, status);
		fb.setHmode("NEW");
		return mapping.findForward("NEW");*/
	}

	/*// Starting Image Editor with Old Image to Edit
	public ActionForward GETOLDEDITOR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.getSetOldEditorEssentials(fb, request);
		return mapping.findForward("NEW");
	}*/

	// Modifying OPD Patient Image
	/*public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		
		if(OpdImageExamTabUTIL.modifyOpdPatientImage(fb, request))
		{
			OpdImageExamTabUTIL.setEssentials(fb, request);
			Status status = new Status();
			status.add(Status.TRANSINPROCESS, "Image Saved Successfully", "");
			WebUTIL.setStatus(request, status);
		}
		return mapping.findForward("NEW");
	}*/

	// View Image
/*	public ActionForward VIEWIMAGELOG(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.setImageLog(fb, request,response);
		return mapping.findForward("VIEW");
	}*/

	// View Previous Image
	public ActionForward AJAX_GETPREVIMGEXM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		EHRSection_ImageExamUTL.AJAX_GETPREVIMGEXM(fb, request,response);
		return null;
	}
	
	public ActionForward Image(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		EHRSection_ImageExamUTL.Image(fb, request,response);
		return null;
	}

/*	// View Next Image
	public ActionForward VIEWNEXTLOG(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.setNextImageLog(fb, request);
		return mapping.findForward("VIEW");
	}*/
	
	public ActionForward AJAX_GETOTHERIMAGELIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//fb.reset(mapping, request);
		EHRSection_ImageExamUTL.AJAX_GETOTHERIMAGELIST(fb, request,response);
		return null;
	}
	
	public ActionForward GetOtherImageSrc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//fb.reset(mapping, request);
		EHRSection_ImageExamUTL.GetOtherImageSrc(fb, request,response);
		return null;
	}
	
	public ActionForward AJAX_REVOKEIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//fb.reset(mapping, request);
		EHRSection_ImageExamUTL.AJAX_REVOKEIMAGE(fb, request,response);
		//EHRSection_ImageExamUTL.AJAX_GETPREVIMGEXM(fb, request,response);
		return null;
	}
	
	public ActionForward AJAX_GETIMAGEEDITOR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_ImageExamFB fb = (EHRSection_ImageExamFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//fb.reset(mapping, request);
		//EHRSection_ImageExamUTL.AJAX_REVOKEIMAGE(fb, request,response);
		//EHRSection_ImageExamUTL.AJAX_GETPREVIMGEXM(fb, request,response);
		return mapping.findForward("ImgEditor");
	}
	
	
}
