package mrd.transaction.bo;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.noSqlDB.mongodb.MongoXmlHandler;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.AdmissionAdviceVO;
import hisglobal.vo.AssignmentChangeDtlVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CaseSheetLostFoundVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.EprTabAccessDtlVO;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.MrdRecordMovementVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAdmissionDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatFamilyDocDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDtlVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.ServiceAreaPatientVO;
import hisglobal.vo.Service_Req_dtlVO;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.VisitReasonsVO;
import inpatient.InpatientConfig;
import inpatient.transaction.dao.ANCDtlDAO;
import inpatient.transaction.dao.ANCDtlDAOi;
import inpatient.transaction.dao.DoctorRoundDtlDAO;
import inpatient.transaction.dao.DoctorRoundDtlDAOi;
import inpatient.transaction.dao.InPatientEssentialDAO;
import inpatient.transaction.dao.InPatientEssentialDAOi;
import inpatient.transaction.dao.PatIntakeOutDtlDAO;
import inpatient.transaction.dao.PatIntakeOutDtlDAOi;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ehr.EHRConfig;
import mrd.MrdConfig;
import mrd.masters.dao.EprTabAccessDtlDAO;
import mrd.masters.dao.EprTabAccessDtlDAOi;
import mrd.masters.dao.EprTabMasterDAO;
import mrd.masters.dao.EprTabMasterDAOi;
import mrd.transaction.controller.data.EmrCommonDeskDATA;
import mrd.transaction.controller.utl.EmrCommonDeskUTL;
import mrd.transaction.dao.AssignmentChangeDtlDAO;
import mrd.transaction.dao.AssignmentChangeDtlDAOi;
import mrd.transaction.dao.BirthDeathUploadDAO;
import mrd.transaction.dao.BirthDeathUploadDAOi;
import mrd.transaction.dao.CaseSheetDtlDAO;
import mrd.transaction.dao.CaseSheetDtlDAOi;
import mrd.transaction.dao.CaseSheetLostFoundDAO;
import mrd.transaction.dao.CaseSheetLostFoundDAOi;
import mrd.transaction.dao.CertificateBEntryFormDAO;
import mrd.transaction.dao.CertificateIssueDtlDAO;
import mrd.transaction.dao.CertificateIssueDtlDAOi;
import mrd.transaction.dao.CrNoMergeDAO;
import mrd.transaction.dao.CrNoMergeDAOi;
import mrd.transaction.dao.FitnessCertificateDAO;
import mrd.transaction.dao.FitnessCertificateDAOi;
import mrd.transaction.dao.InsuranceDetailDAO;
import mrd.transaction.dao.InsuranceDetailDAOi;
import mrd.transaction.dao.MRDDocumentUploadDAO;
import mrd.transaction.dao.MRDRecordDtlDAO;
import mrd.transaction.dao.MRDRecordDtlDAOi;
import mrd.transaction.dao.MedicalCertificateDAO;
import mrd.transaction.dao.MedicalCertificateDAOi;
import mrd.transaction.dao.MedicalCertificateRequestDAO;
import mrd.transaction.dao.MrdDAO;
import mrd.transaction.dao.MrdDAOi;
import mrd.transaction.dao.MrdEnquiryDAO;
import mrd.transaction.dao.MrdEnquiryDAOi;
import mrd.transaction.dao.MrdEssentialDAO;
import mrd.transaction.dao.MrdEssentialDAOi;
import mrd.transaction.dao.MrdRackShelfChangeDtlDAO;
import mrd.transaction.dao.MrdRackShelfChangeDtlDAOi;
import mrd.transaction.dao.MrdRecordLostFoundDAO;
import mrd.transaction.dao.MrdRecordLostFoundDAOi;
import mrd.transaction.dao.MrdRecordMovementDAO;
import mrd.transaction.dao.MrdRecordMovementDAOi;
import mrd.transaction.dao.MrdRecordRequestDtlDAO;
import mrd.transaction.dao.MrdRecordRequestDtlDAOi;
import mrd.transaction.dao.MrdRecordReturnDtlDAO;
import mrd.transaction.dao.MrdRecordReturnDtlDAOi;
import mrd.transaction.dao.PatFamilyDocDtlDAO;
import mrd.transaction.dao.PatFamilyDocDtlDAOi;
import mrd.transaction.dao.RecordDispatchDtlDAO;
import mrd.transaction.dao.RecordDispatchDtlDAOi;
import mrd.transaction.dao.RecordEnclosureDtlDAO;
import mrd.transaction.dao.RecordEnclosureDtlDAOi;
import mrd.transaction.dao.RecordLostFoundDtlDAO;
import mrd.transaction.dao.RecordLostFoundDtlDAOi;
import mrd.transaction.dao.RecordTypeWiseChecklistDAO;
import mrd.transaction.dao.RecordTypeWiseChecklistDAOi;
import mrd.transaction.dao.RecordTypeWiseEnclosureDAO;
import mrd.transaction.dao.RecordTypeWiseEnclosureDAOi;
import mrd.transaction.dao.RequestRecordDtlDAO;
import mrd.transaction.dao.RequestRecordDtlDAOi;
import mrd.transaction.dao.SummonDetailDAO;
import mrd.transaction.dao.SummonDetailDAOi;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CertificateBEntryFormReqVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import mrd.vo.MRDDocumentUploadVO;
import opd.OpdConfig;
import opd.dao.DocumentUploadDtlDAO;
import opd.dao.DocumentUploadDtlDAOi;
import opd.dao.EpisodeAllergiesDAO;
import opd.dao.EpisodeExtInvDtlDAO;
import opd.dao.EpisodeExtInvDtlDAOi;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.OpdPatientImageDtlDAO;
import opd.dao.PatAlertsDetailDAO;
import opd.dao.PatAlertsDetailDAOi;
import opd.dao.PatDrugTreatmentDetailDAO;
import opd.dao.PatDrugTreatmentDetailDAOi;
import opd.dao.PatientClinicalDetailDAO;
import opd.dao.PatientClinicalDetailDAOi;
import opd.dao.PatientProfileDetailDAO;
import opd.dao.PatientProfileDetailDAOi;
import registration.RegistrationConfig;
import registration.dao.AddressDAO;
import registration.dao.EpisodeDAO;
import registration.dao.EpisodeDiagnosisDAO;
import registration.dao.EpisodeDiagnosisDAOi;
import registration.dao.EpisodeRefDtlDAO;
import registration.dao.EpisodeRefDtlDAOi;
import registration.dao.EpisodeSummaryDAO;
import registration.dao.EpisodeSummaryDAOi;
import registration.dao.PatientDAO;
import registration.dao.PatientImageDtlDAO;

public class MrdBO implements MrdBOi
{
	
