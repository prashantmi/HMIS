package hisglobal.utility.burnDisc;

/**
 * Burns ISO file system image to disc
 */

public class TestBurn {

  public static void main(String[] args) {
    try{
		BurnDisc test = new BurnDisc("F:","C:\\a.java","0");
	    test.burn();
    }
    catch(BurnCDException e){
    	e.printStackTrace();
    }
  }

}