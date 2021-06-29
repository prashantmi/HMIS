package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SummonDtlFB extends ActionForm
{
	private String hmode;
	private String summonTypeId;
	private String summonDateTime;
	private String recevingDate;
	private String recevingTimeHr;
	private String recevingTimeMin;
	private String hearingDate;
	private String hearingTimeHr;
	private String hearingTimeMin;
	private String courtName;
	private String judgeName;
	private String courtAddress;
	private String constableName;
	private String constableDesig;
	private String constableBadgeNo;
	private String caseNo;
	private String policeStation;
	private String CIDNo;
	private String empName;
	private String summonRemarks;
	private String postmortemId;
	private String MLCNo;
	private String patCrNo;
	private String patAddress;
	private String motherName;
	private String spouseName;
	private String patGenderCode;
	private String fatherName;
	private String age;
	private String patName;
	private String patAgeType;
	private String patGender;
	private String patDOB;
	
	private String sysHour;
	private String sysMinute;
	private String sysDate;
	private String summonFlag;
	
	private String episodeCode;
	private String episodeVisitNo;
	
	private String searchType;
	private String str_first_name;
	private String str_middle_name;
	private String str_last_name;
	private String str_pat_crno;
	private String str_postmortem_no;
	private String str_mlc_no;
	private String str_admission_no;
	
	private String[] patNameArray;
	private String[] fatherNameArray;
	private String[] spouseNameArray;
	private String[] motherNameArray;
	private String[] patGenderArray;
	private String[] patAgeArray;
	private String[] patAddressArray;
	private String[] crNoArray;
	private String[] postMortemNoArray;
	private String[] mlcNoArray;
	private String[] patGenderCodeArray; 
	private String[] patDOBArray; 
	private String[] episodeCodeArray;
	private String[] episodeVisitNoArray; 
	private String[] patAdmissionNoArray;
	
	private String[] postMortem_flagArray;
	private String[] mlc_flagArray; 
	private String[] mlc_patNameArray;
	private String[] mlc_fatherNameArray;
	private String[] mlc_spouseNameArray;
	private String[] mlc_motherNameArray;
	private String[] mlc_patGenderArray;
	private String[] mlc_patAgeArray;
	private String[] mlc_patAddressArray;
	private String[] mlc_crNoArray;
	private String[] mlc_postMortemNoArray;
	private String[] mlc_mlcNoArray;
	private String[] mlc_patGenderCodeArray; 
	private String[] mlc_patDOBArray; 
	private String[] mlc_episodeCodeArray;
	private String[] mlc_episodeVisitNoArray; 
	private String[] mlc_admissionNoArray;
	
	private String[] admission_flagArray;
	private String[] adm_patAdmNoArray;
	private String[] adm_patNameArray;
	private String[] adm_fatherNameArray;
	private String[] adm_spouseNameArray;
	private String[] adm_motherNameArray;
	private String[] adm_patGenderArray;
	private String[] adm_patAgeArray;
	private String[] adm_patAddressArray;
	private String[] adm_crNoArray;
	private String[] adm_mlcNoArray;
	private String[] adm_postMortemNoArray; 
	
	
	private String[] CIDNoArray;
	private String[] recevingDateArray;
	private String[] recevingTimeHoursArray;
	private String[] recevingTimeMinuteArray;
	private String cidNoFlag;
	
	private String patAdmNo;
	
	
	
	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getCidNoFlag() {
		return cidNoFlag;
	}

	public void setCidNoFlag(String cidNoFlag) {
		this.cidNoFlag = cidNoFlag;
	}

	public String[] getCIDNoArray() {
		return CIDNoArray;
	}

	public void setCIDNoArray(String[] noArray) {
		CIDNoArray = noArray;
	}

	public String[] getEpisodeCodeArray() {
		return episodeCodeArray;
	}

	public void setEpisodeCodeArray(String[] episodeCodeArray) {
		this.episodeCodeArray = episodeCodeArray;
	}

	public String[] getEpisodeVisitNoArray() {
		return episodeVisitNoArray;
	}

	public void setEpisodeVisitNoArray(String[] episodeVisitNoArray) {
		this.episodeVisitNoArray = episodeVisitNoArray;
	}

	public String[] getPatDOBArray() {
		return patDOBArray;
	}

	public void setPatDOBArray(String[] patDOBArray) {
		this.patDOBArray = patDOBArray;
	}

	public String[] getPatGenderCodeArray() {
		return patGenderCodeArray;
	}

	public void setPatGenderCodeArray(String[] patGenderCodeArray) {
		this.patGenderCodeArray = patGenderCodeArray;
	}

	public String getStr_first_name() {
		return str_first_name;
	}

	public void setStr_first_name(String str_first_name) {
		this.str_first_name = str_first_name;
	}

	public String getStr_middle_name() {
		return str_middle_name;
	}

	public void setStr_middle_name(String str_middle_name) {
		this.str_middle_name = str_middle_name;
	}

	public String getStr_last_name() {
		return str_last_name;
	}

	public void setStr_last_name(String str_last_name) {
		this.str_last_name = str_last_name;
	}

	public String getStr_pat_crno() {
		return str_pat_crno;
	}

	public void setStr_pat_crno(String str_pat_crno) {
		this.str_pat_crno = str_pat_crno;
	}

	public String getStr_postmortem_no() {
		return str_postmortem_no;
	}

	public void setStr_postmortem_no(String str_postmortem_no) {
		this.str_postmortem_no = str_postmortem_no;
	}

	public String getStr_mlc_no() {
		return str_mlc_no;
	}

	public void setStr_mlc_no(String str_mlc_no) {
		this.str_mlc_no = str_mlc_no;
	}

	public String getSummonFlag() {
		return summonFlag;
	}

	public void setSummonFlag(String summonFlag) {
		this.summonFlag = summonFlag;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.age="";
		this.caseNo="";
		this.CIDNo="";
		this.constableBadgeNo="";
		this.constableName="";
		this.courtAddress="";
		this.courtName="";
		this.empName="";
		this.fatherName="";
		this.hearingDate="";
		this.hearingTimeHr="";
		this.hearingTimeMin="";
		this.judgeName="";
		this.MLCNo="";
		this.motherName="";
		this.patAddress="";
		this.patCrNo="";
		this.patGenderCode="-1";
		this.patName="";
		this.policeStation="";
		this.postmortemId="";
		this.recevingDate="";
		this.recevingTimeHr="";
		this.recevingTimeMin="";
		this.spouseName="";
		this.summonDateTime="";
		this.summonRemarks="";
		this.summonTypeId="-1";
		this.patAgeType="-1";
		
	}
	
	public String getSysHour() {
		return sysHour;
	}
	public void setSysHour(String sysHour) {
		this.sysHour = sysHour;
	}
	public String getSysMinute() {
		return sysMinute;
	}
	public void setSysMinute(String sysMinute) {
		this.sysMinute = sysMinute;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSummonTypeId() {
		return summonTypeId;
	}
	public void setSummonTypeId(String summonTypeId) {
		this.summonTypeId = summonTypeId;
	}
	public String getSummonDateTime() {
		return summonDateTime;
	}
	public void setSummonDateTime(String summonDateTime) {
		this.summonDateTime = summonDateTime;
	}
	public String getRecevingDate() {
		return recevingDate;
	}
	public void setRecevingDate(String recevingDate) {
		this.recevingDate = recevingDate;
	}
	public String getRecevingTimeHr() {
		return recevingTimeHr;
	}
	public void setRecevingTimeHr(String recevingTimeHr) {
		this.recevingTimeHr = recevingTimeHr;
	}
	public String getRecevingTimeMin() {
		return recevingTimeMin;
	}
	public void setRecevingTimeMin(String recevingTimeMin) {
		this.recevingTimeMin = recevingTimeMin;
	}
	public String getHearingDate() {
		return hearingDate;
	}
	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}
	public String getHearingTimeHr() {
		return hearingTimeHr;
	}
	public void setHearingTimeHr(String hearingTimeHr) {
		this.hearingTimeHr = hearingTimeHr;
	}
	public String getHearingTimeMin() {
		return hearingTimeMin;
	}
	public void setHearingTimeMin(String hearingTimeMin) {
		this.hearingTimeMin = hearingTimeMin;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getJudgeName() {
		return judgeName;
	}
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}
	public String getCourtAddress() {
		return courtAddress;
	}
	public void setCourtAddress(String courtAddress) {
		this.courtAddress = courtAddress;
	}
	public String getConstableName() {
		return constableName;
	}
	public void setConstableName(String constableName) {
		this.constableName = constableName;
	}
	public String getConstableDesig() {
		return constableDesig;
	}
	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}
	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}
	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getCIDNo() {
		return CIDNo;
	}
	public void setCIDNo(String no) {
		CIDNo = no;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getSummonRemarks() {
		return summonRemarks;
	}
	public void setSummonRemarks(String summonRemarks) {
		this.summonRemarks = summonRemarks;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getMLCNo() {
		return MLCNo;
	}
	public void setMLCNo(String no) {
		MLCNo = no;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPatAgeType() {
		return patAgeType;
	}

	public void setPatAgeType(String patAgeType) {
		this.patAgeType = patAgeType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getPatGender() {
		return patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

		public String[] getPatNameArray() {
		return patNameArray;
	}

	public void setPatNameArray(String[] patNameArray) {
		this.patNameArray = patNameArray;
	}

	public String[] getFatherNameArray() {
		return fatherNameArray;
	}

	public void setFatherNameArray(String[] fatherNameArray) {
		this.fatherNameArray = fatherNameArray;
	}

	public String[] getSpouseNameArray() {
		return spouseNameArray;
	}

	public void setSpouseNameArray(String[] spouseNameArray) {
		this.spouseNameArray = spouseNameArray;
	}

	public String[] getMotherNameArray() {
		return motherNameArray;
	}

	public void setMotherNameArray(String[] motherNameArray) {
		this.motherNameArray = motherNameArray;
	}

	public String[] getPatGenderArray() {
		return patGenderArray;
	}

	public void setPatGenderArray(String[] patGenderArray) {
		this.patGenderArray = patGenderArray;
	}

	public String[] getPatAgeArray() {
		return patAgeArray;
	}

	public void setPatAgeArray(String[] patAgeArray) {
		this.patAgeArray = patAgeArray;
	}

	public String[] getPatAddressArray() {
		return patAddressArray;
	}

	public void setPatAddressArray(String[] patAddressArray) {
		this.patAddressArray = patAddressArray;
	}

	public String[] getCrNoArray() {
		return crNoArray;
	}

	public void setCrNoArray(String[] crNoArray) {
		this.crNoArray = crNoArray;
	}

	public String[] getPostMortemNoArray() {
		return postMortemNoArray;
	}

	public void setPostMortemNoArray(String[] postMortemNoArray) {
		this.postMortemNoArray = postMortemNoArray;
	}

	public String[] getMlcNoArray() {
		return mlcNoArray;
	}

	public void setMlcNoArray(String[] mlcNoArray) {
		this.mlcNoArray = mlcNoArray;
	}

	public String getPatDOB() {
		return patDOB;
	}

	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String[] getMlc_patNameArray() {
		return mlc_patNameArray;
	}

	public void setMlc_patNameArray(String[] mlc_patNameArray) {
		this.mlc_patNameArray = mlc_patNameArray;
	}

	public String[] getMlc_fatherNameArray() {
		return mlc_fatherNameArray;
	}

	public void setMlc_fatherNameArray(String[] mlc_fatherNameArray) {
		this.mlc_fatherNameArray = mlc_fatherNameArray;
	}

	public String[] getMlc_spouseNameArray() {
		return mlc_spouseNameArray;
	}

	public void setMlc_spouseNameArray(String[] mlc_spouseNameArray) {
		this.mlc_spouseNameArray = mlc_spouseNameArray;
	}

	public String[] getMlc_motherNameArray() {
		return mlc_motherNameArray;
	}

	public void setMlc_motherNameArray(String[] mlc_motherNameArray) {
		this.mlc_motherNameArray = mlc_motherNameArray;
	}

	public String[] getMlc_patGenderArray() {
		return mlc_patGenderArray;
	}

	public void setMlc_patGenderArray(String[] mlc_patGenderArray) {
		this.mlc_patGenderArray = mlc_patGenderArray;
	}

	public String[] getMlc_patAgeArray() {
		return mlc_patAgeArray;
	}

	public void setMlc_patAgeArray(String[] mlc_patAgeArray) {
		this.mlc_patAgeArray = mlc_patAgeArray;
	}

	public String[] getMlc_patAddressArray() {
		return mlc_patAddressArray;
	}

	public void setMlc_patAddressArray(String[] mlc_patAddressArray) {
		this.mlc_patAddressArray = mlc_patAddressArray;
	}

	public String[] getMlc_crNoArray() {
		return mlc_crNoArray;
	}

	public void setMlc_crNoArray(String[] mlc_crNoArray) {
		this.mlc_crNoArray = mlc_crNoArray;
	}

	public String[] getMlc_postMortemNoArray() {
		return mlc_postMortemNoArray;
	}

	public void setMlc_postMortemNoArray(String[] mlc_postMortemNoArray) {
		this.mlc_postMortemNoArray = mlc_postMortemNoArray;
	}

	public String[] getMlc_mlcNoArray() {
		return mlc_mlcNoArray;
	}

	public void setMlc_mlcNoArray(String[] mlc_mlcNoArray) {
		this.mlc_mlcNoArray = mlc_mlcNoArray;
	}

	public String[] getMlc_patGenderCodeArray() {
		return mlc_patGenderCodeArray;
	}

	public void setMlc_patGenderCodeArray(String[] mlc_patGenderCodeArray) {
		this.mlc_patGenderCodeArray = mlc_patGenderCodeArray;
	}

	public String[] getMlc_patDOBArray() {
		return mlc_patDOBArray;
	}

	public void setMlc_patDOBArray(String[] mlc_patDOBArray) {
		this.mlc_patDOBArray = mlc_patDOBArray;
	}

	public String[] getMlc_episodeCodeArray() {
		return mlc_episodeCodeArray;
	}

	public void setMlc_episodeCodeArray(String[] mlc_episodeCodeArray) {
		this.mlc_episodeCodeArray = mlc_episodeCodeArray;
	}

	public String[] getMlc_episodeVisitNoArray() {
		return mlc_episodeVisitNoArray;
	}

	public void setMlc_episodeVisitNoArray(String[] mlc_episodeVisitNoArray) {
		this.mlc_episodeVisitNoArray = mlc_episodeVisitNoArray;
	}

	public String[] getPostMortem_flagArray() {
		return postMortem_flagArray;
	}

	public void setPostMortem_flagArray(String[] postMortem_flagArray) {
		this.postMortem_flagArray = postMortem_flagArray;
	}

	public String[] getMlc_flagArray() {
		return mlc_flagArray;
	}

	public void setMlc_flagArray(String[] mlc_flagArray) {
		this.mlc_flagArray = mlc_flagArray;
	}

	public String[] getRecevingDateArray() {
		return recevingDateArray;
	}

	public void setRecevingDateArray(String[] recevingDateArray) {
		this.recevingDateArray = recevingDateArray;
	}

	public String[] getRecevingTimeHoursArray() {
		return recevingTimeHoursArray;
	}

	public void setRecevingTimeHoursArray(String[] recevingTimeHoursArray) {
		this.recevingTimeHoursArray = recevingTimeHoursArray;
	}

	public String[] getRecevingTimeMinuteArray() {
		return recevingTimeMinuteArray;
	}

	public void setRecevingTimeMinuteArray(String[] recevingTimeMinuteArray) {
		this.recevingTimeMinuteArray = recevingTimeMinuteArray;
	}

	public String getStr_admission_no() {
		return str_admission_no;
	}

	public void setStr_admission_no(String str_admission_no) {
		this.str_admission_no = str_admission_no;
	}

	public String[] getAdmission_flagArray() {
		return admission_flagArray;
	}

	public void setAdmission_flagArray(String[] admission_flagArray) {
		this.admission_flagArray = admission_flagArray;
	}

	public String[] getAdm_patNameArray() {
		return adm_patNameArray;
	}

	public void setAdm_patNameArray(String[] adm_patNameArray) {
		this.adm_patNameArray = adm_patNameArray;
	}

	public String[] getAdm_fatherNameArray() {
		return adm_fatherNameArray;
	}

	public void setAdm_fatherNameArray(String[] adm_fatherNameArray) {
		this.adm_fatherNameArray = adm_fatherNameArray;
	}

	public String[] getAdm_spouseNameArray() {
		return adm_spouseNameArray;
	}

	public void setAdm_spouseNameArray(String[] adm_spouseNameArray) {
		this.adm_spouseNameArray = adm_spouseNameArray;
	}

	public String[] getAdm_motherNameArray() {
		return adm_motherNameArray;
	}

	public void setAdm_motherNameArray(String[] adm_motherNameArray) {
		this.adm_motherNameArray = adm_motherNameArray;
	}

	public String[] getAdm_patGenderArray() {
		return adm_patGenderArray;
	}

	public void setAdm_patGenderArray(String[] adm_patGenderArray) {
		this.adm_patGenderArray = adm_patGenderArray;
	}

	public String[] getAdm_patAgeArray() {
		return adm_patAgeArray;
	}

	public void setAdm_patAgeArray(String[] adm_patAgeArray) {
		this.adm_patAgeArray = adm_patAgeArray;
	}

	public String[] getAdm_patAddressArray() {
		return adm_patAddressArray;
	}

	public void setAdm_patAddressArray(String[] adm_patAddressArray) {
		this.adm_patAddressArray = adm_patAddressArray;
	}

	public String[] getAdm_crNoArray() {
		return adm_crNoArray;
	}

	public void setAdm_crNoArray(String[] adm_crNoArray) {
		this.adm_crNoArray = adm_crNoArray;
	}

	public String[] getAdm_patAdmNoArray() {
		return adm_patAdmNoArray;
	}

	public void setAdm_patAdmNoArray(String[] adm_patAdmNoArray) {
		this.adm_patAdmNoArray = adm_patAdmNoArray;
	}

	public String[] getAdm_mlcNoArray() {
		return adm_mlcNoArray;
	}

	public void setAdm_mlcNoArray(String[] adm_mlcNoArray) {
		this.adm_mlcNoArray = adm_mlcNoArray;
	}

	public String[] getAdm_postMortemNoArray() {
		return adm_postMortemNoArray;
	}

	public void setAdm_postMortemNoArray(String[] adm_postMortemNoArray) {
		this.adm_postMortemNoArray = adm_postMortemNoArray;
	}

	public String[] getPatAdmissionNoArray() {
		return patAdmissionNoArray;
	}

	public void setPatAdmissionNoArray(String[] patAdmissionNoArray) {
		this.patAdmissionNoArray = patAdmissionNoArray;
	}

	public String[] getMlc_admissionNoArray() {
		return mlc_admissionNoArray;
	}

	public void setMlc_admissionNoArray(String[] mlc_admissionNoArray) {
		this.mlc_admissionNoArray = mlc_admissionNoArray;
	}


		
}
