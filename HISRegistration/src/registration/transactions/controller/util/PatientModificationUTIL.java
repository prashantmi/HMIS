package registration.transactions.controller.util;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.ArrayUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisPatientStillUnknownException;
import registration.transactions.controller.actionsupport.PatientDetailModificationSUP;
import registration.transactions.controller.data.DskPatientModificationDATA;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.PatientModificationDATA;
import vo.registration.AddressVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientModificationAuditVO;
import vo.registration.PatientModificationVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import auditlogClient.AuditlogClientConfig;
import auditlogClient.util.AuditlogDATA;

public class PatientModificationUTIL extends ControllerUTIL
{

	public static void setPatientDtlForMod(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		try
		{
			setInitialPatientDetailEssentials(objSUP_p, objRequest_p);
			PatientVO patVO = new PatientVO();
			patVO.setPatCrNo(objSUP_p.getPatCrNo());
			if (patVO != null)
			{
				PatDetailUTIL.getPatientDtlByCrno(objSUP_p, objRequest_p);
				if(WebUTIL.getStatus(objRequest_p).getMessage("007").equalsIgnoreCase("Patient Details Not Found"))
				{
					objSUP_p.setErrorMessage("Patient Details Not Found");
					objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
					throw new HisRecordNotFoundException("Patient Details Not Found");
				}
				else if(WebUTIL.getStatus(objRequest_p).getMessage("007").equalsIgnoreCase("Current Patient Category is invalid please change the Patient Category first"))
				{
					objSUP_p.setErrorMessage("Current Patient Category is invalid please change the Patient Category first");
					objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
					throw new HisRecordNotFoundException("Current Patient Category is invalid please change the Patient Category first");
				}
				
				else
				{
					if(WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO)!=null)
					{
						patVO = (PatientVO) WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.PATIENT_VO);
					}
					else
					{
						patVO.setPatCrNo(objSUP_p.getCrNoToModify());
					}
					if (patVO != null)
					{
//						if(!patVO.getPatRegCatCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
//						{
//							throw new HisPatientStillUnknownException("Patient registered as opd/emergency patient.Details can only be modified at MRD/Registration/Emergency desk");
//						}
						if(patVO.getIsUnknown()!=null && patVO.getIsUnknown().equals("1"))
						{
							objSUP_p.setErrorMessage("Patient Still Unknown. Use Unknown to Known Conversion");
							throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
						}
						//To Check whether the CRNo is merged or not,Added by Singaravelan on 16-Jul-2015 
						if(patVO.getPatIsMerged()!=null && patVO.getPatIsMerged().equals("2"))
						{
							objSUP_p.setAfterGo("0");
							objSUP_p.setErrorMessage("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO() );
							throw new HisRecordNotFoundException("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO() );
						}
						if(patVO.getIsCatExpired()!=null && patVO.getIsCatExpired().equals("1"))
							{
							/* #Start					:Sheeldarshi 
							#Modify Date(CR/PRS)	:25thMay'15 
							#Reason					: UAT Test Report Requirements:Requirement 4
							 */	
							//objSUP_p.setErrorMessage("Patient Category is Expired. Please Change Patient Category First");										
							objSUP_p.setErrorMessage("Patient Category Validity is over. Kindly renew it first.");
							//End
							throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
							}
//						if(patVO.getPatIsDead().equals("1"))
//						{
//							objSUP_p.setErrorMessage("Patient is Dead. Details cannot be modified");
//							throw new HisPatientStillUnknownException("Patient is Dead. Details cannot be modified");
//						}

						objSUP_p.setBeforeModificationAge(patVO.getPatAge());
						PatientModificationUTIL.setAddressVOs(objSUP_p, objRequest_p, status);
						status.add(Status.TRANSINPROCESS, "", "");
						WebUTIL.populate(objSUP_p, patVO);
						if(patVO.getPatDOB()!=null)
						{
						Date dtDOB = WebUTIL.getDateFromString(patVO.getPatDOB(), "dd-MMM-yyyy");
						String strDOB = WebUTIL.getCustomisedSysDate(dtDOB, "dd-MM-yyyy");
						objSUP_p.setPatDOB(strDOB);
						objSUP_p.setDefaultPatDOB(strDOB);
						}
						objSUP_p.setDefaultPatAge(patVO.getPatAge());
						objSUP_p.setDefaultPatAgeUnit(patVO.getPatAgeUnit());

						//patVO.setPatDOB(strDOB);
						if(patVO.getPatIdNo()!=null)
						{
						objSUP_p.setPatIdNo(patVO.getPatIdNo());
						patVO.setPatIdNo(patVO.getPatIdNo());
						}
						objSUP_p.setBeforeModificationAge(patVO.getPatAge());
						String strCaseCode = objSUP_p.getPatCasteCode();
						if(strCaseCode != null)
						{
							strCaseCode.trim();
							objSUP_p.setPatCasteCode(strCaseCode.trim());
						}
					}
				}

				try{
					int x=0;
					AddressVO[] addVoArr = (AddressVO[]) objRequest_p.getSession().getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
					String auditlogProcessId="2";
					Map mp= new LinkedHashMap();
					PatientModificationAuditVO objPatientModificationAuditVO = new PatientModificationAuditVO();
					for(int i=0;i<addVoArr.length;i++){
						if(addVoArr[i].getPatAddTypeCode().equals("1")){
							x=i;
							break;
						}
					}
					
					AddressVO objAddresVo = new AddressVO();
					HelperMethods.populate(objAddresVo, addVoArr[x]);
					HelperMethods.populate(objPatientModificationAuditVO, patVO);		
					objPatientModificationAuditVO.setPatAddress(objAddresVo);
					
					//commented for Audit Log Off
					mp.put("save_1", objPatientModificationAuditVO);
					mp.put("save_2", addVoArr);				
				//	AuditlogDATA.initAuditLog(AuditlogClientConfig.REGISTRATION, auditlogProcessId ,mp, ControllerUTIL.getUserVO(objRequest_p),objRequest_p);
				}
				catch(Exception e){
					e.printStackTrace();
				}


				objSUP_p.setAfterGo("1");
				WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.PATIENT_VO_MOD, patVO);	
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objSUP_p.setAfterGo("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisPatientStillUnknownException e)
		{
			objSUP_p.setAfterGo("0");
			status.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			objSUP_p.setAfterGo("0");
			status.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objSUP_p.setAfterGo("0");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			objSUP_p.setAfterGo("0");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p, status);

		}
	}


	/**
	 * Sets Bean in NEW mode of action. Initialises beans.
	 * 
	 * @param SUP object of PatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 */
	public static void setInitialPatientDetailEssentials(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		status.add(Status.NEW, "", "");
		try
		{
			Map mp = PatientModificationDATA.getInitialPatientModAddDetailEssentials();
			WebUTIL.setMapInSession(mp, objRequest_p,"PatientDetailModificationAction");
			setSysdate(objRequest_p);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		finally
		{
			WebUTIL.setStatus(objRequest_p, status);
		}
	}



	/**
	 * 
	 * @param SUP - object of PatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 */

	public static void setAddressTypeCombo(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_ADD; // address Activity Status
		HttpSession session = WebUTIL.getSession(objRequest_p);
		try
		{

			AddressVO[] addressVO;
			addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			Collection colAllAddress = (Collection) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE);

			Collection colRemAdd = new HashSet();
			colRemAdd.addAll(colAllAddress);

			Iterator itr = colAllAddress.iterator();
			while (itr.hasNext())
			{
				Entry objEntry = (Entry) itr.next();
				String value = objEntry.getValue();
				for (int j = 0; j < addressVO.length; j++)
				{
					if (value.equals(addressVO[j].getPatAddTypeCode()))
					{
						colRemAdd.remove(objEntry);
						break;
					}
				}
			}
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE_FOR_VALIDATION, colRemAdd);
		}
		catch (Exception e)
		{
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			objRequest_p.setAttribute(Config.STATUS_OBJECT, status);
			session.setAttribute(RegistrationConfig.PATIENT_ADDRESS_STATUS, add_modify);
		}
	}

