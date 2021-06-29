package billing.transactions;

public class BillReconcileTransBO {

	public void getBillDetails(BillReconcileTransVO voObj) {

		try {
			
			BillReconcileTransDAO.getBillDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) {
			voObj.setStrMsgString("BillReconcileTransBO.setReqValues() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		// return web;
	}

	/**
	 * This method will be used to get tariff details based on Bill no
	 * 
	 * @param voObj
	 */
	public void getTariffOrFinalSettlementDetails(BillReconcileTransVO voObj) {

		String tempBsId[] = null;
		tempBsId = (voObj.getStrValmode()).replace("^", "#").split("#");

		if (tempBsId[4] == null)
			tempBsId[4] = "";

		try {
			// opd /ipd/emeregency service
			if (Integer.parseInt(tempBsId[3]) < 13) {
				// account does not exist
				if (tempBsId[4].equals("") || tempBsId[4].equals("0"))
					BillReconcileTransDAO.getTariffDetails_NoAcc(voObj);
				else
					BillReconcileTransDAO.getTariffDetails_Acc(voObj);
			} else if (Integer.parseInt(tempBsId[3]) == 21) {
				BillReconcileTransDAO.getFSetLst(voObj);
			}
			/*
			 * else { if (tempBsId[4] == null || tempBsId[4].equals("0"))
			 * BillReconcileTransDAO.getPkgLst_NoAcc(voObj); else
			 * BillReconcileTransDAO.getPkgLst_Acc(voObj); }
			 */

		} catch (Exception e) {
			voObj.setStrMsgString("BillReconcileTransBO.setTariffValues() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * To get group details in Final Settlement (consolidated) section
	 * 
	 * @param voObj
	 */
	public void getFinalSettlermentDtls(BillReconcileTransVO voObj) {

		try {

			BillReconcileTransDAO.getFinalSettlementDtls(voObj);

		} catch (Exception e) {
			voObj.setStrMsgString("BillReconcileTransBO.getFinalSettlermentDtls() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	public void initReconcilation(BillReconcileTransVO voObj) {

		try {

			BillReconcileTransDAO.getApprovalList(voObj);
			BillReconcileTransDAO.getRemarksList(voObj);

		} catch (Exception e) {
			voObj.setStrMsgString("BillReconcileTransBO.initReconcilation() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/*
	 * public void unit(BillReconcileTransVO vo,String unit_id) { try {
	 * 
	 * BillReconcileTransDAO.unit(vo,unit_id); } catch(Exception e) { } }
	 */

	/**
	 * retrieves initialize Group, Discount Approval By and Discount Remarks
	 * Combo box.
	 * 
	 * @param voObj -
	 *            Value Object
	 */

	public void initGroupAndDiscountDetails(BillReconcileTransVO voObj) {

		try {
			BillReconcileTransDAO.getOffLineGroup(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				String err = "BillReconcileTransBO.initGroupAndDiscountDetails()-->"
						+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		} catch (Exception e) {
			voObj
					.setStrMsgString("BillReconcileTransBO.initGroupAndDiscountDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * retrieves Group List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */

	public void getGroupDetails(BillReconcileTransVO voObj) {

		try {
			BillReconcileTransDAO.getOffLineGroup(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				String err = "BillReconcileTransBO.getGroupDetails()-->"
						+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		} catch (Exception e) {
			voObj.setStrMsgString("BillReconcileTransBO.getGroupDetails() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * retrieves Tariff List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */

	public void getTariffDetails(BillReconcileTransVO voObj) {

		try {
			BillReconcileTransDAO.getOffLineTariffList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				String err = "BillReconcileTransBO.getTariffDetails()-->"
						+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		} catch (Exception e) {
			voObj
					.setStrMsgString("BillReconcileTransBO.getTariffDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * used to get unit based on tariff name
	 * 
	 * @param voObj
	 */
	public void getOffLineTariffUnitDetails(BillReconcileTransVO voObj) {

		try {

			BillReconcileTransDAO.getOffLineTariffUnitList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				String err = "BillReconcileTransBO.getOffLineTariffUnitDetails()-->"
						+ voObj.getStrMsgString();
				voObj.setStrMsgString(err);
			}
		} catch (Exception e) {
			voObj
					.setStrMsgString("BillReconcileTransBO.getOffLineTariffUnitDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	public void insertData(BillReconcileTransVO vo) {

		try {

			BillReconcileTransDAO.insertDataProc(vo);

			if (vo.getStrMsgType().equals("1")) {
				String err = "BillReconcileTransBO.insertData()-->"
						+ vo.getStrMsgString();
				vo.setStrMsgString(err);
			}
		} catch (Exception e) {

			vo.setStrMsgString("BillReconcileTransBO.insertData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * retrieves Tariff List.
	 * 
	 * @param voObj -
	 *            Value Object
	 */
	public void getTariffCodeDetails(BillReconcileTransVO voObj) {
		BillReconcileTransDAO.getOffLineTariffListByCode(voObj);

		if (voObj.getStrMsgType().equals("1")) {

			String err = "BillReconcileTransBO.getTariffCodeDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
	}

	
}
