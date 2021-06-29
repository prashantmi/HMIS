package opd.master.dao;

/**
 * @author  CDAC
 */

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class GlobalDeskMasterDAO extends DataAccessObject
{
	Logger log;

	public GlobalDeskMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	//* Inserting Desk Record
	public void create(DeskMasterVO _DeskVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERTGLOBAL.GBLT_DESK_MST";
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
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _DeskVO.getDeskName());
			populateMAP.put(sq.next(), _DeskVO.getDeskType());
			populateMAP.put(sq.next(), _DeskVO.getIsDefault());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _DeskVO.getIsValid());
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
	
	public boolean checkDuplicateDesk(DeskMasterVO _DeskVO, UserVO _UserVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.DUPLICATE_DESKNAME.GBLT_DESK_MST";
	   	
	   	try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	
        	populateMAP.put(sq.next(),_DeskVO.getDeskName());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
        	// Reverted Change 		
		//	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),_DeskVO.getDeskType());
        	populateMAP.put(sq.next(),_DeskVO.getDeskId());

        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	rs.next();
        	if(rs.getInt(1)==0)
        	{
        		flag=true;
        	}
        	else
        	{
        		flag=false;
        	}
            
            return flag;
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
	

	//* Updating Desk Record
	public void update(DeskMasterVO _DeskVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.GBLT_DESK_MST";
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
			populateMAP.put(sq.next(), _DeskVO.getDeskName());
			populateMAP.put(sq.next(), _DeskVO.getDeskType());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _DeskVO.getIsValid());
			populateMAP.put(sq.next(), _DeskVO.getIsDefault());
			populateMAP.put(sq.next(), _DeskVO.getDeskId());
			//Change for global mapping ..			
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		    populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
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

	//* Getting Desk Id with given Desk Name, Desk Type, Seat Id and IsValid 
	public String getDeskId(DeskMasterVO _DeskVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DESK_ID.GBLT_DESK_MST";

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
			populateMAP.put(sq.next(), _DeskVO.getDeskName());
			populateMAP.put(sq.next(), _DeskVO.getDeskType());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _DeskVO.getIsValid());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
				
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Such Desk Exists ... ");
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

	//* Getting Desk VO By Desk Id 
	public DeskMasterVO selectByDeskId(String _DeskId, UserVO _userVo)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DeskMasterVO voDskMst = new DeskMasterVO();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.BY_DESK_ID.GBLT_DESK_MST";

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
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		//	populateMAP.put(sq.next(), _userVo.getHospitalCode());
			populateMAP.put(sq.next(), _DeskId);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Desk Exists... ");
			rs.first();
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		try
		{ 
			
			voDskMst.setDeskId(_DeskId);
			voDskMst.setDeskName(rs.getString(1));
			
			voDskMst.setDeskType(rs.getString(2));
			
			voDskMst.setIsDefault(rs.getString(3));
			
			voDskMst.setIsValid(rs.getString(4));
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :selectByDeskId" + e);
		}
		return voDskMst;
	}

}

