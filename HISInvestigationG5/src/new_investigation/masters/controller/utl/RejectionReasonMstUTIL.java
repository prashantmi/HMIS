package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hisglobal.masterutil.MasterInterface;
import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.RejectionReasonMstDATA;
import new_investigation.masters.controller.fb.RejectionReasonMstFB;
import new_investigation.vo.RejectionReasonMasterVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

 
public class RejectionReasonMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Rejection Reason", "Used For Type"};
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
				//+ "#2^InActive";
		return comboQuery;
	}
	
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.REJECTIONREASON.HIVT_REJECTIONREASON_MST").replace("?",seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.REJECTIONREASON.HIVT_REJECTIONREASON_MST.COND.0"));

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
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"rejectionreasonMst.main.0"));
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
		String masterName = "Rejection Reason Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "HGSTR_REJECTIONREASON" };
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
		String strSearchField[] = { "1", " UPPER(hgstr_rejectionreason) " };
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Rejection Reason Name");
		viewHdr.add("D");
		viewHdr.add("Used For Type");
		viewHdr.add("D");
		
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.rejectionreason.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	
	public static boolean saveRejectionReason(RejectionReasonMstFB rejectionreason_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			RejectionReasonMasterVO rejectionreasonmaster_VO = new RejectionReasonMasterVO();
			HelperMethods.populate(rejectionreasonmaster_VO, rejectionreason_fb);
		
			RejectionReasonMstDATA.saveRejectionReason(rejectionreasonmaster_VO, userVO);
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
	
	
	
	public static boolean fetchCheckListRejectionReason(RejectionReasonMstFB rejectionreason_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RejectionReasonMasterVO rejectionreasonmaster_VO = new RejectionReasonMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			//
			 Map mp=new HashMap();
             String chk="",rejectionReasonId="";
             String[] concatid={};
             
              // Fetching Selected Record Primary Key
             chk = rejectionreason_fb.getChk()[0].replace("$", "@");  
             concatid = chk.split("@");
             rejectionReasonId = concatid[0];
             	
             rejectionreasonmaster_VO.setIsActive(rejectionreason_fb.getIsActive());
             
             rejectionreason_fb.setRejectionreasonId(rejectionReasonId);
             rejectionreasonmaster_VO.setRejectionreasonId(rejectionReasonId);
                     
			rejectionreason_fb.setSelectedChk(rejectionreason_fb.getChk()[0]);

			RejectionReasonMstDATA.fetchRejectionReason(rejectionreasonmaster_VO, userVO);
			  
            rejectionreason_fb.setRejectionreasonId(rejectionReasonId);
            rejectionreasonmaster_VO.setRejectionreasonId(rejectionReasonId);
           	
			HelperMethods.populate(rejectionreason_fb, rejectionreasonmaster_VO);
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

	
	public static boolean fetchRejectionReason(RejectionReasonMstFB rejectionreason_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RejectionReasonMasterVO rejectionreasonmaster_VO = new RejectionReasonMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
				
			rejectionreasonmaster_VO.setRejectionreasonId(rejectionreason_fb.getChk()[0].split("$")[0]);

			RejectionReasonMstDATA.fetchRejectionReason(rejectionreasonmaster_VO, userVO);
			HelperMethods.populate(rejectionreason_fb, rejectionreasonmaster_VO);

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

	public static boolean savemodifyRejectionReason(RejectionReasonMstFB rejectionreason_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
		
			RejectionReasonMasterVO rejectionreasonmaster_VO = new RejectionReasonMasterVO();
			rejectionreasonmaster_VO.setRejectionreasonId(rejectionreason_fb.getRejectionreasonId());

			 HelperMethods.populate(rejectionreasonmaster_VO, rejectionreason_fb);
		
			RejectionReasonMstDATA.savemodifyRejectionReason(rejectionreasonmaster_VO, userVO);
			
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
	
	
	public static boolean reFetchCheckListRejectionReason(RejectionReasonMstFB rejectionreason_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RejectionReasonMasterVO rejectionreasonmaster_VO = new RejectionReasonMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
						
			String rejectionReasonId=rejectionreason_fb.getSelectedChk().replace("$","#").split("#")[0];
			rejectionreasonmaster_VO.setRejectionreasonId(rejectionReasonId);
			RejectionReasonMstDATA.fetchRejectionReason(rejectionreasonmaster_VO, userVO);
			
				
			HelperMethods.populate(rejectionreason_fb, rejectionreasonmaster_VO);
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
