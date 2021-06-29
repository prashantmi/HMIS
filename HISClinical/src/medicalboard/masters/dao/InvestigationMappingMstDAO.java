package medicalboard.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MbInvestigationMappingMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class InvestigationMappingMstDAO extends DataAccessObject implements InvestigationMappingMstDAOi
{
	public InvestigationMappingMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public void create(MbInvestigationMappingMstVO investigationMappingVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_INVESTIGATION_MAPPING";
		
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
	         	
	        	populateMAP.put(sq.next(),investigationMappingVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	//sereial no
	        	populateMAP.put(sq.next(),investigationMappingVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),investigationMappingVO.getLabTestCode());
	        	
	        	populateMAP.put(sq.next(),investigationMappingVO.getLabTestCode());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),investigationMappingVO.getIsOptional());
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	//entry date
	        	populateMAP.put(sq.next(),investigationMappingVO.getLastModifyDate());
	        	populateMAP.put(sq.next(),investigationMappingVO.getLastModifySeatId());
	         	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("populateMAP::"+e);
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
	
	public void updateInvestigationMappingDtl(MbInvestigationMappingMstVO investigationMappingVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE.HMBT_INVESTIGATION_MAPPING";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), investigationMappingVO.getCertificateTypeID());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
}
