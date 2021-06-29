package uploadfilesonserver;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class BloodGroupingUploadFB extends ActionForm{

	
	private String hmode;
	private FormFile uploadedFileName;
	private String crno;
	private String index;

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public FormFile getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(FormFile uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCrno() {
		return crno;
	}

	public void setCrno(String crno) {
		this.crno = crno;
	}
	
	
	
	
}
