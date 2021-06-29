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
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;

public class DeskDetailDAO extends DataAccessObject 
{
	Logger log;
	
	public DeskDetailDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	//* Inserting Desk Detail Data
	public void create(DeskDetailVO _DeskDtlVO, UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_DESK_DTL";
        
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
        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskId());
        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskMenuId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskId());
        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskMenuId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_DeskDtlVO.getLocation());
        	populateMAP.put(sq.next(),_DeskDtlVO.getDisplayOrder());
        	populateMAP.put(sq.next(),_DeskDtlVO.getUserDeskMenuName());
        	populateMAP.put(sq.next(),_DeskDtlVO.getColor());
        	populateMAP.put(sq.next(),_DeskDtlVO.getIsLoginBound());
        	if(_DeskDtlVO.getDutyRoleId().trim().equals("") || _DeskDtlVO.getDutyRoleId().trim().equals("-1"))
        		_DeskDtlVO.setDutyRoleId(null);
        	populateMAP.put(sq.next(),_DeskDtlVO.getDutyRoleId());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_DeskDtlVO.getIsValid());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("DeskDetailDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	
	//* Inserting Desk Detail Data
		public void createGlobal(DeskDetailVO _DeskDtlVO, UserVO _UserVO)
		{
			String query  = "";
	        Map populateMAP =new HashMap();
	        Sequence sq=new Sequence();
	        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	        String queryKey="INSERT.GBLT_DESK_DTL";
	        
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
	        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskId());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskMenuId());
	        	populateMAP.put(sq.next(),Config.SUPER_HOSPITAL_CODE);
	            
	        //	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskId());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getDeskMenuId());
	        	populateMAP.put(sq.next(),Config.SUPER_HOSPITAL_CODE);
	            
	        //	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getLocation());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getDisplayOrder());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getUserDeskMenuName());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getColor());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getIsLoginBound());
	        	if(_DeskDtlVO.getDutyRoleId().trim().equals("") || _DeskDtlVO.getDutyRoleId().trim().equals("-1"))
	        		_DeskDtlVO.setDutyRoleId(null);
	        	populateMAP.put(sq.next(),_DeskDtlVO.getDutyRoleId());
	        	populateMAP.put(sq.next(),_UserVO.getSeatId());
	        	populateMAP.put(sq.next(),_DeskDtlVO.getIsValid());
	        }
	        catch(Exception e){
	        	throw new HisApplicationExecutionException("DeskDetailDAO.populateMAP::"+e);
	        }
	        try{
	        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	        }
		}
	
	
	// Getting Desk Detail VO Array By Desk
	public DeskDetailVO[] selectMenuListByDeskId(DeskMasterVO _voDeskMst, UserVO _UserVo)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        DeskDetailVO[] voDskDtlLst = new DeskDetailVO[0];
		ValueObject[] vo= {};
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.ALL_BY_DESK_ID_BY_LOC.GBLT_DESK_DTL";
        
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
        	populateMAP.put(sq.next(),_voDeskMst.getDeskId());
        	populateMAP.put(sq.next(),_UserVo.getHospitalCode());
        	populateMAP.put(sq.next(),_voDeskMst.getIsValid());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DeskDetailDAO.populateMAP::"+e);
        }
        
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		return voDskDtlLst;
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
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DeskDetailVO.class,rs);
			voDskDtlLst = new DeskDetailVO[vo.length];
			for(int i=0; i<vo.length; i++) voDskDtlLst[i] = (DeskDetailVO)vo[i];
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException  :selectMenuListByDeskId"+e);
        }
        return voDskDtlLst;   
	}
	
	

	// Getting Desk Detail VO Array By Desk
	public DeskDetailVO[] selectMenuListByGlobalDeskId(DeskMasterVO _voDeskMst, UserVO _UserVo)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        DeskDetailVO[] voDskDtlLst = new DeskDetailVO[0];
		ValueObject[] vo= {};
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.ALL_BY_DESK_TYPE_BY_LOC_FROM_GBL.GBLT_DESK_DTL";
        
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
        	populateMAP.put(sq.next(),_voDeskMst.getDeskType());
            populateMAP.put(sq.next(),Config.SUPER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),Config.SUPER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DeskDetailDAO.populateMAP::"+e);
        }
        
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		return voDskDtlLst;
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
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DeskDetailVO.class,rs);
			voDskDtlLst = new DeskDetailVO[vo.length];
			for(int i=0; i<vo.length; i++) voDskDtlLst[i] = (DeskDetailVO)vo[i];
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HisDataAccessException  :selectMenuListByDeskId"+e);
        }
        return voDskDtlLst;   
	}
	
	//* Deleting All Desk Menus Details By Desk Id
	public void deleteDeskMenus(String _DeskId, UserVO _UserVo)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.BY_DESK_ID.GBLT_DESK_DTL";
        
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
        	populateMAP.put(sq.next(),_DeskId);
        	populateMAP.put(sq.next(),_UserVo.getHospitalCode());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("DeskDetailDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public void deleteGlobalDeskMenus(String _DeskId, UserVO _UserVo)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.BY_DESK_ID.GBLT_DESK_DTL";
        
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
        	populateMAP.put(sq.next(),_DeskId);
        	populateMAP.put(sq.next(),Config.SUPER_HOSPITAL_CODE);
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("DeskDetailDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}


}