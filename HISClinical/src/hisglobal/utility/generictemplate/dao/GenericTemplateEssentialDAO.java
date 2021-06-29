package hisglobal.utility.generictemplate.dao;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import oracle.jdbc.driver.OracleTypes;









import ehr.EHRConfig;
import ehr.ImageExam.EHRSection_ImageExamVO;
import ehr.history.vo.EHRSection_HistoryVO;
import emr.vo.PatientClinicalDocDetailVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.TemplateDesignerUtility;
import hisglobal.vo.GrowthChartVO;
import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateCategoryVO;
import hisglobal.vo.TemplateGroupVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class GenericTemplateEssentialDAO extends DataAccessObject implements GenericTemplateEssentialDAOi
{
	
	Logger log;
	/**
	 * Constructor for Setting Transaction Context
	 */
	public GenericTemplateEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	/** 
	 * Getting Template Group List
	 * @param _userVO UserVO 
	 * @return List of TemplateGroupVO objects
	 */
	public List<TemplateGroupVO> getTemplateGroupList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL.HGBT_TEMPLATE_GROUP_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vos = null;
		List<TemplateGroupVO> lstTempGrp = new ArrayList();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vos = HelperMethods.populateVOfrmRS(TemplateGroupVO.class, rs);
				
				for (ValueObject vo : vos)
					lstTempGrp.add((TemplateGroupVO)vo);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateCategoryList" + e);
		}
		return lstTempGrp;
	}

	/** Getting Template Category List
	 * @param _userVO UserVO 
	 * @return List of TemplateCategoryVO objects
	 */
	public List<TemplateCategoryVO> getAllTemplateCategoryList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL.HGBT_TEMPLATE_CATEGORY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vos = null;
		List<TemplateCategoryVO> lstTempCat = new ArrayList();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vos = HelperMethods.populateVOfrmRS(TemplateCategoryVO.class, rs);
				
				for (ValueObject vo : vos)
					lstTempCat.add((TemplateCategoryVO)vo);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateCategoryList" + e);
		}
		return lstTempCat;
	}
	
	
	
	/** Getting Age Range List
	 * @param _userVO UserVO 
	 * @return List of TemplateCategoryVO objects
	 */
	public List<TemplateCategoryVO> getAllAgeRangeList2(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL_AGE_RANGE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vos = null;
		List<TemplateCategoryVO> lstAgeRange = new ArrayList();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vos = HelperMethods.populateVOfrmRS(TemplateCategoryVO.class, rs);
				
				for (ValueObject vo : vos)
					lstAgeRange.add((TemplateCategoryVO)vo);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateCategoryList" + e);
		}
		return lstAgeRange;
	}


//added by swati on date:05-08-2019
	 public List getAllAgeRangeList( UserVO _UserVO)
	      {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
			String queryKey = "SELECT.ALL_AGE_RANGE";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			//log.error(query + "\n");

			System.out.println("   -------> query :: " + query);
			Sequence sq = new Sequence();
		
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());// procedures are to be picked from hospCode 100..21Aug2013
			//populateMAP.put(sq.next(), ServiceAreaConfig.SUPER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = new ArrayList();
			try
			{
				if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HisDataAccessException  :fetchAllProcedure" + e);
			}
			return alRecord;

			
		}
		
