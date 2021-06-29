package hisglobal.utility.installSoftware;

import hisglobal.vo.ValueObject;

public class InstallSoftwareVO extends ValueObject{
	
	private String softwareId;
	private String serverOS;
	private String clientOS;
	private String filePath;
	private String softwareName;
	private String softwareDesc;
	
	
	public String getSoftwareId() {
		return softwareId;
	}
	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}
	public String getServerOS() {
		return serverOS;
	}
	public void setServerOS(String serverOS) {
		this.serverOS = serverOS;
	}
	public String getClientOS() {
		return clientOS;
	}
	public void setClientOS(String clientOS) {
		this.clientOS = clientOS;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public String getSoftwareDesc() {
		return softwareDesc;
	}
	public void setSoftwareDesc(String softwareDesc) {
		this.softwareDesc = softwareDesc;
	}
	
	
	/*
	 * getters and setters
	 * 
	 */
	
		

}
