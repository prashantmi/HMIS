package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.ImageUploadFB;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ImageUploadACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		Status objStatus = new Status();
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		ImageUploadFB fb = (ImageUploadFB) form;
		//String []temp;
		//String strFileExt="";
		Status objStatus = new Status();
		FormFile myfile = fb.getUploadImageName();
		byte[] fileDataAsByte = null;
		String base64Image=null;

		try
		{
			// fb.setTransactionMode(fb.getHmode());
			fileDataAsByte = myfile.getFileData();
			StringBuilder sb = new StringBuilder();
			sb.append("data:image/png;base64,");
			sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(fileDataAsByte, false)));
			base64Image=sb.toString();
			//System.out.println(base64Image);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		WebUTIL.setAttributeInSession(request, OpdConfig.UPLOADED_IMAGE_AS_ARRAY, fileDataAsByte);
		WebUTIL.setAttributeInSession(request, OpdConfig.UPLOADED_IMAGE_NAME, myfile.getFileName());
		WebUTIL.setAttributeInSession(request, "Base64Image", base64Image);
		
		objStatus.add(Status.DONE);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("CLOSE");
	}
	
	public ActionForward NewImageExam(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		Status objStatus = new Status();
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEWIMAGE");
	}
	
	public ActionForward SAVEImageUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
				ImageUploadFB fb = (ImageUploadFB) form;
				//String []temp;
				//String strFileExt="";
				Status objStatus = new Status();
				String myfile = fb.getUploadedFile();
				byte[] fileDataAsByte = null;
				String base64Image=null;
				
				try{
				//String abc=myfile.toString();
					//String str = FileUtils.readFileToString(myfile, "UTF-8");
					// fb.setTransactionMode(fb.getHmode());
					//fileDataAsByte = myfile.getBytes();
					//StringBuilder sb = new StringBuilder();
					//sb.append("data:image/png;base64,");
					//sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(myfile, false)));
					//base64Image=sb.toString();
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				WebUTIL.setAttributeInSession(request, OpdConfig.UPLOADED_IMAGE_AS_ARRAY, fileDataAsByte);
				//WebUTIL.setAttributeInSession(request, OpdConfig.UPLOADED_IMAGE_NAME, myfile.getFileName());
				WebUTIL.setAttributeInSession(request, "Base64Image", myfile);
				
				objStatus.add(Status.DONE);
				WebUTIL.setStatus(request, objStatus);
				return null;
	}
	
}
