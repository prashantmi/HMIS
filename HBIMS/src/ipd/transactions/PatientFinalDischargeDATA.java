package ipd.transactions;

import hisglobal.ReportUtil;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;
//import ipd.IpdTransConfig;



import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

public class PatientFinalDischargeDATA
{

	private static final String pathFileName = "hisglobal.hisconfig.hisReport";
//	public static String DATE_FORMAT_NOW = "DD-MM-YYYY HH:mi:ss";
	/** getting Global parameters* */
	// Info
	/*
	 * DISCHARGE_MODE= 1-onLine 2-offLine for others 1-Yes 0-No
	 */
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	private static ResourceBundle confProp = ResourceBundle.getBundle("ipd.ipd_conf");
	public static String dischrg_Param_Req = hisProp.getString("DISCHARGE_PARAM_REQD");
	public static String display_MLC_Dtls = hisProp.getString("DISPLAY_MLC_DTL");
	public static String display_OPD_Visit_Dtls = hisProp.getString("DISPLAY_OPD_VISIT");
	public static String discharge_diagnosis_required = confProp.getString("discharge_icd_diagnosis_required");
	public static String discharge_advice_field_required = confProp.getString("discharge_advice_field_required");
	public static String discharge_summary_printing_required = confProp.getString("discharge_summary_printing_required");

	public static void getRsnRmk(PatientFinalDischargeFB _formBean, HttpServletRequest _Request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		//PatientFinalDischargeBO patientFinalDischargeBO = null;
		try
		{
			patientFinalDischargeVO = new PatientFinalDischargeVO();
			//patientFinalDischargeBO = new PatientFinalDischargeBO();
			//System.out.println("dis mode"+patientFinalDischargeVO.getStrDischrg_Mode());
			if (_formBean.getStrDischrg_Mode().trim().equals("1"))//online
			{
				patientFinalDischargeVO.setStrApprovalComboMode("2");
			}
			else
			{
				patientFinalDischargeVO.setStrApprovalComboMode("3");//offline
			}
			patientFinalDischargeVO.setStrCurrentDeptUnitRoom(_formBean.getStrCurrentDeptUnitRoom());
			patientFinalDischargeVO.setStrHospitalCode(_Request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//patientFinalDischargeBO.getRsnRmk(patientFinalDischargeVO);
			_formBean.setStrDisBy(patientFinalDischargeVO.getStrDisBy());
			_formBean.setStrDisRsn(patientFinalDischargeVO.getStrDisRsn());
			//_formBean.setStrRmk(patientFinalDischargeVO.getStrRmk());
			_formBean.setStrRsn(patientFinalDischargeVO.getStrRsn());
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getRsnRmk()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			//patientFinalDischargeBO = null;
			patientFinalDischargeVO = null;

		}
	}
	public static void getDischargeAdvisedBy(PatientFinalDischargeFB _formBean, HttpServletRequest _Request,HttpServletResponse response)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		try
		{
			patientFinalDischargeVO = new PatientFinalDischargeVO();
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			//System.out.println("dis mode"+patientFinalDischargeVO.getStrDischrg_Mode());
			if (_formBean.getStrDischrg_Mode().trim().equals("1"))//online
			{
				patientFinalDischargeVO.setStrApprovalComboMode("2");
			}
			else
			{
				patientFinalDischargeVO.setStrApprovalComboMode("3");//offline
			}
			patientFinalDischargeVO.setStrHospitalCode(_Request.getSession().getAttribute("HOSPITAL_CODE").toString());
			patientFinalDischargeVO.setStrCurrentDeptUnitRoom(_formBean.getStrCurrentDeptUnitRoom());
			patientFinalDischargeBO.getRsnRmk(patientFinalDischargeVO);
			_formBean.setStrRmk(patientFinalDischargeVO.getStrRmk());
			
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(patientFinalDischargeVO.getStrRmk());
			
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getRsnRmk()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeBO = null;
			patientFinalDischargeVO = null;

		}
	}

