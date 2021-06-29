package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ANCNeonatalDetailFB extends ActionForm
{
	private String hmode;

	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String userSeatId;
	private String wardCode;
	private String departmentUnitCode;
	private String entryDate;
	private String entryTime;
	
	private String gravidaNo;

	// Mandatory Fileds
	private String birthDateTime;
	private String birthNatureId;
	private String genderCode;
	private String gender;
		
	// Baby Info
	private String weight;
	private String babylength;
	private String headCircumferences;

	// Anomoly Data
	private String isAnomolyPresent;
	private String anomolyRemarks;
	private String anomolyTypeId;
	private String anomolyType;

	private String umbilicalArteries;
	
	// Other
	private String liquorAppearance;//----------------
	
	private String babyBloodGroupCode;//-----------------
	private String babyBloodGroup;

	// Data times
	private String cryDateTime;
	private String cryDate;
	private String cryTime;
	private String urineDateTime;
	private String urineDate;
	private String urineTime;
	private String motherFeedDateTime;
	private String motherFeedDate;
	private String motherFeedTime;
	private String feedDateTime;
	private String feedDate;
	private String feedTime;
	
	// Birth Trauma
	private String isBirthTrauma;
	private String birthTraumaRemarks;
	private String traumaCauseId;
	private String traumaCause;

	// Still Birth Data
	private String deathAge;//-------------------
	private String deathStillBirthCause;
	private String whenStillBirthDetection;

	// On Neonatal Registration
	private String childCrNo;
	private String childAdmissionNo;

	private String transferToFlag;//-----------

	// Other
	private String peaditricianEmpId;//--------------

	private String presentHealth;//--------------

	private String isFootPrintTaken;//--------------

	private String isCordBloodSampleTaken;//---------------


	// APGAR
	private String heartRateApgar;
	private String heartRate;
	private String respirationApgar;
	private String respiration;
	private String colorApgar;
	private String color;
	private String activityApgar;
	private String activity;
	private String grimaceApgar;
	private String grimace;
	private String apgarScore;
	private String apgarTime;

	// For Birth Certificate
		// Patient Detail
	private String patName;
	private String patHusbandName;
	private String patSpouceName;
	private String patAge;
	private String admDateTime;
		// Patient ANC Detail
	private String parityNo;
	private String abortusNo;
	private String lmpDate;
	private String expectedDeliveryDate;
		// Patient ANC Delivery Detail
	private String deliveryCount;
	private String isInduced;
	private String indicationOfInduction;
	private String inductionMethodId;
	private String inductionMethod;
	private String pregnancyDuration;	
		// Certificate
	private String selBabySerialCert;
	private String noOfCopies;
	private String htmlCertificateData;
		
	
	
	//***********************************************************************
	private String selectedNeoNat;
	private String selectedNeoNatSlNo;
	
	
	public ANCNeonatalDetailFB()
	{
		this.selectedNeoNat = "";
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.selectedNeoNat = "";
		this.selectedNeoNatSlNo = "";
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getEntryTime()
	{
		return entryTime;
	}

	public void setEntryTime(String entryTime)
	{
		this.entryTime = entryTime;
	}

	public String getGravidaNo()
	{
		return gravidaNo;
	}

	public void setGravidaNo(String gravidaNo)
	{
		this.gravidaNo = gravidaNo;
	}

	public String getBirthDateTime()
	{
		return birthDateTime;
	}

	public void setBirthDateTime(String birthDateTime)
	{
		this.birthDateTime = birthDateTime;
	}

	public String getBirthNatureId()
	{
		return birthNatureId;
	}

	public void setBirthNatureId(String birthNatureId)
	{
		this.birthNatureId = birthNatureId;
	}

	public String getGenderCode()
	{
		return genderCode;
	}

	public void setGenderCode(String genderCode)
	{
		this.genderCode = genderCode;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public String getBabylength()
	{
		return babylength;
	}

	public void setBabylength(String babylength)
	{
		this.babylength = babylength;
	}

	public String getHeadCircumferences()
	{
		return headCircumferences;
	}

	public void setHeadCircumferences(String headCircumferences)
	{
		this.headCircumferences = headCircumferences;
	}

	public String getIsAnomolyPresent()
	{
		return isAnomolyPresent;
	}

	public void setIsAnomolyPresent(String isAnomolyPresent)
	{
		this.isAnomolyPresent = isAnomolyPresent;
	}

	public String getAnomolyRemarks()
	{
		return anomolyRemarks;
	}

	public void setAnomolyRemarks(String anomolyRemarks)
	{
		this.anomolyRemarks = anomolyRemarks;
	}

	public String getUmbilicalArteries()
	{
		return umbilicalArteries;
	}

	public void setUmbilicalArteries(String umbilicalArteries)
	{
		this.umbilicalArteries = umbilicalArteries;
	}

	public String getLiquorAppearance()
	{
		return liquorAppearance;
	}

	public void setLiquorAppearance(String liquorAppearance)
	{
		this.liquorAppearance = liquorAppearance;
	}

	public String getBabyBloodGroupCode()
	{
		return babyBloodGroupCode;
	}

	public void setBabyBloodGroupCode(String babyBloodGroupCode)
	{
		this.babyBloodGroupCode = babyBloodGroupCode;
	}

	public String getBabyBloodGroup()
	{
		return babyBloodGroup;
	}

	public void setBabyBloodGroup(String babyBloodGroup)
	{
		this.babyBloodGroup = babyBloodGroup;
	}

	public String getCryDateTime()
	{
		return cryDateTime;
	}

	public void setCryDateTime(String cryDateTime)
	{
		this.cryDateTime = cryDateTime;
	}

	public String getUrineDateTime()
	{
		return urineDateTime;
	}

	public void setUrineDateTime(String urineDateTime)
	{
		this.urineDateTime = urineDateTime;
	}

	public String getMotherFeedDateTime()
	{
		return motherFeedDateTime;
	}

	public void setMotherFeedDateTime(String motherFeedDateTime)
	{
		this.motherFeedDateTime = motherFeedDateTime;
	}

	public String getFeedDateTime()
	{
		return feedDateTime;
	}

	public void setFeedDateTime(String feedDateTime)
	{
		this.feedDateTime = feedDateTime;
	}

	public String getIsBirthTrauma()
	{
		return isBirthTrauma;
	}

	public void setIsBirthTrauma(String isBirthTrauma)
	{
		this.isBirthTrauma = isBirthTrauma;
	}

	public String getBirthTraumaRemarks()
	{
		return birthTraumaRemarks;
	}

	public void setBirthTraumaRemarks(String birthTraumaRemarks)
	{
		this.birthTraumaRemarks = birthTraumaRemarks;
	}

	public String getTraumaCauseId()
	{
		return traumaCauseId;
	}

	public void setTraumaCauseId(String traumaCauseId)
	{
		this.traumaCauseId = traumaCauseId;
	}

	public String getDeathAge()
	{
		return deathAge;
	}

	public void setDeathAge(String deathAge)
	{
		this.deathAge = deathAge;
	}

	public String getDeathStillBirthCause()
	{
		return deathStillBirthCause;
	}

	public void setDeathStillBirthCause(String deathStillBirthCause)
	{
		this.deathStillBirthCause = deathStillBirthCause;
	}

	public String getWhenStillBirthDetection()
	{
		return whenStillBirthDetection;
	}

	public void setWhenStillBirthDetection(String whenStillBirthDetection)
	{
		this.whenStillBirthDetection = whenStillBirthDetection;
	}

	public String getChildCrNo()
	{
		return childCrNo;
	}

	public void setChildCrNo(String childCrNo)
	{
		this.childCrNo = childCrNo;
	}

	public String getChildAdmissionNo()
	{
		return childAdmissionNo;
	}

	public void setChildAdmissionNo(String childAdmissionNo)
	{
		this.childAdmissionNo = childAdmissionNo;
	}

	public String getTransferToFlag()
	{
		return transferToFlag;
	}

	public void setTransferToFlag(String transferToFlag)
	{
		this.transferToFlag = transferToFlag;
	}

	public String getPeaditricianEmpId()
	{
		return peaditricianEmpId;
	}

	public void setPeaditricianEmpId(String peaditricianEmpId)
	{
		this.peaditricianEmpId = peaditricianEmpId;
	}

	public String getPresentHealth()
	{
		return presentHealth;
	}

	public void setPresentHealth(String presentHealth)
	{
		this.presentHealth = presentHealth;
	}

	public String getIsFootPrintTaken()
	{
		return isFootPrintTaken;
	}

	public void setIsFootPrintTaken(String isFootPrintTaken)
	{
		this.isFootPrintTaken = isFootPrintTaken;
	}

	public String getIsCordBloodSampleTaken()
	{
		return isCordBloodSampleTaken;
	}

	public void setIsCordBloodSampleTaken(String isCordBloodSampleTaken)
	{
		this.isCordBloodSampleTaken = isCordBloodSampleTaken;
	}

	public String getSelectedNeoNat()
	{
		return selectedNeoNat;
	}

	public void setSelectedNeoNat(String selectedNeoNat)
	{
		this.selectedNeoNat = selectedNeoNat;
	}

	public String getHeartRateApgar()
	{
		return heartRateApgar;
	}

	public void setHeartRateApgar(String heartRateApgar)
	{
		this.heartRateApgar = heartRateApgar;
	}

	public String getHeartRate()
	{
		return heartRate;
	}

	public void setHeartRate(String heartRate)
	{
		this.heartRate = heartRate;
	}

	public String getRespirationApgar()
	{
		return respirationApgar;
	}

	public void setRespirationApgar(String respirationApgar)
	{
		this.respirationApgar = respirationApgar;
	}

	public String getRespiration()
	{
		return respiration;
	}

	public void setRespiration(String respiration)
	{
		this.respiration = respiration;
	}

	public String getColorApgar()
	{
		return colorApgar;
	}

	public void setColorApgar(String colorApgar)
	{
		this.colorApgar = colorApgar;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getActivityApgar()
	{
		return activityApgar;
	}

	public void setActivityApgar(String activityApgar)
	{
		this.activityApgar = activityApgar;
	}

	public String getActivity()
	{
		return activity;
	}

	public void setActivity(String activity)
	{
		this.activity = activity;
	}

	public String getGrimaceApgar()
	{
		return grimaceApgar;
	}

	public void setGrimaceApgar(String grimaceApgar)
	{
		this.grimaceApgar = grimaceApgar;
	}

	public String getGrimace()
	{
		return grimace;
	}

	public void setGrimace(String grimace)
	{
		this.grimace = grimace;
	}

	public String getApgarScore()
	{
		return apgarScore;
	}

	public void setApgarScore(String apgarScore)
	{
		this.apgarScore = apgarScore;
	}

	public String getApgarTime()
	{
		return apgarTime;
	}

	public void setApgarTime(String apgarTime)
	{
		this.apgarTime = apgarTime;
	}

	public String getPatName()
	{
		return patName;
	}

	public void setPatName(String patName)
	{
		this.patName = patName;
	}

	public String getPatHusbandName()
	{
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName)
	{
		this.patHusbandName = patHusbandName;
	}

	public String getPatAge()
	{
		return patAge;
	}

	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	public String getAdmDateTime()
	{
		return admDateTime;
	}

	public void setAdmDateTime(String admDateTime)
	{
		this.admDateTime = admDateTime;
	}

	public String getParityNo()
	{
		return parityNo;
	}

	public void setParityNo(String parityNo)
	{
		this.parityNo = parityNo;
	}

	public String getAbortusNo()
	{
		return abortusNo;
	}

	public void setAbortusNo(String abortusNo)
	{
		this.abortusNo = abortusNo;
	}

	public String getLmpDate()
	{
		return lmpDate;
	}

	public void setLmpDate(String lmpDate)
	{
		this.lmpDate = lmpDate;
	}

	public String getExpectedDeliveryDate()
	{
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate)
	{
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getDeliveryCount()
	{
		return deliveryCount;
	}

	public void setDeliveryCount(String deliveryCount)
	{
		this.deliveryCount = deliveryCount;
	}

	public String getIsInduced()
	{
		return isInduced;
	}

	public void setIsInduced(String isInduced)
	{
		this.isInduced = isInduced;
	}

	public String getIndicationOfInduction()
	{
		return indicationOfInduction;
	}

	public void setIndicationOfInduction(String indicationOfInduction)
	{
		this.indicationOfInduction = indicationOfInduction;
	}

	public String getInductionMethodId()
	{
		return inductionMethodId;
	}

	public void setInductionMethodId(String inductionMethodId)
	{
		this.inductionMethodId = inductionMethodId;
	}

	public String getInductionMethod()
	{
		return inductionMethod;
	}

	public void setInductionMethod(String inductionMethod)
	{
		this.inductionMethod = inductionMethod;
	}

	public String getPregnancyDuration()
	{
		return pregnancyDuration;
	}

	public void setPregnancyDuration(String pregnancyDuration)
	{
		this.pregnancyDuration = pregnancyDuration;
	}

	public String getNoOfCopies()
	{
		return noOfCopies;
	}

	public void setNoOfCopies(String noOfCopies)
	{
		this.noOfCopies = noOfCopies;
	}

	public String getSelBabySerialCert()
	{
		return selBabySerialCert;
	}

	public void setSelBabySerialCert(String selBabySerialCert)
	{
		this.selBabySerialCert = selBabySerialCert;
	}

	public String getHtmlCertificateData()
	{
		return htmlCertificateData;
	}

	public void setHtmlCertificateData(String htmlCertificateData)
	{
		this.htmlCertificateData = htmlCertificateData;
	}

	public String getCryDate()
	{
		return cryDate;
	}

	public void setCryDate(String cryDate)
	{
		this.cryDate = cryDate;
	}

	public String getCryTime()
	{
		return cryTime;
	}

	public void setCryTime(String cryTime)
	{
		this.cryTime = cryTime;
	}

	public String getUrineDate()
	{
		return urineDate;
	}

	public void setUrineDate(String urineDate)
	{
		this.urineDate = urineDate;
	}

	public String getUrineTime()
	{
		return urineTime;
	}

	public void setUrineTime(String urineTime)
	{
		this.urineTime = urineTime;
	}

	public String getMotherFeedDate()
	{
		return motherFeedDate;
	}

	public void setMotherFeedDate(String motherFeedDate)
	{
		this.motherFeedDate = motherFeedDate;
	}

	public String getMotherFeedTime()
	{
		return motherFeedTime;
	}

	public void setMotherFeedTime(String motherFeedTime)
	{
		this.motherFeedTime = motherFeedTime;
	}

	public String getFeedDate()
	{
		return feedDate;
	}

	public void setFeedDate(String feedDate)
	{
		this.feedDate = feedDate;
	}

	public String getFeedTime()
	{
		return feedTime;
	}

	public void setFeedTime(String feedTime)
	{
		this.feedTime = feedTime;
	}

	public String getTraumaCause()
	{
		return traumaCause;
	}

	public void setTraumaCause(String traumaCause)
	{
		this.traumaCause = traumaCause;
	}

	public String getSelectedNeoNatSlNo()
	{
		return selectedNeoNatSlNo;
	}

	public void setSelectedNeoNatSlNo(String selectedNeoNatSlNo)
	{
		this.selectedNeoNatSlNo = selectedNeoNatSlNo;
	}

	public String getAnomolyTypeId()
	{
		return anomolyTypeId;
	}

	public void setAnomolyTypeId(String anomolyTypeId)
	{
		this.anomolyTypeId = anomolyTypeId;
	}

	public String getAnomolyType()
	{
		return anomolyType;
	}

	public void setAnomolyType(String anomolyType)
	{
		this.anomolyType = anomolyType;
	}

	public String getPatSpouceName() {
		return patSpouceName;
	}

	public void setPatSpouceName(String patSpouceName) {
		this.patSpouceName = patSpouceName;
	}

}
