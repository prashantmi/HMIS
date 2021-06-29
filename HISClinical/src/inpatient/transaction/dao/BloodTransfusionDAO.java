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
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import bloodbank.BloodBankConfig;

public class BloodTransfusionDAO extends DataAccessObject implements BloodTransfusionDAOi
{
	public  BloodTransfusionDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void creat(BloodTransfusionDtlVO bloodTransfusionDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HBBT_TRANSFUSSION_DTL";
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
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getRequisitionNo());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getEpisodeCode());
        	//transfusionDate
        	//sereal No
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getRequisitionNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getAdmissionNo());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getTransfussionEndTime());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getTransfussionStartTime());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getPhlebotomyArm());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getBloodBagNo());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getBloodBagSequenceNo());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getRemark());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getUserEmpID());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getQtyTransfussed());
        	populateMAP.put(sq.next(),bloodTransfusionDtlVO.getTransfussionRate());
        	populateMAP.put(sq.next(),InpatientConfig.BLOOD_TRANSFUSSION_COMPLETED);
           	
                	
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
				
		String query = "";
		Map populateMap= new HashMap();
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	//	String filename2= BloodBankConfig.QUERY_FILE_FOR_BLOODBANK_TRANSACTIONDAO;
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
	
	
}
