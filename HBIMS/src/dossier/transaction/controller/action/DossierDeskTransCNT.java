package dossier.transaction.controller.action;


import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dossier.transaction.controller.fb.DossierDeskTransFB;
import dossier.transaction.controller.util.DossierDeskTransUTL;
import dossier.transaction.controller.data.DossierDeskTransData;

public class DossierDeskTransCNT extends GenericController {

	public DossierDeskTransCNT() {
		super(new DossierDeskTransUTL(), "/transaction/DossierDeskTransCNT");
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
	public ActionForward GENERATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ActionForward acFwd = new ActionForward();
		String[] Temp = request.getParameterValues("combo");
		/*String[] arr  = Temp[1].split("\\^");*/
	   /* String cmb  = arr[0];
	    String str = Temp[0];*/
	    acFwd.setPath("/dossier/transaction/DossierRequisitionCNT.cnt?hmode=NEW&servicetypeId="+Temp[0]);
    	acFwd.setContextRelative(true);
        return acFwd;
	   
		
			
			

	}
	
	public ActionForward SETTLEMENT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ActionForward acFwd = new ActionForward();
		String[] Temp = request.getParameterValues("combo");
		/*String[] arr  = Temp[1].split("\\^");*/
	   /* String cmb  = arr[0];
	    String str = Temp[0];*/
	    acFwd.setPath("/dossier/transaction/DossierSettlementCNT.cnt?hmode=NEW&servicetypeId="+Temp[0]);
    	acFwd.setContextRelative(true);
        return acFwd;
	   
		
			
			

	}
	
	public ActionForward  RETURNTOMAINDESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {
		
		return this.LIST(mapping, form, request, response);
	}
	
	
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DossierDeskTransFB formBean = (DossierDeskTransFB) form;

		DossierDeskTransData.insertData(formBean, request);
//		//if(formBean.getStrFlag().equals("1")){
//		//	return this.LIST(mapping, form, request, response);
//		//}else{
//			return this.ISSUE(mapping, form, request, response);
//		//}
		
		DossierDeskTransData.getInitDetailForIssuePage(formBean, request);
		String strtarget = "issue";
		return mapping.findForward(strtarget);
		

	}
	
	public ActionForward RETURN(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {
		
		DossierDeskTransFB formBean = (DossierDeskTransFB) form;
		DossierDeskTransData.getInitDetailForReturnPage(formBean, request);
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
			DossierDeskTransFB formBean = (DossierDeskTransFB) form;
	
			DossierDeskTransData.getInitDetailForIssuePageForView(formBean, request);
			
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
			
			DossierDeskTransFB formBean = (DossierDeskTransFB) form;
			DossierDeskTransData.getInitDetailForReturnViewPage(formBean, request);
			String strtarget = "viewReturn";
			return mapping.findForward(strtarget);
		}

	}
	
	
	/*Insert into Return To Patient*/
	public ActionForward INSERTRET(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DossierDeskTransFB formBean = (DossierDeskTransFB) form;
		DossierDeskTransData.insertReturn(formBean, request);
		//if(formBean.getStrFlag().equals("1")){
		//	return this.LIST(mapping, form, request, response);
		//}
		//else{
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
		DossierDeskTransFB formBean = (DossierDeskTransFB)_form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		DossierDeskTransData.showReport(formBean, request, response);
		
        
    }
	public ActionForward CANCELDOSSIER(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException, SQLException {
		
		DossierDeskTransFB formBean = (DossierDeskTransFB) form;
		DossierDeskTransData.CANCELDOSSIER(formBean, request);
		String strtarget = "cancelled";
		//return this.LIST(mapping, formBean, request, response);
		return mapping.findForward(strtarget);
	}
	
	public ActionForward ISSUEDTLSINIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		DossierDeskTransFB formBean = (DossierDeskTransFB) form;
		DossierDeskTransData.issueDtlsInit(request, response, formBean);
		return null;
	}
}