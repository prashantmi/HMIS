package hisglobal.vo;

public class EprTabMasterVO extends ValueObject
{
	private String tabId;
	private String tabName;
	private String isDefault;
	private String tabUrl;
	private String normalBoundType;
	private String restrictedBoundType;
	private String tabType;
	
	
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getTabUrl() {
		return tabUrl;
	}
	public void setTabUrl(String tabUrl) {
		this.tabUrl = tabUrl;
	}
	public String getNormalBoundType() {
		return normalBoundType;
	}
	public void setNormalBoundType(String normalBoundType) {
		this.normalBoundType = normalBoundType;
	}
	public String getRestrictedBoundType() {
		return restrictedBoundType;
	}
	public void setRestrictedBoundType(String restrictedBoundType) {
		this.restrictedBoundType = restrictedBoundType;
	}
	public String getTabType() {
		return tabType;
	}
	public void setTabType(String tabType) {
		this.tabType = tabType;
	}

	

}
