package bmed.reports.controller.data;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.controller.fb.logRegisterReportFB;
import bmed.transactions.bo.BmedGlobalBO;

public class logRegisterReportDATA {
	
	public static String setInitDtl(String strHospitalCode_p, String strSeatId_p)	throws Exception 
	{
		BmedGlobalBO bmedGlobalBO=null;
		
		String strDepartmentComboOptions;
		try 
		{
			bmedGlobalBO  = new BmedGlobalBO();

			// Calling BO Method
			
           strDepartmentComboOptions = bmedGlobalBO.getDepartmentBasedOnRoleSeatCombo(strHospitalCode_p,strSeatId_p);
           //System.out.println(" ------------"+strDepartmentComboOptions);
			
		} 
		catch (Exception e) 
		{
			throw new Exception("logRegisterReportDATA.setInitDtl()-->" + e.getMessage());
 
		} 
		
		return strDepartmentComboOptions;
	}	

	/**
     * This method is used to get Store Name
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */
	public static void getStoreNameCombo(logRegisterReportFB formBean_p,HttpServletRequest request_p,HttpServletResponse response_p)	 
	{
		BmedGlobalBO bo=null;
		String strErrMsg="";		
		String strStoreComboOptions="";
		try 
		{
			bo = new BmedGlobalBO();

			
			//strStoreComboOptions = bo.getStoreComboOptions(formBean_p.getStrHospCode(),formBean_p.getStrDeptId());
			/* Calling BO Method
			 * */
			strStoreComboOptions = bo.getStoreComboOptions(formBean_p.getStrHospCode(), formBean_p.getStrSeatId(), formBean_p.getStrDeptId());
			//System.out.println(" Store Combo------------"+strStoreComboOptions);
				// Calling BO Method
			
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strStoreComboOptions);
					
			}
			catch(Exception e)
			{
//					e.printStackTrace();
			}
												
		} 
		catch (Exception e) 
		{
			strErrMsg = "logRegisterReportDATA.getStoreNameCombo() --> "	+ e.getMessage();
			  HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",strErrMsg);
			  formBean_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			               eObj = null;
 
		} 
		finally 
		{
         bo  = null;
		}
		
	}	

}
