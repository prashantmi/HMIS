package new_investigation.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.TestParameterMasterVO;
import hisglobal.vo.TestTemplateVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;

import new_investigation.InvestigationConfig;
import new_investigation.masters.bo.InvestigationMasterBOi;
import new_investigation.masters.controller.data.InvSampleMstDATA;
import new_investigation.masters.controller.fb.InvSampleMstFB;
import new_investigation.masters.controller.data.TestParameterMstDATA;
import new_investigation.masters.controller.fb.TestParameterMstFB;
import new_investigation.masters.dao.InvTestParameterMstDAO;
import new_investigation.masters.dao.InvTestParameterMstDAOi;
import new_investigation.transactions.controller.Helper.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class TestParameterMstUTIL implements MasterInterface
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
		String[] columnHdr = { "Test Name","Test Para Name"};
		return columnHdr;
	}

	public String[] getComboHeader() 
	{		
		String[] strComboHdr = { "1","Test Type","1","Record Status" };
		return strComboHdr;
	}

	public String[] getComboQuery()
	{
		String comboQuery[] = new String[2];
		comboQuery[0] = "0^Test Parameter#1^Department Parameter#2^Requisition Form";
	//	comboQuery[0] = "0^Active";
		comboQuery[1] = "1^Active";
		
		return comboQuery;
	}
	
	public String[] getDeleteQuery()
	{
		String deleteQuery[] = new String[1];
		String seatId = httpSession.getAttribute("SEATID").toString();
         
		
		String	paraType="0";
		if(httpSession.getAttribute("paraType")!=null)
		{
				paraType = httpSession.getAttribute("paraType").toString();
				
		}
		 
		if(paraType.equals("2")) // for requisition form
		{
			deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_reqform_test_parameter_mst").replace("?", seatId);
			deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_reqform_test_parameter_mst.COND.0"));
			
		}
		else if(paraType.equals("1"))
		{
			deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_depttest_parameter_mst").replace("?", seatId);
			deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_reqform_test_parameter_mst.COND.0"));
		
		}
		else if(paraType.equals("0"))
		{
			deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_test_parameter_mst").replace("?", seatId);
			deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_reqform_test_parameter_mst.COND.0"));
		
		}
		else
		{
		/*deleteQuery[0] = new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_test_parameter_mst").replace("?", seatId);
		deleteQuery[0] = deleteQuery[0].concat(" where "+ new_investigation.qryHandler_investigation.getQuery(1,"DELETE.TestParameter.hivt_test_parameter_mst.COND.0"));
		*/}
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
		if (cmb == null) { 
		brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"TestParameter.main.0"));
		brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);
		}
		/*if (cmb != null) {
			if (!cmb[0].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
			}
		}*/

		
		if (cmb != null) {
			if (cmb[0].equals("2")) {    // for requisition form
				List<String> list1 = new ArrayList<String>();
				list1.add(InvestigationConfig.IS_VALID_ACTIVE);
				//list1.add(InvestigationConfig.IS_VALID_ACTIVE);
				//list1.add(InvestigationConfig.IS_VALID_ACTIVE);
				brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"TestTypeWise.main.0"));
				brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list1);
				httpSession.setAttribute("paraType", cmb[0]);
				/*brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("labCode", cmb[0]);*/

			}
			
			if (cmb[0].equals("1")) {
				List<String> list1 = new ArrayList<String>();
				list1.add(InvestigationConfig.IS_VALID_ACTIVE);
				//list1.add(InvestigationConfig.IS_VALID_ACTIVE);
				//list1.add(InvestigationConfig.IS_VALID_ACTIVE);
				brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"DEPT_TypeWise.main.0"));
				brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list1);
				httpSession.setAttribute("paraType", cmb[0]);
				/*brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("labCode", cmb[0]);*/

			}
			
			if (cmb[0].equals("0")) {
				brMainQuery.append(new_investigation.qryHandler_investigation.getQuery(1,"TestParameter.main.0"));
				brMainQuery = HelperMethodsDAO.populateQuery(brMainQuery, list);
				httpSession.setAttribute("paraType", cmb[0]);
				/*brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[0]);
				httpSession.setAttribute("labCode", cmb[0]);*/

			}

		/*if (cmb != null) {
			if (!cmb[1].equals("0")) {
				brMainQuery.replace(brMainQuery.lastIndexOf("1"), brMainQuery
						.lastIndexOf("1") + 1, cmb[1]);
			}
		}*/

	}
		
		
		System.out.println("Main Query;;;;;;;;;"+brMainQuery.toString());
		return brMainQuery.toString();
	}
	

	public String getMasterName()
	{
		String masterName = "Test Parameter Master";
		return masterName;
	}

	
	public String[] getOrderBy()
	{
		String orderBy[] = { "1", "x.gstr_test_name" };
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
		String strSearchField[] = { "1", " UPPER(x.GSTR_TEST_NAME) ", "2", " UPPER(z.HGSTR_PARAMETER) "    };
		return strSearchField;
	}
	
	public List<String> getViewHeader()
	{
		List<String> viewHdr = new ArrayList<String>();
		viewHdr.add("Test Name");
		viewHdr.add("D");

		viewHdr.add("Test Para Name");
		viewHdr.add("D");

		 
		return viewHdr;
	}


	public String getViewQuery()
	{
		String	paraType="0";
		if(httpSession.getAttribute("paraType")!=null)
		{
				paraType = httpSession.getAttribute("paraType").toString();
				
		}
		
		
		if(paraType.equals("2"))
		{return new_investigation.qryHandler_investigation.getQuery(1, "select.reqform.test_parameter.view.2");}
		else if(paraType.equals("1"))
		{return new_investigation.qryHandler_investigation.getQuery(1, "select.DEPTWISE.test_parameter.view.2");}
		else if(paraType.equals("0"))
		{return new_investigation.qryHandler_investigation.getQuery(1, "select.test_parameter.view.2");}
		else
		{
			 return new_investigation.qryHandler_investigation.getQuery(1, "select.test_parameter.view.2");
		}
		/*return new_investigation.qryHandler_investigation.getQuery(1, "select.test_parameter.view.2");*/
	}

	public String isGlobal()
	{
		return "null";

	}
	
	public boolean reportInterFaceRequired()
	{
		return false;
	}
	public static boolean saveTestParameter(TestParameterMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
			
			ControllerUTIL.setSysdate(_request);
			String systemDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			  			  			  		
			HelperMethods.populate(testParameterMasterVO, _fb);
		    
			TestParameterMstDATA.saveTestParameter(testParameterMasterVO, userVO);
			
			saveXML(testParameterMasterVO, session);
			
			objStatus.add(Status.DONE, "Test Parameter Data Saved Successfully", "");
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

	
	private static void saveXML(TestParameterMasterVO formVO, HttpSession session) {
		// TODO Auto-generated method stub
		
		Map<String,TestTemplateVO> testTemplateVOObjectMap=new HashMap<String,TestTemplateVO>();
		System.out.println("test code: " + formVO.getTestCode());
   	   testTemplateVOObjectMap.put(formVO.getTestCode(),new TestTemplateVO());
   	   testTemplateVOObjectMap.get(formVO.getTestCode()).setTestCode(formVO.getTestCode());
   	   testTemplateVOObjectMap.get(formVO.getTestCode()).setParaType(formVO.getParaType());
        testTemplateVOObjectMap=new TemplateProcessingHLP(TemplateProcessingHLP.RESULTENTRYFORM).CreateDocumentTreeForTest(testTemplateVOObjectMap, session);
	}

	public static boolean fetchTestParameter(TestParameterMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			 // Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");
			  
			String testCode = concatid[0];
			String paraName = concatid[1].replace("$", "#").split("#")[0];
			
			// for requisition form
			String paratype  = concatid[2].replace("$", "#").split("#")[0];
			
			fb.setParaType(paratype);
			testParameterMasterVO.setParaType(paratype);
			
			
			
			fb.setTestCode(testCode);
			testParameterMasterVO.setTestCode(testCode);
			
			fb.setParameterCode(paraName);
			testParameterMasterVO.setParameterCode(paraName);
			///labMasterVO.setLabCode(fb.getChk()[0]);
			  
			mp=TestParameterMstDATA.fetchTestParameter(testParameterMasterVO, userVO);
			
			WebUTIL.setMapInSession(mp, _request);
			
			 String property=testParameterMasterVO.getElementProperty();
			 String[] multiple_property=property.split("#");
			 
			 
			 
			 if(testParameterMasterVO.getElementType().equals("H"))
			 {
				 if(multiple_property[0] != null && multiple_property[0].equals("1") == true)
					 testParameterMasterVO.setShowDefaultValue(true);
				 else 
					 testParameterMasterVO.setShowDefaultValue(false);
				 //testParameterMasterVO.setShowDefaultValue();
				 testParameterMasterVO.setRows(multiple_property[1]);
			 testParameterMasterVO.setColumns(multiple_property[2]);
			 testParameterMasterVO.setBold(multiple_property[3]);
			 testParameterMasterVO.setUnderline(multiple_property[4]);
			 }
			 
			 
			 
			 if(testParameterMasterVO.getElementType().equals("B"))
			 { testParameterMasterVO.setBold(multiple_property[0]);
			 testParameterMasterVO.setUnderline(multiple_property[1]);
			 }
			 
			 
			 if(testParameterMasterVO.getElementType().equals("E"))
			 {testParameterMasterVO.setSize(multiple_property[0]);
			 testParameterMasterVO.setMaxlength(multiple_property[1]);
			 testParameterMasterVO.setBold(multiple_property[2]);
			 testParameterMasterVO.setUnderline(multiple_property[3]);
			 if(multiple_property.length>4)
			 testParameterMasterVO.setFunctionality(multiple_property[4]);
			 else
				 ;
			 testParameterMasterVO.setDefaultTextBoxValue(testParameterMasterVO.getTextEditorValue());
			 }
			 if(testParameterMasterVO.getElementType().equals("A"))
			 {testParameterMasterVO.setLabelAlignment(multiple_property[0]);
			 testParameterMasterVO.setBold(multiple_property[1]);
			 testParameterMasterVO.setUnderline(multiple_property[2]);
			 }
			 
			 
			 if(testParameterMasterVO.getElementType().equals("D") || testParameterMasterVO.getElementType().equals("J") || testParameterMasterVO.getElementType().equals("K")||testParameterMasterVO.getElementType().equals("P"))
			 {
				 testParameterMasterVO.setBold(multiple_property[0]);
				 testParameterMasterVO.setUnderline(multiple_property[1]);
			 }
			
			 if(testParameterMasterVO.getElementType().equals("Z"))
			 {
			 testParameterMasterVO.setBold(multiple_property[0]);
			 testParameterMasterVO.setUnderline(multiple_property[1]);
			 //testParameterMasterVO.setDefaultTextBoxValue(testParameterMasterVO.getTextEditorValue());
			 }
			 		 
            testParameterMasterVO.setParentParameter(testParameterMasterVO.getParentParameterId().substring(5, 9)); //parameter code from test+parameter of parent
			testParameterMasterVO.setParentId(testParameterMasterVO.getParentParameterId());
			
			HelperMethods.populate(fb, testParameterMasterVO);
			 fb.setShowDefaultValue(testParameterMasterVO.isShowDefaultValue());
			fb.setSelectedChk(fb.getChk()[0]);
			fb.setParentId(testParameterMasterVO.getParentParameterId());
			HelperMethods.populate(fb, testParameterMasterVO);
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

	public static boolean savemodifyTestParameter(TestParameterMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;
		String isValidType="0";
		HttpSession session = _request.getSession();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
	  
			testParameterMasterVO.setTestCode(_fb.getTestCode()); 
			testParameterMasterVO.setParameterCode(_fb.getParameterCode()); 

			testParameterMasterVO.setElementProperty(_fb.getElementProperty());
			
			
			String parentID=_fb.getParentId();
			
			testParameterMasterVO.setParaType(_fb.getParaType());
			
			HelperMethods.populate(testParameterMasterVO, _fb);
			 System.out.println(testParameterMasterVO.getShowParameterNameasLabel());
			TestParameterMstDATA.savemodifyTestParameter(testParameterMasterVO, userVO);
						
			String elementId = null;
			
			if(_fb.getParentId() != null && _fb.getParentId() != "null")
				elementId = _fb.getParentId()+_fb.getTestCode()+_fb.getParameterCode();
			else
				elementId = _fb.getTestCode()+_fb.getParameterCode();
			
			
			System.out.println("parent id: " + _fb.getParentId() + " parent parameter " + _fb.getParentParameter());
			
			if(_fb.getParentId() != null && _fb.getParentId() != "null")
				elementId = _fb.getParentId()+_fb.getTestCode()+_fb.getParameterCode();
			else
				elementId = _fb.getTestCode()+_fb.getParameterCode();
			
			session.setAttribute("paraType", _fb.getParaType());
			
			System.out.println("parent id: " + _fb.getParentId() + " parent parameter " + _fb.getParentParameter());
			if(testParameterMasterVO.getParaType().equals("2"))
			new TemplateProcessingHLP(TemplateProcessingHLP.RESULTENTRYFORM).updatingDocumentTree(InvestigationConfig.XML_TESTREQUISITIONTEMPLATE, _fb.getTestCode(), elementId, isValidType,session);
			else
				new TemplateProcessingHLP(TemplateProcessingHLP.RESULTENTRYFORM).updatingDocumentTree(InvestigationConfig.XML_TESTTEMPLATE, _fb.getTestCode(), elementId, isValidType,session);
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
	
	 
	
	public static boolean fetchTestParameterCombos(TestParameterMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
 
			mp=TestParameterMstDATA.fetchTestParameterCombos(testParameterMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			testParameterMasterVO.setParaType("0");
			HelperMethods.populate(fb, testParameterMasterVO);
			  
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
	
	
	public static boolean  TestParameter(TestParameterMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
			testParameterMasterVO.setTestCode(_fb.getTestCode());
			session.removeAttribute(InvestigationConfig.PARAMETER_COMBO);
			ControllerUTIL.setSysdate(_request);
			  Map mp=new HashMap(); 
			mp=TestParameterMstDATA.TestParameter(testParameterMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			HelperMethods.populate(testParameterMasterVO, _fb);
			objStatus.add(Status.NEW);
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

	public static boolean refetchTestParameter(TestParameterMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			// Fetching Selected Record Primary Key
						String chk = fb.getSelectedChk().replace("$", "@");  //^
						String[] concatid = chk.split("@");
						String testCode= concatid[0];
						String parameterCode = concatid[1].replace("$", "#").split("#")[0];
				         

						 

						fb.setTestCode(testCode); 
						testParameterMasterVO.setTestCode(testCode); 
						testParameterMasterVO.setParameterCode(parameterCode);  
						
			 
			mp=TestParameterMstDATA.fetchTestParameter(testParameterMasterVO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			
			
			
            testParameterMasterVO.setParentParameter(parameterCode);

            String property=testParameterMasterVO.getElementProperty();
			 String[] multiple_property=property.split("#");
			 
			 if(testParameterMasterVO.getElementType().equals("B"))
			 { testParameterMasterVO.setRows(multiple_property[0]);
			 testParameterMasterVO.setColumns(multiple_property[1]);
			 }
			 
			 
			 if(testParameterMasterVO.getElementType().equals("E"))
			 {testParameterMasterVO.setSize(multiple_property[0]);
			 testParameterMasterVO.setMaxlength(multiple_property[1]);
			 testParameterMasterVO.setBold(multiple_property[2]);
			 testParameterMasterVO.setUnderline(multiple_property[3]);
			 }
			 
			 
			 if(testParameterMasterVO.getElementType().equals("D") || testParameterMasterVO.getElementType().equals("J") || testParameterMasterVO.getElementType().equals("K"))
			 {
			 testParameterMasterVO.setBold(multiple_property[0]);
			 testParameterMasterVO.setUnderline(multiple_property[1]);
			 }
			 
			 
			HelperMethods.populate(fb, testParameterMasterVO);
			 fb.setSelectedChk(chk); 
			  
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
	

/*	
	public static StringBuffer ajaxUrlCombo(TestParameterMstFB fb, HttpServletRequest objRequest_p)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		String strMsgText = "";
		String  strFormate="";
		
		
		try
		{
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			Map mp=new HashMap();
			TestParameterMasterVO onlinePatientvo = new TestParameterMasterVO();
			
			
			mp=TestParameterMstDATA.ajaxUrlCombo(onlinePatientvo, voUser);
			WebUTIL.setMapInSession(mp, objRequest_p);
            
			
			strFormate = OnlinePatientAcceptanceDATA.checkAutoGenFormate(onlinePatientvo, voUser);
			//sbAjaxRes.append("[{isTempSamplePresent:\'");
			 
			sbAjaxRes.append(strFormate);
			
			 
			//sbAjaxRes.append("\'");
			//sbAjaxRes.append("}]");
			
			
		}
		catch (Exception e)
		{
			strMsgText = "SampleCollectionUTIL.checkSampleNoDuplicacy() -> " + e.getMessage();
			//HisException eObj = 
			new HisException("Investigation", "SampleCollectionUTIL.checkSampleNoDuplicacy() --> ", strMsgText);
			//objFB_p.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");
			//eObj = null;
		}
		finally
		{
		}
		return sbAjaxRes;
	}*/


	public static boolean saveTestParameterMasterForm(TestParameterMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean saveFlag = true;
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
			
			ControllerUTIL.setSysdate(_request);
			String systemDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			  
			String sys=(String)session.getAttribute(Config.SYSDATE);
			String time=sys.split(" ")[1];
			  			  			  		
			HelperMethods.populate(testParameterMasterVO, _fb);
		    
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			InvTestParameterMstDAOi TestParameterMstDAOi = new InvTestParameterMstDAO(tx);
			// count=TestParameterMstDAOi.checkDuplicateTestParameterModify(testParameterMasterVO, _UserVO);
			//if(count.equals("0"))
			//{
			
			
			
			List<TestParameterMasterVO> lst=TestParameterMstDAOi.fetchTestParameterForMaster(testParameterMasterVO, userVO);
			
			
			for(int i=0;i<lst.size();i++)
			{
				TestParameterMasterVO vo=lst.get(i);
			
				String tests="";
				if(vo.getMappedTest().equals("0") )
				tests=_fb.getTestCodee();
				else
				tests=vo.getMappedTest()+"@"+_fb.getTestCodee();
				
				vo.setMappedTest(tests);
				vo.setParaType("2"); // req form
				
				vo.setParentParameter(vo.getParameterCode());
				
				vo.setTestCodee(_fb.getTestCodee());
				//TestParameterMstDAOi.updateTestParameter(vo, userVO);
				TestParameterMstDATA.updateTestParameter(vo, userVO);
				vo.setTestCode(_fb.getTestCodee());
				
				vo.setReqMasterFormType(_fb.getReqMasterFormType());
				vo.setMastertestCode(_fb.getMastertestCode());
				
				String val=vo.getParentParameterId();
				val=val.replaceAll(_fb.getMastertestCode(), _fb.getTestCode());
				vo.setParentParameterId(val);
				vo.setParaType("2"); // req form
			TestParameterMstDATA.saveTestParameterMasterForm(vo, userVO);
			}
		//	saveXML(testParameterMasterVO, session);
			
			objStatus.add(Status.DONE, "Test Parameter Data Saved Successfully", "");
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

	

	
	public static boolean fetchTestParameterCombosReqForm(TestParameterMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		TestParameterMasterVO testParameterMasterVO = new TestParameterMasterVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			Map mp=new HashMap(); 
 
			mp=TestParameterMstDATA.fetchTestParameterCombosReqform( userVO);
			WebUTIL.setMapInSession(mp, _request);
			//testParameterMasterVO.setParaType("0");
			//HelperMethods.populate(fb, testParameterMasterVO);
			  
			//objStatus.add(Status.NEW);

			
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


