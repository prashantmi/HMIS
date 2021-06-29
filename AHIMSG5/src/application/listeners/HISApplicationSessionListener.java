/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISApplicationSessionListener.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package application.listeners;

import hissso.config.HISSSOServerConfig;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HISApplicationSessionListener implements HttpSessionListener
{

	public void sessionCreated(HttpSessionEvent objSessionEvent)
	{
		// Nothing to do right now for HIS
	}

	public void sessionDestroyed(HttpSessionEvent objSessionEvent)
	{
		HttpSession objSession = objSessionEvent.getSession();

		// Check for SSO TGT Expiration
		HISSSOServerConfig.onSessionExpiration(objSession);
	}
}
