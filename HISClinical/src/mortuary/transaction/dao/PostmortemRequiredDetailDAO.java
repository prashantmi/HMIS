package mortuary.transaction.dao;

import java.sql.Connection;
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
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PostmortemRequiredDetailDAO extends DataAccessObject implements PostmortemRequiredDetailDAOi
{
	public PostmortemRequiredDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Generating Postmortem Id
	public String generatePostmortemId(PostmortemRequestDetailVO postmortmReqDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="GENERATE_POSTMORTEM_ID.HMRT_POSTMORTEM_REQ_DTL";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),postmortmReqDtlVO.getDeceasedNo());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	
	//Inserting Record
	public void create(PostmortemRequestDetailVO postmortmReqDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_REQ_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPatMlcNo());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getCaseNo());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPostmortemStatus());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPoliceStation());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDocketNo());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getOfficerIncharge());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getIoDesignation());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getIoBatchNo());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDutyOffName());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDutyOffDesignation());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDutyOffBatchNo());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getCaseRemarks());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDeathDate());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getIncidenceDate());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getDeathPlace());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getIsRepeat());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getRepeatReason());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getApprovedBy());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getConductBy());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPostmortemType());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getPostmortemReqType());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getConsentStatus());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getAutopsyType());
	    	populateMAP.put(sq.next(), postmortmReqDtlVO.getCancelReason());
	    	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemRequiredDetailDAO.populateMAP::"+e);
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
	
	
	public PostmortemRequestDetailVO[] getPostmortemReqList(UserVO userVO)
	{
		PostmortemRequestDetailVO[] arrPostmortemReqListVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "GET_POSTMORTEM_REQUEST_LIST.HMRT_POSTMORTEM_REQ_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_REQ_TYPE_BODY);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_RAISED);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_APPROVED);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_APPROVED);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_TYPE_FORENSIC);
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrPostmortemReqListVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(PostmortemRequestDetailVO.class, rs);
				arrPostmortemReqListVO = new PostmortemRequestDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrPostmortemReqListVO[i] = (PostmortemRequestDetailVO) vo[i];
				}
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
		return arrPostmortemReqListVO;
	}
	
	public PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO)
	{
		PostmortemRequestDetailVO postmortemReqVO=new PostmortemRequestDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey="GET_POSTMORTEM_REQUEST_DTL.HMRT_POSTMORTEM_REQ_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(), postmortemId);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				postmortemReqVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(postmortemReqVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return postmortemReqVO;
	}
	
	public void updatePreviousRow(PostmortemRequestDetailVO postmortmReqDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_ESSENTIALDAO;
		String queryKey = "UPDATE.HMRT_POSTMORTEM_REQ_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), postmortmReqDtlVO.getPostmortemId());
		populateMAP.put(sq.next(), postmortmReqDtlVO.getSrNo());
		
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
	
	public void updatePostmortemStatus(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_POSTMORTEM_STATUS.HMRT_POSTMORTEM_REQ_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_WAVEOFF);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getPostmortemId());
		populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getPostmortemId());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
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
	
	public String getPostmortemStatusForHandover(String deceasedNo,UserVO userVO)
	{
		String query  = "";
		String str="";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="GET_POSTMORTEM_STATUS_FOR_HANDOVER.HMRT_POSTMORTEM_REQ_DTL";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        	{
        		str="Not Raised@0";
        	}
        	else
        	{
        		rs.first();
        		str=rs.getString(1);
        	}
           
            
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return str;
	}
	
	public void updatePostmortemStatusCompleted(String postmortemId,String status, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_POSTMORTEM_STATUS_COMPLETED.HMRT_POSTMORTEM_REQ_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), status);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
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
