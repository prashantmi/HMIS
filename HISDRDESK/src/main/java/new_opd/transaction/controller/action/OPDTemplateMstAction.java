package new_opd.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_opd.transaction.controller.data.OPDTemplateMstData;
import new_opd.transaction.controller.fb.OPDTemplateMstFb;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.exceptions.HisException;

public class OPDTemplateMstAction  extends DispatchAction {

	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		OPDTemplateMstData.getDeptDtlData(formBean, request);
		return mapping.findForward("INITIAL");
	}
	
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		OPDTemplateMstData.getDeptDtlData(formBean, request);
		return mapping.findForward("ADD");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		OPDTemplateMstData.SaveData(formBean, request);
		
		//return mapping.findForward("INITIAL");
		return unspecified(mapping, formBean, request, response);
	}
	
	public ActionForward PRINTPRESC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		formBean.setStrHtmlString(formBean.getStrHtmlString());
		formBean.setStrJsonParaMetereIdString(formBean.getStrJsonParaMetereIdString());
		return mapping.findForward("PRINTPRESC");
	}
	public ActionForward GETPARAMETER(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		
		OPDTemplateMstData.GETPARAMETER(formBean,request,response);
		return null;
	}
	
	public ActionForward GETDRUG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		
		OPDTemplateMstData.GETDRUG(formBean,request,response); 
		return null;
	}
	
	public ActionForward GETTEST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		
		OPDTemplateMstData.GETTEST(formBean,request,response); 
		return null;
	}
	
	public ActionForward GETICD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
 
		//saveToken(request);
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		
		OPDTemplateMstData.GETICD(formBean,request,response); 
		return null;
	}
	
	public ActionForward GETSITE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		
		OPDTemplateMstData.GETSITE(formBean,request,response); 
		return null;
	}
		
	
	public ActionForward GETAUTOCOMPLETEDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		
		OPDTemplateMstData.GETAUTOCOMPLETEDATA(formBean,request,response);
		return null;
	}
	public ActionForward PRINTPRESCVIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		formBean.setStrHtmlString(formBean.getStrHtmlString());
		formBean.setStrJsonParaMetereIdString(formBean.getStrJsonParaMetereIdString());
		return mapping.findForward("PRINTPRESCVIEW");
	}
	public ActionForward DELETE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		formBean.setStrHtmlString(formBean.getStrHtmlString());
		formBean.setStrJsonParaMetereIdString(formBean.getStrJsonParaMetereIdString());
		OPDTemplateMstData.DeteleRecord(formBean, request);
		return this.unspecified(mapping, formBean, request, response);
				//mapping.findForward("PRINTPRESCVIEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
	//	System.out.println(formBean.getChk()[0]);
		OPDTemplateMstData.getModifyDTL(formBean, request);
		return mapping.findForward("MODIFY");
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OPDTemplateMstFb formBean = (OPDTemplateMstFb) form;
		OPDTemplateMstData.ModifySave(formBean, request);
		
		//return mapping.findForward("INITIAL");
		return unspecified(mapping, formBean, request, response);
	}
	
}
