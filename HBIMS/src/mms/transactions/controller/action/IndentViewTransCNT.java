package mms.transactions.controller.action;

import hisglobal.exceptions.HisException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.controller.data.AnnualPurchaseIndentDATA;
import mms.transactions.controller.data.IndentViewTransDATA;
import mms.transactions.controller.fb.AnnualPurchaseIndentFB;
import mms.transactions.controller.fb.IndentViewTransFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IndentViewTransCNT extends DispatchAction 
{
	/**
	 * INDENTDATA Method Use to Transfer the Control Over Action IndentData.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 * @throws IOException 
	 */ 
	public ActionForward INDENTDATA(ActionMapping mapping
			                        ,ActionForm form
			                        ,HttpServletRequest request
			                        ,HttpServletResponse response)throws HisException, SQLException, IOException
    {
		String strTarget = "view";
		IndentViewTransFB formBean = (IndentViewTransFB) form;
		//String[] combo = request.getParameterValues("combo");
		formBean.setStrChk(request.getParameter("chk"));
    	String path = "/mms"+request.getParameter("cnt_page")+".cnt";
		formBean.setStrPath(path.trim());
		IndentViewTransDATA.viewData(formBean,request);
		return mapping.findForward(strTarget);
	}
	
public ActionForward MODIFY(ActionMapping mapping
            ,ActionForm form
            ,HttpServletRequest request
            ,HttpServletResponse response)throws HisException, SQLException, IOException
{
String strTarget = "Modify";
IndentViewTransFB formBean = (IndentViewTransFB) form;
//String[] combo = request.getParameterValues("combo");
formBean.setStrChk(request.getParameter("chk"));
String path = "/mms/transactions/IndentTransCNT.cnt";

System.out.println("path ++>."+path);
formBean.setStrPath(path.trim());
IndentViewTransDATA.modifyData(formBean,request);
return mapping.findForward(strTarget);
}
	/**
	 * CANCEL 
	 * is used to forward control to Indent Desk
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
//	public ActionForward CANCEL(ActionMapping _mapping, ActionForm _form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws HisException, SQLException 
//	{
//		ActionForward acFwd = new ActionForward();
//		acFwd.setPath(request.getParameter("strPath"));
//        acFwd.setContextRelative(true);
//        return acFwd;
//        
//    }
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		ActionForward acFwd = new ActionForward();
		String strPath = "";
		if(request.getParameter("strPath")!= null)
		{
			strPath = request.getParameter("strPath").concat("?hmode=CANCEL");
			acFwd.setPath(strPath);
	        acFwd.setContextRelative(true);
	        
		}
		return acFwd;
	}



	public ActionForward INSERT(ActionMapping mapping
            ,ActionForm form
            ,HttpServletRequest request
            ,HttpServletResponse response)throws HisException, SQLException, IOException
{
String strTarget = "Modify";
IndentViewTransFB formBean = (IndentViewTransFB) form;
//String[] combo = request.getParameterValues("combo");
formBean.setStrChk(request.getParameter("chk"));
String path = "/mms/transactions/IndentTransCNT.cnt";

System.out.println("path ++>."+path);
formBean.setStrPath(path.trim());

IndentViewTransDATA.INSERT(formBean,request) ;
	
	
return mapping.findForward(strTarget);
}
	
	
}
