package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugListItemMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class DrugListItemMasterDAO extends DataAccessObject implements DrugListItemMasterDAOi
{
	Logger log;

	public DrugListItemMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
public void creat(DrugListItemMstVO drugDetailVo ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_DRUGLIST_ITEM_MST";
		
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
			populateMAP.put(sq.next(), drugDetailVo.getDrugListId());
			populateMAP.put(sq.next(), drugDetailVo.getDrugId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), drugDetailVo.getDoseId());
			//for sereal no
			populateMAP.put(sq.next(), drugDetailVo.getDrugId());
			populateMAP.put(sq.next(), drugDetailVo.getDrugListId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), drugDetailVo.getFrequencyId());
			//entry date
			populateMAP.put(sq.next(), drugDetailVo.getDays());
			populateMAP.put(sq.next(), drugDetailVo.getIsEmptyStomach());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), drugDetailVo.getLastModifyDate());
			populateMAP.put(sq.next(), drugDetailVo.getRemarks());
			populateMAP.put(sq.next(), drugDetailVo.getLastModifySeatId());
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ReqPurposeMstDAO.populateMAP::" + e);
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

public List getDrugDeatilByDrugList(DrugListItemMstVO drugListItemMstVO,UserVO _userVO)
{
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "ESSENTIAL.LIST.HGBT_DRUGLIST_ITEM_MST";
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	
	Sequence sq = new Sequence();
	try
	{
		populateMAP.put(sq.next(), drugListItemMstVO.getDrugListId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
	}

	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		//if (!rs.next()) throw new HisRecordNotFoundException( "No Drug Doses Exists  ");
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}

	List lstDrugDeatil = new ArrayList();
	ValueObject[] vo = null;
	try
	{
		if (rs.next())
		{
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DrugListItemMstVO.class, rs);
			for(ValueObject v : vo)
				lstDrugDeatil.add((DrugListItemMstVO)v);
		}
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
	}
	return lstDrugDeatil;
}
	
public void updateDrugListItemMaster(DrugListItemMstVO drugListItemMstVO,UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	String queryKey = "UPDATE.HGBT_DRUGLIST_ITEM_MST";

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
		populateMAP.put(sq.next(), drugListItemMstVO.getDrugListId());
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

}
