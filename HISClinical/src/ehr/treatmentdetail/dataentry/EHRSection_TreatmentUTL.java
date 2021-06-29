/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentdetail.dataentry;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.io.IOException;
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
import opd.transaction.controller.fb.PatientTreatmentDetailFB;
import ehr.EHRConfig;
import ehr.diagnosis.dataentry.EHRSection_DiagnosisDATA;
import ehr.diagnosis.dataentry.EHRSection_DiagnosisFB;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.treatmentdetail.dataentry.EHRSection_TreatmentFB;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import emr.vo.EHR_PatEncounterDetailsVO;
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
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.DrugSheduleDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;

public class EHRSection_TreatmentUTL extends ControllerUTIL
{
	 
	/**Setting Patient Treatment Detail Essentials
	 * @param _fb
	 * @param _rq
	 */
	
	
	// Modified on 06.04.2016 as per new CIMS Data Model Integration in Treatment Plan
	public static void setEssentials(EHRSection_TreatmentFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		
		Map mpEssentials = null;
		List<EHRSection_TreatmentVO> lstDrugTreatmentDtl = null;
		
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			// Getting Patient Demographic Detail 
			//InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq); //////////////////////////////////coomented as this is only for OPD  as on date 28.3.2017
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
			
			// Fetching Patient Detail from Desk
			PatientDetailVO voDP = new PatientDetailVO();
			HelperMethods.populate(voDP, _fb);
				
				if(voDP.getPatIsPregnant()!="" && voDP.getPatIsPregnant()==OpdConfig.PAT_PREGNANT_YES)
					_fb.setIsPregnantFlag(OpdConfig.PAT_PREGNANT_YES);
				else
					_fb.setIsPregnantFlag(OpdConfig.PAT_PREGNANT_NO);
			
			
			
			mpEssentials=EHRSection_TreatmentDATA.getPatTreatmentDetailEssential(_fb.getPatCrNo(),_fb.getAdviceType(),_fb.getEpisodeCode(),voDP.getDepartmentUnitCode(),ptaientDetailVO.getPatGenderCode(),voDP, userVO);
			
			
			
			
			/**********************************set essential for drug detail***********************************/
			
			
			// Setting Initial Drug Detail List
			lstDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
			String maxEntryDate=(String)mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO);
			if(maxEntryDate==null)
			{
				List defaultDrugProfileDrugLst=(List)mpEssentials.get(OpdConfig.DEFAULT_DRUG_PROFILE_DRUG_LIST);
				for(int i=0;i<defaultDrugProfileDrugLst.size();i++)
				{
					EHRSection_TreatmentVO vo=_fb.resetVO(new EHRSection_TreatmentVO());
					EHRSection_TreatmentVO profileVO=(EHRSection_TreatmentVO)defaultDrugProfileDrugLst.get(i);
					
					
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
					lstDrugTreatmentDtl.add(vo);
				}
				
			}
						
			
		
						
			lstDrugTreatmentDtl.add(_fb.resetVO(new EHRSection_TreatmentVO()));
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			
						
			//getting last visit(drug detail)
			List<EHRSection_TreatmentVO> prevAll = (List<EHRSection_TreatmentVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST);
			/*if(prevAll.size() > 0)
			{
				_fb.setIsSetTREATMENT("1");
				Map essentialMap = new HashMap();
				essentialMap.put(EHRConfig.EHR_TREATMENT_ESSENTIAL_SAVE, prevAll);
				session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, essentialMap);
				
				//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, prevAll);	
					
			}*/
			
