package QMS.transaction.controller.fb;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class QmsFB extends ActionForm
{
	private String mode;
	private String deskMenuId;
	private String departmentUnitCode;
	private String sortType;
	private String crNoSelected;

	private String strHospitalCode;
	private String strSeatId;
	private String strMsgString = "";
	private String strMsgType = "";
	private String episodeCode = "";
	private String deptUnitName="";
	private String strRoomCode;
	private String strPrevDate="";
	private String strInitialMode="1";
	private String strVistNo;
	public String getStrVistNo() {
		return strVistNo;
	}

	public void setStrVistNo(String strVistNo) {
		this.strVistNo = strVistNo;
	}

	public String getStrInitialMode() {
		return strInitialMode;
	}

	public void setStrInitialMode(String strInitialMode) {
		this.strInitialMode = strInitialMode;
	}

	public String getStrPrevDate() {
		return strPrevDate;
	}

	public void setStrPrevDate(String strPrevDate) {
		this.strPrevDate = strPrevDate;
	}

	public String getStrRoomCode() {
		return strRoomCode;
	}

	public void setStrRoomCode(String strRoomCode) {
		this.strRoomCode = strRoomCode;
	}

	public String getDeptUnitName() {
		return deptUnitName;
	}

	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getCrNoSelected()
	{
		return crNoSelected;
	}

	public void setCrNoSelected(String crNoSelected)
	{
		this.crNoSelected = crNoSelected;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{

		this.setDeskMenuId("");
		this.setSortType("");
		this.setDepartmentUnitCode("");
		this.setMode("");
		this.setCrNoSelected("");
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

}
