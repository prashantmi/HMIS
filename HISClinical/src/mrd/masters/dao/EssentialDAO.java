package mrd.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import mrd.vo.UnitWiseEstProcedureMappingMstVO;
import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.RegistrationConfig;

public class EssentialDAO extends DataAccessObject implements EssentialDAOi
{
	
	Logger log;

	public EssentialDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public List getRackNameList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List rackNameList=new ArrayList();
				
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.HPMRSTR_RACK_NAME.HPMRT_RACK_MST";

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
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
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
			rackNameList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rackNameList;
		
	}
	
	public List getRecordType(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List recordTypeList=new ArrayList();
				
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.RECORDTYPE.HPMRT_FLAG_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 		
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
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
			recordTypeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return recordTypeList;
		
	}
	
	//get list of all Mrd
	public List getMrdList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_ALL.HPMRT_MRD_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Mrd Found");
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

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	
	public List getBuilding(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List buildingList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_BUILDING.EST_BUILDING_MST";
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				buildingList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return buildingList;
	}
	
	public List getItemList(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List itemList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_ITEM.HSTT_ITEM_MST";
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				itemList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return itemList;
	}
	
	
	public List getBlockList(String buildingCode,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List areaTypeList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_BLOCK.EST_BLOCK_MST";
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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), buildingCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				areaTypeList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return areaTypeList;
	}
	
	public List getFloorList(String blockId,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List areaTypeList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_FLOOR.EST_FLOOR_MST";
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
			populateMAP.put(sq.next(), blockId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){rs.beforeFirst();
				areaTypeList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return areaTypeList;
	}
	
	public List getRoomList(String floorId,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List roomList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_ROOM.EST_ROOM_MST";
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
			populateMAP.put(sq.next(), floorId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){rs.beforeFirst();
				roomList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return roomList;
	}
	
	public List getAllDepartment(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "GET_ALL_DEPT.GBLT_DEPARTMENT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMstEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Department Found");
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

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MortuaryMstEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
		
	}
	
	//Getting The List of Employee on the Basis of Department
	public List getEmployeeListBasedOnDept(String deptCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "GET_ALL_EMPLOYEE_BASED_ON_DEPT.HGBT_UNIT_CONSULTANT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), deptCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), deptCode);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMstEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Found");
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

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MortuaryMstEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public List getAllMrdRecordType(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List recordTypeList=new ArrayList();
				
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT.RECORDTYPE.HPMRT_MRD_RECORDTYPE_MST";

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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 		
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012

		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
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
			recordTypeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return recordTypeList;
		
	}

	public List getRackBasedOnMrd(String mrdCode,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List rackList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_RACK_BASED_ON_MRD.HPMRT_RACK_MST";
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
			populateMAP.put(sq.next(), mrdCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				rackList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return rackList;
	}
	
	public List getShelfBasedOnRack(String rackId,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List shelfList=new ArrayList();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "SELECT_SHELF_BASED_ON_RACK.HPMRT_RACK_SHELF_MST";
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
			populateMAP.put(sq.next(), rackId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			ResultSet rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				rs.beforeFirst();
				shelfList=HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return shelfList;
	}
	
	//added by Manisha Gangwar Date: 05.Nov.15 for Unit Wise Estimate Procedure Mapping ,  Module: MRD
	
		// * Getting Clinical Department List
			public List getAllClinicalDepartmentList(UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
				String queryKey = "ESSENTIAL.ALL_CLINICAL_DEPT.GBLT_DEPARTMENT_MST";

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
					populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
				}

				try
				{
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
					if (!rs.next()) throw new HisRecordNotFoundException("No Department record Exists in database  ");
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
					throw new HisDataAccessException("HisDataAccessException  :getAllDepartment" + e);
				}
				return alRecord;
			}
			
			//added by Manisha Gangwar Date: 05.Nov.15 for Unit Wise Estimate Procedure Mapping Master,  Module: MRD
			public List getAllUnit(UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();
				String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
				String queryKey = "SELECT.ALL_UNIT.HGBT_UNIT_MST";
				
				// first call the getQueryMethod with arguments filename,querykey from prop file
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException(
							"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}

				Sequence sq = new Sequence();

				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());

				try
				{
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
					
				}
				catch (HisRecordNotFoundException e)
				{
					throw new HisRecordNotFoundException(e.getMessage());
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
					throw new HisDataAccessException("Essential dao" + e);
				}
				return alRecord;
			}
			
			//added by Manisha Gangwar Date: 05.Nov.15 for Unit Wise Estimate Procedure Mapping  ,  Module: MRD
			public List getProcedureUnitListEssential(UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();
				List chartList=new ArrayList();
				String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
				String queryKey = "SELECT_PROCEDURE_UNIT_LIST.HPMRT_PROCEDURE_MST";

				//first call the getQueryMethod with arguments filename,querykey from prop file
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException(
							"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}

				Sequence sq = new Sequence();
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());

				try
				{
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
//					
					
					if(rs.next())
					{
						chartList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
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
				return chartList;
			}
			
			//added by Manisha Gangwar Date: 05.Nov.15 for Unit Wise Estimate Procedure Mapping ,  Module: MRD
			public List getAllUnitNotMappedWithProcedureList(UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();

				String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
				String queryKey = "SELECT.ALL.UNIT.NOT.IN.HPMRT_PROCEDURE_UNIT_MAPPING";
				
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException(
							"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				
				Sequence sq = new Sequence();

				try
				{
					populateMAP.put(sq.next(),_userVO.getHospitalCode());
					populateMAP.put(sq.next(),_userVO.getHospitalCode());
					populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(),_userVO.getHospitalCode());
					populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
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
				List alRecord = new ArrayList();
				try
				{
					if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HisDataAccessException  :getNotAddedUnitList" + e);
				}
				return alRecord;
			}
			
			//added by Manisha Gangwar Date: 05.Nov.15 for Unit Wise Estimate Procedure Mapping  ,  Module: MRD
			public UnitWiseEstProcedureMappingMstVO[] getUnitProcedureByDeptUnit(String _deptUnitCode, UserVO _userVO)
			{

				ResultSet rs = null;
				String query = "";
				ValueObject[] vo={};
				UnitWiseEstProcedureMappingMstVO[] unitChartListMasterVO=null;
				Map populateMAP = new HashMap();
				String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
				String queryKey = "ESSENTIAL.HPMRT_PROCEDURE_UNIT_MAPPING";
				// first call the getQueryMethod with arguments filename,querykey from prop file
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException(
							"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}

				System.out.println("query" + query);
				Sequence sq = new Sequence();

				populateMAP.put(sq.next(),_userVO.getHospitalCode());	
				populateMAP.put(sq.next(), _deptUnitCode);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
				

				try
				{
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
					if (!rs.next()) 
					{
						//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
					}
					else
					{
						rs.beforeFirst();
						vo=HelperMethods.populateVOfrmRS(UnitWiseEstProcedureMappingMstVO.class,rs);
						unitChartListMasterVO=new UnitWiseEstProcedureMappingMstVO[vo.length];
						for(int i=0;i<vo.length;i++)
						{
							unitChartListMasterVO[i]=(UnitWiseEstProcedureMappingMstVO)vo[i];
						}
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

				

				return unitChartListMasterVO;
			}
			
			
			
			
			
}
