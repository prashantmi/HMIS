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
import new_investigation.InvestigationConfig;
import new_investigation.vo.LabMasterVO;
import new_investigation.vo.TestGroupInfoLocalMasterVO;
import new_investigation.vo.TestGroupInfoMasterVO;

public class LocalLabMstDAO extends DataAccessObject implements LocalLabMstDAOi
{

	public LocalLabMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}



	public void createLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "INSERT_LOCAL.HIVT_LABORATORY_MST";
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
			populateMAP.put(sq.next(), labMasterVO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
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
			populateMAP.put(sq.next(), labMasterVO.getLocation());
			populateMAP.put(sq.next(), labMasterVO.getLabIncharge());
			populateMAP.put(sq.next(), labMasterVO.getDisplayHeader());
			populateMAP.put(sq.next(), labMasterVO.getFinalRemark());
			populateMAP.put(sq.next(), labMasterVO.getIstestmodify());
			populateMAP.put(sq.next(), labMasterVO.getIsreportlablabelchanged());
			populateMAP.put(sq.next(), labMasterVO.getIsreportcollectionlabelchanged());
			populateMAP.put(sq.next(), labMasterVO.getIsreportsamplelabelrequired());
			populateMAP.put(sq.next(), labMasterVO.getIsAppointment());
			populateMAP.put(sq.next(), labMasterVO.getIsaptmand());
			populateMAP.put(sq.next(), labMasterVO.getIsfilmused());
			populateMAP.put(sq.next(), labMasterVO.getResultentered());
			populateMAP.put(sq.next(), labMasterVO.getHidefromdesk());
			populateMAP.put(sq.next(), labMasterVO.getAccesstoaddendum());
			populateMAP.put(sq.next(), labMasterVO.getEntryuser());
			populateMAP.put(sq.next(), labMasterVO.getValidation());
			populateMAP.put(sq.next(), labMasterVO.getRevalidation());
			populateMAP.put(sq.next(), labMasterVO. getSopbased());
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



	public void fetchLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT_LOCAL.HIVT_LABORATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
	public void updateLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey ="UPDATE_LOCAL.HIVT_LABORATORY_MST";
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

            if(labMasterVO.getIsTimeBound()==null)
            {
            	labMasterVO.setIsTimeBound("0");
            	
            }

			populateMAP.put(sq.next(), labMasterVO.getIsTimeBound());
			if(labMasterVO.getIsTimeBound().equals("0")){
			populateMAP.put(sq.next(), null);
			populateMAP.put(sq.next(), null);
			}
			
			if(labMasterVO.getIsTimeBound().equals("1")){
				populateMAP.put(sq.next(), labMasterVO.getStartTimeHH()+":"+labMasterVO.getStartTimeMM());
				populateMAP.put(sq.next(), labMasterVO.getEndTimeHH()+":"+labMasterVO.getEndTimeMM());}


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

			populateMAP.put(sq.next(), labMasterVO.getLocation());
			populateMAP.put(sq.next(), labMasterVO.getLabIncharge());
			populateMAP.put(sq.next(), labMasterVO.getDisplayHeader());
			populateMAP.put(sq.next(), labMasterVO.getFinalRemark());
			populateMAP.put(sq.next(), labMasterVO.getIstestmodify());
			populateMAP.put(sq.next(), labMasterVO.getIsreportlablabelchanged());
			populateMAP.put(sq.next(), labMasterVO.getIsreportcollectionlabelchanged());
			populateMAP.put(sq.next(), labMasterVO.getIsreportsamplelabelrequired());
			populateMAP.put(sq.next(), labMasterVO.getIsAppointment());
			populateMAP.put(sq.next(), labMasterVO.getIsaptmand());
			populateMAP.put(sq.next(), labMasterVO.getIsfilmused());
			populateMAP.put(sq.next(), labMasterVO.getResultentered());
			populateMAP.put(sq.next(), labMasterVO.getHidefromdesk());
			populateMAP.put(sq.next(), labMasterVO.getAccesstoaddendum());
			populateMAP.put(sq.next(), labMasterVO.getEntryuser());
			populateMAP.put(sq.next(), labMasterVO.getValidation());
			populateMAP.put(sq.next(), labMasterVO.getRevalidation());
			populateMAP.put(sq.next(), labMasterVO. getSopbased());
			populateMAP.put(sq.next(), labMasterVO.getLabCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


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
	public String checkDuplicateLocalLab(LabMasterVO labMasterVO, UserVO _UserVO)
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


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
	public String checkDuplicateLocalLabModify(LabMasterVO labMasterVO,UserVO _UserVO)
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
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());


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


	public List getGlobalLabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.GLOBAL_LAB_COMBO.HIVT_LABORATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
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


	public List getLocalLabCombo(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List departcombo=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.LAB_COMBO.HIVT_LABORATORY_MST";

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


	public void Populate(LabMasterVO labMasterVO, UserVO _UserVO)
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
	
	
	// For location combo

		public List getlocationCombo(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List lstlocationName=new ArrayList();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey="SELECT.LOCATION_COMBO.HIVT_LABORATORY_MST";

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
				//	throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					lstlocationName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
			return lstlocationName;
		}

		// For lab incharge combo

				public List getlabInchargeCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List lstlabInchargeName=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.LAB_INCHARGE_COMBO.HIVT_LABORATORY_MST";

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
							//throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							lstlabInchargeName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
					return lstlabInchargeName;
				}

				public String checkPrimaryKeyLocalLab(LabMasterVO locallab_VO, UserVO _UserVO)
				{
					String query = "";
					ResultSet rs;
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="CHECKPRIMARYKEY.HIVT_LABORATORY_MST";
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
						populateMAP.put(sq.next(), locallab_VO.getLabCode());   
						populateMAP.put(sq.next(), _UserVO.getHospitalCode());
						
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

			/*	public void updateValidLocalLab(LabMasterVO locallab_VO,UserVO _UserVO)
				{
					String query = "";
					ResultSet rs;
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="UPDATE_VALID.HIVT_LABORATORY_MST";
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

						populateMAP.put(sq.next(), locallab_VO.getLabCode());
						populateMAP.put(sq.next(), _UserVO.getHospitalCode());


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
				*/
				
				
				public void updateValid(LabMasterVO locallab_VO,UserVO _UserVO)
				{
					String query = "";
					ResultSet rs;
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="UPDATE_VALID.HIVT_LABORATORY_MST";
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
						populateMAP.put(sq.next(), locallab_VO.getLabCode());

						populateMAP.put(sq.next(), _UserVO.getHospitalCode());

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
				
}
