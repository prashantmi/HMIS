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


package emr.datafetch.patientClinicalDocuments.persistence.dao;

import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

import emr.vo.PatientClinicalDocDetailVO;
import emr.vo.PatientClinicalDocTypeVO;

public interface PatientClinicalDocumentsDAOi
{
	public void getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO);

	public List getAdvisedByList(String string, UserVO userVO);
	
	public List<PatientClinicalDocDetailVO> getEpisodePatientDocuments(PatientClinicalDocDetailVO _patDocumentVO, UserVO _userVO);

	public List<PatientClinicalDocTypeVO> getClinicalDocumentTypes(String usablity,	String generationMode, UserVO _userVO);
	
	public List<PatientClinicalDocDetailVO> getClinicalSectionCompostionsList(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO);
	
	public String createDocumentId(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO);
	
	public boolean saveClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, String pmode, UserVO _userVO);
	
	public boolean updateOld(PatientClinicalDocDetailVO clinicalDocVO, String pmode, UserVO _userVO);

	public List<PatientClinicalDocDetailVO> fetchDocumentDetails(String pmode, PatientClinicalDocDetailVO _patientProfileDtlVO, UserVO _userVO);

	
	public void updateDocumentStatus(PatientClinicalDocDetailVO _patDocumentDtlVO, UserVO _userVO);
	
	//public void updateOld(PatientClinicalDocDetailVO _patProfileDtlVO, UserVO _userVO);

	public String create(PatientClinicalDocDetailVO _patProfileDtlVO, UserVO _userVO);
	
	public void delete(PatientClinicalDocDetailVO _patProfileDtlVO, UserVO _userVO);

   //Added by Vasu on 07.Dec.2018
	public List<PatientClinicalDocDetailVO> getClinicalSectionTemplatePrintList(PatientClinicalDocDetailVO clinicalDocVO, UserVO _userVO);

	
}