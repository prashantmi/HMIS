/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Vasu
*/


package ehr.questionnaire.presentation;


/*import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;

import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;*/



import java.util.List;
import java.util.Map;











import opd.bo.delegate.OpdPatientDelegate;
import ehr.complaints.business.EHRSection_ComplaintsBO;
import ehr.complaints.vo.EHRSection_ComplaintsVO;
import ehr.examination.business.EHRSection_ExaminationBO;
import emr.vo.PatientClinicalDocDetailVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.bo.GenericTemplateEssentialBO;
import hisglobal.utility.generictemplate.delegate.GenericTemplateEssentialDelegate;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class EHRSection_QuestionnaireDATA extends  ControllerDATA

{	
	public static List<Entry> getDeskMenuTemplateList(String _deskType, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		EHRSection_ComplaintsBO delegate=new EHRSection_ComplaintsBO();
		return delegate.getDeskMenuTemplateList(_deskType, _userDeskTempVO, _userVO);		
	}
	
	
	// Getting Patient Clinical Template Data
		public static Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, EHRSection_ComplaintsVO _patClinicalVO, UserVO _userVO)
		{
			EHRSection_ComplaintsBO delegate=new EHRSection_ComplaintsBO();
			return delegate.getPatientFinalClinicalData(_deskType, _patClinicalVO, _userVO);		
		}
		
		//** Get All Template Details   
		public static Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO)
		{
			EHRSection_ComplaintsBO delegate=new EHRSection_ComplaintsBO();
			return delegate.getAllTemplateDetails(_lstTemps, _userVO);
		}

		
		//** Get Report Date List 
		public static List<Entry> getReportDateList(String _deskType, EHRSection_ComplaintsVO _patCliDtlVO, UserVO _userVO)
		{
			EHRSection_ComplaintsBO delegate=new EHRSection_ComplaintsBO();
			return delegate.getReportDateList(_deskType, _patCliDtlVO, _userVO);
		}
		
		//** Getting Patient Chart Clinical Template Data Template Wise With Para Detial
		public static Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> getPatientChartClinicalDataTempWiseWithParaDtl(String _deskType, 
				EHRSection_ComplaintsVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
		{
			EHRSection_ComplaintsBO delegate=new EHRSection_ComplaintsBO();
			return delegate.getPatientChartClinicalDataTempWiseWithParaDtl(_deskType, _patClinicalVO, _lstReportDates, _lstTemps, _userVO);		
		}
		
		// Saving Patient Clinical Data 
				public static void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO)
				{
					EHRSection_ComplaintsBO delegate=new EHRSection_ComplaintsBO();
					delegate.savePatientClinicalDetail(_deskType, _lstPatCliDtl, _userVO);
				}


				//Added by Vasu on 07.June.2019
				public static Map<String, Object> getTemplateClinicalData(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO,String deskMenuId, String deskType, String deskId)
				{
					GenericTemplateEssentialBO templateEssentialBO  =  new GenericTemplateEssentialBO();
					return templateEssentialBO.getTemplateClinicalData(deskMenuId,deskType,deskId,selectedPatientVO,clinicalDocVO,userVO);
					
				}

		
	}
