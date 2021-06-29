package appointment.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class AppointmentGlobalSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	
	protected String appointmentForId;
	protected String appointmentName;
	protected String sequenceNo;
	protected String[] parameterId;	
	protected String[] actualParameterId;
	protected String actualParameterName;
	protected String parentParameterId;
	protected String actualParameterReferenceId;
	protected String tagView;
	protected String parentParameterActualValue;
	protected String supportClassName;
	protected String displayOrder;
	protected String allActualParameterId;
	protected String appointmentDate;
	protected String shiftId;
	protected String appointmentNo;
	protected String listingMode;
	protected String shiftST;
	protected String shiftET;
	protected String slotST;
	protected String slotET;
	protected String aptTagHTML;
	protected String moduleSpecificCode;
	protected String moduleSpecificKeyName;
	protected String slotType;
	protected String patCrNo;
	
	
	
	public String getListingMode() {
		return listingMode;
	}

	public void setListingMode(String listingMode) {
		this.listingMode = listingMode;
	}

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getAllActualParameterId() {
		return allActualParameterId;
	}

	public void setAllActualParameterId(String allActualParameterId) {
		this.allActualParameterId = allActualParameterId;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getSupportClassName() {
		return supportClassName;
	}

	public void setSupportClassName(String supportClassName) {
		this.supportClassName = supportClassName;
	}

	public String getParentParameterActualValue() {
		return parentParameterActualValue;
	}

	public void setParentParameterActualValue(String parentParameterActualValue) {
		this.parentParameterActualValue = parentParameterActualValue;
	}

	public String getActualParameterName() {
		return actualParameterName;
	}

	public void setActualParameterName(String actualParameterName) {
		this.actualParameterName = actualParameterName;
	}

	public String getTagView() {
		return tagView;
	}

	public void setTagView(String tagView) {
		this.tagView = tagView;
	}

	public void setServletContext(ServletContext context) {
		this.objContext=context;
		
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
	
	public void prepare() throws Exception {
		
		
	}

	

	
	public String getAppointmentForId() {
		return appointmentForId;
	}

	public void setAppointmentForId(String appointmentForId) {
		this.appointmentForId = appointmentForId;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String[] getParameterId() {
		return parameterId;
	}

	public void setParameterId(String[] parameterId) {
		this.parameterId = parameterId;
	}

	public String[] getActualParameterId() {
		return actualParameterId;
	}

	public void setActualParameterId(String[] actualParameterId) {
		this.actualParameterId = actualParameterId;
	}

	
	public String getParentParameterId() {
		return parentParameterId;
	}

	public void setParentParameterId(String parentParameterId) {
		this.parentParameterId = parentParameterId;
	}

	public String getActualParameterReferenceId() {
		return actualParameterReferenceId;
	}

	public void setActualParameterReferenceId(String actualParameterReferenceId) {
		this.actualParameterReferenceId = actualParameterReferenceId;
	}

	public String getAppointmentName() {
		return appointmentName;
	}

	public void setAppointmentName(String appointmentName) {
		this.appointmentName = appointmentName;
	}

	public String getShiftST() {
		return shiftST;
	}

	public void setShiftST(String shiftST) {
		this.shiftST = shiftST;
	}

	public String getShiftET() {
		return shiftET;
	}

	public void setShiftET(String shiftET) {
		this.shiftET = shiftET;
	}

	public String getSlotST() {
		return slotST;
	}

	public void setSlotST(String slotST) {
		this.slotST = slotST;
	}

	public String getSlotET() {
		return slotET;
	}

	public void setSlotET(String slotET) {
		this.slotET = slotET;
	}

	public String getAptTagHTML() {
		return aptTagHTML;
	}

	public void setAptTagHTML(String aptTagHTML) {
		this.aptTagHTML = aptTagHTML;
	}

	public String getModuleSpecificCode() {
		return moduleSpecificCode;
	}

	public void setModuleSpecificCode(String moduleSpecificCode) {
		this.moduleSpecificCode = moduleSpecificCode;
	}

	public String getModuleSpecificKeyName() {
		return moduleSpecificKeyName;
	}

	public void setModuleSpecificKeyName(String moduleSpecificKeyName) {
		this.moduleSpecificKeyName = moduleSpecificKeyName;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

		
}
