package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.master.controller.data.DepartmentIcdMasterDATA;
import opd.master.controller.fb.DepartmentIcdMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DepartmentIcdMasterVO;
import hisglobal.vo.UserVO;
import opd.OpdConfig;

public class DepartmentIcdMasterUTIL extends ControllerUTIL {
	
	public static void setDeptIcdEssential(DepartmentIcdMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus= new Status();
		//HttpSession session= _rq.getSession();
		
		
		setSysdate(_rq);
		Map mp=new HashMap();
		try
		{
			
			UserVO userVO=getUserVO(_rq);
			
			mp=DepartmentIcdMasterDATA.getDeptIcdEssential(userVO);
			WebUTIL.setMapInSession(mp,_rq);
			
			
			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e){
			mp=e.getEssentialMap();
			WebUTIL.setMapInSession(mp,_rq);
			objStatus.add(Status.UNSUCESSFULL,"","");	
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
		
	}
	
	
	public static void getDeptIcdDetail(DepartmentIcdMasterFB _fb, HttpServletRequest _rq){
		DepartmentIcdMasterVO deptIcdVO=new DepartmentIcdMasterVO();
		DepartmentIcdMasterVO arrDeptIcdVO[]=null;
		UserVO userVO=getUserVO(_rq);
		Status objStatus= new Status();
		try
		{
			if(_fb.getChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				_fb.setDepartmentUnitCode(OpdConfig.DEFAULT_UNIT_CODE_ICD_MASTER);
				
			}
			if(_fb.getChoice().equals(OpdConfig.CHOICE_UNIT))
			{
				_fb.setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
				
			}
			HelperMethods.populate(deptIcdVO,_fb);
			setDeptIcdEssential(_fb,_rq);
			arrDeptIcdVO=DepartmentIcdMasterDATA.getDeptIcdDetail(deptIcdVO,userVO);
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.ARRAY_DEPT_ICD_VO,arrDeptIcdVO);
			_fb.setDisplayVO(arrDeptIcdVO);
			_fb.setCurrentblock("1");
			_fb.setCurrentPageNo("1");
			String pageString=settingThePageString(arrDeptIcdVO,_fb.getRecordPerPage(),DepartmentIcdMasterFB.pagesPerBlock);
			_fb.setPageString(pageString);
			objStatus.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"Records Not Found");	
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	}
	
