package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import opd.OpdConfig;
import opd.master.controller.data.IcdIndexLevelMasterDATA;
import opd.master.controller.data.IcdIndexMasterDATA;
import opd.master.controller.fb.IcdIndexLevelMasterFB;
import opd.master.controller.fb.IcdIndexMasterFB;
import hisglobal.vo.IcdIndexMasterVO;


public class IcdIndexMasterUTIL  extends ControllerUTIL 
{
	
	/* populating Icd Group Combo
	 * 
	 */
	public static void getInitializeGroup(IcdIndexMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			Map<String,Object> essentialMap = IcdIndexMasterDATA.getInitializeGroup(userVO);
			
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
	 * Populating the Icd Sub Group Combo
	 */
	public static void getIcdSubGroup(IcdIndexMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexMasterDATA.getIcdSubGroupByGroupCode(fb.getIcdGroupCode(),userVO);
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
	
	
	public static void getDualIcdSubGroup(IcdIndexMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexMasterDATA.getIcdSubGroupByGroupCode(fb.getStrDualGroupCode(),userVO);
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
	public static void getIcdDisease(IcdIndexMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdDiseaseMasterVO> lstIcdDisease = IcdIndexMasterDATA.getIcdDiseaseBySubGroup(fb.getIcdSubgroupCode(),userVO);
			
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
	public static void getDualIcdDisease(IcdIndexMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<IcdDiseaseMasterVO> lstDualIcdDisease = IcdIndexMasterDATA.getIcdDiseaseBySubGroup(fb.getStrDualSubGroupCode(),userVO);
			
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
	public static boolean saveRecord(IcdIndexMasterFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		IcdIndexMasterVO icdIndexMasterVO;

		try
		{
			UserVO userVO = getUserVO(request);
			icdIndexMasterVO = new IcdIndexMasterVO();
			HelperMethods.populate(icdIndexMasterVO, fb);
			icdIndexMasterVO.setHospitalCode(userVO.getHospitalCode());

			flag =	IcdIndexMasterDATA.saveRecord(icdIndexMasterVO, userVO);
			HelperMethods.populate(fb,icdIndexMasterVO);
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
	
	
	
	public static void getModifyRecord(IcdIndexMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		IcdIndexMasterVO vo=null;
		Map<String,Object> mapModify = new HashMap<String, Object>();
		
		try
		{
			UserVO userVO = getUserVO(request);
			vo = new IcdIndexMasterVO();
			String chk = fb.getChk().replace("^", "@");
			String[] strId = chk.split("@");
			fb.setIndexCode(strId[0]);			
			fb.setSlNo(strId[1]);
			
			HelperMethods.populate(vo,fb);
			
			IcdIndexMasterDATA.getModifyRecord(vo, userVO);
			
			HelperMethods.populate(fb, vo);
			
			mapModify =  IcdIndexMasterDATA.getInitializeGroup(userVO);

		// Fetch List of ICD Sub Group & Disease
			if(vo.getDiseaseCode()!=null && !(vo.getDiseaseCode().trim().equals("")))
			{
				// Get List of Sub Group
				List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexMasterDATA.getIcdSubGroupByGroupCode(vo.getIcdGroupCode(),userVO);
				mapModify.put(OpdConfig.OPD_LIST_ICD_SUB_GROUP, lstIcdSubGroups);
				// Get List of Disease
				List<IcdDiseaseMasterVO> lstIcdDisease = IcdIndexMasterDATA.getIcdDiseaseBySubGroup(vo.getIcdSubgroupCode(),userVO);
				mapModify.put(OpdConfig.OPD_LIST_ICD_DISEASE, lstIcdDisease);
			
			}
			// Fetch List of Dual ICD Sub Group & Disease
			if(vo.getStrDualDiseaseCode()!=null && !(vo.getStrDualDiseaseCode().trim().equals("")))
			{
				// Get List of Sub Group
				List<IcdSubgroupMasterVO> lstIcdSubGroups = IcdIndexMasterDATA.getIcdSubGroupByGroupCode(vo.getStrDualGroupCode(),userVO);
				mapModify.put(OpdConfig.OPD_LIST_DUAL_ICD_SUB_GROUP, lstIcdSubGroups);
				// Get List of Disease
				List<IcdDiseaseMasterVO> lstDualIcdDisease = IcdIndexMasterDATA.getIcdDiseaseBySubGroup(vo.getStrDualSubGroupCode(),userVO);
				mapModify.put(OpdConfig.OPD_LIST_DUAL_ICD_DISEASE, lstDualIcdDisease);
				
			}	
				
			
			WebUTIL.setMapInSession(mapModify, request);
		
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
	/*
	 * To Update the Record 
	 */
	
	public static boolean modifySave(IcdIndexMasterFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		IcdIndexMasterVO icdIndexMasterVO;

		try
		{
			UserVO userVO = getUserVO(request);
			icdIndexMasterVO = new IcdIndexMasterVO();
			HelperMethods.populate(icdIndexMasterVO, fb);
			icdIndexMasterVO.setHospitalCode(userVO.getHospitalCode());

			flag	=	IcdIndexMasterDATA.modifySave(icdIndexMasterVO, userVO);
			

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
}