package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import hisglobal.vo.DeskWiseDefaultProfileMstVO;
import hisglobal.vo.UserVO;

public class DeskWiseDefaultProfileMstDAO extends DataAccessObject 
{
	
	public DeskWiseDefaultProfileMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}	

	
	public List getDeskType(UserVO userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="DESK_TYPE.GBLT_DESK_TYPE_MST";
	   	
	   	try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),OpdConfig.PROFILE_BOUND_ONE);
		}
		catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	
	public List getDeskName(String deskTypeId,UserVO userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="DESK_NAME.GBLT_DESK_MST";
	   	
	   	try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deskTypeId);
		}
		catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	
	public List checkDefaultValue(String deskId , UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		//DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO=new DeskWiseDefaultProfileMstVO();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DEFAULT_VALUE.GBLT_DESK_DTL";

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
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deskId);
			populateMAP.put(sq.next(),OpdConfig.USABILITY_FLAG_TWO);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldCompMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	
	public List getAllMenuName(String deskId,UserVO userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="MENU_NAME.GBLT_DESK_DTL";
	   	
	   	try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deskId);
			populateMAP.put(sq.next(),OpdConfig.USABILITY_FLAG_TWO);
		
		}
		catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	
	public List getDefaultMenuName(String deskId,UserVO userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="DEFAULT_MENU_NAME.GBLT_DESK_DTL";
	   	
	   	try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deskId);
			populateMAP.put(sq.next(),OpdConfig.USABILITY_FLAG_TWO);
			populateMAP.put(sq.next(),OpdConfig.IS_PROFILE_DEFAULT_YES);
		
		}
		catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	
	public List getNonDefaultMenuName(String deskId,UserVO userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="NON_DEFAULT_MENU_NAME.GBLT_DESK_DTL";
	   	
	   	try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deskId);
			populateMAP.put(sq.next(),OpdConfig.USABILITY_FLAG_TWO);
			populateMAP.put(sq.next(),OpdConfig.IS_PROFILE_DEFAULT_NO);
		}
		catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
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
		List alRecord = new ArrayList();
		try
		{
			if(rs.next())
				alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return alRecord;
		
	}
	

	public void saveDefaultProfileDetails(DeskWiseDefaultProfileMstVO _DeskWiseDefaultProfileMstVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.PROFILE_DETAILS.GBLT_DESK_DTL";

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
			populateMAP.put(sq.next(), _DeskWiseDefaultProfileMstVO.getProfileOrder());
			populateMAP.put(sq.next(), _DeskWiseDefaultProfileMstVO.getIsDefault());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _DeskWiseDefaultProfileMstVO.getDeskId());
			populateMAP.put(sq.next(), _DeskWiseDefaultProfileMstVO.getDeskMenuId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
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
