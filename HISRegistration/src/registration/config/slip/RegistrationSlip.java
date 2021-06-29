package registration.config.slip;

public class RegistrationSlip {	
	
	private String departmentToBeVisited[];	
	private String departmentUnit[];  
	private String room[];    
	private String queNo[];
	//private String episodeCode[];
    private String episodeDate[];
	private String patPrimaryCat;
	private String fileNo[];
	private String fileNoView[];
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patFirstNameInMultiLang;
	private String patMiddleNameInMultiLang;
	private String patLastNameInMultiLang;
	private String hostName;
	private String patCrNo;
	private String patAmountCollected;
	private String patAge;
	private String patGender;
	private String patGuardianName;
	private String patMonthlyIncome;
	private	String workingDays[];
	private	String timing[];
	private String address1;
	private String address2;
	private String disclaimer1[];
	private String disclaimer2[];
	private String disclaimer3[];
	private String isHeader[];
	private String alignment[];
	private String isDuplicate="0";// 0-original, 1-Duplicate, 2- Reprint
	private String duplicateCharge;
	private String hospitalName;
	private String unitConsultant[];
	private String patHusbandName;
	private String noOfCopies;
	private String printerName;
	private String filePrintFlag[];
	private String cardPrintFlag[];
	private String deptCode[];
	private String referFromDepartment[];
	private String printType[];
	private String expDate[];
	private String patOccupation;
	private String patAddContactNo;	
	private String patNationalId;
	private String patCasteCode;
	
	private String hospitalAddress1;
	private String hospitalAddress2;
	private String hospitalCity;
	private String hospitalDistrict;
	private String mlcDetail;
	public String getHospitalDistrict() {
		return hospitalDistrict;
	}
	public void setHospitalDistrict(String hospitalDistrict) {
		this.hospitalDistrict = hospitalDistrict;
	}
	private String hospitalState;
	private String hospitalpinCode;
	private String hospitalPhone;
	private String hospitalFax;
	private String hospitalEmail;	
	
	private String billNo[];
	private String counterName;
	private String billAmount[];
	//Start:Sheeldarshi:28thOct'14:Registration Cancellation
	private String PatPrimaryCatGrp;
	private String clientName;
	private String patActualAmount;
	private String patPrimaryCatCode;
	private String patPrimaryCatGrpCode; 
	private String receiptNo[];
	private String remarks;
	private String doctorName;
	private String sheetNo;
	private String episodeVisitType;
	private String dependentName;
	private String hiddenPatIdNo;
	private String patMemRelationName;
	private String patMemDeptName;
	private String loginUserName;//By Mukund
	private String isQRCodePrintFlag;
	private String patDistrict;//By Garima for UNit Wise Visit
	private String episodeVisitAmount;
	
	
	public String getPatDistrict() {
		return patDistrict;
	}
	public void setPatDistrict(String patDistrict) {
		this.patDistrict = patDistrict;
	}
	public String[] getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String[] receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatPrimaryCatGrpCode() {
		return patPrimaryCatGrpCode;
	}
	public void setPatPrimaryCatGrpCode(String patPrimaryCatGrpCode) {
		this.patPrimaryCatGrpCode = patPrimaryCatGrpCode;
	}
	public String getPatPrimaryCatGrp() {
		return PatPrimaryCatGrp;
	}
	public void setPatPrimaryCatGrp(String PatPrimaryCatGrp) {
		this.PatPrimaryCatGrp = PatPrimaryCatGrp;
	}
	public String getclientName() {
		return clientName;
	}
	public void setclientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPatActualAmount() {
		return patActualAmount;
	}

	public void setPatActualAmount(String patActualAmount) {
		this.patActualAmount = patActualAmount;
	}
	
	//End :Sheeldarshi:28thOct'14:Registration Cancellation
	
