package hisglobal.utility.burnDisc;

/**
 * Burns ISO file system image to disc
 */
import com.rocketdivision.jstarburn.*;
import java.util.*;


public class BurnImage {

  class Callback implements JStarBurnCallBack {

  public void globalCallback(int callbackType, Object[] args) 
  {	  
  }

  public void bufferCallback(int percent) 
  {
    if (lastBufferPercent != percent){
      lastBufferPercent = percent;
       System.out.println("buffer :" + lastBufferPercent + " percent");
    }

   }

   public void fileCallback(String fileName){
   }

	public void closeSessionCallback(boolean closingBegin)
   {
	   if (closingBegin)
	   {
		   System.out.println("Closing session ...");
	   }
	   else
	   {
		   System.out.println("Closing session finished!");
	   }
   }

   public void progressCallback(int callbackType, int value1, int value2){
     switch (callbackType){
       case CALLBACK_WRITE_PROGRESS:
         if (lastProgressPercent != value1){
           lastProgressPercent = value1;
           System.out.println("burned " + lastProgressPercent + " percent");
         }
         break;


       case CALLBACK_VERIFY_PROGRESS:
         if (lastProgressPercent != value1){
           lastProgressPercent = value1;
           System.out.println("verified " + lastProgressPercent + " percent");
         }
         break;

     }
   }

   public void sizeCallback(long sizeInBytes){
     System.out.println("need to burn " + (sizeInBytes / 1024 / 1024) + " MB" );
   }

  private int lastProgressPercent = 0;
  private int lastBufferPercent = 0;
}


  public static void main(String[] args) {
    BurnImage test = new BurnImage();
    test.burn(args);
  }

  public BurnImage() {

  }

  public void burn(String[] args) {

    args= new String[]{"F:","C:\\a.java","0","0"};
    if (args.length != 4){
      System.out.println("Usage: device name> <image name> <test burn> <need verify>");
      System.out.println("Example: \\\\.\\J: d:\\1.iso 0 1");
      System.exit(1);
    }
    try {
    		

      /**
       * Register library with free key (null passed)
       */
      JStarBurnBurnerGrabber.register(null);

      /**
       * Create new JStarBurnBurnerGrabber object
       */
      JStarBurnBurnerGrabber burner = new JStarBurnBurnerGrabber(args[0],
        new Callback());

      /**
       * get disc information
       */
      DiscInfo info = burner.getDiscInformation();

      /**
       * check if there is a disc in drive
       */
      if (info.getType() == DiscInfo.DISC_TYPE_NO_MEDIA) {
        System.out.println("no disc");
        throw new JStarBurnException("No disc in drive");
      }

      /**
       * set test mode flag
       */
      burner.setTestMode(args[2].matches("1"));

      /**
       * set verify flag
       */
      burner.setVerifyNeeded(args[3].matches("1"));

      /**
       * do the burning
       */
     burner.burnIsoImage(args[1]);

      System.out.println("finished!");

      /**
       * eject disc
       */
      burner.ejectDisc();

      /**
       * Free resources
       */
      burner.freeResources();

      /**
       * Unregister library
       */
      JStarBurnBurnerGrabber.unregister();

    }
    catch (Exception e) {
      System.out.println("Error occured. Error message: " + e.getMessage());
    }
  }
}