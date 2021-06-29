package registration.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class commonTransactionFB extends ActionForm{
	
	String mode=null;
	String uploadedFileName;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}	
	public void reset(ActionMapping mapping,HttpServletRequest request){		
		this.setMode("");
		this.setUploadedFileName("");		
	}	
	public String getUploadedFileName() {
		return uploadedFileName;
	}
	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}
	
	
}
