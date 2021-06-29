package mrd.transaction.controller.utl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.IssueCertificateDATA;
import mrd.transaction.controller.fb.IssueCertificateFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;

public class IssueCertificateUTL extends ControllerUTIL
{
	/** Getting the List of Certificate
	 * @param fb
	 * @param request
	 */
	public static void getGeneratedCertificateList(IssueCertificateFB fb,HttpServletRequest request)
	{
		Status  objStatus=new Status();
		PatMedicalDtlVO[] patMedDtlVO=null;
		PatFitnessDtlVO[] patFitDtlVO=null;
		PatMedicalDtlVO[] patMedDupDtlVO=null;
		PatFitnessDtlVO[] patFitDupDtlVO=null;
		String str="";
		
		try
		{
			//// Getting the List of Generated Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE))
			{
				patMedDtlVO=IssueCertificateDATA.getAllGeneratedMCList(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ALL_GENERATED_MC_OF_PATIENT, patMedDtlVO);
				if(patMedDtlVO.length==0)
					str="Medical Certificate Not Generated ";
			}
			
			//// Getting the List of Generated Fitness Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE))
			{
				patFitDtlVO=IssueCertificateDATA.getAllGeneratedFCList(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ALL_GENERATED_FC_OF_PATIENT, patFitDtlVO);
				if(patFitDtlVO.length==0)
					str="Fitness Certificate Not Generated ";
			}
			
			//// Getting the List of Duplicate Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_DUPLICATE_MEDICAL_CERTIFICATE))
			{
				patMedDupDtlVO=IssueCertificateDATA.getAllDuplicateMCList(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ALL_GENERATED_DUPLICATE_MC_OF_PATIENT, patMedDupDtlVO);
				if(patMedDupDtlVO.length==0)
					str="No Medical Certificate Issued To The Patient ";
			}
				
			////Getting the List of Duplicate Fitness Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_DUPLICATE_FITNESS_CERTIFICATE))
			{
				patFitDupDtlVO=IssueCertificateDATA.getAllDuplicateFCList(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ALL_GENERATED_DUPLICATE_FC_OF_PATIENT, patFitDupDtlVO);
				if(patFitDupDtlVO.length==0)
					str="No Fitness Certificate Issued To The Patient ";
			}
			
			
			objStatus.add(Status.LIST,"",str);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}	
	}
	
	
	/** Getting the Detail of a Certificate
	 * @param fb
	 * @param request
	 */
	public static void getMedicalCertificateDtl(IssueCertificateFB fb,HttpServletRequest request)
	{
		Status  objStatus=new Status();
		HttpSession session=request.getSession(); 
		
		try
		{
			////Getting the Detail of a Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE))
			{
				String mcNo=fb.getSelectedGenMC();
				PatMedicalDtlVO[] patMedDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_MC_OF_PATIENT);
				for(int i=0;i<patMedDtlVO.length;i++)
				{
					if(mcNo.equals(patMedDtlVO[i].getMedicalCertificateId()))
					{
						fb.setSufferingFrom(patMedDtlVO[i].getSufferingFrom());
						fb.setAdviceDays(patMedDtlVO[i].getAdviceDays());
						fb.setFromDate(patMedDtlVO[i].getFromDate());
						fb.setToDate(patMedDtlVO[i].getToDate());
					}
				}
			}
			
			////Getting the Detail of a Fitness Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE))
			{
				String fcNo=fb.getSelectedGenFC();
				PatFitnessDtlVO[] patFitDtlVO=(PatFitnessDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_FC_OF_PATIENT);
				for(int i=0;i<patFitDtlVO.length;i++)
				{
					if(fcNo.equals(patFitDtlVO[i].getFitnessCertificateId()))
					{
						fb.setSufferingFrom(patFitDtlVO[i].getSufferingFrom());
						fb.setFitnessDate(patFitDtlVO[i].getFitnessDate());
						fb.setMedicalCertificateId(patFitDtlVO[i].getMedicalCertificateId());
					}
				}
			}
			
			////Getting the Detail of a Duplicate Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_DUPLICATE_MEDICAL_CERTIFICATE))
			{
				PatMedicalDtlVO[] patMedDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_DUPLICATE_MC_OF_PATIENT);
				for(int i=0;i<patMedDtlVO.length;i++)
				{
					if(fb.getSelectedDupMC().equals(patMedDtlVO[i].getMedicalCertificateId()))
					{
						fb.setSufferingFrom(patMedDtlVO[i].getSufferingFrom());
						fb.setAdviceDays(patMedDtlVO[i].getAdviceDays());
						fb.setFromDate(patMedDtlVO[i].getFromDate());
						fb.setToDate(patMedDtlVO[i].getToDate());
					}
				}
			}
				
