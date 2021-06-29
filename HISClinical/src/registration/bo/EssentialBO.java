package registration.bo;

//last updated on july 06-07-06>>>priya

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisPatientNotReferredException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalClinicalDAO;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.tools.Tree;
import hisglobal.utility.Entry;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;
import hisglobal.vo.YellowSlipMonitoringVO;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import registration.RegistrationConfig;
import registration.dao.DailyPatientDAO;
import registration.dao.EpisodeCloseDAO;
import registration.dao.EpisodeCloseDAOi;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeDiagnosisDAO;
import registration.dao.EpisodeDiagnosisDAOi;
import registration.dao.EssentialDAO;
import registration.dao.PatientBroughtByDtlDAO;
import registration.dao.PoliceVerificationDAO;
import registration.dao.PoliceVerificationDAOi;
import registration.dao.PrimaryCategoryChangeDAO;
import registration.dao.RoomMasterDAO;
import registration.dao.YellowSlipEntryDAO;
import registration.dao.YellowSlipEntryDAOi;
import registration.dao.YellowSlipMonitoringDAO;
import registration.dao.YellowSlipMonitoringDAOi;
import registration.master.dao.DepartmentMasterDAO;
import registration.master.dao.EpisodeTriageDetailDAO;
import registration.master.dao.LocationMasterDAO;
import registration.master.dao.UnitConultantDAO;
import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;

import mrd.transaction.dao.*;
//import mrd.transaction.delegate.*;
import mrd.MrdConfig;
import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;

/**
 * @author vinita1
 * 
 */
public class EssentialBO implements EssentialBOi {

