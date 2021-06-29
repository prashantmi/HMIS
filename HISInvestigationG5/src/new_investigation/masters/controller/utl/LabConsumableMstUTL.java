package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.FilmMStDATA;
import new_investigation.masters.controller.data.LabConsumableMstDATA;
import new_investigation.masters.controller.data.TestMstDATA;
import new_investigation.masters.controller.fb.TestMstFB;
import new_investigation.masters.controller.fb.LabConsumableMstFB;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.LabConsumableMstVO;
import new_investigation.vo.TestNewMasterVO;
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

public class LabConsumableMstUTL implements MasterInterface {

	private static final long serialVersionUID = 05L;

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
		String[] columnHdr = { "Item Name", "Item Type"};
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
		return comboQuery;
	}
	
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.ITEM.HIVT_OTHER_ITEM_MST").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.ITEM.HIVT_OTHER_ITEM_MST.COND.0"));

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
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add("100");
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"LabConsumableMst.main.0"));
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
		String masterName = "Lab Consumable Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1" , " UPPER(HBSTR_ITEM_NAME) "};
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
		String strSearchField[] = {  "1", " UPPER(hbstr_item_name) "};
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		
		viewHdr.add("Item Name");
		viewHdr.add("D");

		viewHdr.add("Item Type");
		viewHdr.add("D");
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.lab.consumable.view.1");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	
	
	
	
	public static boolean saveDetails(LabConsumableMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		int count;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabConsumableMstVO labConsumableMstVO = new LabConsumableMstVO();
			HelperMethods.populate(labConsumableMstVO, _fb);
			//testNewMasterVO.setresultEntryForm(_fb.getResultEntryForm());
			 if(labConsumableMstVO.getItemIdFromStore().equals("-1"))
			 {
				 labConsumableMstVO.setItemIdFromStore(null);
			 }
				
				
				count=LabConsumableMstDATA.checkDuplicateForModify(labConsumableMstVO, userVO);
				  
				  if(count>0)
				  {
					throw new HisDuplicateRecordException(); 
				  }
			LabConsumableMstDATA.saveDetails(labConsumableMstVO, userVO);
			objStatus.add(Status.DONE, "  Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, "Name Already Exist! Please Choose Other Name","");
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

	
	public static boolean fetchDetails(LabConsumableMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConsumableMstVO labConsumableMstVO = new LabConsumableMstVO();
		
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			Map mp=new HashMap();
			String []values=new String[fb.getChk().length];
			values=fb.getChk()[0].replace("$","@").split("@");
			labConsumableMstVO.setOtherItemID(values[0]);
			  fb.setSelectedChk(fb.getChk()[0]);
			
			mp=LabConsumableMstDATA.fetchDetails(labConsumableMstVO, userVO);
			//WebUTIL.setMapInSession(mp, _request);
			
			
	       	
			  HelperMethods.populate(fb, labConsumableMstVO);
			
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
	public static void refetchDetails(LabConsumableMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConsumableMstVO labConsumableMstVO = new LabConsumableMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			Map mp=new HashMap();
			 String data[]=fb.getSelectedChk().replace("$","@").split("@");
				String otherItemId=data[0];
				labConsumableMstVO.setOtherItemID(otherItemId);
			
			mp=LabConsumableMstDATA.fetchDetails(labConsumableMstVO, userVO);
			//WebUTIL.setMapInSession(mp, _request);
			
			
	       	
			  HelperMethods.populate(fb, labConsumableMstVO);
			  fb.setHmode("MODIFY");  
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
		
	}
	public static boolean saveModifyDetails(LabConsumableMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConsumableMstVO labConsumableMstVO = new LabConsumableMstVO();
		int count=0;
		boolean saveFlag=false;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			Map mp=new HashMap();
			
			labConsumableMstVO.setOtherItemID(fb.getOtherItemID());
			 HelperMethods.populate(labConsumableMstVO, fb);
			 String data[]=fb.getSelectedChk().replace("$","@").split("@");
				String otherItemId=data[0];
				labConsumableMstVO.setOtherItemID(otherItemId);
				count=LabConsumableMstDATA.checkDuplicateForModify(labConsumableMstVO, userVO);
				  
				  if(count>0)
				  {
					throw new HisDuplicateRecordException(); 
				  }
			LabConsumableMstDATA.saveModifyDetails(labConsumableMstVO, userVO);
			objStatus.add(Status.NEW);
			objStatus.add(Status.DONE, "  Data Modified Successfully", "");
			saveFlag=true;
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL, "Name Already Exist! Please Choose Other Name","");
			System.out.println(e.getMessage());
		}
		catch (HisRecordNotFoundException e)
		{
			saveFlag=false;
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
			objStatus.add(Status.ERROR, "", "Error");
		}
		
		finally
		{
			
			WebUTIL.setStatus(_request, objStatus);
			
		}
		return saveFlag;
	}

	public static boolean getEssentials(LabConsumableMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConsumableMstVO labConsumableMstVO = new LabConsumableMstVO();
		
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			WebUTIL.populate(labConsumableMstVO, fb);
			mp=LabConsumableMstDATA.getEssentials(labConsumableMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
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
