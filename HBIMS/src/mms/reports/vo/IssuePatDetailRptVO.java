/**
 * Developer : Anurudra Goel
 * Version : 1.0
 * Date : 17/July/2009
 *  
*/
package mms.reports.vo;

import javax.sql.rowset.WebRowSet;

public class IssuePatDetailRptVO {
	private String strHospCode="";
	private String strStoreId="";
	private String strItemId="";
	private String IssueChkDetail="1";
	private String strBatchhNo="1";
	private String strMsgType="0";
	private String strMsgString="";
	private String strSeatId="";
	
	private WebRowSet strWS=null;
	private WebRowSet itemCategWS=null;
	public WebRowSet getStrWS() {
		return strWS;
	}
	public void setStrWS(WebRowSet strWS) {
		this.strWS = strWS;
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
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrBatchhNo() {
		return strBatchhNo;
	}
	public void setStrBatchhNo(String strBatchhNo) {
		this.strBatchhNo = strBatchhNo;
	}
	public String getIssueChkDetail() {
		return IssueChkDetail;
	}
	public void setIssueChkDetail(String issueChkDetail) {
		IssueChkDetail = issueChkDetail;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public WebRowSet getItemCategWS() {
		return itemCategWS;
	}
	public void setItemCategWS(WebRowSet itemCategWS) {
		this.itemCategWS = itemCategWS;
	}

}
