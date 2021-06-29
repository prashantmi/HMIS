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
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.IcdSubgroupMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.DeptUnitIcdMappingDATA;
import opd.master.controller.fb.DeptUnitIcdMappingFB;
import opd.transaction.controller.fb.ExternalInvestigationFB;

/**
 * @author ashas
 *
 */
public class DeptUnitIcdMappingUTIL extends ControllerUTIL
{
	//For getting Dept and Unit essential field 
	public static void getUnitListEssential(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List lstDept=null;
		List listAllUnit=null;
		List listNotMappedUnit=null;
		List lstDisease =null;
		HttpSession session=WebUTIL.getSession(_request);
		try
		{
			session.removeAttribute(OpdConfig.MAPPED_UNIT_LIST);
			essentialMap=DeptUnitIcdMappingDATA.getUnitListEssential(getUserVO(_request));
			
			lstDept=(List)essentialMap.get(OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST);
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			listNotMappedUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED);

			WebUTIL.setAttributeInSession(_request,OpdConfig.ALL_UNIT_LIST, listAllUnit);
			WebUTIL.setAttributeInSession(_request,OpdConfig.ALL_UNIT_LIST_NOT_MAPPED, listNotMappedUnit);
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
			
			WebUTIL.setAttributeInSession(_request,OpdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDeptsOnly);
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
	
	// for getting essential field after click on NEXT 
	public static void getEssential(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List<Entry> mappedUnit= new ArrayList<Entry>();
		Map essentialMap = new HashMap();
		List group= null;
		List lstDisease =null;
	
		try
		{
			List<Entry> notMappedUnit=(ArrayList<Entry>)_request.getSession().getAttribute(OpdConfig.ALL_UNIT_LIST_NOT_MAPPED);
			
			// for getting unit which is mapped in ADD Page
			for(Entry entObj : notMappedUnit)
			{	for(int i =0;i<_fb.getSelectedUnit().length;i++){
				if( entObj.getValue().equals(_fb.getSelectedUnit()[i]))
				{
					mappedUnit.add(entObj);
				}}
			}
			WebUTIL.setAttributeInSession(_request,OpdConfig.MAPPED_UNIT_LIST, mappedUnit);
			essentialMap=DeptUnitIcdMappingDATA.getEssential(getUserVO(_request));
			
			group = (List)essentialMap.get(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP);
			//lstDisease=(List)essentialMap.get(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE);
			WebUTIL.setAttributeInSession(_request,OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP,group);
		//	WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, lstDisease);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, null);
			
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
	
	public static void getSubGroup(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		String group= _fb.getIcdGroupCode();
		List<IcdSubgroupMasterVO> lstSubgroup = new ArrayList<IcdSubgroupMasterVO>();
		List lstICDDisease = new ArrayList();
		Map essentialMap = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			lstSubgroup=DeptUnitIcdMappingDATA.getSubGroupsByGroup(group, userVO);
//			essentialMap = DeptUnitIcdMappingDATA.getIcdDiseaseList(userVO);
//			lstDisease=(List)essentialMap.get(OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE, lstSubgroup);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, null);
			
			List diseaseMapped=(ArrayList)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
			if(diseaseMapped==null||diseaseMapped.size()==0)
			{
				WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,_fb.getSelectedDisease() );
			}
			else
			WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,diseaseMapped );
			
			
			_fb.setHmode("NEXT");
			objStatus.add(Status.TRANSINPROCESS);
			
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
	public static void getDisease(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();
		List mappedDisease = new ArrayList();
		List finalMapped = new ArrayList();
		String subGroup = _fb.getIcdSubgroupCode();
		Map essentialMap = new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		List<IcdDiseaseMasterVO> lstNotMappedDisease = new ArrayList<IcdDiseaseMasterVO>();
	
		try
		{
			UserVO userVO = getUserVO(_request);
			lstDisease=DeptUnitIcdMappingDATA.getDiseaseBySubGroup(subGroup,userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, lstDisease);
			if(_fb.getSelectedDisease()==null || _fb.getSelectedDisease().length==0)
			{
				//WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,"" );
			}
			else
			{	
				List diseaseMapped=(ArrayList)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
				for(int i=0;i<_fb.getSelectedDisease().length;i++)
				{	
					mappedDisease.add(_fb.getSelectedDisease()[i]);
					Collections.sort(mappedDisease);
				}
				if(diseaseMapped==null || diseaseMapped.size()==0)
				{
					WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,mappedDisease );
				}
				else
				{
					for(int i=0;i<mappedDisease.size();i++)
					{
						
						diseaseMapped.add(mappedDisease.get(i));
						Collections.sort(diseaseMapped);
						
					}
				WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,diseaseMapped );
				}
			}
			List finalMappedDisease=(ArrayList)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
			if(finalMappedDisease==null || finalMappedDisease.size()==0)
				{
				}
			else{
				
				for(int k=0;k<lstDisease.size();k++)
				{ boolean flag= false;
					for(int j =0;j<finalMappedDisease.size();j++)
					{	
						if(!(lstDisease.get(k).getDiseaseCode().equals(finalMappedDisease.get(j))))
								flag = true;
							else 
							{	flag= false; break;}
					}
					if(flag)
						lstNotMappedDisease.add(lstDisease. get(k));
					
				}
				WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, lstNotMappedDisease);
			}
			_fb.setHmode("NEXT");
			if(lstDisease==null || lstDisease.size()==0)
			{
				throw new HisRecordNotFoundException("No Disease Found");
			}
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
	
	
	public static void saveDeptUnitIcdMapping(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List departmentIcdMasterVOLst=new ArrayList();
		List mappedDisease =new ArrayList();
		List diseaseMapped =null;
		try
		{
			 diseaseMapped=(ArrayList)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
			for(int i=0;i<_fb.getSelectedDisease().length;i++)
			{	
				mappedDisease.add(_fb.getSelectedDisease()[i]);
			}
			if(diseaseMapped==null || diseaseMapped.size()==0)
			{
//				throw new HisRecordNotFoundException("No Mapped Disease exist ");
			}
			else
			{
				for(int i=0;i<mappedDisease.size();i++)
				{
					diseaseMapped.add(mappedDisease.get(i));
				}
			
			}
			int selectedUnit=_fb.getSelectedUnit().length;
			
				
			for(int j=1;j<selectedUnit;j++)
			{	if(diseaseMapped==null || diseaseMapped.size()==0)
				{for(int i=0;i<mappedDisease.size();i++)
				{
					DepartmentIcdMasterVO departmentIcdMasterVO=new DepartmentIcdMasterVO();
					
					departmentIcdMasterVO.setDiseaseCode((String)mappedDisease.get(i));
					departmentIcdMasterVO.setDepartmentUnitCode(_fb.getSelectedUnit()[j].trim());
					departmentIcdMasterVOLst.add(departmentIcdMasterVO);
				}
				}
			else
			{for(int i=0;i<diseaseMapped.size();i++)
			{
				DepartmentIcdMasterVO departmentIcdMasterVO=new DepartmentIcdMasterVO();
				
				departmentIcdMasterVO.setDiseaseCode((String)diseaseMapped.get(i));
				departmentIcdMasterVO.setDepartmentUnitCode(_fb.getSelectedUnit()[j].trim());
				departmentIcdMasterVOLst.add(departmentIcdMasterVO);
			}
			}
			}
			DeptUnitIcdMappingDATA.saveDeptUnitIcdMapping(departmentIcdMasterVOLst,getUserVO(_request));
			
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
	
	public static void getDeptUnitIcdForModify(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		Map essentialMap=new HashMap();
		List listAllUnit=null;
		DepartmentIcdMasterVO[] departmentIcdMasterVO=null;
		List mappedDiseaseList= new ArrayList();
		List popUpDisease =new ArrayList();
		List groupLst=null;
		
		String chk=_fb.getChk().replace("^","@");
		String[] code=chk.split("@");
		
		String deptUnitCode=code[0];
		String diseaseCode=code[1];
		String slNo=code[2];
		String hospitalCode=code[3];
		try
		{
			DepartmentIcdMasterVO vo=new DepartmentIcdMasterVO();
			vo.setDepartmentUnitCode(deptUnitCode);
			vo.setDiseaseCode(diseaseCode);
			vo.setSlNo(slNo);
			vo.setHospitalCode(hospitalCode);
			
			_fb.setDeptUnitCode(deptUnitCode);
			
			String group= _fb.getIcdGroupCode();
			System.out.println("group code:"+group);
			List<IcdSubgroupMasterVO> lstSubgroup = new ArrayList<IcdSubgroupMasterVO>();
			essentialMap=DeptUnitIcdMappingDATA.getDeptUnitIcdForModify(vo,getUserVO(_request));
//			lstSubgroup=DeptUnitIcdMappingDATA.getSubGroupsByGroup(group, getUserVO(_request));
			
			listAllUnit=(List)essentialMap.get(OpdConfig.ALL_UNIT_LIST);
			groupLst = (List)essentialMap.get(OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP);
			
			for(int i=0;i<listAllUnit.size();i++)
			{
				 Entry ent=(Entry)listAllUnit.get(i);
				 if( ent.getValue().equalsIgnoreCase(deptUnitCode))
				 {
					_fb.setDeptUnitName(ent.getLabel());
					break;
				 }
			}
			departmentIcdMasterVO=(DepartmentIcdMasterVO[])essentialMap.get(OpdConfig.MAPPED_UNIT_ICD_DISEASE_LIST_VO_ARRAY);
			for(int i=0;i<departmentIcdMasterVO.length;i++)
			 {
				popUpDisease.add(departmentIcdMasterVO[i].getDiseaseCode());
				 Entry ent=new Entry();
				 ent.setLabel(departmentIcdMasterVO[i].getDisease());
				 ent.setValue(departmentIcdMasterVO[i].getDiseaseCode());
				 _fb.setIcdGroupCode(departmentIcdMasterVO[i].getIcdGroupCode());
				 System.out.println("IcdGroupCode :"+_fb.getIcdGroupCode());
				 mappedDiseaseList.add(ent) ;
				
//				 _fb.setIcdGroup(departmentIcdMasterVO[i].getIcdGroup());
//				 _fb.setIcdSubgroup(departmentIcdMasterVO[i].getIcdSubGroup());
				 }
			
			WebUTIL.setAttributeInSession(_request,OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_GROUP, groupLst);
//			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_USED_ICD_SUBGROUP_GROUPWISE, lstSubgroup);
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_ESSENTIAL_LIST_ALL_ICD_DISEASE_SUBGROUPWISE, null);
			WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST, mappedDiseaseList);
			Collections.sort(popUpDisease);
			 WebUTIL.setAttributeInSession(_request,OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT, popUpDisease);
			
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
	public static void getModDisease(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List<IcdDiseaseMasterVO> lstDisease = new ArrayList<IcdDiseaseMasterVO>();
		List mappedDisease = new ArrayList();
		List diseaseMapped =new ArrayList();
		List finalMapped = new ArrayList();
		String subGroup = _fb.getIcdSubgroupCode();
		Map essentialMap = new HashMap();
		HttpSession session=WebUTIL.getSession(_request);
		List<Entry> lstNotMappedDisease = new ArrayList<Entry>();
		List<Entry> notMappedDisease = new ArrayList<Entry>();
		String deptCode=_fb.getDeptUnitCode();
	
		try
		{
			UserVO userVO = getUserVO(_request);
			essentialMap=DeptUnitIcdMappingDATA.getModDisease(deptCode,subGroup,userVO);
			notMappedDisease=(List)essentialMap.get(OpdConfig.DISEASE_LIST_NOT_MAPPED);
			WebUTIL.setAttributeInSession(_request, OpdConfig.DISEASE_LIST_NOT_MAPPED, notMappedDisease);

			/* For finding not mapped disease
			
//			List<Entry> diseaseMapped=(List<Entry>)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
//			System.out.println("diseaseMappedfdlkgjfdlgj"+diseaseMapped);
//			for(int k=0;k<lstDisease.size();k++)
//				{ boolean flag= false;
//					for(int j =0;j<diseaseMapped.size();j++)
//					{	
//						if(!(lstDisease.get(k).getDiseaseCode().equals(diseaseMapped.get(j).getValue())))
//								flag = true;
//							else 
//							{	flag= false; break;}
//					}
//					if(flag)
//					{
//						 Entry ent=new Entry();
//						 ent.setLabel(lstDisease.get(k).getDisease());
//						 ent.setValue(lstDisease.get(k).getDiseaseCode());
//						 notMappedDisease.add(ent);
//					}
//				}
//			WebUTIL.setAttributeInSession(_request, OpdConfig.DISEASE_LIST_NOT_MAPPED, notMappedDisease);
 * */
			
			diseaseMapped=(List)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
			session.removeAttribute(OpdConfig.MAPPED_DISEASE_LIST);
			//WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST, diseaseMapped);
			if(_fb.getSelectedDisease()==null || _fb.getSelectedDisease().length==0)
			{
//				WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT, diseaseMapped);
			}
			else
			{	
				for(int i=0;i<_fb.getSelectedDisease().length;i++)
				{	
					if(notMappedDisease.get(i).getValue().equals(_fb.getSelectedDisease()[i]))
					{
//						Entry ent = new Entry();
//						//ent.setLabel(notMappedDisease.get(i).getLabel());
//						ent.setValue(notMappedDisease.get(i).getValue());
						mappedDisease.add(notMappedDisease.get(i).getValue());
					}
					else
					{
//						Entry ent = new Entry();
//						//ent.setLabel(_fb.getSelectedDisease()[i]);
//						ent.setValue(_fb.getSelectedDisease()[i]);
						mappedDisease.add(_fb.getSelectedDisease()[i]);
					}
					
				}
				for(int i=0;i<mappedDisease.size();i++)
				{
					for(int m=0;m<diseaseMapped.size();m++)
					{
					if(mappedDisease.get(i).equals(diseaseMapped.get(m)))
					{
						diseaseMapped.remove(m);
					}
					else
					{
						
					}
					
				}
						diseaseMapped.add(mappedDisease.get(i));
				}
				Collections.sort(diseaseMapped);
				//session.removeAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
				WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT,diseaseMapped );
				for(int k=0;k<notMappedDisease.size();k++)
				{ boolean flag= false;
					for(int j =0;j<diseaseMapped.size();j++)
					{	
						if(!(notMappedDisease.get(k).getValue().equals(diseaseMapped.get(j))))
								flag = true;
							else 
							{	flag= false; break;}
					}
					if(flag){
						Entry ent = new Entry();
						ent.setLabel(notMappedDisease.get(k).getLabel());
						ent.setValue(notMappedDisease.get(k).getValue());
						lstNotMappedDisease.add(ent);
					}
				}
				WebUTIL.setAttributeInSession(_request, OpdConfig.DISEASE_LIST_NOT_MAPPED, lstNotMappedDisease);
			}
			_fb.setHmode("NEXT");
			if(notMappedDisease==null || notMappedDisease.size()==0)
			{
				throw new HisRecordNotFoundException("No Disease Found");
			}
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
	public static void deleteRow(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		List mappedDisease = new ArrayList();
		List diseaseMapped = new ArrayList();
		
		try
		{
			diseaseMapped=(List)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
			for(int i=0;i<diseaseMapped.size();i++)
			{
				if(!_fb.getDeleteIndex().equals(String.valueOf(i)))
				{
					mappedDisease.add(diseaseMapped.get(i));
				}
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT, mappedDisease);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
	public static void modifySaveDeptIcdMapping(DeptUnitIcdMappingFB _fb,HttpServletRequest _request)
	{
		Status objStatus =new Status();
		List unitDiseaseList=new ArrayList();
		List mappedDisease=new ArrayList();
			try
			{

				List diseaseMapped=(ArrayList)_request.getSession().getAttribute(OpdConfig.MAPPED_DISEASE_LIST_WITH_UNIT);
				for(int i=0;i<_fb.getSelectedDisease().length;i++)
				{	
					mappedDisease.add(_fb.getSelectedDisease()[i]);
				}
				if(diseaseMapped==null || diseaseMapped.size()==0)
				{
					throw new HisRecordNotFoundException("No disease Mapped ");
				}
				else
				{
					for(int i=0;i<mappedDisease.size();i++)
					{
//						Entry ent = new Entry();
//						ent.setValue((String)mappedDisease.get(i));
						diseaseMapped.add(mappedDisease.get(i));
					}
				}
				int countFile=_fb.getSelectedDisease().length;
				
				for(int i=0;i<diseaseMapped.size();i++)
				{
					DepartmentIcdMasterVO deptIcdMasterVO=new DepartmentIcdMasterVO();			
					deptIcdMasterVO.setDiseaseCode((String)diseaseMapped.get(i));
					deptIcdMasterVO.setDepartmentUnitCode(_fb.getDeptUnitCode());
					
					unitDiseaseList.add(deptIcdMasterVO);
				}
				
				DeptUnitIcdMappingDATA.modifySaveDeptIcdMapping(unitDiseaseList,getUserVO(_request));
				
				objStatus.add(Status.DONE,"Unit Disease Mapping Modified Successfully","");
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
