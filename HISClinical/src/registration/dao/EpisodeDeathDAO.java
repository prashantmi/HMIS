package registration.dao;

import inpatient.InpatientConfig;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import registration.RegistrationConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeDeathVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.UserVO;

public class EpisodeDeathDAO extends DataAccessObject implements EpisodeDeathDAOi
{

	public EpisodeDeathDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void create(EpisodeDeathVO _episodeDeathVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_DEATH_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		//call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			_episodeDeathVO.setSeatId(_userVO.getSeatId());

			Sequence sq = new Sequence();

			populateMAP.put(sq.next(), _episodeDeathVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeDeathVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _episodeDeathVO.getAdmissionNo());
			populateMAP.put(sq.next(), _episodeDeathVO.getCurrentDepartmentCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getCurrentUnitCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getCurrentWardCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getBedCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getDeathDate());
			populateMAP.put(sq.next(), _episodeDeathVO.getDeathTime());
			populateMAP.put(sq.next(), _episodeDeathVO.getDeathCause());
			populateMAP.put(sq.next(), _episodeDeathVO.getAttendConsultantId());
			populateMAP.put(sq.next(), _episodeDeathVO.getConsultantRemark());
			populateMAP.put(sq.next(), _episodeDeathVO.getBodyHandOverTo());
			populateMAP.put(sq.next(), _episodeDeathVO.getBodyHandOverDate());
			populateMAP.put(sq.next(), _episodeDeathVO.getBodyHandOverTime());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatRelativeName());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatRelativeAddress());
			populateMAP.put(sq.next(), _episodeDeathVO.getRelativeCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _episodeDeathVO.getSeatId());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatFirstName());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatMiddleName());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatLastName());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatAge());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatGenderCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatPrimaryCatCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatSecondaryCatCode());
			populateMAP.put(sq.next(), _episodeDeathVO.getEpisodeDate());
			populateMAP.put(sq.next(), _episodeDeathVO.getPatGuardianName());
			populateMAP.put(sq.next(), _episodeDeathVO.getMannerOfDeath());
			populateMAP.put(sq.next(), _episodeDeathVO.getDeathverificationDate());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"EpisodeDeathDAO create::HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		System.out.println("query" + query);

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED::EpisodeDeathDAO:" + e);
		}
	}//END OF CREATE

	public void populateMap(EpisodeDeathVO _episodeDeathVO, Map _populateMAP) throws HisApplicationExecutionException
	{
		System.out.println("inside populateMap" + _episodeDeathVO);
		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), _episodeDeathVO.getPatCrNo());
		_populateMAP.put(sq.next(), _episodeDeathVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), _episodeDeathVO.getAdmissionNo());
		_populateMAP.put(sq.next(), _episodeDeathVO.getCurrentDepartmentCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getCurrentUnitCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getCurrentWardCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getBedCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getDeathDate());
		_populateMAP.put(sq.next(), _episodeDeathVO.getDeathTime());
		_populateMAP.put(sq.next(), _episodeDeathVO.getDeathCause());
		_populateMAP.put(sq.next(), _episodeDeathVO.getAttendConsultantId());
		_populateMAP.put(sq.next(), _episodeDeathVO.getConsultantRemark());
		_populateMAP.put(sq.next(), _episodeDeathVO.getBodyHandOverTo());
		_populateMAP.put(sq.next(), _episodeDeathVO.getBodyHandOverDate());
		_populateMAP.put(sq.next(), _episodeDeathVO.getBodyHandOverTime());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatRelativeName());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatRelativeAddress());
		_populateMAP.put(sq.next(), _episodeDeathVO.getRelativeCode());
		_populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMAP.put(sq.next(), _episodeDeathVO.getSeatId());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatFirstName());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatLastName());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatAge());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _episodeDeathVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _episodeDeathVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _episodeDeathVO.getMannerOfDeath());
		_populateMAP.put(sq.next(), _episodeDeathVO.getDeathverificationDate());
		System.out.println("inside populateMap222");
	}

	public EpisodeDeathVO getDeathDtl(EpisodeVO _episodeVO, UserVO _userVO)
	{
		EpisodeDeathVO episodeDeathVO = new EpisodeDeathVO();

		System.out.println("inside getActionDtl  ");
		Map _populateMapActionDtl = new HashMap();

		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_DEATH_DTL";
		Sequence sq = new Sequence();

		_populateMapActionDtl.put(sq.next(), _episodeVO.getEpisodeCode());
		_populateMapActionDtl.put(sq.next(), _episodeVO.getEpisodeVisitNo());
		_populateMapActionDtl.put(sq.next(), _episodeVO.getPatCrNo());
		_populateMapActionDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapActionDtl);
			System.out.println("hello........" + rs);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(episodeDeathVO, rs);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("MlcDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return episodeDeathVO;
	}

	
	
	public void updateTreatementCatCode(JSYRegitrationVO jRegitrationVO,String jsyCategoryCode,UserVO _userVO){
		String query  = "";
	   Map populateMAP =new HashMap();
	   String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	   String queryKey="UPDATE_TREATEMENT_CAT.HRGT_EPISODE_DTL";
	   
	   try{
	       query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }     
	   catch(Exception e){
	   	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   System.out.println("query"+query);
	   try{    	  
		      Sequence sq=new Sequence();
		      populateMAP.put(sq.next(), jsyCategoryCode);
		      populateMAP.put(sq.next(), jRegitrationVO.getPatCrNo());
		      populateMAP.put(sq.next(), jRegitrationVO.getEpisodeCode());
		      populateMAP.put(sq.next(), jRegitrationVO.getVisitNo());
		      populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		      populateMAP.put(sq.next(),_userVO.getHospitalCode());
	   }
	   catch(Exception e){
	   	throw new HisApplicationExecutionException("EpisodeDeathDAO:updateexpiry()::"+e);
	   }
	   try{
	   	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	   }
	   catch(Exception e){
	   	e.printStackTrace();
	   	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
	   }     
 }

}//END OF CLASS

