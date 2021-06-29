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
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.vo.InvResultValidationRespVO;
import new_investigation.vo.InvTrackingReportVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invAntibioticProcessVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TriStringObject;
import org.w3c.dom.Node;


public class InvResultValidationRespDAO
  extends DataAccessObject
{
  public InvResultValidationRespDAO(JDBCTransactionContext _tx) { super(_tx); }


  public  List<Inv_SampleCollectionVO> AajaxGetCollAreaList(UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs = null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.SAMPLE_COLLECTION_AREA.HIVT_COLLECTION_AREA_MST";
		Sequence sq = new Sequence();

		List<Inv_SampleCollectionVO> lstInvSampleCollectionVO =null;
		populateMAP.put(sq.next(),  _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
	//	populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), _UserVO.getUserSeatId());
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
					populateMAP);
			rs.beforeFirst();

			 if (!rs.next())
	            {
	                throw new HisRecordNotFoundException("No Sample Collection Area Found");
	            }
	            else
	            {
	                rs.beforeFirst();
	                lstInvSampleCollectionVO=new ArrayList<Inv_SampleCollectionVO>();
	                Inv_SampleCollectionVO InvSampleCollectionVO=null;
	                while (rs.next()) {
	                	InvSampleCollectionVO=new Inv_SampleCollectionVO();
	                    HelperMethods.populateVOfrmRS(InvSampleCollectionVO, rs);
	                    lstInvSampleCollectionVO.add(InvSampleCollectionVO);
	                }
	            }
		}
		catch (HisRecordNotFoundException e)
		{

			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("" + e);
		}
		return lstInvSampleCollectionVO;

	}

  public List AjaxGetLabList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO) {
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

  public List<InvResultValidationRespVO> AjaxGetValidationReqnList(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO) {
    ResultSet rs = null;
    String query = "";
    Map populateMAP = new HashMap();
    String fromAlpha = "";
    String toAlpha = "";
    String filename = "new_investigation.transactions.invResultValidationRespQuery";

    String queryKey = "";
    queryKey = "SELECT.RESULT_VALIDATION_HIVT_REQUISITION_HEADER";

    try {
    	query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }

    //Condition for filtering by collection area
    if (!invResultValidationRespVO.getCollAreaCode().equals("0")) {
    	query = String.valueOf(query) + "AND A.hivnum_sample_col_area_code = ?";
    }


    String orderByDateClause="";
    String dateTypefilterClause="";
    //Condition for filtering by dateType
    if (invResultValidationRespVO.getDateFiltersOrBoth()!=null & (invResultValidationRespVO.getDateFiltersOrBoth().equals("0") || invResultValidationRespVO.getDateFiltersOrBoth().equals("1"))) {

    	if(invResultValidationRespVO.getDateTypeCode().equals("1")){
    		dateTypefilterClause = "AND TRUNC(a.hivt_entry_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivt_entry_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY'))";
    		orderByDateClause = " a.hivt_entry_date desc";
    	}

    	if(invResultValidationRespVO.getDateTypeCode().equals("2") || invResultValidationRespVO.getDateTypeCode().equals("3")){
    		dateTypefilterClause = "AND TRUNC(a.hivdt_coll_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_coll_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY'))";
    		orderByDateClause = " a.hivdt_coll_date_time desc";
    	}

    	if(invResultValidationRespVO.getDateTypeCode().equals("4")){
    		dateTypefilterClause = "AND TRUNC(a.hivdt_result_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_result_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY'))";
    		orderByDateClause = " a.hivdt_result_date desc";
    	}
    } else {
    	orderByDateClause = " a.hivdt_coll_date_time";
    }
    query = String.valueOf(query) + dateTypefilterClause;

    //Condition for filtering by CrNo
    if (invResultValidationRespVO.getDateFiltersOrBoth()!= null && (invResultValidationRespVO.getDateFiltersOrBoth().equals("1") || invResultValidationRespVO.getDateFiltersOrBoth().equals("2")) && invResultValidationRespVO.getCrNo()!= null && !invResultValidationRespVO.getCrNo().equals(""))
    {	String crNoClause = "And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)=" + invResultValidationRespVO.getCrNo();
    	query = String.valueOf(query) + crNoClause;
    }

   //Condition for filtering by Requisition Status
    String reqnStatusClause = "";
    if (invResultValidationRespVO.getValidationStatusCode() != null && invResultValidationRespVO.getValidationStatusCode().equals("1")) {
    	reqnStatusClause = " and hivnum_reqdtl_status in (7) ";
    } else if (invResultValidationRespVO.getValidationStatusCode() != null && invResultValidationRespVO.getValidationStatusCode().equals("2")) {
    	reqnStatusClause = " and hivnum_reqdtl_status in (8,13) ";
    } else if (invResultValidationRespVO.getValidationStatusCode() != null && invResultValidationRespVO.getValidationStatusCode().equals("3")) {
    	reqnStatusClause = " and hivnum_reqdtl_status in (28) ";
    }
    query = String.valueOf(query) + reqnStatusClause;

    //Condition for filtering by Lab Code
    String labFilterClause = "";
    if (invResultValidationRespVO.getLabCode().equals("0")) {
    	labFilterClause = " and a.gnum_lab_code like '%' ";
    } else {
    	labFilterClause = " and a.gnum_lab_code=" + invResultValidationRespVO.getLabCode();
    }
    query = String.valueOf(query) + labFilterClause;

    //Order by Clause
    query = String.valueOf(query) + "ORDER BY gbnum_priority_code desc, hivnum_temp_sample_no, grp, "+orderByDateClause;

    System.out.println("Query Print" + query);

    Sequence sq = new Sequence();
    try {
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), "15");

      populateMAP.put(sq.next(), _UserVO.getUserSeatId());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

      if (!invResultValidationRespVO.getCollAreaCode().equals("0")) {
      	populateMAP.put(sq.next(), invResultValidationRespVO.getCollAreaCode());
        }

      if (checkNumeric(_UserVO.getUserEmpID())) {
          populateMAP.put(sq.next(), _UserVO.getUserEmpID());
        } else {
          populateMAP.put(sq.next(), "0");
        }

      if (invResultValidationRespVO.getDateFiltersOrBoth().equals("0") || invResultValidationRespVO.getDateFiltersOrBoth().equals("1")) {
        populateMAP.put(sq.next(), invResultValidationRespVO.getFromDate());
        populateMAP.put(sq.next(), invResultValidationRespVO.getToDate());
      }

    }
    catch (Exception e) {
      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
    }

    PatientDetailVO[] dailyPatientVOs = null;
    List<InvResultValidationRespVO> lstInvResultValidationRespVO = new ArrayList<InvResultValidationRespVO>();
    List<InvResultValidationRespVO> finalLststInvResultValidationRespVO = new ArrayList<InvResultValidationRespVO>();
    ValueObject[] valueObjects = null;

    try {

      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
      if (rs.next())
      {
        rs.beforeFirst();
        valueObjects = HelperMethods.populateVOfrmRS(InvResultValidationRespVO.class, rs);

        for (int i = 0; i < valueObjects.length; i++)
        {
          lstInvResultValidationRespVO.add((InvResultValidationRespVO)valueObjects[i]);

          if (((InvResultValidationRespVO)lstInvResultValidationRespVO.get(i)).getReqDtlStatus().equals("13") || ((InvResultValidationRespVO)lstInvResultValidationRespVO.get(i)).getReqDtlStatus().equals("26"))
          {
            if (((InvResultValidationRespVO)lstInvResultValidationRespVO.get(i)).getReportAvailableAfter().equals("2"))
            { finalLststInvResultValidationRespVO.add((InvResultValidationRespVO)lstInvResultValidationRespVO.get(i)); }
          }
          else
          { finalLststInvResultValidationRespVO.add((InvResultValidationRespVO)lstInvResultValidationRespVO.get(i)); }
        }
      }

    } catch (Exception e) {

	      if (e.getClass() == HisRecordNotFoundException.class)
	      { throw new HisRecordNotFoundException(e.getMessage()); }
	      throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
     }
    return finalLststInvResultValidationRespVO;
  }


  public String getReqnTestParam(InvResultValidationRespVO vo, UserVO _UserVO) {

		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_RESULT_VALIDATION_DAO;
		String queryKey1="SELECT_INV_RESULTVAL_TESTPARAM_ONREQDNO_HIVT_REQUISITION_DTL";

		String query="";
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();
		Map populateMap= new HashMap();
		Sequence sq= new Sequence();

		String reqnTestParam="";
		try
		{
		query = HelperMethodsDAO.getQuery(filename,queryKey1);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}

		try
		{

			populateMap.put(sq.next(), vo.getRequisitionNo());
			populateMap.put(sq.next(), vo.getRequisitionDNo());
			populateMap.put(sq.next(), _UserVO.getHospitalCode());

			rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if(!rs.next()) { }
			else {
				rs.beforeFirst();
				while(rs.next()) {
					reqnTestParam=rs.getString(1);
				}
			}

		}
		catch (Exception e)
		{
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		 }

		return reqnTestParam;
	}

  public void updateResultValidationInRequisitionDtl(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO) {
    String query = "";
    String query2 = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    Map populateMAP2 = new HashMap();
    Sequence sq2 = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.RESULT.VALIDATION.HIVT_REQUISITION_DTLS";
    String queryKeySendToMachine = "UPDATE.REQDTLS.RESULT.VALIDATION.HIVT_REQUISITION_DTLS_SEND_TO_MACHINE";
    String queryKeySampleUpdate = "UPDATE.HMIT_SAMPLE_DTL_SEND_TO_MACHINE";

    try {
      if (invResultValidationRespVO.getIsSendToMachine() != null && invResultValidationRespVO.getIsSendToMachine().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKeySendToMachine);
        query2 = HelperMethodsDAO.getQuery(filename, queryKeySampleUpdate);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      }

    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }

    try {
      if (invResultValidationRespVO.getIsSendToMachine() != null && invResultValidationRespVO.getIsSendToMachine().equals("1")) {
        populateMAP.put(sq.next(), "1");
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), "1");
        populateMAP.put(sq.next(), invResultValidationRespVO.getReqDtlStatus());
        populateMAP.put(sq.next(), invResultValidationRespVO.getVisitReason());

        populateMAP.put(sq.next(), invResultValidationRespVO.getRequisitionDNo());
        populateMAP.put(sq.next(), invResultValidationRespVO.getTestCode());
        populateMAP.put(sq.next(), _UserVO.getHospitalCode());


        populateMAP2.put(sq2.next(), "0");
        populateMAP2.put(sq2.next(), "1");
        populateMAP2.put(sq2.next(), "1");
        populateMAP2.put(sq2.next(), "1");
        populateMAP2.put(sq2.next(), _UserVO.getHospitalCode());
        populateMAP2.put(sq2.next(), invResultValidationRespVO.getRequisitionDNo());
        populateMAP2.put(sq2.next(), invResultValidationRespVO.getRequisitionDNo());
        populateMAP2.put(sq2.next(), _UserVO.getHospitalCode());

      }
      else {


        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), invResultValidationRespVO.getReqDtlStatus());
    //    populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), invResultValidationRespVO.getVisitReason());

        populateMAP.put(sq.next(), invResultValidationRespVO.getRequisitionDNo());
        populateMAP.put(sq.next(), invResultValidationRespVO.getTestCode());
        populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      }

    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    }

    try {
      if (invResultValidationRespVO.getIsSendToMachine() != null && invResultValidationRespVO.getIsSendToMachine().equals("1")) {
        HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
        HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query2, populateMAP2);
      } else {
        HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
      }

    }
    catch (Exception e) {

      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    }
  }




  public void insertResultValidationDtl(ResultEntryVO voResultValidation, UserVO _UserVO) {
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
      String resultEntryvalue = voResultValidation.getResultEntryValue();
      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");


      populateMAP.put(sq.next(), voResultValidation.getResultEntryValue().equals("") ? "--" : resultEntryvalue);

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
      HelperMethodsDAO.excecuteUpdate(getTransactionContext()
          .getConnection(), query, populateMAP);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new HisDataAccessException("Exception While insertion in HIVT_PARAMETER_DTL Table");
    }
  }






  public List getResultValidfationDataList(ResultEntryVO InvResultEntryVO) {
    String query = null;
    ResultSet rs = null;

    Sequence sq = new Sequence();
    Map populateMap = new HashMap();

    List<TriStringObject> resultValidationList = new ArrayList<TriStringObject>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.HIVT_PARAMETER_DTL.WORKORDERRESULTENTRYDATA.RESULTVALIDATIONPROCESS";
    Connection conn = getTransactionContext().getConnection();

    populateMap.put(sq.next(), InvResultEntryVO.getRequisitionDNo());
    populateMap.put(sq.next(), InvResultEntryVO.getHospitalcode());




    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
      System.out.println("Query :WorkOrder List For Result Validation " + query);
    }
    catch (Exception e) {

      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }



    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);


      while (rs.next())
      {

        String parentCode = "";

        if (rs.getString(1).equals(rs.getString(3))) {
          parentCode = rs.getString(3);
        } else {
          parentCode = String.valueOf(rs.getString(3)) + rs.getString(1);
        }
        TriStringObject triStringObject = new TriStringObject(rs.getString(1), rs.getString(2), parentCode);
        if (resultValidationList == null) {
          resultValidationList = new ArrayList<TriStringObject>();
        }

        resultValidationList.add(triStringObject);
      }

    }
    catch (Exception e) {

      e.printStackTrace();
      throw new HisDataAccessException("RESULT Validation :getResultValidfationDataList  :" + e);
    }
    return resultValidationList;
  }








  public List getRequisiitionDataList(ResultEntryVO InvResultEntryVO) {
    String query = null;
    ResultSet rs = null;

    Sequence sq = new Sequence();
    Map populateMap = new HashMap();

    List<TriStringObject> resultValidationList = new ArrayList<TriStringObject>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.HIVT_REQUISITIONFORM_DTL.WORKORDERRESULTENTRYDATA.RESULTVALIDATIONPROCESS";
    Connection conn = getTransactionContext().getConnection();

    populateMap.put(sq.next(), InvResultEntryVO.getRequisitionDNo());
    populateMap.put(sq.next(), InvResultEntryVO.getLabCode());
    populateMap.put(sq.next(), InvResultEntryVO.getTestCode());
    populateMap.put(sq.next(), InvResultEntryVO.getHospitalcode());




    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
      System.out.println("Query :WorkOrder List For Result Validation " + query);
    }
    catch (Exception e) {

      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }



    try {
      rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);


      while (rs.next())
      {

        String parentCode = "";

        if (rs.getString(1).equals(rs.getString(3))) {
          parentCode = rs.getString(3);
        } else {
          parentCode = String.valueOf(rs.getString(3)) + rs.getString(1);
        }
        TriStringObject triStringObject = new TriStringObject(rs.getString(1), rs.getString(2), parentCode);
        if (resultValidationList == null) {
          resultValidationList = new ArrayList<TriStringObject>();
        }

        resultValidationList.add(triStringObject);
      }

    }
    catch (Exception e) {

      e.printStackTrace();
      throw new HisDataAccessException("RESULT Validation :getResultValidfationDataList  :" + e);
    }
    return resultValidationList;
  }






  public List<ResultEntryVO> gethyperdata(ResultEntryVO invresultentryvo, Node node) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List<ResultEntryVO> parameterCombo = invresultentryvo.getParameterCombo();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "GET_HYPERLINK_DATA";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    ValueObject[] valueObjects = null;
    List<ResultEntryVO> lstInvResultEntryVO = new ArrayList<ResultEntryVO>();



    populateMap.put(sq.next(), "1");

    populateMap.put(sq.next(), invresultentryvo.getHospitalcode());
    populateMap.put(sq.next(), invresultentryvo.getRequisitionDNo());
    populateMap.put(sq.next(), invresultentryvo.getRequisitionNo());
    populateMap.put(sq.next(), invresultentryvo.getTestParaMeterCode());

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
        valueObjects = HelperMethods.populateVOfrmRS(ResultEntryVO.class, rs);

        for (int i = 0; i < valueObjects.length; i++)
        {

          lstInvResultEntryVO.add((ResultEntryVO)valueObjects[i]);

          parameterCombo.add((ResultEntryVO)lstInvResultEntryVO.get(i));
        }

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











  public void insertResultValidationDtlInJobWorkorderData(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO) {
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
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatCRNo());

      populateMAP.put(sq.next(), invResultValidationRespVO.getRequisitionDNo());
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getRequisitionNo());
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatCRNo());
      populateMAP.put(sq.next(), invResultValidationRespVO.getTestCode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getLabCode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getSampleNo());
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatAge());
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatGender());

      populateMAP.put(sq.next(), (invResultValidationRespVO.getTestParaMeterCode() == null) ? "" : invResultValidationRespVO.getTestParaMeterCode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getReportAvailableAfter());

      populateMAP.put(sq.next(), (invResultValidationRespVO.getResultEntryValue() == null) ? "" : invResultValidationRespVO.getResultEntryValue());
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatVisitDat());
      if (invResultValidationRespVO.getPatVisitNo() != null &&
        invResultValidationRespVO.getPatVisitNo().equals("null"))
        invResultValidationRespVO.setPatVisitNo("");
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatVisitNo());
      populateMAP.put(sq.next(), invResultValidationRespVO.getLabNo());
      populateMAP.put(sq.next(), invResultValidationRespVO.getEpisodeCode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getDepartmentcode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatdeptunitcode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getRequisitionTypeCode());
      populateMAP.put(sq.next(), invResultValidationRespVO.getTestName());
      populateMAP.put(sq.next(), invResultValidationRespVO.getPatLabName());
      populateMAP.put(sq.next(), invResultValidationRespVO.getSampleName());
      populateMAP.put(sq.next(), invResultValidationRespVO.getTempSampleNo());

      if (invResultValidationRespVO.getDynamicTemplatePrintedGroup() != null && invResultValidationRespVO.getDynamicTemplatePrintedGroup().equals("1"))
      {
        populateMAP.put(sq.next(), invResultValidationRespVO.getGroupCode());
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





  public String setComboValueName(String testCode, String paraCode, String paraEntry) {
    String query = "";

    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "CHECK_COMBO_VALUE.HIVT_TEST_PARAMETER_COMBO_MST";
    String record = null;

    try {
      int value = Integer.parseInt(paraEntry);
      String.valueOf(value);
    }
    catch (Exception e) {

      paraEntry = "0";
    }


    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {

      return record;
    }

    try {
      populateMAP.put(sq.next(), "1");
      populateMAP.put(sq.next(), testCode);
      populateMAP.put(sq.next(), paraCode);
      populateMAP.put(sq.next(), paraEntry);


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




  public String setRangeFlagValueName(String testCode, ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";

    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.HIVT_LABORATORY_TEST_MST.PRINT_WITH_STANDARD_RANGES";
    String record = null;



    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {

      return record;
    }


    try {
      populateMAP.put(sq.next(), testCode);
      populateMAP.put(sq.next(), invresultentryvo.getLabCode());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());





    }
    catch (Exception e) {

      return record;
    }

    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);

      if (!rs.next())
      {
        return record;
      }


      record = rs.getString(1);





    }
    catch (Exception e) {

      return record;
    }
    return record;
  }








  //pm check focus
  public List setLabNoComboEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    ResultSet rs = null;
    List labNoCombo = new ArrayList();
    List finalLabNoCombo = new ArrayList();
    List<String> reportAvailAfter = new ArrayList<String>();
    List<String> reqStatus = new ArrayList<String>();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT.LABNO_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.LABNO_COMBO_COLL_DATE_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String orderBy = "order by labNo";
    String reqDtlValue = "";
    String conditionLab = "";


    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (8,13) ";
    }
    else {

      reqDtlValue = " and hivnum_reqdtl_status in (7) ";
    }
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and a.gnum_lab_code like '%' ";
    } else {
      conditionLab = " and a.gnum_lab_code=" + invresultentryvo.getLabCode();
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


    if (checkNumeric(_UserVO.getUserEmpID())) {

      populateMAP.put(sq.next(), _UserVO.getUserEmpID());
    } else {

      populateMAP.put(sq.next(), "0");
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

      for (int i = 0; i < reportAvailAfter.size(); i++) {



        if (((String)reqStatus.get(i)).contains("13") || ((String)reqStatus.get(i)).contains("26")) {

          if (((String)reportAvailAfter.get(i)).contains("2"))
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
    } catch (Exception e) {

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
    String queryKey = "SELECT.TESTWISE_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.TESTWISE_COMBO_COLL_DATE_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String orderBy = "order by testName";
    String reqDtlValue = "";
    String conditionLab = "";

    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (8,13) ";
    }
    else {

      reqDtlValue = " and hivnum_reqdtl_status in (7) ";
    }
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and a.gnum_lab_code like '%' ";
    } else {
      conditionLab = " and a.gnum_lab_code=" + invresultentryvo.getLabCode();
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


    if (checkNumeric(_UserVO.getUserEmpID())) {

      populateMAP.put(sq.next(), _UserVO.getUserEmpID());
    } else {

      populateMAP.put(sq.next(), "0");
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
          if (((String)reportAvailAfter.get(i)).contains("2"))
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
    String queryKey = "SELECT.SAMPLENO_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.SAMPLENO_COMBO_COLL_DATE_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String orderBy = "order by sampleNo";
    String reqDtlValue = "";
    String conditionLab = "";


    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (8,13) ";
    }
    else {

      reqDtlValue = " and hivnum_reqdtl_status in (7) ";
    }
    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and a.gnum_lab_code like '%' ";
    } else {
      conditionLab = " and a.gnum_lab_code=" + invresultentryvo.getLabCode();
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


    if (checkNumeric(_UserVO.getUserEmpID())) {

      populateMAP.put(sq.next(), _UserVO.getUserEmpID());
    } else {

      populateMAP.put(sq.next(), "0");
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
        return sampleNoCombo;
      }


      rs.beforeFirst();
      sampleNoCombo = HelperMethodsDAO.getAlOfEntryObjects(rs);

      rs.beforeFirst();
      while (rs.next()) {
        reportAvailAfter.add(rs.getString(3));
        reqStatus.add(rs.getString(4));
      }

      for (int i = 0; i < reportAvailAfter.size(); i++) {



        if (((String)reqStatus.get(i)).contains("13") || ((String)reqStatus.get(i)).contains("26")) {

          if (((String)reportAvailAfter.get(i)).contains("2"))
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
    String queryKey = "SELECT.TESTGROUP_COMBO_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String queryKeyCollDate = "SELECT.TESTGROUP_COMBO_COLL_DATE_RESULT_VALIDATION_HIVT_REQUISITION_HEADER";
    String orderBy = "order by groupName";
    String reqDtlValue = "";
    String conditionLab = "";

    if (invresultentryvo.getNewEntry() != null && invresultentryvo.getNewEntry().equals("2")) {
      reqDtlValue = " and hivnum_reqdtl_status in (8,13) ";
    }
    else {

      reqDtlValue = " and hivnum_reqdtl_status in (7) ";
    }

    if (invresultentryvo.getLabCode().equals("%")) {
      conditionLab = " and a.gnum_lab_code like '%' ";
    } else {
      conditionLab = " and a.gnum_lab_code=" + invresultentryvo.getLabCode();
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


    if (checkNumeric(_UserVO.getUserEmpID())) {

      populateMAP.put(sq.next(), _UserVO.getUserEmpID());
    } else {

      populateMAP.put(sq.next(), "0");
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
          if (((String)reportAvailAfter.get(i)).contains("2"))
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
      e.printStackTrace();
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
      populateMAP.put(sq.next(), "2");

      populateMAP.put(sq.next(), voResultEntry.getPatName());
      populateMAP.put(sq.next(), voResultEntry.getLabCode());
      populateMAP.put(sq.next(), voResultEntry.getPatLabName());
      populateMAP.put(sq.next(), voResultEntry.getPatAge().trim());
      populateMAP.put(sq.next(), voResultEntry.getPatGender());
      populateMAP.put(sq.next(), voResultEntry.getTestName());

      populateMAP.put(sq.next(), voResultEntry.getSampleNo());
      populateMAP.put(sq.next(), voResultEntry.getLabNo());
      populateMAP.put(sq.next(), voResultEntry.getRefRange());
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



  public void updateAddendumRemarkInRequisitionHeader(String reqNo, String addendumRemark, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.ADDENDUMREMARK.HIVT_REQUISITION_HEADER";

    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }



    try {
      populateMAP.put(sq.next(), addendumRemark);

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





  public String checkcannedCode(InvResultValidationRespFB fb, UserVO _UserVO) {
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




  public String checkcannedName(InvResultValidationRespFB fb, UserVO _UserVO) {
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




  public void insertModifyCanned(InvResultValidationRespFB fb, UserVO _UserVO) {
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



  public String fetchCode(InvResultValidationRespFB fb, UserVO _UserVO) {
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




  public void insertUserEnterCode(InvResultValidationRespFB fb, UserVO _UserVO, String code) {
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








  public void insertHyperLinkDetails(ResultEntryVO voResultEntry, UserVO _UserVO, String antibioticCode, String diagnosisval, String organismcode, String testparacode) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.VALIDATION.HYPERLINK.HIVT_WO_ORGAANTIBIOTICS_DTL";



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



  public String getorganismcode(ResultEntryVO invresultentryvo, Node node) {
    String query = "";

    Map populateMap = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "GET_HYPERLINK_DATA_OGANISM_CODE";

    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }


    try {
      populateMap.put(sq.next(), "1");

      populateMap.put(sq.next(), invresultentryvo.getHospitalcode());
      populateMap.put(sq.next(), invresultentryvo.getRequisitionDNo());
      populateMap.put(sq.next(), invresultentryvo.getRequisitionNo());
      populateMap.put(sq.next(), invresultentryvo.getTestParaMeterCode());
    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
    }
    String record = null;

    try {
      ResultSet rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMap);
      while (rs.next())
      {
        record = rs.getString(1);
      }
    }
    catch (Exception e) {

      if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    }
    return record;
  }





  public List<invAntibioticProcessVO> getantibioticlist(ResultEntryVO invresultentryvo, String organismcode, ResultEntryVO tempvo, String reqdno, String tempparacode) {
    String query = "";
    Map populateMap = new HashMap();
    ResultSet rs = null;
    List<invAntibioticProcessVO> getantibioticlistdynamic = invresultentryvo.getGetantibioticlistdynamic();

    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "COMBO.ANTIBITIC_NAME.HIVT_ORGANISM_ANTIBIOTIC_MAPPING_MST";
    Sequence sq = new Sequence();
    Connection conn = getTransactionContext().getConnection();
    ValueObject[] valueObjects = null;
    List<invAntibioticProcessVO> lstInvResultEntryVO = new ArrayList<invAntibioticProcessVO>();

    populateMap.put(sq.next(), invresultentryvo.getHospitalcode());
    populateMap.put(sq.next(), organismcode);
    populateMap.put(sq.next(), "1");
    populateMap.put(sq.next(), invresultentryvo.getHospitalcode());
    populateMap.put(sq.next(), organismcode);




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
        valueObjects = HelperMethods.populateVOfrmRS(invAntibioticProcessVO.class, rs);

        for (int i = 0; i < valueObjects.length; i++)
        {

          lstInvResultEntryVO.add((invAntibioticProcessVO)valueObjects[i]);
          ((invAntibioticProcessVO)lstInvResultEntryVO.get(i)).setReqDno(invresultentryvo.getRequisitionDNo());
          ((invAntibioticProcessVO)lstInvResultEntryVO.get(i)).setTestParaCode(invresultentryvo.getTestParaMeterCode());
          getantibioticlistdynamic.add((invAntibioticProcessVO)lstInvResultEntryVO.get(i));
        }

      }

    } catch (Exception e) {

      if (e.getClass() != HisRecordNotFoundException.class)
      {



        throw new HisDataAccessException("HisDataAccessException:: " + e); }
    }
    return getantibioticlistdynamic;
  }



  public List LabComboForResultValidationAddendum(ResultEntryVO InvResultEntryVO, UserVO _UserVO) {
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
      parameterCombo = HelperMethodsDAO.getAlOfEntryObjectsInvLabs(rs);

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






  public void updatepidvaluesresultwithoutchange(InvResultValidationRespVO invResultValidationRespVO, UserVO _UserVO) {
    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE_HIVT_ICTC_DTL_RESULT_VALUE_WITHOPUT_CHANGE";


    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" +
          e);
    }





    try {
      populateMAP.put(sq.next(), invResultValidationRespVO.getReqDtlStatus());
      populateMAP.put(sq.next(), invResultValidationRespVO.getRequisitionNo());





















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





  public void commentsupdateResultValidationInRequisitionDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {
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




  public void updateResultValidationInRequisitionDtlDraftLog(ResultEntryVO voResultEntry, UserVO _UserVO) {
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

  public static boolean checkNumeric(String value) {
    boolean res = false;
    try {
      res = value.matches("-?\\d+(\\.\\d+)?");
      if (res) {
        res = true;
      }
    } catch (Exception e) {
      res = false;
    }

    return res;
  }



  public void updateResultValidationInRequisitionDtlfileupload(ResultEntryVO voResultValidation, UserVO _UserVO) {
    String query = "";
    String query2 = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    Map populateMAP2 = new HashMap();
    Sequence sq2 = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "UPDATE.REQDTLS.RESULT.VALIDATION.HIVT_REQUISITION_DTLS_FILEUPLOAD";
    String queryKeySendToMachine = "UPDATE.REQDTLS.RESULT.VALIDATION.HIVT_REQUISITION_DTLS_SEND_TO_MACHINE";
    String queryKeySampleUpdate = "UPDATE.HMIT_SAMPLE_DTL_SEND_TO_MACHINE";

    try {
      if (voResultValidation.getIsSendToMachine() != null && voResultValidation.getIsSendToMachine().equals("1")) {
        query = HelperMethodsDAO.getQuery(filename, queryKeySendToMachine);
        query2 = HelperMethodsDAO.getQuery(filename, queryKeySampleUpdate);
      } else {
        query = HelperMethodsDAO.getQuery(filename, queryKey);
      }

    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
    }

    try {
      if (voResultValidation.getIsSendToMachine() != null && voResultValidation.getIsSendToMachine().equals("1")) {
        populateMAP.put(sq.next(), "1");
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), "1");
        populateMAP.put(sq.next(), voResultValidation.getReqDtlStatus());
        populateMAP.put(sq.next(), voResultValidation.getVisitReason());

        populateMAP.put(sq.next(), voResultValidation.getRequisitionDNo());
        populateMAP.put(sq.next(), voResultValidation.getTestCode());
        populateMAP.put(sq.next(), _UserVO.getHospitalCode());


        populateMAP2.put(sq2.next(), "0");
        populateMAP2.put(sq2.next(), "1");
        populateMAP2.put(sq2.next(), "1");
        populateMAP2.put(sq2.next(), "1");
        populateMAP2.put(sq2.next(), _UserVO.getHospitalCode());
        populateMAP2.put(sq2.next(), voResultValidation.getRequisitionDNo());
        populateMAP2.put(sq2.next(), voResultValidation.getRequisitionDNo());
        populateMAP2.put(sq2.next(), _UserVO.getHospitalCode());

      }
      else {


        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), voResultValidation.getReqDtlStatus());
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), voResultValidation.getVisitReason());


        populateMAP.put(sq.next(), voResultValidation.getRequisitionDNo());
        populateMAP.put(sq.next(), voResultValidation.getTestCode());
        populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      }

    }
    catch (Exception e) {

      throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
    }

    try {
      if (voResultValidation.getIsSendToMachine() != null && voResultValidation.getIsSendToMachine().equals("1")) {
        HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
        HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query2, populateMAP2);
      } else {
        HelperMethodsDAO.excecuteUpdate(getTransactionContext().getConnection(), query, populateMAP);
      }

    }
    catch (Exception e) {

      System.out.println(e.getMessage());
      throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
    }
  }



  public void updateIndicationInRequisitionheader(ResultEntryVO voResultValidation, UserVO _UserVO) {
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
      populateMAP.put(sq.next(), voResultValidation.getVisitReason());
      populateMAP.put(sq.next(), _UserVO.getSeatId());

      populateMAP.put(sq.next(), voResultValidation.getRequisitionDNo());
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());

      populateMAP.put(sq.next(), voResultValidation.getLabCode());
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





  public List<ResultEntryVO> getdata( String hospcode) {
	    ResultSet rs = null;
	    String query = "";
	    Map populateMAP = new HashMap();
	    String fromAlpha = "";
	    String toAlpha = "";
	    String filename = "new_investigation.transactions.investigationTransactionQuery";
	    String queryKey = "";
	      queryKey = "SELECT_DATA_UPDATE";





	    try {

	    	query = HelperMethodsDAO.getQuery(filename, queryKey);


	    }
	    catch (Exception e) {

	      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	    }

	    System.out.println("Query ---------> " + query);
	    Sequence sq = new Sequence();

	    try {
	      populateMAP.put(sq.next(), hospcode);
	      populateMAP.put(sq.next(), hospcode);



	    }
	    catch (Exception e) {

	      throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
	    }



	    	ValueObject[] valueObjects = null;
	    List<ResultEntryVO> lstInvResultValidationRespVO = new ArrayList<ResultEntryVO>();
	    List<ResultEntryVO> finalLststInvResultValidationRespVO = new ArrayList<ResultEntryVO>();

	    try{
	      rs = HelperMethodsDAO.executeQuery(getTransactionContext().getConnection(), query, populateMAP);
	      if (rs.next())
	      {




	        rs.beforeFirst();
	        valueObjects = HelperMethods.populateVOfrmRS(ResultEntryVO.class, rs);

	        for (int i = 0; i < valueObjects.length; i++)
	        {

	            lstInvResultValidationRespVO.add((ResultEntryVO)valueObjects[i]);

	        //   if(lstInvResultValidationRespVO.get(i).getRequisitionDNo().equals("961011000119101110001001"))
	            finalLststInvResultValidationRespVO.add((ResultEntryVO)lstInvResultValidationRespVO.get(i));



	        }

	      }

  }
	     catch (Exception e) {

	      if (e.getClass() == HisRecordNotFoundException.class)
	      {
	        throw new HisRecordNotFoundException(e.getMessage());
	      }
	      throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
	    }
	    return finalLststInvResultValidationRespVO;
	  }





  public void updaterefrange(String reqdno,String testcode,String parantparacode, String range,String isrange,String hoscode) {
	    String query = "";
	    String query2 = "";
	    Map populateMAP = new HashMap();
	    Sequence sq = new Sequence();
	    Map populateMAP2 = new HashMap();
	    Sequence sq2 = new Sequence();
	    String filename = "new_investigation.transactions.investigationTransactionQuery";
	    String queryKey = "UPDATE.REF.RANGE";

	    try {
	       query = HelperMethodsDAO.getQuery(filename, queryKey);


	    }
	    catch (Exception e) {

	      throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	    }

	    try {
	          populateMAP.put(sq.next(), range);
	        populateMAP.put(sq.next(), isrange);
	        populateMAP.put(sq.next(), reqdno);
	        populateMAP.put(sq.next(), testcode);
	        populateMAP.put(sq.next(), parantparacode);
	        populateMAP.put(sq.next(), hoscode);



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


  public void insertModifyCanned1(InvResultValidationRespFB fb, UserVO _UserVO) {
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


  public String checkisparmeterexist(InvResultValidationRespVO resultEntryVO,String testcode,String parametercode,UserVO _UserVO) {
	    String query = "";

	    Map populateMAP = new HashMap();
	    Sequence sq = new Sequence();
	    String filename = "new_investigation.transactions.investigationTransactionQuery";
	    String queryKey = "SELECT_COUNT_RESULT_ENTRY_DETAIL_HIVT_PARAMETER_DTL";
	    String record = "1";



	    try {
	      query = HelperMethodsDAO.getQuery(filename, queryKey);
	    }
	    catch (Exception e) {

	      return record;
	    }

	    try {
	      populateMAP.put(sq.next(), resultEntryVO.getRequisitionDNo());
	      populateMAP.put(sq.next(), testcode);
	      populateMAP.put(sq.next(), parametercode);
	      populateMAP.put(sq.next(), testcode+""+parametercode);
	    //  populateMAP.put(sq.next(), parametercode+""+parametercode);
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


public String checkisparmeterexistTextarea(InvResultValidationRespVO resultEntryVO,String testcode,String parametercode) {
	    String query = "";

    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_COUNT_PARAMETER_DTL_TEXTAREA";
	    String record = null;



	    try {
	      query = HelperMethodsDAO.getQuery(filename, queryKey);
	    }
	    catch (Exception e) {

	      return record;
	    }

	    try {

	      populateMAP.put(sq.next(), parametercode);
	      populateMAP.put(sq.next(), testcode);



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



public String gettextareadefaultvvalue(InvResultValidationRespVO resultEntryVO,String testcode,String parametercode) {
	    String query = "";

    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "SELECT_DEFAULT_PARAMETER_DTL_TEXTAREA";
    String record = "--";



    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {

      return record;
    }

    try {
    //  populateMAP.put(sq.next(), resultEntryVO.getRequisitionDNo());
    	   populateMAP.put(sq.next(), testcode);
 	      populateMAP.put(sq.next(), parametercode);
    //  populateMAP.put(sq.next(), parametercode+""+parametercode);


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



public void insertResultEntryDtl_textareaeditor(InvResultValidationRespVO voResultEntry, UserVO _UserVO,String testcode,String paracode,String defaultval) {
	    String query = "";
    Map populateMAP = new HashMap();
    Sequence sq = new Sequence();
    String filename = "new_investigation.transactions.investigationTransactionQuery";
    String queryKey = "INSERT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL_TEXTAREA";


    try {
      query = HelperMethodsDAO.getQuery(filename, queryKey);
    }
    catch (Exception e) {
      throw new HisApplicationExecutionException(
          "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" +
          e);
    }



    try {
      String resultEntryvalue = defaultval;
      resultEntryvalue = resultEntryvalue.replace("&lt;", "&amp;lt;");
      resultEntryvalue = resultEntryvalue.replace("&gt;", "&amp;gt;");



      String value = defaultval.equals("") ? "--" : resultEntryvalue;

      populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());

      populateMAP.put(sq.next(), testcode);
      populateMAP.put(sq.next(), testcode+paracode);
      populateMAP.put(sq.next(), _UserVO.getHospitalCode());
      populateMAP.put(sq.next(), _UserVO.getSeatId());
      populateMAP.put(sq.next(), value);
      populateMAP.put(sq.next(), paracode);
      populateMAP.put(sq.next(), "1");
     // populateMAP.put(sq.next(), null);
     // populateMAP.put(sq.next(), null);
      populateMAP.put(sq.next(), "0");

    //  populateMAP.put(sq.next(), null);
    //  populateMAP.put(sq.next(), null);









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



}
