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
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

public class YellowSlipEntryDAO extends DataAccessObject implements YellowSlipEntryDAOi{

	Logger log;
	
	public YellowSlipEntryDAO(TransactionContext _tx) {
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
		
	/**
	 * saves the yellow slip entry detail. 
	 */
	
	public void save(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO) {
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_YELLOWSLIP_ENTRY_DTL";
		
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
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeIsOpen());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getComplainDetail());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEmpNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeNextVisitDate());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getDiagnosticTypeCode());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getDiagnosticCode());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEntryMode());
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

	public void updateIsValid(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO) {
		
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.IS_VALID.HRGT_YELLOWSLIP_ENTRY_DTL";
		
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
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), yellowSlipEntryDtlVO.getSerialNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
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
	 * Getting the Yellow Slip Entry by user
	 */
	
	public List getYellowSlipEntryByUser(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO) {
		
		List yellowSlipEntryVOList=new ArrayList();
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.YELLOW_SLIP_ENTRY_BY_USER.HRGT_YELLOWSLIP_ENTRY_DTL";
		
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
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getFromDate());
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getToDate());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("YellowSlipEntryDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else{
				rs.beforeFirst();
				while(rs.next()){
					yellowSlipEntryDtlVO=new YellowSlipEntryDtlVO();
					HelperMethods.populateVOfrmRS(yellowSlipEntryDtlVO, rs);
					yellowSlipEntryVOList.add(yellowSlipEntryDtlVO);
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
		return yellowSlipEntryVOList;
	}
	
	/**
	 * Getting the Yellow Slip Entry by CrNo, Episode No. and visit no. 
	 */
	
	public EpisodeVO getYellowSlipEntryByCRNoEpisodeNo(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO) {
		
		EpisodeVO episodeVO=new EpisodeVO();
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.YELLOW_SLIP_ENTRY.BY_CRNO_EPISODE_NO";
		
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
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getPatCrNo());
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("YellowSlipEntryDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			HelperMethods.populateVOfrmRS(episodeVO, rs);
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Diagnosis Found for the Cr No. "+yellowSlipEntryDtlVO.getPatCrNo());
			}
			rs.beforeFirst();*/
			
			
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
		return episodeVO;
	}
	
	
	/**
	 * Getting the Yellow Slip Entry Diagnosis Detail by CrNo, Episode No. and visit no. 
	 */
	
	public List <EpisodeDiagnosisVO> getYellowSlipEntryDiagnosisDtl(YellowSlipEntryDtlVO yellowSlipEntryDtlVO,UserVO userVO) {
		
		List <EpisodeDiagnosisVO> episodeDiagnosisVOList=new ArrayList<EpisodeDiagnosisVO>();
		EpisodeDiagnosisVO episodeDiagnosisVO=new EpisodeDiagnosisVO();
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.YELLOW_SLIP_DIAGNOSIS_DTL.BY_CRNO_EPISODE_NO";
		
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
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getPatCrNo());
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(),yellowSlipEntryDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("YellowSlipEntryDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while(rs.next()){
				episodeDiagnosisVO=new EpisodeDiagnosisVO();
				HelperMethods.populateVOfrmRS(episodeDiagnosisVO, rs);
				episodeDiagnosisVOList.add(episodeDiagnosisVO);
			}
			/*if(!rs.next()){
				throw new HisRecordNotFoundException("No Diagnosis Found for the Cr No. "+yellowSlipEntryDtlVO.getPatCrNo());
			}
			rs.beforeFirst();*/
			
			
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
		return episodeDiagnosisVOList;
	}
	
	
	
}
