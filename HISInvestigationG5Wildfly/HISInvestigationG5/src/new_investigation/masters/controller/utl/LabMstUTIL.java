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
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.LabMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.InvSampleMstDATA;
import new_investigation.masters.controller.fb.InvSampleMstFB;
import new_investigation.masters.controller.data.LabMstDATA;
import new_investigation.masters.controller.fb.LabMstFB;

public class LabMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Lab Name","Lab Short Name", "Department"};
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.LAB.hivt_laboratory_mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.LAB.hivt_laboratory_mst.COND.0"));

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
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"LabMst.main.0"));
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
		String masterName = "Global Laboratory Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "gstr_lab_name", "2", " UPPER(gstr_lab_short) ", "3", " UPPER(pkg_clinical_util_fun.get_dept_name(a.gnum_hospital_code,a.gnum_dept_code)) "  };
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
		String strSearchField[] = { "1", " UPPER(gstr_lab_name) ", "2", " UPPER(gstr_lab_short) ", "3", " UPPER(pkg_clinical_util_fun.get_dept_name(a.gnum_hospital_code,a.gnum_dept_code)) " };
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Lab Name");
		viewHdr.add("D");

		viewHdr.add("Lab Short Name");
		viewHdr.add("D");

		viewHdr.add("Department");
		viewHdr.add("D");
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.lab.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveLab(LabMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabMasterVO labMasterVO = new LabMasterVO();
			
			ControllerUTIL.setSysdate(_request);
			 String systemDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  _fb.setSystemDate(systemDate);
			  String sys=(String)session.getAttribute(Config.SYSDATE);
			  String time=sys.split(" ")[1];
			  _fb.setSystemTime(time);
			  _fb.setSystemTimeInHr(time.split(":")[0]);
			  _fb.setSystemTimeInMin(time.split(":")[1]);
			  
			
			HelperMethods.populate(labMasterVO, _fb);
		    
			LabMstDATA.saveLab(labMasterVO, userVO);
			objStatus.add(Status.DONE, " Data Saved Successfully", "");
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

	
	public static boolean fetchLab(LabMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabMasterVO labMasterVO = new LabMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			 // Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String labCode = concatid[0];
		 
			String startTime=fb.getSampStartHr()+':'+fb.getSampStartMin();
			String endTime=fb.getSampEndHr()+':'+fb.getSampEndMin();
			labMasterVO.setSampleCStrtTime(startTime);
			labMasterVO.setSampleCEndTime(endTime);
			 
			 
			 fb.setLabCode(labCode);
			 labMasterVO.setLabCode(labCode);
			///labMasterVO.setLabCode(fb.getChk()[0]);
			 
			labMasterVO.setPreviousLaboratoryName(fb.getLaboratoryName()); 
			 
			mp=LabMstDATA.fetchLab(labMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labMasterVO);
			 fb.setSelectedChk(fb.getChk()[0]);
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

	public static boolean savemodifyLab(LabMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabMasterVO labMasterVO = new LabMasterVO();
		 
			labMasterVO.setLabCode(_fb.getLabCode());
			 HelperMethods.populate(labMasterVO, _fb);
			 
			 LabMstDATA.savemodifyLab(labMasterVO, userVO);
			
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
	
	public static boolean fetchLabD(LabMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabMasterVO labMasterVO = new LabMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 

			 // Fetching Selected Record Primary Key
			////String chk = fb.getChk()[0].replace("^", "@");
			//String[] concatid = chk.split("@");
			/*String sampleName = concatid[0];
			String sampleSName= concatid[1];
			String loincSystem= concatid[2];
			String remarks= concatid[3];
			

			fb.setSampleName(sampleName); 
	        fb.setSampleSName(sampleSName);
	         fb.setLoincSystem(loincSystem);
	        fb.setRemarks(remarks);
			
			uOMMasterVO.setSampleName(sampleName);
			uOMMasterVO.setSampleSName(sampleSName);
			uOMMasterVO.setLoincSystem(loincSystem);
			uOMMasterVO.setRemarks(remarks);*/

			 
			
			
			//labMasterVO.setUomCode(fb.getChk()[0]);
			mp=LabMstDATA.fetchLabD(labMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labMasterVO);
			  
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
	
	public static boolean reFetchLab(LabMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabMasterVO labMasterVO = new LabMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			 // Fetching Selected Record Primary Key
			String chk = fb.getSelectedChk().replace("^", "@");
			String[] concatid = chk.split("@");
			String labCode = concatid[0];
		 
			String startTime=fb.getSampStartHr()+':'+fb.getSampStartMin();
			String endTime=fb.getSampEndHr()+':'+fb.getSampEndMin();
			labMasterVO.setSampleCStrtTime(startTime);
			labMasterVO.setSampleCEndTime(endTime);
			 
			 
			 fb.setLabCode(labCode);
			 labMasterVO.setLabCode(labCode);
			///labMasterVO.setLabCode(fb.getChk()[0]);
			 
			labMasterVO.setPreviousLaboratoryName(fb.getLaboratoryName()); 
			 
			mp=LabMstDATA.fetchLab(labMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labMasterVO);
			 fb.setSelectedChk(labCode);
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


