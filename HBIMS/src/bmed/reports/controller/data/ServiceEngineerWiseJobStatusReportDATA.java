
package bmed.reports.controller.data;


import bmed.reports.bo.ServiceEngineerWiseJobStatusReportBO;


/**
 * @author   Adil Wasi
 * Creation Date:- 29-Aug-2012 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Service Engineer Wise Job Status Reports
 * 
 */

public class ServiceEngineerWiseJobStatusReportDATA
{


	/**
	 * This method is used to initialize Main Page of Report

	 * @param complaintRequestDtlVO_P	the ComplaintRequestDtlVO
	 * 
	 * @throws Exception
	 */
	public static String setInitDtl(String strHospitalCode)	throws Exception 
	{
		ServiceEngineerWiseJobStatusReportBO bo=null;
		
		String strServiceEngineerComboOptions;
		try 
		{
			bo = new ServiceEngineerWiseJobStatusReportBO();

			// Calling BO Method
			strServiceEngineerComboOptions = bo.getServiceEngineerComboOptions(strHospitalCode);
			
		} 
		catch (Exception e) 
		{
			throw new Exception("ServiceEngineerWiseJobStatusReportDATA.setInitDtl()-->" + e.getMessage());
 
		} 
		finally 
		{
         bo  = null;
		}
		return strServiceEngineerComboOptions;
	}	
}