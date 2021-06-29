package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LabConfigratorMstDATA;
import new_investigation.masters.controller.fb.LabConfigratorMstFB;
import new_investigation.vo.LabConfigratorMstVO;
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

public class LabConfigratorMstUTIL extends ControllerUTIL implements
		MasterInterface {
	
	
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
		String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();

		List<String> list = new ArrayList<String>();
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(hosCode);
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
		String masterName = "Lab Configrator Master";
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
	
	public static boolean fetchLabConfigrator(LabConfigratorMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConfigratorMstVO labConfigratorMstVO = new LabConfigratorMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 

			
			
			mp=LabConfigratorMstDATA.fetchLabConfigrator(labConfigratorMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labConfigratorMstVO);
			  
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
	public static boolean populate(LabConfigratorMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConfigratorMstVO labConfigratorMstVO = new LabConfigratorMstVO();
		List<LabConfigratorMstVO> labConfigratorVO=new ArrayList<LabConfigratorMstVO>();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			Map mp1=new HashMap();
			labConfigratorMstVO.setLabCode(fb.getLabCode());
			
			mp=LabConfigratorMstDATA.populate(labConfigratorMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labConfigratorMstVO);
			mp1=LabConfigratorMstDATA.getTestByLabCode(labConfigratorMstVO, getUserVO(_request));
			WebUTIL.setMapInSession(mp1, _request);
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
	public static boolean saveLabConfigrator(LabConfigratorMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabConfigratorMstVO labConfigratorMstVO = new LabConfigratorMstVO();
			
			ControllerUTIL.setSysdate(_request);
			 String systemDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  fb.setSystemDate(systemDate);
			  String sys=(String)session.getAttribute(Config.SYSDATE);
			  String time=sys.split(" ")[1];
			  fb.setSystemTime(time);
			  fb.setSystemTimeInHr(time.split(":")[0]);
			  fb.setSystemTimeInMin(time.split(":")[1]);
			 
			
			HelperMethods.populate(labConfigratorMstVO, fb);
		    
			LabConfigratorMstDATA.saveLabConfigrator(labConfigratorMstVO, userVO);
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
	public static boolean fetchModifyLabConfigrator(LabConfigratorMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConfigratorMstVO labConfigratorMstVO = new LabConfigratorMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			String labCode = concatid[0];
			fb.setLabCode(labCode);
			String startTime=fb.getSampStartHr()+':'+fb.getSampStartMin();
			String endTime=fb.getSampEndHr()+':'+fb.getSampEndMin();
			labConfigratorMstVO.setSampleCStrtTime(startTime);
			labConfigratorMstVO.setSampleCEndTime(endTime);
			labConfigratorMstVO.setLabCode(labCode);
			labConfigratorMstVO.setPreviousLaboratoryName(fb.getLaboratoryName()); 
			mp=LabConfigratorMstDATA.fetchModifyLabConfigrator(labConfigratorMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, labConfigratorMstVO);
			  
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
	public static boolean saveModifyLabConfigrator(LabConfigratorMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		List deleteMacro=new ArrayList();
		List deleteCanned=new ArrayList();
		List deleteTest=new ArrayList();
		List deleteSample=new ArrayList();
		List deleteMand=new ArrayList();
		//List deleteGroup=new ArrayList();
		
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LabConfigratorMstVO labConfigratorMstVO = new LabConfigratorMstVO();
			
			
			//old data
			
			
			int k=0,j=0,i=0;
				String value="";
				String[] sepValue=null;
			
				//getting test values to be deleted
				List mpTestMapped=(List)session.getAttribute(InvestigationConfig.LIST_TEST_COMBO_MAPPED);
				Map<String,Map<String,List<String>>> mpTestSample2=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_SAMPLE_DTLS);
			 	Map<String,Map<String,List<String>>> mpTestMand=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_MAND_DTLS);
			 	Map<String,Map<String,List<String>>> mpTestGroup=(Map<String,Map<String,List<String>>>)session.getAttribute(InvestigationConfig.MAP_TEST_GROUP_DTLS);
			
			 	if(mpTestMapped==null)
			 		;
			 	
			 	else
			 	{
			 	
			 	for(k=0;k<mpTestMapped.size();k++)
				{	
					value=mpTestMapped.get(k).toString();
					sepValue=value.split("#");
					
					String tCode=sepValue[0];
					
				
					if(fb.getTestChkBox()!=null)
					{
					for(j=0;j<fb.getTestChkBox().length;j++)
					{
						if(tCode.equals(fb.getTestChkBox()[j]))
							break;
					}
					if(j==fb.getTestChkBox().length)
						deleteTest.add(tCode);					
			
					}
					
					else
						deleteTest.add(tCode);					// all old tests unchecked, and no new added. so delete all
					//deleting samples
					List<String> lsttestSample=(List<String>)mpTestSample2.get(mpTestMapped.get(k));
					
					if(lsttestSample==null)
						;
					else
					{
					for(i=0;i<lsttestSample.size();i++)
					{
						String listValue=lsttestSample.get(i);
						String testSample=listValue.split("#")[0]+"#"+listValue.split("#")[1];
						
						if(fb.getSampleChkBox()!=null)
						{
						
						for(j=0;j<fb.getSampleChkBox().length;j++)
						{
							if(testSample.equals(fb.getSampleChkBox()[j]))
								break;
						}
						if(j==fb.getSampleChkBox().length)
							deleteSample.add(testSample);		
						}	
						
						else
							deleteSample.add(testSample);		// all old samples unchecked, and no new added. so delete all
							
					}
					}
					//deleting mand
					List<String> lsttestMand=(List<String>)mpTestMand.get(mpTestMapped.get(k));
					if(lsttestMand==null)
						;
					else
					{	
						
					for(i=0;i<lsttestMand.size();i++)
					{
						String listValue=lsttestMand.get(i);
						String testMand=listValue.split("#")[0]+"#"+listValue.split("#")[1];
						
						if(fb.getMandChkBox()!=null)
						{
						for(j=0;j<fb.getMandChkBox().length;j++)
						{
							if(testMand.equals(fb.getMandChkBox()[j]))
								break;
						}
						if(j==fb.getMandChkBox().length)
							deleteMand.add(testMand);	
						}
						else
							deleteMand.add(testMand);	// all old mands unchecked, and no new added. so delete all
									
					}
				
				
					}
					
					
					//deleting group
					
					/*List<String> lsttestGroup=(List<String>)mpTestGroup.get(mpTestMapped.get(k));
					if(lsttestGroup==null)
						;
					else
					{	
						
					for(i=0;i<lsttestGroup.size();i++)
					{
						String listValue=lsttestGroup.get(i);
						String testGroup=listValue.split("#")[0]+"#"+listValue.split("#")[1];
						
						
						for(j=0;j<fb.getGroupChkBox().length;j++)
						{
							if(testGroup.equals(fb.getGroupChkBox()[j]))
								break;
						}
						if(j==fb.getGroupChkBox().length)
							deleteGroup.add(testGroup);		
									
					}
				
				
					}
					*/
					
				
				
				}
			  
			 	}
				
				
				
			 	
			 	//getting macro codes to be deleted
			 	List mpMacroMapped=(List)session.getAttribute(InvestigationConfig.LIST_MACRO_COMBO_MAPPED);
			
			 	if(mpMacroMapped==null)
			 		;
			 	else
			 	{
			 	
			 	for(k=0;k<mpMacroMapped.size();k++)
				{	
					value=mpMacroMapped.get(k).toString();
					sepValue=value.split("#");
					
					String mCode=sepValue[0];
					
					if(fb.getMacroChkBox()!=null)
					{
					for(j=0;j<fb.getMacroChkBox().length;j++)
					{
						if(mCode.equals(fb.getMacroChkBox()[j]))
							break;
									
					
					}
					if(j==fb.getMacroChkBox().length)
						deleteMacro.add(mCode);
					
					}
					
					else
						deleteMacro.add(mCode);					// all old macros unchecked, and no new added. so delete all
					
				}
			 	}
			  
				//getting canned codes to be deleted
				List mpCannedMapped=(List)session.getAttribute(InvestigationConfig.LIST_CANNED_COMBO_MAPPED);
				
				if(mpCannedMapped==null)
					;
				
				else
				{
				
				for(k=0;k<mpCannedMapped.size();k++)
				{	
					value=mpCannedMapped.get(k).toString();
					sepValue=value.split("#");
					
					String cCode=sepValue[0];
					
					
					if(fb.getCannedChkBox()!=null)
					{
					for(j=0;j<fb.getCannedChkBox().length;j++)
					{
						if(cCode.equals(fb.getCannedChkBox()[j]))
							break;
									
					
					}
					if(j==fb.getCannedChkBox().length)
						deleteCanned.add(cCode);			
					}
					
					else
						deleteCanned.add(cCode);		// all old canned unchecked, and no new added. so delete all
				}
				}
					
			  
			  
			
		 
			labConfigratorMstVO.setLabCode(fb.getLabCode());
			 HelperMethods.populate(labConfigratorMstVO,fb);
			 
			 LabConfigratorMstDATA.saveModifyLabConfigrator(labConfigratorMstVO, userVO,deleteTest,deleteSample,deleteMand,deleteCanned,deleteMacro);
			
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
			objStatus.add(Status.UNSUCESSFULL, "Record Already Mapped","");
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
	public static boolean reFetchModify(LabConfigratorMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LabConfigratorMstVO labConfigratorMstVO = new LabConfigratorMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);		
			
			labConfigratorMstVO.setLabCode(fb.getLabCode());
			LabConfigratorMstDATA.fetchModifyLabConfigrator(labConfigratorMstVO, userVO);
			HelperMethods.populate(fb, labConfigratorMstVO);
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
