package bmed.transactions.bo;

import javax.sql.rowset.WebRowSet;

import bmed.dao.ItemBrandMstDAO;
import bmed.vo.ItemBrandMstVO;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;



public class CommutativeExpenditureBO {
	
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
		String strItemBrandComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ItemBrandMstVO gbltItemBrandMstVO;
		WebRowSet wrsItemBrandComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "BmedTransBO");
			gbltItemBrandMstVO = new ItemBrandMstVO();

			gbltItemBrandMstVO.setStrMode("9");
			gbltItemBrandMstVO.setStrItemCatNo("18");
			gbltItemBrandMstVO.setStrStoreId("0");
			gbltItemBrandMstVO.setStrDepartmentId(strDepartmentId_p);
			gbltItemBrandMstVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			ItemBrandMstDAO.getItemBrandCombo(gbltItemBrandMstVO, hisDAO);
			wrsItemBrandComboOptions = gbltItemBrandMstVO
					.getWrsItemBrandComboOptions();
			if (wrsItemBrandComboOptions != null) {
				strItemBrandComboOptions = hisUtil.getOptionValue(
						wrsItemBrandComboOptions, "0", "0^Select Value", false);

			}

		} catch (Exception ex) {
			throw new Exception(
					"BmedTransBO.getItemBrandComboOptionsOnDepartment(String strHospitalCode_p, String strDepartmentId_p)-->"
							+ ex.getMessage());
		}
		return strItemBrandComboOptions;

	}
	
	

	

}
