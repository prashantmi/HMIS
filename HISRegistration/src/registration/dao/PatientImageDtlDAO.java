package registration.dao;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import registration.config.RegistrationConfig;
import vo.registration.AddressVO;
import vo.registration.PatientImageDtlVO;
import vo.registration.PatientVO;


public class PatientImageDtlDAO extends DataAccessObject {
	public PatientImageDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	
	public PatientImageDtlVO create(HisDAO daoObj,PatientImageDtlVO _patientImageDtlVO, UserVO _userVO){
		
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{			
			strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_image_dtl(?,?,?,?,?,	?,?,?,?,?,	?,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientImageDtlVO);
			
			daoObj.setProcInValue(nProcIndex2, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_puk",_patientImageDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_episodecode",_patientImageDtlVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_isdefault",_patientImageDtlVO.getIsImageDefault(),4);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_fileno",_patientImageDtlVO.getFileNo(),5);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_filename",_patientImageDtlVO.getFilePath(),6);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_ipaddress",(_userVO.getIpAddress()==null || _userVO.getIpAddress().equals("")?"127.0.0.1":_userVO.getIpAddress()),7);

			daoObj.setProcInValue(nProcIndex2, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,8);			
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seatid",_userVO.getSeatId(),9);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hoscode",_userVO.getHospitalCode(),10);			
			daoObj.setProcInValue(nProcIndex2, "p_gnum_s_no",_patientImageDtlVO.getSerialNo(),11);
			
