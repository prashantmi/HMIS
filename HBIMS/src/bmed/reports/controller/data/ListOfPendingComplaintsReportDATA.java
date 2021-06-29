package bmed.reports.controller.data;

import bmed.transactions.bo.BmedTransBO;
import bmed.vo.ComplaintRequestDtlVO;

/**
 * Developer : Vivek Aggarwal
 * Version : 1.0
 * Date : 23-June-	2011
 *
 */
public class ListOfPendingComplaintsReportDATA 
{

	/**
	 * To set Initial Details
	 * 
	 * @param hemtItemMcDtlVO_p the HemtItemMcDtlVO
	 * 
	 * @throws Exception
	 */
	public static void setInitDtl(ComplaintRequestDtlVO complaintRequestDtlVO_p) throws Exception
	{
		BmedTransBO bmedTransBO=null;
		
		try
		{
			bmedTransBO = new BmedTransBO();
			
			bmedTransBO.getComplaintRequestDtlForReport(complaintRequestDtlVO_p);
		}
		catch (Exception e) {
			throw new Exception("ListOfEquipmentUnderAmcReportDATA.getMaintenanceContractDetailsForReport(hemtItemMcDtlVO_p)-->" + e.getMessage());
		}
		finally
		{
			
		}
	}

}
