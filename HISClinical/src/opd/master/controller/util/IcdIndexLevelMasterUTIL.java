package opd.master.controller.util;

import opd.master.controller.data.IcdIndexLevelMasterDATA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.fb.IcdIndexLevelMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
 */
public class IcdIndexLevelMasterUTIL  extends ControllerUTIL 
{
	public static void getInitializeAdd(IcdIndexLevelMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			Map<String,Object> essentialMap = IcdIndexLevelMasterDATA.getInitializeAdd(userVO);
			
			WebUTIL.setMapInSession(essentialMap, request);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}	
	
	
	/*
	 * Populating the Parent Modifier Combo
	 */
	public static boolean getParentModifier(IcdIndexLevelMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();
		boolean flag = true;

		try
		{	
			UserVO userVO = getUserVO(request);
			
			IcdIndexLevelMasterVO icdIndexLevelMasterVO=new IcdIndexLevelMasterVO();
			HelperMethods.populate(icdIndexLevelMasterVO, fb);
				
			List<Entry> lstParentModifier = IcdIndexLevelMasterDATA.getParentModifier(icdIndexLevelMasterVO,userVO);
			
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_PARENT_MODIFIER, lstParentModifier);
			if(lstParentModifier==null || lstParentModifier.size()==0)
			{
				fb.setModifierLevel("1");
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}
			else
			objStatus.add(Status.NEW);
			

		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flag = false;
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	
	/*
	 * Populating the Icd Sub Group Combo
	 */
	public static void getIcdSubGroup(IcdIndexLevelMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexLevelMasterDATA.getIcdSubGroupByGroupCode(fb.getIcdGroupCode(),userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_LIST_ICD_SUB_GROUP, lstIcdSubGroups);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	/*
	 * Populating the Dual Icd Sub Group Combo
	 */
	public static void getDualIcdSubGroup(IcdIndexLevelMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexLevelMasterDATA.getIcdSubGroupByGroupCode(fb.getDualIcdGroupCode(),userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_LIST_DUAL_ICD_SUB_GROUP, lstIcdSubGroups);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	/*
	 * Populating the Icd Disease Combo
	 */
	public static void getIcdDisease(IcdIndexLevelMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdDiseaseMasterVO> lstIcdDisease = IcdIndexLevelMasterDATA.getIcdDiseaseBySubGroup(fb.getIcdSubgroupCode(),userVO);
			
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_LIST_ICD_DISEASE, lstIcdDisease);
			if(lstIcdDisease==null || lstIcdDisease.size()==0)
			{
				objStatus.add(Status.NEW,"","No Icd Disease Found");
			}
			else	objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	/*
	 * Populating the Dual Icd Disease Combo
	 */
	public static void getDualIcdDisease(IcdIndexLevelMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdDiseaseMasterVO> lstDualIcdDisease = IcdIndexLevelMasterDATA.getIcdDiseaseBySubGroup(fb.getDualIcdSubGroupCode(),userVO);
			
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_LIST_DUAL_ICD_DISEASE, lstDualIcdDisease);
			if(lstDualIcdDisease==null || lstDualIcdDisease.size()==0)
			{
				objStatus.add(Status.NEW,"","No Dual Icd Disease Found");
			}
			else	objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	/*
	 * Saving the Record on the Add Page
	 */
	public static boolean saveRecord(IcdIndexLevelMasterFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		IcdIndexLevelMasterVO icdIndexLevelMasterVO;

		try
		{
			UserVO userVO = getUserVO(request);
			icdIndexLevelMasterVO = new IcdIndexLevelMasterVO();
			HelperMethods.populate(icdIndexLevelMasterVO, fb);
			icdIndexLevelMasterVO.setHospitalCode(userVO.getHospitalCode());

			flag =	IcdIndexLevelMasterDATA.saveRecord(icdIndexLevelMasterVO, userVO);
			if(flag)
			objStatus.add(Status.NEW, "Record added successfully", "");
			else
			objStatus.add(Status.NEW, "Duplicate Record Exists","");
	
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	public static void getModifyRecord(IcdIndexLevelMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		IcdIndexLevelMasterVO vo=null;
		Map<String,Object> map = new HashMap<String, Object>();
		String [] strDiseases = null;
		try
		{
			UserVO userVO = getUserVO(request);
			vo = new IcdIndexLevelMasterVO();
//	Primary key 	1000071008^101^1 	
			String chk = fb.getChk().replace("^", "@");
			String[] strId = chk.split("@");
			fb.setIndexModifierID(strId[0]);			
			fb.setSlNo(strId[2]);
			
			fb.setIndexCode(fb.getControls()[0]);
		
			HelperMethods.populate(vo, fb);
			
			map =  IcdIndexLevelMasterDATA.getInitializeAdd(userVO);

			IcdIndexLevelMasterDATA.getModifyRecord(vo, userVO);
			
			if(vo.getParentIndexModifierCode()!=null && !(vo.getParentIndexModifierCode().trim().equals("")))
			{
				// Get List of Parent Modifier
				List<Entry> lstParentModifier = IcdIndexLevelMasterDATA.getParentModifier(vo,userVO);
				map.put(OpdConfig.OPD_ESSENTIAL_LIST_ICD_PARENT_MODIFIER, lstParentModifier);

			}
			
			// Fetch List of ICD Sub Group & Disease
			if(vo.getDiseaseCode()!=null && !(vo.getDiseaseCode().trim().equals("")))
			{
				// Get List of Sub Group
				List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexLevelMasterDATA.getIcdSubGroupByGroupCode(vo.getIcdGroupCode(),userVO);
				map.put(OpdConfig.OPD_LIST_ICD_SUB_GROUP, lstIcdSubGroups);
				// Get List of Disease
				List<IcdDiseaseMasterVO> lstIcdDisease = IcdIndexLevelMasterDATA.getIcdDiseaseBySubGroup(vo.getIcdSubgroupCode(),userVO);
				map.put(OpdConfig.OPD_LIST_ICD_DISEASE, lstIcdDisease);
				
				if(lstIcdDisease==null || lstIcdDisease.size()==0)
				{
					objStatus.add(Status.NEW,"","No Icd Disease Found");
				}
				else	objStatus.add(Status.NEW);
				
			}
			// Fetch List of Dual ICD Sub Group & Disease
			if(vo.getDualDiseaseCode()!=null && !(vo.getDualDiseaseCode().trim().equals("")))
			{
				// Get List of Sub Group
				List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexLevelMasterDATA.getIcdSubGroupByGroupCode(vo.getDualIcdGroupCode(),userVO);
				map.put(OpdConfig.OPD_LIST_DUAL_ICD_SUB_GROUP, lstIcdSubGroups);
				// Get List of Disease
				List<IcdDiseaseMasterVO> lstDualIcdDisease = IcdIndexLevelMasterDATA.getIcdDiseaseBySubGroup(vo.getDualIcdSubGroupCode(),userVO);
				map.put(OpdConfig.OPD_LIST_DUAL_ICD_DISEASE, lstDualIcdDisease);
				
				if(lstDualIcdDisease==null || lstDualIcdDisease.size()==0)
				{
					objStatus.add(Status.NEW,"","No Dual Icd Disease Found");
				}
				else	objStatus.add(Status.NEW);
			}	
				
			HelperMethods.populate(fb, vo);
			WebUTIL.setMapInSession(map, request);
			
			objStatus.add(Status.NEW);
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
		
	}
	
	public static boolean modifySave(IcdIndexLevelMasterFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		IcdIndexLevelMasterVO icdIndexLevelMasterVO;

		try
		{
			UserVO userVO = getUserVO(request);
			icdIndexLevelMasterVO = new IcdIndexLevelMasterVO();
			HelperMethods.populate(icdIndexLevelMasterVO, fb);
			icdIndexLevelMasterVO.setHospitalCode(userVO.getHospitalCode());

			flag	=	IcdIndexLevelMasterDATA.modifySave(icdIndexLevelMasterVO, userVO);
			

			if(flag)
				objStatus.add(Status.TRANSINPROCESS, "Record added successfully", "");
			else
				objStatus.add(Status.UNSUCESSFULL, "Duplicate Record Exists","");
		}
		catch (HisRecordNotFoundException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
		
	}
	
	/*
	 * To View Page
	 */	
	public static void getViewRecord(IcdIndexLevelMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		IcdIndexLevelMasterVO vo=null;
		String [] strDiseases = null;
		try
		{
			UserVO userVO = getUserVO(request);
			vo = new IcdIndexLevelMasterVO();
//	Primary key 	1000071008^101^1 	
			String chk = fb.getChk().replace("^", "@");
			String[] strId = chk.split("@");
			fb.setIndexModifierID(strId[0]);			
			fb.setSlNo(strId[2]);
			
			fb.setIndexCode(fb.getControls()[0]);
		
			HelperMethods.populate(vo, fb);

			IcdIndexLevelMasterDATA.getViewRecord(vo, userVO);
			

				
			HelperMethods.populate(fb, vo);
			
			objStatus.add(Status.NEW);
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
	}
}