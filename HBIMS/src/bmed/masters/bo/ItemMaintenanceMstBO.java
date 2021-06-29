package bmed.masters.bo;

import bmed.masters.dao.ItemMaintenanceMstDAO;
import bmed.masters.vo.ItemMaintenanceMstVO;

public class ItemMaintenanceMstBO 
{
	/**
	 * Gets the component name.
	 * 
	 * @param vo the vo
	 * 
	 * @return the component name
	 */
	public void getAddPageComponent(ItemMaintenanceMstVO vo) 
	{
		ItemMaintenanceMstDAO.getDeptStoreName(vo);
		ItemMaintenanceMstDAO.getItemCatgCmb(vo);
		ItemMaintenanceMstDAO.getEnggItemTypeCmb(vo);
		
		ItemMaintenanceMstDAO.getUnitCmb(vo);
		//ItemMaintenanceMstDAO.getMaintenanceNameCmb(vo);
		ItemMaintenanceMstDAO.getMaintenanceNameCmb(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ItemMaintenanceMstBO.getAddPageComponent()---->"
					+ vo.getStrMsgString());
	}
	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getItemNameCmb(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.getItemNameCmb(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ItemMaintenanceMstBO.getItemNameCmb()---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}

	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getStockDtl(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.getStockDtl(vo);
		
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ItemMaintenanceMstBO.getStockDtl()---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}

	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getMaintenanceNameCmb(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.getMaintenanceNameCmb(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ItemMaintenanceMstBO.getMaintenanceNameCmb()---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}

	
	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getItemSlNoCmbBasisOfItem(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.getItemSlNoCmbBasisOfItem(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ItemMaintenanceMstBO.getItemSlNoCmbBasisOfItem()---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}

	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getEnggItemSubTypeOnBasisOfEnggItemType(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.getEnggItemSubTypeOnBasisOfEnggItemType(vo);
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ItemMaintenanceMstBO.getEnggItemSubTypeOnBasisOfEnggItemType()---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}
	
	
	/**
	 * To get values of Group Name associate with Item Category:
	 * 
	 * @param vo
	 */
	public void getLeftListBoxValue(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.getLeftListBoxValue(vo);
		ItemMaintenanceMstDAO.getRightListBoxValue(vo);
		
		if(vo.getStrMsgType().equals("1"))
		{
				vo.setStrMsgString("ItemMaintenanceMstBO.getEnggItemSubTypeOnBasisOfEnggItemType()---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
		}
	}	
	
	
	/**
	 * to insert the data and check duplicate before insert record.
	 * 
	 * @param vo the vo
	 */
	public void insertRecord(ItemMaintenanceMstVO vo) 
	{
		ItemMaintenanceMstDAO.chkDuplicate(vo);
		
		if (vo.getBExistStatus() == true)
		{
			ItemMaintenanceMstDAO.insertQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("ItemMaintenanceMstBO.insert---->"+ vo.getStrMsgString());
			}
		}
		
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("ItemMaintenanceMstBO.insertRecord() --> "
					+ vo.getStrMsgString());
		}

	}
	
	/**
	 * Modify.
	 * 
	 * @param vo the vo
	 */
	public void modify(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.modify(vo);
		ItemMaintenanceMstDAO.getUnitCmb(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ItemMaintenanceMstBO.modify()---->" + vo.getStrMsgString());
	}
	
	/**
	 * View Details
	 * 
	 * @param vo the vo
	 */
	public void View(ItemMaintenanceMstVO vo)
	{
		ItemMaintenanceMstDAO.view(vo);
		if (vo.getStrMsgType().equals("1"))
			vo.setStrMsgString("ItemMaintenanceMstBO.modify()---->" + vo.getStrMsgString());
	}

	/**
	 * Update.
	 * 
	 * @param vo the vo
	 */
	public void updateRecord(ItemMaintenanceMstVO vo) 
	{
			ItemMaintenanceMstDAO.updateRecord(vo);
			if (vo.getStrMsgType().equals("1"))
				vo.setStrMsgString("ItemMaintenanceMstBO.updateRecord()---->"
						+ vo.getStrMsgString());
		
	}
	
	
	/**
	 * Update.
	 * 
	 * @param vo the vo
	 */
	public void getWarrantyMaintenanceDtl(ItemMaintenanceMstVO vo) 
	{
			ItemMaintenanceMstDAO.getWarrantyData(vo);
			ItemMaintenanceMstDAO.getContractData(vo);
			if (vo.getStrMsgType().equals("1"))
				vo.setStrMsgString("ItemMaintenanceMstBO.updateRecord()---->"
						+ vo.getStrMsgString());
		
	}

	
}
