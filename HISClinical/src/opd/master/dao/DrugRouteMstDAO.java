package opd.master.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.UserVO;

public class DrugRouteMstDAO extends DataAccessObject 
{
	
	public DrugRouteMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}	

	
	public DrugRouteMstVO itemName(String itemID , UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DrugRouteMstVO _drugRouteMstVO=new DrugRouteMstVO();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_DRUG_ROUTE_MST";

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
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), itemID);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MstDAO.populateMAP::" + e);
		}
		//String str = new String();
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_drugRouteMstVO.setItemTypeName(rs.getString(1));
											
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _drugRouteMstVO;
	}
	
	public void saveDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_DRUG_ROUTE_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _macroMstVO.getProcessID());
			populateMAP.put(sq.next(), _drugRouteMstVO.getItemTypeId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteName());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteDesc());
			populateMAP.put(sq.next(), OpdConfig.SLNO);
			populateMAP.put(sq.next(), _drugRouteMstVO.getRouteClassification());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
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
	
	public DrugRouteMstVO fetchDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename =OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="FETCH.HGBT_DRUG_ROUTE_MST";

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
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getSerialNo());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getIsValid());
			//populateMAP.put(sq.next(), _drugRouteMstVO.getRouteClassification());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDntionTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_drugRouteMstVO.setDrugRouteName(rs.getString(1));
				_drugRouteMstVO.setDrugRouteDesc(rs.getString(2));
				_drugRouteMstVO.setDrugRouteId(rs.getString(3));
				_drugRouteMstVO.setRouteClassification(rs.getString(4));
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _drugRouteMstVO;
	}
	
	public void updateDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_DRUG_ROUTE_MST";

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
			
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getSerialNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
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
		
	public void saveModDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFYINSERT.HGBT_DRUG_ROUTE_MST";

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
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getItemTypeId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteName());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteDesc());
			populateMAP.put(sq.next(), _drugRouteMstVO.getRouteClassification());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getIsValid());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
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
	
	public String checkDuplicateDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="CHECKDUPLICATEBEFOREINSERT.HGBT_DRUG_ROUTE_MST";

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
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteName().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getItemTypeId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	public String checkDuplicateDrugRouteInfoForModify(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="CHECKDUPLICATEFORMODIFY.HGBT_DRUG_ROUTE_MST";

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
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteName().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getItemTypeId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getSerialNo());
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	//Added By Chetan 
	public DrugRouteMstVO getDrugRouteInfo(DrugRouteMstVO _drugRouteMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename =OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="GET.HGBT_DRUG_ROUTE_MST";

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
		
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getIsValid());
			//populateMAP.put(sq.next(), _drugRouteMstVO.getRouteClassification());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDntionTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_drugRouteMstVO.setDrugRouteName(rs.getString(1));
				_drugRouteMstVO.setItemTypeId(rs.getString(2));
				_drugRouteMstVO.setRouteClassification(rs.getString(3));
				_drugRouteMstVO.setDrugRouteId(rs.getString(4));
			
				
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _drugRouteMstVO;
	}
	

}
