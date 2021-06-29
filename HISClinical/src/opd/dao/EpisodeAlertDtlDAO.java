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
import hisglobal.vo.EpisodeAlertDtlVO;
import hisglobal.vo.UserVO;

public class EpisodeAlertDtlDAO extends DataAccessObject implements EpisodeAlertDtlDAOi
{
	public EpisodeAlertDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	// Inserting a New Row
	public void create(EpisodeAlertDtlVO epiAlertDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_DAO;
        String queryKey="INSERT.HRGT_EPISODE_ALERT_DTL";
        /* String alertId=epiAlertDtlVO.getPatAlertId();
		String [] id;
		id=alertId.split("#");
		String idVal=id[0];
		System.out.println("epiAlertDtlVO:::::"+idVal); */
       
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
        	populateMAP.put(sq.next(), epiAlertDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getPatAlertId());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getAdmissionNo());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getRemarks());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getDurationDate());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getDurationDate());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getIsRevoked());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), epiAlertDtlVO.getAlertName());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("EpisodeAlertDtlDAO.populateMAP::"+e);
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
