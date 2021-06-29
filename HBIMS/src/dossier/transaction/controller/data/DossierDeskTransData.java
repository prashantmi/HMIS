package dossier.transaction.controller.data;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import mms.MmsConfigUtil;
import dossier.transaction.bo.DossierDeskTransBO;
import dossier.transaction.controller.fb.DossierDeskTransFB;
import dossier.transaction.controller.hlp.DossierDeskTransHLP;
import dossier.transaction.dao.DossierDeskTransDAO;
import dossier.transaction.vo.DossierDeskTransVO;


public class DossierDeskTransData {
	public static void getInitDetailForIssuePage(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request) {

		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strPatientDtl="";
		String strmsgText = "";
		String strRequestTypeId="";
		String strRaisingReqTypeId="";
		String strIssueItemId="";
		String strRequestDtl="";
		String strChk="";
		String temp[]=null;
		String hosCode="";
		String strRequestItemDtl="";
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		StringBuffer buff=null;
		BillConfigUtil billConfig=null;
		String RaisingStoreId="RaisingStoreI";
		try {
				bo = new DossierDeskTransBO();
				vo = new DossierDeskTransVO();
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				billConfig=new BillConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_DossierDeskTransFB.setCombo(request.getParameterValues("combo"));
					strRaisingReqTypeId=request.getParameterValues("combo")[1];
					strStoreId=request.getParameterValues("combo")[0];
					_DossierDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
					
			 	}
			 strChk=request.getParameter("chk");
			 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
			 {
				 _DossierDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
				 _DossierDeskTransFB.setStrStoreName(_DossierDeskTransFB.getComboValue());
			 }
			/**
			 * This is used set variables during request type equal to patient
			 */
//			if(strRaisingReqTypeId.equals("13")){
				temp=strChk.replace('@', '#').split("#");
				//_DossierDeskTransFB.setStrItemCategNo(temp[0]);
				_DossierDeskTransFB.setStrLpRequestNo(temp[0]);
				_DossierDeskTransFB.setStrRequestDate(temp[14]);
				//_DossierDeskTransFB.setStrRaisingStoreId(temp[3]);
				_DossierDeskTransFB.setStrCrNo(temp[2]);
				_DossierDeskTransFB.setStrBSReqNo(temp[0]);
				_DossierDeskTransFB.setStrPoNo("0");
				_DossierDeskTransFB.setStrPoStoreId("0");
				_DossierDeskTransFB.setStrUrgentFlg("0");
				_DossierDeskTransFB.setStrStoreId(strStoreId);
				strRequestTypeId="98";
//			}
//			/**
//			 * This is used set variables during request type equal to employee
//			 */
//			else if(strRaisingReqTypeId.equals("12")){
//				
//				temp=strChk.replace('@', '#').split("#");
//				_DossierDeskTransFB.setStrItemCategNo(temp[0]);
//				_DossierDeskTransFB.setStrLpRequestNo(temp[1]);
//				_DossierDeskTransFB.setStrRequestDate(temp[2]);
//				_DossierDeskTransFB.setStrRaisingStoreId(temp[3]);
//				_DossierDeskTransFB.setStrCrNo(temp[4]);
//				_DossierDeskTransFB.setStrEmpNo(temp[5]);
//				_DossierDeskTransFB.setStrPoNo(temp[6]);
//				_DossierDeskTransFB.setStrPoStoreId(temp[7]);
//				 strRequestTypeId="36";
//				
//			}
//			/**
//			 * This is used set variables during request type equal to department
//			 */
//			else
//			{
//				 temp=strChk.replace('@', '#').split("#");
//				 _DossierDeskTransFB.setStrItemCategNo(temp[0]);
//				_DossierDeskTransFB.setStrLpRequestNo(temp[1]);
//				_DossierDeskTransFB.setStrRequestDate(temp[2]);
//				_DossierDeskTransFB.setStrRaisingStoreId(temp[3]);
//				_DossierDeskTransFB.setStrPoNo(temp[4]);
//				_DossierDeskTransFB.setStrPoStoreId(temp[5]);
//				strRequestTypeId="37";
//			}
			
//			if(strRaisingReqTypeId.equals("13")|| strRaisingReqTypeId.equals("12"))
//			{
				strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_DossierDeskTransFB.getStrCrNo(), hosCode, true);
//			}
			_DossierDeskTransFB.setStrPatientDtl(strPatientDtl);
			
			
			
			
			vo.setStrHospitalCode(hosCode);
			vo.setStrRaisingStoreId(_DossierDeskTransFB.getStrRaisingStoreId());
			vo.setStrLpRequestNo(_DossierDeskTransFB.getStrLpRequestNo());
			vo.setStrRequestDate(_DossierDeskTransFB.getStrRequestDate());
			vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
			vo.setStrPoStoreId(_DossierDeskTransFB.getStrPoStoreId());
			vo.setStrPoNo(_DossierDeskTransFB.getStrPoNo());
			vo.setStrCrNo(_DossierDeskTransFB.getStrCrNo());
			vo.setStrUrgentFlg(_DossierDeskTransFB.getStrUrgentFlg());
			vo.setStrFinStartDate(mmsConfig.getStrFinancialStartDate(strStoreId, hosCode));
			vo.setStrFinEndDate(mmsConfig.getStrFinancialEndDate(strStoreId, hosCode));
			vo.setStrBSReqNo("0");
			vo.setStrStoreId(_DossierDeskTransFB.getStrStoreId());
			//if(_DossierDeskTransFB.getStrBSReqNo().equals("0"))
				bo.getLPRequestDetail(vo);
			/*else
				bo.getLPRequestDetail_new(vo);
			*/
			
			_DossierDeskTransFB.setStrDeptName(vo.getStrDeptName());
			_DossierDeskTransFB.setStrBillingHiddenValue(vo.getStrBillingHiddenValue());
			WebRowSet wb = vo.getPatAccountDtl();
			if(wb!=null)
			{
				if(wb.size()!=0) 
				{
					
					while(wb.next()){
						_DossierDeskTransFB.setStrPatAccountNo(wb.getString(1));
						_DossierDeskTransFB.setStrPatAccountBal(wb.getString(2));
					}
				}
			}
			else
			{
				
			}
			_DossierDeskTransFB.setStrHospitalCode(hosCode);
			strRequestDtl=DossierDeskTransHLP.initViewForIssueAddPage(_DossierDeskTransFB);
			
			//_DossierDeskTransFB.setStrDiagDtl(patientDiagDtl(_DossierDeskTransFB.getStrCrNo(),hosCode));
			
			
			
