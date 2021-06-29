package mms.transactions.vo;

import javax.sql.rowset.WebRowSet;

public class SampleRegisterPurTransVO {
private static final long serialVersionUID = 02L;
	

	private Boolean BExistStatus = false;
	private String strMsgString = "";
	private String strMsgType = "";
	private String strCtDate = "";
	private String strEffectiveFrom = "";
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strGatePassNo="";
	private String strApprovalStatus="";
	private String strCommitteeMemberHidden[]=null;
	private String strMemberRecommendation[]=null;
	private String strStoreId = "";
	private String strStoreName = "";
	private String strItemId = "";
	private String strItemBrandId = "";
	private String strBatchSlno = "";
	private String strItemReciveNo = "";
	private String strRemarks = "";
	private String strRecieveQty[] = {"0"};
	private String strRecieveQtyUnitId[] = {"0"};
	private String strFileNo="";
	private String strPageNo="";
	private String strFileName="";
	private String strExpDate[]=null;
	private String strIssueQty[] = {"0"};
	private String strIssueQtyUnitId []= {"0"};
	private String strCondemenedQty[] = {"0"};
	private String strCondemenedQtyUnitId[] = {"0"};
	private String strItemName="";
	private String strItemBrandName="";
	private String strRecQty="";
	private String strRecQtyUnitId="";
	private String strItemCategDtl="";
	private WebRowSet strSupplierWS=null;
	private String strChk="";
	private String strUnitId="";
	private String strQuotationNo="";
	private String strTenderNo="";
	private String strSupplierName="";
	private String strSampleName="";
	private String strStoreTypeId="";
	private String strGroupName="";
	private String strItemCatNo="";
	private String strFinancialStartYear="";
	private String strFinancialEndYear="";
	private String strSupplierId="";
	private String strBatchSlNo[]=null;
	private String strChkHidden[]=null;
	private String strUnitName[]=null;
	private String strQty []=null;
	
	private String itemParamValue []=null;
	private String strUnit[]=null; 
	private WebRowSet itemDetailsWS=null;
	private WebRowSet unitWS=null;
	private WebRowSet groupWs=null;
	private Boolean isFlag=false;
	private String strSampleRecNoHidden="";
	private String strSampleItemIdHidden="";
	private String strSampleItemBrandIdHidden="";
	private String strSampleItemBatchSlNoHidden="";
	private String strQuotationNoHidden[]=null;
	private String strTenderNoHidden[]=null;
	private String strSupplierNoHidden[]=null;
	private String strIssueBy="";
	private String strSubGroupName="";
	private WebRowSet consultantWs=null;
	private String strConsultantName="";
	private String strConsultantValues="";
	private String strItemIdHidden[]=null;
	private String strItemBrandIdHidden[]=null;
	private String strItemBatchSlnoHidden[]=null;	
	private String strLength="";
	private String strSampleRecNo[]=null;
	private String strItemCategory="";
	private String	strItemCategoryValue="";
	private String strCommitteeType="";
	private WebRowSet itemCategoryWS=null;
	private WebRowSet committeTypeWS=null;
	private WebRowSet committeMemberWS=null;
	private String strIsGatePassIssue="";
	private String strSampleRecieveNo="";
	private String strReturnTo="";
	private String strUnitVal="";
	private String strTendorDate="";
	private String strQuotationDate="";
	
	private String strSampleStatus = "";
	private String strItemUnitId = "";
	private WebRowSet proposalNoWs = null;
	private WebRowSet itemWs = null;
	private String strProposalNo = "";
	private String strIsValid = "";
	private String strExpiryDate = "";
	private String strIssuedQty = "";
	private String strReturnQty = "";
	private String strCondemnQty = "";
	private String strSampleItemStatus = "";
	private String strComRmksSlNo = "";
	
