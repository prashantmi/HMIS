package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BlockAreaMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String serialNo;
	
	private String blockId;
	private String areaCode[];
	private String selectedAreaCode[];
	private String fetchedList;
	private String areaTypeCode;
	private String workPrefrence;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.blockId="";
		this.areaCode=null;
		this.areaTypeCode="";
		this.workPrefrence="";
	
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getAreaTypeCode() {
		return areaTypeCode;
	}

	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}

	public String getWorkPrefrence() {
		return workPrefrence;
	}

	public void setWorkPrefrence(String workPrefrence) {
		this.workPrefrence = workPrefrence;
	}

	public String[] getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String[] areaCode) {
		this.areaCode = areaCode;
	}

	public String[] getSelectedAreaCode() {
		return selectedAreaCode;
	}

	public void setSelectedAreaCode(String[] selectedAreaCode) {
		this.selectedAreaCode = selectedAreaCode;
	}

	public String getFetchedList() {
		return fetchedList;
	}

	public void setFetchedList(String fetchedList) {
		this.fetchedList = fetchedList;
	}

		
}
