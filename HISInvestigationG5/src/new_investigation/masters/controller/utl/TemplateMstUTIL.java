

package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.masters.controller.data.TemplateMstDATA;
import new_investigation.masters.controller.data.LabTestSampleGlobalMstDATA;
import new_investigation.masters.controller.fb.TemplateMstFB;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.vo.TemplateMstVO;
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

public class TemplateMstUTIL extends ControllerUTIL implements MasterInterface {

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
		//br.append("<img src='../../hisglobal/images/btn-del.png' style='cursor:pointer;'; title='Select One Or More CheckBox To Delete Record(s)'  border=0  tabindex='1' onKeyPress='deleteRecords(\"DELETE\");' onClick='deleteRecords(\"DELETE\");'>");
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
		String[] columnHdr = { "Template Name" };
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
		/*String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Template_Mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Template_Mst.COND"));

		return deleteQuery;*/
		return null;
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
		String hosCode = httpSession.getAttribute("HOSPITAL_CODE").toString();
		List<String> list = new ArrayList<String>();
		list.add(Config.IS_VALID_ACTIVE);
		
		
	
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"TemplateMst.main"));
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
		String masterName = "Template  Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "hivt_template_name" };
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
		String strSearchField[] = { "1", " UPPER(hivt_template_name)"};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Template  Name");
		viewHdr.add("D");


		/*viewHdr.add("Template Type");
		viewHdr.add("D");
*/
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "VIEW.TemplateMst");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	
	public static boolean saveTemplate(TemplateMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TemplateMstVO templateMstVO = new TemplateMstVO();
			
			ControllerUTIL.setSysdate(_request);
			HelperMethods.populate(templateMstVO, fb);
		    
			TemplateMstDATA.saveTemplate(templateMstVO, userVO);
			objStatus.add(Status.DONE, "Template Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL,"Template Name Already Exist","");
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
	public static boolean fetchModifyTemplate(TemplateMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TemplateMstVO templateMstVO = new TemplateMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			String chk = fb.getChk()[0].replace("$", "@");
			String[] concatid = chk.split("@");
			String templateCode = concatid[0];
			fb.setTemplateCode(templateCode);
			templateMstVO.setTemplateCode(templateCode);
			TemplateMstDATA.fetchModifyTemplate(templateMstVO, userVO);
			HelperMethods.populate(fb, templateMstVO);
			  
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
	public static boolean saveModifyTemplate(TemplateMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TemplateMstVO templateMstVO = new TemplateMstVO();
		 
			templateMstVO.setTemplateCode(fb.getTemplateCode());
			 HelperMethods.populate(templateMstVO, fb);
			 
			 TemplateMstDATA.saveModifyTemplate(templateMstVO, userVO);
			
			objStatus.add(Status.DONE, "Data Modified Successfully", "");
			
		}
		catch (HisDuplicateRecordException e)
		{  hasFlag=false;
			System.out.println("Default Sample Already Exist");
			objStatus.add(Status.UNSUCESSFULL,"Template Name Already Exist" ,"");
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
	public static boolean reFetchCheckListMacro(TemplateMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TemplateMstVO templateMstVO = new TemplateMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);		
			
			templateMstVO.setTemplateCode(fb.getTemplateCode());
			TemplateMstDATA.fetchModifyTemplate(templateMstVO, userVO);
				
			HelperMethods.populate(fb, templateMstVO);
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
