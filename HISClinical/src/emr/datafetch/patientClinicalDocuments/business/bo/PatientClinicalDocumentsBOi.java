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


package emr.datafetch.patientClinicalDocuments.business.bo;

import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import emr.vo.DocumentAccessPolicyVO;
import emr.vo.DocumentGroupDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;
//import hisglobal.vo.Apt_appointmentDtlVO;
//import hisglobal.vo.Apt_slotDtlVO;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import emr.vo.PatientClinicalDocDetailVO;


	public interface PatientClinicalDocumentsBOi {


	public void getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO);
		
	public List getAdvisedByList(String string, UserVO userVO);

/*	public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO,
			UserVO _userVO);*/
	
	public Map<String,Object> getPatientDocumentsEssentials(PatientClinicalDocDetailVO _patDocumentVO, String _deskType,UserVO _userVO);
	
	
	
	public List<PatientClinicalDocDetailVO> getClinicalSectionCompostionsList(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO); 
	
	public boolean saveClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO, HttpServletRequest _rq); 
	
	public boolean saveModifyClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO, HttpServletRequest _rq); 
	
	public void documentGeneration(PatientClinicalDocDetailVO _patDocumentDtlVO,DocumentGroupDtlVO[] documentGroupDtlVO,DocumentAccessPolicyVO documentAccessPolicy, UserVO _userVO);
	
	public List<PatientClinicalDocDetailVO> fetchDetailsForGenerate(String pmode,PatientClinicalDocDetailVO _patientProfileDtlVO,String _deskType, UserVO _userVO);
	
	public Map fetchClinicalDocument(PatientClinicalDocDetailVO _patientProfileDtlVO,String genderCode, UserVO _userVO);
	
    public void modifyClinicalDocument(List<PatientClinicalDocDetailVO> _lstPatProfileDtlVO, UserVO _userVO);
	
	
	public void removeClinicalDocument(List<PatientClinicalDocDetailVO> _lstProfileDtlVO, UserVO _userVO);
    
	public List<PatientClinicalDocDetailVO> getClinicalSectionTemplatePrintList(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO); 

	}
