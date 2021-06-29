package ehr.ImageExam;

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
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import opd.OpdConfig;
import registration.RegistrationConfig;



public class EHRSection_ImageExamDAO extends DataAccessObject 
{
	public EHRSection_ImageExamDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	// Creating Initial Entry Record
	public List<EHRSection_ImageExamVO> AJAX_GETIMAGELIST(EHRSection_ImageExamVO imageExamVO,UserVO _userVO)
	{
	//	System.out.println("in getPrevReqList");
		
		ResultSet rs 		= 	null;
		List alRecord 		= 	new ArrayList();
		Connection conn		=	super.getTransactionContext().getConnection();
		CallableStatement cstmt=null;
		try 
		{
			
			cstmt=conn.prepareCall("{ call PKG_EHR_VIEW.get_image_list(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, "1");
			cstmt.setString(2, "");
			cstmt.setString(3, imageExamVO.getDepartmentUnitCode());
			cstmt.setString(4, "");
			cstmt.setString(5, "");
			cstmt.setString(6,_userVO.getSeatId());
			cstmt.setString(7,_userVO.getHospitalCode());
			cstmt.setString(8,"");
			cstmt.setString(9,imageExamVO.getEpisodeCode());
			cstmt.setString(10, imageExamVO.getEpisodeVisitNo());
			cstmt.setString(11, imageExamVO.getAdmissionNo());
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.REF);
			cstmt.execute();
			rs=(ResultSet)cstmt.getObject(13);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		try
		{
			if(rs==null)
			{
			//	System.out.println("null resultset");
			}
			while(rs.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("data:image/png;base64,");
				sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rs.getBytes(4), false)));
				//contourChart = sb.toString();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				EHRSection_ImageExamVO imageExamVO1=new EHRSection_ImageExamVO();
					/*serviceVO.setVisitType(rs.getString(1));
					serviceVO.setDeptName(rs.getString(2));
					serviceVO.setWardname(rs.getString(3));
					serviceVO.setRemark(rs.getString(4));
					serviceVO.setServiceAreaName(rs.getString(5));
					serviceVO.setProcedureName(rs.getString(6));
					serviceVO.setRequisitionDate(rs.getString(7));
					serviceVO.setProc_Code(rs.getString(8));
					serviceVO.setServiceAreaCode(rs.getString(9));
					serviceVO.set_reqStatus(rs.getString(10));
					serviceVO.setSittingsDone(rs.getString(11));
					serviceVO.setSittingsRequired(rs.getString(12));
					serviceVO.setDtService(rs.getString(13));*/
				imageExamVO1.setDepartmentUnitCode(rs.getString(1));
				imageExamVO1.setImageCode(rs.getString(2));
				imageExamVO1.setImageName(rs.getString(3));
				//byte[] imgSrc=Base64.encodeBase64(rs.getBytes(4));
				String base64DataString = sb.toString();
				imageExamVO1.setImageFileName(base64DataString);
				imageExamVO1.setIsImageDefault(rs.getString(4));
					alRecord.add(imageExamVO1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPrevReqList" + e);
//			e.printStackTrace();
		}
		finally
		{
			if(cstmt!=null)
			{
				try {
					cstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
	public String getContCrNoWise(String _patCrNo,UserVO _userVO)
	{
		String count="0";
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
        String queryKey="SELECT.hopt_pat_image_dtl.COUNT_CR_NO_WISE";
        
        
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
	
	
	public boolean save(EHRSection_ImageExamVO _vo, UserVO _userVO)
	{
		HisDAO dao = null;
	
		int nInsertedIndex1 = 0;
	      try{    
	    	  dao = new HisDAO("EHR Transaction", "EHRSection_ImageExamDAO.save");
	    	  
		nInsertedIndex1 = dao.setProcedure("{call pkg_ehr_dml.proc_HOPT_PAT_IMAGE_DTL_dml(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

		dao.setProcInValue(nInsertedIndex1, "p_mode", "1", 1);
		dao.setProcInValue(nInsertedIndex1, "p_puk", _vo.getPatCrNo(), 2);
		dao.setProcInValue(nInsertedIndex1, "p_episodecode", _vo.getEpisodeCode(), 3);
		dao.setProcInValue(nInsertedIndex1, "p_visit_no", _vo.getEpisodeVisitNo(), 4);
		
		
		dao.setProcInValue(nInsertedIndex1, "p_admissionno", _vo.getAdmissionNo()!=null?_vo.getAdmissionNo():"", 5);
		dao.setProcInValue(nInsertedIndex1, "p_entry_date", _vo.getEntryDate(), 6);

		dao.setProcInValue(nInsertedIndex1, "p_record_date", _vo.getEntryDate(), 7);
		
		dao.setProcInValue(nInsertedIndex1, "p_image_name", _vo.getImageName(), 8);
		dao.setProcInValue(nInsertedIndex1, "p_remark", _vo.getRemarks(), 9);
		dao.setProcInValue(nInsertedIndex1, "img_dir_path", _vo.getDirPath(), 10);
		dao.setProcInValue(nInsertedIndex1, "file_connector", Config.IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR, 11);
		dao.setProcInValue(nInsertedIndex1, "file_ext", Config.IMAGE_EDITOR_EXAMINATION_FILENAME_EXTENSION, 12); 
		dao.setProcInValue(nInsertedIndex1, "p_seat_id",_userVO.getSeatId() , 13); 
		dao.setProcInValue(nInsertedIndex1, "p_hosp_code", _userVO.getHospitalCode(), 14);
		dao.setProcInValue(nInsertedIndex1, "p_is_valid", Config.IS_VALID_ACTIVE, 15);
		dao.setProcInValue(nInsertedIndex1, "img_file_name",_vo.getImageFileName() , 16);
		dao.setProcOutValue(nInsertedIndex1, "err", 1, 17);
		dao.execute(nInsertedIndex1, 1);
		_vo.setErrmsg("Data Saved");
		synchronized (dao)
		{
			dao.fire();
		}
		}
	catch (Exception e) 
    {
		_vo.setErrmsg("Image Not Saved");//error
		e.printStackTrace(); 
		return false;
	}
	      finally{
				if (dao != null) {
					dao.free();
				}
				dao = null;
			}
		return true;
		
		//String imageFileName = selectMaxSerialFileName(_vo, _userVO);
		
		//return imageFileName;
	}
	
	public String selectMaxSerialFileName(EHRSection_ImageExamVO _vo, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.FILENAME_OF_MAX_SNO.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), _vo.getPatCrNo());
			populateMAP.put(sq.next(), _vo.getEntryDate());
			populateMAP.put(sq.next(), _vo.getEpisodeCode());
			populateMAP.put(sq.next(), _vo.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _vo.getImageName());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdPatientImageDtlDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		String imageSNo = "";
		try
		{
			if (rs.next())
			{
				rs.first();
				imageSNo = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting File Name" + e);
		}
		return imageSNo;
	}
	
	public void saveImageToPostgres(EHRSection_ImageExamVO patImageVO,String imageSNo) throws SQLException
	{
		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(patImageVO.getImageFile()!=null)
			{
			 String query = " update HOPT_PAT_IMAGE_DTL SET gbyte_image_data=? where hrgnum_puk=? and gnum_isvalid=1 and hrgnum_s_no=? ";
		   	 ps = conn.prepareStatement(query);
		   	 InputStream iss = new ByteArrayInputStream(patImageVO.getImageFile());	
		   	 ps.setBinaryStream(1,iss,patImageVO.getImageFile().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(2,patImageVO.getPatCrNo());
		   	ps.setString(3,imageSNo);
		   	// ps.setString(3,_patientImageDtlVO.getPatCrNo());
		   	 ps.executeUpdate();
			}
		}
		catch(Exception e)
		   	{
		   		//System.out.println("exception in Image saving transaction..."+e); 
		   
		   			ps.close();
		   			ps =null;  
		   			try {
						throw new Exception("error in saving image to postgres...terminated unsuccesfully");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    
		   	}
	}
	
	public List<EHRSection_ImageExamVO> AJAX_GETPREVIMGEXM(EHRSection_ImageExamVO imageExamVO,UserVO _userVO)
	{
	//	System.out.println("in getPrevReqList");
		
		ResultSet rs 		= 	null;
		List<EHRSection_ImageExamVO> alRecord 		= 	new ArrayList<EHRSection_ImageExamVO>();
		Connection conn		=	super.getTransactionContext().getConnection();
		CallableStatement cstmt=null;
		try 
		{
			
			cstmt=conn.prepareCall("{ call PKG_EHR_VIEW.get_prev_image_exm(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, "1");
			cstmt.setString(2, imageExamVO.getPatCrNo());
			cstmt.setString(3, "");
			cstmt.setString(4, "");
			cstmt.setString(5,_userVO.getSeatId());
			cstmt.setString(6,_userVO.getHospitalCode());
			cstmt.setString(7,"");
			cstmt.setString(8,imageExamVO.getEpisodeCode());
			cstmt.setString(9, imageExamVO.getEpisodeVisitNo());
			cstmt.setString(10, imageExamVO.getAdmissionNo());
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.REF);
			cstmt.execute();
			rs=(ResultSet)cstmt.getObject(12);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		try
		{
			if(rs==null)
			{
			//	System.out.println("null resultset");
			}
			while(rs.next())
			{
				ByteArrayOutputStream result = new ByteArrayOutputStream();
				String rst=rs.getString(2);
				InputStream is = FTPFileTransfer.retrieveFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, rs.getString(2).replace("//", "/"), imageExamVO.getPatCrNo());
				ByteArrayOutputStream os = new ByteArrayOutputStream();

				byte[] buffer = new byte[1024];
				int len;

				// read bytes from the input stream and store them in buffer
				while ((len = is.read(buffer)) != -1) {
					// write bytes from the buffer into output stream
					os.write(buffer, 0, len);
				}
					byte[] bytes=os.toByteArray();
					
				/*byte[] buffer=null ;
		        int length;
		        while ((length = is.read(buffer)) != -1) {
		            result.write(buffer, 0, length);
		        }*/
				//byte[] imageBytes = new byte[(int)is.length()];
				/*is.read(imageBytes, 0, imageBytes.length);
				is.close();
				String imageStr = Base64.encodeBase64String(imageBytes);*/
				//byte[] getDoc= MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).latestFetchFile(rs.getString(2));
				StringBuilder sb = new StringBuilder();
				sb.append("data:image/png;base64,");
				sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(bytes, false)));
				String base64DataString = sb.toString();
				String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE; // <ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>;"ftpmanglagiri:ftp@manglagiri@uatmanglagiri.dcservices.in/opt/ftp/uat_manglagiri";
				String ftpServerUserName = FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME;
				String ftpServerPassword = FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD;
				
				String strHostOnly=ftpServerURL.substring(ftpServerURL.lastIndexOf("@")+1);
				strHostOnly=strHostOnly.split("/")[0];
				ftpServerUserName=ftpServerUserName.replaceAll("@", "%40");
				ftpServerPassword=ftpServerPassword.replaceAll("@", "%40");
				String path="ftp://"+ftpServerUserName+":"+ftpServerPassword+"@"+strHostOnly+rs.getString(2).replace("//", "/");
				//URL urlftp = new URL(path);
				/*StringBuilder sb = new StringBuilder();
				sb.append("data:image/png;base64,");*/
				//sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rs.getBytes(4), false)));
				//contourChart = sb.toString();
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				EHRSection_ImageExamVO imageExamVO1=new EHRSection_ImageExamVO();
				imageExamVO1.setImageName(rs.getString(1));
				imageExamVO1.setImageCode(path);
				imageExamVO1.setImagePath(rs.getString(2));
				imageExamVO1.setEntryDate(rs.getString(3));
				//byte[] imgSrc=Base64.encodeBase64(rs.getBytes(4));
				//String base64DataString = sb.toString();
				imageExamVO1.setImageFileName(base64DataString);
				//imageExamVO1.setIsImageDefault(rs.getString(4));
				if(rs.getString(5)!=null)
				imageExamVO1.setRemarks(rs.getString(5));
				else
					imageExamVO1.setRemarks("-");
					alRecord.add(imageExamVO1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPrevReqList" + e);
//			e.printStackTrace();
		}
		finally
		{
			if(cstmt!=null)
			{
				try {
					cstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
	public List<EHRSection_ImageExamVO> AJAX_GETOTHERIMAGELIST(EHRSection_ImageExamVO imageExamVO,UserVO _userVO)
	{
	//	System.out.println("in getPrevReqList");
		
		ResultSet rs 		= 	null;
		List alRecord 		= 	new ArrayList();
		Connection conn		=	super.getTransactionContext().getConnection();
		CallableStatement cstmt=null;
		try 
		{
			
			cstmt=conn.prepareCall("{ call PKG_EHR_VIEW.get_image_list(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, "2");
			cstmt.setString(2, imageExamVO.getImageName()==null?"":imageExamVO.getImageName());
			cstmt.setString(3, imageExamVO.getDepartmentUnitCode());
			cstmt.setString(4, "");
			cstmt.setString(5, "");
			cstmt.setString(6,_userVO.getSeatId());
			cstmt.setString(7,_userVO.getHospitalCode());
			cstmt.setString(8,"");
			cstmt.setString(9,imageExamVO.getEpisodeCode());
			cstmt.setString(10, imageExamVO.getEpisodeVisitNo());
			cstmt.setString(11, imageExamVO.getAdmissionNo());
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.REF);
			cstmt.execute();
			rs=(ResultSet)cstmt.getObject(13);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		try
		{
			if(rs==null)
			{
			//	System.out.println("null resultset");
			}
			while(rs.next())
			{
		
				EHRSection_ImageExamVO imageExamVO1=new EHRSection_ImageExamVO();
		
				//imageExamVO1.setDepartmentUnitCode(rs.getString(1));
				imageExamVO1.setImageCode(rs.getString(1));
				imageExamVO1.setImageName(rs.getString(2));
				
					alRecord.add(imageExamVO1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPrevReqList" + e);
//			e.printStackTrace();
		}
		finally
		{
			if(cstmt!=null)
			{
				try {
					cstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
	
	public List<EHRSection_ImageExamVO> GetOtherImageSrc(EHRSection_ImageExamVO imageExamVO,UserVO _userVO)
	{
	//	System.out.println("in getPrevReqList");
		
		ResultSet rs 		= 	null;
		List alRecord 		= 	new ArrayList();
		Connection conn		=	super.getTransactionContext().getConnection();
		CallableStatement cstmt=null;
		try 
		{
			
			cstmt=conn.prepareCall("{ call PKG_EHR_VIEW.get_image_list(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, "3");
			cstmt.setString(2, imageExamVO.getImageName()==null?"":imageExamVO.getImageName());
			cstmt.setString(3, imageExamVO.getDepartmentUnitCode());
			cstmt.setString(4, "");
			cstmt.setString(5, "");
			cstmt.setString(6,_userVO.getSeatId());
			cstmt.setString(7,_userVO.getHospitalCode());
			cstmt.setString(8,"");
			cstmt.setString(9,imageExamVO.getEpisodeCode());
			cstmt.setString(10, imageExamVO.getEpisodeVisitNo());
			cstmt.setString(11, imageExamVO.getAdmissionNo());
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.REF);
			cstmt.execute();
			rs=(ResultSet)cstmt.getObject(13);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		try
		{
			if(rs==null)
			{
			//	System.out.println("null resultset");
			}
			while(rs.next())
			{
		
				EHRSection_ImageExamVO imageExamVO1=new EHRSection_ImageExamVO();
				StringBuilder sb = new StringBuilder();
				sb.append("data:image/png;base64,");
				sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rs.getBytes(3), false)));
				String base64DataString = sb.toString();

				
				imageExamVO1.setImageCode(rs.getString(1));
				imageExamVO1.setImageName(rs.getString(2));
				imageExamVO1.setImageFileName(base64DataString);
				
					alRecord.add(imageExamVO1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPrevReqList" + e);
//			e.printStackTrace();
		}
		finally
		{
			if(cstmt!=null)
			{
				try {
					cstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
	
	public void AJAX_REVOKEIMAGE(EHRSection_ImageExamVO imageExamVO,UserVO _userVO) throws SQLException
	{
		

		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(imageExamVO.getImageFileName()!=null)
			{
			 String query = "  UPDATE HOPT_PAT_IMAGE_DTL SET gnum_isvalid=0 WHERE hrgnum_puk = ? and  UPPER(hopstr_image_filename) like UPPER(?); ";
		   	 ps = conn.prepareStatement(query);
		   	 System.out.println(ps);
		   	 //InputStream iss = new ByteArrayInputStream(patImageVO.getImageFile());	
		   	 //ps.setBinaryStream(1,iss,patImageVO.getImageFile().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(1,imageExamVO.getPatCrNo());
		   	ps.setString(2,imageExamVO.getImageFileName());
		   	// ps.setString(3,_patientImageDtlVO.getPatCrNo());
		   	 ps.executeUpdate();
			}	
		}
			catch(Exception e)
		   	{
		   		//System.out.println("exception in Image saving transaction..."+e); 
		   
		   			
						ps.close();
					
		   			ps =null;  
		   			try {
						throw new Exception("error in saving image to postgres...terminated unsuccesfully");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    
		   	}
	
		/*HisDAO dao = null;
		
		int nInsertedIndex1 = 0;
	      try{    
	    	  dao = new HisDAO("EHR Transaction", "EHRSection_ImageExamDAO.AJAX_REVOKEIMAGE");
	    	  
		nInsertedIndex1 = dao.setProcedure("{call pkg_ehr_dml.proc_HOPT_PAT_IMAGE_DTL_dml(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

		dao.setProcInValue(nInsertedIndex1, "p_mode", "2", 1);
		dao.setProcInValue(nInsertedIndex1, "p_puk", imageExamVO.getPatCrNo(), 2);
		dao.setProcInValue(nInsertedIndex1, "p_episodecode", imageExamVO.getEpisodeCode()!=null?imageExamVO.getEpisodeCode():"", 3);
		dao.setProcInValue(nInsertedIndex1, "p_visit_no", imageExamVO.getEpisodeVisitNo()!=null?imageExamVO.getEpisodeVisitNo():"", 4);
		
		
		dao.setProcInValue(nInsertedIndex1, "p_admissionno", imageExamVO.getAdmissionNo()!=null?imageExamVO.getAdmissionNo():"", 5);
		dao.setProcInValue(nInsertedIndex1, "p_entry_date", imageExamVO.getEntryDate()!=null?imageExamVO.getEntryDate():"", 6);

		dao.setProcInValue(nInsertedIndex1, "p_record_date", imageExamVO.getEntryDate()!=null?imageExamVO.getEntryDate():"", 7);
		String vrty=imageExamVO.getImageFileName().split("/",5)[1];
		dao.setProcInValue(nInsertedIndex1, "p_image_name", imageExamVO.getImageFileName().split("/")[1], 8);
		dao.setProcInValue(nInsertedIndex1, "p_remark", imageExamVO.getRemarks()!=null?imageExamVO.getRemarks():"", 9);
		dao.setProcInValue(nInsertedIndex1, "img_dir_path", imageExamVO.getDirPath()!=null?imageExamVO.getDirPath():"", 10);
		dao.setProcInValue(nInsertedIndex1, "file_connector", Config.IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR, 11);
		dao.setProcInValue(nInsertedIndex1, "file_ext", Config.IMAGE_EDITOR_EXAMINATION_FILENAME_EXTENSION, 12); 
		dao.setProcInValue(nInsertedIndex1, "p_seat_id",_userVO.getSeatId() , 13); 
		dao.setProcInValue(nInsertedIndex1, "p_hosp_code", _userVO.getHospitalCode(), 14);
		dao.setProcInValue(nInsertedIndex1, "p_is_valid", Config.IS_VALID_ACTIVE, 15);
		dao.setProcInValue(nInsertedIndex1, "img_file_name",imageExamVO.getImageFileName()!=null?imageExamVO.getImageFileName():"" , 16);
		dao.setProcOutValue(nInsertedIndex1, "err", 1, 17);
		dao.execute(nInsertedIndex1, 1);
		imageExamVO.setErrmsg("Data Revoked");
		synchronized (dao)
		{
			dao.fire();
		}
		}
	catch (Exception e) 
    {
		imageExamVO.setErrmsg("Error in Revoking");//error
		e.printStackTrace();
		
	}
	      finally{
				if (dao != null) {
					dao.free();
				}
				dao = null;
			}
		
		
	}*/
	
}
}

