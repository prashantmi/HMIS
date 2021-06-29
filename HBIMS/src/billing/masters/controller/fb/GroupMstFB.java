
package billing.masters.controller.fb;
/* Group Master Form Bean
 * author: Debashis Sardar
 * Created on : 09-Sep-2011
 */
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

	public class GroupMstFB extends GenericFormBean {

		private static final long serialVersionUID = 02L;
		private String strgroupName = "";
		private String strEffectiveFrom = "";
		private String strisPackage = "0";
		private String chk1 = "";
		private String strgroupId = "";
		private String strremark = "";
		private String strisValid = "";
		private String strisVisible = "0";
		private String strErrorMsg = "";
		private String msg = "";
		private String warnings = "";
		private String strseatId = "";
		private String strlastSeatId = "";
		private String strhospitalcode = "";
		private String strctdate = "";
		private String strentrydate = "";
		private String strCrntDate = null;
		private String strMsgString = "";
		private String strMsgType = "";
        private String strerrmsg = "";
		private String strconsumeableCharge = "";
		private String strGroupCombo="";
		private String strGlobalGroupName="";
		private String strHospitalServiceCheckbox="";
		private int[] hospServiceValue=null;
		private String strGroupCode="";
 		
		public static long getSerialVersionUID() {
			return serialVersionUID;
		}

		
		public String getStrCrntDate() throws Exception {

			if (this.strCrntDate == null) {
				HisUtil util = new HisUtil("billing", "GroupMstFB");
				try {
					strCrntDate = util.getASDate("dd-MMM-yyyy");
					util = null;
				} catch (Exception e) {
					throw new Exception("Billing.GroupMstFB.getStrCrntDate()-->"
							+ e.getMessage());
				}
			}

			return strCrntDate;
		}

		
	
	public String getStrgroupName() {
			return strgroupName;
		}



		public void setStrgroupName(String strgroupName) {
			this.strgroupName = strgroupName;
		}



		public String getStrEffectiveFrom() {
			return strEffectiveFrom;
		}



		public void setStrEffectiveFrom(String strEffectiveFrom) {
			this.strEffectiveFrom = strEffectiveFrom;
		}



		public String getStrisPackage() {
			return strisPackage;
		}



		public void setStrisPackage(String strisPackage) {
			this.strisPackage = strisPackage;
		}



		public String getChk1() {
			return chk1;
		}



		public void setChk1(String chk1) {
			this.chk1 = chk1;
		}



		public String getStrgroupId() {
			return strgroupId;
		}



		public void setStrgroupId(String strgroupId) {
			this.strgroupId = strgroupId;
		}



		public String getStrremark() {
			return strremark;
		}



		public void setStrremark(String strremark) {
			this.strremark = strremark;
		}



		public String getStrisValid() {
			return strisValid;
		}



		public void setStrisValid(String strisValid) {
			this.strisValid = strisValid;
		}



		public String getStrisVisible() {
			return strisVisible;
		}



		public void setStrisVisible(String strisVisible) {
			this.strisVisible = strisVisible;
		}



		public String getStrErrorMsg() {
			return strErrorMsg;
		}



		public void setStrErrorMsg(String strErrorMsg) {
			this.strErrorMsg = strErrorMsg;
		}



		public String getMsg() {
			return msg;
		}



		public void setMsg(String msg) {
			this.msg = msg;
		}



		public String getWarnings() {
			return warnings;
		}



		public void setWarnings(String warnings) {
			this.warnings = warnings;
		}



		public String getStrseatId() {
			return strseatId;
		}



		public void setStrseatId(String strseatId) {
			this.strseatId = strseatId;
		}



		public String getStrlastSeatId() {
			return strlastSeatId;
		}



		public void setStrlastSeatId(String strlastSeatId) {
			this.strlastSeatId = strlastSeatId;
		}



		public String getStrhospitalcode() {
			return strhospitalcode;
		}



		public void setStrhospitalcode(String strhospitalcode) {
			this.strhospitalcode = strhospitalcode;
		}



		public String getStrctdate() {
			
			HisUtil util = new HisUtil("Group Master", "GroupMstFB");
			try {
				strctdate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Billing Module", "Group Master",
						"GroupMstFB.getCtDate()-->" + e.getMessage());
			}
			
			return strctdate;
		}



		public void setStrctdate(String strctdate) {
			this.strctdate = strctdate;
		}



		public String getStrentrydate() {
			return strentrydate;
		}



		public void setStrentrydate(String strentrydate) {
			this.strentrydate = strentrydate;
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



		public String getStrerrmsg() {
			return strerrmsg;
		}



		public void setStrerrmsg(String strerrmsg) {
			this.strerrmsg = strerrmsg;
		}



		public void setStrCrntDate(String strCrntDate) {
			this.strCrntDate = strCrntDate;
		}



	

	public String getStrGroupCombo() {
		return strGroupCombo;
	}


	public void setStrGroupCombo(String strGroupCombo) {
		this.strGroupCombo = strGroupCombo;
	}


	public String getStrGlobalGroupName() {
		return strGlobalGroupName;
	}


	public void setStrGlobalGroupName(String strGlobalGroupName) {
		this.strGlobalGroupName = strGlobalGroupName;
	}


	public String getStrconsumeableCharge() {
		return strconsumeableCharge;
	}


	public void setStrconsumeableCharge(String strconsumeableCharge) {
		this.strconsumeableCharge = strconsumeableCharge;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.setStrconsumeableCharge("");
	}

	public int[] getHospServiceValue() {
		return hospServiceValue;
	}


	public void setHospServiceValue(int[] hospServiceValue) {
		this.hospServiceValue = hospServiceValue;
	}


	public String getStrHospitalServiceCheckbox() {
		return strHospitalServiceCheckbox;
	}


	public void setStrHospitalServiceCheckbox(String strHospitalServiceCheckbox) {
		this.strHospitalServiceCheckbox = strHospitalServiceCheckbox;
	}


	public String getStrGroupCode() {
		return strGroupCode;
	}


	public void setStrGroupCode(String strGroupCode) {
		this.strGroupCode = strGroupCode;
	}
	

	
}
