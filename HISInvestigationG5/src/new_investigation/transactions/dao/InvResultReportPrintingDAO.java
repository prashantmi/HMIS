package new_investigation.transactions.dao;

/**
 * @author : C-DAC, Noida
 * Project : HISInvestigationG5
 * Module  : New_Investigation
 * Created On : 23-Feb-2015
 * 
 * Developed By : Pathan Basha
 * 
 * Purpose : This Class should be used for all Essential Data Access Data Layer Methods 
 * 			regarding all processes
 * 
 * Modified By:
 * 
 * Modified On: 
 */


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;
/*import hisglobal.vo.FormAReportVO;
import hisglobal.vo.FormCReportVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.QualityControlMstVO;
import hisglobal.vo.QualityParameterMappingVO;
import hisglobal.vo.RefreshmentItemMstVO;
import hisglobal.vo.ScreeningOfOtherTTDReportVO;
import hisglobal.vo.TherapeuticPatientDtlVO;*/
//import hisglobal.vo.TherapeuticTypeMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
//import hisglobal.vo.VoluntaryCardDtlVO;










import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.InvResultReportPrintingVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.OnlinePatientAcceptanceVO;
import new_investigation.vo.RequistionHeaderVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.template.ResultEntryVO;



public class InvResultReportPrintingDAO extends DataAccessObject
{
	public InvResultReportPrintingDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
	
