package new_investigation.transactions.dao;

/**
 * @author : C-DAC, Noida
 * Project : AHIMS
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
import new_investigation.vo.InvDuplicateResultReportPrintingVO;
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



public class InvDuplicateResultReportPrintingDAO extends DataAccessObject
{
	public InvDuplicateResultReportPrintingDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
	
	public List LabComboForDuplicateResultReportPrinting(InvDuplicateResultReportPrintingVO  invresultreportprintingvo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING_HIVT_LABORATORY_MST";
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
				//updated by krishnan nema on 01/10/2018
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
		
	
	public List<InvDuplicateResultReportPrintingVO> setDuplicateResultReportPrintingEssentials(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.DUPLICATE_RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER";
		String queryKey1 = "SELECT.DUPLICATE_RESULT_REPORT_PRINTING_HIVT_REQUISITION_HEADER";
		 
		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+invresultreportprintingvo.getPatCRNo();
		
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%"+invresultreportprintingvo.getTempPatName()+"%'";

		String condition1=" AND gnum_test_code ="+invresultreportprintingvo.getTestWiseCode();
		String condition2="AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')>=regexp_replace('"+invresultreportprintingvo.getFromSampleNo()+"','[^[:digit:]]','','g') AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')<=regexp_replace('"+invresultreportprintingvo.getToSampleNo()+"','[^[:digit:]]','','g')";
		String condition3="AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')>=regexp_replace('"+invresultreportprintingvo.getFromLabNo()+"','[^[:digit:]]','','g') AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')<=regexp_replace('"+invresultreportprintingvo.getToLabNo()+"','[^[:digit:]]','','g')";
		String condition4=" AND hgnum_group_code ="+invresultreportprintingvo.getTestGroupCode();
		



	//	String orderByCondition=" order by hivt_entry_date,patPuk,tempSampleNo ";


		String orderByCondition=" order by hivdt_coll_date_time,hivdt_acceptance_date_time,tempSampleNo,labNo,hivtnum_req_dno";

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
		List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvDuplicateResultReportPrintingVO>();
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
				valueObjects = HelperMethods.populateVOfrmRS(InvDuplicateResultReportPrintingVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultReportPrintingVO.add((InvDuplicateResultReportPrintingVO)valueObjects[i]);
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
	
	
	
	
	
	public List<InvDuplicateResultReportPrintingVO> setDuplicateResultReportPrintingEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultreportprintingvo, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.DUPLICATE_RESULT_REPORT_PRINTING_ON_LOAD_HIVT_REQUISITION_HEADER";
		String condition1="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+invresultreportprintingvo.getPatCRNo();
		String orderByCondition=" order by hivdt_coll_date_time,hivdt_acceptance_date_time,tempSampleNo,labNo";//,hivtnum_req_dno,hivt_entry_date,patCRNo, ";
 
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
		List<InvDuplicateResultReportPrintingVO> lstInvResultReportPrintingVO = new ArrayList<InvDuplicateResultReportPrintingVO>();
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
				valueObjects = HelperMethods.populateVOfrmRS(InvDuplicateResultReportPrintingVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];      
					lstInvResultReportPrintingVO.add((InvDuplicateResultReportPrintingVO)valueObjects[i]);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public InvDuplicateResultReportPrintingVO getDuplicatePdfName(InvDuplicateResultReportPrintingVO invresultreportprintingvo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		
		InvDuplicateResultReportPrintingVO invresultreportp;
		
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_PDF_NAME_FOR_DUPLICATE_REPORT_HIVTSTR_PDF_URL";
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
				invresultreportp=new InvDuplicateResultReportPrintingVO();
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
	
	
	
	
	
	
	
	public List setLabNoComboEssentials(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List labNoCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABNO_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by labNo";
		String condition2="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by labNo";
				
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
		 }	
		return labNoCombo;
	}
		

	public List setTestComboEssentials(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTWISE_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by testName";
		String condition2="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by testName";
					
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
		 }	
		return testCombo;
	}
		


	public List setSamplNoComboEssentials(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List sampleNoCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.SAMPLENO_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by sampleNo";
		String condition2="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by sampleNo";
		
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
		 }	
		return sampleNoCombo;
	}
		
	//setTestGroupComboEssentials
	public List setTestGroupComboEssentials(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testGrpCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTGROUP_COMBO_RESULT_REPORT_HIVT_REQUISITION_HEADER";
		String condition1="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by groupName";
		String condition2="and hivnum_reqdtl_status ="+InvestigationConfig.DUPLICATE_REPORT_PRINITNG +" order by groupName";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		System.out.println("pdfGenration"+invresultentryvo.getPdfGenerationType());
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		/*if(invresultentryvo.getPdfGenerationType().equals("1"))
		 * 
		{
		 populateMAP.put(sq.next(), InvestigationConfig.REPORT_PDF_GEN);
		}
		
		else
		{
			 populateMAP.put(sq.next(), InvestigationConfig.REPORT_PDF_GEN_INPROCESS);
		}*/
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
		 }	
		return testGrpCombo;
	}
	
	
	
	public List setLabNoComboEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List labNoCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.LABNO_COMBO_RESULT_DUPLICATE_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
		
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
		 }	
		return labNoCombo;
	}
		

	public List setTestComboEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTWISE_COMBO_RESULT_DUPLICATE_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
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
		 }	
		return testCombo;
	}
		


	public List setSamplNoComboEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List sampleNoCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.SAMPLENO_COMBO_RESULT_DUPLICATE_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
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
		 }	
		return sampleNoCombo;
	}
		
	//setTestGroupComboEssentials
	public List setTestGroupComboEssentialsOnLoad(InvDuplicateResultReportPrintingVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMAP= new HashMap();
		ResultSet rs=null;
		List testGrpCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.TESTGROUP_COMBO_RESULT_DUPLICATE_REPORT_ONLOAD_HIVT_REQUISITION_HEADER";
		Sequence sq= new Sequence();//  
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
		 }	
		return testGrpCombo;
	}
		
	
}

