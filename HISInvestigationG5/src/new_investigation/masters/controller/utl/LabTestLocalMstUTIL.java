package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LabTestGlobalMstDATA;
import new_investigation.masters.controller.data.LabTestLocalMstDATA;
import new_investigation.masters.controller.fb.LabTestGlobalMstFB;
import new_investigation.vo.LabTestGlobalMstVO;
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
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class LabTestLocalMstUTIL extends ControllerUTIL implements
		MasterInterface {
	
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
		String[] columnHdr = { "Lab Name","Test Name"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "0", "Laboratory","1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[2];
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		comboQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,
		"SELECT.Lab_Tset_Global_Mst").replace("?",hosCode );
		comboQuery[1] = "1^Active";
		return comboQuery;
	}
	
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"Delete.Lab_Test_Global_mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Lab_Test_Global_mst.COND.0"));

		return deleteQuery;
	}
	
	
	public String getJsFiles() 
	{
	String files="../../new_investigation/js/ListCombo.js";
		return files;
}
	
	 
	
public String getMainQuery(String[] cmb)
{
	
	//UserVO uservo = super.getUserVO(httpRequest);
	StringBuffer brMainQuery = new StringBuffer();
	
	
	
	List<String> list = new ArrayList<String>();
	String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
	list.add(InvestigationConfig.IS_VALID_ACTIVE);
	list.add(hosCode);
	brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"LabTestGlobalMst.main.0"));
	brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

	if (cmb != null) {
		if (!cmb[0].equals("0")) {
			brMainQuery.append(" AND "
					+ new_investigation.qryHandler_investigation.getQuery(1,
					"select.labTestGlobalMst.cond.0")
					.replace("?", "1"));
			
			brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
					.lastIndexOf("1") + 1, cmb[0]);
			httpSession.setAttribute("labCode", cmb[0]);

		}

	if (cmb != null) {
		if (!cmb[1].equals("0")) {
			brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
					.lastIndexOf("1") + 1, cmb[1]);
		}
	}

	
}
	System.out.println("Main Query;;;;;;;;;"+brMainQuery.toString());
	return brMainQuery.toString();
}
	

	public String getMasterName()
	{
		String masterName = "Lab Test Local Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "a.gnum_test_code" };
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
		String strSearchField[] = { "1","UPPER(b.gstr_lab_name)","2", " UPPER(c.gstr_test_name) "};
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Lab Name");
		viewHdr.add("D");

		viewHdr.add("Test Name");
		viewHdr.add("D");
		
		//viewHdr.add("Test Days");
		//viewHdr.add("D");
		
		viewHdr.add("Test Code");
		viewHdr.add("D");
		
		viewHdr.add("No OF Test");
		viewHdr.add("D");
		 
		viewHdr.add("Is Test Available");
		viewHdr.add("D");
		
		viewHdr.add("Is Appointment");
		viewHdr.add("D");
		
		viewHdr.add("Is MultiSession");
		viewHdr.add("D");
		
		viewHdr.add("Is Mandatory");
		viewHdr.add("D");
		
		viewHdr.add("Is Requisition Form Needed");
		viewHdr.add("D");
		
		viewHdr.add("Is Sample Form Needed");
		viewHdr.add("D");
		
		viewHdr.add("Gender");
		viewHdr.add("D");
		
		viewHdr.add("is Secure Printing");
		viewHdr.add("D");
		
		viewHdr.add("Is Grossing Required");
		viewHdr.add("D");
		
		viewHdr.add("Is Film Required");
		viewHdr.add("D");
		
		viewHdr.add("Is Consent");
		viewHdr.add("D");
		
		viewHdr.add("Test Method");
		viewHdr.add("D");
		
		viewHdr.add("Is Confidential");
		viewHdr.add("D");
		
		viewHdr.add("Test Printing Type ");
		viewHdr.add("D");
		
		viewHdr.add("Report Available After");
		viewHdr.add("D");
		
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "VIEW.Lab_Test_Local_Mst");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveLabTestLocal(LabTestGlobalMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabTestGlobalMstVO labTestGlobalMstVO = new LabTestGlobalMstVO();
			
			ControllerUTIL.setSysdate(_request);
			HelperMethods.populate(labTestGlobalMstVO, _fb);
		    
			LabTestLocalMstDATA.saveLabTest(labTestGlobalMstVO, userVO);
			objStatus.add(Status.DONE, "Lab Test Data Saved Successfully", "");
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

	
	public static boolean fetchLabTest(LabTestGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		List lstMachine = new ArrayList();
		LabTestGlobalMstVO labTestMasterVO = new LabTestGlobalMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			String[] lab=_request.getParameterValues("combo");
			String labCode=lab[0];
			Map mp=new HashMap();
			labTestMasterVO.setLabCode(labCode);
			mp=LabTestLocalMstDATA.fetchLabTest(labTestMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			lstTest = LabTestLocalMstDATA.getTestBylabCode(labTestMasterVO, getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,InvestigationConfig.LIST_TEST_COMBO, lstTest);
			
			lstMachine = LabTestLocalMstDATA.getMachineLocal(labTestMasterVO, getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,InvestigationConfig.LIST_MACHINE_COMBO, lstMachine);

			
			HelperMethods.populate(fb, labTestMasterVO);
			
			//fb.setHmode("ADD");
			
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

	public static boolean fetchLabTestGlobalData(LabTestGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		List lstMachine = new ArrayList();

		LabTestGlobalMstVO labTestMasterVO = new LabTestGlobalMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			labTestMasterVO.setLabCode(fb.getLabCode());
			labTestMasterVO.setTestCode(fb.getTestCode());
			mp = LabTestLocalMstDATA.fetchLabTestGlobalData(labTestMasterVO, getUserVO(_request));
			WebUTIL.setMapInRequest(mp, _request);
			lstTest = LabTestLocalMstDATA.getTestBylabCode(labTestMasterVO, getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,InvestigationConfig.LIST_TEST_COMBO, lstTest);
			
			lstMachine = LabTestLocalMstDATA.getMachineLocal(labTestMasterVO, getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,InvestigationConfig.LIST_MACHINE_COMBO, lstMachine);
			
			
			HelperMethods.populate(fb, labTestMasterVO);
			
			//fb.setHmode("ADD");
			
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

	
	public static boolean savemodifyLabTest(LabTestGlobalMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabTestGlobalMstVO labTestGlobalMstVO = new LabTestGlobalMstVO();
		 
			labTestGlobalMstVO.setLabCode(_fb.getLabCode());
			 HelperMethods.populate(labTestGlobalMstVO, _fb);
			 
			 LabTestLocalMstDATA.savemodifyLabTest(labTestGlobalMstVO, userVO);
			
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
	
	public static boolean fetchModifyLabTestLocal(LabTestGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstMachine=new ArrayList();
		LabTestGlobalMstVO labTestGlobalMstVO = new LabTestGlobalMstVO();
		
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String labCode = concatid[0];
			String testCode = concatid[1];
			fb.setLabCode(labCode);
			fb.setTestCode(testCode);
			labTestGlobalMstVO.setLabCode(labCode);
			labTestGlobalMstVO.setTestCode(testCode);
			mp=LabTestLocalMstDATA.fetchModifyLabTestGlobal(labTestGlobalMstVO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);
			lstMachine = LabTestLocalMstDATA.getMachineLocal(labTestGlobalMstVO, getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,InvestigationConfig.LIST_MACHINE_COMBO, lstMachine);
			
			HelperMethods.populate(fb, labTestGlobalMstVO);
			  
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
	
	public static StringBuffer getTestByLabCode(LabTestGlobalMstFB objFB, HttpServletRequest request, String labCode)
	{
		LabTestGlobalMstVO labTestMasterVO = new LabTestGlobalMstVO();
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		boolean flag = true;
		try
		{
			System.out.println("labCode :"+labCode);
			//String labCode1 = labCode.substring(0, labCode.indexOf("^"));
			labTestMasterVO.setLabCode(labCode);
			objFB.setTestCode("0");
			lstTest = LabTestLocalMstDATA.getTestBylabCode(labTestMasterVO, getUserVO(request));
			WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_TEST_COMBO, lstTest);
			System.out.println("lstTest 0 :"+lstTest.get(0));
			if(lstTest!=null && lstTest.size()>0)
			{
				sbAjaxRes.append("[");
				for(int i=0; i<lstTest.size(); i++)
				{
					String room=lstTest.get(i).toString();
					String [] testDetail= room.split(",");
					String testId= testDetail[0].substring(1, testDetail[0].length());
					String testName= testDetail[1].substring(0, (testDetail[1].length()-1));
					System.out.println("Test id :"+testId+"  TestName :"+testName);
					sbAjaxRes.append("{"); 
					sbAjaxRes.append("testName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(testId);sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("testCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(testName);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
				}
				if(lstTest.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_TEST_COMBO, lstTest);
			//objFB.setShowRommList("0");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
		return sbAjaxRes;
	}

	public static boolean reFetchCheckListMacro(LabTestGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabTestGlobalMstVO labTestGlobalMstVO = new LabTestGlobalMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);		
			
			labTestGlobalMstVO.setLabCode(fb.getLabCode());
			labTestGlobalMstVO.setTestCode(fb.getTestCode());
			LabTestLocalMstDATA.fetchModifyLabTestGlobal(labTestGlobalMstVO, userVO);
			
				
			HelperMethods.populate(fb, labTestGlobalMstVO);
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
