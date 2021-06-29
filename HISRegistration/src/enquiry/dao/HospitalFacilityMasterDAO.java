package enquiry.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enquiry.enquiryConfig;
import enquiry.vo.HospitalFacilityMasterVO;


public class HospitalFacilityMasterDAO extends DataAccessObject{
	
private static Object lock = new Object();

	
	public HospitalFacilityMasterDAO(TransactionContext _tx) {
		super(_tx);
	}
	public Object getLock() {
		return lock;		
	}
	
	public List getAllHospitalFacilityList(String isValid, UserVO _uservo) {
		HospitalFacilityMasterVO hospitalFacilityVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL.HGBT_HOSPITAL_FACILITY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),isValid);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}	
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					hospitalFacilityVO=new HospitalFacilityMasterVO();
					HelperMethods.populateVOfrmRS(hospitalFacilityVO, rs);
					voList.add(hospitalFacilityVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HospitalFacilityDAO: :: " + e);
		}
		return voList;
	}
	
	public void saveHospitalFacility(HospitalFacilityMasterVO  hospitalFacilityMasterVO,UserVO _uservo) {
		
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "INSERT.HGBT_HOSPITAL_FACILITY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityName());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityDescType());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityDesc());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getIsEmergencyService());
		//_populateMap.put(sq.next(),hospitalFacilityMasterVO.getDisplayOrder());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getIsLocationSpecific());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getIsValid());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getLocationQuery());
		_populateMap.put(sq.next(),_uservo.getSeatId());
		
		try {
			 HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalFacilityDAO: :: " + e);
		}
	}
		
	public void modifyInsertHospitalFacility(HospitalFacilityMasterVO  hospitalFacilityMasterVO,UserVO _uservo) {
		
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "MODIFY.INSERT.HGBT_HOSPITAL_FACILITY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityId());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityId());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityName());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityDescType());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityDesc());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getIsEmergencyService());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getDisplayOrder());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getIsLocationSpecific());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getIsValid());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getLocationQuery()==null?"":hospitalFacilityMasterVO.getLocationQuery());
		_populateMap.put(sq.next(),_uservo.getSeatId());
		
		try {
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalFacilityDAO: :: " + e);
		}
	}
	
	public void modifyHospitalFacility(HospitalFacilityMasterVO  hospitalFacilityMasterVO,UserVO _uservo) {
		
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "UPDATE.HGBT_HOSPITAL_FACILITY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		_populateMap.put(sq.next(),_uservo.getSeatId());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityId());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityId());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
				
		try {
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalFacilityDAO: :: " + e);
		}
	}
	
	
	public HospitalFacilityMasterVO getHospitalFacilityList(HospitalFacilityMasterVO hospitalFacilityVO,UserVO _uservo) {
		//HospitalFacilityMasterVO hospitalFacilityVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.HGBT_HOSPITAL_FACILITY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),hospitalFacilityVO.getFacilityId());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),hospitalFacilityVO.getSlNo());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}	
			else{
				HelperMethods.populateVOfrmRS(hospitalFacilityVO, rs);
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HospitalFacilityDAO: :: " + e);
		}
		return hospitalFacilityVO;
	}

	public void changeDisplayOrder(HospitalFacilityMasterVO  hospitalFacilityMasterVO,UserVO _uservo) {
		
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "CHANGE_DISPLAY_ORDER.HGBT_HOSPITAL_FACILITY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getDisplayOrder());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getFacilityId());
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),hospitalFacilityMasterVO.getSlNo());
		
				
		try {
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalFacilityDAO: :: " + e);
		}
	}
	
}