	/**
	 * sets patient's address details in AddressVO
	 * 
	 * @param SUP - object of PatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 * @param status
	 */

	protected static void setAddressVOs(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
	{
		HttpSession session = objRequest_p.getSession();
		Status objStatus = new Status();
		PatientVO patVO = new PatientVO();
		UserVO userVO = getUserVO(objRequest_p);
		if(objSUP_p.getCrNoToModify()!=null)
			patVO.setPatCrNo(objSUP_p.getCrNoToModify());
		else
			patVO.setPatCrNo(objSUP_p.getPatCrNo());
		AddressVO[] addressVO;
		addressVO = PatientModificationDATA.getAddressEssentials(patVO, userVO);
		session.setAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR, addressVO);
		objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
	}


	@SuppressWarnings("unchecked")
	public static void populateRegistrationFormEssentials_AJAX(PatientDetailModificationSUP objNewPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession){	
		Status objStatus=new Status();
		String outputString="";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		Element renewalConfigElement=null;
		Map mp=new HashMap();
		try
		{
			UserVO userVO=getUserVO(objRequest);
			HospitalMstVO objHosVO=getHospitalVO(objRequest);
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			
			if(WebUTIL.getSession(objRequest).getAttribute(RegistrationConfig.PATIENT_VO_MOD)!=null){
				PatientVO patVO = (PatientVO) WebUTIL.getSession(objRequest).getAttribute(RegistrationConfig.PATIENT_VO_MOD);

				defaultElement.setAttribute("defaultpatAddCountryCode", patVO.getPatAddCountryCode());
				defaultElement.setAttribute("defaultpatAddStateCode", patVO.getPatAddStateCode());
				defaultElement.setAttribute("defaultpatAddDistrictCode", patVO.getPatAddDistrictCode());

				defaultElement.setAttribute("defaultpatGenderCode", patVO.getPatGenderCode());
				defaultElement.setAttribute("defaultpatMaritalStatusCode", ((patVO.getPatMaritalStatusCode()!=null && !(patVO.getPatMaritalStatusCode().equals("")))?patVO.getPatMaritalStatusCode():"0") );
				defaultElement.setAttribute("defaultpatAgeUnit", patVO.getPatAgeUnit());
				if( patVO.getPatCasteCode() != null)
					defaultElement.setAttribute("defaultpatCasteCode", patVO.getPatCasteCode().trim());
				defaultElement.setAttribute("defaultpatOccupation", patVO.getPatOccupation());
				defaultElement.setAttribute("defaultpatReligionCode", ((patVO.getPatReligionCode()!=null && !(patVO.getPatReligionCode().equals("")))?patVO.getPatReligionCode():"0"));
				defaultElement.setAttribute("defaultpatDocType",((patVO.getPatDocType()!=null && !(patVO.getPatDocType().equals("")))?patVO.getPatDocType():"-1"));
				defaultElement.setAttribute("defaultisActualDob", patVO.getIsActualDob());
				defaultElement.setAttribute("defaultpatDOB", patVO.getPatDOB());
				defaultElement.setAttribute("defaultpatBirthPlace", patVO.getPatBirthPlace());
				
				
				defaultElement.setAttribute("defaultrequestBy",((patVO.getRequestBy()!=null && !(patVO.getRequestBy().equals("")))?patVO.getRequestBy():"-1"));
				defaultElement.setAttribute("defaultrequestRelation", patVO.getRequestRelation());
				defaultElement.setAttribute("defaultrequestByName", patVO.getRequestByName());
				defaultElement.setAttribute("defaultrequestByAddress", patVO.getRequestByAddress());
				defaultElement.setAttribute("defaultconstableDesig", patVO.getConstableDesig());
				defaultElement.setAttribute("defaultconstableBadgeNo", patVO.getConstableBadgeNo());
				
				mp=PatientModificationDATA.getPatModFormEssentials_AJAX(userVO,objHosVO.getState());
				
				
				renewalConfigElement=responseDocument.createElement("renewalConfig");
				rootElement.appendChild(renewalConfigElement);
				RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				renewalConfigElement.setAttribute("seniorCitizenAgeLimit", objRegistrationConfigVO.getSeniorCitizenAgeLimit());
				renewalConfigElement.setAttribute("seniorCitizenCatCode", objRegistrationConfigVO.getSeniorCitizenCatCode());

			}

		
			WebUTIL.setMapInSession(mp, objRequest,"PatientDetailModificationAction");
		
			setAddressTypeCombo(objNewPatientRegistrationSUP, objRequest);
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS),responseDocument,"patMaritalStatusCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_GENDER),responseDocument,"patGenderCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION),responseDocument,"patReligionCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_CASTE),responseDocument,"patCasteCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY),responseDocument,"patAddCountryCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_STATE),responseDocument,"patAddStateCode");

			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE),responseDocument,"patAgeUnit");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL),responseDocument,"patRefGnctdHospitalCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY),responseDocument,"patIsUrban");

			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL),responseDocument,"patRefGnctdHospitalDept");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL),responseDocument,"patOccupation");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE),responseDocument,"patAddDistrictCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE),responseDocument,"patAddTypeCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL),responseDocument,"requestRelation");
			createVerificationObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS),responseDocument,"patDocType");

			
			objRequest.getSession().setAttribute("DistrictList",(List<Entry>)mp.get(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE));
			
			
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
		}

		catch(Exception e){
			objStatus.add(Status.ERROR, "","Error");
			e.printStackTrace();
		}		
		finally
		{

			writeResponse(objResponse, outputString);
		}	
	}

	private static void createVerificationObject(List<Entry> list,Document responseDocument, String fieldName) 
	{

		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);
			String[] entryValueArray=entry.getValue().split("#");
			option.setAttribute("value", "{\"patDocType\":\""+entryValueArray[0]+"\",\"patDocIdSize\": \""+entryValueArray[1]+"\",\"patDocValidationType\":\""+entryValueArray[2]+"\",\"patDocTypeName\":\""+entry.getLabel()+"\"}");
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}

	private static void createOptionObject(List<Entry> list,Document responseDocument, String fieldName) 
	{

		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);

			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}


	private static void createOptionObject(HashSet<Entry> list,Document responseDocument, String fieldName) 
	{

		Element fieldElement=responseDocument.createElement(fieldName);
		for(Entry entry:list)
		{
			Element option= responseDocument.createElement("option");
			fieldElement.appendChild(option);

			option.setAttribute("value", entry.getValue());
			option.setAttribute("label", entry.getLabel());
		}
		responseDocument.getFirstChild().appendChild(fieldElement);
	}

	public static void writeResponse(HttpServletResponse resp, String output)
	{
		try
		{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}	


	/**
	 * Sets the Address specified to be modified in the Form Bean.
	 * 
	 * @param SUP - object of SplPatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 */

	public static void setAddressToModify(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		HttpSession session = WebUTIL.getSession(objRequest_p);
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_MODIFY;
		String outputString="";

		try
		{
			UserVO userVO=getUserVO(objRequest_p);

			AddressVO[] addressVO;
			addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			objSUP_p.setPatFBAddTypeCode((String)objRequest_p.getParameter("fbAddTypeCode"));

			// identifying the address and populate it into the formbean
			for (int i = 0; i < addressVO.length; i++)
			{
				if (addressVO[i].getPatAddTypeCode().equals(objSUP_p.getPatFBAddTypeCode()))
				{
					WebUTIL.populate(objSUP_p, addressVO[i]);
				}
			}

			objSUP_p.setPatAddTypeLable(objSUP_p.getPatAddType());

			objSUP_p.setAddModify(RegistrationConfig.IS_ADDRESS_MODIFIED);
			Collection col = getAddressTypesForModify(objSUP_p, objRequest_p, status);
			session.setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE, col);

			// in order to refresh we set that the verification doc is not added

			objSUP_p.setVerificationDocumentAdded(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_ADDED);

			if (objSUP_p.getPatAddPIN() != null)
			{
				if(objSUP_p.getPatAddPIN().equalsIgnoreCase("0"))
				{
					objSUP_p.setPatAddPIN("");
				}
			}
			List relationList = (ArrayList) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL);

			List relationList2 = (ArrayList) WebUTIL.removeEntriesfromOptions(relationList, RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);

			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, relationList2);
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			TransformerFactory trf= TransformerFactory.newInstance();
			Document responseDocument=null;
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			defaultElement.setAttribute("defaultaddModify" , RegistrationConfig.IS_ADDRESS_MODIFIED);
			defaultElement.setAttribute("defaultpatAddCountryCode" , objSUP_p.getPatAddCountryCode());
			defaultElement.setAttribute("defaultpatAddStateCode", objSUP_p.getPatAddStateCode());
			defaultElement.setAttribute("defaultpatAddStreet" , objSUP_p.getPatAddStreet());
			defaultElement.setAttribute("defaultpatAddCityLoc" , objSUP_p.getPatAddCityLoc());
			defaultElement.setAttribute("defaultpatAddCity" , objSUP_p.getPatAddCity());
			defaultElement.setAttribute("defaultpatAddPIN", objSUP_p.getPatAddPIN());
			defaultElement.setAttribute("defaultpatIsUrban" , ((objSUP_p.getPatIsUrban()!=null && !(objSUP_p.getPatIsUrban().equals("")))?objSUP_p.getPatIsUrban():"1"));
			defaultElement.setAttribute("defaultpatAddDistrict" , objSUP_p.getPatAddDistrict());

			defaultElement.setAttribute("defaultpatAddStateName" , objSUP_p.getPatAddStateName());
			defaultElement.setAttribute("defaultpatAddHNo", objSUP_p.getPatAddHNo());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objSUP_p.getPatAddDistrictCode());

			defaultElement.setAttribute("defaultpatAddLandMarks", objSUP_p.getPatAddLandMarks());
			defaultElement.setAttribute("defaultpatAddEmailId", objSUP_p.getPatAddEmailId());
			defaultElement.setAttribute("defaultdefaultpatAddTypeCode" , objSUP_p.getPatAddTypeCode());
			defaultElement.setAttribute("defaultpatAddPhoneNo" , objSUP_p.getPatAddPhoneNo());
			defaultElement.setAttribute("defaultpatAddPhoneOwner" ,(objSUP_p.getPatAddPhoneOwner()!=null && !(objSUP_p.getPatAddPhoneOwner().equals("")) )?objSUP_p.getPatAddPhoneOwner():"1" );
			defaultElement.setAttribute("defaultpatAddMobileNo",  objSUP_p.getPatAddMobileNo());
			defaultElement.setAttribute("defaultpatAddTypeLable", objSUP_p.getPatAddTypeLable());
			defaultElement.setAttribute("defaultpatEmgCntNo",  objSUP_p.getPatEmgCntNo());
			List lstDistrict=NewRegistrationDATA.getDistrict_AJAX(userVO,objSUP_p.getPatAddStateCode(),objSUP_p.getPatAddCountryCode());
			
			createOptionObject((List<Entry>)lstDistrict,responseDocument,"patAddDistrictCode");

			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);

		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			// objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
			// objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			e.printStackTrace();
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		catch (Exception e)
		{
			//System.out.println("Inside HisException");
			e.printStackTrace();
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		finally
		{
			writeResponse(objResponse, outputString);
			objRequest_p.setAttribute(Config.STATUS_OBJECT, status);
			session.setAttribute(RegistrationConfig.PATIENT_ADDRESS_STATUS, add_modify);
			//session.setAttribute(RegistrationConfig.PATIENT_ADDRESS_STATUS, add_modify);
		}
	}



	/**
	 * 
	 * @param SUP - object of SplPatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 */

	public static void setAddressToAdd(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_ADD; // address Activity Status
		HttpSession session = WebUTIL.getSession(objRequest_p);
		HospitalMstVO objHosVo= getHospitalVO(objRequest_p);
		String outputString="";
		try
		{
			AddressVO[] addressVO;
			addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			Collection colAllAddress = (Collection) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE);
			Collection colRemAdd = new HashSet();
			colRemAdd.addAll(colAllAddress);
			Iterator itr = colAllAddress.iterator();
			while (itr.hasNext())
			{
				Entry objEntry = (Entry) itr.next();
				String value = objEntry.getValue();
				for (int j = 0; j < addressVO.length; j++)
				{
					if (value.equals(addressVO[j].getPatAddTypeCode()))
					{
						colRemAdd.remove(objEntry);
						break;
					}
				}

			}

			List relationList = (ArrayList) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL);
			List relationList2 = (ArrayList) WebUTIL.removeEntriesfromOptions(relationList, RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, relationList2);

			session.setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE, colRemAdd);
			objSUP_p.resetAddress();
			objSUP_p.setAddModify(RegistrationConfig.IS_ADDRESS_ADDED);
			objSUP_p.setPatAddCountryCode(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			objSUP_p.setPatAddStateCode(objHosVo.getState());
			objSUP_p.setIsLocationComboReq("0");
			// in order to refresh we set that the verification doc is not added
			objSUP_p.setVerificationDocumentAdded(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_ADDED);

			objSUP_p.setPatFBAddTypeCode("");
			objSUP_p.setRequestBy("-1");
			objSUP_p.setRequestRelation("-1");
			objSUP_p.setRequestByName("");
			objSUP_p.setRequestByAddress("");

			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			TransformerFactory trf= TransformerFactory.newInstance();
			Document responseDocument=null;
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);

			defaultElement.setAttribute("defaultaddModify" , RegistrationConfig.IS_ADDRESS_ADDED);
			defaultElement.setAttribute("defaultpatAddStreet" , "");
			defaultElement.setAttribute("defaultpatAddCityLoc" , "");
			defaultElement.setAttribute("defaultpatAddCity" , "");
			defaultElement.setAttribute("defaultpatAddPIN", "");
			defaultElement.setAttribute("defaultpatIsUrban" , "1");


			defaultElement.setAttribute("defaultpatAddCountryCode", RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			defaultElement.setAttribute("defaultpatAddStateCode", objHosVo.getState());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objHosVo.getDistrictCode());

			defaultElement.setAttribute("defaultpatAddStateName" , "");
			defaultElement.setAttribute("defaultpatAddHNo", "");
			defaultElement.setAttribute("defaultpatAddLandMarks", "");
			defaultElement.setAttribute("defaultpatAddEmail", "");
			defaultElement.setAttribute("defaultpatAddTypeCode" , "");
			defaultElement.setAttribute("defaultpatAddPhoneNo" , "");
			defaultElement.setAttribute("defaultpatAddPhoneOwner" , "1");
			defaultElement.setAttribute("defaultpatAddMobileNo", "");
			
			
			
			createOptionObject((HashSet<Entry>)colRemAdd,responseDocument,"patAddTypeCode");
			createOptionObject((List<Entry>)objRequest_p.getSession().getAttribute("DistrictList"),responseDocument,"patAddDistrictCode");

			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
		}
		catch (Exception e)
		{
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			writeResponse(objResponse, outputString);
			objRequest_p.setAttribute(Config.STATUS_OBJECT, status);
			session.setAttribute(RegistrationConfig.PATIENT_ADDRESS_STATUS, add_modify);
		}
	}
	
	public static void setSameAsCurrentAddressToModify(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		Status status = new Status();
		HttpSession session = WebUTIL.getSession(objRequest_p);
		HospitalMstVO objHosVo= getHospitalVO(objRequest_p);
		String outputString="";
		try
		{
			AddressVO[] addressVO;
			UserVO userVO=getUserVO(objRequest_p);

			addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);			
			objSUP_p.setPatFBAddTypeCode((String)objRequest_p.getParameter("fbAddTypeCode"));
			String patAddTypeLabel=(String)objRequest_p.getParameter("fbAddressLabel");


			// identifying the address and populate it into the formbean
			for (int i = 0; i < addressVO.length; i++)
			{
				if (addressVO[i].getPatAddTypeCode().equals(objSUP_p.getPatFBAddTypeCode()))
				{
					WebUTIL.populate(objSUP_p, addressVO[i]);
				}
			}

			objSUP_p.setPatAddTypeLable(patAddTypeLabel);
			if (objSUP_p.getPatAddPIN() != null)
			{
				if(objSUP_p.getPatAddPIN().equalsIgnoreCase("0"))
				{
					objSUP_p.setPatAddPIN("");
				}
			}

			List relationList = (ArrayList) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL);
			List relationList2 = (ArrayList) WebUTIL.removeEntriesfromOptions(relationList, RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, relationList2);
			
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			TransformerFactory trf= TransformerFactory.newInstance();
			Document responseDocument=null;
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);

			defaultElement.setAttribute("defaultpatAddCountryCode" , objSUP_p.getPatAddCountryCode());
			defaultElement.setAttribute("defaultpatAddStateCode", objSUP_p.getPatAddStateCode());
			defaultElement.setAttribute("defaultpatAddStreet" , objSUP_p.getPatAddStreet());
			defaultElement.setAttribute("defaultpatAddCityLoc" , objSUP_p.getPatAddCityLoc());
			defaultElement.setAttribute("defaultpatAddCity" , objSUP_p.getPatAddCity());
			defaultElement.setAttribute("defaultpatAddPIN", objSUP_p.getPatAddPIN());
			defaultElement.setAttribute("defaultpatIsUrban",((objSUP_p.getPatIsUrban()!=null && !(objSUP_p.getPatIsUrban().equals("")))?objSUP_p.getPatIsUrban():"-1"));
			defaultElement.setAttribute("defaultpatIsUrbanCode",((objSUP_p.getPatIsUrban()!=null && !(objSUP_p.getPatIsUrban().equals("")))?objSUP_p.getPatIsUrban():"-1"));
			defaultElement.setAttribute("defaultpatAddDistrict" , objSUP_p.getPatAddDistrict());

			defaultElement.setAttribute("defaultpatAddStateName" , objSUP_p.getPatAddStateName());
			defaultElement.setAttribute("defaultpatAddHNo", objSUP_p.getPatAddHNo());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objSUP_p.getPatAddDistrictCode());

			defaultElement.setAttribute("defaultpatAddLandMarks", objSUP_p.getPatAddLandMarks());
			defaultElement.setAttribute("defaultpatAddEmailId", objSUP_p.getPatAddEmailId());
			defaultElement.setAttribute("defaultdefaultpatAddTypeCode" , objSUP_p.getPatAddTypeCode());
			defaultElement.setAttribute("defaultpatAddPhoneNo" , objSUP_p.getPatAddPhoneNo());
			defaultElement.setAttribute("defaultpatAddPhoneOwner" ,(objSUP_p.getPatAddPhoneOwner()!=null && !(objSUP_p.getPatAddPhoneOwner().equals("")) )?objSUP_p.getPatAddPhoneOwner():"1" );
			defaultElement.setAttribute("defaultpatAddMobileNo",  objSUP_p.getPatAddMobileNo());
			defaultElement.setAttribute("defaultpatAddTypeLable", objSUP_p.getPatAddTypeLable());
			List lstDistrict=NewRegistrationDATA.getDistrict_AJAX(userVO,objSUP_p.getPatAddStateCode(),objSUP_p.getPatAddCountryCode());
			
			createOptionObject((List<Entry>)lstDistrict,responseDocument,"patAddDistrictCode");	

			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
		}
		catch (Exception e)
		{
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			writeResponse(objResponse, outputString);
			objRequest_p.setAttribute(Config.STATUS_OBJECT, status);
		}
	}
	
	
	public static void setSameAsCurrentAddressToAdd(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_ADD; // address Activity Status
		HttpSession session = WebUTIL.getSession(objRequest_p);
		HospitalMstVO objHosVo= getHospitalVO(objRequest_p);
		String outputString="";
		try
		{
			AddressVO[] addressVO;
			UserVO userVO=getUserVO(objRequest_p);

			addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			Collection colAllAddress = (Collection) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE);
			Collection colRemAdd = new HashSet();
			colRemAdd.addAll(colAllAddress);
			Iterator itr = colAllAddress.iterator();
			while (itr.hasNext())
			{
				Entry objEntry = (Entry) itr.next();
				String value = objEntry.getValue();
				for (int j = 0; j < addressVO.length; j++)
				{
					if (value.equals(addressVO[j].getPatAddTypeCode()))
					{
						colRemAdd.remove(objEntry);
						break;
					}
				}

			}

			List relationList = (ArrayList) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL);
			List relationList2 = (ArrayList) WebUTIL.removeEntriesfromOptions(relationList, RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, relationList2);

			session.setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_EDIT_ADDRESS_TYPE, colRemAdd);
			
			//objSUP_p.setPatFBAddTypeCode((String)objRequest_p.getParameter("fbAddTypeCode"));
			objSUP_p.setPatFBAddTypeCode("1");

			// identifying the address and populate it into the formbean
			for (int i = 0; i < addressVO.length; i++)
			{
				if (addressVO[i].getPatAddTypeCode().equals(objSUP_p.getPatFBAddTypeCode()))
				{
					WebUTIL.populate(objSUP_p, addressVO[i]);
				}
			}
			
			objSUP_p.setAddModify(RegistrationConfig.IS_ADDRESS_ADDED);
			objSUP_p.setVerificationDocumentAdded(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_ADDED);

			objSUP_p.setPatFBAddTypeCode("");
			objSUP_p.setRequestBy("-1");
			objSUP_p.setRequestRelation("-1");
			objSUP_p.setRequestByName("");
			objSUP_p.setRequestByAddress("");

			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			TransformerFactory trf= TransformerFactory.newInstance();
			Document responseDocument=null;
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);

			defaultElement.setAttribute("defaultaddModify" , RegistrationConfig.IS_ADDRESS_ADDED);
			defaultElement.setAttribute("defaultpatAddCountryCode" , objSUP_p.getPatAddCountryCode());
			//defaultElement.setAttribute("defaultpatIsUrbanCode" , objSUP_p.getPatIsUrban());//added by sandip naik on 16/6/2017
			defaultElement.setAttribute("defaultpatIsUrban",((objSUP_p.getPatIsUrban()!=null && !(objSUP_p.getPatIsUrban().equals("")))?objSUP_p.getPatIsUrban():"0"));
			defaultElement.setAttribute("defaultpatIsUrbanCode",((objSUP_p.getPatIsUrban()!=null && !(objSUP_p.getPatIsUrban().equals("")))?objSUP_p.getPatIsUrban():"-1"));
