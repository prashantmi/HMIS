package ipd.transactions;
/*
 * Patient Attendance FB
 * 
 * author: Debashis Sardar
 * 
 * dated: 16/02/2012 
 */
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PatientAttendanceFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strServiceType="";
	private String strServiceName="";
	private String strServiceTypeId="";
	private String strHospCode = "";
	private WebRowSet strServiceTypeWS=null;
	private WebRowSet strServiceNameWS=null;
	private String strStatusCombo="";
	private String strStatus="";
	private WebRowSet strPatDetailWs=null;
	private String strCrNo="";
	private String strAdmNo="";
	private String strMovNo="";
	private String strSeatId="";
	private String strAcceptedFlag="0";
	private String strControl="";
	private String msg="";
	private String hmode="";
	private String cnt="";
	private String strDeptProperty="";
	private WebRowSet StrDepartmentWs=null;
	private String strErrMsgString="";
	private String strDepartment="";
	private WebRowSet strUnitWs=null;
	private String strAge="0";
	private String strGenderCode="0";
	private String strAgeCode="0";
	private String strTreatmentCat="0";
	private String strDeptCode="";
	private String strDeptUnitCode="";
	private String strWard="";
	private String strRemarks="";
	private String strChk="";
	private String strUnit="";
	private String strCtDate="";
	private String strEffectiveFrom="";
	private String strEffectiveTo="";
	private WebRowSet strPatStatusViewDtlWs=null;
	private String strServiceDeptCode="";
	private String strServiceUnitCode="";
	private String strRoom="";
	private String strsamewardchk="";
	private String strBed="";
	
	
	
	public String getStrBed() {
		return strBed;
	}

	public void setStrBed(String strBed) {
		this.strBed = strBed;
	}

	public String getStrsamewardchk() {
		return strsamewardchk;
	}

	public void setStrsamewardchk(String strsamewardchk) {
		this.strsamewardchk = strsamewardchk;
	}

	public String getStrServiceDeptCode() {
		return strServiceDeptCode;
	}

	public void setStrServiceDeptCode(String strServiceDeptCode) {
		this.strServiceDeptCode = strServiceDeptCode;
	}

	public String getStrServiceUnitCode() {
		return strServiceUnitCode;
	}

	public void setStrServiceUnitCode(String strServiceUnitCode) {
		this.strServiceUnitCode = strServiceUnitCode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.strMsgString 				="";
		this.strMsgType				="";
		this.strServiceType				="";
		this.strServiceName			="";
		this.strServiceTypeId		="";
		this.strHospCode		="";
		this.strServiceTypeWS		=null;
		this.strServiceNameWS		=null;
		this.strStatusCombo		="";
		this.strStatus		="";
		this.strPatDetailWs		=null;
		this.strCrNo		="";
		this.strAdmNo		="";
		this.strMovNo		="";
		this.strSeatId		="";
		
	}
	
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrWard() {
		return strWard;
	}
	public void setStrWard(String strWard) {
		this.strWard = strWard;
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
	public String getStrAge() {
		return strAge;
	}
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	public String getStrGenderCode() {
		return strGenderCode;
	}
	public void setStrGenderCode(String strGenderCode) {
		this.strGenderCode = strGenderCode;
	}
	public String getStrAgeCode() {
		return strAgeCode;
	}
	public void setStrAgeCode(String strAgeCode) {
		this.strAgeCode = strAgeCode;
	}
	public String getStrTreatmentCat() {
		return strTreatmentCat;
	}
	public void setStrTreatmentCat(String strTreatmentCat) {
		this.strTreatmentCat = strTreatmentCat;
	}
	public String getStrDepartment() {
		return strDepartment;
	}
	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}
	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}
	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}
	public String getStrErrMsgString() {
		return strErrMsgString;
	}
	public void setStrErrMsgString(String strErrMsgString) {
		this.strErrMsgString = strErrMsgString;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrAdmNo() {
		return strAdmNo;
	}
	public void setStrAdmNo(String strAdmNo) {
		this.strAdmNo = strAdmNo;
	}
	public String getStrMovNo() {
		return strMovNo;
	}
	public void setStrMovNo(String strMovNo) {
		this.strMovNo = strMovNo;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public WebRowSet getStrPatDetailWs() {
		return strPatDetailWs;
	}
	public void setStrPatDetailWs(WebRowSet strPatDetailWs) {
		this.strPatDetailWs = strPatDetailWs;
	}
	public String getStrStatusCombo() {
		return strStatusCombo;
	}
	public void setStrStatusCombo(String strStatusCombo) {
		this.strStatusCombo = strStatusCombo;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
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
	public String getStrServiceType() {
		return strServiceType;
	}
	public void setStrServiceType(String strServiceType) {
		this.strServiceType = strServiceType;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public WebRowSet getStrServiceTypeWS() {
		return strServiceTypeWS;
	}
	public void setStrServiceTypeWS(WebRowSet strServiceTypeWS) {
		this.strServiceTypeWS = strServiceTypeWS;
	}
	public String getStrServiceName() {
		return strServiceName;
	}
	public void setStrServiceName(String strServiceName) {
		this.strServiceName = strServiceName;
	}
	public WebRowSet getStrServiceNameWS() {
		return strServiceNameWS;
	}
	public void setStrServiceNameWS(WebRowSet strServiceNameWS) {
		this.strServiceNameWS = strServiceNameWS;
	}
	public String getStrServiceTypeId() {
		return strServiceTypeId;
	}
	public void setStrServiceTypeId(String strServiceTypeId) {
		this.strServiceTypeId = strServiceTypeId;
	}
	public String getStrAcceptedFlag() {
		return strAcceptedFlag;
	}
	public void setStrAcceptedFlag(String strAcceptedFlag) {
		this.strAcceptedFlag = strAcceptedFlag;
	}
	public String getStrControl() {
		return strControl;
	}
	public void setStrControl(String strControl) {
		this.strControl = strControl;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getStrDeptProperty() {
		return strDeptProperty;
	}
	public void setStrDeptProperty(String strDeptProperty) {
		this.strDeptProperty = strDeptProperty;
	}
	public WebRowSet getStrDepartmentWs() {
		return StrDepartmentWs;
	}
	public void setStrDepartmentWs(WebRowSet strDepartmentWs) {
		StrDepartmentWs = strDepartmentWs;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrChk() {
		return strChk;
	}
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	public String getStrUnit() {
		return strUnit;
	}
	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
	}
	public WebRowSet getStrPatStatusViewDtlWs() {
		return strPatStatusViewDtlWs;
	}
	public void setStrPatStatusViewDtlWs(WebRowSet strPatStatusViewDtlWs) {
		this.strPatStatusViewDtlWs = strPatStatusViewDtlWs;
	}
	public String getStrRoom() {
		return strRoom;
	}
	public void setStrRoom(String strRoom) {
		this.strRoom = strRoom;
	}


}