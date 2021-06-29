/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.history.presentation;


/*import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;

import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;*/



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





















import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.bo.delegate.OpdPatientDelegate;
import ehr.examination.business.EHRSection_ExaminationBO;
import ehr.history.business.EHRSection_HistoryBO;
import ehr.history.vo.EHRSection_HistoryVO;
import emr.vo.PatientClinicalDocDetailVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.bo.GenericTemplateEssentialBO;
import hisglobal.utility.generictemplate.delegate.GenericTemplateEssentialDelegate;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class EHRSection_HistoryDATA extends  ControllerDATA

{	
	public static List<Entry> getDeskMenuTemplateList(String _deskType, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
		return delegate.getDeskMenuTemplateList(_deskType, _userDeskTempVO, _userVO);		
	}
	
	
	// Getting Patient Clinical Template Data
		public static Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, EHRSection_HistoryVO _patClinicalVO, UserVO _userVO)
		{
			EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
			return delegate.getPatientFinalClinicalData(_deskType, _patClinicalVO, _userVO);		
		}
		
		//** Get All Template Details   
		public static Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO)
		{
			EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
			return delegate.getAllTemplateDetails(_lstTemps, _userVO);
		}

		
		//** Get Report Date List 
		public static List<Entry> getReportDateList(String _deskType, EHRSection_HistoryVO _patCliDtlVO, UserVO _userVO)
		{
			EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
			return delegate.getReportDateList(_deskType, _patCliDtlVO, _userVO);
		}
		
		//** Getting Patient Chart Clinical Template Data Template Wise With Para Detial
		public static Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> getPatientChartClinicalDataTempWiseWithParaDtl(String _deskType, 
				EHRSection_HistoryVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
		{
			EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
			return delegate.getPatientChartClinicalDataTempWiseWithParaDtl(_deskType, _patClinicalVO, _lstReportDates, _lstTemps, _userVO);		
		}
		
		// Saving Patient Clinical Data 
		public static void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO)
		{
			EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
			delegate.savePatientClinicalDetail(_deskType, _lstPatCliDtl, _userVO);
		}


		//Added by Vasu on 14.May.2019
		public static void getTemplateDataForHistory(HttpServletRequest request,UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
		{
			
			Map mpTempParaValues = new HashMap<String, Map<String, String>>();
			
			
			HttpSession session = WebUTIL.getSession(request);
			/*List<EHRSection_HistoryVO> historyVo = new ArrayList<EHRSection_HistoryVO>();*/
			
			String deskMenuId = "2";//For History
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			String deskId = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID);
			/*EHRSection_HistoryBO delegate=new EHRSection_HistoryBO();
			
			historyVo = delegate.getTemplateDataForHistory(deskMenuId,deskType,deskId,selectedPatientVO,clinicalDocVO,userVO);*/
			
			GenericTemplateEssentialBO templateEssentialBO  =  new GenericTemplateEssentialBO();
			
			mpTempParaValues = templateEssentialBO.getTemplateClinicalData(deskMenuId,deskType,deskId,selectedPatientVO,clinicalDocVO,userVO);
		}


		public static Map<String, Object> getTemplateClinicalData(UserVO userVO,PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO, String deskMenuId,String deskType, String deskId)
		{
			GenericTemplateEssentialBO templateEssentialBO  =  new GenericTemplateEssentialBO();
			return templateEssentialBO.getTemplateClinicalData(deskMenuId,deskType,deskId,selectedPatientVO,clinicalDocVO,userVO);
		}


		
	}
