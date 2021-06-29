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


package emr.datafetch.patientClinicalDocuments.presentation.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import emr.vo.DocumentAccessPolicyVO;
import emr.vo.DocumentGroupDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;






import com.google.gson.Gson;
import com.google.gson.JsonElement;

import vo.registration.PatientVO;
import ehr.EHRConfig;
import ehr.diagnosis.presentation.EHRSection_DiagnosisLNK;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentDATA;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentFB;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentUTL;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import emr.datafetch.patientClinicalDocuments.presentation.fb.PatientClinicalDocumentsFB;
import emr.datafetch.patientClinicalDocuments.presentation.data.PatientClinicalDocumentsDATA;
import emr.vo.EHR_PatEncounterDetailsVO;
import emr.vo.PatientClinicalDocDetailVO;
import emr.vo.PatientClinicalDocTypeVO;
import emr.vo.PatientClinicalDocumentsVO;


public class PatientClinicalDocumentsUTL extends ControllerUTIL {

	public static void getPatDetailOpp(HttpServletRequest request, HttpServletResponse response, PatientClinicalDocumentsFB fb) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		String flag1="1";
		PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		String crno = ptaientDetailVO.getPatCrNo();
		try{
		PatientClinicalDocumentsDATA.getPatDetailOpp(ptaientDetailVO,userVO);
		fb.setVisitReason(ptaientDetailVO.getVisitReason());
		fb.setDeptUnit(ptaientDetailVO.getDepartmentUnitName());
		System.out.println(crno);
		PatientClinicalDocumentsUTL.getAdvisedByList(request,response);
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();

		}
	}
	public static void getAdvisedByList(HttpServletRequest _request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserVO userVO = getUserVO(_request);
		setSysdate(_request);
		List advisedByList = new ArrayList();
		advisedByList=PatientClinicalDocumentsDATA.getAdvisedByList("123", userVO);
		WebUTIL.setAttributeInSession(_request, OpdConfig.ADVISED_BY_LIST,advisedByList);
		
	}
	
	
	
	public static void setEssentials(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
		Map<String, Object> essentialMap = null;
		String documentBound = "";
		try
		{
			PatientDetailVO voPD = new PatientDetailVO();
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			setSysdate(_rq);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
			ControllerUTIL.getHospitalVO(_rq);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);			
			if(ptaientDetailVO == null)
			{
				PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);				
				for (int i = 0; i < al.length; i++)
				{
					voPD = (PatientDetailVO) al[i];
					if (voPD.getPatCrNo().equals(_fb.getPatCrNo())) break;
				}
			}
			voPD = ptaientDetailVO;
			if(voPD.getEpisodeCode()!=null)
			_fb.setEpisodeCode(voPD.getEpisodeCode());
			if(voPD.getEpisodeVisitNo()!=null)
			_fb.setEpisodeVisitNo(voPD.getEpisodeVisitNo());
			if(voPD.getPatAdmNo()!=null)
			_fb.setAdmissionNo(voPD.getPatAdmNo());
			if(voPD.getPatPrimaryCatCode()!=null)
			_fb.setPatCategoryCode(voPD.getPatPrimaryCatCode());
			if(voPD.getDepartmentUnitCode()!=null)
			_fb.setDepartmentUnitCode(voPD.getDepartmentUnitCode());
			if(voPD.getPatCrNo()!=null)
			_fb.setPatCrNo(voPD.getPatCrNo());
			// Desk Type, Desk Id
			_fb.setDeskType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE));
			_fb.setDeskId((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID));

			// Getting Episode Previous Patient Documents
			PatientClinicalDocDetailVO voPatDocument = new PatientClinicalDocDetailVO();
			HelperMethods.populate(voPatDocument, _fb);

			essentialMap = PatientClinicalDocumentsDATA.getPatientDocumentsEssentials(voPatDocument, _fb.getDeskType(), userVO);
			lstPrevDocuments = (List) essentialMap.get(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST);

			documentBound = (String) essentialMap.get(EHRConfig.OPD_DESK_DOCUMENT_BOUND);
			List<Entry> listDocumentType = new ArrayList<Entry>();
			List<PatientClinicalDocTypeVO> documentTypeVOList = (List) essentialMap.get(EHRConfig.DOCUMENT_TYPE_VO_LIST);

			PatientClinicalDocTypeVO documentTypeVO = null;
			if ((documentBound != null) && documentTypeVOList != null && documentTypeVOList.size() > 0)
			{
				for (int i = 0; i < documentTypeVOList.size(); i++)
				{
					documentTypeVO = new PatientClinicalDocTypeVO();
					documentTypeVO = documentTypeVOList.get(i);
					Entry entry = new Entry();
					entry.setLabel(documentTypeVO.getDocumentName());
					entry.setValue(documentTypeVO.getDocumentType() + "#" + documentTypeVO.getIsUnique() + "#" + ((documentTypeVO.getDefaultName()==null)?"":documentTypeVO.getDefaultName()) + "#" + documentTypeVO.getDocumentGenerationMode());
					listDocumentType.add(entry);
					//_fb.setDocumentGenerationMode(documentTypeVO.getDocumentGenerationMode());
				}
				if (documentTypeVOList.size() == 1)
				{
					_fb.setDocumentType(documentTypeVOList.get(0).getDocumentType() + "#" + documentTypeVOList.get(0).getIsUnique() + "#"
							+ documentTypeVOList.get(0).getDefaultName() + "#" + documentTypeVO.getDocumentGenerationMode());
					_fb.setDocumentHeader(documentTypeVOList.get(0).getDefaultName());
				}
			}

			Iterator itr = lstPrevDocuments.iterator();
			while (itr.hasNext())
			{
				PatientClinicalDocDetailVO patientDocumentDtlVO = (PatientClinicalDocDetailVO) itr.next();
				for (int i = 0; i < documentTypeVOList.size(); i++)
				{
					if (documentTypeVOList.get(i).getDocumentType().equals(patientDocumentDtlVO.getDocumentType()))
					{
						patientDocumentDtlVO.setDocumentDesc(documentTypeVOList.get(i).getDocumentName());
					}
				}

				
			}

			WebUTIL.setAttributeInSession(_rq, EHRConfig.DOCUMENT_TYPE_LIST, listDocumentType);
			WebUTIL.setAttributeInSession(_rq, EHRConfig.DOCUMENT_TYPE_VO_LIST, documentTypeVOList);
			WebUTIL.setAttributeInSession(_rq, EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST, lstPrevDocuments);

			_fb.setDocumentGenerationType(EHRConfig.DOCUMENT_TYPE_GENERATION_MODE_CUSTOMIZED);

			if (lstPrevDocuments.size() > 0) objStatus.add(Status.LIST);
			objStatus.add(Status.NEW);
			if (listDocumentType.size() == 0)
			{

				throw new HisRecordNotFoundException("No Document Type Found");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		
		
	}
	
	
	
	
	public static void getClinicalSectionCompositions(HttpServletRequest request, HttpServletResponse response, PatientClinicalDocumentsFB fb) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		String crno = ptaientDetailVO.getPatCrNo();
		PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();
	
		try
		{
	    HelperMethods.populate(clinicalDocVO,ptaientDetailVO);
	    clinicalDocVO.setDocumentType(fb.getDocumentType());
	    clinicalDocVO.setDocumentTitle(fb.getDocumentTitle());
	    
	    List<PatientClinicalDocDetailVO> lstClinicalDocDetailVO= new ArrayList<PatientClinicalDocDetailVO>();
	    lstClinicalDocDetailVO=PatientClinicalDocumentsDATA.getClinicalSectionCompostionsList(clinicalDocVO,userVO);
	     
	   /* Gson gson = new Gson();
	    String json=gson.toJson(lstClinicalDocDetailVO);
	    fb.setClinicalSectionCompJSON(json);
		*/
		WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_SECTION_COMP_LIST, lstClinicalDocDetailVO);
		
		WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS, clinicalDocVO);
		
		
		
		//createJSONObject
		//String jsonSelect =  {"patCrNo":"","patDocId":"","patEncounterDtl":{},"patCompositionSelectData":{"ADVICEONDISCHARGE": {"list_selected":[], "html":{}} ,"TREATMENT":{"list_selected":[],"html":{}},"ENCDIAGNOSIS":{"list_selected":[],"html":{}},
	                          //"ENCINVESTIGATION":{"list_selected":[]}}};
		
		
		
		
	    }
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	
	
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, PatientClinicalDocumentsFB _fb) 
	{
		String flag=PatientClinicalDocumentsUTL.saveEHRPatClinicalDocumentDetails(_rq,response,_fb);
		try
		{
			writeResponse(response, flag);
		}
		catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();
			
		}
	}
	
	
	// Saving Treatment Detail 
	public static String saveEHRPatClinicalDocumentDetails(HttpServletRequest _rq,HttpServletResponse response, PatientClinicalDocumentsFB _fb) 
	{
		String isSave = "true";
		Status objStatus = new Status();
		PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();
		PatientClinicalDocDetailVO clinicalDocDetailVO= new PatientClinicalDocDetailVO();
	
	
		
		try
		{
			//HttpSession session = _rq.getSession();
			//PatientClinicalDocDetailVO ptaientDocClinicaDetailVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_SECTION_COMP_LIST);
		
			
			if(WebUTIL.getSession(_rq).getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS)!=null)
			{	
			clinicalDocVO = (PatientClinicalDocDetailVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
			HelperMethods.populatetToNullOrEmpty(clinicalDocDetailVO,clinicalDocVO);
			}
			
			
			HelperMethods.populatetToNullOrEmpty(clinicalDocDetailVO,_fb); 
			clinicalDocDetailVO.setClinicalDocCompSelectJSON(_fb.getClinicalDocCompSelectJSON());
			
			UserVO userVO = getUserVO(_rq);    
			if(_fb.getClinicalDocumentMode().equals("MODIFY"))
			{
				clinicalDocDetailVO.setDocumentId(_fb.getDocumentId());
				PatientClinicalDocumentsDATA.saveModifyClinicalSectionCompositionsDetail(clinicalDocDetailVO,userVO,_rq);
			
			}
			else
				PatientClinicalDocumentsDATA.saveClinicalSectionCompositionsDetail(clinicalDocDetailVO,userVO,_rq);
		  
		   /* Gson gson = new Gson();
		    String json=gson.toJson(lstClinicalDocDetailVO);
		    fb.setClinicalSectionCompJSON(json);
			*/
			WebUTIL.setAttributeInSession(_rq, EHRConfig.CLINICAL_SECTION_COMP_SELECT_JSON, _fb.getClinicalDocCompSelectJSON());
		    
				
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
	}
	finally
	{
		WebUTIL.setStatus(_rq, objStatus);
	}
	return isSave;
	
		
	}
	
	
	/**
	 * generate the document
	 */
	public static boolean documentGeneration(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		// List diagnosisTypeList=new ArrayList();
		String resultHtml;
		// String url="";
		// Map inAllMap=new HashMap();
		// Map inAllPreviousMap=new HashMap();
		List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
		PatientClinicalDocDetailVO patDocumentDtlVO = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// session = _rq.getSession();
			//DocumentProforma proforma = (DocumentProforma) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_PROFORMA_OBJECT);
			DocumentGroupDtlVO[] documentGroupDtlVO = (DocumentGroupDtlVO[]) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_ACCESS_POLICY_VO_ARRAY);
			DocumentAccessPolicyVO documentAccessPolicy = (DocumentAccessPolicyVO) session
					.getAttribute(EHRConfig.PATIENT_DOCUMENT_ACCESS_POLICY_DETAIL_VO);

			lstPrevDocuments = (List<PatientClinicalDocDetailVO>) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
				patDocumentDtlVO = lstPrevDocuments.get(index);
				
			}

			resultHtml = _fb.getDocumentHTML(); // proforma.getFinalDocumentHtmlCode();
			//System.out.println("***********get document type***********"+proforma.getDocumentType());
		//	patDocumentDtlVO.setDocumentType(proforma.getDocumentType());
			patDocumentDtlVO.setDocumentData(resultHtml);
			
			

			/*if (documentAccessPolicy.getAccessType() != null)
			{
				if (!documentAccessPolicy.getAccessType().equals(EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_TO_ALL))
				{
					if (documentGroupDtlVO == null || documentGroupDtlVO.length == 0)
					{
						objStatus.add(Status.DONE, "", "No Default Access Policy Found");
					}
					else
					{
						PatientClinicalDocumentsDATA.documentGeneration(patDocumentDtlVO, documentGroupDtlVO, documentAccessPolicy, userVO);
						objStatus.add(Status.DONE, "", "Document Generated Successfully");
					}
				}
				else
				{
					PatientClinicalDocumentsDATA.documentGeneration(patDocumentDtlVO, documentGroupDtlVO, documentAccessPolicy, userVO);
					objStatus.add(Status.DONE, "", "Document Generated Successfully");
				}

			}*/
			//else
			{
				PatientClinicalDocumentsDATA.documentGeneration(patDocumentDtlVO, documentGroupDtlVO, documentAccessPolicy, userVO);
				objStatus.add(Status.DONE, "", "Document Generated Successfully");
				//objStatus.add(Status.FAILURE, "", "No Default Access Policy Found");
			}
			// objStatus.add(Status.NEW, "", "Document Genrated Successfully");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
	
	public static String getViewDoc(PatientClinicalDocumentsFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
	{
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		String s ="";
		byte[] getDoc;
		try
		{
			String filename=fb.getDocumentId()+Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
			getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_GENERATED_PATIENT_PROFILE_UPLOAD).latestFetchFile(filename);
			s = new String(getDoc);
						
		}
		catch (Exception e)
		{
	    e.printStackTrace();
	    String msg="<B>This file does not exist:: Please Contact Administrator</B>";
		byte[] bytes=msg.getBytes();
		response_p.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}catch(Exception ee)
			{
			}
			
		}
	
		return s;
	}
	
	
	public static void fetchDetailsForGenerate(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
	{
		// boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		Map essentialMap = null;
		List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
		// List<PatientClinicalDocDetailVO> lstDeletingDocuments = new ArrayList<PatientClinicalDocDetailVO>();
		PatientClinicalDocDetailVO patDocumentDtlVO = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		String reviewDate = "" , dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="";
		String documentType = "";
		String pmode="1";   // 1. generate  2. modify
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			lstPrevDocuments = (List<PatientClinicalDocDetailVO>) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
				patDocumentDtlVO = lstPrevDocuments.get(index);
				_fb.setDocumentId(patDocumentDtlVO.getDocumentId());
				_fb.setSerialNo(patDocumentDtlVO.getSerialNo());
				//_fb.setDocumentHeader(patDocumentDtlVO.getDocumentHeader());
				//_fb.setRemarks(patDocumentDtlVO.getRemarks());
				_fb.setDocumentType(patDocumentDtlVO.getDocumentType());
			}

			PatientClinicalDocDetailVO _patientDocumentDtlVO = new PatientClinicalDocDetailVO();
			_patientDocumentDtlVO.setDocumentId(_fb.getDocumentId());
			_patientDocumentDtlVO.setDocumentType(_fb.getDocumentType());
			//_patientDocumentDtlVO.setPatCategoryCode(_fb.getPatCategoryCode());
			_patientDocumentDtlVO.setPatCrNo(_fb.getPatCrNo());
			_patientDocumentDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			_patientDocumentDtlVO.setSerialNo(_fb.getSerialNo());
			//_patientDocumentDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());

			
			
			List<PatientClinicalDocDetailVO> lstClinicalDocDetailVO= new ArrayList<PatientClinicalDocDetailVO>();
		    lstClinicalDocDetailVO=PatientClinicalDocumentsDATA.fetchDetailsForGenerate(pmode,_patientDocumentDtlVO,_fb.getDeskType(), userVO);
		   	   
		    
			WebUTIL.setAttributeInSession(_rq, EHRConfig.CLINICAL_SECTION_COMP_LIST_HTML, lstClinicalDocDetailVO);
			 Gson gson = new Gson();
			 String json=gson.toJson(lstClinicalDocDetailVO);
			 //   _fb.setClinicalSectionCompJSON(json);
			 	System.out.println("makkkkk"+json);
			/*	
			// to set document options
			if (session.getAttribute(EHRConfig.PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST) == null)
			{
				// Initialising Document with Header and Footer
				// PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

				lstProMenus = PatientClinicalDocumentsDATA.getDocumentBasedDeskMenus(_fb.getDocumentType(), _fb.getDeskId(), userVO);

		
				 * if(_fb.getDocumentType()!=null) { if(_fb.getDocumentType().equals(OpdConfig.DOCUMENT_TYPE_DISCHARGE)) {
				 * DeskMenuMasterVO footerVO1 = new DeskMenuMasterVO(); footerVO1.setDeskMenuId("99998");
				 * footerVO1.setDeskMenuName("Advice On Discharge"); footerVO1.setDeskUrl("ADVICEONDISCHARGE");
				 * lstProMenus.add(footerVO1); } }
				 * 
				 * DeskMenuMasterVO footerVO = new DeskMenuMasterVO(); footerVO.setDeskMenuId("99999");
				 * footerVO.setDeskMenuName("Document Footer"); footerVO.setDeskUrl("DOCUMENTFOOTER");
				 * lstProMenus.add(footerVO);
				 

				Map<String, String> mapMenuURL = new HashMap<String, String>();
				for (DeskMenuMasterVO vo : lstProMenus)
					mapMenuURL.put(vo.getDeskMenuId(), vo.getDeskUrl());
				// proforma.setOptionsURLMap(mapMenuURL);

				WebUTIL.setAttributeInSession(_rq, EHRConfig.PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST, lstProMenus);
				if (lstProMenus.size() == 0) throw new HisRecordNotFoundException("No Document Related Options in this Desk... ");
			}
			else
			{
				lstProMenus = (List) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST);
			}
			// to set document header

			DocumentProforma proforma = new DocumentProforma();
			PatientDetailVO patVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			proforma.setHeader(_fb.getDocumentHeader(), _fb.getRemarks(), patVO, userVO);
			proforma.setDocumentType(_fb.getDocumentType());
			List documentTypeList = (List) session.getAttribute(EHRConfig.DOCUMENT_TYPE_LIST);
			Iterator iteratorDocumentTypeList = documentTypeList.iterator();
			while (iteratorDocumentTypeList.hasNext())
			{
				Entry entry = (Entry) iteratorDocumentTypeList.next();
				if ((_fb.getDocumentType() != null) && (_fb.getDocumentType().equals(entry.getValue())))
				{
					proforma.setDocumentTypeDesc(entry.getLabel());
					break;
				}
			}
			WebUTIL.setAttributeInSession(_rq, EHRConfig.PATIENT_DOCUMENT_PROFORMA_OBJECT, proforma);
			// Document Diagnosis Detail
			DocumentDiagnosisDtlVO[] documentDiagnosisDtlVO = (DocumentDiagnosisDtlVO[]) session
					.getAttribute(EHRConfig.PATIENT_DOCUMENT_DIAGNOSIS_DTL_VO_ARRAY);
			if (documentDiagnosisDtlVO != null && documentDiagnosisDtlVO.length > 0)
			{
				// //////// to get fetch selected row
				String[] str = new String[documentDiagnosisDtlVO.length];
				for (int i = 0; i < documentDiagnosisDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);


			}}*/
				}
	finally
	{
		
	}
	


}
	
	
	
	public static void fetchDetailsForModify(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
	{
		// boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = null;
		Map essentialMap = null;
		List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
		// List<PatientClinicalDocDetailVO> lstDeletingDocuments = new ArrayList<PatientClinicalDocDetailVO>();
		PatientClinicalDocDetailVO patDocumentDtlVO = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		String reviewDate = "" , dischargeType="", dischargeAdvisedBy="", dischargeAdvisedDept="";
		String documentType = "";
		String pmode="2";   // 1. generate  2. modify
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			lstPrevDocuments = (List<PatientClinicalDocDetailVO>) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST);

			for (int i = 0; i < _fb.getSelectedIndex().length; i++)
			{
				int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
				patDocumentDtlVO = lstPrevDocuments.get(index);
				_fb.setDocumentId(patDocumentDtlVO.getDocumentId());
				_fb.setSerialNo(patDocumentDtlVO.getSerialNo());
				//_fb.setDocumentHeader(patDocumentDtlVO.getDocumentHeader());
				//_fb.setRemarks(patDocumentDtlVO.getRemarks());
				_fb.setDocumentType(patDocumentDtlVO.getDocumentType());
			}

			PatientClinicalDocDetailVO _patientDocumentDtlVO = new PatientClinicalDocDetailVO();
			_patientDocumentDtlVO.setDocumentId(_fb.getDocumentId());
			_patientDocumentDtlVO.setDocumentType(_fb.getDocumentType());
			//_patientDocumentDtlVO.setPatCategoryCode(_fb.getPatCategoryCode());
			_patientDocumentDtlVO.setPatCrNo(_fb.getPatCrNo());
			_patientDocumentDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			//_patientDocumentDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
			_patientDocumentDtlVO.setSerialNo(_fb.getSerialNo());

			
			
			List<PatientClinicalDocDetailVO> lstClinicalDocDetailVO= new ArrayList<PatientClinicalDocDetailVO>();
		    lstClinicalDocDetailVO=PatientClinicalDocumentsDATA.fetchDetailsForGenerate(pmode,_patientDocumentDtlVO,_fb.getDeskType(), userVO);
		    
		    
			WebUTIL.setAttributeInSession(_rq, EHRConfig.CLINICAL_SECTION_COMP_LIST_HTML, lstClinicalDocDetailVO);
			
			if(lstClinicalDocDetailVO!=null)
			{
				PatientClinicalDocDetailVO doctypevoo  = (PatientClinicalDocDetailVO)lstClinicalDocDetailVO.get(0);
				_fb.setClinicalDocCompSelectJSONModify( doctypevoo.getClinicalDocCompSelectJSONModify());
				HelperMethods.populatetToNullOrEmpty(_fb, doctypevoo);
				
				/*String prevClinicalSectionCode="", newClinicalSectionCode="";
				for(int i=0;i<lstClinicalDocDetailVO.size();i++)
				{
					PatientClinicalDocDetailVO voo  = (PatientClinicalDocDetailVO)lstClinicalDocDetailVO.get(i);
					String key = voo.getClinicalSecCompKey();
					newClinicalSectionCode= voo.getClinicalSectionCode();
					System.out.println(key);
					//String url="/emr/ehrComposition_"+key+".cnt?hmode=PATCLINICALDOC_"+key+"_VIEW";
					if(newClinicalSectionCode!=null){
				  if(!newClinicalSectionCode.equalsIgnoreCase(prevClinicalSectionCode)) {  
					voo.getClinicalSectionName();
					 
					// voo.getClinicalDocCompSelectJSON();
					
				
			
				  }
			}}*/}

			/*	
			// to set document options
			if (session.getAttribute(EHRConfig.PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST) == null)
			{
				// Initialising Document with Header and Footer
				// PatientDetailVO patVO = (PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

				lstProMenus = PatientClinicalDocumentsDATA.getDocumentBasedDeskMenus(_fb.getDocumentType(), _fb.getDeskId(), userVO);

		
				 * if(_fb.getDocumentType()!=null) { if(_fb.getDocumentType().equals(OpdConfig.DOCUMENT_TYPE_DISCHARGE)) {
				 * DeskMenuMasterVO footerVO1 = new DeskMenuMasterVO(); footerVO1.setDeskMenuId("99998");
				 * footerVO1.setDeskMenuName("Advice On Discharge"); footerVO1.setDeskUrl("ADVICEONDISCHARGE");
				 * lstProMenus.add(footerVO1); } }
				 * 
				 * DeskMenuMasterVO footerVO = new DeskMenuMasterVO(); footerVO.setDeskMenuId("99999");
				 * footerVO.setDeskMenuName("Document Footer"); footerVO.setDeskUrl("DOCUMENTFOOTER");
				 * lstProMenus.add(footerVO);
				 

				Map<String, String> mapMenuURL = new HashMap<String, String>();
				for (DeskMenuMasterVO vo : lstProMenus)
					mapMenuURL.put(vo.getDeskMenuId(), vo.getDeskUrl());
				// proforma.setOptionsURLMap(mapMenuURL);

				WebUTIL.setAttributeInSession(_rq, EHRConfig.PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST, lstProMenus);
				if (lstProMenus.size() == 0) throw new HisRecordNotFoundException("No Document Related Options in this Desk... ");
			}
			else
			{
				lstProMenus = (List) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_BASED_DESK_MENUS_LIST);
			}
			// to set document header

			DocumentProforma proforma = new DocumentProforma();
			PatientDetailVO patVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			proforma.setHeader(_fb.getDocumentHeader(), _fb.getRemarks(), patVO, userVO);
			proforma.setDocumentType(_fb.getDocumentType());
			List documentTypeList = (List) session.getAttribute(EHRConfig.DOCUMENT_TYPE_LIST);
			Iterator iteratorDocumentTypeList = documentTypeList.iterator();
			while (iteratorDocumentTypeList.hasNext())
			{
				Entry entry = (Entry) iteratorDocumentTypeList.next();
				if ((_fb.getDocumentType() != null) && (_fb.getDocumentType().equals(entry.getValue())))
				{
					proforma.setDocumentTypeDesc(entry.getLabel());
					break;
				}
			}
			WebUTIL.setAttributeInSession(_rq, EHRConfig.PATIENT_DOCUMENT_PROFORMA_OBJECT, proforma);
			// Document Diagnosis Detail
			DocumentDiagnosisDtlVO[] documentDiagnosisDtlVO = (DocumentDiagnosisDtlVO[]) session
					.getAttribute(EHRConfig.PATIENT_DOCUMENT_DIAGNOSIS_DTL_VO_ARRAY);
			if (documentDiagnosisDtlVO != null && documentDiagnosisDtlVO.length > 0)
			{
				// //////// to get fetch selected row
				String[] str = new String[documentDiagnosisDtlVO.length];
				for (int i = 0; i < documentDiagnosisDtlVO.length; i++)
				{
					str[i] = String.valueOf(i);
				}
				_fb.setFetchSelectedRow(str);


			}}*/
				}
	finally
	{
		
	}
	


}
	
	
	
	
	

	
	// Modifying Patient Document
	
	public static void fetchClinicalDocumentDetails(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = null;
		// List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
		List<DeskMenuMasterVO> lstProMenus = null;
		// PatientClinicalDocDetailVO patDocumentDtlVO = null;
		Map essentialMap = null;
		String profileType = "";
		DisclaimerMstVO _disclaimerMstVOs = new DisclaimerMstVO();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			session = _rq.getSession();
			setSysdate(_rq);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));

			/*
			 * for(int i=0; i<_fb.getSelectedIndex().length; i++) { int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
			 * patDocumentDtlVO = lstPrevDocuments.get(index); _fb.setDocumentId(patDocumentDtlVO.getDocumentId());
			 * _fb.setSerialNo(patDocumentDtlVO.getSerialNo()); _fb.setDocumentHeader(patDocumentDtlVO.getDocumentHeader());
			 * _fb.setRemarks(patDocumentDtlVO.getRemarks()); }
			 */

			// String profileTypeCode=_fb.getDocumentType();
			profileType = _fb.getDocumentType();
			// String isUnique=_fb.getDocumentType().split("#")[1];
			// String profileHeader=_fb.getDocumentType().split("#")[2];
			
			PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						patientDetailVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/

			PatientClinicalDocDetailVO _patientDocumentDtlVO = new PatientClinicalDocDetailVO();
			_patientDocumentDtlVO.setDocumentId(_fb.getDocumentId());
			_patientDocumentDtlVO.setPatCrNo(_fb.getPatCrNo());
			_patientDocumentDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			_patientDocumentDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
			_patientDocumentDtlVO.setDocumentType(profileType);

			essentialMap = PatientClinicalDocumentsDATA.fetchClinicalDocument(_patientDocumentDtlVO, patientDetailVO.getPatGenderCode(), userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);
			lstProMenus = PatientClinicalDocumentsDATA.getDocumentBasedDeskMenus(profileType, _fb.getDeskId(), userVO);

	
		
		
		WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_PROFILE_PROFORMA_OBJECT, proforma);
	
	
	objStatus.add(Status.NEW);
}
catch (HisRecordNotFoundException e)
{
	e.printStackTrace();
	objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
}
catch (HisDataAccessException e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR_DA, e.getMessage(), "");
}
catch (HisApplicationExecutionException e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR_AE, e.getMessage(), "");
}
catch (HisException e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR, e.getMessage(), "");
}
catch (Exception e)
{
	e.printStackTrace();
	objStatus.add(Status.ERROR_AE, e.getMessage(), "");
}
finally
{
	WebUTIL.setStatus(_rq, objStatus);
}
}
	
	
	
	
	
	
	
	
	
	
	
		public static boolean modifyClinicalDocument(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
		{
			boolean flag = true;
			Status objStatus = new Status();
			HttpSession session = null;

			List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
			List<PatientClinicalDocDetailVO> lstModifyingDocuments = new ArrayList<PatientClinicalDocDetailVO>();
			PatientClinicalDocDetailVO patDocumentDtlVO = null;
			try
			{
				UserVO userVO = getUserVO(_rq);
				session = _rq.getSession();
				lstPrevDocuments = (List<PatientClinicalDocDetailVO>) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST); //PATIENT_PROFILE_EPISODE_PROFILES_LIST

				for (int i = 0; i < _fb.getSelectedIndex().length; i++)
				{
					int index = Integer.parseInt(_fb.getSelectedIndex()[i]);

					patDocumentDtlVO = new PatientClinicalDocDetailVO();
					HelperMethods.populate(patDocumentDtlVO, lstPrevDocuments.get(index));

					patDocumentDtlVO.setPatCrNo(_fb.getPatCrNo());
					patDocumentDtlVO.setEpisodeCode(_fb.getEpisodeCode());
					patDocumentDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					patDocumentDtlVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
					patDocumentDtlVO.setAdmissionNo(_fb.getAdmissionNo());
					if (!patDocumentDtlVO.getAccessType().equalsIgnoreCase(_fb.getSelectedAccessType())
							|| !patDocumentDtlVO.getUserLevel().equalsIgnoreCase(_fb.getSelectedUserLevel()))
					{
						patDocumentDtlVO.setAccessType(_fb.getSelectedAccessType());
						patDocumentDtlVO.setUserLevel(_fb.getSelectedUserLevel());
						lstModifyingDocuments.add(patDocumentDtlVO);
					}
				}
				if (lstModifyingDocuments.size() > 0) PatientClinicalDocumentsDATA.modifyClinicalDocument(lstModifyingDocuments, userVO);
				objStatus.add(Status.LIST);
				objStatus.add(Status.NEW, "", "Document(s) Modified Successfully");
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				flag = false;
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				flag = false;
			}
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				flag = false;
			}
			catch (HisException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR, e.getMessage(), "");
				flag = false;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				flag = false;
			}
			finally
			{
				WebUTIL.setStatus(_rq, objStatus);
			}
			return flag;
		}

		// Removing Patient Document
		public static boolean removeClinicalDocument(PatientClinicalDocumentsFB _fb, HttpServletRequest _rq)
		{
			boolean flag = true;
			Status objStatus = new Status();
			HttpSession session = null;

			List<PatientClinicalDocDetailVO> lstPrevDocuments = null;
			List<PatientClinicalDocDetailVO> lstDeletingDocuments = new ArrayList<PatientClinicalDocDetailVO>();
			PatientClinicalDocDetailVO patDocumentDtlVO = null;
			try
			{
				UserVO userVO = getUserVO(_rq);
				session = _rq.getSession();
				lstPrevDocuments = (List<PatientClinicalDocDetailVO>) session.getAttribute(EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST);

				for (int i = 0; i < _fb.getSelectedIndex().length; i++)
				{
					int index = Integer.parseInt(_fb.getSelectedIndex()[i]);
					patDocumentDtlVO = lstPrevDocuments.get(index);
					lstDeletingDocuments.add(patDocumentDtlVO);
				}
				if (lstDeletingDocuments.size() > 0) PatientClinicalDocumentsDATA.removeClinicalDocument(lstDeletingDocuments, userVO);
				if (lstPrevDocuments.size() > 1) objStatus.add(Status.LIST);
				objStatus.add(Status.NEW, "", "Document Removed Successfully");
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
				flag = false;
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				flag = false;
			}
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				flag = false;
			}
			catch (HisException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR, e.getMessage(), "");
				flag = false;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				flag = false;
			}
			finally
			{
				WebUTIL.setStatus(_rq, objStatus);
			}
			return flag;
		}
	
}