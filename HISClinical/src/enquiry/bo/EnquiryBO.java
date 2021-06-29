package enquiry.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.AddressEnqiryVO;
import hisglobal.vo.BloodStockEnquiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.ConsultantDetailVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DepartmentLocationEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.OpdEnquiryVO;
import hisglobal.vo.PatientEnquiryVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import mortuary.MortuaryConfig;
import registration.RegistrationConfig;
import registration.dao.EssentialDAO;
import enquiry.enquiryConfig;
import enquiry.dao.EnquiryDAO;
import enquiry.dao.HospitalChargeEnquiryDAO;
import enquiry.dao.HospitalFacilityMasterDAO;
import enquiry.vo.BloodDonorEnquriyVO;
import enquiry.vo.HospitalConsultantEnquiryVO;
import enquiry.vo.HospitalDepartmentEnquiryVO;
import enquiry.vo.HospitalFacilityMasterVO;
import enquiry.vo.HospitalRegistrationTimingsVO;
import enquiry.vo.HospitalWardEnquiryVO;
import enquiry.vo.InPatientEnquiryVO;
import enquiry.vo.OpdScheduleEnquiryVO;
import enquiry.vo.StaffEnquiryVO;

public class EnquiryBO implements EnquiryBOi{

	
	public Map getPatientEnquiryEssentials(UserVO _userVO){
		Map essentialMap= new HashMap();
		List genderList=null;
		List locationList=null;
		List stateList=null;
		List countryList=null;
		List departmentList=null;
		List areaCategory=null;
		List districtList=null;
		List globalDepartmentList=null;

		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			EssentialDAO EssentialDAO= new EssentialDAO(tx);
			
			departmentList = EssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,departmentList);
			
