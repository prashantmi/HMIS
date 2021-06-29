package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.TemplateMappingMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.TemplateMappingMstDATA;
import opd.master.controller.fb.TemplateMappingMstFB;

public class TemplateMappingMstUTL extends ControllerUTIL
{
	
	public static void getEssentials(TemplateMappingMstFB fb,	HttpServletRequest _request) 
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		//List listColumn=new ArrayList();
		//List listPrimaryKeyColumn=new ArrayList();
		try
		{
			String categoryCode=fb.getControls()[0];
			if(categoryCode!=null)
				fb.setTemplateCategory(categoryCode);
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			mp=TemplateMappingMstDATA.getEssentials(fb.getTemplateCategory(),userVO);
			fb.setTemplateCategoryName((String)mp.get(OpdConfig.TEMPLATE_CATEGORY_NAME));
			fb.setDeptRadio("1");
			WebUTIL.setMapInSession(mp, _request);
			objStatus.add(Status.NEW);
		}
		
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setMapInSession(mp, _request);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "");
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
	}

	public static void getUnitNotAdded(TemplateMappingMstFB fb,HttpServletRequest _request) {
		
		Status objStatus = new Status();
		//Map mp = new HashMap();
		List unitList=new ArrayList();
		//List listPrimaryKeyColumn=new ArrayList();
		String categoryCode=fb.getTemplateCategory();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			TemplateMappingMstVO templateMappingVO=new TemplateMappingMstVO();
			templateMappingVO.setTemplateCategory(categoryCode);
			templateMappingVO.setDeptCode(fb.getDeptCode());
			
			unitList=TemplateMappingMstDATA.getUnitNotAdded(templateMappingVO,userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED, unitList);
			objStatus.add(Status.NEW);
		}
		
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED, unitList);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Unit Found");
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
		
	}
	
	public static void getWardNotAdded(TemplateMappingMstFB fb,HttpServletRequest _request)  {
		
		Status objStatus = new Status();
		//Map mp = new HashMap();
		List wardList=new ArrayList();
		//List listPrimaryKeyColumn=new ArrayList();
		String categoryCode=fb.getTemplateCategory();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			TemplateMappingMstVO templateMappingVO=new TemplateMappingMstVO();
			templateMappingVO.setTemplateCategory(categoryCode);
			templateMappingVO.setDeptCode(fb.getDeptCode());
			templateMappingVO.setDeptUnitCode(fb.getDeptUnitCode());
			
			wardList=TemplateMappingMstDATA.getWardNotAdded(templateMappingVO,userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_WARD_NOT_ASSIGNED, wardList);
			objStatus.add(Status.NEW);
		}
		
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_WARD_NOT_ASSIGNED, wardList);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Ward Found ");
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
		
	}
	
	public static void saveTemplateMapping(TemplateMappingMstFB fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			TemplateMappingMstVO templateMappingVOs[];
			int len=0;
			len=fb.getSelectedTemplateId().length;
			templateMappingVOs =new TemplateMappingMstVO[len];
			for(int i=0;i<len;i++){
				templateMappingVOs[i]=new TemplateMappingMstVO();
				templateMappingVOs[i].setTemplateCategory(fb.getTemplateCategory());
				templateMappingVOs[i].setDeptCode(fb.getDeptCode());
				if(!fb.getDeptUnitCode().equals("-1"))
					templateMappingVOs[i].setDeptUnitCode(fb.getDeptUnitCode());
				templateMappingVOs[i].setIsDefault(fb.getIsDefault());
				if(!fb.getWardCode().equals("-1"))
					templateMappingVOs[i].setWardCode(fb.getWardCode());
				templateMappingVOs[i].setTemplateId(fb.getSelectedTemplateId()[i].split("%")[0]);
			}			
			TemplateMappingMstDATA.saveTemplateMapping(templateMappingVOs,userVO);
			
			objStatus.add(Status.DONE,"","Record added Successfully");
			fb.setHmode("ADD");
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
		
	}

	public static void modifyTemplateMapping(TemplateMappingMstFB fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			TemplateMappingMstVO insertTemplateMappingVOs[];
			TemplateMappingMstVO updateTemplateMappingVOs[];
			int len=0;
			String selectedList[]=fb.getSelectedTemplateId();
			String fetchedList[]=fb.getFetchedList().split("@");
			boolean flag=false;
			for(int i=0;i<selectedList.length;i++){
				for(int j=0;j<fetchedList.length;j++){
					if(selectedList[i].split("%")[1].equals(fetchedList[j].split("%")[1])){
						flag=true;
						break;
					}
					else{
						flag=false;
					}
				}
				if(!flag) len++;
			}
			insertTemplateMappingVOs =new TemplateMappingMstVO[len];
			len=0;
			for(int i=0;i<selectedList.length;i++){
				for(int j=0;j<fetchedList.length;j++){
					if(selectedList[i].split("%")[1].equals(fetchedList[j].split("%")[1])){
						flag=true;
						break;
					}
					else{
						flag=false;
					}
				}
				if(!flag){
					insertTemplateMappingVOs[len]=new TemplateMappingMstVO();
					insertTemplateMappingVOs[len].setTemplateCategory(fb.getTemplateCategory());
					insertTemplateMappingVOs[len].setDeptCode(fb.getDeptCode());
					if(!fb.getDeptUnitCode().equals("-1"))
						insertTemplateMappingVOs[len].setDeptUnitCode(fb.getDeptUnitCode());
					insertTemplateMappingVOs[len].setIsDefault(fb.getIsDefault());
					if(!fb.getWardCode().equals("-1"))
						insertTemplateMappingVOs[len].setWardCode(fb.getWardCode());
					insertTemplateMappingVOs[len].setTemplateId(fb.getSelectedTemplateId()[i].split("%")[1]);
					len++;
				}	
			}
			len=0;
			for(int i=0;i<fetchedList.length;i++){
				for(int j=0;j<selectedList.length;j++){
					if(fetchedList[i].split("%")[1].equals(selectedList[j].split("%")[1])){
						flag=true;
						break;
					}
					else{
						flag=false;
					}
				}
				if(!flag) len++;
			}	
			updateTemplateMappingVOs =new TemplateMappingMstVO[len];
			len=0;
			for(int i=0;i<fetchedList.length;i++){
				for(int j=0;j<selectedList.length;j++){
					if(fetchedList[i].split("%")[1].equals(selectedList[j].split("%")[1])){
						flag=true;
						break;
					}
					else{
						flag=false;
					}
				}
				if(!flag) {
					updateTemplateMappingVOs[len]=new TemplateMappingMstVO();
					updateTemplateMappingVOs[len].setTemplateCategory(fb.getTemplateCategory());
					updateTemplateMappingVOs[len].setDeptCode(fb.getDeptCode());
					updateTemplateMappingVOs[len].setTemplateMappingId(fetchedList[i].split("%")[0]);
					len++;
				}
			}	
			
			TemplateMappingMstDATA.modifyTemplateMapping(updateTemplateMappingVOs,insertTemplateMappingVOs,userVO);
			
			objStatus.add(Status.DONE,"","Record modified successfully");
			fb.setHmode("ADD");
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
		
	}
	
	public static void getTemplateMapping(TemplateMappingMstFB fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		TemplateMappingMstVO templateMappingVOs[];
		TemplateMappingMstVO templateMappingVO=new TemplateMappingMstVO();
		String primaryKey=fb.getChk()[0];
		primaryKey=primaryKey.replace("^", "@");
		String templateMappingId=primaryKey.split("@")[0];
		String templateCategory=primaryKey.split("@")[1];
		String entryValues[];
		String fetchedList="";
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			templateMappingVO.setTemplateCategory(templateCategory);
			templateMappingVO.setTemplateMappingId(templateMappingId);
			Map essentialMap=(HashMap)TemplateMappingMstDATA.getTemplateMapping(templateMappingVO,userVO);
			templateMappingVOs=(TemplateMappingMstVO[])essentialMap.get(OpdConfig.TEMPLATE_MAPPING_DETAIL);
			List templateAdded=new ArrayList();
			for(int i=0;i<templateMappingVOs.length;i++){
				Entry entry=new Entry();
				entry.setLabel(templateMappingVOs[i].getTemplateName());
				entry.setValue(templateMappingVOs[i].getTemplateId());
				templateAdded.add(entry);
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_ASSIGNED, templateAdded);
			WebUTIL.setMapInSession(essentialMap, _request);
			fb.setDeptCode(templateMappingVOs[0].getDeptCode().split("@")[0]);
			fb.setDeptName(templateMappingVOs[0].getDeptCode().split("@")[1]);
			if(!templateMappingVOs[0].getDeptUnitCode().equals("@")){
				String deptUnit[]=templateMappingVOs[0].getDeptUnitCode().split("@");
				fb.setDeptUnitCode(deptUnit[0]);
				if(deptUnit.length>1)
					fb.setDeptUnitName(deptUnit[1]);
			}
			if(!templateMappingVOs[0].getWardCode().equals("@")){
				String wardCode[]=templateMappingVOs[0].getWardCode().split("@");
				fb.setWardCode(wardCode[0]);
				if(wardCode.length>1)
					fb.setWardName(wardCode[1]);
			}	
			fb.setIsDefault(templateMappingVOs[0].getIsDefault());	
			fb.setTemplateCategory(templateCategory);
			String templateCategoryName= (String)essentialMap.get(OpdConfig.TEMPLATE_CATEGORY_NAME);
			fb.setTemplateCategoryName(templateCategoryName);
			List templateNotAdded=(ArrayList)essentialMap.get(OpdConfig.TEMPLATE_NOT_ASSIGNED);
			entryValues=new String[templateAdded.size()];
			for(int i=0;i<templateAdded.size();i++){
				Entry entry=(Entry)templateAdded.get(i);
				entryValues[i]=entry.getValue().split("%")[1]+ "%" + entry.getValue().split("%")[1];
				fetchedList=fetchedList+entry.getValue()+"@";
			}
			fb.setFetchedList(fetchedList);
			templateNotAdded=(ArrayList)WebUTIL.removeEntriesfromOptions(templateNotAdded, entryValues);
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_NOT_ASSIGNED, templateNotAdded);
			objStatus.add(Status.NEW);
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Record Found");
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
		
		
	}

	public static void getDepartment(TemplateMappingMstFB fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		List listDepartment=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			listDepartment=TemplateMappingMstDATA.getDepartment(userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_DEPT_NOT_ASSIGNED, listDepartment);
			objStatus.add(Status.NEW);
		}
		
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_DEPT_NOT_ASSIGNED, listDepartment);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "");
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
		
	}

	public static void getAllUnits(TemplateMappingMstFB fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		List listUnit=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			listUnit=TemplateMappingMstDATA.getAllUnits(fb.getDeptCode(),userVO);
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED, listUnit);
			objStatus.add(Status.NEW);
		}
		
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request, OpdConfig.TEMPLATE_MAPPING_UNIT_NOT_ASSIGNED, listUnit);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
		
	}

}

