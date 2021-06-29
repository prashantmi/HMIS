package new_investigation.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
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

import hisglobal.utility.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.ItemLabTestMappingMstDATA;
import new_investigation.masters.controller.data.LabConsumableMstDATA;
import new_investigation.masters.controller.fb.ItemLabTestMappingMstFB;
import new_investigation.vo.ItemLabTestMappingMstVO;
import new_investigation.vo.LabConsumableMstVO;

public class ItemLabTestMappingMstUTL implements MasterInterface {

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
		String[] columnHdr = {"Item Name","Manufacture","Lot No","Remarks"};
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.HIVT_ITEM_USES_DTL.ITEM").replace("?",seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.HIVT_ITEM_USES_DTL.ITEM.COND0"));
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
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"ItemUsesDetailMst.main.0"));
		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();
		list.add(hosCode);
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		
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
		String masterName = "Item Uses Detail";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "hbstr_item_name" };
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
		String strSearchField[] = { "1", " hbstr_item_name ","3","hbstr_lot_no"};
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Item Name");
		viewHdr.add("D");
		viewHdr.add("Lab Code");
		viewHdr.add("D");
		viewHdr.add("Laboratory Code");
		viewHdr.add("D");
		
			
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "SELECT.ITEM_USES_DTL.VIEW");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	
	public static Map fetchDetails(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		UserVO userVO = ControllerUTIL.getUserVO(request);
		ControllerUTIL.setSysdate(request);
		ItemLabTestMappingMstVO itemLabTestMappingMstVO=new ItemLabTestMappingMstVO(); 
		Map mp=new HashMap();
		Status objStatus=new Status();
		try
		{
			String []values=new String[fb.getChk()[0].replace("$","@").split("@").length];
			values=fb.getChk()[0].replace("$","@").split("@");
			itemLabTestMappingMstVO.setOtherItemID(values[0]);
			
			itemLabTestMappingMstVO.setSlno(values[1]);
			itemLabTestMappingMstVO.setLotNo(values[3]);
			fb.setSelectedChk(fb.getChk()[0]);
			fb.setTempOtherID(values[0]);
			//hbnum_other_item_code=? and hbnum_slno=? and gnum_hospital_code=? and hbstr_lot_no=? and hivnum_laboratory_code =? and hivnum_test_code=?
			mp=ItemLabTestMappingMstDATA.fetchDetails(itemLabTestMappingMstVO, userVO);	
			HelperMethods.populate(fb, itemLabTestMappingMstVO);
	
			if(null==fb.getUsageStartDate())
			{
				fb.setUsageStartDate("");
			}
			if(null==fb.getUsageEndDate())
			{
				fb.setUsageEndDate("");
			}
			if(null==fb.getExpiryDate())
			{
				fb.setExpiryDate("");
			}
			if(null!=fb.getEntry()&&fb.getEntry().equals("0"))
			{
				fb.setEntry("manual");
			}
			if(null!=fb.getEntry()&&fb.getEntry().equals("1"))
			{
				fb.setEntry("store");
			}
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
		WebUTIL.setStatus(request, objStatus);
	}
	return null;
	}
	public static boolean modifySaveDetails(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		UserVO userVO = ControllerUTIL.getUserVO(request);
		Status objStatus=new Status();
		ItemLabTestMappingMstVO itemLabTestMappingMstVO = new ItemLabTestMappingMstVO();
		boolean flag=false;
		try{
			
		HelperMethods.populate(itemLabTestMappingMstVO, fb);
		itemLabTestMappingMstVO.setItemID(fb.getItemID());
		String []values=fb.getSelectedChk().replace("$","@").split("@");
		itemLabTestMappingMstVO.setOtherItemID(values[0]);
		itemLabTestMappingMstVO.setSlno(values[1]);
		itemLabTestMappingMstVO.setLotNo(values[3]);
		itemLabTestMappingMstVO.setItemIdFromStore(itemLabTestMappingMstVO.getItemIdFromStore().split("#")[0]);
		if(null!=fb.getEntry()&&fb.getEntry().equals("manual"))
		{
			itemLabTestMappingMstVO.setEntry("0");
		}
		if(null!=fb.getEntry()&&fb.getEntry().equals("store"))
		{
			itemLabTestMappingMstVO.setEntry("1");
		}
		if(fb.getEntry().equals("manual"))
		{
			itemLabTestMappingMstVO.setStoreID(null);
			itemLabTestMappingMstVO.setItemIdFromStore(null);
			itemLabTestMappingMstVO.setBatchNo(null);
		}
		
		ItemLabTestMappingMstDATA.saveDetailsModify(itemLabTestMappingMstVO, userVO);
		objStatus.add(Status.DONE, "  Data Modified Successfully", "");
		flag=true;
	}
	catch (HisRecordNotFoundException e)
	{
		flag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
	}
	catch (HisDataAccessException e)
	{
		flag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA, "", "Data Access Error");
	}
	catch (HisApplicationExecutionException e)
	{
		flag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
	}
	catch (HisException e)
	{
		flag=false;
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR, "", "Error");
	}
	finally
	{
		WebUTIL.setStatus(request, objStatus);
	}
		return flag;
	}
	public static Map SaveDetails(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		UserVO userVO = ControllerUTIL.getUserVO(request);
		Status objStatus=new Status();
		ItemLabTestMappingMstVO itemLabTestMappingMstVO = new ItemLabTestMappingMstVO();
		try{
		HelperMethods.populate(itemLabTestMappingMstVO, fb);
		
		if(null!=fb.getEntry()&&fb.getEntry().equals("manual"))
		{
			itemLabTestMappingMstVO.setEntry("0");
		}
		if(null!=fb.getEntry()&&fb.getEntry().equals("store"))
		{
			itemLabTestMappingMstVO.setEntry("1");
		}
		if(fb.getEntry().equals("manual"))
		{
			itemLabTestMappingMstVO.setStoreID(null);
			itemLabTestMappingMstVO.setItemIdFromStore(null);
			//itemLabTestMappingMstVO.setBatchNo(null);
		}
		ItemLabTestMappingMstDATA.saveDetails(itemLabTestMappingMstVO, userVO);
		objStatus.add(Status.DONE, "  Data Saved Successfully", "");
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
		WebUTIL.setStatus(request, objStatus);
	}
		return null;
	}
	public static Map getEssentials(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
	UserVO userVO = ControllerUTIL.getUserVO(request);
	ControllerUTIL.setSysdate(request);
	ItemLabTestMappingMstVO itemLabTestMappingMstVO=new ItemLabTestMappingMstVO(); 
	Map mp=new HashMap();
	try
	{
		//hbnum_other_item_code=? and hbnum_slno=? and gnum_hospital_code=? and hbstr_lot_no=? and hivnum_laboratory_code =? and hivnum_test_code=?
		mp=ItemLabTestMappingMstDATA.getEssentials(itemLabTestMappingMstVO, userVO);	
		WebUTIL.setMapInSession(mp, request);
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
		WebUTIL.setStatus(request, objStatus);
	}

		
	return null;
	}
	public static StringBuffer lotNoDuplicacyCheck(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		StringBuffer strBuff=new StringBuffer();
		try{
		UserVO userVO = ControllerUTIL.getUserVO(request);
		ControllerUTIL.setSysdate(request);
		
		ItemLabTestMappingMstVO itemLabTestMappingMstVO=new ItemLabTestMappingMstVO(); 
		Map mp=new HashMap();
		int count=0;
		HelperMethods.populate(itemLabTestMappingMstVO,fb);
		
		mp=ItemLabTestMappingMstDATA.lotDuplicacyCheck(itemLabTestMappingMstVO, userVO);	
		strBuff.append(itemLabTestMappingMstVO.getCount());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally{
	
			
	}
		return strBuff;
	}
	public static StringBuffer getItemCombo(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
	String strMsgText = "";
	try
	{
		UserVO voUser = ControllerUTIL.getUserVO(request);
		
		ItemLabTestMappingMstVO itemLabTestMappingMstVO = new ItemLabTestMappingMstVO();
		
		WebUTIL.populate(itemLabTestMappingMstVO, fb);

		List<Entry> lstItem = ItemLabTestMappingMstDATA.getItemListCombo(itemLabTestMappingMstVO, voUser);
		
		if(lstItem!=null && lstItem.size()>0)
		{
			sbAjaxRes.append("[");
			for(Entry vo : lstItem)
			{
				sbAjaxRes.append("{");
				sbAjaxRes.append("itemId");sbAjaxRes.append(":");
				sbAjaxRes.append("\'");sbAjaxRes.append(vo.getValue());sbAjaxRes.append("\'");sbAjaxRes.append(",");
				sbAjaxRes.append("itemName");sbAjaxRes.append(":");
				sbAjaxRes.append("\'");sbAjaxRes.append(vo.getLabel());sbAjaxRes.append("\'");
				sbAjaxRes.append("}");sbAjaxRes.append(",");
				
			}
			if(lstItem.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
			sbAjaxRes.append("]");
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
	}
	return sbAjaxRes;
	}
	public static Map reFetchDetails(ItemLabTestMappingMstFB fb,HttpServletRequest request)
	{
		UserVO userVO = ControllerUTIL.getUserVO(request);
		ControllerUTIL.setSysdate(request);
		ItemLabTestMappingMstVO itemLabTestMappingMstVO=new ItemLabTestMappingMstVO(); 
		Map mp=new HashMap();
		Status objStatus=new Status();
		try
		{
			String []values=new String[fb.getSelectedChk().replace("$","@").split("@").length];
			values=fb.getSelectedChk().replace("$","@").split("@");
			itemLabTestMappingMstVO.setOtherItemID(values[0]);
			itemLabTestMappingMstVO.setSlno(values[1]);
			itemLabTestMappingMstVO.setLotNo(values[3]);

			
			//hbnum_other_item_code=? and hbnum_slno=? and gnum_hospital_code=? and hbstr_lot_no=? and hivnum_laboratory_code =? and hivnum_test_code=?
			mp=ItemLabTestMappingMstDATA.fetchDetails(itemLabTestMappingMstVO, userVO);	
			HelperMethods.populate(fb, itemLabTestMappingMstVO);
	
			if(null==fb.getUsageStartDate())
			{
				fb.setUsageStartDate("");
			}
			if(null==fb.getUsageEndDate())
			{
				fb.setUsageEndDate("");
			}
			if(null==fb.getExpiryDate())
			{
				fb.setExpiryDate("");
			}
			if(null!=fb.getEntry()&&fb.getEntry().equals("0"))
			{
				fb.setEntry("manual");
			}
			if(null!=fb.getEntry()&&fb.getEntry().equals("1"))
			{
				fb.setEntry("store");
			}
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
		WebUTIL.setStatus(request, objStatus);
	}
	return null;
	}
	
}