			globalDepartmentList = EssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GLOBAL_DEPARTMENT,globalDepartmentList);			
			
			genderList=EssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,genderList);
			
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,new ArrayList());			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_WARD_CODE,new ArrayList());
			
			/*locationList=EssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION, locationList);*/
						
			/*areaCategory=EssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY, areaCategory);*/
			
			stateList = EssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE, _userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,stateList);
					
			countryList=EssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,countryList);
			
			districtList=EssentialDAO.getDistrictList(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
		    essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE, districtList);
			
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		

	}
	
	
	public Map getStaffEnquiryEssentials(UserVO _userVO){
		Map essentialMap= new HashMap();
		List genderList=null;
		List departmentList=null;
		List designationList=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			EssentialDAO EssentialDAO= new EssentialDAO(tx);
			EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
			
			genderList=EssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,genderList);
			
			departmentList=enquiryDAO.getAllDeparments(_userVO);
			essentialMap.put(enquiryConfig.ENQUIRY_BO_OPTION_ALLDEPT, departmentList);
			
			designationList = enquiryDAO.getAllDesignations(_userVO);
			essentialMap.put(enquiryConfig.ENQUIRY_OPTION_DESIGNATION,designationList);
			
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
		
	}
	
	
	public CommonEnquiryVO[] searchPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		CommonEnquiryVO[] commonEnquiryVOs=null;
		HashMap patDetailMap=new HashMap();
		HashMap addDetailMap=new HashMap();
		HashMap episodeDetailMap=new HashMap();
		HashMap finalQueryMap=new HashMap();
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		String isCurrentHospitalSearch_p="1";
		try
		{
			tx.begin();
			
				patDetailMap=HelperMethods.createQueryMapFromVO(_patEnquiryVO);
				addDetailMap=HelperMethods.createQueryMapFromVO(_addEnquiryVO);
				episodeDetailMap=HelperMethods.createQueryMapFromVO(_episodeEnquiryVO);
				finalQueryMap.putAll(patDetailMap);
				finalQueryMap.putAll(addDetailMap);
				finalQueryMap.putAll(episodeDetailMap);
				isCurrentHospitalSearch_p=_patEnquiryVO.getIsCurrentHospitalSearch();
				
				commonEnquiryVOs=enquiryDAO.searchPatient(finalQueryMap,_userVO,isCurrentHospitalSearch_p);
			
		}	
		catch(HisRecordNotFoundException e){
				tx.rollback();	
				 e.printStackTrace(); 
				throw new HisRecordNotFoundException(e.getMessage()); 
			}
			catch(HisApplicationExecutionException e){	   		   	
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisApplicationExecutionException(e.getMessage());
		   	 }
		   	 
		   	catch(HisDataAccessException e){		
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisDataAccessException(e.getMessage());  	
		  	 }
			catch(Exception e){
				e.printStackTrace();	
				tx.rollback();		
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally{
				tx.close();			
			}
			return commonEnquiryVOs;		

	}
	
	
	public StaffEnquiryVO[] searchStaffDetail(StaffEnquiryVO staffEnquiryVO,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		StaffEnquiryVO[] staffEnquiryVOs=null;
		HashMap staffDetailMap=new HashMap();
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		
		try
		{
			tx.begin();
			
			staffDetailMap=HelperMethods.createQueryMapFromVO(staffEnquiryVO);
					
			staffEnquiryVOs=enquiryDAO.searchStaffDetail(staffDetailMap,_userVO);
			
		}	
		catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException(e.getMessage());
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException(e.getMessage());  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally{
			tx.close();			
		}
		return staffEnquiryVOs;		
		
	}
	
	
	public Map getInPatientDetail(String patientCRNo,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		InPatientEnquiryVO patientEnquiryVO=new InPatientEnquiryVO();
		EpisodeEnquiryVO episodeEnquiryVO=new EpisodeEnquiryVO();
		Map inPatientDetailMap=new HashMap();
		try
		{
			tx.begin();
			EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
			episodeEnquiryVO=enquiryDAO.getOpdPatientDetail(patientCRNo, _userVO);
			inPatientDetailMap.put(enquiryConfig.OPD_PATIENT_ENQUIRY_VO, episodeEnquiryVO);
			patientEnquiryVO=enquiryDAO.getInPatientDetail(patientCRNo, _userVO);
			inPatientDetailMap.put(enquiryConfig.IN_PATIENT_ENQUIRY_VO, patientEnquiryVO);
			
		}	
		catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			inPatientDetailMap.put(enquiryConfig.IN_PATIENT_ENQUIRY_VO, patientEnquiryVO);
			throw new HisRecordNotFoundException(e.getMessage(),inPatientDetailMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException(e.getMessage());
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException(e.getMessage());  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally{
			tx.close();			
		}
		return inPatientDetailMap;		
		
	}
	
	public CommonEnquiryVO[] searchInPatientDetails(PatientEnquiryVO _patEnquiryVO,AddressEnqiryVO _addEnquiryVO,EpisodeEnquiryVO _episodeEnquiryVO,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		CommonEnquiryVO[] commonEnquiryVOs=null;
		HashMap patDetailMap=new HashMap();
		HashMap addDetailMap=new HashMap();
		HashMap episodeDetailMap=new HashMap();
		HashMap finalQueryMap=new HashMap();
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		String isCurrentHospitalSearch_p="1";
		
		try
		{
			tx.begin();
		
			patDetailMap=HelperMethods.createQueryMapFromVO(_patEnquiryVO);
			addDetailMap=HelperMethods.createQueryMapFromVO(_addEnquiryVO);
			episodeDetailMap=HelperMethods.createQueryMapFromVO(_episodeEnquiryVO);
			finalQueryMap.putAll(patDetailMap);
			finalQueryMap.putAll(addDetailMap);
			finalQueryMap.putAll(episodeDetailMap);
			isCurrentHospitalSearch_p=_patEnquiryVO.getIsCurrentHospitalSearch();
			
			//commonEnquiryVOs=enquiryDAO.searchPatient(finalQueryMap,_userVO);
			commonEnquiryVOs=enquiryDAO.searchInPatient(finalQueryMap,_userVO,isCurrentHospitalSearch_p);

			
		}	
		catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException(e.getMessage());
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException(e.getMessage());  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally{
			tx.close();			
		}
		return commonEnquiryVOs;		
		
	}
	
	
	public  OpdEnquiryVO[] getOpdEnquirySearchDetail(OpdEnquiryVO _opdEnquiryVO,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		PatientVO[] patVO=null;
		OpdEnquiryVO[] opdEnquiryVOs=null;
		HashMap opdEnquiryDetailMap=new HashMap();
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		
		try
		{
			tx.begin();
			opdEnquiryDetailMap=HelperMethods.createQueryMapFromVO(_opdEnquiryVO);
			opdEnquiryVOs=enquiryDAO.getOpdEnquirySearchDetail(opdEnquiryDetailMap,_userVO);
			
		}	
		catch(HisRecordNotFoundException e){
				tx.rollback();	
				 e.printStackTrace(); 
				throw new HisRecordNotFoundException(e.getMessage()); 
			}
			catch(HisApplicationExecutionException e){	   		   	
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisApplicationExecutionException(e.getMessage());
		   	 }
		   	 
		   	catch(HisDataAccessException e){		
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisDataAccessException(e.getMessage());  	
		  	 }
			catch(Exception e){
				e.printStackTrace();	
				tx.rollback();		
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally{
				tx.close();			
			}
			return opdEnquiryVOs;		

		}
	
	public Map getDepartmentLocationEnquiryEssentials(UserVO _userVO){

		Map essentialMap= new HashMap();
		
		List departmentList=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			EssentialDAO EssentialDAO= new EssentialDAO(tx);
			departmentList = EssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,departmentList);
			
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		

	}
	
 public DepartmentLocationEnquiryVO searchDepatmentLocation(String _departmentCode,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		PatientVO[] patVO=null;
		DepartmentLocationEnquiryVO departmentLocationEnquiryVO=null;
		
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		int switchVariable=0;
		List crNO=null;
		
		try
		{
			tx.begin();
			departmentLocationEnquiryVO=enquiryDAO.searchDepartmentLocation(_departmentCode,_userVO);
			
		}	
		catch(HisRecordNotFoundException e){
				tx.rollback();	
				 e.printStackTrace(); 
				throw new HisRecordNotFoundException(e.getMessage()); 
			}
			catch(HisApplicationExecutionException e){	   		   	
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisApplicationExecutionException();
		   	 }
		   	 
		   	catch(HisDataAccessException e){		
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisDataAccessException();  	
		  	 }
			catch(Exception e){
				e.printStackTrace();	
				tx.rollback();		
				throw new HisApplicationExecutionException();
			}
			finally{
				tx.close();			
			}
			return departmentLocationEnquiryVO;		

	}

 
	public  OpdEnquiryVO[] getAllConsulatantDetailsForUnit(String _deptUnitCode,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		PatientVO[] patVO=null;
		OpdEnquiryVO[] opdEnquiryVOs=null;
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		
		
		
		try
		{
			tx.begin();


			
			
			
			opdEnquiryVOs=enquiryDAO.getAllConsulatantDetailsForUnit(_deptUnitCode,_userVO);
			
		}	
		catch(HisRecordNotFoundException e){
				tx.rollback();	
				 e.printStackTrace(); 
				throw new HisRecordNotFoundException(e.getMessage()); 
			}
			catch(HisApplicationExecutionException e){	   		   	
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisApplicationExecutionException(e.getMessage());
		   	 }
		   	 
		   	catch(HisDataAccessException e){		
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisDataAccessException(e.getMessage());  	
		  	 }
			catch(Exception e){
				e.printStackTrace();	
				tx.rollback();		
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally{
				tx.close();			
			}
			return opdEnquiryVOs;		
		}

	

 public ConsultantDetailVO[] searchConsultantDetail(ConsultantDetailVO _consultantDetailVO,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		ConsultantDetailVO[] consultantDetailVOs=null;
		DepartmentLocationEnquiryVO departmentLocationEnquiryVO=null;
		HashMap finalQueryMap=new HashMap();
		EnquiryDAO enquiryDAO=new EnquiryDAO(tx);
		
		try
		{
			tx.begin();
			finalQueryMap=HelperMethods.createQueryMapFromVO(_consultantDetailVO);
			consultantDetailVOs=enquiryDAO.searchConsultantDetail(finalQueryMap,_userVO);
			
		}	
		catch(HisRecordNotFoundException e){
				tx.rollback();	
				 e.printStackTrace(); 
				throw new HisRecordNotFoundException(e.getMessage()); 
			}
			catch(HisApplicationExecutionException e){	   		   	
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisApplicationExecutionException(e.getMessage());
		   	 }
		   	 
		   	catch(HisDataAccessException e){		
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisDataAccessException(e.getMessage());  	
		  	 }
			catch(Exception e){
				e.printStackTrace();	
				tx.rollback();		
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally{
				tx.close();			
			}
			return consultantDetailVOs;		

	}


 public Map getConsultantDetailEnquiryEssentials(UserVO _userVO){
		Map essentialMap= new HashMap();
		List locationList=null;
		List stateList=null;
		List countryList=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			EssentialDAO EssentialDAO= new EssentialDAO(tx);
			
			locationList=EssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION, locationList);
			
			stateList = EssentialDAO.getState(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,stateList);
					
			countryList=EssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,countryList);
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		

	}
	
 public BloodStockEnquiryVO[] getBloodStockEnquiry(UserVO _userVO,String _choice){
		BloodStockEnquiryVO[] bloodStockEnquiry=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			bloodStockEnquiry=enqDAO.getBloodStock(_userVO,_choice);
			
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return bloodStockEnquiry;		

	}	
 
 public List getBloodDonorGroups(UserVO _userVO){
	 List bloodGroup=new ArrayList();
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			BloodBankEssentialDAO enqDAO= new BloodBankEssentialDAO(tx);
			tx.begin();
			
			bloodGroup=enqDAO.getBloodGroup(_userVO);
			
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return bloodGroup;		

	}	

 public BloodDonorEnquriyVO[] getBloodDonorEnquiryDetails(UserVO _userVO,BloodDonorEnquriyVO _bloodDonorVO){
	 BloodDonorEnquriyVO[] bloodStockEnquiry=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			bloodStockEnquiry=enqDAO.getBloodDonorEnquiryDetails(_userVO,_bloodDonorVO);
			
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return bloodStockEnquiry;		

	}	
 
 	public List getFreePatCatList(UserVO userVO){
	 
	 List freePatCatList=new ArrayList();
	 JDBCTransactionContext tx =new JDBCTransactionContext();
	 
	 try
	 {
		 EnquiryDAO enqDAO= new EnquiryDAO(tx);
		 tx.begin();
		 freePatCatList=enqDAO.getFreePatientCatList( userVO);
	 }catch(HisRecordNotFoundException e){
		 tx.rollback();	
		 e.printStackTrace(); 
		 throw new HisRecordNotFoundException(e.getMessage()); 
	 }
	 catch(HisApplicationExecutionException e){	   		   	
		 tx.rollback();
		 e.printStackTrace();   		 
		 throw new HisApplicationExecutionException();
	 }
	 
	 catch(HisDataAccessException e){		
		 tx.rollback();
		 e.printStackTrace();   		 
		 throw new HisDataAccessException();  	
	 }
	 catch(Exception e){
		 e.printStackTrace();	
		 tx.rollback();		
		 throw new HisApplicationExecutionException();
	 }
	 finally{
		 tx.close();			
	 }
	 return freePatCatList;		
	 
 }	
 	
 	public Map getDepartmentEnquiryEssential(UserVO userVO){
 		 
 		Map essentialMap=new HashMap();
 		 List allDepartment=new ArrayList();
 		 JDBCTransactionContext tx =new JDBCTransactionContext();
 		 
 		 try
 		 {
 			 EnquiryDAO enqDAO= new EnquiryDAO(tx);
 			 tx.begin();
 			allDepartment=enqDAO.getAllDepartmentWithType( userVO);
 			essentialMap.put(enquiryConfig.ALL_DEPARTMENT_WITH_DEPARTMENT_TYPE, allDepartment);
 		 }catch(HisRecordNotFoundException e){
 			 tx.rollback();	
 			 e.printStackTrace(); 
 			 throw new HisRecordNotFoundException(e.getMessage()); 
 		 }
 		 catch(HisApplicationExecutionException e){	   		   	
 			 tx.rollback();
 			 e.printStackTrace();   		 
 			 throw new HisApplicationExecutionException();
 		 }
 		 
 		 catch(HisDataAccessException e){		
 			 tx.rollback();
 			 e.printStackTrace();   		 
 			 throw new HisDataAccessException();  	
 		 }
 		 catch(Exception e){
 			 e.printStackTrace();	
 			 tx.rollback();		
 			 throw new HisApplicationExecutionException();
 		 }
 		 finally{
 			 tx.close();			
 		 }
 		 return essentialMap;		
 		 
 	 }

 	public HospitalDepartmentEnquiryVO[] getDepartmentEnquiryDetail(String _deptCode,String _deptTypeCode,UserVO _userVO){
 		
 		HospitalDepartmentEnquiryVO[] departmentEnquiry=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			if(_deptTypeCode.equals(RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE))
				departmentEnquiry=enqDAO.getDepartmentEnquiryDetailWithUnit(_deptCode,_userVO);
			else if(_deptTypeCode.equals(RegistrationConfig.DEPT_TYPE_PARA_CLINICAL_VALUE))
				departmentEnquiry=enqDAO.getParaClinicDepartmentEnquiryDetail(_deptCode,_userVO);
			else 
				departmentEnquiry=enqDAO.getDepartmentEnquiryDetailWithoutUnit(_deptCode,_userVO);
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return departmentEnquiry;	
 	}

 	/* **********************************Hospital Consultant Enquiry ******************************/ 
 	
 	public Map getConsultantEnquiryEssential(String processType,UserVO userVO){
		 
 		Map essentialMap=new HashMap();
 		List consultantVOList=null;
 		JDBCTransactionContext tx =new JDBCTransactionContext();
 		 
 		 try
 		 {
 			 EnquiryDAO enqDAO= new EnquiryDAO(tx);
 			 tx.begin();
 			consultantVOList=enqDAO.getAllConsultant(processType,userVO);
 			essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY, consultantVOList);
 			
 		 }catch(HisRecordNotFoundException e){
 			 tx.rollback();	
 			 e.printStackTrace(); 
 			 throw new HisRecordNotFoundException(e.getMessage()); 
 		 }
 		 catch(HisApplicationExecutionException e){	   		   	
 			 tx.rollback();
 			 e.printStackTrace();   		 
 			 throw new HisApplicationExecutionException();
 		 }
 		 
 		 catch(HisDataAccessException e){		
 			 tx.rollback();
 			 e.printStackTrace();   		 
 			 throw new HisDataAccessException();  	
 		 }
 		 catch(Exception e){
 			 e.printStackTrace();	
 			 tx.rollback();		
 			 throw new HisApplicationExecutionException();
 		 }
 		 finally{
 			 tx.close();			
 		 }
 		 return essentialMap;		
 		 
 	 }
 	
 	public Map getConsultantEnquiryDetailByEmpNo(String empNo,UserVO userVO){
 		
 		Map essentialMap=new HashMap();
 		HospitalConsultantEnquiryVO [] consultantVO=null;
 		JDBCTransactionContext tx =new JDBCTransactionContext();
 		
 		try
 		{
 			EnquiryDAO enqDAO= new EnquiryDAO(tx);
 			tx.begin();
 			consultantVO=enqDAO.getConsultantDetailsBYEmpNo(empNo, userVO);
 			essentialMap.put(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY, consultantVO);
 			
 		}catch(HisRecordNotFoundException e){
 			tx.rollback();	
 			e.printStackTrace(); 
 			throw new HisRecordNotFoundException(e.getMessage()); 
 		}
 		catch(HisApplicationExecutionException e){	   		   	
 			tx.rollback();
 			e.printStackTrace();   		 
 			throw new HisApplicationExecutionException();
 		}
 		
 		catch(HisDataAccessException e){		
 			tx.rollback();
 			e.printStackTrace();   		 
 			throw new HisDataAccessException();  	
 		}
 		catch(Exception e){
 			e.printStackTrace();	
 			tx.rollback();		
 			throw new HisApplicationExecutionException();
 		}
 		finally{
 			tx.close();			
 		}
 		return essentialMap;		
 		
 	}
 	
 	public Map getConsultantEnquiryDetailByName(HospitalConsultantEnquiryVO consultantVO,UserVO userVO){
 		
 		Map essentialMap=new HashMap();
 		HospitalConsultantEnquiryVO [] consultantVOArray=null;
 		JDBCTransactionContext tx =new JDBCTransactionContext();
 		
 		try
 		{
 			EnquiryDAO enqDAO= new EnquiryDAO(tx);
 			tx.begin();
 			consultantVOArray=enqDAO.getConsultantDetailsByName(consultantVO, userVO);
 			//essentialMap.put(enquiryConfig.CONSULTANT_DETAIL_VO_ARRAY, consultantVO);
 			essentialMap.put(enquiryConfig.HOSPITAL_CONSULTANT_VO_ARRAY_VIEW, consultantVOArray);
 			
 		}catch(HisRecordNotFoundException e){
 			tx.rollback();	
 			e.printStackTrace(); 
 			throw new HisRecordNotFoundException(e.getMessage()); 
 		}
 		catch(HisApplicationExecutionException e){	   		   	
 			tx.rollback();
 			e.printStackTrace();   		 
 			throw new HisApplicationExecutionException();
 		}
 		
 		catch(HisDataAccessException e){		
 			tx.rollback();
 			e.printStackTrace();   		 
 			throw new HisDataAccessException();  	
 		}
 		catch(Exception e){
 			e.printStackTrace();	
 			tx.rollback();		
 			throw new HisApplicationExecutionException();
 		}
 		finally{
 			tx.close();			
 		}
 		return essentialMap;		
 		
 	}
 	
 	public Map getConsultantEnquiryUnitDetail(String deptUnit,UserVO userVO){
 		
 		String unitDays="";
 		List unitWorkingDetailList=null;
 		JDBCTransactionContext tx =new JDBCTransactionContext();
 		Map essentialMap=new HashMap();
 		HospitalDepartmentEnquiryVO [] hospitalDeptVO;
 		try
 		{
 			EnquiryDAO enqDAO= new EnquiryDAO(tx);
 			EssentialDAO essentialDAO= new EssentialDAO(tx);
 			tx.begin();
 			//unitRoomDays=essentialDAO.getUnitWorkingDays(deptUnit, userVO);
 			//unitRoomDays=unitRoomDays+"#"+enqDAO.getRoomByDeptUnit(deptUnit, userVO);// days#timing#room1#room2
 			//unitDays=enqDAO.getUnitWorkingDays(deptUnit, userVO);
 			//essentialMap.put(enquiryConfig.UNIT_WORKING_DAYS, unitDays);
 			unitWorkingDetailList=enqDAO.getUnitWorkingSchedule(deptUnit, userVO);
 			essentialMap.put(enquiryConfig.UNIT_WORKING_DAYS, unitWorkingDetailList);
 			//hospitalDeptVO=enqDAO.getDepartmentUnitRoomDetail(deptUnit, userVO);
 			//essentialMap.put(enquiryConfig.UNIT_ROOM_LIST, hospitalDeptVO);
 			
 		}catch(HisRecordNotFoundException e){
 			tx.rollback();	
 			e.printStackTrace(); 
 			throw new HisRecordNotFoundException(e.getMessage()); 
 		}
 		catch(HisApplicationExecutionException e){	   		   	
 			tx.rollback();
 			e.printStackTrace();   		 
 			throw new HisApplicationExecutionException();
 		}
 		
 		catch(HisDataAccessException e){		
 			tx.rollback();
 			e.printStackTrace();   		 
 			throw new HisDataAccessException();  	
 		}
 		catch(Exception e){
 			e.printStackTrace();	
 			tx.rollback();		
 			throw new HisApplicationExecutionException();
 		}
 		finally{
 			tx.close();			
 		}
 		return essentialMap;		
 		
 	}

 	
 	public Map getDepartmentUnitEnquiryDetail(String _deptUnitCode,UserVO _userVO){
 		
 		HospitalDepartmentEnquiryVO departmentUnitEnquiry=null;
 		HospitalDepartmentEnquiryVO[] departmentUnitConsultantEnquiry=null;
 		HospitalDepartmentEnquiryVO[] departmentUnitRoomEnquiry=null;
 		HospitalDepartmentEnquiryVO[] departmentUnitWardEnquiry=null;
		Map essentialMap=new HashMap();
		List wardFloorList=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			departmentUnitEnquiry=enqDAO.getDepartmentUnitEnquiryDetail(_deptUnitCode,_userVO);
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_ENQUIRY_DETAIL_VO, departmentUnitEnquiry);
			departmentUnitConsultantEnquiry=enqDAO.getDepartmentUnitConsultantDetail(_deptUnitCode,_userVO);
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_CONSULTANT_ENQUIRY_DETAIL_VO, departmentUnitConsultantEnquiry);
			//departmentUnitRoomEnquiry=enqDAO.getDepartmentUnitRoomDetail(_deptUnitCode,_userVO);
			//essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_ROOM_ENQUIRY_DETAIL_VO, departmentUnitRoomEnquiry);
			//String unitWorkinDays=enqDAO.getUnitWorkingDays(_deptUnitCode, _userVO);
			List unitWorkingDetailList=enqDAO.getUnitWorkingSchedule(_deptUnitCode, _userVO);
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_WORKING_DAYS, unitWorkingDetailList);
			departmentUnitWardEnquiry=enqDAO.getDepartmentUnitWardDetail(_deptUnitCode,_userVO);
			if(departmentUnitWardEnquiry!=null)
			for(int i=0;i<departmentUnitWardEnquiry.length;i++){
				String floorId[]=departmentUnitWardEnquiry[i].getLocationFloor().split(",");
				String floorName="";
				for(int j=0;j<floorId.length;j++){
					floorName=floorName+", "+enqDAO.getFloorNameByFloorId(floorId[j], _userVO);
				}
				departmentUnitWardEnquiry[i].setLocationFloor(floorName.replaceFirst(",", ""));
			}
			
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_WARD_ENQUIRY_DETAIL_VO, departmentUnitWardEnquiry);
			
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;	
 	}

 	
 	/* ************************************* Ward Enquiry *********************************************/
	public Map getWardEnquiryEssential(UserVO _uservo) {
		List wardList =new ArrayList();
 		Map essentialMap=new HashMap();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			wardList=enqDAO.getWardList(_uservo);
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_WARD_LIST, wardList);
		
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			essentialMap.put(enquiryConfig.DEPARTMENT_UNIT_WARD_LIST, wardList);
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}


	public List getWardEnquiryDetail(String wardCode, UserVO _uservo) {
		List<HospitalWardEnquiryVO> wardList =new ArrayList();
 		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			wardList=enqDAO.getWardEnquiryDetail(wardCode,_uservo);
			
			if(wardList!=null){
				String floorId[]=wardList.get(0).getLocationFloor().split(",");
				String floorName="";
				for(int j=0;j<floorId.length;j++){
					floorName=floorName+", "+enqDAO.getFloorNameByFloorId(floorId[j], _uservo);
				}
				for(int j=0;j<wardList.size();j++){
					wardList.get(j).setLocationFloor(floorName.replaceFirst(",", ""));
				}	
			}
					
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			 e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisApplicationExecutionException();
	   	 }
	   	 
	   	catch(HisDataAccessException e){		
	   		 tx.rollback();
	   		 e.printStackTrace();   		 
	   		 throw new HisDataAccessException();  	
	  	 }
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return wardList;
	}


	public Map getHospitalEssentials(UserVO userVO){
		 
 		Map essentialMap=new HashMap();
 		 
 		 JDBCTransactionContext tx =new JDBCTransactionContext();
 		HospitalMstVO _hospitalVO=new HospitalMstVO(); 
 		HospitalRegistrationTimingsVO[] _hospitalTimingsVO=null;
 		
 		 try
 		 {
 			 EnquiryDAO enqDAO= new EnquiryDAO(tx);
 			 tx.begin();
 			 
 			_hospitalVO=enqDAO.getHospitalEssentials1(userVO);
 			essentialMap.put(enquiryConfig.VO_OF_HOSPITAL_MST, _hospitalVO);
 			
 			
 			//_hospitalTimingsVO=enqDAO.getHospitalEssentials2(userVO);
 			//essentialMap.put(enquiryConfig.VO_ARRAY_OF_REGISTRATION_TIMINGS,_hospitalTimingsVO);
 			
 			
 		 }catch(HisRecordNotFoundException e){
 			 tx.rollback();	
 			 e.printStackTrace(); 
 			 throw new HisRecordNotFoundException(e.getMessage()); 
 		 }
 		 catch(HisApplicationExecutionException e){	   		   	
 			 tx.rollback();
 			 e.printStackTrace();   		 
 			 throw new HisApplicationExecutionException();
 		 }
 		 
 		 catch(HisDataAccessException e){		
 			 tx.rollback();
 			 e.printStackTrace();   		 
 			 throw new HisDataAccessException();  	
 		 }
 		 catch(Exception e){
 			 e.printStackTrace();	
 			 tx.rollback();		
 			 throw new HisApplicationExecutionException();
 		 }
 		 finally{
 			 tx.close();			
 		 }
 		 return essentialMap;		
 		 
 	 }
	

	/* ****************************OPd Schedule Enquiry *************************************/
	
	public Map getOpdScheduleEnquiryEssential(UserVO _uservo) {
		List deparmentList =new ArrayList();
		List specialClinicList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			deparmentList=enqDAO.getAllDepartmentList(_uservo);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DEPT_VO, deparmentList);
			specialClinicList=enqDAO.getSpecialClinicDeptList(_uservo);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_SPECIAL_CLINIC_VO, specialClinicList);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public Map getUnitWorkingDetail(String departmentCode,UserVO _uservo) {
		List roomList =new ArrayList();
		List unitWorkingDetailList =null;
		List <OpdScheduleEnquiryVO>opdScheduleEnqVOList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		Map unitWorkingDetailMap=new LinkedHashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			opdScheduleEnqVOList=enqDAO.getOpdScheduleEnquiryDetail(departmentCode, _uservo);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO, opdScheduleEnqVOList);
			
			for(int i=0;i<opdScheduleEnqVOList.size();i++){
				unitWorkingDetailList =new ArrayList();
				unitWorkingDetailList=enqDAO.getUnitWorkingSchedule(opdScheduleEnqVOList.get(i).getDepartmentUnitCode(), _uservo);
				unitWorkingDetailMap.put(opdScheduleEnqVOList.get(i).getDepartmentUnit(), unitWorkingDetailList);
			}
			//roomList=enqDAO.getRoomByDept(departmentCode, _uservo);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL, unitWorkingDetailMap);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public Map getSpecialClinicWorkingDetail(String departmentUnitCode,UserVO _uservo) {
		List unitWorkingDetailList =null;
		List <OpdScheduleEnquiryVO>opdScheduleEnqVOList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		Map unitWorkingDetailMap=new LinkedHashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			opdScheduleEnqVOList=enqDAO.getSpecialClinicDetail(departmentUnitCode, _uservo);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_DETAIL_VO, opdScheduleEnqVOList);
			/*roomList=enqDAO.getRoomByDept(departmentUnitCode.substring(0,3), _uservo);
			essentialMap.put(enquiryConfig.UNIT_ROOM_LIST, roomList);*/
			for(int i=0;i<opdScheduleEnqVOList.size();i++){
				unitWorkingDetailList =new ArrayList();
				unitWorkingDetailList=enqDAO.getUnitWorkingSchedule(opdScheduleEnqVOList.get(i).getDepartmentUnitCode(), _uservo);
				unitWorkingDetailMap.put(opdScheduleEnqVOList.get(i).getDepartmentUnit(), unitWorkingDetailList);
			}
			//roomList=enqDAO.getRoomByDept(departmentCode, _uservo);
			essentialMap.put(enquiryConfig.OPD_SCHEDULE_ENQUIRY_UNIT_WORKING_DETAIL, unitWorkingDetailMap);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public Map getTelephoneEnquiryEssentials(UserVO _uservo) {
		List telephonelist =new ArrayList();
		List deptTelephonelist =new ArrayList();
		List empTelephonelist =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			//telephonelist=enqDAO.getTelePhoneDetailOfIsImportant(_uservo);
			//essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_TELEPHONE_DETAIL_VO, telephonelist);
			//deptTelephonelist=enqDAO.getTelePhoneDetailOfDept(_uservo);
			//essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_DEPT_TELEPHONE_DETAIL_VO, deptTelephonelist);
			
			empTelephonelist=enqDAO.getTelePhoneDetailOfEmployee(_uservo);
			essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO_VIEW, empTelephonelist);
			
			//essentialMap.put(enquiryConfig.TELEPHONE_ENQUIRY_EMP_TELEPHONE_DETAIL_VO, empTelephonelist);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public Map getOperationTheaterEnquiryEssential(UserVO _uservo) {
		List operationTheaterVOList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			operationTheaterVOList=enqDAO.getOperationTheaterList(_uservo);
			essentialMap.put(enquiryConfig.OPERATION_THEATER_ENQUIRY_VO, operationTheaterVOList);
			
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public List getAllHospitalFacilityList(String isValid,UserVO _uservo) {
		List hospitalFacilityVOList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			if(isValid.equals("")) isValid=Config.IS_VALID_ACTIVE;
			HospitalFacilityMasterDAO daoObj= new HospitalFacilityMasterDAO(tx);
			tx.begin();
			
			hospitalFacilityVOList=daoObj.getAllHospitalFacilityList(isValid,_uservo);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return hospitalFacilityVOList;
	}
	
	public void saveHospitalFacility(HospitalFacilityMasterVO  hospitalFacilityMasterVO,UserVO _uservo){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			HospitalFacilityMasterDAO daoObj= new HospitalFacilityMasterDAO(tx);
			tx.begin();
			
			daoObj.saveHospitalFacility(hospitalFacilityMasterVO, _uservo);
			
		}		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
	}
	
	public HospitalFacilityMasterVO getHospitalFacility(HospitalFacilityMasterVO hospitalFacilityMasterVO, UserVO userVO) {
		List hospitalFacilityVOList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			HospitalFacilityMasterDAO daoObj= new HospitalFacilityMasterDAO(tx);
			tx.begin();
			
			hospitalFacilityMasterVO=daoObj.getHospitalFacilityList(hospitalFacilityMasterVO,userVO);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return hospitalFacilityMasterVO;
	}

	public void modifyHospitalFacility(HospitalFacilityMasterVO  hospitalFacilityMasterVO,UserVO _uservo){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			HospitalFacilityMasterDAO daoObj= new HospitalFacilityMasterDAO(tx);
			tx.begin();
			
			daoObj.modifyHospitalFacility(hospitalFacilityMasterVO, _uservo);
			daoObj.modifyInsertHospitalFacility(hospitalFacilityMasterVO, _uservo);
			
		}		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
	}
	
	public void changeDisplayOrder(HospitalFacilityMasterVO [] hospitalFacilityMasterVO,UserVO _uservo){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			HospitalFacilityMasterDAO daoObj= new HospitalFacilityMasterDAO(tx);
			tx.begin();
			for(int i=0;i<hospitalFacilityMasterVO.length;i++){
				daoObj.changeDisplayOrder(hospitalFacilityMasterVO[i], _uservo);
			}
			
		}		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
	}

	public void deleteHospitalFacility(HospitalFacilityMasterVO[] hospitalFacilityMasterVO, UserVO userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			HospitalFacilityMasterDAO daoObj= new HospitalFacilityMasterDAO(tx);
			tx.begin();
			for(int i=0;i<hospitalFacilityMasterVO.length;i++){
				daoObj.modifyHospitalFacility(hospitalFacilityMasterVO[i], userVO);
			}
			
		}		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
	}


	public Map getAllHospitalTarrifList(String groupId, UserVO _uservo) {
		List grouplist =new ArrayList();
		List voList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		try
		{
			HospitalChargeEnquiryDAO enqDAO= new HospitalChargeEnquiryDAO(tx);
			tx.begin();
			
			grouplist=enqDAO.getGroupList(_uservo);
			essentialMap.put(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_GROUP_LIST, grouplist);
			voList=enqDAO.getAllHospitalChargeList(groupId, _uservo);
			essentialMap.put(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST, voList);
			essentialMap.put(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW, voList);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public Map getChargeDetailByTariff(String tariffId,UserVO _uservo) {
		
		List voList =new ArrayList();
		List chargeTypeList =new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		try
		{
			HospitalChargeEnquiryDAO enqDAO= new HospitalChargeEnquiryDAO(tx);
			tx.begin();
			
			voList=enqDAO.getChargeByTariff(tariffId, _uservo);
			essentialMap.put(enquiryConfig.HOSPITAL_CHARGE_DETAIL_VO_LIST, voList);
			chargeTypeList=enqDAO.getChargeTypeList(_uservo);
			essentialMap.put(enquiryConfig.HOSPITAL_CHARGE_TYPE_LIST, chargeTypeList);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public List getLabEnquiryEssential(UserVO _uservo) {
		
		List voList =new ArrayList();
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		//Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			voList=enqDAO.getLaboratoryList(_uservo);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return voList;
	}
	
	public List getLabTestList(String labCode,UserVO _uservo) {
		
		List voList =new ArrayList();
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		//Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			
			voList=enqDAO.getLabTestList(labCode, _uservo);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return voList;
	}


	public Map getOTConsultantEnquiryEssential(UserVO _uservo) {
		List otConsultantList =new ArrayList();
		List otConsultantWorkingDays =new ArrayList();
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			otConsultantList=enqDAO.getOTConsultantList(_uservo);
			essentialMap.put(enquiryConfig.OT_CONSULTANT_LIST, otConsultantList);
			otConsultantWorkingDays=enqDAO.getOTConsultantWorkingDays(_uservo);
			essentialMap.put(enquiryConfig.OT_CONSULTANT_WORKING_DAYS, otConsultantWorkingDays);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public List getOTConsultantDetail(String empNo,UserVO _uservo) {
		HospitalConsultantEnquiryVO consultantVO=null;
		List consultantVOList=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		//Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			consultantVOList=enqDAO.getOTConsultantDetail(empNo, _uservo);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return consultantVOList;
	}
	
	public Map getHolidayList(String year,UserVO _uservo) {
		
		Map essentialMap=new HashMap();
		List holidayVOList=null;
		List yearList=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		//Map essentialMap=new HashMap();
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			holidayVOList=enqDAO.getHolidayList(year, _uservo);
			essentialMap.put(enquiryConfig.HOLIDAY_LIST, holidayVOList);
			yearList=enqDAO.getHolidayYearList(_uservo);
			essentialMap.put(enquiryConfig.HOLIDAY_YEAR_LIST, yearList);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}
	
	public List getGuestHouseList(UserVO _uservo) {
		
		List guestHouseVOList=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			guestHouseVOList=enqDAO.getGuestHouseList(_uservo);
			
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return guestHouseVOList;
	}
	
	public List getGuestHouseBedDetail(String guestHouse,UserVO _uservo) {
		
		List guestHouseVOList=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			guestHouseVOList=enqDAO.getGuestHouseBedDetail(guestHouse, _uservo);
					
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return guestHouseVOList;
	}
	
public List getTemplateListForGuidlines(UserVO _uservo) {
		
		List templateList=null;
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		
		try
		{
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			tx.begin();
			templateList=enqDAO.getTemplateListForGuidlines(_uservo);
					
		}catch(HisRecordNotFoundException e){
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e){	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		
		catch(HisDataAccessException e){		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e){
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return templateList;
	}
	
	public List<DeceasedDetailVO> getAllDeceasedListInMortuary(UserVO userVO)
	{
		JDBCTransactionContext tx=new JDBCTransactionContext();
		List<DeceasedDetailVO> lstDeceasedVO=new ArrayList<DeceasedDetailVO>();
		
		try
		{
			tx.begin();
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			lstDeceasedVO=enqDAO.getAllDeceasedListInMortuary(userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstDeceasedVO; 
	}
	
	public Map getDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		DeceasedDetailVO genAppVO=new DeceasedDetailVO();
	//	PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
	//	String postmortemStatus="";
		String postmortemStatusNLabel="";
		String postmortemStatusName="";
		String strStorage="";
		
		try
		{
			tx.begin();
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			
			genAppVO=enqDAO.getDeceasedGeneralAppearance(deceasedNo,userVO);
			essentialMap.put(enquiryConfig.DECEASED_GENERAL_APPEARANCE_VO, genAppVO);
			
			deceasedImageVO=enqDAO.getDeceasedDefaultImage(deceasedNo, userVO);
			essentialMap.put(enquiryConfig.DECEASED_DEFAULT_IMAGE_VO, deceasedImageVO);
			
			postmortemStatusNLabel=enqDAO.getPostmortemStatusForHandover(deceasedNo,userVO);
			//postmortemStatus=postmortemStatusNLabel.split("@")[1];
			postmortemStatusName=postmortemStatusNLabel.split("@")[0];
			essentialMap.put(enquiryConfig.DECEASED_POSTMORTEM_STATUS, postmortemStatusName);
			
			if(genAppVO.getBodyStatus().equals(MortuaryConfig.BODY_STATUS_IN_CHAMBER))
			{
				strStorage=enqDAO.getDeceasedStorageDetail(deceasedNo, userVO);
				essentialMap.put(enquiryConfig.DECEASED_STORAGE_DETAIL, strStorage);
			}
			
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return essentialMap;
	}
	
	public Map getEssentialForDeceasedEnquiry(UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List genderList=new ArrayList();
		
		try
		{
			tx.begin();
			EssentialDAO EssentialDAO= new EssentialDAO(tx);
			
			genderList=EssentialDAO.getGender(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,genderList);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return essentialMap;
	}
	
	public DeceasedDetailVO[] searchDeceased(String fName,String mName,String lName,String genderCode,String fromDate,String toDate,String chkUnknown,String chkUnclaimed,UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		DeceasedDetailVO[] deceasedDtlVO=null;
		
		try
		{
			tx.begin();
			EnquiryDAO enqDAO=new EnquiryDAO(tx);
			deceasedDtlVO=enqDAO.searchDeceased(fName,mName,lName,genderCode,fromDate,toDate,chkUnknown,chkUnclaimed,userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return deceasedDtlVO;
	}
	
	public Map getSearchDeceasedDtlByDeceaseNo(String deceasedNo,UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		DeceasedDetailVO genAppVO=new DeceasedDetailVO();
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
		DeceasedHandoverDtlVO deceasedHandoverVO=new DeceasedHandoverDtlVO();
		String postmortemStatusNLabel="";
		String postmortemStatusName="";
		String strStorage="";
		
		try
		{
			tx.begin();
			EnquiryDAO enqDAO= new EnquiryDAO(tx);
			
			genAppVO=enqDAO.getDeceasedGeneralAppearance(deceasedNo,userVO);
			essentialMap.put(enquiryConfig.DECEASED_GENERAL_APPEARANCE_VO, genAppVO);
			
			deceasedImageVO=enqDAO.getDeceasedDefaultImage(deceasedNo, userVO);
			essentialMap.put(enquiryConfig.DECEASED_DEFAULT_IMAGE_VO, deceasedImageVO);
			
			postmortemStatusNLabel=enqDAO.getPostmortemStatusForHandover(deceasedNo,userVO);
			postmortemStatusName=postmortemStatusNLabel.split("@")[0];
			essentialMap.put(enquiryConfig.DECEASED_POSTMORTEM_STATUS, postmortemStatusName);
			
			if(genAppVO.getBodyStatus().equals(MortuaryConfig.BODY_STATUS_IN_CHAMBER))
			{
				strStorage=enqDAO.getDeceasedStorageDetail(deceasedNo, userVO);
				essentialMap.put(enquiryConfig.DECEASED_STORAGE_DETAIL, strStorage);
			}
			if(genAppVO.getBodyStatus().equals(MortuaryConfig.BODY_STATUS_HANDOVER))
			{
				//handover
				deceasedHandoverVO=enqDAO.getDeceasedHandoverDetail(deceasedNo,userVO);
				essentialMap.put(enquiryConfig.DECEASED_HANDOVER_DETAIL, deceasedHandoverVO);
			}
			
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			e.printStackTrace(); 
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisApplicationExecutionException();
		}
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
			throw new HisDataAccessException();  	
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return essentialMap;
	}
	
}