			if(maxEntryDate==null)
			{
				_fb.setPrescriptionDate("");	
			}
			else
			{
				_fb.setPrescriptionDate(maxEntryDate);	
			}
			
			
			List<EHRSection_TreatmentVO> prevLast = new ArrayList<EHRSection_TreatmentVO>();
			for(EHRSection_TreatmentVO vo : prevAll)
			{
				//if(vo.getEntryDate().equals(maxEntryDate))
				//{
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
			//}
			
			//comparing end date with system date(for revoke button) 
			Iterator<EHRSection_TreatmentVO> itr = prevLast.iterator();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			while(itr.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO = itr.next();
				Date sysDate=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				Date endDate = sysDate;
				if(EHRSection_TreatmentVO.getEndDate()!=null)
					endDate=df.parse(EHRSection_TreatmentVO.getEndDate());
				if(endDate.before(sysDate))
				{
					EHRSection_TreatmentVO.setDate("before");
					
					//String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
					DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
					//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
					Calendar calendar1=Calendar.getInstance();
					Calendar calendar2=Calendar.getInstance();
					calendar1.setTime(sinmpledf.parse(EHRSection_TreatmentVO.getStartDate()));
					calendar2.setTime(sinmpledf.parse(EHRSection_TreatmentVO.getEndDate()));
					long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
					String advDays=String.valueOf(noOfDays);
					Integer days=Integer.parseInt(advDays);
					
					EHRSection_TreatmentVO.setIntakeDays(days.toString());
					
					
										
				}
				else
				{
					EHRSection_TreatmentVO.setDate("after");
					String advDays="0";
					if(EHRSection_TreatmentVO.getStartDate()!=null)
					{
						String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
						DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
						//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
						Calendar calendar1=Calendar.getInstance();
						Calendar calendar2=Calendar.getInstance();
						calendar1.setTime(sinmpledf.parse(EHRSection_TreatmentVO.getStartDate()));
						calendar2.setTime(sinmpledf.parse(sysadteStr));
						long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
						advDays=String.valueOf(noOfDays);
					}
					Integer days=Integer.parseInt(advDays);
					
					if(days>0)
					{
						EHRSection_TreatmentVO.setIntakeDays(days.toString());
					}
					else
					{
						EHRSection_TreatmentVO.setIntakeDays("0");
					}
					
				}
				if(EHRSection_TreatmentVO.getRemarks()==null)
				{
					EHRSection_TreatmentVO.setRemarks("");
				}
			}
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
					
			
			//for removing  revoked drug 
			Iterator<EHRSection_TreatmentVO> drugItr = prevLast.iterator();
			while(drugItr.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO = drugItr.next();
				if(EHRSection_TreatmentVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					drugItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
			
			if(prevLast!=null && prevLast.size() > 0)
			{
				_fb.setIsSetTREATMENT("1");
				
				//Map essentialMap=(HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				//essentialMap.put(EHRConfig.EHR_TREATMENT_ESSENTIAL_SAVE, prevLast);
				//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, essentialMap);
				
				EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				patencountervo.setListSaveAllTreatment(prevLast);
				WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
				
					
			}
			
			
			
			//getting today visit(drug detail)
			List<EHRSection_TreatmentVO> todayDrugDetailList = new ArrayList<EHRSection_TreatmentVO>();
			Iterator<EHRSection_TreatmentVO> prevAllDrugDetailItr=prevAll.iterator();
			while(prevAllDrugDetailItr.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO=prevAllDrugDetailItr.next();
				if(EHRSection_TreatmentVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()) )
				{
					
					todayDrugDetailList.add(EHRSection_TreatmentVO);
					
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST, todayDrugDetailList);
			
			//concatenating drugId with itemtypeId
			//List<DrugDoseVO> drugDoseVOList = (List<DrugDoseVO>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			List<Entry> drugList=(List<Entry>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator<EHRSection_TreatmentVO> iter =lstDrugTreatmentDtl.iterator();
			
			while(iter.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO =iter.next();
				Iterator<Entry> itera =drugList.iterator();
				while(itera.hasNext())
				{
					Entry obj=itera.next();
					String[] itemIdAndItemTypeId=obj.getValue().split("#");
					String itemId=itemIdAndItemTypeId[0];
					String ItemTypeId=itemIdAndItemTypeId[1];
					if(EHRSection_TreatmentVO.getDrugId().equals(itemId))
					{
						if(EHRSection_TreatmentVO.getRemarks()==null)
						{
							EHRSection_TreatmentVO.setRemarks("");
						}
						EHRSection_TreatmentVO.setDrugId(EHRSection_TreatmentVO.getDrugId()+"#"+ItemTypeId);					
					}
				}
			}
			
			
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			_fb.setDrugDetailRows(Integer.toString(lstDrugTreatmentDtl.size()));
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
			if(_fb.getAdviceType().equals(Config.TREATMENT_ADVICE_TYPE_DEFUALT))
			{
			EHRVOUtility.setTreatmentDetailVO(_rq, _fb.getPatCrNo(), prevLast); //Added by Vasu on 2.Nov.2018
			}
			else
			{
				EHRVOUtility.setDrugAdviceVO(_rq, _fb.getPatCrNo(), prevLast);//Added by Vasu on 17.Dec.18
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
	
	

	public static void getDrugAdviceAlerts(EHRSection_TreatmentFB fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_rq);
		
		String result = "";
		
		//String itemTypeId = _rq.getParameter("drugItemType");
		String drugId = _rq.getParameter("drugId");
		String drugName = _rq.getParameter("drugName");
		String patcrNo = _rq.getParameter("patcrNo");
		String departmentUnitCode=_rq.getParameter("departmentUnitCode");
		try
		{
						
			EHRSection_TreatmentVO patDrugDtlVO=new EHRSection_TreatmentVO();
			patDrugDtlVO.setPatCrNo(patcrNo);
			patDrugDtlVO.setDrugId(drugId);
			patDrugDtlVO.setDrugName(drugName);
			patDrugDtlVO.setDepartmentUnitCode(departmentUnitCode);
			
			result=EHRSection_TreatmentDATA.getdrugAdviceAlerts(patDrugDtlVO, userVO);  
			//System.out.println(result);
			_resp.getWriter().write(result.toString());
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

	

	
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentFB _fb) 
	{
		String flag=EHRSection_TreatmentUTL.saveTreatmentDetails(_rq,response,_fb);
		try
		{
			writeResponse(response, flag);
		}
		catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();
			
		}
	}
	
	
	// Saving Treatment Detail 
	public static String saveTreatmentDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentFB _fb) 
	{
		
		String isSave = "true";
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		List<EHRSection_TreatmentVO> lstDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
		List<EHRSection_TreatmentVO> revokedDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
		int currentDiagLength = 0;
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
		String sysadateForTodayVisit=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
		List <EHRSection_TreatmentVO> lstDrugTreatmentDtlAllSave= new ArrayList<EHRSection_TreatmentVO>(); // for print purpose (populating all data into seesion  without page refresh)
		try
		{
			
			DrugFrequencyMstVO[] drugFreqMstVoArray=(DrugFrequencyMstVO[])session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			String freqValue="";
				
			

			HelperMethods.populatetToNullOrEmpty(selectedPatientVO, _fb);
			//currentDiagLength=_fb.getDiagonisticTypeCode().length;		
			currentDiagLength=_fb.getTreatmentRecordStatus().length;
			
			
			for (int i = 0, k=0; i < currentDiagLength; i++)
			{
				
				/**********saving new treatment details**********/
				//Added by Vasu on 26.Nov.2018 for Advice on Discharge Save
				/*if(_fb.getHmode().equalsIgnoreCase("ADVICEONDISCHARGE_SAVE"))
				{
					//String[] treatmentRecordStatusForAdviceOnDischarge;
					currentDiagLength = 1;
				}*/
				//End Vasu 
				if(_fb.getTreatmentRecordStatus()[i].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED))
				{
					if(_fb.getSelDrugName()!=null && _fb.getSelDrugName().length>i && !_fb.getSelDrugName()[i].isEmpty())
					{
						EHRSection_TreatmentVO drugVO = new EHRSection_TreatmentVO();
						
						//Added by Vasu on 17.Dec.2018 to pass entry mode for Advice on Discharge Drugs
						if(_fb.getHmode().equalsIgnoreCase("PATCLINICALDOC_ENC_MED_ADV_SAVE"))
						{
							drugVO.setEntryMode(OpdConfig.ENTRY_MODE_FOR_DISCHARGE_ADVICE_DRUG); //3 - In case of Advice on discharge
							_fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DISCHARGE);
						}
						else
						{
							drugVO.setEntryMode(OpdConfig.ENTRY_MODE); //1--In case of OPD and IPD
							_fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
						}
						drugVO.setDoseQty(_fb.getQuantity()[i]);
						drugVO.setRequiredQty(_fb.getQuantity()[i]);
						drugVO.setIssueQty("0");
						drugVO.setDrugId(_fb.getSelDrugId()[i]);
						//drugVO.setDrugName(_fb.getSelDrugName()[i]);
						//drugVO.setDoseId(_fb.getSelDoseId()[i].split("\\^")[0]);
						drugVO.setDoseName(_fb.getSelDoseName()[i]);
						drugVO.setFrequencyId(_fb.getSelFrequencyId()[i]);
						drugVO.setFrequencyName(_fb.getSelFrequencyName()[i]);
						drugVO.setDays(_fb.getSelDays()[i]);
						drugVO.setStartDate(_fb.getSelStartDay()[i]);
						drugVO.setRemarks(_fb.getSelInstructions()[i]);
						drugVO.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
						drugVO.setEndDate("");
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
						drugVO.setDrugBrandName(_fb.getSelDrugName()[i]);
						drugVO.setDrugTypeID(_fb.getSelDrugItemTypeId()[i]);
						drugVO.setRxContinue(OpdConfig.NEW_RECORD);
						drugVO.setTodayVisitFlag("yes");
						//drugVO.setTodayVisitFlag(_fb.getTodayVisitFlag()[i]);
						drugVO.setSerialNo("");
						drugVO.setEntryDate(sysadateForTodayVisit);
						if(_fb.getAdviceType()!=null)
						drugVO.setAdviceType(_fb.getAdviceType());
					
						//_fb.setTreatmentRecordStatus()[i]=EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED;
						lstDrugTreatmentDtl.add(drugVO);
						
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
					
				}
				}
				
				/*********** saving revoke treatment details*************/
				if(_fb.getTreatmentRecordStatus()[i].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_TOREVOKE))
				{	
								EHRSection_TreatmentVO episodeRevokeDiaVO=new EHRSection_TreatmentVO();
								HelperMethods.populatetToNullOrEmpty(episodeRevokeDiaVO, selectedPatientVO);
							  	episodeRevokeDiaVO.setDrugId(_fb.getPrevDrugId()[k]);
								episodeRevokeDiaVO.setDrugName(_fb.getPrevDrugName()[k]);
								episodeRevokeDiaVO.setDoseId(_fb.getPrevDoseId()[k]);
								episodeRevokeDiaVO.setDoseName(_fb.getPrevDoseName()[k]);
								episodeRevokeDiaVO.setFrequencyId(_fb.getPreviousFrequencyId()[k]);
								episodeRevokeDiaVO.setDays(_fb.getPrevDays()[k]);
								episodeRevokeDiaVO.setStartDate(_fb.getPrevStartDate()[k]);
								episodeRevokeDiaVO.setDoseQty(_fb.getPrevDoseQty()[k]);
							//	episodeRevokeDiaVO.setRemarks(_fb.getPrevInstructions()[i]);
							//	episodeRevokeDiaVO.setRxContinueFlag(_fb.getPrevRxContinueFlag()[i]);
								episodeRevokeDiaVO.setEndDate(_fb.getPrevEndDate()[k]);
								episodeRevokeDiaVO.setDrugRouteId(_fb.getPrevRouteId()[k]);
								episodeRevokeDiaVO.setDrugRouteName(_fb.getPrevRouteName()[k]);
								episodeRevokeDiaVO.setDrugAdminId(_fb.getPrevDrugAdminId()[k]);
								episodeRevokeDiaVO.setDrugAdminName(_fb.getPrevDrugAdminName()[k]);
								episodeRevokeDiaVO.setDrugBrandId(_fb.getPrevDrugBrandId()[k]);
								//episodeRevokeDiaVO.setDrugTypeID(_fb.getPrevDrugItemTypeId()[i]);
								episodeRevokeDiaVO.setRxContinue(OpdConfig.REVOKE);
								episodeRevokeDiaVO.setEpisodeCode(_fb.getEpisodeCode());
								episodeRevokeDiaVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
								episodeRevokeDiaVO.setPatCrNo(_fb.getPatCrNo());
								episodeRevokeDiaVO.setAdmissionNo(_fb.getAdmissionNo());
								episodeRevokeDiaVO.setSysDate(sysadteString);
								episodeRevokeDiaVO.setRevokeStatus("revoke");
								
								//here we checking that this record is today visit record 
								if(episodeRevokeDiaVO.getEntryDate().equals(sysadateForTodayVisit))
								{
									episodeRevokeDiaVO.setTodayVisitFlag("yes");
								}
								else
								{
									episodeRevokeDiaVO.setTodayVisitFlag("no");
								}
								
								episodeRevokeDiaVO.setRequiredQty("0");
								episodeRevokeDiaVO.setIssueQty("0");
								
							//	episodeRevokeDiaVO.setRevokeRemarks(_fb.getRevokeRemarks()[i]);
								episodeRevokeDiaVO.setRevokeRemarks("");
								episodeRevokeDiaVO.setSerialNo(_fb.getSerealNo()[k]);
								revokedDrugTreatmentDtl.add(episodeRevokeDiaVO);
								k++;
				}
				
				
				if(_fb.getTreatmentRecordStatus()[i].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED))
				{
					k++;
				}
				
				
			}
			
			
			/**************************saving drug schedule**************/
			
			
			
