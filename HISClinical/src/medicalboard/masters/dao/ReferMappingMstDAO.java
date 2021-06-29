package medicalboard.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MbReferMappingMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class ReferMappingMstDAO extends DataAccessObject implements ReferMappingMstDAOi{

	public ReferMappingMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	
	public void create(MbReferMappingMstVO mappingMstVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_REFER_MAPPING";
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
	          	populateMAP.put(sq.next(),mappingMstVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	          	populateMAP.put(sq.next(),mappingMstVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	          	populateMAP.put(sq.next(),mappingMstVO.getReferType());
	          	populateMAP.put(sq.next(),mappingMstVO.getDeptCode());
	          	populateMAP.put(sq.next(),mappingMstVO.getDeptUnitCode());
	          	populateMAP.put(sq.next(),mappingMstVO.getRemarks());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	          	populateMAP.put(sq.next(),mappingMstVO.getIsOptional());
	         	populateMAP.put(sq.next(),userVO.getSeatId());
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
	
	
	
	public MbReferMappingMstVO[] getReferMappingDetail(MbReferMappingMstVO mReferMappingMstVO,UserVO _UserVO)
	{
		ValueObject[] vo =
		{};
		MbReferMappingMstVO[] mappingMstVOs= null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey = "SELECT.HMBT_REFER_MAPPING";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		 populateMAP.put(sq.next(), mReferMappingMstVO.getCertificateTypeID());
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Department/Unit Found");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(MbReferMappingMstVO.class, rs);
			System.out.println("length" + vo.length);
			mappingMstVOs = new MbReferMappingMstVO[vo.length];
			System.out.println("_patientVO.length:: " + mappingMstVOs.length);
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				mappingMstVOs[i] = (MbReferMappingMstVO) vo[i];
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("alRecord primary. cat" + alRecord);
		return mappingMstVOs;
	}

	
	
	public void updateReferDetail(String certificateTypeId,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE.HMBT_REFER_MAPPING";
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
			populateMAP.put(sq.next(), certificateTypeId);
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
