package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class ConsentRequestFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;// for HRGNUM_EPISODE_NO
	private String episodeVisitNo;// for HRGNUM_VISIT_NO
	private String[] requestID;
	private String[] serviceTypeId;
	private String[] serviceId;
	private String templateId;
	private String[] consentId;
	private String templateDesc;
	private String serviceTypeDesc;
	private String serviceDesc;
	private String raisedBy;
	private String receivedBy;
	private String consentStatus;
	private String raisedDate;
	private String doctorId;
	private String[] chk;

	private String[] givenBy;
	private String[] relationCode;
	private String[] relativeIdRemark;
	private String[] relativeContactNo;
	private String[] relativeAddr;
	private String[] relativeName;
	private String[] serviceConsentId;

	private String patAdmNo;
	private String consentHtmlCode;
	private String isDirectCall;
	//for pagination
	private int currentPage;
	
	private String hiddenPatDeadStatus;
	

	public String getHiddenPatDeadStatus() {
		return hiddenPatDeadStatus;
	}

	public void setHiddenPatDeadStatus(String hiddenPatDeadStatus) {
		this.hiddenPatDeadStatus = hiddenPatDeadStatus;
	}

	public ConsentRequestFB()
	{
		this.currentPage=1;
	}
	

	public String getPatAdmNo()
	{
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo)
	{
		this.patAdmNo = patAdmNo;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.relationCode = null;
		this.relativeAddr = null;
		this.relativeContactNo = null;
		this.relativeIdRemark = null;
		this.relativeName = null;
		this.givenBy = null;
	}

	public String[] getRelationCode()
	{
		return relationCode;
	}

	public void setRelationCode(String[] relationCode)
	{
		this.relationCode = relationCode;
	}

	public String[] getRelativeIdRemark()
	{
		return relativeIdRemark;
	}

	public void setRelativeIdRemark(String[] relativeIdRemark)
	{
		this.relativeIdRemark = relativeIdRemark;
	}

	public String[] getRelativeContactNo()
	{
		return relativeContactNo;
	}

	public void setRelativeContactNo(String[] relativeContactNo)
	{
		this.relativeContactNo = relativeContactNo;
	}

	public String[] getRelativeAddr()
	{
		return relativeAddr;
	}

	public void setRelativeAddr(String[] relativeAddr)
	{
		this.relativeAddr = relativeAddr;
	}

	public String[] getRelativeName()
	{
		return relativeName;
	}

	public void setRelativeName(String[] relativeName)
	{
		this.relativeName = relativeName;
	}

	public String getTemplateDesc()
	{
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc)
	{
		this.templateDesc = templateDesc;
	}

	public String getServiceTypeDesc()
	{
		return serviceTypeDesc;
	}

	public void setServiceTypeDesc(String serviceTypeDesc)
	{
		this.serviceTypeDesc = serviceTypeDesc;
	}

	public String getServiceDesc()
	{
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc)
	{
		this.serviceDesc = serviceDesc;
	}

	public String getRaisedBy()
	{
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy)
	{
		this.raisedBy = raisedBy;
	}

	public String getReceivedBy()
	{
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy)
	{
		this.receivedBy = receivedBy;
	}

	public String getConsentStatus()
	{
		return consentStatus;
	}

	public void setConsentStatus(String consentStatus)
	{
		this.consentStatus = consentStatus;
	}

	public String getRaisedDate()
	{
		return raisedDate;
	}

	public void setRaisedDate(String raisedDate)
	{
		this.raisedDate = raisedDate;
	}

	public String getDoctorId()
	{
		return doctorId;
	}

	public void setDoctorId(String doctorId)
	{
		this.doctorId = doctorId;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String[] getServiceTypeId()
	{
		return serviceTypeId;
	}

	public String[] getRequestID()
	{
		return requestID;
	}

	public void setRequestID(String[] requestID)
	{
		this.requestID = requestID;
	}

	public void setServiceTypeId(String[] serviceTypeId)
	{
		this.serviceTypeId = serviceTypeId;
	}

	public String[] getServiceId()
	{
		return serviceId;
	}

	public void setServiceId(String[] serviceId)
	{
		this.serviceId = serviceId;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String[] getConsentId()
	{
		return consentId;
	}

	public void setConsentId(String[] consentId)
	{
		this.consentId = consentId;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
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

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String[] getGivenBy()
	{
		return givenBy;
	}

	public void setGivenBy(String[] givenBy)
	{
		this.givenBy = givenBy;
	}

	public String getConsentHtmlCode()
	{
		return consentHtmlCode;
	}

	public void setConsentHtmlCode(String consentHtmlCode)
	{
		this.consentHtmlCode = consentHtmlCode;
	}

	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String[] getServiceConsentId() {
		return serviceConsentId;
	}

	public void setServiceConsentId(String[] serviceConsentId) {
		this.serviceConsentId = serviceConsentId;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
