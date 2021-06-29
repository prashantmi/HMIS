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
import new_investigation.vo.LabTestGlobalMstVO;
import new_investigation.vo.InvParameterMasterVO;
//import hisglobal.vo.LabMasterVO;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.MandatoryMasterVO;
import new_investigation.vo.UOMMasterVO;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.TestNewMasterVO;
import hisglobal.vo.LabTestMasterVO;
 

public class InvSampleMstDAO extends DataAccessObject implements InvSampleMstDAOi
{

	public InvSampleMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	 
	//START SAMPLE MASTER //
		public InvSampleMasterVO[] getChecklistDetail(UserVO userVO)
		{
			InvSampleMasterVO[] InvSampleMasterVOs=null;
			ValueObject[] vo=null;
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT_CHECKLIST.HIVT_SAMPLE_MST";
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
				populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
				 
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("BldCheckListMstDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("No Checklist Found");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(InvSampleMasterVO.class, rs);
					InvSampleMasterVOs = new InvSampleMasterVO[vo.length];
					for (int i = 0; i < vo.length; i++) {
						InvSampleMasterVOs[i] = (InvSampleMasterVO) vo[i];
					}
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
			return InvSampleMasterVOs;
		}



		public void create(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = "INSERT.HIVT_SAMPLE_MST";
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
			 
				 
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleName());
				 
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleSName());
				populateMAP.put(sq.next(), bCheckListMasterVO.getLoincSystem());
				
				
				populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
				 
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), bCheckListMasterVO.getRemarks());
				 
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
		



		public void fetchCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.HIVT_SAMPLE_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), bCheckListMasterVO.getSampleCode());
			
		 
			 
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
						HelperMethods.populateVOfrmRS(bCheckListMasterVO ,rs);
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
		

	 
		public void updateCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="UPDATE.HIVT_SAMPLE_MST";
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
			 
				 
			 
				
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleName());
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleSName());
				populateMAP.put(sq.next(), bCheckListMasterVO.getLoincSystem());
				 
				
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), bCheckListMasterVO.getRemarks());
				
			 
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleCode());
				
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
		
		
		public void savemodifyCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = "INSERT_MODIFY.HIVT_SAMPLE_MST";

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
				 	
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleName());
				 
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleSName());
				populateMAP.put(sq.next(), bCheckListMasterVO.getLoincSystem());
				
				populateMAP.put(sq.next(), bCheckListMasterVO.getIsActive());
				 
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), bCheckListMasterVO.getRemarks());
				
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


	 
		public String checkDuplicateCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="CHECKDUPLICATE.HIVT_SAMPLE_MST";
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
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleName().trim());
				 
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


		public String checkDuplicateCheckListModify(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO)
		{
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_SAMPLE_MST";
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
			 
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleName().trim());
	            
				populateMAP.put(sq.next(), bCheckListMasterVO.getSampleCode());
				 
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

	////////////////////////////////////START MANDATORY COMBO MASTER///////////////////////////////////////

	public MandatoryComboMasterVO[] getMandatoryComboDetail(UserVO userVO)
	{
		MandatoryComboMasterVO[] MandatoryComboMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_CHECKLIST.HIVT_MAND_COMBO_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorycombo_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Checklist Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(InvSampleMasterVO.class, rs);
				MandatoryComboMasterVOs = new MandatoryComboMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					MandatoryComboMasterVOs[i] = (MandatoryComboMasterVO) vo[i];
				}
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
		return MandatoryComboMasterVOs;
	}




	public void createMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_MAND_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

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

			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryCode());
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryValue()); 
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorycombo_DAO.populateMAP::" + e);
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



	public void fetchCheckListMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_MAND_COMBO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), mandatorycombo_VO.getMandatoryCode());
		populateMap.put(sq.next(), mandatorycombo_VO.getMandatorySeq());


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
					HelperMethods.populateVOfrmRS(mandatorycombo_VO ,rs);
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

	//to display data according to the mandatory code selected

	public List<MandatoryComboMasterVO> fetchdisplaydataMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		List<MandatoryComboMasterVO> mandatorycombo_listVO = new ArrayList<MandatoryComboMasterVO>();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_DISPLAYDATA.HIVT_MAND_COMBO_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), mandatorycombo_VO.getMandatoryCode());

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
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(MandatoryComboMasterVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
				{
					mandatorycombo_listVO.add((MandatoryComboMasterVO)valueObjects[i]);
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
		return mandatorycombo_listVO;
	}

	// for Updating The old Record
	public void updateMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		String query = "";
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_MAND_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

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
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryValue());
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatorySeq());
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryCode());

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


	/// check for duplicate MANDATORY VALUE
	public String checkDuplicateMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_MAND_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();			


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
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryCode());
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryValue());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);    

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


	/// check for duplicate MANDATORY VALUE on Modify
	public String checkDuplicateModifyMandatoryCombo(MandatoryComboMasterVO mandatorycombo_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_MAND_COMBO_MST";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

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
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryCode());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);         
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatoryValue().trim());
			populateMAP.put(sq.next(), mandatorycombo_VO.getMandatorySeq());

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




	// For mandatory name combo

	public List getMandCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MANDATORY_COMBO.HIVT_MAND_COMBO_MST";

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
				lstMandName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstMandName;
	}

	/////////////////////////////////////END MANDATORRY COMBO MASTER/////////////////////////////////

	////////////////////////////////////START MANDATORY MASTER////////////////////////////////////////

	public MandatoryMasterVO[] getMandatoryDetail(UserVO userVO)
	{
		MandatoryMasterVO[] MandatoryMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_CHECKLIST.HIVT_MANDATORY_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorymaster_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Checklist Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(InvSampleMasterVO.class, rs);
				MandatoryMasterVOs = new MandatoryMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					MandatoryMasterVOs[i] = (MandatoryMasterVO) vo[i];
				}
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
		return MandatoryMasterVOs;
	}




	public void createMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_MANDATORY_MST";

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


			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryName());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryType());
			populateMAP.put(sq.next(), mandatorymaster_VO.getRemarks());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorymaster_DAO.populateMAP::" + e);
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



	public void fetchCheckListMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_MANDATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), mandatorymaster_VO.getMandatoryCode());

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
					HelperMethods.populateVOfrmRS(mandatorymaster_VO ,rs);
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
	public void updateMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_MANDATORY_MST";
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
			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryName());
			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryType());
			populateMAP.put(sq.next(), mandatorymaster_VO.getRemarks());
			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorymaster_DAO.populateMAP::" + e);
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

	/// check for duplicate MANDATORY NAME
	public String checkDuplicateMandatory(MandatoryMasterVO mandatorymaster_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_MANDATORY_MST";
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
			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryName().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);         

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorymaster_DAO.populateMAP::" + e);
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


	/// check for duplicate MANDATORY Name on Modify
	public String checkDuplicateModifyMandatory(MandatoryMasterVO mandatorymaster_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_MANDATORY_MST";
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
			populateMAP.put(sq.next(), mandatorymaster_VO.getMandatoryName().trim());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("mandatorymaster_DAO.populateMAP::" + e);
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

	/////////////////////////////////////////////end mandatory/////////////////////////////////////////////




	//START UOM MASTER  Added By Pathan Basha //
	public UOMMasterVO[] getUOMDetail(UserVO userVO)
	{
		UOMMasterVO[] UOMMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_UOM.HIVT_UOM_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			//populateMap.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldCheckListMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No UOM Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(UOMMasterVO.class, rs);
				UOMMasterVOs = new UOMMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					UOMMasterVOs[i] = (UOMMasterVO) vo[i];
				}
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
		return UOMMasterVOs;
	}




	public void createUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_UOM_MST";
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
		 
			 
				populateMAP.put(sq.next(), uOMMasterVO.getLoincProperty());
			 
				populateMAP.put(sq.next(), uOMMasterVO.getUomName());
			 
			
			
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			 
			 
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
	

	public void fetchUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_UOM_MST";
	
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		
		 
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		 
		populateMap.put(sq.next(), uOMMasterVO.getUomCode());
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
					HelperMethods.populateVOfrmRS(uOMMasterVO ,rs);
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
	public void updateUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_UOM_MST";
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
			 
			 
		 
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _UserVO.getSeatId());
				populateMAP.put(sq.next(), uOMMasterVO.getLoincProperty());
				populateMAP.put(sq.next(), uOMMasterVO.getUomName());
		populateMAP.put(sq.next(), uOMMasterVO.getUomCode());
			
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


	public void savemodifyUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_MODIFY.HIVT_UOM_MST";

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
			 	
			populateMAP.put(sq.next(), uOMMasterVO.getLoincProperty());
			
		 	populateMAP.put(sq.next(), uOMMasterVO.getUomName());
			 
			
		 	populateMAP.put(sq.next(), uOMMasterVO.getIsActive());
			 	 
		 	 
			 	populateMAP.put(sq.next(), _UserVO.getSeatId());
			
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


	/// check for duplicate ParameterName
	public String checkDuplicateUOM(UOMMasterVO uOMMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_UOM_MST";
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
			populateMAP.put(sq.next(), uOMMasterVO.getUomName().trim());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);         
			 
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

	/// check for duplicate Parameter Name on Modify
	public String checkDuplicateUOMModify(UOMMasterVO uOMMasterVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_UOM_MST";
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
		 
		populateMAP.put(sq.next(), uOMMasterVO.getUomName().trim());
		populateMAP.put(sq.next(), uOMMasterVO.getUomCode());
		
		
		 
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


	//END UOM MASTER  Added By Pathan Basha //


		
		 
	//START LAB MASTER  Added By Pathan Basha //
	public LabMasterVO[] getLabDetail(UserVO userVO)
	{
		LabMasterVO[] LabMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_LAB.HIVT_LABORATORY_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldCheckListMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Lab Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(LabMasterVO.class, rs);
				LabMasterVOs = new LabMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					LabMasterVOs[i] = (LabMasterVO) vo[i];
				}
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
		return LabMasterVOs;
	}



	public void createLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_LABORATORY_MST";
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
			
	
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
			 
			 populateMAP.put(sq.next(), labMasterVO.getDepartment());
			  populateMAP.put(sq.next(), labMasterVO.getLaboratoryName());
			  populateMAP.put(sq.next(), labMasterVO.getLabShortSName());
				  populateMAP.put(sq.next(), labMasterVO.getLabType());
				  
			  populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
            
            
            
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
                 
        	populateMAP.put(sq.next(), labMasterVO.getHeadertext());
        	populateMAP.put(sq.next(), labMasterVO.getFooterText());
        	populateMAP.put(sq.next(), labMasterVO.getRemarks());
        	 
        	populateMAP.put(sq.next(), labMasterVO.getLabWorkingDays());
        	 
        	populateMAP.put(sq.next(), labMasterVO.getNumberofTests());
				populateMAP.put(sq.next(), labMasterVO.getSampleNumberConfig());
				populateMAP.put(sq.next(), labMasterVO.getLabNumberConfig());
				populateMAP.put(sq.next(), labMasterVO.getDisplayHeader());

			
			
		
		 
			 
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
	


	public void fetchLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LABORATORY_MST";
	
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
		 
		 
		populateMap.put(sq.next(), labMasterVO.getLabCode());
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
					HelperMethods.populateVOfrmRS(labMasterVO ,rs);
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
	public void updateLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_LABORATORY_MST";
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
			 
			 
			 
			
			
			 
			populateMAP.put(sq.next(), labMasterVO.getDepartment());
			populateMAP.put(sq.next(), labMasterVO.getLaboratoryName());
			populateMAP.put(sq.next(), labMasterVO.getLabShortSName());
			populateMAP.put(sq.next(), labMasterVO.getLabType());
			populateMAP.put(sq.next(), labMasterVO.getHeadertext());
			populateMAP.put(sq.next(), labMasterVO.getFooterText());
			
	populateMAP.put(sq.next(), _UserVO.getSeatId());
	populateMAP.put(sq.next(), labMasterVO.getRemarks());
	populateMAP.put(sq.next(), labMasterVO.getLabWorkingDays());
	populateMAP.put(sq.next(), labMasterVO.getNumberofTests());
	populateMAP.put(sq.next(), labMasterVO.getSampleNumberConfig());
	populateMAP.put(sq.next(), labMasterVO.getLabNumberConfig());
	populateMAP.put(sq.next(), labMasterVO.getDisplayHeader());


	 
	populateMAP.put(sq.next(), labMasterVO.getLabCode());
	populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);

			
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

	public void savemodifyLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_MODIFY.HIVT_LABORATORY_MST";

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
			 	
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
			 
            populateMAP.put(sq.next(), labMasterVO.getDeptCode());
			  populateMAP.put(sq.next(), labMasterVO.getLaboratoryName());
			  populateMAP.put(sq.next(), labMasterVO.getLabShortSName());
			  populateMAP.put(sq.next(), labMasterVO.getLabType());
			  
			  populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
            
            
            
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
                 
        	populateMAP.put(sq.next(), labMasterVO.getHeadertext());
        	populateMAP.put(sq.next(), labMasterVO.getFooterText());
        	populateMAP.put(sq.next(), labMasterVO.getRemarks());
        	 
        	populateMAP.put(sq.next(), labMasterVO.getLabWorkingDays());
        	populateMAP.put(sq.next(), labMasterVO.getSampleCStrtTime());
        	populateMAP.put(sq.next(), labMasterVO.getSampleCEndTime());
				populateMAP.put(sq.next(), labMasterVO.getNumberofTests());
			
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

	
	 /// check for duplicate ParameterName
	public String checkDuplicateLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_LABORATORY_MST";
		
		

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
			populateMAP.put(sq.next(), labMasterVO.getLaboratoryName().trim());
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
			 
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
	
	
	/// check for duplicate Parameter Name on Modify
	public String checkDuplicateLabModify(LabMasterVO labMasterVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_LABORATORY_MST";

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
		 
			populateMAP.put(sq.next(), labMasterVO.getLaboratoryName().trim());
			populateMAP.put(sq.next(), labMasterVO.getLabCode());
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);

			
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

	
	public List getLabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_LABORATORY_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);

		 
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
	
				//END LAB MASTER  Added By Pathan Basha //

	// For Loinic Combo

	
	public List getLoinicCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstLoinic=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LOINIC_COMBO.HIVT_SAMPLE_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		//populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
				lstLoinic=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstLoinic;
	}


	public List getLoinicCombouom(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstLoinicuom=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LOINIC_COMBO_UOM.HIVT_UOM_MST";
		
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
				lstLoinicuom=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstLoinicuom;
	}
	
	//START TEST MASTER //
	public TestNewMasterVO[] getTestDetail(UserVO userVO)
	{
		TestNewMasterVO[] TestNewMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_CHECKLIST.HIVT_TEST_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldCheckListMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Checklist Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(InvSampleMasterVO.class, rs);
				TestNewMasterVOs = new TestNewMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					TestNewMasterVOs[i] = (TestNewMasterVO) vo[i];
				}
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
		return TestNewMasterVOs;
	}


	
	
	public void createTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_TEST_MST";
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
			 			 
			 
			 
			
			populateMAP.put(sq.next(), testNewMasterVO.getTestName());
			populateMAP.put(sq.next(), testNewMasterVO.getLoincTiming());
			
			populateMAP.put(sq.next(), testNewMasterVO.getTestSName());
			
			populateMAP.put(sq.next(), testNewMasterVO.getTestDescription());
			
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), testNewMasterVO.getTestType());
			
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testNewMasterVO.getPrintedWith());
			populateMAP.put(sq.next(), testNewMasterVO.getresultEntryForm());
			populateMAP.put(sq.next(), testNewMasterVO.getPrintingTemplateCode());
			populateMAP.put(sq.next(), testNewMasterVO.getDepartmentPrintedWith());
			populateMAP.put(sq.next(), testNewMasterVO.getDepartmentResultEntryForm());
			populateMAP.put(sq.next(), testNewMasterVO.getDepartmentPrintingTemplateCode());
			
			populateMAP.put(sq.next(), testNewMasterVO.getReqMasterFormType());
			populateMAP.put(sq.next(), testNewMasterVO.getIsreportrequiredonseperatepage());
			populateMAP.put(sq.next(), testNewMasterVO.getIsreportupload());
			populateMAP.put(sq.next(), testNewMasterVO.getIsfrontpage());
			
			 
			 
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
	
	
	
	public void fetchTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_TEST_MST";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		 
		
		populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
		 
		populateMap.put(sq.next(), testNewMasterVO.getTestCode());
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
					HelperMethods.populateVOfrmRS(testNewMasterVO ,rs);
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
	public void updateTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_TEST_MST";
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
			 
			 
		 
			populateMAP.put(sq.next(), testNewMasterVO.getReqMasterFormType()); 
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), testNewMasterVO.getTestName());
			populateMAP.put(sq.next(), testNewMasterVO.getLoincTiming());
			populateMAP.put(sq.next(), testNewMasterVO.getTestSName());
			populateMAP.put(sq.next(), testNewMasterVO.getTestDescription());
			populateMAP.put(sq.next(), testNewMasterVO.getTestType());
			populateMAP.put(sq.next(), testNewMasterVO.getPrintedWith());
			populateMAP.put(sq.next(), testNewMasterVO.getresultEntryForm());
			populateMAP.put(sq.next(), testNewMasterVO.getPrintingTemplateCode());
			
			
			populateMAP.put(sq.next(), testNewMasterVO.getDepartmentPrintedWith());
			populateMAP.put(sq.next(), testNewMasterVO.getDepartmentResultEntryForm());
			populateMAP.put(sq.next(), testNewMasterVO.getDepartmentPrintingTemplateCode());
			populateMAP.put(sq.next(), testNewMasterVO.getIsreportrequiredonseperatepage());
			populateMAP.put(sq.next(), testNewMasterVO.getIsreportupload());
			populateMAP.put(sq.next(), testNewMasterVO.getIsfrontpage());
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
			
			
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

 
	
	 /// check for duplicate ParameterName
	public String checkDuplicateTest(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_TEST_MST";
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
			populateMAP.put(sq.next(), testNewMasterVO.getTestName().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);         
			 
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
	
	
	/// check for duplicate Parameter Name on Modify
	public String checkDuplicateTestModify(TestNewMasterVO testNewMasterVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_TEST_MST";
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
		 
			populateMAP.put(sq.next(), testNewMasterVO.getTestName().trim());
			
			populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
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
	
	public List getTestCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lsttest=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.TEST_COMBO.HIVT_TEST_MST";
		
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
				lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lsttest;
	}
	


	//////////////////////////////////////////////end test master/////////////////////////////////////////
	/////////////////////////////////////////parameter master////////////////////////////////////////


	public InvParameterMasterVO[] getChecklistDetail_parameter(UserVO userVO)
	{
		InvParameterMasterVO[] InvParameterMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_CHECKLIST.HIVT_PARAMETER_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Checklist Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(InvParameterMasterVO.class, rs);
				InvParameterMasterVOs = new InvParameterMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					InvParameterMasterVOs[i] = (InvParameterMasterVO) vo[i];
				}
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
		return InvParameterMasterVOs;
	}

	public void createParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_PARAMETER_MST";
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

			populateMAP.put(sq.next(), parametermaster_VO.getParameterName());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), parametermaster_VO.getRemarks());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
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

	public void fetchParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_PARAMETER_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();


		populateMap.put(sq.next(),parametermaster_VO.getParameterCode());

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
					HelperMethods.populateVOfrmRS(parametermaster_VO ,rs);
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

	public void updateParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_PARAMETER_MST";
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
			populateMAP.put(sq.next(), parametermaster_VO.getParameterName());
			populateMAP.put(sq.next(), parametermaster_VO.getRemarks());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);       
			populateMAP.put(sq.next(),parametermaster_VO.getParameterCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
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


	public String checkDuplicateParameter(InvParameterMasterVO parametermaster_VO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_PARAMETER_MST";
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
			populateMAP.put(sq.next(), parametermaster_VO.getParameterName().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);         

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

	public String checkDuplicateModifyParameter(InvParameterMasterVO parametermaster_VO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_PARAMETER_MST";
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
			populateMAP.put(sq.next(), parametermaster_VO.getParameterName().trim());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("parametermaster_DAO.populateMAP::" + e);
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

	///////////////////////////////////////////////parametermaster////////////////////////////////////////////


	//START LAB TEST MASTER  Added By Pathan Basha //
	public LabTestMasterVO[] getLabTestDetail(UserVO userVO)
	{
		LabTestMasterVO[] LabTestMasterVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_LAB.HIVT_LAB_MST";
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
			populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),InvestigationConfig.HOSPITAL_CODE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldCheckListMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Lab Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(LabTestMasterVO.class, rs);
				LabTestMasterVOs = new LabTestMasterVO[vo.length];
				for (int i = 0; i < vo.length; i++) {
					LabTestMasterVOs[i] = (LabTestMasterVO) vo[i];
				}
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
		return LabTestMasterVOs;
	}




	public void createLabTest(LabTestMasterVO labTestMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT.HIVT_LAB_MST";
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
			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);

			populateMAP.put(sq.next(), labTestMasterVO.getDepartment());
			populateMAP.put(sq.next(), labTestMasterVO.getLaboratoryName());
			populateMAP.put(sq.next(), labTestMasterVO.getLabShortSName());
			populateMAP.put(sq.next(), labTestMasterVO.getLabType());

			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);



			populateMAP.put(sq.next(), _UserVO.getSeatId());

			populateMAP.put(sq.next(), labTestMasterVO.getHeadertext());
			populateMAP.put(sq.next(), labTestMasterVO.getFooterText());
			populateMAP.put(sq.next(), labTestMasterVO.getRemarks());

			populateMAP.put(sq.next(), labTestMasterVO.getLabWorkingDays());
			 
			populateMAP.put(sq.next(), labTestMasterVO.getNumberofTests());
			populateMAP.put(sq.next(), labTestMasterVO.getSampleNumberConfig());




			 

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



	public void fetchLabTest(LabTestMasterVO labTestMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.HIVT_LAB_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);

		 
		populateMap.put(sq.next(), labTestMasterVO.getLabCode());
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
					HelperMethods.populateVOfrmRS(labTestMasterVO ,rs);
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
	public void updateLabTest(LabTestMasterVO labTestMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE.HIVT_LABORATORY_MST";
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






			populateMAP.put(sq.next(), labTestMasterVO.getDepartment());
			populateMAP.put(sq.next(), labTestMasterVO.getLaboratoryName());
			populateMAP.put(sq.next(), labTestMasterVO.getLabShortSName());
			populateMAP.put(sq.next(), labTestMasterVO.getLabType());
			populateMAP.put(sq.next(), labTestMasterVO.getHeadertext());
			populateMAP.put(sq.next(), labTestMasterVO.getFooterText());

			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), labTestMasterVO.getRemarks());
			populateMAP.put(sq.next(), labTestMasterVO.getLabWorkingDays());
			populateMAP.put(sq.next(), labTestMasterVO.getNumberofTests());

			 
			populateMAP.put(sq.next(), labTestMasterVO.getLabCode());

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

	public void savemodifyLabTest(LabTestMasterVO Test, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_MODIFY.HIVT_LAB_MST";

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

			populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);

			 

			populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);



			populateMAP.put(sq.next(), _UserVO.getSeatId());

			 

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


	/// check for duplicate ParameterName
	public String checkDuplicateLabTest(LabTestMasterVO labTestMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICATE.HIVT_LAB_MST";
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
			populateMAP.put(sq.next(), labTestMasterVO.getLaboratoryName().trim());
			 
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


	/// check for duplicate Parameter Name on Modify
	public String checkDuplicateLabTestModify(LabTestMasterVO labTestMasterVO,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_LAB_MST";
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


	public List getLabTestCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_LAB_MST";

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


	//END LAB TEST MASTER  Added By Pathan Basha //


	 
	 
	public List getTestSampleCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.";

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
				//END  TEST  SAMPLE MASTER  Added By Pathan Basha //
				
				
				public List getMachine(LabTestGlobalMstVO labTestGlobalMstVO,UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List lstMachine=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.MACHINE_NAME_GLOBAL";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();

					populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
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
							//throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return lstMachine;
				}
				//END  TEST  SAMPLE MASTER  Added By Pathan Basha //
				
				
				//fetching test template name for test master - punnet
				
				
				public List getTestPrintingTemplateCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List lstPrintingTemplateName=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.TEST_PRINTING_TEMPLATE_COMBO.HIVT_PRINTING_TEMPLATE";

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
				
				
				//when a new test is created update the printing master, test code obtained via auto generate method 
				public void updatePrintingTemplate(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
				{
					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="UPDATE_NEW_TEST.HIVT_PRINTING_TEMPLATE";
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
						populateMAP.put(sq.next(), testNewMasterVO.getPrintingTemplateCode());
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

			 //on modifying the test,  test code is available 
				public void updatePrintingTemplateOnModify(TestNewMasterVO testNewMasterVO, UserVO _UserVO)
				{
					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="UPDATE_MODIFY_TEST.HIVT_PRINTING_TEMPLATE";
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
						populateMAP.put(sq.next(), testNewMasterVO.getTestCode());
						populateMAP.put(sq.next(), testNewMasterVO.getPrintingTemplateCode());
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
