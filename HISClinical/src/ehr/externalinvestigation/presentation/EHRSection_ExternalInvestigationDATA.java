/**
##		Date					: 05-Aug-2019
##		Reason	(CR/PRS)		: External Investigation Section at SPD 
##		Created By				: Vasu
*/


package ehr.externalinvestigation.presentation;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import ehr.examination.business.EHRSection_ExaminationBO;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.externalinvestigation.business.EHRSection_ExternalInvestigationBO;
import ehr.externalinvestigation.vo.EHRSection_ExternalInvestigationVO;
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

public class EHRSection_ExternalInvestigationDATA extends  ControllerDATA

{	

		public static Map getParameterForExtInv(PatientDetailVO selectedPatientVO, UserVO userVO)
		{
			EHRSection_ExternalInvestigationBO  extInvBO=new EHRSection_ExternalInvestigationBO();
			return extInvBO.getParameterForExtInv(selectedPatientVO,userVO);
			
		}

		public static void saveExtInvestigationDetail(List<EHRSection_ExternalInvestigationVO> lstExtInvDtl,PatientDetailVO selectedPatientVO, UserVO userVO)
		{
			EHRSection_ExternalInvestigationBO  extInvBO=new EHRSection_ExternalInvestigationBO();
			 extInvBO.saveExtInvestigationDetail(lstExtInvDtl,selectedPatientVO,userVO);
			
		}

		public static void deletePreviousExtInvestigations(EHRSection_ExternalInvestigationVO eHRExtInvDetailsVO,UserVO userVO)
		{
			EHRSection_ExternalInvestigationBO  extInvBO=new EHRSection_ExternalInvestigationBO();
			extInvBO.deletePreviousExtInvestigations(eHRExtInvDetailsVO, userVO);
			
		}

		
	}
