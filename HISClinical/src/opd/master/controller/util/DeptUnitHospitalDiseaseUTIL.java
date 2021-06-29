/**
 * 
 */
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
import hisglobal.vo.DepartmentHosDiseaseMstVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ChartUnitMappingMstDATA;
import opd.master.controller.data.DeptUnitHospitalDiseaseDATA;
import opd.master.controller.fb.DeptUnitHospitalDiseaseFB;

/**
 * @author ashas
 *
 */
public class DeptUnitHospitalDiseaseUTIL extends ControllerUTIL{

	public static void getUnitListEssential(DeptUnitHospitalDiseaseFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List hosDisease = null;
		HttpSession session=WebUTIL.getSession(_request);
		try
		{
			session.removeAttribute(OpdConfig.LIST_OF_UNIT_MAPPED_WITH_DEPT);
			essentialMap=DeptUnitHospitalDiseaseDATA.getDeptUnitEssential(getUserVO(_request));
			
			lstDept=(List)essentialMap.get(OpdConfig.LIST_OF_ALL_CLINICAL_DEPARTMENT);
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(OpdConfig.DEPT_WISE_LIST_OF_ALL_NOT_MAPPED_UNIT);
			
			hosDisease = (List)essentialMap.get(OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE,hosDisease);

			WebUTIL.setAttributeInSession(_request,OpdConfig.ALL_UNIT_LIST, listAllUnit);
			WebUTIL.setAttributeInSession(_request,OpdConfig.DEPT_WISE_LIST_OF_ALL_NOT_MAPPED_UNIT, listNotMappedUnit);
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
			
			WebUTIL.setAttributeInSession(_request,OpdConfig.LIST_OF_ALL_CLINICAL_DEPARTMENT, lstDeptsOnly);
			objStatus.add(Status.TRANSINPROCESS,"","");
			
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
	
	public static void saveDeptUnitHosDiseaseMapping(DeptUnitHospitalDiseaseFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List departmentHosDisVOLst=new ArrayList();
//		List mappedDisease =new ArrayList();
		List diseaseMapped =null;
		try
		{
			int mappedDisease = _fb.getSelecteHospitaldDisease().length;
			int selectedUnit=_fb.getSelectedUnit().length;
			
				
			for(int j=0;j<selectedUnit;j++)
			{	
				for(int i=0;i<mappedDisease;i++)
				{
					DepartmentHosDiseaseMstVO deptHosDiseaseMstVO=new DepartmentHosDiseaseMstVO();
					
					deptHosDiseaseMstVO.setHospitalDiseaseCode(_fb.getSelecteHospitaldDisease()[i]);
					deptHosDiseaseMstVO.setDepartmentUnitCode(_fb.getSelectedUnit()[j].trim());
					departmentHosDisVOLst.add(deptHosDiseaseMstVO);
				}
				}
			DeptUnitHospitalDiseaseDATA.saveDeptUnitHosDisease(departmentHosDisVOLst,getUserVO(_request));
			_fb.setDeptCode("-1");
			objStatus.add(Status.DONE,"Record Added Successfully","");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"",e.getMessage());
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
	
	public static void getUnitWiseMappedDiseasForModify(DeptUnitHospitalDiseaseFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		DepartmentHosDiseaseMstVO[] departmentHosDiseaseMstVO=null;
		List mappedDiseaseList= new ArrayList();
		List popUpDisease =new ArrayList();
		List listAllDisease=null;
		boolean flag=false;
		
		String chk=_fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String deptUnitCode=code[0];
		String hospitalDiseaseCode=code[1];
		String slNo=code[2];
		String hospitalCode=code[3];
		
		try
		{
			DepartmentHosDiseaseMstVO vo=new DepartmentHosDiseaseMstVO();
			vo.setDepartmentUnitCode(deptUnitCode);
			vo.setHospitalDiseaseCode(hospitalDiseaseCode);
			vo.setSlNo(slNo);
			vo.setHospitalCode(hospitalCode);
			
			_fb.setDeptUnitCode(deptUnitCode);
			
			essentialMap=DeptUnitHospitalDiseaseDATA.getUnitWiseMappedDiseasForModify(vo,getUserVO(_request));
//			lstSubgroup=DeptUnitHospitalDiseaseDATA.getSubGroupsByGroup(group, getUserVO(_request));
			
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
			departmentHosDiseaseMstVO=(DepartmentHosDiseaseMstVO[])essentialMap.get(OpdConfig.LIST_OF_MAPPED_UNIT_HOSPITAL_DISEASE_VO_ARRAY);
			for(int i=0;i<departmentHosDiseaseMstVO.length;i++)
			 {
				 Entry ent=new Entry();
				 ent.setLabel(departmentHosDiseaseMstVO[i].getHospitalDisease());
				 ent.setValue(departmentHosDiseaseMstVO[i].getHospitalDiseaseCode());
				 mappedDiseaseList.add(ent) ;
			 }			
			WebUTIL.setAttributeInSession(_request, OpdConfig.LIST_OF_MAPPED_HOSPITAL_DISEASE_WITH_UNIT, mappedDiseaseList);
			
			listAllDisease=(List)essentialMap.get(OpdConfig.ESSENTIAL_LIST_ALL_HOSPITAL_DISEASE);
	 		
			 ArrayList notSelectedDiseaseList=new ArrayList();
			 
			 for (int i = 0; i < listAllDisease.size(); i++)
				{
					Entry entobj = (Entry) listAllDisease.get(i);
					for (int j = 0; j < mappedDiseaseList.size(); j++)
					{
						Entry ent = (Entry) mappedDiseaseList.get(j);
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
						notSelectedDiseaseList.add(newobj);
					}
				}
			 WebUTIL.setAttributeInSession(_request,OpdConfig.LIST_NOT_MAPPED_HOSPITAL_DISEASE ,notSelectedDiseaseList);
			
			
			 
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

	
	public static void modifySaveDeptUnitHosDiseaseMapping(DeptUnitHospitalDiseaseFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List hosDiseaseLst=new ArrayList();
			try
			{
				
				int countDisease=_fb.getSelecteHospitaldDisease().length;
				
				for(int i=0;i<countDisease;i++)
				{
									
					DepartmentHosDiseaseMstVO departmentHosDiseaseMstVO=new DepartmentHosDiseaseMstVO();
					departmentHosDiseaseMstVO.setHospitalDiseaseCode(_fb.getSelecteHospitaldDisease()[i]);
					departmentHosDiseaseMstVO.setDepartmentUnitCode(_fb.getDeptUnitCode());
					hosDiseaseLst.add(departmentHosDiseaseMstVO);
					
				}
				
				DeptUnitHospitalDiseaseDATA.modifySaveDeptUnitHosDiseaseMapping(hosDiseaseLst,getUserVO(_request));
				
				objStatus.add(Status.DONE,"Department Unit wise Hospital Mapping Modified Successfully","");
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
