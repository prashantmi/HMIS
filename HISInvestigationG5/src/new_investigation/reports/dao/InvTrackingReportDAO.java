package new_investigation.reports.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;
import new_investigation.vo.InvTrackingReportVO;
import new_investigation.vo.SampleAcceptanceVO;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class InvTrackingReportDAO extends DataAccessObject implements InvTrackingReportDAOI {

	public InvTrackingReportDAO(JDBCTransactionContext tx) {
		super(tx);
	}

	public InvTrackingReportVO AjaxGetPatDetails(InvTrackingReportVO vo, UserVO userVO) {


		String filename=InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO;
		String queryKey1="SELECT_INV_TRACKING_PAT_DETAILS_OPD_ONCRNO_HIVT_REQUISITION_DTL";
		String queryKey2="SELECT_INV_TRACKING_PAT_DETAILS_IPD_ONCRNO_HIVT_REQUISITION_DTL";

		String queryKey3="SELECT_INV_TRACKING_PAT_DETAILS_OPD_ONBILLNO_HIVT_REQUISITION_DTL";
		String queryKey4="SELECT_INV_TRACKING_PAT_DETAILS_IPD_ONBILLNO_HIVT_REQUISITION_DTL";

		String query1="";
		String query2="";

		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();

		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		InvTrackingReportVO vo2 = new InvTrackingReportVO();

		try
		{
			if(vo.getSearchType().equals("1")) {
				query1 = HelperMethodsDAO.getQuery(filename, queryKey1);
				query2 = HelperMethodsDAO.getQuery(filename, queryKey2);
			}
			else if(vo.getSearchType().equals("2"))
			{
				query1 = HelperMethodsDAO.getQuery(filename, queryKey3);
				query2 = HelperMethodsDAO.getQuery(filename, queryKey4);
			}

		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}

		try
		{
			if(vo.getSearchType().equals("1")) {
				populateMap.put(sq.next(), vo.getCrNo());
			}
			else if(vo.getSearchType().equals("2"))
			{
				populateMap.put(sq.next(), vo.getBillNo());
			}

			rs = HelperMethodsDAO.executeQuery(conn, query1, populateMap);

			if(!rs.next()) { }
			else {
				rs.beforeFirst();
				while(rs.next()) {
					HelperMethods.populateVOfrmRS(vo2, rs);
				}
			}

			/* Check If IPD Patient and get IPD Data*/
			if(vo2.getPatStatusCode().equals("2")){
				sq.reset();

				if(vo.getSearchType().equals("1")) {
					populateMap.put(sq.next(), userVO.getHospitalCode());
					populateMap.put(sq.next(), vo.getCrNo());
				}
				else if(vo.getSearchType().equals("2"))
				{
					populateMap.put(sq.next(), userVO.getHospitalCode());
					populateMap.put(sq.next(), vo.getBillNo());
				}

				rs = HelperMethodsDAO.executeQuery(conn, query2, populateMap);

				if(!rs.next()) { }
				else {
					rs.beforeFirst();
					while(rs.next()) {
						HelperMethods.populateVOfrmRS(vo2, rs);
					}
				}
			}
		}

		catch (Exception e)
		{
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		}
		return vo2;
	}


	public List<InvTrackingReportVO> AjaxGetPatSampleReqnList(InvTrackingReportVO vo, UserVO userVO) {

		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO;
		
		String queryKeyReqnNew = "SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_HIVT_REQUISITION_DTL_NEW";
		String queryKeyReqnHst = "SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_HIVT_REQUISITION_DTL_HST";

		String queryNew, queryHst, query="";
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();

		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<InvTrackingReportVO> listInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
		
		try {
	    	queryNew = HelperMethodsDAO.getQuery(filename, queryKeyReqnNew);
	    	queryHst = HelperMethodsDAO.getQuery(filename, queryKeyReqnHst);
	    }
	    catch (Exception e) {
	      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	    }
		
		String orderByDateClause=" hivt_entry_date desc ";
	    String dateTypefilterClause="";
	    
	    //Condition for filtering by dateType
	    if(vo.getDateFiltersOrBoth()!=null)
	    if (vo.getDateFiltersOrBoth().equals("0") || vo.getDateFiltersOrBoth().equals("1")) {

	    	if(vo.getDateTypeCode().equals("1")){
	    		dateTypefilterClause = " AND TRUNC(a.hivt_entry_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivt_entry_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivt_entry_date desc ";
	    	}

	    	if(vo.getDateTypeCode().equals("2")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_coll_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_coll_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_coll_date_time desc ";
	    	}

	    	if(vo.getDateTypeCode().equals("3")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_acceptance_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_acceptance_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_acceptance_date_time desc ";
	    	}//hivtdt_pat_accpt_date_time 
	    	
	    	if(vo.getDateTypeCode().equals("4")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_result_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_result_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_result_date desc ";
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("5")){
	    		dateTypefilterClause = " AND TRUNC(a.hivtdt_res_val_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivtdt_res_val_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivtdt_res_val_date desc ";
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("6")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_report_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_report_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_report_date desc ";
	    	}
	    }
	    
	    queryNew = String.valueOf(queryNew) + dateTypefilterClause;
	    queryHst = String.valueOf(queryHst) + dateTypefilterClause;
	    
	    //Condition for filtering by CrNo
	    if (vo.getCrNo()!= null && !vo.getCrNo().equals(""))
	    {	String crNoClause = " and a.hrgnum_puk_reqd=" + vo.getCrNo();
	    	queryNew = String.valueOf(queryNew) + crNoClause;
	    	queryHst = String.valueOf(queryHst) + crNoClause;
	    }
	    
	    //Condition for filtering by BillNo
	    if (vo.getBillNo()!= null && !vo.getBillNo().equals(""))
	    {	String billNoClause = " and a.gnum_bill_no=" + vo.getBillNo();
	    	queryNew = String.valueOf(queryNew) + billNoClause;
	    	queryHst = String.valueOf(queryHst) + billNoClause;
	    }
	    
	    //Condition for filtering by requisitionNo
	    if (vo.getRequisitionNo()!= null && !vo.getRequisitionNo().equals(""))
	    {	String requisitionNoClause = " and a.hivnum_req_no=" + vo.getRequisitionNo();
	    	queryNew = String.valueOf(queryNew) + requisitionNoClause;
	    	queryHst = String.valueOf(queryHst) + requisitionNoClause;
	    }
	    
	    //Condition for filtering by Test Code
	    if (vo.getTestCode()!= null && !vo.getTestCode().equals(""))
	    {	String testCodeClause = " and a.gnum_test_code =" +vo.getTestCode()+" AND ( hgnum_group_type = 0 OR hgnum_group_type IS NULL) ";
	    	queryNew = String.valueOf(queryNew) + testCodeClause;
	    	queryHst = String.valueOf(queryHst) + testCodeClause;
	    }
	    
	    //Condition for filtering by Group Code
	    if (vo.getGroupCode()!= null && !vo.getGroupCode().equals(""))
	    {	String groupCodeClause = " and a.hgnum_group_code ="+vo.getGroupCode()+" AND hgnum_group_type = 1 ";
	    	queryNew = String.valueOf(queryNew) + groupCodeClause;
	    	queryHst = String.valueOf(queryHst) + groupCodeClause;
	    }
	    
	    
	    //Condition for filtering by Requisition Status
	    String requisitionStatusClause = "";
	    if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("1")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (2,5) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("2")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (3) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("3")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (6) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("4")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (7) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("5")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (8,13) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("6")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (14) ";
	    }
	    
	    queryNew = String.valueOf(queryNew) + requisitionStatusClause;
	    queryHst = String.valueOf(queryHst) + requisitionStatusClause;
	    
	    if(vo.getDataFromArchival().equals("1")) {
	    	query = String.valueOf(queryNew)+" UNION "+String.valueOf(queryHst);
	    } else {
	    	query = String.valueOf(queryNew);
	    }
	    
	    //Order by Clause
	    query = String.valueOf(query) + " ORDER BY gbnum_priority_code desc, sampleNo, groupName, "+orderByDateClause;
	    System.out.println("Query Print:: " + query);
	    
		try
		{	
			if(vo.getDataFromArchival().equals("0")) {
				populateMap.put(sq.next(), userVO.getHospitalCode());
				if(vo.getDateFiltersOrBoth()!=null)
				if (vo.getDateFiltersOrBoth().equals("1")) {
					populateMap.put(sq.next(), vo.getFromDate());
					populateMap.put(sq.next(), vo.getToDate());
			    }
		    } else {
		    	for(int count=0; count<2; count++) {
			    	populateMap.put(sq.next(), userVO.getHospitalCode());
			    	if(vo.getDateFiltersOrBoth()!=null)
					if (vo.getDateFiltersOrBoth().equals("1")) {
						populateMap.put(sq.next(), vo.getFromDate());
						populateMap.put(sq.next(), vo.getToDate());
				    }
		    	}
		    }
			
			rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if(!rs.next()) { }
			else {
				rs.beforeFirst();
				while(rs.next()) {
					InvTrackingReportVO vo2 = new InvTrackingReportVO();
					HelperMethods.populateVOfrmRS(vo2, rs);

					listInvTrackingReportVO.add(vo2);
				}
			}

		}
		catch (Exception e)
		{
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		 }

		return listInvTrackingReportVO;
	}

	public List<InvTrackingReportVO> AjaxGetPatPatientReqnList(InvTrackingReportVO vo, UserVO userVO) {

		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO;
		
		String queryKeyReqnNew = "SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_HIVT_REQUISITION_DTL_NEW";
		String queryKeyReqnHst = "SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_HIVT_REQUISITION_DTL_HST";

		String queryNew, queryHst, query="";
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();

		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<InvTrackingReportVO> listInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
		
		try {
	    	queryNew = HelperMethodsDAO.getQuery(filename, queryKeyReqnNew);
	    	queryHst = HelperMethodsDAO.getQuery(filename, queryKeyReqnHst);
	    }
	    catch (Exception e) {
	      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	    }
		
		String orderByDateClause=" hivt_entry_date desc ";
	    String dateTypefilterClause="";
	    
	    //Condition for filtering by dateType
	    if(vo.getDateFiltersOrBoth()!=null)
	    if (vo.getDateFiltersOrBoth().equals("0") || vo.getDateFiltersOrBoth().equals("1")) {

	    	if(vo.getDateTypeCode().equals("1")){
	    		dateTypefilterClause = " AND TRUNC(a.hivt_entry_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivt_entry_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivt_entry_date desc ";
	    	}

	    	if(vo.getDateTypeCode().equals("2")){
	    		dateTypefilterClause = " AND TRUNC(a.hivtdt_pat_accpt_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivtdt_pat_accpt_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivtdt_pat_accpt_date_time desc ";
	    	}

	    	if(vo.getDateTypeCode().equals("3")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_acceptance_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_acceptance_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivtdt_pat_accpt_date_time desc ";//hivdt_acceptance_date_time
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("4")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_result_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_result_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_result_date desc ";
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("5")){
	    		dateTypefilterClause = " AND TRUNC(a.hivtdt_res_val_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivtdt_res_val_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivtdt_res_val_date desc ";
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("6")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_report_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_report_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_report_date desc ";
	    	}
	    }
	    
	    queryNew = String.valueOf(queryNew) + dateTypefilterClause;
	    queryHst = String.valueOf(queryHst) + dateTypefilterClause;
	    
	    //Condition for filtering by CrNo
	    if (vo.getCrNo()!= null && !vo.getCrNo().equals(""))
	    {	String crNoClause = " and a.hrgnum_puk_reqd=" + vo.getCrNo();
	    	queryNew = String.valueOf(queryNew) + crNoClause;
	    	queryHst = String.valueOf(queryHst) + crNoClause;
	    }
	    
	    //Condition for filtering by BillNo
	    if (vo.getBillNo()!= null && !vo.getBillNo().equals(""))
	    {	String billNoClause = " and a.gnum_bill_no=" + vo.getBillNo();
	    	queryNew = String.valueOf(queryNew) + billNoClause;
	    	queryHst = String.valueOf(queryHst) + billNoClause;
	    }
	    
	    //Condition for filtering by requisitionNo
	    if (vo.getRequisitionNo()!= null && !vo.getRequisitionNo().equals(""))
	    {	String requisitionNoClause = " and a.hivnum_req_no=" + vo.getRequisitionNo();
	    	queryNew = String.valueOf(queryNew) + requisitionNoClause;
	    	queryHst = String.valueOf(queryHst) + requisitionNoClause;
	    }
	    
	    //Condition for filtering by Test Code
	    if (vo.getTestCode()!= null && !vo.getTestCode().equals(""))
	    {	String testCodeClause = " and a.gnum_test_code =" +vo.getTestCode()+" AND ( hgnum_group_type = 0 OR hgnum_group_type IS NULL) ";
	    	queryNew = String.valueOf(queryNew) + testCodeClause;
	    	queryHst = String.valueOf(queryHst) + testCodeClause;
	    }
	    
	    //Condition for filtering by Group Code
	    if (vo.getGroupCode()!= null && !vo.getGroupCode().equals(""))
	    {	String groupCodeClause = " and hgnum_group_code ="+vo.getGroupCode()+" AND hgnum_group_type = 1 ";
	    	queryNew = String.valueOf(queryNew) + groupCodeClause;
	    	queryHst = String.valueOf(queryHst) + groupCodeClause;
	    }
	    
	    
	    //Condition for filtering by Requisition Status
	    String requisitionStatusClause = "";
	    if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("1")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (2,5) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("2")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (3) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("3")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (6) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("4")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (7) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("5")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (8,13) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("6")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (14) ";
	    }
	    
	    queryNew = String.valueOf(queryNew) + requisitionStatusClause;
	    queryHst = String.valueOf(queryHst) + requisitionStatusClause;
	    
	    if(vo.getDataFromArchival().equals("1")) {
	    	query = String.valueOf(queryNew)+" UNION "+String.valueOf(queryHst);
	    } else {
	    	query = String.valueOf(queryNew);
	    }
	    
	    //Order by Clause
	    query = String.valueOf(query) + " ORDER BY gbnum_priority_code desc, accessionNo, groupName, "+orderByDateClause;
	    System.out.println("Query Print:: " + query);
	    
		try
		{	
			if(vo.getDataFromArchival().equals("0")) {
				populateMap.put(sq.next(), userVO.getHospitalCode());
				if(vo.getDateFiltersOrBoth()!=null)
				if (vo.getDateFiltersOrBoth().equals("1")) {
					populateMap.put(sq.next(), vo.getFromDate());
					populateMap.put(sq.next(), vo.getToDate());
			    }
		    } else {
		    	for(int count=0; count<2; count++) {
			    	populateMap.put(sq.next(), userVO.getHospitalCode());
			    	if(vo.getDateFiltersOrBoth()!=null)
					if (vo.getDateFiltersOrBoth().equals("1")) {
						populateMap.put(sq.next(), vo.getFromDate());
						populateMap.put(sq.next(), vo.getToDate());
				    }
		    	}
		    }

			rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if(!rs.next()) { }
			else {
				rs.beforeFirst();
				while(rs.next()) {
					InvTrackingReportVO vo2 = new InvTrackingReportVO();
					HelperMethods.populateVOfrmRS(vo2, rs);

					listInvTrackingReportVO.add(vo2);
				}
			}

		}
		catch (Exception e)
		{
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		 }

		return listInvTrackingReportVO;
	}


	public List<InvTrackingReportVO> AjaxGetReqnTestParamList(InvTrackingReportVO vo, UserVO userVO) {

		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO;
		String queryKeyReqnNew = "SELECT_INV_TRACKING_REPORT_TESTPARAM_HIVT_REQUISITION_DTL_NEW";
		String queryKeyReqnHst = "SELECT_INV_TRACKING_REPORT_TESTPARAM_HIVT_REQUISITION_DTL_HST";
		
		String queryNew, queryHst, query="";
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();

		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<InvTrackingReportVO> listInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
		
		try {
	    	queryNew = HelperMethodsDAO.getQuery(filename, queryKeyReqnNew);
	    	queryHst = HelperMethodsDAO.getQuery(filename, queryKeyReqnHst);
	    }
	    catch (Exception e) {
	      throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	    }
		
		String orderByDateClause=" hivt_entry_date desc ";
	    String dateTypefilterClause="";
	    
	    //Condition for filtering by dateType
	    if(vo.getDateFiltersOrBoth()!=null)
	    if (vo.getDateFiltersOrBoth().equals("0") || vo.getDateFiltersOrBoth().equals("1")) {

	    	if(vo.getDateTypeCode().equals("1")){
	    		dateTypefilterClause = " AND TRUNC(a.hivt_entry_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivt_entry_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivt_entry_date desc ";
	    	}

	    	if(vo.getDateTypeCode().equals("2")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_coll_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_coll_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_coll_date_time desc ";
	    	}

	    	if(vo.getDateTypeCode().equals("3")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_acceptance_date_time) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_acceptance_date_time) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_acceptance_date_time desc ";
	    	}//hivtdt_pat_accpt_date_time 
	    	
	    	if(vo.getDateTypeCode().equals("4")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_result_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_result_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_result_date desc ";
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("5")){
	    		dateTypefilterClause = " AND TRUNC(a.hivtdt_res_val_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivtdt_res_val_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivtdt_res_val_date desc ";
	    	}
	    	
	    	if(vo.getDateTypeCode().equals("6")){
	    		dateTypefilterClause = " AND TRUNC(a.hivdt_report_date) >= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) AND TRUNC(a.hivdt_report_date) <= TRUNC(TO_DATE(?, 'DD-Mon-YYYY')) ";
	    		orderByDateClause = " hivdt_report_date desc ";
	    	}
	    }
	    
	    queryNew = String.valueOf(queryNew) + dateTypefilterClause;
	    queryHst = String.valueOf(queryHst) + dateTypefilterClause;
	    
	    //Condition for filtering by CrNo
	    if (vo.getCrNo()!= null && !vo.getCrNo().equals(""))
	    {	String crNoClause = " and a.hrgnum_puk_reqd=" + vo.getCrNo();
	    	queryNew = String.valueOf(queryNew) + crNoClause;
	    	queryHst = String.valueOf(queryHst) + crNoClause;
	    }
	    
	    //Condition for filtering by BillNo
	    if (vo.getBillNo()!= null && !vo.getBillNo().equals(""))
	    {	String billNoClause = " and a.gnum_bill_no=" + vo.getBillNo();
	    	queryNew = String.valueOf(queryNew) + billNoClause;
	    	queryHst = String.valueOf(queryHst) + billNoClause;
	    }
	    
	    //Condition for filtering by requisitionNo
	    if (vo.getRequisitionNo()!= null && !vo.getRequisitionNo().equals(""))
	    {	String requisitionNoClause = " and a.hivnum_req_no=" + vo.getRequisitionNo();
	    	queryNew = String.valueOf(queryNew) + requisitionNoClause;
	    	queryHst = String.valueOf(queryHst) + requisitionNoClause;
	    }
	    
	    //Condition for filtering by Test Code
	    if (vo.getTestCode()!= null && !vo.getTestCode().equals(""))
	    {	String testCodeClause = " and a.gnum_test_code =" +vo.getTestCode()+" AND ( hgnum_group_type = 0 OR hgnum_group_type IS NULL) ";
	    	queryNew = String.valueOf(queryNew) + testCodeClause;
	    	queryHst = String.valueOf(queryHst) + testCodeClause;
	    }
	    
	    //Condition for filtering by Group Code
	    if (vo.getGroupCode()!= null && !vo.getGroupCode().equals(""))
	    {	String groupCodeClause = " and  a.hgnum_group_code ="+vo.getGroupCode()+" AND hgnum_group_type = 1 ";
	    	queryNew = String.valueOf(queryNew) + groupCodeClause;
	    	queryHst = String.valueOf(queryHst) + groupCodeClause;
	    }
	    
	    
	    //Condition for filtering by Requisition Status
	    String requisitionStatusClause = "";
	    if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("1")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (2,5) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("2")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (3) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("3")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (6) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("4")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (7) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("5")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (8,13) ";
	    } else if (vo.getRequisitionStatusCode() != null && vo.getRequisitionStatusCode().equals("6")) {
	    	requisitionStatusClause = " and hivnum_reqdtl_status in (14) ";
	    }
	    
	    queryNew = String.valueOf(queryNew) + requisitionStatusClause;
	    queryHst = String.valueOf(queryHst) + requisitionStatusClause;
	    
	    if(vo.getDataFromArchival().equals("1")) {
	    	query = String.valueOf(queryNew)+" UNION "+String.valueOf(queryHst);
	    } else {
	    	query = String.valueOf(queryNew);
	    }
	    
	    //Order by Clause
	    query = String.valueOf(query) + " ORDER BY hivdt_requisition_date_reqd desc, testCode,groupCode ,sampleNo DESC, "+orderByDateClause;
	    System.out.println("Query Print:: " + query);
	    
		try
		{	
			if(vo.getDataFromArchival().equals("0")) {
				populateMap.put(sq.next(), userVO.getHospitalCode());
				if(vo.getDateFiltersOrBoth()!=null)
				if (vo.getDateFiltersOrBoth().equals("1")) {
					populateMap.put(sq.next(), vo.getFromDate());
					populateMap.put(sq.next(), vo.getToDate());
			    }
		    } else {
		    	for(int count=0; count<2; count++) {
			    	populateMap.put(sq.next(), userVO.getHospitalCode());
			    	if(vo.getDateFiltersOrBoth()!=null)
					if (vo.getDateFiltersOrBoth().equals("1")) {
						populateMap.put(sq.next(), vo.getFromDate());
						populateMap.put(sq.next(), vo.getToDate());
				    }
		    	}
		    }

			rs=HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if(!rs.next()) { }
			else {
				rs.beforeFirst();
				while(rs.next()) {
					InvTrackingReportVO vo2 = new InvTrackingReportVO();
					HelperMethods.populateVOfrmRS(vo2, rs);
					listInvTrackingReportVO.add(vo2);
				}
			}
		}
		catch (Exception e)
		{
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		 }

		return listInvTrackingReportVO;
	}


	public Map<String, String> GetTestTurnAroundTime(InvTrackingReportVO vo, UserVO userVO) {

		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRACKING_REPORTSDAO;
		String queryKey="SELECT_INV_TRACKING_REPORT.TURN_AROUND_TIME.HIVT_TEST_MST";
		String query="";

		Map<String, String> mp = new HashMap<String, String>();
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();

		Map populateMap= new HashMap();
		//Sequence sq= new Sequence();

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
			//populateMap.put(sq.next(), userVO.getHospitalCode());

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if (!rs.next()) {  }
			else
			{
				rs.beforeFirst();
				while(rs.next()){

					mp.put(rs.getString(1), rs.getString(2));
				}
			}
		}
		catch(Exception e)
		{
			 throw new HisDataAccessException("HisDataAccessException:: "+e);
		 }

		return mp;
	}

	public String isfromFTPorMONGO( UserVO _UserVO)
	{
		String count=""	;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.HIVT_REPORT_DB_MST";


		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}

		List<SampleAcceptanceVO> listSampleAcceptanceVO = new ArrayList<SampleAcceptanceVO>();
		ValueObject[] valueObjects = null;

		try
		{

			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{							}
			else
			{
				count=rs.getString(1);

			}
		}
		catch (Exception e)
		{

		}
		return count;
	}

}
