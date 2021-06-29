
/* (PACKAGE SERVICE MASTER)  */
/* Package Service Master Form Bean
 * author: Debashis Sardar
 * Created on : 01-Sep-2011
 */
package billing.masters.controller.fb;
import hisglobal.exceptions.HisException;

import hisglobal.masterutil.GenericFormBean;

import hisglobal.utility.HisUtil;


public class PackageServiceMstFB extends GenericFormBean {

	
		private static final long serialVersionUID = 02L;
		private String strLstModifySeatId = "";
		private String strpackageId = "";
		private String strgroupName = "";
		private String strpackageName = "";
		private String strtrfDefUnit = "";
		private String strtariffName = null;
		private String strtariffId = "";
		private String strMultiTariffId[] = null;
		private String strgroupId = "";
		private String strisGrpFlag = "";
		private String strqty = null;
		private String strMultiQty[] = null;
		private String strtariffType=null;
		private String strtariffID=null;
		

		private String streffectiveFrm = "";
		private String strremarks = "";
		private String strunitId = "";
		private String strMultiUnitId[] = null;
		private String strunitName = "";
		private String strlstModifyDate = "";
		private String strlstModifySeatId = "";
		private String strentryDate = "";
		private String strseatId = "";
		private String strisValid = "";
		private String strpackModuleCombo = "";
		private String strpackgrpModuleCombo = "";
		private String strpacktariffModuleCombo = "";
		private String strpackunitModuleCombo = "";

		private String strMsgString = "";
		private String strMsgType = "";

		private String errmsg = "";
		private String normalMsg = "";
		private String strNormalMsg="";
		private String warningMsg = "";
		private String strSlNo = "";
		private String strHospitalCode = "";
		private String strCtDate = "";

