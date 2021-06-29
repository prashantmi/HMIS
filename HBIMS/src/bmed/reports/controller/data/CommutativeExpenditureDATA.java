package bmed.reports.controller.data;

import hisglobal.hisconfig.Config;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.bo.CommutativeExpenditureBO;
import bmed.vo.ComplaintRequestDtlVO;

/**
 * @author   Amit kr
 * Creation Date:- 21-June-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) List of Compeleted Complaints Request Reports
 * 
 */
public class CommutativeExpenditureDATA
{


	/**
	 * This method is used to initialize Main Page of Report

	 * @param complaintRequestDtlVO_P	the ComplaintRequestDtlVO
	 * 
	 * @throws Exception
	 */
	public static String setInitDtl(ComplaintRequestDtlVO complaintRequestDtlVO_P,String strHospitalCode)	throws Exception 
	{
		CommutativeExpenditureBO bo=null;
		
		String strItemComboOptions;
		try 
		{
			bo = new CommutativeExpenditureBO();

			
			strItemComboOptions = bo.getItemBrandComboOptionsOnDepartment(Config.SUPER_USER_HOSPITAL_CODE,"0");
			
				// Calling BO Method
												
		} 
		catch (Exception e) 
		{
			throw new Exception("ListOfCompletedComplaintsDATA.setInitDtl()-->" + e.getMessage());
 
		} 
		finally 
		{
         bo  = null;
		}
		return strItemComboOptions;
	}	
}