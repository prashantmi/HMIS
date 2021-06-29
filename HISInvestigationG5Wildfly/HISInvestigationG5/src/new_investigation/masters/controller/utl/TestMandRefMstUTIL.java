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
import new_investigation.vo.TestMandRefMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.TestMandRefMstDATA;
import new_investigation.masters.controller.fb.TestMandRefMstFB;

public class TestMandRefMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Test Name","Test Parameter Name"};
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTMANDREF.HIVT_TEST_MAND_REF_VAL_MST").replace("?",seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TESTMANDREF.HIVT_TEST_MAND_REF_VAL_MST.COND.0"));

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
		list.add(InvestigationConfig.HOSPITAL_CODE);

		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"testmandrefMst.main.0"));
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
		String masterName = "Test Mandatory Reference Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "data","2","P.hgstr_parameter" };
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
		String strSearchField[] = { "1", " UPPER(gstr_test_name) " };
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test Name");
		viewHdr.add("D");
		viewHdr.add("Test Parameter Name");
		viewHdr.add("D");
		
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.testmandref.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}



	public static boolean saveTestMandRef(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;

		try
		{
		
			
			int rowvalue;
/*
		if(testmandref_fb.getNumberOfRow()=="")
		{	rowvalue=1;	}
		else
		{	rowvalue= Integer.parseInt(testmandref_fb.getNumberOfRow());	}
*/

			
			if(testmandref_fb.getNumberOfRow().equals(""))
				if(testmandref_fb.getNumberOfRowPresent().equals(""))
				rowvalue=1;
				else if(testmandref_fb.getNumberOfRowPresent()==null)
					rowvalue=1;
				else
					rowvalue= Integer.parseInt(testmandref_fb.getNumberOfRowPresent());
			else			
			rowvalue= Integer.parseInt(testmandref_fb.getNumberOfRow());
				
			
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		int i=0;
		List<TestMandRefMasterVO> lstTestMandRefVO=new ArrayList<TestMandRefMasterVO>();


		if(testmandref_fb.getNumberOfRow()!=null && testmandref_fb.getNumberOfRowPresent()!=null && testmandref_fb.getNumberOfRow().equals("") && testmandref_fb.getNumberOfRowPresent()!="" &&  testmandref_fb.getNumberOfRowPresent()!=null)

		{
		
			i=Integer.parseInt(testmandref_fb.getNumberOfRowPresent());
		rowvalue=i+(Integer.parseInt(testmandref_fb.getNumberOfRow())-Integer.parseInt(testmandref_fb.getNumberOfRowPresent()));
				
		}
		
			for(;i<rowvalue;i++)
			{
				TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
	
				testmandref_VO.setTestCode(testmandref_fb.getTestCode());
				testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());
				testmandref_VO.setCriteriaCode(testmandref_fb.getCriteriaCode());
				
				testmandref_VO.setGender(testmandref_fb.getGender()==null?"":testmandref_fb.getGender()[i]);
				
				
				
				testmandref_VO.setHighAge(testmandref_fb.getHighAge()==null?"":testmandref_fb.getHighAge()[i]);
				testmandref_VO.setLowAge(testmandref_fb.getLowAge()==null?"":testmandref_fb.getLowAge()[i]);

				testmandref_VO.setHighAgeUom(testmandref_fb.getHighAgeUom()==null?"":testmandref_fb.getHighAgeUom()[i]);
				testmandref_VO.setLowAgeUom(testmandref_fb.getLowAgeUom()==null?"":testmandref_fb.getLowAgeUom()[i]);

				testmandref_VO.setHighValue(testmandref_fb.getHighValue()==null?"":testmandref_fb.getHighValue()[i]);
				testmandref_VO.setLowValue(testmandref_fb.getLowValue()==null?"":testmandref_fb.getLowValue()[i]);
				testmandref_VO.setHighValueUom(testmandref_fb.getHighValueUom()==null?"":testmandref_fb.getHighValueUom()[i]);
				testmandref_VO.setLowValueUom(testmandref_fb.getLowValueUom()==null?"":testmandref_fb.getLowValueUom()[i]);

				
				testmandref_VO.setRemarks(testmandref_fb.getRemarks()[i]);
				testmandref_VO.setSymbol(testmandref_fb.getSymbol()[i]);

				if((testmandref_fb.getLabCode()!=null) && (testmandref_fb.getLabCode()[i].equals("")==false) && (testmandref_fb.getLabCode()[i].equals("-1")==false))
					testmandref_VO.setLabCode(testmandref_fb.getLabCode()[i]);
				else
					testmandref_VO.setLabCode(null);
				
				if((testmandref_fb.getMandCode()!=null) && (testmandref_fb.getMandCode()[i].equals("")==false) && (testmandref_fb.getMandCode()[i].equals("-1")==false))
				testmandref_VO.setMandCode(testmandref_fb.getMandCode()[i]);
				else
					testmandref_VO.setMandCode(null);
				
				if((testmandref_fb.getSampleCode()!=null) && testmandref_fb.getSampleCode()[i].equals("")==false && testmandref_fb.getSampleCode()[i].equals("-1")==false)
				testmandref_VO.setSampleCode(testmandref_fb.getSampleCode()[i]);
				else
					testmandref_VO.setSampleCode(null);
				
				
				testmandref_VO.setRange(testmandref_fb.getRange()==null?"":testmandref_fb.getRange()[i]);
				testmandref_VO.setRangeTyp(testmandref_fb.getRangeTyp()==null?"":testmandref_fb.getRangeTyp());
				testmandref_VO.setRangeUom(testmandref_fb.getRangeUom()==null?"":testmandref_fb.getRangeUom()[i]);
	
				lstTestMandRefVO.add(testmandref_VO);
			}
			
			TestMandRefMstDATA.saveTestMandRef(lstTestMandRefVO, userVO);
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



	public static boolean fetchCheckListTestMandRef(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
		List<TestMandRefMasterVO> lstTestMandRefVO=new ArrayList<TestMandRefMasterVO>();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();


			// Fetching Selected Record Primary Key
			String chk = testmandref_fb.getChk()[0].replace("$", "@");  //^
			String[] concatid = chk.split("@");
			String testCode = concatid[0];
			String parameterCode = concatid[1];
		//	String sequenceCode = concatid[2];

			String rangeTyp = concatid[3];
		System.out.println("rangeTyp"+rangeTyp);
			testmandref_fb.setRangeTyp(rangeTyp); 
			testmandref_VO.setRangeTyp(rangeTyp);



			testmandref_fb.setTestCode(testCode); 
			testmandref_VO.setTestCode(testCode);

			testmandref_fb.setParameterCode(parameterCode); 
			testmandref_VO.setParameterCode(parameterCode);
			
			
	//		testmandref_VO.setSeqNo(sequenceCode);

		
			testmandref_fb.setSelectedChk(testmandref_fb.getChk()[0]);

			

			mp=TestMandRefMstDATA.fetchCheckListTestMandRef(testmandref_VO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);



			////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			testmandref_fb.setCriteriaCode(testmandref_VO.getCriteriaCode());
			testmandref_fb.setRangeTyp(rangeTyp);
			/*testmandref_fb.setTestCode(testmandref_VO.getTestCode());
			testmandref_fb.setParameterCode(testmandref_VO.getParameterCode());
			
			
			String gender[]={testmandref_VO.getGender()};
			testmandref_fb.setGender(gender);
			testmandref_VO.setTestCode(testmandref_fb.getTestCode());
			testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());
			
			
			String highAge[]={testmandref_VO.getHighAge()};			
			testmandref_fb.setHighAge(highAge);
			

			String lowAge[]={testmandref_VO.getLowAge()};			
			testmandref_fb.setLowAge(lowAge);


			String highAgeUom[]={testmandref_VO.getHighAgeUom()};			
			testmandref_fb.setHighAgeUom(highAgeUom);


			String lowAgeUom[]={testmandref_VO.getLowAgeUom()};			
			testmandref_fb.setLowAgeUom(lowAgeUom);
			


			String highValue[]={testmandref_VO.getHighValue()};			
			testmandref_fb.setHighValue(highValue);

			String lowValue[]={testmandref_VO.getLowValue()};			
			testmandref_fb.setLowValue(lowValue);

			String highValueUom[]={testmandref_VO.getHighValueUom()};			
			testmandref_fb.setHighValueUom(highValueUom);


			String lowValueUom[]={testmandref_VO.getLowValueUom()};			
			testmandref_fb.setLowValueUom(lowValueUom);


			String remarks[]={testmandref_VO.getRemarks()};			
			testmandref_fb.setRemarks(remarks);


			String symbol[]={testmandref_VO.getSymbol()};			
			testmandref_fb.setSymbol(symbol);


			String labCode[]={testmandref_VO.getLabCode()};			
			testmandref_fb.setLabCode(labCode);


			String sampleCode[]={testmandref_VO.getSampleCode()};			
			testmandref_fb.setSampleCode(sampleCode);


			String mandCode[]={testmandref_VO.getSampleCode()};			
			testmandref_fb.setSampleCode(sampleCode);*/
			
			
			
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


	public static boolean fetchTestMandRef(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 




			mp=TestMandRefMstDATA.fetchTestMandRef(testmandref_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(testmandref_fb, testmandref_VO);

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


	public static boolean savemodifyTestMandRef(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		int k,i,oldrow;
		String mod[]={"-1"};
		String del[]={"-1"};
		String seq="";
		
		List lstOld = new ArrayList();
		List modifySeqList= new ArrayList();
		List deleteSeqList= new ArrayList();

		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			int rowvalue;

			/*feth old seq no to be compared with entered list seq no*/
			
			lstOld=TestMandRefMstDATA.fetchOld(testmandref_fb.getTestCode(),testmandref_fb.getParameterCode(), userVO);
			
			
		
			
			
			
			if(testmandref_fb.getNumberOfRow().equals("NaN"))
				rowvalue=testmandref_fb.getLowValueUom().length;
			else if(testmandref_fb.getNumberOfRow().equals(""))
				rowvalue=Integer.parseInt(testmandref_fb.getNumberOfRowPresent());
			else
			rowvalue= Integer.parseInt(testmandref_fb.getNumberOfRow());
			
			oldrow=testmandref_fb.getSeqNo().length;

			Iterator itr=lstOld.iterator();
			while(itr.hasNext()){
			
				Entry obj=(Entry)itr.next();
					
				modifySeqList.add(obj.getValue());
						
			}
			
			lstOld=modifySeqList;
			modifySeqList= new ArrayList();
			
			for(k=0;k<testmandref_fb.getSeqNo().length;k++)
			{
				String str=(String) testmandref_fb.getSeqNo()[k];
				
				
				if(str.equals(""))
					;
				else if(lstOld.contains(str))
				modifySeqList.add(str);
				else
				deleteSeqList.add(str);
				
			}
			
			for(k=0;k<lstOld.size();k++)
			{
				String str=(String) lstOld.get(k);
				
				
				if(!modifySeqList.contains(str))
					deleteSeqList.add(str);
				
			}
			
			
			
			
			
/*	for(int i=0; i < list1.size(); i++){
				
				String  str1=(String)list1.get(i);
					
				if(!list2.contains(str1))
					resultantList.add(str1);
			*/
			
			/*
			for(k=0;k<rowvalue;k++)
			{
				seq=testmandref_fb.getSeqNo()[k]==null?"0":testmandref_fb.getSeqNo()[k];
				
				
				if(seqList.contains(seq))
					{	mod[k]="1";
						del[k]="0";
					}
				
				else
				{mod[k]="0";
				del[k]="1";}
				
			}*/
		
		
		
			List<TestMandRefMasterVO> new_lstTestMandRefVO=new ArrayList<TestMandRefMasterVO>();
			List<TestMandRefMasterVO> modify_lstTestMandRefVO=new ArrayList<TestMandRefMasterVO>();

			for(i=0;i<oldrow;i++)
			{
				TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
				
				
				  /*modify */
						
						testmandref_VO.setTestCode(testmandref_fb.getTestCode());
						testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());
					
						testmandref_VO.setSeqNo(testmandref_fb.getSeqNo()[i]);			
						
						testmandref_VO.setGender(testmandref_fb.getGender()==null?"":testmandref_fb.getGender()[i]);
						
						
						
						testmandref_VO.setHighAge(testmandref_fb.getHighAge()==null?"":testmandref_fb.getHighAge()[i]);
						testmandref_VO.setLowAge(testmandref_fb.getLowAge()==null?"":testmandref_fb.getLowAge()[i]);

						testmandref_VO.setHighAgeUom(testmandref_fb.getHighAgeUom()==null?"":testmandref_fb.getHighAgeUom()[i]);
						testmandref_VO.setLowAgeUom(testmandref_fb.getLowAgeUom()==null?"":testmandref_fb.getLowAgeUom()[i]);

						testmandref_VO.setHighValue(testmandref_fb.getHighValue()==null?"":testmandref_fb.getHighValue()[i]);
						testmandref_VO.setLowValue(testmandref_fb.getLowValue()==null?"":testmandref_fb.getLowValue()[i]);
						testmandref_VO.setHighValueUom(testmandref_fb.getHighValueUom()==null?"":testmandref_fb.getHighValueUom()[i]);
						testmandref_VO.setLowValueUom(testmandref_fb.getLowValueUom()==null?"":testmandref_fb.getLowValueUom()[i]);

						
						testmandref_VO.setRemarks(testmandref_fb.getRemarks()[i]);
						testmandref_VO.setSymbol(testmandref_fb.getSymbol()[i]);
						
						testmandref_VO.setRange(testmandref_fb.getRange()==null?"":testmandref_fb.getRange()[i]);
						testmandref_VO.setRangeTyp(testmandref_fb.getRangeTyp()==null?"":testmandref_fb.getRangeTyp());
						testmandref_VO.setRangeUom(testmandref_fb.getRangeUom()==null?"":testmandref_fb.getRangeUom()[i]);
						
						if((testmandref_fb.getLabCode()!=null) && (testmandref_fb.getLabCode()[i].equals("")==false) && (testmandref_fb.getLabCode()[i].equals("-1")==false))
							testmandref_VO.setLabCode(testmandref_fb.getLabCode()[i]);
						else
							testmandref_VO.setLabCode(null);
						
						if((testmandref_fb.getMandCode()!=null) && (testmandref_fb.getMandCode()[i].equals("")==false) && (testmandref_fb.getMandCode()[i].equals("-1")==false))
						testmandref_VO.setMandCode(testmandref_fb.getMandCode()[i]);
						else
							testmandref_VO.setMandCode(null);
						
						if((testmandref_fb.getSampleCode()!=null) && testmandref_fb.getSampleCode()[i].equals("")==false && testmandref_fb.getSampleCode()[i].equals("-1")==false)
						testmandref_VO.setSampleCode(testmandref_fb.getSampleCode()[i]);
						else
							testmandref_VO.setSampleCode(null);
						
						
						
			
						modify_lstTestMandRefVO.add(testmandref_VO);
					
				

			}
			/////////////////////////////  new rows ////////////////////////////////
			
			if(rowvalue-oldrow>0)
			for(i=oldrow;i<rowvalue;i++)
			{
				TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
				
				
				  /*add */  
					
					testmandref_VO.setTestCode(testmandref_fb.getTestCode());
					testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());
					testmandref_VO.setCriteriaCode(testmandref_fb.getCriteriaCode());
					
					testmandref_VO.setGender(testmandref_fb.getGender()==null?"":testmandref_fb.getGender()[i]);
					
					
					
					testmandref_VO.setHighAge(testmandref_fb.getHighAge()==null?"":testmandref_fb.getHighAge()[i]);
					testmandref_VO.setLowAge(testmandref_fb.getLowAge()==null?"":testmandref_fb.getLowAge()[i]);

					testmandref_VO.setHighAgeUom(testmandref_fb.getHighAgeUom()==null?"":testmandref_fb.getHighAgeUom()[i]);
					testmandref_VO.setLowAgeUom(testmandref_fb.getLowAgeUom()==null?"":testmandref_fb.getLowAgeUom()[i]);

					testmandref_VO.setHighValue(testmandref_fb.getHighValue()==null?"":testmandref_fb.getHighValue()[i]);
					testmandref_VO.setLowValue(testmandref_fb.getLowValue()==null?"":testmandref_fb.getLowValue()[i]);
					testmandref_VO.setHighValueUom(testmandref_fb.getHighValueUom()==null?"":testmandref_fb.getHighValueUom()[i]);
					testmandref_VO.setLowValueUom(testmandref_fb.getLowValueUom()==null?"":testmandref_fb.getLowValueUom()[i]);
					
					
					testmandref_VO.setRange(testmandref_fb.getRange()==null?"":testmandref_fb.getRange()[i]);
					testmandref_VO.setRangeTyp(testmandref_fb.getRangeTyp()==null?"":testmandref_fb.getRangeTyp());
					testmandref_VO.setRangeUom(testmandref_fb.getRangeUom()==null?"":testmandref_fb.getRangeUom()[i]);
					
					testmandref_VO.setRemarks(testmandref_fb.getRemarks()[i]);
					testmandref_VO.setSymbol(testmandref_fb.getSymbol()[i]);
					
					

					if((testmandref_fb.getLabCode()!=null) && (testmandref_fb.getLabCode()[i].equals("")==false) && (testmandref_fb.getLabCode()[i].equals("-1")==false))
						testmandref_VO.setLabCode(testmandref_fb.getLabCode()[i]);
					else
						testmandref_VO.setLabCode(null);
					
					if((testmandref_fb.getMandCode()!=null) && (testmandref_fb.getMandCode()[i].equals("")==false) && (testmandref_fb.getMandCode()[i].equals("-1")==false))
					testmandref_VO.setMandCode(testmandref_fb.getMandCode()[i]);
					else
						testmandref_VO.setMandCode(null);
					
					if((testmandref_fb.getSampleCode()!=null) && testmandref_fb.getSampleCode()[i].equals("")==false && testmandref_fb.getSampleCode()[i].equals("-1")==false)
					testmandref_VO.setSampleCode(testmandref_fb.getSampleCode()[i]);
					else
						testmandref_VO.setSampleCode(null);
					
		
					new_lstTestMandRefVO.add(testmandref_VO);
				
				
				
		}
			
			
			
			/////////////////////////////////////////////////////////////
		
			if(new_lstTestMandRefVO.size()>0)
			TestMandRefMstDATA.saveTestMandRef(new_lstTestMandRefVO, userVO);
			
			if(deleteSeqList.size()>0)
			TestMandRefMstDATA.deleteTestMandRef(deleteSeqList,testmandref_fb.getTestCode(),testmandref_fb.getParameterCode(), userVO);
			
			if(modify_lstTestMandRefVO.size()>0)
			TestMandRefMstDATA.savemodifyTestMandRef(modify_lstTestMandRefVO, userVO);

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

	public static boolean fetchdisplaydataTestMandRef(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		List<TestMandRefMasterVO> testmandref_listVO=new ArrayList<TestMandRefMasterVO>();

		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();

		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			testmandref_VO.setTestCode(testmandref_fb.getTestCode());
			testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());

			testmandref_listVO=TestMandRefMstDATA.fetchdisplaydataTestMandRef(testmandref_VO, userVO);

			WebUTIL.setAttributeInSession(_request, InvestigationConfig.DISPLAY_DATA_TEST_PARA, testmandref_listVO);


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
	

	public static boolean reFetchCheckListTestMandRef(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();

			String chk="";

			// Fetching Selected Record Primary Key
			chk = testmandref_fb.getSelectedChk().replace("$", "@");
			String[] concatid = chk.split("@");
			String testCode = concatid[0];
			String parameterCode = concatid[1];
			String rangetyp=concatid[3];
			//String sequenceCode = concatid[2];




			testmandref_fb.setTestCode(testCode); 
			testmandref_VO.setTestCode(testCode);

			testmandref_fb.setParameterCode(parameterCode); 
			testmandref_VO.setParameterCode(parameterCode);

			testmandref_fb.setRangeTyp(rangetyp);
			testmandref_VO.setRangeTyp(rangetyp);

			mp=TestMandRefMstDATA.fetchCheckListTestMandRef(testmandref_VO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);



			////////////////////////////////////////////////////////////////////////////////////////////////////////////			

		
			testmandref_fb.setTestCode(testmandref_VO.getTestCode());
			
			testmandref_fb.setParameterCode(testmandref_VO.getParameterCode());
		
			testmandref_fb.setRangeTyp(rangetyp);
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
	public static boolean fetchParameterCombo(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session= _request.getSession();
		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			session.removeAttribute(InvestigationConfig.LIST_TESTPARA_COMBO);

			ControllerUTIL.setSysdate(_request);

			testmandref_VO.setTestCode(testmandref_fb.getTestCode());
			Map mp=new HashMap();

			mp=TestMandRefMstDATA.fetchParameterCombo(testmandref_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);



			HelperMethods.populate(testmandref_fb, testmandref_VO);

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

	
	public static boolean fetchCombo(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session= _request.getSession();
		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			

			ControllerUTIL.setSysdate(_request);

			testmandref_VO.setTestCode(testmandref_fb.getTestCode());
			testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());

			Map mp=new HashMap();

			mp=TestMandRefMstDATA.fetchCombo(testmandref_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);



			HelperMethods.populate(testmandref_fb, testmandref_VO);

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
	
	public static boolean getValues(TestMandRefMstFB testmandref_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
		List<TestMandRefMasterVO> lstLocalTestMandRefVO=new ArrayList<TestMandRefMasterVO>();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();


			// Fetching Selected Record Primary Key
			/*String chk = testmandref_fb.getChk()[0].replace("$", "@");  //^
			String[] concatid = chk.split("@");
			String testCode = concatid[0];
			String parameterCode = concatid[1];
			String sequenceCode = concatid[2];
*/


			String rangeTyp=testmandref_fb.getRangeTyp();
			
			testmandref_VO.setTestCode(testmandref_fb.getTestCode());
			testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());
			testmandref_VO.setRangeTyp(rangeTyp);
			
			//testmandref_VO.setSeqNo(sequenceCode);

		
			//testmandref_fb.setSelectedChk(testmandref_fb.getChk()[0]);

			

			mp=TestMandRefMstDATA.fetchCheckListLocalTestMandRef(testmandref_VO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);



			////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			testmandref_fb.setCriteriaCode(testmandref_VO.getCriteriaCode());
			testmandref_fb.setFromGlobal(testmandref_VO.getFromGlobal());
			testmandref_fb.setRangeTyp(rangeTyp);
		
			/*testmandref_fb.setRange(testmandref_VO.getRange()==null?"":testmandref_VO.getRange());
			testmandref_fb.setRangeTyp(testmandref_VO.getRangeTyp()==null?"":testmandref_VO.getRangeTyp());
			testmandref_fb.setRangeUom(rangeTyp==null?"":rangeTyp);*/
		//	System.out.println("rangeTyp"+rangeTyp);
			/*testmandref_fb.setTestCode(testmandref_VO.getTestCode());
			testmandref_fb.setParameterCode(testmandref_VO.getParameterCode());
			
			
			String gender[]={testmandref_VO.getGender()};
			testmandref_fb.setGender(gender);
			testmandref_VO.setTestCode(testmandref_fb.getTestCode());
			testmandref_VO.setParameterCode(testmandref_fb.getParameterCode());
			
			
			String highAge[]={testmandref_VO.getHighAge()};			
			testmandref_fb.setHighAge(highAge);
			

			String lowAge[]={testmandref_VO.getLowAge()};			
			testmandref_fb.setLowAge(lowAge);


			String highAgeUom[]={testmandref_VO.getHighAgeUom()};			
			testmandref_fb.setHighAgeUom(highAgeUom);


			String lowAgeUom[]={testmandref_VO.getLowAgeUom()};			
			testmandref_fb.setLowAgeUom(lowAgeUom);
			


			String highValue[]={testmandref_VO.getHighValue()};			
			testmandref_fb.setHighValue(highValue);

			String lowValue[]={testmandref_VO.getLowValue()};			
			testmandref_fb.setLowValue(lowValue);

			String highValueUom[]={testmandref_VO.getHighValueUom()};			
			testmandref_fb.setHighValueUom(highValueUom);


			String lowValueUom[]={testmandref_VO.getLowValueUom()};			
			testmandref_fb.setLowValueUom(lowValueUom);


			String remarks[]={testmandref_VO.getRemarks()};			
			testmandref_fb.setRemarks(remarks);


			String symbol[]={testmandref_VO.getSymbol()};			
			testmandref_fb.setSymbol(symbol);


			String labCode[]={testmandref_VO.getLabCode()};			
			testmandref_fb.setLabCode(labCode);


			String sampleCode[]={testmandref_VO.getSampleCode()};			
			testmandref_fb.setSampleCode(sampleCode);


			String mandCode[]={testmandref_VO.getSampleCode()};			
			testmandref_fb.setSampleCode(sampleCode);*/
			
			
			
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
	
	public static StringBuffer getLabTest(TestMandRefMstFB objFB, HttpServletRequest request, String labCode,String testCode)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus = new Status();
		List lstUnit = new ArrayList();
		boolean flag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(request);
			TestMandRefMasterVO testmandref_VO = new TestMandRefMasterVO();
			ControllerUTIL.setSysdate(request);
			
			testmandref_VO.setTestCode(testCode);			
			
			System.out.println("labCode :"+labCode);
			testmandref_VO.setLabCode(labCode);
			
			lstUnit = TestMandRefMstDATA.getLabSample(testmandref_VO,userVO);
			
			
			WebUTIL.setAttributeInSession(request, InvestigationConfig.LIST_SAMPLE_COMBO, lstUnit);
			
			/*if(lstUnit!=null && lstUnit.size()>0)
			{*/
				sbAjaxRes.append("[");
				
				sbAjaxRes.append("{");
				sbAjaxRes.append("sampleCode");sbAjaxRes.append(":");
				sbAjaxRes.append("\'");sbAjaxRes.append("-1");sbAjaxRes.append("\'");sbAjaxRes.append(",");
				sbAjaxRes.append("sampleName");sbAjaxRes.append(":");
				sbAjaxRes.append("\'");sbAjaxRes.append("Select Value");sbAjaxRes.append("\'");
				sbAjaxRes.append("}");sbAjaxRes.append(",");	
				
				
				for(int i=0; i<lstUnit.size(); i++)
				{
					String unit=lstUnit.get(i).toString();
					String [] unitDetail= unit.split(",");
					String sampleCode= unitDetail[0].substring(1, unitDetail[0].length());
					String sampleName= unitDetail[1].substring(0, (unitDetail[1].length()-1));
					System.out.println("sampleCode :"+sampleCode+"  sampleName :"+sampleName);
					sbAjaxRes.append("{");
					sbAjaxRes.append("sampleCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(sampleName);sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("sampleName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append(sampleCode);sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
				}
				if(lstUnit.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
				sbAjaxRes.append("]");
		/*	}
			if(lstUnit==null)
			{
					sbAjaxRes.append("[");
					sbAjaxRes.append("{");
					sbAjaxRes.append("sampleCode");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append("Select Value");sbAjaxRes.append("\'");sbAjaxRes.append(",");
					sbAjaxRes.append("sampleName");sbAjaxRes.append(":");
					sbAjaxRes.append("\'");sbAjaxRes.append("-1");sbAjaxRes.append("\'");
					sbAjaxRes.append("}");sbAjaxRes.append(",");					
					sbAjaxRes.append("]");	
			}*/
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request,InvestigationConfig.LIST_UOM_COMBO, lstUnit);
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
	
	

}


