package hisglobal.utility.masterVerification;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class MasterVerificationMstDAO extends DataAccessObject
{
	public  MasterVerificationMstDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(MasterVerificationVO masterVerficationVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
        String queryKey="INSERT.GBLT_MASTER_VERIFICATION_MST";
         
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
        	
        	populateMAP.put(sq.next(), masterVerficationVO.getModuleId());
        	populateMAP.put(sq.next(), masterVerficationVO.getModuleId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), masterVerficationVO.getMainHeader());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), masterVerficationVO.getSubHeader());
        	populateMAP.put(sq.next(), masterVerficationVO.getColumnHeader());
        	populateMAP.put(sq.next(), masterVerficationVO.getFooterHeader());
        	populateMAP.put(sq.next(), masterVerficationVO.getColumnSubHeader());
        	populateMAP.put(sq.next(), masterVerficationVO.getMainQuery());
        	populateMAP.put(sq.next(), masterVerficationVO.getOrderOption());
        	populateMAP.put(sq.next(), masterVerficationVO.getCriteriaLabel1());
        	populateMAP.put(sq.next(), masterVerficationVO.getCriteriaColumn1());
        	populateMAP.put(sq.next(), masterVerficationVO.getCriteriaQuery1());
        	/*populateMAP.put(sq.next(), masterVerficationVO.getCriteriaLabel2());
        	populateMAP.put(sq.next(), masterVerficationVO.getCriteriaColumn2());
        	populateMAP.put(sq.next(), masterVerficationVO.getCriteriaLabel3());
        	populateMAP.put(sq.next(), masterVerficationVO.getCriteriaColumn3());*/
        	populateMAP.put(sq.next(), "");
        	populateMAP.put(sq.next(), "");
        	populateMAP.put(sq.next(),"");
        	populateMAP.put(sq.next(), "");
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), masterVerficationVO.getGroupQuery());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MasterVerificationMstDAO.populateMAP::"+e);
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
	
	public void modify(MasterVerificationVO masterVerficationVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey="MODIFY.GBLT_MASTER_VERIFICATION_MST";
		
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
			
			//populateMAP.put(sq.next(), masterVerficationVO.getModuleId());
			//populateMAP.put(sq.next(), masterVerficationVO.getSerialNo());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), masterVerficationVO.getMainHeader());
			populateMAP.put(sq.next(), masterVerficationVO.getSubHeader());
			populateMAP.put(sq.next(), masterVerficationVO.getColumnHeader());
			populateMAP.put(sq.next(), masterVerficationVO.getFooterHeader());
			populateMAP.put(sq.next(), masterVerficationVO.getColumnSubHeader());
			populateMAP.put(sq.next(), masterVerficationVO.getMainQuery());
			populateMAP.put(sq.next(), masterVerficationVO.getOrderOption());
			populateMAP.put(sq.next(), masterVerficationVO.getCriteriaLabel1());
			populateMAP.put(sq.next(), masterVerficationVO.getCriteriaColumn1());
			populateMAP.put(sq.next(), masterVerficationVO.getCriteriaQuery1());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), masterVerficationVO.getGroupQuery());
			populateMAP.put(sq.next(), masterVerficationVO.getModuleId());
			populateMAP.put(sq.next(), masterVerficationVO.getSerialNo());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MasterVerificationMstDAO.populateMAP::"+e);
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
	
	public MasterVerificationVO select(MasterVerificationVO masterVerficationVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey="SELECT.GBLT_MASTER_VERIFICATION_MST";
		
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
			
			populateMAP.put(sq.next(), masterVerficationVO.getModuleId());
			populateMAP.put(sq.next(), masterVerficationVO.getSerialNo());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
						
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MasterVerificationMstDAO.populateMAP::"+e);
		}
		try
		{
			
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			HelperMethods.populateVOfrmRS(masterVerficationVO, rs);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException");
		}
		return masterVerficationVO;
	}
	
	
}
