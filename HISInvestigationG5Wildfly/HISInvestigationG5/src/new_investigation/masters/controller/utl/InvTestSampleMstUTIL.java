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
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.InvTestSampleMstDATA;
import new_investigation.masters.controller.fb.InvTestSampleMstFB;
import new_investigation.vo.InvTestSampleMasterVO;

public class InvTestSampleMstUTIL implements MasterInterface
{
	private static final long serialVersionUID = 02L;

	HttpSession httpSession = null;
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
		String[] columnHdr = { "Test","Sample","Is Default" };
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "0", "Test Name" , "1", "Record Status" };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[2];
		comboQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"SELECT.TEST_COMBO.HIVT_TEST_SAMPLE_MST");
		comboQuery[1] = "1^Active";
		//#2^InActive";

		return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTSAMPLE.HIVT_TEST_SAMPLE_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTSAMPLE.HIVT_TEST_SAMPLE_MST.COND.0"));

		return deleteQuery;
	}


	public String getJsFiles() 
	{
	String files="../../new_investigation/js/ListCombo.js";
		return files;
}


	public String getMainQuery(String[] cmb)
	{

		StringBuffer brMainQuery = new StringBuffer();

		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"testsampleMst.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);

		//////////////////////////////////////////////////////////////////////
		

		if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.append(" AND "
						+ new_investigation.qryHandler_investigation.getQuery(1,
						"testsampleMst.cond.0")
						.replace("?", "1"));
				
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("testCode", cmb[0]);

			}
		
			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);
			}
		}

		
		System.out.println("Main Query;;;;;;;;;"+brMainQuery.toString());
		return brMainQuery.toString();
	}


	public String getMasterName()
	{
		String masterName = "Test Sample Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "T.GSTR_TEST_NAME" };
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
		String strSearchField[] = { "1", " UPPER(T.gstr_test_name) "};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test");
		viewHdr.add("D");


		viewHdr.add("Sample");
		viewHdr.add("D");

		viewHdr.add("Sample Quantity");
		viewHdr.add("D");


		viewHdr.add("Unit of Measurement Name");
		viewHdr.add("D");

		viewHdr.add("Container Name");
		viewHdr.add("D");

		viewHdr.add("Is Default");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.testsample.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveInvTestSample(InvTestSampleMstFB testsample_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			InvTestSampleMasterVO testsample_VO = new InvTestSampleMasterVO();
			HelperMethods.populate(testsample_VO, testsample_fb);

			InvTestSampleMstDATA.saveInvTestSample(testsample_VO, userVO);
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


	public static boolean fetchCheckListInvTestSample(InvTestSampleMstFB testsample_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvTestSampleMasterVO testsample_VO = new InvTestSampleMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",testCode="",sampleCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = testsample_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			testCode = concatid[0];
			sampleCode = concatid[1];
		


			testsample_VO.setIsActive(testsample_fb.getIsActive());

			testsample_fb.setTestCode(testCode); 
			testsample_VO.setTestCode(testCode);
			testsample_fb.setSampleCode(sampleCode); 
			testsample_VO.setSampleCode(sampleCode);


			testsample_fb.setSelectedChk(testsample_fb.getChk()[0]);

			mp=InvTestSampleMstDATA.fetchCheckListInvTestSample(testsample_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			testsample_fb.setTestCode(testCode); 
			testsample_VO.setTestCode(testCode);
			testsample_fb.setSampleCode(sampleCode); 
			testsample_VO.setSampleCode(sampleCode);
	

			HelperMethods.populate(testsample_fb, testsample_VO);
			if(testsample_fb.getDefaultSample().equals("1"))
				testsample_fb.setDefaultTrue("1");
			
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


	public static boolean fetchInvTestSample(InvTestSampleMstFB testsample_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvTestSampleMasterVO testsample_VO = new InvTestSampleMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			String[] testCombo=_request.getParameterValues("combo");
			String testCode=testCombo[0];
			testsample_VO.setTestCode(testCode);
			mp=InvTestSampleMstDATA.fetchInvTestSample(testsample_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(testsample_fb, testsample_VO);

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


	public static boolean savemodifyInvTestSample(InvTestSampleMstFB testsample_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			InvTestSampleMasterVO testsample_VO = new InvTestSampleMasterVO();

			//		testsample_VO.setCollectionareaCode(testsample_fb.getCollectionareaCode());
			HelperMethods.populate(testsample_VO, testsample_fb);

			InvTestSampleMstDATA.savemodifyInvTestSample(testsample_VO, userVO);

			objStatus.add(Status.DONE, "Data Modified Successfully", "");
			//objStatus.add(Status.TRANSINPROCESS);
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

	public static boolean reFetchCheckListInvTestSample(InvTestSampleMstFB testsample_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvTestSampleMasterVO testsample_VO = new InvTestSampleMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",testCode="",sampleCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = testsample_fb.getSelectedChk().replace("$", "@");  
			concatid = chk.split("@");
			testCode = concatid[0];
			sampleCode = concatid[1];
	



			testsample_VO.setIsActive(testsample_fb.getIsActive());

			testsample_fb.setTestCode(testCode); 
			testsample_VO.setTestCode(testCode);
			testsample_fb.setSampleCode(sampleCode); 
			testsample_VO.setSampleCode(sampleCode);
		




			mp=InvTestSampleMstDATA.fetchCheckListInvTestSample(testsample_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			testsample_fb.setTestCode(testCode); 
			testsample_VO.setTestCode(testCode);
			testsample_fb.setSampleCode(sampleCode); 
			testsample_VO.setSampleCode(sampleCode);
	

			HelperMethods.populate(testsample_fb, testsample_VO);

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


