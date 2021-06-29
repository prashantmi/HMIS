/**********************************************************
 Project:	   AHIMS_G5	
 File:         HISApplicationRequestListener.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package application.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class HISApplicationRequestListener implements ServletRequestListener
{

	public void requestDestroyed(ServletRequestEvent arg0)
	{
		// Nothing to do right now for HIS
	}

	public void requestInitialized(ServletRequestEvent arg0)
	{
		// Nothing to do right now for HIS
	}

}
