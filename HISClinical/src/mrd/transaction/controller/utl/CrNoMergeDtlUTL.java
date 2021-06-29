package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import registration.controller.util.PatDetailUTIL;

import mrd.MrdConfig;
import mrd.transaction.controller.data.CrNoMergeDtlDATA;
import mrd.transaction.controller.fb.CrNoMergeDtlFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class CrNoMergeDtlUTL extends ControllerUTIL
{
	public static void getNotUsedCrNo(CrNoMergeDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		PatientVO patVO=new PatientVO();
		
 		List<PatientVO> lstPatVO=null;
		List<PatientVO> lstSearchPatVO=null;
		
		try
		{
			lstPatVO=(List<PatientVO>)session.getAttribute(MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			if(fb.getConcatedCrNo()==null || fb.getConcatedCrNo().equals(""))
			{
				String crNo=fb.getPatNotUsedCrNo();
				try
				{
					patVO=CrNoMergeDtlDATA.getNotUsedCrNo(crNo,getUserVO(request));
				}
				catch(HisRecordNotFoundException e)
				{
					patVO = null;
					objStatus.add(Status.TRANSINPROCESS,"Patient Detail Not Found","");
				}
				catch(HisException e)
				{
					patVO = null;
					objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");
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
						lstPatVO.add(patVO);
					}
				}
			}
			else
			{
				String searchedCrNo[]=fb.getConcatedCrNo().split("@");
				lstSearchPatVO=(List<PatientVO>)session.getAttribute(MrdConfig.ARR_SEARCH_PATIENT);
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
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO, lstPatVO);
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	
	public static void deleteRow(CrNoMergeDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<PatientVO> lstPatVO=null;
		
		try
		{
			lstPatVO=(List<PatientVO>)session.getAttribute(MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			for(int i=0;i<lstPatVO.size();i++)
			{
				if(fb.getDeleteCrNo().equals(lstPatVO.get(i).getPatCrNo()))
				{
					lstPatVO.remove(i);
				}
			}
			
			WebUTIL.setAttributeInSession(request, MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO, lstPatVO);
			objStatus.add(Status.TRANSINPROCESS);
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void saveNotUsedCrNo(CrNoMergeDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		List<CrNoMergeDtlVO> lstNotUsedCRNo=null;
		List<PatientVO> addedPatListVO=null; 
		CrNoMergeDtlVO crNoMergeVO=null;
	//	PatientVO[] patVO=null;
		
		try
		{
			lstNotUsedCRNo=new ArrayList<CrNoMergeDtlVO>();
			addedPatListVO=(List<PatientVO>)session.getAttribute(MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			
			for(int i=0;i<addedPatListVO.size();i++)
			{
				crNoMergeVO=new CrNoMergeDtlVO();
				crNoMergeVO.setPatMainCrNo(fb.getPatCrNo());
				crNoMergeVO.setPatNotUsedCrNo(addedPatListVO.get(i).getPatCrNo());
				crNoMergeVO.setRemarks(fb.getRemarks());
				crNoMergeVO.setIsMerged(MrdConfig.CR_NUMBER_IS_MERGED_YES);
				
				lstNotUsedCRNo.add(crNoMergeVO);
			}
			CrNoMergeDtlDATA.saveNotUsedCrNo(lstNotUsedCRNo,getUserVO(request));
			objStatus.add(Status.DONE,"","CR Number Merged Successfully");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
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
	
	public static void searchPatient(CrNoMergeDtlFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		List<PatientVO> addedPatVOList=null;
		List<PatientVO> mergedPatVOList=null;
		List<PatientVO> searchPatVOList=new ArrayList<PatientVO>();
		
		
		try
		{
			UserVO userVO =getUserVO(request);
			String crNo=fb.getPatCrNo();
			fb.setPatCrNo("");
			PatientVO[] patVO=new PatientVO[]{};
			
			
			HashMap searchMap=HelperMethods.createQueryMapFromVO(fb);
			searchMap.remove("hmode");
			searchMap.remove("crNoToRetrieve");
			
			addedPatVOList=(List<PatientVO>)session.getAttribute(MrdConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
			mergedPatVOList=(List<PatientVO>)session.getAttribute(MrdConfig.ARR_MERGED_PATIENT);
			patVO=CrNoMergeDtlDATA.searchPatient(searchMap,userVO);
			
			for(int i=0;i<patVO.length;i++)
			{
				searchPatVOList.add(patVO[i]);
			}
			if(addedPatVOList!=null)
			{
				for(int j=0;j<addedPatVOList.size();j++)
				{
					for(int k=0;k<searchPatVOList.size();k++)
					{
						if(addedPatVOList.get(j).getPatCrNo().equals(searchPatVOList.get(k).getPatCrNo()))
						{
							searchPatVOList.remove(k);
							break;
						}
					}
				}
			}
			
			if(mergedPatVOList!=null)
			{
				for(int j=0;j<mergedPatVOList.size();j++)
				{
					for(int k=0;k<searchPatVOList.size();k++)
					{
						if(mergedPatVOList.get(j).getPatCrNo().equals(searchPatVOList.get(k).getPatCrNo()))
						{
							searchPatVOList.remove(k);
							break;
						}
					}
				}
			}
			if(crNo!=null)
			{
				for(int k=0;k<searchPatVOList.size();k++)
				{
					if(crNo.equals(searchPatVOList.get(k).getPatCrNo()))
					{
						searchPatVOList.remove(k);
						break;
					}
				}
			}
			
			fb.setPatCrNo(crNo);
			session.setAttribute(MrdConfig.ARR_SEARCH_PATIENT , searchPatVOList);
			if(searchPatVOList.size()==0)
				status.add(Status.NEW,"","No Patient Found");
			else
				status.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			session.setAttribute(MrdConfig.ARR_SEARCH_PATIENT ,new ArrayList<PatientVO>());
			status.add(Status.NEW, "No Matching Record Found", e.getMessage());
		}
		catch(HisApplicationExecutionException e)
		{
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Application execution error", "");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Data Access error", "");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			status.add(Status.ERROR_DA, "Error", "");
		}
		finally
		{
			WebUTIL.setStatus(request, status);
		}
	}
	
	public static void getMergedCrNo(CrNoMergeDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession(); 
		
		try
		{
			PatDetailUTIL.getPatientDtlByCrno(fb, request);
			if(session.getAttribute(RegistrationConfig.PATIENT_VO)==null)
			{
				objStatus.add(Status.UNSUCESSFULL,"","Patient is Already Merged");
				//throw new HisRecordNotFoundException("Patient is Already Merged");
			}
			else
			{
				List<PatientVO> lstMergedCrNo=CrNoMergeDtlDATA.getMergedCrNo(fb.getPatCrNo(),getUserVO(request));
				WebUTIL.setAttributeInSession(request, MrdConfig.ARR_MERGED_PATIENT, lstMergedCrNo);
				objStatus.add(Status.TRANSINPROCESS);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
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
			System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}
	}
	
	public static void revokeMergedCRNo(CrNoMergeDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			String revokedCrNo=fb.getRevokedCrNo();
			String revokeReason=fb.getReason();
			CrNoMergeDtlDATA.revokeMergedCRNo(revokeReason,fb.getPatCrNo(),revokedCrNo,getUserVO(request));
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
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