			daoObj.setProcOutValue(nProcIndex2, "err", 1,12);

			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
        return _patientImageDtlVO;
	}
	
	public PatientImageDtlVO getPatientDefaultImageByCrNo(String patCrNo, UserVO _userVO){
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_IMAGE_DETAIL_BY_CRNO(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr="";
	    PatientImageDtlVO patientImageDtlVO=new PatientImageDtlVO();
		try 
		{			
			daoObj = new HisDAO("Registration","PatientImageDtlDAO");
			nProcIndex = daoObj.setProcedure(strProcName);	
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno", patCrNo,2);
			daoObj.setProcInValue(nProcIndex, "p_isdefault", RegistrationConfig.IS_IMAGE_DEFAULT_TRUE,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			strErr = daoObj.getString(nProcIndex, "err");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}


		try {
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Patient Image Details Not Found");
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(patientImageDtlVO, rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:getPatientDefaultImageByCrNo:HelperMethods :: " + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return patientImageDtlVO;

	}
public PatientImageDtlVO uploadVerificationDoc(HisDAO daoObj,PatientImageDtlVO _patientImageDtlVO, UserVO _userVO,String docName,String docId){
		
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{			
			strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_document_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientImageDtlVO);
			
			daoObj.setProcInValue(nProcIndex2, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_puk",_patientImageDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_episodecode",_patientImageDtlVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_documentid",docId,4);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_doc_title",docName,5);
			daoObj.setProcInValue(nProcIndex2, "p_gstr_filename",_patientImageDtlVO.getFileNo(),6);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_filetype",_patientImageDtlVO.getFileType(),7);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_ipaddress",(_userVO.getIpAddress()==null || _userVO.getIpAddress().equals("")?"127.0.0.1":_userVO.getIpAddress()),8);

			daoObj.setProcInValue(nProcIndex2, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,9);			
			daoObj.setProcInValue(nProcIndex2, "p_gnum_seatid",_userVO.getSeatId(),10);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_hoscode",_userVO.getHospitalCode(),11);	
			
			System.out.println("p_hrgnum_puk :"+ _patientImageDtlVO.getPatCrNo());
			System.out.println("p_hrgnum_episode_code :"+ _patientImageDtlVO.getEpisodeCode());
			System.out.println("p_gstr_documentid :"+ docId);
			System.out.println("p_gstr_doc_title :"+docName);
			System.out.println("p_gstr_filename :"+ _patientImageDtlVO.getFileNo());
			System.out.println("p_gnum_filetype :"+ _patientImageDtlVO.getFileType());
			System.out.println("p_gnum_ipaddress :"+ (_userVO.getIpAddress()==null || _userVO.getIpAddress().equals("")?"127.0.0.1":_userVO.getIpAddress()));
			System.out.println("p_gnum_isvalid :"+ Config.IS_VALID_ACTIVE);
			System.out.println("p_gnum_seatid :"+ _userVO.getSeatId());
			
			daoObj.setProcOutValue(nProcIndex2, "err", 1,12);

			
			//daoObj.executeProcedureByPosition(nProcIndex2);
			daoObj.execute(nProcIndex2,1);	
			//strErr = daoObj.getString(nProcIndex2, "err");
			if (strErr == null)
				strErr = "";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
        return _patientImageDtlVO;
	}



/*Added By Manisha Gangwar Date: 23.3.2018 For Saving Image to Postgres*/


public void saveImageToPostgres(HisDAO daoObj,PatientImageDtlVO _patientImageDtlVO, byte[] imagebyte, UserVO _userVO) throws Exception
{
	
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	try
	{
		if(imagebyte!=null)
		{
		 String query = "update hrgt_patient_image_dtl SET gbyte_image_data=? where HRGNUM_PUK=? and HRGNUM_S_NO = (SELECT NVL (MAX (hrgnum_s_no),1) FROM hrgt_patient_image_dtl where hrgnum_puk = ?)";
	   	 ps = conn.prepareStatement(query);
	   	 InputStream iss = new ByteArrayInputStream(imagebyte);	
	   	 ps.setBinaryStream(1,iss,imagebyte.length);
		// ps.setBytes(1,decodedData);
	   	 ps.setString(2,_patientImageDtlVO.getPatCrNo());
	   	 ps.setString(3,_patientImageDtlVO.getPatCrNo());
	   	 ps.executeUpdate();
		}
	}
	catch(Exception e)
	   	{
	   		System.out.println("exception in Image saving transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
		
}

/*Added By Manisha Gangwar Date: 23.3.2018 For Fetching Image to Postgres*/


public byte[] latestFetchImagePostgres(String fileNo) throws Exception
{
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		String query = "select gbyte_image_data from hrgt_patient_image_dtl where gst_file_name = ? and gnum_isvalid=1";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 ps.setString(1,fileNo);
		 ResultSet resultSet = ps.executeQuery();
		 
		 if(resultSet!=null && resultSet.next())
			{
			 //Base64 codec = new Base64();
			 //imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
			  imgBytes = resultSet.getBytes(1);
			  if(imgBytes!=null)
			  fis = new ByteArrayInputStream(imgBytes); 
				
			  
			}
	}
	catch(Exception e)
	   	{
	   		System.out.println("exception in Image saving transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
	return imgBytes;
}


// added for saving documents to postgres
public void saveDocToPostgres(HisDAO daoObj,PatientImageDtlVO _patientImageDtlVO, byte[] imagebyte) throws Exception
{
	
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	try
	{
		if(imagebyte!=null)
		{
		 String query = "update hrgt_patient_document_dtl SET gbyte_doc_data=? where HRGNUM_PUK=? and HRGNUM_S_NO = (SELECT NVL (MAX (hrgnum_s_no),1) FROM hrgt_patient_image_dtl where hrgnum_puk = ?) and gnum_isvalid=1";
	   	 ps = conn.prepareStatement(query);
	   	 InputStream iss = new ByteArrayInputStream(imagebyte);	
	   	 ps.setBinaryStream(1,iss,imagebyte.length);
		// ps.setBytes(1,decodedData);
	   	 ps.setString(2,_patientImageDtlVO.getPatCrNo());
	   	 ps.setString(3,_patientImageDtlVO.getPatCrNo());
	   	 ps.executeUpdate();
		}
	}
	catch(Exception e)
	   	{
	   		System.out.println("exception in Image saving transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
		
}




public PatientImageDtlVO getPatientDocByCrNo(String patCrNo, UserVO _userVO){
	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_DOCUMENT_DETAIL_BY_CRNO(?,?,?,?,?,?,?)}";
	int nProcIndex = 0;
	String strErr="";
    PatientImageDtlVO patientImageDtlVO=new PatientImageDtlVO();
	try 
	{			
		daoObj = new HisDAO("Registration","PatientImageDtlDAO");
		nProcIndex = daoObj.setProcedure(strProcName);	
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_crno", patCrNo,2);
		daoObj.setProcInValue(nProcIndex, "p_isdefault", RegistrationConfig.IS_IMAGE_DEFAULT_TRUE,3);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),4);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",Config.IS_VALID_ACTIVE,5);
		daoObj.setProcOutValue(nProcIndex, "err", 1,6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
		daoObj.executeProcedureByPosition(nProcIndex);

		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}


	try {
		if (!rs.next()) {
			throw new HisRecordNotFoundException("Patient Image Details Not Found");
		} else {
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(patientImageDtlVO, rs);
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("PatientDAO:getPatientDefaultImageByCrNo:HelperMethods :: " + e);
	}
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return patientImageDtlVO;

}




/*Added By Manisha Gangwar Date: 23.3.2018 For Fetching Image from Postgres*/


public PatientVO fetchVerificationDoc(PatientVO objPatientVO_p, UserVO _userVO)
{	
	WebRowSet rs = null;
	HisDAO daoObj = null;
	String strProcName2 = "{call pkg_reg_view.proc_hrgt_patient_document_dtl(?,?,?,?)}";
	int nProcIndex2 = 0;
	String strErr="";
	
	try 
	{
		daoObj = new HisDAO("Registration","PatientImageDtlDAO");
	    nProcIndex2 = daoObj.setProcedure(strProcName2);
		daoObj.setProcInValue(nProcIndex2, "p_mode","1",1);   
		daoObj.setProcInValue(nProcIndex2, "p_gnum_puk",objPatientVO_p.getPatCrNo(),2);
		daoObj.setProcOutValue(nProcIndex2, "err",1,3);
		daoObj.setProcOutValue(nProcIndex2, "resultset",2,4);
		//daoObj.execute(nProcIndex2,1);	
		daoObj.executeProcedureByPosition(nProcIndex2);
		strErr = daoObj.getString(nProcIndex2, "err");
		rs = daoObj.getWebRowSet(nProcIndex2, "resultset");
			
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}


	try {
		if (!rs.next()) {
			throw new HisRecordNotFoundException("Patient Image Details Not Found");
		} else {
			rs.beforeFirst();
			objPatientVO_p.setVerificationDocumentId(rs.getString("1"));
			//HelperMethods.populateVOfrmRS(objPatientVO_p, rs);
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("PatientDAO:getPatientDefaultImageByCrNo:HelperMethods :: " + e);
	}
	finally 
	{
		if (daoObj != null) 
		{
			daoObj.free();
			daoObj = null;
		}
	}
	return objPatientVO_p;
}




/*Added By Manisha Gangwar Date: 23.3.2018 For Fetching Image to Postgres*/

public byte[] latestFetchDocPostgres(String fileNo) throws Exception
{
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		String query = "select gbyte_doc_data from hrgt_patient_document_dtl where gst_file_name = ? and gnum_isvalid=1";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 ps.setString(1,fileNo);
		 ResultSet resultSet = ps.executeQuery();
		 
		 if(resultSet!=null && resultSet.next())
			{
			 //Base64 codec = new Base64();
			 //imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
			  imgBytes = resultSet.getBytes(1);
			  if(imgBytes!=null)
			  fis = new ByteArrayInputStream(imgBytes); 
				
			  
			}
	}
	catch(Exception e)
	   	{
	   		System.out.println("exception in Image saving transaction..."+e); 
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { System.out.println("error in try block..."+se); }
	   	}
	return imgBytes;
}



	


}
