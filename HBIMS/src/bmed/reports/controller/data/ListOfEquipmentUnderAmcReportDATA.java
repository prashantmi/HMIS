package bmed.reports.controller.data;

import hisglobal.utility.HisUtil;
import bmed.transactions.bo.BmedTransBO;
import bmed.vo.HemtItemMcDtlVO;

/**
 * Developer : Vivek Aggarwal
 * Version : 1.0
 * Date : 21-June-	2011
 *
 */
public class ListOfEquipmentUnderAmcReportDATA 
{

	/**
	 * To set Initial Details
	 * 
	 * @param hemtItemMcDtlVO_p the HemtItemMcDtlVO
	 * 
	 * @throws Exception
	 */
	public static void setInitDtl(HemtItemMcDtlVO hemtItemMcDtlVO_p) throws Exception
	{
		BmedTransBO bmedTransBO=null;
		
		try
		{
			bmedTransBO = new BmedTransBO();
			
			
			bmedTransBO.getMaintenanceContractDetailsForReport(hemtItemMcDtlVO_p);
		}
		catch (Exception e) {
			throw new Exception("ListOfEquipmentUnderAmcReportDATA.getMaintenanceContractDetailsForReport(hemtItemMcDtlVO_p)-->" + e.getMessage());
		}
		finally
		{
			
		}
	}

}
