package opd.transaction.controller.util;


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.data.OpenEpisodeDATA;
import opd.transaction.controller.fb.OpenEpisodeFB;

import opd.OpdConfig;
import registration.RegistrationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeClosedException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;


public class OpenEpisodeUTIL extends  ControllerUTIL
{
	public static void setPatientDtlByCrno(OpenEpisodeFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		HttpSession ses=WebUTIL.getSession(_request);
		try
		{
			UserVO userVO=getUserVO(_request);
			PatientVO patientVO=new PatientVO();
			EpisodeCloseVO[] episodeCloseVO;
			patientVO.setPatCrNo(_fb.getPatCrNo());
			patientVO=OpenEpisodeDATA.getPatientDtl(patientVO,userVO);
			HelperMethods.populate(_fb,patientVO);
			WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO,patientVO);
			patientVO=(PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			String crNo=patientVO.getPatCrNo();
			episodeCloseVO=OpenEpisodeDATA.getPatientEpisodeDtl(crNo,userVO,patientVO);
			Collection collCloseEpisode=new LinkedList();
			if(episodeCloseVO.length==0)
			{
				throw new HisEpisodeClosedException();
			}
			for(int i=0;i<episodeCloseVO.length;i++)
			{
				collCloseEpisode.add(episodeCloseVO[i]);
			}
			WebUTIL.setAttributeInSession(_request,OpdConfig.COLL_CLOSE_EPISODE,collCloseEpisode);
//			HelperMethods.populate(_fb,episodeCloseVO);
//			episodeCloseVO=(EpisodeCloseVO[])ses.getAttribute(OpdConfig.EPISODE_CLOSE_VO);
			
			objStatus.add(Status.INPROCESS,"","");
			objStatus.add(Status.TRANSINPROCESS,"","");
			
		}
		catch(HisEpisodeClosedException e)
		{
			objStatus.add(Status.NEW,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"","");
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void openPatientEpisode(OpenEpisodeFB _fb,HttpServletRequest _request)
	{
		PatientVO patientVO=(PatientVO) WebUTIL.getSession(_request).getAttribute(RegistrationConfig.PATIENT_VO);
		UserVO userVO=getUserVO(_request);
		String crNo=patientVO.getPatCrNo();
		Status objStatus=new Status();
		
		Collection closeEpisodeColl=(Collection)WebUTIL.getSession(_request).getAttribute(OpdConfig.COLL_CLOSE_EPISODE);
		Iterator itr=closeEpisodeColl.iterator();
		EpisodeCloseVO[] episodeCloseVO=new EpisodeCloseVO[closeEpisodeColl.size()];
		int index=0;
		while(itr.hasNext())
		{
			episodeCloseVO[index]=(EpisodeCloseVO)itr.next();
			index++;
		}
		
	try
		{
			EpisodeCloseVO[] selectedEpisodeCloseVO=new EpisodeCloseVO[_fb.getSelectEpisodeCode().length];
			for(int i=0;i<_fb.getSelectEpisodeCode().length;i++)
			{
				for(int j=0;j<episodeCloseVO.length;j++)
				{
					if(_fb.getSelectEpisodeCode()[i].equals(episodeCloseVO[j].getEpisodeCode()))
					{
						selectedEpisodeCloseVO[i]=new EpisodeCloseVO();
						HelperMethods.populate(selectedEpisodeCloseVO[i],episodeCloseVO[j]);
					}
				}
			}
			
//			String x=episodeCloseVO[0].getEpisodeCode();
//			System.out.println("<<<<<<<<<<<<<<"+x+">>>>>>>>>>>>>");
//			patientVO.setEpisodeCode(_fb.getEpisodeCode());
//			patientVO.setEpisodeCode(x);
			OpenEpisodeDATA.updatePatientEpisode(crNo,userVO,selectedEpisodeCloseVO);
			objStatus.add(Status.NEW,"Episode opened","");
		}
		catch(HisEpisodeClosedException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW,e.getMessage(),"");
		}
		catch(HisUpdateUnsuccesfullException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Update Failed");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");
		}
		catch(HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		
	}
}
