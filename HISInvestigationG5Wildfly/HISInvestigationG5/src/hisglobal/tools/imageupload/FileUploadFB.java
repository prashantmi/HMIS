package hisglobal.tools.imageupload;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class FileUploadFB extends ActionForm{

	private FormFile uploadedFileName;	
	private String hmode;
	private String mapKey;
	private String primaryKey; 	
	private String screenType;
	
	
	
	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

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

	public void reset(ActionMapping mapping,HttpServletRequest request){
	
	}
	

}

