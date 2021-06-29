package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import registration.RegistrationConfig;
 

/**
 * PatientDAO is a class which describes all the methods required for database interaction for HRGT_PATIENT_DTL table, for example, insert, update, select
 * and delete. PatientDAO class provides a standard interface between Business Objects and Database.
 * @author AHIS
 */
public class PatientDAO extends RegistrationDAO implements PatientDAOi {

//	Logger log;
	/**
	 * Creates a new PatientDAO object.
	 * @param _transactionContext	Provides the lock on a transaction.
	 */
	public PatientDAO(TransactionContext _transactionContext) 
	{
		super(_transactionContext);
		//log = LogManager.getLogger(this.getClass());
	}
	
	 
	
	
	
	
	
	
	
	
	
	
	
	public PatientVO[] retrieveByContactNo(String contactNo, AddressVO[] _addressVO, UserVO _userVO) {
		ValueObject[] vo = {};
		PatientVO[] _patientVO={};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_CONTACT_NUMBER";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), contactNo);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByContactNo:HelperMethods :: " + e);
		}
		return _patientVO;
	}

	
	
	
	
	public PatientVO[] retrieveByName(String _patientName, AddressVO[] _addressVO,  UserVO _userVO) {
		PatientVO[] _patientVO={};
		ValueObject[] vo = {};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_NAME";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), _patientName);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
			
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);

		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException("No Record Found Against This Name");
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}

		return _patientVO;
	}
		
	
	public PatientVO[] retrieveByEmployeeID(String nationalID, AddressVO[] _addressVO, UserVO _uservo) {
		ValueObject[] vo = {};
		PatientVO[] _patientVO={};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_EMPLOYEEID";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), nationalID);
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else 
				throw new HisDataAccessException("PatientDAO:retrieveByNationalID:HelperMethods :: " + e);
		}
		
		return _patientVO;
	}

	
	public PatientVO[] retrieveByNationalID(String nationalID, AddressVO[] _addressVO, UserVO _uservo) {
		ValueObject[] vo = {};
		PatientVO[] _patientVO={};
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.RETRIEVE_BY_NATIONALID";
		Sequence sq = new Sequence();

		_populateMapPatientDtl.put(sq.next(), nationalID+"%");
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		Connection conn = super.getTransactionContext().getConnection();
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query...." + query);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				System.out.println("Record found");
				System.out.println("rs" + rs.getString(1));
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				_patientVO = new PatientVO[vo.length];
				System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					_patientVO[i] = (PatientVO) vo[i];
					System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
				
				for (int i = 0; i < _patientVO.length; i++)
				_patientVO[i].setPatAddress(_addressVO[i]);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByNationalID:HelperMethods :: " + e);
		}
		
		return _patientVO;
	}
public PatientVO[] searchPatient(HashMap _searchMap,UserVO _userVO)
	
	{
		PatientVO[] patientVOs=null;
		AddressVO[] addressVOs=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		//PatientVO[] _patVO=null;
		String query="";
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.SEARCH.PATIENT";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		int Rs;
		String orderBy=" ORDER BY  initcap(a.hrgstr_fname||a.hrgstr_mname||a.hrgstr_lname),a.hrgstr_father_name ";
		Sequence sq = new Sequence();
		
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		//////////////Preparing query to fetch patient records using patient details/////////
				
		
		Set keySet=(Set)_searchMap.keySet();
		
		Iterator keySetItr=keySet.iterator();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_searchMap.get(mapKey);
			mapValue=mapValue.trim();
			if(mapKey.equals("gnum_city_loc_code")){
				whereCOndition=whereCOndition+" AND "+"( UPPER (b.hrgstr_city_location) LIKE UPPER ('"+mapValue+"%'))";
			}
			else if(mapKey.equalsIgnoreCase("fromDate")){
				
				whereCOndition=whereCOndition+" AND a.hrgnum_puk IN (SELECT hrgnum_puk FROM hrgt_episode_dtl WHERE "+
					"TRUNC (hrgdt_episode_start_date)>= trunc(to_date('"+_searchMap.get("fromdate")+"','dd-Mon-yyyy'))"+ 
					" and gnum_hospital_code=a.gnum_hospital_code)";
			}
			
			else if(mapKey.equalsIgnoreCase("toDate")){
				
				whereCOndition=whereCOndition+" AND a.hrgnum_puk IN (SELECT hrgnum_puk FROM hrgt_episode_dtl WHERE "+
				"TRUNC (hrgdt_episode_start_date)<= trunc(to_date('"+_searchMap.get("todate")+"','dd-Mon-yyyy'))"+ 
				" and gnum_hospital_code=a.gnum_hospital_code)";
			}
			else if(mapKey.equals("hrgstr_age")){
				String lowerLimit=mapValue.split("-")[0];
				String upperLimit=mapValue.split("-")[1];

				whereCOndition=whereCOndition+"AND round(date_part('day',((TO_DATE(TO_CHAR(sysdate::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy')- TO_DATE(TO_CHAR(HRGDT_DOB::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy'))))/365) >" + lowerLimit ;
				whereCOndition=whereCOndition+" AND  round(date_part('day',((TO_DATE(TO_CHAR(sysdate::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy')- TO_DATE(TO_CHAR(HRGDT_DOB::timestamp,'dd-mm-yyyy'),'dd-mm-yyyy'))))/365) <=" + upperLimit ;
			}
			else if(mapKey.equals("hrgstr_file_no")){
				
				whereCOndition=whereCOndition+" AND a.hrgnum_puk IN (SELECT hrgnum_puk FROM hrgt_episode_dtl WHERE "+
				mapKey+" like '"+mapValue+"'"+ 
				" and gnum_hospital_code=a.gnum_hospital_code)";
			}
			else if(mapKey.equals("gnum_hospital_code")){
				if(!mapValue.equalsIgnoreCase("100")){
				whereCOndition=whereCOndition+" AND "+"( UPPER (a."+mapKey+") = ('"+mapValue+"'))";
				}
			}
		
			else
				whereCOndition=whereCOndition+" AND "+"( UPPER (a."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		finalQuery=query + whereCOndition + orderBy;
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("NO Patient found matching the search criteria");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
				addressVOs=new AddressVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					addressVOs[i]=(AddressVO)valueObjects[i];
					
				}
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				patientVOs=new PatientVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					patientVOs[i]=(PatientVO)valueObjects[i];
					patientVOs[i].setPatAddress(addressVOs[i]);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("PatientDAO:searchPatient() Data access exception "+e);
		}
		
		return patientVOs;
	}

	
	
}//end of class
