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

import org.apache.struts.config.FormBeanConfig;

import billing.BillConfigUtil;
import mms.MmsConfigUtil;
import dossier.transaction.bo.DossierRequisitionBO;
import dossier.transaction.bo.LPIssueDeskTransBO;
import dossier.transaction.controller.fb.LPIssueDeskTransFB;
import dossier.transaction.controller.hlp.LPIssueDeskTransHLP;
import dossier.transaction.vo.LPIssueDeskTransVO;


public class LPIssueDeskTransDATA {
	public static void getInitDetailForIssuePage(LPIssueDeskTransFB _LPIssueDeskTransFB,
			HttpServletRequest request) {

		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
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
				bo = new LPIssueDeskTransBO();
				vo = new LPIssueDeskTransVO();
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				billConfig=new BillConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_LPIssueDeskTransFB.setCombo(request.getParameterValues("combo"));
					strRaisingReqTypeId=request.getParameterValues("combo")[1];
					strStoreId=request.getParameterValues("combo")[0];
					_LPIssueDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
					
			 	}
			 strChk=request.getParameter("chk");
			 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
			 {
				 _LPIssueDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
				 _LPIssueDeskTransFB.setStrStoreName(_LPIssueDeskTransFB.getComboValue());
				 _LPIssueDeskTransFB.setStrPatientType((strChk.split("\\@")[17]).split("\\$")[0]);
				 vo.setStrPatientType((strChk.split("\\@")[17]).split("\\$")[0]);
			 }
			/**
			 * This is used set variables during request type equal to patient
			 */
//			if(strRaisingReqTypeId.equals("13")){
				temp=strChk.replace('@', '#').split("#");
				//_LPIssueDeskTransFB.setStrItemCategNo(temp[0]);
				_LPIssueDeskTransFB.setStrLpRequestNo(temp[0]);
				_LPIssueDeskTransFB.setStrRequestDate(temp[14]);
				//_LPIssueDeskTransFB.setStrRaisingStoreId(temp[3]);
				_LPIssueDeskTransFB.setStrCrNo(temp[2]);
				_LPIssueDeskTransFB.setStrBSReqNo(temp[0]);
				_LPIssueDeskTransFB.setStrPoNo("0");
				_LPIssueDeskTransFB.setStrPoStoreId("0");
				_LPIssueDeskTransFB.setStrUrgentFlg("0");
				_LPIssueDeskTransFB.setStrStoreId(strStoreId);
				_LPIssueDeskTransFB.setStrDossierShortName(temp[18].split("\\$")[0]);
				strRequestTypeId="98";
//			}
//			/**
//			 * This is used set variables during request type equal to emplo7yee
//			 */
//			else if(strRaisingReqTypeId.equals("12")){
//				
//				temp=strChk.replace('@', '#').split("#");
//				_LPIssueDeskTransFB.setStrItemCategNo(temp[0]);
//				_LPIssueDeskTransFB.setStrLpRequestNo(temp[1]);
//				_LPIssueDeskTransFB.setStrRequestDate(temp[2]);
//				_LPIssueDeskTransFB.setStrRaisingStoreId(temp[3]);
//				_LPIssueDeskTransFB.setStrCrNo(temp[4]);
//				_LPIssueDeskTransFB.setStrEmpNo(temp[5]);
//				_LPIssueDeskTransFB.setStrPoNo(temp[6]);
//				_LPIssueDeskTransFB.setStrPoStoreId(temp[7]);
//				 strRequestTypeId="36";
//				
//			}
//			/**
//			 * This is used set variables during request type equal to department
//			 */
//			else
//			{
//				 temp=strChk.replace('@', '#').split("#");
//				 _LPIssueDeskTransFB.setStrItemCategNo(temp[0]);
//				_LPIssueDeskTransFB.setStrLpRequestNo(temp[1]);
//				_LPIssueDeskTransFB.setStrRequestDate(temp[2]);
//				_LPIssueDeskTransFB.setStrRaisingStoreId(temp[3]);
//				_LPIssueDeskTransFB.setStrPoNo(temp[4]);
//				_LPIssueDeskTransFB.setStrPoStoreId(temp[5]);
//				strRequestTypeId="37";
//			}
			
//			if(strRaisingReqTypeId.equals("13")|| strRaisingReqTypeId.equals("12"))
//			{
				strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_LPIssueDeskTransFB.getStrCrNo(), hosCode, true);
