/*
  * Copyright ©.
 */
package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.GetPatientDtlJob;
import hisglobal.tools.RSTree;
import hisglobal.tools.Tree;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DeathMannerMasterVO;
import hisglobal.vo.DepartmentMasterVO;
import hisglobal.vo.DeptUnitRosterVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientCategoryVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.ShiftMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

//import oracle.jdbc.OracleTypes;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jscape.inet.sftp.requests.Request;

import disaster.DisasterConfig;
import registration.RegistrationConfig;
import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;
//import disaster.DisasterConfig;

public class EssentialDAO extends DataAccessObject implements EssentialDAOi
{

	Logger log;

	public EssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		log = LogManager.getLogger(this.getClass());
	}

	public List getPrimaryCat(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName_old = "{call PKG_REG_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?,?,?)}";
		String strProcName = "{call pkg_clinical_util_view.proc_gblt_patient_cat_mst(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid", "1",2);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hoscode",Config.SUPER_USER_HOSPITAL_CODE,3);
			daoObj.setProcInValue(nProcIndex, "p_gnum_hoscode_local",_userVO.getHospitalCode(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			
			/*daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_sup_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,3);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_charge_type_id", "0",4);
			daoObj.setProcInValue(nProcIndex, "p_moduleId", _userVO.getModuleId(),5);
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId(),6);
			daoObj.setProcInValue(nProcIndex, "p_effect_from", "",7);
			daoObj.setProcInValue(nProcIndex, "p_effect_TO", "",8);
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);*/


						daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
			{
				throw new HisRecordNotFoundException("No Category Found");
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	public List<PatientCategoryVO> getPrimaryCatDetailList(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		ValueObject[] vo ={};
		List<PatientCategoryVO> lst = new ArrayList<PatientCategoryVO>();

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "3");
			daoObj.setProcInValue(nProcIndex, "p_sup_hosp_code",Config.SUPER_USER_HOSPITAL_CODE);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_charge_type_id", "0");
			daoObj.setProcInValue(nProcIndex, "p_moduleId", _userVO.getModuleId());
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId());
			daoObj.setProcInValue(nProcIndex, "p_effect_from", "");
			daoObj.setProcInValue(nProcIndex, "p_effect_TO", "");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				webRs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(PatientCategoryVO.class, webRs);
				for(ValueObject v : vo)
					lst.add((PatientCategoryVO)v);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return lst;
	}

	//* Get Primary Category Detial VOs List 
	public List getPrimaryCatDetailVOs(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_PATIENT_CAT_MST.DETAIL_VOS";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), RegistrationConfig.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
				throw new HisRecordNotFoundException( "No Records for Patient Category found. Either there are No Records in Database or No Records against your Seat Id exist  ");
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
			rs.beforeFirst();
			while(rs.next())
			{
				PatientCategoryVO vo= new PatientCategoryVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				alRecord.add(vo);
			}			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	////////////////primary category with expiry days//////////////////////////
	public List getPrimaryCatWithExpiryDays(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.PRIMARY.EXPIRY.DAYS.GBLT_PATIENT_CAT_MST";
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

		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.MODULE_ID_REGISTRATION);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for primary category found. Either there are no records in database or no records against your seat id exist  ");
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

			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	/////////////////////////////////////////////////////////////////////////
	/**
	 * METHOD USED TO RETRIEVE DATA FOR SECONDARY CATEGORY BASED ON 
	 * PRIMARY CATEGORY
	 * @deprecated Replaced by overloaded mathod. Secondary Category not dependent on Primary Category.
	 */

	/*public List getSecondaryCat(String  _PrimaryCatCode,UserVO _userVO)
	{
	  ResultSet rs=null;    		   	
	 	  String query =  "" ;
	 	  Map populateMAP =new HashMap();    		 	  	
	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	  String queryKey="ESSENTIAL.GBLT_PATIENT_CAT_MST.SECONDARY";
	  
	  //first call the getQueryMethod with arguments filename,querykey from prop file    		 	  
	  try{
	      query =HelperMethodsDAO.getQuery(filename,queryKey); 
	     }
	  catch(Exception e)
	  {	
		 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	  }    		 	  
	 	  System.out.println("query"+query);   
	 	  Sequence sq=new Sequence();
	 	  populateMAP.put(sq.next(),_PrimaryCatCode);
	 	  populateMAP.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);
	 	  populateMAP.put(sq.next(),_userVO.getSeatId());
	 	  
	  try{
	       rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	       if(!rs.next()){
	    	   throw new HisRecordNotFoundException("Record against specified Primary category Not found");		 	    	 	    	 
	 	     } 	
	    }
	  catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				return new ArrayList();
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);     		 		  
	  }    		 	      		 	  
	  List alRecord = new ArrayList();    		 	  
	  try{
		  rs.beforeFirst(); 
		  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
	     }
	  catch(Exception e)
	  {
		throw new HisDataAccessException("getSecondaryCat:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
	  }
	  System.out.println("alRecord sec. cat"+alRecord);
	 	  return alRecord;        
	 }*/

	/**
	 * METHOD USED TO RETRIEVE DATA FOR SECONDARY CATEGORY.
	 */
	public List getSecondaryCat(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_PATIENT_CAT_MST";
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
		populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_SECONDARY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for treatment category found. Either there are no records in database or no records against your seat id exist  ");
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
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	///////////////////////get secondary category with expiry days/////

	public List getSecondaryCatWithExpiryDays(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.SECONDARY.EXPIRY.DAYS.GBLT_PATIENT_CAT_MST";
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
		populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_SECONDARY);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.MODULE_ID_REGISTRATION);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Records For Treatment Category Found. Either There Are No Records In Database Or No Records Against Your Seat Id Exist  ");
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
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
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
			daoObj.setProcInValue(nProcIndex, "p_sup_hosp_code",Config.SUPER_USER_HOSPITAL_CODE);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	public List getGender(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_GENDER_MST(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			//daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public List getEducation(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_EDUCATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
			{
				throw new HisRecordNotFoundException("No Education Found");
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
			
				daoObj.free();
				daoObj = null;
			
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	public List getUser()
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.GBLT_USER_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for User found. Either there are no records in database or no records against ur seat id exist ");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	////////////

	public List getAllDept(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.GBLT_DEPARTMENT_MST_1";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(),_userVO.getHospitalCode());
	    populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Department found. Either there are no records in database or no records against ur seat id exist ");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	public List getDeptUnit(UserVO _userVO,String unitType,String moduleId)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_HGBT_DEPTUNIT_MST(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_rosterType", unitType,2);
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId(),3);
			daoObj.setProcInValue(nProcIndex, "p_moduleId", moduleId,4);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No Department Unit Found");
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	/**
	 * METHOD USED TO RETRIEVE DATA FOR DEPARTMENT (LATER ON IT WIL BE BASED ON USER)
	 * 
	 */
	/*public List getDepartment(UserVO _userVO) 
	  {
	  	  //_userVO.get seatid to be obtained from userVO

	  	  ResultSet rs=null;    		   	
	   	  String query =  "" ;
	   	  Map populateMAP =new HashMap();    		 	  	
	 	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	 	  String queryKey="ESSENTIAL.GBLT_DEPARTMENT_MST";
	 	  
	 	  //first call the getQueryMethod with arguments filename,querykey from prop file    		 	  
	 	  try{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	     }
	 	  catch(Exception e)
	 	  {	
	 		 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	 	  }    		 	  
	 	  System.out.println("query"+query);
	 	  Sequence sq=new Sequence();
	 	  populateMAP.put(sq.next(),RegistrationConfig.IS_VALID_ACTIVE);		 	  
	 	  //populateMAP.put(sq.next(),_userVO.getSeatId());//set the value for seat id
	 	  
	 	  try{
	 	       rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	 	      if(!rs.next()){
	 			 throw new HisRecordNotFoundException("getDepartment");
	 		 }
	 			 
	 	     }catch(Exception e){
	 		 if(e.getClass()==HisRecordNotFoundException.class){
	 				throw new HisRecordNotFoundException();	
	 			}
	 			else
	 		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	 	    }   		  		 	      		 	  
	 	  List alRecord = new ArrayList();    		 	  
	 	  try{
	   	  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
	 	  }
	 	  catch(Exception e)
	 	  {
	 		throw new HisDataAccessException("DepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
	 	  }
	   	  return alRecord;
	    
	  }*/

	/**
	 * Retrieves data for Department Combo.
	 * Returns only those departments which are active at current time.
	 * @param	_userVO	provides user details
	 * @return	List of the departments active presently
	 */
	public List getDepartment(UserVO _userVO, String _unitType)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		
		Map populateMAP = new HashMap();
		String errorMsg = null;
		List alRecord = new ArrayList();
		
		///for counter mapping
		alRecord=getDepartmentCounterWise( _userVO,  _unitType);
		
		/////in case counter mapping not found
		if (alRecord == null || alRecord.size() == 0)
		{
			try
			{
				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_DEPT_COMBO);
				strProc.addInParameter(1, Types.VARCHAR, Config.IS_VALID_ACTIVE);
				strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(3, Types.VARCHAR, _unitType);
				strProc.addInParameter(4, Types.VARCHAR, _userVO.getSeatId());
				strProc.addInParameter(5, Types.VARCHAR, _userVO.getIpAddress());
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.REF);
	
				strProc.execute(super.getTransactionContext().getConnection());
				errorMsg = (String) strProc.getParameterAt(6);
				rs = (ResultSet) strProc.getParameterAt(7);
			}
			catch (HisException e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("DepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);

			}
		}
		/*  try{
		 	 	if(!rs.next()){
		 	 	throw new HisRecordNotFoundException("No Department with any Units functioning");
			 }		 			 
		     }
		 catch(Exception e){
			        if(e.getClass()==HisRecordNotFoundException.class)
			        {
					   throw new HisRecordNotFoundException(e.getMessage());	
				    }
				  else
			           throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		    }   		 	*/
		//List alRecord = new ArrayList();
		
		
		try
		{
			if (alRecord == null || alRecord.size() == 0)
			{
				throw new HisRecordNotFoundException("Either No Department with any Units functioning Or No Department is Assigned to this Seat Id");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	/**
	 * METHOD USED TO RETRIEVE DATA FOR LOCATION 
	 * 
	 */
	public List getBuilding(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.EST_BUILDING_MST";

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
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No records for Location found");
			}

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
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,3);
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
			
				daoObj.free();
				daoObj = null;
			
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	/**
	 * METHOD USED TO RETRIEVE REFERRING HOSPITAL NAME
	 * 
	 */
	public List getRefHospital(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_HOSPITAL_MST(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_sup_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				throw new HisRecordNotFoundException("No Refer Hospital Found");
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	
	public List getRefHospitalForReferal(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.FOR_REFERAL.GBLT_EXT_INSTITUTE_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), "123");
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException("No Refrence hospital records found in Databas");

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
			throw new HisDataAccessException("RefHospitalDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	
	
	
	

	/**
	 * Retrieves all the departments in which a patient can go for a new visit.
	 * Returns only those departments which are active at current time.
	 * @param	_userVO	provides user details
	 * @return	List of the departments active presently
	 */
	public List getNewDeptVisitDepartment(String _crNo, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_VISIT_DEPARTMENT_LIST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno",_crNo,2);
			daoObj.setProcInValue(nProcIndex, "p_moduleId", Config.MODULE_ID_REGISTRATION,3);
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId(),4);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),5);
			daoObj.setProcInValue(nProcIndex, "p_visit_type",RegistrationConfig.EPISODE_VISIT_TYPE_OPD,6);
			daoObj.setProcInValue(nProcIndex, "p_is_active", "1",7);
			daoObj.setProcInValue(nProcIndex, "p_ip_address", _userVO.getIpAddress(),8);
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println(" _userVO.getSeatId():       "+ _userVO.getSeatId());
			
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
			e.printStackTrace();
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}	
		
		System.out.println("alRecord size  :  "+alRecord.size());
		return alRecord;
	}

	public List getNewDeptToVisitDepartment(String _crNo, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_VISIT_TO_DEPARTMENT_LIST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno",_crNo,2);
			daoObj.setProcInValue(nProcIndex, "p_moduleId", Config.MODULE_ID_REGISTRATION,3);
			daoObj.setProcInValue(nProcIndex, "p_seatId", _userVO.getSeatId(),4);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),5);
			daoObj.setProcInValue(nProcIndex, "p_visit_type",RegistrationConfig.EPISODE_VISIT_TYPE_OPD,6);
			daoObj.setProcInValue(nProcIndex, "p_is_active", "1",7);
			daoObj.setProcInValue(nProcIndex, "p_ip_address", _userVO.getIpAddress(),8);
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			System.out.println(" _userVO.getSeatId():       "+ _userVO.getSeatId());
			
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
			e.printStackTrace();
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}	
		
		System.out.println("alRecord size  :  "+alRecord.size());
		return alRecord;
	}

	
	/**
	 * Retrieves all the departments in which a patient can revisit.
	 * @param	_userVO	provides user details
	 * @return	List of the departments active presently
	 */
	public List getOldDeptVisitDepartment(String _crNo, UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.OLD_DEPT_VISIT.GBLT_DEPARTMENT_MST";
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
		System.out.println("query::" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(),_userVO.getSeatId());//set the value for seat id
		populateMAP.put(sq.next(), _crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISOPEN_TRUE);
		populateMAP.put(sq.next(), _crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			System.out.println("after executing query");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("OldDeptVisitDepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		System.out.println("inside getOldDeptVisitDepartment()...." + alRecord);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;

		
	}

	public List getOptionsCmoRegisterEssential(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_EPISODE_ACTION_MST.EMG";
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
		System.out.println("inside getOptionsCmoRegisterEssential");
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_EMG);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CmoRegisterEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);

		}
		return alRecord;
	}

	//* Getting Death Relation List
	public List getEpisodeDeathEssential(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_RELATION_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query --------- " + query);

		Sequence sq = new Sequence();
		
		try
		{		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException("No Relation Record Found");
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getDepartmentWithCasuality(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_DEPARTMENT_WITH_CASUALITY_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("inside getDepartmentWithCasuality");
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO record found for department with casuality");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RefHospitalDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		/*	finally{
			try{if(true)
				throw new HisRecordNotFoundException("No Department with any Units functioning");
			}
			catch(Exception e){
			throw new HisRecordNotFoundException("No Department with any Units functioning");			
			}}*/
		System.out.println("inside getDepartmentWithCasuality()...." + alRecord);
		return alRecord;
	}

	public Tree getDiagnosisData(UserVO _userVO)
	{
		ResultSet rs = null;
		Tree diagnosisTree = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS_TREE";

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
		System.out.println("query" + query);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
			System.out.println("inside DAO of DIAGNOSIS TREE");
			ResultSetMetaData rsmd = rs.getMetaData();
			int noOfCol = rsmd.getColumnCount();
			RSTree rstree = new RSTree(rs, noOfCol);
			diagnosisTree = rstree.buildTree();
			System.out.println("inside DAO of DIAGNOSIS TREE22");
		}

		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return diagnosisTree;
	}

	public List getWardBasedOnDepartment(String _deptCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_WARD_MST.WARD";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _deptCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		System.out.println("inside getWardBasedOnDepartment()...." + alRecord);
		return alRecord;

	}

	public List getDoctorDeskEssential()
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_EPISODE_ACTION_MST.OPD";
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
		System.out.println("inside getDoctorDeskEssential");
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_OPD);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			System.out.println("alRecord" + alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CmoRegisterEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);

		}
		return alRecord;
	}

	public List getOptionsMlcDtl(UserVO _userVO)
	{
		List optionsMlcDtl = new ArrayList();
		optionsMlcDtl.add(new Entry("Formal MLC", "0"));
		optionsMlcDtl.add(new Entry("MLC by CMO", "1"));
		optionsMlcDtl.add(new Entry("Brought By", "2"));
		return optionsMlcDtl;

	}

	public List getOptionCrNoMlcNO(UserVO _userVO)
	{
		List optionsMlcNoCrNO = new ArrayList();
		optionsMlcNoCrNO.add(new Entry("CR NO", "0"));
		optionsMlcNoCrNO.add(new Entry("MLC NO", "1"));
		return optionsMlcNoCrNO;
	}

	public List getSearchOptions(UserVO _userVO)
	{
		List searchOptions = new ArrayList();
		searchOptions.add(new Entry("By Name", "0"));
		searchOptions.add(new Entry("By Contact Number", "1"));
		searchOptions.add(new Entry("By National ID", "2"));
		return searchOptions;
	}

	/*public List getDeptUnit(String deptCode){
	  System.out.println("inside getDeptUnit");
	  ResultSet rs=null;
	  String query =  "" ;
	  Map populateMAP =new HashMap();
	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	  String queryKey="ESSENTIAL.HGBT_UNIT_MST.UNIT_CHANGE";
	  //first call the getQueryMethod with arguments filename,querykey from prop file
	  
	  try{
		  query =HelperMethodsDAO.getQuery(filename,queryKey);
	  }catch(Exception e){
		  throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	  }
	  System.out.println("query"+query);
	  Sequence sq=new Sequence();
	  populateMAP.put(sq.next(),RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE);
	  populateMAP.put(sq.next(),deptCode);
	  
	  try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		  if(!rs.next())
			  throw new HisRecordNotFoundException("getPrimaryCat");
	  }catch(Exception e){
		  throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	  }
	  
	  List alRecord = new ArrayList();
	  
	  try{
		  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
	  }catch(Exception e){
		  throw new HisDataAccessException("UnitChangeDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
	  }
	  System.out.println("alRecord....units available for change::"+alRecord);
	  return alRecord;   
	}*/

	public List getDeptUnit(String deptUnitCode,UserVO _userVO)
	{
		System.out.println("inside getDeptUnit");
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_UNIT_MST.UNIT_CHANGE";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		String deptCode = "";
		System.out.println("deptCode......1111.....:: " + deptCode);
		deptCode = deptUnitCode.substring(0, 3);
		System.out.println("deptCode......1111.....:: " + deptCode);
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(),RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE);
		populateMAP.put(sq.next(), deptCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Unit Found");
		}
		catch (Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UnitChangeDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getNameValues(String deptCode, String roomCode, String deptUnitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.SELECT_NAME_VALUES_DISPLAY";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		//String deptCode=deptUnitCode.substring(3, 6);
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

		populateMAP.put(sq.next(), roomCode);
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), deptCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		//populateMAP.put(sq.next(),RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE);
		//populateMAP.put(sq.next(),deptCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("getNameValues");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		//Map arrMAP =new HashMap();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfResultSet(rs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getNameValuesEmergency(String deptCode, String deptUnitCode)
	{
		ResultSet rs = null;
		String query = "";

		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.SELECT_NAME_VALUES_DISPLAY_EMERGENCY";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		//String deptCode=deptUnitCode.substring(3, 6);
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), deptCode);
		//populateMAP.put(sq.next(),RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE);
		//populateMAP.put(sq.next(),deptCode);
		populateMAP.put(sq.next(), deptUnitCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("getNameValues");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		//Map arrMAP =new HashMap();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfResultSet(rs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getRegCategoryEssential(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_REG_CAT_MST";
		//first call the getQueryMethod with arguments filename,querykey from prop file
		//String deptCode=deptUnitCode.substring(3, 6);
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("getNameValues");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		//Map arrMAP =new HashMap();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	/**
	 * Retrieves all the departments from which a patient has been referred to some other department.
	 * @param	_crNo The String specifying value of CR No
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getRefFromDepartment(String _crNo, UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.REFERRED_FROM_DEPT.HRGT_EPISODE_DTL";

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
		populateMAP.put(sq.next(), _crNo);
		populateMAP.put(sq.next(), RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);
		populateMAP.put(sq.next(), _crNo);

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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getRefFromDepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	/**
	 * Retrieves all the departments to which a patient has been referred from some other department.
	 * @param	_crNo The String specifying value of CR No
	 * @param	_userVO	Provides user details
	 * @return	List of the departments.
	 */
	public List getRefToDepartment(String _crNo, UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.REFERRED_TO_DEPT.HRGT_EPISODE_REFFER_DTL";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.IS_REFERRED_IN_OUT_REFER_INTERNAL);
		populateMAP.put(sq.next(), _crNo);

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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getRefToDepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	/**
	 * Retrieves all the departments of a hospital.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllDepartment(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_DEPT.GBLT_DEPARTMENT_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);

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
				throw new HisRecordNotFoundException("No Department Found");
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

	public List getUnitBasedOnDepartment(String _deptCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_UNIT_MST.DEPT";
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

		populateMAP.put(sq.next(), _deptCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
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
	
	public List getUnitBasedOnSpeciality(UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_UNIT_MST.SPECIALITY";
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record Not Found");
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

	public List getDeptBasedOnWeekday(String _WeekDay, UserVO _userVO)
	{

		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HOPT_ROSTER_MST.DEPT_WEEKDAY";
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
		populateMAP.put(sq.next(), _WeekDay);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified WeekDAy Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return alRecord;
	}

	// Death Manner List
	public List getDeathMannerEssential(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_DEATH_MANNER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Death Manner Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getDiagnosisTypeEssential(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_DIAGNOSIS_TYPE_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("NationalityDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getAllRooms(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ROOMS.HGBT_ROOM_MST";
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

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Rooms Found");
			}
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
			throw new HisDataAccessException("NationalityDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;

	}

	public List getShiftEssential(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_SHIFT_MST";

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

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.SHIFT_TYPE_OPD);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Shift Found");
			}
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
			alRecord = this.populateMapList(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Shift Master:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getEmployeeAsConsultant(String designationMappingProcessId,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ISCONSULTANT_VAL.PSRT_EMPLOYEE_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), designationMappingProcessId);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("getEmployeeAsConsultant");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException("No Consultant Found");
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
			throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,3);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	public String getBillAmountBasedOnCategory(String _categoryCode, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_BILLING_REG_CHARGES(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String amount="";
		
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_serviceId", RegistrationConfig.REGISTRATION_SERVICE_ID,2);
			daoObj.setProcInValue(nProcIndex, "p_tariffId", _userVO.getTariffID(),3);
			daoObj.setProcInValue(nProcIndex, "p_catCode", _categoryCode,4);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				if (!webRs.next())
				{
					amount = "0";
					return amount;
				}
				else
				{
					amount = webRs.getString(1);
				}
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		
		return amount;
	}
	
	public String checkBplDetails(String _bplcardNo, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strFunc = "";
		strFunc = "{? = call REG_FUNCTION.FUNC_CHECK_BPL_DETAILS(?,?,?)}";
		int nfuncIndex = 0;
		String bplCount="";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO.checkBplDetails()");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2,"1");
			daoObj.setFuncInValue(nfuncIndex, 3, _userVO.getHospitalCode());
			daoObj.setFuncInValue(nfuncIndex, 4, _bplcardNo);
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			bplCount = daoObj.getFuncString(nfuncIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}		
		
		return bplCount;
	}
	
	public WebRowSet getBplSearchDetails(BPLDetailsVO bplVO, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_BPL_SEARCH_DTL(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "p_BPLCardNo", bplVO.getPatBPLCardNo());
			daoObj.setProcInValue(nProcIndex, "p_Member", bplVO.getMemberName());
			daoObj.setProcInValue(nProcIndex, "p_familyHead", bplVO.getFamilyHeadName());
			daoObj.setProcInValue(nProcIndex, "p_BPLFamilyId", bplVO.getBplFamilyId());
			daoObj.setProcInValue(nProcIndex, "p_areCat", bplVO.getPatIsUrban());
			daoObj.setProcInValue(nProcIndex, "p_familyHeadRelation", bplVO.getBplRelationCode());
			daoObj.setProcInValue(nProcIndex, "p_gender", bplVO.getPatGenderCode());
			daoObj.setProcInValue(nProcIndex, "p_hospCode", _userVO.getHospitalCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");			
		} 
		catch (Exception e) 
		{
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}		
		
		return webRs;
	}
	
	public String[] checkDuplicateRegistration(PatientVO patVO, UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strFunc = "";
		strFunc = "{? = call REG_FUNCTION.checkDuplicate(?,?,?,?,?,?)}";
		int nfuncIndex = 0;
		String strErr = "";
		String isDup="";
		String[] crNo=null;
		boolean isDuplicate=false;
		Request _rq;
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO.checkBplDetails()");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2,"1");
			daoObj.setFuncInValue(nfuncIndex, 3,patVO.getPatFirstName()+patVO.getPatMiddleName()+patVO.getPatLastName()+
					patVO.getPatCasteCode()+patVO.getPatGuardianName()+patVO.getPatGenderCode());
			daoObj.setFuncInValue(nfuncIndex, 4,patVO.getIsActualDob());
			daoObj.setFuncInValue(nfuncIndex, 5,patVO.getPatAge());
			daoObj.setFuncInValue(nfuncIndex, 6,patVO.getPatAgeUnit());
			daoObj.setFuncInValue(nfuncIndex, 7,patVO.getPatDOB());
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			isDup = daoObj.getFuncString(nfuncIndex);
			if(!isDup.equals(null))
				crNo= isDup.split("#");
			
			/*for(int i=0;i<crNo.length;i++)
			{
				System.out.println(crNo[i]);
				
			}*/
			
			
			
			
			if (isDup!=null) 
			{
				isDuplicate=true;
				throw new HisDuplicateRecordException("Duplicate Registration");
			}
		} 
		catch(HisDuplicateRecordException e)
		{
			
		}
		catch (Exception e) 
		{			
		
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}		
		
		return crNo;
	}

	public String getBillAmountBasedOnDeptGrouping(String _categoryCode, String _fromDept, String _toDept,String entryDate, UserVO _userVO)
	{
		
		
		
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.REG_CHARGE.REG_DEPT_CHARGES";
		String amount;
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
		populateMAP.put(sq.next(), RegistrationConfig.REGISTRATION_SERVICE_ID);
		populateMAP.put(sq.next(), _userVO.getTariffID());
		populateMAP.put(sq.next(), _categoryCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.REGISTRATION_BILLING_GROUPING_FLAG);
		populateMAP.put(sq.next(), _fromDept);
		populateMAP.put(sq.next(), _toDept);
		populateMAP.put(sq.next(), entryDate);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				amount = "0";
				return amount;
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			amount = rs.getString(1);
			if(amount==null || amount.equals("")){
				amount="0";
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);

		}

		return amount;
	}

	public String getBillAmountBasedOnUnitGrouping(String _categoryCode, String _fromUnit, String _toUnit, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.REG_CHARGE.REG_UNIT_CHARGES";
		String amount;
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
		populateMAP.put(sq.next(), RegistrationConfig.REGISTRATION_SERVICE_ID);
		populateMAP.put(sq.next(), _userVO.getTariffID());
		populateMAP.put(sq.next(), _categoryCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.REGISTRATION_BILLING_GROUPING_FLAG);
		populateMAP.put(sq.next(), _fromUnit);
		populateMAP.put(sq.next(), _toUnit);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				amount = "0";
				return amount;
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			amount = rs.getString(1);
			if(amount==null || amount.equals("")){
				amount="0";
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);

		}

		return amount;
	}

	public List getUnitsByType(UserVO _userVO, String _unitType)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String errorMsg = null;
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL_SP_CLINIC";

		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_UNIT_COMBO);
			strProc.addInParameter(1, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, _unitType);
			strProc.addInParameter(4, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(5, Types.VARCHAR, _userVO.getIpAddress());
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(6);
			rs = (ResultSet) strProc.getParameterAt(7);
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);

		}

		try
		{
			if (alRecord == null || alRecord.size() == 0)
			{
				throw new HisRecordNotFoundException("Either No Department with any Units functioning Or No Unit Assigned to this Seat Id");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return alRecord;

	}

	public List getNewVisitUnitsWithSpeciality(UserVO _userVO, String crNo)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL_GBLT_DEPARTMENT_MST_NEW_VISIT_SP_CLINIC";
		List alRecord = new ArrayList();
		
		////for counter wise special clinic mapping
		alRecord=getNewVisitUnitsWithSpecialityCounterWise( _userVO,  crNo);
		
		///if counter mapping not found
		if(alRecord==null || alRecord.size()==0)
		{
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either Patient has visited all the Units assigned to this Seat Id Or No Special Unit assigned to this Seat Id");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
/*			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);*/
		}

		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		}
		return alRecord;
	}

	public List getEmgVisitUnitsWithCasualty(UserVO _userVO, String crNo)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL_GBLT_DEPARTMENT_MST_EMG_VISIT";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
	/*	populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());*/

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Either Patient has Visited all the Units Assigned to this SeatId Or No Units Assigned to this Seat Id");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getUnitsWithCasuality(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_UNIT_MST.UNITTYPE";

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
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_CASUALITY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		populateMAP.put(sq.next(), _userVO.getSeatId());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Units With Casuality Found");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getUnitConsultant(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ISCONSULTANTDOCT_LIST.PSRT_EMPLOYEE_MST";

		// first call the getQueryMethod with arguments filename,query key from prop file
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);  //#Changed by Akash Singh, Due to table changed in G5, dated on 20-07-2015 
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getUnitConsultant  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public String[] getExpiryDtlForRenewal(String deptUnitCode)
	{
		ResultSet rs = null;
		String query = "";
		String[] epiryDtl;
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.EXPIRY_DTL.HGBT_UNIT_MST";

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
		populateMAP.put(sq.next(), deptUnitCode);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record");
			}
			rs.beforeFirst();
			epiryDtl = new String[3];
			int i = 0;
			while (rs.next())
			{
				epiryDtl[0] = rs.getString(1);
				epiryDtl[1] = rs.getString(2);
				epiryDtl[2] = rs.getString(3);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return epiryDtl;
	}

	public String getNextDate()
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.NEXT_EXPIRY_DATE_DUAL";

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
		populateMAP.put(sq.next(), Config.HOSPITAL_RENEWAL_EXPIRY_DAY);

		String nextExpiryDate = "";
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			nextExpiryDate = rs.getString(1);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return nextExpiryDate;
	}

	public String getNextExpiryDate(String _expiryDate)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.NEXT_EXPIRY_DATE_FROM_DUAL";

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
		populateMAP.put(sq.next(), _expiryDate);
		populateMAP.put(sq.next(), _expiryDate);

		String nextExpiryDate = "";
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			nextExpiryDate = rs.getString(1);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return nextExpiryDate;
	}

	
	public List getAllUnitBasedOnSeatID(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.UNIT.BASED.ON.SEATID.HGBT_UNIT_MST";

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

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getSeatId());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Unit Found against this SEAT ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getEpisodeActionList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.EPISODE.ACTION.GBLT_EPISODE_ACTION_MST";

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

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Episode Action Found in the database or against your SEAT ID");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getIcdCodeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ICD.CODE.HGBT_ICD_DISEASE_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Episode Action Found in the database or against your SEAT ID");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getDiagnosisTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS.TYPE.HGBT_DIAGNOSIS_TYPE_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Diagnosis Type Found in the database or against your SEAT ID");
			}

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
			throw new HisDataAccessException();
		}
		return alRecord;
	}

	public List getIcdCodes(String searchIcdCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.BY.ICD.CODE.HGBT_ICD_DISEASE_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), searchIcdCode + "%");
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No such ICD code Found in the database ");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getDiseaseName(String _searchDisease, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.BY.DISEASE.NAME.HGBT_ICD_DISEASE_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _searchDisease + "%");
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No such disease  Found in the database ");
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getOpdUser(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.OPD.GBLT_USER_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode()); 
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for User found. Either there are no records in database or no records against ur seat id exist ");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getIcdCodeBasedOnDept(String _deptCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HGBT_DEPT_ICD_MST.DEPT";
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
		populateMAP.put(sq.next(), _deptCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return alRecord;
	}

	public List getDoctorNameBasedOnUnit(String _unitCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.PSRT_EMPLOYEE_MST.UNIT";

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
		populateMAP.put(sq.next(), _unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		return alRecord;
	}

	

	public List getSeason(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.HRGT_REG_SEASON_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Records For Season Found");
			}

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
			throw new HisDataAccessException("StateDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getShiftForRegistration(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.FOR_REG.GBLT_SHIFT_MST";

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
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.SHIFT_TYPE_REGISTRATION);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Shift Found");
			}
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
			throw new HisDataAccessException("Shift Master:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getAllDeptList(UserVO _userVO)

	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.DEPT.GBLT_DEPARTMENT_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
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
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

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
	// Patient Status List
	/**
	## 		Modification Log		:Hospital Code Removed				
	##		Modify Date				: 19-01-2015
	##		Reason	(CR/PRS)		: CR [Hospital code removed from the Table HRGT_PAT_STATUS_MST]
	##		Modify By				: Akash Singh
	*/
	public List getPatientStatusList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.PAT_STATUS_LIST.HRGT_PAT_STATUS_MST";

		// first call the getQueryMethod with arguments filename,query key from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//System.out.println("query ---------> " + query);
		Sequence sq = new Sequence();

		try
		{
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getPatientStatusList  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Status Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	// Emergency Case List
	public List getEmergencyCasesList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.EMER_CASE_LIST.HRGT_EMRGENCY_CASE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getEmergencyCasesList  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Emergency Case Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	
	public String getUnitDiagnosisTypeCode(String _unitCode,UserVO _userVO)
	{
			ResultSet rs=null;    		   	
	   	  String query =  "" ;
	   	  Map populateMAP =new HashMap();    		 	  	
	 	  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	 	  String queryKey="SELECT.DIAGNOSIS.TYPE.CODE.HGBT_UNIT_MST";
	 	  String diagnosisTypeCode="";
	 	      		 	  
	 	  try{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
	 	     }
	 	  catch(Exception e)
	 	  {	
	 		 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
	 	  }    		 	  
	 	  Sequence sq=new Sequence();
	 	  
	 	  
	 	  populateMAP.put(sq.next(),_unitCode);
	 	  populateMAP.put(sq.next(),_userVO.getHospitalCode());
	 	  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	 	  populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
	 	  try{
	 		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
	 		  			 
	     }catch(Exception e){
		
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }	 	 	 	    		 	      		 	  
	 	  
		try {
			rs.beforeFirst();
        	if(rs.next())
        	{
        		
        		diagnosisTypeCode=rs.getString(1);
        	}
		} catch (SQLException e) {
			e.printStackTrace();
			 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	
		} 	  
	 	
	   	  return diagnosisTypeCode;  		
	
	}
	
	public List getIcdDiagnosisCodeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "";		
		//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS.CODE.HGBT_ICD_DISEASE_MST"; // fetching data from ICD_DISEASE_MST

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

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Icd found");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdCodeList" + e);
		}

		return alRecord;
	}

	// //List Hospital DiagnosisCode////

	public List getHospitalDiagnosisCodeList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = "";
		//String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.DIAGNOSIS.CODE.HGBT_HOSPITAL_DISEASE_MST";
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

		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Hospital Code found ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	public List getConsultantForUnit(String _unitCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.CONSULTANT.HGBT_UNIT_CONSULTANT_MST";

		// first call the getQueryMethod with arguments filename,query key from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getUnitConsultant  " + e);
		} 	  	 	  

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}  




