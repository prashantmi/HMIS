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
import new_investigation.vo.MachineTestParameterMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.MachineTestParameterMstDATA;
import new_investigation.masters.controller.fb.MachineTestParameterMstFB;

public class MachineTestParameterMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Machine Name","Test Name","Test Parameter Name", "Machine Test Parameter Code"};
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
		//2^InActive";
		return comboQuery;
	}

	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.MACHINETESTPARA.HMIT_MACHINE_TESTPARA_MST").replace("?",seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.MACHINETESTPARA.HMIT_MACHINE_TESTPARA_MST.COND.0"));

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
		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();
		List<String> list = new ArrayList<String>();
		list.add(hosCode);

		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"MACHINE_TEST_PARAMETER_MST.MAIN.0"));
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
		String masterName = "Machine Test Parameter Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "M.hmistr_machine_name","2","T.gstr_test_name","3","P.hgstr_parameter","4","MTP.hmistr_machine_test_code" };
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
		String strSearchField[] = { "1","UPPER(hmistr_machine_name)","2", " UPPER(gstr_test_name) " };
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Machine Name");
		viewHdr.add("D");
		viewHdr.add("Test Name");
		viewHdr.add("D");
		viewHdr.add("Test Parameter Name");
		viewHdr.add("D");
		viewHdr.add("Machine Test Parameter Name");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "SELECT.MACHINETESTPARAMST.VIEW");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}



	public static boolean saveDetails(MachineTestParameterMstFB machineTestPara_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		List<MachineTestParameterMasterVO> machineTestParamMst_listVO=new ArrayList<MachineTestParameterMasterVO>();


		try
		{
		int size= machineTestPara_fb.getMachineParameterCode().length;
		
		for(int i=0;i<size;i++)
		{
			MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();
			
			machineTestParaMstVO.setMachineCode(machineTestPara_fb.getMachineCode());
			machineTestParaMstVO.setTestCode(machineTestPara_fb.getTestCode());
			machineTestParaMstVO.setParameterCode(machineTestPara_fb.getParameterCode()[i]);
			machineTestParaMstVO.setMachineParameterCode(machineTestPara_fb.getMachineParameterCode()[i]);
			machineTestParaMstVO.setMachineParameterName(machineTestPara_fb.getMachineParameterName()[i]);
			
			if(machineTestParaMstVO.getMachineParameterCode()!=null && machineTestParaMstVO.getMachineParameterCode().equals("")==false)
			machineTestParamMst_listVO.add(machineTestParaMstVO);
		
		
		}
		MachineTestParameterMstDATA.saveDetails(machineTestParamMst_listVO, userVO);
		
		
		WebUTIL.setAttributeInSession(_request, InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM, null);
		WebUTIL.setAttributeInSession(_request, InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM_ADDED,null);
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
	
	
	
	public static boolean displayDetails(MachineTestParameterMstFB machineTestPara_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			
			//int size= machineTestPara_fb.getMachineParameterCode().length;
			
			//for(int i=0;i<size;i++)
			//{
				MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();

			// Fetching Selected Record Primary Key
						//String parameterCode = concatid[1];
			//String sequenceCode = concatid[2];
			machineTestParaMstVO.setMachineCode(machineTestPara_fb.getMachineCode());
			machineTestParaMstVO.setTestCode(machineTestPara_fb.getTestCode());
			/*machineTestPara_fb.setMachineCode(machineCode);
			

			machineTestPara_fb.setTestCode(testCode); 
*/
			//testparacombo_fb.setParameterCode(parameterCode); 
			//testparacombo_VO.setParameterCode(parameterCode);

			//testparacombo_fb.setTestparaSeq(sequenceCode); 
			//testparacombo_VO.setTestparaSeq(sequenceCode);
			//testparacombo_fb.setSelectedChk(testparacombo_fb.getChk()[0]);



			mp=MachineTestParameterMstDATA.displayDetails(machineTestParaMstVO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);



					

			

			machineTestPara_fb.setMachineCode(machineTestParaMstVO.getMachineCode());
			machineTestPara_fb.setTestCode(machineTestParaMstVO.getTestCode());
			
			//machineTestParaMstVO.setParameterName(machineTestPara_fb.getParameterName()[i]);
			//machineTestParaMstVO.setMachineParameterCode(machineTestPara_fb.getMachineParameterCode()[i]);
			//machineTestParaMstVO.setMachineParameterName(machineTestPara_fb.getMachineParameterName()[i]);
			
			//}
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
	



	public static boolean fetchModifyDetails(MachineTestParameterMstFB machineTestPara_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			
			//int size= machineTestPara_fb.getMachineParameterCode().length;
			
			//for(int i=0;i<size;i++)
			//{
				MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();

			// Fetching Selected Record Primary Key
			String chk = machineTestPara_fb.getChk()[0].replace("$", "@");  //^
			String[] concatid = chk.split("@");
			String machineCode=concatid[0];
			String testCode = concatid[1];
			//String parameterCode = concatid[1];
			//String sequenceCode = concatid[2];
			machineTestParaMstVO.setMachineCode(machineCode);
			machineTestParaMstVO.setTestCode(testCode);
			machineTestPara_fb.setSelectedChk(machineTestPara_fb.getChk()[0]);
			/*machineTestPara_fb.setMachineCode(machineCode);
			

			machineTestPara_fb.setTestCode(testCode); 
*/
			//testparacombo_fb.setParameterCode(parameterCode); 
			//testparacombo_VO.setParameterCode(parameterCode);

			//testparacombo_fb.setTestparaSeq(sequenceCode); 
			//testparacombo_VO.setTestparaSeq(sequenceCode);
			//testparacombo_fb.setSelectedChk(testparacombo_fb.getChk()[0]);



			mp=MachineTestParameterMstDATA.fetchModifyDetails(machineTestParaMstVO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);



					

			

			machineTestPara_fb.setMachineCode(machineCode);
			machineTestPara_fb.setTestCode(testCode);
			
			//machineTestParaMstVO.setParameterName(machineTestPara_fb.getParameterName()[i]);
			//machineTestParaMstVO.setMachineParameterCode(machineTestPara_fb.getMachineParameterCode()[i]);
			//machineTestParaMstVO.setMachineParameterName(machineTestPara_fb.getMachineParameterName()[i]);
			
			//}
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


	public static boolean fetchParaMachineCombo(MachineTestParameterMstFB machineTestParaCombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			mp=MachineTestParameterMstDATA.fetchParaMachineCombo(machineTestParaMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(machineTestParaCombo_fb, machineTestParaMstVO);

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

	public static boolean fetchParaTestCombo(MachineTestParameterMstFB machineTestParaCombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			mp=MachineTestParameterMstDATA.fetchParaTestCombo(machineTestParaMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(machineTestParaCombo_fb, machineTestParaMstVO);

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


	public static boolean savemodifyMachineTestParaCombo(MachineTestParameterMstFB machineTestPara_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			List<MachineTestParameterMasterVO> machineTestParamMst_listVO=new ArrayList<MachineTestParameterMasterVO>();

			int size= machineTestPara_fb.getMachineParameterCode().length;
			
			for(int i=0;i<size;i++)
			{
				MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();
				
				machineTestParaMstVO.setMachineCode(machineTestPara_fb.getMachineCode());
				machineTestParaMstVO.setTestCode(machineTestPara_fb.getTestCode());
				machineTestParaMstVO.setParameterCode(machineTestPara_fb.getParameterCode()[i]);
				machineTestParaMstVO.setMachineParameterCode(machineTestPara_fb.getMachineParameterCode()[i]);
				machineTestParaMstVO.setMachineParameterName(machineTestPara_fb.getMachineParameterName()[i]);
				
				machineTestParamMst_listVO.add(machineTestParaMstVO);
			}
			
			/*testparacombo_VO.setParameterCode(_fb.getParameterCode());
			testparacombo_VO.setTestParameterValue(testparacombo_fb.getTestParameterValue());
			testparacombo_VO.setTestCode(testparacombo_fb.getTestCode());
			testparacombo_VO.setTestparaSeq(testparacombo_fb.getTestparaSeq());*/

			/////////////////////////////////////////////////////////////


			MachineTestParameterMstDATA.savemodifymachineTestParaCombo(machineTestParamMst_listVO, userVO);

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

	public static boolean displayParameterDetails(MachineTestParameterMstFB parameterMachineCombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		List<MachineTestParameterMasterVO> machineTestParamMst_listVO=new ArrayList<MachineTestParameterMasterVO>();

		MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();

		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			//machineTestParaMstVO.setParameterName(parameterMachineCombo_fb.getParameterName());
			//machineTestParaMstVO.setParameterCode(parameterMachineCombo_fb.getParameterCode());
			machineTestParaMstVO.setTestCode(parameterMachineCombo_fb.getTestCode());
			machineTestParaMstVO.setMachineCode(parameterMachineCombo_fb.getMachineCode());
			machineTestParamMst_listVO=MachineTestParameterMstDATA.displayParameterDetails(machineTestParaMstVO, userVO);

			WebUTIL.setAttributeInSession(_request, InvestigationConfig.DISPLAY_DATA_MACHINE_TEST_PARAM, machineTestParamMst_listVO);


			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found. Add new records!!");
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
	

	public static boolean reFetchCheckMachineListTestParaCombo(MachineTestParameterMstFB machineTestPara_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		//MachineTestParameterMasterVO testparacombo_VO = new MachineTestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();

			String chk="";
			chk = machineTestPara_fb.getSelectedChk().replace("$", "@");
			String[] concatid = chk.split("@");
			String machineCode=concatid[0];
			String testCode = concatid[1];
			MachineTestParameterMasterVO machineTestParaMstVO = new MachineTestParameterMasterVO();

			machineTestParaMstVO.setMachineCode(machineCode);
			machineTestParaMstVO.setTestCode(testCode);
			


			/*testparacombo_fb.setTestCode(testCode); 
			testparacombo_VO.setTestCode(testCode);

			testparacombo_fb.setParameterCode(parameterCode); 
			testparacombo_VO.setParameterCode(parameterCode);

			testparacombo_fb.setTestparaSeq(sequenceCode); 
			testparacombo_VO.setTestparaSeq(sequenceCode);*/



			mp=MachineTestParameterMstDATA.fetchModifyDetails(machineTestParaMstVO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);



			////////////////////////////////////////////////////////////////////////////////////////////////////////////			

			/*testparacombo_fb.setTestParameterValue(testparacombo_VO.getTestparaValue());
			testparacombo_fb.setTestCode(testparacombo_VO.getTestCode());
			testparacombo_fb.setTestName(testparacombo_VO.getTestName());
			testparacombo_fb.setParameterCode(testparacombo_VO.getParameterCode());
			testparacombo_fb.setTestparaSeq(testparacombo_VO.getTestparaSeq());
*/
			////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
	/*
	public static boolean fetchParameterCombo(TestParaComboMstFB testparacombo_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session= _request.getSession();
		TestParaComboMasterVO testparacombo_VO = new TestParaComboMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			session.removeAttribute(InvestigationConfig.LIST_TESTPARA_COMBO);

			ControllerUTIL.setSysdate(_request);

			testparacombo_VO.setTestCode(testparacombo_fb.getTestCode());
			Map mp=new HashMap();

			mp=TestParaComboMstDATA.fetchParameterCombo(testparacombo_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);



			HelperMethods.populate(testparacombo_fb, testparacombo_VO);

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
*/
}


