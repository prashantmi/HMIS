package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class InsuranceDetailDAO extends DataAccessObject implements InsuranceDetailDAOi
{
	public  InsuranceDetailDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void creat(InsuranceDetailVO insuranceDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_INSURANCE_DTL";
        
       
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
        	
        	//getting insurance id
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(), insuranceDetailVO.getPatCrNo());
        	populateMAP.put(sq.next(), insuranceDetailVO.getEpisodeCode());
        	populateMAP.put(sq.next(), insuranceDetailVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), insuranceDetailVO.getCompanyId());
        	populateMAP.put(sq.next(), insuranceDetailVO.getPolicyNo());
        	populateMAP.put(sq.next(), insuranceDetailVO.getPatAdmNo());
        	populateMAP.put(sq.next(), insuranceDetailVO.getIsOpinionForm());
        	populateMAP.put(sq.next(), insuranceDetailVO.getReceiveDateTime());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionPages());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionBy());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionRemarks());
        	populateMAP.put(sq.next(), insuranceDetailVO.getStatus());
        	populateMAP.put(sq.next(), insuranceDetailVO.getRemarks());
        	populateMAP.put(sq.next(), insuranceDetailVO.getCIDNo());
        	populateMAP.put(sq.next(), insuranceDetailVO.getIsOpinionUpload());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	//entry date(system date)
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionDate());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionSeatId());
        	populateMAP.put(sq.next(), insuranceDetailVO.getPostEntryDate());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), insuranceDetailVO.getPostSeatId());
        	populateMAP.put(sq.next(), insuranceDetailVO.getReceivingMode());
               	
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
	
	public void saveInsuranceClaimRecordEntry(InsuranceDetailVO insuranceDetailVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="UPDATE.OPINION_DETAIL.HPMRT_INSURANCE_DTL";
        
       
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
        	
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionBy());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionRemarks());
        	populateMAP.put(sq.next(), insuranceDetailVO.getSendingMode());
        	populateMAP.put(sq.next(), insuranceDetailVO.getStatus());
        	populateMAP.put(sq.next(), insuranceDetailVO.getOpinionDate());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), insuranceDetailVO.getInsuranceId());
        	
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
