package bmed.reports.bo;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import bmed.dao.EquipmentInspectionOrTestReportDAO;
import bmed.dao.ItemBrandMstDAO;
import bmed.reports.vo.EquipmentInspectionOrTestReportVO;
import bmed.vo.ItemBrandMstVO;

public class EquipmentInspectionOrTestReportBO {

	/**
	 * This method will give Equipment Inspection/Test Report Data according to  Hospital Code 
	 * 
	 * @param equipmentInspectionOrTestReportVO_p	the EquipmentInspectionOrTestReportVO
	 * 
	 * @throws Exception
	 */
	public void getEquipmentInspectionOrTestDtlForReport(
			EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_p) throws Exception {

		HisDAO hisDao = null;
		

		try {
			hisDao = new HisDAO("bmed", "EquipmentInspectionOrTestReportBO");
			
			EquipmentInspectionOrTestReportDAO.getData(equipmentInspectionOrTestReportVO_p, hisDao);

		} 
		catch (Exception e) 
		{
			throw new Exception("EquipmentInspectionOrTestReportBO.getEquipmentInspectionOrTestDtlForReport(EquipmentInspectionOrTestReportVO)-->"	+ e.getMessage());
		}
		finally 
		{
			/* Closing Transaction */
			if (hisDao != null) 
			{
				hisDao.free();
				hisDao = null;
			}
		}
	}
	
	/**
	 * To get Item Name on the basis of Store Id
	 * 
	 * @param 	equipmentInspectionOrTestReportVO_p the EquipmentInspectionOrTestReportVO
 	 * 
	 * @return	strItemBrandComboOptions the String
	 */
	
	public String getItemBrandComboOptionsOnStore(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_p)	throws Exception 
	{
		String strItemBrandComboOptions =""; 
		if(equipmentInspectionOrTestReportVO_p.getStrUniqId().equals("1"))
			strItemBrandComboOptions = "<option title='All' value='%' selected='selected'>All</option>";
		else
			strItemBrandComboOptions = "<option title='All' value='0' selected='selected'>Select Value</option>";
		
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "EquipmentInspectionOrTestReportBO");
			EquipmentInspectionOrTestReportDAO.getItemNameCombo(equipmentInspectionOrTestReportVO_p);
			wrsItemBrandComboOptions = equipmentInspectionOrTestReportVO_p.getWrsItemName();
			if (wrsItemBrandComboOptions != null) {
				if(equipmentInspectionOrTestReportVO_p.getStrUniqId().equals("1"))
					strItemBrandComboOptions = hisUtil.getOptionValue(
											wrsItemBrandComboOptions, "%", "%^All", false);
				else
					strItemBrandComboOptions = hisUtil.getOptionValue(
							wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"EquipmentInspectionOrTestReportBO.getItemBrandComboOptionsOnStore(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_p)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}
	
	/**
	 * To get Item Name on the basis of Store Id
	 * 
	 * @param 	equipmentInspectionOrTestReportVO_p the EquipmentInspectionOrTestReportVO
 	 * 
	 * @return	strItemBrandComboOptions the String
	 */
	
	public String getEquipmentTestCombo(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_p)	throws Exception 
	{
		String strEquipmentTestomboOptions =""; 
		
		strEquipmentTestomboOptions = "<option title='All' value='0' selected='selected'>Select Value</option>";
		
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "EquipmentInspectionOrTestReportBO");
			EquipmentInspectionOrTestReportDAO.getEquipmentTestCombo(equipmentInspectionOrTestReportVO_p);
			wrsItemBrandComboOptions = equipmentInspectionOrTestReportVO_p.getWrsEquipmentSlNo();
			if (wrsItemBrandComboOptions != null) {
				strEquipmentTestomboOptions = hisUtil.getOptionValue(
											wrsItemBrandComboOptions, "0", "0^Select Value", false);
				

			}

		} catch (Exception ex) {
			throw new Exception(
					"EquipmentInspectionOrTestReportBO.getItemBrandComboOptionsOnStore(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_p)-->"
							+ ex.getMessage());
		}
		return strEquipmentTestomboOptions;

	}

}
