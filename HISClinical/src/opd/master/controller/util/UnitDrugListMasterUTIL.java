package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.UnitDrugListMasterDATA;
import opd.master.controller.fb.UnitDrugListMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UnitDrugListMasterVO;

public class UnitDrugListMasterUTIL extends ControllerUTIL
{
	public static void getUnitDrugListEssential(UnitDrugListMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllDrugListList=null;
		HttpSession session=WebUTIL.getSession(request);
		try
		{
			session.removeAttribute(OpdConfig.MAPPED_UNIT_DRUGLIST_LIST);
			
			essentialMap=UnitDrugListMasterDATA.getUnitDrugListMasterEssential(getUserVO(request));
			
			lstDept=(List)essentialMap.get(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST);
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST);
			listAllDrugListList=(List)essentialMap.get(OpdConfig.ALL_DRUGLIST_LIST);
			
		
			session.setAttribute(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			session.setAttribute(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			session.setAttribute(OpdConfig.ALL_DRUGLIST_LIST, listAllDrugListList);
			
			
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
	
	public static void saveUnitDrugList(UnitDrugListMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		//String unitCode=fb.getDeptCode();
		List unitDrugListVOLst=new ArrayList();
		try
		{
			//UnitAudioVideoMasterDATA.deleteUnitAudioVideo(unitCode,getUserVO(request));
			int countFile=fb.getSelectedDrugListCode().length;
			int countUnit=fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
				for(int i=0;i<countFile;i++)
				{
					UnitDrugListMasterVO unitDrugListMasterVO=new UnitDrugListMasterVO();
					unitDrugListMasterVO.setDrugListId(fb.getSelectedDrugListCode()[i]);
					unitDrugListMasterVO.setDeptUnitCode(fb.getSelectedUnit()[j].trim());
					
					if(fb.getDefaultDrugListCode().equals(fb.getSelectedDrugListCode()[i]))
					{
						unitDrugListMasterVO.setIsDefault(OpdConfig.IS_DEFAULT_YES);
					}
					else
					{
						unitDrugListMasterVO.setIsDefault(OpdConfig.IS_DEFAULT_NO);
					}
					
					unitDrugListVOLst.add(unitDrugListMasterVO);
				}
			}
			
			UnitDrugListMasterDATA.saveUnitDrugList(unitDrugListVOLst,getUserVO(request));
			
			fb.setDeptCode("-1");
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void getUnitDrugListForModify(UnitDrugListMasterFB fb,HttpServletRequest request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		UnitDrugListMasterVO[] unitDrugListMasterVO=null;
		List listAllDrugList=null;
		List selDrugListLst=new ArrayList();
		
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String deptUnitCode=code[0];
		String drugListId=code[1];
		String slNo=code[2];
		String hospitalCode=code[3];
		
		
		
		try
		{
			UnitDrugListMasterVO vo=new UnitDrugListMasterVO();
			
			
			vo.setDeptUnitCode(deptUnitCode);
			vo.setDrugListId(drugListId);
			vo.setSlNo(slNo);
			vo.setHospitalCode(hospitalCode);
			
			fb.setDeptCode(deptUnitCode);
			
			essentialMap=UnitDrugListMasterDATA.getUnitMacroForModify(vo,getUserVO(request));
			
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
			
			
			unitDrugListMasterVO=(UnitDrugListMasterVO[])essentialMap.get(OpdConfig.MAPPED_UNIT_DRUG_LIST_VO_ARRAY);
			
			
			 
			 for(int i=0;i<unitDrugListMasterVO.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(unitDrugListMasterVO[i].getDrugListDesc());
				 ent.setValue(unitDrugListMasterVO[i].getDrugListId());
				 selDrugListLst.add(ent);
				 
				 if(unitDrugListMasterVO[i].getIsDefault().equals(OpdConfig.IS_DEFAULT_YES))
				 {
					 fb.setDefaultDrugListCode(unitDrugListMasterVO[i].getDrugListId());
				 }
			 }
			 
				 
			 WebUTIL.setAttributeInSession(request,OpdConfig.MAPPED_UNIT_DRUGLIST_LIST, selDrugListLst);
			 
					 
			 
			 listAllDrugList=(List)essentialMap.get(OpdConfig.ALL_DRUGLIST_LIST);
			 		
			 ArrayList notSelectedDrugListLst=new ArrayList();
			 
			 for (int i = 0; i < listAllDrugList.size(); i++)
				{
					Entry entobj = (Entry) listAllDrugList.get(i);
					for (int j = 0; j < selDrugListLst.size(); j++)
					{
						Entry ent = (Entry) selDrugListLst.get(j);
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
						notSelectedDrugListLst.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(request,OpdConfig.LIST_ALL_DRUGLIST_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedDrugListLst);
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
	
	public static void modifySaveDrugList(UnitDrugListMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		List unitDrugListLst=new ArrayList();
			try
			{
				
				int countFile=fb.getSelectedDrugListCode().length;
				
				for(int i=0;i<countFile;i++)
				{
									
					UnitDrugListMasterVO unitDrugListMasterVO=new UnitDrugListMasterVO();
					unitDrugListMasterVO.setDrugListId(fb.getSelectedDrugListCode()[i]);
					unitDrugListMasterVO.setDeptUnitCode(fb.getDeptCode());
					
					unitDrugListLst.add(unitDrugListMasterVO);
					
					if(fb.getDefaultDrugListCode().equals(fb.getSelectedDrugListCode()[i]))
					{
						unitDrugListMasterVO.setIsDefault(OpdConfig.IS_DEFAULT_YES);
					}
					else
					{
						unitDrugListMasterVO.setIsDefault(OpdConfig.IS_DEFAULT_NO);
					}
					
				}
				
				UnitDrugListMasterDATA.modifySaveDrugList(unitDrugListLst,getUserVO(request));
				
				objStatus.add(Status.DONE,"Drug List Modified Successfully","");
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