//			}
			_LPIssueDeskTransFB.setStrPatientDtl(strPatientDtl);
			
			
			
			
			vo.setStrHospitalCode(hosCode);
			vo.setStrRaisingStoreId(_LPIssueDeskTransFB.getStrRaisingStoreId());
			vo.setStrLpRequestNo(_LPIssueDeskTransFB.getStrLpRequestNo());
			vo.setStrRequestDate(_LPIssueDeskTransFB.getStrRequestDate());
			vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
			vo.setStrPoStoreId(_LPIssueDeskTransFB.getStrPoStoreId());
			vo.setStrPoNo(_LPIssueDeskTransFB.getStrPoNo());
			vo.setStrCrNo(_LPIssueDeskTransFB.getStrCrNo());
			vo.setStrUrgentFlg(_LPIssueDeskTransFB.getStrUrgentFlg());
			vo.setStrFinStartDate(mmsConfig.getStrFinancialStartDate(strStoreId, hosCode));
			vo.setStrFinEndDate(mmsConfig.getStrFinancialEndDate(strStoreId, hosCode));
			vo.setStrBSReqNo("0");
			vo.setStrStoreId(_LPIssueDeskTransFB.getStrStoreId());
			vo.setStrPatientType(_LPIssueDeskTransFB.getStrPatientType());
			//if(_LPIssueDeskTransFB.getStrBSReqNo().equals("0"))
				bo.getLPRequestDetail(vo);
			/*else
				bo.getLPRequestDetail_new(vo);
			*/
			
			_LPIssueDeskTransFB.setStrDeptName(vo.getStrDeptName());
			_LPIssueDeskTransFB.setStrBillingHiddenValue(vo.getStrBillingHiddenValue());
			WebRowSet wb = vo.getPatAccountDtl();
			if(wb!=null)
			{
				if(wb.size()!=0) 
				{
					
					while(wb.next()){
						_LPIssueDeskTransFB.setStrPatAccountNo(wb.getString(1));
						_LPIssueDeskTransFB.setStrPatAccountBal(wb.getString(2));
					}
				}
			}
			else
			{
				
			}
			_LPIssueDeskTransFB.setStrHospitalCode(hosCode);
			strRequestDtl=LPIssueDeskTransHLP.initViewForIssueAddPage(_LPIssueDeskTransFB);
			
			//_LPIssueDeskTransFB.setStrDiagDtl(patientDiagDtl(_LPIssueDeskTransFB.getStrCrNo(),hosCode));
			
			
			
			_LPIssueDeskTransFB.setStrRequestDtl(strRequestDtl);
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
				strRequestItemDtl=LPIssueDeskTransHLP.getItemDetails(vo.getRequestItemDtlWS());
				
				_LPIssueDeskTransFB.setStrRequestItemDtl(strRequestItemDtl);
				
				strIssueItemId=LPIssueDeskTransHLP.getIssueItemDetails(vo.getIssueItemDtlWS(),strCostReq,strStoreId,hosCode,vo.getStrRaisingStoreId(),vo.getStrBSReqNo(),vo);
			
				
			}
			_LPIssueDeskTransFB.setStrPatAccountNo(_LPIssueDeskTransFB.getStrBillingHiddenValue().split("\\^")[0]);
			_LPIssueDeskTransFB.setStrIssueItemId(strIssueItemId);
			
			_LPIssueDeskTransFB.setStrChk(strChk);
			
			_LPIssueDeskTransFB.setStrRequestTypeId(strRequestTypeId);
			
			_LPIssueDeskTransFB.setStrStoreId(strStoreId);
			
			_LPIssueDeskTransFB.setCombo(request.getParameterValues("combo"));
			
			_LPIssueDeskTransFB.setStrSCMIntegration(mmsConfig.getStrSCMIntegration());
			 
			//_LPIssueDeskTransFB.setStrBillPaidCat(billConfig.getStrPaidCategory());
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_LPIssueDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void getInitDetailForReturnPage(LPIssueDeskTransFB _LPIssueDeskTransFB,
			HttpServletRequest request) {

		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
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
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		try {
			
			
				bo = new LPIssueDeskTransBO();
				vo = new LPIssueDeskTransVO();
				hisutil = new HisUtil("MMS","LPIssueDeskTransDATA");
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_LPIssueDeskTransFB.setCombo(request.getParameterValues("combo"));
					 strRaisingReqTypeId=request.getParameterValues("combo")[1];
					 strStoreId=request.getParameterValues("combo")[0];
					_LPIssueDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
			 	}
				strChk=request.getParameter("chk");
				 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
				 {
					 _LPIssueDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
					 _LPIssueDeskTransFB.setStrStoreName(_LPIssueDeskTransFB.getComboValue());
				 }
				
				/**
				 * This is used set variables during request type equal to patient
				 */
				if(strRaisingReqTypeId.equals("35") || strRaisingReqTypeId.equals("32")){//Patient
					
					temp=strChk.replace('@', '#').split("#");
					_LPIssueDeskTransFB.setStrItemCategNo(temp[4]);
					_LPIssueDeskTransFB.setStrIssueNo(temp[1]);
					_LPIssueDeskTransFB.setStrIssueStoreId(temp[0]);
					_LPIssueDeskTransFB.setStrCrNo(temp[2]);
					_LPIssueDeskTransFB.setStrIssueDate(temp[5]);
					strRequestTypeId="44";
				}
				/**
				 * This is used set variables during request type equal to employee
				 */
				else if(strRaisingReqTypeId.equals("12")){//Staff
					
					temp=strChk.replace('@', '#').split("#");
					
					_LPIssueDeskTransFB.setStrItemCategNo(temp[4]);
					_LPIssueDeskTransFB.setStrIssueNo(temp[1]);
					_LPIssueDeskTransFB.setStrIssueStoreId(temp[0]);
					_LPIssueDeskTransFB.setStrCrNo(temp[2]);
					_LPIssueDeskTransFB.setStrIssueDate(temp[5]);
					_LPIssueDeskTransFB.setStrEmpNo(temp[3]);
					
					
					strRequestTypeId="45";
				}
				/**
				 * This is used set variables during request type equal to department
				 */
				else//Dept
				{
					 temp=strChk.replace('@', '#').split("#");
					 _LPIssueDeskTransFB.setStrItemCategNo("97");
						_LPIssueDeskTransFB.setStrIssueNo(temp[1]);
						_LPIssueDeskTransFB.setStrIssueStoreId(strStoreId);
						_LPIssueDeskTransFB.setStrIssueDate(temp[6]);
						_LPIssueDeskTransFB.setStrStoreName(temp[4]);
						_LPIssueDeskTransFB.setStrCrNo(temp[0]);
						_LPIssueDeskTransFB.setStrStoreName(temp[19]);
						_LPIssueDeskTransFB.setStrDossierShortName(temp[20]);
						_LPIssueDeskTransFB.setStrReturnReqNo(temp[2]);
						_LPIssueDeskTransFB.setStrPatientType(temp[22].split("\\$")[0]);
						_LPIssueDeskTransFB.setStrGstrReqNo(temp[21]);
						//_LPIssueDeskTransFB.setStrser
					strRequestTypeId="97";
				}
				
				if(strRaisingReqTypeId.equals("35")|| strRaisingReqTypeId.equals("12") || strRaisingReqTypeId.equals("32") || strRequestTypeId.equalsIgnoreCase("97"))
				{
					strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_LPIssueDeskTransFB.getStrCrNo(), hosCode, true);
				}
				_LPIssueDeskTransFB.setStrPatientDtl(strPatientDtl);
							
				
				vo.setStrIssueNo(_LPIssueDeskTransFB.getStrIssueNo());
				vo.setStrIssueStoreId(_LPIssueDeskTransFB.getStrIssueStoreId());
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
				vo.setStrRaisingStoreId(_LPIssueDeskTransFB.getStrIssueStoreId());
				vo.setStrReturnReqNo(_LPIssueDeskTransFB.getStrReturnReqNo());
				bo.getIssueItemDetail(vo);
				
				
				strRequestDtl=LPIssueDeskTransHLP.getIssuedItemDetails(vo.getIssuedItemDtlWS(),vo.getStrHospitalCode(),strCostReq,vo);
				
				_LPIssueDeskTransFB.setStrDeptName(vo.getStrDeptName());
				_LPIssueDeskTransFB.setStrRequestTypeId(strRequestTypeId);
				_LPIssueDeskTransFB.setStrStoreId(strStoreId);
				_LPIssueDeskTransFB.setStrRequestDtl(strRequestDtl);
				_LPIssueDeskTransFB.setStrChk(strChk);
				_LPIssueDeskTransFB.setStrSettlementFlag(vo.getStrSettlementFlag());
				if (vo.getApprovedBy() != null
						&& vo.getApprovedBy().size() > 0) {
					cmbVal = hisutil.getOptionValue(vo.getApprovedBy(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal = "<option value='0'>Select Value</option>";
				}
			_LPIssueDeskTransFB.setStrRecommendByVal(cmbVal);
						 
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_LPIssueDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}
	
	public static void getInitDetailForReturnViewPage(LPIssueDeskTransFB _LPIssueDeskTransFB,
			HttpServletRequest request) {

		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
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
		String strStoreId="";
		MmsConfigUtil mmsConfig=null;
		String strCostReq="";
		try {
			
			
				bo = new LPIssueDeskTransBO();
				vo = new LPIssueDeskTransVO();
				hisutil = new HisUtil("MMS","LPIssueDeskTransDATA");
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_LPIssueDeskTransFB.setCombo(request.getParameterValues("combo"));
					 strRaisingReqTypeId=request.getParameterValues("combo")[1];
					 strStoreId=request.getParameterValues("combo")[0];
					_LPIssueDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
			 	}
				strChk=request.getParameter("chk");
				 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
				 {
					 _LPIssueDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
					 _LPIssueDeskTransFB.setStrStoreName(_LPIssueDeskTransFB.getComboValue());
				 }
				
				/**
				 * This is used set variables during request type equal to patient
				 */
				if(strRaisingReqTypeId.equals("35") || strRaisingReqTypeId.equals("32")){//Patient
					
					temp=strChk.replace('@', '#').split("#");
					_LPIssueDeskTransFB.setStrItemCategNo(temp[4]);
					_LPIssueDeskTransFB.setStrIssueNo(temp[1]);
					_LPIssueDeskTransFB.setStrIssueStoreId(temp[0]);
					_LPIssueDeskTransFB.setStrCrNo(temp[2]);
					_LPIssueDeskTransFB.setStrIssueDate(temp[5]);
					strRequestTypeId="44";
				}
				/**
				 * This is used set variables during request type equal to employee
				 */
				else if(strRaisingReqTypeId.equals("12")){//Staff
					
					temp=strChk.replace('@', '#').split("#");
					
					_LPIssueDeskTransFB.setStrItemCategNo(temp[4]);
					_LPIssueDeskTransFB.setStrIssueNo(temp[1]);
					_LPIssueDeskTransFB.setStrIssueStoreId(temp[0]);
					_LPIssueDeskTransFB.setStrCrNo(temp[2]);
					_LPIssueDeskTransFB.setStrIssueDate(temp[5]);
					_LPIssueDeskTransFB.setStrEmpNo(temp[3]);
					
					
					strRequestTypeId="45";
				}
				/**
				 * This is used set variables during request type equal to department
				 */
				else//Dept
				{
					 temp=strChk.replace('@', '#').split("#");
					 _LPIssueDeskTransFB.setStrItemCategNo("97");
						_LPIssueDeskTransFB.setStrIssueNo(temp[1]);
						_LPIssueDeskTransFB.setStrLpRequestNo(temp[2]);
						_LPIssueDeskTransFB.setStrIssueStoreId(strStoreId);
						_LPIssueDeskTransFB.setStrIssueDate(temp[6]);
						//_LPIssueDeskTransFB.setStrStoreName(temp[4]);
						_LPIssueDeskTransFB.setStrCrNo(temp[0]);
						//_LPIssueDeskTransFB.setStrStoreName(temp[19]);
						//_LPIssueDeskTransFB.setStrser
						_LPIssueDeskTransFB.setPrintReq("1");
					strRequestTypeId="97";
				}
				
				if(strRaisingReqTypeId.equals("35")|| strRaisingReqTypeId.equals("12") || strRaisingReqTypeId.equals("32") || strRequestTypeId.equalsIgnoreCase("97"))
				{
					strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_LPIssueDeskTransFB.getStrCrNo(), hosCode, true);
				}
				_LPIssueDeskTransFB.setStrPatientDtl(strPatientDtl);
							
				
				vo.setStrIssueNo(_LPIssueDeskTransFB.getStrIssueNo());
				vo.setStrIssueStoreId(_LPIssueDeskTransFB.getStrIssueStoreId());
				vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
				vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
				vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
				vo.setStrRaisingStoreId(_LPIssueDeskTransFB.getStrIssueStoreId());
				bo.getIssueItemDetailview(vo);
				
				bo.getReturnNoDtls(vo);
				
				_LPIssueDeskTransFB.setStrReturnNo(vo.getStrReturnNo());
				
				strRequestDtl=LPIssueDeskTransHLP.getIssuedItemDetailsForReturnView(vo.getIssuedItemDtlWS(),vo.getStrHospitalCode(),strCostReq);
				
				_LPIssueDeskTransFB.setStrDeptName(vo.getStrDeptName());
				_LPIssueDeskTransFB.setStrRequestTypeId(strRequestTypeId);
				_LPIssueDeskTransFB.setStrStoreId(strStoreId);
				_LPIssueDeskTransFB.setStrRequestDtl(strRequestDtl);
				_LPIssueDeskTransFB.setStrChk(strChk);
				if (vo.getApprovedBy() != null
						&& vo.getApprovedBy().size() > 0) {
					cmbVal = hisutil.getOptionValue(vo.getApprovedBy(), 
							"0", "0^Select Value", true);
				} else {
					cmbVal = "<option value='0'>Select Value</option>";
				}
			_LPIssueDeskTransFB.setStrRecommendByVal(cmbVal);
			
						 
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForReturnViewPage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getInitDetailForReturnViewPage()", strmsgText);
			_LPIssueDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	
	public static void insertData(LPIssueDeskTransFB _LPIssueDeskTransFB,
			HttpServletRequest request){
		
		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
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
			bo= new LPIssueDeskTransBO();
			
			vo = (LPIssueDeskTransVO) TransferObjectFactory
			.populateData("dossier.transaction.vo.LPIssueDeskTransVO",
					_LPIssueDeskTransFB);
			UCreq=_LPIssueDeskTransFB.getStrUCReq();
			if(_LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("12") || _LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("13") || 
						_LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("35") || _LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("98") ){
				//System.out.println("check1");
				if(_LPIssueDeskTransFB.getStrAdmissionDtlHidVal()!=null){
					//System.out.println("check2");
					//System.out.println(_LPIssueDeskTransFB.getStrAdmissionDtlHidVal());
					
					
					temp=_LPIssueDeskTransFB.getStrAdmissionDtlHidVal().replace('^', '#').split("#");
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
			
			if(_LPIssueDeskTransFB.getStrRequestTypeId().equals("98")){
				strDescription="Issue To Patient: CRNO"+_LPIssueDeskTransFB.getStrCrNo();
		
			}else if(_LPIssueDeskTransFB.getStrRequestTypeId().equals("36")){
				strDescription="Issue To Employee: C.R.No. "+_LPIssueDeskTransFB.getStrCrNo()+
				" EmpNo: "+_LPIssueDeskTransFB.getStrEmpNo();
			}
			else{
				strDescription="Issue To Department: DeptName ";
			}
			 vo.setStrCrNo(_LPIssueDeskTransFB.getStrCrNo());
			vo.setStrDescription(strDescription);
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

			vo.setStrFinStartDate(mcu.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrFinEndDate(mcu.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStockDtlsId(_LPIssueDeskTransFB.getStockDtlsId());
			vo.setStrUrgentFlg(_LPIssueDeskTransFB.getStrUrgentFlg().replace("$","#").split("#")[0]);
			vo.setStrPatientType(_LPIssueDeskTransFB.getStrPatientType());
			vo.setStrDossierShortName(_LPIssueDeskTransFB.getStrDossierShortName());
			bo.insertData(vo);
			
			
			if(vo.getStrMsgType().equals("1")){
				_LPIssueDeskTransFB.setStrFlag("0");
				
				throw new Exception(vo.getStrMsgString());
			}else{
				_LPIssueDeskTransFB.setStrFlag("0");
				if(vo.getStrBSReqNo() != null && !vo.getStrBSReqNo().equals("0"))
				{
					_LPIssueDeskTransFB.setStrMsg("Data Save successfully with Dossier Issue no :"+vo.getStrBSReqNo());
					_LPIssueDeskTransFB.setStrIssueNo(vo.getStrBSReqNo());
					_LPIssueDeskTransFB.setPrintReq("1");
				}
			}
			if(vo.getStrIssueNo() != null || !vo.getStrIssueNo().equals(""))
				_LPIssueDeskTransFB.setStrIssueNo(vo.getStrIssueNo());
			
			_LPIssueDeskTransFB.setStrUCReq(UCreq);
		}
		catch(Exception _err)
		{
			_err.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.insertData(vo) --> "+ _err.getMessage();
			System.out.println(strmsgText);
			HisException eObj = new HisException("mms","LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			if(strmsgText.contains("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!"))
				_LPIssueDeskTransFB.setStrErr("Patient Account Balance Going To Be Insufficient.Please Deposit Part Payment!!");
			else		
				_LPIssueDeskTransFB.setStrErr("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		}
	}
	public static void insertReturn(LPIssueDeskTransFB _LPIssueDeskTransFB,
			HttpServletRequest request){
		
		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
		MmsConfigUtil mcu=null;
		String temp[]=null;
		String strmsgText="";
		String strDescription="";
		try{
			mcu=new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			bo= new LPIssueDeskTransBO();
			vo = (LPIssueDeskTransVO) TransferObjectFactory.populateData("dossier.transaction.vo.LPIssueDeskTransVO",_LPIssueDeskTransFB);
			_LPIssueDeskTransFB.setStrRaisingReqTypeId("98");
		if(_LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("12") || _LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("13")  || _LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("35") || _LPIssueDeskTransFB.getStrRaisingReqTypeId().equals("98")){
				if(_LPIssueDeskTransFB.getStrAdmissionDtlHidVal()!=null){
					temp=_LPIssueDeskTransFB.getStrAdmissionDtlHidVal().replace('^', '#').split("#");
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
			
			if(_LPIssueDeskTransFB.getStrRequestTypeId().equals("44")){
				strDescription="Return From Patient: CRNO"+_LPIssueDeskTransFB.getStrCrNo();
		
			}else if(_LPIssueDeskTransFB.getStrRequestTypeId().equals("45")){
				strDescription="Return From Employee: C.R.No. "+_LPIssueDeskTransFB.getStrCrNo()+" EmpNo: "+_LPIssueDeskTransFB.getStrEmpNo();
			}
			else{
				strDescription="Reurn From  Department: DeptName ";
			}
			 
			vo.setStrDescription(strDescription);
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrFinStartDate(mcu.getStrFinancialStartDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrFinEndDate(mcu.getStrFinancialEndDate(vo.getStrStoreId() , vo.getStrHospitalCode()));
			vo.setStrGstrReqNo(_LPIssueDeskTransFB.getStrGstrReqNo());
			bo.insertRet(vo);
			if(vo.getStrMsgType().equals("1")){
				_LPIssueDeskTransFB.setStrFlag("0");
				
				throw new Exception(vo.getStrMsgString());
			}else{
				_LPIssueDeskTransFB.setStrFlag("1");
			}
			_LPIssueDeskTransFB.setStrReturnNo(vo.getStrReturnNo());
			_LPIssueDeskTransFB.setStrMsg("Data Save successfully ");
			_LPIssueDeskTransFB.setPrintReq("1");
		}
		catch(Exception _err){
	    
			_err.printStackTrace();
	    strmsgText = "Issue Desk.LPIssueDeskTransDATA.insertData(vo) --> "
				+ _err.getMessage();
		HisException eObj = new HisException("mms",
				"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
		_LPIssueDeskTransFB.setStrErr("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
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
			HttpServletResponse response, LPIssueDeskTransFB formBean) {
		String strPopUpDtls = null;
		String index = "";
		String strmsgText = null;

		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
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
			
			bo = new LPIssueDeskTransBO();
			vo = new LPIssueDeskTransVO();
			
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
				strPopUpDtls = LPIssueDeskTransHLP.getPopUpInfo(vo.getPopupWS(),
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
			HttpServletResponse response, LPIssueDeskTransFB formBean) {

		String strPopUpDtls = null;
		String strmsgText = null;
		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
		HisUtil hisutil = null;
		System.out.println("Inside Data getPoPUpData--2");
		try {
			
			bo = new LPIssueDeskTransBO();
			vo = new LPIssueDeskTransVO();
			//System.out.println("PoppUp Data-->>>>>"+(String) request.getParameter("popupData"));
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrPopUpData((String) request.getParameter("popupData"));
            
			 strPopUpDtls = LPIssueDeskTransHLP.getPopUpData(vo.getStrPopUpData());

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
		LPIssueDeskTransVO vo = new LPIssueDeskTransVO();	
		LPIssueDeskTransBO bo = new LPIssueDeskTransBO();
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
	
	public static void showReport(LPIssueDeskTransFB formBean,
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
			HttpServletResponse response, LPIssueDeskTransFB formBean) 
	{

		String strSearchItemInitView = "";
		//boolean printFlag = false;
		
		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
		String strMode = "";
		String strStoreId = "";
		String strIssueNo = "";

		try {

			    strMode = (String) request.getParameter("strMode");
			 strStoreId = (String) request.getParameter("strStoreId");
			 strIssueNo = (String) request.getParameter("strIssueNo");			
			         bo = new LPIssueDeskTransBO();
			         vo = new LPIssueDeskTransVO();
			
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
			/*if(strMode.equals("4"))
			{
				bo.getIssueDtlsInitDtlsExtraItems(vo);
			}*/

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
				   formBean.setStrHospitalName(formBean.getWsIssueDetails().getString(1).split("\\^")[0]);
				   formBean.setStrStoreName(formBean.getWsIssueDetails().getString(14));	
				   formBean.setStrPatientName(formBean.getWsIssueDetails().getString(10));
				   formBean.setStrIssueNo(formBean.getWsIssueDetails().getString(11));
				   vo.setStrIssueNo(formBean.getWsIssueDetails().getString(11));

				   if(strMode.equals("4"))
					{
						bo.getIssueDtlsInitDtlsExtraItems(vo);
					}
				   else
					   formBean.setStrBSReqNo(formBean.getWsIssueDetails().getString(23));
				   formBean.setWsExtraIssueDetails(vo.getWsExtraIssueDetails());
					if (vo.getStrMsgType().equals("1")) 
						throw new Exception(vo.getStrMsgString());
				 //  formBean.setStrPrescribedBy("");
				   formBean.setStrIssueDate(formBean.getWsIssueDetails().getString(12));
				   formBean.setStrCrNo(formBean.getWsIssueDetails().getString(13)+"("+formBean.getWsIssueDetails().getString(19)+")");	
				   //formBean.setStrServiceName(formBean.getWsIssueDetails().getString(8));
				   formBean.setStrDossierName(formBean.getWsIssueDetails().getString(9));
				   formBean.setStrDeptName(formBean.getWsIssueDetails().getString(17));
				   formBean.setStrServiceName(formBean.getWsIssueDetails().getString(18)+"("+formBean.getWsIssueDetails().getString(8)+")");
				  
				   //formBean.setStrHindiText("");
				   //formBean.setStrRegNo("");	
				   //formBean.setStrMode("1");	
				   //formBean.setStrLFAccountNo("");
				   //formBean.setStrLocDL("");
				   
				   formBean.getWsIssueDetails().beforeFirst();
			}
			
			strSearchItemInitView = LPIssueDeskTransHLP.getIssueDtlsInitView(formBean);

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
	
	public static void getInitDetailForViewPage(LPIssueDeskTransFB _LPIssueDeskTransFB,
			HttpServletRequest request) {

		LPIssueDeskTransBO bo = null;
		LPIssueDeskTransVO vo = null;
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
				bo = new LPIssueDeskTransBO();
				vo = new LPIssueDeskTransVO();
				hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
				mmsConfig=new MmsConfigUtil(hosCode);
				billConfig=new BillConfigUtil(hosCode);
				strCostReq=mmsConfig.getStrCostReq();
				if (request.getParameter("combo") != null	&& request.getParameter("combo").length() > 0) 
			 	{
					_LPIssueDeskTransFB.setCombo(request.getParameterValues("combo"));
					strRaisingReqTypeId=request.getParameterValues("combo")[1];
					strStoreId=request.getParameterValues("combo")[0];
					_LPIssueDeskTransFB.setStrRaisingReqTypeId(strRaisingReqTypeId);
					
			 	}
			 strChk=request.getParameter("chk");
			 if (request.getParameter("comboValue") != null	&& request.getParameter("comboValue").length() > 0) 
			 {
				 _LPIssueDeskTransFB.setComboValue(request.getParameterValues("comboValue")[0]);
				 _LPIssueDeskTransFB.setStrStoreName(_LPIssueDeskTransFB.getComboValue());
				 _LPIssueDeskTransFB.setStrPatientType((strChk.split("\\@")[17]).split("\\$")[0]);
				 vo.setStrPatientType((strChk.split("\\@")[17]).split("\\$")[0]);
			 }
			/**
			 * This is used set variables during request type equal to patient
			 */
//			if(strRaisingReqTypeId.equals("13")){
				temp=strChk.replace('@', '#').split("#");
				//_LPIssueDeskTransFB.setStrItemCategNo(temp[0]);
				_LPIssueDeskTransFB.setStrLpRequestNo(temp[0]);
				_LPIssueDeskTransFB.setStrRequestDate(temp[14]);
				//_LPIssueDeskTransFB.setStrRaisingStoreId(temp[3]);
				_LPIssueDeskTransFB.setStrCrNo(temp[2]);
				_LPIssueDeskTransFB.setStrBSReqNo(temp[0]);
				_LPIssueDeskTransFB.setStrPoNo("0");
				_LPIssueDeskTransFB.setStrPoStoreId("0");
				_LPIssueDeskTransFB.setStrUrgentFlg("0");
				_LPIssueDeskTransFB.setStrStoreId(strStoreId);
				_LPIssueDeskTransFB.setStrDossierStatusFlag(temp[9]);
				//_LPIssueDeskTransFB.setStrDossierShortName(temp[18].split("\\$")[0]);
				strRequestTypeId="98";
				_LPIssueDeskTransFB.setPrintReq("1");
//			}
//			/**
//			 * This is used set variables during request type equal to emplo7yee
//			 */
//			else if(strRaisingReqTypeId.equals("12")){
//				
//				temp=strChk.replace('@', '#').split("#");
//				_LPIssueDeskTransFB.setStrItemCategNo(temp[0]);
//				_LPIssueDeskTransFB.setStrLpRequestNo(temp[1]);
//				_LPIssueDeskTransFB.setStrRequestDate(temp[2]);
//				_LPIssueDeskTransFB.setStrRaisingStoreId(temp[3]);
//				_LPIssueDeskTransFB.setStrCrNo(temp[4]);
//				_LPIssueDeskTransFB.setStrEmpNo(temp[5]);
//				_LPIssueDeskTransFB.setStrPoNo(temp[6]);
//				_LPIssueDeskTransFB.setStrPoStoreId(temp[7]);
//				 strRequestTypeId="36";
//				
//			}
//			/**
//			 * This is used set variables during request type equal to department
//			 */
//			else
//			{
//				 temp=strChk.replace('@', '#').split("#");
//				 _LPIssueDeskTransFB.setStrItemCategNo(temp[0]);
//				_LPIssueDeskTransFB.setStrLpRequestNo(temp[1]);
//				_LPIssueDeskTransFB.setStrRequestDate(temp[2]);
//				_LPIssueDeskTransFB.setStrRaisingStoreId(temp[3]);
//				_LPIssueDeskTransFB.setStrPoNo(temp[4]);
//				_LPIssueDeskTransFB.setStrPoStoreId(temp[5]);
//				strRequestTypeId="37";
//			}
			
//			if(strRaisingReqTypeId.equals("13")|| strRaisingReqTypeId.equals("12"))
//			{
				strPatientDtl=PatientDtlHLP.patientWithAdmissionDtl(_LPIssueDeskTransFB.getStrCrNo(), hosCode, true);
//			}
			_LPIssueDeskTransFB.setStrPatientDtl(strPatientDtl);
			
			
			
			
			vo.setStrHospitalCode(hosCode);
			vo.setStrRaisingStoreId(_LPIssueDeskTransFB.getStrRaisingStoreId());
			vo.setStrLpRequestNo(_LPIssueDeskTransFB.getStrLpRequestNo());
			vo.setStrRequestDate(_LPIssueDeskTransFB.getStrRequestDate());
			vo.setStrRaisingReqTypeId(strRaisingReqTypeId);
			vo.setStrPoStoreId(_LPIssueDeskTransFB.getStrPoStoreId());
			vo.setStrPoNo(_LPIssueDeskTransFB.getStrPoNo());
			vo.setStrCrNo(_LPIssueDeskTransFB.getStrCrNo());
			vo.setStrUrgentFlg(_LPIssueDeskTransFB.getStrUrgentFlg());
			vo.setStrFinStartDate(mmsConfig.getStrFinancialStartDate(strStoreId, hosCode));
			vo.setStrFinEndDate(mmsConfig.getStrFinancialEndDate(strStoreId, hosCode));
			vo.setStrBSReqNo("0");
			vo.setStrStoreId(_LPIssueDeskTransFB.getStrStoreId());
			vo.setStrPatientType(_LPIssueDeskTransFB.getStrPatientType());
			vo.setStrDossierStatusFlag(_LPIssueDeskTransFB.getStrDossierStatusFlag());
			
			System.out.println("dossier status flag -->> "+vo.getStrDossierStatusFlag());
			
			//if(_LPIssueDeskTransFB.getStrBSReqNo().equals("0"))
				bo.getLPRequestDetail(vo);
				
				bo.getIssueNoDtls(vo);
			/*else
				bo.getLPRequestDetail_new(vo);
			*/
			_LPIssueDeskTransFB.setStrIssueNo(vo.getStrIssueNo());
			_LPIssueDeskTransFB.setStrDeptName(vo.getStrDeptName());
			_LPIssueDeskTransFB.setStrBillingHiddenValue(vo.getStrBillingHiddenValue());
			WebRowSet wb = vo.getPatAccountDtl();
			if(wb!=null)
			{
				if(wb.size()!=0) 
				{
					
					while(wb.next()){
						_LPIssueDeskTransFB.setStrPatAccountNo(wb.getString(1));
						_LPIssueDeskTransFB.setStrPatAccountBal(wb.getString(2));
					}
				}
			}
			else
			{
				
			}
			_LPIssueDeskTransFB.setStrHospitalCode(hosCode);
			strRequestDtl=LPIssueDeskTransHLP.initViewForIssueAddPage(_LPIssueDeskTransFB);
			
			//_LPIssueDeskTransFB.setStrDiagDtl(patientDiagDtl(_LPIssueDeskTransFB.getStrCrNo(),hosCode));
			
			
			
			_LPIssueDeskTransFB.setStrRequestDtl(strRequestDtl);
			
			
			System.out.println("mmsConfig.getStrSCMIntegration"+mmsConfig.getStrSCMIntegration().equals("1"));
			System.out.println("vo.getStrLpRequestNo"+vo.getStrLpRequestNo().substring(0,2).equals("10"));
			if(mmsConfig.getStrSCMIntegration().equals("1") && vo.getStrLpRequestNo().substring(0,2).equals("11"))
			{
				buff=new StringBuffer();
				buff.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
				buff.append("<tr>");
				buff.append("<td class='multiControl'>Integration with e-Aushadhi is yes. So, dossier issue can't be processed</td></tr></table>");
				
				strIssueItemId = buff.toString();
			}
			else
			{
				strRequestItemDtl=LPIssueDeskTransHLP.getItemDetails(vo.getRequestItemDtlWS());
				
				_LPIssueDeskTransFB.setStrRequestItemDtl(strRequestItemDtl);
				
				strIssueItemId=LPIssueDeskTransHLP.getIssueItemDetailsForIssueView(vo.getIssueItemDtlWS(),strCostReq,strStoreId,hosCode,vo.getStrRaisingStoreId(),vo.getStrBSReqNo(),vo);
			
				
			}
			_LPIssueDeskTransFB.setStrPatAccountNo(_LPIssueDeskTransFB.getStrBillingHiddenValue().split("\\^")[0]);
			_LPIssueDeskTransFB.setStrIssueItemId(strIssueItemId);
			
			_LPIssueDeskTransFB.setStrChk(strChk);
			
			_LPIssueDeskTransFB.setStrRequestTypeId(strRequestTypeId);
			
			_LPIssueDeskTransFB.setStrStoreId(strStoreId);
			
			_LPIssueDeskTransFB.setCombo(request.getParameterValues("combo"));
			
			_LPIssueDeskTransFB.setStrSCMIntegration(mmsConfig.getStrSCMIntegration());
			 
			//_LPIssueDeskTransFB.setStrBillPaidCat(billConfig.getStrPaidCategory());
			
			
		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "Issue Desk.LPIssueDeskTransDATA.getInitDetailForIssuePage(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"LPIssueDeskTransDATA->getIndentDetails()", strmsgText);
			_LPIssueDeskTransFB.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}
	
		
}
