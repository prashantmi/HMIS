package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ReactionParaDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.util.HashMap;
import java.util.Map;

public class ReactionParaDetailDAO extends DataAccessObject implements ReactionParaDetailDAOi
{
	public  ReactionParaDetailDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void creat(ReactionParaDtlVO vo,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HRGT_EPISODE_REACTION_PARA_DTL";
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
        	populateMAP.put(sq.next(),vo.getPatCrNo());
        	//record date
        	//serial No
        	//populateMAP.put(sq.next(),vo.getPatCrNo());
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),vo.getSerialNo());
        	populateMAP.put(sq.next(),vo.getPatCrNo()); // for para serialno
        	populateMAP.put(sq.next(),vo.getSerialNo());  //for para serial no
        	//populateMAP.put(sq.next(),vo.getParaSerialNo());
        	populateMAP.put(sq.next(),vo.getTemplateId());
        	populateMAP.put(sq.next(),vo.getParaValue());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),vo.getParaID());
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
