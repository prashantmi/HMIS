package opd.audioVideoPlayer;


	
	import java.applet.Applet;
	import java.awt.*;
	import java.lang.String;
	import java.net.URL;
	import java.net.MalformedURLException;
	import java.io.IOException;
import javax.media.*;

import opd.OpdConfig;
	/**
	* This is a Java Applet that  create a simple
	* media player with a media event listener. It will play the
	* media clip right away and continuously loop.
	
	*/
	public class AudioVideoPlayerAPPLET extends Applet implements ControllerListener
	{


	
			Player player = null;
			public void init() {
			setLayout(new BorderLayout());
			try {
			//URL mediaURL = new URL("http://localhost:8080/FirstPro/MOV00254.MPG");
			//	URL mediaURL = new URL("http://10.103.0.20:8080/testWeb/MOV00254.MPG");
				String URLString=getParameter("file");
				
				URL mediaURL=new URL(URLString);
				//MediaLocator ml=new MediaLocator(mediaURL);
			player = Manager.createPlayer(mediaURL);
			
			player.addControllerListener(this);
			
			}
			catch (Exception e) {
			System.out.println("Got exception "+e);
			
			}
			}
			public void start() {
			player.start();
			}
			public void stop() {
			player.stop();
			player.deallocate();
			}
			public void destroy() {
			player.close();
			}
			public synchronized void controllerUpdate(ControllerEvent event) {
				
			if (event instanceof RealizeCompleteEvent) {
			Component comp;
			player.setRate(0.8F);

			if ((comp = player.getVisualComponent()) != null)
			add ("Center", comp);
			if ((comp = player.getControlPanelComponent()) != null)
			add ("South", comp);
			validate();
			}
			}
			
		}
