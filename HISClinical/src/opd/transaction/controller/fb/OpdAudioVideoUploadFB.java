package opd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class OpdAudioVideoUploadFB extends ActionForm {
	
	private String[] filePath;
	private String hmode;
	private String selectedFile;
	private String patCrNo;
	//private FormFile uploadedFileName;

	
	/*public FormFile getUploadedFileName() {
		return uploadedFileName;
	}
	public void setUploadedFileName(FormFile uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}*/
	public String getSelectedFile() {
		return selectedFile;
	}
	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}
	public String[] getFilePath() {
		return filePath;
	}
	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

}
