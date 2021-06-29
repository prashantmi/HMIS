package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ChartParameterMappingDATA;
import opd.master.controller.fb.ChartParameterMappingFB;

public class ChartParameterMappingUTIL extends ControllerUTIL
{
	// Getting Essentials for Chart Parameter Master
	public static void getEssentials(ChartParameterMappingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			UserVO _userVO = getUserVO(_rq);
			Map<String,Object> map = ChartParameterMappingDATA.getEssentials(_userVO);
			WebUTIL.setMapInSession(map, _rq);
			_fb.setHmode("NEW");
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, "", e.getMessage());
		}
		catch (HisInsertNotAllowedException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Exception", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Exception", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	// Getting Parameter List for Selected Chart
	public static void getParameters(ChartParameterMappingFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		HttpSession session = _request.getSession();
		
		List<Entry> lstAllParaClinical = null;
		List<Entry> lstAllParaInv = null;
		List<Entry> lstAllParaInOut = null;
		List<Entry> lstNotParaClinical = new ArrayList<Entry>();
		List<Entry> lstNotParaInv = new ArrayList<Entry>();
		List<Entry> lstNotParaInOut = new ArrayList<Entry>();
		List<ChartParameterMappingVO> lstParaClinical = null;
		List<ChartParameterMappingVO> lstParaInv = null;
		List<ChartParameterMappingVO> lstParaInOut = null;
		
		List<ChartMasterVO> lstCharts = null;
		
		try
		{
			UserVO _userVO = getUserVO(_request);			
			mp = ChartParameterMappingDATA.getParameterForChart(_fb.getChartId(), _userVO);
			
			lstCharts = (ArrayList<ChartMasterVO>)session.getAttribute(OpdConfig.CHART_NAME_LIST);
			ChartMasterVO voChart = null;
			
			for(ChartMasterVO v : lstCharts)
				if(v.getChartId().equalsIgnoreCase(_fb.getChartId()))
				{
					voChart = v;
					break;
				}
			if(voChart!=null)	HelperMethods.populate(_fb, voChart);
			
			// Clinical Parameters
			lstParaClinical = (ArrayList<ChartParameterMappingVO>) mp.get(OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL);
			lstAllParaClinical = (ArrayList<Entry>) session.getAttribute(OpdConfig.ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL);
			for(Entry ent : lstAllParaClinical)
			{
				boolean flag = true;
				for(ChartParameterMappingVO vo : lstParaClinical)
					if(vo.getParaId().equalsIgnoreCase(ent.getValue()))
					{
						flag = false;
						break;						
					}
				if(flag)	lstNotParaClinical.add(ent);				
			}

			// Investigation Parameters
			lstParaInv = (ArrayList<ChartParameterMappingVO>) mp.get(OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION);
			lstAllParaInv = (ArrayList<Entry>) session.getAttribute(OpdConfig.ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION);
			for(Entry ent : lstAllParaInv)
			{
				boolean flag = true;
				for(ChartParameterMappingVO vo : lstParaInv)
					if(vo.getParaId().equalsIgnoreCase(ent.getValue()))
					{
						flag = false;
						break;						
					}
				if(flag)	lstNotParaInv.add(ent);				
			}
				
			// Intake Output Parameters
			lstParaInOut = (ArrayList<ChartParameterMappingVO>) mp.get(OpdConfig.ADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT);
			lstAllParaInOut = (ArrayList<Entry>) session.getAttribute(OpdConfig.ALL_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT);
			for(Entry ent : lstAllParaInOut)
			{
				boolean flag = true;
				for(ChartParameterMappingVO vo : lstParaInOut)
					if(vo.getParaId().equalsIgnoreCase(ent.getValue()))
					{
						flag = false;
						break;						
					}
				if(flag)	lstNotParaInOut.add(ent);				
			}

			mp.put(OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_CLINICAL, lstNotParaClinical);
			mp.put(OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INVESTIGATION, lstNotParaInv);
			mp.put(OpdConfig.UNADDED_PARAMETER_LIST_OF_PARAMETER_TYPE_INTAKE_OUTPUT, lstNotParaInOut);
			WebUTIL.setMapInSession(mp, _request);
			objStatus.add(Status.NEW);
			objStatus.add(Status.LIST);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.NEW, "", e.getMessage());
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

	// For saving Chart Parameter details
	public static void saveChartParaMapping(ChartParameterMappingFB _fb, HttpServletRequest _request)
	{
		UserVO _userVO = getUserVO(_request);
		Status objStatus = new Status();
		List<ChartParameterMappingVO> lstAddPara = new ArrayList<ChartParameterMappingVO>();
		try
		{
			int seq = 0;
			// Clinical Parameters
			if (_fb.getSelectedPara() != null && _fb.getSelectedPara().length != 0)
			{
				for (int i = 0; i < _fb.getSelectedPara().length; i++)
				{
					seq++;
					ChartParameterMappingVO vo = new ChartParameterMappingVO();
					vo.setChartName(_fb.getChartId());
					vo.setPara(_fb.getSelectedPara()[i]);
					vo.setParaType(OpdConfig.CHART_PARAMETER_TYPE_CLINICAL);
					vo.setDisplayOrder(Integer.toString(seq));
					lstAddPara.add(vo);
				}
			}
			// Investigation Parameters
			if (_fb.getArrSelectedInvPara() != null && _fb.getArrSelectedInvPara().length != 0)
			{
				for (int i = 0; i < _fb.getArrSelectedInvPara().length; i++)
				{
					seq++;
					ChartParameterMappingVO vo = new ChartParameterMappingVO();
					vo.setChartName(_fb.getChartId());
					vo.setPara(_fb.getArrSelectedInvPara()[i]);
					vo.setParaType(OpdConfig.CHART_PARAMETER_TYPE_INVESTIGATION);
					vo.setDisplayOrder(Integer.toString(seq));
					lstAddPara.add(vo);
				}
			}
			// Intake Output Parameters
			if (_fb.getArrSelectedInOutPara() != null && _fb.getArrSelectedInOutPara().length != 0)
			{
				for (int i = 0; i < _fb.getArrSelectedInOutPara().length; i++)
				{
					seq++;
					ChartParameterMappingVO vo = new ChartParameterMappingVO();
					vo.setChartName(_fb.getChartId());
					vo.setPara(_fb.getArrSelectedInOutPara()[i]);
					vo.setParaType(OpdConfig.CHART_PARAMETER_TYPE_INTAKE_OUTPUT);
					vo.setDisplayOrder(Integer.toString(seq));
					lstAddPara.add(vo);
				}
			}
			ChartParameterMappingDATA.saveChartParameterMapping(_fb.getChartId(), lstAddPara, _userVO);
			
			objStatus.add(Status.NEW, "", "Record Saved Successfully");
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
}
