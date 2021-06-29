package new_investigation.vo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import hisglobal.vo.ValueObject;



public class machineResultEntryVO extends ValueObject
{
	                    
	private String seatId;
	private String allrefrange;
	
	private String patCrNo;
	private int startIndex;
	private int endIndex;
	private String resultArray;
	private String testCode;
	private String labCode;
	private String machineCode;
	private String record;
	private String sampleCollDate="";
	private String resultEntryDate="";
	private String reqDNo;
	private String parameterCode;
	private String labNo;
	private String machineTestParameterCode;
	private String machineTestParameterName;
	private String machineResult;
	private String machineResultEntryDate;
	private String machineLabSampleNo;
	private String machineRecordId;
	private String machineReqId;
	private String reqDtlStatus;
	private String parentId;
	private String machineTestParameterParaCount;
	private String sampleStatus;
	private String resultStatus;
	private String patGender;
	private String patAge;
	private String refRange;
	private String patcrno1;
	private String samplenoo;
	
	private String paraname;
	private String testname;
	private String preference;
	private String grpname;
	private String grpcode;
	private String machinename;
	private String shortgrpcode;
	private String ranges;
	private String isrepeattest;
	private String labName;

	private String machineId;
	private String is_inrange;
	private String is_callfrom_newmachine_process;
	
	private String priority;
	private String is_patdead;
	private String as_patmlc;
	private String is_pregnant;
	private String is_newborn;
	private String is_vip;
	private String pattype;
	private String mlcno ;
	private String reqDate ;

private String is_unknown ;
	
	private String admissionno;
	
	private String bedName="";
	private String mobileNo;

