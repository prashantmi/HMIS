package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.UnitDrugMasterData;
import opd.master.controller.fb.UnitDrugMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UnitDrugMstVO;

public class UnitDrugMasterUtil extends ControllerUTIL{
	public static void getUnitDrugEssential(UnitDrugMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List listAllExtTreatList=null;
		HttpSession session=WebUTIL.getSession(request);
		try
		{
			essentialMap=UnitDrugMasterData.getDrugMasterEssential(getUserVO(request));
			
			lstDept=(List)essentialMap.get(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST);
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST);
			listAllExtTreatList=(List)essentialMap.get(OpdConfig.ALL_EXT_TREATMENT_LIST);
			
		
			session.setAttribute(OpdConfig.ALL_UNIT_LIST, listAllUnit);
			session.setAttribute(OpdConfig.NOT_MAPPED_ALL_UNIT_LIST, listNotMappedUnit);
			session.setAttribute(OpdConfig.ALL_EXT_TREATMENT_LIST, listAllExtTreatList);
			
			
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
	
	
	public static void saveUnitDrugList(UnitDrugMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		List unitDrugVOLst=new ArrayList(); 
		try
		{
			int countFile=fb.getSelectedExtTreatCode().length;
			int countUnit=fb.getSelectedUnit().length;
			
			for(int j=0;j<countUnit;j++)
			{
				for(int i=0;i<countFile;i++)
				{
					UnitDrugMstVO unitDrugVO=new UnitDrugMstVO();
					unitDrugVO.setExtTreatId(fb.getSelectedExtTreatCode()[i].split("#")[0]);
					unitDrugVO.setDeptUnitCode(fb.getSelectedUnit()[j].trim());
					unitDrugVOLst.add(unitDrugVO);
				}
			}
			
			UnitDrugMasterData.saveUnitDrugDetail(unitDrugVOLst, getUserVO(request));
			
			objStatus.add(Status.DONE,"Drug  Items Mapped With Unit Successfully","");
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
	
	
	public static void getUnitDrugLisyForModify(UnitDrugMasterFB fb,HttpServletRequest request)
	{
		boolean flag=false;
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		UnitDrugMstVO[] unitDrugVOArray=null;
		List listAllExtTreatList=null;
		List selExtTreatLst=new ArrayList();
		
		String chk=fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String extTreatId=code[0];
		String deptUnitCode=code[1];
		String hospitalCode=code[2];
		//String slNo=code[3];
		
		
		
		try
		{
			UnitDrugMstVO vo=new UnitDrugMstVO();
			
			vo.setExtTreatId(extTreatId);
			vo.setDeptUnitCode(deptUnitCode);
			//vo.setSlNo(slNo);
			vo.setHospitalCode(hospitalCode);
			
			fb.setDeptCode(deptUnitCode);
			
			essentialMap=UnitDrugMasterData.getUnitDrugLisyForModify(vo,getUserVO(request));
			
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
			
			
			unitDrugVOArray=(UnitDrugMstVO[])essentialMap.get(OpdConfig.MAPPED_UNIT_EXT_TREATMENT_VO_ARRAY);
			
			
			 
			 for(int i=0;i<unitDrugVOArray.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(unitDrugVOArray[i].getExtTreatName());
				 ent.setValue(unitDrugVOArray[i].getExtTreatId());
				 selExtTreatLst.add(ent);
			 }
			 
				 
			 WebUTIL.setAttributeInSession(request,OpdConfig.MAPPED_UNIT_EXT_TREATMENT_LIST, selExtTreatLst);
			 
					 
			 
			 listAllExtTreatList=(List)essentialMap.get(OpdConfig.ALL_EXT_TREATMENT_LIST);
			 		
			 ArrayList notSelectedExtTreat=new ArrayList();
			 
			 for (int i = 0; i < listAllExtTreatList.size(); i++)
				{
					Entry entobj = (Entry) listAllExtTreatList.get(i);
					for (int j = 0; j < selExtTreatLst.size(); j++)
					{
						Entry ent = (Entry) selExtTreatLst.get(j);
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
						notSelectedExtTreat.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(request,OpdConfig.LIST_ALL_EXT_TREAT_NOT_IN_SELECTED_BASED_ON_UNIT ,notSelectedExtTreat);
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
	
	public static void modifySaveUnitDrugDetail(UnitDrugMasterFB fb,HttpServletRequest request)
	{
		Status objStatus =new Status();
		List unitDrugVOLst=new ArrayList();
			try
			{
				
				int countFile=fb.getSelectedExtTreatCode().length;
				
				for(int i=0;i<countFile;i++)
				{
					UnitDrugMstVO unitDrugVO=new UnitDrugMstVO();
					unitDrugVO.setExtTreatId(fb.getSelectedExtTreatCode()[i].split("#")[0]);
				
					unitDrugVO.setDeptUnitCode(fb.getDeptCode());
					unitDrugVOLst.add(unitDrugVO);
					
				}
				
				UnitDrugMasterData.modifySaveUnitDrugDetail(unitDrugVOLst,getUserVO(request));
				
				objStatus.add(Status.DONE,"Drug Detail Modified Successfully","");
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