			List drugAdminList=new ArrayList();	
			Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if(drugSchedule==null)
			{
				drugSchedule=new HashMap();
			}
			
			DrugFrequencyMstVO[] arr = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			DrugFrequencyMstVO drugFreqVO = null;
			
			EHRSection_TreatmentVO drugTreatDtlVO = new EHRSection_TreatmentVO();
			
			Iterator drugScheduleItr=lstDrugTreatmentDtl.iterator();
			while(drugScheduleItr.hasNext())
			{
				drugTreatDtlVO=(EHRSection_TreatmentVO)drugScheduleItr.next();
				if(!drugSchedule.containsKey(drugTreatDtlVO.getDrugId() +"#"+ drugTreatDtlVO.getFrequencyId()+"#"+drugTreatDtlVO.getDoseId()))
				{
					List scheduleLst=new ArrayList();
					for(int t=0;t<arr.length;t++)
					{
						if(arr[t].getFrequencyId().equals(drugTreatDtlVO.getFrequencyId()))
						{
							drugFreqVO=arr[t];
							break;
						}
					}
			
				
							
			int freqCount=Integer.parseInt(freqValue);
			String doseName="";
			for(int i=0;i<freqCount;i++)
			{
				String scheduleSlNo=new Integer(i).toString();
				String[] dosetime=drugFreqVO.getFirstDoseTime().split(":");
				String[] durationtime=drugFreqVO.getDoseDuration().split(":");
				int dosetimehr=Integer.parseInt(dosetime[0])+Integer.parseInt(durationtime[0])*i;
				int dosetimemin=Integer.parseInt(dosetime[1])+Integer.parseInt(durationtime[1])*i;
				String hr="",min="";
				if(dosetimehr<10)
					hr="0"+Integer.toString(dosetimehr);
				else
					hr=Integer.toString(dosetimehr);
				if(dosetimemin<10)
					min="0"+Integer.toString(dosetimemin);
				else
					min=Integer.toString(dosetimemin);
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
				
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			List<EHRSection_TreatmentVO> essentialList=(List<EHRSection_TreatmentVO>) patencountervo.getListSaveAllTreatment();
			
			//getting final list for print purposes
			if(essentialList!=null)
			lstDrugTreatmentDtlAllSave.addAll(essentialList);   // getting all previous data
			
			//if(revokedDrugTreatmentDtl!=null)  
			//lstDrugTreatmentDtlAllSave.removeAll(revokedDrugTreatmentDtl);     // removing revoked data
			
			
	
			if(revokedDrugTreatmentDtl!=null)  {
			for (Iterator<EHRSection_TreatmentVO> revokeitr = revokedDrugTreatmentDtl.iterator(); revokeitr.hasNext();) 
			{
			EHRSection_TreatmentVO revokedrug = revokeitr.next();
			for (Iterator<EHRSection_TreatmentVO> iterator = lstDrugTreatmentDtlAllSave.iterator(); iterator.hasNext();) {
				EHRSection_TreatmentVO essentiallist = iterator.next();
			    if (essentiallist.getDrugId().equals(revokedrug.getDrugId()))
			    {
			        // Remove the current element from the iterator and the list.
			        iterator.remove();
			    }
			}
			}}
			
			if(lstDrugTreatmentDtl!=null)
			lstDrugTreatmentDtlAllSave.addAll(lstDrugTreatmentDtl);   // adding all new data 
		
			
			patencountervo.setListSaveAllTreatment(lstDrugTreatmentDtlAllSave); // setting final list
			WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
		
			
			EHRSection_TreatmentDATA.savePatTreatmentDetail(lstDrugTreatmentDtl,revokedDrugTreatmentDtl, drugSchedule,drugAdminList,getUserVO(_rq));
			
			objStatus.add(Status.DONE, "Record Saved", "");
		}

		
		catch (HisDataAccessException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return isSave;
		
	}
	
	public static void saveDrugAdministration(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentFB _fb)
	{
		String isSave = "true";
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		List<EHRSection_TreatmentVO> lstDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
		List<EHRSection_TreatmentVO> revokedDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
		int currentDiagLength = 0;
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
		String sysadateForTodayVisit=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
		
		try
		{
		/**********************saving drug administration detail in case of IPD ****************************/
		
			
			List remainDrudIdLst=new ArrayList();
			Iterator remainingItr=prevRemainingDrugLst.iterator();
			while(remainingItr.hasNext())
			{
				PatDrugTreatmentDetailVO vo =(PatDrugTreatmentDetailVO)remainingItr.next();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
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
					
					
				}
			}
			
			//saving schedule for remaining drug
			
			 prevLastDrugTreatDtlVOList=null;
			 
			 episodeVisitNo="";
			 entryDate="";
			 //String slNo="";
			 
			prevLastDrugTreatDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			drugScheduleVOLst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_SCHEDULE);
			
					
				for(int i=0;i<remainDrudIdLst.size();i++)
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
				}
		
			
			
			////saving
		
		List allDrugList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
		
		List drugAdminList=new ArrayList();	
		
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			for(int i=0;i<lstDrugTreatmentDtl.size();i++)
			{
				EHRSection_TreatmentVO drugDtlVO=lstDrugTreatmentDtl.get(i);
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
		}}
					catch (HisDataAccessException e)
					{
						isSave = "false";
						objStatus.add(Status.ERROR_DA, e.getMessage(), "");
					}
					catch (HisApplicationExecutionException e)
					{
						isSave = "false";
						objStatus.add(Status.ERROR_AE, e.getMessage(), "");
					}
					catch (HisException e)
					{
						isSave = "false";
						objStatus.add(Status.ERROR, e.getMessage(), "");
						System.out.print(e.getMessage());
					}
					catch (Exception e)
					{
						isSave = "false";
						objStatus.add(Status.ERROR_AE, e.getMessage(), "");
						System.out.print(e.getMessage());
					}
					finally
					{
						WebUTIL.setStatus(_rq, objStatus);
					}
			
	}
	

	public static void getDefaultShedule(EHRSection_TreatmentFB _fb, HttpServletRequest _request)
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
	
	public static void saveDrugShedule(EHRSection_TreatmentFB _fb, HttpServletRequest _request)
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
	
	public static void getPrevDrugShedule(EHRSection_TreatmentFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List prevPatDrugTreatmentDtlVOList=new ArrayList();
		try{
			 int selectedDelRow=Integer.parseInt(_fb.getScheduleIndex());
			 prevPatDrugTreatmentDtlVOList=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST);
			 			  
			 Iterator<EHRSection_TreatmentVO> itr = prevPatDrugTreatmentDtlVOList.iterator();
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
				 EHRSection_TreatmentVO _EHRSection_TreatmentVO =itr.next();
				   if(j==selectedDelRow)
					 {		
					     //drugId=_EHRSection_TreatmentVO.getDrugId();
					     //doseId=_EHRSection_TreatmentVO.getDoseId();
					     episodeVisitNo=_EHRSection_TreatmentVO.getEpisodeVisitNo();
					     entryDate=_EHRSection_TreatmentVO.getEntryDate();
					     episodeCode=_EHRSection_TreatmentVO.getEpisodeCode();
					     slNo=_EHRSection_TreatmentVO.getSerialNo();
					     patCrNo=_EHRSection_TreatmentVO.getPatCrNo();
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
	
	
	
	
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
		//	resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
						e.printStackTrace();

		}
	}


   //Added by Vasu on 23.July.2019 for treatment Given save
	public static void saveTreatmentGivenDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentFB _fb)
	{
		
		String isSave = "true";
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		List<EHRSection_TreatmentVO> lstDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
		List<EHRSection_TreatmentVO> revokedDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
		int currentDiagLength = 0;
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
		String sysadateForTodayVisit=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
		List <EHRSection_TreatmentVO> lstDrugTreatmentDtlAllSave= new ArrayList<EHRSection_TreatmentVO>(); // for print purpose (populating all data into seesion  without page refresh)
		try
		{
			
			DrugFrequencyMstVO[] drugFreqMstVoArray=(DrugFrequencyMstVO[])session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			String freqValue="";
				
			

			HelperMethods.populatetToNullOrEmpty(selectedPatientVO, _fb);
			//currentDiagLength=_fb.getDiagonisticTypeCode().length;		
			currentDiagLength=_fb.getTreatmentRecordStatusForTreatmentGiven().length;
			
            
			for (int i = 0, k=0; i < currentDiagLength; i++)
			{
				
				/**********saving new treatment details**********/
				//Added by Vasu on 26.Nov.2018 for Advice on Discharge Save
				/*if(_fb.getHmode().equalsIgnoreCase("ADVICEONDISCHARGE_SAVE"))
				{
					//String[] treatmentRecordStatusForAdviceOnDischarge;
					currentDiagLength = 1;
				}*/
				//End Vasu 
				if(_fb.getTreatmentRecordStatusForTreatmentGiven()[i].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED))
				{
					if(_fb.getSelDrugNameForTreatmentGiven()!=null && _fb.getSelDrugNameForTreatmentGiven().length>i && !_fb.getSelDrugNameForTreatmentGiven()[i].isEmpty())
					{
						EHRSection_TreatmentVO drugVO = new EHRSection_TreatmentVO();
						
						//Added by Vasu on 17.Dec.2018 to pass entry mode for Advice on Discharge Drugs
						if(_fb.getHmode().equalsIgnoreCase("PATCLINICALDOC_ENC_MED_ADV_SAVE"))
						{
							drugVO.setEntryMode(OpdConfig.ENTRY_MODE_FOR_DISCHARGE_ADVICE_DRUG); //3 - In case of Advice on discharge
							_fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DISCHARGE);
						}
						else
						{
							drugVO.setEntryMode(OpdConfig.ENTRY_MODE); //1--In case of OPD and IPD
							_fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
						}
						
						 String startDate = _fb.getSelStartDayForTreatmentGiven()[i];
							
						 SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
						
						
						 Date date1=sdf.parse((String)startDate);
						 SimpleDateFormat sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
						 String strDate = sdf3.format(date1); 
						 startDate = strDate;
						 
						
						//drugVO.setDoseQty(_fb.getQuantity()[i]);
						//drugVO.setRequiredQty(_fb.getQuantity()[i]);
						//drugVO.setIssueQty("0");
						drugVO.setDrugId(_fb.getSelDrugIdForTreatmentGiven()[i]);
						//drugVO.setDrugName(_fb.getSelDrugName()[i]);
						//drugVO.setDoseId(_fb.getSelDoseId()[i].split("\\^")[0]);
						drugVO.setDoseName(_fb.getSelDoseNameForTreatmentGiven()[i]);
						drugVO.setFrequencyId(_fb.getSelFrequencyIdForTreatmentGiven()[i]);
						drugVO.setFrequencyName(_fb.getSelFrequencyNameForTreatmentGiven()[i]);
						drugVO.setDays(_fb.getSelDaysForTreatmentGiven()[i]);
						//drugVO.setStartDate(_fb.getSelStartDay()[i]);
						drugVO.setStartDate(startDate);
						//drugVO.setRemarks(_fb.getSelInstructions()[i]);
						drugVO.setRxContinueFlag(_fb.getRxContinueFlag()[i]);
						drugVO.setEndDate("");
						//setting next visit
						drugVO.setEpisodeCode(_fb.getEpisodeCode());
						drugVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
						drugVO.setPatCrNo(_fb.getPatCrNo());
						drugVO.setAdmissionNo(_fb.getAdmissionNo());
						drugVO.setSysDate(sysadteString);
					//	drugVO.setIsEmptyStomach(isEmptyStomach[i]);  //isEMptyStomach checkbox is removed
						drugVO.setDrugRouteId(_fb.getSelDrugRouteIdForTreatmentGiven()[i]);
						drugVO.setRevokeStatus("false");
						drugVO.setDrugAdminId(_fb.getSelDrugAdminIdForTreatmentGiven()[i]);
						drugVO.setDrugAdminName(_fb.getSelDrugAdminNameForTreatmentGiven()[i]);
						drugVO.setDrugBrandId(_fb.getSelDrugBrandIdForTreatmentGiven()[i]);
						drugVO.setDrugBrandName(_fb.getSelDrugNameForTreatmentGiven()[i]);
						drugVO.setDrugTypeID(_fb.getSelDrugItemTypeIdForTreatmentGiven()[i]);
						drugVO.setRxContinue(OpdConfig.NEW_RECORD);
						drugVO.setTodayVisitFlag("yes");
						//drugVO.setTodayVisitFlag(_fb.getTodayVisitFlag()[i]);
						drugVO.setSerialNo("");
						drugVO.setEntryDate(sysadateForTodayVisit);
						if(_fb.getAdviceType()!=null)
						drugVO.setAdviceType(_fb.getAdviceType());
					
						drugVO.setTreatmentType("TREATMENTGIVEN");
						//_fb.setTreatmentRecordStatus()[i]=EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED;
						lstDrugTreatmentDtl.add(drugVO);
						
						if(drugFreqMstVoArray!=null)
						 {
						for(int j=0;j<drugFreqMstVoArray.length;j++)
						{
							DrugFrequencyMstVO vo=drugFreqMstVoArray[j];
							if(_fb.getSelFrequencyIdForTreatmentGiven()[i].equals(vo.getFrequencyId()))
							{
								freqValue=vo.getFrequency();
							}
						}
						}
					
				}
				}
				
				/*********** saving revoke treatment details*************/
				if(_fb.getTreatmentRecordStatusForTreatmentGiven()[i].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_TOREVOKE))
				{	
								EHRSection_TreatmentVO episodeRevokeDiaVO=new EHRSection_TreatmentVO();
								HelperMethods.populatetToNullOrEmpty(episodeRevokeDiaVO, selectedPatientVO);
							  	episodeRevokeDiaVO.setDrugId(_fb.getPrevDrugIdForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDrugName(_fb.getPrevDrugNameForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDoseId(_fb.getPrevDoseIdForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDoseName(_fb.getPrevDoseNameForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setFrequencyId(_fb.getPreviousFrequencyIdForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDays(_fb.getPrevDaysForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setStartDate(_fb.getPrevStartDateForTreatmentGiven()[k]);
								//episodeRevokeDiaVO.setDoseQty(_fb.getPrevDoseQty()[k]);
							//	episodeRevokeDiaVO.setRemarks(_fb.getPrevInstructions()[i]);
							//	episodeRevokeDiaVO.setRxContinueFlag(_fb.getPrevRxContinueFlag()[i]);
								episodeRevokeDiaVO.setEndDate(_fb.getPrevEndDateForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDrugRouteId(_fb.getPrevRouteIdForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDrugRouteName(_fb.getPrevRouteNameForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDrugAdminId(_fb.getPrevDrugAdminIdForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDrugAdminName(_fb.getPrevDrugAdminNameForTreatmentGiven()[k]);
								episodeRevokeDiaVO.setDrugBrandId(_fb.getPrevDrugBrandIdForTreatmentGiven()[k]);
								//episodeRevokeDiaVO.setDrugTypeID(_fb.getPrevDrugItemTypeId()[i]);
								episodeRevokeDiaVO.setRxContinue(OpdConfig.REVOKE);
								episodeRevokeDiaVO.setEpisodeCode(_fb.getEpisodeCode());
								episodeRevokeDiaVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
								episodeRevokeDiaVO.setPatCrNo(_fb.getPatCrNo());
								episodeRevokeDiaVO.setAdmissionNo(_fb.getAdmissionNo());
								episodeRevokeDiaVO.setSysDate(sysadteString);
								episodeRevokeDiaVO.setRevokeStatus("revoke");
								
								//here we checking that this record is today visit record 
								if(episodeRevokeDiaVO.getEntryDate().equals(sysadateForTodayVisit))
								{
									episodeRevokeDiaVO.setTodayVisitFlag("yes");
								}
								else
								{
									episodeRevokeDiaVO.setTodayVisitFlag("no");
								}
								
								episodeRevokeDiaVO.setRequiredQty("0");
								episodeRevokeDiaVO.setIssueQty("0");
								
							//	episodeRevokeDiaVO.setRevokeRemarks(_fb.getRevokeRemarks()[i]);
								episodeRevokeDiaVO.setRevokeRemarks("");
								episodeRevokeDiaVO.setSerialNo(_fb.getSerealNoForTreatmentGiven()[k]);
								revokedDrugTreatmentDtl.add(episodeRevokeDiaVO);
								k++;
				}
				
				
				if(_fb.getTreatmentRecordStatusForTreatmentGiven()[i].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED))
				{
					k++;
				}
				
				
			}
			
			
			/**************************saving drug schedule**************/
			
			
			
			List drugAdminList=new ArrayList();	
			Map drugSchedule=(Map)session.getAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			if(drugSchedule==null)
			{
				drugSchedule=new HashMap();
			}
			
			DrugFrequencyMstVO[] arr = (DrugFrequencyMstVO[]) session.getAttribute(OpdConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY);
			DrugFrequencyMstVO drugFreqVO = null;
			
			EHRSection_TreatmentVO drugTreatDtlVO = new EHRSection_TreatmentVO();
			
			Iterator drugScheduleItr=lstDrugTreatmentDtl.iterator();
			while(drugScheduleItr.hasNext())
			{
				drugTreatDtlVO=(EHRSection_TreatmentVO)drugScheduleItr.next();
				if(!drugSchedule.containsKey(drugTreatDtlVO.getDrugId() +"#"+ drugTreatDtlVO.getFrequencyId()+"#"+drugTreatDtlVO.getDoseId()))
				{
					List scheduleLst=new ArrayList();
					for(int t=0;t<arr.length;t++)
					{
						if(arr[t].getFrequencyId().equals(drugTreatDtlVO.getFrequencyId()))
						{
							drugFreqVO=arr[t];
							break;
						}
					}
			
				
							
			int freqCount=Integer.parseInt(freqValue);
			String doseName="";
			for(int i=0;i<freqCount;i++)
			{
				String scheduleSlNo=new Integer(i).toString();
				String[] dosetime=drugFreqVO.getFirstDoseTime().split(":");
				String[] durationtime=drugFreqVO.getDoseDuration().split(":");
				int dosetimehr=Integer.parseInt(dosetime[0])+Integer.parseInt(durationtime[0])*i;
				int dosetimemin=Integer.parseInt(dosetime[1])+Integer.parseInt(durationtime[1])*i;
				String hr="",min="";
				if(dosetimehr<10)
					hr="0"+Integer.toString(dosetimehr);
				else
					hr=Integer.toString(dosetimehr);
				if(dosetimemin<10)
					min="0"+Integer.toString(dosetimemin);
				else
					min=Integer.toString(dosetimemin);
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
				
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			List<EHRSection_TreatmentVO> essentialList=(List<EHRSection_TreatmentVO>) patencountervo.getListSaveAllTreatment();
			
			//getting final list for print purposes
			if(essentialList!=null)
			lstDrugTreatmentDtlAllSave.addAll(essentialList);   // getting all previous data
			
			//if(revokedDrugTreatmentDtl!=null)  
			//lstDrugTreatmentDtlAllSave.removeAll(revokedDrugTreatmentDtl);     // removing revoked data
			
			
	
			if(revokedDrugTreatmentDtl!=null)  {
			for (Iterator<EHRSection_TreatmentVO> revokeitr = revokedDrugTreatmentDtl.iterator(); revokeitr.hasNext();) 
			{
			EHRSection_TreatmentVO revokedrug = revokeitr.next();
			for (Iterator<EHRSection_TreatmentVO> iterator = lstDrugTreatmentDtlAllSave.iterator(); iterator.hasNext();) {
				EHRSection_TreatmentVO essentiallist = iterator.next();
			    if (essentiallist.getDrugId().equals(revokedrug.getDrugId()))
			    {
			        // Remove the current element from the iterator and the list.
			        iterator.remove();
			    }
			}
			}}
			
			if(lstDrugTreatmentDtl!=null)
			lstDrugTreatmentDtlAllSave.addAll(lstDrugTreatmentDtl);   // adding all new data 
		
			
			patencountervo.setListSaveAllTreatment(lstDrugTreatmentDtlAllSave); // setting final list
			WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
		
			
			EHRSection_TreatmentDATA.savePatTreatmentDetail(lstDrugTreatmentDtl,revokedDrugTreatmentDtl, drugSchedule,drugAdminList,getUserVO(_rq));
			
			objStatus.add(Status.DONE, "Record Saved", "");
		}

		
		catch (HisDataAccessException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		//return isSave;
	
	}
	
	
	public static void setEssentialsForTreatmentGiven(EHRSection_TreatmentFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		
		Map mpEssentials = null;
		List<EHRSection_TreatmentVO> lstDrugTreatmentDtl = null;
		
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			session.removeAttribute(OpdConfig.PARTICULAR_DRUGLIST_DEATIL);
			
			// Getting Patient Demographic Detail 
			//InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq); //////////////////////////////////coomented as this is only for OPD  as on date 28.3.2017
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
			
			// Fetching Patient Detail from Desk
			PatientDetailVO voDP = new PatientDetailVO();
			HelperMethods.populate(voDP, _fb);
				
				if(voDP.getPatIsPregnant()!="" && voDP.getPatIsPregnant()==OpdConfig.PAT_PREGNANT_YES)
					_fb.setIsPregnantFlag(OpdConfig.PAT_PREGNANT_YES);
				else
					_fb.setIsPregnantFlag(OpdConfig.PAT_PREGNANT_NO);
			
			
			
			mpEssentials=EHRSection_TreatmentDATA.getPatTreatmentDetailEssential(_fb.getPatCrNo(),_fb.getAdviceType(),_fb.getEpisodeCode(),voDP.getDepartmentUnitCode(),ptaientDetailVO.getPatGenderCode(),voDP, userVO);
			
			
			
			
			/**********************************set essential for drug detail***********************************/
			
			
			// Setting Initial Drug Detail List
			lstDrugTreatmentDtl= new ArrayList<EHRSection_TreatmentVO>();
			String maxEntryDate=(String)mpEssentials.get(OpdConfig.MAX_ENTRY_DATE_BY_CRNO);
			if(maxEntryDate==null)
			{
				List defaultDrugProfileDrugLst=(List)mpEssentials.get(OpdConfig.DEFAULT_DRUG_PROFILE_DRUG_LIST);
				for(int i=0;i<defaultDrugProfileDrugLst.size();i++)
				{
					EHRSection_TreatmentVO vo=_fb.resetVO(new EHRSection_TreatmentVO());
					EHRSection_TreatmentVO profileVO=(EHRSection_TreatmentVO)defaultDrugProfileDrugLst.get(i);
					
					
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
					lstDrugTreatmentDtl.add(vo);
				}
				
			}
						
			
		
						
			lstDrugTreatmentDtl.add(_fb.resetVO(new EHRSection_TreatmentVO()));
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			
						
			//getting last visit(drug detail)
			List<EHRSection_TreatmentVO> prevAll = (List<EHRSection_TreatmentVO>)mpEssentials.get(OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST);
			/*if(prevAll.size() > 0)
			{
				_fb.setIsSetTREATMENT("1");
				Map essentialMap = new HashMap();
				essentialMap.put(EHRConfig.EHR_TREATMENT_ESSENTIAL_SAVE, prevAll);
				session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, essentialMap);
				
				//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, prevAll);	
					
			}*/
			
			if(maxEntryDate==null)
			{
				_fb.setPrescriptionDate("");	
			}
			else
			{
				_fb.setPrescriptionDate(maxEntryDate);	
			}
			
			
			List<EHRSection_TreatmentVO> prevLast = new ArrayList<EHRSection_TreatmentVO>();
			for(EHRSection_TreatmentVO vo : prevAll)
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
			
			//comparing end date with system date(for revoke button) 
			Iterator<EHRSection_TreatmentVO> itr = prevLast.iterator();
			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
			while(itr.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO = itr.next();
				Date sysDate=df.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
				Date endDate = sysDate;
				if(EHRSection_TreatmentVO.getEndDate()!=null)
					endDate=df.parse(EHRSection_TreatmentVO.getEndDate());
				if(endDate.before(sysDate))
				{
					EHRSection_TreatmentVO.setDate("before");
					
					//String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
					DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
					//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
					Calendar calendar1=Calendar.getInstance();
					Calendar calendar2=Calendar.getInstance();
					calendar1.setTime(sinmpledf.parse(EHRSection_TreatmentVO.getStartDate()));
					calendar2.setTime(sinmpledf.parse(EHRSection_TreatmentVO.getEndDate()));
					long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
					String advDays=String.valueOf(noOfDays);
					Integer days=Integer.parseInt(advDays);
					
					EHRSection_TreatmentVO.setIntakeDays(days.toString());
					
					
										
				}
				else
				{
					EHRSection_TreatmentVO.setDate("after");
					String advDays="0";
					if(EHRSection_TreatmentVO.getStartDate()!=null)
					{
						String sysadteStr=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
						DateFormat sinmpledf = new SimpleDateFormat("dd-MMM-yyyy");
						//Date sysDate=sinmpledf.parse(WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
						Calendar calendar1=Calendar.getInstance();
						Calendar calendar2=Calendar.getInstance();
						calendar1.setTime(sinmpledf.parse(EHRSection_TreatmentVO.getStartDate()));
						calendar2.setTime(sinmpledf.parse(sysadteStr));
						long noOfDays=((calendar2.getTime().getTime() - calendar1.getTime().getTime()) / (24 * 3600 * 1000))+1;
						advDays=String.valueOf(noOfDays);
					}
					Integer days=Integer.parseInt(advDays);
					
					if(days>0)
					{
						EHRSection_TreatmentVO.setIntakeDays(days.toString());
					}
					else
					{
						EHRSection_TreatmentVO.setIntakeDays("0");
					}
					
				}
				if(EHRSection_TreatmentVO.getRemarks()==null)
				{
					EHRSection_TreatmentVO.setRemarks("");
				}
			}
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
					
			
			//for removing  revoked drug 
			Iterator<EHRSection_TreatmentVO> drugItr = prevLast.iterator();
			while(drugItr.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO = drugItr.next();
				if(EHRSection_TreatmentVO.getRxContinue().equals(OpdConfig.REVOKE))
				{
					drugItr.remove();
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST, prevLast);
			
			if(prevLast!=null && prevLast.size() > 0)
			{
				_fb.setIsSetTREATMENT("1");
				
				//Map essentialMap=(HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				//essentialMap.put(EHRConfig.EHR_TREATMENT_ESSENTIAL_SAVE, prevLast);
				//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, essentialMap);
				
				EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				patencountervo.setListSaveAllTreatment(prevLast);
				WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
				
					
			}
			
			
			
			//getting today visit(drug detail)
			List<EHRSection_TreatmentVO> todayDrugDetailList = new ArrayList<EHRSection_TreatmentVO>();
			Iterator<EHRSection_TreatmentVO> prevAllDrugDetailItr=prevAll.iterator();
			while(prevAllDrugDetailItr.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO=prevAllDrugDetailItr.next();
				if(EHRSection_TreatmentVO.getEpisodeVisitNo().equals(_fb.getEpisodeVisitNo()) )
				{
					
					todayDrugDetailList.add(EHRSection_TreatmentVO);
					
				}
			}
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST, todayDrugDetailList);
			
			//concatenating drugId with itemtypeId
			//List<DrugDoseVO> drugDoseVOList = (List<DrugDoseVO>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			List<Entry> drugList=(List<Entry>)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			Iterator<EHRSection_TreatmentVO> iter =lstDrugTreatmentDtl.iterator();
			
			while(iter.hasNext())
			{
				EHRSection_TreatmentVO EHRSection_TreatmentVO =iter.next();
				Iterator<Entry> itera =drugList.iterator();
				while(itera.hasNext())
				{
					Entry obj=itera.next();
					String[] itemIdAndItemTypeId=obj.getValue().split("#");
					String itemId=itemIdAndItemTypeId[0];
					String ItemTypeId=itemIdAndItemTypeId[1];
					if(EHRSection_TreatmentVO.getDrugId().equals(itemId))
					{
						if(EHRSection_TreatmentVO.getRemarks()==null)
						{
							EHRSection_TreatmentVO.setRemarks("");
						}
						EHRSection_TreatmentVO.setDrugId(EHRSection_TreatmentVO.getDrugId()+"#"+ItemTypeId);					
					}
				}
			}
			
			
			
			mpEssentials.put(OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST, lstDrugTreatmentDtl);
			_fb.setDrugDetailRows(Integer.toString(lstDrugTreatmentDtl.size()));
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
			if(_fb.getAdviceType().equals(Config.TREATMENT_ADVICE_TYPE_DEFUALT))
			{
			EHRVOUtility.setTreatmentDetailVO(_rq, _fb.getPatCrNo(), prevLast); //Added by Vasu on 2.Nov.2018
			}
			else
			{
				EHRVOUtility.setDrugAdviceVO(_rq, _fb.getPatCrNo(), prevLast);//Added by Vasu on 17.Dec.18
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
}

