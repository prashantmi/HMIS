package startup.menu;

import hisglobal.backutil.dto.DataTransferObject;

public class MenuVO extends DataTransferObject {
	private String varMenuId;
	private String varMenuName;
	private String varURL;
	private String varMenuContext;
	public String getVarMenuId() {
		return varMenuId;
	}
	public void setVarMenuId(String varMenuId) {
		this.varMenuId = varMenuId;
	}
	public String getVarMenuName() {
		return varMenuName;
	}
	public void setVarMenuName(String varMenuName) {
		this.varMenuName = varMenuName;
	}
	public String getVarURL() {
		return varURL;
	}
	public void setVarURL(String varURL) {
		this.varURL = varURL;
	}
	public String getVarMenuContext() {
		return varMenuContext;
	}
	public void setVarMenuContext(String varMenuContext) {
		this.varMenuContext = varMenuContext;
	}
	

}
