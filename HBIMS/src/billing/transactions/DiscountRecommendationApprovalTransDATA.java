package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

public class DiscountRecommendationApprovalTransDATA {
	public static void initClientDetail(String strChk,
			DiscountRecommendationApprovalTransFB formBean) {
		String strmsgText = null;
		DiscountRecommendationApprovalTransVO vo = null;
		vo.setStrChk(strChk);

		try {
			vo = new DiscountRecommendationApprovalTransVO();
			formBean.setStrCrNo(strChk);
		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountRecommendationApprovalTransDATA->initClientDetail()", strmsgText);
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
	public static String AjaxResponse(DiscountRecommendationApprovalTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		// Declearing Variables
		String strmsgText = "";
		WebRowSet wb = null;
		// String temp = null;
		int hlp_opt_sel = 0;
		// String strProcMode = "";
		// HisUtil hisutil = null;

		// Createing Object
		DiscountRecommendationApprovalTransVO vo = new DiscountRecommendationApprovalTransVO();
		DiscountRecommendationApprovalTransBO bo = new DiscountRecommendationApprovalTransBO();
		// hisutil = new HisUtil("transaction", "IpdBillManagementTransDATA");

		String strValmode = (String) request.getParameter("modName");
		String strDeptCode = (String)request.getParameter("deptCode");
		
		// System.out.println(" DATA AjaxResponse strValmode"+strValmode);
		vo.setStrHospitalCode(formBean.getStrHospitalCode());

		vo.setStrDepartment(strDeptCode);
			
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
				tariffDetails = DiscountRecommendationApprovalTransHLP.getTariffDetails(wb,
						vo);
			if (hlp_opt_sel == 2)
				tariffDetails = DiscountRecommendationApprovalTransHLP.getIPDBillSetlmnt(wb,
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

	public static void getDepartmentList(DiscountRecommendationApprovalTransFB formBean , HttpServletRequest request) {

		DiscountRecommendationApprovalTransVO vo = null;
		DiscountRecommendationApprovalTransBO bo = null;
		HisUtil util = null;

		try {

			vo = new DiscountRecommendationApprovalTransVO();
			bo = new DiscountRecommendationApprovalTransBO();
			util = new HisUtil("Discount Recommendation Approval Trans",
					"DiscountRecommendationApprovalTransDATA.getDepartmentList()");

			formBean.setStrHospitalCode(request.getSession().getAttribute(
			"HOSPITAL_CODE").toString());
			
			formBean.setStrSeatId(request.getSession().getAttribute(
			"SEATID").toString());
			
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getDepartmentList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.getWsDepartmentValues() != null
					&& vo.getWsDepartmentValues().size() > 0) {

				formBean
						.setStrDepartmentValues(util.getOptionValue(vo
								.getWsDepartmentValues(), formBean.getStrDepartment(),
								"0^Select Value", false));

			} else {

				formBean
						.setStrDepartmentValues("<option value='0'>Select Value</option>");
			}

		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"DiscountRecommendationApprovalTransDATA->getDepartmentList()", e
							.getMessage());
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {

			vo = null;
			bo = null;
			util = null;
		}

	}
	
	
	public static void getUnitList( HttpServletRequest request , HttpServletResponse response) {

		DiscountRecommendationApprovalTransVO vo = null;
		DiscountRecommendationApprovalTransBO bo = null;
		HisUtil util = null;
		String strTemp = "";	
		
		try {

			vo = new DiscountRecommendationApprovalTransVO();
			bo = new DiscountRecommendationApprovalTransBO();
			util = new HisUtil("Discount Recommendation Approval Trans",
					"DiscountRecommendationApprovalTransDATA.getDepartmentList()");

			String strDeptCode = request.getParameter("deptCode");
		
			vo.setStrDepartment(strDeptCode);
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo.getUnitList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			if (vo.getWsUnitValues() != null
					&& vo.getWsUnitValues().size() > 0) {

				strTemp = util.getOptionValue(vo
								.getWsUnitValues(), "0",
								"0^Select Value", false);

			} else {

				strTemp = "<option value='0'>Select Value</option>";
			}

			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strTemp);
			
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try {
				HisException eObj = new HisException("Billing",
						"DiscountRecommendationApprovalTransDATA->getUnitList()",
						strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			} catch (IOException e1) {
				new HisException("Billing",
						"DiscountRecommendationApprovalTransDATA->getUnitList()", e1
								.getMessage());
			}
		} finally {

			vo = null;
			bo = null;
			util = null;
		}

	}
	
	
	public static void getRsnRmk(DiscountRecommendationApprovalTransFB formBean) {

		String strmsgText = null;
		DiscountRecommendationApprovalTransVO vo = null;
		DiscountRecommendationApprovalTransBO bo = null;
		// HisUtil hisutil = null;
		// String temp = null;

		try {
			if (formBean.getStrMsgType().equals("0")) {
				vo = new DiscountRecommendationApprovalTransVO();
				bo = new DiscountRecommendationApprovalTransBO();
				// hisutil = new HisUtil("transaction",
				// "DiscountRecommendationApprovalTransDATA.getRsnRmk()");

				vo.setStrHospitalCode(formBean.getStrHospitalCode());
				bo.getRsnRmk(vo);
				formBean.setStrRsn(vo.getStrRsn());
				formBean.setStrRmk(vo.getStrRmk());
			} else {
				formBean.setStrRsn("<option value = '0'>Select Value</option>");
				formBean.setStrRmk("<option value = '0'>Select Value</option>");
			}

		} catch (Exception e) {
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountRecommendationApprovalTransDATA->getRsnRmk()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {
		
				vo = null;
		
				bo = null;
		}
	}

	// /////////////////////////////////////////////////////////////////////////////

	public static void initOnlineReq(String strChk,
			DiscountRecommendationApprovalTransFB formBean) {

		DiscountRecommendationApprovalTransVO vo = null;
	
		String strmsgText = null;

		try {
			vo = new DiscountRecommendationApprovalTransVO();
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
					"DiscountRecommendationApprovalTransDATA->initOnlineReq()", strmsgText);

			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
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
	public static void PopUpResponse(DiscountRecommendationApprovalTransFB formbean,
			HttpServletRequest request, HttpServletResponse response) {
		String pop_fld = null;
		WebRowSet ws = null;
		String strmsgText = null;
		DiscountRecommendationApprovalTransVO vo = null;
		DiscountRecommendationApprovalTransBO bo = null;
		String strDisBy = null;
		String strDisDate = null;
		String strDisReson = null;

		try {
			vo = new DiscountRecommendationApprovalTransVO();
			bo = new DiscountRecommendationApprovalTransBO();

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
						"DiscountRecommendationApprovalTransDATA->PopUpResponse()",
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
	public static boolean getInsertData(DiscountRecommendationApprovalTransFB formBean,
			HttpServletRequest request) {
		// Declare Variables
		boolean fretValue = false;
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		int length = 0; //, add = 0, add1 = 0, Finalsum = 0;
	//	String[] TestData4 = null;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		if(request.getSession().getAttribute("USER_LEVEL") != null){
			
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL")
					.toString());
			
		}else{
			formBean.setStrUserLevel("1");
		}
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		// Create Object for VO & BO
		DiscountRecommendationApprovalTransVO vo = null;
		DiscountRecommendationApprovalTransBO bo = null;

		try {
			vo = new DiscountRecommendationApprovalTransVO();
			bo = new DiscountRecommendationApprovalTransBO();
			int bServiceId = Integer.parseInt(formBean.getHlpBServiceID());
			vo.setStrBserviceId(formBean.getHlpBServiceID());

			vo.setStrDepartment(formBean.getStrDepartment());	
			
			if (bServiceId != 21) {
				if (formBean.getTrfDisApproval() != null) {

					length = formBean.getTrfDisApproval().length;
					if (length > 0) {

						vo.setStrLength(length);
						vo.setTrfDisApproval(formBean.getTrfDisApproval());
						vo.setStrhiddTrfAddDis(formBean.getStrhiddTrfAddDis());	//check box value [trf id ^ app id]
						vo.setStrHospitalCode(formBean.getStrHospitalCode()); // 1
						vo.setStrSeatId(formBean.getStrSeatId()); // 2
						vo.setStrUserLevel(formBean.getStrUserLevel()); // 3
						vo.setStrHidValOnLineReqDis(formBean
								.getStrHidValOnLineReqDis()); // 4
						vo.setApprovalId(formBean.getStrApprovId()); // 5
						vo.setStrTariffIdInsert(formBean.getStrTrfId()); // 6
						vo.setStrPrevDisType(formBean.getStrDiscountType()); // 7
						vo.setLstDisService(formBean.getStrDiscountUnit());
						
						vo.setOpd_discount(formBean.getOpd_discount());
						vo.setOpd_discountType(formBean.getOpd_discountType()); // 9
						vo.setStrRmk(formBean.getStrRmk()); // discount by
						vo.setStrDrt(formBean.getStrDrt()); // discount reason
						
						fretValue = bo.getInsertDataForServices(vo);
					//}
					}
				}
			} else {
				// final settlement
				if (bServiceId == 21) {
/*
					if (formBean.getStrhiddTrfFADis().equals("1")) {
						add = 0;
						add1 = 0;
						add = Integer.parseInt(formBean.getStrIpdBillCurrDis());
						add1 = Integer.parseInt(formBean.getStrLstDisUnit());
						Finalsum = Finalsum + add + add1;

					} else {
						add = 0;
						add1 = 0;
						add = Integer.parseInt(formBean.getStrIpdBillCurrDis());
						Finalsum = Finalsum + add + add1;
					}
					String sumFA = String.valueOf(Finalsum);
					TestData4 = formBean.getStrIpdBillFianlDis().split("\\-");
					*/
					
					//vo.setStrhiddTrfFADis(formBean.getStrhiddTrfFADis());
					vo.setStrHospitalCode(formBean.getStrHospitalCode());
					vo.setStrSeatId(formBean.getStrSeatId());
					vo.setStrUserLevel(formBean.getStrUserLevel());
					vo.setStrHidValOnLineReqDis(formBean
							.getStrHidValOnLineReqDis());
					/** ************* IMP VALUE **************** */
					vo.setApprovalId(formBean.getStrApprovId()); // 5
					vo.setStrPrevDisType(formBean.getStrDiscountType()); // 7
					vo.setLstDisService(formBean.getStrDiscountUnit());
					
					//vo.setOpd_discount(formBean.getOpd_discount());
					//vo.setOpd_discountType(formBean.getOpd_discountType()); // 9
					
					
					//vo.setApproval_id(formBean.getApproval_id()); // 1
					//vo.setSumFAdjustment(TestData4[1]); // 2
					//vo.setStrPrevDisTypeFA(formBean.getStrPrevDisTypeFA()); // 3
					//vo.setStrLstDisUnitFA(formBean.getStrLstDisUnit()); // 4
					//vo.setStrCurrDisTypeFA(formBean.getDiscountType()); // 5
					vo.setStrTrfId(formBean.getTariff_id()); // 6
					vo.setStrIpdBilldiscountType(formBean.getDiscountType()); // 7
					vo.setStrIpdBillCurrDis(formBean.getStrIpdBillCurrDis()); // 8

					vo.setStrRmk(formBean.getStrRmk());
					vo.setStrDrt(formBean.getStrDrt());
					vo.setStrhiddTrfFADis(formBean.getStrhiddTrfFADis());
					
					fretValue = bo.getInsertDataForFinalAdjustment(vo);
				}
			}

			if (fretValue) {
				strMsg = "Discount Approval successfully Inserted!!";
				vo.setStrMsg(strMsg);
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
			// e.printStackTrace();
			strmsgText = "billing.transactions.DiscountRecommendationApprovalTransDATA.getInsertData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"DiscountRecommendationApprovalTransaction->getInsertData()", strmsgText);
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

}
