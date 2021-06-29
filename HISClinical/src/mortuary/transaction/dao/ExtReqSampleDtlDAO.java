package mortuary.transaction.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.lowagie.tools.concat_pdf;

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
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ExtReqSampleDtlDAO extends DataAccessObject implements ExtReqSampleDtlDAOi
{
	public ExtReqSampleDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting The Record
	public void create(MortuaryExtReqSampleDtlVO extReqSampleDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_EXTREQ_SAMPLE_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), extReqSampleDtlVO.getRequestId());
	    	populateMAP.put(sq.next(), extReqSampleDtlVO.getItemCode());
	    	populateMAP.put(sq.next(), extReqSampleDtlVO.getStatus());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), extReqSampleDtlVO.getReceiveRemarks());
	    	populateMAP.put(sq.next(), extReqSampleDtlVO.getRequestId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	
	    	
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("ExtReqSampleDtlDAO.populateMAP::"+e);
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
	
	public MortuaryExtReqSampleDtlVO[] getRequestedSampleByRequestId(String requestId,UserVO userVO)
	{
		MortuaryExtReqSampleDtlVO[] arrExtReqSampleDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "GET_REQUESTED_SAMPLE_DETAIL_BY_REQUESTID.HMRT_EXTREQ_SAMPLE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), requestId);
		populateMAP.put(sq.next(), MortuaryConfig.EXTERNAL_REQUEST_SAMPLE_STATUS_SEND);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Sample Send To External Lab");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MortuaryExtReqSampleDtlVO.class, rs);
				arrExtReqSampleDtlVO = new MortuaryExtReqSampleDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrExtReqSampleDtlVO[i] = (MortuaryExtReqSampleDtlVO) vo[i];
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
		return arrExtReqSampleDtlVO;
	}
	
	public void updateReceiveRemarks(MortuaryExtReqSampleDtlVO extReqSampleDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_RECEIVED_REMARKS.HMRT_EXTREQ_SAMPLE_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), extReqSampleDtlVO.getStatus());
		populateMAP.put(sq.next(), extReqSampleDtlVO.getReceiveRemarks());
		populateMAP.put(sq.next(), extReqSampleDtlVO.getRequestId());
		populateMAP.put(sq.next(), extReqSampleDtlVO.getItemCode());
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
}
