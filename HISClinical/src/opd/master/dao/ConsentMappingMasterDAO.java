package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.ConsentMappingMasterVO;
import hisglobal.vo.UserVO;
import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConsentMappingMasterDAO extends DataAccessObject 
{
	Logger log;

	public ConsentMappingMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	public String getServiceListQuery(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SERVICEID_QUERY.HGBT_CONSENT_SERVICE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Such Service Exists ... ");
			rs.first();
			return rs.getString(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	public List getServiceList(String Query,ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet rs;
		Map populateMAP = new HashMap();
		HisDAO daoObj = new HisDAO("OPD","ConsentMappingMasterDAO");
		try
		{
			//rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), Query, populateMAP);
		
			nProcedureIndex = daoObj.setProcedure(Query); 
			
			// Setting and Registering In and Out Parameters 
			daoObj.setProcInValue(nProcedureIndex, "p_mode",consentMappingMasterVO.getServiceTypeId(),1);
			daoObj.setProcInValue(nProcedureIndex, "is_valid",Config.IS_VALID_ACTIVE ,2);
			daoObj.setProcInValue(nProcedureIndex, "hosp_code", _userVO.getHospitalCode(),3);
			daoObj.setProcInValue(nProcedureIndex, "seat_id",_userVO.getSeatId() ,4);
			daoObj.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			daoObj.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

			// Executing Procedure 
			daoObj.executeProcedureByPosition(nProcedureIndex);
			// Getting out parameters 
			strDBErr = daoObj.getString(nProcedureIndex, "err");
			rs = daoObj.getWebRowSet(nProcedureIndex, "resultset");
			
			// If Database Error Occurs, No further processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public List getTemplateList(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.TEMPLATELIST.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateCategory());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}

	public List getServiceList(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SERVICELISTFORMODIFY.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateType());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateType());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateType());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateType());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}


	public List getSelectedServiceList(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SELECTEDSERVICELISTFORMODIFY.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}

	public List getSelectedServiceIdList(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SELECTEDSERVICEIDLIST.HGBT_CONSENT_MAPPING";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getChoice());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	
   public void create(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HGBT_CONSENT_MAPPING";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        
        try{
        	populateMAP.put(sq.next(),consentMappingMasterVO.getServiceTypeId());
        	populateMAP.put(sq.next(),consentMappingMasterVO.getTemplateId());
        	populateMAP.put(sq.next(),consentMappingMasterVO.getServiceId());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	populateMAP.put(sq.next(),consentMappingMasterVO.getServiceTypeId());
        	populateMAP.put(sq.next(),consentMappingMasterVO.getTemplateId());
        	populateMAP.put(sq.next(),consentMappingMasterVO.getServiceId());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),consentMappingMasterVO.getChoice());
        	populateMAP.put(sq.next(), _userVO.getSeatId());
        	populateMAP.put(sq.next(),consentMappingMasterVO.getServiceDesc());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("ConsentMappingMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
   public ConsentMappingMasterVO getServiceTypeDesc(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SERVICEDESC.HGBT_CONSENT_SERVICE_MST";
		ConsentMappingMasterVO _consentMappingMasterVO= new ConsentMappingMasterVO();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Such Service Exists ... ");
			rs.first();
			_consentMappingMasterVO.setServiceTypeDesc(rs.getString(1));
			return _consentMappingMasterVO;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
   
   public ConsentMappingMasterVO getServiceDescAndTemplateType(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SERVICEDESC_TEMPLATEDESC.HGBT_CONSENT_MAPPING";
		ConsentMappingMasterVO _consentMappingMasterVO= new ConsentMappingMasterVO();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getSerialNo());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Such Service Exists ... ");
			rs.first();
			_consentMappingMasterVO.setServiceDesc(rs.getString(1));
			_consentMappingMasterVO.setTemplateType(rs.getString(2));
			return _consentMappingMasterVO;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
   
   public ConsentMappingMasterVO getTemplateTypeDesc(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.TEMPLATETYPEDESC.HGBT_TEMPLATE_MST";
		ConsentMappingMasterVO _consentMappingMasterVO= new ConsentMappingMasterVO();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateType());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Such Service Exists ... ");
			rs.first();
			_consentMappingMasterVO.setTemplateDesc(rs.getString(1));
			return _consentMappingMasterVO;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
   public List getSelectedTemplateList(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SELECTEDTEMPLATELIST.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateCategory());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
   
   public void updateConsentMappingDetail(ConsentMappingMasterVO consentMappingMasterVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_CONSENT_MAPPING";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceTypeId());
			//populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateId());
			populateMAP.put(sq.next(), consentMappingMasterVO.getServiceId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), consentMappingMasterVO.getTemplateType());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
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
