package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class DoctorDeskFB extends CRNoFB
{
	private String mode;
	private String hmode;
	private String deskType;
	private String deskMenuId;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String patAdmNo;
	private String departmentUnitCode;
	private String roomCode;
	private String wardCode;
	private String ipdRoomCode;
	private String bedCode;
	private String patMovNo;

	private String crNoSelected;
	private String selListItemKey;

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.setMode("");
		this.setHmode("");
		this.setDeskType("");
		this.setPatCrNo("");
		this.setDeskMenuId("");
		this.setDepartmentUnitCode("");
		this.setRoomCode("");
		this.setWardCode("");
		this.setIpdRoomCode("");
		this.setBedCode("");
		this.setPatMovNo("");
		
		this.setCrNoSelected("");
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getCrNoSelected()
	{
		return crNoSelected;
	}

	public void setCrNoSelected(String crNoSelected)
	{
		this.crNoSelected = crNoSelected;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
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

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getDeskType() {
		return deskType;
	}

	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getSelListItemKey() {
		return selListItemKey;
	}

	public void setSelListItemKey(String selListItemKey) {
		this.selListItemKey = selListItemKey;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getIpdRoomCode() {
		return ipdRoomCode;
	}

	public void setIpdRoomCode(String ipdRoomCode) {
		this.ipdRoomCode = ipdRoomCode;
	}

	public String getBedCode() {
		return bedCode;
	}

	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}

	public String getPatMovNo() {
		return patMovNo;
	}

	public void setPatMovNo(String patMovNo) {
		this.patMovNo = patMovNo;
	}
}
