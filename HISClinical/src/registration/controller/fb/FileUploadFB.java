package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class FileUploadFB extends ActionForm{
	
	private FormFile uploadedFileName;	
		
	public FormFile getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(FormFile uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request){
	
	}

}
