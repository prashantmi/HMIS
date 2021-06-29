package billing.transactions;

import hisglobal.utility.HisUtil;

/**
* @author Anshul Jindal
* This is BO file i.e; Business Object File
* This file is used to transfer control to DAO file.
*/

/**
 * @author Anonymous
 *
 */
public class OnlineRequestCancellationTransBO {

	/**
	 * @param vo
	 */
	public void getOnlineRequestDetails(OnlineRequestCancellationTransVO vo) {
		//OnlineRequestCancellationTransDAO daoObj = null;

		try {
			//daoObj = new OnlineRequestCancellationTransDAO();
			OnlineRequestCancellationTransDAO.getOnlineRequestDetailsProc(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo
						.setStrMsgString("OnlineRequestCancellationTransBO."
								+ "getOnlineRequestDetails()-->"
								+ vo.getStrMsgString());
			}

		} catch (Exception e) {
			vo.setStrMsgString("OnlineRequestCancellationTransBO.getOnlineRequestDetails() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} 

	}

	/**
	 * @param vo
	 */
	public void getCancelledByCmb(OnlineRequestCancellationTransVO vo) {

		
		HisUtil hisUtil = null;
		String cancelledByCmb = null;
		
		try {
			
			hisUtil = new HisUtil("billing", "OnlineRequestCancellationTransBO");
			OnlineRequestCancellationTransDAO.getCancelledByCmbProc(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo
						.setStrMsgString("OnlineRequestCancellationTransBO.getCancelledByCmb()--"
								+ vo.getStrMsgString());
			} else {
				cancelledByCmb = hisUtil.getOptionValue(vo.getStrCancelledByWs(), "0^SelectValue",
						"Select Value", false);
				vo.setStrCancelledBy(cancelledByCmb);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("OnlineRequestCancellationTransBO.getCancelledByCmb() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			
			if (hisUtil != null)
				hisUtil = null;
		}

	}

	/**
	 * @param vo
	 */
	public void getCancelReasonCmb(OnlineRequestCancellationTransVO vo) {

		
		HisUtil hisUtil = null;
		String cancelReasonCmb = null;
		

		try {
			
			hisUtil = new HisUtil("billing", "OnlineRequestCancellationTransBO");
			OnlineRequestCancellationTransDAO.getCancelReasonProc(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo
						.setStrMsgString("OnlineRequestCancellationTransBO.getCancelledByCmb()--"
								+ vo.getStrMsgString());
			} else {
				cancelReasonCmb = hisUtil.getOptionValue(vo.getStrCancelReasonWs(), "", "", false);
				cancelReasonCmb = cancelReasonCmb + "<option value ='0'>"
						+ "others" + "</option>\n";
				vo.setStrCancelReason(cancelReasonCmb);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("OnlineRequestCancellationTransBO.getCancelReasonCmb() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			
			if (hisUtil != null)
				hisUtil = null;
		}
	}

	/**
	 * @param vo
	 */
	public void getPopUpInfo(OnlineRequestCancellationTransVO vo) {

		try {
			
			OnlineRequestCancellationTransDAO.getPopUpInfoProc(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("OnlineRequestCancellationTransBO."
						+ "getPopUpInfo() --> " + vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("OnlineRequestCancellationTransBO.getPopUpInfo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			
		}

	}
	
	/**
	 * @param vo
	 */
	public void insertData(OnlineRequestCancellationTransVO vo) {

		try {
			OnlineRequestCancellationTransDAO.insertDataProc(vo);
			
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("OnlineRequestCancellationTransBO."
						+ "insertData()--> " + vo.getStrMsgString());
			}else{
				vo.setStrMsg("Selected Request has been cancelled successfully");
			}

		} catch (Exception e) {

			vo.setStrMsgString("OnlineRequestCancellationTransBO.insertData() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			
		}

	}


}