			////Getting the Detail of a Duplicate Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_DUPLICATE_FITNESS_CERTIFICATE))
			{
				PatFitnessDtlVO[] patFitDtlVO=(PatFitnessDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_DUPLICATE_FC_OF_PATIENT);
				for(int i=0;i<patFitDtlVO.length;i++)
				{
					if(fb.getSelectedDupFC().equals(patFitDtlVO[i].getFitnessCertificateId()))
					{
						fb.setSufferingFrom(patFitDtlVO[i].getSufferingFrom());
						fb.setFitnessDate(patFitDtlVO[i].getFitnessDate());
						fb.setMedicalCertificateDesc(patFitDtlVO[i].getMedicalCertificateDesc());
					}
				}
			}
		
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}	
	}
	
	/** Saving the Certificate Issue Detail
	 * @param fb
	 * @param request
	 */
	public static void saveCertificateIssueDtl(IssueCertificateFB fb,HttpServletRequest request)
	{
		Status  objStatus=new Status();
		HttpSession session=request.getSession(); 
		CertificateIssueDtlVO certificateIssueDtlVO=new CertificateIssueDtlVO();
		RecordDispatchDtlVO dispatchVO=new RecordDispatchDtlVO();
		
		try
		{
			//////For Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE))
			{
				PatMedicalDtlVO[] patMedDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_MC_OF_PATIENT);
				int i=Integer.parseInt(fb.getSelectedGenMC());
				
				certificateIssueDtlVO.setHandoverTo(patMedDtlVO[i].getPatName());
				certificateIssueDtlVO.setDeptUnitCode(patMedDtlVO[i].getUnitCode());
				certificateIssueDtlVO.setCertificateDesc(patMedDtlVO[i].getMedicalCertificateDesc());
				certificateIssueDtlVO.setCertificateId(patMedDtlVO[i].getMedicalCertificateId());
				
				certificateIssueDtlVO.setIsDuplicate(MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
				certificateIssueDtlVO.setRecordType(MrdConfig.RECORD_TYPE_MEDICAL);
				certificateIssueDtlVO.setRemarks(fb.getRemarks());
				
				if(fb.getIsReceiptTaken()==null)
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
				}	
				else
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
				}	
				
				dispatchVO.setRecordId(patMedDtlVO[i].getMedicalCertificateId());
				dispatchVO.setRecordDesc(patMedDtlVO[i].getMedicalCertificateDesc());
				dispatchVO.setRecordType(MrdConfig.RECORD_TYPE_MEDICAL);
				dispatchVO.setDeptUnitCode(patMedDtlVO[i].getUnitCode());
				dispatchVO.setDeptCode(patMedDtlVO[i].getUnitCode().substring(0, 3));
				dispatchVO.setRemarks(fb.getRemarks());
				dispatchVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
				dispatchVO.setEntryMode(MrdConfig.CERTIFICATE_DISPATCH_ENTRY_MODE_ONLINE);
				HelperMethods.populatetToNullOrEmpty(dispatchVO, patMedDtlVO[i]);
			}
			
			//////For Fitness Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE))
			{
				PatFitnessDtlVO[] patFitDtlVO=(PatFitnessDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_FC_OF_PATIENT);
				int i=Integer.parseInt(fb.getSelectedGenFC());
				
				certificateIssueDtlVO.setHandoverTo(patFitDtlVO[i].getPatName());
				certificateIssueDtlVO.setDeptUnitCode(patFitDtlVO[i].getUnitCode());
				certificateIssueDtlVO.setCertificateDesc(patFitDtlVO[i].getFitnessCertificateDesc());
				certificateIssueDtlVO.setCertificateId(patFitDtlVO[i].getFitnessCertificateId());
				
				certificateIssueDtlVO.setIsDuplicate(MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
				certificateIssueDtlVO.setRecordType(MrdConfig.RECORD_TYPE_FITNESS);
				certificateIssueDtlVO.setRemarks(fb.getRemarks());
				
				if(fb.getIsReceiptTaken()==null)
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
				}	
				else
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
				}	
				
				dispatchVO.setRecordId(patFitDtlVO[i].getFitnessCertificateId());
				dispatchVO.setRecordDesc(patFitDtlVO[i].getFitnessCertificateDesc());
				dispatchVO.setRecordType(MrdConfig.RECORD_TYPE_FITNESS);
				dispatchVO.setDeptUnitCode(patFitDtlVO[i].getUnitCode());
				dispatchVO.setDeptCode(patFitDtlVO[i].getUnitCode().substring(0, 3));
				dispatchVO.setRemarks(fb.getRemarks());
				dispatchVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
				dispatchVO.setEntryMode(MrdConfig.CERTIFICATE_DISPATCH_ENTRY_MODE_ONLINE);
				
				HelperMethods.populatetToNullOrEmpty(dispatchVO, patFitDtlVO[i]);
			}
			
			////For Duplicate Medical Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_DUPLICATE_MEDICAL_CERTIFICATE))
			{
				PatMedicalDtlVO[] patMedDtlVO=(PatMedicalDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_DUPLICATE_MC_OF_PATIENT);
				int i=Integer.parseInt(fb.getSelectedDupMC());
				
				certificateIssueDtlVO.setHandoverTo(patMedDtlVO[i].getPatName());
				certificateIssueDtlVO.setDeptUnitCode(patMedDtlVO[i].getUnitCode());
				certificateIssueDtlVO.setCertificateDesc(patMedDtlVO[i].getMedicalCertificateDesc());
				certificateIssueDtlVO.setCertificateId(patMedDtlVO[i].getMedicalCertificateId());
				
				certificateIssueDtlVO.setIsDuplicate(MrdConfig.CERTIFICATE_IS_DUPLICATE_YES);
				certificateIssueDtlVO.setRecordType(MrdConfig.RECORD_TYPE_MEDICAL);
				certificateIssueDtlVO.setRemarks(fb.getRemarks());
				
				if(fb.getIsReceiptTaken()==null)
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
				}	
				else
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
				}	
				
				dispatchVO.setRecordId(patMedDtlVO[i].getMedicalCertificateId());
				dispatchVO.setRecordDesc(patMedDtlVO[i].getMedicalCertificateDesc());
				dispatchVO.setRecordType(MrdConfig.RECORD_TYPE_MEDICAL);
				dispatchVO.setDeptUnitCode(patMedDtlVO[i].getUnitCode());
				dispatchVO.setDeptCode(patMedDtlVO[i].getUnitCode().substring(0, 3));
				dispatchVO.setRemarks(fb.getRemarks());
				dispatchVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
				dispatchVO.setEntryMode(MrdConfig.CERTIFICATE_DISPATCH_ENTRY_MODE_ONLINE);
				
				HelperMethods.populatetToNullOrEmpty(dispatchVO, patMedDtlVO[i]);
			}
				
			////For Duplicate Fitness Certificate
			if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_DUPLICATE_FITNESS_CERTIFICATE))
			{
				PatFitnessDtlVO[] patFitDtlVO=(PatFitnessDtlVO[])session.getAttribute(MrdConfig.ALL_GENERATED_DUPLICATE_FC_OF_PATIENT);
				int i=Integer.parseInt(fb.getSelectedDupFC());
				
				certificateIssueDtlVO.setHandoverTo(patFitDtlVO[i].getPatName());
				certificateIssueDtlVO.setDeptUnitCode(patFitDtlVO[i].getUnitCode());
				certificateIssueDtlVO.setCertificateDesc(patFitDtlVO[i].getFitnessCertificateDesc());
				certificateIssueDtlVO.setCertificateId(patFitDtlVO[i].getFitnessCertificateId());
						
				certificateIssueDtlVO.setIsDuplicate(MrdConfig.CERTIFICATE_IS_DUPLICATE_YES);
				certificateIssueDtlVO.setRecordType(MrdConfig.RECORD_TYPE_FITNESS);
				certificateIssueDtlVO.setRemarks(fb.getRemarks());
				
				if(fb.getIsReceiptTaken()==null)
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_NOT_REQUIRED);
				}	
				else
				{
					certificateIssueDtlVO.setIsReceiptTaken(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
					certificateIssueDtlVO.setRecordStatus(MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);
				}	
				
				dispatchVO.setRecordId(patFitDtlVO[i].getFitnessCertificateId());
				dispatchVO.setRecordDesc(patFitDtlVO[i].getFitnessCertificateDesc());
				dispatchVO.setRecordType(MrdConfig.RECORD_TYPE_FITNESS);
				dispatchVO.setDeptUnitCode(patFitDtlVO[i].getUnitCode());
				dispatchVO.setDeptCode(patFitDtlVO[i].getUnitCode().substring(0, 3));
				dispatchVO.setRemarks(fb.getRemarks());
				dispatchVO.setRecordStatus(MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_READY_TO_DISPATCH);
				dispatchVO.setEntryMode(MrdConfig.CERTIFICATE_DISPATCH_ENTRY_MODE_ONLINE);
				
				HelperMethods.populatetToNullOrEmpty(dispatchVO, patFitDtlVO[i]);
			}
			
			
			dispatchVO.setPatCrNo(fb.getPatCrNo());
			IssueCertificateDATA.saveCertificateIssueDtl(dispatchVO,certificateIssueDtlVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Certificate Issued");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		
	}
}
