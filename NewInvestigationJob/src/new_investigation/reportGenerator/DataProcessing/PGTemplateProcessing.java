/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_investigation.reportGenerator.DataProcessing;

import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.QueryConfig;
/*import new_investigation.reportGenerator.DataHelper.ServiceConfiguration;*/
import new_investigation.reportGenerator.FileHandler.XMLFileHandler;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.TemplateHelper.TriStringObject;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import new_investigation.reportGenerator.TemplateHelper.vo.UserVO;
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
@SuppressWarnings({"unused", "unchecked", "rawtypes"})
public class PGTemplateProcessing {
	
	
	public static ResultSet executeQuery(Connection _conn, String _query, Map _populateMap) throws Exception
	{
		ResultSet rs = null;
		//rs.TYPE_SCROLL_SENSITIVE;
		PreparedStatement pst = null;
		pst = _conn.prepareStatement(_query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		//System.out.println("inside executeQuery() of class HelperMethodsDAO");
		//System.out.println("size of map::" + _populateMap.size());
		StringBuffer stb = new StringBuffer(_query);
		Set set = _populateMap.keySet();
		//System.out.println("sd2");
		Iterator itr = set.iterator();
		//System.out.println("ww2");
		System.out.println("size of map::" + _populateMap.size());
		java.util.ArrayList al = new java.util.ArrayList();
		for (int i = 0; i < set.size(); i++)
		{
			al.add(null);
		}
		//int i=1;
		while (itr.hasNext())
		{
			//System.out.println("SDSDsdd");
			Integer objKey = (Integer) itr.next();
			//objKey.getClass();
			//System.out.println("SDSDsdd");
			String val = (String) _populateMap.get(objKey);
			//System.out.println("SDSDsdd1");
			//System.out.println("[" + objKey.intValue() + ", " + val + "]");

			if(val==null || val.trim().equals("")) // Added By Pragya
				pst.setNull(objKey.intValue(), Types.NULL);
			else
				pst.setString(objKey.intValue(), val);
			//pst.setString(objKey.intValue(), val);
			al.set(objKey.intValue() - 1, val); //<><<<<<<<
			//i++;	
		}

		String[] value = new String[]
		{};
		value = (String[]) al.toArray(value);
		for (int i = 0; i < value.length; i++)
		{
			//System.out.println("value["+i+"]"+value[i]);
			int x = stb.indexOf("?");
			if (value[i] == null) stb.replace(x, x + 1, "null");
			else if (value[i].equals("")) stb.replace(x, x + 1, "\' \'");
			else stb.replace(x, x + 1, value[i]);

		}
		System.out.println("stb....  " + stb);

		//System.out.println("al:   +   " + al); //<><<<<<<
		//System.out.println("after setting values for prepare st. in class helpermethodsDAo");
		try{
		rs = pst.executeQuery();
		}catch(Exception e){
		e.printStackTrace();
		}
		//System.out.println("after executing query");
		return rs;
	}

	
    
	public boolean processingData(ResultEntryVO resultEntryVO) {
    	ResultSet rs = null;
	    String query = "";
	    Map populateMAP = new HashMap();
	    
        boolean jobprocessContinue = false;
       // Log(Level.INFO, "Processing to generate Patient XML");
        String processingCRNo = null;
        String processingworkorder = null;
        Map<String, String> reportingCRNo = new HashMap<String, String>(5);

        ResultSet workorderdetailsQueryRC = null;
        Connection conn = null;
        //CallableStatement cstmt = null;

	    try {
        

        	conn = PGDataHelper.getInstance().getConnection();
            if (conn == null) {
                conn = PGDataHelper.getInstance().createPostgresConnection();  
               
            }
           
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            	
            
           // cstmt = conn.prepareCall(QueryConfig.GET_CR_DTL_XML_JOBS);
            query=QueryConfig.GET_CR_DTL_XML_JOBS;
            
            populateMAP.put(1, resultEntryVO.getHospitalCode());
            processingCRNo=resultEntryVO.getPatCRNo();
            populateMAP.put(2, processingCRNo);
            populateMAP.put(3, resultEntryVO.getRequisitionNo());

            rs = executeQuery(conn, query, populateMAP);
            
            /*
            cstmt.registerOutParameter(1, Types.REF);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);
            */
            //cstmt.execute();
          
            //processingCRNo = cstmt.getString(3);
            //processingworkorder = cstmt.getString(2);
            
            //processingCRNo = rs.getString(3);
            //processingworkorder = rs.getString(2);
            }
            

           
           
            //if (processingCRNo != null ) {
              if (rs.next()) {
	  	        rs.beforeFirst();
            
            	System.out.println("xml start  for Crno:"+processingCRNo);
            	Log(Level.INFO, "xml start for Crno: " + processingCRNo );
            	//	 Log(Level.INFO, "WorkOrderPrintingJobs processingCRNo:: " + processingCRNo + " processingworkorder ::" + processingworkorder);
                //workorderdetailsQueryRC = (ResultSet) cstmt.getObject(1);
                workorderdetailsQueryRC = rs;

                if (workorderdetailsQueryRC == null) {
                	System.out.println("Null Result Set fro crno: "+processingCRNo);
                    Log(Level.INFO, "Null Result Set fro crno: "+processingCRNo);

                }
                List<ResultEntryVOGroupByValidatedBy> entryVOGroupByValidatedBies = new ArrayList<ResultEntryVOGroupByValidatedBy>();
                resultsetProcessingForGroupingWorkOrder(workorderdetailsQueryRC, entryVOGroupByValidatedBies);

                if (workorderdetailsQueryRC != null) {
                    workorderdetailsQueryRC.close();
                }

               /* if (cstmt != null) {
                    cstmt.close();
             
                }
                */

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
        
                System.out.println("xml end for crno: "+processingCRNo);
                Log(Level.INFO, " xml end for crno: "+processingCRNo);
                
            
            } else {
                jobprocessContinue = false;
            }
            
          
            

        } catch (Exception e) {
            e.printStackTrace();
            Log(Level.SEVERE, "PGTemplateProcessing: " + e.getMessage());
            try {
            	if(conn!=null && !conn.isClosed())
                conn.rollback();
            } catch (SQLException ex) {
                Log(Level.SEVERE, ex);
            }
            jobprocessContinue = false;
        } finally {
            try {
            	/*
                if (cstmt != null) {
                    cstmt.close();
                }
					*/
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
