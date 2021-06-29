package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.LabTestGlobalMstVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

public class LabTestLocalMstDAO extends DataAccessObject {

	public LabTestLocalMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
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
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
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

	public List getTestBylabCode(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_NAME.Lab_Test_Local_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),labTestGlobalMstVO.getLabCode() );
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(),labTestGlobalMstVO.getLabCode() );
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
				//throw new HisRecordNotFoundException();
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
	
	public void SaveLabTest(LabTestGlobalMstVO labTestGlobalMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "ADD.Lab_Test_Local_mst";
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
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getFooterText());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsFilmReq());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConsent());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestMethod());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsConfidential());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestPrintingType());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPrintingTemplateId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getReportAvailableAfter());	
			populateMAP.put(sq.next(),labTestGlobalMstVO.getUserTestCode());	
			populateMAP.put(sq.next(),labTestGlobalMstVO.getDeskProperties());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getMachineId());
            populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPat());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionColl());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPost());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionTech());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsPID());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsNablAccritedTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPreferenceOrder());

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
		String queryKey = "UPDATE.DUPLICATE_DATA.Lab_Test_Local_Mst";
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
			populateMAP.put(sq.next(),labTestGlobalMstVO.getUserTestCode());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getTestMethod());
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
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),labTestGlobalMstVO.getDeskProperties());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getMachineId());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPat());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionColl());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionPost());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getInstructionTech());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsPID());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getIsNablAccritedTest());
			populateMAP.put(sq.next(),labTestGlobalMstVO.getPreferenceOrder());
			
			//where clause
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
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

	public List fetchLabTestGlobalData(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="FETCH_GLOBAL_DATA.Lab_Test_Local_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				//throw new HisRecordNotFoundException();
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
	
	
	public List getModifyDataLocal(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="MODIFY.Lab_Test_Local_mst";
		
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

	
	public String checkDuplicateUserTestCode(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String record=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_USERTESTCODE.Lab_Test_Local_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),LabTestGlobalMstVO.getUserTestCode());
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					record=rs.getString(1);
					
				}
				
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
		return record;
	}
	
	public String checkDuplicateUserTestCodeOnModify(LabTestGlobalMstVO LabTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String record=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_USERTESTCODE_ON_MODIFY.Lab_Test_Local_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),LabTestGlobalMstVO.getUserTestCode());
		populateMap.put(sq.next(),LabTestGlobalMstVO.getLabCode());
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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					record=rs.getString(1);
					
				}
				
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
		return record;
	}
	
	
	public List getMachineLocal(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMachine=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MACHINE_NAME_GLOBAL";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(),labTestGlobalMstVO.getLabCode());


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
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstMachine=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstMachine;
	}
	
}
