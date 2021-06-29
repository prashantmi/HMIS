package billing.transactions;

import hisglobal.utility.HisUtil;

public class RefundApprovalTransBO {

	public void getBillDtls(RefundApprovalTransVO voObj) 
	{
		try 
		{
/*
			bcu = new BillConfigUtil();
			String s = bcu.getOpdRefundPenalty() + "^"
					+ bcu.getIpdRefundPenalty() + "^"
					+ bcu.getEmergencyRefundPenalty();
			
			voObj.setHspServ(s);
*/
			RefundApprovalTransDAO.getBillDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) 
			{
				voObj.setStrMsgString("RefundApprovalTransBO."+ "getBillDtls() --> " + voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("RefundApprovalTransBO.getBillDtls() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
	}

	
	public void setTariffValues(RefundApprovalTransVO voObj) 
	{
		String tempBsId[] = null;
		String valMode = "";

		try 
		{
			valMode = voObj.getStrValmode();
			tempBsId = valMode.replace("^", "#").split("#");
		
				if (tempBsId[4] == null || tempBsId[4].equals("0"))
					RefundApprovalTransDAO.getTariffDetails_NoAcc(voObj);
				else
					RefundApprovalTransDAO.getTariffDetails_Acc(voObj);
		
			if (voObj.getStrMsgType().equals("1"))
			{
				voObj.setStrMsgString("RefundApprovalTransBO."+ "setTariffValues() --> " + voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("RefundApprovalTransBO.setTariffValues() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	
	public void getUnitCombo(RefundApprovalTransVO vo) {

		String unitCmb = "";
		HisUtil hisUtil = null;

		try {
			hisUtil = new HisUtil("billing", "RefundApprovalTransBO");

			RefundApprovalTransDAO.getUnitCmb(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("RefundApprovalTransBO."
						+ "getUnitCombo() --> " + vo.getStrMsgString());
			} else {
				//System.out.println("in bo vo.getStrUnitCmb()size--"+vo.getStrUnitCmb().size());
				if(vo.getStrUnitCmb().size()==0)
				{
					unitCmb = hisUtil.getOptionValue(vo.getStrUnitCmb(),"0^SelectValue" 
							, "Select Value", false);
				}
				else{
				unitCmb = hisUtil.getOptionValue(vo.getStrUnitCmb(), vo
						.getStrUnit(), "", false);
				}
				vo.setStrUnitCmbVal(unitCmb);
			}
		} catch (Exception e) {
			vo.setStrMsgString("RefundApprovalTransBO.getUnitCombo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (hisUtil != null) {
				hisUtil = null;
			}
		}
	}

	public void getRsnRmk(RefundApprovalTransVO vo) {
		HisUtil hisutil = null;

		try {
			hisutil = new HisUtil("transaction", "RefundApprovalTransHLP");
			RefundApprovalTransDAO.getRefundRsn(vo);
			vo.setStrDisRsn(vo.getStrCancelReasonWs());
			
			if(vo.getStrRefundMode().equals("0")){
				RefundApprovalTransDAO.getRefundBy(vo);
			}
			
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("RefundApprovalTransBO."
						+ "getRsnRmk() --> " + vo.getStrMsgString());
			} else {
				
				
				String str2 = hisutil.getOptionValue(vo.getStrDisRsn(), "",
						"", true);
				
				if(vo.getStrRefundMode().equals("0")){
				vo.setStrDisBy(vo.getStrCancelledBy());
				String str1 = hisutil.getOptionValue(vo.getStrDisBy(), "0",
						"0^Select Value", true);
				vo.setStrRmk(str1);
				
				}
				
				vo.setStrRsn(str2);
			
			}
		} catch (Exception e) {
			vo.setStrMsgString("RefundApprovalTransBO.getRsnRmk() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (hisutil != null) {
				hisutil = null;
			}
		}

	}

	/**
	 * @param vo
	 */
	public void insertData(RefundApprovalTransVO vo) {

		try {
			RefundApprovalTransDAO.insertDataProc(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("RefundApprovalTransBO."
						+ "insertData()--> " + vo.getStrMsgString());
			}else{
				vo.setStrMsg("Selected Tariffs has been cancelled successfully");
			}

		} catch (Exception e) {

			vo.setStrMsgString("RefundApprovalTransBO.insertData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			
		}

	}

}
