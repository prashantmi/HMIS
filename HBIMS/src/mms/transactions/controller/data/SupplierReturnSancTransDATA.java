package mms.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import mms.MmsConfigUtil;
import mms.transactions.bo.SupplierReturnSancTransBO;
import mms.transactions.controller.fb.SupplierReturnSancTransFB;
import mms.transactions.controller.hlp.SupplierReturnSancTransHLP;
import mms.transactions.vo.SupplierReturnSancTransVO;

public class SupplierReturnSancTransDATA {
	
	public static  String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	  public static  String DATE_FORMAT_NOW = "dd-MMM-yyyy";
	    
	  public static  String now(String frmt)
	    {
	      HisUtil util=null;
	      String a="";
	      util=new HisUtil("transaction","SupplierReturn");
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
	 * This method is used to set all the values which
	 * are required to populate the Item Category List.
	 * @param formBean
	 * @param request
	 */
	
	public static void getData(SupplierReturnSancTransFB formBean, 
						HttpServletRequest request) {

		SupplierReturnSancTransBO bo = null;
		SupplierReturnSancTransVO voObj = null;
		String strmsgText = null;
		WebRowSet ws=null;
		HisUtil util = null;
		try {
			bo = new SupplierReturnSancTransBO();
			voObj = new SupplierReturnSancTransVO();
			String strStoreName=request.getParameter("comboValue");
			String strChk=request.getParameter("chk");
			if(strChk!=null)
			{
			    formBean.setStrChk(strChk);
			    voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			    String strComboValues[] = request.getParameterValues("combo");
			    formBean.setStrStoreName(strStoreName);
				//System.out.println("strStoreId->"+strComboValues[0]);
				//System.out.println("StrChk->"+strChk);
				formBean.setStrStoreId(strComboValues[0]);
				voObj.setStrStoreId(strComboValues[0]);
				String strTemp1[]= strChk.replace("@", "#").split("#");
				formBean.setStrItemCategoryNoH(strTemp1[6]);
				voObj.setStrItemCategoryNoH(strTemp1[6]);
				formBean.setStrReqDate(strTemp1[2]);
				formBean.setStrItemCategoryNameH(strTemp1[3]);
				formBean.setStrReqNo(strTemp1[1]);
				voObj.setStrReqNo(strTemp1[1]);
				formBean.setStrOnlineFlag(strTemp1[5]);
				formBean.setStrPONo(strTemp1[7]);
				voObj.setStrPONo(strTemp1[7]);
				formBean.setStrPOStoreId(strTemp1[8]);
				voObj.setStrPOStoreId(strTemp1[8]);
				formBean.setStrReturnFlag(strTemp1[9]);
				if(strTemp1[9].trim().equals("1"))
					 formBean.setStrReturnTypeName("Rejected");
				if(strTemp1[9].trim().equals("2"))
					 formBean.setStrReturnTypeName("Breakage");
				if(strTemp1[9].trim().equals("3"))
					 formBean.setStrReturnTypeName("Excess");
				if(strTemp1[9].trim().equals("4"))
					 formBean.setStrReturnTypeName("LP Return");
				
				formBean.setStrReturnType(strTemp1[10]);
				if(strTemp1[10].trim().equals("1"))
					 formBean.setStrReturnReason("Replacement");
				if(strTemp1[10].trim().equals("2"))
					 formBean.setStrReturnReason("Return");
				
				formBean.setStrDeliveryDate(strTemp1[11]);
				formBean.setStrScheduleNo(strTemp1[12].replace('$', '#').split("#")[0]);
				formBean.setStrCurrentDate(now(DATE_FORMAT_NOWwt));
			} 	
			else
			{
				voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				voObj.setStrStoreId(formBean.getStrStoreId());
				voObj.setStrItemCategoryNoH(formBean.getStrItemCategoryNoH());
				voObj.setStrReqNo(formBean.getStrReqNo());
				voObj.setStrPONo(formBean.getStrPONo());
				voObj.setStrPOStoreId(formBean.getStrPOStoreId());
			}
			//voObj = (SupplierReturnSancTransVO) TransferObjectFactory.populateData("mms.transactions.vo.SupplierReturnSancTransVO",formBean);
		
			bo.getItemAndPODetails(voObj);
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			ws=voObj.getStrPODetailsWs();
			while(ws.next())
			{
			   formBean.setStrPOStoreName(ws.getString(8));
			   formBean.setStrPODate(ws.getString(2));
			   formBean.setStrPOType(ws.getString(4));
			   formBean.setStrSupplierName(ws.getString(6));
			}   
			String strItemDtls=SupplierReturnSancTransHLP.getItemDetails(voObj);
			formBean.setStrItemDtls(strItemDtls);
			formBean.setStrGroupName(voObj.getStrGroupName());
			formBean.setStrTotalReturnCost(voObj.getStrTotalReturnCost());
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
		
		} catch (Exception e) {
			strmsgText = "mms.transactions.SupplierReturnSancTransDATA.getData --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"SupplierReturnSancTransDATA->getData()", strmsgText);
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
	 * This method is used to set all the values
	 * for inserting the details.
	 * @param formBean
	 * @param request
	 * @param response
	 */
	
	public static void insert(SupplierReturnSancTransFB formBean, HttpServletRequest request,
				HttpServletResponse response) {
		
			SupplierReturnSancTransBO bo = null;
			SupplierReturnSancTransVO voObj = null;
			String strmsgText = null;
			HisUtil util = null;
			
			MmsConfigUtil mmsConfig = null;
		
		try {
			bo = new SupplierReturnSancTransBO();
			voObj = new SupplierReturnSancTransVO();
			mmsConfig = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			
			//voObj = (SupplierReturnSancTransVO)TransferObjectFactory.populateData("mms.transactions.vo.SupplierReturnSancTransVO",formBean);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrStoreId(formBean.getStrStoreId());
			voObj.setStrItemCategoryNoH(formBean.getStrItemCategoryNoH());
			voObj.setStrReqNo(formBean.getStrReqNo());
			voObj.setStrRemarks(formBean.getStrRemarks());
			voObj.setStrReturnFlag(formBean.getStrReturnFlag());
			voObj.setStrFinancialStartYear(mmsConfig.getStrFinancialStartDate(voObj.getStrStoreId() , voObj.getStrHospitalCode()));
			voObj.setStrFinancialEndYear(mmsConfig.getStrFinancialEndDate(voObj.getStrStoreId() , voObj.getStrHospitalCode()));
			bo.insert(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}else{
				formBean.setStrNormalMsg("Data Updated Successfully");
			}
			
		} catch (Exception e) {
			strmsgText = "mms.transactions.SupplierReturnSancTransDATA.insert --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"SupplierReturnSancTransDATA->insert()", strmsgText);
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
	}
