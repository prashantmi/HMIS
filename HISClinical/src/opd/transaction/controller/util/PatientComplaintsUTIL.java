package opd.transaction.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.PatientComplaintsDATA;
import opd.transaction.controller.fb.PatientComplaintsFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.PatientDetailVO;

public class PatientComplaintsUTIL extends ControllerUTIL
{
	public static void getParameterForExtInv(PatientComplaintsFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setRecordTimeHr(time.split(":")[0]);
			fb.setRecordTimeMin(time.split(":")[1]);
			fb.setHiddenTimeHr(fb.getRecordTimeHr());
			fb.setHiddenTimeMin(fb.getRecordTimeMin());
			
			List lstParameter=PatientComplaintsDATA.getParameterForExtInv(getUserVO(request));
			WebUTIL.setAttributeInSession(request, OpdConfig.EXT_INV_PARAMETER_LIST ,lstParameter);
			EpisodeExtInvDtlVO[] epiAddedExtInvDtlVO=PatientComplaintsDATA.getAddedExternalInvDetail(fb.getPatCrNo(),getUserVO(request));
			if(epiAddedExtInvDtlVO.length>0)
			{
				for(int i=0;i<epiAddedExtInvDtlVO.length;i++)
				{
					String dt=epiAddedExtInvDtlVO[i].getRecordDate();
					epiAddedExtInvDtlVO[i].setRecordDate(dt.split(" ")[0]);
					epiAddedExtInvDtlVO[i].setRecordTime(dt.split(" ")[1]);
				}
			}
			WebUTIL.setAttributeInSession(request, OpdConfig.ARR_ADDED_EXTERNAL_INVESTIGATION_DTL, epiAddedExtInvDtlVO);
			objStatus.add(Status.TRANSINPROCESS);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
	
	public static void addRow(PatientComplaintsFB fb,HttpServletRequest request)
	 {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
//		EpisodeExtInvDtlVO[] epiExtInvDtlVO=null;
		List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO=null;
//        EpisodeExtInvDtlVO[] episodeExtInvDtlVO=null;
//        EpisodeExtInvDtlVO[] epiExternalInvDtlVO=null;
//        List lstParameter=null;
//		String paraName="";
		
		try
		{
			/*lstParameter=(List)session.getAttribute(OpdConfig.EXT_INV_PARAMETER_LIST);
			for(int i=0;i<lstParameter.size();i++)
			{
				Entry ent=(Entry)lstParameter.get(i);
			    if( ent.getLabel().equalsIgnoreCase(fb.getParaId()))
			    {
			    	paraName=ent.getValue();
			    	break;
			    }
			}*/
			
			lstEpiExtInvDtlVO=(List<EpisodeExtInvDtlVO>)session.getAttribute(OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL);
			
			EpisodeExtInvDtlVO voEpisodeExtInvDtl = new EpisodeExtInvDtlVO();
			if(Integer.parseInt(fb.getRecordTimeHr())<10) fb.setRecordTimeHr("0"+trimTime(fb.getRecordTimeHr()));
			if(Integer.parseInt(fb.getRecordTimeMin())<10) fb.setRecordTimeMin("0"+trimTime(fb.getRecordTimeMin()));
			voEpisodeExtInvDtl.setRecordDate(fb.getRecordDate());
			voEpisodeExtInvDtl.setRecordTime(fb.getRecordTimeHr()+":"+fb.getRecordTimeMin());
			voEpisodeExtInvDtl.setParaId(fb.getParaId());// paraName);
			voEpisodeExtInvDtl.setParaName(fb.getParaName());// Id());
			voEpisodeExtInvDtl.setParaValue(fb.getParaValue());
			voEpisodeExtInvDtl.setRemarks(fb.getRemarks()); // Changes as per CR 19 By Pragya 2013.05.03
			
			if(lstEpiExtInvDtlVO==null)	lstEpiExtInvDtlVO = new ArrayList<EpisodeExtInvDtlVO>();
			lstEpiExtInvDtlVO.add(voEpisodeExtInvDtl);
			
			WebUTIL.setAttributeInSession(request, OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL, lstEpiExtInvDtlVO);
			/*if(epiExtInvDtlVO==null)
			{
				if(Integer.parseInt(fb.getRecordTimeHr())<10) fb.setRecordTimeHr("0"+trimTime(fb.getRecordTimeHr()));
				if(Integer.parseInt(fb.getRecordTimeMin())<10) fb.setRecordTimeMin("0"+trimTime(fb.getRecordTimeMin()));
				episodeExtInvDtlVO=new EpisodeExtInvDtlVO[1];
				episodeExtInvDtlVO[0]=new EpisodeExtInvDtlVO();
				episodeExtInvDtlVO[0].setRecordDate(fb.getRecordDate());
				episodeExtInvDtlVO[0].setRecordTime(fb.getRecordTimeHr()+":"+fb.getRecordTimeMin());
				episodeExtInvDtlVO[0].setParaId(fb.getParaId());// paraName);
				episodeExtInvDtlVO[0].setParaName(fb.getParaName());// Id());
				episodeExtInvDtlVO[0].setParaValue(fb.getParaValue());
				episodeExtInvDtlVO[0].setRemarks(fb.getRemarks()); // Changes as per CR 19 By Pragya 2013.05.03
				
				WebUTIL.setAttributeInSession(request, OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL, episodeExtInvDtlVO);
			}
			else
			{
				epiExternalInvDtlVO=new EpisodeExtInvDtlVO[epiExtInvDtlVO.length+1];
				int i=0;
				for(;i<epiExtInvDtlVO.length;i++)
				{
					epiExternalInvDtlVO[i]=epiExtInvDtlVO[i];
				}
				if(Integer.parseInt(fb.getRecordTimeHr())<10) fb.setRecordTimeHr("0"+trimTime(fb.getRecordTimeHr()));
				if(Integer.parseInt(fb.getRecordTimeMin())<10) fb.setRecordTimeMin("0"+trimTime(fb.getRecordTimeMin()));
				epiExternalInvDtlVO[i]=new EpisodeExtInvDtlVO();
				epiExternalInvDtlVO[i].setRecordDate(fb.getRecordDate());
				epiExternalInvDtlVO[i].setRecordTime(fb.getRecordTimeHr()+":"+fb.getRecordTimeMin());
				epiExternalInvDtlVO[0].setParaId(fb.getParaId());// paraName);
				epiExternalInvDtlVO[0].setParaName(fb.getParaName());// Id());
				epiExternalInvDtlVO[i].setParaValue(fb.getParaValue());
				episodeExtInvDtlVO[0].setRemarks(fb.getRemarks()); // Changes as per CR 19 By Pragya 2013.05.03
				
				WebUTIL.setAttributeInSession(request, OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL, epiExternalInvDtlVO);
			}*/
			
			fb.setRecordTimeHr(fb.getHiddenTimeHr());
			fb.setRecordTimeMin(fb.getHiddenTimeMin());
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
	
	public static void deleteRow(PatientComplaintsFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
//		EpisodeExtInvDtlVO[] epiExtInvDtlVO=null;
		List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO=null;
//        EpisodeExtInvDtlVO[] episodeExtInvDtlVO=null;
		
		try
		{
			lstEpiExtInvDtlVO=(List<EpisodeExtInvDtlVO>)session.getAttribute(OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL);
			
/*			epiExtInvDtlVO=(EpisodeExtInvDtlVO[])session.getAttribute(OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL);
			episodeExtInvDtlVO=new EpisodeExtInvDtlVO[epiExtInvDtlVO.length-1];
			int j=0;
			for(int i=0;i<epiExtInvDtlVO.length;i++)
			{
				if(!fb.getDeleteIndex().equals(String.valueOf(i)))
				{
					episodeExtInvDtlVO[j]=epiExtInvDtlVO[i];
					j++;
				}
			}*/
			
			if(lstEpiExtInvDtlVO!=null && lstEpiExtInvDtlVO.size()>0)
				lstEpiExtInvDtlVO.remove(Integer.parseInt(fb.getDeleteIndex()));
			
			WebUTIL.setAttributeInSession(request, OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL, lstEpiExtInvDtlVO);
//			WebUTIL.setAttributeInSession(request, OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL, episodeExtInvDtlVO);
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
	
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void saveExtInvestigationDtl(PatientComplaintsFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO=null;
		EpisodeExtInvDtlVO[] epiExtInvDtlVO=null;
		EpisodeExtInvDtlVO episodeExtInvDtlVO=null;
		List lstParameter=null;
		PatientDetailVO[] dailyPatientVOs = null;
		String paraName="";
		
		try
		{
			PatientDetailVO patientDetailVO=null;
			if(fb.getIsDirectCall().equals("DESK"))
			{
				/*PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				
				for(int i=0;i<arrayDailyPatVO.length;i++)
				{
					if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
					{
						selectedPatientVO=arrayDailyPatVO[i];
					}
				}*/
				patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
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
			}	
			
			/*lstParameter=(List)session.getAttribute(OpdConfig.EXT_INV_PARAMETER_LIST);
			for(int i=0;i<lstParameter.size();i++)
			{
				Entry ent=(Entry)lstParameter.get(i);
			    if( ent.getLabel().equalsIgnoreCase(fb.getParaId()))
			    {
			    	paraName=ent.getValue();
			    	break;
			    }
			}*/
			
//			lstEpiExtInvDtlVO=new ArrayList<EpisodeExtInvDtlVO>();
//			epiExtInvDtlVO=(EpisodeExtInvDtlVO[])session.getAttribute(OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL);

			lstEpiExtInvDtlVO = (List<EpisodeExtInvDtlVO>)session.getAttribute(OpdConfig.ARR_EXTERNAL_INVESTIGATION_DTL);
			if(lstEpiExtInvDtlVO==null)	lstEpiExtInvDtlVO=new ArrayList<EpisodeExtInvDtlVO>();
			
//			if(epiExtInvDtlVO!=null)
			if(lstEpiExtInvDtlVO!=null && lstEpiExtInvDtlVO.size()>0)
			{
//				for(int i=0;i<epiExtInvDtlVO.length;i++)
				for(EpisodeExtInvDtlVO vo : lstEpiExtInvDtlVO)
				{
					episodeExtInvDtlVO=vo; //new EpisodeExtInvDtlVO();
//					episodeExtInvDtlVO.setParaId(epiExtInvDtlVO[i].getParaId());
//					episodeExtInvDtlVO.setRecordDate(epiExtInvDtlVO[i].getRecordDate());
//					episodeExtInvDtlVO.setRecordTime(epiExtInvDtlVO[i].getRecordTime());
//					episodeExtInvDtlVO.setParaValue(epiExtInvDtlVO[i].getParaValue());
					episodeExtInvDtlVO.setPatCrNo(fb.getPatCrNo());
					
					episodeExtInvDtlVO.setExtLabAdd(fb.getExtLabAdd());
					episodeExtInvDtlVO.setExtLabContactNo(fb.getExtLabContactNo());
					episodeExtInvDtlVO.setExtLabName(fb.getExtLabName());
					episodeExtInvDtlVO.setTestConductedFrom(fb.getTestConductedFrom());
					if(fb.getIsDirectCall().equals("DESK"))
					{
						episodeExtInvDtlVO.setEntryMode(OpdConfig.EXT_INV_ENTRY_MODE_BY_HOSPITAL);
						HelperMethods.populate(episodeExtInvDtlVO, patientDetailVO);
					}	
					if(fb.getIsDirectCall().equals("DIRECT"))
					{
						episodeExtInvDtlVO.setEntryMode(OpdConfig.EXT_INV_ENTRY_MODE_BY_PATIENT);
					}
					//lstEpiExtInvDtlVO.add(episodeExtInvDtlVO);
				}
			}
			if(!fb.getParaId().equals(""))
			{
				if(Integer.parseInt(fb.getRecordTimeHr())<10) fb.setRecordTimeHr("0"+trimTime(fb.getRecordTimeHr()));
				if(Integer.parseInt(fb.getRecordTimeMin())<10) fb.setRecordTimeMin("0"+trimTime(fb.getRecordTimeMin()));
				episodeExtInvDtlVO=new EpisodeExtInvDtlVO();
				episodeExtInvDtlVO.setParaId(fb.getParaId());// paraName);
				episodeExtInvDtlVO.setRecordDate(fb.getRecordDate());
				episodeExtInvDtlVO.setRecordTime(fb.getRecordTimeHr()+":"+fb.getRecordTimeMin());
				episodeExtInvDtlVO.setParaValue(fb.getParaValue());
				episodeExtInvDtlVO.setPatCrNo(fb.getPatCrNo());
				episodeExtInvDtlVO.setRemarks(fb.getRemarks()); // Changes as per CR 19 By Pragya 2013.05.03
				
				episodeExtInvDtlVO.setExtLabAdd(fb.getExtLabAdd());
				episodeExtInvDtlVO.setExtLabContactNo(fb.getExtLabContactNo());
				episodeExtInvDtlVO.setExtLabName(fb.getExtLabName());
				episodeExtInvDtlVO.setTestConductedFrom(fb.getTestConductedFrom());
				if(fb.getIsDirectCall().equals("DESK"))
				{
					episodeExtInvDtlVO.setEntryMode(OpdConfig.EXT_INV_ENTRY_MODE_BY_HOSPITAL);
					HelperMethods.populate(episodeExtInvDtlVO, patientDetailVO);
				}	
				if(fb.getIsDirectCall().equals("DIRECT"))
				{
					episodeExtInvDtlVO.setEntryMode(OpdConfig.EXT_INV_ENTRY_MODE_BY_PATIENT);
				}
				lstEpiExtInvDtlVO.add(episodeExtInvDtlVO);
			}
			
			PatientComplaintsDATA.saveExtInvestigationDtl(lstEpiExtInvDtlVO,getUserVO(request));
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		

			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static String trimTime(String num)
	{
		try
		{
			while(num.substring(0,1).equals("0"))	
				num=num.substring(1);
			
			if(num.equals(""))
				num="0";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			num="0";
		}
		return num;
	}
}
