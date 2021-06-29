package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.util.HashMap;
import java.util.Map;

public class DoctorWardRoundDtlDAO extends DataAccessObject implements DoctorWardRoundDtlDAOi
{
	
	public  DoctorWardRoundDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(DoctorWardRoundDtlVO _doctorWardRoundDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HIPD_DOCTOR_WARDROUND_DTL";
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
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getRoundDate());
        	populateMAP.put(sq.next(), _doctorWardRoundDtlVO.getRoundDate());
			populateMAP.put(sq.next(), _doctorWardRoundDtlVO.getWardCode());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getWardCode());
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getRoundType());
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getRoundOutTime());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getRoundInTime());
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getNextRoundDate());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getEnterBy());
        	populateMAP.put(sq.next(),_doctorWardRoundDtlVO.getEmpNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getIpAddress());
        	
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
