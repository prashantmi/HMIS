package opd.dao;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeAllergySitedDtlVO;
import hisglobal.vo.UserVO;

public class EpisodeAllergySiteDtlDAO extends DataAccessObject {

	public  EpisodeAllergySiteDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public void create(EpisodeAllergySitedDtlVO _episodeAllergyVO,UserVO _userVO){
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT.HRGT_EPISODE_ALLERGY_SITEDTL";
        
       
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
       
        
        
        try{
        	populateMAP.put(sq.next(),_episodeAllergyVO.getPatCrNo());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeCode());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getPatCrNo());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeCode());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getAllergiesCode());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getPatAdmNo());
        	populateMAP.put(sq.next(), _episodeAllergyVO.getAllergySiteID());
        	populateMAP.put(sq.next(),_episodeAllergyVO.getReasonCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_userVO.getSeatId());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("EpisodeAllergySiteDtlDAO.populateMAP::"+e);
        }
        try{
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }

	}

}
