package billing.transactions;

import hisglobal.utility.HisUtil;

/**
 * @author Anshul Jindal
 * 
 */
public class BillingCancellationTransBO {

	/**
	 * @param vo
	 */
	public void getBillDetails(BillingCancellationTransVO vo) {

		BillingCancellationTransDAO.getBillDetailsProc(vo);

		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("BillingCancellationTransBO."
					+ "getBillDetails() --> " + vo.getStrMsgString());
		}

	}
	
	public void getCrBillDetails(BillingCancellationTransVO vo) {

		BillingCancellationTransDAO.getCrBillDetailsProc(vo);

		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("BillingCancellationTransBO."
					+ "getCrBillDetails() --> " + vo.getStrMsgString());
		}

	}

	/**
	 * @param vo
	 */
	public void getCancelledByCmb(BillingCancellationTransVO vo) {

		HisUtil hisUtil = null;
		String cancelledByCmb = null;
		try {
			hisUtil = new HisUtil("billing", "BillingCancellationTransBO");

			BillingCancellationTransDAO.getCancelledByCmbProc(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("BillingCancellationTransBO."
						+ "getCancelledByCmb() --> " + vo.getStrMsgString());
			} else {
				cancelledByCmb = hisUtil.getOptionValue(vo
						.getStrCancelledByWs(), "0^SelectValue",
						"Select Value", false);

				vo.setStrCancelledBy(cancelledByCmb);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("BillingCancellationTransBO.getCancelledByCmb() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			hisUtil = null;
		}

	}

	/**
	 * @param vo
	 */
	public void getCancelReasonCmb(BillingCancellationTransVO vo) {

		HisUtil hisUtil = null;
		String cancelReasonCmb = null;
		try {

			hisUtil = new HisUtil("billing", "BillingCancellationTransBO");
			BillingCancellationTransDAO.getCancelReasonProc(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("BillingCancellationTransBO."
						+ "getCancelReasonCmb()--> " + vo.getStrMsgString());
			} else {

				cancelReasonCmb = hisUtil.getOptionValue(vo
						.getStrCancelReasonWs(), "", "", false);
				cancelReasonCmb = cancelReasonCmb + "<option value ='0'>"
						+ "others" + "</option>\n";
				// System.out.println("cmb2--"+cancelReasonCmb);
				vo.setStrCancelReason(cancelReasonCmb);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("BillingCancellationTransBO.getCancelReasonCmb() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {

			hisUtil = null;
		}

	}

	/**
	 * @param vo
	 */
	public void getPopUpInfo(BillingCancellationTransVO vo) {

		BillingCancellationTransDAO.getPopUpInfoProc(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("BillingCancellationTransBO."
					+ "getPopUpInfo()--> " + vo.getStrMsgString());
		}

	}

	/**
	 * @param vo
	 */
	public void insertData(BillingCancellationTransVO vo) {

		BillingCancellationTransDAO.insertDataProc(vo);

		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("BillingCancellationTransBO."
					+ "insertData()--> " + vo.getStrMsgString());
		}

	}
	
	public void setTariffValues(BillingCancellationTransVO voObj) 
	{
		String tempBsId[] = null;
		String valMode = "";

		try 
		{
			valMode = voObj.getStrChkValues();
			tempBsId = valMode.replace("^", "#").split("#");
					
				if (tempBsId[4] == null || tempBsId[4].equals("0")){
					voObj.setStrService("0");				
					BillingCancellationTransDAO.getTariffDetails_NoAcc(voObj);
				}
				else
				{
					voObj.setStrService("1");	
					BillingCancellationTransDAO.getTariffDetails_Acc(voObj);
				}
		
			if (voObj.getStrMsgType().equals("1"))
			{
				voObj.setStrMsgString("BillingCancellationTransBO."+ "setTariffValues() --> " + voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("BillingCancellationTransBO.setTariffValues() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

}
