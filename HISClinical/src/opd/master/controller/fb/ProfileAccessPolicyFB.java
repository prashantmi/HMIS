package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ProfileAccessPolicyFB extends ActionForm  
{

	private String profileType;
	private String policyType;
	private String accessType;
	private String userLevel;
	private String profileGroupId;
	private String profileGroupName;
	private String deptUnitCode;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String hmode;
	private String serialNo;
	private String isActive;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String chk;
	private String deptUnitName;
	private String profileTypeName;
	private String policyTypeName;
	private String sysDate="";
	private String effectiveFrom;
	private String effectiveTo;
	private String [] selectedDeptUnitCode;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.deptUnitCode="-1";
		this.profileType="-1";
		this.policyType="-1";
		this.accessType="-1";
		this.userLevel="-1";
		this.profileGroupId="-1";
		this.selectedDeptUnitCode=new String []{};
	}
	
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getProfileGroupId() {
		return profileGroupId;
	}
	public void setProfileGroupId(String profileGroupId) {
		this.profileGroupId = profileGroupId;
	}
	public String getProfileGroupName() {
		return profileGroupName;
	}
	public void setProfileGroupName(String profileGroupName) {
		this.profileGroupName = profileGroupName;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}
	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	

	public String getDeptUnitName() {
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}

	public String getProfileTypeName() {
		return profileTypeName;
	}

	public void setProfileTypeName(String profileTypeName) {
		this.profileTypeName = profileTypeName;
	}

	public String getPolicyTypeName() {
		return policyTypeName;
	}

	public void setPolicyTypeName(String policyTypeName) {
		this.policyTypeName = policyTypeName;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String  []getSelectedDeptUnitCode() {
		return selectedDeptUnitCode;
	}

	public void setSelectedDeptUnitCode(String [] selectedDeptUnitCode) {
		this.selectedDeptUnitCode = selectedDeptUnitCode;
	}
	
}
