package medicalboard.transactions.bo;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.BoardConfigurationVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardBillingVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardExternalReferVO;
import hisglobal.vo.MedicalBoardRequisitionChangeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVerificationDtlVO;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.Test;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.dao.MbMasterEssentialDAO;
import medicalboard.masters.dao.MbMasterEssentialDAOi;
import medicalboard.transactions.dao.MbChecklistDetailDAO;
import medicalboard.transactions.dao.MbChecklistDetailDAOi;
import medicalboard.transactions.dao.MbReferMappingDAO;
import medicalboard.transactions.dao.MbReferMappingDAOi;
import medicalboard.transactions.dao.MbRequisitionChangeDAO;
import medicalboard.transactions.dao.MbRequisitionDetailDAO;
import medicalboard.transactions.dao.MbRequisitionDetailDAOi;
import medicalboard.transactions.dao.MbTransactionEssentialDAO;
import medicalboard.transactions.dao.MbTransactionEssentialDAOi;
import medicalboard.transactions.dao.MbVerificationDetailDAO;
import medicalboard.transactions.dao.MbVerificationDetailDAOi;
import medicalboard.transactions.dao.MbVisitDetailDAO;
import opd.dao.OpdEssentialDAO;
import registration.RegistrationConfig;
import registration.bo.PatientBOSupport;
import registration.dao.EpisodeRefDtlDAO;
import registration.dao.EpisodeRefDtlDAOi;
import registration.dao.EssentialDAO;
import registration.dao.PatientImageDtlDAO;


public class MedicalBoardEssentialBO implements MedicalBoardEssentialBOi
{

	public Map setMsNewRegistrationEssentials(UserVO _userVO){
		Map essentialMap=new HashMap();
		List lstPrimaryCat=null;        
		List lstAreaCategory=null;
		List lstAgeType=null;
		List lstGender=null;
		List lstLocation=null;
		List lstState=null;
		List lstCountry=null;
		List lstNationality=null;
		List lstDistrict=null;
//		List lstCertificateType =null;
		List lstOrganization =null;
//		List lstDepartmentDetail=null;
		List lstMaritalStatus=null;
		BoardConfigurationVO bConfigurationVO=new BoardConfigurationVO();
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;

		JDBCTransactionContext tx =new JDBCTransactionContext();
		try{
			tx.begin();
			EssentialDAO objEssentialDAO=new EssentialDAO(tx);
//			GlobalEssentialDAO objGlobalEssentialDAO=new GlobalEssentialDAO(tx);
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);

			lstPrimaryCat=essentialDAOi.getPatientCategory(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);			

			lstGender=objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER, lstGender);

