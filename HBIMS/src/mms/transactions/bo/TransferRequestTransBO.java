package mms.transactions.bo;

import mms.transactions.dao.TransferRequestTransDAO;
import mms.transactions.vo.TransferRequestTransVO;

public class TransferRequestTransBO 
{
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialAdd(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getItemName(vo);		
		TransferRequestTransDAO.GetGroupNameCombo(vo);	
		TransferRequestTransDAO.getSubGroupList(vo);
		TransferRequestTransDAO.getApprovedByCombo(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.initialAdd() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialModify(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getTransferDtlForModify(vo);		
		TransferRequestTransDAO.getApprovedByCombo(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.initialModify() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialView(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getTransferDtlForView(vo);
		TransferRequestTransDAO.getTransferOrderDtlForView(vo);
			
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.initialView() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void initialOrderView(TransferRequestTransVO vo) 
	{		
		
		TransferRequestTransDAO.getOrderDtlForView(vo);
		           TransferRequestTransDAO.getTransferDtl(vo);		
			
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.initialOrderView() --> "
					+ vo.getStrMsgString());
		}
	}
	
	
	
	
	/*
	 * Get Sub Group & Generic Item Name on the Basis of Store Id and Group Id
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void subGrpAndItemCmb(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getSubGroupList(vo);	
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.subGrpAndItemCmb() --> "
					+ vo.getStrMsgString());
		}
	}
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getItemBrandCmb(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getItemName(vo);		
		
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.getItemBrandCmb() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getTransferDtl(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getTransferDtl(vo);		
				
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.getTransferDtl() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * for getting option value of combo on add page
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getTransferItemDtl(TransferRequestTransVO vo) 
	{		
		TransferRequestTransDAO.getTransferItemDtl(vo);		
				
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("TransferRequestTransBO.getTransferItemDtl() --> "
					+ vo.getStrMsgString());
		}
	}
	
	/**
	 * to insert the data
	 * 
	 * @param vo
	 */	

	public void insert(TransferRequestTransVO vo) 
	{
		TransferRequestTransDAO.insert(vo);
		if (vo.getStrMsgType().equals("1")) 
		{
			String strErr = vo.getStrMsgString();
			vo.setStrMsgString("TransferRequestTransBO.insert() --> " + strErr);
		}

	}
	
	/**
	 * to insert the data
	 * 
	 * @param vo
	 */	

	public void update(TransferRequestTransVO vo) 
	{
		TransferRequestTransDAO.update(vo);
		if (vo.getStrMsgType().equals("1")) 
		{
			String strErr = vo.getStrMsgString();
			vo.setStrMsgString("TransferRequestTransBO.update() --> " + strErr);
		}

	}
	
	/**
	 * CANCEL  Method is Used to transfer Value Object to Data Access Object 
	 * @param vo
	 */
	public void cancel(TransferRequestTransVO vo)
	{
		TransferRequestTransDAO.cancel(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("TransferRequestTransBO.CANCEL() --> "+ vo.getStrMsgString());
		  }
		  
	}

}


