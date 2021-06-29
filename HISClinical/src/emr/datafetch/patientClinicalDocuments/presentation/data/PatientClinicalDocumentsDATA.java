/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : OPD IPD
 ## Process/Database Object Name	    : PATIENT CLINICAL DOCUMENTS
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2017 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package emr.datafetch.patientClinicalDocuments.presentation.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import emr.vo.DocumentAccessPolicyVO;
import emr.vo.DocumentGroupDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.bo.delegate.OpdPatientDelegate;
import emr.datafetch.patientClinicalDocuments.business.bo.PatientClinicalDocumentsBO;
import emr.datafetch.patientClinicalDocuments.business.bo.PatientClinicalDocumentsBOi;
import emr.vo.PatientClinicalDocDetailVO;




public class PatientClinicalDocumentsDATA extends ControllerDATA {

	public static void getPatDetailOpp(PatientDetailVO ptaientDetailVO,
			UserVO userVO) {
		PatientClinicalDocumentsBOi delegate=new PatientClinicalDocumentsBO();
		delegate.getPatDetailOpp(ptaientDetailVO,userVO) ;  
	}

	public static List getAdvisedByList(String string, UserVO userVO) {
		// TODO Auto-generated method stub
	//	OpdOPPDelegate essentialDelegate = new OpdOPPDelegate();
		PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
		List AdvisedByList = new ArrayList();
		AdvisedByList = essentialDelegate.getAdvisedByList(string,userVO);
		return AdvisedByList;
	}
	
	
	
	
	// Getting Patient Episode Documents List
		public static Map<String,Object> getPatientDocumentsEssentials(PatientClinicalDocDetailVO _patDocumentVO, String _deskType,UserVO _userVO)
		{
			PatientClinicalDocumentsBOi delegate = new PatientClinicalDocumentsBO();
			return delegate.getPatientDocumentsEssentials(_patDocumentVO, _deskType, _userVO);
		}
		
		
		public static List<PatientClinicalDocDetailVO> getClinicalSectionCompostionsList(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO) {
			// TODO Auto-generated method stub
		//	OpdOPPDelegate essentialDelegate = new OpdOPPDelegate();
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			List<PatientClinicalDocDetailVO> lstclinicalSectionComp = new ArrayList<PatientClinicalDocDetailVO>();
			lstclinicalSectionComp = essentialDelegate.getClinicalSectionCompostionsList(clinicalDocVO,userVO);
			return lstclinicalSectionComp;
		}
		
		
		public static boolean saveClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO,HttpServletRequest _rq) {
			// TODO Auto-generated method stub
		//	OpdOPPDelegate essentialDelegate = new OpdOPPDelegate();
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			boolean flag = essentialDelegate.saveClinicalSectionCompositionsDetail(clinicalDocVO,userVO,_rq);
			return flag;
		}
		
				
		public static boolean saveModifyClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO,HttpServletRequest _rq) {
			// TODO Auto-generated method stub
		//	OpdOPPDelegate essentialDelegate = new OpdOPPDelegate();
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			boolean flag = essentialDelegate.saveModifyClinicalSectionCompositionsDetail(clinicalDocVO,userVO,_rq);
			return flag;
		}
		
		
		public static List<PatientClinicalDocDetailVO> fetchDetailsForGenerate(String pmode,PatientClinicalDocDetailVO _patientProfileDtlVO,String _deskType, UserVO _userVO)
		{
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			List<PatientClinicalDocDetailVO> lstclinicalSectionComp = new ArrayList<PatientClinicalDocDetailVO>();
			lstclinicalSectionComp = essentialDelegate.fetchDetailsForGenerate(pmode,_patientProfileDtlVO,_deskType, _userVO);
			return lstclinicalSectionComp ;
		}
		
		
		public static void documentGeneration(PatientClinicalDocDetailVO _patDocumentDtlVO,DocumentGroupDtlVO[] documentGroupDtlVO,DocumentAccessPolicyVO documentAccessPolicy, UserVO _userVO)
		{
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			essentialDelegate.documentGeneration(_patDocumentDtlVO,documentGroupDtlVO,documentAccessPolicy,_userVO);
		}
		
		// Modifying Patient Profile
		
		public static Map fetchClinicalDocument(PatientClinicalDocDetailVO _patientProfileDtlVO,String genderCode,UserVO _userVO)
		{
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			return essentialDelegate.fetchClinicalDocument(_patientProfileDtlVO,genderCode, _userVO);
		}
		
		
		public static void modifyClinicalDocument(List<PatientClinicalDocDetailVO> _lstPatProfileDtlVO, UserVO _userVO)
		{
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			essentialDelegate.modifyClinicalDocument(_lstPatProfileDtlVO, _userVO);
		}
		
		// Removing Patient Profile
		public static void removeClinicalDocument(List<PatientClinicalDocDetailVO> _lstProfileDtlVO, UserVO _userVO)
		{
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			essentialDelegate.removeClinicalDocument(_lstProfileDtlVO, _userVO);
		}
		
		//Added by Vasu on 27.Nov.2018
		public static List<EpisodeVO> getEpisodeVisitDetails(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO) {
			PatientClinicalDocumentsBO essentialBO=new PatientClinicalDocumentsBO();
			List<EpisodeVO> lstEpisodeVO= new ArrayList<EpisodeVO>();
			lstEpisodeVO=essentialBO.getEpisodeVisitDetails(clinicalDocVO,userVO);
			return lstEpisodeVO;
		}
		
		//Added by Vasu on 07.Dec.2018
		public static List<PatientClinicalDocDetailVO> getClinicalSectionTemplatePrintList(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO) {
			
			PatientClinicalDocumentsBOi essentialDelegate=new PatientClinicalDocumentsBO();
			List<PatientClinicalDocDetailVO> lstClinicalSectionTemplatePrint = new ArrayList<PatientClinicalDocDetailVO>();
			lstClinicalSectionTemplatePrint = essentialDelegate.getClinicalSectionTemplatePrintList(clinicalDocVO,userVO);
			return lstClinicalSectionTemplatePrint;
		}

	}
