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
import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedBroughtByDtlDAO extends DataAccessObject implements DeceasedBroughtByDtlDAOi
{
	public DeceasedBroughtByDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(DeceasedBroughtByDtlVO deceasedBroughtVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_BROUGHTBY_DTL";
	    
	   
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
	    	
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getIsBroughtBy());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getEmpId());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getBroughtByName());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getBroughtByAddress());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getBroughtByPhone());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getOfficerDesignation());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getRelativeCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getOfficerBadgeNo());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getBroughtLocation());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getPoliceStnAccmponiedBy());
	    	populateMAP.put(sq.next(), deceasedBroughtVO.getPoliceContactNo());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedBroughtByDtlDAO.populateMAP::"+e);
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
