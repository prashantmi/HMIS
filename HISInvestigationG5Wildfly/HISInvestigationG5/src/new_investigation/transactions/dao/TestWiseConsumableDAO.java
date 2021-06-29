package new_investigation.transactions.dao;

/**
 * @author : C-DAC, Noida
 * Project : HISInvestigationG5
 * Module  : New_Investigation
 * Created On : 07/09/2016
 * 
 * Developed By : Jatin Kumar
 * 
 * Purpose : Test Wise Consumable Process DAO
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
import hisglobal.utility.Entry;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
import new_investigation.vo.template.TestWiseConsumableVO;


public class TestWiseConsumableDAO extends DataAccessObject
{
	public TestWiseConsumableDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
	
	public List LabComboForResultEntry(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List parameterCombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT_LAB_COMBO_FOR_TEST_WISE_HIVT_LABORATORY_MST";
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
		
	
	
	 
	
	public List<TestWiseConsumableVO> setPatientResultEntryEssentials(TestWiseConsumableVO testWiseConsumableVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.TEST_WISE_HIVT_REQUISITION_HEADER";
		String condition5="And (Select hrgnum_puk from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no)="+testWiseConsumableVO.getPatCRNo();
		String searchPatName=" And (Select lower(hrgstr_patname) from hivt_requisition_header where gnum_hospital_code = a.gnum_hospital_code and hivnum_req_no = a.hivnum_req_no) like '%"+testWiseConsumableVO.getTempPatName()+"%'";
		String condition1=" AND gnum_test_code ="+testWiseConsumableVO.getTestWiseCode();
		String condition2="AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')>=regexp_replace('"+testWiseConsumableVO.getFromSampleNo()+"','[^[:digit:]]','','g') AND regexp_replace(hivnum_temp_sample_no,'[^[:digit:]]','','g')<=regexp_replace('"+testWiseConsumableVO.getToSampleNo()+"','[^[:digit:]]','','g')";
		String condition3="AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')>=regexp_replace('"+testWiseConsumableVO.getFromLabNo()+"','[^[:digit:]]','','g') AND regexp_replace(hgstr_daily_labno,'[^[:digit:]]','','g')<=regexp_replace('"+testWiseConsumableVO.getToLabNo()+"','[^[:digit:]]','','g')";
		String condition4=" AND hgnum_group_code ="+testWiseConsumableVO.getTestGroupCode();
		String reqDtlValue="";
		String conditionLab="";
		
		//append order by condition in this string
		//String orderByCondition=" order by hivt_entry_date,patCRNo,tempSampleNo";//,hivtnum_req_dno ";
		String orderByCondition=" order by hivdt_coll_date_time,tempSampleNo";//,hivtnum_req_dno,hivt_entry_date,patCRNo, ";

		//req dtl value division for new entry and modification
	/*	if(testWiseConsumableVO.getNewEntry()!=null&&testWiseConsumableVO.getNewEntry().equals("2"))
		{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
		}
		
		else
			reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";*/
			
		
		if(testWiseConsumableVO.getLabCode().equals("%"))
			conditionLab=" and gnum_lab_code like '%' ";
		else
			conditionLab=" and gnum_lab_code="+testWiseConsumableVO.getLabCode();
		
		
		
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
			//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
			populateMAP.put(sq.next(),testWiseConsumableVO.getFromDate());
			populateMAP.put(sq.next(),testWiseConsumableVO.getToDate());
		//	populateMAP.put(sq.next(),testWiseConsumableVO.getLabCode());
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			 
			//populateMAP.put(sq.next(), patVO.getPatCRNo());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		List<TestWiseConsumableVO> lsttestWiseConsumableVO = new ArrayList<TestWiseConsumableVO>();
		List<TestWiseConsumableVO> finalLsttestWiseConsumableVO = new ArrayList<TestWiseConsumableVO>();
		ValueObject[] valueObjects = null;
		query+=conditionLab;
		query+=reqDtlValue;
		try
		{
			if(testWiseConsumableVO.getPatCRNo()!=null&&!testWiseConsumableVO.getPatCRNo().equals(""))
			{
				query+=condition5;
			}
			
			if(testWiseConsumableVO.getTempPatName()!=null&&!testWiseConsumableVO.getTempPatName().equals(""))
			{
				query+=searchPatName;
			}
			
			
			if(testWiseConsumableVO.getGenerationType()!=null&&!testWiseConsumableVO.getOnLoadValue().equals("ONLOAD"))
			{
				if(testWiseConsumableVO.getGenerationType().equals("T")&&!testWiseConsumableVO.getTestWiseCode().equals("-1"))
				{
					query+=condition1;
				}
				
				if(testWiseConsumableVO.getGenerationType().equals("L")&&!testWiseConsumableVO.getFromLabNo().equals("-1")&&!testWiseConsumableVO.getToLabNo().equals("-1"))
				{
				 
					query+=condition3;
				}
				
				if(testWiseConsumableVO.getGenerationType().equals("S")&&!testWiseConsumableVO.getFromSampleNo().equals("-1")&&!testWiseConsumableVO.getToSampleNo().equals("-1"))
				{
					query+=condition2;
					
				}
				
				if(testWiseConsumableVO.getGenerationType().equals("TG")&&!testWiseConsumableVO.getTestGroupCode().equals("-1")&&!testWiseConsumableVO.getTestGroupCode().equals(""))
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
				valueObjects = HelperMethods.populateVOfrmRS(TestWiseConsumableVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lsttestWiseConsumableVO.add((TestWiseConsumableVO)valueObjects[i]);
					if(lsttestWiseConsumableVO.get(i).getReqDtlStatus().equals(InvestigationConfig.READY_RESULTPRINTING) || lsttestWiseConsumableVO.get(i).getReqDtlStatus().equals(InvestigationConfig.REPORT_PDF_GEN))
					{
						if(lsttestWiseConsumableVO.get(i).getReportAvailableAfter().equals(InvestigationConfig.REPORTAVAILABLEAFTERRESULTPRINTING))
						{						
							finalLsttestWiseConsumableVO.add(lsttestWiseConsumableVO.get(i));
							//status 13 and 26 added if its report is available after this process only
						}
					}
					else{
						finalLsttestWiseConsumableVO.add(lsttestWiseConsumableVO.get(i)); //add all other values
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
		return finalLsttestWiseConsumableVO;
	}	
	











public List setLabNoComboEssentials(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List labNoCombo=new ArrayList();
	List finalLabNoCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.LABNO_COMBO_TEST_WISE_HIVT_REQUISITION_HEADER";
	String orderBy=" order by labNo";
	String reqDtlValue="";
	String conditionLab="";

	
	//req dtl value division for new entry and modification
	/*if(testWiseConsumableVO.getNewEntry()!=null&&testWiseConsumableVO.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";*/
		
	
	
	if(testWiseConsumableVO.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+testWiseConsumableVO.getLabCode();
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		
		//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
		populateMAP.put(sq.next(),testWiseConsumableVO.getFromDate());
		populateMAP.put(sq.next(),testWiseConsumableVO.getToDate());
		//populateMAP.put(sq.next(),testWiseConsumableVO.getLabCode());
		 
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
	

public List setTestComboEssentials(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testCombo=new ArrayList();
	List finalTestCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.TESTWISE_COMBO_LAB_WISE_HIVT_REQUISITION_HEADER";
	String orderBy="	order by testName";
String reqDtlValue="";
String conditionLab="";

	
	//req dtl value division for new entry and modification
	/*if(testWiseConsumableVO.getNewEntry()!=null&&testWiseConsumableVO.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
		*/
	
	if(testWiseConsumableVO.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+testWiseConsumableVO.getLabCode();
		
		
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
   populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
	populateMAP.put(sq.next(),testWiseConsumableVO.getFromDate());
	populateMAP.put(sq.next(),testWiseConsumableVO.getToDate());
//	populateMAP.put(sq.next(),testWiseConsumableVO.getLabCode());
	
	 
	
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
	


public List setSamplNoComboEssentials(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List sampleNoCombo=new ArrayList();
	List finalSampleNoCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.SAMPLENO_COMBO_TEST_WISE_HIVT_REQUISITION_HEADER";
	String orderBy="order by sampleNo";
String reqDtlValue="";
String conditionLab="";
	
	//req dtl value division for new entry and modification
	/*if(testWiseConsumableVO.getNewEntry()!=null&&testWiseConsumableVO.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
	*/
	
	if(testWiseConsumableVO.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+testWiseConsumableVO.getLabCode();
		
		
		
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
	populateMAP.put(sq.next(),testWiseConsumableVO.getFromDate());
	populateMAP.put(sq.next(),testWiseConsumableVO.getToDate());
	//populateMAP.put(sq.next(),testWiseConsumableVO.getLabCode());
	
	 
	
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
public List setTestGroupComboEssentials(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	List finalTestGroupCombo=new ArrayList();
	List<String> reportAvailAfter=new ArrayList<String>();
	List<String> reqStatus=new ArrayList<String>();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.TESTGROUP_COMBO_LAB_WISE_HIVT_REQUISITION_HEADER";
	String orderBy="order by groupName";
String reqDtlValue="";
String conditionLab="";
	
	//req dtl value division for new entry and modification
	/*if(testWiseConsumableVO.getNewEntry()!=null&&testWiseConsumableVO.getNewEntry().equals("2"))
	{		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISTION_DTL_RESULT_ENTRY_STATUS+","+InvestigationConfig.READY_RESULTPRINTING+","+InvestigationConfig.REPORT_PDF_GEN+") "; 
	}
	
	else
		reqDtlValue=" and hivnum_reqdtl_status in ("+InvestigationConfig.REQUISITION_DTL_STATUS_RESULT_ENTRY+") ";
	*/
	if(testWiseConsumableVO.getLabCode().equals("%"))
		conditionLab=" and gnum_lab_code like '%' ";
	else
		conditionLab=" and gnum_lab_code="+testWiseConsumableVO.getLabCode();
		
		
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	//populateMAP.put(sq.next(),onlinePatientvo.getPatCRNo());
	populateMAP.put(sq.next(),testWiseConsumableVO.getFromDate());
	populateMAP.put(sq.next(),testWiseConsumableVO.getToDate());
	//populateMAP.put(sq.next(),testWiseConsumableVO.getLabCode());
	
	 
	
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

public TestWiseConsumableVO[] getTestWiseConsumableList(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_OTHER_ITEM_MST.ALL.CONSUMTION";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), testWiseConsumableVO.getLabCode());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
	 
	
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
			throw new HisRecordNotFoundException("No Test Found,Please Map Test For This Lab!");
		}
		else
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(TestWiseConsumableVO.class, rs);
			testWiseConsumableVOList=new TestWiseConsumableVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++)
			{
				testWiseConsumableVOList[i]=(TestWiseConsumableVO)valueObjects[i];
			}
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return testWiseConsumableVOList;
}


public int saveConsumableList(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="INSERT.HIVT_ITEM_CONSUMPTION_DTL.CONSUMPTION_DTL";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	int count=0;
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getItemName());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	//populateMAP.put(sq.next(), testWiseConsumableVO.getSlNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	populateMAP.put(sq.next(), testWiseConsumableVO.getLabCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), testWiseConsumableVO.getTestCode());
	populateMAP.put(sq.next(), testWiseConsumableVO.getLotNo());
	populateMAP.put(sq.next(), _UserVO.getSeatId());
	//sysdate
	populateMAP.put(sq.next(), testWiseConsumableVO.getItemTypeID());
	populateMAP.put(sq.next(), testWiseConsumableVO.getManufacture());
	populateMAP.put(sq.next(), testWiseConsumableVO.getExpiryDate());
	populateMAP.put(sq.next(), testWiseConsumableVO.getRemarks());
	populateMAP.put(sq.next(), testWiseConsumableVO.getStoreName());
	populateMAP.put(sq.next(), testWiseConsumableVO.getItemID());
	populateMAP.put(sq.next(), testWiseConsumableVO.getDefaultQuantity());
	populateMAP.put(sq.next(), testWiseConsumableVO.getCrNo());//puk
	populateMAP.put(sq.next(),testWiseConsumableVO.getUnitCode());
	populateMAP.put(sq.next(), _UserVO.getSeatId());
	//sysdate
	
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
		count= HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return count;
}
public int getCount(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_ITEM_CONSUMPTION_DTL.COUNT";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	int count=0;
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), testWiseConsumableVO.getSlNo());
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
			
		}
		else
		{
			count=rs.getInt(1);
			
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return count;
}

public List getLotCombo(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List lotCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_ITEM_USES_DTL.LOT";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	int count=0;
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(),InvestigationConfig.USAGE_STATUS_INUSE_VALUE);
	 
	
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
			return lotCombo;
		}
		else
		{
			rs.beforeFirst();
			lotCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return lotCombo;
}
public Entry getLotNQuantity(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List lotValuesCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_ITEM_COMSUMPTION_DTL.LOT.QUANTITY";
	ValueObject []valueObjects=null;
	String values="";
	Entry entry=new Entry();
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	int count=0;
	//hivtnum_req_dno, hbnum_slno, hbnum_other_item_code, gnum_hospital_code
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	populateMAP.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE); 
	
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
			return null;
		}
		else
		{
			entry.setLabel(rs.getString(1));
			entry.setValue(rs.getString(2));
			
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return entry;
}
public int updateConsumableList(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="UPDATE.HIVT_ITEM_CONSUMPTION_DTL.CONSUMPTION_DTL";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	int count=0;
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getItemName());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	
	
	
	populateMAP.put(sq.next(), testWiseConsumableVO.getLabCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), testWiseConsumableVO.getTestCode());
	populateMAP.put(sq.next(), testWiseConsumableVO.getLotNo());
	
	//sysdate
	populateMAP.put(sq.next(), testWiseConsumableVO.getItemTypeID());
	populateMAP.put(sq.next(), testWiseConsumableVO.getManufacture());
	populateMAP.put(sq.next(), testWiseConsumableVO.getExpiryDate());
	populateMAP.put(sq.next(), testWiseConsumableVO.getRemarks());
	populateMAP.put(sq.next(), testWiseConsumableVO.getStoreName());
	populateMAP.put(sq.next(), testWiseConsumableVO.getItemID());
	populateMAP.put(sq.next(), testWiseConsumableVO.getDefaultQuantity());
	populateMAP.put(sq.next(), testWiseConsumableVO.getCrNo());//puk
	populateMAP.put(sq.next(),testWiseConsumableVO.getUnitCode());
	populateMAP.put(sq.next(), _UserVO.getSeatId());
	//sysdate
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), testWiseConsumableVO.getSlNo());
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
		count= HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return count;
}
public TestWiseConsumableVO[] getPatientDetails(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_REQUISITION_HEADER.PATIENT_DTL";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionNo());
	
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
			throw new HisRecordNotFoundException("No Patient Details Found");
		}
		else
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(TestWiseConsumableVO.class, rs);
			testWiseConsumableVOList=new TestWiseConsumableVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++)
			{
				testWiseConsumableVOList[i]=(TestWiseConsumableVO)valueObjects[i];
			}
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return testWiseConsumableVOList;
}
public TestWiseConsumableVO[] getTestWiseConsumableListConsumableDetail(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_ITEM_COMSUMPTION_DTL.ITEM_LIST";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	
	 
	
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
			//throw new HisRecordNotFoundException("No Test Found,Please Map Test For This Lab!");
		}
		else
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(TestWiseConsumableVO.class, rs);
			testWiseConsumableVOList=new TestWiseConsumableVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++)
			{
				testWiseConsumableVOList[i]=(TestWiseConsumableVO)valueObjects[i];
			}
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			//throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return testWiseConsumableVOList;
}
public TestWiseConsumableVO[] getTestWiseConsumableListALL(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.HIVT_ITEM_USES_DTL.TEMP_ITEM_LIST";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	
	
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	//populateMAP.put(sq.next(), testWiseConsumableVO.getLabCode());
	/*populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());*/
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), InvestigationConfig.USAGE_STATUS_INUSE_VALUE);
	
	 
	
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
			//throw new HisRecordNotFoundException("No Test Found,Please Map Test For This Lab!");
		}
		else
		{
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(TestWiseConsumableVO.class, rs);
			testWiseConsumableVOList=new TestWiseConsumableVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++)
			{
				testWiseConsumableVOList[i]=(TestWiseConsumableVO)valueObjects[i];
			}
		}
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return testWiseConsumableVOList;
}
public int deleteItem(TestWiseConsumableVO testWiseConsumableVO,UserVO _UserVO)
{
	String query="";
	Map populateMAP= new HashMap();
	ResultSet rs=null;
	List testGrpCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="DELETE.HIVT_ITEM_CONSUMPTION_DTL.CONSUMPTION_DTL";
	ValueObject []valueObjects=null;
	TestWiseConsumableVO[] testWiseConsumableVOList=null;	
	int count=0;
	//GNUM_ISVALID=? WHERE hivtnum_req_dno=? AND hbnum_slno=? hbnum_other_item_code=? gnum_hospital_code=?
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	populateMAP.put(sq.next(), "0");
	populateMAP.put(sq.next(), testWiseConsumableVO.getRequisitionDNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getSlNo());
	populateMAP.put(sq.next(), testWiseConsumableVO.getOtherItemID());
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
		count= HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		
	
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException("Record Not Found");
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }	
	return count;
}
}

