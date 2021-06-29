package mortuary.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PostmortemDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

public class PostmortemWaveoffDtlDAO  extends DataAccessObject implements PostmortemWaveoffDtlDAOi
{
	public PostmortemWaveoffDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record
	public void create(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_POSTMORTEM_WAVEOFF_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getPostmortemId());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getWaveoffBy());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getWaveoffMode());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDeptInfo());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getWaveoffReason());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getLetterNo());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getOfficerIncharge());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getIoDesignation());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getIoBatchNo());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDutyOffName());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getApprovedBy());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDutyOffDesignation());
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDutyOffBatchNo());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("PostmortemWaveoffDtlDAO.populateMAP::"+e);
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
