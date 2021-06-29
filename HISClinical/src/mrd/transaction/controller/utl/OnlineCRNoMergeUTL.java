package mrd.transaction.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.OnlineCRNoMergeDATA;
import mrd.transaction.controller.fb.OnlineCRNoMergeFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class OnlineCRNoMergeUTL extends ControllerUTIL
{
	public static void getEssentialForOnlineCRNoMerge(OnlineCRNoMergeFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		
		try
		{
			Map mp=OnlineCRNoMergeDATA.getEssentialForOnlineCRNoMerge(getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
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
		}
	}
	
	public static void searchPatient(OnlineCRNoMergeFB fb,HttpServletRequest request)
	{
		Status status = new Status();
		HttpSession session=request.getSession();
		
		try
		{
			fb.setPatMainCrNo("");
			UserVO userVO =getUserVO(request);
			PatientVO[] patVO=new PatientVO[]{};
			HashMap searchMap=HelperMethods.createQueryMapFromVO(fb);
			searchMap.remove("hmode");
			searchMap.remove("crNoToRetrieve");
			searchMap.remove("patMainCrNo");
			
			patVO=OnlineCRNoMergeDATA.searchPatient(searchMap,userVO);

			List lstMainCRNo=OnlineCRNoMergeDATA.getMainCRNumberList(userVO);
			
			
			for( int i=0;i<lstMainCRNo.size();i++)
			{
				Entry ent=(Entry)lstMainCRNo.get(i);
				for(int j=0;j<patVO.length;j++)
				{
					if(ent.getValue().equals(patVO[j].getPatCrNo()))
					{
						patVO[j].setIsMainCrNo(MrdConfig.CR_NUMBER_IS_MAIN_YES) ;
					}
				}
			}
			
			for(int k=0;k<patVO.length;k++)
			{
				if(patVO[k].getIsMainCrNo()==null)
				{
					patVO[k].setIsMainCrNo(MrdConfig.CR_NUMBER_IS_MAIN_NO);
				}
			}
			
			session.setAttribute(MrdConfig.ARR_ONLINE_SEARCH_PATIENT, patVO) ;
			status.add(Status.NEW);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			session.setAttribute(MrdConfig.ARR_ONLINE_SEARCH_PATIENT ,new PatientVO[]{});
			status.add(Status.NEW, "", e.getMessage());
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
	
	public static void saveOnlineCRNumberMerge(OnlineCRNoMergeFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session=request.getSession();
		List<CrNoMergeDtlVO> lstNotUsedCRNo=null;
		CrNoMergeDtlVO crNoMergeVO=null;
		
		try
		{
			lstNotUsedCRNo=new ArrayList<CrNoMergeDtlVO>();
			for(int i=0;i<fb.getCrNoToRetrieve().length;i++)
			{
				if(!fb.getPatMainCrNo().equals(fb.getCrNoToRetrieve()[i]))
				{
					crNoMergeVO=new CrNoMergeDtlVO();
					crNoMergeVO.setPatMainCrNo(fb.getPatMainCrNo());
					crNoMergeVO.setPatNotUsedCrNo(fb.getCrNoToRetrieve()[i]);
					crNoMergeVO.setIsMerged(MrdConfig.CR_NUMBER_IS_MERGED_YES);
					crNoMergeVO.setRemarks(fb.getRemarks());
					
					lstNotUsedCRNo.add(crNoMergeVO);
				}
			}
			OnlineCRNoMergeDATA.saveNotUsedCrNo(lstNotUsedCRNo,getUserVO(request));
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
}
