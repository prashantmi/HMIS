package billing.transactions;

import billing.BillConfigUtil;

public class EstEnquiryTransBO {

	/**
	 * method used to retrieve Counter Id 
	 * @param voObj
	 */
	public void checkCounterStatus(EstEnquiryTransVO voObj){
		
		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		
		EstEnquiryTransDAO.getCounterId(voObj);
				
		if (voObj.getStrMsgType().equals("1")) {
			String err = "CashCollectionTransBO.checkCounterStatus()-->"
					+ voObj.getStrMsgString();
			voObj.setStrMsgString(err);

		}
		
	}
	

	public void getWithoutCrNoOnlineReqTariffDtls(EstEnquiryTransVO voObj) {

		EstEnquiryTransDAO.getOnlineTariffDetails(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getWithoutCrNoOnlineReqTariffDtls()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	

	/**
	 * retrieves Billing Service List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getGroupDetails(EstEnquiryTransVO voObj) {
		EstEnquiryTransDAO.getOffLineGroup(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getGroupDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * retrieves Tariff List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getTariffDetails(EstEnquiryTransVO voObj) {
		EstEnquiryTransDAO.getOffLineTariffList(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getTariffDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}
	
	

	/**
	 * retrieves Tariff List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getTariffCodeDetails(EstEnquiryTransVO voObj) {
		EstEnquiryTransDAO.getOffLineTariffListByCode(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getTariffCodeDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}

	/**
	 * retrieves Tariff Unit List.
	 * 
	 * @param voObj -
	 *            Value Object.
	 */
	public void getOffLineTariffUnitDetails(EstEnquiryTransVO voObj) {

		EstEnquiryTransDAO.getOffLineTariffUnitList(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getOffLineTariffUnitDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
 
	/**
	 * Initialize Without Cr. No. Details
	 * 
	 * @param voObj
	 */
	public void initwithoutCrNoDetails(EstEnquiryTransVO voObj) {

		EstEnquiryTransDAO.getBillingServiceList(voObj);
		EstEnquiryTransDAO.getRaisingDepartmentList(voObj);
		EstEnquiryTransDAO.getOffLineGroup(voObj);
	 
		EstEnquiryTransDAO.getApprovedBy(voObj);
		EstEnquiryTransDAO.getRemarksList(voObj);
		EstEnquiryTransDAO.getRequestNoList(voObj);
		EstEnquiryTransDAO.getClientNameList(voObj);
		
		EstEnquiryTransDAO.getPaymentModeList(voObj);	
		EstEnquiryTransDAO.getGenderList(voObj);
		EstEnquiryTransDAO.getServiceTypeList(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.initOffLineDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Initialize Without Cr. No. Bill Details
	 * 
	 * @param voObj
	 */
	public void initWithoutCrNoBillDetails(EstEnquiryTransVO voObj) {

		EstEnquiryTransDAO.getWithoutCrNoBillTariffDetails(voObj);
		EstEnquiryTransDAO.getGenderList(voObj);
		EstEnquiryTransDAO.getGuarantorDetails(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.initWithoutCrNoBillDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}

	/**
	 * gets previous Cr No. Details
	 * 
	 * @param voObj
	 */
	public void getPreviousCrNoDtls(EstEnquiryTransVO voObj) {

		EstEnquiryTransDAO.getPreviousCrNoDetails(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getPreviousCrNoDtls()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	/**
	 * gets Bill Listing Details
	 * 
	 * @param voObj
	 */
	public void getBillListingDtl(EstEnquiryTransVO voObj) {

		EstEnquiryTransDAO.getBillListingDtl_from_OutBound(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.initOffLineDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
 
	
	/**
	 * Insert's Without Cr. No. Receipt Services.
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertWithoutCrNoReceiptServices(EstEnquiryTransVO voObj) {

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		EstEnquiryTransDAO.insertWithoutCrNoReceiptServices(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.insertWithoutCrNoReceiptServices()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	/**
	 * Insert's Without Cr. No. Refund Services.
	 * Retrieves Module id, Group id, Unit id, Category Code and Hospital Service Details from Bill Configuration File
	 * @param voObj
	 */
	public void insertWithoutCrNoRefundServices(EstEnquiryTransVO voObj) {

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);
		voObj.setStrOffLineTreatmentCategory(BillConfigUtil.NORMAL_CATEGORY);
		voObj.setStrOffLineHospitalService(BillConfigUtil.DEFAULT_HOSPITAL_SERVICE);
			
		voObj.setStrPreviousCrNo(voObj.getStrGuarantorHiddenVal().replace("^", "#").split("#")[7] );
		
		EstEnquiryTransDAO.insertWithoutCrNoRefundServices(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.insertWithoutCrNoReceiptServices()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	

	public void insertOnlineWithoutCrNoReceiptServices(EstEnquiryTransVO voObj) {

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		EstEnquiryTransDAO.insertOnlineWithoutCrNoReceiptServices(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.insertOnlineWithoutCrNoReceiptServices()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	
	public void fetchServiceList(EstEnquiryTransVO voObj) {
		EstEnquiryTransDAO.fetchServiceList(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.getGroupDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	
}
