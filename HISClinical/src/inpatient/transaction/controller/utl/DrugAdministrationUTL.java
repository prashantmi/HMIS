package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DrugAdministrationDATA;
import inpatient.transaction.controller.fb.DrugAdministrationFB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;

public class DrugAdministrationUTL extends ControllerUTIL
{
	/*
	 * This function is used to get essential detail for drug administration.
	 * 
	 */
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		List lstColorCodes=new ArrayList();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			
			String sys=(String)session.getAttribute(Config.SYSDATE);

			String time=sys.split(" ")[1];

			_fb.setSysHours(time.split(":")[0]);
			_fb.setSysMinut(time.split(":")[1]);
						
			_fb.setSysDate(sysadteString);
			_fb.setTimeLimit(InpatientConfig.TIME_LIMIT_FOR_DRUG);   
			//_fb.setBeforeTimeLimit(InpatientConfig.BEFORE_TIME_LIMIT_FOR_DRUG); Date: 20.10.2016
			
			
			//setting colourcode
			
			Entry entConsent= new Entry("Consent rasied for this drug",InpatientConfig.IPD_COLOR_CODE_COSENT);
			lstColorCodes.add(entConsent);
			Entry entAllergy= new Entry("Patient has allergy from this drug",InpatientConfig.IPD_COLOR_CODE_ALLERGY);
			lstColorCodes.add(entAllergy);
			Entry entReaction= new Entry("This drug has been reacted to patient",InpatientConfig.IPD_COLOR_CODE_REACTION);
			lstColorCodes.add(entReaction);
			Entry entTime= new Entry("This drug will enable before two hour of drug time",InpatientConfig.IPD_COLOR_CODE_TIME);
			lstColorCodes.add(entTime);			
			session.setAttribute(InpatientConfig.IPD_DRUG_LIST_COLOR_CODIFICATION_INFO, lstColorCodes);
			
			// Setting Desk Essentials
			// Episode, Visit, Admission No
			/*PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}*/

			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setAdmissionNo(voDP.getPatAdmNo());
			_fb.setAdmissionDate(voDP.getAdmDateTime());
			_fb.setDepartmentUnitcode(voDP.getDepartmentUnitCode());
			
			mpEssentials=DrugAdministrationDATA.getPatTreatmentDetailEssential(voDP, userVO);
			
						
			/*
			List<PatDrugTreatmentDetailVO> prevLast = new ArrayList<PatDrugTreatmentDetailVO>();	
			
			
			//taking the last visit of patient
			int maxVisit = 0;
			for(PatDrugTreatmentDetailVO vo : prevAll)
				if(Integer.parseInt(vo.getEpisodeVisitNo())>maxVisit)
					maxVisit=Integer.parseInt(vo.getEpisodeVisitNo());
			
			//getting last visit(drug detail)
			List<PatDrugTreatmentDetailVO> prevAll = (List<PatDrugTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST);
			for(PatDrugTreatmentDetailVO vo : prevAll)
			{
				if(vo.getEntryDate().equals(maxEntryDate))
				{
					prevLast.add(vo);
				}
			}
			
			*/
			List<PatDrugTreatmentDetailVO> prevAll = (List<PatDrugTreatmentDetailVO>)mpEssentials.get(InpatientConfig.PREV_ALL_DRUG_DETAIL_LIST_OF_PATIENT);
			String maxEntryDate=(String)mpEssentials.get(InpatientConfig.MAX_ENTRY_DATE_FROMDRUGDETAIL_BY_CRNO);
			List<PatDrugTreatmentDetailVO> prevLast = new ArrayList<PatDrugTreatmentDetailVO>();
			
				
			
			for(PatDrugTreatmentDetailVO vo : prevAll)
			//	if(vo.getEpisodeVisitNo().equals(Integer.toString(maxVisit)))
				if(vo.getEntryDate().equals(maxEntryDate))	
				{
					//This is for showing the value of empty stomach
					if(vo.getIsEmptyStomach().equals(InpatientConfig.IS_EMPTY_STOMATCH_YES))
					{
						vo.setIsEmptyStomach("Yes");
					}
					else
					{
						vo.setIsEmptyStomach("No");
					}
					// Removing revoked drug
					if(!(vo.getRxContinue().equals(OpdConfig.REVOKE)))
					{
						prevLast.add(vo);
					}
				 }
			
			
		
