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
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class MrdRecordIssueDtlDAO extends DataAccessObject implements MrdRecordIssueDtlDAOi
{
	public MrdRecordIssueDtlDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void create(MrdRecordIssueDtlVO mrdRecordIssueDtlDAO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_MRDRECORD_ISSUE_DTL";
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
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getRequestId());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getRecordType());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getMrdCode());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getRemarks());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getHandoverTo());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getExpectedReturnDate());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getHandoverToName());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::"+e);
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
	}//end create
	
	
	public List<MrdRecordIssueDtlVO> selectByRequestId(MrdRecordIssueDtlVO mrdRecordIssueDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.BY_REQID.HPMRT_MRDRECORD_ISSUE_DTL";
		ResultSet rs;
		List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList=null;
		//MrdRecordIssueDtlVO mrdRecordIssueDtlVO=null;
		
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
			populateMAP.put(sq.next(), mrdRecordIssueDtlVO.getRequestId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Record Found");
			}
			else{
				rs.beforeFirst();
				mrdRecordIssueDtlVOList=new ArrayList<MrdRecordIssueDtlVO>();
				while (rs.next()) {
					mrdRecordIssueDtlVO=new MrdRecordIssueDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordIssueDtlVO, rs);
					mrdRecordIssueDtlVOList.add(mrdRecordIssueDtlVO);
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
				throw new HisDataAccessException("HisDataAccessException While ADDING");
			}
		}
		
		return mrdRecordIssueDtlVOList;
	}//end selectByRequestId
	
	public List<MrdRecordIssueDtlVO> selectByMrdRecordId(MrdRecordIssueDtlVO mrdRecordIssueDtlVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.BY_RECORD_ID.HPMRT_MRDRECORD_ISSUE_DTL";
		ResultSet rs;
		List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList=null;
		///MrdRecordIssueDtlVO mrdRecordIssueDtlVO=null;
		
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), mrdRecordIssueDtlVO.getMrdRecordId());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}
			else{
				rs.beforeFirst();
				mrdRecordIssueDtlVOList=new ArrayList<MrdRecordIssueDtlVO>();
				while (rs.next()) {
					mrdRecordIssueDtlVO=new MrdRecordIssueDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordIssueDtlVO, rs);
					mrdRecordIssueDtlVOList.add(mrdRecordIssueDtlVO);
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
				throw new HisDataAccessException("HisDataAccessException While ADDING");
			}
		}
		
		return mrdRecordIssueDtlVOList;
	}//end selectByRequestId
	
	
	
	public List<MrdRecordIssueDtlVO> getReturnedMrdRecordListByRequestId(String requestId,String recordId,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT_ISSUED_MRD_RECORD_BY_REQ_ID.HPMRT_MRDRECORD_ISSUE_DTL";
		ResultSet rs;
		List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList=null;
		
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
			/*populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 
			populateMAP.put(sq.next(), recordId);
			populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_ISSUED);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			*/
			
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), requestId);
			populateMAP.put(sq.next(), recordId);
			
		
			
			
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}
			else{
				rs.beforeFirst();
				mrdRecordIssueDtlVOList=new ArrayList<MrdRecordIssueDtlVO>();
				while (rs.next()) 
				{
					MrdRecordIssueDtlVO mrdRecordIssueDtlVO=new MrdRecordIssueDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordIssueDtlVO, rs);
					mrdRecordIssueDtlVOList.add(mrdRecordIssueDtlVO);
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
				throw new HisDataAccessException("HisDataAccessException While ADDING");
			}
		}
		
		return mrdRecordIssueDtlVOList;
	}
	
	public void createExtended(MrdRecordIssueDtlVO mrdRecordIssueDtlDAO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_MRDRECORD_ISSUE_DTL";
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
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getRequestId());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getRecordType());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getMrdCode());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getRemarks());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getHandoverTo());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getExpectedReturnDate());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), mrdRecordIssueDtlDAO.getHandoverToName());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::"+e);
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
	}//end create
	
}//end class
