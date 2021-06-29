package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.IcdMappingMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class OpdIcdMappingDAO extends DataAccessObject implements OpdIcdMappingDAOi
{

	public OpdIcdMappingDAO(TransactionContext _tx)
	{
		super(_tx);
//		log = LogManager.getLogger(this.getClass());
	}

	// to save icd mapping
	public void create(IcdMappingMasterVO listIcdMasterVO_p,UserVO userVO_p) {

		String strQuery = "";
		Map mapPopulate = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "INSERT.HGBT_ICD_MAPPING_MST";

		try {
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			mapPopulate.put(sq.next(), listIcdMasterVO_p.getMappingID());
			mapPopulate.put(sq.next(), listIcdMasterVO_p.getMappingType());
			mapPopulate.put(sq.next(), listIcdMasterVO_p.getDiseaseCode());
			mapPopulate.put(sq.next(), userVO_p.getSeatId());
			mapPopulate.put(sq.next(), Config.IS_VALID_ACTIVE);
			mapPopulate.put(sq.next(), userVO_p.getHospitalCode());
			mapPopulate.put(sq.next(), listIcdMasterVO_p.getMappingID());
			mapPopulate.put(sq.next(), listIcdMasterVO_p.getMappingType());
			mapPopulate.put(sq.next(), listIcdMasterVO_p.getDiseaseCode());
			mapPopulate.put(sq.next(), userVO_p.getHospitalCode());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"OpdIcdMappingDAO.mapPopulate::" + e);
		}

		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), strQuery, mapPopulate);

		} catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate"
					+ e);
		}

	}
	// to get icd mapping for modify
	public IcdMappingMasterVO[] getIcdMappingForModify(IcdMappingMasterVO vo_p, UserVO userVO_p)
	{

		ResultSet rs = null;
		String strQuery = "";
		ValueObject[] vo={};
		IcdMappingMasterVO[] icdMappingMasterVO=null;
		Map mapPopulate = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.HGBT_ICD_MAPPING_MST";
		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + strQuery);
		Sequence sq = new Sequence();

		mapPopulate.put(sq.next(), vo_p.getMappingID());
		mapPopulate.put(sq.next(), vo_p.getMappingType());
		mapPopulate.put(sq.next(), userVO_p.getHospitalCode());
		mapPopulate.put(sq.next(), Config.IS_VALID_ACTIVE);
		mapPopulate.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulate);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(IcdMappingMasterVO.class,rs);
				icdMappingMasterVO=new IcdMappingMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					icdMappingMasterVO[i]=(IcdMappingMasterVO)vo[i];
				}
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

		

		return icdMappingMasterVO;
	}
	// to get not mapped Icd Disease
	public List getNotMappedIcdDisease(String strMappingType_p,String strMappingId_p, String strSubGroupCode_p,UserVO userVO_p)
	{
		ResultSet rs = null;
		String strQuery = "";
		Map mapPopulate = new HashMap();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "SELECT.DISEASE_NOT_MAPPED.HGBT_ICD_MAPPING_MST";
		List listDiseaseNotAdded=new ArrayList();;
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			mapPopulate.put(sq.next(), userVO_p.getHospitalCode());
			mapPopulate.put(sq.next(), Config.IS_VALID_ACTIVE);
			mapPopulate.put(sq.next(), strSubGroupCode_p);
			mapPopulate.put(sq.next(), OpdConfig.ICD_DISEASE_RECORD_TYPE_DISEASE);
			mapPopulate.put(sq.next(), strMappingType_p);
			mapPopulate.put(sq.next(), strMappingId_p);
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.mapPopulate::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), strQuery, mapPopulate);
				if(rs.next())
				{
					listDiseaseNotAdded=HelperMethodsDAO.getAlOfEntryObjects(rs);	
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
			return listDiseaseNotAdded;
	}
	
	// for updating modified data
	public void update(String strMappingId_p, String strMappingType_p ,UserVO userVO_p)
	{
		String strQuery = "";
		Map mapPopulate = new HashMap();
		Sequence sq = new Sequence();
		String strFilename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String strQueryKey = "UPDATE.HGBT_ICD_MAPPING_MST";
		
		try
		{
			strQuery = HelperMethodsDAO.getQuery(strFilename, strQueryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			mapPopulate.put(sq.next(),Config.IS_VALID_DELETED);
			mapPopulate.put(sq.next(),strMappingId_p );
			mapPopulate.put(sq.next(),strMappingType_p );
			mapPopulate.put(sq.next(),userVO_p.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.mapPopulate::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), strQuery, mapPopulate);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
}

