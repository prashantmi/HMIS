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
import new_investigation.masters.controller.data.TestGroupInfoLocalMstDATA;
import new_investigation.masters.controller.data.TestGroupInfoMstDATA;
import new_investigation.masters.controller.fb.TestGroupInfoLocalMstFB;

public class TestGroupInfoLocalMstUTIL implements MasterInterface
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
		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();

		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(hosCode);
		
	
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"testgroupinfoMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}
            System.out.println(brMainQuery.toString());
		return brMainQuery.toString();
	}


	public String getMasterName()
	{
		String masterName = "Local Test Group Information Master";
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
		String strSearchField[] = { "1", " UPPER(hgstr_group_name) ", "2","UPPER(L.gstr_lab_name)","3","UPPER(T.gstr_test_name)" };
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
	public static boolean saveTestGroupInfoLocal(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		TestGroupInfoMasterVO[] insert_testgroupinfolocal_VO = null;
		TestGroupInfoMasterVO[] delete_testgroupinfolocal_VO = null;
		TestGroupInfoMasterVO[] modify_testgroupinfolocal_VO = null;

		TestGroupInfoMasterVO testGroup_VO=new TestGroupInfoMasterVO();
		
		List insertList=new ArrayList();
		List deleteList=new ArrayList();

		List userTestSeqList=new ArrayList();
		List testSeqResultList=new ArrayList();
		List modifyTestNameList=new ArrayList();
		List modifyTestSeqList=new ArrayList();
		
		
		
		boolean saveFlag = true;
		try
		{
		
		//getting the old mapped list
			
		List oldMappedList=TestGroupInfoLocalMstUTIL.getOldMappedList(testgroupinfolocal_fb, _request);			
			
		//getting the New mapped list
		
		List newMappedList=TestGroupInfoLocalMstUTIL.getNewMappedList(testgroupinfolocal_fb, _request);
			
		if(newMappedList==null)
			newMappedList=new ArrayList();
			
		if(oldMappedList==null)
			oldMappedList=new ArrayList();
			
		//gettign the test seq numbers list
		for(int j=0;j<testgroupinfolocal_fb.getTestSeqNo().length;j++)
			userTestSeqList.add(testgroupinfolocal_fb.getTestSeqNo()[j]);

		
		
		
		//getting the insert  list
			if(testgroupinfolocal_fb.getCheckLocal().equals("0"))
				insertList=newMappedList;
			else
			//	insertList=TestGroupInfoLocalMstUTIL.getCompareList(newMappedList, oldMappedList);	
			{

				for(int i=0; i < newMappedList.size(); i++){

					String  str1=(String)newMappedList.get(i);
					String cannedStr=(String)userTestSeqList.get(i);
					
					if(!oldMappedList.contains(str1))
					{	insertList.add(str1);
					testSeqResultList.add(cannedStr);
					}
					else
					{
						modifyTestNameList.add(str1);
						modifyTestSeqList.add(cannedStr);
					}

				}

				userTestSeqList=testSeqResultList;
			}
			
			//getting the insert  list
			deleteList=TestGroupInfoLocalMstUTIL.getCompareList(oldMappedList,newMappedList);	
			
			//getting the insert MasterVO
			if(insertList!=null)				
				insert_testgroupinfolocal_VO=TestGroupInfoLocalMstUTIL.getTestGroupInfoLocalVO(insertList, testgroupinfolocal_fb,userTestSeqList,"1");
			
			//getting the insert MasterVO
			if(deleteList!=null)				
				delete_testgroupinfolocal_VO=TestGroupInfoLocalMstUTIL.getTestGroupInfoLocalVO(deleteList, testgroupinfolocal_fb,userTestSeqList,"0");
			
			
			

			//getting the modified MasterVO
			if(modifyTestNameList!=null)				
				modify_testgroupinfolocal_VO=TestGroupInfoLocalMstUTIL.getTestGroupInfoLocalVO(modifyTestNameList, testgroupinfolocal_fb,modifyTestSeqList,"1");

			
			
			
			//template related values
			testGroup_VO.setGroupCode(testgroupinfolocal_fb.getGroupCode());
			testGroup_VO.setLabCode(testgroupinfolocal_fb.getLabCode());
			testGroup_VO.setIsDynamicResult(testgroupinfolocal_fb.getIsDynamicResult());
			testGroup_VO.setIsDynamicTemplate(testgroupinfolocal_fb.getIsDynamicTemplate());
			testGroup_VO.setPrintingTemplateCode(testgroupinfolocal_fb.getPrintingTemplateCode());
			
			testGroup_VO.setHmode(testgroupinfolocal_fb.getHmode());
			
			if(testgroupinfolocal_fb.getGlobalTemplate()==null)
				testgroupinfolocal_fb.setGlobalTemplate("1");
			
			testGroup_VO.setGlobalTemplate(testgroupinfolocal_fb.getGlobalTemplate());
			testGroup_VO.setUserGroupCode(testgroupinfolocal_fb.getUserGroupCode());
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			
			
		//	if(testgroupinfolocal_fb.getHmode()!=null && testgroupinfolocal_fb.getHmode().equals("MODIFYSAVE"))
		//	{
				TestGroupInfoLocalMstDATA.modifyTemplateValueLocal(testGroup_VO, userVO);
				
		//	}
			
			
			
			
			
			
			/*			HelperMethods.populate(testgroupinfolocal_VO, testgroupinfolocal_fb);
*/			
				
			TestGroupInfoLocalMstDATA.saveTestGroupInfoLocal(insert_testgroupinfolocal_VO,delete_testgroupinfolocal_VO, userVO,modify_testgroupinfolocal_VO);
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


	public static boolean fetchCheckListTestGroupInfoLocal(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfolocal_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",groupCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = testgroupinfolocal_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			groupCode = concatid[0];
			labCode = concatid[1];
			hospitalCode=concatid[2];


			testgroupinfolocal_VO.setIsActive(testgroupinfolocal_fb.getIsActive());

		
			testgroupinfolocal_fb.setHospitalCode(hospitalCode); 
			testgroupinfolocal_VO.setHospitalCode(hospitalCode);
			testgroupinfolocal_fb.setGroupCode(groupCode); 
			testgroupinfolocal_VO.setGroupCode(groupCode);
			testgroupinfolocal_fb.setLabCode(labCode); 
			testgroupinfolocal_VO.setLabCode(labCode);
			
			testgroupinfolocal_VO.setHmode(testgroupinfolocal_fb.getHmode());

			testgroupinfolocal_fb.setSelectedChk(testgroupinfolocal_fb.getChk()[0]);


			mp.putAll(TestGroupInfoLocalMstDATA.fetchTestGroupInfoLocal(testgroupinfolocal_VO, userVO));
			mp.putAll(TestGroupInfoLocalMstDATA.getTestLocal(testgroupinfolocal_VO, userVO));
			WebUTIL.setMapInSession(mp, _request);

		
			testgroupinfolocal_fb.setHospitalCode(hospitalCode); 
			testgroupinfolocal_VO.setHospitalCode(hospitalCode);
			testgroupinfolocal_fb.setGroupCode(groupCode); 
			testgroupinfolocal_VO.setGroupCode(groupCode);
			testgroupinfolocal_fb.setLabCode(labCode); 
			testgroupinfolocal_VO.setLabCode(labCode);
			
			testgroupinfolocal_fb.setIsDynamicResult(testgroupinfolocal_VO.getIsDynamicResult());
			testgroupinfolocal_fb.setIsDynamicTemplate(testgroupinfolocal_VO.getIsDynamicTemplate());
			testgroupinfolocal_fb.setPrintingTemplateCode(testgroupinfolocal_VO.getPrintingTemplateCode());
			
			testgroupinfolocal_fb.setUserGroupCode(testgroupinfolocal_VO.getUserGroupCode());
			
			HelperMethods.populate(testgroupinfolocal_fb, testgroupinfolocal_VO);

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


	public static boolean fetchTestGroupInfoLocal(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfolocal_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			
			
			
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=TestGroupInfoLocalMstDATA.fetchTestGroupInfoLocal(testgroupinfolocal_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(testgroupinfolocal_fb, testgroupinfolocal_VO);
			
			
			
	
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


	public static boolean reFetchCheckListTestGroupInfoLocal(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfolocal_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",groupCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = testgroupinfolocal_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			groupCode = concatid[0];
			labCode = concatid[1];
			hospitalCode=concatid[2];


			testgroupinfolocal_VO.setIsActive(testgroupinfolocal_fb.getIsActive());

		
			testgroupinfolocal_fb.setHospitalCode(hospitalCode); 
			testgroupinfolocal_VO.setHospitalCode(hospitalCode);
			testgroupinfolocal_fb.setGroupCode(groupCode); 
			testgroupinfolocal_VO.setGroupCode(groupCode);
			testgroupinfolocal_fb.setLabCode(labCode); 
			testgroupinfolocal_VO.setLabCode(labCode);
			testgroupinfolocal_VO.setHmode(testgroupinfolocal_fb.getHmode());
			testgroupinfolocal_VO.setCheckLocal("1");
			mp.putAll(TestGroupInfoLocalMstDATA.fetchTestGroupInfoLocal(testgroupinfolocal_VO, userVO));
			mp.putAll(TestGroupInfoMstDATA.getTest(testgroupinfolocal_VO, userVO));


/*			mp=TestGroupInfoLocalMstDATA.fetchCheckListTestGroupInfoLocal(testgroupinfolocal_VO, userVO);
*/			WebUTIL.setMapInSession(mp, _request);

		
			testgroupinfolocal_fb.setHospitalCode(hospitalCode); 
			testgroupinfolocal_VO.setHospitalCode(hospitalCode);
			testgroupinfolocal_fb.setGroupCode(groupCode); 
			testgroupinfolocal_VO.setGroupCode(groupCode);
			testgroupinfolocal_fb.setLabCode(labCode); 
			testgroupinfolocal_VO.setLabCode(labCode);

			testgroupinfolocal_fb.setIsDynamicResult(testgroupinfolocal_VO.getIsDynamicResult());
			testgroupinfolocal_fb.setIsDynamicTemplate(testgroupinfolocal_VO.getIsDynamicTemplate());
			testgroupinfolocal_fb.setPrintingTemplateCode(testgroupinfolocal_VO.getPrintingTemplateCode());


			HelperMethods.populate(testgroupinfolocal_fb, testgroupinfolocal_VO);

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

	public static boolean getTestLocal(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupInfoMasterVO testgroupinfolocal_VO = new TestGroupInfoMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			testgroupinfolocal_VO.setGroupCode(testgroupinfolocal_fb.getGroupCode());
			testgroupinfolocal_VO.setLabCode(testgroupinfolocal_fb.getLabCode());

			Map mp=new HashMap(); 


			mp=TestGroupInfoLocalMstDATA.getTestLocal(testgroupinfolocal_VO, userVO);
		
			//mp=TestGroupInfoLocalMstDATA.getTemplateLocal(testgroupinfolocal_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(testgroupinfolocal_fb, testgroupinfolocal_VO);
			testgroupinfolocal_fb.setIsDynamicTemplate(testgroupinfolocal_VO.getIsDynamicTemplate()==null?"0":testgroupinfolocal_VO.getIsDynamicTemplate());
			testgroupinfolocal_fb.setIsDynamicResult(testgroupinfolocal_VO.getIsDynamicResult()==null?"0":testgroupinfolocal_VO.getIsDynamicResult());
			testgroupinfolocal_fb.setPrintingTemplateCode(testgroupinfolocal_VO.getPrintingTemplateCode()==null?"-1":testgroupinfolocal_VO.getPrintingTemplateCode());
			testgroupinfolocal_fb.setGlobalTemplate(testgroupinfolocal_VO.getGlobalTemplate()==null?"1":testgroupinfolocal_VO.getGlobalTemplate());

			
			
			objStatus.add(Status.NEW);
		}
		/*catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "NO MORE TESTS EXISTS");
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

	
	public static List getOldMappedList(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
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
	
	
	public static List getNewMappedList(TestGroupInfoLocalMstFB testgroupinfolocal_fb, HttpServletRequest _request)
	{
				List newMappedList=new ArrayList();
		
		try
		{
			if(testgroupinfolocal_fb.getMappedList()!=null && testgroupinfolocal_fb.getMappedList().length!=0){
				
				
			for(int i=0; i <  testgroupinfolocal_fb.getMappedList().length; i++){
				
				newMappedList.add(testgroupinfolocal_fb.getMappedList()[i]);
					
				
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

	
	public static TestGroupInfoMasterVO[] getTestGroupInfoLocalVO(List documentList,TestGroupInfoLocalMstFB testgroupinfolocal_fb,List testSeqList, String insDel)
	{
		TestGroupInfoMasterVO[] testgroupinfolocal_VO=null;
		
		
		try
		{
			if(documentList!=null && documentList.size()!=0){
				testgroupinfolocal_VO=new TestGroupInfoMasterVO[documentList.size()];
				
			for(int i=0; i < testgroupinfolocal_VO.length; i++){
				testgroupinfolocal_VO[i]=new TestGroupInfoMasterVO();
				testgroupinfolocal_VO[i].setTestCode((String)documentList.get(i));
				testgroupinfolocal_VO[i].setLabCode(testgroupinfolocal_fb.getLabCode());
				testgroupinfolocal_VO[i].setGroupCode(testgroupinfolocal_fb.getGroupCode());
				testgroupinfolocal_VO[i].setRemarks(testgroupinfolocal_fb.getRemarks());
				testgroupinfolocal_VO[i].setIsDynamicTemplate(testgroupinfolocal_fb.getIsDynamicTemplate());
				testgroupinfolocal_VO[i].setIsDynamicResult(testgroupinfolocal_fb.getIsDynamicResult());
				testgroupinfolocal_VO[i].setPrintingTemplateCode(testgroupinfolocal_fb.getPrintingTemplateCode());
				testgroupinfolocal_VO[i].setUserGroupCode(testgroupinfolocal_fb.getUserGroupCode());

				if(insDel.equals("1"))
					testgroupinfolocal_VO[i].setTestSeqNo((String)testSeqList.get(i)==null?"":(String)testSeqList.get(i));
				}
				}
		}		
		catch (Exception e)
		{
		e.printStackTrace();
			
		}
return testgroupinfolocal_VO;
		
}
	
	
	
	
	
	
	
}


