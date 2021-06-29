package opd.master.controller.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DrugListMasterDATA;
import opd.master.controller.fb.DrugListMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DrugListMasterVO;
import hisglobal.vo.UserVO;

public class DrugListMasterUTIL extends ControllerUTIL
{
	public static boolean saveKeywordMstDetail(DrugListMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DrugListMasterVO drugListMasterVO = new DrugListMasterVO();
			
			drugListMasterVO.setDrugListName(_fb.getDrugListName());
			drugListMasterVO.setDiseaseCode(_fb.getDiseaseCode());
			drugListMasterVO.setRemark(_fb.getRemark());
			drugListMasterVO.setSlNo("1");
			
			hasFlag=DrugListMasterDATA.saveDrugListMstDetail(drugListMasterVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {	hasFlag=false;
				 objStatus.add(Status.NEW, "Drug List Name Already Exists", "");
			 }

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
			hasFlag=false;
			objStatus.add(Status.ERROR, "", "Drug List Name already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(DrugListMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DrugListMasterVO drugListMasterVO = new DrugListMasterVO();
		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sDrugListId = concatid[0];
			String sSlno = concatid[1];
			String shospitalCode = concatid[2];
			
			// putting the selected Record Primary Key into Vo
			
			drugListMasterVO.setSlNo(sSlno);
			drugListMasterVO.setDrugListId(sDrugListId);
			drugListMasterVO.setHospitalCode(shospitalCode);
			
					
			mp = DrugListMasterDATA.getDataForModifyDrugListMst(drugListMasterVO,userVO);
			
			drugListMasterVO=(DrugListMasterVO)mp.get(OpdConfig.KEYWORD_MST_VO_FOR_MODIFY);
		    
			
			fb.setDrugListName(drugListMasterVO.getDrugListName());
			fb.setDrugListId(drugListMasterVO.getDrugListId());
			fb.setRemark(drugListMasterVO.getRemark());
			fb.setDiseaseCode(drugListMasterVO.getDiseaseCode());
			fb.setIsValid(drugListMasterVO.getIsValid());
			fb.setSlNo(drugListMasterVO.getSlNo());
			
			
			objStatus.add(Status.NEW);
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
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean saveModDrugListMaster(DrugListMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			DrugListMasterVO drugListMasterVO = new DrugListMasterVO();

			drugListMasterVO.setDrugListName(_fb.getDrugListName());
			drugListMasterVO.setDiseaseCode(_fb.getDiseaseCode());
			drugListMasterVO.setRemark(_fb.getRemark());
			drugListMasterVO.setSlNo(_fb.getSlNo());
			drugListMasterVO.setIsValid(_fb.getIsValid());
			drugListMasterVO.setDrugListId(_fb.getDrugListId());
									
			flag=DrugListMasterDATA.saveModDrugListMstDetail(drugListMasterVO,userVO);
			if(flag){
				objStatus.add(Status.DONE,"","Record Modified Successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Drug List Name already exists");
				
			}
			
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
			objStatus.add(Status.ERROR, "", "Drug List Name already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
}
