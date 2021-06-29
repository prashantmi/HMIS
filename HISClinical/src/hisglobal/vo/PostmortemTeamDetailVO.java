package hisglobal.vo;

public class PostmortemTeamDetailVO extends ValueObject
{
	private String postmortemId;
	private String srNO;
	private String empId;
	private String roleId;
	private String empName;
	private String roleName;
	private String isIncharge;
	private String isInchargeLabel;
	private String isPerformed;
	private String isPerformedLabel;
	private String reason;
	
	
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSrNO() {
		return srNO;
	}
	public void setSrNO(String srNO) {
		this.srNO = srNO;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getIsIncharge() {
		return isIncharge;
	}
	public void setIsIncharge(String isIncharge) {
		this.isIncharge = isIncharge;
	}
	public String getIsInchargeLabel() {
		return isInchargeLabel;
	}
	public void setIsInchargeLabel(String isInchargeLabel) {
		this.isInchargeLabel = isInchargeLabel;
	}
	public String getIsPerformed() {
		return isPerformed;
	}
	public void setIsPerformed(String isPerformed) {
		this.isPerformed = isPerformed;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIsPerformedLabel() {
		return isPerformedLabel;
	}
	public void setIsPerformedLabel(String isPerformedLabel) {
		this.isPerformedLabel = isPerformedLabel;
	}
	
	
}
