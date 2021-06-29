package opd.transaction.controller.util;

import inpatient.InpatientConfig;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.dao.PatientProfileDetailDAO;
import opd.dao.PatientProfileDetailDAOi;
import opd.transaction.controller.data.GenericPatientProfileDATA;
import opd.transaction.controller.data.GenericTemplateTileDATA;
import opd.transaction.controller.fb.GenericPatientProfileFB;
import opd.transaction.controller.fb.OpdDocumentArchivalFB;
import opd.transaction.controller.util.GenericPatientProfileUTIL.ProfileProforma;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class GenericAutometicPatientProfileUTIL extends ControllerUTIL {
	
	public static boolean DESKDIAGNOSIS(GenericPatientProfileFB fb,HttpServletRequest request, String patCrNo, String profileType,String deskMenuId, UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL DESKDIAGNOSIS Fun");
		boolean flag = false;
		try
		{
			HttpSession session =request.getSession();
			System.out.println("getEpisodeCode :"+voDP.getEpisodeCode()+" getAdmissionNo :"+voDP.getPatAdmNo()+" getDeskType :"+fb.getDeskType()+" Episode Visit No :"+voDP.getEpisodeVisitNo());
			EpisodeDiagnosisVO[] episodeDiagnosisVOs = GenericPatientProfileDATA.getEpisodeDiagnosis(patCrNo, voDP, fb.getDeskType(), userVO, profileGenerationMode);
			//WebUTIL.setAttributeInSession(request, OpdConfig.OPD_PATIENT_PROFILE_EPISODEDIAGNOSISVO_ARRAY, episodeDiagnosisVOs);						
			if(episodeDiagnosisVOs!=null && episodeDiagnosisVOs.length>0) flag =true;			
			String [] localSelectedRow= new String[episodeDiagnosisVOs.length];
			for(int i=0; i<episodeDiagnosisVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);

			//To SET DIAGNOSIS
			if(flag)
			{
				proforma.setDiagnosisProforma(deskMenuId, episodeDiagnosisVOs, fb.getSelectedRow());
				WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public static boolean DESKALLERGIES(GenericPatientProfileFB fb,HttpServletRequest request, String patCrNo, String deskMenuId, UserVO userVO, ProfileProforma proforma, String profileGenerationMode, PatientDetailVO voDP) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL DESKALLERGIES Fun");
		boolean flag = false;
		try 
		{	
			HttpSession session =request.getSession();
			PatAllergyDtlVO[] patAllergyDtlVOs = null;
			System.out.println("Checking Genration Mode");
				System.out.println("Genration Mode is 2");
				patAllergyDtlVOs = GenericPatientProfileDATA.getPatientAllergiesDetail(patCrNo, userVO, voDP, profileGenerationMode);
			System.out.println("length of patAllergyDtlVOs is :"+patAllergyDtlVOs.length);
			if(patAllergyDtlVOs!=null && patAllergyDtlVOs.length>0) flag =true;
			
			String [] localSelectedRow= new String[patAllergyDtlVOs.length];
			for(int i=0; i<patAllergyDtlVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			System.out.println("Selected row length:"+fb.getSelectedRow().length);
			
			//To SET DESKALLERGIES
			if(flag)
			{
				System.out.println("DeskMenuId :"+deskMenuId+" AllergyDtlVOs :"+patAllergyDtlVOs+" SelectedRow :"+fb.getSelectedRow());
				proforma.setAllergiesProforma(deskMenuId, patAllergyDtlVOs, fb.getSelectedRow());
				WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static boolean GENERICPATIENTALERTS(GenericPatientProfileFB fb, String patCrNo, String deskMenuId, HttpServletRequest request, UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL GENERICPATIENTALERTS Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();
			PatientAlertsDetailVO[] patientAlertsDetailVOs = GenericPatientProfileDATA.getPatientAlertsDetail(patCrNo, userVO ,voDP, profileGenerationMode);
			if(patientAlertsDetailVOs!=null && patientAlertsDetailVOs.length>0) flag =true;
			
			String [] localSelectedRow= new String[patientAlertsDetailVOs.length];
			for(int i=0; i<patientAlertsDetailVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			
			//To SET GENERICPATIENTALERTS
			if(flag)
			{
				proforma.setAlertsProforma(deskMenuId, patientAlertsDetailVOs, fb.getSelectedRow());
				WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;}

	public static boolean DESKTREATMENTDETAIL(GenericPatientProfileFB fb,String patCrNo, HttpServletRequest request,  String deskMenuId, UserVO userVO,ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) {

		System.out.println("in GenericAutometicPatientProfileUTIL GENERICPATIENTALERTS Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();
			PatDrugTreatmentDetailVO[] patDrugTreatmentDtlVO = GenericPatientProfileDATA.getPatientTreatmentDetail(patCrNo, voDP, profileGenerationMode, fb.getDeskType(), userVO);
			System.out.println("length of patDrugTreatmentDtlVO is :"+patDrugTreatmentDtlVO.length);
			if(patDrugTreatmentDtlVO!=null && patDrugTreatmentDtlVO.length>0) flag =true;
			String [] localSelectedRow= new String[patDrugTreatmentDtlVO.length];
			for(int i=0; i<patDrugTreatmentDtlVO.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			//To SET GENERICPATIENTALERTS
			if(flag)
			{
			proforma.setTreatmentProforma(deskMenuId, patDrugTreatmentDtlVO, fb.getSelectedRow());
			WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static boolean EXTERNALEXAMINATION(GenericPatientProfileFB fb,String patCrNo, HttpServletRequest request, String deskMenuId,UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL EXTERNALEXAMINATION Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();
			EpisodeExtInvDtlVO[] episodeExtInvDtlVO = GenericPatientProfileDATA.getEpisodeExtInvestigation(patCrNo, voDP, profileGenerationMode,userVO);
			System.out.println("length of patDrugTreatmentDtlVO is :"+episodeExtInvDtlVO.length);
			if(episodeExtInvDtlVO!=null && episodeExtInvDtlVO.length>0) flag =true;
			
			String [] localSelectedRow= new String[episodeExtInvDtlVO.length];
			for(int i=0; i<episodeExtInvDtlVO.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			//To SET EXTERNALEXAMINATION
			if(flag)
			{
			proforma.setExtInvestigationProforma(deskMenuId, episodeExtInvDtlVO, fb.getSelectedRow());
			WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static boolean RESULTVIEWING(GenericPatientProfileFB fb,String patCrNo, HttpServletRequest request, String deskMenuId,UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL RESULTVIEWING Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();
			System.out.println("GOING FOR length of patDrugTreatmentDtlVO :");
			String deskType =(String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			ProfileInvestigationVO[] profileInvestigationVOs = GenericPatientProfileDATA.getEpisodeInvestigation(patCrNo, deskType, userVO, voDP, profileGenerationMode);
			System.out.println("length of patDrugTreatmentDtlVO is :"+profileInvestigationVOs.length);
			if(profileInvestigationVOs!=null && profileInvestigationVOs.length>0) flag =true;
			
			String [] localSelectedRow= new String[profileInvestigationVOs.length];
			for(int i=0; i<profileInvestigationVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			//To SET RESULTVIEWING
			if(flag)
			{
			proforma.setInvestigationProforma(deskMenuId, profileInvestigationVOs, fb.getSelectedRow());
			WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	
	
	public static boolean OTVIEWING(GenericPatientProfileFB fb, String patCrNo,
			HttpServletRequest request, String deskMenuId, UserVO userVO,
			ProfileProforma proforma, PatientDetailVO voDP,
			String profileGenerationMode) {
		System.out.println("in GenericAutometicPatientProfileUTIL OTVIEWING Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();
			ProfileOTDetailVO[] profileOTDetailVOs = GenericPatientProfileDATA.getPatientOperationDetail(patCrNo, userVO, voDP, profileGenerationMode);
			System.out.println("length of patDrugTreatmentDtlVO is :"+profileOTDetailVOs.length);
			if(profileOTDetailVOs!=null && profileOTDetailVOs.length>0) flag =true;
			
			String [] localSelectedRow= new String[profileOTDetailVOs.length];
			for(int i=0; i<profileOTDetailVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			//To SET OTVIEWING
			if(flag)
			{
			proforma.setOperationDetailProforma(deskMenuId, profileOTDetailVOs, fb.getSelectedRow());
			WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public static boolean IMAGEEXAMINATION(GenericPatientProfileFB fb,
			String patCrNo, HttpServletRequest request, String deskMenuId,
			UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP,
			String profileGenerationMode) {
		System.out.println("in GenericAutometicPatientProfileUTIL IMAGEEXAMINATION Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();
			OpdPatientImageDtlVO[] episodeExamImagesVOs = GenericPatientProfileDATA.getEpisodeExamImages(patCrNo,userVO, voDP, profileGenerationMode);
			System.out.println("length of patDrugTreatmentDtlVO is :"+episodeExamImagesVOs.length);
			if(episodeExamImagesVOs!=null && episodeExamImagesVOs.length>0) flag =true;
			
			String [] localSelectedRow= new String[episodeExamImagesVOs.length];
			for(int i=0; i<episodeExamImagesVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			//To SET IMAGEEXAMINATION
			if(flag)
			{
			proforma.setExamImagesProforma(deskMenuId, episodeExamImagesVOs, fb.getSelectedRow());
			WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	public static boolean DOCTORROUND(GenericPatientProfileFB fb,
			String patCrNo, HttpServletRequest request, String deskMenuId,
			UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP,
			String profileGenerationMode) {
		System.out.println("in GenericAutometicPatientProfileUTIL GENERICPATIENTALERTS Fun");
		boolean flag = false;
		try 
		{
			HttpSession session =request.getSession();	
				DoctorRoundDtlVO[] doctorRoundDtlVOs = GenericPatientProfileDATA.getPatientProgressNotes(patCrNo, userVO, voDP, profileGenerationMode);
			if(doctorRoundDtlVOs!=null && doctorRoundDtlVOs.length>0) flag =true;
			
			String [] localSelectedRow= new String[doctorRoundDtlVOs.length];
			for(int i=0; i<doctorRoundDtlVOs.length; i++)
			{
				localSelectedRow[i]= (i+"");
			}
			fb.setSelectedRow(localSelectedRow);
			
			//To SET DOCTORROUND
			if(flag)
			{
				proforma.setEpiProgressNotesDetailProforma(deskMenuId, doctorRoundDtlVOs, fb.getSelectedRow());
				WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;}

	public static void PRINTHTML(GenericPatientProfileFB _fb, HttpServletRequest _rq, UserVO userVO)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		PatientProfileDetailVO patProfileDtlVO = new PatientProfileDetailVO();
		/*int profile = 0;*/
		//String fileName = profileId + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
		/*HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS,
				Config.PATIENT_PROFILE_STORAGE_PATH_LINUX);
		fileUtil.setFileContent(_fb.getProfileHTML().getBytes());
			fileUtil.saveFile(); */
		ProfileGroupDtlVO[] profileGroupDtlVO = (ProfileGroupDtlVO[]) session.getAttribute(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_VO_ARRAY);
		ProfileAccessPolicyVO profileAccessPolicy = (ProfileAccessPolicyVO) session.getAttribute(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_DETAIL_VO);

		lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
		//patProfileDtlVO = lstPrevProfiles.get(0);
		/*for (int i = 0; i < lstPrevProfiles.size(); i++)
		{
			patProfileDtlVO = lstPrevProfiles.get(i);
			if(_fb.getProfileHeader().equals(patProfileDtlVO.getProfileHeader()))
			{
				patProfileDtlVO = lstPrevProfiles.get(i);
				profile=1;
			}
			if(profile==1)
			{
				break;
			}
		}*/
		
		patProfileDtlVO.setPatCrNo(_fb.getPatCrNo());
		patProfileDtlVO.setProfileHeader(_fb.getProfileHeader());
		patProfileDtlVO.setProfileId(_fb.getProfileId());
		ByteArrayOutputStream baos = HTMLToPDFUTIL.convertHtmlToPDFDirect(_rq, _fb.getProfileHTML());
		byte[] b = baos.toByteArray(); 
		patProfileDtlVO.setProfileDataPdf(b);
		
		patProfileDtlVO.setProfileData(_fb.getProfileHTML());
		
		GenericPatientProfileDATA.profileGeneration(patProfileDtlVO, profileGroupDtlVO, profileAccessPolicy, userVO);
		objStatus.add(Status.DONE, "", "Profile Generated Successfully");
		
		
		//MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_GENERATED_PATIENT_PROFILE_UPLOAD).savePDFFile(fileName,_fb.getProfileHTML().getBytes());
		
		
	}

	
	
	
	public static boolean GENERICTEMPLATE(GenericPatientProfileFB _fb,HttpServletRequest request, String patCrNo, String profileType,String deskMenuId, UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL DESKDIAGNOSIS Fun");
		boolean flag = false;
		try
		{
			HttpSession session = request.getSession();
			// Fetching Data 
				// Record Dates
				PatientClinicalDetailVO patCliniDtlVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliniDtlVO, voDP);
				patCliniDtlVO.setDeskMenuId(deskMenuId);
	
				List<Entry> lstReportDates = GenericTemplateTileDATA.getReportDateList(_fb.getDeskType(), patCliniDtlVO, userVO);
				// If no Data Return false
				if (lstReportDates == null)
					return false;
				// Setting Record Dates
				LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
				for (Entry e : lstReportDates)
					mapRecordDates.put(e.getValue(), e.getLabel());

				// Map of RecordDate/Map of Temp Id/Map of Parameter Id/Para Value
				Map<String, Map<String, Map<String, String>>> mpChartClinicalData = null;
				mpChartClinicalData = GenericTemplateTileDATA.getPatientChartClinicalDataTempWiseforProfile(_fb.getDeskType(), profileGenerationMode, patCliniDtlVO, lstReportDates,
						userVO);
				
				if(_fb.getAdmissionNo()!=null)
				{
					lstReportDates = new ArrayList<Entry>();
					for(String key : mpChartClinicalData.keySet())
					if(mapRecordDates.containsKey(key))
					lstReportDates.add(new Entry(key, mapRecordDates.get(key)));
				}
				// Setting Template Dates
				WebUTIL.setAttributeInSession(request, OpdConfig.GENERIC_TEMP_TILE_ALL_VISIT_DATES, lstReportDates);

				// Setting Desk Menu Wise
				Map<String, Map<String, Map<String, Map<String, String>>>> mpDeskMenuChartClinicalData = (Map<String, Map<String, Map<String, Map<String, String>>>>) session.getAttribute(GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP);
				if(mpDeskMenuChartClinicalData==null) mpDeskMenuChartClinicalData = new HashMap<String, Map<String,Map<String,Map<String,String>>>>();
				mpDeskMenuChartClinicalData.put(deskMenuId, mpChartClinicalData);
				WebUTIL.setAttributeInSession(request, GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP, mpDeskMenuChartClinicalData);
				flag=true;
			//To SET Template Data
			if(flag)
			{
				proforma.setComplaintsProforma(deskMenuId);				
				WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	//Added by Vasu on 16.Apr.2019 to add profile footer
	public static boolean patientProfileFooter(GenericPatientProfileFB fb,HttpServletRequest request, String patCrNo, String profileType,String deskMenuId, UserVO userVO, ProfileProforma proforma, PatientDetailVO voDP, String profileGenerationMode) 
	{
		System.out.println("in GenericAutometicPatientProfileUTIL profileFooter Fun");
		boolean flag = false;
		
		String url ="";
		String remarks = "";
		String reviewOn = "", dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="";
		DisclaimerMstVO disclaimerVO = new DisclaimerMstVO();
		
		try
		{
			
			HttpSession session =request.getSession();
			System.out.println("getEpisodeCode :"+voDP.getEpisodeCode()+" getAdmissionNo :"+voDP.getPatAdmNo()+" getDeskType :"+fb.getDeskType()+" Episode Visit No :"+voDP.getEpisodeVisitNo());
			
			
			 profileType = profileType.split("#")[0];
			DisclaimerMstVO disclaimerMstVO = GenericPatientProfileDATA.fetchDisclaimerDetails(fb.getDepartmentUnitCode(), profileType, userVO);
			//Map mp = GenericPatientProfileDATA.getDischargeProfileEssentials(fb.getDepartmentUnitCode(), fb.getPatCrNo(), userVO);
			Map essentialMap = GenericPatientProfileDATA.getPatientProfileFooterEssentials(voDP,userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.PAT_PROFILE_DTL_DISCLAIMER_DTL_VO, disclaimerMstVO);
			
			List<EpisodeVO> episodeVO = (List<EpisodeVO>) essentialMap.get(OpdConfig.PATIENT_PROFILE_FOOTER_ESSENTIALS);
			
			EpisodeVO eVO = null;
			eVO = episodeVO.get(0); 
			
			dischargeAdvisedBy = eVO.getUnitConsultant();

			if (disclaimerMstVO.getDisclaimerDesc1() != null)
			{
				fb.setDisclaimerDesc1(disclaimerMstVO.getDisclaimerDesc1());
			}
			if (disclaimerMstVO.getDisclaimerDesc2() != null)
			{
				fb.setDisclaimerDesc2(disclaimerMstVO.getDisclaimerDesc2());
			}
			if (disclaimerMstVO.getDisclaimerDesc3() != null)
			{
				fb.setDisclaimerDesc3(disclaimerMstVO.getDisclaimerDesc3());
			}
			
			//WebUTIL.setMapInSession(mp, request);

		
			proforma.setProfileFooterDetailProforma(deskMenuId,remarks,reviewOn,dischargeType,"",dischargeAdvisedBy,"","","","",disclaimerMstVO,"","");
			WebUTIL.setAttributeInSession(request, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
