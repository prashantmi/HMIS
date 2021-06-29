package mms.transactions.controller.data;


import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

import mms.AttachFileGlobal;
import mms.MmsConfigUtil;
import mms.transactions.bo.BillApprovalTransBO;
import mms.transactions.controller.fb.BillApprovalTransFB;
import mms.transactions.controller.hlp.BillApprovalTransHLP;
import mms.transactions.vo.BillApprovalTransVO;

/**
 * 
 * @author dell
 *
 */

public class BillApprovalTransDATA {
	
	 public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	 public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
	    
	  public static  String now(String frmt)
	    {
	      HisUtil util=null;
	      String a="";
	      util=new HisUtil("transaction","SupplierReturnReqTransDATA");
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
	 * This method is used to populate the value of Store name combo box and
	 * this method calls the getInitialValues() method which is define in
	 * SupplierReturnReqTransBO java file.
	 * 
	 * @param formBean
	 * @param request
	 */
	public static void getInitialValues(BillApprovalTransFB formBean,
			HttpServletRequest request) {

		BillApprovalTransBO bo = null;
		BillApprovalTransVO voObj = null;
		HisUtil util = null;
		String strmsgText = "";
	//	String ctDate = "";
		String hosCode = "";
	//	String seatid = "";
	//	String strPOValues = "";
	//	String strItemCatVal = "";

		try {
			bo = new BillApprovalTransBO();
			voObj = new BillApprovalTransVO();
			util=new HisUtil("MMS","Bill Approval Transaction");
			//System.out.println("cmbVal->"+request.getParameter("comboValue"));
			if(request.getParameter("comboValue")!=null)
			{
				String comboValue[] = request.getParameter("comboValue").replace('^', '#').split("#");
				String strStoreName=comboValue[0];
				String strBillTypeName=comboValue[1];
				String strComboValues[] = request.getParameterValues("combo");
				formBean.setStrStoreName(strStoreName);
				formBean.setStrBillTypeName(strBillTypeName);
				//System.out.println("strStoreId ->" + strComboValues[0]);
				//System.out.println("strBillType->" + strComboValues[1]);
				formBean.setStrStoreId(strComboValues[0]);
				formBean.setStrBillType(strComboValues[1]);
			}	
			hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			voObj.setStrHospitalCode(hosCode);
			voObj.setStrStoreId(formBean.getStrStoreId());
			bo.getPODetailsSearchList(voObj);
			String poCmb=util.getOptionValue(voObj.getStrPOSearchDetailsWs(), "0", "0^Select Value", false);
			formBean.setStrPONoCmb(poCmb);
			formBean.setStrCurrentDate(now(DATE_FORMAT_NOW));

		} catch (Exception e) {
			e.printStackTrace();
			
			strmsgText = "mms.transactions.BillApprovalTransDATA.getInitialValues --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"BillApprovalTransDATA->getInitialValues()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

	/**
	 * This method is used to display the value of Item Details and this method
	 * calls the getItemDetails() method which is define in
	 * SupplierReturnReqTransBO java file.
	 * 
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void getPODetails(BillApprovalTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		BillApprovalTransBO bo = null;
		BillApprovalTransVO vo = null;
		String strmsgText = "";
		String hosCode = "";
	//	HisUtil util = null;
		WebRowSet ws=null;
	//	String strPODetails = "";
	//	String strPOArray[] = null;
	//	String ItemDetails = "";
		String []temp=null;
		try {
			bo = new BillApprovalTransBO();
			vo = new BillApprovalTransVO();
	//		util = new HisUtil("MMS Transaction", "BillApprovalTransDATA");
			hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			String seatId = request.getSession().getAttribute("SEATID").toString();
			vo.setStrSeatId(seatId);
			vo.setStrHospitalCode(hosCode);
			vo.setStrStoreId(formBean.getStrStoreId());
			temp=formBean.getStrPONoCmb().replace('^', '#').split("#");
			vo.setStrPONo(formBean.getStrPONoCmb().replace('^', '#').split("#")[0]);
			vo.setStrPOStoreId(formBean.getStrPONoCmb().replace('^', '#').split("#")[1]);
			formBean.setStrPONo(formBean.getStrPONoCmb().replace('^', '#').split("#")[0]);
			if(temp.length==3){
				formBean.setStrPOPrefix(formBean.getStrPONoCmb().replace('^', '#').split("#")[2]);
			}
			
			
			bo.getPODetails(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
			    ws=vo.getStrPODetailsWs();
				while(ws.next())
			    {
			    	vo.setStrPODate(ws.getString(1));
			    	vo.setStrPOTypeId(ws.getString(2));
			    	vo.setStrPOType(ws.getString(3));
			    	vo.setStrSupplierId(ws.getString(4));
			    	vo.setStrSupplierName(ws.getString(5));
			    	vo.setStrPOStoreId(ws.getString(6));
			    	vo.setStrPOStoreName(ws.getString(7));
			    	vo.setStrItemCategoryNoH(ws.getString(8));
			    	vo.setStrItemCategoryNameH(ws.getString(9));
			    	vo.setStrCurrencyId(ws.getString(10));
			    	vo.setStrCurrencyName(ws.getString(11));
			    	vo.setStrCurrencyValue(ws.getString(12));
			    	vo.setStrOverallPOTax(ws.getString(13));
			    	vo.setStrAdvanceTaken(ws.getString(14));
			    	vo.setStrAdvanceAdjusted(ws.getString(15));
			    	vo.setStrNetPenalty(ws.getString(16));
			    	vo.setStrCurrentDate(now(DATE_FORMAT_NOW));
			    	//setting fromBean
			    	formBean.setStrPODate(vo.getStrPODate());
				    formBean.setStrPOTypeId(vo.getStrPOTypeId());
					formBean.setStrPOType(vo.getStrPOType());
					formBean.setStrSupplierId(vo.getStrSupplierId());
					formBean.setStrSupplierName(vo.getStrSupplierName());
					formBean.setStrPOStoreId(vo.getStrPOStoreId());
					formBean.setStrPOStoreName(vo.getStrPOStoreName());
					formBean.setStrItemCategoryNoH(ws.getString(8));
					formBean.setStrItemCategoryNameH(ws.getString(9));
					formBean.setStrCurrencyId(ws.getString(10));
					formBean.setStrCurrencyName(ws.getString(11));
					formBean.setStrCurrencyValue(ws.getString(12));
					formBean.setStrOverallPOTax(ws.getString(13));
					formBean.setStrAdvanceTaken(ws.getString(14));
					formBean.setStrAdvanceAdjusted(ws.getString(15));
					formBean.setStrNetPenalty(ws.getString(16));
					formBean.setStrCurrentDate(now(DATE_FORMAT_NOW));
			    }
				bo.getPOScheduleDetails(vo);
				formBean.setStrCurrentDate(now(DATE_FORMAT_NOW));
				String strScheduleDtls=BillApprovalTransHLP.getScheduleDetails(vo);
				String strOtherDtls=BillApprovalTransHLP.getOtherDetails(vo);
				//System.out.println("strOtherDtls->"+strOtherDtls);
				formBean.setStrScheduleDetails(strScheduleDtls);
				formBean.setStrOtherDetails(strOtherDtls);
			}	

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.BillApprovalTransDATA.getPODetails --> "
				+ e.getMessage();
		    HisException eObj = new HisException("mms",
				"BillApprovalTransDATA->getPODetails()",
				strmsgText);
		    formBean.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		    eObj = null;
			
		} finally {
			bo = null;
			vo = null;
		}

	}
	public static void getPeneltyDtl(BillApprovalTransFB formBean,
			HttpServletRequest request,HttpServletResponse response) {

		BillApprovalTransBO bo = null;
		BillApprovalTransVO vo = null;
		String strmsgText = "";
		String result="";
		String strHospitalCode = "";
		String scheduleNos="";
		String strPOStoreId="";
		String strPONo="";
		String rejectedPenelty="";
		String latePenelty="";
		
		
		String temp[]=null;
		
	//	HisUtil util = null;
		try {
			
				vo=new BillApprovalTransVO();
				bo=new BillApprovalTransBO();
			
			
				scheduleNos=request.getParameter("scheduleNos");
				latePenelty=request.getParameter("latePenelty");
				rejectedPenelty=request.getParameter("rejectedPenelty");
				strPONo=request.getParameter("poNo");
				strHospitalCode= request.getSession().getAttribute("HOSPITAL_CODE").toString();
				strPOStoreId=request.getParameter("poStoreId");
				temp=scheduleNos.replace('^', '#').split("#");
				if(temp.length==1){
					scheduleNos="("+temp[0]+")";
				
				}else{
					scheduleNos="(";
						for(int i=0, stopI = temp.length  ;i<stopI;i++){
							if(i!= stopI-1)
								scheduleNos+=temp[i]+",";
							else
								scheduleNos+=temp[i];
						}
						scheduleNos+=")";
				}
				vo.setStrPOStoreId(strPOStoreId);
				vo.setStrHospitalCode(strHospitalCode);
				vo.setStrPONo(strPONo);
				vo.setStrScheduleNo(scheduleNos);
				bo.getPeneltyDtl(vo);
				result=BillApprovalTransHLP.createPeneltyDtl(vo.getWsPeneltyDtl(), latePenelty, rejectedPenelty);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(result);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "mms.transactions.BillApprovalTransDATA.getPeneltyDtl -->"
				+ e.getMessage();
		    HisException eObj = new HisException("mms",
				"BillApprovalTransDATA->getPeneltyDtl()",
				strmsgText);
		    formBean.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		    eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
		}
	}
	public static void getScheduleItemDtls(BillApprovalTransFB formBean,
			HttpServletRequest request,HttpServletResponse response) {

	//	BillApprovalTransBO bo = null;
		BillApprovalTransVO vo = null;
		String strmsgText = "";
		String strScheduleItemDtls="";
		String hosCode = "";
	//	HisUtil util = null;
		try {
	//		bo = new BillApprovalTransBO();
			vo = new BillApprovalTransVO();
	//		util = new HisUtil("MMS Transaction", "BillApprovalTransDATA");
			hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			vo.setStrHospitalCode(hosCode);
			vo.setStrPOStoreId(request.getParameter("poStoreId"));
			vo.setStrPONo(request.getParameter("poNo"));
			vo.setStrSelScheduleNos(request.getParameter("scheduleNoList"));
			//System.out.println("postoreId->"+request.getParameter("poStoreId"));
			//System.out.println("poNo->"+request.getParameter("poNo"));
			//System.out.println("scheduleNoList->"+request.getParameter("scheduleNoList"));
			strScheduleItemDtls=BillApprovalTransHLP.getScheduleItemDetails(vo);
			formBean.setStrSearchListPODetails(strScheduleItemDtls);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strScheduleItemDtls);

		} 
		catch (Exception e) 
		{
			strmsgText = "mms.transactions.BillApprovalTransDATA.initSearchList -->"
				+ e.getMessage();
		    HisException eObj = new HisException("mms",
				"BillApprovalTransDATA->getPODetails()",
				strmsgText);
		    formBean.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		    eObj = null;
		} 
		finally 
		{
	//		bo = null;
			vo = null;
		}
	}
	public static void initSearchList(BillApprovalTransFB formBean,
			HttpServletRequest request,HttpServletResponse response) {

		BillApprovalTransBO bo = null;
		BillApprovalTransVO vo = null;
		String strmsgText = "";
		String strPOSearchListDtl="";
		String hosCode = "";
	//	HisUtil util = null;
		try {
			bo = new BillApprovalTransBO();
			vo = new BillApprovalTransVO();
		//	util = new HisUtil("MMS Transaction", "BillApprovalTransDATA");
			hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			vo.setStrHospitalCode(hosCode);
			vo.setStrStoreId(request.getParameter("storeId"));
			vo.setStrSearchListPODtlFromDate(request.getParameter("fromDate"));
			vo.setStrSearchListPODtlToDate(request.getParameter("toDate"));
			//System.out.println("storeId->"+request.getParameter("storeId"));
			//System.out.println("fromDate->"+request.getParameter("fromDate"));
			//System.out.println("toDate->"+request.getParameter("toDate"));
			bo.getPODetailsSearchList(vo);
			//System.out.println("afterBO"+vo.getStrMsgType());
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
				strPOSearchListDtl=BillApprovalTransHLP.getPONoSearchListDetails(vo);
				formBean.setStrSearchListPODetails(strPOSearchListDtl);
				//System.out.println("popUpStr->"+strPOSearchListDtl);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strPOSearchListDtl);
			}		

		} 
		catch (Exception e) 
		{
			strmsgText = "mms.transactions.BillApprovalTransDATA.initSearchList -->"
				+ e.getMessage();
		    HisException eObj = new HisException("mms",
				"BillApprovalTransDATA->getPODetails()",
				strmsgText);
		    formBean.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		    eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
		}
	}

	public static void insert(BillApprovalTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		BillApprovalTransBO bo = null;
		BillApprovalTransVO voObj = null;
		String strmsgText = null;
		
		MmsConfigUtil mmsConfig = null;
	//	String fileLocation="";
		String fileExt="";
		String []temp=null;
		AttachFileGlobal fs=null;
	//	String strFileName="";
		String currenDateTime="";
		try {
			bo = new BillApprovalTransBO();
			voObj = new BillApprovalTransVO();
			mmsConfig = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			fs=new AttachFileGlobal();
			voObj = (BillApprovalTransVO) TransferObjectFactory
					.populateData("mms.transactions.vo.BillApprovalTransVO",
							formBean);
			voObj.setStrFinancialStartYear(mmsConfig.getStrFinancialStartDate(voObj.getStrStoreId() , voObj.getStrHospitalCode()));
			voObj.setStrFinancialEndYear(mmsConfig.getStrFinancialEndDate(voObj.getStrStoreId() , voObj.getStrHospitalCode()));
	//		strFileName="";
			currenDateTime= now(DATE_FORMAT_NOWwt);
			 temp=currenDateTime.replace('/', '#').split("#");
			 currenDateTime=temp[0]+"_"+temp[1];
			 voObj.setStrCurrentDateTime(currenDateTime);
            
           /* FormFile myFile = formBean.getStrLocation();
            temp=myFile.getFileName().replace('.', '#').split("#");
			fileExt=temp[temp.length-1];
			voObj.setStrFileExt(fileExt);*/
			
			bo.insert(voObj);
	//		fileLocation=mmsConfig.getStrCommitteeFilePath();
			//fs.saveFile(myFile.getFileData(), voObj.getStrFileName()); 
		    formBean.setStrCurrentDate(now(DATE_FORMAT_NOW));
        	if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			} else {
				formBean.setStrNormalMsg("Data Inserted Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.BillApprovalTransDATA.insert --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"BillApprovalTransDATA->insert()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
		}	
	}
}
