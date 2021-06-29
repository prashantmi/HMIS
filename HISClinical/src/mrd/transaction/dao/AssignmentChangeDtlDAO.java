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
import hisglobal.vo.AssignmentChangeDtlVO;
import hisglobal.vo.UserVO;

public class AssignmentChangeDtlDAO extends DataAccessObject implements AssignmentChangeDtlDAOi
{
	public  AssignmentChangeDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(AssignmentChangeDtlVO assignChangeDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_ASSIGNMENT_CHANGE_DTL";
        
       
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
        	populateMAP.put(sq.next(), assignChangeDtlVO.getSummonId());
        	populateMAP.put(sq.next(), assignChangeDtlVO.getOldAssignee());
        	//sereal Number
        	populateMAP.put(sq.next(), assignChangeDtlVO.getSummonId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(), assignChangeDtlVO.getOldOtherAssignReason());
        	populateMAP.put(sq.next(), assignChangeDtlVO.getOldSummonAck());
        	populateMAP.put(sq.next(), assignChangeDtlVO.getOldAssignSame());
        	populateMAP.put(sq.next(), assignChangeDtlVO.getNewAssignee());
        	populateMAP.put(sq.next(), assignChangeDtlVO.getReason());
        	//entry date (sysdate)
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	
            	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
