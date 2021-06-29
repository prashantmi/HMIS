package registration.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class PatientReferralSUP extends CRNoSUP implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	protected String errorMessage;
	protected String normalMessage;	

	protected String goFlag;
	protected String AfterGo;
	protected String isDesk;
	protected String appointmentNo;
	protected String appointmentQueueNo;

	public String getAfterGo() {
		return AfterGo;
	}

	public void setAfterGo(String afterGo) {
		AfterGo = afterGo;
	}

	public String getGoFlag() {
		return goFlag;
	}

	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}

	protected String patCrNo;
	protected String crNoToModify;
	private String selectedEpisode;
	private String episodeCode;
	private String episodeVisitNo;
	private String serialNo;
	private String patAdmNo;
	private String fromDepartmentCode;
	private String fromDepartment;
	private String fromDepartmentUnitCode;
	private String fromDepartmentUnit;
	private String fromDoctorCode;
	private String choice;
	private String patRefHospitalDeptOther;
	private String patRefGnctdHospitalDept;
	private String PatRefGnctdHospitalDeptUnit;
	private String PatRefHospitalName;
	private String isAssociated;
	private String PatRefGnctdHospitalCode;
	private String DepartmentCode;
	private String DepartmentUnitCode;
	
	private String fromWardCode;
	private String fromWard;
	private String fromEpisodeCode;
	private String toDepartmentCode;
	private String toDepartment;
	private String toDepartmentUnitCode;
	private String toDepartmentUnit;
	private String toDoctorCode;
	private String toWardCode;
	private String toWard;
	private String toEpisodeCode;
	private String toEpisodeVisitNo;
	private String externalHospitalCode;
	private String externalHospitalName;
	private String externalHospitalDoctorName;
	private String externalHospitalPatCrNo;
	private String externalHospitalDepartment;
	private String externalHospitalDepartmentUnit;
	private String isRefferInOut;
	private String reffDateTime;
	private String episodeReferAcceptDate;
	private String systemIPAddress;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String lastModifiedDate;
	private String lastModifiedSeatId;
	private String remarks;
	private String externalReferRemarks;
	
	public String getExternalReferRemarks() {
		return externalReferRemarks;
	}

	public void setExternalReferRemarks(String externalReferRemarks) {
		this.externalReferRemarks = externalReferRemarks;
	}

	public String getIsAssociated() {
		return isAssociated;
	}

	public void setIsAssociated(String isAssociated) {
		this.isAssociated = isAssociated;
	}

	public String getPatRefGnctdHospitalCode() {
		return PatRefGnctdHospitalCode;
	}

	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode) {
		PatRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	public String getDepartmentCode() {
		return DepartmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		DepartmentCode = departmentCode;
	}

	public String getDepartmentUnitCode() {
		return DepartmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		DepartmentUnitCode = departmentUnitCode;
	}

	public String getPatRefGnctdHospitalDeptUnit() {
		return PatRefGnctdHospitalDeptUnit;
	}

	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit) {
		PatRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getPatRefHospitalName() {
		return PatRefHospitalName;
	}

	public void setPatRefHospitalName(String patRefHospitalName) {
		PatRefHospitalName = patRefHospitalName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getNormalMessage() {
		return normalMessage;
	}

	public void setNormalMessage(String normalMessage) {
		this.normalMessage = normalMessage;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getPatRefHospitalDeptOther() {
		return patRefHospitalDeptOther;
	}

	public void setPatRefHospitalDeptOther(String patRefHospitalDeptOther) {
		this.patRefHospitalDeptOther = patRefHospitalDeptOther;
	}

	public String getPatRefGnctdHospitalDept() {
		return patRefGnctdHospitalDept;
	}

	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept) {
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}
	public String getSelectedEpisode() {
		return selectedEpisode;
	}

	public void setSelectedEpisode(String selectedEpisode) {
		this.selectedEpisode = selectedEpisode;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getFromDepartmentCode() {
		return fromDepartmentCode;
	}

	public void setFromDepartmentCode(String fromDepartmentCode) {
		this.fromDepartmentCode = fromDepartmentCode;
	}

	public String getFromDepartment() {
		return fromDepartment;
	}

	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}

	public String getFromDepartmentUnitCode() {
		return fromDepartmentUnitCode;
	}

	public void setFromDepartmentUnitCode(String fromDepartmentUnitCode) {
		this.fromDepartmentUnitCode = fromDepartmentUnitCode;
	}

	public String getFromDepartmentUnit() {
		return fromDepartmentUnit;
	}

	public void setFromDepartmentUnit(String fromDepartmentUnit) {
		this.fromDepartmentUnit = fromDepartmentUnit;
	}

	public String getFromDoctorCode() {
		return fromDoctorCode;
	}

	public void setFromDoctorCode(String fromDoctorCode) {
		this.fromDoctorCode = fromDoctorCode;
	}

	public String getFromWardCode() {
		return fromWardCode;
	}

	public void setFromWardCode(String fromWardCode) {
		this.fromWardCode = fromWardCode;
	}

	public String getFromWard() {
		return fromWard;
	}

	public void setFromWard(String fromWard) {
		this.fromWard = fromWard;
	}

	public String getFromEpisodeCode() {
		return fromEpisodeCode;
	}

	public void setFromEpisodeCode(String fromEpisodeCode) {
		this.fromEpisodeCode = fromEpisodeCode;
	}

	public String getToDepartmentCode() {
		return toDepartmentCode;
	}

	public void setToDepartmentCode(String toDepartmentCode) {
		this.toDepartmentCode = toDepartmentCode;
	}

	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}

	public String getToDepartmentUnitCode() {
		return toDepartmentUnitCode;
	}

	public void setToDepartmentUnitCode(String toDepartmentUnitCode) {
		this.toDepartmentUnitCode = toDepartmentUnitCode;
	}

	public String getToDepartmentUnit() {
		return toDepartmentUnit;
	}

	public void setToDepartmentUnit(String toDepartmentUnit) {
		this.toDepartmentUnit = toDepartmentUnit;
	}

	public String getToDoctorCode() {
		return toDoctorCode;
	}

	public void setToDoctorCode(String toDoctorCode) {
		this.toDoctorCode = toDoctorCode;
	}

	public String getToWardCode() {
		return toWardCode;
	}

	public void setToWardCode(String toWardCode) {
		this.toWardCode = toWardCode;
	}

	public String getToWard() {
		return toWard;
	}

	public void setToWard(String toWard) {
		this.toWard = toWard;
	}

	public String getToEpisodeCode() {
		return toEpisodeCode;
	}

	public void setToEpisodeCode(String toEpisodeCode) {
		this.toEpisodeCode = toEpisodeCode;
	}

	public String getToEpisodeVisitNo() {
		return toEpisodeVisitNo;
	}

	public void setToEpisodeVisitNo(String toEpisodeVisitNo) {
		this.toEpisodeVisitNo = toEpisodeVisitNo;
	}

	public String getExternalHospitalCode() {
		return externalHospitalCode;
	}

	public void setExternalHospitalCode(String externalHospitalCode) {
		this.externalHospitalCode = externalHospitalCode;
	}

	public String getExternalHospitalName() {
		return externalHospitalName;
	}

	public void setExternalHospitalName(String externalHospitalName) {
		this.externalHospitalName = externalHospitalName;
	}

	public String getExternalHospitalDoctorName() {
		return externalHospitalDoctorName;
	}

	public void setExternalHospitalDoctorName(String externalHospitalDoctorName) {
		this.externalHospitalDoctorName = externalHospitalDoctorName;
	}

	public String getExternalHospitalPatCrNo() {
		return externalHospitalPatCrNo;
	}

	public void setExternalHospitalPatCrNo(String externalHospitalPatCrNo) {
		this.externalHospitalPatCrNo = externalHospitalPatCrNo;
	}

	public String getExternalHospitalDepartment() {
		return externalHospitalDepartment;
	}

	public void setExternalHospitalDepartment(String externalHospitalDepartment) {
		this.externalHospitalDepartment = externalHospitalDepartment;
	}

	public String getExternalHospitalDepartmentUnit() {
		return externalHospitalDepartmentUnit;
	}

	public void setExternalHospitalDepartmentUnit(
			String externalHospitalDepartmentUnit) {
		this.externalHospitalDepartmentUnit = externalHospitalDepartmentUnit;
	}

	public String getIsRefferInOut() {
		return isRefferInOut;
	}

	public void setIsRefferInOut(String isRefferInOut) {
		this.isRefferInOut = isRefferInOut;
	}

	public String getReffDateTime() {
		return reffDateTime;
	}

	public void setReffDateTime(String reffDateTime) {
		this.reffDateTime = reffDateTime;
	}

	public String getEpisodeReferAcceptDate() {
		return episodeReferAcceptDate;
	}

	public void setEpisodeReferAcceptDate(String episodeReferAcceptDate) {
		this.episodeReferAcceptDate = episodeReferAcceptDate;
	}

	public String getSystemIPAddress() {
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
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

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedSeatId() {
		return lastModifiedSeatId;
	}

	public void setLastModifiedSeatId(String lastModifiedSeatId) {
		this.lastModifiedSeatId = lastModifiedSeatId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

		
	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getCrNoToModify() {
		return crNoToModify;
	}

	public void setCrNoToModify(String crNoToModify) {
		this.crNoToModify = crNoToModify;
	}

	

	

	public PatientReferralSUP()
	{
		
	}
	
	public void clear()
	{
				
	}
	

	public HttpServletRequest getObjRequest()
	{
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest)
	{
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse()
	{
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse)
	{
		this.objResponse = objResponse;
	}

	/*
	 * @Override public CountryModel getModel() { System.out.println("Inside CountryAction ::: getModel()"); return
	 * modelCountry; }
	 */

	public ServletContext getObjContext() {
		return objContext;
	}

	public void setObjContext(ServletContext objContext) {
		this.objContext = objContext;
	}

	public Map getMapSesion() {
		return mapSesion;
	}

	public void setMapSesion(Map mapSesion) {
		this.mapSesion = mapSesion;
	}

	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	@Override 
	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	@Override 
	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}

	public String getIsDesk() {
		return isDesk;
	}

	public void setIsDesk(String isDesk) {
		this.isDesk = isDesk;
	}
	public String getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public String getAppointmentQueueNo() {
		return appointmentQueueNo;
	}
	public void setAppointmentQueueNo(String appointmentQueueNo) {
		this.appointmentQueueNo = appointmentQueueNo;
	}
}
