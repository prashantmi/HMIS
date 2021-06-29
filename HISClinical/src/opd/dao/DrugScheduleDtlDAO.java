package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.UserVO;

public class DrugScheduleDtlDAO extends DataAccessObject
{
	Logger log;
	
	public DrugScheduleDtlDAO(TransactionContext _tx)
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public void save(DrugSheduleDtlVO drugSheduleDtlVO, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_DRUG_SCHEDULE_DTL";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			populateMAP.put(sq.next(),drugSheduleDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getSerialNo());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getSheduleSerialNo());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getEpisodeVisitNo());
        	//populateMAP.put(sq.next(),drugSheduleDtlVO.getItemTypeId());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getPopupDrugId());
        	
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getDoseId());
        	populateMAP.put(sq.next(),_userVO.getSeatId());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getDoseName());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getDoseTime());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getIsEmptyStomach());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getDoseShift());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getRemark());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getCutOffTime());
        	populateMAP.put(sq.next(),drugSheduleDtlVO.getCutOffTimeafter());
        	
                	
        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"DrugScheduleDtlDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException("Exception While ADDING");
		}

	}
	
	public String getMaxSlNo(DrugSheduleDtlVO drugSheduleDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_MAX_SLNO.HRGT_EPISODE_DRUG_SCHEDULE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), drugSheduleDtlVO.getPatCrNo());
		populateMAP.put(sq.next(), drugSheduleDtlVO.getEpisodeCode());
		populateMAP.put(sq.next(), drugSheduleDtlVO.getEpisodeVisitNo());
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			return rs.getString(1);			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		
	}
	
	public void updateSchedule(DrugSheduleDtlVO vo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_REVOKE.HRGT_EPISODE_DRUG_SCHEDULE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), vo.getPatCrNo());
			populateMAP.put(sq.next(), vo.getEpisodeCode());
			populateMAP.put(sq.next(), vo.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), vo.getItemTypeId());
			populateMAP.put(sq.next(), vo.getSerialNo());
			populateMAP.put(sq.next(), vo.getSheduleSerialNo());
			populateMAP.put(sq.next(), vo.getEntryDate());
						
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"PatDrugTreatmentDetailDAO.getPatientEpisodeClinicalData::populateMAP " + e);
		}

		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	
}
