/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoapp;

import DataProcessing.PGTemplateProcessing;
import MongoHelper.MongoXmlHandler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * *************************Start of program***************************** ##
 * Copyright Information	: C-DAC, Noida ## Project Name	: CCD SDK ## Name of
 * Developer	: Siddharth Srivastava ## Module Name	: Health Standards ##
 * Process/Database Object Name	: ## Purpose : ## Date of Creation	: ##
 * Modification Log	: ##	Modify Date	: ##	Reason	(CR/PRS)	: ##	Modify By	:
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class QXMLJob implements Job {

    //static int i = 0;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("beepXML ");
        boolean jobProcessContinue = true;
        PGTemplateProcessing pgtp = new PGTemplateProcessing();
              
        while (jobProcessContinue) {
        //	MongoXmlHandler.getInstance(); 
            jobProcessContinue = pgtp.processingData();
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
