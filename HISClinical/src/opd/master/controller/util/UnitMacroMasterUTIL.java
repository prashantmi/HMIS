package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.UnitMacroMasterDATA;
import opd.master.controller.fb.UnitMacroMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UnitWiseMacroMstVO;

public class UnitMacroMasterUTIL extends ControllerUTIL 
{
	public static void getUnitMacroEssential(UnitMacroMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllMacroList=null;
		List listAllMacroProcessList=null;
		HttpSession session=WebUTIL.getSession(request);
		try
		{
			essentialMap=UnitMacroMasterDATA.getUnitMacroMasterEssential(getUserVO(request));
			
			lstDept=(List)essentialMap.get(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST);
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST);
			listAllMacroList=(List)essentialMap.get(OpdConfig.ALL_MACRO_LIST);
			listAllMacroProcessList=(List)essentialMap.get(OpdConfig.ALL_MACRO_PROCESS_LIST);
			
			
			session.setAttribute(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			session.setAttribute(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			session.setAttribute(OpdConfig.ALL_MACRO_LIST, listAllMacroList);
			session.setAttribute(OpdConfig.ALL_MACRO_PROCESS_LIST, listAllMacroProcessList);
			
			
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	
	public static void saveUnitExtTreatment(UnitMacroMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		List unitMacroVOLst=new ArrayList(); 
		try
		{
			int countFile=fb.getSelectedMacroList().length;
			int countUnit=fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
				for(int i=0;i<countFile;i++)
				{
					UnitWiseMacroMstVO unitMacroVO=new UnitWiseMacroMstVO();
					unitMacroVO.setMacroId(fb.getSelectedMacroList()[i].split("#")[0]);
					unitMacroVO.setDeptUnitCode(fb.getSelectedUnit()[j].trim());
					unitMacroVO.setProcessId(fb.getSelectedMacroList()[i].split("#")[1]);
					
					unitMacroVOLst.add(unitMacroVO);
				}
			}
			
			UnitMacroMasterDATA.saveUnitMacroDetail(unitMacroVOLst, getUserVO(request));
			
			objStatus.add(Status.DONE,"Record Save Successfully","");
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	
	public static void getUnitMacroForModify(UnitMacroMasterFB fb,HttpServletRequest request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		UnitWiseMacroMstVO[] unitWiseMacroMstVO=null;
		List listAllMacro=null;
		List selMacroLst=new ArrayList();
		
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String macroId=code[0];
		String deptUnitCode=code[1];
		String hospitalCode=code[2];
		String slNo=code[3];
		
		
		
		try
		{
			UnitWiseMacroMstVO vo=new UnitWiseMacroMstVO();
			
			vo.setMacroId(macroId);
			vo.setDeptUnitCode(deptUnitCode);
			vo.setSlNo(slNo);
			vo.setHospitalCode(hospitalCode);
			
			fb.setDeptCode(deptUnitCode);
			
			essentialMap=UnitMacroMasterDATA.getUnitMacroForModify(vo,getUserVO(request));
			
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			
			for(int i=0;i<listAllUnit.size();i++)
			{
				 Entry ent=(Entry)listAllUnit.get(i);
				 if( ent.getValue().equalsIgnoreCase(deptUnitCode))
				 {
					fb.setDeptUnitName(ent.getLabel());
					break;
				 }
			}
			
			
			unitWiseMacroMstVO=(UnitWiseMacroMstVO[])essentialMap.get(OpdConfig.MAPPED_UNIT_MACRO_VO_ARRAY);
			
			
			 
			 for(int i=0;i<unitWiseMacroMstVO.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(unitWiseMacroMstVO[i].getMacroHeader());
				 ent.setValue(unitWiseMacroMstVO[i].getMacroId());
				 selMacroLst.add(ent);
			 }
			 
				 
			 WebUTIL.setAttributeInSession(request,OpdConfig.MAPPED_UNIT_MACRO_LIST, selMacroLst);
			 
					 
			 
			 listAllMacro=(List)essentialMap.get(OpdConfig.ALL_MACRO_LIST);
			 		
			 ArrayList notSelectedMacroLst=new ArrayList();
			 
			 for (int i = 0; i < listAllMacro.size(); i++)
				{
					Entry entobj = (Entry) listAllMacro.get(i);
					for (int j = 0; j < selMacroLst.size(); j++)
					{
						Entry ent = (Entry) selMacroLst.get(j);
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
						notSelectedMacroLst.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(request,OpdConfig.LIST_ALL_MACRO_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedMacroLst);
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void modifySaveUnitMacro(UnitMacroMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		List unitMacroVOLst=new ArrayList();
			try
			{
				
				int countFile=fb.getSelectedMacroList().length;
				
				for(int i=0;i<countFile;i++)
				{
					UnitWiseMacroMstVO unitMacroVO=new UnitWiseMacroMstVO();
					
					unitMacroVO.setMacroId(fb.getSelectedMacroList()[i].split("#")[0]);
					unitMacroVO.setDeptUnitCode(fb.getDeptCode());
					unitMacroVO.setProcessId(fb.getSelectedMacroList()[i].split("#")[1]);
					
					unitMacroVOLst.add(unitMacroVO);
					
				}
				
				UnitMacroMasterDATA.modifySaveUnitMacro(unitMacroVOLst,getUserVO(request));
				
				objStatus.add(Status.DONE,"External Treatment Modified Successfully","");
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
				WebUTIL.setStatus(request,objStatus);
			}
	}
}
