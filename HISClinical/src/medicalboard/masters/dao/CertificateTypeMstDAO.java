package medicalboard.masters.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import medicalboard.MedicalBoardConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.UserVO;


public class CertificateTypeMstDAO extends DataAccessObject implements CertificateTypeMstDAOi{

	public CertificateTypeMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	//Checking For Duplicate Name
	public String checkDuplicateCertificateTypeName(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT_CHECKDUPLICATE_ON_INSERT.HMBT_CERTIFICATE_TYPE_MST";
		
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
	        populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeName());
	        populateMAP.put(sq.next(), userVO.getHospitalCode());
	        //populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("CertificateTypeMstDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	rs.first();
		    return rs.getString(1);
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
		    }
		    else
		    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}    
	}
	
	
	
	public String getMaxCertificateTypeId(UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT_MAX_CERTIFICATETYPE_ID.HMBT_CERTIFICATE_TYPE_MST";
		
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
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("CertificateTypeMstDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	rs.first();
		    return rs.getString(1);
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
		    }
		    else
		    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}    
	}
	
	
	
	
	public void create(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_CERTIFICATE_TYPE_MST";
		
		String tempCode= mTypeMstVO.getTemplateCode();
		if(tempCode.equals("-1"))
				{
			tempCode= "";
				}
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
	         	populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeID());
	        	populateMAP.put(sq.next(),Config.SL_NO);
	        	populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeName());
	        	populateMAP.put(sq.next(),mTypeMstVO.getCertificateCatID());
	        	populateMAP.put(sq.next(),mTypeMstVO.getDescription());
	        	populateMAP.put(sq.next(),mTypeMstVO.getBoardType());
	        	populateMAP.put(sq.next(),mTypeMstVO.getDeptUnitCode());
	        	populateMAP.put(sq.next(),mTypeMstVO.getLocationBound());
	        	populateMAP.put(sq.next(),mTypeMstVO.getMinRequest());
	        	populateMAP.put(sq.next(),mTypeMstVO.getMaxRequest());
	        	populateMAP.put(sq.next(),mTypeMstVO.getRequisitionBy());
	        	populateMAP.put(sq.next(),mTypeMstVO.getMaxAge());
	        	populateMAP.put(sq.next(),mTypeMstVO.getMinAge());
	         	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	//populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	         	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	         	populateMAP.put(sq.next(),userVO.getSeatId());
	         	populateMAP.put(sq.next(),mTypeMstVO.getIssueType());
	         	populateMAP.put(sq.next(),tempCode);
	         	populateMAP.put(sq.next(),mTypeMstVO.getIsCertNoRequired());
	         	populateMAP.put(sq.next(),mTypeMstVO.getCertNoStartFrom());
	         	populateMAP.put(sq.next(),mTypeMstVO.getIsCertNoRequired());
	         	populateMAP.put(sq.next(),mTypeMstVO.getCertNoStartFrom());
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
	
	
	
	public void saveDistrict(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT_DISTRICT.HMBT_LOCATION_ELIGIBILITY_MST";
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
	        	populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeID());
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),mTypeMstVO.getDistrictID());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
	
	
	
	
	public MbCertificateTypeMstVO getDataCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		MbCertificateTypeMstVO vo=new MbCertificateTypeMstVO();

		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="SELECT.HMBT_CERTIFICATE_TYPE_MST";
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
			populateMAP.put(sq.next(), mTypeMstVO.getCertificateTypeID());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), mTypeMstVO.getSlNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	
/*	public  getDataDistrictID(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		MbCertificateTypeMstVO vo=new MbCertificateTypeMstVO();

		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="SELECT_DISTRICT.HMBT_LOCATION_ELIGIBILITY_MST";
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
			populateMAP.put(sq.next(), mTypeMstVO.getCertificateTypeID());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), mTypeMstVO.getSlNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	*/
	
	
	public List  getDataDistrictIDForModify(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
			String queryKey = "SELECT_DISTRICT.HMBT_LOCATION_ELIGIBILITY_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
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
			populateMAP.put(sq.next(), mTypeMstVO.getCertificateTypeID());
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord=new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	
	public List  getRemainingDistrictIDForModify(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
			String queryKey = "SELECT_REMAINING_DISTRICT_LIST.GBLT_DISTRICT_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
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
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), mTypeMstVO.getCertificateTypeID());
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord=new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	
	
	public void updateCertificateType(MbCertificateTypeMstVO mTypeMstVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE.HMBT_CERTIFICATE_TYPE_MST";
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
			populateMAP.put(sq.next(), mTypeMstVO.getCertificateTypeID());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), mTypeMstVO.getSlNo());
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
	
	

	
	public void modifySaveCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="MODIFY_INSERT.HMBT_CERTIFICATE_TYPE_MST";
		String tempCode=mTypeMstVO.getTemplateCode();
		if(tempCode.equals("-1"))
		{
			tempCode="";
		}
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
			populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeID());
			populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeID());
			//populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeName());
        	populateMAP.put(sq.next(),mTypeMstVO.getCertificateCatID());
        	populateMAP.put(sq.next(),mTypeMstVO.getDescription());
        	populateMAP.put(sq.next(),mTypeMstVO.getBoardType());
        	populateMAP.put(sq.next(),mTypeMstVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),mTypeMstVO.getLocationBound());
        	populateMAP.put(sq.next(),mTypeMstVO.getMinRequest());
        	populateMAP.put(sq.next(),mTypeMstVO.getMaxRequest());
        	populateMAP.put(sq.next(),mTypeMstVO.getRequisitionBy());
        	populateMAP.put(sq.next(),mTypeMstVO.getMaxAge());
        	populateMAP.put(sq.next(),mTypeMstVO.getMinAge());
         	//populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
         	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
         	populateMAP.put(sq.next(),_UserVO.getSeatId());
         	populateMAP.put(sq.next(),mTypeMstVO.getIssueType());
         	populateMAP.put(sq.next(),tempCode);
         	populateMAP.put(sq.next(),mTypeMstVO.getIsCertNoRequired());
         	populateMAP.put(sq.next(),mTypeMstVO.getCertNoStartFrom());
         	populateMAP.put(sq.next(),mTypeMstVO.getIsCertNoRequired());
         	populateMAP.put(sq.next(),mTypeMstVO.getCertNoStartFrom());
         	
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
		
	
	
	
	
	public String checkDuplicateCertificateNameForModify(MbCertificateTypeMstVO mTypeMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT_CHECKDUPLICATE_ON_MODIFY.HMBT_CERTIFICATE_TYPE_MST";
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
	        populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),mTypeMstVO.getCertificateTypeID());
	       	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	rs.first();
		    return rs.getString(1);
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
		    }
		    else
		    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}    
	}

	
	
	
	
	public void updateDistrictId(MbCertificateTypeMstVO mTypeMstVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE_DISTRICT.HMBT_LOCATION_ELIGIBILITY_MST";
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
			populateMAP.put(sq.next(), mTypeMstVO.getCertificateTypeID());
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
