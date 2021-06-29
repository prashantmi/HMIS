package hisglobal.business;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.GlobalEssentialDAOi;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.installSoftware.InstallSoftwareVO;
import hisglobal.utility.masterVerification.MasterVerificationMstDAO;
import hisglobal.utility.masterVerification.MasterVerificationVO;

import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientAuditLogMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

public class GlobalEssentialBO implements GlobalEssentialBOi
{
	public String[] getSysDate(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String[] dateAndFormat = new String[2];
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			dateAndFormat = dao.getSystemDate(_userVO);
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
		return dateAndFormat;
	}

	public Date getSysDate(Date dt)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			dt = dao.getSystemDate(new UserVO(), dt);
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return dt;
	}

	public String isRegistrationAllowed(String _categoryCode, UserVO _userVO)
	{
		String registrationAllowed = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			registrationAllowed = dao.isRegistrationAllowed(_categoryCode, _userVO);
		}
		catch (HisRegistrationTimingExpiredException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRegistrationTimingExpiredException(e.getMessage());
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
		return registrationAllowed;
	}

	public List getSystemDateAndFormat(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List dateAndFormat = new ArrayList();
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			dateAndFormat = dao.getSystemDateAndFormat(_userVO);
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
			//throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return dateAndFormat;
	}
	
	
	

	public HospitalMstVO getHospitalDetail(String hospitalCode) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HospitalMstVO hospitalMstVO=new HospitalMstVO();
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			hospitalMstVO = dao.getHospitalDetail(hospitalCode);
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return hospitalMstVO;
	}
	
	public List getHospitalCombo() {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List hospitalList=new ArrayList();
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			hospitalList = dao.getHospitalCombo();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return hospitalList;
	}


	public List getSoftwareList(InstallSoftwareVO softwareVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List softwareVOList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			softwareVOList = dao.getSoftwareList(softwareVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return softwareVOList;
	}

	
	public List getModuleList(UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List moduleList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			moduleList = dao.getModuleList(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return moduleList;
	}
	
	public List getMasterListByModuleId(String moduleId,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List masterVerificationList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			masterVerificationList = dao.getMasterListByModuleId(moduleId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return masterVerificationList;
	}
	
	
	public List getAllMasterList(String moduleId,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List masterList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			masterList = dao.getAllMasterList(moduleId, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return masterList;
	}
	
	public List getMasterColumnList(String masterName,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List masterColumnList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			masterColumnList = dao.getMasterColumnList(masterName, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return masterColumnList;
	}
	
	
	public List getCriteriaData(String criteriaQuery, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List criteriaDataList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			criteriaDataList = dao.getMasterCriteriaData(criteriaQuery, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return criteriaDataList;
	}
	
	
	public List getMasterDataList(String mainQuery, String[] masterColumnArray,String isActive,
			UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List masterDataList=null;
		try
		{
			tx.begin();
			GlobalEssentialDAOi dao = new GlobalEssentialDAO(tx);
			masterDataList = dao.getMasterDataList(mainQuery,masterColumnArray,isActive, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			System.out.println("HisRecordNotFoundException:: " + e);
			e.printStackTrace();
			throw new HisRecordNotFoundException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return masterDataList;
	}
	
	
	
	public void saveMasterVerification(MasterVerificationVO masterVerficationVO,UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MasterVerificationMstDAO dao = new MasterVerificationMstDAO(tx);
			dao.create(masterVerficationVO, userVO);
		}
	
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}
	

	public MasterVerificationVO getMasterVerification(MasterVerificationVO masterVerificationVO, UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MasterVerificationMstDAO dao = new MasterVerificationMstDAO(tx);
			masterVerificationVO=dao.select(masterVerificationVO, userVO);
		}
		
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return masterVerificationVO;
	}
	
	
	public void modifyMasterVerification(MasterVerificationVO masterVerificationVO, UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MasterVerificationMstDAO dao = new MasterVerificationMstDAO(tx);
			dao.modify(masterVerificationVO, userVO);
		}
		
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
	}

}
