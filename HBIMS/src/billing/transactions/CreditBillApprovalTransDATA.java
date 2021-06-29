package billing.transactions;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

import org.apache.struts.upload.FormFile;



//import cssd.transactions.controller.fb.WorkAllocationFB;

public class CreditBillApprovalTransDATA 
{
	public static void initOnlineReq(String strChk,CreditBillApprovalTransFB formBean) 
	{
		CreditBillApprovalTransVO vo = null;
		String strmsgText = null;
		HisUtil hisUtil=null;

		try 
		{
			vo = new CreditBillApprovalTransVO();
			hisUtil=new HisUtil("Billing","CreditBillApprovalDATA");
			formBean.setStrCrNo1(strChk);			

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());

			// Calling HLP to get Patient Detail
			String strPatView = PatientDtlHLP.patientDtl(formBean.getStrCrNo(),true);
			
			if (strPatView.trim().equals("")) 
			{
				formBean.setStrErrMsg("Invalid CR No.");
				formBean.setStrMsgType("1");
				formBean.setStrCRNoSatus("0");
			} 
			else 
			{
				formBean.setStrPatientDetailsView(strPatView);
				formBean.setStrCRNoSatus("1");
				formBean.setStrMsgType("0");
			}
			formBean.setStrCurrentDate(hisUtil.getASDate("dd-MMM-yyyy"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","CreditBillApprovalTransDATA->initOnlineReq()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrCRNoSatus("1");
			formBean.setStrMsgType("1");
			eObj = null;

		} 
		finally 
		{
			vo = null;
		}
	}
	
	//to get combo values for waived by..
	public static void getWaivedBy(CreditBillApprovalTransFB formBean) 
	{
		String strmsgText = null;
		CreditBillApprovalTransVO vo = null;
		CreditBillApprovalTransBO bo = null;
		
		try 
		{
			if (formBean.getStrMsgType().equals("0")) 
			{
				vo = new CreditBillApprovalTransVO();
				bo = new CreditBillApprovalTransBO();
			
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				vo.setStrSeatId(formBean.getStrSeatId());
				
				bo.getWaivedBy(vo);
				
				formBean.setStrRelationWs(vo.getStrRelationWs());
				formBean.setStrRmk(vo.getStrRmk());
			} 
			else 
			{
				//waived by..
				formBean.setStrRmk("<option value = '0'>Select Value</option>");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","CreditBillApprovalTransDATA->getWaivedBy()", strmsgText);
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
	
	/**
	 * Gets Waiver Details
	 */
	public static String AjaxResponse(CreditBillApprovalTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		// Declearing Variables
		String strmsgText = "";
		WebRowSet wb = null;
		int hlp_opt_sel = 0;
		
		CreditBillApprovalTransVO vo = new CreditBillApprovalTransVO();
		CreditBillApprovalTransBO bo = new CreditBillApprovalTransBO();
		

		String strValmode = (String) request.getParameter("modName");
		// System.out.println(" DATA AjaxResponse strValmode"+strValmode);
		vo.setStrHospitalCode(formBean.getStrHospitalCode());

		// strValmode = REQ_NO +"^"+ SBLNUM_BSERVICE_ID +"^"+HBLNUM_PATACCOUNT_NO+"^"+HiddenValue
		String strTemp[] = strValmode.replace('@', '#').split("#");
		vo.setStrHidValOnLineReqDis(strTemp[3]); // Here we set the Hidden
		// Value which we get from
		vo.setStrBserviceId(strTemp[1]); // This Show Whether IPD & OPD or Final Adjustment Selected

		//System.out.println("operation type ipd/opd/final bill::"+strTemp[1]);
		
		int bServiceId = Integer.parseInt(strTemp[1]);

		
		if (bServiceId == 21)
			hlp_opt_sel = 2;
		else
			hlp_opt_sel = 1;
		
		vo.setStrValmode(strValmode);
		vo.setHlp_opt_sel(String.valueOf(hlp_opt_sel));

		bo.setReqValues(vo);
		wb = vo.getStrOnLineTariffWs();

		String tariffDetails = null;

		try {
			if (hlp_opt_sel == 1)
				tariffDetails = CreditBillApprovalTransHLP.getTariffDetails(wb,vo);
			if (hlp_opt_sel == 2)//final adjustment
				tariffDetails = CreditBillApprovalTransHLP.getIPDBillSetlmnt(wb,vo);
		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "billing.transactions.CreditBillApprovalTransDATA.TariffDtl(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"RefundApprovalTransaction->CreditBillApprovalTransDATA->TariffDtl()",
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

	/**
	 * ****************************Insert
	 * Method()*******************************************
	 */
	/*
	 * This Logic Based on BService Id If BServcieId is in between(>9 <15) 
	 * Call Servcie Method Insert
	 */
	/* If BService Id is 21 then Insert Logic for Final Adjustment */
	public static boolean getInsertData(CreditBillApprovalTransFB formBean,	HttpServletRequest request) 
	{
		CreditBillApprovalTransVO vo = null;
		CreditBillApprovalTransBO bo = null;

		boolean fretValue = false;
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		int length = 0; 
		String[] tempArrDiscGvn=new String[500];
		String[] tempArrTariffCost=new String[500];
		String[] tempArrCreditLetterNo=new String[500];
		String[] tempArrCreditLetterDate=new String[500];

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		if (request.getSession().getAttribute("USER_LEVEL") != null) 
		{
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL").toString());
		} 
		else 
		{
			formBean.setStrUserLevel("1");
		}
		
		try 
		{
			vo = new CreditBillApprovalTransVO();
			bo = new CreditBillApprovalTransBO();
			
			int bServiceId = Integer.parseInt(formBean.getHlpBServiceID());
			vo.setStrBserviceId(formBean.getHlpBServiceID());

			if (bServiceId != 21) // Not Final Adjustment
			{
				if (formBean.getTrfDisApproval() != null) 
				{
					length = formBean.getTrfDisApproval().length;					
					if (length > 0) 
					{
						vo.setStrLength(length);
						vo.setTrfDisApproval(formBean.getTrfDisApproval());
						vo.setStrhiddTrfAddDis(formBean.getStrhiddTrfAddDis()); 
						vo.setStrHospitalCode(formBean.getStrHospitalCode());
						vo.setStrSeatId(formBean.getStrSeatId());
						vo.setStrUserLevel(formBean.getStrUserLevel());
						vo.setStrHidValOnLineReqDis(formBean.getStrHidValOnLineReqDis());
						vo.setApprovalId(formBean.getStrApprovId());
						vo.setStrTariffIdInsert(formBean.getStrTrfId());
						vo.setStrPrevDisType(formBean.getStrDiscountType());
						vo.setLstDisService(formBean.getStrDiscountUnit());
						vo.setOpd_discount(formBean.getOpd_discount());
						vo.setOpd_discountType(formBean.getOpd_discountType());
						vo.setStrRmk(formBean.getStrRmk());
						
						for(int i=0;i<formBean.getDiscGivenAmt().length;i++)
						{							
							tempArrDiscGvn[i]=formBean.getDiscGivenAmt()[i];
						}
						for(int i=0;i<formBean.getTariffCost().length;i++)
						{
							tempArrTariffCost[i]=formBean.getTariffCost()[i];
						}
						for(int i=0;i<formBean.getStrCreditLetterNo().length;i++)
						{
							
							tempArrCreditLetterNo[i]=formBean.getStrCreditLetterNo()[i];
						}
						
						for(int i=0;i<formBean.getStrCreditLetterDate().length;i++)
						{
							tempArrCreditLetterDate[i]=formBean.getStrCreditLetterDate()[i];
						}
						//for calculating paid by pat and client amt..
						vo.setDiscGivenAmt(tempArrDiscGvn);
						vo.setTariffCost(tempArrTariffCost);
						
						// credit details tariff wise(row wise)
						vo.setStrCreditLetterNo(tempArrCreditLetterNo);
						vo.setStrCreditLetterDate(tempArrCreditLetterDate);
						
						//get client no..
						vo.setStrCatgoryCode(formBean.getStrCatgoryCode());
						
						CreditBillApprovalTransVO tempObj=new CreditBillApprovalTransVO();
						tempObj=bo.getClientNo(vo);
						
						vo.setStrClientNo(tempObj.getStrClientNo());
						//System.out.println("cr no received is::"+formBean.getFinalCRNo());
						vo.setCrNo(formBean.getFinalCRNo());
					
						vo.setStrEmployeeId(formBean.getStrEmployeeId());
						vo.setStrEmployeeName(formBean.getStrEmployeeName());
						vo.setStrRalationId(formBean.getStrRalationId());
						vo.setStrCardValidity(formBean.getStrCardValidity());
						
						fretValue = bo.getInsertDataForServices(vo);
						// }
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
					//System.out.println("curr disc val is::"+formBean.getDiscountType());
					vo.setStrIpdBillCurrDis(formBean.getStrIpdBillCurrDis()); // 8

					vo.setStrRmk(formBean.getStrRmk());
					vo.setStrDrt(formBean.getStrDrt());
					vo.setStrhiddTrfFADis(formBean.getStrhiddTrfFADis());

					//fretValue = bo.getInsertDataForFinalAdjustment(vo); do uncomment this..
				}
			}

			if (fretValue) 
			{
				strMsg = "Credit/Cashless Bill Approval successfully Inserted!!";
				vo.setStrMsg(strMsg);
				
				//try writing file..will work only when single file is uploaded for each row..
				OutputStream out=null;
				String[] strHiddenVal = null;
				String fileSavePath=HisUtil.getParameterFromHisPathXML("CREDIT_BILL_PATH");
				strHiddenVal = formBean.getStrHidValOnLineReqDis().split("\\^");
				String strHbReqNo = strHiddenVal[0];//used for naming a file(file name will be requisition no wise)..
				String ext=".jpeg";
				String []tempArr=null;
				int lengthOfFilesToBeUploaded=0;
				lengthOfFilesToBeUploaded=formBean.getTrfDisApproval().length;
				
				for(int k=0;k<lengthOfFilesToBeUploaded;k++)
				{
					FormFile myfile=formBean.getUploadedFile(k);
					
					//System.out.println("uploaded file name is:::"+myfile.getFileName());
				}
				
				java.util.Date date= new java.util.Date();
				
				for(int k=0;k<lengthOfFilesToBeUploaded;k++)
				{
					FormFile myfile=formBean.getUploadedFile(k);
					
					try
					{
						tempArr=myfile.getFileName().replace('.', '#').split("#");
						ext=tempArr[tempArr.length-1];
						//System.out.println("extension is::"+ext);
						System.out.println("uploaded file name is:::"+myfile.getFileName()+"::k::"+k);
						
						out = new FileOutputStream( new File(fileSavePath+"/"+strHbReqNo+"_"+date.getTime()+"_"+k+"."+ext));
						out.write(myfile.getFileData());
						out.close();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
			} else {
				strWarning = "Discount Approval Not Inserted!";
				vo.setStrWarning(strWarning);
			}
			// Check Error
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				formBean.setStrMsg(vo.getStrMsg());
			}
			formBean.setStrApproval_id(vo.getStrApproval_id());
		}

		catch (Exception e) {
			 e.printStackTrace();
			strmsgText = "billing.transactions.DiscountApprovalTransDATA.getInsertData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountApprovalTransaction->getInsertData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;

			bo = null;
		}

		return fretValue;
	}
	
	//to get Client No in case of credit billing..
	public static void getClientNo(CreditBillApprovalTransFB formBean) 
	{
		String strmsgText = null;
		CreditBillApprovalTransVO vo = null;
		CreditBillApprovalTransBO bo = null;
		
		try 
		{
			if (formBean.getStrMsgType().equals("0")) 
			{
				vo = new CreditBillApprovalTransVO();
				bo = new CreditBillApprovalTransBO();
			
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				vo.setStrSeatId(formBean.getStrSeatId());
				
				bo.getWaivedBy(vo);
				
				formBean.setStrRsn(vo.getStrRsn());
				formBean.setStrRmk(vo.getStrRmk());
			} 
			else 
			{
				//set client no to zero in case of same not being received..
				formBean.setStrClientNo("0");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","CreditBillApprovalTransDATA->getClientNo()", strmsgText);
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
	
	//to get combo values for relations..
	public static void getRelationWS(CreditBillApprovalTransFB formBean) 
	{
		String strmsgText = null;
		CreditBillApprovalTransVO vo = null;
		CreditBillApprovalTransBO bo = null;
		System.out.println("data credit");
		try 
		{
			if (formBean.getStrMsgType().equals("0")) 
			{
				vo = new CreditBillApprovalTransVO();
				bo = new CreditBillApprovalTransBO();
			
				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				vo.setStrSeatId(formBean.getStrSeatId());
				
				bo.getRelationWS(vo);
				
				formBean.setStrRsn(vo.getStrRsn());
				formBean.setStrRmk(vo.getStrRmk());
			} 
			else 
			{
				//waived by..
				formBean.setStrRelationWs("<option value = '0'>Select Value</option>");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","CreditBillApprovalTransDATA->getRelationWS()", strmsgText);
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
	
	///////////////////////////////////////////////old data/////////////////////////////////////
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

	

}
