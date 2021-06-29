package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RecordLostInMrdFB extends ActionForm
{
	private String mrdRecordId;
	private String slNo;
	private String lostDateTime;
	private String reportedBy;
	private String recordId;
	private String recordDesc;
	private String hiddenRecordId;
	private String lastSeenDateTime;
	private String lastSeenDate;
	private String lastSeenTimeHr;
	private String lastSeenTimeMin;
	private String lostArea;
	private String lostType;
	private String lostRemarks;
	private String foundDateTime;
	private String foundRemarks;
	private String foundBy;
	private String hmode;
	
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String sysDate="";
	
	///Search
	private String hrgnum_puk;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String hipnum_admno;
	private String hrgnum_mlc_no;
	private String hipdt_disstatus_code;
	private String hrgnum_isunknown;
	private String hrgnum_is_broughtdead;
	private String fromDate="";
	private String toDate="";
	private String deathCase;
	private String gnum_gender_code;
	private String hrgnum_name;
	private String selectedIndex;
	private String selectRecord;
	private String concatedIndex;
	private String recordType;
	private String entryDate;
	
	
	
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getHrgnum_puk() {
		return hrgnum_puk;
	}
	public void setHrgnum_puk(String hrgnum_puk) {
		this.hrgnum_puk = hrgnum_puk;
	}
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
	public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	public String getHipnum_admno() {
		return hipnum_admno;
	}
	public void setHipnum_admno(String hipnum_admno) {
		this.hipnum_admno = hipnum_admno;
	}
	public String getHrgnum_mlc_no() {
		return hrgnum_mlc_no;
	}
	public void setHrgnum_mlc_no(String hrgnum_mlc_no) {
		this.hrgnum_mlc_no = hrgnum_mlc_no;
	}
	public String getHipdt_disstatus_code() {
		return hipdt_disstatus_code;
	}
	public void setHipdt_disstatus_code(String hipdt_disstatus_code) {
		this.hipdt_disstatus_code = hipdt_disstatus_code;
	}
	public String getHrgnum_isunknown() {
		return hrgnum_isunknown;
	}
	public void setHrgnum_isunknown(String hrgnum_isunknown) {
		this.hrgnum_isunknown = hrgnum_isunknown;
	}
	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}
	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
	}
	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}
	public String getHrgnum_name() {
		return hrgnum_name;
	}
	public void setHrgnum_name(String hrgnum_name) {
		this.hrgnum_name = hrgnum_name;
	}
	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String getConcatedIndex() {
		return concatedIndex;
	}
	public void setConcatedIndex(String concatedIndex) {
		this.concatedIndex = concatedIndex;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getLostDateTime() {
		return lostDateTime;
	}
	public void setLostDateTime(String lostDateTime) {
		this.lostDateTime = lostDateTime;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getLastSeenDateTime() {
		return lastSeenDateTime;
	}
	public void setLastSeenDateTime(String lastSeenDateTime) {
		this.lastSeenDateTime = lastSeenDateTime;
	}
	public String getLostArea() {
		return lostArea;
	}
	public void setLostArea(String lostArea) {
		this.lostArea = lostArea;
	}
	public String getLostType() {
		return lostType;
	}
	public void setLostType(String lostType) {
		this.lostType = lostType;
	}
	public String getLostRemarks() {
		return lostRemarks;
	}
	public void setLostRemarks(String lostRemarks) {
		this.lostRemarks = lostRemarks;
	}
	public String getFoundDateTime() {
		return foundDateTime;
	}
	public void setFoundDateTime(String foundDateTime) {
		this.foundDateTime = foundDateTime;
	}
	public String getFoundRemarks() {
		return foundRemarks;
	}
	public void setFoundRemarks(String foundRemarks) {
		this.foundRemarks = foundRemarks;
	}
	public String getFoundBy() {
		return foundBy;
	}
	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
	}
	public String getHiddenRecordId() {
		return hiddenRecordId;
	}
	public void setHiddenRecordId(String hiddenRecordId) {
		this.hiddenRecordId = hiddenRecordId;
	}
	public String getLastSeenDate() {
		return lastSeenDate;
	}
	public void setLastSeenDate(String lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}
	public String getLastSeenTimeHr() {
		return lastSeenTimeHr;
	}
	public void setLastSeenTimeHr(String lastSeenTimeHr) {
		this.lastSeenTimeHr = lastSeenTimeHr;
	}
	public String getLastSeenTimeMin() {
		return lastSeenTimeMin;
	}
	public void setLastSeenTimeMin(String lastSeenTimeMin) {
		this.lastSeenTimeMin = lastSeenTimeMin;
	}
	public String getSelectRecord() {
		return selectRecord;
	}
	public void setSelectRecord(String selectRecord) {
		this.selectRecord = selectRecord;
	}
	public String getRecordDesc() {
		return recordDesc;
	}
	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
	}
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setMrdRecordId("");
		this.setRecordDesc("");
		this.setRecordId("");
		this.setReportedBy("");
		this.setLastSeenDate("");
		this.setLastSeenTimeHr("");
		this.setLastSeenTimeMin("");
		this.setLostArea("");
		this.setLostRemarks("");
	}
}
