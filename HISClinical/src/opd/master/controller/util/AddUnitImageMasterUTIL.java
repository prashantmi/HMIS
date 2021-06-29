package opd.master.controller.util;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.OPDUnitImageMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.AddUnitImageMasterDATA;
import opd.master.controller.fb.OPDUnitImageMasterFB;

public class AddUnitImageMasterUTIL extends ControllerUTIL
{
	// * Setting Essentials for Unit Image Master
	public static void setEssential(OPDUnitImageMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		List allDeptLst = null;
		List notMappedUnitList = null;
		try
		{
			UserVO userVO = getUserVO(_request);

			mp = AddUnitImageMasterDATA.getEssentials(userVO);

			allDeptLst = (List) mp.get(OpdConfig.EssentialBO_LIST_ALL_DEPT);
			notMappedUnitList = (List) mp.get(OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST);

			// Getting only those Department which has Unit(not mapped unit)
			if (allDeptLst == null || allDeptLst.size() == 0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found");
			}
			if (notMappedUnitList == null || notMappedUnitList.size() == 0)
			{
				throw new HisRecordNotFoundException("No Clinical Unit Found");
			}

			List lstDeptsOnly = new ArrayList();

			for (int i = 0; i < allDeptLst.size(); i++)
			{
				boolean flag = false;
				Entry entDept = (Entry) allDeptLst.get(i);

				for (int j = 0; j < notMappedUnitList.size(); j++)
				{
					Entry entUnit = (Entry) notMappedUnitList.get(j);
					if (entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{
						flag = true;
						break;
					}
				}

				if (flag) lstDeptsOnly.add(entDept);
			}

			mp.put(OpdConfig.EssentialBO_LIST_ALL_DEPT, lstDeptsOnly);
			WebUTIL.setMapInSession(mp, _request);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	// * Adding Image Unit Record Unit-Wise
	public static boolean AddImageUnitWise(OPDUnitImageMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			List<OPDUnitImageMasterVO> lstUnitImages = new ArrayList<OPDUnitImageMasterVO>();
			int countImage = _fb.getSelectedImages().length;
			int countUnit = _fb.getSelectedUnit().length;
			for (int j = 0; j < countUnit; j++)
			{
				for (int i = 0; i < countImage; i++)
				{
					OPDUnitImageMasterVO voUnitImageMenu = new OPDUnitImageMasterVO();
					
					voUnitImageMenu.setImageUnitCode(_fb.getSelectedImages()[i].trim());
					voUnitImageMenu.setDeptUnitCode(_fb.getSelectedUnit()[j].trim());
					voUnitImageMenu.setIsValid(Config.IS_VALID_ACTIVE);
					
					lstUnitImages.add(voUnitImageMenu);
				}
			}
			AddUnitImageMasterDATA.AddImageUnitWise(lstUnitImages, userVO);
			objStatus.add(Status.INPROCESS, "Record Saved Successully", "");
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			saveFlag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			saveFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			saveFlag = false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			saveFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return saveFlag;
	}

	public static boolean ModifyAddImageUnitWise(OPDUnitImageMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			List<OPDUnitImageMasterVO> lstUnitImages = new ArrayList<OPDUnitImageMasterVO>();

			for (int i = 0; i < _fb.getSelectedImages().length; i++)
			{
				OPDUnitImageMasterVO voUnitImageMenu = new OPDUnitImageMasterVO();
				voUnitImageMenu.setImageUnitCode(_fb.getSelectedImages()[i].trim());
				voUnitImageMenu.setDeptUnitCode(_fb.getDeptUnitCode());
				voUnitImageMenu.setIsValid(Config.IS_VALID_ACTIVE);
				lstUnitImages.add(voUnitImageMenu);
			}

			AddUnitImageMasterDATA.ModifyAddImageUnitWise(lstUnitImages, _fb.getDeptUnitCode(), userVO);
			objStatus.add(Status.INPROCESS, "Record Modified Successully", "");
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			saveFlag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			saveFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			saveFlag = false;
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
			saveFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return saveFlag;
	}

	// * Fetching Unit Name Record
	public static boolean fetchUnitNameRecordData(OPDUnitImageMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String unitName = "";
		try
		{
			UserVO userVO = getUserVO(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String selUnit = concatid[0];
			String imagecode = concatid[1];
			String slNo = concatid[3];

			// Setting Selected Unit
			_fb.setDeptUnitCode(selUnit);
			_fb.setImageCode(imagecode);
			_fb.setSlNo(slNo);
			unitName = AddUnitImageMasterDATA.getUnitName(_fb.getDeptUnitCode(), userVO);
			_fb.setDeptUnitName(unitName);

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
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	// * Fetching Selected Images Unit Wise
	public static boolean fetchSelectedImagesUnitWise(OPDUnitImageMasterFB _fb, HttpServletRequest _request)
	{
		boolean flag = false;
		Status objStatus = new Status();
		Map mp = new HashMap();

		try
		{
			UserVO userVO = getUserVO(_request);

			mp = AddUnitImageMasterDATA.getModifyUnitImageMasterEssentials(_fb.getDeptUnitCode(), userVO);

			// Removing Selected Images from Unselected
			List allImage = (ArrayList) mp.get(OpdConfig.OPD_ESSENTIAL_ALL_IMAGE_LIST);
			List selectedImage = (ArrayList) mp.get(OpdConfig.OPD_ESSENTIAL_SELECTED_IMAGE_LIST);
			ArrayList alUnits = new ArrayList();

			for (int i = 0; i < allImage.size(); i++)
			{
				Entry entobj = (Entry) allImage.get(i);
				for (int j = 0; j < selectedImage.size(); j++)
				{
					Entry entob = (Entry) selectedImage.get(j);
					if (entob.getValue().equals(entobj.getValue()))
					{
						flag = true;
						break;
					}
					else flag = false;
				}
				if (!flag)
				{
					Entry newobj = new Entry();
					newobj.setValue(entobj.getValue());
					newobj.setLabel(entobj.getLabel());
					alUnits.add(newobj);
				}
			}
			mp.put(OpdConfig.OPD_ESSENTIAL_UNSELECTED_IMAGE_LIST, alUnits);

			WebUTIL.setMapInSession(mp, _request);
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
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	// * Getting Image for View
	public static void getImageForView(OPDUnitImageMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String deptUnitCode = _fb.getDeptUnitCode();

		try
		{
			UserVO userVO = getUserVO(_request);

			OPDUnitImageMasterVO[] arrOpdUnitImageMasterVO = AddUnitImageMasterDATA.getImageForView(deptUnitCode, userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.ARR_VIEW_IMAGE_UNIT_WISE_VO, arrOpdUnitImageMasterVO);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Record Not Found");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Transaction Failed");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Transaction Failed");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Transaction Failed");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}

}