	private String strRecQtyUnit = "";
	/**
	 * @return the strRecQtyUnit
	 */
	public String getStrRecQtyUnit() {
		return strRecQtyUnit;
	}
	/**
	 * @param strRecQtyUnit the strRecQtyUnit to set
	 */
	public void setStrRecQtyUnit(String strRecQtyUnit) {
		this.strRecQtyUnit = strRecQtyUnit;
	}
	/**
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}
	/**
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrTendorDate() {
		return strTendorDate;
	}
	public void setStrTendorDate(String strTendorDate) {
		this.strTendorDate = strTendorDate;
	}
	public String getStrQuotationDate() {
		return strQuotationDate;
	}
	public void setStrQuotationDate(String strQuotationDate) {
		this.strQuotationDate = strQuotationDate;
	}
	public String getStrIsGatePassIssue() {
		return strIsGatePassIssue;
	}
	public void setStrIsGatePassIssue(String strIsGatePassIssue) {
		this.strIsGatePassIssue = strIsGatePassIssue;
	}
	public String[] getStrSampleRecNo() {
		return strSampleRecNo;
	}
	public void setStrSampleRecNo(String[] strSampleRecNo) {
		this.strSampleRecNo = strSampleRecNo;
	}
	public String getStrLength() {
		return strLength;
	}
	public void setStrLength(String strLength) {
		this.strLength = strLength;
	}
	public String[] getStrItemIdHidden() {
		return strItemIdHidden;
	}
	public void setStrItemIdHidden(String[] strItemIdHidden) {
		this.strItemIdHidden = strItemIdHidden;
	}
	public String[] getStrItemBrandIdHidden() {
		return strItemBrandIdHidden;
	}
	public void setStrItemBrandIdHidden(String[] strItemBrandIdHidden) {
		this.strItemBrandIdHidden = strItemBrandIdHidden;
	}
	public String[] getStrItemBatchSlnoHidden() {
		return strItemBatchSlnoHidden;
	}
	public void setStrItemBatchSlnoHidden(String[] strItemBatchSlnoHidden) {
		this.strItemBatchSlnoHidden = strItemBatchSlnoHidden;
	}
	public String getStrSubGroupName() {
		return strSubGroupName;
	}
	public void setStrSubGroupName(String strSubGroupName) {
		this.strSubGroupName = strSubGroupName;
	}
	public String getStrIssueBy() {
		return strIssueBy;
	}
	public void setStrIssueBy(String strIssueBy) {
		this.strIssueBy = strIssueBy;
	}
	public String getStrSampleRecNoHidden() {
		return strSampleRecNoHidden;
	}
	public void setStrSampleRecNoHidden(String strSampleRecNoHidden) {
		this.strSampleRecNoHidden = strSampleRecNoHidden;
	}
	public String getStrSampleItemIdHidden() {
		return strSampleItemIdHidden;
	}
	public void setStrSampleItemIdHidden(String strSampleItemIdHidden) {
		this.strSampleItemIdHidden = strSampleItemIdHidden;
	}
	public String getStrSampleItemBrandIdHidden() {
		return strSampleItemBrandIdHidden;
	}
	public void setStrSampleItemBrandIdHidden(String strSampleItemBrandIdHidden) {
		this.strSampleItemBrandIdHidden = strSampleItemBrandIdHidden;
	}
	public String getStrSampleItemBatchSlNoHidden() {
		return strSampleItemBatchSlNoHidden;
	}
	public void setStrSampleItemBatchSlNoHidden(String strSampleItemBatchSlNoHidden) {
		this.strSampleItemBatchSlNoHidden = strSampleItemBatchSlNoHidden;
	}
	public String[] getStrQuotationNoHidden() {
		return strQuotationNoHidden;
	}
	public void setStrQuotationNoHidden(String[] strQuotationNoHidden) {
		this.strQuotationNoHidden = strQuotationNoHidden;
	}
	public String[] getStrTenderNoHidden() {
		return strTenderNoHidden;
	}
	public void setStrTenderNoHidden(String[] strTenderNoHidden) {
		this.strTenderNoHidden = strTenderNoHidden;
	}
	public String[] getStrSupplierNoHidden() {
		return strSupplierNoHidden;
	}
	public void setStrSupplierNoHidden(String[] strSupplierNoHidden) {
		this.strSupplierNoHidden = strSupplierNoHidden;
	}
	public Boolean getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(Boolean isFlag) {
		this.isFlag = isFlag;
	}
	public String[] getStrQty() {
		return strQty;
	}
	public void setStrQty(String[] strQty) {
		this.strQty = strQty;
	}
	public String[] getItemParamValue() {
		return itemParamValue;
	}
	public void setItemParamValue(String[] itemParamValue) {
		this.itemParamValue = itemParamValue;
	}
	public String getStrGroupName() {
		return strGroupName;
	}
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}
	public String getStrStoreTypeId() {
		return strStoreTypeId;
	}
	public void setStrStoreTypeId(String strStoreTypeId) {
		this.strStoreTypeId = strStoreTypeId;
	}
	public String getStrChk() {
		return strChk;
	}
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	public String getStrUnitId() {
		return strUnitId;
	}
	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}
	public WebRowSet getItemDetailsWS() {
		return itemDetailsWS;
	}
	public void setItemDetailsWS(WebRowSet itemDetailsWS) {
		this.itemDetailsWS = itemDetailsWS;
	}
	public WebRowSet getUnitWS() {
		return unitWS;
	}
	public void setUnitWS(WebRowSet unitWS) {
		this.unitWS = unitWS;
	}
	public WebRowSet getStrSupplierWS() {
		return strSupplierWS;
	}
	public void setStrSupplierWS(WebRowSet strSupplierWS) {
		this.strSupplierWS = strSupplierWS;
	}
	/**
	 * @return the strCtDate
	 */
	public String getStrCtDate() {
		return strCtDate;
	}
	/**
	 * @param strCtDate the strCtDate to set
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	/**
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	/**
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the bExistStatus
	 */
	public Boolean getBExistStatus() {
		return BExistStatus;
	}
	/**
	 * @param existStatus the bExistStatus to set
	 */
	public void setBExistStatus(Boolean existStatus) {
		BExistStatus = existStatus;
	}
	/**
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}
	/**
	 * @param strMsgString the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}
	/**
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	/**
	 * @return the strSubGroupCombo
	 */
	
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public String getStrBatchSlno() {
		return strBatchSlno;
	}
	public void setStrBatchSlno(String strBatchSlno) {
		this.strBatchSlno = strBatchSlno;
	}
	public String getStrItemReciveNo() {
		return strItemReciveNo;
	}
	public void setStrItemReciveNo(String strItemReciveNo) {
		this.strItemReciveNo = strItemReciveNo;
	}
	public String getStrQuotationNo() {
		return strQuotationNo;
	}
	public void setStrQuotationNo(String strQuotationNo) {
		this.strQuotationNo = strQuotationNo;
	}
	public String getStrTenderNo() {
		return strTenderNo;
	}
	public void setStrTenderNo(String strTenderNo) {
		this.strTenderNo = strTenderNo;
	}
	public String getStrSupplierName() {
		return strSupplierName;
	}
	public void setStrSupplierName(String strSupplierName) {
		this.strSupplierName = strSupplierName;
	}
	public String getStrSampleName() {
		return strSampleName;
	}
	public void setStrSampleName(String strSampleName) {
		this.strSampleName = strSampleName;
	}
	public WebRowSet getGroupWs() {
		return groupWs;
	}
	public void setGroupWs(WebRowSet groupWs) {
		this.groupWs = groupWs;
	}
	public String getStrItemCatNo() {
		return strItemCatNo;
	}
	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}
	public String[] getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String[] strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String getStrFinancialStartYear() {
		return strFinancialStartYear;
	}
	public void setStrFinancialStartYear(String strFinancialStartYear) {
		this.strFinancialStartYear = strFinancialStartYear;
	}
	public String getStrFinancialEndYear() {
		return strFinancialEndYear;
	}
	public void setStrFinancialEndYear(String strFinancialEndYear) {
		this.strFinancialEndYear = strFinancialEndYear;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrSupplierId() {
		return strSupplierId;
	}
	public void setStrSupplierId(String strSupplierId) {
		this.strSupplierId = strSupplierId;
	}
	public String[] getStrChkHidden() {
		return strChkHidden;
	}
	public void setStrChkHidden(String[] strChkHidden) {
		this.strChkHidden = strChkHidden;
	}
	
	public String getStrReturnTo() {
		return strReturnTo;
	}
	public void setStrReturnTo(String strReturnTo) {
		this.strReturnTo = strReturnTo;
	}
	public String[] getStrUnit() {
		return strUnit;
	}
	public void setStrUnit(String[] strUnit) {
		this.strUnit = strUnit;
	}
	public String[] getStrRecieveQty() {
		return strRecieveQty;
	}
	public void setStrRecieveQty(String[] strRecieveQty) {
		this.strRecieveQty = strRecieveQty;
	}
	public String[] getStrRecieveQtyUnitId() {
		return strRecieveQtyUnitId;
	}
	public void setStrRecieveQtyUnitId(String[] strRecieveQtyUnitId) {
		this.strRecieveQtyUnitId = strRecieveQtyUnitId;
	}
	public String[] getStrIssueQty() {
		return strIssueQty;
	}
	public void setStrIssueQty(String[] strIssueQty) {
		this.strIssueQty = strIssueQty;
	}
	public String[] getStrIssueQtyUnitId() {
		return strIssueQtyUnitId;
	}
	public void setStrIssueQtyUnitId(String[] strIssueQtyUnitId) {
		this.strIssueQtyUnitId = strIssueQtyUnitId;
	}
	public String[] getStrCondemenedQty() {
		return strCondemenedQty;
	}
	public void setStrCondemenedQty(String[] strCondemenedQty) {
		this.strCondemenedQty = strCondemenedQty;
	}
	public String[] getStrCondemenedQtyUnitId() {
		return strCondemenedQtyUnitId;
	}
	public void setStrCondemenedQtyUnitId(String[] strCondemenedQtyUnitId) {
		this.strCondemenedQtyUnitId = strCondemenedQtyUnitId;
	}
	public WebRowSet getConsultantWs() {
		return consultantWs;
	}
	public void setConsultantWs(WebRowSet consultantWs) {
		this.consultantWs = consultantWs;
	}
	public String getStrConsultantName() {
		return strConsultantName;
	}
	public void setStrConsultantName(String strConsultantName) {
		this.strConsultantName = strConsultantName;
	}
	public String getStrConsultantValues() {
		return strConsultantValues;
	}
	public void setStrConsultantValues(String strConsultantValues) {
		this.strConsultantValues = strConsultantValues;
	}
	public String getStrItemCategoryValue() {
		return strItemCategoryValue;
	}
	public void setStrItemCategoryValue(String strItemCategoryValue) {
		this.strItemCategoryValue = strItemCategoryValue;
	}
	public WebRowSet getItemCategoryWS() {
		return itemCategoryWS;
	}
	public void setItemCategoryWS(WebRowSet itemCategoryWS) {
		this.itemCategoryWS = itemCategoryWS;
	}
	public String getStrItemCategory() {
		return strItemCategory;
	}
	public void setStrItemCategory(String strItemCategory) {
		this.strItemCategory = strItemCategory;
	}
	public String[] getStrBatchSlNo() {
		return strBatchSlNo;
	}
	public void setStrBatchSlNo(String[] strBatchSlNo) {
		this.strBatchSlNo = strBatchSlNo;
	}
	public String getStrItemName() {
		return strItemName;
	}
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}
	public String getStrItemBrandName() {
		return strItemBrandName;
	}
	public void setStrItemBrandName(String strItemBrandName) {
		this.strItemBrandName = strItemBrandName;
	}
	public String getStrRecQty() {
		return strRecQty;
	}
	public void setStrRecQty(String strRecQty) {
		this.strRecQty = strRecQty;
	}
	public String getStrRecQtyUnitId() {
		return strRecQtyUnitId;
	}
	public void setStrRecQtyUnitId(String strRecQtyUnitId) {
		this.strRecQtyUnitId = strRecQtyUnitId;
	}
	public String getStrItemCategDtl() {
		return strItemCategDtl;
	}
	public void setStrItemCategDtl(String strItemCategDtl) {
		this.strItemCategDtl = strItemCategDtl;
	}
	public WebRowSet getCommitteTypeWS() {
		return committeTypeWS;
	}
	public void setCommitteTypeWS(WebRowSet committeTypeWS) {
		this.committeTypeWS = committeTypeWS;
	}
	public String getStrSampleRecieveNo() {
		return strSampleRecieveNo;
	}
	public void setStrSampleRecieveNo(String strSampleRecieveNo) {
		this.strSampleRecieveNo = strSampleRecieveNo;
	}
	public String getStrGatePassNo() {
		return strGatePassNo;
	}
	public void setStrGatePassNo(String strGatePassNo) {
		this.strGatePassNo = strGatePassNo;
	}
	
	public String getStrCommitteeType() {
		return strCommitteeType;
	}
	public void setStrCommitteeType(String strCommitteeType) {
		this.strCommitteeType = strCommitteeType;
	}
	public WebRowSet getCommitteMemberWS() {
		return committeMemberWS;
	}
	public void setCommitteMemberWS(WebRowSet committeMemberWS) {
		this.committeMemberWS = committeMemberWS;
	}
	public String getStrApprovalStatus() {
		return strApprovalStatus;
	}
	public void setStrApprovalStatus(String strApprovalStatus) {
		this.strApprovalStatus = strApprovalStatus;
	}
	public String[] getStrCommitteeMemberHidden() {
		return strCommitteeMemberHidden;
	}
	public void setStrCommitteeMemberHidden(String[] strCommitteeMemberHidden) {
		this.strCommitteeMemberHidden = strCommitteeMemberHidden;
	}
	public String[] getStrMemberRecommendation() {
		return strMemberRecommendation;
	}
	public void setStrMemberRecommendation(String[] strMemberRecommendation) {
		this.strMemberRecommendation = strMemberRecommendation;
	}
	public String[] getStrExpDate() {
		return strExpDate;
	}
	public void setStrExpDate(String[] strExpDate) {
		this.strExpDate = strExpDate;
	}
	public String getStrUnitVal() {
		return strUnitVal;
	}
	public void setStrUnitVal(String strUnitVal) {
		this.strUnitVal = strUnitVal;
	}
	public String getStrFileNo() {
		return strFileNo;
	}
	public void setStrFileNo(String strFileNo) {
		this.strFileNo = strFileNo;
	}
	public String getStrPageNo() {
		return strPageNo;
	}
	public void setStrPageNo(String strPageNo) {
		this.strPageNo = strPageNo;
	}
	public String getStrFileName() {
		return strFileName;
	}
	public void setStrFileName(String strFileName) {
		this.strFileName = strFileName;
	}
	/**
	 * @return the proposalNoWs
	 */
	public WebRowSet getProposalNoWs() {
		return proposalNoWs;
	}
	/**
	 * @param proposalNoWs the proposalNoWs to set
	 */
	public void setProposalNoWs(WebRowSet proposalNoWs) {
		this.proposalNoWs = proposalNoWs;
	}
	/**
	 * @return the strProposalNo
	 */
	public String getStrProposalNo() {
		return strProposalNo;
	}
	/**
	 * @param strProposalNo the strProposalNo to set
	 */
	public void setStrProposalNo(String strProposalNo) {
		this.strProposalNo = strProposalNo;
	}
	/**
	 * @return the itemWs
	 */
	public WebRowSet getItemWs() {
		return itemWs;
	}
	/**
	 * @param itemWs the itemWs to set
	 */
	public void setItemWs(WebRowSet itemWs) {
		this.itemWs = itemWs;
	}
	/**
	 * @return the strItemUnitId
	 */
	public String getStrItemUnitId() {
		return strItemUnitId;
	}
	/**
	 * @param strItemUnitId the strItemUnitId to set
	 */
	public void setStrItemUnitId(String strItemUnitId) {
		this.strItemUnitId = strItemUnitId;
	}
	/**
	 * @return the strSampleStatus
	 */
	public String getStrSampleStatus() {
		return strSampleStatus;
	}
	/**
	 * @param strSampleStatus the strSampleStatus to set
	 */
	public void setStrSampleStatus(String strSampleStatus) {
		this.strSampleStatus = strSampleStatus;
	}
	/**
	 * @return the strExpiryDate
	 */
	public String getStrExpiryDate() {
		return strExpiryDate;
	}
	/**
	 * @param strExpiryDate the strExpiryDate to set
	 */
	public void setStrExpiryDate(String strExpiryDate) {
		this.strExpiryDate = strExpiryDate;
	}
	/**
	 * @return the strIssuedQty
	 */
	public String getStrIssuedQty() {
		return strIssuedQty;
	}
	/**
	 * @param strIssuedQty the strIssuedQty to set
	 */
	public void setStrIssuedQty(String strIssuedQty) {
		this.strIssuedQty = strIssuedQty;
	}
	/**
	 * @return the strReturnQty
	 */
	public String getStrReturnQty() {
		return strReturnQty;
	}
	/**
	 * @param strReturnQty the strReturnQty to set
	 */
	public void setStrReturnQty(String strReturnQty) {
		this.strReturnQty = strReturnQty;
	}
	/**
	 * @return the strCondemnQty
	 */
	public String getStrCondemnQty() {
		return strCondemnQty;
	}
	/**
	 * @param strCondemnQty the strCondemnQty to set
	 */
	public void setStrCondemnQty(String strCondemnQty) {
		this.strCondemnQty = strCondemnQty;
	}
	/**
	 * @return the strSampleItemStatus
	 */
	public String getStrSampleItemStatus() {
		return strSampleItemStatus;
	}
	/**
	 * @param strSampleItemStatus the strSampleItemStatus to set
	 */
	public void setStrSampleItemStatus(String strSampleItemStatus) {
		this.strSampleItemStatus = strSampleItemStatus;
	}
	/**
	 * @return the strComRmksSlNo
	 */
	public String getStrComRmksSlNo() {
		return strComRmksSlNo;
	}
	/**
	 * @param strComRmksSlNo the strComRmksSlNo to set
	 */
	public void setStrComRmksSlNo(String strComRmksSlNo) {
		this.strComRmksSlNo = strComRmksSlNo;
	}
	
}
