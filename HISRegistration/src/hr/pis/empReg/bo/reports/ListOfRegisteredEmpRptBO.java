package hr.pis.empReg.bo.reports;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;
import hr.pis.config.PisEssentialsDAO;




import hr.pis.empReg.config.PrConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfRegisteredEmpRptBO 
{
	
	public Map<String, List<Entry>> getRegistrationRptFormEssentials(UserVO _userVO) 
	{
		Map<String, List<Entry>> essentialMap = new HashMap<>();

		String strMode_p = "1";
		List<Entry> lstDesignation = null;
		List<Entry> lstDepartment = null;
		List<Entry> lstGender = null;
		List<Entry> lstFinalStatus = null;
		List<Entry> lstNatureOfJob = null;
	
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			
			PisEssentialsDAO objDAO = new PisEssentialsDAO(tx);
			
			
			
			lstDesignation = objDAO.getDesignation(strMode_p, "", _userVO, "", "");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DESIGNATION, lstDesignation);
			
			
			lstDepartment = objDAO.getDepartment(strMode_p, "", _userVO, "", "");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_DEPARTMENT, lstDepartment);
			
			lstGender = objDAO.getGender(strMode_p, "", _userVO, "", "");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_GENDER, lstGender);
			
			lstFinalStatus = objDAO.getFinalStatus(strMode_p, "", _userVO, "", "");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_FINAL_STATUS, lstFinalStatus);
			
			lstNatureOfJob = objDAO.getNatureOfJob(strMode_p, "", _userVO, "", "");
			essentialMap.put(PrConfig.ESSENTIAL_BO_OPTION_NATURE_OF_JOB, lstNatureOfJob);
			
			
			
						
			
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	

}