	// /////////////////////////////////////////////////////////////////////////////
	public static void getPatientDischargeParameter(PatientFinalDischargeFB _formBean, HttpServletRequest _request, HttpServletResponse _response)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		try
		{
			_formBean.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			_formBean.setStrDeptCode(_request.getParameter("strDeptCode"));
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			String strPatientDischargeParameter = PatientFinalDischargeHLP.getPatientDischargeParameter(patientFinalDischargeVO);
			_formBean.setStrPatientDisParam(strPatientDischargeParameter);
			if (patientFinalDischargeVO.getStrMsgType().equals("1"))
			{
				throw new Exception(patientFinalDischargeVO.getStrMsgString());
			}
			_response.getWriter().println(strPatientDischargeParameter);
		}
		catch (Exception e)
		{
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getPatientDischargeParameter()-->" + e.getMessage());
			try
			{
				_response.getWriter().print("Error####" + "Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			}
			catch (Exception e1)
			{
			}
		}
		finally
		{
			patientFinalDischargeVO = null;

		}
	}

	public static void initFinal_Diagnosis(HttpServletRequest _request, HttpServletResponse _response, PatientFinalDischargeFB _formBean)
	{

		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;

		try
		{
			_formBean.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			patientFinalDischargeVO = new PatientFinalDischargeVO();
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			patientFinalDischargeBO.initFinalDiagnosis(patientFinalDischargeVO);
			// System.out.println("webRowSet->"+patientFinalDischargeVO.getWsFinalDiagnosis());
			HisUtil util = new HisUtil("Patient Final Discharge", "PatientFinalDischargeDATA");
			String strFinalDiagnosis = util.getOptionValue(patientFinalDischargeVO.getWsFinalDiagnosis(), "0", "0^Select Value", false);

			_formBean.setStrDiagnosisTypeCombo(strFinalDiagnosis);
			if (patientFinalDischargeVO.getStrMsgType().equals(""))
			{
				_response.getWriter().print(strFinalDiagnosis);
			}
			else if (patientFinalDischargeVO.getStrMsgType().equals("1"))
			{
				throw new Exception(patientFinalDischargeVO.getStrMsgString());
			}
		}
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientFinalDischargeDATA->initFinalDiagnosis()", strmsgText);
			try
			{
				_response.getWriter().print("Error####" + "Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			}
			catch (Exception e1)
			{
			}
			eObj = null;
		}
		finally
		{
			patientFinalDischargeVO = null;
			patientFinalDischargeBO = null;
		}

	}

	public static void initFinalDiagnosis(HttpServletRequest _request, HttpServletResponse _response, PatientFinalDischargeFB _formBean)
	{
		PatientFinalDischargeVO vo = null;
		PatientFinalDischargeBO bo = null;
		String strICDValues = "";
		HisUtil util = null;
		try
		{
			vo = new PatientFinalDischargeVO();
			bo = new PatientFinalDischargeBO();
			vo.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrSearchString(_formBean.getStrSearchString());
			vo.setStrSearchMode(_formBean.getStrSearchMode());
			bo.initFinalDiagnosis(vo);

			util = new HisUtil("ADT", "AdmissionAdviceTransDATA.initFinalDiagnosis");

			strICDValues = util.getDropDown(vo.getWsFinalDiagnosis(), 1, 5, true);

			_response.setHeader("Cache-Control", "no-cache");
			_response.getWriter().print(strICDValues);
			/*
			 * boolean fFirst = true; while(vo.getWsFinalDiagnosis().next()){ if(fFirst){ strICDValues =
			 * vo.getWsFinalDiagnosis().getString(2)+"/ "+vo.getWsFinalDiagnosis().getString(1); fFirst=false; }else
			 * strICDValues += "^"+vo.getWsFinalDiagnosis().getString(2)+"/ "+vo.getWsFinalDiagnosis().getString(1); } fFirst =
			 * true; vo.getWsFinalDiagnosis().beforeFirst(); while(vo.getWsFinalDiagnosis().next()){ if(fFirst){ strICDValues +=
			 * "#"+ vo.getWsFinalDiagnosis().getString(1)+"/ "+vo.getWsFinalDiagnosis().getString(2); fFirst=false; }else
			 * strICDValues += "^"+vo.getWsFinalDiagnosis().getString(1)+"/ "+vo.getWsFinalDiagnosis().getString(2); } if
			 * (vo.getStrMsgType().equals("")) { _response.setHeader("Cache-Control", "no-cache");
			 * _response.getWriter().print(strICDValues); } else { throw new Exception(vo.getStrMsgString()); }
			 */
		}
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientFinalDischargeDATA->initFinalDiagnosis()", strmsgText);
			try
			{
				_response.getWriter().print("Error####" + "Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			}
			catch (Exception e1)
			{
			}
			eObj = null;
		}
		finally
		{
			bo = null;
			vo = null;
		}
	}

	public static void initHospitalDiagnosis(PatientFinalDischargeFB _formBean, HttpServletRequest _request, HttpServletResponse _response)
	{
		PatientFinalDischargeVO vo = null;
		PatientFinalDischargeBO bo = null;
		String strICDValues = "";
		HisUtil util = null;
		try
		{
			vo = new PatientFinalDischargeVO();
			bo = new PatientFinalDischargeBO();
			vo.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrSearchString(_formBean.getStrSearchString());
			vo.setStrSearchMode(_formBean.getStrSearchMode());
			bo.initFinalDiagnosis(vo);

			util = new HisUtil("ADT", "AdmissionAdviceTransDATA.initFinalDiagnosis");

			strICDValues = util.getDropDown(vo.getWsFinalDiagnosis(), 1, 5, true);

			_response.setHeader("Cache-Control", "no-cache");
			_response.getWriter().print(strICDValues);
			/*
			 * boolean fFirst = true; while(vo.getWsFinalDiagnosis().next()){ if(fFirst){ strICDValues =
			 * vo.getWsFinalDiagnosis().getString(2)+"/ "+vo.getWsFinalDiagnosis().getString(1); fFirst=false; }else
			 * strICDValues += "^"+vo.getWsFinalDiagnosis().getString(2)+"/ "+vo.getWsFinalDiagnosis().getString(1); } fFirst =
			 * true; vo.getWsFinalDiagnosis().beforeFirst(); while(vo.getWsFinalDiagnosis().next()){ if(fFirst){ strICDValues +=
			 * "#"+ vo.getWsFinalDiagnosis().getString(1)+"/ "+vo.getWsFinalDiagnosis().getString(2); fFirst=false; }else
			 * strICDValues += "^"+vo.getWsFinalDiagnosis().getString(1)+"/ "+vo.getWsFinalDiagnosis().getString(2); } if
			 * (vo.getStrMsgType().equals("")) { _response.setHeader("Cache-Control", "no-cache");
			 * _response.getWriter().print(strICDValues); } else { throw new Exception(vo.getStrMsgString()); }
			 */
		}
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientFinalDischargeDATA->initHospitalDiagnosis()", strmsgText);
			try
			{
				_response.getWriter().print("Error####" + "Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			}
			catch (Exception e1)
			{
			}
			eObj = null;
		}
		finally
		{
			bo = null;
			vo = null;
		}
	}

	public static void initIcdDiagnosis(PatientFinalDischargeFB _formBean, HttpServletRequest _request, HttpServletResponse _response)
	{
		PatientFinalDischargeVO vo = null;
		PatientFinalDischargeBO bo = null;
		try
		{
			vo = new PatientFinalDischargeVO();
			bo = new PatientFinalDischargeBO();
			vo.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			vo.setStrSearchString(_formBean.getStrSearchString());
			vo.setStrSearchMode(_formBean.getStrSearchMode());
			bo.setIcdDiagnosis(vo);
			String strICDValues = "";

			HisUtil util = null;

			util = new HisUtil("ADT", "AdmissionAdviceTransDATA.initIcdDiagnosis");

			strICDValues = util.getDropDown(vo.getWsIcd10Diagnosis(), 1, 5, true);

			_response.setHeader("Cache-Control", "no-cache");
			_response.getWriter().print(strICDValues);

			/*
			 * bo.initFinalDiagnosis(vo); String strHospICDValues = ""; boolean fHospFirst = true;
			 * while(vo.getWsFinalDiagnosis().next()){ if(fHospFirst){ strHospICDValues =
			 * vo.getWsFinalDiagnosis().getString(2)+"/ "+vo.getWsFinalDiagnosis().getString(1); fHospFirst=false; }else
			 * strHospICDValues += "^"+vo.getWsFinalDiagnosis().getString(2)+"/ "+vo.getWsFinalDiagnosis().getString(1); }
			 * fHospFirst = true; vo.getWsFinalDiagnosis().beforeFirst(); while(vo.getWsFinalDiagnosis().next()){
			 * if(fHospFirst){ strHospICDValues += "#"+ vo.getWsFinalDiagnosis().getString(1)+"/
			 * "+vo.getWsFinalDiagnosis().getString(2); fHospFirst=false; }else strHospICDValues +=
			 * "^"+vo.getWsFinalDiagnosis().getString(1)+"/ "+vo.getWsFinalDiagnosis().getString(2); } if
			 * (vo.getStrMsgType().equals("")) { _response.setHeader("Cache-Control", "no-cache");
			 * _response.getWriter().print(strICDValues+"~^~"+strHospICDValues); } else { throw new
			 * Exception(vo.getStrMsgString()); }
			 */
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD", "PatientFinalDischargeDATA->initIcdDiagnosis()", strmsgText);
			try
			{
				_response.getWriter().print("Error####" + "Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			}
			catch (Exception e1)
			{
			}
			eObj = null;
		}
		finally
		{
			bo = null;
			vo = null;
		}
	}

	public static void getPatientDischargeTypeCombo(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		LinkedHashMap mapProcedureParam = new LinkedHashMap();
		try
		{
			_formBean.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			patientFinalDischargeBO.getPatientDischargeTypeCombo(patientFinalDischargeVO);

			_formBean.setStrPatientDischargeTypeComboValues(patientFinalDischargeVO.getStrPatientDischargeTypeComboValues());
			if (_formBean.getStrDiagnosisType().equals("0")) // ICD
			mapProcedureParam.put("modeval", "1");
			else mapProcedureParam.put("modeval", "2");
			mapProcedureParam.put("hosp_code", patientFinalDischargeVO.getStrHospitalCode());
			mapProcedureParam.put("err", "#1");
			mapProcedureParam.put("resultset", "#2");

			String temp = HisComboOptions.getOptionsFromProc("pkg_simple_view.Proc_Hgbt_Diagnosis_Type_Dtl", mapProcedureParam, "14", "0^Select Value",false);
			_formBean.setStrDiagnosticTypeValues(temp);
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());

		}
		catch (Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getPatientDischargeTypeCombo()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;
			patientFinalDischargeBO = null;
		}
	}

	public static void getPatientDeathDetails(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		try
		{
			_formBean.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);

			String strDeathDetails = PatientFinalDischargeHLP.getPatientDeathDetails(patientFinalDischargeVO);
			_formBean.setStrIsDead(patientFinalDischargeVO.getStrIsDead());

			_formBean.setStrDeathDetails(strDeathDetails);
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());

		}
		catch (Exception e)
		{
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getPatientDeathDetails()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;

		}
	}

	public static void insert(PatientFinalDischargeFB _formBean, HttpServletRequest _request, HttpServletResponse _response)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		IpdConfigUtil ipdConfig = null;
		try
		{
			
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			_formBean.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			ipdConfig = new IpdConfigUtil(_formBean.getStrHospitalCode());
			_formBean.setStrSeatID((String) _request.getSession().getAttribute("SEATID"));
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			patientFinalDischargeVO.setStrWardCode(_formBean.getCurWrdBedCode().replace("^","#").split("#")[0]);
			_formBean.setStrAdmissionNo(_formBean.getCurAdmNo());
			patientFinalDischargeVO.setStrBillIntegrated(ipdConfig.getStrIntegrationBilling());
			patientFinalDischargeVO.setStrHospitalCode(_formBean.getStrHospitalCode());
			patientFinalDischargeVO.setStrAdvisedBy(_formBean.getStrAdvisedBy());
			patientFinalDischargeVO.setStrRemarksOnline(_formBean.getStrRemarksOnline());
			patientFinalDischargeBO.insert(patientFinalDischargeVO);

			// if (patientFinalDischargeVO.getStrMsgType().equals("1")) {
			// throw new Exception(patientFinalDischargeVO.getStrMsgString());
			// } else
			// _formBean
			// .setStrNormalMsgString("Data Has Been Saved Successfully");

			// _formBean.setStrSaveStatus("1");
			
			//System.out.println("discharge slip required:::    "+ipdConfig.getStrDischargeSlipReq() );
			_formBean.setStrDischargeSummaryPrintRequired(ipdConfig.getStrDischargeSlipReq());
			if (patientFinalDischargeVO.getStrMsgType().equals("1"))
			{
				throw new Exception(patientFinalDischargeVO.getStrMsgString());
			}
			else
			{
				_formBean.setStrNormalMsgString("Patient Has Been Discharged");
				_formBean.setStrCrNo("");
				if (_formBean.getStrTransferUnit().equals(ipdConfig.getStrDischargeTypeAbsconded()))//ABSCONDED
				{
					_formBean.setStrSaveStatus("3");
					 //showAbscondedReport(_formBean, _request,_response);
					//_formBean.setStrNormalMsgString("Wait...Generating Absconded Discharge Report");
				}
				else
				{
					if(_formBean.getStrDischargeSummaryPrintRequired().equals("1"))
					{
					_formBean.setStrSaveStatus("1");
					}
					else
						_formBean.setStrSaveStatus("2");
				//	_formBean.setStrDischargeSummaryPrintRequired(discharge_summary_printing_required);
				}
				
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.insert()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;
			patientFinalDischargeBO = null;
			_request=null;
			_response=null;
			
		}
	}

	/**
	 * @param _formBean
	 * @param _request
	 */
	/**
	 * @param _formBean
	 * @param _request
	 */
	public static void checkAdmin(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		WebRowSet ws = null;
		IpdConfigUtil ipdConfig = null;
		IpdConfigUtil config = new IpdConfigUtil(_formBean.getStrHospitalCode());
		HisUtil util =new HisUtil("IPD", "Final Discharge");
		String dischrg_Mode = config.getStrDischargeProcessType();
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		try
		{
			ipdConfig = new IpdConfigUtil(_formBean.getStrHospitalCode());
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			_formBean.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));
			_formBean.setStrSeatID((String) _request.getSession().getAttribute("SEATID"));
			if(_formBean.getStrCrNo().equals(""))
			{
				_formBean.setStrCrNo(_request.getParameter("patCrNo"));
				_formBean.setCallFromIpdDesk("1");
			}
			_formBean.setStrDisDate(util.getDSDate("DD-Mon-YYYY"));
			patientFinalDischargeVO = new PatientFinalDischargeVO();
			patientFinalDischargeVO.setStrHospitalCode(_formBean.getStrHospitalCode());
			patientFinalDischargeVO.setStrSeatID(_formBean.getStrSeatID());
			patientFinalDischargeVO.setStrCrNo(_formBean.getStrCrNo());
             String Cr_no=_formBean.getStrCrNo();
			patientFinalDischargeBO.checkAdmin(patientFinalDischargeVO);
			
			_formBean.setBillcount(patientFinalDischargeVO.getBillcount());
			
			if(!_formBean.getBillcount().equals("0"))
				patientFinalDischargeBO.checkForBillClearance(patientFinalDischargeVO);
			
		
			String hourminsec=util.getDSDate("hh:mi:ss:AM");
			String[] timeArr=hourminsec.replace(":","#").split("#");
			_formBean.setStrAbsHour(timeArr[0]);
			_formBean.setStrAbsMin(timeArr[1]);
			_formBean.setStrAbsSec(timeArr[2]);
			if(timeArr[3].equals("AM"))
			{
				_formBean.setStrAbs1("AM");
				_formBean.setStrAbs2("PM");				
			}
			else if(timeArr[3].equals("PM"))
			{
				_formBean.setStrAbs1("PM");
				_formBean.setStrAbs2("AM");
		    }
			//if (ipdConfig.getStrIntegrationBilling().equals("1") && ipdConfig.getStrPatientAdjustedFinalDischargeBill().equals("1")) 
			if (ipdConfig.getStrIntegrationBilling().equals("1"))
				patientFinalDischargeBO.checkForBillClearance(patientFinalDischargeVO);
			
			_formBean.setStrMsgType(patientFinalDischargeVO.getStrMsgType());
			if (!(patientFinalDischargeVO.getStrMsgType().equals("4") || patientFinalDischargeVO.getStrMsgType().equals("5")))
			{
				_formBean.setStrCheckAdminWS(patientFinalDischargeVO.getStrCheckAdminWS());
				ws = _formBean.getStrCheckAdminWS(); // details from
				// admission table
				// SETTING GLOBAL PARAMETERS
				 //System.out.println("discharge type"+dischrg_Mode);
				_formBean.setStrDischrg_Mode(dischrg_Mode);
				_formBean.setStrDischargeType(dischrg_Mode);
				_formBean.setStrDischrg_Param_Req(dischrg_Param_Req);
				_formBean.setStrDisplay_MLC_Dtls(display_MLC_Dtls);
				_formBean.setStrDisplay_OPD_Visit_Dtls(display_OPD_Visit_Dtls);
				_formBean.setStrDischargeDiagnosisRequired(discharge_diagnosis_required);
				_formBean.setStrDischargeAdviceFieldRequired(discharge_advice_field_required);
				// END
				if (ws.next())// getting MLC & OPD Visit dtls
				{
					if (Integer.parseInt(ws.getString(17)) > 0) _formBean.setStrMsgType("100");
					_formBean.setStrAdmStatCode(ws.getString(1));
					patientFinalDischargeVO.setStrAdmNo(ws.getString(18));
					patientFinalDischargeVO.setStrAdmStatCode(ws.getString(1));
					if (_formBean.getStrAdmStatCode().equals("11") || _formBean.getStrAdmStatCode().equals("15")
							|| _formBean.getStrAdmStatCode().equals("17"))
					{
						patientFinalDischargeBO.checkTransitTimeClearance(patientFinalDischargeVO);
						if (patientFinalDischargeVO.getStrMsgType().equals(1)) throw new Exception(patientFinalDischargeVO.getStrMsgString());
					}
					_formBean.setStrClearForDischarge(patientFinalDischargeVO.getStrClearForDischarge());
					_formBean.setStrPoliceInfrmDtMLC(ws.getString(5));
					_formBean.setStrAdmNo(ws.getString(18));
					_formBean.setStrApprovDtMLC(ws.getString(6));
					_formBean.setStrApprovByMLC(ws.getString(7));
					_formBean.setStrNxtVisitOPD(ws.getString(8));
					_formBean.setStrRoomOPD(ws.getString(9));
					_formBean.setIsDead(ws.getString(2));
					/* _formBean.setdesch(ws.getString(11));discharge status */
					_formBean.setStrAdviceDate(ws.getString(14));
					_formBean.setStrRmk(ws.getString(13));
					_formBean.setStrRemarksOnline(ws.getString(12));
					_formBean.setStrAdvisedBy(ws.getString(15));
					_formBean.setStrDischargeTypeName(ws.getString(16));
					_formBean.setStrSlipCrNo(Cr_no);
				}
				if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());
				else if (patientFinalDischargeVO.getStrMsgType().equals("3"))
				{
					_formBean.setStrMsgType("3");
				}
			}

		}
		catch (Exception e)
		{
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.checkAdmin()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;

		}
	}

