package hisglobal.vo;

public class PostmortemExamDtlVO extends ValueObject
{
	private String postmortemId;
	private String slNo;
	private String TemplateId;
	private String paraValue;
	private String paraId;
	
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getTemplateId() {
		return TemplateId;
	}
	public void setTemplateId(String templateId) {
		TemplateId = templateId;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getParaId() {
		return paraId;
	}
	public void setParaId(String paraId) {
		this.paraId = paraId;
	}
	
	
}
