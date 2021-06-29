 
package new_investigation.reportGenerator.Logging;

 
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
 
import java.io.IOException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
 
 
public class ServiceLogger {

    //public static FileHandler fh;
    static boolean  isDebug = true;
    public static void Log(String className, Level level, String msg) {
        
        isDebug = PropertiesHelper.getIsDebugConfiguration();
        if(isDebug)
            System.out.println(msg);
        
        //FileHandler fh = null;
         
        
        org.apache.log4j.Level l;
        if (level ==  Level.INFO)
            l = org.apache.log4j.Level.INFO;
        else if(level == Level.SEVERE)
            l = org.apache.log4j.Level.FATAL;
        else if (level == Level.WARNING)
            l = org.apache.log4j.Level.WARN;
        else
            l = org.apache.log4j.Level.DEBUG;
                
                
                
        
        Logger.getLogger(className).log( l, "");
        Logger.getLogger("").log( l, msg);
        Logger.getLogger("").log( l, "");
       // Logger.getLogger("df").addAppender(null);
        

    }

    public static void Log(String className, Level level, Exception ex) {
         isDebug = PropertiesHelper.getIsDebugConfiguration();
        if(isDebug)
            ex.printStackTrace();
        //FileHandler fh = null;
         
         org.apache.log4j.Level l;
        if (level ==  Level.INFO)
                l = org.apache.log4j.Level.INFO;
        else if(level == Level.SEVERE)
            l = org.apache.log4j.Level.FATAL;
        else if (level == Level.WARNING)
            l = org.apache.log4j.Level.WARN;
        else
            l = org.apache.log4j.Level.DEBUG;
                
                
        
        Logger.getLogger(className).log( l, "");
        Logger.getLogger("").log( l, null, ex);
        Logger.getLogger("").log( l, "");
       
    }
}
