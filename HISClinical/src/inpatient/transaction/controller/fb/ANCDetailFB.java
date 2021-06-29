package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ANCDetailFB extends ActionForm
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
	
	private String ancEpisodeCode;
	private String ancEpisodeUnitCode;
	private String ancEpisodeUnit;
	
	private String ancDetailGetFlag;	// 1:Yes if a Gravida is in running otherwise 0:No

	// Other Details
	private String patDOB;
	private String patAge;
	private String patBloodGroup;
	private String patHusbandBloodGroup;
	private String patCaste;
	private String patFamilyType;
	private String patGuardianName;
	private String patMotherName;
	private String patHusbandName;
	private String patOccupation;
	private String patHusbandOccupation;
	private String patFatherOccupation;
	private String patMotherOccupation;
	private String patEducationStatus;
	private String patSpouseEducationStatus;
	private String patFatherEducationStatus;
	private String patMotherEducationStatus;

	// Gynaecology Details
	private String patDateOfMarriage;
	private String patAgeOfMarriage;
	private String patUnitAgeOfMarriage;
	private String isActualDateOfMarriage;
	
	private String patDateOfMenarche;
	private String patAgeOfMenarche;
	private String patUnitAgeOfMenarche;
	private String isActualAgeOfMenarche;
	
	private String menstrualCycleId;
	private String menstrualCycleDays;

	// Antenatal Detail
	private String ancNo;
	private String gravidaNo;
	private String parityNo;
	private String abortusNo;
	private String lmpDate;
	private String isActualLMP;
	private String expectedDeliveryDate;
	private String ultraSoundEDD;
	private String actualDeliveryDate;
	private String gestationStartDate;
	private String gestationPeriod;
	private String isHighriskPregnancy;
	private String complications;
	private String deliveryStatus;
	private String queckeningWeek;
	private String queckeningRemarks;
	private String bookingStartDate;
	private String bookingEndDate;
	private String isContraceptiveUsed;
	private String contraceptiveRemarks;
	private String detectionMethod;
	private String detectionDate;
	private String isAntiDGiven;
	private String remarks;

	private String histParityNo;
	private String histAbortusNo;
	private String histCount;
	private String initalGravida;
	private String[] histGravidaNo;
	private String[] histDeliveryStatus;
	private String[] histDeliveryDate;
	private String[] histPregnancyDuration;
	private String[] histPregnancyRemarks;
	private String[] histDeliveryPlaceId;
	private String[] histLabourDuration;
	private String[] histDeliveryTypeId;
	private String[] histIsAntiDGiven;
	private String[] histLabourRemarks;
	private String[] histNoOfBirths;	
	
	private String[] histChildNo;
	private String[] histBirthNatureId;
	private String[] histGenderCode;
	private String[] histWeight;
	private String[] histBabyBloodGroupCode;
	private String[] histPresentHealth;
	private String[] histDeathAge;
	private String[] histDeathCause;

	private String deliveryPlaceList;
	private String genderList;
	private String deliveryTypeList;
	private String bloodGroupList;
	
	private String histPregData;
	
	private String endANCDetailFlag;
	
	private String macroProcessId;
	private String macroHead;
	
	public ANCDetailFB()
	{
		this.ancDetailGetFlag=OpdConfig.NO;
		
		this.initalGravida = "0";
		this.isActualAgeOfMenarche= OpdConfig.NO;
		this.isActualDateOfMarriage=OpdConfig.NO;
		this.isActualLMP=OpdConfig.YES;
		this.isHighriskPregnancy=OpdConfig.NO;
		this.endANCDetailFlag=OpdConfig.NO;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		// Gynaecology Details
		this.menstrualCycleId="";
		this.menstrualCycleDays="";

		// Antenatal Detail
		this.ancNo="";
		this.gravidaNo="";
		this.parityNo="";
		this.abortusNo="";
		this.lmpDate="";
		this.isActualLMP="";
		this.expectedDeliveryDate="";
		this.ultraSoundEDD="";
		this.actualDeliveryDate="";
		this.gestationStartDate="";
		this.gestationPeriod="";
		this.isHighriskPregnancy="";
		this.complications="";
		this.deliveryStatus="";
		this.queckeningWeek="";
		this.queckeningRemarks="";
		this.bookingStartDate="";
		this.bookingEndDate="";
		this.isContraceptiveUsed="";
		this.contraceptiveRemarks="";
		this.detectionMethod="";
		this.detectionDate="";
		this.isAntiDGiven="";
		this.remarks="";

		this.histParityNo="";
		this.histAbortusNo="";
		this.histCount="";
		this.initalGravida="";
		this.deliveryPlaceList="";
		this.genderList="";
		this.deliveryTypeList="";
		this.bloodGroupList="";
		
		this.histPregData="";
		this.endANCDetailFlag=OpdConfig.NO;
	}

	public String getDeliveryPlaceList()
	{
		return deliveryPlaceList;
	}

	public void setDeliveryPlaceList(String deliveryPlaceList)
	{
		this.deliveryPlaceList = deliveryPlaceList;
	}

	public String getGenderList()
	{
		return genderList;
	}

	public void setGenderList(String genderList)
	{
		this.genderList = genderList;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
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

	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	public String getPatMotherName()
	{
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName)
	{
		this.patMotherName = patMotherName;
	}

	public String getPatHusbandName()
	{
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName)
	{
		this.patHusbandName = patHusbandName;
	}

	public String getPatOccupation()
	{
		return patOccupation;
	}

	public void setPatOccupation(String patOccupation)
	{
		this.patOccupation = patOccupation;
	}

	public String getPatHusbandOccupation()
	{
		return patHusbandOccupation;
	}

	public void setPatHusbandOccupation(String patHusbandOccupation)
	{
		this.patHusbandOccupation = patHusbandOccupation;
	}

	public String getPatFatherOccupation()
	{
		return patFatherOccupation;
	}

	public void setPatFatherOccupation(String patFatherOccupation)
	{
		this.patFatherOccupation = patFatherOccupation;
	}

	public String getPatMotherOccupation()
	{
		return patMotherOccupation;
	}

	public void setPatMotherOccupation(String patMotherOccupation)
	{
		this.patMotherOccupation = patMotherOccupation;
	}

	public String getPatEducationStatus()
	{
		return patEducationStatus;
	}

	public void setPatEducationStatus(String patEducationStatus)
	{
		this.patEducationStatus = patEducationStatus;
	}

	public String getPatSpouseEducationStatus()
	{
		return patSpouseEducationStatus;
	}

	public void setPatSpouseEducationStatus(String patSpouseEducationStatus)
	{
		this.patSpouseEducationStatus = patSpouseEducationStatus;
	}

	public String getPatFatherEducationStatus()
	{
		return patFatherEducationStatus;
	}

	public void setPatFatherEducationStatus(String patFatherEducationStatus)
	{
		this.patFatherEducationStatus = patFatherEducationStatus;
	}

	public String getPatMotherEducationStatus()
	{
		return patMotherEducationStatus;
	}

	public void setPatMotherEducationStatus(String patMotherEducationStatus)
	{
		this.patMotherEducationStatus = patMotherEducationStatus;
	}

	public String getPatDateOfMarriage()
	{
		return patDateOfMarriage;
	}

	public void setPatDateOfMarriage(String patDateOfMarriage)
	{
		this.patDateOfMarriage = patDateOfMarriage;
	}

	public String getPatAgeOfMenarche()
	{
		return patAgeOfMenarche;
	}

	public void setPatAgeOfMenarche(String patAgeOfMenarche)
	{
		this.patAgeOfMenarche = patAgeOfMenarche;
	}

	public String getMenstrualCycleId()
	{
		return menstrualCycleId;
	}

	public void setMenstrualCycleId(String menstrualCycleId)
	{
		this.menstrualCycleId = menstrualCycleId;
	}

	public String getAncNo()
	{
		return ancNo;
	}

	public void setAncNo(String ancNo)
	{
		this.ancNo = ancNo;
	}

	public String getGravidaNo()
	{
		return gravidaNo;
	}

	public void setGravidaNo(String gravidaNo)
	{
		this.gravidaNo = gravidaNo;
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

	public String getIsActualLMP()
	{
		return isActualLMP;
	}

	public void setIsActualLMP(String isActualLMP)
	{
		this.isActualLMP = isActualLMP;
	}

	public String getExpectedDeliveryDate()
	{
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate)
	{
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getActualDeliveryDate()
	{
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(String actualDeliveryDate)
	{
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public String getGestationStartDate()
	{
		return gestationStartDate;
	}

	public void setGestationStartDate(String gestationStartDate)
	{
		this.gestationStartDate = gestationStartDate;
	}

	public String getGestationPeriod()
	{
		return gestationPeriod;
	}

	public void setGestationPeriod(String gestationPeriod)
	{
		this.gestationPeriod = gestationPeriod;
	}

	public String getIsHighriskPregnancy()
	{
		return isHighriskPregnancy;
	}

	public void setIsHighriskPregnancy(String isHighriskPregnancy)
	{
		this.isHighriskPregnancy = isHighriskPregnancy;
	}

	public String getComplications()
	{
		return complications;
	}

	public void setComplications(String complications)
	{
		this.complications = complications;
	}

	public String getDeliveryStatus()
	{
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus)
	{
		this.deliveryStatus = deliveryStatus;
	}

	public String getQueckeningWeek()
	{
		return queckeningWeek;
	}

	public void setQueckeningWeek(String queckeningWeek)
	{
		this.queckeningWeek = queckeningWeek;
	}

	public String getQueckeningRemarks()
	{
		return queckeningRemarks;
	}

	public void setQueckeningRemarks(String queckeningRemarks)
	{
		this.queckeningRemarks = queckeningRemarks;
	}

	public String getBookingStartDate()
	{
		return bookingStartDate;
	}

	public void setBookingStartDate(String bookingStartDate)
	{
		this.bookingStartDate = bookingStartDate;
	}

	public String getBookingEndDate()
	{
		return bookingEndDate;
	}

	public void setBookingEndDate(String bookingEndDate)
	{
		this.bookingEndDate = bookingEndDate;
	}

	public String getPatBloodGroup()
	{
		return patBloodGroup;
	}

	public void setPatBloodGroup(String patBloodGroup)
	{
		this.patBloodGroup = patBloodGroup;
	}

	public String getPatCaste()
	{
		return patCaste;
	}

	public void setPatCaste(String patCaste)
	{
		this.patCaste = patCaste;
	}

	public String getPatFamilyType()
	{
		return patFamilyType;
	}

	public void setPatFamilyType(String patFamilyType)
	{
		this.patFamilyType = patFamilyType;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getIsActualDateOfMarriage()
	{
		return isActualDateOfMarriage;
	}

	public void setIsActualDateOfMarriage(String isActualDateOfMarriage)
	{
		this.isActualDateOfMarriage = isActualDateOfMarriage;
	}

	public String getIsActualAgeOfMenarche()
	{
		return isActualAgeOfMenarche;
	}

	public void setIsActualAgeOfMenarche(String isActualAgeOfMenarche)
	{
		this.isActualAgeOfMenarche = isActualAgeOfMenarche;
	}

	public String getPatAgeOfMarriage()
	{
		return patAgeOfMarriage;
	}

	public void setPatAgeOfMarriage(String patAgeOfMarriage)
	{
		this.patAgeOfMarriage = patAgeOfMarriage;
	}

	public String getPatUnitAgeOfMarriage()
	{
		return patUnitAgeOfMarriage;
	}

	public void setPatUnitAgeOfMarriage(String patUnitAgeOfMarriage)
	{
		this.patUnitAgeOfMarriage = patUnitAgeOfMarriage;
	}

	public String getPatDateOfMenarche()
	{
		return patDateOfMenarche;
	}

	public void setPatDateOfMenarche(String patDateOfMenarche)
	{
		this.patDateOfMenarche = patDateOfMenarche;
	}

	public String getPatUnitAgeOfMenarche()
	{
		return patUnitAgeOfMenarche;
	}

	public void setPatUnitAgeOfMenarche(String patUnitAgeOfMenarche)
	{
		this.patUnitAgeOfMenarche = patUnitAgeOfMenarche;
	}

	public String getHistParityNo()
	{
		return histParityNo;
	}

	public void setHistParityNo(String histParityNo)
	{
		this.histParityNo = histParityNo;
	}

	public String getHistAbortusNo()
	{
		return histAbortusNo;
	}

	public void setHistAbortusNo(String histAbortusNo)
	{
		this.histAbortusNo = histAbortusNo;
	}

	public String getHistCount()
	{
		return histCount;
	}

	public void setHistCount(String histCount)
	{
		this.histCount = histCount;
	}

	public String[] getHistGravidaNo()
	{
		return histGravidaNo;
	}

	public void setHistGravidaNo(String[] histGravidaNo)
	{
		this.histGravidaNo = histGravidaNo;
	}

	public String[] getHistDeliveryStatus()
	{
		return histDeliveryStatus;
	}

	public void setHistDeliveryStatus(String[] histDeliveryStatus)
	{
		this.histDeliveryStatus = histDeliveryStatus;
	}

	public String[] getHistDeliveryDate()
	{
		return histDeliveryDate;
	}

	public void setHistDeliveryDate(String[] histDeliveryDate)
	{
		this.histDeliveryDate = histDeliveryDate;
	}

	public String[] getHistPregnancyDuration()
	{
		return histPregnancyDuration;
	}

	public void setHistPregnancyDuration(String[] histPregnancyDuration)
	{
		this.histPregnancyDuration = histPregnancyDuration;
	}

	public String[] getHistPregnancyRemarks()
	{
		return histPregnancyRemarks;
	}

	public void setHistPregnancyRemarks(String[] histPregnancyRemarks)
	{
		this.histPregnancyRemarks = histPregnancyRemarks;
	}

	public String[] getHistDeliveryPlaceId()
	{
		return histDeliveryPlaceId;
	}

	public void setHistDeliveryPlaceId(String[] histDeliveryPlaceId)
	{
		this.histDeliveryPlaceId = histDeliveryPlaceId;
	}

	public String[] getHistLabourDuration()
	{
		return histLabourDuration;
	}

	public void setHistLabourDuration(String[] histLabourDuration)
	{
		this.histLabourDuration = histLabourDuration;
	}

	public String[] getHistLabourRemarks()
	{
		return histLabourRemarks;
	}

	public void setHistLabourRemarks(String[] histLabourRemarks)
	{
		this.histLabourRemarks = histLabourRemarks;
	}

	public String[] getHistBirthNatureId()
	{
		return histBirthNatureId;
	}

	public void setHistBirthNatureId(String[] histBirthNatureId)
	{
		this.histBirthNatureId = histBirthNatureId;
	}

	public String[] getHistGenderCode()
	{
		return histGenderCode;
	}

	public void setHistGenderCode(String[] histGenderCode)
	{
		this.histGenderCode = histGenderCode;
	}

	public String[] getHistWeight()
	{
		return histWeight;
	}

	public void setHistWeight(String[] histWeight)
	{
		this.histWeight = histWeight;
	}

	public String[] getHistPresentHealth()
	{
		return histPresentHealth;
	}

	public void setHistPresentHealth(String[] histPresentHealth)
	{
		this.histPresentHealth = histPresentHealth;
	}

	public String[] getHistChildNo()
	{
		return histChildNo;
	}

	public void setHistChildNo(String[] histChildNo)
	{
		this.histChildNo = histChildNo;
	}

	public String[] getHistDeathAge()
	{
		return histDeathAge;
	}

	public void setHistDeathAge(String[] histDeathAge)
	{
		this.histDeathAge = histDeathAge;
	}

	public String[] getHistDeathCause()
	{
		return histDeathCause;
	}

	public void setHistDeathCause(String[] histDeathCause)
	{
		this.histDeathCause = histDeathCause;
	}

	public String getInitalGravida()
	{
		return initalGravida;
	}

	public void setInitalGravida(String initalGravida)
	{
		this.initalGravida = initalGravida;
	}

	public String getPatDOB()
	{
		return patDOB;
	}

	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}

	public String getPatHusbandBloodGroup()
	{
		return patHusbandBloodGroup;
	}

	public void setPatHusbandBloodGroup(String patHusbandBloodGroup)
	{
		this.patHusbandBloodGroup = patHusbandBloodGroup;
	}

	public String getUltraSoundEDD()
	{
		return ultraSoundEDD;
	}

	public void setUltraSoundEDD(String ultraSoundEDD)
	{
		this.ultraSoundEDD = ultraSoundEDD;
	}

	public String getHistPregData()
	{
		return histPregData;
	}

	public void setHistPregData(String histPregData)
	{
		this.histPregData = histPregData;
	}

	public String getMenstrualCycleDays()
	{
		return menstrualCycleDays;
	}

	public void setMenstrualCycleDays(String menstrualCycleDays)
	{
		this.menstrualCycleDays = menstrualCycleDays;
	}

	public String getIsContraceptiveUsed()
	{
		return isContraceptiveUsed;
	}

	public void setIsContraceptiveUsed(String isContraceptiveUsed)
	{
		this.isContraceptiveUsed = isContraceptiveUsed;
	}

	public String getContraceptiveRemarks()
	{
		return contraceptiveRemarks;
	}

	public void setContraceptiveRemarks(String contraceptiveRemarks)
	{
		this.contraceptiveRemarks = contraceptiveRemarks;
	}

	public String[] getHistDeliveryTypeId()
	{
		return histDeliveryTypeId;
	}

	public void setHistDeliveryTypeId(String[] histDeliveryTypeId)
	{
		this.histDeliveryTypeId = histDeliveryTypeId;
	}

	public String[] getHistIsAntiDGiven()
	{
		return histIsAntiDGiven;
	}

	public void setHistIsAntiDGiven(String[] histIsAntiDGiven)
	{
		this.histIsAntiDGiven = histIsAntiDGiven;
	}

	public String[] getHistBabyBloodGroupCode()
	{
		return histBabyBloodGroupCode;
	}

	public void setHistBabyBloodGroupCode(String[] histBabyBloodGroupCode)
	{
		this.histBabyBloodGroupCode = histBabyBloodGroupCode;
	}

	public String getDeliveryTypeList()
	{
		return deliveryTypeList;
	}

	public void setDeliveryTypeList(String deliveryTypeList)
	{
		this.deliveryTypeList = deliveryTypeList;
	}

	public String getAncDetailGetFlag()
	{
		return ancDetailGetFlag;
	}

	public void setAncDetailGetFlag(String ancDetailGetFlag)
	{
		this.ancDetailGetFlag = ancDetailGetFlag;
	}

	public String getBloodGroupList()
	{
		return bloodGroupList;
	}

	public void setBloodGroupList(String bloodGroupList)
	{
		this.bloodGroupList = bloodGroupList;
	}

	public String[] getHistNoOfBirths()
	{
		return histNoOfBirths;
	}

	public void setHistNoOfBirths(String[] histNoOfBirths)
	{
		this.histNoOfBirths = histNoOfBirths;
	}

	public String getDetectionMethod()
	{
		return detectionMethod;
	}

	public void setDetectionMethod(String detectionMethod)
	{
		this.detectionMethod = detectionMethod;
	}

	public String getDetectionDate()
	{
		return detectionDate;
	}

	public void setDetectionDate(String detectionDate)
	{
		this.detectionDate = detectionDate;
	}

	public String getAncEpisodeCode()
	{
		return ancEpisodeCode;
	}

	public void setAncEpisodeCode(String ancEpisodeCode)
	{
		this.ancEpisodeCode = ancEpisodeCode;
	}

	public String getAncEpisodeUnitCode()
	{
		return ancEpisodeUnitCode;
	}

	public void setAncEpisodeUnitCode(String ancEpisodeUnitCode)
	{
		this.ancEpisodeUnitCode = ancEpisodeUnitCode;
	}

	public String getAncEpisodeUnit()
	{
		return ancEpisodeUnit;
	}

	public void setAncEpisodeUnit(String ancEpisodeUnit)
	{
		this.ancEpisodeUnit = ancEpisodeUnit;
	}

	public String getIsAntiDGiven()
	{
		return isAntiDGiven;
	}

	public void setIsAntiDGiven(String isAntiDGiven)
	{
		this.isAntiDGiven = isAntiDGiven;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getEndANCDetailFlag()
	{
		return endANCDetailFlag;
	}

	public void setEndANCDetailFlag(String endANCDetailFlag)
	{
		this.endANCDetailFlag = endANCDetailFlag;
	}

	public String getPatAge()
	{
		return patAge;
	}

	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	public String getMacroProcessId()
	{
		return macroProcessId;
	}

	public void setMacroProcessId(String macroProcessId)
	{
		this.macroProcessId = macroProcessId;
	}

	public String getMacroHead()
	{
		return macroHead;
	}

	public void setMacroHead(String macroHead)
	{
		this.macroHead = macroHead;
	}
}
