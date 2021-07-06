package new_opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class DoctorDeskFB extends ActionForm {
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
	private String deptUnitName = "";
	private String strRoomCode;
	private String strPrevDate = "";
	private String strInitialMode = "1";
	private String strVistNo;
	private String strHiddenDeptCode;

	private String strRailTailFlg;
	private String strmode;
	private String strMobileCrno = "";
	private String strMobileEpisodeCode;
	private String strMobileHospitalCode = "";
	private String strMobileVisitNo = "1";
	private String strMobileEntryDate;
	private String strMobileDeptUnitCode;
	private String strMobileSeatId;
	private String strTemplateType;
	private String strHospitalName;
	private String strHospitalAddres;
	private FormFile strLocation = null;
	private String strDocumenttype;
	private String strFileData;
	
	// private FileItem filename;

	public String getStrFileData() {
		return strFileData;
	}

	public void setStrFileData(String strFileData) {
		this.strFileData = strFileData;
	}

	public String getStrDocumenttype() {
		return strDocumenttype;
	}

	public void setStrDocumenttype(String strDocumenttype) {
		this.strDocumenttype = strDocumenttype;
	}

	/*
	 * public FileItem getFilename() { return filename; }
	 * 
	 * public void setFilename(FileItem filename) { this.filename = filename; }
	 */
	public String getStrRailTailFlg() {
		return strRailTailFlg;
	}

	public String getStrmode() {
		return strmode;
	}

	public void setStrmode(String strmode) {
		this.strmode = strmode;
	}

	public FormFile getStrLocation() {
		return strLocation;
	}

	public void setStrLocation(FormFile strLocation) {
		this.strLocation = strLocation;
	}

	public void setStrRailTailFlg(String strRailTailFlg) {
		this.strRailTailFlg = strRailTailFlg;
	}

	public String getStrHospitalAddres() {
		return strHospitalAddres;
	}

	public void setStrHospitalAddres(String strHospitalAddres) {
		this.strHospitalAddres = strHospitalAddres;
	}

	public String getStrHospitalName() {
		return strHospitalName;
	}

	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
	}

	public String getStrTemplateType() {
		return strTemplateType;
	}

	public void setStrTemplateType(String strTemplateType) {
		this.strTemplateType = strTemplateType;
	}

	public String getStrMobileSeatId() {
		return strMobileSeatId;
	}

	public void setStrMobileSeatId(String strMobileSeatId) {
		this.strMobileSeatId = strMobileSeatId;
	}

	public String getStrMobileCrno() {
		return strMobileCrno;
	}

	public void setStrMobileCrno(String strMobileCrno) {
		this.strMobileCrno = strMobileCrno;
	}

	public String getStrMobileEpisodeCode() {
		return strMobileEpisodeCode;
	}

	public void setStrMobileEpisodeCode(String strMobileEpisodeCode) {
		this.strMobileEpisodeCode = strMobileEpisodeCode;
	}

	public String getStrMobileHospitalCode() {
		return strMobileHospitalCode;
	}

	public void setStrMobileHospitalCode(String strMobileHospitalCode) {
		this.strMobileHospitalCode = strMobileHospitalCode;
	}

	public String getStrMobileVisitNo() {
		return strMobileVisitNo;
	}

	public void setStrMobileVisitNo(String strMobileVisitNo) {
		this.strMobileVisitNo = strMobileVisitNo;
	}

	public String getStrMobileEntryDate() {
		return strMobileEntryDate;
	}

	public void setStrMobileEntryDate(String strMobileEntryDate) {
		this.strMobileEntryDate = strMobileEntryDate;
	}

	public String getStrMobileDeptUnitCode() {
		return strMobileDeptUnitCode;
	}

	public void setStrMobileDeptUnitCode(String strMobileDeptUnitCode) {
		this.strMobileDeptUnitCode = strMobileDeptUnitCode;
	}

	public String getStrHiddenDeptCode() {
		return strHiddenDeptCode;
	}

	public void setStrHiddenDeptCode(String strHiddenDeptCode) {
		this.strHiddenDeptCode = strHiddenDeptCode;
	}

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

	public String getCrNoSelected() {
		return crNoSelected;
	}

	public void setCrNoSelected(String crNoSelected) {
		this.crNoSelected = crNoSelected;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {

		this.setDeskMenuId("");
		this.setSortType("");
		this.setDepartmentUnitCode("");
		this.setMode("");
		this.setCrNoSelected("");
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}

}
