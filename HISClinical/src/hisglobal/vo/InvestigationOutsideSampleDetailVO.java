package hisglobal.vo;

public class InvestigationOutsideSampleDetailVO extends ValueObject{
		String requisitionNo;
		String referredByHospital;
		String referredByHospitalCode;
		String noOfSamples;
		String seatID;
		public String getNoOfSamples() {
			return noOfSamples;
		}
		public void setNoOfSamples(String noOfSamples) {
			this.noOfSamples = noOfSamples;
		}
		public String getReferredByHospital() {
			return referredByHospital;
		}
		public void setReferredByHospital(String referredByHospital) {
			this.referredByHospital = referredByHospital;
		}
		public String getReferredByHospitalCode() {
			return referredByHospitalCode;
		}
		public void setReferredByHospitalCode(String referredByHospitalCode) {
			this.referredByHospitalCode = referredByHospitalCode;
		}
		public String getRequisitionNo() {
			return requisitionNo;
		}
		public void setRequisitionNo(String requisitionNo) {
			this.requisitionNo = requisitionNo;
		}
		public String getSeatID() {
			return seatID;
		}
		public void setSeatID(String seatID) {
			this.seatID = seatID;
		}

}
