package registration.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.RegistrationConfig;
/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class AddressDAO extends RegistrationDAO implements AddressDAOi
{
	public AddressDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	
	/**
	 * Creates a new address entry in DB for a patient.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	AddressVO with values stored in DB.
	 */
    
    
    
    
    
    
	 public AddressVO[] retrieveByName(String _patientName, UserVO _userVO){
	    	ValueObject[] vo = {};
	    	AddressVO[] _addressVO=new AddressVO[]{};
	    	Map _populateMapPatientAddDtl =new HashMap();
	    	//System.out.println("_patientVO.getPatAddTypeCode()::...."+_patientVO.getPatAddTypeCode());
	    	//System.out.println("_patientVO.getPatCrNo() in dao"+_patientVO.getPatCrNo());    	

	    	Sequence sq=new Sequence();
	    	String query="";
	    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	    	
	    	String queryKey="SELECT.HRGT_PATIENT_ADD_DTL.RETRIEVE_BY_NAME.ADD_TYPE";
	    	_populateMapPatientAddDtl.put(sq.next(),_patientName);
	    	_populateMapPatientAddDtl.put(sq.next(),Config.IS_VALID_ACTIVE); 
	           	
	    	Connection conn =super.getTransactionContext().getConnection();    	
	    	try{
	    		query =HelperMethodsDAO.getQuery(filename,queryKey);
	    		
	    	}
	    	catch(Exception e){
	    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    	}
	    	
	    	try{
	    		
	    		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,_populateMapPatientAddDtl);

	    		
	    		//HelperMethods.populateVOfrmRS(_addressVO, rs);
	    		
	    		    		
	    		if (!rs.next()){
	    			System.out.println("no records");
	    			throw new HisRecordNotFoundException();       			
	    		}
	    		else
	    		{
	    			
	    			rs.beforeFirst();
	    			vo=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
	    			_addressVO = new AddressVO[vo.length];
					//System.out.println("_patientVO.length:: " + _patientVO.length);
					for (int i = 0; i < vo.length; i++) {
						
						_addressVO[i] = (AddressVO) vo[i];
						//System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
					}
	    			//System.out.println("_addressVO.getPatMiddleName()"+_patientVO.getPatMiddleName());
	    		}
	    	}
	    	catch(Exception e){
	    		if(e.getClass()==HisRecordNotFoundException.class){
	    			throw new HisRecordNotFoundException();    			
	    		}
	    		else    		
	    		throw new HisDataAccessException("AddressDAO:retrieveByCrNo:HelperMethods :: "+e);
	    	}
	    	
	    	return _addressVO;
	    }
	    
    
    
    
    
    
    
    public AddressVO[] retrieveByContactNo(String contactNo, UserVO _userVO){
    	ValueObject[] vo = {};
    	AddressVO[] _addressVO=new AddressVO[]{};
    	Map _populateMapPatientAddDtl =new HashMap();
    	//System.out.println("_patientVO.getPatAddTypeCode()::...."+_patientVO.getPatAddTypeCode());
    	//System.out.println("_patientVO.getPatCrNo() in dao"+_patientVO.getPatCrNo());    	

    	Sequence sq=new Sequence();
    	String query="";
    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
    	
    	String queryKey="SELECT.HRGT_PATIENT_ADD_DTL.RETRIEVE_BY_CONTACT_NO.ADD_TYPE";
    	_populateMapPatientAddDtl.put(sq.next(),contactNo);
    	_populateMapPatientAddDtl.put(sq.next(),Config.IS_VALID_ACTIVE); 
           	
    	Connection conn =super.getTransactionContext().getConnection();    	
    	try{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    		
    	}
    	catch(Exception e){
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    	
    	try{
    		
    		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,_populateMapPatientAddDtl);

    		
    		//HelperMethods.populateVOfrmRS(_addressVO, rs);
    		
    		    		
    		if (!rs.next()){
    			System.out.println("no records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			
    			rs.beforeFirst();
    			vo=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
    			_addressVO = new AddressVO[vo.length];
				//System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					
					_addressVO[i] = (AddressVO) vo[i];
					//System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
    			//System.out.println("_addressVO.getPatMiddleName()"+_patientVO.getPatMiddleName());
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();    			
    		}
    		else    		
    		throw new HisDataAccessException("AddressDAO:retrieveByCrNo:HelperMethods :: "+e);
    	}
    	
    	return _addressVO;
    } 
    
    
    
    public AddressVO[] retrieveByEmployeeID(String nationalID, UserVO _userVO){
    	ValueObject[] vo = {};
    	AddressVO[] _addressVO=new AddressVO[]{};
    	Map _populateMapPatientAddDtl =new HashMap();
    	//System.out.println("_patientVO.getPatAddTypeCode()::...."+_patientVO.getPatAddTypeCode());
    	//System.out.println("_patientVO.getPatCrNo() in dao"+_patientVO.getPatCrNo());    	

    	Sequence sq=new Sequence();
    	String query="";
    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
    	
    	String queryKey="SELECT.HRGT_PATIENT_ADD_DTL.RETRIEVE_BY_EMPLOYEE_ID.ADD_TYPE";
    	_populateMapPatientAddDtl.put(sq.next(),nationalID);
    	_populateMapPatientAddDtl.put(sq.next(),Config.IS_VALID_ACTIVE); 
           	
    	Connection conn =super.getTransactionContext().getConnection();    	
    	try{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    		
    	}
    	catch(Exception e){
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    	
    	try{
    		
    		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,_populateMapPatientAddDtl);

    		
    		//HelperMethods.populateVOfrmRS(_addressVO, rs);
    		
    		    		
    		if (!rs.next()){
    			System.out.println("no records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			
    			rs.beforeFirst();
    			vo=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
    			_addressVO = new AddressVO[vo.length];
				//System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					
					_addressVO[i] = (AddressVO) vo[i];
					//System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
    			//System.out.println("_addressVO.getPatMiddleName()"+_patientVO.getPatMiddleName());
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();    			
    		}
    		else    		
    		throw new HisDataAccessException("AddressDAO:retrieveByCrNo:HelperMethods :: "+e);
    	}
    	System.out.println("address vo length........................."+_addressVO.length);
    	return _addressVO;
    }
    
    
    public AddressVO[] retrieveByNationalID(String nationalID, UserVO _userVO){
    	ValueObject[] vo = {};
    	AddressVO[] _addressVO=new AddressVO[]{};
    	Map _populateMapPatientAddDtl =new HashMap();
    	//System.out.println("_patientVO.getPatAddTypeCode()::...."+_patientVO.getPatAddTypeCode());
    	//System.out.println("_patientVO.getPatCrNo() in dao"+_patientVO.getPatCrNo());    	

    	Sequence sq=new Sequence();
    	String query="";
    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
    	
    	String queryKey="SELECT.HRGT_PATIENT_ADD_DTL.RETRIEVE_BY_NATIONAL_ID.ADD_TYPE";
    	_populateMapPatientAddDtl.put(sq.next(),nationalID);
    	_populateMapPatientAddDtl.put(sq.next(),Config.IS_VALID_ACTIVE); 
           	
    	Connection conn =super.getTransactionContext().getConnection();    	
    	try{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    		
    	}
    	catch(Exception e){
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    	
    	try{
    		
    		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,_populateMapPatientAddDtl);

    		
    		//HelperMethods.populateVOfrmRS(_addressVO, rs);
    		
    		    		
    		if (!rs.next()){
    			System.out.println("no records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			
    			rs.beforeFirst();
    			vo=HelperMethods.populateVOfrmRS(AddressVO.class, rs);
    			_addressVO = new AddressVO[vo.length];
				//System.out.println("_patientVO.length:: " + _patientVO.length);
				for (int i = 0; i < vo.length; i++) {
					
					_addressVO[i] = (AddressVO) vo[i];
					//System.out.println("_patientVO[" + i + "].getPatCrNo"+ _patientVO[i].getPatCrNo());
				}
    			//System.out.println("_addressVO.getPatMiddleName()"+_patientVO.getPatMiddleName());
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();    			
    		}
    		else    		
    		throw new HisDataAccessException("AddressDAO:retrieveByCrNo:HelperMethods :: "+e);
    	}
    	
    	return _addressVO;
    }
    
}