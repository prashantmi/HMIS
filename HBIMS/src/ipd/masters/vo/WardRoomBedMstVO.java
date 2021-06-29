package ipd.masters.vo;

import java.sql.SQLException;

import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class WardRoomBedMstVO extends GenericFormBean {
	private static final long serialVersionUID = 02L;

	
	private String strErr="";
	private String strWarning="";
	private String strMsg="";
	private String strBedDetail="";
	@SuppressWarnings("unused")
	private String strWardNameCombo="";
	private String strBedTypeCombo="";
	private String strHospitalCode="";
	private String strRoomName="";
	private String strErrorMsg="";
	private String strBuildingBlock="";
	private String strBedLimit="";
	private String strBedName="";
	@SuppressWarnings("unused")
	private String strBedType="";
	@SuppressWarnings("unused")
	private String strBedStatus="";
	private String strSeatId="";
	private String strWardCode="";
	private String strWardType="";
	private String strRoomId="";
	private String strEffectiveFrom=null;
	private String strBedTypeName="";
	private String strBedInWard="";
	private  String strBedStatusName="";
	private String strRemarks="";
	private String strDate="";
	private String strValid="";
	private String strBedName2="";
	private String strWardName="";
	@SuppressWarnings("unused")
	private String strBedTypeModi="";
	private String strBedTypeNameModi="";
	private String strBedStatusNameModi="";
	@SuppressWarnings("unused")
	private String strBedStatusModi="";
	private String strChk="";
	private String[] bedName=null;
	private String[] bedType=null;
	private String[] bedStatus=null;
	private String strCtDate="";
	private String strPropertyId="";
	private String strPropertyName="";
	private WebRowSet propertyDetails = null;
	private String strPropertyDetailsCombo = "";
	private String strCheckedProperty[] = null;
	private String strBedCode="";
	private WebRowSet strPropertyIdWS = null;
	private String StrRoomCombo="";
	private String strmodProperties="";
	
	private String strModifyChkProperties="";
	private String strCheckedHidden[]=null;
	private String[] strIsSharable=null;
	
	
	public String[] getStrIsSharable() {
		return strIsSharable;
	}
	public void setStrIsSharable(String[] strIsSharable) {
		this.strIsSharable = strIsSharable;
	}
	public WebRowSet getStrPropertyIdWS() {
		return strPropertyIdWS;
	}
	public void setStrPropertyIdWS(WebRowSet strPropertyIdWS) {
		this.strPropertyIdWS = strPropertyIdWS;
	}
	public String getStrBedCode() {
		return strBedCode;
	}
	public void setStrBedCode(String strBedCode) {
		this.strBedCode = strBedCode;
	}
	public String[] getStrCheckedProperty() {
		return strCheckedProperty;
	}
	public void setStrCheckedProperty(String[] strCheckedProperty) {
		this.strCheckedProperty = strCheckedProperty;
	}
	public WebRowSet getPropertyDetails() {
		return propertyDetails;
	}
	public void setPropertyDetails(WebRowSet propertyDetails) {
		this.propertyDetails = propertyDetails;
	}
	public String getStrPropertyDetailsCombo() {
		return strPropertyDetailsCombo;
	}
	public void setStrPropertyDetailsCombo(String strPropertyDetailsCombo) {
		this.strPropertyDetailsCombo = strPropertyDetailsCombo;
	}
	public String getStrPropertyId() {
		return strPropertyId;
	}
	public void setStrPropertyId(String strPropertyId) {
		this.strPropertyId = strPropertyId;
	}
	public String getStrPropertyName() {
		return strPropertyName;
	}
	public void setStrPropertyName(String strPropertyName) {
		this.strPropertyName = strPropertyName;
	}
	public String getStrBedDetail() {
		
		return strBedDetail;
	}
	public void setStrBedDetail(String strBedDetail) {
		this.strBedDetail = strBedDetail;
	}
	/**
	 * This function return ward combo value
	 * @return
	 */
	public String getStrWardNameCombo() {
		String cmbstr2 = "";
		// String errMsg ="";
		String strQuery4 ;
		WebRowSet web4 = null;
		String hcode=this.getStrHospitalCode();
		//System.out.println("hcode--->"+this.getStrHospitalCode());
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.14");
		//System.out.println("strQ---->"+strQuery4);
		HisDAO dao = new HisDAO("ipd", "DAOWardRoomBedMst");
		int index;
		WebRowSet wb1;
		try {// O.V true
			
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, hcode);
			wb1=dao.executeQry(index);
			//System.out.println("successfully executed");
			HisUtil hisutil = new HisUtil("ipd", "WardRoomBedMstVO");
			cmbstr2=hisutil.getOptionValue(wb1, "0", "0^Select Value", false);
			//System.out.println("cmbstr2"+cmbstr2);
					
		} 
		
		catch (Exception e) {
			new HisException("Ward Room Bed Master",

					"WardRoomBedMstVO.getStrWardNameCombo()", e.getMessage());

		} finally {
			if (web4 != null) {
				//web.close();
				//web = null;
			}
			
		}
		return cmbstr2;
	}
	public void setStrWardNameCombo(String strWardNameCombo) {
		this.strWardNameCombo = strWardNameCombo;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrRoomName() {
		return strRoomName;
	}
	public void setStrRoomName(String strRoomName) {
		this.strRoomName = strRoomName;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public String getStrBuildingBlock() {
		return strBuildingBlock;
	}
	public void setStrBuildingBlock(String strBuildingBlock) {
		this.strBuildingBlock = strBuildingBlock;
	}
	public String getStrBedLimit() {
		return strBedLimit;
	}
	public void setStrBedLimit(String strBedLimit) {
		this.strBedLimit = strBedLimit;
	}
	/**
	 * This function return the value for bed type combo
	 * @return
	 */
	public String getStrBedType() {
		String cmbstr2 = "";
		// String errMsg ="";
		String strQuery4 ;
		WebRowSet web4 = null;
		String hcode=this.getStrHospitalCode();
		//System.out.println("hcode--->"+this.getStrHospitalCode());
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.7");
		//System.out.println("strQ---->"+strQuery4);
		HisDAO dao = new HisDAO("ipd", "DAOWardRoomBedMst");
		int index;
		WebRowSet wb1;
		try {
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, IpdConfigUtil.SUPER_HOSPITAL_CODE);
			wb1=dao.executeQry(index);
			HisUtil hisutil = new HisUtil("ipd", "WardRoomBedMstVO");
			cmbstr2=hisutil.getOptionValue(wb1, "1", "", false);
		} 
		
		catch (Exception e) {
			new HisException("Ward Room Bed Master",

					"WardRoomBedMstVO.getStrBedType()", e.getMessage());

		} finally {
			if (web4 != null) {
				
			}
			
		}
		return cmbstr2;
	}
	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
	}
	/**
	 * This function return value for bedstatus combo
	 * @return
	 * @throws SQLException 
	 */
	public String getStrBedStatus() throws SQLException 
	{
		String cmbstr2 = "";
		String strQuery ;
		String hcode=IpdConfigUtil.SUPER_HOSPITAL_CODE;
		strQuery = ipd.qryHandler_ipd.getQuery(1, "select.wardroombedstatus.1");
		HisDAO dao = new HisDAO("ADT", "WardRoomBedMstVO");
		int index;
		WebRowSet wb1=null;
		try 
		{
			index=dao.setQuery(strQuery);
			dao.setQryValue(index, 1, hcode);
			wb1=dao.executeQry(index);
			HisUtil hisutil = new HisUtil("ADT", "WardRoomBedMstVO");
			cmbstr2=hisutil.getOptionValue(wb1, IpdTransConfig.getBedStatusVacantCode(), "", false);						
		}
		catch (Exception e) 
		{
			new HisException("ADT","WardRoomBedMstVO.getStrBedStatus()", e.getMessage());
		} 
		finally 
		{
			if (wb1 != null) 
			{
				wb1.close();
				wb1 = null;
			}			
		}
		return cmbstr2;
	}
	
	public void setStrBedStatus(String strBedStatus) {
		this.strBedStatus = strBedStatus;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrWardType() {
		return strWardType;
	}
	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}
	public String getStrRoomId() {
		return strRoomId;
	}
	public void setStrRoomId(String strRoomId) {
		this.strRoomId = strRoomId;
	}
	public String[] getBedName() {
		return bedName;
	}
	public void setBedName(String[] bedName) {
		this.bedName = bedName;
	}
	public String[] getBedType() {
		return bedType;
	}
	public void setBedType(String[] bedType) {
		this.bedType = bedType;
	}
	public String[] getBedStatus() {
		return bedStatus;
	}
	public void setBedStatus(String[] bedStatus) {
		this.bedStatus = bedStatus;
	}
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Ward Room Bed Master",
					"WardRoomBedMstVO");
			try {
				strEffectiveFrom= util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ward Room Bed Master",

						"WardRoomBedMstVO.getStrEffectiveFrom()", e.getMessage());

			}
		}
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrBedInWard() {
		return strBedInWard;
	}
	public void setStrBedInWard(String strBedInWard) {
		this.strBedInWard = strBedInWard;
	}
	public String getStrBedTypeCombo() {
		return strBedTypeCombo;
	}
	public void setStrBedTypeCombo(String strBedTypeCombo) {
		this.strBedTypeCombo = strBedTypeCombo;
	}
	public String getStrWardName() {
		return strWardName;
	}
	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}
	public String getStrBedTypeName() {
		return strBedTypeName;
	}
	public void setStrBedTypeName(String strBedTypeName) {
		this.strBedTypeName = strBedTypeName;
	}
	public String getStrBedStatusName() {
		return strBedStatusName;
	}
	public void setStrBedStatusName(String strBedStatusName) {
		this.strBedStatusName = strBedStatusName;
	}
	public String getStrBedName() {
		return strBedName;
	}
	public void setStrBedName(String strBedName) {
		this.strBedName = strBedName;
	}
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getStrValid() {
		return strValid;
	}
	public void setStrValid(String strValid) {
		this.strValid = strValid;
	}
	public String getStrBedName2() {
		return strBedName2;
	}
	public void setStrBedName2(String strBedName2) {
		this.strBedName2 = strBedName2;
	}
	public String getStrChk() {
		return strChk;
	}
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	public String getStrBedTypeModi() {
		String cmbstr2 = "";
		// String errMsg ="";
		String strQuery4 ;
		WebRowSet web4 = null;
		String hcode=this.getStrHospitalCode();
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombed.7");
		HisDAO dao = new HisDAO("ipd", "DAOWardRoomBedMst");
		int index;
		WebRowSet wb1;
		try {// O.V true
			
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, IpdConfigUtil.SUPER_HOSPITAL_CODE);
			wb1=dao.executeQry(index);
			HisUtil hisutil = new HisUtil("ipd", "WardRoomBedMstVO");
			cmbstr2=hisutil.getOptionValue(wb1, this.getStrBedTypeNameModi(), "0^Select value", false);
			//System.out.println("cmbstr2"+cmbstr2);
					
		} 
		
		catch (Exception e) {
			new HisException("Ward Room Bed Master",
					"WardRoomBedMstVO.getStrBedTypeModi()", e.getMessage());

		} finally {
			if (web4 != null) {
				//web.close();
				//web = null;
			}
			
		}
		return cmbstr2;
	}
	public void setStrBedTypeModi(String strBedTypeModi) {
		this.strBedTypeModi = strBedTypeModi;
	}
	public String getStrBedTypeNameModi() {
		return strBedTypeNameModi;
	}
	public void setStrBedTypeNameModi(String strBedTypeNameModi) {
		this.strBedTypeNameModi = strBedTypeNameModi;
	}
	public String getStrBedStatusNameModi() {
		return strBedStatusNameModi;
	}
	public void setStrBedStatusNameModi(String strBedStatusNameModi) {
		this.strBedStatusNameModi = strBedStatusNameModi;
	}
	public String getStrBedStatusModi() {
		String cmbstr2 = "";
		// String errMsg ="";
		String strQuery4 ;
		
		WebRowSet web4 = null;
		String hcode=this.getStrHospitalCode();
		strQuery4 = ipd.qryHandler_ipd.getQuery(1, "select.wardroombedstatus.1");
		HisDAO dao = new HisDAO("ipd", "DAOWardRoomBedMst");
		int index;
		WebRowSet wb1;
		try {// O.V true
			
			index=dao.setQuery(strQuery4);
			dao.setQryValue(index, 1, IpdConfigUtil.SUPER_HOSPITAL_CODE.toString());
			wb1=dao.executeQry(index);
			HisUtil hisutil = new HisUtil("ipd", "WardRoomBedMstVO");
			cmbstr2=hisutil.getOptionValue(wb1, this.getStrBedStatusNameModi(), "0^Select value", false);
						
		} 
		
		catch (Exception e) {
			new HisException("Ward Room Bed Master",
					"WardRoomBedMstVO.getStrBedStatusModi()", e.getMessage());
		} finally {
			if (web4 != null) {
				//web.close();
				//web = null;
			}
			
		}
		return cmbstr2;
	}
	
	public void setStrBedStatusModi(String strBedStatusModi) {
		this.strBedStatusModi = strBedStatusModi;
	}
	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() {

		HisUtil util = new HisUtil("Ward Room Bed Master", "WardRoomBedMstVO");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");

		} catch (Exception e) {

			new HisException("Ward Room Bed Master",
					"WardRoomBedMstVO.getStrCtDate()", e.getMessage());
		} finally {
			util = null;
		}

		return strCtDate;
	}
	public String getStrmodProperties() {
		return strmodProperties;
	}
	public void setStrmodProperties(String strmodProperties) {
		this.strmodProperties = strmodProperties;
	}
	public String getStrModifyChkProperties() {
		return strModifyChkProperties;
	}
	public void setStrModifyChkProperties(String strModifyChkProperties) {
		this.strModifyChkProperties = strModifyChkProperties;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String[] getStrCheckedHidden() {
		return strCheckedHidden;
	}
	public void setStrCheckedHidden(String[] strCheckedHidden) {
		this.strCheckedHidden = strCheckedHidden;
	}
	public String getStrRoomCombo() {
		return StrRoomCombo;
	}
	public void setStrRoomCombo(String strRoomCombo) {
		StrRoomCombo = strRoomCombo;
	}
	

	

}
