package bmed.transactions.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.transactions.controller.data.OfflineSparePartAddTransDATA;
import bmed.transactions.controller.fb.OfflineSparePartAddTransFB;


/**
 * @author Vivek Aggarwal  
 * Creation Date:- 10-May-2011 
 * Modifying Date:- 13-May-2011
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class OfflineSparePartAddTransUTIL {

	public static void initializeMain(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,	HttpServletRequest request_p)
	{
		OfflineSparePartAddTransDATA.initializeMain(offlineSparePartAddTransFB_p, request_p);
	}

	/**
	 * To Get Store Names Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 */
	public static void getStoreName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getStoreName(offlineSparePartAddTransFB_p, request_p, response_p);
	}

	
	/**
	 * Get Engg Item Sub Type Combo using Ajax. 
	 * 
	 * @param mapping_p the mapping
	 * @param form_p the form
	 * @param request_p the request
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	
	public static void getEnggItemSubTypeCmb(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,	HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getEnggItemSubTypeCmb(offlineSparePartAddTransFB_p, request_p, response_p);
	}


	/**
	 * To Get Item Category Name Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getItemCategoryName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getItemCategoryName(offlineSparePartAddTransFB_p, request_p, response_p);
	}

	/**
	 * To Get Item Name on basis of Store
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * 
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getItemName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getItemName(offlineSparePartAddTransFB_p, request_p, response_p);
	}

	
	/**
	 * To Get Spare Part Name on basis of Item Name
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * 
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getSparePartName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getSparePartName(offlineSparePartAddTransFB_p, request_p, response_p);
	}
	
	/**
	 * To Get Stock Details Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * 
	 * @param request_p the HttpServletRequest
 	 * @param response_p the HttpServletResponse
	 */
	public static void getStockDetails(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,	HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getStockDetails(offlineSparePartAddTransFB_p, request_p, response_p);
	}

	/**
	 * To Get Spare Part Stock Details Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * 
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void getSparePartStockDetails(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.getSparePartStockDetails(offlineSparePartAddTransFB_p, request_p, response_p);		
	}
	
	
	/**
	 * To Save Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * 
	 * @param request_p the HttpServletRequest
 	 * @param response_p the HttpServletResponse
	 */
	public static void saveData(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p) 
	{
		OfflineSparePartAddTransDATA.saveData(offlineSparePartAddTransFB_p, request_p);
	}

	/**
	 * To delete Spare Part Stock Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * 
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 */
	public static void deleteSparePartStockDtl(	OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		OfflineSparePartAddTransDATA.deleteSparePartStockDtl(offlineSparePartAddTransFB_p, request_p, response_p);	
	}
}
