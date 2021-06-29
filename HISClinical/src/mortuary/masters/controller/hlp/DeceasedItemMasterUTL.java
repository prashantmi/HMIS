package mortuary.masters.controller.hlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;
import mrd.MrdConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.UserVO;

public class DeceasedItemMasterUTL extends ControllerUTIL
{
	public static boolean getItemTypeList(DeceasedItemMasterFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		List itemTypeLst=new ArrayList();
		boolean flag = true;
		
		try
		{
			
			for(int i=0;i<MortuaryConfig.DECEASED_ITEM_TYPE.length-1;i++)
			{
				Entry ent=new Entry();
				ent.setValue(String.valueOf(i+1));
				ent.setLabel(MortuaryConfig.DECEASED_ITEM_TYPE[i+1]);
				itemTypeLst.add(ent);
			}
		WebUTIL.setAttributeInSession(request, MortuaryConfig.DECEASED_ITEM_TYPE_LIST, itemTypeLst);		
		
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
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	
	//Saving the Data
	public static boolean saveDeceasedItemMaster(DeceasedItemMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		DeceasedItemMasterVO DeceasedItemMasterVO=new DeceasedItemMasterVO();
		try
		{
			HelperMethods.populate(DeceasedItemMasterVO, fb);
			DeceasedItemMasterDATA.saveDeceasedItemMaster(DeceasedItemMasterVO,getUserVO(request));
			exist=false;
			fb.setHmode(fb.getTempMode());
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			e.printStackTrace();
			exist=true;
			fb.setHmode(fb.getTempMode());
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return exist;
	}
	
	public static boolean getDataForModify(DeceasedItemMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DeceasedItemMasterVO _DeceasedItemMasterVO = new DeceasedItemMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sItemCode = concatid[0];
			String shospitalCode = concatid[1];
			String sItemSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setItemCode(sItemCode);
			fb.setSlNo(sItemSlno);

			_DeceasedItemMasterVO.setSlNo(sItemSlno);
			_DeceasedItemMasterVO.setItemCode(sItemCode);
			
			_DeceasedItemMasterVO = DeceasedItemMasterDATA.getDataForModify(_DeceasedItemMasterVO, userVO);
		    HelperMethods.populate(fb, _DeceasedItemMasterVO);
		    fb.setItemCode(sItemCode);
			fb.setSlNo(sItemSlno);
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
	
	public static boolean saveModDeceasedItemMaster(DeceasedItemMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			DeceasedItemMasterVO _DeceasedItemMasterVO = new DeceasedItemMasterVO();

			HelperMethods.populate( _DeceasedItemMasterVO , fb);
			fb.setHmode(fb.getTempMode());
			flag=DeceasedItemMasterDATA.saveModDeceasedItemMaster(_DeceasedItemMasterVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Item Name already exists");				
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
			objStatus.add(Status.ERROR, "", "Item Name already exists");
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