/**
## 		Modification Log		: saveHandoverToDetail					
##		Modify Date				: 07-01-2015	
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh 
*/	


	public List getRelationsList(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_CLINICAL_GBL_VIEW.PROC_GBLT_RELATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", Config.SUPER_USER_HOSPITAL_CODE,2);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public List getPatientCategoryList(UserVO _userVO)
	{

		ResultSet rs=null;
    	String query =  "" ;
    	Map populateMAP =new HashMap();
    	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    	String queryKey="ESSENTIAL.GBLT_PATIENT_CAT_MST.PRIMARY_OLD";
    	
    	//first call the getQueryMethod with arguments filename,querykey from prop file
    	try
    	{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(),RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY);
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
    	
    		    	
    	try{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next()){
    			throw new HisRecordNotFoundException("");
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}
    	List alRecord = new ArrayList();
    	try{
    		alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
    	}
    	catch(Exception e){
    		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	}
    	return alRecord;
	
	}
	
	public List getRegCategoryList(UserVO _userVO)
	{

		ResultSet rs=null;
    	String query =  "" ;
    	Map populateMAP =new HashMap();
    	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    	String queryKey="ESSENTIAL.GBLT_REG_CAT_MST_LIST";
    	
    	//first call the getQueryMethod with arguments filename,querykey from prop file
    	try
    	{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    
    	Sequence sq=new Sequence();
    	
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	
    		    	
    	try{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next()){
    			throw new HisRecordNotFoundException("");
    		}
    	}
    	catch(Exception e){
    		if(e.getClass()==HisRecordNotFoundException.class){
    			throw new HisRecordNotFoundException();
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}
    	List alRecord = new ArrayList();
    	try{
    		alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
    	}
    	catch(Exception e){
    		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	}
    	return alRecord;
	
	}

	public List getAllUnit(UserVO _userVO)
	{

		ResultSet rs=null;
    	String query =  "" ;
    	Map populateMAP =new HashMap();
    	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    	String queryKey="ESSENTIAL.ALL.HGBT_UNIT_MST";
    	
    	//first call the getQueryMethod with arguments filename,querykey from prop file
    	try
    	{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    
    	Sequence sq=new Sequence();
    	
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    		    	
    	try
    	{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next())
    		{
    			throw new HisRecordNotFoundException("");
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisRecordNotFoundException.class)
    		{
    			throw new HisRecordNotFoundException();
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}
    	List alRecord = new ArrayList();
    	try
    	{
    		alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	}
    	return alRecord;
	}

	
	public List getMlcNoBasedOnCrNo(String crNo,UserVO _userVO)
	{

		ResultSet rs=null;
    	String query =  "" ;
    	Map populateMAP =new HashMap();
    	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    	String queryKey="SELECT.MLC_NO.HRGT_PATIENT_MLC_DTL";
    	
    	try
    	{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    
    	Sequence sq=new Sequence();
    	
    	populateMAP.put(sq.next(),crNo);
    	populateMAP.put(sq.next(),_userVO.getHospitalCode());
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    		    	
    	try
    	{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next())
    		{
    			throw new HisRecordNotFoundException("");
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisRecordNotFoundException.class)
    		{
    			throw new HisRecordNotFoundException();
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}
    	List alRecord = new ArrayList();
    	try
    	{
    		alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	}
    	return alRecord;
	}
	
	
	
	public String checkValidMlcCrNo(String crNo,UserVO _userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="CHECK_VALID_MLC_CR_NO.HRGT_PATIENT_MLC_DTL";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),crNo);
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
        	if(!rs.next())
        		throw new HisApplicationExecutionException("");
            rs.first();
            System.out.println(">>>>>"+rs.getString(1));
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}


	// Getting List of All Injury Types
	public List getInjuryTypes(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ALL_INJURY_TYPES.HRGT_INJURY_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//System.out.println("query ----------> " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getInjuryTypes  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Injury Type Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public String getDefaultStateName(UserVO _userVO)
	{

		ResultSet rs=null;
    	String query =  "" ;
    	Map populateMAP =new HashMap();
    	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
    	String queryKey="SELECT.DEFAULTSTATE.GBLT_STATE_MST";
    	
    	try
    	{
    		query =HelperMethodsDAO.getQuery(filename,queryKey);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    	}
    
    	Sequence sq=new Sequence();
    	populateMAP.put(sq.next(),RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE);
    	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);   	
    		    	
    	try
    	{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next())
    		{
    			throw new HisRecordNotFoundException("No State Found");
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisRecordNotFoundException.class)
    		{
    			throw new HisRecordNotFoundException(e.getMessage());
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}
    	String defaultStateName=null;
    	try
    	{
    		defaultStateName=rs.getString(1);
    	}
    	catch(Exception e)
    	{
    		throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);
    	}
    	return defaultStateName;
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
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,3);
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public   RoomMasterVO getEssentialToModifyRoom(String code,UserVO _uservo) {	
		
		RoomMasterVO roomMasterMasterVO=new RoomMasterVO();
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.ROOMMASTERDETAIL.HGBT_ROOM_MST";
		
		Sequence sq=new Sequence();
		populateMap.put(sq.next(),code);	
		populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
			 	 
		
		try{
			
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
					
	 	    if(!rs.next()){
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else{
	 	    	rs.beforeFirst();
	 	    	
	 	    	while(rs.next()){
	 	    		
	 	 	    	HelperMethods.populateVOfrmRS(roomMasterMasterVO,rs); 	
	 	 	    	
	 	    	}
	 	    	
	 	    }
	 	    
		}
		catch(Exception e){
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }			 	 	
		return roomMasterMasterVO;

	}

	
/*	public List getDistrictList(UserVO _userVO,String _stateCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.DISTRICT.GBLT_DISTRICT_MST";		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(),_stateCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getInjuryTypes  " + e);
		}
		try
    	{
    		rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
    		if(!rs.next())
    		{
    			throw new HisRecordNotFoundException("No District Found");
    		}
    	}
    	catch(Exception e)
    	{
    		if(e.getClass()==HisRecordNotFoundException.class)
    		{
    			throw new HisRecordNotFoundException();
    		}
    		else
    			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    	}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}*/
	
	public CityLocationMasterVO getDetailOnBasisOfLocation(String locCode,UserVO userVO)
	{
		CityLocationMasterVO cityLocMstVO=new CityLocationMasterVO();
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT_LOC_DETAIL_BASIS_OF_CODE_GBLT_CITY_LOCATION_MST"; 
		
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),locCode);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		Connection conn =super.getTransactionContext().getConnection();
		try
		{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);	 	 
		
		try
		{
			
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
					
	 	    if(!rs.next())
	 	    {
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else
	 	    {
	 	    	rs.beforeFirst();
	 	    	
	 	    	while(rs.next())
	 	    	{
	 	    		HelperMethods.populateVOfrmRS(cityLocMstVO,rs);
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
		return cityLocMstVO;
	}

	
	public String calculateRenewalDateForExpiry(String _expiryDate){
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.EXPIRY_DATE.FOR.STAMPING.AND.RENEWAL.FROM_DUAL";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _expiryDate);
		populateMAP.put(sq.next(), _expiryDate);

		String nextExpiryDate = "";
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			nextExpiryDate = rs.getString(1);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return nextExpiryDate;

	}

	 public List populateMapList(ResultSet rs)throws SQLException
	    {
	    	List temp=new ArrayList();
	    	rs.beforeFirst();    	
	    		while(rs.next())
	        	{    		
	    			ShiftMasterVO _shiftMasterVO=new ShiftMasterVO();
	        		HelperMethods.populateVOfrmRS(_shiftMasterVO,rs);    		
	        		temp.add(_shiftMasterVO);
	        	}	    
	    	return temp;
	    }

	
	public List getSpecialClinicUnitList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL_SPECIAL_CLINIC_UNIT.HGBT_UNIT_MST";


		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");
		
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Special Unit Found.");
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

			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		return alRecord;
	}

	public String getBillAmountAndIdRequiredBasedOnCategory(String _categoryCode,UserVO _userVO){
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.BILL.AMOUNT.IS_ID_REQUIRED.HBLT_CHARGE_MST.GBLT_PATIENT_CAT_MST";

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

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), RegistrationConfig.REGISTRATION_SERVICE_ID);
		populateMAP.put(sq.next(), _userVO.getTariffID());
		populateMAP.put(sq.next(), _categoryCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _categoryCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);

		String idRequired = "";
		String amount="";
		String amountAndIdRequired="";
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
			amount=rs.getString(1);
			idRequired = rs.getString(2);
			}
			amountAndIdRequired=amount+"^"+idRequired;

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return amountAndIdRequired;
	}

	/**
	 * METHOD USED TO RETRIEVE BLOCK BASED ON BUILDING
	 * 
	 */

	public List getBlockComboBasedOnBuilding(UserVO _userVO,String _buildingCode)
	{
		 ResultSet rs=null;    		   	
	 	  String query =  "" ;
	 	  Map populateMAP =new HashMap();    		 	  	
		  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		  String queryKey="SELECT.BLOCK.EST_BLOCK_MST";
		  
		    		 	  
		  try{
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		     }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }    		 	  
		 	
		  Sequence sq=new Sequence();  
		  
		 
		  populateMAP.put(sq.next(), _buildingCode);
		  populateMAP.put(sq.next(),_userVO.getHospitalCode());
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  
		  try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(!rs.next()){
			 throw new HisRecordNotFoundException("Block Not Found");
		            	 }
			 
	   }catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	  }	 	 
		    		 	      		 	  
		  List alRecord = new ArrayList();    		 	  
		  try{
	 	  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		  }
		  catch(Exception e)
		  {
			throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
		  }
	 	  return alRecord; 
	}
	
	public List getFloorComboBasedOnBlock(UserVO _userVO,String _blockCode)
	{
		 ResultSet rs=null;    		   	
	 	  String query =  "" ;
	 	  Map populateMAP =new HashMap();    		 	  	
		  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		  String queryKey="SELECT.FLOOR.EST_FLOOR_MST";
		  
		    		 	  
		  try{
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		     }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }    		 	  
		 	
		  Sequence sq=new Sequence();  
		  
		 
		  populateMAP.put(sq.next(), _blockCode);
		  populateMAP.put(sq.next(),_userVO.getHospitalCode());
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  
		  try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(!rs.next()){
			 throw new HisRecordNotFoundException("Floor Not Found");
		 }
			 
	   }catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	  }	 	 
		    		 	      		 	  
		  List alRecord = new ArrayList();    		 	  
		  try{
	 	  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		  }
		  catch(Exception e)
		  {
			throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
		  }
	 	  return alRecord; 
	}
	
	public List getRoomComboBasedOnFloor(UserVO _userVO,String _floorCode)
	{
		 ResultSet rs=null;    		   	
	 	  String query =  "" ;
	 	  Map populateMAP =new HashMap();    		 	  	
		  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		  String queryKey="SELECT.ROOM.EST_ROOM_MST";
		  
		    		 	  
		  try{
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		     }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }    		 	  
		 	
		  Sequence sq=new Sequence();  
		  
		  
		  populateMAP.put(sq.next(), _floorCode);
		  populateMAP.put(sq.next(),_userVO.getHospitalCode());
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  
		  try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(!rs.next()){
			 throw new HisRecordNotFoundException("Room Not Found");
		 }
			 
	   }catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	  }	 	 
		    		 	      		 	  
		  List alRecord = new ArrayList();    		 	  
		  try{
	 	  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		  }
		  catch(Exception e)
		  {
			throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
		  }
	 	  return alRecord; 
	}

	public List getLocationType(UserVO _userVO)
	{
		 ResultSet rs=null;    		   	
	 	  String query =  "" ;
	 	  Map populateMAP =new HashMap();    		 	  	
		  String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		  String queryKey="SELECT.GBLT_LOCATION_TYPE_MST";
		  
		    		 	  
		  try{
		      query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		     }
		  catch(Exception e)
		  {	
			 throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		  }    		 	  
		 	
		  Sequence sq=new Sequence();  
		  
		  
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		
		  
		  try{
		  rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		 if(!rs.next()){
			 throw new HisRecordNotFoundException("Location Type Not Found");
		 }
			 
	   }catch(Exception e){
		 if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
		 throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	  }	 	 
		    		 	      		 	  
		  List alRecord = new ArrayList();    		 	  
		  try{
	 	  alRecord= HelperMethodsDAO.getAlOfEntryObjects(rs);
		  }
		  catch(Exception e)
		  {
			throw new HisDataAccessException("CountryDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)"+e);    		 		 
		  }
	 	  return alRecord; 
	}
	
	public List getRegistrationCategory()

    {

          System.out.println("inside get srea cat");

          List lstRegCat = new ArrayList();

          List alList = new ArrayList();

          alList.add("Normal");

          alList.add(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL);

          alList.add("Special");

          alList.add(RegistrationConfig.PATIENT_REG_CATEGORY_SPECIAL);

          alList.add("Emergency");

          alList.add(RegistrationConfig.PATIENT_REG_CATEGORY_EMERGENCY);



          for (int i = 0; i < alList.size();)

          {

                Entry objEntry = new Entry((String) alList.get(i++), (String) alList.get(i++));

                lstRegCat.add(objEntry);

          }

          return lstRegCat;

    }
	
	public void checkDuplicateAdd(String _smode,String _pNo,String _code,String _fullName,String _shortName ,UserVO _userVO) {
		
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.CHEK_DUPLICATE_RECORD"; 	  
		
		String isValid=Config.IS_VALID_ACTIVE;
		
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		populateMap.put(sq.next(),_smode);
		populateMap.put(sq.next(),_pNo);
		populateMap.put(sq.next(),_code);
		populateMap.put(sq.next(),_fullName);
		populateMap.put(sq.next(),_shortName);
		
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
	 	    rs.next();

	 	   msg=rs.getString(1); 
	 	   boolean flag=msg.equals("0");
	 	    if(!(msg.equals("0")))
	 	   throw new HisDuplicateRecordException(msg);
	 	    
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisDuplicateRecordException.class){
				throw new HisDuplicateRecordException(e.getMessage());	
			}
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }			 	 	
		
	}

 public String marqueMsg(UserVO _userVO)
 {
		String message="";
		String errorMsg="";
	try {
			ResultSet rs=null;
			Connection conn =super.getTransactionContext().getConnection();
			Procedure proc = new Procedure(RegistrationDaoConfig.PROCEDURE_MARQUE_MSG);
			proc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			proc.addInParameter(2, Types.VARCHAR, _userVO.getSeatId());
			proc.addOutParameter(3,Types.VARCHAR);
			proc.addOutParameter(4, Types.REF);
			proc.execute(conn);
			errorMsg = (String) proc.getParameterAt(3);
			rs = (ResultSet) proc.getParameterAt(4);
			int i=1;
			while(rs.next())
			{
				message=message+" * "+(String)rs.getString(1);
				i++;
			}
			System.out.println("_patientVO.getPatAge():::::...."+ errorMsg);
		} catch (Exception e) {
				throw new HisDataAccessException("" + e);
		}
		return message;
	}

	
	public List getCmoList(UserVO _userVO) {
		
		List cmoList=new ArrayList();
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.CMO_LIST.PIST_EMP_PERSONNEL_DTL"; 	  
		
		Sequence sq=new Sequence();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			cmoList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisDuplicateRecordException.class){
				throw new HisDuplicateRecordException(e.getMessage());	
			}
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return cmoList;	
	}
	
