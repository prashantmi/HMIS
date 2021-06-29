package inpatient.masters.dao;

/**
 * @author  CDAC
 */

import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Sequence;

import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UnitInvParaMappingMstDAO extends DataAccessObject 
{	
	Logger log;	
	
	public UnitInvParaMappingMstDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public void createUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HGBT_DEPTUNITWISE_INV_MST.UNIT_WISE";
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
        	populateMAP.put(sq.next(),_voUDMT.getUnitId());
        //	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_voUDMT.getUnitId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getParaId());
        	populateMAP.put(sq.next(),_voUDMT.getParaId());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_voUDMT.getWardCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UnitInvParaMappingMstDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	}
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
}

	public void updateTableUnitWise(String _unitId,UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="UDPATE.HGBT_DEPTUNITWISE_INV_MST.UNIT_WISE";
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
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_unitId);
        
        
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UnitInvParaMappingMstDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        	}
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public void updateTableWardWise(String _unitId,String _wardCode, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="UDPATE.HGBT_DEPTUNITWISE_INV_MST.WARD_WISE";

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
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_unitId);
        	
        	populateMAP.put(sq.next(),_wardCode);
        
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UnitInvParaMappingMstDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        	}
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	
	public void createUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{	
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HGBT_DEPTUNITWISE_INV_MST.UNITWARD_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getUnitId());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_voUDMT.getUnitId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getParaId());
        	populateMAP.put(sq.next(),_voUDMT.getParaId());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_voUDMT.getWardCode());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UnitInvParaMappingMstDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	}
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	//* Fetching User Desk Menu Record
	public UnitInvParaMappingVO fetchRecord(UnitInvParaMappingVO _UnitInvParaVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UnitInvParaMappingVO unitInvParaVO = new UnitInvParaMappingVO();
        
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.FORMODIFY.HGBT_DEPTUNITWISE_INV_MST";
        
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
        	//populateMAP.put(sq.next(),_UnitInvParaVO.getSlNo());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UnitInvParaVO.getUnitId());
        	populateMAP.put(sq.next(),_UnitInvParaVO.getSlNo());
        	
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
				unitInvParaVO.setSlNo(rs.getString(1));
				unitInvParaVO.setUnitId(rs.getString(2));
				unitInvParaVO.setDisplayValue(rs.getString(3));
				unitInvParaVO.setParaId(rs.getString(4));
				unitInvParaVO.setWardCode(rs.getString(5));
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
        return unitInvParaVO;   
	}
	
	public UnitInvParaMappingVO fetchParameter(String _slno,String _unitId,String _wardCode, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UnitInvParaMappingVO unitInvParaVO = new UnitInvParaMappingVO();
        
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.PARAMETERS_FORVIEW";
        
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
        	populateMAP.put(sq.next(),_slno);
        	populateMAP.put(sq.next(),_unitId);
        	        	
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
				unitInvParaVO.setParaName(rs.getString(1));
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
        return unitInvParaVO;   
	}
	
	public List gettingWards(String _deptUnitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List wards = new ArrayList();
		
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.WARDLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _deptUnitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _deptUnitCode);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next()) wards = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :getUserDeskMenuSeatsInAllUnits" + e);
		}
		return wards;
	}
	
	public UnitInvParaMappingVO getParameterName(String _paraCode, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey="SELECT.PARAMETER.MODIFY";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_paraCode);
			populateMap.put(sq.next(),_userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
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
		UnitInvParaMappingVO paraName = new UnitInvParaMappingVO();
		try
		{
			if(rs.next())
			{
				paraName.setParaId(rs.getString(1));
				paraName.setDeptUnitCode(rs.getString(2));
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllDepartment"+e);
		}
		return paraName;
	}
	
	public List getParaListForModify(String _slno,String _unitId,String _wardCode,UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		
		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey="SELECT.PARAMETERLISTFORMODIFY";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_userVO.getHospitalCode());
		//	populateMap.put(sq.next(),_slno);
			populateMap.put(sq.next(),_unitId);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if (!rs.next()) throw new HisRecordNotFoundException("No Records found");
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
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

			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}
	
public List getParaListForWardWise(String _slno,String _unitId,String _wardCode,UserVO _userVO)
{
	ResultSet rs=null;
	String query =  "" ;
	Map populateMap =new HashMap();
	
	String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	String queryKey="SELECT.PARAMETERLISTFORWARDWISE";

	Sequence sq=new Sequence();
	
	try
	{
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_userVO.getHospitalCode());
	//	populateMap.put(sq.next(),_slno);
		populateMap.put(sq.next(),_unitId);
		populateMap.put(sq.next(),_wardCode);
	}
	catch(Exception e)
	{
		throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
	}
	
	try
	{
		query =HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	}
	
	System.out.println("   -------> query :: "+query);
	
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		if (!rs.next()) throw new HisRecordNotFoundException("No Records found");
	}
	catch (Exception e)
	{

		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
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

		throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
	
	return alRecord;
}

}
