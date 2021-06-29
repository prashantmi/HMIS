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
import enquiry.vo.HospitalChargeEnquiryVO;
import enquiry.vo.HospitalFacilityMasterVO;


public class HospitalChargeEnquiryDAO extends DataAccessObject{
	
private static Object lock = new Object();

	
	public HospitalChargeEnquiryDAO(TransactionContext _tx) {
		super(_tx);
	}
	public Object getLock() {
		return lock;		
	}
	
	public List getAllHospitalChargeList(String groupId, UserVO _uservo) {
		HospitalChargeEnquiryVO hospitalChargeVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL_TARIFF.HBLT_TARIFF_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),groupId);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}	
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					hospitalChargeVO=new HospitalChargeEnquiryVO();
					HelperMethods.populateVOfrmRS(hospitalChargeVO, rs);
					voList.add(hospitalChargeVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HospitalChargeEnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getGroupList(UserVO _uservo) {
		 
		List groupList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.GROUP_NAME.HBLT_GROUP_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}	
			else{
				groupList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalChargeEnquiryDAO: :: " + e);
		}
		return groupList;
	}
	
	public List getChargeTypeList(UserVO _uservo) {
		
		List chargeTypeList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.CHARGE_TYPE.SBLT_CHARGETYPE_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}	
			else{
				chargeTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalChargeEnquiryDAO: :: " + e);
		}
		return chargeTypeList;
	}
	
	public List getChargeByTariff(String tariffId,UserVO _uservo) {
		HospitalChargeEnquiryVO hospitalChargeVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.CHARGE_DETAIL.BY_TARIFF.HBLT_CHARGE_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),tariffId);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("No Record Found");
			}	
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					hospitalChargeVO=new HospitalChargeEnquiryVO();
					HelperMethods.populateVOfrmRS(hospitalChargeVO, rs);
					voList.add(hospitalChargeVO);
				}
			}
		} 
		catch (Exception e) {
			throw new HisDataAccessException("HospitalChargeEnquiryDAO: :: " + e);
		}
		return voList;
	}
	
}


