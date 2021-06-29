package bmed.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class logRegisterReportFB extends ActionForm{

		private static final long serialVersionUID = 02L;
		
		private String strNormalMsg="";
		private String strErrMsg="";
		private String strWarningMsg="";
		private String strHospCode="";
		private String strIsFooter="";
		private String strUserRemarks="";
		
		//private String strItemCategNo = "";
		private String strReportFormat = "0";
		private String strReportId = "";

		private String strFromDate = "";
		private String strToDate = "";
		private String strDeptId;
		private String strDeptCmb;
		private String strStoreId;
		private String strStoreCmb;
		private String strCurrentDate="";
		private String strComplaintId;
		private String strSeatId;

		//private String strItemDateOrDetailed ="1";

		public String getStrSeatId() {
			return strSeatId;
		}

		public void setStrSeatId(String strSeatId) {
			this.strSeatId = strSeatId;
		}

		public String getStrNormalMsg() {
			return strNormalMsg;
		}

		public void setStrNormalMsg(String strNormalMsg) {
			this.strNormalMsg = strNormalMsg;
		}

		public String getStrErrMsg() {
			return strErrMsg;
		}

		public void setStrErrMsg(String strErrMsg) {
			this.strErrMsg = strErrMsg;
		}

		public String getStrWarningMsg() {
			return strWarningMsg;
		}

		public void setStrWarningMsg(String strWarningMsg) {
			this.strWarningMsg = strWarningMsg;
		}

		public String getStrHospCode() {
			return strHospCode;
		}

		public void setStrHospCode(String strHospCode) {
			this.strHospCode = strHospCode;
		}

		public String getStrIsFooter() {
			return strIsFooter;
		}

		public void setStrIsFooter(String strIsFooter) {
			this.strIsFooter = strIsFooter;
		}

		public String getStrUserRemarks() {
			return strUserRemarks;
		}

		public void setStrUserRemarks(String strUserRemarks) {
			this.strUserRemarks = strUserRemarks;
		}

		/*public String getStrItemCategNo() {
			return strItemCategNo;
		}

		public void setStrItemCategNo(String strItemCategNo) {
			this.strItemCategNo = strItemCategNo;
		}*/

		public String getStrReportFormat() {
			return strReportFormat;
		}

		public void setStrReportFormat(String strReportFormat) {
			this.strReportFormat = strReportFormat;
		}

		public String getStrReportId() {
			return strReportId;
		}

		public void setStrReportId(String strReportId) {
			this.strReportId = strReportId;
		}

		public String getStrFromDate() {
			return strFromDate;
		}

		public void setStrFromDate(String strFromDate) {
			this.strFromDate = strFromDate;
		}

		public String getStrToDate() {
			return strToDate;
		}

		public void setStrToDate(String strToDate) {
			this.strToDate = strToDate;
		}

		public String getStrDeptId() {
			return strDeptId;
		}

		public void setStrDeptId(String strDeptId) {
			this.strDeptId = strDeptId;
		}

		public String getStrDeptCmb() {
			return strDeptCmb;
		}

		public void setStrDeptCmb(String strDeptCmb) {
			this.strDeptCmb = strDeptCmb;
		}

		public String getStrStoreId() {
			return strStoreId;
		}

		public void setStrStoreId(String strStoreId) {
			this.strStoreId = strStoreId;
		}

		public String getStrStoreCmb() {
			return strStoreCmb;
		}

		public void setStrStoreCmb(String strStoreCmb) {
			this.strStoreCmb = strStoreCmb;
		}

		public String getStrCurrentDate() {
			return strCurrentDate;
		}

		public void setStrCurrentDate(String strCurrentDate) {
			this.strCurrentDate = strCurrentDate;
		}

		public String getStrComplaintId() {
			return strComplaintId;
		}

		public void setStrComplaintId(String strComplaintId) {
			this.strComplaintId = strComplaintId;
		}

		/*public String getStrItemDateOrDetailed() {
			return strItemDateOrDetailed;
		}

		public void setStrItemDateOrDetailed(String strItemDateOrDetailed) {
			this.strItemDateOrDetailed = strItemDateOrDetailed;
		}*/
		
		

}
