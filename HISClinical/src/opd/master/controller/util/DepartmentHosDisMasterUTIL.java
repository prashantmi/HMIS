package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DepartmentHosDisMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.DepartmentHosDisMasterDATA;
import opd.master.controller.fb.DepartmentHosDisMasterFB;

public class DepartmentHosDisMasterUTIL extends ControllerUTIL {
	/**Method used for getting the department and Unit List
	 * and getting the Hospital Disease and SubDisease List
	 * @param _fb
	 * @param _rq
	 */
	public static void setDeptHosDisEssential(DepartmentHosDisMasterFB _fb, HttpServletRequest _rq)
	{
		Status objStatus= new Status();		
		setSysdate(_rq);
		Map mp=new HashMap();
		try
		{
			UserVO userVO=getUserVO(_rq);
			
			mp=DepartmentHosDisMasterDATA.getDeptHosDisEssential(userVO);
			WebUTIL.setMapInSession(mp,_rq);

			objStatus.add(Status.NEW);
		}
		catch(HisRecordNotFoundException e)
		{
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
		finally
		{
			WebUTIL.setStatus(_rq,objStatus);
			
		}	
		
	}
	
	/**Method used for displaying the list of Hospital diseases
	 * when the specified choice is selected
	 * @param _fb
	 * @param _rq
	 */
	public static void getDeptHosDisDetail(DepartmentHosDisMasterFB _fb, HttpServletRequest _rq){
		DepartmentHosDisMasterVO depthosDisVO=new DepartmentHosDisMasterVO();
		DepartmentHosDisMasterVO arrDeptHosDisVO[]=null;
		UserVO userVO=getUserVO(_rq);
		Status objStatus= new Status();
		try
		{
			if(_fb.getChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				_fb.setDepartmentUnitCode(OpdConfig.DEFAULT_UNIT_CODE_HOS_MASTER);
				
			}
			if(_fb.getChoice().equals(OpdConfig.CHOICE_UNIT))
			{
				_fb.setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
				
			}
			HelperMethods.populate(depthosDisVO,_fb);
			setDeptHosDisEssential(_fb,_rq);
			arrDeptHosDisVO=DepartmentHosDisMasterDATA.getDeptHosDisDetail(depthosDisVO,userVO);
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.ARRAY_DEPT_HOSDIS_VO,arrDeptHosDisVO);
			_fb.setDisplayVO(arrDeptHosDisVO);
			_fb.setCurrentblock("1");
			_fb.setCurrentPageNo("1");
			String pageString=settingThePageString(arrDeptHosDisVO,_fb.getRecordPerPage(),DepartmentHosDisMasterFB.pagesPerBlock);
			_fb.setPageString(pageString);
			objStatus.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"Records Not Found.........");	
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
	
	/**Method used for saving the Hospital diseases
	 * @param _fb
	 * @param _rq
	 */
	public static void saveDeptHosDisCode(DepartmentHosDisMasterFB _fb, HttpServletRequest _rq){
		
		DepartmentHosDisMasterVO deptHosVO[]= null;
		UserVO userVO=getUserVO(_rq);
		Status objStatus= new Status();
		//String selectedHosDisType=_fb.getHosdisType();
		
		try{
		if(_fb.getValueChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
		{
			_fb.setDepartmentUnitCode(OpdConfig.DEFAULT_UNIT_CODE_HOS_MASTER);
					}
		if(_fb.getValueChoice().equals(OpdConfig.CHOICE_UNIT))
		{
			_fb.setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
			
		}	
				
		deptHosVO=new DepartmentHosDisMasterVO[_fb.getSelectedCheckbox().length];
		for(int i=0;i<_fb.getSelectedCheckbox().length;i++)
		{
			deptHosVO[i]=new DepartmentHosDisMasterVO();
			deptHosVO[i].setDepartmentCode(_fb.getDepartmentCode());
			deptHosVO[i].setDepartmentUnitCode(_fb.getDepartmentUnitCode());
			deptHosVO[i].setHospitalDiseaseCode(_fb.getSelectedCheckbox()[i]);
				
		}
		DepartmentHosDisMasterDATA.saveDeptHosDisCode(deptHosVO,_fb.getHosdisType(),userVO);

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
	
	/**Method used for deleting the hospital diseases
	 * @param _fb
	 * @param _rq
	 */
	public static void deleteDeptHosDisCode(DepartmentHosDisMasterFB _fb, HttpServletRequest _rq){
		DepartmentHosDisMasterVO deptHosDisVO=new DepartmentHosDisMasterVO();
		
		UserVO userVO=getUserVO(_rq);
		Status objStatus= new Status();
		try
		{
			if(_fb.getValueChoice().equals(OpdConfig.CHOICE_DEPARTMENT))
			{
				_fb.setDepartmentUnitCode(OpdConfig.DEFAULT_UNIT_CODE_HOS_MASTER);
				
			}
			if(_fb.getValueChoice().equals(OpdConfig.CHOICE_UNIT))
			{
				_fb.setDepartmentCode(_fb.getDepartmentUnitCode().substring(0,3));
				
			}
			
			for(int i=0;i<_fb.getSelectedCheckbox().length;i++)
			{	
				
				deptHosDisVO.setDepartmentCode(_fb.getDepartmentCode());
				deptHosDisVO.setDepartmentUnitCode(_fb.getDepartmentUnitCode());
			}
			DepartmentHosDisMasterDATA.deleteDeptHosDisCode(deptHosDisVO,_fb.getSelectedCheckbox(),_fb.getHosdisType(),userVO);
			getDeptHosDisDetail(_fb,_rq);
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
	
	/**Method used for displaying the list of Hospital diseases
	 * which are not added
	 * @param _fb
	 * @param _rq
	 */
	public static void displayListForAdd(DepartmentHosDisMasterFB _fb, HttpServletRequest _rq){
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
		if(_fb.getHosdisType()==null || _fb.getHosdisType().equals(""))
		{
			_fb.setHosdisType(OpdConfig.CHOICE_DISEASE);
						
		}
		
		try{
			
			if(_fb.getHosdisType().equals(OpdConfig.CHOICE_DISEASE))	
			{				
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.EssentialBO_LIST_HOSDIS_DISEASE))
				{
					displayList=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_HOSDIS_DISEASE);
					if(session.getAttribute(OpdConfig.ARRAY_DEPT_HOSDIS_VO)!=null)
					{
						DepartmentHosDisMasterVO[] vos = (DepartmentHosDisMasterVO[])session.getAttribute(OpdConfig.ARRAY_DEPT_HOSDIS_VO);
						List l= new ArrayList();
						for(Object o:displayList) l.add(o);
						
						for(int i=0;i<vos.length;i++)
						{
							for(Object o :l)
							{
								if( vos[i].getHospitalDiseaseCode().equals(((Entry)o).getValue()))
								{
									l.remove(o);
									break;
								}
							}
						}
						displayList = l;						
					}					
				}					
				else
					throw new HisRecordNotFoundException("Hospital Disease not Available");
			}else if(_fb.getHosdisType().equals(OpdConfig.CHOICE_SUBDISEASE))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.EssentialBO_LIST_HOSDIS_SUBDISEASE))
				{
					displayList=(List)session.getAttribute(OpdConfig.EssentialBO_LIST_HOSDIS_SUBDISEASE);
					if(session.getAttribute(OpdConfig.ARRAY_DEPT_HOSDIS_VO)!=null)
					{
						DepartmentHosDisMasterVO[] vos = (DepartmentHosDisMasterVO[])session.getAttribute(OpdConfig.ARRAY_DEPT_HOSDIS_VO);
						List l= new ArrayList();
						for(Object o:displayList) l.add(o);
						
						for(int i=0;i<vos.length;i++)
						{
							for(Object o :l)
							{
								if( vos[i].getHospitalDiseaseCode().equals(((Entry)o).getValue()))
								{
									l.remove(o);
									break;
								}
							}
						}
						displayList = l;						
					}	
				}
				else
					throw new HisRecordNotFoundException("Hospital Sub Disease Not Available");
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
	
	/**Method Used for displaying the list for removal of Hospital diseases
	 * @param _fb
	 * @param _rq
	 */
	public static void displayListForRemove(DepartmentHosDisMasterFB _fb, HttpServletRequest _rq){
		List displayList=new ArrayList();
		Status objStatus= new Status();
		HttpSession session= _rq.getSession();
		if(_fb.getHosdisType()==null || _fb.getHosdisType().equals(""))
		{
			_fb.setHosdisType(OpdConfig.CHOICE_DISEASE);
						
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
			mp=DepartmentHosDisMasterDATA.getDeptHosDisRemovalDetails(_fb.getValueChoice(),code,userVO);
			WebUTIL.setMapInSession(mp,_rq);
			
			
			objStatus.add(Status.INPROCESS);
		}catch(HisRecordNotFoundException e){
			mp=e.getEssentialMap();
			WebUTIL.setMapInSession(mp,_rq);
			objStatus.add(Status.UNSUCESSFULL,"","");		
		}
		try{
			
			if(_fb.getHosdisType().equals(OpdConfig.CHOICE_DISEASE))
			{
				if(WebUTIL.objectIsAvailableInSession(_rq,OpdConfig.DISEASE_LIST_FOR_REMOVAL))
					displayList=(List)session.getAttribute(OpdConfig.DISEASE_LIST_FOR_REMOVAL);
				else
					throw new HisRecordNotFoundException("No Disease Added");
			}else if(_fb.getHosdisType().equals(OpdConfig.CHOICE_SUBDISEASE))
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

	
	/**Function used for Search according to Hospital disease
	 * @param _fb
	 * @param _rq
	 */
	public static void search(DepartmentHosDisMasterFB _fb,HttpServletRequest _rq){
		
		Status objStatus= new Status();
		DepartmentHosDisMasterVO[] deptHosDisVO=null;
		DepartmentHosDisMasterVO[] arrayDeptHosDisVO=(DepartmentHosDisMasterVO[])WebUTIL.getSession(_rq).getAttribute(OpdConfig.ARRAY_DEPT_HOSDIS_VO);
		List searchList=new ArrayList();
		String searchKey=((_fb.searchKey.trim()).replaceAll(" ","")).toLowerCase(); // preparing
																					// the
																					// search
																					// key
		String compareKey="";
		try{
			if(_fb.searchType.equals("1")){
				for(int i=0;i<arrayDeptHosDisVO.length;i++)
				{
					compareKey=(((arrayDeptHosDisVO[i].getDisease()).trim()).replaceAll(" ","")).toLowerCase();
					if(compareKey.indexOf(searchKey)==0)
					{
						searchList.add(arrayDeptHosDisVO[i]);
					}
					
				}
			}
		
		if(searchList.size()==0)
		{
			deptHosDisVO=arrayDeptHosDisVO;
			throw new HisRecordNotFoundException("No such record exists");
		}
		deptHosDisVO=new DepartmentHosDisMasterVO[searchList.size()];
		
		deptHosDisVO=(DepartmentHosDisMasterVO[])searchList.toArray(deptHosDisVO);
				
		_fb.setDisplayVO(deptHosDisVO);
		_fb.setCurrentblock("1");
		_fb.setCurrentPageNo("1");
		String pageString=settingThePageString(deptHosDisVO,_fb.getRecordPerPage(),DepartmentHosDisMasterFB.pagesPerBlock);
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
