package opd.dao;

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
import hisglobal.vo.ImageMasterVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import opd.OpdConfig;
import registration.RegistrationConfig;

public class OpdPatientImageDtlDAO extends DataAccessObject implements OpdPatientImageDtlDAOi
{
	public OpdPatientImageDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	// Creating Initial Entry Record
	public String createInitialEntry(OpdPatientImageDtlVO _VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.INITIAL_ENTRY.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), _VO.getPatCrNo());
			populateMAP.put(sq.next(), _VO.getPatCrNo());
			populateMAP.put(sq.next(), _VO.getEpisodeCode());
			populateMAP.put(sq.next(), _VO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _VO.getEpisodeCode());
			populateMAP.put(sq.next(), _VO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdPatientImageDtlDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}		
		String sno=selectMaxSerialOfCRNo(_VO.getPatCrNo(),_UserVO);		
		return sno;
	}

	// Removing Initial Entry Record
	public void removeInitialEntries(OpdPatientImageDtlVO _VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "DELETE.REMOVE_INITIAL_ENTRY.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), _VO.getPatCrNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdPatientImageDtlDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// Selecting Maximum Serial of given CRNO
	public String selectMaxSerialOfCRNo(String _CrNo, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.SNO_OF_CRNO.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), _CrNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		String sno = new String();
		try
		{
			if (rs.next())
			{
				rs.first();
				sno = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Image List" + e);
		}
		return sno;
	}

	// Updating Record After Final Saving
	public void updateFinalRecord(OpdPatientImageDtlVO _VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.FINAL_RECORD.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), _VO.getEpisodeCode());
			populateMAP.put(sq.next(), _VO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _VO.getImageName());
			populateMAP.put(sq.next(), _VO.getImageFileName());
			populateMAP.put(sq.next(), _VO.getRemarks());
			populateMAP.put(sq.next(), _VO.getDirPath());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _VO.getPatCrNo());
			populateMAP.put(sq.next(), _VO.getSerialNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdPatientImageDtlDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

	}

	// Getting Images List of Patient in given Episode Visit
	public List<OpdPatientImageDtlVO> getOPDPatOldImagesList( UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.EPISODE_PAT_IMAGES.HOPT_PAT_IMAGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			String orderBy = " ORDER BY GDT_ENTRY_DATE DESC";
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				query= query+orderBy;
				
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query= query+"AND HRGNUM_VISIT_NO=?"+orderBy;
				
			}	
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				populateMAP.put(sq.next(), voDP.getPatCrNo());
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				populateMAP.put(sq.next(), voDP.getPatCrNo());
				populateMAP.put(sq.next(), voDP.getEpisodeCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), voDP.getEpisodeVisitNo());
			}	
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

		List<OpdPatientImageDtlVO> lstImages = new ArrayList<OpdPatientImageDtlVO>();
		ValueObject vo[] = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(OpdPatientImageDtlVO.class, rs);
				for(ValueObject v : vo)
				{
					lstImages.add((OpdPatientImageDtlVO)v);					
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Previos Patient Image List" + e);
		}
		return lstImages;
	}


	
	/**
	 * To get patient diagnosis images
	 * @param _PatImgVo
	 * @param _UserVO
	 * @return List of OpdPatientImageDtlVO
	 */
	public OpdPatientImageDtlVO[] getPatientImagesDetailsEMR(OpdPatientImageDtlVO _PatImgVo, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HOPT_PAT_IMAGE_DTL.EMR";

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
			populateMAP.put(sq.next(), _PatImgVo.getPatCrNo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		OpdPatientImageDtlVO[] opdPatientImageDtlVOs = null;
		ValueObject vo[] = null;
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(OpdPatientImageDtlVO.class, rs);
				opdPatientImageDtlVOs=new OpdPatientImageDtlVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					opdPatientImageDtlVOs[i]=(OpdPatientImageDtlVO)vo[i];			
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Image List" + e);
		}
		return opdPatientImageDtlVOs;
	}


	// Selecting Maximum Serial of given Patient Episode
	public String selectMaxSerialFileName(OpdPatientImageDtlVO _vo, UserVO _userVO)
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

		String imageFileName = "";
		try
		{
			if (rs.next())
			{
				rs.first();
				imageFileName = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting File Name" + e);
		}
		return imageFileName;
	}

	// Saving Record
	public String save(OpdPatientImageDtlVO _vo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.RECORD.HOPT_PAT_IMAGE_DTL";

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
			//populateMAP.put(sq.next(), _vo.getPatCrNo());
			populateMAP.put(sq.next(), _vo.getEpisodeCode());
			populateMAP.put(sq.next(), _vo.getEpisodeVisitNo());
			populateMAP.put(sq.next(), (_vo.getAdmissionNo()!=null && _vo.getAdmissionNo().trim().equals(""))?null:_vo.getAdmissionNo());
			populateMAP.put(sq.next(), _vo.getImageName());
			
			// File Name ( <PatCrno>$<date in dd-mm-yyyy>$<slno>.<extension> )
				//populateMAP.put(sq.next(), Config.IMAGE_EDITOR_EXAMINATION_FILENAME_STARTSWITH+_vo.getPatCrNo()+Config.IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR);
			populateMAP.put(sq.next(), _vo.getPatCrNo() + Config.IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR);
			populateMAP.put(sq.next(), _vo.getEntryDate());
			populateMAP.put(sq.next(), Config.IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR);
			populateMAP.put(sq.next(), _vo.getPatCrNo());
			populateMAP.put(sq.next(), Config.IMAGE_EDITOR_EXAMINATION_FILENAME_EXTENSION);
			
			populateMAP.put(sq.next(), _vo.getRemarks());
			populateMAP.put(sq.next(), _vo.getDirPath());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _vo.getEntryDate());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdPatientImageDtlDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		String imageFileName = selectMaxSerialFileName(_vo, _userVO);
		
		return imageFileName;
	}

	// Updating Record For Modification
	public void updateOld(OpdPatientImageDtlVO _vo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.OLD_RECORD.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _vo.getNewFileName());
			populateMAP.put(sq.next(), _vo.getImageFileName());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdPatientImageDtlDAO.populateMAP::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

	}

	// Getting Images Log Detail By Image File Name
	public OpdPatientImageDtlVO getImagesLogDetail(String _imageFileName, UserVO _userVO)
	{
		String query = "";
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.LOG_PAT_IMAGES.HOPT_PAT_IMAGE_DTL";

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
			populateMAP.put(sq.next(), _imageFileName);
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

		OpdPatientImageDtlVO vo = null;
		try
		{
			if (rs.next())
			{				
				vo = new OpdPatientImageDtlVO();
				HelperMethods.populateVOfrmRS(vo, rs);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getting Previos Patient Image List" + e);
		}
		return vo;
	}

	public List<OpdPatientImageDtlVO> getOPDPatOldImagesList(
			OpdPatientImageDtlVO _PatImgVo, UserVO _UserVO) {
		// TODO Auto-generated method stub
		return null;
	}
	//Added By Shweta on 31-JUN-2019
	public byte[] latestFetchImagePostgres(String imgCode) throws Exception
	{
		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		byte[] imgBytes = null;
		InputStream fis=null;
		try
		{
			
			
			String query = "select gbyte_image_data from HOPT_IMAGE_MST where hopnum_image_code= ? and gnum_isvalid=1";
			 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		   	 ps.setString(1,imgCode);
			 ResultSet resultSet = ps.executeQuery();
			 
			 if(resultSet!=null && resultSet.next())
				{
				 Base64 codec = new Base64();
				 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
				  imgBytes = resultSet.getBytes(1);
				  if(imgBytes!=null)
				  fis = new ByteArrayInputStream(imgBytes); 
					
				  
				}
		
	}
		catch(Exception e)
	   	{
	   		
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { }
	   	}
	return imgBytes;
}
	
	public void saveImageToPostgres(OpdPatientImageDtlVO patImageVO,String imageFileName) throws SQLException
	{
		PreparedStatement ps = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		try
		{
			if(patImageVO.getImageFile()!=null)
			{
			 String query = " update HOPT_PAT_IMAGE_DTL SET gbyte_image_data=? where hrgnum_puk=? and gnum_isvalid=1 and hopstr_image_filename=?";
		   	 ps = conn.prepareStatement(query);
		   	 InputStream iss = new ByteArrayInputStream(patImageVO.getImageFile());	
		   	 ps.setBinaryStream(1,iss,patImageVO.getImageFile().length);
			// ps.setBytes(1,decodedData);
		   	 ps.setString(2,patImageVO.getPatCrNo());
		   	ps.setString(3,imageFileName);
		   	// ps.setString(3,_patientImageDtlVO.getPatCrNo());
		   	 ps.executeUpdate();
			}
		}
		catch(Exception e)
		   	{
		   		
		   
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
	
	public byte[] viewImageFromPostgres(OpdPatientImageDtlVO vo) throws Exception
	{
		
		PreparedStatement ps = null;
		//PreparedStatement ps1 = null;
		Connection conn	=	super.getTransactionContext().getConnection();
		byte[] imgBytes = null;
		InputStream fis=null;
		try
		{
			
			
			String query = "select gbyte_image_data from HOPT_PAT_IMAGE_DTL where hopstr_image_filename=? and gnum_isvalid=1";
			 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		   	 ps.setString(1,vo.getImageFileName());
			 ResultSet resultSet = ps.executeQuery();
			 
			 if(resultSet!=null && resultSet.next())
				{
				 Base64 codec = new Base64();
				 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
				  imgBytes = resultSet.getBytes(1);
				  if(imgBytes!=null)
				  fis = new ByteArrayInputStream(imgBytes); 
					
				  
				}
		
	}
		catch(Exception e)
	   	{
	   		
	   		try 
	   		{  ps.close(); ps =null;  throw new Exception("error in saving image to postgres...terminated unsuccesfully");
	   		} 
	   		catch( SQLException se ) { }
	   	}
	return imgBytes;
}
	
}

