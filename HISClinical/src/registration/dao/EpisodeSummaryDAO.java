package registration.dao;

import hisglobal.backutil.HisDAO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import opd.OpdConfig;

import mrd.MrdConfig;

import registration.RegistrationConfig;

public class EpisodeSummaryDAO extends DataAccessObject implements EpisodeSummaryDAOi
{
	public EpisodeSummaryDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	/**
	 * Saving Episode Summary Detail
	 * @param _episodeSummaryVO
	 * @param _userVO
	 */
	public void create(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.NEW.HRGT_EPISODE_SUMMARY_DTL";
		String episodeKeyword="'"+_episodeSummaryVO.getEpisodeKeywords()+"'";
		String episodeKeywordId="'"+_episodeSummaryVO.getEpisodeKeywordID()+"'";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), _episodeSummaryVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _episodeSummaryVO.getVisitNotes());					
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeNextVisitDate());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeSummary());			
			populateMAP.put(sq.next(), _episodeSummaryVO.getNextVisitCriteria());			
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			populateMAP.put(sq.next(), _episodeSummaryVO.getNextVisitDuration());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeKeywords());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeKeywordID());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED ::EpisodeSummaryDAO:: "+ e);
		}
	}

	/**
	 * Retrieves All Visit Summary of Current Visit
	 * @param	_episodeVO Epispde Visit Detail
	 * @param	_userVO	User Detail
	 * @return	List of EpisodeSummaryDetailVO
	 */
	public List<EpisodeSummaryDetailVO> getAllVisitSummaryByEpisodeVisit(EpisodeVO _episodeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.ALL_SUMMARY.HRGT_EPISODE_DTL";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
			populateMap.put(sq.next(), _episodeVO.getEpisodeVisitNo());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
		}

		ValueObject[] vo ={};
		List<EpisodeSummaryDetailVO> lstEpisodeSummary = new ArrayList<EpisodeSummaryDetailVO>();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeSummaryDetailVO.class, rs);
				for(ValueObject o : vo)
					lstEpisodeSummary.add((EpisodeSummaryDetailVO)o);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
		}
		return lstEpisodeSummary;
	}
	
	
	
	public List<EpisodeVO> getVisitSummaryDetails(EpisodeVO _episodeVO, UserVO _userVO)
	{
		
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_emr_dtl.proc_retrieve_visit_summary_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);		
		daoObj.setProcInValue(nProcIndex, "cr_no", _episodeVO.getPatCrNo(),1);
		daoObj.setProcInValue(nProcIndex, "episode_code", _episodeVO.getEpisodeCode(),2);
		daoObj.setProcInValue(nProcIndex, "episode_visitno", _episodeVO.getEpisodeVisitNo(),3);
		daoObj.setProcInValue(nProcIndex, "hosp_code",_userVO.getHospitalCode(),4);
		daoObj.setProcInValue(nProcIndex, "is_valid", Config.IS_VALID_ACTIVE,5);
		daoObj.setProcOutValue(nProcIndex, "err", 1,6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		List<EpisodeVO> lstEpisodeDtl = new ArrayList<EpisodeVO>();
		try
		{
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				for(ValueObject o : valueObjects)
					lstEpisodeDtl.add((EpisodeVO)o);
			}			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return lstEpisodeDtl;
	}
	/**
	 * Updating Old Episode Summary Detail as Deleted
	 * @param _episodeSummaryVO
	 * @param _userVO
	 */
	public void updateOld(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.OLD.HRGT_EPISODE_SUMMARY_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _episodeSummaryVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeVisitNo());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UPDATE FAILED ::EpisodeSummaryDAO:: "+ e);
		}
	}

	
	/**
	 * Retrieves All Visit Summary of Current Visit
	 * @param	_episodeVO Epispde Visit Detail
	 * @param	_userVO	User Detail
	 * @return	List of EpisodeSummaryDetailVO
	 */
	public List<EpisodeSummaryDetailVO> getAllVisitSummaryByCrNo(EpisodeVO _episodeVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.ALL_SUMMARY.HRGT_EPISODE_SUMMARY_DTL.EMR";
		String orderBy=" ORDER BY x.hrgnum_visit_no DESC, x.hrgnum_visit_slno DESC";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = x.hrgnum_episode_code "
						+ "and HRGNUM_PUK=x.HRGNUM_PUK and HRGNUM_VISIT_NO=x.HRGNUM_VISIT_NO and gnum_isvalid=x.gnum_isvalid AND "
						+ "gnum_hospital_code = x.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		try
		{
			Sequence sq = new Sequence();
			
			populateMap.put(sq.next(), _episodeVO.getPatCrNo());
			//populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
		}

		ValueObject[] vo ={};
		List<EpisodeSummaryDetailVO> lstEpisodeSummary = new ArrayList<EpisodeSummaryDetailVO>();
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeSummaryDetailVO.class, rs);
				for(ValueObject o : vo)
					lstEpisodeSummary.add((EpisodeSummaryDetailVO)o);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
		}
		return lstEpisodeSummary;
	}
	
	
	
	
	
	public String select(String episodeKeywords, UserVO _userVO)
	{
		String query = "";
		String keyWordIds = "";
		Map populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.KEYWORDID.hgbt_episode_keyword_mst";
		String keyWord[]=episodeKeywords.split(",");
		int len=keyWord.length;
		System.out.println("length::::"+keyWord.length);
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		for(int i=0 ;i<len; i++)
		{
			
			try
			{
				Sequence sq = new Sequence();
				
				populateMap.put(sq.next(), keyWord[i]);
				//populateMap.put(sq.next(), _episodeVO.getEpisodeCode());
				populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.populateMap::retrieveAllVisitsByEpisodeNo::" + e);
			}
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
				
				while(rs.next())
				{
					//rs.beforeFirst();
					if(i==0)
					{
						keyWordIds=rs.getString(1);
					}
					else
					{
						keyWordIds+="#"+ rs.getString(1);
					}					
				}
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("EpisodeDAO:retrieveAllVisitsByEpisodeNo::Episode Visit Details:: " + e);
			}
		}
		return keyWordIds;
	}
	
	
	public void updateEpisodeSummaryDtl(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.NEW.HRGT_EPISODE_SUMMARY_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), _episodeSummaryVO.getVisitNotes());					
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeNextVisitDate());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeSummary());			
			populateMAP.put(sq.next(), _episodeSummaryVO.getNextVisitCriteria());			
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			populateMAP.put(sq.next(), _episodeSummaryVO.getNextVisitDuration());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeKeywords());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeKeywordID());			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _episodeSummaryVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeSummaryVO.getEpisodeVisitNo());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED ::EpisodeSummaryDAO:: "+ e);
		}
	}
	
	
	
	
	
	/*public String select(String episodeKeywords, UserVO _userVO)
	{
		String keyWordIds = "";
		//ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.KEYWORDID.hgbt_episode_keyword_mst";
		EpisodeSummaryDetailVO  episodeSummaryDtlVo=new EpisodeSummaryDetailVO();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), episodeKeywords);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdGroupMasterDAO.populateMAP::" + e);
		}

		try
		{
             ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//HelperMethods.populateVOfrmRS(EpisodeSummaryDetailVO.class, rs);
             if (rs.next())
			{
				rs.beforeFirst();
				keyWordIds =keyWordIds+"#"+ rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return keyWordIds;
	}*/
	
	/*public void populateMap(EpisodeCloseVO _episodeCloseVO, Map _populateMAP) throws HisApplicationExecutionException
	{
		System.out.println("inside populateMap" + _episodeCloseVO);
		Sequence sq = new Sequence();
		_populateMAP.put(sq.next(), _episodeCloseVO.getPatCrNo());
		System.out.println("getPatCrNo" + _episodeCloseVO.getPatCrNo());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), _episodeCloseVO.getAdmissionNo());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCloseType());
		_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		System.out.println("inside populateMap222");
	}

	public EpisodeCloseVO[] getPatientEpisodeDtl(String crNo, UserVO _userVO, PatientVO _patientVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		ValueObject[] vo =
		{};
		String fileName = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.CLOSE.EPISODE.HRGT_EPISODE_CLOSE_DTL";

		EpisodeCloseVO[] _episodeCloseVO;
		try
		{
			query = HelperMethodsDAO.getQuery(fileName, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), crNo);

			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisEpisodeClosedException(" No Close Episode Found For This C.R.Number ");
		}
		catch (HisEpisodeClosedException e)
		{
			throw new HisEpisodeClosedException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		try
		{
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(EpisodeCloseVO.class, rs);
			_episodeCloseVO = new EpisodeCloseVO[vo.length];

			for (int i = 0; i < vo.length; i++)
			{
				_episodeCloseVO[i] = (EpisodeCloseVO) vo[i];
			}
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeCloseDAO:getPatientEpisodeDtl::Episode close dtls" + e);
		}
		return _episodeCloseVO;

	}
	
	
	public void update(EpisodeCloseVO _episodeCloseVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_EPISODE_CLOSE_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _episodeCloseVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeCloseVO.getEpisodeCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UPDATE FAILED ::EpisodeCloseDAO:populateMap(_dailyPatientVO,psDailyPatDtl)::"+ e);
		}
	}*/
}