	public String getPatAddContactNo() {
		return patAddContactNo;
	}
	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
	}
	public String[] getPrintType() {
		return printType;
	}
	public void setPrintType(String[] printType) {
		this.printType = printType;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String[] getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String[] workingDays) {
		this.workingDays = workingDays;
	}
	public String getPatAmountCollected() {
		return patAmountCollected;
	}
	public void setPatAmountCollected(String patAmountCollected) {
		this.patAmountCollected = patAmountCollected;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatMonthlyIncome() {
		return patMonthlyIncome;
	}
	public void setPatMonthlyIncome(String patMonthlyIncome) {
		this.patMonthlyIncome = patMonthlyIncome;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		
		//this.patCrNo = patCrNo.substring(0, 4)+" "+patCrNo.substring(4, 8)+" "+patCrNo.substring(8);
		this.patCrNo = patCrNo;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String[] getDepartmentToBeVisited() {
		return departmentToBeVisited;
	}
	public void setDepartmentToBeVisited(String[] departmentToBeVisited) {
		this.departmentToBeVisited = departmentToBeVisited;
	}
	public String[] getDepartmentUnit() {
		return departmentUnit;
	}
	public void setDepartmentUnit(String[] departmentUnit) {
		this.departmentUnit = departmentUnit;
	}
	/*public String[] getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String[] episodeCode) {
		this.episodeCode = episodeCode;
	}*/
	public String[] getFileNo() {
		return fileNo;
	}
	public void setFileNo(String[] fileNo) {
		this.fileNo = fileNo;
	}
	public String[] getQueNo() {
		return queNo;
	}
	public void setQueNo(String[] queNo) {
		this.queNo = queNo;
	}
	public String[] getRoom() {
		return room;
	}
	public void setRoom(String[] room) {
		this.room = room;
	}
	public String[] getEpisodeDate() {
		return episodeDate;
	}
	public void setEpisodeDate(String[] episodeDate) {
		this.episodeDate = episodeDate;
	}
	
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatFirstNameInMultiLang() {
		return patFirstNameInMultiLang;
	}
	public void setPatFirstNameInMultiLang(String patFirstNameInMultiLang) {
		this.patFirstNameInMultiLang = patFirstNameInMultiLang;
	}
	public String getPatMiddleNameInMultiLang() {
		return patMiddleNameInMultiLang;
	}
	public void setPatMiddleNameInMultiLang(String patMiddleNameInMultiLang) {
		this.patMiddleNameInMultiLang = patMiddleNameInMultiLang;
	}
	public String getPatLastNameInMultiLang() {
		return patLastNameInMultiLang;
	}
	public void setPatLastNameInMultiLang(String patLastNameInMultiLang) {
		this.patLastNameInMultiLang = patLastNameInMultiLang;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String[] getTiming() {
		return timing;
	}
	public void setTiming(String[] timing) {
		this.timing = timing;
	}
	public String[] getDisclaimer1() {
		return disclaimer1;
	}
	public void setDisclaimer1(String [] disclaimer1) {
		this.disclaimer1 = disclaimer1;
	}
	public String []getDisclaimer2() {
		return disclaimer2;
	}
	public void setDisclaimer2(String []disclaimer2) {
		this.disclaimer2 = disclaimer2;
	}
	public String []getDisclaimer3() {
		return disclaimer3;
	}
	public void setDisclaimer3(String []disclaimer3) {
		this.disclaimer3 = disclaimer3;
	}
	public String []getIsHeader() {
		return isHeader;
	}
	public void setIsHeader(String []isHeader) {
		this.isHeader = isHeader;
	}
	public String [] getAlignment() {
		return alignment;
	}
	public void setAlignment(String []alignment) {
		this.alignment = alignment;
	}
	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getDuplicateCharge() {
		return duplicateCharge;
	}
	public void setDuplicateCharge(String duplicateCharge) {
		this.duplicateCharge = duplicateCharge;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String[] getFileNoView() {
		return fileNoView;
	}
	public void setFileNoView(String[] fileNoView) {
		this.fileNoView = fileNoView;
	}
	public String[] getUnitConsultant() {
		return unitConsultant;
	}
	public void setUnitConsultant(String[] unitConsultant) {
		this.unitConsultant = unitConsultant;
	}
	public String getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(String noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public String getPrinterName() {
		return printerName;
	}
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	public String[] getFilePrintFlag() {
		return filePrintFlag;
	}
	public void setFilePrintFlag(String[] filePrintFlag) {
		this.filePrintFlag = filePrintFlag;
	}
	public String[] getCardPrintFlag() {
		return cardPrintFlag;
	}
	public void setCardPrintFlag(String[] cardPrintFlag) {
		this.cardPrintFlag = cardPrintFlag;
	}
	public String[] getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String[] deptCode) {
		this.deptCode = deptCode;
	}
	public String[] getReferFromDepartment() {
		return referFromDepartment;
	}
	public void setReferFromDepartment(String[] referFromDepartment) {
		this.referFromDepartment = referFromDepartment;
	}
	public String[] getExpDate() {
		return expDate;
	}
	public void setExpDate(String[] expDate) {
		this.expDate = expDate;
	}
	public String getPatOccupation() {
		return patOccupation;
	}
	public void setPatOccupation(String patOccupation) {
		this.patOccupation = patOccupation;
	}
	public String getPatNationalId() {
		return patNationalId;
	}
	public void setPatNationalId(String patNationalId) {
		this.patNationalId = patNationalId;
	}
	public String getPatCasteCode() {
		return patCasteCode;
	}
	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
	}
	public String getHospitalAddress1() {
		return hospitalAddress1;
	}
	public void setHospitalAddress1(String hospitalAddress1) {
		this.hospitalAddress1 = hospitalAddress1;
	}
	public String getHospitalAddress2() {
		return hospitalAddress2;
	}
	public void setHospitalAddress2(String hospitalAddress2) {
		this.hospitalAddress2 = hospitalAddress2;
	}
	public String getHospitalCity() {
		return hospitalCity;
	}
	public void setHospitalCity(String hospitalCity) {
		this.hospitalCity = hospitalCity;
	}
	public String getHospitalState() {
		return hospitalState;
	}
	public void setHospitalState(String hospitalState) {
		this.hospitalState = hospitalState;
	}
	public String getHospitalpinCode() {
		return hospitalpinCode;
	}
	public void setHospitalpinCode(String hospitalpinCode) {
		this.hospitalpinCode = hospitalpinCode;
	}
	public String getHospitalPhone() {
		return hospitalPhone;
	}
	public void setHospitalPhone(String hospitalPhone) {
		this.hospitalPhone = hospitalPhone;
	}
	public String getHospitalFax() {
		return hospitalFax;
	}
	public void setHospitalFax(String hospitalFax) {
		this.hospitalFax = hospitalFax;
	}
	public String getHospitalEmail() {
		return hospitalEmail;
	}
	public void setHospitalEmail(String hospitalEmail) {
		this.hospitalEmail = hospitalEmail;
	}
	public String[] getBillNo() {
		return billNo;
	}
	public void setBillNo(String[] billNo) {
		this.billNo = billNo;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String[] getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String[] billAmount) {
		this.billAmount = billAmount;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setDoctorName(String doctorName)
	{
		this.doctorName = doctorName;
	}
	public String getDoctorName()
	{
		return doctorName;
	}
	public String getSheetNo()
	{
		return sheetNo;
	}
	public void setSheetNo(String sheetNo)
	{
		this.sheetNo = sheetNo;
	}
	public String getEpisodeVisitType() {
		return episodeVisitType;
	}
	public void setEpisodeVisitType(String episodeVisitType) {
		this.episodeVisitType = episodeVisitType;
	}
	
	public String getPatMemRelationName() {
		return patMemRelationName;
	}

	public void setPatMemRelationName(String patMemRelationName) {
		this.patMemRelationName = patMemRelationName;
	}
	public String getHiddenPatIdNo() {
		return hiddenPatIdNo;
	}

	public void setHiddenPatIdNo(String hiddenPatIdNo) {
		this.hiddenPatIdNo = hiddenPatIdNo;
	}
	public String getDependentName() {
		return dependentName;
	}
	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	public String getPatMemDeptName() {
		return patMemDeptName;
	}
	public void setPatMemDeptName(String patMemDeptName) {
		this.patMemDeptName = patMemDeptName;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getIsQRCodePrintFlag() {
		return isQRCodePrintFlag;
	}
	public void setIsQRCodePrintFlag(String isQRCodePrintFlag) {
		this.isQRCodePrintFlag = isQRCodePrintFlag;
	}
	public String getMlcDetail() {
		return mlcDetail;
	}
	public void setMlcDetail(String mlcDetail) {
		this.mlcDetail = mlcDetail;
	}
	
	
	public String getEpisodeVisitAmount() {
		return episodeVisitAmount;
	}
	public void setEpisodeVisitAmount(String episodeVisitAmount) {
		this.episodeVisitAmount = episodeVisitAmount;
	}
}