			_DossierDeskTransFB.setStrRequestDtl(strRequestDtl);
			System.out.println("mmsConfig.getStrSCMIntegration"+mmsConfig.getStrSCMIntegration().equals("1"));
			System.out.println("vo.getStrLpRequestNo"+vo.getStrLpRequestNo().substring(0,2).equals("10"));
			if(mmsConfig.getStrSCMIntegration().equals("1") && vo.getStrLpRequestNo().substring(0,2).equals("11"))
			{
				buff=new StringBuffer();
				buff.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
				buff.append("<tr>");
				buff.append("<td class='multiControl'>Integration with e-Aushadhi is yes. So,issue can't be processed</td></tr></table>");
				
				strIssueItemId = buff.toString();
			}
			else
			{
				strRequestItemDtl=DossierDeskTransHLP.getItemDetails(vo.getRequestItemDtlWS());
				
				_DossierDeskTransFB.setStrRequestItemDtl(strRequestItemDtl);
				
				strIssueItemId=DossierDeskTransHLP.getIssueItemDetails(vo.getIssueItemDtlWS(),strCostReq,strStoreId,hosCode,vo.getStrRaisingStoreId(),vo.getStrBSReqNo(),vo);
			
				
			}
			
			_DossierDeskTransFB.setStrIssueItemId(strIssueItemId);
			
			_DossierDeskTransFB.setStrChk(strChk);
			
			_DossierDeskTransFB.setStrRequestTypeId(strRequestTypeId);
			
			_DossierDeskTransFB.setStrStoreId(strStoreId);
			
			_DossierDeskTransFB.setCombo(request.getParameterValues("combo"));
			
			_DossierDeskTransFB.setStrSCMIntegration(mmsConfig.getStrSCMIntegration());
			 
