/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory locally i.e. on hospital code 101
 ## Date of Creation					:19-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.TestMandatoryLocalMstDATA;
import new_investigation.masters.controller.fb.TestMandatoryMstFB;
import new_investigation.vo.TestMandatoryMstVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

public class TestMandatoryLocalMstUTIL extends ControllerUTIL implements
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
		String[] columnHdr = { "Test Name","Mandatory Name" };
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Test_Mandatory_Global_Mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Test_Mandatory_Global_Mst.Cond"));

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
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		list.add(Config.IS_VALID_ACTIVE);
		list.add(hosCode);
		
	
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"TestMandatoryMst.Main"));
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
		String masterName = "Test Mandatory Local Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1","b.gstr_test_name","2", "c.hgstr_mandatory_name" };
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
		String strSearchField[] = { "1", " UPPER(b.gstr_test_name) ","2","UPPER(c.hgstr_mandatory_name)"};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test Name");
		viewHdr.add("D");


		viewHdr.add("Mandatory Name");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "VIEW.Test_Mandatory_Global_Mst");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}

	public static boolean fetchTestMandatory(TestMandatoryMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestMandatoryMstVO testMandatoryMstVO = new TestMandatoryMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			
			/*_request.getSession().removeAttribute("notrequired");*/
			
			
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=TestMandatoryLocalMstDATA.fetchTestMandatory(testMandatoryMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(fb, testMandatoryMstVO);
			
			
			
			
		/*	_request.getSession().removeAttribute(InvestigationConfig.LIST_AREA_COMBO);
			_request.getSession().removeAttribute(InvestigationConfig.LIST_SELECTED_COMBO);
		*/
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
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	public static boolean getMandatory(TestMandatoryMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestMandatoryMstVO testMandatoryMstVO = new TestMandatoryMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			testMandatoryMstVO.setTestCode(fb.getTestCode());

			Map mp=new HashMap(); 


			mp=TestMandatoryLocalMstDATA.getMandatory(testMandatoryMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(fb, testMandatoryMstVO);
			
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
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	public static boolean saveTestMandatoryMst(TestMandatoryMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		TestMandatoryMstVO[] insert_testMandatoryMstVO = null;
		TestMandatoryMstVO[] delete_testMandatoryMstVO = null;
		List insertList=new ArrayList();
		List deleteList=new ArrayList();


		boolean saveFlag = true;
		try
		{
		
		//getting the old mapped list
			
		List oldMappedList=TestMandatoryLocalMstUTIL.getOldMappedList(fb, _request);			
			
		//getting the New mapped list
		
		List newMappedList=TestMandatoryLocalMstUTIL.getNewMappedList(fb, _request);
			
		if(newMappedList==null)
			newMappedList=new ArrayList();
			
		if(oldMappedList==null)
			oldMappedList=new ArrayList();
			
			
			//getting the insert  list
			if(fb.getCount().equals("0"))
			{
				insertList=newMappedList;
			}
			else
				insertList=TestMandatoryLocalMstUTIL.getCompareList(newMappedList, oldMappedList);
				
			
			//getting the insert  list
			deleteList=TestMandatoryLocalMstUTIL.getCompareList(oldMappedList,newMappedList);	
			
			//getting the insert MasterVO
			if(insertList!=null)				
				insert_testMandatoryMstVO=TestMandatoryLocalMstUTIL.getLabCollectionAreaVO(insertList, fb);
			
			//getting the insert MasterVO
			if(deleteList!=null)				
				delete_testMandatoryMstVO=TestMandatoryLocalMstUTIL.getLabCollectionAreaVO(deleteList, fb);
			
			
			
			
			
			UserVO userVO = ControllerUTIL.getUserVO(_request);
/*			HelperMethods.populate(labcollectionarea_VO, labcollectionarea_fb);
*/			
			TestMandatoryLocalMstDATA.saveTestMandatory(insert_testMandatoryMstVO,delete_testMandatoryMstVO, userVO);
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

		finally
		{

			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return saveFlag;
	}
	
	public static List getOldMappedList(TestMandatoryMstFB fb, HttpServletRequest _request)
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
	
	public static List getNewMappedList(TestMandatoryMstFB fb, HttpServletRequest _request)
	{
				List newMappedList=new ArrayList();
				try
				{
								
			if(fb.getMappedList()!=null && fb.getMappedList().length!=0){
				
				
			for(int i=0; i <  fb.getMappedList().length; i++){
				
				newMappedList.add(fb.getMappedList()[i]);
					
				
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
	

	public static TestMandatoryMstVO[] getLabCollectionAreaVO(List documentList,TestMandatoryMstFB fb)
	{
		TestMandatoryMstVO[] testMandatoryMstVO=null;
		/*int temp=Integer.parseInt(labcollectionarea_fb.getTemplab());
		int iteration=documentList.size();
		for(int i=0; i < _patCatVerDocVO.length; i++){
					_patCatVerDocVO[i]=new PatientCategoryVerificationMasterVO();
					_patCatVerDocVO[i].setPatientCategoryCode(_fb.getPatientPrimaryCategory());
					_patCatVerDocVO[i].setDocumentCode((String)documentList.get(i));
					_patCatVerDocVO[i].setIsReq(RegistrationConfig.PATIENT_CATEGORY_VERIFICATION_MST_IS_REQ);
 					
					
					}*/
		
		try
		{
			if(documentList!=null && documentList.size()!=0){
				testMandatoryMstVO=new TestMandatoryMstVO[documentList.size()];
				
			for(int i=0; i < testMandatoryMstVO.length; i++){
				testMandatoryMstVO[i]=new TestMandatoryMstVO();
				testMandatoryMstVO[i].setMandCode((String)documentList.get(i));
				testMandatoryMstVO[i].setTestCode(fb.getTestCode());
				
				}
				}
		}		
		catch (Exception e)
		{
		e.printStackTrace();
			
		}
return testMandatoryMstVO;
		
}

	public static boolean fetchCheckListLabCollectionArea(TestMandatoryMstFB fb, HttpServletRequest _request)

    {

          Status objStatus = new Status();

          TestMandatoryMstVO testMandatoryMstVO = new TestMandatoryMstVO();

          try

          {

                UserVO userVO = ControllerUTIL.getUserVO(_request);
                ControllerUTIL.setSysdate(_request);
                Map mp=new HashMap();
                String chk="",testCode="",hospitalCode="";
                String[] concatid={};
                chk = fb.getChk()[0].replace("$", "@"); 
                concatid = chk.split("@");
                testCode = concatid[2];
                hospitalCode = concatid[1];
                fb.setTestCode(testCode);
                testMandatoryMstVO.setTestCode(testCode);
                fb.setSelectedChk(fb.getChk()[0]);
                mp.putAll(TestMandatoryLocalMstDATA.fetchTestMandatory(testMandatoryMstVO,userVO));
                mp.putAll(TestMandatoryLocalMstDATA.getMandatory(testMandatoryMstVO, userVO));
                WebUTIL.setMapInSession(mp, _request);
                fb.setTestCode(testCode);
                testMandatoryMstVO.setTestCode(testCode);
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

 

          finally

          {

                WebUTIL.setStatus(_request, objStatus);

          }

          return true;

    }

}
