package mms.transactions.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.MmsConfigUtil;
import mms.transactions.bo.TransferRequestTransBO;
import mms.transactions.controller.fb.TransferRequestTransFB;
import mms.transactions.controller.hlp.TransferRequestTransHLP;
import mms.transactions.vo.TransferRequestTransVO;

public class TransferRequestTransDATA 
{
	 public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	  public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
	    
	  public static  String now(String frmt)
	    {
	      HisUtil util=null;
	      String a="";
	      util=new HisUtil("transaction","TransferRequestTransHLP");
	      try{
	       a= util.getASDate(frmt);
	      }
	      catch(Exception e){
	    	
	      }
	      /*Calendar cal = Calendar.getInstance();
	      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	      return sdf.format(cal.getTime());*/
	      return a;
	    }
	/**
	 * to display the Store Name and Group Name on Add page
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void initialAdd(TransferRequestTransFB formBean,
			HttpServletRequest request) {

		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;
		String strmsgText = "";
		try 
		{			
			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();		
			
			String strStoreName = request.getParameter("comboValue");			
			String strComboValues[] = request.getParameterValues("combo");
			formBean.setStrStoreName(strStoreName);			
			String strStoreId = formBean.getCombo()[0];
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			formBean.setStrDefaultCurrencyCode(MmsConfigUtil.DEFAULT_CURRENCY_CODE);
			// Calling BO Method
			bo.initialAdd(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
			formBean.setStrGroupNameCombo(vo.getStrGroupNameCombo());
			formBean.setStrSubGroupCombo(vo.getStrSubGroupCombo());
			formBean.setStrItemNameCombo(vo.getStrItemNameCombo());
			formBean.setStrApprovedByCombo(vo.getStrApprovedByCombo());
			formBean.setStrReqDate(now(DATE_FORMAT_NOWwt));
			formBean.setStrCtDate(now(DATE_FORMAT_NOW));
					

		} 
		catch (Exception e) 
		{          
			strmsgText = "Drug Inventory.TransferRequestTransDATA.initialAdd(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms",	"TransferRequestTransDATA->initialAdd()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "	+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			
		}

	}
	
	/**
	 * to display the Store Name and Group Name on Add page
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void initModify(TransferRequestTransFB formBean,
			HttpServletRequest request) {

		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;
		String strmsgText = "";
		try 
		{			
			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();		
			
			String strStoreName = request.getParameter("comboValue");			
			String strComboValues[] = request.getParameterValues("combo");
			formBean.setStrStoreName(strStoreName);			
			String strStoreId = formBean.getCombo()[0];
			
           String strChk = formBean.getStrChk();		
		   
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(strStoreId);
			vo.setStrRequestNo(strChk.split("\\$")[0]);
			vo.setStrItemCategoryNo("10");
			vo.setStrChk(strChk);
			// Calling BO Method
			bo.initialModify(vo);
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
			else
			{
				while(vo.getStrModifyDtlWS().next())
				{
					formBean.setStrRequestNoView(vo.getStrRequestNo());
					formBean.setStrRequestDateView(vo.getStrModifyDtlWS().getString(1));
					formBean.setStrItemNameView(vo.getStrModifyDtlWS().getString(2));
					formBean.setStrReqQtyView(vo.getStrModifyDtlWS().getString(3));
					formBean.setStrUnitNameView(vo.getStrModifyDtlWS().getString(4));
					formBean.setStrGroupNameView(vo.getStrModifyDtlWS().getString(5));
					formBean.setStrStoreNameView(vo.getStrModifyDtlWS().getString(6));	
					formBean.setStrApprovedByView(vo.getStrModifyDtlWS().getString(7));
					formBean.setStrApprovedDateView(vo.getStrModifyDtlWS().getString(8));
					formBean.setStrSubGroupNameView(vo.getStrModifyDtlWS().getString(10));
					
				}
			}			
			formBean.setStrApprovedByCombo(vo.getStrApprovedByCombo());
			formBean.setStrReqDate(now(DATE_FORMAT_NOWwt));
			formBean.setStrCtDate(now(DATE_FORMAT_NOW));
			formBean.setStrModifyChk(vo.getStrChk());
			formBean.setStrChk(vo.getStrChk());
			
					

		} 
		catch (Exception e) 
		{          
			strmsgText = "Drug Inventory.TransferRequestTransDATA.initModify(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms",	"TransferRequestTransDATA->initModify()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "	+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			
		}

	}
	
	/**
	 * to display the Store Name and Group Name on Add page
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void initView(TransferRequestTransFB formBean,HttpServletRequest request) 
	{

		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;
		String strmsgText = "";
		String path="";
		try 
		{			
			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();		
			
			String strStoreName = request.getParameter("comboValue");			
			String strComboValues[] = request.getParameterValues("combo");
			formBean.setStrStoreName(strStoreName);		
			
           String strChk = formBean.getStrChk();
           path = "/mms"+request.getParameter("cnt_page")+".cnt";
			
			if(request.getParameter("cnt_page") == null)
			{
				
				path = request.getParameter("strPath");
			}
			
			formBean.setStrPath(path.trim());
           
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrRequestNo(strChk.split("\\$")[0]);
			vo.setStrItemCategoryNo("10");
			
			// Calling BO Method
			bo.initialView(vo);
			
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
			else
			{
				while(vo.getStrModifyDtlWS() != null && vo.getStrModifyDtlWS().next())
				{
					formBean.setStrRequestNoView(vo.getStrRequestNo());
					formBean.setStrRequestDateView(vo.getStrModifyDtlWS().getString(1));
					formBean.setStrItemNameView(vo.getStrModifyDtlWS().getString(2));
					formBean.setStrReqQtyView(vo.getStrModifyDtlWS().getString(3));
					formBean.setStrUnitNameView(vo.getStrModifyDtlWS().getString(4));
					formBean.setStrGroupNameView(vo.getStrModifyDtlWS().getString(5));
					formBean.setStrStoreNameView(vo.getStrModifyDtlWS().getString(6));						
					formBean.setStrApprovedByView(vo.getStrModifyDtlWS().getString(7));
					formBean.setStrApprovedDateView(vo.getStrModifyDtlWS().getString(8));
					formBean.setStrApprovedStatusView(vo.getStrModifyDtlWS().getString(9));					
					formBean.setStrReqQtyWithUnitView(vo.getStrModifyDtlWS().getString(10));
					formBean.setStrApprovedQtyWithUnitView(vo.getStrModifyDtlWS().getString(11));
					formBean.setStrAckQtyWithUnitView(vo.getStrModifyDtlWS().getString(12));
					formBean.setStrRaisingAvlQtyWithUnitView(vo.getStrModifyDtlWS().getString(13));
					formBean.setStrAckAvlQtyWithUnitView(vo.getStrModifyDtlWS().getString(14));
					formBean.setStrRaisingRemarksView(vo.getStrModifyDtlWS().getString(15));
					formBean.setStrOrderBySeatIdView(vo.getStrModifyDtlWS().getString(16));
					formBean.setStrOrderByDateView(vo.getStrModifyDtlWS().getString(17));
					formBean.setStrOrderRemarksView(vo.getStrModifyDtlWS().getString(18));
					formBean.setStrStatusView(vo.getStrModifyDtlWS().getString(19));					
				}
				formBean.setWbTransferOrderDetail(vo.getWbTransferOrderDetail());
			}			
			formBean.setStrCtDate(now(DATE_FORMAT_NOW));
			String strTransferOrderDetails = TransferRequestTransHLP.getTransferOrderDetails(formBean);
			formBean.setStrTransferOrderDetails(strTransferOrderDetails);		

		} 
		catch (Exception e) 
		{         
			e.printStackTrace();
			strmsgText = "Drug Inventory.TransferRequestTransDATA.initView(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms",	"TransferRequestTransDATA->initView()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "	+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			
		}

	}
	
	/**
	 * to display the Store Name and Group Name on Add page
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void initOrderView(TransferRequestTransFB formBean,HttpServletRequest request) 
	{

		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;
		String strmsgText = "";
		String path="";
		try 
		{			
			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();			
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatid = request.getSession().getAttribute("SEATID").toString();		
			          
           path = "/mms"+request.getParameter("cnt_page")+".cnt";
			
			if(request.getParameter("cnt_page") == null)
			{
				
				path = request.getParameter("strPath");
			}
			
			formBean.setStrPath(path.trim());
           
			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);
			vo.setStrStoreId("0");
			vo.setStrOrderNumber(formBean.getStrOrderNo());
			vo.setStrItemCategoryNo("10");
			
			// Calling BO Method
			bo.initialOrderView(vo);
			
			
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
			else
			{
				while(vo.getWbTransferOrderDetailView() != null && vo.getWbTransferOrderDetailView().next())
				{
					formBean.setStrOrderNoView(vo.getWbTransferOrderDetailView().getString(1));
					formBean.setStrOrderNoDate(vo.getWbTransferOrderDetailView().getString(2));
					formBean.setStrRaisingDDWView(vo.getWbTransferOrderDetailView().getString(3));
					formBean.setStrRequestNoView(vo.getWbTransferOrderDetailView().getString(4));
					formBean.setStrTransferDDWView(vo.getWbTransferOrderDetailView().getString(5));
					formBean.setStrTransferReqNoView(vo.getWbTransferOrderDetailView().getString(6));				
					formBean.setStrGroupNameView(vo.getWbTransferOrderDetailView().getString(7));
					formBean.setStrItemNameView(vo.getWbTransferOrderDetailView().getString(8));					
					formBean.setStrOrderQtyView(vo.getWbTransferOrderDetailView().getString(9));				
					formBean.setStrTransferQtyView(vo.getWbTransferOrderDetailView().getString(10));					
					formBean.setStrAckQtyWithUnitView(vo.getWbTransferOrderDetailView().getString(11));					
					formBean.setStrOrderRemarksView(vo.getWbTransferOrderDetailView().getString(12));					
					formBean.setStrSubGroupNameView(vo.getWbTransferOrderDetailView().getString(13));									
				}
				formBean.setWbTransferOrderDetail(vo.getWbTransferOrderDetail());
				String strTransferDetailsView = TransferRequestTransHLP.getTransferDetails(formBean);
				formBean.setStrTransferDetailsView(strTransferDetailsView);
			}			

		} 
		catch (Exception e) 
		{         
			e.printStackTrace();
			strmsgText = "Drug Inventory.TransferRequestTransDATA.initOrderView(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms",	"TransferRequestTransDATA->initOrderView()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "	+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			
		}

	}
	
	/**
	 * To get values of SubgroupCombo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void subGroupCombo(TransferRequestTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{

		String strmsgText = "";
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;		
		try
		{

			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();

			String strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			String strSeatId = request.getSession().getAttribute("SEATID").toString();			
			String strGroupId = (String) request.getParameter("GroupId");
			String strStoreId = (String) request.getParameter("storeId");
			
			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrGroupId(strGroupId);

			bo.subGrpAndItemCmb(vo);
			

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
			String  subGrpCmbStr = vo.getStrSubGroupCombo();
			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(subGrpCmbStr);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DrugInventory.TransferRequestTransDATA.subGroupCombo(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferRequestTransDATA->itemName()", strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
			
		}
	}
	
	
	/**
	 * To get values of Item Name Combo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void itemBrandName(TransferRequestTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{

		String strmsgText = "";
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;

		try {

			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();

			String strHospitalCode = request.getSession().getAttribute(
					"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID")
					.toString();
			
			String strStoreId = (String) request.getParameter("storeId");
			String strGroupId = (String) request.getParameter("strGroupId");
			String strSubGroupId = (String) request.getParameter("strSubGroupId");
			
			vo.setStrStoreId(strStoreId);
			vo.setStrItemCategoryNo("10");
			vo.setStrGroupId(strGroupId);
			vo.setStrSubGroupId(strSubGroupId);
			
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
		

			bo.getItemBrandCmb(vo);
			
			String cmbstr = vo.getStrItemNameCombo();

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.TransferRequestTransDATA.itemBrandName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferRequestTransDATA->itemBrandName()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		}
	}
	
	/**
	 * To get values of Item Name Combo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void transferDtl(TransferRequestTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{

		String strmsgText = "";
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;

		try {

			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();		

			String strHospitalCode = request.getSession().getAttribute(	"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID").toString();
			
			String strHiddenValue = (String) request.getParameter("strHiddenValue");
			
			// ReqNo||'^'||Transfer Store ID||'^'||Transfer Req No||'^'||Transfer Date
			//  ||'^'||Transfer remarks||'^'||Ack Date||'^'||ACK_REMARKS||'^'||STATUS
			//  ||'^'||Demand Store ID ||^|| Order Number
			
			vo.setStrRequestNo(strHiddenValue.split("\\^")[0]);
			vo.setStrStoreId(strHiddenValue.split("\\^")[8]);
			vo.setStrOrderNumber(strHiddenValue.split("\\^")[9]);
			vo.setStrItemCategoryNo("10");			
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
		

			bo.getTransferDtl(vo);
			
			formBean.setWbTransferOrderDetail(vo.getWbTransferOrderDetail());
			
			String cmbstr = TransferRequestTransHLP.getTransferDetails(formBean);
			

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr);
			} catch (Exception e) {
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.TransferRequestTransDATA.transferDtl(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferRequestTransDATA->transferDtl()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		}
	}
	
	/**
	 * To get values of Item Name Combo
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void transferItem(TransferRequestTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{

		String strmsgText = "";
		String cmbstr ="";
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;

		try {

			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();		

			String strHospitalCode = request.getSession().getAttribute(	"HOSPITAL_CODE").toString();
			String strSeatId = request.getSession().getAttribute("SEATID").toString();
			
			String strTransferNo = (String) request.getParameter("strTransferNo").split("\\^")[0];
			vo.setStrStoreId((String) request.getParameter("strTransferNo").split("\\^")[2]);
			vo.setStrRequestNo(strTransferNo);			
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
		
            // Calling BO Method
			bo.getTransferItemDtl(vo);
			
            cmbstr = TransferRequestTransHLP.getDrugDetails(vo.getWbTransferOrderDetail(), formBean,(String) request.getParameter("strTransferNo").split("\\^")[1]);
			

			try 
			{
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(cmbstr+"^"+(String) request.getParameter("strTransferNo").split("\\^")[1]);
			} 
			catch (Exception e) 
			{
				throw e;
			}

		} catch (Exception e) {
			strmsgText = "DrugInventory.TransferRequestTransDATA.transferItem(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferRequestTransDATA->transferItem()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;
		}
	}
	
	/**
	 * to insert the data
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void insert(TransferRequestTransFB formBean) 
	{
		String strmsgText = "";
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;

		try 
		{
			bo = new TransferRequestTransBO();           
           vo = (TransferRequestTransVO) TransferObjectFactory.populateData("mms.transactions.vo.TransferRequestTransVO", formBean);
           // Calling BO method
			bo.insert(vo);
			
			
				if (vo.getStrMsgType().equals("1"))
				{
					formBean.setStrErrMsg(vo.getStrMsgString());
					
					throw new Exception(vo.getStrMsgString());
				}
				else
				{	
			       formBean.setStrNormalMsg("Data Has been Successfully Saved");
				}   
			 

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			formBean.setStrStoreName(vo.getStrStoreName());
			strmsgText = "TransferRequestTransDATA.insert(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms","TransferRequestTransDATA->insert()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			vo = null;
			

		}
	}
	
	
	/**
	 * to insert the data
	 * 
	 * @param formBean
	 * @param request
	 */

