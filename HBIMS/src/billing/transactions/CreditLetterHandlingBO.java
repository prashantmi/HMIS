package billing.transactions;

import billing.BillConfigUtil;

public class CreditLetterHandlingBO {

	/**
	 * method used to retrieve Counter Id 
	 * @param voObj
	 */
	public void checkCounterStatus(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);

		CreditLetterHandlingDao.getCounterId(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.checkCounterStatus()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);

		}

	}

	public void preInitOffLineDetails(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getHospitalServiceList(voObj);
		CreditLetterHandlingDao.getBillingServiceList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.preInitOffLineDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}

	/**
	 * initialize required off-line details.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void initOffLineDetails(CreditHandlingLetterVO voObj)
	{

		
		CreditLetterHandlingDao dao=new CreditLetterHandlingDao();
		CreditLetterHandlingDao.getHospitalServiceList(voObj);
		//CreditLetterHandlingDao.getBillingServiceList(voObj);
		CreditLetterHandlingDao.getPaymentModeList(voObj);
		CreditLetterHandlingDao.getRaisingDepartmentList(voObj);
		CreditLetterHandlingDao.getAdmEpisodeTreatmentCatDtl(voObj);//GETS ADMITTED DEPT,CAT,EPISODE,VISIT IF IPD OTHERWISE LAST VISITED DEPT,TREAT CAT IF PRESENT OTHERWISE PAT CAT, EPISODE,VISIT
		//Commented By Amit Kumar Ateria .This Logic implemented in method getAdmEpisodeTreatmentCatDtl.Admitted Department/Last Visited Department
		//CreditLetterHandlingDao.getRaisingDepartment(voObj);
		//end
		CreditLetterHandlingDao.getEpisodeListCombo(voObj);//MAY BE COMMENTED BECAUSE ONLY ONE EPSIODE IS COMING
		CreditLetterHandlingDao.getTreatmentCategoryList(voObj);	
		CreditLetterHandlingDao.getWardList(voObj);
		CreditLetterHandlingDao.getSplWardList(voObj);
		
		CreditLetterHandlingDao.getClientNameList(voObj);
		
		
		dao.getWaivedBy(voObj);//For Waiver Details

		if (voObj.getStrOffLineWard() != null && !voObj.getStrOffLineWard().trim().equals(""))
		{
			String[] strWardDtls = voObj.getStrOffLineWard().replace("^", "#").split("#");
			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineClientType(strWardDtls[1]);
		}

		CreditLetterHandlingDao.getOffLineClientPatientNumber(voObj);

		if (!voObj.getStrOffLineClientPatientHidden().equals(""))
		{

			String temp[] = voObj.getStrOffLineClientPatientHidden().replace("^", "#").split("#");

			voObj.setStrOffLineClientPatientNo(temp[1]);
			voObj.setStrOffLineAccountNo(temp[2]);
			voObj.setStrOffLineWard(temp[4]);
			//voObj.setStrOffLineSpecialWard(temp[5]);  commented by ajay to get ward name after admission and before acceptance
			voObj.setStrOffLineTreatmentCategory(temp[6]);
			voObj.setStrOffLineRaisingDepartment(temp[7]);

			if (!voObj.getStrOffLineClientPatientNo().equals(""))
			{

				CreditLetterHandlingDao.getOffLineClientPatientDetailsWithClientPatNo(voObj);

			}

		}

		if (voObj.getStrOffLineClientPatientNo().equals(""))
		{
			// 1 - OPD , 2- IPD , 3 - Emergency & 19 - Account

			if ((voObj.getStrOffLineHospitalService().equals("1") || voObj.getStrOffLineHospitalService().equals("3")) || (voObj
					.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineBillingService().equals("19")))
			{

				CreditLetterHandlingDao.getOffLineClientPatientDetailsWithCrNo(voObj);
			}

		}

		if (voObj.getStrOffLineRequestType().equals("2"))
		{

			CreditLetterHandlingDao.getApprovedBy(voObj);
			CreditLetterHandlingDao.getRemarksList(voObj);

		}

		//CreditLetterHandlingDao.getOffLineGroup(voObj);

		if (voObj.getStrClerkDiscount().equals("1"))
		{
			CreditLetterHandlingDao.getOffLineDiscountApprovalList(voObj);
			CreditLetterHandlingDao.getOffLineDiscountRemarksList(voObj);
		}
		/*if(voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#").length>=2 && (voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#")[1].equals("3") || voObj.getStrOffLineTreatmentCategory().replace("^","#").split("#")[1].equals("4")))
			CreditLetterHandlingDao.getCreditLettersList(voObj);*/
		CreditLetterHandlingDao.getWalletDetails(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.initOffLineDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	/**
	 * initialize required off-line details.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void initAdvanceOffLineDetails(CreditHandlingLetterVO voObj)
	{

		
		CreditLetterHandlingDao dao=new CreditLetterHandlingDao();
		CreditLetterHandlingDao.getHospitalServiceListBasedOnVisit(voObj);
		CreditLetterHandlingDao.getBillingServiceListExcludingIpdAdvance(voObj);
		CreditLetterHandlingDao.getPaymentModeList(voObj);
		CreditLetterHandlingDao.getSplWardList(voObj);
		CreditLetterHandlingDao.getRaisingDepartmentList(voObj);
		CreditLetterHandlingDao.getAdmEpisodeTreatmentCatDtl(voObj);//GETS ADMITTED DEPT,CAT,EPISODE,VISIT IF IPD OTHERWISE LAST VISITED DEPT,TREAT CAT IF PRESENT OTHERWISE PAT CAT, EPISODE,VISIT
		//Commented By Amit Kumar Ateria .This Logic implemented in method getAdmEpisodeTreatmentCatDtl.Admitted Department/Last Visited Department
		//CreditLetterHandlingDao.getRaisingDepartment(voObj);
		//end
		CreditLetterHandlingDao.getEpisodeListCombo(voObj);//MAY BE COMMENTED BECAUSE ONLY ONE EPSIODE IS COMING
		CreditLetterHandlingDao.getTreatmentCategoryList(voObj);	
		CreditLetterHandlingDao.getWardList(voObj);
		

		CreditLetterHandlingDao.getClientNameList(voObj);
		
		
		dao.getWaivedBy(voObj);//For Waiver Details

		if (voObj.getStrOffLineWard() != null && !voObj.getStrOffLineWard().trim().equals(""))
		{
			String[] strWardDtls = voObj.getStrOffLineWard().replace("^", "#").split("#");
			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineClientType(strWardDtls[1]);
		}

		CreditLetterHandlingDao.getOffLineClientPatientNumber(voObj);

		if (!voObj.getStrOffLineClientPatientHidden().equals(""))
		{

			String temp[] = voObj.getStrOffLineClientPatientHidden().replace("^", "#").split("#");

			voObj.setStrOffLineClientPatientNo(temp[1]);
			voObj.setStrOffLineAccountNo(temp[2]);
			voObj.setStrOffLineWard(temp[4]);
			voObj.setStrOffLineSpecialWard(temp[5]);
			voObj.setStrOffLineTreatmentCategory(temp[6]);
			voObj.setStrOffLineRaisingDepartment(temp[7]);

			if (!voObj.getStrOffLineClientPatientNo().equals(""))
			{

				CreditLetterHandlingDao.getOffLineClientPatientDetailsWithClientPatNo(voObj);

			}

		}

		if (voObj.getStrOffLineClientPatientNo().equals(""))
		{
			// 1 - OPD , 2- IPD , 3 - Emergency & 19 - Account

			if ((voObj.getStrOffLineHospitalService().equals("1") || voObj.getStrOffLineHospitalService().equals("3")) || (voObj
					.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineBillingService().equals("19")))
			{

				CreditLetterHandlingDao.getOffLineClientPatientDetailsWithCrNo(voObj);
			}

		}

		if (voObj.getStrOffLineRequestType().equals("2"))
		{

			CreditLetterHandlingDao.getApprovedBy(voObj);
			CreditLetterHandlingDao.getRemarksList(voObj);

		}

		//CreditLetterHandlingDao.getOffLineGroup(voObj);

		if (voObj.getStrClerkDiscount().equals("1"))
		{

			CreditLetterHandlingDao.getOffLineDiscountApprovalList(voObj);
			CreditLetterHandlingDao.getOffLineDiscountRemarksList(voObj);

		}
		//CreditLetterHandlingDao.getCreditLettersList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.initOffLineDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	

	/**
	 * retrieves required off-line details.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getOffLineClientDetails(CreditHandlingLetterVO voObj)
	{

		/*
		 * CreditLetterHandlingDao.getOffLineAccountNo(voObj);
		 * 
		 * 
		 * if (!voObj.getStrOffLineAccountNo().equals("")) {
		 * 
		 * System.out.println(" Acc No : "+voObj.getStrOffLineAccountNo());
		 */

		CreditLetterHandlingDao.getOffLineClientPatientNumber(voObj);

		if (!voObj.getStrOffLineClientPatientHidden().equals(""))
		{

			String temp[] = voObj.getStrOffLineClientPatientHidden().replace("^", "#").split("#");

			voObj.setStrOffLineClientPatientNo(temp[1]);
			voObj.setStrOffLineAccountNo(temp[2]);
			voObj.setStrOffLineWard(temp[4]);
			voObj.setStrOffLineSpecialWard(temp[5]);
			voObj.setStrOffLineTreatmentCategory(temp[6]);
			voObj.setStrOffLineRaisingDepartment(temp[7]);

			if (!voObj.getStrOffLineClientPatientNo().equals(""))
			{

				CreditLetterHandlingDao.getOffLineClientPatientDetailsWithClientPatNo(voObj);

			}

		}
		// }

		if (voObj.getStrOffLineClientPatientNo().equals(""))
		{
			// 1 - OPD , 2- IPD , 3 - Emergency & 19 - Account
			if ((voObj.getStrOffLineHospitalService().equals("1") || voObj.getStrOffLineHospitalService().equals("3")) || (voObj
					.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineBillingService().equals("19")))
			{

				CreditLetterHandlingDao.getOffLineClientPatientDetailsWithCrNo(voObj);
			}

		}

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOffLineClientDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * retrieves Billing Service List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getBillServiceDetails(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getBillingServiceList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getBillServiceDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}
	

	/**
	 * retrieves Episode List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getEpisodeDetails(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getEpisodeList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getEpisodeDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}

	/**
	 * checks Whether PartPayment or Advance Part Exist. If Hospital Service is
	 * 2 (IPD) and Request Type is 1 (Receipt) and (Billing Service is 19 -
	 * Advance or 20 - Part Payment) then Part Payment or Advance Exist.
	 * 
	 * @param voObj -
	 *            Cash Collection Transaction Value Object
	 * @return true - if Part Payment or Advance Exist.<br>
	 *         false - if Part Payment or Advance Does Not Exist.
	 */
	public boolean isPartPaymentorAdvanceExist(CreditHandlingLetterVO voObj)
	{

		if (voObj.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineRequestType().equals("1") && (voObj
				.getStrOffLineBillingService().equals("19") || voObj.getStrOffLineBillingService().equals("20")))
		{

			if (voObj.getStrMsgType().equals("1"))
			{

				String err = "CreditLetterHandlingBO.isPartPaymentorAdvanceExist()-->" + voObj.getStrMsgString();

				voObj.setStrMsgString(err);
			}

			return true;
		} else
		{
			return false;
		}

	}

	/**
	 * retrieves Part Payment or Account Details.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getPartPaymentOrAdvanceDtls(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getApprovedBy(voObj);
		CreditLetterHandlingDao.getRemarksList(voObj);
		CreditLetterHandlingDao.getPartPaymentOrAdvanceAmount(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getPartPaymentOrAdvanceDtls()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * gets Admission Cancellation Details
	 * 
	 * @param voObj
	 */
	public void getAdmissionCancellationDtl(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getOffLineRefundAdvancePatAccDtls(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getAdmissionCancellationDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * retrieves Billing Service List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getGroupDetails(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getOffLineGroup(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getGroupDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * retrieves Tariff List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getTariffDetails(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getOffLineTariffList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.getTariffDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}
	
	
	public void getTariffCodeDetailsDrug(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getOffLineTariffListByCodeD(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.getTariffDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}

	/**
	 * retrieves Tariff List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getTariffCodeDetails(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.getOffLineTariffListByCode(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.getTariffCodeDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}

	/**
	 * retrieves Tariff Unit List.
	 * 
	 * @param voObj -
	 *            Value Object.
	 */
	public void getOffLineTariffUnitDetails(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getOffLineTariffUnitList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOffLineTariffUnitDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * retrieves Bill Details
	 * 
	 * @param voObj -
	 *            Value Object.
	 */
	public void getOffLineBillDetails(CreditHandlingLetterVO voObj)
	{

		BillConfigUtil bcu = new BillConfigUtil(voObj.getStrHospitalCode());

		if (voObj.getStrOffLineHospitalService().equals("1"))
		{
			voObj.setStrOffLineRefundPenalty(bcu.getOpdRefundPenalty());

		} else if (voObj.getStrOffLineHospitalService().equals("2"))
		{
			voObj.setStrOffLineRefundPenalty(bcu.getIpdRefundPenalty());
		} else
		{
			voObj.setStrOffLineRefundPenalty(bcu.getEmergencyRefundPenalty());
		}

		CreditLetterHandlingDao.getBillDetails(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOffLineBillDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOffLinePartPayBillDetails(CreditHandlingLetterVO voObj)
	{

		BillConfigUtil bcu = new BillConfigUtil(voObj.getStrHospitalCode());

		if (voObj.getStrOffLineHospitalService().equals("1"))
		{
			voObj.setStrOffLineRefundPenalty(bcu.getOpdRefundPenalty());

		} else if (voObj.getStrOffLineHospitalService().equals("2"))
		{
			voObj.setStrOffLineRefundPenalty(bcu.getIpdRefundPenalty());
		} else
		{
			voObj.setStrOffLineRefundPenalty(bcu.getEmergencyRefundPenalty());
		}

		CreditLetterHandlingDao.getPartPayBillDetails(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOffLineBillDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * retrieves Bill Tariff Details
	 * 
	 * @param voObj -
	 *            Value Object.
	 */
	public void getOffLineBillTariffDetails(CreditHandlingLetterVO voObj)
	{
		if (voObj.getStrOffLineHospitalService().equals("2"))//IPD
		{
			CreditLetterHandlingDao.getTariffDetails_Acc(voObj);
		} 
		else
		{
			CreditLetterHandlingDao.getTariffDetails_NoAcc(voObj);
		}
		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.getOffLineBillTariffDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}

	/**
	 * gets Bill Listing Details
	 * 
	 * @param voObj
	 */
	public void getBillListingDtl(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getBillListingDtl_from_OutBound(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.initOffLineDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineRaisingDepartmentDtl(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getRaisingDepartmentList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOfflineRaisingDepartmentDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineTreatmentCategoryDtl(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getTreatmentCategoryList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOfflineTreatmentCategoryDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineWardDtl(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getWardList(voObj);

		if (voObj.getStrOffLineWard() != null && !voObj.getStrOffLineWard().trim().equals(""))
		{
			String[] strWardDtls = voObj.getStrOffLineWard().replace("^", "#").split("#");
			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineClientType(strWardDtls[1]);
		}
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOfflineWardDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineSpecialWardDtl(CreditHandlingLetterVO voObj)
	{

		CreditLetterHandlingDao.getSplWardList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getOfflineWardDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Inserts Off-line Receipt Part Payment
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineReceiptPartPayment(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CreditLetterHandlingDao.insertOfflinePartPayment(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.insertOfflineReceiptPartPayment()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Inserts Off-line Receipt Advance
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineReceiptAdvance(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);
		
		CreditLetterHandlingDao.getpataccno(voObj);
		//System.out.println("voObj.setStrOffLineAccountNo"+voObj.getStrOffLineAccountNo());
		if (!voObj.getStrOffLineAccountNo().equals("0"))
		{
			voObj.setStrMsgType("1");
			voObj.setStrMsgString("Account Already Opened/Advance Collected. Multiple Accounts cannot be opened");
		}
		else
			CreditLetterHandlingDao.insertOfflineAdvance(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.insertOfflineReceiptAdvance()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Inserts Off-line Receipt Service
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineReceiptService(CreditHandlingLetterVO voObj)
	{
		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		if (voObj.getStrIsAdvanceRequired().equals("0") && voObj.getStrOffLineHospitalService().equals("2") && 
			voObj.getStrOffLineBillingService().equals("10") && (voObj.getStrOffLineAccountNo() == null || voObj.getStrOffLineAccountNo().equals("0")))
		{
			voObj = CreditLetterHandlingDao.openZeroBalanceAccount(voObj);
		}
		CreditLetterHandlingDao.insertOfflineReceiptServices(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.insertOfflineReceiptService()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Inserts Off-line Refund Services
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineRefundService(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CreditLetterHandlingDao.insertOfflineRefundServices(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.insertOfflineRefundService()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Inserts Off-line Refund Admission Cancellation
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineRefundAdmissionCancellation(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CreditLetterHandlingDao.insertOfflineRefundAdmissionCancellation(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.insertOfflineRefundAdmissionCancellation()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	/**
	 * Inserts Off-line Refund Admission Cancellation
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineRefundPartPayment(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CreditLetterHandlingDao.insertOfflineRefundPartPayment(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.insertOfflineRefundAdmissionCancellation()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	 /* Inserts Off-line Estimation
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineEstimation(CreditHandlingLetterVO voObj)
	{
		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		if (voObj.getStrIsAdvanceRequired().equals("0") && voObj.getStrOffLineHospitalService().equals("2") && 
			voObj.getStrOffLineBillingService().equals("10") && (voObj.getStrOffLineAccountNo() == null || voObj.getStrOffLineAccountNo().equals("0")))
		{
			voObj = CreditLetterHandlingDao.openZeroBalanceAccount(voObj);
		}
		CreditLetterHandlingDao.insertOfflineEstimation(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.insertOfflineReceiptService()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}

	}
	public void getBillingPackageNames(CreditHandlingLetterVO VO) {
		CreditLetterHandlingDao.getBillingPackageNames(VO);
		if (VO.getStrMsgType().equals("1")) 
			VO.setStrMsgString(" CreditLetterHandlingBO.getBillingPackageNames() --> "
					+ VO.getStrMsgString());
	}
	public void insertOfflineReceiptPackage(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CreditLetterHandlingDao.insertOfflinePackage(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.insertOfflineReceiptPartPayment()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	public void getCreditLetterList(CreditHandlingLetterVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		CreditLetterHandlingDao.getCreditLettersList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "CreditLetterHandlingBO.getCreditLetterList()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	
	public void saveCreditLetter(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.saveCreditLetter(voObj);
		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.saveCreditLetter()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}
	public void removeCreditLetter(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.removeCreditLetter(voObj);
		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.removeCreditLetter()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}
	public void expireCreditLetter(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.expireCreditLetter(voObj);
		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.expireCreditLetter()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}
	public void modifyCreditLetter(CreditHandlingLetterVO voObj)
	{
		CreditLetterHandlingDao.modifyCreditLetter(voObj);
		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "CreditLetterHandlingBO.modifyCreditLetter()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}
}