package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.StockEntryOfBloodDATA;
import inpatient.transaction.controller.fb.StockEntryOfBloodFB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StockEntryOfBloodUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(StockEntryOfBloodFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = new HashMap();
		List reqComponentList=null;
		List ComponentComboList=null;
		List aboComboList=null;
		List rhComboList=null;
		List patBloodStockVoList=null;
		List requisitionNoComboList=new ArrayList();
		List patBloodStockVoListWithSourceFlag=new ArrayList();
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			UserVO userVO = getUserVO(_rq);
			
			InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
			PatientDetailVO patientVO=(PatientDetailVO)session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus=(String)patientVO.getPatStatusCode();
			_fb.setPatDeathStatus(patDeathStatus);
			
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
			
			
			mpEssentials=StockEntryOfBloodDATA.getStockEntryOfBloodEssential(voDP,userVO);
			reqComponentList=(List)mpEssentials.get(InpatientConfig.ALL_REQUESTED_COMPONENT_LIST_BY_CRNO);
			
			List requisitonNoList=new ArrayList();
			
			for(int i=0;i<reqComponentList.size();i++)
			{
				BloodRequisitionComponentDtlVO vo=new BloodRequisitionComponentDtlVO();
				vo=(BloodRequisitionComponentDtlVO)reqComponentList.get(i);
				Boolean flag=false;
				for(int j=0;j<requisitonNoList.size();j++)
				{
					String reqNo=(String)requisitonNoList.get(j);
					if(reqNo.equals(vo.getRequisitionNo()))
					{
						flag=true;
					}
				}
				if(!flag)
				{
					requisitonNoList.add(vo.getRequisitionNo());
				}
				
				
				/*
				if(requisitionNoComboList.size()!=0)
				{
					for(int j=0;j<requisitionNoComboList.size();j++)
					{
						Entry obj=(Entry)requisitionNoComboList.get(j);
						if(!(vo.getRequisitionNo().equals(obj.getValue())))
						{
							requisitionNoComboList.add(entryObj);
						}
					}
				}
				else
				{
					requisitionNoComboList.add(entryObj);
				}
				*/
				
				
				
			}
			Collections.sort(requisitonNoList);
		    
			for(int i=0;i<requisitonNoList.size();i++)
			{
				String reqNo=(String)requisitonNoList.get(i);
				Entry entryObj=new Entry();
				entryObj.setLabel(reqNo);
				entryObj.setValue(reqNo);
				requisitionNoComboList.add(entryObj);
			}
			
			mpEssentials.put(InpatientConfig.REQUISITION_NO_COMBO_LIST, requisitionNoComboList);
			
			Iterator itr=reqComponentList.iterator();
			while(itr.hasNext())
			{
				BloodRequisitionComponentDtlVO vo=(BloodRequisitionComponentDtlVO)itr.next();
				if(vo.getRequisitionStatus().equals(InpatientConfig.HBNUM_REQ_STATUS_RQUISITION_NOT_ACCEPTED_OR_DENIED))
				{
					vo.setComponentReqStatus("Denied");
				}
				else
				{
					if(vo.getComponentReqStatus().equals(InpatientConfig.HBNUM_REQ_STATUS_BAG_ISSUES_COMPLETED))
					{
						vo.setComponentReqStatus("Issued");
					}
					if(vo.getComponentReqStatus().equals(InpatientConfig.HBNUM_REQ_STATUS_RAISED_BUT_NOT_ACCEPTED_IN_BLOOD_BANK))
					{
						vo.setComponentReqStatus("In Proccess");
					}
					
					if(vo.getComponentReqStatus().equals(InpatientConfig.HBNUM_REQ_STATUS_RAISED_AND_ACCEPTED))
					{
						vo.setComponentReqStatus("Accepted");
					}
					
					if(vo.getComponentReqStatus().equals(InpatientConfig.HBNUM_REQ_STATUS_PARTIALY_ACCEPTED))
					{
						vo.setComponentReqStatus("Partialy Accepted");
					}
					if(vo.getComponentReqStatus().equals(InpatientConfig.HBNUM_REQ_STATUS_RQUISITION_NOT_ACCEPTED_OR_DENIED))
					{
						vo.setComponentReqStatus("Denied");
					}
				}
			}
			
			patBloodStockVoList=(List)mpEssentials.get(InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO);
			for(int i=0;i<patBloodStockVoList.size();i++)
			{
				PatBloodStockDtlVO vo=new PatBloodStockDtlVO();
				vo=(PatBloodStockDtlVO)patBloodStockVoList.get(i);
				if(vo.getSourceFlag().equals("1"))
				{
					vo.setSourceFlag("Internal Blood Bank");
				}
				else
				{
					vo.setSourceFlag("Extrenal Blood Bank");
				}
				if(vo.getStockStatus().equals(InpatientConfig.IN_STOCK))
				{
					vo.setTransfusionStatus("Available");
				}
				else
				{
					vo.setTransfusionStatus("Transfused");
				}
				
								
				patBloodStockVoListWithSourceFlag.add(vo);
				
			}
			
			mpEssentials.put(InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO, patBloodStockVoListWithSourceFlag);
			
			
			aboComboList=(List)mpEssentials.get(InpatientConfig.ALL_ABO_LIST);
			
			rhComboList=(List)mpEssentials.get(InpatientConfig.ALL_RH_LIST);
			
			ComponentComboList=(List)mpEssentials.get(InpatientConfig.COMPONENT_LIST_FOR_COMBO);
			
			//this create combo for component
			String componentComboStr="<option value='-1'>Select Value</option>";
			Iterator listIterator=ComponentComboList.iterator();
			while(listIterator.hasNext())
			{
				Entry entry=(Entry)listIterator.next();
				componentComboStr=componentComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
			}
			_fb.setComponentCombo(componentComboStr);
			
			//this creat combo for abo
			
			String aboComboStr="<option value='-1'>Select</option>";
			Iterator aboIterator=aboComboList.iterator();
			while(aboIterator.hasNext())
			{
				Entry entry=(Entry)aboIterator.next();
				aboComboStr=aboComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
			}
			_fb.setAboCombo(aboComboStr);
			
			
			//this creat combo for rh
			
			String rhComboStr="<option value='-1'>Select</option>";
			Iterator rhIterator=rhComboList.iterator();
			while(rhIterator.hasNext())
			{
				Entry entry=(Entry)rhIterator.next();
				rhComboStr=rhComboStr+"<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
			}
			_fb.setRhCombo(rhComboStr);
			
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
	
	
public static void saveExternalBloodStockDtl(StockEntryOfBloodFB _fb,HttpServletRequest _rq){
		
		Status objStatus=new Status();
		UserVO userVO=getUserVO(_rq);
		setSysdate(_rq);
		//HttpSession session=_rq.getSession();
		try{
			int componentRowCount=Integer.parseInt(_fb.getNumberOfRow());
			PatBloodStockDtlVO[] patBloodStockDtlVO = new PatBloodStockDtlVO[componentRowCount];
			
			for(int i=0;i<componentRowCount;i++)
			{
				patBloodStockDtlVO[i]=new PatBloodStockDtlVO();
				patBloodStockDtlVO[i].setBloodComponentID(_fb.getBloodComponentID()[i]);
				patBloodStockDtlVO[i].setBloodBagNo(_fb.getBloodBagNo()[i]);
				patBloodStockDtlVO[i].setBloodABO(_fb.getBloodAbo()[i]);
				patBloodStockDtlVO[i].setRh(_fb.getRh()[i]);
				patBloodStockDtlVO[i].setBloodBagExpiry(_fb.getExpiryDate()[i]);
				patBloodStockDtlVO[i].setBagVolume(_fb.getVolume()[i]);
				patBloodStockDtlVO[i].setBagBatchNo(_fb.getBatchNo()[i]);
				patBloodStockDtlVO[i].setTubingNo(_fb.getTubingNo()[i]);
				patBloodStockDtlVO[i].setPatCrNo(_fb.getPatCrNo());
				patBloodStockDtlVO[i].setBloodBankName(_fb.getBloodBankName());
				patBloodStockDtlVO[i].setBloodBankAddr(_fb.getBloodBankAddr());
				patBloodStockDtlVO[i].setContactNo(_fb.getContactNo());
				patBloodStockDtlVO[i].setRequisitionNo(_fb.getRequisitionNo());
				
				patBloodStockDtlVO[i].setStockStatus(InpatientConfig.IN_STOCK);
				patBloodStockDtlVO[i].setSourceFlag(InpatientConfig.ARRANGE_BY_PATIENT_FROM_OTHER_BANK);	
				patBloodStockDtlVO[i].setIsReaction(InpatientConfig.IS_REACTION_NO);
			}
			
			StockEntryOfBloodDATA.saveExternalBloodStockDtl(patBloodStockDtlVO, userVO);
			objStatus.add(Status.NEW,"Data Inserted Successfully","");
			
		}catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	}
}