public List getShiftList(UserVO _userVO) {
		
		List shiftList=new ArrayList();
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.SHIFT.GBLT_SHIFT_MST"; 	  
		
		Sequence sq=new Sequence();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);	 
			shiftList=HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisDuplicateRecordException.class){
				throw new HisDuplicateRecordException(e.getMessage());	
			}
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return shiftList;	
	}

public List getDepartmentType(UserVO _userVO) {
	
	List list=new ArrayList();
	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.DEPARTMENT_TYPE.GBLT_DEPARTMENT_TYPE"; 	  
	
	Sequence sq=new Sequence();
	populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
	
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
			throw new HisRecordNotFoundException("Department Type Not Found");
		}
		list=HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch(Exception e){
		e.printStackTrace();
		
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException(e.getMessage());	
		}
		else			 		
			throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	}			 	 	
	return list;	
}
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
	
	/* query to Fetch Discalimer by deptUnit */
	public String getDisclaimerByUnit(String unitCode,UserVO _userVO)
	{
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.HRGT_DISCLAIMER_MST.UNITWISE"; 	  
		
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		populateMap.put(sq.next(),unitCode);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		String disclaimer=null;	
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
				disclaimer=null;
			}
			else{
				rs.beforeFirst();
				rs.next();
				disclaimer=rs.getString(1);
			}	
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return disclaimer;
	}

	/* query to Fetch Discalimer by department */
	public String getDisclaimerByDept(String deptCode,UserVO _userVO)
	{
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.HRGT_DISCLAIMER_MST.DEPTWISE"; 	  
		
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		populateMap.put(sq.next(),deptCode);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		String disclaimer=null;	
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
				disclaimer=null;
			}
			else{
				rs.beforeFirst();
				rs.next();
				disclaimer=rs.getString(1);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return disclaimer;
	}
	
	/* query to Fetch Default Discalimer */
	public String getDefaultDisclaimer(String usablityFlag,UserVO _userVO)
	{
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey="SELECT.FOR.REGISTRATION.HRGT_DISCLAIMER_MST.DEFAULT"; 	  
		
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		populateMap.put(sq.next(),usablityFlag);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		String disclaimer=null;	
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
				disclaimer=null;
			}
			else{
				rs.beforeFirst();
				rs.next();
				disclaimer=rs.getString(1);
			}	 
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return disclaimer;
	}
	
	//Query to fetch all patients for scheduling and searching purpose
	
	public ResultSet getAllPatients()
	{
		
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.ALL_PATIENT_FOR_SEARCHING.HRGT_DAILY_PATIENT_DTL"; 	  
		
		Sequence sq=new Sequence();
		PatientVO patientVo=null;
		List patientVoList=new ArrayList();
		ResultSet rs =null;
	/*	populateMap.put(sq.next(),_userVO.getHospitalCode());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);*/
		
		String patientDetail=null;	
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try{
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if(!rs.next())
			{
				patientDetail=null;
				//throw new HisRecordNotFoundException("No Patient found"); 
			}
			else{
				rs.beforeFirst();
				
				/*while(rs.next()){
					patientVo=new PatientVO();
					HelperMethods.populateVOfrmRS(patientVo, rs);
					//GetPatientDtlJob jobClass=new GetPatientDtlJob();
					//GetPatientDtlJob.addTODocument(patientVo);
					patientVoList.add(patientVo);
					
				}*/
				GetPatientDtlJob.addTODocument(rs);
				//System.out.println("finished addDocument");
			}	 
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return rs;
	}
	
	public List getReferDepartmentList(UserVO _userVO)
	{
		
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_REFER_DEPARTMENT_LIST(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);			
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_dept_type_clinical",RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE,3);
			daoObj.setProcInValue(nProcIndex, "p_is_active",Config.IS_VALID_ACTIVE,4);
			daoObj.setProcInValue(nProcIndex, "p_is_inactive",Config.IS_VALID_INACTIVE,5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
				//throw new HisRecordNotFoundException("No Refer Department Found");
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;

	}
	
	public List getAllUnitByType(UserVO _userVO, String _unitType)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List al=new ArrayList();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_UNIT_BY_TYPE.HGBT_UNIT_MST";

		Sequence sq=new Sequence();
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_unitType);
		
		String patientDetail=null;	
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try{
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if(!rs.next())
			{
				
				throw new HisRecordNotFoundException("No Unit Found"); 
			}
			else{
				rs.beforeFirst();
				al=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
				 
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return al;

	}
	
	public List getAllUnitByTypeForReferal(String _crNo,UserVO _userVO, String _unitType)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List al=new ArrayList();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_UNIT_BY_TYPE_FOR_REFERAL.HGBT_UNIT_MST";

		Sequence sq=new Sequence();
		/*populateMAP.put(sq.next(),_userVO.getHospitalCode());
		//populateMAP.put(sq.next(),"123");
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_unitType);*/
		
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),Config.IS_VALID_INACTIVE);
		//populateMAP.put(sq.next(),_unitType);
		populateMAP.put(sq.next(),_crNo);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		String patientDetail=null;	
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try{
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if(!rs.next())
			{
				
				//throw new HisRecordNotFoundException("No Unit Found"); 
			}
			else{
				rs.beforeFirst();
				al=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
				 
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return al;

	}
	
	public List getAllDepartmentWithGeneralUnit(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List al=new ArrayList();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_DEPARTMENT_WITH_GENERAL_UNIT.GBLT_DEPARTMENT_MST";

		Sequence sq=new Sequence();
		
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		populateMAP.put(sq.next(),RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		populateMAP.put(sq.next(),RegistrationConfig.UNIT_TYPE_GENERAL);
		
		String patientDetail=null;	
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try{
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if(!rs.next())
			{
				
				throw new HisRecordNotFoundException("No Department With General Unit Found"); 
			}
			else{
				rs.beforeFirst();
				al=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
				 
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return al;

	}
	
	public List getAllDepartmentWithGeneralUnitForReferal(String _crNO,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		List al=new ArrayList();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_DEPARTMENT_WITH_GENERAL_UNIT_FOR_REFERAL.GBLT_DEPARTMENT_MST";

		Sequence sq=new Sequence();
		
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		//populateMAP.put(sq.next(),"123");
		populateMAP.put(sq.next(),RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		populateMAP.put(sq.next(),RegistrationConfig.UNIT_TYPE_GENERAL);
		populateMAP.put(sq.next(),_crNO);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		String patientDetail=null;	
		Connection conn =super.getTransactionContext().getConnection();
		
		try{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}catch(Exception e){
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try{
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			if(!rs.next())
			{
				
				//throw new HisRecordNotFoundException("No Department With General Unit Found"); 
			}
			else{
				rs.beforeFirst();
				al=HelperMethodsDAO.getAlOfEntryObjects(rs);
				}
				 
		}
		catch(Exception e){
			e.printStackTrace();
			
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}			 	 	
		return al;

	}
	
	public List getPatConditionMacro(String processId,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "GET_MACRO_FOR_PATIENT_CONDITION.HGBT_MACRO_MST";
		Sequence sq=new Sequence();
		
		populateMAP.put(sq.next(),userVO.getHospitalCode());
		populateMAP.put(sq.next(),processId);
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		
		log.info(query);		 	 
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	// Getting List of All Death Manner
	public DeathMannerMasterVO[] getDeathManner(UserVO _userVO)
	{
		ResultSet rs = null;
		ValueObject[] vo ={};
		DeathMannerMasterVO[] patDeathMannerVO;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ALL_DEATH_MANNER.HGBT_DEATH_MANNER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query ----------> " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getDeathManner  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Death Manner Record Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(DeathMannerMasterVO.class, rs);
			patDeathMannerVO = new DeathMannerMasterVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patDeathMannerVO[i] = (DeathMannerMasterVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveAllByCrNo::Episode Details:: " + e);
		}
		return patDeathMannerVO;
		
	}
	
	// Getting List of All Injury Nature
	public List getInjuryNature(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ALL_INJURY_NATURE.HRGT_INJURY_NATURE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//System.out.println("query ----------> " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getInjuryNature  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Injury Nature Record Found");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List getDeathMannerList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.DEATH_MANNER_WITH_ACCIDENTAL_FLAG.HGBT_DEATH_MANNER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query ----------> " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getDeathManner  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Death Manner Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public String getOnSetDateNRecentVisitDate(String crNo,String epiCode,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT_ONSETDATE_N_RECENTVISITDATE.HRGT_DAILY_PATIENT_DTL";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),crNo);
		populateMap.put(sq.next(),epiCode);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("No Record Found ... ");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	
	//getting the list of clinical department
	public List getClinicalDeptList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.CLINICAL_DEPT.GBLT_DEPARTMENT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//System.out.println("query ----------> " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getInjuryNature  " + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Department Record Found");
			}
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
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List getVerificationDocumetByCatCode(String catCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		List alRecord = null;
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "VER_DOC_BY_CAT_CODE.GBLT_VERIFICATION_DOCUMENT_MST";
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), catCode);
		
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

		return alRecord;
	}


	public List getDisasterList(UserVO _uservo) 
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_GBLT_GENDER_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE);
			daoObj.setProcInValue(nProcIndex, "p_disStatus",DisasterConfig.DISASTER_STATUS_ACTIVATED);
			
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;	
					
	}


	public List getUnitsBasedOnWeekDay(String day,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.UNITS_BASED_ON_WEEKDAY";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), day);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    //populateMAP.put(sq.next(), MrdConfig.WEEK_OF_MONTH);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No records for Department found. Either there are no records in database or no records against ur seat id exist ");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}



	//getting the age range
	public List getAgeRangeList(UserVO _userVO)
	{
		List ageRangeList=null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.AGE_RANGE.HGBT_AGE_CLASSIFICATION_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException( "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		Sequence sq = new Sequence();
		
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populateMAP::getAgeRangeList  " + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				ageRangeList=new ArrayList();
			}
				
			ageRangeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return ageRangeList;
	}
	
	
	public List getUnitRoom(String _unitRoom, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ROOM.HRGT_DEPT_UNIT_ROOM_MST";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _unitRoom);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Room Not Found For the Selected Unit");
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
		System.out.println("alRecord sec. cat" + alRecord);
		return alRecord;
	}
	
	
	public List getPreviousDiagnosisForYellowSlip(EpisodeVO episodeVO, UserVO _userVO)
	{
		List<EpisodeDiagnosisVO> previousDiagnosisList=null;
		EpisodeDiagnosisVO diagnosisVO=new EpisodeDiagnosisVO();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.PREVIOUS_DIAGNOSIS.HRGT_EPISODE_DIAGNOSIS_DTL";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), episodeVO.getPatCrNo());
		populateMAP.put(sq.next(), episodeVO.getEpisodeCode());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		//populateMAP.put(sq.next(), OpdConfig.DIAGNOSIS_IS_REPEAT_NEW);
		//populateMAP.put(sq.next(), OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				previousDiagnosisList= new ArrayList<EpisodeDiagnosisVO>();
			}
			rs.beforeFirst();
				 
			while(rs.next()){
				diagnosisVO=new EpisodeDiagnosisVO();
				HelperMethods.populateVOfrmRS(diagnosisVO, rs);
				previousDiagnosisList.add(diagnosisVO);
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
		
		return previousDiagnosisList;
	}
	
	public List getUnitConsultants(String _unitRoom, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.CONSULTANT.HOU_1_2.HGBT_UNIT_CONSULTANT_MST";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _unitRoom);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found For the Selected Unit");
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
		System.out.println("alRecord sec. cat" + alRecord);
		return alRecord;
	}

	
	public List getPatientPrimaryCategory(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT_PRIMARY_CATEGORY.GBLT_PATIENT_CAT_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			
			
			populateMAP.put(sq.next(), RegistrationConfig.PATIENT_CAT_TYPE_PRIMARY);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			//rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Primary Category Found");
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
		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RefHospitalDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List getCategoryWiseMappedDocument(String _categoryCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT_CATEGORYWISE_MAPPED_VERIFICATION_DOC.GBLT_PATCAT_VERIFICATION_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
			Sequence sq = new Sequence();
			
			

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _categoryCode);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
		//		throw new HisRecordNotFoundException("No Patient Primary Category Found");
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
		List alRecord = new ArrayList();
		try
		{
			rs.beforeFirst();
		
		//if(rs.next())
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RefHospitalDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	
	public List getCategoryWiseUnMappedDocument(String _categoryCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT_CATEGORYWISE_UNMAPPED_VERIFICATION_DOC.GBLT_PATCAT_VERIFICATION_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), _categoryCode);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
		//		throw new HisRecordNotFoundException("No Patient Primary Category Found");
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
		List alRecord = new ArrayList();
		try
		{
			rs.beforeFirst();
			
		//	if(rs.next()){
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
				
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RefHospitalDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public  ReportVO[] getReportForHosPatStat(ReportVO _vo,UserVO _userVO)
		{
			ReportVO[] reportVOArray=null;
			String query = "";
			ValueObject[] vo=null;
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
			String queryKey = "SELECT_REPORTQUERY_HOSPATSTAT_REPORT";
			
			String selectField="";
			String groupField="";
			String q1="";
			
			if(_vo.getChoice().equals(Config.CHOICE_DATE_WISE))
			{
				selectField="TO_CHAR(a.epi_date,'dd-Mon-YYYY') as epiDate ,";
				groupField="a.HRGDT_EPISODE_DATE";
			}
			if(_vo.getChoice().equals(Config.CHOICE_MONTH_WISE))
			{
				selectField="TO_CHAR(a.epi_date,'Month-YYYY') as epiDate ,";
				groupField="a.HRGDT_EPISODE_DATE,'mm'";
			}
			if(_vo.getChoice().equals(Config.CHOICE_YEAR_WISE))
			{
				selectField="TO_CHAR(a.epi_date,'YYYY') as epiDate ,";
				groupField="a.HRGDT_EPISODE_DATE,'YYYY'";
			}
			try
			{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("query" + query);
			
			q1="select "+ selectField + query;
			q1=q1.replace("@", groupField);
							
			
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			try
			{
			//	populateMAP.put(sq.next(), selectField);
			//	populateMAP.put(sq.next(), groupField);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), _vo.getFromDate());
				populateMAP.put(sq.next(), _vo.getToDate());
				populateMAP.put(sq.next(), _vo.getUnitCode());
			//	populateMAP.put(sq.next(), groupField);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("populateMAP::" + e);
			}
			try
			{System.out.println(q1);
				rs = HelperMethodsDAO.executeQuery(conn, q1, populateMAP);
				
				if(!rs.next())
				{
					throw new HisReportDataNotFoundException("No Data Found");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(ReportVO.class, rs);
					System.out.println("length" + vo.length);
					reportVOArray = new ReportVO[vo.length];
				//	System.out.println("donorDetailVOs.length:: " + donorDetailVOs.length);
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						reportVOArray[i] = (ReportVO) vo[i];
						
					}
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Data Found");	
				}
				/*else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);*/			 
			 }
			return reportVOArray;
		}
	

	public List getUnitsFromRoster(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "ESSENTIAL.RETRIEVE_UNITS.HOPT_DEPT_UNIT_ROSTER_DTL";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Allotted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		/*
		 * finally{ try{if(true) throw new HisRecordNotFoundException("No Department with any Units functioning"); }
		 * catch(Exception e){ throw new HisRecordNotFoundException("No Department with any Units functioning"); }}
		 */
		return alRecord;
	}

	public List getAllActiveUnitsFromRoster(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "ESSENTIAL.RETRIEVE_ALL_ACTIVE_UNITS.HOPT_DEPT_UNIT_ROSTER_DTL";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}


		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Allotted For This Seat ID");
			}
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
		throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
	/*
	 * finally{ try{if(true) throw new HisRecordNotFoundException("No Department with any Units functioning"); }
	 * catch(Exception e){ throw new HisRecordNotFoundException("No Department with any Units functioning"); }}
	 */
	return alRecord;
	}
	
	
		
	
	public List getRoomsByUnitCode(UserVO _userVO, String deptUnitCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "ESSENTIAL.HGBT_ROOM_MST.RETRIEVE_BY_DEPTUNITCODE";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		populateMAP.put(sq.next(), deptUnitCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Allotted For This Unit");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	public List getAllActiveRoomsByUnitCode(UserVO _userVO, String deptUnitCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "ESSENTIAL.ACTIVE_ROOM_BY_DEPTUNITCODE.HGBT_ROOM_MST";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		populateMAP.put(sq.next(), deptUnitCode);

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Allotted For This Unit");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	public List getParticularRoomDetail(String deptUnitCode,String roomCode,UserVO userVO)
	{
		List deptUnitRosterVOList = new ArrayList();
		DeptUnitRosterVO vo=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.ROOM_DETAIL.HOPT_DEPT_UNIT_ROSTER_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), roomCode);
								
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				vo=new DeptUnitRosterVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				deptUnitRosterVOList.add(vo);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return deptUnitRosterVOList;
	}
	
	public List getTodayVistPatListByRoom(String deptUnitCode,String roomCode,UserVO userVO)
	{
		List dailyPatientVOList = new ArrayList();
		DailyPatientVO vo=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.TODAY_VISIT_PAT_LIST.HRGT_DAILY_PATIENT_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), roomCode);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), roomCode);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
										
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				vo=new DailyPatientVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				dailyPatientVOList.add(vo);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return dailyPatientVOList;
	}

	



