package opd.master.dao;

import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UserVO;

public class UnitExtTreatDAO extends DataAccessObject implements UnitExtTreatDAOi
{
	public UnitExtTreatDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void create(UnitExtTreatMstVO vo,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_EXT_UNIT_TREATMENT_MST";
		
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
			populateMAP.put(sq.next(),vo.getExtTreatId());
			populateMAP.put(sq.next(),vo.getDeptUnitCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			//sereal no
			populateMAP.put(sq.next(),vo.getExtTreatId());
			populateMAP.put(sq.next(),vo.getDeptUnitCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.populateMAP::" + e);
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
	
	public void updateByDeptUnit(String deptUnitCode,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_EXT_UNIT_TREATMENT_MST";
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),deptUnitCode );
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.populateMAP::" + e);
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
