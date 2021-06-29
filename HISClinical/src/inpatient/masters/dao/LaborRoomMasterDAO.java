package inpatient.masters.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import inpatient.InpatientConfig;
import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.LaborRoomMasterVO;
import inpatient.masters.dao.LaborRoomMasterDAOi;

public class LaborRoomMasterDAO extends DataAccessObject implements LaborRoomMasterDAOi
{
	Logger log;

	public LaborRoomMasterDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}
	
	public List getDeskType(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.GBLT_DESK_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("OpdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public boolean checkDuplicateLaborRoom(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO)

	{

		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	
	    String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	    String queryKey="SELECT.DUPLICATE_CHECK.HANCT_LABOR_ROOM_MST";
	   	
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
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getDepartmentCode());
	    	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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
	
	public void create(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HANCT_LABOR_ROOM_MST";

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
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomName());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomShortName());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomDescription());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getDepartmentCode());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserVO.getUserSeatId());
        	
        		
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
	
	public LaborRoomMasterVO fetchRecord(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        LaborRoomMasterVO laborRoomMasterVo = new LaborRoomMasterVO();
        
//        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;

        String queryKey="SELECT.FULL_INFO.HANCT_LABOR_ROOM_MST";

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
        	
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomId());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getSlNo());
         	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
			rs.beforeFirst();
			while(rs.next())
			{
				
				laborRoomMasterVo.setLaborRoomId(rs.getString(1));
				laborRoomMasterVo.setSlNo(rs.getString(2));
				laborRoomMasterVo.setLaborRoomName(rs.getString(3));
				laborRoomMasterVo.setLaborRoomShortName(rs.getString(4));
				laborRoomMasterVo.setLaborRoomDescription(rs.getString(5));
				laborRoomMasterVo.setEntryDate(rs.getString(6));
				laborRoomMasterVo.setIsValid(rs.getString(7));
				laborRoomMasterVo.setDepartmentCode(rs.getString(8));
				laborRoomMasterVo.setHospitalCode(rs.getString(9));
			}
				
			}
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
        
       
        return laborRoomMasterVo;   

	}
	
	public boolean check_Modify_DuplicateLaborRoom(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	   	String queryKey="SELECT.MODIFY_DUPLICATE_CHECK.HANCT_LABOR_ROOM_MST";
	   	
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
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getDepartmentCode());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomName());
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomId());
        	
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

	public void update(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO)
	{
		
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="UPDATE.HANCT_LABOR_ROOM_MST";

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
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),_UserVO.getUserSeatId());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getLaborRoomId());
        	populateMAP.put(sq.next(),_LaborRoomMasterVO.getSlNo());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("LaborRoomMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
     
        
	}
	
	public void modifySave(LaborRoomMasterVO laborRoomMasterVO,UserVO _userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.MODIFY.HANCT_LABOR_ROOM_MST";

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
        	populateMAP.put(sq.next(),laborRoomMasterVO.getLaborRoomId());
        	populateMAP.put(sq.next(),laborRoomMasterVO.getLaborRoomId());
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
           	populateMAP.put(sq.next(),laborRoomMasterVO.getLaborRoomName());
        	populateMAP.put(sq.next(),laborRoomMasterVO.getLaborRoomShortName());
        	populateMAP.put(sq.next(),laborRoomMasterVO.getLaborRoomDescription());
        	populateMAP.put(sq.next(),laborRoomMasterVO.getDepartmentCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_userVO.getHospitalCode());
        	populateMAP.put(sq.next(),_userVO.getUserSeatId());
        	
        		
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}

}