	private String patStatus;
    private String wardName;
 	private String patCategory;

 	
	public String getReqDNo() {
		return reqDNo;
	}
	public void setReqDNo(String reqDNo) {
		this.reqDNo = reqDNo;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getResultArray() {
		return resultArray;
	}
	public void setResultArray(String resultArray) {
		this.resultArray = resultArray;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getSampleCollDate() {
		return sampleCollDate;
	}
	public void setSampleCollDate(String sampleCollDate) {
		this.sampleCollDate = sampleCollDate;
	}
	public String getResultEntryDate() {
		return resultEntryDate;
	}
	public void setResultEntryDate(String resultEntryDate) {
		this.resultEntryDate = resultEntryDate;
	}
	public String getLabNo() {
		return labNo;
	}
	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}
	public String getMachineTestParameterCode() {
		return machineTestParameterCode;
	}
	public void setMachineTestParameterCode(String machineTestParameterCode) {
		this.machineTestParameterCode = machineTestParameterCode;
	}
	public String getMachineTestParameterName() {
		return machineTestParameterName;
	}
	public void setMachineTestParameterName(String machineTestParameterName) {
		this.machineTestParameterName = machineTestParameterName;
	}
	public String getMachineResult() {
		return machineResult;
	}
	public void setMachineResult(String machineResult) {
		this.machineResult = machineResult;
	}
	public String getMachineResultEntryDate() {
		return machineResultEntryDate;
	}
	public void setMachineResultEntryDate(String machineResultEntryDate) {
		this.machineResultEntryDate = machineResultEntryDate;
	}
	public String getMachineLabSampleNo() {
		return machineLabSampleNo;
	}
	public void setMachineLabSampleNo(String machineLabSampleNo) {
		this.machineLabSampleNo = machineLabSampleNo;
	}
	public String getMachineRecordId() {
		return machineRecordId;
	}
	public void setMachineRecordId(String machineRecordId) {
		this.machineRecordId = machineRecordId;
	}
	public String getMachineReqId() {
		return machineReqId;
	}
	public void setMachineReqId(String machineReqId) {
		this.machineReqId = machineReqId;
	}
	public String getReqDtlStatus() {
		return reqDtlStatus;
	}
	public void setReqDtlStatus(String reqDtlStatus) {
		this.reqDtlStatus = reqDtlStatus;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getmachineTestParameterParaCount() {
		return machineTestParameterParaCount;
	}
	public void setmachineTestParameterParaCount(
			String machineTestParameterParaCount) {
		this.machineTestParameterParaCount = machineTestParameterParaCount;
	}
	public String getSampleStatus() {
		return sampleStatus;
	}
	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getRefRange() {
		return refRange;
	}
	public void setRefRange(String refRange) {
		this.refRange = refRange;
	}
	
	public String getSamplenoo() {
		return samplenoo;
	}
	public void setSamplenoo(String samplenoo) {
		this.samplenoo = samplenoo;
	}
	public String getPatcrno1() {
		return patcrno1;
	}
	public void setPatcrno1(String patcrno1) {
		this.patcrno1 = patcrno1;
	}
	public String getMachineTestParameterParaCount() {
		return machineTestParameterParaCount;
	}
	public void setMachineTestParameterParaCount(
			String machineTestParameterParaCount) {
		this.machineTestParameterParaCount = machineTestParameterParaCount;
	}
	public String getParaname() {
		return paraname;
	}
	public void setParaname(String paraname) {
		this.paraname = paraname;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public String getGrpname() {
		return grpname;
	}
	public void setGrpname(String grpname) {
		this.grpname = grpname;
	}
	public String getGrpcode() {
		return grpcode;
	}
	public void setGrpcode(String grpcode) {
		this.grpcode = grpcode;
	}
	public String getMachinename() {
		return machinename;
	}
	public void setMachinename(String machinename) {
		this.machinename = machinename;
	}
	public String getShortgrpcode() {
		return shortgrpcode;
	}
	public void setShortgrpcode(String shortgrpcode) {
		this.shortgrpcode = shortgrpcode;
	}
	public String getRanges() {
		return ranges;
	}
	public void setRanges(String ranges) {
		this.ranges = ranges;
	}
	public String getIsrepeattest() {
		return isrepeattest;
	}
	public void setIsrepeattest(String isrepeattest) {
		this.isrepeattest = isrepeattest;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getIs_inrange() {
		return is_inrange;
	}
	public void setIs_inrange(String is_inrange) {
		this.is_inrange = is_inrange;
	}
	public String getIs_callfrom_newmachine_process() {
		return is_callfrom_newmachine_process;
	}
	public void setIs_callfrom_newmachine_process(String is_callfrom_newmachine_process) {
		this.is_callfrom_newmachine_process = is_callfrom_newmachine_process;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIs_patdead() {
		return is_patdead;
	}
	public void setIs_patdead(String is_patdead) {
		this.is_patdead = is_patdead;
	}
	public String getAs_patmlc() {
		return as_patmlc;
	}
	public void setAs_patmlc(String as_patmlc) {
		this.as_patmlc = as_patmlc;
	}
	public String getIs_pregnant() {
		return is_pregnant;
	}
	public void setIs_pregnant(String is_pregnant) {
		this.is_pregnant = is_pregnant;
	}
	public String getIs_newborn() {
		return is_newborn;
	}
	public void setIs_newborn(String is_newborn) {
		this.is_newborn = is_newborn;
	}
	public String getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}
	public String getPattype() {
		return pattype;
	}
	public void setPattype(String pattype) {
		this.pattype = pattype;
	}
	public String getMlcno() {
		return mlcno;
	}
	public void setMlcno(String mlcno) {
		this.mlcno = mlcno;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getIs_unknown() {
		return is_unknown;
	}
	public void setIs_unknown(String is_unknown) {
		this.is_unknown = is_unknown;
	}
	public String getAdmissionno() {
		return admissionno;
	}
	public void setAdmissionno(String admissionno) {
		this.admissionno = admissionno;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getPatCategory() {
		return patCategory;
	}
	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}
	public String getAllrefrange() {
		return allrefrange;
	}
	public void setAllrefrange(String allrefrange) {
		this.allrefrange = allrefrange;
	}

	
	


}

