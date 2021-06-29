package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class RequestRecordDtlDAO extends DataAccessObject implements RequestRecordDtlDAOi
{
	public RequestRecordDtlDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void create(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_REQ_RECORD_DTL";
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
        	populateMAP.put(sq.next(), requestRecordDtlVO.getRequestId());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getMrdRecordId());
        	populateMAP.put(sq.next(), "1");	//requestRecordDtlVO.getSlNo());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getRecordType());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getRemarks());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getPatAdmNo());
        	//populateMAP.put(sq.next(), requestRecordDtlVO.getReqStatus());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());	//approved By
        	populateMAP.put(sq.next(), requestRecordDtlVO.getDeptUnit());//added by sandip naik on 25/7/17
        	populateMAP.put(sq.next(), userVO.getHospitalCode());        	
        	populateMAP.put(sq.next(), requestRecordDtlVO.getMrdCode());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getReqPurposeId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), requestRecordDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getCancelReason());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());	//approved By
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getDeptUnit());//added by sandip naik on 25/7/17

        		
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
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
	
	//Online
	public void onlineInsert(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey = "";
        if(requestRecordDtlVO.getDESIGNATED_APPROVED_BY().equalsIgnoreCase("Self Approved"))
        {
        	 queryKey="INSERT_ONLINE_APPROVED.HPMRT_REQ_RECORD_DTL";
        }
        else
        {
        	queryKey="INSERT_ONLINE.HPMRT_REQ_RECORD_DTL";
        }
         
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
        	populateMAP.put(sq.next(), requestRecordDtlVO.getRequestId());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getMrdRecordId());
        	populateMAP.put(sq.next(), "1");	//requestRecordDtlVO.getSlNo());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getRecordType());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getRemarks());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getPatAdmNo());
        	//added by sandip naik on 25/7/17
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getDeptUnit());//added by sandip naik on 20/7/17
        	populateMAP.put(sq.next(), userVO.getHospitalCode());//end by sandip naik
        	
        	//populateMAP.put(sq.next(), requestRecordDtlVO.getReqStatus());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getMrdCode());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getReqPurposeId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), requestRecordDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), requestRecordDtlVO.getDeptUnit());//added by sandip naik on 20/7/17

        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
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
	
	
	
	public void update(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="UPDATE.HPMRT_REQ_RECORD_DTL";
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
			populateMAP.put(sq.next(), requestRecordDtlVO.getReqStatus());
			populateMAP.put(sq.next(), requestRecordDtlVO.getCancelReason());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), requestRecordDtlVO.getRequestId());
			populateMAP.put(sq.next(), requestRecordDtlVO.getMrdRecordId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
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
	
	public void updateRejectDetail(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="UPDATE.REJECT_DETAIL.HPMRT_REQ_RECORD_DTL";
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
			populateMAP.put(sq.next(), requestRecordDtlVO.getReqStatus());
			populateMAP.put(sq.next(), requestRecordDtlVO.getCancelReason());
			populateMAP.put(sq.next(), requestRecordDtlVO.getRequestId());
			populateMAP.put(sq.next(), requestRecordDtlVO.getMrdRecordId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
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
	
	public void updateRequestStatus(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="UPDATE.REQUEST_STATUS.HPMRT_REQ_RECORD_DTL";
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
			populateMAP.put(sq.next(), requestRecordDtlVO.getReqStatus());
			populateMAP.put(sq.next(), requestRecordDtlVO.getRequestId());
			populateMAP.put(sq.next(), requestRecordDtlVO.getMrdRecordId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
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
	
	//select all record request by reqID 
	public List<RequestRecordDtlVO> selectAllRecordByRequestID(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.BY_REQ_ID.HPMRT_REQ_RECORD_DTL";
		List <RequestRecordDtlVO> requestRecordDtlVOList=null;
		ResultSet rs=null;
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			//populateMAP.put(sq.next(), requestRecordDtlVO.getRequestId());commented by sandip naik on 20/7/17
			populateMAP.put(sq.next(), requestRecordDtlVO.getRecordId());//added by sandip naik on 20/7/17
			populateMAP.put(sq.next(), requestRecordDtlVO.getReqStatus());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(),requestRecordDtlVO.getRequestId() );

			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);commented by sandip naik on 15/06/17
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Request Found For Approval");
			}
			else{
				rs.beforeFirst();
				requestRecordDtlVOList=new ArrayList<RequestRecordDtlVO>();
				while (rs.next()) {
					requestRecordDtlVO=new RequestRecordDtlVO();
					HelperMethods.populateVOfrmRS(requestRecordDtlVO, rs);
					requestRecordDtlVOList.add(requestRecordDtlVO);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException While fetching");
			}	
		}
		
		return requestRecordDtlVOList;
	}
	
	public List<RequestRecordDtlVO> getPendingRecordRequestStatus(String requestId,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.PENDING_REQUEST_DETAIL.HPMRT_REQ_RECORD_DTL";
		List <RequestRecordDtlVO> requestRecordDtlVOList=null;
		ResultSet rs=null;
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), requestId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RequestRecordDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found ");
			}
			else
			{
				rs.beforeFirst();
				requestRecordDtlVOList=new ArrayList<RequestRecordDtlVO>();
				while (rs.next())
				{
					RequestRecordDtlVO requestRecordDtlVO=new RequestRecordDtlVO();
					HelperMethods.populateVOfrmRS(requestRecordDtlVO, rs);
					requestRecordDtlVOList.add(requestRecordDtlVO);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException While fetching");
			}	
		}
		
		return requestRecordDtlVOList;
	}
	
}
