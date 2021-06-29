package billing.transactions;/*

/*
 * COMPULSORY CHARGES BY CONSULTANT
 * 
 * author:Manisha Gangwar
 * 
 * dated:23rd Jan 2019
 */

import java.util.LinkedHashMap;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.HLPAccountDtl;

public class CompulsoryChargesByConsultantDATA {

	public static void getPatientDtls(CompulsoryChargesByConsultantFB formBean,
			HttpServletRequest request) {
		String strmsgText = "";
		String strHospitalCode = "";
		CompulsoryChargesByConsultantVO vo = new CompulsoryChargesByConsultantVO();
		CompulsoryChargesByConsultantBO bo = new CompulsoryChargesByConsultantBO();
		
		try {
			strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(strHospitalCode);
			bo.getPatientDtls(vo);
			//System.out.println("vo.getStrChk()"+vo.getStrChk());
			String[] chk1={vo.getStrChk()};
			formBean.setChk(chk1);
			String strTemp[] = vo.getStrChk().replace('@', '#').split("#"); // Get The
			formBean.setStrAdmissionDate(strTemp[14]);
			formBean.setStrWardName(strTemp[15]);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			strmsgText = "billing.transactions.CompulsoryChargesByConsultantDATA.getPatientDtls(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"CompulsoryChargesByConsultantDATA->getPatientDtls()",
					strmsgText);
			
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			
			eObj = null;
		} finally {
			
			vo = null;
			bo = null;
		}

	}
	
	
	
	public static boolean PatientDtl(HttpServletRequest request,
			HttpServletResponse response, CompulsoryChargesByConsultantFB formBean,
			String chk) {
		// Declaring Variable
		String msgStr = "";
		String strChk = null;
		String strChk2 = null;
		boolean bRetVal = true;
		String s = null;

		String strClientApprovalDtlHlp = null;
		String strClientAcctDtlHlp = null;
		// String value= null;
		String strPatView = "";
		// Creating Object for VO
		IpdBillManagementTransVO vo = null;
		BillConfigUtil billConfigUtil = null;

		try {

			billConfigUtil = new BillConfigUtil(formBean.getStrHospitalCode());

			formBean.setStrExcessCreditLimit(billConfigUtil.getIpdExcessCreditLimit());
			formBean.setStrArogyaIpdCreditLimit(billConfigUtil.getStrArogyaIpdCreditLimit());
			/*if (request.getParameter("chk") == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = request.getParameter("chk");
				strChk2 = request.getParameter("chk");
			}*/
			String StrChkTemp ="";
			if(formBean.getStrChk().equals("")||formBean.getStrChk().equals(null))
			{
				String[] arr=formBean.getChk();
				StrChkTemp =arr[0];
				
			}
			else
			{
				StrChkTemp =formBean.getStrChk();
			}
			if (StrChkTemp == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = StrChkTemp;
				strChk2 = StrChkTemp;
			}
			if (StrChkTemp == null) {
				strChk = chk;
				strChk2 = chk;
			} else {
				strChk = StrChkTemp;
				strChk2 = StrChkTemp;
			}
			// System.out.println("Add Service
			// Length-->"+formBean.getStrComboValue().length);
			if (formBean.getStrComboValue().length == 3) {
				String s1[] = formBean.getStrComboValue();
				s = s1[1] + "^" + s1[2];

			} else {
				String s1[] = request.getParameterValues("strComboVal");
				s = s1[0];
			}
			String strChkBoxComboValue = strChk2 + "@" + s;

			formBean.setStrChkBoxComboValue(s);
			vo = new IpdBillManagementTransVO();
			// Set the Value into formBean
			formBean.setStrChk(strChk);

			String strTemp[] = strChk.replace('@', '#').split("#"); // Get The
			// Cr No....
			vo.setStrComboValue(formBean.getStrComboValue()); // Setting For
			// List Page
			// Combo Value

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrAccountNo(strTemp[0]);
			formBean.setStrComboValue(vo.getStrComboValue());
			formBean.setStrCrNo(strTemp[1]);
			formBean.setStrCtDate(vo.getStrCtDate());
			formBean.setStrAccountNo(strTemp[0]);
			
			// Pateint Detail HLP...Which..Replace TLD
			strPatView = PatientDtlHLP.compactPatientWithAdmissionDtlWithoutCatExpiryCheck(formBean.getStrCrNo(), vo.getStrHospitalCode(), false);
			formBean.setStrPatientDetailsView(strPatView);
			// HLP - Account Dtl With Exception Handling
			strClientAcctDtlHlp = HLPAccountDtl.getAccountDetailsHLP1(strTemp[0], strChkBoxComboValue, vo.getStrHospitalCode());
			String[] TestData1 = strClientAcctDtlHlp.split("\\####");
			if (TestData1[0].equals("ERROR")) {
				throw new Exception(TestData1[1]);
			} else {
				formBean.setStrClientAcctDtl(TestData1[0]);
			}

		} catch (Exception e) {

			e.printStackTrace();

			bRetVal = false;
			msgStr = e.getMessage();
			HisException eObj = new HisException("Billing",
					"CompulsoryChargesByConsultantDATA->PatientDtl()", msgStr);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			billConfigUtil = null;

		}

		return bRetVal;
	}
	
	public static void initAddServicesDtl(HttpServletRequest request,HttpServletResponse response, CompulsoryChargesByConsultantFB formBean , String strMode) 
	{
 
		CompulsoryChargesByConsultantBO compbo =null;
		IpdBillManagementTransBO bo = null;
		IpdBillManagementTransVO vo = null;
		CompulsoryChargesByConsultantVO cvo=null;
		HisUtil hisutil = null;
		BillConfigUtil configUtil = null;
		try {

			vo = new IpdBillManagementTransVO();
			bo = new IpdBillManagementTransBO();
			cvo= new CompulsoryChargesByConsultantVO();
			compbo =new CompulsoryChargesByConsultantBO();
			
			hisutil = new HisUtil("transaction", "CompulsoryChargesByConsultantDATA");
			configUtil = new BillConfigUtil(formBean.getStrHospitalCode());
			 
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrChargeTypeID(formBean.getStrChargeTypeID());
			vo.setStrAccountNo(formBean.getStrAccountNo());
			
			vo.setStrWardCode(formBean.getCombo()[2].replace("^", "#").split("#")[0]);
	
			//	vo.setStrWardType(formBean.getStrTempWardCode());
			vo.setStrWardType(formBean.getStrWardType());    // added for ipdchgtype   by manisha 
			
			
			formBean.setStrUrgSur(configUtil.getStrUrgTrfSur());
			
			bo.getAccountDtls(vo);
			formBean.setServiceFlag(vo.getServiceFlag());
			
			System.out.println("strAdvanceamt"+vo.getStrAdvanceamt());
			formBean.setGrpid(vo.getGrpid());
			formBean.setStrAdvanceamt(vo.getStrAdvanceamt());	
			formBean.setStrStartDate(vo.getStrStartDate());
			formBean.setStrEndDate(vo.getStrEndDate());
			
			vo.setStrWardType(formBean.getStrWardType());  	
			
			bo.getTreatmentCatAndWardTypeValues(vo);
			cvo.setStrCrNo(formBean.getStrCrNo());
			cvo.setStrHospitalCode(formBean.getStrHospitalCode());
			
			//compbo.getDefaultCompulsoryTariff(cvo);
			//formBean.setStrDefaultCompTariffId(cvo.getStrDefaultCompTariffId());
			
			
		/*	compbo.getCompulsoryTariffCombo(vo);
			
			WebRowSet ws2 = vo.getStrTariffNameCombo();
			
			formBean.setStrCtDate(vo.getStrCtDate());
			formBean.setStrGroupId(vo.getStrGroupId());
			formBean.setStrTariffCode(vo.getStrTariffCode());
			
			cvo.setStrCrNo(formBean.getStrCrNo());
			cvo.setStrHospitalCode(formBean.getStrHospitalCode());
			compbo.getDefaultCompulsoryTariff(cvo);
			formBean.setStrDefaultCompTariffId(cvo.getStrDefaultCompTariffId());
			
			String strTariffCmbo= "";
			if (ws2 != null) 
			{
				if(ws2.size() != 0)
				{
					//ws.next();
					if (ws2!= null && ws2.size() > 0) 
					{
						strTariffCmbo= new HisUtil("Billing", "HLPBilling").getOptionValue(ws2, cvo.getStrDefaultCompTariffId(), "", false);	
						//sb.append("<option value='0'>Select Value</option>");
						//sb.append("</select>");
						
					} 
					else 
					{
						strTariffCmbo="<option value='0'>Select Value</option>";
						//sb.append("</select>");
					}				
				} 
			
			}
			formBean.setStrTariffNameCombo(strTariffCmbo);
			
			*/
			
			
			if(vo.getStrMsgType().equals("1"))
			{				
				throw new Exception(vo.getStrMsgString());
			}
			
			String strTreatmentCat = "<option value='0'>Select Value</option>";
			
			if(vo.getOfflineTreatmentCategoryList() != null && vo.getOfflineTreatmentCategoryList().size() > 0)
			{			 
				strTreatmentCat = hisutil.getOptionValue(vo.getOfflineTreatmentCategoryList(),vo.getStrTreatmentCategory(), "", true);							
			}
			
			formBean.setStrTreatmentCategoryValues(strTreatmentCat);
			
			 if(strMode.equals("1"))
			 {				 
				 if(formBean.getStrTempWardCode().trim().length() > 0)
					 vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);
				 
				 if(vo.getStrWardType().equals("1"))
				 {						
					 formBean.setStrWardTypeValues("<option value='1'>GENERAL</option>");						
				 }
				 else
				 {						
						formBean.setStrWardTypeValues("<option value='2'>PRIVATE</option>");						
				 }				 
			 }
			 else
			 {				 
				 //if(formBean.getStrTempWardCode().trim().length() > 0)
				 //vo.setStrWardType(formBean.getStrTempWardCode().replace("^", "#").split("#")[0]);		
				 
				 String strWardType = "<option value='0'>Select Value</option>";
					
					if(vo.getWardTypeList() != null && vo.getWardTypeList().size() > 0)
					{					 
						strWardType = hisutil.getOptionValue(vo.getWardTypeList(),vo.getStrWardType() , "0^Select value", false , false);							
					}
					
					formBean.setStrWardTypeValues(strWardType);
					 String strDept = "<option value='0'>Select Value</option>";
						
						if(vo.getStrDepartmentList() != null && vo.getStrDepartmentList().size() > 0)
						{						 
							strDept = hisutil.getOptionValue(vo.getStrDepartmentList(),vo.getStrDepartment(), "0^Select value", true);							
						}
						
						formBean.setStrDepartmentValues(strDept);						
						String strPrevDates = "<option value='0'>Select Value</option>";
						
						if(vo.getStrPreviousDatesList() != null && vo.getStrPreviousDatesList().size() > 0)
						{							 
								strPrevDates = hisutil.getOptionValue(vo.getStrPreviousDatesList(),
											"0", "0^Select value", true);							
						}							
						formBean.setStrPreviousDateValues(strPrevDates);
						LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
						mapProcedureParam.put("mode_val", "1");
						mapProcedureParam.put("dept_code", vo.getStrDepartment());
						mapProcedureParam.put("hosp_code", formBean.getStrHospitalCode());
						mapProcedureParam.put("err", "#1");
						mapProcedureParam.put("resultset", "#2");
						
						formBean.setStrSpecialWardTypeValues(HisComboOptions.getOptionsFromProc("pkg_bill_view.proc_ward_list", mapProcedureParam, vo.getStrSpecialWardType(), "0^Select Value", false));									
			 }
					 
			
			
		//	bo.getAllTrfList(vo);
			
		//	String JSONObj=IpdBillManagementTransHLP.getAllTrfListJSON(vo); // mani
		
			
			compbo.getCompulsoryTariffCombo(vo);
			
			
			String JSONObj=IpdBillManagementTransHLP.getCompusloryChargesDtlListJSON(vo);
			
			
			//System.out.println("JSONObj"+JSONObj);
			formBean.setStrAllTrfJSON(JSONObj);
			formBean.setStrAllTrfHLP(vo.getStrAllTrfHLP());
			
	
		} 
		catch (Exception e) 
		{
			HisException eObj = new HisException("Billing","CompulsoryChargesByConsultantDATA->initClientApprovalDtl()",e.getMessage());
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			vo = null;
			hisutil = null;
		}		
	}
	
	
	public static boolean InsertCompChargesByConsultant(CompulsoryChargesByConsultantFB formBean,UserVO userVO) {

		// Declaring Variables
		boolean fretValue = true;
		// String strErrmsg = "";
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";
		// Creating VO & BO Object
		CompulsoryChargesByConsultantVO vo = null;
		IpdBillManagementTransBO bo = null;
		CompulsoryChargesByConsultantBO compbo =null;

		try {
			vo = new CompulsoryChargesByConsultantVO();
			compbo= new CompulsoryChargesByConsultantBO(); 
			// Set value into VO
			String strTemp[] = formBean.getStrChk().replace("@", "#").split("#");
			
			vo.setStrPatAcctNo(strTemp[0]);
			vo.setStrCrNo(strTemp[1]);
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrCompulsoryTariffCode(formBean.getStrCompulsoryTariffCode());
			vo.setStrNumRows(formBean.getStrNumRows());
			vo.setStrDefaultCompTariff(formBean.getStrDefaultCompTariff());
			vo.setStrGrpId(formBean.getStrGrpId());
						
			// Calling BO insert method 
			fretValue = compbo.insertPorcedure(vo,userVO);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			if (fretValue) {
				strMsg = "Record saved successfully!";
				vo.setStrMsg(strMsg);
			} else {
				strWarning = "Record Not Saved!Data Already Exist!!";
				vo.setStrWarning(strWarning);
			}
		} catch (Exception e) {
			fretValue = false;
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing",
					"CompulsoryChargesByConsultantDATA->InserRecord()", strmsgText);
			formBean.setStrErrMsg("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			vo = null;
			bo = null;
		}
		return fretValue;
	}
	
	
	public static void getWardOnBasisOfUnitCode(CompulsoryChargesByConsultantFB beanObj, String unitcode,
			HttpServletRequest request,HttpServletResponse response)  {

			CompulsoryChargesByConsultantVO VO = null;
			CompulsoryChargesByConsultantBO bo = null;
				
				HisUtil util = null;
				String strward = "";

				try 
				{
					VO = new CompulsoryChargesByConsultantVO();
					VO.setStrHospitalCode(beanObj.getStrHospitalCode());
					bo = new CompulsoryChargesByConsultantBO();
					/*if (modUnit != null)
						beanObj.setStrUnit(modUnit);
					VO.setStrUnit(beanObj.getStrUnit());
					 */
					bo.getWardOnBasisOfUnitCode(VO);
					if (VO.getStrMsgType().equals("1")) 
					{ 
						throw new Exception(VO.getStrMsgString());
					}
					beanObj.setStrMsgString(VO.getStrMsgString());
					beanObj.setStrMsgType(VO.getStrMsgType());

					util = new HisUtil("ipd", "CompulsoryChargesByConsultantDATA");
					strward = util.getOptionValue(VO.getStrWardnameCombo(), beanObj.getStrWardCode(), "0^Select Value", false);
	
	
	public static void getWardOnBasisOfUnitCode(CompulsoryChargesByConsultantFB beanObj, String unitcode,
			HttpServletRequest request,HttpServletResponse response)  {

			CompulsoryChargesByConsultantVO VO = null;
			CompulsoryChargesByConsultantBO bo = null;
				
				HisUtil util = null;
				String strward = "";

				try 
				{
					VO = new CompulsoryChargesByConsultantVO();
					VO.setStrHospitalCode(beanObj.getStrHospitalCode());
					bo = new CompulsoryChargesByConsultantBO();
					/*if (modUnit != null)
						beanObj.setStrUnit(modUnit);
					VO.setStrUnit(beanObj.getStrUnit());
					 */
					bo.getWardOnBasisOfUnitCode(VO);
					if (VO.getStrMsgType().equals("1")) 
					{ 
						throw new Exception(VO.getStrMsgString());
					}
					beanObj.setStrMsgString(VO.getStrMsgString());
					beanObj.setStrMsgType(VO.getStrMsgType());

					if (beanObj.getStrMsgType().equals("1")) 
					{
						throw new Exception(beanObj.getStrMsgString());
					} 
					else 
					{
						response.setHeader("Cache-Control", "no-cache");
						//beanObj.setStrwardproperty(strward);
					}
				}
				catch (Exception e) 
				{
					VO.setStrMsgString(e.getMessage());
					VO.setStrMsgType("1");
					HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.ward()-->", VO.getStrMsgString());
					try 
					{
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().print("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
					} 
					catch (Exception e1) 
					{
					}
					eObj = null;
				} 
				finally 
				{
					if (VO != null)
						VO = null;
					if (bo != null)
						bo = null;
					if (util != null)
						util = null;
				}
			}
	
					util = new HisUtil("ipd", "CompulsoryChargesByConsultantDATA");
					strward = util.getOptionValue(VO.getStrWardnameCombo(), beanObj.getStrWardCode(), "0^Select Value", false);

					if (beanObj.getStrMsgType().equals("1")) 
					{
						throw new Exception(beanObj.getStrMsgString());
					} 
					else 
					{
						response.setHeader("Cache-Control", "no-cache");
						//beanObj.setStrwardproperty(strward);
					}
				}
				catch (Exception e) 
				{
					VO.setStrMsgString(e.getMessage());
					VO.setStrMsgType("1");
					HisException eObj = new HisException("IPD->Transactions->NursingDesk","NursingDeskTransDATA.ward()-->", VO.getStrMsgString());
					try 
					{
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().print("ERROR####Application Error [ERROR ID : "+ eObj.getErrorID()+ "], Contact System Administrator! ");
					} 
					catch (Exception e1) 
					{
					}
					eObj = null;
				} 
				finally 
				{
					if (VO != null)
						VO = null;
					if (bo != null)
						bo = null;
					if (util != null)
						util = null;
				}
			}
	
	
}
