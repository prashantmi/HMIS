package hisglobal.vo;

public class MbLocationEligiblityMstVO extends ValueObject{

	private String certificateTypeID;
	private String slNo;
	private String districtId;
	
	
	public String getCertificateTypeID() {
		return certificateTypeID;
	}
	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
		
	
}
