package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ProfileAccessPolicyDATA;
import opd.master.controller.fb.ProfileAccessPolicyFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.UserVO;

public class ProfileAccessPolicyUTIL extends ControllerUTIL{

	public static void fetchProfileAccessPolicyEssentials(ProfileAccessPolicyFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		try{
			UserVO userVO= getUserVO( _rq);
			setSysdate(_rq);
			essentialMap=ProfileAccessPolicyDATA.fetchProfileAccessPolicyEssentials(userVO);
					
			/*for(int i=1;i<OpdConfig.PROFILE_TYPE_IPD.length;i++)
			{
				
				Entry entry=new Entry();
				entry.setLabel(OpdConfig.PROFILE_TYPE_IPD[i]);
				entry.setValue(String.valueOf(i));
				listProfileType.add(entry);
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_TYPE_LIST, listProfileType);
			*/
			WebUTIL.setMapInSession(essentialMap, _rq);
				objStatus.add(Status.NEW,"","");
		}
		catch(HisRecordNotFoundException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisInsertNotAllowedException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisDataAccessException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in ProfileAccessPolicyUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static void getDeptUnitOfProfileAccess(ProfileAccessPolicyFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		List<Entry> deptUnitAlreadyMappedList= new ArrayList<Entry>();
		try{
			UserVO userVO= getUserVO( _rq);
			HttpSession session=_rq.getSession();
			ProfileAccessPolicyVO profileAccessPolicyVO=new ProfileAccessPolicyVO();
			profileAccessPolicyVO.setProfileType(_fb.getProfileType());
			profileAccessPolicyVO.setPolicyType(_fb.getPolicyType());
			deptUnitAlreadyMappedList=ProfileAccessPolicyDATA.getDeptUnitMappedWithProfileAccess(profileAccessPolicyVO,userVO);
			
			List deptUnitList=(List)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST);
			if(deptUnitAlreadyMappedList!=null)
				deptUnitList.removeAll(deptUnitAlreadyMappedList);
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST, deptUnitList);
			objStatus.add(Status.NEW,"","");
		}
		catch(HisRecordNotFoundException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisInsertNotAllowedException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisDataAccessException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in ProfileAccessPolicyUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static boolean saveProfileAccessPolicy(ProfileAccessPolicyFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		ProfileAccessPolicyVO profileAccessPolicyVO=new ProfileAccessPolicyVO();
		List<ProfileAccessPolicyVO> profileAccessPolicyVOList=new ArrayList<ProfileAccessPolicyVO>();
		try
		{
			UserVO userVO = getUserVO(_request);
			//HelperMethods.populate(profileAccessPolicyVO, _fb);
			String [] departmentUnitCode=_fb.getSelectedDeptUnitCode();
			if(departmentUnitCode!=null){
				for(String unitCode:departmentUnitCode){
					profileAccessPolicyVO=new ProfileAccessPolicyVO();
					HelperMethods.populate(profileAccessPolicyVO, _fb);
					profileAccessPolicyVO.setDeptUnitCode(unitCode);
					profileAccessPolicyVOList.add(profileAccessPolicyVO);
				}
			}
			ProfileAccessPolicyDATA.saveProfileAccessPolicy(profileAccessPolicyVOList,userVO);
			flag=true;
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
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
		return flag;
	}

	
	public static void fetchProfileAccessPolicyModify(ProfileAccessPolicyFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		HttpSession session=_request.getSession();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		ProfileAccessPolicyVO profileAccessPolicyVO=new ProfileAccessPolicyVO();
		try
		{
			setSysdate(_request);
			essentialMap=ProfileAccessPolicyDATA.fetchProfileAccessPolicyEssentials(userVO);
			
		/*	for(int i=1;i<OpdConfig.PROFILE_TYPE_IPD.length;i++)
			{
				
				Entry entry=new Entry();
				entry.setLabel(OpdConfig.PROFILE_TYPE_IPD[i]);
				entry.setValue(String.valueOf(i));
				listProfileType.add(entry);
			}
			WebUTIL.setAttributeInSession(_request, OpdConfig.PROFILE_TYPE_LIST, listProfileType);
			*/
			WebUTIL.setMapInSession(essentialMap, _request);
			
			List deptUnitList=(List)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST);
			
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String deptUnitCode=primaryKey[0];
			String profileType=primaryKey[1];
			String policyType=primaryKey[3];
			String serialNo=primaryKey[4];
			
			profileAccessPolicyVO.setDeptUnitCode(deptUnitCode);
			profileAccessPolicyVO.setProfileType(profileType);
			profileAccessPolicyVO.setPolicyType(policyType);
			profileAccessPolicyVO.setSerialNo(serialNo);
			
			profileAccessPolicyVO=ProfileAccessPolicyDATA.fetchProfileAccessPolicyModify(profileAccessPolicyVO,userVO);
			HelperMethods.populate(_fb, profileAccessPolicyVO);
			
			Iterator deptUnitListItr=deptUnitList.iterator();
			while(deptUnitListItr.hasNext())
			{
				Entry entry=(Entry)deptUnitListItr.next();
				if(deptUnitCode.equals(entry.getValue()))
				{
					_fb.setDeptUnitName(entry.getLabel());
				}
			}
			/*if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_REFER))
			{
				_fb.setProfileTypeName(OpdConfig.PROFILE_TYPE_REFER_DESC);
			}
			else if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_DISCHARGE))
			{
				_fb.setProfileTypeName(OpdConfig.PROFILE_TYPE_DISCHARGE_DESC);
			}
			else if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_CASESHEET))
			{
				_fb.setProfileTypeName(OpdConfig.PROFILE_TYPE_CASESHEET_DESC);
			}
			else if(_fb.getProfileType().equals(OpdConfig.PROFILE_TYPE_GENERAL))
			{
				_fb.setProfileTypeName(OpdConfig.PROFILE_TYPE_GENERAL_DESC);
			}
			*/
			if(_fb.getPolicyType().equals(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL))
			{
				_fb.setPolicyTypeName(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_NORMAL_LABEL);
			}
			else if(_fb.getPolicyType().equals(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED))
			{
				_fb.setPolicyTypeName(OpdConfig.PROFILE_ACCESS_POLICY_POLICY_TYPE_RESTRICTED_LABEL);
			}
			
			
			objStatus.add(Status.DONE);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
	
	
	/**
	 * modify the old record and insert new record in the database
	 * @param _fb
	 * @param _request
	 */
	
	public static boolean modifySave(ProfileAccessPolicyFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		boolean flag=false;
		ProfileAccessPolicyVO profileAccessPolicyVO=new ProfileAccessPolicyVO();
		try
		{
			//String disasterTypeCode=_fb.getDisasterTypeCode();
			HelperMethods.populate(profileAccessPolicyVO, _fb);
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String deptUnitCode=primaryKey[0];
			String profileType=primaryKey[1];
			String policyType=primaryKey[3];
			String serialNo=primaryKey[4];
			
			profileAccessPolicyVO.setDeptUnitCode(deptUnitCode);
			profileAccessPolicyVO.setProfileType(profileType);
			profileAccessPolicyVO.setPolicyType(policyType);
			profileAccessPolicyVO.setSerialNo(serialNo);
			
			ProfileAccessPolicyDATA.modifySave(profileAccessPolicyVO,userVO);
			//HelperMethods.populate(_fb, diasasterVO);
			flag=true;
			objStatus.add(Status.DONE);
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			
		}
		catch (HisException e)
		{
			flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
	
	
	

	
}
