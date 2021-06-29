package registration.masters.controller.actionsupport;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class RegistrationConfigSUP extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware
{
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;

	@SuppressWarnings("rawtypes")
	protected Map mapSession;

	protected String strHospitalCode;
	protected String strconfigGroup;
	private String strtempReg;
	private String strmodechoice;
	private String strcrossconsult;
	private String strappointmentbs;
	private String strduplicacyChk;
	private String stradharintegration;
	private String stremgRenewal;
	private String strNoOfHrs;
	private String strseniorCitizenCatCode;
	private String strseniorCitizenAge;
	private String strmobileService;
	private String strimgUploadMode;
	
	protected String   strconfigGroupOpd;
	protected String   strconfigGroupSpl;
	protected String   strconfigGroupEmg;
	
	private String strtempRegOpd;
	private String strmodechoiceOpd;
	private String strcrossconsultOpd;
	private String strappointmentbsOpd;
	private String strduplicacyChkOpd;
	private String stradharintegrationOpd;
	private String stremgRenewalOpd;
	private String strNoOfHrsOpd;
	private String strseniorCitizenCatCodeOpd;
	private String strseniorCitizenAgeOpd;
	private String strmobileServiceOpd;
	private String strimgUploadModeOpd;
	
	private String strtempRegSpl;
	private String strmodechoiceSpl;
	private String strcrossconsultSpl;
	private String strappointmentbsSpl;
	private String strduplicacyChkSpl;
	private String stradharintegrationSpl;
	private String stremgRenewalSpl;
	private String strNoOfHrsSpl;
	private String strseniorCitizenCatCodeSpl;
	private String strseniorCitizenAgeSpl;
	private String strmobileServiceSpl;
	private String strimgUploadModeSpl;
	
	private String strtempRegEmg;
	private String strmodechoiceEmg;
	private String strcrossconsultEmg;
	private String strappointmentbsEmg;
	private String strduplicacyChkEmg;
	private String stradharintegrationEmg;
	private String stremgRenewalEmg;
	private String strNoOfHrsEmg;
	private String strseniorCitizenCatCodeEmg;
	private String strseniorCitizenAgeEmg;
	private String strmobileServiceEmg;
	private String strimgUploadModeEmg;
	
	protected String flagAddMod=null;
	
	protected String strMsg=null;
	private int temp;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strErrorMsg;


/*	public void clear()
	{
		strRenewalGroup=null;
		strRenewalGroupGen=null;
		strRenewalGroupEmg=null;
		strRenewalGroupSpl=null;
		strHospitalCode=null;
		strRenewalType=null;
		strRenewalMode=null;
		strNoOfDays=null;
		strIsMultipleMonth=null;
		strMonth1=null;
		strMonth2=null;
		strMonth3=null;
		chkCommonCode=null;
		strRenewalTypeGen=null;
		strRenewalTypeSpl=null;
		strRenewalTypeEmg=null;
		strRenewalModeGen=null;
		strRenewalModeSpl=null;
		strRenewalModeEmg=null;

		strNoOfDaysGen=null;
		strNoOfDaysSpl=null;
		strNoOfDaysEmg=null;
		
		strMonth1Gen=null;
		strMonth1Spl=null;
		strMonth1Emg=null;
		
		strIsMultipleMonthGen=null;
		strIsMultipleMonthSpl=null;
		strIsMultipleMonthEmg=null;

		strMonth2Gen=null;
		strMonth2Spl=null;
		strMonth2Emg=null;

		strMonth3Gen=null;
		strMonth3Spl=null;
		strMonth3Emg=null;
		
		strDay1Gen=null;
		strDay1Emg=null;
		strDay1Spl=null;
		
		strDay2Gen=null;
		strDay2Emg=null;
		strDay2Spl=null;
		
		strDay3Gen=null;
		strDay3Spl=null;

		strDay3Emg=null;
		
		strDay1=null;
		strDay2=null;
		strDay3=null;
		//strMsg="";
		
		
	}*/
	public int getStrtemp() {
		return temp;
	}

	public void setStrtemp(int temp) {
		this.temp = temp;
	}

	public String getStrconfigGroup() {
		return strconfigGroup;
	}
	public void setStrconfigGroup(String strconfigGroup) {
		this.strconfigGroup = strconfigGroup;
	}
	public String getStrtempReg() {
		return strtempReg;
	}

	public void setStrtempReg(String strtempReg) {
		this.strtempReg = strtempReg;
	}

	public String getStrmodechoice() {
		return strmodechoice;
	}

	public void setStrmodechoice(String strmodechoice) {
		this.strmodechoice = strmodechoice;
	}

	public String getStrcrossconsult() {
		return strcrossconsult;
	}

	public void setStrcrossconsult(String strcrossconsult) {
		this.strcrossconsult = strcrossconsult;
	}

	public String getStrappointmentbs() {
		return strappointmentbs;
	}
	
	public void setStrappointmentbs(String strappointmentbs) {
		this.strappointmentbs = strappointmentbs;
	}

	public String getStrduplicacyChk() {
		return strduplicacyChk;
	}

	public void setStrduplicacyChk(String strduplicacyChk) {
		this.strduplicacyChk = strduplicacyChk;
	}

	public String getStradharintegration() {
		return stradharintegration;
	}

	public void setStradharintegration(String stradharintegration) {
		this.stradharintegration = stradharintegration;
	}

	public String getStremgRenewal() {
		return stremgRenewal;
	}

	public void setStremgRenewal(String stremgRenewal) {
		this.stremgRenewal = stremgRenewal;
	}

	public String getStrNoOfHrs() {
		return strNoOfHrs;
	}

	public void setStrNoOfHrs(String strNoOfHrs) {
		this.strNoOfHrs = strNoOfHrs;
	}

	public String getStrseniorCitizenCatCode() {
		return strseniorCitizenCatCodeOpd;
	}
	
	public void setStrseniorCitizenCatCode(String strseniorCitizenCatCode) {
		this.strseniorCitizenCatCode = strseniorCitizenCatCode;
	}

	public String getStrseniorCitizenAge() {
		return strseniorCitizenAge;
	}

	public void setStrseniorCitizenAge(String strseniorCitizenAge) {
		this.strseniorCitizenAge = strseniorCitizenAge;
	}

	public String getStrmobileService() {
		return strmobileService;
	}
	
	public void setStrmobileService(String strmobileService) {
		this.strmobileService = strmobileService;
	}
	
	public String getStrimgUploadMode() {
		return strimgUploadMode;
	}
	
	public void setStrimgUploadMode(String strimgUploadMode) {
		this.strimgUploadMode = strimgUploadMode;
	}
	
	public String getStrconfigGroupOpd() {
		return strconfigGroupOpd;
	}
	public void setStrconfigGroupOpd(String strconfigGroupOpd) {
		this.strconfigGroupOpd = strconfigGroupOpd;
	}
	public String getStrtempRegOpd() {
		return strtempRegOpd;
	}

	public void setStrtempRegOpd(String strtempRegOpd) {
		this.strtempRegOpd = strtempRegOpd;
	}

	public String getStrmodechoiceOpd() {
		return strmodechoiceOpd;
	}

	public void setStrmodechoiceOpd(String strmodechoiceOpd) {
		this.strmodechoiceOpd = strmodechoiceOpd;
	}

	public String getStrcrossconsultOpd() {
		return strcrossconsultOpd;
	}

	public void setStrcrossconsultOpd(String strcrossconsultOpd) {
		this.strcrossconsultOpd = strcrossconsultOpd;
	}

	public String getStrappointmentbsOpd() {
		return strappointmentbsOpd;
	}
	
	public void setStrappointmentbsOpd(String strappointmentbsOpd) {
		this.strappointmentbsOpd = strappointmentbsOpd;
	}

	public String getStrduplicacyChkOpd() {
		return strduplicacyChkOpd;
	}

	public void setStrduplicacyChkOpd(String strduplicacyChkOpd) {
		this.strduplicacyChkOpd = strduplicacyChkOpd;
	}

	public String getStradharintegrationOpd() {
		return stradharintegrationOpd;
	}

	public void setStradharintegrationOpd(String stradharintegrationOpd) {
		this.stradharintegrationOpd = stradharintegrationOpd;
	}

	public String getStremgRenewalOpd() {
		return stremgRenewalOpd;
	}

	public void setStremgRenewalOpd(String stremgRenewalOpd) {
		this.stremgRenewalOpd = stremgRenewalOpd;
	}

	public String getStrNoOfHrsOpd() {
		return strNoOfHrsOpd;
	}

	public void setStrNoOfHrsOpd(String strNoOfHrsOpd) {
		this.strNoOfHrsOpd = strNoOfHrsOpd;
	}

	public String getStrseniorCitizenCatCodeOpd() {
		return strseniorCitizenCatCodeOpd;
	}
	
	public void setStrseniorCitizenCatCodeOpd(String strseniorCitizenCatCodeOpd) {
		this.strseniorCitizenCatCodeOpd = strseniorCitizenCatCodeOpd;
	}

	public String getStrseniorCitizenAgeOpd() {
		return strseniorCitizenAgeOpd;
	}

	public void setStrseniorCitizenAgeOpd(String strseniorCitizenAgeOpd) {
		this.strseniorCitizenAgeOpd = strseniorCitizenAgeOpd;
	}

	public String getStrmobileServiceOpd() {
		return strmobileServiceOpd;
	}
	
	public void setStrmobileServiceOpd(String strmobileServiceOpd) {
		this.strmobileServiceOpd = strmobileServiceOpd;
	}
	
	public String getStrimgUploadModeOpd() {
		return strimgUploadModeOpd;
	}
	
	public void setStrimgUploadModeOpd(String strimgUploadModeOpd) {
		this.strimgUploadModeOpd = strimgUploadModeOpd;
	}
	
	
	public String getStrconfigGroupSpl() {
		return strconfigGroupSpl;
	}
	public void setStrconfigGroupSpl(String strconfigGroupSpl) {
		this.strconfigGroupSpl = strconfigGroupSpl;
	}
	public String getStrtempRegSpl() {
		return strtempRegSpl;
	}

	public void setStrtempRegSpl(String strtempRegSpl) {
		this.strtempRegSpl = strtempRegSpl;
	}

	public String getStrmodechoiceSpl() {
		return strmodechoiceSpl;
	}

	public void setStrmodechoiceSpl(String strmodechoiceSpl) {
		this.strmodechoiceSpl = strmodechoiceSpl;
	}

	public String getStrcrossconsultSpl() {
		return strcrossconsultSpl;
	}

	public void setStrcrossconsultSpl(String strcrossconsultSpl) {
		this.strcrossconsultSpl = strcrossconsultSpl;
	}

	public String getStrappointmentbsSpl() {
		return strappointmentbsSpl;
	}
	
	public void setStrappointmentbsSpl(String strappointmentbsSpl) {
		this.strappointmentbsSpl = strappointmentbsSpl;
	}

	public String getStrduplicacyChkSpl() {
		return strduplicacyChkSpl;
	}

	public void setStrduplicacyChkSpl(String strduplicacyChkSpl) {
		this.strduplicacyChkSpl = strduplicacyChkSpl;
	}

	public String getStradharintegrationSpl() {
		return stradharintegrationSpl;
	}

	public void setStradharintegrationSpl(String stradharintegrationSpl) {
		this.stradharintegrationSpl = stradharintegrationSpl;
	}

	public String getStremgRenewalSpl() {
		return stremgRenewalSpl;
	}

	public void setStremgRenewalSpl(String stremgRenewalSpl) {
		this.stremgRenewalSpl = stremgRenewalSpl;
	}

	public String getStrNoOfHrsSpl() {
		return strNoOfHrsSpl;
	}

	public void setStrNoOfHrsSpl(String strNoOfHrsSpl) {
		this.strNoOfHrsSpl = strNoOfHrsSpl;
	}

	public String getStrseniorCitizenCatCodeSpl() {
		return strseniorCitizenCatCodeSpl;
	}
	
	public void setStrseniorCitizenCatCodeSpl(String strseniorCitizenCatCodeSpl) {
		this.strseniorCitizenCatCodeSpl = strseniorCitizenCatCodeSpl;
	}

	public String getStrseniorCitizenAgeSpl() {
		return strseniorCitizenAgeSpl;
	}

	public void setStrseniorCitizenAgeSpl(String strseniorCitizenAgeSpl) {
		this.strseniorCitizenAgeSpl = strseniorCitizenAgeSpl;
	}

	public String getStrmobileServiceSpl() {
		return strmobileServiceSpl;
	}
	
	public void setStrmobileServiceSpl(String strmobileServiceSpl) {
		this.strmobileServiceSpl = strmobileServiceSpl;
	}
	
	public String getStrimgUploadModeSpl() {
		return strimgUploadModeSpl;
	}
	
	public void setStrimgUploadModeSpl(String strimgUploadModeSpl) {
		this.strimgUploadModeSpl = strimgUploadModeSpl;
	}
	
	
	public String getStrconfigGroupEmg() {
		return strconfigGroupEmg;
	}
	public void setStrconfigGroupEmg(String strconfigGroupEmg) {
		this.strconfigGroupEmg = strconfigGroupEmg;
	}
	public String getStrtempRegEmg() {
		return strtempRegEmg;
	}

	public void setStrtempRegEmg(String strtempRegEmg) {
		this.strtempRegEmg = strtempRegEmg;
	}

	public String getStrmodechoiceEmg() {
		return strmodechoiceEmg;
	}

	public void setStrmodechoiceEmg(String strmodechoiceEmg) {
		this.strmodechoiceEmg = strmodechoiceEmg;
	}

	public String getStrcrossconsultEmg() {
		return strcrossconsultEmg;
	}

	public void setStrcrossconsultEmg(String strcrossconsultEmg) {
		this.strcrossconsultEmg = strcrossconsultEmg;
	}

	public String getStrappointmentbsEmg() {
		return strappointmentbsEmg;
	}
	
	public void setStrappointmentbsEmg(String strappointmentbsEmg) {
		this.strappointmentbsEmg = strappointmentbsEmg;
	}

	public String getStrduplicacyChkEmg() {
		return strduplicacyChkEmg;
	}

	public void setStrduplicacyChkEmg(String strduplicacyChkEmg) {
		this.strduplicacyChkEmg = strduplicacyChkEmg;
	}

	public String getStradharintegrationEmg() {
		return stradharintegrationEmg;
	}

	public void setStradharintegrationEmg(String stradharintegrationEmg) {
		this.stradharintegrationEmg = stradharintegrationEmg;
	}

	public String getStremgRenewalEmg() {
		return stremgRenewalEmg;
	}

	public void setStremgRenewalEmg(String stremgRenewalEmg) {
		this.stremgRenewalEmg = stremgRenewalEmg;
	}

	public String getStrNoOfHrsEmg() {
		return strNoOfHrsEmg;
	}

	public void setStrNoOfHrsEmg(String strNoOfHrsEmg) {
		this.strNoOfHrsEmg = strNoOfHrsEmg;
	}

	public String getStrseniorCitizenCatCodeEmg() {
		return strseniorCitizenCatCodeEmg;
	}
	
	public void setStrseniorCitizenCatCodeEmg(String strseniorCitizenCatCodeEmg) {
		this.strseniorCitizenCatCodeEmg = strseniorCitizenCatCodeEmg;
	}

	public String getStrseniorCitizenAgeEmg() {
		return strseniorCitizenAgeEmg;
	}

	public void setStrseniorCitizenAgeEmg(String strseniorCitizenAgeEmg) {
		this.strseniorCitizenAgeEmg = strseniorCitizenAgeEmg;
	}

	public String getStrmobileServiceEmg() {
		return strmobileServiceEmg;
	}
	
	public void setStrmobileServiceEmg(String strmobileServiceEmg) {
		this.strmobileServiceEmg = strmobileServiceEmg;
	}
	
	public String getStrimgUploadModeEmg() {
		return strimgUploadModeEmg;
	}
	
	public void setStrimgUploadModeEmg(String strimgUploadModeEmg) {
		this.strimgUploadModeEmg = strimgUploadModeEmg;
	}
	
	/*public String getStrContactPerson() {
		return strContactPerson;
	}
	
	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	public String getStrHl7Code() {
		return strHl7Code;
	}
	
	public void setStrHl7Code(String strHl7Code) {
		this.strHl7Code = strHl7Code;
	}
	
	public String getStrRemarks() {
		return strRemarks;
	}
	
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}*/
	
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
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
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public void setStrErrMsg(String string) {
		// TODO Auto-generated method stub
		
	}

	/////////////////////////////////////////////////////////////////
	public Map getMapSession() {
		return mapSession;
	}

	public void setMapSession(Map mapSession) {
		this.mapSession = mapSession;
	}

	public HttpServletRequest getObjRequest()
	{
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest)
	{
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse()
	{
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse)
	{
		this.objResponse = objResponse;
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map sessionMap)
	{
		this.mapSession = sessionMap;
	}


	public Object[] getStrRegistrationTypeGen() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object[] getStrRegistrationModeGen() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object[] getStrRegistrationTypeEmg() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object[] getStrRegistrationModeEmg() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object[] getStrRegistrationTypeSpl() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object[] getStrRegistrationModeSpl() {
		// TODO Auto-generated method stub
		return null;
	}


	public Object[] getStrRegistrationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
