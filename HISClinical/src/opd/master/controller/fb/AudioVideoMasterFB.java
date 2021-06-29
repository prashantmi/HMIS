package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AudioVideoMasterFB extends ActionForm
{
	private String hmode;
	private String audioVideoName;
	private String ext;
	private String chk;
	private String fileName;
	private String fileCode;
	private String tempMode;
	private String uploadFileName;
	private String fileHeader;
	private String isDefault;
	private String slNo;
	
	public String getFileHeader() {
		return fileHeader;
	}
	public void setFileHeader(String fileHeader) {
		this.fileHeader = fileHeader;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getAudioVideoName() {
		return audioVideoName;
	}
	public void setAudioVideoName(String audioVideoName) {
		this.audioVideoName = audioVideoName;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileCode() {
		return fileCode;
	}
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request )
	{
		this.setFileCode("");
		this.setFileName("");
		this.setAudioVideoName("");
		this.setUploadFileName("");
		this.setChk("");
		this.setTempMode("");
		this.fileHeader="";
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
	
}
