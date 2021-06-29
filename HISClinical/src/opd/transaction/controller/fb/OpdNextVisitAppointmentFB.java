package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class OpdNextVisitAppointmentFB extends CRNoFB {
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.patName="";
		this.paraName1="";
		this.paraName2="";
		this.paraName3="";
		this.paraName4="";
		this.slotCode="";		
		this.actCode="";
		this.slotStartTime="";
		this.slotEndTime="";
		this.remark="";	
		this.aptActCode="";		
		this.aptStatus="";	
		this.isvalid="";
		this.seatId="";	
		this.statusRemark="";	
		this.level="0";
		this.selSlotTime="";		
	}
		
	
	private String hmode;	
	private String patCrNo;
	private String patFirstName;
    private String patMiddleName;
    private String patLastName;
    private String patGender;
    private String patGuardianName;
    private String patAgeUnit;    
	private String patRegCatCode;
	private String getCrNoToRetrieve;
    private String patPrimaryCat;
	private String patSecondaryCat;
    private String patGenderCode;
	private String patPrimaryCatCode;
	private String deptUnitName;
	private String msg;
	private String strRows;

	private String patName;		
	private String	patAddress;
	private String patAge;
	private String patSex;
	
	private String actCode;
	private String actName;
	private String aptDate="";
	private String paraName1;
	private String paraName2;
	private String paraName3;
	private String paraName4;
	private String para1;
	private String para1ActualName;
	private String para2;
	private String para2ActualName;
	private String para3;
	private String para4;	
	private String slotCode;	
	private String slotStartTime;
	private String slotEndTime;
	private String remark;	
	private String aptActCode;
	private String aptReqNo;
	private String aptStatus;	
	private String isvalid;
	private String seatId;	
	private String statusRemark;	
	private String level="0";	
	private String selSlotTime;
	private String tempPara1;
	private String tempActCode;
	private String systemDate;

	
	
	public String getTempPara1() {
		return tempPara1;
	}
	public void setTempPara1(String tempPara1) {
		this.tempPara1 = tempPara1;
	}
	public String getTempActCode() {
		return tempActCode;
	}
	public void setTempActCode(String tempActCode) {
		this.tempActCode = tempActCode;
	}

	public String getActCode() {
		return actCode;
	}
	
	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	
	public String getAptActCode() {
		return aptActCode;
	}
	
	public void setAptActCode(String aptActCode) {
		this.aptActCode = aptActCode;
	}
	
	public String getAptReqNo() {
		return aptReqNo;
	}
	
	public void setAptReqNo(String aptReqNo) {
		this.aptReqNo = aptReqNo;
	}
	
	public String getAptStatus() {
		return aptStatus;
	}
	
	public void setAptStatus(String aptStatus) {
		this.aptStatus = aptStatus;
	}
	
	public String getIsvalid() {
		return isvalid;
	}
	
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	
	public String getPatAddress() {
		return patAddress;
	}
	
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	
	public String getPatAge() {
		return patAge;
	}
	
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	
	public String getPatName() {
		return patName;
	}
	
	public void setPatName(String patName) {
		this.patName = patName;
	}
	
	public String getPatSex() {
		return patSex;
	}
	
	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getSeatId() {
		return seatId;
	}
	
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	
	public String getSlotCode() {
		return slotCode;
	}
	
	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}
	
	public String getSlotEndTime() {
		return slotEndTime;
	}
	
	public void setSlotEndTime(String slotEndTime) {
		this.slotEndTime = slotEndTime;
	}
	
	public String getSlotStartTime() {
		return slotStartTime;
	}
	
	public void setSlotStartTime(String slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	
	public String getStatusRemark() {
		return statusRemark;
	}
	
	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}
	/**
	 * @return Returns the patCrno.
	 */
	public String getGetCrNoToRetrieve() {
		return getCrNoToRetrieve;
	}
	public void setGetCrNoToRetrieve(String getCrNoToRetrieve) {
		this.getCrNoToRetrieve = getCrNoToRetrieve;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
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
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatRegCatCode() {
		return patRegCatCode;
	}
	public void setPatRegCatCode(String patRegCatCode) {
		this.patRegCatCode = patRegCatCode;
	}
	public String getPatSecondaryCat() {
		return patSecondaryCat;
	}
	public void setPatSecondaryCat(String patSecondaryCat) {
		this.patSecondaryCat = patSecondaryCat;
	}
	
	public String getParaName1() {
		return paraName1;
	}
	
	public void setParaName1(String paraName1) {
		this.paraName1 = paraName1;
	}
	
	public String getParaName2() {
		return paraName2;
	}
	
	public void setParaName2(String paraName2) {
		this.paraName2 = paraName2;
	}
	
	public String getParaName3() {
		return paraName3;
	}
	
	public void setParaName3(String paraName3) {
		this.paraName3 = paraName3;
	}
	
	public String getParaName4() {
		return paraName4;
	}
	
	public void setParaName4(String paraName4) {
		this.paraName4 = paraName4;
	}
	
	public String getPara1() {
		return para1;
	}
	
	public void setPara1(String para1) {
		this.para1 = para1;
	}
	
	public String getPara2() {
		return para2;
	}
	
	public void setPara2(String para2) {
		this.para2 = para2;
	}
	
	public String getPara3() {
		return para3;
	}
	
	public void setPara3(String para3) {
		this.para3 = para3;
	}
	
	public String getPara4() {
		return para4;
	}
	
	public void setPara4(String para4) {
		this.para4 = para4;
	}

	public String getAptDate() {
		return aptDate;
	}
	
	public void setAptDate(String aptDate) {
		this.aptDate = aptDate;
		System.out.println("this.aptDate in set " + this.aptDate);
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getSelSlotTime() {
		return selSlotTime;
	}
	
	public void setSelSlotTime(String selSlotTime) {
		this.selSlotTime = selSlotTime;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getPara1ActualName() {
		return para1ActualName;
	}
	public void setPara1ActualName(String para1ActualName) {
		this.para1ActualName = para1ActualName;
	}
	public String getPara2ActualName() {
		return para2ActualName;
	}
	public void setPara2ActualName(String para2ActualName) {
		this.para2ActualName = para2ActualName;
	}
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}
	public String getStrRows() {
		return strRows;
	}
	public void setStrRows(String strRows) {
		this.strRows = strRows;
	}
	


}
