package ipd.transactions;
import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

public class DeathNotificationTransVO implements TransferObject{
	private static final long serialVersionUID = 02L;
	private String strCrNo="";
	private String strIsPregnant="0";
	private String strIsDelivery="";
	private String strIsSiftToMortuary="0";
	private String strMsgString = "";
	private String strErrMsg = "";
	private String strMsgType = "0";
	private String strNormalMsg = "";
	private String strAdmno="";
	private String strEpisodeCode="";
	private String strVistNo="";
	private String strAdmDate="";
	private String strDeptCode="";
	private String strDeptUnitCode="";
	private String strWardCode="";
	private String strRoomeCode="";
	private String strBedCode="";
	private String strIsNewBorn="";
	private String strInvalidCrNo = "0";
	private String strPatAdmCode="";
	private String strDead = "";
	private String strMlcNo=null;
	private String strDeathDate="";
	private String strOnsetDeathDate="";
	private String strDeathDateTime="";
	private String strOnsetDeathDateTime="";
	private String strCauseDeath="";
	private String strDeathManner="";
	private String strAntecedentCauseDeath="";
	private String strInjuryDetail="";
	private String strConsultant="";
	private String strShiftDateTime="";
	private String strConsultantValue="";
	private String strDeathMannerValue="";
	private String strDeathCauseValue="";
	private String strTransoutFlg="";
	private String strDeathCode="";
	private String strBedStatusVacantCode="";
	private String strDescpCauseDeath="";
	private String strVerifyDateTime="";
	private String strHospCode="";
	private String strSeatId="";
	private String strHandOverTo="";
	private String strHandOverDateTime="";
	private String strHandOverHour="";
	private String strHandOverMin="";
	private String strHandOverSec="";
	private String strHandOverAmPm="";
	
