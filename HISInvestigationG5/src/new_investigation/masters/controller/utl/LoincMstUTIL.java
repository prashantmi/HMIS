package new_investigation.masters.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.data.LabTestSampleGlobalMstDATA;
import new_investigation.masters.controller.data.LoincMstDATA;
import new_investigation.masters.controller.fb.LabTestSampleGlobalMstFB;
import new_investigation.masters.controller.fb.LoincMstFB;
import new_investigation.vo.LabTestSampleMstVO;
import new_investigation.vo.LoincMstVO;

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

public class LoincMstUTIL extends ControllerUTIL implements MasterInterface {

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
		String[] columnHdr = {"Loinc Code","Test Name","Test Sample","Test Para Name","Unit of Measurement"};
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

		deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Loinc_Mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.Loinc_Mst.Cond"));

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
		list.add(Config.IS_VALID_ACTIVE);
		//list.add(Config.SUPER_USER_HOSPITAL_CODE);
		
	
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"loincMst.main.0"));
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
		String masterName = "Loinc Master";
		return masterName;
	}


	public String[] getOrderBy()
	{
		String orderBy[] = { "1","a.hivstr_loinc_string","2","b.gstr_test_name","3","c.gstr_sample_name","4","d.hgstr_parameter","5","e.gstr_uom" };
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
		String strSearchField[] = { "1","UPPER(a.hivstr_loinc_string)","2", " UPPER(b.gstr_test_name)","3","UPPER(c.gstr_sample_name)","4","UPPER(d.hgstr_parameter)","5","e.gstr_uom"};
		return strSearchField;
	}

	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Loinc Code");
		viewHdr.add("D");
		
		viewHdr.add("Test Name");
		viewHdr.add("D");


		viewHdr.add("Parameter Name");
		viewHdr.add("D");
		
		viewHdr.add("Sample Name");
		viewHdr.add("D");
		
		viewHdr.add("UOM Name");
		viewHdr.add("D");

		return viewHdr;
	}


	public String getViewQuery()
	{
		return new_investigation.qryHandler_investigation.getQuery(1, "VIEW.Loinc_Mst");
	}

	public String isGlobal()
	{
		return "null";

	}

	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean fetchLoincMst(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LoincMstVO loincMstVO = new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			mp=LoincMstDATA.fetchLoincMst(loincMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
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
	public static boolean fetchTestPara(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LoincMstVO loincMstVO = new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			String testHashCode=fb.getTestCode();
			String testCode=testHashCode.split("#")[0];
			loincMstVO.setTestCode(testHashCode);
			loincMstVO.setParaCode(fb.getParaCode());
			mp=LoincMstDATA.fetchTestPara(loincMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
			
			//fb.setHmode("ADD");
			
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
	/*public static boolean fetchUOM(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LoincMstVO loincMstVO = new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			loincMstVO.setTestCode(fb.getTestCode());
			loincMstVO.setTestName(fb.getTestName());
			loincMstVO.setParaCode(fb.getParaCode());
			loincMstVO.setParaName(fb.getParaName());
			loincMstVO.setSampleCode(fb.getSampleCode());
			mp=LoincMstDATA.fetchUOM(loincMstVO ,userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
			
			//fb.setHmode("ADD");
			
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
	}*/
	public static boolean getLoinc(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LoincMstVO loincMstVO = new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			loincMstVO.setTestCode(fb.getTestCode());
			loincMstVO.setParaCode(fb.getParaCode());
			loincMstVO.setSampleCode(fb.getSampleCode());
			loincMstVO.setUomCode(fb.getUomCode());
			loincMstVO.setMethodCode(fb.getMethodCode());
			mp=LoincMstDATA.getLoinc(loincMstVO,userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
			
			//fb.setHmode("ADD");
			
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
	public static boolean getSearch(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List lstTest = new ArrayList();
		LoincMstVO loincMstVO = new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap();
			loincMstVO.setLoincCode(fb.getLoincCode());
			loincMstVO.setTestCode(fb.getTestCode());
			loincMstVO.setParaCode(fb.getParaCode());
			loincMstVO.setSampleCode(fb.getSampleCode());
			loincMstVO.setUomCode(fb.getUomCode());
			loincMstVO.setTestName(fb.getTestName());
			loincMstVO.setParaName(fb.getParaName());
			loincMstVO.setSampleName(fb.getSampleName());
			loincMstVO.setUomName(fb.getUomName());
			loincMstVO.setLoincSearch(fb.getLoincSearch());
			loincMstVO.setMethodCode(fb.getMethodCode());
			mp=LoincMstDATA.getSearch(loincMstVO,userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
			
			//fb.setHmode("ADD");
			
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

	public static boolean saveLoinc(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LoincMstVO loincMstVO = new LoincMstVO();
			
			ControllerUTIL.setSysdate(_request);
			HelperMethods.populate(loincMstVO, fb);
		    
			LoincMstDATA.saveLoinc(loincMstVO, userVO);
			objStatus.add(Status.DONE, "Loinc Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL,"Loinc Already Exist","");
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
	public static boolean fetchModifyLoinc(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LoincMstVO  loincMstVO=new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
			String chk = fb.getChk()[0].replace("^", "@");
			String loincCode = chk.replace("$", "#").split("#")[0];
			fb.setLoincCode(loincCode);
			loincMstVO.setLoincCode(loincCode);
			mp=LoincMstDATA.fetchModifyLoinc(loincMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
			  
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
	public static boolean saveModifyLoinc(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			LoincMstVO loincMstVO = new LoincMstVO();
			
			ControllerUTIL.setSysdate(_request);
			HelperMethods.populate(loincMstVO, fb);
		    
			LoincMstDATA.saveModifyLoinc(loincMstVO, userVO);
			objStatus.add(Status.DONE, "Loinc Data Saved Successfully", "");
		}
		catch (HisDuplicateRecordException e)
		{
			saveFlag=false;
			objStatus.add(Status.UNSUCESSFULL,"Loinc Already Exist","");
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
	public static boolean reFetchLoinc(LoincMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		LoincMstVO loincMstVO = new LoincMstVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);		
			Map mp=new HashMap();
			loincMstVO.setLoincCode(fb.getLoincCode());
			mp=LoincMstDATA.fetchModifyLoinc(loincMstVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(fb, loincMstVO);
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
