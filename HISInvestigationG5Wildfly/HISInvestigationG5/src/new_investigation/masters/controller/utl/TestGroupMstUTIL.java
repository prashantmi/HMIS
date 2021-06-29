package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hisglobal.masterutil.MasterInterface;
import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.TestGroupMstDATA;
import new_investigation.masters.controller.fb.TestGroupMstFB;
import new_investigation.vo.TestGroupMasterVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

 
public class TestGroupMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Group Name", "Group Type"};
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
				//+ "#2^InActive";
		return comboQuery;
	}
	
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTGROUP.HIVT_TEST_GROUP_MST").replace("?",seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTGROUP.HIVT_TEST_GROUP_MST.COND.0"));

		return deleteQuery;
	}
	
	
	public String getJsFiles() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	
	public String getMainQuery(String[] cmb)
	{
		
		StringBuffer brMainQuery = new StringBuffer();
		
		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(Config.SUPER_USER_HOSPITAL_CODE);
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"testgroupMst.main.0"));
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
		String masterName = "Global Test Group Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "HGSTR_GROUP_NAME" };
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
		String strSearchField[] = { "1", " UPPER(hgstr_group_name) " };
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Group Name");
		viewHdr.add("D");
		viewHdr.add("Group Type");
		viewHdr.add("D");
		viewHdr.add("remarks");
		viewHdr.add("D");
		
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.testgroupMst.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	
	public static boolean saveTestGroup(TestGroupMstFB testgroup_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TestGroupMasterVO testgroupmaster_VO = new TestGroupMasterVO();
			HelperMethods.populate(testgroupmaster_VO, testgroup_fb);
		
			TestGroupMstDATA.saveTestGroup(testgroupmaster_VO, userVO);
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
	
	
	
	public static boolean modifyTestGroup(TestGroupMstFB testgroup_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupMasterVO testgroupmaster_VO = new TestGroupMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			Map mp=new HashMap();
             String chk="",groupCde="",hospCde="";
             String[] concatid={};
             
              // Fetching Selected Record Primary Key
             chk = testgroup_fb.getChk()[0].replace("$", "@");  
             concatid = chk.split("@");
             groupCde = concatid[0];
             hospCde = concatid[1];
             	
             testgroupmaster_VO.setIsActive(testgroup_fb.getIsActive());
             
             testgroup_fb.setGroupCode(groupCde);
             testgroupmaster_VO.setGroupCode(groupCde);
             testgroup_fb.setHospitalCode(hospCde);
             testgroupmaster_VO.setHospitalCode(hospCde); 
                     
			testgroup_fb.setSelectedChk(testgroup_fb.getChk()[0]);

			TestGroupMstDATA.fetchTestGroup(testgroupmaster_VO, userVO);
			  
            testgroup_fb.setGroupCode(groupCde);
            testgroupmaster_VO.setGroupCode(groupCde);
            testgroup_fb.setHospitalCode(hospCde);
            testgroupmaster_VO.setHospitalCode(hospCde);
           	
			HelperMethods.populate(testgroup_fb, testgroupmaster_VO);
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

	
	public static boolean fetchTestGroup(TestGroupMstFB testgroup_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupMasterVO testgroupmaster_VO = new TestGroupMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
				
			testgroupmaster_VO.setGroupCode(testgroup_fb.getChk()[0].split("$")[0]);

			TestGroupMstDATA.fetchTestGroup(testgroupmaster_VO, userVO);
			HelperMethods.populate(testgroup_fb, testgroupmaster_VO);

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

	public static boolean updateTestGroup(TestGroupMstFB testgroup_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
		
			TestGroupMasterVO testgroupmaster_VO = new TestGroupMasterVO();
			testgroupmaster_VO.setGroupCode(testgroup_fb.getGroupCode());

			 HelperMethods.populate(testgroupmaster_VO, testgroup_fb);
		
			TestGroupMstDATA.updateTestGroup(testgroupmaster_VO, userVO);
			
			objStatus.add(Status.DONE, "Data Modified Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{  hasFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(),"");
		}
		catch (HisRecordNotFoundException e)
		{  hasFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(),"");
		}
		catch (HisDataAccessException e)
		{   hasFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   hasFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{   hasFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return hasFlag;
	}
	
	
	public static boolean clearTestGroup(TestGroupMstFB testgroup_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestGroupMasterVO testgroupmaster_VO = new TestGroupMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			   String chk="",groupCde="",hospCde="";
	             String[] concatid={};
	             
						
			chk = testgroup_fb.getSelectedChk().replace("$", "@");  
            concatid = chk.split("@");
            groupCde = concatid[0];
            hospCde = concatid[1];
            
            testgroup_fb.setGroupCode(groupCde);
			testgroupmaster_VO.setGroupCode(groupCde);
			testgroup_fb.setHospitalCode(hospCde);
            testgroupmaster_VO.setHospitalCode(hospCde);
            
			TestGroupMstDATA.fetchTestGroup(testgroupmaster_VO, userVO);
			
				
			HelperMethods.populate(testgroup_fb, testgroupmaster_VO);
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

}
