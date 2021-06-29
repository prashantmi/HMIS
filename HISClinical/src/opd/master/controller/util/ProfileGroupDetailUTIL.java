package opd.master.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.ProfileGroupDetailDATA;
import opd.master.controller.fb.ProfileGroupDetailFB;
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
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.UserVO;

public class ProfileGroupDetailUTIL extends ControllerUTIL 
{
	
	public static void fetchProfileGroupDetailEssentials(ProfileGroupDetailFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		List profileGroupList=null;
		//List<Entry> listProfileType= new ArrayList<Entry>();
		try{
			UserVO userVO= getUserVO( _rq);
			essentialMap=ProfileGroupDetailDATA.fetchProfileGroupDetailEssentials(userVO);
					
			profileGroupList=(List)essentialMap.get(OpdConfig.PROFILE_GROUP_DETAIL_GROUP_LIST);
			
			if(profileGroupList.size()==0)
			{
				objStatus.add(Status.DONE,"","Either No Group Name Exists or All are Added");
			}
			
			
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
			objStatus.add(Status.ERROR,"Exception in DisasterAreaMstUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}


	
	public static boolean saveProfileGroupAccessPrivDetail(ProfileGroupDetailFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		List<ProfileGroupDtlVO> lstProfileAccesses = null;
		lstProfileAccesses = new ArrayList<ProfileGroupDtlVO>();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			
			for(int i=0;i<_fb.getSelectedUserUnitSpecificType().length;i++)
			{
				if(_fb.getSelectedUserUnitSpecificType()[i].equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
				{ 
					
					for(String unitCode : _fb.getSelectedUnits())
					{
						ProfileGroupDtlVO vo=new ProfileGroupDtlVO();
						vo.setProfileGroupId(_fb.getProfileGroupId());
						vo.setRecordMode(_fb.getSelectedUserUnitSpecificType()[i]);
						vo.setAccessRecordId(unitCode);
						lstProfileAccesses.add(vo);
					}
				}
				if(_fb.getSelectedUserUnitSpecificType()[i].equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND))
				{
					List userVOList =(List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);
					
					Iterator itr=userVOList.iterator();
					while(itr.hasNext())
					{
						UserVO userVO1=(UserVO)itr.next();
						ProfileGroupDtlVO vo=new ProfileGroupDtlVO();
						vo.setProfileGroupId(_fb.getProfileGroupId());
						vo.setRecordMode(_fb.getSelectedUserUnitSpecificType()[i]);
						vo.setAccessRecordId(userVO1.getUserId());
						lstProfileAccesses.add(vo);
						
					}																		
				}
				
			}
	
	
		
			ProfileGroupDetailDATA.saveProfileGroupAccessPrivDetail(lstProfileAccesses, userVO);
		
			
			objStatus.add(Status.DONE,"","Profile Group Detail Save Successfully");
			
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

	
	public static void fetchProfileGroupDetailAccessModify(ProfileGroupDetailFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		ProfileGroupDtlVO profileGroupDetailVO=new ProfileGroupDtlVO(); 
		//ProfileGroupDtlVO[] _profileGroupDetailVOs=null; 
		List<ProfileGroupDtlVO> lstProfileAccesses = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<Entry> lstAdded = new ArrayList<Entry>();
		HttpSession session=_request.getSession();
		//List<Entry> lstNotAdded = new ArrayList<Entry>();
		List<Entry> lstUnit=null;
		List<Entry> lstUnitAll=null;
		List<Entry> lstUnitLeft = new ArrayList<Entry>();
		 List<Entry> lstAddUnit = new ArrayList<Entry>();
		try
		{
			
			essentialMap=ProfileGroupDetailDATA.fetchProfileGroupDetailEssentials(userVO);
			WebUTIL.setMapInSession(essentialMap, _request);
			
			List usersList=(List)session.getAttribute(OpdConfig.PROFILE_GROUP_DETAIL_ALL_USERS);
			lstUnit=(List<Entry>)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL);
			lstUnitAll=(List<Entry>)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL);
			
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String profileGroupId=primaryKey[0];
			
			profileGroupDetailVO.setProfileGroupId(profileGroupId);
			
			lstProfileAccesses=ProfileGroupDetailDATA.fetchProfileGroupDetailAccessModify(profileGroupDetailVO,userVO);
			
			Map<String, String> mpAllUnits = new HashMap<String, String>(); 
			Iterator itr=lstUnit.iterator();
			while(itr.hasNext())
			{
				Entry entry=(Entry)itr.next();
				Entry entry1=new Entry();
				entry1.setLabel(entry.getLabel());
				entry1.setValue(entry.getValue());
				lstAddUnit.add(entry1);
				mpAllUnits.put(entry1.getValue(),entry1.getLabel());
				
			}
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_UNITS_MAP, mpAllUnits);
			
			
			List<UserVO> listAddedUser=new ArrayList();
			//List<Entry> lstNotAddedUser = new ArrayList<Entry>();
			Map<String, String> mpAllUsers = new HashMap<String, String>(); 
			for(int i=0;i<lstProfileAccesses.size();i++)
			{
				ProfileGroupDtlVO vo=new ProfileGroupDtlVO();
				UserVO _userVO=new UserVO();
				vo=(ProfileGroupDtlVO)lstProfileAccesses.get(i);
				_fb.setProfileGroupName(vo.getProfileGroupName());
				_fb.setProfileGroupId(vo.getProfileGroupId());
				_fb.setSerialNo(vo.getSerialNo());
				
				if(vo.getRecordMode().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND))
				{
					_userVO.setUserId(vo.getAccessRecordId());
					
					if(usersList!=null)
					{
					Iterator usersListIterator =usersList.iterator();
						while(usersListIterator.hasNext())
						{
							UserVO _userVOs=(UserVO)usersListIterator.next();
								if(_userVO.getUserId().equals(_userVOs.getUserId()))
								{
									_userVO.setUserSeatId(_userVOs.getUserSeatId());
									_userVO.setUserEmpID(_userVOs.getUserEmpID());
									_userVO.setUserType(_userVOs.getUserType());
									_userVO.setUserName(_userVOs.getUserName());
								}
						}
					}
					
					listAddedUser.add(_userVO);
					Entry entObj = new Entry();
					entObj.setLabel(_userVO.getUserName());
					entObj.setValue(_userVO.getUserId());
					mpAllUsers.put(entObj.getValue(), entObj.getLabel());
				}
				if(vo.getRecordMode().equals(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
				{
					
						if(lstUnitAll!=null)
						{
							Iterator lstUnitIterator=lstUnit.iterator();
							while(lstUnitIterator.hasNext())
							{
								Entry entry=(Entry)lstUnitIterator.next();
								if(vo.getAccessRecordId().equals(entry.getValue()))
								{
										Entry entry1=new Entry();
										entry1.setLabel(entry.getLabel());
										entry1.setValue(entry.getValue());
										lstAdded.add(entry1);
								}
									//map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_UNITS_MAP, mpAllUnits);
								}		
									
									Iterator iterator1=lstUnit.iterator();
									while(iterator1.hasNext())
									{
										Entry entObj=null;
										Entry entry1=(Entry)iterator1.next();
										lstUnitLeft.add(entry1);
										
											if(entry1.getValue().equalsIgnoreCase(vo.getAccessRecordId()))
												{
													entObj=entry1;
												}
 										
										if(entObj!=null)
										{
											iterator1.remove();
										}
										
									 }
									
								
							}
						}
				}
				
				
			
			WebUTIL.setAttributeInSession(_request, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, listAddedUser);
			WebUTIL.setAttributeInSession(_request, OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL, lstUnitLeft);
			WebUTIL.setAttributeInSession(_request, OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST, lstUnit);
			WebUTIL.setAttributeInSession(_request, OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST, lstAdded);
			
			if(listAddedUser.size()>0)
			{
				_fb.setUserPriveledgeFlag(OpdConfig.PROFILE_GROUP_DETAIL_USER_PRESENT);
			}
			else
			{
				_fb.setUserPriveledgeFlag(OpdConfig.PROFILE_GROUP_DETAIL_USER_NOT_PRESENT);
			}
			
			
				if(lstAdded.size()>0 && listAddedUser.size()>0)
				{
					_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC+"#"+OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
				}
				else if(lstAdded.size()>0)
				{
					_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC);
				}
				else if(listAddedUser.size()>0)
				{
					_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
				}
			
			
			
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, listAddedUser);
			//map.put(OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_USER_LIST, lstNotAddedUser);
			//map.put(OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST, lstNotAddedUser);
			map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP, mpAllUsers);
	
		WebUTIL.setMapInSession(map, _request);
			// HelperMethods.populate(_fb, profileGroupDetailVO);
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
	
	public static boolean modifySave(ProfileGroupDetailFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		boolean flag=false;
		HttpSession session=_request.getSession();
		List<ProfileGroupDtlVO> lstProfileAccesses = null;
		lstProfileAccesses = new ArrayList<ProfileGroupDtlVO>();
		ProfileGroupDtlVO profileGroupDtlVO=new ProfileGroupDtlVO();
		try
		{
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String profileGroupId=primaryKey[0];
			
			for(int i=0;i<_fb.getSelectedUserUnitSpecificType().length;i++)
			{
				if(_fb.getSelectedUserUnitSpecificType()[i].equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC))
				{ 
					
					for(String unitCode : _fb.getSelectedUnits())
					{
						ProfileGroupDtlVO vo=new ProfileGroupDtlVO();
						vo.setProfileGroupId(profileGroupId);
						vo.setSerialNo(_fb.getSerialNo());
						vo.setRecordMode(_fb.getSelectedUserUnitSpecificType()[i]);
						vo.setAccessRecordId(unitCode);
						lstProfileAccesses.add(vo);
					}
				}
				if(_fb.getSelectedUserUnitSpecificType()[i].equalsIgnoreCase(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND))
				{
					List userVOList =(List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);
					
					Iterator itr=userVOList.iterator();
					while(itr.hasNext())
					{
						UserVO userVO1=(UserVO)itr.next();
						ProfileGroupDtlVO vo=new ProfileGroupDtlVO();
						vo.setProfileGroupId(profileGroupId);
						vo.setSerialNo(_fb.getSerialNo());
						vo.setRecordMode(_fb.getSelectedUserUnitSpecificType()[i]);
						vo.setAccessRecordId(userVO1.getUserId());
						lstProfileAccesses.add(vo);
						
					}																		
				}
				
			}
	
			
			HelperMethods.populate(profileGroupDtlVO, _fb);
			profileGroupDtlVO.setProfileGroupId(profileGroupId);
			ProfileGroupDetailDATA.modifySave(lstProfileAccesses,profileGroupDtlVO,userVO);
			
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
	
	public static void setSearchUsers(ProfileGroupDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<UserVO> lstSearchUsers = new ArrayList<UserVO>();
		Map<String, String> mpAllUsers = new HashMap<String, String>();
		Map map = new HashMap();
		List<Entry> lstUnit = null;
		//List<Entry> lstUnitAll = null;
		List<Entry> lstUnitLeft = new ArrayList<Entry>();
		//List<Entry> lstNotAdded = new ArrayList<Entry>();
		List<Entry> lstAdded = new ArrayList<Entry>();
		List<Entry> lstAddUnit = new ArrayList<Entry>();
		//List<Entry> allUnitList = new ArrayList<Entry>();
		HttpSession session = null;
		try
		{
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			session = WebUTIL.getSession(_rq);
			
			// Setting List
			//mpAllUsers = (HashMap<String, String>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP);
			
			lstUnit=(List<Entry>)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL);
			//lstUnitAll=(List<Entry>)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL);
			
			
			// Setting Search Users
			String searchStr = _fb.getSearchString();
			if(searchStr!=null && !searchStr.trim().equals(""))
			{
				lstSearchUsers = ProfileGroupDetailDATA.getSearchUsersForProfileAccessPrivil(_fb.getSearchMode(),searchStr.toUpperCase(),userVO);
			}		
			if(lstSearchUsers.size()>0)
			{
				for(UserVO vo : lstSearchUsers)
					mpAllUsers.put(vo.getUserId(), vo.getUserSeatId());
				WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST, lstSearchUsers);
				objStatus.add(Status.NEW);
				objStatus.add(Status.TRANSINPROCESS);
								
			}
			else
				objStatus.add(Status.NEW,"","No User Found");			
			
			 List lstAddedUnit=(List)session.getAttribute(OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST); 
			
			 if(lstAddedUnit!=null)
			 {
				    if(lstAddedUnit.size()>0 || _fb.getSelectedUnits()!=null && _fb.getSelectedUnits().length>0)
					{
						_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC+"#"+OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
					}
				   else
					{
						_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
					}
			 }
			 else
			 {
				  if(_fb.getSelectedUnits()!=null && _fb.getSelectedUnits().length>0)
					{
						_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC+"#"+OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
					}
				   else
					{
						_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
					}
			 }
				
				Map<String, String> mpAllUnits = new HashMap<String, String>(); 
				Iterator itr=lstUnit.iterator();
				while(itr.hasNext())
				{
					Entry entry=(Entry)itr.next();
					Entry entry1=new Entry();
					entry1.setLabel(entry.getLabel());
					entry1.setValue(entry.getValue());
					lstAddUnit.add(entry1);
					mpAllUnits.put(entry1.getValue(),entry1.getLabel());
					
				}
				map.put(OpdConfig.OPD_PATIENT_PROFILE_ALL_UNITS_MAP, mpAllUnits);
				
				
				Iterator iterator=lstUnit.iterator();
				while(iterator.hasNext())
				{
					Entry entObj=null;
					Entry entry=(Entry)iterator.next();
					lstUnitLeft.add(entry);
					if(_fb.getSelectedUnits()!=null)
					{
						for(int i=0;i<_fb.getSelectedUnits().length;i++)
						{
							if(entry.getValue().equalsIgnoreCase(_fb.getSelectedUnits()[i]))
							{
								entObj=entry;
								break;
							}
						}
					}
					if(entObj!=null)
					{
						iterator.remove();
					}
					
				}
				
				/*if(_fb.getUnitList().length>0)
				{
					for(String _userId : _fb.getUnitList())
					{
						Entry entObj = new Entry();
						entObj.setValue(_userId);
						entObj.setLabel(mpAllUnits.get(_userId));
						lstNotAdded.add(entObj);
					}
				}*/
				
				
				if(_fb.getSelectedUnits().length>0)
				{
					for(String _userId : _fb.getSelectedUnits())
					{
						Entry entObj = new Entry();
						entObj.setValue(_userId);
						entObj.setLabel(mpAllUnits.get(_userId));
						lstAdded.add(entObj);
					}
				}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL, lstUnitLeft);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST, lstUnit);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST, lstAdded);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP, mpAllUsers);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void addSearchUsers(ProfileGroupDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Map<String, String> mpAllUsers = null;
		List<Entry> lstNotAdded = new ArrayList<Entry>();
		//List<Entry> lstAdded = new ArrayList<Entry>();
		List listAdded=new ArrayList();
		List searchUserList=new ArrayList();
		HttpSession session = null;
		try
		{
			setSysdate(_rq);
			session = WebUTIL.getSession(_rq);
			
			// Setting List
			mpAllUsers = (HashMap<String, String>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP); 
			
			_fb.setUsersList(new String[0]);
			_fb.setSelectedUsers(new String[0]);
			
			listAdded=(List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);
			searchUserList=(List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_SEARCHED_USER_LIST);
			
			if(listAdded==null)
			{	
				listAdded=new ArrayList();
				for(int i=0;i<searchUserList.size();i++)
				{
					UserVO userVO=new UserVO();
					userVO=(UserVO)searchUserList.get(i);
					for(int j=0;j<_fb.getSelectedUserIndex().length;j++)
					{
						if(userVO.getUserId().equals(_fb.getSelectedUserIndex()[j]))
						listAdded.add(searchUserList.get(i));
					}
				}
			}
			else
			{
				for(int i=0;i<searchUserList.size();i++)
				{
					UserVO userVO=new UserVO();
					userVO=(UserVO)searchUserList.get(i);
					for(int j=0;j<_fb.getSelectedUserIndex().length;j++)
					{
						if(userVO.getUserId().equals(_fb.getSelectedUserIndex()[j]))
						listAdded.add(searchUserList.get(i));
					}
				}
			}
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, listAdded);
			
			// Setting Search Users
			for(String userId : _fb.getSelectedUserIndex())
			{
				Entry entObj = new Entry();
				entObj.setValue(userId);
				entObj.setLabel(mpAllUsers.get(userId));
				lstNotAdded.add(0,entObj);
			}
			
			List lstAddedUnit=(List)session.getAttribute(OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST); 
			 
			if(listAdded.size()>0)
			{
				_fb.setUserPriveledgeFlag(OpdConfig.PROFILE_GROUP_DETAIL_USER_PRESENT);
			}
			else
			{
				_fb.setUserPriveledgeFlag(OpdConfig.PROFILE_GROUP_DETAIL_USER_NOT_PRESENT);
			}
			
			if(lstAddedUnit.size()>0)
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC+"#"+OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}
			else
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ESSENTIAL_USER_LIST, lstNotAdded);
			//WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, lstAdded);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	
	public static void removeUserPriv(ProfileGroupDetailFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		//Map<String, String> mpAllUsers = null;
		//List<Entry> lstNotAdded = new ArrayList<Entry>();
		//List<Entry> lstAdded = new ArrayList<Entry>();
		//List listAdded=new ArrayList();
		//Map map = new HashMap();
		//List<Entry> lstUnit = null;
		//List<Entry> lstUnitAll = null;
		//List searchUserList=new ArrayList();
		//List<Entry> lstAddUnit = new ArrayList<Entry>();
		//List<Entry> allUnitList = new ArrayList<Entry>();
		//List<Entry> lstUnitLeft = new ArrayList<Entry>();
		HttpSession session = null;
		try
		{
			setSysdate(_rq);
			//UserVO userVO = getUserVO(_rq);
			session = WebUTIL.getSession(_rq);
			
			List addedUserList=(List)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST);
			
			//mpAllUsers = (HashMap<String, String>)session.getAttribute(OpdConfig.OPD_PATIENT_PROFILE_ALL_USERS_MAP);
			
			//lstUnit=(List<Entry>)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL);
			//lstUnitAll=(List<Entry>)session.getAttribute(OpdConfig.PROFILE_GROUP_MASTER_DEPT_UNIT_CODE_LIST_ALL);
			List lstAddedUnit=(List)session.getAttribute(OpdConfig.PROFILE_GROUP_DETAIL_ADDED_UNIT_LIST);
			
			String index=_fb.getDeleteIndex();
			//UserVO _userVO=(UserVO)addedUserList.get(Integer.parseInt(index));
			addedUserList.remove(Integer.parseInt(index));
			
		
			if(addedUserList.size()>0)
			{
				_fb.setUserPriveledgeFlag(OpdConfig.PROFILE_GROUP_DETAIL_USER_PRESENT);
			}
			else
			{
				_fb.setUserPriveledgeFlag(OpdConfig.PROFILE_GROUP_DETAIL_USER_NOT_PRESENT);
			}
			
			if(lstAddedUnit!=null)
			{	
				if(lstAddedUnit.size()>0)
				{
					_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_UNIT_SPECIFIC+"#"+OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
				}
			}
			else
			{
				_fb.setPreviousUnitUser(OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_USER_BOUND);
			}
			
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OPD_PATIENT_PROFILE_ADDED_USER_LIST, addedUserList);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

}
