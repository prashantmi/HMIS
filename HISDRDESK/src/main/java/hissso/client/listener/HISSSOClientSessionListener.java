package hissso.client.listener;

import java.util.Date;

import hissso.config.HISSSOClientConfig;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HISSSOClientSessionListener implements HttpSessionListener
{

	public void sessionCreated(HttpSessionEvent objSessionEvent)
	{
		//System.out.println("******************  HBIMS sessionCreated in HISSSOClientSessionListener************Stack Trace  ************************ @ >> "+ objSessionEvent.getSession().getId());
		//System.out.println("******************  HBIMS sessionCreated in HISSSOClientSessionListener************Class Name  ************************ @ >> "+ objSessionEvent.getSource().getClass().getName());
		
		// Nothing to do right now for HIS
	}

	public void sessionDestroyed(HttpSessionEvent objSessionEvent)
	{
		//System.out.println("******************  HBIMS sessionDestroyed in HISSSOClientSessionListener************Stack Trace  ************************ @ >> "+ objSessionEvent.getSession().getId());
		HttpSession objSession = objSessionEvent.getSession();

		// Check for SSO TGT Expiration
		HISSSOClientConfig.onSessionExpiration(objSession);
	}
}
