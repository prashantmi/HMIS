package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDeadDonorException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisPermanentUnfitDonorException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.GenericTemplateUtility.TempParameter;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.BloodTransfusionDATA;
import inpatient.transaction.controller.fb.BloodTransfusionFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BloodTransfusionDtlUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(BloodTransfusionFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		List lstBldTransDtlVO=new ArrayList<BloodTransfusionDtlVO>();
		List patBloodStockVoList=null;
		List patBloodStockVoListWithSourceFlag=new ArrayList();
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{
			UserVO userVO = getUserVO(_rq);
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
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setAdmissionNo(voDP.getPatAdmNo());
			
			setSysdate(_rq);
			Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
			String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy");
			_fb.setSysdate(sysadteString);
						
			mpEssentials=BloodTransfusionDATA.getBloodTransfusionEssential(voDP,userVO);
			
			patBloodStockVoList=(List)mpEssentials.get(InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO);
			for(int i=0;i<patBloodStockVoList.size();i++)
			{
				PatBloodStockDtlVO vo=new PatBloodStockDtlVO();
				vo=(PatBloodStockDtlVO)patBloodStockVoList.get(i);
				if(vo.getSourceFlag().equals("1"))
				{
					vo.setSourceFlag("Internal Blood Bank");
					vo.setTransfusionStartDate(sysadteString);
					vo.setTransfusionEndDate(sysadteString);
				}
				else
				{
					vo.setSourceFlag("Extrenal Blood Bank");
					vo.setTransfusionStartDate(sysadteString);
					vo.setTransfusionEndDate(sysadteString);
				}
				patBloodStockVoListWithSourceFlag.add(vo);
				
			}
			
			mpEssentials.put(InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO, patBloodStockVoListWithSourceFlag);
			
			
			//  Setting Previous Transfusion Details 
			
			String patCrNo=_fb.getPatCrNo();
			lstBldTransDtlVO=BloodTransfusionDATA.getPreviousBloodTransDtl(patCrNo,lstBldTransDtlVO, userVO);
			WebUTIL.setAttributeInSession(_rq, InpatientConfig.PREVIOUS_TRANSFUSION_REACTION_DETAIL, lstBldTransDtlVO);
			
			
			
			// End of Previous Transfusion Details
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
			if(patBloodStockVoListWithSourceFlag.size()==0)
			{
				objStatus.add(Status.DONE,"No bag available for transfusion for this patient","");
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
	
	
public static boolean saveBloodTransfusionDtl(BloodTransfusionFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		UserVO userVO=getUserVO(_rq);
		setSysdate(_rq);  
		List bloodTrasVOList=new ArrayList();
		List patIntakeOutDtlVOList=new ArrayList();
		boolean flagSave=false;
		try{
			
			setSysdate(_rq);
			
			// Transfusion Reaction DEtail
			Map<String, TransfusionReactionDtlVO> mapTrasReaction = (Map<String, TransfusionReactionDtlVO>) _rq.getSession().getAttribute(
					InpatientConfig.TRANSFUSION_REACTION_DETAIL);
			Map<String, List<TransfusionReactionParaDtlVO>> mapTrasReactionPara = (Map<String, List<TransfusionReactionParaDtlVO>>) _rq.getSession().getAttribute(
					InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL);

			String[] selectedBag=_fb.getSelectedBag(); 
			for(int i=0;i<selectedBag.length;i++)
			{
				BloodTransfusionDtlVO vo=new BloodTransfusionDtlVO();
				
				
				int ind=Integer.parseInt(selectedBag[i]);
				
				vo.setPatCrNo(_fb.getPatCrNo());
				vo.setEpisodeCode(_fb.getEpisodeCode());
				vo.setAdmissionNo(_fb.getAdmissionNo());
				vo.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				
				vo.setRemark(_fb.getRemarks()[i]);
				vo.setBloodBagNo(_fb.getBloodBagNo()[ind]);
				vo.setStockBagSerialNo(_fb.getStockBagSerialNo()[ind]);
				vo.setStockDate(_fb.getStockDate()[ind]);
				if((_fb.getBloodBagSequenceNo()[ind]!=null) || !(_fb.getBloodBagSequenceNo()[ind].equals("")))
				{
					vo.setBloodBagSequenceNo(_fb.getBloodBagSequenceNo()[ind]);
				}
				else
				{
					vo.setBloodBagSequenceNo("");
				}
				
				vo.setTransfussionStartTime(_fb.getTransfusionStartDate()[ind]+" "+_fb.getStartHr()[i]+":"+_fb.getStartMin()[i]);
				vo.setTransfussionEndTime(_fb.getTransfusionEndDate()[ind]+" "+_fb.getEndHr()[i]+":"+_fb.getEndMin()[i]);
				vo.setPhlebotomyArm(_fb.getPhlebotomyArm()[i]);
				vo.setRequisitionNo(_fb.getRequisitionNo()[i]);
				vo.setQtyTransfussed(_fb.getQtyTransfussed()[i]);
				vo.setTransfussionRate(_fb.getTransfussionRate()[i]);
				
				bloodTrasVOList.add(vo);
				
				//saving intake detail
				PatIntakeOutDtlVO patIntakeOutDtlVO=new PatIntakeOutDtlVO();
				
				patIntakeOutDtlVO.setPatCrNo(_fb.getPatCrNo());
				patIntakeOutDtlVO.setEpisodeCode(_fb.getEpisodeCode());
				patIntakeOutDtlVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				patIntakeOutDtlVO.setPatAdmNo(_fb.getAdmissionNo());
				patIntakeOutDtlVO.setInTakeOutParaId("");
				patIntakeOutDtlVO.setInTakeOutParaName(InpatientConfig.INTAKE_OUT_PARANAME_FOR_BLOOD_TRANSFUSION);
				patIntakeOutDtlVO.setInTakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
				patIntakeOutDtlVO.setRouteId(_fb.getPhlebotomyArm()[i]);
				patIntakeOutDtlVO.setInTakeOutTime(_fb.getTransfusionStartDate()[ind]+" "+_fb.getStartHr()[i]+":"+_fb.getStartMin()[i]);
				patIntakeOutDtlVO.setRemarks(_fb.getRemarks()[i]);
				patIntakeOutDtlVO.setEntryMode(InpatientConfig.INTAKEOUT_ENTRY_MODE_BLOOD_TRANSEFUSION);
				patIntakeOutDtlVO.setVolume(_fb.getQtyTransfussed()[i]);
								
				patIntakeOutDtlVOList.add(patIntakeOutDtlVO);
			}
			BloodTransfusionDATA.saveBloodTransfusionDtl(mapTrasReaction,mapTrasReactionPara,bloodTrasVOList,patIntakeOutDtlVOList,selectedBag, userVO);
			
//			for(int i=0;i<selectedBag.length;i++)
//			{
//				TransfusionReactionDtlVO voTrReaction = mapTrasReaction.get(selectedBag[i]);
//				List<TransfusionReactionParaDtlVO> lstTrnReaPara = mapTrasReactionPara.get(selectedBag[i]);
//				
//				if(voTrReaction!=null) BloodTransfusionDATA.saveExtBloodTransReactionDtl(voTrReaction, lstTrnReaPara, userVO);
//			}

			
			
			
			
			
			/*//////// old Code using List only
			List bloodTrasReactionParaVOList=new ArrayList();
			List prevTransfusionDtls=(List)_rq.getSession().getAttribute(InpatientConfig.TRANSFUSION_REACTION_DETAIL);
			List prevTransfusionTemplateDtls=(List)_rq.getSession().getAttribute(InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL);
			TransfusionReactionDtlVO transReactionDtlVO=new TransfusionReactionDtlVO();
			if(prevTransfusionDtls!=null && prevTransfusionDtls.size()!=0)
			for(int k=0;k<prevTransfusionDtls.size();k++)
			{
				 transReactionDtlVO=(TransfusionReactionDtlVO)prevTransfusionDtls.get(k);
				
				 for(int z=0;z<prevTransfusionTemplateDtls.size();z++)
					{
					 	bloodTrasReactionParaVOList=(List)prevTransfusionTemplateDtls.get(z);
					 	 if(bloodTrasReactionParaVOList!=null && bloodTrasReactionParaVOList.size()!=0)
					 	 {
					 		 String chk=(String)bloodTrasReactionParaVOList.get(0);
					 		 if(transReactionDtlVO.getSeqNo().equals(chk.substring(1)))
					 		{
					 			 bloodTrasReactionParaVOList.remove(0);
					 			BloodTransfusionDATA.saveExtBloodTransReactionDtl(transReactionDtlVO,bloodTrasReactionParaVOList, userVO);
					 		}
					 		 else
					 		 {
					 			 //System.out.println("");
					 		 }
					 	 }
					 	 else
					 	 {
					 		 bloodTrasReactionParaVOList=null;
					 		 BloodTransfusionDATA.saveExtBloodTransReactionDtl(transReactionDtlVO,bloodTrasReactionParaVOList, userVO);
					 	 }
					}
				
			}*/
			objStatus.add(Status.NEW,"Blood Transfused Successfully","");
			flagSave=true;
			
		}catch(HisDataAccessException e){
			e.printStackTrace();
			flagSave=false;
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){	
			e.printStackTrace();
			flagSave=false;
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			e.printStackTrace();
			flagSave=false;
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
		return flagSave;
	}
public static void getExtPatTransReactionEssential(BloodTransfusionFB _fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
//	List bagDetailList=null;
	Map essentialMap=new HashMap(); 
	HttpSession session=WebUTIL.getSession(_request);
	try
	{
		UserVO userVo=getUserVO(_request);
		setSysdate(_request);
		Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
		String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MMM-yyyy HH:mm");
		_fb.setSysdate(sysadteString);
		
		essentialMap=BloodTransfusionDATA.getExtPatTransReactionEssential(userVo);
		
		WebUTIL.setMapInSession(essentialMap, _request);
	}
	catch(HisDeadDonorException e){
		System.out.println(e.getMessage());
		objStatus.add(Status.LIST,e.getMessage(),"");
	}	
	catch(HisPermanentUnfitDonorException e){
		System.out.println(e.getMessage());
		objStatus.add(Status.LIST,e.getMessage(),"");
	}	
	catch (HisRecordNotFoundException e)
	{
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		System.out.println(e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		System.out.println(e.getMessage());
	}
	catch (HisApplicationExecutionException e)
	{
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		System.out.println(e.getMessage());
	}
	catch (HisException e)
	{
		objStatus.add(Status.ERROR, "", "Error");
		System.out.println(e.getMessage());
	}
	finally
	{ 
		WebUTIL.setStatus(_request, objStatus);
	}
}

	public static void savePatTransReactionDtl(BloodTransfusionFB fb_p, HttpServletRequest request_p)
	{
		Status objStatus = new Status();
		try
		{
			// Transfusion Reation Detail
			TransfusionReactionDtlVO transReactionDtlVO = new TransfusionReactionDtlVO();

			transReactionDtlVO.setSeqNo(fb_p.getPopupCount());
			String bagNo = fb_p.getPopupBloodBagNo().substring(0, (fb_p.getPopupBloodBagNo().length() - 3));
			transReactionDtlVO.setBloodBagNo(bagNo);
			transReactionDtlVO.setBloodBagSequenceNo(fb_p.getPopupBloodBagNo().substring((fb_p.getPopupBloodBagNo().length() - 2),
					(fb_p.getPopupBloodBagNo().length() - 1)));
			transReactionDtlVO.setBloodBagSequenceNo(fb_p.getPopupCount());
			transReactionDtlVO.setReactionStartTime(fb_p.getReactionStartDate() + " " + fb_p.getReactionStartHr() + ":" + fb_p.getReactionStartMin());
			transReactionDtlVO.setControllSummary(fb_p.getControlSummary());
			// transReactionDtlVO.setTransfusinStatus(fb_p.getTransfusionStatusArray()[index]);
			transReactionDtlVO.setRequisitionNo(fb_p.getReqNo());
			transReactionDtlVO.setSourceFlag(InpatientConfig.FROM_IN_HOUSE_BLOOD_BANK);
			transReactionDtlVO.setReactionSummary(fb_p.getReactionSummary());
			transReactionDtlVO.setIsFormFilled(fb_p.getIsFormFilled());
			transReactionDtlVO.setIsTransfusionSet(fb_p.getIsTransfusionSet());
			transReactionDtlVO.setIsPostTransfusionSample(fb_p.getIsPostTransfusionSample());
			transReactionDtlVO.setBloodComponentTransfusedAmount(fb_p.getBloodComponentTransfusedAmount());
			transReactionDtlVO.setReactionFormSendDate(fb_p.getReactionFormSendDate() + " " + fb_p.getReactionFormSendTimeHr() + ":"
					+ fb_p.getReactionFormSendTimeMin());

			Map<String, TransfusionReactionDtlVO> mapTrasReaction = (Map<String, TransfusionReactionDtlVO>) request_p.getSession().getAttribute(
					InpatientConfig.TRANSFUSION_REACTION_DETAIL);
			
			if(mapTrasReaction==null)
			{
				mapTrasReaction = new HashMap<String, TransfusionReactionDtlVO>();
				mapTrasReaction.put(fb_p.getPopupCount(), transReactionDtlVO);
			}
			WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_DETAIL, mapTrasReaction);
			
			/*
			List transfusionDtlList = new ArrayList();
			List newAddedTransDtl = new ArrayList();
			transfusionDtlList.add(transReactionDtlVO);
			List prevTransfusionDtl = (List) request_p.getSession().getAttribute(InpatientConfig.TRANSFUSION_REACTION_DETAIL);
			if (prevTransfusionDtl != null)
			{
				newAddedTransDtl.add(prevTransfusionDtl);
				newAddedTransDtl.add(transfusionDtlList);
				WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_DETAIL, newAddedTransDtl);
			}
			else WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_DETAIL, transfusionDtlList);*/

			// Transfusion Reation Para Detail
			List templateParaList = GenericTemplateUtility.getTempParameterValueList(request_p);
			List bloodTrasReactionParaVOList = new ArrayList();
			for (int i = 0; i < templateParaList.size(); i++)
			{
				//bloodTrasReactionParaVOList.add("#" + fb_p.getPopupCount());
				TempParameter obj = (TempParameter) templateParaList.get(i);
				TransfusionReactionParaDtlVO transReactionParaDtlVO = new TransfusionReactionParaDtlVO();
				transReactionParaDtlVO.setTemplateId(obj.getTemplateId());
				transReactionParaDtlVO.setParaId(obj.getParaId());
				transReactionParaDtlVO.setParaValue(obj.getParaValue());
				bloodTrasReactionParaVOList.add(transReactionParaDtlVO);

			}

			Map<String, List<TransfusionReactionParaDtlVO>> mapTrasReactionPara = (Map<String, List<TransfusionReactionParaDtlVO>>) request_p.getSession().getAttribute(
					InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL);
			
			if(mapTrasReactionPara==null)
			{
				mapTrasReactionPara = new HashMap<String, List<TransfusionReactionParaDtlVO>>();
				mapTrasReactionPara.put(fb_p.getPopupCount(), bloodTrasReactionParaVOList);
			}
			WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL, mapTrasReactionPara);

			
			/*
			List templateParaDetailList = new ArrayList();
			List newTemplateParaDetailList = new ArrayList();
			templateParaDetailList.add(bloodTrasReactionParaVOList);
			prevTransfusionTempDtl=(List)request_p.getSession().getAttribute(InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL);
			List prevTransfusionTempDtl = mapTrasReactionPara.get(0);
			if (prevTransfusionTempDtl != null)
			{
				newTemplateParaDetailList.add(prevTransfusionTempDtl);
				newTemplateParaDetailList.add(templateParaDetailList);
				// WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL,
				// newTemplateParaDetailList);
				mapTrasReactionPara.put(fb_p.getPopupCount(), newTemplateParaDetailList);
			}
			else
			// WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL,
			// templateParaDetailList);
			mapTrasReactionPara.put(fb_p.getPopupCount(), templateParaDetailList);
			WebUTIL.setAttributeInSession(request_p, InpatientConfig.TRANSFUSION_REACTION_TEMPLATE_DETAIL, mapTrasReactionPara);*/

		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request_p, objStatus);

		}
}

}
