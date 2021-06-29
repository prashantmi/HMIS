package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.ChartUnitMapppingVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ChartUnitMappingMstDATA;
import opd.master.controller.fb.ChartUnitMappingMstFB;

public class ChartUnitMappingMstUTIL extends ControllerUTIL
{
	public static void getChartUnitListEssential(ChartUnitMappingMstFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllChartList=null;
		HttpSession session=WebUTIL.getSession(_request);
		try
		{
			session.removeAttribute(OpdConfig.MAPPED_UNIT_CHARTLIST_LIST);
			
			essentialMap=ChartUnitMappingMstDATA.getChartUnitListEssential(getUserVO(_request));
			
			lstDept=(List)essentialMap.get(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST);
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_CHART);
			listAllChartList=(List)essentialMap.get(OpdConfig.CHART_NAME_LIST);
			
		
			session.setAttribute(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			session.setAttribute(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_CHART, listNotMappedUnit);
			session.setAttribute(OpdConfig.CHART_NAME_LIST, listAllChartList);
			
			
			// Getting only those Department which has  Unit(not mapped unit) 
			
			if(lstDept==null || lstDept.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Department Found to Add");
			}
			if(listAllUnit==null || listAllUnit.size()==0)
			{
				throw new HisRecordNotFoundException("No Clinical Unit Found to Add");
			}
			
			List lstDeptsOnly = new ArrayList();
			
			for(int i=0;i<lstDept.size();i++)
			{				
				boolean flag = false;
				Entry entDept=(Entry)lstDept.get(i);
				
				for(int j=0;j<listNotMappedUnit.size();j++)
				{
					Entry entUnit=(Entry)listNotMappedUnit.get(j);
					if(entUnit.getValue().substring(0, 3).equals(entDept.getValue()))
					{	flag = true;	break;	}
				}
					
				if(flag)	lstDeptsOnly.add(entDept);
			}
			
			session.setAttribute(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDeptsOnly);
			
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
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
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void saveChartUnitList(ChartUnitMappingMstFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List unitChartListVOLst=new ArrayList();
		try
		{
			int countFile=_fb.getSelectedChartListCode().length;
			int countUnit=_fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
				for(int i=0;i<countFile;i++)
				{
					ChartUnitMapppingVO chartUnitMappingVO=new ChartUnitMapppingVO();
					
					chartUnitMappingVO.setChartId(_fb.getSelectedChartListCode()[i]);
					chartUnitMappingVO.setDeptUnitCode(_fb.getSelectedUnit()[j].trim());
					
					if(_fb.getDefaultChartListCode().equals(_fb.getSelectedChartListCode()[i]))
					{
						if(_fb.getIsDefault().equals(OpdConfig.CURRENT_VISIT))
						chartUnitMappingVO.setIsDefault(OpdConfig.CURRENT_VISIT);
						else
							chartUnitMappingVO.setIsDefault(OpdConfig.COMPLETE_EPISODE);
					}
					else
						chartUnitMappingVO.setIsDefault(OpdConfig.NONE);
						
					unitChartListVOLst.add(chartUnitMappingVO);
				}
			}
			
			ChartUnitMappingMstDATA.saveChartUnitList(unitChartListVOLst,getUserVO(_request));
			
			_fb.setDeptCode("-1");
			objStatus.add(Status.DONE,"Record Added To Unit Successfully","");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Transaction Failed");		
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
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void getChartUnitListForModify(ChartUnitMappingMstFB _fb,HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		ChartUnitMapppingVO[] chartUnitMapppingVO=null;
		List listAllChart=null;
		List selChartList=new ArrayList();
		
		String chk=_fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String deptUnitCode=code[0];
		String chartId=code[1];
		String slNo=code[2];
		String hospitalCode=code[3];
		try
		{
			ChartUnitMapppingVO vo=new ChartUnitMapppingVO();
			vo.setDeptUnitCode(deptUnitCode);
			vo.setChartId(chartId);
			vo.setSlNo(slNo);
			vo.setHospitalCode(hospitalCode);
			
			_fb.setDeptCode(deptUnitCode);
			
			essentialMap=ChartUnitMappingMstDATA.getChartUnitListForModify(vo,getUserVO(_request));
			
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			
			for(int i=0;i<listAllUnit.size();i++)
			{
				 Entry ent=(Entry)listAllUnit.get(i);
				 if( ent.getValue().equalsIgnoreCase(deptUnitCode))
				 {
					_fb.setDeptUnitName(ent.getLabel());
					break;
				 }
			}
			chartUnitMapppingVO=(ChartUnitMapppingVO[])essentialMap.get(OpdConfig.MAPPED_UNIT_CHART_LIST_VO_ARRAY);
			 
			 for(int i=0;i<chartUnitMapppingVO.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(chartUnitMapppingVO[i].getChartListDesc());
				 ent.setValue(chartUnitMapppingVO[i].getChartId());
				 selChartList.add(ent);
				 if(chartUnitMapppingVO[i].getIsDefault().equals(OpdConfig.CURRENT_VISIT))
				 {
					 _fb.setDefaultChartListCode(chartUnitMapppingVO[i].getChartId());
					 _fb.setIsDefault(chartUnitMapppingVO[i].getIsDefault());
				 }
////					
				 if(chartUnitMapppingVO[i].getIsDefault().equals(OpdConfig.COMPLETE_EPISODE))
				 {
					 _fb.setDefaultChartListCode(chartUnitMapppingVO[i].getChartId());
					 _fb.setIsDefault(chartUnitMapppingVO[i].getIsDefault());
				 }
				 
				 }
				 
			 WebUTIL.setAttributeInSession(_request,OpdConfig.MAPPED_UNIT_CHARTLIST_LIST, selChartList);
			 
			 listAllChart=(List)essentialMap.get(OpdConfig.CHART_NAME_LIST);
			 		
			 ArrayList notSelectedChartList=new ArrayList();
			 
			 for (int i = 0; i < listAllChart.size(); i++)
				{
					Entry entobj = (Entry) listAllChart.get(i);
					for (int j = 0; j < selChartList.size(); j++)
					{
						Entry ent = (Entry) selChartList.get(j);
						if (ent.getValue().equals(entobj.getValue()))
						{
							flag = true;	break;
						}
						else
							flag = false;
					}
					if (!flag)
					{
						Entry newobj = new Entry();
						newobj.setValue(entobj.getValue());
						newobj.setLabel(entobj.getLabel());
						notSelectedChartList.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(_request,OpdConfig.LIST_ALL_CHARTLIST_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedChartList);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
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
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	
	public static void modifySaveChartList(ChartUnitMappingMstFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List unitChartList=new ArrayList();
			try
			{
				
				int countFile=_fb.getSelectedChartListCode().length;
				
				for(int i=0;i<countFile;i++)
				{
									
					ChartUnitMapppingVO chartUnitMappingVO=new ChartUnitMapppingVO();
					chartUnitMappingVO.setChartId(_fb.getSelectedChartListCode()[i]);
					chartUnitMappingVO.setDeptUnitCode(_fb.getDeptCode());
					
					if(_fb.getDefaultChartListCode().equals(_fb.getSelectedChartListCode()[i]))
					
					
							chartUnitMappingVO.setIsDefault(_fb.getIsDefault());
					
					
					else
						chartUnitMappingVO.setIsDefault(OpdConfig.NONE);
					unitChartList.add(chartUnitMappingVO);
				}
				
				ChartUnitMappingMstDATA.modifySaveChartList(unitChartList,getUserVO(_request));
				
				objStatus.add(Status.DONE,"Chart List Modified Successfully","");
			}
			catch(HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA,"","Transaction Failed");		
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
				WebUTIL.setStatus(_request,objStatus);
			}
	}


}
