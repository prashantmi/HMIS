package registration.transactions.controller.util;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

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
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisPatientStillUnknownException;
import registration.transactions.controller.actionsupport.DskPatientDetailModificationSUP;
import registration.transactions.controller.data.DskPatientModificationDATA;
import registration.transactions.controller.data.EmgPatDetailModificationDATA;
import registration.transactions.controller.data.patDtlModifiacationDATA;
import vo.registration.AddressVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;

public class DskPatientModificationUTIL extends ControllerUTIL
{

	public static void setPatientDtlForMod(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		try
		{
			setInitialPatientDetailEssentials(objSUP_p, objRequest_p);
			PatientVO patVO = new PatientVO();
			if(objRequest_p.getParameter("crno")!=null)
				objSUP_p.setPatCrNo(objRequest_p.getParameter("crno"));
			patVO.setPatCrNo(objSUP_p.getPatCrNo());
			if (patVO != null)
			{
				String strVisitType= "1";
				PatDetailUTIL.getPatientDtlByCrnoDailyPatient(objSUP_p, objRequest_p,strVisitType);
				if(WebUTIL.getStatus(objRequest_p).getMessage("007").equalsIgnoreCase("Patient Details Not Found"))
				{
					objSUP_p.setErrorMessage("Patient Details Not Found. Either The Patient Is Not Registred Or Modification Time Ellapsed");
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
						System.out.println("---------------"+objSUP_p.getCrNoToModify()+"--------------");
						patVO.setPatCrNo(objSUP_p.getCrNoToModify());
					}
					if (patVO != null)
					{

						//if(!patVO.getPatRegCatCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL))//By Mukund on 08.05.2017
						if(!(patVO.getPatRegCatCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL)||patVO.getPatRegCatCode().equalsIgnoreCase(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL)))
						{
							objSUP_p.setErrorMessage("Patient Registered As Emergency/Special.Details can only be modified at MRD Patient Deatail Modification");
							objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));

							throw new HisPatientStillUnknownException("Patient registered as opd.Details can only be modified at MRD/Registration Desk");
						}
						if(patVO.getIsUnknown().equals("1"))
						{
							objSUP_p.setErrorMessage("Patient Still Unknown. Use Unknown to Known Conversion");
							objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
							throw new HisRecordNotFoundException("Patient Still Unknown. Use Unknown to Known Conversion");
						}
						//To Check whether the CRNo is merged or not,Added by Singaravelan on 16-Jul-2015 
						if(patVO.getPatIsMerged()!=null && patVO.getPatIsMerged().equals("2"))
						{
							objSUP_p.setAfterGo("0");
							objSUP_p.setErrorMessage("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO());
							throw new HisRecordNotFoundException("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO() );
						}
						if(!patVO.getSeatId().equals(getUserVO(objRequest_p).getSeatId()))
						{
							objSUP_p.setErrorMessage("User Does Not has Permission  To Modify Patient Details. It Can Be Modified At MRD Patient Modification Process");
							objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));

							throw new HisRecordNotFoundException("User Does Not has Permission  To Modify Patient Details. It Can Be Modified At MRD Patient Modification Process");
						}
						if(!patVO.getIsOpdAllowed().equals("1"))
						{
							objSUP_p.setErrorMessage("Modification Time Elapsed");
							objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));

