package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.ICDIncludeExcludeDATA;
import opd.master.controller.fb.ICDIncludeExcludeFB;

public class ICDIncludeExcludeUTIL extends ControllerUTIL
{
	// Setting Essentials
	public static void setEssential(ICDIncludeExcludeFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(request);
			
			fb.setIcdGroupCode(fb.getControls()[0]);
			fb.setIcdSubgroupCode(fb.getControls()[1]);
			fb.setDiseaseCode(fb.getControls()[2]);
			
			mp = ICDIncludeExcludeDATA.getEssential(fb.getIcdGroupCode(),fb.getIcdSubgroupCode(),fb.getDiseaseCode(), userVO);
			fb.setIcdGroup((String)mp.get(OpdConfig.OPD_ESSENTIAL_GROUP_NAME));
			fb.setIcdSubgroup((String)mp.get(OpdConfig.OPD_ESSENTIAL_SUBGROUP_NAME));
			fb.setMainDisease((String)mp.get(OpdConfig.OPD_ESSENTIAL_DISEASE_SUBDISEASE_NAME));
			WebUTIL.setMapInSession(mp, request);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}

	// Setting Selected Disease Detail
	public static void setDiseaseDetail(ICDIncludeExcludeFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		try
		{
			List<IcdDiseaseMasterVO> lstDisease = (ArrayList<IcdDiseaseMasterVO>)request.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE);
			for(IcdDiseaseMasterVO voTemp : lstDisease)
			{
				if(voTemp.getDiseaseCode().trim().equalsIgnoreCase(fb.getDiseaseCode().trim()))
				{
					fb.setMainDisease(voTemp.getDisease());
					fb.setDiseaseTypeCode(voTemp.getDiseaseTypeCode());
					fb.setDiseaseTypeName(voTemp.getDiseaseTypeName());
					fb.setIsCommon(voTemp.getIsCommon());
					//fb.setIsDiseaseDetail(OpdConfig.YES);
					break;
				}
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Record Not Found");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Transaction Failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}

	// Saving Addendant Reason Record
	public static boolean saveRecord(ICDIncludeExcludeFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		IcdDiseaseMasterVO vo = new IcdDiseaseMasterVO();

		try
		{
			UserVO userVO = getUserVO(request);
			
			if(fb.getParentCode()!=null && fb.getParentCode().equals("-1"))	fb.setParentCode(null);
			
			List<IcdDiseaseMasterVO> lstDisease = (ArrayList<IcdDiseaseMasterVO>)request.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE);
			String mainDisease = "";
			for(IcdDiseaseMasterVO voTemp : lstDisease)
			{
				if(voTemp.getDiseaseCode().trim().equalsIgnoreCase(fb.getDiseaseCode().trim()))
				{
					mainDisease = voTemp.getFullDiseaseName();
					fb.setDiseaseTypeCode(voTemp.getDiseaseTypeCode());
					fb.setDiseaseTypeName(voTemp.getDiseaseTypeName());
					fb.setIsCommon(voTemp.getIsCommon());
					break;
				}
			}
			HelperMethods.populate(vo, fb);
			vo.setMainDisease(mainDisease);
			ICDIncludeExcludeDATA.saveRecord(vo, userVO);
			objStatus.add(Status.TRANSINPROCESS, "Record added successfully", "");
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

	// Getting Addendant Reason Record
	public static void getRecord(ICDIncludeExcludeFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		IcdDiseaseMasterVO vo = new IcdDiseaseMasterVO();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(request);

			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			fb.setDiseaseCode(concatid[0]);			
			fb.setSlNo(concatid[2]);
			
			fb.setIcdGroupCode(fb.getControls()[0]);
			fb.setIcdSubgroupCode(fb.getControls()[1]);
			fb.setDiseaseCode(fb.getControls()[2]);

			HelperMethods.populate(vo, fb);

			mp = ICDIncludeExcludeDATA.getEssential(fb.getIcdGroupCode(),fb.getIcdSubgroupCode(),fb.getDiseaseCode(), userVO);
			vo = ICDIncludeExcludeDATA.getRecord(vo, userVO);

			HelperMethods.populate(fb, vo);
			//fb.setIsDiseaseDetail(OpdConfig.YES);
			WebUTIL.setMapInSession(mp, request);

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
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	// Modifying Addendant Reason Record
	public static boolean modifyRecord(ICDIncludeExcludeFB fb, HttpServletRequest request)
	{
		boolean flag = true;
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(request);
			IcdDiseaseMasterVO vo = new IcdDiseaseMasterVO();

			if(fb.getParentCode()!=null && fb.getParentCode().equals("-1"))	fb.setParentCode(null);
			List<IcdDiseaseMasterVO> lstDisease = (ArrayList<IcdDiseaseMasterVO>)request.getSession().getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBDISEASE);
			String mainDisease = "";
			for(IcdDiseaseMasterVO voTemp : lstDisease)
			{
				if(voTemp.getDiseaseCode().trim().equalsIgnoreCase(fb.getDiseaseCode().trim()))
				{
					mainDisease =  voTemp.getFullDiseaseName();
					fb.setDiseaseTypeCode(voTemp.getDiseaseTypeCode());
					fb.setDiseaseTypeName(voTemp.getDiseaseTypeName());
					fb.setIsCommon(voTemp.getIsCommon());
					break;
				}
			}
			HelperMethods.populate(vo, fb);
			vo.setMainDisease(mainDisease);
			
			ICDIncludeExcludeDATA.updateRecord(vo, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
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
