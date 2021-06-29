package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class MrdRecordReturnDtlDAO extends DataAccessObject implements MrdRecordReturnDtlDAOi
{
	public MrdRecordReturnDtlDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void create(MrdRecordReturnDtlVO mrdRecordReturnDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_MRDRECORD_RETURN_DTL";
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
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getRequestId());
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getRecordType());
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getMrdCode());
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getRemarks());
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getReturnBy());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getReturnByName());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MrdRecordReturnDtlDAO.populateMAP::"+e);
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
