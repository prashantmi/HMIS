/**
 * 
 */
package mms.reports.bo;

import mms.reports.dao.ListItemWiseSupplierRptDAO;
import mms.reports.dao.ReturnDetailRptDAO;
import mms.reports.vo.ListItemWiseSupplierRptVO;

/**
 * @author user
 *
 */
public class ListItemWiseSupplierRptBO {
	
	/**
	 * To get values of Item Category Name:
	 * 
	 * @param vo
	 */
	public void initialAdd(ListItemWiseSupplierRptVO vo)
	{
		ListItemWiseSupplierRptDAO.itemCategoryName(vo);
		ListItemWiseSupplierRptDAO.getSuppliedByList(vo);
		ListItemWiseSupplierRptDAO.getHospitalName(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ListItemWiseSupplierRptBO.initialAdd---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}
	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getGroupName(ListItemWiseSupplierRptVO vo)
	{
		ListItemWiseSupplierRptDAO.groupName(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ListItemWiseSupplierRptBO.getGroupName---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}
	
	/**
	 * To get values of SubGroup Name associate with Group Name:
	 * 
	 * @param vo
	 */
	public void getSubGroupName(ListItemWiseSupplierRptVO vo)
	{
		ListItemWiseSupplierRptDAO.subGroupName(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ListItemWiseSupplierRptBO.getSubGroupName---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}
	
	/**
	 * To get values of Generic Item Name associate with Store, Item Category, Group Name, SubGrp:
	 * 
	 * @param vo
	 */
	public void getItemName(ListItemWiseSupplierRptVO vo)
	{
		ListItemWiseSupplierRptDAO.ItemName(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ListItemWiseSupplierRptBO.getGenItemName---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}
	

}
