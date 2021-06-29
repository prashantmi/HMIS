/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: SIDDHARTH SRIVASTAVA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : Template Processing Helper Class
 ## Purpose						        : This class provides functions for generating templates via Test Parameter Master
 ## Date of Creation					: 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


 */

package new_investigation.transactions.controller.Helper;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.TransactionException;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.TestTemplateVO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;

import org.dom4j.io.SAXReader;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TemplateProcessingHLP {
	Document documentObj;
	Document newHorizontalMatrixDocumentObj;
	Document newVerticalMatrixDocumentObj;
	//Connection conn;
	int rowNos = 1;
	int creationMode;
	public final static int RESULTENTRYFORM = 1;
	public final static int TESTREQUISITIONFORM = 2;
	public final static int TESTSAMPLECOLLECTIONFORM = 3;
	public final static int LABREQUISITIONFORM = 4;
	public final static int LABTESTGROUPRESULTENTRYFORM = 5;
	/*
	 * supported modes 1: RESULTENTRYFORM 2: REQUISITIONFORM 3:
	 * SAMPLECOLLECTIONFORM 4: Laboratory RequisitionForm 5: Laboratorytestgroup
	 * resultEntry form
	 */
	String hospitalCode;

	public TemplateProcessingHLP(int mode) {
		creationMode = mode;
		// this.hospitalCode=hospitalCode;
	}

	private int i;

	public Map<String, TestTemplateVO> CreateDocumentTreeForTest(
			Map<String, TestTemplateVO> testTemplateVOObjectMap,
			HttpSession session) {
		System.out.println("CreateDocumentTreeForTest");
		rowNos = 1;
		String queryTestTemplateDetail = "";
		ResultSet rs = null;
		Map returnMap = new HashMap();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		/*
		 * try { Class.forName("oracle.jdbc.driver.OracleDriver"); } catch
		 * (ClassNotFoundException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */
		// String filename =
		// InvestigationConfig.QUERY_FILE_FOR_DAO_INVESTIGATION;
		java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
		/*
		 * String query=
		 * "SELECT LEVEL,view1.parameterCode,view1.testparametername,testparametercode,parentid,criteriacode,testvaluetype,decode(objectid,'B','label','E','textBox','H','textArea','D','select','I','queryValue','J','button','L','imagesection'),functionName,objEvent,objValidationString ,property"
		 * +
		 * " ,isparameternamedisplayed,labelelementdisplaytype,labelAlignment,elementAlignment,defaultValue, isPrintable,queryCode,buttonName,callUrl "
		 * +
		 * " FROM (SELECT   hpm.hgstr_parameter AS testparametername,htpd.gnum_test_parameter_code AS parameterCode, "
		 * + " htpd.gnum_test_parameter_code AS testparametercode, "+
		 * " htpd.gstr_value_obj_id objectid, htpd.gnum_parent_id AS parent, "+
		 * //
		 * "-- Getchildelements (htpd.gnum_test_parameter_code) AS childnodes, "
		 * + " htpd.gnum_criteria_code AS criteriacode, "+
		 * " htpd.gbl_value_type AS testvaluetype,DECODE(htpd.GNUM_PARENT_ID,htpd.GNUM_TEST_PARAMETER_CODE,NULL,htpd.GNUM_PARENT_ID) AS parentid ,GSTR_FUNCTION_NAME as functionName"
		 * +
		 * " ,HIVTNUM_OBJEVENT AS objEvent, HIVTNUM_VALIDATION_FUNC as objValidationString,htpd.HIVSTR_ELEMENT_PROPERTY as property "
		 * +
		 * " ,hivtnum_para_namedisplay AS isparameternamedisplayed,hivtnum_label_eledisplay_type AS labelelementdisplaytype, hivtstr_label_align as labelAlignment,hivtstr_element_align as elementAlignment"
		 * +
		 * " ,HIVTSTR_DEFAULTVALUE as defaultValue,HIVT_NUM_ELEMENT_SEQUENCE as se ,htpd.GNUM_ISPRINTABLE as isPrintable,htpd.HIVTSTR_QUERY as queryCode,HIVTSTR_BUTTON_NAME as buttonName,HIVTSTR_CALL_URL as callUrl	 FROM HIVT_TEST_PARAMETER_DTL htpd, HIVT_PARAMETER_MST hpm "
		 * + " WHERE SUBSTR (htpd.gnum_test_parameter_code, 1, 4) = ? "+
		 * " AND SUBSTR (htpd.gnum_test_parameter_code, 5, 4) = hpm.hgnum_parameter_id and htpd.GNUM_HOSPITAL_CODE="
		 * +hospitalCode+
		 * " and HPM.GNUM_HOSPITAL_CODE=HTPD.GNUM_HOSPITAL_CODE  AND htpd.GNUM_ISVALID=1 and hpm.GNUM_ISVALID=1 order by htpd.HIVT_NUM_ELEMENT_SEQUENCE) view1 CONNECT BY PRIOR view1.parameterCode=view1.parentid START WITH view1.parentid IS NULL "
		 * ;
		 */

		/*
		 * String query_old = "SELECT LEVEL," + "view1.parameterCode," +
		 * "view1.testparametername," + "testparametercode," + "parentid," +
		 * "criteriacode," + "testvaluetype, " +
		 * "decode(objectid,'B','label','E','textBox','H','textArea','D','select','I','queryValue','J','button','L','imagesection'),"
		 * + "functionName,objEvent," + "property," +
		 * "isparameternamedisplayed," + "labelAlignment," + "elementAlignment,"
		 * + "isPrintable," + "queryCode," + "buttonName," + "callUrl " +
		 * "FROM (SELECT (Select hgstr_parameter from hivt_parameter_mst where gnum_isvalid=1"
		 * +
		 * " and hgnum_parameter_id=a.gnum_parameter_code ) AS testparametername,a.gnum_test_code "
		 * +
		 * "|| a.gnum_parameter_code as parameterCode,a.gnum_test_code || a.gnum_parameter_code as testparametercode,"
		 * + "a.gstr_element_type objectid, " + "a.gnum_parent_id AS parent, " +
		 * "a.gnum_criteria_code AS criteriacode, " +
		 * "a.gnum_arrange_as AS testvaluetype," +
		 * "DECODE(a.GNUM_PARENT_ID,(concat(a.gnum_test_code,a.gnum_parameter_code)),NULL,a.GNUM_PARENT_ID) AS parentid,"
		 * + "GSTR_FUNCTION_NAME as functionName," +
		 * "HIVTNUM_OBJEVENT AS objEvent," +
		 * "a.HIVSTR_ELEMENT_PROPERTY as property, " +
		 * "hivnum_paraname_displayaslabel AS isparameternamedisplayed," +
		 * " hivtstr_label_align as labelAlignment," +
		 * "hivtstr_element_align as elementAlignment," +
		 * "HIVT_NUM_ELEMENT_SEQUENCE as se," +
		 * "a.GNUM_ISPRINTABLE as isPrintable," +
		 * "a.HIVTSTR_QUERY as queryCode," +
		 * "HIVTSTR_BUTTON_NAME as buttonName," + "HIVTSTR_CALL_URL as callUrl"
		 * + " FROM HIVT_TEST_PARAMETER_MST a" + " WHERE  a.gnum_test_code = ?"
		 * + "	 AND a.GNUM_ISVALID=1 order by a.HIVT_NUM_ELEMENT_SEQUENCE) " +
		 * "view1 CONNECT BY PRIOR" + " view1.parameterCode=view1.parentid " +
		 * "START WITH view1.parentid IS NULL  order by se ";
		 */

		/*
		 * String query = "SELECT LEVEL," + "view1.parameterCode," +
		 * "view1.testparametername," + "testparametercode," + "parentid," +
		 * "criteriacode," + "testvaluetype, " +
		 * "decode(objectid,'B','label','E','textBox','H','textArea','D','select','I','queryValue','J','button','L','imagesection'),"
		 * + "functionName,objEvent," + "property," +
		 * "isparameternamedisplayed," + "labelAlignment," + "elementAlignment,"
		 * + "isPrintable," + "queryCode," + "buttonName," + "callUrl " +
		 * "FROM (SELECT (Select hgstr_parameter from hivt_parameter_mst where gnum_isvalid=1"
		 * +
		 * " and hgnum_parameter_id=a.gnum_parameter_code ) AS testparametername,a.gnum_test_code "
		 * +
		 * "|| a.gnum_parameter_code as parameterCode,a.gnum_test_code || a.gnum_parameter_code as testparametercode,"
		 * + "a.gstr_element_type objectid, " + "a.gnum_parent_id AS parent, " +
		 * "a.gnum_criteria_code AS criteriacode, " +
		 * "a.gnum_arrange_as AS testvaluetype," +
		 * "DECODE(a.GNUM_PARENT_ID,(concat(a.gnum_test_code,a.gnum_parameter_code)),NULL,a.GNUM_PARENT_ID) AS parentid,"
		 * + "GSTR_FUNCTION_NAME as functionName," +
		 * "HIVTNUM_OBJEVENT AS objEvent," +
		 * "a.HIVSTR_ELEMENT_PROPERTY as property, " +
		 * "hivnum_paraname_displayaslabel AS isparameternamedisplayed," +
		 * " hivtstr_label_align as labelAlignment," +
		 * "hivtstr_element_align as elementAlignment," +
		 * "HIVT_NUM_ELEMENT_SEQUENCE as se," +
		 * "a.GNUM_ISPRINTABLE as isPrintable," +
		 * "a.HIVTSTR_QUERY as queryCode," +
		 * "HIVTSTR_BUTTON_NAME as buttonName," + "HIVTSTR_CALL_URL as callUrl"
		 * + " FROM HIVT_TEST_PARAMETER_MST a" + " WHERE  a.gnum_test_code = ?"
		 * + "	 AND a.GNUM_ISVALID=1 order by a.HIVT_NUM_ELEMENT_SEQUENCE) " +
		 * "view1 CONNECT BY PRIOR" + " view1.parameterCode=view1.parentid " +
		 * "START WITH view1.parentid IS NULL  order by se "; String
		 * queryKeyTestTemplateDetail =
		 * "SELECT.HIVT_TEST_TEMPLATE_DTL.TESTPARAMETERDETAIL_MODIFIED";
		 */

		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey =""; 
		String paratype="";
		

		for (TestTemplateVO testTemplateVOObj : testTemplateVOObjectMap
				.values()) {
			paratype=testTemplateVOObj.getParaType();
		}
		if(paratype.equals("0"))
		queryKey =	"SELECT.TEMPLATE_DATA.TEST_PARAMETER_DETAIL";
		else if(paratype.equals("1"))
			queryKey =	"SELECT.TEMPLATE_DATA.DEPTTEST_PARAMETER_DETAIL";
		else if(paratype.equals("2")) // for requisition form also
			queryKey =	"SELECT.TEMPLATE_DATA.REQFORM_TEST_PARAMETER_MST";
		
		Connection conn = null;
		try {
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			tx.begin();
			 conn = tx.getConnection();
			// queryTestTemplateDetail =
			// InvestigationQueryHandler.getTransactionQuery(queryKeyTestTemplateDetail,
			// "");
			// System.out.println("Query :TEMPLATE QUERY "+queryTestTemplateDetail);

			// Statement st=conn.createStatement();

			// ResultSet rs=st.execute(query);
			 //Map for putting the criteria Code
		
			for (TestTemplateVO testTemplateVOObj : testTemplateVOObjectMap
					.values()) {
				System.out.println("CreateDocumentTreeForTest step 1");
				Map populateMAP = new HashMap();
				Sequence sq = new Sequence();
				populateMAP.put(sq.next(), testTemplateVOObj.getTestCode());
				// PreparedStatement pstmt = conn.prepareStatement(query);
				// pstmt.setString(1,testTemplateVOObj.getTestCode());
				System.out.println(testTemplateVOObj.getTestCode());
				System.out.println("step 2" + query);
				// rs=pstmt.executeQuery();
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				//

				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory
						.newDocumentBuilder();

				Document documentObj = documentBuilder.newDocument();

				documentObj.setXmlStandalone(true);

				Element root = documentObj.createElement("TestTemplate");
				root.setAttribute("testCode", testTemplateVOObj.getTestCode());
				root.setAttribute("parameterName",
						testTemplateVOObj.getTestName());
				root.setAttribute("parameterCode", "");
				root.setAttribute("parentParameterCode", "");
				root.setAttribute("levelNo", "0");
				root.setAttribute("removeStatus", "0");
				HashMap<String, Node> parentMap = new HashMap();
				documentObj.appendChild(root);
				Node parent = root;
				// parentMap.put(getParentParamCode(parent), parent);
				Element previousElement = root;
				Element rootElement = root;
				int maxLevelNo = 0;
				
				while (rs.next()) {
					Element element = documentObj.createElement("testElement");

					String levelNo = rs.getString(1);
					int lNo = Integer.parseInt(levelNo);
					if (maxLevelNo < lNo)
						maxLevelNo = lNo;
					element.setAttribute("levelNo", levelNo);
					String parentid = rs.getString(5);

					element.setAttribute("parameterCode", rs.getString(4));

					element.setAttribute("parameterName", rs.getString(3));
					element.setAttribute("CriteriaCode", rs.getString(6));
				
					element.setAttribute("valueType", rs.getString(7));// represents
																		// the
																		// type
																		// of
																		// template(simple,horizontal,vertical)
					String objId =  rs.getString(8);
					element.setAttribute("objectId", rs.getString(8));
					element.setAttribute("removeStatus", "0");
					element.setAttribute("functionName", rs.getString(9));
					element.setAttribute("eventName", rs.getString(10));
					element.setAttribute("eventValidationString", rs.getString(9)); // not
																		// in
																		// new
																		// database

					element.setAttribute("property", rs.getString(11));
					element.setAttribute("isparameternamedisplayed",
							rs.getString(12));
					element.setAttribute("labelelementdisplaytype", "false"); // not
																				// in
																				// new
																				// database,
																				// so
																				// please
																				// check
					element.setAttribute("labelAlignment", rs.getString(13));
					element.setAttribute("elementAlignment", rs.getString(14));
					String defaultValue = "-";
					if(rs.getString(19) != null && rs.getString(19).length() > 0 )
						defaultValue = rs.getString(19);
					element.setAttribute("defaultValue", defaultValue);  // not in new
																// database, so
																// please check
					element.setAttribute("isPrintable", rs.getString(15));
					element.setAttribute("queryCode", rs.getString(16));
					element.setAttribute("buttonName", rs.getString(17));
					element.setAttribute("callUrl", rs.getString(18));
					/*added by chandan gupta*/
					element.setAttribute("hypername", rs.getString(20));
					parentMap.put(rs.getString(4), element);
					
					// System.out.println("Parent level No. - "+(Integer.parseInt(parent.getAttributes().getNamedItem("levelNo").getNodeValue())+"  levelNo.  -"+Integer.parseInt(element.getAttribute("levelNo"))));
					if (parentid == null) {
						parent = rootElement;
					} else {
						if (parentMap.size() > 0)// &&
													// Integer.parseInt(previousElement.getAttribute("levelNo"))
													// != 0)
						{
							Node p = parentMap.get(parentid);
							if (p != null)
								parent = p;
							else
								parent = rootElement;
						}

					}

					/*
					 * if(Integer.parseInt(parent.getAttributes().getNamedItem(
					 * "levelNo").getNodeValue())<
					 * Integer.parseInt(element.getAttribute("levelNo"))) {
					 * if(Integer
					 * .parseInt(previousElement.getAttribute("levelNo"
					 * ))==Integer.parseInt(element.getAttribute("levelNo")) )
					 * parent=parent; else if(parentid != null &&
					 * parentMap.size() >0 )// &&
					 * Integer.parseInt(previousElement.getAttribute("levelNo"))
					 * != 0) { Node p = parentMap.get(parentid); if(p!= null)
					 * parent = p; else parent = previousElement; } else {
					 * parent = previousElement; }
					 * 
					 * 
					 * }
					 */
					/*
					 * else
					 * if(Integer.parseInt(parent.getAttributes().getNamedItem
					 * ("levelNo").getNodeValue())==
					 * Integer.parseInt(element.getAttribute("levelNo"))) {
					 * parent=parent.getParentNode(); }
					 */
					/*
					 * else {
					 * System.out.println("loop 1 :"+parent.getAttributes(
					 * ).getNamedItem
					 * ("levelNo")+" parent :"+parent.getAttributes
					 * ().getNamedItem("parameterName").getNodeValue());
					 * parent=getMatchingParentElement(parent,element);
					 * System.out
					 * .println("loop 1 :"+parent.getAttributes().getNamedItem
					 * ("levelNo"
					 * )+" parent :"+parent.getAttributes().getNamedItem
					 * ("parameterName").getNodeValue());
					 * 
					 * }
					 */

					// System.out.println("parent name "+parent.getNodeName());
					// System.out.println("loop 1 "+" parent "+parent.getAttributes().getNamedItem("parameterName").getNodeValue()+"  child level No.="+element.getAttributes().getNamedItem("parameterName"));

					element.setAttribute(
							"parentParameterCode",
							(parent.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue() + parent.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue()));
					parent.appendChild((Node) element);

					previousElement = element;

				}

				rs.close();
				// pstmt.close();

				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				// transformer.transform( new DOMSource(documentObj), new
				// StreamResult("c:/template/A.xml"));
				this.documentObj = documentObj;
				// System.out.println(documentObj);

				Document newDocumentObjectHM = documentBuilder.newDocument();
				this.newHorizontalMatrixDocumentObj = newDocumentObjectHM;

				Document newDocumentObjectSM = documentBuilder.newDocument();
				Element rootSM = newDocumentObjectSM
						.createElement("TestTemplateSM");
				rootSM.setAttribute("testCode", testTemplateVOObj.getTestCode());
				rootSM.setAttribute("parameterName",
						testTemplateVOObj.getTestName());
				rootSM.setAttribute("parameterCode", "");
				rootSM.setAttribute("parentParameterCode", "");
				rootSM.setAttribute("levelNo", "0");
				rootSM.setAttribute("removeStatus", "0");
				newDocumentObjectSM.appendChild(rootSM);

				Document newDocumentObjectVM = documentBuilder.newDocument();
				Element rootHM = newDocumentObjectHM
						.createElement("TestTemplateHM");
				rootHM.setAttribute("testCode", testTemplateVOObj.getTestCode());
				rootHM.setAttribute("parameterName",
						testTemplateVOObj.getTestName());
				rootHM.setAttribute("parameterCode", "");
				rootHM.setAttribute("parentParameterCode", "");
				rootHM.setAttribute("levelNo", "0");
				rootHM.setAttribute("removeStatus", "0");
				newDocumentObjectHM.appendChild(rootHM);

				Element rootVM = newDocumentObjectVM
						.createElement("TestTemplateHM");
				rootVM.setAttribute("testCode", testTemplateVOObj.getTestCode());
				rootVM.setAttribute("parameterName",
						testTemplateVOObj.getTestName());
				rootVM.setAttribute("parameterCode", "");
				rootVM.setAttribute("parentParameterCode", "");
				rootVM.setAttribute("levelNo", "0");
				rootVM.setAttribute("removeStatus", "0");
				newDocumentObjectVM.appendChild(rootVM);

				// createAllTypesTreeFromOriginalTree((Node)root,(Node)rootHM,(Node)rootVM,documentObj,newDocumentObjectHM,newDocumentObjectVM);
				Node clonedNodeSM = newDocumentObjectSM.importNode((Node) root,
						true);
				rootSM.appendChild(clonedNodeSM);

				Node clonedNodeHM = newDocumentObjectHM.importNode((Node) root,
						true);
				rootHM.appendChild(clonedNodeHM);
				Node clonedNodeVM = newDocumentObjectVM.importNode((Node) root,
						true);
				rootVM.appendChild(clonedNodeVM);

				/* creating the simple matrix */
				System.out
						.println("<---------------------Creating Simple Matrix------------->");
				createSimpleMatrix(clonedNodeSM, rootSM);
				/* creating the Horizontal matrix */
				System.out
						.println("<---------------------Creating Horizontal Matrix------------->");
				createHorizontalMatrix(clonedNodeHM, rootHM);
				/* creating the Vertical matrix */
				System.out
						.println("<---------------------Creating Vertical Matrix------------->");
				createVerticalMatrix(clonedNodeVM, rootVM);

				// transformer.transform( new DOMSource(newDocumentObjectSM),
				// new StreamResult("c:/template/SM.xml"));
				// transformer.transform( new DOMSource(newDocumentObjectHM),
				// new StreamResult("c:/template/HM.xml"));
				// transformer.transform( new DOMSource(newDocumentObjectVM),
				// new StreamResult("c:/template/VM.xml"));
				this.documentObj = documentObj;

				Document actualdocumentObj = null;
				try {
					InputStream ip = null;
					
					if(paratype.equals("0"))
					ip=InvestigationTemplateDataHelper
							.getInstance()
							.getClobObjectFromDatabaseForResultEntryFormDocument();
					else if(paratype.equals("1"))
						ip=InvestigationTemplateDataHelper
								.getInstance()
								.getClobObjectFromDatabaseForDepartmentResultEntryFormDocument();
					else if(paratype.equals("2"))
						ip=InvestigationTemplateDataHelper
								.getInstance()
								.getClobObjectFromDatabaseForRequisitionFormResultEntryFormDocument();
					if (ip != null)
						actualdocumentObj = documentBuilder.parse(ip);
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}

				if (actualdocumentObj == null) {
					actualdocumentObj = documentBuilder.newDocument();
				}

				// Element
				// rootHTML=newDocumentObjectHTML.createElement("templates");

				Element newTestHTML = null;
				Node actualRootElement = null;
				NodeList templatesList = actualdocumentObj
						.getElementsByTagName("templates");
				System.out.println("tempalte list size "
						+ templatesList.getLength());

				if (templatesList.getLength() != 0) {
					actualRootElement = templatesList.item(0);
					XPathFactory factory = XPathFactory.newInstance();
					XPath xpath = factory.newXPath();
					System.out.println("testtemplate[@labTestCode='"
							+ testTemplateVOObj.getTestCode() + "']");
					XPathExpression expr = xpath
							.compile("testtemplate[@labTestCode='"
									+ testTemplateVOObj.getTestCode() + "']");
					Object result = expr.evaluate(actualRootElement,
							XPathConstants.NODESET);
					NodeList nodes = (NodeList) result;
					System.out.println(nodes.getLength());
					if (nodes == null || nodes.getLength() == 0) {
						newTestHTML = actualdocumentObj
								.createElement("testtemplate");
					} else {
						for (int i = 0; i < nodes.getLength(); i++) {
							System.out.println(nodes.getLength()
									+ "removing existing template" + i);
							actualRootElement.removeChild(nodes.item(i));
						}

						newTestHTML = actualdocumentObj
								.createElement("testtemplate");
					}

					actualRootElement.appendChild(newTestHTML);

				} else {
					actualRootElement = actualdocumentObj
							.createElement("templates");
					newTestHTML = actualdocumentObj
							.createElement("testtemplate");
					actualRootElement.appendChild(newTestHTML);
					actualdocumentObj.appendChild(actualRootElement);
				}

				newTestHTML.setAttribute("labTestCode",
						testTemplateVOObj.getTestCode());
				Element tableHTML = actualdocumentObj.createElement("table");
				newTestHTML.appendChild(tableHTML);
				tableHTML.setAttribute("width", "100%");
				tableHTML.setAttribute("type", "1");
				// rootHTML.appendChild(newTestHTML);
				createHorizontalMatrixHtml(clonedNodeHM, tableHTML,
						actualdocumentObj, newDocumentObjectHM);
				tableHTML = actualdocumentObj.createElement("table");
				newTestHTML.appendChild(tableHTML);
				tableHTML.setAttribute("width", "100%");
				tableHTML.setAttribute("type", "3");
				createVerticalMatrixHtml(clonedNodeVM, tableHTML,
						actualdocumentObj, newDocumentObjectVM);
				tableHTML = actualdocumentObj.createElement("table");
				newTestHTML.appendChild(tableHTML);
				tableHTML.setAttribute("width", "100%");
				tableHTML.setAttribute("type", "2");
				int maxColumns = getMaximumNoOFColumnsSimpleHTML(clonedNodeSM,
						0);
				System.out.println("createSimpleTemplateHtml maxColumns  ="
						+ maxColumns);

				if (maxColumns < InvestigationConfig.minimumNoOfColumnsinSimpleTemplate) {
					if (maxColumns != 1) {
						maxColumns = maxColumns + 1;
					}
				}
				
				if(maxLevelNo == 1)
				{
					maxColumns = 2;
				}

				
				tableHTML.setAttribute("maxColumns", "" + maxColumns);
				createSimpleTemplateHtml(clonedNodeSM, tableHTML,
						actualdocumentObj, newDocumentObjectSM, maxColumns);

				// transformer.transform( new DOMSource(actualdocumentObj), new
				// StreamResult(path+"testtemplate.xml"));
				Source domSource = new DOMSource(actualdocumentObj);

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);

				System.out.println("XML Create Function: ");
				System.out.println(baos.toString());
			//	String testcode=testTemplateVOObj.getTestCode(); 
			//	loadtodbintestcase(actualdocumentObj,testcode);

			}
			
			/*modification for department template. puneet*/
			/*modification for requisition form template. chandan*/
			if(paratype.equals("0"))
			loadDocumentStringToDatabase(
					InvestigationConfig.XML_TESTTEMPLATE,
					"testTemplate.xml", "testTemplate.xml",
					baos.toString(), session);
			else if(paratype.equals("1"))
				loadDocumentStringToDatabase(
						InvestigationConfig.XML_DEPARTMENT_TESTTEMPLATE,
						"testTemplate.xml", "testTemplate.xml",
						baos.toString(), session);
			else if(paratype.equals("2"))         // requisition form
				loadDocumentStringToDatabase(
						InvestigationConfig.XML_TESTREQUISITIONTEMPLATE,
						"testTemplate.xml", "testTemplate.xml",
						baos.toString(), session);
			else
				;//requisition form 
			
			
			System.out.println("CreateDocumentTreeForTest::after Save");

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				conn.close();
				//tx.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnMap;
	}

	private String getParentParamCode(Element parent) {
		if (parent == null)
			return null;
		String value = parent.getAttributes().getNamedItem("parameterCode")
				.getNodeValue();
		if (value.equals("") == true)
			value = "0";
		return value;
	}

	public void loadDocumentStringToDatabase(String xmlTesttemplateId,
			String xmlTesttemplateName, String xmlTesttemplateRemarks,
			String templateFileString, HttpSession session) {
		System.out.println("Loading .... loadDocumentStringToDatabase"
				+ templateFileString);

		/*
		 * try { // Class.forName(InvestigationConfig.databaseDriver); } catch
		 * (ClassNotFoundException e1) { e1.printStackTrace(); }
		 */

		Connection connection = null;
		CallableStatement cstmt = null;
		JDBCTransactionContext tx = null;
		try {
			// connection =
			// DriverManager.getConnection(Conn.getDatabaseConnection(),InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
			// connection.setAutoCommit(false);

			tx = new JDBCTransactionContext();
			tx.begin();
			connection = tx.getConnection();
			
			cstmt = connection
					.prepareCall("{call pkg_inv_template.loaddocument(?,?,?,?,?)}");

			cstmt.setString(1, xmlTesttemplateId);
			cstmt.setString(2, xmlTesttemplateName);
			cstmt.setString(3, xmlTesttemplateRemarks);
			cstmt.setString(4, templateFileString);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			// cstmt.setString(6, ""+hospitalCode);
			cstmt.execute();

		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				cstmt.close();
				connection.commit();
				connection.close();
				
			//	tx.close();
				// commented by Siddharth, 27/03/2015
				// InvestigationDocumentCacheManager.getInstance().loadDocumentObj(xmlTesttemplateId,""+hospitalCode);
				InvestigationTemplateDataHelper.getInstance().loadDocumentObj(
						xmlTesttemplateId, session);
				System.out
						.println("document uploading successfull to datadase");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println("success .... loadDocumentStringToDatabase");

	}

	
	
//	private int getColumnWidth(int colNo, int maxColNo)
//	{
//		int labelColumnWidth = 10;
//		int lastColumnWidth = 100 - labelColumnWidth;
//		if(colNo < maxColNo)
//			return labelColumnWidth;
//		else 
//			return lastColumnWidth;
//	}
	
	
	private int getColumnWidth(int colNo, int maxColNo, int maxColWidth)
	{
		int labelColumnWidth = 10;
		int lastColumnWidth = maxColWidth - labelColumnWidth;		
			
		if(colNo < maxColNo)
			return labelColumnWidth;
		else 
			return lastColumnWidth;
	}
	
	private void createSimpleTemplateHtml(Node clonedNodeSM,
			Node templateElement, Document newDocumentObjectHTML,
			Document newDocumentObjectHM, int maxColumns) {
		int maxWidth = 100;
		rowNos = 1;
		if (clonedNodeSM.hasChildNodes()) {
			
			//int columnWidth = 100 / maxColumns;
			//columnWidth = 20;
			
			NodeList childNodeList = clonedNodeSM.getChildNodes();
			int rowNo = 1;

			for (int i = 0; i < childNodeList.getLength(); i++) {
				int columnNo = 1;
				String classString = "";
				Node child = childNodeList.item(i);
				Element rowDetailsElement = newDocumentObjectHTML
						.createElement("rowDetails");
				System.out.println("Assigning Row No :->" + rowNo);
				rowDetailsElement.setAttribute("rowNo", "" + rowNos++);

				int levelNo = Integer.parseInt(child.getAttributes()
						.getNamedItem("levelNo").getNodeValue());
				System.out.println("levelNo  " + levelNo);
				for (int level = 1; level < levelNo; level++) {
					if (columnNo / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, maxWidth)
							+ "%");
					columnDetailsElement.setAttribute("align", "center");

					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					tableElement.setAttribute("width", "100%");
					tableElement.setAttribute("cellspacing", "0");
					tableElement.setAttribute("cellpadding", "0");
					tableElement.setAttribute("border", "0");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");

					Element elementElement = newDocumentObjectHTML
							.createElement("element");// element Element
					elementElement.setAttribute("id", "");
					elementElement.setAttribute("name", "");
					elementElement.setAttribute("type", "");
					elementElement.setAttribute("idC", "");
					elementElement.setAttribute("value", "");
					/*added by chandan gupta*/
					elementElement.setAttribute("hyperlinkname", "");
					elementElement.setAttribute("val", "");
					elementElement.setAttribute("tbl", "");
					elementElement.setAttribute("checked", "");
					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);

					columnDetailsElement.appendChild(tableElement);// add
																	// element
																	// to the
																	// columndetails
																	// element
					rowDetailsElement.appendChild(columnDetailsElement);
				}

				System.out.println("objectID "
						+ child.getAttributes().getNamedItem("objectId")
								.getNodeValue());

				if (child.getAttributes().getNamedItem("objectId")
						.getNodeValue().equals("label") == false) {
					// element is not a label

					if (columnNo / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, maxWidth)
							+ "%");
					System.out.println("object name = "
							+ child.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());
					columnDetailsElement.setAttribute("align", child
							.getAttributes().getNamedItem("labelAlignment")
							.getNodeValue());

					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");

					/*if (child.getAttributes()
							.getNamedItem("isparameternamedisplayed")
							.getNodeValue().equals("1"))*/
					if (child.getAttributes()
							.getNamedItem("isparameternamedisplayed")
							.getNodeValue() != null)
					{
						// parameter name is displayed as label with elemnt
						// itself
						// Siddharth Srivastava
						// 28/03/2015

						if (child.getAttributes()
								.getNamedItem("labelelementdisplaytype")
								.getNodeValue().equals("0") == false) {
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							Element elementElement = newDocumentObjectHTML
									.createElement("element");
							elementElement.setAttribute("idC", "label");
							elementElement.setAttribute("name", "");
							
							String displayValue =  child.getAttributes()
									.getNamedItem("isparameternamedisplayed")
									.getNodeValue();
							elementElement.setAttribute("display",displayValue);
							
							String elementValue = "";
							if(displayValue.equals("1"))
								elementValue = child.getAttributes()
										.getNamedItem("parameterName")
										.getNodeValue();
							
							
							
							elementElement.setAttribute(
									"value",elementValue
									);
							elementElement.setAttribute("type", "");
							//System.out.println("SET BOLD N UNDERLINE");
							setBoldAndUnderlinePropertiesForParameterLabel(
									elementElement, child);

							elementElement.setAttribute(
									"align",
									child.getAttributes()
											.getNamedItem("labelAlignment")
											.getNodeValue());
							System.out
									.println("isparameternamedisplayed[~label]   ="
											+ child.getAttributes()
													.getNamedItem(
															"isparameternamedisplayed")
													.getNodeValue());
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

							for (int j = columnNo; j < maxColumns; j++) {
								 if(columnNo/2!=0)
								 classString="tdfonthead";
								 else
								 classString="tdfont";
								
								 columnDetailsElement=newDocumentObjectHTML.createElement("columnDetails");
								 columnDetailsElement.setAttribute("colNo",
								 ""+j);
								 columnDetailsElement.setAttribute("class",
								 classString);
//								 columnDetailsElement.setAttribute("width",
//								 ""+columnWidth+"%");
								 columnDetailsElement.setAttribute("width", "" + getColumnWidth(j, maxColumns, maxWidth)
											+ "%");
								 columnDetailsElement.setAttribute("align",
								 "center");
								 tableElement=newDocumentObjectHTML.createElement("table");
								 tableElement.setAttribute("width","100%");
								 tableElement.setAttribute("cellspacing","0");
								 tableElement.setAttribute("cellpadding","0");
								 tableElement.setAttribute("border","0");
								 rowElement=newDocumentObjectHTML.createElement("tr");
								 columnElement=newDocumentObjectHTML.createElement("td");
								
								 elementElement=newDocumentObjectHTML.createElement("element");//element Element 
								 elementElement.setAttribute("id", "");
								 elementElement.setAttribute("name","");
								 elementElement.setAttribute("type", "");
								 elementElement.setAttribute("idC","");
								 elementElement.setAttribute("value","");
								 columnElement.appendChild(elementElement);
								 rowElement.appendChild(columnElement);
								 tableElement.appendChild(rowElement);
								 columnDetailsElement.appendChild(tableElement);//add element to the columndetails element
								 rowDetailsElement.appendChild(columnDetailsElement);
								columnNo++;// incrementing columnNO
							}

							// adding control element at the last column
							elementElement = newDocumentObjectHTML
									.createElement("element");
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", ""
									+ columnNo++);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, maxWidth)
									+ "%");
							columnDetailsElement.setAttribute("align", "left");

							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							elementElement = newDocumentObjectHTML
									.createElement("element");
							
							elementElement.setAttribute("displayParam", displayValue);
							createElementInHTMLForm(elementElement, child);

							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

						} else {
							// element label display distance is more than
							// expected

							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							Element elementElement = newDocumentObjectHTML
									.createElement("element");
							elementElement.setAttribute("idC", "label");
							elementElement.setAttribute("name", "");
							elementElement.setAttribute(
									"value",
									child.getAttributes()
											.getNamedItem("parameterName")
											.getNodeValue());
							elementElement.setAttribute("type", "");
							elementElement.setAttribute(
									"align",
									child.getAttributes()
											.getNamedItem("labelAlignment")
											.getNodeValue());
							elementElement
									.setAttribute(
											"isPrintable",
											child.getAttributes()
													.getNamedItem("isPrintable")
													.getNodeValue());
							setBoldAndUnderlinePropertiesForParameterLabel(
									elementElement, child);

							System.out
									.println("isparameternamedisplayed[~label distance>0]   ="
											+ child.getAttributes()
													.getNamedItem(
															"isparameternamedisplayed")
													.getNodeValue());
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

							int distance = Integer.parseInt(child
									.getAttributes()
									.getNamedItem("labelelementdisplaytype")
									.getNodeValue());
							System.out.println("element name "
									+ child.getAttributes()
											.getNamedItem("parameterName")
											.getNodeValue() + "distance  ="
									+ distance);
							for (int cdistance = 0; (cdistance < distance && columnNo < maxColumns); cdistance++) {
								columnDetailsElement = newDocumentObjectHTML
										.createElement("columnDetails");
								columnDetailsElement.setAttribute("colNo", ""
										+ columnNo++);
								columnDetailsElement.setAttribute("class",
										classString);
//								columnDetailsElement.setAttribute("width", ""
//										+ columnWidth + "%");
								columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, maxWidth)
										+ "%");
								columnDetailsElement.setAttribute("align",
										"center");
								tableElement = newDocumentObjectHTML
										.createElement("table");
								tableElement.setAttribute("width", "100%");
								tableElement.setAttribute("cellspacing", "0");
								tableElement.setAttribute("cellpadding", "0");
								tableElement.setAttribute("border", "0");
								rowElement = newDocumentObjectHTML
										.createElement("tr");
								columnElement = newDocumentObjectHTML
										.createElement("td");

								elementElement = newDocumentObjectHTML
										.createElement("element");// element
																	// Element
								elementElement.setAttribute("id", "");
								elementElement.setAttribute("name", "");
								elementElement.setAttribute("type", "");
								elementElement.setAttribute("idC", "");
								elementElement.setAttribute("value", "");
								columnElement.appendChild(elementElement);
								rowElement.appendChild(columnElement);
								tableElement.appendChild(rowElement);
								columnDetailsElement.appendChild(tableElement);// add
																				// element
																				// to
																				// the
																				// columndetails
																				// element
								rowDetailsElement
										.appendChild(columnDetailsElement);
							}
							// adding control element
							elementElement = newDocumentObjectHTML
									.createElement("element");
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", ""
									+ columnNo++);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, maxWidth)
									+ "%");
							columnDetailsElement.setAttribute(
									"align",
									child.getAttributes()
											.getNamedItem("elementAlignment")
											.getNodeValue());

							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							elementElement = newDocumentObjectHTML
									.createElement("element");
							createElementInHTMLForm(elementElement, child);

							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

						}

						/* creting the last of columns of row */
						for (int j = columnNo; j <= maxColumns; j++) {
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", "" + j);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(j, maxColumns, maxWidth)
									+ "%");
							columnDetailsElement
									.setAttribute("align", "center");
							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							Element elementElement = newDocumentObjectHTML
									.createElement("element");// element Element
							elementElement.setAttribute("id", "");
							elementElement.setAttribute("name", "");
							elementElement.setAttribute("type", "");
							elementElement.setAttribute("idC", "");
							elementElement.setAttribute("value", "");
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);// add
																			// element
																			// to
																			// the
																			// columndetails
																			// element
							rowDetailsElement.appendChild(columnDetailsElement);
							columnNo++; // ////////////////////----------------------added
										// 19102009------------------/////

						}

					} else {
						// if element label is not displayed along with the
						// element type

						Element elementElement = newDocumentObjectHTML
								.createElement("element");
						if (columnNo / 2 != 0)
							classString = "tdfonthead";
						else
							classString = "tdfont";

						/*
						 * columnDetailsElement=newDocumentObjectHTML.createElement
						 * ("columnDetails");
						 * columnDetailsElement.setAttribute("colNo",
						 * ""+columnNo++);
						 * columnDetailsElement.setAttribute("class",
						 * classString);
						 * columnDetailsElement.setAttribute("width",
						 * ""+columnWidth+"%");
						 * columnDetailsElement.setAttribute("align", "center");
						 */

						tableElement = newDocumentObjectHTML
								.createElement("table");
						tableElement.setAttribute("width", "100%");
						tableElement.setAttribute("cellspacing", "0");
						tableElement.setAttribute("cellpadding", "0");
						tableElement.setAttribute("border", "0");
						rowElement = newDocumentObjectHTML.createElement("tr");
						columnElement = newDocumentObjectHTML
								.createElement("td");

						elementElement = newDocumentObjectHTML
								.createElement("element");
						createElementInHTMLForm(elementElement, child);

						columnElement.appendChild(elementElement);
						rowElement.appendChild(columnElement);
						tableElement.appendChild(rowElement);
						columnDetailsElement.appendChild(tableElement);
						rowDetailsElement.appendChild(columnDetailsElement);

						System.out.println("100: columnNo  " + columnNo
								+ "  maxColumns " + maxColumns);
						for (int j = columnNo; j <= maxColumns; j++) {
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", "" + j);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(j, maxColumns, maxWidth)
									+ "%");
							columnDetailsElement
									.setAttribute("align", "center");
							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							elementElement = newDocumentObjectHTML
									.createElement("element");// element Element
							elementElement.setAttribute("id", "");
							elementElement.setAttribute("name", "");
							elementElement.setAttribute("type", "");
							elementElement.setAttribute("idC", "");
							elementElement.setAttribute("value", "");
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);// add
																			// element
																			// to
																			// the
																			// columndetails
																			// element
							rowDetailsElement.appendChild(columnDetailsElement);
							columnNo++;// added on 19102009------------------///
						}
					}

				} else {
					// element type=label

					if (columnNo / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, maxWidth)
							+ "%");
					columnDetailsElement.setAttribute("align", child
							.getAttributes().getNamedItem("labelAlignment")
							.getNodeValue());
					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");

					System.out.println("isparameternamedisplayed [label]  ="
							+ child.getAttributes()
									.getNamedItem("isparameternamedisplayed")
									.getNodeValue());

					Element elementElement = newDocumentObjectHTML
							.createElement("element");
					createElementInHTMLForm(elementElement, child);

					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);
					columnDetailsElement.appendChild(tableElement);

					rowDetailsElement.appendChild(columnDetailsElement);

					/*for (int j = columnNo; j <= maxColumns; j++) { //comment by chanduu FOR LABEL AS label not print in center 7dec2018
						if (j / 2 != 0)
							classString = "tdfonthead";
						else
							classString = "tdfont";

						columnDetailsElement = newDocumentObjectHTML
								.createElement("columnDetails");
						columnDetailsElement.setAttribute("colNo", "" + j);
						columnDetailsElement.setAttribute("class", classString);
//						columnDetailsElement.setAttribute("width", ""
//								+ columnWidth + "%");
						columnDetailsElement.setAttribute("width", "" + getColumnWidth(j, maxColumns, maxWidth)
								+ "%");
						columnDetailsElement.setAttribute("align", "center");
						tableElement = newDocumentObjectHTML
								.createElement("table");
						tableElement.setAttribute("width", "100%");
						tableElement.setAttribute("cellspacing", "0");
						tableElement.setAttribute("cellpadding", "0");
						tableElement.setAttribute("border", "0");
						rowElement = newDocumentObjectHTML.createElement("tr");
						columnElement = newDocumentObjectHTML
								.createElement("td");

						elementElement = newDocumentObjectHTML
								.createElement("element");// element Element
						elementElement.setAttribute("id", "");
						elementElement.setAttribute("name", "");
						elementElement.setAttribute("type", "");
						elementElement.setAttribute("idC", "");
						elementElement.setAttribute("value", "");
						columnElement.appendChild(elementElement);
						rowElement.appendChild(columnElement);
						tableElement.appendChild(rowElement);
						columnDetailsElement.appendChild(tableElement);// add
																		// element
																		// to
																		// the
																		// columndetails
																		// element
						rowDetailsElement.appendChild(columnDetailsElement);
						columnNo++;// added on 19102009------------------///
					}*/

				}
				// createElementInHTMLForm()

				templateElement.appendChild(rowDetailsElement);
				if (child.hasChildNodes()) {
//					recursionForSimpleHTMLElements(child, templateElement,
//							newDocumentObjectHTML, maxColumns, columnWidth,
//							rowNo);
					recursionForSimpleHTMLElements(child, templateElement,
							newDocumentObjectHTML, maxColumns, getColumnWidth(columnNo-1, maxColumns, maxWidth),
							rowNo);
					
				}
				
				columnNo = 1;
			}

		}

	}

	private void recursionForSimpleHTMLElements(Node elementSM,
			Node templateElement, Document newDocumentObjectHTML,
			int maxColumns, int columnWidth, int rowNo) {
		if (elementSM.hasChildNodes()) {
			NodeList childNodeList = elementSM.getChildNodes();

			for (int i = 0; i < childNodeList.getLength(); i++) {
				int columnNo = 1;
				String classString = "";
				Node child = childNodeList.item(i);
				Element rowDetailsElement = newDocumentObjectHTML
						.createElement("rowDetails");
				rowDetailsElement.setAttribute("rowNo", "" + rowNos++);

				int levelNo = Integer.parseInt(child.getAttributes()
						.getNamedItem("levelNo").getNodeValue());
				System.out.println("levelNo  " + levelNo);
				for (int level = 1; level < levelNo; level++) {
					if (columnNo / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
							+ "%");
					columnDetailsElement.setAttribute("align", "center");

					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					tableElement.setAttribute("width", "100%");
					tableElement.setAttribute("cellspacing", "0");
					tableElement.setAttribute("cellpadding", "0");
					tableElement.setAttribute("border", "0");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");
					Element elementElement = newDocumentObjectHTML
							.createElement("element");// element Element
					elementElement.setAttribute("id", "");
					elementElement.setAttribute("name", "");
					elementElement.setAttribute("type", "");
					elementElement.setAttribute("idC", "");
					elementElement.setAttribute("value", "");

					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);
					columnDetailsElement.appendChild(tableElement);// add
																	// element
																	// to the
																	// columndetails
																	// element
					rowDetailsElement.appendChild(columnDetailsElement);
				}

				if (child.getAttributes().getNamedItem("objectId")
						.getNodeValue().equals("label") == false) {
					if (columnNo / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
							+ "%");
					columnDetailsElement.setAttribute("align", child
							.getAttributes().getNamedItem("labelAlignment")
							.getNodeValue());

					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");

					if (child.getAttributes()
							.getNamedItem("isparameternamedisplayed")
							.getNodeValue().equals("1")) {
						if (child.getAttributes()
								.getNamedItem("labelelementdisplaytype")
								.getNodeValue().equals("0") == false) {
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							Element elementElement = newDocumentObjectHTML
									.createElement("element");
							elementElement.setAttribute("idC", "label");
							elementElement.setAttribute("name", "");
							elementElement.setAttribute(
									"value",
									child.getAttributes()
											.getNamedItem("parameterName")
											.getNodeValue());
							elementElement.setAttribute("type", "");
							elementElement.setAttribute(
									"align",
									child.getAttributes()
											.getNamedItem("labelAlignment")
											.getNodeValue());
							setBoldAndUnderlinePropertiesForParameterLabel(
									elementElement, child);

							System.out
									.println("isparameternamedisplayed[~label]   ="
											+ child.getAttributes()
													.getNamedItem(
															"isparameternamedisplayed")
													.getNodeValue());
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

							for (int j = columnNo; j < maxColumns; j++) {
								if (columnNo / 2 != 0)
									classString = "tdfonthead";
								else
									classString = "tdfont";

								columnDetailsElement = newDocumentObjectHTML
										.createElement("columnDetails");
								columnDetailsElement.setAttribute("colNo", ""
										+ j);
								columnDetailsElement.setAttribute("class",
										classString);
//								columnDetailsElement.setAttribute("width", ""
//										+ columnWidth + "%");
								columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
										+ "%");
								columnDetailsElement.setAttribute("align",
										"center");
								tableElement = newDocumentObjectHTML
										.createElement("table");
								tableElement.setAttribute("width", "100%");
								tableElement.setAttribute("cellspacing", "0");
								tableElement.setAttribute("cellpadding", "0");
								tableElement.setAttribute("border", "0");
								rowElement = newDocumentObjectHTML
										.createElement("tr");
								columnElement = newDocumentObjectHTML
										.createElement("td");

								elementElement = newDocumentObjectHTML
										.createElement("element");// element
																	// Element
								elementElement.setAttribute("id", "");
								elementElement.setAttribute("name", "");
								elementElement.setAttribute("type", "");
								elementElement.setAttribute("idC", "");
								elementElement.setAttribute("value", "");
								columnElement.appendChild(elementElement);
								rowElement.appendChild(columnElement);
								tableElement.appendChild(rowElement);
								columnDetailsElement.appendChild(tableElement);// add
																				// element
																				// to
																				// the
																				// columndetails
																				// element
								rowDetailsElement
										.appendChild(columnDetailsElement);
								columnNo++;// incrementing columnNO
							}

							// adding control element at the last column
							elementElement = newDocumentObjectHTML
									.createElement("element");
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", ""
									+ columnNo++);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
									+ "%");
							columnDetailsElement.setAttribute(
									"align",
									child.getAttributes()
											.getNamedItem("elementAlignment")
											.getNodeValue());

							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							elementElement = newDocumentObjectHTML
									.createElement("element");
							createElementInHTMLForm(elementElement, child);

							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

						} else {
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							Element elementElement = newDocumentObjectHTML
									.createElement("element");
							elementElement.setAttribute("idC", "label");
							elementElement.setAttribute("name", "");
							elementElement.setAttribute(
									"value",
									child.getAttributes()
											.getNamedItem("parameterName")
											.getNodeValue());
							elementElement.setAttribute("type", "");
							elementElement.setAttribute(
									"align",
									child.getAttributes()
											.getNamedItem("labelAlignment")
											.getNodeValue());

							setBoldAndUnderlinePropertiesForParameterLabel(
									elementElement, child);

							System.out
									.println("isparameternamedisplayed[~label]   ="
											+ child.getAttributes()
													.getNamedItem(
															"isparameternamedisplayed")
													.getNodeValue());
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

							int distance = Integer.parseInt(child
									.getAttributes()
									.getNamedItem("labelelementdisplaytype")
									.getNodeValue());
							System.out.println("element name "
									+ child.getAttributes()
											.getNamedItem("parameterName")
											.getNodeValue() + "distance  ="
									+ distance);
							for (int cdistance = 0; (cdistance < distance && columnNo < maxColumns); cdistance++) {
								columnDetailsElement = newDocumentObjectHTML
										.createElement("columnDetails");
								columnDetailsElement.setAttribute("colNo", ""
										+ columnNo++);
								columnDetailsElement.setAttribute("class",
										classString);
//								columnDetailsElement.setAttribute("width", ""
//										+ columnWidth + "%");
								columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
										+ "%");
								columnDetailsElement.setAttribute("align",
										"center");
								tableElement = newDocumentObjectHTML
										.createElement("table");
								tableElement.setAttribute("width", "100%");
								tableElement.setAttribute("cellspacing", "0");
								tableElement.setAttribute("cellpadding", "0");
								tableElement.setAttribute("border", "0");
								rowElement = newDocumentObjectHTML
										.createElement("tr");
								columnElement = newDocumentObjectHTML
										.createElement("td");

								elementElement = newDocumentObjectHTML
										.createElement("element");// element
																	// Element
								elementElement.setAttribute("id", "");
								elementElement.setAttribute("name", "");
								elementElement.setAttribute("type", "");
								elementElement.setAttribute("idC", "");
								elementElement.setAttribute("value", "");
								columnElement.appendChild(elementElement);
								rowElement.appendChild(columnElement);
								tableElement.appendChild(rowElement);
								columnDetailsElement.appendChild(tableElement);// add
																				// element
																				// to
																				// the
																				// columndetails
																				// element
								rowDetailsElement
										.appendChild(columnDetailsElement);
							}
							// adding control element
							elementElement = newDocumentObjectHTML
									.createElement("element");
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", ""
									+ columnNo++);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
									+ "%");
							// columnDetailsElement.setAttribute("align",
							// child.getAttributes().getNamedItem("align").getNodeValue());

							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							elementElement = newDocumentObjectHTML
									.createElement("element");
							createElementInHTMLForm(elementElement, child);

							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);
							rowDetailsElement.appendChild(columnDetailsElement);

							for (int j = columnNo; j <= maxColumns; j++) {
								if (columnNo / 2 != 0)
									classString = "tdfonthead";
								else
									classString = "tdfont";

								columnDetailsElement = newDocumentObjectHTML
										.createElement("columnDetails");
								columnDetailsElement.setAttribute("colNo", ""
										+ j);
								columnDetailsElement.setAttribute("class",
										classString);
//								columnDetailsElement.setAttribute("width", ""
//										+ columnWidth + "%");
								columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
										+ "%");
								columnDetailsElement.setAttribute("align",
										"center");
								tableElement = newDocumentObjectHTML
										.createElement("table");
								tableElement.setAttribute("width", "100%");
								tableElement.setAttribute("cellspacing", "0");
								tableElement.setAttribute("cellpadding", "0");
								tableElement.setAttribute("border", "0");
								rowElement = newDocumentObjectHTML
										.createElement("tr");
								columnElement = newDocumentObjectHTML
										.createElement("td");

								elementElement = newDocumentObjectHTML
										.createElement("element");// element
																	// Element
								elementElement.setAttribute("id", "");
								elementElement.setAttribute("name", "");
								elementElement.setAttribute("type", "");
								elementElement.setAttribute("idC", "");
								elementElement.setAttribute("value", "");
								columnElement.appendChild(elementElement);
								rowElement.appendChild(columnElement);
								tableElement.appendChild(rowElement);
								columnDetailsElement.appendChild(tableElement);// add
																				// element
																				// to
																				// the
																				// columndetails
																				// element
								rowDetailsElement
										.appendChild(columnDetailsElement);
								columnNo++;// added on
											// 19102009------------------///
							}
						}
					} else {
						Element elementElement = newDocumentObjectHTML
								.createElement("element");
						if (columnNo / 2 != 0)
							classString = "tdfonthead";
						else
							classString = "tdfont";

						columnDetailsElement = newDocumentObjectHTML
								.createElement("columnDetails");
						columnDetailsElement.setAttribute("colNo", ""
								+ columnNo++);
						columnDetailsElement.setAttribute("class", classString);
//						columnDetailsElement.setAttribute("width", ""
//								+ columnWidth + "%");
						columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
								+ "%");
						columnDetailsElement.setAttribute("align", "center");

						tableElement = newDocumentObjectHTML
								.createElement("table");
						tableElement.setAttribute("width", "100%");
						tableElement.setAttribute("cellspacing", "0");
						tableElement.setAttribute("cellpadding", "0");
						tableElement.setAttribute("border", "0");
						rowElement = newDocumentObjectHTML.createElement("tr");
						columnElement = newDocumentObjectHTML
								.createElement("td");

						elementElement = newDocumentObjectHTML
								.createElement("element");
						createElementInHTMLForm(elementElement, child);

						columnElement.appendChild(elementElement);
						rowElement.appendChild(columnElement);
						tableElement.appendChild(rowElement);
						columnDetailsElement.appendChild(tableElement);
						rowDetailsElement.appendChild(columnDetailsElement);

						System.out.println("101:  columnNo " + columnNo
								+ " maxColumns " + maxColumns);
						for (int j = columnNo; j <= maxColumns; j++) {
							if (columnNo / 2 != 0)
								classString = "tdfonthead";
							else
								classString = "tdfont";

							columnDetailsElement = newDocumentObjectHTML
									.createElement("columnDetails");
							columnDetailsElement.setAttribute("colNo", "" + j);
							columnDetailsElement.setAttribute("class",
									classString);
//							columnDetailsElement.setAttribute("width", ""
//									+ columnWidth + "%");
							columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
									+ "%");
							columnDetailsElement
									.setAttribute("align", "center");
							tableElement = newDocumentObjectHTML
									.createElement("table");
							tableElement.setAttribute("width", "100%");
							tableElement.setAttribute("cellspacing", "0");
							tableElement.setAttribute("cellpadding", "0");
							tableElement.setAttribute("border", "0");
							rowElement = newDocumentObjectHTML
									.createElement("tr");
							columnElement = newDocumentObjectHTML
									.createElement("td");

							elementElement = newDocumentObjectHTML
									.createElement("element");// element Element
							elementElement.setAttribute("id", "");
							elementElement.setAttribute("name", "");
							elementElement.setAttribute("type", "");
							elementElement.setAttribute("idC", "");
							elementElement.setAttribute("value", "");
							columnElement.appendChild(elementElement);
							rowElement.appendChild(columnElement);
							tableElement.appendChild(rowElement);
							columnDetailsElement.appendChild(tableElement);// add
																			// element
																			// to
																			// the
																			// columndetails
																			// element
							rowDetailsElement.appendChild(columnDetailsElement);
							columnNo++;// added on 19102009------------------///
						}
					}

				} else {
					if (columnNo / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
							+ "%");
					// columnDetailsElement.setAttribute("align",
					// child.getAttributes().getNamedItem("align").getNodeValue());
					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					tableElement.setAttribute("width", "100%");
					tableElement.setAttribute("cellspacing", "0");
					tableElement.setAttribute("cellpadding", "0");
					tableElement.setAttribute("border", "0");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");
					Element elementElement = newDocumentObjectHTML
							.createElement("element");
					createElementInHTMLForm(elementElement, child);

					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);
					columnDetailsElement.appendChild(tableElement);
					rowDetailsElement.appendChild(columnDetailsElement);
				}
				// createElementInHTMLForm()
				for (int j = columnNo; j <= maxColumns; j++) {
					if (j / 2 != 0)
						classString = "tdfonthead";
					else
						classString = "tdfont";

					Element columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");
					columnDetailsElement.setAttribute("colNo", "" + j);
					columnDetailsElement.setAttribute("class", classString);
//					columnDetailsElement.setAttribute("width", "" + columnWidth
//							+ "%");
					columnDetailsElement.setAttribute("width", "" + getColumnWidth(columnNo-1, maxColumns, columnWidth)
							+ "%");
					columnDetailsElement.setAttribute("align", "center");

					Element tableElement = newDocumentObjectHTML
							.createElement("table");
					tableElement.setAttribute("width", "100%");
					tableElement.setAttribute("cellspacing", "0");
					tableElement.setAttribute("cellpadding", "0");
					tableElement.setAttribute("border", "0");
					Element rowElement = newDocumentObjectHTML
							.createElement("tr");
					Element columnElement = newDocumentObjectHTML
							.createElement("td");

					Element elementElement = newDocumentObjectHTML
							.createElement("element");// element Element
					elementElement.setAttribute("id", "");
					elementElement.setAttribute("name", "");
					elementElement.setAttribute("type", "");
					elementElement.setAttribute("idC", "");
					elementElement.setAttribute("value", "");

					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);
					columnDetailsElement.appendChild(tableElement);// add
																	// element
																	// to the
																	// columndetails
																	// element
					rowDetailsElement.appendChild(columnDetailsElement);
					columnNo++;// added on 19102009------------------///
				}

				templateElement.appendChild(rowDetailsElement);

				if (child.hasChildNodes()) {
					recursionForSimpleHTMLElements(child, templateElement,
							newDocumentObjectHTML, maxColumns, columnWidth,
							rowNo);
				}

				columnNo = 1;
			}

		}
	}

	public static void setBoldAndUnderlinePropertiesForParameterLabel(
			Element elementElement, Node childelementElement) {
		String[] elementProperties = childelementElement.getAttributes()
				.getNamedItem("property").getNodeValue().split("#");
		if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("textArea")) {
			// changing 3,4 to 2,3, check later if it works
			elementElement.setAttribute("bold", elementProperties[3]);
			elementElement.setAttribute("underline", elementProperties[4]);
			// elementElement.setAttribute("bold",elementProperties[2]);
			// elementElement.setAttribute("underline",elementProperties[3]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("textBox")) {
			elementElement.setAttribute("bold", elementProperties[2]);
			elementElement.setAttribute("underline", elementProperties[3]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("label")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"labelID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());

		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("select")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("queryValue")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("listBox")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("button")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		} else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("imagesection")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		}
		
		else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("autocomplete")) {
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		}
		/*added by chandan for hyperlink*/
		else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("hyperlink")) {
			elementElement.setAttribute("align", elementProperties[0]);
			elementElement.setAttribute("bold", elementProperties[1]);
			elementElement.setAttribute("underline", elementProperties[2]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		}
		/*added by chandan for checkbox*/
		else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("CheckBox")) {
			elementElement.setAttribute("align", elementProperties[0]);
			elementElement.setAttribute("bold", elementProperties[1]);
			elementElement.setAttribute("underline", elementProperties[2]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		}
		/*added by chandan for snomedct*/
		else if (childelementElement.getAttributes().getNamedItem("objectId")
				.getNodeValue().equals("SnomedCT")) {
			
			elementElement.setAttribute("align", (elementProperties[0].equals("")?"0":elementProperties[0]));
			elementElement.setAttribute("bold", (elementProperties[1].equals("")?"0":elementProperties[1]));
			elementElement.setAttribute("underline", elementProperties[2]);
			elementElement.setAttribute(
					"id",
					"eleID_template#"
							+ childelementElement.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ childelementElement.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
		}
		
		
		else {

		}

		if (childelementElement.getAttributes()
				.getNamedItem("dependentelement") != null) {
			elementElement.setAttribute("dependentelement", childelementElement
					.getAttributes().getNamedItem("dependentelement")
					.getNodeValue());
		}

		if (childelementElement.getAttributes().getNamedItem(
				"dependentelementvalue") != null) {
			elementElement.setAttribute(
					"dependentelementvalue",
					childelementElement.getAttributes()
							.getNamedItem("dependentelementvalue")
							.getNodeValue());
		}

		if (childelementElement.getAttributes().getNamedItem(
				"defaultvaluecondition") != null) {
			elementElement.setAttribute(
					"defaultvaluecondition",
					childelementElement.getAttributes()
							.getNamedItem("defaultvaluecondition")
							.getNodeValue());
		}

	}

	private int getMaximumNoOFColumnsSimpleHTML(Node clonedNodeSM,
			int maxLevelNo) {

		if (clonedNodeSM.hasChildNodes()) {
			NodeList childNodeList = clonedNodeSM.getChildNodes();
			Boolean updated = false;
			for (int i = 0; i < childNodeList.getLength(); i++) {

				Node child = childNodeList.item(i);
				if (child.hasChildNodes()) {
					maxLevelNo = getMaximumNoOFColumnsSimpleHTML(child,
							maxLevelNo);
				}

				int levelNo = Integer.parseInt(child.getAttributes()
						.getNamedItem("levelNo").getNodeValue());
				System.out.println("Max level No " + maxLevelNo + "level No"
						+ levelNo);

				if (levelNo > maxLevelNo) {
					maxLevelNo = levelNo;
				}

				if (maxLevelNo == 1 && updated == false) {
					System.out.println("  -----------------> "
							+ child.getAttributes().getNamedItem("objectId")
									.getNodeValue());
					if (child.getAttributes().getNamedItem("objectId")
							.getNodeValue().equals("label") == false) {
						maxLevelNo++;
					}

					updated = true;
				}
			}

		}

		return maxLevelNo;
	}

	/*
	 * This function is for updating an element this does not reorders the
	 * elements in the template it just modifies the existing case.
	 * templateTypId: template id represents the xml document from which.
	 * nodeId: it is the the particular template of the xml document. elementId:
	 * it is the element of the particular template to be modified
	 */
	public void updatingDocumentTree(String templateTypId, String nodeId,
			String elementId, String isValid, HttpSession session) {
		// LOGGER_INV.log(Level.INFO,"updatingDocumentTree started"+isValid+" elementId->"+elementId);
		// InvestigationStyleSheetConfigurationCacheManager
		// styleSheetConfigurationCacheManager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
		// InvestigationDocumentCacheManager
		// documentConfigurationCacheManager=InvestigationDocumentCacheManager.getInstance();
		Connection	conn = null;
		JDBCTransactionContext tx = null;
		
		Node templateNode = InvestigationTemplateDataHelper.getInstance()
				.getTemplateNodeObj(templateTypId, nodeId, session, false);// =
																	// documentConfigurationCacheManager.getTemplateNodeObj(templateTypId,
																	// true,
																	// nodeId);
		String tableName = "";
		if (templateNode == null || isValid.equals("0") == false) {
			// LOGGER_INV.log(Level.INFO,"updatingDocumentTree creating ");
			Map<String, TestTemplateVO> testTemplateVOObjectMap = new HashMap<String, TestTemplateVO>();
			testTemplateVOObjectMap.put(nodeId, new TestTemplateVO());
			testTemplateVOObjectMap.get(nodeId).setTestCode(nodeId);

			switch (Integer.parseInt(templateTypId)) {
			case 1:// test template form
				CreateDocumentTreeForTest(testTemplateVOObjectMap, session);
				tableName = "HIVT_TEST_PARAMETER_MST ";
				break;
			case 3:// test template form    // for requisition form
				CreateDocumentTreeForTest(testTemplateVOObjectMap, session);
				tableName = "HIVT_TEST_PARAMETER_MST ";
				break;
			
				/*
			 * case 2:// sample template form
			 * CreateSampleDocumentTreeForTest(testTemplateVOObjectMap);
			 * tableName="HIVT_TEST_SAMPLE_PARAMETER_DTL"; break; case 3://
			 * requisition template form
			 * CreateRequisitionDocumentTreeForTest(testTemplateVOObjectMap);
			 * tableName="HIVT_TEST_REQ_PARAMETER_DTL"; break;
			 * 
			 * case 5:// printing template form break; case 6:// Result Entry
			 * group document
			 * CreateTestFroupDocumentTreeForTest(testTemplateVOObjectMap);
			 * tableName="HIVT_TEST_GROUP_PARAMETER_DTL"; break; case 7://
			 * labRegister document break; case 8:// laboratory requisition form
			 * CreateDocumentTreeForLaboratoryRequestForm
			 * (testTemplateVOObjectMap);
			 * tableName="HIVT_LAB_REQ_PARAMETER_DTL";
			 * 
			 * break;
			 */
			case 9:

				break;
			case 10:
				break;

			}

			return;

		} else {
			// LOGGER_INV.log(Level.INFO,"updatingDocumentTree updating ");
			try {
				System.out.println("Element ID: " + elementId);
				int StartIndex = elementId.length() - 9;
				String parameterCodeConcat = elementId.substring(StartIndex,
						elementId.length());
				String parentParameterCode = ((StartIndex == 0)) ? parameterCodeConcat
						: elementId.substring(0, StartIndex);
				String parameterCode = parameterCodeConcat.substring(5);

				String insertionRemarks = "";
				String insertionId = "";
				switch (Integer.parseInt(templateTypId)) {
				case 1:// test template form

					tableName = "HIVT_TEST_PARAMETER_MST ";
					insertionRemarks = "testtemplate.xml";
					insertionId = InvestigationConfig.XML_TESTTEMPLATE;
				case 3:// test template form

					tableName = "HIVT_REQFORM_TEST_PARAMETER_MST ";
					insertionRemarks = "testtemplate.xml";
					insertionId = InvestigationConfig.XML_TESTREQUISITIONTEMPLATE;
										break;
				
					/*
				 * case 2:// sample template form
				 * insertionRemarks="testSampletemplate.xml";
				 * tableName="HIVT_TEST_SAMPLE_PARAMETER_DTL";
				 * insertionId=InvestigationConfig.XML_TESTSAMPLETEMPLATE;
				 * break; case 3:// requisition template form
				 * insertionRemarks="testRequisitiontemplate.xml";
				 * tableName="HIVT_TEST_REQ_PARAMETER_DTL";
				 * insertionId=InvestigationConfig.XML_TESTREQUISITIONTEMPLATE;
				 * break;
				 * 
				 * case 5:// printing template form break; case 6:// Result
				 * Entry group document
				 * insertionRemarks="labtestgrouptemplate.xml";
				 * tableName="HIVT_TEST_GROUP_PARAMETER_DTL";
				 * insertionId=InvestigationConfig.XML_LABTESTGROUPTEMPLATE;
				 * break; case 7:// labRegister document break; case 8://
				 * laboratory requisition form
				 * insertionRemarks="laboratoryRequisitiontemplate.xml";
				 * tableName="HIVT_LAB_REQ_PARAMETER_DTL";
				 * insertionId=InvestigationConfig
				 * .XML_LABORATORYREQUISITIONTEMPLATE; break;
				 */
				case 9:

					break;
				case 10:
					break;

				}

				Element replacementElement = ((Element) templateNode)
						.getOwnerDocument().createElement("element");

				/*
				 * String elementFetchingQuery=
				 * "SELECT  LEVEL, view1.parametercode, view1.testparametername,testparametercode, parentid, criteriacode, testvaluetype, "
				 * +
				 * " DECODE (objectid,'B', 'label','E', 'textBox','H', 'textArea','D', 'select','I', 'queryValue','J', 'button','L', 'imagesection'), "
				 * +
				 * " functionname, objevent, objvalidationstring, property,isparameternamedisplayed, labelelementdisplaytype, labelalignment, "
				 * +
				 * " elementalignment, defaultvalue, isprintable, querycode, buttonname,callurl,dependentelement,dependentelementvalue,defaultvaluecondition "
				 * +
				 * " FROM (SELECT   hpm.hgstr_parameter AS testparametername,"+
				 * ((tableName.equals("HIVT_TEST_GROUP_PARAMETER_DTL")?
				 * "htpd.GNUM_TEST_GROUP_PARAMETER_CODE"
				 * :"htpd.gnum_test_parameter_code"
				 * ))+" AS parametercode,"+((tableName
				 * .equals("HIVT_TEST_GROUP_PARAMETER_DTL"
				 * )?"htpd..GNUM_TEST_GROUP_PARAMETER_CODE"
				 * :"htpd.gnum_test_parameter_code"))+" AS testparametercode, "+
				 * " htpd.gstr_value_obj_id objectid,htpd.gnum_parent_id AS PARENT,htpd.gnum_criteria_code AS criteriacode,htpd.gbl_value_type AS testvaluetype, "
				 * + " DECODE (htpd.gnum_parent_id, "+((tableName.equals(
				 * "HIVT_TEST_GROUP_PARAMETER_DTL"
				 * )?"htpd.GNUM_TEST_GROUP_PARAMETER_CODE"
				 * :"htpd.gnum_test_parameter_code"))+
				 * ", NULL,htpd.gnum_parent_id) AS parentid,gstr_function_name AS functionname, "
				 * +
				 * " hivtnum_objevent AS objevent,hivtnum_validation_func AS objvalidationstring,htpd.hivstr_element_property AS property,hivtnum_para_namedisplay AS isparameternamedisplayed, "
				 * +
				 * " hivtnum_label_eledisplay_type AS labelelementdisplaytype,hivtstr_label_align AS labelalignment,hivtstr_element_align AS elementalignment, "
				 * +
				 * " hivtstr_defaultvalue AS defaultvalue,hivt_num_element_sequence AS se,htpd.gnum_isprintable AS isprintable,htpd.hivtstr_query AS querycode, "
				 * +
				 * " hivtstr_button_name AS buttonname,hivtstr_call_url AS callurl,HIVTNUM_DEP_ELEMENT as dependentelement, HIVTSTR_DEP_ELE_VALUE as dependentelementvalue,HIVTNUM_DEF_CONDITION as defaultvaluecondition FROM "
				 * +tableName+" htpd, hivt_parameter_mst hpm "+
				 * " WHERE SUBSTR ("
				 * +((tableName.equals("HIVT_TEST_GROUP_PARAMETER_DTL"
				 * )?"htpd.GNUM_TEST_GROUP_PARAMETER_CODE"
				 * :"htpd.gnum_test_parameter_code"
				 * ))+", 1, 4) = ? AND SUBSTR ("+
				 * ((tableName.equals("HIVT_TEST_GROUP_PARAMETER_DTL"
				 * )?"htpd.GNUM_TEST_GROUP_PARAMETER_CODE"
				 * :"htpd.gnum_test_parameter_code"
				 * ))+", 5, 4)=hpm.hgnum_parameter_id "+
				 * " AND htpd.gnum_parent_id = ? AND "
				 * +((tableName.equals("HIVT_TEST_GROUP_PARAMETER_DTL"
				 * )?"htpd.GNUM_TEST_GROUP_PARAMETER_CODE"
				 * :"htpd.gnum_test_parameter_code"))+
				 * " = ? AND htpd.gnum_hospital_code = 101 AND hpm.gnum_hospital_code = htpd.gnum_hospital_code "
				 * +
				 * " AND htpd.gnum_isvalid = 1 AND hpm.gnum_isvalid = 1 ORDER BY htpd.hivt_num_element_sequence) view1 CONNECT BY PRIOR view1.parametercode = view1.parentid "
				 * + " START WITH view1.parentid IS NULL";
				 */

				String elementFetchingQuery = "SELECT LEVEL, "
						+ "view1.parametercode, "
						+ "view1.testparametername,"
						+ " testparametercode, "
						+ "parentid, "
						+ "criteriacode, "
						+ "testvaluetype,"
						+ "(Select GSTR_VALUE_OBJ from HIVT_TESTPARA_ELEMENTTYPE_MST where gnum_is_valid=1 and  GSTR_VALUE_OBJ_ID = objectid)"
						+ "functionname, "
						+ "objevent, "
						+ "objvalidationstring, "
						+ "property,"
						+ " isparameternamedisplayed,  "
						+ "labelalignment,"
						+ "elementalignment, "
						+ " isprintable,"
						+ " querycode, "
						+ "buttonname,"
						+ " callurl,"
						+ " defaultValue "
						+ " FROM (SELECT (Select  hgstr_parameter from hivt_parameter_mst where gnum_isvalid=1 and hgnum_parameter_id= a.GNUM_PARAMETER_CODE) AS testparametername,"
						+ "a.GNUM_PARAMETER_CODE AS parametercode,"
						+ "a.GNUM_TEST_CODE AS testparametercode,"
						+ "a.GSTR_ELEMENT_TYPE objectid,"
						+ "a.gnum_parent_id AS PARENT,"
						+ "a.GNUM_CRITERIA_CODE AS criteriacode,"
						+ "a.GNUM_ARRANGE_AS AS testvaluetype,"
						+ "DECODE (gnum_parent_id,"
						+ "concat(GNUM_TEST_CODE,GNUM_PARAMETER_CODE), NULL,"
						+ "gnum_parent_id"
						+ ") AS parentid,"
						+ "gstr_function_name AS functionname,"
						+ "hivtnum_objevent AS objevent,"
						+ "HIVTNUM_EVENT_FUNC AS objvalidationstring,"
						+ "a.hivstr_element_property AS property,"
						+ "HIVNUM_PARANAME_DISPLAYASLABEL AS isparameternamedisplayed,"
						+ "hivtstr_label_align AS labelalignment,"
						+ "hivtstr_element_align AS elementalignment,"
						+ "hivt_num_element_sequence AS se,"
						+ "a.gnum_isprintable AS isprintable,"
						+ "a.hivtstr_query AS querycode,"
						+ "hivtstr_button_name AS buttonname,"
						+ "hivtstr_call_url AS callurl,"
						+ "hivtstr_default_value as defaultValue "
						+ " FROM hivt_test_parameter_mst a WHERE GNUM_TEST_CODE = ?"
						+ " AND a.gnum_parent_id = ?"
						+ " AND a.GNUM_PARAMETER_CODE = ?"
						+ " AND a.gnum_isvalid = 1"
						+ " ORDER BY a.hivt_num_element_sequence"
						+ ") view1 CONNECT BY PRIOR view1.parametercode = view1.parentid START WITH view1.parentid IS NULL order by se "; //or a.hivt_num_element_sequence

				/*
				 * String elementFetchingQuery = "SELECT LEVEL," +
				 * "view1.parameterCode," + "view1.testparametername," +
				 * "testparametercode," + "parentid," + "criteriacode," +
				 * "testvaluetype, " +
				 * "decode(objectid,'B','label','E','textBox','H','textArea','D','select','I','queryValue','J','button','L','imagesection'),"
				 * + "functionName,objEvent," + "property," +
				 * "isparameternamedisplayed," + "labelAlignment," +
				 * "elementAlignment," + "isPrintable," + "queryCode," +
				 * "buttonName," + "callUrl " +
				 * "FROM (SELECT (Select hgstr_parameter from hivt_parameter_mst where gnum_isvalid=1"
				 * +
				 * " and hgnum_parameter_id=a.gnum_parameter_code ) AS testparametername,a.gnum_test_code "
				 * +
				 * "|| a.gnum_parameter_code as parameterCode,a.gnum_test_code || a.gnum_parameter_code as testparametercode,"
				 * + "a.gstr_element_type objectid, " +
				 * "a.gnum_parent_id AS parent, " +
				 * "a.gnum_criteria_code AS criteriacode, " +
				 * "a.gnum_arrange_as AS testvaluetype," +
				 * "DECODE(a.GNUM_PARENT_ID,(concat(a.gnum_test_code,a.gnum_parameter_code)),NULL,a.GNUM_PARENT_ID) AS parentid,"
				 * + "GSTR_FUNCTION_NAME as functionName," +
				 * "HIVTNUM_OBJEVENT AS objEvent," +
				 * "a.HIVSTR_ELEMENT_PROPERTY as property, " +
				 * "hivnum_paraname_displayaslabel AS isparameternamedisplayed,"
				 * + " hivtstr_label_align as labelAlignment," +
				 * "hivtstr_element_align as elementAlignment," +
				 * "HIVT_NUM_ELEMENT_SEQUENCE as se," +
				 * "a.GNUM_ISPRINTABLE as isPrintable," +
				 * "a.HIVTSTR_QUERY as queryCode," +
				 * "HIVTSTR_BUTTON_NAME as buttonName," +
				 * "HIVTSTR_CALL_URL as callUrl" +
				 * " FROM HIVT_TEST_PARAMETER_MST a" +
				 * " WHERE  a.gnum_test_code = ?" +
				 * "	 AND a.GNUM_ISVALID=1 order by a.HIVT_NUM_ELEMENT_SEQUENCE) "
				 * + "view1 CONNECT BY PRIOR" +
				 * " view1.parameterCode=view1.parentid " +
				 * "START WITH view1.parentid IS NULL  order by se ";
				 */

				System.out.println("query: " + elementFetchingQuery);
				System.out.println("Element ID: " + elementId);
				 tx = new JDBCTransactionContext();
				tx.begin();
			
					conn = tx.getConnection();

				// LOGGER_INV.log(Level.INFO,elementFetchingQuery+" "+nodeId+"  "+parentParameterCode+"  "+parameterCode);
				PreparedStatement pstmt = conn
						.prepareStatement(elementFetchingQuery);

				System.out.println("Node ID: " + nodeId + " parent "
						+ parentParameterCode + " parameterCode "
						+ parameterCode);
				pstmt.setString(1, nodeId);
				pstmt.setString(2, parentParameterCode);
				pstmt.setString(3, parameterCode);
				pstmt.execute();
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Element element = ((Element) templateNode)
							.getOwnerDocument().createElement("testElement");
					/*
					 * element.setAttribute("levelNo",rs.getString(1));
					 * element.setAttribute("parameterCode",rs.getString(4));
					 * element.setAttribute("parameterName",rs.getString(3));
					 * element.setAttribute("CriteriaCode",rs.getString(6));
					 * element.setAttribute("valueType",rs.getString(7)); //
					 * represents the type of
					 * template(simple,horizontal,vertical)
					 * element.setAttribute("objectId",rs.getString(8));
					 * element.setAttribute("removeStatus","0");
					 * element.setAttribute("functionName",rs.getString(9));
					 * element.setAttribute("eventName",rs.getString(10));
					 * element
					 * .setAttribute("eventValidationString",rs.getString(11));
					 * element.setAttribute("property",rs.getString(12));
					 * element
					 * .setAttribute("isparameternamedisplayed",rs.getString
					 * (13));
					 * //element.setAttribute("labelelementdisplaytype",rs
					 * .getString(14));
					 * element.setAttribute("labelAlignment",rs.getString(14));
					 * element
					 * .setAttribute("elementAlignment",rs.getString(15));
					 * //element.setAttribute("defaultValue",rs.getString(17));
					 * element.setAttribute("isPrintable",rs.getString(16));
					 * element.setAttribute("queryCode",rs.getString(17));
					 * element.setAttribute("buttonName",rs.getString(18));
					 * element.setAttribute("callUrl",rs.getString(19));
					 * //element
					 * .setAttribute("dependentelement",rs.getString(22));
					 * //element
					 * .setAttribute("dependentelementvalue",rs.getString(23));
					 * element
					 * .setAttribute("parentParameterCode",rs.getString(5));
					 * //element.setAttribute("defaultvaluecondition",
					 * rs.getString(24));
					 */
					element.setAttribute("levelNo", rs.getString(1));
					element.setAttribute("parameterCode", rs.getString(4));

					element.setAttribute("parameterName", rs.getString(3));
					element.setAttribute("CriteriaCode", rs.getString(6));
					element.setAttribute("valueType", rs.getString(7));// represents
																		// the
																		// type
																		// of
																		// template(simple,horizontal,vertical)
					element.setAttribute("objectId", rs.getString(8));
					element.setAttribute("removeStatus", "0");
					element.setAttribute("functionName", rs.getString(9));
					element.setAttribute("eventName", rs.getString(10));
					element.setAttribute("eventValidationString", "0"); // not
																		// in
																		// new
																		// database

					element.setAttribute("property", rs.getString(11));
					element.setAttribute("isparameternamedisplayed",
							rs.getString(12));
					element.setAttribute("labelelementdisplaytype", "false"); // not
																				// in
																				// new
																				// database,
																				// so
																				// please
																				// check
					element.setAttribute("labelAlignment", rs.getString(13));
					element.setAttribute("elementAlignment", rs.getString(14));
					String defaultValue = "-";
					if(rs.getString(19) != null && rs.getString(19).length() > 0 )
						defaultValue = rs.getString(19);
					element.setAttribute("defaultValue", defaultValue); // not in new
																// database, so
																// please check
					element.setAttribute("isPrintable", rs.getString(15));
					element.setAttribute("queryCode", rs.getString(16));
					element.setAttribute("buttonName", rs.getString(17));
					element.setAttribute("callUrl", rs.getString(18));
					element.setAttribute("parentParameterCode", rs.getString(5));
					createElementInHTMLForm(replacementElement, element);

				}

				rs.close();
				pstmt.close();
				XPath xpath = XPathFactory.newInstance().newXPath();// documentConfigurationCacheManager.getXPathFactory().newXPath();
				if (parentParameterCode.equals(parameterCode)) {
					parentParameterCode = "";
				}

				// Document
				// newDoc=documentConfigurationCacheManager.getNewDocument();
				// newDoc.appendChild(newDoc.importNode(templateNode, true));
				// documentConfigurationCacheManager.createXMLforCheck("c:/testProfileProcedure.xml",newDoc);

				String xquery = "//testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@name='template#"
						+ parentParameterCode + parameterCode + "']";
				// LOGGER_INV.log(Level.INFO,"xquery  "+xquery);
				XPathExpression xpathExpresssion = xpath.compile(xquery);
				Object result = xpathExpresssion.evaluate(templateNode,
						XPathConstants.NODESET);
				NodeList childNodeList = (NodeList) result;
				Element foundElement = null;

				if (childNodeList.getLength() != 0) {
					foundElement = (Element) childNodeList.item(0);

					if (foundElement != null) {
						Node tdNode = foundElement.getParentNode();
						// LOGGER_INV.log(Level.INFO,"!--- removing found element--!");
						tdNode.removeChild(foundElement);
						// LOGGER_INV.log(Level.INFO,"!--- appending replacement element--!");
						/*
						 * removing the found element from existing template and
						 * adding new replacement element
						 */
						tdNode.appendChild(replacementElement);
					}

					Document actualDocumentObj = templateNode
							.getOwnerDocument();// InvestigationDocumentCacheManager.getInstance().loadDocumentObj(templateTypId);
					Source domSource = new DOMSource(actualDocumentObj);
					java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
					StreamResult streamResult = new StreamResult(baos);
					TransformerFactory transformerFactory = TransformerFactory
							.newInstance();
					transformerFactory.newTransformer().transform(domSource,
							streamResult);
					// styleSheetConfigurationCacheManager.getNewTransformer().transform(domSource,streamResult);
					loadDocumentStringToDatabase(insertionId, insertionRemarks,
							insertionRemarks, baos.toString(), session);

				} else {
					
					String paratype="";
					if(session.getAttribute("paraType")!=null)
					{
						paratype=session.getAttribute("paraType").toString();
					}
					Map<String, TestTemplateVO> testTemplateVOObjectMap = new HashMap<String, TestTemplateVO>();
					testTemplateVOObjectMap.put(nodeId, new TestTemplateVO());
					testTemplateVOObjectMap.get(nodeId).setTestCode(nodeId);
					testTemplateVOObjectMap.get(nodeId).setParaType(paratype);

					// LOGGER_INV.log(Level.INFO,"Element Not Found in template therefore recreating whole template");
					switch (Integer.parseInt(templateTypId)) {
					case 1:// test template form
						CreateDocumentTreeForTest(testTemplateVOObjectMap,
								session);
						tableName = "HIVT_TEST_PARAMETER_MST ";
						break;
					case 3:// test template form
						CreateDocumentTreeForTest(testTemplateVOObjectMap,
								session);
						tableName = "HIVT_REQFORM_TEST_PARAMETER_MST ";
						break;
						/*
					 * case 2:// sample template form
					 * CreateSampleDocumentTreeForTest(testTemplateVOObjectMap);
					 * tableName="HIVT_TEST_SAMPLE_PARAMETER_DTL"; break; case
					 * 3:// requisition template form
					 * CreateRequisitionDocumentTreeForTest
					 * (testTemplateVOObjectMap);
					 * tableName="HIVT_TEST_REQ_PARAMETER_DTL"; break;
					 * 
					 * case 5:// printing template form break; case 6:// Result
					 * Entry group document
					 * CreateTestFroupDocumentTreeForTest(testTemplateVOObjectMap
					 * ); tableName="HIVT_TEST_GROUP_PARAMETER_DTL"; break; case
					 * 7:// labRegister document break; case 8:// laboratory
					 * requisition form
					 * CreateDocumentTreeForLaboratoryRequestForm
					 * (testTemplateVOObjectMap);
					 * tableName="HIVT_LAB_REQ_PARAMETER_DTL";
					 * 
					 * break;
					 */
					case 9:

						break;
					case 10:
						break;

					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						if (!conn.isClosed()) {
							conn.close();
						}
					}
					//tx.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void createHorizontalMatrixHtml(Node clonedNodeHM,
			Node templateElement, Document newDocumentObjectHTML,
			Document newDocumentObjectHM) {

		System.out.println("<-----createHorizontalMatrixHtml---->");
		rowNos = 1;
		Map rowMap = getAllSimpleElementsAtLevel1HavingHMChild(clonedNodeHM);
		System.out.println(rowMap);
		Map columnMap = getAllHMElementsAtLevel2havingNoChidOrHMChild(clonedNodeHM);
		System.out.println(columnMap);
		if (columnMap != null && rowMap != null) {
			int columnMax = columnMap.size() + 1;
			int maxRow = 0;
			int rowNo = 1;
			maxRow = rowNo;
			int columnWidth = 100 / columnMax;
			int columnNo = 1;
			Element rowDetailsElement = newDocumentObjectHTML
					.createElement("rowDetails");
			rowDetailsElement.setAttribute("rowNo", "" + rowNos++);

			Element columnDetailsElement = newDocumentObjectHTML
					.createElement("columnDetails");// column Element
			columnDetailsElement.setAttribute("colNo", "1");
			columnNo++;
			columnDetailsElement.setAttribute("class", "tdfont");
			columnDetailsElement.setAttribute("width", "" + columnWidth + "%");
			columnDetailsElement.setAttribute("align", "center");
			Element tableElement = newDocumentObjectHTML.createElement("table");
			tableElement.setAttribute("width", "100%");
			tableElement.setAttribute("cellspacing", "0");
			tableElement.setAttribute("cellpadding", "0");
			tableElement.setAttribute("border", "0");
			Element rowElement = newDocumentObjectHTML.createElement("tr");
			Element columnElement = newDocumentObjectHTML.createElement("td");
			Element elementElement = newDocumentObjectHTML
					.createElement("element");// element Element
			elementElement.setAttribute("id", "");
			elementElement.setAttribute("name", "");
			elementElement.setAttribute("type", "");

			elementElement.setAttribute("idC", "");
			elementElement.setAttribute("value", "");

			columnElement.appendChild(elementElement);
			rowElement.appendChild(columnElement);
			tableElement.appendChild(rowElement);
			columnDetailsElement.appendChild(tableElement);
			rowDetailsElement.appendChild(columnDetailsElement);
			templateElement.appendChild(rowDetailsElement);
			Iterator columnArrayiterator = columnMap.keySet().iterator();
			while (columnArrayiterator.hasNext()) {

				String parameterCode = ((String) columnArrayiterator.next())
						.split("#")[0];
				String levelNo = "2";
				Element column = newDocumentObjectHTML
						.createElement("rowDetails");
				String classString = "tdfonthead";

				/*
				 * get the element with following parameter code which is
				 * horizontal matrix and at levelNo put xquery in work
				 */
				Node columnObj = getElementWithFollowingCode(parameterCode,
						levelNo, newDocumentObjectHM);

				columnDetailsElement = newDocumentObjectHTML
						.createElement("columnDetails");// column Element
				columnDetailsElement.setAttribute("colNo", "" + columnNo++);
				columnDetailsElement.setAttribute("class", "" + classString);
				columnDetailsElement.setAttribute("width", "" + columnWidth
						+ "%");
				columnDetailsElement.setAttribute("align", columnObj
						.getAttributes().getNamedItem("labelAlignment")
						.getNodeValue());

				tableElement = newDocumentObjectHTML.createElement("table");
				tableElement.setAttribute("width", "100%");
				tableElement.setAttribute("cellspacing", "0");
				tableElement.setAttribute("cellpadding", "0");
				tableElement.setAttribute("border", "0");

				rowElement = newDocumentObjectHTML.createElement("tr");
				columnElement = newDocumentObjectHTML.createElement("td");

				elementElement = newDocumentObjectHTML.createElement("element");// element
																				// Element
				elementElement.setAttribute("id", "");
				elementElement.setAttribute("name", "");
				elementElement.setAttribute("type", "");
				elementElement.setAttribute("idC", "label");
				elementElement.setAttribute("value", columnObj.getAttributes()
						.getNamedItem("parameterName").getNodeValue());
				elementElement.setAttribute("align", columnObj.getAttributes()
						.getNamedItem("labelAlignment").getNodeValue());
				setBoldAndUnderlinePropertiesForParameterLabel(elementElement,
						columnObj);
				columnElement.appendChild(elementElement);
				rowElement.appendChild(columnElement);

				tableElement.appendChild(rowElement);
				columnDetailsElement.appendChild(tableElement);// add element to
																// the
																// columndetails
																// element
				rowDetailsElement.appendChild(columnDetailsElement);// add
																	// columnelement
																	// to the
																	// rowDetailsElement
																	// element

			}

			templateElement.appendChild(rowDetailsElement);

			// String[] rowArray=(String[])rowMap.keySet().toArray();
			Iterator rowArrayiterator = rowMap.keySet().iterator();
			while (rowArrayiterator.hasNext()) {
				String rowParameterCode = ((String) rowArrayiterator.next())
						.split("#")[0];
				String rowLevelNo = "1";
				columnNo = 1;
				rowDetailsElement = newDocumentObjectHTML
						.createElement("rowDetails");
				rowDetailsElement.setAttribute("rowNo", "" + rowNos++);

				Node columnObj = getElementWithFollowingCode(rowParameterCode,
						rowLevelNo, newDocumentObjectHM);

				columnDetailsElement = newDocumentObjectHTML
						.createElement("columnDetails");// column Element
				columnDetailsElement.setAttribute("colNo", "" + columnNo++);
				columnDetailsElement.setAttribute("class", "tdfonthead");
				columnDetailsElement.setAttribute("width", "" + columnWidth
						+ "%");
				columnDetailsElement.setAttribute("align", "center");
				tableElement = newDocumentObjectHTML.createElement("table");
				tableElement.setAttribute("width", "100%");
				tableElement.setAttribute("cellspacing", "0");
				tableElement.setAttribute("cellpadding", "0");
				tableElement.setAttribute("border", "0");
				rowElement = newDocumentObjectHTML.createElement("tr");
				columnElement = newDocumentObjectHTML.createElement("td");
				elementElement = newDocumentObjectHTML.createElement("element");// element
																				// Element
				elementElement.setAttribute("id", "");
				elementElement.setAttribute("name", "");
				elementElement.setAttribute("type", "");
				elementElement.setAttribute("align", "center");
				elementElement.setAttribute("idC", "label");
				elementElement.setAttribute(
						"value",
						""
								+ columnObj.getAttributes()
										.getNamedItem("parameterName")
										.getNodeValue());
				setBoldAndUnderlinePropertiesForParameterLabel(elementElement,
						columnObj);
				columnElement.appendChild(elementElement);
				if (columnObj.getAttributes().getNamedItem("objectId")
						.getNodeValue().equals("label")) {

				} else {
					columnElement = newDocumentObjectHTML.createElement("td");
					elementElement = newDocumentObjectHTML
							.createElement("element");// element Element
					createElementInHTMLForm(elementElement, columnObj);
					columnElement.appendChild(elementElement);
				}

				rowElement.appendChild(columnElement);
				tableElement.appendChild(rowElement);
				columnDetailsElement.appendChild(tableElement);// add element to
																// the
																// columndetails
																// element
				rowDetailsElement.appendChild(columnDetailsElement);

				columnArrayiterator = columnMap.keySet().iterator();
				while (columnArrayiterator.hasNext()) {
					String parentParameterCode = rowParameterCode;
					String parameterCode = ((String) columnArrayiterator.next())
							.split("#")[0];
					String levelNo = "2";
					columnObj = getElementWithFollowingCode(
							parentParameterCode, parameterCode, levelNo,
							newDocumentObjectHM);
					columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");// column Element
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", "tdfont");
					columnDetailsElement.setAttribute("width", "" + columnWidth
							+ "%");
					columnDetailsElement.setAttribute("align", "center");

					tableElement = newDocumentObjectHTML.createElement("table");
					tableElement.setAttribute("width", "100%");
					tableElement.setAttribute("cellspacing", "0");
					tableElement.setAttribute("cellpadding", "0");
					tableElement.setAttribute("border", "0");

					rowElement = newDocumentObjectHTML.createElement("tr");
					columnElement = newDocumentObjectHTML.createElement("td");

					elementElement = newDocumentObjectHTML
							.createElement("element");// element Element
					elementElement.setAttribute("id", "");
					if (columnObj != null) {
						createElementInHTMLForm(elementElement, columnObj);
					} else {
						elementElement.setAttribute("id", "");
						elementElement.setAttribute("name", "");
						elementElement.setAttribute("type", "");

						elementElement.setAttribute("idC", "");
						elementElement.setAttribute("value", "");
					}
					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);
					columnDetailsElement.appendChild(tableElement);// add
																	// element
																	// to the
																	// column
																	// details
																	// element
					rowDetailsElement.appendChild(columnDetailsElement);
				}
				templateElement.appendChild(rowDetailsElement);

			}
		}
	}

	private Node getElementWithFollowingCode(String parentParameterCode,
			String parameterCode, String levelNo, Document newDocumentObjectHTML) {
		Node testDocumentNode = null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		String xpathQuery = "/TestTemplateHM/TestTemplate";
		int actuallevelNo = Integer.parseInt(levelNo);

		for (int i = 0; i < actuallevelNo; i++) {
			xpathQuery += "/testElement";
		}

		xpathQuery += "[@parentParameterCode='" + parentParameterCode
				+ "'][@parameterCode='" + parameterCode + "']";

		System.out.println(xpathQuery);
		try {
			XPathExpression expr = xpath.compile(xpathQuery);
			Object result = expr.evaluate(newDocumentObjectHTML,
					XPathConstants.NODESET);

			NodeList nodes = (NodeList) result;
			System.out.println("Length  =" + nodes.getLength());
			testDocumentNode = nodes.item(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testDocumentNode;
	}

	private Map getAllHMElementsAtLevel2havingNoChidOrHMChild(Node clonedNodeHM) {
		Map<String, Map> columnMap = null;
		if (clonedNodeHM.hasChildNodes()) {
			NodeList childNodeList = clonedNodeHM.getChildNodes();
			for (int i = 0; i < childNodeList.getLength(); i++) {
				Node child = childNodeList.item(i);
				if (child.getAttributes().getNamedItem("valueType") != null
						&& (child.getAttributes().getNamedItem("valueType")
								.getNodeValue().equals("2") == true)) {
					if (child.hasChildNodes()) {
						NodeList childsChildNodeList = child.getChildNodes();
						for (int j = 0; j < childsChildNodeList.getLength(); j++) {
							Node childsChild = childsChildNodeList.item(j);
							if (childsChild.getAttributes().getNamedItem(
									"valueType") != null
									&& (childsChild.getAttributes()
											.getNamedItem("valueType")
											.getNodeValue().equals("3") == true)) {
								if (columnMap == null) {
									columnMap = new HashMap<String, Map>();
								}

								String parameterCode = childsChild
										.getAttributes()
										.getNamedItem("parameterCode")
										.getNodeValue();
								if (columnMap.containsKey(parameterCode) == false) {

									if (childsChild.hasChildNodes()) {

										columnMap.put(
												parameterCode,
												getAllChildOfHMElement(
														childsChild, null,
														parameterCode));
									} else {
										columnMap.put(parameterCode, null);
									}
								} else {
									if (childsChild.hasChildNodes()) {
										columnMap
												.put(parameterCode,
														getAllChildOfHMElement(
																childsChild,
																columnMap
																		.get(childsChild
																				.getAttributes()
																				.getNamedItem(
																						"parameterCode")
																				.getNodeValue()),
																parameterCode));
									}

								}

							}
						}

					}
				}
			}

		}

		return columnMap;
	}

	private Map getAllChildOfHMElement(Node child, Map<String, Map> childMap,
			String parentParameterCode) {
		if (childMap == null)
			childMap = new HashMap<String, Map>();

		NodeList childsChildList = child.getChildNodes();
		for (int i = 0; i < childsChildList.getLength(); i++) {
			Node childsChild = childsChildList.item(i);
			String parameterCode = childsChild.getAttributes()
					.getNamedItem("parameterCode").getNodeValue().substring(8);
			if (childsChild.getAttributes().getNamedItem("valueType")
					.getNodeValue().equals("3")) {
				if (childMap.containsKey(parentParameterCode
						+ childsChild.getAttributes()
								.getNamedItem("parameterCode").getNodeValue())) {
					if (childsChild.hasChildNodes() == true) {
						childMap.put(
								parentParameterCode + parameterCode,
								getAllChildOfHMElement(
										childsChild,
										childMap.get(childsChild
												.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue()),
										parentParameterCode + parameterCode));
					}

				} else {
					if (childsChild.hasChildNodes() == false)
						childMap.put(
								parentParameterCode
										+ childsChild.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue(), null);
					else
						childMap.put(
								parentParameterCode
										+ childsChild.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue(),
								getAllChildOfHMElement(
										childsChild,
										childMap.get(childsChild
												.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue()),
										parentParameterCode + parameterCode));
				}
			}

		}

		return childMap;
	}

	private Node getElementWithFollowingCode(String parameterCode,
			String levelNo, Document newDocumentObjectHTML) {
		Node testDocumentNode = null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		String xpathQuery = "/TestTemplateHM/TestTemplate";
		int actuallevelNo = Integer.parseInt(levelNo);

		for (int i = 0; i < actuallevelNo; i++) {
			xpathQuery += "/testElement";
		}

		xpathQuery += "[@parameterCode='" + parameterCode + "'][@levelNo='"
				+ levelNo + "']";
		System.out.println(xpathQuery);
		try {
			XPathExpression expr = xpath.compile(xpathQuery);
			Object result = expr.evaluate(newDocumentObjectHTML,
					XPathConstants.NODESET);

			NodeList nodes = (NodeList) result;
			System.out.println("Length  =" + nodes.getLength());
			if (nodes.getLength() != 0) {
				for (int i = 0; i < nodes.getLength(); i++) {
					testDocumentNode = nodes.item(i);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testDocumentNode;
	}

	private Map getAllSimpleElementsAtLevel1HavingHMChild(Node clonedNodeHM) {
		Map<String, Map> rowMap = null;
		if (clonedNodeHM.hasChildNodes()) {
			NodeList childNodeList = clonedNodeHM.getChildNodes();
			for (int i = 0; i < childNodeList.getLength(); i++) {
				System.out.println(" child are found ");
				Node child = childNodeList.item(i);
				// System.out.println(child.getAttributes().getNamedItem("parameterName").getNodeValue()+" :  valueType ="+child.getAttributes().getNamedItem("valueType").getNodeValue());;
				if (child.getAttributes().getNamedItem("valueType") != null
						&& (child.getAttributes().getNamedItem("valueType")
								.getNodeValue().equals("2") == true)) {
					if (child.hasChildNodes()) {
						NodeList childsChildNodeList = child.getChildNodes();
						for (int j = 0; j < childsChildNodeList.getLength(); j++) {
							System.out.println(" child childs are found ");
							Node childsChild = childsChildNodeList.item(j);
							System.out.println(childsChild.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue()
									+ " :  valueType ="
									+ childsChild.getAttributes()
											.getNamedItem("valueType")
											.getNodeValue());
							;
							if (childsChild.getAttributes().getNamedItem(
									"valueType") != null
									&& (childsChild.getAttributes()
											.getNamedItem("valueType")
											.getNodeValue().equals("3") == true)) {
								if (rowMap == null) {
									rowMap = new HashMap<String, Map>();
								}

								System.out.println("Adding  Parameter Code :"
										+ child.getAttributes().getNamedItem(
												"parameterCode")
										+ "   Parameter Name="
										+ child.getAttributes().getNamedItem(
												"parameterName"));
								rowMap.put(
										child.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue(), null);

							}
						}

					}
				}
			}

		}

		return rowMap;
	}

	private void createElementInHTMLForm(Element elementElement, Node columnObj) {

		System.out.println("createElementInHTMLForm=   "
				+ columnObj.getAttributes().getNamedItem("objectId")
						.getNodeValue());
		if (columnObj.getAttributes().getNamedItem("objectId").getNodeValue()
				.equalsIgnoreCase("textArea")) {
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "textArea");
			elementElement.setAttribute("type", "");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			// elementElement.setAttribute("editor",elementProperties[0] );
			// elementElement.setAttribute("rows",elementProperties[1] );
			// elementElement.setAttribute("cols",elementProperties[2] );
			elementElement.setAttribute("editor", elementProperties[0]);
			elementElement.setAttribute("rows", elementProperties[1]);
			elementElement.setAttribute("cols", elementProperties[2]);
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());

		}  else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("textBox")) {
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute(
					"id","");
			elementElement.setAttribute("idC", "input");
			elementElement.setAttribute("type", "text");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			elementElement.setAttribute("size", elementProperties[0]);
			elementElement.setAttribute("maxlength", elementProperties[1]);
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			elementElement
			.setAttribute("changeTableId","");
			if(elementProperties.length>4)
			elementElement
			.setAttribute("isDate",elementProperties[4]);

		} 

		/*added by chandan*/
		else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("HyperLink")) {
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute(
					"id","");
			elementElement.setAttribute("idC", "hyperlink");
			elementElement.setAttribute("type", "text");
			elementElement.setAttribute("tbl", "");;
			elementElement.setAttribute("value", columnObj.getAttributes()
					.getNamedItem("callUrl").getNodeValue());
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());

			elementElement.setAttribute("hyperlinkname", columnObj.getAttributes()
					.getNamedItem("hypername").getNodeValue());
			elementElement.setAttribute("val", columnObj.getAttributes()
					.getNamedItem("callUrl").getNodeValue());
			System.out.println("HYPER NAME:"
					+ columnObj.getAttributes()
							.getNamedItem("hypername").getNodeValue());
		}
		
		/*added by chandan for checkbox*/
		 else if (columnObj.getAttributes().getNamedItem("objectId")
					.getNodeValue().equalsIgnoreCase("CheckBox")) {
				elementElement.setAttribute(
						"name",
						"template#"
								+ columnObj.getAttributes()
										.getNamedItem("parentParameterCode")
										.getNodeValue()
								+ columnObj.getAttributes()
										.getNamedItem("parameterCode")
										.getNodeValue());
				elementElement.setAttribute(
						"id","");
				elementElement.setAttribute("idC", "checkbox");
				elementElement.setAttribute("type", "text");
				elementElement.setAttribute("checked", "");;
				elementElement.setAttribute("value", "");
				elementElement.setAttribute("align", columnObj.getAttributes()
						.getNamedItem("elementAlignment").getNodeValue());
				System.out.println("element Align"
						+ columnObj.getAttributes()
								.getNamedItem("elementAlignment").getNodeValue());
				
				String[] elementProperties = columnObj.getAttributes()
						.getNamedItem("property").getNodeValue().split("#");
elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("labelAlignment").getNodeValue());
elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
				/*elementElement.setAttribute("eventName", columnObj.getAttributes()
						.getNamedItem("eventName").getNodeValue());
				elementElement.setAttribute("eventValidationString", columnObj
						.getAttributes().getNamedItem("eventValidationString")
						.getNodeValue());*/
				/*String[] elementProperties = columnObj.getAttributes()
						.getNamedItem("property").getNodeValue().split("#");
				elementElement.setAttribute("size", elementProperties[0]);
				elementElement.setAttribute("maxlength", elementProperties[1]);*/
				elementElement.setAttribute("defaultValue", columnObj
						.getAttributes().getNamedItem("defaultValue")
						.getNodeValue());
				elementElement
						.setAttribute("isPrintable", columnObj.getAttributes()
								.getNamedItem("isPrintable").getNodeValue());
				

			} 
		
		else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("label")) {
			elementElement.setAttribute("idC", "label");
			elementElement.setAttribute("name", "");
			elementElement.setAttribute("value", columnObj.getAttributes()
					.getNamedItem("parameterName").getNodeValue());
			elementElement.setAttribute("type", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("labelAlignment").getNodeValue());
			System.out.println("Label Align"
					+ columnObj.getAttributes().getNamedItem("labelAlignment")
							.getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("select")) {
			System.out.println(columnObj.getAttributes()
					.getNamedItem("functionName").getNodeValue());
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "Select");
			elementElement.setAttribute("type", "");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			Element optionsElement = elementElement.getOwnerDocument()
					.createElement("options");
			optionsElement = getAllOptionsOfElement(columnObj.getAttributes()
					.getNamedItem("parameterCode").getNodeValue(),
					optionsElement);
			elementElement.appendChild(optionsElement);
			if (columnObj.getAttributes().getNamedItem("functionName")
					.getNodeValue().equals("-1")) {

			} else {
				elementElement.setAttribute("functionName", columnObj
						.getAttributes().getNamedItem("functionName")
						.getNodeValue());
			}

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("queryValue")) {

			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "queryValue");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("queryCode", columnObj.getAttributes()
					.getNamedItem("queryCode").getNodeValue());
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("queryCombo")) {

			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "queryValue");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("queryCode", columnObj.getAttributes()
					.getNamedItem("queryCode").getNodeValue());
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("button")) {
			System.out
					.println("<----------------------button-------------------------------->");
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "button");
			elementElement.setAttribute("value", columnObj.getAttributes()
					.getNamedItem("buttonName").getNodeValue());
			elementElement.setAttribute("callUrl", columnObj.getAttributes()
					.getNamedItem("callUrl").getNodeValue());
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("imagesection")) {
			System.out
					.println("<----------------------imagesection-------------------------------->");
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "imagesection");
			elementElement.setAttribute("value", columnObj.getAttributes()
					.getNamedItem("buttonName").getNodeValue());
			elementElement.setAttribute("callUrl", columnObj.getAttributes()
					.getNamedItem("callUrl").getNodeValue());
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		} 
		else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("listbox")) {
			System.out.println(columnObj.getAttributes()
					.getNamedItem("functionName").getNodeValue());
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "listbox");
			elementElement.setAttribute("type", "");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			Element optionsElement = elementElement.getOwnerDocument()
					.createElement("options");
			optionsElement = getAllOptionsOfElement(columnObj.getAttributes()
					.getNamedItem("parameterCode").getNodeValue(),
					optionsElement);
			elementElement.appendChild(optionsElement);
			if (columnObj.getAttributes().getNamedItem("functionName")
					.getNodeValue().equals("-1")) {

			}  else {
				elementElement.setAttribute("functionName", columnObj
						.getAttributes().getNamedItem("functionName")
						.getNodeValue());
			}
		}else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("autocomplete")) {
			System.out.println(columnObj.getAttributes()
					.getNamedItem("functionName").getNodeValue());
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "autocomplete");
			elementElement.setAttribute("autocompletion", "true");
			elementElement.setAttribute("onfocus", "autovalue()");
			elementElement.setAttribute("id",  "auto"+ columnObj.getAttributes()
					.getNamedItem("parentParameterCode")
					.getNodeValue()
			+ columnObj.getAttributes()
					.getNamedItem("parameterCode")
					.getNodeValue());
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("type", "text");
			elementElement.setAttribute("list",  "auto"+ columnObj.getAttributes()
					.getNamedItem("parentParameterCode")
					.getNodeValue()
			+ columnObj.getAttributes()
					.getNamedItem("parameterCode")
					.getNodeValue());
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			Element optionsElements = elementElement.getOwnerDocument()
					.createElement("options");
			optionsElements = getAllOptionsOfElement(columnObj.getAttributes()
					.getNamedItem("parameterCode").getNodeValue(),
					optionsElements);
			elementElement.appendChild(optionsElements);
			if (columnObj.getAttributes().getNamedItem("functionName")
					.getNodeValue().equals("-1")) {

			} else {
				elementElement.setAttribute("functionName", columnObj
						.getAttributes().getNamedItem("functionName")
						.getNodeValue());
			}

		}
		else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("SnomedCT")) {
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute(
					"id","");
			elementElement.setAttribute("idC", "snomedct");
			elementElement.setAttribute("type", "text");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			System.out.println("element Align"
					+ columnObj.getAttributes()
							.getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			elementElement.setAttribute("size", elementProperties[0]);
			elementElement.setAttribute("maxlength", elementProperties[1]);
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			elementElement
			.setAttribute("changeTableId","");
			if(elementProperties.length>4)
			elementElement
			.setAttribute("isDate",elementProperties[4]);

		} 
else {

		}

	}

	public static Element getAllOptionsOfElement(String testParameterCode,
			Element optionsElement) {

		// change to hivt test para combo master
		/*
		 * String query=
		 * " Select HGNUM_SEQ_ID, HGSTR_VALUE from HIVT_TEST_PARAM_CNTR_VAL_DTL "
		 * + " where GNUM_TEST_PARAMETER_CODE=? AND GNUM_HOSPITAL_CODE=?" +
		 * " AND GNUM_IS_VALID=1";
		 */
		String query = "Select HGNUM_SEQ_ID, HGSTR_VALUE from HIVT_TEST_PARAM_COMBO_MST  where gnum_parameter_code=? and GNUM_TEST_CODE=?  AND GNUM_IS_VALID=1";
		// Siddharth: copied from PGI code. change this for new database(PHDM)
		/*
		 * switch (this.creationMode) { case 2: query=
		 * " Select HGNUM_SEQ_ID, HGSTR_VALUE from HIVT_TEST_REQPARAMCNTR_VAL_DTL "
		 * + " where GNUM_TEST_PARAMETER_CODE=? AND GNUM_HOSPITAL_CODE=?" +
		 * " AND GNUM_IS_VALID=1"; break; case 3: query=
		 * " Select HGNUM_SEQ_ID, HGSTR_VALUE from HIVT_TEST_SAMPARAMCNTR_VAL_DTL "
		 * + " where GNUM_TEST_PARAMETER_CODE=? AND GNUM_HOSPITAL_CODE=?" +
		 * " AND GNUM_IS_VALID=1"; break; case 4: query=
		 * " Select HGNUM_SEQ_ID, HGSTR_VALUE from HIVT_LAB_REQPARAMCNTR_VAL_DTL "
		 * + " where GNUM_LAB_PARAMETER_CODE=? AND GNUM_HOSPITAL_CODE=?" +
		 * " AND GNUM_IS_VALID=1"; break; case 5: query=
		 * " Select HGNUM_SEQ_ID, HGSTR_VALUE from HIVT_TESTGRP_PARA_CNTR_VAL_DTL"
		 * + " where GNUM_TEST_GROUP_PARAMETER_CODE=? AND GNUM_HOSPITAL_CODE=?"
		 * + " AND GNUM_IS_VALID=1"; break;
		 * 
		 * default: break;
		 * 
		 * }
		 */
		Connection conn = null;
		JDBCTransactionContext tx= null;
		System.out.println("Query  : " + query);
		System.out.println("Options Test Parameter Code: " + testParameterCode);
		PreparedStatement pstmt;
		try {
			tx = new JDBCTransactionContext();
			tx.begin();
			conn = tx.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, testParameterCode.substring(5));// parameter code
			pstmt.setString(2, testParameterCode.substring(0, 5)); // test code
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Element newOptionElement = optionsElement.getOwnerDocument()
						.createElement("option");
				newOptionElement.setAttribute("value", rs.getString(1));
				newOptionElement.setAttribute("label", rs.getString(2));
				newOptionElement.setAttribute("selected", "false");
				optionsElement.appendChild(newOptionElement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//tx.close();
		}

		return optionsElement;

	}

	public void createVerticalMatrix(Node child, Node parent) {
		boolean status = removeAllSimpleElements(child, parent);
		System.out.println("1:" + status);
		if (status) {
			parent.removeChild(child);
		}

		if (parent.hasChildNodes()) {
			status = removeAllHMElements(child, parent);
			System.out.println("1:" + status);
			if (status) {
				parent.removeChild(child);
			}
		}

	}

	public void createHorizontalMatrix(Node child, Node parent) {
		boolean status = removeAllSimpleElements(child, parent);
		System.out.println("1:" + status);
		if (status) {
			parent.removeChild(child);
		}

		if (parent.hasChildNodes()) {
			status = removeAllVMElements(child, parent);
			System.out.println("1:" + status);
			if (status) {
				parent.removeChild(child);
			}
		}

	}

	private boolean removeAllSimpleElements(Node child, Node parent) {
		System.out.println("removeAllSimpleElements  ");
		System.out.println("\n\n parent   ="
				+ parent.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
		System.out.println(" child   ="
				+ child.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
		boolean status = false;
		if (child.hasChildNodes() == false) {
			if (child.getAttributes().getNamedItem("valueType") != null
					&& (child.getAttributes().getNamedItem("valueType")
							.getNodeValue().equals("1") == true)) {
				System.out.println(child.getAttributes()
						.getNamedItem("valueType").getNodeValue()
						+ " removing child with no child    ="
						+ child.getAttributes().getNamedItem("parameterName")
								.getNodeValue());
				// parent.removeChild(child);
				System.out.println(" -- child  removed  ---  ");
				status = true;
			}

		} else {
			if (child.getAttributes().getNamedItem("valueType") != null
					&& (child.getAttributes().getNamedItem("valueType")
							.getNodeValue().equals("1") == true)) {
				System.out.println(child.getAttributes()
						.getNamedItem("valueType").getNodeValue()
						+ " removing child   with child  ="
						+ child.getAttributes().getNamedItem("parameterName")
								.getNodeValue());
				// parent.removeChild(child);
				System.out.println(" -- child  removed  ---  ");
				status = true;
			} else {
				NodeList childsChildList = child.getChildNodes();
				for (int i = 0; i < childsChildList.getLength(); i++) {
					Node childsChild = childsChildList.item(i);
					System.out.println("\n\n childsChild   ="
							+ childsChild.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());
					System.out.println(" child   ="
							+ child.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());

					boolean newStatus = removeAllSimpleElements(childsChild,
							child);
					System.out.println("new Status=" + newStatus);
					if (newStatus) {
						System.out.println("decreasing i=" + i);
						child.removeChild(childsChild);
						i--;
						System.out.println("i=" + i);
					}
				}

				if (child.hasChildNodes() == false) {
					Node parentsParent = parent.getParentNode();
					if (parentsParent != null) {
						System.out.println(" removing parent with child    ="
								+ child.getAttributes()
										.getNamedItem("parameterName")
										.getNodeValue());
						// parent.removeChild(child);
						System.out.println(" -- parent  removed  ---  ");
						status = true;
					}
				} else {
					status = false;
				}

			}
		}

		/**/

		return status;

	}

	public void createSimpleMatrix(Node child, Node parent) {
		boolean status = removeAllHMElements(child, parent);
		System.out.println("1:" + status);
		if (status) {
			parent.removeChild(child);
		}

		if (parent.hasChildNodes()) {
			status = removeAllVMElements(child, parent);
			System.out.println("1:" + status);
			if (status) {
				parent.removeChild(child);
			}
		}

	}

	private boolean removeAllHMElements(Node child, Node parent) {
		System.out.println("removeAllHMElements  ");
		System.out.println("\n\n parent   ="
				+ parent.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
		System.out.println(" child   ="
				+ child.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
		boolean status = false;
		if (child.hasChildNodes() == false) {
			if (child.getAttributes().getNamedItem("valueType") != null
					&& (child.getAttributes().getNamedItem("valueType")
							.getNodeValue().equals("3") == true)) {
				System.out.println(child.getAttributes()
						.getNamedItem("valueType").getNodeValue()
						+ " removing child with no child    ="
						+ child.getAttributes().getNamedItem("parameterName")
								.getNodeValue());
				// parent.removeChild(child);
				System.out.println(" -- child  removed  ---  ");
				status = true;
			}

		} else {
			if (child.getAttributes().getNamedItem("valueType") != null
					&& (child.getAttributes().getNamedItem("valueType")
							.getNodeValue().equals("3") == true)) {
				System.out.println(child.getAttributes()
						.getNamedItem("valueType").getNodeValue()
						+ " removing child   with child  ="
						+ child.getAttributes().getNamedItem("parameterName")
								.getNodeValue());
				// parent.removeChild(child);
				System.out.println(" -- child  removed  ---  ");
				status = true;
			} else {
				NodeList childsChildList = child.getChildNodes();
				for (int i = 0; i < childsChildList.getLength(); i++) {
					Node childsChild = childsChildList.item(i);
					System.out.println("\n\n childsChild   ="
							+ childsChild.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());
					System.out.println(" child   ="
							+ child.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());

					boolean newStatus = removeAllHMElements(childsChild, child);
					System.out.println("new Status=" + newStatus);
					if (newStatus) {
						System.out.println("decreasing i=" + i);
						child.removeChild(childsChild);
						i--;
						System.out.println("i=" + i);
					}
				}

				if (child.hasChildNodes() == false) {
					Node parentsParent = parent.getParentNode();
					if (parentsParent != null) {
						System.out.println(" removing parent with child    ="
								+ child.getAttributes()
										.getNamedItem("parameterName")
										.getNodeValue());
						// parent.removeChild(child);
						System.out.println(" -- parent  removed  ---  ");
						status = true;
					}
				} else {
					status = false;
				}

			}
		}

		/**/

		return status;
	}

	private boolean removeAllVMElements(Node child, Node parent) {
		System.out.println("removeAllVMElements  ");
		System.out.println("\n\n parent   ="
				+ parent.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
		System.out.println(" child   ="
				+ child.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
		boolean status = false;
		if (child.hasChildNodes() == false) {
			if (child.getAttributes().getNamedItem("valueType") != null
					&& (child.getAttributes().getNamedItem("valueType")
							.getNodeValue().equals("4") == true)) {
				System.out.println(child.getAttributes()
						.getNamedItem("valueType").getNodeValue()
						+ " removing child with no child    ="
						+ child.getAttributes().getNamedItem("parameterName")
								.getNodeValue());
				// parent.removeChild(child);
				System.out.println(" -- child  removed  ---  ");
				status = true;
			}

		} else {
			if (child.getAttributes().getNamedItem("valueType") != null
					&& (child.getAttributes().getNamedItem("valueType")
							.getNodeValue().equals("4") == true)) {
				System.out.println(child.getAttributes()
						.getNamedItem("valueType").getNodeValue()
						+ " removing child   with child  ="
						+ child.getAttributes().getNamedItem("parameterName")
								.getNodeValue());
				// parent.removeChild(child);
				System.out.println(" -- child  removed  ---  ");
				status = true;
			} else {
				NodeList childsChildList = child.getChildNodes();
				for (int i = 0; i < childsChildList.getLength(); i++) {
					Node childsChild = childsChildList.item(i);
					System.out.println("\n\n childsChild   ="
							+ childsChild.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());
					System.out.println(" child   ="
							+ child.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue());

					boolean newStatus = removeAllVMElements(childsChild, child);
					System.out.println("new Status=" + newStatus);
					if (newStatus) {
						System.out.println("decreasing i=" + i);
						child.removeChild(childsChild);
						i--;
						System.out.println("i=" + i);
					}
				}

				if (child.hasChildNodes() == false) {
					Node parentsParent = parent.getParentNode();
					if (parentsParent != null) {
						System.out.println(" removing parent with child    ="
								+ child.getAttributes()
										.getNamedItem("parameterName")
										.getNodeValue());
						// parent.removeChild(child);
						System.out.println(" -- parent  removed  ---  ");
						status = true;
					}
				} else {
					status = false;
				}

			}
		}

		/**/

		return status;

	}

	public Node getMatchingParentElement(Node child, Node originalChild) {
		Node parent = child.getParentNode();
		if ((parent == null)
				|| (parent.getAttributes() == null)
				|| (Integer.parseInt(parent.getAttributes()
						.getNamedItem("levelNo").getNodeValue()) == Integer
						.parseInt(originalChild.getAttributes()
								.getNamedItem("levelNo").getNodeValue()))) {
			return parent.getParentNode();
		} else
			return getMatchingParentElement(parent, originalChild);

	}

	private Map getAllSimpleElementsAtLevel1HavingVMChild(Node clonedNodeHM) {
		Map<String, Map> rowMap = null;
		if (clonedNodeHM.hasChildNodes()) {
			NodeList childNodeList = clonedNodeHM.getChildNodes();
			for (int i = 0; i < childNodeList.getLength(); i++) {
				System.out.println(" child are found ");
				Node child = childNodeList.item(i);
				// System.out.println(child.getAttributes().getNamedItem("parameterName").getNodeValue()+" :  valueType ="+child.getAttributes().getNamedItem("valueType").getNodeValue());;
				if (child.getAttributes().getNamedItem("valueType") != null
						&& (child.getAttributes().getNamedItem("valueType")
								.getNodeValue().equals("2") == true)) {
					if (child.hasChildNodes()) {
						NodeList childsChildNodeList = child.getChildNodes();
						for (int j = 0; j < childsChildNodeList.getLength(); j++) {
							System.out.println(" child childs are found ");
							Node childsChild = childsChildNodeList.item(j);
							System.out.println(childsChild.getAttributes()
									.getNamedItem("parameterName")
									.getNodeValue()
									+ " :  valueType ="
									+ childsChild.getAttributes()
											.getNamedItem("valueType")
											.getNodeValue());
							;
							if (childsChild.getAttributes().getNamedItem(
									"valueType") != null
									&& (childsChild.getAttributes()
											.getNamedItem("valueType")
											.getNodeValue().equals("4") == true)) {
								if (rowMap == null) {
									rowMap = new HashMap<String, Map>();
								}

								System.out.println("Adding  Parameter Code :"
										+ child.getAttributes().getNamedItem(
												"parameterCode")
										+ "   Parameter Name="
										+ child.getAttributes().getNamedItem(
												"parameterName"));
								rowMap.put(
										child.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue(), null);

							}
						}

					}
				}
			}

		}

		return rowMap;
	}

	private Map getAllHMElementsAtLevel2havingNoChidOrVMChild(Node clonedNodeHM) {
		Map<String, Map> columnMap = null;
		if (clonedNodeHM.hasChildNodes()) {
			NodeList childNodeList = clonedNodeHM.getChildNodes();
			for (int i = 0; i < childNodeList.getLength(); i++) {
				Node child = childNodeList.item(i);
				if (child.getAttributes().getNamedItem("valueType") != null
						&& (child.getAttributes().getNamedItem("valueType")
								.getNodeValue().equals("2") == true)) {
					if (child.hasChildNodes()) {
						NodeList childsChildNodeList = child.getChildNodes();
						for (int j = 0; j < childsChildNodeList.getLength(); j++) {
							Node childsChild = childsChildNodeList.item(j);
							if (childsChild.getAttributes().getNamedItem(
									"valueType") != null
									&& (childsChild.getAttributes()
											.getNamedItem("valueType")
											.getNodeValue().equals("4") == true)) {
								if (columnMap == null) {
									columnMap = new HashMap<String, Map>();
								}

								String parameterCode = childsChild
										.getAttributes()
										.getNamedItem("parameterCode")
										.getNodeValue();
								if (columnMap.containsKey(parameterCode) == false) {

									if (childsChild.hasChildNodes()) {

										columnMap.put(
												parameterCode,
												getAllChildOfVMElement(
														childsChild, null,
														parameterCode));
									} else {
										columnMap.put(parameterCode, null);
									}
								} else {
									if (childsChild.hasChildNodes()) {
										columnMap
												.put(parameterCode,
														getAllChildOfVMElement(
																childsChild,
																columnMap
																		.get(childsChild
																				.getAttributes()
																				.getNamedItem(
																						"parameterCode")
																				.getNodeValue()),
																parameterCode));
									}

								}

							}
						}

					}
				}
			}

		}

		return columnMap;
	}

	private Map getAllChildOfVMElement(Node child, Map<String, Map> childMap,
			String parentParameterCode) {
		if (childMap == null)
			childMap = new HashMap<String, Map>();

		NodeList childsChildList = child.getChildNodes();
		for (int i = 0; i < childsChildList.getLength(); i++) {
			Node childsChild = childsChildList.item(i);
			String parameterCode = childsChild.getAttributes()
					.getNamedItem("parameterCode").getNodeValue().substring(8);
			if (childsChild.getAttributes().getNamedItem("valueType")
					.getNodeValue().equals("4")) {
				if (childMap.containsKey(parentParameterCode
						+ childsChild.getAttributes()
								.getNamedItem("parameterCode").getNodeValue())) {
					if (childsChild.hasChildNodes() == true) {
						childMap.put(
								parentParameterCode + parameterCode,
								getAllChildOfHMElement(
										childsChild,
										childMap.get(childsChild
												.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue()),
										parentParameterCode + parameterCode));
					}

				} else {
					if (childsChild.hasChildNodes() == false)
						childMap.put(
								parentParameterCode
										+ childsChild.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue(), null);
					else
						childMap.put(
								parentParameterCode
										+ childsChild.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue(),
								getAllChildOfHMElement(
										childsChild,
										childMap.get(childsChild
												.getAttributes()
												.getNamedItem("parameterCode")
												.getNodeValue()),
										parentParameterCode + parameterCode));
				}
			}

		}

		return childMap;
	}

	private void createVerticalMatrixHtml(Node clonedNodeHM,
			Node templateElement, Document newDocumentObjectHTML,
			Document newDocumentObjectHM) {

		System.out.println("<-----createVerticalMatrixHtml---->");
		rowNos = 1;
		Map columnMap = getAllSimpleElementsAtLevel1HavingVMChild(clonedNodeHM);
		System.out.println(columnMap);
		Map rowMap = getAllHMElementsAtLevel2havingNoChidOrVMChild(clonedNodeHM);
		System.out.println(columnMap);
		if (columnMap != null && rowMap != null) {
			int columnMax = columnMap.size() + 1;
			int maxRow = 0;
			int rowNo = 1;
			maxRow = rowNo;
			int columnWidth = 100 / columnMax;
			int columnNo = 1;
			Element rowDetailsElement = newDocumentObjectHTML
					.createElement("rowDetails");
			rowDetailsElement.setAttribute("rowNo", "" + rowNos++);

			Element columnDetailsElement = newDocumentObjectHTML
					.createElement("columnDetails");// column Element
			columnDetailsElement.setAttribute("colNo", "1");
			columnNo++;
			columnDetailsElement.setAttribute("class", "tdfont");
			columnDetailsElement.setAttribute("width", "" + columnWidth + "%");
			columnDetailsElement.setAttribute("align", "center");
			Element tableElement = newDocumentObjectHTML.createElement("table");
			tableElement.setAttribute("width", "100%");
			tableElement.setAttribute("cellspacing", "0");
			tableElement.setAttribute("cellpadding", "0");
			tableElement.setAttribute("border", "0");
			Element rowElement = newDocumentObjectHTML.createElement("tr");
			Element columnElement = newDocumentObjectHTML.createElement("td");
			Element elementElement = newDocumentObjectHTML
					.createElement("element");// element Element
			elementElement.setAttribute("id", "");
			elementElement.setAttribute("name", "");
			elementElement.setAttribute("type", "");
			elementElement.setAttribute("idC", "");
			elementElement.setAttribute("value", "");
			columnElement.appendChild(elementElement);
			rowElement.appendChild(columnElement);
			tableElement.appendChild(rowElement);
			columnDetailsElement.appendChild(tableElement);
			rowDetailsElement.appendChild(columnDetailsElement);
			templateElement.appendChild(rowDetailsElement);
			Iterator columnArrayiterator = columnMap.keySet().iterator();
			while (columnArrayiterator.hasNext()) {

				String parameterCode = ((String) columnArrayiterator.next())
						.split("#")[0];
				String levelNo = "1";
				Element column = newDocumentObjectHTML
						.createElement("rowDetails");
				String classString = "tdfonthead";

				/*
				 * get the element with following parameter code which is
				 * horizontal matrix and at levelNo put xquery in work
				 */
				Node columnObj = getElementWithFollowingCode(parameterCode,
						levelNo, newDocumentObjectHM);

				columnDetailsElement = newDocumentObjectHTML
						.createElement("columnDetails");// column Element
				columnDetailsElement.setAttribute("colNo", "" + columnNo++);
				columnDetailsElement.setAttribute("class", "" + classString);
				columnDetailsElement.setAttribute("width", "" + columnWidth
						+ "%");
				columnDetailsElement.setAttribute("align", columnObj
						.getAttributes().getNamedItem("labelAlignment")
						.getNodeValue());

				tableElement = newDocumentObjectHTML.createElement("table");
				tableElement.setAttribute("width", "100%");
				tableElement.setAttribute("cellspacing", "0");
				tableElement.setAttribute("cellpadding", "0");
				tableElement.setAttribute("border", "0");

				rowElement = newDocumentObjectHTML.createElement("tr");
				columnElement = newDocumentObjectHTML.createElement("td");

				elementElement = newDocumentObjectHTML.createElement("element");// element
																				// Element
				elementElement.setAttribute("id", "");
				elementElement.setAttribute("name", "");
				elementElement.setAttribute("type", "");
				elementElement.setAttribute("idC", "label");
				elementElement.setAttribute("value", columnObj.getAttributes()
						.getNamedItem("parameterName").getNodeValue());
				elementElement.setAttribute("align", columnObj.getAttributes()
						.getNamedItem("labelAlignment").getNodeValue());
				setBoldAndUnderlinePropertiesForParameterLabel(elementElement,
						columnObj);
				columnElement.appendChild(elementElement);
				rowElement.appendChild(columnElement);

				tableElement.appendChild(rowElement);

				rowElement = newDocumentObjectHTML.createElement("tr");
				columnElement = newDocumentObjectHTML.createElement("td");
				elementElement = newDocumentObjectHTML.createElement("element");

				createElementInHTMLForm(elementElement, columnObj);

				columnElement.appendChild(elementElement);
				rowElement.appendChild(columnElement);

				tableElement.appendChild(rowElement);

				columnDetailsElement.appendChild(tableElement);// add element to
																// the
																// columndetails
																// element
				rowDetailsElement.appendChild(columnDetailsElement);// add
																	// columnelement
																	// to the
																	// rowDetailsElement
																	// element

			}

			templateElement.appendChild(rowDetailsElement);

			// String[] rowArray=(String[])rowMap.keySet().toArray();
			Iterator rowArrayiterator = rowMap.keySet().iterator();
			while (rowArrayiterator.hasNext()) {
				String rowParameterCode = ((String) rowArrayiterator.next())
						.split("#")[0];
				String rowLevelNo = "2";
				columnNo = 1;
				rowDetailsElement = newDocumentObjectHTML
						.createElement("rowDetails");
				rowDetailsElement.setAttribute("rowNo", "" + rowNos++);

				Node columnObj = getElementWithFollowingCode(rowParameterCode,
						rowLevelNo, newDocumentObjectHM);

				columnDetailsElement = newDocumentObjectHTML
						.createElement("columnDetails");// column Element
				columnDetailsElement.setAttribute("colNo", "" + columnNo++);
				columnDetailsElement.setAttribute("class", "tdfonthead");
				columnDetailsElement.setAttribute("width", "" + columnWidth
						+ "%");
				System.out.println("label Alignment for column elements"
						+ columnObj.getAttributes()
								.getNamedItem("labelAlignment").getNodeValue());
				columnDetailsElement.setAttribute("align", "right");
				tableElement = newDocumentObjectHTML.createElement("table");
				tableElement.setAttribute("width", "100%");
				tableElement.setAttribute("cellspacing", "0");
				tableElement.setAttribute("cellpadding", "0");
				tableElement.setAttribute("border", "0");
				rowElement = newDocumentObjectHTML.createElement("tr");
				columnElement = newDocumentObjectHTML.createElement("td");
				elementElement = newDocumentObjectHTML.createElement("element");// element
																				// Element
				elementElement.setAttribute("id", "");
				elementElement.setAttribute("name", "");
				elementElement.setAttribute("type", "");
				elementElement.setAttribute("idC", "label");
				elementElement.setAttribute(
						"value",
						""
								+ columnObj.getAttributes()
										.getNamedItem("parameterName")
										.getNodeValue());
				setBoldAndUnderlinePropertiesForParameterLabel(elementElement,
						columnObj);
				columnElement.appendChild(elementElement);
				rowElement.appendChild(columnElement);
				tableElement.appendChild(rowElement);
				columnDetailsElement.appendChild(tableElement);// add element to
																// the
																// columndetails
																// element
				rowDetailsElement.appendChild(columnDetailsElement);

				columnArrayiterator = columnMap.keySet().iterator();
				while (columnArrayiterator.hasNext()) {
					String parameterCode = rowParameterCode;
					String parentParameterCode = ((String) columnArrayiterator
							.next()).split("#")[0];
					String levelNo = "2";
					columnObj = getElementWithFollowingCode(
							parentParameterCode, parameterCode, levelNo,
							newDocumentObjectHM);
					columnDetailsElement = newDocumentObjectHTML
							.createElement("columnDetails");// column Element
					columnDetailsElement.setAttribute("colNo", "" + columnNo++);
					columnDetailsElement.setAttribute("class", "tdfont");
					columnDetailsElement.setAttribute("width", "" + columnWidth
							+ "%");
					columnDetailsElement.setAttribute("align", "center");

					tableElement = newDocumentObjectHTML.createElement("table");
					tableElement.setAttribute("width", "100%");
					tableElement.setAttribute("cellspacing", "0");
					tableElement.setAttribute("cellpadding", "0");
					tableElement.setAttribute("border", "0");

					rowElement = newDocumentObjectHTML.createElement("tr");
					columnElement = newDocumentObjectHTML.createElement("td");

					elementElement = newDocumentObjectHTML
							.createElement("element");// element Element
					elementElement.setAttribute("id", "");
					createElementInHTMLForm(elementElement, columnObj);
					columnElement.appendChild(elementElement);
					rowElement.appendChild(columnElement);
					tableElement.appendChild(rowElement);
					columnDetailsElement.appendChild(tableElement);// add
																	// element
																	// to the
																	// column
																	// details
																	// element
					rowDetailsElement.appendChild(columnDetailsElement);
				}
				templateElement.appendChild(rowDetailsElement);

			}
		}
	}
	
	
	
	public void loadtodbintestcase(Document actualdocumentObj,String testcode) throws XPathExpressionException {
		
		
		
		//Element newTestHTML = null;
		Node newTestHTML=null;
		DocumentBuilder documentBuilder=null;

		Node actualRootElement = null;
NodeList templatesList = actualdocumentObj
				.getElementsByTagName("templates");
				
				if (templatesList.getLength() != 0) {
				
				actualRootElement = templatesList.item(0);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			System.out.println("testtemplate[@labTestCode='"
					+ testcode + "']");
			XPathExpression expr = xpath
					.compile("testtemplate[@labTestCode='"
							+ testcode + "']");
			Object result = expr.evaluate(actualRootElement,
					XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			//NodeList nodes = (NodeList) result;

			for(int i=0;i<nodes.getLength();i++)
{
	System.out.println("total test contain "+nodes.getLength());
	
	String ss=(String)nodes.item(i).getNodeValue();
	
	System.out.println("total test contain "+ss);

	
	//LOGGER_INV.log(Level.INFO,nodes.getLength()+" existing template"+i);
	
	newTestHTML=nodes.item(i);
	
    String key = newTestHTML.getAttributes().getNamedItem("labTestCode").getNodeValue();
	
	System.out.println("test code:::::::::::::"+key);

	
	Transformer transformer;
	try {
		//transformer = transformerFactory.newTransformer(new StreamSource(//path+"StyleSheet.xsl"));
		//Note: get the XSL stylesheet from the database and convert it to a transformer object
		transformer= InvestigationTemplateDataHelper.getInstance().getTransformerObject(InvestigationConfig.XSL_VIEWTEMPLATE_STYLESHEET);
				
		//InvestigationStyleSheetConfigurationCacheManager.getInstance().getTransformerObj(InvestigationConfig.XSL_STYLESHEET);
		Document newdoc=documentBuilder.newDocument();
		newdoc.appendChild(newdoc.importNode(newTestHTML, true));
		//resultEntryVO.setTestDocument(newdoc);
		java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
	 	StreamResult streamResult=new StreamResult(baos) ;
	 	transformer.transform(new DOMSource(newdoc),streamResult);
	 	//resultEntryVO.setTemplateDocumentString(baos.toString());
	 	
	 	TransformerFactory tf = TransformerFactory.newInstance();
	 	Transformer transformer1 = tf.newTransformer();
	 	transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	 	StringWriter writer = new StringWriter();
	 	transformer1.transform(new DOMSource(newdoc), new StreamResult(writer));
	 	String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
	 	String output1="<?xml version='1.0' encoding='UTF-8'?><templates>"+output+"</templates>";
	 	//System.out.println("1",key,output);
	 	//loadDocumentStringToDatabase(key,output1);
	 	//InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/step.xml", newdoc);
		
	} catch (TransactionException e) {
			e.printStackTrace();
	} catch (TransformerException e) {
			e.printStackTrace();
	}
	
}

			
				
				}
				
		
	}
}