							throw new HisRecordNotFoundException("User Does Not has Permission  To Modify Patient Details. It Can Be Modified At MRD Patient Modification Process");
						}
						if(patVO.getPatIsDead().equals("1"))
						{
							objSUP_p.setErrorMessage("Patient is Dead. Details cannot be modified");
							objSUP_p.setPatCrNo((String)objRequest_p.getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
							throw new HisPatientStillUnknownException("Patient is Dead. Details cannot be modified");
						}
						objSUP_p.setBeforeModificationAge(patVO.getPatAge());
						DskPatientModificationUTIL.setAddressVOs(objSUP_p, objRequest_p, status);
						status.add(Status.TRANSINPROCESS, "", "");
						WebUTIL.populate(objSUP_p, patVO);
						if(WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR)!=null)
						{
							AddressVO[]	addressVO  =(AddressVO[]) WebUTIL.getSession(objRequest_p).getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
							for (int i = 0; i < addressVO.length; i++)
							{
								if (addressVO[i].getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT))
								{
									if(addressVO[i].getPatAddCountryCode().equalsIgnoreCase("IND"))
									{
										addressVO[i].setPatAddState("");
										addressVO[i].setPatAddDistrict("");
									}
										
									WebUTIL.populate(objSUP_p,addressVO[i] );
									patVO.setPatAddress(addressVO[i]);
								}
							}
						}

						Date dtDOB = WebUTIL.getDateFromString(patVO.getPatDOB(), "dd-MMM-yyyy");
						String strDOB = WebUTIL.getCustomisedSysDate(dtDOB, "dd-MM-yyyy");
						objSUP_p.setPatDOB(strDOB);
						objSUP_p.setDefaultPatDOB(strDOB);
						objSUP_p.setDefaultPatAge(patVO.getPatAge());
						objSUP_p.setDefaultPatAgeUnit(patVO.getPatAgeUnit());
						//patVO.setPatDOB(strDOB);
						objSUP_p.setPatIdNo(patVO.getPatIdNo());
						patVO.setPatIdNo(patVO.getPatIdNo());
						objSUP_p.setBeforeModificationAge(patVO.getPatAge());
						String strCaseCode =  patVO.getPatCasteCode();
						if(strCaseCode != null)
						{
							strCaseCode.trim();
							objSUP_p.setPatCasteCode(strCaseCode.trim());
						}
					}
				}
			}
			objSUP_p.setAfterGo("1");
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.PATIENT_VO_MOD, patVO);	

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
	public static void setInitialPatientDetailEssentials(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status status = new Status();
		status.add(Status.NEW, "", "");
		try
		{
			Map mp = DskPatientModificationDATA.getInitialPatientModAddDetailEssentials();
			WebUTIL.setMapInSession(mp, objRequest_p,"DskPatientDetailModificationAction");
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

	public static void setAddressTypeCombo(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
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

	protected static void setAddressVOs(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
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
		addressVO = DskPatientModificationDATA.getAddressEssentials(patVO, userVO);
		
		session.setAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR, addressVO);
		objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
	}






	public static void populateRegistrationFormEssentials_AJAX(DskPatientDetailModificationSUP objNewPatientRegistrationSUP,HttpServletRequest objRequest,
			HttpServletResponse objResponse, Map<Object, Object> mapSession){	
		Status objStatus=new Status();
		String outputString="";
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		TransformerFactory trf= TransformerFactory.newInstance();
		Document responseDocument=null;
		Map mp=new HashMap();
		Element renewalConfigElement=null;
		try
		{
			UserVO userVO=getUserVO(objRequest);
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
				defaultElement.setAttribute("defaultpatAddStateCode",  patVO.getPatAddStateCode());
				defaultElement.setAttribute("defaultpatAddDistrictCode",  patVO.getPatAddDistrictCode());
				defaultElement.setAttribute("defaultpatIsUrban" , ((patVO.getPatIsUrban()==null||patVO.getPatIsUrban().equals(""))?"-1":patVO.getPatIsUrban()));

				defaultElement.setAttribute("defaultpatGenderCode", patVO.getPatGenderCode());
				defaultElement.setAttribute("defaultpatMaritalStatusCode",  ((patVO.getPatMaritalStatusCode()!=null && !patVO.getPatMaritalStatusCode().equals(""))?patVO.getPatMaritalStatusCode():"0"));
				defaultElement.setAttribute("defaultpatAgeUnit", patVO.getPatAgeUnit());
				if(patVO.getPatCasteCode() != null)
					defaultElement.setAttribute("defaultpatCasteCode", patVO.getPatCasteCode().trim());
				else
					defaultElement.setAttribute("defaultpatCasteCode", "0");
				defaultElement.setAttribute("defaultpatOccupation", ((patVO.getPatOccupation()!=null && !patVO.getPatOccupation().equals(""))?patVO.getPatOccupation():"0"));
				defaultElement.setAttribute("defaultpatReligionCode", ((patVO.getPatReligionCode()!=null && !patVO.getPatReligionCode().equals(""))?patVO.getPatReligionCode():"0")); 
				defaultElement.setAttribute("defaultpatDocType", ((patVO.getPatDocType()!=null && !patVO.getPatDocType().equals(""))?patVO.getPatDocType():"-1"));  
				defaultElement.setAttribute("defaultisActualDob", patVO.getIsActualDob());
				defaultElement.setAttribute("defaultpatDOB", patVO.getPatDOB());
				defaultElement.setAttribute("defaultpatBirthPlace", patVO.getPatBirthPlace());
				mp=EmgPatDetailModificationDATA.getEmgPatModFormEssentials_AJAX(userVO,patVO.getPatAddStateCode());
				//Start:Sheeldarshi
				//Reason:Bug 10166 - System should be able to validate senior citizen category
				renewalConfigElement=responseDocument.createElement("renewalConfig");
				rootElement.appendChild(renewalConfigElement);
				RegistrationConfigVO objRegistrationConfigVO = (RegistrationConfigVO)mp.get(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG);
				renewalConfigElement.setAttribute("seniorCitizenAgeLimit", objRegistrationConfigVO.getSeniorCitizenAgeLimit());
				renewalConfigElement.setAttribute("seniorCitizenCatCode", objRegistrationConfigVO.getSeniorCitizenCatCode());

				//End

			}


//			mp=DskPatientModificationDATA.getPatModFormEssentials_AJAX(userVO,patVO.getPatAddStateCode());
			WebUTIL.setMapInSession(mp, objRequest ,"DskPatientDetailModificationAction");

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
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL),responseDocument,"patRefGnctdHospitalCode");
			createOptionObject((List<Entry>)mp.get(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL),responseDocument,"patRefGnctdHospitalDept");
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

	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
			resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}	


	/**
	 * Sets the Address specified to be modified in the Form Bean.
	 * 
	 * @param SUP - object of SplPatientModificationSUP
	 * @param objRequest_p -HttpServletRequest
	 */

	public static void setAddressToModify(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
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

	public static void setAddressToAdd(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p,HttpServletResponse objResponse )
	{
		Status status = new Status();
		String add_modify = RegistrationConfig.PATIENT_ADDRESS_ADD; // address Activity Status
		HttpSession session = WebUTIL.getSession(objRequest_p);
		String outputString="";
		try
		{
			HospitalMstVO oBJhHospitalMstVO = ControllerUTIL.getHospitalVO(objRequest_p);
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
			objSUP_p.setPatAddStateCode(oBJhHospitalMstVO.getState());
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
			defaultElement.setAttribute("defaultpatIsUrban" , "");


			defaultElement.setAttribute("defaultpatAddCountryCode", RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			defaultElement.setAttribute("defaultpatAddStateCode", oBJhHospitalMstVO.getState());
			defaultElement.setAttribute("defaultpatAddDistrictCode", oBJhHospitalMstVO.getDistrictCode());

			defaultElement.setAttribute("defaultpatAddStateName" , "");
			defaultElement.setAttribute("defaultpatAddHNo", "");
			defaultElement.setAttribute("defaultpatAddLandMarks", "");
			defaultElement.setAttribute("defaultpatAddEmail", "");
			defaultElement.setAttribute("defaultdefaultpatAddTypeCode" , "");
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

	protected static Collection getAddressTypesForModify(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p, Status status)
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

	public static int updateRecord(DskPatientDetailModificationSUP objSUP_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		AddressVO _addressVO = null;
		int x = 0;
		try
		{
			
			UserVO userVO = getUserVO(objRequest_p);
			HospitalMstVO hosVo = getHospitalVO(objRequest_p);
			HttpSession session = WebUTIL.getSession(objRequest_p);
			// Old Patient Dtl
			PatientVO patientVO = (PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);
			PatientVO patientVOOldData = new PatientVO();
			HelperMethods.populate(patientVOOldData, patientVO);
			String patCat = patientVO.getPatPrimaryCatCode();
			objSUP_p.setIsActualDob(objSUP_p.getTempIsActualDOB());
			AddressVO[] addressVO = (AddressVO[]) session.getAttribute(RegistrationConfig.REGDESK_ADDRESSVO_ARR);
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

			
			if (!objSUP_p.getPatAddStateCode().equalsIgnoreCase(hosVo.getState()))
			{
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

			// Address Detail
			for (int i = 0; i < addressVO.length; i++)
			{
				//if (addressVO[i].getPatAddTypeCode().equals(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT))
				//{
					String addType = addressVO[i].getPatAddType();
					String sNo = addressVO[i].getSerialNo();
					HelperMethods.populate(addressVO[i], objSUP_p);
					addressVO[i].setSerialNo(sNo);
					addressVO[i].setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
					_addressVO = addressVO[i];
					
					break;
				//}
			}

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
			HelperMethods.populate(patientVO, objSUP_p);
			patientVO.setPatPrimaryCatCode(patCat);
			PatientIdVO objPatientIdVO_p = new PatientIdVO();

			HelperMethods.populate(objPatientIdVO_p, objSUP_p);
			objPatientIdVO_p.setPatDocType(objSUP_p.getPatDocType());
			objPatientIdVO_p.setPatIsAlternateId("1");
			objPatientIdVO_p.setPatIdNo(objSUP_p.getPatCardNo());


			String strUniqueIdDuplicyFlag ="0";
			/*strUniqueIdDuplicyFlag = DskPatientModificationDATA.checkUniqueIdDuplicy(userVO, objPatientIdVO_p,objSUP_p.getPatNationalId());

			if(strUniqueIdDuplicyFlag!=null && !strUniqueIdDuplicyFlag.equals("")){
				if(strUniqueIdDuplicyFlag.equals("1")){
					objSUP_p.setErrorMessage("Duplicate National Id/ Aadhar No");
					return 1;
				}else if(strUniqueIdDuplicyFlag.equals("2")){
					objSUP_p.setErrorMessage("Patient With Card Number : "+ objSUP_p.getPatCardNo()+ " already registered.");
					return 1;
				}
			}*/
			DskPatientModificationDATA.updateRecordDsk(patientVO, patientVOOldData, objSUP_p.getAddModify(), _addressVO,  userVO,objPatientIdVO_p);
			objSUP_p.setPatCrNo("");
			objSUP_p.setErrorMessage("Patient Details Modified Successfully");
			objRequest_p.getSession().removeAttribute("keyPatientVO");

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

	/**
	 * sets all cr no for modification
	 * retrieves a list of patients registered in the last specified duration
	 * 
	 * @param _request - HttpServletRequest
	 */
	public static void setAllCRNoForModification(HttpServletRequest _request){
		Status objStatus=new Status();
		PatientVO patientVO[]=null;
		try{
			UserVO userVO =getUserVO(_request);
			//setSysdate(_request);

			////this value is set in SpecialClinicRegDeskUTIL for special clinic
			String deskType=(String)_request.getAttribute(RegistrationConfig.REGISTRATION_DESK_TYPE);

			deskType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
			String episodeVisitType;
			if(deskType==null)
				deskType="";
			if(deskType.equalsIgnoreCase(RegistrationConfig.REGISTRATION_DESK_TYPE_SPECIAL))
				episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_SPECIAL;
			//else if(deskType.equalsIgnoreCase(RegistrationConfig.REGISTRATION_DESK_TYPE_OPD))
			else 
				episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_OPD;
				//episodeVisitType=RegistrationConfig.EPISODE_VISIT_TYPE_EMG;
			
			////////////
			patientVO=patDtlModifiacationDATA.getCRNoForModification(userVO,episodeVisitType);



			WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO_LIST,patientVO);
			objStatus.add(Status.NEW,"","");	
		}

		catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
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

		finally
		{
			WebUTIL.setStatus(_request,objStatus);			

		}
	}
	public static boolean checkIfRefDeptIsOther(String refDept,HttpServletRequest request){
		boolean flag=false;
		if(refDept.equals("")){
			return true;
		}
		HttpSession session=request.getSession();
		List deptList=(ArrayList)session.getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL);
		if(deptList!=null){
			for(int i=0;i<deptList.size();i++){
				Entry entry=(Entry)deptList.get(i);
				if(entry.getValue().equalsIgnoreCase(refDept.trim())){
					flag=true;
					break;
				}
			}
		}
		return flag;
	}

}
