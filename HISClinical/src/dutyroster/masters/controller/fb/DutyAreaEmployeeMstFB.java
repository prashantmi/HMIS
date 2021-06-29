package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DutyAreaEmployeeMstFB extends ActionForm {

	private String areaTypeCode;
	private String areaTypeName;
	private String areaCode;
	private String areaName;
	private String dutyRoleId;
	private String dutyRoleDesc;
	private String empCollectionLeft;
	private String empCollectionRight;
	private String newEmpSelectedLeft;
	private String newEmpSelectedRight;
	private String oldEmpSelectedLeft;
	private String oldEmpSelectedRight;
	private String hmode;
	private String chk;
	private String isValid;
	private String hospitalCode;
	private String serialNo;
	private String empDesg="";
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

		this.areaTypeCode = "-1";
		this.areaTypeName = "";
		this.areaCode = "-1";
		this.areaName = "";
		this.dutyRoleId = "";
		this.dutyRoleDesc = "";
		this.empCollectionLeft = "";
		this.empCollectionRight = "";
		this.newEmpSelectedLeft = "";
		this.newEmpSelectedRight = "";	
		this.oldEmpSelectedLeft = "";
		this.oldEmpSelectedRight = "";	
		this.chk = "";
		this.isValid = "-1";
		this.hospitalCode = "";	
		this.serialNo = "";	
		this.empDesg="-1";
		
	}
	
	
	
	
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDutyRoleId() {
		return dutyRoleId;
	}
	public void setDutyRoleId(String dutyRoleId) {
		this.dutyRoleId = dutyRoleId;
	}
	public String getDutyRoleDesc() {
		return dutyRoleDesc;
	}
	public void setDutyRoleDesc(String dutyRoleDesc) {
		this.dutyRoleDesc = dutyRoleDesc;
	}
	public String getEmpCollectionLeft() {
		return empCollectionLeft;
	}
	public void setEmpCollectionLeft(String empCollectionLeft) {
		this.empCollectionLeft = empCollectionLeft;
	}
	public String getEmpCollectionRight() {
		return empCollectionRight;
	}
	public void setEmpCollectionRight(String empCollectionRight) {
		this.empCollectionRight = empCollectionRight;
	}
	
	public String getNewEmpSelectedLeft() {
		return newEmpSelectedLeft;
	}
   public void setNewEmpSelectedLeft(String newEmpSelectedLeft) {
		this.newEmpSelectedLeft = newEmpSelectedLeft;
	}
   public String getNewEmpSelectedRight() {
		return newEmpSelectedRight;
	}
	public void setNewEmpSelectedRight(String newEmpSelectedRight) {
		this.newEmpSelectedRight = newEmpSelectedRight;
	}
	public String getOldEmpSelectedLeft() {
		return oldEmpSelectedLeft;
	}
    public void setOldEmpSelectedLeft(String oldEmpSelectedLeft) {
		this.oldEmpSelectedLeft = oldEmpSelectedLeft;
	}
	public String getOldEmpSelectedRight() {
		return oldEmpSelectedRight;
	}
	public void setOldEmpSelectedRight(String oldEmpSelectedRight) {
		this.oldEmpSelectedRight = oldEmpSelectedRight;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    public String getEmpDesg() {
		return empDesg;
	}
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}
	
}
