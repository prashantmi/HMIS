

package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import ipd.DAOIpd;
import ipd.masters.dao.WardCriteriaMstDAO;

import javax.sql.rowset.WebRowSet;

public class WardCriteriaMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;
	
	
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	private String strReportId = "0";
	@SuppressWarnings("unused")
	private String strLCategory = "";
	private String strWardModi ="";
	private String[] strRCategory = null;
	private String[] strGender = null;
	private String strtempGender = "0";
	private String[] strFromAge = null;
	private String[] strToAge = null;
	@SuppressWarnings("unused")
	private String strLDisease ="";
	private String strRemark = "";
	private String strSeatId = "";
	private String strLastModifySeatId = "";
	private String strEffectiveFrom = null;
	private String strEffectiveTo =null;
	private String strCtDate = null;
	private String strIsValid = "";
	private String[] strFunit = null;
	private String strWardCmb = "";
	private String strWardName="";
	private String strLCategoryCmb = "";
	private String strRCategoryCmbModi = "";
	@SuppressWarnings("unused")
	private String StrRCategoryModi ="";
	@SuppressWarnings("unused")
	private String strLDiseaseModi ="";
	private String strLDiseaseCmb ="";
	private String strRDiseaseCmbModi ="";
	private String[] strRDisease =null;
	@SuppressWarnings("unused")
	private String strWardCode = "";
	private String strHospitalCode = "";
	@SuppressWarnings("unused")
	private String strLCategoryModi ="";
	private String strErrorMsg = "";
	private String strmsg = "";
	private String strwarnings = "";
	private String strChkAge = "0";
	private  String strChkTreatment ="0";
	private String strChkDisease ="0";
	private String hmode="";
	private String strRoomNo="";
	private String strRoomNoValues="";
	private String strRoomName="";
	
	private int ntotalRows ;
	
	
	
	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}
	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	/**
	 * @return the strIsFooter
	 */
	public String getStrIsFooter() {
		return strIsFooter;
	}
	/**
	 * @param strIsFooter the strIsFooter to set
	 */
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	/**
	 * @return the strUserRemarks
	 */
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	/**
	 * @param strUserRemarks the strUserRemarks to set
	 */
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}
	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	/**
	 * @return the strRoomName
	 */
	public String getStrRoomName() {
		return strRoomName;
	}
	/**
	 * @param strRoomName the strRoomName to set
	 */
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	/**
	 * @return the strRoomNoValues
	 */
	public String getStrRoomNoValues() {
		return strRoomNoValues;
	}
	/**
	 * @param strRoomNoValues the strRoomNoValues to set
	 */
	public void setStrRoomNoValues(String strRoomNoValues) {
		this.strRoomNoValues = strRoomNoValues;
	}
	/**
	 * @return the strRoomNo
	 */
	public String getStrRoomNo() {
		return strRoomNo;
	}
	/**
	 * @param strRoomNo the strRoomNo to set
	 */
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}
	public String getStrChkAge() {
		return strChkAge;
	}
	public void setStrChkAge(String strChkAge) {
		this.strChkAge = strChkAge;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public String getStrmsg() {
		return strmsg;
	}
	public void setStrmsg(String strmsg) {
		this.strmsg = strmsg;
	}
	public String getStrwarnings() {
		return strwarnings;
	}
	public void setStrwarnings(String strwarnings) {
		this.strwarnings = strwarnings;
	}
	public String[] getStrGender() {
		return strGender;
	}
	public void setStrGender(String[] strGender) {
		this.strGender = strGender;
	}
	public String[] getStrFromAge() {
		return strFromAge;
	}
	public void setStrFromAge(String[] strFromAge) {
		this.strFromAge = strFromAge;
	}
	public String[] getStrToAge() {
		return strToAge;
	}
	public void setStrToAge(String[] strToAge) {
		this.strToAge = strToAge;
	}
	public String getStrRemark() {
		return strRemark;
	}
	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrLastModifySeatId() {
		return strLastModifySeatId;
	}
	public void setStrLastModifySeatId(String strLastModifySeatId) {
		this.strLastModifySeatId = strLastModifySeatId;
	}
	
	/**
	 * retrieves and returns the Application Server Current Date 
	 * in a Format of Effective From.
	 * 
	 * @return - Current Date in String Format.
	 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("WardCriteria  Master", "VOWardCriteria");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "Ward Master",
						"VOWardCriteria.getStrEffectiveFrom()-->" + e.getMessage());
			}
		}
		return strEffectiveFrom;
	}
	
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() { 
		HisUtil util = new HisUtil("WardCriteria  Master", "VOWardCriteria");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Ward Master",
					"VOWardCriteria.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String[] getStrFunit() {
		return strFunit;
	}
	public void setStrFunit(String[] strFunit) {
		this.strFunit = strFunit;
	}
	public String getStrWardCmb() {
		return strWardCmb;
	}
	public void setStrWardCmb(String strWardCmb) {
		this.strWardCmb = strWardCmb;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * Through this method using ward code user gets ward name.
	 * 
	 * @return String Value
	 * @throws Exception
	 */
	public String getStrWardCode() throws Exception {
		
		String cmbstr = "";
		String errMsg = "";
		HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.wardcriteria.2").replace("?", ""+this.strHospitalCode+"");
		
		try {
			cmbstr = hisutil.getOptionValue(qry, "0", "0^Select Value");
		} catch (Exception e) {
			errMsg = "VOWardCriteria.getStrWardCode() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
		}
		return cmbstr;
	}

	/**
	 * By this Method user get gender combo.
	 * 
	 * @return either Male or female in String Format
	 */
	public String getComboGender() {

		String cmbstr = "";

		WebRowSet ws = null;
		DAOIpd daoipd = new DAOIpd("ipd", "VOWardMst");
		try {
			ws = daoipd.getGenderDtl(true);
			HisUtil hisutil = new HisUtil("ipd", "VOWardMst");

			cmbstr = hisutil.getOptionValue(ws, this.getStrtempGender(),
					"0^Select Value", false);

		} catch (Exception e) {
			new HisException("IPD", "VOWardMst.getComboGender", e.getMessage());
		}

		return cmbstr;

	}
	public String getStrtempGender() {
		return strtempGender;
	}
	public void setStrtempGender(String strtempGender) {
		this.strtempGender = strtempGender;
	}
	/**
	 * User get Left Side Treatment Category.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getStrLCategory() throws Exception {
		
		String cmbstr = "";
		String errMsg = "";
		HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.wardcriteria.3").replace("?", ""+this.strHospitalCode+"");
		try {
			cmbstr = hisutil.getOptionValue(qry, "0", "");
		} catch (Exception e) {
			errMsg = "VOWardCriteria.getStrLCategory() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
	}
		return cmbstr;
}
	/**
	 * User get Modified Left Side Treatment Category.
	 * 
	 * @return
	 * @throws Exception
	 */
public String getStrLCategoryModi() throws Exception {
	WebRowSet ws = null;
	   
	    String errMsg="";
	    String  cmbstr ="";
	    WardCriteriaMstVO vo = new WardCriteriaMstVO();
	    HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
	    try {
			ws =   WardCriteriaMstDAO.getLeftCatg(vo,this.getStrHospitalCode(),this.getStrWardModi());
			cmbstr = hisutil.getOptionValue(ws,"0","",false);
		} catch (Exception e) {
			errMsg = "VOWardCriteria.getStrLCategoryModi() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
	}
	return cmbstr;
}
	

/**
 * User get Modified Left Side Disease Type Criteria.
 * @return
 * @throws Exception
 */
public String getStrLDiseaseModi() throws Exception {
	WebRowSet ws = null;
	   
	    String errMsg="";
	    String  cmbstr ="";
	    WardCriteriaMstVO vo = new WardCriteriaMstVO();
	    HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
	    try {
	    	ws =   WardCriteriaMstDAO.getLDisease(vo,this.getStrHospitalCode(),this.getStrWardModi());
			cmbstr = hisutil.getOptionValue(ws,"0","",false);
		} catch (Exception e) {
			errMsg = "VOWardCriteria.getStrLDiseaseModi() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
		}
	return cmbstr;
}
	
/**
 * User get Modified Right Side Disease Type Criteria.
 * @return
 * @throws Exception
 */

public String getStrRDiseaseModi() throws Exception {
	WebRowSet ws = null;
	   
	    String errMsg="";
	    String  cmbstr ="";
	    int n=0;
	    WardCriteriaMstVO vo = new WardCriteriaMstVO();
	    HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
	    try {
			ws =   WardCriteriaMstDAO.getRDisease(vo,this.getStrHospitalCode(),this.getStrWardModi());
			while(ws .next())
			{
		    	 n = Integer.parseInt(ws.getString(1));
			}
			if(n==-1)
			{
				cmbstr = " ";
			}
			else
			{		
				ws.beforeFirst();
				cmbstr = hisutil.getOptionValue(ws,"0","",false);
			}
			
	    } catch (Exception e) {
			errMsg = "VOWardCriteria.getStrRDiseaseModi() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
		}
		return cmbstr;
	}
	
/**
 * User get Right Side Treatment Category.
 * @return
 * @throws Exception
 */
public String getStrRCategoryModi() throws Exception {
	WebRowSet ws = null;
	   
	    String errMsg="";
	    int n =0;
	    String  cmbstr ="";
	    WardCriteriaMstVO vo = new WardCriteriaMstVO();
	    HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
	    try {
	    	ws =   WardCriteriaMstDAO.getRightCatg(vo,this.getStrHospitalCode(),this.getStrWardModi());
			while(ws .next())
			{
		    	 n = Integer.parseInt(ws.getString(1));
			}
			if(n==-1)
			{
				cmbstr = " ";
			}
			else
			{				
			 ws.beforeFirst();
			 cmbstr = hisutil.getOptionValue(ws,"0","",false);				
			}
		} catch (Exception e) {
			errMsg = "VOWardCriteria.getStrRCategoryModi() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
		}
	return cmbstr;
}



public void setStrLCategory(String strLCategory) {
		this.strLCategory = strLCategory;
	}
	public String[] getStrRCategory() {
		return strRCategory;
	}
	public void setStrRCategory(String[] strRCategory) {
		this.strRCategory = strRCategory;
	}
	public String getStrLCategoryCmb() {
		return strLCategoryCmb;
	}
	public void setStrLCategoryCmb(String strLCategoryCmb) {
		this.strLCategoryCmb = strLCategoryCmb;
	}
	public String getStrChkTreatment() {
		return strChkTreatment;
	}
	public void setStrChkTreatment(String strChkTreatment) {
		this.strChkTreatment = strChkTreatment;
	}
	public String getStrChkDisease() {
		return strChkDisease;
	}
	public void setStrChkDisease(String strChkDisease) {
		this.strChkDisease = strChkDisease;
	}
	public String getStrLDiseaseCmb() {
		return strLDiseaseCmb;
	}
	public void setStrLDiseaseCmb(String strLDiseaseCmb) {
		this.strLDiseaseCmb = strLDiseaseCmb;
	}
	
		
	
	
	public void setStrLDisease(String strLDisease) {
		this.strLDisease = strLDisease;
	}
	/**
	 * User get Left Side Disease Type Criteria.
	 * @return
	 * @throws Exception
	 */
	public String getStrLDisease() throws Exception {
		
		String cmbstr = "";
		String errMsg = "";
		HisUtil hisutil = new HisUtil("ipd", "VOWardCriteria");
		String qry = ipd.qryHandler_ipd.getQuery(1, "select.wardcriteria.4").replace("?", ""+this.strHospitalCode+"");
		
		try {
			cmbstr = hisutil.getOptionValue(qry, "0", "");
		
		} catch (Exception e) {
			errMsg = "VOWardCriteria.getStrLDisease() -->"
					+ e.getMessage();
			throw new Exception(errMsg);
		} finally {
			hisutil = null;
		}
		return cmbstr;
	}
public String[] getStrRDisease() {
	return strRDisease;
}
public void setStrRDisease(String[] strRDisease) {
	this.strRDisease = strRDisease;
}

public String getStrWardName() {
	return strWardName;
}
public void setStrWardName(String strWardName) {
	this.strWardName = strWardName;
}
public void setNtotalRows(int ntotalRows) {
	this.ntotalRows = ntotalRows;
}
public int getNtotalRows() {
	return ntotalRows;
}
public String getStrEffectiveTo() {
	return strEffectiveTo;
}
public void setStrEffectiveTo(String strEffectiveTo) {
	this.strEffectiveTo = strEffectiveTo;
}
public void setStrLCategoryModi(String strLCategoryModi) {
	this.strLCategoryModi = strLCategoryModi;
}
public String getStrRCategoryCmbModi() {
	return strRCategoryCmbModi;
}
public void setStrRCategoryCmbModi(String strRCategoryCmbModi) {
	this.strRCategoryCmbModi = strRCategoryCmbModi;
}
public String getStrRDiseaseCmbModi() {
	return strRDiseaseCmbModi;
}
public void setStrRDiseaseCmbModi(String strRDiseaseCmbModi) {
	this.strRDiseaseCmbModi = strRDiseaseCmbModi;
}
public void setStrRCategoryModi(String strRCategoryModi) {
	StrRCategoryModi = strRCategoryModi;
}
public void setStrLDiseaseModi(String strLDiseaseModi) {
	this.strLDiseaseModi = strLDiseaseModi;
}
public String getStrWardModi() {
	return strWardModi;
}
public void setStrWardModi(String strWardModi) {
	this.strWardModi = strWardModi;
}


public String getHmode() {
	return hmode;
}
public void setHmode(String hmode) {
	this.hmode = hmode;
}

	


}
