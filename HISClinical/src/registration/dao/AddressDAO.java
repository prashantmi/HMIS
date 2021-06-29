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
import hisglobal.exceptions.HisApplicationExecutionException;
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
    public AddressVO createNewAddress(HisDAO daoObj,AddressVO _addressVO, UserVO _userVO)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_patient_address_dtl(?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?,?,?,?::numeric,?,?,?::numeric,?,?,?::numeric,?)}";
			
			HelperMethods.setNullToEmpty(_addressVO);
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _addressVO.getPatCrNo().equals("")?"0":_addressVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no","1",3);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),24);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_add_type_code", _addressVO.getPatAddTypeCode().equals("")?"0":_addressVO.getPatAddTypeCode(),4);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _addressVO.getPatAddCity(),5);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _addressVO.getPatAddDistrict(),6);
	    	daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _addressVO.getPatAddDistrictCode().equals("")?"0":_addressVO.getPatAddDistrictCode(),7);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_state_code", _addressVO.getPatAddStateCode().equals("")?"0":_addressVO.getPatAddStateCode(),8);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _addressVO.getPatAddStateName(),9);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pincode", _addressVO.getPatAddPIN().equals("")?"0":_addressVO.getPatAddPIN(),10);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_country_code", _addressVO.getPatAddCountryCode(),11);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _addressVO.getIsValid(),12);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _userVO.getSeatId(),13);
	    	daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",14);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _addressVO.getIsAddressDelhi(),15);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _addressVO.getPatAddHNo(),16);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_street_no", _addressVO.getPatAddStreet(),17);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _addressVO.getPatAddContactNo(),18);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _addressVO.getPatAddCityLocCode().equals("")?"0":_addressVO.getPatAddCityLocCode(),19);
	    	daoObj.setProcInValue(nProcIndex1, "p_is_current_addresss", _addressVO.getIsCurrentAddress().equals("")?"0":_addressVO.getIsCurrentAddress(),20);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _addressVO.getPatAddCityLoc(),21);
	    	daoObj.setProcInValue(nProcIndex1, "p_gstr_tehsil", _addressVO.getStrPatAddressTehsil(),22);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),23);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_verification_slno", _addressVO.getVerificationDocumentId(),25);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", _addressVO.getPatAddMobileNo(),26);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fax_no", _addressVO.getPatAddFaxNo(),27);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_email_id", _addressVO.getPatAddEmailId(),28);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_change_req_by", _addressVO.getRequestBy().equals("")?"0":_addressVO.getRequestBy(),29);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_name", _addressVO.getRequestByName(),30);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_add", _addressVO.getRequestByAddress(),31);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code", _addressVO.getRequestRelation().equals("")?"0":_addressVO.getRequestRelation(),32);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno", _addressVO.getConstableBadgeNo(),33);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig", _addressVO.getConstableDesig(),34);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _addressVO.getPatIsUrban(),35);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,36);
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{						
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _addressVO;
    }
    
    /**
	 * Populates the map with the address details to be entered in the DB.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_populateMAP	Map containig values which will be used for insert query.
	 */
    	Sequence sq=new Sequence();
    	public void populateMap(AddressVO _addressVO,UserVO _userVO ,Map _populateMAP)throws SQLException{
    	
    	_populateMAP.put(sq.next(), _addressVO.getPatCrNo());//-----HRGNUM_PUK
    	_populateMAP.put(sq.next(), _addressVO.getPatCrNo());
    	_populateMAP.put(sq.next(), _userVO.getHospitalCode());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddTypeCode());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddCity());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddDistrict());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddDistrictCode());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddStateCode());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddStateName());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddPIN());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddCountryCode());
    	_populateMAP.put(sq.next(), _addressVO.getIsValid());
    	_populateMAP.put(sq.next(), _userVO.getSeatId());
    	//_populateMAP.put(sq.next(), _addressVO.getEntryDate());
    	_populateMAP.put(sq.next(), _addressVO.getIsAddressDelhi());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddHNo());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddStreet());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddContactNo());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddCityLocCode());
    	
    	_populateMAP.put(sq.next(), _addressVO.getIsCurrentAddress());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddCityLoc());
    	_populateMAP.put(sq.next(),_userVO.getIpAddress());
    	_populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	_populateMAP.put(sq.next(), _addressVO.getVerificationDocumentId());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddMobileNo());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddFaxNo());
    	_populateMAP.put(sq.next(), _addressVO.getPatAddEmailId());
    	_populateMAP.put(sq.next(), _addressVO.getRequestBy());
    	_populateMAP.put(sq.next(), _addressVO.getRequestByName());
    	_populateMAP.put(sq.next(), _addressVO.getRequestByAddress());
    	_populateMAP.put(sq.next(), _addressVO.getRequestRelation());
    	_populateMAP.put(sq.next(), _addressVO.getConstableBadgeNo());
    	_populateMAP.put(sq.next(), _addressVO.getConstableDesig());
    	_populateMAP.put(sq.next(), _addressVO.getPatIsUrban());
    	
    }
	
    /**
	 * Updates an already stored address in the DB for a patient.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	Number of records updated.
	 */	   
    public int update(AddressVO _addressVO, UserVO _userVO){
    	int x=0;
    	String query  = "";
        Map _populateUpdateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="UPDATE.HRGT_PATIENT_ADD_DTL";                       
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try{
        	populateUpdate(_addressVO,_populateUpdateMAP,_userVO);
        }
        catch(Exception e){
        	throw new HisDataAccessException("AddressDAO:populateMap(_addressVO,populateMAP)::"+e);
        }
        try{
        	x=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,_populateUpdateMAP);
        	
        	if(x==0){
        		throw new HisUpdateUnsuccesfullException();
        	}
        }
        catch(Exception e){
        	if(e.getClass()==HisUpdateUnsuccesfullException.class){
				throw new HisUpdateUnsuccesfullException();	
			}
			else			 		
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
    	return x;
    }
    
    /**
	 * Populates the map with the address details to be updated in the DB.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_populateUpdateMAP	Map containig values which will be used for update query.
	 */
    public void populateUpdate(AddressVO _addressVO, Map _populateUpdateMAP,UserVO _userVO)throws SQLException{
    	    	
    	Sequence sq=new Sequence();
    		    	
    	
    	//_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddTypeCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCity());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddDistrict());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddDistrictCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddStateCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddStateName());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddPIN());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCountryCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getIsValid());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getIsAddressDelhi());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddHNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddStreet());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddContactNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCityLocCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getIsCurrentAddress());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCityLoc());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getVerificationDocumentId());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddMobileNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddFaxNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddEmailId());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddPhoneNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatCrNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddTypeCode());
    	_populateUpdateMAP.put(sq.next(), _userVO.getHospitalCode());
    	
    	    	
    }
    
    /**
     * Retrieves the address details of a patient on the basis of CR No for a particular address type.
     * Also checks that the address should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient whose address is to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	AddressVO containing address details for the given CR No.
     */
    public AddressVO retrieveByCrNo(PatientVO _patientVO, UserVO _userVO){

    	AddressVO _addressVO=new AddressVO();
    	   	
    	WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_ADD_DTL_WITH_CRNO(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			System.out.println("patTyprCode"+_patientVO.getPatAddTypeCode());
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		//daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _patientVO.getPatCrNo().substring(0,3),1);
		daoObj.setProcInValue(nProcIndex, "p_crno", _patientVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,3);
		daoObj.setProcInValue(nProcIndex, "p_pattypecode",_patientVO.getPatAddTypeCode(),4);
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
			/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			System.out.println("queryDate " + queryDate);
*/
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
		}

		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		try{
    				
    		if (!rs.next()){
    			System.out.println("no records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			
    			rs.beforeFirst();
    			HelperMethods.populateVOfrmRS(_addressVO, rs);
    			
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
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
    	return _addressVO;
    } 
    
    /**
     * Retrieves all the addresses of a patient on the basis of CR No.
     * Also checks that the address should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient whose address is to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of AddressVO containing all the addresses of a patient.
	 * Modified By Pragya at 05-Aug-2011
     */
    public AddressVO[] retrieveByCrNoAll(String crNo, UserVO _userVO)
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_ADD_DTL(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			//daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", crNo.substring(0,3),2);
			daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_USER_HOSPITAL_CODE,3);
			daoObj.setProcInValue(nProcIndex, "p_crno", crNo,4);
			daoObj.setProcInValue(nProcIndex, "p_add_type_code", "",5);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e
					+ strErr);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		AddressVO[] _addressVO;
		ValueObject[] vo = {};
		try
		{
			if (!rs.next())
			{
				System.out.println("no records");
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(AddressVO.class, rs);
				_addressVO = new AddressVO[vo.length];
				for (int i = 0; i < vo.length; i++)
					_addressVO[i] = (AddressVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("AddressDAO:retrieveByCrNoAll:HelperMethods :: " + e);
		}
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return _addressVO;
	}
    
    
    /**
	 * Retrieves the address details of a patient from another DB system on the basis of Previous CR No for a particular
	 * address type. Also checks that the address should be valid and active.
	 * 
	 * @param _patientVO Provides CR No of the patient whose address is to be searched.
	 * @param _userVO Provides User details.
	 * @return AddressVO containing address details for the given CR No.
	 */
    public AddressVO previousSystemCrNo(PatientVO _patientVO, UserVO _userVO){
    	AddressVO _addressVO=new AddressVO();
    	Map _populateMapPatientAddDtl =new HashMap();
    	
    	String query="";
    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.PREVIOUS_SYSTEM.HRGT_PREV_PATIENT_ADD_DTL";
		Sequence sq=new Sequence();
		_populateMapPatientAddDtl.put(sq.next(),_patientVO.getPrevCrNo());
		//whether the patient add retrieved is current or not
		//check add type code for prev system
		_populateMapPatientAddDtl.put(sq.next(),RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
		_populateMapPatientAddDtl.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMapPatientAddDtl.put(sq.next(),_userVO.getHospitalCode());
		Connection conn =super.getTransactionContext().getConnection();    	
    	try{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    		
    	}
    	catch(Exception e){
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    	
    	try{
    		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,_populateMapPatientAddDtl);
    		
    		
    		if (!rs.next()){
    			System.out.println("no records");
    			throw new HisRecordNotFoundException();       			
    		}
    		else
    		{
    			
    			rs.beforeFirst();
    			HelperMethods.populateVOfrmRS(_addressVO, rs);
    			
    			//System.out.println("_addressVO.getPatMiddleName()"+_patientVO.getPatMiddleName());
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException("No Record Found");	
			}
			else			 		
    		  throw new HisDataAccessException("AddressDAO:previousSystemCrNo:HelperMethods :: "+e);
    	}
    	return _addressVO;
    }
    
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
    
	
    /**
	 * Updates an already stored address along with Address Type in the DB for a patient.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_userVO		Provides User details.
	 * @return	Number of records updated.
	 */	   
    public int updateWithAddressType(AddressVO _addressVO, UserVO _userVO){
    	int x=0;
    	String query  = "";
        Map _populateUpdateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="UPDATE.WITH_ADDRESS_TYPE.HRGT_PATIENT_ADD_DTL";                       
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try{
        	populateUpdateWithAddressType(_addressVO,_populateUpdateMAP,_userVO);
        }
        catch(Exception e){
        	throw new HisDataAccessException("AddressDAO:populateMap(_addressVO,populateMAP)::"+e);
        }
        try{
        	x=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,_populateUpdateMAP);
        	
        	if(x==0){
        		throw new HisUpdateUnsuccesfullException();
        	}
        }
        catch(Exception e){
        	if(e.getClass()==HisUpdateUnsuccesfullException.class){
				throw new HisUpdateUnsuccesfullException();	
			}
			else			 		
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
    	return x;
    }
    
    
    /**
	 * Populates the map with the address details to be updated in the DB.
	 * @param	_addressVO	Provides address details to be entered.
	 * @param	_populateUpdateMAP	Map containig values which will be used for update query.
	 */
    public void populateUpdateWithAddressType(AddressVO _addressVO, Map _populateUpdateMAP,UserVO _userVO)throws SQLException{
    	    	
    	Sequence sq=new Sequence();
    		    	
    	
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddTypeCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCity());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddDistrict());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddStateCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddPIN());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCountryCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getIsValid());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getIsAddressDelhi());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddHNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddStreet());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddContactNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCityLocCode());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getIsCurrentAddress());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddCityLoc());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getVerificationDocumentId());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddMobileNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddFaxNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddEmailId());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatAddPhoneNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getPatCrNo());
    	_populateUpdateMAP.put(sq.next(), _addressVO.getSerialNo());
    	_populateUpdateMAP.put(sq.next(),_userVO.getHospitalCode());
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
    
    public String updateAddressDetailByProcedure(AddressVO _addressVO, UserVO _userVO){
        Sequence sq=new Sequence();
        String err ="";
        try{
        	Connection conn = super.getTransactionContext().getConnection();
        	Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_UPDATE_PATIENT_ADDRESS_DTL);
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getPatCrNo());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getSerialNo());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getPatAddTypeCode());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddCity());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddDistrict());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getPatAddStateCode());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getPatAddPIN());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getPatAddCountryCode());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getIsAddressDelhi());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddHNo());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddStreet());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddContactNo());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getPatAddCityLocCode());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _addressVO.getIsCurrentAddress());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddCityLoc());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddPhoneNo());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddMobileNo());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddFaxNo());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddEmailId());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getPatAddStateName());
			strProc.addInParameter(sq.next(), Types.NUMERIC, _userVO.getHospitalCode());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _userVO.getIpAddress());
			strProc.addInParameter(sq.next(), Types.VARCHAR, _addressVO.getVerificationDocumentId());
			strProc.addInParameter(sq.next(), Types.NUMERIC, Config.IS_VALID_ACTIVE);
			strProc.addInParameter(sq.next(), Types.NUMERIC, _userVO.getSeatId());
			strProc.addOutParameter(sq.next(), Types.VARCHAR);
			
			strProc.execute(conn);
			 err= (String) strProc.getParameterAt(sq.next()-1);
			 System.out.println("");
        }
        catch(Exception e){
        	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
       
  return err;
    }
    
    public int updatePreviousRow(HisDAO daoObj, AddressVO _addressVO, UserVO _userVO)
	{
		int x = 1;
		//String queryKey = "UPDATE.PREVIOUS_ROW.HRGT_PATIENT_ADD_DTL"; registrationQuery
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_patient_address_dtl(?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?,?,?,?::numeric,?,?,?::numeric,?,?,?::numeric,?)}";
			
			HelperMethods.setNullToEmpty(_addressVO);
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);		
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "3",1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_addressVO.getPatCrNo()==""?"0": _addressVO.getPatCrNo() ,2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no",_addressVO.getSerialNo()==""?"0": _addressVO.getSerialNo(),3);

	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_add_type_code", _addressVO.getPatAddTypeCode(),4);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _addressVO.getPatAddCity(),5);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district",_addressVO.getPatAddDistrict()==""?"0": _addressVO.getPatAddDistrict(),6);
	    	daoObj.setProcInValue(nProcIndex1, "p_num_dist_id",_addressVO.getPatAddDistrictCode()==""?"0":  _addressVO.getPatAddDistrictCode(),7);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_state_code", _addressVO.getPatAddStateCode(),8);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _addressVO.getPatAddStateName()==""?"0":_addressVO.getPatAddStateName(),9);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pincode",_addressVO.getPatAddPIN()==null|| _addressVO.getPatAddPIN()==""?"0": _addressVO.getPatAddPIN(),10);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_country_code",_addressVO.getPatAddCountryCode()==""?"0":_addressVO.getPatAddCountryCode(),11);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", Config.IS_VALID_INACTIVE,12);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _userVO.getSeatId(),13);
	    	daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","0",14);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _addressVO.getIsAddressDelhi(),15);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _addressVO.getPatAddHNo(),16);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_street_no", _addressVO.getPatAddStreet(),17);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no",_addressVO.getPatAddContactNo()==""?"0":_addressVO.getPatAddContactNo(),18);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code",  _addressVO.getPatAddCityLocCode()==""?"0": _addressVO.getPatAddCityLocCode(),19);
	    	daoObj.setProcInValue(nProcIndex1, "p_is_current_addresss", _addressVO.getIsCurrentAddress(),20);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _addressVO.getPatAddCityLoc(),21);
	    	daoObj.setProcInValue(nProcIndex1, "p_gstr_tehsil", _addressVO.getStrPatAddressTehsil(),22);
	    	
	    
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),23);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode(),24);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_verification_slno", _addressVO.getVerificationDocumentId(),25);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", _addressVO.getPatAddMobileNo(),26);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fax_no", _addressVO.getPatAddFaxNo(),27);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_email_id", _addressVO.getPatAddEmailId(),28);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_change_req_by",_addressVO.getRequestBy()==""?"0":_addressVO.getRequestBy(),29);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_name", _addressVO.getRequestByName(),30);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_add", _addressVO.getRequestByAddress(),31);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code",_addressVO.getRequestRelation()==""?"0":_addressVO.getRequestRelation(),32);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno", _addressVO.getConstableBadgeNo(),33);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig", _addressVO.getConstableDesig(),34);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _addressVO.getPatIsUrban()==""?"0":_addressVO.getPatIsUrban(),35);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,36);
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{						
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return x;
	}
    
    public AddressVO insertNewRowAddress(HisDAO daoObj, AddressVO _addressVO, UserVO _userVO)
    {
        //String queryKey="INSERT_NEW_ROW.HRGT_PATIENT_ADD_DTL";                       
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_patient_address_dtl(?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?::numeric,?::numeric,?,?,?,?::numeric,?,?,?,?,?::numeric,?,?,?::numeric,?,?,?::numeric,?)}";
			System.out.println("the pin code is "+ _addressVO.getPatAddPIN());
			HelperMethods.setNullToEmpty(_addressVO);
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);
			/*daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk",_addressVO.getPatCrNo()==""?"0": _addressVO.getPatCrNo() ,2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no","1",3);

	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_add_type_code", _addressVO.getPatAddTypeCode(),4);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _addressVO.getPatAddCity(),5);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district",_addressVO.getPatAddDistrict()==""?"0": _addressVO.getPatAddDistrict(),6);
	    	daoObj.setProcInValue(nProcIndex1, "p_num_dist_id",_addressVO.getPatAddDistrictCode()==""?"0":  _addressVO.getPatAddDistrictCode(),7);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_state_code", _addressVO.getPatAddStateCode(),8);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name",_addressVO.getPatAddStateName()==""?"0":_addressVO.getPatAddStateName(),9);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pincode",_addressVO.getPatAddPIN()==null|| _addressVO.getPatAddPIN()==""?"0": _addressVO.getPatAddPIN(),10);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_country_code",_addressVO.getPatAddCountryCode()==""?"0":_addressVO.getPatAddCountryCode(),11);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid",_addressVO.getIsValid()==""?"0":_addressVO.getIsValid(),12);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _userVO.getSeatId(),13);
	    	daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","0",14);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _addressVO.getIsAddressDelhi(),15);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _addressVO.getPatAddHNo(),16);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_street_no", _addressVO.getPatAddStreet(),17);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no",_addressVO.getPatAddContactNo()==""?"0":_addressVO.getPatAddContactNo(),18);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _addressVO.getPatAddCityLocCode()==""?"0": _addressVO.getPatAddCityLocCode(),19);
	    	daoObj.setProcInValue(nProcIndex1, "p_is_current_addresss", _addressVO.getIsCurrentAddress(),20);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _addressVO.getPatAddCityLoc(),21);
	    	daoObj.setProcInValue(nProcIndex1, "p_gstr_tehsil", _addressVO.getStrPatAddressTehsil(),22);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress()==""?"0": _userVO.getIpAddress(),23);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code",_userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode() ,24);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_verification_slno", _addressVO.getVerificationDocumentId(),25);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", _addressVO.getPatAddMobileNo(),26);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fax_no", _addressVO.getPatAddFaxNo(),27);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_email_id", _addressVO.getPatAddEmailId(),28);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_change_req_by",_addressVO.getRequestBy()==""?"0":_addressVO.getRequestBy(),29);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_name", _addressVO.getRequestByName(),30);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_add", _addressVO.getRequestByAddress(),31);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code",_addressVO.getRequestRelation()==""?"0":_addressVO.getRequestRelation(),32);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno", _addressVO.getConstableBadgeNo(),33);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig", _addressVO.getConstableDesig(),34);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban",_addressVO.getPatIsUrban()==""?"0":_addressVO.getPatIsUrban(),35);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,36);*/
			
			
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _addressVO.getPatCrNo().equals("")?"0":_addressVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_s_no","1",3);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),24);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_add_type_code", _addressVO.getPatAddTypeCode().equals("")?"0":_addressVO.getPatAddTypeCode(),4);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _addressVO.getPatAddCity(),5);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _addressVO.getPatAddDistrict(),6);
	    	daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _addressVO.getPatAddDistrictCode().equals("")?"0":_addressVO.getPatAddDistrictCode(),7);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_state_code", _addressVO.getPatAddStateCode().equals("")?"0":_addressVO.getPatAddStateCode(),8);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _addressVO.getPatAddStateName(),9);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pincode", _addressVO.getPatAddPIN().equals("")?"0":_addressVO.getPatAddPIN(),10);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_country_code", _addressVO.getPatAddCountryCode(),11);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _addressVO.getIsValid(),12);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _userVO.getSeatId(),13);
	    	daoObj.setProcInValue(nProcIndex1, "p_gdt_entry_date","",14);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _addressVO.getIsAddressDelhi(),15);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _addressVO.getPatAddHNo(),16);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_street_no", _addressVO.getPatAddStreet(),17);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _addressVO.getPatAddContactNo(),18);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _addressVO.getPatAddCityLocCode().equals("")?"0":_addressVO.getPatAddCityLocCode(),19);
	    	daoObj.setProcInValue(nProcIndex1, "p_is_current_addresss", _addressVO.getIsCurrentAddress().equals("")?"0":_addressVO.getIsCurrentAddress(),20);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _addressVO.getPatAddCityLoc(),21);
	    	daoObj.setProcInValue(nProcIndex1, "p_gstr_tehsil", _addressVO.getStrPatAddressTehsil(),22);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",_userVO.getIpAddress(),23);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_verification_slno", _addressVO.getVerificationDocumentId(),25);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mobile_no", _addressVO.getPatAddMobileNo(),26);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fax_no", _addressVO.getPatAddFaxNo(),27);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_email_id", _addressVO.getPatAddEmailId(),28);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_change_req_by", _addressVO.getRequestBy().equals("")?"0":_addressVO.getRequestBy(),29);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_name", _addressVO.getRequestByName(),30);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_change_reqby_add", _addressVO.getRequestByAddress(),31);
	    	daoObj.setProcInValue(nProcIndex1, "p_gnum_relation_code", _addressVO.getRequestRelation().equals("")?"0":_addressVO.getRequestRelation(),32);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_badgeno", _addressVO.getConstableBadgeNo(),33);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgstr_constable_desig", _addressVO.getConstableDesig(),34);
	    	daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _addressVO.getPatIsUrban(),35);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,36);
			
			
			daoObj.execute(nProcIndex1,1);			
			//daoObj.executeProcedureByPosition(nProcIndex1);
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{						
					}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _addressVO;
   }
}