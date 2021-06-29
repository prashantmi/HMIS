package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DoctorDesigMappingVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DoctorDesigMappingDAO extends DataAccessObject implements DoctorDesigMappingDAOi {

	Logger log;

	public DoctorDesigMappingDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
		
	public List getProcessType(){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		//Sequence sq = new Sequence();
		List prcoessTypeList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.HPMRT_FLAG_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				prcoessTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("No Process Type Found");
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
		
		return prcoessTypeList;
	}
	
	public List getNotAssignedDesignationList(String processType,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		String pType = processType;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List designationList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.DESIGNATION.gblt_designation_mst";
		
		
		try
		{
			
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//	populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), processType);
				
			
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DoctorDesigMappingDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				designationList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("Designation Not Found or All are Mapped");
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
		
		return designationList;
	}
	
	public List getAssignedDesignationList(String processType,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List assignedDesignationList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.HGBT_DOCTOR_DESIG_MAPPING";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			
			populateMAP.put(sq.next(), processType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DoctorDesigMappingDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				assignedDesignationList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("");
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
		
		return assignedDesignationList;
	}
	
	
	public void modify(DoctorDesigMappingVO doctorDesigMappingVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "UPDATE.HGBT_DOCTOR_DESIG_MAPPING";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), doctorDesigMappingVO.getProcessType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), doctorDesigMappingVO.getDesignationId());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DoctorDesigMappingDAO.populateMAP::" + e);
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
	
	public void insert(DoctorDesigMappingVO doctorDesigMappingVO ,UserVO userVO) {
		
		//ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HGBT_DOCTOR_DESIG_MAPPING";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), doctorDesigMappingVO.getProcessType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), doctorDesigMappingVO.getDesignationCode());
			populateMAP.put(sq.next(), doctorDesigMappingVO.getProcessType());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), doctorDesigMappingVO.getDesignationId());
			populateMAP.put(sq.next(), doctorDesigMappingVO.getRemarks());
			populateMAP.put(sq.next(), doctorDesigMappingVO.getMaxDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DoctorDesigMappingDAO.populateMAP::" + e);
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
	
	public String getDesignationName(String designationId,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		String designationName="";
		
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.DESIG_NAME.gblt_designation_mst";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try
		{								
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 		
			populateMAP.put(sq.next(), designationId);
					
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdMasterDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				throw new HisRecordNotFoundException("Record Not Found");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			designationName=rs.getString(1);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return designationName;
	}
	
	
}
