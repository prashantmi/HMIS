package new_investigation.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import new_investigation.vo.InvParameterMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.InvParameterMstDATA;
import new_investigation.masters.controller.data.TestGroupInfoMstDATA;
import new_investigation.masters.controller.fb.InvParameterMstFB;
import new_investigation.masters.controller.fb.TestGroupInfoMstFB;

public class TestGroupInfoMstUTIL implements MasterInterface
{
	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;

	public void setHttpSession(HttpSession session)
	{
		this.httpSession = session;
	}

	public String getButtons()
	{
		StringBuilder br = new StringBuilder();
		br.append("<img src='../../hisglobal/images/btn-add.png' style='cursor:pointer;' title='Click To Add A Record'  border=0 tabindex='1' onKeyPress='add(\"ADD\");' onClick='add(\"ADD\");'>");
		br.append("<img src='../../hisglobal/images/btn-mo.png' style='cursor:pointer;'; title='Select A Record To Modify'  border=0 tabindex='1' onKeyPress='edit(\"MODIFY\");' onClick='edit(\"MODIFY\");' >");
		br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;'; title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
		br.append("<img src='../../hisglobal/images/btn-view.png' style='cursor:pointer;' title='Select A Record To View'  border=0  tabindex='1' onKeyPress='view(\"VIEWDATA\");' onClick='view(\"VIEWDATA\");'>");
		br.append("<img src='../../hisglobal/images/btn-rpt.png' style='cursor:pointer;' title='Select A Record To Generate Reoprts'  border=0  tabindex='1' onKeyPress='report(\"REPORT\");'  onClick='report(\"REPORT\");'>");
		br.append("<img src='../../hisglobal/images/btn-ccl.png' style='cursor:pointer;' title='cancel page'  border=0  tabindex='1' onKeyPress='cancelFunc();'  onClick='cancelFunc();'>");
		return br.toString();
	}

	public String[] getColsWidth() 
	{
		return null;
	}


	public String[] getColumnHeader()
	{
		String[] columnHdr = { "Group Name","Laboratory Name","Test Name" };
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[1];
		comboQuery[0] = "1^Active";
		//#2^InActive";
		return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTGROUPINFO.HIVT_TEST_GROUP_INFO_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTGROUPINFO.HIVT_TEST_GROUP_INFO_MST.COND.0"));

		return deleteQuery;
	}


	public String getJsFiles() 
	{
		// TODO Auto-generated method stub
		return null;
	}



	public String getMainQuery(String[] cmb)
	{
	
		//	UserVO userVO;
		StringBuffer brMainQuery = new StringBuffer();
		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(Config.SUPER_USER_HOSPITAL_CODE);
		
	
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"testgroupinfoMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}

		return brMainQuery.toString();
	}


	public String getMasterName()
	{
		String masterName = "Global Test Group Information Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "G.HGSTR_GROUP_NAME","2", "L.GSTR_LAB_NAME" };
		return orderBy;
	}

	public int getPage_per_block()
	{
		return 10;
	}


	public int getRecord_per_page() 
	{
		return 10;
	}


