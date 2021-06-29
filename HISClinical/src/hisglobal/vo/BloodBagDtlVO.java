package hisglobal.vo;

public class BloodBagDtlVO extends ValueObject
{
	private String bagNo;
	private String bagSequenceNo;
    private String donorRegistrationNo;
    private String bagVolume;
    private String donorVisitNo;
    private String bagTypeCode;
    private String tubingNo;
    private String pilotTube;
    private String storageID;
    private String cellStatus;
    private String serumStatus;
    private String investigationStatus;
    private String bagStatus;
    private String componentFlag;
    private String bloodGroupCode;
    private String bloodGroupDesc;
    private String antiCoagulantVolume;
    private String donationTypeFlag;
    private String donationTypeCode;
    private String donationTypeDesc;
    private String bagTypeDesc;
    private String donationDate;
    private String antiCoagulantId;
    private String antiCoagulantFlag;
    private String initialBloodABO;
	private String isAnticoagulant;
	private String initialRH;
    private String initialRHDesc;
    private String bagMaxVolume;
	private String bagStatusDesc;
	private String donationStartTime;
	private String plainPilotTube;
	private String bloodBagSequenceNo;
	private String donationEndTime;
	private String groupingStatus;
	private String bloodAbo;
	private String bloodRh;
	private String bloodRhDesc;
	private String antibodyStatus;
	private String antibodyStatusDesc;
	private String bagBatchNo;
	private String anticoagulantPilotTube;
	private String plainBagAccount;
	private String solutionBagAccount;
	private String additiveSolution;
	private String bagExpiryAdditive;
	private String antibodyResult;
	private String crossMatchFlag;
	private String donationMode;
	private String donorVisitNoToModfy;
	private String storeId;
	private String itemId;
	private String bagAndSeqNo;
	private String bloodBagExpiry;
	private String donorName;
    private String bloodComponentID;
    private String donationChkFlag;
    
    //For Component Seperation Process
    private String bagAttached;
    private String storageArea;
    private String componentStatusFlag;
    private String grossAppearance;
    private String preparationDate;
    private String flagLabel;
    private String masterBagVolume;
    
     //By Pawan Kumar B N on 22-09-2012
	private String expiryDate;
	private String salineNo;
	
	//By Pawan Kumar B N on 08-12-2012
		private String collectionPoint;
		
	//By Pawan Kumar B N on 20-01-2013
			private String bldTestName;
	
	//Added By Pawan Kumar B N on 05-Feb-2013
    private String remarks;		
    
  //Added By Pawan Kumar B N on 22-Feb-2013
    private String orderBy;		
    
  //Added By Pawan Kumar B N on 22-Feb-2013
    private String allStock;
    
    //Added By Pawan Kumar B N on 22-Feb-2013
    private String labellingDate;
    private String shiftingDate;
			
