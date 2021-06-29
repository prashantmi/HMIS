package startup;
import javax.servlet.*;
import javax.servlet.http.*;

import login.Auth;

public class hisUserSessionListener  implements HttpSessionListener{
	
	public void sessionCreated(HttpSessionEvent event) {
		
		int sessionTimeOutSeconds=30*60;
		event.getSession().setMaxInactiveInterval(sessionTimeOutSeconds);
	}
	public void sessionDestroyed(HttpSessionEvent event) {	
		//System.out.println("INSIDE DESTROY");
		Auth authObj = new Auth();		
		ServletContext sc = event.getSession().getServletContext();
		synchronized(sc)
		{
			userInfoList userList = (userInfoList)sc.getAttribute("userList");
			if(userList==null || userList.size()==0)
				userList = new userInfoList();			
			HttpSession session = null;
			session = event.getSession();
			String seatId =  null;			
			if(session!=null)				
				seatId = (String)session.getAttribute("SEATID");		
			if ( seatId != null )
			{
				userList.remove(seatId);	
				authObj.logOutAction(seatId);//update log out field in gblt_user_log
				session.removeAttribute("SEATID");//from session				
			}
			sc.setAttribute("userList",userList);			
		}
		//System.out.println("GOING DESTROYin hisUserSessionListener");
	}
}
