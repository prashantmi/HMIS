package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddImageUploadMasterFB extends ActionForm
{
	private String transactionMode;
	private String imageCode;
	private String imageName;
	private String fileName;
	private String ext;
	private String chk;
	private String uploadImageName;
	private String hmode;
	private String isDefault;
	private String slNo;
	private String isActive;
	private String isValid;
	
	/*Added by Saurabh on 6-sept-2017 for getting height and width of an image*/
	private String imageHeight;
	private String imageWidth;
	//End Saurabh
	
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getUploadImageName() {
		return uploadImageName;
	}
	public void setUploadImageName(String uploadImageName) {
		this.uploadImageName = uploadImageName;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setImageCode("");
		this.setImageName("");
//		this.setFileName("");
		this.setTransactionMode("");
		this.setUploadImageName("");
		this.setExt("");
//		this.setHmode("");
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}
	
}
