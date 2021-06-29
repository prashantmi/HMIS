package mongoapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
import DataProcessing.PGPDFProcessing;
import DataProcessing.PGTemplateProcessing;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

public class XMLGeneratorService {

    private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable xmlGenerator = new Runnable() {
            public void run() {

                System.out.println("beepXML");
                PGTemplateProcessing pgtp = new PGTemplateProcessing();

                pgtp.processingData();
                
            }
        };

        final ScheduledFuture<?> xmlGeneratorHandler
                = scheduler.scheduleAtFixedRate(xmlGenerator, 10, 30, SECONDS);
        scheduler.execute(new Runnable() {
            public void run() {
                xmlGeneratorHandler.cancel(true);
            }});
/*.schedule(new Runnable() {
            public void run() {
                xmlGeneratorHandler.cancel(true);
            }
        }, 60 * 60, SECONDS);*/
    }
}
