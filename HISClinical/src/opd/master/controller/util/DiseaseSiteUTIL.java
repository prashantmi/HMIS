package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DiseaseSiteMasterVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.DiseaseSiteDATA;
import opd.master.controller.fb.DiseaseSiteFB;

public class DiseaseSiteUTIL extends ControllerUTIL
{
	// Settings Essentials
	public static void setEssentials(DiseaseSiteFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		UserVO userVO = null;
		
		try
		{
			userVO = getUserVO(_request);
			mp = DiseaseSiteDATA.getEssentials(userVO);
			
			WebUTIL.setMapInSession(mp, _request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Exception");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}
	
	// Gettings Subgroup Group Wise
	public static void setSubGroups(DiseaseSiteFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<IcdSubgroupMasterVO> lstSubgroup = new ArrayList<IcdSubgroupMasterVO>();
		UserVO userVO = null;
		
		try
		{
			userVO = getUserVO(_request);
			lstSubgroup = DiseaseSiteDATA.getSubGroupsByGroup(_fb.getIcdGroupCode(), userVO);			
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE, lstSubgroup);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Exception");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}
	
	// Gettings Diseases Subgroup Wise
	public static void setDiseaseSubGroupWise(DiseaseSiteFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();
		UserVO userVO = null;
		
		try
		{
			userVO = getUserVO(_request);
			lstDisease = DiseaseSiteDATA.getDiseaseBySubGroup(_fb.getIcdSubgroupCode(), userVO);			
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, lstDisease);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Exception");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	public static boolean save(DiseaseSiteFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		boolean flag = true;
		UserVO userVO = null;
		try
		{
			userVO = getUserVO(_request);
			
			DiseaseSiteMasterVO diseaseSiteVO = new DiseaseSiteMasterVO();
			HelperMethods.populate(diseaseSiteVO, _fb);
			if(diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
			{
				diseaseSiteVO.setIcdSiteCode(null);
				diseaseSiteVO.setIcdSubgroupDiseaseCode(null);
			}
			else if(diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP))
				diseaseSiteVO.setIcdSubgroupDiseaseCode(_fb.getIcdSubgroupCode());
			else if(diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE))
				diseaseSiteVO.setIcdSubgroupDiseaseCode(_fb.getDiseaseCode());
			
			DiseaseSiteDATA.save(diseaseSiteVO, userVO);
		}
		catch(HisDuplicateRecordException e)
		{	   		   	
			flag = false;
			e.printStackTrace(); 
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch(Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Exception");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return flag;
	}

	// Settings Essentials
	public static void getRecord(DiseaseSiteFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		UserVO userVO = null;		
		try
		{
			userVO = getUserVO(_request);
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			_fb.setDiseaseSiteId(concatid[0]);			
			
			DiseaseSiteMasterVO vo = new DiseaseSiteMasterVO();
			HelperMethods.populate(vo, _fb);

			mp = DiseaseSiteDATA.getEssentials(userVO);
			vo = DiseaseSiteDATA.getRecord(vo, userVO);
			HelperMethods.populate(_fb, vo);
			if(!vo.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
			{
				List<IcdSubgroupMasterVO> lstSubgroup = new ArrayList<IcdSubgroupMasterVO>();
				lstSubgroup = DiseaseSiteDATA.getSubGroupsByGroup(_fb.getIcdGroupCode(), userVO);			
				WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE, lstSubgroup);
			}
			if(vo.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE))
			{
				List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();
				lstDisease = DiseaseSiteDATA.getDiseaseBySubGroup(_fb.getIcdSubgroupCode(), userVO);			
				WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, lstDisease);
			}

			WebUTIL.setMapInSession(mp, _request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Exception");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	public static boolean modifyRecord(DiseaseSiteFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		boolean flag = true;
		UserVO userVO = null;
		try
		{
			userVO = getUserVO(_request);
			
			DiseaseSiteMasterVO diseaseSiteVO = new DiseaseSiteMasterVO();
			HelperMethods.populate(diseaseSiteVO, _fb);
			if(diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED))
			{
				diseaseSiteVO.setIcdSiteCode(null);
				diseaseSiteVO.setIcdSubgroupDiseaseCode(null);
			}
			else if(diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_SUBGROUP))
				diseaseSiteVO.setIcdSubgroupDiseaseCode(_fb.getIcdSubgroupCode());
			else if(diseaseSiteVO.getIcdCodeType().equals(OpdConfig.ICD_DISEASE_SITE_CODETYPE_ICD_DISEASE))
				diseaseSiteVO.setIcdSubgroupDiseaseCode(_fb.getDiseaseCode());
			
			DiseaseSiteDATA.modify(diseaseSiteVO, userVO);
			objStatus.add(Status.TRANSINPROCESS,"Record Modified Successfully","");
		}
		catch(HisDuplicateRecordException e)
		{	   		   	
			flag = false;
			e.printStackTrace(); 
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch(Exception e)
		{
			flag = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Exception");
		}
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return flag;
	}
}