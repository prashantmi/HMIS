package registration.bo;

//last updated on july 06-07-06>>>priya



import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.masters.controller.data.UnitConsultantMstDATA;
import registration.config.RegistrationConfig;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.dao.CreditAvailDtlDAO;
import registration.dao.BeneficiaryPatientDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EssentialDAO;
import registration.dao.MlcDAO;
import registration.dao.MlcParameterDAO;
import registration.dao.PatCatDocMapMstDAO;
import registration.dao.PatientDAO;
import registration.dao.PrimaryCategoryChangeDAO;
import registration.dao.RegEssentialDAO;
import registration.dao.VerificationDocDAO;
import vo.registration.CreditAvailDetailVO;
import vo.registration.BeneficiaryPatientVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcVO;
import vo.registration.PatientIdVO;
import vo.registration.PatientVO;
import vo.registration.PrimaryCategoryChangeVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import vo.registration.UnitConsultantVO;
import registration.config.RegistrationConfigurationBean;


/**
 * @author CDAC Noida
 * 
 */
public class RegEssentialBO {
	
	public Map<String, Object> getRegistrationFormInitialEssentials(UserVO _userVO,String isUnitWiseRegistration) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstDepartment = null;
		List lstDeptUnit=null;	
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigurationBean _objRegConfigVO2 = new RegistrationConfigurationBean();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		RenewalConfigVO objRenewalConfigVO=null;
			

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			try{
				//if(!isUnitWiseRegistration.equals("1"))
					lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,"0","1","1");
					essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);

				//else
					lstDeptUnit = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,"0","1","5");
					essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT_UNIT,lstDeptUnit);
				
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT_UNIT,lstDeptUnit);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			try{
				lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO, RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			try{
				lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
				mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
				objRenewalConfigVO=mapOfRenewalVoOnKeyPatCat.get("10");
				if(objRenewalConfigVO==null)
					throw new HisRecordNotFoundException("Default Renewal Rules for OPD is not defined for this hospital");
				else					
					essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);

			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			try{
				_objRegConfigVO2=objEssentialDAO.getRegistrationConfigDtl2(_userVO,"1","1");
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean, _objRegConfigVO2);
			}
			catch(Exception e)
			{}
			
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	
	public Map<String, Object> getSplRegistrationFormInitialEssentials(UserVO _userVO) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstDepartment = null;
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		RenewalConfigVO objRenewalConfigVO=null;
		//By Mukund
		RegistrationConfigurationBean _objRegConfigVO2 = new RegistrationConfigurationBean();

		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			try{
				lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,"0","2","4");
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			try{
				lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO, RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			try{
				lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
				mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
				objRenewalConfigVO=mapOfRenewalVoOnKeyPatCat.get("10");
				if(objRenewalConfigVO==null)
					throw new HisRecordNotFoundException("Default Renewal Rules for Special Clinics is not defined for this hospital");
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			//By Mukund on 26.03.2018
			try{
				_objRegConfigVO2=objEssentialDAO.getRegistrationConfigDtl2(_userVO,"1","1");
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean, _objRegConfigVO2);
			}
			catch(Exception e)
			{
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean, _objRegConfigVO2);
				throw new HisRecordNotFoundException(e.getMessage());
			}//End On 26.03.2018
			
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	
	
	
	
	public Map<String, Object> getEmgRegistrationFormInitialEssentials(UserVO _userVO) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstDepartment = null;
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		RenewalConfigVO objRenewalConfigVO=null;
		//By Mukund
		RegistrationConfigurationBean _objRegConfigVO2 = new RegistrationConfigurationBean();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			try{
				//lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,"0","1","1");
				lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_CASUALITY,"0","3","5");
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			try{
				lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO, RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			try{
				lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
				mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
				objRenewalConfigVO=mapOfRenewalVoOnKeyPatCat.get("10");
				if(objRenewalConfigVO==null)
					throw new HisRecordNotFoundException("Default Renewal Rules for Casuality is not defined for this hospital");
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			//By Mukund on 26.03.2018
			try{
				_objRegConfigVO2=objEssentialDAO.getRegistrationConfigDtl2(_userVO,"1","1");
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean, _objRegConfigVO2);
			}
			catch(Exception e)
			{
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean, _objRegConfigVO2);
				throw new HisRecordNotFoundException(e.getMessage());
			}//End On 26.03.2018
			
			
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public Map<String, Object> getRegistrationFormEssentials_AJAX(UserVO _userVO, String strState, HttpServletRequest objRequest_p) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstEducation = null;
		List lstCaste = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstDeptUnit=null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List lstUnit = null;
		List occupationDetail = null;
		String amount = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List relationList = null;
		List<Entry> lstVerificationDoc=null;
		List lstClient=null;
		List lstRelation=null;
		List lstRMOEmployees=null;
		List lstLetterType=null;
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		List lstPaymentMode = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			lstDepartment=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
			lstDeptUnit=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT_UNIT);
			lstPrimaryCat=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
			mapOfRenewalVoOnKeyPatCat=(Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			if(lstDepartment==null || lstDepartment.size()==0){
				try{
					lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,"0","1","1");
					lstDeptUnit = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,"0","1","5");
				
				}catch(Exception e){
					//e.printStackTrace();
				}finally{
					essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
					essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT_UNIT,lstDeptUnit);
				}
			}else{
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT_UNIT,lstDeptUnit);
			}
				
			
			if(lstPrimaryCat==null || lstPrimaryCat.size()==0){
				try{
					lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO, RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
				}catch(Exception e){
					//e.printStackTrace();
				}finally{
					essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
				}
			}else{
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			}
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			lstRMOEmployees = objEssentialDAO.getRMOEmployees(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RMO_EMP,lstRMOEmployees);
			//End
			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
			
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = getStateBasedOnCountry_AJAX(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			/*lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);*/
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO, Config.SUPER_HOSPITAL_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			lstDistrict = getDistrictBasedOnState_AJAX(_userVO,strState,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,relationList);
			
			occupationDetail = objEssentialDAO.getOccupationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
						
			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);
			
			//Start:Sheeldarshi
			//Reason: Change for adding Organisation combo on All Registration Pages
			//Date: 14thJune'15
			lstClient=objEssentialDAO.getClientList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST,lstClient);
			//End
			
			lstRelation=objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,lstRelation);
			
			//Start:Sheeldarshi
			//Reason: Change for adding Letter type combo on All Registration Pages for credit category
			//Date: 14thJune'15
			lstLetterType=objEssentialDAO.getLetterTypeList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST,lstLetterType);
			//End 
			
			
			/*voRenewalConfigVO=objEssentialDAO.getRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_RENEWEL_CONFIG,voRenewalConfigVO);*/
			
			if(mapOfRenewalVoOnKeyPatCat==null || mapOfRenewalVoOnKeyPatCat.size()==0){
				lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
				mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
			}else{
				essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
			}
			voRegistrationConfig=objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
			
			lstPaymentMode=objEssentialDAO.getPaymentModeList(_userVO);
			essentialMap.put(RegistrationConfig.PAYMENT_MODE_OPTION_LIST,lstPaymentMode);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public List getStateBasedOnCountry_AJAX(UserVO _userVO, String strCountryCode_p) 
	{
		
		List lstState = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstState = objEssentialDAO.getStateBasedOnCountry(strCountryCode_p,_userVO);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstState;
	}
	public List getDistrictBasedOnState_AJAX(UserVO _userVO, String strStateCode_p,String strCountryCode) 
	{
		
		List lstDistrict = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstDistrict = objEssentialDAO.getDistrictList(_userVO,strStateCode_p);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstDistrict;
	}
	public List getRefDeptBasedOnRefHosp_AJAX(UserVO objUserVO_p, String strRefHospCode_p) 
	{
		
		List lstReferDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstReferDepartment = objEssentialDAO.getReferDepartmentList(objUserVO_p, strRefHospCode_p);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException("No Referred Department Found.");
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact Systaem Admin");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact Systaem Admin");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact Systaem Admin");
		} finally {
			tx.close();
		}
		return lstReferDepartment;
	}
	public List getPatDtlOnPatCatId(UserVO userVO_p,String patPrimaryCatCode_p,String strSearchCatName_p,String strSearchCatId_p, String strMode_p)
	{
		
		List lstPatientJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstPatientJsonObjString = objEssentialDAO.getPatDtlOnPatCatId(userVO_p,patPrimaryCatCode_p,strSearchCatName_p,strSearchCatId_p, strMode_p);
			//voPatient.setPatAddress(patAddress);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	public List getPatDtlOnPatMobile(UserVO userVO_p,String searchId,String searchValue, String strMode_p)
	{
		
		List lstPatientJsonObjString=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstPatientJsonObjString = objEssentialDAO.getPatDtlOnPatMobile(userVO_p,searchId,searchValue, strMode_p);
			//voPatient.setPatAddress(patAddress);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstPatientJsonObjString;
	}
	
	public Map<String, Object> getNewPatientRegEssential(UserVO _userVO,String strStateCode) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstEducation = null;
		List lstCaste = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List lstUnit = null;
		List occupationDetail = null;
		String amount = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List relationList = null;
		List<Entry> lstVerificationDoc=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			//GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			/*
			 * lstAllDepartment=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);
			 */
			lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,"0","1","1");
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO,RegistrationConfig.NEW_REGISTRATION_TARIFF_ID);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			/*
			 * lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);
			 */

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);
			
			/*lstEducation = objEssentialDAO.getEducation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_EDUCATION,lstEducation);*/

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
					
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			/*lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					lstLocation);
			*/
			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);

			
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			//lstNationality = objEssentialDAO.getNationality(_userVO);
			//essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			/*String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(_userVO,new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);*/
			
			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO,"");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			//lstDepartment = objEssentialDAO.getDeptUnit(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,Config.MODULE_ID_REGISTRATION);
			//essentialMap.put(Config.ESSENTIALBO_OPTION_DEPARTMENT,lstDepartment);

			lstDistrict = objEssentialDAO.getDistrictList(_userVO,strStateCode);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					relationList);
			
			occupationDetail = objEssentialDAO.getOccupationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
			
						
			 lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			 essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);

			/*
			 * amount=
			 * objEssentialDAO.getBillAmountBasedOnCategory(RegistrationConfig.DEFAULT_PRIMARY_CATEGORY,_userVO);
			 * essentialMap.put(RegistrationConfig.AMOUNT_COLLECTED, amount);
			 *///
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}


	public Map<String, Object> getPatModFormEssentials_AJAX(UserVO _userVO,String strStateCode) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstEducation = null;
		List lstCaste = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List lstUnit = null;
		List occupationDetail = null;
		String amount = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List relationList = null;
		List<Entry> lstVerificationDoc=null;
		List lstAddressType = null;
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
	
			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
			
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = getStateBasedOnCountry_AJAX(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO,"");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			lstDistrict = getDistrictBasedOnState_AJAX(_userVO,strStateCode,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,relationList);
			
			occupationDetail = objEssentialDAO.getOccupationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
						
			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);
			
			lstAddressType = objEssentialDAO.getAddressType(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE, lstAddressType);
			
			voRegistrationConfig=objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public List<Entry> getVerificationDocuments(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> verificationDocuments = null;
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
	
			verificationDocuments = objEssentialDAO
					.getVerificationDocuments(_userVO);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return verificationDocuments;
	}
	
	public String checkUniqueIdDuplicay(UserVO objUserVO_p, PatientIdVO objPatientIdVO_p,String strNationalId_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strUniqueIdOrAadharExistFlag="0";
		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
	
			strUniqueIdOrAadharExistFlag=objRegEssentialDAO.checkUniqueIdDuplicay(objUserVO_p, objPatientIdVO_p, strNationalId_p);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return strUniqueIdOrAadharExistFlag;
	
	}
	
	public String checkUniqueIdDuplicayWithCRNo(UserVO objUserVO_p, PatientIdVO objPatientIdVO_p,String strNationalId_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strUniqueIdOrAadharExistFlag="0";
		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
	
			strUniqueIdOrAadharExistFlag=objRegEssentialDAO.checkUniqueIdDuplicayWithCrNo(objUserVO_p, objPatientIdVO_p, strNationalId_p);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return strUniqueIdOrAadharExistFlag;
	
	}

	public String getBillAmountBasedOnCategory(String _CategoryCodeCode,
			UserVO _userVO) {

		String amountAndIdRequired = "";

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			amountAndIdRequired = objEssentialDAO.getBillAmountBasedOnCategory(
					_CategoryCodeCode, _userVO);
			return amountAndIdRequired;
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	
	/* Start : Surabhi
	 * Reason : Bill amount to be added for externally referred patients
	 * date : 7th oct 2016
	 * */
	public String getBillAmountBasedOnCategoryForExt(PatientVO objPatientVO_p,
			UserVO _userVO) {

		String amountAndIdRequired = "";

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			amountAndIdRequired = objEssentialDAO.getBillAmountBasedOnCategoryforExt(
					objPatientVO_p, _userVO);
			return amountAndIdRequired;
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}
	// end
	
	/*Get Refer Essentials*/
	public Map<String, Object> getPatientReferralEssentials(String _crNo, String _deptCode, UserVO _userVO)
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstSplUnit = null;
		List lstAllDepartment = null;
		List lstRefHospital = null;
		//List<Entry> lstDeptUnits = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			//Get Hospital and external Institutes list for Referral
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);
						
			// All Departments with General Unit
			lstAllDepartment = objEssentialDAO.getDeptForPatientReferal(_crNo,_userVO,RegistrationConfig.UNIT_TYPE_GENERAL);
			essentialMap.put(RegistrationConfig.PATIENT_REFERRAL_OPTION_INTERNALDEPT, lstAllDepartment);
			
			// All Special Units
			lstSplUnit = objEssentialDAO.getSpeialUnitsForPatientReferal(_crNo,_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY);			
			essentialMap.put(RegistrationConfig.PATIENT_REFERRAL_OPTION_SPLUNITS, lstSplUnit);
				
			
				
			// All General Units of Current Department
			//lstDeptUnits = opdEssentialDAO.getGeneralUnitsListByDept(_deptCode,_userVO);
			//essentialMap.put(OpdConfig.OPD_ESSENTIALBO_LIST_DEPARTMENTS_GENERAL_UNITS, lstDeptUnits);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	/**
	 * For New Dept/Emergency New Dept Visit -- Aadil
	 * @param strCrNo_p
	 * @param objUserVO_p
	 * @param strUnitType_p
	 * @param strRosterType_p [strRosterType_p=1 for New Dept Visit,strRosterType_p=3 for Emg New Dept Visit]  
	 * @param strDeptMode_p [strDeptMode_p=3 for New Dept Visit,strDeptMode_p=6 for Emg New Dept Visit]  
	 * @return Map
	 */
	public Map<String, List> getNewDeptVisitEssential(String strCrNo_p, UserVO objUserVO_p, String strUnitType_p, String strRosterType_p, String strDeptMode_p) {

		Map<String, List> essentialMap = new HashMap<String, List>();
		List lstDepartment = null;
		List lstRefHospital = null;
		List lstNewDeptVisitDepartment = null;
		List lstLetterType=null;
		
		String strRefHospCode="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			System.out.println("RegEssentialBO :: getNewDeptVisitEssential()");
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
			
			//Start:Sheeldarshi
			//Reason: Change for adding Letter type combo on All Registration Pages for credit category
			//Date: 14thJune'15
			lstLetterType=objRegEssentialDAO.getLetterTypeList(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST,lstLetterType);
			//End
						
			lstRefHospital = objRegEssentialDAO.getRefHospital(objUserVO_p);
			System.out.println("lstRefHospital.size :"+lstRefHospital.size());
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);
			//strRefHospCode= objUserVO_p.getHospitalCode();
			strRefHospCode= RegistrationConfig.SUPER_USER_HOSPITAL_CODE;
			
			List lstReferDepartment = objRegEssentialDAO.getReferDepartmentList(objUserVO_p,strRefHospCode);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			objUserVO_p.setModuleId(RegistrationConfig.MODULE_ID_REGISTRATION);
			List	lstPrimaryCat = objRegEssentialDAO.getPrimaryCat(objUserVO_p, RegistrationConfig.NEW_DEPT_VISIT_TARIFF_ID);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstNewDeptVisitDepartment = objRegEssentialDAO.getDepartment(objUserVO_p,strUnitType_p,strCrNo_p,strRosterType_p,strDeptMode_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
			
			
			

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("lstNewDeptVisitDepartment :"+lstNewDeptVisitDepartment);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			System.out.println("inside catch -->> lstNewDeptVisitDepartment :0");
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}

		catch (HisApplicationExecutionException e) {
			System.out.println("lstNewDeptVisitDepartment :2");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			System.out.println("lstNewDeptVisitDepartment :3");
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			System.out.println("lstNewDeptVisitDepartment :4");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
			return essentialMap;
		}
		
	}
	
	public Map<String, Object> getOldDeptVisitEssential(String strCrNo_p, UserVO objUserVO_p) {
		Map<String, Object> essentialMap = new HashMap<String, Object>();

		List lstRegCategory = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
	
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;

		List lstOldDeptVisitDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			
			//lstRegCategory = objRegEssentialDAO.getRegCategory(objUserVO_p);
			//essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);

			
			lstCountry = objRegEssentialDAO.getCountry(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = objRegEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE, objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);

			lstRefHospital = objRegEssentialDAO.getRefHospital(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstNationality = objRegEssentialDAO.getNationality(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);

			lstAreaCategory = objRegEssentialDAO.getAreaCategory();
			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);

			lstAgeType = objRegEssentialDAO.getAgeType();
			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);

			/*String[] dt = objGlobalEssentialDAO.getSystemDate(objUserVO_p);
			essentialMap.put(Config.SYSDATE, dt[0]);*/

			lstOldDeptVisitDepartment = objRegEssentialDAO.getDepartment(objUserVO_p, RegistrationConfig.UNIT_TYPE_GENERAL, strCrNo_p, "1", "5");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT,lstOldDeptVisitDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public RenewalConfigVO getRefnewalConfigDtl(UserVO objUserVO_p, String strRenewelGrp_p) {
		
		RenewalConfigVO voRenewalConfig=null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			voRenewalConfig=objEssentialDAO.getRenewelConfigDtl(objUserVO_p,strRenewelGrp_p,"2");
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return voRenewalConfig;
	}
	
	

	public Map<String, Object> getSplRegistrationFormEssentials_AJAX(UserVO _userVO,String strStateCode, HttpServletRequest objRequest_p) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstEducation = null;
		List lstCaste = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List lstUnit = null;
		List occupationDetail = null;
		String amount = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List relationList = null;
		List<Entry> lstVerificationDoc=null;
		List lstRMOEmployees=null;
		List lstClient=null;
		//By Mukund on 25.07.2016
		List lstLetterType=null;
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		List lstPaymentMode = null;
		RegistrationConfigurationBean _objConfigBean = new RegistrationConfigurationBean();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstDepartment=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
			lstPrimaryCat=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
			mapOfRenewalVoOnKeyPatCat=(Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			
			if(lstDepartment==null || lstDepartment.size()==0){
				lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,"0","2","8");
			}
			//lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,"0","2","4");
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,lstDepartment);
			
			
			if(lstPrimaryCat==null || lstPrimaryCat.size()==0){
				lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO,RegistrationConfig.SPECIAL_CLINIC_REGISTRATION_TARIFF_ID);
			}
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			lstRMOEmployees = objEssentialDAO.getRMOEmployees(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RMO_EMP,lstRMOEmployees);
			//End
			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
			
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = getStateBasedOnCountry_AJAX(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstDistrict = getDistrictBasedOnState_AJAX(_userVO,strStateCode,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,relationList);
			
			occupationDetail = objEssentialDAO.getOccupationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
						
			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);
			
			if(mapOfRenewalVoOnKeyPatCat==null || mapOfRenewalVoOnKeyPatCat.size()==0){
				lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_SPL,"2");
				mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
			}
			essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
			
			voRegistrationConfig=objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_SPL,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
			
			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO,RegistrationConfig.SUPER_USER_HOSPITAL_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			lstNationality = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);
			
			//Start:Sheeldarshi
			//Reason: Change for adding Organisation combo on All Registration Pages
			//Date: 14thJune'15
			lstClient=objEssentialDAO.getClientList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST,lstClient);
			//End
			
			//added by Mukund on 25.07.2016
			lstLetterType=objEssentialDAO.getLetterTypeList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST,lstLetterType);
			//End
			lstPaymentMode=objEssentialDAO.getPaymentModeList(_userVO);
			essentialMap.put(RegistrationConfig.PAYMENT_MODE_OPTION_LIST,lstPaymentMode);
			//By Mukund on 26.03.2018
			try{
				_objConfigBean=objEssentialDAO.getRegistrationConfigDtl2(_userVO,"2","1");
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean_Special, _objConfigBean);
			}
			catch(Exception e)
			{
				essentialMap.put(RegistrationConfig.Registration_Configuration_Bean_Special, _objConfigBean);
				throw new HisRecordNotFoundException(e.getMessage());
			}//End On 26.03.2018
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	public Map<String, Object> getEmgRegistrationFormEssentials_AJAX(UserVO _userVO,String strStateCode, HttpServletRequest objRequest_p) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstEducation = null;
		List lstCaste = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List lstUnit = null;
		List occupationDetail = null;
		String amount = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List lstRMOEmployees= null;
		List relationList = null;
		List<Entry> lstVerificationDoc=null;
		List lstClient=null;
		List lstLetterType=null;
		List consultantList=null;
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		List lstPaymentMode = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		//List lstLocation = null;
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			UnitConsultantMstDATA objUnitConsultantData_p = new UnitConsultantMstDATA();
			
			lstDepartment=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT);
			lstPrimaryCat=(List)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY);
			mapOfRenewalVoOnKeyPatCat=(Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			///ROSTER TYPE=3 FOR CAUSALITY
			if(lstDepartment==null || lstDepartment.size()==0){
				lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_CASUALITY,"0","3","5");
			}
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			
			if(lstPrimaryCat==null || lstPrimaryCat.size()==0){
				lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO,RegistrationConfig.EMERGENCY_REGISTRATION_TARIFF_ID);
			}
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
			/*  ## 		Modification Log							
	 		##		Modify Date				:10thMar'15 
	 		##		Reason	(CR/PRS)		:RMO Changes in Category Master, Registration Process
	 		##		Modify By				:Sheeldarshi 
			 */
			lstRMOEmployees = objEssentialDAO.getRMOEmployees(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RMO_EMP,lstRMOEmployees);
			//End
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = getStateBasedOnCountry_AJAX(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstDistrict = getDistrictBasedOnState_AJAX(_userVO,strStateCode,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,relationList);
			
			occupationDetail = objEssentialDAO.getOccupationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
						
			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);
			
			if(mapOfRenewalVoOnKeyPatCat==null || mapOfRenewalVoOnKeyPatCat.size()==0){
				lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
				mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
			}
			essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
			
			voRegistrationConfig=objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
			
			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO,"");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			//Start:Sheeldarshi
			//Reason: Change for adding Organisation combo on All Registration Pages
			//Date: 14thJune'15
			lstClient=objEssentialDAO.getClientList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_CLIENT_OPTION_LIST,lstClient);
			//End
			
			//added by Mukund on 23.07.2016
			lstLetterType=objEssentialDAO.getLetterTypeList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST,lstLetterType);
			//End
			
			//To get the consultant list IN MLC
			UnitConsultantVO unitconsultantmodel=new UnitConsultantVO();
			consultantList=objEssentialDAO.getConsultantListMLC(hisDAO, unitconsultantmodel, _userVO);
			essentialMap.put(RegistrationConfig.BROUGHT_BY_DEATH_DECLARED_BY,consultantList);	
			
			//End
			//to get the payment mode list for emergency
			lstPaymentMode=objEssentialDAO.getPaymentModeList(_userVO);
			essentialMap.put(RegistrationConfig.PAYMENT_MODE_OPTION_LIST,lstPaymentMode);
			
			/*UnitConsultantVO unitconsultantmodel=new UnitConsultantVO();
			consultantList=objUnitConsultantData_p.getConsultantList(unitconsultantmodel, _userVO);
			essentialMap.put("broughtDeadDeclaredByConsultant",consultantList);*/	
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	/*Get Refer Essentials*/
	public Map<String, Object> getPatientSearchEssentials(UserVO _userVO,String strStateCode)
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstGender = null;
		List lstAreaCategory = null;
		List lstCountry = null;
		List lstState = null;
		List lstDistrict = null;
		List<Entry> lstVerificationDoc = null;
		List lstHospitals=null;
		List ageRangeList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = getStateBasedOnCountry_AJAX(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
						
			lstDistrict = getDistrictBasedOnState_AJAX(_userVO,strStateCode,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);
						
			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);
			
			lstHospitals=objEssentialDAO.getHospitalList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_HOSPITAL_LIST,lstHospitals);
			
			ageRangeList=objEssentialDAO.getAgeRangeList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_AGE_RANGE_OPTION_LIST,ageRangeList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map<String, Object> getReferDtlEssential(UserVO objUserVO_p) {
		List lstDepartment = null;
		List lstRefHospital = null;
		List lstReferDepartment = null;
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
			
			lstRefHospital = objRegEssentialDAO.getRefHospital(objUserVO_p);
			System.out.println("lstRefHospital.size :"+lstRefHospital.size());
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);
			String strRefHospCode= objUserVO_p.getHospitalCode();

			lstReferDepartment = objRegEssentialDAO.getReferDepartmentList(objUserVO_p,strRefHospCode);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map<String, Object> getEmgPatModFormEssentials_AJAX(UserVO _userVO, String strStateCode) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstEducation = null;
		List lstCaste = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List lstUnit = null;
		List occupationDetail = null;
		String amount = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List relationList = null;
		List<Entry> lstVerificationDoc=null;
		List lstAddressType = null;
                List consultantList=null;
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
          	UnitConsultantMstDATA objUnitConsultantData_p = new UnitConsultantMstDATA();
	
			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
			
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = getStateBasedOnCountry_AJAX(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
			
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO,"");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			lstDistrict = getDistrictBasedOnState_AJAX(_userVO,strStateCode,RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,relationList);
			
			occupationDetail = objEssentialDAO.getOccupationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
						
			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);
			
			lstAddressType = objEssentialDAO.getAddressType(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE, lstAddressType);
		
			//Start:Sheeldarshi
			//Reason:Bug 10166 - System should be able to validate senior citizen category	
			voRegistrationConfig=objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
                        //To get the consultant list using same procedure as unitConsultant Master
			
			UnitConsultantVO unitconsultantmodel=new UnitConsultantVO();
			consultantList=objEssentialDAO.getConsultantListMLC(hisDAO, unitconsultantmodel, _userVO);
			essentialMap.put(RegistrationConfig.BROUGHT_BY_DEATH_DECLARED_BY,consultantList);	
			/*UnitConsultantVO unitconsultantmodel=new UnitConsultantVO();
			consultantList=objUnitConsultantData_p.getConsultantList(unitconsultantmodel, _userVO);
			essentialMap.put("broughtDeadDeclaredByConsultant",consultantList);	*/
			//End
			//End
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	/**
	 * For New Dept/Emergency New Dept Visit -- Aadil
	 * @param strCrNo_p
	 * @param objUserVO_p
	 * @param strUnitType_p
	 * @param strRosterType_p [strRosterType_p=1 for New Dept Visit,strRosterType_p=3 for Emg New Dept Visit]  
	 * @param strDeptMode_p [strDeptMode_p=3 for New Dept Visit,strDeptMode_p=6 for Emg New Dept Visit]  
	 * @return Map
	 */
	public Map<String, List> getEmgDeptVisitEssential(String strCrNo_p, UserVO objUserVO_p, String strUnitType_p, String strRosterType_p, String strDeptMode_p) {

		Map<String, List> essentialMap = new HashMap<String, List>();
		List lstDepartment = null;
		List lstRefHospital = null;
		List lstNewDeptVisitDepartment = null;
		List lstLetterType=null;
		
		String strRefHospCode="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			System.out.println("RegEssentialBO :: getNewDeptVisitEssential()");
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);

			
			lstRefHospital = objRegEssentialDAO.getRefHospital(objUserVO_p);
			System.out.println("lstRefHospital.size :"+lstRefHospital.size());
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);
			//strRefHospCode= objUserVO_p.getHospitalCode();
			strRefHospCode= RegistrationConfig.SUPER_USER_HOSPITAL_CODE;

			List lstReferDepartment = objRegEssentialDAO.getReferDepartmentList(objUserVO_p,strRefHospCode);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			//Start:Sheeldarshi
			//Reason: Change for adding Letter type combo on All Registration Pages for credit category
			//Date: 14thJune'15
			lstLetterType=objRegEssentialDAO.getLetterTypeList(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_LETTER_TYPE_OPTION_LIST,lstLetterType);
			//End
			
			lstNewDeptVisitDepartment = objRegEssentialDAO.getDepartment(objUserVO_p,strUnitType_p,strCrNo_p,strRosterType_p,strDeptMode_p);				
			//ListIterator itr = lstNewDeptVisitDepartment.listIterator();			
			System.out.println("lstNewDeptVisitDepartment.size :"+lstNewDeptVisitDepartment.size());
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_EMG_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
			
			
			

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("lstNewDeptVisitDepartment :"+lstNewDeptVisitDepartment);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_EMG_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			System.out.println("lstNewDeptVisitDepartment :1");
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}

		catch (HisApplicationExecutionException e) {
			System.out.println("lstNewDeptVisitDepartment :2");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			System.out.println("lstNewDeptVisitDepartment :3");
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			System.out.println("lstNewDeptVisitDepartment :4");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
			return essentialMap;
		}
		
		
	}
	
	/**To Get the Primary Category Change Essentials Added by Singaravelan on 04-Mar-2014 **/
	public Map<String, Object> getChangePrimaryCategoryEssential(String crNo,String patCatCode, UserVO _userVO) {

		Map<String, Object> essentialMap = new HashMap<String, Object>();
		List lstPrimaryCat = null;
		List lstVerificationDocument = null;
		List patCatDetail=null;
		List patCatIDDetail=null;
		PrimaryCategoryChangeVO[] primaryCatChangeVO = null;
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			RegEssentialDAO _objEssentialDAO = new RegEssentialDAO(tx);

			PrimaryCategoryChangeDAO objPrimaryCatChangeDAO = new PrimaryCategoryChangeDAO(tx);
			
			patCatDetail=objPrimaryCatChangeDAO.getPrimaryCatDetails(patCatCode, _userVO);
			if (patCatDetail!=null && patCatDetail.size()>0)
				essentialMap.put(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_DETAILS,patCatDetail.get(0).toString());
			else
				essentialMap.put(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_DETAILS,new ArrayList().toString());
			
			patCatIDDetail=objPrimaryCatChangeDAO.getPrimaryCatIDDetails(crNo, _userVO);
			if (patCatIDDetail!=null && patCatIDDetail.size()>0)
				essentialMap.put(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_ID_DETAILS,patCatIDDetail.get(0).toString());
			else
				essentialMap.put(RegistrationConfig.ESSENTIALBO_PRIMARY_CATEGORY_WITH_ID_DETAILS,new ArrayList().toString());
			
			voRegistrationConfig=_objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
			essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
		
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	/**To Get the VerificationDocumet By CatCodePrimary Category Change Added by Singaravelan on 04-Mar-2014 **/
	public List getVerificationDocumetByCatCode(String catCode,UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstVerificationDocument = null;
		
		try{
			tx.begin();
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			PatCatDocMapMstDAO objEssentialDAO=new PatCatDocMapMstDAO();
			lstVerificationDocument = objEssentialDAO.getCategoryWiseMappedDocument(catCode,hisDAO,userVO);
		} catch (HisRecordNotFoundException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage() );
		} catch (HisDataAccessException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e){
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally{
			tx.close();
		}
		return lstVerificationDocument;
	}
	
	/**To Get the VerificationDocumet By CatCode in Primary Category Change Added by Singaravelan on 04-Mar-2014 **/
	public List getCatVerificationDocumetByCatCode(String catCode,UserVO userVO){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstVerificationDocument = null;
		
		try{
			tx.begin();
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			PatCatDocMapMstDAO objEssentialDAO=new PatCatDocMapMstDAO();
			lstVerificationDocument = objEssentialDAO.getCategoryWiseVerificationDocument(catCode,hisDAO,userVO);
		} catch (HisRecordNotFoundException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage() );
		} catch (HisDataAccessException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e){
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally{
			tx.close();
		}
		return lstVerificationDocument;
	}
	
	
	/**To Get the VerificationDocumet list Except Given DocCode Added by Singaravelan on 04-Mar-2014 **/
	public List getVerificationDocumetExceptDocCode(UserVO userVO,String docCode){
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstVerificationDocument = null;
		
		try{
			tx.begin();
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			VerificationDocDAO objEssentialDAO=new VerificationDocDAO();
			lstVerificationDocument = objEssentialDAO.getVerificationDocumentsExceptDocCode(userVO,hisDAO,docCode);
		} catch (HisRecordNotFoundException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage() );
		} catch (HisDataAccessException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e){
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e){
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally{
			tx.close();
		}
		return lstVerificationDocument;
	}
	
	/**
	 * For New Dept/Emergency New Dept Visit -- Aadil
	 * @param strCrNo_p
	 * @param objUserVO_p
	 * @param strUnitType_p
	 * @param strRosterType_p [strRosterType_p=1 for New Dept Visit,strRosterType_p=3 for Emg New Dept Visit]  
	 * @param strDeptMode_p [strDeptMode_p=3 for New Dept Visit,strDeptMode_p=6 for Emg New Dept Visit]  
	 * @return Map
	 */
	public Map<String, List> getSpclNewDeptVisitEssential(String strCrNo_p, UserVO objUserVO_p, String strUnitType_p, String strRosterType_p, String strDeptMode_p) {

		Map<String, List> essentialMap = new HashMap<String, List>();
		List lstDepartment = null;
		List lstRefHospital = null;
		List lstNewDeptVisitDepartment = null;
		
		String strRefHospCode="";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			System.out.println("RegEssentialBO :: getSplNewDeptVisitEssential()");
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);

			
			lstRefHospital = objRegEssentialDAO.getRefHospital(objUserVO_p);
			System.out.println("lstRefHospital.size :"+lstRefHospital.size());
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);
			//strRefHospCode= objUserVO_p.getHospitalCode();
			strRefHospCode= RegistrationConfig.SUPER_USER_HOSPITAL_CODE;

			List lstReferDepartment = objRegEssentialDAO.getReferDepartmentList(objUserVO_p,strRefHospCode);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
				
			lstNewDeptVisitDepartment = objRegEssentialDAO.getDepartment(objUserVO_p,strUnitType_p,strCrNo_p,strRosterType_p,strDeptMode_p);				
			//ListIterator itr = lstNewDeptVisitDepartment.listIterator();			
			System.out.println("lstNewDeptVisitDepartment.size :"+lstNewDeptVisitDepartment.size());
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SPL_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
			

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("lstNewDeptVisitDepartment :"+lstNewDeptVisitDepartment);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SPL_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			System.out.println("lstNewDeptVisitDepartment :1");
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}

		catch (HisApplicationExecutionException e) {
			System.out.println("lstNewDeptVisitDepartment :2");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			System.out.println("lstNewDeptVisitDepartment :3");
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			System.out.println("lstNewDeptVisitDepartment :4");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		
		return essentialMap;
	}
	/**
	 * Created By -- Aadil
	 * @param strMode_p
	 * @param objUserVO_p
	 * @param strCrNo_p
	 * @param strVisitDate_p
	 * @return EpisodeVO[]
	 */
	public EpisodeVO[] getEpisodeDtlForMLC(String strMode_p, UserVO objUserVO_p, String strCrNo_p,String strVisitDate_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] objArrEpisodeVO =
		{};
		
		try
		{
			tx.begin();
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			objArrEpisodeVO = episodeDao.getEpisodeDtlForMLC(objUserVO_p, strMode_p, strCrNo_p, strVisitDate_p);
			
		}//end of try
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDeadPatientException | HisApplicationExecutionException | HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException(e.getMessage());
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrEpisodeVO;
	}
	
	/**
	 * Created By -- Aadil
	 * @param strCrNo_p
	 * @param strDeptCode_p
	 * @param strDeptUnitCode_p
	 * @param objUserVO_p
	 * @param strMode_p [9 : MLC To Non MLC Conversion, ]
	 * @param visitType_p
	 * Note : same procedure is available in Patient BO. Use from corresponding files as per your need.
	 * @return
	 */
	public EpisodeVO[] getEpisodeDtlByCrNoByDeptByUnit(String strCrNo_p, String strDeptCode_p, String strDeptUnitCode_p, UserVO objUserVO_p, String strMode_p, String visitType_p)
	{
		System.out.println("RegEssentialBO :: getEpisodeDtlByCrNoByDeptByUnit()");
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] objArrEpisodeVO_p;
		String reprintMode = "";

		try
		{
			tx.begin();
			EpisodeDAO objEpisodeDAO = new EpisodeDAO(tx);
			objArrEpisodeVO_p = objEpisodeDAO.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p,"", strDeptCode_p, strDeptUnitCode_p, objUserVO_p, strMode_p, visitType_p);
			
		}
		catch (HisRecordNotFoundException | HisApplicationExecutionException | HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException(e.getMessage());
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	public RenewalConfigVO getRenewelConfigDtl(UserVO objUserVO_p, String strRenewalGroup, String strMode_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		RenewalConfigVO voRenewalConfigVO=null;
		boolean flagError=false;
		String strErrorMsg="";
		
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);

			voRenewalConfigVO=objRegEssentialDAO.getRenewelConfigDtl(objUserVO_p,strRenewalGroup,strMode_p);
			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisException e) {
			throw new HisException(e.getMessage());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
		return voRenewalConfigVO;
	}
	
	public List<RenewalConfigVO> getListOfRenewalConfigDtl(UserVO objUserVO_p, String strRenewelGrp_p) {
		
		List<RenewalConfigVO> lstRenewalConfigVO=null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(objUserVO_p,strRenewelGrp_p,"2");
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return lstRenewalConfigVO;
	}
	public Map<String, RenewalConfigVO> getMapOfRenewalConfigDtlOnKeyPatCat(UserVO objUserVO_p, String strRenewelGrp_p) {
		
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
			
			lstRenewalConfigVO=objEssentialDAO.getListOfRenewelConfigDtl(objUserVO_p,strRenewelGrp_p,"2");
			mapOfRenewalVoOnKeyPatCat=objEssentialDAO.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return mapOfRenewalVoOnKeyPatCat;
	}
	
	public RegistrationConfigVO getRegistrationConfigDtl(UserVO objUserVO_p, String strRenewalGroup, String strMode_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		RegistrationConfigVO voRegistrationConfig=null;
		boolean flagError=false;
		String strErrorMsg="";
		
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);

			voRegistrationConfig=objRegEssentialDAO.getRegistrationConfigDtl(objUserVO_p,strRenewalGroup,strMode_p);
			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisException e) {
			throw new HisException(e.getMessage());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
		return voRegistrationConfig;
	}
	
	// Getting MLC Detail Essentials
		public Map<String, Object> getMlcDetailEssentials(UserVO objUserVO_p) 
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO hisDAO = new HisDAO("Reg", "regbo");
			List lstUnitConsults = null;
			List lstRelationships = null;
			List lstInjuryTypes = null;
			List lstPatientStatus = null;
			List lstDoctorDtl = null;
			List lstInjuryNature = null;
			List lstInjurySite = null;
			//RenewalConfigVO voRenewalConfigVO=null;
			boolean flagError=false;
			boolean flagHisRecordType=false;
			String strErrorMsg="";
			
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				tx.begin();
				EssentialDAO objEssentialDAO = new EssentialDAO(tx);
				RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
				//UnitConsultantMstDATA objUnitConsultantData_p = new UnitConsultantMstDATA();
				MlcDAO objMlcDAO = new MlcDAO(tx);


				try{
					lstRelationships = objRegEssentialDAO.getRelationsList(objUserVO_p);
					map.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,lstRelationships);
				}catch(Exception e){
					flagError=true;
					strErrorMsg=e.getMessage();
					if(e.getClass().equals(HisRecordNotFoundException.class))
						flagHisRecordType=true;
				}

				/*voRenewalConfigVO=objRegEssentialDAO.getRenewelConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
				map.put(RegistrationConfig.ESSENTIALBO_VO_RENEWEL_CONFIG,voRenewalConfigVO);*/
				
				try{
					lstInjuryTypes = objRegEssentialDAO.getMlcTypesList(objUserVO_p);
					map.put(RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST,lstInjuryTypes);
				}catch(Exception e){
					if(flagError)
						strErrorMsg+=". And ";
					strErrorMsg+=e.getMessage();
					flagError=true;
					if(e.getClass().equals(HisRecordNotFoundException.class))
						flagHisRecordType=true;
				}
				
				try{
					lstInjuryNature = objRegEssentialDAO.getInjuryNatureList(objUserVO_p);
					map.put(RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST, lstInjuryNature);
				}catch(Exception e){
					if(flagError)
						strErrorMsg+=". And ";
					strErrorMsg+=e.getMessage();
					flagError=true;
					if(e.getClass().equals(HisRecordNotFoundException.class))
						flagHisRecordType=true;
				}
				
				try{
					lstPatientStatus = objRegEssentialDAO.getPatientStatusList(objUserVO_p);
					map.put(RegistrationConfig.PATIENT_STATUS_LIST, lstPatientStatus);
				}catch(Exception e){
					if(flagError)
						strErrorMsg+=". And ";
					strErrorMsg+=e.getMessage();
					flagError=true;
					if(e.getClass().equals(HisRecordNotFoundException.class))
						flagHisRecordType=true;
				}
				
				
				
				try{
					UnitConsultantVO unitconsultantmodel=new UnitConsultantVO();
					//lstDoctorDtl=objUnitConsultantData_p.getConsultantList(unitconsultantmodel, objUserVO_p);
					lstDoctorDtl=objRegEssentialDAO.getConsultantListMLC(hisDAO, unitconsultantmodel, objUserVO_p);
					map.put(RegistrationConfig.ESSENTIALBO_OPTION_CONSULTANT_DTL,lstDoctorDtl);
				}catch(Exception e){
					if(flagError)
						strErrorMsg+=". And ";
					strErrorMsg+=e.getMessage();
					flagError=true;
					if(e.getClass().equals(HisRecordNotFoundException.class))
						flagHisRecordType=true;
				}
				
				
				
				
				
				try{
					lstInjurySite = objRegEssentialDAO.getDiagnosisSiteList(objUserVO_p);
					map.put(RegistrationConfig.ESSENTIAL_DISEASE_SIDE_LIST, lstInjurySite);
				}catch(Exception e){
					if(flagError)
						strErrorMsg+=". And ";
					strErrorMsg+=e.getMessage();
					flagError=true;
					if(e.getClass().equals(HisRecordNotFoundException.class))
						flagHisRecordType=true;
				}
				if(flagError)
					if(flagHisRecordType)
						throw new HisRecordNotFoundException("Record Not Found");
					else
						throw new HisException(strErrorMsg);
				
			} catch (HisRecordNotFoundException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisRecordNotFoundException(e.getMessage(),map);
			} catch (HisException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisException(e.getMessage());
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException(e.getMessage());
			} finally {
				tx.close();
			}
			return map;
		}
		
	// Getting MLC Detail Essentials
	public Map<String, Object> getMlcDetailEssentials(UserVO objUserVO_p, MlcVO objMlcVO_p, String strMlcMode_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstUnitConsults = null;
		List lstRelationships = null;
		List lstInjuryTypes = null;
		List lstPatientStatus = null;
		List lstDoctorDtl = null;
		List lstInjuryNature = null;
		List lstInjurySite = null;
		List lstMlcVO = null;
		//RenewalConfigVO voRenewalConfigVO=null;
		boolean flagError=false;
		boolean flagHisRecordType=false;
		String strErrorMsg="";
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
			UnitConsultantMstDATA objUnitConsultantData_p = new UnitConsultantMstDATA();
			MlcDAO objMlcDAO = new MlcDAO(tx);

			lstMlcVO = objMlcDAO.getMlcDtlList(objMlcVO_p,objUserVO_p,strMlcMode_p);
			map.put(RegistrationConfig.EMGREGISTRATIONDESK_MLCVO_LIST,lstMlcVO);

			try{
				lstRelationships = objRegEssentialDAO.getRelationsList(objUserVO_p);
				map.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,lstRelationships);
			}catch(Exception e){
				flagError=true;
				strErrorMsg=e.getMessage();
				if(e.getClass().equals(HisRecordNotFoundException.class))
					flagHisRecordType=true;
			}

			/*voRenewalConfigVO=objRegEssentialDAO.getRenewelConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
			map.put(RegistrationConfig.ESSENTIALBO_VO_RENEWEL_CONFIG,voRenewalConfigVO);*/
			
			try{
				lstInjuryTypes = objRegEssentialDAO.getMlcTypesList(objUserVO_p);
				map.put(RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST,lstInjuryTypes);
			}catch(Exception e){
				if(flagError)
					strErrorMsg+=". And ";
				strErrorMsg+=e.getMessage();
				flagError=true;
				if(e.getClass().equals(HisRecordNotFoundException.class))
					flagHisRecordType=true;
			}
			
			try{
				lstInjuryNature = objRegEssentialDAO.getInjuryNatureList(objUserVO_p);
				map.put(RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST, lstInjuryNature);
			}catch(Exception e){
				if(flagError)
					strErrorMsg+=". And ";
				strErrorMsg+=e.getMessage();
				flagError=true;
				if(e.getClass().equals(HisRecordNotFoundException.class))
					flagHisRecordType=true;
			}
			
			try{
				lstPatientStatus = objRegEssentialDAO.getPatientStatusList(objUserVO_p);
				map.put(RegistrationConfig.PATIENT_STATUS_LIST, lstPatientStatus);
			}catch(Exception e){
				if(flagError)
					strErrorMsg+=". And ";
				strErrorMsg+=e.getMessage();
				flagError=true;
				if(e.getClass().equals(HisRecordNotFoundException.class))
					flagHisRecordType=true;
			}
			
			
			// added by warish purpus:get consultant list	
			try{
				UnitConsultantVO unitconsultantmodel=new UnitConsultantVO();
				//lstDoctorDtl=objUnitConsultantData_p.getConsultantList(unitconsultantmodel, objUserVO_p);
				lstDoctorDtl=objRegEssentialDAO.getConsultantListMLC(hisDAO, unitconsultantmodel, objUserVO_p);
				map.put(RegistrationConfig.ESSENTIALBO_OPTION_CONSULTANT_DTL,lstDoctorDtl);
			}catch(Exception e){
				if(flagError)
					strErrorMsg+=". And ";
				strErrorMsg+=e.getMessage();
				flagError=true;
				if(e.getClass().equals(HisRecordNotFoundException.class))
					flagHisRecordType=true;
			}
			
			
			try{
				lstInjurySite = objRegEssentialDAO.getDiagnosisSiteList(objUserVO_p);
				map.put(RegistrationConfig.ESSENTIAL_DISEASE_SIDE_LIST, lstInjurySite);
			}catch(Exception e){
				if(flagError)
					strErrorMsg+=". And ";
				strErrorMsg+=e.getMessage();
				flagError=true;
				if(e.getClass().equals(HisRecordNotFoundException.class))
					flagHisRecordType=true;
			}
			if(flagError)
				if(flagHisRecordType)
					throw new HisRecordNotFoundException("Record Not Found");
				else
					throw new HisException(strErrorMsg);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(),map);
		} catch (HisException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisException(e.getMessage());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
		return map;
	}
	
	public MlcVO getMlcDtls(MlcVO objMlcVO_p, UserVO objUserVO_p, String strMode_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		MlcVO objMlcVO=null;
		
		try {
			tx.begin();
			MlcDAO objMlcDAO = new MlcDAO(tx);

			objMlcVO = objMlcDAO.getMlcDtls(objMlcVO_p,objUserVO_p,strMode_p);
			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisException e) {
			throw new HisException(e.getMessage());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(e.getMessage());
		} 
		return objMlcVO;
	}
	
	public List<MlcParameterDtlVO> getMlcParameterDtlList(MlcParameterDtlVO objMlcParameterDtlVO_p, UserVO objUserVO_p, String strMode_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List<MlcParameterDtlVO> lstMlcParameterDtlVO=null;
		
		try {
			tx.begin();
			MlcParameterDAO objMlcParameterDAO = new MlcParameterDAO(tx);

			lstMlcParameterDtlVO = objMlcParameterDAO.getMlcParameterDtlList(objMlcParameterDtlVO_p,objUserVO_p,strMode_p);
			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisException e) {
			throw new HisException(e.getMessage());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(e.getMessage());
		} 
		return lstMlcParameterDtlVO;
	}

	public Map<String, List> getPatientdeathDetailEssential(String strCrNo_p, String strEpisodeCode_p, UserVO objUserVO_p) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, List> essentialMap = new HashMap<String, List>();
		List lstRelationships = new ArrayList();
		List lstInjuryTypes = new ArrayList();
		List lstInjuryNature = new ArrayList();
		List lstDeathManner = new ArrayList();
		String strDate = "@";

		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);

			lstRelationships = objRegEssentialDAO.getRelationsList(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, lstRelationships);

			lstInjuryTypes = objRegEssentialDAO.getMlcTypesList(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST,lstInjuryTypes);
			
			
			lstInjuryNature = objRegEssentialDAO.getInjuryNatureList(objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST, lstInjuryNature);
			
			lstDeathManner = objRegEssentialDAO.getDeathMannerList("3","","",objUserVO_p);
			essentialMap.put(RegistrationConfig.ESSENTIAL_DEATH_MANNER_LIST, lstDeathManner);

			//strDate = objRegEssentialDAO.getOnSetDateNRecentVisitDate(strCrNo_p, strEpisodeCode_p, objUserVO_p);
			//essentialMap.put(RegistrationConfig.ESSENTIAL_DEATH_ON_SET_DATE_N_RECENT_VISIT_DATE, strDate);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	//To find the Counter Name on the IP Address Basis Added by Singaravelan on 21-May-14
	public String getCounterName(UserVO _userVO,String ipAddress) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strCounterName="";
		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);	
			strCounterName=objRegEssentialDAO.getCounterName(_userVO,ipAddress);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return strCounterName;
	
	}
	
	/**
	 * Retrieves all the CR No from the Appointment Dtl Table fro Special Clinic Registration.
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO populated with CR No.
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public PatientVO[] getCRNoWithAppointment(UserVO _userVO, String mode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] _arrPatientVO ={};
		try	{
			
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			_arrPatientVO = patientDao.getCRNoWithAppointment(_userVO, mode);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrPatientVO;
	}
	
	/**
	 * Retrieves the CR No Details from the Appointment Dtl Table fro Special Clinic Registration.
	 * @param _userVO Provides User details.
	 * @return PatientVO populated with CR No.
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public PatientVO getDetailsWithAppointment(PatientVO _patientVO,UserVO _userVO,String mode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO _arrPatientVO = null;

		try	{
			
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			_arrPatientVO = patientDao.getDetailsWithAppointment(_patientVO, _userVO, mode);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrPatientVO;
	}
	
	/**
	 * Retrieves all the CR No from the Appointment Dtl Table fro Special Clinic Registration.
	 * @param _userVO Provides User details.
	 * @return Array of PatientVO populated with CR No.
	 * Added by Singaravelan on 28-Aug-2014
	 */
	public PatientVO[] searchSpecialCRNoWithAppointment(PatientVO _patientVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientVO[] _arrPatientVO ={};
		try	{
			
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
			_arrPatientVO = patientDao.searchSpecialCRNoWithAppointment(_patientVO,_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _arrPatientVO;
	}
	
	public List getUserList(UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Reg", "regbo");
		List lstUser = null;
		try {
			lstUser = RegEssentialDAO.getUserList(hisDAO, uservo);
		} catch (HisDataAccessException e) {
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		}
		return lstUser;

	}
	
	//Start:Sheeldarshi: 30thSep'14
	public Map<String, Map<String, RenewalConfigVO>> getSpecialRegistrationFormInitialEssentials(UserVO _userVO) 
	{
		//Map<String, Map<String, RenewalConfigVO>> essentialMap = new HashMap<String, Map<String, RenewalConfigVO>>();
		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstDepartment = null;
		List<RenewalConfigVO> lstRenewalConfigVO=new ArrayList<RenewalConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		Map<String, RenewalConfigVO> mapOfRenewalVoOnKeyPatCat=null;
		RenewalConfigVO objRenewalConfigVO=null;
		
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
				PatientDAO patientDao = new PatientDAO(tx);
				RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);//By Mukund
			try{
				lstRenewalConfigVO=patientDao.getListOfRenewelConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_SPL,"2");
				mapOfRenewalVoOnKeyPatCat=patientDao.convertRenewalVoToMapOfRenewalVoOnKeyPatCat(lstRenewalConfigVO);
				objRenewalConfigVO=mapOfRenewalVoOnKeyPatCat.get("10");
				if(objRenewalConfigVO==null)
					throw new HisRecordNotFoundException("Default Renewal Rules for Special Clinics is not defined for this hospital");
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO,mapOfRenewalVoOnKeyPatCat);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			//By Mukund for "Dept/Unit" dropdown in splClinincRegWthAppmnt
			try{
				lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,"0","2","8");
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	// End:Sheeldarshi: 30thSep'14
	
	public List getRelationList_AJAX(UserVO _userVO) 
	{
		
		List lstRelationList = null;	

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);			
			lstRelationList = objEssentialDAO.getRelationsList(_userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstRelationList;
	}
	
	public List getClientList_AJAX(UserVO _userVO) 
	{
		
		List lstRelationList = null;	

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);			
			lstRelationList = objEssentialDAO.getClientList(_userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstRelationList;
	}
	
	public String checkBeneficiaryIdDuplicy(UserVO objUserVO_p, CreditAvailDetailVO objcreditAvailDtlVO_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strUniqueRefLetterFlag="0";
		try {
			tx.begin();
			CreditAvailDtlDAO objCreditAvailDtlDAO = new CreditAvailDtlDAO(tx);
	
			strUniqueRefLetterFlag=objCreditAvailDtlDAO.checkBeneficiaryIdDuplicy(objUserVO_p, objcreditAvailDtlVO_p);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return strUniqueRefLetterFlag;
	
	}
	/*Start:Sheeldarshi
	Reason:prevent user from registering patients with same arogyashri number. 
	Date:03-July-15
	*/
	public String checkAarogyashriIdDuplicy(UserVO objUserVO_p, BeneficiaryPatientVO objPatBenlDtlVO_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strUniqueRefLetterFlag="0";
		try {
			tx.begin();
			BeneficiaryPatientDAO objPatBenDtlDAO = new BeneficiaryPatientDAO(tx);
	
			strUniqueRefLetterFlag=objPatBenDtlDAO.checkAarogyashriIdDuplicy(objUserVO_p, objPatBenlDtlVO_p);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return strUniqueRefLetterFlag;
	
	}
	//End
	
	public Map<String, RegistrationConfigVO> getSpecialRegistrationFormRegConfigEssentials(UserVO _userVO) 
	{
		Map<String, RegistrationConfigVO> essentialMap = new HashMap<String, RegistrationConfigVO>();
		RegistrationConfigVO voRegistrationConfig = new RegistrationConfigVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			try{
				RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);	
				voRegistrationConfig=objEssentialDAO.getRegistrationConfigDtl(_userVO,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_SPL,"2");
				essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
				
			}catch(Exception e){
				essentialMap.put(RegistrationConfig.ESSENTIALBO_VO_REGISTRATION_CONFIG,voRegistrationConfig);
				throw new HisRecordNotFoundException(e.getMessage());
			}
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public void getCashCollectionDetail(PatientVO patientVO,UserVO _uservo) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);
		objEssentialDAO.setcashCollectionDetail_from_OutBound(patientVO,_uservo);
		
	}
	
	/*Added By Mukund on 27.07.2016*/
	public List getLetterTypeList_AJAX(UserVO _userVO) 
	{
		
		List lstLettrTypeList = null;	

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);			
			lstLettrTypeList = objEssentialDAO.getLetterTypeList(_userVO);
			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstLettrTypeList;
	}
	
	public RegistrationConfigurationBean getRegistrationConfigDtl2(UserVO objUserVO_p) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		RegistrationConfigurationBean _objRegConfigVO2 = null;
		boolean flagError=false;
		String strErrorMsg="";
		
		try {
			tx.begin();
			RegEssentialDAO objRegEssentialDAO = new RegEssentialDAO(tx);
			_objRegConfigVO2=objRegEssentialDAO.getRegistrationConfigDtl2(objUserVO_p,"1","1");
			
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisException e) {
			throw new HisException(e.getMessage());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
		return _objRegConfigVO2;
	}
	/*End:Mukund*/
	
	public void continueWithExistingDuplicateRecord(UserVO objUserVO_p,  PatientVO patVO, String duplicateSearchSlno)
	{
		String[] crNoArr = new String[0];
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);			
			objEssentialDAO.updateDuplicateSearchLog(patVO, objUserVO_p, crNoArr, "2", duplicateSearchSlno, "2");
			//public String updateDuplicateSearchLog(PatientVO patVO, UserVO objUserVO_p, String[] crNoArr, String strMode_p, String duplicateSearchSlno, String strActTaken)
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	
	}
	
	public String getPatientQRCode(String patCrNo, UserVO objUsrVO)
	{
		String strQRCode = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try{
			tx.begin();
			RegEssentialDAO objRegEssDAO = new RegEssentialDAO(tx);
			strQRCode = objRegEssDAO.getPatientQRCode(patCrNo, objUsrVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			tx.close();
		}
		return strQRCode;
	}
	
	public Map<String, Object> getDuplicateCardInitialEssentials(UserVO _userVO) 
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		RegistrationConfigurationBean _objRegConfigBean = new RegistrationConfigurationBean();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);

			_objRegConfigBean=objEssentialDAO.getRegistrationConfigDtl2(_userVO,"1","1");
			essentialMap.put(RegistrationConfig.Registration_Configuration_Bean, _objRegConfigBean);
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException("Error, Contact System Administrator");
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException("Error, Contact System Administrator");
		} finally {
			tx.close();
		}
		return essentialMap;
	}


	public List getPaymentModeList_AJAX(UserVO _UserVO) {
		List lstPaymentMode = null;	

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			RegEssentialDAO objEssentialDAO = new RegEssentialDAO(tx);			
			lstPaymentMode = objEssentialDAO.getPaymentModeList(_UserVO);
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstPaymentMode;

	}
	
}//end class