		private String strChk1 = "";
		private String strUnitCmb = "";
		//private String tempStr = "";
		// //----------GETTER METHODS-----------////

		
		public String getStrCtDate() {
			HisUtil util = new HisUtil("Package Service Master", "packservMstFB");
			try {
				strCtDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Billing Module", "Package Service Master",
						"packservMstFB.getStrCtDate()-->" + e.getMessage());
			}
			return strCtDate;
		}

		public void setStrCtDate(String strCtDate) {
			this.strCtDate = strCtDate;
		}

		public String getStrLstModifySeatId() {
			return strLstModifySeatId;
		}

		public void setStrLstModifySeatId(String strLstModifySeatId) {
			this.strLstModifySeatId = strLstModifySeatId;
		}

		public String getStrpackageId() {
			return strpackageId;
		}

		public void setStrpackageId(String strpackageId) {
			this.strpackageId = strpackageId;
		}

		public String getStrgroupName() {
			return strgroupName;
		}

		public void setStrgroupName(String strgroupName) {
			this.strgroupName = strgroupName;
		}

		public String getStrpackageName() {
			return strpackageName;
		}

		public void setStrpackageName(String strpackageName) {
			this.strpackageName = strpackageName;
		}

		public String getStrtrfDefUnit() {
			return strtrfDefUnit;
		}

		public void setStrtrfDefUnit(String strtrfDefUnit) {
			this.strtrfDefUnit = strtrfDefUnit;
		}

		public String getStrtariffName() {
			return strtariffName;
		}

		public void setStrtariffName(String strtariffName) {
			this.strtariffName = strtariffName;
		}

		public String getStrtariffId() {
			return strtariffId;
		}

		public void setStrtariffId(String strtariffId) {
			this.strtariffId = strtariffId;
		}

		public String[] getStrMultiTariffId() {
			return strMultiTariffId;
		}

		public void setStrMultiTariffId(String[] strMultiTariffId) {
			this.strMultiTariffId = strMultiTariffId;
		}

		public String getStrgroupId() {
			return strgroupId;
		}

		public void setStrgroupId(String strgroupId) {
			this.strgroupId = strgroupId;
		}

		public String getStrisGrpFlag() {
			return strisGrpFlag;
		}

		public void setStrisGrpFlag(String strisGrpFlag) {
			this.strisGrpFlag = strisGrpFlag;
		}

		public String getStrqty() {
			return strqty;
		}

		public void setStrqty(String strqty) {
			this.strqty = strqty;
		}

		public String[] getStrMultiQty() {
			return strMultiQty;
		}

		public void setStrMultiQty(String[] strMultiQty) {
			this.strMultiQty = strMultiQty;
		}

		public String getStreffectiveFrm() {
			return streffectiveFrm;
		}

		public void setStreffectiveFrm(String streffectiveFrm) {
			this.streffectiveFrm = streffectiveFrm;
		}

		public String getStrremarks() {
			return strremarks;
		}

		public void setStrremarks(String strremarks) {
			this.strremarks = strremarks;
		}

		public String getStrunitId() {
			return strunitId;
		}

		public void setStrunitId(String strunitId) {
			this.strunitId = strunitId;
		}

		public String[] getStrMultiUnitId() {
			return strMultiUnitId;
		}

		public void setStrMultiUnitId(String[] strMultiUnitId) {
			this.strMultiUnitId = strMultiUnitId;
		}

		public String getStrunitName() {
			return strunitName;
		}

		public void setStrunitName(String strunitName) {
			this.strunitName = strunitName;
		}

		public String getStrlstModifyDate() {
			return strlstModifyDate;
		}

		public void setStrlstModifyDate(String strlstModifyDate) {
			this.strlstModifyDate = strlstModifyDate;
		}

		public String getStrlstModifySeatId() {
			return strlstModifySeatId;
		}

		public void setStrlstModifySeatId(String strlstModifySeatId) {
			this.strlstModifySeatId = strlstModifySeatId;
		}

		public String getStrentryDate() {
			return strentryDate;
		}

		public void setStrentryDate(String strentryDate) {
			this.strentryDate = strentryDate;
		}

		public String getStrseatId() {
			return strseatId;
		}

		public void setStrseatId(String strseatId) {
			this.strseatId = strseatId;
		}

		public String getStrisValid() {
			return strisValid;
		}

		public void setStrisValid(String strisValid) {
			this.strisValid = strisValid;
		}

		public String getStrpackModuleCombo() {
			return strpackModuleCombo;
		}

		public void setStrpackModuleCombo(String strpackModuleCombo) {
			this.strpackModuleCombo = strpackModuleCombo;
		}

		public String getStrpackgrpModuleCombo() {
			return strpackgrpModuleCombo;
		}

		public void setStrpackgrpModuleCombo(String strpackgrpModuleCombo) {
			this.strpackgrpModuleCombo = strpackgrpModuleCombo;
		}

		public String getStrpacktariffModuleCombo() {
			return strpacktariffModuleCombo;
		}

		public void setStrpacktariffModuleCombo(String strpacktariffModuleCombo) {
			this.strpacktariffModuleCombo = strpacktariffModuleCombo;
		}

		public String getStrpackunitModuleCombo() {
			return strpackunitModuleCombo;
		}

		public void setStrpackunitModuleCombo(String strpackunitModuleCombo) {
			this.strpackunitModuleCombo = strpackunitModuleCombo;
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

		public String getErrmsg() {
			return errmsg;
		}

		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}

		public String getNormalMsg() {
			return normalMsg;
		}

		public void setNormalMsg(String normalMsg) {
			this.normalMsg = normalMsg;
		}

		public String getStrNormalMsg() {
			return strNormalMsg;
		}

		public void setStrNormalMsg(String strNormalMsg) {
			this.strNormalMsg = strNormalMsg;
		}

		public String getWarningMsg() {
			return warningMsg;
		}

		public void setWarningMsg(String warningMsg) {
			this.warningMsg = warningMsg;
		}

		public String getStrHospitalCode() {
			return strHospitalCode;
		}

		public void setStrHospitalCode(String strHospitalCode) {
			this.strHospitalCode = strHospitalCode;
		}



		public String getStrUnitCmb() {
			return strUnitCmb;
		}

		public void setStrUnitCmb(String strUnitCmb) {
			this.strUnitCmb = strUnitCmb;
		}

		public String getStrSlNo() {
			return strSlNo;
		}

		public void setStrSlNo(String strSlNo) {
			this.strSlNo = strSlNo;
		}

		public String getStrChk1() {
			return strChk1;
		}

		public void setStrChk1(String strChk1) {
			this.strChk1 = strChk1;
		}

		public String getStrtariffType() {
			return strtariffType;
		}

		public void setStrtariffType(String strtariffType) {
			this.strtariffType = strtariffType;
		}

		public String getStrtariffID() {
			return strtariffID;
		}

		public void setStrtariffID(String strtariffID) {
			this.strtariffID = strtariffID;
		}

	}

	

