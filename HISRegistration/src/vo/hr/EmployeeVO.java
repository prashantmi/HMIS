package vo.hr;


public class EmployeeVO //implements java.io.Serializable //extends ValueObject 
{
	
	public EmployeeVO() {
	}
	
	private String strEmpNo;
	private String intRecSlNo;
	private String strOldEmpNo;
	private String strEmpName;
	private String strEmpNameInRegLang;
	private String strEmpSerGrpCode;
	private String strEmpSerGrp;	
	private String intEmpDesigCode;
	private String strEmpDesig;
	private String intEmpDeptCode;
	private String strEmpDept;
	
	private String intEmpNojId;
	private String strEmpNoj;
	
	private String isNextIncDtMand;
	private String isRetirementDtMand;
	
	private String intEmpCadreId;
	private String strEmpCadre;
	
	private String strEmpOfficeCode;
	private String strEmpOffice;	
	private String intEmpEstbId;
	private String strEmpEstb;
	
	private String strEmpClassCode;
	private String strEmpClass;
	private String intEmpRecruitSrcId;
	private String strEmpRecruitSrc;
	
	private String strEmpFileNo;
	
	private String dtEmpDOB;
	private String dtEmpApppointmentDt;
	private String dtEmpJoiningDt;
	private String strEmpJoiningTimeCode;
	private String strEmpJoiningTime;
	private String dtEmpRetirementDt;
	private String dtEmpJobFromDt;
	private String dtEmpJobToDt;
	private String dtNextIncDate;
	
    private String dtReleivingDate;
	
	private String strNormalMsg;
	
	
	
	
	
