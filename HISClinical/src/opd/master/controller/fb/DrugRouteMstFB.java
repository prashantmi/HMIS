package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DrugRouteMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;
	private String controls[];
	private String drugRouteId;
	private String hospitalCode;
	private String serialNo;
	private String drugRouteName;
	private String drugRouteDesc;
	private String itemTypeId;
	
	private String routeClassification;
	private String seatId;
	private String entryDate;
	private String itemTypeName;
	private String itemType;
	private String isFrequencyBound;

	
	
	
	public String getItemType() {
		return itemType;
	}


	public void setItemType(String itemType) {
		this.itemType = itemType;
	}


	public String getIsFrequencyBound() {
		return isFrequencyBound;
	}


	public void setIsFrequencyBound(String isFrequencyBound) {
		this.isFrequencyBound = isFrequencyBound;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String[] getChk() {
		return chk;
	}


	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String[] getControls() {
		return controls;
	}


	public void setControls(String[] controls) {
		this.controls = controls;
	}


	public String getDrugRouteId() {
		return drugRouteId;
	}


	public void setDrugRouteId(String drugRouteId) {
		this.drugRouteId = drugRouteId;
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


	public String getDrugRouteName() {
		return drugRouteName;
	}


	public void setDrugRouteName(String drugRouteName) {
		this.drugRouteName = drugRouteName;
	}


	public String getDrugRouteDesc() {
		return drugRouteDesc;
	}


	public void setDrugRouteDesc(String drugRouteDesc) {
		this.drugRouteDesc = drugRouteDesc;
	}


	public String getItemTypeId() {
		return itemTypeId;
	}


	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}


	
	public String getRouteClassification() {
		return routeClassification;
	}


	public void setRouteClassification(String routeClassification) {
		this.routeClassification = routeClassification;
	}


	public String getSeatId() {
		return seatId;
	}


	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}


	public String getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}


	public String getItemTypeName() {
		return itemTypeName;
	}


	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.controls= new String[2];
		this.drugRouteDesc="";
		this.drugRouteName="";
		this.routeClassification="-1";
		
	}
}