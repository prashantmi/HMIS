package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientProfileCDBurnDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class PatientProfileCDBurnDAO extends DataAccessObject 
{
	public PatientProfileCDBurnDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void create(PatientProfileCDBurnDtlVO patProfileCDBurnDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_PAT_PROFILE_CDBURN_DTL";
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
        	populateMAP.put(sq.next(), patProfileCDBurnDtlVO.getProfileId());
        	populateMAP.put(sq.next(), patProfileCDBurnDtlVO.getBillNo());
        	populateMAP.put(sq.next(), patProfileCDBurnDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), patProfileCDBurnDtlVO.getRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatientProfileCDBurnDAO.populateMAP::"+e);
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
