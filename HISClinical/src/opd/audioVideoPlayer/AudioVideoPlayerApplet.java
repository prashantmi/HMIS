package opd.audioVideoPlayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

 public class AudioVideoPlayerApplet extends JApplet implements ControllerListener {

	Player player = null;
	// component in which video is playing
	Component visualComponent = null;
	// controls gain, position, start, stop
	Component controlComponent = null;
	// displays progress during download
	Component progressBar = null;
	JPanel panel = new JPanel();
	JLabel msg=new JLabel();
	String URLString="";
	public void init() {
		this.setSize(700, 400);
		setLayout(new BorderLayout());
		try {
	
			URL mediaURL=null;
			this.setLayout(null);
			this.add(panel);
			panel.setLayout(new BorderLayout());
			panel.setSize(700, 400);
			panel.setVisible(true);
			//set the label for displaying the error message
			panel.add(msg,BorderLayout.CENTER);
			msg.setHorizontalAlignment(JLabel.CENTER);
			msg.setForeground(Color.red);

			//URL mediaURL = new URL("http://localhost:8080/FirstPro/MOV00254.MPG");
			//URL mediaURL = new URL("ftp://10.103.0.20:8080/testWeb/MOV00254.MPG");
			URLString=getParameter("file");
			//URLString = "ftp://administrator:hisopd@10.0.5.177/dir/Sample.mov";
			//URLString = "file:///c:/balloo01.mpg";
			mediaURL = new URL(URLString);
			// URL mediaURL=new URL(Config.URL_OF_AUDIO_VIDEO_FILE_ON_SERVER+"/balloo01.mpg");
			System.out.println(mediaURL.getPath());
			//MediaLocator ml=new MediaLocator(mediaURL);
			
			if(mediaURL!=null)
				player = Manager.createRealizedPlayer(mediaURL);
			//get the visual component of the player
			Component video = player.getVisualComponent();
			//get the seek bar,play and pause control
			Component audio = player.getControlPanelComponent();
			player.addControllerListener(this);
			if(audio!=null)
			panel.add(audio, BorderLayout.SOUTH);
			if (video != null) {
				panel.add(video, BorderLayout.CENTER);
			}
			//player.start();
	
		}
		catch (CannotRealizeException e) {
			System.out.println("This format cannot be play with this Player");
			msg.setText("This format cannot be play with this Player");
			msg.setForeground(Color.red);
		}
		catch (MalformedURLException e) {
			System.out.println("Url is not correct");
			msg.setText("File Path is not correct");
			msg.setForeground(Color.red);
		}
		catch (NoPlayerException e) {
			if(e.getClass()==IOException.class)
			{
				e.printStackTrace();
				msg.setText("The specified File cannot be found");
				msg.setForeground(Color.red);
			}
			else
			{
				e.printStackTrace();
				msg.setText("The specified File cannot be found");
				msg.setForeground(Color.red);
			}
		}
		catch (IOException e) {
			System.out.println("The "+ URLString + " File cannot be found");
			msg.setText("The specified File cannot be found");
			msg.setForeground(Color.red);
		}
		catch (Exception e) {
			System.out.println("Got exception " + e);
			msg.setText("Exception in Play");
			e.printStackTrace();
		}
		
	}

	public void start() {

		try{
			if(player!=null)
			player.start();
		}
		catch (Exception e) {
			System.out.println("Exception in play " + e);
			e.printStackTrace();
		}
	}

	public void stop() {
		if(player!=null){
			player.stop();
			player.deallocate();
		}	
	}

	public void destroy() {
	if(player!=null)
		player.close();

	}

	 public synchronized void controllerUpdate(ControllerEvent event) {
         if (event instanceof RealizeCompleteEvent) {
             Component comp;
             //player.setRate(0.8F);
             if ((comp = player.getVisualComponent()) != null)
            	 add ("Center", comp);
             if ((comp = player.getControlPanelComponent()) != null)
            	 add ("South", comp);
             	validate();
         }

     }
	/**
	 * This controllerUpdate function must be defined in order
	 * to implement a ControllerListener interface. This
	 * function will be called whenever there is a media event.
	 */
	/*public synchronized void controllerUpdate(ControllerEvent event) {
		// If we're getting messages from a dead player,

		// just leave
		if (player == null)
			return;
		// When the player is Realized, get the visual
		// and control components and add them to the Applet
		if (event instanceof RealizeCompleteEvent) {
			if ((visualComponent = player.getVisualComponent()) != null)
				add("Center", visualComponent);
			if ((controlComponent = player.getControlPanelComponent()) != null)
				add("South", controlComponent);
			// force the applet to draw the components
			validate();
		} else if (event instanceof CachingControlEvent) {
			// Put a progress bar up when downloading starts,
			// take it down when downloading ends.
			CachingControlEvent e = (CachingControlEvent) event;
			CachingControl cc = e.getCachingControl();
			long cc_progress = e.getContentProgress();
			long cc_length = cc.getContentLength();
			// Add the bar if not already there ...
			if (progressBar == null)
				if ((progressBar = cc.getProgressBarComponent()) != null) {
					add("North", progressBar);
					validate();
				}
			// Remove bar when finished ownloading
			if (progressBar != null)
				if (cc_progress == cc_length) {
					remove(progressBar);
					progressBar = null;
					validate();
				}
		} else if (event instanceof EndOfMediaEvent) {

			// We've reached the end of the media; rewind and
			// start over
			player.setMediaTime(new Time(0));
			player.start();
		}

	}
      */          	
                

    }
