/**
##		Date					: 05-Aug-2019
##		Reason	(CR/PRS)		: External Investigation Section at SPD 
##		Created By				: Vasu
*/


package ehr.externalinvestigation.presentation;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;
import inpatient.transaction.controller.utl.PatNursingDeskDataFlagsMenuWiseUTL;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ehr.EHRConfig;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO;
import ehr.history.presentation.EHRSection_HistoryDATA;
import ehr.history.presentation.EHRSection_HistoryFB;
import ehr.history.vo.EHRSection_HistoryVO;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentDATA;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.vo.EHR_PatEncounterDetailsVO;
import emr.vo.PatientClinicalDocDetailVO;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import opd.OpdConfig;
import opd.transaction.controller.data.ExternalInvestigationDATA;
import registration.controller.fb.CRNoFB;

public class EHRSection_ExternalInvestigationUTL extends ControllerUTIL
{

	public static void getParameterForExtInv(EHRSection_ExternalInvestigationFB fb, HttpServletRequest request)
	{

		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setRecordTimeHr(time.split(":")[0]);
			fb.setRecordTimeMin(time.split(":")[1]);
			fb.setHiddenTimeHr(fb.getRecordTimeHr());
			fb.setHiddenTimeMin(fb.getRecordTimeMin());
			
			Map externalInvestigationEssentials = new HashMap();
			
			PatientDetailVO selectedPatientVO =null;
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			externalInvestigationEssentials= EHRSection_ExternalInvestigationDATA.getParameterForExtInv(selectedPatientVO,getUserVO(request));
			
			List lstParameter = (List)externalInvestigationEssentials.get(EHRConfig.PARAMETERS_FOR_EXTERNAL_INVESTIGATION);
			//String parameterList = new Gson().toJson(lstParameter);
			WebUTIL.setAttributeInSession(request, OpdConfig.EXT_INV_PARAMETER_LIST ,lstParameter);
			
			List lstTests = (List)externalInvestigationEssentials.get(EHRConfig.TESTS_FOR_EXTERNAL_INVESTIGATION);
			//String testsList = new Gson().toJson(lstTests);
			WebUTIL.setAttributeInSession(request, EHRConfig.TESTS_FOR_EXTERNAL_INVESTIGATION ,lstTests );
			
			EHRSection_ExternalInvestigationVO[] epiAddedExtInvDtlVO = (EHRSection_ExternalInvestigationVO[])externalInvestigationEssentials.get(EHRConfig.ADDED_EXTERNAL_INVESTIGATIONS);
			
			if(epiAddedExtInvDtlVO.length>0)
			{
				for(int i=0;i<epiAddedExtInvDtlVO.length;i++)
				{
					String dt=epiAddedExtInvDtlVO[i].getRecordDate();
					epiAddedExtInvDtlVO[i].setRecordDate(dt.split(" ")[0]);
					epiAddedExtInvDtlVO[i].setRecordTime(dt.split(" ")[1]);
				}
			}
			//String extInvAdded = new Gson().toJson(epiAddedExtInvDtlVO);
			WebUTIL.setAttributeInSession(request, EHRConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL, epiAddedExtInvDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
		
	}

	public static void saveExternalInvestigationDetails(HttpServletRequest _rq, HttpServletResponse response,EHRSection_ExternalInvestigationFB _fb)
	{

		String isSave = "true";
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List<EHRSection_ExternalInvestigationVO> lstExtInvDtl= new ArrayList<EHRSection_ExternalInvestigationVO>();
		int currentDiagLength = 0;
		
		try
		{
			
			PatientDetailVO selectedPatientVO =null;
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);	
			//HelperMethods.populatetToNullOrEmpty(selectedPatientVO, _fb);
			currentDiagLength=_fb.getSelRecordDate().length;
			
			
			for (int i = 0; i < currentDiagLength; i++)
			{
				
					if(_fb.getSelTestName()!=null && _fb.getSelTestName().length>i && !_fb.getSelTestName()[i].isEmpty())
					{
						EHRSection_ExternalInvestigationVO ExtInvDtlVO  = new EHRSection_ExternalInvestigationVO();
						
						ExtInvDtlVO.setSelRecordDate(_fb.getSelRecordDate()[i]);
						//ExtInvDtlVO.setSelRecordTimeHr(_fb.getSelRecordTimeHr()[i]);
						//ExtInvDtlVO.setSelRecordTimeMin(_fb.getSelRecordTimeMin()[i]);
						ExtInvDtlVO.setRecordTime(_fb.getSelRecordTimeHr()[i]+":"+_fb.getSelRecordTimeMin()[i]);
						ExtInvDtlVO.setSelTestName(_fb.getSelTestName()[i]);
						ExtInvDtlVO.setSelTestCode(_fb.getSelTestCode()[i]);
						ExtInvDtlVO.setSelParaName(_fb.getSelParaName()[i]);
						ExtInvDtlVO.setSelParaCode(_fb.getSelParaCode()[i]);
						ExtInvDtlVO.setSelParaValue(_fb.getSelParaValue()[i]);
						
						ExtInvDtlVO.setTestConductedFrom(_fb.getTestConductedFrom());
						ExtInvDtlVO.setExtLabAdd(_fb.getExtLabAdd());
						ExtInvDtlVO.setExtLabContactNo(_fb.getExtLabContactNo());
						ExtInvDtlVO.setExtLabName(_fb.getExtLabName());	
						
						lstExtInvDtl.add(ExtInvDtlVO);
							
				}
													
				
			}
			
			//System.out.println("");
			EHRSection_ExternalInvestigationDATA.saveExtInvestigationDetail(lstExtInvDtl,selectedPatientVO,getUserVO(_rq));
			
			objStatus.add(Status.DONE, "Record Saved", "");
		}

		
		catch (HisDataAccessException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		//return isSave;
		
	
		
	}

	public static void deletePreviousExtInvestigations(EHRSection_ExternalInvestigationFB fb, HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		EHRSection_ExternalInvestigationVO EHRExtInvDetailsVO =new EHRSection_ExternalInvestigationVO();
		try
		{
			UserVO userVO = getUserVO(request);	
		    PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		    
		    EHRExtInvDetailsVO.setPatAdmNo(ptaientDetailVO.getAddmissionNo());
		    EHRExtInvDetailsVO.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
		    EHRExtInvDetailsVO.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
		    EHRExtInvDetailsVO.setPatCrNo(ptaientDetailVO.getPatCrNo());
		    EHRExtInvDetailsVO.setSlno(fb.getSlno());
			
			EHRSection_ExternalInvestigationDATA.deletePreviousExtInvestigations(EHRExtInvDetailsVO, userVO);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}