	private boolean fFemale=false;
	
	
	/**
	 * WebRowset
	 */
	private WebRowSet consultantWS=null;
	private WebRowSet deathMannerWS=null;
	private WebRowSet deathCauseWS=null;
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrIsPregnant() {
		return strIsPregnant;
	}
	public void setStrIsPregnant(String strIsPregnant) {
		this.strIsPregnant = strIsPregnant;
	}
	public String getStrIsDelivery() {
		return strIsDelivery;
	}
	public void setStrIsDelivery(String strIsDelivery) {
		this.strIsDelivery = strIsDelivery;
	}
	public String getStrIsSiftToMortuary() {
		return strIsSiftToMortuary;
	}
	public void setStrIsSiftToMortuary(String strIsSiftToMortuary) {
		this.strIsSiftToMortuary = strIsSiftToMortuary;
	}
	public WebRowSet getConsultantWS() {
		return consultantWS;
	}
	public void setConsultantWS(WebRowSet consultantWS) {
		this.consultantWS = consultantWS;
	}
	public WebRowSet getDeathMannerWS() {
		return deathMannerWS;
	}
	public void setDeathMannerWS(WebRowSet deathMannerWS) {
		this.deathMannerWS = deathMannerWS;
	}
	public WebRowSet getDeathCauseWS() {
		return deathCauseWS;
	}
	public void setDeathCauseWS(WebRowSet deathCauseWS) {
		this.deathCauseWS = deathCauseWS;
	}
	public String getStrAdmno() {
		return strAdmno;
	}
	public void setStrAdmno(String strAdmno) {
		this.strAdmno = strAdmno;
	}
	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}
	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}
	public String getStrVistNo() {
		return strVistNo;
	}
	public void setStrVistNo(String strVistNo) {
		this.strVistNo = strVistNo;
	}
	public String getStrAdmDate() {
		return strAdmDate;
	}
	public void setStrAdmDate(String strAdmDate) {
		this.strAdmDate = strAdmDate;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrRoomeCode() {
		return strRoomeCode;
	}
	public void setStrRoomeCode(String strRoomeCode) {
		this.strRoomeCode = strRoomeCode;
	}
	public String getStrBedCode() {
		return strBedCode;
	}
	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}
	public String getStrIsNewBorn() {
		return strIsNewBorn;
	}
	public void setStrIsNewBorn(String strIsNewBorn) {
		this.strIsNewBorn = strIsNewBorn;
	}
	public String getStrMlcNo() {
		return strMlcNo;
	}
	public void setStrMlcNo(String strMlcNo) {
		this.strMlcNo = strMlcNo;
	}
	public String getStrDeathDate() {
		return strDeathDate;
	}
	public void setStrDeathDate(String strDeathDate) {
		this.strDeathDate = strDeathDate;
	}
	public String getStrOnsetDeathDate() {
		return strOnsetDeathDate;
	}
	public void setStrOnsetDeathDate(String strOnsetDeathDate) {
		this.strOnsetDeathDate = strOnsetDeathDate;
	}
	public String getStrDeathDateTime() {
		return strDeathDateTime;
	}
	public void setStrDeathDateTime(String strDeathDateTime) {
		this.strDeathDateTime = strDeathDateTime;
	}
	public String getStrOnsetDeathDateTime() {
		return strOnsetDeathDateTime;
	}
	public void setStrOnsetDeathDateTime(String strOnsetDeathDateTime) {
		this.strOnsetDeathDateTime = strOnsetDeathDateTime;
	}
	public String getStrCauseDeath() {
		return strCauseDeath;
	}
	public void setStrCauseDeath(String strCauseDeath) {
		this.strCauseDeath = strCauseDeath;
	}
	public String getStrDeathManner() {
		return strDeathManner;
	}
	public void setStrDeathManner(String strDeathManner) {
		this.strDeathManner = strDeathManner;
	}
	public String getStrAntecedentCauseDeath() {
		return strAntecedentCauseDeath;
	}
	public void setStrAntecedentCauseDeath(String strAntecedentCauseDeath) {
		this.strAntecedentCauseDeath = strAntecedentCauseDeath;
	}
	public String getStrInjuryDetail() {
		return strInjuryDetail;
	}
	public void setStrInjuryDetail(String strInjuryDetail) {
		this.strInjuryDetail = strInjuryDetail;
	}
	public String getStrConsultant() {
		return strConsultant;
	}
	public void setStrConsultant(String strConsultant) {
		this.strConsultant = strConsultant;
	}
	public String getStrShiftDateTime() {
		return strShiftDateTime;
	}
	public void setStrShiftDateTime(String strShiftDateTime) {
		this.strShiftDateTime = strShiftDateTime;
	}
	public String getStrConsultantValue() {
		return strConsultantValue;
	}
	public void setStrConsultantValue(String strConsultantValue) {
		this.strConsultantValue = strConsultantValue;
	}
	public String getStrDeathMannerValue() {
		return strDeathMannerValue;
	}
	public void setStrDeathMannerValue(String strDeathMannerValue) {
		this.strDeathMannerValue = strDeathMannerValue;
	}
	public String getStrDeathCauseValue() {
		return strDeathCauseValue;
	}
	public void setStrDeathCauseValue(String strDeathCauseValue) {
		this.strDeathCauseValue = strDeathCauseValue;
	}
	public String getStrTransoutFlg() {
		return strTransoutFlg;
	}
	public void setStrTransoutFlg(String strTransoutFlg) {
		this.strTransoutFlg = strTransoutFlg;
	}
	public String getStrDeathCode() {
		return strDeathCode;
	}
	public void setStrDeathCode(String strDeathCode) {
		this.strDeathCode = strDeathCode;
	}
	public String getStrBedStatusVacantCode() {
		return strBedStatusVacantCode;
	}
	public void setStrBedStatusVacantCode(String strBedStatusVacantCode) {
		this.strBedStatusVacantCode = strBedStatusVacantCode;
	}
	public String getStrDescpCauseDeath() {
		return strDescpCauseDeath;
	}
	public void setStrDescpCauseDeath(String strDescpCauseDeath) {
		this.strDescpCauseDeath = strDescpCauseDeath;
	}
	public String getStrVerifyDateTime() {
		return strVerifyDateTime;
	}
	public void setStrVerifyDateTime(String strVerifyDateTime) {
		this.strVerifyDateTime = strVerifyDateTime;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @return the strPatAdmCode
	 */
	public String getStrPatAdmCode() {
		return strPatAdmCode;
	}
	/**
	 * @param strPatAdmCode the strPatAdmCode to set
	 */
	public void setStrPatAdmCode(String strPatAdmCode) {
		this.strPatAdmCode = strPatAdmCode;
	}
	/**
	 * @return the strDead
	 */
	public String getStrDead() {
		return strDead;
	}
	/**
	 * @param strDead the strDead to set
	 */
	public void setStrDead(String strDead) {
		this.strDead = strDead;
	}
	/**
	 * @return the fFemale
	 */
	public boolean isFFemale() {
		return fFemale;
	}
	/**
	 * @param female the fFemale to set
	 */
	public void setFFemale(boolean female) {
		fFemale = female;
	}
	/**
	 * @return the strHandOverTo
	 */
	public String getStrHandOverTo() {
		return strHandOverTo;
	}
	/**
	 * @param strHandOverTo the strHandOverTo to set
	 */
	public void setStrHandOverTo(String strHandOverTo) {
		this.strHandOverTo = strHandOverTo;
	}
	
	/**
	 * @return the strHandOverHour
	 */
	public String getStrHandOverHour() {
		return strHandOverHour;
	}
	/**
	 * @param strHandOverHour the strHandOverHour to set
	 */
	public void setStrHandOverHour(String strHandOverHour) {
		this.strHandOverHour = strHandOverHour;
	}
	/**
	 * @return the strHandOverMin
	 */
	public String getStrHandOverMin() {
		return strHandOverMin;
	}
	/**
	 * @param strHandOverMin the strHandOverMin to set
	 */
	public void setStrHandOverMin(String strHandOverMin) {
		this.strHandOverMin = strHandOverMin;
	}
	/**
	 * @return the strHandOverSec
	 */
	public String getStrHandOverSec() {
		return strHandOverSec;
	}
	/**
	 * @param strHandOverSec the strHandOverSec to set
	 */
	public void setStrHandOverSec(String strHandOverSec) {
		this.strHandOverSec = strHandOverSec;
	}
	/**
	 * @return the strHandOverAmPm
	 */
	public String getStrHandOverAmPm() {
		return strHandOverAmPm;
	}
	/**
	 * @param strHandOverAmPm the strHandOverAmPm to set
	 */
	public void setStrHandOverAmPm(String strHandOverAmPm) {
		this.strHandOverAmPm = strHandOverAmPm;
	}
	/**
	 * @return the strHandOverDateTime
	 */
	public String getStrHandOverDateTime() {
		return strHandOverDateTime;
	}
	/**
	 * @param strHandOverDateTime the strHandOverDateTime to set
	 */
	public void setStrHandOverDateTime(String strHandOverDateTime) {
		this.strHandOverDateTime = strHandOverDateTime;
	}
	/**
	 * @return the strInvalidCrNo
	 */
	public String getStrInvalidCrNo() {
		return strInvalidCrNo;
	}
	/**
	 * @param strInvalidCrNo the strInvalidCrNo to set
	 */
	public void setStrInvalidCrNo(String strInvalidCrNo) {
		this.strInvalidCrNo = strInvalidCrNo;
	}

}
