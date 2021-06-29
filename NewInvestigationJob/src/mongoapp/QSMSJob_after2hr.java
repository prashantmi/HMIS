// 
// Decompiled by Procyon v0.5.36
// 

package mongoapp;

import org.quartz.JobExecutionException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.quartz.JobExecutionContext;
import org.quartz.Job;

public class QSMSJob_after2hr implements Job
{
    @Override
    public void execute(final JobExecutionContext jec) throws JobExecutionException {
        System.out.println("beep....====================================== ");
        final String s = null;
        final boolean flg = false;
        try {
            final Process p = Runtime.getRuntime().exec("./investigationservice.sh");
            final BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            final BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            System.out.println("Hereeeee is the standard output of the command:\n");
            System.out.println("Here is the standard error of the command (if any):\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