public  ReportVO[] getAgeWiseRegReport(ReportVO _reportVO,UserVO _userVO)

	{
		ReportVO[] reportVOArray=null;
		String query = "";
		ValueObject[] vo=null;
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT_REPORTQUERY_AGE_WISE_REPORT";
		
		String selectField="";
		String groupField="";
		String q1="";
		
		if(_reportVO.getChoice().equals(Config.CHOICE_DATE_WISE))
		{
			selectField=" TO_CHAR (episodedate, 'dd-Mon-YYYY') AS epiDate, "; 
			groupField="TO_CHAR (episodedate, 'dd-Mon-YYYY') ";
		}
		if(_reportVO.getChoice().equals(Config.CHOICE_MONTH_WISE))
		{
			selectField="  TO_CHAR (episodedate, 'Mon-YYYY') AS epiDate, ";
			groupField="TO_CHAR (episodedate, 'Mon-YYYY') ";
		}
		if(_reportVO.getChoice().equals(Config.CHOICE_YEAR_WISE))
		{
			selectField="  TO_CHAR (episodedate, 'YYYY') AS epiDate, ";
			groupField="TO_CHAR (episodedate, 'YYYY') ";
		}
		try
		{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("query" + query);
		
		q1="select "+ selectField + query;
		q1=q1.replace("@", groupField);
						
		System.out.println("query q1" + q1);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.IS_OLD_FALSE);
			populateMAP.put(sq.next(), RegistrationConfig.IS_OLD_TRUE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _reportVO.getUnitCode());
			populateMAP.put(sq.next(), _reportVO.getFromDate());
			populateMAP.put(sq.next(), _reportVO.getToDate());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{	System.out.println("q1=  "+q1);
			rs = HelperMethodsDAO.executeQuery(conn, q1, populateMAP);
			
			if(!rs.next())
			{
				throw new HisReportDataNotFoundException("No Data Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(ReportVO.class, rs);
				System.out.println("length" + vo.length);
				reportVOArray = new ReportVO[vo.length];
			//	System.out.println("donorDetailVOs.length:: " + donorDetailVOs.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					reportVOArray[i] = (ReportVO) vo[i];
					
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException("No Data Found");	
			}
			/*else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);*/			 
		 }
		return reportVOArray;
	}


public  ReportVO[] getReportForHosPatStatState(ReportVO _reportVO,UserVO _userVO)
	{
		ReportVO[] reportVOArray=null;
		String query = "";
		ValueObject[] vo=null;
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT_REPORTQUERY_HOSPATSTATSTATE_REPORT";
		
		String selectField="";
		String groupField="";
		String q1="";
		
		if(_reportVO.getChoice().equals(Config.CHOICE_DATE_WISE))
		{
			selectField="TO_CHAR(m.epi_date,'DD-Mon-YYYY') as epiDate, "; 
			groupField="TRUNC(a.hrgdt_episode_date) ";
		}
		if(_reportVO.getChoice().equals(Config.CHOICE_MONTH_WISE))
		{
			selectField=" TO_CHAR(m.epi_date,'Mon-YYYY') as epiDate, ";
			groupField="TRUNC(a.hrgdt_episode_date,'mm') ";
		}
		if(_reportVO.getChoice().equals(Config.CHOICE_YEAR_WISE))
		{
			selectField="TO_CHAR(m.epi_date,'YYYY') as epiDate, ";
			groupField="TRUNC(a.hrgdt_episode_date,'yyyy') ";
		}
		try
		{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("query" + query);
		
		q1="select "+ selectField + query;
		q1=q1.replace("@", groupField);
						
		System.out.println("query q1" + q1);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.IS_OLD_FALSE);
			populateMAP.put(sq.next(), RegistrationConfig.IS_OLD_TRUE);
			populateMAP.put(sq.next(), _reportVO.getFromDate());
			populateMAP.put(sq.next(), _reportVO.getToDate());
			populateMAP.put(sq.next(), _reportVO.getUnitCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{	System.out.println("q1=  "+q1);
			rs = HelperMethodsDAO.executeQuery(conn, q1, populateMAP);
			
			if(!rs.next())
			{
				throw new HisReportDataNotFoundException("No Data Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(ReportVO.class, rs);
				System.out.println("length" + vo.length);
				reportVOArray = new ReportVO[vo.length];
			//	System.out.println("donorDetailVOs.length:: " + donorDetailVOs.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					reportVOArray[i] = (ReportVO) vo[i];
					
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException("No Data Found");	
			}
			/*else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);*/			 
		 }
		return reportVOArray;

	
}

	//get the list of the department unit and room for combo
	public Map getDeptUnitRoom(UserVO userVO)
	{
		List departmentList = new ArrayList();
		List unitList = new ArrayList();
		List roomList = new ArrayList();
		String errorMsg = "";
		ResultSet rs = null;
		Map essentialMap=new HashMap();
		
		////for counter mapping
		essentialMap=getDeptUnitRoomCounterWise(userVO);
		departmentList=(List)essentialMap.get(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT);
		
		if(departmentList==null || departmentList.size()==0)
		{
		Connection conn = super.getTransactionContext().getConnection();
		
		
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_FOR_DEPT_UNIT_ROOM_LIST);
			strProc.addInParameter(1, Types.NUMERIC, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.NUMERIC, userVO.getSeatId());
			strProc.addInParameter(3, Types.NUMERIC, userVO.getModuleId());
			strProc.addInParameter(4, Types.NUMERIC, RegistrationConfig.UNIT_TYPE_GENERAL);
			strProc.addOutParameter(5, Types.VARCHAR);
			strProc.addOutParameter(6, Types.REF);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(5);
			rs = (ResultSet) strProc.getParameterAt(6);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
										
		try
		{
			//if (!rs.next())
			//{
			//	throw new HisRecordNotFoundException("No Department Found");
			//}
			//rs.beforeFirst();
			Entry entry=null;
			while(rs.next()){
				entry=new Entry();
				entry.setValue(rs.getString(1));//departmentCode
				entry.setLabel(rs.getString(2));
				departmentList.add(entry);
				
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(7));//Unit Code#unitType
				entry.setLabel(rs.getString(4));
				unitList.add(entry);
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(5));//unitCode#roomCode
				entry.setLabel(rs.getString(6));
				roomList.add(entry);
			}
			
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, departmentList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT, unitList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ROOM, roomList);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		}
		
		return essentialMap;
	}

	///dept unit room for room wise new departmetn visit
	
	
	public Map getNewDeptDeptUnitRoom(String _crNo,UserVO userVO)
	{
		List departmentList = new ArrayList();
		List unitList = new ArrayList();
		List roomList = new ArrayList();
		String errorMsg = "";
		ResultSet rs = null;
		Map essentialMap=new HashMap();
		
		///for counter mapping
		essentialMap=getNewDeptDeptUnitRoomCounterWise(_crNo, userVO);
		departmentList=(List)essentialMap.get(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT);
		
		if(departmentList==null || departmentList.size()==0)
		{
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_ROOM_WISE_NEW_REGISTRATION_FOR_DEPT_UNIT_ROOM_LIST);
			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, userVO.getSeatId());
			strProc.addInParameter(3, Types.VARCHAR,userVO.getModuleId());
			strProc.addInParameter(4, Types.VARCHAR, RegistrationConfig.UNIT_TYPE_GENERAL);
			strProc.addOutParameter(5, Types.VARCHAR);
			strProc.addOutParameter(6, Types.REF);
			strProc.addInParameter(7, Types.VARCHAR, _crNo);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(5);
			rs = (ResultSet) strProc.getParameterAt(6);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
										
		try
		{
			//if (!rs.next())
			//{
			//	throw new HisRecordNotFoundException("No Department Found");
			//}
			//rs.beforeFirst();
			Entry entry=null;
			while(rs.next()){
				entry=new Entry();
				entry.setValue(rs.getString(1));//departmentCode
				entry.setLabel(rs.getString(2));
				departmentList.add(entry);
				
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(7));//Unit Code#unitType
				entry.setLabel(rs.getString(4));
				unitList.add(entry);
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(5));//unitCode#roomCode
				entry.setLabel(rs.getString(6));
				roomList.add(entry);
			}
			
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, departmentList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT, unitList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ROOM, roomList);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		}
		return essentialMap;
		
	}
	
	
	public List getUnitDateWiseRoomRosterList(String unitCode,String deactivationDate,UserVO _userVO)
	{
		List unitDatewiseRoomList = new ArrayList();
		DeptUnitRosterVO vo=null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.UNIT_DATE_ROOM_DETAIL.HOPT_DEPT_UNIT_ROSTER_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), deactivationDate);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);		
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		populateMAP.put(sq.next(), unitCode);
								
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Room Found");
			}
			rs.beforeFirst();
			while(rs.next()){
				vo=new DeptUnitRosterVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				unitDatewiseRoomList.add(vo);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return unitDatewiseRoomList;
	}
	

	public List getRoomsByUnitOfDayWiseCapacity(String _unitRoom, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ROOM.DAY_WISE_CAPACITY.HRGT_DEPT_UNIT_ROOM_MST";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _unitRoom);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_ROOM_DAY_WISE_CAPACITY_MODE);
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Room Not Found For the Selected Unit");
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
		System.out.println("alRecord sec. cat" + alRecord);
		return alRecord;
	}
	
	public List getRoomsByUnitForRoster(String _unitRoom, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ROOM_ROSTER.HRGT_DEPT_UNIT_ROOM_MST";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _unitRoom);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Room Not Found For the Selected Unit");
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
		System.out.println("alRecord sec. cat" + alRecord);
		return alRecord;
	}
	

	public List getAllCounter(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.GBLT_COUNTER_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
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
			throw new HisDataAccessException(" Master:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	

	public List getUnitRosterWorkingDaysList(String _unitCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT_UNIT_DAYS_LIST.HOPT_DEPT_UNIT_ROSTER_MST";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), _unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Room Not Found For the Selected Unit");
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
		System.out.println("alRecord sec. cat" + alRecord);
		return alRecord;
	}
	
	
	public List getConsultantByUnit(String _unitCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.CONSULTANT_BY_UNIT.PIST_EMP_PERSONNEL_DTL";
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
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		
		populateMAP.put(sq.next(), _unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next())
			//{
				//throw new HisRecordNotFoundException("Room Not Found For the Selected Unit");
			//}
			//else 
				rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		System.out.println("alRecord sec. cat" + alRecord);
		return alRecord;
	}
	
	public List getDepartmentsForFilePrinting(UserVO _userVO)
	{
		
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.FOR_FILE_NO_PRINTING.GBLT_DEPARTMENT_MST";
		List alRecord=new ArrayList();
		    		 	  
		 try{
		     query =HelperMethodsDAO.getQuery(filename,queryKey);
		 }
		 catch(Exception e){
		  throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		 }    		 	  
		 System.out.println("query"+query);
		 Sequence sq=new Sequence();
		 populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);		
		 populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);		
		 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		 populateMAP.put(sq.next(),_userVO.getSeatId());
		 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		 
		 try{
		      rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		     if(!rs.next()){
		    	 throw new HisRecordNotFoundException("No Department with any Units functioning");
		     }
		     else{
		    	 rs.beforeFirst();
		    	 alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		     }
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return alRecord;

	}

	public List getUnitForFileNoPrinting(String departmentCode,UserVO _userVO) {
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.FOR_FILE_NO_PRINTING.HGBT_UNIT_MST";
		List alRecord=new ArrayList();
		    		 	  
		 try{
		     query =HelperMethodsDAO.getQuery(filename,queryKey);
		 }
		 catch(Exception e){
		  throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		 }    		 	  
		 System.out.println("query"+query);
		 Sequence sq=new Sequence();
		 
		 populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);		
		 populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);		
		 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		 populateMAP.put(sq.next(),departmentCode);
		 populateMAP.put(sq.next(),_userVO.getSeatId());
		 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		 populateMAP.put(sq.next(),_userVO.getHospitalCode());
		 
		 try{
		      rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
		     if(!rs.next()){
		    	 throw new HisRecordNotFoundException("No Unit Found");
		     }	
		     else{
		    	 rs.beforeFirst();
		    	 alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		     }
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return alRecord;
	}
	
	public List getAllUsers(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.SELECT_ALL_USER.GBLT_USER_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No User Found");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List getTodayWorkingRoomBasedOnUnit(String departmentUnitCode,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.SELECT_TODAY_WORKING_ROOM.HRGT_DEPT_UNIT_ROOM_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), departmentUnitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No Active Room Found");
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
			rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	
	public List getDeptWhoseFileIsRequired(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "SELECT.WHOSE_FILE_REQUIRED.GBLT_DEPARTMENT_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.FILE_NO_REQUIRED_DEPT_WISE);
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
			rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public List getUnitWhoseFileIsRequired(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "SELECT.WHOSE_FILE_REQUIRED.HGBT_UNIT_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), RegistrationConfig.FILE_NO_REQUIRED_UNIT_WISE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
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
			rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	
	public List getDepartmentCounterWise(UserVO _userVO, String _unitType)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		
		Map populateMAP = new HashMap();
		
		String errorMsg = null;
		List alRecord = new ArrayList();
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_DEPT_COMBO_COUNTER_WISE);
			strProc.addInParameter(1, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, _unitType);
			strProc.addInParameter(4, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(5, Types.VARCHAR, _userVO.getIpAddress());
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(6);
			rs = (ResultSet) strProc.getParameterAt(7);
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);

		}
		
		return alRecord;

	}

	
	public List getNewDeptVisitDepartmentCounterWise(String _crNo, UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		////////for counter wise mapping
		String queryKey="ESSENTIAL.NEW_DEPT_VISIT_COUNTER_WISE.GBLT_DEPARTMENT_MST";
		
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
		
		Sequence seq = new Sequence();
		populateMAP.put(seq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(seq.next(), _userVO.getHospitalCode());
		populateMAP.put(seq.next(), _crNo);
		populateMAP.put(seq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(seq.next(), RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
		populateMAP.put(seq.next(), _userVO.getHospitalCode());
		populateMAP.put(seq.next(), _userVO.getIpAddress());
		populateMAP.put(seq.next(), _userVO.getHospitalCode());
		populateMAP.put(seq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
							
		}
		catch (Exception e)
		{

			 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		List alRecord = new ArrayList();
		try
		{

			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("NewDeptVisitDepartmentDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
				
		return alRecord;
	}

	public List getNewVisitUnitsWithSpecialityCounterWise(UserVO _userVO, String crNo)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.NEW_VISIT_SP_CLINIC_COUNTER_WISE.HGBT_UNIT_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), _userVO.getIpAddress());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
	public Map getDeptUnitRoomCounterWise(UserVO userVO)
	{
		List departmentList = new ArrayList();
		List unitList = new ArrayList();
		List roomList = new ArrayList();
		String errorMsg = "";
		ResultSet rs = null;
		Map essentialMap=new HashMap();
		Connection conn = super.getTransactionContext().getConnection();
		
		
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_DEPT_UNIT_ROOM_LIST_COUNTER_WISE);
			strProc.addInParameter(1, Types.NUMERIC, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.NUMERIC, userVO.getSeatId());
			strProc.addInParameter(3, Types.NUMERIC, userVO.getIpAddress());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
										
		try
		{
			//if (!rs.next())
			//{
			//	throw new HisRecordNotFoundException("No Department Found");
			//}
			//rs.beforeFirst();
			Entry entry=null;
			while(rs.next()){
				entry=new Entry();
				entry.setValue(rs.getString(1));//departmentCode
				entry.setLabel(rs.getString(2));
				departmentList.add(entry);
				
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(7));//Unit Code#unitType
				entry.setLabel(rs.getString(4));
				unitList.add(entry);
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(5));//unitCode#roomCode
				entry.setLabel(rs.getString(6));
				roomList.add(entry);
			}
			
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, departmentList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT, unitList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ROOM, roomList);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return essentialMap;
	}

	public Map getNewDeptDeptUnitRoomCounterWise(String _crNo,UserVO userVO)
	{
		List departmentList = new ArrayList();
		List unitList = new ArrayList();
		List roomList = new ArrayList();
		String errorMsg = "";
		ResultSet rs = null;
		Map essentialMap=new HashMap();
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_NEW_DEPT_DEPT_UNIT_ROOM_LIST_COUNTER_WISE);
			/*strProc.addInParameter(1, Types.NUMERIC, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.NUMERIC, userVO.getSeatId());
			strProc.addInParameter(3, Types.NUMERIC, userVO.getIpAddress());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);
			strProc.addInParameter(6, Types.NUMERIC, _crNo);*/
			
			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, userVO.getSeatId());
			strProc.addInParameter(3, Types.VARCHAR, userVO.getIpAddress());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);
			strProc.addInParameter(6, Types.VARCHAR, _crNo);
			
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
										
		try
		{
			//if (!rs.next())
			//{
			//	throw new HisRecordNotFoundException("No Department Found");
			//}
			//rs.beforeFirst();
			Entry entry=null;
			while(rs.next()){
				entry=new Entry();
				entry.setValue(rs.getString(1));//departmentCode
				entry.setLabel(rs.getString(2));
				departmentList.add(entry);
				
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(7));//Unit Code#unitType
				entry.setLabel(rs.getString(4));
				unitList.add(entry);
				
				entry=new Entry();
				entry.setValue(rs.getString(3)+"#"+rs.getString(5));//unitCode#roomCode
				entry.setLabel(rs.getString(6));
				roomList.add(entry);
			}
			
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT, departmentList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_DEPT_UNIT, unitList);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ROOM, roomList);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
		return essentialMap;
	}