	public String[] getSearchField() 
	{		
		String strSearchField[] = { "1", " UPPER(hgstr_group_name)", "2","UPPER(L.gstr_lab_name)","3","UPPER(T.gstr_test_name)"};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Group Name");
		viewHdr.add("D");
		
		viewHdr.add("Laboratory Name");
		viewHdr.add("D");


		viewHdr.add("Test Name");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.testgroupinfo.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveTestGroupInfo(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		TestGroupInfoMasterVO[] insert_testgroupinfo_VO = null;
		TestGroupInfoMasterVO[] delete_testgroupinfo_VO = null;
		TestGroupInfoMasterVO testGroup_VO=new TestGroupInfoMasterVO();
		List insertList=new ArrayList();
		List deleteList=new ArrayList();


		boolean saveFlag = true;
		try
		{
		
		//getting the old mapped list
			
		List oldMappedList=TestGroupInfoMstUTIL.getOldMappedList(testgroupinfo_fb, _request);			
			
		//getting the New mapped list
		
		List newMappedList=TestGroupInfoMstUTIL.getNewMappedList(testgroupinfo_fb, _request);
			
		if(newMappedList==null)
			newMappedList=new ArrayList();
			
		if(oldMappedList==null)
			oldMappedList=new ArrayList();
			
			
			//getting the insert  list
			insertList=TestGroupInfoMstUTIL.getCompareList(newMappedList, oldMappedList);	
			
			
			//getting the insert  list
			deleteList=TestGroupInfoMstUTIL.getCompareList(oldMappedList,newMappedList);	
			
			//getting the insert MasterVO
			if(insertList!=null)				
				insert_testgroupinfo_VO=TestGroupInfoMstUTIL.getTestGroupInfoVO(insertList, testgroupinfo_fb);
			
			//getting the insert MasterVO
			if(deleteList!=null)				
				delete_testgroupinfo_VO=TestGroupInfoMstUTIL.getTestGroupInfoVO(deleteList, testgroupinfo_fb);
			
			//template related values
			testGroup_VO.setGroupCode(testgroupinfo_fb.getGroupCode());
			testGroup_VO.setLabCode(testgroupinfo_fb.getLabCode());
			testGroup_VO.setIsDynamicResult(testgroupinfo_fb.getIsDynamicResult());
			testGroup_VO.setIsDynamicTemplate(testgroupinfo_fb.getIsDynamicTemplate());
			testGroup_VO.setPrintingTemplateCode(testgroupinfo_fb.getPrintingTemplateCode());
			
			
			
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			
			
			//if(testgroupinfo_fb.getHmode()!=null && testgroupinfo_fb.getHmode().equals("MODIFYSAVE"))
			//{
				TestGroupInfoMstDATA.modifyTemplateValue(testGroup_VO, userVO);
				
			//}
			
			
			
/*			HelperMethods.populate(testgroupinfo_VO, testgroupinfo_fb);
*/			
			TestGroupInfoMstDATA.saveTestGroupInfo(insert_testgroupinfo_VO,delete_testgroupinfo_VO, userVO);
			objStatus.add(Status.DONE, "Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,  e.getMessage(),"");
		}
		finally
		{

			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return saveFlag;
	}


	public static boolean fetchCheckListTestGroupInfo(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfo_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",groupCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = testgroupinfo_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			groupCode = concatid[0];
			labCode = concatid[1];
			hospitalCode=concatid[2];
			testgroupinfo_VO.setIsActive(testgroupinfo_fb.getIsActive());

		
			testgroupinfo_VO.setHospitalCode(hospitalCode); 
			testgroupinfo_VO.setHospitalCode(hospitalCode);
			testgroupinfo_fb.setGroupCode(groupCode); 
			testgroupinfo_VO.setGroupCode(groupCode);
			testgroupinfo_fb.setLabCode(labCode); 
			testgroupinfo_VO.setLabCode(labCode);
			
			testgroupinfo_fb.setSelectedChk(testgroupinfo_fb.getChk()[0]);
			testgroupinfo_VO.setHmode(testgroupinfo_fb.getHmode());
			
			
			mp.putAll(TestGroupInfoMstDATA.fetchTestGroupInfo(testgroupinfo_VO, userVO));
			mp.putAll(TestGroupInfoMstDATA.getTest(testgroupinfo_VO, userVO));
			WebUTIL.setMapInSession(mp, _request);

		
			testgroupinfo_fb.setHospitalCode(hospitalCode); 
			testgroupinfo_VO.setHospitalCode(hospitalCode);
			testgroupinfo_fb.setGroupCode(groupCode); 
			testgroupinfo_VO.setGroupCode(groupCode);
			testgroupinfo_fb.setLabCode(labCode); 
			testgroupinfo_VO.setLabCode(labCode);
			
			//testgroupinfo_fb.setTestCode(testgroupinfo_VO.getTestCode());
			testgroupinfo_fb.setIsDynamicResult(testgroupinfo_VO.getIsDynamicResult());
			testgroupinfo_fb.setIsDynamicTemplate(testgroupinfo_VO.getIsDynamicTemplate());
			testgroupinfo_fb.setPrintingTemplateCode(testgroupinfo_VO.getPrintingTemplateCode());
			HelperMethods.populate(testgroupinfo_fb, testgroupinfo_VO);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}


	public static boolean fetchTestGroupInfo(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfo_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			
			
			
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=TestGroupInfoMstDATA.fetchTestGroupInfo(testgroupinfo_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(testgroupinfo_fb, testgroupinfo_VO);
			
			
	
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}


	

	public static boolean reFetchCheckListTestGroupInfo(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfo_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",groupCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = testgroupinfo_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			groupCode = concatid[0];
			labCode = concatid[1];
			hospitalCode=concatid[2];


			testgroupinfo_VO.setIsActive(testgroupinfo_fb.getIsActive());

		
			testgroupinfo_fb.setHospitalCode(hospitalCode); 
			testgroupinfo_VO.setHospitalCode(hospitalCode);
			testgroupinfo_fb.setGroupCode(groupCode); 
			testgroupinfo_VO.setGroupCode(groupCode);
			testgroupinfo_fb.setLabCode(labCode); 
			testgroupinfo_VO.setLabCode(labCode);
			testgroupinfo_VO.setHmode(testgroupinfo_fb.getHmode());
			
			mp.putAll(TestGroupInfoMstDATA.fetchTestGroupInfo(testgroupinfo_VO, userVO));
			mp.putAll(TestGroupInfoMstDATA.getTest(testgroupinfo_VO, userVO));
			WebUTIL.setMapInSession(mp, _request);

		
			testgroupinfo_fb.setHospitalCode(hospitalCode); 
			testgroupinfo_VO.setHospitalCode(hospitalCode);
			testgroupinfo_fb.setGroupCode(groupCode); 
			testgroupinfo_VO.setGroupCode(groupCode);
			testgroupinfo_fb.setLabCode(labCode); 
			testgroupinfo_VO.setLabCode(labCode);

			testgroupinfo_fb.setIsDynamicResult(testgroupinfo_VO.getIsDynamicResult());
			testgroupinfo_fb.setIsDynamicTemplate(testgroupinfo_VO.getIsDynamicTemplate());
			testgroupinfo_fb.setPrintingTemplateCode(testgroupinfo_VO.getPrintingTemplateCode());
	


			HelperMethods.populate(testgroupinfo_fb, testgroupinfo_VO);

			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean getTest(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfo_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			testgroupinfo_VO.setGroupCode(testgroupinfo_fb.getGroupCode());
			testgroupinfo_VO.setLabCode(testgroupinfo_fb.getLabCode());

			Map mp=new HashMap(); 


			mp=TestGroupInfoMstDATA.getTest(testgroupinfo_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(testgroupinfo_fb, testgroupinfo_VO);
			testgroupinfo_fb.setIsDynamicTemplate(testgroupinfo_VO.getIsDynamicTemplate()==null?"0":testgroupinfo_VO.getIsDynamicTemplate());
			testgroupinfo_fb.setIsDynamicResult(testgroupinfo_VO.getIsDynamicResult()==null?"0":testgroupinfo_VO.getIsDynamicResult());
			testgroupinfo_fb.setPrintingTemplateCode(testgroupinfo_VO.getPrintingTemplateCode()==null?"-1":testgroupinfo_VO.getPrintingTemplateCode());
			

			
			
			objStatus.add(Status.NEW);
		}
		/*catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}*/
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	
	public static List getOldMappedList(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
		List oldMappedListFromSession=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.LIST_SELECTED_COMBO);
		List oldMappedList=new ArrayList();
		
		try
		{
			Iterator itr=oldMappedListFromSession.iterator();
			
			while(itr.hasNext()){
			
				Entry obj=(Entry)itr.next();
					
				oldMappedList.add(obj.getValue());
						
					}
						
		}
		catch (Exception e)
		{
		e.printStackTrace();
		
			
		}
		
		return oldMappedList;
		
}
	
	
	public static List getNewMappedList(TestGroupInfoMstFB testgroupinfo_fb, HttpServletRequest _request)
	{
				List newMappedList=new ArrayList();
		
		try
		{
			if(testgroupinfo_fb.getMappedList()!=null && testgroupinfo_fb.getMappedList().length!=0){
				
				
			for(int i=0; i <  testgroupinfo_fb.getMappedList().length; i++){
				
				newMappedList.add(testgroupinfo_fb.getMappedList()[i]);
					
				
				}
				
			
			
			
				}
		}		
		catch (Exception e)
		{
		e.printStackTrace();
		
			
		}
		
		
		return newMappedList;
		
}
	
	
	public static List getCompareList(List list1, List list2)
	{
				List resultantList=new ArrayList();
		
		try
		{
			
			for(int i=0; i < list1.size(); i++){
				
				String  str1=(String)list1.get(i);
					
				if(!list2.contains(str1))
					resultantList.add(str1);
					
				
				}
				
			
	
			
		}
		catch (Exception e)
		{
		e.printStackTrace();
		
			
		}
		
		return resultantList;
}

	
	public static TestGroupInfoMasterVO[] getTestGroupInfoVO(List documentList,TestGroupInfoMstFB testgroupinfo_fb)
	{
		TestGroupInfoMasterVO[] testgroupinfo_VO=null;
		
		
		try
		{
			if(documentList!=null && documentList.size()!=0){
				testgroupinfo_VO=new TestGroupInfoMasterVO[documentList.size()];
				
			for(int i=0; i < testgroupinfo_VO.length; i++){
				testgroupinfo_VO[i]=new TestGroupInfoMasterVO();
				testgroupinfo_VO[i].setTestCode((String)documentList.get(i));
				testgroupinfo_VO[i].setLabCode(testgroupinfo_fb.getLabCode());
				testgroupinfo_VO[i].setGroupCode(testgroupinfo_fb.getGroupCode());
				testgroupinfo_VO[i].setRemarks(testgroupinfo_fb.getRemarks());
				testgroupinfo_VO[i].setIsDynamicTemplate(testgroupinfo_fb.getIsDynamicTemplate());
				testgroupinfo_VO[i].setIsDynamicResult(testgroupinfo_fb.getIsDynamicResult());
				testgroupinfo_VO[i].setPrintingTemplateCode(testgroupinfo_fb.getPrintingTemplateCode());
				
				}
				}
		}		
		catch (Exception e)
		{
		e.printStackTrace();
			
		}
return testgroupinfo_VO;
		
}
	
	
	
	
	
	
}


