package registration.dao;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import javax.sql.rowset.WebRowSet;



















import org.apache.commons.codec.binary.Base64;












import emr.vo.EHR_PatientProfileDtlVO;
import registration.RegistrationConfig;


public class PatientImageDtlDAO extends DataAccessObject {
	public PatientImageDtlDAO(TransactionContext _transactionContext){
		super(_transactionContext);
	}
	/*
	 * Modified By aKASH sINGH at 05-Oct-2015
	 */
public PatientImageDtlVO create(HisDAO daoObj,PatientImageDtlVO _patientImageDtlVO, UserVO _userVO){
		
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName2="";
		
		try 
		{			
			strProcName2 = "{call pkg_reg_dml.dml_hrgt_patient_image_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";

			nProcIndex2 = daoObj.setProcedure(strProcName2);			
			HelperMethods.setNullToEmpty(_patientImageDtlVO);
			
			daoObj.setProcInValue(nProcIndex2, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_puk",_patientImageDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_episodecode",_patientImageDtlVO.getEpisodeCode(),3);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_isdefault",_patientImageDtlVO.getIsImageDefault(),4);
			//daoObj.setProcInValue(nProcIndex2, "p_gnum_fileno",_patientImageDtlVO.getFileNo(),5);
			daoObj.setProcInValue(nProcIndex2, "p_gnum_fileno",_patientImageDtlVO.getDocId(),5); //Modified by Vasu on 10.Aug.2018 to save file name as per document id
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
        return _patientImageDtlVO;
	}

/*
 * Added by Manisha Gangwar date : 22.3.2018  Saving Image to Postgres
 */



public void saveImageToPostgres(HisDAO daoObj,PatientImageDtlVO _patientImageDtlVO, byte[] decodedData, UserVO _userVO) throws Exception
{
	
	PreparedStatement ps = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	try
	{
		if(_patientImageDtlVO.getImageFile()!=null)
		{
		 String query = "update HRGT_PATIENT_DOCUMENT_DTL SET gbyte_doc_data=? where HRGNUM_PUK=? and HRGNUM_S_NO = (SELECT NVL (MAX (hrgnum_s_no),1) FROM HRGT_PATIENT_DOCUMENT_DTL where hrgnum_puk = ?)";
	   	 ps = conn.prepareStatement(query);
	   	 InputStream iss = new ByteArrayInputStream(_patientImageDtlVO.getImageFile());	
	   	 ps.setBinaryStream(1,iss,_patientImageDtlVO.getImageFile().length);
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
	finally{
		if(daoObj!=null){
			daoObj.free();
		}
		daoObj=null;
	}
}


public byte[] latestFetchImagePostgres(String fileNo) throws Exception
{
	PreparedStatement ps = null;
	PreparedStatement ps1 = null;
	Connection conn	=	super.getTransactionContext().getConnection();
	byte[] imgBytes = null;
	InputStream fis=null;
	try
	{
		
		
		String query = "select gbyte_doc_data, to_char(gnum_entry_source) from HRGT_PATIENT_DOCUMENT_DTL where hrgstr_document_id = ? and gnum_isvalid=1";
		 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   	 ps.setString(1,fileNo);
		 ResultSet resultSet = ps.executeQuery();
		 
		 if(resultSet!=null && resultSet.next())
			{
			 if(resultSet.getString(2).equals("203") ||resultSet.getString(2).equals("204"))
				 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
			 else
				 imgBytes = resultSet.getBytes(1);
			  if(imgBytes!=null)
			  fis = new ByteArrayInputStream(imgBytes); 
				
			  
			}
		 
		 // Added by Dheeraj on 04-Oct-2018 for Patient Icon access
		 
		 else
		 {
			 resultSet.close();
			 String query1 = "select hpmrbytea_flag_value from hpmrt_config_flag_mst where hpmrstr_flag_description = ? and gnum_isvalid=1";
			 ps1= conn.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 ps1.setString(1,fileNo);
		   	 //ps.setString(1,fileNo);
			 
			 ResultSet resultSet1 = ps1.executeQuery();
			 
			 if(resultSet1!=null && resultSet1.next())
				{
				 Base64 codec = new Base64();
				 imgBytes = Base64.decodeBase64(resultSet1.getBytes(1));
				  imgBytes = resultSet1.getBytes(1);
				  if(imgBytes!=null)
				  fis = new ByteArrayInputStream(imgBytes);   
				}
			 
		 }
		 
		////////////////////////////////////////////////////////////////////////////////////////////////////// 
		 
		 
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
	

	public void populateMap(PatientImageDtlVO _patImageDtlVO,UserVO _userVO, Map _populateMAP)throws SQLException
	{
			Sequence sq=new Sequence();
				
			_populateMAP.put(sq.next(),_patImageDtlVO.getPatCrNo());
			_populateMAP.put(sq.next(),_patImageDtlVO.getPatCrNo());
			_populateMAP.put(sq.next(),_patImageDtlVO.getEpisodeCode());
			_populateMAP.put(sq.next(),_patImageDtlVO.getIsImageDefault());
			_populateMAP.put(sq.next(),_userVO.getIpAddress());
			_populateMAP.put(sq.next(),_userVO.getSeatId());
			_populateMAP.put(sq.next(),_patImageDtlVO.getIsValid());
			_populateMAP.put(sq.next(),_userVO.getHospitalCode());
			_populateMAP.put(sq.next(),_patImageDtlVO.getLastModifiedDate());
			_populateMAP.put(sq.next(),_patImageDtlVO.getLastModifiedSeatId());
			_populateMAP.put(sq.next(),_patImageDtlVO.getFileNo());
			_populateMAP.put(sq.next(),_patImageDtlVO.getFilePath());
			

	
	}

	public String selctMaxSerialNo(String patCrNo, UserVO _userVO)
	{
		//String queryKey = "SELECT.MAX_SNO.HRGT_PATIENT_IMAGE_DTL";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_PATIENT_IMAGE_DTL(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try
		{
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk",  patCrNo,3);
			daoObj.setProcInValue(nProcIndex, "hrgnum_episode_code", "",4);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e
					+ strErr);
		}
		finally
		{
			if (daoObj != null)
			
				daoObj.free();
				daoObj = null;
			
		}

		String SerialNo = "";
		try
		{
			if (rs.next())
			{
				SerialNo = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return SerialNo;
	}


public PatientImageDtlVO[] retrivePayientImageByCrNo(String patCrNo, UserVO _userVO){
	ResultSet rs=null;
	ResultSet rs1=null;
	String query  = "";
	Sequence sq=new Sequence();
    Map populateMAP =new HashMap();
    Map populateMAP1 =new HashMap();
    String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
    String queryKey="SELECT.HRGT_PATIENT_IMAGE_DTL.IMAGE_BY_CRNO_EMR";      
    PatientImageDtlVO[] patientImageDtlVO=null;
    ValueObject[] valueObjects=null;
    try{
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    System.out.println("query"+query);
    
    try{
    	populateMAP.put(sq.next(),patCrNo);
    	populateMAP.put(sq.next(),hisglobal.hisconfig.Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	
    }
    catch(Exception e){
    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
    }
    try{
    	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    	
    	if (!rs.next()){
			//System.out.println("no records");
		//	throw new HisRecordNotFoundException(); 
    		rs.close();
    		
    		//Added by Dheeraj on 05-Oct-2018 to access Patient Gender Icon
			String query1 = "select gstr_gender_code fileNo, gnum_isvalid isImageDefault from hrgt_patient_dtl where hrgnum_puk= ? and gnum_isvalid=? AND GNUM_HOSPITAL_CODE=?";
			//Added by Dheeraj on 05-Oct-2018 to access patient gender icon
			
			
			try{
				rs1=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query1,populateMAP);
			}
			
			catch(Exception e){
		    	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
		    }
			
			if(!rs1.next()){
				System.out.println("no records");
			}
			
			else {
				rs1.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(PatientImageDtlVO.class, rs1);
				patientImageDtlVO=new PatientImageDtlVO[valueObjects.length];
				
				for(int i=0;i< valueObjects.length;i++){
					
					patientImageDtlVO[i]=(PatientImageDtlVO)valueObjects[i];
										}
			}
			
			
			
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
			
			
		}
		else
		{
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(PatientImageDtlVO.class, rs);
			patientImageDtlVO=new PatientImageDtlVO[valueObjects.length];
			
			for(int i=0;i< valueObjects.length;i++){
				
				patientImageDtlVO[i]=(PatientImageDtlVO)valueObjects[i];
									}
	    	
		}
    	
    	
    }
    catch(Exception e){
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
    return patientImageDtlVO;
}


	public List getPatientImages(String patCrNo, UserVO _userVO){
		List patientImageDtlVOList=new ArrayList();
		PatientImageDtlVO imageDtlVO=null;
		ResultSet rs=null;
		String query  = "";
		Sequence sq=new Sequence();
		Map populateMAP =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.ALL_IMAGES_BY_CRNO.HRGT_PATIENT_IMAGE_DTL";                       
		//Properties properties = new Properties();        
		//call the getQueryMethod with arguments filename,querykey from prop file
		String SerialNo="";
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e){
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		System.out.println("query"+query);
		
		try{
			populateMAP.put(sq.next(),patCrNo);
			populateMAP.put(sq.next(),hisglobal.hisconfig.Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			
		}
		catch(Exception e){
			throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
		}
		try{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Image Found");
			}
			else{
				rs.beforeFirst();
				while(rs.next()){
					imageDtlVO=new PatientImageDtlVO();
					HelperMethods.populateVOfrmRS(imageDtlVO, rs);
					patientImageDtlVOList.add(imageDtlVO);
					
				}
			}
		}
		
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else{	
				throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
			}	
		}
		return patientImageDtlVOList;
	}
	
	public int deletePatientImage(PatientImageDtlVO _patientImageDtlVO, UserVO _userVO){
		
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="DELETE.HRGT_PATIENT_IMAGE_DTL";                       
        int rowCount=0;
        Sequence sq=new Sequence();
        try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e){
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		System.out.println("query"+query);
		
		try{
			populateMAP.put(sq.next(),hisglobal.hisconfig.Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),_userVO.getSeatId());
			populateMAP.put(sq.next(),_patientImageDtlVO.getPatCrNo());
			populateMAP.put(sq.next(),_patientImageDtlVO.getSerialNo());
				
		}
		catch(Exception e){
			throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
		}
		try{
			rowCount=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		return rowCount;
	}
	
	/**
	 * 
	 * @param _patientImageDtlVO
	 * @param _userVO
	 * @return Modify the field isImageDefault...........
	 * Modified By Pragya at 9-Aug-2011
	 */
	/*public int modifyPatientImage(HisDAO daoObj, PatientImageDtlVO _patientImageDtlVO, UserVO _userVO)
	{
		//String queryKey="MODIFY.HRGT_PATIENT_IMAGE_DTL";
		int rowCount = 1;
		String strErr = "";
		int nProcIndex2 = 0;
		String strProcName1 = "";

		try
		{
			strProcName1 = "{call pkg_reg_dml.PROC_HRGT_PATIENT_IMAGE_DTL(?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?,?::numeric,?,?,?)}";

			HelperMethods.setNullToEmpty(_patientImageDtlVO);

			nProcIndex2 = daoObj.setProcedure(strProcName1);
			
			
			daoObj.setProcInValue(nProcIndex2, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_puk", _patientImageDtlVO.getPatCrNo(),2);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_slno", _patientImageDtlVO.getSerialNo().equals("")?"0":_patientImageDtlVO.getSerialNo(),3);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_episode_code", _patientImageDtlVO.getEpisodeCode().equals("")?"0":_patientImageDtlVO.getEpisodeCode(),4);
			daoObj.setProcInValue(nProcIndex2, "hrgnum_is_default", _patientImageDtlVO.getIsImageDefault(),5);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_ip_add", _userVO.getIpAddress(),6);
			daoObj.setProcInValue(nProcIndex2, "gnum_seat_id", _userVO.getSeatId(),7);
			daoObj.setProcInValue(nProcIndex2, "gdt_entry_date", "",8);
			daoObj.setProcInValue(nProcIndex2, "gnum_isvalid", _patientImageDtlVO.getIsValid(),9);
			daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code", _userVO.getHospitalCode(),10);
			daoObj.setProcInValue(nProcIndex2, "gdt_lstmod_date", "",11);
			daoObj.setProcInValue(nProcIndex2, "gnum_lstmod_seatid", _userVO.getSeatId(),12);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_fileno", _patientImageDtlVO.getFileNo(),13);
			daoObj.setProcInValue(nProcIndex2, "hrgstr_path", _patientImageDtlVO.getFilePath(),14);
			daoObj.setProcOutValue(nProcIndex2, "err", 1,15);
			
			daoObj.execute(nProcIndex2, 1);

			// strErr = daoObj.getString(nProcIndex1, "err");
			if (strErr == null) strErr = "";

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
		return rowCount;
	}
	
*/	
	//Added BY Warish, Dated on 25-10-2017, for MOdify Patient img detail
	
	public int modifyPatientImage(HisDAO daoObj, PatientImageDtlVO _patientImageDtlVO, UserVO _userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="MODIFY.HRGT_PATIENT_IMAGE_DTL";                       
        int rowCount=0;
        Sequence sq=new Sequence();
        try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e){
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		System.out.println("query"+query);
		
		try{
			
			populateMAP.put(sq.next(),_patientImageDtlVO.getIsImageDefault());
			populateMAP.put(sq.next(),_patientImageDtlVO.getPatCrNo());
			populateMAP.put(sq.next(),_patientImageDtlVO.getSerialNo());
				
		}
		catch(Exception e){
			throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
		}
		try{
			rowCount=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return rowCount;
		}
	

	public PatientImageDtlVO getPatientDefaultImageByCrNo(String patCrNo, UserVO _userVO){
		
		
		WebRowSet rs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call Pkg_Reg_View.get_IMAGE_BY_CRNO(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		PatientImageDtlVO patientImageDtlVO=new PatientImageDtlVO();
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "p_hosp_code", _userVO.getHospitalCode(),1);
		daoObj.setProcInValue(nProcIndex, "p_crno", patCrNo,2);
		daoObj.setProcInValue(nProcIndex, "p_isvalid",hisglobal.hisconfig.Config.IS_VALID_ACTIVE,3);
		daoObj.setProcInValue(nProcIndex, "p_isimagetrue",RegistrationConfig.IS_IMAGE_DEFAULT_TRUE,4);		
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
			/*queryDate = HelperMethodsDAO.getQuery(filename, queryKeyDate);
			System.out.println("queryDate " + queryDate);
*/
		strErr = daoObj.getString(nProcIndex, "err");
		//System.out.println("strErr----------------------->"+strErr);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}

		try {
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Patient Details Not Found");
			} else {
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(patientImageDtlVO, rs);
				
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
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
	
	public PatientImageDtlVO getMedicalBoardPatientImage(String patCrNo, UserVO _userVO)
	{
		PatientImageDtlVO imageDtlVO = null;
		ResultSet rs = null;
		String query = "";
		Sequence sq = new Sequence();
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.DEFAULT_IMAGES_BY_CRNO.HRGT_PATIENT_IMAGE_DTL";
		 //Properties properties = new Properties();
	   // call the getQueryMethod with arguments filename,querykey from prop file
		String SerialNo = "";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{
			populateMAP.put(sq.next(), patCrNo);
			populateMAP.put(sq.next(), hisglobal.hisconfig.Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Image Found");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				imageDtlVO = new PatientImageDtlVO();
				HelperMethods.populateVOfrmRS(imageDtlVO, rs);

			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
			{
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		return imageDtlVO;
	}
	
//Added BY Akash Singh, Dated on 05-10-2015, for getting count form HRGT_PATIENT_IMAGE_DTL
	public String getContCrNoWise(String _patCrNo,UserVO _userVO)
	{
		String count="0";
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="SELECT.HRGT_PATIENT_IMAGE_DTL.COUNT_CR_NO_WISE";
        
        
        try{
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
       
        populateMAP.put(sq.next(),_patCrNo);
        
     	try{
 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 	 	    if(rs.next()){
 	 	       count=rs.getString(1);
 	 	    }
 	 	 
 	 	   
 	 	 
 	 	  			
 	 	  }
 		catch(Exception e){
 			if(e.getClass()==HisRecordNotFoundException.class){
 				throw new HisRecordNotFoundException(e.getMessage());	
 			}
 			else			 		
 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
 		 }			 
    
		return count;
	}
	
	//Added by Vasu on 05.Nov.2018
	public void savePatProfileToPostgreS(HisDAO daoObj,EHR_PatientProfileDtlVO _patProfileDtlVO, byte[] decodedData, UserVO _userVO) throws Exception
	{
		
		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(_patProfileDtlVO.getProfileDataPdf()!=null)
			{
			 String query = "update hpmrt_pat_profile_dtl SET gbyte_profile_pdf_data=? where HRGNUM_PUK=? and hpmrnum_slno = (SELECT NVL (MAX (hpmrnum_slno),1) FROM hpmrt_pat_profile_dtl where hrgnum_puk = ?)";
		   	 ps = conn.prepareStatement(query);
		   	 InputStream iss = new ByteArrayInputStream(_patProfileDtlVO.getProfileDataPdf());	
		   	 ps.setBinaryStream(1,iss,_patProfileDtlVO.getProfileDataPdf().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(2,_patProfileDtlVO.getPatCrNo());
		   	 ps.setString(3,_patProfileDtlVO.getPatCrNo());
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
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
	}
	

}
