package hisglobal.persistence;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.installSoftware.InstallSoftwareVO;
import hisglobal.utility.masterVerification.MasterVerificationVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class GlobalEssentialDAO extends DataAccessObject implements GlobalEssentialDAOi
{
	Logger log;
	public GlobalEssentialDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	

	public String[] getSystemDate(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_SYSTEM_DATE_CR_FORMAT(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String date = "";
		String[] dateAndFormat = new String[2];
		

		try 
		{
			daoObj = new HisDAO("Registration","GlobalEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno_size", Config.CR_NO_FORMAT_SIZE,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				ResultSetMetaData rsmd = webRs.getMetaData();
				int no_of_col = rsmd.getColumnCount();
				while (webRs.next())
				{
					for (int i = 1; i <= no_of_col; i++)
					{
						alRecord.add(webRs.getString(i));
					}
				}
				Calendar cl = Calendar.getInstance();

				cl.set(Calendar.DATE, Integer.parseInt((String) alRecord.get(0)));
				cl.set(Calendar.MONTH, Integer.parseInt((String) alRecord.get(1)) - 1);
				cl.set(Calendar.YEAR, Integer.parseInt((String) alRecord.get(2)));
				cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) alRecord.get(3)));
				cl.set(Calendar.MINUTE, Integer.parseInt((String) alRecord.get(4)));
				cl.set(Calendar.SECOND, Integer.parseInt((String) alRecord.get(5)));
				Date dt = cl.getTime();
				SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
				sf.applyPattern("dd/MM/yyyy HH:mm");
				//TimeZone tz = cl.getTimeZone();
				date = sf.format(dt);
				String defaultCrNoFormat = (String) alRecord.get(6);

				dateAndFormat[0] = date;
				dateAndFormat[1] = defaultCrNoFormat;
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return dateAndFormat;
	}
	public Date getSystemDate(Date _dt)
	{
		WebRowSet webRs = null;
		//ResultSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_SYSTEM_DATE_CR_FORMAT(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		try 
		{
			daoObj = new HisDAO("Registration","GlobalEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno_size", Config.CR_NO_FORMAT_SIZE,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code","100",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				ResultSetMetaData rsmd = webRs.getMetaData();
				int no_of_col = rsmd.getColumnCount();
				while (webRs.next())
				{
					for (int i = 1; i <= no_of_col; i++)
					{
						alRecord.add(webRs.getString(i));
					}
				}			
			Calendar cl = Calendar.getInstance();
			cl.set(Calendar.DATE, Integer.parseInt((String) alRecord.get(0)));
			cl.set(Calendar.MONTH, Integer.parseInt((String) alRecord.get(1)) - 1);
			cl.set(Calendar.YEAR, Integer.parseInt((String) alRecord.get(2)));
			cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) alRecord.get(3)));
			cl.set(Calendar.MINUTE, Integer.parseInt((String) alRecord.get(4)));
			cl.set(Calendar.SECOND, Integer.parseInt((String) alRecord.get(5)));
			_dt = cl.getTime();
			}
			return _dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:getSystemDate(Date _dt)" + e);
			
		}
	}
	public Date getSystemDate(UserVO _userVO,Date _dt)
	{
		WebRowSet webRs = null;
		//ResultSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_SYSTEM_DATE_CR_FORMAT(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		try 
		{
			daoObj = new HisDAO("Registration","GlobalEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno_size", Config.CR_NO_FORMAT_SIZE,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code","101",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(super.getTransactionContext().getConnection(),nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				ResultSetMetaData rsmd = webRs.getMetaData();
				int no_of_col = rsmd.getColumnCount();
				while (webRs.next())
				{
					for (int i = 1; i <= no_of_col; i++)
					{
						alRecord.add(webRs.getString(i));
					}
				}			
			Calendar cl = Calendar.getInstance();
			cl.set(Calendar.DATE, Integer.parseInt((String) alRecord.get(0)));
			cl.set(Calendar.MONTH, Integer.parseInt((String) alRecord.get(1)) - 1);
			cl.set(Calendar.YEAR, Integer.parseInt((String) alRecord.get(2)));
			cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) alRecord.get(3)));
			cl.set(Calendar.MINUTE, Integer.parseInt((String) alRecord.get(4)));
			cl.set(Calendar.SECOND, Integer.parseInt((String) alRecord.get(5)));
			_dt = cl.getTime();
			}
			return _dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:getSystemDate(Date _dt)" + e);
			
		}
	}

	public List getSystemDateAndFormat(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_SYSTEM_DATE_CR_FORMAT(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String date = "";
		List dateAndFormat = new ArrayList();
		boolean retValue = false;
		

		try 
		{
			daoObj = new HisDAO("Registration","GlobalEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno_size", Config.CR_NO_FORMAT_SIZE,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			retValue=daoObj.executeProcedureByPosition(super.getTransactionContext().getConnection(),nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
	
			if (strErr.equals("")) 
			{
				ResultSetMetaData rsmd = webRs.getMetaData();
				int no_of_col = rsmd.getColumnCount();
				while (webRs.next())
				{
					for (int i = 1; i <= no_of_col; i++)
					{
						alRecord.add(webRs.getString(i));
					}
				}
				Calendar cl = Calendar.getInstance();

				cl.set(Calendar.DATE, Integer.parseInt((String) alRecord.get(0)));
				cl.set(Calendar.MONTH, Integer.parseInt((String) alRecord.get(1)) - 1);
				cl.set(Calendar.YEAR, Integer.parseInt((String) alRecord.get(2)));
				cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) alRecord.get(3)));
				cl.set(Calendar.MINUTE, Integer.parseInt((String) alRecord.get(4)));
				cl.set(Calendar.SECOND, Integer.parseInt((String) alRecord.get(5)));
				Date dt = cl.getTime();
				SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
				sf.applyPattern("dd/MM/yyyy HH:mm");
				//TimeZone tz = cl.getTimeZone();
				date = sf.format(dt);
				String defaultCrNoFormat = (String) alRecord.get(6);

				dateAndFormat.add(0, date);
				dateAndFormat.add(1, defaultCrNoFormat);
				dateAndFormat.add(2, dt);
			} 
			else 
			{
				retValue=false;
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			retValue=false;
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
			e.printStackTrace();
			
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
			
			}
		return dateAndFormat;
	}

	public String isRegistrationAllowed(String _categoryCode, UserVO _userVO)
	{
		String registrationAllowed = null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.REGISTRATION_TIMING.DUAL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _categoryCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if(!rs.next()){
			   throw new HisRecordNotFoundException("Record against specified Department Not Found");		 	    	 	    	 
			   }
			else*/
			//   rs.beforeFirst(); 
			rs.next();
			registrationAllowed = rs.getString(1);
			if (registrationAllowed.equals("") || registrationAllowed.equals(Config.REGISTRATION_ALLOWED_FALSE))
			{
				throw new HisRegistrationTimingExpiredException("Registration Time Is Over");
			}
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRegistrationTimingExpiredException.class)
			{
				throw new HisRegistrationTimingExpiredException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return registrationAllowed;
	}


	public HospitalMstVO getHospitalDetail(String hospitalCode) {
		
		HospitalMstVO hospitalMstVo=new HospitalMstVO();
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_HOSPITAL_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hospCode", hospitalCode,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			webRs.beforeFirst();
			HelperMethods.populateVOfrmRS(hospitalMstVo, webRs);
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} 
			else
				throw new HisDataAccessException("GlobalDAO:getHospitalDetail:HelperMethods :: " + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return hospitalMstVo;
	}


	public List getSoftwareList(InstallSoftwareVO softwareVO)
	{
		List softwareVOList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.SOFTWARE.HGBT_SOFTWARE_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), softwareVO.getServerOS());
		populateMAP.put(sq.next(), softwareVO.getClientOS());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
			   throw new HisRecordNotFoundException("No Software Found");		 	    	 	    	 
			   }
			else
			rs.beforeFirst(); 
			while(rs.next()){
				softwareVO=new InstallSoftwareVO();
				HelperMethods.populateVOfrmRS(softwareVO, rs);
				softwareVOList.add(softwareVO);
			}	
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return softwareVOList;
	}
	
	
	public List getModuleList(UserVO userVO)
	{
		List moduleList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.MODULE_LIST.GBLT_METATABLE_TYPE_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Module Found");		 	    	 	    	 
			}
			else
				rs.beforeFirst(); 
			moduleList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return moduleList;
	}
	
	public List getMasterListByModuleId(String moduleId,UserVO userVO)
	{
		List<MasterVerificationVO> masterVerificationVOList=new ArrayList<MasterVerificationVO>();
		MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.BY_MODULEID.GBLT_MASTER_VERIFICATION_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), moduleId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Master found for this module");		 	    	 	    	 
			}
			else
				rs.beforeFirst(); 
			
			while(rs.next()){
				masterVerificationVO=new MasterVerificationVO();
				HelperMethods.populateVOfrmRS(masterVerificationVO, rs);
				masterVerificationVOList.add(masterVerificationVO);
			}
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return masterVerificationVOList;
	}
	
	
	public List getAllMasterList(String moduleId,UserVO userVO)
	{
		/*List<MasterVerificationVO> masterVerificationVOList=new ArrayList<MasterVerificationVO>();
		MasterVerificationVO masterVerificationVO=new MasterVerificationVO();*/
		List masterList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.ALL_MASTER_LIST.BY_MODULE_ID";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
			//populateMAP.put(sq.next(), moduleId);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			//rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Master found for this module");		 	    	 	    	 
			}
			else
				//rs.beforeFirst(); 
				masterList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return masterList;
	}
	
	public List getMasterColumnList(String masterName, UserVO userVO)
	{
		List masterColumnList=new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.COLUMN_LIST.BY_MASTER";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
			populateMAP.put(sq.next(), masterName);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Master found for this module");		 	    	 	    	 
			}
			else
				//rs.beforeFirst(); 
				masterColumnList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return masterColumnList;
	}
	
	
	public List getMasterCriteriaData(String criteriaQuery, UserVO userVO)
	{
		List criteriaDataList=null;
		ResultSet rs = null;
		//String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		//populateMAP.put(sq.next(), moduleId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		System.out.println("criteriaQuery :::: " + criteriaQuery);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), criteriaQuery,populateMAP);			
			if(!rs.next())	{
				throw new HisRecordNotFoundException("No Data Found");		 	    	 	    	 
			}
			else{
				rs.beforeFirst(); 
			}
			criteriaDataList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return criteriaDataList;
	}
	
		
		
	public List getMasterDataList(String mainQuery, String[] masterColumnArray,String isActive,UserVO userVO)
	{
		List masterDataList=null;
		List columnList=new ArrayList();
		String row="";
		ResultSet rs = null;
		//String query = "";
		Map populateMAP = new HashMap();
				
		Sequence sq = new Sequence();
		//populateMAP.put(sq.next(), moduleId);
		populateMAP.put(sq.next(), isActive);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			System.out.println("mainQuery :: "+ mainQuery);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), mainQuery,populateMAP);
			if(!rs.next()){
				throw new HisRecordNotFoundException("No Master data found");		 	    	 	    	 
			}
			else{
				masterDataList=new ArrayList();
				rs.beforeFirst();
			}	
			ResultSetMetaData rsm=rs.getMetaData();
			int count=rsm.getColumnCount();
			System.out.println(rsm.getColumnLabel(count));
			while(rs.next()){
				columnList=new ArrayList();
				for(int i=1;i<=count;i++){
					columnList.add(rs.getString(i));
				}
				masterDataList.add(columnList);
			}
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return masterDataList;
	}
	
	
	public boolean isCrNoExists(String patCrNo,UserVO userVO)
	{
		List<MasterVerificationVO> masterVerificationVOList=new ArrayList<MasterVerificationVO>();
		MasterVerificationVO masterVerificationVO=new MasterVerificationVO();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.COUNT_BY_CRNO.HRGT_PATIENT_DTL";
		boolean flag=false;
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), patCrNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			if(Integer.parseInt(rs.getString(1))>0)
					flag=true;
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return flag;
	}
	
	
	public List getHospitalCombo() {
		
		List hospitalList=new ArrayList();
		Map _populateMap = new HashMap();
		String query = "";
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "SELECT.HOSPITAL_COMBO.GBLT_HOSPITAL_MST";
		
		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		Connection conn = super.getTransactionContext().getConnection();
		try {
			
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (rs.next()) {
				rs.beforeFirst();
				hospitalList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			throw new HisDataAccessException("GlobalDAO:getHospitalDetail:HelperMethods :: " + e);
		}
		return hospitalList;
	}
	
	/**
	 * Retrieves all the departments of a hospital.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllHospitalsSeatIDWise(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_HOSPTIAL_SEAT_WISE.GBLT_HOSPITAL_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file
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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Hospitals Found");
			}
			else rs.beforeFirst();
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return alRecord;
	}
	
	/**
	 * Retrieves all the departments of a hospital.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllHospitalsSeatIDWiseRegistration(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = Config.GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_HOSPTIAL_SEAT_WISE.GBLT_HOSPITAL_MST_REGISTRATION";

		//first call the getQueryMethod with arguments filename,querykey from prop file
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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Hospitals Found");
			}
			else rs.beforeFirst();
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return alRecord;
	}
	
	/*Added by Vasu Dated on 2017.12.11 for mongoDB Configuration*/
	public Map<String, String> getMongoDBConfigurationsfromDB()
	{
		ResultSet rs = null;
		String query = "";
		List alRecord = null;
		Map populateMAP = new HashMap();
		String filename = Config.QUERY_FILE_FOR_DAO;
		String queryKey = "GET_MONGO_ESSENTIALS.HPMRT_CONFIG_FLAG_MST";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Map<String, String> mpConfigurations = new HashMap<String, String>(); 
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No record for Verification Document found");
			}
			else
			{
				alRecord = new ArrayList();
				try
				{
					alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
					for(Object o : alRecord)
					{
						Entry e = (Entry)o;
						mpConfigurations.put(e.getValue(), e.getLabel());
					}
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("VerificationDocumentsDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
				}
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}

		return mpConfigurations;
	}

}
