package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeceasedItemMasterFB extends ActionForm
{
	private String itemCode;
	private String slNo;
	private String itemName;
	private String itemType;
	private String isActive;
	private String tempMode;
	
	private String chk[];
	private String hmode;
	
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.itemName="";
		this.itemType="-1";
			
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemType() {
		return itemType;
	}


	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String[] getChk() {
		return chk;
	}


	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getTempMode() {
		return tempMode;
	}


	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}


}
