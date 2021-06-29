/**
 * 
 */
package mms.transactions.bo;

import mms.transactions.dao.LocalPurchaseNewTransDAO;
import mms.transactions.vo.LocalPurchaseNewTransVO;

/**
 * @author user
 *
 */
public class LocalPurchaseNewTransBO {
	
	/**
	 * To get values of Item Category Name:
	 * 
	 * @param vo
	 */
	public void initialAdd(LocalPurchaseNewTransVO vo)
	{
		LocalPurchaseNewTransDAO.getSupplierList(vo);
		LocalPurchaseNewTransDAO.getItemList(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("LocalPurchaseNewTransBO.initialAdd---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}	
	}
	
	public void save(LocalPurchaseNewTransVO vo)
	{
		//if(vo.getStrItemCategoryNo().equals("1")){
			LocalPurchaseNewTransDAO.save(vo);
		//}else{
		//	LocalPurchaseNewTransDAO.stockDetails(vo);
		//	LocalPurchaseNewTransDAO.empStockDetails(vo);
		//}
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("LocalPurchaseNewTransBO.searchStockDtl---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}
	
	public void getPrintDetails(LocalPurchaseNewTransVO vo) {
		LocalPurchaseNewTransDAO.getPrintDetails(vo);
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("ChallanProcessTransBO.getVerifiedItemDetails() --> "
					+ vo.getStrMsgString());
		}
	}

}
