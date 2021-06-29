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
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LocalLabCannedMstDATA;

import new_investigation.masters.controller.fb.LabCannedMstFB;
import new_investigation.vo.LabCannedMasterVO;


public class LocalLabCannedMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Laboratory Name","Canned Name","Canned Code" };
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE_LOCAL.LABCANNED.HIVT_LAB_CANNED_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE_LOCAL.LABCANNED.HIVT_LAB_CANNED_MST.COND.0"));

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
		list.add(hosCode);
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"locallabcanned.main.0"));
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
		String masterName = "Local Lab Canned Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "L.GSTR_LAB_NAME", "2", "M.GSTR_CANNED_NAME" };
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
		String strSearchField[] = { "1", " UPPER(gstr_lab_name) ", "2", " UPPER(gstr_canned_name) "};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Laboratory Name");
		viewHdr.add("D");


		viewHdr.add("CANNED Name");
		viewHdr.add("D");


		viewHdr.add("Canned Code");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select_local.labcanned.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveLocalLabCanned(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();

		LabCannedMasterVO[] insert_labcannedmaster_VO = null;
		LabCannedMasterVO[] delete_labcannedmaster_VO = null;
		LabCannedMasterVO[] modify_labcannedmaster_VO = null;
		
		List insertList=new ArrayList();
		List deleteList=new ArrayList();
		List userCannedList=new ArrayList();
		List cannedResultList=new ArrayList();
		List modifyCannedNameList=new ArrayList();
		List modifyCannedCodeList=new ArrayList();
		
		
		boolean saveFlag = true;
		try
		{

			//getting the old mapped list

			List oldMappedList=LocalLabCannedMstUTIL.getOldMappedList(labcanned_fb, _request);			

			//getting the New mapped list

			List newMappedList=LocalLabCannedMstUTIL.getNewMappedList(labcanned_fb, _request);
			
			//getting usercanned code values in same order as new list
			
			for(int j=0;j<labcanned_fb.getUserCannedCode().length;j++)
				userCannedList.add(labcanned_fb.getUserCannedCode()[j]);

			if(newMappedList==null)
				newMappedList=new ArrayList();

			if(oldMappedList==null)
				oldMappedList=new ArrayList();


			//getting the insert  list
			if(labcanned_fb.getCount().equals("0"))
			{
				insertList=newMappedList;
			}
			else
			/*insertList=LocalLabCannedMstUTIL.getCompareList(newMappedList, oldMappedList,userCannedList);	

			
			try*/
			{

				for(int i=0; i < newMappedList.size(); i++){

					String  str1=(String)newMappedList.get(i);
					String cannedStr=(String)userCannedList.get(i);
					
					if(!oldMappedList.contains(str1))
					{	insertList.add(str1);
						cannedResultList.add(cannedStr);
					}
					else
					{
						modifyCannedNameList.add(str1);
						modifyCannedCodeList.add(cannedStr);
					}

				}

				userCannedList=cannedResultList;
			}

			//getting the insert  list
			deleteList=LocalLabCannedMstUTIL.getDeleteList(oldMappedList,newMappedList);	

			//getting the insert MasterVO
			if(insertList!=null)				
				insert_labcannedmaster_VO=LocalLabCannedMstUTIL.getLabMacroMapVO(insertList, labcanned_fb,userCannedList,"1");

			//getting the delete MasterVO
			if(deleteList!=null)				
				delete_labcannedmaster_VO=LocalLabCannedMstUTIL.getLabMacroMapVO(deleteList, labcanned_fb,userCannedList,"0");

			//getting the modified MasterVO
			if(modifyCannedNameList!=null)				
				modify_labcannedmaster_VO=LocalLabCannedMstUTIL.getLabMacroMapVO(modifyCannedNameList, labcanned_fb,modifyCannedCodeList,"1");





			UserVO userVO = ControllerUTIL.getUserVO(_request);
			/*			HelperMethods.populate(labcannedmaster_VO, labcanned_fb);
			 */			
			LocalLabCannedMstDATA.saveLocalLabCanned(insert_labcannedmaster_VO,delete_labcannedmaster_VO, userVO,modify_labcannedmaster_VO);
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


	public static boolean fetchCheckListLocalLabCanned(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCannedMasterVO labcannedmaster_VO = new LabCannedMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = labcanned_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			labCode = concatid[2];
			hospitalCode = concatid[1];



			labcannedmaster_VO.setIsActive(labcanned_fb.getIsActive());

			labcanned_fb.setLabCode(labCode); 
			labcannedmaster_VO.setLabCode(labCode);
			labcanned_fb.setHospitalCode(hospitalCode); 
			labcannedmaster_VO.setHospitalCode(hospitalCode);

			labcanned_fb.setSelectedChk(labcanned_fb.getChk()[0]);



			mp.putAll(LocalLabCannedMstDATA.fetchLocalLabCanned(labcannedmaster_VO, userVO));
			mp.putAll(LocalLabCannedMstDATA.getLocalLabCanned(labcannedmaster_VO, userVO));
			
			WebUTIL.setMapInSession(mp, _request);

			labcanned_fb.setLabCode(labCode); 
			labcannedmaster_VO.setLabCode(labCode);
			labcanned_fb.setHospitalCode(hospitalCode); 
			labcannedmaster_VO.setHospitalCode(hospitalCode);
			//labcanned_fb.setLocalLaboratoryName(labcannedmaster_VO.getLocalLaboratoryName()); 


			HelperMethods.populate(labcanned_fb, labcannedmaster_VO);
			
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


	public static boolean fetchLocalLabCanned(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCannedMasterVO labcannedmaster_VO = new LabCannedMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);

			/*_request.getSession().removeAttribute("notrequired");*/


			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=LocalLabCannedMstDATA.fetchLocalLabCanned(labcannedmaster_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(labcanned_fb, labcannedmaster_VO);


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

	public static boolean reFetchCheckListLocalLabCanned(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCannedMasterVO labcannedmaster_VO = new LabCannedMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = labcanned_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			labCode = concatid[2];
			hospitalCode = concatid[1];



			labcannedmaster_VO.setIsActive(labcanned_fb.getIsActive());

			labcanned_fb.setLabCode(labCode); 
			labcannedmaster_VO.setLabCode(labCode);
			labcanned_fb.setHospitalCode(hospitalCode); 
			labcannedmaster_VO.setHospitalCode(hospitalCode);

			mp=LocalLabCannedMstDATA.getLocalLabCanned(labcannedmaster_VO, userVO);


			WebUTIL.setMapInSession(mp, _request);

			labcanned_fb.setLabCode(labCode); 
			labcannedmaster_VO.setLabCode(labCode);
			labcanned_fb.setHospitalCode(hospitalCode); 
			labcannedmaster_VO.setHospitalCode(hospitalCode);



			HelperMethods.populate(labcanned_fb, labcannedmaster_VO);

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

	public static boolean getLocalLabCanned(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabCannedMasterVO labcannedmaster_VO = new LabCannedMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			labcannedmaster_VO.setLabCode(labcanned_fb.getLabCode());

			Map mp=new HashMap(); 


			mp=LocalLabCannedMstDATA.getLocalLabCanned(labcannedmaster_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(labcanned_fb, labcannedmaster_VO);

			objStatus.add(Status.NEW);
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


	public static List getOldMappedList(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		List oldMappedListFromSession=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.LIST_SELECTED_CANNED_COMBO);
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


	public static List getNewMappedList(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		List newMappedList=new ArrayList();

		try
		{
			if(labcanned_fb.getMappedList()!=null && labcanned_fb.getMappedList().length!=0){


				for(int i=0; i <  labcanned_fb.getMappedList().length; i++){

					newMappedList.add(labcanned_fb.getMappedList()[i]);


				}




			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();


		}


		return newMappedList;

	}


	public static List getCompareList(List list1, List list2,List userCannedList)
	{
		List resultantList=new ArrayList();
		List cannedResultList=new ArrayList();
		try
		{

			for(int i=0; i < list1.size(); i++){

				String  str1=(String)list1.get(i);
				String cannedStr=(String)userCannedList.get(i);
				
				if(!list2.contains(str1))
				{	resultantList.add(str1);
					cannedResultList.add(cannedStr);
				}

			}

			userCannedList=cannedResultList;
		}
		catch (Exception e)
		{
			e.printStackTrace();


		}

		return resultantList;
	}


	public static LabCannedMasterVO[] getLabMacroMapVO(List documentList,LabCannedMstFB labcanned_fb,List userCannedList, String insDel)
	{
		LabCannedMasterVO[] labcannedmaster_VO=null;


		try
		{
			if(documentList!=null && documentList.size()!=0){
				labcannedmaster_VO=new LabCannedMasterVO[documentList.size()];

				for(int i=0; i < labcannedmaster_VO.length; i++){
					labcannedmaster_VO[i]=new LabCannedMasterVO();
					labcannedmaster_VO[i].setCannedCode((String)documentList.get(i));
					if(insDel.equals("1"))
					labcannedmaster_VO[i].setUserCannedCode((String)userCannedList.get(i)==null?"":(String)userCannedList.get(i));
					labcannedmaster_VO[i].setLabCode(labcanned_fb.getLabCode());
				}
			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();

		}
		return labcannedmaster_VO;

	}
	
	/*public static boolean fetchdisplaydataMacroLocalMap(LabCannedMstFB labcanned_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();


		LabCannedMasterVO labcannedmaster_VO = new LabCannedMasterVO();




		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			labcannedmaster_VO.setLabCode(labcanned_fb.getLabCode());//
			
			
			LocalTestGroupMstDATA.fetchdisplaydataMacroLocalMap(labcannedmaster_VO, userVO);


			HelperMethods.populate(labcanned_fb, labcannedmaster_VO);

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
	}*/

	
	public static List getDeleteList(List list1, List list2)
	{
		List resultantList=new ArrayList();
	
		try
		{

			for(int i=0; i < list1.size(); i++){

				String  str1=(String)list1.get(i);
			
				
				if(!list2.contains(str1))
				{	resultantList.add(str1);
					
				}

			}

		
		}
		catch (Exception e)
		{
			e.printStackTrace();


		}

		return resultantList;
	}
}


