package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;

public class DiscountApprovalTransDATA {
	public static void initClientDetail(String strChk,
			DiscountApprovalTransFB formBean) {
		String strmsgText = null;
		DiscountApprovalTransVO vo = null;
		vo.setStrChk(strChk);

		try {
			vo = new DiscountApprovalTransVO();
			formBean.setStrCrNo(strChk);
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountApprovalTransDATA->initClientDetail()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

		}
	}

	/**
	 * *******************************AJAX
	 * RESPONSE*******************************************
	 */
	public static String AjaxResponse(DiscountApprovalTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		// Declearing Variables
		String strmsgText = "";
		WebRowSet wb = null;
		// String temp = null;
		int hlp_opt_sel = 0;
		// String strProcMode = "";
		// HisUtil hisutil = null;

		// Createing Object
		DiscountApprovalTransVO vo = new DiscountApprovalTransVO();
		DiscountApprovalTransBO bo = new DiscountApprovalTransBO();
		// hisutil = new HisUtil("transaction", "IpdBillManagementTransDATA");

		String strValmode = (String) request.getParameter("modName");
		// System.out.println(" DATA AjaxResponse strValmode"+strValmode);
		vo.setStrHospitalCode(formBean.getStrHospitalCode());

		// strValmode = REQ_NO +"^"+ SBLNUM_BSERVICE_ID
		// +"^"+HBLNUM_PATACCOUNT_NO+"^"+HiddenValue
		String strTemp[] = strValmode.replace('@', '#').split("#");
		vo.setStrHidValOnLineReqDis(strTemp[3]); // Here we set the Hidden
		// Value which we get from
		vo.setStrBserviceId(strTemp[1]); // This Show Whether IPD & OPD or
		// Final Adjustment Selected

		int bServiceId = Integer.parseInt(strTemp[1]);

		/*
		 * if (strTemp[1].equals("0")) { hlp_opt_sel = 1; } if ((bServiceId > 9) &&
		 * (bServiceId < 15)) // For OPD & IPD selected { hlp_opt_sel = 1; } if
		 * (bServiceId == 21) // For Final Adjustment { hlp_opt_sel = 2; }
		 */
		if (bServiceId == 21)
			hlp_opt_sel = 2;
		else
			hlp_opt_sel = 1;
		// lines added by anshul start

		/*
		 * if(strTemp[1].equals("10") || strTemp[1].equals("11") ||
		 * strTemp[1].equals("12")) strProcMode="1"; else strProcMode="2";
		 */

		// lines added by anshul end
		// Calling BO Method
		vo.setStrValmode(strValmode);
		vo.setHlp_opt_sel(String.valueOf(hlp_opt_sel));

		bo.setReqValues(vo);
		wb = vo.getStrOnLineTariffWs();

		String tariffDetails = null;

		try {
			if (hlp_opt_sel == 1)
				tariffDetails = DiscountApprovalTransHLP.getTariffDetails(wb,
						vo);
			if (hlp_opt_sel == 2)
				tariffDetails = DiscountApprovalTransHLP.getIPDBillSetlmnt(wb,
						vo);
		} catch (Exception e) {
			strmsgText = "billing.transactions.RefundApprovalTransDATA.TariffDtl(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"RefundApprovalTransaction->RefundApprovalTransDATA->TariffDtl()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;

		}
		if (strmsgText.equals("")) {
			return tariffDetails;
		} else {
			return strmsgText;
		}
	}

