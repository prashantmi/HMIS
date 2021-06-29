package mrd.transaction.dao;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.UserVO;

public class MrdRackShelfChangeDtlDAO extends DataAccessObject implements MrdRackShelfChangeDtlDAOi
{
	public  MrdRackShelfChangeDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	// Inserting Data
	public void create(MrdRackShelfChangeDtlVO mrdRackShelfChangeDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_MRD_RACKSHELF_CHANGE_DTL";
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
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getToMrdCode());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getToRackId());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getToShelfId());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getRemarks());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getFromRackId());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getEntryMode());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getFromMrdCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getFromShelfId());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), mrdRackShelfChangeDtlVO.getPutBy());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MrdRackShelfChangeDtlDAO.populateMAP::"+e);
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
}