public List getReferDepartmentListForHospital(UserVO _userVO) {

		
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_REFER_DEPARTMENT_LIST(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "2",1);			
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_dept_type_clinical",RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE,3);
			daoObj.setProcInValue(nProcIndex, "p_is_active",Config.IS_VALID_ACTIVE,4);
			daoObj.setProcInValue(nProcIndex, "p_is_inactive",Config.IS_VALID_INACTIVE,5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;

	
	}

	/**
	 * Retrieves all  hospitals.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllHospitals(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_HOSPTIAL.GBLT_HOSPITAL_MST";

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
	public List getAllHospitalsSeatIDWise(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
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

	public List getAllGlobalDepartment(UserVO userVO) {
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_GLOBAL_DEPT.GBLT_DEPARTMENT_MST";

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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);

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
				throw new HisRecordNotFoundException("No Department Found");
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

	public DSSRegistrationVO[] getAgeWiseDSS(DSSRegistrationVO vo) {
	  		   	
		 
		  ValueObject[] valueObject={};
		  DSSRegistrationVO[] dssVO=null;
		  WebRowSet webRs = null;
			HisDAO daoObj = null;
			List alRecord = new ArrayList();
			String strProcName = "{call Pkg_Reg_DSS.PROC_AGE_WISE_DSS_REGISTRATION(?,?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
			String strErr = "";

			try 
			{

			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1");
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", vo.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_department_code", vo.getStrDepartmentCode());
			daoObj.setProcInValue(nProcIndex, "p_patcat_code", vo.getStrPatCatCode());
			daoObj.setProcInValue(nProcIndex, "p_patcaste_code", vo.getStrPatCasteCode());
			daoObj.setProcInValue(nProcIndex, "p_state_code", vo.getStrStateCode());
			daoObj.setProcInValue(nProcIndex, "p_religion_code", vo.getStrReligionCode());
			daoObj.setProcInValue(nProcIndex, "p_from_date", vo.getStrMonYear());
			daoObj.setProcInValue(nProcIndex, "p_to_date", vo.getStrMonYear());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null) strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (!webRs.next())
			{
				throw new HisRecordNotFoundException("Report Data not Found");
			}
			while (webRs.next())
			{
				System.out.println("webRs.getString(1)" + webRs.getString(1));
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
			finally{
				if(daoObj!=null){
					daoObj.free();
				}
				daoObj=null;
			}
		try
		{

			valueObject = HelperMethods.populateVOfrmRS(DSSRegistrationVO.class, webRs);

			dssVO = new DSSRegistrationVO[valueObject.length];
			for (int i = 0; i < valueObject.length; i++)
			{

				dssVO[i] = (DSSRegistrationVO) valueObject[i];

			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return dssVO;

	}

	public String getDSSMinimumMonthYear(String strMode_p, UserVO voUser_p)
	{
		HisDAO daoObj = null;
		String strFunc = "{? = call Pkg_Reg_DSS.get_min_monthyear(?,?)}";
		int nfuncIndex = 0;
		String monthYr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO.getDSSMinimumMonthYear()");

			nfuncIndex = daoObj.setFunction(strFunc);
		
			daoObj.setFuncInValue(nfuncIndex, 2, strMode_p);
			daoObj.setFuncInValue(nfuncIndex, 3, voUser_p.getHospitalCode());
			daoObj.setFuncOutValue(nfuncIndex, 1);
		
			daoObj.executeFunction(nfuncIndex);
		
			monthYr = daoObj.getFuncString(nfuncIndex);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}		
		return monthYr;
	}

	public List<DSSRegistrationVO> getDSSAgeWiseStatReportData(DSSReportVO vo)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_DSS.proc_age_wise_stats_dss_reg(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try
		{
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1");
			daoObj.setProcInValue(nProcIndex, "p_is_consolidated", vo.getStrIsConsolidated());
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", vo.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_department_code", vo.getStrDepartmentCode());
			daoObj.setProcInValue(nProcIndex, "p_patcat_code", vo.getStrPatCatCode());
			daoObj.setProcInValue(nProcIndex, "p_patcaste_code", vo.getStrPatCasteCode());
			daoObj.setProcInValue(nProcIndex, "p_religion_code", vo.getStrReligionCode());
			daoObj.setProcInValue(nProcIndex, "p_state_code", vo.getStrStateCode());
			daoObj.setProcInValue(nProcIndex, "p_duration_mode", vo.getStrDurationMode());
			daoObj.setProcInValue(nProcIndex, "p_from_year", vo.getStrFromYear());
			daoObj.setProcInValue(nProcIndex, "p_to_year", vo.getStrToYear());
			daoObj.setProcInValue(nProcIndex, "p_from_mon", vo.getStrFromMonth());
			daoObj.setProcInValue(nProcIndex, "p_to_mon", vo.getStrToMonth());
			daoObj.setProcInValue(nProcIndex, "p_from_date", vo.getStrFromDate());
			daoObj.setProcInValue(nProcIndex, "p_to_date", vo.getStrToDate());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null) strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (!webRs.next())
			{
				throw new HisRecordNotFoundException("Report Data not Found");
			}
			while (webRs.next())
			{
				System.out.println("webRs.getString(1)" + webRs.getString(1));
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}

		ValueObject[] valueObject = {};
		List<DSSRegistrationVO> lstDSSData = new ArrayList<DSSRegistrationVO>();
		try
		{
			webRs.beforeFirst();
			valueObject = HelperMethods.populateVOfrmRS(DSSRegistrationVO.class, webRs);
			for (ValueObject v: valueObject)
				lstDSSData.add((DSSRegistrationVO) v);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDSSData;
	}
	
	public List<DSSEpisodeVO> getDSSOPDStatsReportData(DSSReportVO vo)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_DSS.proc_opd_stats_dss(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try
		{
			daoObj = new HisDAO("Registration", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1");
			daoObj.setProcInValue(nProcIndex, "p_is_consolidated", vo.getStrIsConsolidated());
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", vo.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_department_code", (vo.getStrDepartmentCode()==null)?"":vo.getStrDepartmentCode());
			daoObj.setProcInValue(nProcIndex, "p_patcat_code", (vo.getStrPatCatCode()==null)?"":vo.getStrPatCatCode());
			daoObj.setProcInValue(nProcIndex, "p_patcaste_code", (vo.getStrPatCasteCode()==null)?"":vo.getStrPatCasteCode());
			daoObj.setProcInValue(nProcIndex, "p_religion_code", (vo.getStrReligionCode()==null)?"":vo.getStrReligionCode());
			daoObj.setProcInValue(nProcIndex, "p_state_code", (vo.getStrStateCode()==null)?"":vo.getStrStateCode());
			daoObj.setProcInValue(nProcIndex, "p_duration_mode", vo.getStrDurationMode());
			daoObj.setProcInValue(nProcIndex, "p_from_year", vo.getStrFromYear());
			daoObj.setProcInValue(nProcIndex, "p_to_year", vo.getStrToYear());
			daoObj.setProcInValue(nProcIndex, "p_from_mon", vo.getStrFromMonth());
			daoObj.setProcInValue(nProcIndex, "p_to_mon", vo.getStrToMonth());
			daoObj.setProcInValue(nProcIndex, "p_from_date", vo.getStrFromDate());
			daoObj.setProcInValue(nProcIndex, "p_to_date", vo.getStrToDate());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null) strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (!webRs.next())
			{
				throw new HisRecordNotFoundException("Report Data not Found");
			}
			while (webRs.next())
			{
				System.out.println("webRs.getString(1)" + webRs.getString(1));
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}

		ValueObject[] valueObject = {};
		List<DSSEpisodeVO> lstDSSData = new ArrayList<DSSEpisodeVO>();
		try
		{
			webRs.beforeFirst();
			valueObject = HelperMethods.populateVOfrmRS(DSSEpisodeVO.class, webRs);
			for (ValueObject v: valueObject)
				lstDSSData.add((DSSEpisodeVO) v);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return lstDSSData;
	}

	public List getUsersBasedOnHospital(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String querykey = "ESSENTIAL.SELECT_ALL_USER.GBLT_USER_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, querykey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No User Found");
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
			throw new HisDataAccessException("UserDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}	
	

	/**
	 * By Deepika Gaba
	 * Append Date:- 04-April-2012 
	 * Modifying Date:- 
	 * Used For:-Ambulance Wise Case Brought Report 
	 * Module:- Emergency (HIS Project)
	 * 
	 */
	public List getVehicleList(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_HTRT_VEHICLE_MST(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public  String getPatCrNoFrmMCTSNo(String mctsNo,UserVO _userVO
	)

{
EpisodeVO[] reportVOArray=null;
List<EpisodeVO> lstEpisodeVO = new ArrayList<EpisodeVO>();

String query = "";
ValueObject[] vo=null;
ResultSet rs;
Map populateMAP = new HashMap();
Sequence sq = new Sequence();
Connection conn=super.getTransactionContext().getConnection();

String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
String queryKey = "SELECT.PATCRNO.FROM_MCTS_MO.HRGT_PATIENT_DTL";

String selectField="";
String groupField="";

String patcrno="";
try
{
query = HelperMethodsDAO.getQuery(filename, queryKey);



				
System.out.println("query q1" + query);
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
}
try
{
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
    populateMAP.put(sq.next(), mctsNo);
    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	
}
catch (Exception e)
{
	throw new HisApplicationExecutionException("populateMAP::" + e);
}
try
{	System.out.println("q1=  "+query);
	rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
	
	if(!rs.next())
	{
		//throw new HisReportDataNotFoundException("No Data Found");
	}
	else
	{
		patcrno= rs.getString(1);
	}
	
	
}
catch(Exception e)
{
	e.printStackTrace();	
	if(e.getClass()==HisRecordNotFoundException.class)
	{
		throw new HisRecordNotFoundException("No Data Found");	
	}
	/*else			 		
	 throw new HisDataAccessException("HisDataAccessException:: "+e);*/			 
 }
return patcrno;
}
	
	
	public  List<EpisodeVO> getPatVisitDtlReport(String patcrno,UserVO _userVO,String fromDate,String toDate, String Choice)

	{
		EpisodeVO[] reportVOArray=null;
		List<EpisodeVO> lstEpisodeVO = new ArrayList<EpisodeVO>();

		String query = "";
		ValueObject[] vo=null;
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT_REPORTQUERY_PAT_VISIT_WISE_DTL";
		
		String selectField="";
		String groupField="";
		
	
		try
		{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		if(!fromDate.equals("")){
			query+=" and trunc(hrgdt_episode_date) >= trunc(to_date('" + fromDate + "','dd-mon-yyyy'))";
		}
		if(!toDate.equals("")){
			query+=" and trunc(hrgdt_episode_date) <= trunc(to_date('" + toDate + "','dd-mon-yyyy'))";
		}
		if(Choice.equals("1"))
			query+= "order by hrgdt_episode_date";
			else
				query+= "order by departmentunit,hrgdt_episode_date";
	
	
						
		System.out.println("query q1" + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		    populateMAP.put(sq.next(), patcrno);
			//populateMAP.put(sq.next(),fromDate);
			//populateMAP.put(sq.next(),toDate );
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{	System.out.println("q1=  "+query);
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if(!rs.next())
			{
			//	throw new HisReportDataNotFoundException("No Data Found");
			}
			else
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
				System.out.println("length" + vo.length);
				reportVOArray = new EpisodeVO[vo.length];
			//	System.out.println("donorDetailVOs.length:: " + donorDetailVOs.length);
				for (int i = 0; i < vo.length; i++) {
					System.out.println("before casting");
					reportVOArray[i] = (EpisodeVO) vo[i];
					lstEpisodeVO.add((EpisodeVO)vo[i]);
					
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException("No Data Found");	
			}
			/*else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);*/			 
		 }
		return lstEpisodeVO;
	}

	public List getExaminationTypeCombo(UserVO _userVO)
	{

		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.proc_hrgt_police_exam_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
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
			e.printStackTrace();
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public List getPoliceCombo(UserVO _userVO)
	{

		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.proc_hrgt_police_station_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
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
			e.printStackTrace();
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}

	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(String strMode_p,
			String strPatCrNo_p, String strEpisodeCode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		HisDAO hisDAO = null;
		
		String strProcName = "{call PKG_REG_VIEW.proc_hrgt_police_exam_req_dtl(?,?,?::numeric,?::numeric,?,		?,?)}";
		
		try
		{//procedure Name
			hisDAO = new HisDAO("Registration","EssentialDAO");
			
			nProcedureIndex = hisDAO.setProcedure(strProcName);

			// Setting and Registering In and Out Parameters 
			hisDAO.setProcInValue(nProcedureIndex, "p_modeval", strMode_p,1);
			hisDAO.setProcInValue(nProcedureIndex, "p_crno", strPatCrNo_p,2);
			hisDAO.setProcInValue(nProcedureIndex, "p_episode_code", strEpisodeCode_p==null|| strEpisodeCode_p.equals("")?"0":strEpisodeCode_p,3);
			hisDAO.setProcInValue(nProcedureIndex, "p_episode_visitNo", strEpisodeVisitNo_p==null|| strEpisodeVisitNo_p.equals("")?"0":strEpisodeVisitNo_p,4);
			hisDAO.setProcInValue(nProcedureIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),5);
	
			hisDAO.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

			// Executing Procedure 
			hisDAO.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO.getString(nProcedureIndex, "err");
			objResSet = hisDAO.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO.getPatientsForPoliceExaminationReqt()::hisDAO.executeProcedure(" +  
					 ",Mode:" +  strMode_p +  ") -> " + e.getMessage());
		}
		finally{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
		}
		List<PatientDetailVO> lst = new ArrayList<PatientDetailVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatientDetailVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatientDetailVO) obj);
		}
			}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO.getPatientsForPoliceExaminationReqt()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	public List<PoliceExaminationReqtDtlVO> getPoliceExaminationReqtDtl(String strMode_p,
			String strPatCrNo_p, String strEpisodeCode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		HisDAO hisDAO = null;
		
		String strProcName = "{call PKG_REG_VIEW.proc_hrgt_police_exam_req_dtl(?,?,?::numeric,?::numeric,?,		?,?)}";
		
		try
		{//procedure Name
			hisDAO = new HisDAO("Registration","EssentialDAO");
			
			nProcedureIndex = hisDAO.setProcedure(strProcName);

			// Setting and Registering In and Out Parameters 
			hisDAO.setProcInValue(nProcedureIndex, "p_modeval", strMode_p,1);
			hisDAO.setProcInValue(nProcedureIndex, "p_crno", strPatCrNo_p,2);
			hisDAO.setProcInValue(nProcedureIndex, "p_episode_code", strEpisodeCode_p==null|| strEpisodeCode_p.equals("")?"0":strEpisodeCode_p,3);
			hisDAO.setProcInValue(nProcedureIndex, "p_episode_visitNo", strEpisodeVisitNo_p==null|| strEpisodeVisitNo_p.equals("")?"0":strEpisodeVisitNo_p,4);
			hisDAO.setProcInValue(nProcedureIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),5);
	
			hisDAO.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

			// Executing Procedure 
			hisDAO.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO.getString(nProcedureIndex, "err");
			objResSet = hisDAO.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO.getPoliceExaminationReqtDtl()::hisDAO.executeProcedure(" + 
					 ",Mode:" +  strMode_p +  ") -> " + e.getMessage());
		}
		finally{
			if(hisDAO!=null){
				hisDAO.free();
			}
			hisDAO=null;
		}

		List<PoliceExaminationReqtDtlVO> lst = new ArrayList<PoliceExaminationReqtDtlVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PoliceExaminationReqtDtlVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PoliceExaminationReqtDtlVO) obj);
		}
			}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO.getPoliceExaminationReqtDtl()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
	
	//Added by Singaravelan on 28-Jul-2014 to show the Salutation List in Employee Master
	public List getSalutation(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_CLINICAL_GBL_VIEW.PROC_PIST_SALUTATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",Config.SUPER_USER_HOSPITAL_CODE,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
			{
				throw new HisRecordNotFoundException("No Salutation Found");
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	//Added by Singaravelan on 28-Jul-2014 to show the Designation List in Employee Master
	public List getDesignationList(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_CLINICAL_GBL_VIEW.PROC_PIST_DESIGNATION_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(webRs.size()<=0)
			{
				throw new HisRecordNotFoundException("No Designation Found");
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
			
				daoObj.free();
				daoObj = null;
			
		}		
		return alRecord;
	}
	
	public List getMlcTypes(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
		String queryKey = "SELECT.ALL_MLC_TYPES.HRGT_MLC_CASE_TYPE_MST";
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
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Mlc Type Found  ");
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
			throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}
	
}//end class
