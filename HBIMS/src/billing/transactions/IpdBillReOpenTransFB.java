
	package billing.transactions;
	/*
	 * Ipd Bill Re-Open Transaction FB
	 * 
	 * author : Debashis Sardar
	 * 
	 * date: 10-Dec-2011
	 * 
	 */
	import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

	public class IpdBillReOpenTransFB extends ActionForm {

		java.util.LinkedHashMap<String, String> lhm = new java.util.LinkedHashMap<String, String>();
		private static final long serialVersionUID = 02L;
		
		private String strWarningMsg = "";
		private String strNormalMsg = "";
		private String strRcptNo="";
		private String RcptNo="";
		private String strBillDate="";
		private String strApprovedBy="";
		private String strExpenseAmt="";
		private String strReceiveAmt="";
		private WebRowSet strPatientDetailsWs = null;
		private String strMsgString = null;
		private WebRowSet strPatAdmDetailsWs = null;
		private String strSeatId = "";
		
		private String strGlbErrMsgTLD = "";
		private String strMsgType = "";
		private String strChk = "";
		private String tmpMsg = "";
		private String strCrNo = "";
		private String strCrNo1 = "";
		private String strBId = "";
		
		private String strRecFrClnt = "";
		
		
		private String strRecFrPat = "";
		
		private String strDetls = "";
		
		
		private String strAccPreDis = "";
		private String strApproval_id = "";
		private String strBill = "";
		private String strHospitalCode = "";
		private String strHospServ = "";
		private String StrErrMsg = "";
		private String strMsg = "";
		private String strPatientDtls = "";
		
		
		private String strReqNo ="";
		private String strBServiceId="";
		private String strBillNo = "";
		private String strPatAccNo ="";
		private String strApprovalId ="";
		
		private String strUnitId[] = null;
	    private String strCtDate = "";
	    private String strBillCatCode="";
	    private String strBillCatGrp="";
	        
	    
	    private String[] chk = null;
	    private String[] chkHidd = null;
	    
		public String getStrCtDate() 
		{
			HisUtil util = new HisUtil("Drug Safty Alert Master", "SparePartsMstFB");
			try {
				strCtDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("MMS Module", "Spare Parts Master",
						"SparePartsMstFB.getStrCtDate()-->" + e.getMessage());
			}
			return strCtDate;

		}
		
		
		

		public String getStrErrMsg() {
			return StrErrMsg;
		}

		public void setStrErrMsg(String strErrMsg) {
			StrErrMsg = strErrMsg;
		}

		public String getStrBill() {
			return strBill;
		}

		public void setStrBill(String strBill) {
			this.strBill = strBill;
		}

		

		public String getStrCrNo() {
			return strCrNo;
		}

		public void setStrCrNo(String strCrNo) {
			this.strCrNo = strCrNo;
		}

		public String getStrChk() {
			return strChk;
		}

		public void setStrChk(String strChk) {
			this.strChk = strChk;
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

		public WebRowSet getStrPatientDetailsWs() {
			return strPatientDetailsWs;
		}

		public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
			this.strPatientDetailsWs = strPatientDetailsWs;
		}

		

		public String getStrCrNo1() {
			return strCrNo1;
		}

		public void setStrCrNo1(String strCrNo1) {
			this.strCrNo1 = strCrNo1;
		}

		public String getStrBId() {
			return strBId;
		}

		public void setStrBId(String strBId) {
			this.strBId = strBId;
		}

		public String getStrRecFrClnt() {
			return strRecFrClnt;
		}

		public void setStrRecFrClnt(String strRecFrClnt) {
			this.strRecFrClnt = strRecFrClnt;
		}

		

		
		public String getStrRecFrPat() {
			return strRecFrPat;
		}

		public void setStrRecFrPat(String strRecFrPat) {
			this.strRecFrPat = strRecFrPat;
		}

		
		public String getStrDetls() {
			return strDetls;
		}

		public void setStrDetls(String strDetls) {
			this.strDetls = strDetls;
		}

		

		public String getStrAccPreDis() {
			return strAccPreDis;
		}

		public void setStrAccPreDis(String strAccPreDis) {
			this.strAccPreDis = strAccPreDis;
		}

		public String getStrApproval_id() {
			return strApproval_id;
		}

		public void setStrApproval_id(String strApproval_id) {
			this.strApproval_id = strApproval_id;
		}

		

		public String getTmpMsg() {
			return tmpMsg;
		}

		public void setTmpMsg(String tmpMsg) {
			this.tmpMsg = tmpMsg;
		}

		public String getStrGlbErrMsgTLD() {
			return strGlbErrMsgTLD;
		}

		public void setStrGlbErrMsgTLD(String strGlbErrMsgTLD) {
			this.strGlbErrMsgTLD = strGlbErrMsgTLD;
		}


		public String getStrHospitalCode() {
			return strHospitalCode;
		}

		public void setStrHospitalCode(String strHospitalCode) {
			this.strHospitalCode = strHospitalCode;
		}

		public String getStrHospServ() {
			return strHospServ;
		}

		public void setStrHospServ(String strHospServ) {
			this.strHospServ = strHospServ;
		}

		public String getStrMsg() {
			return strMsg;
		}

		public void setStrMsg(String strMsg) {
			this.strMsg = strMsg;
		}

		public String getStrPatientDtls() {
			return strPatientDtls;
		}

		public void setStrPatientDtls(String strPatientDtls) {
			this.strPatientDtls = strPatientDtls;
		}

		

		public String getStrBillNo() {
			return strBillNo;
		}

		public void setStrBillNo(String strBillNo) {
			this.strBillNo = strBillNo;
		}

		

		public String[] getStrUnitId() {
			return strUnitId;
		}

		public void setStrUnitId(String[] strUnitId) {
			this.strUnitId = strUnitId;
		}

		

		public String getStrReqNo() {
			return strReqNo;
		}


		public void setStrReqNo(String strReqNo) {
			this.strReqNo = strReqNo;
		}


		public String getStrPatAccNo() {
			return strPatAccNo;
		}


		public void setStrPatAccNo(String strPatAccNo) {
			this.strPatAccNo = strPatAccNo;
		}


		public String getStrApprovalId() {
			return strApprovalId;
		}


		public void setStrApprovalId(String strApprovalId) {
			this.strApprovalId = strApprovalId;
		}


		public String getStrBServiceId() {
			return strBServiceId;
		}


		public void setStrBServiceId(String strBServiceId) {
			this.strBServiceId = strBServiceId;
		}


		


		public void setStrCtDate(String strCtDate) {
			this.strCtDate = strCtDate;
		}


		/**
		 * @return the chk
		 */
		public String[] getChk() {
			return chk;
		}


		/**
		 * @param chk the chk to set
		 */
		public void setChk(String[] chk) {
			this.chk = chk;
		}


		/**
		 * @return the chkHidd
		 */
		public String[] getChkHidd() {
			return chkHidd;
		}


		/**
		 * @param chkHidd the chkHidd to set
		 */
		public void setChkHidd(String[] chkHidd) {
			this.chkHidd = chkHidd;
		}


		/**
		 * @return the strWarningMsg
		 */
		public String getStrWarningMsg() {
			return strWarningMsg;
		}


		/**
		 * @param strWarningMsg the strWarningMsg to set
		 */
		public void setStrWarningMsg(String strWarningMsg) {
			this.strWarningMsg = strWarningMsg;
		}


		/**
		 * @return the strNormalMsg
		 */
		public String getStrNormalMsg() {
			return strNormalMsg;
		}


		/**
		 * @param strNormalMsg the strNormalMsg to set
		 */
		public void setStrNormalMsg(String strNormalMsg) {
			this.strNormalMsg = strNormalMsg;
		}


		


		public String getStrSeatId() {
			return strSeatId;
		}


		public void setStrSeatId(String strSeatId) {
			this.strSeatId = strSeatId;
		}




		public String getStrRcptNo() {
			return strRcptNo;
		}




		public void setStrRcptNo(String strRcptNo) {
			this.strRcptNo = strRcptNo;
		}




		public WebRowSet getStrPatAdmDetailsWs() {
			return strPatAdmDetailsWs;
		}




		public void setStrPatAdmDetailsWs(WebRowSet strPatAdmDetailsWs) {
			this.strPatAdmDetailsWs = strPatAdmDetailsWs;
		}




		public String getRcptNo() {
			return RcptNo;
		}




		public void setRcptNo(String rcptNo) {
			RcptNo = rcptNo;
		}




		public String getStrBillDate() {
			return strBillDate;
		}




		public void setStrBillDate(String strBillDate) {
			this.strBillDate = strBillDate;
		}




		public String getStrApprovedBy() {
			return strApprovedBy;
		}




		public void setStrApprovedBy(String strApprovedBy) {
			this.strApprovedBy = strApprovedBy;
		}




		public String getStrExpenseAmt() {
			return strExpenseAmt;
		}




		public void setStrExpenseAmt(String strExpenseAmt) {
			this.strExpenseAmt = strExpenseAmt;
		}




		public String getStrReceiveAmt() {
			return strReceiveAmt;
		}




		public void setStrReceiveAmt(String strReceiveAmt) {
			this.strReceiveAmt = strReceiveAmt;
		}
		public java.util.LinkedHashMap<String, String> getLhm() 
		{
			//lhm.put(" Final Bill Processing ", "ADDSERVICE");
			//lhm.put(" Bed Transfers ", "BEDTRANSFER");
			//lhm.put(" View Consolidated/Detail Bill", "VIEWBILL");
			//lhm.put(" Finalize Bill ", "BILLAPPROVAL");
			//lhm.put(" No Dues Printing ", "NODUESPRINT");
			lhm.put(" Final Bill Data Editing ", "IPDBILLREOPEN");
			//lhm.put(" Update Account ", "UPDATEACCOUNTSTATUS");
			return lhm;
		}




		public String getStrBillCatCode() {
			return strBillCatCode;
		}




		public void setStrBillCatCode(String strBillCatCode) {
			this.strBillCatCode = strBillCatCode;
		}




		public String getStrBillCatGrp() {
			return strBillCatGrp;
		}




		public void setStrBillCatGrp(String strBillCatGrp) {
			this.strBillCatGrp = strBillCatGrp;
		}

		
	}


