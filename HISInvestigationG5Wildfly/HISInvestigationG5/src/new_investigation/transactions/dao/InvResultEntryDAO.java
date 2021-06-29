package new_investigation.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.machineResultEntryVO;
import new_investigation.vo.template.ResultEntryVO;


public class InvResultEntryDAO
  extends DataAccessObject
{
  public InvResultEntryDAO(JDBCTransactionContext _tx) { super(_tx); }





  
  public List LabComboForResultEntry(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List parameterCombo = new ArrayList();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_LAB_COMBO_FOR_RESULT_ENTRY_HIVT_LABORATORY_MST";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMap.put(sq.next(), _UserVO.getHospitalCode());
    populateMap.put(sq.next(), "15");
    populateMap.put(sq.next(), _UserVO.getUserSeatId());
    populateMap.put(sq.next(), _UserVO.getHospitalCode());



    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      if (!rs.next())
      {
        throw new HisRecordNotFoundException();
      }

      
      rs.beforeFirst();

      
      parameterCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return parameterCombo;
  }




  
  public List<ResultEntryVO> showCannedDetails(String labCode, String cannedMacroCheck, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List<ResultEntryVO> resultentryvo = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_CANNED_DETAILS_RESULT_ENTRY_HIVT_LAB_CANNED_MST";
    String queryKey1 = "SELECT_MACRO_DETAILS_RESULT_ENTRY_HIVT_LAB_MACRO_MAPPING_MST";
    Sequence sq = new Sequence();




    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    populateMAP.put(sq.next(), labCode);
    populateMAP.put(sq.next(), _UserVO.getUserId());
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    populateMAP.put(sq.next(), labCode);
    populateMAP.put(sq.next(), _UserVO.getUserId());

    
    try {
      if (cannedMacroCheck.equals("CANNED"))
      {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      }
      if (cannedMacroCheck.equals("MACRO"))
      {
        query = HelperMethodsDAO.getQuery(filename, queryKey1);
      
      }
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
          populateMAP);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("No Test / Lab  Found");
      }

      
      rs.beforeFirst();
      resultentryvo = new ArrayList<ResultEntryVO>();
      ResultEntryVO voCanTest = null;
      while (rs.next()) {
        voCanTest = new ResultEntryVO();
        HelperMethods.populateVOfrmRS(voCanTest, rs);
        resultentryvo.add(voCanTest);
      
      }

    
    }
    catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    } 
    return resultentryvo;
  }


  
  public List<ResultEntryVO> setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String fromAlpha = "";
    String toAlpha = "";
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    
    String queryKey = "";
    
    if (!invresultentryvo.getSampleAreaCode().equals("-2")) {
      queryKey = "SELECT.RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    } else {
      queryKey = "SELECT.RESULT_ENTRY_HIVT_REQUISITION_HEADER_ALL";
    } 
    String queryKeyCollDate = "";
    
    if (!invresultentryvo.getSampleAreaCode().equals("-2")) {
      queryKeyCollDate = "SELECT.RESULT_ENTRY_COLL_DATE_HIVT_REQUISITION_HEADER";
    } else {
      queryKeyCollDate = "SELECT.RESULT_ENTRY_COLL_DATE_HIVT_REQUISITION_HEADER_ALL";
    } 
    String condition5 = "And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)=" + invresultentryvo.getPatCRNo();
    String searchPatName = " And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%" + invresultentryvo.getTempPatName() + "%'";
    String condition1 = " AND gnum_test_code =" + invresultentryvo.getTestWiseCode();

    
    String condition2 = "";
    String orderByCondition = "";
    if (invresultentryvo.getFromSampleNo() != null && invresultentryvo.getToSampleNo() != null) {
      fromAlpha = invresultentryvo.getFromSampleNo().replaceAll("[^A-Za-z]+", "");
      toAlpha = invresultentryvo.getToSampleNo().replaceAll("[^A-Za-z]+", "");
      if (fromAlpha.equalsIgnoreCase(toAlpha)) {
        condition2 = "AND (cast (regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g') as numeric)) >= (cast (regexp_replace('" + invresultentryvo.getFromSampleNo() + "','[^[:digit:]]','','g') as numeric)) AND (cast (regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g') as numeric)) <= (cast (regexp_replace('" + invresultentryvo.getToSampleNo() + "','[^[:digit:]]','','g')  as numeric)) AND hivnum_temp_sample_no like '%" + fromAlpha + "%'";
        
        orderByCondition = " order by hivnum_temp_sample_no,preferenceOrder,grp,hivdt_coll_date_time desc";
      } else {
        condition2 = "AND (cast (regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g') as numeric)) >= (cast (regexp_replace('" + invresultentryvo.getFromSampleNo() + "','[^[:digit:]]','','g') as numeric)) AND (cast (regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g') as numeric)) <= (cast (regexp_replace('" + invresultentryvo.getToSampleNo() + "','[^[:digit:]]','','g')  as numeric))";
        orderByCondition = " order by hivnum_temp_sample_no,preferenceOrder,grp,hivdt_coll_date_time desc";
      } 
    } else {
      
      condition2 = "AND (cast (regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g') as numeric)) >= (cast (regexp_replace('" + invresultentryvo.getFromSampleNo() + "','[^[:digit:]]','','g') as numeric)) AND (cast (regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g') as numeric)) <= (cast (regexp_replace('" + invresultentryvo.getToSampleNo() + "','[^[:digit:]]','','g')  as numeric))";
      orderByCondition = " order by hivnum_temp_sample_no,preferenceOrder,grp,hivdt_coll_date_time desc";
    } 



    
    String condition3 = "AND (cast (regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g') as numeric)) >= (cast (regexp_replace('" + invresultentryvo.getFromLabNo() + "','[^[:digit:]]','','g')   as numeric)) AND (cast (regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g') as numeric)) <= (cast (regexp_replace('" + invresultentryvo.getToLabNo() + "','[^[:digit:]]','','g')  as numeric))";
    String condition4 = " AND hgnum_group_code =" + invresultentryvo.getTestGroupCode();
    
    String reqDtlValue = "";
    String conditionLab = "";







    
    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (7,13,26) ";
    }
    else if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("3")) {
      reqDtlValue = " and hivnum_reqdtl_status in (27) ";
    } else {
      reqDtlValue = " and hivnum_reqdtl_status in (6) ";
    } 
    if (invresultentryvo.getLabCode() == null) {
      invresultentryvo.setLabCode("%");
    }
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and gnum_lab_code like '%' ";
    } else {
      conditionLab = " and gnum_lab_code=" + invresultentryvo.getLabCode();
    } 


    
    try {
      if (invresultentryvo.getSearchBy().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKeyCollDate);
      } 
    } catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();


    
    try {
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

      
      populateMAP.put(sq.next(), "15");
      populateMAP.put(sq.next(), _UserVO.getUserSeatId());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

      
      if (invresultentryvo.getSearchBy().equals("1")) {
        populateMAP.put(sq.next(), invresultentryvo.getFromDate());
        populateMAP.put(sq.next(), invresultentryvo.getToDate());
      } else {
        populateMAP.put(sq.next(), invresultentryvo.getFromCollDate());
        populateMAP.put(sq.next(), invresultentryvo.getToCollDate());
      } 
      
      if (!invresultentryvo.getSampleAreaCode().equals("-2")) {
        populateMAP.put(sq.next(), invresultentryvo.getSampleAreaCode().contains("#") ? invresultentryvo.getSampleAreaCode().split("#")[0] : invresultentryvo.getSampleAreaCode());
      }





      
      populateMAP.put(sq.next(), _UserVO.getUserId());
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    PatientDetailVO[] dailyPatientVOs = null;
    List<ResultEntryVO> lstInvResultEntryVO = new ArrayList<ResultEntryVO>();
    List<ResultEntryVO> finalLstInvResultEntryVO = new ArrayList<ResultEntryVO>();
    ValueObject[] valueObjects = null;
    query = String.valueOf(query) + conditionLab;
    query = String.valueOf(query) + reqDtlValue;
    
    try {
      if (invresultentryvo.getPatCRNo() != null && !invresultentryvo.getPatCRNo().equals(""))
      {
        query = String.valueOf(query) + condition5;
      }
      
      if (invresultentryvo.getTempPatName() != null && !invresultentryvo.getTempPatName().equals(""))
      {
        query = String.valueOf(query) + searchPatName;
      }

      
      if (invresultentryvo.getGenerationType() != null && !invresultentryvo.getOnLoadValue().equals("ONLOAD")) {
        
        if (invresultentryvo.getGenerationType().equals("T") && !invresultentryvo.getTestWiseCode().equals("-1"))
        {
          query = String.valueOf(query) + condition1;
        }
        
        if (invresultentryvo.getGenerationType().equals("L") && !invresultentryvo.getFromLabNo().equals("-1") && !invresultentryvo.getToLabNo().equals("-1"))
        {
          
          query = String.valueOf(query) + condition3;
        }
        
        if (invresultentryvo.getGenerationType().equals("S") && !invresultentryvo.getFromSampleNo().equals("-1") && !invresultentryvo.getToSampleNo().equals("-1"))
        {
          query = String.valueOf(query) + condition2;
        }

        
        if (invresultentryvo.getGenerationType().equals("TG") && !invresultentryvo.getTestGroupCode().equals("-1") && !invresultentryvo.getTestGroupCode().equals(""))
        {
          query = String.valueOf(query) + condition4;
        }
      } 
      
      query = String.valueOf(query) + orderByCondition;
      
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next()) {




        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(ResultEntryVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++) {

          
          lstInvResultEntryVO.add((ResultEntryVO)valueObjects[i]);
          if (((ResultEntryVO)lstInvResultEntryVO.get(i)).getReqDtlStatus().equals("13") || ((ResultEntryVO)lstInvResultEntryVO.get(i)).getReqDtlStatus().equals("26"))
          {
            System.out.println("dnoooo" + ((ResultEntryVO)lstInvResultEntryVO.get(i)).getRequisitionDNo());
            if (((ResultEntryVO)lstInvResultEntryVO.get(i)).getReportAvailableAfter().equals("1"))
            {
              finalLstInvResultEntryVO.add((ResultEntryVO)lstInvResultEntryVO.get(i));
            }
          }
          else
          {
            finalLstInvResultEntryVO.add((ResultEntryVO)lstInvResultEntryVO.get(i));
          }
        
        } 
      } 
    } catch (Exception e) {
      
      if (e.getClass() != HisRecordNotFoundException.class) {


        
        e.printStackTrace();
        throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
      } 
    }  return finalLstInvResultEntryVO;
  }


  
  public void updateResultEntryInRequisitionDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.RESULT.ENTRY.HIVT_REQUISITION_DTLS";



    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 

    
    try {
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getReqDtlStatus());
    //  populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getVisitReason());
      String emcode = "0";
      if(voResultEntry.getCrNoReqNoString()!=null)
      for (int i = 0; i < voResultEntry.getCrNoReqNoString().length; i++) {
        
        if (voResultEntry.getCrNoReqNoString()[i].contains(voResultEntry.getRequisitionNo())) {
          emcode = voResultEntry.getEmpNameWise()[i];
        }
      } 
      populateMAP.put(sq.next(), emcode);

      
      populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
      
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }



























































  
  public void insertHyperLinkDetails(ResultEntryVO voResultEntry, UserVO _UserVO, String antibioticCode, String diagnosisval, String organismcode, String testparacode) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.ENTRY.HYPERLINK.HIVT_WO_ORGAANTIBIOTICS_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
      
      populateMAP.put(sq.next(), testparacode);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), organismcode);
      populateMAP.put(sq.next(), antibioticCode);
      populateMAP.put(sq.next(), diagnosisval);
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
      populateMAP.put(sq.next(), "1");





    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }



  
  public void insertResultEntryDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 


    
    try {
      String resultEntryvalue = voResultEntry.getResultEntryValue();
      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");


      
      String value = voResultEntry.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
      
      populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
      
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), voResultEntry.getParantParaCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), value);
      populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), voResultEntry.getLoincCode());
      populateMAP.put(sq.next(), voResultEntry.getStrRefRange());
      populateMAP.put(sq.next(), "0");
      
      populateMAP.put(sq.next(), (voResultEntry.getFileuploaddata() == null) ? "" : voResultEntry.getFileuploaddata());
      populateMAP.put(sq.next(), (voResultEntry.getFilename() == null) ? "" : voResultEntry.getFilename());







    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
    } 
  }




  
  public void insertResultEntryDtlInJobWorkorderData(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.ENTRY.DETAIL.HIVT_JOBWORKORDER_DATA";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 





    
    try {
      populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
      
      populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
      populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), voResultEntry.getLabCode());
      populateMAP.put(sq.next(), (voResultEntry.getSampleNo() == null) ? "" : voResultEntry.getSampleNo());
      populateMAP.put(sq.next(), (voResultEntry.getPatAge() == null) ? "" : voResultEntry.getPatAge());
      populateMAP.put(sq.next(), voResultEntry.getPatGender());
      
      populateMAP.put(sq.next(), (voResultEntry.getTestParaMeterCode() == null) ? "" : voResultEntry.getTestParaMeterCode());
      populateMAP.put(sq.next(), voResultEntry.getReportAvailableAfter());
      
      populateMAP.put(sq.next(), (voResultEntry.getResultEntryValue() == null) ? "" : voResultEntry.getResultEntryValue());
      populateMAP.put(sq.next(), voResultEntry.getPatVisitDat());
      populateMAP.put(sq.next(), voResultEntry.getPatVisitNo().equals("null") ? "" : voResultEntry.getPatVisitNo());
      populateMAP.put(sq.next(), voResultEntry.getLabNo());
      populateMAP.put(sq.next(), voResultEntry.getEpisodeCode());
      populateMAP.put(sq.next(), voResultEntry.getDepartmentcode());
      populateMAP.put(sq.next(), voResultEntry.getPatdeptunitcode());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionTypeCode());
      populateMAP.put(sq.next(), voResultEntry.getTestName());
      populateMAP.put(sq.next(), voResultEntry.getPatLabName());
      populateMAP.put(sq.next(), voResultEntry.getSampleName());
      populateMAP.put(sq.next(), voResultEntry.getTempSampleNo());
      
      if (voResultEntry.getDynamicTemplatePrintedGroup() != null && voResultEntry.getDynamicTemplatePrintedGroup().equals("1"))
      {
        populateMAP.put(sq.next(), voResultEntry.getGroupCode());
      }
      else
      {
        populateMAP.put(sq.next(), "");
      
      }

    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
    } 
  }





  
  public String getLoincCode(String passValue) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.TEMPLATE_PROCESSING.LOINC_CODE";
    
    String[] values = passValue.split("#");
    String testCode = values[0];
    String paraCode = values[1];
    String sampleCode = values[2];
    String uomCode = values[3];
    
    System.out.println("test code: " + testCode + " paraCode: " + paraCode);
    JDBCTransactionContext tx = new JDBCTransactionContext();
    tx.begin();
    Connection conn = tx.getConnection();
    String loincCode = null;
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);





      
      System.out.println("Query ---------> " + query);
      Sequence sq = new Sequence();

      
      populateMAP.put(sq.next(), testCode);
      populateMAP.put(sq.next(), paraCode);
      populateMAP.put(sq.next(), sampleCode);
      populateMAP.put(sq.next(), uomCode);








      
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      while (rs.next())
      {
        loincCode = rs.getString(1);
        System.out.println("loincCode found: " + loincCode);
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      if (e.getClass() == HisDataAccessException.class)
      {
        throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
      }
      if (e.getClass() == HisApplicationExecutionException.class)
      {
        throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    }
    finally {
      
      tx.close();
    } 
    return loincCode;
  }




  
  public List setLabNoComboEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List labNoCombo = new ArrayList();
    List finalLabNoCombo = new ArrayList();
    List<String> reportAvailAfter = new ArrayList<String>();
    List<String> reqStatus = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.LABNO_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.LABNO_COMBO_COLL_DATE_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String orderBy = " order by labNo";
    String reqDtlValue = "";
    String conditionLab = "";


    
    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (7,13,26) ";
    }
    else {
      
      reqDtlValue = " and hivnum_reqdtl_status in (6) ";
    } 

    
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and gnum_lab_code like '%' ";
    } else {
      conditionLab = " and gnum_lab_code=" + invresultentryvo.getLabCode();
    } 
    
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());





    
    if (invresultentryvo.getSearchBy().equals("1")) {
      populateMAP.put(sq.next(), invresultentryvo.getFromDate());
      populateMAP.put(sq.next(), invresultentryvo.getToDate());
    } else {
      populateMAP.put(sq.next(), invresultentryvo.getFromCollDate());
      populateMAP.put(sq.next(), invresultentryvo.getToCollDate());
    } 

    
    try {
      if (invresultentryvo.getSearchBy().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKeyCollDate);
      
      }
    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    query = String.valueOf(query) + conditionLab;
    query = String.valueOf(query) + reqDtlValue + orderBy;
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      if (!rs.next())
      {
        return labNoCombo;
      }

      
      rs.beforeFirst();
      labNoCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);

      
      rs.beforeFirst();
      while (rs.next()) {
        reportAvailAfter.add(rs.getString(3));
        reqStatus.add(rs.getString(4));
      } 
      
      for (int i = 0; i < reportAvailAfter.size(); i++)
      {

        
        if (((String)reqStatus.get(i)).contains("13") || ((String)reqStatus.get(i)).contains("26")) {
          
          if (((String)reportAvailAfter.get(i)).contains("1"))
          {
            if (!finalLabNoCombo.contains(labNoCombo.get(i))) {
              finalLabNoCombo.add(labNoCombo.get(i));
            
            }
          
          }
        }
        else if (!finalLabNoCombo.contains(labNoCombo.get(i))) {
          finalLabNoCombo.add(labNoCombo.get(i));
        
        }
      
      }
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        return labNoCombo;
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return finalLabNoCombo;
  }


  
  public List setTestComboEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List testCombo = new ArrayList();
    List finalTestCombo = new ArrayList();
    List<String> reportAvailAfter = new ArrayList<String>();
    List<String> reqStatus = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.TESTWISE_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.TESTWISE_COMBO_COLL_DATE_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String orderBy = "\torder by testName";
    String reqDtlValue = "";
    String conditionLab = "";


    
    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (7,13,26) ";
    }
    else {
      
      reqDtlValue = " and hivnum_reqdtl_status in (6) ";
    } 
    
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and gnum_lab_code like '%' ";
    } else {
      conditionLab = " and gnum_lab_code=" + invresultentryvo.getLabCode();
    } 


    
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());





    
    if (invresultentryvo.getSearchBy().equals("1")) {
      populateMAP.put(sq.next(), invresultentryvo.getFromDate());
      populateMAP.put(sq.next(), invresultentryvo.getToDate());
    } else {
      populateMAP.put(sq.next(), invresultentryvo.getFromCollDate());
      populateMAP.put(sq.next(), invresultentryvo.getToCollDate());
    } 


    
    try {
      if (invresultentryvo.getSearchBy().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKeyCollDate);
      }
    
    } catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    query = String.valueOf(query) + conditionLab;
    query = String.valueOf(query) + reqDtlValue + orderBy;
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      if (!rs.next())
      {
        return testCombo;
      }

      
      rs.beforeFirst();
      testCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);

      
      rs.beforeFirst();
      while (rs.next()) {
        reportAvailAfter.add(rs.getString(3));
        reqStatus.add(rs.getString(4));
      } 
      
      for (int i = 0; i < reportAvailAfter.size(); i++) {


        
        if (((String)reqStatus.get(i)).contains("13") || ((String)reqStatus.get(i)).contains("26"))
        {
          if (((String)reportAvailAfter.get(i)).contains("1"))
          {
            finalTestCombo.add(testCombo.get(i));
          
          }
        }
        else
        {
          finalTestCombo.add(testCombo.get(i));
        }
      
      } 
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        return testCombo;
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return finalTestCombo;
  }



  
  public List setSamplNoComboEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List sampleNoCombo = new ArrayList();
    List finalSampleNoCombo = new ArrayList();
    List<String> reportAvailAfter = new ArrayList<String>();
    List<String> reqStatus = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.SAMPLENO_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.SAMPLENO_COMBO_COLL_DATE_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String orderBy = "order by sampleNo";
    String reqDtlValue = "";
    String conditionLab = "";

    
    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (7,13,26) ";
    }
    else {
      
      reqDtlValue = " and hivnum_reqdtl_status in (6) ";
    } 
    
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and gnum_lab_code like '%' ";
    } else {
      conditionLab = " and gnum_lab_code=" + invresultentryvo.getLabCode();
    } 



    
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());





    
    if (invresultentryvo.getSearchBy().equals("1")) {
      populateMAP.put(sq.next(), invresultentryvo.getFromDate());
      populateMAP.put(sq.next(), invresultentryvo.getToDate());
    } else {
      populateMAP.put(sq.next(), invresultentryvo.getFromCollDate());
      populateMAP.put(sq.next(), invresultentryvo.getToCollDate());
    } 

    
    try {
      if (invresultentryvo.getSearchBy().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKeyCollDate);
      }
    
    } catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    query = String.valueOf(query) + conditionLab;
    
    query = String.valueOf(query) + reqDtlValue + orderBy;

    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      if (!rs.next())
      {
        return sampleNoCombo;
      }

      
      rs.beforeFirst();
      sampleNoCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);




      
      rs.beforeFirst();
      while (rs.next()) {
        reportAvailAfter.add(rs.getString(3));
        reqStatus.add(rs.getString(4));
      } 
      
      for (int i = 0; i < reportAvailAfter.size(); i++)
      {

        
        if (((String)reqStatus.get(i)).contains("13") || ((String)reqStatus.get(i)).contains("26")) {
          
          if (((String)reportAvailAfter.get(i)).contains("1"))
          {
            
            if (!finalSampleNoCombo.contains(sampleNoCombo.get(i))) {
              finalSampleNoCombo.add(sampleNoCombo.get(i));
            
            }
          
          }
        }
        else if (!finalSampleNoCombo.contains(sampleNoCombo.get(i))) {
          finalSampleNoCombo.add(sampleNoCombo.get(i));
        }
      
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        return sampleNoCombo;
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return finalSampleNoCombo;
  }


  
  public List setTestGroupComboEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List testGrpCombo = new ArrayList();
    List finalTestGroupCombo = new ArrayList();
    List<String> reportAvailAfter = new ArrayList<String>();
    List<String> reqStatus = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.TESTGROUP_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.TESTGROUP_COMBO_COLL_DATE_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
    String orderBy = "order by groupName";
    String reqDtlValue = "";
    String conditionLab = "";

    
    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (7,13,26) ";
    }
    else {
      
      reqDtlValue = " and hivnum_reqdtl_status in (6) ";
    } 
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and gnum_lab_code like '%' ";
    } else {
      conditionLab = " and gnum_lab_code=" + invresultentryvo.getLabCode();
    } 


    
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());






    
    if (invresultentryvo.getSearchBy().equals("1")) {
      populateMAP.put(sq.next(), invresultentryvo.getFromDate());
      populateMAP.put(sq.next(), invresultentryvo.getToDate());
    } else {
      populateMAP.put(sq.next(), invresultentryvo.getFromCollDate());
      populateMAP.put(sq.next(), invresultentryvo.getToCollDate());
    } 

    
    try {
      if (invresultentryvo.getSearchBy().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKeyCollDate);
      }
    
    } catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    query = String.valueOf(query) + conditionLab;
    query = String.valueOf(query) + reqDtlValue + orderBy;

    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      if (!rs.next())
      {
        return testGrpCombo;
      }

      
      rs.beforeFirst();
      testGrpCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);

      
      rs.beforeFirst();
      while (rs.next()) {
        reportAvailAfter.add(rs.getString(3));
        reqStatus.add(rs.getString(4));
      } 
      
      for (int i = 0; i < reportAvailAfter.size(); i++) {


        
        if (((String)reqStatus.get(i)).contains("13") || ((String)reqStatus.get(i)).contains("26"))
        {
          if (((String)reportAvailAfter.get(i)).contains("1"))
          {
            finalTestGroupCombo.add(testGrpCombo.get(i));
          
          }
        }
        else
        {
          finalTestGroupCombo.add(testGrpCombo.get(i));
        }
      
      } 
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        return testGrpCombo;
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return finalTestGroupCombo;
  }


  
  public void updateModifyResultEntryInRequisitionDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.RESULT.ENTRY.MODIFY.HIVT_REQUISITION_DTLS";
    String queryKey_FILEUPLOAD = "UPDATE.REQDTLS.RESULT.ENTRY.MODIFY.HIVT_REQUISITION_DTLS_FILEUPLOAD";


    
    try {
      if (voResultEntry.getFileuploaddata() != null && !voResultEntry.getFileuploaddata().equals("")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey_FILEUPLOAD);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } 
    } catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 

    
    try {
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getReqDtlStatus());
    //  populateMAP.put(sq.next(), _UserVO.getSeatId());

      
      if (voResultEntry.getFileuploaddata() != null && !voResultEntry.getFileuploaddata().equals("")) {

        
        populateMAP.put(sq.next(), (voResultEntry.getFileuploaddata() == null) ? "" : voResultEntry.getFileuploaddata());
        populateMAP.put(sq.next(), (voResultEntry.getFilename() == null) ? "" : voResultEntry.getFilename());
      } 

      
      populateMAP.put(sq.next(), voResultEntry.getVisitReason());
      
      populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
      
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }


  
  public void modifyResultValidationDtl(ResultEntryVO voResultValidation, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL";
    String queryKey_FILEUPLOAD = "UPDATE.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL_FILEUPLOAD";



    
    try {
      if (voResultValidation.getFileuploaddata() != null && !voResultValidation.getFileuploaddata().equals("")) {
        query = HelperMethodsDAO.getQuery(filename, queryKey_FILEUPLOAD);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      } 
    } catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      String value = voResultValidation.getResultEntryValue().equals("") ? "--" : voResultValidation.getResultEntryValue();
      populateMAP.put(sq.next(), value);
      populateMAP.put(sq.next(), _UserVO.getSeatId());

      
      if (voResultValidation.getFileuploaddata() != null && !voResultValidation.getFileuploaddata().equals("")) {
        
        populateMAP.put(sq.next(), (voResultValidation.getFileuploaddata() == null) ? "" : voResultValidation.getFileuploaddata());
        populateMAP.put(sq.next(), (voResultValidation.getFilename() == null) ? "" : voResultValidation.getFilename());
      } 

      
      populateMAP.put(sq.next(), voResultValidation.getParameterRequisitionDNo());

      
      populateMAP.put(sq.next(), voResultValidation.getTestCode());
      populateMAP.put(sq.next(), voResultValidation.getTestParaMeterCode());
      populateMAP.put(sq.next(), voResultValidation.getParantParaCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      int i = HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
    } 
  }



  
  public void insertResultLogDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.LOG.DETAIL.HIVT_RESULT_LOG_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 



    
    try {
      populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), voResultEntry.getParantParaCode());
      populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());

      
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getResultEntryValue().equals("") ? "--" : voResultEntry.getResultEntryValue());
      populateMAP.put(sq.next(), voResultEntry.getPreviousValue());
      
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), "1");
      
      populateMAP.put(sq.next(), voResultEntry.getPatName());
      populateMAP.put(sq.next(), voResultEntry.getLabCode());
      populateMAP.put(sq.next(), voResultEntry.getPatLabName());
      populateMAP.put(sq.next(), voResultEntry.getPatAge().trim());
      populateMAP.put(sq.next(), voResultEntry.getPatGender());
      populateMAP.put(sq.next(), voResultEntry.getTestName());
      populateMAP.put(sq.next(), voResultEntry.getSampleNo());
      populateMAP.put(sq.next(), voResultEntry.getLabNo());
      populateMAP.put(sq.next(), voResultEntry.getStrRefRange());
      populateMAP.put(sq.next(), voResultEntry.getDetpUnitCode());
      populateMAP.put(sq.next(), voResultEntry.getDepartmentcode());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionTypeCode());
      populateMAP.put(sq.next(), voResultEntry.getPatUnitName());
      populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
      populateMAP.put(sq.next(), voResultEntry.getTempSampleNo());
      populateMAP.put(sq.next(), voResultEntry.getSampleName());







    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in hivt_result_log_dtl Table");
    } 
  }



  
  public List autoCannedDetails(String labCode, String cannedMacroCheck, UserVO _UserVO) {
    String query = "";
    List<String> lstCannedCodes = new ArrayList<String>();
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List<ResultEntryVO> resultentryvo = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_CANNED_CODE_DETAILS_RESULT_ENTRY_HIVT_LAB_CANNED_MST";
    String queryKey1 = "SELECT_MACRO_CODE_DETAILS_RESULT_ENTRY_HIVT_LAB_MACRO_MAPPING_MST";
    Sequence sq = new Sequence();




    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    populateMAP.put(sq.next(), labCode);
    
    try {
      if (cannedMacroCheck.equals("CANNED"))
      {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      }
      if (cannedMacroCheck.equals("MACRO"))
      {
        query = HelperMethodsDAO.getQuery(filename, queryKey1);
      
      }
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
          populateMAP);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("No Test / Lab  Found");
      }

      
      rs.beforeFirst();
      
      if (rs.next())
      {
        lstCannedCodes = HelperMethodsDAO.getAlOfEntryObjects(rs);


      
      }


    
    }
    catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    } 
    return lstCannedCodes;
  }


  
  public void updateFinalRemarkInRequisitionHeader(String reqNo, String finalRemark, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.FINALREMARK.HIVT_REQUISITION_HEADER";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      populateMAP.put(sq.next(), finalRemark);
      
      populateMAP.put(sq.next(), reqNo);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }

  
  public void updateFinalRemarkEmpCodeInRequisitionHeader(String reqNo, String finalRemark, String empCode, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.FINALREMARK.EMPCODE.HIVT_REQUISITION_HEADER";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      populateMAP.put(sq.next(), finalRemark);
      populateMAP.put(sq.next(), empCode);
      
      populateMAP.put(sq.next(), reqNo);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }


  
  public String checkcannedCode(InvResultEntryFB fb, UserVO _UserVO) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "CHECK_CANNED_CODE.HIVT_LAB_CANNED_MST";
    String record = null;










    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), fb.getCannedCode());

    
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);


      
      while (rs.next())
      {
        record = rs.getString(1);
      }
    }
    catch (Exception e) {
      
      return record;
    } 
    return record;
  }




  
  public String checkcannedName(InvResultEntryFB fb, UserVO _UserVO) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "CHECK_CANNED_NAME.HIVT_CANNED_MST";
    String record = null;

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), fb.getCannedName());


    
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);


      
      while (rs.next())
      {
        record = rs.getString(1);
      }
    }
    catch (Exception e) {
      
      return record;
    } 
    return record;
  }



  
  public void insertModifyCanned(InvResultEntryFB fb, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.MODIFT_CANNED.HIVT_CANNED_MST";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 



    
    try {
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), fb.getCannedContent());
      populateMAP.put(sq.next(), fb.getCannedName());

    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_CANNED_MST Table");
    } 
  }


  
  public String fetchCode(InvResultEntryFB fb, UserVO _UserVO) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.CODE.HIVT_LAB_CANNED_MST";
    String record = null;

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      populateMAP.put(sq.next(), fb.getCannedName());

    
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);


      
      while (rs.next())
      {
        record = rs.getString(1);
      }
    }
    catch (Exception e) {
      
      return record;
    } 
    return record;
  }



  
  public void insertUserEnterCode(InvResultEntryFB fb, UserVO _UserVO, String code) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.USER_CODE.HIVT_LAB_CANNED_MST";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 


    
    try {
      populateMAP.put(sq.next(), code);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), fb.getModifycannedLabCode());
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), fb.getCannedCode());

    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_CANNED_MST Table");
    } 
  }







  
  public void updateHyperLinkDetails(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.HIVT_WO_ORGAANTIBIOTICS_DTL1";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());

      
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());



    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }



  
  public List<Inv_SampleCollectionVO> getBilledPatList(String reqNo, String reqType, UserVO _UserVO, String reqDno) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.BILLING_PAT_LIST.HIVT_REQUISITION_DTLS_ADDENDUM";
    Sequence sq = new Sequence();
    String condition = "and hivtnum_req_dno=" + reqDno;
    Inv_SampleCollectionVO voSampleCollection = null;
    
    List<Inv_SampleCollectionVO> lstInvSampleCollectionVO = null;
    
    populateMAP.put(sq.next(), reqType);
    populateMAP.put(sq.next(), "6");
    populateMAP.put(sq.next(), "11");
    populateMAP.put(sq.next(), reqNo);
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      query = String.valueOf(query) + condition;
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
          populateMAP);
      rs.beforeFirst();
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("No Patient List  Found");
      }

      
      rs.beforeFirst();
      lstInvSampleCollectionVO = new ArrayList<Inv_SampleCollectionVO>();
      while (rs.next()) {
        voSampleCollection = new Inv_SampleCollectionVO();
        HelperMethods.populateVOfrmRS(voSampleCollection, rs);
        lstInvSampleCollectionVO.add(voSampleCollection);
      }
    
    }
    catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    } 
    return lstInvSampleCollectionVO;
  }



  
  public List<OnlinePatientAcceptanceVO> patientDetails(String reqNo, String reqType, UserVO _UserVO, String reqDno) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_HIVT_REQUISITION_HEADER_ADDENDUM";
    Sequence sq = new Sequence();
    String condition = "and hivtnum_req_dno=" + reqDno;
    OnlinePatientAcceptanceVO voPatientCollection = null;
    
    List<OnlinePatientAcceptanceVO> lstInvPatientCollectionVO = null;
    populateMAP.put(sq.next(), reqType);
    populateMAP.put(sq.next(), reqNo);
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      query = String.valueOf(query) + condition;
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
          populateMAP);
      rs.beforeFirst();
      
      if (rs.next()) {




        
        rs.beforeFirst();
        lstInvPatientCollectionVO = new ArrayList<OnlinePatientAcceptanceVO>();
        while (rs.next()) {
          voPatientCollection = new OnlinePatientAcceptanceVO();
          HelperMethods.populateVOfrmRS(voPatientCollection, rs);
          lstInvPatientCollectionVO.add(voPatientCollection);
        }
      
      } 
    } catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    } 
    return lstInvPatientCollectionVO;
  }




  
  public String getPatStatus(String reqno, UserVO uservo) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_PATSTATUS_ADDNDUM";
    String record = null;


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return record;
    } 

    
    try {
      populateMAP.put(sq.next(), uservo.getHospitalCode());
      populateMAP.put(sq.next(), reqno);



    
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);


      
      while (rs.next())
      {
        record = rs.getString(1);
      }
    }
    catch (Exception e) {
      
      return record;
    } 
    return record;
  }


  
  public List<String> setEmployeeNameComboEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.LIST.PIST_EMP_REGISTRATION_DTL_EMP_NAME";
    String conditionLab = "";
    String orderBy = " order by empName";
    
    List<String> lstEmpName = null;
    Sequence sq = new Sequence();
    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    populateMAP.put(sq.next(), _UserVO.getUserSeatId());
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 



















    
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " gnum_lab_code like '%' )";
    } else {
      conditionLab = " gnum_lab_code=" + invresultentryvo.getLabCode() + " )";
    } 
    query = String.valueOf(query) + conditionLab + orderBy;

    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      
      lstEmpName = new ArrayList<String>();








      
      if (!rs.next())
      {
        return lstEmpName;
      }

      
      rs.beforeFirst();
      lstEmpName = HelperMethodsDAO.getAlOfEntryObjects(rs);

    
    }
    catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
    } 
    return lstEmpName;
  }




  
  public String CHECKCISPARAMETERDEPENDENTRF(String fb, UserVO _UserVO) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_PARA_RESULT_ENTRY";
    String record = "";


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      populateMAP.put(sq.next(), fb);



    
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);


      
      while (rs.next())
      {
        record = String.valueOf(record) + rs.getString(1) + "@@@";
      }
    }
    catch (Exception e) {
      
      return record;
    } 
    return record;
  }



  
  public void insertHyperLinkDetailsNew(ResultEntryVO voResultEntry, UserVO _UserVO, antibioticprocessVO voo) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.ENTRY.HYPERLINK.HIVT_WO_ORGAANTIBIOTICS_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
      
      populateMAP.put(sq.next(), voo.getTestParaCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voo.getOrganismcode());
      populateMAP.put(sq.next(), voo.getAntibioticcode());
      populateMAP.put(sq.next(), voo.getResult());
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), voo.getGrowth());
      populateMAP.put(sq.next(), voo.getGrowthname());
      populateMAP.put(sq.next(), voo.getRemark());



    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }


  
  public String checkparameterexistornot(ResultEntryVO voResultValidation, UserVO _UserVO) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.COUNT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL";
    String record = null;


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return record;
    } 

    
    try {
      populateMAP.put(sq.next(), voResultValidation.getParameterRequisitionDNo());

      
      populateMAP.put(sq.next(), voResultValidation.getTestCode());
      populateMAP.put(sq.next(), voResultValidation.getTestParaMeterCode());
      populateMAP.put(sq.next(), voResultValidation.getParantParaCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    }
    catch (Exception e) {
      
      return record;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);


      
      while (rs.next())
      {
        record = rs.getString(1);
      }
    }
    catch (Exception e) {
      
      return record;
    } 
    return record;
  }



  
  public void deletehivtorganism(InvResultEntryFB fb, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.HIVT_WOORGANISM_TABLE";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 




    
    try {
      populateMAP.put(sq.next(), fb.getRequisitionDNo()[0]);
      
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }


  
  public void updatepidvaluesresult(ResultEntryVO voResultEntry, UserVO _UserVO, String hiv1, String hiv2, String status) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE_HIVT_ICTC_DTL_RESULT_VALUE_hiv1";
    String queryKey1 = "UPDATE_HIVT_ICTC_DTL_RESULT_VALUE_hiv2";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 


    
    try {
      if (hiv1 != null && !hiv1.equals("") && !hiv1.equals("--")) {
        
        query = HelperMethodsDAO.getQuery(filename, queryKey);
        populateMAP.put(sq.next(), hiv1);
      } 

      
      if (hiv2 != null && !hiv2.equals("") && !hiv2.equals("--")) {
        
        query = HelperMethodsDAO.getQuery(filename, queryKey1);
        populateMAP.put(sq.next(), hiv2);
      } 

      
      populateMAP.put(sq.next(), status);
      populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());




















    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
    } 
  }



  
  public void commentsupdateResultEntryInRequisitionDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "";
    if (!voResultEntry.getTempSampleNo().split("/")[0].equals("")) {
      queryKey = "UPDATE.REQDTLS.RESULT.ENTRY.HIVT_REQUISITION_DTLS_COMMENTS";
    } else {
      queryKey = "UPDATE.REQDTLS.RESULT.ENTRY.HIVT_REQUISITION_DTLS_COMMENTS_PATACC";
    } 
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 





    
    try {
      populateMAP.put(sq.next(), voResultEntry.getRemarkvaluess());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
      
      if (!voResultEntry.getTempSampleNo().split("/")[0].equals("")) {
        populateMAP.put(sq.next(), voResultEntry.getTempSampleNo().split("/")[0]);
      }
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }


  
  public void updateResultEntryInRequisitionDtlDraftLog(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE_HIVT_REQUISITION_DTL_DRAFT_REQ_LOG";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 

    
    try {
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
      populateMAP.put(sq.next(), voResultEntry.getTestCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("draftlog.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }


  
  public void updateIndicationInRequisitionheader(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE_HIVT_REQUISITION_HEADER_HRGSTR_VISIT_REASON";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), voResultEntry.getVisitReason());
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      
      populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      
      populateMAP.put(sq.next(), voResultEntry.getLabCode());
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("draftlog.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
    }
    catch (Exception e) {
      
      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    } 
  }



  
  public void insertechodata(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.IN.HIVT_ECHO";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      String echoval = (voResultEntry.getFinalechovalue() == null) ? "" : voResultEntry.getFinalechovalue();
      
      if (echoval.contains("^^^"))
      {
        String[] echovar = echoval.split("\\^\\^\\^");
        
        System.out.println("val");
        
        populateMAP.put(sq.next(), echovar[0]);
        
        populateMAP.put(sq.next(), echovar[1]);





      
      }






    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
    } 
  }



  
  public void updateechodata(ResultEntryVO voResultEntry, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.IN.HIVT_ECHO";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      String echoval = (voResultEntry.getFinalechovalue() == null) ? "" : voResultEntry.getFinalechovalue();
      
      if (echoval.contains("^^^"))
      {
        String[] echovar = echoval.split("\\^\\^\\^");
        
        System.out.println("val");
        
        populateMAP.put(sq.next(), echovar[1]);
        
        populateMAP.put(sq.next(), echovar[0]);






      
      }







    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
    } 
  }



  
  public String checkifEcho(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.HIVT_LABBORATORY_TEST_MST.ECHOTEST";
    String isEcho = null;


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      return isEcho;
    } 

    
    try {
      populateMAP.put(sq.next(), invresultentryvo.getTestCode());
      populateMAP.put(sq.next(), invresultentryvo.getLabCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());




    
    }
    catch (Exception e) {
      
      return isEcho;
    } 
    
    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      
      if (!rs.next())
      {
        return isEcho;
      }

      
      isEcho = rs.getString(1);




    
    }
    catch (Exception e) {
      
      return isEcho;
    } 
    return isEcho;
  }
  
  
  

