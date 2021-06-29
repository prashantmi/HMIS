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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisPatientStillUnknownException;
import registration.transactions.controller.actionsupport.UnknownToKnownSUP;
import registration.transactions.controller.data.DskPatientModificationDATA;
import registration.transactions.controller.data.NewRegistrationDATA;
import registration.transactions.controller.data.UnknownToKnownDATA;
import vo.registration.AddressVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientModificationVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.UnknownChangeDtlVO;
import vo.registration.UnknownVerificationDtlVO;

public class UnknownToKnownUTIL extends ControllerUTIL
{

	public static void setPatientDtlForMod(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		try
		{
			setInitialPatientDetailEssentials(objSUP_p, objRequest_p);
			PatientVO patVO = new PatientVO();
			patVO.setPatCrNo(objSUP_p.getPatCrNo());
			if (patVO != null)
			{
				String strVisitType= "3";
				if(objRequest_p.getParameter("crno")!=null)
					objSUP_p.setPatCrNo(objRequest_p.getParameter("crno"));
				patVO.setPatCrNo(objSUP_p.getPatCrNo());

				PatDetailUTIL.getPatientDtlByCrno(objSUP_p, objRequest_p);

				if(WebUTIL.getStatus(objRequest_p).getMessage("007").equalsIgnoreCase("Patient Details Not Found"))
				{
					objSUP_p.setErrorMessage("Patient Details Not Found");
					throw new HisRecordNotFoundException("Patient Details Not Found");
				}
				else if(WebUTIL.getStatus(objRequest_p).getMessage("007").equalsIgnoreCase("Current Patient Category is invalid please change the Patient Category first"))
				{
					objSUP_p.setErrorMessage("Current Patient Category is invalid please change the Patient Category first");
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
						System.out.println("---------------"+objSUP_p.getCrNoToModify()+"--------------");
						patVO.setPatCrNo(objSUP_p.getCrNoToModify());
					}
					if (patVO != null)
					{
						//if(!patVO.getPatRegCatCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))
					//	{
//
						//	throw new HisPatientStillUnknownException("Patient registered as opd/emergency patient.Details can only be modified at MRD/Registration/Emergency desk");
						//}
						
						if(!patVO.getIsUnknown().equals("1"))
						{
							objSUP_p.setErrorMessage("Patient is not Unknown. Conversion cannot be done");
							throw new HisRecordNotFoundException("Patient is not Unknown. Conversion cannot be done");
						}
						if(patVO.getPatIsDead().equals("1"))
						{
							objSUP_p.setErrorMessage("Patient is Dead. Details cannot be modified");
							throw new HisPatientStillUnknownException("Patient is Dead. Details cannot be modified");
						}

						objSUP_p.setBeforeModificationAge(patVO.getPatAge());
						UnknownToKnownUTIL.setAddressVOs(objSUP_p, objRequest_p, status);
						status.add(Status.TRANSINPROCESS, "", "");
						WebUTIL.populate(objSUP_p, patVO);
						if(WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR)!=null)
						{
							AddressVO[]	addressVO  =(AddressVO[]) WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
							for (int i = 0; i < addressVO.length; i++)
							{
								if (addressVO[i].getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT))
								{
									WebUTIL.populate(objSUP_p,addressVO[i] );
									patVO.setPatAddress(addressVO[i]);
								}
							}
						}
						if (patVO.getIsUnknown().equalsIgnoreCase(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE))
						{
							if(patVO.getPatFirstName().length()>9){
								objSUP_p.setPatFirstName(patVO.getPatFirstName().substring(9));
								patVO.setPatFirstName(patVO.getPatFirstName().substring(9));// Check if the patient has been converted to known or not
							}
							else{
								objSUP_p.setPatFirstName(patVO.getPatFirstName());
								patVO.setPatFirstName(patVO.getPatFirstName());// Check if the patient has been converted to known or not
							}
						}
						Date dtDOB = WebUTIL.getDateFromString(patVO.getPatDOB(), "dd-MMM-yyyy");
						String strDOB = WebUTIL.getCustomisedSysDate(dtDOB, "dd-MM-yyyy");
						objSUP_p.setPatDOB(strDOB);
						//patVO.setPatDOB(strDOB);
						objSUP_p.setPatIdNo(patVO.getPatIdNo());
						patVO.setPatIdNo(patVO.getPatIdNo());
						objSUP_p.setBeforeModificationAge(patVO.getPatAge());
						String strCaseCode = objSUP_p.getPatCasteCode();
						
						
						if(strCaseCode != null)
						{
							strCaseCode.trim();
							objSUP_p.setPatCasteCode(strCaseCode.trim());
						}
						if (objSUP_p.getPatAddPIN() != null)
						{
							if(objSUP_p.getPatAddPIN().equalsIgnoreCase("0"))
							{
								patVO.setPatAddPIN("");
								objSUP_p.setPatAddPIN("");
							}
						}
					}
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
	public static void setInitialPatientDetailEssentials(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		status.add(Status.NEW, "", "");
		try
		{
			Map mp = UnknownToKnownDATA.getInitialPatientModAddDetailEssentials();
			WebUTIL.setMapInSession(mp, objRequest_p,"UnknownToKnownAction");
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

	public static void setAddressTypeCombo(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p)
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

	protected static void setAddressVOs(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
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
		addressVO = UnknownToKnownDATA.getAddressEssentials(patVO, userVO);
		session.setAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR, addressVO);
		objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
	}
	/**
	 * sets patient's address details in AddressVO
	 * 
	 * @param SUP - object of PatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 * @param status
	 */

	protected static PatientBroughtByDtlVO setBroughtByDtl(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
	{
		HttpSession session = objRequest_p.getSession();
		PatientBroughtByDtlVO objPatientBroughtByDtlVO_p = new PatientBroughtByDtlVO();
		UserVO userVO = getUserVO(objRequest_p);
		objPatientBroughtByDtlVO_p.setPatCrNo(objSUP_p.getPatCrNo());
		PatientBroughtByDtlVO broughtByVO;
		broughtByVO = UnknownToKnownDATA.getBroughtByDetails(objPatientBroughtByDtlVO_p, userVO);
		session.setAttribute(RegistrationConfig.EMG_BROUGHTBY_DETAILS, broughtByVO);
		return broughtByVO;
	}





	@SuppressWarnings("unchecked")
	public static void populateRegistrationFormEssentials_AJAX(UnknownToKnownSUP objNewPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession){	
		Status objStatus=new Status();
		String outputString="";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		Map mp=new HashMap();
		try
		{
			HospitalMstVO OBJhvO= getHospitalVO(objRequest);

			UserVO userVO=getUserVO(objRequest);
			userVO.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			userVO.setTariffID(RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			Element defaultElement=responseDocument.createElement("defaults");
			rootElement.appendChild(defaultElement);
			PatientBroughtByDtlVO patientBroughtByDtlVO = new PatientBroughtByDtlVO(); 
			if(WebUTIL.getSession(objRequest).getAttribute(RegistrationConfig.EMG_BROUGHTBY_DETAILS)!=null){
				 patientBroughtByDtlVO = (PatientBroughtByDtlVO) WebUTIL.getSession(objRequest).getAttribute(RegistrationConfig.EMG_BROUGHTBY_DETAILS);
			}
			
			if(WebUTIL.getSession(objRequest).getAttribute(RegistrationConfig.PATIENT_VO_MOD)!=null){
				PatientVO patVO = (PatientVO) WebUTIL.getSession(objRequest).getAttribute(RegistrationConfig.PATIENT_VO_MOD);
				if(patVO.getPatCasteCode()!=null){
					patVO.setPatCasteCode(patVO.getPatCasteCode().trim());
				}
				defaultElement.setAttribute("defaultpatAddCountryCode", RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
				defaultElement.setAttribute("defaultpatAddStateCode", OBJhvO.getState());
				defaultElement.setAttribute("defaultpatAddDistrictCode", OBJhvO.getDistrictCode());

				defaultElement.setAttribute("defaultpatGenderCode", patVO.getPatGenderCode());
				defaultElement.setAttribute("defaultpatMaritalStatusCode", ((patVO.getPatMaritalStatusCode()!=null && !(patVO.getPatMaritalStatusCode().equals("")))?patVO.getPatMaritalStatusCode():"0"));//By Mukund
				defaultElement.setAttribute("defaultpatAgeUnit", patVO.getPatAgeUnit());
				defaultElement.setAttribute("defaultpatCasteCode", ((patVO.getPatCasteCode()!=null && !(patVO.getPatCasteCode().equals("")))?patVO.getPatCasteCode():"0"));//By Mukund
				defaultElement.setAttribute("defaultpatOccupation", patVO.getPatOccupation());
				defaultElement.setAttribute("defaultpatReligionCode", ((patVO.getPatReligionCode()!=null && !(patVO.getPatReligionCode().equals("")))?patVO.getPatReligionCode():"0"));//By Mukund
				defaultElement.setAttribute("defaultpatDocType", ((patVO.getPatDocType()!=null && !(patVO.getPatDocType().equals("")))?patVO.getPatDocType():"-1"));//By Mukund
				defaultElement.setAttribute("defaultisActualDob", patVO.getIsActualDob());
				defaultElement.setAttribute("defaultpatDOB", patVO.getPatDOB());
				defaultElement.setAttribute("defaultpatBirthPlace", patVO.getPatBirthPlace());
				defaultElement.setAttribute("defaultrequestBy", ((patVO.getRequestBy()!=null && !(patVO.getRequestBy().equals("")))?patVO.getRequestBy():"-1"));//By Mukund
				defaultElement.setAttribute("defaultrequestRelation", patVO.getRequestRelation());
				defaultElement.setAttribute("defaultrequestByName", patVO.getRequestByName());
				defaultElement.setAttribute("defaultrequestByAddress", patVO.getRequestByAddress());
				defaultElement.setAttribute("defaultconstableDesig", patVO.getConstableDesig());
				defaultElement.setAttribute("defaultconstableBadgeNo", patVO.getConstableBadgeNo());
				
				
				//defaultElement.setAttribute("defaultbroughtByRelationCode", patientBroughtByDtlVO.getBroughtByRelationCode());
				

			}

			mp=UnknownToKnownDATA.getEmgPatModFormEssentials_AJAX(userVO,OBJhvO.getState());
			//Start:Sheeldarshi
			//Reason:Bug 10166 - System should be able to validate senior citizen category
			RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
			defaultElement.setAttribute("seniorCitizenAgeLimit", objRegistrationConfigVO.getSeniorCitizenAgeLimit());
			defaultElement.setAttribute("seniorCitizenCatCode", objRegistrationConfigVO.getSeniorCitizenCatCode());
			//End
			WebUTIL.setMapInSession(mp, objRequest,"UnknownToKnownAction");

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

	public static void setAddressToModify(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		HttpSession session = WebUTIL.getSession(objRequest_p);
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_MODIFY;
		String outputString="";

		try
		{
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
			defaultElement.setAttribute("defaultpatIsUrban" , objSUP_p.getPatIsUrban());
			defaultElement.setAttribute("defaultpatAddDistrict" , objSUP_p.getPatAddDistrict());

			defaultElement.setAttribute("defaultpatAddStateName" , objSUP_p.getPatAddStateName());
			defaultElement.setAttribute("defaultpatAddHNo", objSUP_p.getPatAddHNo());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objSUP_p.getPatAddDistrictCode());

			defaultElement.setAttribute("defaultpatAddLandMarks", objSUP_p.getPatAddLandMarks());
			defaultElement.setAttribute("defaultpatAddEmailId", objSUP_p.getPatAddEmailId());
			defaultElement.setAttribute("defaultdefaultpatAddTypeCode" , objSUP_p.getPatAddTypeCode());
			defaultElement.setAttribute("defaultpatAddPhoneNo" , objSUP_p.getPatAddPhoneNo());
			defaultElement.setAttribute("defaultpatAddPhoneOwner" ,objSUP_p.getPatAddPhoneOwner());
			defaultElement.setAttribute("defaultpatAddMobileNo",  objSUP_p.getPatAddMobileNo());
			defaultElement.setAttribute("defaultpatAddTypeLable", objSUP_p.getPatAddTypeLable());
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

	public static void setAddressToAdd(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_ADD; // address Activity Status
		HttpSession session = WebUTIL.getSession(objRequest_p);
		HospitalMstVO objHospitalMstVo = getHospitalVO(objRequest_p);
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
			objSUP_p.setPatAddStateCode(objHospitalMstVo.getState());
			objSUP_p.setPatFBAddTypeCode("");
			//objSUP_p.setRequestBy("-1");
			//objSUP_p.setRequestRelation("-1");
			//objSUP_p.setRequestByName("");
			//objSUP_p.setRequestByAddress("");

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
			defaultElement.setAttribute("defaultpatIsUrban" , "");


			defaultElement.setAttribute("defaultpatAddCountryCode", RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			defaultElement.setAttribute("defaultpatAddStateCode", objHospitalMstVo.getState());
			defaultElement.setAttribute("defaultpatAddDistrictCode", objHospitalMstVo.getDistrictCode());

			defaultElement.setAttribute("defaultpatAddStateName" , "");
			defaultElement.setAttribute("defaultpatAddHNo", "");
			defaultElement.setAttribute("defaultpatAddLandMarks", "");
			defaultElement.setAttribute("defaultpatAddEmail", "");
			defaultElement.setAttribute("defaultpatAddTypeCode" , "");
			defaultElement.setAttribute("defaultpatAddPhoneNo" , "");
			defaultElement.setAttribute("defaultpatAddPhoneOwner" , "");
			defaultElement.setAttribute("defaultpatAddMobileNo", "");
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

	protected static Collection getAddressTypesForModify(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
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

	public static int updateRecord(UnknownToKnownSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p)
	{
		Status objStatus = new Status();
		AddressVO _addressVO = null;
		int x = 0;
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		
		Document responseDocument=null;
		String strIsSavedSuccussfulFlag="0", strSavedOrErrMsg="",strIsFormFieldResetFlag="0", strPrintDivContent="", strErrMsg="",strIsDuplicatePatient="";
		try
		{
			responseDocument=dbf.newDocumentBuilder().newDocument();
			Element rootElement=responseDocument.createElement("root");
			responseDocument.appendChild(rootElement);
			
			HospitalMstVO objHospitalMstVo = getHospitalVO(objRequest_p);
			UserVO userVO = getUserVO(objRequest_p);
			HttpSession session = WebUTIL.getSession(objRequest_p);
			// Old Patient Dtl
			PatientVO patientVO = (PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);
			PatientVO patientVOOldData = new PatientVO();
			HelperMethods.populate(patientVOOldData, patientVO);
			String patCat = patientVO.getPatPrimaryCatCode();
			AddressVO addressVO = new AddressVO();
			// Verify Document
			objSUP_p.setIsActualDob(objSUP_p.getTempIsActualDOB());
		
			//AddressVO[] addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
			
			/*if (objSUP_p.getPatAddCountryCode() != null && objSUP_p.getPatAddCountryCode().equals(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE))
			{
				objSUP_p.setPatAddStateName("");
			}
			else
			{
				objSUP_p.setPatAddStateCode("");
			}

			if (!objSUP_p.getPatAddStateCode().equalsIgnoreCase(objHospitalMstVo.getState()))
			{
				objSUP_p.setPatAddCityLocCode("");
				objSUP_p.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_FALSE);
			}
			else
			{
				objSUP_p.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_TRUE);
			}
			if (objSUP_p.getPatAddDistrictCode() != null && !objSUP_p.getPatAddDistrictCode().equals(""))
			{
				objSUP_p.setPatAddDistrict("");
			}
			*/

			/*Start : Surabhi
			 * Date : 9th Nov 16
			 * Reason : For modifying the address part in the process*/
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
			if (!objSUP_p.getPatAddStateCode().equalsIgnoreCase(objHospitalMstVo.getState()))
			{
				//objSUP_p.setPatAddCityLocCode("");
				objSUP_p.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_FALSE);
			}
			else
			{
				objSUP_p.setIsAddressDelhi(RegistrationConfig.IS_ADDRESS_DELHI_TRUE);
			}



			UnknownVerificationDtlVO UnknownVerificationDtlVO_p = new UnknownVerificationDtlVO();
			//UnknownVerificationDtlVO_p.setProcessType(RegistrationConfig.PROCESS_TYPE_PATIENT_DTL);
			objSUP_p.setIsActualDob(objSUP_p.getTempIsActualDOB());
			WebUTIL.populate(UnknownVerificationDtlVO_p, objSUP_p);
			String[] frmVerDoc=null; 
			/*Modify Date			: 24thNov'14
			  Reason	(CR/PRS)	: Secondary UHID check incorporation
			  Modify By				: Sheeldarshi */
			if(WebUTIL.getSession(objRequest_p).getAttribute("isDuplicatePatientSession")=="1" && WebUTIL.getSession(objRequest_p).getAttribute("SelectedVerifyDocsSession")!=null)
			{	
				frmVerDoc= (String[])WebUTIL.getSession(objRequest_p).getAttribute("SelectedVerifyDocsSession");
				objSUP_p.setArrSelectedVerifyDocs(frmVerDoc);
			}	
			//End
			 frmVerDoc = objSUP_p.getArrSelectedVerifyDocs();
			String docCode="";
			
			for(int i=0;i<frmVerDoc.length;i++){
				docCode +=(docCode == "" ? frmVerDoc[i].split("#")[0] : "&&" + frmVerDoc[i].split("#")[0]);
			}
			
			
			
			String strVerDoc = "";
			for (int verIdx = 0; frmVerDoc != null && verIdx < frmVerDoc.length; verIdx++)
			{
				String strCode = frmVerDoc[verIdx].split("#")[0];
				//String st[] = frmVerDoc[verIdx].split("\\|");
				
				String strCodeName = frmVerDoc[verIdx].split("\\|")[1];
				System.out.println("Code Name "+strCodeName);
				
				strVerDoc += strCode+"|"+strCodeName+"#";
			}
			UnknownVerificationDtlVO_p.setStrDocumentId(strVerDoc);
			UnknownVerificationDtlVO_p.setStrDocumentCode(docCode);
			UnknownVerificationDtlVO_p.setFileType("4");

			UnknownChangeDtlVO UnknownChangeDtlVO_p=new UnknownChangeDtlVO();
			WebUTIL.populate(UnknownChangeDtlVO_p, objSUP_p);	
			if(patientVO.getRegisterDate()!=null)
			UnknownChangeDtlVO_p.setRegisterDate(patientVO.getRegisterDate().substring(0, 12));	
			// Request By Detail
			if (objSUP_p.getRequestBy().equals("1"))///relative
			{
				patientVOOldData.setRequestByName(objSUP_p.getRequestByName());
				patientVOOldData.setRequestByAddress(objSUP_p.getRequestByAddress());
			}

			if (objSUP_p.getRequestBy().equals("2"))
			{
				patientVOOldData.setRequestByName(objSUP_p.getRequestByName());
				patientVOOldData.setRequestByAddress(objSUP_p.getRequestByAddress());
				patientVOOldData.setConstableBadgeNo(objSUP_p.getConstableBadgeNo());
				patientVOOldData.setConstableDesig(objSUP_p.getConstableDesig());
			}
			
			
		
			// Address Detail
				/*for (int i = 0; i <= addressVO.length; i++)
				{
					if (addressVO[i].getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT))
					{
						String addType = addressVO[i].getPatAddType();
						String sNo = addressVO[i].getSerialNo();
						HelperMethods.populate(addressVO[i], objSUP_p);
						addressVO[i].setSerialNo(sNo);
						addressVO[i].setPatAddType(addType);
						addressVO[i].setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
						_addressVO = addressVO[i];
						break;
					}
				}

			
					for (int i = 0; i < addressVO.length; i++)
					{
						if (addressVO[i].getPatAddTypeCode().equals("1"))
						{
							HelperMethods.populate(objSUP_p,addressVO[i]);
							break;
						}
					}*/
				
			
			{
				HelperMethods.populate(patientVO, objSUP_p);
			}
			
			HelperMethods.populate(addressVO, patientVO);
			patientVO.setPatPrimaryCatCode(patCat);
			PatientIdVO objPatientIdVO_p = new PatientIdVO();

			HelperMethods.populate(objPatientIdVO_p, objSUP_p);
			objPatientIdVO_p.setPatDocType(objSUP_p.getPatDocType());
			objPatientIdVO_p.setPatIsAlternateId("1");
			objPatientIdVO_p.setPatIdNo(objSUP_p.getPatCardNo());

			String strUniqueIdDuplicyFlag ="0";
			strUniqueIdDuplicyFlag = DskPatientModificationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO_p,objSUP_p.getPatNationalId());

			if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					objSUP_p.setErrorMessage("Duplicate National Id/ Aadhar No");
					return 1;
				}else if(strUniqueIdDuplicyFlag.equals("2")){
					objSUP_p.setErrorMessage("Patient With Card Number : "+ objSUP_p.getPatCardNo()+ " already registered.");
					return 1;
				}
			}
			
			/*Modify Date			: 24thNov'14
			  Reason	(CR/PRS)	: Secondary UHID check incorporation
			  Modify By				: Sheeldarshi */
			//To Check the Duplicacy on the Secondary Alternate Id Basis
			if((objSUP_p.getPatIdNo()!=null && !objSUP_p.getPatIdNo().equals("") && objSUP_p.getPatCatDocIsAlternateId().equals("1")))
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
			
			}
			//End:Sheeldarshi
			
			
			/*  ## 		Modification Log							
	 		##		Modify Date				:29thJan'15 
	 		##		Reason	(CR/PRS)		:Image upload integration in patient detail module
	 		##		Modify By				:Sheeldarshi */
			
			  if(WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT)!=null){
				//FormFile imageFile=(FormFile)WebUTIL.getSession(_req).getAttribute(RegistrationConfig.UPLOADED_FILE);
				//patientVO.setImageFile((byte[])WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				byte[] filContent=IOUtils.toByteArray((FileInputStream)WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				//patientVO.setImageFile((byte[])WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT));
				patientVO.setImageFile(filContent);
			    patientVO.setImageFileName((String)WebUTIL.getSession(objRequest_p).getAttribute(FileTransferConfig.KEY_UPLOADED_FILE_NAME));
			    //patientVO.setIsImageUploaded(RegistrationConfig.IMAGE_UPLOADED_TRUE);
			}
			//End:Sheeldarshi 
				
			patientVO.setIsUnknown(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE);

			UnknownToKnownDATA.updateRecord(patientVO, patientVOOldData, objSUP_p.getAddModify(), addressVO, userVO,objPatientIdVO_p,UnknownVerificationDtlVO_p,UnknownChangeDtlVO_p,objRequest_p);

			objSUP_p.setPatCrNo("");
			objSUP_p.setErrorMessage("Patient Details Modified Successfully");
			System.out.println("FullSlip :"+strPrintDivContent);
			objRequest_p.getSession().removeAttribute("keyPatientVO");
			
		}
		catch(HisDuplicateRecordException e){
			strIsDuplicatePatient="1";
			objSUP_p.setisDuplicatePatient("1");
			
			WebUTIL.setAttributeInSession(objRequest_p, "SelectedVerifyDocsSession", objSUP_p.getArrSelectedVerifyDocs());
			WebUTIL.setAttributeInSession(objRequest_p, "isDuplicatePatientSession", "1");
			x=1;
			strErrMsg = "Duplicate Record Exist";
		}
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
