package hisglobal.listener;

import java.util.ArrayList;
import java.util.Iterator;


import hisglobal.exceptions.HisInvalidSessionException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class HisRequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void requestInitialized(ServletRequestEvent arg0) {
		/*ServletContext context  =arg0.getServletContext();
		ArrayList invalidSessionList = (ArrayList) context.getAttribute("INVALID_SESSION_USER");
		ArrayList loginUserList = (ArrayList) context.getAttribute("LOGIN_USER");
		String val="";
		String[] temp;
		if(invalidSessionList != null)
		{
			if(loginUserList != null)
			{
				for(int i=0;i<invalidSessionList.size();i++)
				{
					for(int j=0;j<loginUserList.size();j++)
					{
						val = (String)invalidSessionList.get(i);
						temp =((String)loginUserList.get(j)).split("#");
						if(temp[0].equals(val))
						{
							loginUserList.remove(j);
							context.removeAttribute("LOGIN_USER");
							context.setAttribute("LOGIN_USER",loginUserList);
							throw new HisInvalidSessionException();
						}
					}
					
				}
			}
			*/
		}
		
			
		
		
	}
	
	


