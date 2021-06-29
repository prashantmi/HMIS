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
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.RequistionHeaderVO;










































public class OnlinePatientAcceptanceDAO
  extends DataAccessObject
  implements OnlinePatientAcceptanceDAOi
{
  public OnlinePatientAcceptanceDAO(JDBCTransactionContext _tx) { super(_tx); }


  
  public Inv_RequisitionRaisingPatientVO getInvRaisingPatDetails(Inv_RequisitionRaisingPatientVO patVO, UserVO _UserVO) {
    Inv_RequisitionRaisingPatientVO invReqRaisingVO;
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL";
    Sequence sq = new Sequence();

    
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());
    populateMAP.put(sq.next(), patVO.getPatCRNo());
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, 
          populateMAP);
      rs.beforeFirst();
      invReqRaisingVO = new Inv_RequisitionRaisingPatientVO();
      if (rs.next())
      {
        
        HelperMethods.populateVOfrmRS(invReqRaisingVO, rs);
      }
    }
    catch (HisRecordNotFoundException e) {

      
      throw new HisRecordNotFoundException(e.getMessage());
    }
    catch (Exception e) {
      
      throw new HisDataAccessException(""+e);
    } 
    return invReqRaisingVO;
  }

  
  public List getSampleCombo(String labCode, String testCode, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List lstSample = new ArrayList();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.LABTESTSAMPLECOMBO.HIVT_LABTEST_SAMPLE_MST";
    
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMap.put(sq.next(), labCode);
    populateMap.put(sq.next(), testCode);
    populateMap.put(sq.next(), _UserVO.getHospitalCode());
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      if (rs.next())
      {



        
        rs.beforeFirst();
        lstSample = HelperMethodsDAO.getAlOfEntryObjects(rs);
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return lstSample;
  }


  
  public String generateRequisitionNoSequence(String labCode, UserVO userVO) {
    String sequence_Hash_yyymmdd = "";
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      populateMap.put(sq.next(), labCode);
      populateMap.put(sq.next(), userVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("");
      }

      
      sequence_Hash_yyymmdd = String.valueOf(rs.getString(1)) + "#" + rs.getString(2);



    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return sequence_Hash_yyymmdd;
  }


  
  public void insertRequisitionSequenceInMaintainer(String labCode, String sequence, String yymmdd, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      populateMAP.put(sq.next(), labCode);
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      populateMAP.put(sq.next(), sequence);
      populateMAP.put(sq.next(), yymmdd);

    
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
      throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
    } 
  }


  
  public void updateRequisitionSequenceInMaintainer(String sequence, String labcCode, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), sequence);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), labcCode);
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




  
  public void insertRequisitionHeaderDtl(RequistionHeaderVO voReqHeader, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.HIVT_REQUISITION_HEADER";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      populateMAP.put(sq.next(), voReqHeader.getRequisitionNumber());
      populateMAP.put(sq.next(), voReqHeader.getPatCrNo());
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      populateMAP.put(sq.next(), voReqHeader.getVisitNo());
      populateMAP.put(sq.next(), voReqHeader.getLabCode());
      populateMAP.put(sq.next(), voReqHeader.getIsConfidential());
      populateMAP.put(sq.next(), voReqHeader.getReqHeaderStatus());
      populateMAP.put(sq.next(), _userVO.getSeatId());
      populateMAP.put(sq.next(), voReqHeader.getEpisodeCode());
      populateMAP.put(sq.next(), voReqHeader.getPatName());
      populateMAP.put(sq.next(), voReqHeader.getReqType());
      populateMAP.put(sq.next(), voReqHeader.getPatDob());
      populateMAP.put(sq.next(), voReqHeader.getVisitDate());
      populateMAP.put(sq.next(), voReqHeader.getIsActualDob());
      populateMAP.put(sq.next(), voReqHeader.getOrderedByDoctor());
      populateMAP.put(sq.next(), voReqHeader.getGenderCode());
      populateMAP.put(sq.next(), voReqHeader.getRequsitionRaisedThrough());
      populateMAP.put(sq.next(), voReqHeader.getPatAge());
      populateMAP.put(sq.next(), voReqHeader.getPatAdmissionNo());
      populateMAP.put(sq.next(), voReqHeader.getPatAddress());
      populateMAP.put(sq.next(), voReqHeader.getWardCode());
      populateMAP.put(sq.next(), voReqHeader.getMobileNo());
      populateMAP.put(sq.next(), voReqHeader.getEmailId());
      populateMAP.put(sq.next(), voReqHeader.getRoomCode());
      populateMAP.put(sq.next(), voReqHeader.getBedCode());
      populateMAP.put(sq.next(), voReqHeader.getMlcNo());
      populateMAP.put(sq.next(), voReqHeader.getBedName());
      populateMAP.put(sq.next(), voReqHeader.getRoomName());
      populateMAP.put(sq.next(), voReqHeader.getWardName());
      populateMAP.put(sq.next(), voReqHeader.getDeptName());
      
      populateMAP.put(sq.next(), voReqHeader.getUnitName());
      
      populateMAP.put(sq.next(), voReqHeader.getDeptCode());
      populateMAP.put(sq.next(), voReqHeader.getDeptUnitCode());
      populateMAP.put(sq.next(), voReqHeader.getIsAutomatedRequest());
      populateMAP.put(sq.next(), voReqHeader.getOrderedByDoctorName());
      populateMAP.put(sq.next(), voReqHeader.getRefHospitalCode());
      populateMAP.put(sq.next(), "");
      populateMAP.put(sq.next(), voReqHeader.getRefLabCode());
      populateMAP.put(sq.next(), voReqHeader.getHospOrLabName());
      populateMAP.put(sq.next(), voReqHeader.getExtCrNo());
      populateMAP.put(sq.next(), voReqHeader.getBillNo());
      populateMAP.put(sq.next(), voReqHeader.getDeleteReason());
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_HEADER Table");
    } 
  }






  
  public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.HIVT_REQUISITION_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      
      populateMAP.put(sq.next(), voLabTest.getStrRequsitionDNo());
      
      populateMAP.put(sq.next(), (voLabTest.getStrIsAppointment() == null) ? "0" : voLabTest.getStrIsAppointment());
      
      populateMAP.put(sq.next(), voLabTest.getStrLabCode());
      
      populateMAP.put(sq.next(), voLabTest.getStrTestCode());
      
      populateMAP.put(sq.next(), (voLabTest.getStrIsConfidential() == null) ? "0" : voLabTest.getStrIsConfidential());


      
      populateMAP.put(sq.next(), voLabTest.getStrSampleCode());




      
      populateMAP.put(sq.next(), _userVO.getSeatId());
      
      populateMAP.put(sq.next(), voLabTest.getStrReqNo());
      
      populateMAP.put(sq.next(), voLabTest.getStrWorkOrderSequence());
      
      populateMAP.put(sq.next(), voLabTest.getStrTypeOfComponent());
      
      populateMAP.put(sq.next(), voLabTest.getStrRequisitionDtlStatus());




    
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "IcdGroupMasterDAO.populateMAP::" + e);
    } 
    
    try {
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
    } 
  }


  
  public List<Inv_EpisodeVO> getPatientEpisode(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_EPISODE_DTLS.HRGT_EPISODE_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();
    
    try {
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      
      populateMAP.put(sq.next(), patVO.getPatCRNo());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    PatientDetailVO[] dailyPatientVOs = null;
    List<Inv_EpisodeVO> lstPatientDetailVO = new ArrayList<Inv_EpisodeVO>();
    ValueObject[] valueObjects = null;

    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(Inv_EpisodeVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++)
        {
          
          lstPatientDetailVO.add((Inv_EpisodeVO)valueObjects[i]);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
    } 
    return lstPatientDetailVO;
  }


  
  public List<Inv_PatientAdmissionDtlVO> getPatientAdmission(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_ADMISSION_DTLS.HIPT_PATADMISSION_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 

    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();

    
    try {
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      
      populateMAP.put(sq.next(), patVO.getPatCRNo());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    Inv_PatientAdmissionDtlVO[] dailyPatientVOs = null;
    List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionDetailVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
    ValueObject[] valueObjects = null;

    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(Inv_PatientAdmissionDtlVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++)
        {
          
          lstPatientAdmissionDetailVO.add((Inv_PatientAdmissionDtlVO)valueObjects[i]);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
    } 
    
    return lstPatientAdmissionDetailVO;
  }


  
  public List<BookMarkVO> getBookMarkList(UserVO _userVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();
    
    try {
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      
      populateMAP.put(sq.next(), _userVO.getSeatId());
      
      populateMAP.put(sq.next(), "15");
      
      populateMAP.put(sq.next(), _userVO.getUserSeatId());
      
      populateMAP.put(sq.next(), _userVO.getHospitalCode());

    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    BookMarkVO[] bookMarkVOs = null;
    List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
    ValueObject[] valueObjects = null;

    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++)
        {
          
          lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
    } 
    return lstBookMarkVO;
  }


  
  public List getLabCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List parameterCombo = new ArrayList();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_LAB_COMBO_HIVT_LABORATORY_MST";
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



  
  public List<OnlinePatientAcceptanceVO> setPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.HIVT_REQUISITION_HEADER";
    String condition1 = " AND HRGNUM_PUK=" + onlinePatientvo.getPatCRNo();
    String condition2 = " and GNUM_LAB_CODE=" + onlinePatientvo.getLabCode();

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();


    
    try {
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

      
      populateMAP.put(sq.next(), onlinePatientvo.getFromDate());
      populateMAP.put(sq.next(), onlinePatientvo.getToDate());
      
      populateMAP.put(sq.next(), _UserVO.getUserSeatId());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());


    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    PatientDetailVO[] dailyPatientVOs = null;
    List<OnlinePatientAcceptanceVO> lstOnlinePatientAcceptanceVO = new ArrayList<OnlinePatientAcceptanceVO>();
    ValueObject[] valueObjects = null;

    
    try {
      if (onlinePatientvo.getPatCRNo() != null && !onlinePatientvo.getPatCRNo().equals(""))
      {
        query = String.valueOf(query) + condition1;
      }
      
      if (!onlinePatientvo.getLabCode().equals("%"))
      {
        query = String.valueOf(query) + condition2;
      }

      
      if (onlinePatientvo.getPatCRNo() != null && !onlinePatientvo.getPatCRNo().equals("") && !onlinePatientvo.getLabCode().equals("%"))
      {
        query = String.valueOf(query) + condition1 + condition2;
      }

      
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(OnlinePatientAcceptanceVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++)
        {
          
          lstOnlinePatientAcceptanceVO.add((OnlinePatientAcceptanceVO)valueObjects[i]);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      e.printStackTrace();
      throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
    } 

    
    return lstOnlinePatientAcceptanceVO;
  }





  
  public List<OnlinePatientAcceptanceVO> setPatientEssentialsOnLoad(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.ONLOAD_HIVT_REQUISITION_HEADER";
    String condition4 = "and  EXISTS ( SELECT 1 FROM HIVT_COLLECTION_AREA_MST z WHERE z.gnum_area_code =" + onlinePatientvo.getSampleAreaCode() + "  and z.hipnum_ward_code=A.hipnum_wardcode and z.gnum_hospital_code=a.gnum_hospital_code and gnum_isvalid=1)";

    
    String iswardforccolection = iswardforcollectionarea(onlinePatientvo.getSampleAreaCode(), _UserVO.getHospitalCode());
    
    String wardtype = "";
    String wardcode = "";
    if (iswardforccolection != null && !iswardforccolection.equals("")) {

      
      wardtype = iswardforccolection.split("#")[0];
      
      if (wardtype.equals("2"))
      {
        wardcode = iswardforccolection.split("#")[1];
      }
    } 




    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    if (wardtype.equals("2") && 
      wardcode != null) {

      
      query = String.valueOf(query) + condition4;

      
      query = String.valueOf(query) + " and a.HIPNUM_WARDCODE=" + wardcode + " ";
    } 


    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();


    
    try {
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      
      populateMAP.put(sq.next(), _UserVO.getUserSeatId());
      
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    PatientDetailVO[] dailyPatientVOs = null;
    List<OnlinePatientAcceptanceVO> lstOnlinePatientAcceptanceVO = new ArrayList<OnlinePatientAcceptanceVO>();
    ValueObject[] valueObjects = null;


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(OnlinePatientAcceptanceVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++)
        {
          
          lstOnlinePatientAcceptanceVO.add((OnlinePatientAcceptanceVO)valueObjects[i]);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
    } 
    return lstOnlinePatientAcceptanceVO;
  }











  
  public List<OnlinePatientAcceptanceVO> patientDetails(String reqNo, String reqType, String acceptedToNotAccepted, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_HIVT_REQUISITION_HEADER";
    Sequence sq = new Sequence();
    
    OnlinePatientAcceptanceVO voPatientCollection = null;
    
    List<OnlinePatientAcceptanceVO> lstInvPatientCollectionVO = null;
    populateMAP.put(sq.next(), reqType);
    
    if (acceptedToNotAccepted.equals("2")) {
      String reqdtlStatus = "6";
      populateMAP.put(sq.next(), "6");
      populateMAP.put(sq.next(), "-99");
    } else {
      
      populateMAP.put(sq.next(), "5");
      populateMAP.put(sq.next(), "10");
    } 
    populateMAP.put(sq.next(), reqNo);
    populateMAP.put(sq.next(), _UserVO.getHospitalCode());

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
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
      
      throw new HisDataAccessException(""+e);
    } 
    return lstInvPatientCollectionVO;
  }



  
  public List getTestCombo(UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List departcombo = new ArrayList();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.COMBO_hivt_rejectionreason_mst";
    
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();


    
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
      departcombo = HelperMethodsDAO.getAlOfEntryObjects(rs);
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return departcombo;
  }

  
  public String generatePatientNoSequence(String labCode, UserVO userVO) {
    String sequence_Hash_yyymmdd = "";
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT.SYS_REQUISITION_MAINTAINER";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 

    
    try {
      populateMap.put(sq.next(), labCode);
      populateMap.put(sq.next(), userVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("");
      }

      
      sequence_Hash_yyymmdd = String.valueOf(rs.getString(1)) + "#" + rs.getString(2);



    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return sequence_Hash_yyymmdd;
  }

  
  public void insertPatientNoSequenceInMaintainer(String labCode, String sequence, String yymmdd, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.PATIENTNO.SYS_PATIENTACC_MAINTAINER";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      populateMAP.put(sq.next(), labCode);
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      populateMAP.put(sq.next(), sequence);
      populateMAP.put(sq.next(), yymmdd);

    
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
      throw new HisDataAccessException("Exception While insertion in SYS_PATIENTACC_MAINTAI Table");
    } 
  }


  
  public void updatePatientNoSequenceInMaintainer(String sequence, String labCode, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.PATIENT.REQUISTIONNO.SYS_SAMPLE_MAINTAINER";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), sequence);
      
      populateMAP.put(sq.next(), labCode);
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


  
  public void updateRequisitionHeaderForPatient(OnlinePatientAcceptanceVO voPatient, String patAddress, String reqNo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.PATIENTDTLS.HIVT_REQUISITION_HEADER";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    try {
      populateMAP.put(sq.next(), voPatient.getMobileNo());
      populateMAP.put(sq.next(), voPatient.getEmailId());
      populateMAP.put(sq.next(), voPatient.getAddress());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), reqNo);
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


  
  public void updateRequisitionDtlForPatient(OnlinePatientAcceptanceVO voPatient, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.PATIENT.HIVT_REQUISITION_DTLS";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      populateMAP.put(sq.next(), voPatient.getReqDtlStatus());
      
      populateMAP.put(sq.next(), voPatient.getBillNo());
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());
      populateMAP.put(sq.next(), voPatient.getRejectionReason());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());


      
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voPatient.getAccepted());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
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


  
  public void updateRequisitionDtlForPatientRescheduled(OnlinePatientAcceptanceVO voPatient, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.PATIENT.RESCHEDULED.HIVT_REQUISITION_DTLS";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      populateMAP.put(sq.next(), voPatient.getReqDtlStatus());
      
      populateMAP.put(sq.next(), voPatient.getBillNo());
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());
      populateMAP.put(sq.next(), voPatient.getRejectionReason());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), voPatient.getAccepted());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
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



  
  public void updateRequisitionDtlForPatientAccepted(OnlinePatientAcceptanceVO voPatient, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.PATIENT.ACCEPTED.HIVT_REQUISITION_DTLS";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      populateMAP.put(sq.next(), voPatient.getSampleAreaCode());
      
      populateMAP.put(sq.next(), voPatient.getReqDtlStatus());
      
      populateMAP.put(sq.next(), voPatient.getBillNo());
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());



      
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), voPatient.getAccepted());
      
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), _UserVO.getSeatId());

      
      populateMAP.put(sq.next(), voPatient.getMachineCode());

      
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), voPatient.getUserHasPermission());
      
      populateMAP.put(sq.next(), voPatient.getMobileNo());
      populateMAP.put(sq.next(), voPatient.getEmailId());
      populateMAP.put(sq.next(), voPatient.getAddress());
      
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());


    
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







  
  public void insertPatientDtlForPatient(String pataccNo, OnlinePatientAcceptanceVO voPatient, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.PATIENTDTLSACCEPTED.HIVT_PATIENT_ACCEPTANCE_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 





    
    try {
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      
      populateMAP.put(sq.next(), pataccNo);
      
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), voPatient.getAccepted());
      
      populateMAP.put(sq.next(), _userVO.getSeatId());
      
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      
      populateMAP.put(sq.next(), voPatient.getUserHasPermission());







    
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
      throw new HisDataAccessException("Exception While insertion in HIVT_PATIENT_ACCEPTANCE_DTL Table");
    } 
  }



  
  public void insertPatientNotAcceptedDtlForPatient(String pataccNo, OnlinePatientAcceptanceVO voPatient, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.PATIENTDTLSNOTACCEPTED.HIVT_PATIENT_ACCEPTANCE_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 

    
    try {
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      
      populateMAP.put(sq.next(), pataccNo);
      populateMAP.put(sq.next(), voPatient.getAccepted());
      populateMAP.put(sq.next(), voPatient.getRejectionReason());
      populateMAP.put(sq.next(), _userVO.getSeatId());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());
      
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      populateMAP.put(sq.next(), _userVO.getSeatId());
    
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
      throw new HisDataAccessException("Exception While insertion in HIVT_PATIENT_ACCEPTANCE_DTL Table");
    } 
  }





  
  public boolean checkDailyLabNoDuplicacy(OnlinePatientAcceptanceVO voPatient, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    boolean isLabNoPresent = false;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.CHECKDAILYLABNODUPLICACY";
    int count = 0;
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();

    
    populateMap.put(sq.next(), voPatient.getLabNoConfiguration());

    
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


      
      count = rs.getInt(1);
      if (count > 0) {
        isLabNoPresent = true;
      
      }
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return isLabNoPresent;
  }

  
  public List getPatientAcceptanceAutoLabNOConfig(OnlinePatientAcceptanceVO onlineAcceptanceVO, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.HIVT_LABNO_CONF_MST";


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();

    
    try {
      populateMAP.put(sq.next(), onlineAcceptanceVO.getLabCode());
      populateMAP.put(sq.next(), onlineAcceptanceVO.getTestCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());


    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    List<OnlinePatientAcceptanceVO> listSampleAcceptanceVO = new ArrayList<OnlinePatientAcceptanceVO>();
    ValueObject[] valueObjects = null;


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(OnlinePatientAcceptanceVO.class, rs);



        
        listSampleAcceptanceVO.add((OnlinePatientAcceptanceVO)valueObjects[0]);
      
      }
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        return listSampleAcceptanceVO;
      }
      return listSampleAcceptanceVO;
    } 
    return listSampleAcceptanceVO;
  }

  
  public void updatePatientAccInhivtlabnoconfmst(OnlinePatientAcceptanceVO voSamAcc, String finalEntryDate, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.PATIENTACCDETAIL.HIVT_LABNO_CONF_MST";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 

    
    try {
      populateMAP.put(sq.next(), finalEntryDate);
      populateMAP.put(sq.next(), voSamAcc.getLabNoConfiguration());
      populateMAP.put(sq.next(), voSamAcc.getConfigLab());
      populateMAP.put(sq.next(), voSamAcc.getConfigTest());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voSamAcc.getConfigType());
      populateMAP.put(sq.next(), voSamAcc.getConfigSeq());


    
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
  
  public String generateLabNoDateSequence(String subLength, UserVO userVO) {
    String sequence_Hash_yyymmdd = "";
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.SAMPLEACC.LABNODATEGEN.FROM.DUAL";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 

    
    try {
      populateMap.put(sq.next(), subLength);
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("");
      }

      
      sequence_Hash_yyymmdd = rs.getString(1);



    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return sequence_Hash_yyymmdd;
  }

  
  public void updatePatientAccInhivtlabnoconfmstResetLabNO(OnlinePatientAcceptanceVO voSamAcc, String finalEntryDate, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.PATIENTACCDETAIL.RESET_RUNNING_LABNO.HIVT_LABNO_CONF_MST";
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 


    
    try {
      populateMAP.put(sq.next(), voSamAcc.getConfigLab());
      populateMAP.put(sq.next(), voSamAcc.getConfigTest());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), voSamAcc.getConfigType());
      populateMAP.put(sq.next(), voSamAcc.getConfigSeq());

    
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








  
  public List<OnlinePatientAcceptanceVO> checkAutoGenFormate(OnlinePatientAcceptanceVO online, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List<String> Formate = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.HIVT_LABNO_CONF_MST";

    
    Connection conn = getTransactionContext().getConnection();

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);


    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();


    
    try {
      populateMap.put(sq.next(), online.getLabCode());
      populateMap.put(sq.next(), online.getTestCode());
      populateMap.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    List<OnlinePatientAcceptanceVO> onlinepatientacceptancevo = new ArrayList<OnlinePatientAcceptanceVO>();
    ValueObject[] valueObjects = null;


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMap);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(OnlinePatientAcceptanceVO.class, rs);



        
        onlinepatientacceptancevo.add((OnlinePatientAcceptanceVO)valueObjects[0]);
      }
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return onlinepatientacceptancevo;
  }




  
  public String checkAutoGenFormateRunningLabNo(OnlinePatientAcceptanceVO online, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String Formate = "";
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.AJAX.CHECK.HIVT_LABNO_CONF_MST";
    Connection conn = getTransactionContext().getConnection();

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();

    
    try {
      populateMAP.put(sq.next(), online.getConfigLab());
      populateMAP.put(sq.next(), online.getConfigTest());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), online.getConfigType());
      populateMAP.put(sq.next(), online.getConfigSeq());



    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        if (rs.next())
        {
          Formate = rs.getString(1);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return Formate;
  }



  
  public List<OnlinePatientAcceptanceVO> checkAutoGenFormateAreawise(OnlinePatientAcceptanceVO online, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List<String> Formate = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.HIVT_LABNO_CONF_MST_AREA_WISE";

    
    Connection conn = getTransactionContext().getConnection();

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);


    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();

    
    try {
      populateMap.put(sq.next(), online.getSampleAreaCode());
      populateMap.put(sq.next(), online.getLabCode());
      populateMap.put(sq.next(), online.getTestCode());
      populateMap.put(sq.next(), _UserVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    List<OnlinePatientAcceptanceVO> onlinepatientacceptancevo = new ArrayList<OnlinePatientAcceptanceVO>();
    ValueObject[] valueObjects = null;


    
    try {
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMap);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(OnlinePatientAcceptanceVO.class, rs);



        
        onlinepatientacceptancevo.add((OnlinePatientAcceptanceVO)valueObjects[0]);
      }
    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return onlinepatientacceptancevo;
  }





  
  public String checkAutoGenFormateRunningLabNoAreaWise(OnlinePatientAcceptanceVO online, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String Formate = "";
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PATIENT_ACCC_DETAIL_AUTOGEN_LABNO.AJAX.CHECK.HIVT_LABNO_CONF_MST_AREA_WISE";
    Connection conn = getTransactionContext().getConnection();

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();

    
    try {
      populateMAP.put(sq.next(), online.getConfigLab());
      populateMAP.put(sq.next(), online.getConfigTest());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), online.getConfigType());
      populateMAP.put(sq.next(), online.getConfigSeq());
      populateMAP.put(sq.next(), online.getSampleAreaCode());


    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        if (rs.next())
        {
          Formate = rs.getString(1);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return Formate;
  }



  
  public List getMachineCombos(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List parameterCombo = new ArrayList();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_MACHINE_COMBO_LOCATION_BASE";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    populateMap.put(sq.next(), _UserVO.getHospitalCode());
    populateMap.put(sq.next(), onlinePatientvo.getSampleAreaCode());
    
    populateMap.put(sq.next(), "2");

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      if (rs.next())
      {



        
        rs.beforeFirst();
        parameterCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return parameterCombo;
  }





  
  public String iswardforcollectionarea(String collareacode, String hospitalcode) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String Formate = "";
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.WARDCODE.HIVT_COLLECTION_AREA_MST";

    
    Connection conn = getTransactionContext().getConnection();


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);

    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();

    
    try {
      populateMap.put(sq.next(), collareacode);
      populateMap.put(sq.next(), hospitalcode);

    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      if (rs.next())
      {



        
        rs.beforeFirst();
        if (rs.next())
        {
          Formate = rs.getString(1);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return Formate;
  }



  
  public String getmaxidreasonmstt(OnlinePatientAcceptanceVO rejectionreasonmaster_VO, UserVO _UserVO) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String Formate = "";
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.MAX.REASON.MST";

    
    Connection conn = getTransactionContext().getConnection();


    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);

    
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();












    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      if (rs.next())
      {



        
        rs.beforeFirst();
        if (rs.next())
        {
          Formate = rs.getString(1);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException();
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return Formate;
  }


  
  public void insertreasonmst(OnlinePatientAcceptanceVO rejectionreasonmaster_VO, UserVO _UserVO, String fromwherecalling, String idd) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.IN.REASON.MST";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 


    
    try {
      populateMAP.put(sq.next(), idd);
      populateMAP.put(sq.next(), fromwherecalling);
      populateMAP.put(sq.next(), rejectionreasonmaster_VO.getExtrarejectionReason());

      
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), _UserVO.getSeatId());

    
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
      throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
    } 
  }




  
  public void updatePatientNotAcceptedDtlForPatient(String pataccNo, OnlinePatientAcceptanceVO voPatient, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.PATIENTDTLSNOTACCEPTED.HIVT_PATIENT_ACCEPTANCE_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 


    
    try {
      populateMAP.put(sq.next(), voPatient.getAccepted());
      populateMAP.put(sq.next(), voPatient.getRejectionReason());
      populateMAP.put(sq.next(), _userVO.getSeatId());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      populateMAP.put(sq.next(), _userVO.getSeatId());
      
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      populateMAP.put(sq.next(), pataccNo);
    
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
      throw new HisDataAccessException("Exception While insertion in HIVT_PATIENT_ACCEPTANCE_DTL Table");
    } 
  }



  
  public List<OnlinePatientAcceptanceVO> setAcceptedPatientEssentials(OnlinePatientAcceptanceVO onlinePatientvo, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.ACCEPTED.HIVT_REQUISITION_HEADER";
    String condition1 = " AND HRGNUM_PUK=" + onlinePatientvo.getPatCRNo();
    String condition2 = " and GNUM_LAB_CODE=" + onlinePatientvo.getLabCode();

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    } 
    
    System.out.println("Query ---------> " + query);
    Sequence sq = new Sequence();


    
    try {
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

      
      populateMAP.put(sq.next(), onlinePatientvo.getFromDate());
      populateMAP.put(sq.next(), onlinePatientvo.getToDate());
      
      populateMAP.put(sq.next(), _UserVO.getUserSeatId());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());


    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    } 
    
    PatientDetailVO[] dailyPatientVOs = null;
    List<OnlinePatientAcceptanceVO> lstOnlinePatientAcceptanceVO = new ArrayList<OnlinePatientAcceptanceVO>();
    ValueObject[] valueObjects = null;

    
    try {
      if (onlinePatientvo.getPatCRNo() != null && !onlinePatientvo.getPatCRNo().equals(""))
      {
        query = String.valueOf(query) + condition1;
      }
      
      if (!onlinePatientvo.getLabCode().equals("%"))
      {
        query = String.valueOf(query) + condition2;
      }

      
      if (onlinePatientvo.getPatCRNo() != null && !onlinePatientvo.getPatCRNo().equals("") && !onlinePatientvo.getLabCode().equals("%"))
      {
        query = String.valueOf(query) + condition1 + condition2;
      }

      
      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {



        
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(OnlinePatientAcceptanceVO.class, rs);
        
        for (int i = 0; i < valueObjects.length; i++)
        {
          
          lstOnlinePatientAcceptanceVO.add((OnlinePatientAcceptanceVO)valueObjects[i]);
        }
      }
    
    } catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      e.printStackTrace();
      throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
    } 

    
    return lstOnlinePatientAcceptanceVO;
  }



  
  public void updatePatientAcceptedDtlForPatient(String pataccNo, OnlinePatientAcceptanceVO voPatient, UserVO _userVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.PATIENTDTLSACCEPTED.HIVT_PATIENT_ACCEPTANCE_DTL";

    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + 
          e);
    } 






    
    try {
      populateMAP.put(sq.next(), voPatient.getAccepted());
      populateMAP.put(sq.next(), _userVO.getSeatId());
      populateMAP.put(sq.next(), voPatient.getLabNoConfiguration());
      
      populateMAP.put(sq.next(), voPatient.getAcceptanceDate());
      populateMAP.put(sq.next(), voPatient.getRejectionAction());
      populateMAP.put(sq.next(), voPatient.getUserHasPermission());

      
      populateMAP.put(sq.next(), voPatient.getRequisitionDNo());
      populateMAP.put(sq.next(), _userVO.getHospitalCode());
      populateMAP.put(sq.next(), pataccNo);




    
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
      throw new HisDataAccessException("Exception While insertion in HIVT_PATIENT_ACCEPTANCE_DTL Table");
    } 
  }




  
  public String selectPataccNoPatientAcceptedDtl(OnlinePatientAcceptanceVO voPatient, UserVO _userVO) {
    String patAccNo = "";
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.PAT_ACC_NO.HIVT_PATIENT_ACCEPTANCE_DTL";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 



    
    try {
      populateMap.put(sq.next(), voPatient.getRequisitionDNo());
      populateMap.put(sq.next(), _userVO.getHospitalCode());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("");
      }

      
      patAccNo = rs.getString(1);



    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return patAccNo;
  }



  
  public Map getUserList(UserVO _UserVO) {
    Map<String, String> uerList = new HashMap<String, String>();
    
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.GNUM_USERID.GBLT_USER_MST";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    
    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
    } 

    
    try {
      populateMap.put(sq.next(), _UserVO.getHospitalCode());
      populateMap.put(sq.next(), _UserVO.getUserId());
    
    }
    catch (Exception e) {
      
      throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
    } 
    
    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
      
      if (!rs.next())
      {
        throw new HisRecordNotFoundException("");
      }

      
      rs.beforeFirst();
      
      while (rs.next())
      {
        uerList.put(rs.getString(1), rs.getString(2));

      
      }

    
    }
    catch (Exception e) {
      
      if (e.getClass() == HisRecordNotFoundException.class)
      {
        throw new HisRecordNotFoundException(e.getMessage());
      }
      
      throw new HisDataAccessException("HisDataAccessException:: " + e);
    } 
    return uerList;
  }
}
