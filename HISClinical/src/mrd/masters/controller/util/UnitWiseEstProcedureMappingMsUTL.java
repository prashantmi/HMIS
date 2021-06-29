/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : MRD
 ## Process/Database Object Name	    : UNIT WISE ESTIMATE PROCEDURE MAPPING MASTER
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package mrd.masters.controller.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.masters.controller.fb.UnitWiseEstProcedureMappingMstFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import mrd.vo.UnitWiseEstProcedureMappingMstVO;
import mrd.masters.controller.data.UnitWiseEstProcedureMappingMstDATA;


public class UnitWiseEstProcedureMappingMsUTL extends ControllerUTIL
{
	public static void getProcedureUnitListEssential(UnitWiseEstProcedureMappingMstFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllProcedureList=null;
		HttpSession session=WebUTIL.getSession(_request);
		try
		{
			session.removeAttribute(MrdConfig.MAPPED_UNIT_PROCEDURE_LIST);
			
			essentialMap=UnitWiseEstProcedureMappingMstDATA.getProcedureUnitListEssential(getUserVO(_request));
			
			lstDept=(List)essentialMap.get(MrdConfig.ALL_CLINICAL_DEPARTMENT_LIST);
			listAllUnit=(List)essentialMap.get(MrdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(MrdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_PROCEDURE);
			listAllProcedureList=(List)essentialMap.get(MrdConfig.PROCEDURE_NAME_LIST);
			
		
			session.setAttribute(MrdConfig.ALL_UNIT_LIST, listAllUnit);
			session.setAttribute(MrdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_PROCEDURE, listNotMappedUnit);
			session.setAttribute(MrdConfig.PROCEDURE_NAME_LIST, listAllProcedureList);
			
			
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
			
			session.setAttribute(MrdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDeptsOnly);
			
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
	
	public static void saveProcedureUnitList(UnitWiseEstProcedureMappingMstFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List unitProcedureListVOLst=new ArrayList();
		try
		{
			int countFile=_fb.getSelectedProcedureListCode().length;
			int countUnit=_fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
				for(int i=0;i<countFile;i++)
				{
					UnitWiseEstProcedureMappingMstVO procedureUnitMappingVO=new UnitWiseEstProcedureMappingMstVO();
					
					procedureUnitMappingVO.setTariffId(_fb.getSelectedProcedureListCode()[i]);
					procedureUnitMappingVO.setDeptUnitCode(_fb.getSelectedUnit()[j].trim());
					
					if(_fb.getDefaultProcedureListCode().equals(_fb.getSelectedProcedureListCode()[i]))
						procedureUnitMappingVO.setIsDefault(MrdConfig.YES);
					else
						procedureUnitMappingVO.setIsDefault(MrdConfig.NO);
						
					unitProcedureListVOLst.add(procedureUnitMappingVO);
				}
			}
			
			UnitWiseEstProcedureMappingMstDATA.saveProcedureUnitList(unitProcedureListVOLst,getUserVO(_request));
			
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
	
	public static void getProcedureUnitListForModify(UnitWiseEstProcedureMappingMstFB _fb,HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		UnitWiseEstProcedureMappingMstVO[] UnitWiseEstProcedureMappingMstVO=null;
		List listAllProcedure=null;
		List selProcedureList=new ArrayList();
		
		String chk=_fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String deptUnitCode=code[0];
		String tariffId=code[1];
		String hospitalCode=code[2];
		try
		{
			UnitWiseEstProcedureMappingMstVO vo=new UnitWiseEstProcedureMappingMstVO();
			vo.setDeptUnitCode(deptUnitCode);
			vo.setTariffId(tariffId);
			vo.setHospitalCode(hospitalCode);
			
			_fb.setDeptCode(deptUnitCode);
			
			essentialMap=UnitWiseEstProcedureMappingMstDATA.getProcedureUnitListForModify(vo,getUserVO(_request));
			
			listAllUnit=(List)essentialMap.get(MrdConfig.ALL_UNIT_LIST);
			
			for(int i=0;i<listAllUnit.size();i++)
			{
				 Entry ent=(Entry)listAllUnit.get(i);
				 if( ent.getValue().equalsIgnoreCase(deptUnitCode))
				 {
					_fb.setDeptUnitName(ent.getLabel());
					break;
				 }
			}
			UnitWiseEstProcedureMappingMstVO=(UnitWiseEstProcedureMappingMstVO[])essentialMap.get(MrdConfig.MAPPED_UNIT_PROCEDURE_LIST_VO_ARRAY);
			 
			 for(int i=0;i<UnitWiseEstProcedureMappingMstVO.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(UnitWiseEstProcedureMappingMstVO[i].getProcedureListDesc());
				 ent.setValue(UnitWiseEstProcedureMappingMstVO[i].getTariffId());
				 selProcedureList.add(ent);
				 if(UnitWiseEstProcedureMappingMstVO[i].getIsDefault().equals(MrdConfig.YES))
					 _fb.setDefaultProcedureListCode(UnitWiseEstProcedureMappingMstVO[i].getTariffId());
				 _fb.setIsDefault(UnitWiseEstProcedureMappingMstVO[i].getIsDefault());
			 }
				 
			 WebUTIL.setAttributeInSession(_request,MrdConfig.MAPPED_UNIT_PROCEDURE_LIST, selProcedureList);
			 
			 listAllProcedure=(List)essentialMap.get(MrdConfig.PROCEDURE_NAME_LIST);
			 		
			 ArrayList notSelectedProcedureList=new ArrayList();
			 
			 for (int i = 0; i < listAllProcedure.size(); i++)
				{
					Entry entobj = (Entry) listAllProcedure.get(i);
					for (int j = 0; j < selProcedureList.size(); j++)
					{
						Entry ent = (Entry) selProcedureList.get(j);
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
						notSelectedProcedureList.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(_request,MrdConfig.LIST_ALL_PROCEDURE_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedProcedureList);
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
	
	public static void modifySaveProcedureList(UnitWiseEstProcedureMappingMstFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List unitProcedureList=new ArrayList();
			try
			{
				
				int countFile=_fb.getSelectedProcedureListCode().length;
				
				for(int i=0;i<countFile;i++)
				{
									
					UnitWiseEstProcedureMappingMstVO procedureUnitMappingVO=new UnitWiseEstProcedureMappingMstVO();
					procedureUnitMappingVO.setTariffId(_fb.getSelectedProcedureListCode()[i]);
					procedureUnitMappingVO.setDeptUnitCode(_fb.getDeptCode());
					
					
					
					if(_fb.getDefaultProcedureListCode().equals(_fb.getSelectedProcedureListCode()[i]))
					
						procedureUnitMappingVO.setIsDefault(MrdConfig.CURRENT_VISIT);
							//procedureUnitMappingVO.setIsDefault(_fb.getIsDefault());
					
					
					else
						procedureUnitMappingVO.setIsDefault(MrdConfig.NONE);
					unitProcedureList.add(procedureUnitMappingVO);
				}
				
				UnitWiseEstProcedureMappingMstDATA.modifySaveProcedureList(unitProcedureList,getUserVO(_request));
				
				objStatus.add(Status.DONE,"Procedure List Modified Successfully","");
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
