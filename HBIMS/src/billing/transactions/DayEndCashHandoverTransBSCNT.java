package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;


public class DayEndCashHandoverTransBSCNT extends CSRFGardTokenAction{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		return this.PROCESS(mapping, form, request, response);

    }

	/**
	 * forwards control to the Day End Process Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward PROCESS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
			throws HisException, SQLException
	{
		generateToken(request);
		String target = "enter";
		DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;
		DayEndCashHandoverTransDATA.dayEndProcess(formBean,request);
		return mapping.findForward(target);
	}
	
	/**
	 * forwards control to the Day End Report Page and 
	 * insertion of data
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception 
	 * @throws SQLException
	 */
	
	
	public ActionForward DAYENDREPORT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	        throws Exception
    {	
			validateToken(request, response);
			DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;			
			formBean.setStrCounterId(request.getParameter("strCounterId"));		
			DayEndCashHandoverTransDATA.addData(formBean,request,response);
			return this.PROCESS(mapping, formBean, request, response);
			
	}
	
	
	public ActionForward REPRINT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DayEndCashHandoverTransDATA.rePrint(request, response);

		return null;

	}
	
	
//	public void SHOWRPT(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws HisException, SQLException {
//
//		
//		
//		DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;
//		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
//		DayEndCashHandoverTransDATA.showReport(formBean, request, response);
//		
//		
//	}

	
	/**
	 * forwards control to the Initial Page and 
	 * insertion of data
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws Exception 
	 * @throws SQLException
	 */
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
	    ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
			
		
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
		public ActionForward pendingDayEndDetails(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		{
			DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;
	
			DayEndCashHandoverTransDATA.pendingDayEndDetails(formBean,request,response);
		
			return null; 
		}
		
		public ActionForward getDayEndAmountAjax(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
			try 
			{
				DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;	
			DayEndCashHandoverTransDATA.getDayEndAmountAjax(formBean,request,response);	
			DayEndCashHandoverTransDATA.getDayEndCreditColAjax(formBean,request,response);	
			DayEndCashHandoverTransDATA.getDayEndInstAmtAjax(formBean,request,response);	
			response.getWriter().print(formBean.getStrDayEndAmount()+"#"+formBean.getStrDayEndCreditCol()+"#"+formBean.getStrDayEndInstAmt());
		    } catch (Exception e) {
			e.printStackTrace();
		    } 
			return null; 
		}
		
		//get day end amount userwise n counter wise Ajax..
		public ActionForward getDayEndAmountAjaxUserNcounterWise(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		{
			DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;
	
			DayEndCashHandoverTransDATA.getDayEndAmountAjaxUserNCounterWise(formBean,request,response);
		
			return null; 
		}
		/*public ActionForward OfflineMode(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			DayEndCashHandoverTransFB formBean = (DayEndCashHandoverTransFB) form;
	
			DayEndCashHandoverTransDATA.OfflineMode(formBean,request,response);
		
			return null; 
		}*/
    }