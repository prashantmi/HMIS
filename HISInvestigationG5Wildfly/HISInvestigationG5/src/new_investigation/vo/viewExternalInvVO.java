package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class viewExternalInvVO extends ValueObject {

	private String patCrNo;
	private String RequisitionNo;
	private String testName;
	private String patCRNo;
	private String patName;
	private String reqDate;
    private String fileName;
    private String id;
    private String contentType;
    private String requisitionDno;
    private String fileUploadData;
   
    public String getRequisitionDno() {
		return requisitionDno;
	}

	public void setRequisitionDno(String requisitionDno) {
		this.requisitionDno = requisitionDno;
	}

	public String getFileUploadData() {
		return fileUploadData;
	}

	public void setFileUploadData(String fileUploadData) {
		this.fileUploadData = fileUploadData;
	}

	//added by krishnan nema on 19/11/2018
    private String labName;
    
	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getRequisitionNo() {
		return RequisitionNo;
	}

	public void setRequisitionNo(String requisitionNo) {
		RequisitionNo = requisitionNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getPatCRNo() {
		return patCRNo;
	}

	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}
	
	
	
    
	
}
