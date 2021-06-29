package hisglobal.utility.burnDisc;

import java.util.List;
import java.util.Vector;

import com.rocketdivision.jstarburn.DeviceInfo;
import com.rocketdivision.jstarburn.DiscInfo;
import com.rocketdivision.jstarburn.JStarBurnBurnerGrabber;
import com.rocketdivision.jstarburn.JStarBurnCallBack;

/* * Scans system for available CD/DVD drives */

public class ScanDevices {

 class Callback implements JStarBurnCallBack {

	 public void globalCallback(int callbackType, Object[] args) 
	{	  
	}

	public void bufferCallback(int percent) 
	{
	}

	public void closeSessionCallback(boolean closingBegin)
	{
	}
	
	public void fileCallback(String fileName)
	{
	}

	public void progressCallback(int callbackType, int value1, int value2)
	{
	}

	public void sizeCallback(long sizeInBytes)
	{
	}
 }

  public void FindDevices(String[] args)
  {
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

        for (int i = 0; i < deviceList.size(); i++) 
        {
          DeviceInfo info = (DeviceInfo) deviceList.get(i);
          System.out.println("Found device: "+ info.getVendorId() + " " +info.getProductRevisionLevel() + " " + info.getDeviceName() +
                             " " +
                             (info.isCDRWrite() ? "burner" : "reader"));
          
          JStarBurnBurnerGrabber burner = null;
          
          switch(SCSITransport)
          {
          case JStarBurnBurnerGrabber.SCSI_TRANSPORT_ASPI:
        	  burner = new JStarBurnBurnerGrabber(info.getPortId(), info.getBusId(), info.getTargetId(), info.getLUN(), new Callback());
        	  break;
          case JStarBurnBurnerGrabber.SCSI_TRANSPORT_SPTI:        	  
          case JStarBurnBurnerGrabber.SCSI_TRANSPORT_SPTD:
        	  burner = new JStarBurnBurnerGrabber(info.getDeviceName(), new Callback());        	  
        	  break;          
          }
      
          /**
           * get disc information
           */          
          
          DiscInfo discInfo = burner.getDiscInformation();          
   
          Vector speeds;
          DiscInfo.RWSpeed speed;     
          
  	      //Print supported read speeds
  	      speeds = discInfo.getReadSpeeds();
  	      System.out.println("Supported read speeds: ");    
  	      for(int j=0; j < speeds.size() ; j++)
  		  {
  		    speed = (DiscInfo.RWSpeed)speeds.elementAt(j);
  		    System.out.println(speed.speedInKBps + " ("+ speed.speedX +")");		          
  		  }
  		  
  		  //Print supported write speeds
  		  speeds = discInfo.getWriteSpeeds();
  		  System.out.println("Supported write speeds:");    
  		  for(int k=0; k < speeds.size() ; k++)
  		  {
  		    speed = (DiscInfo.RWSpeed)speeds.elementAt(k);
  		    System.out.println(speed.speedInKBps + " ("+ speed.speedX +")");
  		  }
        }
        
        /**
         * Unregister library
         */
        JStarBurnBurnerGrabber.unregister();

      } catch (Exception e){
          System.out.println("Error occured. Error message: " + e.getMessage());
      }	  
  }  
  
  public static void main(String[] args) 
  {
	  new ScanDevices().FindDevices(args);
  }


}