			//getting SOS drug list
			DrugFrequencyMstVO[] drugFrequencyMstVO=(DrugFrequencyMstVO[])mpEssentials.get(InpatientConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			List sosDrugList=new ArrayList();
			
			Iterator sosItr=prevLast.iterator();
			while(sosItr.hasNext())
			{
				PatDrugTreatmentDetailVO patTreatVo=(PatDrugTreatmentDetailVO)sosItr.next();
				
				for(int i=0;i<drugFrequencyMstVO.length;i++)
				{
					DrugFrequencyMstVO freqVO=drugFrequencyMstVO[i];
					
					if(patTreatVo.getFrequencyId().equals(freqVO.getFrequencyId()))
					{
						if(freqVO.getFrequency().equals("0"))
						{
							Entry obj=new Entry();
							obj.setLabel(patTreatVo.getDrugName());
							obj.setValue(patTreatVo.getDrugId()+ '#'+patTreatVo.getDoseId()+'#'+patTreatVo.getDoseName());
							
							
							sosDrugList.add(obj);
						}
					}
				}
			}
			
			//concatinating itemId with itemTypeId and ivFluid flag in sosDrugList
			List drugLst=(List)mpEssentials.get(InpatientConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator itemtypeItr=sosDrugList.iterator();
			while(itemtypeItr.hasNext())
			{
				Entry sosObj=(Entry)itemtypeItr.next();
				Iterator drugLstItr=drugLst.iterator();
				while(drugLstItr.hasNext())
				{
					Entry drugObj=(Entry)drugLstItr.next();
					String[] drugIdandItemtypeId=drugObj.getValue().split("#");
					String[] parts=sosObj.getValue().split("#");
						if(drugIdandItemtypeId[0].equals(parts[0]))
						{
							String value=sosObj.getValue()+"#"+drugIdandItemtypeId[1]+"#"+drugIdandItemtypeId[2]+"#"+drugIdandItemtypeId[3]+"#"+drugIdandItemtypeId[4];
							sosObj.setValue(value);
						}
				}
			 }
			
			session.setAttribute(InpatientConfig.SOS_DRUG_LIST_FOR_PATIENT, sosDrugList);
			
			// For getting all drug Advised List (for drug combo)
			List todayDrudAdvicedLst=new ArrayList();
			todayDrudAdvicedLst=(List)mpEssentials.get(InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT);
			
			List allDrugLst=(List)mpEssentials.get(InpatientConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator prevLastVOItr=todayDrudAdvicedLst.iterator();
			while(prevLastVOItr.hasNext())
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)prevLastVOItr.next();
				Iterator allDrugLstItr=allDrugLst.iterator();
				while(allDrugLstItr.hasNext())
				{
					Entry obj=(Entry)allDrugLstItr.next();
					String[] drugIdandItemtypeId=obj.getValue().split("#");
					
						if(drugIdandItemtypeId[0].equals(vo.getItemId()))
						{
							vo.setItemTypeId(drugIdandItemtypeId[1]);
							vo.setIvFluidFlag(drugIdandItemtypeId[2]);
						}
					
				 }
			 }
			
			session.setAttribute(InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST, prevLast);
			//session.setAttribute(InpatientConfig.LAST_DRUG_ADVICED_LST, drudAdvicedLst);
			
			//identify which is sosIvfluid or which scheduledIvFluid
			List inProcessIvFuidList=(List)mpEssentials.get(InpatientConfig.SELECTED_IVFLUIDS_LIST);
			if(inProcessIvFuidList!=null)
			{
				for(int i=0;i<inProcessIvFuidList.size();i++)
				{
					DrugAdminDtlVO vo=(DrugAdminDtlVO)inProcessIvFuidList.get(i);
					if(vo.getDoseTime()==null)
					{
						vo.setSosFlag(InpatientConfig.IS_SOS_IVFLUID);
					}
					else
					{
						vo.setSosFlag(InpatientConfig.IS_NOT_SOS_IVFLUID);
					}
					if(vo.getRemarks()==null)
					{
						vo.setRemarks("");
					}
				}
			}
			
			//concatinating itemtype desc with drug name
			
			List todayDrugLst=(List)mpEssentials.get(InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT);
			
			for(int i=0;i<todayDrugLst.size();i++)
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)todayDrugLst.get(i);
				//String itemTypeDesc=vo.getItemTypeDesc();
				String itemName=vo.getItemName();
				//vo.setItemName(itemName)+ " ( "+itemTypeDesc+" )");
				vo.setItemName(itemName);
		
				
			}
			
			
			session.setAttribute(InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT, todayDrugLst);
			
			
			if(prevLast==null || prevLast.size()==0)
			{
				//objStatus.add(Status.DONE,"","No Treatment Advice For This Patient");
			}
			
			if(todayDrudAdvicedLst==null || todayDrudAdvicedLst.size()==0)
			{
				objStatus.add(Status.DONE, "", "No drug for execution");
			}
			
			
			
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	//Getting Drug Schedule
	public static void getPrevDrugShedule(DrugAdministrationFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List prevPatDrugTreatmentDtlVOList=new ArrayList();
		try{
			 int selectedDelRow=Integer.parseInt(_fb.getScheduleIndex());
			 prevPatDrugTreatmentDtlVOList=(List)session.getAttribute(InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST);
			 Iterator<PatDrugTreatmentDetailVO> itr = prevPatDrugTreatmentDtlVOList.iterator();
			 int j=0;
			 //below there variable use for getting schedule detail 
			 String drugId="";
			 String episodeVisitNo="";
			 String sereialNo="";
			 while(itr.hasNext())
			 {
				 PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO=itr.next();
				   if(j==selectedDelRow)
					 {		
					     drugId=_patDrugTreatmentDetailVO.getDrugId().split("#")[0];//because drugId concatenate with frequency
					     episodeVisitNo=_patDrugTreatmentDetailVO.getEpisodeVisitNo();
					     sereialNo=_patDrugTreatmentDetailVO.getSerialNo();
					 }
				  j++;
			 }
			 //getting drug schedule for particular RxContinue drug
			 List drugScheduleVOLst=(List)session.getAttribute(InpatientConfig.PREV_DRUG_SCHEDULE_OF_PATIENT);
			 List drugScheduleLst=new ArrayList();
			 if(drugScheduleVOLst!=null){
			 for(int i=0;i<drugScheduleVOLst.size();i++)
			 {
				 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(i);
				 if(drugSheduleDtlVO.getItemTypeId().equals(drugId) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getSerialNo().equals(sereialNo))
				 {
					drugScheduleLst.add(drugSheduleDtlVO);
				 }
			 }
			session.setAttribute(OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG, drugScheduleLst);
			objStatus.add(Status.TRANSINPROCESS);
		}}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	/*
	public static void addNewDrugExeRow(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		DrugAdminDtlVO[] drugAdminDtlVO=null;
		DrugAdminDtlVO[] drugAdministrationDtlVO=null;
		List drugLst=null;
		List drugRouteLst=null;
		try
		{
			setSysdate(_rq);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			
			// getting doseId,DoseName,isEmptyStomach value
			List prevDrugDtlLst=(List)session.getAttribute(InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST);
			String doseId="";
			String doseName="";
			String isEmptyStomach="";
			Iterator prevDrugDtlLstItr=prevDrugDtlLst.iterator();
			while(prevDrugDtlLstItr.hasNext())
			{
				PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevDrugDtlLstItr.next();
				if(vo.getDrugId().split("#")[0].equals(_fb.getSosSelDrugId().split("#")[0]))
				{
					doseId=vo.getDoseId();
					doseName=vo.getDoseName();
					isEmptyStomach=vo.getIsEmptyStomach();
				}
			}
			//Getting Drug Name and Drug Id
			drugLst=(List)session.getAttribute(InpatientConfig.LAST_DRUG_ADVICED_LST);
			String drugName="";
			String drugId="";
			Iterator drugLstItr=drugLst.iterator();
			while(drugLstItr.hasNext())
			{
				Entry obj=(Entry)drugLstItr.next();
				if(obj.getValue().equals(_fb.getSelDrugID()))
				{
					drugName=obj.getLabel();
					drugId=obj.getValue();
				}
			}
			// Getting Route Name and Route Id
			drugRouteLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO);
			String routeName="";
			String routeId="";
			Iterator drugRouteLstItr=drugRouteLst.iterator();
			while(drugRouteLstItr.hasNext())
			{
				DrugRouteMstVO obj=(DrugRouteMstVO)drugRouteLstItr.next();
				if(obj.getDrugRouteId().equals(_fb.getSelDrugRouteId()))
				{
					routeName=obj.getDrugRouteName();
					routeId=obj.getDrugRouteId();
				}
			}
			String ivfluidFlag=_fb.getSelDrugID().split("#")[2];
			List ivfluidLst=null;
			ivfluidLst=(List)session.getAttribute(InpatientConfig.SELECTED_IVFLUIDS_LIST);
			if(ivfluidLst==null)
			{
				ivfluidLst=new ArrayList();
			}
			
			PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}
			
			if(!(ivfluidFlag.equals("0"))&& _fb.getSelEndExecutionTimeHrs().equals("") && _fb.getSelEndExecutionTimeMin().equals(""))
			{
				DrugAdminDtlVO vo=new DrugAdminDtlVO();
				vo.setItemId(drugId);
				vo.setItemName(drugName);
				vo.setDrugRouteId(routeId);
				vo.setDrugRouteName(routeName);
				vo.setDoseId(doseId);
				vo.setDoseName(doseName); 
				vo.setIsEmptyStomach(isEmptyStomach);               
				vo.setAdministrationTime(_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
				vo.setAdministrationEndTime(null);
				vo.setBatchNo(_fb.getSelBatchNo());
				vo.setExpiryDate(_fb.getSelExpriryDate());
				vo.setRemarks(_fb.getSelExecutionRemark());
				vo.setDrugSourceFlag(_fb.getDrugSourceFromPat());
				vo.setSerialNo("");
				
				vo.setEpisodeCode(voDP.getEpisodeCode());
				vo.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				vo.setPatCrNo(voDP.getPatCrNo());
				vo.setPatAdmNo(voDP.getPatAdmNo());
							
				ivfluidLst.add(vo);
				session.setAttribute(InpatientConfig.SELECTED_IVFLUIDS_LIST, ivfluidLst);
			}
			else
			{
				drugAdministrationDtlVO=(DrugAdminDtlVO[])session.getAttribute(InpatientConfig.PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY);
				if(drugAdministrationDtlVO==null)
				{
					drugAdminDtlVO=new DrugAdminDtlVO[1];
					
					drugAdminDtlVO[0]=new DrugAdminDtlVO();
					drugAdminDtlVO[0].setItemId(drugId);
					drugAdminDtlVO[0].setItemName(drugName);
					drugAdminDtlVO[0].setDrugRouteId(routeId);
					drugAdminDtlVO[0].setDrugRouteName(routeName);
					drugAdminDtlVO[0].setDoseId(doseId);
					drugAdminDtlVO[0].setDoseName(doseName); 
					drugAdminDtlVO[0].setIsEmptyStomach(isEmptyStomach);               
					drugAdminDtlVO[0].setAdministrationTime(_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
					if(_fb.getSelEndExecutionTimeHrs().equals("") && _fb.getSelEndExecutionTimeMin().equals(""))
					{
						drugAdminDtlVO[0].setAdministrationEndTime(_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
					}
					else
					{
						drugAdminDtlVO[0].setAdministrationEndTime(_fb.getSelEndExecutionTimeHrs()+":"+_fb.getSelEndExecutionTimeMin());
					}
					drugAdminDtlVO[0].setBatchNo(_fb.getSelBatchNo());
					drugAdminDtlVO[0].setExpiryDate(_fb.getSelExpriryDate());
					drugAdminDtlVO[0].setRemarks(_fb.getSelExecutionRemark());
					drugAdminDtlVO[0].setDrugSourceFlag(_fb.getDrugSourceFromPat());
					WebUTIL.setAttributeInSession(_rq,InpatientConfig.PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY, drugAdminDtlVO);
				}
				else
				{
					drugAdminDtlVO=new DrugAdminDtlVO[drugAdministrationDtlVO.length+1];
					int i=0;
					for(;i<drugAdministrationDtlVO.length;i++)
					{
						drugAdminDtlVO[i]=drugAdministrationDtlVO[i];
					}
					
					drugAdminDtlVO[i]=new DrugAdminDtlVO();
					
					drugAdminDtlVO[i].setItemId(drugId);
					drugAdminDtlVO[i].setItemName(drugName);
					drugAdminDtlVO[i].setDrugRouteId(routeId);
					drugAdminDtlVO[i].setDrugRouteName(routeName);
					drugAdminDtlVO[i].setDoseId(doseId);
					drugAdminDtlVO[i].setDoseName(doseName); 
					drugAdminDtlVO[i].setIsEmptyStomach(isEmptyStomach);  
					drugAdminDtlVO[i].setAdministrationTime(_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
					if(_fb.getSelEndExecutionTimeHrs().equals("") || _fb.getSelEndExecutionTimeHrs().equals(null))
					{
						drugAdminDtlVO[i].setAdministrationEndTime(_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
					}
					else
					{
						drugAdminDtlVO[i].setAdministrationEndTime(_fb.getSelEndExecutionTimeHrs()+":"+_fb.getSelEndExecutionTimeMin());
					}
					drugAdminDtlVO[i].setBatchNo(_fb.getSelBatchNo());
					drugAdminDtlVO[i].setExpiryDate(_fb.getSelExpriryDate());
					drugAdminDtlVO[i].setRemarks(_fb.getSelExecutionRemark());
					drugAdminDtlVO[i].setDrugSourceFlag(_fb.getDrugSourceFromPat());
					WebUTIL.setAttributeInSession(_rq, InpatientConfig.PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY, drugAdminDtlVO);
				}
			}
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	} 
	*/
	
	
	public static void addNewActivityRow(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		
		try
		{
			setSysdate(_rq);
			//Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			//String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			
			List extAdminDtlVOList=null;
			extAdminDtlVOList=(List)session.getAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST);
			
			if(extAdminDtlVOList==null)
			{
				extAdminDtlVOList=new ArrayList();
			}
			
			ExtAdminDtlVO vo=new ExtAdminDtlVO();
						
			vo.setExtTreatmentId(_fb.getActivityExtId());
			vo.setAdministrationTime(_fb.getActivitySelStartExecTimeHrs()+":"+_fb.getActivitySelStartExecTimeMin());
			vo.setRemarks(_fb.getActivitySelRemarks());
				
			//get and set ext treatment  name
			List activityList=(List)session.getAttribute(InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT);
			for(int i=0;i<activityList.size();i++)
			{
				Entry obj=(Entry)activityList.get(i);
				if(obj.getValue().equals(_fb.getActivityExtId()))
				{
					vo.setExtTreatmentName(obj.getLabel());
				}
			}
						
			 vo.setPatCrNo(_fb.getPatCrNo());
			 vo.setPatAdmNo(_fb.getAdmissionNo());
			 vo.setEpisodeCode(_fb.getEpisodeCode());
			 vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			 vo.setAdminFlag(OpdConfig.NEW_RECORD);
			
			
			 extAdminDtlVOList.add(vo);
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST, extAdminDtlVOList);
			//session.setAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST, drugAdminDtlVOList);
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	
	public static void addNewDrugExeRow(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		
		try
		{
			setSysdate(_rq);
			//Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			//String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			
			List drugAdminDtlVOList=null;
			drugAdminDtlVOList=(List)session.getAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST);
			
			if(drugAdminDtlVOList==null)
			{
				drugAdminDtlVOList=new ArrayList();
			}
			
			DrugAdminDtlVO vo=new DrugAdminDtlVO();
			
			vo.setItemId(_fb.getSosSelDrugId().split("#")[0]);
			vo.setActualDoseId(_fb.getSosSelDoseId());
			vo.setActualDrugRouteId(_fb.getSosSelDrugRouteId());
			vo.setAdministrationTime(_fb.getSosSelStartExecutionTimeHrs()+":"+_fb.getSosSelStartExecutionTimeMin());
			vo.setDrugBrandId(_fb.getSelDrugBrandArraySOS()[0]);
			vo.setDrugBrandName(_fb.getSelDrugBrandNameSOS());
			if(_fb.getSosSelEndExecutionTimeHrs()==null)
			{
				_fb.setSosSelEndExecutionTimeHrs("");
			}
			if(_fb.getSosSelEndExecutionTimeMin()==null)
			{
				_fb.setSosSelEndExecutionTimeMin("");
			}
			if(_fb.getSosSelEndExecutionTimeHrs()=="" && _fb.getSosSelEndExecutionTimeMin()=="")
			{
				vo.setAdministrationEndTime("");
			}
			else
			{
				vo.setAdministrationEndTime(_fb.getSosSelEndExecutionTimeHrs()+":"+_fb.getSosSelEndExecutionTimeMin());
			}
			if(_fb.getSosSelVolume()==null)
			{
				vo.setVolume("");
			}
			else
			{
				vo.setVolume(_fb.getSosSelVolume());
			}
			vo.setBatchNo(_fb.getSosSelBatchNo());
			vo.setExpiryDate(_fb.getSosSelExpriryDate());
			vo.setRemarks(_fb.getSosSelRemarks());
				
			//get and set item name
			List sosDrugList=(List)session.getAttribute(InpatientConfig.SOS_DRUG_LIST_FOR_PATIENT);
			for(int i=0;i<sosDrugList.size();i++)
			{
				Entry obj=(Entry)sosDrugList.get(i);
				if(obj.getValue().split("#")[0].equals(_fb.getSosSelDrugId().split("#")[0]))
				{
					vo.setItemName(obj.getLabel());
				}
			}
			
			//get and set dose name
			List allDrugDoseList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			for(int i=0;i<allDrugDoseList.size();i++)
			{
				DrugDoseVO drugDoseVo=(DrugDoseVO)allDrugDoseList.get(i);
				if(_fb.getSosSelDoseId().equals(drugDoseVo.getDoseId()))
				{
					vo.setActualDoseName(drugDoseVo.getDoseName());
				}
			}
			
			//get and set route name
			List drugRouteVOList=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO);
			for(int i=0;i<drugRouteVOList.size();i++)
			{
				DrugRouteMstVO drugRouteVO=(DrugRouteMstVO)drugRouteVOList.get(i);
				if(_fb.getSosSelDrugRouteId().equals(drugRouteVO.getDrugRouteId()))
				{
					vo.setDrugRouteName(drugRouteVO.getDrugRouteName());
				}
			}
			
			// get and set other information
			
			List lastDrugAdviceList=(List)session.getAttribute(InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST);
			for(int i=0;i<lastDrugAdviceList.size();i++)
			{
				PatDrugTreatmentDetailVO patTreatVO=(PatDrugTreatmentDetailVO)lastDrugAdviceList.get(i);
				if(_fb.getSosSelDrugId().split("#")[0].equals(patTreatVO.getDrugId()))
				{
					vo.setAdviceDate(patTreatVO.getEntryDate());
					vo.setIsEmptyStomach(patTreatVO.getIsEmptyStomach());
					vo.setDrugRouteId(patTreatVO.getDrugRouteId());
					vo.setDoseId(patTreatVO.getDoseId());
					vo.setDoseName(patTreatVO.getDoseName());
				}
			}
			
			 vo.setPatCrNo(_fb.getPatCrNo());
			 vo.setPatAdmNo(_fb.getAdmissionNo());
			 vo.setEpisodeCode(_fb.getEpisodeCode());
			 vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			 vo.setIvFluidFlag(_fb.getSosSelDrugId().split("#")[2]); 
			
			drugAdminDtlVOList.add(vo);
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST, drugAdminDtlVOList);
			//session.setAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST, drugAdminDtlVOList);
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void deleteDrugExeRow(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		List sosDrugAdminList=null;
		try
		{
			setSysdate(_rq);
			sosDrugAdminList =(List)session.getAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST);
			
			int rowIndex = Integer.parseInt(_fb.getIndex());
			int j=0;
			Iterator itr=sosDrugAdminList.iterator();
			
			while(itr.hasNext())
			{
				//DrugAdminDtlVO vo=(DrugAdminDtlVO)
				itr.next();
				
				if(rowIndex==j)
				{
					itr.remove();
				}
				
				j++;
			}
			session.setAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST, sosDrugAdminList);
			
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	} 
	
	public static void deleteActivityRow(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		List activityExtAdminList=null;
		try
		{
			setSysdate(_rq);
			activityExtAdminList =(List)session.getAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST);
			
			int rowIndex = Integer.parseInt(_fb.getIndex());
			int j=0;
			Iterator itr=activityExtAdminList.iterator();
			
			while(itr.hasNext())
			{
				//ExtAdminDtlVO vo=(ExtAdminDtlVO)
				itr.next();
				
				if(rowIndex==j)
				{
					itr.remove();
				}
				
				j++;
			}
			session.setAttribute(InpatientConfig.SELECTED_ACTIVITY_DETAIL_LIST, activityExtAdminList);
			
			
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	} 
	
	public static void deleteIvfluidRow(DrugAdministrationFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(_rq);
		List ivfluidLst=null;
		try
		{
			setSysdate(_rq);
			ivfluidLst=(List)session.getAttribute(InpatientConfig.SELECTED_IVFLUIDS_LIST);
			int row = Integer.parseInt(_fb.getIndex());
			int j=0;
			Iterator itr=ivfluidLst.iterator();
			while(itr.hasNext())
			{	
				//DrugAdminDtlVO vo=(DrugAdminDtlVO)
				itr.next();
				if(j==row)
				{
					itr.remove();
				}
				j++;
			}
			WebUTIL.setAttributeInSession(_rq,InpatientConfig.SELECTED_IVFLUIDS_LIST, ivfluidLst);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	} 
	
	public static void getDrugRoute(DrugAdministrationFB fb, HttpServletRequest request,HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session=request.getSession();
		String result="";
		String itemTypeId=request.getParameter("itemTypeId");
		String itemId=request.getParameter("drugItemId");
		try
		{
			//this is for getting particular drugRoute list on the basis of itemTypeId
			List allDrugRouteVOLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO);
			List drugRoutetLst=new ArrayList();		
			Iterator itr=allDrugRouteVOLst.iterator();
			while(itr.hasNext())
			{
				DrugRouteMstVO vo=(DrugRouteMstVO)itr.next();
				//if(vo.getItemTypeId().equals(itemTypeId))
				if(vo.getDrugItemId().equals(itemId))
				{
					Entry ent=new Entry();
					ent.setValue(vo.getDrugRouteId());
					ent.setLabel(vo.getDrugRouteName());
					drugRoutetLst.add(ent);
				}
			}
			//this is for writing the response 
			Iterator drugRoutetLstItr = drugRoutetLst.iterator();
			while(drugRoutetLstItr.hasNext())
			{
				Entry obj=(Entry)drugRoutetLstItr.next();
				result=result + obj.getValue() + "$" + obj.getLabel() + "$";	
			}
		   if(!result.equals(""))	result = result.substring(0,result.length()-1);
			try
			{
				PrintWriter writer = _resp.getWriter();
				writer.write(result);
				writer.flush();
				writer.close();
			}								
			catch (IOException e)				
			{								
				e.printStackTrace();					
			}
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
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
	
	/*
	// saving drug Execution Dtl
	public static void saveDrugAdmin(DrugAdministrationFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List drugRouteLst=new ArrayList();
		List drugLst=new ArrayList();
		DrugAdminDtlVO[] drugAdminDtlVO=null;
		DrugAdminDtlVO[] drugAdministrationDtlVO=null;
		int count;
		try
		{	
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			_fb.setAdministrationDate(sysadteString);
			// for getting episodeCode ,visit number,admission number
			PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}
			
			// getting doseId,DoseName,isEmptyStomach value
			List prevDrugDtlLst=(List)session.getAttribute(InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST);
			String doseId="";
			String doseName="";
			String isEmptyStomach="";
			Iterator prevDrugDtlLstItr=prevDrugDtlLst.iterator();
			while(prevDrugDtlLstItr.hasNext())
			{
				PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevDrugDtlLstItr.next();
				if(vo.getDrugId().split("#")[0].equals(_fb.getSelDrugID().split("#")[0]))
				{
					doseId=vo.getDoseId();
					doseName=vo.getDoseName();
					isEmptyStomach=vo.getIsEmptyStomach();
				}
			}
						
			//Getting Drug Name and Drug Id
			drugLst=(List)session.getAttribute(InpatientConfig.LAST_DRUG_ADVICED_LST);
			String drugName="";
			String drugId="";
			Iterator drugLstItr=drugLst.iterator();
			while(drugLstItr.hasNext())
			{
				Entry obj=(Entry)drugLstItr.next();
				if(obj.getValue().equals(_fb.getSelDrugID()))
				{
					drugName=obj.getLabel();
					drugId=obj.getValue();
				}
			}
			
			// Getting Route Name and Route Id
			drugRouteLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO);
			String routeName="";
			String routeId="";
			Iterator drugRouteLstItr=drugRouteLst.iterator();
			while(drugRouteLstItr.hasNext())
			{
				DrugRouteMstVO obj=(DrugRouteMstVO)drugRouteLstItr.next();
				if(obj.getDrugRouteId().equals(_fb.getSelDrugRouteId()))
				{
					routeName=obj.getDrugRouteName();
					routeId=obj.getDrugRouteId();
				}
			}
			
			drugAdministrationDtlVO=(DrugAdminDtlVO[])session.getAttribute(InpatientConfig.PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_ARRAY);
			
			if(!(_fb.getSelDrugID().equals("-1")))
			{
				if(drugAdministrationDtlVO==null)
					count=1;
				else
					count=drugAdministrationDtlVO.length+1;
				
				drugAdminDtlVO=new DrugAdminDtlVO[count];
				
				drugAdminDtlVO[0]=new DrugAdminDtlVO();
							
				drugAdminDtlVO[0].setEpisodeCode(voDP.getEpisodeCode());
				drugAdminDtlVO[0].setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				drugAdminDtlVO[0].setPatCrNo(voDP.getPatCrNo());
				drugAdminDtlVO[0].setPatAdmNo(voDP.getPatAdmNo());
				
				drugAdminDtlVO[0].setItemId(drugId);
				drugAdminDtlVO[0].setItemName(drugName);
				drugAdminDtlVO[0].setDrugRouteId(routeId);
				drugAdminDtlVO[0].setDrugRouteName(routeName);
				drugAdminDtlVO[0].setDoseId(doseId);
				drugAdminDtlVO[0].setDoseName(doseName); 
				drugAdminDtlVO[0].setIsEmptyStomach(isEmptyStomach);  
				drugAdminDtlVO[0].setAdministrationTime(sysadteString+" "+_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
				if(!(_fb.getSelDrugID().split("#")[2].equals("0")) && _fb.getSelEndExecutionTimeHrs().equals(""))
				{
					drugAdminDtlVO[0].setAdministrationEndTime("");
				}
				if(!(_fb.getSelDrugID().split("#")[2].equals("0")) && !(_fb.getSelEndExecutionTimeHrs().equals("")))
				{
					drugAdminDtlVO[0].setAdministrationEndTime(sysadteString+" "+_fb.getSelEndExecutionTimeHrs()+":"+_fb.getSelEndExecutionTimeMin());
				}
				if(_fb.getSelDrugID().split("#")[2].equals("0"))
				{
					drugAdminDtlVO[0].setAdministrationEndTime(sysadteString+" "+_fb.getSelStartExecutionTimeHrs()+":"+_fb.getSelStartExecutionTimeMin());
				}
				drugAdminDtlVO[0].setBatchNo(_fb.getSelBatchNo());
				drugAdminDtlVO[0].setExpiryDate(_fb.getSelExpriryDate());
				drugAdminDtlVO[0].setRemarks(_fb.getSelExecutionRemark());
				drugAdminDtlVO[0].setDrugSourceFlag(_fb.getDrugSourceFromPat());
				
				if(drugAdministrationDtlVO!=null)
				{
					int j=1;
					for(int i=0;i<drugAdministrationDtlVO.length;i++)
					{
						drugAdminDtlVO[j]=new DrugAdminDtlVO();
						
						drugAdminDtlVO[j]=new DrugAdminDtlVO();
						
						drugAdminDtlVO[j].setEpisodeCode(voDP.getEpisodeCode());
						drugAdminDtlVO[j].setEpisodeVisitNo(voDP.getEpisodeVisitNo());
						drugAdminDtlVO[j].setPatCrNo(voDP.getPatCrNo());
						drugAdminDtlVO[j].setPatAdmNo(voDP.getPatAdmNo());
						
						drugAdminDtlVO[j].setItemId(drugAdministrationDtlVO[i].getItemId());
						drugAdminDtlVO[j].setItemName(drugAdministrationDtlVO[i].getItemName());
						drugAdminDtlVO[j].setDrugRouteId(drugAdministrationDtlVO[i].getDrugRouteId());
						drugAdminDtlVO[j].setDrugRouteName(drugAdministrationDtlVO[i].getDrugRouteName());
						drugAdminDtlVO[j].setDoseId(drugAdministrationDtlVO[i].getDoseId());
						drugAdminDtlVO[j].setDoseName(drugAdministrationDtlVO[i].getDoseName()); 
						drugAdminDtlVO[j].setIsEmptyStomach(drugAdministrationDtlVO[i].getIsEmptyStomach());               
						drugAdminDtlVO[j].setAdministrationTime(sysadteString+" "+drugAdministrationDtlVO[i].getAdministrationTime());
						drugAdminDtlVO[j].setAdministrationEndTime(sysadteString+" "+drugAdministrationDtlVO[i].getAdministrationEndTime());
						drugAdminDtlVO[j].setBatchNo(drugAdministrationDtlVO[i].getBatchNo());
						drugAdminDtlVO[j].setExpiryDate(drugAdministrationDtlVO[i].getExpiryDate());
						drugAdminDtlVO[j].setRemarks(drugAdministrationDtlVO[i].getRemarks());
						drugAdminDtlVO[j].setDrugSourceFlag(drugAdministrationDtlVO[i].getDrugSourceFlag());
						j++;
					}
				}
			}
			else
			{
				if(drugAdministrationDtlVO!=null)
				{
					drugAdminDtlVO=new DrugAdminDtlVO[drugAdministrationDtlVO.length];
					int j=0;
					for(int i=0;i<drugAdministrationDtlVO.length;i++)
					{
						
						drugAdminDtlVO[j]=new DrugAdminDtlVO();
						
						drugAdminDtlVO[j].setEpisodeCode(voDP.getEpisodeCode());
						drugAdminDtlVO[j].setEpisodeVisitNo(voDP.getEpisodeVisitNo());
						drugAdminDtlVO[j].setPatCrNo(voDP.getPatCrNo());
						drugAdminDtlVO[j].setPatAdmNo(voDP.getPatAdmNo());
						
						drugAdminDtlVO[j].setItemId(drugAdministrationDtlVO[i].getItemId());
						drugAdminDtlVO[j].setItemName(drugAdministrationDtlVO[i].getItemName());
						drugAdminDtlVO[j].setDrugRouteId(drugAdministrationDtlVO[i].getDrugRouteId());
						drugAdminDtlVO[j].setDrugRouteName(drugAdministrationDtlVO[i].getDrugRouteName());
						drugAdminDtlVO[j].setDoseId(drugAdministrationDtlVO[i].getDoseId());
						drugAdminDtlVO[j].setDoseName(drugAdministrationDtlVO[i].getDoseName()); 
						drugAdminDtlVO[j].setIsEmptyStomach(drugAdministrationDtlVO[i].getIsEmptyStomach());               
						drugAdminDtlVO[j].setAdministrationTime(sysadteString+" "+drugAdministrationDtlVO[i].getAdministrationTime());
						drugAdminDtlVO[j].setAdministrationEndTime(sysadteString+" "+drugAdministrationDtlVO[i].getAdministrationEndTime());
						drugAdminDtlVO[j].setBatchNo(drugAdministrationDtlVO[i].getBatchNo());
						drugAdminDtlVO[j].setExpiryDate(drugAdministrationDtlVO[i].getExpiryDate());
						drugAdminDtlVO[j].setRemarks(drugAdministrationDtlVO[i].getRemarks());
						drugAdminDtlVO[j].setDrugSourceFlag(drugAdministrationDtlVO[i].getDrugSourceFlag());
						j++;
					}
				}
			}
					
			List ivfluidLst=null;
			List ivfluidLstWithTime=new ArrayList();
			ivfluidLst=(List)session.getAttribute(InpatientConfig.SELECTED_IVFLUIDS_LIST);
			for(int i=0;i<ivfluidLst.size();i++)
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)ivfluidLst.get(i);
				String administrationTime=vo.getAdministrationTime();
				vo.setAdministrationTime(sysadteString+" "+administrationTime);
				vo.setRemarks(_fb.getSelExecutionRemarkArray()[i]);
				if(!(_fb.getSelEndExecutionTimeHrsArray()[i].equals("")))
				{
					vo.setAdministrationEndTime(sysadteString+" "+_fb.getSelEndExecutionTimeHrsArray()[i]+":"+_fb.getSelEndExecutionTimeMinArray()[i] );
				}
				ivfluidLstWithTime.add(vo);
			}
						
			DrugAdministrationDATA.saveDrugTreatment(drugAdminDtlVO,ivfluidLstWithTime,getUserVO(_request));
			objStatus.add(Status.DONE,"","Drug Given Successfully");
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
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	*/
	
	// saving drug Execution Dtl
	public static void saveDrugAdmin(DrugAdministrationFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List drugAdminVOList=new ArrayList();
		List ivFluidDrugAdminVOList=new ArrayList();
		List patIntakeOutDtlVOList=new ArrayList();
		UserVO _userVo =new UserVO();
		try
		{	
			if(_request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_USER_VO)!=null)
			{
				
			 _userVo = (UserVO) _request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_USER_VO);
			 
			}
			else
			{
				_userVo = getUserVO(_request);
			}
			
			
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		//	String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy"); changed for giving drug administration to older dates *************
			
			//String sysadteStringForINtake=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			
			//String sysadteString=_fb.getScheduleDate();
			
			//_fb.setAdministrationDate(sysadteString);
			//List allDrugDoseLst=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			//List allDrugList=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUGS);
			
			/***********************saving scheduled drug*******************************************/
			//saving sheduled drug
			String[] selIndexArray= _fb.getSelIndexArray();
			if(selIndexArray!=null)
			{
				for(int i=0;i<selIndexArray.length;i++)
				{
					DrugAdminDtlVO vo=new DrugAdminDtlVO();
					
					Integer ind=Integer.parseInt(selIndexArray[i]);
					//vo.setActualDoseId(_fb.getSelDoseIdArray()[ind]);
					vo.setActualDoseName(_fb.getSelDoseNameArray()[ind]);
					vo.setActualDrugRouteId(_fb.getSelDrugRouteIdArray()[i]);  
				
					
					vo.setAdministrationTime(_fb.getSelScheduleDateArray()[i]+" "+_fb.getSelStartExecutionTimeHrs()[i]+":"+_fb.getSelStartExecutionTimeMin()[i]);
					vo.setAdministrationDate(_fb.getSelScheduleDateArray()[i]);
					if(_fb.getDrugStoreSourceArray()[i].equals("1"))
					{
						vo.setBatchNo(_fb.getSelPatientBatchNoArray()[i].split("#")[0]);
						vo.setDrugBrandId(_fb.getSelDrugBrandArray()[i].split("#")[0]);
					    
					}
					else
					{
						vo.setBatchNo(_fb.getSelBatchNoArray()[i].split("#")[0]);
						vo.setDrugBrandId(_fb.getSelDrugBrandArrayFromStore()[i].split("#")[0]);
					
		 
					}
					vo.setExpiryDate(_fb.getSelExpriryDateArray()[ind]);
					vo.setDrugBrandName(_fb.getSelDrugBrandName());
					//String ivfluidFlag=_fb.getSelIvFluidFlagArray()[ind];
				
					vo.setActualItemBrandID(_fb.getSelItemBrandIdArray()[ind]);
					vo.setActualItemName(_fb.getSelDrugBrandName());
					if(!_fb.getSelIvFluidFlagArray()[ind].equals(InpatientConfig.IS_IVFLUID_NO) && _fb.getSelEndExecutionTimeHrs()[i]=="" && _fb.getSelEndExecutionTimeMin()[i]=="")
					{
						vo.setAdminFlag(OpdConfig.SCHEDULE);
					}
					else
					{
						vo.setAdminFlag(OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
						
						/*
						// this is for saving intake detail
						
						PatIntakeOutDtlVO patIntakeOutDtlVO=new PatIntakeOutDtlVO();
						
						patIntakeOutDtlVO.setPatCrNo(_fb.getPatCrNo());
						patIntakeOutDtlVO.setEpisodeCode(_fb.getEpisodeCode());
						patIntakeOutDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
						patIntakeOutDtlVO.setPatAdmNo(_fb.getAdmissionNo());
						//patIntakeOutDtlVO.setInTakeOutParaId(_fb.getSelItemIdArray()[i]);
						patIntakeOutDtlVO.setInTakeOutParaName(InpatientConfig.INTAKE_OUT_PARANAME_FOR_BLOOD_TRANSFUSION);
						patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
						patIntakeOutDtlVO.setRouteId(_fb.getSelDrugRouteIdArray()[i]);
						patIntakeOutDtlVO.setInTakeOutTime(sysadteStringForINtake+" "+_fb.getSelStartExecutionTimeHrs()[i]+":"+_fb.getSelStartExecutionTimeMin()[i]);
						patIntakeOutDtlVO.setRemarks(_fb.getSelRemarksArray()[i]);
						patIntakeOutDtlVO.setVolume(_fb.getSelVolumeArray()[i]);
						patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_DRUG);
						
						for(int k=0;k<allDrugList.size();k++)
						{
							Entry obj=(Entry)allDrugList.get(k);
							String drugId=obj.getValue().split("#")[0];
							if(drugId.equals(_fb.getSelItemIdArray()[i]))
							{
								patIntakeOutDtlVO.setInTakeOutParaName(obj.getLabel());
							}
						}
											
						patIntakeOutDtlVOList.add(patIntakeOutDtlVO);
						*/
					}
					
					if(_fb.getSelEndExecutionTimeHrs()[i]=="" && _fb.getSelEndExecutionTimeMin()[i]=="")
					{
						vo.setAdministrationEndTime("");
						
					}
					else
					{
						vo.setAdministrationEndTime(_fb.getSelScheduleDateArray()[i]+" "+_fb.getSelEndExecutionTimeHrs()[i]+":"+_fb.getSelEndExecutionTimeMin()[i]);
					}
					
					
					vo.setAdviceDate(_fb.getSelAdviceDateArray()[ind]);
					vo.setSerialNo(_fb.getSelSerealNoArray()[ind]);
					vo.setPatCrNo(_fb.getPatCrNo());
					vo.setRemarks(_fb.getSelRemarksArray()[i]);
					vo.setVolume(_fb.getSelVolumeArray()[i]);
					vo.setEpisodeCode(_fb.getEpisodeCode());
					
					drugAdminVOList.add(vo);
					
					
					
					
					
				}
			}
			
			/********************saving in process drug**************************************************/
			// saving Inprocess IvFluid List
			
			String[] ivFluidSelIndexArray=_fb.getIvFluidSelIndexArray();
			
			if(ivFluidSelIndexArray!=null)
			{
				for(int i=0;i<ivFluidSelIndexArray.length;i++)
				{
					DrugAdminDtlVO vo=new DrugAdminDtlVO();
					
					Integer ind=Integer.parseInt(ivFluidSelIndexArray[i]);
					
					vo.setPatCrNo(_fb.getPatCrNo());
					vo.setSerialNo(_fb.getIvFluidSelSerealNoArray()[ind]);
					vo.setAdviceDate(_fb.getIvFluidSelAdviceDateArray()[ind]);
					vo.setAdministrationEndTime(_fb.getSelScheduleDateArray()[i]+" "+_fb.getIvFluidSelEndExecutionTimeHrs()[i]+":"+_fb.getIvFluidSelEndExecutionTimeMin()[i]);
					vo.setRemarks(_fb.getIvFluidSelRemarksArray()[i]);
					vo.setVolume(_fb.getIvFluidSelVolumeArray()[i]);
					vo.setIsReaction(InpatientConfig.IS_REACTION_NO);
					vo.setAdministrationDate(_fb.getSelScheduleDateArray()[i]);
					if(_fb.getSosFlagArray()[ind].equals(InpatientConfig.IS_SOS_IVFLUID))
					{
						vo.setAdminFlag(OpdConfig.DRUG_GIVEN_WITHOUT_SCHEDULE);
					}
					else
					{
						vo.setAdminFlag(OpdConfig.DRUG_GIVEN_IN_PER_SCHEDULE);
					}
					
					/*
					//for saving intake detail
					
					PatIntakeOutDtlVO patIntakeOutDtlVO=new PatIntakeOutDtlVO();
					
					patIntakeOutDtlVO.setPatCrNo(_fb.getPatCrNo());
					patIntakeOutDtlVO.setEpisodeCode(_fb.getEpisodeCode());
					patIntakeOutDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					patIntakeOutDtlVO.setPatAdmNo(_fb.getAdmissionNo());
					//patIntakeOutDtlVO.setInTakeOutParaId(_fb.getIvFluidSelItemIdArray()[i]);
					patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
					patIntakeOutDtlVO.setRouteId(_fb.getIvFluidSelRouteIdArray()[i]);
					patIntakeOutDtlVO.setInTakeOutTime(sysadteStringForINtake+" "+_fb.getSelStartTimeForInTakeArray()[i]);
					patIntakeOutDtlVO.setRemarks(_fb.getIvFluidSelRemarksArray()[i]);
					patIntakeOutDtlVO.setVolume(_fb.getIvFluidSelVolumeArray()[i]);
					patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_DRUG);
					for(int k=0;k<allDrugList.size();k++)
					{
						Entry obj=(Entry)allDrugList.get(k);
						String drugId=obj.getValue().split("#")[0];
						if(drugId.equals(_fb.getIvFluidSelItemIdArray()[i]))
						{
							patIntakeOutDtlVO.setInTakeOutParaName(obj.getLabel());
						}
					}
					
					patIntakeOutDtlVOList.add(patIntakeOutDtlVO);
					*/
					ivFluidDrugAdminVOList.add(vo);
					
					
				}
			}
			
			/********************saving sos drug****************************************************/
			// saving first sosDrug row
			List sosDrugList=(List)session.getAttribute(InpatientConfig.SOS_PAT_DRUG_TREAT_ADMIN_DTL_DETAIL_LIST);
			if(sosDrugList==null)
			{
				sosDrugList=new ArrayList();
			}
			if(_fb.getSosSelDrugId()!=null && _fb.getSosSelDrugId()!="")
			{
				DrugAdminDtlVO vo=new DrugAdminDtlVO();
				
				vo.setItemId(_fb.getSosSelDrugId().split("#")[0]);
				//vo.setActualDoseId(_fb.getSosSelDoseId());
				vo.setActualDrugRouteId(_fb.getSosSelDrugRouteId());
				vo.setAdministrationTime(_fb.getSosSelStartExecutionTimeHrs()+":"+_fb.getSosSelStartExecutionTimeMin());
				if(_fb.getSosSelEndExecutionTimeHrs()==null)
				{
					_fb.setSosSelEndExecutionTimeHrs("");
				}
				if(_fb.getSosSelEndExecutionTimeMin()==null)
				{
					_fb.setSosSelEndExecutionTimeMin("");
				}
				if(_fb.getSosSelEndExecutionTimeHrs()=="" && _fb.getSosSelEndExecutionTimeMin()=="")
				{
					vo.setAdministrationEndTime("");
				}
				else
				{
					vo.setAdministrationEndTime(_fb.getSosSelEndExecutionTimeHrs()+":"+_fb.getSosSelEndExecutionTimeMin());
				}
				
				vo.setBatchNo(_fb.getSosSelBatchNo());
				vo.setExpiryDate(_fb.getSosSelExpriryDate());
				vo.setRemarks(_fb.getSosSelRemarks());
				vo.setVolume(_fb.getSosSelVolume());
					
				//get and set item name
				List sosDrugAdminList=(List)session.getAttribute(InpatientConfig.SOS_DRUG_LIST_FOR_PATIENT);
				for(int i=0;i<sosDrugAdminList.size();i++)
				{
					Entry obj=(Entry)sosDrugAdminList.get(i);
					if(obj.getValue().split("#")[0].equals(_fb.getSosSelDrugId().split("#")[0]))
					{
						vo.setItemName(obj.getLabel());
					}
				}
				
				//get and set dose name
				List allDrugDoseList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
				/*for(int i=0;i<allDrugDoseList.size();i++)
				{
					DrugDoseVO drugDoseVo=(DrugDoseVO)allDrugDoseList.get(i);
					if(_fb.getSosSelDoseId().equals(drugDoseVo.getDoseId()))
					{
						vo.setActualDoseName(drugDoseVo.getDoseName());
					}
				}*/
				vo.setActualDoseName(_fb.getSosSelDoseId());

				
				//get and set route name
				List drugRouteVOList=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO);
				for(int i=0;i<drugRouteVOList.size();i++)
				{
					DrugRouteMstVO drugRouteVO=(DrugRouteMstVO)drugRouteVOList.get(i);
					if(_fb.getSosSelDrugRouteId().equals(drugRouteVO.getDrugRouteId()))
					{
						vo.setDrugRouteName(drugRouteVO.getDrugRouteName());
					}
				}
				
				// get and set other information
				
				List lastDrugAdviceList=(List)session.getAttribute(InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST);
				for(int i=0;i<lastDrugAdviceList.size();i++)
				{
					PatDrugTreatmentDetailVO patTreatVO=(PatDrugTreatmentDetailVO)lastDrugAdviceList.get(i);
					if(_fb.getSosSelDrugId().split("#")[0].equals(patTreatVO.getDrugId()))
					{
						vo.setAdviceDate(patTreatVO.getEntryDate());
						vo.setIsEmptyStomach(patTreatVO.getIsEmptyStomach());
						vo.setDrugRouteId(patTreatVO.getDrugRouteId());
						vo.setDoseId(patTreatVO.getDoseId());
						vo.setDoseName(patTreatVO.getDoseName());
					}
				}
				
				 vo.setPatCrNo(_fb.getPatCrNo());
				 vo.setPatAdmNo(_fb.getAdmissionNo());
				 vo.setEpisodeCode(_fb.getEpisodeCode());
				 vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				 vo.setIvFluidFlag(_fb.getSosSelDrugId().split("#")[2]); 
				 
				 sosDrugList.add(vo);
				 
				 
				
			}
			
			// saving SOS drug
			
			if(sosDrugList!=null)
			{
				for(int i=0;i<sosDrugList.size();i++)
				{
					DrugAdminDtlVO vo=(DrugAdminDtlVO)sosDrugList.get(i);
					
					
					String adminTime=vo.getAdministrationTime();
					String adminEndTime=vo.getAdministrationEndTime();
					vo.setAdministrationTime(_fb.getSysDate()+" "+adminTime);
					if(adminEndTime!="")
					{
						vo.setAdministrationEndTime(_fb.getSelScheduleDateArray()[i]+" "+adminEndTime);
					}
					if(vo.getIsEmptyStomach().equals("No"))
					{
						vo.setIsEmptyStomach(OpdConfig.NOT_EMPTY_STOMACH);
					}
					else
					{
						vo.setIsEmptyStomach(OpdConfig.EMPTY_STOMACH);
					}
					
					if(!vo.getIvFluidFlag().equals(InpatientConfig.IS_IVFLUID_NO) && vo.getAdministrationEndTime()=="")
					{
						vo.setAdminFlag(OpdConfig.SCHEDULE);
					}
					else
					{
						vo.setAdminFlag(OpdConfig.DRUG_GIVEN_WITHOUT_SCHEDULE);
						
						/*
						//for saving intake detail
						
						PatIntakeOutDtlVO patIntakeOutDtlVO=new PatIntakeOutDtlVO();
						
						patIntakeOutDtlVO.setPatCrNo(_fb.getPatCrNo());
						patIntakeOutDtlVO.setEpisodeCode(_fb.getEpisodeCode());
						patIntakeOutDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
						patIntakeOutDtlVO.setPatAdmNo(_fb.getAdmissionNo());
						patIntakeOutDtlVO.setInTakeOutParaId(vo.getItemId());
						patIntakeOutDtlVO.setInTakeOutParaName(InpatientConfig.INTAKE_OUT_PARANAME_FOR_BLOOD_TRANSFUSION);
						patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
						//patIntakeOutDtlVO.setRouteId(vo.getDrugRouteId());
						patIntakeOutDtlVO.setInTakeOutTime(sysadteStringForINtake+" "+adminTime);
						patIntakeOutDtlVO.setRemarks(vo.getRemarks());
						patIntakeOutDtlVO.setVolume(vo.getVolume());
						patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_DRUG);
						for(int k=0;k<allDrugList.size();k++)
						{
							Entry obj=(Entry)allDrugList.get(k);
							String drugId=obj.getValue().split("#")[0];
							if(drugId.equals(vo.getItemId()))
							{
								patIntakeOutDtlVO.setInTakeOutParaName(obj.getLabel());
							}
						}
						
						patIntakeOutDtlVOList.add(patIntakeOutDtlVO);
						
						*/
					}
					
					
				}
			}
			
			
			
			//DrugAdministrationDATA.saveDrugTreatment(drugAdminVOList,ivFluidDrugAdminVOList,sosDrugList,patIntakeOutDtlVOList,getUserVO(_request)); //commented for saving login bound process with new logged in user 
			DrugAdministrationDATA.saveDrugTreatment(drugAdminVOList,ivFluidDrugAdminVOList,sosDrugList,patIntakeOutDtlVOList,_userVo);
			
			
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(_request, _fb.getDeskMenuId());
			objStatus.add(Status.DONE,"","Drug Given Successfully");
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
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	public static void getIntruction(DrugAdministrationFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		try{
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	//Getting Drug Schedule
	public static void getDrugSheduleShiftWise(DrugAdministrationFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		//List prevPatDrugTreatmentDtlVOList=new ArrayList();
		try{
			 		 			
			 List drugScheduleVOLst=(List)session.getAttribute(InpatientConfig.PREV_DRUG_SCHEDULE_OF_PATIENT);
			 List allDrugLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_LIST_ALL_DRUGS);
			 
			 List drugScheduleLst=new ArrayList();
			 for(int i=0;i<drugScheduleVOLst.size();i++)
			 {
				 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(i);
				 if(drugSheduleDtlVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))
				 {
					Iterator itr = allDrugLst.iterator();
					while(itr.hasNext())
					{
						Entry obj=(Entry)itr.next();
						String itemTypeId=drugSheduleDtlVO.getItemTypeId().split("#")[0];
						String drugId=obj.getValue().split("#")[0];
						if(itemTypeId.equals(drugId))
						{
							drugSheduleDtlVO.setDrugName(obj.getLabel());
							drugScheduleLst.add(drugSheduleDtlVO);
						}
					 }
				  }
			  }
			
			session.setAttribute(InpatientConfig.PREV_DRUG_SCHEDULE_LIST_WITH_PARTICULAR_DRUG_NAME, drugScheduleLst);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	//Getting Drug Schedule
	public static void getDrugInfo(DrugAdministrationFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List treatInfoLst=null;
		//List drugRouteLst=null;
		try{
			UserVO userVO = getUserVO(_request); 
			
			PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}
						
			if(_fb.getViewTreatAdminChoice().equals(InpatientConfig.DATEWISE_TREAT_INFO))
			 {
				 DrugAdminDtlVO drugAdminDtlVO=new DrugAdminDtlVO();
				 drugAdminDtlVO.setPatCrNo(_fb.getPatCrNo());
				 drugAdminDtlVO.setTreatFromDate(_fb.getTreatFromDate());
				 drugAdminDtlVO.setTreatToDate(_fb.getTreatToDate());
				 drugAdminDtlVO.setEpisodeCode(voDP.getEpisodeCode());
				 treatInfoLst=DrugAdministrationDATA.getDateWiseTreatInfo(drugAdminDtlVO,userVO);
				 session.setAttribute(InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO, treatInfoLst);
				 
				 if(treatInfoLst==null || treatInfoLst.size()==0)
				 {
					 objStatus.add(Status.TRANSINPROCESS, "", "No drug is given between given dates");
				 }
			 }
			 else
			 { 
				 DrugAdminDtlVO drugAdminDtlVO=new DrugAdminDtlVO();
				 drugAdminDtlVO.setPatCrNo(_fb.getPatCrNo());
				 drugAdminDtlVO.setItemId(_fb.getSelDrugID().split("#")[0]);
				 drugAdminDtlVO.setEpisodeCode(voDP.getEpisodeCode());
				 treatInfoLst=DrugAdministrationDATA.getDrugWiseTreatInfo(drugAdminDtlVO,userVO);
				 session.setAttribute(InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_DRUGWISE, treatInfoLst);
				 /*
				 if(treatInfoLst==null || treatInfoLst.size()==0)
				 {
					 objStatus.add(Status.TRANSINPROCESS, "", "This drug is not given");
				 }
				 */
			 }
						
			//objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
}
