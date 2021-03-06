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
		String queryKey1="SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL";
		String queryKey2="SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_ONCRNO_ARCHIVAL_HIVT_REQUISITION_DTL";
		
		String queryKey3="SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_ONBILLNO_HIVT_REQUISITION_DTL";
		String queryKey5="SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL_REPORTS";
		String queryKey6="SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL_REPORTS_GENE";
		String queryKey7="SELECT_INV_TRACKING_REPORT_SAMPLEBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL_REPORTS_GENE1";
		
		String query="";
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();
		
		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<InvTrackingReportVO> listInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
		boolean flg=false;
		try 
		{	
			if(vo.getSearchType().equals("1")) {
				if (vo.getDataFromArchival().equals("0")) {
					query = HelperMethodsDAO.getQuery(filename,queryKey1);
					
					
					if(vo.getRequisitionNo()!=null && !vo.getRequisitionNo().equals("") && !vo.getRequisitionNo().equals("undefined") )
					{
						if(vo.getRequisitionNo().contains("generated"))
						{
							flg=true;
							vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
						query = HelperMethodsDAO.getQuery(filename,queryKey6);
						}
						else if(vo.getRequisitionNo().contains("partially"))
						{
							flg=true;
							vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
							query = HelperMethodsDAO.getQuery(filename,queryKey7);
								
						}
						else
						{
							if(vo.getRequisitionNo()!=null && vo.getRequisitionNo().contains("@@"))
							{
								flg=true;
								vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
							query = HelperMethodsDAO.getQuery(filename,queryKey5);
							}	
						}	
					}
					
				} /* forTestOrGroupOrAll */
						
				if(vo.getDataFromArchival().equals("1")) {
				query = HelperMethodsDAO.getQuery(filename,queryKey2);
				}
			} 
			else if(vo.getSearchType().equals("2"))
			{	
				query = HelperMethodsDAO.getQuery(filename,queryKey3);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		try
		{ 
			if(vo.getSearchType().equals("1")) {

				if(vo.getDataFromArchival().equals("0")) {
					
					if(vo.getRequisitionNo()!=null && !vo.getRequisitionNo().equals("") && !vo.getRequisitionNo().equals("undefined") && flg==true)
					{
						populateMap.put(sq.next(), vo.getCrNo());
						populateMap.put(sq.next(), userVO.getHospitalCode());
						populateMap.put(sq.next(), vo.getRequisitionNo());
						
					}
					else
					{
					populateMap.put(sq.next(), vo.getCrNo());
					populateMap.put(sq.next(), userVO.getHospitalCode());
					
					populateMap.put(sq.next(), vo.getForTestOrGroupOrAll());
					populateMap.put(sq.next(), vo.getTestCode());
					populateMap.put(sq.next(), vo.getGroupCode());
					}
				}
				else if(vo.getDataFromArchival().equals("1")) {
					populateMap.put(sq.next(), vo.getCrNo());
					populateMap.put(sq.next(), userVO.getHospitalCode());
					populateMap.put(sq.next(), vo.getFromDate());
					populateMap.put(sq.next(), vo.getToDate());
					
					populateMap.put(sq.next(), vo.getForTestOrGroupOrAll());
					populateMap.put(sq.next(), vo.getTestCode());
					populateMap.put(sq.next(), vo.getGroupCode());
				}
			} 
			else if(vo.getSearchType().equals("2"))
			{
				populateMap.put(sq.next(), vo.getBillNo());
				populateMap.put(sq.next(), userVO.getHospitalCode());
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
		String queryKey1="SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL";
		String queryKey2="SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_ONCRNO_ARCHIVAL_HIVT_REQUISITION_DTL";
		
		String queryKey3="SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_ONBILLNO_HIVT_REQUISITION_DTL";
		
		
		String queryKey5="SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL_REPORTS";
		String queryKey6="SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL_REPORTS_GENE";
		String queryKey7="SELECT_INV_TRACKING_REPORT_PATIENTBASED_TEST_ONCRNO_HIVT_REQUISITION_DTL_REPORTS_GENE1";
		
		boolean flg=false;
		
		
		String query="";
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();
		
		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<InvTrackingReportVO> listInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
		
		try 
		{
			if(vo.getSearchType().equals("1")) {
				if(vo.getDataFromArchival().equals("0"))
				{
					query = HelperMethodsDAO.getQuery(filename,queryKey1);
				
				if(vo.getRequisitionNo()!=null && !vo.getRequisitionNo().equals("") && !vo.getRequisitionNo().equals("undefined") )
				{
					if(vo.getRequisitionNo().contains("generated"))
					{
						flg=true;
						vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
					query = HelperMethodsDAO.getQuery(filename,queryKey6);
					}	
					else if(vo.getRequisitionNo().contains("partially"))
					{
						flg=true;
						vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
						query = HelperMethodsDAO.getQuery(filename,queryKey7);
							
					}
					else
					{
						if(vo.getRequisitionNo()!=null && vo.getRequisitionNo().contains("@@"))
						{
							flg=true;
						vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
						query = HelperMethodsDAO.getQuery(filename,queryKey5);
						}	
					}
							
					
				}
				
				}	
				if(vo.getDataFromArchival().equals("1"))
				query = HelperMethodsDAO.getQuery(filename,queryKey2);
			} 
			else if(vo.getSearchType().equals("2"))
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey3);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		
		try
		{
			if(vo.getSearchType().equals("1")) {
				if(vo.getDataFromArchival().equals("0")) {
					
					if(vo.getRequisitionNo()!=null && !vo.getRequisitionNo().equals("") && !vo.getRequisitionNo().equals("undefined") && flg==true)
					{
						
						populateMap.put(sq.next(), vo.getCrNo());
						populateMap.put(sq.next(), userVO.getHospitalCode());
						populateMap.put(sq.next(), vo.getRequisitionNo());
						
							
					
						
					}
					else
					{
					populateMap.put(sq.next(), vo.getCrNo());
					populateMap.put(sq.next(), userVO.getHospitalCode());
					
					populateMap.put(sq.next(), vo.getForTestOrGroupOrAll());
					populateMap.put(sq.next(), vo.getTestCode());
					populateMap.put(sq.next(), vo.getGroupCode());
				
					}
					
				}
				else if(vo.getDataFromArchival().equals("1")) {
					populateMap.put(sq.next(), vo.getCrNo());
					populateMap.put(sq.next(), userVO.getHospitalCode());
					populateMap.put(sq.next(), vo.getFromDate());
					populateMap.put(sq.next(), vo.getToDate());
					
					populateMap.put(sq.next(), vo.getForTestOrGroupOrAll());
					populateMap.put(sq.next(), vo.getTestCode());
					populateMap.put(sq.next(), vo.getGroupCode());
				}
			
			} 
			else if(vo.getSearchType().equals("2"))
			{
				populateMap.put(sq.next(), vo.getBillNo());
				populateMap.put(sq.next(), userVO.getHospitalCode());
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
		String queryKey1="SELECT_INV_TRACKING_REPORT_TESTPARAM_ONCRNO_HIVT_REQUISITION_DTL";
		String queryKey2="SELECT_INV_TRACKING_REPORT_TESTPARAM_ONCRNO_ARCHIVAL_HIVT_REQUISITION_DTL";
		
		String queryKey3="SELECT_INV_TRACKING_REPORT_TESTPARAM_ONBILLNO_HIVT_REQUISITION_DTL";
		String query="";
		String queryKey5="SELECT_INV_TRACKING_REPORT_TESTPARAM_ONCRNO_HIVT_REQUISITION_DTL_REPORTS";
		String queryKey6="SELECT_INV_TRACKING_REPORT_TESTPARAM_ONCRNO_HIVT_REQUISITION_DTL_REPORTS_GENE";
		String queryKey7="SELECT_INV_TRACKING_REPORT_TESTPARAM_ONCRNO_HIVT_REQUISITION_DTL_REPORTS_GENE1";
		boolean flg=false;
		
		ResultSet rs=null;
		Connection conn=super.getTransactionContext().getConnection();
		
		Map populateMap= new HashMap();
		Sequence sq= new Sequence();
		List<InvTrackingReportVO> listInvTrackingReportVO=new ArrayList<InvTrackingReportVO>();
		
		try 
		{
			if(vo.getSearchType().equals("1")) {
				if(vo.getDataFromArchival().equals("0")) {
					query = HelperMethodsDAO.getQuery(filename,queryKey1);
					
					if(vo.getRequisitionNo()!=null && !vo.getRequisitionNo().equals("") && !vo.getRequisitionNo().equals("undefined"))
					{
						if(vo.getRequisitionNo().contains("generated"))
						{
							flg=true;
							vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
						query = HelperMethodsDAO.getQuery(filename,queryKey6);
						}	
						else if(vo.getRequisitionNo().contains("partially"))
						{
							flg=true;
							vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
							query = HelperMethodsDAO.getQuery(filename,queryKey7);
								
						}
						else
						{
							
							if(vo.getRequisitionNo()!=null && vo.getRequisitionNo().contains("@@"))
							{
								flg=true;
							vo.setRequisitionNo(vo.getRequisitionNo().split("@@")[0]);
							query = HelperMethodsDAO.getQuery(filename,queryKey5);
							}	
						}
					}
				}
				else if(vo.getDataFromArchival().equals("1")) {
					query = HelperMethodsDAO.getQuery(filename,queryKey2);
				}
			
			} 
			else if(vo.getSearchType().equals("2"))
			{
				query = HelperMethodsDAO.getQuery(filename,queryKey3);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}

		try
		{
			if(vo.getSearchType().equals("1")) {


				if(vo.getDataFromArchival().equals("0")) {
					
					

					if(vo.getRequisitionNo()!=null && !vo.getRequisitionNo().equals("") && !vo.getRequisitionNo().equals("undefined") && flg==true)
					{
						populateMap.put(sq.next(), vo.getCrNo());
						populateMap.put(sq.next(), userVO.getHospitalCode());
						populateMap.put(sq.next(), vo.getRequisitionNo());
						
						
					}
					else
					{
					populateMap.put(sq.next(), vo.getCrNo());
					populateMap.put(sq.next(), userVO.getHospitalCode());
					
					populateMap.put(sq.next(), vo.getForTestOrGroupOrAll());
					populateMap.put(sq.next(), vo.getTestCode());
					populateMap.put(sq.next(), vo.getGroupCode());
					
					}
				}
				else if(vo.getDataFromArchival().equals("1")) {
					populateMap.put(sq.next(), vo.getCrNo());
					populateMap.put(sq.next(), userVO.getHospitalCode());
					populateMap.put(sq.next(), vo.getFromDate());
					populateMap.put(sq.next(), vo.getToDate());
					
					populateMap.put(sq.next(), vo.getForTestOrGroupOrAll());
					populateMap.put(sq.next(), vo.getTestCode());
					populateMap.put(sq.next(), vo.getGroupCode());
				}
			} 
			else if(vo.getSearchType().equals("2"))
			{
				populateMap.put(sq.next(), vo.getBillNo());
				populateMap.put(sq.next(), userVO.getHospitalCode());
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