  	public String getComponentStatusFlag()
	{
		return componentStatusFlag;
	}
	public void setComponentStatusFlag(String componentStatusFlag)
	{
		this.componentStatusFlag = componentStatusFlag;
	}
	public String getItemId()
	{
		return itemId;
	}
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}
	public String getStoreId()
	{
		return storeId;
	}
	public void setStoreId(String storeId)
	{
		this.storeId = storeId;
	}
	public String getDonorVisitNoToModfy()
	{
		return donorVisitNoToModfy;
	}
	public void setDonorVisitNoToModfy(String donorVisitNoToModfy)
	{
		this.donorVisitNoToModfy = donorVisitNoToModfy;
	}
	public String getBagExpiryAdditive()
	{
		return bagExpiryAdditive;
	}
	public void setBagExpiryAdditive(String bagExpiryAdditive)
	{
		this.bagExpiryAdditive = bagExpiryAdditive;
	}
	public String getAdditiveSolution()
	{
		return additiveSolution;
	}
	public void setAdditiveSolution(String additiveSolution)
	{
		this.additiveSolution = additiveSolution;
	}
	public String getAntibodyStatus()
	{
		return antibodyStatus;
	}
	public void setAntibodyStatus(String antibodyStatus)
	{
		this.antibodyStatus = antibodyStatus;
	}
	public String getBagBatchNo()
	{
		return bagBatchNo;
	}
	public void setBagBatchNo(String bagBatchNo)
	{
		this.bagBatchNo = bagBatchNo;
	}
	public String getAnticoagulantPilotTube()
	{
		return anticoagulantPilotTube;
	}
	public void setAnticoagulantPilotTube(String anticoagulantPilotTube)
	{
		this.anticoagulantPilotTube = anticoagulantPilotTube;
	}
	public String getPlainBagAccount()
	{
		return plainBagAccount;
	}
	public void setPlainBagAccount(String plainBagAccount)
	{
		this.plainBagAccount = plainBagAccount;
	}
	public String getSolutionBagAccount()
	{
		return solutionBagAccount;
	}
	public void setSolutionBagAccount(String solutionBagAccount)
	{
		this.solutionBagAccount = solutionBagAccount;
	}
	public String getGroupingStatus()
	{
		return groupingStatus;
	}
	public void setGroupingStatus(String groupingStatus)
	{
		this.groupingStatus = groupingStatus;
	}
	public String getBloodAbo()
	{
		return bloodAbo;
	}
	public void setBloodAbo(String bloodAbo)
	{
		this.bloodAbo = bloodAbo;
	}
	public String getBloodRh()
	{
		return bloodRh;
	}
	public void setBloodRh(String bloodRh)
	{
		this.bloodRh = bloodRh;
	}
	public String getBagMaxVolume()
	{
		return bagMaxVolume;
	}
	public void setBagMaxVolume(String bagMaxVolume)
	{
		this.bagMaxVolume = bagMaxVolume;
	}
	public String getInitialBloodABO()
	{
		return initialBloodABO;
	}
	public void setInitialBloodABO(String initialBloodABO)
	{
		this.initialBloodABO = initialBloodABO;
	}
	public String getInitialRH()
	{
		return initialRH;
	}
	public void setInitialRH(String initialRH)
	{
		this.initialRH = initialRH;
	}
	
    public String getDonationDate()
	{
		return donationDate;
	}
	public void setDonationDate(String donationDate)
	{
		this.donationDate = donationDate;
	}
	
	public String getBagNo()
    {
		return bagNo;
	}
	public void setBagNo(String bagNo)
	{
		this.bagNo = bagNo;
	}
	public String getDonorRegistrationNo()
	{
		return donorRegistrationNo;
	}
	public void setDonorRegistrationNo(String donorRegistrationNo)
	{
		this.donorRegistrationNo = donorRegistrationNo;
	}
	public String getBagVolume()
	{
		return bagVolume;
	}
	public void setBagVolume(String bagVolume)
	{
		this.bagVolume = bagVolume;
	}
	public String getDonorVisitNo()
	{
		return donorVisitNo;
	}
	public void setDonorVisitNo(String donorVisitNo)
	{
		this.donorVisitNo = donorVisitNo;
	}
	
	public String getBagTypeCode()
	{
		return bagTypeCode;
	}
	public void setBagTypeCode(String bagTypeCode)
	{
		this.bagTypeCode = bagTypeCode;
	}
	public String getTubingNo()
	{
		return tubingNo;
	}
	public void setTubingNo(String tubingNo)
	{
		this.tubingNo = tubingNo;
	}
	public String getPilotTube()
	{
		return pilotTube;
	}
	public void setPilotTube(String pilotTube)
	{
		this.pilotTube = pilotTube;
	}
	public String getStorageID()
	{
		return storageID;
	}
	public void setStorageID(String storageID)
	{
		this.storageID = storageID;
	}
	public String getCellStatus()
	{
		return cellStatus;
	}
	public void setCellStatus(String cellStatus)
	{
		this.cellStatus = cellStatus;
	}
	public String getSerumStatus()
	{
		return serumStatus;
	}
	public void setSerumStatus(String serumStatus)
	{
		this.serumStatus = serumStatus;
	}
	public String getInvestigationStatus()
	{
		return investigationStatus;
	}
	public void setInvestigationStatus(String investigationStatus)
	{
		this.investigationStatus = investigationStatus;
	}
	public String getBagStatus()
	{
		return bagStatus;
	}
	public void setBagStatus(String bagStatus)
	{
		this.bagStatus = bagStatus;
	}
	public String getComponentFlag()
	{
		return componentFlag;
	}
	public void setComponentFlag(String componentFlag)
	{
		this.componentFlag = componentFlag;
	}
	public String getBloodGroupCode()
	{
		return bloodGroupCode;
	}
	public void setBloodGroupCode(String bloodGroupCode)
	{
		this.bloodGroupCode = bloodGroupCode;
	}
	public String getBloodGroupDesc()
	{
		return bloodGroupDesc;
	}
	public void setBloodGroupDesc(String bloodGroupDesc)
	{
		this.bloodGroupDesc = bloodGroupDesc;
	}
	public String getAntiCoagulantVolume()
	{
		return antiCoagulantVolume;
	}
	public void setAntiCoagulantVolume(String antiCoagulantVolume)
	{
		this.antiCoagulantVolume = antiCoagulantVolume;
	}
	public String getDonationTypeFlag()
	{
		return donationTypeFlag;
	}
	public void setDonationTypeFlag(String donationTypeFlag)
	{
		this.donationTypeFlag = donationTypeFlag;
	}
	public String getDonationTypeCode()
	{
		return donationTypeCode;
	}
	public void setDonationTypeCode(String donationTypeCode)
	{
		this.donationTypeCode = donationTypeCode;
	}
	public String getDonationTypeDesc()
	{
		return donationTypeDesc;
	}
	public void setDonationTypeDesc(String donationTypeDesc)
	{
		this.donationTypeDesc = donationTypeDesc;
	}
	public String getBagTypeDesc()
	{
		return bagTypeDesc;
	}
	public void setBagTypeDesc(String bagTypeDesc)
	{
		this.bagTypeDesc = bagTypeDesc;
	}
	public String getIsAnticoagulant()
	{
		return isAnticoagulant;
	}
	public void setIsAnticoagulant(String isAnticoagulant)
	{
		this.isAnticoagulant = isAnticoagulant;
	}
	

	public String getAntiCoagulantId()
	{
		return antiCoagulantId;
	}
	public void setAntiCoagulantId(String antiCoagulantId)
	{
		this.antiCoagulantId = antiCoagulantId;
	}
	public String getAntiCoagulantFlag()
	{
		return antiCoagulantFlag;
	}
	public void setAntiCoagulantFlag(String antiCoagulantFlag)
	{
		this.antiCoagulantFlag = antiCoagulantFlag;
	}
	public String getBagStatusDesc()
	{
		return bagStatusDesc;
	}
	public void setBagStatusDesc(String bagStatusDesc)
	{
		this.bagStatusDesc = bagStatusDesc;
	}
	public String getInitialRHDesc()
	{
		return initialRHDesc;
	}
	public void setInitialRHDesc(String initialRHDesc)
	{
		this.initialRHDesc = initialRHDesc;
	}
	public String getDonationStartTime()
	{
		return donationStartTime;
	}
	public void setDonationStartTime(String donationStartTime)
	{
		this.donationStartTime = donationStartTime;
	}
	public String getDonationEndTime()
	{
		return donationEndTime;
	}
	public void setDonationEndTime(String donationEndTime)
	{
		this.donationEndTime = donationEndTime;
	}
	public String getPlainPilotTube()
	{
		return plainPilotTube;
	}
	public void setPlainPilotTube(String plainPilotTube)
	{
		this.plainPilotTube = plainPilotTube;
	}
	public String getBloodBagSequenceNo()
	{
		return bloodBagSequenceNo;
	}
	public void setBloodBagSequenceNo(String bloodBagSequenceNo)
	{
		this.bloodBagSequenceNo = bloodBagSequenceNo;
	}
	public String getBagSequenceNo()
	{
		return bagSequenceNo;
	}
	public void setBagSequenceNo(String bagSequenceNo)
	{
		this.bagSequenceNo = bagSequenceNo;
	}

	public String getAntibodyResult()
	{
		return antibodyResult;
	}
	public void setAntibodyResult(String antibodyResult)
	{
		this.antibodyResult = antibodyResult;
	}
	public String getBloodRhDesc()
	{
		return bloodRhDesc;
	}
	public void setBloodRhDesc(String bloodRhDesc)
	{
		this.bloodRhDesc = bloodRhDesc;
	}
	public String getAntibodyStatusDesc()
	{
		return antibodyStatusDesc;
	}
	public void setAntibodyStatusDesc(String antibodyStatusDesc)
	{
		this.antibodyStatusDesc = antibodyStatusDesc;
	}

	public String getCrossMatchFlag()
	{
		return crossMatchFlag;
	}
	public void setCrossMatchFlag(String crossMatchFlag)
	{
		this.crossMatchFlag = crossMatchFlag;
	}
	public String getDonationMode()
	{
		return donationMode;
	}
	public void setDonationMode(String donationMode)
	{
		this.donationMode = donationMode;
	}
	public String getBagAndSeqNo()
	{
		return bagAndSeqNo;
	}
	public void setBagAndSeqNo(String bagAndSeqNo)
	{
		this.bagAndSeqNo = bagAndSeqNo;
	}
	public String getBloodBagExpiry()
	{
		return bloodBagExpiry;
	}
	public void setBloodBagExpiry(String bloodBagExpiry)
	{
		this.bloodBagExpiry = bloodBagExpiry;
	}
	public String getDonorName() {
		return donorName;
	}
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	public String getBloodComponentID() {
		return bloodComponentID;
	}
	public void setBloodComponentID(String bloodComponentID) {
		this.bloodComponentID = bloodComponentID;
	}
	public String getDonationChkFlag() {
		return donationChkFlag;
	}
	public void setDonationChkFlag(String donationChkFlag) {
		this.donationChkFlag = donationChkFlag;
	}
	public String getBagAttached()
	{
		return bagAttached;
	}
	public void setBagAttached(String bagAttached)
	{
		this.bagAttached = bagAttached;
	}
	public String getStorageArea()
	{
		return storageArea;
	}
	public void setStorageArea(String storageArea)
	{
		this.storageArea = storageArea;
	}
	public String getGrossAppearance()
	{
		return grossAppearance;
	}
	public void setGrossAppearance(String grossAppearance)
	{
		this.grossAppearance = grossAppearance;
	}
	public String getPreparationDate()
	{
		return preparationDate;
	}
	public void setPreparationDate(String preparationDate)
	{
		this.preparationDate = preparationDate;
	}
	public String getFlagLabel()
	{
		return flagLabel;
	}
	public void setFlagLabel(String flagLabel)
	{
		this.flagLabel = flagLabel;
	}
	public String getMasterBagVolume()
	{
		return masterBagVolume;
	}
	public void setMasterBagVolume(String masterBagVolume)
	{
		this.masterBagVolume = masterBagVolume;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSalineNo() {
		return salineNo;
	}
	public void setSalineNo(String salineNo) {
		this.salineNo = salineNo;
	}
	public String getCollectionPoint() {
		return collectionPoint;
	}
	public void setCollectionPoint(String collectionPoint) {
		this.collectionPoint = collectionPoint;
	}
	public String getBldTestName() {
		return bldTestName;
	}
	public void setBldTestName(String bldTestName) {
		this.bldTestName = bldTestName;
	}
	public String getRemarks()
	{
		return remarks;
	}
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public String getOrderBy()
	{
		return orderBy;
	}
	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}
	public String getAllStock()
	{
		return allStock;
	}
	public void setAllStock(String allStock)
	{
		this.allStock = allStock;
	}
	public String getLabellingDate()
	{
		return labellingDate;
	}
	public void setLabellingDate(String labellingDate)
	{
		this.labellingDate = labellingDate;
	}
	public String getShiftingDate()
	{
		return shiftingDate;
	}
	public void setShiftingDate(String shiftingDate)
	{
		this.shiftingDate = shiftingDate;
	}
    


}
