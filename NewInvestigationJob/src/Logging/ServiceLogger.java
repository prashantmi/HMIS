/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logging;

import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import DataHelper.ServiceConfiguration;
import java.io.IOException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
/*import java.util.logging.FileHandler;

import java.util.logging.Logger;
import sun.net.www.protocol.jar.Handler;
*/
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class ServiceLogger {

    //public static FileHandler fh;
    static boolean  isDebug = true;
    public static void Log(String className, Level level, String msg) {
        
        isDebug = PropertiesHelper.getIsDebugConfiguration();
        if(isDebug)
            System.out.println(msg);
        //FileHandler fh = null;
        /*try {

            //fh = new FileHandler(ServiceConfiguration.ServiceLogPath);
           // Logger.getLogger(className).addHandler(fh);
            // }
        } catch (IOException ex) {
            //  Logger.getLogger(ServiceLogger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (SecurityException ex) {
            //  Logger.getLogger(ServiceLogger.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }*/
        
        org.apache.log4j.Level l;
        if (level ==  Level.INFO)
                l = org.apache.log4j.Level.INFO;
        else if(level == Level.SEVERE)
            l = org.apache.log4j.Level.FATAL;
        else if (level == Level.WARNING)
            l = org.apache.log4j.Level.WARN;
        else
            l = org.apache.log4j.Level.DEBUG;
                
                
                
        
        
        Logger.getLogger(className).log( l, msg);
       // Logger.getLogger("df").addAppender(null);
       /* try {
            Logger.getLogger(className).removeHandler(fh);
            if (fh != null) {
                fh.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    public static void Log(String className, Level level, Exception ex) {
         isDebug = PropertiesHelper.getIsDebugConfiguration();
        if(isDebug)
            ex.printStackTrace();
        //FileHandler fh = null;
        /*try {
            {
              //  fh = new FileHandler(ServiceConfiguration.ServiceLogPath);
             //   Logger.getLogger(className).addHandler(fh);

            }
        } catch (IOException ex1) {
            // Logger.getLogger(ServiceLogger.class.getName()).log(Level.SEVERE, null, ex1);
            System.out.println(ex1.getMessage());
        } catch (SecurityException ex1) {
            // Logger.getLogger(ServiceLogger.class.getName()).log(Level.SEVERE, null, ex1);
            System.out.println(ex1.getMessage());
        }*/
         org.apache.log4j.Level l;
        if (level ==  Level.INFO)
                l = org.apache.log4j.Level.INFO;
        else if(level == Level.SEVERE)
            l = org.apache.log4j.Level.FATAL;
        else if (level == Level.WARNING)
            l = org.apache.log4j.Level.WARN;
        else
            l = org.apache.log4j.Level.DEBUG;
                
                
        Logger.getLogger(className).log( l, null, ex);
      /*  try {
            Logger.getLogger(className).removeHandler(fh);
            if (fh != null) {
                fh.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
