/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.ot.vo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;

public class EHRSection_OTDetailsVO extends ValueObject
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
	
	private String selOperationName;
    private String selSurgeonName;
	private String selSergeonCode;
	private String selOperationCode;
	private String selOperativeFindings;
	private String selOperationDate;
	private String selOperatonComplications;
	
	private String selOperatonImplant;
	private String selOperatonPreOp;
	private String selOperatonPostOp;
	
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
	public String getOperationSummary() {
		return operationSummary;
	}
	public void setOperationSummary(String operationSummary) {
		this.operationSummary = operationSummary;
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
	public String getSelSergeonCode() {
		return selSergeonCode;
	}
	public void setSelSergeonCode(String selSergeonCode) {
		this.selSergeonCode = selSergeonCode;
	}
	public String getSelOperationCode() {
		return selOperationCode;
	}
	public void setSelOperationCode(String selOperationCode) {
		this.selOperationCode = selOperationCode;
	}
	public String getSelOperativeFindings() {
		return selOperativeFindings;
	}
	public void setSelOperativeFindings(String selOperativeFindings) {
		this.selOperativeFindings = selOperativeFindings;
	}
	public String getSelOperationDate() {
		return selOperationDate;
	}
	public void setSelOperationDate(String selOperationDate) {
		this.selOperationDate = selOperationDate;
	}
	public String getSelOperatonComplications() {
		return selOperatonComplications;
	}
	public void setSelOperatonComplications(String selOperatonComplications) {
		this.selOperatonComplications = selOperatonComplications;
	}
	public String getSlno() {
		return slno;
	}
	public void setSlno(String slno) {
		this.slno = slno;
	}
	public String getSelOperatonImplant() {
		return selOperatonImplant;
	}
	public void setSelOperatonImplant(String selOperatonImplant) {
		this.selOperatonImplant = selOperatonImplant;
	}
	public String getSelOperatonPreOp() {
		return selOperatonPreOp;
	}
	public void setSelOperatonPreOp(String selOperatonPreOp) {
		this.selOperatonPreOp = selOperatonPreOp;
	}
	public String getSelOperatonPostOp() {
		return selOperatonPostOp;
	}
	public void setSelOperatonPostOp(String selOperatonPostOp) {
		this.selOperatonPostOp = selOperatonPostOp;
	}
	
}