	public String getIsRetirementDtMand() {
		return isRetirementDtMand;
	}
	public void setIsRetirementDtMand(String isRetirementDtMand) {
		this.isRetirementDtMand = isRetirementDtMand;
	}
	public String getIsNextIncDtMand() {
		return isNextIncDtMand;
	}
	public void setIsNextIncDtMand(String isNextIncDtMand) {
		this.isNextIncDtMand = isNextIncDtMand;
	}
	public String getDtReleivingDate() {
		return dtReleivingDate;
	}
	public void setDtReleivingDate(String dtReleivingDate) {
		this.dtReleivingDate = dtReleivingDate;
	}
	public String getStrEmpNameInRegLang() {
		return strEmpNameInRegLang;
	}
	public void setStrEmpNameInRegLang(String strEmpNameInRegLang) {
		this.strEmpNameInRegLang = strEmpNameInRegLang;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getDtNextIncDate() {
		return dtNextIncDate;
	}
	public void setDtNextIncDate(String dtNextIncDate) {
		this.dtNextIncDate = dtNextIncDate;
	}
	public String getIntEmpNojId() {
		return intEmpNojId;
	}
	public String getIntRecSlNo() {
		return intRecSlNo;
	}
	public void setIntRecSlNo(String intRecSlNo) {
		this.intRecSlNo = intRecSlNo;
	}
	public void setIntEmpNojId(String intEmpNojId) {
		this.intEmpNojId = intEmpNojId;
	}
	public String getStrEmpNoj() {
		return strEmpNoj;
	}
	public void setStrEmpNoj(String strEmpNoj) {
		this.strEmpNoj = strEmpNoj;
	}
	public String getStrEmpJoiningTime() {
		return strEmpJoiningTime;
	}
	public String getStrEmpJoiningTimeCode() {
		return strEmpJoiningTimeCode;
	}
	public void setStrEmpJoiningTimeCode(String strEmpJoiningTimeCode) {
		this.strEmpJoiningTimeCode = strEmpJoiningTimeCode;
	}
	public void setStrEmpJoiningTime(String strEmpJoiningTime) {
		this.strEmpJoiningTime = strEmpJoiningTime;
	}
	public String getIntEmpRecruitSrcId() {
		return intEmpRecruitSrcId;
	}
	public void setIntEmpRecruitSrcId(String intEmpRecruitSrcId) {
		this.intEmpRecruitSrcId = intEmpRecruitSrcId;
	}
	public String getStrEmpRecruitSrc() {
		return strEmpRecruitSrc;
	}
	public void setStrEmpRecruitSrc(String strEmpRecruitSrc) {
		this.strEmpRecruitSrc = strEmpRecruitSrc;
	}
	public String getStrEmpSerGrpCode() {
		return strEmpSerGrpCode;
	}
	public void setStrEmpSerGrpCode(String strEmpSerGrpCode) {
		this.strEmpSerGrpCode = strEmpSerGrpCode;
	}
	public String getStrEmpSerGrp() {
		return strEmpSerGrp;
	}
	public void setStrEmpSerGrp(String strEmpSerGrp) {
		this.strEmpSerGrp = strEmpSerGrp;
	}
	public String getStrEmpFileNo() {
		return strEmpFileNo;
	}
	public void setStrEmpFileNo(String strEmpFileNo) {
		this.strEmpFileNo = strEmpFileNo;
	}
	public String getDtEmpDOB() {
		return dtEmpDOB;
	}
	public void setDtEmpDOB(String dtEmpDOB) {
		this.dtEmpDOB = dtEmpDOB;
	}
	public String getDtEmpApppointmentDt() {
		return dtEmpApppointmentDt;
	}
	public void setDtEmpApppointmentDt(String dtEmpApppointmentDt) {
		this.dtEmpApppointmentDt = dtEmpApppointmentDt;
	}
	public String getDtEmpJoiningDt() {
		return dtEmpJoiningDt;
	}
	public void setDtEmpJoiningDt(String dtEmpJoiningDt) {
		this.dtEmpJoiningDt = dtEmpJoiningDt;
	}
	public String getDtEmpRetirementDt() {
		return dtEmpRetirementDt;
	}
	public void setDtEmpRetirementDt(String dtEmpRetirementDt) {
		this.dtEmpRetirementDt = dtEmpRetirementDt;
	}
	public String getDtEmpJobFromDt() {
		return dtEmpJobFromDt;
	}
	public void setDtEmpJobFromDt(String dtEmpJobFromDt) {
		this.dtEmpJobFromDt = dtEmpJobFromDt;
	}
	public String getDtEmpJobToDt() {
		return dtEmpJobToDt;
	}
	public void setDtEmpJobToDt(String dtEmpJobToDt) {
		this.dtEmpJobToDt = dtEmpJobToDt;
	}
	public String getStrEmpNo() {
		return strEmpNo;
	}
	public void setStrEmpNo(String strEmpNo) {
		this.strEmpNo = strEmpNo;
	}
	public String getStrOldEmpNo() {
		return strOldEmpNo;
	}
	public void setStrOldEmpNo(String strOldEmpNo) {
		this.strOldEmpNo = strOldEmpNo;
	}
	public String getStrEmpName() {
		return strEmpName;
	}
	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}
	public String getIntEmpDesigCode() {
		return intEmpDesigCode;
	}
	public void setIntEmpDesigCode(String intEmpDesigCode) {
		this.intEmpDesigCode = intEmpDesigCode;
	}
	public String getStrEmpDesig() {
		return strEmpDesig;
	}
	public void setStrEmpDesig(String strEmpDesig) {
		this.strEmpDesig = strEmpDesig;
	}
	public String getIntEmpDeptCode() {
		return intEmpDeptCode;
	}
	public void setIntEmpDeptCode(String intEmpDeptCode) {
		this.intEmpDeptCode = intEmpDeptCode;
	}
	public String getStrEmpDept() {
		return strEmpDept;
	}
	public void setStrEmpDept(String strEmpDept) {
		this.strEmpDept = strEmpDept;
	}
	public String getIntEmpCadreId() {
		return intEmpCadreId;
	}
	public void setIntEmpCadreId(String intEmpCadreId) {
		this.intEmpCadreId = intEmpCadreId;
	}
	public String getStrEmpCadre() {
		return strEmpCadre;
	}
	public void setStrEmpCadre(String strEmpCadre) {
		this.strEmpCadre = strEmpCadre;
	}
	public String getStrEmpOfficeCode() {
		return strEmpOfficeCode;
	}
	public void setStrEmpOfficeCode(String strEmpOfficeCode) {
		this.strEmpOfficeCode = strEmpOfficeCode;
	}
	public String getStrEmpOffice() {
		return strEmpOffice;
	}
	public void setStrEmpOffice(String strEmpOffice) {
		this.strEmpOffice = strEmpOffice;
	}
	public String getIntEmpEstbId() {
		return intEmpEstbId;
	}
	public void setIntEmpEstbId(String intEmpEstbId) {
		this.intEmpEstbId = intEmpEstbId;
	}
	public String getStrEmpEstb() {
		return strEmpEstb;
	}
	public void setStrEmpEstb(String strEmpEstb) {
		this.strEmpEstb = strEmpEstb;
	}
	public String getStrEmpClassCode() {
		return strEmpClassCode;
	}
	public void setStrEmpClassCode(String strEmpClassCode) {
		this.strEmpClassCode = strEmpClassCode;
	}
	public String getStrEmpClass() {
		return strEmpClass;
	}
	public void setStrEmpClass(String strEmpClass) {
		this.strEmpClass = strEmpClass;
	}
		
	
	
	
	
}