	public static String getMLCDetails(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		String hlpMLC = "";
		try
		{
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			patientFinalDischargeVO.setStrHospitalCode((String) _request.getSession().getAttribute("HOSPITAL_CODE"));

			if (patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("1"))
			{
				hlpMLC = PatientFinalDischargeHLP.getMLCDetails_onLine(patientFinalDischargeVO);
			}
			else
			{
				patientFinalDischargeVO.setStrApprovalComboMode("3");
				patientFinalDischargeBO.getApprovalByCMO(patientFinalDischargeVO);
				hlpMLC = PatientFinalDischargeHLP.getMLCDetails_offLine(patientFinalDischargeVO);
			}
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());

			_formBean.setStrHlpMLC(hlpMLC);
			// System.out.println("hlpMLC->"+hlpMLC);
		}
		catch (Exception e)
		{
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getMLCDetails()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;

		}
		return hlpMLC;
	}

	public static String getDischargeDtl(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		String hlpMLC = "";
		
		IpdConfigUtil ipdC = new IpdConfigUtil(_formBean.getStrHospitalCode());
		try
		{
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",_formBean);
			if (patientFinalDischargeVO.getStrDischrg_Mode().trim().equals(ipdC.getStrDischargeTypeLAMA()))// LAMA
			{
				hlpMLC = PatientFinalDischargeHLP.getLAMA(patientFinalDischargeVO);
			}
			if (patientFinalDischargeVO.getStrDischrg_Mode().trim().equals(ipdC.getStrDischargeTypeAbsconded()))// Absconded
			{
				hlpMLC = PatientFinalDischargeHLP.getPatientAbscondedDetails(patientFinalDischargeVO);
			}
			if (patientFinalDischargeVO.getStrDischrg_Mode().trim().equals(ipdC.getStrDischargeTypeTransfer()))// Transfer
			{
				hlpMLC = PatientFinalDischargeHLP.getTransfered(patientFinalDischargeVO);
			}
		}
		catch (Exception e)
		{
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getDischargeDtl()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;

		}
		return hlpMLC;
	}

	public static String getOPDVisitDetails(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		// String tmp[]=null;
		String hlpOPD = "";
		try
		{
			// System.out.println("_formBean.getCurDept_Unt_RomCode()->"+_formBean.getCurDept_Unt_RomCode());
			// tmp=(_formBean.getCurDept_Unt_RomCode()).replace('^',
			// '#').split("#");
			// System.out.println("deptunit->"+tmp[1]);
			// _formBean.setStrDeptUnitCode(tmp[1]);

			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			if (patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("1"))
			{
				hlpOPD = PatientFinalDischargeHLP.getOPD_VisitDetails_onLine(patientFinalDischargeVO);
			}
			else
			{
				patientFinalDischargeBO.getOPDVisitRoom(patientFinalDischargeVO);
				hlpOPD = PatientFinalDischargeHLP.getOPD_VisitDetails_offLine(patientFinalDischargeVO);
			}
			if (patientFinalDischargeVO.getStrMsgType().trim().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());
			_formBean.setStrHlpOPD(hlpOPD);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getOPDVisitDetails()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;
		}
		return hlpOPD;
	}

	public static void showReport(PatientFinalDischargeFB formBean, HttpServletRequest request, HttpServletResponse response)
	{

		// ResourceBundle rsBundle = ResourceBundle.getBundle(pathFileName);
		String path = HisUtil.getParameterFromHisPathXML("IPD_PATH");
		IpdConfigUtil ipdUtil = null;

		ReportUtil report = null;

		try
		{

			ipdUtil = new IpdConfigUtil(formBean.getStrHospitalCode());
			report = new ReportUtil();

			String strAdmNo = formBean.getStrAdmissionNo();
			String strHospCode = formBean.getStrHospitalCode();

			String strFileName = path + File.separator + strAdmNo + ".pdf";
			File f = new File(strFileName);

			if (f.exists())
			{

				report.displayReportFromFile(response, "IPD", strAdmNo, "pdf");

			}
			else
			{

				//String strDischargeFooter = ipdUtil.getStrDischargeSummaryReportFooter();
				String strDischargeFooter ="Computer Generated Report,Signature Not Required";
				String strReportAdvice = ipdUtil.getStrDischargeSummaryReportAdvice();
				String reportPath = "/ipd/reports/dischargeSummary.rptdesign";

				//String strDischargeImage = HisUtil.getParameterFromHisPathXML("DISCHARGE_IMAGE");

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("adm_No", strAdmNo);
				params.put("hosp_Code", strHospCode);
				params.put("report_Name", "Discharge Summary");
				params.put("report_Advice", strReportAdvice);
				params.put("discharge_Footer", strDischargeFooter);
				params.put("discharge_Image", "");

				report.saveReport(request, response, reportPath, "IPD", strAdmNo, "pdf", params, true);
				formBean.setStrNormalMsgString("Discharge Summary Report Successfully Generated!!");

			}
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}

	}

	public static void showAbscondedReport(PatientFinalDischargeFB formBean, HttpServletRequest request, HttpServletResponse response)
	{

		ResourceBundle rsBundle = ResourceBundle.getBundle(pathFileName);
		String path = HisUtil.getParameterFromHisPathXML("TEMP_PATH");

		ReportUtil report = null;

		try
		{

			report = new ReportUtil();
            String strAdmNo = formBean.getStrAdmissionNo();
			String strHospCode = formBean.getStrHospitalCode();
			String strReportName = "( ABSCONDED FORM )";
			String strReportId = formBean.getStrReportId();

			String strFileName = path + File.separator + strAdmNo + ".pdf";

			File f = new File(strFileName);

			if (f.exists())
			{
				//System.out.println("1");

				report.displayReportFromFile(response, "IPD", strAdmNo, "pdf");

			}
			else
			{
//System.out.println("2");
				String strStationOffAdd = rsBundle.getString("STATIONOFF_ADDRESS");
				String strPinCode = rsBundle.getString("PIN_CODE");
				String reportPath = "/ipd/reports/abscondedSummary.rptdesign";

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("adm_Nooo", strAdmNo);
				params.put("hosp_Code", strHospCode);
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);

				params.put("station_offadd", strStationOffAdd);
				params.put("pin_Code", strPinCode);
				formBean.setStrNormalMsgString("Discharge Summary(Absconded Form) Report Successfully Generated!!");
				report.saveReport(request, response, reportPath, "IPD", strAdmNo, "pdf", params, true);
				

			}
		}
		catch (Exception e)
		{

			e.printStackTrace();

		}

	}

	public static void getStrTreatmentResultCombo(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		try
		{
			
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			patientFinalDischargeBO.getStrTreatmentResultCombo(patientFinalDischargeVO);

			_formBean.setStrTreatmentResultComboValues(patientFinalDischargeVO.getStrTreatmentResultComboValues());
			
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());

		}
		catch (Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getStrTreatmentResultCombo()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;
			patientFinalDischargeBO = null;
		}
	}
	
	public static void getStrProfileId(PatientFinalDischargeFB _formBean, HttpServletRequest _request)
	{
		PatientFinalDischargeVO patientFinalDischargeVO = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		try
		{
			
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeVO = (PatientFinalDischargeVO) TransferObjectFactory.populateData("ipd.transactions.PatientFinalDischargeVO",
					_formBean);
			if(_formBean.getStrCrNo().equals(""))
			{
				_formBean.setStrCrNo(_request.getParameter("patCrNo"));
				_formBean.setCallFromIpdDesk("1");
			}
			patientFinalDischargeVO.setStrCrNo(_formBean.getStrCrNo());
			patientFinalDischargeBO.getStrProfileId(patientFinalDischargeVO);

			_formBean.setStrProfileId(patientFinalDischargeVO.getStrProfileId());
			
			if (patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(patientFinalDischargeVO.getStrMsgString());

		}
		catch (Exception e)
		{
			e.printStackTrace();
			HisException eObj = new HisException("Patient Final Discharge Transaction", "PatientFinalDischargeDATA",
					"PatientFinalDischargeDATA.getStrProfileId()-->" + e.getMessage());
			_formBean.setStrErrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		}
		finally
		{
			patientFinalDischargeVO = null;
			patientFinalDischargeBO = null;
		}
	}
	public static void printSlip(PatientFinalDischargeFB formBean) 
	{	
		PatientAdmissionTransVO voObj= null;
		PatientAdmissionTransBO bo= null;
		IpdConfigUtil ipdC=new IpdConfigUtil(formBean.getStrHospitalCode());
		StringBuffer sb1=new StringBuffer();
		
		try 
		{
			voObj =  new PatientAdmissionTransVO();
			bo    =  new PatientAdmissionTransBO();
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatID());
			voObj.setStrAdmNo(formBean.getStrAdmNo());
			bo.printSlip(voObj);
			formBean.setStrDeptUnitName(voObj.getStrDeptName()+"/"+voObj.getStrUnitName());
			formBean.setStrWardName(voObj.getStrWardName());
			formBean.setStrConsultantName(voObj.getStrConsultantName());
			formBean.setStrMlcNo(voObj.getStrMlcNo());
			formBean.setStrRoom(voObj.getStrRoom());
			formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
			formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
			formBean.setStrMotherName(voObj.getStrMotherName());
			formBean.setStrDepartmentName(voObj.getStrDeptName());
			formBean.setStrDeptUnitName(voObj.getStrDeptUnitName());
			formBean.setStrPatName(voObj.getStrPatName());
			formBean.setStrAdmNo(voObj.getStrAdmNo());
			formBean.setStrAdmDateTime(voObj.getStrAdmDateTime());
			formBean.setStrFatherName(voObj.getStrFatherName());
			formBean.setStrHusbandName(voObj.getStrHusbandName());
			formBean.setStrAge(voObj.getStrAge());
			formBean.setStrAddress(voObj.getStrAddress());	
			formBean.setStrPhoneNo(voObj.getStrPhoneNo());
			formBean.setStrPinCode(voObj.getStrPinCode());
			formBean.setStrMonthlyIncome(voObj.getStrMonthlyIncome());
			formBean.setStrIsNewBorn(voObj.getStrIsNewBorn());
			formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
			formBean.setStrMotherName(voObj.getStrMotherName());
			formBean.setStrBed(voObj.getStrBed());
			formBean.setStrMaritalStatus(voObj.getStrMaritalStatus());
			formBean.setStrHospDtl(voObj.getStrHospDtl());
			if(voObj.getStrEmgAddressFlag().equals("1"))
			{
				formBean.setStrEmgAddressFlag("1");
				formBean.setStrFirstPersonName(voObj.getStrFirstPersonName());
				formBean.setStrEmgAddress1(voObj.getStrEmgAddress1());
				formBean.setStrFirstpersonphone(voObj.getStrFirstpersonphone());
			}
			
			formBean.setStrPatCategoryName(voObj.getStrPatCategoryName());
			formBean.setStrPatientIdNumber(voObj.getStrPatientIdNumber());
			formBean.setStrPatientAdhaarNo(voObj.getStrPatientAdhaarNo());
			formBean.setStrAdmissionCharge(voObj.getStrAdmissionCharge());
			
			formBean.setStrIsAdvanceAmountAtAdmission(ipdC.getStrAdmissionCharge());
			
			formBean.setStrDOB(voObj.getStrDobTime());
            formBean.setStrMaritalStatus(voObj.getStrMaritalStatus());
			formBean.setStrHouseNo(voObj.getStrHouseNo());	
			formBean.setStrStreet(voObj.getStrStreet());	
			formBean.setStrCity(voObj.getStrCity());    
			formBean.setStrDistrict(voObj.getStrDistrict());	
			formBean.setStrStateName(voObj.getStrStateName());
			formBean.setStrPinCode(voObj.getStrPinCode()); 
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountReceiptNo(voObj.getStrAdvanceAmountReceiptNo());//Account No/Bill No
			formBean.setStrAdvanceAmountDate(voObj.getStrAdvanceAmountDate());
			formBean.setStrMobileNo(voObj.getStrMobileNo());
			formBean.setStrCaseSheetNo(voObj.getStrCaseSheetNo());
			formBean.setStrIsCreditAdvanceBilling(voObj.getStrIsCreditAdvanceBilling());
			formBean.setStrCreditLetterRefNo(voObj.getStrCreditLetterRefNo());
			formBean.setStrCreditLetterDate(voObj.getStrCreditLetterDate());
			formBean.setStrClientName(voObj.getStrClientName());
			formBean.setStrStaffNo(voObj.getStrStaffNo());
			formBean.setStrStaffName(voObj.getStrStaffName());
			formBean.setStrRelationship(voObj.getStrRelationship());
			formBean.setStrTehsil(voObj.getStrTehsil());
			formBean.setStrPolInfo(voObj.getStrPolInfo());
			formBean.setStrNamInf(voObj.getStrNamInf());
			formBean.setStrIdenMark(voObj.getStrIdenMark());
			formBean.setStrRefRem(voObj.getStrRefRem());
			formBean.setStrDetPs(voObj.getStrDetPs());
			formBean.setStrHospName(voObj.getStrHospName());
			//System.out.println("voObj.getDaysStay():::"+voObj.getDaysStay()+"voObj.getDisDateTime():::"+voObj.getDisDateTime());
			formBean.setStrDaysStay(voObj.getDaysStay());
			formBean.setStrDisDateTime(voObj.getDisDateTime());
			System.out.println("voObj.getDisDateTime()"+voObj.getDisDateTime());
			StringBuffer sb=new StringBuffer();

			sb.append("CR No. :"+formBean.getStrCrNo());
			sb.append("\nAdmission No. :"+formBean.getStrAdmNo());
			sb.append("\nName :"+formBean.getStrPatName());
			sb.append("\nFather/Spouse Name :"+formBean.getStrFatherName());
			sb.append("\nMother Name :"+formBean.getStrFatherName());
			sb.append("\nDepartment Name :"+ (formBean.getStrDepartmentName().equals("") ? "--" :formBean.getStrDepartmentName()));
			sb.append("\nWard Name :"+formBean.getStrWardName());
			sb.append("\nAdmission Date :"+formBean.getStrAdmDateTime());
			sb.append("\nAge/Gender :"+formBean.getStrAge());
			sb.append("\nPhone/Mobile :"+formBean.getStrPhoneNo());
			//sb.append("\nCategory :"+formBean.getStrPatCategoryName()+"/"+(formBean.getStrPatientIdNumber().equals("") ? "---" : formBean.getStrPatientIdNumber()));
			sb.append("\nCategory :"+formBean.getStrPatCategoryName());
			
			formBean.setStrPatCatGrp(voObj.getStrPatCatGrp());
			
			if(formBean.getStrPatCatGrp().equals("0"))//Paid
			{
				//sb1.append("<table width='100%' style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%' style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'><div align='left'><b>Advance Amount</b></div></td>");
				
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'><div align='left'><b>Mode Of Payment</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'><div align='left'><b>Details</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'><div align='left'><b></b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>Paying</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'>"+formBean.getStrAdvanceAmount()+"</td>");
				
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'>Cash</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'>"+formBean.getStrAdvanceAmountDate()+"/"+formBean.getStrAdvanceAmountReceiptNo()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='25%'></td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			else if(formBean.getStrPatCatGrp().equals("3"))
			{
				//sb1.append("<table width='100%' style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'  style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'><div align='left'><b>Org. Name</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'><div align='left'><b>Letter No./Date</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='30%'><div align='left'><b>Eligible Amount</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>Credit</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'>"+formBean.getStrClientName()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'>"+formBean.getStrCreditLetterRefNo()+"/"+formBean.getStrCreditLetterDate()+"</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='30%'>"+formBean.getStrAdvanceAmount()+"</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			
			else if(formBean.getStrPatCatGrp().equals("1"))//Staff
			{
				//sb1.append("<table width='100%'style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%' style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>Self/Depenedent</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='20%'><div align='left'><b>Name of Staff(if Dependent)</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>Relationhip</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>EC No.</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>SD By</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				//sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>Satff</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			
			else if(formBean.getStrPatCatGrp().equals("4"))
			{
				//sb1.append("<table width='100%' style='border:2px solid black;border-collapse: collapse; '>");
				sb1.append("<tr>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%' style='background-color:silver;'><div align='left'><b>Patient Category</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'><div align='left'><b>WAP/Card No.</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'><div align='left'><b>Code No.</b></div></td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'><div align='left'><b>Eligible Amount</b></div></td>");
				sb1.append("</tr>");
				
				sb1.append("<tr>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>ArogyaShree</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='1' width='20%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='25%'>--</td>");
				sb1.append("<td class='SLIPCONTROL' colspan='2' width='35%'>--</td>");
				sb1.append("</tr>");
				//sb1.append("</table>");
			}
			formBean.setStrPatCatSlip(sb1.toString());
			
			
			
			//String qrCodeText=Qrcode.getText(sb.toString());
			//String filePath2=HisUtil.getParameterFromHisPathXML("TEMP_PATH");
		    //String filePath = "C:\\PHDM\\AHIMS\\Qrcode images\\"+formBean.getStrAdmNo()+".png";
			//String filePath = filePath2+ File.separator + formBean.getStrAdmNo()+".png";
		  //  String path_globalImages = resourceBundle.getValueFromKey("IMAGE_PATH_QR");
		    
		  //  System.out.println("path is "+path_globalImages);
		    
		    
		    //int size = 1000;
	        //String fileType = "png";
	        //File qrFile = new File(filePath);
	        //Qrcode.createQRImage(qrFile, qrCodeText, size, fileType);
	        
			
		} 
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("ADT", "PatientAdmissionTransDATA->printSlip()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
			formBean.setStrCrNo("");
		}
}

}
