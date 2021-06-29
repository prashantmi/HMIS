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

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileAlertsDtlVO;
import hisglobal.vo.ProfileAllergyDtlVO;
import hisglobal.vo.ProfileClinicalDtlVO;
import hisglobal.vo.ProfileDiagnosisDtlVO;
import hisglobal.vo.ProfileDietAdviceDtlVO;
import hisglobal.vo.ProfileDrugAdviceDtlVO;
import hisglobal.vo.ProfileExtExamDtlVO;
import hisglobal.vo.ProfileExtTreatmentDtlVO;
import hisglobal.vo.ProfileFooterDtlVO;
import hisglobal.vo.ProfileImageDtlVO;
import hisglobal.vo.ProfileInvDtlVO;
import hisglobal.vo.ProfileOTDtlVO;
import hisglobal.vo.ProfileProgressNotesDtlVO;
import hisglobal.vo.ProfileRestAdviceDtlVO;
import emr.vo.DocumentAccessDetailVO;
import emr.vo.DocumentAccessPolicyVO;
import emr.vo.DocumentGroupDtlVO;
import ehr.EHRConfig;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;
import emr.vo.PatientClinicalDocTypeVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import emr.datafetch.patientClinicalDocuments.persistence.dao.PatientClinicalDocumentsDAO;
import emr.datafetch.patientClinicalDocuments.persistence.dao.PatientClinicalDocumentsDAOi;
import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.ProfileAccessDetailDAO;
import opd.dao.ProfileAccessDetailDAOi;
import opd.dao.ProfileAlertsDtlDAO;
import opd.dao.ProfileAlertsDtlDAOi;
import opd.dao.ProfileAllergyDtlDAO;
import opd.dao.ProfileAllergyDtlDAOi;
import opd.dao.ProfileClinicalDtlDAO;
import opd.dao.ProfileClinicalDtlDAOi;
import opd.dao.ProfileDiagnosisDtlDAO;
import opd.dao.ProfileDiagnosisDtlDAOi;
import opd.dao.ProfileDietAdviceDtlDAO;
import opd.dao.ProfileDietAdviceDtlDAOi;
import opd.dao.ProfileDrugAdviceDtlDAO;
import opd.dao.ProfileDrugAdviceDtlDAOi;
import opd.dao.ProfileDrugScheduleDtlDAO;
import opd.dao.ProfileDrugScheduleDtlDAOi;
import opd.dao.ProfileExtExamDtlDAO;
import opd.dao.ProfileExtTreatmentDtlDAO;
import opd.dao.ProfileExtTreatmentDtlDAOi;
import opd.dao.ProfileFooterDtlDAO;
import opd.dao.ProfileFooterDtlDAOi;
import opd.dao.ProfileImageDtlDAO;
import opd.dao.ProfileImageDtlDAOi;
import opd.dao.ProfileInvDtlDAO;
import opd.dao.ProfileInvDtlDAOi;
import opd.dao.ProfileOTDtlDAO;
import opd.dao.ProfileOTDtlDAOi;
import opd.dao.ProfileProgressNotesDtlDAO;
import opd.dao.ProfileRestAdviceDtlDAO;
import opd.dao.ProfileRestAdviceDtlDAOi;
import opd.dao.ProfileTypeDAO;
import registration.dao.EpisodeDAO;