	public List LabComboForResultReportPrinting(InvResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LAB_COMBO_FOR_RESULT_REPORT_PRINTING_HIVT_LABORATORY_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
		populateMap.put(sq.next(),_UserVO.getUserSeatId());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
		 
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				parameterCombo=HelperMethodsDAO.getAlOfEntryObjectsInvLabs(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return parameterCombo;
	}
		
	
	public List<InvResultReportPrintingVO> setResultReportPrintingEssentials(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="";
		
		if(invresultreportprintingvo.getDateType().equals("p")) {
			if(invresultreportprintingvo.getReportType().equals("0"))
				queryKey="SELECT.RESULT_REPORT_PRINTING_PATIENTACCPDATE_HIVT_REQUISITION_HEADER1";
			else
				queryKey="SELECT.DEPT_RESULT_REPORT_PRINTING_PATIENTACCPDATE_HIVT_REQUISITION_HEADER";
		} else {
			if(invresultreportprintingvo.getReportType().equals("0"))
				queryKey="SELECT.RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER1";
			else
				queryKey="SELECT.DEPT_RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER";
		}
	
		String queryKey1 ="";
		
		if(invresultreportprintingvo.getDateType().equals("p")) {
			
			if(invresultreportprintingvo.getReportType().equals("0"))
				queryKey1=	"SELECT.RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_PATIENTACCPDATE_HIVT_REQUISITION_HEADER";
			else
				queryKey1="SELECT.DEPT_RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_PATIENTACCPDATE_HIVT_REQUISITION_HEADER";
			
		} else {
			if(invresultreportprintingvo.getReportType().equals("0"))
				queryKey1=	"SELECT.RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_HIVT_REQUISITION_HEADER";
			else
				queryKey1="SELECT.DEPT_RESULT_REPORT_PRINTING_FOR_PDF_IN_PROCESS_HIVT_REQUISITION_HEADER";
		}
		
		String condition5="And hrgnum_puk_reqd="+invresultreportprintingvo.getPatCRNo();
	
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%"+invresultreportprintingvo.getTempPatName()+"%'";
		String condition1=" AND gnum_test_code ="+invresultreportprintingvo.getTestWiseCode();
		String condition2="AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')>=regexp_replace('"+invresultreportprintingvo.getFromSampleNo()+"','[^[:digit:]]','','g') AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')<=regexp_replace('"+invresultreportprintingvo.getToSampleNo()+"','[^[:digit:]]','','g')";
		String condition3="AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')>=regexp_replace('"+invresultreportprintingvo.getFromLabNo()+"','[^[:digit:]]','','g') AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')<=regexp_replace('"+invresultreportprintingvo.getToLabNo()+"','[^[:digit:]]','','g')";
	    String condition4=" AND hgnum_group_code ="+invresultreportprintingvo.getTestGroupCode();
		



	//String orderByCondition=" order by hivt_entry_date,patPuk,tempSampleNo ";


	
	String orderByCondition=" order by  hivdt_coll_date_time,hivdt_acceptance_date_time,tempSampleNo,labNo,hivtnum_req_dno";

		try
		{
			 
			if(invresultreportprintingvo.getPdfGenerationType().equals("1"))
			{
			query = HelperMethodsDAO.getQuery(filename, queryKey1);
			}
			
			else
			{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
		
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
			
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(),_UserVO.getUserSeatId());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			
			//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
			populateMAP.put(sq.next(),invresultreportprintingvo.getFromDate());
			populateMAP.put(sq.next(),invresultreportprintingvo.getToDate());
			populateMAP.put(sq.next(),invresultreportprintingvo.getLabCode());
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			//populateMAP.put(sq.next(), patVO.getPatCRNo());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		     
		PatientDetailVO[] dailyPatientVOs = null;
		List<InvResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvResultReportPrintingVO>();
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
			
			
			
			if(invresultreportprintingvo.getGenerationType()!=null&&!invresultreportprintingvo.getOnLoadValue().equals("ONLOAD"))
			{
				if(invresultreportprintingvo.getGenerationType().equals("T")&&!invresultreportprintingvo.getTestWiseCode().equals("-1"))
				{
					query+=condition1;
				}
				
				if(invresultreportprintingvo.getGenerationType().equals("L")&&!invresultreportprintingvo.getFromLabNo().equals("-1")&&!invresultreportprintingvo.getToLabNo().equals("-1"))
				{
				 
					query+=condition3;
				}
				
				if(invresultreportprintingvo.getGenerationType().equals("S")&&!invresultreportprintingvo.getFromSampleNo().equals("-1")&&!invresultreportprintingvo.getToSampleNo().equals("-1"))
				{
					query+=condition2;
					
				}
				
				if(invresultreportprintingvo.getGenerationType().equals("TG")&&!invresultreportprintingvo.getTestGroupCode().equals("-1")&&!invresultreportprintingvo.getTestGroupCode().equals(""))
				{
					query+=condition4;
				}
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
				valueObjects = HelperMethods.populateVOfrmRS(InvResultReportPrintingVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultReportPrintingVO.add((InvResultReportPrintingVO)valueObjects[i]);
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
		return lstInvResultReportPrintingVO;
	}	
	
	
	
	
	
	public List<InvResultReportPrintingVO> setResultReportPrintingEssentialsOnLoad(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		System.out.println("setResultReportPrintingEssentialsOnLoad ---------> ");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.RESULT_REPORT_PRINTING_ON_LOAD_HIVT_REQUISITION_HEADER";
		String condition1="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+invresultreportprintingvo.getPatCRNo();
		//String orderByCondition=" order by hivdt_coll_date_time,patPuk,gnum_lab_code,(Select gnum_preference_order from hivt_laboratory_test_mst where gnum_isvalid=1 and gnum_hospital_code=a.gnum_hospital_code  and gnum_test_code=a.gnum_test_code and gnum_labcode=a.gnum_lab_code)";//,hivtnum_req_dno,hivt_entry_date,patCRNo, ";
		//String orderByCondition=" order by hivdt_coll_date_time,patPuk,gnum_lab_code,(Select gnum_preference_order from hivt_laboratory_test_mst where gnum_isvalid=1 and gnum_hospital_code=a.gnum_hospital_code  and gnum_test_code=a.gnum_test_code and gnum_labcode=a.gnum_lab_code)";
		String orderByCondition=" order by hivdt_coll_date_time,hivdt_acceptance_date_time,tempSampleNo,labNo,hivtnum_req_dno ";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query Result ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(),_UserVO.getUserSeatId());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
			//populateMAP.put(sq.next(),invresultreportprintingvo.getFromDate());
			//populateMAP.put(sq.next(),invresultreportprintingvo.getToDate());
			//populateMAP.put(sq.next(),invresultreportprintingvo.getLabCode());
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			//populateMAP.put(sq.next(), patVO.getPatCRNo());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		     
		PatientDetailVO[] dailyPatientVOs = null;
		List<InvResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvResultReportPrintingVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			if(invresultreportprintingvo.getPatCRNo()!=null&&!invresultreportprintingvo.getPatCRNo().equals(""))
			{
				query+=condition1;
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
				valueObjects = HelperMethods.populateVOfrmRS(InvResultReportPrintingVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultReportPrintingVO.add((InvResultReportPrintingVO)valueObjects[i]);
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
		return lstInvResultReportPrintingVO;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public InvResultReportPrintingVO getPdfName(InvResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		
		InvResultReportPrintingVO invresultreportp;
		
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_PDF_NAME_HIVTSTR_PDF_URL";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		
		
		

		
		
		//populateMap.put(sq.next(),_UserVO.getHospitalCode());
				//populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				//populateMap.put(sq.next(),_UserVO.getUserSeatId());
				populateMap.put(sq.next(),invresultreportprintingvo.getPatPuk());
				populateMap.put(sq.next(),invresultreportprintingvo.getRequisitionDNo());
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				invresultreportp=new InvResultReportPrintingVO();
                HelperMethods.populateVOfrmRS(invresultreportp, rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return invresultreportp;
	}
	
	
	
	
	
	
	
	
	 
	public void updateResultReportPrintingDetailInRequisitionDtl(InvResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query = "";
		String query_new = "";
		
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="";
		String queryKey_new="";
		
		
		if(invresultreportprintingvo.getReportType() != null && invresultreportprintingvo.getReportType().equals("0")){
			if(invresultreportprintingvo.getGroupCode()!=null)
			{
				queryKey = "UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_GROUP_CASE";
				queryKey_new = "UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_GROUP_CASE_NEW";
				
			}else
			{	
				queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS";
				queryKey_new="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_NEW";
				
			}
			
			}else
			{
				queryKey="UPDATE.REQDTLS.DEPT.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS";
				queryKey_new="UPDATE.REQDTLS.DEPT.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_NEW";
				
			}
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			query_new = HelperMethodsDAO.getQuery(filename, queryKey_new);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			//UPDATE hivt_requisition_dtl SET hivnum_reqdtl_status=?, hivtdt_res_print_date=SYSDATE, hivtnum_res_print_seatid=? WHERE hivnum_req_no=? and hgnum_group_code = ?
			if(invresultreportprintingvo.getGroupCode()!=null){
				populateMAP.put(sq.next(), InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING);
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), invresultreportprintingvo.getRequisitionNo());
				populateMAP.put(sq.next(), invresultreportprintingvo.getGroupCode());
			}else{	
				if(invresultreportprintingvo.getReportType().equals("0")) 
				populateMAP.put(sq.next(), InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING);
				else
					populateMAP.put(sq.next(), "34");	
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(),invresultreportprintingvo.getRequisitionDNo());
			}
			 
			 
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query_new, populateMAP);
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public List setLabNoComboEssentials(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List labNoCombo=new ArrayList();
		/*String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABNO_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="";
		if(invresultentryvo.getReportType().equals("0"))
		condition1="and hivnum_reqdtl_status ="+InvestigationConfig.REPORT_PDF_GEN +" order by labNo";
		else
			condition1="and hivnum_dept_reqdtl_status=33 order by labNo";
		
		String condition2="";
		
		if(invresultentryvo.getReportType().equals("0"))
			condition2="and hivnum_reqdtl_status ="+InvestigationConfig.REPORT_PDF_GEN_INPROCESS +" order by labNo";
		else
			condition2="and hivnum_reqdtl_status = 32 order by labNo";
				
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 
			//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
			populateMAP.put(sq.next(),invresultentryvo.getFromDate());
			populateMAP.put(sq.next(),invresultentryvo.getToDate());
			populateMAP.put(sq.next(),invresultentryvo.getLabCode());
			   
		try
		{
			
			
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			
			if(invresultentryvo.getPdfGenerationType().equals("1"))
			{
				query+=condition2;
				
			}
			
			else
			{
				query+=condition1; 
			}


			
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return labNoCombo;
			}
			else
			{
				rs.beforeFirst();
				labNoCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return labNoCombo;
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return labNoCombo;
	}
		

	public List setTestComboEssentials(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testCombo=new ArrayList();
	/*	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTWISE_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="";
		if(invresultentryvo.getReportType().equals("0"))
		condition1="and hivnum_reqdtl_status in ("+InvestigationConfig.REPORT_PDF_GEN +",'14') order by testName";
		else
			condition1="and hivnum_dept_reqdtl_status=33 order by testName";
		
		String condition2="";
		
		if(invresultentryvo.getReportType().equals("0"))
			condition2="and hivnum_reqdtl_status ="+InvestigationConfig.REPORT_PDF_GEN_INPROCESS +" order by testName";
		else
			condition2="and hivnum_reqdtl_status = 32 order by testName";
					
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
	   populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	    
		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		populateMAP.put(sq.next(),invresultentryvo.getLabCode());
		
		 
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			if(invresultentryvo.getPdfGenerationType().equals("1"))
			{
				query+=condition2;
				
			}
			
			else
			{
				query+=condition1; 
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return testCombo;
			}
			else
			{
				rs.beforeFirst();
				testCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return testCombo;
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return testCombo;
	}
		


	public List setSamplNoComboEssentials(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List sampleNoCombo=new ArrayList();
		/*String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.SAMPLENO_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="";
		if(invresultentryvo.getReportType().equals("0"))
		condition1="and hivnum_reqdtl_status in("+InvestigationConfig.REPORT_PDF_GEN +",'14') order by sampleNo";
		else
			condition1="and hivnum_dept_reqdtl_status=33 order by sampleNo";
		
		String condition2="";
		
		if(invresultentryvo.getReportType().equals("0"))
			condition2="and hivnum_reqdtl_status ="+InvestigationConfig.REPORT_PDF_GEN_INPROCESS +" order by sampleNo";
		else
			condition2="and hivnum_reqdtl_status = 32 order by sampleNo";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 
		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		populateMAP.put(sq.next(),invresultentryvo.getLabCode());
		
		 
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
			if(invresultentryvo.getPdfGenerationType().equals("1"))
			{
				query+=condition2;
				
			}
			
			else
			{
				query+=condition1; 
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return sampleNoCombo;
			}
			else
			{
				rs.beforeFirst();
				sampleNoCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return sampleNoCombo;	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return sampleNoCombo;
	}
		
	//setTestGroupComboEssentials
	public List setTestGroupComboEssentials(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testGrpCombo=new ArrayList();
		/*String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTGROUP_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="";
		if(invresultentryvo.getReportType().equals("0"))
		condition1="and hivnum_reqdtl_status ="+InvestigationConfig.REPORT_PDF_GEN +" order by groupName";
		else
			condition1="and hivnum_dept_reqdtl_status=33 order by groupName";
		
		String condition2="";
		
		if(invresultentryvo.getReportType().equals("0"))
			condition2="and hivnum_reqdtl_status ="+InvestigationConfig.REPORT_PDF_GEN_INPROCESS +" order by groupName";
		else
			condition2="and hivnum_reqdtl_status = 32 order by groupName";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		System.out.println("pdfGenration"+invresultentryvo.getPdfGenerationType());
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		if(invresultentryvo.getPdfGenerationType().equals("1"))
		 * 
		{
		 populateMAP.put(sq.next(), InvestigationConfig.REPORT_PDF_GEN);
		}
		
		else
		{
			 populateMAP.put(sq.next(), InvestigationConfig.REPORT_PDF_GEN_INPROCESS);
		}
		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		populateMAP.put(sq.next(),invresultentryvo.getLabCode());
		
		 
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		
			if(invresultentryvo.getPdfGenerationType().equals("1"))
			{
				query+=condition2;
				
			}
			
			else
			{
				query+=condition1; 
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return testGrpCombo;
			}
			else
			{
				rs.beforeFirst();
				testGrpCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return testGrpCombo;	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return testGrpCombo;
	}
	
	
	
	public List setLabNoComboEssentialsOnLoad(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List labNoCombo=new ArrayList();
		/*String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABNO_COMBO_RESULT_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		  
			 
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
			
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return labNoCombo;
			}
			else
			{
				rs.beforeFirst();
				labNoCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return labNoCombo;
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return labNoCombo;
	}
		

	public List setTestComboEssentialsOnLoad(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testCombo=new ArrayList();
	/*	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTWISE_COMBO_RESULT_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
	   populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	   
		
		 
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return testCombo;
			}
			else
			{
				rs.beforeFirst();
				testCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return testCombo;
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return testCombo;
	}
		


	public List setSamplNoComboEssentialsOnLoad(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List sampleNoCombo=new ArrayList();
	/*	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.SAMPLENO_COMBO_RESULT_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 
	 
		
		 
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return sampleNoCombo;
			}
			else
			{
				rs.beforeFirst();
				sampleNoCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return sampleNoCombo;	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return sampleNoCombo;
	}
		
	//setTestGroupComboEssentials
	public List setTestGroupComboEssentialsOnLoad(InvResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testGrpCombo=new ArrayList();
	/*	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTGROUP_COMBO_RESULT_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		 
		
		 
		
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if (!rs.next())
			{
				return testGrpCombo;
			}
			else
			{
				rs.beforeFirst();
				testGrpCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				return testGrpCombo;	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	*/
		return testGrpCombo;
	}
		

	
	public String getmongodbname()
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.MONGODB_NAME.HIVT_MONGODB_TABLE";
       String returnn="";
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
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			populateMAP.put(sq.next(), "96101");
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
			while (rs.next())
			{
				returnn=(rs.getString(1));
				System.out.println("mongo db path:::" + returnn);
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		}
		return returnn;
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
		 
			//populateMAP.put(sq.next(),sampleAcceptanceVO.getPackingListNO());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			//populateMAP.put(sq.next(),sampleAcceptanceVO.getFromDate());
			//populateMAP.put(sq.next(),sampleAcceptanceVO.getToDate());
		 	 
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
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
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



	public void updateResultReportPrintingDetailInRequisitionDtldialytbl(InvResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="";
		
		
		if(invresultreportprintingvo.getReportType() != null && invresultreportprintingvo.getReportType().equals("0")){
			if(invresultreportprintingvo.getGroupCode()!=null)
				queryKey = "UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_GROUP_CASE_HSTBL";
			else
				queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_DAILYTBL";
		}else
			queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_DAILYTBL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			//UPDATE hivt_requisition_dtl SET hivnum_reqdtl_status=?, hivtdt_res_print_date=SYSDATE, hivtnum_res_print_seatid=? WHERE hivnum_req_no=? and hgnum_group_code = ?
			if(invresultreportprintingvo.getGroupCode()!=null){
				populateMAP.put(sq.next(), InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING);
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), invresultreportprintingvo.getRequisitionNo());
				populateMAP.put(sq.next(), invresultreportprintingvo.getGroupCode());
			}else{	
				if(invresultreportprintingvo.getReportType().equals("0")) 
				populateMAP.put(sq.next(), InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING);
				else
					populateMAP.put(sq.next(), "34");	
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(),invresultreportprintingvo.getRequisitionDNo());
			}
			 
			 
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	

	
	public void updateResultReportPrintingDetailInRequisitionDtlhisotrytbl(InvResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="";
		
		
		if(invresultreportprintingvo.getReportType() != null && invresultreportprintingvo.getReportType().equals("0")){
			if(invresultreportprintingvo.getGroupCode()!=null)
				queryKey = "UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_GROUP_CASE_HSTBL";
			else
				queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_HSTTBL";
		}else
			queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_HSTTBL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			//UPDATE hivt_requisition_dtl SET hivnum_reqdtl_status=?, hivtdt_res_print_date=SYSDATE, hivtnum_res_print_seatid=? WHERE hivnum_req_no=? and hgnum_group_code = ?
			if(invresultreportprintingvo.getGroupCode()!=null){
				populateMAP.put(sq.next(), InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING);
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), invresultreportprintingvo.getRequisitionNo());
				populateMAP.put(sq.next(), invresultreportprintingvo.getGroupCode());
			}else{	
				if(invresultreportprintingvo.getReportType().equals("0")) 
				populateMAP.put(sq.next(), InvestigationConfig.REQDETAILS_DATATUS_FOR_INV_REPORT_PRINTING);
				else
					populateMAP.put(sq.next(), "34");	
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(),invresultreportprintingvo.getRequisitionDNo());
			}
			 
			 
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	
	
