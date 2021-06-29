package ehr.ImageExam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import opd.bo.OpdEssentialBOi;
import opd.dao.OpdPatientImageDtlDAO;
import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.UserVO;
import registration.dao.DailyPatientDAO;

public class EHRSection_ImageExamBO {
public List AJAX_GETIMAGELIST(EHRSection_ImageExamVO vo,UserVO _userVO)
	{
		//String count = "";
		List imageList=new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			EHRSection_ImageExamDAO imgDAO=new EHRSection_ImageExamDAO(tx);
			imageList = imgDAO.AJAX_GETIMAGELIST(vo, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return imageList;

	}
public static void SAVEIMAGE(EHRSection_ImageExamVO patImageVO, UserVO userVO) {
	  JDBCTransactionContext tx = new JDBCTransactionContext();
	 
	 // HisDAO hisDAO = new HisDAO("HISClinical", "OpdPatientBO");
	  try
	  {
		  tx.begin();
		  
		  EHRSection_ImageExamDAO patImageDAO=new EHRSection_ImageExamDAO(tx);
		 String count=patImageDAO.getContCrNoWise(patImageVO.getPatCrNo(), userVO);
		 int countInt=Integer.parseInt(count);
		 countInt=countInt+1;
				String docId="";
				//docId=patImageVO.getImageCode()+"-"+patImageVO.getPatCrNo()+"-"+patImageVO.getEpisodeCode();
				docId=patImageVO.getPatCrNo()+"_"+patImageVO.getEpisodeCode()+"_"+countInt+".png";
				//System.out.println("Inside FTP");
				
				//MongoXmlHandler.getInstanceWithURL(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD, HISConfig.HIS_MONGODB_PAT_DOC).savePDFFile(docId, patImageVO.getImageFile());
				
				File file=new File(docId);
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(patImageVO.getImageFile()));
				ImageIO.write(image, "png", file);
				//FileUtils.writeByteArrayToFile(file, patImageVO.getImageFile());
				FileInputStream fileInFTPStream = new FileInputStream(file);
				//System.out.println("hiiii");
				String fileLocation=FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, docId, fileInFTPStream, patImageVO.getPatCrNo());
				//System.out.println("hiii");
				//System.out.println("imageFileName:::"+patImageVO.getImageFileName());
				
				if(fileLocation!=null){
				patImageVO.setImageFileName(fileLocation);
				 patImageDAO.save(patImageVO, userVO);
				}
				
				/*GsonBuilder gsonBuilder=new GsonBuilder();
				Gson gson=gsonBuilder.create();
				
				String JSONObject_SaveImageList=gson.toJson(patImageVO);*/
				//System.out.println(JSONObject_SaveImageList);
				//MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_PATIENT_IMAGE_UPLOAD).savePDFFile(docId, patImageVO.getImageFile());
				//System.out.println("FTP Saved");
				//patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
				//System.out.println();
				/*if( patImageDAO.save(patImageVO, userVO))
				MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD).savePDFFile(docId,  patImageVO.getImageFile());*/
				//patImageDAO.saveImageToPostgres(patImageVO,imageSNo);
			
	  }
		
	  catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

public List AJAX_GETPREVIMGEXM(EHRSection_ImageExamVO vo,UserVO _userVO)
{
	//String count = "";
	List<EHRSection_ImageExamVO> imageList=new ArrayList<EHRSection_ImageExamVO>();
	JDBCTransactionContext tx = new JDBCTransactionContext();
	try
	{
		tx.begin();
		EHRSection_ImageExamDAO imgDAO=new EHRSection_ImageExamDAO(tx);
		GsonBuilder gsonBuilder=new GsonBuilder();
		Gson gson=gsonBuilder.create();
		
		String JSONObject_SaveImageList=gson.toJson(vo);
		//System.out.println(JSONObject_SaveImageList);
		imageList = imgDAO.AJAX_GETPREVIMGEXM(vo, _userVO);
		
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	}

	catch (HisDataAccessException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisDataAccessException();
	}
	catch (Exception e)
	{
		e.printStackTrace();
		//System.out.println("error.... Essential BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return imageList;

}

public List AJAX_GETOTHERIMAGELIST(EHRSection_ImageExamVO vo,UserVO _userVO)
{
	//String count = "";
	List imageList=new ArrayList();
	JDBCTransactionContext tx = new JDBCTransactionContext();
	try
	{
		tx.begin();
		EHRSection_ImageExamDAO imgDAO=new EHRSection_ImageExamDAO(tx);
		imageList = imgDAO.AJAX_GETOTHERIMAGELIST(vo, _userVO);
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	}

	catch (HisDataAccessException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisDataAccessException();
	}
	catch (Exception e)
	{
		e.printStackTrace();
		//System.out.println("error.... Essential BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return imageList;

}
public List GetOtherImageSrc(EHRSection_ImageExamVO vo,UserVO _userVO)
{
	//String count = "";
	List imageList=new ArrayList();
	JDBCTransactionContext tx = new JDBCTransactionContext();
	try
	{
		tx.begin();
		EHRSection_ImageExamDAO imgDAO=new EHRSection_ImageExamDAO(tx);
		imageList = imgDAO.GetOtherImageSrc(vo, _userVO);
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	}

	catch (HisDataAccessException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisDataAccessException();
	}
	catch (Exception e)
	{
		e.printStackTrace();
		//System.out.println("error.... Essential BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	return imageList;

}

public void AJAX_REVOKEIMAGE(EHRSection_ImageExamVO vo,UserVO _userVO)
{
	//String count = "";
	List imageList=new ArrayList();
	JDBCTransactionContext tx = new JDBCTransactionContext();
	try
	{
		tx.begin();
		EHRSection_ImageExamDAO imgDAO=new EHRSection_ImageExamDAO(tx);
		 imgDAO.AJAX_REVOKEIMAGE(vo, _userVO);
	}
	catch (HisRecordNotFoundException e)
	{
		tx.rollback();
		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisApplicationExecutionException();
	}

	catch (HisDataAccessException e)
	{
		tx.rollback();
		e.printStackTrace();
		throw new HisDataAccessException();
	}
	catch (Exception e)
	{
		e.printStackTrace();
		//System.out.println("error.... Essential BO");
		tx.rollback();
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();
	}
	//return imageList;

}
}