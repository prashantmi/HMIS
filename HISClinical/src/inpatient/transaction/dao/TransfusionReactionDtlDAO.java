package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransfusionReactionDtlDAO extends DataAccessObject implements TransfusionReactionDtlDAOi
{	
	public  TransfusionReactionDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void creat(TransfusionReactionDtlVO transReactionDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HBBT_TRANSFUSSION_REACTION_DTL";
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
        	populateMAP.put(sq.next(),transReactionDtlVO.getBloodBagNo());
        	//record date (sysdate)
        	populateMAP.put(sq.next(),transReactionDtlVO.getBloodBagSequenceNo());
        	//reaction Id
        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),transReactionDtlVO.getReactionID());
        	
        	populateMAP.put(sq.next(),transReactionDtlVO.getReactionStartTime());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),transReactionDtlVO.getReactionEndTime());
        	//entry date(sysDate)
        	populateMAP.put(sq.next(),transReactionDtlVO.getControllSummary());
        	populateMAP.put(sq.next(),transReactionDtlVO.getTransfusinStatus());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),transReactionDtlVO.getRequisitionNo());
        	populateMAP.put(sq.next(),transReactionDtlVO.getSourceFlag());
        	populateMAP.put(sq.next(),transReactionDtlVO.getReactionSummary());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
                     	
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
	
	public List getTemplateIdList(UserVO userVO)
	{
		List tempalteIdList=new ArrayList();
		ResultSet rs=null;
		
		//PatientDetailVO patientDetailVO=new PatientDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.TEMPLAEID_LIST_FOR_TRANSFUSION_REACTION.HGBT_TEMPLATE_MAPPING_MST";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), GenericTemplateConfig.TEMPLATE_CATEGORY_TRANSFUSSION_REACTION);
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE );
	
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					tempalteIdList.add(rs.getString(1));
				}
			}
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		

		
		return tempalteIdList;
	}
	
	public String getMaxSlNo(UserVO userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_MAXSLNO.HBBT_TRANSFUSSION_REACTION_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No record Is Here");
			}
			return rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		
	}
}
