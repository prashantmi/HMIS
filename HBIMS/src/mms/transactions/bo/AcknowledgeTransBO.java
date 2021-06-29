package mms.transactions.bo;

import mms.transactions.dao.AcknowledgeTransDAO;
import mms.transactions.vo.AcknowledgeTransVO;

public class AcknowledgeTransBO {
	
	public void getAcknowledgeVal(AcknowledgeTransVO vo)
	{
		
		AcknowledgeTransDAO.getAcknowledgeVal(vo);
		AcknowledgeTransDAO.getItemVal(vo);
				
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("AcknowledgeTransBO.getAcknowledgeVal() --> "+ vo.getStrMsgString());
		  }
	   }
	public void getAcknowledgeVal_voucher(AcknowledgeTransVO vo)
	{
		
		AcknowledgeTransDAO.getAcknowledgeValVoucher(vo);
		AcknowledgeTransDAO.getItemVal(vo);
				
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("AcknowledgeTransBO.getAcknowledgeVal() --> "+ vo.getStrMsgString());
		  }
	   }
	
	
	
	public void getAcknowledgeVal1(AcknowledgeTransVO vo)
	{
		
		AcknowledgeTransDAO.getAcknowledgeValView(vo);
		AcknowledgeTransDAO.getItemVal(vo);
		AcknowledgeTransDAO.getAckVal(vo);
				
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("AcknowledgeTransBO.getAcknowledgeVal1() --> "+ vo.getStrMsgString());
		  }
	   }
	
	public void insertRecord(AcknowledgeTransVO vo)
	{
		
		AcknowledgeTransDAO.insertRecord(vo);
			
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("AcknowledgeTransBO.insertRecord() --> "+ vo.getStrMsgString());
		  }
	   }
	
	 public void getTransferDtl(AcknowledgeTransVO _AcknowledgeTransVO){
			
			AcknowledgeTransDAO.getTransferDtl(_AcknowledgeTransVO);
			if (_AcknowledgeTransVO.getStrMsgType().equals("1")){
				_AcknowledgeTransVO.setStrMsgString("AcknowledgeTransBO.getTransferDtl() --> "+ _AcknowledgeTransVO.getStrMsgString());
			  }
			
		}

}
