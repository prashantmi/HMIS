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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.InvCriteriaCodeVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.TestMandRefMasterVO;
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
import new_investigation.vo.testAvailabilityVO;


public class testAvailabilityDAO extends DataAccessObject
{
	public testAvailabilityDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
	
	public List LabComboForMachineResultEntry(testAvailabilityVO invtestAvailabilityVO,UserVO _UserVO)
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
		
	
	
	public List<testAvailabilityVO> getPatientMachineResultEntry(testAvailabilityVO invtestAvailabilityVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.TEST_AVAILABILITY_DETAILS.HIVT_LABORATORY_TEST_MST";
		
		//based on test status query appended
		// % - both status, 1 available, 0 not available
		String statusAll=" and gnum_is_test_available like '%'";
		String statusAvailable=" and gnum_is_test_available="+invtestAvailabilityVO.getTestStatus();
		String orderBy=" order by isAvailable desc, testName";
		
		String searchTest=" and lower((select gstr_test_name from hivt_test_mst where gnum_isvalid=1 and gnum_test_code=a.gnum_test_code)) like lower('%"+invtestAvailabilityVO.getSearchTest()+"%')    ";
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
				populateMAP.put(sq.next(), invtestAvailabilityVO.getLabCode());
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("test avaialabilty dao .populateMAP ::" + e);
		}
		
		List<testAvailabilityVO> lstInvtestAvailabilityVO = new ArrayList<testAvailabilityVO>();
		ValueObject[] valueObjects = null;
		
		//based on test status query appended
		// % - both status, 1 available, 0 not available
		if(invtestAvailabilityVO.getTestStatus().equals("%"))
			query+=statusAll;
		else
			query+=statusAvailable;
		
		
		if(invtestAvailabilityVO.getSearchTest()!=null)
			query+=searchTest;
		else
			;
		
		try
		{
		
			query+=orderBy;
		
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(testAvailabilityVO.class, rs);
			
				for (int i = 0; i < valueObjects.length; i++)
				{
					lstInvtestAvailabilityVO.add((testAvailabilityVO)valueObjects[i]);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("test avaialbility dao get test details :: " + e);
		}
		return lstInvtestAvailabilityVO;
	}	
	
	
	
	
	public void insertLogDetailsTest(testAvailabilityVO voResultEntry, UserVO _UserVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.TEST_LOG_DETAILS.HIVT_TEST_AVAILABILITY_LOG_DTL";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {



			populateMAP.put(sq.next(), voResultEntry.getTestCode());
			populateMAP.put(sq.next(), voResultEntry.getLabCode()); 
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());

			populateMAP.put(sq.next(), voResultEntry.getTestCode());
			populateMAP.put(sq.next(), voResultEntry.getLabCode()); 
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());


			populateMAP.put(sq.next(), voResultEntry.getIsAvailable()); 
			populateMAP.put(sq.next(), voResultEntry.getRemarks()); 
			populateMAP.put(sq.next(), _UserVO.getSeatId());

			populateMAP.put(sq.next(), voResultEntry.getFromDate());
			populateMAP.put(sq.next(), voResultEntry.getToDate()); 





			
			
        	
            	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"test available dao insertion in log table::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in test available log dtl Table");
		}

	}
 
		


//insertResultEntryDtlInJobWorkorderData


public void insertMachineResultEntryDtlInJobWorkorderData(testAvailabilityVO voResultEntry, UserVO _UserVO) {

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
		 
		 
		
		 
		
	   // populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
        //Need To Be ADD IS_VALID_ACTIVE
	//	 populateMAP.put(sq.next(), voResultEntry.getRequisitionDNo());
		 populateMAP.put(sq.next(), InvestigationConfig.UPDATION_TYPE);
		 populateMAP.put(sq.next(), InvestigationConfig.STATUS_CODE);
		 populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	//	 populateMAP.put(sq.next(), voResultEntry.getRequisitionNo());
	 //       populateMAP.put(sq.next(), voResultEntry.getPatCRNo());
	        populateMAP.put(sq.next(), voResultEntry.getTestCode());
	        populateMAP.put(sq.next(), voResultEntry.getLabCode());
	//        populateMAP.put(sq.next(), voResultEntry.getSampleNo()==null?"":voResultEntry.getSampleNo());
	//        populateMAP.put(sq.next(), voResultEntry.getPatAge()==null?"":voResultEntry.getPatAge());
	//        populateMAP.put(sq.next(), voResultEntry.getPatGender());
	//        
	//        populateMAP.put(sq.next(), voResultEntry.getTestParaMeterCode()==null?"":voResultEntry.getTestParaMeterCode()); 
	//        populateMAP.put(sq.next(), voResultEntry.getReportAvailableAfter());	        
	        
    /*    populateMAP.put(sq.next(), voResultEntry.getResultEntryValue()==null?"":voResultEntry.getResultEntryValue());
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
*/

		
		
    	
        	
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



public List getLabBasedMachine(testAvailabilityVO invtestAvailabilityVO,UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List labBasedMachineCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.LABTEST_MACHINE.HIVT_LABTEST_MST.MACHINE.RESULT.ENTRY";
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();
	

	populateMap.put(sq.next(),_UserVO.getHospitalCode());
	populateMap.put(sq.next(),invtestAvailabilityVO.getLabCode());

	
	 
	
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
			labBasedMachineCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
	return labBasedMachineCombo;
}
	


public void updateResultDtl(testAvailabilityVO voResultEntry, UserVO _UserVO) {

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey ="UPDATE.TEST_AVAIALABILITY.HIVT_LABORATORY_TEST_MST";
	
	
	try {
		
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	

	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
						+ e);
	}

	try {	 
		
		populateMAP.put(sq.next(),voResultEntry.getIsAvailable());
		
		
		
		if(voResultEntry.getIsAvailable().equals("0"))
		{
			
			populateMAP.put(sq.next(), voResultEntry.getFromDate());
			populateMAP.put(sq.next(), voResultEntry.getToDate());
					
		}
		else
		{
			
			populateMAP.put(sq.next(), null);
			populateMAP.put(sq.next(), null);
			
			
		}
		
		populateMAP.put(sq.next(),voResultEntry.getRemarks());
		populateMAP.put(sq.next(), _UserVO.getSeatId());

		
		if(voResultEntry.getIsAvailable().equals("0"))
		{
			
			populateMAP.put(sq.next(),"1");
					
		}
		else
		{
			
			populateMAP.put(sq.next(),"0");
			
			
		}
		
		
		
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		populateMAP.put(sq.next(), voResultEntry.getLabCode());
		populateMAP.put(sq.next(), voResultEntry.getTestCode());
		
                	
	} catch (Exception e) {
		throw new HisApplicationExecutionException(
				"IcdGroupMasterDAO.populateMAP::" + e);
	}
	try {

		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
				.getConnection(), query, populateMAP);
	} catch (Exception e) {
		System.out.println(e.getMessage());
		throw new HisDataAccessException("Exception While insertion in test availability process Table");
	}

}