	//  Getting The List of All The Episodes of the Patient
	public EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] arrEpisodeVO = null;
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			arrEpisodeVO=mcDAO.getAllEpisodeOfThePatient(crNo,userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return arrEpisodeVO;
	}
	
	//  Getting the List of Admission Advice Given to the Patient on a Particular Episode 
	public AdmissionAdviceVO[] getAdmissionAdvice(String crNo,String epiCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AdmissionAdviceVO[] arrAdmAdviceVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			arrAdmAdviceVO=mrdDAO.getAdmissionAdvice(crNo,epiCode,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrAdmAdviceVO;
	}
	
	// Getting the List of Rest Advice Given to the Patient on a Particular Episode 
	public EpisodeRestAdviceVO[] getEpisodeRestAdvice(String crNo,String epiCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeRestAdviceVO[] arrEpiRestAdviceVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			arrEpiRestAdviceVO=mrdDAO.getEpisodeRestAdvice(crNo,epiCode,userVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrEpiRestAdviceVO;
	}
	
	//  Saving the Medical Certificate Details 
	public void saveOnBasisRestAdvice(PatMedicalDtlVO patMedicalDtlVO,String restFlag,String unitCode,String genMode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO[] patMedDtlVO = null;
		String id="";
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			patMedDtlVO=mcDAO.checkExistingRecord(patMedicalDtlVO,userVO); // Checking for Already Generated Medical Certificate
			if(patMedDtlVO.length==0)
			{
				id=mcDAO.generateCertificateId(patMedicalDtlVO, unitCode, genMode,userVO);
				patMedicalDtlVO.setMedicalCertificateId(id);
				if(genMode.equals(Config.MEDICAL_CERTIFICATE_GENERATION_AUTOMATIC))
					patMedicalDtlVO.setMedicalCertificateDesc(id);
				
				if(restFlag.equals("2"))
					mrdEssDAO.updateCertificateStatus(patMedicalDtlVO,userVO);
				
				mcDAO.create(patMedicalDtlVO, userVO);
				
			}	
			else
			{
				String str="";
				str+="<br>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Medical Certificate Already Generated within The Duration"+"</b></font>";
				str+="<table width='100%'><tr><td width='40%' align='center'></td>";
				str+="<td width='10%' align='center'></td>";
				str+="<td width='15%' align='center'></td>";
				str+="<td width='15%' align='center'></td>";
				str+="<td width='20%' align='center'></td>";
				str+="</tr>";
				str+="<tr><td width='40%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> <b>"+"Suffering From"+"</b></font></td>";
				str+="<td width='10%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Advice Days"+"</b></font></td>";
				str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Start Day"+"</b></font></td>";
				str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"End Day"+"</b></font></td>";
				str+="<td width='20%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Unit Name"+"</b></font></td>";
				str+="</tr>";
				
				for(int i=0;i<patMedDtlVO.length;i++)
				{
					str+="<tr><td width='40%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getSufferingFrom()+"</font></td>";
					str+="<td width='10%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getAdviceDays()+"</font></td>";
					str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getFromDate()+"</font></td>";
					str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getToDate()+"</font></td>";
					str+="<td width='20%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getUnitName()+"</font></td></tr>";
				}
				str+="</table>";
				throw new HisDataAccessException(str);
			}	
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
			throw new HisDataAccessException(e.getMessage());
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
	
	// Getting the List of All Visits of the patient on a Particular Episode
	public EpisodeVO[] getAllVisitOfEpisodePat(String crNo,String epiCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] arrVisitEpiVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			arrVisitEpiVO=mrdDAO.getAllVisitOfEpisodePat(crNo,epiCode,userVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrVisitEpiVO;
	}
	
	// Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	public PatMedicalDtlVO[] getIssuedMedicalCertificate(String crNo,String epiCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO[] arrIssuedMCVO = null;
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			arrIssuedMCVO=mcDAO.getIssuedMedicalCertificate(crNo,epiCode,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrIssuedMCVO;
	}
	
	//  Saving the Fitness Date of The Patient
	public void saveFitnessDate(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count="";
		String id="";
		
		try
		{
			tx.begin();
			FitnessCertificateDAOi fcDAO=new FitnessCertificateDAO(tx); 
			count=fcDAO.checkEsistingFitnessDate(patFitnessDtlVO,userVO);
			if(count.equals("0"))
			{
				id=fcDAO.generateFitnessCertificateId(patFitnessDtlVO, unitCode, genMode, userVO);
				if(genMode.equals(Config.MEDICAL_CERTIFICATE_GENERATION_AUTOMATIC))
					patFitnessDtlVO.setFitnessCertificateDesc(id);
				fcDAO.create(patFitnessDtlVO, unitCode, genMode, userVO, id);
			}
			else
				throw new HisDataAccessException("Fitness Date Exist For The Patient");
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
			throw new HisDataAccessException(e.getMessage());
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
	
	// Saving the Issue Certificate Detail
	public void saveCertificateIssueDtl(RecordDispatchDtlVO dispatchVO, CertificateIssueDtlVO certificateIssueDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String dispatchId="";
		
		try
		{
			tx.begin();
			CertificateIssueDtlDAOi ciDAO=new CertificateIssueDtlDAO(tx);
			RecordDispatchDtlDAOi dispatchDAO=new RecordDispatchDtlDAO(tx);
			String recordType=dispatchVO.getRecordType();
			dispatchId=dispatchDAO.generateDispatchId(userVO,recordType);
			dispatchVO.setDispatchId(dispatchId);
			
			dispatchDAO.create(dispatchVO, userVO);
			ciDAO.create(certificateIssueDtlVO,userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
	
	// Saving the Change Duration Information of the Medical Certificate
	public void saveExtendMedicalCertificate(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO[] patMedDtlVO = null;
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			patMedDtlVO=mcDAO.checkExistingRecordForExtend(patMedicalDtlVO,userVO); // Checking for Already Generated Medical Certificate
			if(patMedDtlVO.length==0)
			{
				mcDAO.update(patMedicalDtlVO,userVO);		//Updating The Previous Record
				mcDAO.create(patMedicalDtlVO, userVO);		//Inserting a New Record
			}
			else
			{
				String str="";
				str+="<br>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Medical Certificate Already Generated within The Duration"+"</b></font>";
				str+="<table width='100%'><tr><td width='40%' align='center'></td>";
				str+="<td width='10%' align='center'></td>";
				str+="<td width='15%' align='center'></td>";
				str+="<td width='15%' align='center'></td>";
				str+="<td width='20%' align='center'></td>";
				str+="</tr>";
				str+="<tr><td width='40%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> <b>"+"Suffering From"+"</b></font></td>";
				str+="<td width='10%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Advice Days"+"</b></font></td>";
				str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Start Day"+"</b></font></td>";
				str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"End Day"+"</b></font></td>";
				str+="<td width='20%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Unit Name"+"</b></font></td>";
				str+="</tr>";
				
				for(int i=0;i<patMedDtlVO.length;i++)
				{
					str+="<tr><td width='40%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getSufferingFrom()+"</font></td>";
					str+="<td width='10%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getAdviceDays()+"</font></td>";
					str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getFromDate()+"</font></td>";
					str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getToDate()+"</font></td>";
					str+="<td width='20%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getUnitName()+"</font></td></tr>";
				}
				str+="</table>";
				throw new HisDataAccessException(str);
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	// Getting The List of Certificate For Movement By Unit 
	public CertificateIssueDtlVO[] getAllCertificateForMoveBydUnit(String unitCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CertificateIssueDtlVO[] arrCertificateMoveVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			arrCertificateMoveVO=mrdDAO.getAllCertificateForMoveBydUnit(unitCode,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrCertificateMoveVO;
	}
	
	//  Saving Certificate Movement Detail
	public void saveCertificateMovement(List<RecordCheckListDtlVO> lstRecordCheckList,RecordDispatchDtlVO[] recordMoveDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String recordStatus="";
		
		try
		{
			tx.begin();
			RecordDispatchDtlDAOi recordMovDAO=new RecordDispatchDtlDAO(tx);
			CertificateIssueDtlDAOi certificateIssueDAO=new CertificateIssueDtlDAO(tx);
			RecordTypeWiseChecklistDAOi recordCheckListDAO=new RecordTypeWiseChecklistDAO(tx);
			for(int i=0;i<recordMoveDtlVO.length;i++)
			{
				recordStatus=MrdConfig.CERTIFICATE_RECORD_STATUS_RECEIVED_BY_MRD_CLERK;
				certificateIssueDAO.updateCertificateRecordStatus(recordStatus,recordMoveDtlVO[i].getRecordId(),recordMoveDtlVO[i].getSlNo(),recordMoveDtlVO[i].getRecordType(),userVO);	//Updating the Record Status of Certificate
		//		recordMovDAO.create(recordMoveDtlVO[i],userVO);		//Inserting Data
				recordMovDAO.updateRecipentDtl(recordMoveDtlVO[i], userVO);
			}
			
			for(int i=0;i<lstRecordCheckList.size();i++)
			{
				recordCheckListDAO.create(lstRecordCheckList.get(i), userVO);
			}
			
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
			throw new HisDataAccessException(e.getMessage());
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
	
	// Getting The List of Certificate For Movement By Cr No 
	public CertificateIssueDtlVO[] getAllCertificateForMoveByCrNo(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CertificateIssueDtlVO[] arrCertificateMoveVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			arrCertificateMoveVO=mrdDAO.getAllCertificateForMoveByCrNo(crNo,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrCertificateMoveVO;
	}
	
	/* not in use
	//  Saving the Certificate Received Details
	public void saveReceiveCertificate(String certificateRcvMode,MrdRecordDtlVO[] mrdRecordDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO patMedDtlVO=new PatMedicalDtlVO();
		PatFitnessDtlVO patFitDtlVO=new PatFitnessDtlVO();
		RecordDispatchDtlVO recordMoveDtlVO=new RecordDispatchDtlVO();
		String recordStatus="";
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDtlDAO=new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			CertificateIssueDtlDAOi certificateIssueDAO=new CertificateIssueDtlDAO(tx);
			RecordDispatchDtlDAOi recordMovDtlDAO=new RecordDispatchDtlDAO(tx);
			
			
			for(int i=0;i<mrdRecordDtlVO.length;i++)
			{
				if(mrdRecordDtlVO[i].getRecordType().equals(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE))
				{
					patMedDtlVO=mrdEssDAO.getPUKForMedicalCert(mrdRecordDtlVO[i], userVO);	// Getting Patient Information For Medical Certificate
					mrdRecordDtlVO[i].setPatCrNo(patMedDtlVO.getPatCrNo());
					mrdRecordDtlVO[i].setPatAdmNo(patMedDtlVO.getPatAdmNo());
					mrdRecordDtlVO[i].setEpisodeCode(patMedDtlVO.getEpisodeCode());
					mrdRecordDtlVO[i].setEpisodeVisitNo(patMedDtlVO.getEpisodeVisitNo());
				}	
				else
				{
					patFitDtlVO=mrdEssDAO.getPUKForFitnessCert(mrdRecordDtlVO[i], userVO);	// Getting Patient Information For Fitness Certificate
					mrdRecordDtlVO[i].setPatCrNo(patFitDtlVO.getPatCrNo());
					mrdRecordDtlVO[i].setPatAdmNo(patFitDtlVO.getPatAdmNo());
					mrdRecordDtlVO[i].setEpisodeCode(patFitDtlVO.getEpisodeCode());
					mrdRecordDtlVO[i].setEpisodeVisitNo(patFitDtlVO.getEpisodeVisitNo());
				}
			
				recordStatus=MrdConfig.CERTIFICATE_RECORD_STATUS_RECEIVED;
				
				if(certificateRcvMode.equals(Config.CERTIFICATE_RECEIVED_OFFLINE))	// For OFFLINE Certificate Received
				{
					HelperMethods.populate(recordMoveDtlVO, mrdRecordDtlVO[i]);
					recordMoveDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_MOVEMENT_RECORD_STATUS_RECEIVED);
					recordMovDtlDAO.insertOfflineRecord(recordMoveDtlVO, userVO);		//Inserting Data in Movement Table
				}
				if(certificateRcvMode.equals(Config.CERTIFICATE_RECEIVED_ONLINE))	// For ONLINE Certificate Received
				{
					String recMovStatus=MrdConfig.CERTIFICATE_MOVEMENT_RECORD_STATUS_RECEIVED;
					String receiveFrom=mrdRecordDtlVO[i].getReceiveFrom();
					// Updating The Receive From & Record Status of The Certificate  
					recordMovDtlDAO.updateCertificateRecordStatus(receiveFrom,recMovStatus, mrdRecordDtlVO[i].getRecordId(), mrdRecordDtlVO[i].getSlNo(),mrdRecordDtlVO[i].getRecordType(), userVO);
				}
			
				// Updating The Record Status of The Certificate 
				certificateIssueDAO.updateCertificateRecordStatus(recordStatus, mrdRecordDtlVO[i].getRecordId(), mrdRecordDtlVO[i].getSlNo(),mrdRecordDtlVO[i].getRecordType(), userVO);
				mrdRecordDtlDAO.create(mrdRecordDtlVO[i], userVO);		//Inserting The Data into MRD Record Table
				
			}
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
	}
	
	*/
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] arrEpisodeVO = null;
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			arrEpisodeVO=mcDAO.getAllEpisodeOfThePatientTodayVisited(crNo,userVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrEpisodeVO;
	}
	
	public EpisodeRestAdviceVO[] getEpisodeRestAdviceTodayVisited(String crNo,String epiCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeRestAdviceVO[] arrEpiRestAdviceVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			arrEpiRestAdviceVO=mrdDAO.getEpisodeRestAdviceTodayVisited(crNo,epiCode,userVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrEpiRestAdviceVO;
	}
	
	public void saveFitnessDateNExtendMC(PatMedicalDtlVO patMedicalDtlVO,PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatMedicalDtlVO[] patMedDtlVO = null;
		String id="";
		
		try
		{
			tx.begin();
			MedicalCertificateDAOi mcDAO=new MedicalCertificateDAO(tx);
			FitnessCertificateDAOi fcDAO=new FitnessCertificateDAO(tx);
			
			patMedDtlVO=mcDAO.checkExistingRecordForExtend(patMedicalDtlVO,userVO); // Checking for Already Generated Medical Certificate
			if(patMedDtlVO.length==0)
			{
				mcDAO.update(patMedicalDtlVO,userVO);		//Updating The Previous Record
				mcDAO.create(patMedicalDtlVO, userVO);		//Inserting a New Record
				
				id=fcDAO.generateFitnessCertificateId(patFitnessDtlVO, unitCode, genMode, userVO);
				if(genMode.equals(Config.MEDICAL_CERTIFICATE_GENERATION_AUTOMATIC))
					patFitnessDtlVO.setFitnessCertificateDesc(id);
				fcDAO.create(patFitnessDtlVO, unitCode, genMode, userVO, id);
			}
			else
			{
				String str="";
				str+="<br>"+"<font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Medical Certificate Already Generated within The Duration"+"</b></font>";
				str+="<table width='100%'><tr><td width='40%' align='center'></td>";
				str+="<td width='10%' align='center'></td>";
				str+="<td width='15%' align='center'></td>";
				str+="<td width='15%' align='center'></td>";
				str+="<td width='20%' align='center'></td>";
				str+="</tr>";
				str+="<tr><td width='40%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> <b>"+"Suffering From"+"</b></font></td>";
				str+="<td width='10%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Advice Days"+"</b></font></td>";
				str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Start Day"+"</b></font></td>";
				str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"End Day"+"</b></font></td>";
				str+="<td width='20%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b> "+"Unit Name"+"</b></font></td>";
				str+="</tr>";
				
				for(int i=0;i<patMedDtlVO.length;i++)
				{
					str+="<tr><td width='40%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getSufferingFrom()+"</font></td>";
					str+="<td width='10%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getAdviceDays()+"</font></td>";
					str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getFromDate()+"</font></td>";
					str+="<td width='15%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getToDate()+"</font></td>";
					str+="<td width='20%' align='center'><font color='#FF0000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> "+patMedDtlVO[i].getUnitName()+"</font></td></tr>";
				}
				str+="</table>";
				throw new HisDataAccessException(str);
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public PatFamilyDocDtlVO[] getExistingFamilyDoctorRecord(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatFamilyDocDtlVO[] arrExistPatFamilyDocVO = null;
		
		try
		{
			tx.begin();
			PatFamilyDocDtlDAOi dao=new PatFamilyDocDtlDAO(tx);
			arrExistPatFamilyDocVO=dao.getExistingFamilyDoctorRecord(crNo,userVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return arrExistPatFamilyDocVO;
	}
	
	public void savePatientFamilyDoctorDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PatFamilyDocDtlDAOi dao=new PatFamilyDocDtlDAO(tx);
			dao.savePatientFamilyDoctorDetail(patFamilyDocVO,userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public PatFamilyDocDtlVO getPatientFamilyDocDetail(String chk,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatFamilyDocDtlVO patFamilyDocVO = null;
		
		try
		{
			tx.begin();
			PatFamilyDocDtlDAOi dao=new PatFamilyDocDtlDAO(tx);
			patFamilyDocVO=dao.getPatientFamilyDocDetail(chk,userVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return patFamilyDocVO;
	}
	
	public void modifyPatientFamilyDocDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PatFamilyDocDtlDAOi dao=new PatFamilyDocDtlDAO(tx);
			dao.modifyPatientFamilyDocDetail(patFamilyDocVO,userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void revokePatientFamilyDocDetail(String crNo,String hCode,String slNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			PatFamilyDocDtlDAOi dao=new PatFamilyDocDtlDAO(tx);
			dao.revokePatientFamilyDocDetail(crNo,hCode,slNo,userVO);
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
			throw new HisDataAccessException(e.getMessage());
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

	
	// Getting The List of Patient for case sheet dispatch By Unit 
	public List getCaseSheetListToReady(String unitCode,String wardCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List caseSheetDtlVOList=null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			caseSheetDtlVOList=mrdDAO.getCaseSheetListToReady(unitCode,wardCode,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return caseSheetDtlVOList;
	}
	
	//added by swati sagar on date :01-may-2019
	
	public List getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List caseSheetDtlVOList=null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			caseSheetDtlVOList=mrdDAO.getCaseSheetListToReadyADMNOWISE(admno,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return caseSheetDtlVOList;
	}
	
	//added by swati sagar
	//date:02-may-2019
	public List getCaseSheetListToReadyCRNOWISE(String crno,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List caseSheetDtlVOList=null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			caseSheetDtlVOList=mrdDAO.getCaseSheetListToReadyCRNOWISE(crno,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return caseSheetDtlVOList;
	}
	
	public void saveCaseSheetDispatch(RecordDispatchDtlVO[] casesheetDispatchVOArray,CaseSheetDtlVO[] caseSheetDtlVOArray,Map enclosureMap,List checklistMstVOList,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeWiseEnclosureMstVO enclosureMstVO=null;	
		RecordCheckListDtlVO checklistVO=null;	
		try
		{
			tx.begin();
			RecordDispatchDtlDAOi dispatchDetailDAO=new RecordDispatchDtlDAO(tx);
			RecordTypeWiseEnclosureDAOi enclosureDAO=new RecordTypeWiseEnclosureDAO(tx);
			RecordTypeWiseChecklistDAOi checklistDAO=new RecordTypeWiseChecklistDAO(tx);
			CaseSheetDtlDAOi caseSheetDtlDao=new CaseSheetDtlDAO(tx);
			
			for(int i=0;i<casesheetDispatchVOArray.length;i++){
				String dispatchId=dispatchDetailDAO.generateDispatchId(userVO, casesheetDispatchVOArray[i].getRecordType());
				casesheetDispatchVOArray[i].setDispatchId(dispatchId);
				dispatchDetailDAO.create(casesheetDispatchVOArray[i], userVO);
				List list=(List)enclosureMap.get(casesheetDispatchVOArray[i].getRecordId());
				List enclosureList=(List)list.get(0);
				List checklistVOList=(List)list.get(1);
				for(int j=0;j<enclosureList.size();j++){
					enclosureMstVO=(RecordTypeWiseEnclosureMstVO)enclosureList.get(j);
					enclosureMstVO.setDispatchId(dispatchId);
					enclosureDAO.create(enclosureMstVO,userVO);
				}
				if(checklistMstVOList!=null){
					for(int j=0;j<checklistMstVOList.size();j++){
						checklistVO=(RecordCheckListDtlVO)checklistMstVOList.get(j);
						checklistVO.setDispatchId(dispatchId);
						checklistVO.setRecordId(casesheetDispatchVOArray[i].getRecordId());
						checklistDAO.create(checklistVO, userVO);
					}
				}
				else{
					for(int j=0;j<checklistVOList.size();j++){
						checklistVO=(RecordCheckListDtlVO)checklistVOList.get(j);
						checklistVO.setDispatchId(dispatchId);
						//checklistVO.setRecordId(casesheetDispatchVOArray[i].getRecordId());
						checklistDAO.create(checklistVO, userVO);
					}
				}
			}
			
			for(int i=0;i<caseSheetDtlVOArray.length;i++){
				caseSheetDtlDao.update(caseSheetDtlVOArray[i], userVO);
			}	
			
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
		
	}
	
	
	public Map getAllEnclosureDetails(CaseSheetDtlVO caseSheetDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeWiseEnclosureMstVO[] enclosureDtlVO = null;
		RecordTypeCheckListMstVO[] checklistDtlVO = null;
		//List<RecordTypeWiseEnclosureMstVO> enclosurePreviouslyAddedVO = null;
		//List<RecordTypeCheckListMstVO> checklistPreviouslyAdded = null;
		Map essentialMap=new HashMap();
		PatientDetailVO patientDetailVO;
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			//MrdEssentialDAOi mrdessentialDAO=new MrdEssentialDAO(tx);
			//RecordTypeWiseChecklistDAOi checkListDtlDao=new RecordTypeWiseChecklistDAO(tx);
			patientDetailVO=mrdDAO.getDischargePatientDtlByCrNo(caseSheetDtlVO.getPatCrNo(), userVO);
			essentialMap.put(InpatientConfig.INPATIENT_ADMISSION_VO, patientDetailVO);
			if(caseSheetDtlVO.getCaseSheetType().equals(MrdConfig.CASESHEET_TYPE_GENERAL))
				enclosureDtlVO=mrdDAO.getAllEnclosureDetails(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET,userVO);
			else
				enclosureDtlVO=mrdDAO.getAllEnclosureDetails(MrdConfig.RECORD_TYPE_MLC_CASESHEET,userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_ENCLOSURE_LIST, enclosureDtlVO);
			/*if(caseSheetDtlVO.getRecordStatus().equals(MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_RETURN)){
				enclosurePreviouslyAddedVO=mrdessentialDAO.getEnclosureByRecordId(caseSheetDtlVO.getPatAdmNo(), userVO);
				essentialMap.put(MrdConfig.CASE_SHEET_ENCLOSURE_PRE_ADDED_VO_LIST, enclosurePreviouslyAddedVO);
				checklistPreviouslyAdded=checkListDtlDao.selectCheckListDtl(caseSheetDtlVO.getPatAdmNo(), userVO);
				essentialMap.put(MrdConfig.CASE_SHEET_CHECKLIST_PRE_ADDED_VO_LIST, checklistPreviouslyAdded);
				
			}*/
			if(caseSheetDtlVO.getCaseSheetType().equals(MrdConfig.CASESHEET_TYPE_GENERAL))
				checklistDtlVO=mrdDAO.getAllChecklistDetails(MrdConfig.CHECKLIST_MODE_DISPATCH_LEVEL,MrdConfig.RECORD_TYPE_GENERAL_CASESHEET,userVO);
			else
				checklistDtlVO=mrdDAO.getAllChecklistDetails(MrdConfig.CHECKLIST_MODE_DISPATCH_LEVEL,MrdConfig.RECORD_TYPE_MLC_CASESHEET,userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_CHECKLIST, checklistDtlVO);
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public RecordTypeCheckListMstVO[] getAllChecklistDetails(String checklistMode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeCheckListMstVO[] checklistDtlVO = null;
		
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			checklistDtlVO=mrdDAO.getAllChecklistDetails(checklistMode,MrdConfig.RECORD_TYPE_CASESHEET,userVO);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return checklistDtlVO;
	}

	

	
	public void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			CrNoMergeDAOi mergeDAO=new CrNoMergeDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			String str=Config.IS_VALID_DELETED;
			for(int i=0;i<lstNotUsedCRNo.size();i++)
			{
				mergeDAO.saveNotUsedCrNo(lstNotUsedCRNo.get(i),userVO);
				mrdEssDAO.updatePatientValidStatus(str,lstNotUsedCRNo.get(i).getPatNotUsedCrNo(),userVO);
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public List<PatientVO> getMergedCrNo(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lst=null;
		PatientVO patVO=null;
		List<PatientVO> mergedPatVOList=new ArrayList<PatientVO>();
		
		try
		{
			tx.begin();
			CrNoMergeDAOi mergeDAO=new CrNoMergeDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			lst=mergeDAO.getMergedCrNo(crNo,userVO);
			for(int i=0;i<lst.size();i++)
			{
				Entry obj=(Entry)((List)lst).get(i);
				patVO=mrdEssDAO.getPatDtlByCrNo(obj.getValue(),userVO);
				mergedPatVOList.add(patVO);
			}
			
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mergedPatVOList;
	}
	
	public void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			CrNoMergeDAOi mergeDAO=new CrNoMergeDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			String str=Config.IS_VALID_ACTIVE;
			mergeDAO.revokeMergedCRNo(reason,mainCrNo,crNo,userVO);
			mrdEssDAO.updatePatientValidStatus(str,crNo,userVO); 
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public List getMainCRNumberList(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstMainCRNo=null;
		
		try
		{
			tx.begin();
			CrNoMergeDAOi mergeDAO=new CrNoMergeDAO(tx);
			lstMainCRNo=mergeDAO.getMainCRNumberList(userVO);
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
		return lstMainCRNo;
	}
	
	
	public Map getEpisodesEMR(PatientVO patientVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeVO[] episodeVOs=null;
		PatientDetailVO[] patientDetailVOs=null;
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			EpisodeDAO episodeDAO=new EpisodeDAO(tx);
			episodeVOs=episodeDAO.retrieveAllOpenEpisodesEMR(patientVO.getPatCrNo(), userVO);
			essentialMap.put(MrdConfig.EPISODE_TREE_ARRAY, episodeVOs);
			InPatientEssentialDAOi inPatientEssentialDAOi=new InPatientEssentialDAO(tx);
			patientDetailVOs=inPatientEssentialDAOi.getPatientAdmissionDetailsEMR(patientVO.getPatCrNo(),userVO);
			essentialMap.put(MrdConfig.ADMISSION_TREE_ARRAY,patientDetailVOs);
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
		return essentialMap;
	}
	
	public Map getPersonalProfile(PatientVO patientVO,String departmentUnitCode,String tabType,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		AddressVO[] addressVOs=null;
		PatientImageDtlVO[] patientImageDtlVOs=null;
		//EpisodeVO[] episodeVOs=null;
		//PatFamilyDocDtlVO[] patFamilyDocDtlVOs=null;
		//EpisodeDiagnosisVO[] episodeDiagnosisVOs=null;
		//PatDrugTreatmentDetailVO[] treatmentVO=null;
		//PatientAlertsDetailVO[] alertVOs=null;
		//EpisodeAllergiesVO [] patAllergiesVOs=null;
		List eprTabList=null;
		Map map=new HashMap();
		String policyType="";
		try
		{
			tx.begin();
			
			////////////////////////////////////Tab Detail/////////////////////////////////////////
			EprTabMasterDAOi eprTabMstDAO=new EprTabMasterDAO(tx);
			eprTabList=eprTabMstDAO.selectTab(tabType,userVO);
			////////////////////////////////////////////////////////////////////////////
			
			/////////////////////////////////Check for emr Restricted Category//////////////////////
			MrdEssentialDAOi mrdEssentialDAO= new MrdEssentialDAO(tx);
			int count=mrdEssentialDAO.getEmrRestrictedCategory(patientVO.getPatPrimaryCatCode(), userVO);
			if(count>0){
				policyType=MrdConfig.EPR_PATIENT_CATEGORY_RESTRICTED;
			}
			else
				policyType=MrdConfig.EPR_PATIENT_CATEGORY_NORMAL;
			////////////////////////////////////////////////////////////////////////////
			
			/////////////////////////////////get Access policy for tabs///////////////////////
			EprTabAccessDtlDAOi tabAccessDAO=new EprTabAccessDtlDAO(tx);
			EprTabAccessDtlVO eprTabAccessDtlVO=new EprTabAccessDtlVO();
			eprTabAccessDtlVO.setPolicyType(policyType);
			eprTabAccessDtlVO.setDepartmentUnitCode(departmentUnitCode);
			List<EprTabAccessDtlVO> tabAccessDtlVOList=tabAccessDAO.selectTabAccessDetail(eprTabAccessDtlVO, userVO);
			
			////////////////////////////////////////////////////////////////////////////
			
			//////////////////////////Address Details//////////////////////////////////////
			try{
				AddressDAO addressDAO=new AddressDAO(tx);
				addressVOs=addressDAO.retrieveByCrNoAll(patientVO.getPatCrNo(), userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				//tx.rollback();
				e.printStackTrace();
			}
			///////////////////////////////////////////////////////////////////
			
			//////////////////////Image Details///////////////////////////////////
			PatientImageDtlDAO patientImageDtlDAO=new PatientImageDtlDAO(tx);
			patientImageDtlVOs=patientImageDtlDAO.retrivePayientImageByCrNo(patientVO.getPatCrNo(), userVO);
			
			/////////////////////////////////////////////////////////////////////////
			
			//////////////////////Patient Mlc Detail/////////////////////////////////
			MlcVO mlcVO = null;
			EpisodeDAO episodeDao = new EpisodeDAO(tx);
			try{
				String mlcNumber = episodeDao.isPatientMLC(patientVO, userVO);
				if (mlcNumber != null || !(mlcNumber.equals("")))
				{
					mlcVO = new MlcVO();
					mlcVO.setPatCrNo(patientVO.getPatCrNo());
					mlcVO.setMlcNo(mlcNumber);
				}
			}
			catch (HisRecordNotFoundException e)
			{
				//tx.rollback();
				e.printStackTrace();
			}
			/////////////////////////////////////////////////////////////////////////
			
			
			/* *** currently these information are not shown ************** 
			////////////////////////Next Visit Details/////////////////////
			EpisodeDAO episodeDAO=new EpisodeDAO(tx);
			episodeVOs=episodeDAO.retrieveNextVsitDetails(patientVO.getPatCrNo(),userVO);
			///////////////////////////////////////////////////////////////////
			
			
			//////////////////////Patient Diagnosis Details///////////////
			EpisodeDiagnosisDAOi episodeDiagnosisDAOi=new EpisodeDiagnosisDAO(tx);
			episodeDiagnosisVOs=episodeDiagnosisDAOi.retrieveCurrentEpisodeDiagnosisForEMR(patientVO.getPatCrNo(), userVO);
			////////////////////////////////////////////////////////////////////
			
			//////////////////////Patient treatment Details///////////////
			PatDrugTreatmentDetailDAOi drugTreatementDAOi=new PatDrugTreatmentDetailDAO(tx);
			treatmentVO=drugTreatementDAOi.getTreatmentAdviceDetailsEMR(patientVO.getPatCrNo(),null,MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, userVO);
			////////////////////////////////////////////////////////////////////
			
			//////////////////////Patient chronic disease Details///////////////
			PatAlertsDetailDAOi  patAlertDAOi=new PatAlertsDetailDAO(tx);
			alertVOs=patAlertDAOi.getPatientAlertEMR(patientVO.getPatCrNo(),null,MrdConfig.EPR_DISPLAY_ALL_RECORD_YES,userVO);
			////////////////////////////////////////////////////////////////////
			
			//////////////////////Patient Allergies Details///////////////
			try{
				EpisodeAllergiesDAO  patAllergiesDAO=new EpisodeAllergiesDAO(tx);
				EpisodeAllergiesVO episodeAllergyVO=new EpisodeAllergiesVO();
				episodeAllergyVO.setPatCrNo(patientVO.getPatCrNo());
				patAllergiesVOs=patAllergiesDAO.getAllergyDtlAll(episodeAllergyVO,null,MrdConfig.EPR_DISPLAY_ALL_RECORD_YES,userVO);
			}
			catch (HisRecordNotFoundException e) {
				System.out.println(e.getMessage());
			}
			////////////////////////////////////////////////////////////////////
			
			////////////////////////Patient Family Doctor Details///////////////
			try
			{
			PatFamilyDocDtlDAOi patFamilyDocDtlDAOi=new PatFamilyDocDtlDAO(tx);
			patFamilyDocDtlVOs=patFamilyDocDtlDAOi.getExistingFamilyDoctorRecord(patientVO.getPatCrNo(), userVO);
			if(patFamilyDocDtlVOs.length==0)
			{
				patFamilyDocDtlVOs=null;
			}
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				e.printStackTrace();
			}
			////////////////////////////////////////////////////////////////////
			****************/
			
			// CR Merge List
			CrNoMergeDAOi recordDAO = new CrNoMergeDAO(tx);
			List<CrNoMergeDtlVO> lstCRMerges = recordDAO.getMergedCrNoForEMR(patientVO.getPatCrNo(), userVO);
			
			
			
			map.put(MrdConfig.EMR_TABS_ARRAY, eprTabList);
			map.put(MrdConfig.EPR_TAB_ACCESS_DTL_VO_LIST, tabAccessDtlVOList); 
			map.put(MrdConfig.EPR_PATIENT_CATEGORY, policyType);
			map.put(MrdConfig.PAT_ADDRESS_DETAILS, addressVOs);
			map.put(MrdConfig.PAT_IMAGE_DETAILS, patientImageDtlVOs);
			map.put(MrdConfig.PAT_MLC_VO, mlcVO);
			map.put(MrdConfig.PATIENT_CR_MERGE_LIST, lstCRMerges);
			
			//map.put(MrdConfig.PAT_EPISODE_NEXT_VISIT_ARRAY, episodeVOs);
			//map.put(MrdConfig.PAT_FAMILY_DOC_DETAILS, patFamilyDocDtlVOs);
			//map.put(MrdConfig.PAT_EPISODE_DIAGNOSIS_VO_ARRAY, episodeDiagnosisVOs);
			//map.put(MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY, treatmentVO); 
			//map.put(MrdConfig.PAT_CHRONIC_DISEASE_ARRAY, alertVOs); 
			//map.put(MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY, patAllergiesVOs); 
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
		return map;
	}

	
	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByCrNoEMR(PatientVO _patientVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			PatientDAO patientDao = new PatientDAO(tx);
		
			_patientVO = patientDao.retrieveByCrNoEMR(_patientVO,_userVO);
			String fname = "(Unknown)" + _patientVO.getPatFirstName();

			if (_patientVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) _patientVO.setPatFirstName(fname);

			// if(_patientVO.getPatAge()!=null &&
			// _patientVO.getIsActualDob().equals(RegistrationConfig.IS_ACTUAL_DOB_FALSE)){
			if (_patientVO.getPatAge() != null)
			{
				String patAgeWithUnit = _patientVO.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				_patientVO.setPatAge(patAgeWithUnit);
				System.out.println("_patientVO.getPatAge()1:::" + _patientVO.getPatAge());

				if (ageunit.equalsIgnoreCase("yr")) _patientVO.setPatAgeUnit("Y");
				if (ageunit.equalsIgnoreCase("mth")) _patientVO.setPatAgeUnit("M");
				if (ageunit.equalsIgnoreCase("d")) _patientVO.setPatAgeUnit("D");
			}
			/*
			 * System.out.println("Patient dob::"+_patientVO.getPatDOB()); System.out.println("Patient
			 * isurban::"+_patientVO.getPatIsUrban()); System.out.println("after populate"); System.out.println("Patient
			 * first name::"+_patientVO.getPatFirstName());
			 * System.out.println("_patientVO.getPatAddCity()"+_patientVO.getPatAddCity());
			 */

		/*	if (_patientVO.getIsImageUploaded() != null && _patientVO.getIsImageUploaded().equalsIgnoreCase("1"))
			{
				_patientVO.setImageFile(HelperMethods.getByteArrayOfImage(Config.PATIENT_IMAGE_FILE_STORAGE_PATH + "\\" + _patientVO.getPatCrNo()));
				_patientVO.setImageFileName(Config.PATIENT_IMAGE_FILE_STORAGE_PATH + _patientVO.getPatCrNo());
			}
*/
			System.out.println(_patientVO.getPatPrimaryCatCode());
			/*if (_patientVO.getPatPrimaryCatCode() == null || _patientVO.getPatPrimaryCatCode().equals("")) throw new HisRecordNotFoundException(
					"Current Patient Category is invalid please change the Patient Category first");

*/
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("inside  BO Record not found exception");
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("inside  BO HisDataAccessException");
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("inside  BO HisApplicationExecutionException");
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return _patientVO;
	}
	
	/**
	 * Retrieves patient allergies details on the basis of CR No from the Episode Allergies Dtl Table. 
	 * @param _episodeAllergiesVO Provides CR No, episodeCode and visit No
	 * @param _userVO Provides User details.
	 * @return EpisodeAllergiesVO[] with values stored in DB.
	 */
	public EpisodeAllergiesVO[] getEpisodeAllergiesVisitWise(EpisodeAllergiesVO _episodeAllergiesVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeAllergiesVO[] episodeAllergiesVOs=null;
		try
		{
			tx.begin();
			EpisodeAllergiesDAO episodeAllergiesDAO=new EpisodeAllergiesDAO(tx);
			episodeAllergiesVOs=episodeAllergiesDAO.getAllergyDtlEpisodeWiseVisitWise(_episodeAllergiesVO, _userVO);
			
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
		return episodeAllergiesVOs;
	}
	
	/**
	 * Retrieves patient chronic diseases for EMR. 
	 * @param _episodeAllergiesVO Provides CR No, episodeCode and visit No
	 * @param _userVO Provides User details.
	 * @return EpisodeAllergiesVO[] with values stored in DB.
	 */
	public PatientAlertsDetailVO[] getPatientAlertsEMR(PatientAlertsDetailVO _patientAlertsDetailVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientAlertsDetailVO[] patientAlertsDetailVOs=null;
		try
		{
			tx.begin();
			PatAlertsDetailDAOi patAlertsDetailDAOi=new PatAlertsDetailDAO(tx);
			patientAlertsDetailVOs=patAlertsDetailDAOi.getPatientAlertEMR(_patientAlertsDetailVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
			
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
		return patientAlertsDetailVOs;
	}
	
	/**
	 * Retrieves patient diagnosis details episode and visit wise for EMR. 
	 * @param _episodeAllergiesVO Provides CR No, episodeCode and visit No
	 * @param _userVO Provides User details.
	 * @return EpisodeDiagnosisVO[] with values stored in DB.
	 */
	public EpisodeDiagnosisVO[] getDiagnosisDetailsEpisodeVisitWiseEMR(EpisodeDiagnosisVO _episodeDiagnosisVO,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeDiagnosisVO[] episodeDiagnosisVOs=null;
		try
		{
			tx.begin();
			EpisodeDiagnosisDAOi episodeDiagnosisDAOi=new EpisodeDiagnosisDAO(tx);
			episodeDiagnosisVOs=episodeDiagnosisDAOi.retrieveEpisodeDiagnosisEpisodeVisitWiseForEMR(_episodeDiagnosisVO, _userVO);
			
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
		return episodeDiagnosisVOs;
	}
	
	/**
	 * Retrieves patient all diagnosis details. 
	 * @param _episodeAllergiesVO Provides CR No
	 * @param _userVO Provides User details.
	 * @return EpisodeDiagnosisVO[] with values stored in DB.
	 */
	public EpisodeDiagnosisVO[] getAllDiagnosisDetails(EpisodeDiagnosisVO _episodeDiagnosisVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeDiagnosisVO[] episodeDiagnosisVOs=null;
		try
		{
			tx.begin();
			EpisodeDiagnosisDAOi episodeDiagnosisDAOi=new EpisodeDiagnosisDAO(tx);
			episodeDiagnosisVOs=episodeDiagnosisDAOi.retrieveEpisodeDiagnosisForEMR(_episodeDiagnosisVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
			
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
		return episodeDiagnosisVOs;
	}
	
	/**
	 * Retrieves patient allergies details on the basis of CR No from the Episode Allergies Dtl Table. 
	 * @param _episodeAllergiesVO Provides CR No
	 * @param _userVO Provides User details.
	 * @return EpisodeAllergiesVO[] with values stored in DB.
	 */
	public EpisodeAllergiesVO[] getEpisodeAllergiesAll(EpisodeAllergiesVO _episodeAllergiesVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeAllergiesVO[] episodeAllergiesVOs=null;
		try
		{
			tx.begin();
			EpisodeAllergiesDAO episodeAllergiesDAO=new EpisodeAllergiesDAO(tx);
			episodeAllergiesVOs=episodeAllergiesDAO.getAllergyDtlAll(_episodeAllergiesVO,departmentUnitArray,accessType, _userVO);
			
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
		return episodeAllergiesVOs;
	}

	public List getPatientImageDtlByCrNo(String crNo, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List patientImageDtlVOList;
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			patientImageDtlVOList=patImageDAO.getPatientImages(crNo, userVO);
			
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
	
	
	
	 
	 public  byte[] latestFetchImagePostgres(String fileNo) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			byte[] patientImageDtlVOList;
			try
			{
				tx.begin();
				PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
				patientImageDtlVOList=patImageDAO.latestFetchImagePostgres(fileNo);
				
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
		

	public void deletePatientImage(PatientImageDtlVO[] patImageVO, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			for(int i=0;i<patImageVO.length;i++)
				patImageDAO.deletePatientImage(patImageVO[i], userVO);
			
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
		//return rowCount;
	}
	
	public void modifyPatientImage(PatientImageDtlVO[] patImageVO, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		HisDAO hisDAO = new HisDAO("MRD", "MrdBO");
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			if(patImageVO!=null){/* Nilesh Gupta 7/11/2017 */
				for(int i=0;i<patImageVO.length;i++)
				patImageDAO.modifyPatientImage(hisDAO, patImageVO[i], userVO);
			}
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
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
			tx.close();
		}
		//return rowCount;
	}
	
	
/**
 * Changed By :- Akash Singh
 * Reason:- change file upload process from ftp to mongoDb 
 * Changed Date :- 05-10-2015
 * @author AHIS*
 */	
	/*public void savePatientImage(PatientImageDtlVO []patImageVO, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		String count;
		HisDAO hisDAO = new HisDAO("MRD", "MrdBO");
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			count=patImageDAO.getContCrNoWise(patImageVO[0].getPatCrNo(), userVO);
			for(int i=0;i<patImageVO.length;i++)
			{
				System.out.println("count is :- "+count);
				int countInt=Integer.parseInt(count);
				countInt=countInt+1;
				String docId="";
				docId=patImageVO[i].getPatCrNo()+"_"+countInt;
				System.out.println("Inside FTP DOC Id is"+docId);
				//FileInputStream fileInFTPStream = new FileInputStream(this.uploadedFile);
				System.out.println("fileno:::"+patImageVO[i].getFileNo());
				//patImageVO[i].setFileNo(docId); 
				patImageVO[i].setDocId(docId); //to save file name as document id as set in PatientImageDtlDAO

//comment by Akash Singh, Dated on 05-10-2015 due to change for ftp to MongoDb
				//File file=new File(patImageVO[i].getFileNo());
				//FileUtils.writeByteArrayToFile(file, patImageVO[i].getImageFile());
				//FileInputStream fileInFTPStream = new FileInputStream(file);
				//InputStream inFTPStream = new ByteArrayInputStream();
				//FTPFileTransfer.uploadFile(strProcessId, strFileName, fileInFTPStream, patientImageDtlVO.getPatCrNo());
				//FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patImageVO[i].getFileNo(), fileInFTPStream, patImageVO[i].getPatCrNo());

// Calling MongoDb from Here  Commented  for saving image to postgres by manisha gangwar on date: 21.3.2018 				
				
				  MongoXmlHandler.getInstance(Config.NOSQL_MONGO_DATASOURCE_PATIENT_IMAGE_UPLOAD).savePDFFile(docId, patImageVO[i].getImageFile());
				 System.out.println("FTP Saved");
				 //End mongodb
				
				//patientImageDtlDAO.create(objHisDAO,patientImageDtlVO, objUserVO_p);
				
				
				// changes for image saving to postgres , previously on mongodb by manisha gangwar date: 21.3.2018
				  //Base64 codec = new Base64();
				  byte[] contentData = patImageVO[i].getImageFile();
		          byte[] encodedData = Base64.encodeBase64(contentData);
		            
				patImageDAO.create(hisDAO, patImageVO[i],userVO);
				synchronized (hisDAO) {
					hisDAO.fire();
				}
				patImageDAO.saveImageToPostgres(hisDAO,patImageVO[i], encodedData,userVO);
				
			}
			
			
			
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
			if (hisDAO != null)
			{
				hisDAO.free();
				hisDAO = null;
			}
			tx.close();
		}
	}*/
	
	
	
	//Added By Ranjit For FTP
	public void savePatientImage(PatientImageDtlVO []patImageVO, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		String count;
		HisDAO hisDAO = new HisDAO("MRD", "MrdBO");
		try
		{
			tx.begin();
			PatientImageDtlDAO patImageDAO=new PatientImageDtlDAO(tx);
			count=patImageDAO.getContCrNoWise(patImageVO[0].getPatCrNo(), userVO);
			for(int i=0;i<patImageVO.length;i++)
			{
				System.out.println("count is :- "+count);
				int countInt=Integer.parseInt(count);
				countInt=countInt+1;
				String docId="";
				docId=patImageVO[i].getPatCrNo()+"_"+countInt;

				patImageVO[i].setDocId(docId); //to save file name as document id as set in PatientImageDtlDAO
				
				File file=new File(patImageVO[i].getFileNo());
				FileUtils.writeByteArrayToFile(file, patImageVO[i].getImageFile());
				FileInputStream fileInFTPStream = new FileInputStream(file);
				
								
				
				
				patImageDAO.create(hisDAO, patImageVO[i],userVO);
				
				synchronized (hisDAO) {
					hisDAO.fire();
				}
				
				patImageVO[i].setFileNo(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD+"_Image_"+ docId);
				
				String fileLocation=FTPFileTransfer.uploadFile(FileTransferConfig.PROCESS_ID_PATIENT_IMAGE_UPLOAD, patImageVO[i].getFileNo(), fileInFTPStream, patImageVO[i].getPatCrNo());
				
			//Q	patImageDAO.saveFileLocation(hisDAO,patImageVO[i],patImageVO[i].getImageFile(),userVO,fileLocation); 
				
				
				
			}
			
			
			
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
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
			tx.close();
		}
	}
	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtl(String deptUnitCode,String ward,UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<CaseSheetDtlVO> caseSheetDtlVOList=null;
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			caseSheetDtlVOList=mrdDAO.getPatientCaseSheetDtl(deptUnitCode, ward, userVO);
			
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
		return caseSheetDtlVOList;
	}

	public List<CaseSheetDtlVO> getPatientCaseSheetDtlByCrNo(String patCrNo,UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<CaseSheetDtlVO> caseSheetDtlVOList=null;
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			caseSheetDtlVOList=mrdDAO.getPatientCaseSheetDtlByCrNo(patCrNo, userVO);
			
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
		return caseSheetDtlVOList;
	}
	
	public CaseSheetLostFoundVO getPatientCaseSheetLostFoundDtl(CaseSheetLostFoundVO caseSheetLostFoundVO,UserVO userVO){
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//List<CaseSheetDtlVO> caseSheetDtlVOList=null;
		try
		{
			tx.begin();
			CaseSheetLostFoundDAOi daoObj=new CaseSheetLostFoundDAO(tx);
			caseSheetLostFoundVO=daoObj.select(caseSheetLostFoundVO, userVO);
			
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
		return caseSheetLostFoundVO;
	}
	
	public void updatePatientCaseSheetStatus(CaseSheetDtlVO caseSheetDtlVO,
			CaseSheetLostFoundVO caseSheetLostFoundVO, String updateType,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			CaseSheetLostFoundDAOi lostFoundDAO=new CaseSheetLostFoundDAO(tx);
			CaseSheetDtlDAOi caseSheetDtlDao=new CaseSheetDtlDAO(tx);
			if(updateType.equals("0") || updateType.equals("1")){
				if(updateType.equals("1")){
					caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_IN_WARD);
				}
				lostFoundDAO.create(caseSheetLostFoundVO, userVO);
			}	
			if(updateType.equals("2")){
				caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_IN_WARD);
				caseSheetDtlDao.create(caseSheetDtlVO, userVO);
			}
			if(!updateType.equals("2")){
				if(updateType.equals("3"))
					caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_CANCEL);
				caseSheetDtlDao.update(caseSheetDtlVO, userVO);
			}	
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
		
	}
	
	/**
	 * Retrieves patient drug advice details on the basis of CR No from the Episode Drug Dtl Table. 
	 * @param _episodeAllergiesVO Provides CR No
	 * @param _userVO Provides User details.
	 * @return EpisodeAllergiesVO[] with values stored in DB.
	 */
	public PatDrugTreatmentDetailVO[] getPateintDrugAdviceAll(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs=null;
		try
		{
			tx.begin();
			PatDrugTreatmentDetailDAOi patDrugTreatmentDetailDAOi=new PatDrugTreatmentDetailDAO(tx);
			patDrugTreatmentDetailVOs=patDrugTreatmentDetailDAOi.getTreatmentAdviceDetailsEMR(_patDrugTreatmentDetailVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
			
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
		return patDrugTreatmentDetailVOs;
	}

	/**
	 * Retrieves patient drug advice details on the basis of CR No from the Episode Drug Dtl Table. 
	 * @param _episodeAllergiesVO Provides CR No
	 * @param _userVO Provides User details.
	 * @return EpisodeAllergiesVO[] with values stored in DB.
	 */
	public PatDrugTreatmentDetailVO[] getPateintDrugAdviceAllOffline(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs=null;
		try
		{
			tx.begin();
//			PatDrugTreatmentDetailDAOi patDrugTreatmentDetailDAOi=new PatDrugTreatmentDetailDAO(tx);
//			patDrugTreatmentDetailVOs=patDrugTreatmentDetailDAOi.getOfflineTreatmentAdviceDetailsEMR(_patDrugTreatmentDetailVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
			
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
		return patDrugTreatmentDetailVOs;
	}

	
	// Added By Shweta for fetching External Treatment Detail on 15-May-2019
		public PatDrugTreatmentDetailVO[] getPateintExtTreatmentDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs=null;
			try
			{
				tx.begin();
				PatDrugTreatmentDetailDAOi patDrugTreatmentDetailDAOi=new PatDrugTreatmentDetailDAO(tx);
				patDrugTreatmentDetailVOs=patDrugTreatmentDetailDAOi.getPateintExtTreatmentDetails(_patDrugTreatmentDetailVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
				
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
			return patDrugTreatmentDetailVOs;
		}
		

	
	/**
	 * Retrieves patient Investigation details on the basis of CR No from the hivt_requisition_dtl Table. 
	 * @param _profInvestigationVO Provides CR No
	 * @param _userVO Provides User details.
	 * @return ProfileInvestigationVO[] with values fetched from DB.
	 */
	public ProfileInvestigationVO[] getPatientInvestigationDetails(ProfileInvestigationVO _profInvestigationVO,String [] departmentUnitArray,String accessType,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ProfileInvestigationVO[] profileInvestigationVOs=null;
		try
		{
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi=new OpdEssentialDAO(tx);
			profileInvestigationVOs=opdEssentialDAOi.getPatientInvestigationDetailsEMR(_profInvestigationVO.getPatCrNo(),departmentUnitArray, accessType,_userVO);
			
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
		return profileInvestigationVOs;
	}
	
	public Map getOutParaDetailEMR(PatientDetailVO dailyPatVO,String []departmentUnitArray,String accessType,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatIntakeOutDtlVO[] arrPatOuttakeDtlVO=null;
		PatIntakeOutDtlVO[] arrPatIntakeDtlVO=null;
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
			try{
			arrPatIntakeDtlVO=patIntakeOutDAO.getOutParaDetailEMR(InpatientConfig.INTAKEOUT_MODE_INTAKE,dailyPatVO,departmentUnitArray, accessType, userVO);
			}
			catch (HisRecordNotFoundException e) 
			{e.printStackTrace();}
			essentialMap.put(MrdConfig.PAT_INTAKE_DETAIL_ARRAY,arrPatIntakeDtlVO);
			try{
			arrPatOuttakeDtlVO=patIntakeOutDAO.getOutParaDetailEMR(InpatientConfig.INTAKEOUT_MODE_OUTTAKE,dailyPatVO,departmentUnitArray, accessType, userVO);
			}
			catch (HisRecordNotFoundException e) 
			{e.printStackTrace();}
			essentialMap.put(MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY,arrPatOuttakeDtlVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return essentialMap;
	}
	
	
	/**
	 * To Get Patient Progress Notes for EMR 
	 * @param doctorRoundDtlVO
	 * @param userVO
	 * @return DoctorRoundDtlVO[]
	 */
	public DoctorRoundDtlVO[] getPatientProgressNotesEMR(DoctorRoundDtlVO doctorRoundDtlVO,String[] departmentUnitArray, String accessType, UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		DoctorRoundDtlVO[] doctorRoundDtlVOs = null;
		try {
			tx.begin();
			DoctorRoundDtlDAOi doctorRoundDtlDAOi = new DoctorRoundDtlDAO(tx);
			doctorRoundDtlVOs = doctorRoundDtlDAOi.getPatientProgressNotesEMR(
					doctorRoundDtlVO.getPatCrNo(),departmentUnitArray,accessType, userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return doctorRoundDtlVOs;
	}
	
	/**
	 * To Get Patient Patient Vitals For EMR 
	 * @param _patientClinicalDetailVO
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public PatientClinicalDetailVO[] getPatientVitalsEMR(PatientClinicalDetailVO _patientClinicalDetailVO,String [] departmentUnitArray,String accessType, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
		try {
			tx.begin();
			PatientClinicalDetailDAOi patientClinicalDetailDAOi = new PatientClinicalDetailDAO(tx);
			patientClinicalDetailVOs = patientClinicalDetailDAOi.getPatientVitalsEMR(_patientClinicalDetailVO, departmentUnitArray, accessType, _userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return patientClinicalDetailVOs;
	}
	
	/**
	 * To Get Patient External Examination For EMR 
	 * @param _episodExtInvDtlVO
	 * @param userVO
	 * @return EpisodeExtInvDtlVO[]
	 */
	public EpisodeExtInvDtlVO[] getPatientExternalExaminationEMR(EpisodeExtInvDtlVO _episodExtInvDtlVO,String[] departmentUnitArray, String accessType, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeExtInvDtlVO[] episodeExtInvDtlVOs = null;
		try {
			tx.begin();
			EpisodeExtInvDtlDAOi episodeExtInvDtlDAOi = new EpisodeExtInvDtlDAO(tx);
			episodeExtInvDtlVOs = episodeExtInvDtlDAOi.getPatientExternalInvDetailEMR(_episodExtInvDtlVO.getPatCrNo(), departmentUnitArray, accessType, _userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return episodeExtInvDtlVOs;
	}
	
	/**
	 * To Get Patient Complaints For EMR 
	 * @param _patientClinicalDetailVO
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public PatientClinicalDetailVO[] getPatientComplaintsEMR(PatientClinicalDetailVO _patientClinicalDetailVO,String _templateCategory, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
		try {
			tx.begin();
			PatientClinicalDetailDAOi patientClinicalDetailDAOi = new PatientClinicalDetailDAO(tx);
			patientClinicalDetailVOs = patientClinicalDetailDAOi.getPatientComplaintsByCrNoEMR(_patientClinicalDetailVO,_templateCategory, _userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return patientClinicalDetailVOs;
	}

	
	public void saveForBirthRegUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			BirthDeathUploadDAOi birthDAO=new BirthDeathUploadDAO(tx);
			if(birthUploadVO.getIsHandoverTo()==null)
				birthDAO.insertUpload(birthUploadVO,userVO);
			else
				birthDAO.insertUploadHandover(birthUploadVO,userVO);
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
	}
	
	public void saveHandoverForBirthRegUpload(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			BirthDeathUploadDAOi birthDAO=new BirthDeathUploadDAO(tx);
			birthDAO.insertHandover(birthHandoverVO,userVO);
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
	}
	


	
	/**
	 * Getting Patient All Profiles List For EMR
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<PatientProfileDetailVO> lstPatProfiles = null;

		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			tx.begin();
			lstPatProfiles = dao.getPatientProfilesForEMR(_patCrNo, departmentUnitArray,accessType, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
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
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return lstPatProfiles;
	}
	
	/**
	 * To get patient diagnosis images
	 * @param _opdPatientImageDtlVO
	 * @param _userVO
	 * @return OpdPatientImageDtlVO[]
	 */
	public OpdPatientImageDtlVO[] getPatientDiagnosisImages(OpdPatientImageDtlVO _opdPatientImageDtlVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		OpdPatientImageDtlVO[] opdPatientImageDtlVOs = null;

		try
		{
			OpdPatientImageDtlDAO dao = new OpdPatientImageDtlDAO(tx);
			tx.begin();
			opdPatientImageDtlVOs = dao.getPatientImagesDetailsEMR(_opdPatientImageDtlVO, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
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
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return opdPatientImageDtlVOs;
	}

	
	
	public void saveSummonDetail(SummonDetailVO summonDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		try
		{
			tx.begin();
			SummonDetailDAOi daoObj=new SummonDetailDAO(tx);
			
			if(summonDetailVO.getRecordType().equals(MrdConfig.NOT_IN_DATABASE))
			{
				daoObj.create(summonDetailVO, userVO);
			}
			else
			{
				daoObj.saveComputSummonDtl(summonDetailVO, userVO);
			}
			
			
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
		
	}

	
	
	
	public void saveSummonAssigmentDetail(SummonDetailVO summonDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		try
		{
			tx.begin();
			SummonDetailDAOi daoObj=new SummonDetailDAO(tx);
			daoObj.SaveAssignDetail(summonDetailVO, userVO);
			
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
		
	}
	
	public void savePostSummonDetail(SummonDetailVO summonDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			SummonDetailDAOi daoObj=new SummonDetailDAO(tx);
			daoObj.SavePostSummonDetail(summonDetailVO, userVO);
			
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
		
	}
	
	public void saveChangeAssignDetail(AssignmentChangeDtlVO assignChangeDtlVO,SummonDetailVO summonDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		try
		{
			tx.begin();
			AssignmentChangeDtlDAOi daoObj=new AssignmentChangeDtlDAO(tx);
			daoObj.create(assignChangeDtlVO, userVO);
			
			SummonDetailDAOi summonDaoObj=new SummonDetailDAO(tx);
			summonDaoObj.SaveAssignDetail(summonDetailVO, userVO);
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
		
	}
	
	public void saveInsuranceDetail(InsuranceDetailVO insuranceDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			InsuranceDetailDAOi daoObj=new InsuranceDetailDAO(tx);
			daoObj.creat(insuranceDetailVO, userVO);
			
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
		
	}
	
	/**To get patient operation performed
	 * @param crNo
	 * @param _userVO
	 * @return List
	 */
	public List getPatientOperationList(String crNo, String[] departmentUnitArray, String accessType,UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List listOperationPerformed=null;
		try {
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi=new OpdEssentialDAO(tx);
			listOperationPerformed = opdEssentialDAOi.getOperationPerformedListEMR(crNo,departmentUnitArray,accessType, _userVO);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return listOperationPerformed;
	}
	
	/**To get template list and para id value map
	 * @param crNo
	 * @param deptCode
	 * @param reqNo
	 * @param _userVO
	 * @return Map
	 */
	public Map getPatientOperationTemplateParaValueId(String crNo,String deptCode,String reqNo, UserVO _userVO) 
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map templateAndParaDetails=new HashMap();
		List listOperationTemplate=null;
		List templateParaIdValue=null;
		try {
			tx.begin();
			OpdEssentialDAOi opdEssentialDAOi=new OpdEssentialDAO(tx);
			listOperationTemplate = opdEssentialDAOi.getOTTemplateListEMR(deptCode, _userVO);
			templateParaIdValue=opdEssentialDAOi.getOTTemplateParaValueEMR(deptCode, crNo, reqNo, _userVO);
			templateAndParaDetails.put(MrdConfig.OPERATION_TEMPLATE_LIST,listOperationTemplate);
			templateAndParaDetails.put(MrdConfig.OPERATION_TEMPLATE_PARA_ID_VALUE,templateParaIdValue);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		} catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return templateAndParaDetails;
	}

	
	public ANCDetailVO[] getPatientAncDetails(ANCDetailVO _ancDetailVO,String [] departmentUnitArray,String accessType, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ANCDetailVO[] ancDetailVOs=null;
		try
		{
			tx.begin();
			ANCDtlDAOi ancDetailDAO=new ANCDtlDAO(tx);
			ancDetailVOs=ancDetailDAO.getPatientAncDetailsForEMR(_ancDetailVO,departmentUnitArray,accessType, _userVO);
			
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
		return ancDetailVOs;
	}

	
	public void rejectCertificateHandover(List<RecordDispatchDtlVO> lstRejectRecord,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			RecordDispatchDtlDAOi recordMovDAO=new RecordDispatchDtlDAO(tx);
			for(int i=0;i<lstRejectRecord.size();i++)
			{
				recordMovDAO.updateRejectRecord(lstRejectRecord.get(i),userVO);
			}	
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public Map getCaseSheetEnquiryEssentials(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		//PatientDetailVO patientDetailVO;
		InPatientEssentialDAO inpatientEssentialDAO=new InPatientEssentialDAO(tx);
		OpdEssentialDAOi opdEssentialDAOi=new OpdEssentialDAO(tx);
		List dischargeStatusList=null;
		List alertsList=null;
		List allergyList=null;
		try
		{
			tx.begin();
			dischargeStatusList=inpatientEssentialDAO.getDischargeStatusList(userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_ENQUIRY_DISCHARGE_TYPE_LIST, dischargeStatusList);
			
			alertsList=opdEssentialDAOi.getPatAlertDetail(userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_ENQUIRY_ALERT_TYPE_LIST, alertsList);
			
			allergyList=opdEssentialDAOi.getAllergyTypeList(userVO);
			essentialMap.put(MrdConfig.CASE_SHEET_ENQUIRY_ALLERGY_TYPE_LIST, allergyList);
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
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}


public CommonCaseSheetEnquiryVO[] searchPatientCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO){
		
		JDBCTransactionContext tx =new JDBCTransactionContext();
		//CommonEnquiryVO[] commonEnquiryVOs=null;
		CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVO=null;
		HashMap caseSheetDetailMap=new HashMap();
		//HashMap finalQueryMap=new HashMap();
		MrdEnquiryDAOi mrdEnquiryDAOi=new MrdEnquiryDAO(tx);
		try
		{
				tx.begin();
				caseSheetDetailMap=HelperMethods.createQueryMapFromVO(caseSheetEnquiryVO);
			
				if(caseSheetEnquiryVO.getEnquiryType().equals(MrdConfig.CASE_SHEET_ENQUIRY_PATIENT_WISE))
				{
					commonCaseSheetEnquiryVO=mrdEnquiryDAOi.searchPatientCaseSheet(caseSheetDetailMap,_userVO);
				}
				if(caseSheetEnquiryVO.getEnquiryType().equals(MrdConfig.CASE_SHEET_ENQUIRY_CHRONIC_DISEASE_WISE))
				{
					commonCaseSheetEnquiryVO=mrdEnquiryDAOi.searchChronicCaseSheet(caseSheetEnquiryVO,_userVO);
				}
				if(caseSheetEnquiryVO.getEnquiryType().equals(MrdConfig.CASE_SHEET_ENQUIRY_ALLERGY_WISE))
				{
					commonCaseSheetEnquiryVO=mrdEnquiryDAOi.searchAllergyCaseSheet(caseSheetEnquiryVO,_userVO);
				}
				if(caseSheetEnquiryVO.getEnquiryType().equals(MrdConfig.CASE_SHEET_ENQUIRY_DIAGNOSIS_WISE))
				{
					commonCaseSheetEnquiryVO=mrdEnquiryDAOi.searchDiagnosisCaseSheet(caseSheetEnquiryVO,_userVO);
				}
		}	
		catch(HisRecordNotFoundException e){
				tx.rollback();	
				 e.printStackTrace(); 
				throw new HisRecordNotFoundException(e.getMessage()); 
			}
			catch(HisApplicationExecutionException e){	   		   	
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisApplicationExecutionException(e.getMessage());
		   	 }
		   	 
		   	catch(HisDataAccessException e){		
		   		 tx.rollback();
		   		 e.printStackTrace();   		 
		   		 throw new HisDataAccessException(e.getMessage());  	
		  	 }
			catch(Exception e){
				e.printStackTrace();	
				tx.rollback();		
				throw new HisApplicationExecutionException(e.getMessage());
			}
			finally{
				tx.close();			
			}
			return commonCaseSheetEnquiryVO;		

	}

		public MrdRecordDtlVO[] fetchRecordStorageDetail(CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO,UserVO _userVO){
			
			JDBCTransactionContext tx =new JDBCTransactionContext();
			MrdRecordDtlVO[] mrdRecordDtlVO=null;
			MrdEnquiryDAOi mrdEnquiryDAOi=new MrdEnquiryDAO(tx);
			try
			{
					tx.begin();
					mrdRecordDtlVO=mrdEnquiryDAOi.fetchRecordStorageDetail(_commonCaseSheetEnquiryVO,_userVO);
				
			}	
			catch(HisRecordNotFoundException e){
					tx.rollback();	
					 e.printStackTrace(); 
					throw new HisRecordNotFoundException(e.getMessage()); 
				}
				catch(HisApplicationExecutionException e){	   		   	
			   		 tx.rollback();
			   		 e.printStackTrace();   		 
			   		 throw new HisApplicationExecutionException(e.getMessage());
			   	 }
			   	 
			   	catch(HisDataAccessException e){		
			   		 tx.rollback();
			   		 e.printStackTrace();   		 
			   		 throw new HisDataAccessException(e.getMessage());  	
			  	 }
				catch(Exception e){
					e.printStackTrace();	
					tx.rollback();		
					throw new HisApplicationExecutionException(e.getMessage());
				}
				finally{
					tx.close();			
				}
				return mrdRecordDtlVO;		
		
		}

	
	public void saveRecordAccepted(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String mrdRecordId="";
		String status="";
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO=new MRDRecordDtlDAO(tx);
			RecordDispatchDtlDAOi dispatchDAO=new RecordDispatchDtlDAO(tx);
			RecordTypeWiseEnclosureDAOi enclosureSummaryDAO=new RecordTypeWiseEnclosureDAO(tx);
			RecordTypeWiseChecklistDAOi checkListDtlDAO=new RecordTypeWiseChecklistDAO(tx);
			RecordEnclosureDtlDAOi recEnclosureDAO=new RecordEnclosureDtlDAO(tx);
			
			for(int i=0;i<lstMrdRecordVO.size();i++)
			{
				mrdRecordId=mrdRecordDAO.generateMrdRecordId(userVO, lstMrdRecordVO.get(i).getRecordType());
				lstMrdRecordVO.get(i).setMrdRecordId(mrdRecordId);
				mrdRecordDAO.insertRecordAcceptInMrd(lstMrdRecordVO.get(i), userVO);
				status=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_IN_MRD; 
				if(!lstMrdRecordVO.get(i).getRecordType().equals(MrdConfig.RECORD_TYPE_OPD_FILE))
					dispatchDAO.updateRecordStatus(lstMrdRecordVO.get(i).getDispatchId(),status,userVO);
			}
			
			if(lstRecordEnclosureDtl!=null)
			{
				for(int i=0;i<lstRecordEnclosureDtl.size();i++)
				{
					lstRecordEnclosureDtl.get(i).setMrdRecordId(mrdRecordId);
					recEnclosureDAO.create(lstRecordEnclosureDtl.get(i), userVO);
				}
			}
			
			if(lstEnclosure!=null)
			{
				for(int i=0;i<lstEnclosure.size();i++)
				{
					enclosureSummaryDAO.updateEnclosurePagesInMrd(lstEnclosure.get(i),userVO);
				}
			}
			
			if(lstCheckList!=null)
			{
				for(int i=0;i<lstCheckList.size();i++)
				{
					checkListDtlDAO.create(lstCheckList.get(i), userVO);
				}
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveRecordArchived(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String mrdRecordId="";
		String status="";
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO=new MRDRecordDtlDAO(tx);
			RecordDispatchDtlDAOi dispatchDAO=new RecordDispatchDtlDAO(tx);
			RecordTypeWiseEnclosureDAOi enclosureSummaryDAO=new RecordTypeWiseEnclosureDAO(tx);
			RecordTypeWiseChecklistDAOi checkListDtlDAO=new RecordTypeWiseChecklistDAO(tx);
			RecordEnclosureDtlDAOi recEnclosureDAO=new RecordEnclosureDtlDAO(tx);
			
			for(int i=0;i<lstMrdRecordVO.size();i++)
			{
				mrdRecordId=mrdRecordDAO.generateMrdRecordId(userVO, lstMrdRecordVO.get(i).getRecordType());
				lstMrdRecordVO.get(i).setMrdRecordId(mrdRecordId);
				mrdRecordDAO.create(lstMrdRecordVO.get(i), userVO);
				status=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_IN_MRD; 
				if(!lstMrdRecordVO.get(i).getRecordType().equals(MrdConfig.RECORD_TYPE_OPD_FILE))
					dispatchDAO.updateRecordStatus(lstMrdRecordVO.get(i).getDispatchId(),status,userVO);
			}
			
			if(lstRecordEnclosureDtl!=null)
			{
				for(int i=0;i<lstRecordEnclosureDtl.size();i++)
				{
					lstRecordEnclosureDtl.get(i).setMrdRecordId(mrdRecordId);
					recEnclosureDAO.create(lstRecordEnclosureDtl.get(i), userVO);
				}
			}
			
			if(lstEnclosure!=null)
			{
				for(int i=0;i<lstEnclosure.size();i++)
				{
					enclosureSummaryDAO.updateEnclosurePagesInMrd(lstEnclosure.get(i), userVO);
				}
			}
			
			if(lstCheckList!=null)
			{
				for(int i=0;i<lstCheckList.size();i++)
				{
					checkListDtlDAO.create(lstCheckList.get(i), userVO);
				}
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveRecordLost(List<RecordLostFoundDtlVO> lstLostRecord,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status="";
		
		try
		{
			tx.begin();
			RecordLostFoundDtlDAOi recordLostDAO=new RecordLostFoundDtlDAO(tx);
			RecordDispatchDtlDAOi dispatchDAO=new RecordDispatchDtlDAO(tx);
			
			for(int i=0;i<lstLostRecord.size();i++)
			{
				recordLostDAO.create(lstLostRecord.get(i), userVO);
				status=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_LOST; 
				dispatchDAO.updateRecordStatus(lstLostRecord.get(i).getDispatchId(),status,userVO);
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveRecordArchivalInMrd(List<MrdRecordDtlVO> lstMrdRecordVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO=new MRDRecordDtlDAO(tx);
			
			for(int i=0;i<lstMrdRecordVO.size();i++)
			{
				mrdRecordDAO.updateRecordArchivalDetail(lstMrdRecordVO.get(i),userVO);
			}
			
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveFoundDetailNArchivedInMrd(List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordLostFoundDtlVO> lstFoundRecordVO,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String status="";
		String mrdRecordId="";
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDAO=new MRDRecordDtlDAO(tx);
			RecordLostFoundDtlDAOi foundDAO=new RecordLostFoundDtlDAO(tx);
			RecordDispatchDtlDAOi dispatchDAO=new RecordDispatchDtlDAO(tx);
			RecordTypeWiseEnclosureDAOi enclosureSummaryDAO=new RecordTypeWiseEnclosureDAO(tx);
			RecordEnclosureDtlDAOi recEnclosureDAO=new RecordEnclosureDtlDAO(tx);
			RecordTypeWiseChecklistDAOi checkListDtlDAO=new RecordTypeWiseChecklistDAO(tx);
			
			for(int i=0;i<lstMrdRecordVO.size();i++)
			{
				mrdRecordId=mrdRecordDAO.generateMrdRecordId(userVO, lstMrdRecordVO.get(i).getRecordType());
				lstMrdRecordVO.get(i).setMrdRecordId(mrdRecordId);
				mrdRecordDAO.create(lstMrdRecordVO.get(i), userVO) ;
				status=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_IN_MRD; 
				dispatchDAO.updateRecordStatus(lstMrdRecordVO.get(i).getDispatchId(),status,userVO);
			}
			
			if(lstRecordEnclosureDtl!=null)
			{
				for(int i=0;i<lstRecordEnclosureDtl.size();i++)
				{
					lstRecordEnclosureDtl.get(i).setMrdRecordId(mrdRecordId);
					recEnclosureDAO.create(lstRecordEnclosureDtl.get(i), userVO);
				}
			}
			
			for(int i=0;i<lstFoundRecordVO.size();i++)
			{
				foundDAO.updateFoundDetail(lstFoundRecordVO.get(i),userVO);
			}
			
			if(lstEnclosure!=null)
			{
				for(int i=0;i<lstEnclosure.size();i++)
				{
					enclosureSummaryDAO.updateEnclosurePagesInMrd(lstEnclosure.get(i), userVO);
				}
			}
			
			if(lstCheckList!=null)
			{
				for(int i=0;i<lstCheckList.size();i++)
				{
					checkListDtlDAO.create(lstCheckList.get(i), userVO);
				}
			}
			
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveForOfflineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String requestId="";
		
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordReqDAO=new MrdRecordRequestDtlDAO(tx);
			RequestRecordDtlDAOi reqRecordDAO=new RequestRecordDtlDAO(tx);
			
			requestId=mrdRecordReqDAO.generateRequestId(userVO);
			mrdRecordRequestVO.setRequestId(requestId);
			
			if(mrdRecordRequestVO.getRequestByFlag().equals("1"))
			{
				mrdRecordRequestVO.setRequestBy("");
			}
			
			for(int i=0;i<lstRequestRecordVO.size();i++)
			{
				lstRequestRecordVO.get(i).setRequestId(requestId);
				reqRecordDAO.create(lstRequestRecordVO.get(i), userVO);
			}
			
			mrdRecordReqDAO.create(mrdRecordRequestVO, userVO);

			
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
			throw new HisDataAccessException(e.getMessage());
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
	
	//added by swati on date :27-02-2019
	public void saveIcdIndexDetail(List<MrdIcdDtlVO> lstmrdVo,UserVO userVO,PatientDetailVO patDtlVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String requestId="";
        String StrMode = "";
        List<MrdIcdDtlVO> lstPrevIcdRecords =null;
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordReqDAO=new MrdRecordRequestDtlDAO(tx);
			RequestRecordDtlDAOi reqRecordDAO=new RequestRecordDtlDAO(tx);
			MrdEssentialDAO mrdEssentialDAO = new MrdEssentialDAO(tx);
			
			//Added by Vasu on 07.March.2019
			lstPrevIcdRecords = mrdEssentialDAO.getPreviousIcdCodeDetails(patDtlVO ,userVO);
			
			if(!lstPrevIcdRecords.isEmpty())
			{
				StrMode = "2";
			    mrdRecordReqDAO.icdIndexDtlInsert(lstmrdVo.get(0), userVO,StrMode);
			}
			
	for(int i=0;i<lstmrdVo.size();i++){
		       StrMode = "1";
			
			mrdRecordReqDAO.icdIndexDtlInsert(lstmrdVo.get(i), userVO,StrMode);

	}
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
			throw new HisDataAccessException(e.getMessage());
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
	
	public void saveOnlineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String requestId="";
		
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordReqDAO=new MrdRecordRequestDtlDAO(tx);
			RequestRecordDtlDAOi reqRecordDAO=new RequestRecordDtlDAO(tx);
			
			requestId=mrdRecordReqDAO.generateRequestId(userVO);
			mrdRecordRequestVO.setRequestId(requestId);
						
			for(int i=0;i<lstRequestRecordVO.size();i++)
			{
				lstRequestRecordVO.get(i).setRequestId(requestId);
				reqRecordDAO.onlineInsert(lstRequestRecordVO.get(i), userVO);
			}
			mrdRecordReqDAO.onlineInsert(mrdRecordRequestVO, userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
	


	/* **********************************Case sheet handover******************************************/
	
	public RecordDispatchDtlVO[] getRecordDispatchList(RecordDispatchDtlVO recordDispatchVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordDispatchDtlVO [] recordDispatchArray=null;
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			recordDispatchArray=mrdDAO.getRecordListByUnitWard(recordDispatchVO, userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
		return recordDispatchArray;
	}
	
	//ADDED BY SWATI  ON DATE:06-MAY-2019
			//ADM NO WISE DTL
	public RecordDispatchDtlVO[] getCaseSheetListToReadyADMNOWISE2(String admno,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordDispatchDtlVO [] recordDispatchArray=null;
		try
		{
			tx.begin();
			
			MrdDAOi mrdDAO=new MrdDAO(tx);
			recordDispatchArray=mrdDAO.getCaseSheetListToReadyADMNOWISE2(admno, userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
		return recordDispatchArray;
	}
	
	
	//ADDED BY SWATI  ON DATE:07-MAY-2019
			//CR NO WISE DTL
		public RecordDispatchDtlVO[] getCaseSheetListToReadyCRNOWISE2(String crno,UserVO userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			RecordDispatchDtlVO [] recordDispatchArray=null;
			try
			{
				tx.begin();
				
				MrdDAOi mrdDAO=new MrdDAO(tx);
				recordDispatchArray=mrdDAO.getCaseSheetListToReadyCRNOWISE2(crno, userVO);
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
				throw new HisDataAccessException(e.getMessage());
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
			return recordDispatchArray;
		}
		
		
		
	
	
	public RecordDispatchDtlVO[] getRecordListByPatCrNo(RecordDispatchDtlVO recordDispatchVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordDispatchDtlVO [] recordDispatchArray=null;
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			recordDispatchArray=mrdDAO.getRecordListByPatCrNo(recordDispatchVO, userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
		return recordDispatchArray;
	}
	
	
	public Map getEnclosureSummary(RecordDispatchDtlVO recordDispatchVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeWiseEnclosureMstVO [] enclosureSummaryArray=null;
		RecordTypeCheckListMstVO[] arrChecklistDtlVO=null;
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureDAOi enclosureDAO=new RecordTypeWiseEnclosureDAO(tx);
			MrdDAOi mrdDAO=new MrdDAO(tx);
			List enclosureVOList=enclosureDAO.getEnclosureDetailByDispatchId(recordDispatchVO.getDispatchId(), recordDispatchVO.getRecordType(), userVO);
			if(enclosureVOList!=null){
				enclosureSummaryArray=new RecordTypeWiseEnclosureMstVO[enclosureVOList.size()];
				enclosureVOList.toArray(enclosureSummaryArray);
			}	
			essentialMap.put(MrdConfig.RECORD_ENCLOSURE_SUMMARY_VO_ARRAY, enclosureSummaryArray);
			arrChecklistDtlVO=mrdDAO.getChecklistByRecordType(MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL, recordDispatchVO.getRecordType(), userVO);
			essentialMap.put(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY, arrChecklistDtlVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException(e.getMessage());
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
		return essentialMap;
	}
	
	
	//get the list of the checklist for handover
	public RecordTypeCheckListMstVO[] getChecklistForHandOver(String recordType,
			String checklistMode, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeCheckListMstVO [] checklistVOArray=null;
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			checklistVOArray=mrdDAO.getChecklistByRecordType(checklistMode, recordType, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException(e.getMessage());
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
		return checklistVOArray;
	}
	
	public Map getEssentialForCaseSheetHandOver(RecordDispatchDtlVO recordDispatchVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordTypeCheckListMstVO [] checklistVOArray=null;
		//RecordCheckListDtlVO [] preChecklistVOArray=null;
		List empList=null;
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			MrdDAOi mrdDAO=new MrdDAO(tx);
			//RecordTypeWiseChecklistDAOi checklistDAO=new RecordTypeWiseChecklistDAO(tx);
			empList=mrdDAO.getEmployeeListForHandover(MrdConfig.PROCESS_ID_CASESHEET, recordDispatchVO.getDeptUnitCode(), recordDispatchVO.getWardCode(), userVO);
			essentialMap.put(MrdConfig.RECIEVING_HANDOVER_EMP_LIST_OPTION, empList);
			//preChecklistVOArray=checklistDAO.getCheckListedRecord(recordDispatchVO.getDispatchId(), recordDispatchVO.getRecordType(), userVO);
			//essentialMap.put(MrdConfig.CASE_SHEET_CHECKLIST_PRE_ADDED_VO_LIST, preChecklistVOArray);
			checklistVOArray=mrdDAO.getChecklistByRecordType(MrdConfig.CHECKLIST_MODE_HANDOVER_LEVEL, recordDispatchVO.getRecordType(), userVO);
			essentialMap.put(MrdConfig.CHECKLIST_CASESHEET_DISPATCH_VO_ARRAY, checklistVOArray);
			
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
			throw new HisDataAccessException(e.getMessage());
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
		return essentialMap;
	}
	
	//save the detail of the handover
	public void saveCaseSheetHandoverDetail(List<RecordDispatchDtlVO> recordDispatchVOList,List checklistVOList,Map enclosureMap,String isAccepted, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//List<RecordTypeWiseEnclosureMstVO>  enclosureVOList=null;
		RecordDispatchDtlVO recordDispatchVO=null;
		String mrdRecordId = "";
		try
		{
			tx.begin();
			RecordTypeWiseEnclosureDAOi enclosureDAO=new RecordTypeWiseEnclosureDAO(tx);
			RecordTypeWiseChecklistDAOi checklistDAO=new RecordTypeWiseChecklistDAO(tx);	
			RecordDispatchDtlDAOi recordDispatchDAO=new RecordDispatchDtlDAO(tx);	
			CaseSheetDtlDAOi casesheetDtlDAO=new CaseSheetDtlDAO(tx);	
			
			for(int i=0;i<recordDispatchVOList.size();i++){
				recordDispatchVO=(RecordDispatchDtlVO)recordDispatchVOList.get(i);
				CaseSheetDtlVO caseSheetDtlVO=new CaseSheetDtlVO();
				caseSheetDtlVO.setCaseSheetId(recordDispatchVO.getRecordId());
				//MRDRecordDtlDAOi mrdRecordDAO=new MRDRecordDtlDAO(tx);
				/*//Added by Prachi
				
				mrdRecordId=mrdRecordDAO.generateMrdRecordId(userVO, recordDispatchVOList.get(i).getRecordType());
				recordDispatchVOList.get(i).setMrdRecordId(mrdRecordId);
				*/
				//if case sheet is accepted then save the handover detail
				if(isAccepted.equals("1")){
					recordDispatchDAO.updateRecordForCasesheetHandover(recordDispatchVO, userVO);
					//RecordCheckListDtlVO checklistVO=null;
					//save the checklist detail
					/*if(checklistVOList!=null)
					for(int j=0;j<checklistVOList.size();j++){
						checklistVO=(RecordCheckListDtlVO)checklistVOList.get(j);
						checklistVO.setDispatchId(recordDispatchVO.getDispatchId());
						checklistVO.setRecordId(recordDispatchVO.getRecordId());
						checklistDAO.create(checklistVO, userVO);
					}*/
					//update case sheet status as dipatch of case sheet dtl table
					caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_DISPATCHED);
					casesheetDtlDAO.update(caseSheetDtlVO, userVO);
				}
				//if case sheet is rejected then update the record status of record dispatch table
				//as return and update case sheet status as inWard of caseSheet dtl table
				else{
					caseSheetDtlVO.setCaseSheetStatus(MrdConfig.CASE_SHEET_IN_WARD);
					casesheetDtlDAO.update(caseSheetDtlVO, userVO);
					recordDispatchDAO.updateRejectRecord(recordDispatchVO, userVO);
				}
			}
			
			//update the enclosure detail if case sheet is accepted
			if(isAccepted.equals("1")){
				if(enclosureMap!=null){
					Set keySet=enclosureMap.keySet();
					Iterator itr=keySet.iterator();
					while (itr.hasNext()) {
						List list = (List )enclosureMap.get(itr.next());
						List enclosureList=(List)list.get(0);
						for(int i=0;i<enclosureList.size();i++){
							enclosureDAO.updateVerifyByPeon((RecordTypeWiseEnclosureMstVO)enclosureList.get(i), userVO);
						}
						checklistVOList=(List)list.get(1);
						RecordCheckListDtlVO checklistVO=null;
						for(int j=0;j<checklistVOList.size();j++){
							checklistVO=(RecordCheckListDtlVO)checklistVOList.get(j);
							checklistDAO.create(checklistVO, userVO);
						}
					}
				}
			}
			
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
			throw new HisDataAccessException(e.getMessage());
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
		
		
		if(isAccepted.equals("1"))
		{
		try
		{
			// Acceptance in MRD at the time of Handover Change By Prachi dated 16-1-19 as per AIIMSP REquirement

			
			List<MrdRecordDtlVO> lstMrdRecordVO=new ArrayList<MrdRecordDtlVO>();
			List<RecordTypeWiseEnclosureMstVO> lstEnclosure=new ArrayList<RecordTypeWiseEnclosureMstVO>();
			List<RecordCheckListDtlVO> lstCheckList=new ArrayList<RecordCheckListDtlVO>();
			List<RecordEnclosureDtlVO> lstRecordEnclosureDtl=new ArrayList<RecordEnclosureDtlVO>();

			
			//RecordDispatchDtlVO[] arrRecordDispatchVO=null; recordDispatchVOList
			
			for(RecordDispatchDtlVO voDispatch : recordDispatchVOList)
			{
				MrdRecordDtlVO mrdRecordVO=new MrdRecordDtlVO();
				mrdRecordVO.setRecordId(voDispatch.getRecordId());
				mrdRecordVO.setRecordDesc(voDispatch.getRecordDesc());
				mrdRecordVO.setRecordType(voDispatch.getRecordType());
				mrdRecordVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_IN_MRD);
				mrdRecordVO.setMrdCode(""); // ---- Need to update at the time of Archival
				mrdRecordVO.setPatCrNo(voDispatch.getPatCrNo());
				mrdRecordVO.setEpisodeCode(voDispatch.getEpisodeCode());
				mrdRecordVO.setEpisodeVisitNo(voDispatch.getEpisodeVisitNo());
				mrdRecordVO.setPatAdmNo(voDispatch.getPatAdmNo());
				mrdRecordVO.setDispatchId(voDispatch.getDispatchId());
				
				lstMrdRecordVO.add(mrdRecordVO);
							
				Map enclosureListMap=enclosureMap;
			/*	if(enclosureListMap!=null)
				{
					List lstEnclosureInMrdVO=(List)enclosureListMap.get(voDispatch.getRecordId());
					//System.out.println("size of enclosurelist" +lstEnclosureInMrdVO.size());
					for(int k=0;k<lstEnclosureInMrdVO.size();k++)
					{
						RecordTypeWiseEnclosureMstVO enclosureDtlVO=new RecordTypeWiseEnclosureMstVO();
						enclosureDtlVO=(RecordTypeWiseEnclosureMstVO)lstEnclosureInMrdVO.get(k);
						
						lstEnclosure.add(enclosureDtlVO);
						
						RecordEnclosureDtlVO recEnclosureDtlVO=new RecordEnclosureDtlVO();
						recEnclosureDtlVO.setEnclosureId(enclosureDtlVO.getEnclosureId());
						recEnclosureDtlVO.setPages(enclosureDtlVO.getVerifiedByMrd());
						recEnclosureDtlVO.setIsLost(MrdConfig.NO);
						
						lstRecordEnclosureDtl.add(recEnclosureDtlVO);
					}
			}
							
				////For CheckList
				List lstArchivalCheckList=checklistVOList;
				for(int k=0;k<lstArchivalCheckList.size();k++)
				{
					RecordCheckListDtlVO checkListDtlVO=new RecordCheckListDtlVO();
					checkListDtlVO=(RecordCheckListDtlVO)lstArchivalCheckList.get(k);
					
					lstCheckList.add(checkListDtlVO);
				}*/
			}
			if(isAccepted.equals("1"))
			{	
			saveRecordAccepted(lstMrdRecordVO, lstEnclosure, lstCheckList, lstRecordEnclosureDtl, userVO);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
		}
		}
		
	}
	/* ************************************End case sheet handover*********************************************/
	
	//Case sheet approval
	public void saveCaseSheetApprovalDetail(List recordDispatchVOList,List<CaseSheetDtlVO> caseSheetDtlVOList, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		RecordDispatchDtlVO recordDispatchVO=null;
		try
		{
			tx.begin();
			RecordDispatchDtlDAOi recordDispatchDAO=new RecordDispatchDtlDAO(tx);	
			CaseSheetDtlDAOi caseSheetDtlDAO=new CaseSheetDtlDAO(tx);	
			//update the approval detail of the record dipatch dtl table of the selected dipatchId
			for(int i=0;i<recordDispatchVOList.size();i++){
				recordDispatchVO=(RecordDispatchDtlVO)recordDispatchVOList.get(i);
				recordDispatchDAO.updateApprovalDetail(recordDispatchVO, userVO);
			}
			
			for(int i=0;i<caseSheetDtlVOList.size();i++){
				caseSheetDtlDAO.update(caseSheetDtlVOList.get(i), userVO);
			}
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
			throw new HisDataAccessException(e.getMessage());
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
	

	public void saveSummonAcceptenceDetail(String  summonId, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		//int rowCount=0;
		try
		{
			tx.begin();
			SummonDetailDAOi daoObj=new SummonDetailDAO(tx);
			daoObj.SaveSummonAcceptedDetail(summonId, userVO);
			
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
		
	}
	
	public void saveInsuranceClaimRecordEntry(InsuranceDetailVO insuranceDetailVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			InsuranceDetailDAOi daoObj=new InsuranceDetailDAO(tx);
			daoObj.saveInsuranceClaimRecordEntry(insuranceDetailVO, userVO);
			
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
	}
	
	public void saveMrdRecordReturnDetail(List<MrdRecordReturnDtlVO> mrdRecordReturnDtlList,List<MrdRecordDtlVO> mrdRecordDtlVOList,String isRequestReturn,List<MrdRackShelfChangeDtlVO> mrdRackShelfChangeList,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		
		try
		{
			tx.begin();
			MrdRecordReturnDtlDAOi mrdRecordReturnDAO=new MrdRecordReturnDtlDAO(tx);
			MRDRecordDtlDAOi mrdRecordDAO=new MRDRecordDtlDAO(tx);
			MrdRecordRequestDtlDAOi mrdRecordRequestDAO=new MrdRecordRequestDtlDAO(tx);
			MrdRackShelfChangeDtlDAOi mrdRackShelfChangeDAO= new MrdRackShelfChangeDtlDAO(tx);
			for(int i=0;i<mrdRecordReturnDtlList.size();i++)
			{
				mrdRecordReturnDAO.create(mrdRecordReturnDtlList.get(i), userVO);
				
				/*MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO();
				mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_IN_MRD);
				mrdRecordDtlVO.setMrdRecordId(mrdRecordReturnDtlList.get(i).getMrdRecordId());
				mrdRecordDAO.updateRecordStatus(mrdRecordDtlVO, userVO);*/
			}
			
			for(int i=0;i<mrdRecordDtlVOList.size();i++)
			{
				if(mrdRecordDtlVOList.get(i).getChangeArchivedFlag().equals(MrdConfig.YES))
					mrdRecordDAO.updateReturnRecordArchived(mrdRecordDtlVOList.get(i), userVO);
				else
					mrdRecordDAO.updateReturnRecordInMrd(mrdRecordDtlVOList.get(i), userVO);
			}
			
			if(mrdRackShelfChangeList!=null)
			{
				for(int i=0;i<mrdRackShelfChangeList.size();i++)
				{
					mrdRackShelfChangeDAO.create(mrdRackShelfChangeList.get(i), userVO);
				}
			}	
			
			//if(isRequestReturn.equals(MrdConfig.YES))
			//{
				MrdRecordRequestDtlVO mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
				//mrdRecordRequestDtlVO.setReqStatus(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED);
				mrdRecordRequestDtlVO.setRequestId(mrdRecordReturnDtlList.get(0).getRequestId());
				mrdRecordRequestDtlVO.setRecordId(mrdRecordReturnDtlList.get(0).getMrdRecordId());
				mrdRecordRequestDAO.updateRecordRequestStatus(mrdRecordRequestDtlVO, userVO);		//for update in HPMRT_REQ_RECORD_DTL		
				mrdRecordRequestDAO.updateRequestStatus(mrdRecordRequestDtlVO, userVO);              // for update in HPMRT_MRDRECORD_REQ_DTL
			//}
			
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
	}
	
	public void saveMrdRecordLostDetail(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO();
		
		try
		{
			tx.begin();
			MrdRecordLostFoundDAOi mrdRecordLostDAO=new MrdRecordLostFoundDAO(tx);
			MRDRecordDtlDAOi mrdRecordDtlDAO= new MRDRecordDtlDAO(tx);
			
			mrdRecordLostDAO.insertLostDtl(mrdRecordLostVO, userVO);
			
			mrdRecordDtlVO.setMrdRecordId(mrdRecordLostVO.getMrdRecordId());
			mrdRecordDtlVO.setRecordStatus(MrdConfig.MRD_RECORD_STATUS_LOST);
			mrdRecordDtlDAO.updateRecordStatus(mrdRecordDtlVO, userVO);
			
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
	}
	
	public MrdRecordDtlVO[] getMrdRecordBasedOnShelfList(String recordType,String shelfId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordDtlVO[] arrMrdRecordDtlVO=null;
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDtlDAO= new MRDRecordDtlDAO(tx);
			arrMrdRecordDtlVO=mrdRecordDtlDAO.getMrdRecordBasedOnShelfList(recordType,shelfId,userVO);
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
			throw new HisDataAccessException(e.getMessage());
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
		return arrMrdRecordDtlVO;
	}
	
	public void saveRecordMovementDetail(List<MrdRackShelfChangeDtlVO> lstMrdRackShelfChangeVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mrdRecordDtlDAO= new MRDRecordDtlDAO(tx);
			MrdRackShelfChangeDtlDAOi mrdRackShelfChangeDAO=new MrdRackShelfChangeDtlDAO(tx);
			if(lstMrdRackShelfChangeVO.get(0).getMovementOptionFlag().equals("0"))
			{	
				for(int i=0;i<lstMrdRackShelfChangeVO.size();i++)
				{
					MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO();
					mrdRecordDtlVO.setMrdRecordId(lstMrdRackShelfChangeVO.get(i).getMrdRecordId());
					mrdRecordDtlVO.setRecordStatus("5");
					System.out.println("MrdBO.saveRecordMovementDetail() For Destroy");
					mrdRecordDtlDAO.destroyRecordDetail(mrdRecordDtlVO, userVO);
				}
			}
			else
			{
				for(int i=0;i<lstMrdRackShelfChangeVO.size();i++)
				{
					mrdRackShelfChangeDAO.create(lstMrdRackShelfChangeVO.get(i), userVO);
					
					MrdRecordDtlVO mrdRecordDtlVO=new MrdRecordDtlVO();
					mrdRecordDtlVO.setMrdCode(lstMrdRackShelfChangeVO.get(i).getToMrdCode());
					mrdRecordDtlVO.setRackId(lstMrdRackShelfChangeVO.get(i).getToRackId());
					mrdRecordDtlVO.setShelfId(lstMrdRackShelfChangeVO.get(i).getToShelfId());
					mrdRecordDtlVO.setMrdRecordId(lstMrdRackShelfChangeVO.get(i).getMrdRecordId());
					mrdRecordDtlVO.setPutBy(lstMrdRackShelfChangeVO.get(i).getPutBy());
					System.out.println("MrdBO.saveRecordMovementDetail() For Movement");
					mrdRecordDtlDAO.updateRecordMovementDetail(mrdRecordDtlVO, userVO);
				}				
			}			
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
	}

	public void saveRecordFoundNArchivalDetail(MrdRecordLostFoundDtlVO mrdFoundVO,MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdRecordLostFoundDAOi mrdRecordFoundDAO=new MrdRecordLostFoundDAO(tx);
			MRDRecordDtlDAOi mrdRecordDtlDAO= new MRDRecordDtlDAO(tx);
			
			mrdRecordFoundDAO.updateFoundDetail(mrdFoundVO,userVO);
			
			mrdRecordDtlDAO.updateRecordStatus(mrdRecordDtlVO, userVO);
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
	}
	
	public void deleteRejectedRecordDetail(String requestId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdRecordRequestDtlDAOi mrdRecordReqDAO=new MrdRecordRequestDtlDAO(tx);
			mrdRecordReqDAO.deleteRejectedRecordDetail(requestId,userVO);
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
	}
	
	
	public List getDepartmentUnitListForEMR(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List departmentUnitList=null;
		try
		{
			tx.begin();
			MrdEssentialDAOi essentialDAO=new MrdEssentialDAO(tx);
			departmentUnitList=essentialDAO.getDepartmentUnitForEmr(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			//throw new HisRecordNotFoundException(e.getMessage());
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
		return departmentUnitList;
	}
	
	public Map getListOfOpdFilesToMove(String _mrdCode, UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordDtlVO[] arrMrdRecordVO = null;
		Map essMap=new HashMap();
		List listHandOverTo=new ArrayList();
		String deptCode="";
		try
		{
			tx.begin();
			MRDRecordDtlDAOi mcDAO=new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			
			arrMrdRecordVO=mcDAO.getListOfOpdFilesToMove(_mrdCode,_userVO);
			essMap.put(MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_MOVEMENT, arrMrdRecordVO);
			
			listHandOverTo=mrdEssDAO.getRecordPutByList(deptCode,_userVO);
			essMap.put(MrdConfig.LIST_RECORD_HANDOVER_TO_FROM_MRD, listHandOverTo);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essMap);
		} catch (HisApplicationExecutionException e)
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
		return essMap;
	
	}

	public void saveOPDFileMovementDetail(MrdRecordDtlVO[] _mrdRecordVO,MrdRecordMovementVO[] _movementVO,UserVO _userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
	
		
		try
		{
			tx.begin();
			MRDRecordDtlDAOi recordDAO=new MRDRecordDtlDAO(tx);
			MrdRecordMovementDAOi movementDAO=new MrdRecordMovementDAO(tx);
			
			//updating record detail
			for(int i=0;i<_mrdRecordVO.length;i++)
			{
			recordDAO.updateRecordStatusAndIssueFlag(_mrdRecordVO[i],_userVO);
			}
			
			//inserting movement detail
			for(int i=0;i<_movementVO.length;i++)
			{
			movementDAO.create(_movementVO[i], _userVO);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		
		
	}

	public Map getListOfOpdFilesToReturn(String mrdCode,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		MrdRecordDtlVO[] arrMrdRecordVO = null;
		Map essMap=new HashMap();
		List listHandOverTo=new ArrayList();
		String deptCode="";
		try
		{
			tx.begin();
			
			MRDRecordDtlDAOi recordDAO=new MRDRecordDtlDAO(tx);
			MrdEssentialDAOi mrdEssDAO=new MrdEssentialDAO(tx);
			
			arrMrdRecordVO=recordDAO.getListOfOpdFilesToReturn(mrdCode,userVO);
			essMap.put(MrdConfig.ARRAY_MRD_RECORD_VOLIST_OPD_FILES_FOR_RETURN, arrMrdRecordVO);
			
			listHandOverTo=mrdEssDAO.getRecordPutByList(deptCode,userVO);
			essMap.put(MrdConfig.LIST_RECORD_HANDOVER_BY_IN_MRD, listHandOverTo);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return essMap;
	}
	
	//get patient vsisit summary for EMR
	public Map getPatientVisitSummaryForEMR(EpisodeVO episodeVO,String[] departmentUnitArray, String accessType,UserVO userVO) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		EpisodeSummaryDetailVO[] episodeSummaryDetailVO = null;
		Map essMap=new HashMap();
		PatAdmissionDtlVO [] patAdmissionDtlVOs=null;
		EpisodeRefDtlVO [] episodeRefVOs=null;
		try
		{
			tx.begin();
			
			EpisodeSummaryDAOi daoObj=new EpisodeSummaryDAO(tx);
			MrdEssentialDAOi mrdEssentialDAO=new MrdEssentialDAO(tx);
			EpisodeRefDtlDAOi episodeRefDtlDAO=new EpisodeRefDtlDAO(tx);
			
			List list=daoObj.getAllVisitSummaryByCrNo(episodeVO,departmentUnitArray,accessType, userVO);
			if(list.size()>0){
				episodeSummaryDetailVO=new EpisodeSummaryDetailVO[list.size()];
				episodeSummaryDetailVO=(EpisodeSummaryDetailVO[])list.toArray(episodeSummaryDetailVO);
			}
			essMap.put(MrdConfig.EPISODE_SUMMARY_VO_ARRAY, episodeSummaryDetailVO);
			
			List patAdmissionDtlVOList= mrdEssentialDAO.getPatientAdmissionDetailByCrNo(episodeVO.getPatCrNo(), userVO);
			if(patAdmissionDtlVOList.size()>0){
				patAdmissionDtlVOs=new PatAdmissionDtlVO[patAdmissionDtlVOList.size()];
				patAdmissionDtlVOs=(PatAdmissionDtlVO[])patAdmissionDtlVOList.toArray(patAdmissionDtlVOs);
			}
			essMap.put(MrdConfig.PAT_ADMISSION_DTL_VO_ARRAY, patAdmissionDtlVOs);
			
			EpisodeRefDtlVO episodeRefDtlVO=new EpisodeRefDtlVO();
			episodeRefDtlVO.setPatCrNo(episodeVO.getPatCrNo());
			episodeRefVOs=episodeRefDtlDAO.getEpisodeReferDtlByCrNoEmr(episodeRefDtlVO, userVO);
			essMap.put(MrdConfig.EPISODE_REF_DTL_VO_ARRAY, episodeRefVOs);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e)
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
		return essMap;
	}
	
	

	// Getting Patient CR Merger for EMR
	public List getPatientCRNoMergeList(CrNoMergeDtlVO voCRMerge, String[] departmentUnitArray, String accessType, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<CrNoMergeDtlVO> lstCRMerges = null;
		try
		{
			tx.begin();

			CrNoMergeDAOi recordDAO = new CrNoMergeDAO(tx);
			lstCRMerges = recordDAO.getMergedCrNoForEMR(voCRMerge.getPatMainCrNo(), userVO);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstCRMerges;
	}

	public void saveMedicalCertificateRequest(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) 
	{
		System.out.println("MrdBO.saveMedicalCertificateRequest()");
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			MedicalCertificateRequestDAO medicalCertificateReqDAO=new MedicalCertificateRequestDAO(tx);
			MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
				if(patMedicalDtlVO.getMedicalFitnessFlag().equals("0"))
				{
					String MedCertificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_MEDICAL,patMedicalDtlVO.getDepartmentUnitCode(),MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC ,userVO);
					medicalCertificateReqDAO.saveMedicalCertificateRequest("1", MedCertificateId, patMedicalDtlVO,userVO);
				}
				else if(patMedicalDtlVO.getMedicalFitnessFlag().equals("1"))
				{
					String FitCertificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_FITNESS,patMedicalDtlVO.getDepartmentUnitCode(),MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC ,userVO);
					medicalCertificateReqDAO.saveFitnessCertificateRequest("1", FitCertificateId, patMedicalDtlVO,userVO);
				}
		} 
		catch (NullPointerException e) {
			tx.rollback();
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException();
		}
		finally
		{
			tx.close();
		}
	}
	
	
	/**
	 * Saving Certificate B Entry Form Request Detail
	 * @author MAnisha Gangwar 16-Oct-2015
	 */
	public boolean saveCertBEntryReqDtl(CertificateBEntryFormReqVO certBEntryReqVO, UserVO voUser_p)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean bFlag = true;
		HisDAO hisDAO = new HisDAO("MRD", "MrdBO");
		try
		{
			tx.begin();
			MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
			CertificateBEntryFormDAO certBEntryDAO=new CertificateBEntryFormDAO(tx);
			
			String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_CERTIFICATE_B,"",MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC, voUser_p);
			
			certBEntryReqVO.setCertificateId(certificateId);;
			certBEntryDAO.dml(hisDAO, "1",certBEntryReqVO,voUser_p);

			/*synchronized (hisDAO)
			{
				hisDAO.fire();
			}*/
		}
		catch (HisRecordNotFoundException e)
		{
			bFlag = false;
			tx.rollback();
			throw new HisRecordNotFoundException("MrdBO.saveCertBEntryReqDtl()::HisRecordNotFoundException -> " + e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			bFlag = false;
			tx.rollback();
			throw new HisDataAccessException("MrdBO.saveCertBEntryReqDtl()::HisDataAccessException -> " + e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			bFlag = false;
			tx.rollback();
			throw new HisApplicationExecutionException("MrdBO.saveCertBEntryReqDtl()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		catch (HisException e)
		{
			bFlag = false;
			tx.rollback();
			throw new HisException("MrdBO.saveCertBEntryReqDtl()::HisException -> " + e.getMessage());
		}
		catch (Exception e)
		{
			bFlag = false;
			tx.rollback();
			throw new HisApplicationExecutionException("MrdBO.saveCertBEntryReqDtl()::HisApplicationExecutionException -> "
					+ e.getMessage());
		}
		finally
		{
			if (hisDAO != null)
			
				hisDAO.free();
				hisDAO = null;
			
			tx.close();
		}
		return bFlag;
	}
	
	
	// added by Manisha Gangwar on 20.10.2015 for saving Certificate B entry handover details & Bill status
		public void saveCertificateBHandoverDtl(CertificateBEntryFormReqVO certBEntryReqVO, UserVO voUser_p)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			boolean bFlag = true;
			HisDAO hisDAO = new HisDAO("MRD", "MrdBO");
			try
			{
				tx.begin();
				MrdEssentialDAO mrdEssDAO = new MrdEssentialDAO(tx);
				CertificateBEntryFormDAO certBEntryDAO=new CertificateBEntryFormDAO(tx);
				
				/*String certificateId = mrdEssDAO.generateCertificateId(MrdConfig.RECORD_TYPE_CERTIFICATE_B,"",MrdConfig.MEDICAL_CERIFICATE_REQUEST_GENMODE_AUTOMATIC, voUser_p);
				
				certBEntryReqVO.setCertificateId(certificateId);*/
				certBEntryDAO.dml(hisDAO, "2",certBEntryReqVO,voUser_p);

				/*synchronized (hisDAO)
				{
					hisDAO.fire();
				}*/
			}
			catch (HisRecordNotFoundException e)
			{
				bFlag = false;
				tx.rollback();
				throw new HisRecordNotFoundException("MrdBO.saveCertBEntryReqDtl()::HisRecordNotFoundException -> " + e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				bFlag = false;
				tx.rollback();
				throw new HisDataAccessException("MrdBO.saveCertBEntryReqDtl()::HisDataAccessException -> " + e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				bFlag = false;
				tx.rollback();
				throw new HisApplicationExecutionException("MrdBO.saveCertBEntryReqDtl()::HisApplicationExecutionException -> "
						+ e.getMessage());
			}
			catch (HisException e)
			{
				bFlag = false;
				tx.rollback();
				throw new HisException("MrdBO.saveCertBEntryReqDtl()::HisException -> " + e.getMessage());
			}
			catch (Exception e)
			{
				bFlag = false;
				tx.rollback();
				throw new HisApplicationExecutionException("MrdBO.saveCertBEntryReqDtl()::HisApplicationExecutionException -> "
						+ e.getMessage());
			}
			finally
			{
				if (hisDAO != null)
				
					hisDAO.free();
					hisDAO = null;
				
				tx.close();
			}
		
		}

		
		public JsonElement getPatientEMR(PatientVO _patientVO, UserVO _userVO,HttpServletRequest _request)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			JsonObject jsonObjEMR = new JsonObject();
			boolean presentEMR = false;
			boolean presentEMRSection = false;
			String temp = "";
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			try
			{
				tx.begin();
				
				// Patient Detail
				jsonObjEMR.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PATIENT_CRNO, _patientVO.getPatCrNo());
				JsonObject jsonObjEncounters = new JsonObject();
				
				// Treatment
				JsonObject jsonObjTreatment = new JsonObject();
				JsonObject jsonObjExTreatment = new JsonObject();
				presentEMRSection = false;
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = new PatDrugTreatmentDetailVO();
				PatDrugTreatmentDetailVO[] patExtTreatmentDetailVOs=null;
				PatDrugTreatmentDetailVO[] patDrugTreatmentDetailVOs=null;
				PatDrugTreatmentDetailDAOi patDrugTreatmentDetailDAOi=new PatDrugTreatmentDetailDAO(tx);
				patDrugTreatmentDetailVOs=patDrugTreatmentDetailDAOi.getTreatmentAdviceDetailsEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				patExtTreatmentDetailVOs=patDrugTreatmentDetailDAOi.getPateintExtTreatmentDetails(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				JsonArray arrTreatment = new JsonArray();
				JsonArray arrExTreatment = new JsonArray();
				if(patDrugTreatmentDetailVOs!=null && patDrugTreatmentDetailVOs.length>0){
					for(PatDrugTreatmentDetailVO vo : patDrugTreatmentDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMR = true;
						presentEMRSection = true;
						
						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtTreatment = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtTreatment);
						EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_DRUG_ADVICE_DETAILS_VO_ARRAY, patDrugTreatmentDetailVOs, _request);
						arrTreatment.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjTreatment);
					}
				jsonObjTreatment.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjTreatment.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrTreatment);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_TREATMENT, jsonObjTreatment);  //--- 1 TREATMENT
				}
				
				if(patExtTreatmentDetailVOs!=null && patExtTreatmentDetailVOs.length>0){
					for(PatDrugTreatmentDetailVO vo:patExtTreatmentDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMR = true;
						presentEMRSection = true;
						
						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtTreatment = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtTreatment);
						EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_EXT_TREATMENT_DETAILS_VO_ARRAY, patExtTreatmentDetailVOs, _request);
						arrExTreatment.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjExTreatment);
					}
					jsonObjExTreatment.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
					jsonObjExTreatment.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrExTreatment);
					
					jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_TREATMENT, jsonObjExTreatment);  //--- 1 TREATMENT
				}
				
				
				//System.out.println(jsonObjTreatment.toString());
				
				
				//Diagnosis
				JsonObject jsonObjDiagnosis = new JsonObject();
				presentEMRSection = false;

				JsonArray arrDiagnosis = new JsonArray();			
				EpisodeDiagnosisVO[] episodeDiagnosisVOs=null;
				EpisodeDiagnosisDAOi episodeDiagnosisDAOi=new EpisodeDiagnosisDAO(tx);
				episodeDiagnosisVOs=episodeDiagnosisDAOi.retrieveEpisodeDiagnosisForEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				if(episodeDiagnosisVOs!=null && episodeDiagnosisVOs.length>0)
					for(EpisodeDiagnosisVO vo : episodeDiagnosisVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtDiagnosis = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtDiagnosis);
						EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_DIAGNOSIS_DETAILS_VISIT_ARRAY, episodeDiagnosisVOs, _request);
						arrDiagnosis.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjDiagnosis);
					}
				jsonObjDiagnosis.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjDiagnosis.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrDiagnosis);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_DIAGNOSIS, jsonObjDiagnosis); //---2 DIAGNOSIS
				//System.out.println(jsonObjDiagnosis);
				

				//Allergies
				JsonObject jsonObjAllergies = new JsonObject();
				presentEMRSection = false;
				JsonArray arrAllergy = new JsonArray();
				EpisodeAllergiesVO _episodeAllergiesVO = new EpisodeAllergiesVO();
				_episodeAllergiesVO.setPatCrNo(_patientVO.getPatCrNo());
				EpisodeAllergiesVO[] episodeAllergiesVOs=null;
				EpisodeAllergiesDAO episodeAllergiesDAO=new EpisodeAllergiesDAO(tx);
				episodeAllergiesVOs=episodeAllergiesDAO.getAllergyDtlAll(_episodeAllergiesVO,new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				if(episodeAllergiesVOs!=null && episodeAllergiesVOs.length>0)
					for(EpisodeAllergiesVO vo : episodeAllergiesVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtAllergy = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtAllergy);
						EmrCommonDeskUTL.setPatientRecordMap(_episodeAllergiesVO.getPatCrNo(), MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY,episodeAllergiesVOs,_request);
						arrAllergy.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjAllergies);
					}
				jsonObjAllergies.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjAllergies.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrAllergy);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_ALLERGY, jsonObjAllergies); //---3 ALLERGIES
				
				//System.out.println(jsonObjAllergies);
				
				//INVESTIGATION
				JsonObject jsonObjInvestigation = new JsonObject();
				presentEMRSection = false;

				JsonArray arrInvestigation = new JsonArray();
				ProfileInvestigationVO[] profileInvestigationVOs=null;
				OpdEssentialDAOi opdEssentialDAOi=new OpdEssentialDAO(tx);
				profileInvestigationVOs=opdEssentialDAOi.getPatientInvestigationDetailsEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES,_userVO);
					
				if(profileInvestigationVOs!=null && profileInvestigationVOs.length>0)
					for(ProfileInvestigationVO vo : profileInvestigationVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtInvestigation = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtInvestigation);
						EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_INVESTIGATION_DETAILS_VO_ARRAY, profileInvestigationVOs, _request);
						arrInvestigation.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjInvestigation);
					}
				jsonObjInvestigation.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjInvestigation.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrInvestigation);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_INVESTIGATION, jsonObjInvestigation); //--4 INVESTIGATION
				
				
				//CHRONICDISEASE
				JsonObject jsonObjChronic = new JsonObject();
				presentEMRSection = false;

				JsonArray arrChronic = new JsonArray();
				PatientAlertsDetailVO[] patientAlertsDetailVOs=null;
				PatAlertsDetailDAOi patAlertsDetailDAOi=new PatAlertsDetailDAO(tx);
				patientAlertsDetailVOs=patAlertsDetailDAOi.getPatientAlertEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
						
				if(patientAlertsDetailVOs!=null && patientAlertsDetailVOs.length>0)
					for(PatientAlertsDetailVO vo : patientAlertsDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtChronic = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtChronic);
						EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_CHRONIC_DISEASE_ARRAY, patientAlertsDetailVOs, _request);
						arrChronic.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjChronic);
					}
				jsonObjChronic.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjChronic.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrChronic);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_CHRONIC_DISEASE, jsonObjChronic); //-- 5 CHRONICDISEASE
				
				//INTAKEOUTTAKE
				JsonObject jsonObjInOuttake = new JsonObject();
				presentEMRSection = false;
				
				JsonObject jsonObjIntake = new JsonObject();
				JsonObject jsonObjOuttake = new JsonObject();

				JsonArray arrIntake = new JsonArray();
				JsonArray arrOuttake = new JsonArray();
				PatIntakeOutDtlVO[] arrPatOuttakeDtlVOs=null;
				PatIntakeOutDtlVO[] arrPatIntakeDtlVOs=null;
				PatIntakeOutDtlDAOi patIntakeOutDAO=new PatIntakeOutDtlDAO(tx);
				PatientDetailVO dailyPatVO=new PatientDetailVO();
				dailyPatVO.setPatCrNo(_patientVO.getPatCrNo());
				arrPatIntakeDtlVOs=patIntakeOutDAO.getOutParaDetailEMR(InpatientConfig.INTAKEOUT_MODE_INTAKE,dailyPatVO,new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
					
				arrPatOuttakeDtlVOs=patIntakeOutDAO.getOutParaDetailEMR(InpatientConfig.INTAKEOUT_MODE_OUTTAKE,dailyPatVO,new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
						
				if(arrPatIntakeDtlVOs!=null && arrPatIntakeDtlVOs.length>0)
					for(PatIntakeOutDtlVO vo : arrPatIntakeDtlVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtIntake = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtIntake);
						
						arrIntake.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjIntake);
						setEHRSectionPresentOnject(episodeVO, jsonObjInOuttake);
					}
				jsonObjIntake.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjIntake.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrIntake);
				
				if(arrPatOuttakeDtlVOs!=null && arrPatOuttakeDtlVOs.length>0)
					for(PatIntakeOutDtlVO vo : arrPatOuttakeDtlVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtOuttake = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtOuttake);
						
						arrOuttake.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjOuttake);
						setEHRSectionPresentOnject(episodeVO, jsonObjInOuttake);
					}
				jsonObjOuttake.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjOuttake.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrOuttake);
				
				jsonObjInOuttake.add(EHRConfig.EHR_SECTION_KEY_INTAKE, jsonObjIntake);
				jsonObjInOuttake.add(EHRConfig.EHR_SECTION_KEY_OUTTAKE, jsonObjOuttake);		
				
				
				jsonObjInOuttake.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_INTAKE_OUTTAKE, jsonObjInOuttake);  // -- 6 INTAKEOUTTAKE
				
				
				//PROGRESSNOTES
				JsonObject jsonObjPNotes = new JsonObject();
				presentEMRSection = false;

				JsonArray arrPNotes = new JsonArray();
				DoctorRoundDtlVO[] doctorRoundDtlVOs = null;
				DoctorRoundDtlDAOi doctorRoundDtlDAOi = new DoctorRoundDtlDAO(tx);
				doctorRoundDtlVOs = doctorRoundDtlDAOi.getPatientProgressNotesEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);	
				if(doctorRoundDtlVOs!=null && doctorRoundDtlVOs.length>0)
					for(DoctorRoundDtlVO vo : doctorRoundDtlVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtPNotes = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtPNotes);
						
						arrPNotes.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjPNotes);
					}
				jsonObjPNotes.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjPNotes.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrPNotes);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_PROGRESS_NOTES, jsonObjPNotes);  // -- 7 PROGRESSNOTES
				
				//VITALS
				JsonObject jsonObjVitals = new JsonObject();
				presentEMRSection = false;

				JsonArray arrVitals = new JsonArray();
				PatientClinicalDetailVO _patientClinicalDetailVO = new PatientClinicalDetailVO();
				_patientClinicalDetailVO.setPatCrNo(_patientVO.getPatCrNo());
				PatientClinicalDetailVO[] patientClinicalDetailVOs = null;
				PatientClinicalDetailDAOi patientClinicalDetailDAOi = new PatientClinicalDetailDAO(tx);
				patientClinicalDetailVOs = patientClinicalDetailDAOi.getPatientVitalsEMR(_patientClinicalDetailVO,new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				if(patientClinicalDetailVOs!=null && patientClinicalDetailVOs.length>0)
					for(PatientClinicalDetailVO vo : patientClinicalDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtVitals = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtVitals);
						
						arrVitals.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjVitals);
					}
				jsonObjVitals.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjVitals.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrVitals);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_VITALS, jsonObjVitals); // -- 8 VITALS
				
							
				
				//EXTINESTIGATION
				JsonObject jsonObjExtInvestigation = new JsonObject();
				presentEMRSection = false;

				JsonArray arrExtInvestigation = new JsonArray();
				EpisodeExtInvDtlVO[] episodeExtInvDtlVOs = null;
				EpisodeExtInvDtlDAOi episodeExtInvDtlDAOi = new EpisodeExtInvDtlDAO(tx);
				episodeExtInvDtlVOs = episodeExtInvDtlDAOi.getPatientExternalInvDetailEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
					
				if(episodeExtInvDtlVOs!=null && episodeExtInvDtlVOs.length>0)
					for(EpisodeExtInvDtlVO vo : episodeExtInvDtlVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						if(vo.getPatAdmNo()!=null)
							episodeVO.setAdmissionNo(vo.getPatAdmNo());
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtExtInvestigation = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtExtInvestigation);
						
						arrExtInvestigation.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjExtInvestigation);
					}
				jsonObjExtInvestigation.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjExtInvestigation.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrExtInvestigation);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_EXTINESTIGATION, jsonObjExtInvestigation); // -- 9 EXTINESTIGATION
				
				
				//COMPLAINTS
				JsonObject jsonObjComplaints = new JsonObject();
				presentEMRSection = false;

				JsonArray arrComplaints = new JsonArray();
				patientClinicalDetailVOs = null;
				patientClinicalDetailDAOi = new PatientClinicalDetailDAO(tx);
				patientClinicalDetailVOs = patientClinicalDetailDAOi.getPatientComplaintsByCrNoEMR(_patientClinicalDetailVO,GenericTemplateConfig.TEMPLATE_CATEGORY_COMPLAINT, _userVO);
				
				if(patientClinicalDetailVOs!=null && patientClinicalDetailVOs.length>0)
					for(PatientClinicalDetailVO vo : patientClinicalDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtComplaints = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtComplaints);
						
						arrComplaints.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjComplaints);
					}
				jsonObjComplaints.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjComplaints.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrComplaints);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_COMPLAINTS, jsonObjComplaints); //-- 10 COMPLAINTS
				
				//HISTORY
				JsonObject jsonObjHistory = new JsonObject();
				presentEMRSection = false;

				JsonArray arrHistory = new JsonArray();
				patientClinicalDetailVOs = null;
				patientClinicalDetailDAOi = new PatientClinicalDetailDAO(tx);
				patientClinicalDetailVOs = patientClinicalDetailDAOi.getPatientComplaintsByCrNoEMR(_patientClinicalDetailVO,GenericTemplateConfig.TEMPLATE_CATEGORY_HISTORY, _userVO);
				
				if(patientClinicalDetailVOs!=null && patientClinicalDetailVOs.length>0)
					for(PatientClinicalDetailVO vo : patientClinicalDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtHistory = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtHistory);
						
						arrHistory.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjHistory);
					}
				jsonObjHistory.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjHistory.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrHistory);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_HISTORY, jsonObjHistory); // -- 11 HISTORY
				
				//EXAMINATION
				JsonObject jsonObjExamination = new JsonObject();
				presentEMRSection = false;

				JsonArray arrExamination = new JsonArray();
				patientClinicalDetailVOs = null;
				patientClinicalDetailDAOi = new PatientClinicalDetailDAO(tx);
				patientClinicalDetailVOs = patientClinicalDetailDAOi.getPatientComplaintsByCrNoEMR(_patientClinicalDetailVO,GenericTemplateConfig.TEMPLATE_CATEGORY_EXAMINATION, _userVO);
				
				if(patientClinicalDetailVOs!=null && patientClinicalDetailVOs.length>0)
					for(PatientClinicalDetailVO vo : patientClinicalDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtExamination = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtExamination);
						
						arrExamination.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjExamination);
					}
				jsonObjExamination.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjExamination.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrExamination);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_EXAMINATION, jsonObjExamination); //-- 12 EXAMINATION
				
				//PROFILE
				JsonObject jsonObjProfile = new JsonObject();
				presentEMRSection = false;
				DocumentUploadDtlVO[] documentUploadVOs=null;
				//JsonObject jsonObjDocUpload = new JsonObject();
				//JsonArray arrDocUpload = new JsonArray();
				
				JsonArray arrProfile = new JsonArray();
				PatientProfileDetailVO[] lstPatProfiles = null;
				MrdDAO  mrdDao = new MrdDAO(tx);
				lstPatProfiles=mrdDao.retrieveProfileDetailsForEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				documentUploadVOs=mrdDao.retrieveDocumentUploadForEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				
				if( (lstPatProfiles!=null && lstPatProfiles.length>0) || (documentUploadVOs!=null && documentUploadVOs.length>0))
				{
					if(lstPatProfiles!=null && lstPatProfiles.length>0)
					for(PatientProfileDetailVO vo : lstPatProfiles)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtProfile = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtProfile);
						
						arrProfile.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjProfile);
					}
					
					
					if((documentUploadVOs!=null && documentUploadVOs.length>0)){
					
					for(DocumentUploadDtlVO vo : documentUploadVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;
						
						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtDocUpload = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtDocUpload);
						EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, episodeDiagnosisVOs, _request);
						arrProfile.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjProfile);
					}
					
				}
					
					
					jsonObjProfile.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
					jsonObjProfile.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrProfile);
					
					jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_PROFILE, jsonObjProfile);//-- 13 PROFILE
				}
				
				
				
				
				//OPERATION
				JsonObject jsonObjOperation = new JsonObject();
				presentEMRSection = false;

				JsonArray arrOperation = new JsonArray();
				List<ProfileOTDtlVO> listOperationPerformed=null;
				opdEssentialDAOi=new OpdEssentialDAO(tx);
				listOperationPerformed = opdEssentialDAOi.getOperationPerformedListEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
					
				if(listOperationPerformed!=null)
					for(ProfileOTDtlVO vo : listOperationPerformed)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtOperation = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtOperation);
						
						arrOperation.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjOperation);
					}
				jsonObjOperation.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjOperation.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrOperation);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_OPERATION, jsonObjOperation);
				
				
				//ANCDETAIL
				JsonObject jsonObjAnc = new JsonObject();
				presentEMRSection = false;

				JsonArray arrAnc = new JsonArray();
				ANCDetailVO _ancDetailVO = new ANCDetailVO();
				_ancDetailVO.setPatCrNo(_patientVO.getPatCrNo());
				ANCDetailVO[] ancDetailVOs=null;
				ANCDtlDAOi ancDetailDAO=new ANCDtlDAO(tx);
				ancDetailVOs=ancDetailDAO.getPatientAncDetailsForEMR(_ancDetailVO,new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
						
				if(ancDetailVOs!=null && ancDetailVOs.length>0)
					for(ANCDetailVO vo : ancDetailVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtAnc = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtAnc);
						
						arrAnc.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjAnc);
					}
				jsonObjAnc.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjAnc.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrAnc);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_ANCDETAIL, jsonObjAnc);
				
				//VISITREASON
				JsonObject jsonObjVisitRsn = new JsonObject();
				presentEMRSection = false;

				JsonArray arrVisitRsn = new JsonArray();
				VisitReasonsVO[] visitReasonsVOs=null;
				mrdDao = new MrdDAO(tx);
				visitReasonsVOs=mrdDao.retrieveVisitReasonsForEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
						
				if(visitReasonsVOs!=null && visitReasonsVOs.length>0)
					for(VisitReasonsVO vo : visitReasonsVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtVisitRsn = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtVisitRsn);
						
						arrVisitRsn.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjVisitRsn);
					}
				jsonObjVisitRsn.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjVisitRsn.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrVisitRsn);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_VISITREASON, jsonObjVisitRsn);
				
				
				//SERVICEAREA
				JsonObject jsonObjSrvArea = new JsonObject();
				presentEMRSection = false;

				JsonArray arrSrvArea = new JsonArray();
				Service_Req_dtlVO[] serviceReqDtlVOs=null;
				mrdDao = new MrdDAO(tx);
				serviceReqDtlVOs=mrdDao.retrieveServiceProceduresForEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
						
				if(serviceReqDtlVOs!=null && serviceReqDtlVOs.length>0)
					for(Service_Req_dtlVO vo : serviceReqDtlVOs)
					{
						JsonObject jsonObj =  new JsonObject();
						presentEMRSection = true;

						EpisodeVO episodeVO = new EpisodeVO();
						HelperMethods.populate(episodeVO, vo);
						temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						JsonElement jsonElemEpi = parser.parse(temp);
						jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						temp = gson.toJson(vo);
						//System.out.println(temp);
						JsonElement jsonElemtSrvArea = parser.parse(temp);
					//	jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtSrvArea);
						
						arrSrvArea.add(jsonObj);
						
						// Setting Counters 
						setEHRSectionPresentOnject(episodeVO, jsonObjSrvArea);
					}
				jsonObjSrvArea.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				jsonObjSrvArea.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrSrvArea);
				
				jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_SERVICEAREA, jsonObjSrvArea);
				
				
				//Document Upload
				//JsonObject jsonObjDocUpload = new JsonObject();
				//presentEMRSection = false;

				//JsonArray arrDocUpload = new JsonArray();			
				//DocumentUploadDtlVO[] documentUploadVOs=null;
				//mrdDao = new MrdDAO(tx);
				//documentUploadVOs=mrdDao.retrieveDocumentUploadForEMR(_patientVO.getPatCrNo(),new String[0],MrdConfig.EPR_DISPLAY_ALL_RECORD_YES, _userVO);
				
				//if(documentUploadVOs!=null && documentUploadVOs.length>0)
					//for(DocumentUploadDtlVO vo : documentUploadVOs)
					//{
						//JsonObject jsonObj =  new JsonObject();
						//presentEMRSection = true;

						//EpisodeVO episodeVO = new EpisodeVO();
						//HelperMethods.populate(episodeVO, vo);
						//temp = gson.toJson(episodeVO);
						//System.out.println(temp);
						//JsonElement jsonElemEpi = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_SECTION_KEY_ENCOUNTER, jsonElemEpi);
						
						//temp = gson.toJson(vo);
						//System.out.println(temp);
						//JsonElement jsonElemtDocUpload = parser.parse(temp);
						//jsonObj.add(EHRConfig.EHR_OBJECT_FIELD_NAME_SECTION_DATA, jsonElemtDocUpload);
						//EmrCommonDeskUTL.setPatientRecordMap(_patientVO.getPatCrNo(), MrdConfig.PAT_DOCUMENT_UPLOAD_ARRAY, episodeDiagnosisVOs, _request);
						//arrDocUpload.add(jsonObj);
						
						// Setting Counters 
						//setEHRSectionPresentOnject(episodeVO, jsonObjDocUpload);
					//}
				//jsonObjDocUpload.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, presentEMRSection);
				//jsonObjDocUpload.add(EHRConfig.EHR_OBJECT_FIELD_NAME_LIST_DATA, arrDocUpload);
				
				//jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_DOCUMENT_ARCHIVAL, jsonObjDocUpload);
				//jsonObjEMR.add(EHRConfig.EHR_SECTION_KEY_PROFILE, jsonObjDocUpload);//Added by Shweta On 13-May-2019 
				
				
				System.out.println(jsonObjEMR);
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println("inside  BO HisApplicationExecutionException");
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println("inside  BO HisDataAccessException");
				e.printStackTrace();
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println("inside  BO HisApplicationExecutionException");
				e.printStackTrace();
				tx.rollback();
				//throw new HisApplicationExecutionException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
			return jsonObjEMR;
		}
		
		private void setEHRSectionPresentOnject(EpisodeVO vo, JsonObject jsonSectionObj)
		{
			JsonObject jsonObjHospital =  null;
			if(jsonSectionObj.has("H"+vo.getHospitalCode()))
			{
				jsonObjHospital = jsonSectionObj.getAsJsonObject("H"+vo.getHospitalCode());
			}
			else
			{
				jsonObjHospital = new JsonObject();
				jsonObjHospital.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
				jsonSectionObj.add("H"+vo.getHospitalCode(), jsonObjHospital);
			}
			
			if(vo.getAdmissionNo()==null || vo.getAdmissionNo().trim().equals(""))
			{
				JsonObject jsonObjOpd =  null;
				if(jsonObjHospital.has("O"))
				{
					jsonObjOpd = jsonObjHospital.getAsJsonObject("O");
				}
				else
				{
					jsonObjOpd = new JsonObject();
					jsonObjOpd.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
					jsonObjHospital.add("O", jsonObjOpd);
				}
				
				JsonObject jsonObjDeptUnit = null;
				if(jsonObjOpd.has("U"+vo.getDepartmentUnitCode()))
				{
					jsonObjDeptUnit = jsonObjOpd.getAsJsonObject("U"+vo.getDepartmentUnitCode());
				}
				else
				{
					jsonObjDeptUnit = new JsonObject();
					jsonObjDeptUnit.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
					jsonObjOpd.add("U"+vo.getDepartmentUnitCode(), jsonObjDeptUnit);
				}

				JsonObject jsonObjEpisode = null;
				if(jsonObjDeptUnit.has("E"+vo.getEpisodeCode()))
				{
					jsonObjEpisode = jsonObjDeptUnit.getAsJsonObject("E"+vo.getEpisodeCode());
				}
				else
				{
					jsonObjEpisode = new JsonObject();
					jsonObjEpisode.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
					jsonObjDeptUnit.add("E"+vo.getEpisodeCode(), jsonObjEpisode);
				}

				JsonObject jsonObjVisit = null;
				if(jsonObjEpisode.has("V"+vo.getEpisodeVisitNo()))
				{
					jsonObjVisit = jsonObjEpisode.getAsJsonObject("V"+vo.getEpisodeVisitNo());
				}
				else
				{
					jsonObjVisit = new JsonObject();
					jsonObjVisit.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
					jsonObjEpisode.add("V"+vo.getEpisodeVisitNo(), jsonObjVisit);
				}
			}
			else if(vo.getAdmissionNo()!=null && !vo.getAdmissionNo().trim().equals(""))
			{
				JsonObject jsonObjIpd =  null;
				if(jsonObjHospital.has("I"))
				{
					jsonObjIpd = jsonObjHospital.getAsJsonObject("I");
				}
				else
				{
					jsonObjIpd = new JsonObject();
					jsonObjIpd.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
					jsonObjHospital.add("I", jsonObjIpd);
				}

				JsonObject jsonObjAdm =  null;
				if(jsonObjIpd.has("A"+vo.getAdmissionNo()))
				{
					jsonObjAdm = jsonObjIpd.getAsJsonObject("A"+vo.getAdmissionNo());
				}
				else
				{
					jsonObjAdm = new JsonObject();
					jsonObjAdm.addProperty(EHRConfig.EHR_OBJECT_FIELD_NAME_PRESENT, true);
					jsonObjIpd.add("A"+vo.getAdmissionNo(), jsonObjAdm);
				}
			}
		}
		
		
		public VisitReasonsVO[] getAllVisitDetails(VisitReasonsVO _visitReasonVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			VisitReasonsVO[] visitReasonsVOs=null;
			try
			{
				tx.begin();
				MrdDAO mrdDao = new MrdDAO(tx);
				visitReasonsVOs=mrdDao.retrieveVisitReasonsForEMR(_visitReasonVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
				
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
			return visitReasonsVOs;
		}
		
		
		public Service_Req_dtlVO[] getAllServiceProcedures(Service_Req_dtlVO _serviceReqDtlVOs,String[] departmentUnitArray, String accessType,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Service_Req_dtlVO[] serviceReqDtlVOs=null;
			try
			{
				tx.begin();
				MrdDAO mrdDao = new MrdDAO(tx);
				serviceReqDtlVOs=mrdDao.retrieveServiceProceduresForEMR(_serviceReqDtlVOs.getPatCrNo(),departmentUnitArray,accessType, _userVO);
				
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
			return serviceReqDtlVOs;
		}
		
		public void saveExtendDays(MrdRecordRequestDtlVO mrdRecordRequestVO,
				UserVO userVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
		
			
			try
			{
				tx.begin();
				MRDRecordDtlDAOi recordDAO=new MRDRecordDtlDAO(tx);
				
				recordDAO.saveExtendDays(mrdRecordRequestVO, userVO);
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			} catch (HisApplicationExecutionException e)
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
			
			
		}

		public MrdRecordRequestDtlVO getEssentials(MrdRecordRequestDtlVO mrdRecordRequestVO,
				UserVO userVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			MrdRecordRequestDtlVO mrdRecordRequestVOO  = new MrdRecordRequestDtlVO();
			
			try
			{
				tx.begin();
				MRDRecordDtlDAOi recordDAO=new MRDRecordDtlDAO(tx);
				
				mrdRecordRequestVOO = recordDAO.getEssentials(mrdRecordRequestVO, userVO);
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			} catch (HisApplicationExecutionException e)
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
			
			return mrdRecordRequestVOO;
		}

		public void updateSL_NO(MrdRecordRequestDtlVO mrdRecordRequestVO,
				UserVO userVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
		
			
			try
			{
				tx.begin();
				MRDRecordDtlDAOi recordDAO=new MRDRecordDtlDAO(tx);
				
				recordDAO.updateSL_NO(mrdRecordRequestVO, userVO);
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			} catch (HisApplicationExecutionException e)
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
			
			
		}
		public DocumentUploadDtlVO[] getAllDocUploaded(DocumentUploadDtlVO _documentUploadDtlVO,String[] departmentUnitArray, String accessType,UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			DocumentUploadDtlVO[] documentUploadDtlVOs=null;
			try
			{
				tx.begin();
				MrdDAO mrdDao = new MrdDAO(tx);
				documentUploadDtlVOs=mrdDao.retrieveDocumentUploadForEMR(_documentUploadDtlVO.getPatCrNo(),departmentUnitArray,accessType, _userVO);
				
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
			return documentUploadDtlVOs;
		}
		
		
		public void savePatientMRDDocumentdetails(MRDDocumentUploadVO[] docUploadVos,MRDDocumentUploadVO[] documentUploadRevokeDtlVO, UserVO userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			String count;
			String newFileName="";
			int i = 0;
			
			byte [] docFile = null; //added by Dheeraj on 27-Sept-2018
			
			try
			{
				MRDDocumentUploadDAO docUploadDaoi = new MRDDocumentUploadDAO(tx);
				tx.begin();
				if( (docUploadVos!=null) && (docUploadVos.length>0) )
					{
					count=docUploadDaoi.getContCrNoWise(docUploadVos[0].getPatCrNo(), userVO);
					//int countInt=Integer.parseInt(count)+10;
					int countInt=Integer.parseInt(count);
					for (; i < docUploadVos.length; i++)
					{
						String sysdate=docUploadVos[i].getEntryDate();
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
						String[] filePartName={docUploadVos[i].getPatCrNo(),newSysdate,String.valueOf(countInt)};
						String fileName=HelperMethods.getFileName('$',filePartName);
						String fileExt=docUploadVos[i].getDocumentName().substring(docUploadVos[i].getDocumentName().lastIndexOf("."));
						fileExt = fileExt.substring(1);
						fileName=fileName+fileExt;
						newFileName= docUploadVos[i].getPatCrNo()+"_"+countInt;
						//String docId="";
						//docId=docUploadVos[i].getPatCrNo()+"_"+countInt;
						//docUploadVos[i].setDocumentCode(docId);
						docUploadVos[i].setDocumentName(newFileName);
						docUploadVos[i].setDocumentDirectoryPath(fileExt);
						docUploadVos[i].setDocSlNo(""+countInt);
						
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
						docUploadDaoi.create(docUploadVos[i], userVO);
						//docUploadDaoi.update(docUploadVos[i], userVO);
						
						}
					}
				if(documentUploadRevokeDtlVO!=null)
				{
					for(int j=0;j<documentUploadRevokeDtlVO.length;j++)
					{
						docUploadDaoi.revokeDocument(documentUploadRevokeDtlVO[j], userVO);
					}
				}
				
				//Added by Dheeraj on 27-Sept-2018  to add data to postgres
				MRDDocumentUploadVO mrdDocumentUploadVO = new MRDDocumentUploadVO();
				
				for (i = 0; i < docUploadVos.length; i++)
				{
					
					mrdDocumentUploadVO.setPatCrNo(docUploadVos[i].getPatCrNo());
					mrdDocumentUploadVO.setEpisodeCode(docUploadVos[i].getEpisodeCode());
					mrdDocumentUploadVO.setDocFile(docUploadVos[i].getDocFile());
					mrdDocumentUploadVO.setDocSlNo(docUploadVos[i].getDocSlNo());
				
				
				docFile = docUploadVos[i].getDocFile();
				
				
				boolean flag =true;
				if(flag)
				{
					mrdDocumentUploadVO.setPathForWindows(Config.PATIENT_IMAGE_FILE_STORAGE_PATH); 
				}
				else
				{
					mrdDocumentUploadVO.setPathPathLinux(Config.PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX); 
				}
				
				mrdDocumentUploadVO.setIsValid(Config.IS_VALID_ACTIVE);
				}
			
				docUploadDaoi.saveImageToPostgres(mrdDocumentUploadVO,docFile,userVO);
				docUploadDaoi.setUploadedStatus(mrdDocumentUploadVO,docFile,userVO);
				
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
				

			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
		}

		
		// Added by Dheeraj on 27-Sept-2018 to fetch Document from Postgres
		public byte[] fetchImageFromPostgres(MRDDocumentUploadVO mrdDocumentUploadVO) {
			JDBCTransactionContext tx = new JDBCTransactionContext();
			HisDAO objHisDAO = null;
			byte[] getDoc =null;
			try
			{
				objHisDAO = new HisDAO("OPD","PatientBO");
				MRDDocumentUploadDAO mrdDocumentUploadDAO = new MRDDocumentUploadDAO(tx);
				tx.begin();
				
				 getDoc= mrdDocumentUploadDAO.latestFetchImagePostgres(mrdDocumentUploadVO.getPatCrNo());
				

			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
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

		//Added by Vasu on 07.March.2019
		public void deleteIcdIndexDetail(MrdIcdDtlVO lcdMrdVo, UserVO userVO) 
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			try
			{
				tx.begin();
			   MrdEssentialDAO essentialDAO = new MrdEssentialDAO(tx);
			   
			   essentialDAO.deleteIcdIndexDetail(lcdMrdVo,userVO);
			
			}
			catch (HisApplicationExecutionException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				System.out.println(e.getMessage());
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				tx.rollback();
			}
			finally
			{
				tx.close();
			}
			
		}
		
		//Added By Shweta on 09-MAY-2019
		public List<DocumentUploadDtlVO> getDocumentArchivalEssentials(String patCrNo,String episodeCode, UserVO _userVO)
		{
		//DocumentUploadDtlVO[] documentUploadDtlVOs = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<DocumentUploadDtlVO> lstdocuploaded=null;
		Map essMap = new HashMap();
		try
		{
			PatientProfileDetailDAOi dao = new PatientProfileDetailDAO(tx);
			tx.begin();
			lstdocuploaded = dao.getDocumentUploadEssentials(patCrNo,episodeCode, _userVO);
			//essMap.put(OpdConfig.DOCUMENT_DTL_VO_ARRAY, documentUploadDtlVOs); 
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essMap);
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstdocuploaded;
		}

		public RecordDispatchDtlVO[] getRecordListByPatAdmNo(RecordDispatchDtlVO recordDispatchVO,UserVO userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			RecordDispatchDtlVO [] recordDispatchArray=null;
			try
			{
				tx.begin();
				MrdDAOi mrdDAO=new MrdDAO(tx);
				recordDispatchArray=mrdDAO.getRecordListByPatAdmNo(recordDispatchVO, userVO);
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
				throw new HisDataAccessException(e.getMessage());
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
			return recordDispatchArray;
		}
		

	}

