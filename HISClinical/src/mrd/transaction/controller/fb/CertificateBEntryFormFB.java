package mrd.transaction.controller.fb;


	import registration.controller.fb.CRNoFB;
	import javax.servlet.http.HttpServletRequest;
	import org.apache.struts.action.ActionMapping;

	public class CertificateBEntryFormFB  extends CRNoFB {
		
		private String reqNo;
		private String certificateId;
		private String reqType;
		private String patName;
		private String reqStatus;
		private String patGenderCode;
		private String empNo;
		private String patDOB;
		private String patientAddress;
		private String patContactNo;
		private String billNo;
		private String recordType;
		private String handoverTo;
		private String recordDesc;
		private String reqReason;
		private String handoverToName;
		private String seatId;
		private String handoverToRel;
		private String requestBy;
		private String hospitsalCode;
		private String requestByName;
		private String handoverToAddress;
		private String requestedByRel;
		private String requestedByAddress;
		private String handoverToContact;
		private String entryDate;
		private String requestedByContact;
		
		private String relationship;
		private String approvedBy;
		private String remarks;
		private String hmode;
		private String selectRecord;
		private String patAge;
		private String quantity;
		private String mode;

		private String patAgeUnit;
		private int currentPage=1;
		private String hospitalCode;
		
		public String getHospitalCode() {
			return hospitalCode;
		}

		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}

		
		public CertificateBEntryFormFB()
		{
			this.currentPage=1;
		}
		
		public void reset(ActionMapping mapping, HttpServletRequest request)
		{
			this.setPatCrNo("");
			this.setReqType("0");
		}

		public String getReqNo() {
			return reqNo;
		}
		public void setReqNo(String reqNo) {
			this.reqNo = reqNo;
		}
		public String getCertificateId() {
			return certificateId;
		}
		public void setCertificateId(String certificateId) {
			this.certificateId = certificateId;
		}
		public String getReqType() {
			return reqType;
		}
		public void setReqType(String reqType) {
			this.reqType = reqType;
		}
		public String getPatName() {
			return patName;
		}
		public void setPatName(String patName) {
			this.patName = patName;
		}
		public String getReqStatus() {
			return reqStatus;
		}
		public void setReqStatus(String reqStatus) {
			this.reqStatus = reqStatus;
		}
		public String getPatGenderCode() {
			return patGenderCode;
		}
		public void setPatGenderCode(String patGenderCode) {
			this.patGenderCode = patGenderCode;
		}
		public String getEmpNo() {
			return empNo;
		}
		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}
		public String getPatDOB() {
			return patDOB;
		}
		public void setPatDOB(String patDOB) {
			this.patDOB = patDOB;
		}
		public String getPatientAddress() {
			return patientAddress;
		}
		public void setPatientAddress(String patientAddress) {
			this.patientAddress = patientAddress;
		}
		public String getPatContactNo() {
			return patContactNo;
		}
		public void setPatContactNo(String patContactNo) {
			this.patContactNo = patContactNo;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getRecordType() {
			return recordType;
		}
		public void setRecordType(String recordType) {
			this.recordType = recordType;
		}
		public String getHandoverTo() {
			return handoverTo;
		}
		public void setHandoverTo(String handoverTo) {
			this.handoverTo = handoverTo;
		}
		public String getRecordDesc() {
			return recordDesc;
		}
		public void setRecordDesc(String recordDesc) {
			this.recordDesc = recordDesc;
		}
		public String getReqReason() {
			return reqReason;
		}
		public void setReqReason(String reqReason) {
			this.reqReason = reqReason;
		}
		public String getHandoverToName() {
			return handoverToName;
		}
		public void setHandoverToName(String handoverToName) {
			this.handoverToName = handoverToName;
		}
		public String getSeatId() {
			return seatId;
		}
		public void setSeatId(String seatId) {
			this.seatId = seatId;
		}
		public String getHandoverToRel() {
			return handoverToRel;
		}
		public void setHandoverToRel(String handoverToRel) {
			this.handoverToRel = handoverToRel;
		}
		public String getRequestBy() {
			return requestBy;
		}
		public void setRequestBy(String requestBy) {
			this.requestBy = requestBy;
		}
		public String getHospitsalCode() {
			return hospitsalCode;
		}
		public void setHospitsalCode(String hospitsalCode) {
			this.hospitsalCode = hospitsalCode;
		}
		public String getRequestByName() {
			return requestByName;
		}
		public void setRequestByName(String requestByName) {
			this.requestByName = requestByName;
		}
		public String getHandoverToAddress() {
			return handoverToAddress;
		}
		public void setHandoverToAddress(String handoverToAddress) {
			this.handoverToAddress = handoverToAddress;
		}
		public String getRequestedByRel() {
			return requestedByRel;
		}
		public void setRequestedByRel(String requestedByRel) {
			this.requestedByRel = requestedByRel;
		}
		public String getRequestedByAddress() {
			return requestedByAddress;
		}
		public void setRequestedByAddress(String requestedByAddress) {
			this.requestedByAddress = requestedByAddress;
		}
		public String getHandoverToContact() {
			return handoverToContact;
		}
		public void setHandoverToContact(String handoverToContact) {
			this.handoverToContact = handoverToContact;
		}
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		public String getRequestedByContact() {
			return requestedByContact;
		}
		public void setRequestedByContact(String requestedByContact) {
			this.requestedByContact = requestedByContact;
		}
		public String getRelationship() {
			return relationship;
		}
		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
		public String getApprovedBy() {
			return approvedBy;
		}
		public void setApprovedBy(String approvedBy) {
			this.approvedBy = approvedBy;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getHmode() {
			return hmode;
		}
		public void setHmode(String hmode) {
			this.hmode = hmode;
		}
		public String getSelectRecord() {
			return selectRecord;
		}
		public void setSelectRecord(String selectRecord) {
			this.selectRecord = selectRecord;
		}
		public String getPatAge() {
			return patAge;
		}
		public void setPatAge(String patAge) {
			this.patAge = patAge;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getPatAgeUnit() {
			return patAgeUnit;
		}

		public void setPatAgeUnit(String patAgeUnit) {
			this.patAgeUnit = patAgeUnit;
		}

		
		

	}
