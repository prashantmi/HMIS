/**
Author Anurudra Goel
DATE 10-June-2009
**/

package dossier.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dossier.transaction.controller.data.DossierRequisitionDATA;
import dossier.transaction.controller.data.LPIssueDeskTransDATA;
import dossier.transaction.controller.fb.DossierRequisitionFB;
import dossier.transaction.controller.fb.LPIssueDeskTransFB;
import dossier.transaction.controller.util.DossierDeskTransUTL;
import dossier.transaction.controller.util.LPIssueDeskTransUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LPIssueDeskTransCNT extends GenericController {

	public LPIssueDeskTransCNT() {
		super(new LPIssueDeskTransUTL(), "/transaction/LPIssueDeskTransCNT");
	}

	
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ISSUE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ActionForward acFwd = new ActionForward();
		String[] Temp = request.getParameterValues("combo");
		String[] arr  = Temp[1].split("\\^");
	    String cmb  = arr[0];
	    String str = Temp[0];
	    
	    if(cmb.equals("31")) //Issue to store 
	    {
	    	acFwd.setPath("/mms/transactions/IssueDeskTransCNT.cnt?hmode=ISSUE");
	    	acFwd.setContextRelative(true);
	        return acFwd;
	    }
	    else
		if(cmb.equals("32")) // Issue to patient (OPD)
		{
		    acFwd.setPath("/mms/transactions/IssueTransCNT.cnt?hmode=unspecified&strMode=0&strStoreId="+str+"^--");
		    acFwd.setContextRelative(true);
	        return acFwd;
		}	
		else
		{
			LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;

			LPIssueDeskTransDATA.getInitDetailForIssuePage(formBean, request);
			formBean.setStrUCReq("0");
			String strtarget = "issue";
			return mapping.findForward(strtarget);
		}
		
			
			

	}
	
	public ActionForward  RETURNTOMAINDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {
		
		return this.LIST(mapping, form, request, response);
	}
	
	
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;

		LPIssueDeskTransDATA.insertData(formBean, request);
//		//if(formBean.getStrFlag().equals("1")){
//		//	return this.LIST(mapping, form, request, response);
//		//}else{
//			return this.ISSUE(mapping, form, request, response);
//		//}
		
		LPIssueDeskTransDATA.getInitDetailForIssuePage(formBean, request);
		String strtarget = "issue";
		return mapping.findForward(strtarget);
		

	}
	
	public ActionForward RETURN(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {
		
		LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;
		LPIssueDeskTransDATA.getInitDetailForReturnPage(formBean, request);
		String strtarget = "return";
		return mapping.findForward(strtarget);
	}
	
	
	public ActionForward ISSUEVIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

	//	System.out.println(" Inside 1");
		ActionForward acFwd = new ActionForward();
		String[] Temp = request.getParameterValues("combo");
		String[] arr  = Temp[1].split("\\^");
	    String cmb  = arr[0];
	    String str = Temp[0];
	    
	    if(cmb.equals("31")) //Issue to store 
	    {
	    	acFwd.setPath("/mms/transactions/IssueDeskTransCNT.cnt?hmode=VIEW2");
	    	acFwd.setContextRelative(true);
	        return acFwd;
	    }
	    else
		{
			LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;
	
			LPIssueDeskTransDATA.getInitDetailForViewPage(formBean, request);
			
			String strtarget = "viewIssue";
			return mapping.findForward(strtarget);
		}

	}
	
	public ActionForward RETURNVIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		ActionForward acFwd = new ActionForward();
		String[] Temp = request.getParameterValues("combo");
		String[] arr  = Temp[1].split("\\^");
	    String cmb  = arr[0];
	    String str = Temp[0];
	    
	    if(cmb.equals("31")) //Issue to store 
	    {
	    	acFwd.setPath("/mms/transactions/IssueDeskTransCNT.cnt?hmode=VIEW2");
	    	acFwd.setContextRelative(true);
	        return acFwd;
	    }
	    else
		{
			
			LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;
			LPIssueDeskTransDATA.getInitDetailForReturnViewPage(formBean, request);
			String strtarget = "viewReturn";
			return mapping.findForward(strtarget);
		}

	}
	
	
	/*Insert into Return To Patient*/
	public ActionForward INSERTRET(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;
		LPIssueDeskTransDATA.insertReturn(formBean, request);
		//if(formBean.getStrFlag().equals("1")){
		//	return this.LIST(mapping, form, request, response);
		//}
	//	else{
			return this.RETURN(mapping, form, request, response);
	//	}
		

	}

	/**
	 * This method is used to cancel the Process.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	/**
	 * This method is used to FORWARD CONTROL ON A CNT FOR VIEW PAGE.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//System.out.println(" Inside 2");
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/IssueViewDetailsCNT.cnt?hmode=init");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public void PRINT(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
//		String[] s = request.getParameterValues("chk");
//		for(int i=0;i<s.length;i++)
//		{
//			System.out.println(" In Cnt Chk-->"+i+"==>>"+s[i]);			
//		}
		///hisglobal/initPage.jsp
		/*System.out.println("reached to print.........");
		ActionForward acFwd = new ActionForward();
		request.setAttribute("Path","LPIssuedesktransCNT.cnt");
		acFwd.setPath("/mms/transactions/IndentPrintTransCNT.cnt?hmode=PRINT");
	    acFwd.setContextRelative(true);
        return acFwd;*/
		LPIssueDeskTransFB formBean = (LPIssueDeskTransFB)_form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		LPIssueDeskTransDATA.showReport(formBean, request, response);
		
        
    }

	public ActionForward ISSUEDTLSINITONE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		LPIssueDeskTransFB formBean = (LPIssueDeskTransFB) form;
		LPIssueDeskTransDATA.issueDtlsInit(request, response, formBean);
		return null;
	}
	
}
