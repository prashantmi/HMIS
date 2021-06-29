package bmed.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.EMMSStaticConfigurator;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.masters.bo.ItemMaintenanceMstBO;
import bmed.masters.controller.fb.ItemMaintenanceMstFB;
import bmed.masters.controller.hlp.ItemMaintenanceMstHLP;
import bmed.masters.vo.ItemMaintenanceMstVO;

public class ItemMaintenanceMstDATA 
{
	/**
	 * to display the Item Category combo.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 */
	public static void getAddPageComponent(ItemMaintenanceMstFB formBean,HttpServletRequest request) 
	{
		ItemMaintenanceMstBO bo = null;
		ItemMaintenanceMstVO vo = null;
		HisUtil         hisutil = null;
		String strItemNameCmb;
		String strEnggItemTypeCmb;
		String strMaintenanceCmb;
		String strmsgText;
		String strUnitCmb,strHosCode,strSeatid,ctDate,strComboVal;
		String strCombo[] = null;
		try 
		{
			      hisutil = new HisUtil("bmed", "ItemMaintenanceMstDATA");
			           bo = new ItemMaintenanceMstBO();
			           vo = new ItemMaintenanceMstVO();
			       ctDate = hisutil.getASDate("dd-MMM-yyyy");
			
			   strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			    strSeatid = request.getSession().getAttribute("SEATID").toString();
			  strComboVal = request.getParameter("comboValue").toString();
			     strCombo = request.getParameterValues("combo");
			     
			formBean.setStrCtDate(ctDate);
			formBean.setStrHospitalCode(strHosCode);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			    
			formBean.setStrStoreId(strCombo[0]);  // This Variable working as Department Code
			formBean.setStrIsValid(strCombo[1]);
			
			vo.setStrStoreId(strCombo[0]);
			vo.setStrIsValid(strCombo[1]);
			
			formBean.setStrStoreIdValue(strCombo[0]);
			formBean.setComboValue(strComboVal);
			formBean.setCombo(strCombo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
            // Calling BO Method
			bo.getAddPageComponent(vo);
			
			if (vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{

					if (vo.getWsItemCatgName() != null && vo.getWsItemCatgName().size() > 0) 
					{
						strItemNameCmb = hisutil.getOptionValue(vo.getWsItemCatgName(), ""	, "0^Select Value", false);
					} 
					else 
					{
						strItemNameCmb = "<option value='0'>Select Value</option>";
					}
					
					if (vo.getWsEnggItemType() != null && vo.getWsEnggItemType().size() > 0) 
					{
						strEnggItemTypeCmb = hisutil.getOptionValue(vo.getWsEnggItemType(), ""	, "0^Select Value", false);
					} 
					else 
					{
						strEnggItemTypeCmb = "<option value='0'>Select Value</option>";
					}
					
					if (vo.getWsMaintenanceCmb() != null && vo.getWsMaintenanceCmb().size() > 0) 
					{
						strMaintenanceCmb = hisutil.getOptionValue(vo.getWsMaintenanceCmb(), ""	, "0^Select Value", false);
					} 
					else 
					{
						strMaintenanceCmb = "<option value='0'>Select Value</option>";
					}
					
					if (vo.getWsUnitName() != null && vo.getWsUnitName().size() > 0) 
					{
						strUnitCmb = hisutil.getOptionValue(vo.getWsUnitName(), ""	, "0^Select Value", false);
					} 
					else 
					{
						strUnitCmb = "<option value='0'>Select Value</option>";
					}
					
					
					
					if (vo.getStrMsgType().equals("1")) 
					{
						throw new Exception(vo.getStrMsgString());
					}
					formBean.setStrItemCategoryCmb(strItemNameCmb);
					formBean.setStrStoreName(vo.getStrStoreName());
					formBean.setStrEnggItemTypeCmb(strEnggItemTypeCmb);
					//formBean.setStrMaintenanceCmb(strMaintenanceCmb);
					formBean.setStrUnitIdCmb(strUnitCmb);

		     }
		   }
			catch (Exception e) 
			{
           e.printStackTrace();
			strmsgText = "ItemMaintenanceMstDATA.initialAdd(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ItemMaintenanceMstDATA->initialAdd()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			hisutil = null;
		}

	}
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getItemNameCmb(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) 
	{
		ItemMaintenanceMstVO vo = null;
		ItemMaintenanceMstBO bo = null;
		HisUtil         hisutil = null;
		String strHosCode;
		String strSeatid;
		String strItemCatgId;
		String strItemNameCmb;
		String strStoreId;
		try 
		{
			      hisutil = new HisUtil("bmed","ItemMaintenanceMstDATA");
			           vo = new ItemMaintenanceMstVO();
			           bo = new ItemMaintenanceMstBO();
		
			   strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			    strSeatid = request.getSession().getAttribute("SEATID").toString();
			strItemCatgId = (String) request.getParameter("itemCatNo");
			   strStoreId = (String) request.getParameter("storeId");   
			
			vo.setStrStoreId(strStoreId);
			vo.setStrItemCatgId(strItemCatgId);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getItemNameCmb(vo);			
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			if(vo.getWsItemName()!=null	&& vo.getWsItemName().size() > 0)
			{			
				strItemNameCmb = hisutil.getOptionValue(vo.getWsItemName(),	"", "0^Select Value", true);
			}
			else 
			{
				strItemNameCmb = "<option value='0'>Select Value</option>";
			}
			
			try 
			{
				
				  response.setHeader("Cache-Control", "no-cache");
				  response.getWriter().print(strItemNameCmb);
					
				}catch(Exception e){
					
				}
			
									
		} catch (Exception e) {
		
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getItemNameCmb()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			hisutil = null;
		}
	}
	
	
	
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getStockDtl(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) {
		ItemMaintenanceMstVO vo = null;
		ItemMaintenanceMstBO bo = null;
		String strHosCode;
		String strSeatid;
		String strItemid;
		String strStockDtl;
		String strItemCatgId;
		String strDeptId;
		try 
		{
			      
			           vo = new ItemMaintenanceMstVO();
			           bo = new ItemMaintenanceMstBO();
			   strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			    strSeatid = request.getSession().getAttribute("SEATID").toString();
			    strItemid = (String) request.getParameter("itemNo");
			strItemCatgId = (String) request.getParameter("itemCatNo");
			    strDeptId = (String) request.getParameter("deptId");
			
			vo.setStrStoreId(strDeptId);     // This Variable Used as Dep Code
			vo.setStrItemCatgId(strItemCatgId);
			vo.setStrItemid(strItemid);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getStockDtl(vo);	//In This method we call [ Stock Details + Selected Task Details List Box ] on the basis of Item Brand Id
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			strStockDtl = ItemMaintenanceMstHLP.getStockDetails(vo.getWsBatchName());
			// Here vo.getWsBatchName() carry Stock details Information
						
			try {									
				response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strStockDtl);
					
				}catch(Exception e){
					
				}
			
									
		} catch (Exception e) 
		{
		    e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getStockDtl()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			
		}
	}
	
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getWarrantyMaintenanceDtl(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) 
	{
		ItemMaintenanceMstVO vo = null;
		ItemMaintenanceMstBO bo = null;
		String strHosCode;
		String strSeatid;
		String strBatchNo;		
		String strItemSlNo;
		String strItemId;
		String strItemBrandId;
		String strWarrantyData;
		String strMainteContractData;
		try 
		{
			      
			           vo = new ItemMaintenanceMstVO();
			           bo = new ItemMaintenanceMstBO();
			   strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			    strSeatid = request.getSession().getAttribute("SEATID").toString();
			   strBatchNo = (String) request.getParameter("batchNo");
			  strItemSlNo = (String) request.getParameter("itemSlNo");
			    strItemId = (String) request.getParameter("itemId");
		   strItemBrandId = (String) request.getParameter("itemBrandId");
			
			vo.setStrBatchNo(strBatchNo);
			vo.setStrSerialNo(strItemSlNo);
			vo.setStrItemid(strItemId);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			vo.setStrItemBrandId(strItemBrandId);
			
			// Calling BO Method	
			bo.getWarrantyMaintenanceDtl(vo);	//In This method we get [ Warranty Details + Maintenance Detail ]			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if(vo.getWsWarrantyDtl().size()>0) 
			{
				
				strWarrantyData = ItemMaintenanceMstHLP.getWarrantyData(vo.getWsWarrantyDtl());
			}
			else
			{
				strWarrantyData="";
			}	
			if(vo.getWsMaintenanceDtl().size()>0) 
			{
				
				strMainteContractData = ItemMaintenanceMstHLP.getMainteContractData(vo.getWsMaintenanceDtl());
			}
			else
			{
				strMainteContractData="";
			}	
						
			try 
			{
				
				response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strWarrantyData+"$$$$"+strMainteContractData);
					
				}catch(Exception e){
					
				}
			
									
		} catch (Exception e) {
		    e.printStackTrace();
		    String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getStockDtl()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			
		}
	}
	
	
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getMaintenanceCmb(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) {
		ItemMaintenanceMstVO vo = null;
		ItemMaintenanceMstBO bo = null;
		HisUtil         hisutil = null;
		String strHosCode;
		String strSeatid;
		String strDeptId;
		String strStockInfo;
		String strItemId;
    	String strMantCmb;
		try 
		{
			     hisutil = new HisUtil("bmed","ItemMaintenanceMstDATA");
			          vo = new ItemMaintenanceMstVO();
			          bo = new ItemMaintenanceMstBO();
		
			    strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			     strSeatid = request.getSession().getAttribute("SEATID").toString();
			  strStockInfo = (String) request.getParameter("stockInfo");
			     strItemId = (String) request.getParameter("itemId");
                 strDeptId = (String) request.getParameter("deptId");
           /*
            * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	          ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
              GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
            */
            String[] arr = strStockInfo.split("\\^");
			vo.setStrStoreId(arr[0]);
			vo.setStrBatchNo(arr[3]);
			vo.setStrSerialNo(arr[4]);
			vo.setStrDeptID(strDeptId);
			vo.setStrItemBrandId(strItemId);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getMaintenanceNameCmb(vo);			
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			
			if(vo.getWsMaintenanceCmb()!=null && vo.getWsMaintenanceCmb().size() > 0){			
				strMantCmb = hisutil.getOptionValue(vo.getWsMaintenanceCmb(), "", "0^Select Value", true);
			}
			else {
				strMantCmb = "<option value='0'>Select Value</option>";
			}
			
			
			try 
			{									
				response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strMantCmb);
					
				
			}
			catch(Exception e){	}
			
									
		} catch (Exception e) {
		
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getMaintenanceCmb()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			hisutil = null;
		}
	}
	
	
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getItemSlNoCmbBasisOfItem(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) {
		ItemMaintenanceMstVO vo = null;
		ItemMaintenanceMstBO bo = null;
		HisUtil         hisutil = null;
		String strHosCode;
		String strSeatid;
		String strItemid;
		String strItemSlNoCmb;
		String strItemCatgId;
		try 
		{
			      hisutil = new HisUtil("bmed","ItemMaintenanceMstDATA");
			           vo = new ItemMaintenanceMstVO();
			           bo = new ItemMaintenanceMstBO();
			   strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			    strSeatid = request.getSession().getAttribute("SEATID").toString();
			    strItemid = (String) request.getParameter("itemNo");
            strItemCatgId = (String) request.getParameter("itemCatNo");
			
			vo.setStrItemCatgId(strItemCatgId);
			vo.setStrItemid(strItemid);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getItemSlNoCmbBasisOfItem(vo);			
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if(vo.getWsItemSlNo()!=null
					&& vo.getWsItemSlNo().size() > 0){			
				strItemSlNoCmb = hisutil.getOptionValue(vo.getWsItemSlNo(),
					"", "0^Select Value", true);
			}else {
				strItemSlNoCmb = "<option value='0'>All Value</option>";
			}
			
			try {									
				response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strItemSlNoCmb);
					
				}catch(Exception e){
					
				}
			
									
		} catch (Exception e) {
		
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getItemNameCmb()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			hisutil = null;
		}
	}
	
	
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getEnggItemSubTypeOnBasisOfEnggItemType(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) {
		ItemMaintenanceMstVO vo=null;
		ItemMaintenanceMstBO bo= null;
		HisUtil hisutil = null;
		String strHosCode;
		String strSeatid;
		String strEnggItemTypeId;
		String strEnggItemSubTypeCmb = "";
		try 
		{
			hisutil = new HisUtil("bmed","ItemMaintenanceMstDATA");
			vo = new ItemMaintenanceMstVO();
			bo = new ItemMaintenanceMstBO();
		
			strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			strSeatid = request.getSession().getAttribute("SEATID").toString();
			strEnggItemTypeId = (String) request.getParameter("enggItemType");
			
			vo.setStrEnggItemTypeId(strEnggItemTypeId);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getEnggItemSubTypeOnBasisOfEnggItemType(vo);			
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if(vo.getWsEnggItemSubType()!=null&& vo.getWsEnggItemSubType().size() > 0)
			{			
				strEnggItemSubTypeCmb = hisutil.getOptionValue(vo.getWsEnggItemSubType(),"", "0^Select Value", true);
			}
			else 
			{
				strEnggItemSubTypeCmb = "<option value='0'>Select Value</option>";
			}
			
			try {									
				response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strEnggItemSubTypeCmb);
					
				}
			    catch(Exception e)
				{
//					e.printStackTrace();
				}
			
									
		} catch (Exception e) {
		
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getItemNameCmb()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			hisutil = null;
		}
	}
	
	
	
	/**
	 * This function is used to display Group Name on the basis of ItemCategory Name: 
	 * @param formBean
	 */
	public static void getLeftListBoxValue(ItemMaintenanceMstFB formBean,HttpServletRequest request,
			HttpServletResponse response) 
	{
		ItemMaintenanceMstVO vo = null;
		ItemMaintenanceMstBO bo = null;
		HisUtil         hisutil = null;
		String strHosCode;
		String strSeatid;
		String strMainTenanceId;
		String strLeftListBox;
		String strRightListBox;
		String strStockInfo;
		String strItemId;
		String strDeptId;
		try 
		{
					 hisutil = new HisUtil("bmed","ItemMaintenanceMstDATA");
					      vo = new ItemMaintenanceMstVO();
					      bo = new ItemMaintenanceMstBO();
		
			      strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			       strSeatid = request.getSession().getAttribute("SEATID").toString();
			strMainTenanceId = (String) request.getParameter("mainTenanceId");
			
			strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
		     strSeatid = request.getSession().getAttribute("SEATID").toString();
		  strStockInfo = (String) request.getParameter("stockInfo");
		     strItemId = (String) request.getParameter("itemId");
             strDeptId = (String) request.getParameter("deptId");
      /*
       * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
         ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
         GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
       */
	        String[] arr = strStockInfo.split("\\^");
			
	        
			vo.setStrBatchNo(arr[3]);
			vo.setStrSerialNo(arr[4]);
			vo.setStrDeptID(strDeptId);
			vo.setStrItemBrandId(strItemId);
			vo.setStrMaintenanceId(strMainTenanceId);
			vo.setStrHospitalCode(strHosCode);
			vo.setStrSeatId(strSeatid);
			
			// Calling BO Method	
			bo.getLeftListBoxValue(vo);			
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			
			if(vo.getWsLeftListBox()!=null&& vo.getWsLeftListBox().size() > 0)
			{			
				strLeftListBox = hisutil.getOptionValue(vo.getWsLeftListBox(),"", "", true);
			}
			else 
			{
				strLeftListBox = "";
			}
			
			if(vo.getWsRightListBox()!=null&& vo.getWsRightListBox().size() > 0)
			{			
				strRightListBox = hisutil.getOptionValue(vo.getWsRightListBox(),"", "", true);
			}
			else 
			{
				strRightListBox = "";
			}
			
			try {									
				response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strLeftListBox+"@@"+strRightListBox);
					
				}catch(Exception e){
					
				}
			
									
		} catch (Exception e) {
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("bmed", "ItemMaintenanceMstDATA->getItemNameCmb()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print("ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID() + "],Contact System Administrator! ");

		     } catch (Exception e1) {
				
			}
			eObj = null;
		}
		finally {
			if(vo != null) vo = null;
			if(formBean != null) formBean = null;
			hisutil = null;
		}
	}
	
	/**
	 * to insert the data
	 * 
	 * @param formBean
	 * @param request
	 */
	public static void insertRecord(ItemMaintenanceMstFB formBean,
			HttpServletRequest request) 
	{
		ItemMaintenanceMstBO bo = null;
		ItemMaintenanceMstVO vo = null;
		String strmsgText,strComboVal;

		try 
		{
			          bo = new ItemMaintenanceMstBO();
			          vo = new ItemMaintenanceMstVO();
             strComboVal = request.getParameter("comboValue").toString();
						
			formBean.setComboValue(strComboVal);
			
			vo.setStrStoreId(formBean.getStrStoreIdValue());
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());          
            			
	      /*
	       * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
	         ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
	         GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
	       */
	       String[] arr = formBean.getStrStockInfoVal().split("\\^");

	       formBean.setStrBatchNo(arr[3]);
	       formBean.setStrSerialNo(arr[4]);
	       		
			vo = (ItemMaintenanceMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ItemMaintenanceMstVO", formBean);

			// Calling BO Method
			bo.insertRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			if (vo.getBExistStatus()== false) 
			{
				formBean.setStrWarning("Task Name already exist for the Maintenance Name!");
			}
			else 
			{
				formBean.setStrMsgString("Record saved successfully!");
				formBean.setStrStoreId(vo.getStrStoreId());
			}
			

		} 
		catch (Exception e) 
		{
           e.printStackTrace();
			strmsgText = "SupplierItemMaster.ItemMaintenanceMstDATA.insertRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ItemMaintenanceMstDATA->insertRecord()", strmsgText);
			formBean.setStrErr("Exception while saving record! Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}

	}
	
	/**
	 * Modify.
	 * 
	 * @param request the request
	 * @param formBean the form bean
	 */
	public static void modify(HttpServletRequest request, ItemMaintenanceMstFB formBean) 
	{
		ItemMaintenanceMstBO bo = null;
		ItemMaintenanceMstVO vo = null;
		String           temp[] = null;
		String strChk,strUnitCmb;
		HisUtil hisutil;
		String strLeftListBox,strRightListBox;
		
		try
		{
				 vo = new ItemMaintenanceMstVO();
				 bo = new ItemMaintenanceMstBO();
			hisutil = new HisUtil("bmed","ItemMaintenanceMstDATA");
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
			if (request.getParameter("chk") != null) 
			{            
				// CHK Value :::18100001@10102101@0@1001@0@1001@108$1
				// A.HEMNUM_DEPT_ID @ A. HEMNUM_ITEMBRAND_ID @ A.HEMSTR_BATCH_NO @ A.HEMSTR_SERIAL_NO @ A.HEMNUM_MAINTE_ID @ A.GNUM_HOSP_CODE
				
				strChk = request.getParameter("chk");
				strChk = strChk.replace("$", "@");
				
				temp = strChk.split("@");
				vo.setStrStoreId(temp[0]);
				vo.setStrItemid(temp[1]);
				vo.setStrBatchNo(temp[2]);
				vo.setStrSerialNo(temp[3]);
				vo.setStrMaintenanceId(temp[4]);
				vo.setStrHospitalCode(temp[5]);
				vo.setStrDeptID(temp[0]);
				vo.setStrItemBrandId(temp[1]);
				vo.setStrIsValid("1");
				formBean.setStrChk(strChk);
			} 
			else 
			{
				strChk = formBean.getStrChk();
				strChk = strChk.replace("$", "@");
				
				temp = strChk.split("@");
				  
				vo.setStrStoreId(temp[0]);
				vo.setStrItemid(temp[1]);
				vo.setStrBatchNo(temp[2]);
				vo.setStrSerialNo(temp[3]);
				vo.setStrMaintenanceId(temp[4]);
				vo.setStrHospitalCode(temp[5]);
				vo.setStrDeptID(temp[0]);
				vo.setStrItemBrandId(temp[1]);
				formBean.setStrChk(strChk);
			}			
			// Calling BO Method 
			 bo.modify(vo);
			 bo.getLeftListBoxValue(vo);
			
			   
			   
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				
			  if(!formBean.getStrGetView().equals("1"))
			  {	  
				
				if(vo.getWsLeftListBox()!=null&& vo.getWsLeftListBox().size() > 0)
				{			
					strLeftListBox = hisutil.getOptionValue(vo.getWsLeftListBox(),"", "", true);
				}
				else 
				{
					strLeftListBox = "";
				}
				
				if(vo.getWsRightListBox()!=null&& vo.getWsRightListBox().size() > 0)
				{			
					strRightListBox = hisutil.getOptionValue(vo.getWsRightListBox(),"", "", true);
				}
				else 
				{
					strRightListBox = "";
				}
				formBean.setStrStoreName(vo.getStrStoreName());
				formBean.setStrItemNameCmb(vo.getStrItemNameCmb());
				formBean.setStrEnggItemSubTypeCmb(vo.getStrEnggItemSubTypeCmb());
				formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
				formBean.setStrMaintenanceCmb(vo.getStrMaintenanceCmb());
				formBean.setStrLeftItemList(strLeftListBox);
				formBean.setStrRightItemList(strRightListBox);
				formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
				formBean.setStrIsValid(vo.getStrIsValid());
				formBean.setStrRemarks(vo.getStrRemarks());
				formBean.setStrFromHour(vo.getStrFromHour());
				formBean.setStrFromMin(vo.getStrFromMin());
				formBean.setStrToHour(vo.getStrToHour());
				formBean.setStrToMin(vo.getStrToMin());
				formBean.setStrMaintenanceDesc(vo.getStrMaintenanceDesc());
				formBean.setStrMaintenancePeriod(vo.getStrMaintenancePeriod());
				formBean.setStrMaintenancePeriodUnit(vo.getStrMaintenancePeriodUnit());
				formBean.setStrMaintenancePeriodInDays(vo.getStrAlertDuration());
				if (vo.getWsUnitName() != null && vo.getWsUnitName().size() > 0) 
				{
					strUnitCmb = hisutil.getOptionValue(vo.getWsUnitName(), vo.getStrMaintenancePeriodUnit(), "0^Select Value", false);
				} 
				else 
				{
					strUnitCmb = "<option value='0'>Select Value</option>";
				}
				formBean.setStrUnitIdCmb(strUnitCmb);
			  }
			  else
			  {
				    bo.View(vo);
				    
				    String strTaskHlp = ItemMaintenanceMstHLP.getTaskDetails(vo.getWsRightListBox());
				    formBean.setStrTaskHlp(strTaskHlp);
				    formBean.setStrItemNameCmb(vo.getStrItemNameCmb());
				    formBean.setStrEnggItemSubTypeCmb(vo.getStrEnggItemSubTypeCmb());
					formBean.setStrEnggItemTypeCmb(vo.getStrEnggItemTypeCmb());
					formBean.setStrMaintenanceCmb(vo.getStrMaintenanceCmb());
					formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
					formBean.setStrMaintenancePeriod(vo.getStrMaintenancePeriod());
					formBean.setStrMaintenancePeriodInDays(vo.getStrMaintenancePeriodInDays());
					formBean.setStrPreferTimeFrom(vo.getStrPreferTimeFrom());
					formBean.setStrPreferTimeTo(vo.getStrPreferTimeTo());
					formBean.setStrMaintenanceDesc(vo.getStrMaintenanceDesc());
					formBean.setStrEffectiveFrom(vo.getStrEffectiveFrom());
					formBean.setStrRemarks(vo.getStrRemarks());
					formBean.setStrIsValid(vo.getStrIsValid());
					formBean.setStrStoreName(vo.getStrStoreName());
					
				  
			  }	  
								
				
				//getLotsDtl
				
				if(vo.getStrBatchNo().equals("0"))
				{
					 formBean.setStrBatchNoCmb("----");
				}
				else
				{	
				    formBean.setStrBatchNoCmb(vo.getStrBatchNo());
				} 
				if(vo.getStrSerialNo().equals("0"))
				{
					formBean.setStrSerialNoCmb("----");
				}
				else
				{	
					formBean.setStrSerialNoCmb(vo.getStrSerialNo());
				} 
				
				
	
				
			}
		} catch (Exception e) {
//			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("mms",
					"ItemMaintenanceMstDATA->modify()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			bo = null;
			vo = null;
			
		}
	}

	
	/**
	 * to update the record.
	 * 
	 * @param formBean the form bean
	 * @param request the request
	 * 
	 * @return true, if update record
	 */
	public static boolean updateRecord(ItemMaintenanceMstFB formBean,HttpServletRequest request)
	{
		ItemMaintenanceMstBO bo = null;
		ItemMaintenanceMstVO vo = null;
		boolean        retValue = true;
		String           temp[] = null;
		String strmsgText;
		String strChk,strHosCode,strSeatid;
		try 
		{
			                bo = new ItemMaintenanceMstBO();
			                vo = new ItemMaintenanceMstVO();
			        strHosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			         strSeatid = request.getSession().getAttribute("SEATID").toString();
						strChk = formBean.getStrChk();
						strChk = strChk.replace("$", "@");
						  temp = strChk.split("@");
				formBean.setStrStoreId(temp[0]);
				formBean.setStrItemid(temp[1]);
				formBean.setStrBatchNo(temp[2]);
				formBean.setStrSerialNo(temp[3]);
				formBean.setStrMaintenanceId(temp[4]);
				formBean.setStrHospitalCode(temp[5]);
				
				formBean.setStrChk(strChk);
			
			formBean.setStrHospitalCode(strHosCode);
			formBean.setStrSeatId(strSeatid);			
			
			vo = (ItemMaintenanceMstVO) TransferObjectFactory.populateData("bmed.masters.vo.ItemMaintenanceMstVO", formBean);
			// Calling BO Method
			bo.updateRecord(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			else 
			{
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				} 
				else 
				{
					formBean.setStrMsg("Record Modify Successfully");
				}
			}

		} 
		catch (Exception e) 
		{

			strmsgText = "ItemMaintenanceMstDATA.updateRecord(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ItemMaintenanceMstDATA->updateRecord()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
			retValue = false;

			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
//		System.out.println("O/p of Re:::::"+retValue);
		return retValue;
	}
	
	
	/**
	 * To get Uploaded File
	 * 
	 * @param itemMaintenanceMstFB_p
	 * @param request_p
	 * @param response_p
	 */
	public static void getUploadedFile(ItemMaintenanceMstFB itemMaintenanceMstFB_p,HttpServletRequest request_p, HttpServletResponse response_p)
	{			
				String strmsgText = null;
				String strFileName = "";
				File f = null;
				FileInputStream fis = null;
				byte[] fileContent = new byte[1024];
				BmedConfigUtil bmed =null;
				String strFtpFolderName ="";
				UserVO userVo = null;
				
				try 
				{
					   
					   bmed = new BmedConfigUtil();
					   userVo = ControllerUTIL.getUserVO(request_p);
					   strFtpFolderName = bmed.getStrFtpFileFolder("15", userVo.getHospitalCode());
				       
					  
					   
					   if(strFtpFolderName.equals("")||strFtpFolderName==null)
					   {
						    strFtpFolderName = "bmedDOCS";
					   } 
					
					
					strFileName = itemMaintenanceMstFB_p.getStrUploadFileId();
								
					//System.out.println("File Name::::::"+ItemWarrantyDtlsFB_p.getStrUploadFileId());
					String[] strTemp = strFileName.replace(".", "#").split("#");
					String strExt = strTemp[strTemp.length - 1];
					
				
					 if (strExt.equalsIgnoreCase("txt")
								|| strExt.equalsIgnoreCase("txt")) {
							
							response_p.setContentType("application/txt");
							response_p.setHeader("Content-disposition",
									" filename="+strFileName);
							
						}
						 else if (strExt.equalsIgnoreCase("pdf")) 
						{
			                
							response_p.setContentType("application/pdf");
							response_p.setHeader("Content-disposition",	"attachment; filename="+strFileName);

						} else if (strExt.equalsIgnoreCase("html")
								|| strExt.equalsIgnoreCase("htm")) {
							
							response_p.setContentType("text/html;charset=utf-8");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						}else if (strExt.equalsIgnoreCase("xml")) {
							
							response_p.setContentType("application/xml");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
							
							response_p.setContentType("application/msword");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						} else if (strExt.equalsIgnoreCase("rdf")) {
							
							response_p.setContentType("application/msword");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						} else if (strExt.equalsIgnoreCase("rtf")) {
							
							response_p.setContentType("application/msword");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						} else if(strExt.equalsIgnoreCase("png")){

							response_p.setContentType("image/png");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						} else if(strExt.equalsIgnoreCase("gif")){

							response_p.setContentType("image/gif");
							response_p.setHeader("Content-disposition",
								"attachment; filename="+strFileName);
						
						} else if(strExt.equalsIgnoreCase("jpeg") || strExt.equalsIgnoreCase("jpg")){
				
							response_p.setContentType("image/jpg");
							response_p.setHeader("Content-disposition",
								"attachment; filename="+strFileName);
						
						} else {

							response_p.setContentType("text/html;charset=utf-8");
							response_p.setHeader("Content-disposition",
									"attachment; filename="+strFileName);
							
						}
					/*******************************************************************/

					  String sessionFtpAddress= EMMSStaticConfigurator.bmedpath;//"10.0.5.152/ftpserver"; //populate from session
					  //String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
					  String Fileurl= "ftp://"+sessionFtpAddress+"/"+"/"+strFtpFolderName;			
					 
					 
					  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
					  URLConnection          urlc =	urlftp.openConnection();
					  InputStream              io = urlc.getInputStream();
					  		  
					 
					        FileOutputStream fos = new FileOutputStream(strFileName);
					        byte[] buf = new byte[4096];
					        int read = 0;
					        while ((read = io.read(buf)) > 0) 
					        {
					            fos.write(buf, 0, read);
					        }	    				  				  	  
					  
					     f = new File(strFileName);

						if (!f.isFile()) 
						{

							throw new Exception("Invalid File Path");
						}

						fis = new FileInputStream(f);

						while (fis.read(fileContent) != -1) 
						{
			                
							response_p.getOutputStream().write(fileContent);
						}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					strmsgText = "bmed.master.PreTechApprovalTransDATA.getUploadedFile --> "
							+ e.getMessage();
					HisException eObj = new HisException("bmed",
							"PreTechApprovalTransDATA->getUploadedFile()", strmsgText);
					itemMaintenanceMstFB_p.setStrErr("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
			
					eObj = null;
				} finally {
			
					if (f != null)
						f = null;
					if (fis != null)
						fis = null;
				}
			}

}
