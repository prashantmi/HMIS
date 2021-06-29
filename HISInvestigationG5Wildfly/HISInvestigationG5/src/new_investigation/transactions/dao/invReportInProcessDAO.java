package new_investigation.transactions.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.invReportInProcessVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class invReportInProcessDAO extends DataAccessObject {

	public invReportInProcessDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	
	public List<invReportInProcessVO> setResultReportPrintingEssentials(invReportInProcessVO invresultreportprintingvo, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="";
		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+invresultreportprintingvo.getPatCRNo();
		
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like lower('%"+invresultreportprintingvo.getTempPatName()+"%')";
		
		
		//queryKey="SELECT.RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER";
		
		
		String queryKey1 ="";
		
		
		queryKey1=	"SELECT.RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_HIVT_REQUISITION_HEADER_INPROCESS_1";
		
	
		
	
	String orderByCondition=" order by hivt_entry_date desc,patPuk,tempSampleNo,labNo ";
		try
		{
			 
			
			query = HelperMethodsDAO.getQuery(filename, queryKey1);
			
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			
			
		populateMAP.put(sq.next(),invresultreportprintingvo.getFromDate());
		 populateMAP.put(sq.next(),invresultreportprintingvo.getToDate());
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			//populateMAP.put(sq.next(), patVO.getPatCRNo());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		     
		//PatientDetailVO[] dailyPatientVOs = null;
		List<invReportInProcessVO> lstinvReportInProcessVO = new ArrayList<invReportInProcessVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			if(invresultreportprintingvo.getPatCRNo()!=null&&!invresultreportprintingvo.getPatCRNo().equals(""))
			{
				query+=condition5;
			}
			
			
			if(invresultreportprintingvo.getTempPatName()!=null&&!invresultreportprintingvo.getTempPatName().equals(""))
			{
				query+=searchPatName;
			}
			
			
			query+=orderByCondition;
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportInProcessVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstinvReportInProcessVO.add((invReportInProcessVO)valueObjects[i]);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("InvResultReportPrintingDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return lstinvReportInProcessVO;
	}	
	
	public List<invReportInProcessVO> setResultReportPrintingEssentialsOnLoad( UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="";
		
		
		
		//queryKey="SELECT.RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER";
		
		
		String queryKey1 ="";
		
		
		queryKey1=	"SELECT.RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_HIVT_REQUISITION_HEADER_INPROCESS";
		
	
		
	
	String orderByCondition=" order by hivt_entry_date desc,patPuk,tempSampleNo,labNo ";
		try
		{
			 
			
			query = HelperMethodsDAO.getQuery(filename, queryKey1);
			
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			
			
		//	populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
			
			
		//	populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			//populateMAP.put(sq.next(), patVO.getPatCRNo());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		     
		//PatientDetailVO[] dailyPatientVOs = null;
		List<invReportInProcessVO> lstinvReportInProcessVO = new ArrayList<invReportInProcessVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			
			
			
			query+=orderByCondition;
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(invReportInProcessVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstinvReportInProcessVO.add((invReportInProcessVO)valueObjects[i]);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("InvResultReportPrintingDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return lstinvReportInProcessVO;
	}	
	
	
	public void saveInJobWorkOrder(invReportInProcessVO voResultEntry, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.RESULT.ENTRY.DETAIL.HIVT_JOBWORKORDER_DATA";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			if(voResultEntry.getGroupCode()==null || voResultEntry.getGroupCode().equals(""))
			{
				voResultEntry.setGroupCode("null");
			}
			 
			
		    populateMAP.put(sq.next(), voResultEntry.getPatCRNo().equals("null")?"":voResultEntry.getPatCRNo());
	        //Need To Be ADD IS_VALID_ACTIVE
			 populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
			 populateMAP.put(sq.next(), InvestigationConfig.UPDATION_TYPE);
			 populateMAP.put(sq.next(), InvestigationConfig.STATUS_CODE);
			 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			 populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
		        populateMAP.put(sq.next(), voResultEntry.getPatCRNo().equals("null")?"":voResultEntry.getPatCRNo());
		        populateMAP.put(sq.next(), voResultEntry.getTestCode());
		        populateMAP.put(sq.next(), voResultEntry.getLabCode());
		        populateMAP.put(sq.next(), voResultEntry.getSampleNo()==null?"":voResultEntry.getSampleNo());
		        populateMAP.put(sq.next(), voResultEntry.getPatAge()==null?"":voResultEntry.getPatAge());
		        populateMAP.put(sq.next(), voResultEntry.getPatGender());
		        
		        populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode()==null?"":voResultEntry.getTestParaMeterCode()); 
		        populateMAP.put(sq.next(), voResultEntry.getReportAvailableAfter());	        
		        
	        populateMAP.put(sq.next(), voResultEntry.getResultEntryValue()==null?"":voResultEntry.getResultEntryValue());
	        populateMAP.put(sq.next(), voResultEntry.getPatVisitDat());
	        populateMAP.put(sq.next(), voResultEntry.getPatVisitNo().equals("null")?"":voResultEntry.getPatVisitNo());
	        populateMAP.put(sq.next(), voResultEntry.getLabNo());
	        populateMAP.put(sq.next(), voResultEntry.getEpisodeCode());
	        populateMAP.put(sq.next(), voResultEntry.getDepartmentcode());
	        populateMAP.put(sq.next(), voResultEntry.getPatdeptunitcode());
	        populateMAP.put(sq.next(), voResultEntry.getRequisitionTypeCode());
	        populateMAP.put(sq.next(), voResultEntry.getTestName());
	        populateMAP.put(sq.next(), voResultEntry.getPatLabName());
	        populateMAP.put(sq.next(), voResultEntry.getSampleName());
	        populateMAP.put(sq.next(), voResultEntry.getTempSampleNo()==null?"":voResultEntry.getTempSampleNo());
            populateMAP.put(sq.next(), voResultEntry.getGroupCode().equals("null")?"":voResultEntry.getGroupCode());
           // populateMAP.put(sq.next(), "");
			
			
	    	
	        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
		}

	}
	
	
	
	
	
	
	public String checkDataInJobWorkOrder(invReportInProcessVO voResultEntry, UserVO _UserVO,String dno) 
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="CHECK.RESULT.ENTRY.DETAIL.HIVT_JOBWORKORDER_DATA";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			
			 
			
			 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			 populateMAP.put(sq.next(), dno);
			
           
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("IcdGroupMasterDAO.populateMAP::::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
		 	{
				record=rs.getString(1);
		
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
	
	
	public void updateInJobWorkOrder(invReportInProcessVO voResultEntry, UserVO _UserVO,String dno) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.RESULT.ENTRY.DETAIL.HIVT_JOBWORKORDER_DATA";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			
			 
			
			 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			 populateMAP.put(sq.next(), dno);
			
			
			
           // populateMAP.put(sq.next(), "");
			
			
	    	
	        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
		}

	}
	
	
	public void updateInDTL(invReportInProcessVO voResultEntry, UserVO _UserVO,String dno) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.RESULT.ENTRY.DETAIL.HIVT_REQ_DTL";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			
			 
			 populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
			 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			
			
			
           // populateMAP.put(sq.next(), "");
			
			
	    	
	        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
		}

	}

	
	
	
	public void updateInDTLDAILY(invReportInProcessVO voResultEntry, UserVO _UserVO,String dno) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.RESULT.ENTRY.DETAIL.HIVT_REQ_DTL_DAILY";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			
			 
			 populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
			 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			
			
			
           // populateMAP.put(sq.next(), "");
			
			
	    	
	        	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in  HIVT_JOBWORKORDER_DATA Table");
		}

	}

	
	

}
