/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.PGDataHelper;
import DataHelper.PropertiesHelper;
import DataHelper.QueryConfig;
import Logging.ServiceLogger;
import SMSSender.SMSHttpPostClient;
import SMSSender.SMSHttpsNICPostClient;
import SMSSender.config.SMSConfig;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class SMSProcessing {

    public boolean processingData() {
        boolean jobprocessContinue = false;
        Log(Level.INFO, "Processing to SEND SMS TO Patient");
  
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
        	conn = PGDataHelper.getInstance().getConnection();
            if (conn == null) {
                conn = PGDataHelper.getInstance().createPostgresConnection();  
               
            }
                                
            PreparedStatement pstmt = null;
            List<ResultEntryVOGroupByValidatedBy> lstSMSInfo = new ArrayList<ResultEntryVOGroupByValidatedBy>();

            //get req no for which report has been generated
            String query =(QueryConfig.Q_GET_REQ_SMS_DATA);
            pstmt = conn.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
           
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next())
            {
            	;
            }
            else
            {
                rs.beforeFirst();
                while (rs.next()) {
                	
                	
                	/*voCanTest=new ResultEntryVOGroupByValidatedBy();
                    DataHandler.populateVOfrmRS(voCanTest, rs);
                    lstSMSInfo.add(voCanTest);*/
                	
                	
                	String reqNo=rs.getString(1);
                	String mobileNo=rs.getString(2);
                	String countReqDno=rs.getString(3);
                	String countReportGen=rs.getString(4);
                	String labName=rs.getString(5);
                	String patName=rs.getString(6);
                	String addendum=rs.getString(7);
                	
                	if(countReportGen.equals(countReqDno))
                		{
                		SMSConfig objSMSConfig=new SMSConfig();
            			String   message  = "Hello "+patName+", your report for the Lab: "+labName+" has been successfully generated. Please collect at your convenience. Thanks and Regards. NIMS";
            		
            			if(addendum!=null)
            				 message  = "Hello "+patName+", your AMENDED-ADDENDED report for the Lab: "+labName+" has been successfully generated. Please collect at your convenience. Thanks and Regards. NIMS";
            			if(mobileNo!=null)
            				SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(PropertiesHelper.getSMSUserName(), PropertiesHelper.getSMSPassword(),PropertiesHelper.getSMSId(),PropertiesHelper.getSMSUrl(),mobileNo,message);
            			//SMSHttpPostClient.sendSingleSMSThroughSMSGateway(PropertiesHelper.getSMSUserName(), PropertiesHelper.getSMSPassword(),PropertiesHelper.getSMSId(),PropertiesHelper.getSMSUrl(), mobileNo,message);
            			
            			
            			//code from sending message through NIC SMS Gateway
            			//SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(PropertiesHelper.getSMSUserName(), PropertiesHelper.getSMSPassword(),PropertiesHelper.getSMSId(),PropertiesHelper.getSMSUrl(),mobileNo,message);
            			
            			
            			PreparedStatement pstmt2 = conn.prepareStatement(QueryConfig.Q_UPDATE_REQ_SMS_DATA);
                        pstmt2.setString(1, reqNo);
                        pstmt2.executeUpdate();
                		}
                	else
                		;//do nothing
                
                  }
                if (pstmt != null) {
                    pstmt.close();
                }
                conn.commit();
               
            }
           
     

        } catch (Exception e) {
            e.printStackTrace();
            Log(Level.SEVERE, "SMSProcessing: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Log(Level.SEVERE, ex);
            }
            jobprocessContinue = false;
        } finally {
            try {
                if (cstmt != null) {
                    cstmt.close();
                }

                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log(Level.INFO, "SMS Processing complete");

        }

        return jobprocessContinue;
    }

    private void Log(Level level, String msg) {
        ServiceLogger.Log(PGTemplateProcessing.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
        ServiceLogger.Log(PGTemplateProcessing.class.getName(), level, e);
    }

}
