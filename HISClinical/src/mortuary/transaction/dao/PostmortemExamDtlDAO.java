package mortuary.transaction.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.PostmortemExamDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PostmortemExamDtlDAO extends DataAccessObject implements PostmortemExamDtlDAOi
{
	public PostmortemExamDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void create(PostmortemExamDtlVO postmortemExamDtlVO,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_EXAM_DTL";
	    
	   
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), postmortemExamDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemExamDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemExamDtlVO.getTemplateId());
	    	populateMAP.put(sq.next(), postmortemExamDtlVO.getParaValue());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), postmortemExamDtlVO.getParaId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemExamDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HisDataAccessException While ADDING");
	    }
	}
	
	public Map<String, Map<String, String>> getTemplateDetail(String postmortemId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
	    String queryKey="SELECT_BY_POSTMORTEMID.HMRT_POSTMORTEM_EXAM_DTL";
		Sequence sq = new Sequence();
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
			populateMAP.put(sq.next(), postmortemId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PostmortemExamDtlDAO.getPatientClinicalData::populateMAP " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		Map<String, Map<String, String>> mpParaTempValues = new HashMap<String, Map<String,String>>();
		ValueObject[] vo = {};
		GenericTemplateUtility.TempParameter tempParas[] = null;
		
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);				
				tempParas = new GenericTemplateUtility.TempParameter[vo.length];
				for (int i = 0; i < vo.length; i++)
					tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
				{
					Map<String, String> map = null;
					if(mpParaTempValues.get(paraValVO.getTemplateId())!=null)
						map = mpParaTempValues.get(paraValVO.getTemplateId());
					else
						map = new HashMap<String, String>();
					map.put(paraValVO.getParaId(), paraValVO.getParaValue());
					mpParaTempValues.put(paraValVO.getTemplateId(), map);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return mpParaTempValues;
	}
	
	public void updateValue(PostmortemExamDtlVO postmortemExamDtlVO,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="UPDATE.HMRT_POSTMORTEM_EXAM_DTL";
	    
	    try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), postmortemExamDtlVO.getParaValue());
		populateMAP.put(sq.next(), postmortemExamDtlVO.getPostmortemId());
		populateMAP.put(sq.next(), postmortemExamDtlVO.getTemplateId());
		populateMAP.put(sq.next(), postmortemExamDtlVO.getParaId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void invalidTheRecord(String postmortemId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="UPDATE_INVALID_STATUS.HMRT_POSTMORTEM_EXAM_DTL";
	    
	    try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
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
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
}
