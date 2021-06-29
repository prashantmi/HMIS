package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ANCDeliveryDetailFB extends ActionForm
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
	
	private String ancEpisodeCode;
	private String ancEpisodeUnitCode;
	private String ancEpisodeUnit;
	
	private String bookedStatus;

	// Other Required ANC Details
	private String parityNo;
	private String abortusNo;
	private String lmpDate;
	private String expectedDeliveryDate;
	private String ultraSoundEDD;
	private String gestationStartDate;
	private String gestationPeriod;
	private String isHighriskPregnancy;
	private String complications;
	private String queckeningWeek;
	private String queckeningRemarks;
	private String isContraceptiveUsed;
	private String contraceptiveRemarks;
	private String detectionMethod;
	private String detectionDate;
	private String isAntiDGiven;

	
	// Pregnancy Detail
	private String gravidaNo;
	private String pregnancyDuration;
	private String deliveryDate;
	private String deliveryStatus;
	private String deliveryPlaceId;
	private String deliveryPlace;
	private String labourRoomId;
	private String labourRoomAreaId;

	// Ruputre Detail
	private String ruptureType;
	private String ruptureDuration;
	private String ruptureDateTime;
	private String ruptureDate;
	private String ruptureTime;

	// Induction Detail
	private String isInduced;
	private String indicationOfInduction;
	private String inductionMethodId;
	private String inductionMethod;
	
	// Labour Detail
	private String labourStage1Duration;
	private String labourStage2Duration;
	private String labourStage3Duration;
	private String labourDuration;
	private String labourStage1Remarks;
	private String labourStage2Remarks;
	private String labourStage3Remarks;
	private String labourRemarks;

	// Delivery Detail
	private String deliveryTypeId;
	private String deliveryType;
	private String indicationOfDeliveyType;

	// Placenta Detail
	private String placentaWeight;
	private String placentaTypeId;
	private String placentaType;
	private String placentaMorphology;
	private String placentaHistopath;
	private String placentaRemovalMethodId;
	private String placentaRemovalMethod;

	// Other Details 
	private String isFoetalDistress;

	private String complicationId;
	private String complication;
	private String complicationRemarks;
	private String pregnancyRemarks;
	
	private String isEAS;				// -----?
	private String doctorId; // obstetrian Id
	
	private String motherStatus;
	private String deathCause;
	
	private String deliveryCount;

	// Delivery OutCome Detail
	private String[] birthDateTime;
	private String[] birthDate;
	private String[] birthTime;
	private String[] birthNatureId;
	private String[] birthGenderCode;
	private String[] birthWeight;
	private String[] birthDeathCause;
	private String[] birthDeathAge;
	private String[] birthPresentHealth;
	

	private String listLaborRoomArea;
	// ? --- 
	// 1. Is Anti-D Given
	// Liquor Status
	
	
	public ANCDeliveryDetailFB()
	{
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.deliveryCount="";
		this.birthWeight=null;
		this.birthPresentHealth=null;
		this.birthDeathAge=null;
		this.birthDeathCause=null;
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

	public String getBookedStatus()
	{
		return bookedStatus;
	}

	public void setBookedStatus(String bookedStatus)
	{
		this.bookedStatus = bookedStatus;
	}

	public String getGravidaNo()
	{
		return gravidaNo;
	}

	public void setGravidaNo(String gravidaNo)
	{
		this.gravidaNo = gravidaNo;
	}

	public String getPregnancyDuration()
	{
		return pregnancyDuration;
	}

	public void setPregnancyDuration(String pregnancyDuration)
	{
		this.pregnancyDuration = pregnancyDuration;
	}

	public String getDeliveryDate()
	{
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryStatus()
	{
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus)
	{
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryPlaceId()
	{
		return deliveryPlaceId;
	}

	public void setDeliveryPlaceId(String deliveryPlaceId)
	{
		this.deliveryPlaceId = deliveryPlaceId;
	}

	public String getDeliveryPlace()
	{
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace)
	{
		this.deliveryPlace = deliveryPlace;
	}

	public String getLabourRoomAreaId()
	{
		return labourRoomAreaId;
	}

	public void setLabourRoomAreaId(String labourRoomAreaId)
	{
		this.labourRoomAreaId = labourRoomAreaId;
	}

	public String getRuptureType()
	{
		return ruptureType;
	}

	public void setRuptureType(String ruptureType)
	{
		this.ruptureType = ruptureType;
	}

	public String getRuptureDuration()
	{
		return ruptureDuration;
	}

	public void setRuptureDuration(String ruptureDuration)
	{
		this.ruptureDuration = ruptureDuration;
	}

	public String getRuptureDateTime()
	{
		return ruptureDateTime;
	}

	public void setRuptureDateTime(String ruptureDateTime)
	{
		this.ruptureDateTime = ruptureDateTime;
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

	public String getLabourStage1Duration()
	{
		return labourStage1Duration;
	}

	public void setLabourStage1Duration(String labourStage1Duration)
	{
		this.labourStage1Duration = labourStage1Duration;
	}

	public String getLabourStage2Duration()
	{
		return labourStage2Duration;
	}

	public void setLabourStage2Duration(String labourStage2Duration)
	{
		this.labourStage2Duration = labourStage2Duration;
	}

	public String getLabourStage3Duration()
	{
		return labourStage3Duration;
	}

	public void setLabourStage3Duration(String labourStage3Duration)
	{
		this.labourStage3Duration = labourStage3Duration;
	}

	public String getLabourDuration()
	{
		return labourDuration;
	}

	public void setLabourDuration(String labourDuration)
	{
		this.labourDuration = labourDuration;
	}

	public String getLabourStage1Remarks()
	{
		return labourStage1Remarks;
	}

	public void setLabourStage1Remarks(String labourStage1Remarks)
	{
		this.labourStage1Remarks = labourStage1Remarks;
	}

	public String getLabourStage2Remarks()
	{
		return labourStage2Remarks;
	}

	public void setLabourStage2Remarks(String labourStage2Remarks)
	{
		this.labourStage2Remarks = labourStage2Remarks;
	}

	public String getLabourStage3Remarks()
	{
		return labourStage3Remarks;
	}

	public void setLabourStage3Remarks(String labourStage3Remarks)
	{
		this.labourStage3Remarks = labourStage3Remarks;
	}

	public String getLabourRemarks()
	{
		return labourRemarks;
	}

	public void setLabourRemarks(String labourRemarks)
	{
		this.labourRemarks = labourRemarks;
	}

	public String getDeliveryTypeId()
	{
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(String deliveryTypeId)
	{
		this.deliveryTypeId = deliveryTypeId;
	}

	public String getDeliveryType()
	{
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType)
	{
		this.deliveryType = deliveryType;
	}

	public String getIndicationOfDeliveyType()
	{
		return indicationOfDeliveyType;
	}

	public void setIndicationOfDeliveyType(String indicationOfDeliveyType)
	{
		this.indicationOfDeliveyType = indicationOfDeliveyType;
	}

	public String getPlacentaWeight()
	{
		return placentaWeight;
	}

	public void setPlacentaWeight(String placentaWeight)
	{
		this.placentaWeight = placentaWeight;
	}

	public String getPlacentaTypeId()
	{
		return placentaTypeId;
	}

	public void setPlacentaTypeId(String placentaTypeId)
	{
		this.placentaTypeId = placentaTypeId;
	}

	public String getPlacentaType()
	{
		return placentaType;
	}

	public void setPlacentaType(String placentaType)
	{
		this.placentaType = placentaType;
	}

	public String getPlacentaMorphology()
	{
		return placentaMorphology;
	}

	public void setPlacentaMorphology(String placentaMorphology)
	{
		this.placentaMorphology = placentaMorphology;
	}

	public String getPlacentaHistopath()
	{
		return placentaHistopath;
	}

	public void setPlacentaHistopath(String placentaHistopath)
	{
		this.placentaHistopath = placentaHistopath;
	}

	public String getPlacentaRemovalMethod()
	{
		return placentaRemovalMethod;
	}

	public void setPlacentaRemovalMethod(String placentaRemovalMethod)
	{
		this.placentaRemovalMethod = placentaRemovalMethod;
	}

	public String getIsFoetalDistress()
	{
		return isFoetalDistress;
	}

	public void setIsFoetalDistress(String isFoetalDistress)
	{
		this.isFoetalDistress = isFoetalDistress;
	}

	public String getComplicationId()
	{
		return complicationId;
	}

	public void setComplicationId(String complicationId)
	{
		this.complicationId = complicationId;
	}

	public String getComplication()
	{
		return complication;
	}

	public void setComplication(String complication)
	{
		this.complication = complication;
	}

	public String getComplicationRemarks()
	{
		return complicationRemarks;
	}

	public void setComplicationRemarks(String complicationRemarks)
	{
		this.complicationRemarks = complicationRemarks;
	}

	public String getPregnancyRemarks()
	{
		return pregnancyRemarks;
	}

	public void setPregnancyRemarks(String pregnancyRemarks)
	{
		this.pregnancyRemarks = pregnancyRemarks;
	}

	public String getIsEAS()
	{
		return isEAS;
	}

	public void setIsEAS(String isEAS)
	{
		this.isEAS = isEAS;
	}

	public String getDoctorId()
	{
		return doctorId;
	}

	public void setDoctorId(String doctorId)
	{
		this.doctorId = doctorId;
	}

	public String getMotherStatus()
	{
		return motherStatus;
	}

	public void setMotherStatus(String motherStatus)
	{
		this.motherStatus = motherStatus;
	}

	public String getDeathCause()
	{
		return deathCause;
	}

	public void setDeathCause(String deathCause)
	{
		this.deathCause = deathCause;
	}

	public String getRuptureDate()
	{
		return ruptureDate;
	}

	public void setRuptureDate(String ruptureDate)
	{
		this.ruptureDate = ruptureDate;
	}

	public String getRuptureTime()
	{
		return ruptureTime;
	}

	public void setRuptureTime(String ruptureTime)
	{
		this.ruptureTime = ruptureTime;
	}

	public String getLabourRoomId()
	{
		return labourRoomId;
	}

	public void setLabourRoomId(String labourRoomId)
	{
		this.labourRoomId = labourRoomId;
	}

	public String getListLaborRoomArea()
	{
		return listLaborRoomArea;
	}

	public void setListLaborRoomArea(String listLaborRoomArea)
	{
		this.listLaborRoomArea = listLaborRoomArea;
	}

	public String getPlacentaRemovalMethodId()
	{
		return placentaRemovalMethodId;
	}

	public void setPlacentaRemovalMethodId(String placentaRemovalMethodId)
	{
		this.placentaRemovalMethodId = placentaRemovalMethodId;
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

	public String getUltraSoundEDD()
	{
		return ultraSoundEDD;
	}

	public void setUltraSoundEDD(String ultraSoundEDD)
	{
		this.ultraSoundEDD = ultraSoundEDD;
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

	public String getIsAntiDGiven()
	{
		return isAntiDGiven;
	}

	public void setIsAntiDGiven(String isAntiDGiven)
	{
		this.isAntiDGiven = isAntiDGiven;
	}

	public String getDeliveryCount()
	{
		return deliveryCount;
	}

	public void setDeliveryCount(String deliveryCount)
	{
		this.deliveryCount = deliveryCount;
	}

	public String[] getBirthDateTime()
	{
		return birthDateTime;
	}

	public void setBirthDateTime(String[] birthDateTime)
	{
		this.birthDateTime = birthDateTime;
	}

	public String[] getBirthNatureId()
	{
		return birthNatureId;
	}

	public void setBirthNatureId(String[] birthNatureId)
	{
		this.birthNatureId = birthNatureId;
	}

	public String[] getBirthGenderCode()
	{
		return birthGenderCode;
	}

	public void setBirthGenderCode(String[] birthGenderCode)
	{
		this.birthGenderCode = birthGenderCode;
	}

	public String[] getBirthWeight()
	{
		return birthWeight;
	}

	public void setBirthWeight(String[] birthWeight)
	{
		this.birthWeight = birthWeight;
	}

	public String[] getBirthDeathCause()
	{
		return birthDeathCause;
	}

	public void setBirthDeathCause(String[] birthDeathCause)
	{
		this.birthDeathCause = birthDeathCause;
	}

	public String[] getBirthDeathAge()
	{
		return birthDeathAge;
	}

	public void setBirthDeathAge(String[] birthDeathAge)
	{
		this.birthDeathAge = birthDeathAge;
	}

	public String[] getBirthPresentHealth()
	{
		return birthPresentHealth;
	}

	public void setBirthPresentHealth(String[] birthPresentHealth)
	{
		this.birthPresentHealth = birthPresentHealth;
	}

	public String[] getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(String[] birthDate)
	{
		this.birthDate = birthDate;
	}

	public String[] getBirthTime()
	{
		return birthTime;
	}

	public void setBirthTime(String[] birthTime)
	{
		this.birthTime = birthTime;
	}

	public String getEntryTime()
	{
		return entryTime;
	}

	public void setEntryTime(String entryTime)
	{
		this.entryTime = entryTime;
	}
}