	public static void update(TransferRequestTransFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		String strmsgText = "";
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;

		try 
		{
			bo = new TransferRequestTransBO();     
			vo = new TransferRequestTransVO();
           String strChk = formBean.getStrChk();			
           vo = (TransferRequestTransVO) TransferObjectFactory.populateData("mms.transactions.vo.TransferRequestTransVO", formBean);
           vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrRequestNo(strChk.split("\\$")[0]);
			vo.setStrItemCategoryNo("10");
           // Calling BO method
			bo.update(vo);
			
			
				if (vo.getStrMsgType().equals("1"))
				{
					formBean.setStrErrMsg(vo.getStrMsgString());
					
					throw new Exception(vo.getStrMsgString());
				}
				else
				{	
			       formBean.setStrNormalMsg("Data Has been Updated Successfully Saved");
				}   
			 

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			formBean.setStrStoreName(vo.getStrStoreName());
			strmsgText = "TransferRequestTransDATA.update(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("mms","TransferRequestTransDATA->update()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		} finally {

			bo = null;
			vo = null;
			

		}
	}
	
	
	/**
	 * This function is used to invoke Bo's update method to update data
	 * 
	 * @param request
	 * @param formBean
	 */
	public static boolean CancelRecord(HttpServletRequest request, TransferRequestTransFB formBean) 
	{
		TransferRequestTransBO bo = null;
		TransferRequestTransVO vo = null;
		boolean retValue = true;
		//String tempChk[] = null;
		
		try {
			bo = new TransferRequestTransBO();
			vo = new TransferRequestTransVO();
			String strHospitalCode = request.getSession().getAttribute(	"HOSPITAL_CODE").toString();
	        String strSeatId = request.getSession().getAttribute("SEATID").toString();
			
	        
	        String strStoreName = request.getParameter("comboValue");			
			String strComboValues[] = request.getParameterValues("combo");
			formBean.setStrStoreName(strStoreName);			
			String strStoreId = formBean.getCombo()[0];			
			
			String strChk = formBean.getStrChk();
			//10911200001$1$Cancel By Amit			
			System.out.println("strChk-"+strChk);
			String[] temp  = strChk.split("\\$");
			vo.setStrItemCategoryNo("10");
			vo.setStrRequestNo(temp[0]);
			
			vo.setStrStoreId(strStoreId);
			vo.setStrSeatId(strSeatId);			
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrCancelReson(temp[2]);
			// Calling BO Method
			bo.cancel(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				retValue = false;
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
				formBean.setStrNormalMsg("Record is successfully Cancel");

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			retValue = false;
			String strmsgText = "Item Master.TransferRequestTransDATA.CancelRecord(vo) --> "
				+ e.getMessage();
			HisException eObj = new HisException("MMS","TransferRequestTransDATA->CancelRecord()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {
			bo = null;
			vo = null;
		}
		return retValue;
	}
	

}