//			defaultElement.setAttribute("defaultpatIsUrban" , objSUP_p.getPatIsUrban());
			defaultElement.setAttribute("defaultpatAddStreet" , objSUP_p.getPatAddStreet());
			defaultElement.setAttribute("defaultpatAddCityLoc" , objSUP_p.getPatAddCityLoc());
			defaultElement.setAttribute("defaultpatAddCity" , objSUP_p.getPatAddCity());
			defaultElement.setAttribute("defaultpatAddPIN", objSUP_p.getPatAddPIN());
			//defaultElement.setAttribute("defaultpatIsUrban" , objSUP_p.getPatIsUrban());
			defaultElement.setAttribute("defaultpatAddDistrict" , objSUP_p.getPatAddDistrict());

			defaultElement.setAttribute("defaultpatAddStateName" , objSUP_p.getPatAddStateName());
			defaultElement.setAttribute("defaultpatAddHNo", objSUP_p.getPatAddHNo());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objSUP_p.getPatAddDistrictCode());

			defaultElement.setAttribute("defaultpatAddLandMarks", objSUP_p.getPatAddLandMarks());
			defaultElement.setAttribute("defaultpatAddEmailId", objSUP_p.getPatAddEmailId());
			defaultElement.setAttribute("defaultdefaultpatAddTypeCode" , objSUP_p.getPatAddTypeCode());
			defaultElement.setAttribute("defaultpatAddPhoneNo" , objSUP_p.getPatAddPhoneNo());
			defaultElement.setAttribute("defaultpatAddPhoneOwner" ,(objSUP_p.getPatAddPhoneOwner()!=null && !(objSUP_p.getPatAddPhoneOwner().equals("")) )?objSUP_p.getPatAddPhoneOwner():"1");
			defaultElement.setAttribute("defaultpatAddMobileNo",  objSUP_p.getPatAddMobileNo());
			defaultElement.setAttribute("defaultpatAddTypeLable", objSUP_p.getPatAddTypeLable());
			
			List lstDistrict=NewRegistrationDATA.getDistrict_AJAX(userVO,objSUP_p.getPatAddStateCode(),objSUP_p.getPatAddCountryCode());
			createOptionObject((List<Entry>)lstDistrict,responseDocument,"patAddDistrictCode");
					
			createOptionObject((HashSet<Entry>)colRemAdd,responseDocument,"patAddTypeCode");

			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			trf.newTransformer().transform( new DOMSource(responseDocument),new StreamResult(baos));
			outputString=baos.toString();
			System.out.println("outputString "+outputString);
		}
		catch (Exception e)
		{
			status.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			writeResponse(objResponse, outputString);
			objRequest_p.setAttribute(Config.STATUS_OBJECT, status);
			session.setAttribute(RegistrationConfig.PATIENT_ADDRESS_STATUS, add_modify);
		}
	}


	/**
	 * gets address type for modification removes the additional address type from available address types
	 * 
	 * @param SUP - object of SplPatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 * @param status
	 * @return Collection
	 */

	protected static Collection getAddressTypesForModify(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
	{
		Collection colRemAdd = null;
		HttpSession session = WebUTIL.getSession(objRequest_p);
		try
		{
			Collection colAllAddress = (Collection) session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE);

			AddressVO[] colAvailableAddress = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			String selectedAddressType = objSUP_p.getPatFBAddTypeCode();

			colRemAdd = (Collection) colAllAddress.getClass().newInstance();
			colRemAdd.addAll(colAllAddress);
			getAddressTypes(colRemAdd, colAvailableAddress, selectedAddressType);
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			// objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
			// objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		catch (InstantiationException e)
		{
			//System.out.println("Inside HisException");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		catch (IllegalAccessException e)
		{
			//System.out.println("Inside HisException");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		catch (Exception e)
		{
			//System.out.println("Inside HisException");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		}
		finally
		{
		}
		return colRemAdd;
	}

	/**
	 * gets Address Types for Modification removes the additional address type from available address types
	 * 
	 * @param colRemAdd
	 * @param colAvailableAddress
	 * @param selectedAddressType
	 */
	protected static void getAddressTypes(Collection colRemAdd, AddressVO[] colAvailableAddress, String selectedAddressType)
	{
		try{
			Iterator it = colRemAdd.iterator();

			for (; it.hasNext();)
			{

				for (int j = 0; j < colAvailableAddress.length; j++)
				{
					if(it.hasNext())
					{
						Entry objEntry = (Entry) it.next();
						String value = objEntry.getValue();
						if (!value.equals(selectedAddressType) && value.equals(colAvailableAddress[j].getPatAddTypeCode()))
						{
							it.remove();
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * updates patient address record
	 * 
	 * @param SUP - object of SplPatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 * @return
	 */

	public static int updateRecord(PatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		AddressVO _addressVO = null;
		int x = 0;
		String strErrMsg="",strIsDuplicatePatient="";

		try
		{
			UserVO userVO = getUserVO(objRequest_p);
			HttpSession session = WebUTIL.getSession(objRequest_p);
			HospitalMstVO OBJhvO= getHospitalVO(objRequest_p);
			// Old Patient Dtl
			PatientVO patientVO = (PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);
			PatientVO patientVOOldData = new PatientVO();
			HelperMethods.populate(patientVOOldData, patientVO);
			String patCat = patientVO.getPatPrimaryCatCode();

			// Verify Document
			PatientModificationVO _PatientModificationVO = new PatientModificationVO();
			_PatientModificationVO.setProcessType(RegistrationConfig.PROCESS_TYPE_PATIENT_DTL);
			objSUP_p.setIsActualDob(objSUP_p.getTempIsActualDOB());
			objSUP_p.setSeatId(userVO.getSeatId());
			WebUTIL.populate(_PatientModificationVO, objSUP_p);
			String[] frmVerDoc = objSUP_p.getArrSelectedVerifyDocs();
			String strVerDoc = "";
			/*Modify Date			: 24thNov'14
			  Reason	(CR/PRS)	: Secondary UHID check incorporation
			  Modify By				: Sheeldarshi */
			if(WebUTIL.getSession(objRequest_p).getAttribute("isDuplicatePatientSession")=="1" && WebUTIL.getSession(objRequest_p).getAttribute("SelectedVerifyDocsSession")!=null)
			{	
				frmVerDoc= (String[])WebUTIL.getSession(objRequest_p).getAttribute("SelectedVerifyDocsSession");
				objSUP_p.setArrSelectedVerifyDocs(frmVerDoc);
			}	
			//End
			for (int verIdx = 0; frmVerDoc != null && verIdx < frmVerDoc.length; verIdx++)
			{
				String strCode = frmVerDoc[verIdx].split("#")[0];
				//String st[] = frmVerDoc[verIdx].split("\\|");
				
				String strCodeName = frmVerDoc[verIdx].split("\\|")[1];
				System.out.println("Code Name "+strCodeName);
				
				strVerDoc += strCode+"|"+strCodeName+"#";
			}
			_PatientModificationVO.setStrDocumentCode(strVerDoc);

			AddressVO[] addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			/*
			 * 
			 * not checking is employee for now
			 * 
			 * if (!(patCat.equals(Config.PRIMARY_CATEGORY_EMPLOYEE_CODE)))
			{*/
//			if (objSUP_p.getPatAddCountryCode() != null && objSUP_p.getPatAddCountryCode().equals(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE))
//			{
//				objSUP_p.setPatAddStateName("");
//			}
//			else
//			{
//				objSUP_p.setPatAddStateCode("");
//			}
			if(objSUP_p.getPatAddState()!=null && objSUP_p.getPatAddState().indexOf(',')!=-1){
				String[] strArrPatAddState= objSUP_p.getPatAddState().split("\\,"); 
				if(!strArrPatAddState[0].trim().equals(""))
					objSUP_p.setPatAddState(strArrPatAddState[0].trim());
				else
					objSUP_p.setPatAddState(strArrPatAddState[1].trim());
			}
			
			if(objSUP_p.getPatAddDistrict()!=null && objSUP_p.getPatAddDistrict().indexOf(',')!=-1){
				String[] strArrPatAddDistrict= objSUP_p.getPatAddDistrict().split("\\,"); 
				if(!strArrPatAddDistrict[0].trim().equals(""))
					objSUP_p.setPatAddDistrict(strArrPatAddDistrict[0].trim());
				else
					objSUP_p.setPatAddDistrict(strArrPatAddDistrict[1].trim());
			}

			if (!objSUP_p.getPatAddStateCode().equalsIgnoreCase(OBJhvO.getState()))
			{
				//objSUP_p.setPatAddCityLocCode("");
				objSUP_p.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_FALSE);
			}
			else
			{
				objSUP_p.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_TRUE);
			}
//			if (objSUP_p.getPatAddDistrictCode() != null && !objSUP_p.getPatAddDistrictCode().equals(""))
//			{
//				objSUP_p.setPatAddDistrict("");
//			}


			// Request By Detail
			patientVOOldData.setRequestBy(objSUP_p.getRequestBy());
			if (objSUP_p.getRequestBy().equals("1"))///relative
			{
				patientVOOldData.setRequestByName(objSUP_p.getRequestByName());
				patientVOOldData.setRequestByAddress(objSUP_p.getRequestByAddress());
			}

			// Address Detail
			if (objSUP_p.getAddModify().equals(RegistrationConfig.IS_ADDRESS_MODIFIED))
			{
				for (int i = 0; i < addressVO.length; i++)
				{
					if (addressVO[i].getPatAddTypeCode().equals(objSUP_p.getPatFBAddTypeCode()))
					{
						String addType = addressVO[i].getPatAddType();
						String sNo = addressVO[i].getSerialNo();
						{
							HelperMethods.populate(addressVO[i], objSUP_p);
						}
						addressVO[i].setSerialNo(sNo);
						addressVO[i].setPatAddType(addType);
						addressVO[i].setPatAddTypeCode(objSUP_p.getPatFBAddTypeCode());
						_addressVO = addressVO[i];
						break;
					}
				}
			}
			if (objSUP_p.getAddModify().equals(RegistrationConfig.IS_ADDRESS_ADDED))
			{
				_addressVO = new AddressVO();
				HelperMethods.populate(_addressVO, objSUP_p);
				//_addressVO.setPatAddTypeCode(objSUP_p.getPatFBAddTypeCode());
			}

			if(objSUP_p.getAddModify().equals(RegistrationConfig.IS_ADDRESS_ADDED)||objSUP_p.getAddModify().equals(RegistrationConfig.IS_ADDRESS_MODIFIED))
			{
				if(!_addressVO.getPatAddTypeCode().equals("1"))
				{
					for (int i = 0; i < addressVO.length; i++)
					{
						if (addressVO[i].getPatAddTypeCode().equals("1"))
						{
							HelperMethods.populate(objSUP_p,addressVO[i]);
							break;
						}
					}
				}
			}
			{
				HelperMethods.populate(patientVO, objSUP_p);
			}
			patientVO.setPatPrimaryCatCode(patCat);
			_PatientModificationVO.setRequestBy(objSUP_p.getRequestBy());
			if (objSUP_p.getRequestBy().equals("1"))//RegistrationConfig.MODIFICATION_REQUESTED_BY_RELATIVE))
			{
				_PatientModificationVO.setRequestByName(objSUP_p.getRequestByName());
				_PatientModificationVO.setRequestByAddress(objSUP_p.getRequestByAddress());
				_PatientModificationVO.setRequestRelation(objSUP_p.getRequestRelation());
			}

			if (objSUP_p.getRequestBy().equals("0"))//RegistrationConfig.MODIFICATION_REQUESTED_BY_SELF))
			{
				patientVOOldData.setRequestRelation(RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);
				_PatientModificationVO.setRequestRelation(RegistrationConfig.RELATION_MASTER_VALUE_FOR_SELF);
			}
			PatientIdVO objPatientIdVO_p = new PatientIdVO();

			HelperMethods.populate(objPatientIdVO_p, objSUP_p);
			objPatientIdVO_p.setPatDocType(objSUP_p.getPatDocType());
			objPatientIdVO_p.setPatIsAlternateId("1");
			objPatientIdVO_p.setPatIdNo(objSUP_p.getPatCardNo());

			String strUniqueIdDuplicyFlag ="0";
			strUniqueIdDuplicyFlag = DskPatientModificationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO_p,objSUP_p.getPatNationalId());

			/*if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					objSUP_p.setErrorMessage("Duplicate National Id/ Aadhar No");
					return 1;
				}else if(strUniqueIdDuplicyFlag.equals("2")){
					objSUP_p.setErrorMessage("Patient With Card Number : "+ objSUP_p.getPatCardNo()+ " already registered.");
					return 1;
				}
			}*/
			
			/*Modify Date			: 5thDec'14
			  Reason	(CR/PRS)	: Secondary UHID check incorporation
			  Modify By				: Sheeldarshi */
			//To Check the Duplicacy on the Secondary Alternate Id Basis
			/*if((objSUP_p.getPatIdNo()!=null && !objSUP_p.getPatIdNo().equals("") && objSUP_p.getPatCatDocIsAlternateId().equals("1")))
			{
				objPatientIdVO_p.setPatIdNo(objSUP_p.getPatIdNo());
				objPatientIdVO_p.setPatDocType(objSUP_p.getPatCatDocCode());
				strUniqueIdDuplicyFlag = NewRegistrationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO_p,objSUP_p.getPatNationalId());
					
				if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
					if(strUniqueIdDuplicyFlag.equals("1")){
						strErrMsg="Duplicate National Id/ Aadhar No";
						
						return 1;
					}else if(strUniqueIdDuplicyFlag.equals("2")){
						strErrMsg="Patient with this Id No ("+objSUP_p.getPatIdNo()+ ") already registered.";
						
						return 1;
					}
				}
			
			}*/
			//End:Sheeldarshi
		/*  ## 		Modification Log							
	 		##		Modify Date				:29thJan'15 
	 		##		Reason	(CR/PRS)		:Image upload integration in patient detail module
	 		##		Modify By				:Sheeldarshi */
			
			  if(WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC)!=null){
				//FormFile imageFile=(FormFile)WebUTIL.getSession(_req).getAttribute(RegistrationConfig.UPLOADED_FILE);
				//patientVO.setImageFile((byte[])WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				  
				byte[] filContent=IOUtils.toByteArray((FileInputStream)WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC));
				//patientVO.setImageFile((byte[])WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				patientVO.setImageFile(filContent);
			    patientVO.setImageFileName((String)WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME));
			    //patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
			}
			//End:Sheeldarshi 
			PatientModificationDATA.updateRecord(patientVO, patientVOOldData, objSUP_p.getAddModify(), _addressVO, _PatientModificationVO, userVO,objPatientIdVO_p, objRequest_p);

			//Audit Log Integration
			
			try{
				Map mp= new LinkedHashMap();
				int y=0;
				
				PatientModificationAuditVO objPatientModificationAuditVO = new PatientModificationAuditVO();
				AddressVO[] addVoArr = (AddressVO[]) objRequest_p.getSession().getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
				
				HelperMethods.populate(objPatientModificationAuditVO, patientVO);
				if(_addressVO!=null){
					for(int i=0;i<addVoArr.length;i++){
						if(addVoArr[i].getPatAddTypeCode().equals(_addressVO.getPatAddTypeCode())){
							y=i;
							//addVoArr = (AddressVO[])ArrayUtils.removeElement(addressVO, i);
							break;
						}
					}
					//ArrayUtils.add(addVoArr,_addressVO);					
				}
				

				for(int i=0;i<addVoArr.length;i++){
					if(addVoArr[i].getPatAddTypeCode().equals("1")){
						y=i;
						break;
					}
				}
				AddressVO objAddresVo = new AddressVO();				
				HelperMethods.populate(objAddresVo, addVoArr[y]);
				objPatientModificationAuditVO.setPatAddress(objAddresVo);
				
				mp.put("save_1"  , objPatientModificationAuditVO);
				mp.put("save_2"  , addVoArr);
				//mp.put("save_2"  , _addressVO);				
				/*String[] arrKeyVariables= new String[1];
				arrKeyVariables[0]=patientVO.getPatCrNo();
				AuditlogDATA.saveUpdateAuditLog(mp,ControllerUTIL.getUserVO(objRequest_p),objRequest_p,arrKeyVariables);*/
			}
			catch(Exception e){
				e.printStackTrace();
			}



			objSUP_p.setPatCrNo("");
			objSUP_p.setErrorMessage("Patient Details Modified Successfully");
			objRequest_p.getSession().removeAttribute("keyPatientVO");
		}
		/*Modify Date			: 24thNov'14
		  Reason	(CR/PRS)	: Secondary UHID check incorporation
		  Modify By				: Sheeldarshi */
		catch(HisDuplicateRecordException e){
			strIsDuplicatePatient="1";
			objSUP_p.setisDuplicatePatient("1");
			
			WebUTIL.setAttributeInSession(objRequest_p, "SelectedVerifyDocsSession", objSUP_p.getArrSelectedVerifyDocs());
			WebUTIL.setAttributeInSession(objRequest_p, "isDuplicatePatientSession", "1");
			x=1;
			strErrMsg = "Duplicate Record Exist";
			objStatus.add(Status.ERROR_AE, "Duplicate Record Exist", "");
		}
		//End:Sheeldarshi
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, "Application Execution Exception", "");
			e.printStackTrace();
		}
		finally
		{
			objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return x;
	}


}
