package opd.transaction.controller.util;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.PatientTreatmentDetailDATA;
import opd.transaction.controller.fb.PatientTreatmentDetailFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugDoseIndicationMstVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugRouteMstVO;
import hisglobal.vo.DrugSaftyAlertMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.UserVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientTreatmentDetailUTIL extends ControllerUTIL
{
	 
	/**Setting Patient Treatment Detail Essentials
	 * @param _fb
	 * @param _rq
	 */
	
	
	// Modified on 06.04.2016 as per new CIMS Data Model Integration in Treatment Plan
	public static void setEssentials(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		
		Map mpEssentials = null;
		List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			// Getting Patient Demographic Detail 
			InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			
			// System Date
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			_fb.setSysDate(sysadteString);
			_fb.setOpdIpdFlag("");
						
			//This sysdate string for validation
			String sysDateStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			_fb.setSysDateForValidation(sysDateStr);
			
			String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			_fb.setDeskType(deskType);
			
			// Setting Desk Essentials
			// Episode, Visit, Admission No
			PatientDetailVO voDP = null;
			
			if(session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setAdmissionNo(voDP.getPatAdmNo());
				_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
				
					//if(voDP.getPatIsPregnant()!="" || voDP.getPatIsPregnant()==OpdConfig.PAT_PREGNANT_NO) // TO CHECK WITHOUT DATA
				//if(voDP.getPatGestationWeek()!=null)
				
				// added by manisha gangwar date: 12.4.2016 for Drug Alert on preg category code
				if(voDP.getPatIsPregnant()!="" && voDP.getPatIsPregnant()==OpdConfig.PAT_PREGNANT_YES)
					_fb.setIsPregnantFlag(OpdConfig.PAT_PREGNANT_YES);
				else
					_fb.setIsPregnantFlag(OpdConfig.PAT_PREGNANT_NO);
			}
			
			else if(session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
			{
				PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);				
				for (PatientDetailVO vo : al)
					if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
					{
						voDP = vo;
						break;					
					}
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setAdmissionNo(voDP.getPatAdmNo());
				_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			}

			
			
			mpEssentials=PatientTreatmentDetailDATA.getPatTreatmentDetailEssential(_fb.getPatCrNo(), _fb.getEpisodeCode(),voDP.getDepartmentUnitCode(),ptaientDetailVO.getPatGenderCode(),voDP, userVO);
			
			/**********************************set essential for drug detail***********************************/
			
			//for drug log
			List drugLogList=(List)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG);
			
			if((drugLogList!=null) && (drugLogList.size()!=0))
			{
				for(int i=0;i<drugLogList.size();i++)
				{
					PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)drugLogList.get(i);
					
					if(vo.getRxContinue().equals(OpdConfig.SIMPLE_RX))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="Rx "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("Rx ");
						}
						
					}
					
					if(vo.getRxContinue().equals(OpdConfig.REVOKE))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="Revoked "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("Revoked");
						}
						
					}
					
					if(vo.getRxContinue().equals(OpdConfig.NEW_RECORD))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="New "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("New");
						}
						
					}
					
					if(vo.getRxContinue().equals(OpdConfig.CHANGE_IN_PREV))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="Changed "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("Change");
						}
						
					}
				}
			}
			
			
			//this is for not repeating presciption date at the time of view prescription date only view in a single row
			List prevAllList=new ArrayList();
			prevAllList= (List)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG);
			
			List entryDateList=new ArrayList();
			
			for(int i=0;i<prevAllList.size();i++)
			{
				PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevAllList.get(i);
				
				boolean flag=false;
				
				for(int j=0;j<entryDateList.size();j++)
				{
					String entryDate=(String)entryDateList.get(j);
					
					if(entryDate.equals(vo.getEntryDate()))
					{
						flag=true;
					}
				}
				
				if(!flag)
				{
					entryDateList.add(vo.getEntryDate());
				}
			}
			
			List tempDrugLogList=new ArrayList();
			
			for(int i=0;i<entryDateList.size();i++)
			{
				String entryDate=(String)entryDateList.get(i);
				Boolean flag=false;
				
				for(int j=0;j<prevAllList.size();j++)
				{
					
					
					PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevAllList.get(j);
					if(entryDate.equals(vo.getEntryDate()))
					{
						if(!flag)
						{
							if(vo.getOpdIpdFlag().equals(OpdConfig.IS_NOT_DISCHARGE_ADVICE))
							{
								if((vo.getAdmissionNo()!=null) && (!vo.getAdmissionNo().equals("")))
								{
									String entyrDate=vo.getEntryDate()+" ( IPD )";
									vo.setEntryDate(entyrDate);
								}
								else
								{
									String entyrDate=vo.getEntryDate()+" ( OPD )";
									vo.setEntryDate(entyrDate);
								}
								
								
							}
							else
							{
								String entyrDate=vo.getEntryDate()+" ( Discharge )";
								vo.setEntryDate(entyrDate);
							}
							tempDrugLogList.add(vo);
							flag=true;
						}
						else
						{
							vo.setEntryDate("");
							tempDrugLogList.add(vo);
						}
						
					}
				}
				
			}
			
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG, tempDrugLogList);
			
			
			
			
			
			//_fb.setInstActivityType("1");
			// Setting Initial Drug Detail List
			lstDrugTreatmentDtl= new ArrayList<PatDrugTreatmentDetailVO>();
			String maxEntryDate=(String)mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO);
			if(maxEntryDate==null)
			{
				List defaultDrugProfileDrugLst=(List)mpEssentials.get(OpdConfig.DEFAULT_DRUG_PROFILE_DRUG_LIST);
				for(int i=0;i<defaultDrugProfileDrugLst.size();i++)
				{
					PatDrugTreatmentDetailVO vo=_fb.resetVO(new PatDrugTreatmentDetailVO());
					PatDrugTreatmentDetailVO profileVO=(PatDrugTreatmentDetailVO)defaultDrugProfileDrugLst.get(i);
					
					
					vo.setDrugId(profileVO.getDrugId());
					vo.setDrugName(profileVO.getDrugName());
					vo.setDoseId(profileVO.getDoseId());
					vo.setDoseName(profileVO.getDoseName());
					vo.setFrequencyId(profileVO.getFrequencyId());
					vo.setDays(profileVO.getDays());
					vo.setDrugBrandId(profileVO.getDrugBrandId());
					//vo.setStartDate(_fb.getSelStartDay()[i]);
					if(profileVO.getRemarks()!=null)
					{
						vo.setRemarks(profileVO.getRemarks());
					}
					else
					{
						vo.setRemarks("");
					}
					
					//vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
					vo.setIsEmptyStomach(profileVO.getIsEmptyStomach());
					//vo.setDrugRouteId(_fb.getSelDrugRouteId()[i]);
					//vo.setDrugRouteName(_fb.getSelDrugRouteName()[i]);
					//vo.setQuantity(_fb.getQuantity()[i]);
					vo.setCutOffBefore(profileVO.getCutOffBefore());
					vo.setCutOffAfter(profileVO.getCutOffAfter());
					vo.setSelStock("-1");
					lstDrugTreatmentDtl.add(vo);
				}
				
			}
						
			lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			
						
			//getting last visit(drug detail)
			List<PatDrugTreatmentDetailVO> prevAll = (List<PatDrugTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST);
			
			
			if(maxEntryDate==null)
			{
				_fb.setPrescriptionDate("");	
			}
			else
			{
				_fb.setPrescriptionDate(maxEntryDate);	
			}
			
			
			List<PatDrugTreatmentDetailVO> prevLast = new ArrayList<PatDrugTreatmentDetailVO>();
			for(PatDrugTreatmentDetailVO vo : prevAll)
			{
				if(vo.getEntryDate().equals(maxEntryDate))
				{
					_fb.setOpdIpdFlag(vo.getOpdIpdFlag());
					String advDays="0";
					if(vo.getStartDate()!=null)
					{
						String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
						DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
						//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
						Calendar calendar1=Calendar.getInstance();
						Calendar calendar2=Calendar.getInstance();
						calendar1.setTime(sinmpledf.parse(vo.getStartDate()));
						calendar2.setTime(sinmpledf.parse(sysadteStr));
						long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
						advDays=String.valueOf(noOfDays);
					}
					Integer days=Integer.parseInt(advDays);
					
					if(days>0)
					{
						vo.setIntakeDays(days.toString());
					}
					else
					{
						vo.setIntakeDays("0");
					}
					
					prevLast.add(vo);
				}
			}
			
			//comparing end date with system date(for revoke button) 
			Iterator<PatDrugTreatmentDetailVO> itr = prevLast.iterator();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			while(itr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = itr.next();
				Date sysDate=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				Date endDate = sysDate;
				if(patDrugTreatmentDetailVO.getEndDate()!=null)
					endDate=df.parse(patDrugTreatmentDetailVO.getEndDate());
				if(endDate.before(sysDate))
				{
					patDrugTreatmentDetailVO.setDate("before");
					
					//String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
					DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
					//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
					Calendar calendar1=Calendar.getInstance();
					Calendar calendar2=Calendar.getInstance();
					calendar1.setTime(sinmpledf.parse(patDrugTreatmentDetailVO.getStartDate()));
					calendar2.setTime(sinmpledf.parse(patDrugTreatmentDetailVO.getEndDate()));
					long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
					String advDays=String.valueOf(noOfDays);
					Integer days=Integer.parseInt(advDays);
					
					patDrugTreatmentDetailVO.setIntakeDays(days.toString());
					
					
										
				}
				else
				{
					patDrugTreatmentDetailVO.setDate("after");
					String advDays="0";
					if(patDrugTreatmentDetailVO.getStartDate()!=null)
					{
						String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
						DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
						//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
						Calendar calendar1=Calendar.getInstance();
						Calendar calendar2=Calendar.getInstance();
						calendar1.setTime(sinmpledf.parse(patDrugTreatmentDetailVO.getStartDate()));
						calendar2.setTime(sinmpledf.parse(sysadteStr));
						long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
						advDays=String.valueOf(noOfDays);
					}
					Integer days=Integer.parseInt(advDays);
					
					if(days>0)
					{
						patDrugTreatmentDetailVO.setIntakeDays(days.toString());
					}
					else
					{
						patDrugTreatmentDetailVO.setIntakeDays("0");
					}
					
				}
				if(patDrugTreatmentDetailVO.getRemarks()==null)
				{
					patDrugTreatmentDetailVO.setRemarks("");
				}
			}
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
					
			
			//for removing  revoked drug 
			Iterator<PatDrugTreatmentDetailVO> drugItr = prevLast.iterator();
			while(drugItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = drugItr.next();
				if(patDrugTreatmentDetailVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					drugItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
			
			
			//for last prescription detail
			
			List patDetailVOList=new ArrayList();
			patDetailVOList.add(voDP);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_DETAIL_VO_FOR_PRINTING,patDetailVOList);
			
			//List<PatDrugTreatmentDetailVO> drugPrintingList=(List)mpEssentials.get(OpdConfig.DRUG_LIST_FOR_PRINTING);
			List drugScheduleVOLst=(List)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			List drugListForPrint=new ArrayList();
			
			for(int i=0;i<prevLast.size();i++)
			{
				PatDrugTreatmentDetailVO patTreatVo=(PatDrugTreatmentDetailVO)prevLast.get(i);
				String doseTime="";
				String cutOffBefore="";
				String cutOffAfter="";
				_fb.setEmpName(patTreatVo.getEmpName());
				DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
				Calendar calendar1=Calendar.getInstance();
				Calendar calendar2=Calendar.getInstance();
				if(patTreatVo.getStartDate()!=null) calendar1.setTime(sinmpledf.parse(patTreatVo.getStartDate()));
				if(patTreatVo.getEndDate()!=null)calendar2.setTime(sinmpledf.parse(patTreatVo.getEndDate()));
				long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
				String advDays=String.valueOf(noOfDays);
				
				for(int j=0;j<drugScheduleVOLst.size();j++)
				{
					DrugSheduleDtlVO sheduleVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(j);
					if(patTreatVo.getEntryDate().equals(sheduleVO.getEntryDate()) && patTreatVo.getDrugId().equals(sheduleVO.getItemTypeId()) && patTreatVo.getSerialNo().equals(sheduleVO.getSerialNo()))
					{
						doseTime=doseTime+" & "+sheduleVO.getDoseTime()+" ("+ sheduleVO.getDoseName() +") ";
						cutOffBefore=sheduleVO.getCutOffTime();
						cutOffAfter=sheduleVO.getCutOffTimeafter();
					}
					if(j==(drugScheduleVOLst.size()-1))
					{
						if(!doseTime.equals(""))
						{
							doseTime=doseTime.substring(2);
						}
						
						patTreatVo.setDoseTime(doseTime);
						patTreatVo.setDays(advDays);
						patTreatVo.setCutOffBefore(cutOffBefore);
						patTreatVo.setCutOffAfter(cutOffAfter);
						drugListForPrint.add(patTreatVo);
						
					}
				}
			}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.DRUG_LIST_FOR_PRINTING, drugListForPrint);
			
			
			
			//getting today visit(drug detail)
			List<PatDrugTreatmentDetailVO> todayDrugDetailList = new ArrayList<PatDrugTreatmentDetailVO>();
			Iterator<PatDrugTreatmentDetailVO> prevAllDrugDetailItr=prevAll.iterator();
			while(prevAllDrugDetailItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=prevAllDrugDetailItr.next();
				if(patDrugTreatmentDetailVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()) )
				{
					
					todayDrugDetailList.add(patDrugTreatmentDetailVO);
					
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST, todayDrugDetailList);
			
			//concatenating drugId with itemtypeId
			//List<DrugDoseVO> drugDoseVOList = (List<DrugDoseVO>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			List<Entry> drugList=(List<Entry>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator<PatDrugTreatmentDetailVO> iter =lstDrugTreatmentDtl.iterator();
			
			while(iter.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO =iter.next();
				Iterator<Entry> itera =drugList.iterator();
				while(itera.hasNext())
				{
					Entry obj=itera.next();
					String[] itemIdAndItemTypeId=obj.getValue().split("#");
					String itemId=itemIdAndItemTypeId[0];
					String ItemTypeId=itemIdAndItemTypeId[1];
					if(patDrugTreatmentDetailVO.getDrugId().equals(itemId))
					{
						if(patDrugTreatmentDetailVO.getRemarks()==null)
						{
							patDrugTreatmentDetailVO.setRemarks("");
						}
						patDrugTreatmentDetailVO.setDrugId(patDrugTreatmentDetailVO.getDrugId()+"#"+ItemTypeId);					
					}
				}
			}
			
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			_fb.setDrugDetailRows(Integer.toString(lstDrugTreatmentDtl.size()));
			
	/***************set Essential for rest advice detail *******************************************/
			
			//setting the rest start date(system date)
			String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			_fb.setRestStartDate(String.valueOf(sysDate));	
			
			//getting today visit(rest advice)
			List prevAllRestAdvice = (List<PatDrugTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST);
			List todayAllRestAdvice=new ArrayList();
			List prevAllRestAdviceForPrinting=new ArrayList();
			
			Iterator prevAllRestAdviceItr=prevAllRestAdvice.iterator();
			while(prevAllRestAdviceItr.hasNext())
			{
				RestAdviceDtlVO restAdviceDtlVO=(RestAdviceDtlVO)prevAllRestAdviceItr.next();
				prevAllRestAdviceForPrinting.add(restAdviceDtlVO);
				if(restAdviceDtlVO.getEntryDate().equals(sysDate.toString()))
				{
					todayAllRestAdvice.add(restAdviceDtlVO);
					prevAllRestAdviceItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST, todayAllRestAdvice);
			mpEssentials.put(OpdConfig.PREV_REST_DETAIL_LIST_FOR_PRINTING, prevAllRestAdviceForPrinting);
			
	/********************************set Essential for external treatment******************************/ 		
			
			 List deptUnitWiseInstList=(List)mpEssentials.get(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER);
			 List deptUnitWiseActivityList=(List)mpEssentials.get(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER);
			
			 WebUTIL.setAttributeInSession(_rq, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, deptUnitWiseInstList);
			 WebUTIL.setAttributeInSession(_rq, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, deptUnitWiseActivityList);
			 
			 WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_CHOICE, "0");
			 
						
			//getting last visit(External Treatment detail)
			List<PatExtTreatmentDetailVO> prevAllExt = (List<PatExtTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST);
			List<PatExtTreatmentDetailVO> prevLastExt = new ArrayList<PatExtTreatmentDetailVO>();
			/*
			int maxVisitExt = 0;
			for(PatExtTreatmentDetailVO vo : prevAllExt)
				if(Integer.parseInt(vo.getEpisodeVisitNo())>maxVisitExt)
					maxVisitExt=Integer.parseInt(vo.getEpisodeVisitNo());

			for(PatExtTreatmentDetailVO vo : prevAllExt)
				if(vo.getEpisodeVisitNo().equals(Integer.toString(maxVisitExt)))
					prevLastExt.add(vo);
			*/
			String maxEntryDateForExtTreat=(String)mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO_FOR_EXTTREAT);
			for(PatExtTreatmentDetailVO vo : prevAllExt)
			{
				if(vo.getEntryDate().equals(maxEntryDateForExtTreat))
				{
					prevLastExt.add(vo);
				}
			}
			/*
			//for removing  revoked drug 
			Iterator extTreatItr = prevLastExt.iterator();
			while(extTreatItr.hasNext())
			{
				PatExtTreatmentDetailVO vo = (PatExtTreatmentDetailVO)extTreatItr.next();
				if(vo.getRxContinue().equals("0"))
				{
					extTreatItr.remove();
				}
			}
			*/
			
			//comparing end date with system date(for extRevoke button) 
			Iterator<PatExtTreatmentDetailVO> prevLastExtItr = prevLastExt.iterator();
			while(prevLastExtItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO = prevLastExtItr.next();
				Date endDateExt=df.parse(patExtTreatmentDetailVO.getEndDate());
				Date sysDateExt=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				if(endDateExt.before(sysDateExt))
				{
					patExtTreatmentDetailVO.setDate("before");
										
				}
				if(patExtTreatmentDetailVO.getRemarks()==null || patExtTreatmentDetailVO.getRemarks().equals(null))
				{
					patExtTreatmentDetailVO.setRemarks("");
				}
				if(patExtTreatmentDetailVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					prevLastExtItr.remove();
				}
			}
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST, prevLastExt);
			
			//getting today visit(drug detail)
			List<PatExtTreatmentDetailVO> todayExtDetailList = new ArrayList<PatExtTreatmentDetailVO>();
			Iterator<PatExtTreatmentDetailVO> prevAllExtDetailItr=prevAllExt.iterator();
			while(prevAllExtDetailItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO=prevAllExtDetailItr.next();
				if(patExtTreatmentDetailVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))
				{
					todayExtDetailList.add(patExtTreatmentDetailVO);
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_EXT_DETAIL_LIST, todayExtDetailList);
			
			// Setting Initial External treatment Detail List 
			List<PatExtTreatmentDetailVO> lstExtTreatmentDtl = null;
			lstExtTreatmentDtl= new ArrayList<PatExtTreatmentDetailVO>();
			lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);
			_fb.setExtDrugDetailRows(Integer.toString(lstExtTreatmentDtl.size()));
			
			List otherInstAndActivityList=(List)mpEssentials.get(OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST);
			List prevLastOtherInstAndActLst=new ArrayList();
			for(int i=0;i<otherInstAndActivityList.size();i++)
			{
				PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)otherInstAndActivityList.get(i);
				if(vo.getEntryDate().equals(maxEntryDateForExtTreat))
				{
					prevLastOtherInstAndActLst.add(vo);
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST, prevLastOtherInstAndActLst);
			
			List otherInstList=new ArrayList();
			List activityList=new ArrayList();
			
			for(int i=0;i<prevLastOtherInstAndActLst.size();i++)
			{
				PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)prevLastOtherInstAndActLst.get(i);
				if(vo.getTreatmentType().equals(OpdConfig.OTHER_INSTRUCTION))
				{
					otherInstList.add(vo);
				}
				else
				{
					activityList.add(vo);
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_INSTRUCTION_LIST, otherInstList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_ACTIVITY_LIST, activityList);
			
	/************************** set essential for diet advice detail******************************/
			
			//setting the Diet start date(system date)
			
			_fb.setDietStartDate(String.valueOf(sysDate));
			
			//getting today visit(Diet advice)
			List<PatDietAdviceDetailVO> prevAllDietAdviceForPrinting=new ArrayList<PatDietAdviceDetailVO>();
						
			List<PatDietAdviceDetailVO> prevAllDietAdvice= (List<PatDietAdviceDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST);
			List<PatDietAdviceDetailVO> todayAllDietAdvice=new ArrayList();
			
						
			Iterator<PatDietAdviceDetailVO> prevAllDietAdviceItr=prevAllDietAdvice.iterator();
			while(prevAllDietAdviceItr.hasNext())
			{
				PatDietAdviceDetailVO patDietAdviceDetailVO=prevAllDietAdviceItr.next();
				prevAllDietAdviceForPrinting.add(patDietAdviceDetailVO);
				if(patDietAdviceDetailVO.getEntryDate().equals(sysDate.toString()))
				{
					todayAllDietAdvice.add(patDietAdviceDetailVO);
					prevAllDietAdviceItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST, todayAllDietAdvice);
			mpEssentials.put(OpdConfig.PREV_ALL_DIET_ADVICE_DTL_FOR_PRINTING, prevAllDietAdviceForPrinting);
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
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
	
	
	/* before change 06.04.2016  */
	
	
	/*public static void setEssentials(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		
		Map mpEssentials = null;
		List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			// Getting Patient Demographic Detail 
			InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			
			// System Date
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
			_fb.setSysDate(sysadteString);
			_fb.setOpdIpdFlag("");
						
			//This sysdate string for validation
			String sysDateStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			_fb.setSysDateForValidation(sysDateStr);
			
			String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			_fb.setDeskType(deskType);
			
			// Setting Desk Essentials
			// Episode, Visit, Admission No
			PatientDetailVO voDP = null;
			if(session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
			{
				PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);				
				for (PatientDetailVO vo : al)
					if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
					{
						voDP = vo;
						break;					
					}
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setAdmissionNo(voDP.getPatAdmNo());
				_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			}

			else if(session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				_fb.setEpisodeCode(voDP.getEpisodeCode());
				_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
				_fb.setAdmissionNo(voDP.getPatAdmNo());
				_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			}
			
			
			mpEssentials=PatientTreatmentDetailDATA.getPatTreatmentDetailEssential(_fb.getPatCrNo(), _fb.getEpisodeCode(),voDP.getDepartmentUnitCode(),ptaientDetailVO.getPatGenderCode(),voDP, userVO);
			
			*//**********************************set essential for drug detail***********************************//*
			
			//for drug log
			List drugLogList=(List)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG);
			
			if((drugLogList!=null) && (drugLogList.size()!=0))
			{
				for(int i=0;i<drugLogList.size();i++)
				{
					PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)drugLogList.get(i);
					
					if(vo.getRxContinue().equals(OpdConfig.SIMPLE_RX))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="Rx "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("Rx ");
						}
						
					}
					
					if(vo.getRxContinue().equals(OpdConfig.REVOKE))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="Revoked "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("Revoked");
						}
						
					}
					
					if(vo.getRxContinue().equals(OpdConfig.NEW_RECORD))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="New "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("New");
						}
						
					}
					
					if(vo.getRxContinue().equals(OpdConfig.CHANGE_IN_PREV))
					{
						if(vo.getIsValid().equals("InActive"))
						{
							String status="Changed "+ "( "+vo.getIsValid()+" )";
							vo.setRxContinueDesc(status);
						}
						else
						{
							vo.setRxContinueDesc("Change");
						}
						
					}
				}
			}
			
			
			//this is for not repeating presciption date at the time of view prescription date only view in a single row
			List prevAllList=new ArrayList();
			prevAllList= (List)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG);
			
			List entryDateList=new ArrayList();
			
			for(int i=0;i<prevAllList.size();i++)
			{
				PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevAllList.get(i);
				
				boolean flag=false;
				
				for(int j=0;j<entryDateList.size();j++)
				{
					String entryDate=(String)entryDateList.get(j);
					
					if(entryDate.equals(vo.getEntryDate()))
					{
						flag=true;
					}
				}
				
				if(!flag)
				{
					entryDateList.add(vo.getEntryDate());
				}
			}
			
			List tempDrugLogList=new ArrayList();
			
			for(int i=0;i<entryDateList.size();i++)
			{
				String entryDate=(String)entryDateList.get(i);
				Boolean flag=false;
				
				for(int j=0;j<prevAllList.size();j++)
				{
					
					
					PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevAllList.get(j);
					if(entryDate.equals(vo.getEntryDate()))
					{
						if(!flag)
						{
							if(vo.getOpdIpdFlag().equals(OpdConfig.IS_NOT_DISCHARGE_ADVICE))
							{
								if((vo.getAdmissionNo()!=null) && (!vo.getAdmissionNo().equals("")))
								{
									String entyrDate=vo.getEntryDate()+" ( IPD )";
									vo.setEntryDate(entyrDate);
								}
								else
								{
									String entyrDate=vo.getEntryDate()+" ( OPD )";
									vo.setEntryDate(entyrDate);
								}
								
								
							}
							else
							{
								String entyrDate=vo.getEntryDate()+" ( Discharge )";
								vo.setEntryDate(entyrDate);
							}
							tempDrugLogList.add(vo);
							flag=true;
						}
						else
						{
							vo.setEntryDate("");
							tempDrugLogList.add(vo);
						}
						
					}
				}
				
			}
			
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG, tempDrugLogList);
			
			
			
			
			
			//_fb.setInstActivityType("1");
			// Setting Initial Drug Detail List
			lstDrugTreatmentDtl= new ArrayList<PatDrugTreatmentDetailVO>();
			String maxEntryDate=(String)mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO);
			if(maxEntryDate==null)
			{
				List defaultDrugProfileDrugLst=(List)mpEssentials.get(OpdConfig.DEFAULT_DRUG_PROFILE_DRUG_LIST);
				for(int i=0;i<defaultDrugProfileDrugLst.size();i++)
				{
					PatDrugTreatmentDetailVO vo=_fb.resetVO(new PatDrugTreatmentDetailVO());
					PatDrugTreatmentDetailVO profileVO=(PatDrugTreatmentDetailVO)defaultDrugProfileDrugLst.get(i);
					
					
					vo.setDrugId(profileVO.getDrugId());
					vo.setDrugName(profileVO.getDrugName());
					vo.setDoseId(profileVO.getDoseId());
					vo.setDoseName(profileVO.getDoseName());
					vo.setFrequencyId(profileVO.getFrequencyId());
					vo.setDays(profileVO.getDays());
					//vo.setStartDate(_fb.getSelStartDay()[i]);
					if(profileVO.getRemarks()!=null)
					{
						vo.setRemarks(profileVO.getRemarks());
					}
					else
					{
						vo.setRemarks("");
					}
					
					//vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
					vo.setIsEmptyStomach(profileVO.getIsEmptyStomach());
					//vo.setDrugRouteId(_fb.getSelDrugRouteId()[i]);
					//vo.setDrugRouteName(_fb.getSelDrugRouteName()[i]);
					//vo.setQuantity(_fb.getQuantity()[i]);
					lstDrugTreatmentDtl.add(vo);
				}
				
			}
						
			lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			
						
			//getting last visit(drug detail)
			List<PatDrugTreatmentDetailVO> prevAll = (List<PatDrugTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST);
			
			
			if(maxEntryDate==null)
			{
				_fb.setPrescriptionDate("");	
			}
			else
			{
				_fb.setPrescriptionDate(maxEntryDate);	
			}
			
			
			List<PatDrugTreatmentDetailVO> prevLast = new ArrayList<PatDrugTreatmentDetailVO>();
			for(PatDrugTreatmentDetailVO vo : prevAll)
			{
				if(vo.getEntryDate().equals(maxEntryDate))
				{
					_fb.setOpdIpdFlag(vo.getOpdIpdFlag());
					String advDays="0";
					if(vo.getStartDate()!=null)
					{
						String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
						DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
						//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
						Calendar calendar1=Calendar.getInstance();
						Calendar calendar2=Calendar.getInstance();
						calendar1.setTime(sinmpledf.parse(vo.getStartDate()));
						calendar2.setTime(sinmpledf.parse(sysadteStr));
						long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
						advDays=String.valueOf(noOfDays);
					}
					Integer days=Integer.parseInt(advDays);
					
					if(days>0)
					{
						vo.setIntakeDays(days.toString());
					}
					else
					{
						vo.setIntakeDays("0");
					}
					
					prevLast.add(vo);
				}
			}
			
			//comparing end date with system date(for revoke button) 
			Iterator<PatDrugTreatmentDetailVO> itr = prevLast.iterator();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			while(itr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = itr.next();
				Date sysDate=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				Date endDate = sysDate;
				if(patDrugTreatmentDetailVO.getEndDate()!=null)
					endDate=df.parse(patDrugTreatmentDetailVO.getEndDate());
				if(endDate.before(sysDate))
				{
					patDrugTreatmentDetailVO.setDate("before");
					
					//String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
					DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
					//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
					Calendar calendar1=Calendar.getInstance();
					Calendar calendar2=Calendar.getInstance();
					calendar1.setTime(sinmpledf.parse(patDrugTreatmentDetailVO.getStartDate()));
					calendar2.setTime(sinmpledf.parse(patDrugTreatmentDetailVO.getEndDate()));
					long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
					String advDays=String.valueOf(noOfDays);
					Integer days=Integer.parseInt(advDays);
					
					patDrugTreatmentDetailVO.setIntakeDays(days.toString());
					
					
										
				}
				else
				{
					patDrugTreatmentDetailVO.setDate("after");
					String advDays="0";
					if(patDrugTreatmentDetailVO.getStartDate()!=null)
					{
						String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
						DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
						//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
						Calendar calendar1=Calendar.getInstance();
						Calendar calendar2=Calendar.getInstance();
						calendar1.setTime(sinmpledf.parse(patDrugTreatmentDetailVO.getStartDate()));
						calendar2.setTime(sinmpledf.parse(sysadteStr));
						long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
						advDays=String.valueOf(noOfDays);
					}
					Integer days=Integer.parseInt(advDays);
					
					if(days>0)
					{
						patDrugTreatmentDetailVO.setIntakeDays(days.toString());
					}
					else
					{
						patDrugTreatmentDetailVO.setIntakeDays("0");
					}
					
				}
				if(patDrugTreatmentDetailVO.getRemarks()==null)
				{
					patDrugTreatmentDetailVO.setRemarks("");
				}
			}
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
					
			
			//for removing  revoked drug 
			Iterator<PatDrugTreatmentDetailVO> drugItr = prevLast.iterator();
			while(drugItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = drugItr.next();
				if(patDrugTreatmentDetailVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					drugItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
			
			
			//for last prescription detail
			
			List patDetailVOList=new ArrayList();
			patDetailVOList.add(voDP);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_DETAIL_VO_FOR_PRINTING,patDetailVOList);
			
			//List<PatDrugTreatmentDetailVO> drugPrintingList=(List)mpEssentials.get(OpdConfig.DRUG_LIST_FOR_PRINTING);
			List drugScheduleVOLst=(List)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			List drugListForPrint=new ArrayList();
			
			for(int i=0;i<prevLast.size();i++)
			{
				PatDrugTreatmentDetailVO patTreatVo=(PatDrugTreatmentDetailVO)prevLast.get(i);
				String doseTime="";
				_fb.setEmpName(patTreatVo.getEmpName());
				DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
				Calendar calendar1=Calendar.getInstance();
				Calendar calendar2=Calendar.getInstance();
				calendar1.setTime(sinmpledf.parse(patTreatVo.getStartDate()));
				calendar2.setTime(sinmpledf.parse(patTreatVo.getEndDate()));
				long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
				String advDays=String.valueOf(noOfDays);
				
				for(int j=0;j<drugScheduleVOLst.size();j++)
				{
					DrugSheduleDtlVO sheduleVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(j);
					if(patTreatVo.getEntryDate().equals(sheduleVO.getEntryDate()) && patTreatVo.getDrugId().equals(sheduleVO.getItemTypeId()) && patTreatVo.getSerialNo().equals(sheduleVO.getSerialNo()))
					{
						doseTime=doseTime+" & "+sheduleVO.getDoseTime()+" ("+ sheduleVO.getDoseName() +") ";
						
					}
					if(j==(drugScheduleVOLst.size()-1))
					{
						if(!doseTime.equals(""))
						{
							doseTime=doseTime.substring(2);
						}
						
						patTreatVo.setDoseTime(doseTime);
						patTreatVo.setDays(advDays);
						drugListForPrint.add(patTreatVo);
						
					}
				}
			}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.DRUG_LIST_FOR_PRINTING, drugListForPrint);
			
			
			
			//getting today visit(drug detail)
			List<PatDrugTreatmentDetailVO> todayDrugDetailList = new ArrayList<PatDrugTreatmentDetailVO>();
			Iterator<PatDrugTreatmentDetailVO> prevAllDrugDetailItr=prevAll.iterator();
			while(prevAllDrugDetailItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=prevAllDrugDetailItr.next();
				if(patDrugTreatmentDetailVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()) )
				{
					
					todayDrugDetailList.add(patDrugTreatmentDetailVO);
					
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST, todayDrugDetailList);
			
			//concatenating drugId with itemtypeId
			//List<DrugDoseVO> drugDoseVOList = (List<DrugDoseVO>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			List<Entry> drugList=(List<Entry>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator<PatDrugTreatmentDetailVO> iter =lstDrugTreatmentDtl.iterator();
			
			while(iter.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO =iter.next();
				Iterator<Entry> itera =drugList.iterator();
				while(itera.hasNext())
				{
					Entry obj=itera.next();
					String[] itemIdAndItemTypeId=obj.getValue().split("#");
					String itemId=itemIdAndItemTypeId[0];
					String ItemTypeId=itemIdAndItemTypeId[1];
					if(patDrugTreatmentDetailVO.getDrugId().equals(itemId))
					{
						if(patDrugTreatmentDetailVO.getRemarks()==null)
						{
							patDrugTreatmentDetailVO.setRemarks("");
						}
						patDrugTreatmentDetailVO.setDrugId(patDrugTreatmentDetailVO.getDrugId()+"#"+ItemTypeId);					
					}
				}
			}
			
			
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			_fb.setDrugDetailRows(Integer.toString(lstDrugTreatmentDtl.size()));
			
	*//***************set Essential for rest advice detail *******************************************//*
			
			//setting the rest start date(system date)
			String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			_fb.setRestStartDate(String.valueOf(sysDate));	
			
			//getting today visit(rest advice)
			List prevAllRestAdvice = (List<PatDrugTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_REST_DETAIL_LIST);
			List todayAllRestAdvice=new ArrayList();
			List prevAllRestAdviceForPrinting=new ArrayList();
			
			Iterator prevAllRestAdviceItr=prevAllRestAdvice.iterator();
			while(prevAllRestAdviceItr.hasNext())
			{
				RestAdviceDtlVO restAdviceDtlVO=(RestAdviceDtlVO)prevAllRestAdviceItr.next();
				prevAllRestAdviceForPrinting.add(restAdviceDtlVO);
				if(restAdviceDtlVO.getEntryDate().equals(sysDate.toString()))
				{
					todayAllRestAdvice.add(restAdviceDtlVO);
					prevAllRestAdviceItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST, todayAllRestAdvice);
			mpEssentials.put(OpdConfig.PREV_REST_DETAIL_LIST_FOR_PRINTING, prevAllRestAdviceForPrinting);
			
	*//********************************set Essential for external treatment******************************//* 		
			
			 List deptUnitWiseInstList=(List)mpEssentials.get(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER);
			 List deptUnitWiseActivityList=(List)mpEssentials.get(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER);
			
			 WebUTIL.setAttributeInSession(_rq, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, deptUnitWiseInstList);
			 WebUTIL.setAttributeInSession(_rq, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, deptUnitWiseActivityList);
			 
			 WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_CHOICE, "0");
			 
						
			//getting last visit(External Treatment detail)
			List<PatExtTreatmentDetailVO> prevAllExt = (List<PatExtTreatmentDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST);
			List<PatExtTreatmentDetailVO> prevLastExt = new ArrayList<PatExtTreatmentDetailVO>();
			
			int maxVisitExt = 0;
			for(PatExtTreatmentDetailVO vo : prevAllExt)
				if(Integer.parseInt(vo.getEpisodeVisitNo())>maxVisitExt)
					maxVisitExt=Integer.parseInt(vo.getEpisodeVisitNo());

			for(PatExtTreatmentDetailVO vo : prevAllExt)
				if(vo.getEpisodeVisitNo().equals(Integer.toString(maxVisitExt)))
					prevLastExt.add(vo);
			
			String maxEntryDateForExtTreat=(String)mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO_FOR_EXTTREAT);
			for(PatExtTreatmentDetailVO vo : prevAllExt)
			{
				if(vo.getEntryDate().equals(maxEntryDateForExtTreat))
				{
					prevLastExt.add(vo);
				}
			}
			
			//for removing  revoked drug 
			Iterator extTreatItr = prevLastExt.iterator();
			while(extTreatItr.hasNext())
			{
				PatExtTreatmentDetailVO vo = (PatExtTreatmentDetailVO)extTreatItr.next();
				if(vo.getRxContinue().equals("0"))
				{
					extTreatItr.remove();
				}
			}
			
			
			//comparing end date with system date(for extRevoke button) 
			Iterator<PatExtTreatmentDetailVO> prevLastExtItr = prevLastExt.iterator();
			while(prevLastExtItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO = prevLastExtItr.next();
				Date endDateExt=df.parse(patExtTreatmentDetailVO.getEndDate());
				Date sysDateExt=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				if(endDateExt.before(sysDateExt))
				{
					patExtTreatmentDetailVO.setDate("before");
										
				}
				if(patExtTreatmentDetailVO.getRemarks()==null || patExtTreatmentDetailVO.getRemarks().equals(null))
				{
					patExtTreatmentDetailVO.setRemarks("");
				}
				if(patExtTreatmentDetailVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					prevLastExtItr.remove();
				}
			}
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST, prevLastExt);
			
			//getting today visit(drug detail)
			List<PatExtTreatmentDetailVO> todayExtDetailList = new ArrayList<PatExtTreatmentDetailVO>();
			Iterator<PatExtTreatmentDetailVO> prevAllExtDetailItr=prevAllExt.iterator();
			while(prevAllExtDetailItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO=prevAllExtDetailItr.next();
				if(patExtTreatmentDetailVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()))
				{
					todayExtDetailList.add(patExtTreatmentDetailVO);
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_EXT_DETAIL_LIST, todayExtDetailList);
			
			// Setting Initial External treatment Detail List 
			List<PatExtTreatmentDetailVO> lstExtTreatmentDtl = null;
			lstExtTreatmentDtl= new ArrayList<PatExtTreatmentDetailVO>();
			lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);
			_fb.setExtDrugDetailRows(Integer.toString(lstExtTreatmentDtl.size()));
			
			List otherInstAndActivityList=(List)mpEssentials.get(OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST);
			List prevLastOtherInstAndActLst=new ArrayList();
			for(int i=0;i<otherInstAndActivityList.size();i++)
			{
				PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)otherInstAndActivityList.get(i);
				if(vo.getEntryDate().equals(maxEntryDateForExtTreat))
				{
					prevLastOtherInstAndActLst.add(vo);
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST, prevLastOtherInstAndActLst);
			
			List otherInstList=new ArrayList();
			List activityList=new ArrayList();
			
			for(int i=0;i<prevLastOtherInstAndActLst.size();i++)
			{
				PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)prevLastOtherInstAndActLst.get(i);
				if(vo.getTreatmentType().equals(OpdConfig.OTHER_INSTRUCTION))
				{
					otherInstList.add(vo);
				}
				else
				{
					activityList.add(vo);
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_INSTRUCTION_LIST, otherInstList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PREV_OTHER_ACTIVITY_LIST, activityList);
			
	*//************************** set essential for diet advice detail******************************//*
			
			//setting the Diet start date(system date)
			
			_fb.setDietStartDate(String.valueOf(sysDate));
			
			//getting today visit(Diet advice)
			List<PatDietAdviceDetailVO> prevAllDietAdviceForPrinting=new ArrayList<PatDietAdviceDetailVO>();
						
			List<PatDietAdviceDetailVO> prevAllDietAdvice= (List<PatDietAdviceDetailVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DIET_ADVICE_DETAIL_LIST);
			List<PatDietAdviceDetailVO> todayAllDietAdvice=new ArrayList();
			
						
			Iterator<PatDietAdviceDetailVO> prevAllDietAdviceItr=prevAllDietAdvice.iterator();
			while(prevAllDietAdviceItr.hasNext())
			{
				PatDietAdviceDetailVO patDietAdviceDetailVO=prevAllDietAdviceItr.next();
				prevAllDietAdviceForPrinting.add(patDietAdviceDetailVO);
				if(patDietAdviceDetailVO.getEntryDate().equals(sysDate.toString()))
				{
					todayAllDietAdvice.add(patDietAdviceDetailVO);
					prevAllDietAdviceItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST, todayAllDietAdvice);
			mpEssentials.put(OpdConfig.PREV_ALL_DIET_ADVICE_DTL_FOR_PRINTING, prevAllDietAdviceForPrinting);
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
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
	
	
	
	
/*
	// Getting and Setting Instant Drug List 
	public static void getSetDrugList(PatientTreatmentDetailFB fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List<Entry> lstMain = null;
		String result = "";
		
		String searchText = _rq.getParameter("text");
		try
		{
			lstMain = (List<Entry>) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			
			searchText = searchText.trim();
			if (!searchText.equals(""))
			{
				for(Entry entry : lstMain)          
				{
					String searchValue = entry.getLabel();

					if ((searchValue != null) && (!searchValue.equals("")) && (searchValue.toUpperCase().startsWith(searchText.toUpperCase())))
					{
						result = result + entry.getValue() + "$" + searchValue + "$";
												
					}

				}
				if(!result.equals(""))	result = result.substring(0,result.length()-1);
			}
			try
			{
				PrintWriter writer = _resp.getWriter();
				if (!searchText.equals(""))												
				{           															
					writer.write(result);													
				}					
				else writer.write("");												
			}												
			catch (IOException e)				
			{								
				e.printStackTrace();					
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	*/
	
	//get set instance EXT Treatment List
	public static void getSetExtTreatmentList(PatientTreatmentDetailFB fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		
		List<Entry> lstMain = null;
		String result = "";
		
		String searchText = _rq.getParameter("text");
		try
		{
			lstMain = (List<Entry>) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_EXT_TREATMENT);

			searchText = searchText.trim();
			if (!searchText.equals(""))
			{
				for(Entry entry : lstMain)          
				{
					String searchValue = entry.getLabel();

					if ((searchValue != null) && (!searchValue.equals("")) && (searchValue.toUpperCase().startsWith(searchText.toUpperCase())))
					{
						result = result + entry.getValue() + "$" + searchValue + "$";
					}

				}
				if(!result.equals(""))	result = result.substring(0,result.length()-1);
			}
			try
			{
				PrintWriter writer = _resp.getWriter();
				if (!searchText.equals(""))												
				{           															
					writer.write(result);													
				}					
				else writer.write("");												
			}												
			catch (IOException e)				
			{								
				e.printStackTrace();					
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Getting and Setting Drug Dose List By Drug Item Type 
	public static void getSetDrugDoseList(PatientTreatmentDetailFB fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		//HttpSession session = _rq.getSession();
		UserVO userVO = getUserVO(_rq);
		
		//List<DrugDoseVO> lstMain = null;
		String result = "";
		
		//String itemTypeId = _rq.getParameter("drugItemType");
		String drugId = _rq.getParameter("drugId");
		String drugName = _rq.getParameter("drugName");
		String patcrNo = _rq.getParameter("patcrNo");
		String departmentUnitCode=_rq.getParameter("departmentUnitCode");
		try
		{
			/*lstMain = (List<DrugDoseVO>) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			if (lstMain!=null && itemTypeId!=null && !itemTypeId.equals(""))
				for(DrugDoseVO vo : lstMain)
					if(vo.getItemTypeId().equals(itemTypeId))
					{
						result = result + vo.getDoseId()+"^"+vo.getIsFrequencyBound()+"^"+vo.getDoseQty() + "$" + vo.getDoseName() + "$";
					}
			if(!result.equals(""))	result = result.substring(0,result.length()-1);*/
			
			
			PatDrugTreatmentDetailVO patDrugDtlVO=new PatDrugTreatmentDetailVO();
			patDrugDtlVO.setPatCrNo(patcrNo);
			patDrugDtlVO.setDrugId(drugId);
			patDrugDtlVO.setDrugName(drugName);
			patDrugDtlVO.setDepartmentUnitCode(departmentUnitCode);
			
			//String allergyStatus=PatientTreatmentDetailDATA.getDrugAllergyAllerts(patDrugDtlVO,userVO);
			//String prevDrugStatus=PatientTreatmentDetailDATA.getPrevDrugStatus(patDrugDtlVO, userVO);
			//String drugReactionStatus=PatientTreatmentDetailDATA.getdrugReactionStatus(patDrugDtlVO, userVO); // commented to call a single procedure to return all (allergies, status, reaction) alerts
			result=PatientTreatmentDetailDATA.getdrugAdviceAlerts(patDrugDtlVO, userVO);    // added by manisha gangwar  date:13.4.2016 for drug advice alerts from a single procedure
			System.out.println(result);
			_resp.getWriter().write(result.toString());
			//result=result + "#" + allergyStatus + "#" + prevDrugStatus;
			//result=allergyStatus + "#" + prevDrugStatus + "#" + drugReactionStatus;
						
			/*try
			{
				//PrintWriter writer = _resp.getWriter();
				//writer.write(result);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}*/
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	//getting drug route list
/*	public static String getSetDrugRouteList(PatientTreatmentDetailFB fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		UserVO userVO = getUserVO(_rq);
		
		List<DrugRouteMstVO> lstMain = null;
		String result = "";
		List drugRouteLst=new ArrayList();
		
		String drugItemId=_rq.getParameter("drugItemId");
	//	String itemTypeId = _rq.getParameter("drugItemType");
		String combo="";
		
		try
		{
			lstMain = (List) session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE);
			if (lstMain!=null && drugItemId!=null && !drugItemId.equals(""))
				for(DrugRouteMstVO vo : lstMain)
					if(vo.getDrugItemId().equals(drugItemId))
					{
						result = result + vo.getDrugRouteId() + "^" + vo.getDrugRouteName() + "^";
											
					}
			if(!result.equals(""))	result = result.substring(0,result.length()-1);
						
			try
			{
				PrintWriter writer = _resp.getWriter();
				writer.write(result);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		//return combo;
		return result;
	}
	*/
	

	// Adding New Drug Row
	public static void addNewDrugRow(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		
		List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		try
		{
			setSysdate(_rq);
			
			// for after submitting change drug page revoke checked  check box remain checked 
			 String revokeItemId="";
			 String[] revokeArray=_fb.getRevoke();
			 if(revokeArray!=null)
			 {
				 for(int i=0;i<revokeArray.length;i++)
				 {
					 String[] itemIdAndIndexArray=revokeArray[i].split("\\$"); 
					 revokeItemId=itemIdAndIndexArray[0]+"$"+itemIdAndIndexArray[2]+"$"+itemIdAndIndexArray[3]+"#"+revokeItemId;
				 }
				 if(!revokeItemId.equals(""))	revokeItemId = revokeItemId.substring(0,revokeItemId.length()-1);
				 _fb.setRevokeHidden(revokeItemId);
			 }
			
			 
			 /*
			 // for after submitting change drug page extension checked  check box remain checked 
			 String extensionItemId="";
			 String[] extensionArray=_fb.getExtension();
			 
			 if(extensionArray!=null)
			 {
				 for(int i=0;i<extensionArray.length;i++)
				 {
					String[] itemIdAndIndexArray=extensionArray[i].split("\\$");
					 extensionItemId=itemIdAndIndexArray[0]+"$"+itemIdAndIndexArray[2]+"$"+itemIdAndIndexArray[3]+"#"+extensionItemId;
				 }
				 if(!extensionItemId.equals(""))	extensionItemId = extensionItemId.substring(0,extensionItemId.length()-1);
				 _fb.setExtensionHidden(extensionItemId);
			 }
			 
			 	 
			 // for after submitting change drug page extensionDays  values remain same 
			 String extensiondays="";
			 String[] extensionDaysArray=_fb.getExtensionDays();
			 
			 if(extensionDaysArray!=null)
			 {
				 for(int i=0;i<extensionDaysArray.length;i++)
				 {
					 //String[] itemIdAndIndexArray=extensionDaysArray[i].split("\\$");
					 extensiondays=extensionDaysArray[i]+"#"+extensiondays;
				 }
				 if(!extensiondays.equals(""))	extensiondays = extensiondays.substring(0,extensiondays.length()-1);
				 _fb.setExtensionDaysHidden(extensiondays);
			 }
			
			*/
			// Setting Entered and New Row
			lstDrugTreatmentDtl= new ArrayList<PatDrugTreatmentDetailVO>();;
			int rows = Integer.parseInt(_fb.getDrugDetailRows());
			//String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
			for(int i=0;i<rows;i++)
			{
				PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
				vo.setDrugId(_fb.getSelDrugId()[i]+"#"+_fb.getSelDrugItemTypeId()[i]);
				vo.setDrugName(_fb.getSelDrugName()[i]);
				vo.setDoseId(_fb.getSelDoseId()[i]);
				vo.setDoseName(_fb.getSelDoseName()[i]);
				vo.setFrequencyId(_fb.getSelFrequencyId()[i]);
				vo.setDays(_fb.getSelDays()[i]);
				vo.setStartDate(_fb.getSelStartDay()[i]);
				vo.setRemarks(_fb.getSelInstructions()[i]);
				vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				//vo.setIsEmptyStomach(isEmptyStomach[i]);
				vo.setDrugRouteId(_fb.getSelDrugRouteId()[i]);
				vo.setDrugRouteName(_fb.getSelDrugRouteName()[i]);
				vo.setQuantity(_fb.getQuantity()[i]);
				vo.setDrugAdminId(_fb.getSelDrugAdminId()[i]);
				vo.setDrugAdminName(_fb.getSelDrugAdminName()[i]);
				vo.setDrugBrandId(_fb.getSelDrugBrandId()[i]);
				//vo.setDrugAdminId(_fb.getDrugAdminId());
				//vo.setDrugAdminName(_fb.getDrugAdminName());
				vo.setSelStock(_fb.getSelStock()[i]);
				lstDrugTreatmentDtl.add(vo);
			}			
			lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
			_fb.setDrugDetailRows(Integer.toString(rows+1)) ;
			
			_fb.setActiveTab("tabDrugAdvice");
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			/*
			// Saving last row of External treatment 
			
			HttpSession session=WebUTIL.getSession(_rq);
			List lstExtTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST);
			
			if(lstExtTreatmentDtl.size()>1)
			{
				//removing the blank row from lstExtTreatmentDtl bcz user can fill or not fill the last row
				Iterator<PatExtTreatmentDetailVO> removeBlankRowExtItr=lstExtTreatmentDtl.iterator();
				while(removeBlankRowExtItr.hasNext())
				{
					PatExtTreatmentDetailVO patExtTreatmentDetailVO=removeBlankRowExtItr.next();
					if(patExtTreatmentDetailVO.getDays().equals(""))
					{
						removeBlankRowExtItr.remove();
						Integer extRowsLength = Integer.parseInt(_fb.getExtDrugDetailRows())-1;
						_fb.setExtDrugDetailRows(extRowsLength.toString());
					}
				}
			}
			
			
			
			int extRowsLength = Integer.parseInt(_fb.getExtDrugDetailRows());
			int index=0;
			index=extRowsLength-1;
			
			Boolean flag=false;
			
			for(int i=0;i<lstExtTreatmentDtl.size();i++)
			{
				PatExtTreatmentDetailVO extVO=(PatExtTreatmentDetailVO)lstExtTreatmentDtl.get(i);
				if(_fb.getSelExtTreatmentName()[index].equals(extVO.getExtTreatmentName()))
				{
					flag=true;
				}
			}
				
			if(!_fb.getSelExtTreatmentName()[index].equals("") && !(flag))
			{
				//removing the blank row from lstExtTreatmentDtl bcz user can fill or not fill the last row
				Iterator<PatExtTreatmentDetailVO> removeBlankRowExtItr=lstExtTreatmentDtl.iterator();
				while(removeBlankRowExtItr.hasNext())
				{
					PatExtTreatmentDetailVO patExtTreatmentDetailVO=removeBlankRowExtItr.next();
					if(patExtTreatmentDetailVO.getDays().equals(""))
					{
						removeBlankRowExtItr.remove();
					}
				}
				
				
				PatExtTreatmentDetailVO vo = new PatExtTreatmentDetailVO();
				
				//vo.setDoseId(_fb.getSelExtDoseId()[i]);
				//vo.setDoseName(_fb.getSelExtDoseName()[i]);
				vo.setFrequencyId(_fb.getSelExtFrequencyId()[index]);
				vo.setDays(_fb.getSelExtDays()[index]);
				vo.setStartDate(_fb.getSelExtStartDay()[index]);
				vo.setRemarks(_fb.getSelExtInstructions()[index]);
				vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
				vo.setExtTreatmentId(_fb.getSelExtTreatmentId()[index]);
				vo.setExtTreatmentName(_fb.getSelExtTreatmentName()[index]);
				lstExtTreatmentDtl.add(vo);	
				
				lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
				_fb.setExtDrugDetailRows(Integer.toString(extRowsLength+1)) ;
							
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);
			}
			
			*/
			HttpSession session=WebUTIL.getSession(_rq);
			List lstExtTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST);
			 
			 int extRowsLength = Integer.parseInt(_fb.getExtDrugDetailRows());
			 int index=0;
			index=extRowsLength-1;
			 
			// if(!_fb.getSelDrugId()[index].equals("0 ") && Integer.parseInt(_fb.getIndexDelete())!=index)
			 if(!_fb.getSelExtTreatmentName()[index].equals(""))
			 {
				 PatExtTreatmentDetailVO vo = new PatExtTreatmentDetailVO();
					
					//vo.setDoseId(_fb.getSelExtDoseId()[i]);
					//vo.setDoseName(_fb.getSelExtDoseName()[i]);
					vo.setFrequencyId(_fb.getSelExtFrequencyId()[index]);
					vo.setDays(_fb.getSelExtDays()[index]);
					vo.setStartDate(_fb.getSelExtStartDay()[index]);
					vo.setRemarks(_fb.getSelExtInstructions()[index]);
					vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
					vo.setExtTreatmentId(_fb.getSelExtTreatmentId()[index]);
					vo.setExtTreatmentName(_fb.getSelExtTreatmentName()[index]);
					
					
					Boolean flag=false;
					//removing the blank row from lstExtTreatmentDtl bcz user can fill or not fill the last row
					Iterator<PatExtTreatmentDetailVO> removeBlankRowExtItr=lstExtTreatmentDtl.iterator();
					while(removeBlankRowExtItr.hasNext())
					{
						PatExtTreatmentDetailVO patExtTreatmentDetailVO=removeBlankRowExtItr.next();
						if(patExtTreatmentDetailVO.getDays().equals(""))
						{
							removeBlankRowExtItr.remove();
							flag=true;
						}
					}
					if(flag)
					{
						lstExtTreatmentDtl.add(vo);
					}
					
					//lstDrugTreatmentDtl.add(vo);
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

	// Adding New External  Treatment Row
	public static void addNewExtTretmentRow(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		
		List<PatExtTreatmentDetailVO> lstExtTreatmentDtl = null;
		try
		{
			setSysdate(_rq);
			
			// Setting Entered and New Row
			lstExtTreatmentDtl= new ArrayList<PatExtTreatmentDetailVO>();;
			int rows = Integer.parseInt(_fb.getExtDrugDetailRows());
			for(int i=0;i<rows;i++)
			{
				PatExtTreatmentDetailVO vo = new PatExtTreatmentDetailVO();
				
				//vo.setDoseId(_fb.getSelExtDoseId()[i]);
				//vo.setDoseName(_fb.getSelExtDoseName()[i]);
				vo.setFrequencyId(_fb.getSelExtFrequencyId()[i]);
				vo.setDays(_fb.getSelExtDays()[i]);
				vo.setStartDate(_fb.getSelExtStartDay()[i]);
				vo.setRemarks(_fb.getSelExtInstructions()[i]);
				vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				vo.setExtTreatmentId(_fb.getSelExtTreatmentId()[i]);
				vo.setExtTreatmentName(_fb.getSelExtTreatmentName()[i]);
				lstExtTreatmentDtl.add(vo);
			}			
			lstExtTreatmentDtl.add(_fb.resetExtTreatmentVO(new PatExtTreatmentDetailVO()));
			_fb.setExtDrugDetailRows(Integer.toString(rows+1)) ;
			_fb.setActiveTab("tabLAAdvice");
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);
			
			/*
			// Saving last row of drug advice
			HttpSession session=WebUTIL.getSession(_rq);
			int index=0;
			
			List lstDrugTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);
			int count=0;
			for(int i=0;i<lstDrugTreatmentDtl.size();i++)
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=(PatDrugTreatmentDetailVO)lstDrugTreatmentDtl.get(i);
				if(!patDrugTreatmentDetailVO.getRxContinueFlag().equals("modify"))
				{
					count++;
				}
				
			}
			
			if(count>1)
			{
				//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
				Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
				while(removeBlankRowItr.hasNext())
				{
					PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
					if(patDrugTreatmentDetailVO.getDays().equals(""))
					{
						removeBlankRowItr.remove();
						Integer drugRowsLength = Integer.parseInt(_fb.getDrugDetailRows())-1;
						_fb.setDrugDetailRows(drugRowsLength.toString());
						index=drugRowsLength;
					}
				}
			}
			
			
			int drugRowsLength = Integer.parseInt(_fb.getDrugDetailRows());
			if(index==0)
			{
				index=drugRowsLength-1;
			}
			
			String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
			
			Boolean flag=false;
			
			for(int i=0;i<lstDrugTreatmentDtl.size();i++)
			{
				PatDrugTreatmentDetailVO treatVO=(PatDrugTreatmentDetailVO)lstDrugTreatmentDtl.get(i);
				if(_fb.getSelDrugId()[index].equals(treatVO.getDrugId().split("#")[0]))
				{
					flag=true;
				}
			}
			
			
			if(!_fb.getSelDrugId()[index].equals("0 ") && !(flag))
			{
				//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
				Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
				while(removeBlankRowItr.hasNext())
				{
					PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
					if(patDrugTreatmentDetailVO.getDays().equals(""))
					{
						removeBlankRowItr.remove();
					}
				}
				
				PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
				vo.setDrugId(_fb.getSelDrugId()[index]+"#"+_fb.getSelDrugItemTypeId()[index]);
				vo.setDrugName(_fb.getSelDrugName()[index]);
				vo.setDoseId(_fb.getSelDoseId()[index]);
				vo.setDoseName(_fb.getSelDoseName()[index]);
				vo.setFrequencyId(_fb.getSelFrequencyId()[index]);
				vo.setDays(_fb.getSelDays()[index]);
				vo.setStartDate(_fb.getSelStartDay()[index]);
				vo.setRemarks(_fb.getSelInstructions()[index]);
				vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
				vo.setIsEmptyStomach(isEmptyStomach[index]);
				vo.setDrugRouteId(_fb.getSelDrugRouteId()[index]);
				vo.setDrugRouteName(_fb.getSelDrugRouteName()[index]);
				vo.setQuantity(_fb.getQuantity()[index]);
				lstDrugTreatmentDtl.add(vo);
				
				//lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
			//	_fb.setDrugDetailRows(Integer.toString(drugRowsLength+1)) ;
								
				WebUTIL.setAttributeInSession(_rq, OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			}
			
			*/
			HttpSession session=WebUTIL.getSession(_rq);
			List lstDrugTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);
			 
			 int drugRowsLength = Integer.parseInt(_fb.getDrugDetailRows());
			 int index=drugRowsLength-1;
			 String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
			 
			// if(!_fb.getSelDrugId()[index].equals("0 ") && Integer.parseInt(_fb.getIndexDelete())!=index)
			 if(!_fb.getSelDrugId()[index].equals("0 "))
			 {
				 	PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
					vo.setDrugId(_fb.getSelDrugId()[index]+"#"+_fb.getSelDrugItemTypeId()[index]);
					vo.setDrugName(_fb.getSelDrugName()[index]);
					vo.setDoseId(_fb.getSelDoseId()[index]);
					vo.setDoseName(_fb.getSelDoseName()[index]);
					vo.setFrequencyId(_fb.getSelFrequencyId()[index]);
					vo.setDays(_fb.getSelDays()[index]);
					vo.setStartDate(_fb.getSelStartDay()[index]);
					vo.setRemarks(_fb.getSelInstructions()[index]);
					vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
					vo.setIsEmptyStomach(isEmptyStomach[index]);
					vo.setDrugRouteId(_fb.getSelDrugRouteId()[index]);
					vo.setDrugRouteName(_fb.getSelDrugRouteName()[index]);
					vo.setQuantity(_fb.getQuantity()[index]);
					vo.setDrugBrandId(_fb.getSelDrugBrandId()[index]);
					
					Boolean flag=false;
					//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
					Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
					while(removeBlankRowItr.hasNext())
					{
						PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
						if(patDrugTreatmentDetailVO.getDays().equals(""))
						{
							removeBlankRowItr.remove();
							flag=true;
							//lstDrugTreatmentDtl.add(vo);
						}
					}
					if(flag)
					{
						lstDrugTreatmentDtl.add(vo);
					}
					
					//lstDrugTreatmentDtl.add(vo);
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
	public static void searchDrug(PatientTreatmentDetailFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<Entry> lstDrugsMain = null;
		List<Entry> lstDrugs = null;
		HttpSession session = _rq.getSession();
		try
		{
			
			
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			//lstDrugsMain = (ArrayList<Entry>)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			//lstDrugsMain = (ArrayList<Entry>)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS_FOR_SEARCH);
			lstDrugsMain = (ArrayList<Entry>)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
		
			
			String searchDrugName = fb.getSearchDrugName();
			String searchDrugItemTypeId = fb.getSearchDrugItemTypeId();
			
			if(searchDrugName!=null && !searchDrugName.trim().equals(""))
			{
				lstDrugs = new ArrayList<Entry>();
				for(Entry e : lstDrugsMain)
					if(e.getLabel().toUpperCase().startsWith(searchDrugName.trim().toUpperCase()))
						lstDrugs.add(e);
			}
			else if(searchDrugItemTypeId!=null && !searchDrugItemTypeId.trim().equals(""))
			{
				lstDrugs = new ArrayList<Entry>();
				for(Entry e : lstDrugsMain)
					if(e.getValue().split("#")[1].equals(searchDrugItemTypeId))
						lstDrugs.add(e);
			}
				
			if (lstDrugs != null && lstDrugs.size()<=0)
				throw new HisRecordNotFoundException("No Drug Exists");
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.PAT_TREATMENT_DTL_SERACH_DRUG_DETAIL_LIST, lstDrugs);
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

	public static void populateSearchedDrug(PatientTreatmentDetailFB _fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		List<Entry> lstDrugs = null;
		try
		{
			lstDrugs = (List<Entry>) WebUTIL.getSession(_rq).getAttribute(OpdConfig.PAT_TREATMENT_DTL_SERACH_DRUG_DETAIL_LIST);

			Entry ent = lstDrugs.get(Integer.parseInt(_fb.getSelectedDrugIndex()));
	
			String label = ent.getLabel();
			String value = (ent.getValue()).trim();
			PrintWriter writer = _resp.getWriter();
			writer.write(label + '^' + value);
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
	
	
	public static String getDoseQty(String doseQty ,String isFrequencyBound , String days,String perDayDoseQty)
	{
		Status objStatus =new Status();
		Double finalQty= 0.0;
		try
		{
			if(doseQty!=null && doseQty!="" && isFrequencyBound!=null && isFrequencyBound!="" && days!=null && days!="")
			{
				if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
				{
					finalQty=Double.parseDouble(perDayDoseQty)*Integer.parseInt(days);
				}
				else
				{
					finalQty=Double.parseDouble(perDayDoseQty)*1*1; 
				}
			}
			else
			{
				finalQty=0.0;
			}
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		return finalQty.toString();
	}

	// Saving Treatment Detail 
	
	public static boolean save(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;		
		Status objStatus = new Status();
		
		setSysdate(_rq);
		HttpSession session=WebUTIL.getSession(_rq);
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
		String sysadateForTodayVisit=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
		
		List<PatDrugTreatmentDetailVO> lstDrugTreatmentDtl = null;
		
		try
		{
			UserVO userVO = getUserVO(_rq);
						
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			//Date sysDate=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd/MMM/yyyy"));
			Date sysDate=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(sysDate);
			
	/****************************saving drug detail**********************************/		
			// Saving Drug Detail
			List drugDoseVOLst=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			DrugFrequencyMstVO[] drugFreqMstVoArray=(DrugFrequencyMstVO[])session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			String doseQty="";
			String isFrequencyBound="";
			String freqValue="";
			Double totalPerDayDoseQty=0.0;
			lstDrugTreatmentDtl= new ArrayList<PatDrugTreatmentDetailVO>();
			
			String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
			for(int i=0;i<Integer.parseInt(_fb.getDrugDetailRows());i++)
			{
				PatDrugTreatmentDetailVO drugVO = new PatDrugTreatmentDetailVO();
				
				// Checking for Empty Drug Row 
				if(_fb.getSelDrugId()[i]==null || _fb.getSelDrugId()[i].trim().equals("") || _fb.getSelDrugName()[i]==null || _fb.getSelDrugName()[i].trim().equals(""))
					continue;
				
				Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
				if(drugSchedule==null)
				{
					drugSchedule=new HashMap();
				}
				//this is the case when we enter drug schedule manually(by schedule popup)
				//if(drugSchedule.containsKey(_fb.getSelDrugId()[i]))
				if(drugSchedule.containsKey(_fb.getSelDrugId()[i]+"#"+_fb.getSelFrequencyId()[i]+"#"+_fb.getSelDoseId()[i]))
				{
					
					List drugScheduleLst=(List)drugSchedule.get(_fb.getSelDrugId()[i]+"#"+_fb.getSelFrequencyId()[i]+"#"+_fb.getSelDoseId()[i]);
					
					 doseQty="0";
					 isFrequencyBound="";
					 freqValue="";
					 totalPerDayDoseQty=0.0;
					//getting frequency value from frequency master for caculating dose qty
					 if(drugFreqMstVoArray!=null)
					 {
					for(int j=0;j<drugFreqMstVoArray.length;j++)
					{
						DrugFrequencyMstVO vo=drugFreqMstVoArray[j];
						if(_fb.getSelFrequencyId()[i].equals(vo.getFrequencyId()))
						{
							freqValue=vo.getFrequency();
						}
					}
					}
					
					//String lstSize=drugScheduleLst.size();
					if(Integer.parseInt(freqValue)!=(drugScheduleLst.size()))
					{
						drugSchedule.remove(_fb.getSelDrugId()[i]);
						drugScheduleLst.clear();
					}
					
					 
					for(int k=0;k<drugScheduleLst.size();k++)
					{
						DrugSheduleDtlVO drugScheduleVO=(DrugSheduleDtlVO)drugScheduleLst.get(k);
						
						/*
						//getting frequency value from frequency master for caculating dose qty
						for(int j=0;j<drugFreqMstVoArray.length;j++)
						{
							DrugFrequencyMstVO vo=drugFreqMstVoArray[j];
							if(_fb.getSelFrequencyId()[i].equals(vo.getFrequencyId()))
							{
								freqValue=vo.getFrequency();
								
								if(freqValue.equals("0"))
								{
									freqValue="1";
								}
							}
						}
						*/
						//checking isFrequency bound
						Iterator itrIsFreqBoundForSched=drugDoseVOLst.iterator();
						while(itrIsFreqBoundForSched.hasNext())
						{
							DrugDoseVO vo=(DrugDoseVO)itrIsFreqBoundForSched.next();
							if(drugScheduleVO.getDoseId().equals(vo.getDoseId()))
							{
								isFrequencyBound=vo.getIsFrequencyBound();
							}
						}
						
						// getting doseQty 
						Iterator itrForDoseQtySched = drugDoseVOLst.iterator();
						boolean flgSetDoseQtByMasterEntry = false; 
						while(itrForDoseQtySched.hasNext())
						{
							DrugDoseVO vo=(DrugDoseVO)itrForDoseQtySched.next();
							if(drugScheduleVO.getDoseId().equals(vo.getDoseId()))
							{
								doseQty=vo.getDoseQty();
								flgSetDoseQtByMasterEntry = true;
								break;
							}
						}
						if(flgSetDoseQtByMasterEntry)
						{
							try
							{
								double qty = Double.parseDouble(drugScheduleVO.getDoseName().trim());
								doseQty = drugScheduleVO.getDoseName().trim();
							}
							catch(Exception e)
							{
								doseQty = "1";
							}
						}
						
						if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
						{
							totalPerDayDoseQty=totalPerDayDoseQty+Double.parseDouble(doseQty);
						}
						else
						{
							/*
							if(totalPerDayDoseQty<Integer.parseInt(doseQty))
							{
								totalPerDayDoseQty=Integer.parseInt(doseQty);
							}
							else
							{
								totalPerDayDoseQty=totalPerDayDoseQty;
							}
							*/
							totalPerDayDoseQty=Double.parseDouble(_fb.getQuantity()[i]);
							
						}
						
						
					}
				}
				//this is the case when drug schedule take default value
				if(!drugSchedule.containsKey(_fb.getSelDrugId()[i]+"#"+_fb.getSelFrequencyId()[i]+"#"+_fb.getSelDoseId()[i]))
				{
					 doseQty="0";
					 isFrequencyBound="1";
					 freqValue="";
					 totalPerDayDoseQty=0.0;
					
					
					 // In case dose in custom,  frequency bound by default if 1(yes) 
					 if(!_fb.getSelDoseId()[i].split("\\^")[0].equals("0"))
					 {
						 isFrequencyBound=_fb.getSelDoseId()[i].split("\\^")[1];
					 	 doseQty=(isFrequencyBound.equals("0"))?"0":_fb.getSelDoseId()[i].split("\\^")[2];
					 }
					if(doseQty==null || doseQty.trim().equals("") || doseQty.trim().equals("0"))
					{
						try
						{
							double qty = Double.parseDouble(_fb.getSelDoseName()[i].trim());
							doseQty = _fb.getSelDoseName()[i].trim();
						}
						catch(Exception e)
						{
							doseQty = "1";
						}
					}

					 
					 /*
					//for getting dose qty and isfrequecybound from drug dose master  
					Iterator itrForIsFreqBound=drugDoseVOLst.iterator();
					while(itrForIsFreqBound.hasNext())
					{
						DrugDoseVO vo=(DrugDoseVO)itrForIsFreqBound.next();
						if(_fb.getSelDrugItemTypeId()[i].equals(vo.getItemTypeId()))
						{
							isFrequencyBound=vo.getIsFrequencyBound();
						}
					}
					// getting doseQty 
					Iterator itrForDoseQty = drugDoseVOLst.iterator();
					while(itrForDoseQty.hasNext())
					{
						DrugDoseVO vo=(DrugDoseVO)itrForDoseQty.next();
						if(_fb.getSelDoseId()[i].equals(vo.getDoseId()))
						{
							doseQty=vo.getDoseQty();
						}
					}
					*/
					//getting frequency value from frequency master for caculating dose qty
					if(drugFreqMstVoArray !=null)
					{
						
					for(int j=0;j<drugFreqMstVoArray.length;j++)
					{
						DrugFrequencyMstVO vo=drugFreqMstVoArray[j];
						if(_fb.getSelFrequencyId()[i].equals(vo.getFrequencyId()))
						{
							freqValue=vo.getFrequency();
							if(freqValue==null || freqValue.equals("0") || freqValue.isEmpty())
							{
								freqValue="1";
							}
						}
					}
				}
					
					if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
					{
						totalPerDayDoseQty=Double.parseDouble(doseQty)*Integer.parseInt(freqValue);
					}
					else
					{
						/*
						if(totalPerDayDoseQty<Integer.parseInt(doseQty))
						{
							totalPerDayDoseQty=Integer.parseInt(doseQty);
						}
						else
						{
							totalPerDayDoseQty=totalPerDayDoseQty;
						}
						*/
						if(_fb.getQuantity()[i].equals(""))
						{
							totalPerDayDoseQty=0.0;
						}
						else
						{
							totalPerDayDoseQty=Double.parseDouble(_fb.getQuantity()[i]);
						}
					}
				}
				
				
				
							
				//getting dose qty
				String finalDoseqty=getDoseQty(doseQty, isFrequencyBound,  _fb.getSelDays()[i],totalPerDayDoseQty.toString());
				
				drugVO.setDoseQty(finalDoseqty);
				
				//because for new record requiredQty=doseQty
				drugVO.setRequiredQty(finalDoseqty);
				drugVO.setIssueQty("0");
				
				drugVO.setDrugId(_fb.getSelDrugId()[i]);
				drugVO.setDrugName(_fb.getSelDrugName()[i]);
				drugVO.setDoseId(_fb.getSelDoseId()[i].split("\\^")[0]);
				drugVO.setDoseName(_fb.getSelDoseName()[i]);
				drugVO.setFrequencyId(_fb.getSelFrequencyId()[i]);
				drugVO.setDays(_fb.getSelDays()[i]);
				drugVO.setStartDate(_fb.getSelStartDay()[i]);
				drugVO.setRemarks(_fb.getSelInstructions()[i]);
				drugVO.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				drugVO.setEndDate(_fb.getEndDate()[i]);
				//setting next visit
				drugVO.setEpisodeCode(_fb.getEpisodeCode());
				drugVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				drugVO.setPatCrNo(_fb.getPatCrNo());
				drugVO.setAdmissionNo(_fb.getAdmissionNo());
				drugVO.setSysDate(sysadteString);
			//	drugVO.setIsEmptyStomach(isEmptyStomach[i]);  //isEMptyStomach checkbox is removed
				drugVO.setDrugRouteId(_fb.getSelDrugRouteId()[i]);
				drugVO.setRevokeStatus("false");
				drugVO.setDrugAdminId(_fb.getSelDrugAdminId()[i]);
				drugVO.setDrugAdminName(_fb.getSelDrugAdminName()[i]);
				drugVO.setDrugBrandId(_fb.getSelDrugBrandId()[i]);
				drugVO.setDrugTypeID(_fb.getSelDrugItemTypeId()[i]);
				
				//This is for modify or drug change case
				if(_fb.getRxContinueFlag()[i].equals("modify"))
				{
					/*
					//setting startDate
					drugVO.setStartDate(_fb.getChangeStartDate()[i]);
					
					//setting end date
					String endDays=_fb.getSelDays()[i];
					String startDays=_fb.getSelStartDay()[i];
					
					String systemDate= WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					
					Calendar c1 = Calendar.getInstance();
					c1.setTime(sdf.parse(systemDate));
					c1.add(Calendar.DATE, (Integer.parseInt(startDays))); 
					String setStartDate = sdf.format(c1.getTime());
					
					
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(setStartDate));
					c.add(Calendar.DATE, (Integer.parseInt(endDays)-1)); 
					String setEndDate = sdf.format(c.getTime());
					
					drugVO.setEndDate(setEndDate);
					
					*/
					
					
					drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
					//boolean scheduleNotPresent=false;
					
					if(drugSchedule.containsKey(_fb.getSelDrugId()[i]))
					{
						List drugScgeduleVoLst=(List)drugSchedule.get(_fb.getSelDrugId()[i]);
						
						 doseQty="0";
						 isFrequencyBound="";
						 freqValue="";
						 totalPerDayDoseQty=0.0;
						 
						for(int k=0;k<drugScgeduleVoLst.size();k++)
						{
							DrugSheduleDtlVO drugScheduleVO=(DrugSheduleDtlVO)drugScgeduleVoLst.get(k);
							//scheduleNotPresent=true;					
							//checking isFrequency bound
							Iterator itrIsFreqBoundForSched=drugDoseVOLst.iterator();
							while(itrIsFreqBoundForSched.hasNext())
							{
								DrugDoseVO vo=(DrugDoseVO)itrIsFreqBoundForSched.next();
								if(drugScheduleVO.getDoseId().equals(vo.getDoseId()))
								{
									isFrequencyBound=vo.getIsFrequencyBound();
								}
							}
							
							// getting doseQty 
							Iterator itrForDoseQtySched = drugDoseVOLst.iterator();
							while(itrForDoseQtySched.hasNext())
							{
								DrugDoseVO vo=(DrugDoseVO)itrForDoseQtySched.next();
								if(drugScheduleVO.getDoseId().equals(vo.getDoseId()))
								{
									doseQty=vo.getDoseQty();
								}
							}
							
							//getting frequency value from frequency master for caculating dose qty
							for(int j=0;j<drugFreqMstVoArray.length;j++)
							{
								DrugFrequencyMstVO vo=drugFreqMstVoArray[j];
								if(_fb.getSelFrequencyId()[i].equals(vo.getFrequencyId()))
								{
									freqValue=vo.getFrequency();
									if(freqValue.equals("0"))
									{
										freqValue="1";
									}
								}
							}
							
							if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
							{
								totalPerDayDoseQty=totalPerDayDoseQty.parseDouble(doseQty);
							}
							else
							{
								/*
								if(totalPerDayDoseQty<Integer.parseInt(doseQty))
								{
									totalPerDayDoseQty=Integer.parseInt(doseQty);
								}
								else
								{
									totalPerDayDoseQty=totalPerDayDoseQty;
								}
								*/
								if(_fb.getQuantity()[i].equals(""))
								{
									totalPerDayDoseQty=0.0;
								}
								else
								{
									totalPerDayDoseQty=Double.parseDouble(_fb.getQuantity()[i]);
								}
								
							}
						}
					}
					/*
					//this is the SOS case
					if(!scheduleNotPresent)
					{
										
						Iterator itrForNoSchedChange=drugDoseVOLst.iterator();
						while(itrForNoSchedChange.hasNext())
						{
							DrugDoseVO vo=(DrugDoseVO)itrForNoSchedChange.next();
							if(_fb.getSelDoseId()[i].equals(vo.getDoseId()))
							{
								isFrequencyBound=vo.getIsFrequencyBound();
							}
						}
						// getting doseQty 
						Iterator itrForDoseQtyChange = drugDoseVOLst.iterator();
						while(itrForDoseQtyChange.hasNext())
						{
							DrugDoseVO vo=(DrugDoseVO)itrForDoseQtyChange.next();
							if(_fb.getSelDoseId()[i].equals(vo.getDoseId()))
							{
								doseQty=vo.getDoseQty();
							}
						}
						
						totalPerDayDoseQty=Integer.parseInt(doseQty);
						
					}
					
					*/
					drugVO.setRxContinue(OpdConfig.CHANGE_IN_PREV);
					
					drugVO.setTodayVisitFlag(_fb.getTodayVisitFlag()[i]);
					drugVO.setSerialNo(_fb.getSerealNo()[i]);
					drugVO.setEntryDate(_fb.getPrevEntryDate()[i]);
					
					if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
					{
						Double totalPrevIssue=(Double.parseDouble(_fb.getPrevDoseQty()[i])-Double.parseDouble(_fb.getPrevRequiredQty()[i])) + Double.parseDouble(_fb.getPrevIssueQty()[i]);
						Date endDate=df.parse(_fb.getPrevEndDate()[i]);
						if(sysdate.before(endDate)|| sysdate.equals(endDate))
						{
							Calendar calendar2=Calendar.getInstance();
							Calendar calendar3=Calendar.getInstance();
							
							calendar2.setTime(df.parse(_fb.getPrevStartDate()[i]));
							calendar3.setTime(df.parse(sysadateForTodayVisit));


							long noOfDays=((calendar3.getTime().getTime() - calendar2.getTime().getTime()) / (24 * 3600 * 1000))+1;
							String consumeDays=String.valueOf(noOfDays);
												
							double consumeQty=Double.parseDouble(consumeDays)*totalPerDayDoseQty;
							Double remainingIssueQty=totalPrevIssue-consumeQty;
							if(remainingIssueQty<0)
							{
								remainingIssueQty=0.0;
							}
							Double requiredQty=Double.parseDouble(finalDoseqty)-remainingIssueQty;
							if(requiredQty<0)
							{
								requiredQty=0.0;
							} 
							
							drugVO.setRequiredQty(requiredQty.toString());
						}
					}
					else
					{
						drugVO.setRequiredQty(totalPerDayDoseQty.toString());
					}


				}
				else
				{
					drugVO.setRxContinue(OpdConfig.NEW_RECORD);
				}
								
				lstDrugTreatmentDtl.add(drugVO);
			}
			
			//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
			Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
			while(removeBlankRowItr.hasNext())
			{
				PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
				if(patDrugTreatmentDetailVO.getDays().equals("")) // here removing those drug rows which are empty 
				{
					removeBlankRowItr.remove();
				}
			}
			
			//saving drug Schedule
			Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if(drugSchedule==null)
			{
				drugSchedule=new HashMap();
			}
			
			//Getting Default Schedule for particular Drug
			
			DrugFrequencyMstVO[] arr = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			DrugFrequencyMstVO drugFreqVO = null;
			
			PatDrugTreatmentDetailVO drugTreatDtlVO = new PatDrugTreatmentDetailVO();
			
			Iterator drugScheduleItr=lstDrugTreatmentDtl.iterator();
			while(drugScheduleItr.hasNext())
			{
				drugTreatDtlVO=(PatDrugTreatmentDetailVO)drugScheduleItr.next();
				if(!drugSchedule.containsKey(drugTreatDtlVO.getDrugId() +"#"+ drugTreatDtlVO.getFrequencyId()+"#"+drugTreatDtlVO.getDoseId()))
				{
					List scheduleLst=new ArrayList();
					for(int i=0;i<arr.length;i++)
					{
						if(arr[i].getFrequencyId().equals(drugTreatDtlVO.getFrequencyId()))
						{
							drugFreqVO=arr[i];
							break;
						}
					}
					/////////////////////////////////////////////////////////
					
					 
					////////////////////////////////////////////////////////
					
				/*	if(drugFreqVO.getMorDoseTime()!=null)
					{

						DrugSheduleDtlVO drugSchduleVO=new DrugSheduleDtlVO();
						drugSchduleVO.setDoseShift(OpdConfig.MORNING_SHIFT_ID);
						drugSchduleVO.setDoseTime(_fb.getDrugRequirmentTimeHrs());
						drugSchduleVO.setPopupDrugId(drugTreatDtlVO.getDrugId());
						drugSchduleVO.setDoseId(drugTreatDtlVO.getDoseId());
						drugSchduleVO.setDoseName(drugTreatDtlVO.getDoseName());
						drugSchduleVO.setIsEmptyStomach(drugTreatDtlVO.getIsEmptyStomach());
						drugSchduleVO.setCutOffTime(drugTreatDtlVO.getCutOffBefore());
						drugSchduleVO.setCutOffTimeafter(drugTreatDtlVO.getCutOffAfter());
						/*
						List drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
							
						 for(int k=0;k<drugScheduleVOLst.size();k++)
						 {
							 DrugSheduleDtlVO scheduleVo=(DrugSheduleDtlVO)drugScheduleVOLst.get(k);
							 if(scheduleVo.getItemTypeId().equals(drugTreatDtlVO.getDrugId()))
							 {
								 
							 }
						 }
						 */
						
						/* drugSchduleVO.setTodayVisitFlag(drugTreatDtlVO.getTodayVisitFlag());
						scheduleLst.add(drugSchduleVO);
						
					}
					if(drugFreqVO.getAftDoseTime()!=null)
					{

						DrugSheduleDtlVO drugSchduleVO=new DrugSheduleDtlVO();
						drugSchduleVO.setDoseShift(OpdConfig.NOON_SHIFT_ID);
						drugSchduleVO.setDoseTime(drugFreqVO.getAftDoseTime());
						drugSchduleVO.setPopupDrugId(drugTreatDtlVO.getDrugId());
						drugSchduleVO.setDoseId(drugTreatDtlVO.getDoseId());
						drugSchduleVO.setDoseName(drugTreatDtlVO.getDoseName());
						drugSchduleVO.setIsEmptyStomach(drugTreatDtlVO.getIsEmptyStomach());
						drugSchduleVO.setTodayVisitFlag(drugTreatDtlVO.getTodayVisitFlag());
						drugSchduleVO.setCutOffTime(drugTreatDtlVO.getCutOffBefore());
						drugSchduleVO.setCutOffTimeafter(drugTreatDtlVO.getCutOffAfter());
						scheduleLst.add(drugSchduleVO);
					}
					if(drugFreqVO.getEveDoseTime()!=null)
					{

						DrugSheduleDtlVO drugSchduleVO=new DrugSheduleDtlVO();
						drugSchduleVO.setDoseShift(OpdConfig.EVENING_SHIFT_ID);
						drugSchduleVO.setDoseTime(drugFreqVO.getEveDoseTime());
						drugSchduleVO.setPopupDrugId(drugTreatDtlVO.getDrugId());
						drugSchduleVO.setDoseId(drugTreatDtlVO.getDoseId());
						drugSchduleVO.setDoseName(drugTreatDtlVO.getDoseName());
						drugSchduleVO.setIsEmptyStomach(drugTreatDtlVO.getIsEmptyStomach());
						drugSchduleVO.setTodayVisitFlag(drugTreatDtlVO.getTodayVisitFlag());
						drugSchduleVO.setCutOffTime(drugTreatDtlVO.getCutOffBefore());
						drugSchduleVO.setCutOffTimeafter(drugTreatDtlVO.getCutOffAfter());
						scheduleLst.add(drugSchduleVO);
					}
					if(drugFreqVO.getNgtDoseTime()!=null)
					{

						DrugSheduleDtlVO drugSchduleVO=new DrugSheduleDtlVO();
						drugSchduleVO.setDoseShift(OpdConfig.NIGHT_SHIFT_ID);
						drugSchduleVO.setDoseTime(drugFreqVO.getNgtDoseTime());
						drugSchduleVO.setPopupDrugId(drugTreatDtlVO.getDrugId());
						drugSchduleVO.setDoseId(drugTreatDtlVO.getDoseId());
						drugSchduleVO.setDoseName(drugTreatDtlVO.getDoseName());
						drugSchduleVO.setIsEmptyStomach(drugTreatDtlVO.getIsEmptyStomach());
						drugSchduleVO.setTodayVisitFlag(drugTreatDtlVO.getTodayVisitFlag());
						drugSchduleVO.setCutOffTime(drugTreatDtlVO.getCutOffBefore());
						drugSchduleVO.setCutOffTimeafter(drugTreatDtlVO.getCutOffAfter());
						scheduleLst.add(drugSchduleVO);
					}
				*/	
			int freqCount=Integer.parseInt(freqValue);
			String doseName="";
			for(int i=0;i<freqCount;i++)
			{
				String scheduleSlNo=new Integer(i).toString();
				String hr="",min="";
				if(drugFreqVO.getFirstDoseTime()!=null)
				{
				String[] dosetime=drugFreqVO.getFirstDoseTime().split(":");
				String[] durationtime=drugFreqVO.getDoseDuration().split(":");
				int dosetimehr=Integer.parseInt(dosetime[0])+Integer.parseInt(durationtime[0])*i;
				int dosetimemin=Integer.parseInt(dosetime[1])+Integer.parseInt(durationtime[1])*i;
				
				if(dosetimehr<10)
					hr="0"+Integer.toString(dosetimehr);
				else if(dosetimehr>=24) //Added by Vasu on 08.Oct.2018 for QID drug frequency
				{
					hr = "23";
					dosetimemin = 59;
				}
				else
					hr=Integer.toString(dosetimehr);
				if(dosetimemin<10)
					min="0"+Integer.toString(dosetimemin);
				else
					min=Integer.toString(dosetimemin);
				}
				DrugSheduleDtlVO drugSchduleVO=new DrugSheduleDtlVO();
				
				drugSchduleVO.setDoseName(drugTreatDtlVO.getDoseName());	
				drugSchduleVO.setDoseId(drugTreatDtlVO.getDoseId());
				drugSchduleVO.setDoseShift("");
				drugSchduleVO.setDoseTime(hr+":"+min);
				drugSchduleVO.setIsEmptyStomach(drugTreatDtlVO.getIsEmptyStomach());
				drugSchduleVO.setTodayVisitFlag(drugTreatDtlVO.getTodayVisitFlag());
				drugSchduleVO.setPopupDrugId(drugTreatDtlVO.getDrugId());
				//drugSchduleVO.setPopupRowIndex(_fb.getRemarks()[i]);
				drugSchduleVO.setCutOffTime(drugFreqVO.getCutOffTime());
				drugSchduleVO.setCutOffTimeafter(drugFreqVO.getCutOffTime());
				scheduleLst.add(drugSchduleVO);
			}
					//String mapKey=drugTreatDtlVO.getDrugId()+"#"+drugTreatDtlVO.getFrequencyId()+"#"+drugTreatDtlVO.getDoseId();
					
					drugSchedule.put(drugTreatDtlVO.getDrugId()+"#"+drugTreatDtlVO.getFrequencyId()+"#"+drugTreatDtlVO.getDoseId(), scheduleLst);
					
				}
				
		}
			
			
			
			//saving revoke List for drug detail
			List prevPatDrugTreatmentDtlVOList=new ArrayList();
			String[] revoke=_fb.getRevoke();
			if(revoke!=null)
			{
				prevPatDrugTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
	 			  
				Iterator<PatDrugTreatmentDetailVO> totalRevokeItr = prevPatDrugTreatmentDtlVOList.iterator();
				 while(totalRevokeItr.hasNext())
				 {
					 PatDrugTreatmentDetailVO patDrugTreatmentDetailVO =totalRevokeItr.next();
					 for(int i=0;i<revoke.length;i++)
					 {
						 String drugId=revoke[i].split("\\$")[0];
						// String index=revoke[i].split("\\$")[1];
						 String frequencyId=revoke[i].split("\\$")[2];
						 String doseId=revoke[i].split("\\$")[3];
						 
						 if(patDrugTreatmentDetailVO.getDrugId().equals(drugId))// && patDrugTreatmentDetailVO.getFrequencyId().equals(frequencyId) && patDrugTreatmentDetailVO.getDoseId().equals(doseId))
						 {
							 
							 patDrugTreatmentDetailVO.setRxContinue(OpdConfig.REVOKE);
														 
							//setting episode code,visit number,admission Number etc
							patDrugTreatmentDetailVO.setEpisodeCode(_fb.getEpisodeCode());
							patDrugTreatmentDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
							patDrugTreatmentDetailVO.setPatCrNo(_fb.getPatCrNo());
							patDrugTreatmentDetailVO.setAdmissionNo(_fb.getAdmissionNo());
							
							patDrugTreatmentDetailVO.setSysDate(sysadteString);
							patDrugTreatmentDetailVO.setRevokeStatus("revoke");
							
							//here we checking that this record is today visit record 
							if(patDrugTreatmentDetailVO.getEntryDate().equals(sysadateForTodayVisit))
							{
								patDrugTreatmentDetailVO.setTodayVisitFlag("yes");
							}
							else
							{
								patDrugTreatmentDetailVO.setTodayVisitFlag("no");
							}
							
							//here we set the requiredQty and IssueQty
							patDrugTreatmentDetailVO.setRequiredQty("0");
							patDrugTreatmentDetailVO.setIssueQty("0");
							
							
							//Saving Revoke Remarks By Pawan Kumar B N on 19-Feb-2013
							patDrugTreatmentDetailVO.setRevokeRemarks(_fb.getRevokeRemarks()[i]);
							
							
							lstDrugTreatmentDtl.add(patDrugTreatmentDetailVO);
							//totalRevokeItr.remove();
						 }
					 }
					
				 }
			}
			
			/*
			//saving Extension List for drug detail
			List prevPatDrugTreatDtlVOLst=new ArrayList();
			String[] extension=_fb.getExtension();
			List extendedDrugIdLst=new ArrayList();
			if(extension!=null)
			{
				prevPatDrugTreatDtlVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
	 			  
				Iterator<PatDrugTreatmentDetailVO> extensionItr = prevPatDrugTreatDtlVOLst.iterator();
				 int j=0;
				 int index=0;
				 while(extensionItr.hasNext())
				 {
					 PatDrugTreatmentDetailVO extendedVO =extensionItr.next();
					 //we dont want to change reflect in session
					 PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=new PatDrugTreatmentDetailVO();
					 HelperMethods.populate(patDrugTreatmentDetailVO, extendedVO);
					
					 for(int i=0;i<extension.length;i++)
					 {
						 String drugId=extension[i].split("\\$")[0];
						// String index=revoke[i].split("\\$")[1];
						 String frequencyId=extension[i].split("\\$")[2];
						 String doseId=extension[i].split("\\$")[3];
						 
						 extendedDrugIdLst.add(drugId+"#"+frequencyId+"#"+doseId);//this is for getting schedule for extended particular drug
						 if(patDrugTreatmentDetailVO.getDrugId().equals(drugId) && patDrugTreatmentDetailVO.getFrequencyId().equals(frequencyId) && patDrugTreatmentDetailVO.getDoseId().equals(doseId))
						 {
							 
							 Integer extendedDays=Integer.parseInt(_fb.getExtensionDays()[index]);
							 patDrugTreatmentDetailVO.setRxContinue(OpdConfig.RX_EXTENSION);
							 int days=Integer.parseInt(patDrugTreatmentDetailVO.getDays());
							 Integer totalDays=days+Integer.parseInt(_fb.getExtensionDays()[index]);
							 patDrugTreatmentDetailVO.setDays(totalDays.toString());
							 
							 String toDate=patDrugTreatmentDetailVO.getEndDate();
							 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
							 Calendar c = Calendar.getInstance();
							 c.setTime(sdf.parse(toDate));
							 c.add(Calendar.DATE, extendedDays); // Getting the End Date 
							 String endDate = sdf.format(c.getTime());
							 patDrugTreatmentDetailVO.setEndDate(endDate);
							 
							 
							//setting episode code,visit number,admission Number etc
							 String episodeVisitNo=patDrugTreatmentDetailVO.getEpisodeVisitNo();
							 
							patDrugTreatmentDetailVO.setEpisodeCode(_fb.getEpisodeCode());
							patDrugTreatmentDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
							patDrugTreatmentDetailVO.setPatCrNo(_fb.getPatCrNo());
							patDrugTreatmentDetailVO.setAdmissionNo(_fb.getAdmissionNo());
							
							patDrugTreatmentDetailVO.setSysDate(sysadteString);
							
							//here we checking that this record is today visit record 
							if(patDrugTreatmentDetailVO.getEntryDate().equals(sysadateForTodayVisit))
							{
								patDrugTreatmentDetailVO.setTodayVisitFlag("yes");
							}
							else
							{
								patDrugTreatmentDetailVO.setTodayVisitFlag("no");
							}
							
							
							
							//for getting dose qty and isfrequecybound from drug dose master
							doseQty="0";
							isFrequencyBound="";
							freqValue="";
							totalPerDayDoseQty=0;
							boolean scheduleNotPresent=false;		
							List drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
							for(int k=0;k<drugScheduleVOLst.size();k++)
							{
								DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(k);
								if(drugSheduleDtlVO.getItemTypeId().equals(drugId) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getEntryDate().equals(patDrugTreatmentDetailVO.getEntryDate()))
								{
									scheduleNotPresent=true; 									
									Iterator itrForIsFreqBound=drugDoseVOLst.iterator();
									while(itrForIsFreqBound.hasNext())
									{
										DrugDoseVO vo=(DrugDoseVO)itrForIsFreqBound.next();
										if(drugSheduleDtlVO.getDoseId().equals(vo.getDoseId()))
										{
											isFrequencyBound=vo.getIsFrequencyBound();
										}
									}
									
									Iterator itrForDoseQty = drugDoseVOLst.iterator();
									while(itrForDoseQty.hasNext())
									{
										DrugDoseVO vo=(DrugDoseVO)itrForDoseQty.next();
										if(drugSheduleDtlVO.getDoseId().equals(vo.getDoseId()))
										{
											doseQty=vo.getDoseQty();
										}
									}
									
									if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
									{
										totalPerDayDoseQty=totalPerDayDoseQty+Integer.parseInt(doseQty);
									}
									
									
								}
							}
							
							
							//getting dose qty and requiredQty
							int prevIssueQty=Integer.parseInt(patDrugTreatmentDetailVO.getIssueQty());
							int prevRequiredQty=Integer.parseInt(patDrugTreatmentDetailVO.getRequiredQty());
							Integer prevDoseQty=Integer.parseInt(patDrugTreatmentDetailVO.getDoseQty());
													
							if(isFrequencyBound.equals(OpdConfig.IS_FREQUENCY_BOUND))
							{
								String finalDoseqty=getDoseQty(doseQty, isFrequencyBound, totalDays.toString(),totalPerDayDoseQty.toString());
								Integer requireQty= (Integer.parseInt(finalDoseqty) - prevDoseQty) + prevRequiredQty - prevIssueQty ;
								
								patDrugTreatmentDetailVO.setDoseQty(finalDoseqty);
								patDrugTreatmentDetailVO.setRequiredQty(requireQty.toString());
								
							}
							else
							{
								Integer requireQty=prevRequiredQty-prevIssueQty;
								patDrugTreatmentDetailVO.setDoseQty(prevDoseQty.toString());
								patDrugTreatmentDetailVO.setRequiredQty(requireQty.toString());
							}
							
							patDrugTreatmentDetailVO.setIssueQty("0");
							lstDrugTreatmentDtl.add(patDrugTreatmentDetailVO);
							//extensionItr.remove();
							index++;
						 }
					 }
					 j++;
				 }
			}
			
		
			
			//getting drug schedule for extended drug and save it
			List prevLastDrugTreatDtlVOList=null;
			
			String  episodeVisitNo="";
			String  entryDate="";
			String slNo="";
			prevLastDrugTreatDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			List drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
					
				for(int i=0;i<extendedDrugIdLst.size();i++)
				{
					String drugId=(String)extendedDrugIdLst.get(i);
					if(prevLastDrugTreatDtlVOList!=null && prevLastDrugTreatDtlVOList.size()!=0)
					{
						
						for(int j=0;j<prevLastDrugTreatDtlVOList.size();j++)
						{
							PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevLastDrugTreatDtlVOList.get(j);
							if(vo.getDrugId().equals(drugId.split("#")[0]) && vo.getFrequencyId().equals(drugId.split("#")[1]) && vo.getDoseId().equals(drugId.split("#")[2]))
							{
								episodeVisitNo=vo.getEpisodeVisitNo();
								entryDate=vo.getEntryDate();
								slNo=vo.getSerialNo();
							}
						}
					}
					
					 List drugScheduleLst=new ArrayList();
					for(int j=0;j<drugScheduleVOLst.size();j++)
					 {
						 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(j);
						
						 
						 if(drugSheduleDtlVO.getSerialNo().equals(slNo) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getEntryDate().equals(entryDate))
						 {
							 drugSheduleDtlVO.setPopupDrugId(drugId.split("#")[0]);
							 //here we checking that this record is today visit record 
							if(drugSheduleDtlVO.getEntryDate().equals(sysadateForTodayVisit))
							{
								drugSheduleDtlVO.setTodayVisitFlag("yes");
							}
							else
							{
								drugSheduleDtlVO.setTodayVisitFlag("no");
							}
						  drugScheduleLst.add(drugSheduleDtlVO);
						 }
					 }
					drugSchedule.put(drugId, drugScheduleLst);
				}

			*/
			
			
			//getting remaining prev drug detail List
			String  episodeVisitNo="";
			String  entryDate="";
			String slNo="";
			List prevLastDrugTreatDtlVOList=null;
			prevLastDrugTreatDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			List drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			
			
			List prevRemainingDrugLst=new ArrayList();
			List prevTotalDrugLst=new ArrayList();
			prevTotalDrugLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
					
			
			Iterator prevTotalItr=prevTotalDrugLst.iterator();
			while(prevTotalItr.hasNext())
			{
				PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevTotalItr.next();
				Iterator addedItr=lstDrugTreatmentDtl.iterator();
				boolean remainingFlag=false;
				while(addedItr.hasNext())
				{
					PatDrugTreatmentDetailVO addedVO=(PatDrugTreatmentDetailVO)addedItr.next();
					if(vo.getDrugId().equals(addedVO.getDrugId()) && vo.getDoseId().equals(addedVO.getDoseId()) && vo.getFrequencyId().equals(vo.getFrequencyId()))
					{
						remainingFlag=true;	
					}
				}
				if(!remainingFlag)
				{
					vo.setRxContinue(OpdConfig.SIMPLE_RX);
					episodeVisitNo=vo.getEpisodeVisitNo();
					
					//setting episode code,visit number,admission Number etc
					PatDrugTreatmentDetailVO remainingVO=new PatDrugTreatmentDetailVO();
					HelperMethods.populate(remainingVO, vo);
					remainingVO.setEpisodeCode(_fb.getEpisodeCode());
					remainingVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					remainingVO.setPatCrNo(_fb.getPatCrNo());
					remainingVO.setAdmissionNo(_fb.getAdmissionNo());
					prevRemainingDrugLst.add(remainingVO);
					
				}
			}
			
						
			// here we add only or save those drug which has end date greater than sysdate
			
			
			
			List remainDrudIdLst=new ArrayList();
			Iterator remainingItr=prevRemainingDrugLst.iterator();
			while(remainingItr.hasNext())
			{
				PatDrugTreatmentDetailVO vo =(PatDrugTreatmentDetailVO)remainingItr.next();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				if(vo.getEndDate()!=null)	
				{
					Date endDate=sdf.parse(vo.getEndDate());
					
				
					Date startDate=sdf.parse(vo.getStartDate());
				
				Date systemDate=sdf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				if(!endDate.before(systemDate) || endDate.equals(systemDate))
				{
					vo.setRxContinue(OpdConfig.SIMPLE_RX);
					remainDrudIdLst.add(vo.getDrugId()+"#"+vo.getFrequencyId()+"#"+vo.getDoseId());//this is for getting schedule for remaining particular drug
		
			/**
			## 		Modification Log		:	prevReqQty	,prevIssueQty			
			##		Modify Date				: 	24-02-2015
			##		Reason	(CR/PRS)		: 	First convert string value in float then convert into int... because it was showing "type cast exception"
			##		Modify By				: 	Akash Singh
			*/		
			
					//here we calculate the requiredQty 
					//int prevReqQty=Integer.parseInt(vo.getRequiredQty());
					Double f = Double.parseDouble(vo.getRequiredQty());
					double prevReqQty = (int)Math.round(f);
					
					int prevIssueQty=0;
					if(vo.getIssueQty()!=null)
					{
						//prevIssueQty=Integer.parseInt(vo.getIssueQty());
				        double f1 = Double.parseDouble(vo.getIssueQty());
						prevIssueQty = (int)Math.round(f);
					}
					doseQty=vo.getDoseQty();
					Double requiredQty= prevReqQty-prevIssueQty;
					vo.setDoseQty(doseQty);
					vo.setRequiredQty(requiredQty.toString());
					vo.setIssueQty("0");
					
					//here we checking that this record is today visit record 
					//and if this is today visit record than we are not adding this vo in the list
					if(vo.getEntryDate().equals(sysadateForTodayVisit))
					{
						vo.setTodayVisitFlag("yes");
						
					}
					else
					{
						vo.setTodayVisitFlag("no");
						lstDrugTreatmentDtl.add(vo);
						
					}
					
					
				}}
			}
			
			//saving schedule for remaining drug
			
			 prevLastDrugTreatDtlVOList=null;
			 
			 episodeVisitNo="";
			 entryDate="";
			 //String slNo="";
			 
			prevLastDrugTreatDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			
					
				/*for(int i=0;i<remainDrudIdLst.size();i++)
				{
					String drugId=(String)remainDrudIdLst.get(i);
					List drugScheduleLst=new ArrayList();
					if(prevLastDrugTreatDtlVOList!=null && prevLastDrugTreatDtlVOList.size()!=0)
					{
						
						for(int j=0;j<prevLastDrugTreatDtlVOList.size();j++)
						{
							PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)prevLastDrugTreatDtlVOList.get(j);
							
							if(vo.getDrugId().equals(drugId.split("#")[0]) && vo.getDoseId().equals(drugId.split("#")[2]) && vo.getFrequencyId().equals(drugId.split("#")[1]))
							{
								episodeVisitNo=vo.getEpisodeVisitNo();
								entryDate=vo.getEntryDate();
								slNo=vo.getSerialNo();
							}
						}
					}
					
					for(int j=0;j<drugScheduleVOLst.size();j++)
					 {
						 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(j);
						 
						 if(drugSheduleDtlVO.getSerialNo().equals(slNo) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getEntryDate().equals(entryDate))
						 {
							 drugSheduleDtlVO.setPopupDrugId(drugId.split("#")[0]);
							 //here we checking that this record is today visit record 
							if(drugSheduleDtlVO.getEntryDate().equals(sysadateForTodayVisit))
							{
								drugSheduleDtlVO.setTodayVisitFlag("yes");
							}
							else
							{
								drugSheduleDtlVO.setTodayVisitFlag("no");
							}
						  drugScheduleLst.add(drugSheduleDtlVO);
						 }
					 }
					drugSchedule.put(drugId, drugScheduleLst);
				}*/
			
	/**********************saving drug administration detail in case of IPD ****************************/
			
				
				List allDrugList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
				
				List drugAdminList=new ArrayList();	
				
				String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
				if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
				{
					for(int i=0;i<lstDrugTreatmentDtl.size();i++)
					{
						PatDrugTreatmentDetailVO drugDtlVO=lstDrugTreatmentDtl.get(i);
						if(!drugDtlVO.getRxContinue().equals(OpdConfig.REVOKE))
						{
						
						List scheduleList=(List)drugSchedule.get(drugDtlVO.getDrugId()+"#"+drugDtlVO.getFrequencyId()+"#"+drugDtlVO.getDoseId());
						
						if(scheduleList!=null)
						{
							for(int j=0;j<scheduleList.size();j++)
							{
								DrugSheduleDtlVO scheduleVO=(DrugSheduleDtlVO)scheduleList.get(j);
								DrugAdminDtlVO drugAdminDtlVO=new DrugAdminDtlVO();
								String startDate="";
								
								drugAdminDtlVO.setAdminFlag(OpdConfig.SCHEDULE);
								//drugAdminDtlVO.setItemId(scheduleVO.getItemTypeId());//this is itemId not itemTypeId
								drugAdminDtlVO.setDoseId(scheduleVO.getDoseId());
								drugAdminDtlVO.setDoseName(scheduleVO.getDoseName());
								drugAdminDtlVO.setDoseTime(scheduleVO.getDoseTime());
								drugAdminDtlVO.setDoseShift(scheduleVO.getDoseShift());
								
								drugAdminDtlVO.setItemId(drugDtlVO.getDrugId());
								drugAdminDtlVO.setBeforeTimeLimit(scheduleVO.getCutOffTime());
								drugAdminDtlVO.setAfterTimeLimit(scheduleVO.getCutOffTimeafter());
								if(allDrugList!=null)
								{
									for(int k=0;k<allDrugList.size();k++)
									{
										Entry obj=(Entry)allDrugList.get(k);
										String itemTypeId=obj.getValue().split("#")[1];
										String drugId=obj.getValue().split("#")[0];
										if(drugDtlVO.getDrugId().equals(drugId))
										{
											drugAdminDtlVO.setItemTypeId(itemTypeId);
											break;
										}
									}
								}
								
								
								//drugAdminDtlVO.setItemTypeId(drugDtlVO.get)
								drugAdminDtlVO.setItemName(drugDtlVO.getDrugName());
								drugAdminDtlVO.setPatCrNo(drugDtlVO.getPatCrNo());
								drugAdminDtlVO.setEpisodeCode(drugDtlVO.getEpisodeCode());
								drugAdminDtlVO.setEpisodeVisitNo(drugDtlVO.getEpisodeVisitNo());
								drugAdminDtlVO.setPatAdmNo(drugDtlVO.getAdmissionNo());
								drugAdminDtlVO.setDrugRouteId(drugDtlVO.getDrugRouteId());
								drugAdminDtlVO.setIsEmptyStomach(drugDtlVO.getIsEmptyStomach());
								drugAdminDtlVO.setNoOfDays(drugDtlVO.getDays());
								drugAdminDtlVO.setUpdateAdviceDate(drugDtlVO.getEntryDate());
								drugAdminDtlVO.setEndDate(drugDtlVO.getEndDate());
								drugAdminDtlVO.setSysdate(sysadateForTodayVisit);
								drugAdminDtlVO.setIsReaction(InpatientConfig.IS_REACTION_NO);
								drugAdminDtlVO.setDrugBrandId(drugDtlVO.getDrugBrandId()); //added on 26.7.2016
								drugAdminDtlVO.setItemTypeId(drugDtlVO.getDrugTypeID());
								
								//getting startdate
								if(drugDtlVO.getRxContinue().equals(OpdConfig.NEW_RECORD) || drugDtlVO.getRxContinue().equals(OpdConfig.CHANGE_IN_PREV))
								{
									String startday=drugDtlVO.getStartDate();
									String systemDate= WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
									 
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
									Calendar c = Calendar.getInstance();
									c.setTime(sdf.parse(systemDate));
									c.add(Calendar.DATE, Integer.parseInt(startday)); 
									startDate = sdf.format(c.getTime());
								}
								else
								{
									startDate=drugDtlVO.getStartDate();
								}
								 
								 drugAdminDtlVO.setStartDate(startDate);
								 String systemDateTime= WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
								 drugAdminDtlVO.setAdviceDate(systemDateTime);
								
								 drugAdminList.add(drugAdminDtlVO);
								
							}
						}
									
						}
					}
				}
					
				
			
				
			
			
			
	/***************************************saving rest advice	*****************************/
			deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			RestAdviceDtlVO restAdviceDtlVO= new RestAdviceDtlVO();
			if(!deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				//saving rest Advice
				
				if(!(_fb.getRestDays().equals("")))
				{
					restAdviceDtlVO.setDays(_fb.getRestDays());
					restAdviceDtlVO.setRestReason(_fb.getRestReason());
					//restAdviceDtlVO.setRemarks(_fb.getRestRemark());
					restAdviceDtlVO.setStartDate(_fb.getRestStartDate());
					restAdviceDtlVO.setSerialNo(_fb.getRestSerialNo());
					restAdviceDtlVO.setEpisodeCode(_fb.getEpisodeCode());
					restAdviceDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					restAdviceDtlVO.setPatCrNo(_fb.getPatCrNo());
					restAdviceDtlVO.setAdmissionNo(_fb.getAdmissionNo());
					restAdviceDtlVO.setCirtificateStatus("0");// status for medical certificate which is not generated
				}
				
				//this is because same record can not insert(because today visit record show in the row which will insert)
				List prevTodayRestDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_TODAY_REST_DETAIL_LIST);
				RestAdviceDtlVO todayRestAdviceDtlVO= new RestAdviceDtlVO();
				if(prevTodayRestDtl.size()!=0)
				{
					todayRestAdviceDtlVO=(RestAdviceDtlVO)prevTodayRestDtl.get(0);
					if((todayRestAdviceDtlVO.getDays().equals(restAdviceDtlVO.getDays())) && (todayRestAdviceDtlVO.getRestReason().equals(restAdviceDtlVO.getRestReason())))
					{
						restAdviceDtlVO.setDays(null);
					}
				}
			}
				
			
			
	/************************************saving external treatment*****************************************/		
			
			//saving External Treatment
			List<PatExtTreatmentDetailVO> lstExtTreatmentDtl= new ArrayList<PatExtTreatmentDetailVO>();
			
			for(int i=0;i<Integer.parseInt(_fb.getExtDrugDetailRows());i++)
			{
				PatExtTreatmentDetailVO extVO = new PatExtTreatmentDetailVO();
				
				//extVO.setDoseId(_fb.getSelExtDoseId()[i]);
				//extVO.setDoseName(_fb.getSelExtDoseName()[i]);
				extVO.setExtTreatmentName(_fb.getSelExtTreatmentName()[i]);
				if(_fb.getSelExtTreatmentId()[i].equals("-1"))
				{
					extVO.setIsMasterBound(OpdConfig.IS_NOT_MASTER_BOUND);
				}
				else
				{
					extVO.setIsMasterBound(OpdConfig.IS_MASTER_BOUND);
				}
				extVO.setFrequencyId(_fb.getSelExtFrequencyId()[i]);
				extVO.setDays(_fb.getSelExtDays()[i]);
				extVO.setStartDate(_fb.getSelExtStartDay()[i]);
				extVO.setRemarks(_fb.getSelExtInstructions()[i]);
				extVO.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				extVO.setExtTreatmentId(_fb.getSelExtTreatmentId()[i]);
				extVO.setExtTreatmentName(_fb.getSelExtTreatmentName()[i]);
				extVO.setTreatmentType(OpdConfig.EXTERNAL_TREATMENT);
				//setting visit detail
				extVO.setEpisodeCode(_fb.getEpisodeCode());
				extVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				extVO.setPatCrNo(_fb.getPatCrNo());
				extVO.setAdmissionNo(_fb.getAdmissionNo());
				extVO.setRxContinue(OpdConfig.NEW_RECORD);
				extVO.setRevokeStatus("");
				extVO.setTodayVisitFlag("");
				lstExtTreatmentDtl.add(extVO);
			}
			
			
			//removing the blank row from lstExtTreatmentDtl bcz user can fill or not fill the last row
			Iterator<PatExtTreatmentDetailVO> removeBlankRowExtItr=lstExtTreatmentDtl.iterator();
			while(removeBlankRowExtItr.hasNext())
			{
				PatExtTreatmentDetailVO patExtTreatmentDetailVO=removeBlankRowExtItr.next();
				if(patExtTreatmentDetailVO.getDays().equals(""))
				{
					removeBlankRowExtItr.remove();
				}
			}
			
			//saving revoke List for External Treatment detail
			List prevPatExtTreatmentDtlVOList=new ArrayList();
			String[] extRevoke=_fb.getExtRevoke();
			if(extRevoke!=null)
			{
				prevPatExtTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST);
	 			  
				Iterator<PatExtTreatmentDetailVO> totalRevokeItr = prevPatExtTreatmentDtlVOList.iterator();
				 int j=0;
				 while(totalRevokeItr.hasNext())
				 {
					 PatExtTreatmentDetailVO patExtTreatmentDetailVO =totalRevokeItr.next();
					 for(int i=0;i<extRevoke.length;i++)
					 {
						 if(j==Integer.parseInt(extRevoke[i]))
						 {
							patExtTreatmentDetailVO.setRevokeStatus("revoke"); 
							if(patExtTreatmentDetailVO.getEntryDate().equals(sysadateForTodayVisit))
							{
								patExtTreatmentDetailVO.setTodayVisitFlag("yes");
							}
							else
							{
								patExtTreatmentDetailVO.setTodayVisitFlag("no");
							}
							patExtTreatmentDetailVO.setEpisodeCode(patExtTreatmentDetailVO.getEpisodeCode());
							patExtTreatmentDetailVO.setEpisodeVisitNo(patExtTreatmentDetailVO.getEpisodeVisitNo());
							patExtTreatmentDetailVO.setPatCrNo(patExtTreatmentDetailVO.getPatCrNo());
							patExtTreatmentDetailVO.setAdmissionNo(patExtTreatmentDetailVO.getAdmissionNo());
							patExtTreatmentDetailVO.setRxContinue(OpdConfig.REVOKE);
							lstExtTreatmentDtl.add(patExtTreatmentDetailVO);
							totalRevokeItr.remove();
						 }
					 }
					 //totalRevokeItr.remove(); 
					 j++;
				 }
			 }
			
			//this is for simple Rx and check end date of teratment should be greater than sysdate 
			List prevPatExtTreatVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST);
			Iterator itr=prevPatExtTreatVOList.iterator();
			while(itr.hasNext())
			{
				PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)itr.next();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
				Date endDate=sdf.parse(vo.getEndDate());
				
				Date systemDate=sdf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				if(!endDate.before(systemDate) || endDate.equals(systemDate))
				{
					//here we checking that this record is today visit record 
					if(vo.getEntryDate().equals(sysadateForTodayVisit))
					{
						vo.setTodayVisitFlag("yes");
					}
					else
					{
						vo.setTodayVisitFlag("no");
					}
					
					vo.setEpisodeCode(_fb.getEpisodeCode());
					vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					vo.setPatCrNo(_fb.getPatCrNo());
					vo.setAdmissionNo(_fb.getAdmissionNo());
					vo.setRxContinue(OpdConfig.SIMPLE_RX);
					vo.setRevokeStatus("");
					vo.setTreatmentType(OpdConfig.EXTERNAL_TREATMENT);
										
					lstExtTreatmentDtl.add(vo);
				}
			}
			
			
			//saving other instruction and activity
			List otherInstructionList=(List)session.getAttribute(OpdConfig.OTHER_INSTRUCTION_LIST_FOR_SAVE);
			List prevOtherInstAndActList=(List)session.getAttribute(OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST);
			if(otherInstructionList==null)
			{
				otherInstructionList=new ArrayList();
				if(prevOtherInstAndActList!=null)
				{
					for(int i=0;i<prevOtherInstAndActList.size();i++)
					{
						PatExtTreatmentDetailVO vo=(PatExtTreatmentDetailVO)prevOtherInstAndActList.get(i);
						otherInstructionList.add(vo);
					}
				}
				
			}
			if(otherInstructionList!=null)
			{
				for(int i=0;i<otherInstructionList.size();i++)
				{
					PatExtTreatmentDetailVO extVO=(PatExtTreatmentDetailVO)otherInstructionList.get(i);
					
					
					Boolean flagStatus=false;
					if(prevOtherInstAndActList.size()!=0)
					{
						for(int j=0;j<prevOtherInstAndActList.size();j++)
						{
							PatExtTreatmentDetailVO prevVo=(PatExtTreatmentDetailVO)prevOtherInstAndActList.get(j);
							if(extVO.getExtTreatmentId().equals(prevVo.getExtTreatmentId()))
							{
								flagStatus=true;
								//here we checking that this record is today visit record 
								if(prevVo.getEntryDate().equals(sysadateForTodayVisit))
								{
									extVO.setTodayVisitFlag("yes");
								}
								else
								{
									extVO.setTodayVisitFlag("no");
								}
								extVO.setEpisodeCode(prevVo.getEpisodeCode());
								extVO.setEpisodeVisitNo(prevVo.getEpisodeVisitNo());
								extVO.setPatCrNo(prevVo.getPatCrNo());
								extVO.setAdmissionNo(prevVo.getAdmissionNo());
								extVO.setSerialNo(prevVo.getSerialNo());
								extVO.setEntryDate(prevVo.getEntryDate());
								extVO.setRevokeStatus("");
								//extVO.setTodayVisitFlag("");
								extVO.setRxContinue(OpdConfig.SIMPLE_RX);
							}
							
						}
					}
					if(!flagStatus)
					{
						extVO.setEpisodeCode(_fb.getEpisodeCode());
						extVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
						extVO.setPatCrNo(_fb.getPatCrNo());
						extVO.setAdmissionNo(_fb.getAdmissionNo());
						extVO.setRevokeStatus("");
						extVO.setTodayVisitFlag("");
						extVO.setRxContinue(OpdConfig.NEW_RECORD);
					}
					
					
					
					lstExtTreatmentDtl.add(extVO);
				}
			}
			
	/***********************************saving ext admin Dtl*****************************************/
			List extAdminVoList=new ArrayList();
			DrugFrequencyMstVO[] drugFreqMstVOArray=(DrugFrequencyMstVO[])session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			
			for(int i=0;i<lstExtTreatmentDtl.size();i++)
			{
				PatExtTreatmentDetailVO vo =(PatExtTreatmentDetailVO)lstExtTreatmentDtl.get(i);
				if(vo.getTreatmentType().equals(OpdConfig.EXTERNAL_TREATMENT))
				{
					ExtAdminDtlVO extAdminVo=new ExtAdminDtlVO();
					HelperMethods.populate(extAdminVo, vo);
					extAdminVo.setPatAdmNo(_fb.getAdmissionNo());
					extAdminVo.setRevokeStatus(vo.getRevokeStatus());
					String startDate="";
					if(vo.getRxContinue().equals(OpdConfig.NEW_RECORD))
					{
						String startday=vo.getStartDate();
						String systemDate= WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
						 
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(systemDate));
						c.add(Calendar.DATE, Integer.parseInt(startday)); 
						startDate = sdf.format(c.getTime());
					}
					else
					{
						startDate=vo.getStartDate();
						extAdminVo.setAdviceDate(vo.getEntryDate());
					}
					extAdminVo.setStartDate(startDate);
					extAdminVo.setAdminFlag(OpdConfig.SCHEDULE);
					if(vo.getExtTreatmentId().equals("-1"))
					{
						extAdminVo.setExtTreatmentId("");
					}
					
					String freqId=vo.getFrequencyId();
					if(freqId!="-1")
					{
						for(int j=0;j<drugFreqMstVOArray.length;j++)
						{
							DrugFrequencyMstVO freqVo=drugFreqMstVOArray[j];
							if(vo.getFrequencyId().equals(freqVo.getFrequencyId()))
							{
								if(freqVo.getMorDoseTime()!=null)
								{
									ExtAdminDtlVO extVO=new ExtAdminDtlVO();
									HelperMethods.populate(extVO, extAdminVo);
									extVO.setDoseTime(freqVo.getMorDoseTime());
									extAdminVoList.add(extVO);							
								}
								if(freqVo.getAftDoseTime()!=null)
								{
									ExtAdminDtlVO extVO=new ExtAdminDtlVO();
									HelperMethods.populate(extVO, extAdminVo);
									extVO.setDoseTime(freqVo.getAftDoseTime());
									extAdminVoList.add(extVO);
								}
								if(freqVo.getEveDoseTime()!=null)
								{
									ExtAdminDtlVO extVO=new ExtAdminDtlVO();
									HelperMethods.populate(extVO, extAdminVo);
									extVO.setDoseTime(freqVo.getEveDoseTime());
									extAdminVoList.add(extVO);
								}
								if(freqVo.getNgtDoseTime()!=null)
								{
									ExtAdminDtlVO extVO=new ExtAdminDtlVO();
									HelperMethods.populate(extVO, extAdminVo);
									extVO.setDoseTime(freqVo.getNgtDoseTime());
									extAdminVoList.add(extVO);
								}
							}
						}
					}
					//extAdminVoList.add(extAdminVo);
				}
			}
			
	/*******************************************saving diet advice************************************/		
			//saving diet Advice
			PatDietAdviceDetailVO _patDietDetailVO= new PatDietAdviceDetailVO();
			if(!(_fb.getDietDays().equals("")))
			{
				_patDietDetailVO.setDays(_fb.getDietDays());
				_patDietDetailVO.setDietTypeCode(_fb.getDietTypeId());
				_patDietDetailVO.setRemarks(_fb.getDietRemark());
				_patDietDetailVO.setStartDate(_fb.getDietStartDate());
				_patDietDetailVO.setSerialNo(_fb.getDietSerialNo());
							
				_patDietDetailVO.setEpisodeCode(_fb.getEpisodeCode());
				_patDietDetailVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				_patDietDetailVO.setPatCrNo(_fb.getPatCrNo());
				_patDietDetailVO.setAdmissionNo(_fb.getAdmissionNo());
			}
			//this is because same record can not insert(because today visit record open in current row)
			List prevTodayDietDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_TODAY_DIET_DETAIL_LIST);
			PatDietAdviceDetailVO _todayPatDietDetailVO= new PatDietAdviceDetailVO();
			if(prevTodayDietDtl.size()!=0)
			{
				_todayPatDietDetailVO=(PatDietAdviceDetailVO)prevTodayDietDtl.get(0);
				if(_todayPatDietDetailVO.getRemarks()==null)
				{
					_todayPatDietDetailVO.setRemarks("");
				}
				if((_todayPatDietDetailVO.getRemarks().equals(_patDietDetailVO.getRemarks())) && (_todayPatDietDetailVO.getDays().equals(_patDietDetailVO.getDays())) && (_todayPatDietDetailVO.getDietTypeCode().equals(_patDietDetailVO.getDietTypeCode())))
				{
					_patDietDetailVO.setDays(null);
				}
			}
			
			//adding entry date for drug advice list
			setSysdate(_rq);
			String systemdate=(String)session.getAttribute(Config.SYSDATE);
			if(lstDrugTreatmentDtl!=null && lstDrugTreatmentDtl.size()!=0)
			{
				for(int i=0;i<lstDrugTreatmentDtl.size();i++)
				{
					PatDrugTreatmentDetailVO vo=(PatDrugTreatmentDetailVO)lstDrugTreatmentDtl.get(i);
					vo.setEntryDate(systemdate);
				}
			}
			
			
			PatientTreatmentDetailDATA.savePatTreatmentDetail(lstDrugTreatmentDtl,lstExtTreatmentDtl,_patDietDetailVO,drugSchedule,restAdviceDtlVO, drugAdminList,extAdminVoList ,userVO);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e) 
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			flag=false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag=false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag=false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag=false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag=false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}
	
	
	/**
	 * @param _fb
	 * @param _request
	 */
	public static void rxContinueDelRowFromTable(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List prevPatDrugTreatmentDtlVOList=new ArrayList();
		List rxContinuePatDrugTreatmentDetailVOList=null;
		List totalPatDrugTreatmentDetailVOList=new ArrayList();
		List allDrugRouteVOLst=null;
		//List rxContinueList=new ArrayList();
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadateForTodayVisit=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
		
		try{
			// UserVO userVO = getUserVO(_request);
			 Map drugSheduleMap=new HashMap();
			 _fb.setActiveTab("tabDrugAdvice");
			 int selectedDelRow=Integer.parseInt(_fb.getIndex());
			 allDrugRouteVOLst=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE);
			 rxContinuePatDrugTreatmentDetailVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_RXCONTINUE_DRUG_DETAIL_LIST);
			 if(rxContinuePatDrugTreatmentDetailVOList==null)
			 {
				 rxContinuePatDrugTreatmentDetailVOList=new ArrayList();
			 }
			 prevPatDrugTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			 totalPatDrugTreatmentDetailVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);			  
			 Iterator<PatDrugTreatmentDetailVO> itr = prevPatDrugTreatmentDtlVOList.iterator();
			 int j=0;
			
			//setting last drug row
				List lstDrugTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);
				 
				 int drugRowsLength = Integer.parseInt(_fb.getDrugDetailRows());
				 int index=drugRowsLength-1;
				 String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
				 
				// if(!_fb.getSelDrugId()[index].equals("0 ") && Integer.parseInt(_fb.getIndexDelete())!=index)
				 if(!_fb.getSelDrugId()[index].equals("0"))
				 {
					 	PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
						vo.setDrugId(_fb.getSelDrugId()[index]+"#"+_fb.getSelDrugItemTypeId()[index]);
						vo.setDrugName(_fb.getSelDrugName()[index]);
						vo.setDoseId(_fb.getSelDoseId()[index]);
						vo.setDoseName(_fb.getSelDoseName()[index]);
						vo.setFrequencyId(_fb.getSelFrequencyId()[index]);
						vo.setDays(_fb.getSelDays()[index]);
						vo.setStartDate(_fb.getSelStartDay()[index]);
						vo.setRemarks(_fb.getSelInstructions()[index]);
						vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
					//	vo.setIsEmptyStomach(isEmptyStomach[index]);
						vo.setDrugRouteId(_fb.getSelDrugRouteId()[index]);
						vo.setDrugRouteName(_fb.getSelDrugRouteName()[index]);
						vo.setQuantity(_fb.getQuantity()[index]);
						vo.setDrugAdminId(_fb.getSelDrugAdminId()[index]);
						vo.setDrugAdminName(_fb.getSelDrugAdminName()[index]);
						vo.setDrugBrandId(_fb.getSelDrugBrandId()[index]);
						
						
						
						Boolean flag=false;
						//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
						Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
						while(removeBlankRowItr.hasNext())
						{
							PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
							if(patDrugTreatmentDetailVO.getDays().equals(""))
							{
								removeBlankRowItr.remove();
								flag=true;
								//lstDrugTreatmentDtl.add(vo);
							}
						}
						if(flag)
						{
							lstDrugTreatmentDtl.add(vo);
						}
						
						//lstDrugTreatmentDtl.add(vo);
				 }
				 _fb.setActiveTab("tabDrugAdvice");
				
			 
			 
			 
			 
			 //below there variable use for getting schedule detail 
			 String freqId="";
			 String drugId="";
			 String episodeVisitNo="";
			 String entryDate="";
			 String doseId="";
			 String slNo="";
			 
			 
			 // for after submitting change drug page revoke checked  check box remain checked 
			 String revokeItemId="";
			 String[] revokeArray=_fb.getRevoke();
			 if(revokeArray!=null)
			 {
				 for(int i=0;i<revokeArray.length;i++)
				 {
					 String[] itemIdAndIndexArray=revokeArray[i].split("\\$"); 
					 revokeItemId=itemIdAndIndexArray[0]+"$"+itemIdAndIndexArray[2]+"$"+itemIdAndIndexArray[3]+"#"+revokeItemId;
				 }
				 if(!revokeItemId.equals(""))	revokeItemId = revokeItemId.substring(0,revokeItemId.length()-1);
				 _fb.setRevokeHidden(revokeItemId);
			 }
			
			 
			 /*
			 // for after submitting change drug page extension checked  check box remain checked 
			 String extensionItemId="";
			 String[] extensionArray=_fb.getExtension();
						 
			 if(extensionArray!=null)
			 {
				 for(int i=0;i<extensionArray.length;i++)
				 {
					String[] itemIdAndIndexArray=extensionArray[i].split("\\$");
					extensionItemId=itemIdAndIndexArray[0]+"$"+itemIdAndIndexArray[2]+"$"+itemIdAndIndexArray[3]+"#"+extensionItemId;
				 }
				 if(!extensionItemId.equals(""))	extensionItemId = extensionItemId.substring(0,extensionItemId.length()-1);
				 _fb.setExtensionHidden(extensionItemId);
			 }
			 
			 	 
			 // for after submitting change drug page extensionDays  values remain same 
			 String extensiondays="";
			 String[] extensionDaysArray=_fb.getExtensionDays();
			 
			 if(extensionDaysArray!=null)
			 {
				 for(int i=0;i<extensionDaysArray.length;i++)
				 {
					 //String[] itemIdAndIndexArray=extensionDaysArray[i].split("\\$");
					 extensiondays=extensionDaysArray[i]+"#"+extensiondays;
				 }
				 if(!extensiondays.equals(""))	extensiondays = extensiondays.substring(0,extensiondays.length()-1);
				 _fb.setExtensionDaysHidden(extensiondays);
			 }
			*/
			 
			 while(itr.hasNext())
			 {
				 PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO =itr.next();
				   if(j==selectedDelRow)
					 {		
					     //this is rxContinue VO List
					     PatDrugTreatmentDetailVO drugTreatVO=new PatDrugTreatmentDetailVO();
						 HelperMethods.populate(drugTreatVO, _patDrugTreatmentDetailVO);
						 drugTreatVO.setRxContinueFlag("modify");
						 rxContinuePatDrugTreatmentDetailVOList.add(drugTreatVO);
						
					     //this is set for identify which VO is open in for modify
						 _patDrugTreatmentDetailVO.setRxContinueFlag("modify"); 
						 _patDrugTreatmentDetailVO.setRxContinue("4");
						 
						//here we checking that this record is today visit record 
							if(_patDrugTreatmentDetailVO.getEntryDate().equals(sysadateForTodayVisit))
							{
								_patDrugTreatmentDetailVO.setTodayVisitFlag("yes");
							}
							else
							{
								_patDrugTreatmentDetailVO.setTodayVisitFlag("no");
							}
						 
						 
							_patDrugTreatmentDetailVO.setChangeStartDate(_patDrugTreatmentDetailVO.getStartDate());
							_patDrugTreatmentDetailVO.setStartDate("0");
							//_patDrugTreatmentDetailVO.setStartDate(_patDrugTreatmentDetailVO.getStartDate());
						 
						 //_patDrugTreatmentDetailVO.setDays("");
						 _patDrugTreatmentDetailVO.setRequirmentTimeHrs("");
						 _patDrugTreatmentDetailVO.setRequirmentTimeMin("");
						 
						 
						//getting dose qty and is frequency
						 List drugDoseVOLst=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
						 String isFrequencyBound="0";
						 String doseQty="0";
						 //checking isFrequency bound
							Iterator itrFreq=drugDoseVOLst.iterator();
							while(itrFreq.hasNext())
							{
								DrugDoseVO vo=(DrugDoseVO)itrFreq.next();
								if(_patDrugTreatmentDetailVO.getDoseId().equals(vo.getDoseId()))
								{
									isFrequencyBound=(vo.getIsFrequencyBound()==null || vo.getIsFrequencyBound().trim().equals(""))?"1":vo.getIsFrequencyBound();
									break;
								}
							}
							
							// getting doseQty 
							Iterator itrForDoseQty = drugDoseVOLst.iterator();
							while(itrForDoseQty.hasNext())
							{
								DrugDoseVO vo=(DrugDoseVO)itrForDoseQty.next();
								if(_patDrugTreatmentDetailVO.getDoseId().equals(vo.getDoseId()))
								{
									doseQty=(vo.getDoseQty()==null || vo.getDoseQty().trim().equals(""))?"0":vo.getDoseQty();
									break;
								}
							}
						 String  tempDoseId=_patDrugTreatmentDetailVO.getDoseId()+"^"+isFrequencyBound+"^"+doseQty;
						 _patDrugTreatmentDetailVO.setDoseId(tempDoseId);
						 if(isFrequencyBound.equals(OpdConfig.IS_NOT_FREQUENCY_BOUND))
						 {
							 _patDrugTreatmentDetailVO.setQuantity(_patDrugTreatmentDetailVO.getDoseQty()); 
						 }
						 else
						 {
							// _patDrugTreatmentDetailVO.setQuantity("");
							 _patDrugTreatmentDetailVO.setQuantity(_patDrugTreatmentDetailVO.getDoseQty()); 
						 }
						
						 
						 // for getting drugRoute Name
						 //String drugRouteId=_patDrugTreatmentDetailVO.getDrugRouteId();
						 Iterator allDrugRouteVOLstItr=allDrugRouteVOLst.iterator();
						 while(allDrugRouteVOLstItr.hasNext())
						 {
							 DrugRouteMstVO drugRouteMstVO=(DrugRouteMstVO)allDrugRouteVOLstItr.next();
							 if(drugRouteMstVO.getDrugRouteId().equals(_patDrugTreatmentDetailVO.getDrugRouteId()))
							 {
								 _patDrugTreatmentDetailVO.setDrugRouteName(drugRouteMstVO.getDrugRouteName());
							 }
						 }
						 
						 totalPatDrugTreatmentDetailVOList.add(0,_patDrugTreatmentDetailVO);
						 
						 freqId=_patDrugTreatmentDetailVO.getFrequencyId();
						 drugId=_patDrugTreatmentDetailVO.getDrugId();
						 episodeVisitNo=_patDrugTreatmentDetailVO.getEpisodeVisitNo();
						 entryDate=_patDrugTreatmentDetailVO.getEntryDate();
						 doseId=_patDrugTreatmentDetailVO.getDoseId().split("\\^")[0];
						 slNo=_patDrugTreatmentDetailVO.getSerialNo();
						 itr.remove();	
					 }
				  j++;
			 }
			 
			 //getting drug schedule for particular RxContinue drug
			 List drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			 String freqCount="0";	
				DrugFrequencyMstVO[] arr = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
				DrugFrequencyMstVO drugFreqVO = null;
						 
			 List drugScheduleLst=new ArrayList();
			 for(int i=0;i<drugScheduleVOLst.size();i++)
			 {
				 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(i);
				 if(drugSheduleDtlVO.getSerialNo().equals(slNo) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getEntryDate().equals(entryDate) )
				 {
					 drugSheduleDtlVO.setPopupFreqId(freqId);
					 String popupDrugId=drugSheduleDtlVO.getItemTypeId();
					 drugSheduleDtlVO.setPopupDrugId(popupDrugId);
					 //this is for getting Frequency
					 for(int k=0;k<arr.length;k++)
					 {
						 if(arr[k].getFrequencyId().equals(freqId))
						 {
								drugFreqVO=arr[k];
						 }
					 }
					 if(drugFreqVO!=null)
					 {
						freqCount=drugFreqVO.getFrequency();			
					 }		
					 drugSheduleDtlVO.setFrequency(freqCount);	
					 
					 if(drugSheduleDtlVO.getEntryDate().equals(sysadateForTodayVisit))
					 {
						 drugSheduleDtlVO.setTodayVisitFlag("yes");
					 }
					else
					{
						drugSheduleDtlVO.setTodayVisitFlag("no");
					}
					 
					 drugScheduleLst.add(drugSheduleDtlVO);
				 }
				 
			 }
			 
			 drugSheduleMap=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			 if(drugSheduleMap==null)
			 {
				 drugSheduleMap=new HashMap();
			 }
			 if(drugScheduleLst.size()!=0)
			 {
				 drugSheduleMap.put(drugId+"#"+freqId+"#"+doseId,drugScheduleLst );
			 }
			
			 session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSheduleMap);
			 
			 WebUTIL.setAttributeInSession(_request,OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST  , prevPatDrugTreatmentDtlVOList);
			 WebUTIL.setAttributeInSession(_request,OpdConfig.PAT_TREATMENT_DTL_RXCONTINUE_DRUG_DETAIL_LIST  , rxContinuePatDrugTreatmentDetailVOList); 
			 
			 //this is for adding drug id with itemTypeId for vo who is open in modify mode(rxContinue)
			 List<Entry> drugList = (List<Entry>)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			 Iterator<PatDrugTreatmentDetailVO> iter =totalPatDrugTreatmentDetailVOList.iterator();
				
				while(iter.hasNext())
				{
					PatDrugTreatmentDetailVO patDrugTreatmentDetailVO =iter.next();
					//Iterator<DrugDoseVO> itera =drugDoseVOList.iterator();
					Iterator<Entry> itera =drugList.iterator();
					while(itera.hasNext())
					{
						Entry obj=itera.next();
						String[] itemIdAndItemTypeId=obj.getValue().split("#");
						String itemId=itemIdAndItemTypeId[0];
						String ItemTypeId=itemIdAndItemTypeId[1];
						if(patDrugTreatmentDetailVO.getDrugId().equals(itemId))
						{
							if(patDrugTreatmentDetailVO.getRemarks()==null)
							{
								patDrugTreatmentDetailVO.setRemarks("");
							}
							patDrugTreatmentDetailVO.setDrugId(patDrugTreatmentDetailVO.getDrugId()+"#"+ItemTypeId);
												
						}
						if(patDrugTreatmentDetailVO.getDrugId().equals("0"))
						{
							if(patDrugTreatmentDetailVO.getRemarks()==null)
							{
								patDrugTreatmentDetailVO.setRemarks("");
							}
							patDrugTreatmentDetailVO.setDrugId(patDrugTreatmentDetailVO.getDrugId()+"#"+"0");
						}
						
					}
					
				}
				WebUTIL.setAttributeInSession(_request, OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, totalPatDrugTreatmentDetailVOList);
				_fb.setDrugDetailRows(Integer.toString(totalPatDrugTreatmentDetailVOList.size()));
			  
				
				//objStatus.add(Status.LIST);
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
	
	public static void rxContinueExtDelRowFromTable(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List prevPatExtTreatmentDtlVOList=new ArrayList();
		List totalPatExtTreatmentDetailVOList=new ArrayList();
		try{
			 //UserVO userVO = getUserVO(_request);
			 int selectedDelRow=Integer.parseInt(_fb.getExtIndex());
			 prevPatExtTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_EXT_TREATMENT_DETAIL_LIST);
			 totalPatExtTreatmentDetailVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST);			  
			 Iterator<PatExtTreatmentDetailVO> itr = prevPatExtTreatmentDtlVOList.iterator();
			 int j=0;
			 while(itr.hasNext()) 
			 {
				 PatExtTreatmentDetailVO _patExtTreatmentDetailVO =itr.next();
				   if(j==selectedDelRow)
					 {		
					     _patExtTreatmentDetailVO.setStartDate("0");
						 _patExtTreatmentDetailVO.setDays("");
						 totalPatExtTreatmentDetailVOList.add(0,_patExtTreatmentDetailVO);
						 itr.remove();	
					 }
				  j++;
			 }
			 WebUTIL.setAttributeInSession(_request,OpdConfig.PAT_TREATMENT_DTL_PREV_EXT_DETAIL_LIST  , prevPatExtTreatmentDtlVOList);
			 WebUTIL.setAttributeInSession(_request, OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, totalPatExtTreatmentDetailVOList);
				
			_fb.setExtDrugDetailRows(Integer.toString(totalPatExtTreatmentDetailVOList.size()));
			  
			 objStatus.add(Status.LIST);
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
	
	public static void getDefaultShedule(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		try{
			 //UserVO userVO = getUserVO(_request);
			 List drugSheduleLst=new ArrayList();
			 String[] popUpDoseId=_fb.getPopupDoseId().split("\\^");
			 _fb.setPopupDoseId(popUpDoseId[0]);
				Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
					if(drugSchedule!=null)
					{
						 
						// drugSheduleLst=(List)drugSchedule.get(_fb.getPopupDrugId());
						drugSheduleLst=(List)drugSchedule.get(_fb.getPopupDrugId()+"#"+_fb.getPopupFreqId()+"#"+_fb.getPopupDoseId());
						if(drugSheduleLst!=null)
						{
							for(int i=0;i<drugSheduleLst.size();i++)
							 {
								 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugSheduleLst.get(i);
								 if(!(drugSheduleDtlVO.getPopupDrugId().equals(_fb.getPopupDrugId())) || !(drugSheduleDtlVO.getPopupFreqId().equals(_fb.getPopupFreqId())) )
								 {
									 //drugSchedule.remove(_fb.getPopupRowIndex());
									 drugSchedule.remove(_fb.getPopupDrugId()+"#"+_fb.getPopupFreqId()+"#"+_fb.getPopupDoseId());
								 }
							 }
					    }
					}
					session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSchedule);
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
	
	public static void saveDrugShedule(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		try{
			// UserVO userVO = getUserVO(_request);
			 Map drugSheduleMap=new HashMap();
			 List drugSheduleLst=new ArrayList();
			 			
			 drugSheduleMap=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			 if(drugSheduleMap==null)
			 {
				 drugSheduleMap=new HashMap();
			 }
			 
			String[] isEmptyStomachArray=  _fb.getPopupCheckIndex().split("#");//this is for getting IsEmptyStomach Check Box value from popup
																			   //because only selected check box array return from form not whole
			int freqCount=Integer.parseInt(_fb.getPopupFreqCount());
			String doseName="";
			for(int i=0;i<freqCount;i++)
			{
				String scheduleSlNo=new Integer(i).toString();
				
				DrugSheduleDtlVO drugSheduleDtlVO=new DrugSheduleDtlVO();
				if(_fb.getPopupDoseIdArray()!=null)
				{
					List lst=(List)session.getAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP);
					Iterator itr=lst.iterator();
					while(itr.hasNext())
					{
						Entry obj=(Entry)itr.next();
						if(obj.getValue().equals(_fb.getPopupDoseIdArray()[i]))
						{
							doseName=obj.getLabel();
							break;
						}
					}
						
					drugSheduleDtlVO.setDoseName(doseName);	
					drugSheduleDtlVO.setDoseId(_fb.getPopupDoseIdArray()[i]);
				}
				else
				{
					drugSheduleDtlVO.setDoseId("0");
					drugSheduleDtlVO.setDoseName(_fb.getPopupDoseNameArray()[i]);
				}
				
				drugSheduleDtlVO.setDoseShift(_fb.getDrugFreqShift()[i]);
				drugSheduleDtlVO.setDoseTime(_fb.getDrugRequirmentTimeHrs()[i]+":"+_fb.getDrugRequirmentTimeMin()[i]);
				//drugSheduleDtlVO.setIsEmptyStomach(_fb.getPopupIsEmptyStomachArray()[i]);
				drugSheduleDtlVO.setIsEmptyStomach(isEmptyStomachArray[i]);
				drugSheduleDtlVO.setSheduleSerialNo(scheduleSlNo);
				drugSheduleDtlVO.setItemTypeId(_fb.getPopupItemTypeId());
				drugSheduleDtlVO.setPopupDrugId(_fb.getPopupDrugId());
				drugSheduleDtlVO.setPopupFreqId(_fb.getPopupFreqId());
				drugSheduleDtlVO.setFrequency(_fb.getPopupFreqCount());
				drugSheduleDtlVO.setPopupRowIndex(_fb.getPopupRowIndex());
				//drugSheduleDtlVO.setPopupRowIndex(_fb.getRemarks()[i]);
				drugSheduleDtlVO.setCutOffTime(_fb.getCutOffTimeBefore()[i]);
				drugSheduleDtlVO.setCutOffTimeafter(_fb.getCutOffTimeAfter()[i]);
				drugSheduleLst.add(drugSheduleDtlVO);
			}
			//drugSheduleMap.put(_fb.getPopupDrugId(), drugSheduleLst);
			drugSheduleMap.put(_fb.getPopupDrugId()+"#"+_fb.getPopupFreqId()+"#"+_fb.getPopupDoseId(), drugSheduleLst);
			session.setAttribute(OpdConfig.DRUG_SHEDULE_MAP, drugSheduleMap);
			
			objStatus.add(Status.NEW);
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
	
	public static void getPrevDrugShedule(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List prevPatDrugTreatmentDtlVOList=new ArrayList();
		try{
			 int selectedDelRow=Integer.parseInt(_fb.getScheduleIndex());
			 prevPatDrugTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			 			  
			 Iterator<PatDrugTreatmentDetailVO> itr = prevPatDrugTreatmentDtlVOList.iterator();
			 int j=0;
			 //below there variable use for getting schedule detail 
			 //String drugId="";
			 String episodeVisitNo="";
			 String entryDate="";
			 //String doseId="";
			 //String freqId="";
			 String episodeCode="";
			 String slNo="";
			 String patCrNo="";
			 while(itr.hasNext())
			 {
				 PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO =itr.next();
				   if(j==selectedDelRow)
					 {		
					     //drugId=_patDrugTreatmentDetailVO.getDrugId();
					     //doseId=_patDrugTreatmentDetailVO.getDoseId();
					     episodeVisitNo=_patDrugTreatmentDetailVO.getEpisodeVisitNo();
					     entryDate=_patDrugTreatmentDetailVO.getEntryDate();
					     episodeCode=_patDrugTreatmentDetailVO.getEpisodeCode();
					     slNo=_patDrugTreatmentDetailVO.getSerialNo();
					     patCrNo=_patDrugTreatmentDetailVO.getPatCrNo();
					 }
				  j++;
			 }
			 
			 //getting drug schedule for particular RxContinue drug
			 List drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			  
			 List drugScheduleLst=new ArrayList();
			 for(int i=0;i<drugScheduleVOLst.size();i++)
			 {
				 DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)drugScheduleVOLst.get(i);
				 if(drugSheduleDtlVO.getEntryDate().equals(entryDate) && drugSheduleDtlVO.getEpisodeCode().equals(episodeCode) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getPatCrNo().equals(patCrNo) && drugSheduleDtlVO.getSerialNo().equals(slNo))
				 {
					 //drugSheduleDtlVO.setDoseName(doseName);
					 drugScheduleLst.add(drugSheduleDtlVO);
				 }
				 
				 
				 /*
				 if(drugSheduleDtlVO.getItemTypeId().equals(drugId) && drugSheduleDtlVO.getEpisodeVisitNo().equals(episodeVisitNo) && drugSheduleDtlVO.getEntryDate().equals(entryDate))
				 {
					 //drugSheduleDtlVO.setDoseName(doseName);
					 drugScheduleLst.add(drugSheduleDtlVO);
				 }
				 */
			 }
			 
			session.setAttribute(OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG, drugScheduleLst);
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
	
	
	public static void getDrugProperty(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		//HttpSession session=WebUTIL.getSession(_request);
		UserVO userVO = getUserVO(_request);
		Map mpDrugProperties=null;
		Map mpColumHeaderForSaftyAlert=new HashMap();
		Map mpDrugDoseIndication=new HashMap();
		try{
			String itemId=_fb.getPropertyItemId(); 
			mpDrugProperties=PatientTreatmentDetailDATA.getDrugProperties(itemId, userVO);
			
			List saftyAlertList=(List)mpDrugProperties.get(OpdConfig.DRUG_SAFTY_ALERT_LIST);
			if(saftyAlertList!=null && saftyAlertList.size()!=0)
			{
				DrugSaftyAlertMstVO saftyAlertVo=(DrugSaftyAlertMstVO)saftyAlertList.get(0);
				if(saftyAlertVo.getCL()!=null)
				{
					mpColumHeaderForSaftyAlert.put("CL", saftyAlertVo.getCL());
				}
				if(saftyAlertVo.getSP()!=null)
				{
					mpColumHeaderForSaftyAlert.put("SP", saftyAlertVo.getSP());
				}
				if(saftyAlertVo.getINT()!=null)
				{
					mpColumHeaderForSaftyAlert.put("INT", saftyAlertVo.getINT());
				}
				if(saftyAlertVo.getINT_POT_HAZ()!=null)
				{
					mpColumHeaderForSaftyAlert.put("INT_POT_HAZ", saftyAlertVo.getINT_POT_HAZ());
				}
				if(saftyAlertVo.getADR_POT_LT()!=null)
				{
					mpColumHeaderForSaftyAlert.put("ADR_POT_LT", saftyAlertVo.getADR_POT_LT());
				}
				if(saftyAlertVo.getLAB_INT()!=null)
				{
					mpColumHeaderForSaftyAlert.put("LAB_INT", saftyAlertVo.getLAB_INT());
				}
				if(saftyAlertVo.getINT_FOOD()!=null)
				{
					mpColumHeaderForSaftyAlert.put("INT_FOOD", saftyAlertVo.getINT_FOOD());
				}
				
			}
						
			WebUTIL.setAttributeInSession(_request, OpdConfig.COLUM_HEADER_MAP_SAFTY_ALERT, mpColumHeaderForSaftyAlert);
			
			List drugDoseIndicationList=(List)mpDrugProperties.get(OpdConfig.DRUG_DOSE_INDICATION_LIST);
			
			if(drugDoseIndicationList!=null && drugDoseIndicationList.size()!=0)
			{
				DrugDoseIndicationMstVO indicationVO=(DrugDoseIndicationMstVO)drugDoseIndicationList.get(0);
				
				if(indicationVO.getORAL()!=null)
				{
					mpDrugDoseIndication.put("ORAL", indicationVO.getORAL());
				}
				if(indicationVO.getINTRAVENOUS()!=null)
				{
					mpDrugDoseIndication.put("INTRAVENOUS", indicationVO.getINTRAVENOUS());
				}
				if(indicationVO.getPARENTRERAL()!=null)
				{
					mpDrugDoseIndication.put("PARENTRERAL", indicationVO.getPARENTRERAL());
				}
				if(indicationVO.getOPTHALMIC()!=null)
				{
					mpDrugDoseIndication.put("OPTHALMIC", indicationVO.getOPTHALMIC());
				}
				if(indicationVO.getINTRA_ACTICULAR()!=null)
				{
					mpDrugDoseIndication.put("INTRA ACTICULAR", indicationVO.getINTRA_ACTICULAR());
				}
				if(indicationVO.getTOPICAL()!=null)
				{
					mpDrugDoseIndication.put("TOPICAL", indicationVO.getTOPICAL());
				}
				if(indicationVO.getANY_ROUTE()!=null)
				{
					mpDrugDoseIndication.put("ANY ROUTE", indicationVO.getANY_ROUTE());
				}
				if(indicationVO.getINTRAMUSCULAR()!=null)
				{
					mpDrugDoseIndication.put("INTRAMUSCULAR", indicationVO.getINTRAMUSCULAR());
				}
				if(indicationVO.getMISCELLANEOUS()!=null)
				{
					mpDrugDoseIndication.put("MISCELLANEOUS", indicationVO.getMISCELLANEOUS());
				}
				if(indicationVO.getOTIC()!=null)
				{
					mpDrugDoseIndication.put("OTIC", indicationVO.getOTIC());
				}
				if(indicationVO.getMOUTH()!=null)
				{
					mpDrugDoseIndication.put("MOUITH", indicationVO.getMOUTH());
				}
				if(indicationVO.getRECTAL()!=null)
				{
					mpDrugDoseIndication.put("RECTAL", indicationVO.getRECTAL());
				}
				if(indicationVO.getNASAL()!=null)
				{
					mpDrugDoseIndication.put("NASAL", indicationVO.getNASAL());
				}
				if(indicationVO.getINHALATION()!=null)
				{
					mpDrugDoseIndication.put("INHALATION", indicationVO.getINHALATION());
				}
				if(indicationVO.getVAGINAL()!=null)
				{
					mpDrugDoseIndication.put("VAGINAL", indicationVO.getVAGINAL());
				}
				if(indicationVO.getLINGUAL()!=null)
				{
					mpDrugDoseIndication.put("LINGUAL", indicationVO.getLINGUAL());
				}
				if(indicationVO.getINTRATHECAL()!=null)
				{
					mpDrugDoseIndication.put("INTRATHECAL()", indicationVO.getINTRATHECAL());
				}
				if(indicationVO.getINTRALESIONAL()!=null)
				{
					mpDrugDoseIndication.put("INTRALESIONAL()", indicationVO.getINTRALESIONAL());
				}
				if(indicationVO.getEXTRA_AMNIOTIC()!=null)
				{
					mpDrugDoseIndication.put("EXTRA AMNIOTIC", indicationVO.getEXTRA_AMNIOTIC());
				}
				if(indicationVO.getEPIDURAL()!=null)
				{
					mpDrugDoseIndication.put("EPIDURAL", indicationVO.getEPIDURAL());
				}
				if(indicationVO.getINTRASPINAL()!=null)
				{
					mpDrugDoseIndication.put("INTRASPINAL", indicationVO.getINTRASPINAL());
				}
				if(indicationVO.getSUBCUTANEOUS()!=null)
				{
					mpDrugDoseIndication.put("SUBCUTANEOUS", indicationVO.getSUBCUTANEOUS());
				}
				if(indicationVO.getINTRAVESICAL()!=null)
				{
					mpDrugDoseIndication.put("INTRAVESICAL", indicationVO.getINTRAVESICAL());
				}
				if(indicationVO.getINTRADERMAL()!=null)
				{
					mpDrugDoseIndication.put("INTRADERMAL", indicationVO.getINTRADERMAL());
				}
				if(indicationVO.getTRENSDERMAL()!=null)
				{
					mpDrugDoseIndication.put("TRENSDERMAL", indicationVO.getTRENSDERMAL());
				}

			}
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.COLUM_HEADER_MAP_DRUG_DOSE_INDICATION, mpDrugDoseIndication);
			
			WebUTIL.setMapInSession(mpDrugProperties, _request);
			
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
	
	
	public static void saveOtherInstruction(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List otherInstructionList=new ArrayList();
		try{
			 //UserVO userVO = getUserVO(_request);
			 String[] otherInstructionId=_fb.getOtherInstructionExtId();
			 //String[] otherInstruction=_fb.getOtherInstruction();
			 //String[] activity=_fb.getActivityExt();
			 String[] activityId=_fb.getActivityExtId();
			 List selOtherInstList=new ArrayList();
			 List selActivityList=new ArrayList();
			 session.removeAttribute(OpdConfig.PREV_OTHER_INSTRUCTION_LIST);
			 session.removeAttribute(OpdConfig.PREV_OTHER_ACTIVITY_LIST);
			 
			 List allInstList=(List)session.getAttribute(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER);
			 List allActivity=(List)session.getAttribute(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER);
			 
			 if(otherInstructionId!=null)
			 {
				 for(int i=0;i<otherInstructionId.length;i++)
				 {
					PatExtTreatmentDetailVO extVO = new PatExtTreatmentDetailVO();
					
					extVO.setExtTreatmentId(otherInstructionId[i]);
					
					for(int j=0;j<allInstList.size();j++)
					{
						Entry obj=(Entry)allInstList.get(j);
						if(otherInstructionId[i].equals(obj.getValue()))
						{
							extVO.setExtTreatmentName(obj.getLabel());
						}
					}
					//extVO.setExtTreatmentName(otherInstruction[i]);
					extVO.setTreatmentType(OpdConfig.OTHER_INSTRUCTION);
					
					otherInstructionList.add(extVO);
					selOtherInstList.add(extVO);
					WebUTIL.setAttributeInSession(_request, OpdConfig.PREV_OTHER_INSTRUCTION_LIST, selOtherInstList);
					
					
				 }
			 }
			 
			 if(activityId!=null)
			 {
				 for(int i=0;i<activityId.length;i++)
				 {
					PatExtTreatmentDetailVO extVO = new PatExtTreatmentDetailVO();
					
					extVO.setExtTreatmentId(activityId[i]);
					
					for(int j=0;j<allActivity.size();j++)
					{
						Entry obj=(Entry)allActivity.get(j);
						if(activityId[i].equals(obj.getValue()))
						{
							extVO.setExtTreatmentName(obj.getLabel());
						}
					}
					
					//extVO.setExtTreatmentName(activity[i]);
					extVO.setTreatmentType(OpdConfig.ONE_TIME_ACTIVITY);
					
					selActivityList.add(extVO);
					otherInstructionList.add(extVO);
					WebUTIL.setAttributeInSession(_request, OpdConfig.PREV_OTHER_ACTIVITY_LIST, selActivityList);
				 }
			 }
			 
			
			 
			 session.setAttribute(OpdConfig.OTHER_INSTRUCTION_LIST_FOR_SAVE, otherInstructionList);
			 //session.setAttribute(OpdConfig.PREV_OTHER_INST_AND_ACTIVITY_LIST, otherInstructionList);	
			
			objStatus.add(Status.NEW);
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
	
	
	public static void getAllInstructionAndActivity(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		
		try{
			 //UserVO userVO = getUserVO(_request);
			
			 List allInstList=(List)session.getAttribute(OpdConfig.ALL_OTHER_INSTRUCTION_LIST_BY_GENDER);
			 List allActivityList=(List)session.getAttribute(OpdConfig.ALL_ONE_TIME_ACTIVITY_LIST_BY_GENDER);
			
			 WebUTIL.setAttributeInSession(_request, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, allInstList);
			 WebUTIL.setAttributeInSession(_request, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, allActivityList);
			 
			objStatus.add(Status.NEW);
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
	
	public static void getDeptWiseInstructionAndActivity(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		
		try{
			 //UserVO userVO = getUserVO(_request);
			
			 List deptUnitWiseInstList=(List)session.getAttribute(OpdConfig.OTHER_INSTRUCTION_LIST_BY_DEPTUNITCODE_AND_GENDER);
			 List deptUnitWiseActivityList=(List)session.getAttribute(OpdConfig.ONE_TIME_ACTIVITY_LIST_BY_DEPTUNITCODE_AND_GENDER);
			
			 WebUTIL.setAttributeInSession(_request, OpdConfig.OTHER_INSTRUCTION_LIST_FOR_BOTH, deptUnitWiseInstList);
			 WebUTIL.setAttributeInSession(_request, OpdConfig.ONE_TIME_ACTIVITY_LIST_FOR_BOTH, deptUnitWiseActivityList);
			 
			objStatus.add(Status.NEW);
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
	
	public static void deleteDrugRow(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		
		try{
			 //UserVO userVO = getUserVO(_request);
			
			 List lstDrugTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);
			 List rxContinueList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_RXCONTINUE_DRUG_DETAIL_LIST);
			 if(rxContinueList==null)
			 {
				 rxContinueList=new ArrayList();
			 }
			 List prevPatDrugTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			 Map drugScheduleMap=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			 
			 int drugRowsLength = Integer.parseInt(_fb.getDrugDetailRows());
			 int index=drugRowsLength-1;
			 String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
			 
			// if(!_fb.getSelDrugId()[index].equals("0 ") && Integer.parseInt(_fb.getIndexDelete())!=index)
			 if(!_fb.getSelDrugId()[index].equals("0 "))
			 {
				 	PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
					vo.setDrugId(_fb.getSelDrugId()[index]+"#"+_fb.getSelDrugItemTypeId()[index]);
					vo.setDrugName(_fb.getSelDrugName()[index]);
					vo.setDoseId(_fb.getSelDoseId()[index]);
					vo.setDoseName(_fb.getSelDoseName()[index]);
					vo.setFrequencyId(_fb.getSelFrequencyId()[index]);
					vo.setDays(_fb.getSelDays()[index]);
					vo.setStartDate(_fb.getSelStartDay()[index]);
					vo.setRemarks(_fb.getSelInstructions()[index]);
					vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
					//vo.setIsEmptyStomach(isEmptyStomach[index]);
					vo.setDrugRouteId(_fb.getSelDrugRouteId()[index]);
					vo.setDrugRouteName(_fb.getSelDrugRouteName()[index]);
					vo.setQuantity(_fb.getQuantity()[index]);
					vo.setSelStock(_fb.getSelStock()[index]);
					Boolean flag=false;
					//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
					Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
					while(removeBlankRowItr.hasNext())
					{
						PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
						if(patDrugTreatmentDetailVO.getDays().equals(""))
						{
							removeBlankRowItr.remove();
							flag=true;
							//lstDrugTreatmentDtl.add(vo);
						}
					}
					if(flag)
					{
						lstDrugTreatmentDtl.add(vo);
					}
					
					//lstDrugTreatmentDtl.add(vo);
			 }
			 	
			 Iterator itr=lstDrugTreatmentDtl.iterator();
			 int count=0;
			 PatDrugTreatmentDetailVO rxVO=null;
			 
			 while(itr.hasNext())
			 {
				 PatDrugTreatmentDetailVO treatVO=(PatDrugTreatmentDetailVO)itr.next();
				 if(count==Integer.parseInt(_fb.getIndexDelete()))
				{
					Iterator rxItr=rxContinueList.iterator();
					while(rxItr.hasNext())
					{
						rxVO=(PatDrugTreatmentDetailVO)rxItr.next();
						if(treatVO.getRxContinueFlag().equals("modify") && treatVO.getDrugId().split("#")[0].equals(rxVO.getDrugId()))
						{
							rxItr.remove();
							prevPatDrugTreatmentDtlVOList.add(rxVO);
							drugScheduleMap.remove(rxVO.getDrugId());
						}
					}
										
					itr.remove();
					break;
				}
				count++;
			}
			 
			 Integer noOfRows=Integer.parseInt(_fb.getDrugDetailRows())-1;
			 _fb.setDrugDetailRows(noOfRows.toString());
			 _fb.setActiveTab("tabDrugAdvice");
			 
			 WebUTIL.setAttributeInSession(_request, OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			 
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
	
	
	public static void deleteExtRow(PatientTreatmentDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		
		try{
			 //UserVO userVO = getUserVO(_request);
			
			 List lstExtTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST);
			 
			 int extRowsLength = Integer.parseInt(_fb.getExtDrugDetailRows());
			 int index=0;
			index=extRowsLength-1;
			 
			// if(!_fb.getSelDrugId()[index].equals("0 ") && Integer.parseInt(_fb.getIndexDelete())!=index)
			 if(!_fb.getSelExtTreatmentName()[index].equals(""))
			 {
				 PatExtTreatmentDetailVO vo = new PatExtTreatmentDetailVO();
					
					//vo.setDoseId(_fb.getSelExtDoseId()[i]);
					//vo.setDoseName(_fb.getSelExtDoseName()[i]);
					vo.setFrequencyId(_fb.getSelExtFrequencyId()[index]);
					vo.setDays(_fb.getSelExtDays()[index]);
					vo.setStartDate(_fb.getSelExtStartDay()[index]);
					vo.setRemarks(_fb.getSelExtInstructions()[index]);
					vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
					vo.setExtTreatmentId(_fb.getSelExtTreatmentId()[index]);
					vo.setExtTreatmentName(_fb.getSelExtTreatmentName()[index]);
					
					
					Boolean flag=false;
					//removing the blank row from lstExtTreatmentDtl bcz user can fill or not fill the last row
					Iterator<PatExtTreatmentDetailVO> removeBlankRowExtItr=lstExtTreatmentDtl.iterator();
					while(removeBlankRowExtItr.hasNext())
					{
						PatExtTreatmentDetailVO patExtTreatmentDetailVO=removeBlankRowExtItr.next();
						if(patExtTreatmentDetailVO.getDays().equals(""))
						{
							removeBlankRowExtItr.remove();
						}
					}
					if(flag)
					{
						lstExtTreatmentDtl.add(vo);
					}
					
					//lstDrugTreatmentDtl.add(vo);
			 }
			 	
			 Iterator itr=lstExtTreatmentDtl.iterator();
			 int count=0;
			 
			 while(itr.hasNext())
			 {
				 PatExtTreatmentDetailVO extTreatVO=(PatExtTreatmentDetailVO)itr.next();
				if(count==Integer.parseInt(_fb.getIndexDelete()))
				{
					itr.remove();
					break;
				}
				count++;
			}
			 Integer noOfRows=Integer.parseInt(_fb.getExtDrugDetailRows())-1;
			 _fb.setExtDrugDetailRows(noOfRows.toString());
			 _fb.setActiveTab("tabLAAdvice");
			 
			 WebUTIL.setAttributeInSession(_request, OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST, lstExtTreatmentDtl);
			 
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
	
	public static void getDrugListDetail(PatientTreatmentDetailFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		try
		{
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			List drugListDetailList=PatientTreatmentDetailDATA.getParticularDrugListDtl(fb.getDrugListId(),getUserVO(_rq));
			
			session.setAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL, drugListDetailList);
			fb.setActivatePopUpTab("tabDrugProfileSearch");
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
	
	public static void populateDrugList(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List lstDrugTreatmentDtl=null;
		try
		{
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			//setting last drug row
			lstDrugTreatmentDtl=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST);
			 
			 int drugRowsLength = Integer.parseInt(_fb.getDrugDetailRows());
			 int index=drugRowsLength-1;
			 String[] isEmptyStomach=_fb.getEmptyStomachIndexArray().split("#");
			 
			 if(!_fb.getSelDrugId()[index].equals("0"))
			 {
				 	PatDrugTreatmentDetailVO vo = new PatDrugTreatmentDetailVO();
					vo.setDrugId(_fb.getSelDrugId()[index]+"#"+_fb.getSelDrugItemTypeId()[index]);
					vo.setDrugName(_fb.getSelDrugName()[index]);
					vo.setDoseId(_fb.getSelDoseId()[index]);
					vo.setDoseName(_fb.getSelDoseName()[index]);
					vo.setFrequencyId(_fb.getSelFrequencyId()[index]);
					vo.setDays(_fb.getSelDays()[index]);
					vo.setStartDate(_fb.getSelStartDay()[index]);
					vo.setRemarks(_fb.getSelInstructions()[index]);
					vo.setRxContinueFlag(_fb.getRxContinueFlag()[index]);
					if(isEmptyStomach.length>index) vo.setIsEmptyStomach(isEmptyStomach[index]);
					vo.setDrugRouteId(_fb.getSelDrugRouteId()[index]);
					vo.setDrugRouteName(_fb.getSelDrugRouteName()[index]);
					vo.setQuantity(_fb.getQuantity()[index]);
					
					lstDrugTreatmentDtl.add(vo);
					
					//removing the blank row from lstDrugTreatmentDtl bcz user can fill or not fill the last row
					Iterator<PatDrugTreatmentDetailVO> removeBlankRowItr=lstDrugTreatmentDtl.iterator();
					while(removeBlankRowItr.hasNext())
					{
						PatDrugTreatmentDetailVO patDrugTreatmentDetailVO=removeBlankRowItr.next();
						if(patDrugTreatmentDetailVO.getDays().equals(""))
						{
							removeBlankRowItr.remove();
						}
					}
					
			 }
			
			List drugListDetailList=PatientTreatmentDetailDATA.getParticularDrugListDtl(_fb.getDrugListId(),getUserVO(_rq));
			
			
			for(int i=0;i<drugListDetailList.size();i++)
			{
				PatDrugTreatmentDetailVO vo=_fb.resetVO(new PatDrugTreatmentDetailVO());
				PatDrugTreatmentDetailVO profileVO=(PatDrugTreatmentDetailVO)drugListDetailList.get(i);
				
				
				vo.setDrugId(profileVO.getDrugId());
				vo.setDrugName(profileVO.getDrugName());
				vo.setDoseId(profileVO.getDoseId());
				vo.setDoseName(profileVO.getDoseName());
				vo.setFrequencyId(profileVO.getFrequencyId());
				vo.setDays(profileVO.getDays());
				//vo.setStartDate(_fb.getSelStartDay()[i]);
				if(profileVO.getRemarks()!=null)
				{
					vo.setRemarks(profileVO.getRemarks());
				}
				else
				{
					vo.setRemarks("");
				}
				
				//vo.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
				vo.setIsEmptyStomach(profileVO.getIsEmptyStomach());
				//vo.setDrugRouteId(_fb.getSelDrugRouteId()[i]);
				//vo.setDrugRouteName(_fb.getSelDrugRouteName()[i]);
				//vo.setQuantity(_fb.getQuantity()[i]);
				lstDrugTreatmentDtl.add(vo);
			
			}
			lstDrugTreatmentDtl.add(_fb.resetVO(new PatDrugTreatmentDetailVO()));
			_fb.setDrugDetailRows(new Integer(lstDrugTreatmentDtl.size()).toString());
			session.setAttribute(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			
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
	
	
	public static StringBuffer setEssentialsForTreatmentDetails(PatientTreatmentDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		UserVO userVO=ControllerUTIL.getUserVO(_rq);
		HttpSession session = WebUTIL.getSession(_rq);
		//List treatmentListDetailList=null;
		StringBuffer strBuff= new StringBuffer();
		try
		{

			strBuff.append("{header:'Patient Treatment Detail',data:[");
			List treatmentListDetailList=PatientTreatmentDetailDATA.treatmentDetailList(_fb.getPatCrNo(),_fb.getEpisodeCode(),userVO);
			for(int i=0;i<treatmentListDetailList.size();i++)
			{
				PatDrugTreatmentDetailVO profileVO=(PatDrugTreatmentDetailVO)treatmentListDetailList.get(i);
				strBuff.append("{header:'Drug Name',value:'" +profileVO.getDrugName()  + "'},{header:'Dose',value:'" +profileVO.getDoseName()  + "'},{header:'Frequency',value:'" + profileVO.getFrequencyName()+ "'}");
			
			}
			strBuff.append("]}");
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
		
		return strBuff;
	}
	
	
	
}
