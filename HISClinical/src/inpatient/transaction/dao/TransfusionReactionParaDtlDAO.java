package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.util.HashMap;
import java.util.Map;

public class TransfusionReactionParaDtlDAO extends DataAccessObject implements TransfusionReactionParaDtlDAOi
{
	public  TransfusionReactionParaDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void creat(TransfusionReactionParaDtlVO transReactionParaDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HBBT_TRANS_REACTION_PARA_DTL";
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
        	//record date (sysdate)
        	//reaction Id
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),transReactionParaDtlVO.getReactionID());
        	
        	populateMAP.put(sq.next(),transReactionParaDtlVO.getParaSerealNo());
        	populateMAP.put(sq.next(),transReactionParaDtlVO.getTemplateId());
        	populateMAP.put(sq.next(),transReactionParaDtlVO.getParaValue());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),transReactionParaDtlVO.getParaId());
        	//entry date(sysDate)
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	                    	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
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
