package new_investigation.masters.controller.fb;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class ItemLabTestMappingMstFB extends ActionForm{

	
	private String hmode;
	private String otherItemID;
	private String itemName;
	private String defaultQuantity;
	private String itemType;
	private String itemIdFromStore;
	private String itemCode;
	private String labCode;
	private String testCode;
	private String itemID;
	private String entry;
	private String lotNo;
	private String manufacture;
	private String expiryDate="";
	private String storeID;
	private String usage;
	private String usageStartDate="";
	private String usageEndDate="";
	private String remarks;
	private String unit;
	private String storeName;
	private String storeItemName;
	private String chk[];
	private String sysDate="";
	private String selectedChk;
	private String batchNo;
	private String tempHmode;
	private String tempLotNo;
	private String tempOtherID;
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.setItemID("");
		this.setItemName("");
		this.setDefaultQuantity("");
		this.setItemType("");
		this.setItemIdFromStore("");
		this.setEntry("manual");
		this.setLotNo("");
		this.setManufacture("");
		this.expiryDate="";
		this.usageEndDate="";
		this.usageStartDate="";
		this.batchNo="";
		this.setUsage("-1");
		this.setItemType("-1");
		this.setRemarks("");
		this.setStoreID("-1");
		this.setTempLotNo("");
	}
	public String getSelectedChk() {
		return selectedChk;
	}
	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getStoreID() {
		return storeID;
	}
	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getUsageStartDate() {
		return usageStartDate;
	}
	public void setUsageStartDate(String usageStartDate) {
		this.usageStartDate = usageStartDate;
	}
	public String getUsageEndDate() {
		return usageEndDate;
	}
	public void setUsageEndDate(String usageEndDate) {
		this.usageEndDate = usageEndDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remark) {
		this.remarks = remark;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreItemName() {
		return storeItemName;
	}
	public void setStoreItemName(String storeItemName) {
		this.storeItemName = storeItemName;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getTempHmode() {
		return tempHmode;
	}
	public void setTempHmode(String tempHmode) {
		this.tempHmode = tempHmode;
	}
	public String getTempLotNo() {
		return tempLotNo;
	}
	public void setTempLotNo(String tempLotNo) {
		this.tempLotNo = tempLotNo;
	}
	public String getTempOtherID() {
		return tempOtherID;
	}
	public void setTempOtherID(String tempOtherID) {
		this.tempOtherID = tempOtherID;
	}
	
}
