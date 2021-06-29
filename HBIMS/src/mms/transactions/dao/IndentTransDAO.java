package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;
import mms.dao.DmlIndentCancelDtlDAO;
import mms.dao.DmlIndentReturnRequestDAO;
import mms.transactions.vo.IndentTransVO;

public class IndentTransDAO 
{
	/**
	 * This method is used to Cancel data into Table'
	 *  
	 * @param vo
	 */
	public synchronized static void CANCEL(IndentTransVO vo) 
	{
		HisDAO dao = null;
		DmlIndentCancelDtlDAO globalDao = null;
		DmlIndentReturnRequestDAO tableDao = null;
		try 
		{
			     dao = new HisDAO("MMS","transactions.IndentTransDAO.CANCEL()");
			     tableDao   = new DmlIndentReturnRequestDAO();	
			     globalDao  = new DmlIndentCancelDtlDAO();
			     
			     
			     globalDao.setStoreId(vo.getStrStoreId());
   			     globalDao.setHosp_code(vo.getStrHospCode());
   			     globalDao.setReqNo(vo.getStrReqNo());
   			     globalDao.setReqTypeId(vo.getStrReqTypeId());
   			     globalDao.setRemarks(vo.getStrCancelReson());
   			     globalDao.setSeatId(vo.getStrSeatId());
   			     globalDao.setItemcatNo(vo.getStrItemCatgNo()); 
   			    
   			     tableDao.setStrId(vo.getStrStoreId()); 
   			     tableDao.setStrItemCatgNo(vo.getStrItemCatgNo());
				 tableDao.setHosp_code(vo.getStrHospCode());
				 tableDao.setReqNo(vo.getStrReqNo()); 
				 tableDao.setReqTypeId(vo.getStrReqTypeId());
				 tableDao.setStrRemarks(vo.getStrCancelReson());
				 
				 if(vo.getStrReqTypeId().equals("16")||vo.getStrReqTypeId().equals("19"))
				 {	 
   			       tableDao.insert12(dao);
				 }
				 if(vo.getStrReqTypeId().equals("18"))
				 {
				   tableDao.insert11(dao);
				 }	 
     		     globalDao.Update(dao);
			    				     
     		    dao.fire();     // Here we Execute in Batch
		     
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
			vo.setStrMsgString("--> IndentTransDAO.CANCEL()-->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		} 
    	finally 
    	{
			if (dao != null)
				dao.free();
			dao = null;
		}

	}

}
