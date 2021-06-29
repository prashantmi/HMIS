package new_investigation.masters.controller.utl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.DynamicReportTemplateFB;
import new_investigation.transactions.controller.Helper.TemplateProcessingHLP;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.template.Entry;
import new_investigation.vo.template.PrintingTemplateTestParameterVO;
import new_investigation.vo.template.ResultEntryVOGroupByValidatedBy;
import new_investigation.vo.template.Test;
import new_investigation.vo.template.TestGroup;
import new_investigation.vo.template.TestGroupTreeListVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.ResultSet;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

public class DynamicReportTemplateUTIL extends ControllerUTIL {

	HttpSession httpSession = null;
	static String hospitalCode;
	Map<String, String> elementMap = new HashMap<String, String>();

	public void newTemplateCreation(HttpServletRequest request,
			HttpServletResponse response) {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::newTemplateCreation");
		Document document = InvestigationTemplateDataHelper.getInstance()
				.getNewDocument();
		request.getSession().removeAttribute("TESTGROUPTREELISTVO");
		request.getSession().removeAttribute("TESTTEMPLATEDOCUMENT");
		request.getSession().removeAttribute("TESTCODESONTEMPLATE");
		Element testtemplateElement = document.createElement("testtemplate");
		document.appendChild(testtemplateElement);
		testtemplateElement.setAttribute("labTestCode", "");
		request.getSession().setAttribute("TESTTEMPLATEDOCUMENT", document);
//		try {
//			writeResponse(response, "");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void createNewTable(HttpServletRequest request,
			HttpServletResponse response) {

		String templateType = request.getParameter("templateType");
		Document printingtemplateDoc = null;
		if (templateType == null) {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		if (printingtemplateDoc == null) {
			javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			try {
				printingtemplateDoc = dbf.newDocumentBuilder().newDocument();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			Element rootelement = printingtemplateDoc
					.createElement("testtemplate");
			rootelement.setAttribute("printingId", "");

			printingtemplateDoc.appendChild(rootelement);

		}
		System.out.println("rows=" + request.getParameter("rows") + " cols="
				+ request.getParameter("cols") + " tableid="
				+ request.getParameter("tableid"));
		Element tableElement = printingtemplateDoc.createElement("table");
		// printingtemplateDoc.getFirstChild().appendChild(tableElement);
		tableElement.setAttribute("type", request.getParameter("tableid"));
		tableElement.setAttribute("width", "100%");
		tableElement.setAttribute("maxColumns", request.getParameter("cols"));
		tableElement.setAttribute("cellspacing",
				request.getParameter("cellspacing"));
		tableElement.setAttribute("cellpadding",
				request.getParameter("cellpadding"));
		tableElement.setAttribute("border", request.getParameter("border"));
		tableElement.setAttribute("bordercolor",
				request.getParameter("borderColor"));
		tableElement.setAttribute("align", request.getParameter("tableAlign"));
		tableElement
				.setAttribute("height", request.getParameter("tableHeight"));
		tableElement.setAttribute("width", request.getParameter("tableWidth"));

		int rows = Integer.parseInt(request.getParameter("rows"));
		int cols = Integer.parseInt(request.getParameter("cols"));
		if (cols == 0) {
			cols = 1;
		}

		int columnwidth = 100 / cols;
		for (int rowNo = 0; rowNo < rows; rowNo++) {
			Element rowElement = printingtemplateDoc
					.createElement("rowDetails");
			tableElement.appendChild(rowElement);
			rowElement.setAttribute("rowNo", "" + (rowNo + 1));
			for (int colNo = 0; colNo < cols; colNo++) {
				Element colElement = printingtemplateDoc
						.createElement("columnDetails");
				rowElement.appendChild(colElement);
				colElement.setAttribute("colNo", "" + (colNo + 1));
				colElement.setAttribute("align", "left");
				colElement.setAttribute("width", columnwidth + "%");
				colElement.setAttribute("class", "tdfont");
				colElement.setAttribute("colspan", "1");

				Element innerTableElement = printingtemplateDoc
						.createElement("table");
				colElement.appendChild(innerTableElement);
				innerTableElement.setAttribute("border", "0");
				innerTableElement.setAttribute("cellpadding", "0");
				innerTableElement.setAttribute("cellspacing", "0");
				innerTableElement.setAttribute("width", "100%");

				Element innerTrElement = printingtemplateDoc
						.createElement("tr");
				innerTableElement.appendChild(innerTrElement);

				Element innerTdElement = printingtemplateDoc
						.createElement("td");
				innerTrElement.appendChild(innerTdElement);

				Element innerElement = printingtemplateDoc
						.createElement("element");
				innerTdElement.appendChild(innerElement);
				innerElement.setAttribute("id", "0");
				innerElement.setAttribute("idC", "");
				innerElement.setAttribute("name", "");
				innerElement.setAttribute("type", "");
				innerElement.setAttribute("value", "");

			}

		}

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		System.out.println("node 1: /testtemplate/table[@type='footer']");
		try {

			XPathExpression expr = xpath
					.compile("/testtemplate/table[@type='footer']");
			Object result = expr.evaluate(printingtemplateDoc,
					XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			if (nodes.getLength() == 0) {
				printingtemplateDoc.getFirstChild().appendChild(tableElement);
			} else {
				System.out.println("inside else block of create table method");
				printingtemplateDoc.getFirstChild().insertBefore(tableElement,
						printingtemplateDoc.getFirstChild().getLastChild());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);

				Source domSource = new DOMSource(printingtemplateDoc);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				String htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void getLaboratoryTestGroup(HttpServletRequest request,
			DynamicReportTemplateFB fb) {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::getLaboratoryTestGroup");
		UserVO userVO = ControllerUTIL.getUserVO(request);
		hospitalCode = userVO.getHospitalCode();

		// Connection connection = super.getConnection();
		// String queryKey = "NEWTEMPLATECREATORFORTESTGROUP.COMBO.POPULATION";
		// List<String> condition = new ArrayList<String>();
		// condition.add(userVO.getHospitalCode());
		// condition.add(userVO.getHospitalCode());

		List labList1 = null;
		List labList2 = null;
		String x = request.getParameter("radioValue");
		System.out.println(x);
		request.getSession().removeAttribute(
				InvestigationConfig.sessionOptionTestList);
		request.getSession().removeAttribute(
						InvestigationConfig.sessionOptionGroupList);
		//if(request.getParameter("radioValue") != null && request.getParameter("radioValue").equals("2") == true)
		//{
			labList2 = InvestigationTemplateDataHelper.getInstance().getGroupTemplateListCombo();
		//}
	//	else {
		labList1 = InvestigationTemplateDataHelper.getInstance()
				.getTemplateListCombo();
	//	}
		// HelperClass.getCombo(queryKey,condition,connection);
		request.getSession().setAttribute(
				InvestigationConfig.sessionOptionTestList, labList1);
		request.getSession().setAttribute(
				InvestigationConfig.sessionOptionGroupList, labList2);

	}

	public void initializeTemplateProcessForVO(HttpServletRequest request,
			HttpServletResponse response) {

		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::initializeTemplateProcessForVO");
		ResultEntryVOGroupByValidatedBy templateQueryParameterVO = new ResultEntryVOGroupByValidatedBy();
		Class classobj = templateQueryParameterVO.getClass();

		String optionText = "";
		for (Field field : classobj.getDeclaredFields()) {
			optionText += "<option value=\"" + field.getName() + "\">"
					+ field.getName() + "</option>";

		}

		System.out.println("optionText  =" + optionText);
		request.getSession().setAttribute("parameterOptionText", optionText);
	}

	public void existingTemplateList(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::existingTemplateList");
		UserVO userVO = ControllerUTIL.getUserVO(request);
		hospitalCode = userVO.getHospitalCode();

		List<Entry> tpList = new ArrayList<Entry>();

		tpList = InvestigationTemplateDataHelper.getInstance()
				.getTemplateList();
		request.getSession().setAttribute("EXSISTTEMPLATELIST", tpList);
	}

	public void generateLabtestTree(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::generateLabtestTree");

		TestGroupTreeListVO testGroupTreeListVO = (TestGroupTreeListVO) request
				.getSession().getAttribute("TESTGROUPTREELISTVO");
		String strTestTree = "";
		String testCodeComplete = request.getParameter("testCode");
		String testGroupName = request.getParameter("testGroupName");
		String isGroup = request.getParameter("testOrGroupRadio");
		String str = null;
		String testCode = null;
		String hospitalCode = null;
		
		try {
			if (testGroupTreeListVO == null)
				testGroupTreeListVO = new TestGroupTreeListVO();

			if(testGroupTreeListVO.getTestModifyList() != null)
			{
				testGroupTreeListVO.getTestModifyList().clear();
			}
			boolean checkTestInTestList = false;

			String groupCode = null;
			List<Test> testList = new ArrayList<Test>();
			if(isGroup != null && isGroup.equals("2") == true )
			{
				groupCode =  testCodeComplete.split("-")[0];
				hospitalCode = testCodeComplete.split("-")[1];
				testList = InvestigationTemplateDataHelper.getInstance().getTestsInGroup(groupCode, hospitalCode);
			}
			else
			{
				testCode = testCodeComplete;
			}
			
			
//			if (testGroupTreeListVO.getTestList() != null
//					&& checkTestInTestList == false)
//				checkTestInTestList = checkTestInTestList(
//						testGroupTreeListVO.getTestList(), testCode);
//
//			if (testGroupTreeListVO.getTestGroupList() != null
//					&& checkTestInTestList == false)
//				checkTestInTestList = checkTestInGroupTestList(
//						testGroupTreeListVO.getTestGroupList(), testCode);
//
//			
//			  if(testGroupTreeListVO.getTestModifyList()!=null &&
//			  checkTestInTestList==false)
//			 checkTestInTestList=checkTestInTestModifyList
//			  (testGroupTreeListVO.
//			  getTestModifyList(),testCode);
//			  
//			  if(testGroupTreeListVO.getTestGroupModifyList()!=null &&
//			  checkTestInTestList==false)
//			  checkTestInTestList=checkTestInModifyGroupList
//			  (testGroupTreeListVO
//			  .getTestGroupModifyList(),testCode);
//			 

			if (checkTestInTestList == false) {
				if (testGroupTreeListVO.getTestModifyList() == null)
					testGroupTreeListVO
							.setTestModifyList(new ArrayList<Test>());
				if(isGroup != null && isGroup.equals("2") == true)
				{
					for (int i = 0; i < testList.size(); i++)
					{
						Test testObj = new Test();
						testObj.setTestCode(testList.get(i).getTestCode());
						testObj.setTestName(testList.get(i).getTestName());
						testGroupTreeListVO.getTestModifyList().add(testObj);
					}
				}
				else {
				Test testObj = new Test();
				testObj.setTestCode(testCode);
				testObj.setTestName(testGroupName);
				testGroupTreeListVO.getTestModifyList().add(testObj);
				}
			//	testGroupTreeListVO.getTestList().add(testObj);
			}
		
			
			request.getSession().setAttribute("TESTGROUPTREELISTVO",
					testGroupTreeListVO);
			//str = initModificationProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		strTestTree = writingResponse(request, response);
		
		try {
			writeResponse(response, strTestTree);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	
	
	public void populateDataIntoTableForElement(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::populateDataIntoTableForElement");

		// add the test codes that have been added in the table on the template
		// to a session variable and access it for saving
		// them in test parameter master
		InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
				.getInstance();
		String labTestCode = request.getParameter("labTestCode");

		HashSet<String> testCodeList = (HashSet<String>) request.getSession()
				.getAttribute("TESTCODESONTEMPLATE");

		if (testCodeList == null)
			testCodeList = new HashSet<String>();

		testCodeList.add(labTestCode);
		request.getSession().setAttribute("TESTCODESONTEMPLATE", testCodeList);

		String parameterCode = request.getParameter("parameterCode");
		String parentParameterCode = request
				.getParameter("parentParameterCode");
		String elementType = request.getParameter("elementType");

		String elementTablePosition = request
				.getParameter("elementTablePosition");
		String elementcolNo = request.getParameter("elementColNo");
		String elementrowNo = request.getParameter("elementRowNo");

		String labelTablePosition = request.getParameter("labelTablePosition");
		String labelColNo = request.getParameter("labelColNo");
		String labelRowNo = request.getParameter("labelRowNo");

		String labelInclusion = request.getParameter("labelInclusion");
		String elementInclusion = request.getParameter("elementInclusion");

		Transformer transformer = null;

		Document actualParameterDetailDocument = (Document) request
				.getSession().getAttribute("actualParameterDetailDocument");
		String templateType = request.getParameter("templateType");
		Document printingtemplateDoc = null;
		if (templateType == null) {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		String xpathStr = "/actualParameterDetail/testTemplate[@id='"
				+ labTestCode.substring(0, 5) + "']/";

		if (elementType.equals("RT"))
			xpathStr += "resultEntryFormParamterElement";
		else if (elementType.equals("SF"))
			xpathStr += "sampleFormParamterElement";
		else if (elementType.equals("RF"))
			xpathStr += "requisitionFormParamterElement";
		else if (elementType.equals("RG"))
			xpathStr += "resultEntryGroupFormParamterElement";
		else {

		}

		xpathStr += "/element[@parameterCode='" + parameterCode
				+ "'][@parentParameterCode='" + parentParameterCode + "']";
		Node columnDetailNode = null;

		try {
			// manager.getNewTransformer().transform(new
			// DOMSource(actualParameterDetailDocument), new
			// StreamResult("d:/Step1.xml"));

			/* finding the element */
			// LOGGER_INV.log(Level.INFO,xpathStr);
			XPathExpression expr = xpath.compile(xpathStr);
			Object result = expr.evaluate(actualParameterDetailDocument,
					XPathConstants.NODESET);
			Node elementNode = null;
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				elementNode = nodes.item(i);
			}

			/* Adding Label to specified position */

			if (labelInclusion.equals("0")) {
				Element newlabelElement = printingtemplateDoc
						.createElement("element");
				newlabelElement.setAttribute("idC", "label");
				newlabelElement.setAttribute("name", "");
				newlabelElement.setAttribute("value", elementNode
						.getAttributes().getNamedItem("parameterName")
						.getNodeValue());
				newlabelElement.setAttribute("type", "");
				newlabelElement.setAttribute("align", "left");
				TemplateProcessingHLP
						.setBoldAndUnderlinePropertiesForParameterLabel(
								newlabelElement, elementNode);

				// LOGGER_INV.log(Level.INFO,printingtemplateDoc+" 1 xquery : /testtemplate/table[@type='"+labelTablePosition+"']/rowDetails[@rowNo='"+labelRowNo+"']/columnDetails[@colNo='"+labelColNo+"']/table/tr/td");
				expr = xpath.compile("/testtemplate/table[@type='"
						+ labelTablePosition + "']/rowDetails[@rowNo='"
						+ labelRowNo + "']/columnDetails[@colNo='" + labelColNo
						+ "']/table/tr/td");
				result = expr.evaluate(printingtemplateDoc,
						XPathConstants.NODESET);
				nodes = (NodeList) result;
				// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
				for (int i = 0; i < nodes.getLength(); i++) {
					columnDetailNode = nodes.item(i);
					NodeList columnDetailChildNodes = columnDetailNode
							.getChildNodes();
					for (int j = 0; j < columnDetailChildNodes.getLength(); j++) {
						Node element = columnDetailChildNodes.item(j);
						if (element.getNodeName().equals("element")) {
							if (element.getAttributes().getNamedItem("idC")
									.getNodeValue() == null
									|| element.getAttributes()
											.getNamedItem("idC").getNodeValue()
											.equals("")) {
								columnDetailNode.removeChild(element);
								j--;
							}
						}
					}

					columnDetailNode.appendChild(newlabelElement);
				}

			}

			/* Adding element to specified position */
			if (elementInclusion.equals("0")) {
				Element newElement = printingtemplateDoc
						.createElement("element");
				createElementInHTMLForm(newElement, elementNode, 1);
				// LOGGER_INV.log(Level.INFO,printingtemplateDoc+" 2 xquery : /testtemplate/table[@type='"+elementTablePosition+"']/rowDetails[@rowNo='"+elementrowNo+"']/columnDetails[@colNo='"+elementcolNo+"']/table/tr/td/");
				expr = xpath.compile("/testtemplate/table[@type='"
						+ elementTablePosition + "']/rowDetails[@rowNo='"
						+ elementrowNo + "']/columnDetails[@colNo='"
						+ elementcolNo + "']/table/tr/td");
				result = expr.evaluate(printingtemplateDoc,
						XPathConstants.NODESET);
				nodes = (NodeList) result;
				// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
				for (int i = 0; i < nodes.getLength(); i++) {
					columnDetailNode = nodes.item(i);
					NodeList columnDetailChildNodes = columnDetailNode
							.getChildNodes();
					for (int j = 0; j < columnDetailChildNodes.getLength(); j++) {
						Node element = columnDetailChildNodes.item(j);
						if (element.getNodeName().equals("element")) {
							if (element.getAttributes().getNamedItem("idC")
									.getNodeValue() == null
									|| element.getAttributes()
											.getNamedItem("idC").getNodeValue()
											.equals("")) {
								columnDetailNode.removeChild(element);
								j--;
							}
						}
					}

					columnDetailNode.appendChild(newElement);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
				Source domSource = new DOMSource(printingtemplateDoc);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				
				//System.out.println("xmlll"+baos.toString()+streamResult.toString());
				transformer.transform(domSource, streamResult);
				String htmlString = baos.toString();
				//System.out.println("htmlString"+htmlString);
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.getSession().setAttribute("TESTTEMPLATEDOCUMENT",
				printingtemplateDoc);
	}

	private void createElementInHTMLForm(Element elementElement,
			Node columnObj, int creationMode) {

		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::createElementInHTMLForm");
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
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
			elementElement.setAttribute("eventName", columnObj.getAttributes()
					.getNamedItem("eventName").getNodeValue());
			elementElement.setAttribute("eventValidationString", columnObj
					.getAttributes().getNamedItem("eventValidationString")
					.getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			elementElement.setAttribute("editor", elementProperties[0]);
			elementElement.setAttribute("rows", elementProperties[1]);
			elementElement.setAttribute("cols", elementProperties[2]);
			elementElement.setAttribute("defaultValue", columnObj
					.getAttributes().getNamedItem("defaultValue")
					.getNodeValue());
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());

		} else if (columnObj.getAttributes().getNamedItem("objectId")
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
			elementElement.setAttribute("idC", "input");
			elementElement.setAttribute("type", "text");
			elementElement.setAttribute("value", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
			elementElement.setAttribute("changeTableId","");
			
			if(elementProperties.length>4)
			elementElement.setAttribute("isDate",elementProperties[4]);
		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("label")) {
			elementElement.setAttribute("idC", "label");
			elementElement.setAttribute("name", "");
			elementElement.setAttribute("value", columnObj.getAttributes()
					.getNamedItem("parameterName").getNodeValue());
			elementElement.setAttribute("type", "");
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("labelAlignment").getNodeValue());
			// LOGGER_INV.log(Level.INFO,"LAbel Align"+columnObj.getAttributes().getNamedItem("labelAlignment").getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			elementElement.setAttribute("bold", elementProperties[0]);
			elementElement.setAttribute("underline", elementProperties[1]);
			elementElement
					.setAttribute("isPrintable", columnObj.getAttributes()
							.getNamedItem("isPrintable").getNodeValue());
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("select")) {
			// LOGGER_INV.log(Level.INFO,columnObj.getAttributes().getNamedItem("functionName").getNodeValue());
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
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
			Element optionsElement = elementElement.getOwnerDocument()
					.createElement("options");
			// Commented by Siddharth, 18/09/2015 modify the query later
			optionsElement = TemplateProcessingHLP.getAllOptionsOfElement(
					columnObj.getAttributes().getNamedItem("parameterCode")
							.getNodeValue(), optionsElement);
			elementElement.appendChild(optionsElement);
			if (columnObj.getAttributes().getNamedItem("functionName")
					.getNodeValue().equals("-1")) {

			} else {
				elementElement.setAttribute("functionName", columnObj
						.getAttributes().getNamedItem("functionName")
						.getNodeValue());
			}

			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));

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
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
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
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("button")) {
			// LOGGER_INV.log(Level.INFO,"<----------------------button-------------------------------->");
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
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		} else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("imagesection")) {
			// LOGGER_INV.log(Level.INFO,"<----------------------imagesection-------------------------------->");
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
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
			// Element
			// queryElement=elementElement.getOwnerDocument().createElement("query");
			// queryElement=getQueryValueElement(columnObj,queryElement);
			// elementElement.appendChild(queryElement);

		}
		
		else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("CheckBox")) {
			// LOGGER_INV.log(Level.INFO,"<----------------------button-------------------------------->");
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "checkbox");
			elementElement.setAttribute("value", columnObj.getAttributes()
					.getNamedItem("buttonName").getNodeValue());
			elementElement.setAttribute("callUrl", columnObj.getAttributes()
					.getNamedItem("callUrl").getNodeValue());
			elementElement.setAttribute("align", columnObj.getAttributes()
					.getNamedItem("elementAlignment").getNodeValue());
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
		}
		
		else if (columnObj.getAttributes().getNamedItem("objectId")
				.getNodeValue().equalsIgnoreCase("SnomedCT")) {
			// LOGGER_INV.log(Level.INFO,"<----------------------button-------------------------------->");
			elementElement.setAttribute(
					"name",
					"template#"
							+ columnObj.getAttributes()
									.getNamedItem("parentParameterCode")
									.getNodeValue()
							+ columnObj.getAttributes()
									.getNamedItem("parameterCode")
									.getNodeValue());
			elementElement.setAttribute("idC", "snomedct");
			elementElement.setAttribute("value", "");
			
			// LOGGER_INV.log(Level.INFO,"element Align"+columnObj.getAttributes().getNamedItem("elementAlignment").getNodeValue());
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
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
			String[] elementProperties = columnObj.getAttributes()
					.getNamedItem("property").getNodeValue().split("#");
			
			elementElement
			.setAttribute("changeTableId","");
			if(elementProperties.length>4)
			elementElement
			.setAttribute("isDate",elementProperties[4]);
			
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			
		}
		
		/*else if (columnObj.getAttributes().getNamedItem("objectId")
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
			//elementElement.setAttribute("type", "text");
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
			
			elementElement.setAttribute("typeOfElement", columnObj
					.getAttributes().getNamedItem("typeOfElement")
					.getNodeValue());
			elementElement
					.setAttribute("labCode", columnObj.getAttributes()
							.getNamedItem("labTestCode").getNodeValue()
							.substring(0, 5));
			elementElement.setAttribute("originalname", columnObj
					.getAttributes().getNamedItem("originalname")
					.getNodeValue());
		}*/
			else {

		}

	}

	public String writingResponse(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writingResponse");
		TestGroupTreeListVO testGroupTreeListVO = (TestGroupTreeListVO) request
				.getSession().getAttribute("TESTGROUPTREELISTVO");
		String strTestTree = "";
		if (testGroupTreeListVO != null) {
			if (testGroupTreeListVO.getTestList() != null
					&& testGroupTreeListVO.getTestList().size() > 0) {
				for (Test test : testGroupTreeListVO.getTestList()) {
					strTestTree += "<li id='submenu1'><a href='#' onclick=\"getParameterDetails('"
							+ test.getTestCode()
							+ "')\">"
							+ test.getTestName()
							+ "</a></li>";
				}
			}
			if (testGroupTreeListVO.getTestGroupList() != null
					&& testGroupTreeListVO.getTestGroupList().size() > 0) {
				for (TestGroup testGroup : testGroupTreeListVO
						.getTestGroupList()) {
					strTestTree += "<li id='submenu2' ><a href='#' onclick=\"getParameterDetails('"
							+ testGroup.getGroupCode()
							+ "')\">"
							+ testGroup.getGroupName() + "</a></li>";
					strTestTree += "<ul id='tree_menu'>";
					if (testGroup.getTest() != null
							&& testGroup.getTest().size() > 0) {
						for (Test test : testGroup.getTest()) {
							strTestTree += "<li id='submenu1' ><a href='#' onclick=\"getParameterDetails('"
									+ test.getTestCode()
									+ "')\">"
									+ test.getTestName() + "</a></li>";
						}
					}
					strTestTree += "</ul>";
				}
			}

			if (testGroupTreeListVO.getTestModifyList() != null
					&& testGroupTreeListVO.getTestModifyList().size() > 0) {
				for (Test test : testGroupTreeListVO.getTestModifyList()) {
					String symbol = "S";
					if(test.isGroup() == true)
						symbol ="G";
					strTestTree += "<li id='submenu1' ><a href='#' onclick=\"getParameterDetails('"
							+ test.getTestCode()
							 + symbol + "')\">"
							+ test.getTestName() + "</a></li>";
				}
			}
			if (testGroupTreeListVO.getTestGroupModifyList() != null
					&& testGroupTreeListVO.getTestGroupModifyList().size() > 0) {
				for (TestGroup testGroup : testGroupTreeListVO
						.getTestGroupModifyList()) {
					strTestTree += "<li id='submenu1'><a href='#' onclick=\"getParameterDetails('"
							+ testGroup.getGroupCode()
							+ "G')\">"
							+ testGroup.getGroupName() + "</a></li>";
					strTestTree += "<ul id='tree_menu'>";
					if (testGroup.getTest() != null
							&& testGroup.getTest().size() > 0) {
						for (Test test : testGroup.getTest()) {
							strTestTree += "<li id='submenu2' ><a href='#' onclick=\"getParameterDetails('"
									+ test.getTestCode()
									+ "S')\">"
									+ test.getTestName() + "</a></li>";
						}
					}
					strTestTree += "</ul>";
				}
			}

		}
		return strTestTree;
		/*
		 * try { response.getWriter().write(strTestTree); } catch (IOException
		 * e) { e.printStackTrace(); }
		 */
	}

	public boolean checkTestInTestList(List<Test> testLst, String TestCode) {
		boolean flag = false;
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::checkTestInTestList");
		if (testLst != null && testLst.size() > 0) {

			for (int i = 0; i < testLst.size(); i++) {
				// LOGGER_INV.log(Level.INFO,"testLst.get(i).getTestCode() "+testLst.get(i).getTestCode()+"  TestCode "+TestCode.substring(0,9));
				String t = testLst.get(i).getTestCode();
				if (testLst.get(i).getTestCode().equals(TestCode) == true) {
					return true;
				}
			}
		}
		return flag;

	}

	private boolean checkTestInGroupTestList(List<TestGroup> grouplst,
			String selectValue) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::checkTestInGroupTestList");
		boolean flag = false;

		if (grouplst != null && grouplst.size() > 0) {
			for (TestGroup group : grouplst) {
				if (group.getTest() != null && group.getTest().size() > 0) {
					for (int i = 0; i < group.getTest().size(); i++) {
						if (group.getTest().get(i).getTestCode()
								.equals(selectValue) == true) {
							return true;
						}
					}
				}
			}
		}
		return flag;
	}

	
	private boolean checkTestInTestModifyList(List<Test> modifyTest,
			String selectValue) {
		
		boolean flag=false;				
		
		if(modifyTest!=null  && modifyTest.size()>0)
		{
		
			for(int i=0;i<modifyTest.size();i++)
			{
				
				if(modifyTest.get(i).getTestCode().equals(selectValue)==true)
				{	
						return true;			
				}
			}
			
		}
		return flag;
	}
	
	private boolean checkTestInModifyGroupList(List<TestGroup> modifyGroup,
			String selectValue) {
		
		boolean flag=false;
		
		if(modifyGroup!=null  && modifyGroup.size()>0){
		for(TestGroup group:modifyGroup)
		{
			for(int i=0;i<group.getTest().size();i++)
			{
				if(group.getTest().get(i).getTestCode().equals(selectValue)==true)
				{	
						return true;			
				}
			}	
			
		}
		}
		
		return flag;
	}
	
	
	public void getSelectedLabTestParameterDtl(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::getSelectedLabTestParameterDtl");
		XPathFactory factory = XPathFactory.newInstance();
		String hospitalCode = (String) request.getSession().getAttribute(
				"HOSPITAL_CODE");
		String paraType=request.getParameter("paraType");
		
		Document actualParameterDetailDocument = (Document) request
				.getSession().getAttribute("actualParameterDetailDocument");
		String laboratoryCode = "";
		String testOrGroupCodeOri = request.getParameter("labTestCode");
		String testOrGroupCode = testOrGroupCodeOri.substring(0,
				testOrGroupCodeOri.length() - 1);
		char type = 'S';
		if (actualParameterDetailDocument == null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
				actualParameterDetailDocument = db.newDocument();
				Element root = actualParameterDetailDocument
						.createElement("actualParameterDetail");
				actualParameterDetailDocument.appendChild(root);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

		}

		// String
		// xpathToLabTestOrGroupCode="/actualParameterDetail/testTemplate[@id="+laboratoryCode+testOrGroupCode+"][@type='"+type+"']";
		String xpathToLabTestOrGroupCode = "/actualParameterDetail/testTemplate[@id="
				+ testOrGroupCode + "]";
		XPath xpath = factory.newXPath();
		Node actionNode = null;
		try {
			String strCreateRequisitionFormDiv = "";
			XPathExpression expr = xpath.compile(xpathToLabTestOrGroupCode);
			Object result = expr.evaluate(actualParameterDetailDocument,
					XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			for (int i = 0; i < nodes.getLength(); i++) {
			//	actionNode = nodes.item(i);

			}

			if (actionNode == null) {
				Element temp_actionNode = actualParameterDetailDocument
						.createElement("testTemplate");
				temp_actionNode.setAttribute("id", laboratoryCode
						+ testOrGroupCode);
				temp_actionNode.setAttribute("type", "" + type);
				
				//to specify if its test para or dept test para
				temp_actionNode.setAttribute("paraType", "" + paraType);
				
				actualParameterDetailDocument.getChildNodes().item(0)
						.appendChild(temp_actionNode);
				actionNode = (Node) temp_actionNode;

			}

			if (type == 'G') {
				// populateGroupResultEntryForm((Element)actionNode,hospitalCode);
				// strCreateRequisitionFormDiv=createFormDiv(actionNode,"resultEntryGroupFormParamterElement","RG");
			} else {
				// populateRequistionForm((Element)actionNode,hospitalCode);
				// populateSampleForm((Element)actionNode,hospitalCode);
				populateResultEntryForm((Element) actionNode, hospitalCode);

				strCreateRequisitionFormDiv = createFormDiv(actionNode,
						"resultEntryFormParamterElement", "RT");
				strCreateRequisitionFormDiv += "#$#"
						+ createFormDiv(actionNode,
								"requisitionFormParamterElement", "RF");
				strCreateRequisitionFormDiv += "#$#"
						+ createFormDiv(actionNode,
								"sampleFormParamterElement", "SF");

			}

			request.getSession().setAttribute("actualParameterDetailDocument",
					actualParameterDetailDocument);

			writeResponse(response, strCreateRequisitionFormDiv);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void populateResultEntryForm(Element actionElement,
			String hospitalCode) {
		// LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupUTIL::populateResultEntryForm");
		String testCode = actionElement.getAttributes().getNamedItem("id")
				.getNodeValue();
		String paraType=actionElement.getAttributes().getNamedItem("paraType")
				.getNodeValue();

		Document ownerDocument = actionElement.getOwnerDocument();
		Element resultEntryFormParamterElement = ownerDocument
				.createElement("resultEntryFormParamterElement");
		actionElement.appendChild(resultEntryFormParamterElement);

		if(paraType.equals("0"))
		resultEntryFormParamterElement = InvestigationTemplateDataHelper
				.getInstance().getResultEntryFormParameters(ownerDocument,
						resultEntryFormParamterElement, testCode);
		else if(paraType.equals("1"))
		resultEntryFormParamterElement = InvestigationTemplateDataHelper
				.getInstance().getDepartmentResultEntryFormParameters(ownerDocument,
						resultEntryFormParamterElement, testCode);
		else if(paraType.equals("2"))
			resultEntryFormParamterElement = InvestigationTemplateDataHelper
					.getInstance().getRequisitionFormParameters(ownerDocument,
							resultEntryFormParamterElement, testCode);
			
		else
			;//resource form if required
	
	}

	private String createFormDiv(Node actionNode, String formType,
			String typeOfElement) {
		// formType 1 for RequisitionForm; 2 for SampleForm; 3 for
		// ResultEntryForm
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::createFormDiv");
		NodeList nodeList = actionNode.getChildNodes();
		NodeList elementNodeList = null;
		String formDiv = "";
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName().equals(formType) == true) {
				elementNodeList = ((Element) node)
						.getElementsByTagName("element");
				break;
			}

		}
		if (elementNodeList != null) {
			formDiv += "<ul>";
			for (int j = 0; j < elementNodeList.getLength(); j++) {
				formDiv += "<li id='parameterId'><a onClick=\"onSelectionParameter('"
						+ elementNodeList.item(j).getAttributes()
								.getNamedItem("labTestCode").getNodeValue()
						+ "','"
						+ elementNodeList.item(j).getAttributes()
								.getNamedItem("parentParameterCode")
								.getNodeValue()
						+ "','"
						+ elementNodeList.item(j).getAttributes()
								.getNamedItem("parameterCode").getNodeValue()
						+ "','"
						+ typeOfElement
						+ "')\" href='#'> "
						+ elementNodeList.item(j).getAttributes()
								.getNamedItem("parameterName").getNodeValue()
						+ "</a></li>";
			}
			formDiv += "</ul>";
		}

		return formDiv;
	}

	public void acceptChangeForDocument(HttpServletRequest request,
			HttpServletResponse response) {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::acceptChangeForDocument");
		UserVO userVO = ControllerUTIL.getUserVO(request);
		hospitalCode = userVO.getHospitalCode();

		List<String> testList = (ArrayList<String>) request.getSession()
				.getAttribute("TESTVALIDATIONLIST");
		List<String> testGroupList = (ArrayList<String>) request.getSession()
				.getAttribute("LABTESTGROUPVALIDATIONLIST");
		List<Element> testParameterCombineList = (ArrayList<Element>) request
				.getSession().getAttribute("TESTPRESENTINDOCUMENT");
		List<Element> testParameterList = new ArrayList<Element>();
		List<Element> testGroupParameterList = new ArrayList<Element>();
		for (Element element : testParameterCombineList) {

			if (element.getAttributes().getNamedItem("isTestGroup") != null
					&& element.getAttributes().getNamedItem("isTestGroup")
							.getNodeValue().equals("YES")) {
				testGroupParameterList.add(element);
			} else {
				testParameterList.add(element);
			}
		}
		// //LOGGER_INV.log(Level.INFO,"testGroupParameterList size is===="+testGroupParameterList.size());
		// //LOGGER_INV.log(Level.INFO,"testParameterList size is===="+testParameterList
		// .size());
		List<PrintingTemplateTestParameterVO> testParameterVOList = (List<PrintingTemplateTestParameterVO>) populateVOFromElement(testParameterList);
		List<PrintingTemplateTestParameterVO> testGroupParameterVOList = (List<PrintingTemplateTestParameterVO>) populateVOFromElement(testGroupParameterList);
		Connection connection = null;
		// CallableStatement cstmt = null;
		try {

			// connection =
			// DriverManager.getConnection(Conn.getDatabaseConnection(),InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
			// cstmt =
			// connection.prepareCall("{call inv_printing_template.savePrintingTemplateData(?,?,?,?,?,?,?,?,?)}");
			// ArrayDescriptor testCollectionAD =
			// ArrayDescriptor.createDescriptor("PRINTINGTEMPLATEVOCOLL",
			// connection);
			// StructDescriptor testCollectionSD =
			// StructDescriptor.createDescriptor("PRINTINGTEMPLATEVO",
			// (Connection) cstmt.getConnection());
			Object[] testListAttributes = new Object[testList.size()];
			int testListIndex = 0;
			for (String testCode : testList) {
				Object[] slideRequestVoAttributes = new Object[25];

				slideRequestVoAttributes[24] = testCode;
				System.out.println("testCode in bo method===" + testCode);

				// testListAttributes[testListIndex++] = new
				// oracle.sql.STRUCT(testCollectionSD, connection,
				// slideRequestVoAttributes);

			}
			// ArrayDescriptor testGroupCollectionAD =
			// ArrayDescriptor.createDescriptor("PRINTINGTEMPLATEVOCOLL",
			// connection);
			// StructDescriptor testGroupSD =
			// StructDescriptor.createDescriptor("PRINTINGTEMPLATEVO",
			// (Connection) cstmt.getConnection());
			Object[] testGroupListAttributes = null;
			if (testGroupList != null) {
				testGroupListAttributes = new Object[testGroupList.size()];
				int testGroupListIndex = 0;
				for (String testGroupCode : testGroupList) {
					Object[] slideRequestVoAttributes = new Object[25];

					slideRequestVoAttributes[23] = "YES";
					slideRequestVoAttributes[24] = testGroupCode;

					// testGroupListAttributes[testGroupListIndex++] = new
					// oracle.sql.STRUCT( testGroupSD, connection,
					// slideRequestVoAttributes);

				}
			}
			// ArrayDescriptor testParameterCollectionAD =
			// ArrayDescriptor.createDescriptor("PRINTINGTEMPLATEVOCOLL",
			// connection);
			// StructDescriptor testParameterSD =
			// StructDescriptor.createDescriptor("PRINTINGTEMPLATEVO",
			// (Connection) cstmt.getConnection());
			Object[] testParameterListAttributes = new Object[testParameterVOList
					.size()];
			int testParameterListIndex = 0;
			for (PrintingTemplateTestParameterVO testParameterVOPT : testParameterVOList) {
				Object[] slideRequestVoAttributes = new Object[25];
				slideRequestVoAttributes[0] = testParameterVOPT.getLevelNo();
				slideRequestVoAttributes[1] = testParameterVOPT
						.getParameterCode();
				slideRequestVoAttributes[2] = testParameterVOPT
						.getParameterName();
				slideRequestVoAttributes[3] = testParameterVOPT
						.getParentParameterCode();
				slideRequestVoAttributes[4] = testParameterVOPT
						.getCriteriaCode();
				slideRequestVoAttributes[5] = testParameterVOPT.getValueType();
				slideRequestVoAttributes[6] = testParameterVOPT.getObjectId();
				slideRequestVoAttributes[7] = testParameterVOPT
						.getRemoveStatus();
				slideRequestVoAttributes[8] = testParameterVOPT
						.getPreviousLabTestCode();// function name takes
													// previous labtestcode
													// value
				slideRequestVoAttributes[9] = testParameterVOPT.getEventName();
				slideRequestVoAttributes[10] = testParameterVOPT
						.getEventValidationString();
				slideRequestVoAttributes[11] = testParameterVOPT.getProperty();
				slideRequestVoAttributes[12] = testParameterVOPT
						.getIsparameternamedisplayed();
				slideRequestVoAttributes[13] = testParameterVOPT
						.getLabelelementdisplaytype();
				slideRequestVoAttributes[14] = testParameterVOPT
						.getLabelAlignment();
				slideRequestVoAttributes[15] = testParameterVOPT
						.getElementAlignment();
				slideRequestVoAttributes[16] = testParameterVOPT
						.getDefaultValue();
				slideRequestVoAttributes[17] = testParameterVOPT
						.getIsPrintable();
				slideRequestVoAttributes[18] = testParameterVOPT.getQueryCode();
				slideRequestVoAttributes[19] = testParameterVOPT
						.getButtonName();
				slideRequestVoAttributes[20] = testParameterVOPT.getCallUrl();
				slideRequestVoAttributes[21] = testParameterVOPT
						.getParentparametername();
				slideRequestVoAttributes[22] = testParameterVOPT
						.getTestGroupName();
				slideRequestVoAttributes[23] = testParameterVOPT
						.getIsTestGroup();
				slideRequestVoAttributes[24] = testParameterVOPT
						.getLabTestCode();

				// testParameterListAttributes[testParameterListIndex++] = new
				// oracle.sql.STRUCT(testParameterSD, connection,
				// slideRequestVoAttributes);

			}
			// ArrayDescriptor testGroupParameterCollectionAD =
			// ArrayDescriptor.createDescriptor("PRINTINGTEMPLATEVOCOLL",
			// connection);
			// StructDescriptor testGroupParameterSD =
			// StructDescriptor.createDescriptor("PRINTINGTEMPLATEVO",
			// (Connection) cstmt.getConnection());
			Object[] testGroupParameterListAttributes = null;
			if (testGroupParameterVOList != null) {
				testGroupParameterListAttributes = new Object[testGroupParameterVOList
						.size()];
				int testGroupParameterIndex = 0;
				for (PrintingTemplateTestParameterVO testParameterVOPT : testGroupParameterVOList) {
					Object[] slideRequestVoAttributes = new Object[25];
					slideRequestVoAttributes[0] = testParameterVOPT
							.getLevelNo();
					slideRequestVoAttributes[1] = testParameterVOPT
							.getParameterCode();
					slideRequestVoAttributes[2] = testParameterVOPT
							.getParameterName();
					slideRequestVoAttributes[3] = testParameterVOPT
							.getParentParameterCode();
					slideRequestVoAttributes[4] = testParameterVOPT
							.getCriteriaCode();
					slideRequestVoAttributes[5] = testParameterVOPT
							.getValueType();
					slideRequestVoAttributes[6] = testParameterVOPT
							.getObjectId();
					slideRequestVoAttributes[7] = testParameterVOPT
							.getRemoveStatus();
					slideRequestVoAttributes[8] = testParameterVOPT
							.getPreviousLabTestCode();// function name takes
														// previous labtestcode
														// value
					slideRequestVoAttributes[9] = testParameterVOPT
							.getEventName();
					slideRequestVoAttributes[10] = testParameterVOPT
							.getEventValidationString();
					slideRequestVoAttributes[11] = testParameterVOPT
							.getProperty();
					slideRequestVoAttributes[12] = testParameterVOPT
							.getIsparameternamedisplayed();
					slideRequestVoAttributes[13] = testParameterVOPT
							.getLabelelementdisplaytype();
					slideRequestVoAttributes[14] = testParameterVOPT
							.getLabelAlignment();
					slideRequestVoAttributes[15] = testParameterVOPT
							.getElementAlignment();
					slideRequestVoAttributes[16] = testParameterVOPT
							.getDefaultValue();
					slideRequestVoAttributes[17] = testParameterVOPT
							.getIsPrintable();
					slideRequestVoAttributes[18] = testParameterVOPT
							.getQueryCode();
					slideRequestVoAttributes[19] = testParameterVOPT
							.getButtonName();
					slideRequestVoAttributes[20] = testParameterVOPT
							.getCallUrl();
					slideRequestVoAttributes[21] = testParameterVOPT
							.getParentparametername();
					slideRequestVoAttributes[22] = testParameterVOPT
							.getTestGroupName();
					slideRequestVoAttributes[23] = testParameterVOPT
							.getIsTestGroup();
					slideRequestVoAttributes[24] = testParameterVOPT
							.getLabTestCode();

					// testGroupParameterListAttributes[testGroupParameterIndex++]
					// = new oracle.sql.STRUCT(testGroupParameterSD, connection,
					// slideRequestVoAttributes);

				}

			}

			// cstmt.setArray(1, new
			// oracle.sql.ARRAY(testCollectionAD,connection,
			// testListAttributes));
			// cstmt.setArray(2, new
			// oracle.sql.ARRAY(testGroupCollectionAD,connection,
			// testGroupListAttributes));
			// cstmt.setArray(3, new
			// oracle.sql.ARRAY(testParameterCollectionAD,connection,
			// testParameterListAttributes));
			// cstmt.setArray(4, new
			// oracle.sql.ARRAY(testGroupParameterCollectionAD,connection,
			// testGroupParameterListAttributes));
			// cstmt.setString(5,request.getParameter("templateName"));
			// cstmt.setString(6,userVO.getHospitalCode());
			// if(userVO.getSeatId()==null)
			// userVO.setSeatId("10091");
			// cstmt.setString(7, "10091");
			// cstmt.registerOutParameter(8, OracleTypes.VARCHAR);//this
			// attribute will fetch the template sequence id from the database
			// cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
			// cstmt.execute();

			// //LOGGER_INV.log(Level.INFO,"cstmt.getString(8)="+cstmt.getString(8));
			// //LOGGER_INV.log(Level.INFO,"cstmt.getString(9)="+cstmt.getString(9));
			// createXmlForTestTemplate(templateSequenceId,request);//this
			// method will create the xml for thr test template
		}
		// catch (SQLException e) {
		// try {
		// connection.rollback();
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// }
		// e.printStackTrace();
		// throw new HisDataAccessException();
		// }
		catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		finally {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<PrintingTemplateTestParameterVO> populateVOFromElement(
			List<Element> elementList) {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::populateVOFromElement");
		List<PrintingTemplateTestParameterVO> tpvoList = new ArrayList<PrintingTemplateTestParameterVO>();
		for (Element element : elementList) {
			PrintingTemplateTestParameterVO voObject = new PrintingTemplateTestParameterVO();
			if (element.getAttributes().getNamedItem("levelNo") != null)
				voObject.setLevelNo(""
						+ element.getAttributes().getNamedItem("levelNo")
								.getNodeValue());
			if (element.getAttributes().getNamedItem("parameterCode") != null)
				voObject.setParameterCode(element.getAttributes()
						.getNamedItem("parameterCode").getNodeValue());
			if (element.getAttributes().getNamedItem("parentParameterCode") != null)
				voObject.setParentParameterCode(element.getAttributes()
						.getNamedItem("parentParameterCode").getNodeValue());
			if (element.getAttributes().getNamedItem("parameterName") != null)
				voObject.setParameterName(element.getAttributes()
						.getNamedItem("parameterName").getNodeValue());
			if (element.getAttributes().getNamedItem("CriteriaCode") != null)
				voObject.setCriteriaCode(element.getAttributes()
						.getNamedItem("CriteriaCode").getNodeValue());
			if (element.getAttributes().getNamedItem("valueType") != null)
				voObject.setValueType(element.getAttributes()
						.getNamedItem("valueType").getNodeValue());
			if (element.getAttributes().getNamedItem("objectId") != null)
				voObject.setObjectId(element.getAttributes()
						.getNamedItem("objectId").getNodeValue());
			if (element.getAttributes().getNamedItem("removeStatus") != null)
				voObject.setRemoveStatus(element.getAttributes()
						.getNamedItem("removeStatus").getNodeValue());
			if (element.getAttributes().getNamedItem("functionName") != null)
				voObject.setFunctionName(element.getAttributes()
						.getNamedItem("functionName").getNodeValue());
			if (element.getAttributes().getNamedItem("eventName") != null)
				voObject.setEventName(element.getAttributes()
						.getNamedItem("eventName").getNodeValue());
			if (element.getAttributes().getNamedItem("eventValidationString") != null)
				voObject.setEventValidationString(element.getAttributes()
						.getNamedItem("eventValidationString").getNodeValue());
			if (element.getAttributes().getNamedItem("property") != null)
				voObject.setProperty(element.getAttributes()
						.getNamedItem("property").getNodeValue());
			if (element.getAttributes()
					.getNamedItem("isparameternamedisplayed") != null)
				voObject.setIsparameternamedisplayed(element.getAttributes()
						.getNamedItem("isparameternamedisplayed")
						.getNodeValue());
			if (element.getAttributes().getNamedItem("labelelementdisplaytype") != null)
				voObject.setLabelelementdisplaytype(element.getAttributes()
						.getNamedItem("labelelementdisplaytype").getNodeValue());
			if (element.getAttributes().getNamedItem("labelAlignment") != null)
				voObject.setLabelAlignment(element.getAttributes()
						.getNamedItem("labelAlignment").getNodeValue());
			if (element.getAttributes().getNamedItem("elementAlignment") != null)
				voObject.setElementAlignment(element.getAttributes()
						.getNamedItem("elementAlignment").getNodeValue());
			if (element.getAttributes().getNamedItem("defaultValue") != null)
				voObject.setDefaultValue(element.getAttributes()
						.getNamedItem("defaultValue").getNodeValue());
			if (element.getAttributes().getNamedItem("isPrintable") != null)
				voObject.setIsPrintable(element.getAttributes()
						.getNamedItem("isPrintable").getNodeValue());
			if (element.getAttributes().getNamedItem("queryCode") != null)
				voObject.setQueryCode(element.getAttributes()
						.getNamedItem("queryCode").getNodeValue());
			if (element.getAttributes().getNamedItem("buttonName") != null)
				voObject.setButtonName(element.getAttributes()
						.getNamedItem("buttonName").getNodeValue());
			if (element.getAttributes().getNamedItem("callUrl") != null)
				voObject.setCallUrl(element.getAttributes()
						.getNamedItem("callUrl").getNodeValue());
			if (element.getAttributes().getNamedItem("parentparametername") != null)
				voObject.setParentparametername(element.getAttributes()
						.getNamedItem("parentparametername").getNodeValue());
			if (element.getAttributes().getNamedItem("testGroupName") != null)
				voObject.setTestGroupName(element.getAttributes()
						.getNamedItem("testGroupName").getNodeValue());
			if (element.getAttributes().getNamedItem("isTestGroup") != null)
				voObject.setIsTestGroup(element.getAttributes()
						.getNamedItem("isTestGroup").getNodeValue());
			if (element.getAttributes().getNamedItem("labTestCode") != null)
				voObject.setLabTestCode(element.getAttributes()
						.getNamedItem("labTestCode").getNodeValue());
			if (element.getAttributes().getNamedItem("previousLabTestCode") != null)
				voObject.setPreviousLabTestCode(element.getAttributes()
						.getNamedItem("previousLabTestCode").getNodeValue());

			tpvoList.add(voObject);

		}
		return tpvoList;
	}

	public void saveModifiedData(HttpServletRequest request,
			HttpServletResponse response) {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::saveModifiedData");
		String templateType = InvestigationConfig.XML_PRINTINGTEMPLATE;
		// //LOGGER_INV.log(Level.INFO,"saveModifiedData");
		String templateSeqId = request.getParameter("templateSeqId");
		Connection connection = null;
		// CallableStatement cstmt = null;
		UserVO userVO = ControllerUTIL.getUserVO(request);
		hospitalCode = userVO.getHospitalCode();
		HashSet<String> testCodeList = (HashSet<String>) request.getSession()
				.getAttribute("TESTCODESONTEMPLATE");
		try {

			String templateRedesignMode = request
					.getParameter("templateRedesignMode");

			String templateName = request.getParameter("templateName");
			String headerHeight = request.getParameter("headerHeight");
			String headerWidth = request.getParameter("headerWidth");
			String footerHeight = request.getParameter("footerHeight");
			String footerWidth = request.getParameter("footerWidth");
		
			TestGroupTreeListVO testGroupTreeListVO = null;

			if (templateRedesignMode == null
					|| templateRedesignMode.equals("PRINTINGTEMPLATE")) {
				testGroupTreeListVO = (TestGroupTreeListVO) request
						.getSession().getAttribute("TESTGROUPTREELISTVO");
				List<Test> testList = testGroupTreeListVO.getTestModifyList();
				List<TestGroup> testGroupList = testGroupTreeListVO
						.getTestGroupModifyList();

				createXmlForTestTemplate(templateSeqId, request, templateType,
						testCodeList, request.getSession());

				
				Object[] testListAttributes = null;
				if (testList != null) {
					// //LOGGER_INV.log(Level.INFO,"testList.size -"+testList.size());
					testListAttributes = new Object[testList.size()];
					int testListIndex = 0;
					for (Test testObj : testList) {
						Object[] slideRequestVoAttributes = new Object[25];

						slideRequestVoAttributes[24] = testObj.getTestCode();
					}
				}			
				Object[] testGroupListAttributes = null;
				if (testGroupList != null) {

					testGroupListAttributes = new Object[testGroupList.size()];
					int testGroupListIndex = 0;
					for (TestGroup testGroupObj : testGroupList) {
						Object[] slideRequestVoAttributes = new Object[25];

						slideRequestVoAttributes[23] = "YES";
						slideRequestVoAttributes[24] = testGroupObj
								.getGroupCode();
						// //LOGGER_INV.log(Level.INFO,"testCode in bo method==="+testGroupObj.getGroupCode());

						// testGroupListAttributes[testGroupListIndex++] = new
						// oracle.sql.STRUCT(testGroupSD, connection,
						// slideRequestVoAttributes);

					}
				}				
			}

		}

		/*
		 * catch (SQLException e) { e.printStackTrace(); try {
		 * connection.rollback(); } catch (SQLException e1) {
		 * e1.printStackTrace(); } e.printStackTrace(); throw new
		 * HisDataAccessException(); }
		 */catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		finally {
			try {
				// connection.commit();
				if (connection != null)
					connection.close();

				// this method will create the xml
				// for thr test template
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean createXmlForTestTemplate(String templateSequenceId,
			HttpServletRequest request, String templateType,
			HashSet<String> testCodeList, HttpSession session) {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::createXmlForTestTemplate");
		InvestigationTemplateDataHelper documentCacheManager = InvestigationTemplateDataHelper
				.getInstance();

		Document document = (Document) request.getSession().getAttribute(
				"TESTTEMPLATEDOCUMENT");

		try {

			boolean isNewTemplate = false;
			//templateSequenceId = null;
			if (templateSequenceId == null || templateSequenceId.length() == 0
					|| templateSequenceId == "") {
				templateSequenceId = InvestigationTemplateDataHelper
						.getInstance().getDynamicTemplateSeqID();
				isNewTemplate = true;
			}
			Document doc = null;
			try {
				doc = documentCacheManager.loadDynamicDocumentObj(templateType,
						session, templateSequenceId);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (doc == null) {
				doc = documentCacheManager.getNewDocument();
				Element root = doc.createElement("templates");
				doc.appendChild(root);
			}

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath
					.compile("/templates/testtemplate[@labTestCode='"
							+ templateSequenceId + "']");
			System.out.println("/templates/testtemplate[@labTestCode='"
					+ templateSequenceId + "']");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodeList = (NodeList) result;
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				node.getParentNode().removeChild(node);
				result = expr.evaluate(doc, XPathConstants.NODESET);
				nodeList = (NodeList) result;
				i--;
			}

			String templateName = request.getParameter("templateName");
			String headerHeight = request.getParameter("headerHeight");
			String headerWidth = request.getParameter("headerWidth");
			String footerHeight = request.getParameter("footerHeight");
			String footerWidth = request.getParameter("footerWidth");
			String pageHeight = request.getParameter("pageHeight");
			String pageWidth = request.getParameter("pageWidth");
			Element templateNode = (Element) document.getFirstChild();
			templateNode.setAttribute("labTestCode", templateSequenceId);
			templateNode.setAttribute("headerheight", headerHeight);
			templateNode.setAttribute("headerwidth", headerWidth);
			templateNode.setAttribute("footerheight", footerHeight);
			templateNode.setAttribute("footerwidth", footerWidth);
			templateNode.setAttribute("pageheight", pageHeight);
			templateNode.setAttribute("pagewidth", pageWidth);
			templateNode.setAttribute("name", templateName);
			templateNode.setAttribute("printingId", templateSequenceId);

			doc.getFirstChild().appendChild(
					doc.importNode(document.getFirstChild(), true));

			try {

				TemplateProcessingHLP templateProcessingClassObject = new TemplateProcessingHLP(
						1);

				Transformer transformer = documentCacheManager
						.getNewTransformer();
				DOMSource domSource = new DOMSource(doc);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				/*
				 * templateProcessingClassObject.loadDocumentStringToDatabase(
				 * templateType, "printingtemplate.xml", "printingtemplate.xml",
				 * baos.toString(), session);
				 */
				
				List<Entry> tpList = (List<Entry>)request.getSession().getAttribute("EXSISTTEMPLATELIST");
				String isGroup = "0";
				Entry entry = new Entry();
				if(tpList != null)
				{
					for(int i = 0; i < tpList.size(); i++)
					{
						if(templateSequenceId.equals(tpList.get(i).getValue())) {
							isGroup = tpList.get(i).getIsGroupTest();
							entry = tpList.get(i);
						}
					}
				}
				documentCacheManager.insertDynamicTemplate(templateSequenceId,
						isNewTemplate, templateName, headerHeight, headerWidth,
						footerHeight, footerWidth, pageHeight, pageWidth,
						baos.toString(), testCodeList, isGroup, entry);

				System.out.println(baos.toString());
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {

			ex.printStackTrace();
			return false;
		} finally {

		}

		return true;
	}

	public void writeLabel(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeLabel");

		InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
				.getInstance();

		javax.xml.transform.Transformer transformer = null;
		String htmlString = null;
		String rows = request.getParameter("rows");
		String tableId = request.getParameter("tableId");
		String cols = request.getParameter("cols");
		String label = request.getParameter("label");
		String fontColor = request.getParameter("fontColor");
		String fontSize = request.getParameter("fontSize");
		String align = request.getParameter("align");
		String bold = request.getParameter("bold");
		String underLine = request.getParameter("underLine");
		Document document = null;
		String requesttemplateType = request.getParameter("templateType");
		if (requesttemplateType == null) {
			document = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			document = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		// LOGGER_INV.log(Level.INFO,"node 1: /testtemplate/table[@type='"+tableId+"']/rowDetails[@rowNo='"+rows+"']/columnDetails[@colNo='"+cols+"']/table/tr/td");

		try {

			// manager.getNewTransformer().transform(new DOMSource(document),
			// new StreamResult("d:/Step1.xml"));

			XPathExpression expr = xpath.compile("/testtemplate/table[@type='"
					+ tableId + "']/rowDetails[@rowNo='" + rows
					+ "']/columnDetails[@colNo='" + cols + "']/table/tr/td");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			Node node1 = null;
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				node1 = nodes.item(i);
				node1.getChildNodes();
				for (int j = 0; j < node1.getChildNodes().getLength(); j++) {

					node1.removeChild(node1.getChildNodes().item(j));
				}

			}

			Element newlabelElement = document.createElement("element");
			newlabelElement.setAttribute("idC", "label");
			newlabelElement.setAttribute("fontcolor", fontColor);
			newlabelElement.setAttribute("fontsize", fontSize);
			newlabelElement.setAttribute("align", align);
			newlabelElement.setAttribute("bold", bold);
			newlabelElement.setAttribute("value", label);
			newlabelElement.setAttribute("underline", underLine);
			node1.appendChild(newlabelElement);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// manager.getNewTransformer().transform(new
				// DOMSource(document), new StreamResult("d:/Step2.xml"));
				transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
				Source domSource = new DOMSource(document);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	public void deleteTable(HttpServletRequest request,
			HttpServletResponse response) {

		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::deleteTable");
		String templateType = request.getParameter("templateType");
		Document printingtemplateDoc = null;
		if (templateType == null) {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		String tableId = request.getParameter("tableid");
		XPathExpression expr;

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node tableNode = null;
		Object result;
		try {
			expr = xpath
					.compile("/testtemplate/table[@type='" + tableId + "']");
			result = expr.evaluate(printingtemplateDoc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength()+"tableID=="+tableId);
			for (int i = 0; i < nodes.getLength(); i++) {
				// LOGGER_INV.log(Level.INFO,"inside for loop in modify table");
				tableNode = nodes.item(i);

			}
			tableNode.getParentNode().removeChild(tableNode);
		} catch (XPathExpressionException e1) {

			e1.printStackTrace();
		} finally {
			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);

				Source domSource = new DOMSource(printingtemplateDoc);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				String htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public void showtablewithBorder(HttpServletRequest request,
			HttpServletResponse response) {

		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::deleteTable");
		String templateType = request.getParameter("templateType");
		Document printingtemplateDoc = null;
		if (templateType == null) {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		String tableId = request.getParameter("tableid");
		XPathExpression expr;

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node tableNode = null;
		Object result;
		try {
			expr = xpath
					.compile("/testtemplate/table[@type='" + tableId + "']");
			result = expr.evaluate(printingtemplateDoc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength()+"tableID=="+tableId);
			for (int i = 0; i < nodes.getLength(); i++) {
				// LOGGER_INV.log(Level.INFO,"inside for loop in modify table");
				tableNode = nodes.item(i);
				String val="1";
				tableNode.getAttributes().getNamedItem("border").setNodeValue(""+val+"%");
			}
			tableNode.getParentNode().appendChild(tableNode);
		} catch (XPathExpressionException e1) {

			e1.printStackTrace();
		} finally {
			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);

				Source domSource = new DOMSource(printingtemplateDoc);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				String htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	
	public void showtablewithoutBorder(HttpServletRequest request,
			HttpServletResponse response) {

		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::deleteTable");
		String templateType = request.getParameter("templateType");
		Document printingtemplateDoc = null;
		if (templateType == null) {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			printingtemplateDoc = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		String tableId = request.getParameter("tableid");
		XPathExpression expr;

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node tableNode = null;
		Object result;
		try {
			expr = xpath
					.compile("/testtemplate/table[@type='" + tableId + "']");
			result = expr.evaluate(printingtemplateDoc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength()+"tableID=="+tableId);
			for (int i = 0; i < nodes.getLength(); i++) {
				// LOGGER_INV.log(Level.INFO,"inside for loop in modify table");
				tableNode = nodes.item(i);
				String val="0";
				tableNode.getAttributes().getNamedItem("border").setNodeValue(""+val+"%");
			}
			tableNode.getParentNode().appendChild(tableNode);
		} catch (XPathExpressionException e1) {

			e1.printStackTrace();
		} finally {
			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);

				Source domSource = new DOMSource(printingtemplateDoc);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				String htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteLabel(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::deleteLabel");
		javax.xml.transform.Transformer transformer = null;
		String htmlString = null;

		String tableId = request.getParameter("tableId");
		String rows = request.getParameter("rows");
		String cols = request.getParameter("cols");

		Document document = null;
		String requesttemplateType = request.getParameter("templateType");
		if (requesttemplateType == null) {
			document = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			document = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		// LOGGER_INV.log(Level.INFO,"node 1: /testtemplate/table[@type='"+tableId+"']/rowDetails[@rowNo='"+rows+"']/columnDetails[@colNo='"+cols+"']/table/tr/td");

		try {

			XPathExpression expr = xpath.compile("/testtemplate/table[@type='"
					+ tableId + "']/rowDetails[@rowNo='" + rows
					+ "']/columnDetails[@colNo='" + cols + "']/table/tr/td");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			Node node1 = null;
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				node1 = nodes.item(i);
				node1.getChildNodes();
				for (int j = 0; j < node1.getChildNodes().getLength(); j++) {
					node1.removeChild(node1.getChildNodes().item(j));
				}

			}

			// node1.getParentNode().removeChild(node1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();

				transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
				Source domSource = new DOMSource(document);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void AddRangeElement(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::AddRangeElement");
		String elementTableNo = request.getParameter("elementTableNo");
		String rangeElementTableNo = request
				.getParameter("rangeElementTableNo");
		String elementRowNo = request.getParameter("elementRowNo");
		String elementColNo = request.getParameter("elementColumnNo");
		String rangeElementRowNo = request.getParameter("rangeElementRowNo");
		String rangeElementColNo = request.getParameter("rangeElementColumnNo");
		String elementName = null;
		Document document = null;
		String requesttemplateType = request.getParameter("templateType");
		if (requesttemplateType == null) {
			document = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			document = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		try {
			// InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/s1.xml",
			// document);
			// LOGGER_INV.log(Level.INFO,document+" xquery : /testtemplate/table[@type='"+elementTableNo+"']/rowDetails[@rowNo='"+elementRowNo+"']/columnDetails[@colNo='"+elementColNo+"']/table/tr/td/element[@idC!='']");
			expr = xpath.compile("/testtemplate/table[@type='" + elementTableNo
					+ "']/rowDetails[@rowNo='" + elementRowNo
					+ "']/columnDetails[@colNo='" + elementColNo
					+ "']/table/tr/td/element[@idC!='']");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			NodeList elementNodeList = ((NodeList) result);
			if (elementNodeList.getLength() != 0) {
				for (int i = 0; i < elementNodeList.getLength(); i++) {
					elementName = elementNodeList.item(i).getAttributes()
							.getNamedItem("name").getNodeValue();
				}

			}

			// LOGGER_INV.log(Level.INFO,"elementName  :::::::::::::::::"+elementName);
			expr = xpath.compile("/testtemplate/table[@type='"
					+ rangeElementTableNo + "']/rowDetails[@rowNo='"
					+ rangeElementRowNo + "']/columnDetails[@colNo='"
					+ rangeElementColNo + "']/table/tr/td");
			Element newlabelElement = document.createElement("element");
			newlabelElement.setAttribute("idC", "range");
			newlabelElement.setAttribute("roe", elementName);
			newlabelElement.setAttribute("rowNo", elementRowNo);
			newlabelElement.setAttribute("colNo", elementColNo);
			newlabelElement.setAttribute("tableNo", elementTableNo);
			newlabelElement.setAttribute("align", "center");
			newlabelElement.setAttribute("fontsize", "1");
			newlabelElement.setAttribute("fontcolor", "black");
			result = expr.evaluate(document, XPathConstants.NODESET);
			
			Node node1 = null;
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
			for (int i1 = 0; i1 < nodes.getLength(); i1++) {
				node1 = nodes.item(i1);
				node1.getChildNodes();
				for (int j = 0; j < node1.getChildNodes().getLength(); j++) {
					node1.removeChild(node1.getChildNodes().item(j));
				}
			}	
			
			
			elementNodeList = ((NodeList) result);
			node1.appendChild(newlabelElement);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();

				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
				Source domSource = new DOMSource(document);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				String htmlString = baos.toString();
				writeResponse(response, htmlString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void redesignTemplateAjax(HttpServletRequest request,
			HttpServletResponse response) {
		// LOGGER_INV.log(Level.INFO, "RedeisignTemplateAJAXServlet::Do Get");
		String tableNo = request.getParameter("tableNo");
		String elementNo = request.getParameter("elementNo");
		initializeElementMap();
		if (elementNo == null) {
			elementNo = "1";
		}

		String rowNo = request.getParameter("rowNo");
		String colNo = request.getParameter("colNo");
		try {
			InvestigationTemplateDataHelper documentManager = InvestigationTemplateDataHelper
					.getInstance();
			Node returnedColumnNode = getColumnAndElementProperties(request,
					response, tableNo, rowNo, colNo);

			Document resultDocument = documentManager.getNewDocument();

			Element rootNode = resultDocument.createElement("results");
			resultDocument.appendChild(rootNode);

			/* Populating column properties */

			Element result1Node = resultDocument.createElement("result");
			result1Node.setAttribute("id", "columnProperties");
			result1Node.setAttribute("divid", "columnPropertiesDiv");
			String saveColumnString = "<table width='100%' bgcolor='lightgreen' ><tr><td class='tdfont' width='100%'><div align='center'><input type='button' class='sub5' value='Save Column Properties' onclick=\"savecolumnProperties('"
					+ tableNo
					+ "','"
					+ rowNo
					+ "','"
					+ colNo
					+ "')\"/></div></td></tr></table>";
			String columnProperties = "<table width='100%'  bgcolor='lightgreen' cellspacing='1' style='border-color:black'><tr><td class='tdfont' colspan='2' width='100%'><b>Column Properties</b></td></tr>";
			columnProperties = createColumnPropertiesString(returnedColumnNode,
					columnProperties);
			columnProperties += "</table>";
			result1Node.setAttribute("dividhtml", columnProperties
					+ saveColumnString);
			rootNode.appendChild(result1Node);

			/* Populating Element List */
			Element result2Node = resultDocument.createElement("result");
			result2Node.setAttribute("id", "elementList");
			result2Node.setAttribute("divid", "elementListDiv");

			NodeList elementNodes = returnedColumnNode.getChildNodes();
			String elementListString = "";
			if (elementNodes.getLength() != 0) {
				for (int i = 0; i < elementNodes.getLength(); i++) {
					elementListString = "<li><a onClick='ajaxCallerForColumnProperties(\"/HISInvestigationG5/new_investigation/redesignAJAXServlet\",document.forms[0].tableNo.value,document.forms[0].rowNo.value,document.forms[0].colNo.value)'>element["
							+ i + "]</a></li>";
				}
			} else {
				elementListString = "<font color='red'>No ElementS Present!</font>";
			}

			result2Node.setAttribute("dividhtml", elementListString);
			rootNode.appendChild(result2Node);

			Element result3Node = resultDocument.createElement("result");
			result3Node.setAttribute("id", "elementProperties");
			result3Node.setAttribute("divid", "elementPropertiesDiv");
			result3Node.setAttribute("dividhtml", "");
			rootNode.appendChild(result3Node);

			Node returnedElementNode = getElementProperties(request, response,
					tableNo, rowNo, colNo, elementNo);
			if (returnedElementNode != null) {

				String saveElementString = "<table width='100%' bgcolor='lightgreen' ><tr><td width='100%' class='tdfont'><div align='center'><input type='button' class='sub5' value='Save Element Properties ' onclick=\"saveElementProperties('"
						+ tableNo
						+ "','"
						+ rowNo
						+ "','"
						+ colNo
						+ "','"
						+ elementNo + "')\"/></div></td></tr></table>";
				String elementProperties = "<table width='100%' cellspacing='1' style='border-color:black'><tr><td class='tdfont' colspan='3' width='100%'><b>Element Properties</b></td></tr>";
				elementProperties = createElementPropertiesString(
						returnedElementNode, elementProperties);
				elementProperties += "</table>";
				result3Node.setAttribute("dividhtml", elementProperties
						+ saveElementString);
			}

			Transformer transformer = documentManager.getNewTransformer();
			Source testProcedureStyleSheet = new DOMSource(resultDocument);
			java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
			Result result = new StreamResult(baos);
			transformer.transform(testProcedureStyleSheet, result);
			transformer = null;
			// response.getWriter().write(baos.toString());
			String htmlString = baos.toString();
			writeResponse(response, htmlString);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Node getColumnAndElementProperties(HttpServletRequest request,
			HttpServletResponse response, String tableNo, String rowNo,
			String colNo) {
		// LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: getColumnAndElementProperties");
		Document document = null;
		String requesttemplateType = request.getParameter("templateType");
		if (requesttemplateType == null) {
			document = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			document = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		Node columnDetailNode = null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		try {
			// LOGGER_INV.log(Level.INFO,document+" xquery : /testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']/columnDetails[@colNo='"+colNo+"']");
			expr = xpath.compile("/testtemplate/table[@type='" + tableNo
					+ "']/rowDetails[@rowNo='" + rowNo
					+ "']/columnDetails[@colNo='" + colNo + "']");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			// LOGGER_INV.log(Level.INFO,"requesttemplateType::"+requesttemplateType+"  "+((NodeList)result).getLength());
			columnDetailNode = ((NodeList) result).item(0);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return columnDetailNode;
	}

	public Node getElementProperties(HttpServletRequest request,
			HttpServletResponse response, String tableNo, String rowNo,
			String colNo, String elementNo) {
		// LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: getElementProperties");
		Document document = null;
		String requesttemplateType = request.getParameter("templateType");
		if (requesttemplateType == null) {
			document = (Document) request.getSession().getAttribute(
					"TESTTEMPLATEDOCUMENT");
		} else {
			document = (Document) request.getSession().getAttribute(
					"MODIFYINGNODE");
		}

		Node columnDetailNode = null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		try {
			// LOGGER_INV.log(Level.INFO,document+" xquery : /testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']/columnDetails[@colNo='"+colNo+"']/table/tr/td/element["+elementNo+"]");
			expr = xpath.compile("/testtemplate/table[@type='" + tableNo
					+ "']/rowDetails[@rowNo='" + rowNo
					+ "']/columnDetails[@colNo='" + colNo
					+ "']/table/tr/td/element[" + elementNo + "]");
			Object result = expr.evaluate(document, XPathConstants.NODESET);
			if (((NodeList) result).getLength() != 0)
				columnDetailNode = ((NodeList) result).item(0);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return columnDetailNode;
	}

	public void initializeElementMap() {
		elementMap.put("align", "Align");
		elementMap.put("defaultValue", "Default Value");
		elementMap.put("eventName", "Event");
		elementMap.put("eventValidationString", "Event Validation");
		elementMap.put("isPrintable", "Printable");
		elementMap.put("maxlength", "Max Length");
		elementMap.put("name", "Name");
		elementMap.put("size", "Size");
		elementMap.put("type", "Sub Type");
		elementMap.put("rows", "Rows");
		elementMap.put("cols", "Cols");
		elementMap.put("bold", "Bold");
		elementMap.put("underline", "Under Line");
		elementMap.put("colNo", "Col No");
		elementMap.put("class", "Class");
		elementMap.put("width", "Width");
		elementMap.put("idC", "Element Type");
		elementMap.put("value", "Value");
		elementMap.put("editor", "Editor");
		elementMap.put("colspan", "Column span");
		elementMap.put("fontcolor", "Font color");
		elementMap.put("fontsize", "Font size");
		elementMap.put("typeOfElement", "Element Type");
		elementMap.put("changeTableId", "Append Table Id"); /* to have id of the table to be appended - puneet*/
	}

	private String createElementPropertiesString(Node returnedElementNode,
			String elementProperties) {
		NamedNodeMap elementPropertiesNodeMap = returnedElementNode
				.getAttributes();
		// LOGGER_INV.log(Level.INFO, "element attribute length "
		// + elementPropertiesNodeMap.getLength());
		boolean fontdetailsfound = false;
		boolean aligndetailsfound = false;
		boolean bolddetailsfound = false;
		for (int i = 0; i < elementPropertiesNodeMap.getLength(); i++) {
			Node propertyNode = elementPropertiesNodeMap.item(i);
			// LOGGER_INV.log(Level.INFO,
			// "element attribute name " + propertyNode.getNodeName()
			// + "  value " + propertyNode.getNodeValue());

			if (propertyNode.getNodeName().equals("fontsize")) {
				fontdetailsfound = true;
			}

			if (propertyNode.getNodeName().equals("align")) {
				aligndetailsfound = true;
			}

			if (propertyNode.getNodeName().equals("bold")) {
				bolddetailsfound = true;
			}

			if (elementMap.get(propertyNode.getNodeName()) != null) {
				elementProperties += "<tr><td class='tdfont' width='30%'><b>"
						+ elementMap.get(propertyNode.getNodeName())
						+ "</b></td><td class='tdfont' width='60%' colspan='2'>"
						+ getElementAttributesProperty(
								propertyNode.getNodeName(),
								propertyNode.getNodeValue()) + "</td></tr>";
			}
		}

		if (fontdetailsfound == false) {
			elementProperties += "<tr><td class='tdfont' width='30%'><b>"
					+ elementMap.get("fontsize")
					+ "</b></td><td class='tdfont' width='60%' colspan='2'>"
					+ getElementAttributesProperty("fontsize", "1")
					+ "</td></tr>";
		}

		if (aligndetailsfound == false) {
			elementProperties += "<tr><td class='tdfont' width='30%'><b>"
					+ elementMap.get("align")
					+ "</b></td><td class='tdfont' width='60%' colspan='2'>"
					+ getElementAttributesProperty("align", "center")
					+ "</td></tr>";
		}

		if (aligndetailsfound == false) {
			elementProperties += "<tr><td class='tdfont' width='30%'><b>"
					+ elementMap.get("bold")
					+ "</b></td><td class='tdfont' width='60%' colspan='2'>"
					+ getElementAttributesProperty("bold", "0") + "</td></tr>";
		}

		if (returnedElementNode.getAttributes().getNamedItem("idC")
				.getNodeValue().equalsIgnoreCase("select")) {
			// LOGGER_INV.log(Level.INFO, "select found"
			// + returnedElementNode.getChildNodes().getLength());
			NodeList optionsNodeList = returnedElementNode.getChildNodes();
			for (int k = 0; k < optionsNodeList.getLength(); k++) {
				Node optionsNode = optionsNodeList.item(k);
				// LOGGER_INV.log(Level.INFO,
				// "select node name" + optionsNode.getNodeName());
				if (optionsNode.getNodeName().equalsIgnoreCase("options")) {
					NodeList optionNodeList = optionsNode.getChildNodes();
					// LOGGER_INV.log(Level.INFO,
					// "select found" + optionNodeList.getLength());
					for (int j = 0; j < optionNodeList.getLength(); j++) {

						Node optionNode = optionNodeList.item(j);
						elementProperties += "<tr><td class='tdfont' width='30%'>"
								+ optionNode.getAttributes()
										.getNamedItem("label").getNodeValue()
								+ "</td>"
								+ "<td width='30%'>"
								+ optionNode.getAttributes()
										.getNamedItem("value").getNodeValue()
								+ "</td>"
								+ "<td><input type='radio' name='selectedOptionRadio'  value='"
								+ optionNode.getAttributes()
										.getNamedItem("label").getNodeValue()
								+ "'"
								+ ((returnedElementNode.getAttributes()
										.getNamedItem("defaultValue")
										.getNodeValue().equals(optionNode
										.getAttributes().getNamedItem("value")
										.getNodeValue())) ? "" : "checked")
								+ "/>" + "</tr>";
					}
				}
			}

		}

		return elementProperties;
	}

	private String createColumnPropertiesString(Node returnedColumnNode,
			String columnProperties) {

		NamedNodeMap columnPropertiesNodeMap = returnedColumnNode
				.getAttributes();
		for (int i = 0; i < columnPropertiesNodeMap.getLength(); i++) {
			Node propertyNode = columnPropertiesNodeMap.item(i);
			columnProperties += "<tr><td width='70%' class='tdfont'><b>"
					+ elementMap.get(propertyNode.getNodeName())
					+ "</b></td><td width='30%' class='tdfont'>"
					+ getColumnAttributeElementproperty(
							propertyNode.getNodeName(),
							propertyNode.getNodeValue()) + "</td></tr>";

		}

		return columnProperties;
	}

	private String getColumnAttributeElementproperty(String nodeName,
			String nodeValue) {
		// LOGGER_INV.log(Level.INFO,
		// "ColumnAttributeElement Element Properties nodeName:"
		// + nodeName + " nodeName " + nodeValue);
		String returnString = "";
		if (nodeName.equalsIgnoreCase("align")) {
			returnString = "<select name='columnAlign' value='" + nodeValue
					+ "'><option value='-1'>Select</option>";
			if (nodeValue.equals("center")) {
				returnString += "<option value='center' selected>center</option>";
			} else {
				returnString += "<option value='center'>center</option>";
			}

			if (nodeValue.equals("left")) {
				returnString += "<option value='left' selected>left</option>";
			} else {
				returnString += "<option value='left'>left</option>";
			}

			if (nodeValue.equals("right")) {
				returnString += "<option value='right' selected>right</option>";
			} else {
				returnString += "<option value='right'>right</option>";
			}

			returnString += "</select>";
		} else if (nodeName.equalsIgnoreCase("colspan")) {
			returnString = "<input type='text' name='columnColSpan' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("class")) {
			returnString = "<input type='text' name='columnClass' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("width")) {
			returnString = "<input type='text' name='columnWidth' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("colNo")) {
			returnString = "<input type='text' name='columnNo' value='"
					+ nodeValue + "'/>";
		} else {

		}

		return returnString;
	}

	private String getElementAttributesProperty(String nodeName,
			String nodeValue) {
		String returnString = "";
		// LOGGER_INV.log(Level.INFO,
		// " ElementAttributesProperty attribute Name:"
		// + nodeName);
		if (nodeName.equalsIgnoreCase("align")) {
			returnString = "<select name='elementAlign' value='" + nodeValue
					+ "'><option value='-1'>Select</option>";
			if (nodeValue.equals("center")) {
				returnString += "<option value='center' selected>center</option>";
			} else {
				returnString += "<option value='center'>center</option>";
			}

			if (nodeValue.equals("left")) {
				returnString += "<option value='left' selected>left</option>";
			} else {
				returnString += "<option value='left'>left</option>";
			}

			if (nodeValue.equals("right")) {
				returnString += "<option value='right' selected>right</option>";
			} else {
				returnString += "<option value='right'>right</option>";
			}

			returnString += "</select>";
		} else if (nodeName.equalsIgnoreCase("defaultValue")) {
			returnString = "<input type='text' name='elementdefaultValue' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("eventName")) {
			returnString = "<input type='text' name='elementeventName' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("eventValidationString")) {
			returnString = "<input type='text' name='elementValidationString' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("maxLength")) {
			returnString = "<input type='text' name='elementmaxLength' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("size")) {
			returnString = "<input type='text' name='elementsize' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("name")) {
			returnString = "<input type='text' name='elementname' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("url")) {
			returnString = "<input type='text' name='elementurl' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("rows")) {
			returnString = "<input type='text' name='elementrows' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("cols")) {
			returnString = "<input type='text' name='elementcols' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("value")) {
			returnString = "<input type='text' name='elementvalue' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("fontcolor")) {
			returnString = "<input type='text' name='elementfontcolor' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("fontsize")) {
			returnString = "<input type='text' name='elementfontsize' value='"
					+ nodeValue + "'/>";
		} else if (nodeName.equalsIgnoreCase("isPrintable")) {
			returnString = "<select name='elementisprintable' value='"
					+ nodeValue + "'>";
			if (nodeValue.equals("0")) {
				returnString += "<option value='0' selected>No</option>";
			} else {
				returnString += "<option value='0'>No</option>";
			}

			if (nodeValue.equals("1")) {
				returnString += "<option value='1' selected>Yes</option>";
			} else {
				returnString += "<option value='1'>Yes</option>";
			}

			returnString += "</select>";
		} else if (nodeName.equalsIgnoreCase("editor")) {
			returnString = "<select name='elementeditor' value='" + nodeValue
					+ "'>";
			if (nodeValue.equals("0")) {
				returnString += "<option value='0' selected>No</option>";
			} else {
				returnString += "<option value='0'>No</option>";
			}

			if (nodeValue.equals("1")) {
				returnString += "<option value='1' selected>Yes</option>";
			} else {
				returnString += "<option value='1'>Yes</option>";
			}

			returnString += "</select>";
		} else if (nodeName.equalsIgnoreCase("bold")) {
			returnString = "<select name='elementbold' value='" + nodeValue
					+ "'>";
			if (nodeValue.equals("0")) {
				returnString += "<option value='0' selected>No</option>";
			} else {
				returnString += "<option value='0'>No</option>";
			}

			if (nodeValue.equals("1")) {
				returnString += "<option value='1' selected>Yes</option>";
			} else {
				returnString += "<option value='1'>Yes</option>";
			}

			returnString += "</select>";
		} else if (nodeName.equalsIgnoreCase("underline")) {
			returnString = "<select name='elementunderline' value='"
					+ nodeValue + "'>";
			if (nodeValue.equals("0")) {
				returnString += "<option value='0' selected>No</option>";
			} else {
				returnString += "<option value='0'>No</option>";
			}

			if (nodeValue.equals("1")) {
				returnString += "<option value='1' selected>Yes</option>";
			} else {
				returnString += "<option value='1'>Yes</option>";
			}

			returnString += "</select>";
		} else if (nodeName.equalsIgnoreCase("changeTableId")) {
			returnString = "<input type='text' name='elementchangeTableId' value='"
					+ nodeValue + "'/>";
		} else {
			returnString = nodeValue;
		}

		// LOGGER_INV.log(Level.INFO, "  " + returnString);
		return returnString;
	}

	public void initModificationProcess(HttpServletRequest request,
			HttpServletResponse response) {

		String templateSeqId = request.getParameter("templateIdRadio");
		String paraType=request.getParameter("paraType");
		String isGroup = null;//request.getParameter("isGroup");
		
		HttpSession session = request.getSession();
		TestGroupTreeListVO testGroupTreeListVO = (TestGroupTreeListVO) session
				.getAttribute("TESTGROUPTREELISTVO");
		HashSet<String> testCodeList = (HashSet<String>) session
						.getAttribute("TESTCODESONTEMPLATE");
		if (testCodeList == null)
			testCodeList = new HashSet<String>();

				
		if (testGroupTreeListVO == null)
			testGroupTreeListVO = new TestGroupTreeListVO();

		InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
				.getInstance();
		List<Entry> tpList = (List<Entry>)request.getSession().getAttribute("EXSISTTEMPLATELIST");
		Entry eDetail = new Entry();
		if(tpList != null)
		{
			for(int i = 0; i < tpList.size(); i++)
			{
				Entry e = tpList.get(i);
				if(e.getValue().equals(templateSeqId))
				{
					eDetail = e;
					break;
				}
			}
		}
		
		isGroup = eDetail.getIsGroupTest();
		
		if(paraType.equals("0"))
		testGroupTreeListVO = manager.fetchTestForDynamicTemplate(
				templateSeqId, testGroupTreeListVO, eDetail);

		else if(paraType.equals("1"))
			testGroupTreeListVO = manager.fetchTestForDepartmentTemplate(
					templateSeqId, testGroupTreeListVO, eDetail);

		else if(paraType.equals("2"))	
			testGroupTreeListVO = manager.fetchTestForDynamicRequisitionTemplate(
					templateSeqId, testGroupTreeListVO, eDetail);

		else
			;
		
		List<Test> t = (List<Test>) testGroupTreeListVO.getTestModifyList();
		String testName = null;
		if( t != null) {
			for ( int i = 0; i < t.size(); i++)
			{
				testCodeList.add(t.get(i).getTestCode());
				if( i == 0)
					testName = t.get(i).getTestName();
				else 
					testName += " , " + t.get(i).getTestName();
			}
		}
		session.setAttribute("TESTCODESONTEMPLATE", testCodeList);
		session.setAttribute("TESTGROUPTREELISTVO", testGroupTreeListVO);
		String strTree = writingResponse(request, response);

		Document document = null;
		String documentStr = "";
		boolean newTemplate = true;
		
		try {

			
			Transformer transformer = manager
					.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
			document = manager.getNewDocument();
			Node existingNode = manager.getTemplateNodeObj(
					InvestigationConfig.XML_PRINTINGTEMPLATE,
					templateSeqId, session, true);
			if( existingNode != null ) {
				document.appendChild(document.importNode(existingNode, true));
			request.getSession().setAttribute("TESTTEMPLATEDOCUMENT", document);
			String headerHeight = ((document.getChildNodes().item(0)
					.getAttributes().getNamedItem("headerheight") == null) ? "100"
					: document.getChildNodes().item(0).getAttributes()
							.getNamedItem("headerheight").getNodeValue());
			String headerWidth = ((document.getChildNodes().item(0)
					.getAttributes().getNamedItem("headerwidth") == null) ? "100%"
					: document.getChildNodes().item(0).getAttributes()
							.getNamedItem("headerwidth").getNodeValue());

			String footerHeight = ((document.getChildNodes().item(0)
					.getAttributes().getNamedItem("footerheight") == null) ? "42"
					: document.getChildNodes().item(0).getAttributes()
							.getNamedItem("footerheight").getNodeValue());
			String footerWidth = ((document.getChildNodes().item(0)
					.getAttributes().getNamedItem("footerwidth") == null) ? "100%"
					: document.getChildNodes().item(0).getAttributes()
							.getNamedItem("footerwidth").getNodeValue());
			String pageHeight = ((document.getChildNodes().item(0)
					.getAttributes().getNamedItem("pageheight") == null) ? InvestigationConfig.pagewidthheight
					: document.getChildNodes().item(0).getAttributes()
							.getNamedItem("pageheight").getNodeValue());
			String pageWidth = ((document.getChildNodes().item(0)
					.getAttributes().getNamedItem("pagewidth") == null) ? InvestigationConfig.pagewidthprinting
					: document.getChildNodes().item(0).getAttributes()
							.getNamedItem("pagewidth").getNodeValue());
			Source domSource = new DOMSource(document);
			java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

			StreamResult streamResult = new StreamResult(baos);
			transformer.transform(domSource, streamResult);
			documentStr = baos.toString() + "#$#" + headerHeight + "#$#"
					+ headerWidth + "#$#" + footerHeight + "#$#" + footerWidth
					+ "#$#" + pageHeight + "#$#" + pageWidth;
			
			newTemplate = false;
			}
			else
			{
				//new Document
			//	newTemplateCreation(request, response);
				 document = InvestigationTemplateDataHelper.getInstance()
						.getNewDocument();
				//request.getSession().removeAttribute("TESTGROUPTREELISTVO");
				//request.getSession().removeAttribute("TESTTEMPLATEDOCUMENT");
				request.getSession().removeAttribute("TESTCODESONTEMPLATE");
				Element testtemplateElement = document.createElement("testtemplate");
				document.appendChild(testtemplateElement);
				testtemplateElement.setAttribute("labTestCode", "");
				request.getSession().setAttribute("TESTTEMPLATEDOCUMENT", document);
				Source domSource = new DOMSource(document);
				java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

				StreamResult streamResult = new StreamResult(baos);
				transformer.transform(domSource, streamResult);
				
				documentStr = baos.toString() + "#$#" + "" + "#$#"
						+ "" + "#$#" + "" + "#$#" + ""
						+ "#$#" + "" + "#$#" + "";
				newTemplate = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String testType = null;
		boolean isGroupTemplate = false;
		if( isGroup.equals("1"))
		{
			testType = "Group:" + testGroupTreeListVO.getGroupName();// + " (" + testGroupTreeListVO.getHospitalName() + ")";
			isGroupTemplate = true;
			
		}
		else
		{
			testType = "Test";
			isGroupTemplate = false;
		}
		if( !newTemplate ) {
			strTree += "#$#" + documentStr;
			
			if(isGroupTemplate)
			 strTree += "#$#" + "Template Found. Type: " + testType + " Test Names: " + testName;
			else 
				strTree += "#$#" + "Template Found. Type: " + testType + " Name: " + testName;
		}
		else
		{
			strTree +=  "#$#" + documentStr;
			if(isGroupTemplate)
				strTree  += "#$#" + "No Template Found. Type: " +  testType  + " Test Names: " + testName;
			else 
				strTree  += "#$#" +  "No Template Found. Type: " +  testType + " Name: " + testName;
		}
		try {
			writeResponse(response, strTree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	return strTree;

	}
	
	
	public void deleteElementFromDocument(HttpServletRequest request,HttpServletResponse response) {
		
		
		InvestigationTemplateDataHelper manager=InvestigationTemplateDataHelper.getInstance();
		 
		Transformer transformer = manager.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
	    
		
		String htmlString=null;
		
		String tableId=request.getParameter("tableId");
		String rows=request.getParameter("rows");
		String cols=request.getParameter("cols");
		
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		 Document newDocument=null;
		try
		{
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableId+"']/rowDetails[@rowNo='"+rows+"']/columnDetails[@colNo='"+cols+"']/table/tr/td");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    Node node1=null;
	    NodeList nodes = (NodeList) result;	    for(int i=0;i<nodes.getLength();i++)
	    {
	    	node1=nodes.item(i);
	    	node1.getChildNodes();
	    	for(int j=0;j<node1.getChildNodes().getLength();j++)
	    	{
	    		node1.removeChild(node1.getChildNodes().item(j));
	    	}
	    	
	    }
	    

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				
				Source domSource=new DOMSource(document);
				java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
				 	
				StreamResult streamResult=new StreamResult(baos) ;
				transformer.transform(domSource,streamResult);
				htmlString=baos.toString();

				writeResponse(response,htmlString);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("TESTTEMPLATEDOCUMENT", newDocument);
		}
	}
	

	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("text");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();

		}
	}

	
	
	public static void setColumnProperties(HttpServletRequest request,HttpServletResponse response) 
	{
		//LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: setColumnProperties");
		String tableNo=request.getParameter("tableNo");
		String rowNo=request.getParameter("rowNo");
		String colNo=request.getParameter("colNo");
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		
		Node columnDetailNode=null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		boolean colspanFound=false;
		XPathExpression expr;
		try {
	//		LOGGER_INV.log(Level.INFO,document+" xquery : /testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']/columnDetails[@colNo='"+colNo+"']");
			expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']/columnDetails[@colNo='"+colNo+"']");
			 Object result = expr.evaluate(document, XPathConstants.NODESET);
			 columnDetailNode=((NodeList)result).item(0);
			 NamedNodeMap allAttributeNodeList=columnDetailNode.getAttributes();
			 
			 for(int i=0;i<allAttributeNodeList.getLength();i++)
			 {
				 Node attributeNode =allAttributeNodeList.item(i);
				 if(attributeNode.getNodeName().equalsIgnoreCase("align"))
				 {
	//				 LOGGER_INV.log(Level.INFO," setColumnProperties :: columnAlign");
					 attributeNode.setNodeValue(request.getParameter("columnAlign"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("class"))
				 {
	//				 LOGGER_INV.log(Level.INFO," setColumnProperties :: columnClass");
					 attributeNode.setNodeValue(request.getParameter("columnClass"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("width"))
				 {
	//				 LOGGER_INV.log(Level.INFO," setColumnProperties :: columnWidth");
					 attributeNode.setNodeValue(request.getParameter("columnWidth"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("colNo"))
					{
	//				 LOGGER_INV.log(Level.INFO," setColumnProperties :: columnNo");
					 attributeNode.setNodeValue(request.getParameter("columnNo"));
						
					}
				 else if(attributeNode.getNodeName().equalsIgnoreCase("colspan"))
					{
					 colspanFound=true;
	//				 LOGGER_INV.log(Level.INFO," setColumnProperties :: columnColSpan");
					 attributeNode.setNodeValue(request.getParameter("columnColSpan"));
						
					}
				 
				 else
				 {
					 
				 }
				 
			
				 
			 }
			 
			 if(colspanFound==false)	
			 {
	//			 LOGGER_INV.log(Level.INFO," colspanFound 1 ");
				 Attr colSpanNode=columnDetailNode.getOwnerDocument().createAttribute("colspan");
				 colSpanNode.setNodeValue("1");
				 columnDetailNode.getAttributes().setNamedItem(colSpanNode);
	//			 LOGGER_INV.log(Level.INFO," colspanFound 2 ");
			 }
			
		//	InvestigationStyleSheetConfigurationCacheManager styleSheetCacheManager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
		//	Transformer transformer= styleSheetCacheManager.getTransformerObj(InvestigationConfig.XSL_PRINTINGTEMPLATE);
			 
			
			
			InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
					.getInstance();
			Transformer transformer = manager
					.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);

			
			
			
			   
		    	Source domSource=new DOMSource(document);
		 	 	java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
		 	 	
		 	 	StreamResult streamResult=new StreamResult(baos) ;
		 	 	transformer.transform(domSource,streamResult);
		 	 	String htmlString=baos.toString();
		 	 	writeResponse(response, htmlString);
			 
		} catch (XPathExpressionException e) {
				e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		}

	
	

	public static void setElementProperties(HttpServletRequest request,HttpServletResponse response) {
		//LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: setElementProperties");
		 String tableNo=request.getParameter("tableNo");
		 String rowNo=request.getParameter("rowNo");
		String colNo=request.getParameter("colNo");
		String elementNo=request.getParameter("elementNo");
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		
		Node elementDetailNode=null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		try {
		//	LOGGER_INV.log(Level.INFO,document+" xquery : /testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']/columnDetails[@colNo='"+colNo+"']/table/tr/td/element["+elementNo+"]");
			expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo="+rowNo+"]/columnDetails[@colNo="+colNo+"]/table/tr/td/element["+elementNo+"]");
			 Object result = expr.evaluate(document, XPathConstants.NODESET);
			 elementDetailNode=((NodeList)result).item(0);
			 NamedNodeMap allAttributeNodeList=elementDetailNode.getAttributes();
			 
			 for(int i=0;i<allAttributeNodeList.getLength();i++)
			 {
				 Node attributeNode =allAttributeNodeList.item(i);
		//		 LOGGER_INV.log(Level.INFO,"ReDesignExistingTemplateUTIL::setElementProperties  "+attributeNode.getNodeName());
				 if(attributeNode.getNodeName().equals("idC"))
				 {
		//			 LOGGER_INV.log(Level.INFO,"ReDesignExistingTemplateUTIL::setElementProperties  "+attributeNode.getNodeValue());
					 if(attributeNode.getNodeValue().equals("label"))
					 {
						 setLabelProperties(request,elementDetailNode);
					 }
					 else if(attributeNode.getNodeValue().equals("Select"))
					 {
						 setSelectProperties(request,elementDetailNode);
					 }
					 else if(attributeNode.getNodeValue().equals("input"))
					 {
						 setInputProperties(request,elementDetailNode);
					 }
					 else if(attributeNode.getNodeValue().equals("textArea"))
					 {
						 setTextAreaProperties(request,elementDetailNode);
					 }
					 else if(attributeNode.getNodeValue().equals("voValue"))
					 {
						 setVOValueProperties(request,elementDetailNode);
					 }	
					 
				 }
				 
				 if(attributeNode.getNodeName().equalsIgnoreCase("align"))
				 {
					 attributeNode.setNodeValue(request.getParameter("columnAlign"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("class"))
				 {
					 attributeNode.setNodeValue(request.getParameter("columnClass"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("width"))
				 {
					 attributeNode.setNodeValue(request.getParameter("columnWidth"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("colspan"))
				 {
					 attributeNode.setNodeValue(request.getParameter("columnColSpan"));
				 }
				 else if(attributeNode.getNodeName().equalsIgnoreCase("colNo"))
					{
					 attributeNode.setNodeValue(request.getParameter("columnNo"));
						
					}
				 else
				 {
					 
				 }
				 
			 }
			 
			/* InvestigationStyleSheetConfigurationCacheManager styleSheetCacheManager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
			 Transformer transformer= styleSheetCacheManager.getTransformerObj(InvestigationConfig.XSL_PRINTINGTEMPLATE);
			
			*/	
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);

				   
			Source domSource=new DOMSource(document);
			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			 	 	
			 	 	StreamResult streamResult=new StreamResult(baos) ;
			 	 	transformer.transform(domSource,streamResult);
			 	 	String htmlString=baos.toString();
			 	 	writeResponse(response, htmlString);
				 
			} catch (XPathExpressionException e) {
					e.printStackTrace();
			} catch (TransformerConfigurationException e) {
				
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		
		
	}

	
	
	private static void setVOValueProperties(HttpServletRequest request,Node elementDetailNode) {
	//	LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: setVOValueProperties");
		try
		{
		if(request.getParameter("elementAlign")!=null && (request.getParameter("elementAlign").equals("")==false))
		{
			
			if(elementDetailNode.getAttributes().getNamedItem("align")!=null)
			{
				elementDetailNode.getAttributes().getNamedItem("align").setNodeValue(request.getParameter("elementAlign"));
			}
			else
			{
				((Element)elementDetailNode).setAttribute("align",request.getParameter("elementAlign"));
			}
		}
		
		if(request.getParameter("elementbold")!=null && (request.getParameter("elementbold").equals("")==false))
		{
			if(elementDetailNode.getAttributes().getNamedItem("bold")!=null)
			{
				elementDetailNode.getAttributes().getNamedItem("bold").setNodeValue(request.getParameter("elementbold"));
			}
			else
			{
				((Element)elementDetailNode).setAttribute("bold",request.getParameter("elementbold"));
			}
		
		}
		if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
		{
		
			if(elementDetailNode.getAttributes().getNamedItem("underline")!=null)
			{
				elementDetailNode.getAttributes().getNamedItem("underline").setNodeValue(request.getParameter("elementunderline"));
			}
			else
			{
				((Element)elementDetailNode).setAttribute("underline",request.getParameter("elementunderline"));
			}
		}
						
		if(request.getParameter("elementfontsize")!=null && (request.getParameter("elementfontsize").equals("")==false))
		{
		
			if(elementDetailNode.getAttributes().getNamedItem("fontsize")!=null)
			{
				elementDetailNode.getAttributes().getNamedItem("fontsize").setNodeValue(request.getParameter("elementfontsize"));
			}
			else
			{
				((Element)elementDetailNode).setAttribute("fontsize",request.getParameter("elementfontsize"));
			}
		}
		
	
	
	if(request.getParameter("elementvalue")!=null && (request.getParameter("elementvalue").equals("")==false))
	{
			elementDetailNode.getAttributes().getNamedItem("value").setNodeValue(request.getParameter("elementvalue"));
	}
		
	}
	catch (Exception e) {
			e.printStackTrace();
		}
	}
	



	private static void setTextAreaProperties(HttpServletRequest request,
			Node elementDetailNode) {
	//	LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: setTextAreaProperties");
		try
		{
		//	LOGGER_INV.log(Level.INFO,"setTextAreaProperties");
		if(request.getParameter("elementAlign")!=null && (request.getParameter("elementAlign").equals("")==false))
		{
			if(elementDetailNode.getAttributes().getNamedItem("align")!=null)
				elementDetailNode.getAttributes().getNamedItem("align").setNodeValue(request.getParameter("elementAlign"));
			else
			{
				
				((Element)elementDetailNode).setAttribute("align", request.getParameter("elementAlign"));
			}
		}
		
		if(request.getParameter("elementbold")!=null && (request.getParameter("elementbold").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementbold");
		elementDetailNode.getAttributes().getNamedItem("bold").setNodeValue(request.getParameter("elementbold"));
		}
		if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
		{
	//		LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementunderline");
		elementDetailNode.getAttributes().getNamedItem("underline").setNodeValue(request.getParameter("elementunderline"));
		}
		if(request.getParameter("elementisprintable")!=null && (request.getParameter("elementisprintable").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementisprintable");
		elementDetailNode.getAttributes().getNamedItem("isPrintable").setNodeValue(request.getParameter("elementisprintable"));
		}
		if(request.getParameter("elementdefaultValue")!=null && (request.getParameter("elementdefaultValue").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementdefaultValue");
		elementDetailNode.getAttributes().getNamedItem("defaultValue").setNodeValue(request.getParameter("elementdefaultValue"));
		}
		if(request.getParameter("elementeventName")!=null && (request.getParameter("elementeventName").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementeventName"+request.getParameter("elementeventName"));
		elementDetailNode.getAttributes().getNamedItem("eventName").setNodeValue(request.getParameter("elementeventName"));
		}
		if(request.getParameter("elementValidationString")!=null && (request.getParameter("elementValidationString").equals("")==false))
		{
	//		LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementValidationString");
		elementDetailNode.getAttributes().getNamedItem("eventValidationString").setNodeValue(request.getParameter("elementValidationString"));
		}
		if(request.getParameter("rows")!=null && (request.getParameter("elementrows").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementrows");
		elementDetailNode.getAttributes().getNamedItem("rows").setNodeValue(request.getParameter("elementrows"));
		}
		if(request.getParameter("elementcols")!=null && (request.getParameter("elementcols").equals("")==false))
		{
	//		LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementcols");
		elementDetailNode.getAttributes().getNamedItem("cols").setNodeValue(request.getParameter("elementcols"));
		}
		if(request.getParameter("elementeditor")!=null && (request.getParameter("elementeditor").equals("")==false))
		{
	//		LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementeditor");
		elementDetailNode.getAttributes().getNamedItem("editor").setNodeValue(request.getParameter("elementeditor"));
		}
		if(request.getParameter("elementdefaultvalue")!=null && (request.getParameter("elementdefaultvalue").equals("")==false))
		{
	//		LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementdefaultvalue");
		elementDetailNode.getAttributes().getNamedItem("defaultValue").setNodeValue(request.getParameter("elementdefaultvalue"));
		}
		if(request.getParameter("elementvalue")!=null && (request.getParameter("elementvalue").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setTextAreaProperties :: elementvalue");
			elementDetailNode.getAttributes().getNamedItem("value").setNodeValue(request.getParameter("elementvalue"));
		}
		
		if(request.getParameter("elementfontsize")!=null && (request.getParameter("elementfontsize").equals("")==false))
		{
			if(elementDetailNode.getAttributes().getNamedItem("fontsize")!=null)
				elementDetailNode.getAttributes().getNamedItem("fontsize").setNodeValue(request.getParameter("elementfontsize"));
			else
			{
				
				((Element)elementDetailNode).setAttribute("fontsize", request.getParameter("elementfontsize"));
			}
		}
		
		
		if(request.getParameter("elementfontcolor")!=null && (request.getParameter("elementfontcolor").equals("")==false))
		{
		elementDetailNode.getAttributes().getNamedItem("fontcolor").setNodeValue(request.getParameter("elementfontcolor"));
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	private static void setInputProperties(HttpServletRequest request,
			Node elementDetailNode) {
		try
		{
		//	LOGGER_INV.log(Level.INFO,"RedesignExistingTemplate :: setInputProperties"+request.getParameter("elementAlign"));
		if(request.getParameter("elementAlign")!=null && (request.getParameter("elementAlign").equals("")==false))
		{
			if(elementDetailNode.getAttributes().getNamedItem("align")!=null)
				elementDetailNode.getAttributes().getNamedItem("align").setNodeValue(request.getParameter("elementAlign"));
		else
		{
			
			((Element)elementDetailNode).setAttribute("align", request.getParameter("elementAlign"));
		}
			elementDetailNode.getAttributes().getNamedItem("align").setNodeValue(request.getParameter("elementAlign"));
		}
		if(request.getParameter("elementbold")!=null && (request.getParameter("elementbold").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("bold").setNodeValue(request.getParameter("elementbold"));
		}
		if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("underline").setNodeValue(request.getParameter("elementunderline"));
		}
		if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("isPrintable").setNodeValue(request.getParameter("elementunderline"));
		}
		if(request.getParameter("elementdefaultValue")!=null && (request.getParameter("elementdefaultValue").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("defaultValue").setNodeValue(request.getParameter("elementdefaultValue"));
		}
		if(request.getParameter("elementeventname")!=null && (request.getParameter("elementeventname").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("eventName").setNodeValue(request.getParameter("elementeventname"));
		}
		if(request.getParameter("elementValidationString")!=null && (request.getParameter("elementValidationString").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("eventValidationString").setNodeValue(request.getParameter("elementValidationString"));
		}
		if(request.getParameter("elementmaxLength")!=null && (request.getParameter("elementmaxLength").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("maxlength").setNodeValue(request.getParameter("elementmaxLength"));
		}
		if(request.getParameter("elementsize")!=null && (request.getParameter("elementsize").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("size").setNodeValue(request.getParameter("elementsize"));
		}
		
		if(request.getParameter("elementvalue")!=null && (request.getParameter("elementvalue").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("value").setNodeValue(request.getParameter("elementvalue"));
		}
		
		if(request.getParameter("elementfontsize")!=null && (request.getParameter("elementfontsize").equals("")==false))
		{
			if(elementDetailNode.getAttributes().getNamedItem("fontsize")!=null)
					elementDetailNode.getAttributes().getNamedItem("fontsize").setNodeValue(request.getParameter("elementfontsize"));
			else
			{
				
				((Element)elementDetailNode).setAttribute("fontsize", request.getParameter("elementfontsize"));
			}
		}
		
		
		if(request.getParameter("elementfontcolor")!=null && (request.getParameter("elementfontcolor").equals("")==false))
		{
		elementDetailNode.getAttributes().getNamedItem("fontcolor").setNodeValue(request.getParameter("elementfontcolor"));
		}
		
		if(request.getParameter("elementchangeTableId")!=null && (request.getParameter("elementchangeTableId").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("changeTableId").setNodeValue(request.getParameter("elementchangeTableId"));
		}
		
		
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void setSelectProperties(HttpServletRequest request,Node elementDetailNode) {
//		LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: setSelectProperties");
		try
		{
			if(request.getParameter("elementAlign")!=null && (request.getParameter("elementAlign").equals("")==false))
			{
//				LOGGER_INV.log(Level.INFO,"setting elementAlign");
				elementDetailNode.getAttributes().getNamedItem("align").setNodeValue(request.getParameter("elementAlign"));
			}
		if(request.getParameter("elementbold")!=null && (request.getParameter("elementbold").equals("")==false))
		{
	//		LOGGER_INV.log(Level.INFO,"setting elementbold");
		elementDetailNode.getAttributes().getNamedItem("bold").setNodeValue(request.getParameter("elementbold"));
		}
		if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setting elementunderline");
		elementDetailNode.getAttributes().getNamedItem("underline").setNodeValue(request.getParameter("elementunderline"));
		}
		if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setting elementunderline");
		elementDetailNode.getAttributes().getNamedItem("isPrintable").setNodeValue(request.getParameter("elementunderline"));
		}
		if(request.getParameter("elementdefaultValue")!=null && (request.getParameter("elementdefaultValue").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setting elementdefaultValue");
		elementDetailNode.getAttributes().getNamedItem("defaultValue").setNodeValue(request.getParameter("elementdefaultValue"));
		}
		if(request.getParameter("elementeventName")!=null && (request.getParameter("elementeventName").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setting elementeventname");
		elementDetailNode.getAttributes().getNamedItem("eventName").setNodeValue(request.getParameter("elementeventName"));
		}
		if(request.getParameter("elementValidationString")!=null && (request.getParameter("elementValidationString").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setting elementValidationString");
		elementDetailNode.getAttributes().getNamedItem("eventValidationString").setNodeValue(request.getParameter("elementValidationString"));
		}
		if(request.getParameter("elementvalue")!=null && (request.getParameter("elementvalue").equals("")==false))
		{
//			LOGGER_INV.log(Level.INFO,"setting elementvalue");
			elementDetailNode.getAttributes().getNamedItem("value").setNodeValue(request.getParameter("elementvalue"));
		}
		
		if(request.getParameter("elementfontsize")!=null && (request.getParameter("elementfontsize").equals("")==false))
		{
			if(elementDetailNode.getAttributes().getNamedItem("fontsize")!=null)
				elementDetailNode.getAttributes().getNamedItem("fontsize").setNodeValue(request.getParameter("elementfontsize"));
			else
			{
				
				((Element)elementDetailNode).setAttribute("fontsize", request.getParameter("elementfontsize"));
			}
		}
		
		
		if(request.getParameter("elementfontcolor")!=null && (request.getParameter("elementfontcolor").equals("")==false))
		{
		elementDetailNode.getAttributes().getNamedItem("fontcolor").setNodeValue(request.getParameter("elementfontcolor"));
		}
		
		
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setLabelProperties(HttpServletRequest request,
			Node elementDetailNode) {
	//	LOGGER_INV.log(Level.INFO,"RedesignExistingTemplateUTIL:: setLabelProperties");
		try
		{
		if(request.getParameter("elementAlign")!=null && (request.getParameter("elementAlign").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("align").setNodeValue(request.getParameter("elementAlign"));
		}
	if(request.getParameter("elementbold")!=null && (request.getParameter("elementbold").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("bold").setNodeValue(request.getParameter("elementbold"));
	}
	if(request.getParameter("elementunderline")!=null && (request.getParameter("elementunderline").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("underline").setNodeValue(request.getParameter("elementunderline"));
	}
	if(request.getParameter("elementIsPrintable")!=null && (request.getParameter("elementIsPrintable").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("isPrintable").setNodeValue(request.getParameter("elementIsPrintable"));
	}
	if(request.getParameter("elementdefaultValue")!=null && (request.getParameter("elementdefaultValue").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("defaultValue").setNodeValue(request.getParameter("elementdefaultValue"));
	}
	if(request.getParameter("elementeventname")!=null && (request.getParameter("elementeventname").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("eventName").setNodeValue(request.getParameter("elementeventname"));
	}
	
	if(request.getParameter("elementfontsize")!=null && (request.getParameter("elementfontsize").equals("")==false))
	{
		if(elementDetailNode.getAttributes().getNamedItem("fontsize")==null)
			((Element)elementDetailNode).setAttribute("fontsize", request.getParameter("elementfontsize"));
			
	elementDetailNode.getAttributes().getNamedItem("fontsize").setNodeValue(request.getParameter("elementfontsize"));
	}
	
	
	if(request.getParameter("elementfontcolor")!=null && (request.getParameter("elementfontcolor").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("fontcolor").setNodeValue(request.getParameter("elementfontcolor"));
	}
	
	
	if(request.getParameter("elementValidationString")!=null && (request.getParameter("elementValidationString").equals("")==false))
	{
	elementDetailNode.getAttributes().getNamedItem("eventValidationString").setNodeValue(request.getParameter("elementValidationString"));
	}
		if(request.getParameter("elementvalue")!=null && (request.getParameter("elementvalue").equals("")==false))
		{
			elementDetailNode.getAttributes().getNamedItem("value").setNodeValue(request.getParameter("elementvalue"));
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteBlankCellsRows(HttpServletRequest request,HttpServletResponse response) 
	{
		//LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::deleteBlankCellsRows");
		//InvestigationStyleSheetConfigurationCacheManager manager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
		String tableNo=request.getParameter("tableNo");
		 String rowNo=request.getParameter("rowNo");
		//javax.xml.transform.Transformer transformer=null;
		
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"node 1: /testtemplate/table[@type='"+fb.getTableNo()+"']");
		
		try
		{
			
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    
	    NodeList nodes = (NodeList) result;
	  //  LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength());
	    for(int i=0;i<nodes.getLength();i++)
	    {
	    	Node rowNode=nodes.item(i);
	    	/*boolean checkDeletable = findIfElementExistsInTheRow(tableNo,rowNo,document);
	    	if(checkDeletable)*/
	    		rowNode.getParentNode().removeChild(rowNode);
	     }
	    
	        updateRowsOFTable(tableNo,document);
	    
	    
	    
	  
    
    
    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
				
				    Source domSource=new DOMSource(document);
			 	 	java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			 	 	StreamResult streamResult=new StreamResult(baos) ;
			 	 	transformer.transform(domSource,streamResult);
			 	 	String htmlString=baos.toString();
			 	 	writeResponse(response,htmlString);
			 	 	//baos.toString();
			} catch (Exception e2) {
			e2.printStackTrace();
			}
			
		}
		
		
		
	}
	
	
	private void updateRowsOFTable(String tableNo, Document document) throws XPathExpressionException {
//		LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::updateRowsOFTable");
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	   
	    NodeList nodes = (NodeList) result;
	//    LOGGER_INV.log(Level.INFO,"updateRowsOFTable: Row node 1: "+nodes.getLength());
	    for(int i=0;i<nodes.getLength();i++)
	    {
	    	Node tableNode=nodes.item(i);
	    	for(int k=0;k<tableNode.getChildNodes().getLength();k++)
	    	{
	    		Node rowNode=tableNode.getChildNodes().item(k);
	    	rowNode.getAttributes().getNamedItem("rowNo").setNodeValue(""+(k+1));
	    	}
	    }
		
	}
	
	private boolean findIfElementExistsInTheRow(String tableNo, String rowNo,
			  Document document) throws XPathExpressionException {
		//LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::findIfElementExistsInTheRow");
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']/rowDetails[@rowNo='"+rowNo+"']/columnDetails/table/tr/td/element[@idC!='']");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    
	    NodeList nodes = (NodeList) result;
	   // LOGGER_INV.log(Level.INFO,"updateRowsOFTable: Row node 1: "+nodes.getLength());
	    if(nodes.getLength()!=0)
	    {
	    	return false;
	    }
	    else
	    {
	    	return true;	
	    }
		
	}
	

	
	public void addBlankCellsRows(HttpServletRequest request,HttpServletResponse response) {
		//LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::addBlankCellsRows");
		//InvestigationStyleSheetConfigurationCacheManager manager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
		
		String htmlString=null;
		String rowNo=request.getParameter("rowNo");
		String tableNo=request.getParameter("tableNo");
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"node 1: /testtemplate/table[@type='"+tableNo+"']");
		
		boolean rowAdded=false;
		
		try
		{
		
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	  //  LOGGER_INV.log(Level.INFO,"addBlankCellsRows node 1: "+nodes.getLength());
	    for(int i=0;i<nodes.getLength();i++)
	    {
	    	Node tableNode=nodes.item(i);
	    	int maxColumns=Integer.parseInt(tableNode.getAttributes().getNamedItem("maxColumns").getNodeValue());
	    	int columnWidth=100/maxColumns; 	
	    	NodeList rowNodes=tableNode.getChildNodes();
	    	for(int k=0;k<rowNodes.getLength();k++)
	    	{
	    	Node currentrowNode=rowNodes.item(k);
	    	if((k+1)==(Integer.parseInt(rowNo)+1))
	    	{
	    	//	LOGGER_INV.log(Level.INFO,(k+1)+" = Row No := "+currentrowNode.getAttributes().getNamedItem("rowNo").getNodeValue());
	    		Element newRowNode=document.createElement("rowDetails");
	    		newRowNode.setAttribute("rowNo", ""+(Integer.parseInt(rowNo)+1));
	    		tableNode.insertBefore(newRowNode,currentrowNode);
	    		rowAdded=true;
	    		for(int j=0;j<maxColumns;j++)
	    		{
	    		Element columnDetailsElement=document.createElement("columnDetails");//column Element
    			columnDetailsElement.setAttribute("colNo", ""+(j+1));
    			columnDetailsElement.setAttribute("class", ((j+1)%2==0)?"tdfonthead":"tdfont");
    			columnDetailsElement.setAttribute("width", ""+columnWidth+"%");
    			columnDetailsElement.setAttribute("align", "center");
    			Element tableElement=document.createElement("table");
    			columnDetailsElement.appendChild(tableElement);
    			tableElement.setAttribute("width","100%");
    			tableElement.setAttribute("cellspacing","0");
    			tableElement.setAttribute("cellpadding","0");
    			tableElement.setAttribute("border","0");
    			Element rowElement=document.createElement("tr");
    			tableElement.appendChild(rowElement);
    			Element columnElement=document.createElement("td");
    			rowElement.appendChild(columnElement);
    			Element elementElement=document.createElement("element");//element Element
    			elementElement.setAttribute("id", "");
    			elementElement.setAttribute("name","");
    			elementElement.setAttribute("type", "");
    			elementElement.setAttribute("align", "");
    			elementElement.setAttribute("idC","");
    			elementElement.setAttribute("value","") ;
    			columnElement.appendChild(elementElement);
    			newRowNode.appendChild(columnDetailsElement);
	    		}
	    		
	    		
	    	}
	    	else if((k+1)>(Integer.parseInt(rowNo)+1))
	    	{
	    	//	LOGGER_INV.log(Level.INFO,(k+1)+" > Row No := "+currentrowNode.getAttributes().getNamedItem("rowNo").getNodeValue());
	    		currentrowNode.getAttributes().getNamedItem("rowNo").setNodeValue(""+(Integer.parseInt(currentrowNode.getAttributes().getNamedItem("rowNo").getNodeValue())+1));
	    	}
	    		
	    
	   
	    	
	    	
	    }
	    	
	    	
	    }
	    
	    if(rowAdded==false)
	    	  	{
		    	Element newRowNode=document.createElement("rowDetails");
	    		newRowNode.setAttribute("rowNo", ""+(Integer.parseInt(rowNo)+1));
	    		int maxColumns=Integer.parseInt(nodes.item(0).getAttributes().getNamedItem("maxColumns").getNodeValue());
		    	int columnWidth=100/maxColumns; 
	    		nodes.item(0).appendChild(newRowNode);
	    		for(int j=0;j<maxColumns;j++)
	    		{
	    		Element columnDetailsElement=document.createElement("columnDetails");//column Element
				columnDetailsElement.setAttribute("colNo", ""+(j+1));
				columnDetailsElement.setAttribute("class", ((j+1)%2==0)?"tdfonthead":"tdfont");
				columnDetailsElement.setAttribute("width", ""+columnWidth+"%");
				columnDetailsElement.setAttribute("align", "center");
				Element tableElement=document.createElement("table");
				columnDetailsElement.appendChild(tableElement);
				tableElement.setAttribute("width","100%");
				tableElement.setAttribute("cellspacing","0");
				tableElement.setAttribute("cellpadding","0");
				tableElement.setAttribute("border","0");
				Element rowElement=document.createElement("tr");
				tableElement.appendChild(rowElement);
				Element columnElement=document.createElement("td");
				rowElement.appendChild(columnElement);
				Element elementElement=document.createElement("element");//element Element
				elementElement.setAttribute("id", "");
				elementElement.setAttribute("name","");
				elementElement.setAttribute("type", "");
				elementElement.setAttribute("align", "");
				elementElement.setAttribute("idC","");
				elementElement.setAttribute("value","") ;
				columnElement.appendChild(elementElement);
				newRowNode.appendChild(columnDetailsElement);
	    		}
		    	}
	    
	   
    
    
    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
				    
				    
			    	Source domSource=new DOMSource(document);
			 	 	java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
			 	 	
			 	 	StreamResult streamResult=new StreamResult(baos) ;
			 	 	transformer.transform(domSource,streamResult);
			 	 	htmlString=baos.toString();	
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				writeResponse(response,htmlString);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}
	

	
	public void addColumnsToEachRow(HttpServletRequest request,HttpServletResponse response){
		//LOGGER_INV.log(Level.INFO,"addColumnsToEachRow");
		//InvestigationStyleSheetConfigurationCacheManager manager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
	 
		//javax.xml.transform.Transformer transformer=null;
		String htmlString=null;
		
		String colNo=request.getParameter("colNo");
		//int ColNo=Integer.parseInt(colno)-1;
		//String colNo=Integer.toString(ColNo);
		String tableNo=request.getParameter("tableNo");
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"node 1: /testtemplate/table[@type='"+tableNo+"']");
		try
		{
		
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	  //  LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength() +"  colNo :"+Integer.parseInt(colNo));
	    for(int i=0;i<nodes.getLength();i++)
	    {
	    	Node tableNode=nodes.item(i);
	    	int maxColumns=Integer.parseInt(tableNode.getAttributes().getNamedItem("maxColumns").getNodeValue());
	    	
	    	maxColumns++;
	    //	LOGGER_INV.log(Level.INFO,"max columns ::"+maxColumns);
	    	int columnWidth=100/maxColumns;
	    	
	    	NodeList rowNodes=tableNode.getChildNodes();
	    //	LOGGER_INV.log(Level.INFO,"rowNodes "+rowNodes.getLength());
	    	for(int k=0;k<rowNodes.getLength();k++)
	    	{
	    	
	    	Node rowNode=rowNodes.item(k);
	    	NodeList rowColumnNodeList=rowNode.getChildNodes();
	    	//LOGGER_INV.log(Level.INFO,"rowColumnNodeList "+rowColumnNodeList.getLength());
	    	for(int j=0;j<rowColumnNodeList.getLength()+1;j++)
	    	{
	    		Node columnNode=rowColumnNodeList.item(j);
	    		
	    		
	    		if((j)>(Integer.parseInt(colNo)))
	    		{
	    			if(columnNode!=null)
	    			{
	    		//	LOGGER_INV.log(Level.INFO,j+" > column No := "+columnNode.getAttributes().getNamedItem("colNo").getNodeValue());
	    			columnNode.getAttributes().getNamedItem("colNo").setNodeValue(""+(Integer.parseInt(columnNode.getAttributes().getNamedItem("colNo").getNodeValue())+1));
	    			columnNode.getAttributes().getNamedItem("width").setNodeValue(""+columnWidth+"%");
	    			}
	    			}
	    		else if((j)==(Integer.parseInt(colNo)))
	    		{
	    		//	LOGGER_INV.log(Level.INFO,j+" = column No := "+columnNode.getAttributes().getNamedItem("colNo").getNodeValue());
	    			Element columnDetailsElement=document.createElement("columnDetails");//column Element
	    			columnDetailsElement.setAttribute("colNo", ""+(j+1));
	    			columnDetailsElement.setAttribute("class", ((j+1)%2==0)?"tdfonthead":"tdfont");
	    			columnDetailsElement.setAttribute("width", ""+columnWidth+"%");
	    			columnDetailsElement.setAttribute("align", "center");
	    			Element tableElement=document.createElement("table");
	    			columnDetailsElement.appendChild(tableElement);
	    			tableElement.setAttribute("width","100%");
	    			tableElement.setAttribute("cellspacing","0");
	    			tableElement.setAttribute("cellpadding","0");
	    			tableElement.setAttribute("border","0");
	    			Element rowElement=document.createElement("tr");
	    			tableElement.appendChild(rowElement);
	    			Element columnElement=document.createElement("td");
	    			rowElement.appendChild(columnElement);
	    			Element elementElement=document.createElement("element");//element Element
	    			elementElement.setAttribute("id", "");
	    			elementElement.setAttribute("name","");
	    			elementElement.setAttribute("type", "");
	    			elementElement.setAttribute("align", "");
	    			elementElement.setAttribute("idC","");
	    			elementElement.setAttribute("value","") ;
	    			columnElement.appendChild(elementElement);
	    			rowNode.insertBefore(columnDetailsElement, columnNode);
	    				    			
	    			
	    		}
	    		else
	    		{
	    			if(columnNode!=null)
	    			{
	    	//		LOGGER_INV.log(Level.INFO,j+" = column No := "+columnNode.getAttributes().getNamedItem("colNo").getNodeValue());
	    			columnNode.getAttributes().getNamedItem("width").setNodeValue(""+columnWidth+"%");
	    		}}
	    		
	    	}
	    	
	    	
	    	
	    }
	    	tableNode.getAttributes().getNamedItem("maxColumns").setNodeValue(""+maxColumns);	
	    	
	    }
	    
	    
    
    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
			    
			    
		    	Source domSource=new DOMSource(document);
		 	 	java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
		 	 	
		 	 	StreamResult streamResult=new StreamResult(baos) ;
		 	 	transformer.transform(domSource,streamResult);
		 	 	htmlString=baos.toString();
		    
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				writeResponse(response, htmlString);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
				
	}

	
	
	
	public void removeColumnsToEachRow(HttpServletRequest request,HttpServletResponse response){
		//LOGGER_INV.log(Level.INFO,"addColumnsToEachRow");
		//InvestigationStyleSheetConfigurationCacheManager manager=InvestigationStyleSheetConfigurationCacheManager.getInstance();
	 
		//javax.xml.transform.Transformer transformer=null;
		String htmlString=null;
		
		String colno=request.getParameter("colNo");
		int ColNo=Integer.parseInt(colno)-1;
		String colNo=Integer.toString(ColNo);
		String tableNo=request.getParameter("tableNo");
		Document document =null;
		String requesttemplateType=request.getParameter("templateType");
		if(requesttemplateType==null)
		{
			document= (Document)request.getSession().getAttribute("TESTTEMPLATEDOCUMENT");
		}
		else
		{
			document= (Document)request.getSession().getAttribute("MODIFYINGNODE");
		}
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"node 1: /testtemplate/table[@type='"+tableNo+"']");
		try
		{
		
		XPathExpression expr = xpath.compile("/testtemplate/table[@type='"+tableNo+"']");
	    Object result = expr.evaluate(document, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	  //  LOGGER_INV.log(Level.INFO,"node 1: "+nodes.getLength() +"  colNo :"+Integer.parseInt(colNo));
	    for(int i=0;i<nodes.getLength();i++)
	    {
	    	Node tableNode=nodes.item(i);
	    	int maxColumns=Integer.parseInt(tableNode.getAttributes().getNamedItem("maxColumns").getNodeValue());
	    	
	    	maxColumns++;
	    //	LOGGER_INV.log(Level.INFO,"max columns ::"+maxColumns);
	    	int columnWidth=100/maxColumns;
	    	
	    	NodeList rowNodes=tableNode.getChildNodes();
	    //	LOGGER_INV.log(Level.INFO,"rowNodes "+rowNodes.getLength());
	    	for(int k=0;k<rowNodes.getLength();k++)
	    	{
	    	
	    	Node rowNode=rowNodes.item(k);
	    	NodeList rowColumnNodeList=rowNode.getChildNodes();
	    	//LOGGER_INV.log(Level.INFO,"rowColumnNodeList "+rowColumnNodeList.getLength());
	    	for(int j=0;j<rowColumnNodeList.getLength();j++)
	    	{
	    		Node columnNode=rowColumnNodeList.item(j);
	    		
	    		
	    		if((j)>(Integer.parseInt(colNo)))
	    		{
	    		//	LOGGER_INV.log(Level.INFO,j+" > column No := "+columnNode.getAttributes().getNamedItem("colNo").getNodeValue());
	    			columnNode.getAttributes().getNamedItem("colNo").setNodeValue(""+(Integer.parseInt(columnNode.getAttributes().getNamedItem("colNo").getNodeValue())+1));
	    			columnNode.getAttributes().getNamedItem("width").setNodeValue(""+columnWidth+"%");
	    		}
	    		else if((j)==(Integer.parseInt(colNo)))
	    		{
	    		//	LOGGER_INV.log(Level.INFO,j+" = column No := "+columnNode.getAttributes().getNamedItem("colNo").getNodeValue());
	    			Element columnDetailsElement=document.createElement("columnDetails");//column Element
	    			columnDetailsElement.setAttribute("colNo", ""+(j+1));
	    			columnDetailsElement.setAttribute("class", ((j+1)%2==0)?"tdfonthead":"tdfont");
	    			columnDetailsElement.setAttribute("width", ""+columnWidth+"%");
	    			columnDetailsElement.setAttribute("align", "center");
	    			Element tableElement=document.createElement("table");
	    			columnDetailsElement.appendChild(tableElement);
	    			tableElement.setAttribute("width","100%");
	    			tableElement.setAttribute("cellspacing","0");
	    			tableElement.setAttribute("cellpadding","0");
	    			tableElement.setAttribute("border","0");
	    			Element rowElement=document.createElement("tr");
	    			tableElement.appendChild(rowElement);
	    			Element columnElement=document.createElement("td");
	    			rowElement.appendChild(columnElement);
	    			Element elementElement=document.createElement("element");//element Element
	    			elementElement.setAttribute("id", "");
	    			elementElement.setAttribute("name","");
	    			elementElement.setAttribute("type", "");
	    			elementElement.setAttribute("align", "");
	    			elementElement.setAttribute("idC","");
	    			elementElement.setAttribute("value","") ;
	    			columnElement.appendChild(elementElement);
	    			//rowNode.insertBefore(columnDetailsElement, columnNode);
	    			rowNode.removeChild(columnNode) ;			
	    			
	    		}
	    		else
	    		{
	    	//		LOGGER_INV.log(Level.INFO,j+" = column No := "+columnNode.getAttributes().getNamedItem("colNo").getNodeValue());
	    			columnNode.getAttributes().getNamedItem("width").setNodeValue(""+columnWidth+"%");
	    		}
	    		
	    	}
	    	
	    	
	    	
	    }
	    	tableNode.getAttributes().getNamedItem("maxColumns").setNodeValue(""+maxColumns);	
	    	
	    }
	    
	    
    
    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				
				InvestigationTemplateDataHelper manager = InvestigationTemplateDataHelper
						.getInstance();
				Transformer transformer = manager
						.getTransformerObject(InvestigationConfig.XSL_PRINTINGTEMPLATE);
			    
			    
		    	Source domSource=new DOMSource(document);
		 	 	java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
		 	 	
		 	 	StreamResult streamResult=new StreamResult(baos) ;
		 	 	transformer.transform(domSource,streamResult);
		 	 	htmlString=baos.toString();
		    
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				writeResponse(response, htmlString);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}
		
				
	}

	
	
}
