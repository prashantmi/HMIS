package opd.transaction.controller.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.PatientAttendantDATA;
import opd.transaction.controller.fb.PatientAttendantFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientFamilyDtlVO;

public class PatientAttendantUTL extends ControllerUTIL
{
	public static void getAllEpisodeOfThePatient(PatientAttendantFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		EpisodeVO[] patEpisodeVO=null;
		
		try
		{
			patEpisodeVO=PatientAttendantDATA.getAllEpisodeOfThePatientTodayVisited(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request,OpdConfig.PATIENT_TODAY_VISITED_EPISODE ,patEpisodeVO );
			objStatus.add(Status.LIST);
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
	
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getPatientAddedAttendant(PatientAttendantFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession(); 
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			if(fb.getIsDirectCall().equals("DIRECT"))
			{
				EpisodeVO[] patEpisodeVO=(EpisodeVO[])session.getAttribute(OpdConfig.PATIENT_TODAY_VISITED_EPISODE);
				int i=Integer.parseInt(fb.getHiddenIndex());
				fb.setPatCrNo(fb.getPatCrNo());
				fb.setEpisodeCode(patEpisodeVO[i].getEpisodeCode());
				fb.setEpisodeVisitNo(patEpisodeVO[i].getEpisodeVisitNo());
			}
			else
			{
			/*	PatientDetailVO selectedPatientVO=null;
				PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				
				for(int i=0;i<arrayDailyPatVO.length;i++)
				{
					if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
					{
						selectedPatientVO=arrayDailyPatVO[i];
						break;
					}
				}*/
				PatientDetailVO patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
				{
					dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
					for (int i = 0; i < dailyPatientVOs.length; i++)
					{
						if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
						{
							patientDetailVO = dailyPatientVOs[i];
							break;
						}
					}
				}
				fb.setPatCrNo(patientDetailVO.getPatCrNo());
				fb.setEpisodeCode(patientDetailVO.getEpisodeCode());
				fb.setEpisodeVisitNo(patientDetailVO.getEpisodeVisitNo());
			}

			Map map=PatientAttendantDATA.getPatientAddedAttendant(fb.getPatCrNo(), fb.getEpisodeCode(), getUserVO(request));
			WebUTIL.setMapInSession(map, request);
			String str=(String)map.get(OpdConfig.PATIENT_FATHER_MOTHER_SPOUCE_NAME);
			fb.setPatFarherMotherSpouceName(str);
			List<PatientFamilyDtlVO> lst=(List)map.get(OpdConfig.PATIENT_ALL_ATTENDANT_VO_LIST);
			if(lst.size()==0)
			{
				fb.setExistingNewFlag(OpdConfig.PATIENT_ATTENDANT_NEW);
				fb.setIsAttendantExist(OpdConfig.NO);
			}
			else
			{
				fb.setExistingNewFlag(OpdConfig.PATIENT_ATTENDANT_EXISTING);
				fb.setIsAttendantExist(OpdConfig.YES);
				for(PatientFamilyDtlVO famVO : lst)	famVO.setSlNo("0");	
				// Seting Added Attendant Status
				List<EpisodeAttendantDetailVO> lstAttendants = (List<EpisodeAttendantDetailVO>)map.get(OpdConfig.PATIENT_EPISODE_ATTENDANT_LIST);
				if(lstAttendants!=null && lstAttendants.size()>0 && lstAttendants.get(0).getEpisodeVisitNo().equals(fb.getEpisodeVisitNo()))
				{
					for(EpisodeAttendantDetailVO attVO : lstAttendants)
					{
						if(!attVO.getEpisodeVisitNo().equals(fb.getEpisodeVisitNo()))	break;
						for(PatientFamilyDtlVO famVO : lst)
							if(famVO.getPatRelativeId().equals(attVO.getPatRelativeId()))
							{
								famVO.setSlNo("1");
								break;
							}
					}
				}
			}	
			fb.setHiddenIndex(fb.getSelectedEpisode());
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
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static boolean savePatientAttendantDtl(PatientAttendantFB fb,HttpServletRequest request)
	{
		boolean flgSave = true;
		Status objStatus=new Status();
		PatientFamilyDtlVO patFamilyVO=new PatientFamilyDtlVO();
		EpisodeAttendantDetailVO epiAttendantVO=new EpisodeAttendantDetailVO();
		String isExisting="";
		
		try
		{
			epiAttendantVO.setPatCrNo(fb.getPatCrNo());
			epiAttendantVO.setEpisodeCode(fb.getEpisodeCode());
			epiAttendantVO.setEpisodeVisitNo(fb.getEpisodeVisitNo());
			if(fb.getExistingNewFlag().equals(OpdConfig.PATIENT_ATTENDANT_NEW))
			{
				HelperMethods.populate(patFamilyVO, fb);
				isExisting=OpdConfig.NO;
			}
			else
			{
				patFamilyVO=null;
				epiAttendantVO.setPatRelativeId(fb.getSelectedAttendant());
				isExisting=OpdConfig.YES;
			}
			epiAttendantVO.setAttendantReasonId(fb.getAttendantReasonId());
			
			PatientAttendantDATA.savePatientAttendantDtl(isExisting,patFamilyVO,epiAttendantVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			flgSave =  true;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flgSave;
	}
}
