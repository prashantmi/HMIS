package bmed.reports.controller.data;

import bmed.transactions.bo.BmedTransBO;
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
public class ListOfCompletedComplaintsDATA 
{

	/**
	 * This method is used to initialize Main Page of Report

	 * @param complaintRequestDtlVO_P	the ComplaintRequestDtlVO
	 * 
	 * @throws Exception
	 */
	public static void setInitDtl(ComplaintRequestDtlVO complaintRequestDtlVO_P)	throws Exception 
	{

		BmedTransBO bmedTransBO=null;
		
		try 
		{
				bmedTransBO = new BmedTransBO();
			    
				// Calling BO Method
				bmedTransBO.getComplaintRequestDtlForReport(complaintRequestDtlVO_P);
								
			} 

		 catch (Exception e) 
		 {
				throw new Exception("ListOfCompletedComplaintsDATA.setInitDtl()-->" + e.getMessage());
 
		 } 
		finally 
		{
			bmedTransBO = null;
		}
	}	
}