package new_investigation.masters.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.controller.utl.DynamicReportTemplateUTIL;

public class CreateNewPrintingTemplate extends HttpServlet {



	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hmode=request.getParameter("hmode");
		if(hmode==null)hmode="";
		
		//LOGGER_INV.log(Level.INFO,"CreateNewPrintingTemplate  "+hmode);
//		if(hmode.equals("GETTESTNTESTGROUPDATA"))
//		{
//			String testCode=request.getParameter("testCode");
//			String mode="ADDTESTORTESTGROUP";
//			char flag=testCode.charAt(9);
//			//LOGGER_INV.log(Level.INFO,"flag="+flag);
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			if(flag=='S')
//			{
//			//LOGGER_INV.log(Level.INFO,"when flag is S"+testCode);
//			ntcUtil.getTestParameterDtl(testCode.substring(5,9),testCode.substring(0,5),request,response,mode);
//			}
//			else
//			{
//			//LOGGER_INV.log(Level.INFO,"when flag is G"+testCode);
//			ntcUtil.getTestGroupParameterDtl(testCode,request,response,mode);
//			
//			}
//		}
//		else if(hmode.equalsIgnoreCase("NEWTEMPLATE"))
		if(hmode.equalsIgnoreCase("NEWTEMPLATE"))
		{
			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
			ntcUtil.newTemplateCreation(request,response);
			
		}
		else if(hmode.equalsIgnoreCase("CREATETABLE"))
		{
			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
			ntcUtil.createNewTable(request,response);
			
		}
//		else if(hmode.equalsIgnoreCase("GETELEMENTDETAILS"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.getElementDetails(request,response);
//			
//		}
//		else if(hmode.equalsIgnoreCase("POPULATEDATAINTOTABLEFORLABEL"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.populateDataIntoTableForLabel(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("POPULATEDATAINTOTABLEFORELEMENT"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.populateDataIntoTableForElement(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("MERGECELLS"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.mergeCells(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("EXCHANGECELLS"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.exchangeCells(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("DELETECELLS"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.deleteCells(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("DELETEBLANKROW"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.deleteRow(request, response);
//		}
//		else if(hmode.equalsIgnoreCase("EXCHANGEROWS"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.exchangeRows(request, response);
//		}
//		else if(hmode.equalsIgnoreCase("ADDROWAFTERROWNO"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.addBlankCellsRows(request, response);
//		}
//		else if(hmode.equalsIgnoreCase("ADDCOLUMNTOEACHROWAFTERCOLUMNNO"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.addColumnsToEachRow(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("ACCEPTCHANGE"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.acceptChangeForDocument(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("MODIFYTABLE"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.modifyTable(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("DELETETABLE"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.deleteTable(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("WRITELABEL"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.writeLabel(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("ADDHR"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.addHR(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("DELETELABEL"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.deleteLabel(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("ADDHEADERTOTABLE"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.addHeaderToTable(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("ADDFOOTERTOTABLE"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.addFooterToTable(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("ADDVOVALUES"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.addVOValues(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("GETTEMPLATELIST"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.getTemplateList(request,response);
//		//	ntcUtil.existingTemplateList(request, response);
//		}
//		else if(hmode.equalsIgnoreCase("SHOWMODIFICATIONPROCESS"))
//		{
//			request.getSession().removeAttribute("TESTGROUPTREELISTVO");
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			
//			ntcUtil.initModificationProcess(request,response);
//			String strTree=ntcUtil.writingResponse(request, response);
//			
//			strTree+="#$#"+ntcUtil.getTestDocumentForTemplateSequenceId(request.getParameter("templateIdRadio"), request);			
//			DynamicReportTemplateUTIL.writeResponse(response, strTree);
//			//ntcUtil.showModificationProcess(request,response);
//			
//		}
//		else if(hmode.equalsIgnoreCase("REPLACEMENTPROCESS"))
//		{
//			
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			
//			String strTree=ntcUtil.getTestDocumentForTemplateSequenceIdforviewing(request.getParameter("templateIdRadio"), request);			
//			DynamicReportTemplateUTIL.writeResponse(response, strTree);
//			//ntcUtil.showModificationProcess(request,response);
//			
//		}
//		else if(hmode.equalsIgnoreCase("REPLACEMENTPROCESSNEWDATA"))
//		{
//			
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			
//			String strTree="#$#"+ntcUtil.getReplaceTemplate(request);			
//			DynamicReportTemplateUTIL.writeResponse(response, strTree);
//			//ntcUtil.showModificationProcess(request,response);
//			
//		}
//		else if(hmode.equalsIgnoreCase("DELETIONOFTEMPLATE"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			String strTree=ntcUtil.deleteTemplate(request);
//			DynamicReportTemplateUTIL.writeResponse(response, strTree);
//			//ntcUtil.showModificationProcess(request,response);
//			
//		}
//		else if(hmode.equalsIgnoreCase("SHOWMODIFICATIONPARAMETER"))
//		{
//			String labTestCode=request.getParameter("labTestCode");
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			char flag=labTestCode.charAt(9);
//			
//			
//				
//			ntcUtil.getSelectedLabTestParameterDtl(labTestCode.substring(5,9),labTestCode.substring(0,5),flag, request, response);
//				
//			
//			/*else if(flag=='G')
//			{
//				//ntcUtil.getTestGroupParameterDtl(labTestCode, request, response,mode);
//			}	*/
//			
//		}
//		else if(hmode.equalsIgnoreCase("SAVEMODIFY"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.saveModifiedData(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("DELETEElEMENT"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.deleteElementFromDocument(request,response);
//		}
//		else if(hmode.equalsIgnoreCase("GETLABTESTWITHTESTGROUPDATA"))
//		{
//			
//			String testCode=request.getParameter("testCode");
//			//LOGGER_INV.log(Level.INFO,"when flag is S  "+testCode);
//			String testGroupName=request.getParameter("testGroupName");
//		//	String mode="GETLABTESTWITHTESTGROUPDATA";
//			char flag=testCode.charAt(9);
//			//LOGGER_INV.log(Level.INFO,"flag="+flag);
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			
//				
//					if(flag=='S')
//					{
//						//LOGGER_INV.log(Level.INFO,"when flag is S"+testCode);
//						ntcUtil.generateLabtestTree(testCode,request,response,testGroupName);
//					}
//					else
//					{
//					//LOGGER_INV.log(Level.INFO,"when flag is G"+testCode);
//						ntcUtil.generateLabtestGroupTree(testCode,request,response,testGroupName);
//					}
//				
//			
//			
//			
//			
//			
//		}
//		else if(hmode.equalsIgnoreCase("ADDRANGEELEMENT"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.AddRangeElement(request, response);
//		}
//		else if(hmode.equalsIgnoreCase("TABLECOLUMNMODIFICATION"))
//		{
//			DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
//			ntcUtil.tableColumnModification(request, response);
//		}
//		else if(hmode.equalsIgnoreCase("REMOVERANGEELEMENT"))
//		{
//			
//		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			this.doGet(req, resp);
	}
}
