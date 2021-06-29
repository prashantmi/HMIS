package billing.transactions;

import billing.BillConfigUtil;

public class PatWalletTransBO
{

	/**
	 * method used to retrieve Counter Id 
	 * @param voObj
	 */
	public void checkCounterStatus(PatWalletTransVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);

		PatWalletTransDAO.getCounterId(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "PatWalletTransBO.checkCounterStatus()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);

		}

	}

	public void preInitOffLineDetails(PatWalletTransVO voObj)
	{
		PatWalletTransDAO.getHospitalServiceList(voObj);
		PatWalletTransDAO.getBillingServiceList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "PatWalletTransBO.preInitOffLineDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}

	/**
	 * initialize required off-line details.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void initOffLineDetails(PatWalletTransVO voObj)
	{	PatWalletTransDAO dao=new PatWalletTransDAO();
		PatWalletTransDAO.getHospitalServiceList(voObj);
		PatWalletTransDAO.getBillingServiceList(voObj);
		PatWalletTransDAO.getPaymentModeList(voObj);
		PatWalletTransDAO.getSplWardList(voObj);
		PatWalletTransDAO.getRaisingDepartmentList(voObj);
		PatWalletTransDAO.getAdmEpisodeTreatmentCatDtl(voObj);
			//Commented By Amit Kumar Ateria .This Logic implemented in method getAdmEpisodeTreatmentCatDtl.Admitted Department/Last Visited Department
		//PatWalletTransDAO.getRaisingDepartment(voObj);
		//end
		PatWalletTransDAO.getEpisodeList(voObj);
		PatWalletTransDAO.getTreatmentCategoryList(voObj);		
		//PatWalletTransDAO.getWardList(voObj);
		PatWalletTransDAO.getWalletAccountDetails(voObj);
//		System.out.println("ACcessing status>>>>"+voObj.getStrAccountStatus());
		if(voObj.getStrAccountStatus().equals("4") || voObj.getStrAccountStatus().equals("2"))
	      {System.out.println("ACcessing DAO in 4!! and 2 !!!");
			PatWalletTransDAO.getWalletAccountSummary(voObj);
	      }
		dao.getWaivedBy(voObj);//For Waiver Details
		/*if (voObj.getStrOffLineWard() != null && !voObj.getStrOffLineWard().trim().equals(""))
		{
			String[] strWardDtls = voObj.getStrOffLineWard().replace("^", "#").split("#");
			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineClientType(strWardDtls[1]);
		}*/
		
		if (!voObj.getStrOffLineClientPatientHidden().equals(""))
		{   String temp[] = voObj.getStrOffLineClientPatientHidden().replace("^", "#").split("#");
			voObj.setStrOffLineClientPatientNo(temp[1]);
			voObj.setStrOffLineAccountNo(temp[2]);
			voObj.setStrOffLineWard(temp[4]);
			voObj.setStrOffLineSpecialWard(temp[5]);
			voObj.setStrOffLineTreatmentCategory(temp[6]);
			voObj.setStrOffLineRaisingDepartment(temp[7]);
			if (!voObj.getStrOffLineClientPatientNo().equals(""))
			{

				PatWalletTransDAO.getOffLineClientPatientDetailsWithClientPatNo(voObj);

			}
		}

		if (voObj.getStrOffLineClientPatientNo().equals(""))
		{
			// 1 - OPD , 2- IPD , 3 - Emergency & 19 - Account

			if ((voObj.getStrOffLineHospitalService().equals("1") || voObj.getStrOffLineHospitalService().equals("3")) || (voObj
					.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineBillingService().equals("19")))
			{

				PatWalletTransDAO.getOffLineClientPatientDetailsWithCrNo(voObj);
			}

		}

		if (voObj.getStrOffLineRequestType().equals("2"))
		{

			PatWalletTransDAO.getApprovedBy(voObj);
			PatWalletTransDAO.getRemarksList(voObj);

		}

		//PatWalletTransDAO.getOffLineGroup(voObj);

		if (voObj.getStrClerkDiscount().equals("1"))
		{

			PatWalletTransDAO.getOffLineDiscountApprovalList(voObj);
			PatWalletTransDAO.getOffLineDiscountRemarksList(voObj);

		}

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.initOffLineDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	
	/**
	 * retrieves Billing Service List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getBillServiceDetails(PatWalletTransVO voObj)
	{
		PatWalletTransDAO.getBillingServiceList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.getBillServiceDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}

	/**
	 * retrieves Episode List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getEpisodeDetails(PatWalletTransVO voObj)
	{
		PatWalletTransDAO.getEpisodeList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.getEpisodeDetails()-->" + voObj.getStrMsgString();

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
	public boolean isPartPaymentorAdvanceExist(PatWalletTransVO voObj)
	{

		if (voObj.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineRequestType().equals("1") && (voObj
				.getStrOffLineBillingService().equals("19") || voObj.getStrOffLineBillingService().equals("20")))
		{

			if (voObj.getStrMsgType().equals("1"))
			{

				String err = "PatWalletTransBO.isPartPaymentorAdvanceExist()-->" + voObj.getStrMsgString();

				voObj.setStrMsgString(err);
			}

			return true;
		} else
		{
			return false;
		}

	}


	/**
	 * Inserts Off-line Receipt Part Payment
	 * 
	 * Retrieves Module id, Group id and Unit id Details from Bill Configuration File
	 * 
	 * @param voObj
	 */
	public void insertOfflineReceiptPartPayment(PatWalletTransVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		PatWalletTransDAO.insertOfflinePartPayment(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.insertOfflineReceiptPartPayment()-->" + voObj.getStrMsgString();

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
	public void insertWalletReceiptAdvance(PatWalletTransVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);
		
		PatWalletTransDAO.chkExistingMobileNo(voObj);
		
		if(voObj.getStrAccountStatus().equals("1"))
		{
		if(Integer.parseInt(voObj.getStrCheckMobileNO())== 0)
        {
			PatWalletTransDAO.insertWalletAdvance(voObj);
        }
      
		}
		else if(voObj.getStrAccountStatus().equals("3"))
	        {
	        	PatWalletTransDAO.insertWalletPartPay(voObj);
	        }
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.insertWalletAdvance()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	public void CLOSEACCOUNT(PatWalletTransVO voObj)
	{

		
        	PatWalletTransDAO.CLOSEACCOUNT(voObj);
        
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.CLOSEACCOUNT()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	public void GetCrFromWalletNo(PatWalletTransVO voObj)
	{

		
        	PatWalletTransDAO.GetCrFromWalletNo(voObj);
        
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "PatWalletTransBO.GetCrFromWalletNo()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}



	
}
