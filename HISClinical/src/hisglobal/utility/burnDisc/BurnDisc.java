package hisglobal.utility.burnDisc;

/**
 * Creates ISO file system and either burns it on disc or saves to image file
 */
import com.rocketdivision.jstarburn.DiscInfo;
import com.rocketdivision.jstarburn.JStarBurnBurnerGrabber;
import com.rocketdivision.jstarburn.JStarBurnCallBack;
import com.rocketdivision.jstarburn.JStarBurnException;

public class BurnDisc {

	private String fileName;	
	public String drive;	
	private String needToVerify="0";
	
	public BurnDisc(){
		
	}
	
	public BurnDisc(String drive,String fileName,String needToVerify){
		this.drive=drive;
		this.fileName=fileName;
		this.needToVerify=needToVerify;
		
	}
  class Callback implements JStarBurnCallBack {


 public void bufferCallback(int percent) {
   if (lastBufferPercent != percent){
     lastBufferPercent = percent;
      System.out.println("buffer :" + lastBufferPercent + " percent");
   }

  }

  public void fileCallback(String fileName){
    System.out.println("adding: " + fileName);
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

        case CALLBACK_FILE_PROGRESS:
          if (lastProgressPercent != value1){
            lastProgressPercent = value1;
            System.out.println("written " + lastProgressPercent + " percent");
          }
          break;
    }
  }

  public void sizeCallback(long sizeInBytes){
    System.out.println("file system size " + (sizeInBytes / 1024 / 1024) + " MB" );
  }

  public void globalCallback(int callbackType, Object[] args)
  {	 	                    
  }

 private int lastProgressPercent = 0;
 private int lastBufferPercent = 0;
}


  public static void main(String[] args) {
    new BurnDisc().burn(args);
  }

  public void burn(String[] args) 
  {

  if (args.length != 4 && args.length != 2 ){
    System.out.println("Usage: device name> <directory> <test burn> <need verify> ");
    System.out.println("or");
    System.out.println("<target image file name> <directory> ");

    System.out.println("Example: \\\\.\\J: d:\\test 0 1");
    System.out.println("Example: d:\\test.iso d:\\test ");
    System.exit(1);
  }
  try {

    /**
     * Register library with free key (null passed)
     */
    JStarBurnBurnerGrabber.register(null);


    JStarBurnBurnerGrabber burner = null;
    if (args.length == 4){
      /**
       * Create new JStarBurnBurnerGrabber object
       */
      burner = new JStarBurnBurnerGrabber(args[0],
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

    } else {
      /**
       * Create new JStarBurnBurnerGrabber object
       */
      burner = new JStarBurnBurnerGrabber(new Callback());
    }



    /**
     * add directory to tree root
     */
    burner.CollisionDetectionDisabled(true);
 
    burner.addFile(args[1]);
 
    /**
     * create new directory
     */
    burner.createDir("test");

    /**
     * add directory to previously created directory
     */
    burner.addFile("test", args[1]);

    if (args.length < 4){
      /**
       * write tree to image file
       */
      burner.saveTreeToImage(args[0]);
    } else {
      burner.burnFileTree();


    }
    /**
      * eject disc
      */ 
     burner.ejectDisc();

	
    System.out.println("finished!");



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
  
  //new Method by riaz
  public void burn() throws BurnCDException
  {
	  //String cdRomDir="F:";
	  String cdRomDir=drive;
	  String testBurnCode="0";
	/*  if (this.fileName.equals("")){
		  System.out.println("Usage: device name> <directory> <test burn> <need verify> ");
		  System.out.println("or");
		  System.out.println("<target image file name> <directory> ");
		  
		  System.out.println("Example: \\\\.\\J: d:\\test 0 1");
		  System.out.println("Example: d:\\test.iso d:\\test ");
		  System.exit(1);
	  }*/
	  try {
		  
		  /**
		   * Register library with free key (null passed)
		   */
		  JStarBurnBurnerGrabber.register(null);
		  
		  
		  JStarBurnBurnerGrabber burner = null;
		  if (!this.fileName.equals("")){
			  /**
			   * Create new JStarBurnBurnerGrabber object
			   */
			  burner = new JStarBurnBurnerGrabber(cdRomDir,
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
			  burner.setTestMode(testBurnCode.matches("1"));
			  
			  /**
			   * set verify flag
			   */
			  burner.setVerifyNeeded(this.needToVerify.matches("1"));
			  
		  } else {
			  /**
			   * Create new JStarBurnBurnerGrabber object
			   */
			  burner = new JStarBurnBurnerGrabber(new Callback());
		  }
		  
		  
		  
		  /**
		   * add directory to tree root
		   */
		  burner.CollisionDetectionDisabled(true);
		  
		  burner.addFile(fileName);
		  
		 /* *//**
		   * create new directory
		   *//*
		  burner.createDir("test");
		  
		  *//**
		   * add directory to previously created directory
		   *//*
		  burner.addFile("test", fileName);*/
		  
		 // if (args.length < 4){
			  /**
			   * write tree to image file
			   */
			 // burner.saveTreeToImage(args[0]);
		  //} else {
			  burner.burnFileTree();
			  
			  
		 // }
		  /**
		   * eject disc
		   */ 
		  burner.ejectDisc();
		  
		  
		  System.out.println("finished!");
		  
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
		  //JStarBurnBurnerGrabber.unregister();
		  //try{
		  //System.out.println("Error occured. Error message: " + e.getMessage());
		  throw new BurnCDException(e.getMessage());
		  //}catch(Exception ex){ex.printStackTrace();}
	  }
  }
  
  
  
	public void eject(String cdRomDir){
	 try {
			  
			  /**
			   * Register library with free key (null passed)
			   */
			  JStarBurnBurnerGrabber.register(null);
			  JStarBurnBurnerGrabber burner = null;
		 	  /**
			   * Create new JStarBurnBurnerGrabber object
			   */
			  burner = new JStarBurnBurnerGrabber(cdRomDir,
					  new Callback());
			  System.out.println("Ejecting Disc:::::");
			  burner.ejectDisc();
			  
		  }
		  catch (Exception e) {
			  e.printStackTrace();
		  }
	}	  

}//end class  