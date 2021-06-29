package registration.transactions.controller.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.inject.Inject;

import registration.config.RegistrationConfig;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import registration.config.Exceptions.HisDeadPatientException;
import registration.config.Exceptions.HisNotAnIPDcaseException;
import registration.config.Exceptions.HisNotAnOPDcaseException;
import registration.config.Exceptions.HisRenewalRequiredException;
import registration.transactions.controller.actionsupport.MergeCRNOSUP;
import registration.transactions.controller.data.CrNoMergeDATA;
import registration.transactions.controller.data.PatientVisitDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import vo.registration.CrNoMergeDtlVO;
import vo.registration.PatientVO;
import vo.registration.RenewalConfigVO;

public class OfflineMergeCRNOUTL extends ControllerUTIL
{	
	public static void setPatientDtlByCrno( MergeCRNOSUP objMergeCRNOSUP_p,HttpServletRequest objRequest_p,Logger logger){
			
		Status status=new Status();
		UserVO objUserVO =getUserVO(objRequest_p);
		PatientVO objPatientVO=new PatientVO();
		objPatientVO.setPatCrNo(objMergeCRNOSUP_p.getPatCrNo());
		logger.info("OfflineMergeCRNOUTL :: setPatientDtlByCrno()");
		
		try{
			objPatientVO=CrNoMergeDATA.getPatientDtl(objPatientVO,objUserVO);
			objMergeCRNOSUP_p.setPatPrimaryCatGrpCode(objPatientVO.getPatPrimaryCatGrpCode());
			if(objPatientVO.getPatIsDead().equals(RegistrationConfig.PATIENT_IS_DEAD))
			{
				objMergeCRNOSUP_p.setAfterGo("0");
				System.out.println("Patient Is Dead");
				throw new HisDeadPatientException("Not apllicable, Patient is Dead");
			}
			
			if(objPatientVO.getIsCatExpired()!=null && objPatientVO.getIsCatExpired().equals("1"))
				throw new HisException("This Category Is Expired. Kindly Select Another Record or Renew its first.");
			
			if(objPatientVO.getPatIsMerged()!=null && objPatientVO.getPatIsMerged().equals("2"))
			{
				objMergeCRNOSUP_p.setAfterGo("0");
				System.out.println("CR No is Not Valid and its Already merged with another CR No");
				throw new HisException("CR No is Not Valid, Already Merged with CR No. " +objPatientVO.getPatMergedMainCrNO() );
			}
			
					
			/*Map<String, RenewalConfigVO> mapOfRenewalVoOnPatCatGroupKey= (Map<String, RenewalConfigVO>)objRequest_p.getSession().getAttribute(RegistrationConfig.ESSENTIALBO_MAP_OF_RENEWEL_CONFIG_VO);
			RenewalConfigVO objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get(objPatientVO.getPatPrimaryCatCode());
			if(objRenewalConfigVO==null)
				objRenewalConfigVO=mapOfRenewalVoOnPatCatGroupKey.get("10");

			objRenewalConfigVO.setStrRenewalGroup(RegistrationConfig.RENEWAL_CONFIG_GROUP_OPD);
			objPatientVO.setRenewalConfig(objRenewalConfigVO);		*/
			
			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
			
			List<PatientVO> lstMergedCrNo=CrNoMergeDATA.getMergedCrNo(objMergeCRNOSUP_p.getPatCrNo(),getUserVO(objRequest_p));
			WebUTIL.setAttributeInSession(objRequest_p, RegistrationConfig.ARR_MERGED_PATIENT, lstMergedCrNo);
												
			
			status.add(Status.TRANSINPROCESS);
		}
		catch(HisRenewalRequiredException e){
	   		//e.printStackTrace();
			System.out.println(e.getMessage());
			objMergeCRNOSUP_p.setErrorMessage("Renewal Required");
		}
		catch(HisRecordNotFoundException e){
			System.out.println(e.getMessage());
	   		if( (objPatientVO!=null) && (objPatientVO.getPatPrimaryCatCode()==null ||objPatientVO.getPatPrimaryCatCode().equals("")))
	   		{
	   			WebUTIL.setAttributeInSession(objRequest_p,RegistrationConfig.PATIENT_VO,objPatientVO);
	   		}
	   		if(e.getMessage()!=null && e.getMessage().trim().equals("Patient Details Not Found"))
	   			objMergeCRNOSUP_p.setAfterGo("0");
	   		objMergeCRNOSUP_p.setErrorMessage(e.getMessage());
		}
		catch(HisDuplicateRecordException e){
	   		System.out.println(e.getMessage());
	   		objMergeCRNOSUP_p.setErrorMessage("Not Eligible For OPD");
		}
	   	catch(HisNotAnOPDcaseException e){
	   		System.out.println(e.getMessage());
	   		objMergeCRNOSUP_p.setErrorMessage("Not Eligible For OPD");
		}
		
		catch(HisNotAnIPDcaseException e){
			System.out.println(e.getMessage());
			objMergeCRNOSUP_p.setErrorMessage("Not Eligible For IPD");
		}
		catch(HisDeadPatientException e){
			objMergeCRNOSUP_p.setErrorMessage("Not applicable, Patient is Dead");
			if(objMergeCRNOSUP_p.getErrorMessage()==null || objMergeCRNOSUP_p.getErrorMessage().equals("")){
			}else{
				objMergeCRNOSUP_p.setAfterGo("0");
			}
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Transaction Unsuccessful");
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Transaction Unsuccessful");
		}
		catch(HisException e){
			objMergeCRNOSUP_p.setErrorMessage(e.getMessage());
			if(objMergeCRNOSUP_p.getErrorMessage()==null || objMergeCRNOSUP_p.getErrorMessage().equals("")){
			}else{
				objMergeCRNOSUP_p.setAfterGo("0");
			}
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Transaction Unsuccessful");
		}
		finally{
			
			WebUTIL.setStatus(objRequest_p, status);
		}
	}
	
	
	
