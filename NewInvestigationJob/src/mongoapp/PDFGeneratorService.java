/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package mongoapp;

import DataProcessing.PGPDFProcessing;
import DataProcessing.PGTemplateProcessing;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;

/***************************Start of program*****************************
 ## Copyright Information			: C-DAC, Noida  
 ## Project Name				: CCD SDK
 ## Name of Developer		 		: Siddharth Srivastava
 ## Module Name					: Health Standards
 ## Process/Database Object Name	        : 
 ## Purpose                                     :
 ## Date of Creation				: 
 ## Modification Log				:				
 ##		Modify Date			: 
 ##		Reason	(CR/PRS)		: 
 ##		Modify By			: 
*/

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class PDFGeneratorService {
private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable pdfGenerator = new Runnable() {
            public void run() {

                System.out.println("beepPDF");
               
                PGPDFProcessing ppp = new PGPDFProcessing();
                ppp.processingData();
            }
        };

        final ScheduledFuture<?> pdfGeneratorHandler
                = scheduler.scheduleAtFixedRate(pdfGenerator, 15, 60, SECONDS);
        
        scheduler.execute(new Runnable() {
            public void run() {
                pdfGeneratorHandler.cancel(true);
            }});
        
       /* scheduler.schedule(new Runnable() {
            public void run() {
                pdfGeneratorHandler.cancel(true);
            }
        }, 60 * 60, SECONDS);*/
    }
}
