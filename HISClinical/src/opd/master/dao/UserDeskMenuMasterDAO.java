package opd.master.dao;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserDeskMenuMasterDAO extends DataAccessObject
{
	Logger log;
	
	public UserDeskMenuMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}
	
	//* Inserting UserDeskMenu Record
	public void create(UserDeskMenuMasterVO _UserDeskMenuVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_MST";

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
        	//populateMAP.put(sq.next(),_UserVO.getUserSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getUserSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getWardCode());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getIsValid());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskType());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getMappingType());
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

	//* Fetching User Desk Menu Record
	public UserDeskMenuMasterVO fetchRecord(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UserDeskMenuMasterVO deskMenuMstVo = new UserDeskMenuMasterVO();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.FULL_INFO.GBLT_USER_DESKMENU_MST";
        
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
        	populateMAP.put(sq.next(),_UserDeskVO.getDeskType());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				HelperMethods.populateVOfrmRS(deskMenuMstVo, rs);
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
        
       
        return deskMenuMstVo;   
	}
	
	public UserDeskMenuMasterVO fetchModifyRecord(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UserDeskMenuMasterVO deskMenuMstVo = new UserDeskMenuMasterVO();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.GBLT_USER_DESKMENU_MST";
        
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
        	//populateMAP.put(sq.next(),_UserDeskVO.getUserDeskMenuId());
        	populateMAP.put(sq.next(),_UserDeskVO.getDeskType());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskVO.getMappingSeqNo());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				HelperMethods.populateVOfrmRS(deskMenuMstVo, rs);
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
        
       
        return deskMenuMstVo;   
	}
	
	
	
	//modified by manisha gangwar date: 24.5.2016
	//* Updating User Desk Menu Record
	public void update(UserDeskMenuMasterVO _UserDeskMenuVO, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.GBLT_USER_DESKMENU_MST";

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
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getIsValid());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskType());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getMappingSeqNo());
        	
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
	
	//* Updating User Desk Menu Record
	public void updateUnitWise(UserDeskMenuMasterVO _UserDeskMenuVO, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE_UNITWISE.GBLT_USER_DESKMENU_MST";

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
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskType());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getIsValid());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	//populateMAP.put(sq.next(),_UserDeskMenuVO.getUserSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getUserDeskMenuId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
	
	public ArrayList fetchRecordForUnitWise(UserDeskMenuMasterVO _UserDeskVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        ArrayList retAL=new ArrayList();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.UNIT_WISE_FULL_INFO.GBLT_USER_DESKMENU_MST";
        
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
        	populateMAP.put(sq.next(),_UserDeskVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_UserDeskVO.getDeskId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskVO.getIsValid());
        	populateMAP.put(sq.next(),_UserDeskVO.getUserDeskMenuId());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No Desk Exists... ");
            rs.first();
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
        try
        {
        	Entry entobj=new Entry("UnitName",rs.getString(1));
        	retAL.add(entobj);
        	entobj = new Entry("DeskName",rs.getString(2));
        	retAL.add(entobj);
        	/*entobj = new Entry("DeskTypeDesc",rs.getString(3));
        	retAL.add(entobj);*/
        	entobj = new Entry("DeskType",rs.getString(3));
        	retAL.add(entobj);
        	
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException  :fetchRecord"+e);
        }
        return retAL;   
	}

	
}