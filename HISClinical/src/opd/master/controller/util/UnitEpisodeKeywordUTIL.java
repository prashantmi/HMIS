package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.UnitEpisodeKeywordVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.UnitEpisodeKeywordDATA;
import opd.master.controller.fb.UnitEpisodeKeywordFB;

public class UnitEpisodeKeywordUTIL extends ControllerUTIL
{
	// Getting Unit Keyword ADD Essentials
	public static void setEssential(UnitEpisodeKeywordFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();

		List allDeptLst = null;
		List notMappedUnitList = null;
		try
		{
			UserVO userVO = getUserVO(_request);

			mp = UnitEpisodeKeywordDATA.getEssentials(userVO);

			allDeptLst = (List) mp.get(OpdConfig.EssentialBO_LIST_ALL_DEPT);
			notMappedUnitList = (List) mp.get(OpdConfig.OPD_ESSENTIAL_NOT_ADDED_UNIT_LIST);

			// Getting only those Department which has Unit(not mapped unit)
			if (allDeptLst == null || allDeptLst.size() == 0) throw new HisRecordNotFoundException("No Clinical Department Found to Add");

			if (notMappedUnitList == null || notMappedUnitList.size() == 0) throw new HisRecordNotFoundException("No Clinical Unit Found to Add");

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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}

	// Adding Keyword Unit Record Unit-Wise
	public static boolean AddKeywordUnitWise(UnitEpisodeKeywordFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);

			List<UnitEpisodeKeywordVO> lstUnitKeywords = new ArrayList<UnitEpisodeKeywordVO>();

			int countKeywords = _fb.getSelectedKeyword().length;
			int countUnits = _fb.getSelectedUnit().length;
			for (int j = 0; j < countUnits; j++)
			{
				for (int i = 0; i < countKeywords; i++)
				{
					UnitEpisodeKeywordVO vo = new UnitEpisodeKeywordVO();

					vo.setEpisodeKeywordId(_fb.getSelectedKeyword()[i].trim());
					vo.setDeptUnitCode(_fb.getSelectedUnit()[j].trim());
					vo.setIsValid(Config.IS_VALID_ACTIVE);

					lstUnitKeywords.add(vo);
				}
			}
			UnitEpisodeKeywordDATA.AddKeywordUnitWise(lstUnitKeywords, userVO);
			objStatus.add(Status.INPROCESS, "Keywords Successully Added To Units.........", "");
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			saveFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			saveFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			saveFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			saveFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return saveFlag;
	}

	// Getting Unit Keyword MODIFY Essentials
	public static boolean fetchModifyData(UnitEpisodeKeywordFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String selUnit = concatid[0];
			String keywordCode = concatid[1];

			// Setting Selected PK
			_fb.setDeptUnitCode(selUnit);
			_fb.setKeywordCode(keywordCode);

			// Settign Unit Name
			String unitName = UnitEpisodeKeywordDATA.getUnitName(_fb.getDeptUnitCode(), userVO);
			_fb.setDeptUnitName(unitName);

			// Getting Essentials
			mp = UnitEpisodeKeywordDATA.getModifyUnitEpisodeKeywordEssentials(_fb.getDeptUnitCode(), userVO);

			// Removing Selected Images from Unselected
			List<EpisodeKeywordsMasterVO> allKeywords = (ArrayList<EpisodeKeywordsMasterVO>) mp.get(OpdConfig.OPD_ESSENTIAL_ALL_EPISODE_KEYWORD_LIST);
			List<EpisodeKeywordsMasterVO> selectedIKeywords = (ArrayList<EpisodeKeywordsMasterVO>) mp.get(OpdConfig.OPD_ESSENTIAL_SELECTED_KEYWORD_LIST);
			List<EpisodeKeywordsMasterVO> target = new ArrayList<EpisodeKeywordsMasterVO>();
			boolean flag = false;

			for (EpisodeKeywordsMasterVO vo : allKeywords)
			{
				flag = false;
				for (EpisodeKeywordsMasterVO selKey : selectedIKeywords)
				{
					if (selKey.getEpisodeKeywordID().trim().equalsIgnoreCase(vo.getEpisodeKeywordID().trim()))
					{
						flag = true;
						break;
					}
				}
				if (!flag) target.add(vo);
			}
			mp.put(OpdConfig.OPD_ESSENTIAL_UNSELECTED_KEYWORD_LIST, target);

			WebUTIL.setMapInSession(mp, _request);
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

	// Modifying Episode Keywords Unit Wise
	public static boolean modifyKeywordsUnitWise(UnitEpisodeKeywordFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			List<UnitEpisodeKeywordVO> lstUnitKeywords = new ArrayList<UnitEpisodeKeywordVO>();

			for (int i = 0; i < _fb.getSelectedKeyword().length; i++)
			{
				UnitEpisodeKeywordVO vo = new UnitEpisodeKeywordVO();
				vo.setEpisodeKeywordId(_fb.getSelectedKeyword()[i].trim());
				vo.setDeptUnitCode(_fb.getDeptUnitCode());
				vo.setIsValid(Config.IS_VALID_ACTIVE);
				lstUnitKeywords.add(vo);
			}

			UnitEpisodeKeywordDATA.modifyUnitEpisodeKeywords(lstUnitKeywords, _fb.getDeptUnitCode(), userVO);
			objStatus.add(Status.INPROCESS, "Keywords Successully Added To Units.........", "");
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			saveFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			saveFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			saveFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			saveFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return saveFlag;
	}

	// Getting Unit Keyword VIEW Essentials
	public static boolean fetchViewData(UnitEpisodeKeywordFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String selUnit = concatid[0];
			String keywordCode = concatid[1];

			// Setting Selected PK
			_fb.setDeptUnitCode(selUnit);
			_fb.setKeywordCode(keywordCode);

			// Settign Unit Name
			String unitName = UnitEpisodeKeywordDATA.getUnitName(_fb.getDeptUnitCode(), userVO);
			_fb.setDeptUnitName(unitName);

			// Getting Essentials
			mp = UnitEpisodeKeywordDATA.getViewUnitEpisodeKeywordEssentials(_fb.getDeptUnitCode(), userVO);

			WebUTIL.setMapInSession(mp, _request);
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
}
