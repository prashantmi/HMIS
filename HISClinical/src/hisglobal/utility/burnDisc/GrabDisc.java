package hisglobal.utility.burnDisc;

/**
 * Grabs CD/DVD to ISO or MDS image file
 *
 */
import com.rocketdivision.jstarburn.*;

public class GrabDisc {
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

 public void fileCallback(String fileName){
 }

 public void progressCallback(int callbackType, int value1, int value2){
   switch (callbackType){
     case CALLBACK_READ_PROGRESS:
       if (lastProgressPercent != value1){
         lastProgressPercent = value1;
         System.out.println("grabbed " + lastProgressPercent + " percent");
       }
       break;

       case CALLBACK_TRACK_BEGIN:
         System.out.println("processing track " + value1 + " of " + value2);
         break;

       case CALLBACK_SPLIT_BEGIN:
         System.out.println("processing split section " + value1 + " of " + value2);
         break;


       case CALLBACK_BAD_BLOCK_HIT:
         System.out.println(value1 + " bad blocks hit at LBA " + value2);
         break;

       case CALLBACK_ECCEDC_BAD_BLOCK_HIT:
         System.out.println(value1 + "ECC/EDC bad blocks hit at LBA " + value2);
         break;

         case CALLBACK_READ_RETRY:
           System.out.println("Read retry at LBA " + value1 + ". Transfer size is " + value2 + " logical blocks");
           break;

   }
 }

 public void sizeCallback(long sizeInBytes){
   System.out.println("need to save" + (sizeInBytes / 1024 / 1024) + " MB" );
 }

private int lastProgressPercent = 0;
private int lastBufferPercent = 0;
}

  public void grab(String[] args){

    if (args.length < 2 ||  args.length > 3){
      System.out.println("Usage: device name> <image name> [optional dvd padding in MBs for mds image]");
      System.out.println("Example: \\\\.\\J: d:\\1.iso ");
      System.out.println("Example: \\\\.\\J: d:\\1.mds 2048 ");
      System.exit(1);
    }

    try{

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

      if (args[1].endsWith(".iso")){
        /**
         * Grab to ISO image (last track is grabbed)
         */
        burner.grabToIso(args[1]);
      } else if (args[1].endsWith(".mds")){
        if (args.length == 3){
          /**
           * Set DVD padding if required
           */
          burner.setDvdPadding(Integer.parseInt(args[2]));

        }

        burner.setReadMode(JStarBurnBurnerGrabber.READ_MODE_RAW_PQ);

        /**
         * Grab to MDS image
         */
        burner.grabToMds(args[1].substring(0,args[1].length() - 4));
      }

      System.out.println("finished!");


      /**
       * Free resources
       */
      burner.freeResources();

      /**
       * Unregister library
       */
      JStarBurnBurnerGrabber.unregister();

    } catch (Exception e){
       System.out.println("Error occured. Error message: " + e.getMessage());
    }
  }



  public static void main(String[] args) {
    new GrabDisc().grab(args);
  }
}
