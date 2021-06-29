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


public class DayEndTransBSCNT extends CSRFGardTokenAction{

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
		DayEndTransFB formBean = (DayEndTransFB) form;
		DayEndTransDATA.dayEndProcess(formBean,request);
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
			DayEndTransFB formBean = (DayEndTransFB) form;			
			formBean.setStrCounterId(request.getParameter("strCounterId"));		
			DayEndTransDATA.addData(formBean,request,response);

		
		if(formBean.getStrReportFormat().toUpperCase().equals("TEXT"))
		{
			formBean.setStrMsg("Day End Report (Summary No. '"+formBean.getStrSummNo()+"' ) Generated Successfully");
			return this.PROCESS(mapping, formBean, request, response);
		}
		else
		{
			return null;
		}
		
	
	}
	
	
	public ActionForward REPRINT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		DayEndTransDATA.rePrint(request, response);

		return null;

	}
	
	
//	public void SHOWRPT(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws HisException, SQLException {
//
//		
//		
//		DayEndTransFB formBean = (DayEndTransFB) form;
//		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
//		DayEndTransDATA.showReport(formBean, request, response);
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
			DayEndTransFB formBean = (DayEndTransFB) form;
	
			DayEndTransDATA.pendingDayEndDetails(formBean,request,response);
		
			return null; 
		}
		
		public ActionForward getDayEndAmountAjax(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
			try 
			{
				DayEndTransFB formBean = (DayEndTransFB) form;	
			DayEndTransDATA.getDayEndAmountAjax(formBean,request,response);	
			DayEndTransDATA.getDayEndCreditColAjax(formBean,request,response);	
			DayEndTransDATA.getDayEndInstAmtAjax(formBean,request,response);	
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
			DayEndTransFB formBean = (DayEndTransFB) form;
	
			DayEndTransDATA.getDayEndAmountAjaxUserNCounterWise(formBean,request,response);
		
			return null; 
		}
    }