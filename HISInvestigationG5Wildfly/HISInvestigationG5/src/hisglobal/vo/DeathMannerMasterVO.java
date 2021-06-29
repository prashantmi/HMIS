package hisglobal.vo;

public class DeathMannerMasterVO extends ValueObject
{
	private String deathMannerCode;
	private String deathMannerName;
	private String deathMannerDesc;
	private String isPostMortemReq;
	private String isAccidental;
	
	
	public String getDeathMannerCode() {
		return deathMannerCode;
	}
	public void setDeathMannerCode(String deathMannerCode) {
		this.deathMannerCode = deathMannerCode;
	}
	public String getDeathMannerName() {
		return deathMannerName;
	}
	public void setDeathMannerName(String deathMannerName) {
		this.deathMannerName = deathMannerName;
	}
	public String getDeathMannerDesc() {
		return deathMannerDesc;
	}
	public void setDeathMannerDesc(String deathMannerDesc) {
		this.deathMannerDesc = deathMannerDesc;
	}
	public String getIsPostMortemReq() {
		return isPostMortemReq;
	}
	public void setIsPostMortemReq(String isPostMortemReq) {
		this.isPostMortemReq = isPostMortemReq;
	}
	public String getIsAccidental() {
		return isAccidental;
	}
	public void setIsAccidental(String isAccidental) {
		this.isAccidental = isAccidental;
	}
}
