
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail Ward Wise VO
	 * 
	 * date: 02/01/2012
	 */
	import javax.sql.rowset.WebRowSet;

	public class NewAdmissionDetailWardWiseRptVO {
		
		private static final long serialVersionUID = 02L;

		private String strMsgString = "";
		private String strMsgType = "";	
		private String strClerkName="";
		private String strReportFormat = "0";
		private String strHospitalCode = "";
		private WebRowSet strWardWs = null;
		private String strSeatId = "";
		
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
		
		public String getStrReportFormat() {
			return strReportFormat;
		}
		public void setStrReportFormat(String strReportFormat) {
			this.strReportFormat = strReportFormat;
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
		public WebRowSet getStrWardWs() {
			return strWardWs;
		}
		public void setStrWardWs(WebRowSet strWardWs) {
			this.strWardWs = strWardWs;
		}
		
		

	}