	public Map getNewPatientRegEssentialForUnkonwn(UserVO _userVO) {
		Map essentialMap = new HashMap();
		//List lstPrimaryCat = null;
		//List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		//List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		//List lstRefHospital = null;
		List lstNationality = null;
		//List lstAllDepartment = null;
		//List lstUnit = null;
		//List occupationDetail = null;
		//String amount = null;
		List lstDistrict = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			/*
			 * lstAllDepartment=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);
			 */

			/*
			 * lstPrimaryCat=objEssentialDAO.getPrimaryCat(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			 */

			/*
			 * lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);
			 */

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
					lstReligion);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					lstLocation);

			lstState = objEssentialDAO.getStateBasedOnCountry(
					RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,
					_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
					lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
					lstCountry);

			/*
			 * lstRefHospital=objEssentialDAO.getRefHospital(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
			 * lstRefHospital);
			 */

			// lstVerificationDoc=objEssentialDAO.getVerificationDocuments();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
			// lstVerificationDoc);
			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
					lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			//System.out.println("lstAreaCategory" + lstAreaCategory);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					lstAreaCategory);

			lstAgeType = objEssentialDAO.getAgeType();
			//System.out.println("lstAgeType" + lstAgeType);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
					lstAgeType);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);

			/*
			 * amount=
			 * objEssentialDAO.getBillAmountBasedOnCategory(RegistrationConfig.DEFAULT_PRIMARY_CATEGORY,_userVO);
			 * essentialMap.put(RegistrationConfig.AMOUNT_COLLECTED, amount);
			 */

			/*if (Config.CLIENT.equals(Config.CLIENT_PGIMER)) {
				occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
				essentialMap.put(
						RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,
						occupationDetail);

			}*/

			lstDistrict = objEssentialDAO.getDistrictList(_userVO,
					RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,
							lstDistrict);

		}

		catch (HisRecordNotFoundException e) {
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
			//System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getNewPatientRegEssential(UserVO _userVO) 
	{
		Map essentialMap = new HashMap();
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
		List lstVerificationDoc=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			/*
			 * lstAllDepartment=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);
			 */
			lstDepartment = objEssentialDAO.getDepartment(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPARTMENT,lstDepartment);
			
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			/*
			 * lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);
			 */

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);
			
			lstEducation = objEssentialDAO.getEducation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_EDUCATION,lstEducation);

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
			
			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			/*String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(_userVO,new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);*/
			
			//lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			lstReferDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			//lstDepartment = objEssentialDAO.getDeptUnit(_userVO,RegistrationConfig.UNIT_TYPE_GENERAL,Config.MODULE_ID_REGISTRATION);
			//essentialMap.put(Config.ESSENTIALBO_OPTION_DEPARTMENT,lstDepartment);

			lstDistrict = objEssentialDAO.getDistrictList(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					relationList);
			
			occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
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

	public Map getPatientMRDRegEssential(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List occupationDetail = null;
		List distrciList = null;
		List relationList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			// lstPrimaryCat=objEssentialDAO.getPrimaryCat(_userVO);
			// /
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			// lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
					lstReligion);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					lstLocation);

			lstState = objEssentialDAO.getStateBasedOnCountry(
					RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,
					_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
					lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
					lstCountry);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			// lstVerificationDoc=objEssentialDAO.getVerificationDocuments();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
			// lstVerificationDoc);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
					lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					lstAreaCategory);

			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
					lstAgeType);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);

			if (Config.CLIENT.equals(Config.CLIENT_PGIMER)) {
				occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
				essentialMap.put(
						RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,
						occupationDetail);
			}

			distrciList = objEssentialDAO.getDistrictList(_userVO,
					RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,
							distrciList);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					relationList);

		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}

		catch (HisApplicationExecutionException e) {
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

	public Map getEmgNewPatientRegEssential(UserVO _userVO) 
	{
		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstUnit = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNationality = null;
		List lstAllDepartment = null;
		List occupationDetail = null;
		List listRelation = null;
		List lstDistrict = null;
		List lstReferDepartment = null;
		List lstDisaster = null;
		List lstCaste = null;
		List lstVehicle = null;
		List lstVerificationDoc = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try 
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);//,Config.MODULE_ID_EMERGENCY);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);

			// lstUnit=objEssentialDAO.getUnitsWithCasuality(_userVO);
			/*lstUnit = objEssentialDAO.getUnitsByType(_userVO,
					RegistrationConfig.UNIT_TYPE_CASUALITY);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY,lstUnit);*/
			
			lstUnit = objEssentialDAO.getDeptUnit(_userVO,RegistrationConfig.UNIT_TYPE_CASUALITY,Config.MODULE_ID_EMERGENCY);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY,lstUnit);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,lstLocation);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS, lstVerificationDoc);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);

			//lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			lstReferDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			listRelation = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,listRelation);

			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);

			lstDistrict = objEssentialDAO.getDistrictList(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);
			
			//lstDisaster = objEssentialDAO.getDisasterList(_userVO);
			//essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ACTIVE_DISASTER_LIST,lstDisaster);
			
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);
			
			lstVehicle = objEssentialDAO.getVehicleList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VEHICLE,lstVehicle);
			
			occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
			
		} 
		catch (HisRecordNotFoundException e) 
		{
			tx.rollback();
			e.getEssentialMap();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} 
		catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) 
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) 
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

	public Map getAllEmergencyOldPatientVisitEssentials(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstRegCategory = null;
		List lstDepartment = null;
		List lstUnit = null;
		List lstAllDepartment = null;
		List lstRefHospital = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			// lstAllDepartment=objEssentialDAO.getAllDepartment(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);

			// lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);

			// lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);

			// lstUnit=objEssentialDAO.getUnitsWithCasuality(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT_WITH_CASUALITY,
			// lstUnit);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			// lstDepartment=objEssentialDAO.getDepartmentWithCasuality(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_WITH_CASUALITY,
			// lstDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getReferDtlEssential(UserVO _userVO) {
		List lstDepartment = null;
		List lstRefHospital = null;
		List lstReferDepartment = null;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			/*
			 * lstDepartment=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,
			 * lstDepartment);
			 */
			
			/*lstDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,
					lstDepartment);*/
			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);
			
			//lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			lstReferDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,
					lstReferDepartment);
			
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

	public List getSecondaryCategory(UserVO _userVO) {
		List lstSecondaryCat = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			// System.out.println("_PrimaryCatCode"+_PrimaryCatCode);
			lstSecondaryCat = objEssentialDAO.getSecondaryCat(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException("Record Not found");
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstSecondaryCat;
	}

	public Map getNewDeptVisitEssential(String _crNo, UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNewDeptVisitDepartment = null;
		List lstNationality = null;
		String amount = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			// lstPrimaryCat=objEssentialDAO.getPrimaryCat(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			/*
			 * lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);
			 * 
			 * lstMaritalStatus=objEssentialDAO.getMaritalStatus(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
			 * lstMaritalStatus);
			 * 
			 * lstGender=objEssentialDAO.getGender(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
			 * lstGender);
			 * 
			 * lstReligion=objEssentialDAO.getReligion(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
			 * lstReligion);
			 * 
			 * lstDepartment=objEssentialDAO.getDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT,
			 * lstDepartment);
			 * 
			 * lstLocation=objEssentialDAO.getLocation(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
			 * lstLocation);
			 * 
			 * lstState=objEssentialDAO.getState(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
			 * lstState);
			 * 
			 * lstCountry=objEssentialDAO.getCountry(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
			 * lstCountry);
			 */
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			// lstVerificationDoc=objEssentialDAO.getVerificationDocuments();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
			// lstVerificationDoc);

			/*
			 * lstNationality=objEssentialDAO.getNationality(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
			 * lstNationality);
			 * 
			 * lstAreaCategory=objEssentialDAO.getAreaCategory();
			 * System.out.println("lstAreaCategory"+lstAreaCategory);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
			 * lstAreaCategory);
			 * 
			 * lstAgeType=objEssentialDAO.getAgeType();
			 * System.out.println("lstAgeType"+lstAgeType);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
			 * lstAgeType);
			 */

			//if (Config.CLIENT.equals(Config.CLIENT_PGIMER)) {
				//List lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			List lstReferDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			//}
			
			//lstNewDeptVisitDepartment = objEssentialDAO.getNewDeptVisitDepartment(_crNo, _userVO);
			lstNewDeptVisitDepartment = objEssentialDAO.getNewDeptToVisitDepartment(_crNo, _userVO);				
			ListIterator itr = lstNewDeptVisitDepartment.listIterator();			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}

		catch (HisApplicationExecutionException e) {
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


	public Map getNewSplDeptVisitEssential(String _crNo, UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstNewDeptVisitDepartment = null;
		List lstNationality = null;
		String amount = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

		
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			List lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			lstNewDeptVisitDepartment = objEssentialDAO.getNewVisitUnitsWithSpeciality(_userVO,_crNo);				
			ListIterator itr = lstNewDeptVisitDepartment.listIterator();			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}

		catch (HisApplicationExecutionException e) {
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

	public Map getNewDeptVisitRoomWiseEssential(String _crNo, UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstRefHospital = null;
		List lstNewDeptVisitDepartment = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			
			//List lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			List lstReferDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);
			
			
			/*lstNewDeptVisitDepartment = objEssentialDAO.getNewDeptVisitDepartment(_crNo, _userVO);					
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NEW_DEPT_VISIT_DEPARTMENT,lstNewDeptVisitDepartment);
*/
			//Get the list of departments,units and room
			Map deptUnitRoomMap=objEssentialDAO.getNewDeptDeptUnitRoom(_crNo,_userVO);
			essentialMap.putAll(deptUnitRoomMap);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}

		catch (HisApplicationExecutionException e) {
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
	
	
	public Map getOldDeptVisitEssential(String _crNo, UserVO _userVO) {
		Map essentialMap = new HashMap();

	//	List lstPrimaryCat = null;
		List lstRegCategory = null;
	//	List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
	//	List lstGender = null;
	//	List lstReligion = null;
	//	List lstDepartment = null;
	//	List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		// List lstVerificationDoc=null;
		List lstNationality = null;

		List lstOldDeptVisitDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			// lstPrimaryCat=objEssentialDAO.getPrimaryCat(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstRegCategory = objEssentialDAO.getRegCategory(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,
					lstRegCategory);

			// lstMaritalStatus=objEssentialDAO.getMaritalStatus();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
			// lstMaritalStatus);

			// lstGender=objEssentialDAO.getGender();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
			// lstGender);

			// lstReligion=objEssentialDAO.getReligion();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
			// lstReligion);

			// lstDepartment=objEssentialDAO.getDepartment(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT,
			// lstDepartment);

			// lstLocation=objEssentialDAO.getLocation();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
			// lstLocation);

			lstState = objEssentialDAO.getState(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
					lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
					lstCountry);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			// lstVerificationDoc=objEssentialDAO.getVerificationDocuments();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
			// lstVerificationDoc);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
					lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					lstAreaCategory);

			lstAgeType = objEssentialDAO.getAgeType();
			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
					lstAgeType);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			lstOldDeptVisitDepartment = objEssentialDAO
					.getOldDeptVisitDepartment(_crNo, _userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_OLD_DEPT_VISIT_DEPARTMENT,
							lstOldDeptVisitDepartment);

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

	public Map getChangePrimaryCategoryEssential(String crNo, UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstVerificationDocument = null;
		PrimaryCategoryChangeVO[] primaryCatChangeVO = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			PrimaryCategoryChangeDAO objPrimaryCatChangeDAO = new PrimaryCategoryChangeDAO(tx);

			lstPrimaryCat = objEssentialDAO.getPrimaryCatWithExpiryDays(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_WITH_EXPIRY_DAYS,lstPrimaryCat);

			/*lstVerificationDocument = objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDocument);
*/
			primaryCatChangeVO = objPrimaryCatChangeDAO.getChangePrimaryCategoryDetail(crNo, _userVO);
			essentialMap.put(RegistrationConfig.ARRAY_PRIMARY_CATEGORY_CHANGE_VOS,primaryCatChangeVO);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			//e.printStackTrace();
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

	public Map getChangeSecondaryCategoryEssential(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstSecondaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			lstSecondaryCat = objEssentialDAO
					.getSecondaryCatWithExpiryDays(_userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY_WITH_EXPIRY_DAYS,
							lstSecondaryCat);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			//e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}

		catch (HisDataAccessException e) {
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

	public Map getFileNoChangeInitials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstSecondaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			/*
			 * lstSecondaryCat=objEssentialDAO.getSecondaryCat();
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY,
			 * lstSecondaryCat);
			 */
			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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
		return essentialMap;
	}

	public Map getRenwalOfRegistrationInitials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstSecondaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			/*
			 * lstSecondaryCat=objEssentialDAO.getSecondaryCat();
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY,
			 * lstSecondaryCat);
			 */
			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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
		return essentialMap;
	}

	public Map getNewSpecialClinicInitials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstSecondaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			/*
			 * lstSecondaryCat=objEssentialDAO.getSecondaryCat();
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY,
			 * lstSecondaryCat);
			 */
			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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
		return essentialMap;
	}

	public Map getPatientDtlModificationEssential(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstRegCategory = null;
		List lstMaritalStatus = null;
		List lstAreaCategory = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		List lstDepartment = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstRefHospital = null;
		List lstVerificationDoc = null;
		List lstNationality = null;
		List lstAllDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAllDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,
					lstAllDepartment);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);

			lstRegCategory = objEssentialDAO.getRegCategory(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,
					lstRegCategory);

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
					lstReligion);

			lstDepartment = objEssentialDAO.getDepartment(_userVO,
					RegistrationConfig.UNIT_TYPE_GENERAL);
			essentialMap.put(Config.ESSENTIALBO_OPTION_DEPARTMENT,
					lstDepartment);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					lstLocation);

			lstState = objEssentialDAO.getState(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
					lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
					lstCountry);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			lstVerificationDoc = objEssentialDAO
					.getVerificationDocuments(_userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
							lstVerificationDoc);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
					lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					lstAreaCategory);

			lstAgeType = objEssentialDAO.getAgeType();
			
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
					lstAgeType);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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
		return essentialMap;
	}

	public Map getAddressDtlModificationEssential(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstAddressType = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstVerificationDoc = null;
		List lstDistrict = null;
		List lstAreaCat = null;
		List relationList = null;
		String _stateCode = RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAddressType = objEssentialDAO.getAddressType(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE,
					lstAddressType);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					lstLocation);

			lstState = objEssentialDAO.getState(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
					lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
					lstCountry);

			lstVerificationDoc = objEssentialDAO
					.getVerificationDocuments(_userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
							lstVerificationDoc);

			lstDistrict = objEssentialDAO.getDistrictList(_userVO, _stateCode);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_DISTRICT_ON_DEFAULT_STATE,
							lstDistrict);

			lstAreaCat = objEssentialDAO.getAreaCategory();
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					lstAreaCat);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					relationList);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
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
		return essentialMap;
	}

	public Map getRefHospEssential(UserVO _userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List lstAllDepartment = null;
		List lstRefHospEssential = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstRefHospEssential = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospEssential);

			lstAllDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,
					lstAllDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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
		return essentialMap;

	}

	public List getOptionsCmoRegisterEssential(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List cmoRegisterEssential = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			cmoRegisterEssential = objEssentialDAO
					.getOptionsCmoRegisterEssential(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return cmoRegisterEssential;
	}

	// * Getting Episode Death Essential
	public Map getEpisodeDeathEssential(UserVO _userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deathEssentialList = null;
		List deathMannerList = null;
		Map deathEssentialMap = new HashMap();

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			deathEssentialList = objEssentialDAO
					.getEpisodeDeathEssential(_userVO);
			deathEssentialMap.put(RegistrationConfig.ESSENTIALBO_RELATIVE_CODE,
					deathEssentialList);
			deathMannerList = objEssentialDAO.getDeathMannerEssential(_userVO);
			deathEssentialMap.put(RegistrationConfig.ESSENTIAL_BO_DEATH_MANNER,
					deathMannerList);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		}

		catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisException();
		} finally {
			tx.close();
		}
		return deathEssentialMap;
	}

	public List getDeptWithCasuality(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deptWithCasualityList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			deptWithCasualityList = objEssentialDAO
					.getDepartmentWithCasuality(_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return deptWithCasualityList;
	}

	/*
	 * public Map getOldDeptVisitEssential(String _crNo, UserVO _userVO) { //
	 */
	public Tree getDiagnosis(UserVO _userVO) {
		System.out.println("inside getDiagnosis of BO");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Tree diagnosisTree = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			diagnosisTree = objEssentialDAO.getDiagnosisData(_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}

		return diagnosisTree;
	}

	public List getDepartmentEssential(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deptEssential = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			deptEssential = objEssentialDAO.getDepartment(_userVO,
					RegistrationConfig.UNIT_TYPE_GENERAL);

		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return deptEssential;
	}

	public List getWardBasedOnDept(String _deptCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List WardList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			WardList = objEssentialDAO.getWardBasedOnDepartment(_deptCode,
					_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}

		catch (HisDataAccessException e) {
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
		return WardList;
	}

	public List getOptionsDoctorDeskEssential(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List doctorDeskEssential = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			doctorDeskEssential = objEssentialDAO.getDoctorDeskEssential();
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return doctorDeskEssential;
	}

	public List getOptionsMlcDtl(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMlcDtlOptions = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstMlcDtlOptions = objEssentialDAO.getOptionsMlcDtl(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return lstMlcDtlOptions;
	}

	public List getOptionCrNoMlcNO(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMlcCrNO = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstMlcCrNO = objEssentialDAO.getOptionCrNoMlcNO(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return lstMlcCrNO;
	}

	public List getSearchOptions(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstSearchOptions = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstSearchOptions = objEssentialDAO.getSearchOptions(_userVO);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}

		catch (HisDataAccessException e) {
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
		return lstSearchOptions;
	}

	public Map getUnitChangeEssential(String deptUnitCode, UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstToDeptUnit = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstToDeptUnit = objEssentialDAO.getDeptUnit(deptUnitCode, _userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_UNIT_CHANGE_TO_DEPTUNIT,
							lstToDeptUnit);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public List getRegCategoryEssential(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List regCategoryEssential = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			regCategoryEssential = objEssentialDAO
					.getRegCategoryEssential(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return regCategoryEssential;

	}

	public List getVerificationDocuments(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List verificationDocuments = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
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

	public Map getReferDeptVisitEssential(String crNo, UserVO _userVO) {
		Map essentialMap = new HashMap();
		/*
		 * List refToDept=null; List refFromDept=null; List allDept=null;
		 */
		List lstPrimaryCat = null;
		List lstRefHospital = null;
		List lstDepartment = null;
		List lstAllDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAllDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,
					lstAllDepartment);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);

			/*
			 * refToDept=objEssentialDAO.getRefToDepartment(crNo, _userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERRED_TO_DEPT,refToDept);
			 * 
			 * refFromDept=objEssentialDAO.getRefFromDepartment(crNo, _userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERRED_FROM_DEPT,refFromDept);
			 * 
			 * allDept=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERRED_ALL_DEPT,
			 * allDept);
			 */
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			lstDepartment = objEssentialDAO.getDepartment(_userVO,
					RegistrationConfig.UNIT_TYPE_GENERAL);
			essentialMap.put(Config.ESSENTIALBO_OPTION_DEPARTMENT,
					lstDepartment);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public List getRefToDepartment(String crNo, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List refToDept = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			refToDept = objEssentialDAO.getRefToDepartment(crNo, _userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return refToDept;
	}

	public List getUnitBasedOnDept(String _deptCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			unitList = objEssentialDAO.getUnitBasedOnDepartment(_deptCode,_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return unitList;
	}
	
/*	public List getUnitBasedOnSpeciality(String speciality, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			if(speciality.equals("%"))
				unitList=essentialDAO.getDeptUnitList(_userVO);
			else
				unitList = objEssentialDAO.getUnitBasedOnSpeciality(speciality,	_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return unitList;
	}*/

	public RoomMasterVO[] getRoomSequenceDtl(String _deptCode,
			String _unitCode, UserVO _userVO) {
		System.out.println("inside getRoomSequenceDtl()");

		JDBCTransactionContext tx = new JDBCTransactionContext();
		RoomMasterVO[] roomMasterVO;
		try {
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of EpisodeDAO");
			RoomMasterDAO roomDAO = new RoomMasterDAO(tx);
			System.out.println("creating object of RoomMasterDAO");
			roomMasterVO = roomDAO.getRoomSequence(_deptCode, _unitCode,
					_userVO);

		}// end of try
		catch (HisRecordNotFoundException e) {
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		} catch (HisPatientNotReferredException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisPatientNotReferredException(e.getMessage());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
		return roomMasterVO;
	}

	public void updateSequenceDtl(RoomMasterVO[] _rmMasterVO, UserVO _userVO) {
		System.out.println("inside updateSequenceDtl()");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			System.out.println("Begining transaction");
			tx.begin();
			System.out.println("creating object of EpisodeDAO");
			RoomMasterDAO roomDAO = new RoomMasterDAO(tx);
			System.out.println("creating object of RoomMasterDAO");
			for (int i = 0; i < _rmMasterVO.length; i++) {
				roomDAO.updateRoomSequence(_rmMasterVO[i], _userVO);
			}
		}// end of try
		catch (HisRecordNotFoundException e) {
			System.out.println("inside HisRecordNotFoundException of ");
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		} catch (HisPatientNotReferredException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisPatientNotReferredException(e.getMessage());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}

	}

	/*
	 * public RosterMasterVO[] getUnitSequenceDtl(String _deptCode, String
	 * _unitCode, UserVO _userVO) {
	 * 
	 * System.out.println("inside getUnitSequenceDtl()"); JDBCTransactionContext
	 * tx=new JDBCTransactionContext(); RosterMasterVO[] rosterMasterVO ; try{
	 * System.out.println("Begining transaction"); tx.begin();
	 * System.out.println("creating object of EpisodeDAO"); RosterMasterDAO
	 * rosterMasterDAO= new RosterMasterDAO(tx); System.out.println("creating
	 * object of RoomMasterDAO");
	 * rosterMasterVO=rosterMasterDAO.getUnitSequence(_deptCode,_unitCode,_userVO);
	 * 
	 * 
	 * }//end of try catch (HisRecordNotFoundException e){
	 * System.out.println("inside HisRecordNotFoundException of ");
	 * tx.rollback(); e.printStackTrace(); throw new
	 * HisRecordNotFoundException(); } catch(HisApplicationExecutionException
	 * e){ tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(e.getMessage()); }
	 * catch(HisDataAccessException e){ e.printStackTrace(); tx.rollback();
	 * throw new HisDataAccessException(e.getMessage()); }
	 * catch(HisPatientNotReferredException e){ e.printStackTrace();
	 * tx.rollback(); throw new HisPatientNotReferredException(e.getMessage()); }
	 * catch(Exception e){ tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } finally{ tx.close(); }
	 * return rosterMasterVO; }
	 * 
	 * public void updateUnitSequenceDtl(RosterMasterVO[] _rstMasterVO, UserVO
	 * _userVO) { System.out.println("inside updateSequenceDtl()");
	 * JDBCTransactionContext tx=new JDBCTransactionContext(); try{
	 * System.out.println("Begining transaction"); tx.begin();
	 * System.out.println("creating object of EpisodeDAO"); RosterMasterDAO
	 * rosterDAO= new RosterMasterDAO(tx); System.out.println("creating object
	 * of RoomMasterDAO"); for(int i=0;i<_rstMasterVO.length;i++){
	 * rosterDAO.updateRosterSequence(_rstMasterVO[i],_userVO); } }//end of try
	 * catch (HisRecordNotFoundException e){ System.out.println("inside
	 * HisRecordNotFoundException of "); tx.rollback(); e.printStackTrace();
	 * throw new HisRecordNotFoundException(); }
	 * catch(HisApplicationExecutionException e){ tx.rollback();
	 * e.printStackTrace(); throw new
	 * HisApplicationExecutionException(e.getMessage()); }
	 * catch(HisDataAccessException e){ e.printStackTrace(); tx.rollback();
	 * throw new HisDataAccessException(e.getMessage()); }
	 * catch(HisPatientNotReferredException e){ e.printStackTrace();
	 * tx.rollback(); throw new HisPatientNotReferredException(e.getMessage()); }
	 * catch(Exception e){ tx.rollback(); e.printStackTrace(); throw new
	 * HisApplicationExecutionException(e.getMessage()); } finally{ tx.close(); }
	 * 
	 *  }
	 */

	public List getRefFromDepartment(String crNo, UserVO _userVO) {
		return null;
	}

	public List getDeptBasedOnWeekday(String _WeekDay, UserVO _userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deptList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			deptList = objEssentialDAO.getDeptBasedOnWeekday(_WeekDay, _userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return deptList;
	}

	public List getWeekdayEssential(UserVO _userVO) {
		System.out.println("getWeekdayEssential");
		List lstWeekDay = new ArrayList();
		List alList = new ArrayList();
		alList.add("Sunday");
		alList.add("1");
		alList.add("Monday");
		alList.add("2");
		alList.add("Tuesday");
		alList.add("3");
		alList.add("WednesDay");
		alList.add("4");
		alList.add("Thursday");
		alList.add("5");
		alList.add("Friday");
		alList.add("6");
		alList.add("Saturday");
		alList.add("7");
		for (int i = 0; i < alList.size();) {
			Entry objEntry = new Entry((String) alList.get(i++),
					(String) alList.get(i++));
			lstWeekDay.add(objEntry);
		}

		return lstWeekDay;
	}

	public List getDiagnosisTypeEssential(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List diagnosisType = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			diagnosisType = objEssentialDAO.getDiagnosisTypeEssential(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return diagnosisType;
	}

	public List getAllDepartment(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List allDeptLst = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			allDeptLst = objEssentialDAO.getAllDepartment(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return allDeptLst;
	}

	public List getShiftEssential(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listShift = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			listShift = objEssentialDAO.getShiftEssential(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
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
		return listShift;
	}

	public List getUnitType(UserVO _userVO) {

		
		List lst = new ArrayList();
		List alList = new ArrayList();
		alList.add("General");
		alList.add(RegistrationConfig.UNIT_TYPE_GENERAL);
		alList.add("Specaility");
		alList.add(RegistrationConfig.UNIT_TYPE_SPECIALITY);
		alList.add("Casuality");
		alList.add(RegistrationConfig.UNIT_TYPE_CASUALITY);

		for (int i = 0; i < alList.size();) {
			Entry objEntry = new Entry((String) alList.get(i++),
					(String) alList.get(i++));
			lst.add(objEntry);
		}
		return lst;

	}

	public Map getUnitEssentials(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstEmpConsultant = new ArrayList();
		String designationMappingProcessId=RegistrationConfig.UNIT_CONSULTANT_DESIGNATION_MAPPING_PROCESSID;
		Map mp = new HashMap();
		try {
			tx.begin();
			UnitConultantDAO unitConsultantMasterDAO=new UnitConultantDAO(tx);
			DepartmentMasterDAO   deptmstDAO= new  DepartmentMasterDAO(tx);
			EssentialDAO essentialDAO= new EssentialDAO(tx);
			List lstDpt = essentialDAO.getAllDepartment(_userVO);
			mp.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, lstDpt);
			List lstUnitType = getUnitType(_userVO);
			mp.put(RegistrationConfig.ESSENTIAL_BO_OPTION_UNITTYPE,lstUnitType);
			List locationList = deptmstDAO.getLocation(_userVO);
			mp.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,locationList);
			lstEmpConsultant= unitConsultantMasterDAO.getEmployeeAsConsultant(designationMappingProcessId,_userVO);
			mp.put(RegistrationConfig.EMPLOYEE_AS_CONSULTANT, lstEmpConsultant);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(),mp);
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
		return mp;
	}

	public List getAllRooms(UserVO _userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		List allRoomLst = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			allRoomLst = objEssentialDAO.getAllRooms(_userVO);
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
		return allRoomLst;
	}

	public Map getRoomsToUnitEssentials(UserVO _userVO) {
		Map mp = new HashMap();

		List lstDept = getAllDepartment(_userVO);
		mp.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, lstDept);

		List lstRoom = getAllRooms(_userVO);
		mp.put(RegistrationConfig.ALL_ROOMS, lstRoom);

		return mp;
	}

	public Map getCollectionSecondaryCategory(Collection primaryCatCode,
			UserVO _userVO) {
		List lstSecondaryCat = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String primaryCategory = null;
		Map secondaryCategoryMap = new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			System.out.println("primaryCatCode:::" + primaryCatCode);
			Iterator itr = primaryCatCode.iterator();
			while (itr.hasNext()) {
				primaryCategory = (String) itr.next();
				lstSecondaryCat = objEssentialDAO.getSecondaryCat(_userVO);
				secondaryCategoryMap
						.put(
								RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY
										+ "[" + primaryCategory + "]",
								lstSecondaryCat);
				// secondaryCategoryMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SECONDARY_CATEGORY+"_"+primaryCategory+"_",lstSecondaryCat);
			}
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException("Record Not found");
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return secondaryCategoryMap;
	}

	public List getEmployeesIsConsultants(String designationMappingProcessId,
			UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstEmpConsultant = null;
		try {
			tx.begin();
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			lstEmpConsultant = essentialDAO.getEmployeeAsConsultant(
					designationMappingProcessId, _userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstEmpConsultant;
	}

	public List getStateBasedOnCountry(String _countryCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstState = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstState = objEssentialDAO.getStateBasedOnCountry(_countryCode,
					_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			tx.close();
		}
		return lstState;
	}

	public Map getAgeWiseReportEssentials(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstGender = null;
		List lstDepartment = null;
		List lstAllPrimaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAllPrimaryCat = objEssentialDAO.getPatientCategoryList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY,
					lstAllPrimaryCat);
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);
			lstDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT,
					lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getEmergencyRegPatReportEssential(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		// List lstRegCat=null;
		// List lstDepartment=null;
		List lstregCat = null;
		List lstAllPrimaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAllPrimaryCat = objEssentialDAO.getPatientCategoryList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY,
					lstAllPrimaryCat);
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);
			// lstRegCat=objEssentialDAO.getRegCategory();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,
			// lstRegCat);
			// lstDepartment=objEssentialDAO.getAllDepartment(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT,
			// lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
			lstregCat = objEssentialDAO.getRegistrationCategory();
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,
					lstregCat);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getGroupWiseCashCollReportEssential(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getMlcPatientListReportsEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		// List lstPrimaryCat=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			// lstPrimaryCat=objEssentialDAO.getPrimaryCat();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getBroughtDeadPatReportsEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		// List lstPrimaryCat=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			// lstPrimaryCat=objEssentialDAO.getPrimaryCat();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getPinCodeWiseReportEssential(UserVO _userVO) {

		Map essentialMap = new HashMap();
		// List lstPrimaryCat=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			// lstPrimaryCat=objEssentialDAO.getPrimaryCat();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getDepartmentWiseRegPatReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT,
					lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getDeptWisePatCatReportsEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllDept(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,
					lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getSpecialityWiseOutdoorPatientReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
		List lstUnit = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = essentialDAO.getAllDeptHavingUnits(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,lstDepartment);
			lstUnit = objEssentialDAO.getUnitBasedOnSpeciality(	_userVO);
			essentialMap.put(MrdConfig.UNITS_BASED_ON_SPECIALITY,lstUnit);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getSpecialityGenderWiseOPDPatientReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = essentialDAO.getAllDeptHavingUnits(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,lstDepartment);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	
	
	public Map getSpecialityUnitWiseSplClinicOPDReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
		List lstUnit = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = essentialDAO.getAllDeptHavingUnits(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,lstDepartment);
			lstUnit = objEssentialDAO.getUnitBasedOnSpeciality(	_userVO);
			essentialMap.put(MrdConfig.UNITS_BASED_ON_SPECIALITY,lstUnit);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}


	public Map getGenderWiseOutdoorPatientReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPatCat = null;
		List lstGender = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstPatCat = objEssentialDAO.getPatientCategoryList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY,lstPatCat);
			lstGender=essentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

}

	public Map getSpecialityWiseOperationReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
		List lstUnit = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllDept(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,
					lstDepartment);
			lstUnit = objEssentialDAO.getUnitBasedOnSpeciality(	_userVO);
			essentialMap.put(MrdConfig.UNITS_BASED_ON_SPECIALITY,lstUnit);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}
	
	public Map getSpecialityWiseInvestigationReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
		List lstUnit = null;
		List lab=null;
		List labTest=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);


			lstDepartment = objEssentialDAO.getAllDept(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,
					lstDepartment);
			lstUnit = objEssentialDAO.getUnitBasedOnSpeciality(	_userVO);
			essentialMap.put(MrdConfig.UNITS_BASED_ON_SPECIALITY,lstUnit);
			
			lab = essentialDAO.getExternalLabList(	_userVO);
			essentialMap.put(MrdConfig.EXT_LAB_LIST,lab);
			
			labTest = essentialDAO.getExternalLabTestList(	_userVO);
			essentialMap.put(MrdConfig.EXt_LAB_TEST_LIST,labTest);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}
	

	
	public Map getSpecialityUnitWiseOPDPatientReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllDept(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,
					lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getSpecialityGenderWiseOpdPatientReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllDept(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,
					lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getDepartmentWiseTotalPatReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT,
					lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getRoomWiseTotalPatReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstRoom = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstRoom = objEssentialDAO.getAllRooms(_userVO);
			essentialMap.put(RegistrationConfig.ALL_ROOMS, lstRoom);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getTotalPatStatReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getUserWiseCashCollReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		// List lstPrimaryCat=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			// lstPrimaryCat=objEssentialDAO.getPrimaryCat();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getUserWisePatListReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		// List lstPrimaryCat=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			// lstPrimaryCat=objEssentialDAO.getPrimaryCat();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getUserWiseRegReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		// List lstPrimaryCat=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			// lstPrimaryCat=objEssentialDAO.getPrimaryCat();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getEmergencyReportEssential(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstregCat = null;
		List hospitalList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
			lstregCat = objEssentialDAO.getRegistrationCategory();
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,
					lstregCat);
			hospitalList=objEssentialDAO.getAllHospitalsSeatIDWise(_userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_SEATID_WISE_LIST, hospitalList);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());

		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	public Map getCategoryWiseReportEssential(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		List lstGender = null;
		List lstUser = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);
			lstUser = objEssentialDAO.getUser();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_USER,
					lstUser);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
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
	public String checkBplDetails(String _bplcardNo,UserVO _userVO) {

		String bplCount ="";

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			bplCount = objEssentialDAO.checkBplDetails(_bplcardNo, _userVO);
			return bplCount;
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
	public WebRowSet getBplSearchDetails(BPLDetailsVO bplVO,UserVO _userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			return objEssentialDAO.getBplSearchDetails(bplVO, _userVO);
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

	public String getBillAmountAndIdRequiredBasedOnCategory(
			String _CategoryCodeCode, UserVO _userVO) {

		String amountAndIdRequired = "";

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			amountAndIdRequired = objEssentialDAO
					.getBillAmountAndIdRequiredBasedOnCategory(
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map getSpecialUnitDetail(UserVO _userVO, String crNo,
			String patPriCatCode) {
		Map map = new HashMap();
		List units = new ArrayList();
		List lstRefHospital=new ArrayList();
		List lstReferDepartment=null;
		EpisodeVO[] episodeVO = null;
		String billAmount = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			units = objEssentialDAO.getNewVisitUnitsWithSpeciality(_userVO,
					crNo);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			billAmount = objEssentialDAO.getBillAmountBasedOnCategory(
					patPriCatCode, _userVO);
			map.put("specislClinic", units);
			map.put(RegistrationConfig.AMOUNT_COLLECTED, billAmount);
			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			map.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,	lstRefHospital);
			
			// = objEssentialDAO.getReferDepartmentList(_userVO);
			lstReferDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			map.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,
					lstReferDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return map;
	}

	public Map getEmgVisitUnitDetail(UserVO _userVO, String crNo,
			String patPriCatCode) {
		Map map = new HashMap();
		List units = new ArrayList();
		EpisodeVO[] episodeVO = null;
		String billAmount = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			units = objEssentialDAO.getEmgVisitUnitsWithCasualty(_userVO, crNo);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			// episodeVO=episodeDAO.getDeptWiseFileNoChange(crNo,_userVO);

			/*billAmount = objEssentialDAO.getBillAmountBasedOnCategory(
					patPriCatCode, _userVO);*/
			// List listRegCat=objEssentialDAO.getRegCategory(_userVO);
			// map.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,listRegCat);
			map.put("specislClinic", units);
			// map.put("fileNo",episodeVO[0]);
			//map.put(RegistrationConfig.AMOUNT_COLLECTED, billAmount);

			return map;
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public Map getSpecialClinicNewPatientRegEssential(UserVO _userVO) {
		Map essentialMap = new HashMap();
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
		List lstVerificationDoc=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);
			lstUnit = objEssentialDAO.getDeptUnit(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,Config.MODULE_ID_REGISTRATION);
			essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,lstUnit);

			
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);
			
			lstEducation = objEssentialDAO.getEducation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_EDUCATION,lstEducation);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
					
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
			
			lstState = objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);
	
			lstAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
			
			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
			
			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			lstDistrict = objEssentialDAO.getDistrictList(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,lstDistrict);

			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					relationList);
			
			occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
			
			
			 lstVerificationDoc=objEssentialDAO.getVerificationDocuments(_userVO);
			 essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,lstVerificationDoc);

				
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

	public Map getSpecialClinicNewPatientRegEssentialsAS(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lst = new ArrayList();
		lst.add(new Entry("Select Value", "-1"));

		List lstPrimaryCat = new ArrayList(lst);
		List lstRegCategory = new ArrayList(lst);
		List lstMaritalStatus = new ArrayList(lst);
		List lstAreaCategory = new ArrayList(lst);
		List lstAgeType = new ArrayList(lst);
		List lstGender = new ArrayList(lst);
		List lstReligion = new ArrayList(lst);
		List lstLocation = new ArrayList(lst);
		List lstState = new ArrayList(lst);
		List lstCountry = new ArrayList(lst);
		List lstRefHospital = new ArrayList(lst);
		List lstNationality = new ArrayList(lst);
		List lstAllDepartment = new ArrayList(lst);
		List lstUnit = new ArrayList(lst);
		List districtList = new ArrayList(lst);
		List lstReferDepartment = new ArrayList(lst);
		String amount = null;
		List occupationDetail = new ArrayList(lst);
		;
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,
				lstAllDepartment);
		essentialMap.put(
				RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
				lstPrimaryCat);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,
				lstRegCategory);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
				lstMaritalStatus);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
				lstGender);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
				lstReligion);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
				lstLocation);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE, lstState);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
				lstCountry);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
				lstNationality);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
				lstAreaCategory);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
				lstAgeType);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,
				occupationDetail);
		essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,
				lstUnit);
		essentialMap.put(
				RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,
				districtList);
		essentialMap.put(
				RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,
				lstReferDepartment);

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			/*
			 * lstAllDepartment=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);
			 */

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,
					lstPrimaryCat);

			/*
			 * lstRegCategory=objEssentialDAO.getRegCategory(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);
			 */

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,
					lstMaritalStatus);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,
					lstReligion);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					lstLocation);

			lstState = objEssentialDAO.getStateBasedOnCountry(
					RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,
					_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,
					lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
					lstCountry);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,
					lstRefHospital);

			// lstVerificationDoc=objEssentialDAO.getVerificationDocuments();
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS,
			// lstVerificationDoc);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,
					lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					lstAreaCategory);

			lstAgeType = objEssentialDAO.getAgeType();
		
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,
					lstAgeType);

			if (Config.CLIENT.equals(Config.CLIENT_PGIMER)) {
				occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
				essentialMap.put(
						RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,
						occupationDetail);


			}
			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,
					lstReferDepartment);

			/*
			 * amount=
			 * objEssentialDAO.getBillAmountBasedOnCategory(RegistrationConfig.DEFAULT_PRIMARY_CATEGORY,_userVO);
			 * essentialMap.put(RegistrationConfig.AMOUNT_COLLECTED, amount);
			 */

			/*lstUnit = objEssentialDAO.getUnitsByType(_userVO,
					RegistrationConfig.UNIT_TYPE_SPECIALITY);
			essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,
					lstUnit);*/
			
			lstUnit = objEssentialDAO.getDeptUnit(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY,Config.MODULE_ID_REGISTRATION);
			essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,lstUnit);


			districtList = objEssentialDAO.getDistrictList(_userVO,
					RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,
							districtList);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			// e.setEssentialMap(essentialMap);
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}

		catch (HisApplicationExecutionException e) {
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

	public List getUnitConsultant(UserVO _userVO) {
		List list = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			list = objEssentialDAO.getUnitConsultant(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return list;
	}

	public Map getMlcDtlEssentials(UserVO _userVO) {
		List unitConsultant = null;
		List realationList = null;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			/*
			 * unitConsultant = objEssentialDAO.getUnitConsultant(_userVO);
			 * essentialMap.put(RegistrationConfig.UNIT_CONSULTANT_LIST,
			 * unitConsultant);
			 */
			realationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					realationList);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public String getNextDate() {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			String nextExpiryDate = objEssentialDAO.getNextDate();

			return nextExpiryDate;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public String getNextExpiryDate(String _expiryDate) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			String nextExpiryDate = objEssentialDAO
					.getNextExpiryDate(_expiryDate);

			return nextExpiryDate;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public List getIcdCodeList(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getIcdCodeList(_userVO);

			return list;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public List getDiagnosisTypeList(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getDiagnosisTypeList(_userVO);

			return list;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public List getAllUnitBasedOnSeatID(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getAllUnitBasedOnSeatID(_userVO);

			return list;
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
	}

	public List getEpisodeActionList(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getEpisodeActionList(_userVO);

			return list;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public List getIcdCodes(String _searchIcdCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getIcdCodes(_searchIcdCode, _userVO);

			return list;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public List getDiseaseName(String _searchDisease, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getDiseaseName(_searchDisease, _userVO);

			return list;
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
	}

	public List getOpdUser(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getOpdUser(_userVO);

			return list;
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

	public List getIcdCodeBasedOnDept(String _deptCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getIcdCodeBasedOnDept(_deptCode,
					_userVO);

			return list;
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

	public List getDoctorNameBasedOnUnit(String _unitCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List list = objEssentialDAO.getDoctorNameBasedOnUnit(_unitCode,
					_userVO);

			return list;
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

	public List getState(UserVO userVO) {
		List lstState = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstState = objEssentialDAO.getState(userVO);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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

	public List getSeason(UserVO userVO) {
		List lstSeason = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstSeason = objEssentialDAO.getSeason(userVO);
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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
		return lstSeason;
	}

	public List getShiftForRegistration(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listShift = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			listShift = objEssentialDAO.getShiftForRegistration(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
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
		return listShift;
	}

	public List getAllDeptList(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listDeptt = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			listDeptt = objEssentialDAO.getAllDeptList(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
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
		return listDeptt;
	}

	public String getBillAmountBasedOnDeptGrouping(String _categoryCode,
			String _fromDept, String _toDept,String entryDate, UserVO _userVO) {
		String registrationAllowed = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			registrationAllowed = objEssentialDAO
					.getBillAmountBasedOnDeptGrouping(_categoryCode, _fromDept,
							_toDept,entryDate, _userVO);
		} catch (HisRegistrationTimingExpiredException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRegistrationTimingExpiredException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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
		return registrationAllowed;
	}

	public String getBillAmountBasedOnUnitGrouping(String _categoryCode,
			String _fromUnit, String _toUnit, UserVO _userVO) {
		String billAmount = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			billAmount = objEssentialDAO.getBillAmountBasedOnUnitGrouping(
					_categoryCode, _fromUnit, _toUnit, _userVO);
		} catch (HisRegistrationTimingExpiredException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRegistrationTimingExpiredException(e.getMessage());
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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
		return billAmount;
	}

	// Getting Triage Detail Essentials
	public Map getTriageDetailEssentials(EpisodeTriageDetailVO _episodeTriageVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map map = new HashMap();
		List lstPatStatus = null;
		List lstEmerCases = null;
		EpisodeTriageDetailVO episodeTriageVO = null;
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			lstPatStatus = objEssentialDAO.getPatientStatusList(_userVO);
			map.put(RegistrationConfig.PATIENT_STATUS_LIST, lstPatStatus);

			lstEmerCases = objEssentialDAO.getEmergencyCasesList(_userVO);
			map.put(RegistrationConfig.EMERGENCY_CASE_LIST, lstEmerCases);
			
			EpisodeTriageDetailDAO dao = new EpisodeTriageDetailDAO(tx);
			episodeTriageVO = dao.getDataByCrnoVisit(_episodeTriageVO.getPatCrNo(), _episodeTriageVO.getEpisodeCode(), _episodeTriageVO.getEpisodeVisitNo(), _userVO);
			map.put(RegistrationConfig.PATIENT_EPISODE_TRIAGE_DETAIL_VO, episodeTriageVO);			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return map;
	}

	public List getPatientCategoryList(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstPatCat = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstPatCat = objEssentialDAO.getPatientCategoryList(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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
		return lstPatCat;
	}

	public List getRegCategoryList(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstRegCat = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstRegCat = objEssentialDAO.getRegCategoryList(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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
		return lstRegCat;
	}

	public Map getYellowSlipEssential(EpisodeVO episodeVO,String _unitCode, UserVO _userVO) {

		Map essentialMap = new HashMap();
		String diagnosisTypeCode = "";
		List diagnosisCodeList = new ArrayList();
		List consultantList = new ArrayList();
		List episodeDiagnosisVOList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			diagnosisTypeCode = objEssentialDAO.getUnitDiagnosisTypeCode(
					_unitCode, _userVO);
			essentialMap.put(RegistrationConfig.UNIT_DIAGNOSIS_TYPE_CODE,
					diagnosisTypeCode);

			if (diagnosisTypeCode.equals(OpdConfig.CHOICE_ICD_CODE)) {
				diagnosisCodeList = objEssentialDAO
						.getIcdDiagnosisCodeList(_userVO);
				essentialMap.put(
						RegistrationConfig.ESSENTIALBO_OPTION_ICD_CODE_LIST,
						diagnosisCodeList);
			} else {
				diagnosisCodeList = objEssentialDAO
						.getHospitalDiagnosisCodeList(_userVO);
				essentialMap
						.put(
								RegistrationConfig.ESSENTIALBO_OPTION_HOSPITAL_DIAGNOSOSIS_CODE_LIST,
								diagnosisCodeList);
			}

			consultantList = objEssentialDAO.getConsultantForUnit(_unitCode,
					_userVO);
			essentialMap.put(RegistrationConfig.UNIT_CONSULTANT_LIST,
					consultantList);
			
			episodeDiagnosisVOList=objEssentialDAO.getPreviousDiagnosisForYellowSlip(episodeVO, _userVO);
			essentialMap.put(RegistrationConfig.PREVIOUS_DIAGNOSIS_VO_LIST,
					episodeDiagnosisVOList);
			

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public List getAllUnit(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstUnit = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstUnit = objEssentialDAO.getAllUnit(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
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
		return lstUnit;
	}

	public List getPrimaryCatDetailVOs(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstUnit = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstUnit = objEssentialDAO.getPrimaryCatDetailVOs(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstUnit;
	}

	// Getting MLC Detail Essentials
	public Map getCsultyMlcDetailEssentials(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMedicalCerificateReqAdvBy=new ArrayList();
		List lstUnitConsults = null;
		List lstRelationships = null;
		List lstInjuryTypes = null;
		List lstPatientStatus = null;
		List lstInjuryNature = null;
		List lstInjurySite = null;
		String consultentType= MrdConfig.PROCESS_TYPE_CONSULTANT;
		
		Map map = new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MedicalCertificateRequestDAO medicalCertificateReqDAO=new MedicalCertificateRequestDAO(tx);
			UnitConultantDAO unitConsultantMasterDAO = new UnitConultantDAO(tx);
		OpdEssentialDAO opdEssentialDAO=new OpdEssentialDAO(tx);
			
		//lstMedicalCerificateReqAdvBy = medicalCertificateReqDAO.getMedicalCertificateReqAdvisedBy(deptUnitCode,_userVO,consultentType);
		//essentialMap.put(MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL, lstMedicalCerificateReqAdvBy);

			lstRelationships = objEssentialDAO.getRelationsList(_userVO);
			map.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					lstRelationships);

			lstInjuryTypes = objEssentialDAO.getMlcTypes(_userVO);
			map.put(RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST,
					lstInjuryTypes);
			lstInjuryNature = objEssentialDAO.getInjuryNature(_userVO);
			map.put(RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST, lstInjuryNature);

			lstPatientStatus = objEssentialDAO.getPatientStatusList(_userVO);
			map.put(RegistrationConfig.PATIENT_STATUS_LIST, lstPatientStatus);
			
			lstInjurySite = opdEssentialDAO.getDiagnosisSiteList(_userVO);
			map.put(RegistrationConfig.ESSENTIAL_DISEASE_SIDE_LIST, lstInjurySite);
			lstUnitConsults = unitConsultantMasterDAO.getEmployeeAsConsultant(
					RegistrationConfig.CMO_DESIGNATION_MAPPING_PROCESSID,
					_userVO);
			 lstUnitConsults = objEssentialDAO.getUnitConsultant(_userVO);
			map.put(RegistrationConfig.UNIT_CONSULTANT_LIST, lstUnitConsults);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(),map);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} finally {
			tx.close();
		}
		return map;
	}

	public List getMlcNoBasedOnCrNo(String crNo, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMlcNo = null;

		String countMlcCrNo;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			countMlcCrNo = objEssentialDAO.checkValidMlcCrNo(crNo, _userVO);
			if (countMlcCrNo.equals("0")) {
				throw new HisApplicationExecutionException("Not a MLC Patient");
			} else {
				lstMlcNo = objEssentialDAO.getMlcNoBasedOnCrNo(crNo, _userVO);
			}

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		} catch (HisDataAccessException e) {
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
		return lstMlcNo;
	}

	public Map getParichitEssentials(UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();

		List lstConsultants = null;
		List lstEpisodeAction = null;
		Map map = new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstConsultants = objEssentialDAO.getUnitConsultant(_userVO);
			map.put(RegistrationConfig.UNIT_CONSULTANT_LIST, lstConsultants);

			lstEpisodeAction = objEssentialDAO.getEpisodeActionList(_userVO);
			map.put(RegistrationConfig.ESSENTIAL_BO_OPTION_EPISODE_ACTION,
					lstEpisodeAction);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return map;
	}

	public Map getEssentialForCityLocation(UserVO _userVO) {
		Map essentialMap = new HashMap();
		String defaultStateName = "";
		List districtList = new ArrayList();
		List optionAreaCategory = new ArrayList();
		String _stateCode = RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE;
		essentialMap.put(RegistrationConfig.DEFAULT_STATE_NAME,
				defaultStateName);
		essentialMap.put(RegistrationConfig.DISTRICT_LIST_DEFAULTSTATE,
				districtList);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
				optionAreaCategory);

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			optionAreaCategory = objEssentialDAO.getAreaCategory();
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,
					optionAreaCategory);
			defaultStateName = objEssentialDAO.getDefaultStateName(_userVO);
			essentialMap.put(RegistrationConfig.DEFAULT_STATE_NAME,
					defaultStateName);
			districtList = objEssentialDAO.getDistrictList(_userVO, _stateCode);
			essentialMap.put(RegistrationConfig.DISTRICT_LIST_DEFAULTSTATE,
					districtList);

			// lstDepartment=objEssentialDAO.getDepartmentWithCasuality(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_WITH_CASUALITY,
			// lstDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getEssentialForRoom(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List locationName = null;
		List buildingList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			/*
			 * locationName=objEssentialDAO.getLocation(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
			 * locationName);
			 */
			buildingList = objEssentialDAO.getBuilding(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_BUILDING,
					buildingList);

			// lstDepartment=objEssentialDAO.getDepartmentWithCasuality(_userVO);
			// essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DEPARTMENT_WITH_CASUALITY,
			// lstDepartment);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public RoomMasterVO getEssentialToModifyRoom(String roomCode, UserVO _userVO) {
		RoomMasterVO roomMasterVO = new RoomMasterVO();
		List locationName = null;
		List roomDetailList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomMasterVO = objEssentialDAO.getEssentialToModifyRoom(roomCode,
					_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomMasterVO;
	}

	public List getDistrictOnBasisOfState(String stateCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstDistrict = null;

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstDistrict = objEssentialDAO.getDistrictList(_userVO, stateCode);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstDistrict;
	}

	public CityLocationMasterVO getDetailOnBasisOfLocation(String locCode,
			UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CityLocationMasterVO cityLocMstVO = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			cityLocMstVO = objEssentialDAO.getDetailOnBasisOfLocation(locCode,
					userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return cityLocMstVO;
	}

	public List getSpecialClinicUnitList(UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstSpecialClinic = null;

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstSpecialClinic = objEssentialDAO.getSpecialClinicUnitList(userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstSpecialClinic;
	}

	public Map getLocationEssential(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List locationList = new ArrayList();
		List buildingList = new ArrayList();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			locationList = objEssentialDAO.getLocationType(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE,
					locationList);
			
			if (Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED
					.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES)) {
				buildingList = objEssentialDAO.getBuilding(_userVO);
				essentialMap.put(
						RegistrationConfig.ESSENTIALBO_OPTION_BUILDING,
						buildingList);
			}

			

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getModifyLocationEssential(String locationCode,
			String hospitalCode, UserVO _userVO) {
		LocationMasterVO locationMasterVO = new LocationMasterVO();

		List locationTypeList = new ArrayList();
		List buildingList = new ArrayList();
		List blockList = new ArrayList();
		List floorList = new ArrayList();
		List roomList = new ArrayList();

		Map EssentialMap = new HashMap();

		EssentialMap.put(RegistrationConfig.ESSENTIAL_LOCATION_MASTER_VO,
				locationMasterVO);
		EssentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_BUILDING,
				buildingList);
		EssentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_BLOCK,
				blockList);
		EssentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_FLOOR,
				floorList);
		EssentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM,
				roomList);
		EssentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE,
				locationTypeList);

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			LocationMasterDAO locationMasterDAO = new LocationMasterDAO(tx);
			EssentialDAO essDao = new EssentialDAO(tx);

			locationMasterVO = locationMasterDAO.getModifyLocationEssential(
					locationCode, hospitalCode);
			EssentialMap.put(RegistrationConfig.ESSENTIAL_LOCATION_MASTER_VO,
					locationMasterVO);
			locationTypeList = essDao.getLocationType(_userVO);
			EssentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_LOCATION_TYPE,
					locationTypeList);

			System.out.println("config value----"
					+ Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED);
			System.out.println("config value----"
					+ Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED);
			if (Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED
					.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES)) {
				System.out.println("inside 1----");
				buildingList = essDao.getBuilding(_userVO);
				EssentialMap.put(
						RegistrationConfig.ESSENTIALBO_OPTION_BUILDING,
						buildingList);

				blockList = essDao.getBlockComboBasedOnBuilding(_userVO,
						locationMasterVO.getBuilding());
				EssentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_BLOCK,
						blockList);

				floorList = essDao.getFloorComboBasedOnBlock(_userVO,
						locationMasterVO.getBlock());
				EssentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_FLOOR,
						floorList);

				roomList = essDao.getRoomComboBasedOnFloor(_userVO,
						locationMasterVO.getFloor());

				EssentialMap.put(
						RegistrationConfig.ESSENTIAL_BO_OPTION_ESTATE_ROOM,
						roomList);
			}

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), EssentialMap);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return EssentialMap;
	}

	public List getBlockComboBasedOnBuilding(UserVO _userVO,
			String _buildingCode) {

		List blockList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			DepartmentMasterDAO deptmstDAO = new DepartmentMasterDAO(tx);
			blockList = objEssentialDAO.getBlockComboBasedOnBuilding(_userVO,
					_buildingCode);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return blockList;
	}

	public List getFloorComboBasedOnBlock(UserVO _userVO, String _blockCode) {

		List floorList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			DepartmentMasterDAO deptmstDAO = new DepartmentMasterDAO(tx);
			floorList = objEssentialDAO.getFloorComboBasedOnBlock(_userVO,
					_blockCode);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return floorList;
	}

	public String getMarqueMessage(UserVO _userVO) {

		String message = "";

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();

			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			message = objEssentialDAO.marqueMsg(_userVO);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return message;
	}

	public List getRoomComboBasedOnFloor(UserVO _userVO, String _floorCode) {

		List roomList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			DepartmentMasterDAO deptmstDAO = new DepartmentMasterDAO(tx);
			roomList = objEssentialDAO.getRoomComboBasedOnFloor(_userVO,
					_floorCode);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;
	}

	public List getDepartmentType(UserVO _userVO) {

		List roomList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			DepartmentMasterDAO deptmstDAO = new DepartmentMasterDAO(tx);
			roomList = objEssentialDAO.getDepartmentType(_userVO);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;
	}

	public Map getEssentialsForShiftWiseCasesByCMOReport(UserVO _uservo) {

		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			List cmoList = objEssentialDAO.getCmoList(_uservo);
			essentialMap.put(RegistrationConfig.CMO_LIST, cmoList);
			List shiftList = objEssentialDAO.getShiftList(_uservo);
			essentialMap.put(RegistrationConfig.SHIFT_LIST, shiftList);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public Map getEssentialsForDiagnosisStatLocationWiseReport(UserVO _userVO) {

		List icdCodeList = null;
		List locationList = null;
		List hospitalDiseaseList = null;
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			icdCodeList = objEssentialDAO.getIcdCodeList(_userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_ICD_CODE_LIST,
					icdCodeList);
			locationList = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,
					locationList);
			hospitalDiseaseList = objEssentialDAO
					.getHospitalDiagnosisCodeList(_userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIALBO_OPTION_HOSPITAL_DIAGNOSOSIS_CODE_LIST,
							hospitalDiseaseList);

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	// Getting CMO Patients List
	public PatientDetailVO[] getCMOPatientList(String _unitCode, UserVO _userVO) {
		PatientDetailVO[] dailyPatientVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			dailyPatientVOs = dailyPatientDAO.getCMOPatientsList(_unitCode,
					_userVO);
		} catch (HisRecordNotFoundException e) {
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return dailyPatientVOs;
	}

	public String getCMOTotalPatCount(UserVO userVO, String unitCode) {
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			DailyPatientDAO dailyPatientDAO = new DailyPatientDAO(tx);
			count = dailyPatientDAO.getCMOTotalPatCount(userVO, unitCode);
		} catch (HisRecordNotFoundException e) {
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
		return count;
	}

	public ResultSet getAllPatientList() {

		List dailyPatientVoList = null;
		ResultSet rs=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			rs = objEssentialDAO.getAllPatients();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return rs;
	}

	public List getPatConditionMacro(String processId, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst = null;

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lst = objEssentialDAO.getPatConditionMacro(processId, userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lst;
	}

	public Map getBroughtByNPoliceVerDtl(MlcVO mlcVO, UserVO userVO) {
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try {
			tx.begin();
			PoliceVerificationDAOi policeVerDAO = new PoliceVerificationDAO(tx);
			PatientBroughtByDtlDAO patBroughtByDAO = new PatientBroughtByDtlDAO(
					tx);

			PoliceVerificationDtlVO policeVerDtlVO = policeVerDAO.select(mlcVO,
					userVO);
			essentialMap.put(RegistrationConfig.POLICE_VERIFICATION_DETAIL_VO,
					policeVerDtlVO);

			PatientBroughtByDtlVO patBroughtByVO = patBroughtByDAO.select(
					mlcVO, userVO);
			essentialMap.put(RegistrationConfig.PATIENT_BROUGHT_BY_VO,
					patBroughtByVO);
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

	public List getRefDepartmentList(UserVO userVO) {

		List lstRefHospital = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			//lstRefHospital = objEssentialDAO.getReferDepartmentListForHospital(userVO);
			lstRefHospital = objEssentialDAO.getAllGlobalDepartment(userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
			//e.printStackTrace();
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
		return lstRefHospital;
	}

	/* Changes by Amit as per OPD G5 Changes */
	public Map getPatientdeathDetailEssential(String crNo, String epiCode,
			UserVO userVO, String deptUnitCode) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List lstMedicalCerificateReqAdvBy=new ArrayList();
		List lstRelationships = null;
		List lstInjuryType = null;
		List lstInjuryNature = null;
		List lstDeathManner = null;
		String date = "";
		String consultentType= MrdConfig.PROCESS_TYPE_CONSULTANT;

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MedicalCertificateRequestDAO medicalCertificateReqDAO=new MedicalCertificateRequestDAO(tx);
			GlobalClinicalDAO objGlobalClinicalDAO=new GlobalClinicalDAO();
			lstRelationships = objGlobalClinicalDAO.getRelationsList(userVO);
			essentialMap.put(
					RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL,
					lstRelationships);

			lstInjuryType = objEssentialDAO.getMlcTypes(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_INJURY_TYPE_LIST,
					lstInjuryType);

			lstInjuryNature = objEssentialDAO.getInjuryNature(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_INJURY_NATURE_LIST,
					lstInjuryNature);

			lstDeathManner = objEssentialDAO.getDeathMannerList(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_DEATH_MANNER_LIST,
					lstDeathManner);

			date = objEssentialDAO.getOnSetDateNRecentVisitDate(crNo, epiCode,
					userVO);
			essentialMap
					.put(
							RegistrationConfig.ESSENTIAL_DEATH_ON_SET_DATE_N_RECENT_VISIT_DATE,
							date);
			lstMedicalCerificateReqAdvBy = medicalCertificateReqDAO.getMedicalCertificateReqAdvisedBy(deptUnitCode,userVO,consultentType);
			essentialMap.put(MrdConfig.MEDICAL_CERTIFICATE_REQUEST_ADVISEDBY_DETAIL, lstMedicalCerificateReqAdvBy);

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

	public Map getClinicalDeptList(UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap = new HashMap();
		List clinicalDeptList = null;

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			clinicalDeptList = objEssentialDAO.getClinicalDeptList(userVO);
			essentialMap.put(RegistrationConfig.CLINICAL_DEPT_LIST,
					clinicalDeptList);
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

	public List getRelationList(UserVO _userVO)
	{
		List lstRefHospital = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstRefHospital = objEssentialDAO.getRelationsList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.getEssentialMap();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstRefHospital;
	}

	public List getYellowSlipEntryUserList(String fromDate, String toDate,
			UserVO userVO) {

		List yellowSlipEntryUserList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			YellowSlipMonitoringDAOi objDAO = new YellowSlipMonitoringDAO(tx);
			yellowSlipEntryUserList = objDAO.getYellowSlipEntryUserList(
					fromDate, toDate, userVO);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return yellowSlipEntryUserList;
	}

	public List getYellowSlipEntryByUser(
			YellowSlipEntryDtlVO yellowSlipEntryVO, UserVO userVO) {

		List yellowSlipEntryVOList = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			YellowSlipEntryDAOi objDAO = new YellowSlipEntryDAO(tx);
			yellowSlipEntryVOList = objDAO.getYellowSlipEntryByUser(
					yellowSlipEntryVO, userVO);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return yellowSlipEntryVOList;
	}

	public Map getYellowSlipEntryByCRNoEpisodeNo(
			YellowSlipEntryDtlVO yellowSlipEntryDtlVO, UserVO userVO) {

		Map essentialMap = new HashMap();
		EpisodeVO episodeVO = new EpisodeVO();
		List<EpisodeDiagnosisVO> episodeDisgnosisVOList = null;
		EpisodeDiagnosisVO episodeDiagnosisVO;
		List consultantList = null;
		List diagnosisTypeList = null;
		String diagnosisTypeCode="";
		List diagnosisCodeList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try {
			tx.begin();
			YellowSlipEntryDAOi objDAO = new YellowSlipEntryDAO(tx);
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			episodeVO = objDAO.getYellowSlipEntryByCRNoEpisodeNo(
					yellowSlipEntryDtlVO, userVO);
			episodeDisgnosisVOList=objDAO.getYellowSlipEntryDiagnosisDtl(yellowSlipEntryDtlVO, userVO);
			
			essentialMap.put(RegistrationConfig.EPISODE_VO, episodeVO);
			essentialMap.put(RegistrationConfig.EPISODE_DIAGNOSIS_VO_LIST,
					episodeDisgnosisVOList);
			consultantList = essentialDAO.getConsultantForUnit(
					yellowSlipEntryDtlVO.getDepartmentUnitCode(), userVO);
			essentialMap.put(RegistrationConfig.UNIT_CONSULTANT_LIST,
					consultantList);
			diagnosisTypeList = essentialDAO.getDiagnosisTypeEssential(userVO);
			essentialMap.put(RegistrationConfig.DIAGNOSIS_LIST,
					diagnosisTypeList);
			
			diagnosisTypeCode=episodeVO.getDiagnosisCodeType();
			
			if (diagnosisTypeCode.equals(OpdConfig.CHOICE_ICD_CODE)) {
				diagnosisCodeList = essentialDAO
						.getIcdDiagnosisCodeList(userVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ICD_CODE_LIST,diagnosisCodeList);
			} else {
				diagnosisCodeList = essentialDAO
						.getHospitalDiagnosisCodeList(userVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_HOSPITAL_DIAGNOSOSIS_CODE_LIST,
						diagnosisCodeList);
			}

			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

	public void saveYellowSlipMonitoringDtl(
			YellowSlipEntryDtlVO yellowSlipEntryDtlVO,
			YellowSlipMonitoringVO yellowSlipMonitoringVO, EpisodeVO episodeVO,
			EpisodeDiagnosisVO[] episodeDiagnosisInsertVO,
			EpisodeDiagnosisVO[] episodeDiagnosisUpdateVO,
			EpisodeCloseVO episodeCloseVO, UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			YellowSlipEntryDAOi entryDAO = new YellowSlipEntryDAO(tx);
			EpisodeDAO episodeDAO = new EpisodeDAO(tx);
			EpisodeDiagnosisDAOi episodeDiagnosisDAO = new EpisodeDiagnosisDAO(tx);
			EpisodeCloseDAOi episodeCloseDAO = new EpisodeCloseDAO(tx);
			YellowSlipMonitoringDAOi monitoringDAO = new YellowSlipMonitoringDAO(
					tx);
			if (yellowSlipEntryDtlVO.getMonitoringMode().equals("0")) {
				entryDAO.updateIsValid(yellowSlipEntryDtlVO, userVO);
				entryDAO.save(yellowSlipEntryDtlVO, userVO);
				///Update Episode_dtl
				episodeDAO.saveDiagnosisDetails(episodeVO, userVO);
				//update old record of diagnosis
				if(episodeDiagnosisUpdateVO!=null)
				for(int i=0;i<episodeDiagnosisUpdateVO.length;i++){
					episodeDiagnosisDAO.removeDiagnosis(episodeDiagnosisUpdateVO[i], userVO);
				}
				//insert new record of diagnosis
				if(episodeDiagnosisInsertVO!=null)
				for(int i=0;i<episodeDiagnosisInsertVO.length;i++){
					episodeDiagnosisDAO.create(episodeDiagnosisInsertVO[i], userVO);
				}
				
				//insert new record in episode close VO
				if(episodeCloseVO!=null && !episodeCloseVO.getIsClosedPreviously().equals("1"))
					episodeCloseDAO.create(episodeCloseVO, userVO);
				
				//update record in episode close VO in case of episode is open from close
				if(episodeCloseVO!=null && episodeCloseVO.getIsClosedPreviously().equals("1")){
					episodeCloseDAO.update(episodeCloseVO, userVO);
					
				}	
			} else {
				if (yellowSlipEntryDtlVO.getModificationRequired().equals("1")) {
					entryDAO.updateIsValid(yellowSlipEntryDtlVO, userVO);
					entryDAO.save(yellowSlipEntryDtlVO, userVO);
					///Update Episode_dtl
					episodeDAO.saveDiagnosisDetails(episodeVO, userVO);
					monitoringDAO.save(yellowSlipMonitoringVO, userVO);
					//update old record of diagnosis
					if(episodeDiagnosisUpdateVO!=null)
					for(int i=0;i<episodeDiagnosisUpdateVO.length;i++){
						episodeDiagnosisDAO.removeDiagnosis(episodeDiagnosisUpdateVO[i], userVO);
					}
					//insert new record of diagnosis
					if(episodeDiagnosisInsertVO!=null)
					for(int i=0;i<episodeDiagnosisInsertVO.length;i++){
						episodeDiagnosisDAO.create(episodeDiagnosisInsertVO[i], userVO);
					}
					//insert new record in episode close VO
					if(episodeCloseVO!=null && !episodeCloseVO.getIsClosedPreviously().equals("1"))
						episodeCloseDAO.create(episodeCloseVO, userVO);
					
					//update record in episode close VO in case of episode is open from close
					if(episodeCloseVO!=null && episodeCloseVO.getIsClosedPreviously().equals("1")){
						episodeCloseDAO.update(episodeCloseVO, userVO);
					}	
					
				} else {
					monitoringDAO.save(yellowSlipMonitoringVO, userVO);
				}
			}

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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}

	}
	
	public Map getUnitBasedOnWeekDay(String day,UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstUnit = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstUnit = objEssentialDAO.getUnitsBasedOnWeekDay(day,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,lstUnit);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}


	
	public List getVerificationDocumetByCatCode(String catCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstVerificationDocument = null;
		
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstVerificationDocument = objEssentialDAO.getVerificationDocumetByCatCode(catCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage() );
		}
		catch (HisDataAccessException e) 
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstVerificationDocument;
	}

	
	
	//get essential for search 
	public Map getEssentialForSearch(UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List genderOptionList=null;
		List locationOptionList=null;
		List ageRangeList=null;
		List hospitalList=null;

		try
		{
			tx.begin();
			EssentialDAO essentialDAO = new EssentialDAO(tx);
			genderOptionList=essentialDAO.getGender(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,genderOptionList);
			
			if(Config.CLIENT.equals(Config.CLIENT_GNCTD))
			{
			locationOptionList=essentialDAO.getLocation(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,locationOptionList);
			}
			
			//Added by Singaravelan on 17-Jul-2014 
			//hospitalList=essentialDAO.getAllHospitalsSeatIDWise(userVO);
			hospitalList=essentialDAO.getAllHospitals(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			/*
			 * hardcoded values are being used
			 * ageRangeList=essentialDAO.getAgeRangeList(userVO);
			essentialMap.put(RegistrationConfig.AGE_RANGE_OPTION_LIST,ageRangeList);*/
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
		
		return essentialMap;
	}
	
	
	public List getUnitRoom(String _unitCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List roomList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList = objEssentialDAO.getUnitRoom(_unitCode,_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;
	}


	public Map getRoomsAndConsultantsByUnit(String _unitCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List roomList = new ArrayList();
		List consultantList = new ArrayList();
		Map EssentialMap=new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList = objEssentialDAO.getUnitRoom(_unitCode,_userVO);
			
			EssentialMap.put(RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST, roomList);
			
			consultantList = objEssentialDAO.getUnitConsultants(_unitCode,_userVO);
			
			EssentialMap.put(RegistrationConfig.ESSENTIAL_OPTION_CONSTULTANT_HOU_1_2_LIST, consultantList);
			
			
		} catch (HisRecordNotFoundException e) {
			EssentialMap.put(RegistrationConfig.ESSENTIAL_OPTION_ROOM_LIST, roomList);
			EssentialMap.put(RegistrationConfig.ESSENTIAL_OPTION_CONSTULTANT_HOU_1_2_LIST, consultantList);
			e.setEssentialMap(EssentialMap);
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(),e.getEssentialMap());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return EssentialMap;
	}
	

	public List getPatientPrimaryCategory(UserVO _userVO) {
		List patPrimaryCategory = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			patPrimaryCategory = objEssentialDAO.getPatientPrimaryCategory(_userVO);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return patPrimaryCategory;
	}

	public Map getCategoryWiseMappedUnMappedDocument(String _categoryCode,UserVO _userVO) {
		
		List mappedDocs = null;
		List unMappedDocs = null;
		Map essentialMap=new HashMap();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
		
			mappedDocs = objEssentialDAO.getCategoryWiseMappedDocument(_categoryCode,_userVO);
			
			essentialMap.put(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY,mappedDocs);
			
			unMappedDocs = objEssentialDAO.getCategoryWiseUnMappedDocument(_categoryCode,_userVO);
			
			essentialMap.put(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY,unMappedDocs);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
			//e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			//e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			//e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getDepartments(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			
				lstDepartment = objEssentialDAO.getAllDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,lstDepartment);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}
	
	public ReportVO[] getReportForHosPatStat(ReportVO vo,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ReportVO[] reportVO=null;
		try
		{ 
			tx.begin();
			
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			reportVO=objEssentialDAO.getReportForHosPatStat(vo,_userVO);
			if(reportVO==null)
				throw new HisReportDataNotFoundException();
			
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
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		/*catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}*/
		finally
		{
			tx.close();
			return reportVO;
			
		}

	}

	public ReportVO[] getReportForHosPatStatState(ReportVO vo,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ReportVO[] reportVO=null;
		try
		{ 
			tx.begin();
			
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			reportVO=objEssentialDAO.getReportForHosPatStatState(vo,_userVO);
			if(reportVO==null)
				throw new HisReportDataNotFoundException();
			
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
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		/*catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}*/
		finally
		{
			tx.close();
			return reportVO;
			
		}

	}

	
	
	public List getDepartmentsForAgeWiseReport(UserVO _userVO) {
		
		List lstDepartment = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			lstDepartment = objEssentialDAO.getAllDepartment(_userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return lstDepartment;
	}
	
	public ReportVO[] getAgeWiseRegReport(ReportVO vo,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ReportVO[] reportVO=null;
		try
		{ 
			tx.begin();
			
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

		
			reportVO=objEssentialDAO.getAgeWiseRegReport(vo,_userVO);

			if(reportVO==null)
				throw new HisReportDataNotFoundException();
			
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
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		/*catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}*/
		finally
		{
			tx.close();
			return reportVO;
			
		}

	}
	public JasperPrint createReport(String jrxmlFileName,ReportVO[] reportVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		JRResultSetDataSource jDataSource=null;
		JasperPrint jPrint = null;
		try
		{
			tx.begin();
			
			//EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			//jDataSource=scheduleLetterPrintDtlDAOi.getSechduleLetterReportDetail(_scheduleLetterPrintDetailVO, _userVO);
			// System.out.println(_reportVO.getJrxmlName());
			JRBeanArrayDataSource jr=new JRBeanArrayDataSource(reportVO);
			InputStream is =  (InputStream) this.getClass().getResourceAsStream("/registration/reports/report1/jrxml/"+ jrxmlFileName);
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jReport = JasperCompileManager.compileReport(jasperDesign);
			Map mpParameter = new HashMap();

			jPrint = JasperFillManager.fillReport(jReport, mpParameter,jr);
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
		
		return jPrint;
	}
	


	
	
	public Map setEssentialForRedirectOfOPDPat(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartmentUnit = null;
		List lstAllActiveDepartmentUnit=null;		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			
			lstDepartmentUnit=objEssentialDAO.getUnitsFromRoster(_userVO);
			essentialMap.put(RegistrationConfig.LIST_OF_DEPT_UNIT_BY_ROSTER, lstDepartmentUnit);
			
			lstAllActiveDepartmentUnit=objEssentialDAO.getAllActiveUnitsFromRoster(_userVO);
			essentialMap.put(RegistrationConfig.LIST_OF_ALL_ACTIVE_DEPT_UNIT_BY_ROSTER, lstAllActiveDepartmentUnit);
			
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}
	
	public List getRoomListByDeptUnit(String deptUnitCode,UserVO _userVO) {

		List roomList = null;
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList=objEssentialDAO.getRoomsByUnitCode(_userVO, deptUnitCode);
			
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;

	}
	
	public List getAllActiveRoomListByDeptUnit(String deptUnitCode,UserVO _userVO) {

		List roomList = null;
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList=objEssentialDAO.getAllActiveRoomsByUnitCode(_userVO, deptUnitCode);
			
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;

	}
	
	public Map getParticularRoomDetail(String deptUnitCode,String roomCode,UserVO _userVO) {

		List roomDetailList = null;
		Map roomDetailMap=new HashMap();	
		List todayPatVisitList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			
			roomDetailList=objEssentialDAO.getParticularRoomDetail(deptUnitCode, roomCode, _userVO);
			roomDetailMap.put(RegistrationConfig.PARTICULAR_ROOM_DETAIL_BY_DEPT_UNIT, roomDetailList);
			
			todayPatVisitList=objEssentialDAO.getTodayVistPatListByRoom(deptUnitCode, roomCode, _userVO);
			roomDetailMap.put(RegistrationConfig.TODAY_VISIT_PAT_LIST_BY_ROOM, todayPatVisitList);
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomDetailMap;

	}
	
	public List getTodayVistPatListByRoom(String deptUnitCode,String roomCode,UserVO userVO) {

		List todayPatVisitList = null;
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			todayPatVisitList=objEssentialDAO.getTodayVistPatListByRoom(deptUnitCode, roomCode, userVO);
			
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return todayPatVisitList;

	}

	
	public Map getAgeSexReligionDeptRegEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstReligion = null;
		List lstAge = null;
		List lstGender=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAge = objEssentialDAO.getAgeRangeList(_userVO);
			essentialMap.put(MrdConfig.AGE_RANGE_COMBO,lstAge);
			
			lstGender=essentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstReligion=objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}
	public Map getAgeSexReligionHosRegEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstReligion = null;
		List lstAge = null;
		List lstGender=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstAge = objEssentialDAO.getAgeRangeList(_userVO);
			essentialMap.put(MrdConfig.AGE_RANGE_COMBO,lstAge);
			
			lstGender=essentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
			lstReligion=objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
			
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getSpecialityWiseIndoorPatientReportEssentials(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;
		List lstUnit = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = essentialDAO.getAllDeptHavingUnits(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,lstDepartment);
			lstUnit = objEssentialDAO.getUnitBasedOnSpeciality(	_userVO);
			essentialMap.put(MrdConfig.UNITS_BASED_ON_SPECIALITY,lstUnit);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getDiseaseType(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDisType = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			
			lstDisType = essentialDAO.getDiseaseType(_userVO);
			essentialMap.put(MrdConfig.ESSENTIAL_DISEASE_TYPE,lstDisType);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}




	
	
	//offline Registration.
	public Map offlineRegistrationEssential(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lst = new ArrayList();
		lst.add(new Entry("Select Value", "-1"));

		List lstPrimaryCat = new ArrayList(lst);
		List lstRegCategory = new ArrayList(lst);
		List lstMaritalStatus = new ArrayList(lst);
		List lstAreaCategory = new ArrayList(lst);
		List lstAgeType = new ArrayList(lst);
		List lstEducation = null;
		List lstCaste = null;
		List lstGender = new ArrayList(lst);
		List lstReligion = new ArrayList(lst);
		List lstLocation = new ArrayList(lst);
		List lstState = new ArrayList(lst);
		List lstCountry = new ArrayList(lst);
		List lstRefHospital = new ArrayList(lst);
		List lstNationality = new ArrayList(lst);
		List lstAllDepartment = new ArrayList(lst);
		List lstUnit = new ArrayList(lst);
		List districtList = new ArrayList(lst);
		List lstReferDepartment = new ArrayList(lst);
		String amount = null;
		List occupationDetail = new ArrayList(lst);
		
		//essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCategory);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,lstLocation);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE, lstState);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
		//essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,lstUnit);
		essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,districtList);
		essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);

			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			/*
			 * lstAllDepartment=objEssentialDAO.getAllDepartment(_userVO);
			 * essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,lstAllDepartment);
			 */

			//get the list of department unit and room
			Map deptUnitRoomMap=objEssentialDAO.getDeptUnitRoom(_userVO);
			essentialMap.putAll(deptUnitRoomMap);
			
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);

			///////
			lstEducation = objEssentialDAO.getEducation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_EDUCATION,lstEducation);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,lstGender);
			
					
			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);
			//////
			

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION,lstReligion);

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,lstLocation);

			lstState = objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE,lstState);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,lstCountry);

			lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL,lstRefHospital);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY,lstNationality);

			lstAreaCategory = objEssentialDAO.getAreaCategory();
			//System.out.println("lstAreaCategory" + lstAreaCategory);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY,lstAreaCategory);

			lstAgeType = objEssentialDAO.getAgeType();
			//System.out.println("lstAgeType" + lstAgeType);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE,lstAgeType);

			if (Config.CLIENT.equals(Config.CLIENT_PGIMER)) {
				occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);
			}
			
			lstReferDepartment = objEssentialDAO.getReferDepartmentList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REFERAL_DEPARTMENT_DTL,lstReferDepartment);

			//not required here
			//lstUnit = objEssentialDAO.getUnitsByType(_userVO,RegistrationConfig.UNIT_TYPE_SPECIALITY);
			//essentialMap.put(RegistrationConfig.REG_DESK_UNIT_WITH_SPECIALITY,lstUnit);

			districtList = objEssentialDAO.getDistrictList(_userVO,RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE,districtList);
			
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			// e.setEssentialMap(essentialMap);
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e) {
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		
		return essentialMap;
	}//end offlineRegistrationEssential

	public List getUnitDateWiseRoomRosterList(String unitCode,String deactivationDate,UserVO _userVO) {

		
		List unitDatewiseRoomList = null;
		
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			
			unitDatewiseRoomList=objEssentialDAO.getUnitDateWiseRoomRosterList(unitCode, deactivationDate,_userVO);
		  
		   
		    
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return unitDatewiseRoomList;

	}

	public List getMaritalStatus(UserVO userVO) {

		
		List lstMaritalStatus = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstMaritalStatus = objEssentialDAO.getMaritalStatus(userVO);
			
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return lstMaritalStatus;
	
	}

	public List getOcccupationList(UserVO userVO) {

		List occupationDetail = null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			occupationDetail = objEssentialDAO.getOccupationDetail(userVO);
				
		}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return occupationDetail;
	
	}

	public List getReligionList(UserVO userVO) {

		List lstReligion = null;
	
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstReligion = objEssentialDAO.getReligion(userVO);
			}

		catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return lstReligion;
	
	}
	
	
	public List getRoomsByUnitOfDayWiseCapacity(String _unitCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List roomList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList = objEssentialDAO.getRoomsByUnitOfDayWiseCapacity(_unitCode,_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;
	}
	
	
	public List getRoomsByUnitForRoster(String _unitCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List roomList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList = objEssentialDAO.getRoomsByUnitForRoster(_unitCode,_userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;
	}
	

	public List getAllCounter(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstCounter = null;

		try {
			tx.begin();
			EssentialDAO essentialDAO=new EssentialDAO(tx);
			lstCounter = essentialDAO.getAllCounter(_userVO);
		} 
		catch (HisRecordNotFoundException e) {
			tx.rollback();
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
		return lstCounter;

	}

	

	public Map setUnitConsultantInitials(String _unitCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitDaysList = null;
		List consultantList = null;
		Map essentialMap=new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			unitDaysList = objEssentialDAO.getUnitRosterWorkingDaysList(_unitCode,_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DEPT_UNIT_DAYS, unitDaysList);
			consultantList=objEssentialDAO.getConsultantByUnit(_unitCode, _userVO);
			essentialMap.put(RegistrationConfig.UNIT_CONSULTANT_LIST, consultantList);
			
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
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
	
	public List getDepartmentsForFilePrinting(UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List departmentList = null;
		
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			departmentList=objEssentialDAO.getDepartmentsForFilePrinting(userVO);
			
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return departmentList;
	}
	
	
	public List getUnitForFileNoPrinting(String departmentCode,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List unitList = null;
		
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			unitList=objEssentialDAO.getUnitForFileNoPrinting(departmentCode,userVO);
			
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return unitList;
	}
	
	
	public Map getEssentialForDeptUserWiseReport(UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deptList = null;
		List userList = null;
		List hospitalList =null;
		List categortList=null;
		Map essentialMap=new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			deptList=objEssentialDAO.getAllDepartment(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, deptList);
			userList=objEssentialDAO.getAllUsers(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_USER, userList);
			hospitalList=objEssentialDAO.getAllHospitalsSeatIDWise(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			categortList=objEssentialDAO.getPrimaryCat(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY, categortList);
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
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
	
	public Map getPatientListingReportEssentials(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deptList = null;
		List userList = null;
		List hospitalList = null;
		List regCatList = null;
		List lstPrimaryCat = null;
		Map essentialMap = new HashMap();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			
			deptList = objEssentialDAO.getAllDepartment(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, deptList);
			
			userList = objEssentialDAO.getAllUsers(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_USER, userList);
			
			hospitalList = objEssentialDAO.getAllHospitalsSeatIDWise(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);

			lstPrimaryCat = objEssentialDAO.getPrimaryCat(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			regCatList = objEssentialDAO.getRegCategory(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY, regCatList);
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
			e.printStackTrace();
			tx.rollback();
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
	
	public List getTodayWorkingRoomBasedOnUnit(String departmentUnitCode,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List roomList = null;
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			roomList=objEssentialDAO.getTodayWorkingRoomBasedOnUnit(departmentUnitCode,userVO);
				
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return roomList;
	}

	
	
	
	/**
	 * Getting Patient Modification Essentials
	 * @param _userVO
	 * @return
	 * Created By Pragya at 04-Aug-2011
	 */
	public Map getPatientModificationEssential(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstCaste = null;
		List lstAddressType = null;
		List lstLocation = null;
		List lstState = null;
		List lstCountry = null;
		List lstVerificationDoc = null;
		List lstDistrict = null;
		List lstAreaCat = null;
		List relationList = null;
		List lstMaritalStatus = null;
		List lstAgeType = null;
		List lstGender = null;
		List lstReligion = null;
		//List lstRefHospital = null;
		List lstNationality = null;
		List distrciList = null;
		List occupationDetail=null;
		
		String _stateCode = RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			lstAgeType = objEssentialDAO.getAgeType();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AGE_TYPE, lstAgeType);

			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER, lstGender);

			lstCaste = objEssentialDAO.getCaste(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE,lstCaste);

			lstNationality = objEssentialDAO.getNationality(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_NATIONALITY, lstNationality);

			lstReligion = objEssentialDAO.getReligion(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION, lstReligion);

			lstCountry = objEssentialDAO.getCountry(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY, lstCountry);

			lstAddressType = objEssentialDAO.getAddressType(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ADDRESS_TYPE, lstAddressType);
			
			lstState = objEssentialDAO.getState(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE, lstState);
			/*lstState = objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE, _userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE, lstState);*/

			lstLocation = objEssentialDAO.getLocation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION, lstLocation);
			
			lstDistrict = objEssentialDAO.getDistrictList(_userVO, _stateCode);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DISTRICT_ON_DEFAULT_STATE, lstDistrict);
			/*distrciList = objEssentialDAO.getDistrictList(_userVO, RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DISTRICT_LIST_STATEWISE, distrciList);*/

			lstAreaCat = objEssentialDAO.getAreaCategory();
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_AREA_CATEGORY, lstAreaCat);
			
			relationList = objEssentialDAO.getRelationsList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELATION_DTL, relationList);

			
			String[] dt = objGlobalEssentialDAO.getSystemDate(_userVO);
			essentialMap.put(Config.SYSDATE, dt[0]);

			Date dtobj = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(RegistrationConfig.SYSADATEOBJECT, dtobj);			
				

			lstVerificationDoc = objEssentialDAO.getVerificationDocuments(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_VERIFICATION_DOCUMENTS, lstVerificationDoc);
			
			lstMaritalStatus = objEssentialDAO.getMaritalStatus(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_MARITAL_STATUS,lstMaritalStatus);
			
			occupationDetail = objEssentialDAO.getOccupationDetail(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_OCCUPATION_DTL,occupationDetail);

			/*lstRefHospital = objEssentialDAO.getRefHospital(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REF_HOSPITAL, lstRefHospital);*/

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			//System.out.println("HisDataAccessException:: " + e);
			//e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			//System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			//System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			//System.out.println("HisException:: " + e);
			//e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			//System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	/**
	 * Getting Cash Collection User Wise Essentails
	 * @param _userVO
	 * @return
	 * Created By Pragya at 16-Aug-2011
	 */ 
	public Map getUserWiseCashCollectionReportEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		//List lstPrimaryCat=null;
		List lstRegCat=null;
		List lstHospitalList =null;
		List lstUserList =null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

			//lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			//essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);

			lstRegCat = objEssentialDAO.getRegCategory(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstRegCat);

			lstUserList=objEssentialDAO.getAllUsers(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_USER, lstUserList);

			lstHospitalList=objEssentialDAO.getAllHospitalsSeatIDWise(_userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, lstHospitalList);

			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
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

	/**
	 * Getting Hospital Essential Combo
	 * @param _userVO
	 * @return
	 * Created By Vivek at 16-Aug-2011
	 */
	public Map<String,String> getHospitalEssentialCombo(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstHospitalCombo = null;


		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			lstHospitalCombo = objEssentialDAO.getAllHospitalsSeatIDWise(_userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, lstHospitalCombo);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map getEssentialForUserWiseReport(UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List userList = null;
		List hospitalList =null;
		List categoryList =null;
		Map essentialMap=new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
		
			userList=objEssentialDAO.getAllUsers(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_USER, userList);
			hospitalList=objEssentialDAO.getAllHospitalsSeatIDWise(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			categoryList= objEssentialDAO.getPatientCategoryList(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY, categoryList);
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
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

	public Map getEssentialForRosterReport(UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		
		List hospitalList =null;
		
		Map essentialMap=new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
		
			
			hospitalList=objEssentialDAO.getAllHospitalsSeatIDWise(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
		
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
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

	public Map getEssentialDSSReport(String strMode_p, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List deptList = null;
		List casteList = null;
		List hospitalList = null;
		List categoryList = null;
		List religionList = null;
		List stateList = null;

		Map essentialMap = new HashMap();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			deptList = objEssentialDAO.getAllGlobalDepartment(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, deptList);
			hospitalList = objEssentialDAO.getAllHospitalsSeatIDWise(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			categoryList = objEssentialDAO.getPrimaryCat(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY, categoryList);
			casteList = objEssentialDAO.getCaste(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_CASTE, casteList);
			religionList = objEssentialDAO.getReligion(userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_RELIGION, religionList);
			stateList = objEssentialDAO.getStateBasedOnCountry(RegistrationConfig.REGISTRATIONDESK_DEFAULT_COUNTRY_CODE, userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_STATE, stateList);
				// Setting Minimum Month Year 
			
			if(strMode_p.equals(RegistrationConfig.DSS_REPORTS_MODES_AGEWISE_REG_STATS_CONSOLIDATED) || strMode_p.equals(RegistrationConfig.DSS_REPORTS_MODES_AGEWISE_REG_STATS_HOSPITALWISE))
			{
				String strMinMonthYear = objEssentialDAO.getDSSMinimumMonthYear(RegistrationConfig.DSS_REPORTS_TARGET_TABLE_REGISTRATION,userVO);
				essentialMap.put(RegistrationConfig.DSS_REPORTS_MINIMUM_MONTH_YEAR, strMinMonthYear);
			}
			else if(strMode_p.equals(RegistrationConfig.DSS_REPORTS_MODES_OPD_STATS_CONSOLIDATED) || strMode_p.equals(RegistrationConfig.DSS_REPORTS_MODES_OPD_STATS_HOSPITALWISE))
			{
				String strMinMonthYear = objEssentialDAO.getDSSMinimumMonthYear(RegistrationConfig.DSS_REPORTS_TARGET_TABLE_EPISODE,userVO);
				essentialMap.put(RegistrationConfig.DSS_REPORTS_MINIMUM_MONTH_YEAR, strMinMonthYear);
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
			e.printStackTrace();
			tx.rollback();
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

	public DSSRegistrationVO[] getAgeWiseDSS(DSSRegistrationVO vo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DSSRegistrationVO[] arrayVO = null;
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			arrayVO = objEssentialDAO.getAgeWiseDSS(vo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
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
			e.printStackTrace();
			tx.rollback();
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
		return arrayVO;
	}
	
	
	
	public List getUserComboForCategoryWiseUserWiseReport(UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List userList = null;
		Map essentialMap=new HashMap();

		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
		
			userList=objEssentialDAO.getUsersBasedOnHospital(userVO);
			//essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_USER, userList);

			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return userList;
	}
	
	public List<DSSRegistrationVO> getDSSAgeWiseStatReportData(DSSReportVO vo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DSSRegistrationVO> lstDSSData = null;
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstDSSData = objEssentialDAO.getDSSAgeWiseStatReportData(vo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
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
			e.printStackTrace();
			tx.rollback();
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
		return lstDSSData;
	}
	
	
	public Map getEssentialForAmbulanceWiseReport(UserVO userVO) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		List userList = null;
		List hospitalList =null;
		List categoryList =null;
		Map essentialMap=new HashMap();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
		
			hospitalList=objEssentialDAO.getAllHospitalsSeatIDWise(userVO);
			essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
			
		} catch (HisRecordNotFoundException e) {
			
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		
		catch (HisDataAccessException e) {
			e.printStackTrace();
			tx.rollback();
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


	public List<DSSEpisodeVO> getDSSOPDStatsReportData(DSSReportVO vo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DSSEpisodeVO> lstDSSData = null;
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstDSSData = objEssentialDAO.getDSSOPDStatsReportData(vo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
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
			e.printStackTrace();
			tx.rollback();
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
		return lstDSSData;
	}

	public List<DSSEpisodeVO> getDSSEMGStatsReportData(DSSReportVO vo)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DSSEpisodeVO> lstDSSData = null;
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstDSSData = objEssentialDAO.getDSSOPDStatsReportData(vo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
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
			e.printStackTrace();
			tx.rollback();
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
		return lstDSSData;
	}
	
	public String getPatCrNoFrmMCTSNo(String mctsNo,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> mapData = new HashMap<String, Object>();
		String patCrno ="";
		try
		{ 

			tx.begin();
			
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			patCrno =objEssentialDAO.getPatCrNoFrmMCTSNo(mctsNo,_userVO);
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
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		/*catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}*/
		finally
		{
			tx.close();
			return patCrno;
			
		}

	}
	
	public Map getPatVisitDtlReport(String patcrno,UserVO _userVO,String fromDate,String toDate, String Choice)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] reportVO=null;
		Map<String, Object> mapData = new HashMap<String, Object>();

		try
		{ 

			tx.begin();
			
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);

		
			List lstData=objEssentialDAO.getPatVisitDtlReport(patcrno,_userVO, fromDate, toDate,Choice);
			mapData.put(RegistrationConfig.RPT_DATA_PATIENT_WISE_VISIT,lstData);

	
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			mapData.put(RegistrationConfig.RPT_DATA_PATIENT_WISE_VISIT,new ArrayList());
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
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException(e.getMessage());
		}
		/*catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}*/
		finally
		{
			tx.close();
			return mapData;
			
		}

	}
	
	public Map getPoliceExaminationReqtEssentials(UserVO _userVO) 
	{
		Map essentialMap = new HashMap();
		
		List lstExaminationType = null;
		List lstPolice = null;
		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			
			lstExaminationType = objEssentialDAO.getExaminationTypeCombo(_userVO);
			essentialMap.put(RegistrationConfig.CASUALTYDESK_OPTION_EXAMINATION_TYPE,lstExaminationType);
			
			lstPolice = objEssentialDAO.getPoliceCombo(_userVO);
			essentialMap.put(RegistrationConfig.CASUALTYDESK_OPTION_POLICESTATION,lstPolice);
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
	
	public List<PoliceExaminationReqtDtlVO> getPoliceExaminationReqtDtl(String strMode_p,
			String strPatCrNo_p, String strEpisode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		
		List<PoliceExaminationReqtDtlVO> lstPoliceExaminationReqtDtlVOs;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			lstPoliceExaminationReqtDtlVOs = objEssentialDAO.getPoliceExaminationReqtDtl(strMode_p,strPatCrNo_p,strEpisode_p,strEpisodeVisitNo_p, objUserVO_p);
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
		return lstPoliceExaminationReqtDtlVOs;
	}
	
	public Map getEssentialForEmployee(UserVO _userVO) {
		Map essentialMap = new HashMap();
		List lstSalutation = new ArrayList();
		List lstGender = new ArrayList();
		List lstCountry = new ArrayList();
		List lstAllDepartment = new ArrayList();	
		List lstDesignation = new ArrayList();	

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			EssentialDAO objEssentialDAO = new EssentialDAO(tx);
			
			lstSalutation = objEssentialDAO.getSalutation(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_SALUTATION,
					lstSalutation);			
			
			lstGender = objEssentialDAO.getGender(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,
					lstGender);
			
			//lstCountry = objEssentialDAO.getCountry(_userVO);
			//essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_COUNTRY,
			//		lstCountry);
			
			lstAllDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_ALL_DEPARTMENT,
					lstAllDepartment);
			
			lstDesignation=objEssentialDAO.getDesignationList(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_DESIGNATION,
					lstDesignation);
			

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.getEssentialMap();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}

}//end class
