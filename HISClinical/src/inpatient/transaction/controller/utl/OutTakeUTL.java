package inpatient.transaction.controller.utl;

/**
 * @copyright CDAC
 * @developer Hruday Meher
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.OutTakeDATA;
import inpatient.transaction.controller.fb.OutTakeFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;

public class OutTakeUTL extends ControllerUTIL
{
	/**Getting The Essential Data for Intake Outtake Process
	 * @param fb
	 * @param request
	 */
	public static void getOutParameterList(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		double totalOuttake=0.0;
		double totalIntake=0.0;
		double balance=0.0;
		
		try
		{
			setSysdate(request);
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			fb.setOutTakeTimeHr(time.split(":")[0]);
			fb.setOutTakeTimeMin(time.split(":")[1]);
			fb.setHiddenTimeHr(fb.getOutTakeTimeHr());
			fb.setHiddenTimeMin(fb.getOutTakeTimeMin());
			fb.setInTakeTimeHr(time.split(":")[0]);
			fb.setInTakeTimeMin(time.split(":")[1]);
			
			String date = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		//	String date=sys.split(" ")[0];
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, -1);		//Getting The Previous Date 	
			String prevDate = sdf.format(c.getTime());  
			fb.setPrevDate(prevDate);
			
			String intakeType=InpatientConfig.INTAKEOUT_MODE_INTAKE;
			String outtakeType=InpatientConfig.INTAKEOUT_MODE_OUTTAKE;
			
			//List of intake parameter list
			List lstOutPara=OutTakeDATA.getOutParameterList(intakeType,getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST, lstOutPara);
			
			//List of intake route list
			List lstRoute=OutTakeDATA.getRouteList(intakeType,getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_OUT_ROUTE_LIST, lstRoute);
			
			//List of outtake parameter list
			List lstInPara=OutTakeDATA.getOutParameterList(outtakeType,getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST, lstInPara);
			
			//List of outtake route list
			List lstInRoute=OutTakeDATA.getRouteList(outtakeType,getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ESSENTIAL_IN_ROUTE_LIST, lstInRoute);
			
			//Patient today intake detail
			PatIntakeOutDtlVO[] patIntakeDtlVO=OutTakeDATA.getIntakeSummary(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.PATIENT_INTAKE_SUMMARY , patIntakeDtlVO);
			if(patIntakeDtlVO.length==0)
			{
				fb.setIntakeSummaryStatus("No Intake Summary Found");
				fb.setTotalIntake(String.valueOf(totalIntake));
			}	
			else
			{
				
				for(int i=0;i<patIntakeDtlVO.length;i++)
				{
					totalIntake=totalIntake+(Double.parseDouble(patIntakeDtlVO[i].getVolume()));
				}
				
				fb.setTotalIntake(String.valueOf(totalIntake));
			}
			
			//Patient today outtake detail
			PatIntakeOutDtlVO[] patOuttakeDtlVO=OutTakeDATA.getOuttakeSummary(fb.getPatCrNo(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.PATIENT_OUTTAKE_SUMMARY , patOuttakeDtlVO);
			if(patOuttakeDtlVO.length==0)
			{	
				fb.setOuttakeSummaryStatus("No Outtake Summary Found");
				fb.setTotalOuttake(String.valueOf(totalOuttake));
			}
			else
			{
				
				for(int i=0;i<patOuttakeDtlVO.length;i++)
				{
					totalOuttake=totalOuttake+(Double.parseDouble(patOuttakeDtlVO[i].getVolume()));
				}
				fb.setTotalOuttake(String.valueOf(totalOuttake));
			}
			balance=totalIntake-totalOuttake;
			fb.setBalance(String.valueOf(balance));
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
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	/** Saving intake outtake detail 
	 * @param fb
	 * @param request
	 */
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void saveOutParameter(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		//PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDtlVO=null;
		PatIntakeOutDtlVO[] patInDtlVO=null;
		List<PatIntakeOutDtlVO> listPatIntakeOutTake=null;
	//	int count;
		PatIntakeOutDtlVO patIntakeOutDtlVO=null;
		List lstOutPara=null;
		List lstInPara=null;
		String outPparaName="";
		String inPparaName="";
		PatientDetailVO[] dailyPatientVOs = null;

		
		try
		{
			/*DailyPatientVO[] arrayDailyPatVO=(DailyPatientVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(selectedPatientVO == null || !selectedPatientVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						selectedPatientVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			
			listPatIntakeOutTake=new ArrayList<PatIntakeOutDtlVO>();
			
			patOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL);
			patInDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL);
			
			
			if(!fb.getVolume().equals(""))
			{	
				lstOutPara=(List)session.getAttribute(InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST);
				for(int i=0;i<lstOutPara.size();i++)
				{
					Entry ent=(Entry)lstOutPara.get(i);
				    if( ent.getValue().equalsIgnoreCase(fb.getInTakeOutParaId()))
				    {
				    	outPparaName=ent.getLabel();
				    	break;
				    }
				}
				
				if(Integer.parseInt(fb.getOutTakeTimeHr())<10) fb.setOutTakeTimeHr("0"+trimTime(fb.getOutTakeTimeHr()));
				if(Integer.parseInt(fb.getOutTakeTimeMin())<10) fb.setOutTakeTimeMin("0"+trimTime(fb.getOutTakeTimeMin()));
				patIntakeOutDtlVO=new PatIntakeOutDtlVO();
				patIntakeOutDtlVO.setOutTakeDate(fb.getOutTakeDate());
				patIntakeOutDtlVO.setOutTakeTime(fb.getOutTakeTimeHr()+":"+fb.getOutTakeTimeMin());
				patIntakeOutDtlVO.setInTakeOutTime(fb.getOutTakeDate()+" "+fb.getOutTakeTimeHr()+":"+fb.getOutTakeTimeMin());
				patIntakeOutDtlVO.setInTakeOutParaId(fb.getInTakeOutParaId());
				patIntakeOutDtlVO.setInTakeOutParaName(outPparaName);
				patIntakeOutDtlVO.setRemarks(fb.getRemarks());
				patIntakeOutDtlVO.setSnomdCIdRemarks(fb.getSnomdCIdRemarks());
				patIntakeOutDtlVO.setSnomdPTRemarks(fb.getSnomdPTRemarks());
				patIntakeOutDtlVO.setVolume(fb.getVolume());
				patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_OUTTAKE);
				patIntakeOutDtlVO.setRouteId(fb.getOutTakeRouteId());
				patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_INTAKE_OUTTAKE);
				OutTakeDATA.getSnomedIdTerm(patIntakeOutDtlVO,getUserVO(request));	
				//patIntakeOutDtlVO.setConceptId(fb.getConceptId());
				//patIntakeOutDtlVO.setPrefferedTerm(fb.getPrefferedTerm());
				HelperMethods.populate(patIntakeOutDtlVO, selectedPatientVO);
				
				listPatIntakeOutTake.add(patIntakeOutDtlVO);
			}
			if(patOutDtlVO!=null)
			{
				int len=patOutDtlVO.length;
				for(int i=0;i<len;i++)
				{
					patIntakeOutDtlVO=new PatIntakeOutDtlVO();
					patIntakeOutDtlVO.setOutTakeDate(patOutDtlVO[i].getOutTakeDate());
					patIntakeOutDtlVO.setOutTakeTime(patOutDtlVO[i].getOutTakeTime());
					patIntakeOutDtlVO.setInTakeOutTime(patOutDtlVO[i].getOutTakeDate()+" "+patOutDtlVO[i].getOutTakeTime());
					patIntakeOutDtlVO.setInTakeOutParaId(patOutDtlVO[i].getInTakeOutParaId());
					patIntakeOutDtlVO.setInTakeOutParaName(patOutDtlVO[i].getInTakeOutParaName());
					patIntakeOutDtlVO.setRemarks(patOutDtlVO[i].getRemarks());
					patIntakeOutDtlVO.setSnomdCIdRemarks(patOutDtlVO[i].getSnomdCIdRemarks());
					patIntakeOutDtlVO.setSnomdPTRemarks(patOutDtlVO[i].getSnomdPTRemarks());
					patIntakeOutDtlVO.setSnomedParaId(patOutDtlVO[i].getSnomedParaId());
					patIntakeOutDtlVO.setSnomedPtermPara(patOutDtlVO[i].getSnomedPtermPara());
					patIntakeOutDtlVO.setVolume(patOutDtlVO[i].getVolume());
					patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_OUTTAKE);
					patIntakeOutDtlVO.setRouteId(patOutDtlVO[i].getRouteId());
					patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_INTAKE_OUTTAKE);
					HelperMethods.populate(patIntakeOutDtlVO, selectedPatientVO);
					
					listPatIntakeOutTake.add(patIntakeOutDtlVO);
				}
			}
			
			if(!fb.getIntakeVolume().equals(""))
			{
				lstInPara=(List)session.getAttribute(InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST);
				for(int i=0;i<lstInPara.size();i++)
				{
					Entry ent=(Entry)lstInPara.get(i);
				    if( ent.getValue().equalsIgnoreCase(fb.getIntakeParaId()))
				    {
				    	inPparaName=ent.getLabel();
				    	break;
				    }
				}
				if(Integer.parseInt(fb.getInTakeTimeHr())<10) fb.setInTakeTimeHr("0"+trimTime(fb.getInTakeTimeHr()));
				if(Integer.parseInt(fb.getInTakeTimeMin())<10) fb.setInTakeTimeMin("0"+trimTime(fb.getInTakeTimeMin()));
				patIntakeOutDtlVO=new PatIntakeOutDtlVO();
				patIntakeOutDtlVO.setOutTakeDate(fb.getInTakeDate());
				patIntakeOutDtlVO.setOutTakeTime(fb.getInTakeTimeHr()+":"+fb.getInTakeTimeMin());
				patIntakeOutDtlVO.setInTakeOutTime(fb.getInTakeDate()+" "+fb.getInTakeTimeHr()+":"+fb.getInTakeTimeMin());
				patIntakeOutDtlVO.setInTakeOutParaId(fb.getIntakeParaId());
				patIntakeOutDtlVO.setInTakeOutParaName(inPparaName);
				patIntakeOutDtlVO.setRemarks(fb.getIntakeRemarks());
				patIntakeOutDtlVO.setSnomdCIdRemarks(fb.getSnomdCIdintakeRemarks());
				patIntakeOutDtlVO.setSnomdPTRemarks(fb.getSnomdPTintakeRemarks());
				patIntakeOutDtlVO.setVolume(fb.getIntakeVolume());
				patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
				patIntakeOutDtlVO.setRouteId(fb.getInTakeRouteId());
				patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_INTAKE_OUTTAKE);
				OutTakeDATA.getSnomedIdTerm(patIntakeOutDtlVO,getUserVO(request));
				HelperMethods.populate(patIntakeOutDtlVO, selectedPatientVO);
				listPatIntakeOutTake.add(patIntakeOutDtlVO);
			}
			if(patInDtlVO!=null)
			{
				int len=patInDtlVO.length;
				for(int i=0;i<len;i++)
				{
					patIntakeOutDtlVO=new PatIntakeOutDtlVO();
					patIntakeOutDtlVO.setOutTakeDate(patInDtlVO[i].getOutTakeDate());
					patIntakeOutDtlVO.setOutTakeTime(patInDtlVO[i].getOutTakeTime());
					patIntakeOutDtlVO.setInTakeOutTime(patInDtlVO[i].getOutTakeDate()+" "+patInDtlVO[i].getOutTakeTime());
					patIntakeOutDtlVO.setInTakeOutParaId(patInDtlVO[i].getInTakeOutParaId());
					patIntakeOutDtlVO.setInTakeOutParaName(patInDtlVO[i].getInTakeOutParaName());
					patIntakeOutDtlVO.setRemarks(patInDtlVO[i].getRemarks());
					patIntakeOutDtlVO.setSnomdCIdRemarks(patInDtlVO[i].getSnomdCIdRemarks());
					patIntakeOutDtlVO.setSnomdPTRemarks(patInDtlVO[i].getSnomdPTRemarks());
					patIntakeOutDtlVO.setSnomedParaId(patInDtlVO[i].getSnomedParaId());
					patIntakeOutDtlVO.setSnomedPtermPara(patInDtlVO[i].getSnomedPtermPara());
					patIntakeOutDtlVO.setRemarks(patInDtlVO[i].getRemarks());
					patIntakeOutDtlVO.setVolume(patInDtlVO[i].getVolume());
					patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
					patIntakeOutDtlVO.setRouteId(patInDtlVO[i].getRouteId());
					patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_INTAKE_OUTTAKE);
					HelperMethods.populate(patIntakeOutDtlVO, selectedPatientVO);
					
					listPatIntakeOutTake.add(patIntakeOutDtlVO);
				}
			}
			
			
			OutTakeDATA.saveOutParameter(listPatIntakeOutTake,getUserVO(request));			
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(request, fb.getDeskMenuId());
			objStatus.add(Status.DONE,"","Record added Successfully");
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
	
	/**Getting all the intake or outtake detail of the patient in a popup based on intake outtake mode 
	 * @param fb
	 * @param request
	 */
	public static void getOutParaDetail(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		double total=0.0;
		PatientDetailVO[] dailyPatientVOs = null;

		
		try
		{
			/*PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(selectedPatientVO == null || !selectedPatientVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						selectedPatientVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			
			patIntakeOutDtlVO=OutTakeDATA.getOutParaDetail(fb.getIntakeOutMode(),selectedPatientVO,getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_PATIENT_OUTTAKE_DETAIL, patIntakeOutDtlVO);
			
			for(int i=0;i<patIntakeOutDtlVO.length;i++)
			{
				total=total+(Double.parseDouble(patIntakeOutDtlVO[i].getVolume()));
			}
			if(fb.getIntakeOutMode().equals(InpatientConfig.INTAKEOUT_MODE_INTAKE))
				fb.setTotalViewIntake(String.valueOf(total));
			if(fb.getIntakeOutMode().equals(InpatientConfig.INTAKEOUT_MODE_OUTTAKE))
				fb.setTotalViewOuttake(String.valueOf(total));
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	/**Adding a new row for outtake detail
	 * @param fb
	 * @param request
	 */
	public static void addRow(OutTakeFB fb,HttpServletRequest request)
	 {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDetailVO=null;
		List lstOutPara=null;
		List lstRuoteId=null;
		String paraName="";
		String routeName="";
		
		try
		{
			lstOutPara=(List)session.getAttribute(InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST);
			for(int i=0;i<lstOutPara.size();i++)
			{
				Entry ent=(Entry)lstOutPara.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getInTakeOutParaId()))
			    {
			    	paraName=ent.getLabel();
			    	break;
			    }
			}
			
			lstRuoteId=(List)session.getAttribute(InpatientConfig.ESSENTIAL_OUT_ROUTE_LIST);
			for(int i=0;i<lstRuoteId.size();i++)
			{
				Entry ent=(Entry)lstRuoteId.get(i);
				if(ent.getValue().equalsIgnoreCase(fb.getOutTakeRouteId()))
				{
					routeName=ent.getLabel();
					break;
				}
			}
			List newList = new ArrayList(lstOutPara);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getInTakeOutParaId()); 
			WebUTIL.setAttributeInSession(request,InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST,newList);
			
			patIntakeOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL);
			
			if(patIntakeOutDtlVO==null)
			{
				if(Integer.parseInt(fb.getOutTakeTimeHr())<10) fb.setOutTakeTimeHr("0"+trimTime(fb.getOutTakeTimeHr()));
				if(Integer.parseInt(fb.getOutTakeTimeMin())<10) fb.setOutTakeTimeMin("0"+trimTime(fb.getOutTakeTimeMin()));
				patOutDtlVO=new PatIntakeOutDtlVO[1];
				patOutDtlVO[0]=new PatIntakeOutDtlVO();
				patOutDtlVO[0].setInTakeOutParaName(paraName);
				patOutDtlVO[0].setInTakeOutParaId(fb.getInTakeOutParaId());
				patOutDtlVO[0].setVolume(fb.getVolume());
				patOutDtlVO[0].setRemarks(fb.getRemarks());
				patOutDtlVO[0].setSnomdPTRemarks(fb.getSnomdPTRemarks());
				patOutDtlVO[0].setSnomdCIdRemarks(fb.getSnomdCIdRemarks());
				patOutDtlVO[0].setOutTakeDate(fb.getOutTakeDate());
				patOutDtlVO[0].setOutTakeTime(fb.getOutTakeTimeHr()+":"+fb.getOutTakeTimeMin());
				patOutDtlVO[0].setRouteId(fb.getOutTakeRouteId());
				patOutDtlVO[0].setRouteName(routeName);
				OutTakeDATA.getSnomedIdTerm(patOutDtlVO[0],getUserVO(request));
				
				WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL, patOutDtlVO);
			}
			else
			{
				patOutDetailVO= new PatIntakeOutDtlVO[patIntakeOutDtlVO.length+1];
				int i=0;
				for(;i<patIntakeOutDtlVO.length;i++)
				{
					patOutDetailVO[i]=patIntakeOutDtlVO[i];
				}
				if(Integer.parseInt(fb.getOutTakeTimeHr())<10) fb.setOutTakeTimeHr("0"+trimTime(fb.getOutTakeTimeHr()));
				if(Integer.parseInt(fb.getOutTakeTimeMin())<10) fb.setOutTakeTimeMin("0"+trimTime(fb.getOutTakeTimeMin()));
				
				patOutDetailVO[i]=new PatIntakeOutDtlVO();
				
				patOutDetailVO[i].setInTakeOutParaName(paraName);
				patOutDetailVO[i].setInTakeOutParaId(fb.getInTakeOutParaId());
				patOutDetailVO[i].setVolume(fb.getVolume());
				patOutDetailVO[i].setRemarks(fb.getRemarks());
				patOutDetailVO[i].setSnomdPTRemarks(fb.getSnomdPTintakeRemarks());
				patOutDetailVO[i].setSnomdCIdRemarks(fb.getSnomdCIdintakeRemarks());
				patOutDetailVO[i].setOutTakeDate(fb.getOutTakeDate());
				patOutDetailVO[i].setOutTakeTime(fb.getOutTakeTimeHr()+":"+fb.getOutTakeTimeMin());
				patOutDetailVO[i].setRouteId(fb.getOutTakeRouteId());
				patOutDetailVO[i].setRouteName(routeName);
				OutTakeDATA.getSnomedIdTerm(patOutDetailVO[i],getUserVO(request));
				
				WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL, patOutDetailVO);
			}
			fb.setOutTakeTimeHr(fb.getHiddenTimeHr());
			fb.setOutTakeTimeMin(fb.getHiddenTimeMin());
			if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL)!=null)
				fb.setOuttakeVOExistFlag("1");
		
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
	
	/**Deleting a row for outtake detail
	 * @param fb
	 * @param request
	 */
	public static void deleteRow(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDtlVO=null;
		
		try
		{
			List lstOutPara=(List)session.getAttribute(InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST);
			List newList = new ArrayList(lstOutPara);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenParaId(),fb.getHiddenParaName()); 
			WebUTIL.setAttributeInSession(request,InpatientConfig.ESSENTIAL_OUT_PARAMETER_LIST,newList);
			
			patIntakeOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL);
			patOutDtlVO=new PatIntakeOutDtlVO[patIntakeOutDtlVO.length-1];
			
			int j=0;
			for(int i=0;i<patIntakeOutDtlVO.length;i++)
			{
				if(!fb.getHiddenParaId().equals(patIntakeOutDtlVO[i].getInTakeOutParaId()))
				{
					patOutDtlVO[j]=patIntakeOutDtlVO[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request,InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL,patOutDtlVO);
			if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_OUTTAKE_DETAIL)==null)
				fb.setOuttakeVOExistFlag("0");
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
	
	/**Adding a new row for intake detail
	 * @param fb
	 * @param request
	 */
	public static void addIntakeRow(OutTakeFB fb,HttpServletRequest request)
	 {
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDetailVO=null;
		List lstInPara=null;
		List lstInRuoteId=null;
		String paraName="";
		String routeName="";
		
		try
		{
			lstInPara=(List)session.getAttribute(InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST);
			for(int i=0;i<lstInPara.size();i++)
			{
				Entry ent=(Entry)lstInPara.get(i);
			    if( ent.getValue().equalsIgnoreCase(fb.getIntakeParaId()))
			    {
			    	paraName=ent.getLabel();
			    	break;
			    }
			}
			
			lstInRuoteId=(List)session.getAttribute(InpatientConfig.ESSENTIAL_IN_ROUTE_LIST);
			for(int i=0;i<lstInRuoteId.size();i++)
			{
				Entry ent=(Entry)lstInRuoteId.get(i);
				if(ent.getValue().equalsIgnoreCase(fb.getInTakeRouteId()))
				{
					routeName=ent.getLabel();
					break;
				}
			}
			List newList = new ArrayList(lstInPara);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getIntakeParaId()); 
			WebUTIL.setAttributeInSession(request,InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST,newList);
			
			patIntakeOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL);
			
			if(patIntakeOutDtlVO==null)
			{
				if(Integer.parseInt(fb.getInTakeTimeHr())<10) fb.setInTakeTimeHr("0"+trimTime(fb.getInTakeTimeHr()));
				if(Integer.parseInt(fb.getInTakeTimeMin())<10) fb.setInTakeTimeMin("0"+trimTime(fb.getInTakeTimeMin()));
				patOutDtlVO=new PatIntakeOutDtlVO[1];
				patOutDtlVO[0]=new PatIntakeOutDtlVO();
				patOutDtlVO[0].setInTakeOutParaName(paraName);
				patOutDtlVO[0].setInTakeOutParaId(fb.getIntakeParaId());
				patOutDtlVO[0].setVolume(fb.getIntakeVolume());
				patOutDtlVO[0].setRemarks(fb.getIntakeRemarks());
				patOutDtlVO[0].setSnomdCIdRemarks(fb.getSnomdCIdRemarks());
				patOutDtlVO[0].setSnomdPTRemarks(fb.getSnomdPTRemarks());
				patOutDtlVO[0].setOutTakeDate(fb.getInTakeDate());
				patOutDtlVO[0].setOutTakeTime(fb.getInTakeTimeHr()+":"+fb.getInTakeTimeMin());
				patOutDtlVO[0].setRouteId(fb.getInTakeRouteId());
				patOutDtlVO[0].setRouteName(routeName);
				OutTakeDATA.getSnomedIdTerm(patOutDtlVO[0],getUserVO(request));
				
				WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL, patOutDtlVO);
			}
			else
			{
				patOutDetailVO= new PatIntakeOutDtlVO[patIntakeOutDtlVO.length+1];
				int i=0;
				for(;i<patIntakeOutDtlVO.length;i++)
				{
					patOutDetailVO[i]=patIntakeOutDtlVO[i];
				}
				if(Integer.parseInt(fb.getInTakeTimeHr())<10) fb.setInTakeTimeHr("0"+trimTime(fb.getInTakeTimeHr()));
				if(Integer.parseInt(fb.getInTakeTimeMin())<10) fb.setInTakeTimeMin("0"+trimTime(fb.getInTakeTimeMin()));
				patOutDetailVO[i]=new PatIntakeOutDtlVO();
				
				patOutDetailVO[i].setInTakeOutParaName(paraName);
				patOutDetailVO[i].setInTakeOutParaId(fb.getIntakeParaId());
				patOutDetailVO[i].setVolume(fb.getIntakeVolume());
				patOutDetailVO[i].setRemarks(fb.getIntakeRemarks());
				patOutDetailVO[i].setSnomdCIdRemarks(fb.getSnomdCIdintakeRemarks());
				patOutDetailVO[i].setSnomdPTRemarks(fb.getSnomdPTintakeRemarks());
				patOutDetailVO[i].setOutTakeDate(fb.getInTakeDate());
				patOutDetailVO[i].setOutTakeTime(fb.getInTakeTimeHr()+":"+fb.getInTakeTimeMin());
				patOutDetailVO[i].setRouteId(fb.getInTakeRouteId());
				patOutDetailVO[i].setRouteName(routeName);
				OutTakeDATA.getSnomedIdTerm(patOutDetailVO[i],getUserVO(request));
				
				WebUTIL.setAttributeInSession(request, InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL, patOutDetailVO);
			}
			fb.setInTakeTimeHr(fb.getHiddenTimeHr());
			fb.setInTakeTimeMin(fb.getHiddenTimeMin());
			if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL)!=null)
				fb.setIntakeVOExistFlag("1");
		
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
	
	/**Deleting a row for intake detail
	 * @param fb
	 * @param request
	 */
	public static void deleteIntakeRow(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		PatIntakeOutDtlVO[] patOutDtlVO=null;
		
		try
		{
			List lstInPara=(List)session.getAttribute(InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST);
			List newList = new ArrayList(lstInPara);
			newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenParaId(),fb.getHiddenParaName()); 
			WebUTIL.setAttributeInSession(request,InpatientConfig.ESSENTIAL_IN_PARAMETER_LIST,newList);
			
			patIntakeOutDtlVO=(PatIntakeOutDtlVO[])session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL);
			patOutDtlVO=new PatIntakeOutDtlVO[patIntakeOutDtlVO.length-1];
			
			int j=0;
			for(int i=0;i<patIntakeOutDtlVO.length;i++)
			{
				if(!fb.getHiddenParaId().equals(patIntakeOutDtlVO[i].getInTakeOutParaId()))
				{
					patOutDtlVO[j]=patIntakeOutDtlVO[i];
					j++;
				}
			}
			WebUTIL.setAttributeInSession(request,InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL,patOutDtlVO);
			if(session.getAttribute(InpatientConfig.ARR_PAT_ADD_INTAKE_DETAIL)==null)
				fb.setIntakeVOExistFlag("0");
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
	
	/**Getting essential data for intake outtake summary
	 * @param fb
	 * @param request
	 */
	public static void getEssentialForAllSummary(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			setSysdate(request);
			
			objStatus.add(Status.NEW );
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
	
	/**Getting intake outtake summary detail based on date range
	 * @param fb
	 * @param request
	 */
	public static void viewSummary(OutTakeFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		HttpSession session=request.getSession();
		PatIntakeOutDtlVO[] patIntakeOutDtlVO=null;
		PatIntakeOutDtlVO[] patIntakeVO=null;
		PatIntakeOutDtlVO[] patOutTakeDtlVO=null;
		int inCount=0;
		int outCount=0;
		double totalOuttake=0.0;
		double totalIntake=0.0;
		double balance=0.0;
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			/*PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}*/
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(selectedPatientVO == null || !selectedPatientVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						selectedPatientVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			patIntakeOutDtlVO=OutTakeDATA.getViewSummaryDetail(selectedPatientVO.getPatAdmNo(),fb.getFromDate(),fb.getToDate(),getUserVO(request));
			
			if(patIntakeOutDtlVO!=null)
			{
				for(int i=0;i<patIntakeOutDtlVO.length;i++)
				{
					if(patIntakeOutDtlVO[i].getInTakeOutMode().equals(InpatientConfig.INTAKEOUT_MODE_INTAKE))
					{
						inCount++;
					}
					else
					{
						outCount++;
					}
				}
				
				patIntakeVO=new PatIntakeOutDtlVO[inCount];
				patOutTakeDtlVO=new PatIntakeOutDtlVO[outCount];
				int j=0,k=0;
				for(int i=0;i<patIntakeOutDtlVO.length;i++)
				{
					if(patIntakeOutDtlVO[i].getInTakeOutMode().equals(InpatientConfig.INTAKEOUT_MODE_INTAKE))
					{
						patIntakeVO[j]=patIntakeOutDtlVO[i];
						totalIntake=totalIntake+(Double.parseDouble(patIntakeOutDtlVO[i].getVolume()));
						j++;
					}
					else
					{
						patOutTakeDtlVO[k]=patIntakeOutDtlVO[i];
						totalOuttake=totalOuttake+(Double.parseDouble(patIntakeOutDtlVO[i].getVolume()));
						k++;
					}
				}
				
			}
			
			balance=totalIntake-totalOuttake;
			
			fb.setViewSummarybalance(String.valueOf(balance));
			fb.setTotalViewSummaryIntake(String.valueOf(totalIntake));
			fb.setTotalViewSummaryOuttake(String.valueOf(totalOuttake));
			
			WebUTIL.setAttributeInSession(request, InpatientConfig.PATIENT_INTAKE_VIEW_SUMMARY, patIntakeVO);
			WebUTIL.setAttributeInSession(request, InpatientConfig.PATIENT_OUTTAKE_VIEW_SUMMARY, patOutTakeDtlVO);
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
}