			//_DossierDeskTransFB.setStrBillPaidCat(billConfig.getStrPaidCategory());
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void getInitDetailForReturnPage(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request) {

		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strPatientDtl="";
		String strmsgText = "";
		String strRequestTypeId="";
		String strRaisingReqTypeId="";
		
		String strRequestDtl="";
		String strChk="";
		String temp[]=null;
		String hosCode="";
		HisUtil hisutil=null;
		String cmbVal="";
		String cmbVal1="";
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		try {
			
			
				bo = new DossierDeskTransBO();
				vo = new DossierDeskTransVO();
				hisutil = new HisUtil("dossier","LPIssueDeskTransDATA");
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_DossierDeskTransFB.setCombo(request.getParameterValues("combo"));
					 strRaisingReqTypeId=request.getParameterValues("combo")[1];
					 strStoreId=request.getParameterValues("combo")[0];
					_DossierDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
			 	}
				strChk=request.getParameter("chk");
				 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
				 {
					 _DossierDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
					 _DossierDeskTransFB.setStrStoreName(_DossierDeskTransFB.getComboValue());
				 }
				
				/**
				 * This is used set variables during request type equal to patient
				 */
				if(strRaisingReqTypeId.equals("35") || strRaisingReqTypeId.equals("32")){//Patient
					
					temp=strChk.replace('@', '#').split("#");
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					strRequestTypeId="44";
				}
				/**
				 * This is used set variables during request type equal to employee
				 */
				else if(strRaisingReqTypeId.equals("12")){//Staff
					
					temp=strChk.replace('@', '#').split("#");
					
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					_DossierDeskTransFB.setStrEmpNo(temp[3]);
					
					
					strRequestTypeId="45";
				}
				/**
				 * This is used set variables during request type equal to department
				 */
				else//Dossier Patient
				{
					 temp=strChk.replace('@', '#').split("#");
					 _DossierDeskTransFB.setStrItemCategNo("95");
						_DossierDeskTransFB.setStrIssueNo(temp[17]);
						_DossierDeskTransFB.setStrIssueStoreId(temp[16]);
						_DossierDeskTransFB.setStrIssueDate(temp[6]);
						_DossierDeskTransFB.setStrStoreName(temp[8]);
						_DossierDeskTransFB.setStrCrNo(temp[2]);
						_DossierDeskTransFB.setStrStoreName(temp[17]);
						_DossierDeskTransFB.setStrCrno1(temp[2]);
						_DossierDeskTransFB.setStrStoreId(temp[16]);
						_DossierDeskTransFB.setStrServicetype(temp[5]);
						_DossierDeskTransFB.setStrDossierId(temp[8]);
						_DossierDeskTransFB.setStrPatientType(temp[19].replace("$", "#").split("#")[0]);
					strRequestTypeId="97";
				}
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrCrNo(_DossierDeskTransFB.getStrCrNo());
				DossierDeskTransDAO.getPatientAccountDetails(vo);
				
				strPatientDtl = PatientDtlHLP.patientWithAdmissionDtlRequestForLP(_DossierDeskTransFB.getStrCrNo(),vo.getStrHospitalCode(),vo.getStrBalanceAmount(),vo.getStrPatAccountNo(),true,_DossierDeskTransFB.getStrPatientType());
				/*if(strRaisingReqTypeId.equals("35")|| strRaisingReqTypeId.equals("12") || strRaisingReqTypeId.equals("32") || strRequestTypeId.equalsIgnoreCase("97"))
				{
					strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_DossierDeskTransFB.getStrCrNo(), hosCode, true);
				}*/ // commented by shalini to get patient account balance on page to check whether acc bal is +ve or not  
				_DossierDeskTransFB.setStrPatientDtl(strPatientDtl);
							
				
				vo.setStrIssueNo(_DossierDeskTransFB.getStrIssueNo());
				vo.setStrIssueStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
				vo.setStrRaisingStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				bo.getIssueItemDetail(vo);
				
				
				strRequestDtl=DossierDeskTransHLP.getIssuedItemDetails(vo.getIssuedItemDtlWS(),vo.getStrHospitalCode(),strCostReq);
				
				if(vo.getIssuedItemDtlWS().size()!=0) 
				{
					vo.getIssuedItemDtlWS().beforeFirst(); 
					if(vo.getIssuedItemDtlWS().next())
						_DossierDeskTransFB.setStrStoreName(vo.getIssuedItemDtlWS().getString(1).split("@")[15]);
				}
				
				_DossierDeskTransFB.setStrDeptName(vo.getStrDeptName());
				_DossierDeskTransFB.setStrRequestTypeId(strRequestTypeId);
				//_DossierDeskTransFB.setStrStoreId(strStoreId);
				_DossierDeskTransFB.setStrRequestDtl(strRequestDtl);
				_DossierDeskTransFB.setStrDoseFrqFlg("0");
				_DossierDeskTransFB.setStrChk(strChk);
				if (vo.getApprovedBy() != null
						&& vo.getApprovedBy().size() > 0) {
					cmbVal = hisutil.getOptionValue(vo.getApprovedBy(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal = "<option value='0'>Select Value</option>";
				}
				
				if (vo.getStrItemCatWs() != null
						&& vo.getStrItemCatWs().size() > 0) {
					cmbVal1 = hisutil.getOptionValue(vo.getStrItemCatWs(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal1 = "<option value='0'>Select Value</option>";
				}
			_DossierDeskTransFB.setStrRecommendByVal(cmbVal);
			_DossierDeskTransFB.setStrItemCatValues(cmbVal1);	 
			_DossierDeskTransFB.setStrBillingHiddenValue(vo.getStrBillingHiddenValue());
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}
	
	public static void getInitDetailForReturnViewPage(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request) {

		/*DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strPatientDtl="";
		String strmsgText = "";
		String strRequestTypeId="";
		String strRaisingReqTypeId="";
		
		String strRequestDtl="";
		String strChk="";
		String temp[]=null;
		String hosCode="";
		String status;
		
		String strStoreId="";
		try {
			
			    bo = new DossierDeskTransBO();
				vo = new DossierDeskTransVO();
				
				 hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
				
				   _DossierDeskTransFB.setCombo(request.getParameterValues("combo"));
				  _DossierDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
				  _DossierDeskTransFB.setStrStoreName(_DossierDeskTransFB.getComboValue());
				  strRequestTypeId=request.getParameterValues("combo")[1];
				   strStoreId=request.getParameterValues("combo")[0];
				   status=request.getParameterValues("combo")[2];
				  _DossierDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
				  strChk=request.getParameter("chk");
				  _DossierDeskTransFB.setStrStoreId(strStoreId);
				
				
				*//**
				 * This is used set variables during request type equal to patient
				 *//*
				if(strRequestTypeId.equals("35")){
					
					 temp=strChk.replace('@', '#').split("#");
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(strStoreId);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					_DossierDeskTransFB.setStrLpRequestNo(temp[6]);
					_DossierDeskTransFB.setStrRequestDate(temp[7]);
					strRaisingReqTypeId="13";
				}
				else
					if(strRequestTypeId.equals("32")){
						
						 temp=strChk.replace('@', '#').split("#");
						_DossierDeskTransFB.setStrItemCategNo(temp[4]);
						_DossierDeskTransFB.setStrIssueNo(temp[1]);
						_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
						_DossierDeskTransFB.setStrCrNo(temp[2]);
						_DossierDeskTransFB.setStrIssueDate(temp[5]);
						
						
					}
				*//**
				 * This is used set variables during request type equal to employee
				 *//*
				else if(strRaisingReqTypeId.equals("12")){
					
					temp=strChk.replace('@', '#').split("#");
					
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					_DossierDeskTransFB.setStrEmpNo(temp[3]);
					
					
					strRequestTypeId="36";
				}
				*//**
				 * This is used set variables during request type equal to department
				 *//*
				else
				{
					 temp=strChk.replace('@', '#').split("#");
					 _DossierDeskTransFB.setStrItemCategNo(temp[0]);
					_DossierDeskTransFB.setStrLpRequestNo(temp[1]);
					_DossierDeskTransFB.setStrRequestDate(temp[2]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[3]);
					_DossierDeskTransFB.setStrStoreId(strStoreId);
					_DossierDeskTransFB.setStrServicetype(temp[4]);
					_DossierDeskTransFB.setStrDossierId(temp[7]);
					strRequestTypeId="37";
				}
				
				if(strRaisingReqTypeId.equals("35")|| strRaisingReqTypeId.equals("12") || strRaisingReqTypeId.equals("13")||strRequestTypeId.equals("32"))
				{
					strPatientDtl=PatientDtlHLP.patientWithAdmissionDtlView(_DossierDeskTransFB.getStrCrNo(), hosCode, true);
				}
				_DossierDeskTransFB.setStrPatientDtl(strPatientDtl);
							
				
				vo.setStrIssueNo(_DossierDeskTransFB.getStrIssueNo());
				vo.setStrIssueStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
				vo.setStrRaisingStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				if(status.equals("2"))
					vo.setStrRequestTypeId(strRequestTypeId);
				
				
				bo.getIssueItemDetailview(vo);
				_DossierDeskTransFB.setStrDeptName(vo.getStrDeptName());
			    _DossierDeskTransFB.setStrRequestTypeId(strRequestTypeId);
				strRequestDtl=DossierDeskTransHLP.getIssuedItemDetailsForReturnView(vo.getIssuedItemDtlWS(),vo.getStrHospitalCode());
				_DossierDeskTransFB.setStrRequestDtl(strRequestDtl);
				
			
						 
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForReturnViewPage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getInitDetailForReturnViewPage()", strmsgText);
			_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}*/
		
		
		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strPatientDtl="";
		String strmsgText = "";
		String strRequestTypeId="";
		String strRaisingReqTypeId="";
		
		String strRequestDtl="";
		String strChk="";
		String temp[]=null;
		String hosCode="";
		HisUtil hisutil=null;
		String cmbVal="";
		String cmbVal1="";
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		try {
			
			
				bo = new DossierDeskTransBO();
				vo = new DossierDeskTransVO();
				hisutil = new HisUtil("dossier","LPIssueDeskTransDATA");
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_DossierDeskTransFB.setCombo(request.getParameterValues("combo"));
					 strRaisingReqTypeId=request.getParameterValues("combo")[1];
					 strStoreId=request.getParameterValues("combo")[0];
					_DossierDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
			 	}
				strChk=request.getParameter("chk");
				 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
				 {
					 _DossierDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
					 _DossierDeskTransFB.setStrStoreName(_DossierDeskTransFB.getComboValue());
				 }
				
				/**
				 * This is used set variables during request type equal to patient
				 */
				if(strRaisingReqTypeId.equals("35") || strRaisingReqTypeId.equals("32")){//Patient
					
					temp=strChk.replace('@', '#').split("#");
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					strRequestTypeId="44";
				}
				/**
				 * This is used set variables during request type equal to employee
				 */
				else if(strRaisingReqTypeId.equals("12")){//Staff
					
					temp=strChk.replace('@', '#').split("#");
					
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					_DossierDeskTransFB.setStrEmpNo(temp[3]);
					
					
					strRequestTypeId="45";
				}
				/**
				 * This is used set variables during request type equal to department
				 */
				else//Dept
				{
					 temp=strChk.replace('@', '#').split("#");
					 _DossierDeskTransFB.setStrItemCategNo("95");
						_DossierDeskTransFB.setStrIssueNo(temp[17]);
						_DossierDeskTransFB.setStrIssueStoreId(temp[16]);
						_DossierDeskTransFB.setStrIssueDate(temp[6]);
						_DossierDeskTransFB.setStrStoreName(temp[8]);
						_DossierDeskTransFB.setStrCrNo(temp[2]);
						_DossierDeskTransFB.setStrStoreName(temp[17]);
						_DossierDeskTransFB.setStrCrno1(temp[2]);
						_DossierDeskTransFB.setStrStoreId(temp[16]);
						_DossierDeskTransFB.setStrServicetype(temp[5]);
						_DossierDeskTransFB.setStrDossierId(temp[8]);
					strRequestTypeId="97";
				}
				
				if(strRaisingReqTypeId.equals("35")|| strRaisingReqTypeId.equals("12") || strRaisingReqTypeId.equals("32") || strRequestTypeId.equalsIgnoreCase("97"))
				{
					strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_DossierDeskTransFB.getStrCrNo(), hosCode, true);
				}
				_DossierDeskTransFB.setStrPatientDtl(strPatientDtl);
							
				
				vo.setStrIssueNo(_DossierDeskTransFB.getStrIssueNo());
				vo.setStrIssueStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
				vo.setStrRaisingStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				vo.setStrStoreId(_DossierDeskTransFB.getStrStoreId());
				bo.getIssueItemDetailForReturnView(vo);
				
				
				strRequestDtl=DossierDeskTransHLP.getIssuedItemDetailsForReturnView(vo.getIssuedItemDtlWS(),vo.getStrHospitalCode(),strCostReq);
				
				_DossierDeskTransFB.setStrDeptName(vo.getStrDeptName());
				_DossierDeskTransFB.setStrRequestTypeId(strRequestTypeId);
				//_DossierDeskTransFB.setStrStoreId(strStoreId);
				_DossierDeskTransFB.setStrRequestDtl(strRequestDtl);
				_DossierDeskTransFB.setStrDoseFrqFlg("0");
				_DossierDeskTransFB.setStrChk(strChk);
				if (vo.getApprovedBy() != null
						&& vo.getApprovedBy().size() > 0) {
					cmbVal = hisutil.getOptionValue(vo.getApprovedBy(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal = "<option value='0'>Select Value</option>";
				}
				
				if (vo.getStrItemCatWs() != null
						&& vo.getStrItemCatWs().size() > 0) {
					cmbVal1 = hisutil.getOptionValue(vo.getStrItemCatWs(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal1 = "<option value='0'>Select Value</option>";
				}
			_DossierDeskTransFB.setStrRecommendByVal(cmbVal);
			_DossierDeskTransFB.setStrItemCatValues(cmbVal1);	 
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForReturnViewPage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	
	public static void insertData(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request){
		
		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		MmsConfigUtil mcu=null;
		
		String temp[]=null;
		//String strItemIdArray[] = null;
		String strmsgText="";
		String strDescription="";
		String[] values = null;
		String UCreq="";
		//int chkLength = 0;
		//String strBrandIdArray[] = null;
		//String strReservedFlagArray[] = null;
		// String strRateArray[] = null;
		// String strRateUnitIdArray[] = null;
		//String stockDtlsId[] = null; // from userHiddenFld=
										// StoreId^Generic_ItemId^Brand_ItemId^Stock_Status_Code^Batch_No^Serial_No
		//String strStochStatusCodeArray[] = null;
		// String strBatchSlNoArray[] = null;
		// String strItemSlNoArray[] = null;
		//String strGroupIdArray[] = null;
		//String strSubGroupIdArray[] = null;
		//String strAvlQtyArray[] = null;
		//String strAvlQtyUnitIdArray[] = null;
		/*
		 * String strBalQtyArray[] = null; String strBalQtyUnitIdArray[] = null;
		 * String strIssueQtyArray[] = null; String strIssueQtyUnitIdArray[] =
		 * null;
		 */
		// String strManufDateArray[] = null;
		// String strExpiryDateUnitIdArray[] = null;
	//	String strConsumableFlagArray[] = null;
		try{
			
			mcu=new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo= new DossierDeskTransBO();
			
			vo = (DossierDeskTransVO) TransferObjectFactory
			.populateData("dossier.transaction.vo.DossierDeskTransVO",
					_DossierDeskTransFB);
			UCreq=_DossierDeskTransFB.getStrUCReq();
			if(_DossierDeskTransFB.getStrRaisingReqTypeId().equals("12") || _DossierDeskTransFB.getStrRaisingReqTypeId().equals("13") || 
						_DossierDeskTransFB.getStrRaisingReqTypeId().equals("35") || _DossierDeskTransFB.getStrRaisingReqTypeId().equals("98") ){
				//System.out.println("check1");
				if(_DossierDeskTransFB.getStrAdmissionDtlHidVal()!=null){
					//System.out.println("check2");
					//System.out.println(_DossierDeskTransFB.getStrAdmissionDtlHidVal());
					
					
					temp=_DossierDeskTransFB.getStrAdmissionDtlHidVal().replace('^', '#').split("#");
					//IPD
					if(temp.length>1){
						vo.setStrAdmissionNo(temp[0]);
						vo.setStrEpisodeCode(temp[1]);
						vo.setStrVisitNo(temp[2]);
						vo.setStrDeptCode(temp[5].substring(0, 3));
						vo.setStrDeptUnitCode(temp[16]);
						vo.setStrWardCode(temp[6]);
						vo.setStrVisitType("0"); 
						vo.setStrTreatmentCat(temp[9]);
						vo.setStrBSIndent("0");
						
					}else{
						
						vo.setStrAdmissionNo("0");
						vo.setStrEpisodeCode("0");
						vo.setStrVisitNo("0");
						vo.setStrDeptCode("0");
						vo.setStrDeptUnitCode("0");
						vo.setStrWardCode("0");
						vo.setStrVisitType("1"); 
						vo.setStrTreatmentCat("0");
					}
				}
				else
				{
					vo.setStrAdmissionNo("0");
					vo.setStrEpisodeCode("0");
					vo.setStrVisitNo("0");
					vo.setStrDeptCode("0");
					vo.setStrDeptUnitCode("0");
					vo.setStrWardCode("0");
					vo.setStrVisitType("1");       //OPD
					vo.setStrTreatmentCat("0");
				}
				
			}
			else{
			
				vo.setStrAdmissionNo("0");
				vo.setStrEpisodeCode("0");
				vo.setStrVisitNo("0");
				vo.setStrDeptCode("0");
				vo.setStrDeptUnitCode("0");
				vo.setStrWardCode("0");
				vo.setStrVisitType("1"); 
				vo.setStrCrNo("0");
				vo.setStrTreatmentCat("0");
			}
			
			if(_DossierDeskTransFB.getStrRequestTypeId().equals("35")){
				strDescription="Issue To Patient: CRNO"+_DossierDeskTransFB.getStrCrNo();
		
			}else if(_DossierDeskTransFB.getStrRequestTypeId().equals("36")){
				strDescription="Issue To Employee: C.R.No. "+_DossierDeskTransFB.getStrCrNo()+
				" EmpNo: "+_DossierDeskTransFB.getStrEmpNo();
			}
			else{
				strDescription="Issue To Department: DeptName ";
			}
			 vo.setStrCrNo(_DossierDeskTransFB.getStrCrNo());
			vo.setStrDescription(strDescription);
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			vo.setStrFinStartDate(mcu.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrFinEndDate(mcu.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStockDtlsId(_DossierDeskTransFB.getStockDtlsId());
			vo.setStrUrgentFlg(_DossierDeskTransFB.getStrUrgentFlg().replace("$","#").split("#")[0]);
		
			bo.insertData(vo);
			
			
			if(vo.getStrMsgType().equals("1")){
				_DossierDeskTransFB.setStrFlag("0");
				
				throw new Exception(vo.getStrMsgString());
			}else{
				_DossierDeskTransFB.setStrFlag("0");
				if(vo.getStrBSReqNo() != null && !vo.getStrBSReqNo().equals("0"))
				{
					_DossierDeskTransFB.setStrMsg("Data Save successfully with Dossier Issue no :"+vo.getStrBSReqNo());
					_DossierDeskTransFB.setStrIssueNo(vo.getStrBSReqNo());
					_DossierDeskTransFB.setPrintReq("1");
				}
			}
			if(vo.getStrIssueNo() != null || !vo.getStrIssueNo().equals(""))
				_DossierDeskTransFB.setStrIssueNo(vo.getStrIssueNo());
			
			_DossierDeskTransFB.setStrUCReq(UCreq);
		}
		catch(Exception _err)
		{
			_err.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.insertData(vo) --> "+ _err.getMessage();
			System.out.println(strmsgText);
			HisException eObj = new HisException("mms","LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			if(strmsgText.contains("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!"))
				_DossierDeskTransFB.setStrErr("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!");
			else		
				_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		}
	}
	public static void insertReturn(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request){
		
		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		MmsConfigUtil mcu=null;
		String temp[]=null;
		String strmsgText="";
		String strDescription="";
		try{
			mcu=new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo= new DossierDeskTransBO();
			vo = (DossierDeskTransVO) TransferObjectFactory.populateData("dossier.transaction.vo.DossierDeskTransVO",_DossierDeskTransFB);
			_DossierDeskTransFB.setStrRaisingReqTypeId("95");
		if(_DossierDeskTransFB.getStrRaisingReqTypeId().equals("12") || _DossierDeskTransFB.getStrRaisingReqTypeId().equals("13")  || _DossierDeskTransFB.getStrRaisingReqTypeId().equals("35") || _DossierDeskTransFB.getStrRaisingReqTypeId().equals("95")){
				if(_DossierDeskTransFB.getStrAdmissionDtlHidVal()!=null){
					temp=_DossierDeskTransFB.getStrAdmissionDtlHidVal().replace('^', '#').split("#");
					if(temp.length>1){
						vo.setStrAdmissionNo(temp[0]);
						vo.setStrEpisodeCode(temp[1]);
						vo.setStrVisitNo(temp[2]);
						vo.setStrDeptCode(temp[5].substring(0, 3));
						vo.setStrDeptUnitCode(temp[5]);
						vo.setStrWardCode(temp[6]);
						vo.setStrVisitType("0");//IPD
						vo.setStrTreatmentCat(temp[9]);
					}
					else{
						vo.setStrAdmissionNo("0");
						vo.setStrEpisodeCode("0");
						vo.setStrVisitNo("0");
						vo.setStrDeptCode("0");
						vo.setStrDeptUnitCode("0");
						vo.setStrWardCode("0");
						vo.setStrVisitType("1");
						vo.setStrTreatmentCat("0");
					}
				}
				else
				{
					vo.setStrAdmissionNo("0");
					vo.setStrEpisodeCode("0");
					vo.setStrVisitNo("0");
					vo.setStrDeptCode("0");
					vo.setStrDeptUnitCode("0");
					vo.setStrWardCode("0");
					vo.setStrVisitType("1");       //OPD
					vo.setStrTreatmentCat("0");
				}
			}
			else{
					vo.setStrAdmissionNo("0");
					vo.setStrEpisodeCode("0");
					vo.setStrVisitNo("0");
					vo.setStrDeptCode("0");
					vo.setStrDeptUnitCode("0");
					vo.setStrWardCode("0");
					vo.setStrVisitType("1"); 
					vo.setStrCrNo("0");
					vo.setStrTreatmentCat("0");
			}
			
			if(_DossierDeskTransFB.getStrRequestTypeId().equals("44")){
				strDescription="Return From Patient: CRNO"+_DossierDeskTransFB.getStrCrNo();
		
			}else if(_DossierDeskTransFB.getStrRequestTypeId().equals("45")){
				strDescription="Return From Employee: C.R.No. "+_DossierDeskTransFB.getStrCrNo()+" EmpNo: "+_DossierDeskTransFB.getStrEmpNo();
			}
			else{
				strDescription="Issue to Patient : CR No. : "+_DossierDeskTransFB.getStrCrNo();
			}
			 
			vo.setStrDescription(strDescription);
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrFinStartDate("");
			vo.setStrFinEndDate("");
			vo.setItemParamValue(_DossierDeskTransFB.getItemParamValue());
			vo.setStrQtyText(_DossierDeskTransFB.getStrQtyText1());
			vo.setIsBroughtByPatient(_DossierDeskTransFB.getIsBroughtByPatient());
			vo.setStrSettlementFlag(_DossierDeskTransFB.getStrSettlementFlag());
			vo.setStrPatientType(_DossierDeskTransFB.getStrPatientType() );
			bo.insertRet(vo);
			if(vo.getStrMsgType().equals("1")){
				_DossierDeskTransFB.setStrFlag("0");
				
				throw new Exception(vo.getStrMsgString());
			}else{
				_DossierDeskTransFB.setStrFlag("1");
				_DossierDeskTransFB.setPrintReq("1");
				_DossierDeskTransFB.setStrMsg("Data Save successfully");
			}
			_DossierDeskTransFB.setStrReturnNo(vo.getStrReturnNo());
		}
		catch(Exception _err){
			_err.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.insertData(vo) --> "+ _err.getMessage();
			System.out.println(strmsgText);
			HisException eObj = new HisException("mms","LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			if(strmsgText.contains("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!"))
				_DossierDeskTransFB.setStrErr("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!");
			else		
				_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

		/*eObj = null;
			_err.printStackTrace();
	    strmsgText = "Issue Desk.LPIssueDeskTransDATA.insertData(vo) --> "
				+ _err.getMessage();
		HisException eObj = new HisException("mms",
				"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
		_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;*/
		}
	}

	/**
	 * This method will get the POPUP info according to the selected Bill No. to
	 * generate a PopUp in HLP
	 * 
	 * @param request
	 * @param response
	 */
	/*public static void getPopUp(HttpServletRequest request,
			HttpServletResponse response, DossierDeskTransFB formBean) {
		String strPopUpDtls = null;
		String index = "";
		String strmsgText = null;

		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		HisUtil hisutil = null;
		String strReqParam = "";
		String[] temp = null;
		String strItemCategoryNo = "";
		String strItemBrand = "";
		String strItemId = "";
		String strHospitalCode = "";
		String strStoreId = "";
		try 
		{
			
			bo = new DossierDeskTransBO();
			vo = new DossierDeskTransVO();
			
			strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();
			strReqParam = (String) request.getParameter("hiddenVal");
			
			temp = strReqParam.replace("^", "#").split("#");
			strItemId = temp[0];
			strItemBrand = temp[1];
			strItemCategoryNo = temp[2];
			strStoreId = temp[3];

//			System.out.println("data strItemId"+strItemId);
//			System.out.println("data strItemBrand"+strItemBrand);
//			System.out.println("data strItemCategoryNo"+strItemCategoryNo);
//			System.out.println("data strHospitalCode"+strHospitalCode);
//			System.out.println("data strStoreId"+strStoreId);
			
			vo.setStrItemId(strItemId);
			vo.setStrItemBrand(strItemBrand);
			vo.setStrItemCategoryNo(strItemCategoryNo);
			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrStoreId(strStoreId);

			index = (String) request.getParameter("index");
			bo.getPopUpInfo(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				strPopUpDtls = DossierDeskTransHLP.getPopUpInfo(vo.getPopupWS(),
						index);
               
				if (strPopUpDtls.equals("ERROR")) {

					HisException eObj = new HisException("MMS",
							"LPIssueDeskTransDATA->getPopUp()", strmsgText);
					strmsgText = "ERROR####Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ";
					eObj = null;
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(strmsgText);
					throw new Exception(vo.getStrMsgString());
				} else {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().write(strPopUpDtls);

				}
			}

		} catch (Exception e) {
			strmsgText = "dossier.transaction.LPIssueDeskTransDATA.getPopUp(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("MMS",
					"LPIssueDeskTransDATA->getPopUp()", strmsgText);
			formBean.setStrErr("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {
			if (vo != null)
				vo = null;
			if (bo != null)
				bo = null;
			if (hisutil != null)
				hisutil = null;
		}

	}

	
	 * This method calls when we again click on a hyperlink In this method first
	 * we set the parameter value on VO and then use in HLP to show again same
	 * pop up. This parameter(popupData) is already set by the value of flag i
	 * in JS file(AJAX Function) and flag i is already set by the link info when
	 * a link is clicked first time
	 * 
	 * @param request
	 * @param response
	
	public static void getPopUpData(HttpServletRequest request,
			HttpServletResponse response, DossierDeskTransFB formBean) {

		String strPopUpDtls = null;
		String strmsgText = null;
		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		HisUtil hisutil = null;
		System.out.println("Inside Data getPoPUpData--2");
		try {
			
			bo = new DossierDeskTransBO();
			vo = new DossierDeskTransVO();
			//System.out.println("PoppUp Data-->>>>>"+(String) request.getParameter("popupData"));
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrPopUpData((String) request.getParameter("popupData"));
            
			 strPopUpDtls = DossierDeskTransHLP.getPopUpData(vo.getStrPopUpData());

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			} else {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(strPopUpDtls);

			}

		} catch (Exception e) {
			strmsgText = "dossier.transaction.LPIssueDeskTransDATA.getPopUpData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("MMS",
					"LPIssueDeskTransDATA->getPopUpData()", strmsgText);
			formBean.setStrErr("ERROR####Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;

		} finally {
			if (bo != null)
				bo = null;
			if (vo != null)
				vo = null;
			if (hisutil != null)
				hisutil = null;
		}

	}*/

	public static String patientDiagDtl(String strCrNo,String strHospitalCode)
	{
		DossierDeskTransVO vo = new DossierDeskTransVO();	
		DossierDeskTransBO bo = new DossierDeskTransBO();
			StringBuffer sb = new StringBuffer("");
			vo.setStrCrNo(strCrNo);
			vo.setStrHospitalCode(strHospitalCode);
			
			WebRowSet ws = null;
			
			sb.append("");
			
			try 
			{
				bo.getPatientDiagDetails(vo);
				
				if (vo.getStrMsgType().equals("1")) 
				{
					throw new Exception(vo.getStrMsgString());
				}
			
				ws = vo.getDiagEmpWs();
				sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
				sb.append("<tr><td width='50%' class='LABEL' ><div align='center'>Diagnosis Name(Code)</div></td>");
				sb.append("<td width='50%' class='LABEL' ><div align='center'>Treatment Consultant</div></td></tr> ");
				if (ws != null && ws.size() > 0) 
				{
					
					while (ws.next()) 
					{
						String diagName = ws.getString(1);
						String diagCode = ws.getString(2);
						String empName = ws.getString(3);
						String empcode = ws.getString(4);
								
						if (diagName == null)
							diagName = "----";
//						if (diagCode == null)
//							diagCode = "----";
						if (empName == null)
							empName = "----";
						if (empcode == null)
							empcode = "----";
						
						
						
						sb.append("<tr><td width='50%' class='CONTROL'><div align='center'>"+diagName+"(<b>"+diagCode+"</b>)</div>");
						sb.append("</td>");
						sb.append("<td width='50%' class='CONTROL'><div align='center'>");
						sb.append(empName);
						sb.append("<input type='hidden' name='strDiagCode' value='"+ diagCode + "'><input type='hidden' name='strEmpCode' value='"+ empcode + "'></div></td>");
						sb.append("</tr>");
//						sb.append("<tr><td width='25%' class='LABEL'>Diagnosis Code</td>");
//						sb.append("<td width='25%' class='CONTROL'> ");
//						sb.append(diagCode);
//						sb.append("</td></tr>");
						
					}
					
				}
				else
				{
					sb.append("<tr><td width='50%' class='CONTROL'><div align='center'>"+"-"+"("+"-"+")</div>");
					sb.append("</td>");
					sb.append("<td width='50%' class='CONTROL'><div align='center'>");
					sb.append("-");
					sb.append("<input type='hidden' name='strDiagCode' value='"+ "-" + "'><input type='hidden' name='strEmpCode' value='"+ "0" + "'></div></td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
			} 
			catch (Exception e) 
			{
				try {
					throw new Exception("LPIssueDeskTransDATA-->diagDtl-->"	+ e.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			finally 
			{
				if (ws != null) 
				{
					try {
						ws.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ws = null;
				}
			}
	return sb.toString();
	}
	
	public static void showReport(DossierDeskTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();

		String reportFormat = "pdf";
		String strReqNo = "";
		String combo[] = null;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = "";
			String[] strChk = request.getParameterValues("chk");
			

			for (int i = 0, stopI = strChk.length; i < stopI; i++) {
				String[] strtemp = strChk[i].replace("@", "#").split("#");
				if (i == 0) {
					strReqNo = strReqNo + strtemp[1];

				} else {
					strReqNo = strReqNo + "," + strtemp[1];

				}
			}

			combo = request.getParameterValues("combo");
			String[] strTemp = combo[0].split("\\^");
			String strStoreId = strTemp[0];
	
				System.out.println("report_id"+ strReportId);
				System.out.println("hospCode"+ strHospitalCode);
				System.out.println("storeId"+ strStoreId);
				System.out.println("issueNo"+ strReqNo);
				String reportPath = "/mms/reports/issue_patient_mmsrpt.rptdesign";
				String strReportName = "UTILITY CERTIFICATE";

				params.put("report_Name", strReportName);
				params.put("report_id", strReportId);
				params.put("hospCode", strHospitalCode);
				params.put("storeId", strStoreId);
				params.put("issueNo", strReqNo);

				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);


		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
	public static void issueDtlsInit(HttpServletRequest request,
			HttpServletResponse response, DossierDeskTransFB formBean) 
	{

		String strSearchItemInitView = "";
		//boolean printFlag = false;
		
		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strMode = "";
		String strStoreId = "";
		String strIssueNo = "";

		try {

			    strMode = (String) request.getParameter("strMode");
			 strStoreId = (String) request.getParameter("strStoreId");
			 strIssueNo = (String) request.getParameter("strIssueNo");			
			         bo = new DossierDeskTransBO();
			         vo = new DossierDeskTransVO();
			
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString()); 
			formBean.setStrStoreId(strStoreId);
			formBean.setStrMode(strMode);
			formBean.setStrIssueNo(strIssueNo);
			/*
			formBean.setStrCrNo(request.getParameter("crNo"));
			formBean.setStrIssueNo(request.getParameter("strIssueNo"));
			formBean.setStrPrescribedBy(request.getParameter("prescribedBy"));		

			
			vo.setStrCrNum(request.getParameter("crNo"));
			vo.setStrIssueNo(request.getParameter("strIssueNo"));
			vo.setStrPrescribedBy(request.getParameter("prescribedBy"));
			*/
			
			vo.setStrMode(formBean.getStrMode());
			vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrIssueNo(formBean.getStrIssueNo());
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrIssueNo(formBean.getStrIssueNo());
			/*
			formBean.setStrIssueNo(strIssueNo);
			formBean.setStrIssueDate(vo.getStrIssueDate());
			formBean.setStrIssueTo(vo.getStrIssueTo());
			formBean.setStrItemCategoryId(vo.getStrItemCategoryNo());
			formBean.setWsIssueDetails(vo.getWsIssueDetails());
			*/
			
			bo.getIssueDtlsInitDtls(vo);

			if (vo.getStrMsgType().equals("1")) 
				throw new Exception(vo.getStrMsgString());
			
 			formBean.setWsIssueDetails(vo.getWsIssueDetails());
				
			if(formBean.getWsIssueDetails().next() && formBean.getWsIssueDetails().size() > 0)
			{
				/*
				  (Which Call in Case of Off-Line Issue Voucher)
				  1.Hospital Name ^ DDC Name ^ Prescribe For ^ Issue No ^ Issued By Doctor ^ Date ^ Cr No ^ Hindi Text ^ Registration No 
				  2.Drug Name
				  3.Batch No 
				  4.Exp Date
				  5.Issue Qty
				 */	
				//System.out.println("Hidden Val==>"+formBean.getWsIssueDetails().getString(1));
				   //formBean.setStrHospitalName(formBean.getWsIssueDetails().getString(1).split("\\^")[0]);
				   formBean.setStrStoreName(formBean.getWsIssueDetails().getString(14));	
				   formBean.setStrPatientName(formBean.getWsIssueDetails().getString(10));
				   formBean.setStrIssueNo(formBean.getWsIssueDetails().getString(11));
				 //  formBean.setStrPrescribedBy("");
				   formBean.setStrIssueDate(formBean.getWsIssueDetails().getString(12));
				   formBean.setStrCrNo(formBean.getWsIssueDetails().getString(13));	
				   //formBean.setStrHindiText("");
				   //formBean.setStrRegNo("");	
				   //formBean.setStrMode("1");	
				   //formBean.setStrLFAccountNo("");
				   //formBean.setStrLocDL("");
				   formBean.getWsIssueDetails().beforeFirst();
			}
			
			strSearchItemInitView = DossierDeskTransHLP.getIssueDtlsInitView(formBean);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strSearchItemInitView);

		} 
		catch (Exception e)
		{
			e.printStackTrace();
			 
			 String strmsgText = "IssueTransDATA.issueDtlsInit(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"IssueTransDATA.issueDtlsInit(vo) --> ", strmsgText);
			eObj.printStackTrace();
			eObj = null;
			  
		}finally {

			bo = null;
			vo = null;
		}
	}
	public static void CANCELDOSSIER(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request) {

		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strPatientDtl="";
		String strmsgText = "";
		String strRequestTypeId="";
		String strRaisingReqTypeId="";
		
		String strRequestDtl="";
		String strChk="";
		String temp[]=null;
		String hosCode="";
		HisUtil hisutil=null;
		String cmbVal="";
		String cmbVal1="";
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		try {
			
			
				bo = new DossierDeskTransBO();
				vo = new DossierDeskTransVO();
				hisutil = new HisUtil("dossier","LPIssueDeskTransDATA");
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				 if(request.getParameter("chk")!=null){
					 strChk=request.getParameter("chk");	 
					String  temp1[]= strChk.replace('@', '#').split("#"); 
					vo.setStrIssueDate(temp1[15]);
					vo.setStrCrNo(temp1[2]);
					vo.setStrHospitalCode(temp1[1]);
					vo.setStrDossierId(temp1[0]);
					vo.setStrRemarks(strChk.split("\\^")[1]);
					vo.setStrVisitType(temp1[17]);
					vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				 }
				 
				bo.cancelDossier(vo);
				
				if(Integer.parseInt( vo.getStrBillingCount()) > 0)
				{
					_DossierDeskTransFB.setStrMsg("Dossier Cannot Be Cancel Billing Already Done !!!!");
					_DossierDeskTransFB.setStrWarning("Dossier Cannot Be Cancel Billing Already Done !!!!");
					
					
				}else{
					
					_DossierDeskTransFB.setStrMsg("Dossier Cancelled !!!!");
					_DossierDeskTransFB.setStrWarning("Dossier Cancelled !!!!");
				}
				
				
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}
	
	public static void getInitDetailForIssuePageForView(DossierDeskTransFB _DossierDeskTransFB,
			HttpServletRequest request) {

		DossierDeskTransBO bo = null;
		DossierDeskTransVO vo = null;
		String strPatientDtl="";
		String strmsgText = "";
		String strRequestTypeId="";
		String strRaisingReqTypeId="";
		
		String strRequestDtl="";
		String strChk="";
		String temp[]=null;
		String hosCode="";
		HisUtil hisutil=null;
		String cmbVal="";
		String cmbVal1="";
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		try {
			
			
				bo = new DossierDeskTransBO();
				vo = new DossierDeskTransVO();
				hisutil = new HisUtil("dossier","LPIssueDeskTransDATA");
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_DossierDeskTransFB.setCombo(request.getParameterValues("combo"));
					 strRaisingReqTypeId=request.getParameterValues("combo")[1];
					 strStoreId=request.getParameterValues("combo")[0];
					_DossierDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
			 	}
				strChk=request.getParameter("chk");
				 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
				 {
					 _DossierDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
					 _DossierDeskTransFB.setStrStoreName(_DossierDeskTransFB.getComboValue());
				 }
				
				/**
				 * This is used set variables during request type equal to patient
				 */
				if(strRaisingReqTypeId.equals("35") || strRaisingReqTypeId.equals("32")){//Patient
					
					temp=strChk.replace('@', '#').split("#");
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					strRequestTypeId="44";
				}
				/**
				 * This is used set variables during request type equal to employee
				 */
				else if(strRaisingReqTypeId.equals("12")){//Staff
					
					temp=strChk.replace('@', '#').split("#");
					
					_DossierDeskTransFB.setStrItemCategNo(temp[4]);
					_DossierDeskTransFB.setStrIssueNo(temp[1]);
					_DossierDeskTransFB.setStrIssueStoreId(temp[0]);
					_DossierDeskTransFB.setStrCrNo(temp[2]);
					_DossierDeskTransFB.setStrIssueDate(temp[5]);
					_DossierDeskTransFB.setStrEmpNo(temp[3]);
					
					
					strRequestTypeId="45";
				}
				/**
				 * This is used set variables during request type equal to department
				 */
				else//Dept
				{
					 temp=strChk.replace('@', '#').split("#");
					 _DossierDeskTransFB.setStrItemCategNo("95");
						_DossierDeskTransFB.setStrIssueNo(temp[0]);
						_DossierDeskTransFB.setStrIssueStoreId(temp[16]);
						_DossierDeskTransFB.setStrIssueDate(temp[6]);
						_DossierDeskTransFB.setStrStoreName(temp[8]);
						_DossierDeskTransFB.setStrCrNo(temp[2]);
						_DossierDeskTransFB.setStrStoreName(temp[17]);
						_DossierDeskTransFB.setStrCrno1(temp[2]);
						_DossierDeskTransFB.setStrStoreId(temp[16]);
						_DossierDeskTransFB.setStrServicetype(temp[5]);
						_DossierDeskTransFB.setStrDossierId(temp[8]);
						_DossierDeskTransFB.setPrintReq("1");
					strRequestTypeId="97";
				}
				
				if(strRaisingReqTypeId.equals("35")|| strRaisingReqTypeId.equals("12") || strRaisingReqTypeId.equals("32") || strRequestTypeId.equalsIgnoreCase("97"))
				{
					strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_DossierDeskTransFB.getStrCrNo(), hosCode, true);
				}
				_DossierDeskTransFB.setStrPatientDtl(strPatientDtl);
							
				
				vo.setStrIssueNo(_DossierDeskTransFB.getStrIssueNo());
				vo.setStrIssueStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
				vo.setStrRaisingStoreId(_DossierDeskTransFB.getStrIssueStoreId());
				bo.getIssueItemDetailForIssueView(vo);
				
				bo.getRequestTypeDtlsForView(vo);
				
				strRequestDtl=DossierDeskTransHLP.getIssuedItemDetailsForIssueView(vo.getIssuedItemDtlWS(),vo.getStrHospitalCode(),strCostReq);
				
				_DossierDeskTransFB.setStrDossierName(vo.getStrDossierName());
				_DossierDeskTransFB.setStrServiceTypeName(vo.getStrServiceTypeName());
				_DossierDeskTransFB.setStrStoreName(vo.getStrStoreName());
				
				_DossierDeskTransFB.setStrDeptName(vo.getStrDeptName());
				_DossierDeskTransFB.setStrRequestTypeId(strRequestTypeId);
				//_DossierDeskTransFB.setStrStoreId(strStoreId);
				_DossierDeskTransFB.setStrRequestDtl(strRequestDtl);
				_DossierDeskTransFB.setStrDoseFrqFlg("0");
				_DossierDeskTransFB.setStrChk(strChk);
				if (vo.getApprovedBy() != null
						&& vo.getApprovedBy().size() > 0) {
					cmbVal = hisutil.getOptionValue(vo.getApprovedBy(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal = "<option value='0'>Select Value</option>";
				}
				
				if (vo.getStrItemCatWs() != null
						&& vo.getStrItemCatWs().size() > 0) {
					cmbVal1 = hisutil.getOptionValue(vo.getStrItemCatWs(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal1 = "<option value='0'>Select Value</option>";
				}
			_DossierDeskTransFB.setStrRecommendByVal(cmbVal);
			_DossierDeskTransFB.setStrItemCatValues(cmbVal1);	 
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePageForView(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_DossierDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}
	
}
