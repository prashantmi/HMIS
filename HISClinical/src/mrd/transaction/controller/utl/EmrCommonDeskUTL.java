package mrd.transaction.controller.utl;

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
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.EprTabMasterVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ServiceAreaPatientVO;
import hisglobal.vo.ServiceAreaVO;
import hisglobal.vo.Service_Req_dtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.VisitReasonsVO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdDocumentArchivalDATA;
import opd.transaction.controller.data.PatientProfileInboxDATA;
import opd.transaction.controller.fb.OpdDocumentArchivalFB;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

import ehr.EHRConfig;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import emr.vo.EHR_PatientProfileDtlVO;
import mrd.MrdConfig;
import mrd.transaction.controller.data.EmrCommonDeskDATA;
import mrd.transaction.controller.data.PatImageUploadDATA;
import mrd.transaction.controller.fb.EmrCommonDeskFB;
import registration.RegistrationConfig;

public class EmrCommonDeskUTL extends ControllerUTIL {

	/**
	 * get the list of the department unit user wise/seat wise
	 * 
	 * @param _fb
	 * @param _request
	 */
	
	public static void getDepartmentUnitListForEMR(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		List<Entry> departmentUnitList = new ArrayList<Entry>();
		try {
			UserVO userVO = getUserVO(_request);
			departmentUnitList = EmrCommonDeskDATA.getDepartmentUnitListForEMR(userVO);
			// if there is only one record then set the departmetunitcode on the
			// form bean
			if (departmentUnitList != null && departmentUnitList.size() == 1) {
				_fb.setDepartmentUnitCode(departmentUnitList.get(0).getValue());
			}

			HttpSession session = _request.getSession();
			session.removeAttribute("crNo");

			// if no department found for the user then forward the user to the
			// cr no page.
			if (departmentUnitList == null) {
				_fb.setIsNew("1");
			}

			WebUTIL.setAttributeInSession(_request,
					MrdConfig.ESSENTIAL_DEPARTMENT_UNIT_LIST,
					departmentUnitList);
			objStatus.add(Status.NEW);
		} catch (HisRecordNotFoundException e) {
			objStatus.add(Status.NEW, "User Has Not Access of EPR", "");
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	// not in use
	public static boolean getEssentials(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		boolean isValidCR = true;
		Status objStatus = new Status();
		EpisodeVO[] episodeVOs = null;
		PatientDetailVO[] patientDetailVOs = null;
		StringBuilder stringBuilder = new StringBuilder();
		Map essentialMap = null;
		try {
			UserVO userVO = getUserVO(_request);
			PatientVO patientVO = new PatientVO();
			patientVO.setPatCrNo(_fb.getPatCrNo());
			essentialMap = EmrCommonDeskDATA
					.getEmrEssentials(patientVO, userVO);
			episodeVOs = (EpisodeVO[]) essentialMap
					.get(MrdConfig.EPISODE_TREE_ARRAY);
			for (int i = 0; i < episodeVOs.length; i++) {
				if (i == 0) {
					stringBuilder.append(episodeVOs[i].getDepartmentUnit()
							+ "#");
					stringBuilder.append(episodeVOs[i].getEpisodeCode() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeVisitNo());
				} else {
					stringBuilder.append("^"
							+ episodeVOs[i].getDepartmentUnit() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeCode() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeVisitNo());
				}
			}
			stringBuilder.append("");
			System.out.println("display " + stringBuilder.toString());
			patientDetailVOs = (PatientDetailVO[]) essentialMap
					.get(MrdConfig.ADMISSION_TREE_ARRAY);

			if (patientDetailVOs != null) {
				StringBuilder stringBuilderAdmission = new StringBuilder();
				for (int i = 0; i < patientDetailVOs.length; i++) {

					stringBuilderAdmission.append(patientDetailVOs[i]
							.getPatAdmNo() + "#");

				}
				_fb.setAddmissionTreeNode(stringBuilderAdmission.toString());
			} else {
				_fb.setAddmissionTreeNode("");
			}

			_fb.setTreeNode(stringBuilder.toString());
		} catch (HisRecordNotFoundException e) {
			 isValidCR = false;
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			 isValidCR = false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			 isValidCR = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			 isValidCR = false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
		return  isValidCR;
	}

	// sets the data for creating the tree and the node
	public static void getNode(EmrCommonDeskFB _fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		EpisodeVO[] episodeVOs = null;
		StringBuilder stringBuilder = new StringBuilder();
		Map essentialMap = null;
		PatientDetailVO[] patientDetailVOs = null;
		try {
			UserVO userVO = getUserVO(_request);
			PatientVO patientVO = new PatientVO();
			HttpSession session = WebUTIL.getSession(_request);
			patientVO.setPatCrNo((String) session.getAttribute("crNo"));
			essentialMap = EmrCommonDeskDATA.getEmrEssentials(patientVO, userVO);
			episodeVOs = (EpisodeVO[]) essentialMap.get(MrdConfig.EPISODE_TREE_ARRAY);
			String unitCode = "";
			// set the data for the opd information
			_fb.setSelectionCriteria("All");// Default Changed by Pragya
											// 2014.06.17
			String dummyhosCode = "0";
			Set<String> setHosCode = new LinkedHashSet<String>();
			Map<String, String> mapHosName = new HashMap<String, String>();
			for (int i = 0; i < episodeVOs.length; i++) {

				if (i == 0) {
					stringBuilder.append(episodeVOs[i].getDepartmentUnit()
							+ "#");
					stringBuilder.append(episodeVOs[i].getEpisodeCode() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeVisitNo()
							+ "#");
					stringBuilder.append(episodeVOs[i].getEntryDate() + "#");
					stringBuilder
							.append(episodeVOs[i].getEpisodeIsOpen() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeTypeCode()
							+ "#"); // Change By Amit for Appending Hospital
									// Code
					stringBuilder.append(episodeVOs[i].getHospitalCode());
					// stringBuilder.append(episodeVOs[i].getHospitalName().toString());
					unitCode = episodeVOs[i].getDepartmentUnitCode();
				} else {
					stringBuilder.append("^"
							+ episodeVOs[i].getDepartmentUnit() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeCode() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeVisitNo()
							+ "#");
					stringBuilder.append(episodeVOs[i].getEntryDate() + "#");
					stringBuilder
							.append(episodeVOs[i].getEpisodeIsOpen() + "#");
					stringBuilder.append(episodeVOs[i].getEpisodeTypeCode()
							+ "#");
					stringBuilder.append(episodeVOs[i].getHospitalCode());
					// stringBuilder.append(episodeVOs[i].getHospitalName().toString());
					unitCode = unitCode + "^"
							+ episodeVOs[i].getDepartmentUnitCode();
				}

				setHosCode.add(episodeVOs[i].getHospitalCode());
				mapHosName.put(episodeVOs[i].getHospitalCode(),
						episodeVOs[i].getHospitalName());
			}

			String[] hosCode = new String[setHosCode.size()];
			String[] hosName = new String[setHosCode.size()];
			int ii = 0;
			for (String code : setHosCode) {
				hosCode[ii] = code;
				if(mapHosName !=null){
					 if(mapHosName.get(code) !=null){
					
					      hosName[ii] = mapHosName.get(code).trim();
					 }
				}
				
				//hosName[ii] = mapHosName.get(code).trim();
				ii++;
			}
			_fb.setHospitalCode(hosCode);
			_fb.setHospitalName(hosName);

			_fb.setUnitCodeArray(unitCode);
			_fb.setTreeNode(stringBuilder.toString());

			// sets the data for the ipd information
			patientDetailVOs = (PatientDetailVO[]) essentialMap
					.get(MrdConfig.ADMISSION_TREE_ARRAY);
			if (patientDetailVOs != null) {
				StringBuilder stringBuilderAdmission = new StringBuilder();
				for (int i = 0; i < patientDetailVOs.length; i++) {
					if (i == 0) {
						stringBuilderAdmission.append(patientDetailVOs[i]
								.getPatAdmNo()
								+ "$"
								+ patientDetailVOs[i].getAdmDateTime()
								+ "$"
								+ patientDetailVOs[i].getDisDateTime()
								+ "$"
								+ patientDetailVOs[i].getHospitalCode());
					} else {
						stringBuilderAdmission.append("#"
								+ patientDetailVOs[i].getPatAdmNo() + "$"
								+ patientDetailVOs[i].getAdmDateTime() + "$"
								+ patientDetailVOs[i].getDisDateTime() + "$"
								+ patientDetailVOs[i].getHospitalCode());
					}
				}

				_fb.setAddmissionTreeNode(stringBuilderAdmission.toString());

				System.out.println("display " + stringBuilder.toString());
			} else {
				_fb.setAddmissionTreeNode("");
			}

			WebUTIL.setAttributeInSession(_request,
					MrdConfig.EPISODE_TREE_ARRAY, episodeVOs);
			WebUTIL.setAttributeInSession(_request,
					MrdConfig.ADMISSION_TREE_ARRAY, patientDetailVOs);
		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	// get the patient demographic detail
	public static void getPatDetail(EmrCommonDeskFB _fb,HttpServletRequest _request,HttpServletResponse _response) {
		Status objStatus = new Status();
		int chkData = 0, i = 0, chkd = 0;
		JsonElement jsonObj = null;
		String PatData = "";

		try {
			UserVO userVO = getUserVO(_request);
			PatientVO patientVO = null;
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PATIENT_DTL_VO);
			// String crno=(String)session.getAttribute("crNo");
			/*
			 * CRNoFB crnoFB=new CRNoFB(); crnoFB.setPatCrNo(crno);
			 */
			// HelperMethods.populate(crnoFB, _fb);

			patientVO = (PatientVO) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PATIENT_DTL_VO, _request);

			// if(patientVO==null)
			{
				patientVO = new PatientVO();
				// PatDetailUTIL.getPatientDtlByCrno(crnoFB, _request);
				// PatientVO
				// patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
				patientVO.setPatCrNo((String) session.getAttribute("crNo"));
				patientVO = EmrCommonDeskDATA.getPatientDetails(patientVO,
						userVO);

				jsonObj = EmrCommonDeskDATA.getPatientEMR(patientVO, userVO,
						_request);

				PatData = jsonObj.toString().replaceAll("'", " ");
				setPatientRecordMap((String) session.getAttribute("crNo"),
						MrdConfig.PATIENT_DTL_VO, patientVO, _request);
				
				System.out.println("PatData==="+PatData);
				
				
			}
			HelperMethods.populate(_fb, patientVO);
			// session.setAttribute(MrdConfig.PATIENT_DTL_VO,patientVO);
			WebUTIL.setAttributeInSession(_request, MrdConfig.PATIENT_DTL_VO,
					patientVO);
			//Addeb by prachi 
			
			//ObjectMapper mapper = new ObjectMapper();
			 //String json = mapper.writeValueAsString(lstTemplates);
			//String json = new Gson().toJson(PatData);

			// System.out.println(json);
			
			WebUTIL.setAttributeInSession(_request, EHRConfig.EHR_JSON_OBJECT_FIELD_NAME_PATIENT_DATA, PatData);
			
		   objStatus.add(Status.NEW);
		   
		  // _response.getWriter().write(PatData);
			
		   //_response.setContentType("application/json"); 
			/*WebUTIL.setAttributeInSession(_request,
					EHRConfig.EHR_JSON_OBJECT_FIELD_NAME_PATIENT_DATA, PatData);*/
			// configureDetail(_fb, _request);
		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	finally {
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	public static void configureDetail(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try {
			/*
			 * UserVO userVO=getUserVO(_request);
			 * 
			 * String[] emrTabs=MrdConfig.EMR_TABS;
			 * WebUTIL.setAttributeInSession(_request,MrdConfig.EMR_TABS_ARRAY,
			 * emrTabs); String[] emrTabsUrl=MrdConfig.EMR_TABS_URL; String
			 * tempUrl=""; for(int i=0;i<emrTabsUrl.length;i++) { if(i==0) {
			 * tempUrl=emrTabsUrl[i]; } else {
			 * tempUrl=tempUrl+"^"+emrTabsUrl[i]; } } _fb.setEmrTabs(tempUrl);
			 * WebUTIL.setAttributeInSession(_request,"emrTabs", tempUrl);
			 */

			List<EprTabMasterVO> eprTabMasterVOList = (List) session
					.getAttribute(MrdConfig.EMR_TABS_ARRAY);
			Iterator itr = eprTabMasterVOList.iterator();
			String[] emrTabsUrl = new String[eprTabMasterVOList.size()];
			int i = 0;
			while (itr.hasNext()) {
				EprTabMasterVO eprTabMasterVO = (EprTabMasterVO) itr.next();
				emrTabsUrl[i++] = eprTabMasterVO.getTabUrl();
			}
			String tempUrl = "";
			for (i = 0; i < emrTabsUrl.length; i++) {
				if (i == 0) {
					tempUrl = emrTabsUrl[i];
				} else {
					tempUrl = tempUrl + "^" + emrTabsUrl[i];
				}
			}
			_fb.setEmrTabs(tempUrl);
			WebUTIL.setAttributeInSession(_request, "emrTabs", tempUrl);

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * get the tab detail get the tab access detail get the patient address
	 * detail get the patient current diagnosis detail patient chronic disease
	 * patient family doctor detail patient next visit details
	 * 
	 * @param _fb
	 * @param _request
	 */
	/*public String execute(){
	String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
	System.out.println("The file path for server is: "+filePath);
	return filePath;
	}
	*//*//File fileToCreate = new File(filePath, "Patient-Male-icon.png");
*/
	
	
	
	/*public static void getPersonalProfileDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) throws IOException {

		Status objStatus=new Status();
		byte[] imageArray=null;
		try
		{
			UserVO userVO=getUserVO(_request);
			PatientVO patientVO=new PatientVO();
			HttpSession session=WebUTIL.getSession(_request);
			patientVO.setPatCrNo((String)session.getAttribute("crNo"));
			String tabType=MrdConfig.EPR_TAB_TYPE_OPD;
			String departmentUnitCode=(String)session.getAttribute("departmentUnitCode");
			_fb.setDepartmentUnitCode(departmentUnitCode);
			Map map=(Map)getPateintRecordMap((String)session.getAttribute("crNo"),MrdConfig.PERSONAL_PROFILE_MAP, _request);
			//if(map==null)
			//{
			map=EmrCommonDeskDATA.getPersonalProfileDetails(patientVO,departmentUnitCode, tabType,userVO);
			setPatientRecordMap((String)session.getAttribute("crNo"), MrdConfig.PERSONAL_PROFILE_MAP, map, _request);
			//}
			
			PatientImageDtlVO[] patientImageDtlVO=(PatientImageDtlVO[])map.get(MrdConfig.PAT_IMAGE_DETAILS);
			List imageArrayList=new ArrayList();
			//EpisodeVO[] episodeVOsNextVisit=null;
			if(patientImageDtlVO!=null)
			{
				for(int i=0;i<patientImageDtlVO.length;i++)
				{
					if( (patientImageDtlVO[i].getIsImageDefault()!=null) && (patientImageDtlVO[i].getIsImageDefault().equals("1")) )
					{
						try {
							String oSName=System.getProperties().getProperty("os.name");
							if(oSName.startsWith("Linux"))
							{
								imageArray=HelperMethods.getByteArrayOfImage("/root/"+patientImageDtlVO[i].getFilePath()+"\\"+patientImageDtlVO[i].getFileNo());
							}
							else
							{
								imageArray=HelperMethods.getByteArrayOfImage(patientImageDtlVO[i].getFilePath()+"/"+patientImageDtlVO[i].getFileNo());
							}
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} 
						break;
						
						// Previously used for mongodb connection  commented by mansiha gangwar date: 23.3.2018
						byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(patientImageDtlVO[i].getFileNo());
						 //End of mongodb
						
						//byteArray.add(HelperMethods.getByteArrayOfImage(filepath));
						
						
						// Added by Manisha Gangwar Date: 22.3.2018 for saving image to Postgres
						 byte[] getDoc= PatImageUploadDATA.latestFetchImagePostgres(patientImageDtlVO[i].getFileNo());
						// byte[] getDoc = Base64.decodeBase64(decodedData);
						
					
						
						byte[] readBytes = null;
						try
						{
							//readBytes = new byte[is.available()];
							//is.read(readBytes);

							imageArrayList.add(getDoc);
						}
						catch (Exception e)
						{
							readBytes = null;
							e.printStackTrace();
						}
					}
				}
			}
			imageArrayList.add(imageArray);
			
			if(patientImageDtlVO!=null)
			{
				for(int i=0;i<patientImageDtlVO.length;i++)
				{
					if( (patientImageDtlVO[i].getIsImageDefault()==null) || (!patientImageDtlVO[i].getIsImageDefault().equals("1")) )
					{ 
						//byte[] imageTempArray=null;
						try {
							String oSName=System.getProperties().getProperty("os.name");
							if(oSName.startsWith("Linux"))
							{
								imageArrayList.add(HelperMethods.getByteArrayOfImage("/root/"+patientImageDtlVO[i].getFilePath()+"\\"+patientImageDtlVO[i].getFileNo()));
							}
							else
							{
								imageArrayList.add(HelperMethods.getByteArrayOfImage(patientImageDtlVO[i].getFilePath()+"/"+patientImageDtlVO[i].getFileNo()));
							}
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						
					}
				}
			}
		
			WebUTIL.setAttributeInSession(_request, Config.IMAGE_BYTE_ARRAY, imageArrayList);
			WebUTIL.setAttributeInSession(_request, RegistrationConfig.UPLOADED_FILE_AS_ARRAY, imageArray);
			WebUTIL.setMapInSession(map, _request);
			PatientVO patientSessionVO=(PatientVO)session.getAttribute(MrdConfig.PATIENT_DTL_VO);
			if(patientSessionVO==null)
			{
				patientSessionVO=new PatientVO();
				//PatDetailUTIL.getPatientDtlByCrno(crnoFB, _request);
				//PatientVO patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
				patientSessionVO.setPatCrNo((String)session.getAttribute("crNo"));
				patientSessionVO=EmrCommonDeskDATA.getPatientDetails(patientSessionVO, userVO);
				//patientVO.setMlcNo(patVO.getMlcNo());
				setPatientRecordMap((String)session.getAttribute("crNo"), MrdConfig.PATIENT_DTL_VO, patientSessionVO, _request);
			}

			MlcVO mlcVO=(MlcVO)map.get(MrdConfig.PAT_MLC_VO);
			if(mlcVO.getMlcNo()==null)
				patientSessionVO.setMlcNo("yet to be alloted");
			else
				patientSessionVO.setMlcNo(mlcVO.getMlcNo());
			
			// CR Merge Status
			List<CrNoMergeDtlVO> lstCrMerge= (List<CrNoMergeDtlVO>) map.get(MrdConfig.PATIENT_CR_MERGE_LIST);
			if(lstCrMerge!=null && lstCrMerge.size()>0)
				patientSessionVO.setIsMainCrNo("1");
			else
				patientSessionVO.setIsMainCrNo("0");
			
			//session.setAttribute(MrdConfig.PATIENT_DTL_VO, patientSessionVO);
			WebUTIL.setAttributeInSession(_request, MrdConfig.PATIENT_DTL_VO, patientSessionVO);
			HelperMethods.populatetToNullOrEmpty(_fb,patientSessionVO);
			
//			if()
			
			
			configureDetail(_fb, _request);
		}
		catch(HisRecordNotFoundException e){
			e.printStackTrace();
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
			e.printStackTrace();
		}		
		finally{
			WebUTIL.setStatus(_request,objStatus);
		  
		}
	}*/
	
	
	//Added By Ranjit For FTP
	public static void getPersonalProfileDetails(EmrCommonDeskFB _fb, HttpServletRequest _request) throws IOException {

	    Status objStatus = new Status();
	    byte[] imageArray = null;
	    String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE;
	    java.io.InputStream inputStream = null;
	    try {
	        UserVO userVO = getUserVO(_request);
	        PatientVO patientVO = new PatientVO();
	        HttpSession session = WebUTIL.getSession(_request);
	        patientVO.setPatCrNo((String) session.getAttribute("crNo"));
	        String tabType = MrdConfig.EPR_TAB_TYPE_OPD;
	        String departmentUnitCode = (String) session.getAttribute("departmentUnitCode");
	        _fb.setDepartmentUnitCode(departmentUnitCode);



	        Map map = (Map) getPateintRecordMap((String) session.getAttribute("crNo"), MrdConfig.PERSONAL_PROFILE_MAP, _request);
	        //if(map==null)
	        //{
	        map = EmrCommonDeskDATA.getPersonalProfileDetails(patientVO, departmentUnitCode, tabType, userVO);
	        setPatientRecordMap((String) session.getAttribute("crNo"), MrdConfig.PERSONAL_PROFILE_MAP, map, _request);
	        //}

	        PatientImageDtlVO[] patientImageDtlVO = (PatientImageDtlVO[]) map.get(MrdConfig.PAT_IMAGE_DETAILS);



	        List imageArrayList = new ArrayList();
	        //EpisodeVO[] episodeVOsNextVisit=null;
	        if (patientImageDtlVO != null) {

	            for (int i = 0; i < patientImageDtlVO.length; i++) {

	                patientImageDtlVO[i].setPatCrNo((String) session.getAttribute("crNo"));
	                byte[] getDoc = PatImageUploadDATA.latestFetchImagePostgres(patientImageDtlVO[i].getFileNo());
	                if (getDoc == null || getDoc.length == 0) {

	                    patientImageDtlVO[i].setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD + "_Image_" + patientImageDtlVO[i].getFileNo());

	                    String strFTPFilePath = FTPFileTransfer.getFilePath(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patientImageDtlVO[i].getPatCrNo());
	                    String finalDocumentURL = ftpServerURL + strFTPFilePath + "/" + patientImageDtlVO[i].getFileNo();

	                    try {
	                        URL urlftp = new URL(finalDocumentURL);
	                        URLConnection urlcon = urlftp.openConnection();
	                        inputStream = urlcon.getInputStream();
	                        imageArray = IOUtils.toByteArray(inputStream);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }


	                //					}
	            }
	        }
	        imageArrayList.add(imageArray);

	        WebUTIL.setAttributeInSession(_request, Config.IMAGE_BYTE_ARRAY, imageArrayList);
	        WebUTIL.setAttributeInSession(_request, RegistrationConfig.UPLOADED_FILE_AS_ARRAY, imageArray);
	        WebUTIL.setMapInSession(map, _request);
	        PatientVO patientSessionVO = (PatientVO) session.getAttribute(MrdConfig.PATIENT_DTL_VO);
	        if (patientSessionVO == null) {
	            patientSessionVO = new PatientVO();
	            //PatDetailUTIL.getPatientDtlByCrno(crnoFB, _request);
	            //PatientVO patVO=(PatientVO)session.getAttribute(RegistrationConfig.PATIENT_VO);
	            patientSessionVO.setPatCrNo((String) session.getAttribute("crNo"));
	            patientSessionVO = EmrCommonDeskDATA.getPatientDetails(patientSessionVO, userVO);
	            //patientVO.setMlcNo(patVO.getMlcNo());
	            setPatientRecordMap((String) session.getAttribute("crNo"), MrdConfig.PATIENT_DTL_VO, patientSessionVO, _request);
	        }

	        MlcVO mlcVO = (MlcVO) map.get(MrdConfig.PAT_MLC_VO);
	        if (mlcVO.getMlcNo() == null)
	            patientSessionVO.setMlcNo("yet to be alloted");
	        else
	            patientSessionVO.setMlcNo(mlcVO.getMlcNo());

	        // CR Merge Status
	        List < CrNoMergeDtlVO > lstCrMerge = (List < CrNoMergeDtlVO > ) map.get(MrdConfig.PATIENT_CR_MERGE_LIST);
	        if (lstCrMerge != null && lstCrMerge.size() > 0)
	            patientSessionVO.setIsMainCrNo("1");
	        else
	            patientSessionVO.setIsMainCrNo("0");

	        //session.setAttribute(MrdConfig.PATIENT_DTL_VO, patientSessionVO);
	        WebUTIL.setAttributeInSession(_request, MrdConfig.PATIENT_DTL_VO, patientSessionVO);
	        HelperMethods.populatetToNullOrEmpty(_fb, patientSessionVO);

	        //			if()


	        configureDetail(_fb, _request);
	    } catch (HisRecordNotFoundException e) {
	        e.printStackTrace();
	    } catch (HisDataAccessException e) {
	        objStatus.add(Status.ERROR_DA, e.getMessage(), "");
	        e.printStackTrace();
	    } catch (HisApplicationExecutionException e) {
	        objStatus.add(Status.ERROR_AE, e.getMessage(), "");
	        e.printStackTrace();
	    } catch (HisException e) {
	        objStatus.add(Status.ERROR, e.getMessage(), "");
	        e.printStackTrace();
	    } finally {
	        WebUTIL.setStatus(_request, objStatus);

	    }
	}
	

	// get the details of the patient allergies
	public static void getAllergiesDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		EpisodeAllergiesVO[] episodeAllergiesVOs = null;
		try {
			UserVO userVO = getUserVO(_request);
			// String[] visitDetails=(_fb.getSendVisitDetail()).split("$");
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY);
			EpisodeAllergiesVO episodeAllergiesVO = new EpisodeAllergiesVO();
			episodeAllergiesVO.setPatCrNo(_fb.getPatCrNo());
			episodeAllergiesVO.setEpisodeCode(_fb.getEpisodeCode());
			episodeAllergiesVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			episodeAllergiesVOs = (EpisodeAllergiesVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY, _request);
			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// episodeAllergiesVOs==null && hasAccess)
			{
				episodeAllergiesVOs = EmrCommonDeskDATA.getEpisodeAllergiesAll(
						episodeAllergiesVO, departmentUnitArray, accessType,
						userVO);
				setPatientRecordMap(episodeAllergiesVO.getPatCrNo(),
						MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
						episodeAllergiesVOs, _request);
			}

			List episodeAllergiesVOsVisitList = new ArrayList();
			EpisodeAllergiesVO[] episodeAllergiesVOsVisitArray = null;
			if (episodeAllergiesVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (episodeAllergiesVOs[i].getPatAdmNo() == null || episodeAllergiesVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (episodeAllergiesVOs[i].getPatAdmNo() != null && !episodeAllergiesVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (episodeAllergiesVOs[i].getPatAdmNo() == null || episodeAllergiesVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeAllergiesVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (episodeAllergiesVOs[i].getPatAdmNo() == null || episodeAllergiesVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeAllergiesVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (episodeAllergiesVOs[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (episodeAllergiesVOs[i].getPatAdmNo() == null || episodeAllergiesVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeAllergiesVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						if ((episodeAllergiesVOs[i].getPatAdmNo() != null && !episodeAllergiesVOs[i]
								.getPatAdmNo().trim().equals(""))
								&& (episodeAllergiesVOs[i].getPatAdmNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (episodeAllergiesVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAllergiesVOsVisitList
									.add(episodeAllergiesVOs[i]);
						}
					}

					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				} else {
					for (int i = 0; i < episodeAllergiesVOs.length; i++) {
						episodeAllergiesVOsVisitList
								.add(episodeAllergiesVOs[i]);
					}
					episodeAllergiesVOsVisitArray = new EpisodeAllergiesVO[episodeAllergiesVOsVisitList
							.size()];
					episodeAllergiesVOsVisitArray = (EpisodeAllergiesVO[]) episodeAllergiesVOsVisitList
							.toArray(episodeAllergiesVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
							episodeAllergiesVOsVisitArray);
				}
			}

			/*
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
			 * i=0;i<episodeAllergiesVOs.length;i++) { if(
			 * (episodeAllergiesVOs[i
			 * ].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (episodeAllergiesVOs[i].getPatAdmNo()==null) ) {
			 * episodeAllergiesVOsVisitList.add(episodeAllergiesVOs[i]); } }
			 * 
			 * episodeAllergiesVOsVisitArray=new
			 * EpisodeAllergiesVO[episodeAllergiesVOsVisitList.size()];
			 * episodeAllergiesVOsVisitArray
			 * =(EpisodeAllergiesVO[])episodeAllergiesVOsVisitList
			 * .toArray(episodeAllergiesVOsVisitArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
			 * episodeAllergiesVOsVisitArray); }
			 * 
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) { for(int
			 * i=0;i<episodeAllergiesVOs.length;i++) { if(
			 * ((episodeAllergiesVOs[
			 * i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (episodeAllergiesVOs
			 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))) &&
			 * (episodeAllergiesVOs[i].getPatAdmNo()==null) ) {
			 * episodeAllergiesVOsVisitList.add(episodeAllergiesVOs[i]); } }
			 * 
			 * episodeAllergiesVOsVisitArray=new
			 * EpisodeAllergiesVO[episodeAllergiesVOsVisitList.size()];
			 * episodeAllergiesVOsVisitArray
			 * =(EpisodeAllergiesVO[])episodeAllergiesVOsVisitList
			 * .toArray(episodeAllergiesVOsVisitArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
			 * episodeAllergiesVOsVisitArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) { for(int
			 * i=0;i<episodeAllergiesVOs.length;i++) { if(
			 * (episodeAllergiesVOs[i
			 * ].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
			 * (episodeAllergiesVOs[i].getPatAdmNo()==null) ) {
			 * episodeAllergiesVOsVisitList.add(episodeAllergiesVOs[i]); } }
			 * 
			 * episodeAllergiesVOsVisitArray=new
			 * EpisodeAllergiesVO[episodeAllergiesVOsVisitList.size()];
			 * episodeAllergiesVOsVisitArray
			 * =(EpisodeAllergiesVO[])episodeAllergiesVOsVisitList
			 * .toArray(episodeAllergiesVOsVisitArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
			 * episodeAllergiesVOsVisitArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<episodeAllergiesVOs.length;i++) { if(
			 * (episodeAllergiesVOs[i].getPatAdmNo()!=null) &&
			 * (episodeAllergiesVOs
			 * [i].getPatAdmNo().equals(_fb.getSelectedPatAdmNo())) ) {
			 * episodeAllergiesVOsVisitList.add(episodeAllergiesVOs[i]); } }
			 * 
			 * episodeAllergiesVOsVisitArray=new
			 * EpisodeAllergiesVO[episodeAllergiesVOsVisitList.size()];
			 * episodeAllergiesVOsVisitArray
			 * =(EpisodeAllergiesVO[])episodeAllergiesVOsVisitList
			 * .toArray(episodeAllergiesVOsVisitArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
			 * episodeAllergiesVOsVisitArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * //episodeAllergiesVOs=episodeAllergiesVOs;
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,
			 * episodeAllergiesVOs); }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * get the detail of the patient chronic disease
	 * 
	 * @param _fb
	 * @param _request
	 */
	public static void getPatientChronicDisease(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		PatientAlertsDetailVO[] patientAlertsDetailVOs = null;
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_CHRONIC_DISEASE_ARRAY);
			PatientAlertsDetailVO patientAlertsDetailVO = new PatientAlertsDetailVO();
			patientAlertsDetailVO.setPatCrNo(_fb.getPatCrNo());

			patientAlertsDetailVOs = (PatientAlertsDetailVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_CHRONIC_DISEASE_ARRAY, _request);

			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}
			// if record is not already fetched and the user has access
			if (hasAccess)// patientAlertsDetailVOs==null && hasAccess)
			{
				// fetch the record according to the access policy and the data
				// of the departmentUnitArray
				patientAlertsDetailVOs = EmrCommonDeskDATA
						.getPatientAlertDetails(patientAlertsDetailVO,
								departmentUnitArray, accessType, userVO);
				setPatientRecordMap(patientAlertsDetailVO.getPatCrNo(),
						MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
						patientAlertsDetailVOs, _request);
			}

			// WebUTIL.setAttributeInSession(_request,MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
			// patientAlertsDetailVOs);

			List patAlertVOList = new ArrayList();
			PatientAlertsDetailVO[] patAlertVOArray = null;
			// set the record according to the selection of tree node
			if (patientAlertsDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientAlertsDetailVOs[i].getAdmissionNo() == null || patientAlertsDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientAlertsDetailVOs[i].getAdmissionNo() != null && !patientAlertsDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (patientAlertsDetailVOs[i].getAdmissionNo() == null || patientAlertsDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientAlertsDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientAlertsDetailVOs[i].getAdmissionNo() == null || patientAlertsDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientAlertsDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientAlertsDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patientAlertsDetailVOs[i].getAdmissionNo() == null || patientAlertsDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientAlertsDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						if ((patientAlertsDetailVOs[i].getAdmissionNo() != null && !patientAlertsDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patientAlertsDetailVOs[i].getAdmissionNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (patientAlertsDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patAlertVOList.add(patientAlertsDetailVOs[i]);
						}
					}

					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				} else {
					for (int i = 0; i < patientAlertsDetailVOs.length; i++) {
						patAlertVOList.add(patientAlertsDetailVOs[i]);
					}
					patAlertVOArray = new PatientAlertsDetailVO[patAlertVOList
							.size()];
					patAlertVOArray = (PatientAlertsDetailVO[]) patAlertVOList
							.toArray(patAlertVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_CHRONIC_DISEASE_ARRAY,
							patAlertVOArray);
				}

				/*
				 * //If the user selects the Episode Wise Option if(
				 * ((_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
				 * i=0;i<patientAlertsDetailVOs.length;i++) { if(
				 * (patientAlertsDetailVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (patientAlertsDetailVOs[i].getAdmissionNo()==null) ) {
				 * patAlertVOList.add(patientAlertsDetailVOs[i]); } }
				 * 
				 * patAlertVOArray=new
				 * PatientAlertsDetailVO[patAlertVOList.size()];
				 * patAlertVOArray=
				 * (PatientAlertsDetailVO[])patAlertVOList.toArray
				 * (patAlertVOArray);
				 * WebUTIL.setAttributeInSession(_request,MrdConfig
				 * .PAT_CHRONIC_DISEASE_ARRAY, patAlertVOArray); }
				 * 
				 * //If the user selects the a particular visit of the episode
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("V") )) { for(int
				 * i=0;i<patientAlertsDetailVOs.length;i++) { if(
				 * ((patientAlertsDetailVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (patientAlertsDetailVOs
				 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))) &&
				 * (patientAlertsDetailVOs[i].getAdmissionNo()==null) ) {
				 * patAlertVOList.add(patientAlertsDetailVOs[i]); } }
				 * 
				 * patAlertVOArray=new
				 * PatientAlertsDetailVO[patAlertVOList.size()];
				 * patAlertVOArray=
				 * (PatientAlertsDetailVO[])patAlertVOList.toArray
				 * (patAlertVOArray);
				 * WebUTIL.setAttributeInSession(_request,MrdConfig
				 * .PAT_CHRONIC_DISEASE_ARRAY, patAlertVOArray); }
				 * 
				 * //If the user selects the a particular department Unit if(
				 * (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("U") )) { for(int
				 * i=0;i<patientAlertsDetailVOs.length;i++) { if(
				 * (patientAlertsDetailVOs
				 * [i].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
				 * (patientAlertsDetailVOs[i].getAdmissionNo()==null) ) {
				 * patAlertVOList.add(patientAlertsDetailVOs[i]); } }
				 * 
				 * patAlertVOArray=new
				 * PatientAlertsDetailVO[patAlertVOList.size()];
				 * patAlertVOArray=
				 * (PatientAlertsDetailVO[])patAlertVOList.toArray
				 * (patAlertVOArray);
				 * WebUTIL.setAttributeInSession(_request,MrdConfig
				 * .PAT_CHRONIC_DISEASE_ARRAY, patAlertVOArray); }
				 * 
				 * //If the user selects the a particular admission of the ipd
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("I") )) { for(int
				 * i=0;i<patientAlertsDetailVOs.length;i++) { if(
				 * (patientAlertsDetailVOs[i].getAdmissionNo()!=null) &&
				 * (patientAlertsDetailVOs
				 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo())) ) {
				 * patAlertVOList.add(patientAlertsDetailVOs[i]); } }
				 * 
				 * patAlertVOArray=new
				 * PatientAlertsDetailVO[patAlertVOList.size()];
				 * patAlertVOArray=
				 * (PatientAlertsDetailVO[])patAlertVOList.toArray
				 * (patAlertVOArray);
				 * WebUTIL.setAttributeInSession(_request,MrdConfig
				 * .PAT_CHRONIC_DISEASE_ARRAY, patAlertVOArray); }
				 * 
				 * //If the user selects the all option from the tree if(
				 * (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("A") )) { for(int
				 * i=0;i<patientAlertsDetailVOs.length;i++) { if(
				 * (patientAlertsDetailVOs[i].getEffectiveTo()==null)) {
				 * patAlertVOList.add(patientAlertsDetailVOs[i]); } }
				 * 
				 * patAlertVOArray=new
				 * PatientAlertsDetailVO[patAlertVOList.size()];
				 * patAlertVOArray=
				 * (PatientAlertsDetailVO[])patAlertVOList.toArray
				 * (patAlertVOArray);
				 * WebUTIL.setAttributeInSession(_request,MrdConfig
				 * .PAT_CHRONIC_DISEASE_ARRAY, patAlertVOArray); }
				 */
			}
		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * get all the diagnosis detail of the patient the record is filter
	 * according to the access policy for this tab
	 * 
	 * @param _fb
	 * @param _request
	 */
	public static void getDiagnosisDetailsVisitWise(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		EpisodeDiagnosisVO[] episodeDiagnosisVOs = null;
		// boolean hasAccess=true;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY);
			
			
			EpisodeDiagnosisVO episodeDiagnosisVO = new EpisodeDiagnosisVO();

			episodeDiagnosisVO.setEpisodeCode(_fb.getEpisodeCode());
			episodeDiagnosisVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			episodeDiagnosisVO.setPatCrNo(_fb.getPatCrNo());
			episodeDiagnosisVO.setHospitalCode(_fb.getHosCode());
			
			
			episodeDiagnosisVOs = (EpisodeDiagnosisVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY, _request);
			
			   
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;
			// List<EprTabAccessDtlVO>
			// eprTabAccessDtlVOList=(List)session.getAttribute(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST);

			// if(eprTabAccessDtlVOList!=null &&
			// eprTabAccessDtlVOList.size()>0){

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());

				// check whether the record is user bound or unit bound
				/*
				 * if(eprTabAccessDtlVOList.get(0).getAccessType().equals(MrdConfig
				 * .EPR_RECORD_ACCESS_USER_BOUND)){ for(int
				 * i=0;i<departmentUnitArray.length;i++){
				 * if(userVO.getSeatId().equals
				 * (departmentUnitArray[i].split("#")[0])){ hasAccess=true;
				 * accessType=MrdConfig.EPR_DISPLAY_ALL_RECORD_YES; break; } } }
				 */
				// hasAccess=isUserBound(_request, _fb.getTabId(),accessType);
				// }

				// add the own department unit
				/*
				 * if(departmentUnitArray==null) departmentUnitArray=new String
				 * [1]; else{ String [] temp=departmentUnitArray;
				 * departmentUnitArray=new String[departmentUnitArray.length+1];
				 * for(int i=0;i<temp.length;i++)
				 * departmentUnitArray[i]=temp[i]; }
				 * departmentUnitArray[departmentUnitArray
				 * .length-1]=(String)session
				 * .getAttribute("departmentUnitCode");
				 */
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// episodeDiagnosisVOs==null && hasAccess)
			{
				episodeDiagnosisVOs = EmrCommonDeskDATA.getAllDiagnosisDetails(
						episodeDiagnosisVO, departmentUnitArray, accessType,
						userVO);
				setPatientRecordMap(episodeDiagnosisVO.getPatCrNo(),
						MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
						episodeDiagnosisVOs, _request);
			}

			// set the list of the record according to selection (All, Unit
			// wise, Episode wise, Episode visit wise,admission no wise )
			List episodeDiagnosisVOsVisitList = new ArrayList();
			EpisodeDiagnosisVO[] episodeDiagnosisVOsVisitArray = null;
			if (episodeDiagnosisVOs != null) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (episodeDiagnosisVOs[i].getPatAdmNo() == null || episodeDiagnosisVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (episodeDiagnosisVOs[i].getPatAdmNo() != null && !episodeDiagnosisVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (episodeDiagnosisVOs[i].getPatAdmNo() == null || episodeDiagnosisVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeDiagnosisVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (episodeDiagnosisVOs[i].getPatAdmNo() == null || episodeDiagnosisVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeDiagnosisVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (episodeDiagnosisVOs[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (episodeDiagnosisVOs[i].getPatAdmNo() == null || episodeDiagnosisVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeDiagnosisVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						if ((episodeDiagnosisVOs[i].getPatAdmNo() != null && !episodeDiagnosisVOs[i]
								.getPatAdmNo().trim().equals(""))
								&& (episodeDiagnosisVOs[i].getPatAdmNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (episodeDiagnosisVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeDiagnosisVOsVisitList
									.add(episodeDiagnosisVOs[i]);
						}
					}

					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				} else {
					for (int i = 0; i < episodeDiagnosisVOs.length; i++) {
						episodeDiagnosisVOsVisitList
								.add(episodeDiagnosisVOs[i]);
					}
					episodeDiagnosisVOsVisitArray = new EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList
							.size()];
					episodeDiagnosisVOsVisitArray = (EpisodeDiagnosisVO[]) episodeDiagnosisVOsVisitList
							.toArray(episodeDiagnosisVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
							episodeDiagnosisVOsVisitArray);
				}

			}
		}

		/*
		 * if( (_fb.getSelectionCriteria()!=null) &&
		 * (_fb.getSelectionCriteria().equals("U") )) { for(int
		 * i=0;i<episodeDiagnosisVOs.length;i++) { if(
		 * (episodeDiagnosisVOs[i].getDepartmentUnitCode
		 * ().equals(_fb.getSelectedUnit())) &&
		 * (episodeDiagnosisVOs[i].getPatAdmNo()==null)) {
		 * episodeDiagnosisVOsVisitList.add(episodeDiagnosisVOs[i]); } }
		 * 
		 * episodeDiagnosisVOsVisitArray=new
		 * EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList.size()];
		 * episodeDiagnosisVOsVisitArray
		 * =(EpisodeDiagnosisVO[])episodeDiagnosisVOsVisitList
		 * .toArray(episodeDiagnosisVOsVisitArray);
		 * WebUTIL.setAttributeInSession
		 * (_request,MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
		 * episodeDiagnosisVOsVisitArray); }
		 * 
		 * if( (_fb.getSelectionCriteria()!=null) &&
		 * (_fb.getSelectionCriteria().equals("E") )) { for(int
		 * i=0;i<episodeDiagnosisVOs.length;i++) { if(
		 * (episodeDiagnosisVOs[i].getEpisodeCode
		 * ().equals(_fb.getEpisodeCode())) &&
		 * (episodeDiagnosisVOs[i].getPatAdmNo()==null) ) {
		 * episodeDiagnosisVOsVisitList.add(episodeDiagnosisVOs[i]); } }
		 * 
		 * episodeDiagnosisVOsVisitArray=new
		 * EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList.size()];
		 * episodeDiagnosisVOsVisitArray
		 * =(EpisodeDiagnosisVO[])episodeDiagnosisVOsVisitList
		 * .toArray(episodeDiagnosisVOsVisitArray);
		 * WebUTIL.setAttributeInSession
		 * (_request,MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
		 * episodeDiagnosisVOsVisitArray); }
		 * 
		 * if( (_fb.getSelectionCriteria()!=null) &&
		 * (_fb.getSelectionCriteria().equals("I") )) { for(int
		 * i=0;i<episodeDiagnosisVOs.length;i++) { if(
		 * (episodeDiagnosisVOs[i].getPatAdmNo()!=null) &&
		 * (episodeDiagnosisVOs[i
		 * ].getPatAdmNo().equals(_fb.getSelectedPatAdmNo()))) {
		 * episodeDiagnosisVOsVisitList.add(episodeDiagnosisVOs[i]); } }
		 * 
		 * episodeDiagnosisVOsVisitArray=new
		 * EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList.size()];
		 * episodeDiagnosisVOsVisitArray
		 * =(EpisodeDiagnosisVO[])episodeDiagnosisVOsVisitList
		 * .toArray(episodeDiagnosisVOsVisitArray);
		 * WebUTIL.setAttributeInSession
		 * (_request,MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
		 * episodeDiagnosisVOsVisitArray); }
		 * 
		 * if( (_fb.getSelectionCriteria()!=null) &&
		 * (_fb.getSelectionCriteria().equals("V") )) { for(int
		 * i=0;i<episodeDiagnosisVOs.length;i++) { if(
		 * ((episodeDiagnosisVOs[i].getEpisodeCode
		 * ().equals(_fb.getEpisodeCode())) &&
		 * (episodeDiagnosisVOs[i].getEpisodeVisitNo
		 * ().equals(_fb.getEpisodeVisitNo()))) &&
		 * (episodeDiagnosisVOs[i].getPatAdmNo()==null) ) {
		 * episodeDiagnosisVOsVisitList.add(episodeDiagnosisVOs[i]); } }
		 * 
		 * episodeDiagnosisVOsVisitArray=new
		 * EpisodeDiagnosisVO[episodeDiagnosisVOsVisitList.size()];
		 * episodeDiagnosisVOsVisitArray
		 * =(EpisodeDiagnosisVO[])episodeDiagnosisVOsVisitList
		 * .toArray(episodeDiagnosisVOsVisitArray);
		 * WebUTIL.setAttributeInSession
		 * (_request,MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
		 * episodeDiagnosisVOsVisitArray); }
		 * 
		 * } if( (_fb.getSelectionCriteria()!=null) &&
		 * (_fb.getSelectionCriteria().equals("A") )) {
		 * //episodeDiagnosisVOs=episodeDiagnosisVOs;
		 * WebUTIL.setAttributeInSession
		 * (_request,MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY,
		 * episodeDiagnosisVOs); } }
		 */

		catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static Object getPateintRecordMap(String _crNo, String _key,
			HttpServletRequest _request) {
		HttpSession session = WebUTIL.getSession(_request);
		String previousCrNo = (String) session
				.getAttribute(MrdConfig.PREVIOUS_CRNO);
		Map patientRecordMap = null;
		Object obj = null;
		if ((previousCrNo != null) && (previousCrNo.equals(_crNo))) {
			patientRecordMap = (Map) session.getAttribute(_crNo);
			obj = patientRecordMap.get(_key);
		} else {
			if (previousCrNo != null)
				session.removeAttribute(previousCrNo);
		}
		return obj;
	}

	public static void setPatientRecordMap(String _crNo, String _key,
			Object _obj, HttpServletRequest _request) {
		HttpSession session = _request.getSession();
		WebUTIL.setAttributeInSession(_request, MrdConfig.PREVIOUS_CRNO, _crNo);
		Map patientRecordMap = (Map) session.getAttribute(_crNo);
		if (patientRecordMap != null) {
			patientRecordMap.put(_key, _obj);
			WebUTIL.setAttributeInSession(_request, _crNo, patientRecordMap);
		} else {
			patientRecordMap = new HashMap();
			patientRecordMap.put(_key, _obj);
			WebUTIL.setAttributeInSession(_request, _crNo, patientRecordMap);
		}

	}

	/**
	 * get patient drug advice detail
	 * 
	 * @param _fb
	 * @param _request
	 */
	public static void getPateintAllDrugAdivceDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs = null;
		PatDrugTreatmentDetailVO[] patDrugTreatmentOfflineDetailVOs = null;
		PatDrugTreatmentDetailVO[] patExtTreatmentDetailVOs=null;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;

		try {
			UserVO userVO = getUserVO(_request);
			// String[] visitDetails=(_fb.getSendVisitDetail()).split("$");
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY);
			session.removeAttribute(MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY);
			PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = new PatDrugTreatmentDetailVO();
			patDrugTreatmentDetailVO.setPatCrNo(_fb.getPatCrNo());
			patDrugTreatmentDetailVO.setEpisodeCode(_fb.getEpisodeCode());
			patDrugTreatmentDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			patDrugTreatmentDetailVOs = (PatDrugTreatmentDetailVO[]) getPateintRecordMap((String) session.getAttribute("crNo"),	MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY, _request);
			patDrugTreatmentOfflineDetailVOs = (PatDrugTreatmentDetailVO[]) getPateintRecordMap((String) session.getAttribute("crNo"), MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,_request);

			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request, _fb.getTabId());
				// check whether the record is user bound or unit bound
				// hasAccess=isUserBound(_request, _fb.getTabId(),accessType);

				// add the own department unit
				/*
				 * if(departmentUnitArray==null) departmentUnitArray=new String
				 * [1]; else{ String [] temp=departmentUnitArray;
				 * departmentUnitArray=new String[departmentUnitArray.length+1];
				 * for(int i=0;i<temp.length;i++)
				 * departmentUnitArray[i]=temp[i]; }
				 * departmentUnitArray[departmentUnitArray
				 * .length-1]=(String)session
				 * .getAttribute("departmentUnitCode");
				 */

				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// (patDrugTreatmentDetailVOs == null ||
							// patDrugTreatmentOfflineDetailVOs == null) &&
							// hasAccess)
			{
				patDrugTreatmentDetailVOs = EmrCommonDeskDATA.getPateintDrugAdviceDetails(patDrugTreatmentDetailVO,	departmentUnitArray, accessType, userVO);
				
				
				//Added By Shweta for fetching External Treatment Detail on 15-May-2019
				patExtTreatmentDetailVOs=EmrCommonDeskDATA.getPateintExtTreatmentDetails(patDrugTreatmentDetailVO, departmentUnitArray, accessType, userVO);

				if (MrdConfig.MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW.equals(MrdConfig.MRD_EMR_TREATMENT_OFFLINE_DETAIL_SHOW_YES)) {
					patDrugTreatmentOfflineDetailVOs = EmrCommonDeskDATA.getPateintDrugAdviceOfflineDetails(patDrugTreatmentDetailVO, departmentUnitArray, accessType, userVO);
					
				}

				setPatientRecordMap(patDrugTreatmentDetailVO.getPatCrNo(), MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,patDrugTreatmentDetailVOs, _request);
				setPatientRecordMap(patDrugTreatmentDetailVO.getPatCrNo(), MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY, patDrugTreatmentOfflineDetailVOs, _request);
				
				
				//Added By Shweta for fetching External Treatment Detail on 15-May-2019
				_request.getSession().setAttribute(MrdConfig.PAT_EXT_TREATMENT_DETAILS_VO_ARRAY,patExtTreatmentDetailVOs );
			}

			List patTreatmentDrugDeyailsArrayList = new ArrayList();
			PatDrugTreatmentDetailVO[] patTreatmentDrugDeyailVoVistArray = null;
			if (patDrugTreatmentDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null) && (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patTreatmentDrugDeyailsArrayList.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,	MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,	patTreatmentDrugDeyailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)	&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i].getHospitalCode().equals(_fb.getHosCode())) && (patDrugTreatmentDetailVOs[i].getAdmissionNo() == null || patDrugTreatmentDetailVOs[i].getAdmissionNo().trim().equals(""))) {
							patTreatmentDrugDeyailsArrayList.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,	MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,	patTreatmentDrugDeyailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patDrugTreatmentDetailVOs[i]
										.getAdmissionNo() != null && !patDrugTreatmentDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patTreatmentDrugDeyailsArrayList
									.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList
							.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
							.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
							patTreatmentDrugDeyailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i]
								.getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (patDrugTreatmentDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugDeyailsArrayList
									.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList
							.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
							.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
							patTreatmentDrugDeyailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patDrugTreatmentDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugDeyailsArrayList
									.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList
							.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
							.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
							patTreatmentDrugDeyailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patDrugTreatmentDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patDrugTreatmentDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugDeyailsArrayList
									.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList
							.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
							.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
							patTreatmentDrugDeyailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						if ((patDrugTreatmentDetailVOs[i].getAdmissionNo() != null && !patDrugTreatmentDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentDetailVOs[i]
										.getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (patDrugTreatmentDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugDeyailsArrayList
									.add(patDrugTreatmentDetailVOs[i]);
						}
					}

					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList
							.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
							.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
							patTreatmentDrugDeyailVoVistArray);
				} else {
					for (int i = 0; i < patDrugTreatmentDetailVOs.length; i++) {
						patTreatmentDrugDeyailsArrayList
								.add(patDrugTreatmentDetailVOs[i]);
					}
					patTreatmentDrugDeyailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugDeyailsArrayList
							.size()];
					patTreatmentDrugDeyailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
							.toArray(patTreatmentDrugDeyailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
							patTreatmentDrugDeyailVoVistArray);
				}

				/*
				 * if (((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("U")))) { for (int i = 0;
				 * i < patDrugTreatmentDetailVOs.length; i++) { if
				 * (patDrugTreatmentDetailVOs[i].getDepartmentUnitCode() !=
				 * null) if
				 * ((patDrugTreatmentDetailVOs[i].getDepartmentUnitCode()
				 * .equals(_fb.getSelectedUnit())) &&
				 * (patDrugTreatmentDetailVOs[i].getAdmissionNo() == null)) {
				 * patTreatmentDrugDeyailsArrayList
				 * .add(patDrugTreatmentDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugDeyailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugDeyailsArrayList.size()];
				 * patTreatmentDrugDeyailVoVistArray =
				 * (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
				 * .toArray(patTreatmentDrugDeyailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugDeyailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("E"))) { for (int i = 0; i
				 * < patDrugTreatmentDetailVOs.length; i++) { if
				 * ((patDrugTreatmentDetailVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (patDrugTreatmentDetailVOs[i].getAdmissionNo() == null)) {
				 * patTreatmentDrugDeyailsArrayList
				 * .add(patDrugTreatmentDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugDeyailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugDeyailsArrayList.size()];
				 * patTreatmentDrugDeyailVoVistArray =
				 * (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
				 * .toArray(patTreatmentDrugDeyailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugDeyailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("I"))) { for (int i = 0; i
				 * < patDrugTreatmentDetailVOs.length; i++) { if
				 * ((patDrugTreatmentDetailVOs[i].getAdmissionNo() != null) &&
				 * (patDrugTreatmentDetailVOs
				 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo()))) {
				 * patTreatmentDrugDeyailsArrayList
				 * .add(patDrugTreatmentDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugDeyailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugDeyailsArrayList.size()];
				 * patTreatmentDrugDeyailVoVistArray =
				 * (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
				 * .toArray(patTreatmentDrugDeyailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugDeyailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("V"))) { for (int i = 0; i
				 * < patDrugTreatmentDetailVOs.length; i++) { if
				 * (((patDrugTreatmentDetailVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (patDrugTreatmentDetailVOs[i]
				 * .getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))) &&
				 * (patDrugTreatmentDetailVOs[i].getAdmissionNo() == null)) {
				 * patTreatmentDrugDeyailsArrayList
				 * .add(patDrugTreatmentDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugDeyailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugDeyailsArrayList.size()];
				 * patTreatmentDrugDeyailVoVistArray =
				 * (PatDrugTreatmentDetailVO[]) patTreatmentDrugDeyailsArrayList
				 * .toArray(patTreatmentDrugDeyailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugDeyailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("A"))) { //
				 * patDrugTreatmentDetailVOs=patDrugTreatmentDetailVOs;
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY,
				 * patDrugTreatmentDetailVOs); }
				 */
			}

			List patTreatmentDrugOfflineDetailsArrayList = new ArrayList();
			PatDrugTreatmentDetailVO[] patTreatmentDrugOfflineDetailVoVistArray = null;
			if (patDrugTreatmentOfflineDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getHospitalCode().equals(_fb.getHosCode()))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getHospitalCode().equals(_fb.getHosCode()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getHospitalCode().equals(_fb.getHosCode()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo() != null && !patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getEpisodeCode().equals(_fb.getEpisodeCode()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getEpisodeCode().equals(_fb.getEpisodeCode()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo() == null || patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
							;
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						if ((patDrugTreatmentOfflineDetailVOs[i]
								.getAdmissionNo() != null && !patDrugTreatmentOfflineDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (patDrugTreatmentOfflineDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patTreatmentDrugOfflineDetailsArrayList
									.add(patDrugTreatmentOfflineDetailVOs[i]);
						}
					}

					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				} else {
					for (int i = 0; i < patDrugTreatmentOfflineDetailVOs.length; i++) {
						patTreatmentDrugOfflineDetailsArrayList
								.add(patDrugTreatmentOfflineDetailVOs[i]);
					}
					patTreatmentDrugOfflineDetailVoVistArray = new PatDrugTreatmentDetailVO[patTreatmentDrugOfflineDetailsArrayList
							.size()];
					patTreatmentDrugOfflineDetailVoVistArray = (PatDrugTreatmentDetailVO[]) patTreatmentDrugOfflineDetailsArrayList
							.toArray(patTreatmentDrugOfflineDetailVoVistArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
							patTreatmentDrugOfflineDetailVoVistArray);
				}

				/*
				 * if (((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("U")))) { for (int i = 0;
				 * i < patDrugTreatmentOfflineDetailVOs.length; i++) { if
				 * (patDrugTreatmentOfflineDetailVOs[i].getDepartmentUnitCode()
				 * != null) if
				 * ((patDrugTreatmentOfflineDetailVOs[i].getDepartmentUnitCode
				 * ().equals(_fb.getSelectedUnit())) &&
				 * (patDrugTreatmentOfflineDetailVOs[i].getAdmissionNo() ==
				 * null)) { patTreatmentDrugOfflineDetailsArrayList.add(
				 * patDrugTreatmentOfflineDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugOfflineDetailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugOfflineDetailsArrayList.size()];
				 * patTreatmentDrugOfflineDetailVoVistArray =
				 * (PatDrugTreatmentDetailVO[])
				 * patTreatmentDrugOfflineDetailsArrayList
				 * .toArray(patTreatmentDrugOfflineDetailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugOfflineDetailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("E"))) { for (int i = 0; i
				 * < patDrugTreatmentOfflineDetailVOs.length; i++) { if
				 * ((patDrugTreatmentOfflineDetailVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (patDrugTreatmentOfflineDetailVOs[i].getAdmissionNo() ==
				 * null)) { patTreatmentDrugOfflineDetailsArrayList.add(
				 * patDrugTreatmentOfflineDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugOfflineDetailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugOfflineDetailsArrayList.size()];
				 * patTreatmentDrugOfflineDetailVoVistArray =
				 * (PatDrugTreatmentDetailVO[])
				 * patTreatmentDrugOfflineDetailsArrayList
				 * .toArray(patTreatmentDrugOfflineDetailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugOfflineDetailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("I"))) { for (int i = 0; i
				 * < patDrugTreatmentOfflineDetailVOs.length; i++) { if
				 * ((patDrugTreatmentOfflineDetailVOs[i].getAdmissionNo() !=
				 * null) &&
				 * (patDrugTreatmentOfflineDetailVOs[i].getAdmissionNo()
				 * .equals(_fb.getSelectedPatAdmNo()))) {
				 * patTreatmentDrugOfflineDetailsArrayList
				 * .add(patDrugTreatmentOfflineDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugOfflineDetailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugOfflineDetailsArrayList.size()];
				 * patTreatmentDrugOfflineDetailVoVistArray =
				 * (PatDrugTreatmentDetailVO[])
				 * patTreatmentDrugOfflineDetailsArrayList
				 * .toArray(patTreatmentDrugOfflineDetailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugOfflineDetailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("V"))) { for (int i = 0; i
				 * < patDrugTreatmentOfflineDetailVOs.length; i++) { if
				 * (((patDrugTreatmentOfflineDetailVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (patDrugTreatmentOfflineDetailVOs
				 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))) &&
				 * (patDrugTreatmentOfflineDetailVOs[i].getAdmissionNo() ==
				 * null)) { patTreatmentDrugOfflineDetailsArrayList.add(
				 * patDrugTreatmentOfflineDetailVOs[i]); } }
				 * 
				 * patTreatmentDrugOfflineDetailVoVistArray = new
				 * PatDrugTreatmentDetailVO
				 * [patTreatmentDrugOfflineDetailsArrayList.size()];
				 * patTreatmentDrugOfflineDetailVoVistArray =
				 * (PatDrugTreatmentDetailVO[])
				 * patTreatmentDrugOfflineDetailsArrayList
				 * .toArray(patTreatmentDrugOfflineDetailVoVistArray);
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
				 * patTreatmentDrugOfflineDetailVoVistArray); } if
				 * ((_fb.getSelectionCriteria() != null) &&
				 * (_fb.getSelectionCriteria().equals("A"))) { //
				 * patDrugTreatmentOfflineDetailVOs
				 * =patDrugTreatmentOfflineDetailVOs;
				 * WebUTIL.setAttributeInSession(_request,
				 * MrdConfig.PAT_DRUG_ADVICE_OFFLINE_DETAILS_VO_ARRAY,
				 * patDrugTreatmentOfflineDetailVOs); }
				 */
			}

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getPatientInvestigationDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		ProfileInvestigationVO[] profileInvestigationVOs = null;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;

		try {
			UserVO userVO = getUserVO(_request);
			// String[] visitDetails=(_fb.getSendVisitDetail()).split("$");
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY);
			ProfileInvestigationVO profileInvestigationVO = new ProfileInvestigationVO();
			profileInvestigationVO.setPatCrNo(_fb.getPatCrNo());
			profileInvestigationVO.setEpisodeCode(_fb.getEpisodeCode());
			profileInvestigationVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			profileInvestigationVOs = (ProfileInvestigationVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY, _request);

			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				// check whether the record is user bound or unit bound
				hasAccess = isUserBound(_request, _fb.getTabId(), accessType);
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// && profileInvestigationVOs==null)
			{
				profileInvestigationVOs = EmrCommonDeskDATA
						.getPatientInvestigationDetails(profileInvestigationVO,
								departmentUnitArray, accessType, userVO);
				setPatientRecordMap(profileInvestigationVO.getPatCrNo(),
						MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
						profileInvestigationVOs, _request);
			}

			List patInvestigationArrayList = new ArrayList();
			ProfileInvestigationVO[] patientProfileInvestigationVOVisitArray = null;
			if ((profileInvestigationVOs != null)
					&& (profileInvestigationVOs.length > 0)) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (profileInvestigationVOs[i].getPatAdmNo() == null || profileInvestigationVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (profileInvestigationVOs[i].getPatAdmNo() != null && !profileInvestigationVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (profileInvestigationVOs[i].getPatAdmNo() == null || profileInvestigationVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (profileInvestigationVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (profileInvestigationVOs[i].getPatAdmNo() == null || profileInvestigationVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (profileInvestigationVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (profileInvestigationVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (profileInvestigationVOs[i].getPatAdmNo() == null || profileInvestigationVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (profileInvestigationVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						if ((profileInvestigationVOs[i].getPatAdmNo() != null && !profileInvestigationVOs[i]
								.getPatAdmNo().trim().equals(""))
								&& (profileInvestigationVOs[i].getPatAdmNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (profileInvestigationVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patInvestigationArrayList
									.add(profileInvestigationVOs[i]);
						}
					}

					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				} else {
					for (int i = 0; i < profileInvestigationVOs.length; i++) {
						patInvestigationArrayList
								.add(profileInvestigationVOs[i]);
					}
					patientProfileInvestigationVOVisitArray = new ProfileInvestigationVO[patInvestigationArrayList
							.size()];
					patientProfileInvestigationVOVisitArray = (ProfileInvestigationVO[]) patInvestigationArrayList
							.toArray(patientProfileInvestigationVOVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
							patientProfileInvestigationVOVisitArray);
				}

				/*
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("V") )) { for(int
				 * i=0;i<profileInvestigationVOs.length;i++) { if(
				 * ((profileInvestigationVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (profileInvestigationVOs
				 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))) &&
				 * (profileInvestigationVOs[i].getPatAdmNo()==null) ) {
				 * patInvestigationArrayList.add(profileInvestigationVOs[i]); }
				 * }
				 * 
				 * patientProfileInvestigationVOVisitArray=new
				 * ProfileInvestigationVO[patInvestigationArrayList.size()];
				 * patientProfileInvestigationVOVisitArray
				 * =(ProfileInvestigationVO[])patInvestigationArrayList.toArray(
				 * patientProfileInvestigationVOVisitArray);
				 * WebUTIL.setAttributeInSession
				 * (_request,MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
				 * patientProfileInvestigationVOVisitArray); }
				 * 
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("E") )) { for(int
				 * i=0;i<profileInvestigationVOs.length;i++) { if(
				 * (profileInvestigationVOs
				 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
				 * (profileInvestigationVOs[i].getPatAdmNo()==null) ) {
				 * patInvestigationArrayList.add(profileInvestigationVOs[i]); }
				 * }
				 * 
				 * patientProfileInvestigationVOVisitArray=new
				 * ProfileInvestigationVO[patInvestigationArrayList.size()];
				 * patientProfileInvestigationVOVisitArray
				 * =(ProfileInvestigationVO[])patInvestigationArrayList.toArray(
				 * patientProfileInvestigationVOVisitArray);
				 * WebUTIL.setAttributeInSession
				 * (_request,MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
				 * patientProfileInvestigationVOVisitArray); }
				 * 
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("I") )) { for(int
				 * i=0;i<profileInvestigationVOs.length;i++) { if(
				 * (profileInvestigationVOs[i].getPatAdmNo()!=null) &&
				 * (profileInvestigationVOs
				 * [i].getPatAdmNo().equals(_fb.getSelectedPatAdmNo()))) {
				 * patInvestigationArrayList.add(profileInvestigationVOs[i]); }
				 * }
				 * 
				 * patientProfileInvestigationVOVisitArray=new
				 * ProfileInvestigationVO[patInvestigationArrayList.size()];
				 * patientProfileInvestigationVOVisitArray
				 * =(ProfileInvestigationVO[])patInvestigationArrayList.toArray(
				 * patientProfileInvestigationVOVisitArray);
				 * WebUTIL.setAttributeInSession
				 * (_request,MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
				 * patientProfileInvestigationVOVisitArray); }
				 * 
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("U") )) { for(int
				 * i=0;i<profileInvestigationVOs.length;i++) { if(
				 * (profileInvestigationVOs
				 * [i].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
				 * (profileInvestigationVOs[i].getPatAdmNo()==null) ) {
				 * patInvestigationArrayList.add(profileInvestigationVOs[i]); }
				 * }
				 * 
				 * patientProfileInvestigationVOVisitArray=new
				 * ProfileInvestigationVO[patInvestigationArrayList.size()];
				 * patientProfileInvestigationVOVisitArray
				 * =(ProfileInvestigationVO[])patInvestigationArrayList.toArray(
				 * patientProfileInvestigationVOVisitArray);
				 * WebUTIL.setAttributeInSession
				 * (_request,MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
				 * patientProfileInvestigationVOVisitArray); }
				 * 
				 * if( (_fb.getSelectionCriteria()!=null) &&
				 * (_fb.getSelectionCriteria().equals("A") )) {
				 * //profileInvestigationVOs=profileInvestigationVOs;
				 * WebUTIL.setAttributeInSession
				 * (_request,MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY,
				 * profileInvestigationVOs); }
				 */

			}
		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getPatientIntakeOutakeDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		// PatIntakeOutDtlVO[] patIntakeOutDtlVOs=null;
		Map patIntakeOutakeMap = null;
		double totalOuttake = 0.0;
		double totalIntake = 0.0;

		boolean isAccessRestricted = true;
		boolean hasAccess = true;
		try {
			UserVO userVO = getUserVO(_request);
			// String[] visitDetails=(_fb.getSendVisitDetail()).split("$");
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_INTAKE_DETAIL_ARRAY);
			session.removeAttribute(MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY);
			PatientDetailVO dailyPatVO = new PatientDetailVO();
			dailyPatVO.setPatCrNo(_fb.getPatCrNo());
			dailyPatVO.setEpisodeCode(_fb.getEpisodeCode());
			dailyPatVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			dailyPatVO.setPatAdmNo(_fb.getSelectedPatAdmNo());
			dailyPatVO.setHospitalCode(_fb.getHosCode());
			_fb.setTotalViewIntake("");
			_fb.setTotalViewOuttake("");

			patIntakeOutakeMap = (Map) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_INTAKE_OUTTAKE_MAP, _request);

			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());

				// check whether the record is user bound or unit bound
				hasAccess = isUserBound(_request, _fb.getTabId(), accessType);
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}
			if (hasAccess)// patIntakeOutakeMap==null && hasAccess)
			{
				patIntakeOutakeMap = EmrCommonDeskDATA.getOutParaDetail(
						dailyPatVO, departmentUnitArray, accessType, userVO);
				setPatientRecordMap(dailyPatVO.getPatCrNo(),
						MrdConfig.PAT_INTAKE_OUTTAKE_MAP, patIntakeOutakeMap,
						_request);
			}
			if ((patIntakeOutakeMap != null)) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					PatIntakeOutDtlVO[] patIntakeOutDtlVOOuttake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY);
					PatIntakeOutDtlVO[] patIntakeOutDtlVOIntake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_INTAKE_DETAIL_ARRAY);

					List patOuttake = new ArrayList();
					List patInatake = new ArrayList();

					if (patIntakeOutDtlVOOuttake != null) {
						for (int i = 0; i < patIntakeOutDtlVOOuttake.length; i++) {
							if (patIntakeOutDtlVOOuttake[i].getHospitalCode()
									.equals(_fb.getHosCode())) {
								patOuttake.add(patIntakeOutDtlVOOuttake[i]);
								totalOuttake = totalOuttake
										+ (Double
												.parseDouble(patIntakeOutDtlVOOuttake[i]
														.getVolume()));
							}

						}
						PatIntakeOutDtlVO[] patOuttakeAdmisionWise = new PatIntakeOutDtlVO[patOuttake
								.size()];
						patOuttakeAdmisionWise = (PatIntakeOutDtlVO[]) patOuttake
								.toArray(patOuttakeAdmisionWise);
						_fb.setTotalViewOuttake(String.valueOf(totalOuttake));
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY,
								patOuttakeAdmisionWise);
					}
					if (patIntakeOutDtlVOIntake != null) {
						for (int i = 0; i < patIntakeOutDtlVOIntake.length; i++) {
							if (patIntakeOutDtlVOIntake[i].getHospitalCode()
									.equals(_fb.getHosCode())) {
								patInatake.add(patIntakeOutDtlVOIntake[i]);
								totalIntake = totalIntake
										+ (Double
												.parseDouble(patIntakeOutDtlVOIntake[i]
														.getVolume()));
							}

						}
						PatIntakeOutDtlVO[] patIntakeAdmisionWise = new PatIntakeOutDtlVO[patInatake
								.size()];
						patIntakeAdmisionWise = (PatIntakeOutDtlVO[]) patInatake
								.toArray(patIntakeAdmisionWise);
						_fb.setTotalViewIntake(String.valueOf(totalIntake));
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_INTAKE_DETAIL_ARRAY,
								patIntakeAdmisionWise);
					}

				}

				else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					PatIntakeOutDtlVO[] patIntakeOutDtlVOOuttake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY);
					PatIntakeOutDtlVO[] patIntakeOutDtlVOIntake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_INTAKE_DETAIL_ARRAY);

					List patOuttake = new ArrayList();
					List patInatake = new ArrayList();

					if (patIntakeOutDtlVOOuttake != null) {
						for (int i = 0; i < patIntakeOutDtlVOOuttake.length; i++) {
							if ((dailyPatVO.getPatAdmNo() != null)
									&& (patIntakeOutDtlVOOuttake[i]
											.getPatAdmNo() != null)
									&& (patIntakeOutDtlVOOuttake[i]
											.getPatAdmNo().equals(dailyPatVO
											.getPatAdmNo()))
									&& (patIntakeOutDtlVOOuttake[i]
											.getHospitalCode().equals(_fb
											.getHosCode()))) {
								patOuttake.add(patIntakeOutDtlVOOuttake[i]);
								totalOuttake = totalOuttake
										+ (Double
												.parseDouble(patIntakeOutDtlVOOuttake[i]
														.getVolume()));
							}

						}
						PatIntakeOutDtlVO[] patOuttakeAdmisionWise = new PatIntakeOutDtlVO[patOuttake
								.size()];
						patOuttakeAdmisionWise = (PatIntakeOutDtlVO[]) patOuttake
								.toArray(patOuttakeAdmisionWise);
						_fb.setTotalViewOuttake(String.valueOf(totalOuttake));
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY,
								patOuttakeAdmisionWise);
					}
					if (patIntakeOutDtlVOIntake != null) {
						for (int i = 0; i < patIntakeOutDtlVOIntake.length; i++) {
							if ((dailyPatVO.getPatAdmNo() != null)
									&& (patIntakeOutDtlVOIntake[i]
											.getPatAdmNo() != null)
									&& (patIntakeOutDtlVOIntake[i]
											.getPatAdmNo().equals(dailyPatVO
											.getPatAdmNo()))
									&& (patIntakeOutDtlVOIntake[i]
											.getHospitalCode().equals(_fb
											.getHosCode()))) {
								patInatake.add(patIntakeOutDtlVOIntake[i]);
								totalIntake = totalIntake
										+ (Double
												.parseDouble(patIntakeOutDtlVOIntake[i]
														.getVolume()));
							}

						}
						PatIntakeOutDtlVO[] patIntakeAdmisionWise = new PatIntakeOutDtlVO[patInatake
								.size()];
						patIntakeAdmisionWise = (PatIntakeOutDtlVO[]) patInatake
								.toArray(patIntakeAdmisionWise);
						_fb.setTotalViewIntake(String.valueOf(totalIntake));
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_INTAKE_DETAIL_ARRAY,
								patIntakeAdmisionWise);
					}

				}
				// if(
				// (profileInvestigationVOs[i].getHospitalCode().equals(_fb.getHosCode()))
				// && (profileInvestigationVOs[i].getPatAdmNo()!=null &&
				// !profileInvestigationVOs[i].getPatAdmNo().trim().equals(""))
				// )
				else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					PatIntakeOutDtlVO[] patIntakeOutDtlVOOuttake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY);
					PatIntakeOutDtlVO[] patIntakeOutDtlVOIntake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_INTAKE_DETAIL_ARRAY);

					List patOuttake = new ArrayList();
					List patInatake = new ArrayList();

					if (patIntakeOutDtlVOOuttake != null) {
						for (int i = 0; i < patIntakeOutDtlVOOuttake.length; i++) {
							if ((patIntakeOutDtlVOOuttake[i].getPatAdmNo() != null)
									&& (patIntakeOutDtlVOOuttake[i]
											.getHospitalCode().equals(_fb
											.getHosCode()))) {
								patOuttake.add(patIntakeOutDtlVOOuttake[i]);
								totalOuttake = totalOuttake
										+ (Double
												.parseDouble(patIntakeOutDtlVOOuttake[i]
														.getVolume()));
							}

						}
						PatIntakeOutDtlVO[] patOuttakeAdmisionWise = new PatIntakeOutDtlVO[patOuttake
								.size()];
						patOuttakeAdmisionWise = (PatIntakeOutDtlVO[]) patOuttake
								.toArray(patOuttakeAdmisionWise);
						_fb.setTotalViewOuttake(String.valueOf(totalOuttake));
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY,
								patOuttakeAdmisionWise);
					}
					if (patIntakeOutDtlVOIntake != null) {
						for (int i = 0; i < patIntakeOutDtlVOIntake.length; i++) {
							if ((patIntakeOutDtlVOIntake[i].getPatAdmNo() != null)
									&& (patIntakeOutDtlVOIntake[i]
											.getHospitalCode().equals(_fb
											.getHosCode()))) {
								patInatake.add(patIntakeOutDtlVOIntake[i]);
								totalIntake = totalIntake
										+ (Double
												.parseDouble(patIntakeOutDtlVOIntake[i]
														.getVolume()));
							}

						}
						PatIntakeOutDtlVO[] patIntakeAdmisionWise = new PatIntakeOutDtlVO[patInatake
								.size()];
						patIntakeAdmisionWise = (PatIntakeOutDtlVO[]) patInatake
								.toArray(patIntakeAdmisionWise);
						_fb.setTotalViewIntake(String.valueOf(totalIntake));
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_INTAKE_DETAIL_ARRAY,
								patIntakeAdmisionWise);
					}

				} else {
					PatIntakeOutDtlVO[] patIntakeOutDtlVOOuttake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY);
					PatIntakeOutDtlVO[] patIntakeOutDtlVOIntake = (PatIntakeOutDtlVO[]) patIntakeOutakeMap
							.get(MrdConfig.PAT_INTAKE_DETAIL_ARRAY);
					if (patIntakeOutDtlVOOuttake != null) {
						for (int i = 0; i < patIntakeOutDtlVOOuttake.length; i++) {
							totalOuttake = totalOuttake
									+ (Double
											.parseDouble(patIntakeOutDtlVOOuttake[i]
													.getVolume()));
						}
						_fb.setTotalViewOuttake(String.valueOf(totalOuttake));
					}
					if (patIntakeOutDtlVOIntake != null) {
						for (int i = 0; i < patIntakeOutDtlVOIntake.length; i++) {
							totalIntake = totalIntake
									+ (Double
											.parseDouble(patIntakeOutDtlVOIntake[i]
													.getVolume()));
						}
						_fb.setTotalViewIntake(String.valueOf(totalIntake));
					}
					WebUTIL.setMapInSession(patIntakeOutakeMap, _request);
				}

			}

			/*
			 * if( (patIntakeOutakeMap!=null) ) {
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) && (
			 * _fb.getSelectionCriteria().equals("I") ) ) { PatIntakeOutDtlVO[]
			 * patIntakeOutDtlVOOuttake
			 * =(PatIntakeOutDtlVO[])patIntakeOutakeMap.get
			 * (MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY); PatIntakeOutDtlVO[]
			 * patIntakeOutDtlVOIntake
			 * =(PatIntakeOutDtlVO[])patIntakeOutakeMap.get
			 * (MrdConfig.PAT_INTAKE_DETAIL_ARRAY);
			 * 
			 * List patOuttake=new ArrayList(); List patInatake=new ArrayList();
			 * 
			 * if(patIntakeOutDtlVOOuttake!=null) { for(int
			 * i=0;i<patIntakeOutDtlVOOuttake.length;i++) { if(
			 * (dailyPatVO.getPatAdmNo()!=null) &&
			 * (patIntakeOutDtlVOOuttake[i].getPatAdmNo()!=null) &&
			 * (patIntakeOutDtlVOOuttake
			 * [i].getPatAdmNo().equals(dailyPatVO.getPatAdmNo())) ) {
			 * patOuttake.add(patIntakeOutDtlVOOuttake[i]);
			 * totalOuttake=totalOuttake
			 * +(Double.parseDouble(patIntakeOutDtlVOOuttake[i].getVolume())); }
			 * 
			 * } PatIntakeOutDtlVO[] patOuttakeAdmisionWise=new
			 * PatIntakeOutDtlVO[patOuttake.size()];
			 * patOuttakeAdmisionWise=(PatIntakeOutDtlVO
			 * [])patOuttake.toArray(patOuttakeAdmisionWise);
			 * _fb.setTotalViewOuttake(String.valueOf(totalOuttake));
			 * WebUTIL.setAttributeInSession(_request,
			 * MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY, patOuttakeAdmisionWise); }
			 * if(patIntakeOutDtlVOIntake!=null) { for(int
			 * i=0;i<patIntakeOutDtlVOIntake.length;i++) { if(
			 * (dailyPatVO.getPatAdmNo()!=null) &&
			 * (patIntakeOutDtlVOIntake[i].getPatAdmNo()!=null) &&
			 * (patIntakeOutDtlVOIntake
			 * [i].getPatAdmNo().equals(dailyPatVO.getPatAdmNo())) ) {
			 * patInatake.add(patIntakeOutDtlVOIntake[i]);
			 * totalIntake=totalIntake
			 * +(Double.parseDouble(patIntakeOutDtlVOIntake[i].getVolume())); }
			 * 
			 * } PatIntakeOutDtlVO[] patIntakeAdmisionWise=new
			 * PatIntakeOutDtlVO[patInatake.size()];
			 * patIntakeAdmisionWise=(PatIntakeOutDtlVO
			 * [])patInatake.toArray(patIntakeAdmisionWise);
			 * _fb.setTotalViewIntake(String.valueOf(totalIntake));
			 * WebUTIL.setAttributeInSession(_request,
			 * MrdConfig.PAT_INTAKE_DETAIL_ARRAY, patIntakeAdmisionWise); }
			 * 
			 * }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) && (
			 * _fb.getSelectionCriteria().equals("A") ) ) { PatIntakeOutDtlVO[]
			 * patIntakeOutDtlVOOuttake
			 * =(PatIntakeOutDtlVO[])patIntakeOutakeMap.get
			 * (MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY); PatIntakeOutDtlVO[]
			 * patIntakeOutDtlVOIntake
			 * =(PatIntakeOutDtlVO[])patIntakeOutakeMap.get
			 * (MrdConfig.PAT_INTAKE_DETAIL_ARRAY);
			 * if(patIntakeOutDtlVOOuttake!=null) { for(int
			 * i=0;i<patIntakeOutDtlVOOuttake.length;i++) {
			 * totalOuttake=totalOuttake
			 * +(Double.parseDouble(patIntakeOutDtlVOOuttake[i].getVolume())); }
			 * _fb.setTotalViewOuttake(String.valueOf(totalOuttake)); }
			 * if(patIntakeOutDtlVOIntake!=null) { for(int
			 * i=0;i<patIntakeOutDtlVOIntake.length;i++) {
			 * totalIntake=totalIntake
			 * +(Double.parseDouble(patIntakeOutDtlVOIntake[i].getVolume())); }
			 * _fb.setTotalViewIntake(String.valueOf(totalIntake)); }
			 * WebUTIL.setMapInSession(patIntakeOutakeMap, _request); } }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient progress notes for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientProgressNotes(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		DoctorRoundDtlVO[] doctorRoundDtlVOs = null;
		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY);
			DoctorRoundDtlVO doctorRoundDtlVO = new DoctorRoundDtlVO();
			doctorRoundDtlVO.setPatCrNo(_fb.getPatCrNo());

			doctorRoundDtlVOs = (DoctorRoundDtlVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY, _request);
			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// doctorRoundDtlVOs==null && hasAccess)
			{
				doctorRoundDtlVOs = EmrCommonDeskDATA
						.getPatientProgressNotesEMR(doctorRoundDtlVO,
								departmentUnitArray, accessType, userVO);
				setPatientRecordMap(doctorRoundDtlVO.getPatCrNo(),
						MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
						doctorRoundDtlVOs, _request);
			}

			List patProgressNotesList = new ArrayList();
			DoctorRoundDtlVO[] patProgressNotesDocRoundDtlArray = null;

			if ((doctorRoundDtlVOs != null) && (doctorRoundDtlVOs.length > 0)) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (doctorRoundDtlVOs[i].getPatAdmNo() == null || doctorRoundDtlVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (doctorRoundDtlVOs[i].getPatAdmNo() != null && !doctorRoundDtlVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (doctorRoundDtlVOs[i].getPatAdmNo() == null || doctorRoundDtlVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (doctorRoundDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (doctorRoundDtlVOs[i].getPatAdmNo() == null || doctorRoundDtlVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (doctorRoundDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (doctorRoundDtlVOs[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (doctorRoundDtlVOs[i].getPatAdmNo() == null || doctorRoundDtlVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (doctorRoundDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						if ((doctorRoundDtlVOs[i].getPatAdmNo() != null && !doctorRoundDtlVOs[i]
								.getPatAdmNo().trim().equals(""))
								&& (doctorRoundDtlVOs[i].getPatAdmNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (doctorRoundDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patProgressNotesList.add(doctorRoundDtlVOs[i]);
						}
					}

					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				} else {
					for (int i = 0; i < doctorRoundDtlVOs.length; i++) {
						patProgressNotesList.add(doctorRoundDtlVOs[i]);
					}
					patProgressNotesDocRoundDtlArray = new DoctorRoundDtlVO[patProgressNotesList
							.size()];
					patProgressNotesDocRoundDtlArray = (DoctorRoundDtlVO[]) patProgressNotesList
							.toArray(patProgressNotesDocRoundDtlArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
							patProgressNotesDocRoundDtlArray);
				}

			}

			/*
			 * if(doctorRoundDtlVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<doctorRoundDtlVOs.length;i++) { if(
			 * (doctorRoundDtlVOs[i].getPatAdmNo()!=null) &&
			 * (doctorRoundDtlVOs[i
			 * ].getPatAdmNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patProgressNotesList.add(doctorRoundDtlVOs[i]); } }
			 * 
			 * patProgressNotesDocRoundDtlArray=new
			 * DoctorRoundDtlVO[patProgressNotesList.size()];
			 * patProgressNotesDocRoundDtlArray
			 * =(DoctorRoundDtlVO[])patProgressNotesList
			 * .toArray(patProgressNotesDocRoundDtlArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
			 * patProgressNotesDocRoundDtlArray); } }
			 * 
			 * if(doctorRoundDtlVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * //doctorRoundDtlVOs=doctorRoundDtlVOs;
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_PROGRESS_NOTES_DOC_ROUND_DTL_ARRAY,
			 * doctorRoundDtlVOs); } }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient vital details for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientVitalDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_VITAL_DETAIL_ARRAY);
			PatientClinicalDetailVO patientClinicalDetailVO = new PatientClinicalDetailVO();
			patientClinicalDetailVO.setPatCrNo(_fb.getPatCrNo());
			patientClinicalDetailVOs = (PatientClinicalDetailVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_VITAL_DETAIL_ARRAY, _request);

			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				// check whether the record is user bound or unit bound
				hasAccess = isUserBound(_request, _fb.getTabId(), accessType);
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// patientClinicalDetailVOs==null && hasAccess)
			{
				patientClinicalDetailVOs = EmrCommonDeskDATA
						.getPatientVitalDetails(patientClinicalDetailVO,
								departmentUnitArray, accessType, userVO);
				setPatientRecordMap(patientClinicalDetailVO.getPatCrNo(),
						MrdConfig.PAT_VITAL_DETAIL_ARRAY,
						patientClinicalDetailVOs, _request);
			}

			List patClinicalDetailsList = new ArrayList();
			PatientClinicalDetailVO[] patClinicalDetailsArray = null;
			if (patientClinicalDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() != null && !patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i]
								.getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getAdmissionNo() != null && !patientClinicalDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}
					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				} else {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						patClinicalDetailsList.add(patientClinicalDetailVOs[i]);
					}
					if (patClinicalDetailsList.size() > 0) {
						patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
								.size()];
						patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
								.toArray(patClinicalDetailsArray);
					} else
						patClinicalDetailsList = null;
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VITAL_DETAIL_ARRAY,
							patClinicalDetailsArray);
				}
			}

			/*
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs[i].getAdmissionNo()!=null) &&
			 * (patientClinicalDetailVOs
			 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PAT_VITAL_DETAIL_ARRAY, patClinicalDetailsArray);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * patClinicalDetailsArray=patientClinicalDetailVOs;
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_VITAL_DETAIL_ARRAY,
			 * patClinicalDetailsArray); } }
			 */
			String recordDate = "";
			// String paraId="";
			LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			Map<String, Map<String, String>> mpVitalChartData = new HashMap<String, Map<String, String>>();
			List<Entry> paraList = new ArrayList<Entry>();
			if ((patClinicalDetailsArray != null)
					&& (patClinicalDetailsArray.length > 0)) {
				Map<String, String> mapParaIDValue = null;
				for (int i = 0; i < patClinicalDetailsArray.length; i++) {
					String recordDateTemp = patClinicalDetailsArray[i]
							.getRecordDate();
					// String paraIdTemp=patClinicalDetailsArray[i].getParaId();

					if (!recordDateTemp.equals(recordDate)) {
						mapRecordDates.put(
								patClinicalDetailsArray[i].getRecordDate(),
								patClinicalDetailsArray[i].getRecordDate());
						mapParaIDValue = new HashMap<String, String>();
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
						mpVitalChartData.put(
								patClinicalDetailsArray[i].getRecordDate(),
								mapParaIDValue);
						recordDate = recordDateTemp;
					} else {
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
					}

					Entry entry = new Entry();
					entry.setValue(patClinicalDetailsArray[i].getParaId());
					entry.setLabel(patClinicalDetailsArray[i].getParaDesc());
					if (!paraList.contains(entry)) {
						paraList.add(entry);
					}
				}

				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);
				WebUTIL.setAttributeInSession(_request,
						GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST,
						paraList);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP,
						mapRecordDates);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP,
						mpVitalChartData);
			} else {
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);
				WebUTIL.setAttributeInSession(_request,
						GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST,
						null);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP,
						null);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP,
						null);
			}

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient external investigation details for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientExternalInvestigationDetails(
			EmrCommonDeskFB _fb, HttpServletRequest _request) {
		Status objStatus = new Status();
		EpisodeExtInvDtlVO[] episodeExtInvDtlVOs = null;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;

		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_EXT_INVESTIGATION_ARRAY);
			EpisodeExtInvDtlVO episodeExtInvDtlVO = new EpisodeExtInvDtlVO();
			episodeExtInvDtlVO.setPatCrNo(_fb.getPatCrNo());

			episodeExtInvDtlVOs = (EpisodeExtInvDtlVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_EXT_INVESTIGATION_ARRAY, _request);

			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());

				// check whether the record is user bound or unit bound
				hasAccess = isUserBound(_request, _fb.getTabId(), accessType);
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// episodeExtInvDtlVOs==null && hasAccess)
			{
				episodeExtInvDtlVOs = EmrCommonDeskDATA
						.getPatientExternalInvestigationDetails(
								episodeExtInvDtlVO, departmentUnitArray,
								accessType, userVO);
				for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
					String dt = episodeExtInvDtlVOs[i].getRecordDate();
					episodeExtInvDtlVOs[i].setRecordDate(dt.split(" ")[0]);
					episodeExtInvDtlVOs[i].setRecordTime(dt.split(" ")[1]);
				}
				setPatientRecordMap(episodeExtInvDtlVO.getPatCrNo(),
						MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
						episodeExtInvDtlVOs, _request);
			}

			List patExternalInvestigationList = new ArrayList();
			EpisodeExtInvDtlVO[] patExternalInvestigationDetailsArray = null;

			if (episodeExtInvDtlVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (episodeExtInvDtlVOs[i].getPatAdmNo() == null || episodeExtInvDtlVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (episodeExtInvDtlVOs[i].getPatAdmNo() != null && !episodeExtInvDtlVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (episodeExtInvDtlVOs[i].getPatAdmNo() == null || episodeExtInvDtlVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeExtInvDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (episodeExtInvDtlVOs[i].getPatAdmNo() == null || episodeExtInvDtlVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeExtInvDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (episodeExtInvDtlVOs[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (episodeExtInvDtlVOs[i].getPatAdmNo() == null || episodeExtInvDtlVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (episodeExtInvDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						if ((episodeExtInvDtlVOs[i].getPatAdmNo() != null && !episodeExtInvDtlVOs[i]
								.getPatAdmNo().trim().equals(""))
								&& (episodeExtInvDtlVOs[i].getPatAdmNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (episodeExtInvDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patExternalInvestigationList
									.add(episodeExtInvDtlVOs[i]);
						}
					}

					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				} else {
					for (int i = 0; i < episodeExtInvDtlVOs.length; i++) {
						patExternalInvestigationList
								.add(episodeExtInvDtlVOs[i]);
					}
					patExternalInvestigationDetailsArray = new EpisodeExtInvDtlVO[patExternalInvestigationList
							.size()];
					patExternalInvestigationDetailsArray = (EpisodeExtInvDtlVO[]) patExternalInvestigationList
							.toArray(patExternalInvestigationDetailsArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
							patExternalInvestigationDetailsArray);
				}
			}

			/*
			 * if( (episodeExtInvDtlVOs!=null) &&
			 * (episodeExtInvDtlVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) ) { for(int
			 * i=0;i<episodeExtInvDtlVOs.length;i++) { if(
			 * (episodeExtInvDtlVOs[i
			 * ].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
			 * (episodeExtInvDtlVOs[i].getPatAdmNo()==null) ) {
			 * patExternalInvestigationList.add(episodeExtInvDtlVOs[i]); } }
			 * 
			 * patExternalInvestigationDetailsArray=new
			 * EpisodeExtInvDtlVO[patExternalInvestigationList.size()];
			 * patExternalInvestigationDetailsArray
			 * =(EpisodeExtInvDtlVO[])patExternalInvestigationList
			 * .toArray(patExternalInvestigationDetailsArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
			 * patExternalInvestigationDetailsArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) { for(int
			 * i=0;i<episodeExtInvDtlVOs.length;i++) { if(
			 * (episodeExtInvDtlVOs[i
			 * ].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (episodeExtInvDtlVOs[i].getPatAdmNo()==null) ) {
			 * patExternalInvestigationList.add(episodeExtInvDtlVOs[i]); } }
			 * 
			 * patExternalInvestigationDetailsArray=new
			 * EpisodeExtInvDtlVO[patExternalInvestigationList.size()];
			 * patExternalInvestigationDetailsArray
			 * =(EpisodeExtInvDtlVO[])patExternalInvestigationList
			 * .toArray(patExternalInvestigationDetailsArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
			 * patExternalInvestigationDetailsArray); }
			 * 
			 * 
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) { for(int
			 * i=0;i<episodeExtInvDtlVOs.length;i++) { if(
			 * ((episodeExtInvDtlVOs[
			 * i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (episodeExtInvDtlVOs
			 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))) &&
			 * (episodeExtInvDtlVOs[i].getPatAdmNo()==null) ) {
			 * patExternalInvestigationList.add(episodeExtInvDtlVOs[i]); } }
			 * 
			 * patExternalInvestigationDetailsArray=new
			 * EpisodeExtInvDtlVO[patExternalInvestigationList.size()];
			 * patExternalInvestigationDetailsArray
			 * =(EpisodeExtInvDtlVO[])patExternalInvestigationList
			 * .toArray(patExternalInvestigationDetailsArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
			 * patExternalInvestigationDetailsArray); } }
			 * if(episodeExtInvDtlVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<episodeExtInvDtlVOs.length;i++) { if(
			 * (episodeExtInvDtlVOs[i].getPatAdmNo()!=null) &&
			 * (episodeExtInvDtlVOs
			 * [i].getPatAdmNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patExternalInvestigationList.add(episodeExtInvDtlVOs[i]); } }
			 * 
			 * patExternalInvestigationDetailsArray=new
			 * EpisodeExtInvDtlVO[patExternalInvestigationList.size()];
			 * patExternalInvestigationDetailsArray
			 * =(EpisodeExtInvDtlVO[])patExternalInvestigationList
			 * .toArray(patExternalInvestigationDetailsArray);
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
			 * patExternalInvestigationDetailsArray);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * //episodeExtInvDtlVOs=episodeExtInvDtlVOs;
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXT_INVESTIGATION_ARRAY,
			 * episodeExtInvDtlVOs); }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient Complaints details for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientComplaintsDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY);
			PatientClinicalDetailVO patientClinicalDetailVO = new PatientClinicalDetailVO();
			patientClinicalDetailVO.setPatCrNo(_fb.getPatCrNo());

			patientClinicalDetailVOs = (PatientClinicalDetailVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY, _request);
			// if(patientClinicalDetailVOs==null)
			{
				patientClinicalDetailVOs = EmrCommonDeskDATA
						.getPatientComplaintDetails(
								patientClinicalDetailVO,
								GenericTemplateConfig.TEMPLATE_CATEGORY_COMPLAINT,
								userVO);
				setPatientRecordMap(patientClinicalDetailVO.getPatCrNo(),
						MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
						patientClinicalDetailVOs, _request);
			}

			List patClinicalDetailsList = new ArrayList();
			PatientClinicalDetailVO[] patClinicalDetailsArray = null;

			if (patientClinicalDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() != null && !patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i]
								.getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getAdmissionNo() != null && !patientClinicalDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						patClinicalDetailsList.add(patientClinicalDetailVOs[i]);
					}
					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
								patClinicalDetailsArray);
				}
			}

			/*
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs[i].getAdmissionNo()!=null) &&
			 * (patientClinicalDetailVOs
			 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
			 * patClinicalDetailsArray);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * patClinicalDetailsArray=patientClinicalDetailVOs;
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_COMPLAINTS_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 */
			String recordDate = "";
			// String paraId="";
			LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			Map<String, Map<String, String>> mpVitalChartData = new HashMap<String, Map<String, String>>();
			List<Entry> paraList = new ArrayList<Entry>();
			if ((patClinicalDetailsArray != null)
					&& (patClinicalDetailsArray.length > 0)) {
				Map<String, String> mapParaIDValue = null;
				for (int i = 0; i < patClinicalDetailsArray.length; i++) {
					String recordDateTemp = patClinicalDetailsArray[i]
							.getRecordDate();
					// String paraIdTemp=patClinicalDetailsArray[i].getParaId();

					if (!recordDateTemp.equals(recordDate)) {
						mapRecordDates.put(
								patClinicalDetailsArray[i].getRecordDate(),
								patClinicalDetailsArray[i].getRecordDate());
						mapParaIDValue = new HashMap<String, String>();
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
						mpVitalChartData.put(
								patClinicalDetailsArray[i].getRecordDate(),
								mapParaIDValue);
						recordDate = recordDateTemp;
					} else {
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
					}

					Entry entry = new Entry();
					entry.setValue(patClinicalDetailsArray[i].getParaId());
					entry.setLabel(patClinicalDetailsArray[i].getParaDesc());
					if (!paraList.contains(entry)) {
						paraList.add(entry);
					}
				}

				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);
				WebUTIL.setAttributeInSession(_request,
						GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST,
						paraList);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP,
						mapRecordDates);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP,
						mpVitalChartData);
			}

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient History details for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientHistoryDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_HISTORY_DETAILS_ARRAY);
			PatientClinicalDetailVO patientClinicalDetailVO = new PatientClinicalDetailVO();
			patientClinicalDetailVO.setPatCrNo(_fb.getPatCrNo());

			patientClinicalDetailVOs = (PatientClinicalDetailVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_HISTORY_DETAILS_ARRAY, _request);
			// if(patientClinicalDetailVOs==null)
			{
				patientClinicalDetailVOs = EmrCommonDeskDATA
						.getPatientComplaintDetails(
								patientClinicalDetailVO,
								GenericTemplateConfig.TEMPLATE_CATEGORY_HISTORY,
								userVO);
				setPatientRecordMap(patientClinicalDetailVO.getPatCrNo(),
						MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
						patientClinicalDetailVOs, _request);
			}

			List patClinicalDetailsList = new ArrayList();
			PatientClinicalDetailVO[] patClinicalDetailsArray = null;
			if (patientClinicalDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() != null && !patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i]
								.getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getAdmissionNo() != null && !patientClinicalDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						patClinicalDetailsList.add(patientClinicalDetailVOs[i]);
					}
					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
								patClinicalDetailsArray);
				}
			}

			/*
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs[i].getAdmissionNo()!=null) &&
			 * (patientClinicalDetailVOs
			 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
			 * patClinicalDetailsArray);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * patClinicalDetailsArray=patientClinicalDetailVOs;
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_HISTORY_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 */
			String recordDate = "";
			// String paraId="";
			LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			Map<String, Map<String, String>> mpVitalChartData = new HashMap<String, Map<String, String>>();
			List<Entry> paraList = new ArrayList<Entry>();
			if ((patClinicalDetailsArray != null)
					&& (patClinicalDetailsArray.length > 0)) {
				Map<String, String> mapParaIDValue = null;
				for (int i = 0; i < patClinicalDetailsArray.length; i++) {
					String recordDateTemp = patClinicalDetailsArray[i]
							.getRecordDate();
					// String paraIdTemp=patClinicalDetailsArray[i].getParaId();

					if (!recordDateTemp.equals(recordDate)) {
						mapRecordDates.put(
								patClinicalDetailsArray[i].getRecordDate(),
								patClinicalDetailsArray[i].getRecordDate());
						mapParaIDValue = new HashMap<String, String>();
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
						mpVitalChartData.put(
								patClinicalDetailsArray[i].getRecordDate(),
								mapParaIDValue);
						recordDate = recordDateTemp;
					} else {
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
					}

					Entry entry = new Entry();
					entry.setValue(patClinicalDetailsArray[i].getParaId());
					entry.setLabel(patClinicalDetailsArray[i].getParaDesc());
					if (!paraList.contains(entry)) {
						paraList.add(entry);
					}
				}

				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);
				WebUTIL.setAttributeInSession(_request,
						GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST,
						paraList);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP,
						mapRecordDates);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP,
						mpVitalChartData);
			}

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient Examination details for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientExaminationDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY);
			PatientClinicalDetailVO patientClinicalDetailVO = new PatientClinicalDetailVO();
			patientClinicalDetailVO.setPatCrNo(_fb.getPatCrNo());

			patientClinicalDetailVOs = (PatientClinicalDetailVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY, _request);
			// if(patientClinicalDetailVOs==null)
			{
				patientClinicalDetailVOs = EmrCommonDeskDATA
						.getPatientComplaintDetails(
								patientClinicalDetailVO,
								GenericTemplateConfig.TEMPLATE_CATEGORY_EXAMINATION,
								userVO);
				setPatientRecordMap(patientClinicalDetailVO.getPatCrNo(),
						MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
						patientClinicalDetailVOs, _request);
			}

			List patClinicalDetailsList = new ArrayList();
			PatientClinicalDetailVO[] patClinicalDetailsArray = null;

			if (patientClinicalDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() != null && !patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i]
								.getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (patientClinicalDetailVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo() == null || patientClinicalDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						if ((patientClinicalDetailVOs[i].getAdmissionNo() != null && !patientClinicalDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (patientClinicalDetailVOs[i]
										.getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (patientClinicalDetailVOs[i]
										.getHospitalCode().equals(_fb
										.getHosCode()))) {
							patClinicalDetailsList
									.add(patientClinicalDetailVOs[i]);
						}
					}

					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				} else {
					for (int i = 0; i < patientClinicalDetailVOs.length; i++) {
						patClinicalDetailsList.add(patientClinicalDetailVOs[i]);
					}
					patClinicalDetailsArray = new PatientClinicalDetailVO[patClinicalDetailsList
							.size()];
					patClinicalDetailsArray = (PatientClinicalDetailVO[]) patClinicalDetailsList
							.toArray(patClinicalDetailsArray);
					if (patClinicalDetailsArray.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
								patClinicalDetailsArray);
				}
			}

			/*
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if( (patientClinicalDetailVOs!=null) &&
			 * (patientClinicalDetailVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) ) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs
			 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo())) &&
			 * (patientClinicalDetailVOs[i].getAdmissionNo()==null) ) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<patientClinicalDetailVOs.length;i++) { if(
			 * (patientClinicalDetailVOs[i].getAdmissionNo()!=null) &&
			 * (patientClinicalDetailVOs
			 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patClinicalDetailsList.add(patientClinicalDetailVOs[i]); } }
			 * 
			 * patClinicalDetailsArray=new
			 * PatientClinicalDetailVO[patClinicalDetailsList.size()];
			 * patClinicalDetailsArray
			 * =(PatientClinicalDetailVO[])patClinicalDetailsList
			 * .toArray(patClinicalDetailsArray);
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
			 * patClinicalDetailsArray);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(patientClinicalDetailVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * patClinicalDetailsArray=patientClinicalDetailVOs;
			 * if(patClinicalDetailsArray.length>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PAT_EXAMINATION_DETAILS_ARRAY,
			 * patClinicalDetailsArray); } }
			 */
			String recordDate = "";
			// String paraId="";
			LinkedHashMap<String, String> mapRecordDates = new LinkedHashMap<String, String>();
			Map<String, Map<String, String>> mpVitalChartData = new HashMap<String, Map<String, String>>();
			List<Entry> paraList = new ArrayList<Entry>();
			if ((patClinicalDetailsArray != null)
					&& (patClinicalDetailsArray.length > 0)) {
				Map<String, String> mapParaIDValue = null;
				for (int i = 0; i < patClinicalDetailsArray.length; i++) {
					String recordDateTemp = patClinicalDetailsArray[i]
							.getRecordDate();
					// String paraIdTemp=patClinicalDetailsArray[i].getParaId();

					if (!recordDateTemp.equals(recordDate)) {
						mapRecordDates.put(
								patClinicalDetailsArray[i].getRecordDate(),
								patClinicalDetailsArray[i].getRecordDate());
						mapParaIDValue = new HashMap<String, String>();
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
						mpVitalChartData.put(
								patClinicalDetailsArray[i].getRecordDate(),
								mapParaIDValue);
						recordDate = recordDateTemp;
					} else {
						mapParaIDValue.put(
								patClinicalDetailsArray[i].getParaId(),
								patClinicalDetailsArray[i].getParaValue());
					}

					Entry entry = new Entry();
					entry.setValue(patClinicalDetailsArray[i].getParaId());
					entry.setLabel(patClinicalDetailsArray[i].getParaDesc());
					if (!paraList.contains(entry)) {
						paraList.add(entry);
					}
				}

				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE,
						GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_PARA_WISE_LIST);
				WebUTIL.setAttributeInSession(_request,
						GenericTemplateConfig.GENERIC_CHART_PARAMETER_LIST,
						paraList);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATA_DATES_LIST_MAP,
						mapRecordDates);
				WebUTIL.setAttributeInSession(
						_request,
						GenericTemplateConfig.GENERIC_CHART_DATES_PARA_VALUE_MAP,
						mpVitalChartData);
			}

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getPatientProfilesForAll(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		List patientProfileDetailList = null;
		List<PatientProfileDetailVO> lstProfiles = null;

		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY);
			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}
			if (hasAccess)
		    patientProfileDetailList = EmrCommonDeskDATA.getPatientProfilesForAll(_fb.getPatCrNo(),departmentUnitArray, accessType, userVO);

			WebUTIL.setAttributeInSession(_request,MrdConfig.PATIENT_PROFILE_DTL_VO_ARRAY,patientProfileDetailList);
			
			//Added by Vasu on 25.Feb.2019
			lstProfiles = PatientProfileInboxDATA.getPatientProfilesForInbox(_fb.getPatCrNo(), _fb.getDepartmentUnitCode(), userVO);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstProfiles);
		}

		catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * To get patient Image Examination details for EMR
	 * 
	 * @param _fb
	 * @param _request
	 * @return void
	 */
	public static void getPatientDiagnosisImages(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		OpdPatientImageDtlVO[] opdPatientImageDtlVOs = null;
		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PATIENT_EXAMINATION_IMAGES);
			OpdPatientImageDtlVO opdPatientImageDtlVO = new OpdPatientImageDtlVO();
			opdPatientImageDtlVO.setPatCrNo(_fb.getPatCrNo());

			opdPatientImageDtlVOs = (OpdPatientImageDtlVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PATIENT_EXAMINATION_IMAGES, _request);
			// if(opdPatientImageDtlVOs==null)
			{
				opdPatientImageDtlVOs = EmrCommonDeskDATA
						.getPatientDiagnosisImages(opdPatientImageDtlVO, userVO);
				setPatientRecordMap(opdPatientImageDtlVO.getPatCrNo(),
						MrdConfig.PATIENT_EXAMINATION_IMAGES,
						opdPatientImageDtlVOs, _request);
			}

			List patImageList = new ArrayList();
			OpdPatientImageDtlVO[] patImageDtlVOs = null;

			if (opdPatientImageDtlVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (opdPatientImageDtlVOs[i].getAdmissionNo() == null || opdPatientImageDtlVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getHospitalCode()
								.equals(_fb.getHosCode()))
								&& (opdPatientImageDtlVOs[i].getAdmissionNo() != null && !opdPatientImageDtlVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (opdPatientImageDtlVOs[i].getAdmissionNo() == null || opdPatientImageDtlVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (opdPatientImageDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (opdPatientImageDtlVOs[i].getAdmissionNo() == null || opdPatientImageDtlVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (opdPatientImageDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getEpisodeCode()
								.equals(_fb.getEpisodeCode()))
								&& (opdPatientImageDtlVOs[i]
										.getEpisodeVisitNo().equals(_fb
										.getEpisodeVisitNo()))
								&& (opdPatientImageDtlVOs[i].getAdmissionNo() == null || opdPatientImageDtlVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (opdPatientImageDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						if ((opdPatientImageDtlVOs[i].getAdmissionNo() != null && !opdPatientImageDtlVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (opdPatientImageDtlVOs[i].getAdmissionNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (opdPatientImageDtlVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							patImageList.add(opdPatientImageDtlVOs[i]);
						}
					}

					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				} else {
					for (int i = 0; i < opdPatientImageDtlVOs.length; i++) {
						patImageList.add(opdPatientImageDtlVOs[i]);
					}
					patImageDtlVOs = new OpdPatientImageDtlVO[patImageList
							.size()];
					patImageDtlVOs = (OpdPatientImageDtlVO[]) patImageList
							.toArray(patImageDtlVOs);
					if (patImageDtlVOs.length > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_EXAMINATION_IMAGES,
								patImageDtlVOs);
				}
			}

			/*
			 * if( (opdPatientImageDtlVOs!=null) &&
			 * (opdPatientImageDtlVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) ) { for(int
			 * i=0;i<opdPatientImageDtlVOs.length;i++) { if(
			 * (opdPatientImageDtlVOs
			 * [i].getDepartmentUnitCode().equals(_fb.getSelectedUnit())) &&
			 * (opdPatientImageDtlVOs[i].getAdmissionNo()==null) ) {
			 * patImageList.add(opdPatientImageDtlVOs[i]); } }
			 * 
			 * patImageDtlVOs=new OpdPatientImageDtlVO[patImageList.size()];
			 * patImageDtlVOs
			 * =(OpdPatientImageDtlVO[])patImageList.toArray(patImageDtlVOs);
			 * if(patImageDtlVOs.length>0)
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PATIENT_EXAMINATION_IMAGES, patImageDtlVOs); } }
			 * 
			 * if( (opdPatientImageDtlVOs!=null) &&
			 * (opdPatientImageDtlVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
			 * i=0;i<opdPatientImageDtlVOs.length;i++) { if(
			 * (opdPatientImageDtlVOs
			 * [i].getEpisodeCode().equals(_fb.getEpisodeCode())) &&
			 * (opdPatientImageDtlVOs[i].getAdmissionNo()==null) ) {
			 * patImageList.add(opdPatientImageDtlVOs[i]); } }
			 * 
			 * patImageDtlVOs=new OpdPatientImageDtlVO[patImageList.size()];
			 * patImageDtlVOs
			 * =(OpdPatientImageDtlVO[])patImageList.toArray(patImageDtlVOs);
			 * if(patImageDtlVOs.length>0)
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PATIENT_EXAMINATION_IMAGES, patImageDtlVOs); } }
			 * 
			 * if( (opdPatientImageDtlVOs!=null) &&
			 * (opdPatientImageDtlVOs.length>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) ) { for(int
			 * i=0;i<opdPatientImageDtlVOs.length;i++) { if(
			 * (opdPatientImageDtlVOs
			 * [i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo())) &&
			 * (opdPatientImageDtlVOs[i].getAdmissionNo()==null) ) {
			 * patImageList.add(opdPatientImageDtlVOs[i]); } }
			 * 
			 * patImageDtlVOs=new OpdPatientImageDtlVO[patImageList.size()];
			 * patImageDtlVOs
			 * =(OpdPatientImageDtlVO[])patImageList.toArray(patImageDtlVOs);
			 * if(patImageDtlVOs.length>0)
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PATIENT_EXAMINATION_IMAGES, patImageDtlVOs); } }
			 * 
			 * if(opdPatientImageDtlVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<opdPatientImageDtlVOs.length;i++) { if(
			 * (opdPatientImageDtlVOs[i].getAdmissionNo()!=null) &&
			 * (opdPatientImageDtlVOs
			 * [i].getAdmissionNo().equals(_fb.getSelectedPatAdmNo()))) {
			 * patImageList.add(opdPatientImageDtlVOs[i]); } }
			 * 
			 * patImageDtlVOs=new OpdPatientImageDtlVO[patImageList.size()];
			 * patImageDtlVOs
			 * =(OpdPatientImageDtlVO[])patImageList.toArray(patImageDtlVOs);
			 * if(patImageDtlVOs.length>0)
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PATIENT_EXAMINATION_IMAGES, patImageDtlVOs);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(opdPatientImageDtlVOs!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * patImageDtlVOs=opdPatientImageDtlVOs; if(patImageDtlVOs.length>0)
			 * WebUTIL
			 * .setAttributeInSession(_request,MrdConfig.PATIENT_EXAMINATION_IMAGES
			 * , patImageDtlVOs); } }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getPatientOperationList(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		List patientOperationList = null;
		try {
			UserVO userVO = getUserVO(_request);

			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PATIENT_OPERATION_LIST);
			OpdPatientImageDtlVO opdPatientImageDtlVO = new OpdPatientImageDtlVO();
			opdPatientImageDtlVO.setPatCrNo(_fb.getPatCrNo());

			patientOperationList = (List) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PATIENT_OPERATION_LIST, _request);

			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// patientOperationList==null && hasAccess)
			{
				System.out.println("hiiiii::");
				patientOperationList = EmrCommonDeskDATA
						.getPatientOperationList(_fb.getPatCrNo(),
								departmentUnitArray, accessType, userVO);
				setPatientRecordMap(opdPatientImageDtlVO.getPatCrNo(),
						MrdConfig.PATIENT_OPERATION_LIST, patientOperationList,
						_request);
			}

			List criteriaBasedOperationList = new ArrayList();

			// (0) OT Req No
			// (1) Admission No
			// (2) department unit code
			// (3) episode
			// (4) operation date
			// (5) operation name
			// (6) Visit No
			// (7) Department Code

			if ((patientOperationList != null)
					&& (patientOperationList.size() > 0)) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				} else {
					Iterator listIterator = patientOperationList.iterator();
					while (listIterator.hasNext()) {
						List tempOperationList = (List) listIterator.next();
						if ((tempOperationList.get(2).equals(_fb
								.getSelectedUnit()))
								&& (tempOperationList.get(1) == null)) {
							criteriaBasedOperationList.add(tempOperationList);
						}
					}

					if (criteriaBasedOperationList.size() > 0)
						WebUTIL.setAttributeInSession(_request,
								MrdConfig.PATIENT_OPERATION_LIST,
								criteriaBasedOperationList);
				}
			}

			/*
			 * if( (patientOperationList!=null) &&
			 * (patientOperationList.size()>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) ) { Iterator
			 * listIterator=patientOperationList.iterator();
			 * while(listIterator.hasNext()) { List
			 * tempOperationList=(List)listIterator.next(); if(
			 * (tempOperationList.get(2).equals(_fb.getSelectedUnit())) &&
			 * (tempOperationList.get(1)==null) ) {
			 * criteriaBasedOperationList.add(tempOperationList); } }
			 * 
			 * 
			 * if(criteriaBasedOperationList.size()>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PATIENT_OPERATION_LIST,
			 * criteriaBasedOperationList); } }
			 * 
			 * if( (patientOperationList!=null) &&
			 * (patientOperationList.size()>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { Iterator
			 * listIterator=patientOperationList.iterator();
			 * while(listIterator.hasNext()) { List
			 * tempOperationList=(List)listIterator.next(); if(
			 * (tempOperationList.get(3).equals(_fb.getEpisodeCode())) &&
			 * (tempOperationList.get(1)==null) ) {
			 * criteriaBasedOperationList.add(tempOperationList); } }
			 * 
			 * 
			 * if(criteriaBasedOperationList.size()>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PATIENT_OPERATION_LIST,
			 * criteriaBasedOperationList); } }
			 * 
			 * if( (patientOperationList!=null) &&
			 * (patientOperationList.size()>0)) {
			 * 
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) ) { Iterator
			 * listIterator=patientOperationList.iterator();
			 * while(listIterator.hasNext()) { List
			 * tempOperationList=(List)listIterator.next(); if(
			 * (tempOperationList.get(6).equals(_fb.getEpisodeVisitNo())) &&
			 * (tempOperationList.get(1)==null) ) {
			 * criteriaBasedOperationList.add(tempOperationList); } }
			 * 
			 * if(criteriaBasedOperationList.size()>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PATIENT_OPERATION_LIST,
			 * criteriaBasedOperationList); } }
			 * 
			 * if(patientOperationList!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { Iterator
			 * listIterator=patientOperationList.iterator();
			 * while(listIterator.hasNext()) { List
			 * tempOperationList=(List)listIterator.next(); if(
			 * (tempOperationList.get(1)!=null) &&
			 * (tempOperationList.get(1).equals(_fb.getSelectedPatAdmNo()))) {
			 * criteriaBasedOperationList.add(tempOperationList); } }
			 * 
			 * if(criteriaBasedOperationList.size()>0)
			 * WebUTIL.setAttributeInSession
			 * (_request,MrdConfig.PATIENT_OPERATION_LIST,
			 * criteriaBasedOperationList);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(patientOperationList!=null) { if(
			 * (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * criteriaBasedOperationList=patientOperationList;
			 * if(patientOperationList.size()>0)
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PATIENT_OPERATION_LIST, criteriaBasedOperationList); }
			 * }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getPatientOperationTemplateDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		Map templateDetailsMap = null;
		List templateList = null;
		List paraIdValueList = null;
		Map mapAll = new HashMap();

		try {
			String deptCode = _request.getParameter("DEPT_CODE");
			String reqNo = _request.getParameter("REQ_NO");
			String otName = _request.getParameter("OT_NAME");
			_request.setAttribute("OT_NAME", otName);
			UserVO userVO = getUserVO(_request);
			templateDetailsMap = EmrCommonDeskDATA
					.getPatientOperationTemplateDetails(_fb.getPatCrNo(),
							deptCode, reqNo, userVO);
			templateList = (List) templateDetailsMap
					.get(MrdConfig.OPERATION_TEMPLATE_LIST);

			paraIdValueList = (List) templateDetailsMap
					.get(MrdConfig.OPERATION_TEMPLATE_PARA_ID_VALUE);

			// (0) template Id
			// (1) parameter ID
			// (2) parameter value
			// (3) status code
			Iterator templateListIterator = templateList.iterator();
			while (templateListIterator.hasNext()) {
				String templateId = (String) templateListIterator.next();
				Map map = new HashMap();
				Iterator paraIdValueListIterator = paraIdValueList.iterator();
				while (paraIdValueListIterator.hasNext()) {
					List list = (List) paraIdValueListIterator.next();
					if (templateId.equals((String) list.get(0))) {
						map.put(list.get(1), list.get(2));
					}
				}
				mapAll.put(templateId, map);

			}
			// need to change the hard value
			WebUTIL.setAttributeInSession(_request,
					"OTTEMPLATEPARAMETERVALUESMAP", mapAll); // Change for Get
																// Parameter
																// Value
			WebUTIL.setAttributeInSession(_request,
					MrdConfig.OPERATION_TEMPLATE_LIST, templateList);
		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getPatientAncDetails(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		// EpisodeAllergiesVO[] episodeAllergiesVOs=null;
		ANCDetailVO[] ancDetailVOs = null;
		try {
			UserVO userVO = getUserVO(_request);
			// String[] visitDetails=(_fb.getSendVisitDetail()).split("$");
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_ANC_DETAIL_VO_ARRAY);
			// EpisodeAllergiesVO episodeAllergiesVO=new EpisodeAllergiesVO();
			ANCDetailVO ancDetailVO = new ANCDetailVO();
			ancDetailVO.setPatCrNo(_fb.getPatCrNo());
			ancDetailVO.setEpisodeCode(_fb.getEpisodeCode());
			ancDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			ancDetailVOs = (ANCDetailVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, _request);

			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// ancDetailVOs==null && hasAccess)
			{
				ancDetailVOs = EmrCommonDeskDATA.getPatientAncDetails(
						ancDetailVO, departmentUnitArray, accessType, userVO);
				setPatientRecordMap(ancDetailVO.getPatCrNo(),
						MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, ancDetailVOs,
						_request);
			}

			List episodeAncVOsVisitList = new ArrayList();
			// EpisodeAllergiesVO[] episodeAllergiesVOsVisitArray=null;
			ANCDetailVO[] episodeAncVOsVisitArray = null;

			if (ancDetailVOs != null) {
				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (ancDetailVOs[i].getAdmissionNo() == null || ancDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (ancDetailVOs[i].getAdmissionNo() != null && !ancDetailVOs[i]
										.getAdmissionNo().trim().equals(""))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getDepartmentUnitCode().equals(_fb
								.getSelectedUnit()))
								&& (ancDetailVOs[i].getAdmissionNo() == null || ancDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (ancDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (ancDetailVOs[i].getAdmissionNo() == null || ancDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (ancDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (ancDetailVOs[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (ancDetailVOs[i].getAdmissionNo() == null || ancDetailVOs[i]
										.getAdmissionNo().trim().equals(""))
								&& (ancDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						if ((ancDetailVOs[i].getAdmissionNo() != null && !ancDetailVOs[i]
								.getAdmissionNo().trim().equals(""))
								&& (ancDetailVOs[i].getAdmissionNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (ancDetailVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeAncVOsVisitList.add(ancDetailVOs[i]);
						}
					}

					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				} else {
					for (int i = 0; i < ancDetailVOs.length; i++) {
						episodeAncVOsVisitList.add(ancDetailVOs[i]);
					}
					episodeAncVOsVisitArray = new ANCDetailVO[episodeAncVOsVisitList
							.size()];
					episodeAncVOsVisitArray = (ANCDetailVO[]) episodeAncVOsVisitList
							.toArray(episodeAncVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
							episodeAncVOsVisitArray);
				}
			}

			/*
			 * if( ((_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("E") )) ) { for(int
			 * i=0;i<ancDetailVOs.length;i++) { if(
			 * (ancDetailVOs[i].getEpisodeCode().equals(_fb.getEpisodeCode()))
			 * && (ancDetailVOs[i].getAdmissionNo()==null) ) {
			 * episodeAncVOsVisitList.add(ancDetailVOs[i]); } }
			 * 
			 * episodeAncVOsVisitArray=new
			 * ANCDetailVO[episodeAncVOsVisitList.size()];
			 * episodeAncVOsVisitArray
			 * =(ANCDetailVO[])episodeAncVOsVisitList.toArray
			 * (episodeAncVOsVisitArray);
			 * WebUTIL.setAttributeInSession(_request,
			 * MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, episodeAncVOsVisitArray); }
			 * 
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("V") )) { for(int
			 * i=0;i<ancDetailVOs.length;i++) { if(
			 * ((ancDetailVOs[i].getEpisodeCode().equals(_fb.getEpisodeCode()))
			 * &&
			 * (ancDetailVOs[i].getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo
			 * ()))) && (ancDetailVOs[i].getAdmissionNo()==null) ) {
			 * episodeAncVOsVisitList.add(ancDetailVOs[i]); } }
			 * 
			 * episodeAncVOsVisitArray=new
			 * ANCDetailVO[episodeAncVOsVisitList.size()];
			 * episodeAncVOsVisitArray
			 * =(ANCDetailVO[])episodeAncVOsVisitList.toArray
			 * (episodeAncVOsVisitArray);
			 * WebUTIL.setAttributeInSession(_request,
			 * MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, episodeAncVOsVisitArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("U") )) { for(int
			 * i=0;i<ancDetailVOs.length;i++) { if(
			 * (ancDetailVOs[i].getDepartmentUnitCode
			 * ().equals(_fb.getSelectedUnit())) &&
			 * (ancDetailVOs[i].getAdmissionNo()==null) ) {
			 * episodeAncVOsVisitList.add(ancDetailVOs[i]); } }
			 * 
			 * episodeAncVOsVisitArray=new
			 * ANCDetailVO[episodeAncVOsVisitList.size()];
			 * episodeAncVOsVisitArray
			 * =(ANCDetailVO[])episodeAncVOsVisitList.toArray
			 * (episodeAncVOsVisitArray);
			 * WebUTIL.setAttributeInSession(_request,
			 * MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, episodeAncVOsVisitArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("I") )) { for(int
			 * i=0;i<ancDetailVOs.length;i++) { if(
			 * (ancDetailVOs[i].getAdmissionNo()!=null) &&
			 * (ancDetailVOs[i].getAdmissionNo
			 * ().equals(_fb.getSelectedPatAdmNo())) ) {
			 * episodeAncVOsVisitList.add(ancDetailVOs[i]); } }
			 * 
			 * episodeAncVOsVisitArray=new
			 * ANCDetailVO[episodeAncVOsVisitList.size()];
			 * episodeAncVOsVisitArray
			 * =(ANCDetailVO[])episodeAncVOsVisitList.toArray
			 * (episodeAncVOsVisitArray);
			 * WebUTIL.setAttributeInSession(_request,
			 * MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, episodeAncVOsVisitArray); }
			 * 
			 * if( (_fb.getSelectionCriteria()!=null) &&
			 * (_fb.getSelectionCriteria().equals("A") )) {
			 * //ancDetailVOs=ancDetailVOs;
			 * WebUTIL.setAttributeInSession(_request
			 * ,MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, ancDetailVOs); }
			 */

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * Get patient visit details in the OPD and IPD
	 * 
	 * @param _fb
	 * @param _request
	 */

	public static void getPatientVisitSummary(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		EpisodeVO episodeVO = new EpisodeVO();
		// EpisodeSummaryDetailVO []episodeSummaryDetailVOs=null;
		try {
			UserVO userVO = getUserVO(_request);
			// String[] visitDetails=(_fb.getSendVisitDetail()).split("$");
			HttpSession session = WebUTIL.getSession(_request);
			// session.removeAttribute(MrdConfig.PAT_ANC_DETAIL_VO_ARRAY);

			episodeVO.setPatCrNo(_fb.getPatCrNo());
			episodeVO.setEpisodeCode(_fb.getEpisodeCode());
			episodeVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			// ancDetailVOs=(ANCDetailVO[])getPateintRecordMap((String)session.getAttribute("crNo"),MrdConfig.PAT_ANC_DETAIL_VO_ARRAY,
			// _request);

			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess) {
				essentialMap = EmrCommonDeskDATA.getPatientVisitSummary(
						episodeVO, departmentUnitArray, accessType, userVO);
				// episodeSummaryDetailVOs=(EpisodeSummaryDetailVO[])essentialMap.get(MrdConfig.EPISODE_SUMMARY_VO_ARRAY);
				// setPatientRecordMap(ancDetailVO.getPatCrNo(),
				// MrdConfig.PAT_ANC_DETAIL_VO_ARRAY, ancDetailVOs, _request);
			}

			List episodeVOList = new ArrayList();
			// EpisodeAllergiesVO[] episodeAllergiesVOsVisitArray=null;
			EpisodeVO[] episodeVOsArray = null;

			EpisodeVO[] episodeVOs = (EpisodeVO[]) session
					.getAttribute(MrdConfig.EPISODE_TREE_ARRAY);

			/*
			 * Calendar c=Calendar.getInstance(); //Calendar
			 * c1=Calendar.getInstance(); //c.set(2010, 5, 12); DateFormat
			 * dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
			 * System.out.println("Date :"+dateFormat.format(c.getTime()));
			 */

			List<Entry> entryList = new ArrayList<Entry>();
			for (EpisodeVO vo : episodeVOs) {
				Entry entry = new Entry();
				entry.setValue(vo.getEpisodeCode() + "#"
						+ vo.getEpisodeVisitNo());
				entry.setLabel(vo.getEpisodeDate());
				entryList.add(entry);
			}
			Collections.sort(entryList, new Entry.EntryComparator());
			/*
			 * for(Entry e:entryList) System.out.println(e.getLabel());
			 */
			EpisodeVO[] episodeVONew = new EpisodeVO[episodeVOs.length];
			int i = 0;
			for (Entry e : entryList) {
				for (EpisodeVO vo : episodeVOs) {
					if (e.getValue().split("#")[0].equals(vo.getEpisodeCode())
							&& e.getValue().split("#")[1].equals(vo
									.getEpisodeVisitNo())) {
						episodeVONew[i++] = vo;
						continue;
					}
				}
			}

			episodeVOs = episodeVONew;

			WebUTIL.setAttributeInSession(_request, MrdConfig.EPISODE_VO_ARRAY,
					episodeVOsArray);

			if (((_fb.getSelectionCriteria() != null) && (_fb
					.getSelectionCriteria().equals("E")))) {
				for (i = 0; i < episodeVOs.length; i++) {
					if ((episodeVOs[i].getEpisodeCode().equals(_fb
							.getEpisodeCode()))
							&& (episodeVOs[i].getAdmissionNo() == null)) {
						episodeVOList.add(episodeVOs[i]);
					}
				}

				episodeVOsArray = new EpisodeVO[episodeVOList.size()];
				episodeVOsArray = (EpisodeVO[]) episodeVOList
						.toArray(episodeVOsArray);
				WebUTIL.setAttributeInSession(_request,
						MrdConfig.EPISODE_VO_ARRAY, episodeVOsArray);
			}

			if ((_fb.getSelectionCriteria() != null)
					&& (_fb.getSelectionCriteria().equals("V"))) {
				for (i = 0; i < episodeVOs.length; i++) {
					if (((episodeVOs[i].getEpisodeCode().equals(_fb
							.getEpisodeCode())) && (episodeVOs[i]
							.getEpisodeVisitNo()
							.equals(_fb.getEpisodeVisitNo())))
							&& (episodeVOs[i].getAdmissionNo() == null)) {
						episodeVOList.add(episodeVOs[i]);
					}
				}

				episodeVOsArray = new EpisodeVO[episodeVOList.size()];
				episodeVOsArray = (EpisodeVO[]) episodeVOList
						.toArray(episodeVOsArray);
				WebUTIL.setAttributeInSession(_request,
						MrdConfig.EPISODE_VO_ARRAY, episodeVOsArray);
			}

			if ((_fb.getSelectionCriteria() != null)
					&& (_fb.getSelectionCriteria().equals("U"))) {
				for (i = 0; i < episodeVOs.length; i++) {
					if ((episodeVOs[i].getDepartmentUnitCode().equals(_fb
							.getSelectedUnit()))
							&& (episodeVOs[i].getAdmissionNo() == null)) {
						episodeVOList.add(episodeVOs[i]);
					}
				}

				episodeVOsArray = new EpisodeVO[episodeVOList.size()];
				episodeVOsArray = (EpisodeVO[]) episodeVOList
						.toArray(episodeVOsArray);
				WebUTIL.setAttributeInSession(_request,
						MrdConfig.EPISODE_VO_ARRAY, episodeVOsArray);
			}

			if ((_fb.getSelectionCriteria() != null)
					&& (_fb.getSelectionCriteria().equals("I"))) {
				for (i = 0; i < episodeVOs.length; i++) {
					if ((episodeVOs[i].getAdmissionNo() != null)
							&& (episodeVOs[i].getAdmissionNo().equals(_fb
									.getSelectedPatAdmNo()))) {
						episodeVOList.add(episodeVOs[i]);
					}
				}

				episodeVOsArray = new EpisodeVO[episodeVOList.size()];
				episodeVOsArray = (EpisodeVO[]) episodeVOList
						.toArray(episodeVOsArray);
				WebUTIL.setAttributeInSession(_request,
						MrdConfig.EPISODE_VO_ARRAY, episodeVOsArray);
			}

			if ((_fb.getSelectionCriteria() != null)
					&& (_fb.getSelectionCriteria().equals("A"))) {

				WebUTIL.setAttributeInSession(_request,
						MrdConfig.EPISODE_VO_ARRAY, episodeVOs);
			}

			/*
			 * for(EpisodeVO vo:episodeVONew){
			 * System.out.println("episodeVONew "+vo.getEntryDate()); }
			 */

			WebUTIL.setAttributeInSession(_request,
					MrdConfig.PAT_ADMISSION_DTL_VO_ARRAY,
					essentialMap.get(MrdConfig.PAT_ADMISSION_DTL_VO_ARRAY));
			// WebUTIL.setAttributeInSession(_request,
			// MrdConfig.EPISODE_VO_ARRAY, episodeVONew);
			WebUTIL.setAttributeInSession(_request,
					MrdConfig.EPISODE_REF_DTL_VO_ARRAY,
					essentialMap.get(MrdConfig.EPISODE_REF_DTL_VO_ARRAY));

		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	/**
	 * Get Patient CR Merge Detail
	 * 
	 * @param _fb
	 * @param _request
	 */
	public static void getPatientCRMerge(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		List<CrNoMergeDtlVO> lstCRMergeVOs = null;
		CrNoMergeDtlVO voCRMerge = new CrNoMergeDtlVO();
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PATIENT_CR_MERGE_LIST);

			voCRMerge.setPatMainCrNo(_fb.getPatCrNo());

			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess) {
				lstCRMergeVOs = EmrCommonDeskDATA.getPatientCRNoMergeList(
						voCRMerge, departmentUnitArray, accessType, userVO);
				setPatientRecordMap(voCRMerge.getPatMainCrNo(),
						MrdConfig.PATIENT_CR_MERGE_LIST, lstCRMergeVOs,
						_request);
			}

			if ((lstCRMergeVOs != null) && (lstCRMergeVOs.size() > 0)) {
				if (lstCRMergeVOs.size() > 0)
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PATIENT_CR_MERGE_LIST, lstCRMergeVOs);
			}
		} catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	/**
	 * Sort the record of the episodeVO array
	 * 
	 * @param _fb
	 * @param _request
	 */

	public static void sortOnDepartmentUnit(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {

		HttpSession session = _request.getSession();
		EpisodeVO[] episodeVOs = (EpisodeVO[]) session
				.getAttribute(MrdConfig.EPISODE_VO_ARRAY);
		String sortOn = _fb.getSortOn();
		List<Entry> entryList = new ArrayList<Entry>();
		for (EpisodeVO vo : episodeVOs) {
			Entry entry = new Entry();
			entry.setValue(vo.getEpisodeCode() + "#" + vo.getEpisodeVisitNo());
			// sort by department Unit
			if (sortOn.equals("departmentUnit")) {
				entry.setLabel(vo.getDepartmentUnit());
			}
			// sort by entry date
			else if (sortOn.equals("entryDate")) {
				entry.setLabel(vo.getEpisodeDate());
			}
			entryList.add(entry);
		}
		Collections.sort(entryList, new Entry.EntryComparator());
		/*
		 * for(Entry e:entryList) System.out.println(e.getLabel());
		 */
		EpisodeVO[] episodeVONew = new EpisodeVO[episodeVOs.length];
		int i = 0;
		for (Entry e : entryList) {
			for (EpisodeVO vo : episodeVOs) {
				if (e.getValue().split("#")[0].equals(vo.getEpisodeCode())
						&& e.getValue().split("#")[1].equals(vo
								.getEpisodeVisitNo())) {
					episodeVONew[i++] = vo;
					continue;
				}
			}
		}

		WebUTIL.setAttributeInSession(_request, MrdConfig.EPISODE_VO_ARRAY,
				episodeVONew);

	}

	/**
	 * Check whether the tab's record is restricted or not
	 * 
	 * @param request
	 * @param tabId
	 * @return flag
	 */
	public static boolean getIsAccessRestricted(HttpServletRequest request,
			String tabId) {
		boolean flag = false;
		HttpSession session = request.getSession();
		EprTabMasterVO eprTabMasterVO = null;
		String boundType = "";
		List<EprTabMasterVO> eprTabMasterVOList = (List) session
				.getAttribute(MrdConfig.EMR_TABS_ARRAY);
		if (eprTabMasterVOList == null)
			return false;// This check need to be verified when Access Policies
							// to apply----
		for (int i = 0; i < eprTabMasterVOList.size(); i++) {
			if (eprTabMasterVOList.get(i).getTabId().equals(tabId)) {
				eprTabMasterVO = eprTabMasterVOList.get(i);
				break;
			}
		}
		String policyType = (String) session
				.getAttribute(MrdConfig.EPR_PATIENT_CATEGORY);
		if (policyType.equals(MrdConfig.EPR_PATIENT_CATEGORY_NORMAL)) {
			boundType = eprTabMasterVO.getNormalBoundType();
		} else {
			boundType = eprTabMasterVO.getRestrictedBoundType();
		}

		if (boundType.equals("0"))
			flag = false;
		else {
			flag = true;
		}

		return flag;
	}

	/**
	 * get the department unit/user seat id array for which the own unit has
	 * privileges
	 * 
	 * @param request
	 * @param tabId
	 * @return
	 */
	public static String[] getDepartmentUnitArray(HttpServletRequest request,
			String tabId) {
		// boolean flag=false;
		HttpSession session = request.getSession();
		List list = new ArrayList();
		String departmentUnitArray[] = null;
		List<EprTabAccessDtlVO> eprTabAccessDtlVOList = (List) session
				.getAttribute(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST);
		if (eprTabAccessDtlVOList != null) {
			for (int i = 0; i < eprTabAccessDtlVOList.size(); i++) {
				if (eprTabAccessDtlVOList.get(i).getTabId().equals(tabId)) {
					// checks whether the access is unit bound or user bound
					// if access is unit bound
					if (eprTabAccessDtlVOList.get(i).getAccessType()
							.equals(MrdConfig.EPR_RECORD_ACCESS_UNIT_BOUND)) {
						list.add(eprTabAccessDtlVOList.get(i).getAccessTo());
					}
					// if access is user bound
					else {
						// check the user has valid access
						if (isUserBound(request, eprTabAccessDtlVOList.get(i))) {
							list.add(eprTabAccessDtlVOList.get(i).getAccessTo());
						}
					}
				}
			}
		}
		departmentUnitArray = new String[list.size()];
		list.toArray(departmentUnitArray);
		return departmentUnitArray;
	}

	// Not in use
	/**
	 * check whether the tab's record is unit bound or user bound if the tab's
	 * record is user bound then check the seat id of the user with the access
	 * to seat id if the seat id matches with any of the record in the array
	 * then the access is allowed and return the access type as display all the
	 * data other wise return the same access type as it was in the parameter
	 * 
	 * @param request
	 * @param tabId
	 * @return access type (display_all_record_yes or display_all_record_no)
	 */

	public static boolean isUserBound(HttpServletRequest request, String tabId,
			String accessType) {
		// String accessType=MrdConfig.EPR_DISPLAY_ALL_RECORD_NO;
		boolean hasAccess = false;
		HttpSession session = request.getSession();
		List<EprTabAccessDtlVO> eprTabAccessDtlVOList = (List) session
				.getAttribute(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST);
		List<EprTabAccessDtlVO> list = new ArrayList();
		for (int i = 0; i < eprTabAccessDtlVOList.size(); i++) {
			if (eprTabAccessDtlVOList.get(i).getTabId().equals(tabId)) {
				list.add(eprTabAccessDtlVOList.get(i));
			}
		}
		if (list != null && list.size() > 0) {
			// if(list.get(0).getAccessType().equals(MrdConfig.EPR_RECORD_ACCESS_USER_BOUND)){
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getAccessType()
						.equals(MrdConfig.EPR_RECORD_ACCESS_USER_BOUND)
						&& !getUserVO(request).getSeatId().equals(
								list.get(i).getUserId())) {
					hasAccess = false;
					// accessType=MrdConfig.EPR_DISPLAY_ALL_RECORD_YES;
					break;
				}
				// else
				// accessType="";
			}
		}
		// }

		return hasAccess;
	}

	/**
	 * returns true if the login user is in the list of access to user list
	 * 
	 * @param request
	 * @param eprTabAccessDtlVO
	 * @return hasAccess
	 */
	public static boolean isUserBound(HttpServletRequest request,
			EprTabAccessDtlVO eprTabAccessDtlVO) {
		boolean hasAccess = false;
		if (getUserVO(request).getSeatId()
				.equals(eprTabAccessDtlVO.getUserId())) {
			hasAccess = true;
		}
		return hasAccess;
	}

	public static void getVisitReasonsEMR(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		VisitReasonsVO[] visitReasonsVOs = null;
		// boolean hasAccess=true;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_VISIT_REASON_ARRAY);
			VisitReasonsVO visitReasonsVO = new VisitReasonsVO();

			visitReasonsVO.setEpisodeCode(_fb.getEpisodeCode());
			visitReasonsVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			visitReasonsVO.setPatCrNo(_fb.getPatCrNo());
			visitReasonsVO.setHospitalCode(_fb.getHosCode());

			visitReasonsVOs = (VisitReasonsVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_VISIT_REASON_ARRAY, _request);

			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// visitReasonsVOs==null && hasAccess)
			{
				visitReasonsVOs = EmrCommonDeskDATA
						.getAllVisitDetails(visitReasonsVO,
								departmentUnitArray, accessType, userVO);
				setPatientRecordMap(visitReasonsVO.getPatCrNo(),
						MrdConfig.PAT_VISIT_REASON_ARRAY, visitReasonsVOs,
						_request);
			}

			// set the list of the record according to selection (All, Unit
			// wise, Episode wise, Episode visit wise,admission no wise )
			List episodeVisitReasonVOsList = new ArrayList();
			VisitReasonsVO[] episodeVisitReasonVOArray = null;
			if (visitReasonsVOs != null) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (visitReasonsVOs[i].getPatAdmNo() == null || visitReasonsVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (visitReasonsVOs[i].getPatAdmNo() != null && !visitReasonsVOs[i]
										.getPatAdmNo().trim().equals(""))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getDepartmentUnitCode()
								.equals(_fb.getSelectedUnit()))
								&& (visitReasonsVOs[i].getPatAdmNo() == null || visitReasonsVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (visitReasonsVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (visitReasonsVOs[i].getPatAdmNo() == null || visitReasonsVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (visitReasonsVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (visitReasonsVOs[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (visitReasonsVOs[i].getPatAdmNo() == null || visitReasonsVOs[i]
										.getPatAdmNo().trim().equals(""))
								&& (visitReasonsVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						if ((visitReasonsVOs[i].getPatAdmNo() != null && !visitReasonsVOs[i]
								.getPatAdmNo().trim().equals(""))
								&& (visitReasonsVOs[i].getPatAdmNo().equals(_fb
										.getSelectedPatAdmNo()))
								&& (visitReasonsVOs[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
						}
					}

					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				} else {
					for (int i = 0; i < visitReasonsVOs.length; i++) {
						episodeVisitReasonVOsList.add(visitReasonsVOs[i]);
					}
					episodeVisitReasonVOArray = new VisitReasonsVO[episodeVisitReasonVOsList
							.size()];
					episodeVisitReasonVOArray = (VisitReasonsVO[]) episodeVisitReasonVOsList
							.toArray(episodeVisitReasonVOArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_VISIT_REASON_ARRAY,
							episodeVisitReasonVOArray);
				}

			}
		}

		catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getServiceProceduresEMR(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		Service_Req_dtlVO[] serviceReqDtlvos = null;
		// boolean hasAccess=true;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY);
			Service_Req_dtlVO serviceReqDtlVO = new Service_Req_dtlVO();

			serviceReqDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			serviceReqDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			serviceReqDtlVO.setPatCrNo(_fb.getPatCrNo());
			serviceReqDtlVO.setHospitalCode(_fb.getHosCode());

			serviceReqDtlvos = (Service_Req_dtlVO[]) getPateintRecordMap(
					(String) session.getAttribute("crNo"),
					MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY, _request);

			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// serviceAreaVOs==null && hasAccess)
			{
				serviceReqDtlvos = EmrCommonDeskDATA.getAllServiceProcedures(
						serviceReqDtlVO, departmentUnitArray, accessType,
						userVO);
				setPatientRecordMap(serviceReqDtlVO.getPatCrNo(),
						MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
						serviceReqDtlvos, _request);
			}

			// set the list of the record according to selection (All, Unit
			// wise, Episode wise, Episode visit wise,admission no wise )
			List serviceProcedureVOsVisitList = new ArrayList();
			Service_Req_dtlVO[] serviceProcedureVOsVisitArray = null;
			if (serviceReqDtlvos != null) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getHospitalCode().equals(_fb
								.getHosCode()))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (serviceReqDtlvos[i].getAdmissionNo() == null || serviceReqDtlvos[i]
										.getAdmissionNo().trim().equals(""))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (serviceReqDtlvos[i].getAdmissionNo() != null && !serviceReqDtlvos[i]
										.getAdmissionNo().trim().equals(""))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getDeptCode().equals(_fb
								.getDepartmentUnitCode()))
								&& (serviceReqDtlvos[i].getAdmissionNo() == null || serviceReqDtlvos[i]
										.getAdmissionNo().trim().equals(""))
								&& (serviceReqDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (serviceReqDtlvos[i].getAdmissionNo() == null || serviceReqDtlvos[i]
										.getAdmissionNo().trim().equals(""))
								&& (serviceReqDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (serviceReqDtlvos[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (serviceReqDtlvos[i].getAdmissionNo() == null || serviceReqDtlvos[i]
										.getAdmissionNo().trim().equals(""))
								&& (serviceReqDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						if ((serviceReqDtlvos[i].getAdmissionNo() != null && !serviceReqDtlvos[i]
								.getAdmissionNo().trim().equals(""))
								&& (serviceReqDtlvos[i].getAdmissionNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (serviceReqDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							serviceProcedureVOsVisitList
									.add(serviceReqDtlvos[i]);
						}
					}

					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				} else {
					for (int i = 0; i < serviceReqDtlvos.length; i++) {
						serviceProcedureVOsVisitList.add(serviceReqDtlvos[i]);
					}
					serviceProcedureVOsVisitArray = new Service_Req_dtlVO[serviceProcedureVOsVisitList
							.size()];
					serviceProcedureVOsVisitArray = (Service_Req_dtlVO[]) serviceProcedureVOsVisitList
							.toArray(serviceProcedureVOsVisitArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_SERVICE_PROCEDURE_ARRAY,
							serviceProcedureVOsVisitArray);
				}

			}
		}

		catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}

	public static void getDocUploadEMR(EmrCommonDeskFB _fb,
			HttpServletRequest _request) {
		Status objStatus = new Status();
		DocumentUploadDtlVO[] docUploadDtlvos = null;
		// boolean hasAccess=true;
		boolean isAccessRestricted = true;
		boolean hasAccess = true;
		try {
			UserVO userVO = getUserVO(_request);
			HttpSession session = WebUTIL.getSession(_request);
			session.removeAttribute(MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY);
			DocumentUploadDtlVO documentUploadDtlVO = new DocumentUploadDtlVO();

			documentUploadDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			documentUploadDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			documentUploadDtlVO.setPatCrNo(_fb.getPatCrNo());
			documentUploadDtlVO.setHospitalCode(_fb.getHosCode());

			// docUploadDtlvos=(DocumentUploadDtlVO[])getPateintRecordMap((String)session.getAttribute("crNo"),MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY,
			// _request);

			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(_request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(_request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}

			if (hasAccess)// serviceAreaVOs==null && hasAccess)
			{
				docUploadDtlvos = EmrCommonDeskDATA.getAllDocUploaded(
						documentUploadDtlVO, departmentUnitArray, accessType,
						userVO);
				setPatientRecordMap(documentUploadDtlVO.getPatCrNo(),
						MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadDtlvos,
						_request);
			}

			// set the list of the record according to selection (All, Unit
			// wise, Episode wise, Episode visit wise,admission no wise )
			List docUploadList = new ArrayList();
			DocumentUploadDtlVO[] docUploadArray = null;
			if (docUploadDtlvos != null) {

				if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("H"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getHospitalCode().equals(_fb
								.getHosCode()))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("O"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (docUploadDtlvos[i].getAdmissionNo() == null || docUploadDtlvos[i]
										.getAdmissionNo().trim().equals(""))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("I"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getHospitalCode().equals(_fb
								.getHosCode()))
								&& (docUploadDtlvos[i].getAdmissionNo() != null && !docUploadDtlvos[i]
										.getAdmissionNo().trim().equals(""))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("U"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getDepartmentUnitCode()
								.equals(_fb.getDepartmentUnitCode()))
								&& (docUploadDtlvos[i].getAdmissionNo() == null || docUploadDtlvos[i]
										.getAdmissionNo().trim().equals(""))
								&& (docUploadDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("E"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (docUploadDtlvos[i].getAdmissionNo() == null || docUploadDtlvos[i]
										.getAdmissionNo().trim().equals(""))
								&& (docUploadDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("V"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getEpisodeCode().equals(_fb
								.getEpisodeCode()))
								&& (docUploadDtlvos[i].getEpisodeVisitNo()
										.equals(_fb.getEpisodeVisitNo()))
								&& (docUploadDtlvos[i].getAdmissionNo() == null || docUploadDtlvos[i]
										.getAdmissionNo().trim().equals(""))
								&& (docUploadDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else if ((_fb.getSelectionCriteria() != null)
						&& (_fb.getSelectionCriteria().equals("A"))) {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						if ((docUploadDtlvos[i].getAdmissionNo() != null && !docUploadDtlvos[i]
								.getAdmissionNo().trim().equals(""))
								&& (docUploadDtlvos[i].getAdmissionNo()
										.equals(_fb.getSelectedPatAdmNo()))
								&& (docUploadDtlvos[i].getHospitalCode()
										.equals(_fb.getHosCode()))) {
							docUploadList.add(docUploadDtlvos[i]);
						}
					}

					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				} else {
					for (int i = 0; i < docUploadDtlvos.length; i++) {
						docUploadList.add(docUploadDtlvos[i]);
					}
					docUploadArray = new DocumentUploadDtlVO[docUploadList
							.size()];
					docUploadArray = (DocumentUploadDtlVO[]) docUploadList
							.toArray(docUploadArray);
					WebUTIL.setAttributeInSession(_request,
							MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, docUploadArray);
				}

			}
		}

		catch (HisRecordNotFoundException e) {
			e.printStackTrace();
		} catch (HisDataAccessException e) {
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisApplicationExecutionException e) {
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		} catch (HisException e) {
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		} finally {
			WebUTIL.setStatus(_request, objStatus);

		}
	}
	
	
	//Added By Shweta on 09-MAY-2019
	public static void getDocumentArchivalEssentials(EmrCommonDeskFB _fb, HttpServletRequest request)
	{
		
		
		Status objStatus = new Status();
		DocumentUploadDtlVO[] docUploadVO = null;
		List patientUploadedDocDetailList = null;
		List<PatientDetailVO> dailyPatientVOs = null;
		PatientProfileDetailVO[] lstPatProfiles = null;

		try {
			UserVO userVO = getUserVO(request);

			HttpSession session = WebUTIL.getSession(request);
			EpisodeVO[] episodeVOs = null;
			Map essentialMap = null;
			String epcode="";
			PatientVO patientVO = new PatientVO();
			patientVO.setPatCrNo((String) session.getAttribute("crNo"));
			session.removeAttribute(MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY);
			boolean isAccessRestricted = true;
			boolean hasAccess = true;
			docUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			//PatientDetailVO selectedPatientVO =null;
			
			//selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			//essentialMap = EmrCommonDeskDATA.getEmrEssentials(patientVO, userVO);
			episodeVOs = (EpisodeVO[]) session.getAttribute(MrdConfig.EPISODE_TREE_ARRAY);
			
			for (int i = 0; i < episodeVOs.length; i++) {
				epcode=episodeVOs[i].getEpisodeCode();
				}
			
			// set the access type for the record display all or display few
			// record according to access policy
			isAccessRestricted = getIsAccessRestricted(request, _fb.getTabId());
			String accessType = (isAccessRestricted == true ? MrdConfig.EPR_DISPLAY_ALL_RECORD_NO
					: MrdConfig.EPR_DISPLAY_ALL_RECORD_YES);
			String departmentUnitArray[] = null;

			if (isAccessRestricted) {
				// get the list of the unit/user whose record can be displayed
				departmentUnitArray = getDepartmentUnitArray(request,
						_fb.getTabId());
				if (departmentUnitArray.length == 0)
					hasAccess = false;
			}
			//if (hasAccess)
				patientUploadedDocDetailList = EmrCommonDeskDATA.getDocumentArchivalEssentials(_fb.getPatCrNo(),epcode, userVO);

			WebUTIL.setAttributeInSession(request,MrdConfig.PATIENT_UPLOADED_DOC_VO_ARRAY,patientUploadedDocDetailList);
			
			
			/*lstProfiles = PatientProfileInboxDATA.getPatientProfilesForInbox(_fb.getPatCrNo(), _fb.getDepartmentUnitCode(), userVO);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST, lstProfiles);*/
		}
		
			catch (HisRecordNotFoundException e) {
				e.printStackTrace();
			} catch (HisDataAccessException e) {
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				e.printStackTrace();
			} catch (HisApplicationExecutionException e) {
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			} catch (HisException e) {
				objStatus.add(Status.ERROR, e.getMessage(), "");
				e.printStackTrace();
			} finally {
				WebUTIL.setStatus(request, objStatus);

			}
		
		
	//}
		/*Map essentialsMap = null;
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		DocumentUploadDtlVO[] docUploadVO = null;
		String sequenceNo = "";
		PatientDetailVO[] dailyPatientVOs = null;
		String patCrNo="";
		String episodeCode="";
		try
		{
			//opdDocumentArchivalFB.setFileType("");
			//opdDocumentArchivalFB.setSelectedRevoke("");
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
			_fb.setPatCrNo(patientDetailVO.getPatCrNo());
			_fb.setEpisodeCode(patientDetailVO.getEpisodeCode());
			_fb.setAddmissionNo(patientDetailVO.getPatAdmNo());
			essentialsMap = OpdDocumentArchivalDATA.getDocumentArchivalEssentials(_fb.getPatCrNo(), _fb.getEpisodeCode(), getUserVO(request));

			WebUTIL.setMapInSession(essentialsMap, request);
			docUploadVO = (DocumentUploadDtlVO[]) session.getAttribute(OpdConfig.DOCUMENT_DTL_VO_ARRAY);
			int x = docUploadVO.length - 1;
			sequenceNo = docUploadVO[x].getSerialNo();
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DOCUMENT_SERIAL_NO, sequenceNo);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			objStatus.add(Status.TRANSINPROCESS);
			_fb.setDocumentTitle("");
			opdDocumentArchivalFB.setDocumentCode("");
			String[] removeReasonArray = new String[docUploadVO.length];
			if ((docUploadVO != null) && (docUploadVO.length > 0))
			{
				for (int k = 0; k < docUploadVO.length; k++)
				{

					removeReasonArray[k] = "";

				}
			}
			_fb.setRemoveReason(removeReasonArray);

		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			essentialsMap = e.getEssentialMap();

			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_DETAIL_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ADD_DOCUMENT_MAP, new LinkedList());
			WebUTIL.setAttributeInSession(request, OpdConfig.DOCUMENT_DTL_VO_ARRAY, null);
			WebUTIL.setMapInSession(essentialsMap, request);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_DOCUMENT_SERIAL_NO, "0");
			objStatus.add(Status.TRANSINPROCESS);
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);

		}
	}*/

}
}