public String isparameterexist(ResultEntryVO voResultEntry, UserVO _UserVO)
{
	String paraCount=""; 
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT_COUNT_TESTPARAMETER";
	Sequence sq = new Sequence();
	Connection conn=super.getTransactionContext().getConnection();

	try
	{
		query = HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	try
	{
		 
		
		   populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
           populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
           populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
           
          /* populateMAP.put(sq.next(), _UserVO.getSeatId());
           populateMAP.put(sq.next(), voResultEntry.getMachineResult());
           populateMAP.put(sq.next(), voResultEntry.getParameterCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voResultEntry.getRefRange());*/ 
	//		populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
	//		populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
			
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		
		if (!rs.next())
		{
			//throw new HisRecordNotFoundException("");
			paraCount="";
		}
		else
		{
			paraCount=rs.getString(1);
		
			System.out.println("paracount");
		}
		
		
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			//throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	return paraCount;
}


public void updatetestparameterdtl(ResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE_HIVT_PARAMETER_DTL_NEWUPDATE";
	
	try {
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HHelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {	 
		
		if(voResultEntry.getResultEntryValue()==null || voResultEntry.getResultEntryValue().equals(""))
		 {
			 voResultEntry.setResultEntryValue("--");
		 }
		
		
		String resultEntryvalue = voResultEntry.getResultEntryValue();
	      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
	      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");


	      
	      String value = voResultEntry.getResultEntryValue().equals("") ? "--" : resultEntryvalue;
	      
	      
		
		 populateMAP.put(sq.next(), value);
		 populateMAP.put(sq.next(), voResultEntry.getLoincCode());
		 populateMAP.put(sq.next(), voResultEntry.getStrRefRange());
		 populateMAP.put(sq.next(), "0");
		 populateMAP.put(sq.next(), (voResultEntry.getFileuploaddata() == null) ? "" : voResultEntry.getFileuploaddata());
	      populateMAP.put(sq.next(), (voResultEntry.getFilename() == null) ? "" : voResultEntry.getFilename());

		 		 
		 //where clause

		 populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
		 
		   populateMAP.put(sq.next(),_UserVO.getHospitalCode());
         populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
         populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
         
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
	}

}


}
