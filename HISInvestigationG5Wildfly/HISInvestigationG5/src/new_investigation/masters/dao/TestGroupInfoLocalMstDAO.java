package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;
import new_investigation.vo.LabCannedMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;

public class TestGroupInfoLocalMstDAO extends DataAccessObject implements TestGroupInfoLocalMstDAOi
{

	public TestGroupInfoLocalMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}




	public void createTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_TEST_GROUP_INFO_MST_LOCAL";
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
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getLabCode());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getIsDynamicTemplate());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getIsDynamicResult());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getPrintingTemplateCode());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getTestSeqNo());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getUserGroupCode());
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



	public void fetchCheckListTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), testgroupinfolocal_VO.getTestCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), testgroupinfolocal_VO.getLabCode());
		populateMap.put(sq.next(), testgroupinfolocal_VO.getGroupCode());

		


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
					HelperMethods.populateVOfrmRS(testgroupinfolocal_VO ,rs);
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
	}


	// for Updating The old Record
	public void deleteTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="DELETE_VALID.HIVT_TEST_GROUP_INFO_MST";
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
			//populateMAP.put(sq.next(), _UserVO.getSeatId());



		
			populateMAP.put(sq.next(), _UserVO.getSeatId());

			populateMAP.put(sq.next(), testgroupinfolocal_VO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getGroupCode());


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




	public String checkPrimaryKeyTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKPRIMARYKEY.HIVT_TEST_GROUP_INFO_MST";
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
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getLabCode());   
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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


	/// check for duplicate Container Name on Modify
	public void updateValidTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE_VALID.HIVT_TEST_GROUP_INFO_MST_LOCAL";
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
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE); 
			populateMAP.put(sq.next(), _UserVO.getSeatId());

			populateMAP.put(sq.next(), testgroupinfolocal_VO.getTestSeqNo());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getUserGroupCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getTestCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
			
			
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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	public List getgroupCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstgroupName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.GROUP_COMBO.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), _UserVO.getHospitalCode());


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
				lstgroupName=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return lstgroupName;
	}
	// For lab combo

	public List getlabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstlabName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());


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
				lstlabName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstlabName;
	}

	

	
	
	public List gettestComboLeft(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstareaName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_TEST_COMBO.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), testgroupinfolocal_VO.getLabCode());
		
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		populateMap.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
		
		populateMap.put(sq.next(), testgroupinfolocal_VO.getLabCode());
		
		if(testgroupinfolocal_VO.getCheckLocal().equals("0"))
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		else
			populateMap.put(sq.next(), _UserVO.getHospitalCode());


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
				lstareaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstareaName;
	}
	
	
	public List getselectedtestComboRight(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO,List<TestGroupInfoMasterVO> lstTestSeqNo)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstareaName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_SELECTED_TEST_COMBO.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
		
		populateMap.put(sq.next(), testgroupinfolocal_VO.getLabCode());
		populateMap.put(sq.next(), _UserVO.getHospitalCode());

		populateMap.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
		populateMap.put(sq.next(), testgroupinfolocal_VO.getLabCode());
		
		
		if(testgroupinfolocal_VO.getCheckLocal().equals("1"))
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
		else
			populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);


		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException();
				lstareaName=null;
			}
			else
			{
				rs.beforeFirst();
				lstareaName=HelperMethodsDAO.getAlOfEntryObjects(rs);
				
				rs.beforeFirst();
				
				valueObjects = HelperMethods.populateVOfrmRS(TestGroupInfoMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					lstTestSeqNo.add((TestGroupInfoMasterVO)valueObjects[i]);
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
		return lstareaName;
	}
	
	//For Printing Template Name

	public List getPrintingTemplateCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstPrintingTemplateName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.PRINTING_TEMPLATE_COMBO.HIVT_TEST_GROUP_INFO_MST";

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
				lstPrintingTemplateName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstPrintingTemplateName;
	}
	
	public void fetchParameter(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.PRINTING_TEMPLATE.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		if(testgroupinfolocal_VO.getCheckLocal()==null)
			testgroupinfolocal_VO.setCheckLocal("0");

		//populateMap.put(sq.next(), testgroupinfo_VO.getTestCode());
		
		if(testgroupinfolocal_VO.getCheckLocal().equals("0"))
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		else
			populateMap.put(sq.next(), _UserVO.getHospitalCode() );
		populateMap.put(sq.next(), testgroupinfolocal_VO.getLabCode());
		populateMap.put(sq.next(), testgroupinfolocal_VO.getGroupCode());


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
					HelperMethods.populateVOfrmRS(testgroupinfolocal_VO ,rs);
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
	}

	public void modifyTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="";
		
		if(testgroupinfo_VO.getGlobalTemplate()==null)
			testgroupinfo_VO.setGlobalTemplate("1");
		
		if(testgroupinfo_VO.getGlobalTemplate().equals("1"))	//using same global template only, no new template id generated(using global one) 
			queryKey ="UPDATE_TEMPLATE.HIVT_TEST_GROUP_INFO_MST_LOCAL";
		else 
			queryKey ="NEW_ENTRY_UPDATE_TEMPLATE.HIVT_TEST_GROUP_INFO_MST_LOCAL";	//generate new template id in printing and use that one
			
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filenRame)OR getting query out of property file" + e);
		}
		try
		{
			
			if(testgroupinfo_VO.getGlobalTemplate().equals("1"))
			{
		//	populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE); 
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testgroupinfo_VO.getIsDynamicTemplate());
			populateMAP.put(sq.next(), testgroupinfo_VO.getIsDynamicResult());
			populateMAP.put(sq.next(), testgroupinfo_VO.getPrintingTemplateCode());//id used same as global
			populateMAP.put(sq.next(), testgroupinfo_VO.getUserGroupCode());
			//where
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getGroupCode());
			
			}
			else
			{
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE); 
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testgroupinfo_VO.getIsDynamicTemplate());
			populateMAP.put(sq.next(), testgroupinfo_VO.getIsDynamicResult());
			populateMAP.put(sq.next(), testgroupinfo_VO.getUserGroupCode());
			//new template id generated from printing template
			
			//where
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getGroupCode());
			
			}
			
			
			
			

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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	
	
	public void modifyPrintingTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE_PRINTING_TEMPLATE.HIVT_PRINTING_TEMPLATE";
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
			
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getGroupCode());
			
			//where
			populateMAP.put(sq.next(), testgroupinfo_VO.getPrintingTemplateCode());
			

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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	

	public List getPrintingTemplateMappedCombo(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstPrintingTemplateName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MAPPED.PRINTING_TEMPLATE_COMBO.HIVT_TEST_GROUP_INFO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMap.put(sq.next(), testgroupinfo_VO.getLabCode());
		populateMap.put(sq.next(), testgroupinfo_VO.getGroupCode());

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
				lstPrintingTemplateName=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return lstPrintingTemplateName;
	}
	

	
	public void insertPrintingTemplateValueLocal(TestGroupInfoMasterVO testgroupinfo_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="INSERT_PRINTING_TEMPLATE_LOCAL.HIVT_PRINTING_TEMPLATE_LOCAL";
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
			
			
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), testgroupinfo_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getGroupCode());
			populateMAP.put(sq.next(),  _UserVO.getStrHospitalMstVo().getHospitalName()+" - ");
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), testgroupinfo_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getGroupCode());
		
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getLabCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getGroupCode());
			populateMAP.put(sq.next(), testgroupinfo_VO.getUserGroupCode());
		
			

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
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	//check dupliace user group code
	public String checkUserGroupCodeTestGroupInfoLocal(TestGroupInfoMasterVO testgroupinfolocal_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKUSERGROUPCODE.HIVT_TEST_GROUP_INFO_MST";
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
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getLabCode());   
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getGroupCode());
			populateMAP.put(sq.next(), testgroupinfolocal_VO.getUserGroupCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
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

	
	
}