	public static void saveDeptIcdCode(DepartmentIcdMasterFB _fb, HttpServletRequest _rq){
		
		DepartmentIcdMasterVO deptIcdVO[]= null;
		UserVO userVO=getUserVO(_rq);
		Status objStatus= new Status();
		String selectedIcdType=_fb.getIcdType();
		
		try{
		if(_fb.getValueChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
		{
			_fb.setDepartmentUnitCode(OpdConfig.DEFAULT_UNIT_CODE_ICD_MASTER);
			
		}
		if(_fb.getValueChoice().equals(OpdConfig.CHOICE_UNIT))
		{
			_fb.setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
			
		}
		
				
		deptIcdVO=new DepartmentIcdMasterVO[_fb.getSelectedCheckbox().length];
		for(int i=0;i<_fb.getSelectedCheckbox().length;i++)
		{
			if(selectedIcdType.equals(OpdConfig.CHOICE_GROUP))
			{
				deptIcdVO[i]=new DepartmentIcdMasterVO();
				HelperMethods.populate(deptIcdVO[i],_fb);
				deptIcdVO[i].setIcdGroupCode(_fb.getSelectedCheckbox()[i]);
			}
			if(selectedIcdType.equals(OpdConfig.CHOICE_SUBGROUP))
			{
				deptIcdVO[i]=new DepartmentIcdMasterVO();
				HelperMethods.populate(deptIcdVO[i],_fb);
				deptIcdVO[i].setIcdSubgroupCode(_fb.getSelectedCheckbox()[i]);
			}
			if(selectedIcdType.equals(OpdConfig.CHOICE_DISEASE) || selectedIcdType.equals(OpdConfig.CHOICE_SUBDISEASE))
			{
				deptIcdVO[i]=new DepartmentIcdMasterVO();
				HelperMethods.populate(deptIcdVO[i],_fb);
				deptIcdVO[i].setDiseaseCode(_fb.getSelectedCheckbox()[i]);
			}
			
		}
		
		
		DepartmentIcdMasterDATA.saveDeptIcdCode(deptIcdVO,_fb.getIcdType(),userVO);
		objStatus.add(Status.DONE,"Record Added","");
		objStatus.add(Status.NEW);
		
		}catch(HisDataAccessException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
		
	}
	
	public static void deleteDeptIcdCode(DepartmentIcdMasterFB _fb, HttpServletRequest _rq){
		DepartmentIcdMasterVO deptIcdVO=new DepartmentIcdMasterVO();
		
		UserVO userVO=getUserVO(_rq);
		Status objStatus= new Status();
		try
		{
			if(_fb.getValueChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				_fb.setDepartmentUnitCode(OpdConfig.DEFAULT_UNIT_CODE_ICD_MASTER);
				
			}
			if(_fb.getValueChoice().equals(OpdConfig.CHOICE_UNIT))
			{
				_fb.setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
				
			}
			
			for(int i=0;i<_fb.getSelectedCheckbox().length;i++)
			{	
				
				deptIcdVO.setDepartmentCode(_fb.getDepartmentCode());
				deptIcdVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
			}
			DepartmentIcdMasterDATA.deleteDeptIcdCode(deptIcdVO,_fb.getSelectedCheckbox(),_fb.getIcdType(),userVO);
			getDeptIcdDetail(_fb,_rq);
			objStatus.add(Status.DONE,"Record Deleted","");
			objStatus.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");	
		}		
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e){		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e){
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e){
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
	}
	
	public static void displayListForAdd(DepartmentIcdMasterFB _fb, HttpServletRequest _rq){
		List displayList=new ArrayList();
		Status objStatus= new Status();
		HttpSession session= _rq.getSession();
		if(_fb.getDepartmentCode().equals("-1"))
		{
			_fb.setChoice(OpdConfig.CHOICE_UNIT);
			
		}
		if(_fb.getDepartmentUnitCode().equals("-1"))
		{
			_fb.setChoice(OpdConfig.CHOICE_DEPARTMENT);
			
		}
		if(_fb.getIcdType()==null || _fb.getIcdType().equals(""))
		{
			_fb.setIcdType(OpdConfig.CHOICE_GROUP);
						
		}
		try{
			if(_fb.getIcdType().equals(OpdConfig.CHOICE_GROUP))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.EssentialBO_LIST_ICD_GROUP))
					displayList=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_ICD_GROUP);
				else
					throw new HisRecordNotFoundException("Group Not Available");
			}else if(_fb.getIcdType().equals(OpdConfig.CHOICE_SUBGROUP))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.EssentialBO_LIST_ICD_SUBGROUP))
					displayList=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_ICD_SUBGROUP);
				else
					throw new HisRecordNotFoundException("Sub Group Not Available");
			}else if(_fb.getIcdType().equals(OpdConfig.CHOICE_DISEASE))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.EssentialBO_LIST_ICD_DISEASE))
					displayList=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_ICD_DISEASE);
				else
					throw new HisRecordNotFoundException("Disease Not Available");
			}else if(_fb.getIcdType().equals(OpdConfig.CHOICE_SUBDISEASE))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.EssentialBO_LIST_ICD_SUBDISEASE))
					displayList=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_ICD_SUBDISEASE);
				else
					throw new HisRecordNotFoundException("Sub Disease Not Available");
			}
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");	
		}
		_fb.setDisplayList(displayList);
		_fb.setCurrentblock("1");
		_fb.setCurrentPageNo("1");
		String pageString=settingThePageString(displayList,_fb.getRecordPerPage(),_fb.getPagesPerBlock());
		_fb.setPageString(pageString);
	}
	
	public static void displayListForRemove(DepartmentIcdMasterFB _fb, HttpServletRequest _rq){
		List displayList=new ArrayList();
		Status objStatus= new Status();
		HttpSession session= _rq.getSession();
		if(_fb.getIcdType()==null || _fb.getIcdType().equals(""))
		{
			_fb.setIcdType(OpdConfig.CHOICE_GROUP);
						
		}
		Map mp=new HashMap();
		try
		{
			String code="";
			UserVO userVO=getUserVO(_rq);
			if(_fb.getValueChoice().equals(OpdConfig.CHOICE_UNIT))
			{
				
				code=_fb.getDepartmentUnitCode();
			}
			if(_fb.getValueChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				
				code=_fb.getDepartmentCode();
			}
			mp=DepartmentIcdMasterDATA.getDeptIcdRemovalDetails(_fb.getValueChoice(),code,userVO);
			WebUTIL.setMapInSession(mp,_rq);
			
			
			objStatus.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			mp=e.getEssentialMap();
			WebUTIL.setMapInSession(mp,_rq);
			objStatus.add(Status.UNSUCESSFULL,"","");		
		}
		try{
			
			if(_fb.getIcdType().equals(OpdConfig.CHOICE_GROUP))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.GROUP_LIST_FOR_REMOVAL))
					displayList=(List)session.getAttribute(OpdConfig.GROUP_LIST_FOR_REMOVAL);
				else
					throw new HisRecordNotFoundException("No Group Is Added");
			}else if(_fb.getIcdType().equals(OpdConfig.CHOICE_SUBGROUP))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.SUB_GROUP_LIST_FOR_REMOVAL))
					displayList=(List)session.getAttribute(OpdConfig.SUB_GROUP_LIST_FOR_REMOVAL);
				else
					throw new HisRecordNotFoundException("No Sub Group Is Added");
			}else if(_fb.getIcdType().equals(OpdConfig.CHOICE_DISEASE))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.DISEASE_LIST_FOR_REMOVAL))
					displayList=(List)session.getAttribute(OpdConfig.DISEASE_LIST_FOR_REMOVAL);
				else
					throw new HisRecordNotFoundException("No Disease Added");
			}else if(_fb.getIcdType().equals(OpdConfig.CHOICE_SUBDISEASE))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.SUB_DISEASE_LIST_FOR_REMOVAL))
					displayList=(List)session.getAttribute(OpdConfig.SUB_DISEASE_LIST_FOR_REMOVAL);
				else
					throw new HisRecordNotFoundException("No Sub Disease Is Added");
			}
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");	
		}
		_fb.setDisplayList(displayList);
		_fb.setCurrentblock("1");
		_fb.setCurrentPageNo("1");
		String pageString=settingThePageString(displayList,_fb.getRecordPerPage(),_fb.getPagesPerBlock());
		_fb.setPageString(pageString);
	}
	
	public static void search(DepartmentIcdMasterFB _fb,HttpServletRequest _rq){
		
		Status objStatus= new Status();
		DepartmentIcdMasterVO[] deptIcdVO=null;
		DepartmentIcdMasterVO[] arrayDeptIcdVO=(DepartmentIcdMasterVO[])WebUTIL.getSession(_rq).getAttribute(OpdConfig.ARRAY_DEPT_ICD_VO);
		List searchList=new ArrayList();
		String searchKey=((_fb.searchKey.trim()).replaceAll(" ","")).toLowerCase();//preparing the search key
		String compareKey="";
		try{
		if(_fb.searchType.equals("1")){
			for(int i=0;i<arrayDeptIcdVO.length;i++)
			{
				compareKey=(((arrayDeptIcdVO[i].getIcdGroup()).trim()).replaceAll(" ","")).toLowerCase();
				if(compareKey.indexOf(searchKey)==0)
				{
					searchList.add(arrayDeptIcdVO[i]);
				}
				
			}
		}
			if(_fb.searchType.equals("2")){
				for(int i=0;i<arrayDeptIcdVO.length;i++)
				{
					compareKey=(((arrayDeptIcdVO[i].getIcdSubGroup()).trim()).replaceAll(" ","")).toLowerCase();
					if(compareKey.indexOf(searchKey)==0)
					{
						searchList.add(arrayDeptIcdVO[i]);
					}
					
				}
			}
			if(_fb.searchType.equals("3")){
				for(int i=0;i<arrayDeptIcdVO.length;i++)
				{
					compareKey=(((arrayDeptIcdVO[i].getDisease()).trim()).replaceAll(" ","")).toLowerCase();
					if(compareKey.indexOf(searchKey)==0)
					{
						searchList.add(arrayDeptIcdVO[i]);
					}
					
				}
			}
		
		if(searchList.size()==0)
		{
			deptIcdVO=arrayDeptIcdVO;
			throw new HisRecordNotFoundException("No such record exists");
		}
		deptIcdVO=new DepartmentIcdMasterVO[searchList.size()];
		
		deptIcdVO=(DepartmentIcdMasterVO[])searchList.toArray(deptIcdVO);
				
		_fb.setDisplayVO(deptIcdVO);
		_fb.setCurrentblock("1");
		_fb.setCurrentPageNo("1");
		String pageString=settingThePageString(deptIcdVO,_fb.getRecordPerPage(),DepartmentIcdMasterFB.pagesPerBlock);
		_fb.setPageString(pageString);
		objStatus.add(Status.INPROCESS);
		
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
	}finally
	{
		WebUTIL.setStatus(_rq,objStatus);
	}
	}
	

	
}
