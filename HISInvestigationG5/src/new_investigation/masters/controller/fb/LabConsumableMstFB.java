package new_investigation.masters.controller.fb;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class LabConsumableMstFB extends ActionForm{

	
	private String hmode;
	private String otherItemID;
	private String itemName;
	private String defaultQuantity;
	private String itemType;
	private String itemIdFromStore;
	private String chk[];
	private String selectedChk;
	private String uomCode;
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.otherItemID="";
		this.setItemName("");
		this.setDefaultQuantity("");
		this.setItemType("");
		this.setItemIdFromStore("");
		
		
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getOtherItemID() {
		return otherItemID;
	}
	public void setOtherItemID(String otherItemID) {
		this.otherItemID = otherItemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDefaultQuantity() {
		return defaultQuantity;
	}
	public void setDefaultQuantity(String defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemIdFromStore() {
		return itemIdFromStore;
	}
	public void setItemIdFromStore(String itemIdFromStore) {
		this.itemIdFromStore = itemIdFromStore;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String chk[]) {
		this.chk = chk;
	}
	public String getSelectedChk() {
		return selectedChk;
	}
	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	
}