	public static void getRsnRmk(DiscountApprovalTransFB formBean) 
	{
		String strmsgText = null;
		DiscountApprovalTransVO vo = null;
		DiscountApprovalTransBO bo = null;
		try 
		{
			if (formBean.getStrMsgType().equals("0")) 
			{
				vo = new DiscountApprovalTransVO();
				bo = new DiscountApprovalTransBO();
			
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				vo.setStrSeatId(formBean.getStrSeatId());
				bo.getRsnRmk(vo);
				formBean.setStrRsn(vo.getStrRsn());
				formBean.setStrRmk(vo.getStrRmk());
			} 
			else 
			{
				formBean.setStrRsn("<option value = '0'>Select Value</option>");
				formBean.setStrRmk("<option value = '0'>Select Value</option>");
			}
		} 
		catch (Exception e) 
		{
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","DiscountApprovalTransDATA->getRsnRmk()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			vo = null;
			bo = null;
		}
	}

	public static void initOnlineReq(String strChk,
			DiscountApprovalTransFB formBean) {

		DiscountApprovalTransVO vo = null;

		String strmsgText = null;

		try {
			vo = new DiscountApprovalTransVO();
			formBean.setStrCrNo1(strChk);

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());

			// Calling HLP to get Patient Detail
			// System.out.println("strPatView");
			String strPatView = PatientDtlHLP.patientDtl(formBean.getStrCrNo(),
					true);
			if (strPatView.trim().equals("")) {
				formBean.setStrErrMsg("Invalid CR No.");
				formBean.setStrMsgType("1");
				formBean.setStrCRNoSatus("0");
			} else {
				formBean.setStrPatientDetailsView(strPatView);
				formBean.setStrCRNoSatus("1");
				formBean.setStrMsgType("0");
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountApprovalTransDATA->initOnlineReq()", strmsgText);
            
			System.out.println("strmsgText"+strmsgText);
			if(strmsgText.contains("Invalid CR. No."))
				formBean.setStrErrMsg("Invalid CR. No.");
			else
				formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrCRNoSatus("1");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;
		}
	}

