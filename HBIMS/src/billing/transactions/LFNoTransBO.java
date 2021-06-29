package billing.transactions;

import billing.BillConfigUtil;

public class LFNoTransBO
{

	/**
	 * method used to retrieve Counter Id 
	 * @param voObj
	 */
	public void checkCounterStatus(LFNoTransVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);

		LFNoTransDAO.getCounterId(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "LFNoTransBO.checkCounterStatus()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);

		}

	}

	public void preInitOffLineDetails(LFNoTransVO voObj)
	{
		LFNoTransDAO.getHospitalServiceList(voObj);
		LFNoTransDAO.getBillingServiceList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{
			String err = "LFNoTransBO.preInitOffLineDetails()-->" + voObj.getStrMsgString();
			voObj.setStrMsgString(err);
		}
	}

	/**
	 * initialize required off-line details.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void initOffLineDetails(LFNoTransVO voObj)
	{	LFNoTransDAO dao=new LFNoTransDAO();
		LFNoTransDAO.getHospitalServiceList(voObj);
		LFNoTransDAO.getBillingServiceList(voObj);
		LFNoTransDAO.getPaymentModeList(voObj);
		LFNoTransDAO.getSplWardList(voObj);
		LFNoTransDAO.getRaisingDepartmentList(voObj);
		LFNoTransDAO.getAdmEpisodeTreatmentCatDtl(voObj);
			//Commented By Amit Kumar Ateria .This Logic implemented in method getAdmEpisodeTreatmentCatDtl.Admitted Department/Last Visited Department
		//LFNoTransDAO.getRaisingDepartment(voObj);
		//end
		LFNoTransDAO.getEpisodeList(voObj);
		LFNoTransDAO.getTreatmentCategoryList(voObj);		
		LFNoTransDAO.getWardList(voObj);
		LFNoTransDAO.getLFAccountDetails(voObj);
//		System.out.println("ACcessing status>>>>"+voObj.getStrAccountStatus());
		if(voObj.getStrAccountStatus().equals("4") || voObj.getStrAccountStatus().equals("2"))
	      {System.out.println("ACcessing DAO in 4!! and 2 !!!");
			LFNoTransDAO.getLFAccountSummary(voObj);
	      }
		dao.getWaivedBy(voObj);//For Waiver Details
		if (voObj.getStrOffLineWard() != null && !voObj.getStrOffLineWard().trim().equals(""))
		{
			String[] strWardDtls = voObj.getStrOffLineWard().replace("^", "#").split("#");
			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineClientType(strWardDtls[1]);
		}
		
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

				LFNoTransDAO.getOffLineClientPatientDetailsWithClientPatNo(voObj);

			}
		}

		if (voObj.getStrOffLineClientPatientNo().equals(""))
		{
			// 1 - OPD , 2- IPD , 3 - Emergency & 19 - Account

			if ((voObj.getStrOffLineHospitalService().equals("1") || voObj.getStrOffLineHospitalService().equals("3")) || (voObj
					.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineBillingService().equals("19")))
			{

				LFNoTransDAO.getOffLineClientPatientDetailsWithCrNo(voObj);
			}

		}

		if (voObj.getStrOffLineRequestType().equals("2"))
		{

			LFNoTransDAO.getApprovedBy(voObj);
			LFNoTransDAO.getRemarksList(voObj);

		}

		//LFNoTransDAO.getOffLineGroup(voObj);

		if (voObj.getStrClerkDiscount().equals("1"))
		{

			LFNoTransDAO.getOffLineDiscountApprovalList(voObj);
			LFNoTransDAO.getOffLineDiscountRemarksList(voObj);

		}

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.initOffLineDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	
	/**
	 * retrieves Billing Service List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getBillServiceDetails(LFNoTransVO voObj)
	{
		LFNoTransDAO.getBillingServiceList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getBillServiceDetails()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}

	/**
	 * retrieves Episode List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getEpisodeDetails(LFNoTransVO voObj)
	{
		LFNoTransDAO.getEpisodeList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getEpisodeDetails()-->" + voObj.getStrMsgString();

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
	public boolean isPartPaymentorAdvanceExist(LFNoTransVO voObj)
	{

		if (voObj.getStrOffLineHospitalService().equals("2") && voObj.getStrOffLineRequestType().equals("1") && (voObj
				.getStrOffLineBillingService().equals("19") || voObj.getStrOffLineBillingService().equals("20")))
		{

			if (voObj.getStrMsgType().equals("1"))
			{

				String err = "LFNoTransBO.isPartPaymentorAdvanceExist()-->" + voObj.getStrMsgString();

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
	public void getPartPaymentOrAdvanceDtls(LFNoTransVO voObj)
	{

		LFNoTransDAO.getApprovedBy(voObj);
		LFNoTransDAO.getRemarksList(voObj);
		LFNoTransDAO.getPartPaymentOrAdvanceAmount(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getPartPaymentOrAdvanceDtls()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	
	public void getOfflineRaisingDepartmentDtl(LFNoTransVO voObj)
	{

		LFNoTransDAO.getRaisingDepartmentList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getOfflineRaisingDepartmentDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineTreatmentCategoryDtl(LFNoTransVO voObj)
	{

		LFNoTransDAO.getTreatmentCategoryList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getOfflineTreatmentCategoryDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineWardDtl(LFNoTransVO voObj)
	{

		LFNoTransDAO.getWardList(voObj);

		if (voObj.getStrOffLineWard() != null && !voObj.getStrOffLineWard().trim().equals(""))
		{
			String[] strWardDtls = voObj.getStrOffLineWard().replace("^", "#").split("#");
			voObj.setStrOffLineWard(strWardDtls[0]);
			voObj.setStrOffLineClientType(strWardDtls[1]);
		}
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getOfflineWardDtl()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}

	public void getOfflineSpecialWardDtl(LFNoTransVO voObj)
	{

		LFNoTransDAO.getSplWardList(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.getOfflineWardDtl()-->" + voObj.getStrMsgString();

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
	public void insertOfflineReceiptPartPayment(LFNoTransVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);

		LFNoTransDAO.insertOfflinePartPayment(voObj);

		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.insertOfflineReceiptPartPayment()-->" + voObj.getStrMsgString();

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
	public void insertOfflineReceiptAdvance(LFNoTransVO voObj)
	{

		voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
		voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
		voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);
		if(voObj.getStrAccountStatus().equals("1"))
        {
			LFNoTransDAO.insertLFAdvance(voObj);
        }
        else
        {
        	LFNoTransDAO.insertLFPartPay(voObj);
        }
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.insertLFAdvance()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	public void CLOSEACCOUNT(LFNoTransVO voObj)
	{

		
        	LFNoTransDAO.CLOSEACCOUNT(voObj);
        
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.CLOSEACCOUNT()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}
	
	public void GetCrFromLFNo(LFNoTransVO voObj)
	{

		
        	LFNoTransDAO.GetCrFromLFNo(voObj);
        
		if (voObj.getStrMsgType().equals("1"))
		{

			String err = "LFNoTransBO.GetCrFromLFNo()-->" + voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}

	}



	
}