//added by swati on date:05-08-2019
	  public List getAllGenderBoundList( UserVO _UserVO)
	      {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();

			String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
			String queryKey = "SELECT.ALL_GENDER";

			try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
			    }
			catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
			//	log.error(query + "\n");

				System.out.println("   -------> query :: " + query);
				Sequence sq = new Sequence();
				
					//populateMAP.put(sq.next(), _UserVO.getHospitalCode());// procedures are to be picked from hospCode 100..21Aug2013
					//populateMAP.put(sq.next(), ServiceAreaConfig.SUPER_HOSPITAL_CODE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			try
				{
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
					
				}
			catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
				        {
							throw new HisRecordNotFoundException(e.getMessage());
						}
					 else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
				}
					List alRecord = new ArrayList();
				try
				{
						if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
				catch (Exception e)
				{
						throw new HisDataAccessException("HisDataAccessException  :fetchAllProcedure" + e);
				}
				return alRecord;

					
				}
				
				
	
	
	
	
	/** Getting Template Category List
	 * @param _userVO UserVO 
	 * @return List of TemplateCategoryVO objects
	 */
	public List<TemplateCategoryVO> getAllGenderBoundList2(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL_GENDER";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		ValueObject[] vos = null;
		List<TemplateCategoryVO> lstGender = new ArrayList();
		try
		{
			while(rs.next())
			{				
				rs.beforeFirst();
				vos = HelperMethods.populateVOfrmRS(TemplateCategoryVO.class, rs);
				
				for (ValueObject vo : vos)
					lstGender.add((TemplateCategoryVO)vo);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateCategoryList" + e);
		}
		return lstGender;
	}

	
	
	
	
	//By Mukund on 10.08.2017
	/** Getting getAllTemplateListwithCat
	 * @param _userVO UserVO 
	 * @return List of TemplateMasterVO objects
	 */
	public List<Entry> getAllTemplateListwithCat(String templateId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL_TEMPLATE_LIST.HGBT_TEMPLATE_CATEGORY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), templateCategory);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> lstTemplt = new ArrayList();
		try
		{
			//if (rs.next())lstTemplt = HelperMethodsDAO.getAlOfEntryObjects(rs);
			while (rs.next())
			{
				if(!templateId.equals(rs.getString(1).split("#")[0]))
				{	
					Entry objEntry = new Entry();
					objEntry.setLabel(rs.getString(2));
					objEntry.setValue(rs.getString(1));
					//System.out.println("Entry: " + objEntry);
					lstTemplt.add(objEntry);
				}
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllTemplateList" + e);
		}
		return lstTemplt;
	}//End on 10.08.2017

	/** Getting All Clinical Parameter List
	 * @param _userVO UserVO 
	 * @return List of Entry objects of Clinical Parameters Id/Value
	 */
	public List<Entry> getAllClinicalParameterList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL.HGBT_PARAMETER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllClinicalParameterList" + e);
		}
		return alRecord;
	}
	
	/** 
	 * Getting All Clinical Parameter List Group Wise
	 * @param _tempGroupId Template Group Id
	 * @param _userVO UserVO User Detail
	 * @return List of Entry objects of Clinical Parameters Id/Value
	 */
	public List<Entry> getAllClinicalParameterListGroupWise(String _tempGroupId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ALL_GROUP_WISE.HGBT_PARAMETER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _tempGroupId);	// Template Group Id in Para Type
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllClinicalParameterList" + e);
		}
		return alRecord;
	}

	/** Getting Parameter Dynamic Data 
	 * @param _query Dynamic Query
	 * @param _userVO User VO
	 * @return Entry Object of Value/Label 
	 */
	public List<Entry> getParameterDynamicData(String _query, UserVO _userVO)
	{
		ResultSet rs = null;
		Map populateMAP = new HashMap();

		System.out.println("   -------> query :: " + _query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			_query = _query.replaceAll("#", _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), _query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List<Entry> alRecord = null;
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getParameterDynamicData" + e);
		}
		return alRecord;
	}

	/** Getting Parameter Range Data on basis of para id Gender and Age
	 * @param _paraId Parameter Id
	 * @param _gender Gender Code 
	 * @param _age Age for Age Limit
	 * @param _userVO User VO
	 * @return Parameter Range VO 
	 */
	public ParameterRangeMasterVO getParameterRangeData(String _paraId, String _gender,String _age, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg = null;
		ParameterRangeMasterVO vo = new ParameterRangeMasterVO();

		try
		{
			Procedure strProc = new Procedure(GenericTemplateConfig.GET_PARAMETER_RANGE_PROC);
			strProc.addInParameter(1, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addInParameter(2, Types.VARCHAR, _paraId);
			strProc.addInParameter(3, Types.VARCHAR, _gender);
			strProc.addInParameter(4, Types.VARCHAR, _age);
			strProc.addInParameter(5, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.REF);//OracleTypes.CURSOR);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(6);
			rs = (ResultSet) strProc.getParameterAt(7);
		}
		catch (HisException e)
		{
			//throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			e.printStackTrace();
			return null;
		}

		try
		{
			if(rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
			}
			else
				vo = null;
		}
		catch (Exception e)
		{
			//throw new HisDataAccessException("DepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			e.printStackTrace();
			vo=null;
		}

		return vo;
	}

	/**
	 * Getting Template List by Unit, Ward, Seat, Desk, Desk Menu Wise
	 * @param _deskType DEsk Type
	 * @param _userDeskTempVO User Desk Template Mapping VO containing required Info
	 * @param _userVO User VO
	 * @return List of Entry objects in format tempId#defaultFlag as Value and Temp Name as Label 
	 */
	public List<Entry> getDeskMenuTemplateList(String _deskType, String _patCrNo, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		ResultSet rs = null;
		String errorMsg="";
		
		try
		{
			Procedure strProc=new Procedure(GenericTemplateConfig.PROC_FOR_TEMPLATE_LIST);
		    strProc.addInParameter(1,Types.VARCHAR,_deskType);
		    strProc.addInParameter(2,Types.VARCHAR,_patCrNo);
			strProc.addInParameter(3,Types.VARCHAR,_userDeskTempVO.getDeptUnitCode());
			if(_userDeskTempVO.getWardCode()==null || _userDeskTempVO.getWardCode().equals("")) _userDeskTempVO.setWardCode("-1");
			strProc.addInParameter(4,Types.VARCHAR,_userDeskTempVO.getWardCode());
			//strProc.addInParameter(4,Types.VARCHAR,_userDeskTempVO.getUserSeatId());
			strProc.addInParameter(5,Types.VARCHAR,_userVO.getSeatId());
			strProc.addInParameter(6,Types.VARCHAR,_userDeskTempVO.getDeskId());
			strProc.addInParameter(7,Types.VARCHAR,_userDeskTempVO.getDeskMenuId());
			strProc.addInParameter(8,Types.VARCHAR,_userVO.getHospitalCode());
			strProc.addOutParameter(9,Types.VARCHAR);
			strProc.addOutParameter(10,Types.REF);//OracleTypes.CURSOR);
			
			System.out.println("_deskType:"+ _deskType);
			System.out.println(" _userVO.getSeatId():"+ _userVO.getSeatId());
			System.out.println("_unitCode:"+_userDeskTempVO.getDeptUnitCode());
			System.out.println("_deskMenuId:"+_userDeskTempVO.getDeskMenuId());
			System.out.println("_userDeskTempVO.getDeskId():"+_userDeskTempVO.getDeskId());
			System.out.println("_userVO.getHospitalCode():"+_userVO.getHospitalCode());
			
			strProc.execute(super.getTransactionContext().getConnection());
			
			errorMsg=(String) strProc.getParameterAt(9);
			rs=(ResultSet) strProc.getParameterAt(10);
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		List<Entry> lstTemplates = new ArrayList<Entry>();
		try
		{
			while(rs.next())
			{
				Entry e = new Entry();
				e.setValue(rs.getString(1));
				e.setLabel(rs.getString(2));
				lstTemplates.add(e);
				System.out.println("TempID::"+rs.getString(1));
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("AlertDAO::getDeskMenuTemplateList"+e);
		}
		return lstTemplates;
	}

	/**
	 * Getting Paremter List by Template Wise
	 * @param _lstTemp List of Entry TempId/TempName
	 * @param _userVO User VO
	 * @return List of Entry objects in format ParaId/ParaName 
	 */
	public List<Entry> getTemplateParametersList(List<Entry> _lstTemp, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.PARAS_IN_TEMPLATES.HGBT_TEMPLATE_PARA_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String tempIds="";
			for(Entry e: _lstTemp)
				tempIds+=e.getValue()+",";
			if(!tempIds.equals("")) tempIds=tempIds.substring(0, tempIds.length()-1);
			query = query.replace("#", tempIds);
			query = query.replace("@", Integer.toString(TemplateDesignerUtility.CONTROL_TYPES_LABEL));
			
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTemplateParametersList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting Paremter List by Template Wise
	 * @param _lstTemp List of Entry TempId/TempName
	 * @param _userVO User VO
	 * @return List of Entry objects in format ParaId/ParaName 
	 */
	public List<Entry> getEntryParametersListofTempIds(List<String> _lstTempIds, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.ENTRY_PARAS_IN_TEMPLATES.HGBT_TEMPLATE_PARA_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//Sequence sq = new Sequence();
		try
		{
			String tempIds="";
			for(String id: _lstTempIds)
				tempIds+=id+",";
			if(!tempIds.equals(""))
			{
			tempIds=tempIds.substring(0, tempIds.length()-1);
			query = query.replace("#", tempIds);
			}
			else
			{
				tempIds="0";
				query = query.replace("#", tempIds);
			}
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTemplateParametersList" + e);
		}
		return alRecord;
	}

	/**
	 * Getting Template List by Template Category
	 * @param _tempCategory 	Array of Template Category IDs
	 * @param _userVO 			User VO
	 * @return 					List of Entry objects in format TempId/TempName 
	 */
	public List<Entry> getTemplateListByCategory(String _tempCategory, UserVO _userVO)
	{
		return getTemplateListByCategories(new String[]{_tempCategory}, _userVO);
	}
	
	/**
	 * Getting Template List by Template Category
	 * @param _tempCategory 	Array of Template Category IDs
	 * @param _userVO 			User VO
	 * @return 					List of Entry objects in format TempId/TempName 
	 */
	public List<Entry> getTemplateListByCategories(String[] _tempCategory, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = GenericTemplateConfig.QUERY_FILE_FOR_GENERIC_TEMPLATE;
		String queryKey = "SELECT.TEMP_BY_CAT.HGBT_TEMPLATE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			String tempCatIds="";
			for(String str: _tempCategory)
				tempCatIds+=str+",";
			if(!tempCatIds.equals("")) tempCatIds=tempCatIds.substring(0, tempCatIds.length()-1);
			query = query.replace("#", tempCatIds);
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("GenericTemplateEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List<Entry> alRecord = new ArrayList<Entry>();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getTemplateListByCategories" + e);
		}
		return alRecord;
	}

	//Added by Vasu on 16.May.2019 to get template clinical data for Single Page
	public Map<String, Object> getTemplateClinicalData(String deskMenuId, String deskType, String deskId, String pmode,PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO)
	{
	
		ResultSet rs = null;
		String errorMsg="";
		
		try
		{
			Procedure strProc=new Procedure(EHRConfig.PROC_FOR__TEMPLATE_CLINICAL_DATA);
			  strProc.addInParameter(1,Types.VARCHAR,pmode);
		    strProc.addInParameter(2,Types.VARCHAR,deskType);
			strProc.addInParameter(3,Types.VARCHAR,selectedPatientVO.getDepartmentUnitCode());
			if(selectedPatientVO.getWardCode()==null || selectedPatientVO.getWardCode().equals("")) selectedPatientVO.setWardCode("-1");
			strProc.addInParameter(4,Types.VARCHAR,selectedPatientVO.getWardCode());
			strProc.addInParameter(5,Types.VARCHAR,userVO.getSeatId());  
			
			strProc.addInParameter(6,Types.VARCHAR,deskId);
			
			strProc.addInParameter(7,Types.VARCHAR,deskMenuId);
			strProc.addInParameter(8,Types.VARCHAR,userVO.getHospitalCode());
			strProc.addInParameter(9,Types.VARCHAR,selectedPatientVO.getPatCrNo());
			strProc.addInParameter(10,Types.VARCHAR,selectedPatientVO.getEpisodeCode());
			strProc.addInParameter(11,Types.VARCHAR,selectedPatientVO.getEpisodeVisitNo());
			strProc.addInParameter(12,Types.VARCHAR,selectedPatientVO.getPatAdmNo());
			strProc.addInParameter(13,Types.VARCHAR,(clinicalDocVO.getDocumentType()==null) ? "":clinicalDocVO.getDocumentType());
			strProc.addInParameter(14,Types.VARCHAR,(clinicalDocVO.getClinicalSectionCode()==null)? "":clinicalDocVO.getClinicalSectionCode());
			
			strProc.addOutParameter(15,Types.VARCHAR);
			strProc.addOutParameter(16,Types.REF);//OracleTypes.CURSOR);
			
			
			strProc.execute(super.getTransactionContext().getConnection());
			
			errorMsg=(String) strProc.getParameterAt(15);
			rs=(ResultSet) strProc.getParameterAt(16) ;
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		Map<String, Object> mpData = new HashMap<String, Object>();
		ValueObject[] arrVOs = {};
		//GenericTemplateUtility.TempParameter tempParas[] = new GenericTemplateUtility.TempParameter[0];
		
		// RecordDate, TemplateId, para id, para value
		Map<String, Map<String, Map<String, String>>> mpParaTempValuesAll = new HashMap<String, Map<String, Map<String, String>>>();
		List<GenericTemplateUtility.TempParameter> lstTempData= new ArrayList<GenericTemplateUtility.TempParameter>();
		// RecordDate, TemplateId, para id, tempparavo
		Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> mpParaTempParaDtlAll = new HashMap<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>>();
		
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(GenericTemplateUtility.TempParameter.class, rs);
				//tempParas = new GenericTemplateUtility.TempParameter[arrVOs.length];
				//for (int i = 0; i < vo.length; i++)
					//tempParas[i] = (GenericTemplateUtility.TempParameter) vo[i];
				for(ValueObject objVO : arrVOs)
				{
					GenericTemplateUtility.TempParameter paraValVO = (GenericTemplateUtility.TempParameter) objVO;
					Map<String, Map<String, String>> mpRecordTemps = mpParaTempValuesAll.get(paraValVO.getRecordDate());
					if(mpRecordTemps==null)
					{
						mpRecordTemps = new HashMap<String, Map<String, String>>();
						mpParaTempValuesAll.put(paraValVO.getRecordDate(), mpRecordTemps);
					}
					Map<String, String> mpTempParas = mpRecordTemps.get(paraValVO.getTemplateId());
					if(mpTempParas==null)
					{
						mpTempParas = new HashMap<String, String>();
						mpRecordTemps.put(paraValVO.getTemplateId(), mpTempParas);
						lstTempData.add(paraValVO);
					}
					mpTempParas.put(paraValVO.getParaId(),paraValVO.getParaValue());

					
					Map<String, Map<String, GenericTemplateUtility.TempParameter>> mpRecordTempsDtl = mpParaTempParaDtlAll.get(paraValVO.getRecordDate());
					if(mpRecordTempsDtl==null)
					{
						mpRecordTempsDtl = new HashMap<String, Map<String, GenericTemplateUtility.TempParameter>>();
						mpParaTempParaDtlAll.put(paraValVO.getRecordDate(), mpRecordTempsDtl);
					}
					Map<String, GenericTemplateUtility.TempParameter> mpTempParasDtl = mpRecordTempsDtl.get(paraValVO.getTemplateId());
					if(mpTempParasDtl==null)
					{
						mpTempParasDtl = new HashMap<String, GenericTemplateUtility.TempParameter>();
						mpRecordTempsDtl.put(paraValVO.getTemplateId(), mpTempParasDtl);
					}
					mpTempParasDtl.put(paraValVO.getParaId(),paraValVO);
				}
				
				// Set Map and List in mpData
				mpData.put("A", mpParaTempValuesAll);
				mpData.put("B", lstTempData);
				mpData.put("C", mpParaTempParaDtlAll);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("AlertDAO::getDeskMenuTemplateList"+e);
		}
		return mpData;
	}
	
	public List<GrowthChartVO> AJAX_GETChartEssential(String crNo)
	{
	//	System.out.println("in getPrevReqList");
		
		ResultSet rs 		= 	null;
		List alRecord 		= 	new ArrayList();
		Connection conn		=	super.getTransactionContext().getConnection();
		CallableStatement cstmt=null;
		try 
		{
			
			cstmt=conn.prepareCall("{ call PKG_EHR_VIEW.get_growthChart_essential(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, "1");
			cstmt.setString(2, crNo);
			cstmt.setString(3, "");
			cstmt.setString(4, "");
			cstmt.setString(5, "");
			cstmt.setString(6,"");
			cstmt.setString(7,"");
			cstmt.setString(8,"");
			cstmt.setString(9,"");
			
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.REF);
			cstmt.execute();
			rs=(ResultSet)cstmt.getObject(11);
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		try
		{
			if(rs==null)
			{
			//	System.out.println("null resultset");
			}
			while(rs.next())
			{
		
				GrowthChartVO growChartVO1=new GrowthChartVO();
		
				//imageExamVO1.setDepartmentUnitCode(rs.getString(1));
				growChartVO1.setVitalId(rs.getString(1));;
				growChartVO1.setVitalName(rs.getString(2));
				growChartVO1.setVitalValue(rs.getString(3));
				growChartVO1.setGender(rs.getString(4));
				growChartVO1.setAgeYears(rs.getString(5));

				if(rs.getString(1).equalsIgnoreCase("1110001"))
				growChartVO1.setWeight(rs.getString(3));
				else
					growChartVO1.setHeight(rs.getString(3));
				growChartVO1.setAgeMonths(rs.getString(6));
				growChartVO1.setAgeDays(rs.getString(7));
				growChartVO1.setAgeWeeks(rs.getString(8));
				
					alRecord.add(growChartVO1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getPrevReqList" + e);
//			e.printStackTrace();
		}
		finally
		{
			if(cstmt!=null)
			{
				try {
					cstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return alRecord;
	}
}
