/**

##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.business.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.transaction.dao.MRDDocumentUploadDAO;
import mrd.vo.MRDDocumentUploadVO;
import emr.dataentry.spp.persistence.dao.UniPagePrescriptionDAO;
import emr.dataentry.spp.persistence.dao.UniPagePrescriptionDAOi;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.datafetch.patientClinicalDocuments.persistence.dao.PatientClinicalDocumentsDAO;
import emr.datafetch.patientClinicalDocuments.persistence.dao.PatientClinicalDocumentsDAOi;
import emr.vo.EHR_PatientProfileDtlVO;
import emr.vo.PatientClinicalDocDetailVO;
import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;
import registration.RegistrationConfig;
import registration.dao.EpisodeDAO;
import registration.dao.PatientImageDtlDAO;
import hisglobal.vo.EpisodeVO;



public class UniPagePrescriptionBO implements UniPagePrescriptionBOi
{
	public void getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		boolean check=false;	
		try
		{
			tx.begin();
			UniPagePrescriptionDAOi OpdOPPDAO=new UniPagePrescriptionDAO(tx);
			
			OpdOPPDAO.getPatDetailOpp(ptaientDetailVO,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		
	}
	public List getAdvisedByList(String string, UserVO userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List AdvisedByList=new ArrayList();
		try
		{
			tx.begin();
			UniPagePrescriptionDAOi OpdOPPDAO=new UniPagePrescriptionDAO(tx);
			AdvisedByList = OpdOPPDAO.getAdvisedByList(string, userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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

			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return AdvisedByList;
	}
	
	
	
	public EpisodeVO  getPatientAdmittedEpisodes(String crNo, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO _arrEpisodeDupVO;
		EpisodeVO objArrEpisodeVO_p;
		String reprintMode = "";

		try
		{
			tx.begin();
			UniPagePrescriptionDAO OpdOPPDAO=new UniPagePrescriptionDAO(tx);
			objArrEpisodeVO_p = OpdOPPDAO.getPatientAdmittedEpisodes(crNo, userVO, "3");
			//essentialMap.put(RegistrationConfig.CHANGE_TR, objArrEpisodeVO_p);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			
			throw new HisRecordNotFoundException(e.getMessage());
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	
	
	
	public EpisodeVO  getPatientAdmittedEpisodesMRD(String crNo, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO _arrEpisodeDupVO;
		EpisodeVO objArrEpisodeVO_p;
		String reprintMode = "";

		try
		{
			tx.begin();
			UniPagePrescriptionDAO OpdOPPDAO=new UniPagePrescriptionDAO(tx);
			objArrEpisodeVO_p = OpdOPPDAO.getPatientAdmittedEpisodes(crNo, userVO, "1");
			//essentialMap.put(RegistrationConfig.CHANGE_TR, objArrEpisodeVO_p);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			
			throw new HisRecordNotFoundException(e.getMessage());
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	public EpisodeVO[]  getAllOpenEpisodesVisitedTodayForTreatment(String crNo, UserVO userVO)
	{
		Map essentialMap = new HashMap();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] _arrEpisodeDupVO;
		EpisodeVO[] objArrEpisodeVO_p;
		String reprintMode = "";

		try
		{
			tx.begin();
			UniPagePrescriptionDAO episodeDao=new UniPagePrescriptionDAO(tx);
			objArrEpisodeVO_p = episodeDao.getAllOpenEpisodesVisitedToday(crNo, userVO, "2");
			//essentialMap.put(RegistrationConfig.CHANGE_TR, objArrEpisodeVO_p);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			
			throw new HisRecordNotFoundException(e.getMessage());
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return objArrEpisodeVO_p;
	}
	
	
	// Added by Dheeraj to save Prescription on 15-Oct-2018
	
	public static void savePatientPrescriptiondetails(PatientClinicalDocDetailVO docUploadVos, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		String newFileName="";
		int i = 0;
		
		byte [] docFile = null; 
		
		try
		{
			UniPagePrescriptionDAO docUploadDaoi = new UniPagePrescriptionDAO(tx);
			tx.begin();
			if( docUploadVos!=null )
				{
				count=docUploadDaoi.getContCrNoWise(docUploadVos.getPatCrNo(), userVO);
				//int countInt=Integer.parseInt(count)+10;
				int countInt=Integer.parseInt(count);
				for (; i < 1; i++)
				{
					String sysdate=docUploadVos.getEntryDate();
					char[] charArray; 
					String newSysdate="";
					charArray=sysdate.toCharArray();
					for(int charIndex=0;charIndex<charArray.length;charIndex++){
						if(Character.isSpaceChar(charArray[charIndex])){
							newSysdate=newSysdate+'-';
						}
						else{
							newSysdate=newSysdate+charArray[charIndex];
						}
					}
					newSysdate=newSysdate.replace('/','-');
					newSysdate=newSysdate.replace(':','-');
					countInt=countInt+1;
					String[] filePartName={docUploadVos.getPatCrNo(),newSysdate,String.valueOf(countInt)};
					//String fileName=HelperMethods.getFileName('$',filePartName);
					//String fileExt=docUploadVos[i].getDocumentName().substring(docUploadVos[i].getDocumentName().lastIndexOf("."));
					String docId =docUploadVos.getPatCrNo()+"_"+countInt;
					//fileName=fileName+fileExt;
					newFileName= docUploadVos.getPatCrNo()+"_"+docUploadVos.getEpisodeCode()+"_"+countInt;
					//String docId="";
					//docId=docUploadVos[i].getPatCrNo()+"_"+countInt;
					//docUploadVos[i].setDocumentCode(docId);
					docUploadVos.setFileName(newFileName);     //documentName
					docUploadVos.setDocumentTypeDesc(docId);   //documentId
					docUploadVos.setSlNo(""+countInt);
					docUploadVos.setVisitNo(""+countInt);
					docUploadVos.setDocumentType("prescription.pdf"); //FileName
					
// comment by Akash Singh, Dated on 01-10-2015 due to change for ftp to MongoDb 					
					//COME
					//HisFileControlUtil fileUtil = new HisFileControlUtil();
					//fileUtil.setFileName(docUploadVos[i].getDocumentName());
					//fileUtil.setWindowsFilePath(docUploadVos[i].getPathForWindows());
					//fileUtil.setLinuxFilePath(docUploadVos[i].getPathPathLinux());
					//fileUtil.setFileContent(docUploadVos[i].getDocFile());
					//fileUtil.saveFile();
					
// Calling MongoDb from Here					
					//MongoXmlHandler.getInstanceWithURL(Config.NOSQL_MONGO_DATASOURCE_DOCUMENT_UPLOAD, HISConfig.HIS_MONGODB_PAT_DOC).savePDFFile(docUploadVos[i].getDocumentCode(), docUploadVos[i].getDocFile());
					docUploadDaoi.createPDF(docUploadVos, userVO);
					//docUploadDaoi.update(docUploadVos[i], userVO);
					
					}
				}
			/*if(documentUploadRevokeDtlVO!=null)
			{
				for(int j=0;j<documentUploadRevokeDtlVO.length;j++)
				{
					docUploadDaoi.revokeDocument(documentUploadRevokeDtlVO[j], userVO);
				}
			}
			*/
			//Added by Dheeraj on 27-Sept-2018  to add data to postgres
			PatientClinicalDocDetailVO patientDocDetailVO = new PatientClinicalDocDetailVO();
			
			for (i = 0; i < 1; i++)
			{
				
				patientDocDetailVO.setPatCrNo(docUploadVos.getPatCrNo());
				patientDocDetailVO.setEpisodeCode(docUploadVos.getEpisodeCode());
				patientDocDetailVO.setDocData(docUploadVos.getDocData());
				patientDocDetailVO.setSlNo(docUploadVos.getSlNo());
			
			
			docFile = docUploadVos.getDocData();
			
			
			boolean flag =true;
			if(flag)
			{
				patientDocDetailVO.setPathForWindows(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
			}
			else
			{
				patientDocDetailVO.setPathForLinux(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
			}
			
			patientDocDetailVO.setIsValid(Config.IS_VALID_ACTIVE);
			}
		
			docUploadDaoi.saveImageToPostgres(patientDocDetailVO,docFile,userVO);
			//docUploadDaoi.setUploadedStatus(mrdDocumentUploadVO,docFile,userVO);
			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			

		}
		catch (HisApplicationExecutionException e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
	}

	
	// Added by Dheeraj on 17-Oct-2018 to fetch Prescription from Postgres
	public static byte[] fetchPrescriptionFromPostgres(UniPagePrescriptionFB fb) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		byte[] getDoc =null;
		try
		{
			objHisDAO = new HisDAO("OPD","PatientBO");
			UniPagePrescriptionDAO uniPagePrescriptionDAO = new UniPagePrescriptionDAO(tx);
			tx.begin();
			
			 getDoc= uniPagePrescriptionDAO.latestPrescriptionImagePostgres(fb);
			

		}
		catch (HisApplicationExecutionException e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			if (objHisDAO != null) {
				objHisDAO.free();
				
			}
			objHisDAO = null;
		
			tx.close();
		}
	return getDoc;
	}
	

	public static byte[] fetchImageFromPostgres(PatientClinicalDocDetailVO patDocDetailVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		byte[] getDoc =null;
		try
		{
			objHisDAO = new HisDAO("OPD","PatientBO");
			UniPagePrescriptionDAO uniPagePrescriptionDAO = new UniPagePrescriptionDAO(tx);
			tx.begin();
			
			 getDoc= uniPagePrescriptionDAO.latestFetchImagePostgres(patDocDetailVO);
			

		}
		catch (HisApplicationExecutionException e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			
				if (objHisDAO != null) {
					objHisDAO.free();
					
				}
				objHisDAO = null;
			
			tx.close();
		}
	return getDoc;
	}
	

	
	
	//Added by Dheeraj on 17-Oct-2018
		public static List<PatientClinicalDocDetailVO> getPrecsriptionDtl(PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO)
		{
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			List<PatientClinicalDocDetailVO> previousPrescriptions=new ArrayList<PatientClinicalDocDetailVO>();
			try
			{
				tx.begin();
				UniPagePrescriptionDAO uniPageDAO=new UniPagePrescriptionDAO(tx);
				previousPrescriptions = uniPageDAO.getPrescriptionDtl(clinicalDocVO, userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				e.printStackTrace();
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

				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return previousPrescriptions;
		}
		


		public static List patientDocDetailVO(String crNo, UserVO userVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			List patientImageDtlVOList;
			try
			{
				tx.begin();
				UniPagePrescriptionDAO patImageDAO=new UniPagePrescriptionDAO(tx);
				patientImageDtlVOList=patImageDAO.getPatientPrescriptions(crNo, userVO);
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
			return patientImageDtlVOList;
		}
		
	
	
	
	
	// uninherited method was generating error
	@Override
	public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO,
			UserVO _userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//Added by Vasu on 13.Nov.2018
	public static byte[] fetchPDFFromPostgres(EHR_PatientProfileDtlVO profileDtlVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HisDAO objHisDAO = null;
		byte[] getDoc =null;
		try
		{
			objHisDAO = new HisDAO("OPD","PatientBO");
			UniPagePrescriptionDAO uniPagePrescriptionDAO = new UniPagePrescriptionDAO(tx);
			tx.begin();
			
			 getDoc= uniPagePrescriptionDAO.getLatestPDFFromPostgres(profileDtlVO);
			

		}
		catch (HisApplicationExecutionException e)
		{
			
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			
				if (objHisDAO != null) {
					objHisDAO.free();
					
				}
				objHisDAO = null;
			
			tx.close();
		}
	return getDoc;
	}
	
	
}
	