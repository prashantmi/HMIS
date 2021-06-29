package mms.transactions.bo;

import mms.transactions.dao.TransferApprovalTransDAO;
import mms.transactions.vo.TransferApprovalTransVO;

public class TransferApprovalTransBO {


	public void initOrderGenerate(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.getDWHList(vo);
		TransferApprovalTransDAO.getDemandRequestDetails(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.demandReject() --> "
					+ strErr);
		}

	}
	


	public void insertOrderGenerate(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.insertOrderGenerate(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.insertOrderGenerate() --> "
					+ strErr);
		}

	}
	
	public void initOrderModify(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.getOrderDetails(vo);
		TransferApprovalTransDAO.getTransferingDetailsModify(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.demandReject() --> "
					+ strErr);
		}

	}
	

	public void insertOrderModify(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.insertOrderModify(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.insertOrderModify() --> "
					+ strErr);
		}

	}
	
	public void getDemandRequestDetails(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.getDemandRequestDetails(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.getDemandRequestDetails() --> "
					+ strErr);
		}

	}
	
	

	public void getTransferingDetails(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.getTransferingDetails(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.getTransferingDetails() --> "
					+ strErr);
		}

	}
	
	
	
	public void demandReject(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.demandReject(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.demandReject() --> "
					+ strErr);
		}

	}

	public void transferReject(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.transferReject(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.transferReject() --> "
					+ strErr);
		}

	}

	public void transferForcefullyClose(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.transferForcefullyClose(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.transferForcefullyClose() --> "
					+ strErr);
		}

	}


	public void cancelOrder(TransferApprovalTransVO vo) {

		TransferApprovalTransDAO.cancelOrder(vo);

		if (vo.getStrMsgType().equals("1")) {

			String strErr = vo.getStrMsgString();

			vo.setStrMsgString("TransferApprovalTransBO.cancelOrder() --> "
					+ strErr);
		}

	}
	
}
