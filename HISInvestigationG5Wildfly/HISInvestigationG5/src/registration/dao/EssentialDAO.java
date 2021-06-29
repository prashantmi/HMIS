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

 



import com.jscape.inet.sftp.requests.Request;

import registration.RegistrationConfig;
import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;
//import disaster.DisasterConfig;

public class EssentialDAO extends DataAccessObject implements EssentialDAOi
{



	public EssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		//log = LogManager.getLogger(this.getClass());
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
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	
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
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return alRecord;
	}
	
	
	
	
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
}//end class  
