package hisglobal.vo;

public class PostmortemOpinionDetailVO extends ValueObject
{
	private String postmortemId;
	private String opinionCode;
	private String opinionName;
	private String opinion;
	private String entryDate;
	private String isValid;
	
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getOpinionCode() {
		return opinionCode;
	}
	public void setOpinionCode(String opinionCode) {
		this.opinionCode = opinionCode;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}
}