//fetch para count
public String getParaCount(String labTestString,UserVO userVO)
{
	String paraCount=""; 
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.PARA.COUNT.HMIT_SAMPLE_DTL";
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
		 
		populateMap.put(sq.next(), labTestString.split("#")[0]);
		populateMap.put(sq.next(), labTestString.split("#")[1]);
		populateMap.put(sq.next(), labTestString.split("#")[2]);
		populateMap.put(sq.next(), labTestString.split("#")[3]);
		populateMap.put(sq.next(), userVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
		
		if (!rs.next())
		{
			//throw new HisRecordNotFoundException("");
			paraCount="";
		}
		else
		{
			paraCount=rs.getString(1);
			
		}
		
		
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }
	return paraCount;
}


public List<TestMandRefMasterVO> getRefRangeValues(UserVO uservo) {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.hivt_test_mand_ref_val_mst";
	JDBCTransactionContext tx = new JDBCTransactionContext();
	tx.begin();
	Connection conn = tx.getConnection();
	List<TestMandRefMasterVO> objList = new ArrayList<TestMandRefMasterVO>();

	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), uservo.getHospitalCode());
		
		
		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		rs.beforeFirst();
		while (rs.next()) {
			TestMandRefMasterVO objTestMandRefVo = new TestMandRefMasterVO();
			HelperMethods.populateVOfrmRS(objTestMandRefVo, rs);
			objList.add(objTestMandRefVo);
		}
	} catch (Exception e) {
		e.printStackTrace();
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException();
		} else if (e.getClass() == HisDataAccessException.class) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		} else if (e.getClass() == HisApplicationExecutionException.class) {
			throw new HisApplicationExecutionException(
					"DailyPatientDAO.populateMAP::" + e);
		} else
			throw new HisDataAccessException("HisDataAccessException:: "
					+ e);
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// tx.close();
	}
	return objList;
}



public List<InvCriteriaCodeVO> getCriteriaCodeValues() {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.CRITERIA_CODE";
	JDBCTransactionContext tx = new JDBCTransactionContext();
	tx.begin();
	Connection conn = tx.getConnection();
	List<InvCriteriaCodeVO> objList = new ArrayList<InvCriteriaCodeVO>();

	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();

		rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
		rs.beforeFirst();
		while (rs.next()) {
			InvCriteriaCodeVO objTestMandRefVo = new InvCriteriaCodeVO();
			HelperMethods.populateVOfrmRS(objTestMandRefVo, rs);
			objList.add(objTestMandRefVo);
		}
	} catch (Exception e) {
		e.printStackTrace();
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException();
		} else if (e.getClass() == HisDataAccessException.class) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		} else if (e.getClass() == HisApplicationExecutionException.class) {
			throw new HisApplicationExecutionException(
					"DailyPatientDAO.populateMAP::" + e);
		} else
			throw new HisDataAccessException("HisDataAccessException:: "
					+ e);
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// tx.close();
	}
	return objList;
}
/*
public  Inv_RequisitionRaisingPatientVO getPatGenderAge(String crNo,UserVO _UserVO)
{
	String query = "";		
	Map populateMAP = new HashMap();
	ResultSet rs = null;
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey = "SELECT.PATIENT_GENDER_AGE.HRGT_PATIENT_DTL";
	Sequence sq = new Sequence();

	Inv_RequisitionRaisingPatientVO invReqRaisingVO;

	populateMAP.put(sq.next(), crNo);
	
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
		invReqRaisingVO=new Inv_RequisitionRaisingPatientVO();
		if(!rs.next())
		{
		}
		else HelperMethods.populateVOfrmRS(invReqRaisingVO,rs);
		
	}
	catch (HisRecordNotFoundException e)
	{

		throw new HisRecordNotFoundException(e.getMessage());
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("" + e);
	}
	return invReqRaisingVO;
}
	*/
}

