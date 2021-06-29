package hisglobal.vo;

public class OpinionMasterVO extends ValueObject 
{
	private String opinionCode;
	private String slNo;
	private String opinionName;
	private String opinionDesc;
	private String isActive;
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getOpinionCode() {
		return opinionCode;
	}
	public void setOpinionCode(String opinionCode) {
		this.opinionCode = opinionCode;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}
	public String getOpinionDesc() {
		return opinionDesc;
	}
	public void setOpinionDesc(String opinionDesc) {
		this.opinionDesc = opinionDesc;
	}
	
	
	
	
}
