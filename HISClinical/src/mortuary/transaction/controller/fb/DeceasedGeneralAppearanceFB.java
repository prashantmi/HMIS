package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeceasedGeneralAppearanceFB extends ActionForm
{
	private String hmode;
	private String patCrNo;
	private String deceasedNo;
	private String postmortemId;
	private String srNo;
	private String complexion;
	private String hairColorLength;
	private String eyeColor;
//	private String length1;
//	private String length2;
	private String clothDetails;
//	private String weight1;
//	private String weight2;
	private String bodyLooks;
	private String pupilRight;
	private String pupilLeft;
	private String corneaRight;
	private String corneaLeft;
	private String cynosis;
	private String lividity;
	private String rigorMortis;
	private String orifices;
	private String mouthOdour;
	private String isDecomposition;
	private String gaExistingFlag;
	private String length;
	private String weight;
	
	//Search
	private String searchFName;
	private String searchMName;
	private String searchLName;
	private String deathDate;
	private String selectedDeceasedNo;
	
	private String imageFilePath;
	
	
	
	public String getSelectedDeceasedNo() {
		return selectedDeceasedNo;
	}
	public void setSelectedDeceasedNo(String selectedDeceasedNo) {
		this.selectedDeceasedNo = selectedDeceasedNo;
	}
	public String getGaExistingFlag() {
		return gaExistingFlag;
	}
	public void setGaExistingFlag(String gaExistingFlag) {
		this.gaExistingFlag = gaExistingFlag;
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
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
	public String getHairColorLength() {
		return hairColorLength;
	}
	public void setHairColorLength(String hairColorLength) {
		this.hairColorLength = hairColorLength;
	}
	public String getEyeColor() {
		return eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
public String getClothDetails() {
		return clothDetails;
	}
	public void setClothDetails(String clothDetails) {
		this.clothDetails = clothDetails;
	}
	public String getBodyLooks() {
		return bodyLooks;
	}
	public void setBodyLooks(String bodyLooks) {
		this.bodyLooks = bodyLooks;
	}
	public String getPupilRight() {
		return pupilRight;
	}
	public void setPupilRight(String pupilRight) {
		this.pupilRight = pupilRight;
	}
	public String getPupilLeft() {
		return pupilLeft;
	}
	public void setPupilLeft(String pupilLeft) {
		this.pupilLeft = pupilLeft;
	}
	public String getCorneaRight() {
		return corneaRight;
	}
	public void setCorneaRight(String corneaRight) {
		this.corneaRight = corneaRight;
	}
	public String getCorneaLeft() {
		return corneaLeft;
	}
	public void setCorneaLeft(String corneaLeft) {
		this.corneaLeft = corneaLeft;
	}
	public String getCynosis() {
		return cynosis;
	}
	public void setCynosis(String cynosis) {
		this.cynosis = cynosis;
	}
	public String getLividity() {
		return lividity;
	}
	public void setLividity(String lividity) {
		this.lividity = lividity;
	}
	public String getRigorMortis() {
		return rigorMortis;
	}
	public void setRigorMortis(String rigorMortis) {
		this.rigorMortis = rigorMortis;
	}
	public String getOrifices() {
		return orifices;
	}
	public void setOrifices(String orifices) {
		this.orifices = orifices;
	}
	public String getMouthOdour() {
		return mouthOdour;
	}
	public void setMouthOdour(String mouthOdour) {
		this.mouthOdour = mouthOdour;
	}
	public String getIsDecomposition() {
		return isDecomposition;
	}
	public void setIsDecomposition(String isDecomposition) {
		this.isDecomposition = isDecomposition;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setIsDecomposition("");
		this.setComplexion("");
		this.setHairColorLength("");
		this.setEyeColor("");
		this.setLength("");
		//this.setLength2("");
		this.setWeight("");
	//	this.setWeight2("");
		this.setBodyLooks("");
		this.setCynosis("");
		this.setPupilLeft("");
		this.setPupilRight("");
		this.setCorneaLeft("");
		this.setCorneaRight("");
		this.setLividity("");
		this.setRigorMortis("");
		this.setOrifices("");
		this.setMouthOdour("");
		this.setClothDetails("");
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getSearchFName() {
		return searchFName;
	}
	public void setSearchFName(String searchFName) {
		this.searchFName = searchFName;
	}
	public String getSearchMName() {
		return searchMName;
	}
	public void setSearchMName(String searchMName) {
		this.searchMName = searchMName;
	}
	public String getSearchLName() {
		return searchLName;
	}
	public void setSearchLName(String searchLName) {
		this.searchLName = searchLName;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
}
