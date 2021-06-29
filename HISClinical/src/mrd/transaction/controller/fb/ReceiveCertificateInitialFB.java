package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReceiveCertificateInitialFB extends ActionForm
{
	private String mode;
	private String recordType;
	private String recordTypeName;
	private String mrdCode;
	private String  strCheckMode;//added by swati sagar on date:08-04-2019
	private String  strCrNo;//added by swati sagar on date:03-08-2019	
	private String wardCodeNew;//added by swati sagar on date:08-04-2019
	private String clearmsg;//added by swati sagar on date:08-may-2019
	private String patAdmNo;//added by swati sagar on date:08-may-2019
	private String isMrdListOne;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getIsMrdListOne() {
		return isMrdListOne;
	}
	public void setIsMrdListOne(String isMrdListOne) {
		this.isMrdListOne = isMrdListOne;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRecordType("");
		this.setMrdCode("");
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getStrCheckMode() {
		return strCheckMode;
	}
	public void setStrCheckMode(String strCheckMode) {
		this.strCheckMode = strCheckMode;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getWardCodeNew() {
		return wardCodeNew;
	}
	public void setWardCodeNew(String wardCodeNew) {
		this.wardCodeNew = wardCodeNew;
	}
	public String getClearmsg() {
		return clearmsg;
	}
	public void setClearmsg(String clearmsg) {
		this.clearmsg = clearmsg;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
}
