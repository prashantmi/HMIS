package bmed.reports.bo;

import javax.sql.rowset.WebRowSet;

import bmed.dao.ServiceEngineerWiseJobStatusReportDAO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.ServiceEngineerWiseJobStatusReportVO;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;



public class ServiceEngineerWiseJobStatusReportBO {
	
	/**
	 * To get Service Engineer Name
	 * 
	 * @param 	strHospitalCode_p String
	 * 
	 * @return	strServiceEngineerComboOptions the String
	 */
	
	public String getServiceEngineerComboOptions(String strHospitalCode_p)	throws Exception 
	{
		String strServiceEngineerComboOptions = "<option title='Select Value' value='0' selected='selected'>Select Value</option>";
		ServiceEngineerWiseJobStatusReportVO serviceEngineerWiseJobStatusReportVO;
		WebRowSet wrsServiceEngineerComboOptions;
		HisUtil hisUtil;
		try {
			hisUtil = new HisUtil("BMED", "ServiceEngineerWiseJobStatusReportBO");
			serviceEngineerWiseJobStatusReportVO = new ServiceEngineerWiseJobStatusReportVO();

			serviceEngineerWiseJobStatusReportVO.setStrMode("1");
			serviceEngineerWiseJobStatusReportVO.setStrHospitalCode(strHospitalCode_p);

			HisDAO hisDAO = new HisDAO("BMED", "BmedTransBO");
			ServiceEngineerWiseJobStatusReportDAO.getServiceEngineerCombo(serviceEngineerWiseJobStatusReportVO,hisDAO);
			wrsServiceEngineerComboOptions = serviceEngineerWiseJobStatusReportVO.getWrsServiceEngineerComboOptions();
			if (wrsServiceEngineerComboOptions != null) {
				strServiceEngineerComboOptions = hisUtil.getOptionValue(wrsServiceEngineerComboOptions, "0", "0^Select Value", false);
			}

		} catch (Exception ex) {
			throw new Exception(
					"ServiceEngineerWiseJobStatusReportBO.getwrsServiceEngineerComboOptions(String strHospitalCode_p)-->"
							+ ex.getMessage());
		}
		return strServiceEngineerComboOptions;

	}
	
	

	

}
