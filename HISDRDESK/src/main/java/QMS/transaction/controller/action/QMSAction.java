package QMS.transaction.controller.action;


/**
## 		Modification Log		: PATIENTSUMMARY					
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/

import hisglobal.config.HISConfig;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QMS.transaction.controller.data.QmsData;
import QMS.transaction.controller.fb.QmsFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket1")
public class QMSAction extends DispatchAction
{
	private static Set<Session> clients = 
		    Collections.synchronizedSet(new HashSet<Session>());
		  
		  @OnMessage
		  public void onMessage(String message, Session session) 
		    throws IOException {
		    
		    synchronized(clients){
		      // Iterate over the connected sessions
		      // and broadcast the received message
		      for(Session client : clients){
		        if (!client.equals(session)){
		          client.getBasicRemote().sendText(message);
		        }
		      }
		    }
		    
		  }
		  
		  @OnOpen
		  public void onOpen (Session session) {
		  // Add session to the connected sessions set
		    clients.add(session);
		  }

		  @OnClose
		  public void onClose (Session session) {
		    // Remove session from the connected sessions set
		    clients.remove(session);
		  }
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		System.out.println("new opd desk:::::");
		QmsFB formBean = (QmsFB) form;
		formBean.setStrInitialMode("1");
		return this.NEW1(mapping, form, request, response);
	}

	/**
	 * sets the view to Patient Listing View
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("123::::::::::");
		QmsFB formBean = (QmsFB) form;
		formBean.setStrInitialMode("2");
		QmsData.getInitailData(formBean ,request);
		return mapping.findForward("NEW");
	}
	public ActionForward NEW1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("123::::::::::");
		QmsFB formBean = (QmsFB) form;
		formBean.setStrInitialMode("1");
		
		/*if(request.getParameter("modeFlag") != null){
			if(request.getParameter("modeFlag").equals("M")){
				String seatId=request.getParameter("seat_id");
				String hospCode=request.getParameter("hospCode");
				String userName=request.getParameter("user_name");
				
				request.getSession().setAttribute("HOSPITAL_CODE", hospCode);
				request.getSession().setAttribute("SEATID",seatId);
				request.getSession().setAttribute("USER_NAME",userName);
				
				UserVO voGlobalUser = new UserVO();
				voGlobalUser.setSeatId(seatId);
				voGlobalUser.setUsrName(userName);
				voGlobalUser.setHospitalCode(hospCode);
				request.getSession().setAttribute(HISConfig.USER_VO,voGlobalUser);
			}
		}*/
		
		
		QmsData.getInitailData(formBean ,request);
		return mapping.findForward("NEW");
	}
	/*
	public ActionForward MOBILENEW1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("123::::::::::");
		QmsFB formBean = (QmsFB) form;
		formBean.setStrInitialMode("1");
		
		String seatId=request.getParameter("seat_id");
		String hospCode=request.getParameter("hospCode");
		String userName=request.getParameter("user_name");
		
		request.getSession().setAttribute("HOSPITAL_CODE", hospCode);
		request.getSession().setAttribute("SEATID",seatId);
		request.getSession().setAttribute("USER_NAME",userName);
		
		UserVO voGlobalUser = new UserVO();
		voGlobalUser.setSeatId(seatId);
		voGlobalUser.setUserName(userName);
		voGlobalUser.setHospitalCode(hospCode);
		request.getSession().setAttribute(HISConfig.USER_VO,voGlobalUser);
		//objSession_p.setAttribute(HISConfig.USER_VO, voGlobalUser);
		
		
		QmsData.getInitailDataMobile(formBean ,request);
		return mapping.findForward("NEW");
	}
	*/
	public void GETPREV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("123::::::::::");
		QmsFB formBean = (QmsFB) form;
		QmsData.getPrevDtl(request, response);
		//return mapping.findForward("NEW");
	}
	
	public ActionForward PRINTPRESC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		 
		return mapping.findForward("NEW1");
	}
	
	
}

/**
## 		Modification Log		: PATIENTSUMMARY
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/

