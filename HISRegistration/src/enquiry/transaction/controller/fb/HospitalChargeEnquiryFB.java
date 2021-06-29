package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalChargeEnquiryFB extends ActionForm {
	

	private String tariffId;
	private String tariffName;
	private String slNo;
	private String groupId="-1";
	private String groupName;
	private String patCat;
	private String chargeType;
	private String hmode;
	private String isDirectCall;
	private int currentPage=1;
	
	private String hospitalCode;
	private String strModeGrpTariff;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.tariffId="";
		this.tariffName="";
		this.slNo="";
		this.groupId="-1";
		this.currentPage=1;
		this.strModeGrpTariff="";

		
	}


	public String getTariffId() {
		return tariffId;
	}


	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}


	public String getTariffName() {
		return tariffName;
	}


	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public String getGroupId() {
		return groupId;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getPatCat() {
		return patCat;
	}


	public void setPatCat(String patCat) {
		this.patCat = patCat;
	}


	public String getChargeType() {
		return chargeType;
	}


	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getIsDirectCall() {
		return isDirectCall;
	}


	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getHospitalCode() {
		return hospitalCode;
	}


	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}


	public String getStrModeGrpTariff() {
		return strModeGrpTariff;
	}


	public void setStrModeGrpTariff(String strModeGrpTariff) {
		this.strModeGrpTariff = strModeGrpTariff;
	}

	
		
}
