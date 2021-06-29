/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.PGDataHelper;
import DataHelper.QueryConfig;
import DataHelper.ServiceConfiguration;
import FileHandler.XMLFileHandler;
import Logging.ServiceLogger;
import TemplateHelper.TriStringObject;
import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import TemplateHelper.vo.UserVO;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class PGTemplateProcessing {

    public boolean processingData() {
        boolean jobprocessContinue = false;
       // Log(Level.INFO, "Processing to generate Patient XML");
        String processingCRNo = null;
        String processingworkorder = null;
        Map<String, String> reportingCRNo = new HashMap<String, String>(5);

        ResultSet workorderdetailsQueryRC = null;
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
        	conn = PGDataHelper.getInstance().getConnection();
            if (conn == null) {
                conn = PGDataHelper.getInstance().createPostgresConnection();  
               
            }
           
            cstmt = conn.prepareCall(QueryConfig.P_GET_CRNO_XML_JOBS);
           
            cstmt.registerOutParameter(1, Types.REF);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            cstmt.execute();
            processingCRNo = cstmt.getString(3);
            processingworkorder = cstmt.getString(2);

            if (processingCRNo != null) {
               
            }

           
           
            if (processingCRNo != null) {
            	 Log(Level.INFO, "WorkOrderPrintingJobs processingCRNo:: " + processingCRNo + " processingworkorder ::" + processingworkorder);
                workorderdetailsQueryRC = (ResultSet) cstmt.getObject(1);

                if (workorderdetailsQueryRC == null) {
                    Log(Level.INFO, "Null Result Set");
                }
                List<ResultEntryVOGroupByValidatedBy> entryVOGroupByValidatedBies = new ArrayList<ResultEntryVOGroupByValidatedBy>();
                resultsetProcessingForGroupingWorkOrder(workorderdetailsQueryRC, entryVOGroupByValidatedBies);

                if (workorderdetailsQueryRC != null) {
                    workorderdetailsQueryRC.close();
                }

                if (cstmt != null) {
                    cstmt.close();
             
                }

                entryVOGroupByValidatedBies = TemplateProcessingUSE.groupSelectedWorkOrders(entryVOGroupByValidatedBies);

                for (int i = 0; i < entryVOGroupByValidatedBies.size(); i++) {
                    ResultEntryVOGroupByValidatedBy voGroupByValidatedBy = entryVOGroupByValidatedBies.get(i);

                    // changed to true 21/05/2015
                    //boolean isForPrinting = false;
                    boolean isForPrinting = true;
                    voGroupByValidatedBy.setIsGroupUpdateble("true");

                    for (int workorderIndex = 0; workorderIndex < voGroupByValidatedBy.getResultEntryVOListValidatedBy().size(); workorderIndex++) {
                        ResultEntryVO entryVO = voGroupByValidatedBy.getResultEntryVOListValidatedBy().get(workorderIndex);
                        entryVO.setIsWorkOrderUpdateble("true");
                        if (!isForPrinting && entryVO.getUpdateType().equals("2")) {
                            isForPrinting = true;
                        }
                    }

                    int labCodeLength = 0;
                    int crNoLength = 0;
                    if (isForPrinting && !reportingCRNo.containsKey(voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode())) {
                        reportingCRNo.put(voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode(), voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode());
                        if (voGroupByValidatedBy.getLaboratoryCode() != null) {
                            labCodeLength = voGroupByValidatedBy.getLaboratoryCode().length();
                        }

                        if (voGroupByValidatedBy.getPatCRNo() != null) {
                            crNoLength = voGroupByValidatedBy.getPatCRNo().length();
                        }
                    }

                }

                XMLFileHandler.updatePatientInvestigationFile(entryVOGroupByValidatedBies);

                jobprocessContinue = true;
                UserVO userVO = new UserVO();
                userVO.setHospitalCode("100");
                Log(Level.INFO, "reportingCRNo " + reportingCRNo);
                if (reportingCRNo != null && reportingCRNo.size() != 0) {
                    //    PGDataHelper.getInstance().processingToPutTheJobs(userVO, reportingCRNo, null, null, labCodeLength, crNoLength);
                    PGDataHelper.getInstance().processingToPutTheJobs(entryVOGroupByValidatedBies);
                }

                //Deleting disabled for now
                /*PreparedStatement pstmt = conn.prepareStatement(QueryConfig.Q_DELETE_XML_JOBS);

               
                 pstmt.setString(1, processingCRNo);
               
                 pstmt.setString(2, processingworkorder);
               
                 pstmt.execute();*/
                //       LOGGER_INV.log(Level.INFO, processingworkorder + "ERROR MSG ::" + cstmt.getString(4));
            } else {
                jobprocessContinue = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log(Level.SEVERE, "PGTemplateProcessing: " + e.getMessage());
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
           // Log(Level.INFO, "Processing for generating XML Completed");

        }

        return jobprocessContinue;
    }

    private List<ResultEntryVOGroupByValidatedBy> resultsetProcessingForGroupingWorkOrder(ResultSet rs, List<ResultEntryVOGroupByValidatedBy> workOrderGroupListForresultEntry) {
        ResultEntryVO resultEntryVO = null;
        Map<String, ResultEntryVOGroupByValidatedBy> checkMap = new LinkedHashMap<String, ResultEntryVOGroupByValidatedBy>();
        ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy = null;
        try {

            while (rs.next()) {

                if (resultEntryVO == null) {

                    resultEntryVO = new ResultEntryVO(rs.getString("laboratorycode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                    DataHandler.populateVOfrmRS(resultEntryVO, rs);
                    resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                    DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                    if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                        resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                    }

                    resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
                    workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);

                    if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                        checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                    }

                } else {
                    if (((resultEntryVO.getRequisitionNo()).equals(rs.getString("requisitionNo")) == true) && (resultEntryVO.getLaboratoryCode().equals(rs.getString("laboratoryCode")) == true) && (resultEntryVO.getTestCode().equals(rs.getString("testCode")) == true)) {
                        if (resultEntryVO.getIsTestMultiSession() != null && resultEntryVO.getIsTestMultiSession().equals("1"))// if test is Multi session
                        {
                            //if test is sample re grouped
                            if ((resultEntryVO.getSampleGroupCode() != null) && (resultEntryVO.getSampleGroupCode().equals(rs.getString("sampleGroupCode")) == true)) {
                                if (resultEntryVO.getIsMultiSessionRegrouped().equals("0"))//grouping is not achieved
                                {
                                    if (resultEntryVO.getSessionId().equals(rs.getString("sessionId"))) {
                                        if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                            resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                        }

                                        resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));

                                    } else {

                                        //resultEntryVO=new ResultEntryVO(); caching implementation 
                                        resultEntryVO = new ResultEntryVO(rs.getString("laboratoryCode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));

                                        DataHandler.populateVOfrmRS(resultEntryVO, rs);

                                        if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                            resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                                        } else {
                                            resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                            DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                            workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                            if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                                checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                            }
                                        }

                                        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                            resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                                        }

                                        resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                                    }
                                } else // grouping to be achieved
                                {
                                    if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                        resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                    }

                                    resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));

                                }

                            } else// test not raised with sample groupCode
                            {

                                if (resultEntryVO.getIsMultiSessionRegrouped() != null && resultEntryVO.getIsMultiSessionRegrouped().equals("0"))// grouping is not achieved
                                {

                                    //resultEntryVO=new ResultEntryVO(); caching implementation 
                                    resultEntryVO = new ResultEntryVO(rs.getString("laboratoryCode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                                    DataHandler.populateVOfrmRS(resultEntryVO, rs);

                                    if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                        resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                                    } else {
                                        resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                        DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                        workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                        if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                            checkMap.put(resultEntryVO.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                        }
                                    }

                                    if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                        resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                                    }

                                    resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                                } else // grouping to be achieved
                                {
                                    if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                        resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                    }

                                    resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));
                                }

                            }

                        } else //Test is not multisessioned
                        {
                            if ((resultEntryVO.getSampleGroupCode() != null) && (resultEntryVO.getSampleGroupCode().equals(rs.getString("sampleGroupCode")) == true)) {
                                if (resultEntryVO.getAssociatedWorkOrders() == null) {
                                    resultEntryVO.setAssociatedWorkOrders(new ArrayList<TriStringObject>());
                                }

                                resultEntryVO.getAssociatedWorkOrders().add(new TriStringObject(rs.getString("requisitionDNo"), rs.getString("sampleNo"), rs.getString("sessionId")));
                            } else {

                                //resultEntryVO=new ResultEntryVO(); caching implementation 
                                resultEntryVO = new ResultEntryVO(rs.getString("laboratoryCode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                                DataHandler.populateVOfrmRS(resultEntryVO, rs);
                                if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                                    resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                                } else {
                                    resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                                    DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                                    workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                                    if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                        checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                                    }
                                }

                                if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                                    resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                                }

                                resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                            }
                        }

                    } else {

                        //resultEntryVO=new ResultEntryVO(); caching implementation 
                        resultEntryVO = new ResultEntryVO(rs.getString("laboratoryCode"), rs.getString("testcode"), rs.getString("groupcode"), rs.getString("hospitalcode"), rs.getString("samplecode"));
                        DataHandler.populateVOfrmRS(resultEntryVO, rs);
                        if (checkMap.containsKey(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode())) {
                            resultEntryVOGroupByValidatedBy = checkMap.get(resultEntryVO.getRequisitionNo() + resultEntryVO.getLaboratoryCode() + resultEntryVO.getGroupCode());
                        } else {
                            resultEntryVOGroupByValidatedBy = new ResultEntryVOGroupByValidatedBy();
                            DataHandler.populate(resultEntryVOGroupByValidatedBy, resultEntryVO);
                            workOrderGroupListForresultEntry.add(resultEntryVOGroupByValidatedBy);
                            if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
                                checkMap.put(resultEntryVOGroupByValidatedBy.getRequisitionNo() + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode(), resultEntryVOGroupByValidatedBy);
                            }
                        }

                        if (resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy() == null) {
                            resultEntryVOGroupByValidatedBy.setResultEntryVOListValidatedBy(new ArrayList<ResultEntryVO>());
                        }

                        resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // LOGGER_INV.log(Level.INFO, "END" + workOrderGroupListForresultEntry.size());
        return workOrderGroupListForresultEntry;
    }

    private void Log(Level level, String msg) {
        ServiceLogger.Log(PGTemplateProcessing.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
        ServiceLogger.Log(PGTemplateProcessing.class.getName(), level, e);
    }

}
