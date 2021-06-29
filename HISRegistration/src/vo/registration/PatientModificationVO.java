package vo.registration;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public  class PatientModificationVO
{
	protected static final long serialVersionUID = 1L;
	protected String processType;
	protected String requestBy;
	protected String strRemarks;
	
	protected String requestByName;
	protected String requestByAddress;
	protected String patCardNo;
	protected String constableDesig;
	protected String constableBadgeNo;
	protected String strDocumentId;
	protected String strDocumentCode;
	protected String strCardID;
	protected String strIsUpload;
	
	protected String patCrNo;
	protected String requestRelation;
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getRequestByName() {
		return requestByName;
	}
	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}
	public String getRequestByAddress() {
		return requestByAddress;
	}
	public void setRequestByAddress(String requestByAddress) {
		this.requestByAddress = requestByAddress;
	}
	public String getPatCardNo() {
		return patCardNo;
	}
	public void setPatCardNo(String patCardNo) {
		this.patCardNo = patCardNo;
	}
	public String getConstableDesig() {
		return constableDesig;
	}
	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}
	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}
	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}

	public String getStrDocumentId() {
		return strDocumentId;
	}
	public void setStrDocumentId(String strDocumentId) {
		this.strDocumentId = strDocumentId;
	}
	public String getStrDocumentCode() {
		return strDocumentCode;
	}
	public void setStrDocumentCode(String strDocumentCode) {
		this.strDocumentCode = strDocumentCode;
	}
	public String getStrCardID() {
		return strCardID;
	}
	public void setStrCardID(String strCardID) {
		this.strCardID = strCardID;
	}
	public String getStrIsUpload() {
		return strIsUpload;
	}
	public void setStrIsUpload(String strIsUpload) {
		this.strIsUpload = strIsUpload;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getRequestRelation() {
		return requestRelation;
	}
	public void setRequestRelation(String requestRelation) {
		this.requestRelation = requestRelation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
