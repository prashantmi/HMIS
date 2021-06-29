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
import new_investigation.vo.SampleNoConfigMasterVO;
import new_investigation.vo.SampleNoConfigMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.SampleNoConfigMstDATA;
import new_investigation.masters.controller.data.SampleNoConfigMstDATA;
import new_investigation.masters.controller.fb.SampleNoConfigMstFB;
import new_investigation.masters.controller.fb.SampleNoConfigMstFB;

public class SampleNoConfigMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Laboratory Name","Patient Type","Test Name","Sample Number Format" };
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.SAMPLENOCONFIG.HIVT_SAMPLENO_CONF_MST1").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.SAMPLENOCONFIG.HIVT_SAMPLENO_CONF_MST1.COND.0"));

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
		list.add(InvestigationConfig.IS_VALID_ACTIVE);
		list.add(hosCode);
		

	//	list.add(userVO.getHospitalCode());
		
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"samplenoconfigMst.main.0"));
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
		String masterName = "Sample Number Configuration Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "L.GSTR_LAB_NAME","2", "gnum_pat_type" };
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
		String strSearchField[] = { "1", " UPPER(l.gstr_lab_name)"};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		
		String[] columnHdr = { "Laboratory Name","Patient Type","Test Name","Initialization Type","Number of Sequence Digits","Sample Number Format","From Series","To Series","Initialization Date" ,"Re-Initialization Date"};

		
		viewHdr.add("Laboratory Name");
		viewHdr.add("D");


		viewHdr.add("Patient Type");
		viewHdr.add("D");

		viewHdr.add("Test Name");
		viewHdr.add("D");
		
		viewHdr.add("Initialization Type");
		viewHdr.add("D");
		
		viewHdr.add("Number of Sequence Digits");
		viewHdr.add("D");
		
		viewHdr.add("Sample Number Format");
		viewHdr.add("D");
		
		viewHdr.add("From Series");
		viewHdr.add("D");
		
		viewHdr.add("To Series");
		viewHdr.add("D");
		
		viewHdr.add("Initialization Date");
		viewHdr.add("D");
		
		viewHdr.add("Re-Initialization Date");
		viewHdr.add("D");
		
		
		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "select.samplenoconfig.view.2");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveSampleNoConfig(SampleNoConfigMstFB samplenoconfig_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			SampleNoConfigMasterVO samplenoconfig_VO = new SampleNoConfigMasterVO();
			HelperMethods.populate(samplenoconfig_VO, samplenoconfig_fb);
			
			
			

			SampleNoConfigMstDATA.saveSampleNoConfig(samplenoconfig_VO, userVO);
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


	public static boolean fetchCheckListSampleNoConfig(SampleNoConfigMstFB samplenoconfig_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		SampleNoConfigMasterVO samplenoconfig_VO = new SampleNoConfigMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",hospitalCode="",seqNo="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = samplenoconfig_fb.getChk()[0].replace("$", "@");  //^
			concatid = chk.split("@");
			seqNo=concatid[0];
			labCode = concatid[1];
			
			


			samplenoconfig_VO.setIsActive(samplenoconfig_fb.getIsActive());

		
			samplenoconfig_fb.setSeqNo(seqNo); 
			samplenoconfig_VO.setSeqNo(seqNo);
			samplenoconfig_fb.setLabCode(labCode); 
			samplenoconfig_VO.setLabCode(labCode);
	
			

			mp=SampleNoConfigMstDATA.fetchCheckListSampleNoConfig(samplenoconfig_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			samplenoconfig_fb.setSeqNo(seqNo); 
			samplenoconfig_VO.setSeqNo(seqNo);
			samplenoconfig_fb.setLabCode(labCode); 
			samplenoconfig_VO.setLabCode(labCode);


			HelperMethods.populate(samplenoconfig_fb, samplenoconfig_VO);
			
			
			String labNoFormat=samplenoconfig_fb.getSampleNoFormat();
			String[] dateFormat=labNoFormat.split("&");
			
			if(dateFormat[0].contains("YYYY"))
				samplenoconfig_fb.setYearFormat("2");
			else if(dateFormat[0].contains("YY"))
				samplenoconfig_fb.setYearFormat("1");
			
			if(dateFormat[0].contains("MON"))
				samplenoconfig_fb.setMonthFormat("2");
			else if(dateFormat[0].contains("MM"))
				samplenoconfig_fb.setMonthFormat("1");
			
			if(dateFormat[0].contains("DD"))
				samplenoconfig_fb.setDateFormat("1");
			
			
			
			samplenoconfig_fb.setSelectedChk(samplenoconfig_fb.getChk()[0]);
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


	public static boolean fetchSampleNoConfig(SampleNoConfigMstFB samplenoconfig_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		SampleNoConfigMasterVO samplenoconfig_VO = new SampleNoConfigMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 


			mp=SampleNoConfigMstDATA.fetchSampleNoConfig(samplenoconfig_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			HelperMethods.populate(samplenoconfig_fb, samplenoconfig_VO);

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


	public static boolean savemodifySampleNoConfig(SampleNoConfigMstFB samplenoconfig_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			SampleNoConfigMasterVO samplenoconfig_VO = new SampleNoConfigMasterVO();

			samplenoconfig_VO.setSeqNo(samplenoconfig_fb.getSeqNo());
			HelperMethods.populate(samplenoconfig_VO, samplenoconfig_fb);

			SampleNoConfigMstDATA.savemodifySampleNoConfig(samplenoconfig_VO, userVO);

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

	public static boolean reFetchCheckListSampleNoConfig(SampleNoConfigMstFB samplenoconfig_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		SampleNoConfigMasterVO samplenoconfig_VO = new SampleNoConfigMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap();
			String chk="",labCode="",seqNo="";
			String[] concatid={};

			// Fetching Selected Record Primary Key
			chk = samplenoconfig_fb.getSelectedChk().replace("$", "@");
			concatid = chk.split("@");
			seqNo=concatid[0];
			labCode = concatid[1];
		
		


			samplenoconfig_VO.setIsActive(samplenoconfig_fb.getIsActive());

			samplenoconfig_fb.setSeqNo(seqNo); 
			samplenoconfig_VO.setSeqNo(seqNo);
			samplenoconfig_fb.setLabCode(labCode); 
			samplenoconfig_VO.setLabCode(labCode);
		


			mp=SampleNoConfigMstDATA.fetchCheckListSampleNoConfig(samplenoconfig_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);


			HelperMethods.populate(samplenoconfig_fb, samplenoconfig_VO);
			
			String labNoFormat=samplenoconfig_fb.getSampleNoFormat();
			String[] dateFormat=labNoFormat.split("&");
			
			if(dateFormat[0].contains("YYYY"))
				samplenoconfig_fb.setYearFormat("2");
			else if(dateFormat[0].contains("YY"))
				samplenoconfig_fb.setYearFormat("1");
			
			if(dateFormat[0].contains("MON"))
				samplenoconfig_fb.setMonthFormat("2");
			else if(dateFormat[0].contains("MM"))
				samplenoconfig_fb.setMonthFormat("1");
			
			if(dateFormat[0].contains("DD"))
				samplenoconfig_fb.setDateFormat("1");
			
			

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

	public static boolean getTest(SampleNoConfigMstFB samplenoconfig_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		SampleNoConfigMasterVO samplenoconfig_VO = new SampleNoConfigMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			
			samplenoconfig_VO.setLabCode(samplenoconfig_fb.getLabCode());
			samplenoconfig_VO.setPatientType(samplenoconfig_fb.getPatientType());

			Map mp=new HashMap(); 


			mp=SampleNoConfigMstDATA.getTest(samplenoconfig_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);

			HelperMethods.populate(samplenoconfig_fb, samplenoconfig_VO);
			
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


