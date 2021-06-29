package opd.master.dao;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.OpdParameterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class OPDParameterMasterDAO extends DataAccessObject
{
	Logger log;

	public OPDParameterMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// * Inserting Parameter Record
	public void create(OpdParameterVO _ParaVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_PARAMETER_MST";

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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _ParaVO.getParaName());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDParameterMasterDAO.populateMAP::" + e);
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

		try
		{
			this.fetchIdByName(_ParaVO, _UserVO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// * Fetching Parameter Id by Parameter Name
	public boolean fetchIdByName(OpdParameterVO _ParaVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		boolean flag = false;

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.ID_BY_NAME.HGBT_PARAMETER_MST";

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
			populateMAP.put(sq.next(), _ParaVO.getParaName());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OPDParameterMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_ParaVO.setParaId(rs.getString(1));
				flag = true;
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
		return flag;
	}

	/*
	 * // fetching Id by Name of Paramter public boolean fetchNameById(OpdParameterVO _ParaVO, UserVO _UserVO) { String query =
	 * ""; ResultSet rs; Map populateMAP =new HashMap(); Sequence sq=new Sequence(); boolean flag=false;
	 * 
	 * String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO; String queryKey="SELECT.NAME_BY_ID.HGBT_PARAMETER_MST";
	 * 
	 * try { query =HelperMethodsDAO.getQuery(filename,queryKey); } catch(Exception e) { throw new
	 * HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property
	 * file"+e); } log.info(query);
	 * 
	 * try { populateMAP.put(sq.next(),_ParaVO.getParaId()); populateMAP.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE); }
	 * catch(Exception e) { throw new HisApplicationExecutionException("OPDParameterMasterDAO.populateMAP::"+e); }
	 * 
	 * try { rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP); if(rs.next()) {
	 * _ParaVO.setParaName(rs.getString(1)); flag=true; } } catch(Exception e) {
	 * if(e.getClass()==HisRecordNotFoundException.class) { throw new HisRecordNotFoundException(e.getMessage()); } else
	 * throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e); } return flag; }
	 */

	/*
	 * public void update(UserDeskMenuMasterVO _UserDeskMenuVO, UserVO _UserVO) {
	 * 
	 * String query = ""; Map populateMAP =new HashMap(); Sequence sq=new Sequence(); String
	 * filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO; String queryKey="UPDATE.GBLT_USER_DESKMENU_MST";
	 * 
	 * try { query =HelperMethodsDAO.getQuery(filename,queryKey); } catch(Exception e) { throw new
	 * HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property
	 * file"+e); } log.info(query);
	 * 
	 * try { populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskId());
	 * populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskType()); populateMAP.put(sq.next(),_UserDeskMenuVO.getIsValid());
	 * populateMAP.put(sq.next(),_UserVO.getSeatId()); populateMAP.put(sq.next(),_UserDeskMenuVO.getUserSeatId());
	 * populateMAP.put(sq.next(),_UserDeskMenuVO.getDeptUnitCode()); } catch(Exception e){ throw new
	 * HisApplicationExecutionException("OPDParameterMasterDAO.populateMAP::"+e); } try{
	 * HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP); } catch(Exception
	 * e){ e.printStackTrace(); throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e); }
	 *  }
	 */

}
