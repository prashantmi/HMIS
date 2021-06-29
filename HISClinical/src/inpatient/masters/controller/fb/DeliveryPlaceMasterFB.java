package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DeliveryPlaceMasterFB extends ActionForm
{
	private String deliveryPlaceId;
	private String slNo;
	private String deliveryPlace;
		
	private String chk[];
	private String hmode;
	private String isValid;
	private String tempMode;
	
	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDeliveryPlace("");
			
	}

	public String getDeliveryPlaceId() {
		return deliveryPlaceId;
	}

	public void setDeliveryPlaceId(String deliveryPlaceId) {
		this.deliveryPlaceId = deliveryPlaceId;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getDeliveryPlace() {
		return deliveryPlace;
	}

	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
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

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}



	
}
