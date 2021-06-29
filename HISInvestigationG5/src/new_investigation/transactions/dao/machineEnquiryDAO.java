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
//import hisglobal.vo.AntibodiesMstVO;
//import hisglobal.vo.BloodBagInvestigationInGroupVO;
//import hisglobal.vo.BloodBagIssueVO;
//import hisglobal.vo.BloodComponentSeparationMstVO;
/*import hisglobal.vo.CrossMatchingVO;
import hisglobal.vo.DailyBloodUnitStockVO;
import hisglobal.vo.DonationApheresisMstVO;*/
import new_investigation.vo.machineEnquiryVO;


public class machineEnquiryDAO extends DataAccessObject
{
	public machineEnquiryDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	
	
	
	
	public List<machineEnquiryVO> getPatientmachineEnquiry(machineEnquiryVO invmachineEnquiryVO, UserVO _UserVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey_3 = "SELECT.MACHINE_ENQUIRY_DETAILS.HMIT_RESULT_DTL_COMPLETED";
		String queryKey_1 = "SELECT.MACHINE_ENQUIRY_DETAILS.HMIT_RESULT_DTL_IN_PROCESS";
		String queryKey_2 = "SELECT.MACHINE_ENQUIRY_DETAILS.HMIT_RESULT_DTL_PARTIAL_COMPLETE";
		String queryKey_0 = "SELECT.MACHINE_ENQUIRY_DETAILS.HMIT_RESULT_DTL_NOT_FOUND";
		String queryKey_all = "SELECT.MACHINE_ENQUIRY_DETAILS.HMIT_RESULT_DTL_ALL";
		
		String reqDtlValue="";
	
		try
		{
			if(invmachineEnquiryVO.getTestStatus().equals("3"))
				query = HelperMethodsDAO.getQuery(filename, queryKey_3);
			else if(invmachineEnquiryVO.getTestStatus().equals("1"))
				query = HelperMethodsDAO.getQuery(filename, queryKey_1);
			else if(invmachineEnquiryVO.getTestStatus().equals("2"))
				query = HelperMethodsDAO.getQuery(filename, queryKey_2);
			else if(invmachineEnquiryVO.getTestStatus().equals("0"))
				query = HelperMethodsDAO.getQuery(filename, queryKey_0);
			else
				query = HelperMethodsDAO.getQuery(filename, queryKey_all);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			
			
			if(invmachineEnquiryVO.getTestStatus().equals("3"))
			{
			
			populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineLabSampleNo());
			populateMAP.put(sq.next(), invmachineEnquiryVO.getFromDate());
			populateMAP.put(sq.next(), invmachineEnquiryVO.getToDate());
					
			}
			
			else if(invmachineEnquiryVO.getTestStatus().equals("1"))
			{
				
				populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineLabSampleNo());	
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineCode());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getFromDate());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getToDate());
				
			}
			
			else if(invmachineEnquiryVO.getTestStatus().equals("2"))
			{
				
				populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineLabSampleNo());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getFromDate());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getToDate());
								
			}
			
			else if(invmachineEnquiryVO.getTestStatus().equals("0"))
			{
				populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineCode());
				
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getMachineLabSampleNo());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getFromDate());
				populateMAP.put(sq.next(), invmachineEnquiryVO.getToDate());
				
			}

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		List<machineEnquiryVO> lstInvmachineEnquiryVO = new ArrayList<machineEnquiryVO>();
		List<machineEnquiryVO> finalLstInvmachineEnquiryVO = new ArrayList<machineEnquiryVO>();
		ValueObject[] valueObjects = null;
		query+=reqDtlValue;
		try
		{
		
			
		
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(machineEnquiryVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstInvmachineEnquiryVO.add((machineEnquiryVO)valueObjects[i]);


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
		return lstInvmachineEnquiryVO;
	}	
	



public List getMachineComboForEnquiry(machineEnquiryVO invmachineEnquiryVO,UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List MachineCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.MACHINE_MST.MACHINE_RESULT_ENQUIRY";
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();

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
			MachineCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
	return MachineCombo;
}
	



public List getMachineBasedSampleNo(machineEnquiryVO invmachineEnquiryVO,UserVO _UserVO)
{
	String query="";
	Map populateMap= new HashMap();
	ResultSet rs=null;
	List labBasedMachineCombo=new ArrayList();
	String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
	String queryKey="SELECT.SAMPLE_NO.HMIT_SAMPLE_NO";
	Sequence sq= new Sequence();
	Connection conn=super.getTransactionContext().getConnection();

	populateMap.put(sq.next(),invmachineEnquiryVO.getTestStatus());
	populateMap.put(sq.next(),invmachineEnquiryVO.getMachineCode());
	populateMap.put(sq.next(),_UserVO.getHospitalCode());
	populateMap.put(sq.next(),invmachineEnquiryVO.getFromDate());
	populateMap.put(sq.next(),invmachineEnquiryVO.getToDate());
	
	
	
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
	



	
}

