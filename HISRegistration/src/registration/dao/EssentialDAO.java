/*
  * Copyright ©.
 */
package registration.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.config.RegistrationConfig;
import registration.config.RegistrationDaoConfig;
import vo.registration.PatientVO;

public class EssentialDAO extends DataAccessObject
{

	Logger log;

	public EssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}



	public List getRelationsList(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_RELATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				//throw new HisRecordNotFoundException("No Relation Found");
				webRs.beforeFirst();
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	public List getDistrictList(UserVO _userVO,String _stateCode)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_DISTRICT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_stateCode", _stateCode,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				//throw new HisRecordNotFoundException("No District Found");
				webRs.beforeFirst();
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	

	public List getOccupationDetail(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_OCCUPATION_MST";
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
		log.error(query + "\n");
		/*log.debug("Execute  query");
		log.error("Error find");
		log.fatal("Fatal Error");*/

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Occupation found. ");
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("GBLT_OCCUPATION_MST:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("occupation Detail" + alRecord);
		return alRecord;
	}
	///////////////////////////////////////////////////////////////////

	public List getRegCategory(UserVO _userVO)
	{
		//String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		//String queryKey = "ESSENTIAL.GBLT_REG_CAT_MST";
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_REG_CAT_MST(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_sup_hosp_code",Config.SUPER_HOSPITAL_CODE);
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
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
		return alRecord;
	}

	public List getGender(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		//String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_GENDER_MST(?,?,?,?)}";
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_GENDER_MST(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			//daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
			{
				throw new HisRecordNotFoundException("No Gender Found");
			}
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	
	public List getCaste(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_CASTE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0);
				//throw new HisRecordNotFoundException("No Caste Found");
				webRs.beforeFirst();
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	////////

	public List getMaritalStatus(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_MARITAL_STATUS_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
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
		return alRecord;	
	}

	public List getReligion(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_RELIGION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0);
				//throw new HisRecordNotFoundException("No Religion Found");
				webRs.beforeFirst();
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	/**
	 * METHOD USED TO RETRIEVE DATA FOR LOCATION 
	 * 
	 */
	public List getLocation(UserVO _userVO)
	{

		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_CITY_LOCATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
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
		return alRecord;
	}
	

	/**
	 * METHOD USED TO RETRIEVE DATA FOR STATE
	 * 
	 */
	public List getState(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_STATE_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "0",1);
			daoObj.setProcInValue(nProcIndex, "p_countryCode", "",2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("EssentialDAO:getState" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	/**
	 * METHOD USED TO RETRIEVE DATA FOR COUNTRY
	 * 
	 */
	public List getCountry(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_COUNTRY_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No Country Found");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	public List getAreaCategory()
	{
		List lstAreaCat = new ArrayList();
		List alList = new ArrayList();
		alList.add("Urban");
		alList.add("0");
		alList.add("Semi-Urban");
		alList.add("1");
		alList.add("Rural");
		alList.add("2");

		for (int i = 0; i < alList.size();)
		{
			Entry objEntry = new Entry((String) alList.get(i++), (String) alList.get(i++));
			lstAreaCat.add(objEntry);
		}
		return lstAreaCat;
	}

	public List getAgeType()
	{

		List lstAgeType = new ArrayList();
		List alList = new ArrayList();

		alList.add("Years");
		alList.add("Y");

		alList.add("Months");
		alList.add("M");

		alList.add("Weeks");
		alList.add("W");

		alList.add("Days");
		alList.add("D");

		for (int i = 0; i < alList.size();)
		{
			Entry objEntry = new Entry((String) alList.get(i++), (String) alList.get(i++));
			lstAgeType.add(objEntry);
		}

		return lstAgeType;
	}

	public List getAddressType(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_ADDRESS_TYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "0",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("EssentialDAO:getAddressType" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	
	
	
	public List getVerificationDocuments(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_VERIFICATION_DOC_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "0",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("EssentialDAO:getVerificationDocuments" + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;

		
	}
	/**
	 * METHOD USED TO RETRIEVE DATA FOR NATIONALITY
	 * 
	 */
	public List getNationality(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_NATIONALITY_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No Nationality Found");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	public List getStateBasedOnCountry(String _countryCode, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_STATE_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_countryCode", _countryCode,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_HOSPITAL_CODE,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No State Found");
			if (strErr.equals("")) 
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(webRs);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException( e.getMessage());
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}

	
	public String getBillAmountBasedOnCategory(String _categoryCode, UserVO objUserVO_p)
	{
		String strAmount=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			/*System.out.println("EssentialDAO :: getBillAmountBasedOnCategory()");
			System.out.println("tariffId :"+objUserVO_p.getTariffID());
			System.out.println("_categoryCode :"+_categoryCode);
			System.out.println("Hosp Code :"+objUserVO_p.getHospitalCode());*/
			daoObj = new HisDAO("Registration","PatientDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_reg_billing_charges(?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			//daoObj.setFuncInValue(funcIndex, 3, RegistrationConfig.REGISTRATION_SERVICE_ID);
			daoObj.setFuncInValue(funcIndex, 3, objUserVO_p.getTariffID());
			daoObj.setFuncInValue(funcIndex, 4, _categoryCode);
			daoObj.setFuncInValue(funcIndex, 5, objUserVO_p.getHospitalCode());
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strAmount = daoObj.getFuncNumeric(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.generateCrNumber()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		if(strAmount.equals(""))
		{
			strAmount="10";
		}
		return strAmount;
	}
	
	/*Start : Surabhi
	 * Reason : to get the bill details of the patients referred from other hospital
	 * date : 7th oct 2016 */
	public String getBillAmountBasedOnCategoryforExt(PatientVO objPatVO_p, UserVO objUserVO_p)
	{
		String strAmount=new String();
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			/*System.out.println("EssentialDAO :: getBillAmountBasedOnCategory()");
			System.out.println("tariffId :"+objUserVO_p.getTariffID());
			System.out.println("_categoryCode :"+_categoryCode);
			System.out.println("Hosp Code :"+objUserVO_p.getHospitalCode());*/
			daoObj = new HisDAO("Registration","PatientDAO");
			funcIndex = daoObj.setFunction("{? = call pkg_reg_util.fun_reg_billing_charges(?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			//daoObj.setFuncInValue(funcIndex, 3, RegistrationConfig.REGISTRATION_SERVICE_ID);
			daoObj.setFuncInValue(funcIndex, 3, objUserVO_p.getTariffID());
			daoObj.setFuncInValue(funcIndex, 4, objPatVO_p.getPatPrimaryCatCode());
			daoObj.setFuncInValue(funcIndex, 5, objPatVO_p.getStrHospCode());
			daoObj.setFuncOutValue(funcIndex,3);
			
			daoObj.executeFuncForNumeric(funcIndex);
			strAmount = daoObj.getFuncNumeric(funcIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new HisDataAccessException("PatientDAO.generateCrNumber()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		if(strAmount.equals(""))
		{
			strAmount="10";
		}
		return strAmount;
	}
	
	//end

public String getUnitWorkingDays(String unitCode,UserVO _userVO)
{

	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.UNIT_WORKING_DAYS_FROM_DUAL"; 	  
	

	
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),unitCode);
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
	String msg;	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	log.info(query);		 	 
 	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
		if(!rs.next())
		{
			throw new HisRecordNotFoundException("");
		}
		rs.beforeFirst();
 	    rs.next();

 	   msg=rs.getString(1); 
 	  
 	    
	}
	catch(Exception e){
		e.printStackTrace();
		
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return msg;
}

	
}//end class
