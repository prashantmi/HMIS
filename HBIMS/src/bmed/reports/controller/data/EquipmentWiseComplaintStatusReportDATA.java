package bmed.reports.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.bo.EquipmentWiseComplaintStatusReportBO;
import bmed.reports.bo.ServiceEngineerWiseJobStatusReportBO;
import bmed.reports.controller.fb.EquipmentWiseComplaintStatusReportFB;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ItemWarrantyDtlsFB;
import bmed.vo.ComplaintRequestDtlVO;

/**
 * @author   Adil Wasi
 * Creation Date:- 30-Aug-2012 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Equipment Wise Complaint Status Reports
 * 
 */
public class EquipmentWiseComplaintStatusReportDATA
{

	
	/**
	 * This method is used to initialize Main Page of Report

	 * @param complaintRequestDtlVO_P	the ComplaintRequestDtlVO
	 * 
	 * @throws Exception
	 */
	public static String setInitDtl(String strHospitalCode_p, String strSeatId_p)	throws Exception 
	{
		BmedGlobalBO bmedGlobalBO=null;
		
		String strDepartmentComboOptions;
		try 
		{
			bmedGlobalBO  = new BmedGlobalBO();

			// Calling BO Method
			
           strDepartmentComboOptions = bmedGlobalBO.getDepartmentBasedOnRoleSeatCombo(strHospitalCode_p,strSeatId_p);
			
			
		} 
		catch (Exception e) 
		{
			throw new Exception("EquipmentWiseComplaintStatusReportDATA.setInitDtl()-->" + e.getMessage());
 
		} 
		
		return strDepartmentComboOptions;
	}	

	/**
     * This method is used to get Store Name
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */
	public static void getItemNameCombo(EquipmentWiseComplaintStatusReportFB formBean_p,HttpServletRequest request_p,HttpServletResponse response_p)	 
	{
		EquipmentWiseComplaintStatusReportBO bo=null;
		String strErrMsg;
		
		String strItemComboOptions="";
		try 
		{
			bo = new EquipmentWiseComplaintStatusReportBO();

			
			strItemComboOptions = bo.getItemBrandComboOptionsOnDepartment(formBean_p.getStrHospCode(),formBean_p.getStrDeptId());
			
				// Calling BO Method
			
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strItemComboOptions);
					
			}
			catch(Exception e)
			{
//					e.printStackTrace();
			}
												
		} 
		catch (Exception e) 
		{
			strErrMsg = "EquipmentWiseComplaintStatusReportDATA.getItemNameCombo() --> "	+ e.getMessage();
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