	public List<InvResultReportPrintingVO> setResultReportPrintingEssentialsOnLoadnew(InvResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		System.out.println("setResultReportPrintingEssentialsOnLoad ---------> ");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "new_investigation.transactions.investigationTransactionReportsQuery";
		String queryKey = "SELECT.RESULT_REPORT_PRINTING_ON_LOAD_HIVT_REQUISITION_HEADER_NEW";
		
String condition_lab="";
		
		if(invresultreportprintingvo.getLabCode()!=null && invresultreportprintingvo.getLabCode().equals("%"))
		{
			condition_lab=" and gnum_lab_code like '%' ";
		}
		else
		{
			condition_lab=" and gnum_lab_code = '"+invresultreportprintingvo.getLabCode()+"' ";
		}
		
		String condition1="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+invresultreportprintingvo.getPatCRNo();
		//String orderByCondition=" order by hivdt_coll_date_time,patPuk,gnum_lab_code,(Select gnum_preference_order from hivt_laboratory_test_mst where gnum_isvalid=1 and gnum_hospital_code=a.gnum_hospital_code  and gnum_test_code=a.gnum_test_code and gnum_labcode=a.gnum_lab_code)";//,hivtnum_req_dno,hivt_entry_date,patCRNo, ";
		//String orderByCondition=" order by hivdt_coll_date_time,patPuk,gnum_lab_code,(Select gnum_preference_order from hivt_laboratory_test_mst where gnum_isvalid=1 and gnum_hospital_code=a.gnum_hospital_code  and gnum_test_code=a.gnum_test_code and gnum_labcode=a.gnum_lab_code)";
	
		//String orderByCondition=" order by hivdt_coll_date_time,hivdt_acceptance_date_time,tempSampleNo,labNo,hivtnum_req_dno ";
		String orderByCondition=" order by priority desc,emer desc  ,is_patdead desc,as_patmlc,is_patdead desc,is_newborn desc,is_pregnant desc, hivt_entry_date desc,hivnum_req_no";
		String orderByCondition_puk="  and hrgnum_puk_reqd='"+invresultreportprintingvo.getPatCRNo()+"' ";

		String orderByCondition_date="and  TRUNC (a.hivt_entry_date) >= TRUNC (TO_DATE ('"+invresultreportprintingvo.getFromDate()+"', 'DD-Mon-YYYY')) AND TRUNC (a.hivt_entry_date) <= TRUNC (TO_DATE ('"+invresultreportprintingvo.getToDate()+"', 'DD-Mon-YYYY'))" ; 
				
		String is_acc_date="0" ;
		
		String orderByCondition_acc_date="and  TRUNC (a.hivdt_coll_date_time) >= TRUNC (TO_DATE ('"+invresultreportprintingvo.getFromDate()+"', 'DD-Mon-YYYY')) AND TRUNC (a.hivdt_coll_date_time) <= TRUNC (TO_DATE ('"+invresultreportprintingvo.getToDate()+"', 'DD-Mon-YYYY'))" ; 
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query Result ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
			
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(),_UserVO.getUserSeatId());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			
			//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
			//populateMAP.put(sq.next(),invresultreportprintingvo.getFromDate());
			//populateMAP.put(sq.next(),invresultreportprintingvo.getToDate());
			//populateMAP.put(sq.next(),invresultreportprintingvo.getLabCode());
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			//populateMAP.put(sq.next(), patVO.getPatCRNo());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		     
		PatientDetailVO[] dailyPatientVOs = null;
		List<InvResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvResultReportPrintingVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			query+=condition_lab;
			
