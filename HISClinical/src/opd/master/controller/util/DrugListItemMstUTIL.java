package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.DrugListItemMasterDATA;
import opd.master.controller.fb.DrugListItemMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DrugDoseVO;
import hisglobal.vo.DrugListItemMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class DrugListItemMstUTIL extends ControllerUTIL
{
	public static void setEssentials(DrugListItemMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		Map mpEssentials = null;
		List allDrugList=null;
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			
			session.removeAttribute(OpdConfig.SELECTED_DRUG_LIST);
			PatientDetailVO voDP = new PatientDetailVO();
			mpEssentials=DrugListItemMasterDATA.getDrugListItemMasterEssential(voDP,userVO);
			
			allDrugList=(List)mpEssentials.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ESSENTIALS_LIST_ALL_DRUGS, allDrugList);
			
			WebUTIL.setMapInSession(mpEssentials, _rq);
			
			_fb.setStatusFlag(OpdConfig.STATUS_FLAG_FOR_ADD);
			_fb.setLength("0");
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "No Drug List Found", "");
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
	
	public static void getDoseData(DrugListItemMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List allDrugDoseVoList=null;
		List drugDoseList=new ArrayList();
		try
		{
			//UserVO userVO = getUserVO(_rq);
						
			allDrugDoseVoList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			
			DrugDoseVO drugDoseVo=null;
			
			for(int i=0;i<allDrugDoseVoList.size();i++)
			{
				drugDoseVo=(DrugDoseVO)allDrugDoseVoList.get(i);
				if(drugDoseVo.getItemTypeId().equals(_fb.getItemId().split("#")[1]))
				{
					Entry obj=new Entry();
					obj.setLabel(drugDoseVo.getDoseName());
					obj.setValue(drugDoseVo.getDoseId());
					drugDoseList.add(obj);
				}
			}
			
			
			
			session.setAttribute(OpdConfig.DRUG_DOSE_LIST_FOR_PARTICULAR_ITEMTYPE, drugDoseList);
			
			if(drugDoseList.size()==0)
			{
				objStatus.add(Status.TRANSINPROCESS,"No Doses Found","");
			}
			else
			{
				objStatus.add(Status.TRANSINPROCESS);
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
	
	public static void addRow(DrugListItemMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List drugDoseList=null;
		List allDrugList=null;
		List allFreqList=null;
		List selectedDrugList=null;
		
		try
		{
			//UserVO userVO = getUserVO(_rq);
			
			selectedDrugList=(List)session.getAttribute(OpdConfig.SELECTED_DRUG_LIST);
			if(selectedDrugList==null)
			{
				selectedDrugList=new ArrayList();
			}
						
			DrugListItemMstVO vo=new DrugListItemMstVO();
			
			vo.setDrugId(_fb.getItemId());
			vo.setFrequencyId(_fb.getFrequencyId());
			vo.setDoseId(_fb.getDoseId());
			vo.setDays(_fb.getDays());
			vo.setIsEmptyStomach(_fb.getIsEmptyStomatch());
			vo.setRemarks(_fb.getRemark());
			
			allDrugList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
					
			for(int i=0;i<allDrugList.size();i++)
			{
				Entry obj=(Entry)allDrugList.get(i);
				
				if(obj.getValue().equals(_fb.getItemId()))
				{
					vo.setDrugName(obj.getLabel());
					break;
				}
			}
				
			drugDoseList=(List)session.getAttribute(OpdConfig.DRUG_DOSE_LIST_FOR_PARTICULAR_ITEMTYPE);
			
			for(int i=0;i<drugDoseList.size();i++)
			{
				Entry obj=(Entry)drugDoseList.get(i);
				
				if(obj.getValue().equals(_fb.getDoseId()))
				{
					vo.setDoseName(obj.getLabel());
					break;
				}
			}
			
			allFreqList=(List)session.getAttribute(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY);
			
			for(int i=0;i<allFreqList.size();i++)
			{
				Entry obj=(Entry)allFreqList.get(i);
				
				if(obj.getValue().equals(_fb.getFrequencyId()))
				{
					vo.setFrequencyName(obj.getLabel());
					break;
				}
			}
			
			if(_fb.getIsEmptyStomatch()!=null)
			{
				vo.setIsEmptyStomachName("Yes");
				vo.setIsEmptyStomach("1");
			}
			else
			{
				vo.setIsEmptyStomachName("No");
				vo.setIsEmptyStomach("0");
			}
			selectedDrugList.add(vo);
			
			_fb.setItemId("-1");
			_fb.setDoseId("-1");
			_fb.setFrequencyId("-1");
			_fb.setDays("");
			_fb.setIsEmptyStomatch(null);
			_fb.setRemark("");
			
			session.setAttribute(OpdConfig.SELECTED_DRUG_LIST, selectedDrugList);
			
			Integer length=selectedDrugList.size();
			_fb.setLength(length.toString());
			
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
	
	
	public static void deleteRow(DrugListItemMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List selectedDrugList=null;
		
		try
		{
			
			//UserVO userVO = getUserVO(_rq);
			
			selectedDrugList=(List)session.getAttribute(OpdConfig.SELECTED_DRUG_LIST);
			
			Iterator itr=selectedDrugList.iterator();
			 int count=0;
						 
			 while(itr.hasNext())
			 {
				 //DrugListItemMstVO vo=(DrugListItemMstVO)itr.next();
				if(count==Integer.parseInt(_fb.getDeleteIndex()))
				{
					//itr.remove();
					selectedDrugList.remove(itr.next());
					break;
				}
				count++;
				itr.next();
			}
			
			 Integer length=selectedDrugList.size();
				_fb.setLength(length.toString());
			 
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
	
	public static void save(DrugListItemMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List selectedDrugList=null;
		
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			
			selectedDrugList=(List)session.getAttribute(OpdConfig.SELECTED_DRUG_LIST);
			
			for(int i=0;i<selectedDrugList.size();i++)
			{
				DrugListItemMstVO vo=(DrugListItemMstVO)selectedDrugList.get(i);
				vo.setDrugId(vo.getDrugId().split("#")[0]);
				vo.setDrugListId(_fb.getDrugListNameId());
			}
			
			
			DrugListItemMasterDATA.saveDrugListItemMstDetail(selectedDrugList, userVO);
			
			objStatus.add(Status.TRANSINPROCESS ,"Record Added Successfully", "");
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
	
	public static void getDataForModify(DrugListItemMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DrugListItemMstVO drugListItemMstVO = new DrugListItemMstVO();
		HttpSession session=WebUTIL.getSession(_request);
		Map mp=new HashMap();
		List allDrugListList=null;
		List drugListNameList=null;
		List allDrugList=null;
		List lstDrugDosesVO=null;
		List lstFreq=null;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String drugListId = concatid[0];
			String itemId = concatid[1];
			String slNo = concatid[2];
			String hospitalCode = concatid[3];
			
			// putting the selected Record Primary Key into Vo
			
			drugListItemMstVO.setDrugListId(drugListId);
			drugListItemMstVO.setDrugId(itemId);
			drugListItemMstVO.setSlNo(slNo);
			drugListItemMstVO.setHospitalCode(hospitalCode);
			
			fb.setDrugListNameId(drugListId);
			
			mp = DrugListItemMasterDATA.getDataForModifyDrugListItemMst(drugListItemMstVO,userVO);
			
			allDrugListList=(List)mp.get(OpdConfig.SELECTED_DRUG_LIST);
			DrugListItemMstVO vo=(DrugListItemMstVO)allDrugListList.get(0);
			fb.setDrugListName(vo.getDrugListName());
			
			Integer length=allDrugListList.size();
			fb.setLength(length.toString());
			
			fb.setStatusFlag(OpdConfig.STATUS_FLAG_FOR_MODIFY);
			
			session.setAttribute(OpdConfig.SELECTED_DRUG_LIST, allDrugListList);
			
			drugListNameList=(List)mp.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGLIST_NAME_LIST);
			session.setAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGLIST_NAME_LIST, drugListNameList);
			
			allDrugList=(List)mp.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS);
			session.setAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUGS, allDrugList);
									
			lstDrugDosesVO=(List)mp.get(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES);
			session.setAttribute(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES, lstDrugDosesVO);
			
			lstFreq=(List)mp.get(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY);
			session.setAttribute(OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY, lstFreq);			
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static void saveModify(DrugListItemMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = WebUTIL.getSession(_rq);
		List selectedDrugList=null;
		
		try
		{
			
			UserVO userVO = getUserVO(_rq);
			
			selectedDrugList=(List)session.getAttribute(OpdConfig.SELECTED_DRUG_LIST);
			
			for(int i=0;i<selectedDrugList.size();i++)
			{
				DrugListItemMstVO vo=(DrugListItemMstVO)selectedDrugList.get(i);
				vo.setDrugId(vo.getDrugId().split("#")[0]);
				vo.setDrugListId(_fb.getDrugListNameId());
			}
			
			DrugListItemMasterDATA.saveModifyDrugListItemMstDetail(selectedDrugList, userVO);
			
			objStatus.add(Status.TRANSINPROCESS ,"Record Modified Successfully", "");
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