	public static void getNotUsedCrNo(MergeCRNOSUP objMergeCRNOSUP_p,HttpServletRequest request,Logger logger)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatientVO patVO=new PatientVO();
		
 		List<PatientVO> lstPatVO=null;
		List<PatientVO> lstSearchPatVO=null;
		boolean	alreadyListed=false;
		try
		{
			lstPatVO=(List<PatientVO>)session.getAttribute(RegistrationConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			if(objMergeCRNOSUP_p.getConcatedCrNo()==null || objMergeCRNOSUP_p.getConcatedCrNo().equals(""))
			{
				String crNo=objMergeCRNOSUP_p.getPatNotUsedCrNo();
				try{
					patVO=CrNoMergeDATA.getNotUsedCrNo(crNo,getUserVO(request));
					
					if(patVO!=null)
					{
						if(patVO.getPatIsMerged()!=null && patVO.getPatIsMerged().equals("2"))
						{
							//objMergeCRNOSUP_p.setAfterGo("0");
							System.out.println("CR No is Not Valid and its Already merged with another CR No");
							throw new HisException("CR No is Not Valid, Already Merged with CR No. " +patVO.getPatMergedMainCrNO() );
						}
					}
				}
				catch(HisRecordNotFoundException e)
				{
					patVO = null;
					objMergeCRNOSUP_p.setErrorMessage("Patient Detail Not Found");
				}
				catch(HisException e)
				{
					patVO = null;
					objMergeCRNOSUP_p.setErrorMessage(e.getMessage());
				}
				
				if(patVO !=null)
				{
					if(lstPatVO==null)
					{
						lstPatVO=new ArrayList<PatientVO>();
						lstPatVO.add(patVO);
					}
					else
					{
						for(PatientVO _tmpPatVo:lstPatVO){
							_tmpPatVo.getPatCrNo().equals(patVO.getPatCrNo());
							alreadyListed=true;
						}
						if(!alreadyListed)
							lstPatVO.add(patVO);
						else
							throw new HisException("CR No is Already Added !");

					}
				}
			}
			else
			{
				String searchedCrNo[]=objMergeCRNOSUP_p.getConcatedCrNo().split("@");
				lstSearchPatVO=(List<PatientVO>)session.getAttribute(RegistrationConfig.ARR_SEARCH_PATIENT);
				if(lstPatVO==null)	lstPatVO=new ArrayList<PatientVO>();
				for(int i=0;i<searchedCrNo.length;i++)
				{
					if(lstSearchPatVO!=null)
					{
						for(int j=0;j<lstSearchPatVO.size();j++)
						{
							if(searchedCrNo[i].equals(lstSearchPatVO.get(j).getPatCrNo()))
							{
								lstPatVO.add(lstSearchPatVO.get(j));
								break;
							}
						}
					}
				}
			}
			WebUTIL.setAttributeInSession(request, RegistrationConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO, lstPatVO);
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage(e.getMessage());

		}
		catch (HisException e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage(e.getMessage());

		}
		catch (Exception e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void deleteRow(MergeCRNOSUP objMergeCRNOSUP_p,HttpServletRequest request,Logger logger)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<PatientVO> lstPatVO=null;
		
		try
		{
			lstPatVO=(List<PatientVO>)session.getAttribute(RegistrationConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			for(int i=0;i<lstPatVO.size();i++)
			{
				if(objMergeCRNOSUP_p.getDeleteCrNo().equals(lstPatVO.get(i).getPatCrNo()))
				{
					lstPatVO.remove(i);
				}
			}
			
			WebUTIL.setAttributeInSession(request, RegistrationConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO, lstPatVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objMergeCRNOSUP_p.setErrorMessage("Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	
	public static void saveNotUsedCrNo(MergeCRNOSUP fb,HttpServletRequest request,Logger logger)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<CrNoMergeDtlVO> lstNotUsedCRNo=null;
		List<PatientVO> addedPatListVO=null; 
		CrNoMergeDtlVO crNoMergeVO=null;
		
		try
		{
			lstNotUsedCRNo=new ArrayList<CrNoMergeDtlVO>();
			addedPatListVO=(List<PatientVO>)session.getAttribute(RegistrationConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			
			for(int i=0;i<addedPatListVO.size();i++)
			{
				crNoMergeVO=new CrNoMergeDtlVO();
				crNoMergeVO.setPatMainCrNo(fb.getPatCrNo());
				crNoMergeVO.setPatNotUsedCrNo(addedPatListVO.get(i).getPatCrNo());
				crNoMergeVO.setRemarks(fb.getRemarks());
				crNoMergeVO.setIsMerged(RegistrationConfig.CR_NUMBER_IS_MERGED_YES);
				
				lstNotUsedCRNo.add(crNoMergeVO);
			}
			CrNoMergeDATA.saveNotUsedCrNo(lstNotUsedCRNo,getUserVO(request));
			objStatus.add(Status.DONE,"","CR Number Merged Successfully");
			fb.setStrNormalMsg("CR Number Merged Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			fb.setErrorMessage("Data Access Error");

		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void revokeMergedCRNo(MergeCRNOSUP fb,HttpServletRequest request,Logger logger)
	{
		Status objStatus = new Status();
		
		try
		{
			String revokedCrNo=fb.getRevokedCrNo();
			String revokeReason=fb.getReason();
			CrNoMergeDATA.revokeMergedCRNo(revokeReason,fb.getPatCrNo(),revokedCrNo,getUserVO(request));
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			fb.setErrorMessage(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	
	
}
