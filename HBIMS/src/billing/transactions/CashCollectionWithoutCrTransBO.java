package billing.transactions;

import billing.BillConfigUtil;

public class CashCollectionWithoutCrTransBO {

	/**
	 * method used to retrieve Counter Id 
	 * @param voObj
	 */
	public void checkCounterStatus(CashCollectionWithoutCrTransVO voObj){
		
		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		
		CashCollectionWithoutCrTransDAO.getCounterId(voObj);
				
		if (voObj.getStrMsgType().equals("1")) {
			String err = "CashCollectionTransBO.checkCounterStatus()-->"
					+ voObj.getStrMsgString();
			voObj.setStrMsgString(err);

		}
		
	}
	

	public void getWithoutCrNoOnlineReqTariffDtls(CashCollectionWithoutCrTransVO voObj) {

		CashCollectionWithoutCrTransDAO.getOnlineTariffDetails(voObj);

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
	public void getGroupDetails(CashCollectionWithoutCrTransVO voObj) {
		CashCollectionWithoutCrTransDAO.getOffLineGroup(voObj);

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
	public void getTariffDetails(CashCollectionWithoutCrTransVO voObj) {
		CashCollectionWithoutCrTransDAO.getOffLineTariffList(voObj);

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
	public void getTariffCodeDetails(CashCollectionWithoutCrTransVO voObj) {
		CashCollectionWithoutCrTransDAO.getOffLineTariffListByCode(voObj);

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
	public void getOffLineTariffUnitDetails(CashCollectionWithoutCrTransVO voObj) {

		CashCollectionWithoutCrTransDAO.getOffLineTariffUnitList(voObj);

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
	public void initwithoutCrNoDetails(CashCollectionWithoutCrTransVO voObj) {

		CashCollectionWithoutCrTransDAO.getBillingServiceList(voObj);
		CashCollectionWithoutCrTransDAO.getRaisingDepartmentList(voObj);
		CashCollectionWithoutCrTransDAO.getOffLineGroup(voObj);
	 
		CashCollectionWithoutCrTransDAO.getApprovedBy(voObj);
		CashCollectionWithoutCrTransDAO.getRemarksList(voObj);
		CashCollectionWithoutCrTransDAO.getRequestNoList(voObj);
		CashCollectionWithoutCrTransDAO.getClientNameList(voObj);
		
		CashCollectionWithoutCrTransDAO.getPaymentModeList(voObj);	
		CashCollectionWithoutCrTransDAO.getGenderList(voObj);

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
	public void initWithoutCrNoBillDetails(CashCollectionWithoutCrTransVO voObj) {

		CashCollectionWithoutCrTransDAO.getWithoutCrNoBillTariffDetails(voObj);
		CashCollectionWithoutCrTransDAO.getGenderList(voObj);
		CashCollectionWithoutCrTransDAO.getGuarantorDetails(voObj);

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
	public void getPreviousCrNoDtls(CashCollectionWithoutCrTransVO voObj) {

		CashCollectionWithoutCrTransDAO.getPreviousCrNoDetails(voObj);

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
	public void getBillListingDtl(CashCollectionWithoutCrTransVO voObj) {

		CashCollectionWithoutCrTransDAO.getBillListingDtl_from_OutBound(voObj);

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
	public void insertWithoutCrNoReceiptServices(CashCollectionWithoutCrTransVO voObj) {

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CashCollectionWithoutCrTransDAO.insertWithoutCrNoReceiptServices(voObj);

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
	public void insertWithoutCrNoRefundServices(CashCollectionWithoutCrTransVO voObj) {

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);
		voObj.setStrOffLineTreatmentCategory(BillConfigUtil.NORMAL_CATEGORY);
		voObj.setStrOffLineHospitalService(BillConfigUtil.DEFAULT_HOSPITAL_SERVICE);
			
		voObj.setStrPreviousCrNo(voObj.getStrGuarantorHiddenVal().replace("^", "#").split("#")[7] );
		
		CashCollectionWithoutCrTransDAO.insertWithoutCrNoRefundServices(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.insertWithoutCrNoReceiptServices()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	

	public void insertOnlineWithoutCrNoReceiptServices(CashCollectionWithoutCrTransVO voObj) {

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CashCollectionWithoutCrTransDAO.insertOnlineWithoutCrNoReceiptServices(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "CashCollectionTransBO.insertOnlineWithoutCrNoReceiptServices()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	
	
}
