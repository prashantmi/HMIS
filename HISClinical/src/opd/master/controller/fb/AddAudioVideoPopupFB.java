package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class AddAudioVideoPopupFB extends ActionForm
{
	private FormFile fileName;
	private String transactionMode;
	
	
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	public FormFile getFileName() {
		return fileName;
	}
	public void setFileName(FormFile fileName) {
		this.fileName = fileName;
	}
	

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		
	}
}
