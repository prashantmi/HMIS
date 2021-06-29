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
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.utility.generictemplate.GenericTemplateUtility.TempParameter;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.BloodTransfusionReactionDATA;
import inpatient.transaction.controller.fb.BloodTransfusionReactionFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.controller.fb.CRNoFB;

public class BloodTransfusionReactionUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(BloodTransfusionReactionFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		List finalBloodTransVOList=new ArrayList();
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

			
			
			mpEssentials=BloodTransfusionReactionDATA.getBloodTransReactionEssential(voDP,userVO); 
			List bloodTransVOList=(List)mpEssentials.get(InpatientConfig.BLOOD_TRANSFUSION_VO_LIST);
			
			Iterator itr=bloodTransVOList.iterator();
			while(itr.hasNext())
			{
				BloodTransfusionDtlVO vo=(BloodTransfusionDtlVO)itr.next();
				/*
				if(!(vo.getBloodBagSequenceNo().equals("")) && !(vo.getBloodBagSequenceNo().equals(null)))
				{
					vo.setBloodBagNo(vo.getBloodBagNo()+"("+vo.getBloodBagSequenceNo()+")");
					
				}
				*/
				
				if(vo.getSourceFlag().equals(InpatientConfig.FROM_IN_HOUSE_BLOOD_BANK))
				{
					vo.setSourceFlag("Internal Bank");
				}
				else
				{
					vo.setSourceFlag("External Bank");
				}
				if(vo.getBloodBagSequenceNo()==null)
				{
					vo.setBloodBagSequenceNo("");
				}
				finalBloodTransVOList.add(vo);
			}
			
			mpEssentials.put(InpatientConfig.BLOOD_TRANSFUSION_VO_LIST, finalBloodTransVOList);
			WebUTIL.setMapInSession(mpEssentials, _rq);
			if(finalBloodTransVOList.size()==0)
			{
				objStatus.add(Status.DONE,"No Blood Transfused To This Patient","");
			}
			
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
	
public static void saveBloodTransReactionDtl(BloodTransfusionReactionFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		UserVO userVO=getUserVO(_rq);
		setSysdate(_rq);  
		//HttpSession session=_rq.getSession();
		List bloodTrasReactionParaVOList=new ArrayList();
		List templateParaList=null;
		try{
			TransfusionReactionDtlVO transReactionDtlVO=new TransfusionReactionDtlVO();
			//int count=0;
			
			Integer index=Integer.parseInt(_fb.getSelectedBag());
			transReactionDtlVO.setBloodBagNo(_fb.getBagNoArray()[index]);
			if(!(_fb.getBagSeqNoArray()[index]==null) && !(_fb.getBagSeqNoArray()[index].equals("")))
			{
				transReactionDtlVO.setBloodBagSequenceNo(_fb.getBagSeqNoArray()[index]);
			}
			transReactionDtlVO.setReactionStartTime(_fb.getReactionStartDate()+" "+_fb.getReactionStartHr()+":"+_fb.getReactionStartMin());
			transReactionDtlVO.setReactionEndTime(_fb.getReactionEndDate()+" "+_fb.getReactionEndHr()+":"+_fb.getReactionEndMin());
			transReactionDtlVO.setControllSummary(_fb.getControlSummary());
			transReactionDtlVO.setTransfusinStatus(_fb.getTransfusionStatusArray()[index]);
			transReactionDtlVO.setRequisitionNo(_fb.getRequisitionNoArray()[index]);
			if(_fb.getSourceFlagArray()[index].equals("Internal Bank"))
			{
				transReactionDtlVO.setSourceFlag(InpatientConfig.FROM_IN_HOUSE_BLOOD_BANK);
			}
			else
			{
				transReactionDtlVO.setSourceFlag(InpatientConfig.ARRANGE_BY_PATIENT_FROM_OTHER_BANK);
			}
			//transReactionDtlVO.setSourceFlag(_fb.getSourceFlagArray()[index]);
			transReactionDtlVO.setReactionSummary(_fb.getReactionSummary());
			transReactionDtlVO.setPatCrNo(_fb.getPatCrNo());
			transReactionDtlVO.setStockDate(_fb.getStockDateArray()[index]);
			transReactionDtlVO.setStockBagSerialNo(_fb.getStockBagSerialNoArray()[index]);
			
			
			templateParaList=GenericTemplateUtility.getTempParameterValueList(_rq);
			for(int i=0;i<templateParaList.size();i++)
			{
				TempParameter obj=(TempParameter)templateParaList.get(i);
				TransfusionReactionParaDtlVO transReactionParaDtlVO=new TransfusionReactionParaDtlVO();
				transReactionParaDtlVO.setTemplateId(obj.getTemplateId());
				transReactionParaDtlVO.setParaId(obj.getParaId());
				transReactionParaDtlVO.setParaValue(obj.getParaValue());
				//transReactionParaDtlVO.setPatCrNo(_fb.getPatCrNo());
				bloodTrasReactionParaVOList.add(transReactionParaDtlVO);
			}
			
			BloodTransfusionReactionDATA.saveBloodTransReactionDtl(transReactionDtlVO,bloodTrasReactionParaVOList, userVO);
					
			objStatus.add(Status.NEW,"Blood Transfused Reaction Saved Successfully","");
			
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	}

//Getting and Setting Macros
public static void getSetMacros(BloodTransfusionReactionFB _fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	try
	{
		UserVO userVO = getUserVO(_request);
		setSysdate(_request);

		List<Entry> lstMacros = BloodTransfusionReactionDATA.getReactionSummaryDetail(_fb.getMacroProcessId(), userVO);
		WebUTIL.setAttributeInSession(_request, InpatientConfig.BLOOD_TRANSFUSION_REACTION_ESSENTIAL_MACROS_BY_PROCESS_ID_LIST, lstMacros);
		if(lstMacros.size()>0)
			objStatus.add(Status.NEW, "", "");
		else
			objStatus.add(Status.NEW, "", "No Record Found");
	}
	catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
	}
	catch(HisDataAccessException e){
		objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
	}
	catch(HisApplicationExecutionException e)
	{		
		objStatus.add(Status.ERROR_AE,e.getMessage(),"");
	}
	catch(HisException e)
	{
		objStatus.add(Status.ERROR,e.getMessage(),"");
	}
	catch(Exception e)
	{
		objStatus.add(Status.ERROR_AE,e.getMessage(),"");
	}
	finally
	{
		WebUTIL.setStatus(_request,objStatus);
	}	
}
}