public class PatientClinicalDocumentsBO implements PatientClinicalDocumentsBOi
{
	public void getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		boolean check=false;	
		try
		{
			tx.begin();
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			
			OpdOPPDAO.getPatDetailOpp(ptaientDetailVO,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
	public List getAdvisedByList(String string, UserVO userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List AdvisedByList=new ArrayList();
		try
		{
			tx.begin();
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			AdvisedByList = OpdOPPDAO.getAdvisedByList(string, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return AdvisedByList;
	}
	
	
	
	
	public Map<String, Object> getPatientDocumentsEssentials(PatientClinicalDocDetailVO _patDocumentVO, String _deskType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientClinicalDocTypeVO> documentTypeVOList = null;
		List<PatientClinicalDocDetailVO> lstPatDocuments  = null;
		String documentBound = "";
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			
			
			PatientClinicalDocumentsDAOi dao = new PatientClinicalDocumentsDAO(tx);
			OpdEssentialDAOi essentialDAOi = new OpdEssentialDAO(tx);
			ProfileTypeDAO documentTypeDao = new ProfileTypeDAO(tx);
			tx.begin();
			lstPatDocuments = dao.getEpisodePatientDocuments(_patDocumentVO, _userVO);
			documentBound = essentialDAOi.getProfileBound(_deskType, _userVO);
			map.put(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST, lstPatDocuments);
			map.put(EHRConfig.OPD_DESK_DOCUMENT_BOUND, documentBound);
			String usablity = EHRConfig.DOCUMENT_TYPE_USABLITY_OPD;
			
			if (_deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) usablity = EHRConfig.DOCUMENT_TYPE_USABLITY_IPD;
			documentTypeVOList =  dao.getClinicalDocumentTypes(usablity, EHRConfig.DOCUMENT_TYPE_GENERATION_MODE_CUSTOMIZED, _userVO);
			map.put(EHRConfig.DOCUMENT_TYPE_VO_LIST, documentTypeVOList);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return map;
	}
	
	
	
	
	
	
	public List<PatientClinicalDocDetailVO> getClinicalSectionCompostionsList(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientClinicalDocDetailVO> lstClinicalSectionCompostions=new ArrayList<PatientClinicalDocDetailVO>();
		
		try
		{
			tx.begin();
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			lstClinicalSectionCompostions = OpdOPPDAO.getClinicalSectionCompostionsList(clinicalDocVO, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstClinicalSectionCompostions;
	}
	
	
	
	public boolean saveClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO,HttpServletRequest _rq)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=true; 
		String pmode="1", key="";
		
		
		try
		{
			tx.begin();
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			
			String documentId= OpdOPPDAO.createDocumentId(clinicalDocVO, userVO);
			
			clinicalDocVO.setDocumentId(documentId);
			// clinical doc dtl   
		    pmode="1";
		     flag = OpdOPPDAO.saveClinicalSectionCompositionsDetail(clinicalDocVO,pmode, userVO);
			
			// clinical sec comp dtl
						
			if((List)_rq.getSession().getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST)!=null)
			{
			
				
			List lstMenus = (List)_rq.getSession().getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
			if(lstMenus!=null)
			{
				String prevClinicalSectionCode="", newClinicalSectionCode="";
				for(int i=0;i<lstMenus.size();i++)
				{
					
					
					PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenus.get(i);
					//HelperMethods.populate(clinicalDocVO,vo);
					 clinicalDocVO.setClinicalSectionCode(vo.getClinicalSectionCode());
					 clinicalDocVO.setClinicalSectionName(vo.getClinicalSectionName());
					 clinicalDocVO.setClinicalSectionOrder(vo.getClinicalSectionOrder());
					 clinicalDocVO.setClinicalSecCompCode(vo.getClinicalSecCompCode());
					 clinicalDocVO.setClinicalSecCompOrder(vo.getClinicalSecCompOrder());
					
					 newClinicalSectionCode= vo.getClinicalSectionCode();
					
					key=vo.getClinicalSecCompKey();
					String jsonSelectist="";
					String jsonLine=clinicalDocVO.getClinicalDocCompSelectJSON();
					JsonElement jelement = new JsonParser().parse(jsonLine);
				    JsonObject  jobject = jelement.getAsJsonObject();
				    JsonObject jobj = jobject.getAsJsonObject("patCompositionSelectData");
				    if(jobj.getAsJsonObject(key)!=null)
				    {
				    jobj = jobj.getAsJsonObject(key);
				    //JsonObject  jhtmlobject = jobj;
				    if(jobj.getAsJsonArray("list_selected")!=null)
				    {
				    JsonArray jarray  = jobj.getAsJsonArray("list_selected");
				    if(jarray.size()>0)
				    {	
				    for(int j=0;j<jarray.size();j++)
				    {	
				    jobj = jarray.get(j).getAsJsonObject();
				    if(j==0)
				    jsonSelectist=jobj.toString();
				    else
				   	jsonSelectist=jsonSelectist+","+jobj.toString();
				    }
				    clinicalDocVO.setClinicalCompSelectJSON(jsonSelectist);
				    
				    jobject = jelement.getAsJsonObject();
				    jobj = jobject.getAsJsonObject("patCompositionSelectData");
				    jobj = jobj.getAsJsonObject(key);
				    JsonPrimitive  jhtmlobject = jobj.getAsJsonPrimitive("html");
				  
				    
				    clinicalDocVO.setClinicalDocCompHTMLJSON(jhtmlobject.toString());
				 // for ignoring duplicate inserts of same clinical sections.  (A clinical section can have multiple composition)
				    if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) 
				    {
				       pmode="2"; // clinical section
				       flag = OpdOPPDAO.saveClinicalSectionCompositionsDetail(clinicalDocVO, pmode, userVO);
				    }
				    
				    pmode="3";  //clinical comp
				    flag = OpdOPPDAO.saveClinicalSectionCompositionsDetail(clinicalDocVO, pmode, userVO);
				    
				    }
				    }	  
				 }
				    prevClinicalSectionCode=newClinicalSectionCode;
				}  
				
			}
			}
			
			
		    
		    
		    
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}
	
	
	
	
	public boolean saveModifyClinicalSectionCompositionsDetail(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO,HttpServletRequest _rq)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag=true; 
		String pmode="1", key="";
		
		
		try
		{
			tx.begin();
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			
			//String documentId= OpdOPPDAO.createDocumentId(clinicalDocVO, userVO);
			
			//clinicalDocVO.setDocumentId(documentId);
			// clinical doc dtl   
			
			
			
		    pmode="1";
		     flag = OpdOPPDAO.updateOld(clinicalDocVO, pmode,userVO);
		  // clinical doc dtl   
			  flag = OpdOPPDAO.saveClinicalSectionCompositionsDetail(clinicalDocVO,pmode, userVO);
				
			
			// clinical sec comp dtl
						
			if((List)_rq.getSession().getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST)!=null)
			{
			
				
			List lstMenus = (List)_rq.getSession().getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
			if(lstMenus!=null)
			{
				String prevClinicalSectionCode="", newClinicalSectionCode="";
				for(int i=0;i<lstMenus.size();i++)
				{
					
					
					PatientClinicalDocDetailVO vo  = (PatientClinicalDocDetailVO)lstMenus.get(i);
					//HelperMethods.populate(clinicalDocVO,vo);
					 clinicalDocVO.setClinicalSectionCode(vo.getClinicalSectionCode());
					 clinicalDocVO.setClinicalSectionName(vo.getClinicalSectionName());
					 clinicalDocVO.setClinicalSectionOrder(vo.getClinicalSectionOrder());
					 clinicalDocVO.setClinicalSecCompCode(vo.getClinicalSecCompCode());
					 clinicalDocVO.setClinicalSecCompOrder(vo.getClinicalSecCompOrder());
					
					 newClinicalSectionCode= vo.getClinicalSectionCode();
					
					key=vo.getClinicalSecCompKey();
					String jsonSelectist="";
					String jsonLine=clinicalDocVO.getClinicalDocCompSelectJSON();
					JsonElement jelement = new JsonParser().parse(jsonLine);
				    JsonObject  jobject = jelement.getAsJsonObject();
				    JsonObject jobj = jobject.getAsJsonObject("patCompositionSelectData");
				    if(jobj.getAsJsonObject(key)!=null)
				    {
				    jobj = jobj.getAsJsonObject(key);
				    //JsonObject  jhtmlobject = jobj;
				    if(jobj.getAsJsonArray("list_selected")!=null)
				    {
				    JsonArray jarray  = jobj.getAsJsonArray("list_selected");
				    if(jarray.size()>0)
				    {	
				    for(int j=0;j<jarray.size();j++)
				    {	
				    jobj = jarray.get(j).getAsJsonObject();
				    if(j==0)
				    jsonSelectist=jobj.toString();
				    else
				   	jsonSelectist=jsonSelectist+","+jobj.toString();
				    }
				    clinicalDocVO.setClinicalCompSelectJSON(jsonSelectist);
				    
				    jobject = jelement.getAsJsonObject();
				    jobj = jobject.getAsJsonObject("patCompositionSelectData");
				    jobj = jobj.getAsJsonObject(key);
				    JsonPrimitive  jhtmlobject = jobj.getAsJsonPrimitive("html");
				  
				    
				    clinicalDocVO.setClinicalDocCompHTMLJSON(jhtmlobject.toString());
				 // for ignoring duplicate inserts of same clinical sections.  (A clinical section can have multiple composition)
				    if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) 
				    {
				       pmode="2"; // clinical section
				       flag = OpdOPPDAO.saveClinicalSectionCompositionsDetail(clinicalDocVO, pmode, userVO);
				    }
				    
				    pmode="3";  //clinical comp
				    flag = OpdOPPDAO.saveClinicalSectionCompositionsDetail(clinicalDocVO, pmode, userVO);
				    
				    }
				    }	  
				 }
				    prevClinicalSectionCode=newClinicalSectionCode;
				}  
				
			}
			}
			
			
		    
		    
		    
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;
	}
	
	
	
	
	public List<PatientClinicalDocDetailVO> fetchDetailsForGenerate(String pmode, PatientClinicalDocDetailVO _patientProfileDtlVO,String _deskType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientClinicalDocDetailVO> docDtlVO = null;
		
		try
		{
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			//OpdEssentialDAOi essentialDAO = new OpdEssentialDAO(tx);
			//ProfileDiagnosisDtlDAOi profileDiagnosisDtlDao = new ProfileDiagnosisDtlDAO(tx);
			tx.begin();
			//ProfileAccessPolicyVO profileAccessPolicy = new ProfileAccessPolicyVO();
			//ProfileGroupDtlVO[] profileGroupDtlVO = null;

			/*String count = dao.fetchProfileRestrictedCapCount(_patientProfileDtlVO, _userVO);

			if (count.equals("1"))
			{
				_patientProfileDtlVO.setPolicyType(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED);
			}
			else
			{
				_patientProfileDtlVO.setPolicyType(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL);
			}

			profileAccessPolicy = dao.fetchAccessTypeProfileAccessPolicy(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_DETAIL_VO, profileAccessPolicy);

			if (profileAccessPolicy.getAccessType() != null)
			{
				if (!profileAccessPolicy.getAccessType().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL))
				{
					profileGroupDtlVO = dao.fetchGroupIdProfileGroupDtl(profileAccessPolicy, _userVO);
					map.put(OpdConfig.PATIENT_PROFILE_ACCESS_POLICY_VO_ARRAY, profileGroupDtlVO);
				}
			}*/

			docDtlVO = OpdOPPDAO.fetchDocumentDetails(pmode,_patientProfileDtlVO, _userVO);
			

			
			// map.put(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstPatProfiles);
			// map.put(OpdConfig.OPD_DESK_PROFILE_BOUND, profileBound);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return docDtlVO;
	}
	
	
	/**
	 * generate the document, the document is created on the disk, the document dtl table is updated with the document status
	 * flag as generated, The default access policy is saved if found in the previous method
	 */
	public void documentGeneration(PatientClinicalDocDetailVO _patDocumentDtlVO, DocumentGroupDtlVO[] documentGroupDtlVO,
			DocumentAccessPolicyVO documentAccessPolicy, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String fileName = "";
		try
		{
			
			PatientClinicalDocumentsDAOi dao=new PatientClinicalDocumentsDAO(tx);
		//	DocumentAccessDetailDAOi documentAccessDetailDAO = new DocumentAccessDetailDAO(tx);
			DocumentAccessDetailVO documentAccessDetailVO = new DocumentAccessDetailVO();

			tx.begin();

			String documentId = _patDocumentDtlVO.getDocumentId();

			fileName = documentId + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
					
			//HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, Config.PATIENT_DOCUMENT_STORAGE_PATH_WINDOWS,
					//Config.PATIENT_DOCUMENT_STORAGE_PATH_LINUX);
			//fileUtil.setFileContent(_patDocumentDtlVO.getDocumentData().getBytes());
			//fileUtil.saveFile();
			
	
            //Calling MongoDb from Here					
			MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_GENERATED_PATIENT_PROFILE_UPLOAD).savePDFFile(fileName,_patDocumentDtlVO.getDocumentData().getBytes());
			
			dao.updateDocumentStatus(_patDocumentDtlVO, _userVO);
			
			/*documentAccessDetailDAO.delete(_patDocumentDtlVO.getDocumentId(), _userVO);

			if (documentAccessPolicy.getAccessType() != null)
			{
				if (documentAccessPolicy.getAccessType().equals(EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_TO_ALL))
				{
					_patDocumentDtlVO.setAccessType(documentAccessPolicy.getAccessType());
					_patDocumentDtlVO.setUserLevel(documentAccessPolicy.getUserLevel());
					dao.updateDocumentStatus(_patDocumentDtlVO, _userVO);
				}
				else
				{

					if (documentGroupDtlVO != null)
					{
						for (int i = 0; i < documentGroupDtlVO.length; i++)
						{
							if (documentGroupDtlVO[i].getRecordMode().equals(EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_UNIT_SPECIFIC))
							{
								documentAccessDetailVO.setDepartmentUnitCode(documentGroupDtlVO[i].getAccessRecordId());
								documentAccessDetailVO.setUserId("");
							}
							else if (documentGroupDtlVO[i].getRecordMode().equals(EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_USER_BOUND))
							{
								documentAccessDetailVO.setUserId(documentGroupDtlVO[i].getAccessRecordId());
								documentAccessDetailVO.setDepartmentUnitCode("");
							}
							documentAccessDetailVO.setAccessType(documentGroupDtlVO[i].getRecordMode());
							documentAccessDetailVO.setDocumentId(_patDocumentDtlVO.getDocumentId());
							documentAccessDetailDAO.create(documentAccessDetailVO, _userVO);

							_patDocumentDtlVO.setAccessType(documentAccessPolicy.getAccessType());
							_patDocumentDtlVO.setUserLevel(documentAccessPolicy.getUserLevel());

							dao.updateDocumentStatus(_patDocumentDtlVO, _userVO);

						}
					}
				}
			}*/

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}
	
	

	
	/**
	 * Modifying Patient Profile
	 * 
	 * @param _lstPatProfileDtlVO List of Patient Profile Detail
	 * @param _userVO User Detail
	 */
	public void modifyClinicalDocument(List<PatientClinicalDocDetailVO> _lstPatProfileDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			PatientClinicalDocumentsDAOi daoPatProfile = new PatientClinicalDocumentsDAO(tx);
			ProfileAccessDetailDAOi daoProfileAccess = new ProfileAccessDetailDAO(tx);

			tx.begin();
			for (PatientClinicalDocDetailVO patProfileDtlVO : _lstPatProfileDtlVO)
			{
				daoPatProfile.updateOld(patProfileDtlVO, _userVO);
				daoPatProfile.create(patProfileDtlVO, _userVO);

				daoProfileAccess.delete(patProfileDtlVO.getDocumentId(), _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	/**
	 * Removing Patient Profile
	 * 
	 * @param _lstProfileDtlVO List of Patient Profile Detail
	 * @param _userVO User Detail
	 */
	public void removeClinicalDocument(List<PatientClinicalDocDetailVO> _lstProfileDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			PatientClinicalDocumentsDAOi dao = new PatientClinicalDocumentsDAO(tx);
			ProfileAccessDetailDAOi daoProfileAccess = new ProfileAccessDetailDAO(tx);

			tx.begin();

			for (PatientClinicalDocDetailVO patProfileDtlVO : _lstProfileDtlVO)
			{
				dao.updateOld(patProfileDtlVO, "1",_userVO);

				//daoProfileAccess.delete(patProfileDtlVO.getDocumentId(), _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}

	}
	
	
	
	public Map fetchClinicalDocument(PatientClinicalDocDetailVO _patientProfileDtlVO, String genderType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		// List<PatientClinicalDocDetailVO> lstPatProfiles = null;
		// String profileBound="";
		List<Entry> lstDietType = null;
		List<Entry> lstDrugRoutes = null;
		// DrugFrequencyMstVO[] drugFrequencyMstVO;
		ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO = null;
				Map map = new HashMap();
		try
		{
			// PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			OpdEssentialDAOi essentialDAO = new OpdEssentialDAO(tx);
			PatientClinicalDocumentsDAOi profileDiagnosisDtlDao = new PatientClinicalDocumentsDAO(tx);
				tx.begin();
			// ProfileAccessPolicyVO profileAccessPolicy=new ProfileAccessPolicyVO();
			// ProfileGroupDtlVO[] profileGroupDtlVO=null;

			profileDiagnosisDtlVO = profileDiagnosisDtlDao.fetchDiagProfileDetails(_patientProfileDtlVO, _userVO);
			map.put(OpdConfig.PATIENT_PROFILE_DIAGNOSIS_DTL_VO_ARRAY, profileDiagnosisDtlVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return map;
	}
	
	//Added by Vasu on 27.Nov.2018
	public List<EpisodeVO> getEpisodeVisitDetails(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO episodeVO = new EpisodeVO();
		episodeVO.setPatCrNo(clinicalDocVO.getPatCrNo());
		episodeVO.setEpisodeCode(clinicalDocVO.getEpisodeCode());
		List<EpisodeVO> lstEpisodeDtl = new ArrayList<EpisodeVO>();
		
		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			lstEpisodeDtl = objEpisodeDAO.retrieveAllEpisodeVisits(episodeVO, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstEpisodeDtl;
	}
	
	//Added by Vasu on 07.Dec.2018
	public List<PatientClinicalDocDetailVO> getClinicalSectionTemplatePrintList(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientClinicalDocDetailVO> lstClinicalSectionTemplatePrint=new ArrayList<PatientClinicalDocDetailVO>();
		
		try
		{
			tx.begin();
			PatientClinicalDocumentsDAOi OpdOPPDAO=new PatientClinicalDocumentsDAO(tx);
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			lstClinicalSectionTemplatePrint = OpdOPPDAO.getClinicalSectionTemplatePrintList(clinicalDocVO, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstClinicalSectionTemplatePrint;
	}
	
}
	