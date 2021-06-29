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
import hisglobal.hisconfig.Config;
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
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.vo.InvParameterMasterVO;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TriStringObject;


public class departmentSpecificResultEntryDAO extends DataAccessObject
{
	public departmentSpecificResultEntryDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
	
	public List LabComboForResultEntry(ResultEntryVO invresultentryvo,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LAB_COMBO_FOR_RESULT_ENTRY_HIVT_LABORATORY_MST";
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
				parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		
	
	
	 
	  public  List<ResultEntryVO>  showCannedDetails(String labCode,String cannedMacroCheck,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<ResultEntryVO> resultentryvo=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT_CANNED_DETAILS_RESULT_ENTRY_HIVT_LAB_CANNED_MST";
				String queryKey1 = "SELECT_MACRO_DETAILS_RESULT_ENTRY_HIVT_LAB_MACRO_MAPPING_MST";
				Sequence sq = new Sequence();
				
				 

				ResultEntryVO invReqRaisingVO;
				
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				populateMAP.put(sq.next(),labCode);
				try
				{
					if(cannedMacroCheck.equals("CANNED"))
					{
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					}
					if(cannedMacroCheck.equals("MACRO"))
					{
						query = HelperMethodsDAO.getQuery(filename, queryKey1);
					}
                   
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					
					 
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			                throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                resultentryvo=new ArrayList<ResultEntryVO>();
			                ResultEntryVO voCanTest=null;
			                while (rs.next()) {
			                	voCanTest=new ResultEntryVO();
			                    HelperMethods.populateVOfrmRS(voCanTest, rs);
			                    resultentryvo.add(voCanTest);
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
				return resultentryvo;

			}
	
	public List<ResultEntryVO> setPatientResultEntryEssentials(ResultEntryVO invresultentryvo, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.DEPT.RESULT_ENTRY_HIVT_REQUISITION_HEADER";
		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+invresultentryvo.getPatCRNo();
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%"+invresultentryvo.getTempPatName()+"%'";
		String condition1=" AND gnum_test_code ="+invresultentryvo.getTestWiseCode();
		String condition2="AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')>=regexp_replace('"+invresultentryvo.getFromSampleNo()+"','[^[:digit:]]','','g') AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')<=regexp_replace('"+invresultentryvo.getToSampleNo()+"','[^[:digit:]]','','g')";
		String condition3="AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')>=regexp_replace('"+invresultentryvo.getFromLabNo()+"','[^[:digit:]]','','g') AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')<=regexp_replace('"+invresultentryvo.getToLabNo()+"','[^[:digit:]]','','g')";
		String condition4=" AND hgnum_group_code ="+invresultentryvo.getTestGroupCode();
		String reqDtlValue="";
		String conditionLab="";
		
		//append order by condition in this string
		//String orderByCondition=" order by hivt_entry_date,patCRNo,tempSampleNo";//,hivtnum_req_dno ";
		String orderByCondition=" order by hivdt_coll_date_time,tempSampleNo";//,hivtnum_req_dno,hivt_entry_date,patCRNo, ";

		//req dtl value division for new entry and modification
		if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_dept_reqdtl_status in (31) "; 
		}
		
		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") and hivnum_dept_reqdtl_status is NULL ";
			
		
		if(invresultentryvo.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
		
		
		
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
			populateMAP.put(sq.next(),invresultentryvo.getFromDate());
			populateMAP.put(sq.next(),invresultentryvo.getToDate());

			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		List<ResultEntryVO> lstInvResultEntryVO = new ArrayList<ResultEntryVO>();
		List<ResultEntryVO> finalLstInvResultEntryVO = new ArrayList<ResultEntryVO>();
		ValueObject[] valueObjects = null;
		query+=conditionLab;
		query+=reqDtlValue;
		try
		{
			if(invresultentryvo.getPatCRNo()!=null&&!invresultentryvo.getPatCRNo().equals(""))
			{
				query+=condition5;
			}
			
			if(invresultentryvo.getTempPatName()!=null&&!invresultentryvo.getTempPatName().equals(""))
			{
				query+=searchPatName;
			}
			
			
			if(invresultentryvo.getGenerationType()!=null&&!invresultentryvo.getOnLoadValue().equals("ONLOAD"))
			{
				if(invresultentryvo.getGenerationType().equals("T")&&!invresultentryvo.getTestWiseCode().equals("-1"))
				{
					query+=condition1;
				}
				
				if(invresultentryvo.getGenerationType().equals("L")&&!invresultentryvo.getFromLabNo().equals("-1")&&!invresultentryvo.getToLabNo().equals("-1"))
				{
				 
					query+=condition3;
				}
				
				if(invresultentryvo.getGenerationType().equals("S")&&!invresultentryvo.getFromSampleNo().equals("-1")&&!invresultentryvo.getToSampleNo().equals("-1"))
				{
					query+=condition2;
					
				}
				
				if(invresultentryvo.getGenerationType().equals("TG")&&!invresultentryvo.getTestGroupCode().equals("-1")&&!invresultentryvo.getTestGroupCode().equals(""))
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
				valueObjects = HelperMethods.populateVOfrmRS(ResultEntryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvResultEntryVO.add((ResultEntryVO)valueObjects[i]);
					if(lstInvResultEntryVO.get(i).getReqDtlStatus().equals(InvestigationConfig.READY_RESULTPRINTING) || lstInvResultEntryVO.get(i).getReqDtlStatus().equals(InvestigationConfig.REPORT_PDF_GEN))
					{
						if(lstInvResultEntryVO.get(i).getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
						{						
							finalLstInvResultEntryVO.add(lstInvResultEntryVO.get(i));
							//status 13 and 26 added if its report is available after this process only
						}
					}
					else{
						finalLstInvResultEntryVO.add(lstInvResultEntryVO.get(i)); //add all other values
					}
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
		}
		return finalLstInvResultEntryVO;
	}	
	
	
	public void updateResultEntryInRequisitionDtl(ResultEntryVO voResultEntry, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.REQDTLS.DEPT.RESULT.ENTRY.HIVT_REQUISITION_DTLS";
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
				
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(),voResultEntry.getReqDtlStatus());
			
			populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
			populateMAP.put(sq.next(), voResultEntry.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());

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
	
	
	
	public void insertResultEntryDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.DEPT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			 
			 
			
            populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
            //Need To Be ADD IS_VALID_ACTIVE
            populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
            
            populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
            populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
            populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
            populateMAP.put(sq.next(),_UserVO.getHospitalCode());
            
            
            populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
            populateMAP.put(sq.next(),_UserVO.getHospitalCode());
            
           
            
            
            populateMAP.put(sq.next(), _UserVO.getSeatId());
            populateMAP.put(sq.next(), voResultEntry.getResultEntryValue());
			populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), voResultEntry.getLoincCode()); 
			populateMAP.put(sq.next(), voResultEntry.getStrRefRange()); 
			
			//populateMAP.put(sq.next(), voResultEntry.getRefRange()); 

			
			
			
        	
            	
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
 
		


//insertResultEntryDtlInJobWorkorderData


public void insertResultEntryDtlInJobWorkorderData(ResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.DEPT.RESULT.ENTRY.DETAIL.HIVT_JOBWORKORDER_DATA";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		 
		
		 
		
	    populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
        //Need To Be ADD IS_VALID_ACTIVE
		 populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
		 populateMAP.put(sq.next(), InvestigationConfig.UPDATION_TYPE);
		 populateMAP.put(sq.next(), InvestigationConfig.STATUS_CODE);
		 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		 populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
	        populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
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
        populateMAP.put(sq.next(), voResultEntry.getTempSampleNo());

        if(voResultEntry.getDynamicTemplatePrintedGroup() != null && voResultEntry.getDynamicTemplatePrintedGroup().equals("1"))
        {
        	populateMAP.put(sq.next(), "");
        }
        else
        {
        	populateMAP.put(sq.next(), "");
        }
		
		
    	
        	
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




public String getLoincCode(String passValue) {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.TEMPLATE_PROCESSING.LOINC_CODE";
	
	String[] values=passValue.split("#");
	String testCode=values[0];
	String paraCode=values[1];
	String sampleCode=values[2];
	String uomCode=values[3];
	
	System.out.println("test code: " + testCode + " paraCode: " + paraCode);
	JDBCTransactionContext tx = new JDBCTransactionContext();
	tx.begin();
	Connection conn = tx.getConnection();
	String loincCode = null;
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
//	}
//	catch (Exception e)
//	{
//		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
//	}

	System.out.println("Query ---------> " + query);
	Sequence sq = new Sequence();
//	try
//	{				
		populateMAP.put(sq.next(),testCode);
		populateMAP.put(sq.next(),paraCode);
		populateMAP.put(sq.next(),sampleCode);
		populateMAP.put(sq.next(),uomCode);			
	//	populateMAP.put(sq.next(),"100");												
//	}
//	catch (Exception e)
//	{
//		throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
//	}
//	
//	try
//	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		while(rs.next())
		{
			loincCode = rs.getString(1);
			System.out.println("loincCode found: " + loincCode);
		}
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException();	
		}
		else if(e.getClass() == HisDataAccessException.class)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		else if(e.getClass() == HisApplicationExecutionException.class)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		else
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	finally 
	{
		tx.close();
	}
	return loincCode;
	//return "41758-4";
}



public List setLabNoComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List labNoCombo=new ArrayList();
	List finalLabNoCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.LABNO_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
	String orderBy=" order by labNo";
	String reqDtlValue="";
	String conditionLab="";

	
	//req dtl value division for new entry and modification
	if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
		
	
	
	if(invresultentryvo.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		
		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),invresultentryvo.getFromDate());
		populateMAP.put(sq.next(),invresultentryvo.getToDate());
		//populateMAP.put(sq.next(),invresultentryvo.getLabCode());
		 
	try
	{
		
		
		query = HelperMethodsDAO.getQuery(filename,queryKey);

		
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	query+=conditionLab;
	query=query+reqDtlValue+orderBy;
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
		
		rs.beforeFirst();
		while(rs.next())
		{ reportAvailAfter.add(rs.getString(3));
		reqStatus.add(rs.getString(4));
		}
		
		for (int i = 0; i < reportAvailAfter.size(); i++)
		{
			//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
			
			if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
			{
				if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
				{	
					if(!finalLabNoCombo.contains(labNoCombo.get(i)))
					finalLabNoCombo.add(labNoCombo.get(i))	; 
					//status 13 and 26 added if its report is available after this process only
				}
				
			}
			else{
				if(!finalLabNoCombo.contains(labNoCombo.get(i)))
				finalLabNoCombo.add(labNoCombo.get(i))	; //add all other values
			}
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
	return finalLabNoCombo;
}
	

public List setTestComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testCombo=new ArrayList();
	List finalTestCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.TESTWISE_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
	String orderBy="	order by testName";
String reqDtlValue="";
String conditionLab="";

	
	//req dtl value division for new entry and modification
	if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
		
	
	if(invresultentryvo.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
		
		
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
   populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
	populateMAP.put(sq.next(),invresultentryvo.getFromDate());
	populateMAP.put(sq.next(),invresultentryvo.getToDate());
//	populateMAP.put(sq.next(),invresultentryvo.getLabCode());
	
	 
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	query+=conditionLab;
	query=query+reqDtlValue+orderBy;
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
		
		rs.beforeFirst();
		while(rs.next())
		{ reportAvailAfter.add(rs.getString(3));
		reqStatus.add(rs.getString(4));
		}
		
		for (int i = 0; i < reportAvailAfter.size(); i++)
		{
			//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
			
			if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
			{
				if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
				{						
					finalTestCombo.add(testCombo.get(i))	; 
					//status 13 and 26 added if its report is available after this process only
				}
				
			}
			else{
				finalTestCombo.add(testCombo.get(i))	; //add all other values
			}
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
	return finalTestCombo;
}
	


public List setSamplNoComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List sampleNoCombo=new ArrayList();
	List finalSampleNoCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.SAMPLENO_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
	String orderBy="order by sampleNo";
String reqDtlValue="";
String conditionLab="";
	
	//req dtl value division for new entry and modification
	if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
	
	
	if(invresultentryvo.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
		
		
		
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
	populateMAP.put(sq.next(),invresultentryvo.getFromDate());
	populateMAP.put(sq.next(),invresultentryvo.getToDate());
	//populateMAP.put(sq.next(),invresultentryvo.getLabCode());
	
	 
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	query+=conditionLab;

	query=query+reqDtlValue+orderBy;

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
			
			//rs.beforeFirst();
			//reportAvailAfter.add()
		}
		
		rs.beforeFirst();
		while(rs.next())
		{ reportAvailAfter.add(rs.getString(3));
		reqStatus.add(rs.getString(4));
		}
		
		for (int i = 0; i < reportAvailAfter.size(); i++)
		{
			//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
			
			if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
			{
				if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
				{	
					
					if(!finalSampleNoCombo.contains(sampleNoCombo.get(i)))
					finalSampleNoCombo.add(sampleNoCombo.get(i))	; 
					//status 13 and 26 added if its report is available after this process only
				}
				
			}
			else{
				if(!finalSampleNoCombo.contains(sampleNoCombo.get(i)))
				finalSampleNoCombo.add(sampleNoCombo.get(i))	; //add all other values
			}
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
	return finalSampleNoCombo;
}
	
//setTestGroupComboEssentials
public List setTestGroupComboEssentials(ResultEntryVO invresultentryvo,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	List finalTestGroupCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.TESTGROUP_COMBO_RESULT_ENTRY_HIVT_REQUISITION_HEADER";
	String orderBy="order by groupName";
String reqDtlValue="";
String conditionLab="";
	
	//req dtl value division for new entry and modification
	if(invresultentryvo.getNewEntry()!=null&&invresultentryvo.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
	
	if(invresultentryvo.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+invresultentryvo.getLabCode();
		
		
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
	populateMAP.put(sq.next(),invresultentryvo.getFromDate());
	populateMAP.put(sq.next(),invresultentryvo.getToDate());
	//populateMAP.put(sq.next(),invresultentryvo.getLabCode());
	
	 
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename,queryKey);
	}
	catch(Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	query+=conditionLab;
	query=query+reqDtlValue+orderBy;

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
		
		rs.beforeFirst();
		while(rs.next())
		{ reportAvailAfter.add(rs.getString(3));
		reqStatus.add(rs.getString(4));
		}
		
		for (int i = 0; i < reportAvailAfter.size(); i++)
		{
			//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
			
			if(reqStatus.get(i).contains(InvestigationConfig.READY_RESULTPRINTING) || reqStatus.get(i).contains(InvestigationConfig.REPORT_PDF_GEN))
			{
				if(reportAvailAfter.get(i).contains(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
				{						
					finalTestGroupCombo.add(testGrpCombo.get(i))	; 
					//status 13 and 26 added if its report is available after this process only
				}
				
			}
			else{
				finalTestGroupCombo.add(testGrpCombo.get(i))	; //add all other values
			}
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
	return finalTestGroupCombo;
}


public void updateModifyResultEntryInRequisitionDtl(ResultEntryVO voResultEntry, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.REQDTLS.DEPT.RESULT.ENTRY.MODIFY.HIVT_REQUISITION_DTLS";
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
			
		populateMAP.put(sq.next(), _UserVO.getSeatId());
		populateMAP.put(sq.next(),voResultEntry.getReqDtlStatus());
		
		populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
		populateMAP.put(sq.next(), voResultEntry.getTestCode());
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

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


public void modifyResultValidationDtl(ResultEntryVO voResultValidation, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.DEPT.RESULT.ENTRY.DETAIL.HIVT_PARAMETER_DTL";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		populateMAP.put(sq.next(), voResultValidation.getResultEntryValue());
		populateMAP.put(sq.next(), _UserVO.getSeatId());

//WHERE            
		populateMAP.put(sq.next(), voResultValidation.getSeqNo());
		populateMAP.put(sq.next(), voResultValidation.getParameterRequisitionDNo());
//		populateMAP.put(sq.next(), voResultValidation.getTestParaMeterCode()); 
//		populateMAP.put(sq.next(), voResultValidation.getParantParaCode());  
		populateMAP.put(sq.next(), voResultValidation.getTestCode()); 
		populateMAP.put(sq.next(), voResultValidation.getTestParaMeterCode());
		populateMAP.put(sq.next(), voResultValidation.getParantParaCode());
		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		
    	
        	
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
	

public void insertResultLogDtl(ResultEntryVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.RESULT.LOG.DETAIL.HIVT_RESULT_LOG_DTL";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		 
		
        populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
        populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
        populateMAP.put(sq.next(), voResultEntry.getParantParaCode());  
    	populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
        populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        
        populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        populateMAP.put(sq.next(), voResultEntry.getParameterRequisitionDNo());
        populateMAP.put(sq.next(), voResultEntry.getTestCode()); 
      	populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode());
      	
      	
        populateMAP.put(sq.next(), _UserVO.getSeatId());
        populateMAP.put(sq.next(), voResultEntry.getResultEntryValue());
        populateMAP.put(sq.next(), voResultEntry.getPreviousValue());
		
		populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), InvestigationConfig.RESULT_MODIFICATION_PROCESS);
		
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
		
		
		//populateMAP.put(sq.next(), voResultEntry.getRefRange()); 

		
		
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in hivt_result_log_dtl Table");
	}

}


public  List  autoCannedDetails(String labCode,String cannedMacroCheck,UserVO _UserVO)
{
	String query = "";		
	List<String> lstCannedCodes=new ArrayList<String>();
	Map populateMAP = new HashMap();
	ResultSet rs = null;
	List<ResultEntryVO> resultentryvo=null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT_CANNED_CODE_DETAILS_RESULT_ENTRY_HIVT_LAB_CANNED_MST";
	String queryKey1 = "SELECT_MACRO_CODE_DETAILS_RESULT_ENTRY_HIVT_LAB_MACRO_MAPPING_MST";
	Sequence sq = new Sequence();
	
	 

	ResultEntryVO invReqRaisingVO;

	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	populateMAP.put(sq.next(),labCode);
	try
	{
		if(cannedMacroCheck.equals("CANNED"))
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		if(cannedMacroCheck.equals("MACRO"))
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey1);
		}
       
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	try
	{
		
		 
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
				populateMAP);
           
            if (!rs.next())
            {
                throw new HisRecordNotFoundException("No Test / Lab  Found");
            }
            else
            {
                rs.beforeFirst();
                
                if(rs.next())
				{
					lstCannedCodes=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
	return lstCannedCodes;

}

public void updateFinalRemarkInRequisitionHeader(String reqNo,String finalRemark, UserVO _UserVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.FINALREMARK.HIVT_REQUISITION_HEADER";
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
			
		
		populateMAP.put(sq.next(), finalRemark);
		  
		populateMAP.put(sq.next(), reqNo);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("resultEntryDAO.populateMAP::" + e);
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

//modify canned check user code
public String checkcannedCode(InvResultEntryFB fb, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="CHECK_CANNED_CODE.HIVT_LAB_CANNED_MST";
	String record=null;
	/*try
	{
		int value= Integer.parseInt(fb.getCannedCode());
		fb.getCannedCode().valueOf(value);
	}
	catch(Exception e)
	{
		
	}*/

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		return record;
	}
	try
	{
		populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
		populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		populateMAP.put(sq.next(), fb.getCannedCode());

	}

	catch (Exception e)
	{
		return record;
	}
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
		return record;
	}
	return record;

}



public String checkcannedName(InvResultEntryFB fb, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="CHECK_CANNED_NAME.HIVT_CANNED_MST";
	String record=null;

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		return record;
	}
	try
	{
		populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
		populateMAP.put(sq.next(),fb.getCannedName());
		

	}

	catch (Exception e)
	{
		return record;
	}
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
		return record;
	}
	return record;

}


public void insertModifyCanned(InvResultEntryFB fb, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.MODIFT_CANNED.HIVT_CANNED_MST";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		 
		
        populateMAP.put(sq.next(), _UserVO.getSeatId());
    	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), fb.getCannedContent()); 
		populateMAP.put(sq.next(), fb.getCannedName()); 
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_CANNED_MST Table");
	}

}

public String fetchCode(InvResultEntryFB fb, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="SELECT.CODE.HIVT_LAB_CANNED_MST";
	String record=null;

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		return record;
	}
	try
	{
		populateMAP.put(sq.next(),fb.getCannedName());
		
	}

	catch (Exception e)
	{
		return record;
	}
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
		return record;
	}
	return record;

}


public void insertUserEnterCode(InvResultEntryFB fb, UserVO _UserVO,String code) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="INSERT.USER_CODE.HIVT_LAB_CANNED_MST";

	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {
		 
		 
		populateMAP.put(sq.next(), code);
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), fb.getModifycannedLabCode());
		populateMAP.put(sq.next(), _UserVO.getSeatId());
    	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), fb.getCannedCode()); 
		
    	
        	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in HIVT_CANNED_MST Table");
	}

}


