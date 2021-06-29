
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail User Wise FB
	 * 
	 * date: 30/12/2011
	 */
	import org.apache.struts.action.ActionForm;

	public class NewAdmissionDetailRptFB extends ActionForm{

		private static final long serialVersionUID = 02L;

		
		private String strIsFooter = "0";
		private String strReportId = "0";
		private String strErrMsg = "";
		private String  strClerkSelected="";
		private String strHospitalCode = "";	
		private String strClerkName="";
		private String strUserId="";
		private String strReportFormat = "0";
		private String strUserRemarks = "";
		private String strClerkList = "";
		private String strEffectiveFrom = "";
		private String strEffectiveTo = "";
		private String strCase = null;
		private String strCurrentDate="";
		private String strSeatId="";
		
		public String getStrCase() {
			return strCase;
		}
		public void setStrCase(String strCase) {
			this.strCase = strCase;
		}
		
		public String getStrReportFormat() {
			return strReportFormat;
		}
		public void setStrReportFormat(String strReportFormat) {
			this.strReportFormat = strReportFormat;
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
		public String getStrReportId() {
			return strReportId;
		}
		public void setStrReportId(String strReportId) {
			this.strReportId = strReportId;
		}
		public String getStrEffectiveTo() {
			return strEffectiveTo;
		}
		public void setStrEffectiveTo(String strEffectiveTo) {
			this.strEffectiveTo = strEffectiveTo;
		}
		public String getStrEffectiveFrom() {
			return strEffectiveFrom;
		}
		public void setStrEffectiveFrom(String strEffectiveFrom) {
			this.strEffectiveFrom = strEffectiveFrom;
		}
		public String getStrErrMsg() {
			return strErrMsg;
		}
		public void setStrErrMsg(String strErrMsg) {
			this.strErrMsg = strErrMsg;
		}
		public String getStrCurrentDate() {
			return strCurrentDate;
		}
		public void setStrCurrentDate(String strCurrentDate) {
			this.strCurrentDate = strCurrentDate;
		}
		
		public String getStrHospitalCode() {
			return strHospitalCode;
		}
		public void setStrHospitalCode(String strHospitalCode) {
			this.strHospitalCode = strHospitalCode;
		}
		public String getStrSeatId() {
			return strSeatId;
		}
		public void setStrSeatId(String strSeatId) {
			this.strSeatId = strSeatId;
		}
		public String getStrClerkName() {
			return strClerkName;
		}
		public void setStrClerkName(String strClerkName) {
			this.strClerkName = strClerkName;
		}
		public String getStrClerkList() {
			return strClerkList;
		}
		public void setStrClerkList(String strClerkList) {
			this.strClerkList = strClerkList;
		}
		public String getStrClerkSelected() {
			return strClerkSelected;
		}
		public void setStrClerkSelected(String strClerkSelected) {
			this.strClerkSelected = strClerkSelected;
		}
		public String getStrUserId() {
			return strUserId;
		}
		public void setStrUserId(String strUserId) {
			this.strUserId = strUserId;
		}
			
	}


