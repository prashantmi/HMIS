package bmed.vo;

import hisglobal.vo.ValueObject;

import javax.sql.rowset.WebRowSet;

public class TestParameterDtlVO extends ValueObject
{
	  private String strMode;
	  private String strTestParaId;
	  private String strTestParaEquId;
	  private String strTestParaName;
	  private String strOutPut;
	  private String strEffFrom;
	  private String strRemarks;
	  private String strEntryDate;
	  private String strSeatId;
	  private String strIsValid;
	  private String strLastModDate;
	  private String strLastModSeatId;
	  private String strMsgString;
	  private String strMsgType;
	  private String strHospitalCode;
	  private WebRowSet wrsData;
	  private String strTestId;
	public String getStrTestId() {
		return strTestId;
	}
	public void setStrTestId(String strTestId) {
		this.strTestId = strTestId;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	
	
	public String getStrTestParaId() {
		return strTestParaId;
	}
	public void setStrTestParaId(String strTestParaId) {
		this.strTestParaId = strTestParaId;
	}
	public String getStrTestParaName() {
		return strTestParaName;
	}
	public void setStrTestParaName(String strTestParaName) {
		this.strTestParaName = strTestParaName;
	}

	public String getStrEffFrom() {
		return strEffFrom;
	}
	public void setStrEffFrom(String strEffFrom) {
		this.strEffFrom = strEffFrom;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrEntryDate() {
		return strEntryDate;
	}
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrLastModDate() {
		return strLastModDate;
	}
	public void setStrLastModDate(String strLastModDate) {
		this.strLastModDate = strLastModDate;
	}
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}
	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}
	public WebRowSet getWrsData() {
		return wrsData;
	}
	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	
	
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrTestParaEquId() {
		return strTestParaEquId;
	}
	public void setStrTestParaEquId(String strTestParaEquId) {
		this.strTestParaEquId = strTestParaEquId;
	}
	public String getStrOutPut() {
		return strOutPut;
	}
	public void setStrOutPut(String strOutPut) {
		this.strOutPut = strOutPut;
	}
	  

}
