package new_investigation.reportGenerator.DataProcessing;

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

import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.QueryConfig;
import new_investigation.reportGenerator.FileHandler.XMLFileHandler;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.TemplateHelper.TriStringObject;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

@SuppressWarnings({"unchecked", "rawtypes"})
public class PGTemplateProcessing {
	
	public Map processingData(ResultEntryVO resultEntryVO) {
		Log(Level.INFO, "Fetch Requisiton eligible for Provisional printing");
    	ResultSet rs = null;
	    String query = "";
	    Map populateMAP = new HashMap();
	    Map xmlReportDoc= new HashMap();
	    
        String processingCRNo = null;
        Map<String, String> reportingCRNo = new HashMap<String, String>(5);

        ResultSet workorderdetailsQueryRC = null;
        Connection conn = null;

	    try {
       
        	PGDataHelper.getInstance();
			conn = PGDataHelper.getConnection();
            if (conn == null) {
                PGDataHelper.getInstance();
				conn = PGDataHelper.createPostgresConnection();          
            }
           
            if(conn==null || conn.isClosed()) {
            } else {
            query=QueryConfig.GET_REQUISITION_DTL_ELIGIBLE_FOR_PROVISIONAL_PRINTING;
            
            populateMAP.put(1, resultEntryVO.getHospitalCode());
            processingCRNo=resultEntryVO.getPatCRNo();
            populateMAP.put(2, processingCRNo);
            populateMAP.put(3, resultEntryVO.getRequisitionNo());

            rs = executeQuery(conn, query, populateMAP);
            }
            
            if (rs.next()) {
            	rs.beforeFirst();
                workorderdetailsQueryRC = rs;

                List<ResultEntryVOGroupByValidatedBy> entryVOGroupByValidatedBies = new ArrayList<ResultEntryVOGroupByValidatedBy>();
                resultsetProcessingForGroupingWorkOrder(workorderdetailsQueryRC, entryVOGroupByValidatedBies);

                if (workorderdetailsQueryRC != null) {
                    workorderdetailsQueryRC.close();
                }

                entryVOGroupByValidatedBies = TemplateProcessingUSE.groupSelectedWorkOrders(entryVOGroupByValidatedBies);

                for (int i = 0; i < entryVOGroupByValidatedBies.size(); i++) {
                    ResultEntryVOGroupByValidatedBy voGroupByValidatedBy = entryVOGroupByValidatedBies.get(i);

                    boolean isForPrinting = true;
                    voGroupByValidatedBy.setIsGroupUpdateble("true");

                    for (int workorderIndex = 0; workorderIndex < voGroupByValidatedBy.getResultEntryVOListValidatedBy().size(); workorderIndex++) {
                        ResultEntryVO entryVO = voGroupByValidatedBy.getResultEntryVOListValidatedBy().get(workorderIndex);
                        entryVO.setIsWorkOrderUpdateble("true");
                        if (!isForPrinting && entryVO.getUpdateType().equals("2")) {
                            isForPrinting = true;
                        }
                    }

                    if (isForPrinting && !reportingCRNo.containsKey(voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode())) {
                        reportingCRNo.put(voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode(), voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode());
                        if (voGroupByValidatedBy.getLaboratoryCode() != null) {
                            voGroupByValidatedBy.getLaboratoryCode().length();
                        }

                        if (voGroupByValidatedBy.getPatCRNo() != null) {
                            voGroupByValidatedBy.getPatCRNo().length();
                        }
                    }
                }

                xmlReportDoc=XMLFileHandler.updatePatientInvestigationFile(entryVOGroupByValidatedBies);

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
        } finally {
            try {
                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return xmlReportDoc;
    }

    
	private static ResultSet executeQuery(Connection _conn, String _query, Map _populateMap) throws Exception
	{
		ResultSet rs = null;
		//rs.TYPE_SCROLL_SENSITIVE;
		PreparedStatement pst = null;
		pst = _conn.prepareStatement(_query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		//Log(Level.INFO, "inside executeQuery() of class HelperMethodsDAO");
		//Log(Level.INFO, "size of map::" + _populateMap.size());
		StringBuffer stb = new StringBuffer(_query);
		Set set = _populateMap.keySet();
		Iterator itr = set.iterator();

		java.util.ArrayList al = new java.util.ArrayList();
		for (int i = 0; i < set.size(); i++)
		{
			al.add(null);
		}
		
		while (itr.hasNext())
		{
			Integer objKey = (Integer) itr.next();
			String val = (String) _populateMap.get(objKey);

			if(val==null || val.trim().equals(""))
				pst.setNull(objKey.intValue(), Types.NULL);
			else
				pst.setString(objKey.intValue(), val);
			al.set(objKey.intValue() - 1, val); 
				
		}

		String[] value = new String[]
		{};
		value = (String[]) al.toArray(value);
		for (int i = 0; i < value.length; i++)
		{
			int x = stb.indexOf("?");
			if (value[i] == null) stb.replace(x, x + 1, "null");
			else if (value[i].equals("")) stb.replace(x, x + 1, "\' \'");
			else stb.replace(x, x + 1, value[i]);

		}
		try{
		rs = pst.executeQuery();
		}catch(Exception e){
		e.printStackTrace();
		}
		return rs;
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

        return workOrderGroupListForresultEntry;
    }

    private void Log(Level level, String msg) {
        ServiceLogger.Log(PGTemplateProcessing.class.getName(), level, msg);
    }

    private void Log(Level level, Exception e) {
        ServiceLogger.Log(PGTemplateProcessing.class.getName(), level, e);
    }

}
