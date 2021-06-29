package hisglobal.vo;

public class EprTabAccessDtlVO extends ValueObject
{
	
	private String tabId;
	private String departmentUnitCode;
	private String toDepartmentUnitCode;
	private String policyType;
	private String accessType;
	private String accessTo;
	private String userLevel;
	private String userId;
	private String userName;
	private String effectiveFrom;
	private String effectiveTo;
	
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
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
	public String getAccessTo() {
		return accessTo;
	}
	public void setAccessTo(String accessTo) {
		this.accessTo = accessTo;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
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
	public String getToDepartmentUnitCode() {
		return toDepartmentUnitCode;
	}
	public void setToDepartmentUnitCode(String toDepartmentUnitCode) {
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		EprTabAccessDtlVO vo=(EprTabAccessDtlVO)obj;
		if(this.tabId.equals(vo.getTabId())
				&& this.accessType.equals(vo.getAccessType()) 
				&& this.userLevel.equals(vo.getUserLevel())){
			return true;
		}
		else{
			return false;
		}
	}
	

}
