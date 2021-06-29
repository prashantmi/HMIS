// 
// Decompiled by Procyon v0.5.36
// 

package mongoapp;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import DataHelper.PGDataHelper;
import org.quartz.JobExecutionException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.quartz.JobExecutionContext;
import org.quartz.Job;

public class QSMSJob__forecefulltstart implements Job
{
    @Override
    public void execute(final JobExecutionContext jec) throws JobExecutionException {
        System.out.println("beep....====================================== ");
        final String s = null;
        final boolean flg = false;
        try {
            final String val = this.processingData();
            if (val != null && val.equals("1")) {
                this.updatedata();
                final Process p = Runtime.getRuntime().exec("./investigationservice.sh");
                final BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                final BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                System.out.println("Hereeeee is the standard output of the command:\n");
                System.out.println("Here is the standard error of the command (if any):\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String processingData() {
        boolean jobprocessContinue = false;
        String processingData = null;
        final String processinglaboratoryCode = null;
        final String hospitalCode = null;
        final String isDeptEntry = null;
        Connection conn = null;
        try {
            PGDataHelper.getInstance();
            conn = PGDataHelper.getConnection();
            if (conn == null) {
                PGDataHelper.getInstance();
                conn = PGDataHelper.createPostgresConnection();
            }
            final String query = "select hivnum_report_start_flag from hivt_report_start where gnum_isvalid=1 limit 1";
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(query);
            final ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                processingData = rs.getString(1);
            }
            rs.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            jobprocessContinue = false;
            try {
                conn.close();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
            return processingData;
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        try {
            conn.close();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        return processingData;
    }
    
    public String updatedata() {
        boolean jobprocessContinue = false;
        final String processingData = null;
        final String processinglaboratoryCode = null;
        final String hospitalCode = null;
        final String isDeptEntry = null;
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            PGDataHelper.getInstance();
            conn = PGDataHelper.getConnection();
            if (conn == null) {
                PGDataHelper.getInstance();
                conn = PGDataHelper.createPostgresConnection();
            }
            final String query = "update hivt_report_start set gnum_isvalid=0";
            cstmt = conn.prepareCall(query);
            cstmt.executeUpdate();
            if (cstmt != null) {
                cstmt.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            jobprocessContinue = false;
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
            return processingData;
        }
        finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }
                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            }
            catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (cstmt != null) {
                cstmt.close();
            }
            if (conn != null) {
                conn.commit();
                conn.close();
            }
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }
        return processingData;
    }
}
