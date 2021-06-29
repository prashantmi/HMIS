package registration.dao;

import java.sql.SQLException;
import java.util.Map;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.*;
import hisglobal.presentation.Status;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
/**
 * AddressDAOi is an interface that declares CRUD methods associated with HRGT_PATIENT_ADD_DTL table.
 * @author AHIS
 */
public interface AddressDAOi
{
	/**
	 * Creates a new address entry in DB for a patient.
	 */
    //public AddressVO createNewAddress(HisDAO daoObj,AddressVO _addressVO, UserVO _userVO);
    
    /**
	 * Updates an already stored address in the DB for a patient.
	 */	   
    public int update(AddressVO _addressVO, UserVO _userVO);

    /**
     * Retrieves the address details of a patient on the basis of CR No for a particular address type.
     * Also checks that the address should be valid and active.
     */
    public AddressVO retrieveByCrNo(PatientVO _patientVO, UserVO _userVO);

    /**
     * Retrieves all the addresses of a patient on the basis of CR No.
     * Also checks that the address should be valid and active.
     */
    public AddressVO[] retrieveByCrNoAll(String crNo, UserVO _userVO);
    
    /**
     * Retrieves the address details of a patient from another DB system on the basis of  Previous CR No for a particular address type.
     * Also checks that the address should be valid and active.
     */
    public AddressVO previousSystemCrNo(PatientVO _patientVO, UserVO _userVO);  
    
    public AddressVO[] retrieveByContactNo(String contactNo, UserVO _userVO);
    
    public AddressVO[] retrieveByName(String _patientName, UserVO _userVO);
    
    public int updateWithAddressType(AddressVO _addressVO, UserVO _userVO);
    
   // public void populateUpdateWithAddressType(AddressVO _addressVO, Map _populateUpdateMAP)throws SQLException;
    
    public AddressVO[] retrieveByNationalID(String nationalID, UserVO _userVO);
    
    
           
}
