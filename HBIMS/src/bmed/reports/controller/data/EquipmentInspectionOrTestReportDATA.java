package bmed.reports.controller.data;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.reports.bo.EquipmentInspectionOrTestReportBO;
import bmed.reports.controller.fb.EquipmentInspectionOrTestReportFB;
import bmed.reports.vo.EquipmentInspectionOrTestReportVO;
import bmed.transactions.bo.BmedGlobalBO;

/**
 * @author   Adil Wasi
 * Creation Date:- 03-Sept-2012
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) Equipment Inspection/Test Report
 * 
 */

public class EquipmentInspectionOrTestReportDATA 
{
	/**
	 * This method is used to initialize Main Page of Report

	 * @param equipmentInspectionOrTestReportVO_P	the EquipmentInspectionOrTestReportVO
	 * 
	 * @throws Exception
	 */
	public static void setInitDtl(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_P)	throws Exception 
	{

		BmedGlobalBO bmedGlobalBO=null;
		String strStoreComboOptions="",strTestNameComboOptions="";
		try 
		{
				bmedGlobalBO = new BmedGlobalBO();
			    
				// Calling BO Method
				strStoreComboOptions=bmedGlobalBO.getStoreComboOptions(equipmentInspectionOrTestReportVO_P.getStrHospitalCode(),equipmentInspectionOrTestReportVO_P.getStrSeatid());
				strTestNameComboOptions = bmedGlobalBO.getTestComboOptions(equipmentInspectionOrTestReportVO_P.getStrHospitalCode());
				
				equipmentInspectionOrTestReportVO_P.setStrStoreCmb(strStoreComboOptions);
				equipmentInspectionOrTestReportVO_P.setStrTestCmb(strTestNameComboOptions);
								
		} 

		 catch (Exception e) 
		 {
				throw new Exception("EquipmentInspectionOrTestReportDATA.setInitDtl()-->" + e.getMessage());
 
		 } 
		finally 
		{
			bmedGlobalBO = null;
		}
		
	}	

	/**
     * This method is used to get Store Name
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */
	public static void getItemNameCombo(EquipmentInspectionOrTestReportFB formBean_p,HttpServletRequest request_p,HttpServletResponse response_p)	 
	{
		EquipmentInspectionOrTestReportBO bo=null;
		String strErrMsg;
		EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_P;
		String strItemComboOptions="";
		try 
		{
			equipmentInspectionOrTestReportVO_P=new EquipmentInspectionOrTestReportVO();
			bo = new EquipmentInspectionOrTestReportBO();

			equipmentInspectionOrTestReportVO_P.setStrHospitalCode(formBean_p.getStrHospitalCode());
			equipmentInspectionOrTestReportVO_P.setStrStoreId(formBean_p.getStrStoreId());
			strItemComboOptions = bo.getItemBrandComboOptionsOnStore(equipmentInspectionOrTestReportVO_P);
			
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
			strErrMsg = "EquipmentInspectionOrTestReportDATA.getItemNameCombo() --> "	+ e.getMessage();
			  HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",strErrMsg);
			  formBean_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			               eObj = null;
 
		} 
		finally 
		{
         bo  = null;
		}
		
	}	
	
	/**
     * This method is used to get Equipment Test Combo
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     * @param response_p
     */
	public static void getEquipmentTestCombo(EquipmentInspectionOrTestReportFB formBean_p,HttpServletRequest request_p,HttpServletResponse response_p)	 
	{
		EquipmentInspectionOrTestReportBO bo=null;
		String strErrMsg;
		EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_P;
		String strItemComboOptions="";
		try 
		{
			equipmentInspectionOrTestReportVO_P=new EquipmentInspectionOrTestReportVO();
			bo = new EquipmentInspectionOrTestReportBO();
			String strItemId= request_p.getParameter("strItemId");
			equipmentInspectionOrTestReportVO_P.setStrHospitalCode(formBean_p.getStrHospitalCode());
			equipmentInspectionOrTestReportVO_P.setStrStoreId(formBean_p.getStrStoreId());
			equipmentInspectionOrTestReportVO_P.setStrItemId(strItemId);
			strItemComboOptions = bo.getEquipmentTestCombo(equipmentInspectionOrTestReportVO_P);
			
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
			strErrMsg = "EquipmentInspectionOrTestReportDATA.getEquipmentTestCombo() --> "	+ e.getMessage();
			  HisException eObj = new HisException("BMED", "ItemWarrantyDtlsDATA",strErrMsg);
			  formBean_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			               eObj = null;
 
		} 
		finally 
		{
         bo  = null;
		}
		
	}	
	
	/**
	 * This method is used to initialize Main Page of Report

	 * @param equipmentInspectionOrTestReportVO_P	the EquipmentInspectionOrTestReportVO
	 * 
	 * @throws Exception
	 */
	public static String setStoreDtl(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_P)	throws Exception 
	{

		BmedGlobalBO bmedGlobalBO=null;
		String strStoreComboOptions="";
		try 
		{
				bmedGlobalBO = new BmedGlobalBO();
			    
				// Calling BO Method
				strStoreComboOptions=bmedGlobalBO.getStoreComboOptions(equipmentInspectionOrTestReportVO_P.getStrHospitalCode(),equipmentInspectionOrTestReportVO_P.getStrSeatid());
								
		} 

		 catch (Exception e) 
		 {
				throw new Exception("EquipmentInspectionOrTestReportDATA.setStoreDtl()-->" + e.getMessage());
 
		 } 
		finally 
		{
			bmedGlobalBO = null;
		}
		
		return strStoreComboOptions;
	}	
	 
	 /**
		 * This method is used to initialize Main Page of Report

		 * @param equipmentInspectionOrTestReportVO_P	the EquipmentInspectionOrTestReportVO
		 * 
		 * @throws Exception
		 */
		public static String setTestDtl(EquipmentInspectionOrTestReportVO equipmentInspectionOrTestReportVO_P)	throws Exception 
		{

			BmedGlobalBO bmedGlobalBO=null;
			String strTestNameComboOptions="";
			try 
			{
					bmedGlobalBO = new BmedGlobalBO();
				    
					// Calling BO Method
					strTestNameComboOptions = bmedGlobalBO.getTestComboOptions(equipmentInspectionOrTestReportVO_P.getStrHospitalCode());
									
			} 

			 catch (Exception e) 
			 {
					throw new Exception("EquipmentInspectionOrTestReportDATA.setTestDtl()-->" + e.getMessage());
	 
			 } 
			finally 
			{
				bmedGlobalBO = null;
			}
			
			return strTestNameComboOptions;
		}
}