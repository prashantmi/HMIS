package hisglobal.utility.burnDisc;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import hisglobal.utility.burnDisc.BurnCDException;
import netscape.javascript.JSObject;

import com.rocketdivision.jstarburn.DeviceInfo;
import com.rocketdivision.jstarburn.JStarBurnBurnerGrabber;

public class BurnDiscApplet extends JApplet implements ActionListener{
	
	JLabel driveName;
	JComboBox driveCombo;
	JButton burnButton;
	JButton cancelButton;
	JPanel panel=new JPanel();
	JLabel msg=new JLabel();
	String path="";
	String winPath="";
	String linuxPath="";
	String drives[]=null;
	String driveCode[]=null;
	public void init() {
			
		//path=getParameter("path");
		winPath=getParameter("winPath");
		linuxPath=getParameter("linuxPath");
		
		driveName=new JLabel();
		driveName.setText("Drive");
		if(System.getProperties().getProperty("os.name").startsWith("Win")){
		
			path=winPath;
		}	
		else
			path=linuxPath;
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.out.println("Inside init-----------------------------------");
			// start();
			burnButton=new JButton("Burn");
			cancelButton=new JButton("Cancel");
			
			drives=FindDevices();	
			panel.setLayout(null);
			if(drives.length>0){
				driveCombo=new JComboBox(drives);
			}
			else{
				driveCombo=new JComboBox();
				msg.setText("No Drive Found..........");
				burnButton.setEnabled(false);
			}
			driveName.setBounds(100, 50, 60, 20);
			driveCombo.setBounds(160, 50, 160, 20);
			burnButton.setBounds(110, 120, 80, 20);
			cancelButton.setBounds(200, 120, 80, 20);
			msg.setBounds(120, 170, 250, 20);
			msg.setForeground(Color.RED);
			panel.add(driveName);
			panel.add(driveCombo);
			panel.add(burnButton);
			panel.add(cancelButton);
			panel.add(msg);
			//msg.setSize(100, 100);
			panel.setVisible(true);
			this.getContentPane().add(panel);
			this.setSize(400, 250);
			burnButton.addActionListener(this);
			cancelButton.addActionListener(this);
			
		}
		catch (Exception e) {
			System.out.println("Exception in init");
		}
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==burnButton){
			System.out.println(driveCode.length);
			String drive=(String)driveCombo.getSelectedItem();
			for(int i=0;i<drives.length;i++){
				if(drives[i].equals(drive)){
					drive=driveCode[i];
					break;
				}
			}
			BurnDisc test = new BurnDisc(drive,path,"0");
			System.out.println("Path to burn :" +path);
			System.out.println("burring drive :" + drive);
			try{
				//BurnDisc test = new BurnDisc("F:","C:\\a.java","0");
			    test.burn();
				//test.eject(test.drive);
			    //msg.setText("Burned Succesfully");
				//JSObject.getWindow (this).eval ("javascript:alert('Burn Completed Successfully')") ;
			    JOptionPane.showMessageDialog(this,"Burn Process Completed Successfully","Burn Disc" ,JOptionPane.INFORMATION_MESSAGE);
			    File f=new File(path);
			    File fileArray[]=f.listFiles();
			    for(int i=0;i<fileArray.length;i++){
			    	//System.out.println("file name:"+fileArray[i]);
			    	fileArray[i].delete();
			    }	
				JSObject.getWindow (this).eval ("javascript:closeWindow()") ; 
			}
			
			catch (BurnCDException em) {
				System.out.println("BurnCDException");
				//msg.setText(em.getMessage());
				JOptionPane.showMessageDialog(this,em.getMessage(),"Error" ,JOptionPane.INFORMATION_MESSAGE);
				test.eject(test.drive);
				em.printStackTrace();
			}
			catch (Exception ex) {
				//msg.setText(ex.getMessage());
				JOptionPane.showMessageDialog(this,ex.getMessage(),"Error" ,JOptionPane.ERROR_MESSAGE);
				test.eject(test.drive);
				ex.printStackTrace();
			}
			finally{
				//msg.setText(buffer.toString());
				//System.exit(0);
			}
		}
		/**
		 * calling the javascript function on cancel button event
		 * JSObject is available in plugin.jar
		 */
		if(e.getSource()==cancelButton){
			JSObject.getWindow (this).eval("javascript:self.close()") ; 
			
		}
	}
	
	public String[] FindDevices()
	  {
	    String [] drivesArray=null;	
		try{
	        /**
	         * Register library with free key (null passed)
	         */
	        JStarBurnBurnerGrabber.register(null);	
	        
	        JStarBurnBurnerGrabber.setSCSITransport(JStarBurnBurnerGrabber.SCSI_TRANSPORT_SPTI);
	        
	        int SCSITransport = JStarBurnBurnerGrabber.SCSI_TRANSPORT_UNKNOWN;
	        
	        SCSITransport = JStarBurnBurnerGrabber.getSCSITransport();

	        /**
	         * scan available devices
	         */
	        List deviceList = JStarBurnBurnerGrabber.scanDevices();
	        drivesArray=new String[deviceList.size()];
	        driveCode=new String[deviceList.size()];
	        for (int i = 0; i < deviceList.size(); i++) 
	        {
	          DeviceInfo info = (DeviceInfo) deviceList.get(i);
	          System.out.println("Found device: "+ info.getVendorId() + " " +info.getProductRevisionLevel() + " " + info.getDeviceName() +
	                             " " +
	                             (info.isCDRWrite() ? "burner" : "reader"));
	          drivesArray[i]=info.getProductRevisionLevel() + " " + info.getDeviceName()+(info.isCDRWrite() ? "burner" : "reader");
	          driveCode[i]=info.getDeviceName();
	         }
	        
	        /**
	         * Unregister library
	         */
	        JStarBurnBurnerGrabber.unregister();

	      } catch (Exception e){
	          System.out.println("Error occured. Error message: " + e.getMessage());
	      }	 
	      return drivesArray;
	  }  
	
}//end class
	
	 

