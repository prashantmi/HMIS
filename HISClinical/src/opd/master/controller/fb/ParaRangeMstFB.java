package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ParaRangeMstFB extends ActionForm
{
	private String paraId;
	private String paraRangeId;
	private String genderCode;
	private String lowAge;
	private String highAge;
	private String lowValue;
	private String highValue;
	private String unitOfMeasure;
	
	private String paraName;
	private String chk;
	private String isValid;
	private String hospitalCode;
	private String controls[];
	private String lowAgeCheck;
	private String hmode;
	private String tempMode;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.genderCode="-1";
		this.lowAge="";
		this.highAge="";
		this.lowValue="";
		this.highValue="";
		this.unitOfMeasure="";
	}
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getLowAgeCheck() {
		return lowAgeCheck;
	}

	public void setLowAgeCheck(String lowAgeCheck) {
		this.lowAgeCheck = lowAgeCheck;
	}

	public ParaRangeMstFB()
	{
		this.controls = new String[1];
	}
	
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getParaRangeId() {
		return paraRangeId;
	}

	public void setParaRangeId(String paraRangeId) {
		this.paraRangeId = paraRangeId;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getLowAge() {
		return lowAge;
	}

	public void setLowAge(String lowAge) {
		this.lowAge = lowAge;
	}

	public String getHighAge() {
		return highAge;
	}

	public void setHighAge(String highAge) {
		this.highAge = highAge;
	}

	public String getLowValue() {
		return lowValue;
	}

	public void setLowValue(String lowValue) {
		this.lowValue = lowValue;
	}

	public String getHighValue() {
		return highValue;
	}

	public void setHighValue(String highValue) {
		this.highValue = highValue;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}


}
