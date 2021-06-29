package bmed.transactions.controller.data;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.OfflineSparePartAddTransFB;
import bmed.vo.HemtItemSparePartDtlVO;

/**
 * @author Vivek Aggarwal  
 * Creation Date:- 10-May-2011 
 * Modifying Date:- 13-May-2011
 * Used For:- 
 * Team Lead By:-  
 *  Module:- BMED(HIS Project)
 * 
 */
public class OfflineSparePartAddTransDATA {

	/**
	 * To initialize the Page
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * 
	 * @return the null
	 */
	public static void initializeMain(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,	HttpServletRequest request_p) {
		
		String strMsgText;
		
		String strDepartmentComboOptions;
		String strEnggItemTypeComboOptions;
		String strUnitComboOptions;
		String strManufacturerNameCombo;
		
		String strHospitalCode,strSeatId;
		UserVO userVO = null;
		
		BmedGlobalBO bmedGlobalBO = null;
		//BmedTransBO bmedTransBO = null;

		try {

			bmedGlobalBO = new BmedGlobalBO();
			//bmedTransBO = new BmedTransBO();

			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId		= userVO.getSeatId();
			
			strDepartmentComboOptions = bmedGlobalBO.getDepartmentComboOptions(strHospitalCode,strSeatId,2);
			offlineSparePartAddTransFB_p.setStrDeptNameCombo(strDepartmentComboOptions);
			
			strEnggItemTypeComboOptions=bmedGlobalBO.getItemTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			offlineSparePartAddTransFB_p.setStrEngineeringItemTypeCmb(strEnggItemTypeComboOptions);
			
			
			 strUnitComboOptions = BmedGlobalBO.getUnitComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			 offlineSparePartAddTransFB_p.setStrUnitNameCombo(strUnitComboOptions);
			 
			 strManufacturerNameCombo=bmedGlobalBO.getManufacturerNameComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			 offlineSparePartAddTransFB_p.setStrManufacturerNameCombo(strManufacturerNameCombo);
			
		} 
		catch (Exception e)
		{
			strMsgText = "OfflineSparePartAddTransDATA.initializeMain --> " + e.getMessage();
			HisException eObj = new HisException("bmed", "OfflineSparePartAddTransDATA.initializeMain()",strMsgText);
			offlineSparePartAddTransFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally {
			strMsgText = null;
		}
		
	}

	/**
	 * To Get Store Names Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 * 
	 */
	public static void getStoreName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strStoreComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strDeptId;
		String strSeatId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strDeptId = request_p.getParameter("deptId");
			strStoreComboOptions = bmedGlobalBO.getStoreComboOptions(strHospitalCode, strSeatId, strDeptId);
			offlineSparePartAddTransFB_p.setStrStoreNameCombo(strStoreComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStoreComboOptions);
		}
		catch (Exception e) 
		{
			strMsgText = "OfflineSparePartAddTransDATA.getStoreName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "	+ e.getMessage();
			HisException eObj = new HisException("bmed", "OfflineSparePartAddTransDATA.getStoreName()", strMsgText);

			try
			{
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print("ERROR#### Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");
			} 
			catch (Exception e1) 
			{
				new HisException("bmed","OfflineSparePartAddTransDATA.getStoreName()",	strMsgText);
			}

			eObj = null;
		}
		finally 
		{
			bmedGlobalBO = null;
		}
	}

	
	/**
	 * Get Engg Item Sub Type Combo using Ajax. 
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	
	public static void getEnggItemSubTypeCmb(OfflineSparePartAddTransFB offlineSparePartAddTransFB,	HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strEnggItemSubTypeComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strEnggItemTypeId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strEnggItemTypeId = request_p.getParameter("enggItemType");
			strEnggItemSubTypeComboOptions = bmedGlobalBO.getItemSubTypeComboOptions(strHospitalCode,strEnggItemTypeId);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strEnggItemSubTypeComboOptions);

		}
		catch (Exception e) 
		{
			strMsgText = "OfflineSparePartAddTransDATA.getEnggItemSubTypeCmb(vo) --> " + e.getMessage();
			HisException eObj = new HisException("bmed","OfflineSparePartAddTransDATA.getEnggItemSubTypeCmb()",strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print("ERROR#### Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");
			} 
			catch (Exception e1) 
			{
				new HisException("bmed","OfflineSparePartAddTransDATA.getEnggItemSubTypeCmb()",strMsgText);
			}

			eObj = null;
		} 
		finally 
		{
			bmedGlobalBO = null;
		}
	}


	/**
	 * To Get Item Category Name Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public static void getItemCategoryName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strItemCategoryComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strStoreId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();

			strStoreId = request_p.getParameter("storeId");
			strItemCategoryComboOptions = bmedGlobalBO.getItemCategoryComboOptionsOnBasisOfStore(strHospitalCode, strStoreId);
			offlineSparePartAddTransFB_p.setStrStoreNameCombo(strItemCategoryComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strItemCategoryComboOptions);
		}
		catch (Exception e) 
		{
			strMsgText = "OfflineSparePartAddTransDATA.getStoreName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "	+ e.getMessage();
			HisException eObj = new HisException("bmed", "OfflineSparePartAddTransDATA.getStoreName()", strMsgText);

			try
			{
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print("ERROR#### Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");
			} 
			catch (Exception e1) 
			{
				new HisException("bmed","OfflineSparePartAddTransDATA.getStoreName()",	strMsgText);
			}

			eObj = null;
		}
		finally 
		{
			bmedGlobalBO = null;
		}
	}

	/**
	 * To Get Item Name on basis of Store
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public static void getItemName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strMsgText;

		BmedGlobalBO bmedGlobalBO = null;
		String strItemComboOptions = null;
		String strHospitalCode;
		String strItemCategoryId;
		UserVO userVO = null;
		try 
		{
			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			//strHospitalCode = userVO.getHospitalCode();
			strHospitalCode = Config.SUPER_USER_HOSPITAL_CODE;

			strItemCategoryId = (String) request_p.getParameter("itemCategoryId");
			strItemComboOptions = bmedGlobalBO.getItemNameComboOptionsOnItemCategory(strHospitalCode,strItemCategoryId);

			
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strItemComboOptions);
		} 
		catch (Exception e) 
		{
			strMsgText = "OfflineSparePartAddTransDATA.getItemName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "	+ e.getMessage();
			HisException eObj = new HisException("bmed", "OfflineSparePartAddTransDATA.getItemName()", strMsgText);

			try 
			{
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print("ERROR#### Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");
			} 
			catch (Exception e1)
			{
				new HisException("bmed", "OfflineSparePartAddTransDATA.getItemName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p)",strMsgText);
			}

			eObj = null;
		} 
		finally 
		{
			bmedGlobalBO = null;
		}
	}

	
	/*
	 * To Get Spare Part Name on basis of Item Name
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public static void getSparePartName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strMsgText;

		BmedTransBO bmedTransBO = null;
		String strItemComboOptions = null;
		String strHospitalCode;
		String strItemId;
		UserVO userVO = null;
		try 
		{
			bmedTransBO = new BmedTransBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();

			strItemId = (String) request_p.getParameter("itemId");
			strItemComboOptions = bmedTransBO.getSparePartNameComboOptionsOnBasisOfItemName(Config.SUPER_USER_HOSPITAL_CODE,strItemId);

			
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strItemComboOptions);
		} 
		catch (Exception e) 
		{
			strMsgText = "OfflineSparePartAddTransDATA.getItemName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "	+ e.getMessage();
			HisException eObj = new HisException("bmed", "OfflineSparePartAddTransDATA.getSparePartName()", strMsgText);

			try 
			{
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print("ERROR#### Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");
			} 
			catch (Exception e1)
			{
				new HisException("bmed", "OfflineSparePartAddTransDATA.getSparePartName(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p)",strMsgText);
			}

			eObj = null;
		} 
		finally 
		{
			bmedTransBO = null;
		}
	}
	
	/*
	 * To Get Stock Details Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public static void getStockDetails(	OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedTransBO bmedTransBO = null;
		String strStockDtl = null;
		String strHospitalCode_p;
		String strItemId_p;
		String strDeptId_p;
		UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p = userVo.getHospitalCode();
			strItemId_p = (String) request_p.getParameter("itemNo");
			strDeptId_p = (String) request_p.getParameter("deptId");
			strStockDtl = bmedTransBO.getStockDetails(strHospitalCode_p,strItemId_p, strDeptId_p);

			try 
			{
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strStockDtl);

			} catch (Exception e) {
			//	e.printStackTrace();
			}

		}
		catch (Exception e)
		{

			strMsgText = "OfflineSparePartAddTransDATA.getStockDetails() --> "+ e.getMessage();
			HisException eObj = new HisException("BMED", "OfflineSparePartAddTransDATA", strMsgText);
			offlineSparePartAddTransFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To Get Spare Part Stock Details Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public static void getSparePartStockDetails(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strErrMsg;
		
		String strSparePartStockDetailsTable;
		BmedTransBO bmedTransBO = null;
//		String strHospitalCode;
//		String strSpartPartName;
		String strStockInf;
		HemtItemSparePartDtlVO hemtItemSparePartDtlVO = null;
		// HemtItemMcDtlVO hemtItemMcDtlVO = null;

		//UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();

			hemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
			/*
			 * User Value
			 */
			//userVo = ControllerUTIL.getUserVO(request_p);
			//strHospitalCode = userVo.getHospitalCode();
			
			/*       0                        1                   2
         	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
         	 *              3                   4
         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
         	             5                      6                                 7
                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
         	 */

				strStockInf = (String) request_p.getParameter("strStockInf");

				hemtItemSparePartDtlVO.setStrMode("1");
				hemtItemSparePartDtlVO.setStrStoreId(strStockInf.split("\\^")[0]);
				hemtItemSparePartDtlVO.setStrItemId(strStockInf.split("\\^")[1]);
				hemtItemSparePartDtlVO.setStrItemSlNo(strStockInf.split("\\^")[4]);
				hemtItemSparePartDtlVO.setStrHospitalCode(strStockInf.split("\\^")[5]);
				hemtItemSparePartDtlVO.setStrSpareId("");
				hemtItemSparePartDtlVO.setStrSpareSlNo("");
				hemtItemSparePartDtlVO.setStrSlNo("");
				
				
				bmedTransBO.getSparePartStockDetails(hemtItemSparePartDtlVO);
				
				strSparePartStockDetailsTable = getSparePartStockDetailsTable(hemtItemSparePartDtlVO.getWrsData(),strStockInf);
				
	

				offlineSparePartAddTransFB_p.setStrSparePartStockDetailsTable(strSparePartStockDetailsTable);

				try {
					response_p.setHeader("Cache-Control", "no-cache");
					response_p.getWriter().print(strSparePartStockDetailsTable);
				} 
				catch (Exception e) 
				{
					//e.printStackTrace();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			strErrMsg = "OfflineSparePartAddTransDATA.getSparePartStockDetails() --> "	+ e.getMessage();
			HisException eObj = new HisException("BMED", "OfflineSparePartAddTransDATA", strErrMsg);
			offlineSparePartAddTransFB_p.setStrErrMsg("Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");

			eObj = null;
		}
		
	}
	
	/*
	 * To delete Spare Part Stock Details Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * @param response_p the response
	 * 
	 * @return the null
	 */
	public static void deleteSparePartStockDtl(	OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 
	{
		String strErrMsg;

		String strStockInf;
		String strSparePartStockDetailsTable;
		BmedTransBO bmedTransBO = null;
		String strHospitalCode;
//		String strSpartPartName;
//		String strDeleteId;
		HemtItemSparePartDtlVO hemtItemSparePartDtlVO = null;
		String strSpareSlNo="";
		String strSpareManufSlno="";
		String strSpareId="";
		
		// HemtItemMcDtlVO hemtItemMcDtlVO = null;

		UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();

			hemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			
			/*       0                        1                   2
         	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
         	 *              3                   4
         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
         	             5                      6                                 7
                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
         	 */

				//strDeleteId = (String) request_p.getParameter("checkId");

				strSpareId=(String) request_p.getParameter("spareId");
				strSpareSlNo=(String) request_p.getParameter("spareSerialNo");
				strSpareManufSlno=(String) request_p.getParameter("ManufacturerNo");

				strStockInf = (String) request_p.getParameter("strStockInf");
				
				hemtItemSparePartDtlVO.setStrMode("2");
												
				hemtItemSparePartDtlVO.setStrSpareSlNo(strSpareSlNo);
				hemtItemSparePartDtlVO.setStrSpareManufSlno(strSpareManufSlno);
				hemtItemSparePartDtlVO.setStrSpareId(strSpareId);
				hemtItemSparePartDtlVO.setStrHospitalCode(strHospitalCode);
				
				
				hemtItemSparePartDtlVO.setStrStoreId(strStockInf.split("\\^")[0]);
				hemtItemSparePartDtlVO.setStrItemId(strStockInf.split("\\^")[1]);
				hemtItemSparePartDtlVO.setStrItemSlNo(strStockInf.split("\\^")[4]);
//				hemtItemSparePartDtlVO.setStrHospitalCode(strStockInf.split("\\^")[5]);
//				hemtItemSparePartDtlVO.setStrSpareId(strSpareId);
//				hemtItemSparePartDtlVO.setStrSpareSlNo(strSpareSlNo);
//				hemtItemSparePartDtlVO.setStrSlNo("");
//				
				
				BmedTransBO.saveDataForOfflineSparePartAddTrans(hemtItemSparePartDtlVO);
				

				/*       0                        1                   2
	         	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	         	 *              3                   4
	         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
	         	             5                      6                                 7
	                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
	         	 */

					strStockInf = (String) request_p.getParameter("strStockInf");

					hemtItemSparePartDtlVO.setStrMode("1");
					hemtItemSparePartDtlVO.setStrStoreId(strStockInf.split("\\^")[0]);
					hemtItemSparePartDtlVO.setStrItemId(strStockInf.split("\\^")[1]);
					hemtItemSparePartDtlVO.setStrItemSlNo(strStockInf.split("\\^")[4]);
					hemtItemSparePartDtlVO.setStrHospitalCode(strStockInf.split("\\^")[5]);
					hemtItemSparePartDtlVO.setStrSpareId(strSpareId);
					hemtItemSparePartDtlVO.setStrSpareSlNo(strSpareSlNo);
					hemtItemSparePartDtlVO.setStrSlNo("");
					
					
					bmedTransBO.getSparePartStockDetails(hemtItemSparePartDtlVO);
					
					strSparePartStockDetailsTable = getSparePartStockDetailsTable(hemtItemSparePartDtlVO.getWrsData(),strStockInf);
					
		

					offlineSparePartAddTransFB_p.setStrSparePartStockDetailsTable(strSparePartStockDetailsTable);

					try {
						response_p.setHeader("Cache-Control", "no-cache");
						response_p.getWriter().print(strSparePartStockDetailsTable);
					} 
					catch (Exception e) 
					{
						//e.printStackTrace();
					}

		} catch (Exception e) {
			e.printStackTrace();
			strErrMsg = "OfflineSparePartAddTransDATA.deleteSparePartStockDtl() --> "	+ e.getMessage();
			HisException eObj = new HisException("BMED", "OfflineSparePartAddTransDATA", strErrMsg);
			offlineSparePartAddTransFB_p.setStrErrMsg("Application Error [ERROR ID : "	+ eObj.getErrorID()	+ "],Contact System Administrator! ");

			eObj = null;
		}
		
	}
	
	/*
	 * To Save Data
	 * 
	 * @param offlineSparePartAddTransFB_p the OfflineSparePartAddTransFB
	 * @param request_p the HttpServletRequest
	 * 
	 * @return the null
	 */
	public static void saveData(OfflineSparePartAddTransFB offlineSparePartAddTransFB_p,HttpServletRequest request_p) {
		String strErrMsg;

		String strSeatId;
		String strHospitalCode;
		UserVO userVo = null;
		
		HemtItemSparePartDtlVO hemtItemSparePartDtlVO=null;	 
		try {

			hemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
			
			 /* User Value */
			 
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getSeatId();

			offlineSparePartAddTransFB_p.setStrSeatId(strSeatId);
			offlineSparePartAddTransFB_p.setStrHospitalCode(strHospitalCode);
			offlineSparePartAddTransFB_p.setStrIsValid(Config.IS_VALID_ACTIVE);
			
			// strStockInf value 			 
			 
		 		/*  0                        1                   2
         	 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
         	 *              3                   4
         	   ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
         	             5                      6                                 7
                GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item(1 : for Item 2:for Non-Item)
         	 */
			
			String strManufactNo=offlineSparePartAddTransFB_p.getManufactNo();
			

			// HEMT_ITEM_SAPRE_PART_DTL
			hemtItemSparePartDtlVO.setStrMode("1");
			hemtItemSparePartDtlVO.setStrItemId(offlineSparePartAddTransFB_p.getStrItemId());
			hemtItemSparePartDtlVO.setStrStoreId(offlineSparePartAddTransFB_p.getStrStoreId());
			hemtItemSparePartDtlVO.setStrHospitalCode(offlineSparePartAddTransFB_p.getStrHospitalCode());
			hemtItemSparePartDtlVO.setStrItemSlNo(offlineSparePartAddTransFB_p.getStrStockInfoVal().split("\\^")[4]);
			hemtItemSparePartDtlVO.setStrSpareId(offlineSparePartAddTransFB_p.getStrSparePartId());
			hemtItemSparePartDtlVO.setStrSpareSlNo(offlineSparePartAddTransFB_p.getStrSparePartSerialNo());
			hemtItemSparePartDtlVO.setStrSlNo("");
			hemtItemSparePartDtlVO.setStrItemManufSlno(( strManufactNo==null || strManufactNo.equals("")) ? "NA" : strManufactNo );
			hemtItemSparePartDtlVO.setStrSpareManufSlno(offlineSparePartAddTransFB_p.getStrManufacturerSerialNo());
			hemtItemSparePartDtlVO.setStrVendorId(offlineSparePartAddTransFB_p.getStrManufacturerId());
			hemtItemSparePartDtlVO.setStrWarrantyDate(offlineSparePartAddTransFB_p.getStrWarrantyFromDate());
			hemtItemSparePartDtlVO.setStrWarrantyUpto(offlineSparePartAddTransFB_p.getStrWarrantyUpto());
			hemtItemSparePartDtlVO.setStrWarrantyUptoUnit(offlineSparePartAddTransFB_p.getStrUnitId());
			hemtItemSparePartDtlVO.setStrSpecification(offlineSparePartAddTransFB_p.getStrSpecification());
			hemtItemSparePartDtlVO.setStrIsAdded("1");
			hemtItemSparePartDtlVO.setStrStatus("1");
			hemtItemSparePartDtlVO.setStrActionDate(offlineSparePartAddTransFB_p.getStrPerformedDate()); // Performed Date
			hemtItemSparePartDtlVO.setStrEntryDate("");
			hemtItemSparePartDtlVO.setStrIsvalid(offlineSparePartAddTransFB_p.getStrIsValid());
			hemtItemSparePartDtlVO.setStrSeatid(offlineSparePartAddTransFB_p.getStrSeatId());
			
			
			// Calling BO
			BmedTransBO.saveDataForOfflineSparePartAddTrans(hemtItemSparePartDtlVO);
			
			offlineSparePartAddTransFB_p.setStrSpecification("");
			offlineSparePartAddTransFB_p.setStrWarrantyFromDate("");
			offlineSparePartAddTransFB_p.setStrPerformedDate("");
			offlineSparePartAddTransFB_p.setStrNormalMsg("Data Saved Successfully");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			strErrMsg = "OfflineSparePartAddTransDATA.saveData() --> " + e.getMessage();
			HisException eObj = new HisException("BMED","OfflineSparePartAddTransDATA", strErrMsg);
			offlineSparePartAddTransFB_p.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID()	+ "],Contact System Administrator! ");
				
			eObj = null;
		}
	}
	
	/**
	 * To Get the Spare-Part Stock Details 
	 * 
	 * @param wrsData_p the WebRowSet
	 * @param strStockInf_p the String
	 * 
	 * @return sbHeader+sbBody  the String 
	 */
	private static String getSparePartStockDetailsTable(WebRowSet wrsData_p,String strStockInf_p) throws SQLException,Exception
	{

		StringBuffer sbHeader = new StringBuffer(1000);
		StringBuffer sbBody = new StringBuffer(1000);
		int nWidth;
		int nColspan;
		
		int r=0;
		
		BmedTransBO bmedTransBO = null;
			nWidth = 25;
			nColspan = 4;
		
		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		
		sbHeader.append("<td width=\""	+ nWidth+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Spare-part Name</td>");
		sbHeader.append("<td width=\""	+ nWidth+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Spare-Part Serial No.</td>");
		sbHeader.append("<td width=\""	+ nWidth+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Manufacturer No. </td>");
		sbHeader.append("<td width=\""	+ nWidth+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Action</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			
			/* Result Index */
			// HEMNUM_ITEM_ID:1
			// HEMNUM_STORE_ID:2
			// GNUM_HOSPITAL_CODE:3
			// HEMSTR_ITEM_SL_NO:4
			// HEMNUM_SPARE_ID:5
			// HEMSTR_SPARE_SL_NO:6
			// HEMNUM_SL_NO:7
			// HEMSTR_ITEM_MANUF_SLNO:8
			// HEMSTR_SPARE_MANUF_SLNO:9
			// HEMNUM_VENDOR_ID:10
			// HEMDT_WARRANTY_DATE:11
			// HSTNUM_WARRANTY_UPTO:12
			// HSTNUM_WARRANTY_UPTO_UNIT:13
			// HEMSTR_SPECIFICATION:14
			// HEMNUM_IS_ADDED:15
			// HEMNUM_STATUS:16
			// HEMDT_ACTION_DATE:17
			// GDT_ENTRY_DATE:18
			// GNUM_ISVALID:19
			// GNUM_SEATID:20

			while (wrsData_p.next()) {
				bmedTransBO=new BmedTransBO();
				String strSpareId=wrsData_p.getString("HEMNUM_SPARE_ID");
				
				String strSparePartName = "";
				String strSparePartNo = wrsData_p.getString("HEMSTR_SPARE_SL_NO");
				String strManufacturerNo = wrsData_p.getString("HEMSTR_SPARE_MANUF_SLNO");
				String strAction = "Delete";
				
				String strHospitalCode=wrsData_p.getString("GNUM_HOSPITAL_CODE");

				strSparePartName=bmedTransBO.getSparePartName(Config.SUPER_USER_HOSPITAL_CODE, strSpareId);
				
				if (strSparePartName == null) {
					strSparePartName = "---";
				}
				
				if (strSparePartNo == null) {
					strSparePartNo = "---";
				}
				
				if (strManufacturerNo == null) {
					strManufacturerNo = "";
				}
				if (strSpareId == null) {
					strSpareId = "0";
				}

				/*
				 * Table Body
				 */

							
				sbBody.append("<tr>");
				
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ "<input type=\"hidden\" name=\"strSpareId\" value=\""+strSpareId + "\" />" + strSparePartName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ "<input type=\"hidden\" name=\"strSparePartNo\" value=\"" + strSparePartNo + "\" />" + strSparePartNo + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ "<input type=\"hidden\" name=\"strManufacturerNo\" value=\"" + strManufacturerNo + "\" />" + strManufacturerNo + "</td>");
//				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ "<input type=\"hidden\" id=\"chkId" + r + "\"value=\"\" />" + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style=\"cursor: pointer;\" onclick=\"deleteSparePartStockDetailRecord('"+strStockInf_p+"','"+r+"');\" >" + strAction + "</a></td>");
				r++;
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\""
					+ nColspan
					+ "\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}


}
