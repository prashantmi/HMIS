
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail Ward Wise FB
	 * 
	 * date: 02/01/2012
	 */
	import org.apache.struts.action.ActionForm;

	public class NewAdmissionDetailWardWiseRptFB extends ActionForm{

		private static final long serialVersionUID = 02L;

		
		private String strIsFooter = "0";
		private String strReportId = "0";
		private String strErrMsg = "";
		private String  strWardSelected="";
		private String strHospitalCode = "";	
		private String strWardName="";
		private String strWardCode="";
		private String strReportFormat = "0";
		private String strUserRemarks = "";
		private String strWardList = "";
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
		public String getStrWardList() {
			return strWardList;
		}
		public void setStrWardList(String strWardList) {
			this.strWardList = strWardList;
		}
		public String getStrWardSelected() {
			return strWardSelected;
		}
		public void setStrWardSelected(String strWardSelected) {
			this.strWardSelected = strWardSelected;
		}
		public String getStrWardName() {
			return strWardName;
		}
		public void setStrWardName(String strWardName) {
			this.strWardName = strWardName;
		}
		public String getStrWardCode() {
			return strWardCode;
		}
		public void setStrWardCode(String strWardCode) {
			this.strWardCode = strWardCode;
		}
			
	}




