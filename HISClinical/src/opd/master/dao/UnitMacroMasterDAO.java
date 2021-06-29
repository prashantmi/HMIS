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
import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserVO;

public class UnitMacroMasterDAO extends DataAccessObject implements UnitMacroMasterDAOi
{
	public UnitMacroMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void create(UnitWiseMacroMstVO vo,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_MACRO_UNITWISE_MST";
		
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
			populateMAP.put(sq.next(),vo.getMacroId());
			populateMAP.put(sq.next(),vo.getDeptUnitCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			//sereal no
			populateMAP.put(sq.next(),vo.getMacroId());
			populateMAP.put(sq.next(),vo.getDeptUnitCode());
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
			populateMAP.put(sq.next(),vo.getProcessId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getSeatId());
			//entrydate
			populateMAP.put(sq.next(),vo.getLastModifyDate());
			populateMAP.put(sq.next(),vo.getLastModifySeatId());
			
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
	
	public void Update(String unittCode,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="UPDATE.HGBT_MACRO_UNITWISE_MST";
	   	
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
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),unittCode);
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UnitImageDescMasterDAO.populateMAP::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }

	}
}
