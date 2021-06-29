package bmed.reports.bo;

import javax.sql.rowset.WebRowSet;

import bmed.dao.GbltDepartmentMstDAO;
import bmed.dao.ItemBrandMstDAO;
import bmed.dao.ServiceEngineerWiseJobStatusReportDAO;
import bmed.vo.EquipmentWiseComplaintStatusReportVO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.ServiceEngineerWiseJobStatusReportVO;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;



public class EquipmentWiseComplaintStatusReportBO {
	
	
	/**
	 * To get Item Name on the basis of Dept code
	 * 
	 * @param 	strHospitalCode_p String
 	 * @param	strDepartmentId_p	the String
	 * 
	 * @return	strItemBrandComboOptions the String
	 */
	
	public String getItemBrandComboOptionsOnDepartment(String strHospitalCode_p, String strDepartmentId_p)	throws Exception 
	{
		String strItemBrandComboOptions = "<option title='All' value='%' selected='selected'>All</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "EquipmentWiseComplaintStatusReportBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("4");
			gbltItemBrandMstVO.setStrItemCatNo("0");
			gbltItemBrandMstVO.setStrStoreId("0");
			gbltItemBrandMstVO.setStrDepartmentId(strDepartmentId_p);
			gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "EquipmentWiseComplaintStatusReportBO");
			ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "%", "%^All", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"EquipmentWiseComplaintStatusReportBO.getItemBrandComboOptionsOnDepartment(String strHospitalCode_p, String strDepartmentId_p)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}
	
	

	

}
