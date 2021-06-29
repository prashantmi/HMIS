/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST GLOBAL MASTER
 ## Purpose						        : 
 ## Date of Creation					:19-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.LabTestGlobalMstVO;
import hisglobal.utility.HelperMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

public class LabTestGlobalMStDAO extends DataAccessObject {

	public LabTestGlobalMStDAO(TransactionContext _tx) 
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	public List getTestMethodCombo(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_METHOD_COMBO";
		
		Sequence sq= new Sequence();
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return testMethod;
	}

	
	
	public void SaveLabTest(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "ADD.Lab_Test_Global_mst";
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
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLabCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestDays());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getNoOfTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsTestAvailable());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsAppointment());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMultisession());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMandatoryReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsRequisitionFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSampleFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getGenderBound());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getAgeBound());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLowAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHighAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSecurePrinting());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHeaderText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsGrossingReq());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),labTestGlobalMstVO.getFooterText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsFilmReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConsent());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestMethod());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConfidential());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestPrintingType());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getReportAvailableAfter());	
			populateMAP.put(sq.next(),labTestGlobalMstVO.getDeskProperties());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getMachineId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPat());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionColl());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPost());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionTech());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsNablAccritedTest());
			
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintedWith());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getRequisitionForm());
			
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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
	
	public void saveModifyLabTest(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "MODIFY.SAVE.Lab_Test_Global_mst";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	populateMAP.put(sq.next(),labTestGlobalMstVO.getTestMethod());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestDays());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getNoOfTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsTestAvailable());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsAppointment());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMultisession());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMandatoryReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsRequisitionFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSampleFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getGenderBound());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getAgeBound());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLowAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHighAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSecurePrinting());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHeaderText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsGrossingReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getFooterText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsFilmReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConsent());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConfidential());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestPrintingType());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getReportAvailableAfter());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getDeskProperties());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getMachineId());
            populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPat());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionColl());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPost());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionTech());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsNablAccritedTest());
			
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintedWith());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getRequisitionForm());
			
			
			
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLabCode());
			
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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
	public void saveModifyLabTestLocal(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "MODIFY.SAVE.Lab_Test_Local_mst";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	populateMAP.put(sq.next(),labTestGlobalMstVO.getTestMethod());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestDays());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getNoOfTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsTestAvailable());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsAppointment());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMultisession());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMandatoryReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsRequisitionFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSampleFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getGenderBound());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getAgeBound());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLowAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHighAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSecurePrinting());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHeaderText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsGrossingReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getFooterText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsFilmReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConsent());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConfidential());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestPrintingType());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getReportAvailableAfter());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getUserTestCode().trim());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getDeskProperties());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getMachineId());
            populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPat());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionColl());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPost());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionTech());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsNablAccritedTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPreferenceOrder());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsPID());                                      //harshita
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLabCode());
			
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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
	
	public List getModifyData(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="MODIFY.Lab_Test_Global_mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),LabTestGlobalMstVO.getLabCode());
		populateMap.put(sq.next(),LabTestGlobalMstVO.getTestCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(LabTestGlobalMstVO, rs);
					
				}
				
				
				
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return testMethod;
	}
	public List getModifyDataLocal(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="MODIFY.Lab_Test_Global_mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),LabTestGlobalMstVO.getLabCode());
		populateMap.put(sq.next(),LabTestGlobalMstVO.getTestCode());
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
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(LabTestGlobalMstVO, rs);
					
				}
				
				
				
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return testMethod;
	}
	public List getTestBylabCode(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_NAME_GLOBAL_MST.0";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestGlobalMstVO.getLabCode() );
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	public List getLabLocalCombo(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Lab_Tset_Global_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	public List getTestMethodLocalCombo(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_METHOD_COMBO";
		
		Sequence sq= new Sequence();
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return testMethod;
	}
	public List getLabCombo(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Lab_Tset_Global_Mst.1";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),LabTestGlobalMstVO.getLabCode());
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
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(LabTestGlobalMstVO, rs);
					
				}
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	public List getLabComboLocal(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.Lab_Tset_Global_Mst.1";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),LabTestGlobalMstVO.getLabCode());
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
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(LabTestGlobalMstVO, rs);
					
				}
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	public List getTestName(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="Select.Test.Lab_Test_Global_Mst.1";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),LabTestGlobalMstVO.getTestCode());
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
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(LabTestGlobalMstVO, rs);
					
				}
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}
	
	public List checkDuplicateData(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_DATA.Lab_Test_GLobal_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_DELETED);
		populateMap.put(sq.next(),LabTestGlobalMstVO.getLabCode());
		populateMap.put(sq.next(),LabTestGlobalMstVO.getTestCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(LabTestGlobalMstVO, rs);
					
				}
				departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return departcombo;
	}

	public void UpdateLabTest(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "UPDATE.DUPLICATE_DATA.Lab_Test_GLobal_Mst";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	populateMAP.put(sq.next(),labTestGlobalMstVO.getTestMethod());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestDays());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getNoOfTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsTestAvailable());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsAppointment());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMultisession());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsMandatoryReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsRequisitionFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSampleFormNeeded());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getGenderBound());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getAgeBound());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLowAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHighAgeRange());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsSecurePrinting());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getHeaderText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsGrossingReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getFooterText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsFilmReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConsent());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConfidential());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestPrintingType());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getReportAvailableAfter());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPat());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionColl());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPost());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionTech());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsNablAccritedTest());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestGlobalMstVO.getDeskProperties());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getMachineId());

			
			//where clause
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getLabCode());
			
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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
}
