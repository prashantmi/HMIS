package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Sequence;
import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.AddressVO;
import vo.registration.PatientVO;
/**
 * AddressDAO is a class which describes all the methods required for database interaction
 * for HRGT_PATIENT_ADD_DTL table, for example, insert, update, select and delete.
 * @author AHIS
 *
 */
public class AddressDAO extends RegistrationDAO
{
	public AddressDAO(TransactionContext _transactionContext){
		super(_transactionContext);
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
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_ADD_DTL(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_mode", "2",1);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,3);
		daoObj.setProcInValue(nProcIndex, "p_crno", _patientVO.getPatCrNo(),4);
		daoObj.setProcInValue(nProcIndex, "p_add_type_code", _patientVO.getPatAddTypeCode(),5);
		
		daoObj.setProcOutValue(nProcIndex, "err", 1,6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
			/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			System.out.println("queryDate " + queryDate);
*/
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_super_hosp_code", Config.SUPER_HOSPITAL_CODE,3);
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
	 * Save/Update an address entry in DB for a patient.
	 * @param	objAddressVO_p	Provides address details to be entered.
	 * @param	objUserVO_p		Provides User details.
	 * @return	AddressVO with values stored in DB.
	 * if strMode_p = 1 -->> Insert
	 * if strMode_p = 2 -->> Update
	 */
    public AddressVO saveAddressDtl(HisDAO objHisDAO_p,AddressVO objAddressVO_p, UserVO objUserVO_p,String strMode_p)
    {
    	String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		
		try 
		{
			strProcName1 = "{call pkg_reg_dml.dml_hrgt_patient_add_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";//29 args on 20.06.18
			
			HelperMethods.setNullToEmpty(objAddressVO_p);
			
			nProcIndex1 = objHisDAO_p.setProcedure(strProcName1);
			System.out.println("AddressDAO :: saveAddressDtl()");
			//////////////////////
			/*System.out.println("p_modeVal :"+ strMode_p);
			System.out.println("p_hrgnum_puk :"+ (objAddressVO_p.getPatCrNo().equals("")?"0":objAddressVO_p.getPatCrNo()));
	    	System.out.println("p_gnum_hospital_code :"+ objUserVO_p.getHospitalCode());
	    	System.out.println("p_gnum_add_type_code :"+ (objAddressVO_p.getPatAddTypeCode().equals("")?"0":objAddressVO_p.getPatAddTypeCode()));
	    	System.out.println("p_hrgstr_city :"+ objAddressVO_p.getPatAddCity());
	    	System.out.println("p_hrgstr_district :"+ objAddressVO_p.getPatAddDistrict());
	    	System.out.println("gnum_district_code :"+ (objAddressVO_p.getPatAddDistrictCode().equals("")?"0":objAddressVO_p.getPatAddDistrictCode()));
	    	System.out.println("gnum_subdistrict_code :"+"");
	    	System.out.println("p_gnum_state_code :"+ (objAddressVO_p.getPatAddStateCode().equals("")?"0":objAddressVO_p.getPatAddStateCode()));
	    	System.out.println("p_hrgstr_state_name :"+ objAddressVO_p.getPatAddState());
	    	System.out.println("p_hrgnum_pincode :"+ (objAddressVO_p.getPatAddPIN().equals("")?"0":objAddressVO_p.getPatAddPIN()));
	    	System.out.println("p_gnum_country_code :"+ objAddressVO_p.getPatAddCountryCode());
	    	System.out.println("p_gnum_isvalid :"+ objAddressVO_p.getIsValid());
	    	System.out.println("p_gnum_seat_id :"+ objUserVO_p.getSeatId());
	    	System.out.println("p_gdt_entry_date :"+"");
	    	System.out.println("p_hrgnum_is_local :"+ objAddressVO_p.getIsAddressDelhi());
	    	System.out.println("hrgstr_address_line1 :"+ objAddressVO_p.getPatAddHNo());
	    	System.out.println("hrgstr_sub_locality1 :"+ objAddressVO_p.getPatAddStreet());
	    	System.out.println("hrgstr_sub_locality2 :"+ objAddressVO_p.getPatAddLandMarks());
	    	System.out.println("hrgnum_phone_owner :"+ objAddressVO_p.getPatAddPhoneOwner());
	    	System.out.println("p_hrgstr_phone_no :"+ objAddressVO_p.getPatAddContactNo());
	    	System.out.println("hrgstr_mobile_no :"+ objAddressVO_p.getPatAddMobileNo());
	    	System.out.println("p_hrgstr_city_location :"+ objAddressVO_p.getPatAddCityLoc());
	    	System.out.println("p_hrgstr_ip_add :"+objUserVO_p.getIpAddress());
	    	System.out.println("p_hrgstr_email_id :"+ objAddressVO_p.getPatAddEmailId());
	    	System.out.println("p_hrgnum_is_urban :"+ objAddressVO_p.getPatIsUrban());
	    	System.out.println("hrgnum_verification_status :"+"2");*/
			//////////////////////
			
			objHisDAO_p.setProcInValue(nProcIndex1, "p_modeVal", strMode_p,1);
			objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_puk", objAddressVO_p.getPatCrNo(),2);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_hospital_code", objUserVO_p.getHospitalCode(),3);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_add_type_code", objAddressVO_p.getPatAddTypeCode().equals("")?"0":objAddressVO_p.getPatAddTypeCode(),4);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city", objAddressVO_p.getPatAddCity(),5);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_district", objAddressVO_p.getPatAddDistrict(),6);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "gnum_district_code",objAddressVO_p.getPatAddDistrictCode(),7);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "gnum_subdistrict_code","",8);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_state_code", objAddressVO_p.getPatAddStateCode(),9);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_state_name", objAddressVO_p.getPatAddState(),10);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_pincode", objAddressVO_p.getPatAddPIN(),11);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_country_code", objAddressVO_p.getPatAddCountryCode(),12);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_isvalid", objAddressVO_p.getIsValid(),13);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gnum_seat_id", objUserVO_p.getSeatId(),14);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_gdt_entry_date","",15);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_local", objAddressVO_p.getPatIsLocal(),16);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "hrgstr_address_line1", objAddressVO_p.getPatAddHNo(),17);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "hrgstr_sub_locality1", objAddressVO_p.getPatAddStreet(),18);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "hrgstr_sub_locality2", objAddressVO_p.getPatAddLandMarks(),19);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "hrgnum_phone_owner", objAddressVO_p.getPatAddPhoneOwner(),20);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_phone_no", objAddressVO_p.getPatAddPhoneNo(),21);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "hrgstr_mobile_no", objAddressVO_p.getPatAddMobileNo(),22);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_city_location", objAddressVO_p.getPatAddCityLoc(),23);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_ip_add",(objUserVO_p.getIpAddress()==null?"127.0.0.1":objUserVO_p.getIpAddress()),24);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_email_id", objAddressVO_p.getPatAddEmailId(),25);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", objAddressVO_p.getPatIsUrban(),26);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "hrgnum_verification_status",objAddressVO_p.getPatVerificationStatus(),27);
	    	objHisDAO_p.setProcInValue(nProcIndex1, "p_hrgstr_emg_cntc", objAddressVO_p.getPatEmgCntNo(), 28);
	    	objHisDAO_p.setProcOutValue(nProcIndex1, "err", 1,29);
	    	
	    	
			objHisDAO_p.execute(nProcIndex1,1);			
			//objHisDAO_p.executeProcedureByPosition(nProcIndex1);			
			
			//strErr = objHisDAO_p.getString(nProcIndex1, "err");
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
		return objAddressVO_p;
    }
    
}