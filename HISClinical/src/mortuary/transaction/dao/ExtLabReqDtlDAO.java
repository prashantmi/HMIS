package mortuary.transaction.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ExtLabReqDtlDAO extends DataAccessObject implements ExtLabReqDtlDAOi
{
	public ExtLabReqDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting The Record
	public void create(MortuaryExtLabRequestDtlVO extLabRequestDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_EXTLAB_REQ_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getRequestId());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getResult());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getStatus());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getExtLabId());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getPoliceStation());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getDutyOffName());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getDutyOffDesignation());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getDutyOffBatchNo());
	    	populateMAP.put(sq.next(), extLabRequestDtlVO.getRequestId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("ExtLabReqDtlDAO.populateMAP::"+e);
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
	
	public String getmaxSrNoBypostmortemId(String postmortemId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="GET_MAX_SR_NO.HMRT_EXTLAB_REQ_DTL";
	    
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
			populateMAP.put(sq.next(),postmortemId);
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
	
	public MortuaryExtLabRequestDtlVO[] getAllRequestDetail(String postmortemId,UserVO userVO)
	{
		MortuaryExtLabRequestDtlVO[] arrExtLabReqDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "GET_SAMPLE_REQUESTED_DETAIL.HMRT_EXTLAB_REQ_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.EXTERNAL_LAB_REQUESTE_STATUS_REQUEST_SEND);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Sample Send To External Lab");
				arrExtLabReqDtlVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MortuaryExtLabRequestDtlVO.class, rs);
				arrExtLabReqDtlVO = new MortuaryExtLabRequestDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrExtLabReqDtlVO[i] = (MortuaryExtLabRequestDtlVO) vo[i];
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
		return arrExtLabReqDtlVO;
	}
	
	public void updateFinalResult(MortuaryExtLabRequestDtlVO extLabRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_FINAL_RESULT.HMRT_EXTLAB_REQ_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), extLabRequestDtlVO.getResult());
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), extLabRequestDtlVO.getStatus());
		populateMAP.put(sq.next(), extLabRequestDtlVO.getRequestId());
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
	
	public MortuaryExtLabRequestDtlVO[] getAllReceivedReport(String postmortemId,UserVO userVO)
	{
		MortuaryExtLabRequestDtlVO[] arrExtLabReportVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "GET_RECEIVED_REPORT_DETAIL.HMRT_EXTLAB_REQ_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), postmortemId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
	//	populateMAP.put(sq.next(), MortuaryConfig.EXTERNAL_LAB_REQUESTE_STATUS_REPORT_RECEIVED);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrExtLabReportVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MortuaryExtLabRequestDtlVO.class, rs);
				arrExtLabReportVO = new MortuaryExtLabRequestDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrExtLabReportVO[i] = (MortuaryExtLabRequestDtlVO) vo[i];
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
		return arrExtLabReportVO;
	}
	
}
