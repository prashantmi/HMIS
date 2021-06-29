package new_investigation.vo.template;

import hisglobal.vo.ValueObject;

public class HexaStringClass extends ValueObject {
	private String primaryID;
	private String firstLabel;
	private String secondLabel;
	private String thirdLabel;
	private String fourthLabel;
	private String fifthLabel;
	public String getPrimaryID() {
		return primaryID;
	}
	public void setPrimaryID(String primaryID) {
		this.primaryID = primaryID;
	}
	public String getFirstLabel() {
		return firstLabel;
	}
	public void setFirstLabel(String firstLabel) {
		this.firstLabel = firstLabel;
	}
	public String getSecondLabel() {
		return secondLabel;
	}
	public void setSecondLabel(String secondLabel) {
		this.secondLabel = secondLabel;
	}
	public String getThirdLabel() {
		return thirdLabel;
	}
	public void setThirdLabel(String thirdLabel) {
		this.thirdLabel = thirdLabel;
	}
	public String getFourthLabel() {
		return fourthLabel;
	}
	public void setFourthLabel(String fourthLabel) {
		this.fourthLabel = fourthLabel;
	}
	public String getFifthLabel() {
		return fifthLabel;
	}
	public void setFifthLabel(String fifthLabel) {
		this.fifthLabel = fifthLabel;
	}
	public HexaStringClass(String primaryID, String firstLabel,
			String secondLabel, String thirdLabel, String fourthLabel,
			String fifthLabel) {
		super();
		this.primaryID = primaryID;
		this.firstLabel = firstLabel;
		this.secondLabel = secondLabel;
		this.thirdLabel = thirdLabel;
		this.fourthLabel = fourthLabel;
		this.fifthLabel = fifthLabel;
	}
	public HexaStringClass() {
		super();
	}
	
	
	
}