public List getResultValidfationDataList(ResultEntryVO InvResultEntryVO)
{
	//String hosCode=httpSession.getAttribute("HOSPITAL_CODE").toString();

	String query=null;
	ResultSet rs=null;

	Sequence sq = new Sequence();
	Map populateMap= new HashMap();

	List<TriStringObject> resultValidationList=new ArrayList<TriStringObject>();	
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.HIVT_DEPT_PARAMETER_DTL.WORKORDERRESULTENTRYDATA.RESULTVALIDATIONPROCESS";			
	Connection conn = super.getTransactionContext().getConnection();	

	populateMap.put(sq.next(), InvResultEntryVO.getRequisitionDNo());
	populateMap.put(sq.next(), InvResultEntryVO.getHospitalcode());

	//   populateMap.put(sq.next(), hosCode);


	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query :WorkOrder List For Result Validation "+query);
	} 
	catch (Exception e) 
	{
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try
	{


		rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);


		while(rs.next())
		{

			String parentCode="";

			if(rs.getString(1).equals(rs.getString(3)))
				parentCode=rs.getString(3);
			else
				parentCode=rs.getString(3)+rs.getString(1);

			TriStringObject triStringObject=  new TriStringObject(rs.getString(1),rs.getString(2), parentCode);
			if(resultValidationList==null)
				resultValidationList=new ArrayList<TriStringObject>();


			resultValidationList.add(triStringObject);
		}

	}		
	catch(Exception e)
	{
		e.printStackTrace();
		throw  new HisDataAccessException("RESULT Validation :getResultValidfationDataList  :"+ e);
	}		
	return resultValidationList;


}



public String checkParameterPresent(ResultEntryVO resultentry_vo, UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="CHECK.PARA.PARA_DTL";
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

		//WHERE            
				populateMAP.put(sq.next(), resultentry_vo.getSeqNo());  
				populateMAP.put(sq.next(), resultentry_vo.getParameterRequisitionDNo());  
				populateMAP.put(sq.next(), resultentry_vo.getTestCode()); 
				populateMAP.put(sq.next(), resultentry_vo.getTestParaMeterCode());
				populateMAP.put(sq.next(), resultentry_vo.getParantParaCode());
				populateMAP.put(sq.next(),_UserVO.getHospitalCode());

	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("dept parameter prescence check.populateMAP::" + e);
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
		else throw new HisDataAccessException("dept parameter prescence check.getResultset" + e);
	}
	return record;
}


}

