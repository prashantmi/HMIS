package mrd.transaction.controller.utl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.data.SummonDetailDATA;
import mrd.transaction.controller.fb.SummonDtlFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

public class SummonDetailUTL extends ControllerUTIL
{
	public static boolean getEssentialForSummonDtl(SummonDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			
			String sys=(String)session.getAttribute(Config.SYSDATE);

			String time=sys.split(" ")[1];

			fb.setSysHour(time.split(":")[0]);
			fb.setSysMinute(time.split(":")[1]);
						
			fb.setSysDate(sysadteString);
			
			//fb.setRecevingDate(sysadteString);
			
			/*
			fb.setRecevingDate(sysadteString);
			fb.setRecevingTimeHr(time.split(":")[0]);
			fb.setRecevingTimeMin(time.split(":")[1]);
			*/
			mpEssential=SummonDetailDATA.getEssentialForSummonDtl(fb.getCidNoFlag(),getUserVO(request));
			
						
			WebUTIL.setMapInSession(mpEssential, request);
			
			objStatus.add(Status.INPROCESS);
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
		return flag;
	}
	
	public static boolean saveSummonDetail(SummonDtlFB _fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		SummonDetailVO summonDetailVO=new SummonDetailVO();
		try
		{
			UserVO userVO = getUserVO(request);
			
			
			
			HelperMethods.populate(summonDetailVO, _fb);
			summonDetailVO.setSummonReceiveDateTime(_fb.getRecevingDate()+" "+_fb.getRecevingTimeHr()+":"+_fb.getRecevingTimeMin());
			if(_fb.getHearingTimeHr().equals(""))
			{
				_fb.setHearingTimeHr("00");
			}
			if(_fb.getHearingTimeMin().equals(""))
			{
				_fb.setHearingTimeMin("00");
			}
			
			summonDetailVO.setHearingDateTime(_fb.getHearingDate()+" "+_fb.getHearingTimeHr()+":"+_fb.getHearingTimeMin());
			summonDetailVO.setEntryMode(MrdConfig.ONLINE);
			summonDetailVO.setStatus(MrdConfig.SUMMON_RECEIVED);
			summonDetailVO.setIsEmpSpecific(MrdConfig.IS_EMPLOYEE_YES);
			summonDetailVO.setIsUpload(MrdConfig.IS_SUMMON_UPLOAD_NO);
			
			if(_fb.getSummonFlag().equals(MrdConfig.IS_COMPUTERIZED_SUMMON_DTL))
			{
				summonDetailVO.setPatDOB(_fb.getPatDOB());
				summonDetailVO.setRecordType(MrdConfig.IN_DATABASE);
			}
			else
			{
				summonDetailVO.setPatDOB(_fb.getAge());
				summonDetailVO.setRecordType(MrdConfig.NOT_IN_DATABASE);
			}
			
			summonDetailVO.setPatAdmNo(_fb.getPatAdmNo());
			SummonDetailDATA.saveSummonDetail(summonDetailVO,userVO);											
			objStatus.add(Status.DONE,"Details Saved","");
		}
		
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean searchPatientDtl(SummonDtlFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = WebUTIL.getSession(request);
		boolean flag = true;
		Map mpEssential=null;
		try
		{
			setSysdate(request);
			
			String _str="";
			
			fb.setAdm_crNoArray(null);
			fb.setAdm_fatherNameArray(null);
			fb.setAdm_mlcNoArray(null);
			fb.setAdm_motherNameArray(null);
			fb.setAdm_patAddressArray(null);
			fb.setAdm_patAdmNoArray(null);
			fb.setAdm_patAgeArray(null);
			fb.setAdm_patGenderArray(null);
			fb.setAdm_patNameArray(null);
			fb.setAdm_postMortemNoArray(null);
			fb.setAdm_spouseNameArray(null);
			fb.setAdmission_flagArray(null);
			fb.setMlc_admissionNoArray(null);
			fb.setMlc_crNoArray(null);
			fb.setMlc_episodeCodeArray(null);
			fb.setMlc_episodeVisitNoArray(null);
			fb.setMlc_fatherNameArray(null);
			fb.setMlc_flagArray(null);
			fb.setMlc_mlcNoArray(null);
			fb.setMlc_motherNameArray(null);
			fb.setMlc_patAddressArray(null);
			fb.setMlc_patAgeArray(null);
			fb.setMlc_patDOBArray(null);
			fb.setMlc_patGenderArray(null);
			fb.setMlc_patGenderCodeArray(null);
			fb.setMlc_patNameArray(null);
			fb.setMlc_postMortemNoArray(null);
			fb.setMlc_spouseNameArray(null);
			fb.setMlcNoArray(null);
			
			fb.setFatherNameArray(null);
			fb.setMotherNameArray(null);
			fb.setSpouseNameArray(null);
			fb.setPatAddressArray(null);
			fb.setPatAdmissionNoArray(null);
			fb.setPatAgeArray(null);
			fb.setPatNameArray(null);
			fb.setPatGenderArray(null);
			fb.setPatGenderCodeArray(null);
			fb.setCrNoArray(null);
			fb.setPatAdmissionNoArray(null);
			fb.setMlcNoArray(null);
			fb.setPostMortemNoArray(null);
			
			
			
			
			
			if(fb.getSearchType().equals(MrdConfig.PAT_SEARCH_BY_NAME))
			{
				if(fb.getStr_first_name()==null || fb.getStr_first_name().equals(""))
				{
					fb.setStr_first_name("@");
				}
				if(fb.getStr_middle_name()==null || fb.getStr_middle_name().equals(""))
				{
					fb.setStr_middle_name("@");
				}
				if(fb.getStr_last_name()==null || fb.getStr_last_name().equals(""))
				{
					fb.setStr_last_name("@");
				}
				_str=fb.getStr_first_name()+"#"+fb.getStr_middle_name()+"#"+fb.getStr_last_name();
			}
			if(fb.getSearchType().equals(MrdConfig.PAT_SEARCH_BY_MLCNO))
			{
				_str=fb.getStr_mlc_no();
			}
			if(fb.getSearchType().equals(MrdConfig.PAT_SEARCH_BY_POSTMARTEM_NO))
			{
				_str=fb.getStr_postmortem_no();
			}
			if(fb.getSearchType().equals(MrdConfig.PAT_SERACH_BY_CRNO))
			{
				_str=fb.getStr_pat_crno();
			}
			if(fb.getSearchType().equals(MrdConfig.PAT_SERACH_BY_ADMISSION_NO))
			{
				_str=fb.getStr_admission_no();
			}
				
			
			mpEssential=SummonDetailDATA.searchPatientDtl(fb.getSearchType(),_str.trim(),getUserVO(request));
			
			if(fb.getSearchType().equals(MrdConfig.PAT_SEARCH_BY_NAME))
			{
				if(fb.getStr_first_name().equals("@"))
				{
					fb.setStr_first_name("");
				}
				if(fb.getStr_middle_name().equals("@"))
				{
					fb.setStr_middle_name("");
				}
				if(fb.getStr_last_name().equals("@"))
				{
					fb.setStr_last_name("");
				}
			}
			
			
						
			WebUTIL.setMapInSession(mpEssential, request);
			
			objStatus.add(Status.INPROCESS);
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
		return flag;
	}
	
	
	
	
}
