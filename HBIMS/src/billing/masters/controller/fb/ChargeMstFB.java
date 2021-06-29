package billing.masters.controller.fb;
/* Package Service Master Form Bean
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;


public class ChargeMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strErrMsg = "";
	private String strLastModiSeatId = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	private String strIsPackage = "0";
	private String strMsgType =  "0";
	private String StrMsgString = "";	
	private String strAllProductCost = "";
	private String strAllTariffCost = "";
	private String strAllTotalCost = "";
	private String strAllIsAdvance = "0";
	private String strAllIsRefundable = "0";	
	private String strModuleId = "0";
	private String strCurrentDate = "";
	private String strIsAllCategory = "";	
	private String strHospitalServiceCombo = "";
	private String strGroupName = "";
	private String strGroupId = "0";
	private String strTariffName = "";
	private String strUnit = "";
	private String strDfltUnit = "";
	private String strPackageName = "";
	private String strTempPatientCategory = "";
	private String strPatientCategoryMod = "";
	private String[] strPatientCategory = null;
	private String strWardType = "0";
	private String[] strProductCost = null;
	private String[] strTariffCost = null;
	private String[] strIsAdvance = null;
	private String[] strIsRefundable = null;
	private String[] strTotalCost = null;
	private String strPackHospService = "";
	private String strPackGroupName = "";
	private String strPackTariffName = "";
	private String strPackUnit = "";
	private String strPackWardType = "0";
	private String strPackPatientCategory = "";
	private String strPackProcdCost = "";
	private String strPackTariffCost = "";
	private String strPackTotalCost = "";
	private String strPackIsAdvance = "0";
	private String strPackIsRefundable = "0";
	private String[] strPatCatArr;
	private String[] strPKTariffArr;
	private String[] strPKPatCatArr;
	private String[] strPkChargeTypeArr;
	private String[] strPKIpdChargeTypeArr;
	private String[] strPKChargeSlNoArr;
	private String[] strWardTypeArr;
	private String[] strProductCostArr;
	private String[] strTariffCostArr;
	private String[] strTotalCostArr;
	private String[] strIsAdvanceArr;
	private String[] strAdvArr;
	private String[] strIsRefundArr;
	private String[] strRefundArr;
	private String[] strFromDateArr;
	private String[] strUnitIdArr;
	private String[] strPackTrfId = null;
	private String[] strPackUnitId = null;
	private String[] strPackTotal = null;
	private String[] strPackQty = null;
	private String[] strPackProcCost = null;
	private String[] strPackTrfCost = null;
	private String[] strRefundable = null;
	private String strEffectiveFrm = null;
	private String strEffectiveTo = "";
	private String strSeatId = "";
	private String strRemarks = "";
	private String strChargeSlNo = "";
	private String strUnitLevel = "";
	private String strTempCategory = "";
	private String strUpdate = "";
	private String strSelectOption = "0";
	private String strNewProcCost = "0";
	private String strNewTrfCost = "0";
	private String strCopyPatCat = "";
	private String strCopyWardType = "0";
	private String strCopyProcCost = "0";
	private String strCopyTrfCost = "0";
	private String strHospitalCode = "";
	private String patientCategory = "";
	private String hospitalService = "0";
	private String hosSerName = "";
	private String trfName = "";
	private String patCatName = "";
	private String unitName = "";
	private String wardType = "";
	private String grpName = "";
    private String strPackageDetails = "";
	private WebRowSet packageDetails = null;
	private String strUpdateMode = "0";
	private String groupCombo = "";
	private String groupCombo1 = "";
	private String tariffCombo = "";
	private String tariffCombo1 = "";
	private String unitCombo = "";
	private String wardCombo = "";	
	private String strPatCatName;	
	private WebRowSet wbModifyData;	
	private String strModifyDtl;
	HttpSession httpSession = null;

	/**
	 * @return the strPatCatName
	 */
	public String getStrPatCatName() {
		return strPatCatName;
	}

	/**
	 * @param strPatCatName the strPatCatName to set
	 */
	public void setStrPatCatName(String strPatCatName) {
		this.strPatCatName = strPatCatName;
	}

	/** *********************** getter methods **************************** */

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @return Returns the strCopyPatCat.
	 */
	public String getStrCopyPatCat() {
		return strCopyPatCat;
	}

	/**
	 * @return Returns the strCopyProcCost.
	 */
	public String getStrCopyProcCost() {
		return strCopyProcCost;
	}

	/**
	 * @return Returns the strCopyTrfCost.
	 */
	public String getStrCopyTrfCost() {
		return strCopyTrfCost;
	}

	/**
	 * @return Returns the strCopyWardType.
	 */
	public String getStrCopyWardType() {
		return strCopyWardType;
	}

	/**
	 * @param strCopyPatCat
	 *            The strCopyPatCat to set.
	 */
	public void setStrCopyPatCat(String strCopyPatCat) {
		this.strCopyPatCat = strCopyPatCat;
	}

	/**
	 * @param strCopyProcCost
	 *            The strCopyProcCost to set.
	 */
	public void setStrCopyProcCost(String strCopyProcCost) {
		this.strCopyProcCost = strCopyProcCost;
	}

	/**
	 * @param strCopyTrfCost
	 *            The strCopyTrfCost to set.
	 */
	public void setStrCopyTrfCost(String strCopyTrfCost) {
		this.strCopyTrfCost = strCopyTrfCost;
	}

	/**
	 * @param strCopyWardType
	 *            The strCopyWardType to set.
	 */
	public void setStrCopyWardType(String strCopyWardType) {
		this.strCopyWardType = strCopyWardType;
	}

	/**
	 * @return Returns the strUpdate.
	 */
	public String getStrUpdate() {
		return strUpdate;
	}

	/**
	 * @param strUpdate
	 *            The strUpdate to set.
	 */
	public void setStrUpdate(String strUpdate) {
		this.strUpdate = strUpdate;
	}

	/**
	 * @return Returns the strTempCategory.
	 */

	/**
	 * @param strTempCategory
	 *            The strTempCategory to set.
	 */

	/**
	 * @return Returns the strUnitLevel.
	 */
	public String getStrUnitLevel() {
		return strUnitLevel;
	}

	/**
	 * @param strUnitLevel
	 *            The strUnitLevel to set.
	 */
	public void setStrUnitLevel(String strUnitLevel) {
		this.strUnitLevel = strUnitLevel;
	}

	/**
	 * @return Returns the strChargeSlNo.
	 */
	public String getStrChargeSlNo() {
		return strChargeSlNo;
	}

	/**
	 * @return Returns the remarks.
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * @return Returns the strEffectiveFrm.
	 * @throws Exception
	 */
	public String getStrEffectiveFrm() {
		if (this.strEffectiveFrm == null || this.strEffectiveFrm.equals("")) {
			HisUtil util = new HisUtil("billing", "ChargeMstFB");
			try {
				strEffectiveFrm = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Billing", "ChargeMstFB.getEffectiveFrm()-->",
						e.getMessage());
			}
		}
		return strEffectiveFrm;
	}

	/**
	 * @return Returns the strEffectiveTo.
	 */
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}

	/**
	 * @return Returns the strErrMsg.
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}

	/**
	 * @return Returns the strGroupName.
	 */
	public String getStrGroupName() {
		return strGroupName;
	}

	/**
	 * @return Returns the strHospitalService.
	 */
	
	/**
	 * @return Returns the strIsAdvance.
	 */
	public String[] getStrIsAdvance() {
		return strIsAdvance;
	}

	/**
	 * @return Returns the strIsPackage.
	 */
	public String getStrIsPackage() {
		return strIsPackage;
	}

	/**
	 * @return Returns the strIsRefundable.
	 */
	public String[] getStrIsRefundable() {
		return strIsRefundable;
	}

	/**
	 * @return Returns the strNormalMsg.
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	/**
	 * @return Returns the strPackageName.
	 */
	public String getStrPackageName() {
		return strPackageName;
	}

	/**
	 * @return Returns the strPackGroupName.
	 */
	public String getStrPackGroupName() {
		return strPackGroupName;
	}

	/**
	 * @return Returns the strPackHospService.
	 */
	public String getStrPackHospService() {
		return strPackHospService;
	}

	/**
	 * @return Returns the strPackIsAdvance.
	 */
	public String getStrPackIsAdvance() {
		return strPackIsAdvance;
	}

	/**
	 * @return Returns the strPackIsRefundable.
	 */
	public String getStrPackIsRefundable() {
		return strPackIsRefundable;
	}

	/**
	 * @return Returns the strPackPatientCategory.
	 */
	public String getStrPackPatientCategory() {
		return strPackPatientCategory;
	}

	/**
	 * @return Returns the strPackProcCost.
	 */
	public String[] getStrPackProcCost() {
		return strPackProcCost;
	}

	/**
	 * @return Returns the strPackProcdCost.
	 */
	public String getStrPackProcdCost() {
		return strPackProcdCost;
	}

	/**
	 * @return Returns the strPackQty.
	 */
	public String[] getStrPackQty() {
		return strPackQty;
	}

	/**
	 * @return Returns the strPackTariffCost.
	 */
	public String getStrPackTariffCost() {
		return strPackTariffCost;
	}

	/**
	 * @return Returns the strPackTariffName.
	 */
	public String getStrPackTariffName() {
		return strPackTariffName;
	}

	/**
	 * @return Returns the strPackTotal.
	 */
	public String[] getStrPackTotal() {
		return strPackTotal;
	}

	/**
	 * @return Returns the strPackTotalCost.
	 */
	public String getStrPackTotalCost() {
		return strPackTotalCost;
	}

	/**
	 * @return Returns the strPackTrfCost.
	 */
	public String[] getStrPackTrfCost() {
		return strPackTrfCost;
	}

	/**
	 * @return Returns the strPackTrfId.
	 */
	public String[] getStrPackTrfId() {
		return strPackTrfId;
	}

	/**
	 * @return Returns the strPackUnit.
	 */
	public String getStrPackUnit() {
		return strPackUnit;
	}

	/**
	 * @return Returns the strPackWardType.
	 */
	public String getStrPackWardType() {
		return strPackWardType;
	}

	/**
	 * @return Returns the strPatientCategory.
	 */
	public String[] getStrPatientCategory() {
		return strPatientCategory;
	}

	/**
	 * @return Returns the strProcedureCost.
	 */
	public String[] getStrProductCost() {
		return strProductCost;
	}

	/**
	 * @return Returns the strRefundable.
	 */
	public String[] getStrRefundable() {
		return strRefundable;
	}

	/**
	 * @return Returns the strSeatId.
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * @return Returns the strTariffCost.
	 */
	public String[] getStrTariffCost() {
		return strTariffCost;
	}

	/**
	 * @return Returns the strTariffName.
	 */
	public String getStrTariffName() {
		return strTariffName;
	}

	/**
	 * @return Returns the strTotalCost.
	 */
	public String[] getStrTotalCost() {
		return strTotalCost;
	}

	/**
	 * @return Returns the strUnit.
	 */
	public String getStrUnit() {
		return strUnit;
	}

	/**
	 * @return Returns the strWardType.
	 */
	public String getStrWardType() {
		return strWardType;
	}

	/**
	 * @return Returns the strWarningMsg.
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	/**
	 * @return Returns the strNewProcCost.
	 */
	public String getStrNewProcCost() {
		return strNewProcCost;
	}

	/**
	 * @return Returns the strNewTrfCost.
	 */
	public String getStrNewTrfCost() {
		return strNewTrfCost;
	}

	/**
	 * @return Returns the strSelectOption.
	 */
	public String getStrSelectOption() {
		return strSelectOption;
	}

	/** *********************** setter methods **************************** */

	/**
	 * @param strChargeSlNo
	 *            The strChargeSlNo to set.
	 */
	public void setStrChargeSlNo(String strChargeSlNo) {
		this.strChargeSlNo = strChargeSlNo;
	}

	/**
	 * @param remarks
	 *            The remarks to set.
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * @param strEffectiveFrm
	 *            The strEffectiveFrm to set.
	 */
	public void setStrEffectiveFrm(String strEffectiveFrm) {
		this.strEffectiveFrm = strEffectiveFrm;
	}

	/**
	 * @param strEffectiveTo
	 *            The strEffectiveTo to set.
	 */
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	/**
	 * @param strErrMsg
	 *            The strErrMsg to set.
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * @param strGroupName
	 *            The strGroupName to set.
	 */
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}

	/**
	 * @param strHospitalService
	 *            The strHospitalService to set.
	 */
	public void setStrHospitalService(String strHospitalServiceCombo) {
		this.strHospitalServiceCombo = strHospitalServiceCombo;
	}

	/**
	 * @param strIsAdvance
	 *            The strIsAdvance to set.
	 */
	public void setStrIsAdvance(String[] strIsAdvance) {
		this.strIsAdvance = strIsAdvance;
	}

	/**
	 * @param strIsPackage
	 *            The strIsPackage to set.
	 */
	public void setStrIsPackage(String strIsPackage) {
		this.strIsPackage = strIsPackage;
	}

	/**
	 * @param strIsRefundable
	 *            The strIsRefundable to set.
	 */
	public void setStrIsRefundable(String[] strIsRefundable) {
		this.strIsRefundable = strIsRefundable;
	}

	/**
	 * @param strNormalMsg
	 *            The strNormalMsg to set.
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	/**
	 * @param strPackageName
	 *            The strPackageName to set.
	 */
	public void setStrPackageName(String strPackageName) {
		this.strPackageName = strPackageName;
	}

	/**
	 * @param strPackGroupName
	 *            The strPackGroupName to set.
	 */
	public void setStrPackGroupName(String strPackGroupName) {
		this.strPackGroupName = strPackGroupName;
	}

	/**
	 * @param strPackHospService
	 *            The strPackHospService to set.
	 */
	public void setStrPackHospService(String strPackHospService) {
		this.strPackHospService = strPackHospService;
	}

	/**
	 * @param strPackIsAdvance
	 *            The strPackIsAdvance to set.
	 */
	public void setStrPackIsAdvance(String strPackIsAdvance) {
		this.strPackIsAdvance = strPackIsAdvance;
	}

	/**
	 * @param strPackIsRefundable
	 *            The strPackIsRefundable to set.
	 */
	public void setStrPackIsRefundable(String strPackIsRefundable) {
		this.strPackIsRefundable = strPackIsRefundable;
	}

	/**
	 * @param strPackPatientCategory
	 *            The strPackPatientCategory to set.
	 */
	public void setStrPackPatientCategory(String strPackPatientCategory) {
		this.strPackPatientCategory = strPackPatientCategory;
	}

	/**
	 * @param strPackProcCost
	 *            The strPackProcCost to set.
	 */
	public void setStrPackProcCost(String[] strPackProcCost) {
		this.strPackProcCost = strPackProcCost;
	}

	/**
	 * @param strPackProcdCost
	 *            The strPackProcdCost to set.
	 */
	public void setStrPackProcdCost(String strPackProcdCost) {
		this.strPackProcdCost = strPackProcdCost;
	}

	/**
	 * @param strPackQty
	 *            The strPackQty to set.
	 */
	public void setStrPackQty(String[] strPackQty) {
		this.strPackQty = strPackQty;
	}

	/**
	 * @param strPackTariffCost
	 *            The strPackTariffCost to set.
	 */
	public void setStrPackTariffCost(String strPackTariffCost) {
		this.strPackTariffCost = strPackTariffCost;
	}

	/**
	 * @param strPackTariffName
	 *            The strPackTariffName to set.
	 */
	public void setStrPackTariffName(String strPackTariffName) {
		this.strPackTariffName = strPackTariffName;
	}

	/**
	 * @param strPackTotal
	 *            The strPackTotal to set.
	 */
	public void setStrPackTotal(String[] strPackTotal) {
		this.strPackTotal = strPackTotal;
	}

	/**
	 * @param strPackTotalCost
	 *            The strPackTotalCost to set.
	 */
	public void setStrPackTotalCost(String strPackTotalCost) {
		this.strPackTotalCost = strPackTotalCost;
	}

	/**
	 * @param strPackTrfCost
	 *            The strPackTrfCost to set.
	 */
	public void setStrPackTrfCost(String[] strPackTrfCost) {
		this.strPackTrfCost = strPackTrfCost;
	}

	/**
	 * @param strPackTrfId
	 *            The strPackTrfId to set.
	 */
	public void setStrPackTrfId(String[] strPackTrfId) {
		this.strPackTrfId = strPackTrfId;
	}

	/**
	 * @param strPackUnit
	 *            The strPackUnit to set.
	 */
	public void setStrPackUnit(String strPackUnit) {
		this.strPackUnit = strPackUnit;
	}

	/**
	 * @param strPackWardType
	 *            The strPackWardType to set.
	 */
	public void setStrPackWardType(String strPackWardType) {
		this.strPackWardType = strPackWardType;
	}

	/**
	 * @param strPatientCategory
	 *            The strPatientCategory to set.
	 */
	public void setStrPatientCategory(String[] strPatientCategory) {
		this.strPatientCategory = strPatientCategory;
	}

	/**
	 * @param strProcedureCost
	 *            The strProcedureCost to set.
	 */
	public void setStrProductCost(String[] strProductCost) {
		this.strProductCost = strProductCost;
	}

	/**
	 * @param strRefundable
	 *            The strRefundable to set.
	 */
	public void setStrRefundable(String[] strRefundable) {
		this.strRefundable = strRefundable;
	}

	/**
	 * @param strSeatId
	 *            The strSeatId to set.
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * @param strTariffCost
	 *            The strTariffCost to set.
	 */
	public void setStrTariffCost(String[] strTariffCost) {
		this.strTariffCost = strTariffCost;
	}

	/**
	 * @param strTariffName
	 *            The strTariffName to set.
	 */
	public void setStrTariffName(String strTariffName) {
		this.strTariffName = strTariffName;
	}

	/**
	 * @param strTotalCost
	 *            The strTotalCost to set.
	 */
	public void setStrTotalCost(String[] strTotalCost) {
		this.strTotalCost = strTotalCost;
	}

	/**
	 * @param strUnit
	 *            The strUnit to set.
	 */
	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
	}

	/**
	 * @param strWardType
	 *            The strWardType to set.
	 */
	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}

	/**
	 * @param strWarningMsg
	 *            The strWarningMsg to set.
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	/**
	 * @param strNewProcCost
	 *            The strNewProcCost to set.
	 */
	public void setStrNewProcCost(String strNewProcCost) {
		this.strNewProcCost = strNewProcCost;
	}

	/**
	 * @param strNewTrfCost
	 *            The strNewTrfCost to set.
	 */
	public void setStrNewTrfCost(String strNewTrfCost) {
		this.strNewTrfCost = strNewTrfCost;
	}

	/**
	 * @param strSelectOption
	 *            The strSelectOption to set.
	 */
	public void setStrSelectOption(String strSelectOption) {
		this.strSelectOption = strSelectOption;
	}

	/** ******************** Combo Functions *************************** */

	/**
	 * To rertrieve Hospital Service Combo.
	 * 
	 * @return String
	 */
	public String getStrHospitalServiceCombo() {
		
		return strHospitalServiceCombo;
	}

	

	/**
	 * To rertrieve Group Name Combo.
	 * 
	 * @return String
	 */
	public String getGroupCombo() {
		

		
		return groupCombo;
	}

	

	/**
	 * To Retrieve Group Name Combo For Package Type.
	 * 
	 * @return String
	 */
	public String getGroupCombo1() {

		

		
		return groupCombo1;
	}

	/**
	 * To Rertrieve Tariff Name Combo.
	 * 
	 * @return String
	 */

	public String getTariffCombo() {
		

		
		return tariffCombo;
	}

	public String getTariffCombo1() {
		

		
		return tariffCombo1;
	}

	

	/**
	 * To Rertrieve Unit Combo.
	 * 
	 * @return String
	 */
	
	public String getUnitCombo() {
		

		
		return unitCombo;
	}

	

	/**
	 * To Rertrieve Ward Combo.
	 * 
	 * @return String
	 */
	

	public String getWardCombo() {
		

		
		return wardCombo;
	}

	/**
	 * To Rertrieve Patient Category Combo.
	 * 
	 * @return String
	 */
	public String getPatientCategory() {

		
		return patientCategory;
	}

	/**
	 * To Rertrieve Package Data , SubTariffs And Their Respective Cost.
	 * 
	 * @return String
	 */
	public String getDataOnPack() {
		String strData = "";
		return strData;
	}



	/**
	 * To Rertrieve Current Date.
	 * 
	 * @return String
	 */
	public String getCurrentDate() {
		HisUtil util = new HisUtil("billing", "VOChargeMst");
		String strCurDate = "";
		try {
			strCurDate = util.getASDate("dd-MMM-yyyy");
		} catch (Exception e) {
			new HisException("Billing", "VOChargeMst.getCurrentDate -->", e
					.getMessage());
		}
		return strCurDate;
	}

	/**
	 * @return Returns the strDfltUnit.
	 */
	public String getStrDfltUnit() {
		return strDfltUnit;
	}

	/**
	 * @param strDfltUnit
	 *            The strDfltUnit to set.
	 */
	public void setStrDfltUnit(String strDfltUnit) {
		this.strDfltUnit = strDfltUnit;
	}

	public String getStrLastModiSeatId() {
		return strLastModiSeatId;
	}

	public void setStrLastModiSeatId(String strLastModiSeatId) {
		this.strLastModiSeatId = strLastModiSeatId;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	public String getHospitalService() {
		return hospitalService;
	}

	public void setHospitalService(String hospitalService) {
		this.hospitalService = hospitalService;
	}

	public String getHosSerName() {
		return hosSerName;
	}

	public void setHosSerName(String hosSerName) {
		this.hosSerName = hosSerName;
	}

	public String getTrfName() {
		return trfName;
	}

	public void setTrfName(String trfName) {
		this.trfName = trfName;
	}

	public String getPatCatName() {
		return patCatName;
	}

	public void setPatCatName(String patCatName) {
		this.patCatName = patCatName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getWardType() {
		return wardType;
	}

	public void setWardType(String wardType) {
		this.wardType = wardType;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getStrTempCategory() {
		return strTempCategory;
	}

	public void setStrTempCategory(String strTempCategory) {
		this.strTempCategory = strTempCategory;

	}

	public String getStrTempPatientCategory() {
		return strTempPatientCategory;
	}

	public void setStrTempPatientCategory(String strTempPatientCategory) {
		this.strTempPatientCategory = strTempPatientCategory;
	}

	public String getStrUpdateMode() {
		return strUpdateMode;
	}

	public void setStrUpdateMode(String strUpdateMode) {
		this.strUpdateMode = strUpdateMode;
	}

	public String getStrPatientCategoryMod() {
		return strPatientCategoryMod;
	}

	public void setStrPatientCategoryMod(String strPatientCategoryMod) {
		this.strPatientCategoryMod = strPatientCategoryMod;
	}

	public String[] getStrPackUnitId() {
		return strPackUnitId;
	}

	public void setStrPackUnitId(String[] strPackUnitId) {
		this.strPackUnitId = strPackUnitId;
	}

	public String getStrPackageDetails() {
		return strPackageDetails;
	}

	public void setStrPackageDetails(String strPackageDetails) {
		this.strPackageDetails = strPackageDetails;
	}

	public WebRowSet getPackageDetails() {
		return packageDetails;
	}

	public void setPackageDetails(WebRowSet packageDetails) {
		this.packageDetails = packageDetails;
	}

	public String getStrModuleId() {
		return strModuleId;
	}

	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}

	public String getStrCurrentDate() {
		HisUtil util = new HisUtil("billing", "ChargeMstVO");
		String strCurDate = "";
		try {
			strCurDate = util.getASDate("dd-MMM-yyyy");
		} catch (Exception e) {
			new HisException("Billing", "ChargeMstVO.getCurrentDate -->", e
					.getMessage());
		}
		strCurrentDate=strCurDate;
		return strCurrentDate;
		
		
	}

	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}

	public String getStrIsAllCategory() {
		return strIsAllCategory;
	}

	public void setStrIsAllCategory(String strIsAllCategory) {
		this.strIsAllCategory = strIsAllCategory;
	}

	public String getStrAllProductCost() {
		return strAllProductCost;
	}

	public void setStrAllProductCost(String strAllProductCost) {
		this.strAllProductCost = strAllProductCost;
	}

	public String getStrAllTariffCost() {
		return strAllTariffCost;
	}

	public void setStrAllTariffCost(String strAllTariffCost) {
		this.strAllTariffCost = strAllTariffCost;
	}

	public String getStrAllTotalCost() {
		return strAllTotalCost;
	}

	public void setStrAllTotalCost(String strAllTotalCost) {
		this.strAllTotalCost = strAllTotalCost;
	}

	public String getStrAllIsAdvance() {
		return strAllIsAdvance;
	}

	public void setStrAllIsAdvance(String strAllIsAdvance) {
		this.strAllIsAdvance = strAllIsAdvance;
	}

	public String getStrAllIsRefundable() {
		return strAllIsRefundable;
	}

	public void setStrAllIsRefundable(String strAllIsRefundable) {
		this.strAllIsRefundable = strAllIsRefundable;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	/**
	 * @return the wbModifyData
	 */
	public WebRowSet getWbModifyData() {
		return wbModifyData;
	}

	/**
	 * @param wbModifyData the wbModifyData to set
	 */
	public void setWbModifyData(WebRowSet wbModifyData) {
		this.wbModifyData = wbModifyData;
	}

	/**
	 * @return the strModifyDtl
	 */
	public String getStrModifyDtl() {
		return strModifyDtl;
	}

	/**
	 * @param strModifyDtl the strModifyDtl to set
	 */
	public void setStrModifyDtl(String strModifyDtl) {
		this.strModifyDtl = strModifyDtl;
	}

	/**
	 * @return the strPatCatArr
	 */
	public String[] getStrPatCatArr() {
		return strPatCatArr;
	}

	/**
	 * @param strPatCatArr the strPatCatArr to set
	 */
	public void setStrPatCatArr(String[] strPatCatArr) {
		this.strPatCatArr = strPatCatArr;
	}

	/**
	 * @return the strPKTariffArr
	 */
	public String[] getStrPKTariffArr() {
		return strPKTariffArr;
	}

	/**
	 * @param strPKTariffArr the strPKTariffArr to set
	 */
	public void setStrPKTariffArr(String[] strPKTariffArr) {
		this.strPKTariffArr = strPKTariffArr;
	}

	/**
	 * @return the strPKPatCatArr
	 */
	public String[] getStrPKPatCatArr() {
		return strPKPatCatArr;
	}

	/**
	 * @param strPKPatCatArr the strPKPatCatArr to set
	 */
	public void setStrPKPatCatArr(String[] strPKPatCatArr) {
		this.strPKPatCatArr = strPKPatCatArr;
	}

	/**
	 * @return the strPkChargeTypeArr
	 */
	public String[] getStrPkChargeTypeArr() {
		return strPkChargeTypeArr;
	}

	/**
	 * @param strPkChargeTypeArr the strPkChargeTypeArr to set
	 */
	public void setStrPkChargeTypeArr(String[] strPkChargeTypeArr) {
		this.strPkChargeTypeArr = strPkChargeTypeArr;
	}

	/**
	 * @return the strPKIpdChargeTypeArr
	 */
	public String[] getStrPKIpdChargeTypeArr() {
		return strPKIpdChargeTypeArr;
	}

	/**
	 * @param strPKIpdChargeTypeArr the strPKIpdChargeTypeArr to set
	 */
	public void setStrPKIpdChargeTypeArr(String[] strPKIpdChargeTypeArr) {
		this.strPKIpdChargeTypeArr = strPKIpdChargeTypeArr;
	}

	/**
	 * @return the strPKChargeSlNoArr
	 */
	public String[] getStrPKChargeSlNoArr() {
		return strPKChargeSlNoArr;
	}

	/**
	 * @param strPKChargeSlNoArr the strPKChargeSlNoArr to set
	 */
	public void setStrPKChargeSlNoArr(String[] strPKChargeSlNoArr) {
		this.strPKChargeSlNoArr = strPKChargeSlNoArr;
	}

	/**
	 * @return the strWardTypeArr
	 */
	public String[] getStrWardTypeArr() {
		return strWardTypeArr;
	}

	/**
	 * @param strWardTypeArr the strWardTypeArr to set
	 */
	public void setStrWardTypeArr(String[] strWardTypeArr) {
		this.strWardTypeArr = strWardTypeArr;
	}

	/**
	 * @return the strProductCostArr
	 */
	public String[] getStrProductCostArr() {
		return strProductCostArr;
	}

	/**
	 * @param strProductCostArr the strProductCostArr to set
	 */
	public void setStrProductCostArr(String[] strProductCostArr) {
		this.strProductCostArr = strProductCostArr;
	}

	/**
	 * @return the strTariffCostArr
	 */
	public String[] getStrTariffCostArr() {
		return strTariffCostArr;
	}

	/**
	 * @param strTariffCostArr the strTariffCostArr to set
	 */
	public void setStrTariffCostArr(String[] strTariffCostArr) {
		this.strTariffCostArr = strTariffCostArr;
	}

	/**
	 * @return the strTotalCostArr
	 */
	public String[] getStrTotalCostArr() {
		return strTotalCostArr;
	}

	/**
	 * @param strTotalCostArr the strTotalCostArr to set
	 */
	public void setStrTotalCostArr(String[] strTotalCostArr) {
		this.strTotalCostArr = strTotalCostArr;
	}

	/**
	 * @return the strIsAdvanceArr
	 */
	public String[] getStrIsAdvanceArr() {
		return strIsAdvanceArr;
	}

	/**
	 * @param strIsAdvanceArr the strIsAdvanceArr to set
	 */
	public void setStrIsAdvanceArr(String[] strIsAdvanceArr) {
		this.strIsAdvanceArr = strIsAdvanceArr;
	}

	/**
	 * @return the strAdvArr
	 */
	public String[] getStrAdvArr() {
		return strAdvArr;
	}

	/**
	 * @param strAdvArr the strAdvArr to set
	 */
	public void setStrAdvArr(String[] strAdvArr) {
		this.strAdvArr = strAdvArr;
	}

	/**
	 * @return the strIsRefundArr
	 */
	public String[] getStrIsRefundArr() {
		return strIsRefundArr;
	}

	/**
	 * @param strIsRefundArr the strIsRefundArr to set
	 */
	public void setStrIsRefundArr(String[] strIsRefundArr) {
		this.strIsRefundArr = strIsRefundArr;
	}

	/**
	 * @return the strRefundArr
	 */
	public String[] getStrRefundArr() {
		return strRefundArr;
	}

	/**
	 * @param strRefundArr the strRefundArr to set
	 */
	public void setStrRefundArr(String[] strRefundArr) {
		this.strRefundArr = strRefundArr;
	}

	/**
	 * @param strHospitalServiceCombo the strHospitalServiceCombo to set
	 */
	public void setStrHospitalServiceCombo(String strHospitalServiceCombo) {
		this.strHospitalServiceCombo = strHospitalServiceCombo;
	}

	/**
	 * @return the strFromDateArr
	 */
	public String[] getStrFromDateArr() {
		return strFromDateArr;
	}

	/**
	 * @param strFromDateArr the strFromDateArr to set
	 */
	public void setStrFromDateArr(String[] strFromDateArr) {
		this.strFromDateArr = strFromDateArr;
	}

	/**
	 * @return the strUnitIdArr
	 */
	public String[] getStrUnitIdArr() {
		return strUnitIdArr;
	}

	/**
	 * @param strUnitIdArr the strUnitIdArr to set
	 */
	public void setStrUnitIdArr(String[] strUnitIdArr) {
		this.strUnitIdArr = strUnitIdArr;
	}

	public void setGroupCombo1(String groupCombo1) {
		this.groupCombo1 = groupCombo1;
	}

	public void setPatientCategory(String patientCategory) {
		this.patientCategory = patientCategory;
	}

	public void setGroupCombo(String groupCombo) {
		this.groupCombo = groupCombo;
	}

	public void setTariffCombo(String tariffCombo) {
		this.tariffCombo = tariffCombo;
	}

	public void setTariffCombo1(String tariffCombo1) {
		this.tariffCombo1 = tariffCombo1;
	}

	public void setUnitCombo(String unitCombo) {
		this.unitCombo = unitCombo;
	}

	public void setWardCombo(String wardCombo) {
		this.wardCombo = wardCombo;
	}

	public String getStrMsgString() {
		return StrMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		StrMsgString = strMsgString;
	}
}

	

