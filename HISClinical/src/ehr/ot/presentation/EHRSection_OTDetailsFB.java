/**
##		Date					: 19-01-2018
##		Reason	(CR/PRS)		: Treatment Given new process
##		Created By				: Vasu
*/


package ehr.ot.presentation;

import javax.servlet.http.HttpServletRequest;




import org.apache.struts.action.ActionMapping;

import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import registration.controller.fb.CRNoFB;

public class EHRSection_OTDetailsFB extends CRNoFB
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admissionNo;
	private String visitDate;
	private String operations;
	private String operationDate;
	private String surgeons;
	private String operativeFindings;
	private String operationSummary;
	private String operationCode;
	private String snomdPTOperativeFindings;
	private String snomdCIdOperativeFindings;
	private String snomedPTOperationSummary;
	private String snomedCidOperationSummary;
	private String surgeonCode;
	private String operationComplications;
	private String entryDate;
	
	private String selOperationName;
	private String selSurgeonName;
	private String[] selSergeonCode;
	private String[] selOperationCode;
	private String[] selOperativeFindings;
	private String[] selOperationDate;
	
	
	
	private String snomedPTOperationImplantSummary;
	private String snomedCidOperationImplantSummary;
	private String operationImplant;
	

	private String snomedPTOperationPreOpSummary;
	private String snomedCidOperationPreOpSummary;
	private String operationPreOp;
	

	private String snomedPTOperationPostOpSummary;
	private String snomedCidOperationPostOpSummary;
	private String operationPostOp;
	
	private String[] selOperatonImplant;
	private String[] selOperatonPreOp;
	private String[] selOperatonPostOp;
	
	
	private String slno;
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	
	public String getOperationSummary() {
		return operationSummary;
	}
	public void setOperationSummary(String operationSummary) {
		this.operationSummary = operationSummary;
	}
	
	public String getSnomdPTOperativeFindings() {
		return snomdPTOperativeFindings;
	}
	public void setSnomdPTOperativeFindings(String snomdPTOperativeFindings) {
		this.snomdPTOperativeFindings = snomdPTOperativeFindings;
	}
	public String getSnomdCIdOperativeFindings() {
		return snomdCIdOperativeFindings;
	}
	public void setSnomdCIdOperativeFindings(String snomdCIdOperativeFindings) {
		this.snomdCIdOperativeFindings = snomdCIdOperativeFindings;
	}
	public String getSnomedPTOperationSummary() {
		return snomedPTOperationSummary;
	}
	public void setSnomedPTOperationSummary(String snomedPTOperationSummary) {
		this.snomedPTOperationSummary = snomedPTOperationSummary;
	}
	public String getSnomedCidOperationSummary() {
		return snomedCidOperationSummary;
	}
	public void setSnomedCidOperationSummary(String snomedCidOperationSummary) {
		this.snomedCidOperationSummary = snomedCidOperationSummary;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(String operations) {
		this.operations = operations;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getSurgeons() {
		return surgeons;
	}
	public void setSurgeons(String surgeons) {
		this.surgeons = surgeons;
	}
	public String getOperativeFindings() {
		return operativeFindings;
	}
	public void setOperativeFindings(String operativeFindings) {
		this.operativeFindings = operativeFindings;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getSurgeonCode() {
		return surgeonCode;
	}
	public void setSurgeonCode(String surgeonCode) {
		this.surgeonCode = surgeonCode;
	}
	public String getSelOperationName() {
		return selOperationName;
	}
	public void setSelOperationName(String selOperationName) {
		this.selOperationName = selOperationName;
	}
	public String getSelSurgeonName() {
		return selSurgeonName;
	}
	public void setSelSurgeonName(String selSurgeonName) {
		this.selSurgeonName = selSurgeonName;
	}
	public String[] getSelSergeonCode() {
		return selSergeonCode;
	}
	public void setSelSergeonCode(String[] selSergeonCode) {
		this.selSergeonCode = selSergeonCode;
	}
	public String[] getSelOperationCode() {
		return selOperationCode;
	}
	public void setSelOperationCode(String[] selOperationCode) {
		this.selOperationCode = selOperationCode;
	}
	public String[] getSelOperativeFindings() {
		return selOperativeFindings;
	}
	public void setSelOperativeFindings(String[] selOperativeFindings) {
		this.selOperativeFindings = selOperativeFindings;
	}
	public String[] getSelOperationDate() {
		return selOperationDate;
	}
	public void setSelOperationDate(String[] selOperationDate) {
		this.selOperationDate = selOperationDate;
	}
	public String getOperationComplications() {
		return operationComplications;
	}
	public void setOperationComplications(String operationComplications) {
		this.operationComplications = operationComplications;
	}
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSnomedPTOperationImplantSummary() {
		return snomedPTOperationImplantSummary;
	}
	public void setSnomedPTOperationImplantSummary(
			String snomedPTOperationImplantSummary) {
		this.snomedPTOperationImplantSummary = snomedPTOperationImplantSummary;
	}
	public String getSnomedCidOperationImplantSummary() {
		return snomedCidOperationImplantSummary;
	}
	public void setSnomedCidOperationImplantSummary(
			String snomedCidOperationImplantSummary) {
		this.snomedCidOperationImplantSummary = snomedCidOperationImplantSummary;
	}
	public String getOperationImplant() {
		return operationImplant;
	}
	public void setOperationImplant(String operationImplant) {
		this.operationImplant = operationImplant;
	}
	public String getSnomedPTOperationPreOpSummary() {
		return snomedPTOperationPreOpSummary;
	}
	public void setSnomedPTOperationPreOpSummary(
			String snomedPTOperationPreOpSummary) {
		this.snomedPTOperationPreOpSummary = snomedPTOperationPreOpSummary;
	}
	public String getSnomedCidOperationPreOpSummary() {
		return snomedCidOperationPreOpSummary;
	}
	public void setSnomedCidOperationPreOpSummary(
			String snomedCidOperationPreOpSummary) {
		this.snomedCidOperationPreOpSummary = snomedCidOperationPreOpSummary;
	}
	public String getOperationPreOp() {
		return operationPreOp;
	}
	public void setOperationPreOp(String operationPreOp) {
		this.operationPreOp = operationPreOp;
	}
	public String getSnomedPTOperationPostOpSummary() {
		return snomedPTOperationPostOpSummary;
	}
	public void setSnomedPTOperationPostOpSummary(
			String snomedPTOperationPostOpSummary) {
		this.snomedPTOperationPostOpSummary = snomedPTOperationPostOpSummary;
	}
	public String getSnomedCidOperationPostOpSummary() {
		return snomedCidOperationPostOpSummary;
	}
	public void setSnomedCidOperationPostOpSummary(
			String snomedCidOperationPostOpSummary) {
		this.snomedCidOperationPostOpSummary = snomedCidOperationPostOpSummary;
	}
	public String getOperationPostOp() {
		return operationPostOp;
	}
	public void setOperationPostOp(String operationPostOp) {
		this.operationPostOp = operationPostOp;
	}
	public String[] getSelOperatonImplant() {
		return selOperatonImplant;
	}
	public void setSelOperatonImplant(String[] selOperatonImplant) {
		this.selOperatonImplant = selOperatonImplant;
	}
	public String[] getSelOperatonPreOp() {
		return selOperatonPreOp;
	}
	public void setSelOperatonPreOp(String[] selOperatonPreOp) {
		this.selOperatonPreOp = selOperatonPreOp;
	}
	public String[] getSelOperatonPostOp() {
		return selOperatonPostOp;
	}
	public void setSelOperatonPostOp(String[] selOperatonPostOp) {
		this.selOperatonPostOp = selOperatonPostOp;
	}
	
		
}