			lstLocation=objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION, lstLocation);

			lstState=objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE, lstState);

			lstCountry=objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY, lstCountry);

			lstNationality=objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY, lstNationality);

			lstAreaCategory=objEssentialDAO.getAreaCategory();
			System.out.println("lstAreaCategory"+lstAreaCategory);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY, lstAreaCategory);

			lstAgeType=objEssentialDAO.getAgeType();
			System.out.println("lstAgeType"+lstAgeType);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE, lstAgeType);

			// String[] dt= objGlobalEssentialDAO.getSystemDate(_userVO);
			// essentialMap.put(Config.SYSDATE, dt[0]);	

			// Date dtobj= objGlobalEssentialDAO.getSystemDate(new Date());
			// essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);	        

			/*  amount= objEssentialDAO.getBillAmountBasedOnCategory(RegistrationConfig.DEFAULT_PRIMARY_CATEGORY,_userVO);
	        essentialMap.put(RegistrationConfig.AMOUNT_COLLECTED, amount);       
			 *///
			mCertificateTypeMstVOs=essentialDAOi.getCertificateType(_userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE,mCertificateTypeMstVOs);	

			lstOrganization=essentialDAOi.getOrganisationName(_userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME,lstOrganization);	

			//lstDepartmentDetail=essentialDAOi.getDepartmentDetail(_userVO);
			//essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_DEPARTMENT_DETAIL,lstDepartmentDetail);	

			lstDistrict=objEssentialDAO.getDistrictList(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE, lstDistrict);

			lstMaritalStatus=objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS, lstMaritalStatus);		

			bConfigurationVO=essentialDAOi.getDepartmentDetail(_userVO);
			essentialMap.put(MedicalBoardConfig.HMBT_BOARD_CONFIGURATION_DEPARTMENT_DETAIL, bConfigurationVO);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}



	public Map getCheckList(String certificateType,UserVO _userVO){
		Map essentialMap=new HashMap();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);

			MedicalBoardChecklistVO[] checklistDtlVO = null;
			checklistDtlVO=essentialDAOi.getAllChecklistDetails(certificateType,_userVO);
			essentialMap.put(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY, checklistDtlVO);	

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}


	public  MbOrganizationMstVO getOrganizationDetail(String orgId,UserVO _userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		MbOrganizationMstVO morgVO=new MbOrganizationMstVO();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			morgVO=essentialDAOi.getOrganizationDetail(orgId, _userVO);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return morgVO;		
	}




	public List getPatientCategory(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			list=essentialDAOi.getPatientCategory(userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,list);	
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return list;
	}

	public List getCertificateType(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List list=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
//			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
//			list=essentialDAOi.getCertificateType(userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE,list);	
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return list;
	}



	public List getOrganisationName(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List list;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			list=essentialDAOi.getOrganisationName(userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME,list);	
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return list;
	}


	/* **************************Medical Board Requisition *****************************/ 

	public List getPatientListForRequisition(UserVO userVO)
	{
		List<PatientVO> list=new ArrayList<PatientVO>();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			list=essentialDAOi.getPatientListForRequisition(userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return list;
	}


	public PatientVO getPatientForRequisitionByCrNo(String crNo,UserVO userVO)
	{
		PatientVO patientVO=new PatientVO();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			patientVO=essentialDAOi.getPatientForRequisitionByCrNo(crNo, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return patientVO;
	}


	public Map getCertificateTypeForRequisition(UserVO userVO)
	{
		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
//		String isBillingDone="";
		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			mCertificateTypeMstVOs=essentialDAOi.getCertificateTypeForRequisition(userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE_LIST, mCertificateTypeMstVOs);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return essentialMap;
	}



	public void saveMedicalBoardRequisition(List lstCheckList,MedicalBoardRequisitionVO requisitionVO,MedicalBoardBillingVO billingVO, UserVO userVO)
	{
//		MbCertificateTypeMstVO[] mCertificateTypeMstVOs = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			MbRequisitionDetailDAOi requisitionDAO = new MbRequisitionDetailDAO(tx);
			MbChecklistDetailDAOi checkListDtlDAO = new MbChecklistDetailDAO(tx);
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);

			requisitionVO=requisitionDAO.create(requisitionVO, userVO);

			for(int i=0;i<lstCheckList.size();i++){
				MedicalBoardChecklistVO checkListVO=(MedicalBoardChecklistVO)lstCheckList.get(i);
				checkListVO.setReqID(requisitionVO.getReqID());
				checkListDtlDAO.create(checkListVO, userVO);
			}

			if(billingVO!=null)
				essentialDAO.updateBillingQuantity(billingVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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

	}


	public Map getEssentialForRequisition(MbCertificateTypeMstVO certificateTypeMstVO,String patCrNo, UserVO userVO){
		Map essentialMap=new HashMap();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List <MedicalBoardBillingVO> billingVO=null;
		List locationEligiblityMstVOList=null;
		List list=null;
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);

			MedicalBoardChecklistVO[] checklistDtlVO = null;
			int count=essentialDAOi.checkDuplicateRequisitionForCertitificateType(patCrNo, certificateTypeMstVO.getCertificateTypeID(), userVO);
			if(count>0){
				throw new HisDuplicateRecordException("Request exists for the certificate Type");
			}
			checklistDtlVO=essentialDAOi.getAllChecklistDetails(certificateTypeMstVO.getCertificateTypeID(),userVO);
			if(checklistDtlVO.length==0){
				checklistDtlVO=null;
			}
			essentialMap.put(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY,checklistDtlVO);

			if(certificateTypeMstVO.getIsBillApply().equals("1")){
				billingVO=essentialDAOi.getBillingDetail(patCrNo, userVO);
				essentialMap.put(MedicalBoardConfig.BILLING_VO, billingVO);	
			}
			if(certificateTypeMstVO.getLocationBound().equals(MedicalBoardConfig.IS_LOCATIONBOUND_YES)){
				locationEligiblityMstVOList=essentialDAOi.getLocationEligliblity(certificateTypeMstVO, userVO);
				essentialMap.put(MedicalBoardConfig.LOCATION_ELIGIBLITY_MSTVO_LIST,locationEligiblityMstVOList);
			}

			list=essentialDAOi.getOrganisationName(userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME,list);	

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap); 
		}
		catch(HisDuplicateRecordException e){
			tx.rollback();		
			throw new HisDuplicateRecordException(e.getMessage(),essentialMap); 
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}


	public List getScheduleListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List scheduleList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			scheduleList=essentialDAOi.getScheduleForRequisition(certificateTypeMstVO, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return scheduleList;		
	}

	public List getCIDNoListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List cidList = null;

		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			cidList = essentialDAOi.getCIDNoListForRequisition(certificateTypeMstVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return cidList;
	}

	/* **************************Medical Board Requisition Change *****************************/

	public List getPatientMbRequisitionsByCrNo(String patCrNo, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			requisitionVOList=essentialDAOi.getPatientMbRequisitionsByCrNo(patCrNo, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return requisitionVOList;		
	}


	public List getMbRequisitionsByOrg(String orgId, String orgName,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			requisitionVOList=essentialDAOi.getMbRequisitionsByOrg(orgId, orgName, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return requisitionVOList;		
	}


	public void saveMedicalBoardRequisitionChange(List <MedicalBoardRequisitionVO>requisitionVOList,
			List<MedicalBoardRequisitionChangeVO> requisitionChangeVOList, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();

		try{
			tx.begin();
			MbRequisitionDetailDAOi requisitionDAO = new MbRequisitionDetailDAO(tx);
			MbRequisitionChangeDAO requisitionChangeDAO = new MbRequisitionChangeDAO(tx);

			for(int i=0;i<requisitionVOList.size();i++){
				requisitionDAO.update(requisitionVOList.get(i), userVO);
			}

			for(int i=0;i<requisitionVOList.size();i++){
				requisitionChangeDAO.create(requisitionChangeVOList.get(i), userVO);
			}

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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}

	}


	/* *************************************Candidate Acceptance*********************************************/

	public List getCertificateTypeList(UserVO userVO,String date){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List certificateTypeList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			certificateTypeList=essentialDAOi.getCertificateTypeList(userVO,date);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return certificateTypeList;		
	}


	// get the list of the candidate who are scheduled today for medical examination

	public List getCandidateList(String certificateTypeID, UserVO userVO,String examDate){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			//String reqStaus=MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED;
			requisitionVOList=essentialDAOi.getcandidateListByCerficateType(certificateTypeID, userVO,examDate);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return requisitionVOList;		
	}
	public List getPrevCandidateList(String certificateTypeID, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			//String reqStaus=MedicalBoardConfig.REQUEST_STATUS_REQUEST_ACCEPTED;  getcandidateListByCerficateTypePrevdate
			requisitionVOList=essentialDAOi.getcandidateListByCerficateTypePrevdate(certificateTypeID, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return requisitionVOList;		
	}


	public Map getCandidateAcceptanceEssential(MedicalBoardRequisitionVO requisitionVO, UserVO userVO,String dtae){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List checklistVOList=null;
		List previouslyAddedList=null;
		List mbBoardList=null;
		List referDetailList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			MbChecklistDetailDAO checklistDAO = new MbChecklistDetailDAO(tx);

			EpisodeVO episodeVO=essentialDAOi.getEpisodeDetailByCrNo(requisitionVO.getPatCrNo(), userVO);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_EPISODE_VO, episodeVO);

			mbBoardList=essentialDAOi.getBoardListByCerficateType(requisitionVO.getCertificateTypeID(), userVO,dtae);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_LIST, mbBoardList);

			//list of checklist which are complusory at acceptance
			checklistVOList=essentialDAOi.getCheckListForAcceptance(requisitionVO.getCertificateTypeID(),requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST, checklistVOList);

			//list of checklist which are checked at the time of requisition
			previouslyAddedList=checklistDAO.getCheckListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST, previouslyAddedList);

			//get the refer detail of the candidate if the candidate is attending at next visit date
			if((Integer.parseInt(requisitionVO.getVisitNo()))>1){
				referDetailList=essentialDAOi.getMBCandidateRefDetail(requisitionVO.getPatCrNo(), userVO);
				essentialMap.put(MedicalBoardConfig.EPISODE_REF_VO_LIST, referDetailList);
			}	
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}
	/**
	 * Save candidate Acceptance this method record following informations
	 * make old patient visit stamp
	 * insert record in medical_visit_dtl
	 * update the req status of requisition_dtl table
	 */
	public void saveCandidateAcceptance(List checklistVOList,
			MedicalBoardRequisitionVO requisitionVO, EpisodeVO episodeVO,
			PatientVO patientVO,MedicalBoardVisitDtlVO visitDetailVO, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();

		try{
			tx.begin();
			MbChecklistDetailDAOi checklistDAO = new MbChecklistDetailDAO(tx);
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			MbRequisitionDetailDAOi requisitionDAO = new MbRequisitionDetailDAO(tx);
			MbVisitDetailDAO visitDetailDAO = new MbVisitDetailDAO(tx);
			
			if(requisitionVO.getChkPreviousDate().equals("0"))
			{
				//insert the checklist compulsory at acceptance
				if(checklistVOList!=null){
					for(int i=0;i<checklistVOList.size();i++){
						checklistDAO.create((MedicalBoardChecklistVO)checklistVOList.get(i), userVO);
					}
				}	
				//update the req status and assign board
				requisitionDAO.updateReqStatus(requisitionVO, userVO);
	
				// make an old visit stamp
				EpisodeVO arrEpisodeVO[]=new EpisodeVO[1];
				arrEpisodeVO[0]=episodeVO;
				//if patient has no visit stamp today
				if(!episodeVO.getVisitedToday().equals("1")){
					//PatientBOSupport.oldDeptVisitStamp(arrEpisodeVO, patientVO, userVO, tx);
					PatientBOSupport.oldDeptVisitStamp(arrEpisodeVO, patientVO, userVO, tx,new EpisodeRefDtlVO());
				}
				episodeVO=essentialDAO.getEpisodeCodeByCrNo(arrEpisodeVO[0].getPatCrNo(), userVO);
	
				visitDetailVO.setEpisodeCode(episodeVO.getEpisodeCode());
				visitDetailVO.setVisitNo(episodeVO.getEpisodeVisitNo());
	
				//create a visit stamp in medical_visit_dtl table
				visitDetailDAO.create(visitDetailVO, userVO);
			
			}
			
			else{
				
				EpisodeVO arrEpisodeVO[]=new EpisodeVO[1];
				arrEpisodeVO[0]=episodeVO;
				if((requisitionVO.getChkPreviousDate()!=null)&&requisitionVO.getChkPreviousDate().equals("1"))
					episodeVO=essentialDAO.getEpisodeCodeByCrNoPrevDate(arrEpisodeVO[0].getPatCrNo(), userVO,requisitionVO.getStrPreviousDate());
			
							
				//insert the checklist compulsory at acceptance
				if(checklistVOList!=null){
					for(int i=0;i<checklistVOList.size();i++){
						checklistDAO.create((MedicalBoardChecklistVO)checklistVOList.get(i), userVO);
					}
				}	
				//update the req status and assign board
				requisitionDAO.updateReqStatus(requisitionVO, userVO);
			
				episodeVO=essentialDAO.getEpisodeCodeByCrNo(arrEpisodeVO[0].getPatCrNo(), userVO);
	
				visitDetailVO.setEpisodeCode(episodeVO.getEpisodeCode());
				visitDetailVO.setVisitNo(episodeVO.getEpisodeVisitNo());
	
				//create a visit stamp in medical_visit_dtl table
				visitDetailDAO.create(visitDetailVO, userVO);
			}

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}

	}


	/* ************************************ Medical Exam ******************************************/

	public List getCertificateTypeListForMedicalExam(UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List certificateTypeList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			certificateTypeList=essentialDAOi.getCertificateTypeListForMedicalExam(userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return certificateTypeList;		
	}

	// get the list of the candidate who attended today for medical examination

	public Map getCandidateListForMedicalExam(String certificateTypeID, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			String reqStaus=MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE;
			requisitionVOList=essentialDAOi.getCandidateListForMedicalByCerficateType(certificateTypeID,reqStaus, userVO);
			essentialMap.put(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST, requisitionVOList);

			String departmentUnitCode=essentialDAOi.getMedicalBoardDepartment(userVO);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_DEPT_UNIT, departmentUnitCode);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}


	public Map getReferMappingList(String certificateTypeID, EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List referMappingVOList=null;
		EpisodeRefDtlVO []episodeRefVOArray=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbReferMappingDAOi referMappingDAO = new MbReferMappingDAO(tx);
			EpisodeRefDtlDAOi episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);

			referMappingVOList=referMappingDAO.selectByCertificateType(certificateTypeID, userVO);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST, referMappingVOList);
			if(episodeRefDtlVO!=null){
				episodeRefVOArray=episodeRefDtlDAO.getEpisodeReferDtlByCrNoEpisodeCode(episodeRefDtlVO, userVO);
				essentialMap.put(MedicalBoardConfig.EPISODE_REF_VO_LIST, episodeRefVOArray);
			}

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}


	public void saveCandidateReferDetail(EpisodeRefDtlVO[] episodeRefVO,
			EpisodeVO[] episodeVO,MedicalBoardVisitDtlVO []visitDetailVO,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();

		try{
			tx.begin();
			EpisodeRefDtlDAO episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			MbVisitDetailDAO mvVisitDtlDAO = new MbVisitDetailDAO(tx);

			for(int i=0;i<episodeVO.length;i++){
				for(int j=0;j<episodeRefVO.length;j++){
					HelperMethods.populate(episodeRefVO[j], episodeVO[i]);
					episodeRefVO[j].setIsValid(Config.IS_VALID_ACTIVE);
					episodeRefDtlDAO.create(episodeRefVO[j], userVO);
				}
			}
			for(int i=0;i<visitDetailVO.length;i++){
				mvVisitDtlDAO.update(visitDetailVO[i], userVO);
			}
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}

	}

	public void raiseCandidateInvestigation(List patientDtlVOList,EpisodeVO[] episodeVO, List<Test> testVOList,
			MedicalBoardVisitDtlVO[] visitDetailVO,String sysdate, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			MbVisitDetailDAO mvVisitDtlDAO = new MbVisitDetailDAO(tx);

			for(int i=0;i<episodeVO.length;i++){
				//raise investigation of testVOList for each episode
				String count[]=essentialDAO.raiseMBInvestigationRequest((PatientDetailVO)patientDtlVOList.get(i), episodeVO[i],
						testVOList, sysdate, MedicalBoardConfig.REQUEST_AREA, MedicalBoardConfig.REQUEST_TYPE, userVO);
				System.out.println("No of investigation raised :"+count.length);
				System.out.println("Requisition No :"+count[0]);
			}

			for(int i=0;i<visitDetailVO.length;i++){
				mvVisitDtlDAO.updateIsInvRaised(visitDetailVO[i], userVO);
			}
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}

	}


	public List getMBCandidateExtRefDetail(String reqId,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List externalRefDtlVOList=null;
		try{
			tx.begin();
			MbTransactionEssentialDAO essentialDAO = new MbTransactionEssentialDAO(tx);
			externalRefDtlVOList=essentialDAO.getMBCandidateExtRefDetail(reqId, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return externalRefDtlVOList;		
	}


	public List getMBCandidateInvDetail(EpisodeVO episodeVO,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List invRequisitionVOList=null;
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			invRequisitionVOList=essentialDAO.getInvDetailByEpisodeCode(episodeVO, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return invRequisitionVOList;		
	}


	public Map getMBInvestigationEssential(MedicalBoardRequisitionVO [] requisitionVO,String certificateTypeID,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List investigationMappingVOList=null;
		List sampleList=null;
		List episodeVOList=new ArrayList();
		List patientDtlVOList=new ArrayList();
		List invReqVOList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			investigationMappingVOList=essentialDAO.getMBInvestigationMapping(certificateTypeID, userVO);
			essentialMap.put(MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST, investigationMappingVOList);

			sampleList=essentialDAO.getMBInvestigationTestSamples(userVO);
			essentialMap.put(MedicalBoardConfig.MB_INVESTIGATION_TEST_SAMPLE_LIST, sampleList);

			EpisodeVO episodeVO;
			PatientDetailVO patientDtlVO;
			for(int i=0;i<requisitionVO.length;i++){
				episodeVO=new EpisodeVO();
				HelperMethods.populate(episodeVO, requisitionVO[i]);
				episodeVO=essentialDAO.getEpisodeDetailByEpisodeCode(episodeVO, userVO);
				episodeVOList.add(episodeVO);
			}

			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_EPISODE_VO_LIST, episodeVOList);

			for(int i=0;i<requisitionVO.length;i++){
				patientDtlVO=new PatientDetailVO();
				HelperMethods.populate(patientDtlVO, requisitionVO[i]);
				patientDtlVO=essentialDAO.getPatientDetailByEpisodeCode(patientDtlVO, userVO);
				patientDtlVOList.add(patientDtlVO);
			}
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_PATIENT_DTL_VO_LIST, patientDtlVOList);

			if(requisitionVO.length==1){
				episodeVO=new EpisodeVO();
				HelperMethods.populate(episodeVO,requisitionVO[0]);
				invReqVOList=essentialDAO.getInvDetailByEpisodeCode(episodeVO, userVO);
				essentialMap.put(MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST, invReqVOList);
			}

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}

	//************************************Post Medical Entry*********************************************/

	public Map getMBPostEntryEssential(UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List certificateTypeList=null;
		//List boardList=null;
		List consultantList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			certificateTypeList=essentialDAO.getCertificateTypeListForPostEntry(userVO);
			essentialMap.put(MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE, certificateTypeList);

			/*boardList=essentialDAO.getAssginedBoard(userVO);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_LIST, boardList);
			 */
			consultantList=essentialDAO.getConsultantList(userVO);
			essentialMap.put(MedicalBoardConfig.MB_CONSULTANT_LIST, consultantList);

			/*
			//list of checklist which are checked at the time of requisition
			previouslyAddedList=checklistDAO.getCheckListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST, previouslyAddedList);
			 */

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}

	public List getCandidateListForPostEntry(String certificateTypeID,String boardNo,String fromDate,String toDate, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;
		//Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			String reqStaus=MedicalBoardConfig.REQUEST_STATUS_ATTENDENCE_ON_VISIT_DATE;
			requisitionVOList=essentialDAOi.getCandidateListForPostEntry(certificateTypeID,reqStaus,boardNo,fromDate,toDate,userVO);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return requisitionVOList;		
	}

	/**
	 * get candidate detail for post entry
	 */
	public Map getCandidateDetailForPostEntry(MedicalBoardRequisitionVO requisitionVO, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List checklistVOList=null;
		List episodeRefVOList=null;
		//List invReqVOList=null;
		List extRefVOList=null;
		List opinionListByReqId=null;
		List boardMemberListByReqId=null;
		List previouslyAddedList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			MbTransactionEssentialDAO essentialDAO = new MbTransactionEssentialDAO(tx);
//			EpisodeRefDtlDAOi episodeRefDtlDAO = new EpisodeRefDtlDAO(tx);
			MbChecklistDetailDAO checklistDAO = new MbChecklistDetailDAO(tx);

			//checklistVOList=essentialDAOi.getCheckListForPostEntry(requisitionVO.getCertificateTypeID(), userVO);
			//essentialMap.put(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST, checklistVOList);

			//list of checklist which are checked at the time of requisition
			previouslyAddedList=checklistDAO.getCheckListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST, previouslyAddedList);

			//list of checklist which are complusory at acceptance
			checklistVOList=essentialDAOi.getCheckListForAcceptance(requisitionVO.getCertificateTypeID(),requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST, checklistVOList);

			episodeRefVOList=essentialDAOi.getMBCandidateRefDetail(requisitionVO.getPatCrNo(), userVO);
			essentialMap.put(MedicalBoardConfig.EPISODE_REF_VO_LIST, episodeRefVOList);

			extRefVOList=essentialDAO.getMBCandidateExtRefDetail(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.EPISODE_EXT_REF_VO_LIST, extRefVOList);

//			EpisodeVO episodeVO=new EpisodeVO();
//			HelperMethods.populate(episodeVO, requisitionVO);
//			invReqVOList=essentialDAOi.getInvDetailByEpisodeCode(episodeVO, userVO);
//			essentialMap.put(MedicalBoardConfig.INV_REQUISITION_DTL_VO_LIST, invReqVOList);

			opinionListByReqId=essentialDAOi.getOpinionListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID, opinionListByReqId);

			boardMemberListByReqId=essentialDAOi.getBoardMemberListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.ALL_BOARD_MEMBER_LIST_BY_REQUISITION_ID, boardMemberListByReqId);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}

	//Save candidate post medical entry
	public void savePostEntryDetail(MedicalBoardRequisitionVO requisitionVO,
			MedicalBoardRequisitionChangeVO reqChangeVO,String isMedicalPerformed,List checklistVOList,List verificationVOList,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();

		try{
			tx.begin();
			MbRequisitionChangeDAO reqChangeDAO=new MbRequisitionChangeDAO(tx);
			MbRequisitionDetailDAOi requisitionDAO=new MbRequisitionDetailDAO(tx);
			MbChecklistDetailDAOi checklistDtlDAO=new MbChecklistDetailDAO(tx);
			MbVerificationDetailDAOi verificationDAO=new MbVerificationDetailDAO(tx);

			if(isMedicalPerformed.equals(MedicalBoardConfig.IS_MEDICAL_PERFORMED_YES)){
				requisitionDAO.updatePostEntryDetail(requisitionVO, userVO);

				for(int i=0;i<checklistVOList.size();i++){
					checklistDtlDAO.create((MedicalBoardChecklistVO)checklistVOList.get(i), userVO);
				}
			}	
			if(isMedicalPerformed.equals(MedicalBoardConfig.IS_MEDICAL_PERFORMED_NO)){
				if(reqChangeVO!=null){
					reqChangeDAO.create(reqChangeVO, userVO);
					requisitionDAO.update(requisitionVO, userVO);
					requisitionDAO.updateReqStatus(requisitionVO, userVO);
				}
			}

			if(verificationVOList!=null)
			{
				for(int i=0;i<verificationVOList.size();i++)
				{
					MedicalBoardVerificationDtlVO verificationVO=(MedicalBoardVerificationDtlVO)verificationVOList.get(i);
					verificationDAO.create(verificationVO, userVO);
				}
			}
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}

	}


	/*	public MedicalBoardChecklistVO[] getAllChecklistDetails(String certificateType,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MedicalBoardChecklistVO[] checklistDtlVO = null;

		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			checklistDtlVO=essentialDAOi.getAllChecklistDetails(certificateType,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
		return checklistDtlVO;
	}*/


	public Map setMedicalBoardInitializationEssentials(UserVO _userVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List allCertificateTypeList=null;
		List empDoctorList=null;
		List empEscortList=null;
		List rollList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);

			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 

			empDoctorList=mEssentialDAOi.getEmpDoctorList(_userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_EMP_DOCTOR,empDoctorList);	

			empEscortList=mEssentialDAOi.getEmpEscortList(_userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_EMP_ESCORTS, empEscortList);

			rollList=mEssentialDAOi.getRollList(_userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_ROLL, rollList);

			allCertificateTypeList=essentialDAOi.getAllCertificateTypeList(_userVO);
			essentialMap.put(MedicalBoardConfig.ALL_CERTIFICATE_TYPE_LIST, allCertificateTypeList);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}


	public List getScheduleList(MbCertificateTypeMstVO certificateTypeMstVO,UserVO _UserVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List scheduleList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			scheduleList=essentialDAOi.getScheduleList(certificateTypeMstVO, _UserVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return scheduleList;		
	}

	public Map getBoardDetail(String certificateTypeId ,String scheduleDate,UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
//		List medicalBoardList=null;
		Map essentialMap=new HashMap();
		List assignBoardList=null;
		List availableBoardDetailList=null;
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
//			BoardDetailDAOi boardDetailDAO=new BoardDetailDAO(tx);

			/*
			String count=boardDetailDAO.checkIsBoardExistForCertifecateType(certificateTypeId, scheduleDate, _UserVO);
			if(count.equals("0"))
			{
				medicalBoardList=essentialDAO.getBoardDetailByCerficateType(certificateTypeId, _UserVO);
				essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_LIST_BY_CERTIFICATE_TYPE, medicalBoardList);
			}
			else
			{
				assignBoardList=essentialDAO.getAssignBoardDetailList(certificateTypeId,scheduleDate, _UserVO);
				essentialMap.put(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE, assignBoardList);
			}
			 */

			assignBoardList=essentialDAO.getAssignBoardDetailList(certificateTypeId,scheduleDate, _UserVO);
			essentialMap.put(MedicalBoardConfig.ASSIGN_BOARD_LIST_BY_CERTIFICATE_TYPE, assignBoardList);

			availableBoardDetailList=essentialDAO.getAvailableBoardDetailList(certificateTypeId, scheduleDate, _UserVO);
			essentialMap.put(MedicalBoardConfig.NEW_BOARD_ADD_LIST, availableBoardDetailList);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;
	}

	public List getTeamDetailByBoardId(String boardId,UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List teamDetailList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			teamDetailList=essentialDAOi.getTeamDetailByBoardId(boardId, _UserVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return teamDetailList;		

	}


	public List getAvailableBoardDetailList(String certificateTypeId ,String scheduleDate,UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List availableBoardDetailList=null;

		try{

			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			availableBoardDetailList=essentialDAO.getAvailableBoardDetailList(certificateTypeId, scheduleDate, _UserVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return availableBoardDetailList;		

	}


	public List getAssignTeamDetail(String boardNo,UserVO _UserVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List teamDetailList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			teamDetailList=essentialDAOi.getAssignTeamDetail(boardNo, _UserVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return teamDetailList;		

	}


	public Map setCertificateHandoverEssentials(UserVO _userVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List varifiedCertificateTypeList=null;
		Map essentialMap=new HashMap();
		List relationList=null;
		List handOverByEmpList=null;
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			OpdEssentialDAO objDAO = new OpdEssentialDAO(tx);

			varifiedCertificateTypeList=essentialDAOi.getVarifiedCertificateTypeList(_userVO);
			essentialMap.put(MedicalBoardConfig.ALL_VARIFIED_CERTIFICATE_TYPE_LIST,varifiedCertificateTypeList);	

			relationList=objDAO.getAllRelationList(_userVO);
			essentialMap.put(MedicalBoardConfig.ALL_RELATIONSHIP_LIST,relationList);

			handOverByEmpList=essentialDAOi.getHandOverByEmpList(_userVO);
			essentialMap.put(MedicalBoardConfig.ALL_HAND_OVER_BY_EMP_LIST,handOverByEmpList);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}


	public Map getReqDateByCertificateTypeId(String certificateTypeId,UserVO _userVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List reqDateByCertificateTypeIdList=null;
		List cadidateList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);


			reqDateByCertificateTypeIdList=essentialDAOi.getReqDateByCertificateTypeId(certificateTypeId,_userVO);
			essentialMap.put(MedicalBoardConfig.ALL_REQ_DATE_BY_CERTIFICATE_TYPE_ID,reqDateByCertificateTypeIdList);	

			cadidateList=essentialDAOi.getAllCandidateListByCertificateType(certificateTypeId,_userVO);
			essentialMap.put(MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE,cadidateList);


		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;		
	}

	public List getCandidateListByReqDate(String certificaetTypeId,String reqDate,UserVO _userVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List cadidateList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);


			cadidateList=essentialDAOi.getCandidateListByReqDate(certificaetTypeId,reqDate,_userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return cadidateList;		
	}

	public List getcandidateListByCerficateType(String certificaetTypeId,UserVO _userVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List cadidateList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);


			cadidateList=essentialDAOi.getAllCandidateListByCertificateType(certificaetTypeId,_userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return cadidateList;		
	}

	public List getAllCandidateListByCrNo(String patCrNo,UserVO userVO)
	{

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List cadidateList=null;

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);


			cadidateList=essentialDAOi.getAllCandidateListByCrNo(patCrNo,userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return cadidateList;		
	}

	public Map getMBCertVerificationEssential(UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List certificateTypeList=null;
		List boardList=null;
//		List consultantList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			certificateTypeList=essentialDAO.getCertTypeListForCertVerification(userVO);
			essentialMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_LIST_FOR_CERT_VERIFICATION, certificateTypeList);

			boardList=essentialDAO.getAssginedBoardForVerification(userVO);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_LIST_FOR_CERT_VERIF, boardList);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;	
	}

	public List getCandidateListForCertVerification(String certificateTypeID,String boardNo,String fromDate,String toDate, UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List requisitionVOList=null;
		//Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAOi = new MbTransactionEssentialDAO(tx);
			String reqStaus=MedicalBoardConfig.REQUEST_STATUS_MEDICAL_PERFORMED;
			requisitionVOList=essentialDAOi.getCandidateListForCertVerification(certificateTypeID,reqStaus,boardNo,fromDate,toDate,userVO);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return requisitionVOList;		
	}

	public Map getMBBoardMemberOpinionDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();

		List opinionListByReqId=null;
		List boardMemberOpinionList=null;
		Map essentialMap=new HashMap();
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);

			boardMemberOpinionList=essentialDAO.getBoardMemberOpinionListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.BOARD_MEMBER_OPINION_LIST_BY_REQID, boardMemberOpinionList);

			opinionListByReqId=essentialDAO.getOpinionListByReqId(requisitionVO.getReqID(), userVO);
			essentialMap.put(MedicalBoardConfig.ALL_OPINION_LIST_BY_REQUISITION_ID, opinionListByReqId);
		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return essentialMap;	
	}

	public String getScanFlag(MedicalBoardRequisitionVO requisitionVO,UserVO userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		String isDocPresent="";
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);

			isDocPresent=essentialDAO.getScanFlag(requisitionVO.getPatCrNo(), userVO);
			//essentialMap.put(MedicalBoardConfig.BOARD_MEMBER_OPINION_LIST_BY_REQID, boardMemberOpinionList);


		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return isDocPresent;	
	}


	public String getLastCertificateNo(String strCertTypeId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String certReqNo = "";
		try
		{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);

			certReqNo = essentialDAO.getLastCertMeDcNo(strCertTypeId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return certReqNo;	
	}

	
	public List getBoardListForPostEntry(String certificateTypeID,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
//		List certificateTypeList=null;
		List boardList=null;
//		List consultantList=null;
		Map essentialMap=new HashMap();

		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);

			boardList=essentialDAO.getAssginedBoardCerttificateBased(certificateTypeID,userVO);
			essentialMap.put(MedicalBoardConfig.MEDICAL_BOARD_LIST, boardList);



		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return boardList;	
	}

	public void saveExternalRefer(MedicalBoardExternalReferVO[] externalReferVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//Map essentialMap=new HashMap();
		MbTransactionEssentialDAO essentialDAO = new MbTransactionEssentialDAO(tx);
		try
		{
			tx.begin();
			for(int i=0;i<externalReferVO.length;i++)
			{
				essentialDAO.saveExternalRefer(externalReferVO[i],userVO);
			}


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
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

	}

	public List getMBCandidateRefDetail(String crNo,UserVO userVO){

		JDBCTransactionContext tx =new JDBCTransactionContext();
		List episodeRefDtlVOList=null;
		try{
			tx.begin();
			MbTransactionEssentialDAOi essentialDAO = new MbTransactionEssentialDAO(tx);
			episodeRefDtlVOList=essentialDAO.getMBCandidateRefDetail(crNo, userVO);

		}
		catch(HisRecordNotFoundException e){
			tx.rollback();		
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
			System.out.println("error.... Essential BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally{
			tx.close();			
		}
		return episodeRefDtlVOList;		
	}


	public void savePatientImage(PatientImageDtlVO patImageVO, UserVO userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			patImageDAO.create(patImageVO, userVO);
			patImageDAO.updateIsDefault(patImageVO, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
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
	
	public String getMaxCRNOPatientImage(String strCRNo,UserVO userVO)
	{

		JDBCTransactionContext tx = new JDBCTransactionContext();
		String strSerialNO;
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			strSerialNO=patImageDAO.selctMaxSerialNo(strCRNo, userVO);

		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return strSerialNO;
	}

}

