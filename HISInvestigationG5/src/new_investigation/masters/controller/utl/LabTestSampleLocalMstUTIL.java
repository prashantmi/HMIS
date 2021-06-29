/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE LOCAL MASTER
 ## Purpose						        : This master is used for mapping the Sample to labs and test locally i.e. on hospital code 101
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LabTestSampleGlobalMstDATA;
import new_investigation.masters.controller.data.LabTestSampleLocalMstDATA;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.vo.LabTestSampleMstVO;
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

public class LabTestSampleLocalMstUTIL extends ControllerUTIL implements
		MasterInterface {
	
	HttpServletRequest httpRequest = null;
	HttpSession httpSession = null;

	//public static HttpServletRequest request = null;
	String labCode="";
	String testCode="";
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
		String[] columnHdr = {"Lab Name", "Test Name", "Sample Name","Is Default Sample"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "0", "Laboratory","0","Test","1", "Record Status"  };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[3];
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		comboQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,
		"SELECT.Lab.Lab_Test_Sample_Global_Mst").replace("?",hosCode );
		comboQuery[1] = new_investigation.qryHandler_investigation.getQuery(1,
				"Select.Test.Lab_Test_Sample_Global_Mst").replace("?",hosCode);
		comboQuery[2] = "1^Active";
		return comboQuery;
	}
	
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Lab_Test_Sample_Global_Mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Lab_Test_Sample_Global_Mst.COND"));

		return deleteQuery;
	}
	
	
	public String getJsFiles() 
	{
	String files="../../new_investigation/js/ListCombo.js";
		return files;
}
	
	 
	
	public String getMainQuery(String[] cmb)
	{
		
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		StringBuffer brMainQuery = new StringBuffer();
		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(hosCode);
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"LabTestSampleGlobalMst.Main"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.append(" AND "
						+ new_investigation.qryHandler_investigation.getQuery(1,
						"LabTestSampleGlobalMst.Main.Cond.0")
						.replace("?", "1"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("labCode", cmb[0]);
				if (!cmb[1].equals("0")) {
					brMainQuery.append(" AND "
							+ new_investigation.qryHandler_investigation.getQuery(1,
							"LabTestSampleGlobalMst.Main.Cond.1")
							.replace("?", "1"));
					
					brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
							.lastIndexOf("1") + 1, cmb[1]);
					httpSession.setAttribute("testCode", cmb[1]);

			}
		/*if (cmb != null) {
			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);
			}
		} */

		
	}
		System.out.println("Main Query;;;;;;;;;"+brMainQuery.toString());
		}
		return brMainQuery.toString();
	}
	

	public String getMasterName()
	{
		String masterName = "Lab Test Local Sample Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "a.gnum_lab_code","2","a.gnum_test_code","3","a.gnum_sample_code" };
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
		String strSearchField[] = { "1", " UPPER(b.gstr_lab_name)","2","UPPER(c.gstr_test_name)","3","UPPER(d.gstr_sample_name)" };
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Lab Name");
		viewHdr.add("D");

		viewHdr.add("Test Name");
		viewHdr.add("D");
		
		viewHdr.add("Sample Name");
		viewHdr.add("D");
		
		viewHdr.add("Sample Quantity");
		viewHdr.add("D");
		
		viewHdr.add("UOM Name");
		viewHdr.add("D");
		
		viewHdr.add("Container Name");
		viewHdr.add("D");
		
		viewHdr.add("Is Default Sample");
		viewHdr.add("D");

		 
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "VIEW.Lab_Test_Sample_Global_Mst");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveLabTestSampleGlobal(LabTestSampleGlobalMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabTestSampleMstVO labTestSampleMstVO = new LabTestSampleMstVO();
			
			ControllerUTIL.setSysdate(_request);
			HelperMethods.populate(labTestSampleMstVO, _fb);
		    
			LabTestSampleLocalMstDATA.saveLabTest(labTestSampleMstVO, userVO);
			objStatus.add(Status.DONE, "Lab Test Sample Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL,"Default Sample Already Exist","");
			System.out.println(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Already Exist");
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

	
	public static boolean fetchLabTestSampleEssential(LabTestSampleGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LabTestSampleMstVO labTestSampleMstVO = new LabTestSampleMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			String[] lab=_request.getParameterValues("combo");
			String labCode=lab[0];
			String testCode=lab[1];
			Map mp=new HashMap();
			labTestSampleMstVO.setLabCode(labCode);
			labTestSampleMstVO.setTestCode(testCode);
			mp=LabTestSampleLocalMstDATA.fetchLabTestSampleEssential(labTestSampleMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labTestSampleMstVO);
			
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

	public static boolean fetchGlobalData(LabTestSampleGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LabTestSampleMstVO labTestSampleMstVO = new LabTestSampleMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			labTestSampleMstVO.setLabCode(fb.getLabCode());
			labTestSampleMstVO.setTestCode(fb.getTestCode());
			labTestSampleMstVO.setSampleCode(fb.getSampleCode());
			mp=LabTestSampleLocalMstDATA.fetchGlobalData(labTestSampleMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labTestSampleMstVO);
			
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

	
	public static boolean saveModifyLabTestSample(LabTestSampleGlobalMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabTestSampleMstVO labTestSampleMstVO = new LabTestSampleMstVO();
		 
			labTestSampleMstVO.setLabCode(_fb.getLabCode());
			 HelperMethods.populate(labTestSampleMstVO, _fb);
			 
			 LabTestSampleLocalMstDATA.saveModifyLabTestSample(labTestSampleMstVO, userVO);
			
			objStatus.add(Status.DONE, "Data Modified Successfully", "");
			
		}
		catch (HisDuplicateRecordException e)
		{  hasFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"Default Sample Already Exist","");
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
	
	public static boolean fetchModifyLabTestGlobal(LabTestSampleGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabTestSampleMstVO labTestSampleMstVO = new LabTestSampleMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String labCode = concatid[0];
			String testCode = concatid[1];
			String sampleCode=concatid[2];
			fb.setLabCode(labCode);
			fb.setTestCode(testCode);
			fb.setSampleCode(sampleCode);
			labTestSampleMstVO.setLabCode(labCode);
			labTestSampleMstVO.setTestCode(testCode);
			labTestSampleMstVO.setSampleCode(sampleCode);
			mp=LabTestSampleLocalMstDATA.fetchModifyLabTestLocal(labTestSampleMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labTestSampleMstVO);
			  
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
	
	public static boolean reFetchCheckListMacro(LabTestSampleGlobalMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabTestSampleMstVO labTestSampleMstVO = new LabTestSampleMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);		
			
			labTestSampleMstVO.setLabCode(fb.getLabCode());
			labTestSampleMstVO.setTestCode(fb.getTestCode());
			labTestSampleMstVO.setSampleCode(fb.getSampleCode());
			LabTestSampleLocalMstDATA.fetchModifyLabTestLocal(labTestSampleMstVO, userVO);
			
				
			HelperMethods.populate(fb, labTestSampleMstVO);
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
