package ipd.transactions;

import java.util.HashMap;
import java.util.LinkedHashMap;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import hisglobal.vo.DailyPatientVO;
//import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
//import hisglobal.vo.DailyPatientVO;
//import hisglobal.vo.PatientDetailVO;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import ipd.setup.IPDConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;


public class AdmissionAdviceTransDATA {
	
	
	
	public static void setDeptComboValues( AdmissionAdviceTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {
		String temp = "";
		AdmissionAdviceTransBO admissionAdviceTransBO = null;
		AdmissionAdviceTransVO admissionAdviceTransVO = null;
		try {
			admissionAdviceTransVO = new AdmissionAdviceTransVO();
			admissionAdviceTransBO = new AdmissionAdviceTransBO();
			admissionAdviceTransVO.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			admissionAdviceTransVO.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			admissionAdviceTransVO.setStrCrNo(formBean.getStrCrNo());
			admissionAdviceTransBO.setDeptComboValues(admissionAdviceTransVO);
			
			if(admissionAdviceTransVO.getStrDeptComboValuesWs().size()==0)
			{
				formBean.setEpFlag("0");
			}
			HisUtil util = new HisUtil("Admission Advice Trans","AdmissionAdviceTransDATA");
			temp = util.getOptionValue(admissionAdviceTransVO.getStrDeptComboValuesWs(), formBean.getStrDepartmentValue(), "0^Select Value",
					true);
			formBean.setStrDeptComboValues(temp);
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","AdmissionAdviceTransDATA->setDeptComboValues()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
	
	public static void setUnitComboValues( AdmissionAdviceTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {
		String temp = "";
		try {
			AdmissionAdviceTransVO admissionAdviceTransVO = new AdmissionAdviceTransVO();
			admissionAdviceTransVO.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			admissionAdviceTransVO.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			admissionAdviceTransVO.setStrCrNo(request.getParameter("puk"));
			admissionAdviceTransVO.setStrDepartmentCode(request.getParameter("strDepartmentCode"));
			
			AdmissionAdviceTransBO admissionAdviceTransBO = new AdmissionAdviceTransBO();
			admissionAdviceTransBO.setUnitComboValues(admissionAdviceTransVO);
			HisUtil util = new HisUtil("Admission Advice Trans","AdmissionAdviceTransDATA");
			temp = util.getOptionValue(admissionAdviceTransVO.getStrUnitComboValuesWs(), formBean.getStrUnitValue(), "0^Select Value",
					true);
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->setUnitComboValues()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
	
	
	
	public static void setAdvnaceAmountValues( AdmissionAdviceTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		try {
			AdmissionAdviceTransVO admissionAdviceTransVO = new AdmissionAdviceTransVO();
			admissionAdviceTransVO.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			admissionAdviceTransVO.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			admissionAdviceTransVO.setStrCrNo(request.getParameter("puk"));
			admissionAdviceTransVO.setStrTreatmentCategoryCode(request.getParameter("catcode"));
			admissionAdviceTransVO.setStrWardCode(request.getParameter("wardCode").replace("@", "#").split("#")[0]);
			System.out.println(request.getSession().getAttribute("HOSPITAL_CODE").toString() +" "+request.getSession().getAttribute("SEATID").toString()+" "+request.getParameter("puk")+" "+request.getParameter("catcode"));
			AdmissionAdviceTransBO admissionAdviceTransBO = new AdmissionAdviceTransBO();
			admissionAdviceTransBO.setAdvnaceAmountValues(admissionAdviceTransVO);
			System.out.println("Advance amount is "+admissionAdviceTransVO.getStrAdvanceAmount());
			response.setHeader("Cache-Control", "no-cache");
			if(admissionAdviceTransVO.getStrAdvanceAmount().equals(""))
			{
				admissionAdviceTransVO.setStrAdvanceAmount("0");
			}
			if(!admissionAdviceTransVO.getStrAccountNo().equals(""))
			{
				admissionAdviceTransVO.setStrAdvanceAmount("0");
			}
			response.getWriter().print(admissionAdviceTransVO.getStrAdvanceAmount());
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->setAdvnaceAmountValues()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
	
	
	/**
	 * This function invoke AdmissionAdviceTransBO's setRequiredValues(). This
	 * function set Consultant name,Treatment Category ,Disease type,ward
	 * combo,and other details reagarding to admission advice on add page.
	 * 
	 * @param formBean
	 */
	public static void initAdmissionAdvice(AdmissionAdviceTransFB formBean,
			HttpServletRequest request) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		LinkedHashMap<String,String> mapProcedureParam=new LinkedHashMap<String,String>();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrDepartmentValue(formBean.getStrDepartmentValue()==null||formBean.getStrDepartmentValue().equals("0")?"":formBean.getStrDepartmentValue());
		vo.setStrUnitValue(formBean.getStrUnitValue());
		HisUtil util = null;
		String temp = "";
		WebRowSet ws=null;
		vo.setStrMode(formBean.getStrMode());
		String strCrNo="";
		if(vo.getStrMode().equals("0")){
			HttpSession hs = request.getSession();
			String strTmpURL = request.getRequestURL().toString();
			hs.setAttribute("deskName", strTmpURL.replace("/", "#").split("#")[6].replace(".", "#").split("#")[0]);
			String strUnitCode = (String) hs.getAttribute("dynamicDeskUnitCode");
			 strCrNo = request.getParameter("patCrNo");
			 vo.setStrCrNo(request.getParameter("patCrNo"));
			DailyPatientVO vo1[]=(DailyPatientVO []) hs.getAttribute("deskPatientList");
			for (int i = 0; i < vo1.length; i++) {
				if (strCrNo.equals(vo1[i].getPatCrNo())) {
					vo.setPatCrNo(vo1[i].getPatCrNo());
					vo.setStrDepartmentValue(strUnitCode.substring(0, 3));
					vo.setStrUnitValue(strUnitCode);
					vo.setStrEpisodeNumber(vo1[i].getEpisodeCode());
					vo.setStrVisitValue(vo1[i].getEpisodeVisitNo());
					vo.setStrPrimaryCategory(vo1[i].getPatPrimaryCatCode());
					vo.setStrSecondaryCategory("");
				}
			}
		}
		
		else
		{
			strCrNo = request.getParameter("strCrNo");
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setPatCrNo(strCrNo);
			vo.setStrCrNo(request.getParameter("strCrNo"));
		}
		
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		
		try 
		{
			bo.setRequiredValues(vo);
			
			if(vo.getStrMsgType().equals("2"))
			{
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				formBean.setStrCrNo("");
			}
			else
			{
				ws=vo.getPreviousDiagnosisWs();				
				formBean.setStrPreviousDiagDtl(AdmissionAdviceTransHLP.getDiagnosisDtl(ws,vo.getStrVisitValue()))	;
				formBean.setStrDepartment(vo.getStrDepartment());
				formBean.setStrMsgString(vo.getStrMsgString());
				formBean.setStrMsgType(vo.getStrMsgType());
				formBean.setStrAge(vo.getStrAge());
				formBean.setStrPatName(vo.getStrPatName());
				formBean.setStrSex(vo.getStrSex());
				formBean.setStrUnitValue(vo.getStrUnitValue());
				formBean.setStrUnitCode(vo.getStrUnitCode());
				formBean.setStrAccountNo(vo.getStrAccountNo());
				formBean.setStrAdvanceAmtpaid(vo.getStrAdvanceAmtpaid());
				formBean.setStrAdvDepDate(vo.getStrAdvDepDate());
				formBean.setStrAccType(vo.getStrAccType());
	
				if (formBean.getStrMsgType().equals("0")) 
				{
					formBean.setPatCrNo(vo.getPatCrNo());
					formBean.setStrEpisodeNumber(vo.getStrEpisodeNumber());
					formBean.setStrDepartment(vo.getStrDepartment());
					formBean.setStrUnit(vo.getStrUnit());
					formBean.setStrMlcNo(vo.getStrMlcNo());
					formBean.setStrDepartmentValue(vo.getStrDepartmentValue());
					formBean.setStrUnitValue(vo.getStrUnitValue());
					formBean.setStrVisitValue(vo.getStrVisitValue());
					formBean.setStrPrimaryCategory(vo.getStrPrimaryCategory());
					formBean.setStrSecondaryCategory(vo.getStrSecondaryCategory());
					
					util = new HisUtil("ADT","AdmissionAdviceTransDATA");
					if(vo.getConsultantNameWs().size()==1)//Only One Employee Found or Current Logged In Employee
						temp = util.getOptionValue(vo.getConsultantNameWs(), "","", false);
					else
						temp = util.getOptionValue(vo.getConsultantNameWs(), "0","0^Select Value", false);
					
					formBean.setStrConsultantNameValues(temp);
					if (!vo.getStrSecondaryCategory().equals("0")) 
					{
						temp = util.getOptionValue(vo.getTreatmentCategoryWs(),IpdTransConfig.getTreatmentCategory(),"0^Select Value", false);	
						formBean.setStrTreatmentCategoryValues(temp);
					} 
					else 
					{
						temp = util.getOptionValue(vo.getTreatmentCategoryWs(), vo.getStrPrimaryCategory(), "0^Select Value", false);
						formBean.setStrTreatmentCategoryValues(temp);
					}
					temp = util.getOptionValue(vo.getDiseaseTypeWs(), "","0^Select Value", false);
					formBean.setStrDiseaseTypeValues(temp);
					
					
					if(formBean.getStrDiagnosisType().equals("0"))//ICD
					mapProcedureParam.put("modeval", "1");
					else
						mapProcedureParam.put("modeval", "2");
					
					mapProcedureParam.put("hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE);
					mapProcedureParam.put("err", "#1");
					mapProcedureParam.put("resultset", "#2");
					temp=HisComboOptions.getOptionsFromProc("pkg_simple_view.Proc_Hgbt_Diagnosis_Type_Dtl", mapProcedureParam, "11", "0^Select Value", false);
					formBean.setStrDiagnosticTypeValues(temp);
					temp=util.getOptionValue(vo.getStrAdmissionTypeWS(), "0","-1^Select Value",false);
					formBean.setStrAdmissionTypeValues(temp);
					temp=util.getOptionValue(vo.getStrTariffNameWS(), "0","0^Select Value",false);
					formBean.setStrTariffNameValues(temp);
					formBean.setStrAdvanceAmount("0");
					if(formBean.getStrEpisodeNumber().equals("0"))
					formBean.setStrEpisodeNumber("NA");	
					
					/*if(vo.getPackageComboValuesWs()!=null && vo.getPackageComboValuesWs().size()>0)
						temp = util.getOptionValue(vo.getPackageComboValuesWs(), "0","0^Select Value", false);
					else
						temp = util.getOptionValue(vo.getPackageComboValuesWs(), "0","0^Select Value", false);
					
					formBean.setStrPackageComboValues(temp);*/
				} 
				else 
				{	
					throw new Exception(vo.getStrMsgString());	
				}
			}
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","AdmissionAdviceTransDATA->initAdmissionAdvice()",strmsgText);
			if(!vo.getStrMsgType().equals("2"))
			formBean.setStrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (util != null)
				util = null;
		}

	}
	
	
	
	/**
	 * This function invoke AdmissionAdviceTransBO's setRequiredValues(). This
	 * function set Consultant name,Treatment Category ,Disease type,ward
	 * combo,and other details reagarding to admission advice on add page.
	 * 
	 * @param formBean
	 */
	public static void initAdmissionAdviceOPDDesk(AdmissionAdviceTransFB formBean,HttpServletRequest request) 
	{

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		LinkedHashMap<String,String> mapProcedureParam=new LinkedHashMap<String,String>();
		/*vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrDepartmentValue(formBean.getStrDepartmentValue()==null||formBean.getStrDepartmentValue().equals("0")?"":formBean.getStrDepartmentValue());
		vo.setStrUnitValue(formBean.getStrUnitValue());*/
		HisUtil util = null;
		String temp = "";
		WebRowSet ws=null;
		//System.out.println("X2");
		
		vo.setStrMode(formBean.getStrMode());
		String strCrNo="";
		String deptUnitCodeFb="";
		//System.out.println("vo.getStrMode()"+vo.getStrMode());
		if(vo.getStrMode().equals("0"))//ONLINE
		{
			/*HttpSession hs = request.getSession();
			String strTmpURL = request.getRequestURL().toString();
			System.out.println("url is  "+request.getRequestURL().toString());
			String s="http://localhost:8081/AHIMS/opd/transaction/opdDeskNew.jsp".replace("/", "#");
			System.out.println(s.split("#")[6]);
			hs.setAttribute("deskName", strTmpURL.replace("/", "#").split("#")[6].replace(".", "#").split("#")[0]);
			String strUnitCode = (String) hs.getAttribute("dynamicDeskUnitCode");
			System.out.println(strUnitCode+" ----------"+request.getParameter("patCrNo")+"--------- "+hs.getAttribute("deskPatientList"));
			 strCrNo = request.getParameter("patCrNo");*/
			
			/*DailyPatientVO vo1[]=(DailyPatientVO []) hs.getAttribute("deskPatientList");
			for (int i = 0; i < vo1.length; i++) {
				if (strCrNo.equals(vo1[i].getPatCrNo())) {
					vo.setPatCrNo(vo1[i].getPatCrNo());
					vo.setStrDepartmentValue(strUnitCode.substring(0, 3));
					vo.setStrUnitValue(strUnitCode);
					vo.setStrEpisodeNumber(vo1[i].getEpisodeCode());
					vo.setStrVisitValue(vo1[i].getEpisodeVisitNo());
					vo.setStrPrimaryCategory(vo1[i].getPatPrimaryCatCode());
					vo.setStrSecondaryCategory("");
				}
			}*/
			 
			   
			 
			/*    System.out.println("session attribute is "+hs.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)+" "+hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO));
			 
				PatientDetailVO voDP = null;
				if(hs.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
				{
					System.out.println("TAKING VO from :-- DynamicDeskConfig.DYNAMIC_DESK_TYPE");
					PatientDetailVO[] al=(PatientDetailVO[])hs.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
					for (int i = 0; i < al.length; i++)
					{
						voDP = (PatientDetailVO) al[i];
						if (voDP.getPatCrNo().equals(formBean.getPatCrNo())) break;
					}
					vo.setPatCrNo(strCrNo);
					vo.setStrEpisodeNumber(voDP.getEpisodeCode());
					vo.setStrVisitValue(voDP.getEpisodeVisitNo());
					vo.setStrAdmissionNo(voDP.getPatAdmNo());
					vo.setStrDepartmentValue(strUnitCode.substring(0, 3));
					vo.setStrCrNo(strCrNo);
					//vo.setSerialNo(voDP.getSerialNo());
					//vo.setIsConfirmed(voDP.getIsConfirmed());
					//fb.setTriageDuration(voDP.getTriageDuration());
					vo.setStrDeptUnitCode(voDP.getDepartmentUnitCode());
					deptUnitCodeFb=voDP.getDepartmentUnitCode();
						// User Detail
					//vo.setStrSeatId(userVO.getSeatId());
					//_fb.setEmpNo(userVO.getUserEmpID());
				}
				else if(hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
				{
					System.out.println("TAKING VO from :-- DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO");
					voDP = (PatientDetailVO) hs.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					vo.setPatCrNo(strCrNo);
					vo.setStrEpisodeNumber(voDP.getEpisodeCode());
					vo.setStrVisitValue(voDP.getEpisodeVisitNo());
					vo.setStrAdmissionNo(voDP.getPatAdmNo());
					vo.setStrDepartmentValue(strUnitCode.substring(0, 3));
					vo.setStrCrNo(strCrNo);
					//vo.sets(voDP.getSerialNo());
					//_fb.setIsConfirmed(voDP.getIsConfirmed());
					//_fb.setTriageDuration(voDP.getTriageDuration());
					vo.setStrDeptUnitCode(voDP.getDepartmentUnitCode());
					deptUnitCodeFb=voDP.getDepartmentUnitCode();
						// User Detail
					//vo.setStrSeatId(userVO.getSeatId());
					//_fb.setEmpNo(userVO.getUserEmpID());

				}
				
				System.out.println(vo.getPatCrNo()+" "+vo.getStrEpisodeNumber()+" "+vo.getStrVisitValue()+" "+vo.getStrAdmissionNo()+" "+vo.getStrDepartmentValue()+" "+vo.getStrDeptUnitCode());
		}
		
		else
		{*/   //not in use
			strCrNo = request.getParameter("patCrNo");
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setPatCrNo(strCrNo);
			vo.setStrEpisodeNumber(request.getParameter("episodeCode"));
			vo.setStrVisitValue(request.getParameter("episodeVisitNo"));
			vo.setStrAdmissionNo(request.getParameter("patAdmNo"));
			vo.setStrDepartmentValue(request.getParameter("departmentUnitCode").substring(0, 3));
			vo.setStrCrNo(strCrNo);
			vo.setStrDeptUnitCode(request.getParameter("departmentUnitCode"));
			vo.setStrUnitValue(request.getParameter("departmentUnitCode"));
			//System.out.println("hasahsaAJAY========="+strCrNo);

			deptUnitCodeFb=request.getParameter("departmentUnitCode");
	
		
			AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		
			try 
			{
				bo.setRequiredValues(vo);
				if(vo.getStrMsgType().equals("2"))
				{
					formBean.setStrMsgString(vo.getStrMsgString());
					formBean.setStrMsgType(vo.getStrMsgType());
					formBean.setStrCrNo("");
				}
				else
				{
					ws=vo.getPreviousDiagnosisWs();
					
					formBean.setStrPreviousDiagDtl(AdmissionAdviceTransHLP.getDiagnosisDtl(ws,vo.getStrVisitValue()))	;
					formBean.setStrDepartment(vo.getStrDepartment());
					formBean.setStrMsgString(vo.getStrMsgString());
					formBean.setStrMsgType(vo.getStrMsgType());
					formBean.setStrAge(vo.getStrAge());
					formBean.setStrPatName(vo.getStrPatName());
					formBean.setStrSex(vo.getStrSex());
					formBean.setStrUnitValue(vo.getStrUnitValue());
					formBean.setStrUnitCode(vo.getStrUnitCode());		
		
					if (formBean.getStrMsgType().equals("0")) 
					{
						formBean.setPatCrNo(vo.getPatCrNo());
						formBean.setStrEpisodeNumber(vo.getStrEpisodeNumber());
						formBean.setStrDepartment(vo.getStrDepartment());
						formBean.setStrUnit(vo.getStrUnit());
						formBean.setStrMlcNo(vo.getStrMlcNo());
						formBean.setStrDepartmentValue(vo.getStrDepartmentValue());
						formBean.setStrUnitValue(vo.getStrUnitValue());
						formBean.setStrVisitValue(vo.getStrVisitValue());
						formBean.setStrPrimaryCategory(vo.getStrPrimaryCategory());
						formBean.setStrSecondaryCategory(vo.getStrSecondaryCategory());
						util = new HisUtil("ADT","AdmissionAdviceTransDATA");
						
						if(vo.getConsultantNameWs().size()==1)//Only One Employee Found or Current Logged In Employee
							temp = util.getOptionValue(vo.getConsultantNameWs(), "","", false);
						else
							temp = util.getOptionValue(vo.getConsultantNameWs(), "0","0^Select Value", false);
						
						formBean.setStrConsultantNameValues(temp);
						if (!vo.getStrSecondaryCategory().equals("0")) 
						{
							temp = util.getOptionValue(vo.getTreatmentCategoryWs(),IpdTransConfig.getTreatmentCategory(),"0^Select Value", false);
							formBean.setStrTreatmentCategoryValues(temp);
						} 
						else 
						{
							temp = util.getOptionValue(vo.getTreatmentCategoryWs(), vo.getStrTreatmentCategoryCode(), "0^Select Value", false);
							formBean.setStrTreatmentCategoryValues(temp);
						}					 
						
						temp = util.getOptionValue(vo.getDiseaseTypeWs(), "","0^Select Value", false);
						formBean.setStrDiseaseTypeValues(temp);
						
						
						if(formBean.getStrDiagnosisType().equals("0"))//ICD
							mapProcedureParam.put("modeval", "1");
						else
							mapProcedureParam.put("modeval", "2");					
						mapProcedureParam.put("hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE);
						mapProcedureParam.put("err", "#1");
						mapProcedureParam.put("resultset", "#2");
						temp=HisComboOptions.getOptionsFromProc("pkg_simple_view.Proc_Hgbt_Diagnosis_Type_Dtl", mapProcedureParam, "", "0^Select Value", false);
						formBean.setStrDiagnosticTypeValues(temp);
						temp=util.getOptionValue(vo.getStrAdmissionTypeWS(), "0","-1^Select Value",false);
						formBean.setStrAdmissionTypeValues(temp);
						temp=util.getOptionValue(vo.getStrTariffNameWS(), "0","0^Select Value",false);
						formBean.setStrTariffNameValues(temp);
						
						/*if(vo.getPackageComboValuesWs()!=null && vo.getPackageComboValuesWs().size()>0)
							temp = util.getOptionValue(vo.getPackageComboValuesWs(), "0","0^Select Value", false);
						else
							temp = util.getOptionValue(vo.getPackageComboValuesWs(), "0","0^Select Value", false);
						
						formBean.setStrPackageComboValues(temp);*/
		
					} 
					else 
					{	
						throw new Exception(vo.getStrMsgString());	
					}
				}
				
				formBean.setStrUnitValue(deptUnitCodeFb);
				//System.out.println("deptunitcode is "+formBean.getStrUnitValue()+" "+deptUnitCodeFb);
			} 
			catch (Exception e) 
			{
	            String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD","AdmissionAdviceTransDATA->initAdmissionAdvice()",strmsgText);
				if(!vo.getStrMsgType().equals("2"))
				formBean.setStrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
			} 
			finally 
			{
				if (vo != null)
					vo = null;
				if (formBean != null)
					formBean = null;
				if (util != null)
					util = null;
			}
		}
	}

	/**
	 * 
	 * @param formBean
	 * @param request
	 */
	public static void initBedStatus(AdmissionAdviceTransFB formBean,
			HttpServletRequest request) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		String temp = request.getParameter("wardCode");
		String strTemp[] = temp.replace("@", "#").split("#");
		vo.setStrTreatmentCategoryCode(request
				.getParameter("treatmentCategory"));
		vo.setStrWard(strTemp[0]);
		vo.setStrWardTypeCode(strTemp[1]);
		formBean.setStrWard(request.getParameter("wardCode"));
		if (!(request.getParameter("deptname").equals(""))) {
			formBean.setStrDepartment(request.getParameter("deptname"));
			vo.setDeptNameFound(true);
		} else {

			vo.setDeptNameFound(false);
		}

		vo.setStrPrimaryCategory(request.getParameter("treatmentCategory"));
		vo.setStrDepartmentValue(request.getParameter("deptcode"));
		vo.setStrUnitValue(request.getParameter("deptUnitCode"));
		vo.setStrUnitCode(request.getParameter("ageCode"));
		vo.setStrSex(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		vo.setPatCrNo(request.getParameter("crNo"));
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		vo.setStrRoomType(IpdTransConfig.getRoomType());
		// vo.setStrPropAdmissionDate(request.getParameter("bDate"));
		formBean.setStrPropAdmissionDate(request.getParameter("bDate"));
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		bo.setBedStatusValues(vo);
		formBean.setStrMsgString(vo.getStrMsgString());
		formBean.setStrMsgType(vo.getStrMsgType());
		formBean.setStrUnitCode(vo.getStrUnitValue());
		HisUtil util = null;
		try {

			if (formBean.getStrMsgType().equals("0")) {

				util = new HisUtil("Admission Advice Trans",
						"AdmissionAdviceTransDATA");
				temp = util.getOptionValue(vo.getWardTYPES(), vo
						.getStrWardTypeCode(), "0^Select Value", false);
				formBean.setStrWardTypeValue(temp);
				temp = util.getOptionValue(vo.getWardWS(), vo.getStrWard(),
						"0^Select Value", true);
				formBean.setStrWardValues(temp);
				temp = util.getOptionValue(vo.getRoomWs(), "0",
						"0^Select Value", false);
				formBean.setStrRoomValues(temp);
				temp = util.getOptionValue(vo.getRoomTypeWs(), IpdTransConfig
						.getRoomType(), "0^Select Value", false);

				formBean.setStrRoomTypeValues(temp);
				temp = util.getOptionValue(vo.getBedTypeWs(), IpdTransConfig
						.getBedTypeCode(), "0^Select Value", false);

				formBean.setStrBedTypeValues(temp);

				if (vo.isDeptNameFound() == false) {

					temp = util.getOptionValue(vo.getDepartTypeWS(), "0",
							"0^Select Value", true);
					formBean.setStrDepartmentValue(temp);

					formBean.setDeptFound(vo.isDeptNameFound());
				} else {
					formBean.setDeptFound(true);
				}

			} else {

				throw new Exception(formBean.getStrMsgString());

			}

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initBedStatus()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;

		} finally {

			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (util != null)
				util = null;
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public static void initBedStatusDtls(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {
		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrWard(request.getParameter("wardCode"));
		vo.setStrBedType(request.getParameter("bedType"));
		vo.setStrRoomType(request.getParameter("roomType"));
		vo.setStrPropAdmissionDate(request.getParameter("bDate"));
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		bo.setBedStatusDtls(vo);
		String bedStatusView = AdmissionAdviceTransHLP.getBedStatusView(vo
				.getBedStatusWs());
		try {
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(bedStatusView);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initBedStatusDtls()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public static void initBedStatusPatientDtls(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		vo.setStrBedStatusMode(request.getParameter("bStatus"));
		vo.setStrWard(request.getParameter("wardCode"));
		vo.setStrBedType(request.getParameter("bedType"));
		vo.setStrRoomType(request.getParameter("roomType"));
		vo.setStrPropAdmissionDate(request.getParameter("bDate"));
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		bo.setBedStatusPatientDtls(vo);
		String bedStatusPatientView = "";
		if (vo.getStrBedStatusMode().equals("V")) {

			bedStatusPatientView = AdmissionAdviceTransHLP
					.getVacentPatientDtls(vo.getBedStatusPatientWs());
		} else {

			bedStatusPatientView = AdmissionAdviceTransHLP
					.getBookedPatientDtls(vo.getBedStatusPatientWs());
		}

		try {
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(bedStatusPatientView);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initBedStatusPatientDtls()",
					strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}

	/**
	 * 
	 * @param response
	 */
	public static void initHosiptalDiagnosis(HttpServletRequest _Request,HttpServletResponse _Response,
			AdmissionAdviceTransFB _FormBean) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		HisUtil util=null;
		try {
			vo.setStrSearchMode(_FormBean.getStrSearchMode());
			vo.setStrSearchString(_FormBean.getStrSearchString());
			vo.setStrHospCode((String)_Request.getSession().getAttribute("HOSPITAL_CODE"));
			bo.setHospitalDiagnosis(vo);
			/*String strHospDiagValues = "";
			boolean fFirst = true;
			while(vo.getHospitalDiagnosisWs().next()){
				if(fFirst){
					strHospDiagValues =  vo.getHospitalDiagnosisWs().getString(2)+"/ "+vo.getHospitalDiagnosisWs().getString(1);
					fFirst=false;
				}else
					strHospDiagValues +=  "^"+vo.getHospitalDiagnosisWs().getString(2)+"/ "+vo.getHospitalDiagnosisWs().getString(1);
			}
			fFirst = true;
			vo.getHospitalDiagnosisWs().beforeFirst();
			while(vo.getHospitalDiagnosisWs().next()){
				if(fFirst){
					strHospDiagValues += "#"+ vo.getHospitalDiagnosisWs().getString(1)+"/ "+vo.getHospitalDiagnosisWs().getString(2);
					fFirst=false;
				}else
					strHospDiagValues +=  "^"+vo.getHospitalDiagnosisWs().getString(1)+"/ "+vo.getHospitalDiagnosisWs().getString(2);
			}
			_FormBean.setStrHospDiagValues(strHospDiagValues);*/
			if (!vo.getStrMsgType().equals("0")) 
			{
				throw new Exception(vo.getStrMsgString());
			}			
				util = new HisUtil("ADT" , "AdmissionAdviceTransDATA.initHosiptalDiagnosis");			
				String strICDValues = util.getDropDown(vo.getHospitalDiagnosisWs(), 1, 5, true);			  
					_Response.setHeader("Cache-Control", "no-cache");
					_Response.getWriter().print(strICDValues);

		} catch (Exception e) {
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initHosiptalDiagnosis()",
					strmsgText);
			_FormBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}

	/**
	 * 
	 * @param response
	 */
	public static void initIcdDiagnosis(HttpServletResponse response,
			AdmissionAdviceTransFB formBean) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();

		HisUtil util = null;
		
		try {
			
		vo.setStrSearchMode(formBean.getStrSearchMode());
		vo.setStrSearchString(formBean.getStrSearchString());
		 
		bo.setIcdDiagnosis(vo);

		
		if (!vo.getStrMsgType().equals("0")) {
			throw new Exception(vo.getStrMsgString());
		}
		
		util = new HisUtil("ADT" , "AdmissionAdviceTransDATA.initIcdDiagnosis");
		
			String strICDValues = util.getDropDown(vo.getIcd10DiagnosisWs(), 1, 5, true);
			
		  
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strICDValues);
			 
		} catch (Exception e) {

			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initIcdDiagnosis()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public static void initWardCombo(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {
		String temp = "";
		try {
			AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());			
			vo.setStrPrimaryCategory(request.getParameter("treatmentCategory"));
			vo.setStrDepartmentValue(request.getParameter("deptcode"));
			vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			vo.setStrUnitCode(request.getParameter("ageCode"));
			vo.setPatCrNo(request.getParameter("crNo"));
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());			
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrDepartment(request.getParameter("deptCode"));
			vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			vo.setStrUnitCode(request.getParameter("unitCode"));
			vo.setStrSex(request.getParameter("sex"));
			vo.setStrAge(request.getParameter("age"));			
			vo.setStrDiseaseTypeCode(request.getParameter("diseaseType"));
			AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
			bo.setWardCombo(vo);
			HisUtil util = new HisUtil("Admission Advice Trans","AdmissionAdviceTransDATA");
			temp = util.getOptionValue(vo.getWardWS(), "0", "0^Select Ward",true);
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initWardCombo()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	/**
	 * 
	 * @param formBean
	 */
	public static void insert(AdmissionAdviceTransFB formBean) 
	{
		IpdConfigUtil ipdConfig = null;
		
		try 
		{
			ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
			AdmissionAdviceTransVO voObj = (AdmissionAdviceTransVO) TransferObjectFactory.populateData("ipd.transactions.AdmissionAdviceTransVO",formBean);
			voObj.setStrIssueEstCertificate(formBean.isStrIssueEstCertificate());
			voObj.setStrBillFlag(ipdConfig.getStrIntegrationBilling());//Billing Online or Offline
			voObj.setStrAdvanceFlag(ipdConfig.getStrAdvanceRequestAdmissionAdvice());//Advance To Be taken or not if Online Billing
			AdmissionAdviceTransBO businessObj = new AdmissionAdviceTransBO();

			businessObj.insert(voObj);
			formBean.setStrMsgType(voObj.getStrMsgType());

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			} 
			else 
			{
				formBean.setStrMsgString("Admission Advice Successfully Generated");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","AdmissionAdviceTransDATA->insert()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public static void initRoomCombo(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {
		String temp = "";
		try {
			AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String temp1 = request.getParameter("wardCode");
			String strTemp[] = temp1.replace("^", "#").split("#");
			vo.setStrWard(strTemp[0]);
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrRoomType(request.getParameter("roomTypeCode"));
			vo.setStrPrimaryCategory(request.getParameter("treatmentCategory"));
			vo.setStrDepartmentValue(request.getParameter("deptcode"));
			vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			vo.setStrUnitCode(request.getParameter("ageCode"));
			vo.setPatCrNo(request.getParameter("crNo"));
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrTreatmentCategoryCode(request
					.getParameter("treatmentCategory"));
			vo.setStrUnitCode(request.getParameter("ageCode"));
			vo.setStrSex(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			
			
			AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
			
						bo.setRoomCombo(vo);
			HisUtil util = new HisUtil("Admission Advice Trans",
					"AdmissionAdviceTransDATA");
			temp = util.getOptionValue(vo.getRoomWs(), "0", "0^Select Value",
					false);
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initRoomCombo()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	/**
	 * This function invoke first AdmissionAdviceTransBO's setBedDetail() and
	 * then invoke AdmissionAdviceTransHLP's getBedDetails()
	 * 
	 * @param request
	 * @param response
	 */
	public static void initBedDetails(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {
		try {
			AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
			vo.setStrWard(request.getParameter("wardCode"));
			vo.setStrRoomCode(request.getParameter("roomCode"));
			vo.setStrBedType(request.getParameter("bedType"));
			vo.setStrUnitValue(request.getParameter("deptUnitCode"));
			bo.setBedDetail(vo);
			String res = AdmissionAdviceTransHLP.getBedDetails(vo);
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(res);
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initBedDetails()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

	public static void initList(AdmissionAdviceTransFB formBean,
			HttpServletRequest request) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		// vo.setStrDepartmentValue("104");
		// vo.setStrUnitValue("10010412001");
		vo.setStrDepartment(request.getParameter("deptname"));
		vo.setStrUnitValue(request.getParameter("unitvalue"));
		vo.setStrCrNo(formBean.getStrCrNo());
		// vo.setStrUnit(request.getParameter("unitname"));
		if(formBean.getStrAdviceDate().equals("") || formBean.getStrAdviceDate()==null)
		vo.setStrAdviceDate(formBean.getStrCtDate());
		else
			vo.setStrAdviceDate(formBean.getStrAdviceDate());
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		bo.setListDtls(vo);
		formBean.setStrMsgString(vo.getStrMsgString());
		formBean.setStrMsgType(vo.getStrMsgType());
		String a = AdmissionAdviceTransHLP.getListView_BS(vo.getListWs());
		formBean.setListView(a);
		formBean.setStrUnit(vo.getStrUnit());
		formBean.setStrWarning(vo.getStrWarning());
		HisUtil util = null;
		// String temp = "";
		try {

			if (formBean.getStrMsgType().equals("0")) {

				formBean.setStrAdviceDate(vo.getStrAdviceDate());
				formBean.setStrDepartment(vo.getStrDepartment());
				formBean.setStrUnit(vo.getStrUnit());
				formBean.setStrDepartmentValue(vo.getStrDepartmentValue());
				formBean.setStrUnitValue(vo.getStrUnitValue());

				util = new HisUtil("Admission Advice Trans",
						"AdmissionAdviceTransDATA");

				/*
				 * temp = util.getOptionValue(vo.getDepartmentWs(), "0",
				 * "0^Select Value", false);
				 * formBean.setStrDepartmentValue(temp);
				 * 
				 * temp = util.getOptionValue(vo.getUnitWs(), "0", "0^Select
				 * Value", false); formBean.setStrUnitValue(temp);
				 * 
				 * temp=util.getOptionValue(vo.getDepartmentWs(),"0", "0^Select
				 * Value", true); formBean.setStrDepartment(temp);
				 */
				// temp = util.getASDate("bDate");
			} else {

				throw new Exception(formBean.getStrMsgString());

			}

		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initList()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		} finally {

			if (vo != null)
				vo = null;
			if (formBean != null)
				formBean = null;
			if (util != null)
				util = null;
		}

	}

	public static void initListDtls(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {

		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		vo.setStrAdviceDate(request.getParameter("advdate"));
		vo.setStrDepartment(request.getParameter("deptname"));
		vo.setStrUnit(request.getParameter("unitname"));
		vo.setStrDepartmentValue(request.getParameter("deptvalue"));
		vo.setStrUnitValue(request.getParameter("unitvalue"));

		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		bo.setListDtls(vo);

		String listView = AdmissionAdviceTransHLP.getListView_BS(vo.getListWs());

		try {
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(listView);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initListDtls()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}

	}

	public static void initWardComboBedDetails(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {
		AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
		
		
		
		vo.setStrDepartmentValue(request.getParameter("deptcode"));
		vo.setStrUnitValue(request.getParameter("deptUnitCode"));
		vo.setStrUnitCode(request.getParameter("ageCode"));
		
		vo.setPatCrNo(request.getParameter("crNo"));
		vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		vo.setStrTreatmentCategoryCode(request
				.getParameter("treatmentCategory"));
		vo.setStrSex(request.getParameter("sexCode"));
		vo.setStrAge(request.getParameter("age"));
		vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
		
		
		
		bo.setWardComboBedDetails(vo);
		HisUtil util = null;
		String temp = "";
		try {
			util = new HisUtil("Admission Advice Trans",
					"AdmissionAdviceTransDATA");
			temp = util.getOptionValue(vo.getWardWS(), "0", "0^SelectValue",
					false);
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->initWardComboBedDetails()",
					strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;

		}

	}

	public static void checkDupicate(HttpServletRequest request,
			HttpServletResponse response, AdmissionAdviceTransFB formBean) {
		try {
			String pk = request.getParameter("wardCode");
			String deptUnitCode = request.getParameter("deptUnitCode");
			String temp[] = pk.replace("@", "#").split("#");
			AdmissionAdviceTransVO vo = new AdmissionAdviceTransVO();
			vo.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			AdmissionAdviceTransBO bo = new AdmissionAdviceTransBO();
			vo.setStrWard(temp[0]);
			vo.setStrUnitValue(deptUnitCode);
			vo.setPatCrNo(request.getParameter("crno"));
			bo.CheckDuplicate(vo);
			if (vo.getStrMsgType().equals("0")) {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(vo.getStrFlag());
			} else {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->checkDupicate()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;

		}
	}
	public static void setPackageAmountValues( AdmissionAdviceTransFB formBean, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		try {
			String temp = "";
			WebRowSet ws=null;
			HisUtil util = new HisUtil("ADT","AdmissionAdviceTransDATA");
			AdmissionAdviceTransVO admissionAdviceTransVO = new AdmissionAdviceTransVO();
			admissionAdviceTransVO.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			admissionAdviceTransVO.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			admissionAdviceTransVO.setStrWardCode(request.getParameter("wardCode").replace("@", "#").split("#")[0]);
			admissionAdviceTransVO.setStrTreatmentCategoryCode(request.getParameter("catcode"));
			
			AdmissionAdviceTransBO admissionAdviceTransBO = new AdmissionAdviceTransBO();
			admissionAdviceTransBO.getBillingPackageNames(admissionAdviceTransVO);
			if(admissionAdviceTransVO.getPackageComboValuesWs()!=null && admissionAdviceTransVO.getPackageComboValuesWs().size()>0)
				temp = util.getOptionValue(admissionAdviceTransVO.getPackageComboValuesWs(), "0","0^Select Value", false);
			else
				temp = util.getOptionValue(admissionAdviceTransVO.getPackageComboValuesWs(), "0","0^Select Value", false);
			
			formBean.setStrPackageComboValues(temp);
			response.getWriter().print(formBean.getStrPackageComboValues());
			
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD",
					"AdmissionAdviceTransDATA->setPackageAmountValues()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}
}
