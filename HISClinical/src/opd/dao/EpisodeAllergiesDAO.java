package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import opd.OpdConfig;
import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class EpisodeAllergiesDAO extends DataAccessObject
{

	public EpisodeAllergiesDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void create(EpisodeAllergiesVO _episodeAllergyVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HRGT_EPISODE_ALLERGY_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), _episodeAllergyVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getPatCrNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergiesCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getPatAdmNo());
			populateMAP.put(sq.next(), _episodeAllergyVO.getRemarks());
			populateMAP.put(sq.next(), _episodeAllergyVO.getReasonCode());
			populateMAP.put(sq.next(), _episodeAllergyVO.getSensitivityCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _episodeAllergyVO.getReasonName());// allergy Name
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySymtoms());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySite());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAdvice());
			populateMAP.put(sq.next(), _episodeAllergyVO.getAllergyTypeName());
			populateMAP.put(sq.next(), _episodeAllergyVO.getDuration());
			populateMAP.put(sq.next(), _episodeAllergyVO.getIsRevoked());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EpisodeAllergiesDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}

	}

	/*
	 * public EpisodeAllergiesVO[] getEpisodeAllergiesByPatient(EpisodeAllergiesVO _episodeAllergyVO,UserVO _userVO){
	 * 
	 * EpisodeAllergiesVO[] _previousEpisodeAllergyVO= null; ValueObject vo[]= null; String query = ""; Map populateMAP =new
	 * HashMap(); Sequence sq=new Sequence(); String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO; String
	 * queryKey="SELECT.PREVIOUS.ALLERGIES.HRGT_EPISODE_ALLERGIES_DTL";
	 * 
	 * 
	 * try{ query =HelperMethodsDAO.getQuery(filename,queryKey);
	 *  } catch(Exception e){ throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR
	 * getting query out of property file"+e); }
	 * 
	 * 
	 * populateMAP.put(sq.next(),_episodeAllergyVO.getPatCrNo()); populateMAP.put(sq.next(),_userVO.getHospitalCode()); //
	 * populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeCode()); //
	 * populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeVisitNo());
	 * 
	 * 
	 * try{ ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	 * if(!rs.next()){ throw new HisRecordNotFoundException("No record for previous allergies found"); } rs.beforeFirst();
	 * 
	 * vo=HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs); _previousEpisodeAllergyVO= new
	 * EpisodeAllergiesVO[vo.length]; for(int i=0;i<vo.length;i++) { _previousEpisodeAllergyVO[i]= (EpisodeAllergiesVO)
	 * vo[i]; }
	 * 
	 *  } catch(Exception e){ if(e.getClass()==HisRecordNotFoundException.class){ throw new
	 * HisRecordNotFoundException(e.getMessage()); } else throw new HisDataAccessException("Application Execution
	 * Exception"+e); }
	 * 
	 * return _previousEpisodeAllergyVO;
	 *  }
	 */

	

	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWise(String selAllergyID, String selAllergyCode, DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_ALL_ALLERGY_EPISODE.HRGT_EPISODE_ALLERGY_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
	//	populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		populateMAP.put(sq.next(), selAllergyCode);
		populateMAP.put(sq.next(), selAllergyID);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for previous allergies found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs);
			arrEpisodeAllergyDtlVO = new EpisodeAllergiesVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeAllergyDtlVO[i] = (EpisodeAllergiesVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpisodeAllergyDtlVO;
	}
	
	
	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWiseVisitWise(EpisodeAllergiesVO _episodeAllergiesVO, UserVO _userVO)
	{
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_ALLERGY_DTL.BY_CRNO_EPISODE_VISIT";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _episodeAllergiesVO.getPatCrNo());
		populateMAP.put(sq.next(), _episodeAllergiesVO.getEpisodeCode());
		populateMAP.put(sq.next(), _episodeAllergiesVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Allergies Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs);
			arrEpisodeAllergyDtlVO = new EpisodeAllergiesVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeAllergyDtlVO[i] = (EpisodeAllergiesVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpisodeAllergyDtlVO;
	}
	
	public EpisodeAllergiesVO[] getAllergyDtlAll(EpisodeAllergiesVO _episodeAllergiesVO, String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HRGT_EPISODE_ALLERGY_DTL.ALL_EMR";
		String orderBy=" ORDER BY episodeisopen,hrgnum_isrevoked,GDT_ENTRY_DATE desc,INITCAP (hgstr_allergy_name),hgnum_allergy_type_code" ;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = a.hrgnum_episode_code "
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid AND "
						+ "gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), _episodeAllergiesVO.getPatCrNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Allergies Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeAllergiesVO.class, rs);
			arrEpisodeAllergyDtlVO = new EpisodeAllergiesVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeAllergyDtlVO[i] = (EpisodeAllergiesVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrEpisodeAllergyDtlVO;
	}
}
