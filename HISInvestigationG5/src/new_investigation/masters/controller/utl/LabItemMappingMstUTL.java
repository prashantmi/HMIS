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
import new_investigation.vo.LabItemMappingMasterVO;
import new_investigation.vo.LabItemMappingMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LabItemMappingMstDATA;
import new_investigation.masters.controller.data.LabItemMappingMstDATA;
import new_investigation.masters.controller.fb.LabItemMappingMstFB;

public class LabItemMappingMstUTL implements MasterInterface
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
		String[] columnHdr = { "Laboratory Name","Item Name" };
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.HIVT_LABITEM_MST.MAPPING_DTL").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.HIVT_LABITEM_MST.MAPPING_DTL.COND0"));

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
		list.add(InvestigationConfig.HOSPITAL_CODE);
		list.add(hosCode);
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		
		
	
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"SELECT.HIVT_LABITEM_MST.MAPPING_ALL"));
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
		String masterName = "Lab Item Mapping Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "o.hbnum_other_item_code","2", "o.hivnum_laboratory_code" };
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
		String strSearchField[] = { "1", " E.gstr_lab_name " };
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		
		
		viewHdr.add("Laboratory Name");
		viewHdr.add("D");


		viewHdr.add("Item Name");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "SELECT.HIVT_LAB_ITEM_MST.VIEW2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveNewItemList(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
		Status objStatus = new Status();

	

		LabItemMappingMasterVO labItemMappingMasterVO=new LabItemMappingMasterVO();
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		ArrayList insertList=new ArrayList();
		ArrayList modifyTestNameList=new ArrayList();
		ArrayList deleteList=new ArrayList();
		List comparison=new ArrayList();
		WebUTIL.populate( labItemMappingMasterVO,labItemMappingMstFB);
		boolean saveFlag = true;
		try
		{
		
		//getting the old mapped list
			
		List oldMappedList=LabItemMappingMstUTL.getOldMappedList(labItemMappingMstFB, _request);			
			
		//getting the New mapped list
		
		List newMappedList=LabItemMappingMstUTL.getNewMappedList(labItemMappingMstFB, _request);
			
		if(newMappedList==null)
			newMappedList=new ArrayList();
			
		if(oldMappedList==null)
			oldMappedList=new ArrayList();
			
		

		
		
		
		//getting the insert  list
			

				for(int i=0; i < newMappedList.size(); i++){

					String  str1=(String)newMappedList.get(i);
					
					
					if(!oldMappedList.contains(str1))
					{	insertList.add(str1);
					
					}
					else
					{
						modifyTestNameList.add(str1);
					}

				}
				comparison=		LabItemMappingMstUTL.getCompareList(oldMappedList,newMappedList);
				//delete List
				for(int i=0;i<comparison.size();i++)
				{
					deleteList.add((String)comparison.get(i));
				}
				LabItemMappingMstDATA.saveNewItemsList(labItemMappingMasterVO, deleteList, insertList,userVO);
				
			
				WebUTIL.populate(labItemMappingMstFB, labItemMappingMasterVO);
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


	public static boolean fetchInfo(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabItemMappingMasterVO labItemMappingMasterVO = new LabItemMappingMasterVO();
		try
		{
			String []values=labItemMappingMstFB.getChk();
			String labCode=values[0].replace("@", "#").split("#")[0];
			labItemMappingMstFB.setSelectedChk(labItemMappingMstFB.getChk()[0]);
			labItemMappingMstFB.setLabCode(labCode);
			LabItemMappingMstUTL.getItems(labItemMappingMstFB, _request);
	
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


	public static boolean getEssentials(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabItemMappingMasterVO labItemMappingMasterVO = new LabItemMappingMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			
			
			
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=LabItemMappingMstDATA.getEssentials(labItemMappingMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(labItemMappingMstFB, labItemMappingMasterVO);
			
			
			
	
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


	

	public static boolean getItems(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabItemMappingMasterVO labItemMappingMasterVO = new LabItemMappingMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			
			labItemMappingMasterVO.setLabCode(labItemMappingMstFB.getLabCode());

			Map mp=new HashMap(); 

			mp=LabItemMappingMstDATA.getItemsList(labItemMappingMasterVO, userVO);
		
			
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(labItemMappingMstFB, labItemMappingMasterVO);
			

			
			
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

	
	public static List getOldMappedList(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
		List oldMappedListFromSession=(ArrayList)_request.getSession().getAttribute(InvestigationConfig.ITEM_LIST_RIGHT_COMBO);
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
	
	
	public static List getNewMappedList(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
				List newMappedList=new ArrayList();
		
		try
		{
			if(labItemMappingMstFB.getMappedList()!=null && labItemMappingMstFB.getMappedList().length!=0){
				
				
			for(int i=0; i <  labItemMappingMstFB.getMappedList().length; i++){
				
				newMappedList.add(labItemMappingMstFB.getMappedList()[i]);
					
				
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

	

	
	public static boolean reFetchInfo(LabItemMappingMstFB labItemMappingMstFB, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabItemMappingMasterVO labItemMappingMasterVO = new LabItemMappingMasterVO();
		try
		{
			
			String labCode=labItemMappingMstFB.getSelectedChk().replace("@", "#").split("#")[0];
			
			labItemMappingMstFB.setLabCode(labCode);
			LabItemMappingMstUTL.getItems(labItemMappingMstFB, _request);
	
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


