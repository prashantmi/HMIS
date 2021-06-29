package inpatient.transaction.dao;

import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.UserVO;

public interface StockEntryOfBloodDtlDAOi 
{
	public void creat(PatBloodStockDtlVO patBloodStockDtlVO,UserVO userVO);
	public void updateStockStatus(BloodTransfusionDtlVO bloodTrasVO,UserVO userVO);
	public void updateIsReactionStatus(TransfusionReactionDtlVO transReactionDtlVO,UserVO userVO);
}
