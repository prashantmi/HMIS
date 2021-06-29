package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.utility.generictemplate.GenericTemplateUtility.TempParameter;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ReactionParaDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DrugReactionData;
import inpatient.transaction.controller.fb.DrugReactionFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.controller.fb.CRNoFB;

public class DrugReactionUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(DrugReactionFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		List allDrugGivenLst=null;
		List templateIdList=null;
		List treatInfoLst=new ArrayList();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			_fb.setSysDate(sysadteString);

			// Getting Patient Detail
			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _rq);
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

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
			
			// Setting Template Essentials
				// Patient Information in Session for Template
				InformationControlBean infoBean = new InformationControlBean();
				infoBean.putPatientDetailValues(patDtlVO);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_TEMPLATE_INFORMATION_BEAN, infoBean);
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE, patDtlVO.getPatGenderType());
				// Age of Patient in Integer
				Date dob = WebUTIL.getDateFromString(patDtlVO.getPatDOB(), "dd-MMM-yyyy");
				long diff = sysdate.getTime() - dob.getTime();
				int age = (int)(diff / ((long)1000 * 60 * 60 * 24 * 365));
				if(age<=0)	age=1;
			WebUTIL.setAttributeInSession(_rq, GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE, Integer.toString(age));

			
			
			DrugAdminDtlVO drugAdminDtlVO=new DrugAdminDtlVO();
			drugAdminDtlVO.setEpisodeCode(_fb.getEpisodeCode());
			drugAdminDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			drugAdminDtlVO.setPatCrNo(_fb.getPatCrNo());
			
			mpEssentials=DrugReactionData .getPatDrugReactionEssential(voDP,drugAdminDtlVO ,userVO);
			
			String maxAdminDate=(String)mpEssentials.get(InpatientConfig.MAX_ADMINISTRATION_DATE_BY_CRNO);
			_fb.setSelAdministrationDate(maxAdminDate);
			
			allDrugGivenLst=(List)mpEssentials.get(InpatientConfig.ESSENTIALS_ALL_DRUG_EXEC_LIST_BY_CRNO);
			Iterator itr=allDrugGivenLst.iterator();
			while(itr.hasNext())
			{
				DrugAdminDtlVO drugAdmDtlVO=(DrugAdminDtlVO)itr.next();
				if(drugAdmDtlVO.getAdministrationDate().equals(_fb.getSelAdministrationDate()))
				{
					treatInfoLst.add(drugAdmDtlVO);
				}
			}
			
			session.setAttribute(InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE, treatInfoLst);
			
			templateIdList=(List)mpEssentials.get(InpatientConfig.TEMPLATEID_LIST_FOR_DRUG_REACTION);
			_fb.setTemplateId((String)templateIdList.get(0));
			
			List prevDrugReactList=(List)mpEssentials.get(InpatientConfig.PREV_DRUG_REACTION_LIST_BY_CRNO);
			
			if((prevDrugReactList==null || prevDrugReactList.size()==0) && (treatInfoLst==null || treatInfoLst.size()==0))
			{
				objStatus.add(Status.DONE,"","No drug given to this patient");
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
	
	public static void getDrugInfo(DrugReactionFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List treatInfoLst=new ArrayList();
		List allDrugGivenLst=null;
		//List drugRouteLst=null;
		try{
			//UserVO userVO = getUserVO(_request); 
			
			allDrugGivenLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_ALL_DRUG_EXEC_LIST_BY_CRNO);
			Iterator itr=allDrugGivenLst.iterator();
			while(itr.hasNext())
			{
				DrugAdminDtlVO drugAdminDtlVO=(DrugAdminDtlVO)itr.next();
				if(drugAdminDtlVO.getAdministrationDate().equals(_fb.getSelAdministrationDate()))
				{
					treatInfoLst.add(drugAdminDtlVO);
				}
			}
			
			session.setAttribute(InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE, treatInfoLst);			
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
	
	public static void saveDrugReactionDtl(DrugReactionFB _fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List drugReactionVOLst=new ArrayList();
		List templateParaList=null;
		List reactionParaDtlVOList=new ArrayList();
		try
		{	
			setSysdate(_request);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");			
			
			//List treatInfoLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE);
			/*			
				DrugReactionVO drugReactionVO=new DrugReactionVO();
				int count=0;
				Iterator itr=treatInfoLst.iterator();
				while(itr.hasNext())
				{
					DrugAdminDtlVO vo=(DrugAdminDtlVO)itr.next();
					if(count==Integer.parseInt(_fb.getSelectedDrug()))
					{
						drugReactionVO.setBatchNo(vo.getBatchNo());
						drugReactionVO.setItemId(vo.getItemId());
						drugReactionVO.setAdministrationDate(vo.getAdministrationDate());
						drugReactionVO.setAdminSerialNo(vo.getSerialNo());
						drugReactionVO.setDrugGivenTime(sysadteString+" "+vo.getAdministrationTime());
						drugReactionVO.setPatCrNo(_fb.getPatCrNo());
						drugReactionVO.setPatAdmNo(_fb.getAdmissionNo());
						drugReactionVO.setEpisodeCode(_fb.getEpisodeCode());
						drugReactionVO.setControlSummary(_fb.getControlSummary());
						drugReactionVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
						drugReactionVO.setReactionEndTime(sysadteString+" "+_fb.getReactionEndHr()+":"+_fb.getReactionEndMin());
						drugReactionVO.setReactionStartTime(sysadteString+" "+_fb.getReactionStartHr()+":"+_fb.getReactionStartMin());
						drugReactionVO.setReactionSummary(_fb.getReactionSummary());
						drugReactionVOLst.add(drugReactionVO);
					}
					count++;
				}
				*/
				DrugReactionVO drugReactionVO=new DrugReactionVO();
				DrugAdminDtlVO vo=new DrugAdminDtlVO();
				vo=(DrugAdminDtlVO)session.getAttribute(InpatientConfig.SELECTED_DRUG_ADMIN_DETAIL_VO);
				drugReactionVO.setBatchNo(vo.getBatchNo());
				drugReactionVO.setItemId(vo.getItemId());
				drugReactionVO.setAdviceDate(vo.getAdviceDate());
				drugReactionVO.setAdministrationDate(vo.getAdministrationDate());
				drugReactionVO.setAdminSerialNo(vo.getSerialNo());
				drugReactionVO.setDrugGivenTime(sysadteString+" "+vo.getAdministrationTime());
				drugReactionVO.setPatCrNo(_fb.getPatCrNo());
				drugReactionVO.setPatAdmNo(_fb.getAdmissionNo());
				drugReactionVO.setEpisodeCode(_fb.getEpisodeCode());
				drugReactionVO.setControlSummary(_fb.getControlSummary());
				drugReactionVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				drugReactionVO.setReactionEndTime(sysadteString+" "+_fb.getReactionEndHr()+":"+_fb.getReactionEndMin());
				drugReactionVO.setReactionStartTime(sysadteString+" "+_fb.getReactionStartHr()+":"+_fb.getReactionStartMin());
				drugReactionVO.setReactionSummary(_fb.getReactionSummary());
				drugReactionVO.setDrugBrandId(_fb.getDrugBrandId());
				drugReactionVO.setDrugBrandName(_fb.getDrugBrandName());
				
				drugReactionVOLst.add(drugReactionVO);
				
				
				templateParaList=GenericTemplateUtility.getTempParameterValueList(_request);
				for(int i=0;i<templateParaList.size();i++)
				{
					TempParameter obj=(TempParameter)templateParaList.get(i);
					ReactionParaDtlVO reactionParaDtlVO=new ReactionParaDtlVO();
					reactionParaDtlVO.setTemplateId(obj.getTemplateId());
					reactionParaDtlVO.setParaID(obj.getParaId());
					reactionParaDtlVO.setParaValue(obj.getParaValue());
					reactionParaDtlVO.setPatCrNo(_fb.getPatCrNo());
					reactionParaDtlVOList.add(reactionParaDtlVO);
				}
			DrugReactionData.saveDrugReactionDtl(drugReactionVO,reactionParaDtlVOList,getUserVO(_request));	
			
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(_request, _fb.getDeskMenuId());
			objStatus.add(Status.DONE,"","Drug Reaction Save Successfully");
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
	
	public static void getSelectedDrugInfo(DrugReactionFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List selectedDrugInfoLst=new ArrayList();
		try{
			//UserVO userVO = getUserVO(_request); 
			
			List treatInfoLst=(List)session.getAttribute(InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE);
			Iterator itr=treatInfoLst.iterator();
			Integer count=0;
			while(itr.hasNext())
			{
				DrugAdminDtlVO drugAdminDtlVO=(DrugAdminDtlVO)itr.next();
				if(count==Integer.parseInt(_fb.getSelectedDrug()))
				{
					_fb.setSelectedDrug(count.toString());
					selectedDrugInfoLst.add(drugAdminDtlVO);
					session.setAttribute(InpatientConfig.ESSENTIALS_DRUG_EXECUTION_LIST_BY_ADMINISTRATIONDATE, selectedDrugInfoLst);
					session.setAttribute(InpatientConfig.SELECTED_DRUG_ADMIN_DETAIL_VO, drugAdminDtlVO);
				}
				count++;
			}
						
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
	
	public static void getDrugReactionDetail(DrugReactionFB _fb, HttpServletRequest _request)
	{
		Status objStatus =new Status();
		HttpSession session=WebUTIL.getSession(_request);
		List drugReactionDtlList=null;
		try{
			UserVO userVO = getUserVO(_request); 
			
			// Setting Desk Essentials      
			// Episode, Visit, Admission No
			PatientDetailVO[] al = (PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;					
				}
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setAdmissionNo(voDP.getPatAdmNo());
			_fb.setAdmissionDate(voDP.getAdmDateTime());
			
			DrugReactionVO drugReactionVo=new DrugReactionVO();
			
			drugReactionVo.setPatCrNo(_fb.getPatCrNo());
			drugReactionVo.setEpisodeCode(_fb.getEpisodeCode());
			drugReactionVo.setItemId(_fb.getPrevItemId());
			
			drugReactionDtlList=DrugReactionData.getDrugReactionDtl(drugReactionVo, userVO);
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.DRUG_REACTION_DTL_LIST_FOR_PARTICULAR_DRUG, drugReactionDtlList);
						
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
	
}
