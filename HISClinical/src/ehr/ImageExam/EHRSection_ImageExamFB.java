package ehr.ImageExam;

/**
 * @author  CDAC
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class EHRSection_ImageExamFB extends ActionForm
{
	private String hmode;
	private String errmsg;
	private String patSerialNo;
	private String userSeatId;
	private String departmentUnitCode;
	private String episodeCode;
	private String episodeVisitNo;
	private String deskMenuId;
	private String admissionNo;

	private String imageCode;
	private String title; // Image Name
	private String imageFileName;
	private String imageOutFileName;
	private String colorDesc;
	private String remark;
	private String dirOutPath;
	private String selectedSNo;
	
	private String selectedLogIndex;
	private String isNextLogPresent;

	private String editingStatus;
	
	// Added by VASU on 18-AUG-2017 to upload image on server 
	private String imgCdFrIndx;
	private String patCrNo;
	//private String episodeCode;
	private String isDefault;
    private String maxSerialNo;
    private String noOfImages;
    private String fileNo;
    private String imgSrc;
    private String imageName;
    
	public EHRSection_ImageExamFB()
	{
		this.userSeatId = "";
		this.departmentUnitCode = "";
		this.episodeCode = "";
		this.episodeVisitNo = "";

		this.patSerialNo = "";
		this.imageCode = "-1";
		this.title = "";
		this.imageFileName = "";
		this.imageOutFileName = "";
		this.colorDesc = "";
		this.remark = "";
		this.dirOutPath = "";
		this.selectedSNo = "";

		this.editingStatus = "";
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.userSeatId = "";
		this.departmentUnitCode = "";
		this.episodeCode = "";
		this.episodeVisitNo = "";

		this.patSerialNo = "";
		this.imageCode = "-1";
		this.title = "";
		this.imageFileName = "";
		this.imageOutFileName = "";
		this.colorDesc = "";
		this.remark = "";
		this.dirOutPath = "";
		this.selectedSNo = "";

		this.editingStatus = "";
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getDeskMenuId()
	{
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId)
	{
		this.deskMenuId = deskMenuId;
	}

	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getImageCode()
	{
		return imageCode;
	}

	public void setImageCode(String imageCode)
	{
		this.imageCode = imageCode;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getImageFileName()
	{
		return imageFileName;
	}

	public void setImageFileName(String imageFileName)
	{
		this.imageFileName = imageFileName;
	}

	public String getColorDesc()
	{
		return colorDesc;
	}

	public void setColorDesc(String colorDesc)
	{
		this.colorDesc = colorDesc;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getImageOutFileName()
	{
		return imageOutFileName;
	}

	public void setImageOutFileName(String imageOutFileName)
	{
		this.imageOutFileName = imageOutFileName;
	}

	public String getPatSerialNo()
	{
		return patSerialNo;
	}

	public void setPatSerialNo(String patSerialNo)
	{
		this.patSerialNo = patSerialNo;
	}

	public String getDirOutPath()
	{
		return dirOutPath;
	}

	public void setDirOutPath(String dirOutPath)
	{
		this.dirOutPath = dirOutPath;
	}

	public String getEditingStatus()
	{
		return editingStatus;
	}

	public void setEditingStatus(String editingStatus)
	{
		this.editingStatus = editingStatus;
	}

	public String getSelectedSNo()
	{
		return selectedSNo;
	}

	public void setSelectedSNo(String selectedSNo)
	{
		this.selectedSNo = selectedSNo;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getIsNextLogPresent()
	{
		return isNextLogPresent;
	}

	public void setIsNextLogPresent(String isNextLogPresent)
	{
		this.isNextLogPresent = isNextLogPresent;
	}

	public String getSelectedLogIndex()
	{
		return selectedLogIndex;
	}

	public void setSelectedLogIndex(String selectedLogIndex)
	{
		this.selectedLogIndex = selectedLogIndex;
	}

	public String getImgCdFrIndx() {
		return imgCdFrIndx;
	}

	public void setImgCdFrIndx(String imgCdFrIndx) {
		this.imgCdFrIndx = imgCdFrIndx;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}



	public String getMaxSerialNo() {
		return maxSerialNo;
	}

	public void setMaxSerialNo(String maxSerialNo) {
		this.maxSerialNo = maxSerialNo;
	}

	public String getNoOfImages() {
		return noOfImages;
	}

	public void setNoOfImages(String noOfImages) {
		this.noOfImages = noOfImages;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
