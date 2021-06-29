package new_investigation.masters.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import new_investigation.masters.controller.fb.DynamicReportTemplateFB;
import new_investigation.masters.controller.fb.TestParameterMstFB;
import new_investigation.masters.controller.utl.DynamicReportTemplateUTIL;
import new_investigation.masters.controller.utl.TestParameterMstUTIL;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import hisglobal.masterutil.GenericController;
import hisglobal.presentation.WebUTIL;

public class DynamicReportTemplateACT extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		
		return this.INTIALIZEREDESIGNTEMPLATE(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{

		WebUTIL.refreshTransState(request);
		 
		return mapping.findForward("NEW");
	}
	public ActionForward INTIALIZEREDESIGNTEMPLATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().removeAttribute("TESTTEMPLATEDOCUMENT");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
		util.getLaboratoryTestGroup(request,fb);
		util.initializeTemplateProcessForVO(request,response);//to generate resultEntryGroupValidationByVO combo values
		util.existingTemplateList(request, response);
		fb.setParaType("0");
		return mapping.findForward("ADD");
	}
	
	public ActionForward NEWTEMPLATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.newTemplateCreation(request,response);
		return mapping.findForward("ADD");
		//return null;
	
	}
	public ActionForward CREATETABLE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.createNewTable(request,response);
		//return mapping.findForward("ADD");
		return null;
	}
	
	public ActionForward ACCEPTCHANGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.acceptChangeForDocument(request,response);
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVEMODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("fgfg");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		String s = fb.getHeaderHeight();
		System.out.println(s);
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.saveModifiedData(request,response);
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETLABTESTWITHTESTGROUPDATA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.generateLabtestTree(request,response);
		String s = fb.getTestCode();
		System.out.println(s);
		return null;
	}
	
	public ActionForward SHOWMODIFICATIONPARAMETER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.getSelectedLabTestParameterDtl(request, response);
		return null;
	}
	
	public ActionForward POPULATEDATAINTOTABLEFORELEMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.populateDataIntoTableForElement(request, response);
		return null;
	}
	
	public ActionForward WRITELABEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.writeLabel(request, response);
		return null;
	}
	
	public ActionForward DELETETABLE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.deleteTable(request, response);
		return null;
	}
	
	public ActionForward SHOWTABLEWITHBORDERS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.showtablewithBorder(request, response);
		return null;
	}
	
	public ActionForward SHOWTABLEWITHOUTBORDERS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.showtablewithoutBorder(request, response);
		return null;
	}
	
	
	public ActionForward DELETELABEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.deleteLabel(request, response);
		return null;
	}
	
	public ActionForward DELETEElEMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.deleteElementFromDocument(request,response);
		return null;
	}
	
	public ActionForward ADDRANGEELEMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.AddRangeElement(request, response);
		return null;
		
	}
	
	public ActionForward REDESIGNTEMPLATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		ntcUtil.redesignTemplateAjax(request, response);
		return null;
	}
	
	public ActionForward SHOWMODIFICATIONPROCESS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		request.getSession().removeAttribute("TESTGROUPTREELISTVO");
		DynamicReportTemplateUTIL ntcUtil=new DynamicReportTemplateUTIL();
		
		ntcUtil.initModificationProcess(request,response);
		
		return null;
	}
	
	public ActionForward DELETEBLANKROW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::DELETEBLANKROW");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
		util.deleteBlankCellsRows(request,response);
		return null;
	}
	
	
	public ActionForward ADDROWAFTERROWNO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::ADDROWAFTERROWNO");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
		util.addBlankCellsRows(request,response);
		return null;
	}
	
	public ActionForward ADDCOLUMNTOEACHROWAFTERCOLUMNNO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::ADDCOLUMNTOEACHROWAFTERCOLUMNNO");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
		util.addColumnsToEachRow(request,response);
		return null;
	}
//	public ActionForward LABORATORYTESTGROUPLIST(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::LABORATORYTESTGROUPLIST");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		util.getLaboratoryTestGroup(request,fb);
//		return mapping.findForward("ADD");
//	}
//	
//	public ActionForward GETTEMPLATEFORSELECTEDTEST(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::GETTEMPLATEFORSELECTEDTEST");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		util.getTemplateForSelectedTest(request,fb);
//		return mapping.findForward("ADD");
//	}
//	public ActionForward MERGECELLS(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::MERGECELLS");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		//util.mergeCells(request,fb);
//		return mapping.findForward("ADD");
//	}
//	
//	public ActionForward EXCHANGECELLS(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::EXCHANGECELLS");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		//util.exchangeCells(request,fb);
//		return mapping.findForward("ADD");
//	}
//	public ActionForward EXCHANGEROWS(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::EXCHANGEROWS");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		//util.exchangeRows(request, fb);
//		return mapping.findForward("ADD");
//	}
//	
	public ActionForward DELETEBLANKCELL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::DELETEBLANKCELL");
		//CreateNewTemplateForTestGroupFB fb=(CreateNewTemplateForTestGroupFB)form;
		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
		util.removeColumnsToEachRow(request,response);
		return null;
	}
	/*public ActionForward DELETEBLANKROW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::DELETEBLANKROW");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
		util.deleteBlankCellsRows(request, fb,response);
		return mapping.findForward("ADD");
	}*/
//	
	
//	
//	public ActionForward ADDROWAFTERROWNO(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::ADDROWAFTERROWNO");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		//util.addBlankCellsRows(request, fb);
//		return mapping.findForward("ADD");
//	}
//	
	public void SETCOLUMNPROPERTIES(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::SETCOLUMNPROPERTIES");
		DynamicReportTemplateUTIL.setColumnProperties(request, response);
		
	}
	public void SETELEMENTPROPERTIES(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::SETELEMENTPROPERTIES");
		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
		
		 DynamicReportTemplateUTIL.setElementProperties(request, response);
		
	}
//	
//	public ActionForward ACCEPTCHANGE(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"NewTemplateCreatorForTestGroupACTION::ACCEPTCHANGE");
//		DynamicReportTemplateFB fb=(DynamicReportTemplateFB)form;
//		DynamicReportTemplateUTIL util=new DynamicReportTemplateUTIL();
//		util.acceptChangesToTemplate(request, fb);
//		return mapping.findForward("ADD");
//	}
//
//	public ActionForward REPLACEMENTPROCESS(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"REPLACEMENTPROCESS");
//		
//		return mapping.findForward("REPLACE");
//	}
//	public ActionForward DELETEPROCESS(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//LOGGER_INV.log(Level.INFO,"DELETEPROCESS");
//		
//		
//		return mapping.findForward("REPLACE");
//	}
}
