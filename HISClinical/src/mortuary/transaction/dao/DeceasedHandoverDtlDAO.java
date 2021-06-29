package mortuary.transaction.dao;

import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedHandoverDtlDAO extends DataAccessObject implements DeceasedHandoverDtlDAOi
{
	public DeceasedHandoverDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(DeceasedHandoverDtlVO deceasedHandoverVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_HANDOVER_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getIsHandoverTo());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getEmpId());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getHandoverToName());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getHandoverToAddress());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getHandoverToPhone());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getOfficerDesignation());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getRelativeCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getOfficerBadgeNo());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getPoliceStation());
	    	populateMAP.put(sq.next(), deceasedHandoverVO.getPoliceContactNo());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedHandoverDtlDAO.populateMAP::"+e);
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