	/**
	 * **********************************New Method For Ajax
	 * Response*************************************************
	 */
	public static void PopUpResponse(DiscountApprovalTransFB formbean,
			HttpServletRequest request, HttpServletResponse response) {
		String pop_fld = null;
		WebRowSet ws = null;
		String strmsgText = null;
		DiscountApprovalTransVO vo = null;
		DiscountApprovalTransBO bo = null;
		String strDisBy = null;
		String strDisDate = null;
		String strDisReson = null;

		try {
			vo = new DiscountApprovalTransVO();
			bo = new DiscountApprovalTransBO();

			// int pop_id = 1;
			String strValmode = (String) request.getParameter("modName");
			String strPopUpId = (String) request.getParameter("popUpId");
			vo.setStrHospitalCode(request.getSession().getAttribute(
					"HOSPITAL_CODE").toString());
			vo.setStrValmode(strValmode);
			vo.setStrPopUpId(strPopUpId);

			if (strPopUpId.equals("2")) {
				// pop_id = 2;
				vo.setStrPopUpId("2");
			}
			vo.setStrPopUpId("1");
			ws = bo.setPopUpVal(vo);

			response.setHeader("Cache-Control", "no-cache");
			if (ws == null) {
				throw new Exception("No Value for Selected Record!!");
			} else {
				while (ws.next()) {
					strDisBy = ws.getString(7);
					strDisDate = ws.getString(1);
					strDisReson = ws.getString(5);
					// System.out.println("App Date:"+ws.getString(1));
					// System.out.println("Pstr Emp No:"+ws.getString(2));
					// System.out.println("HblnumUserLevel:"+ws.getString(3));
					// System.out.println("Hblnum_dis_Unit:"+ws.getString(4));
					// System.out.println("HBlstr_Remarks:"+ws.getString(5));
					// System.out.println("Hblnum_Dis_Type:"+ws.getString(6));
					// System.out.println("Emp_Name:"+ws.getString(7));
				}
				pop_fld = strDisBy + "^" + strDisDate + "^" + strDisReson;
				response.getWriter().print(pop_fld);
			}
		} catch (Exception e) {
			strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				new HisException("Billing",
						"DiscountApprovalTransDATA->PopUpResponse()",
						strmsgText);
				String response1 = "ERROR####No Value for Selected Record!!!";
				response.getWriter().print(response1);

			} catch (IOException e1) {

			}
		} finally {

			bo = null;
			vo = null;

		}
	}

	/**
	 * ****************************Insert
	 * Method()*******************************************
	 */
	/*
	 * This Logic Based on BService Id If BServcieId is in between(>9 <15) Its
	 * Call Servcie Method Insert
	 */
	/* If BService Id is 21 then Insert Logic for Final Adjustment */
	public static boolean getInsertData(DiscountApprovalTransFB formBean,HttpServletRequest request) 
	{
		// Declare Variables
		boolean fretValue = false;
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		int length = 0; // , add = 0, add1 = 0, Finalsum = 0;
		// String[] TestData4 = null;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

		if (request.getSession().getAttribute("USER_LEVEL") != null) 
		{
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		} 
		else 
		{
			formBean.setStrUserLevel("1");
		}
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		// Create Object for VO & BO
		DiscountApprovalTransVO vo = null;
		DiscountApprovalTransBO bo = null;

		try 
		{
			vo = new DiscountApprovalTransVO();
			bo = new DiscountApprovalTransBO();
			int bServiceId = Integer.parseInt(formBean.getHlpBServiceID());
			vo.setStrBserviceId(formBean.getHlpBServiceID());

			if (bServiceId != 21) 
			{
				if (formBean.getTrfDisApproval() != null) 
				{
					length = formBean.getTrfDisApproval().length;
					if (length > 0) 
					{
						vo.setStrLength(length);
						vo.setTrfDisApproval(formBean.getTrfDisApproval());
						vo.setStrhiddTrfAddDis(formBean.getStrhiddTrfAddDis()); // check box value [trfid ^appid]
						vo.setStrHospitalCode(formBean.getStrHospitalCode()); // 1
						vo.setStrSeatId(formBean.getStrSeatId()); // 2
						vo.setStrUserLevel(formBean.getStrUserLevel()); // 3
						vo.setStrHidValOnLineReqDis(formBean.getStrHidValOnLineReqDis()); // 4
						vo.setApprovalId(formBean.getStrApprovId()); // 5
						vo.setStrTariffIdInsert(formBean.getStrTrfId()); // 6
						vo.setStrPrevDisType(formBean.getStrDiscountType()); // 7
						vo.setLstDisService(formBean.getStrDiscountUnit());
						vo.setOpd_discount(formBean.getOpd_discount());
						vo.setOpd_discountType(formBean.getOpd_discountType()); // 9
						vo.setStrRmk(formBean.getStrRmk()); // discount by
						vo.setStrDrt(formBean.getStrDrt()); // discount reason
						
						fretValue = bo.getInsertDataForServices(vo);
						
					}
				}
			} 
			else 
			{
				// final settlement
				if (bServiceId == 21) 
				{
					/*
					 * if (formBean.getStrhiddTrfFADis().equals("1")) { add = 0;
					 * add1 = 0; add =
					 * Integer.parseInt(formBean.getStrIpdBillCurrDis()); add1 =
					 * Integer.parseInt(formBean.getStrLstDisUnit()); Finalsum =
					 * Finalsum + add + add1;
					 *  } else { add = 0; add1 = 0; add =
					 * Integer.parseInt(formBean.getStrIpdBillCurrDis());
					 * Finalsum = Finalsum + add + add1; } String sumFA =
					 * String.valueOf(Finalsum); TestData4 =
					 * formBean.getStrIpdBillFianlDis().split("\\-");
					 */

					// vo.setStrhiddTrfFADis(formBean.getStrhiddTrfFADis());
					vo.setStrHospitalCode(formBean.getStrHospitalCode());
					vo.setStrSeatId(formBean.getStrSeatId());
					vo.setStrUserLevel(formBean.getStrUserLevel());
					vo.setStrHidValOnLineReqDis(formBean
							.getStrHidValOnLineReqDis());
					/** ************* IMP VALUE **************** */
					vo.setApprovalId(formBean.getStrApprovId()); // 5
					vo.setStrPrevDisType(formBean.getStrDiscountType()); // 7
					vo.setLstDisService(formBean.getStrDiscountUnit());

					// vo.setOpd_discount(formBean.getOpd_discount());
					// vo.setOpd_discountType(formBean.getOpd_discountType());
					// // 9

					// vo.setApproval_id(formBean.getApproval_id()); // 1
					// vo.setSumFAdjustment(TestData4[1]); // 2
					// vo.setStrPrevDisTypeFA(formBean.getStrPrevDisTypeFA());
					// // 3
					// vo.setStrLstDisUnitFA(formBean.getStrLstDisUnit()); // 4
					// vo.setStrCurrDisTypeFA(formBean.getDiscountType()); // 5
					vo.setStrTrfId(formBean.getTariff_id()); // 6
					vo.setStrIpdBilldiscountType(formBean.getDiscountType()); // 7
					System.out.println("curr disc val is::"+formBean.getDiscountType());
					vo.setStrIpdBillCurrDis(formBean.getStrIpdBillCurrDis()); // 8

					vo.setStrRmk(formBean.getStrRmk());
					vo.setStrDrt(formBean.getStrDrt());
					vo.setStrhiddTrfFADis(formBean.getStrhiddTrfFADis());

					fretValue = bo.getInsertDataForFinalAdjustment(vo);
				}
			}

			if (fretValue) 
			{
				strMsg = "Discount Approval successfully Inserted!!";
				vo.setStrMsg(strMsg);
				uploadFile(formBean,request,vo);
			} 
			else 
			{
				strWarning = "Discount Approval Not Inserted!";
				vo.setStrWarning(strWarning);
			}
			// Check Error
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				formBean.setStrMsg(vo.getStrMsg());
			}
			formBean.setStrApproval_id(vo.getStrApproval_id());
		}

		catch (Exception e) 
		{
			// e.printStackTrace();
			strmsgText = "billing.transactions.DiscountApprovalTransDATA.getInsertData(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("Billing","DiscountApprovalTransaction->getInsertData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			vo = null;
			bo = null;
		}

		return fretValue;
	}
	
	public static boolean uploadFile(DiscountApprovalTransFB formBean,HttpServletRequest request,DiscountApprovalTransVO vo)
	{
		boolean flag=true;
		if(formBean.getUploadedFile()!=null && formBean.getUploadedFile().getFileName()!=null && !formBean.getUploadedFile().getFileName().equals("") && formBean.getUploadedFile().getFileSize()<=2000000)
		{
			//try writing file..will work only when single file is uploaded for each row..
			OutputStream out=null;
			String fileSavePath=HisUtil.getParameterFromHisPathXML("CREDIT_BILL_PATH");
			String ext=".jpeg";
			String []tempArr=null;
			
			FormFile myfile=formBean.getUploadedFile();
			System.out.println("uploaded file name is:::"+myfile.getFileName());
			java.util.Date date= new java.util.Date();
				try
				{
					tempArr=myfile.getFileName().replace('.', '#').split("#");
					ext=tempArr[tempArr.length-1];
					System.out.println("extension is::"+ext);
					System.out.println("uploaded file path is:::"+fileSavePath+"/"+vo.getStrApproval_id()+"."+ext);
					
					//out = new FileOutputStream( new File(fileSavePath+"/"+strHbReqNo+"_"+date.getTime()+"_"+k+"."+ext));
					out = new FileOutputStream( new File(fileSavePath+"/"+vo.getStrApproval_id()+"."+ext));
					out.write(myfile.getFileData());
					out.close();
				}
				catch (Exception e)
				{
					flag=false;
					e.printStackTrace();
				}			
		}
		return flag;				
	}

}
