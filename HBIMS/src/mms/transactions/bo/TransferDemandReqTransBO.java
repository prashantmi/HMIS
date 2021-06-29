package mms.transactions.bo;

import mms.transactions.dao.TransferDemandReqTransDAO;
import mms.transactions.vo.TransferDemandReqTransVO;

public class TransferDemandReqTransBO 
{
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialAdd(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getItemName(vo);		
		TransferDemandReqTransDAO.GetGroupNameCombo(vo);	
		TransferDemandReqTransDAO.getSubGroupList(vo);
		TransferDemandReqTransDAO.getApprovedByCombo(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.initialAdd() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialModify(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getTransferDtlForModify(vo);		
		TransferDemandReqTransDAO.getApprovedByCombo(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.initialModify() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialView(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getTransferDtlForView(vo);
		TransferDemandReqTransDAO.getTransferOrderDtlForView(vo);
			
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.initialView() --> "
					+ vo.getStrMsgString());
		}
	}
	
	
	
	
	/*
	 * Get Sub Group & Generic Item Name on the Basis of Store Id and Group Id
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void subGrpAndItemCmb(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getSubGroupList(vo);	
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.subGrpAndItemCmb() --> "
					+ vo.getStrMsgString());
		}
	}
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getItemBrandCmb(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getItemName(vo);		
		
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.getItemBrandCmb() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getTransferDtl(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getTransferDtl(vo);		
				
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.getTransferDtl() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getTransferItemDtl(TransferDemandReqTransVO vo) 
	{		
		TransferDemandReqTransDAO.getTransferItemDtl(vo);		
				
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferDemandReqTransBO.getTransferItemDtl() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * to insert the data
	 * 
	 * @param vo
	 */	

	public void insert(TransferDemandReqTransVO vo) 
	{
		TransferDemandReqTransDAO.insert(vo);
		if (vo.getStrMsgType().equals("1")) 
		{
			String strErr = vo.getStrMsgString();
			vo.setStrMsgString("TransferDemandReqTransBO.insert() --> " + strErr);
		}

	}
	
	/**
	 * to insert the data
	 * 
	 * @param vo
	 */	

	public void update(TransferDemandReqTransVO vo) 
	{
		TransferDemandReqTransDAO.update(vo);
		if (vo.getStrMsgType().equals("1")) 
		{
			String strErr = vo.getStrMsgString();
			vo.setStrMsgString("TransferDemandReqTransBO.update() --> " + strErr);
		}

	}
	
	/**
	 * CANCEL  Method is Used to transfer Value Object to Data Access Object 
	 * @param vo
	 */
	public void cancel(TransferDemandReqTransVO vo)
	{
		TransferDemandReqTransDAO.cancel(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("TransferDemandReqTransBO.CANCEL() --> "+ vo.getStrMsgString());
		  }
		  
	}

}
