package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;
import hisglobal.vo.YellowSlipMonitoringVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

public class YellowSlipMonitoringDAO extends DataAccessObject implements YellowSlipMonitoringDAOi{

	Logger log;
	
	public YellowSlipMonitoringDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
		
	/**
	 * saves the yellow slip entry detail. 
	 */
	
	public void save(YellowSlipMonitoringVO YellowSlipMonitoringVO,UserVO userVO) {
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_YELLOWSLIP_MONITORING_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getEnterBy());
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getPatCrNo());
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getEpisodeCode());
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getMonitoringFlag());
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getMonitoringRemarks());
			populateMAP.put(sq.next(), YellowSlipMonitoringVO.getAction());
			populateMAP.put(sq.next(), userVO.getIpAddress());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("YellowSlipEntryDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}

	/**
	 * Getting the list of user who entered the yellow slip
	 */
	
	public List getYellowSlipEntryUserList(String fromDate,String toDate,UserVO userVO) {
		
		List yellowSlipEntryList=new ArrayList();
		YellowSlipMonitoringVO yellowSlipMonitoringVO=new YellowSlipMonitoringVO();
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.YELLOW_SLIP_ENTRY_USER.BY_DATE";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.YELLOW_SLIP_MONITORING_FLAG_MINOR_ERROR);
			populateMAP.put(sq.next(), RegistrationConfig.YELLOW_SLIP_MONITORING_FLAG_MAJOR_ERROR);
			populateMAP.put(sq.next(), fromDate);
			populateMAP.put(sq.next(), toDate);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("YellowSlipEntryDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Yellow Slip Entry User Found");
			}
			else{
				rs.beforeFirst();
				while(rs.next()){
					yellowSlipMonitoringVO=new YellowSlipMonitoringVO();
					HelperMethods.populateVOfrmRS(yellowSlipMonitoringVO, rs);
					yellowSlipEntryList.add(yellowSlipMonitoringVO);
				}
			}
		}
		catch (Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}	
		}
		return yellowSlipEntryList;
	}
	
	
}
