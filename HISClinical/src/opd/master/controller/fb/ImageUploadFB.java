package opd.master.controller.fb;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ImageUploadFB extends ActionForm
{
	private FormFile uploadImageName;
	private String hmode;
	private String transactionMode;
	private String imageName;
	private String uploadedFile;
	
	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public FormFile getUploadImageName() 
	{
		return uploadImageName;
	}
	
	public void setUploadImageName(FormFile uploadImageName) 
	{
		this.uploadImageName = uploadImageName;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(String uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}
