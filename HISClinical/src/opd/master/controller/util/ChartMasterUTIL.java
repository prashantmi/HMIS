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
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.ChartMasterDATA;
import opd.master.controller.fb.ChartMasterFB;

public class ChartMasterUTIL extends ControllerUTIL
{
	public static boolean saveChart(ChartMasterFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		ChartMasterVO chartMasterVO=new ChartMasterVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(chartMasterVO, _fb);
			
			if(_fb.getGenerationType().equals("-1"))
			{
				chartMasterVO.setBodyQuery("");
				chartMasterVO.setFooterQuery("");
			}
			ChartMasterDATA.saveChart(chartMasterVO,userVO);
			flag=true;
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
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
		return flag;
	}
	
public static void getModifyDetail(ChartMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
	
		ChartMasterVO chartMasterVO=new ChartMasterVO();
		try
		{
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");
			String chartId = concatid[0];
			String serialNo = concatid[2];
			
			chartMasterVO.setChartId(chartId);
			chartMasterVO.setSlNo(serialNo);
			_fb.setChartId(chartId);
			_fb.setSlNo(serialNo);
			
			chartMasterVO = ChartMasterDATA.getModifyDetail(chartMasterVO,userVO);
			HelperMethods.populate(_fb, chartMasterVO);
//			if(_fb.getHmode().equals("VIEW"))
//			{
//				if(_fb.getChartCategory().equals(OpdConfig.CHART_CATEGORY_OPD))
//				{
//					_fb.setChartCategory(OpdConfig.CHART_CATEGORY_OPD_LABEL);
//				}
//				else if(_fb.getChartCategory().equals(OpdConfig.CHART_CATEGORY_IPD))
//				{
//					_fb.setChartCategory(OpdConfig.CHART_CATEGORY_IPD_LABEL);
//				}
//				else if(_fb.getChartCategory().equals(OpdConfig.CHART_CATEGORY_OPD_IPD))
//				{
//					_fb.setChartCategory(OpdConfig.CHART_CATEGORY_OPD_IPD_LABEL);
//				}
//						
//			}
			if(_fb.getGenerationType().equals(OpdConfig.CHART_GENERATION_TYPE_ROW_WISE))
			{
				_fb.setGenerationTypeLabel(OpdConfig.CHART_GENERATION_TYPE_ROW_WISE_LABEL);
			}
			else if(_fb.getGenerationType().equals(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE))
			{
				_fb.setGenerationTypeLabel(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE_LABEL);
			}
//			}
			
			objStatus.add(Status.DONE);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		
	}


	
	
	/**
	 * modify the old record and insert new record in the database
	 * @param _fb
	 * @param _request
	 */
	
	public static boolean modifySave(ChartMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		boolean flag=false;
		ChartMasterVO chartMasterVO=new ChartMasterVO();
		try
		{
			HelperMethods.populate(chartMasterVO, _fb);
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String chartId=primaryKey[0];
			String slNo=primaryKey[2];
			
			chartMasterVO.setChartId(chartId);
			chartMasterVO.setSlNo(slNo);
			if(_fb.getGenerationTypeLabel().equals(OpdConfig.CHART_GENERATION_TYPE_ROW_WISE_LABEL))
			chartMasterVO.setGenerationType(OpdConfig.CHART_GENERATION_TYPE_ROW_WISE);
			if(_fb.getGenerationTypeLabel().equals(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE_LABEL))
				chartMasterVO.setGenerationType(OpdConfig.CHART_GENERATION_TYPE_COLUMN_WISE);
			ChartMasterDATA.modifySave(chartMasterVO,userVO);
			flag=true;
			objStatus.add(Status.DONE);
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			
		}
		catch (HisException e)
		{
			flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			
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