			if(invresultreportprintingvo.getDateFiltersOrBothh()!=null && invresultreportprintingvo.getDateFiltersOrBothh().equals("2") && invresultreportprintingvo.getPatCRNo()!=null&&!invresultreportprintingvo.getPatCRNo().equals(""))
			{
				query+=orderByCondition_puk;
			}
			else
			{
				
				
				if(invresultreportprintingvo.getPdfGenerationType()!=null && invresultreportprintingvo.getPdfGenerationType().equalsIgnoreCase("p") )
				{
					
					is_acc_date="1" ;
			       query+=orderByCondition_acc_date;
					
				}
				else
				{
					
				   query+=orderByCondition_date;
				
				}
				
			}
			
			
			
			if(invresultreportprintingvo.getDateFiltersOrBothh()!=null && invresultreportprintingvo.getDateFiltersOrBothh().equals("1") && invresultreportprintingvo.getPatCRNo()!=null&&!invresultreportprintingvo.getPatCRNo().equals(""))
			{
				query+=orderByCondition_puk;
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
				valueObjects = HelperMethods.populateVOfrmRS(InvResultReportPrintingVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					
					
					InvResultReportPrintingVO voo  = (InvResultReportPrintingVO) valueObjects[i];
					
					voo.setPdfGenerationType(is_acc_date);
					
					lstInvResultReportPrintingVO.add(voo);
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
		return lstInvResultReportPrintingVO;
	}	
	

	
	public String UPDATEREPORTS(InvResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query = "";
		String query_new = "";
			Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "new_investigation.transactions.investigationTransactionReportsQuery";
		String queryKey="";
		String queryKey_new="";
			String isupdate="";
		
		//queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS";
		String condition1=" where hivtnum_req_dno in ("+invresultreportprintingvo.getRequisitionDNo()+")";
		
		queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_NEWPROCESS";
		queryKey_new="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_NEWPROCESS_NEW";
		
		
		/*
		 * if(invresultreportprintingvo.getReportType() != null &&
		 * invresultreportprintingvo.getReportType().equals("0")){
		 * if(invresultreportprintingvo.getGroupCode()!=null) queryKey =
		 * "UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS_GROUP_CASE";
		 * else queryKey="UPDATE.REQDTLS.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS";
		 * }else
		 * 
		 * queryKey="UPDATE.REQDTLS.DEPT.RESULT.REPORT_PRINITNG.HIVT_REQUISITION_DTLS";
		 */
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			query_new = HelperMethodsDAO.getQuery(filename, queryKey_new);
			
			query+=condition1;
			query_new+=condition1;
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			//UPDATE hivt_requisition_dtl SET hivnum_reqdtl_status=?, hivtdt_res_print_date=SYSDATE, hivtnum_res_print_seatid=? WHERE hivnum_req_no=? and hgnum_group_code = ?
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), "14");
					 
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			int count= HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query_new, populateMAP);
			
		  isupdate=Integer.toString(count) ;
		  
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return isupdate;
	}
	
	
	
	
	
}

