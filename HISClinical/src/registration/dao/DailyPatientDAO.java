package registration.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisAppointmentNotAvailableException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import opd.OpdConfig;
import registration.RegistrationConfig;

/**
 * DailyPatientDAO is a class which describes all the methods required for database interaction for HRGT_DAILY_PATIENT_DTL
 * table, for example, insert, update, select and delete. DailyPatientDAO class provides a standard interface between
 * Business Objects and Database.
 * 
 * @author AHIS
 * 
 */
public class DailyPatientDAO extends RegistrationDAO implements DailyPatientDAOi
{

	/**
	 * Creates a new DailyPatientDAO object.
	 * 
	 * @param _transactionContext Provides the lock on a transaction.
	 */
	public DailyPatientDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	/**
	 * Creates a new entry in DB for each patient on daily basis. Generates the episode number for the new episode of the
	 * patient. Generates the token details, namely, que no, unit no and room no of the patient when patient registers under
	 * normal categary.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO with values stored in DB.
	 */
	public DailyPatientVO create(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		//String queryKeyGenrateRoomEmg="GENRATE_ROOM_CODE.EMERGENCY";
		//String queryGenrateRoomEmg="";
		//Map populateMAPGenrateRoomEmg=new HashMap();
		//String roomCodeEmg=null;
		Connection conn = super.getTransactionContext().getConnection();
		//ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//System.out.println("query" + query);

		try
		{
			
				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getPatCrNo());
				strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.setReturnType(Types.VARCHAR);
				String episodeNo = (String) strProc.execute(conn);
				_dailyPatientVO.setEpisodeCode(episodeNo);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}

		// if(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL)){
		// if(_dailyPatientVO.getPatRegCatCode().equals(RegistrationConfig.PATIENT_REG_CATEGORY_NORMAL)){

		try
		{

			if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( _dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC)) 	)
			{
				// code to execute procedure for queue no for special clinic

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_SPECIAL_CLINIC_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String fileNo=(String) strProc.getParameterAt(10); 
				//String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				//_dailyPatientVO.setDepartment(deptName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
				
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			}
			else if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
			{

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());//to be pass ed from new and old process
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String fileNo=(String) strProc.getParameterAt(10); 
				String unitName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				// String
				// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+_dailyPatientVO.getDepartmentCode()+unitCode;
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				_dailyPatientVO.setDepartmentUnit(unitName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				
				
				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
					
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
						"Procedure Generate Que No:: returns null value");
			}
						///filenoview same as file number
			_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			System.out.println("_dailyPatientVO.getRegisterDate().......::::" + _dailyPatientVO.getRegisterDate());
			populateMap(_dailyPatientVO, _userVO, populateMAP);
			System.out.println("after populate Map: ");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		System.out.println("_dailyPatientVO.getEpisodeVisitNo()::: 2 " + _dailyPatientVO.getEpisodeVisitNo());
		return _dailyPatientVO;
	}

	/**
	 * Populates the map with the patient's daily details to be entered in the DB.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _populateMAP Map containig values which will be used for insert query.
	 */
	public void populateMap(DailyPatientVO _dailyPatientVO, UserVO _userVO, Map _populateMAP) throws SQLException
	{
		System.out.println("inside populateMap::" + _dailyPatientVO);

		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());//setting the crno for genrating sl no
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); // generating
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); // sl	
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());//no
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//for
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//visit
		/*_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); //setting the crno for genrating visitno
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); //setting the episode number for genrating visitno
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());*/
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatDOB());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getPatDOB().toString());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsMLC());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsOld());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFirstName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatLastName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatReligionCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMotherName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRegCatCode());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getRegisterDate().toString());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getExpiryDate().toString());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRegisterDate());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getExpiryDate());		 
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentUnitCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getQueNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsUrban());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsReferred());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefDoctor());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getEmergencyType());		 
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getIsAddressDelhi());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddHNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrict());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCity());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCountryCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddPIN());
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeType()); 
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeTypeCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsValid());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatientAllowed());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRoomCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEntryDate());
		_populateMAP.put(sq.next(), _userVO.getIpAddress());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddContactNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddTypeCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getPatCatcode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLoc());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getSeatId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalityCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCrno());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDept());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFatherOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNickName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCardNo());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getFileNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark1());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsFree());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDisasterId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldRegDate());
	}

	/**
	 * Creates a new entry in DB for each patient when the patient visits a department in which he/she is registered but
	 * visit has not been stamped. Generates the token details, namely, que no, unit no and room no of the patient.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO with values stored in DB.
	 */
	public DailyPatientVO createOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		System.out.println("cr no in createOldDeptVisit" + _dailyPatientVO.getPatCrNo());
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";

		Connection conn = super.getTransactionContext().getConnection();

		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if (!(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC) || _dailyPatientVO
					.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC)))
			{
				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				// strProc.addInParameter(1, Types.VARCHAR, RegistrationDaoConfig.HARDCODE_DEPARTMRNT_CODE);
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				System.out.println("Hi!!!- " + strProc);
				strProc.execute(conn);

				System.out.println("Hello!!!");
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				// String unitCode=(String)strProc.getParameterAt(6);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				// String
				// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+_dailyPatientVO.getDepartmentCode()+unitCode;
				System.out.println("queNo::" + queNo);
				System.out.println("roomCode::" + roomCode);
				// System.out.println("unitCode::"+unitCode);
				System.out.println("deptUnitCode::" + deptUnitCode);
				System.out.println("unitCap::" + unitCap);

				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
			}

			/*
			 * System.out.println("_dailyPatientVO.getQueNo()..::"+_dailyPatientVO.getQueNo());
			 * System.out.println("_dailyPatientVO.getRoomCode()..::"+_dailyPatientVO.getRoomCode());
			 * System.out.println("_dailyPatientVO.getDepartmentUnitCode()..::"+_dailyPatientVO.getDepartmentUnitCode());
			 * System.out.println("_dailyPatientVO.getPatientAllowed()..::"+_dailyPatientVO.getPatientAllowed());
			 */
			populateMap(_dailyPatientVO, _userVO, populateMAP);
			System.out.println("after populate Map: ");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO:createOldDeptVisit:HelperMethods :: " + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _dailyPatientVO;
	}

	/**
	 * Retrieves the token details for a department as on today. Provides the que no for the particular unit and room no of a
	 * department.
	 * 
	 * @param _dailyPatientVO Provides daily patient details.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO containing token details.
	 */
	public DailyPatientVO getTokenDtlOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		// Map populateMAP =new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;

		String queryRetrieve = "";
		Map populateMAPRetrieve = new HashMap();
		String queryKeyRetrieve = "SELECT.HRGT_DAILY_PATIENT_DTL.OPD_UNITS_TOKEN_DTLS";
		Sequence sq = new Sequence();
		DailyPatientVO dailyPatVO = new DailyPatientVO();
		String deptUnitCode = _dailyPatientVO.getDepartmentUnitCode();
		populateMAPRetrieve.put(sq.next(), _dailyPatientVO.getDepartmentCode());
		populateMAPRetrieve.put(sq.next(), deptUnitCode);
		populateMAPRetrieve.put(sq.next(), _userVO.getHospitalCode());
		populateMAPRetrieve.put(sq.next(), _dailyPatientVO.getRoomCode());
		populateMAPRetrieve.put(sq.next(), _dailyPatientVO.getDepartmentCode());
		populateMAPRetrieve.put(sq.next(), deptUnitCode);
		populateMAPRetrieve.put(sq.next(), _userVO.getHospitalCode());
		populateMAPRetrieve.put(sq.next(), _dailyPatientVO.getRoomCode());
		
		
		Map populateMAPDeptUnitRoom =new HashMap();
		String queryDeptUnitRoom="";
    	String queryKeyDeptUnitRoom="SELECT.HRGT_DEPT_UNIT_ROOM_MST.OPD_VISIT";    	
    	Sequence sqDeptUnit=new Sequence();
    	populateMAPDeptUnitRoom.put(sqDeptUnit.next(),deptUnitCode);
    	populateMAPDeptUnitRoom.put(sqDeptUnit.next(),Config.IS_VALID_ACTIVE);
    	populateMAPDeptUnitRoom.put(sqDeptUnit.next(),_userVO.getHospitalCode());
    	//populateMAPDeptUnitRoom.put(sqDeptUnit.next(), _dailyPatientVO.getRoomCode());
    	populateMAPDeptUnitRoom.put(sqDeptUnit.next(),deptUnitCode);
    	populateMAPDeptUnitRoom.put(sqDeptUnit.next(),Config.IS_VALID_ACTIVE);
    	populateMAPDeptUnitRoom.put(sqDeptUnit.next(), _dailyPatientVO.getRoomCode());
    	
		
		Connection conn =super.getTransactionContext().getConnection();
			try
		{

			
			queryRetrieve = HelperMethodsDAO.getQuery(filename, queryKeyRetrieve);
			
			queryDeptUnitRoom = HelperMethodsDAO.getQuery(filename, queryKeyDeptUnitRoom);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, queryRetrieve, populateMAPRetrieve);
			
			if (!rs.next())
			{
				ResultSet deptUnitRoom = HelperMethodsDAO.executeQuery(conn, queryDeptUnitRoom, populateMAPDeptUnitRoom);
				if (!deptUnitRoom.next())
				{
					throw new HisRecordNotFoundException("");
				}
				else
				{
					deptUnitRoom.beforeFirst();
					HelperMethods.populateVOfrmRS(dailyPatVO, deptUnitRoom);
				}
				// throw new HisRecordNotFoundException("Records not found ");
			}
			else
			{
				// System.out.println("rs.getRow().."/*+rs.getRow()*/);
				// System.out.println("rs"+rs.getString(1));

				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(dailyPatVO, rs);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("DailyPatientVO getTokenDtlOldDeptVisit " + e);
		}
		return dailyPatVO;
	}

	/**
	 * Creates a new entry in DB for each patient when the patient re-visits a department. Patient is alloted the same unit
	 * no and room no as the previous visit.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO with values stored in DB.
	 */
	public DailyPatientVO stampOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMap(_dailyPatientVO, _userVO, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO.stampOldDeptVisit " + e);
		}
		return _dailyPatientVO;
	}
	public DailyPatientVO stampOldDeptVisit(HisDAO daoObj,DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		System.out.println("in old stamp...");
		try 
		{
			//strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?)}";

			  strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?)}";
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_dailyPatientVO);
			HelperMethods.setNullToEmpty(_dailyPatientVO.getPatAddress());
			
			/*daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", _dailyPatientVO.getPatDOB().equals(null)|| _dailyPatientVO.getPatDOB().equals(""))?"":_dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo()==""?"0": _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo()==""?"0": _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode()==""?"0": _dailyPatientVO.getPatSecondaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo()==""?"0": _dailyPatientVO.getPrevCrNo(),10);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo()==""?"0": _dailyPatientVO.getPatIdNo(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC()==""?"0": _dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob()==""?"0": _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld()==""?"0": _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode()==""?"0": _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode()==""?"0": _dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode()==""?"0": _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode()==""?"0": _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode()==""?"0": _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3)==""?"0": _dailyPatientVO.getDepartmentCode().substring(0,3),27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo()==""?"0": _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown()==""?"0": _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome()==""?"0": _dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected()==""?"0": _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban()==""?"0": _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred()==""?"0": _dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode()==""?"0": _dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi()==""?"0": _dailyPatientVO.getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode()==""?"0": _dailyPatientVO.getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode()==""?"0": _dailyPatientVO.getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode()==""?"0": _dailyPatientVO.getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode()==""?"0": _dailyPatientVO.getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN()==""?"0": _dailyPatientVO.getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode()==""?"0": _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode()==""?"0": _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid()==""?"0": _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed()==""?"0": _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode()==""?"0": _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode()==""?"0": _dailyPatientVO.getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId()==""?"0": _dailyPatientVO.getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode()==""?"0": _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno()==""?"0": _dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode()==""?"0": _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree()==""?"0": _dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead()==""?"0": _dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId()==""?"0": _dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode()==""?"0": _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode()==""?"0": _dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo()==""?"0": _dailyPatientVO.getPatMotherCrNo(),82);
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,83);
			
			daoObj.execute(nProcIndex1,1);*/
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", (_dailyPatientVO.getPatDOB().equals(null)|| _dailyPatientVO.getPatDOB().equals(""))?"":_dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode()==""?"0":_dailyPatientVO.getPatSecondaryCatCode(),10);
			if(_dailyPatientVO.getPrevCrNo().equalsIgnoreCase("") || _dailyPatientVO.getPrevCrNo().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", "0",11);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo(),11);
			}
			
			if(_dailyPatientVO.getPatDOB().equalsIgnoreCase("") || _dailyPatientVO.getPatDOB().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob","" ,12);
			}
			else
			{
	        daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			}
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC()==""?"0":_dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode().equals("")?"0":_dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate().equals("")?"":_dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3),27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome().equals("")?"0":_dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred().equals("")?"0":_dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode().equals("")?"0":_dailyPatientVO.getPatAddress().getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode().equals("")?"0":_dailyPatientVO.getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN().equals("")?"0": _dailyPatientVO.getPatAddress().getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId().equals("")?_userVO.getSeatId():_dailyPatientVO.getPatAddress().getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree().equals("")?"0":_dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead().equals("")?"0":_dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId().equals("")?"0":_dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate().equals("")?"":_dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode().equals("")?"0":_dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo().equals("")?"0":_dailyPatientVO.getPatMotherCrNo(),82);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", _dailyPatientVO.getPatBirthPlace().equals("")?" ":_dailyPatientVO.getPatBirthPlace(),83);
			if(_dailyPatientVO.getPatDocType()!="")
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":_dailyPatientVO.getPatDocType(),84);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType(),84);
			}			
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", _dailyPatientVO.getPatFirstNameInMultiLang(),85);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", _dailyPatientVO.getPatMiddleNameInMultiLang(),86);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", _dailyPatientVO.getPatLastNameInMultiLang(),87);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,88);
			
			daoObj.execute(nProcIndex1,1);	
			
			
			//daoObj.executeProcedureByPosition(nProcIndex1);
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _dailyPatientVO;
	}
	/**
	 * Creates a new entry in DB for each patient when the patient re-visits the hospital under emergency. Generates the
	 * episode number for the new episode of the patient.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO with values stored in DB.
	 */
	public DailyPatientVO createEmgOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		System.out.println("cr no in createOldDeptVisit" + _dailyPatientVO.getPatCrNo());
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";

		Connection conn = super.getTransactionContext().getConnection();

		// call the getQueryMethod with arguments filename,querykey from prop file
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
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
			strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getPatCrNo());
			strProc.setReturnType(Types.VARCHAR);
			String episodeNo = (String) strProc.execute(conn);
			System.out.println("after  proc execute episodeNo: " + episodeNo);
			_dailyPatientVO.setEpisodeCode(episodeNo);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}

		try
		{
			System.out.println("_dailyPatientVO.getRegisterDate().......::::" + _dailyPatientVO.getRegisterDate());
			populateMap(_dailyPatientVO, _userVO, populateMAP);
			System.out.println("after populate Map: ");
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("INSERT FAILED::createEmgOldDeptVisit HelperMethodsDAO.getResultset" + e);
		}
		return _dailyPatientVO;
	}

	/**
	 * Updates the records of a New Patient in case the patient gets his details modified within 10 minutes of registration.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _userVO Provides User details.
	 * @return Number of records updated.
	 */
	public int update(HisDAO daoObj, DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		int x=1;
		//String queryKey = "UPDATE.HRGT_DAILY_PATIENT_DTL";
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			//strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			 strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_dailyPatientVO);
			
		/*	daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit());
			daoObj.setProcInValue(nProcIndex1, "p_DOB", _dailyPatientVO.getPatDOB());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3));
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict());
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "");
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead());
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate());
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode());
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo());
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daooObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcOutValue(nProcIndex1, "err", 1);
			
			daoObj.execute(nProcIndex1,1);		*/
			
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", (_dailyPatientVO.getPatDOB().equals(null)|| _dailyPatientVO.getPatDOB().equals(""))?"":_dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode()==""?"0":_dailyPatientVO.getPatSecondaryCatCode(),10);
			if(_dailyPatientVO.getPrevCrNo().equalsIgnoreCase("") || _dailyPatientVO.getPrevCrNo().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", "0",11);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo(),11);
			}
			
			if(_dailyPatientVO.getPatDOB().equalsIgnoreCase("") || _dailyPatientVO.getPatDOB().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob","" ,12);
			}
			else
			{
	        daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			}
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC()==""?"0":_dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode().equals("")?"0":_dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate().equals("")?"":_dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3),27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome().equals("")?"0":_dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred().equals("")?"0":_dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode().equals("")?"0":_dailyPatientVO.getPatAddress().getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode().equals("")?"0":_dailyPatientVO.getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN().equals("")?"0": _dailyPatientVO.getPatAddress().getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId().equals("")?_userVO.getSeatId():_dailyPatientVO.getPatAddress().getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree().equals("")?"0":_dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead().equals("")?"0":_dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId().equals("")?"0":_dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate().equals("")?"":_dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode().equals("")?"0":_dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo().equals("")?"0":_dailyPatientVO.getPatMotherCrNo(),82);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", _dailyPatientVO.getPatBirthPlace().equals("")?" ":_dailyPatientVO.getPatBirthPlace(),83);
			if(_dailyPatientVO.getPatDocType()!="")
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", (_dailyPatientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":(_dailyPatientVO.getPatDocType().length())>3?_dailyPatientVO.getPatDocType().substring(0, 3):_dailyPatientVO.getPatDocType()),84);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType(),84);
			}			
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", _dailyPatientVO.getPatFirstNameInMultiLang(),85);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", _dailyPatientVO.getPatMiddleNameInMultiLang(),86);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", _dailyPatientVO.getPatLastNameInMultiLang(),87);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,88);
			
			daoObj.execute(nProcIndex1,1);	
			
			
			
			
			
			
			
			
			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return x;
	}

	/**
	 * Populates the map with the patient details to be updated in the DB.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _populateUpdateMAP Map containig values which will be used for update query.
	 */

	public void populateUpdate(DailyPatientVO _dailyPatientVO, Map _populateUpdateMap,UserVO _userVO)throws SQLException{
    	System.out.println("inside populateMap");
		Sequence sq = new Sequence();
		System.out.println("_patientVO.getPatRegCat()..::10" + _dailyPatientVO.getPatRegCatCode());

		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPrevCrNo());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatIdNo());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatDOB());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsMLC());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsActualDob());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatFirstName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatMiddleName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatLastName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatGenderCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatMaritalStatusCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatReligionCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatGuardianName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatMotherName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsUnknown());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatMonthlyIncome());
		// _populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAmountCollected());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatIsUrban());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsReferred());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatRefDoctor());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsAddressDelhi());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddHNo());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddCityLocCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddDistrict());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddDistrictCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddCity());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddStateCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddStateName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddCountryCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddPIN());
		_populateUpdateMap.put(sq.next(), _userVO.getIpAddress());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddContactNo());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatRefHospitalName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddTypeCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatPrimaryCatCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatAddCityLoc());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatNationalityCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCrno());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDept());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatHusbandName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatNationalId());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatBloodGroup());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsNewBorn());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatOccupation());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatFatherOccupation());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatHusbandOccupation());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatNickName());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatCardNo());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatMotherCrNo());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getIsBroughtDead());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatStatusCode());
		_populateUpdateMap.put(sq.next(), _dailyPatientVO.getPatCrNo());
		_populateUpdateMap.put(sq.next(),_userVO.getHospitalCode() );
				
    }





	/**
	 * Retrieves the Patient details for a particular CrNo.
	 * 
	 * @param _dailyPatientVO Provides daily patient details.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO pateint detail.
	 */
	public DailyPatientVO searchPatientByCrNo(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_DAILY_PATIENT_DTL(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_USER_HOSPITAL_CODE,6);
		daoObj.setProcOutValue(nProcIndex, "err", 1,7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for this CrNo Found");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_dailyPatientVO, rs);
				/*
				 * ResultSet date = HelperMethodsDAO.executeQuery(conn, queryDate, _populateMapForAge); if (!date.next()) {
				 * throw new HisRecordNotFoundException(""); } else { ResultSetMetaData rsmd = date.getMetaData(); int
				 * no_of_col = rsmd.getColumnCount(); System.out.println("no_of_col" + no_of_col); date.beforeFirst(); while
				 * (date.next()) { for (int i = 1; i <= no_of_col; i++) { i++; age = date.getString(rsmd.getColumnName(i));
				 * System.out.println("age:::..." + age); } }
				 */
				/*
				 * System.out.println("age:::..." + age); _patientVO.setPatAge(age); }
				 */
				// _patientVO.setPatAddress(_addressVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return _dailyPatientVO;
	}
	
	/**
	 * Retrieves the Patient details for a particular CrNo.
	 * 
	 * @param _dailyPatientVO Provides daily patient details.
	 * @param _userVO Provides User details.
	 * @return DailyPatientVO pateint detail.
	 * Added By Singaravelan on 18-Jul-2014 to get the Patient Details irrespective of Hospital
	 */
	public DailyPatientVO searchPatientByCrNoWithoutHospital(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_HRGT_DAILY_PATIENT_DTL(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode", "2",1);
		daoObj.setProcInValue(nProcIndex, "p_gnum_hospital_code", _userVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),3);
		daoObj.setProcInValue(nProcIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "p_gnum_seat_id", _userVO.getSeatId(),5);
		daoObj.setProcInValue(nProcIndex, "p_super_hospital_code", Config.SUPER_USER_HOSPITAL_CODE,6);
		daoObj.setProcOutValue(nProcIndex, "err", 1,7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record for this CrNo Found");
			}
			else
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_dailyPatientVO, rs);
				/*
				 * ResultSet date = HelperMethodsDAO.executeQuery(conn, queryDate, _populateMapForAge); if (!date.next()) {
				 * throw new HisRecordNotFoundException(""); } else { ResultSetMetaData rsmd = date.getMetaData(); int
				 * no_of_col = rsmd.getColumnCount(); System.out.println("no_of_col" + no_of_col); date.beforeFirst(); while
				 * (date.next()) { for (int i = 1; i <= no_of_col; i++) { i++; age = date.getString(rsmd.getColumnName(i));
				 * System.out.println("age:::..." + age); } }
				 */
				/*
				 * System.out.println("age:::..." + age); _patientVO.setPatAge(age); }
				 */
				// _patientVO.setPatAddress(_addressVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return _dailyPatientVO;
	}

	/**
	 * Retrieves the List of patient for particular unit and room.
	 * 
	 * @param _userVO Provides User details.
	 * @param unitCode Provides the Unit.
	 * @param roomCode Provides the room.
	 * @return Patient List as DailyPatientVO[].
	 */
	// *
/*	public PatientDetailVO[] getTodayPatientsBySeatID(UserVO _userVO, String unitCode, String roomCode)
	{
		PatientDetailVO[] dailyPatientVOs = null;
		Map _populateMapPatientDtl = new HashMap();
		ValueObject[] valueObjects = null;
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_FOR_TODAY";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		_populateMapPatientDtl.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		_populateMapPatientDtl.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		_populateMapPatientDtl.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		_populateMapPatientDtl.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
		_populateMapPatientDtl.put(sq.next(), unitCode);
		_populateMapPatientDtl.put(sq.next(), roomCode);
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return dailyPatientVOs;
	}*/
	
	public PatientDetailVO[] getTodayPatientsBySeatID(UserVO _userVO, String unitCode, String roomCode) 
	{
		PatientDetailVO[] dailyPatientVOs = null;
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call PKG_DYNAMIC_DESK_VIEW.proc_retrieve_patient_list_for_today(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "p_mode", "1",1);
		daoObj.setProcInValue(nProcIndex, "super_hosp", Config.SUPER_USER_HOSPITAL_CODE,2);
		daoObj.setProcInValue(nProcIndex, "episode_visitstamped", RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED,3);
		daoObj.setProcInValue(nProcIndex, "episode_visitconfirmed",RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED,4);
		daoObj.setProcInValue(nProcIndex, "unit_code", unitCode,5);
		daoObj.setProcInValue(nProcIndex, "room_code", roomCode,6);
		daoObj.setProcInValue(nProcIndex, "hosp_code", _userVO.getHospitalCode(),7);
		daoObj.setProcInValue(nProcIndex, "is_valid", Config.IS_VALID_ACTIVE,8);
		daoObj.setProcOutValue(nProcIndex, "err", 1,9);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}

		try
		{
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				}
			}
			
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return dailyPatientVOs;
	}
	/*public PatientDetailVO[] getTodayPatientsBySeatID(UserVO _userVO, String unitCode, String roomCode) 
	{
		PatientDetailVO[] dailyPatientVOs = null;
		Map _populateMapPatientDtl = new HashMap();
		ValueObject[] valueObjects = null;
		String errorMsg="";
		WebRowSet rs = null;
		HisDAO daoObj = null;		
		int nProcIndex = 0;
		Procedure strProc=new Procedure(DynamicDeskConfig.PROC_FOR_PATIENT_DTL_FOR_TODAY);
		
		String count = "";
		ResultSet rs = null;
		String errorMsg="";
		String patientCountObj;
		System.out.println("seat id of USER :"+userVO.getSeatId()+"  user id :"+userVO.getUserId());		
		try
		{
			//Procedure strProc=new Procedure(DynamicDeskConfig.PROC_FOR_PATIENT_DTL_FOR_TODAY);
			
			
			strProc.setProcInValue(nProcIndex, "p_mode", "2",1);
			
			
			strProc.addInParameter(1,Types.VARCHAR,1);// Mode
			strProc.addInParameter(2,Types.VARCHAR,Config.SUPER_USER_HOSPITAL_CODE);// Super HospitalCode
			strProc.addInParameter(3,Types.VARCHAR,RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		    strProc.addInParameter(4,Types.VARCHAR,RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
		    strProc.addInParameter(5,Types.VARCHAR,unitCode); //user seat Id
			strProc.addInParameter(6,Types.VARCHAR,roomCode);
			strProc.addInParameter(7,Types.VARCHAR,_userVO.getHospitalCode());
			strProc.addInParameter(8,Types.VARCHAR,Config.IS_VALID_ACTIVE);
			strProc.setReturnType(Types.VARCHAR);
			//return	dailyPatientVOs = (PatientDetailVO[])strProc.execute(super.getTransactionContext().getConnection());
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			ResultSet rs = strProc.execute(super.getTransactionContext().getConnection());
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
	}*/

	/**
	 * Retrieves the total no. of patient for particular unit and room.
	 * 
	 * @param _userVO Provides User details.
	 * @param unitCode Provides the Unit.
	 * @param roomCode Provides the room.
	 * @return count i.e. total no. of Patient .
	 */
	// *
	public String getTodayPatientsCountBySeatID(UserVO _userVO, String unitCode, String roomCode)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		//ValueObject[] valueObjects = null;
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_COUNT";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapPatientDtl.put(sq.next(), unitCode);
		_populateMapPatientDtl.put(sq.next(), roomCode);
		_populateMapPatientDtl.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		//_populateMapPatientDtl.put(sq.next(),RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}

	// *
	public String getTodayPatientsAttendedCountBySeatID(UserVO _userVO, String unitCode, String roomCode)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		//ValueObject[] valueObjects = null;
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_COUNT";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapPatientDtl.put(sq.next(), unitCode);
		_populateMapPatientDtl.put(sq.next(), roomCode);
		//_populateMapPatientDtl.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		_populateMapPatientDtl.put(sq.next(),RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}

	/**
	 * Updates the HRGNUM_IS_CONFIRMED records of a Patient.
	 * 
	 * @param patCrNo of the Patient.
	 * @param serialNo of the Patient.
	 * @param visitNo of the Patient.
	 * @param episodeCode of the Patient.
	 * @param isConfirmed value that has to be updated.
	 * @return Number of records updated.
	 */
	// *
	public int updateIsconfirmed(String patCrNo, String SerialNo, String visitNo, String episodeCode, String isConfirmed,
			UserVO _userVO)
	{
		int x = 0;
		Sequence sq = new Sequence();
		System.out.println("inside update of DailyPatientVO");
		String query = "";
		Map _populateUpdateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.HRGT_DAILY_PATIENT_DTL.ISCOFIRMED";
		Connection conn = super.getTransactionContext().getConnection();
		System.out.println("After obtaining connection in update of PatientDAO ");
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{

			_populateUpdateMap.put(sq.next(), isConfirmed);
			_populateUpdateMap.put(sq.next(), _userVO.getSeatId());
			_populateUpdateMap.put(sq.next(), patCrNo);
			_populateUpdateMap.put(sq.next(), episodeCode);
			_populateUpdateMap.put(sq.next(), visitNo);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			x = HelperMethodsDAO.excecuteUpdate(conn, query, _populateUpdateMap);
			if (x == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException("update unsuccessful::" + e);
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data " + e);
		}
		return x;
	}

	///////////////Change secondary category///////////////////////
	
	public void updateSecondaryCategory(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.SECONDARY_CATEGORY.HRGT_DAILY_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _dailyPatientVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _dailyPatientVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _dailyPatientVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
	

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data change secondary Category " + e);
		}
		
	}
	
	public void changePatientPrimaryCat(DailyPatientVO _dailyPatientVO, UserVO _userVO) {
		
		Map _populateMapPatientDtl = new HashMap();
		//int x = 0;
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.PRIMARY_CATEGORY.HRGT_DAILY_PATIENT_DTL";
		Sequence sq = new Sequence();
		_populateMapPatientDtl.put(sq.next(), _dailyPatientVO.getPatPrimaryCatCode());
		_populateMapPatientDtl.put(sq.next(), _dailyPatientVO.getPatCrNo());
		//_populateMapPatientDtl.put(sq.next(),_userVO.getHospitalCode());
		Connection conn = super.getTransactionContext().getConnection();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		try {
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMapPatientDtl);
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				System.out.println("no record in daily patient");
			} else
				throw new HisDataAccessException("PatientDAO:changePatientPrimaryCat:HelperMethods :: " + e);
		}
	}

	public void updateFileNumber(DailyPatientVO _dailyPatientVO, UserVO _userVO) {
		
		Map _populateMapPatientDtl = new HashMap();
		//int x = 0;
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.FILE_NUMBER.HRGT_DAILY_PATIENT_DTL";
		Sequence sq = new Sequence();
		_populateMapPatientDtl.put(sq.next(), _dailyPatientVO.getFileNo());
		_populateMapPatientDtl.put(sq.next(), _dailyPatientVO.getPatCrNo());
		_populateMapPatientDtl.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMapPatientDtl.put(sq.next(),_userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(),_dailyPatientVO.getEpisodeVisitNo());
		
		Connection conn = super.getTransactionContext().getConnection();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		try {
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMapPatientDtl);
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				System.out.println("no record in daily patient");
			} else
				throw new HisDataAccessException("PatientDAO:changePatientPrimaryCat:HelperMethods :: " + e);
		}
	}

	public String generateFileNumber(String _unitCode,String _hospitalCode){

		//ResultSet rs = null;
		String fileNumber="";
		String errorMsg = null;
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GENERATE_FILE_NUMBER);
			strProc.addInParameter(1, Types.VARCHAR, _unitCode);
			strProc.addInParameter(2, Types.VARCHAR, _hospitalCode);
			strProc.addOutParameter(3, Types.VARCHAR);
			

			strProc.execute(super.getTransactionContext().getConnection());
			fileNumber = (String) strProc.getParameterAt(3);
			
			System.out.println("");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		
		

		return fileNumber;
		
		
		/*Map _populateMapPatientDtl = new HashMap();
		String fileNumber="";
		String query = "";
		ResultSet rs;
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "GENERATE.FILE_NUMBER.AHIS_REG_GenerateFileNo";
		Sequence sq = new Sequence();
		_populateMapPatientDtl.put(sq.next(), _unitCode);
		_populateMapPatientDtl.put(sq.next(), _hospitalCode);
		
		Connection conn = super.getTransactionContext().getConnection();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		System.out.println("query" + query);
		try {
			rs=HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("File Number Not Generated");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				fileNumber = rs.getString(1);
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				System.out.println("no record in daily patient");
			} else
				throw new HisDataAccessException("PatientDAO:changePatientPrimaryCat:HelperMethods :: " + e);
		}
		*/
	}

	// Casuality Patients By Seat Id
	public PatientDetailVO[] getCsultyPatientsBySeatID(String _unitCode, String _roomCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_FOR_CASUALITY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _roomCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		ValueObject[] valueObjects = null;
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("DailyPatientDAO:getCsultyPatientsBySeatID:HelperMethods :: " + e);
		}
		return dailyPatientVOs;
	}

	public void updateRevokeMLCEpisode(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.MLCEPI_TO_NONMLC.HRGT_DAILY_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeTypeCode());
		_populateMap.put(sq.next(), _dailyPatientVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _dailyPatientVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
	

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data change secondary Category " + e);
		}
		
	}
	
	public String getQueNumber(UserVO _userVO,String _unitCode,String _roomCode)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		
		
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.QUE_NO.HRGT_DAILY_PATIENT_DTL";
        String queNo="";
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

		populateMAP.put(sq.next(),_roomCode);
		populateMAP.put(sq.next(), _unitCode);	
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No record for Room Exists in HRGT_DEPT_UNIT_ROOM_MST  ");
			else
			{
				rs.beforeFirst();
			while(rs.next())
				queNo=rs.getString(1);	
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
		
		return queNo;
	}
	
	public void changeDailyPatientDetails(DailyPatientVO _dailyPatientVO,UserVO _userVO)
	{
		//ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.QUE_NO_ROOM_NO_HRGT_DAILY_PATIENT_DTL";

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

						

		//populateMAP.put(sq.next(), _dailyPatientVO.getQueNo());
		populateMAP.put(sq.next(), _dailyPatientVO.getRoomCode());
		populateMAP.put(sq.next(), _dailyPatientVO.getRoomCode());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
		populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeVisitNo());
		
		//By Akash Singh, Due to serialno is not avl in Table hrgt_daily_patient_Detail on Date 13-02-2015
		//populateMAP.put(sq.next(), _dailyPatientVO.getRoom());
		

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException :Change Daily Patient details" + e);
		}
		
	}
	
	public String getCsultyTotalPatCount(UserVO userVO,String unitCode,String roomCode)
	{
		String count = "";
		Map populateMap = new HashMap();
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.COUNT_CSULTY_TOTAL_PATIENT";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
	
		populateMap.put(sq.next(), unitCode);
		populateMap.put(sq.next(), roomCode);
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}
	
	public String getCsultyTodayAdmPatCount(UserVO userVO,String unitCode,String roomCode)
	{
		String count = "";
		Map populateMap = new HashMap();
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.COUNT_CSULTY_TODAY_PATIENT";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
	
		populateMap.put(sq.next(), unitCode);
		populateMap.put(sq.next(), roomCode);
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}
	
	// CMO Patients List For Desk
	public PatientDetailVO[] getCMOPatientsList(String _unitCode, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_FOR_CMO";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
			populateMAP.put(sq.next(), _unitCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		ValueObject[] valueObjects = null;
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("DailyPatientDAO:getCsultyPatientsBySeatID:HelperMethods :: " + e);
		}
		return dailyPatientVOs;
	}
	
	public String getCMOTotalPatCount(UserVO userVO,String unitCode)
	{
		String count = "";
		Map populateMap = new HashMap();
		String query = "";
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.COUNT_CMO_TOTAL_PATIENT";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
	
		populateMap.put(sq.next(), unitCode);
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}
	
	public int updatePatStatusIsconfirmed(String patCrNo, String SerialNo, String visitNo, String episodeCode, String isConfirmed,
			UserVO _userVO)
	{
		int x = 0;
		Sequence sq = new Sequence();
		String query = "";
		Map _populateUpdateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_DAILY_PATIENT_DTL.PAT_STATUS_ISCOFIRMED";
		Connection conn = super.getTransactionContext().getConnection();
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{

			_populateUpdateMap.put(sq.next(), RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
			_populateUpdateMap.put(sq.next(), isConfirmed);
			_populateUpdateMap.put(sq.next(), _userVO.getSeatId());
			_populateUpdateMap.put(sq.next(), patCrNo);
			//_populateUpdateMap.put(sq.next(), SerialNo);
			_populateUpdateMap.put(sq.next(), visitNo);
			_populateUpdateMap.put(sq.next(), episodeCode);
			_populateUpdateMap.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			x = HelperMethodsDAO.excecuteUpdate(conn, query, _populateUpdateMap);
			if (x == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException("update unsuccessful::" + e);
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data " + e);
		}
		return x;
	}

	
	///  Getting Count of Visit of Patient for JSY Registration ///
	/*public String getPatientsVisitCount(UserVO _userVO,String patCrNo)
	{
		String count = "";
		Map _populateMapPatientDtl = new HashMap();
		String query = "";
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_NO_OF_VISIT.HRGT_DAILY_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
	
		_populateMapPatientDtl.put(sq.next(), patCrNo);
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE );
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (rs.next())
			{
				count = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return count;
	}*/

	
	public PatientDetailVO getPatDetailForJSYRegistration(String patCrNo, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		PatientDetailVO dPatientVO=new PatientDetailVO();
		String filename = InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT_PATIENTDETAIL.HRGT_PATIENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		 populateMAP.put(sq.next(), _userVO.getHospitalCode());
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 populateMAP.put(sq.next(), patCrNo);
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
				HelperMethods.populateVOfrmRS(dPatientVO, rs);
			else
				dPatientVO=null;
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return dPatientVO;
	}
	
	
	
	
	
	
	public DailyPatientVO insertDailyPatientInfo(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		//Map populateMAPGenrateRoomEmg=new HashMap();
		//String roomCodeEmg=null;
		Connection conn = super.getTransactionContext().getConnection();
		//ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
			strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getPatCrNo());
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.setReturnType(Types.VARCHAR);
			String episodeNo = (String) strProc.execute(conn);
			System.out.println("after  proc execute episodeNo: " + episodeNo);
			_dailyPatientVO.setEpisodeCode(episodeNo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}
		try
		{
			System.out.println("_dailyPatientVO.getRegisterDate().......::::" + _dailyPatientVO.getRegisterDate());
			populateMap(_dailyPatientVO, _userVO, populateMAP);
			System.out.println("after populate Map: ");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		System.out.println("_dailyPatientVO.getEpisodeVisitNo()::: 2 " + _dailyPatientVO.getEpisodeVisitNo());
		return _dailyPatientVO;
	}

///////////////Change secondary category///////////////////////
	

	public void updateTreatmentCategory(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		String query = "";
		Map _populateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.TREATMENT_CATEGORY.HRGT_DAILY_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), _dailyPatientVO.getPatSecondaryCatCode());
		_populateMap.put(sq.next(), _dailyPatientVO.getPatCrNo().trim());
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeCode().trim());
		_populateMap.put(sq.next(), _dailyPatientVO.getEpisodeVisitNo().trim());
		
	

		try
		{
			HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data change secondary Category " + e);
		}
		
	}
	
	/*public DailyPatientVO generateQueueNumber(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		String strErr = "";
		int nProcIndex1 = 0;
		HisDAO daoObj = null;
		String strProcName1="";
		
		try
		{

			if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( _dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC)) )
			{
				// code to execute procedure for queue no for special clinic

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_SPECIAL_CLINIC_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
				String [] printFlagArray=printFlagFileNo.split("@");
				String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");

				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant = (String) strProc.getParameterAt(16);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				//////generate episode code
				String episodeCode=deptUnitCode+"001";
				_dailyPatientVO.setEpisodeCode(episodeCode);
				///////////
				_dailyPatientVO.setUnitConsultant(unitConsultant);
				
				//for printing formats
				_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
				_dailyPatientVO.setFilePrintSetting(printFlagFileNo.substring(2,5));

				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
				
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			}
			else if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
			{

					strProcName1 = "{call pkg_reg_dml.gen_queno_unitwise(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

					daoObj = new HisDAO("Registration","DailyPatientDAO");

					nProcIndex1 = daoObj.setProcedure(strProcName1);

					daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1");
					daoObj.setProcInValue(nProcIndex1, "p_dept_unitcode", _dailyPatientVO.getDepartmentCode());
					daoObj.setProcInValue(nProcIndex1, "p_pri_cat", _dailyPatientVO.getPatPrimaryCatCode());
					daoObj.setProcInValue(nProcIndex1, "p_sec_cat", _dailyPatientVO.getPatPrimaryCatCode());
					daoObj.setProcInValue(nProcIndex1, "p_hospital_code", _userVO.getHospitalCode());
					daoObj.setProcInValue(nProcIndex1, "p_usability_flag", _dailyPatientVO.getRoomUsability()!=null?_dailyPatientVO.getRoomUsability():"0");
					daoObj.setProcOutValue(nProcIndex1, "p_o_que_no", 1);
					daoObj.setProcOutValue(nProcIndex1, "p_o_room_code",1);
					daoObj.setProcOutValue(nProcIndex1, "p_o_unit", 1);
					daoObj.setProcOutValue(nProcIndex1, "p_o_cap",1);
					daoObj.setProcOutValue(nProcIndex1, "p_unit_name",1);
					daoObj.setProcOutValue(nProcIndex1, "p_room_name", 1);
					daoObj.setProcOutValue(nProcIndex1, "p_status", 1);
					daoObj.setProcOutValue(nProcIndex1, "p_unittime", 1);
					daoObj.setProcOutValue(nProcIndex1, "p_unit_inc_name", 1);
					daoObj.setProcOutValue(nProcIndex1, "p_cardSetting", 1);
					daoObj.setProcOutValue(nProcIndex1, "err", 1);
					
					daoObj.executeProcedure(nProcIndex1);
					strErr = daoObj.getString(nProcIndex1, "err");
						if (strErr == null)
							strErr = "";
			
							if (!strErr.equals("")) 
							{
								throw new Exception(strErr);
							}
							else
							{
								String queNo = daoObj.getString(nProcIndex1, "p_o_que_no");
								String roomCode = daoObj.getString(nProcIndex1, "p_o_room_code");
								String deptUnitCode = daoObj.getString(nProcIndex1, "p_o_unit");
								String unitCap = daoObj.getString(nProcIndex1, "p_o_cap");
								String unitName=daoObj.getString(nProcIndex1, "p_unit_name");
								String roomName=daoObj.getString(nProcIndex1, "p_room_name");
								String status=daoObj.getString(nProcIndex1, "p_status");
								String unitDays=daoObj.getString(nProcIndex1, "p_unittime");
								String unitConsultant = daoObj.getString(nProcIndex1, "p_unit_inc_name");
								String printFlagFileNo=daoObj.getString(nProcIndex1, "p_cardSetting");
								
								if(status.equals("2"))
									throw new HisAppointmentNotAvailableException("Appointment Not Available");
								
								_dailyPatientVO.setQueNo(queNo);
								_dailyPatientVO.setRoomCode(roomCode);
								_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
								_dailyPatientVO.setPatientAllowed(unitCap);
								_dailyPatientVO.setDepartmentUnit(unitName);
								_dailyPatientVO.setRoom(roomName);
								_dailyPatientVO.setUnitWorkingDays(unitDays);
								_dailyPatientVO.setUnitConsultant(unitConsultant);
								
								
								
								//////generate episode code
								String episodeCode=deptUnitCode+"001";
								_dailyPatientVO.setEpisodeCode(episodeCode);
								///////////
								//for printing formats
								_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
								_dailyPatientVO.setFilePrintSetting("");
			
								_dailyPatientVO.setFileNo("");
									
								if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
										|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
										"Procedure Generate Que No:: returns null value");
								_dailyPatientVO.setFileNoView("");
							}
				}
				
		}catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return _dailyPatientVO;
			
	}*/
	public DailyPatientVO generateQueueNumber(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		HisDAO daoObj=null;
		String strProcName1="";
		int nProcIndex1=0;
		String strErr="";
		try
		{

			/*if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( _dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC))||_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL) )
			{*/
				
				strProcName1 = "{call pkg_reg_dml.gen_queno_unitwise(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				daoObj = new HisDAO("Registration","DailyPatientDAO");

				nProcIndex1 = daoObj.setProcedure(strProcName1);

				daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
				daoObj.setProcInValue(nProcIndex1, "p_dept_unitcode", _dailyPatientVO.getDepartmentUnitCode(),2);//_dailyPatientVO.getDepartmentCode()
				daoObj.setProcInValue(nProcIndex1, "p_pri_cat", _dailyPatientVO.getPatPrimaryCatCode(),3);
				daoObj.setProcInValue(nProcIndex1, "p_sec_cat", _dailyPatientVO.getPatPrimaryCatCode(),4);
				daoObj.setProcInValue(nProcIndex1, "p_hospital_code", _userVO.getHospitalCode(),5);
				daoObj.setProcInValue(nProcIndex1, "p_usability_flag", (_dailyPatientVO.getRoomUsability()!=null)?"0":"0",6);
				daoObj.setProcOutValue(nProcIndex1, "p_o_que_no", 1,7);
				daoObj.setProcOutValue(nProcIndex1, "p_o_room_code",1,8);
				daoObj.setProcOutValue(nProcIndex1, "p_o_unit", 1,9);
				daoObj.setProcOutValue(nProcIndex1, "p_o_cap",1,10);
				daoObj.setProcOutValue(nProcIndex1, "p_unit_name",1,11);
				daoObj.setProcOutValue(nProcIndex1, "p_room_name", 1,12);
				daoObj.setProcOutValue(nProcIndex1, "p_status", 1,13);
				daoObj.setProcOutValue(nProcIndex1, "p_unittime", 1,14);
				daoObj.setProcOutValue(nProcIndex1, "p_unit_inc_name", 1,15);
				daoObj.setProcOutValue(nProcIndex1, "p_cardSetting", 1,16);				
				daoObj.setProcOutValue(nProcIndex1, "err", 1,17);
				
				daoObj.executeProcedureByPosition(nProcIndex1);
				strErr = daoObj.getString(nProcIndex1, "err");
					if (strErr == null)
						strErr = "";
		
						if (!strErr.equals("")) 
						{
							System.out.println(strErr);
							throw new Exception(strErr);
						}
						else
						{
							String queNo = daoObj.getString(nProcIndex1, "p_o_que_no");
							String roomCode = daoObj.getString(nProcIndex1, "p_o_room_code");
							String deptUnitCode = daoObj.getString(nProcIndex1, "p_o_unit");
							String unitCap = daoObj.getString(nProcIndex1, "p_o_cap");
							String unitName=daoObj.getString(nProcIndex1, "p_unit_name");
							String roomName=daoObj.getString(nProcIndex1, "p_room_name");
							String status=daoObj.getString(nProcIndex1, "p_status");
							String unitDays=daoObj.getString(nProcIndex1, "p_unittime");
							String unitConsultant = daoObj.getString(nProcIndex1, "p_unit_inc_name");
							String cardPrintSetting = daoObj.getString(nProcIndex1, "p_cardSetting");
							
							if(status.equals("2"))
								throw new HisAppointmentNotAvailableException("Appointment Not Available");
							
							_dailyPatientVO.setQueNo(queNo);
							_dailyPatientVO.setRoomCode(roomCode);
							_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
							_dailyPatientVO.setPatientAllowed(unitCap);
							_dailyPatientVO.setDepartmentUnit(unitName);
							_dailyPatientVO.setRoom(roomName);
							_dailyPatientVO.setUnitWorkingDays(unitDays);
							_dailyPatientVO.setUnitConsultant(unitConsultant);
							_dailyPatientVO.setCardPrintSetting(cardPrintSetting);
							//////generate episode code
							String episodeCode=deptUnitCode+"001";
							_dailyPatientVO.setEpisodeCode(episodeCode);
							///////////
							//for printing formats
							//_dailyPatientVO.setCardPrintSetting("");
							_dailyPatientVO.setFilePrintSetting("");
		
							_dailyPatientVO.setFileNo("");
								
							if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
									|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
									"Procedure Generate Que No:: returns null value");
							_dailyPatientVO.setFileNoView("");
						}
			//}
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _dailyPatientVO;
	}
	public DailyPatientVO createNewRegistration(HisDAO daoObj, DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,	?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods method= new HelperMethods();
			HelperMethods.setNullToEmpty(_dailyPatientVO);
			//HelperMethods.setEmptyToNull(_dailyPatientVO);
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", (_dailyPatientVO.getPatDOB().equals(null)|| _dailyPatientVO.getPatDOB().equals(""))?"":_dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode()==""?"0":_dailyPatientVO.getPatSecondaryCatCode(),10);
			if(_dailyPatientVO.getPrevCrNo().equalsIgnoreCase("") || _dailyPatientVO.getPrevCrNo().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", "0",11);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo(),11);
			}
			
			if(_dailyPatientVO.getPatDOB().equalsIgnoreCase("") || _dailyPatientVO.getPatDOB().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob","" ,12);
			}
			else
			{
	        daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			}
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC()==""?"0":_dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode().equals("")?"0":_dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate().equals("")?"":_dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3),27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome().equals("")?"0":_dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode().equals("")?"0":_dailyPatientVO.getPatAddress().getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN().equals("")?"0": _dailyPatientVO.getPatAddress().getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId().equals("")?_userVO.getSeatId():_dailyPatientVO.getPatAddress().getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree().equals("")?"0":_dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead().equals("")?"0":_dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId().equals("")?"0":_dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate().equals("")?"":_dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode().equals("")?"0":_dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo().equals("")?"0":_dailyPatientVO.getPatMotherCrNo(),82);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", _dailyPatientVO.getPatBirthPlace().equals("")?" ":_dailyPatientVO.getPatBirthPlace(),83);
			if(_dailyPatientVO.getPatDocType()!="")
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":_dailyPatientVO.getPatDocType(),84);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType(),84);
			}			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", _dailyPatientVO.getPatFirstNameInMultiLang(),85);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", _dailyPatientVO.getPatMiddleNameInMultiLang(),86);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", _dailyPatientVO.getPatLastNameInMultiLang(),87);
			
			daoObj.setProcOutValue(nProcIndex1, "err", 1,88);
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						if(_dailyPatientVO.getIsActualDob().equals("1"))
						{
							_dailyPatientVO.setPatAge(getAgeFromDOB(_dailyPatientVO.getPatDOB()));
						}
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _dailyPatientVO;
	}

	public String getAgeFromDOB(String dob)
	{
		String age="";
		int funcIndex=0;
		HisDAO daoObj=null;
		try 
		{
			daoObj = new HisDAO("Registration","DailyPatientDAO");
			funcIndex = daoObj.setFunction("{? = call reg_function.DOB_AGE_ON(?::timestamp,?)}");
			daoObj.setFuncInValue(funcIndex, 2, dob);
			daoObj.setFuncInValue(funcIndex, 3,"");
			daoObj.setFuncOutValue(funcIndex,1);
			
			daoObj.executeFunction(funcIndex);
			age = daoObj.getFuncString(funcIndex);
		} 
		catch (Exception e) 
		{
			throw new HisDataAccessException("PatientDAO.generateCRno()" + e);
		}
		finally 
		{
			if (daoObj != null) 
			
				daoObj.free();
				daoObj = null;
			
		}
		return age;
	}
	
	public void populateMapNewRegistration(DailyPatientVO _dailyPatientVO, UserVO _userVO, Map _populateMAP) throws SQLException
	{
		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());//setting the crno for genrating sl no
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); // generating
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); // sl	
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());//no
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//for
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//visit
		/*_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); //setting the crno for genrating visitno
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); //setting the episode number for genrating visitno
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());*/
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatDOB());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsMLC());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsOld());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFirstName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatLastName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatReligionCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMotherName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRegCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRegisterDate());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentCode().substring(0,3));
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getQueNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsUrban());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsReferred());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefDoctor());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getIsAddressDelhi());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddHNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrict());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCity());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCountryCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddPIN());
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsValid());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatientAllowed());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRoomCode());
		_populateMAP.put(sq.next(), _userVO.getIpAddress());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddContactNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddTypeCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLoc());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getSeatId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalityCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCrno());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDept());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFatherOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNickName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCardNo());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getFileNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark1());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsFree());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDisasterId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldRegDate());
		
	}
	public DailyPatientVO getTokenDtlOldDeptVisitNew(HisDAO daoObj,DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		_dailyPatientVO=generateQueueNumber(_dailyPatientVO, _userVO);
	
	
	/*if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
		_dailyPatientVO.setFileNo(fileNo);*/
		
	if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
			|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
			"Procedure Generate Que No:: returns null value");

		///filenoview same as file number
		//_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());


	
	
	return _dailyPatientVO;
	}
	public DailyPatientVO getTokenDtlOldDeptVisitNew(DailyPatientVO _dailyPatientVO, UserVO _userVO)
			{
		Connection conn = super.getTransactionContext().getConnection();
		try{
				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_OLD_PATIENT);
			strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
			strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getRoomCode());
			strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
			strProc.addInParameter(4, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
			strProc.addOutParameter(5, Types.VARCHAR);
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.VARCHAR);
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(10, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());//to be pass ed from new and old process
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addInParameter(12, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
			strProc.addOutParameter(13, Types.VARCHAR);
			strProc.addOutParameter(14, Types.VARCHAR);
			strProc.addOutParameter(15, Types.VARCHAR);
			strProc.addOutParameter(16, Types.VARCHAR);
			strProc.execute(conn);
			String queNo = (String) strProc.getParameterAt(5);
			String roomCode = (String) strProc.getParameterAt(6);
			String deptUnitCode = (String) strProc.getParameterAt(7);
			String unitCap = (String) strProc.getParameterAt(8);
			String fileNo=(String) strProc.getParameterAt(11); 
			String unitName=(String) strProc.getParameterAt(13);
			String roomName=(String) strProc.getParameterAt(14);
			String status=(String) strProc.getParameterAt(15);
			String unitDays=(String) strProc.getParameterAt(16);
			// String
			// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+_dailyPatientVO.getDepartmentCode()+unitCode;
			if(status.equals("2"))
				throw new HisAppointmentNotAvailableException("Appointment Not Available");
			
			_dailyPatientVO.setQueNo(queNo);
			_dailyPatientVO.setRoomCode(roomCode);
			_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
			_dailyPatientVO.setPatientAllowed(unitCap);
			_dailyPatientVO.setDepartmentUnit(unitName);
			_dailyPatientVO.setRoom(roomName);
			_dailyPatientVO.setUnitWorkingDays(unitDays);
			
			
			/*if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
				_dailyPatientVO.setFileNo(fileNo);*/
				
			if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
					|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
					"Procedure Generate Que No:: returns null value");

	///filenoview same as file number
			//_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
	
	
			}
			catch (HisInvalidTokenNumberException e)
			{
				e.printStackTrace();
				throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
			}
			catch (HisAppointmentNotAvailableException e)
			{
				e.printStackTrace();
				throw new HisAppointmentNotAvailableException(e.getMessage());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new HisDataAccessException("Procedure Generate Que No::" + e);
			}
			
			return _dailyPatientVO;
			}	

	public String generateEpisodeCode(String _patCrNo,UserVO _userVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		String episodeNo="";
		try
		{
			
				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
				strProc.addInParameter(1, Types.VARCHAR, _patCrNo);
				strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.setReturnType(Types.VARCHAR);
				episodeNo = (String) strProc.execute(conn);
				
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}
		return episodeNo;
	}
	
	////Create for new Department visit
	public DailyPatientVO createNewDepartment(DailyPatientVO _dailyPatientVO, UserVO _userVO,String _referMlcNo)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//System.out.println("query" + query);

		/*try
		{
			
				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getPatCrNo());
				strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.setReturnType(Types.VARCHAR);
				String episodeNo = (String) strProc.execute(conn);
				_dailyPatientVO.setEpisodeCode(episodeNo);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}
*/
		
		try
		{

			if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( _dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC)) 	)
			{
				// code to execute procedure for queue no for special clinic

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_SPECIAL_CLINIC_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
			    String [] printFlagArray=printFlagFileNo.split("@");
			    String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");


				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				//_dailyPatientVO.setDepartment(deptName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				_dailyPatientVO.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				String episodeCode=deptUnitCode+"001";
				_dailyPatientVO.setEpisodeCode(episodeCode);
				///////////
				//for printing formats
				_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
				_dailyPatientVO.setFilePrintSetting(printFlagFileNo.substring(2,5));

				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
				
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			}
			else if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
			{

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());//to be pass ed from new and old process
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
			    String [] printFlagArray=printFlagFileNo.split("@");
			    String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");
				String unitName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				// String
				// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+_dailyPatientVO.getDepartmentCode()+unitCode;
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				_dailyPatientVO.setDepartmentUnit(unitName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				_dailyPatientVO.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				String episodeCode=deptUnitCode+"001";
				_dailyPatientVO.setEpisodeCode(episodeCode);
				///////////
				//for printing formats
				_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
				_dailyPatientVO.setFilePrintSetting(printFlagFileNo.substring(2,5));

				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
					
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
						"Procedure Generate Que No:: returns null value");
			}
						///filenoview same as file number
			_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			
			populateMapNewDepartment(_dailyPatientVO, _userVO, populateMAP);
			////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				_dailyPatientVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return _dailyPatientVO;
	}
	
////Create for new Department visit
	public DailyPatientVO createNewDepartment(HisDAO daoObj,DailyPatientVO _dailyPatientVO, UserVO _userVO,String _referMlcNo)
	{

		String query = "";
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try
		{

			//_dailyPatientVO=generateQueueNumber(_dailyPatientVO, _userVO);
			_dailyPatientVO=generateQueueNumberDeptWise(_dailyPatientVO, _userVO);//Modified by Singaravelan on 25-09-13
			///filenoview same as file number		
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_dailyPatientVO);
			HelperMethods.setNullToEmpty(_dailyPatientVO.getPatAddress());
		/*	
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob());
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge());
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit());
			daoObj.setProcInValue(nProcIndex1, "p_DOB", _dailyPatientVO.getPatDOB());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3));
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict());
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "");
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _userVO.getSeatId());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead());
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate());
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode());
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo());
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcOutValue(nProcIndex1, "err", 1);*/
			
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", (_dailyPatientVO.getPatDOB().equals(null)|| _dailyPatientVO.getPatDOB().equals(""))?"":_dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode()==""?"0":_dailyPatientVO.getPatSecondaryCatCode(),10);
			if(_dailyPatientVO.getPrevCrNo().equalsIgnoreCase("") || _dailyPatientVO.getPrevCrNo().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", "0",11);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo(),11);
			}
			
			if(_dailyPatientVO.getPatDOB().equalsIgnoreCase("") || _dailyPatientVO.getPatDOB().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob","" ,12);
			}
			else
			{
	        daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			}
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC()==""?"0":_dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode().equals("")?"0":_dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate().equals("")?"":_dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3),27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome().equals("")?"0":_dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred().equals("")?"0":_dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode().equals("")?"0":_dailyPatientVO.getPatAddress().getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode().equals("")?"0":_dailyPatientVO.getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN().equals("")?"0": _dailyPatientVO.getPatAddress().getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId().equals("")?_userVO.getSeatId():_dailyPatientVO.getPatAddress().getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree().equals("")?"0":_dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead().equals("")?"0":_dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId().equals("")?"0":_dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate().equals("")?"":_dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode().equals("")?"0":_dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo().equals("")?"0":_dailyPatientVO.getPatMotherCrNo(),82);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", _dailyPatientVO.getPatBirthPlace().equals("")?" ":_dailyPatientVO.getPatBirthPlace(),83);
			if(_dailyPatientVO.getPatDocType()!="")
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":_dailyPatientVO.getPatDocType(),84);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType(),84);
			}
			
			
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", _dailyPatientVO.getPatFirstNameInMultiLang(),85);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", _dailyPatientVO.getPatMiddleNameInMultiLang(),86);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", _dailyPatientVO.getPatLastNameInMultiLang(),87);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,88);
			
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		
				
		return _dailyPatientVO;
	}
////Create for new Department visit
	public DailyPatientVO createNewSplUnit(HisDAO daoObj,DailyPatientVO _dailyPatientVO, UserVO _userVO,String _referMlcNo)
	{

		String query = "";
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try
		{

			_dailyPatientVO=generateQueueNumber(_dailyPatientVO, _userVO);//Modified by Deepika on 25-09-13
			//_dailyPatientVO=generateQueueNumberDeptWise(_dailyPatientVO, _userVO);//Modified by Singaravelan on 25-09-13
			///filenoview same as file number		
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		try 
		{
			strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?)}";
			
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_dailyPatientVO);
			HelperMethods.setNullToEmpty(_dailyPatientVO.getPatAddress());
		/*	
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1");
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob());
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge());
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit());
			daoObj.setProcInValue(nProcIndex1, "p_DOB", _dailyPatientVO.getPatDOB());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3));
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict());
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "");
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed());
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _userVO.getSeatId());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo());
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead());
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId());
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo());
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate());
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode());
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn());
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo());
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcOutValue(nProcIndex1, "err", 1);*/
			
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", (_dailyPatientVO.getPatDOB().equals(null)|| _dailyPatientVO.getPatDOB().equals(""))?"":_dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode()==""?"0":_dailyPatientVO.getPatSecondaryCatCode(),10);
			if(_dailyPatientVO.getPrevCrNo().equalsIgnoreCase("") || _dailyPatientVO.getPrevCrNo().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", "0",11);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo(),11);
			}
			
			if(_dailyPatientVO.getPatDOB().equalsIgnoreCase("") || _dailyPatientVO.getPatDOB().equals(null))
			{
				 daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob","" ,12);
			}
			else
			{
	        daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			}
			
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC()==""?"0":_dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode().equals("")?"0":_dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate().equals("")?"":_dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", _dailyPatientVO.getDepartmentCode().substring(0,3),27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentUnitCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome().equals("")?"0":_dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred().equals("")?"0":_dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode().equals("")?"0":_dailyPatientVO.getPatAddress().getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode().equals("")?"0":_dailyPatientVO.getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN().equals("")?"0": _dailyPatientVO.getPatAddress().getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId().equals("")?_userVO.getSeatId():_dailyPatientVO.getPatAddress().getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno().equals("")?"0":_dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree().equals("")?"0":_dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead().equals("")?"0":_dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId().equals("")?"0":_dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate().equals("")?"":_dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode().equals("")?"0":_dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo().equals("")?"0":_dailyPatientVO.getPatMotherCrNo(),82);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", _dailyPatientVO.getPatBirthPlace().equals("")?" ":_dailyPatientVO.getPatBirthPlace(),83);
			if(_dailyPatientVO.getPatDocType()!="")
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":_dailyPatientVO.getPatDocType(),84);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType(),84);
			}
			
			
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", _dailyPatientVO.getPatFirstNameInMultiLang(),85);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", _dailyPatientVO.getPatMiddleNameInMultiLang(),86);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", _dailyPatientVO.getPatLastNameInMultiLang(),87);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,88);
			
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		
				
		return _dailyPatientVO;
	}
	
	/**
	 * Populates the map with the patient's daily details to be entered in the DB.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _populateMAP Map containig values which will be used for insert query.
	 */
	public void populateMapNewDepartment(DailyPatientVO _dailyPatientVO, UserVO _userVO, Map _populateMAP) throws SQLException
	{
		System.out.println("inside populateMap::" + _dailyPatientVO);

		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());//setting the crno for genrating sl no
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); // generating
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); // sl	
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());//no
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//for
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//visit
		/*_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); //setting the crno for genrating visitno
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); //setting the episode number for genrating visitno
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());*/
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatDOB());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getPatDOB().toString());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsMLC());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsOld());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFirstName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatLastName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatReligionCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMotherName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRegCatCode());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getRegisterDate().toString());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getExpiryDate().toString());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRegisterDate());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getExpiryDate());		 
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentUnitCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getQueNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsUrban());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsReferred());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefDoctor());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getEmergencyType());		 
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getIsAddressDelhi());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddHNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrict());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCity());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCountryCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddPIN());
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeType()); 
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeTypeCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsValid());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatientAllowed());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRoomCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEntryDate());
		_populateMAP.put(sq.next(), _userVO.getIpAddress());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddContactNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddTypeCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getPatCatcode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLoc());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getSeatId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalityCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCrno());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDept());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFatherOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNickName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCardNo());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getFileNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark1());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsFree());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDisasterId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldRegDate()==null||_dailyPatientVO.getOldRegDate().trim().equals("")?"":_dailyPatientVO.getOldRegDate().trim());
	}

	
////Create for new Department visit
	public DailyPatientVO createForceFulVisit(HisDAO daoObj,DailyPatientVO _dailyPatientVO, UserVO _userVO,String _referMlcNo)
	{

		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		String strErr = "";
		int nfuncIndex = 0;
		String strFunc="";
		
		// call the getQueryMethod with arguments filename,querykey from prop file
		
		_dailyPatientVO=generateQueueNumberDepartmentWise(_dailyPatientVO, _userVO);
		
		strFunc = "{? = call REG_FUNCTION.puk_episode_gen(?,?,?)}";
		//////generate episode code
		try
		{
			nfuncIndex = daoObj.setFunction(strFunc);
			
			daoObj.setFuncInValue(nfuncIndex, 2, _dailyPatientVO.getPatCrNo());
			daoObj.setFuncInValue(nfuncIndex, 3, _userVO.getHospitalCode());
			daoObj.setFuncInValue(nfuncIndex, 4, _dailyPatientVO.getDepartmentUnitCode());
			daoObj.setFuncOutValue(nfuncIndex, 3);
		
			daoObj.executeFuncForNumeric(nfuncIndex);
		
			String episodeNo = daoObj.getFuncNumeric(nfuncIndex);
			
			_dailyPatientVO.setEpisodeCode(episodeNo);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		///////////

		try
		{
			
		////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				_dailyPatientVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		return _dailyPatientVO;
	}
	public DailyPatientVO createForceFulVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO,String _referMlcNo)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//System.out.println("query" + query);

		
		
		try
		{

			if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( _dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC)) 	)
			{
				// code to execute procedure for queue no for special clinic

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_SPECIAL_CLINIC_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String fileNo=(String) strProc.getParameterAt(10); 
				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				//_dailyPatientVO.setDepartment(deptName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				
				
				
				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
				
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			}
			else if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL))
			{

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_TOKEN_DTL_NEW);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addOutParameter(5, Types.VARCHAR);
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(9, Types.VARCHAR,_dailyPatientVO.getIsNewFileRequired());//to be pass ed from new and old process
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				String roomCode = (String) strProc.getParameterAt(5);
				String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
				String [] printFlagArray=printFlagFileNo.split("@");
				String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");


				String unitName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				// String
				// deptUnitCode=RegistrationDaoConfig.HARDCODE_HOSPITAL_CODE+_dailyPatientVO.getDepartmentCode()+unitCode;
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(roomCode);
				_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
				_dailyPatientVO.setPatientAllowed(unitCap);
				_dailyPatientVO.setDepartmentUnit(unitName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				
			
				//for printing formats
				_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
				_dailyPatientVO.setFilePrintSetting(printFlagFileNo.substring(2,5));

				if(Config.FILE_NO_GENERATION_FLAG.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
					
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
						"Procedure Generate Que No:: returns null value");
			}
						///filenoview same as file number
			_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		
		//////generate episode code
		try
		{
			
				Procedure strEpisodeProc = new Procedure(RegistrationDaoConfig.PROCEDURE_GET_EPISODENO);
				strEpisodeProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getPatCrNo());
				strEpisodeProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
				strEpisodeProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strEpisodeProc.setReturnType(Types.VARCHAR);
				String episodeNo = (String) strEpisodeProc.execute(conn);
				_dailyPatientVO.setEpisodeCode(episodeNo);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:prepareCall()::=call generateEPISODEcode(?)" + e);
		}

		///////////

		try
		{
			
			populateMapForceFulVisit(_dailyPatientVO, _userVO, populateMAP);
			////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				_dailyPatientVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return _dailyPatientVO;
	}

	/**
	 * Populates the map with the patient's daily details to be entered in the DB.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _populateMAP Map containig values which will be used for insert query.
	 */
	public void populateMapForceFulVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO, Map _populateMAP) throws SQLException
	{
		System.out.println("inside populateMap::" + _dailyPatientVO);

		Sequence sq = new Sequence();

		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo());//setting the crno for genrating sl no
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); // generating
		//_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); // sl	
		//_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());//no
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//for
		//_populateMAP.put(sq.next(), _userVO.getHospitalCode());//visit
		/*_populateMAP.put(sq.next(), _dailyPatientVO.getPatCrNo()); //setting the crno for genrating visitno
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode()); //setting the episode number for genrating visitno
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());*/
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeVisitNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatPrimaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatSecondaryCatCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPrevCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatDOB());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getPatDOB().toString());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsMLC());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsActualDob());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsOld());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFirstName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMiddleName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatLastName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGenderCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMaritalStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatReligionCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatStatusCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatGuardianName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMotherName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRegCatCode());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getRegisterDate().toString());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getExpiryDate().toString());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRegisterDate());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getExpiryDate());		 
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDepartmentUnitCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getQueNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsUnknown());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatMonthlyIncome());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAmountCollected());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIsUrban());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsReferred());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefDoctor());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getEmergencyType());		 
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getIsAddressDelhi());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddHNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLocCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrict());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddDistrictCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCity());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddStateName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCountryCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddPIN());
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeCode());
		//_populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeType()); 
		_populateMAP.put(sq.next(), _dailyPatientVO.getEpisodeTypeCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeDate());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsValid());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatientAllowed());
		_populateMAP.put(sq.next(), _dailyPatientVO.getRoomCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEntryDate());
		_populateMAP.put(sq.next(), _userVO.getIpAddress());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddContactNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefHospitalName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddTypeCode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getPatCatcode());
		// _populateMAP.put(sq.next(),_dailyPatientVO.getEpisodeTypeCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getPatAddCityLoc());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatAddress().getSeatId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalityCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalCrno());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDept());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatRefGnctdHospitalDeptUnit());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNationalId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatFatherOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatHusbandOccupation());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatNickName());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatCardNo());
		_populateMAP.put(sq.next(), _userVO.getHospitalCode());
		_populateMAP.put(sq.next(), _dailyPatientVO.getFileNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark1());
		_populateMAP.put(sq.next(), _dailyPatientVO.getPatIdMark2());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsFree());
		_populateMAP.put(sq.next(), _dailyPatientVO.getIsBroughtDead());
		_populateMAP.put(sq.next(), _dailyPatientVO.getDisasterId());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldCrNo());
		_populateMAP.put(sq.next(), _dailyPatientVO.getOldRegDate());
	}

	public void updateUnknownConversion(HisDAO daoObj, DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		//String queryKey = "UPDATE.UNKNOWN_CONVERSION.HRGT_DAILY_PATIENT_DTL";
		String strErr = "";
		int nProcIndex1 = 0;
		String strProcName1="";
		try 
		{
			//strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?,?,? ,?,?,?)}";
			
			  strProcName1 = "{call pkg_reg_dml.proc_hrgt_daily_patient_dtl(?,?,?,?,?,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?::numeric,?,?::numeric,?::numeric,?::numeric,?,?,?,?,?,?,?,?,?,?::numeric,?,?,?,?::numeric,?::numeric,?::numeric,?,?,?::numeric,?::numeric,?::numeric,?::numeric,?,?::numeric,?,?,?,?)}";
			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			HelperMethods.setNullToEmpty(_dailyPatientVO);
			HelperMethods.setNullToEmpty(_dailyPatientVO.getPatAddress());
			
			
			daoObj.setProcInValue(nProcIndex1, "p_modeVal", "3",1);
			daoObj.setProcInValue(nProcIndex1, "p_isActialDOB",_dailyPatientVO.getIsActualDob(),2);
			daoObj.setProcInValue(nProcIndex1, "p_age", _dailyPatientVO.getPatAge(),3);
			daoObj.setProcInValue(nProcIndex1, "p_ageUnit", _dailyPatientVO.getPatAgeUnit(),4);
			daoObj.setProcInValue(nProcIndex1, "p_DOB", _dailyPatientVO.getPatDOB(),5);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_puk", _dailyPatientVO.getPatCrNo(),6);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_visit_no", _dailyPatientVO.getEpisodeVisitNo(),7);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_patient_cat_code", _dailyPatientVO.getPatPrimaryCatCode(),8);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_treatment_cat_code", _dailyPatientVO.getPatSecondaryCatCode(),9);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_prev_puk", _dailyPatientVO.getPrevCrNo(),10);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_id_no", _dailyPatientVO.getPatIdNo(),11);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_dob", _dailyPatientVO.getPatDOB(),12);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_mlc", _dailyPatientVO.getIsMLC(),13);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_actual_dob", _dailyPatientVO.getIsActualDob(),14);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_old", _dailyPatientVO.getPatIsOld(),15);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname", _dailyPatientVO.getPatFirstName(),16);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname", _dailyPatientVO.getPatMiddleName(),17);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname", _dailyPatientVO.getPatLastName(),18);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_gender_code", _dailyPatientVO.getPatGenderCode(),19);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_marital_status_code", _dailyPatientVO.getPatMaritalStatusCode(),20);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_religion_code", _dailyPatientVO.getPatReligionCode(),21);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_pat_status_code", _dailyPatientVO.getPatStatusCode(),22);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_father_name", _dailyPatientVO.getPatGuardianName(),23);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_momname", _dailyPatientVO.getPatMotherName(),24);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_reg_cat_code", _dailyPatientVO.getPatRegCatCode(),25);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_register_date", _dailyPatientVO.getRegisterDate(),26);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_dept_code", "",27);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_deptunitcode", _dailyPatientVO.getDepartmentCode(),28);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_que_no", _dailyPatientVO.getQueNo(),29);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isunknown", _dailyPatientVO.getIsUnknown(),30);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_month_income", _dailyPatientVO.getPatMonthlyIncome(),31);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_amt_collected", _dailyPatientVO.getPatAmountCollected(),32);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_urban", _dailyPatientVO.getPatIsUrban(),33);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_ref", _dailyPatientVO.getIsReferred(),34);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_code", _dailyPatientVO.getPatRefGnctdHospitalCode(),35);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hosp_doct_name", _dailyPatientVO.getPatRefDoctor(),36);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_local", _dailyPatientVO.getPatAddress().getIsAddressDelhi(),37);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_house_no", _dailyPatientVO.getPatAddress().getPatAddHNo(),38);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_city_loc_code", _dailyPatientVO.getPatAddress().getPatAddCityLocCode(),39);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_district", _dailyPatientVO.getPatAddress().getPatAddDistrict(),40);
			daoObj.setProcInValue(nProcIndex1, "p_num_dist_id", _dailyPatientVO.getPatAddress().getPatAddDistrictCode(),41);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city", _dailyPatientVO.getPatAddress().getPatAddCity(),42);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_statecode", _dailyPatientVO.getPatAddress().getPatAddStateCode(),43);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_state_name", _dailyPatientVO.getPatAddress().getPatAddStateName(),44);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_countrycode", _dailyPatientVO.getPatAddress().getPatAddCountryCode(),45);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_pin", _dailyPatientVO.getPatAddress().getPatAddPIN(),46);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_code", _dailyPatientVO.getEpisodeCode(),47);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_episode_type_code", _dailyPatientVO.getEpisodeTypeCode(),48);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_episode_date", "",49);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_isvalid", _dailyPatientVO.getIsValid(),50);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_patient_allowed", _dailyPatientVO.getPatientAllowed(),51);
			daoObj.setProcInValue(nProcIndex1, "p_hgnum_room_code", _dailyPatientVO.getRoomCode(),52);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ip_add", _userVO.getIpAddress(),53);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_contact_no", _dailyPatientVO.getPatAddress().getPatAddContactNo(),54);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_ext_hospital_name", _dailyPatientVO.getPatRefHospitalName(),55);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_add_type_code", _dailyPatientVO.getPatAddress().getPatAddTypeCode(),56);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_city_location", _dailyPatientVO.getPatAddress().getPatAddCityLoc(),57);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_seat_id", _dailyPatientVO.getPatAddress().getSeatId(),58);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_nationality_code", _dailyPatientVO.getPatNationalityCode(),59);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_crno", _dailyPatientVO.getPatRefGnctdHospitalCrno(),60);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hospital_dep", _dailyPatientVO.getPatRefGnctdHospitalDept(),61);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_ext_hosp_dept_unit", _dailyPatientVO.getPatRefGnctdHospitalDeptUnit(),62);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_spousename", _dailyPatientVO.getPatHusbandName(),63);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_national_id", _dailyPatientVO.getPatNationalId(),64);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_pat_occupation", _dailyPatientVO.getPatOccupation(),65);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fat_occupation", _dailyPatientVO.getPatFatherOccupation(),66);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_hus_occupation", _dailyPatientVO.getPatHusbandOccupation(),67);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_nickname", _dailyPatientVO.getPatNickName(),68);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_card_no", _dailyPatientVO.getPatCardNo(),69);
			daoObj.setProcInValue(nProcIndex1, "p_gnum_hospital_code", _userVO.getHospitalCode(),70);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_file_no", _dailyPatientVO.getFileNo(),71);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark1", _dailyPatientVO.getPatIdMark1(),72);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_idmark2", _dailyPatientVO.getPatIdMark2(),73);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isreg_free", _dailyPatientVO.getIsFree(),74);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_is_broughtdead", _dailyPatientVO.getIsBroughtDead(),75);
			daoObj.setProcInValue(nProcIndex1, "p_hdsnum_disaster_id", _dailyPatientVO.getDisasterId(),76);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_deptunit_oldpuk", _dailyPatientVO.getOldCrNo(),77);
			daoObj.setProcInValue(nProcIndex1, "p_hrgdt_deptunit_oldregdate", _dailyPatientVO.getOldRegDate(),78);
			daoObj.setProcInValue(nProcIndex1, "p_num_caste_id", _dailyPatientVO.getPatCasteCode(),79);
			daoObj.setProcInValue(nProcIndex1, "p_hbnum_bldgrp_code", _dailyPatientVO.getPatBloodGroupCode(),80);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_isnewborn", _dailyPatientVO.getIsNewBorn(),81);
			daoObj.setProcInValue(nProcIndex1, "p_hrgnum_mother_puk", _dailyPatientVO.getPatMotherCrNo(),82);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_birth_place", _dailyPatientVO.getPatBirthPlace().equals("")?" ":_dailyPatientVO.getPatBirthPlace(),83);
			if(_dailyPatientVO.getPatDocType()!="")
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType().equalsIgnoreCase("-1")?"-1":_dailyPatientVO.getPatDocType().substring(0, 3),84);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "p_hgnum_id_doc_code", _dailyPatientVO.getPatDocType(),84);
			}			
			//daoObj.setProcOutValue(nProcIndex1, "patDOB", 1);
			//daoObj.setProcOutValue(nProcIndex1, "patAge", 1);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_fname_local", _dailyPatientVO.getPatFirstNameInMultiLang(),85);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_mname_local", _dailyPatientVO.getPatMiddleNameInMultiLang(),86);
			daoObj.setProcInValue(nProcIndex1, "p_hrgstr_lname_local", _dailyPatientVO.getPatLastNameInMultiLang(),87);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,88);
			
			daoObj.execute(nProcIndex1,1);			
			
			//strErr = daoObj.getString(nProcIndex1, "err");
				if (strErr == null)
					strErr = "";

					if (!strErr.equals("")) 
					{
						throw new Exception(strErr);
					}
					else
					{
						//_dailyPatientVO.setPatDOB(daoObj.getString(nProcIndex1, "patDOB"));
						//_dailyPatientVO.setPatAge(daoObj.getString(nProcIndex1, "patAge"));
					}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
	}
	
	public int updateChangeRoomDetail(RoomChangeVO roomChangeVO,UserVO _userVO)
	{
		int x = 0;
		Sequence sq = new Sequence();
		String query = "";
		Map _populateUpdateMap = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.HRGT_DAILY_PATIENT_DTL.ROOM_CHANGE_DETAIL";
		Connection conn = super.getTransactionContext().getConnection();
		System.out.println("After obtaining connection in update of PatientDAO ");
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);

		try
		{

			_populateUpdateMap.put(sq.next(), roomChangeVO.getChangeType());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getFromRoomCode());// it may change so ask
			_populateUpdateMap.put(sq.next(), roomChangeVO.getToDeptUnitCode());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getToRoomCode());
			
			_populateUpdateMap.put(sq.next(), roomChangeVO.getPatCrNo());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getEpisodeCode());
			_populateUpdateMap.put(sq.next(), roomChangeVO.getEpisodeVisitNo());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("populatin map:" + e);
		}

		try
		{
			x = HelperMethodsDAO.excecuteUpdate(conn, query, _populateUpdateMap);
			if (x == 0)
			{
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException("update unsuccessful::" + e);
			}
			else throw new HisDataAccessException("DailyPatientDAO::while updating data " + e);
		}
		return x;
	}
	
	public DailyPatientVO generateQueueNumberRoomWise(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
				
		try
		{
						
			///Deciding file generation type
			String fileNoGeneration="";
			
			if(_dailyPatientVO.getFileNo()==null || _dailyPatientVO.getFileNo().equals(""))
				fileNoGeneration=Config.FILE_NO_GENRATION_AUTO_TRUE;
			else
				fileNoGeneration=Config.FILE_NO_GENRATION_MANNUAL_TRUE;
			
				// code to execute procedure for queue no

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_GET_TOKEN_DTL);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addInParameter(5, Types.NUMERIC,_dailyPatientVO.getRoomCode());
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				//strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);/// also commented setfileno method
				strProc.addInParameter(9, Types.VARCHAR,fileNoGeneration);
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				//String roomCode = (String) strProc.getParameterAt(5);
				//String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String printFlagFileNo=(String) strProc.getParameterAt(10); 
				
				String [] printFlagArray=null;
				if(printFlagFileNo!=null && printFlagFileNo.length() > 0)
					printFlagArray=printFlagFileNo.split("@");
				String fileNo=(printFlagArray!=null && printFlagArray.length>1?printFlagArray[1]:"");

				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				System.out.println("unitConsultant-----"+unitConsultant);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(_dailyPatientVO.getRoomCode());
				_dailyPatientVO.setDepartmentUnitCode(_dailyPatientVO.getDepartmentUnitCode());
				_dailyPatientVO.setPatientAllowed(unitCap);
				//_dailyPatientVO.setDepartment(deptName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				_dailyPatientVO.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				String episodeCode=_dailyPatientVO.getDepartmentUnitCode()+"001";
				_dailyPatientVO.setEpisodeCode(episodeCode);
				///////////
				//for printing formats
				_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
				_dailyPatientVO.setFilePrintSetting(printFlagFileNo.substring(2,5));
			
				if(fileNoGeneration.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					_dailyPatientVO.setFileNo(fileNo);
				
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
							
			///filenoview same as file number
			_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		return _dailyPatientVO;
	}
	//offline registration now createNewregistrationMethod is used
	/*public DailyPatientVO createNewOfflineRegistration(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL_NEW_REGISTRATION";
		String queryKeyGenrateRoomEmg="GENRATE_ROOM_CODE.EMERGENCY";
		String queryGenrateRoomEmg="";
		Map populateMAPGenrateRoomEmg=new HashMap();
		String roomCodeEmg=null;
		
		

		try
		{
			populateMapNewRegistration(_dailyPatientVO, _userVO, populateMAP);
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return _dailyPatientVO;
	}*/

////Create for new Department visit roomWise
	public DailyPatientVO createNewDepartmentRoomWise(DailyPatientVO _dailyPatientVO, UserVO _userVO,String _referMlcNo)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL";
		
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		//System.out.println("query" + query);

				
		try
		{
			
			///Deciding file generation type
			String fileNoGeneration="";
			
			if(_dailyPatientVO.getFileNo()==null || _dailyPatientVO.getFileNo().equals(""))
				fileNoGeneration=Config.FILE_NO_GENRATION_AUTO_TRUE;
			else
				fileNoGeneration=Config.FILE_NO_GENRATION_MANNUAL_TRUE;

			Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_GET_TOKEN_DTL);
			strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
			strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
			strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addInParameter(5, Types.NUMERIC,_dailyPatientVO.getRoomCode());
			strProc.addOutParameter(6, Types.VARCHAR);
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
			//strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);/// also commented setfileno method
			strProc.addInParameter(9, Types.VARCHAR,fileNoGeneration);
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
			strProc.addOutParameter(12, Types.VARCHAR);
			strProc.addOutParameter(13, Types.VARCHAR);
			strProc.addOutParameter(14, Types.VARCHAR);
			strProc.addOutParameter(15, Types.VARCHAR);
			strProc.addOutParameter(16, Types.VARCHAR);
			strProc.execute(conn);
			String queNo = (String) strProc.getParameterAt(4);
			//String roomCode = (String) strProc.getParameterAt(5);
			//String deptUnitCode = (String) strProc.getParameterAt(6);
			String unitCap = (String) strProc.getParameterAt(7);
			String printFlagFileNo=(String) strProc.getParameterAt(10); 
			String [] printFlagArray=printFlagFileNo.split("@");
			String fileNo=(printFlagArray.length>1?printFlagArray[1]:"");

			String deptName=(String) strProc.getParameterAt(12);
			String roomName=(String) strProc.getParameterAt(13);
			String status=(String) strProc.getParameterAt(14);
			String unitDays=(String) strProc.getParameterAt(15);
			String unitConsultant=(String) strProc.getParameterAt(16);
			System.out.println("unitConsultant----"+unitConsultant);
			
			if(status.equals("2"))
				throw new HisAppointmentNotAvailableException("Appointment Not Available");
			
			_dailyPatientVO.setQueNo(queNo);
			_dailyPatientVO.setRoomCode(_dailyPatientVO.getRoomCode());
			_dailyPatientVO.setDepartmentUnitCode(_dailyPatientVO.getDepartmentUnitCode());
			_dailyPatientVO.setPatientAllowed(unitCap);
			//_dailyPatientVO.setDepartment(deptName);
			_dailyPatientVO.setRoom(roomName);
			_dailyPatientVO.setUnitWorkingDays(unitDays);
			_dailyPatientVO.setUnitConsultant(unitConsultant);
			
			//////generate episode code
			String episodeCode=_dailyPatientVO.getDepartmentUnitCode()+"001";
			_dailyPatientVO.setEpisodeCode(episodeCode);
			///////////
			//for printing formats
			_dailyPatientVO.setCardPrintSetting(printFlagFileNo.substring(0,2));
			_dailyPatientVO.setFilePrintSetting(printFlagFileNo.substring(2,5));

			if(fileNoGeneration.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
				_dailyPatientVO.setFileNo(fileNo);
			
			if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
					|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
				throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
						///filenoview same as file number
			_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
			
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			
			populateMapNewDepartment(_dailyPatientVO, _userVO, populateMAP);
			////seting type code in case referral episode is mlc
			if(_referMlcNo!=null && !_referMlcNo.equals(""))
			{
				_dailyPatientVO.setEpisodeTypeCode(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC);
				
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return _dailyPatientVO;
	}
	
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(String mode,String departmentUnitCode, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DailyPatientVO []dPatientVO=null;
		ValueObject[] valueObjects = null;
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey ="";
		if(mode.equals(RegistrationConfig.MODE_FILE_NUMBER_PRINT)){
			queryKey = "SELECT.TODAY_PATIENT_FOR_FILE_NO_PRINTING.HRGT_DAILY_PATIENT_DTL";
			
		}else
			queryKey="SELECT.TODAY_PATIENT_FOR_SLIP_PRINTING.HRGT_DAILY_PATIENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 populateMAP.put(sq.next(), _userVO.getHospitalCode());
		 //if(!departmentUnitCode.equals("%"))
		//	 populateMAP.put(sq.next(), departmentUnitCode.substring(0,3));
		// else
			 //populateMAP.put(sq.next(), departmentUnitCode);
		 populateMAP.put(sq.next(), departmentUnitCode);
		 /*if(mode.equals(RegistrationConfig.MODE_FILE_NUMBER_PRINT)){
			 populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_GENERAL);
		 }*/
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.beforeFirst();
			valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
			dPatientVO = new DailyPatientVO[valueObjects.length];
			for (int i = 0; i < valueObjects.length; i++)
			{
				dPatientVO[i] = (PatientDetailVO) valueObjects[i];
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
		return dPatientVO;
	}
	
	
	public void updateFilePrintFlag(DailyPatientVO dailyPatientVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "UPDATE.FILE_PRINT_FLAG.HRGT_DAILY_PATIENT_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);//pring flag
		populateMAP.put(sq.next(), dailyPatientVO.getPatCrNo());
		populateMAP.put(sq.next(), dailyPatientVO.getEpisodeCode());
		populateMAP.put(sq.next(), dailyPatientVO.getEpisodeVisitNo());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("Application Execution Exception" + e);
		}
		
	}
	
	
	
	//old patient dept visit stamp room wise
	public DailyPatientVO createOldPatientDeptVisitRoomWise(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "INSERT.HRGT_DAILY_PATIENT_DTL_NEW_REGISTRATION";
		//String queryKeyGenrateRoomEmg="GENRATE_ROOM_CODE.EMERGENCY";
		//String queryGenrateRoomEmg="";
		//Map populateMAPGenrateRoomEmg=new HashMap();
		//String roomCodeEmg=null;
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		try
		{
						
			///Deciding file generation type
			String fileNoGeneration="";
			
			//if(_dailyPatientVO.getFileNo()==null || _dailyPatientVO.getFileNo().equals(""))
				//fileNoGeneration=Config.FILE_NO_GENRATION_AUTO_TRUE;
			//else
				fileNoGeneration=Config.FILE_NO_GENRATION_MANNUAL_TRUE;
			
				// code to execute procedure for queue no

				Procedure strProc = new Procedure(RegistrationDaoConfig.PROCEDURE_OFFLINE_REGISTRATION_GET_TOKEN_DTL);
				strProc.addInParameter(1, Types.VARCHAR, _dailyPatientVO.getDepartmentUnitCode());
				strProc.addInParameter(2, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addInParameter(3, Types.VARCHAR, _dailyPatientVO.getPatPrimaryCatCode());
				strProc.addOutParameter(4, Types.VARCHAR);
				strProc.addInParameter(5, Types.NUMERIC,_dailyPatientVO.getRoomCode());
				strProc.addOutParameter(6, Types.VARCHAR);
				strProc.addOutParameter(7, Types.VARCHAR);
				strProc.addInParameter(8, Types.VARCHAR, _userVO.getHospitalCode());
				//strProc.addInParameter(9, Types.VARCHAR,Config.FILE_NO_GENERATION_FLAG);/// also commented setfileno method
				strProc.addInParameter(9, Types.VARCHAR,fileNoGeneration);
				strProc.addOutParameter(10, Types.VARCHAR);
				strProc.addInParameter(11, Types.VARCHAR, _dailyPatientVO.getRoomUsability());
				strProc.addOutParameter(12, Types.VARCHAR);
				strProc.addOutParameter(13, Types.VARCHAR);
				strProc.addOutParameter(14, Types.VARCHAR);
				strProc.addOutParameter(15, Types.VARCHAR);
				strProc.addOutParameter(16, Types.VARCHAR);
				strProc.execute(conn);
				String queNo = (String) strProc.getParameterAt(4);
				//String roomCode = (String) strProc.getParameterAt(5);
				//String deptUnitCode = (String) strProc.getParameterAt(6);
				String unitCap = (String) strProc.getParameterAt(7);
				String fileNo=(String) strProc.getParameterAt(10); 
				String deptName=(String) strProc.getParameterAt(12);
				String roomName=(String) strProc.getParameterAt(13);
				String status=(String) strProc.getParameterAt(14);
				String unitDays=(String) strProc.getParameterAt(15);
				String unitConsultant=(String) strProc.getParameterAt(16);
				System.out.println("unitConsultant-----"+unitConsultant);
				if(status.equals("2"))
					throw new HisAppointmentNotAvailableException("Appointment Not Available");
				
				_dailyPatientVO.setQueNo(queNo);
				_dailyPatientVO.setRoomCode(_dailyPatientVO.getRoomCode());
				_dailyPatientVO.setDepartmentUnitCode(_dailyPatientVO.getDepartmentUnitCode());
				_dailyPatientVO.setPatientAllowed(unitCap);
				//_dailyPatientVO.setDepartment(deptName);
				_dailyPatientVO.setRoom(roomName);
				_dailyPatientVO.setUnitWorkingDays(unitDays);
				_dailyPatientVO.setUnitConsultant(unitConsultant);
				
				//////generate episode code
				//old episode code will be continued
				//String episodeCode=_dailyPatientVO.getDepartmentUnitCode()+"001";
				//_dailyPatientVO.setEpisodeCode(episodeCode);
				//increase the visit no by 1
				//_dailyPatientVO.setEpisodeVisitNo(String.valueOf(Integer.parseInt(_dailyPatientVO.getEpisodeVisitNo()+1)));
				///////////
				
				//if(fileNoGeneration.equals(Config.FILE_NO_GENRATION_AUTO_TRUE))
					//_dailyPatientVO.setFileNo(fileNo);
				
				if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
						|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) 
					throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value");
			///filenoview same as file number
			_dailyPatientVO.setFileNoView(_dailyPatientVO.getFileNo());
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}

		try
		{
			
			populateMapNewRegistration(_dailyPatientVO, _userVO, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("DailyPatientDAO:populateMap(_dailyPatientVO,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return _dailyPatientVO;
	}
	public DailyPatientVO generateQueueNumberDepartmentWise(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		HisDAO daoObj=null;
		String strProcName1="";
		int nProcIndex1=0;
		String strErr="";
		try
		{		
			
			/*
			 * 1	p_modval in varchar2 default '1',
				2 i_dept_code IN HRGT_DAILY_PATIENT_DTL.GNUM_DEPT_CODE%TYPE,
				3 pri_cat IN  GBLT_PATIENT_CAT_MST.GNUM_PATIENT_CAT_CODE%TYPE,
				4 sec_cat IN  GBLT_PATIENT_CAT_MST.GNUM_PATIENT_CAT_CODE%TYPE,
				5 o_que_no OUT HRGT_DAILY_PATIENT_DTL.HRGNUM_QUE_NO%TYPE,
				6 o_room_code OUT HRGT_DAILY_PATIENT_DTL.HGNUM_ROOM_CODE%TYPE,
				7 o_unit OUT HGBT_UNIT_MST.HGNUM_DEPTUNITCODE%TYPE,
				8 o_cap OUT HRGT_DAILY_PATIENT_DTL.HRGNUM_PATIENT_ALLOWED%TYPE,
				9 i_hospital_code IN GBLT_HOSPITAL_MST.GNUM_HOSPITAL_CODE%TYPE,
				10 fileflag IN NUMBER,
				11 filenumber OUT VARCHAR2,
				12 usability_flag in HOPT_DEPT_UNIT_ROSTER_DTL.HRGNUM_ROOM_USABILITY%type default 0,
				13 unit_name out varchar2,
				14 room_name out varchar2,
				15 status out varchar2,
				16 unittime out varchar2,
				17 unit_inc_name out varchar2,
				18 cardSetting OUT VARCHAR2, 
				19 err OUT VARCHAR2
			 * 
			 * 
			 * if (_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_SPECIAL) || 
					(_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_MLC)) || 
					( _dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_EMG_NO_MLC))||_dailyPatientVO.getEpisodeTypeCode().equals(RegistrationConfig.EPISODE_TYPE_CODE_OPD_GENERAL) )
			{*/
				
				strProcName1 = "{call PKG_REG_DML.gen_queno_deptwise(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";  // Total 18 Variables

				daoObj = new HisDAO("Registration","DailyPatientDAO");

				nProcIndex1 = daoObj.setProcedure(strProcName1);

				daoObj.setProcInValue(nProcIndex1, "p_modval", "1",1);
				daoObj.setProcInValue(nProcIndex1, "i_dept_code", _dailyPatientVO.getDepartmentUnitCode().substring(0, 3),2);//_dailyPatientVO.getDepartmentCode()
				daoObj.setProcInValue(nProcIndex1, "pri_cat", _dailyPatientVO.getPatPrimaryCatCode(),3);
				daoObj.setProcInValue(nProcIndex1, "sec_cat", _dailyPatientVO.getPatPrimaryCatCode(),4);
				daoObj.setProcOutValue(nProcIndex1, "o_que_no", 1,5);
				daoObj.setProcOutValue(nProcIndex1, "o_room_code",1,6);
				daoObj.setProcOutValue(nProcIndex1, "o_unit", 1,7);
				daoObj.setProcOutValue(nProcIndex1, "o_cap",1,8);				
				daoObj.setProcInValue(nProcIndex1, "i_hospital_code", _userVO.getHospitalCode(),9);
				//daoObj.setProcInValue(nProcIndex1, "fileflag","0");
				daoObj.setProcOutValue(nProcIndex1, "filenumber",1,10);
				daoObj.setProcInValue(nProcIndex1, "usability_flag", (_dailyPatientVO.getRoomUsability()!=null)?"0":"0",11);				
				daoObj.setProcOutValue(nProcIndex1, "unit_name",1,12);
				daoObj.setProcOutValue(nProcIndex1, "room_name", 1,13);
				daoObj.setProcOutValue(nProcIndex1, "status", 1,14);
				daoObj.setProcOutValue(nProcIndex1, "unittime", 1,15);
				daoObj.setProcOutValue(nProcIndex1, "unit_inc_name", 1,16);
				daoObj.setProcOutValue(nProcIndex1, "cardSetting", 1,17);				
				daoObj.setProcOutValue(nProcIndex1, "err", 1,18);
				
				
				
				
				daoObj.executeProcedureByPosition(nProcIndex1);
				strErr = daoObj.getString(nProcIndex1, "err");
					if (strErr == null)
						strErr = "";
		
						if (!strErr.equals("")) 
						{
							System.out.println(strErr);
							throw new Exception(strErr);
						}
						else
						{
							String queNo = daoObj.getString(nProcIndex1, "o_que_no");
							String roomCode = daoObj.getString(nProcIndex1, "o_room_code");
							String deptUnitCode = daoObj.getString(nProcIndex1, "o_unit");
							String unitCap = daoObj.getString(nProcIndex1, "o_cap");
							String unitName=daoObj.getString(nProcIndex1, "unit_name");
							String roomName=daoObj.getString(nProcIndex1, "room_name");
							String status=daoObj.getString(nProcIndex1, "status");
							String unitDays=daoObj.getString(nProcIndex1, "unittime");
							String unitConsultant = daoObj.getString(nProcIndex1, "unit_inc_name");
							String cardPrintSetting = daoObj.getString(nProcIndex1, "cardSetting");
							
							if(status.equals("2"))
								throw new HisAppointmentNotAvailableException("Appointment Not Available");
							
							_dailyPatientVO.setQueNo(queNo);
							_dailyPatientVO.setRoomCode(roomCode);
							_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
							_dailyPatientVO.setPatientAllowed(unitCap);
							_dailyPatientVO.setDepartmentUnit(unitName);
							_dailyPatientVO.setRoom(roomName);
							_dailyPatientVO.setUnitWorkingDays(unitDays);
							_dailyPatientVO.setUnitConsultant(unitConsultant);
							_dailyPatientVO.setCardPrintSetting(cardPrintSetting);
							//////generate episode code
							String episodeCode=deptUnitCode+"001";
							_dailyPatientVO.setEpisodeCode(episodeCode);
							///////////
							//for printing formats
							//_dailyPatientVO.setCardPrintSetting("");
							_dailyPatientVO.setFilePrintSetting("");
		
							_dailyPatientVO.setFileNo("");
								
							if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
									|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
									"Procedure Generate Que No:: returns null value");
							_dailyPatientVO.setFileNoView("");
						}
			//}
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _dailyPatientVO;
	}
	
	//Added by Singaravelan on 23-09-13 
	public DailyPatientVO generateQueueNumberDeptWise(DailyPatientVO _dailyPatientVO, UserVO _userVO)
	{
		Connection conn = super.getTransactionContext().getConnection();
		ResultSet rs=null;
		HisDAO daoObj=null;
		String strProcName1="";
		int nProcIndex1=0;
		String strErr="";
		try
		{					
				
				strProcName1 = "{call PKG_REG_DML.gen_queno_deptwise(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";  // Total 18 Variables

				daoObj = new HisDAO("Registration","DailyPatientDAO");

				nProcIndex1 = daoObj.setProcedure(strProcName1);

				daoObj.setProcInValue(nProcIndex1, "p_modval", "1",1);
				daoObj.setProcInValue(nProcIndex1, "i_dept_code", _dailyPatientVO.getDepartmentCode(),2);//_dailyPatientVO.getDepartmentCode()
				daoObj.setProcInValue(nProcIndex1, "pri_cat", _dailyPatientVO.getPatPrimaryCatCode(),3);
				daoObj.setProcInValue(nProcIndex1, "sec_cat", _dailyPatientVO.getPatPrimaryCatCode(),4);
				daoObj.setProcOutValue(nProcIndex1, "o_que_no", 1,5);
				daoObj.setProcOutValue(nProcIndex1, "o_room_code",1,6);
				daoObj.setProcOutValue(nProcIndex1, "o_unit", 1,7);
				daoObj.setProcOutValue(nProcIndex1, "o_cap",1,8);				
				daoObj.setProcInValue(nProcIndex1, "i_hospital_code", _userVO.getHospitalCode(),9);
				//daoObj.setProcInValue(nProcIndex1, "fileflag","0");
				daoObj.setProcOutValue(nProcIndex1, "filenumber",1,10);
				daoObj.setProcInValue(nProcIndex1, "usability_flag", (_dailyPatientVO.getRoomUsability()!=null)?"0":"0",11);				
				daoObj.setProcOutValue(nProcIndex1, "unit_name",1,12);
				daoObj.setProcOutValue(nProcIndex1, "room_name", 1,13);
				daoObj.setProcOutValue(nProcIndex1, "status", 1,14);
				daoObj.setProcOutValue(nProcIndex1, "unittime", 1,15);
				daoObj.setProcOutValue(nProcIndex1, "unit_inc_name", 1,16);
				daoObj.setProcOutValue(nProcIndex1, "cardSetting", 1,17);				
				daoObj.setProcOutValue(nProcIndex1, "err", 1,18);
				
				
				
				
				daoObj.executeProcedureByPosition(nProcIndex1);
				strErr = daoObj.getString(nProcIndex1, "err");
					if (strErr == null)
						strErr = "";
		
						if (!strErr.equals("")) 
						{
							System.out.println(strErr);
							throw new Exception(strErr);
						}
						else
						{
							String queNo = daoObj.getString(nProcIndex1, "o_que_no");
							String roomCode = daoObj.getString(nProcIndex1, "o_room_code");
							String deptUnitCode = daoObj.getString(nProcIndex1, "o_unit");
							String unitCap = daoObj.getString(nProcIndex1, "o_cap");
							String unitName=daoObj.getString(nProcIndex1, "unit_name");
							String roomName=daoObj.getString(nProcIndex1, "room_name");
							String status=daoObj.getString(nProcIndex1, "status");
							String unitDays=daoObj.getString(nProcIndex1, "unittime");
							String unitConsultant = daoObj.getString(nProcIndex1, "unit_inc_name");
							String cardPrintSetting = daoObj.getString(nProcIndex1, "cardSetting");
							
							if(status.equals("2"))
								throw new HisAppointmentNotAvailableException("Appointment Not Available");
							
							_dailyPatientVO.setQueNo(queNo);
							_dailyPatientVO.setRoomCode(roomCode);
							_dailyPatientVO.setDepartmentUnitCode(deptUnitCode);
							_dailyPatientVO.setPatientAllowed(unitCap);
							_dailyPatientVO.setDepartmentUnit(unitName);
							_dailyPatientVO.setRoom(roomName);
							_dailyPatientVO.setUnitWorkingDays(unitDays);
							_dailyPatientVO.setUnitConsultant(unitConsultant);
							_dailyPatientVO.setCardPrintSetting(cardPrintSetting);
							//////generate episode code
							String episodeCode=deptUnitCode+"001";
							_dailyPatientVO.setEpisodeCode(episodeCode);
							///////////
							//for printing formats
							//_dailyPatientVO.setCardPrintSetting("");
							_dailyPatientVO.setFilePrintSetting("");
		
							_dailyPatientVO.setFileNo("");
								
							if (_dailyPatientVO.getQueNo() == null || _dailyPatientVO.getRoomCode() == null
									|| _dailyPatientVO.getDepartmentUnitCode() == null || _dailyPatientVO.getPatientAllowed() == null) throw new HisInvalidTokenNumberException(
									"Procedure Generate Que No:: returns null value");
							_dailyPatientVO.setFileNoView("");
						}
			//}
			
		}
		catch (HisInvalidTokenNumberException e)
		{
			e.printStackTrace();
			throw new HisInvalidTokenNumberException("Procedure Generate Que No:: returns null value" + e);
		}
		catch (HisAppointmentNotAvailableException e)
		{
			e.printStackTrace();
			throw new HisAppointmentNotAvailableException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("Procedure Generate Que No::" + e);
		}
		finally{
			if(daoObj!=null){
				daoObj.free();
			}
			daoObj=null;
		}
		return _dailyPatientVO;
	}

	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(String _patCrNo, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_FOR_POLICE_EXAM_REQT";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("Query ---------> " + query);
		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patCrNo);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
		}
		
		PatientDetailVO[] dailyPatientVOs = null;
		List<PatientDetailVO> lstPatientDetailVO = new ArrayList<PatientDetailVO>();
		ValueObject[] valueObjects = null;
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
					lstPatientDetailVO.add((PatientDetailVO)valueObjects[i]);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
		}
		return lstPatientDetailVO;
	}

	public String getPatientCount(String p_mode, UserDeskMenuMasterVO userDeskVO, UserVO userVO) 
	{
		String count = "";
		ResultSet rs = null;
		String errorMsg="";
		String patientCountObj;
		System.out.println("seat id of USER :"+userVO.getSeatId()+"  user id :"+userVO.getUserId());		
		try
		{
			Procedure strProc=new Procedure(DynamicDeskConfig.PROC_FOR_PATIENT_COUNT);
			strProc.addInParameter(1,Types.VARCHAR,p_mode);// Mode
			strProc.addInParameter(2,Types.VARCHAR,userDeskVO.getDeskType());// Desk type
			strProc.addInParameter(3,Types.VARCHAR,userDeskVO.getDeptUnitCode());
		    strProc.addInParameter(4,Types.VARCHAR,userDeskVO.getWardCode());//Room Code
		    strProc.addInParameter(5,Types.VARCHAR,userVO.getSeatId()); //user seat Id
			strProc.addInParameter(6,Types.VARCHAR,userVO.getHospitalCode());
			strProc.addInParameter(7,Types.VARCHAR,Config.IS_VALID_ACTIVE);
			strProc.setReturnType(Types.VARCHAR);
			return	patientCountObj = (String)strProc.execute(super.getTransactionContext().getConnection());
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
	}
	
	/**
	 * To get All Patient list from All department fo New Doctor Desk.
	 * 
	 * @param _dailyPatientVO Provides daily patient details to be entered.
	 * @param _userVO Provides User details.
	 * @return Patient List as DailyPatientVO[].
	 */
	public PatientDetailVO[] getTodayAllPatientsBySeatID(UserVO _userVO, String unitCode, String roomCode)
	{
		System.out.println("DailyPatientDAO.getTodayAllPatientsBySeatID()");
		PatientDetailVO[] dailyPatientVOs = null;
		Map _populateMapPatientDtl = new HashMap();
		ValueObject[] valueObjects = null;
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_ALL_FOR_TODAY";
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		_populateMapPatientDtl.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		_populateMapPatientDtl.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		_populateMapPatientDtl.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_STAMPED);
		_populateMapPatientDtl.put(sq.next(), RegistrationConfig.EPISODE_ISCONFIRMED_VISIT_CONFIRMED);
		//_populateMapPatientDtl.put(sq.next(), unitCode);
		//_populateMapPatientDtl.put(sq.next(), roomCode);
		_populateMapPatientDtl.put(sq.next(), _userVO.getHospitalCode());
		_populateMapPatientDtl.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMapPatientDtl);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientDetailVO.class, rs);
				dailyPatientVOs = new PatientDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return dailyPatientVOs;
	}

	public Map<String, Object> getAllPatientList_AJAX(String _calMode, PatientDetailVO patientDetailVO, UserVO _userVO, int p_page, int p_limit, String p_sidx,	String p_sord, String p_where, String deskType)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		Map<String, Object> populateMAP = new HashMap<String, Object>();
		
		String errorMsg = null;
		
		int $total_pages = 0;
		int $count=0;
		
		String count="";
		System.out.println("departmentName :"+patientDetailVO.getDepartmentUnitCode());
		System.out.println("visit type :"+patientDetailVO.getEpisodeVisitType());
		System.out.println("room code :"+patientDetailVO.getRoomCode());
		System.out.println("seat id :"+_userVO.getSeatId());
		System.out.println("pat cr no :"+patientDetailVO.getPatCrNo());
		System.out.println("pat adm no :"+patientDetailVO.getPatAdmNo());
		if(patientDetailVO.getPatName()!=null)
			patientDetailVO.setPatName("%"+patientDetailVO.getPatName()+"%");
		System.out.println("pat name :"+patientDetailVO.getPatName());
		
		List<List<String>> alRecord = new ArrayList<List<String>>();
		try
		{
			Procedure strProc = new Procedure(DynamicDeskConfig.PROC_FOR_ALL_PATIENT_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, deskType);
			strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(4, Types.VARCHAR, "");
			strProc.addInParameter(5, Types.VARCHAR, "");
			strProc.addInParameter(6, Types.VARCHAR, "");
			strProc.addInParameter(7, Types.VARCHAR, "");
			strProc.addInParameter(8, Types.VARCHAR, p_where);
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(10, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(11, Types.VARCHAR, patientDetailVO.getDepartmentUnitCode());
			strProc.addInParameter(12, Types.VARCHAR, patientDetailVO.getEpisodeVisitType());
			strProc.addInParameter(13, Types.VARCHAR, patientDetailVO.getRoomCode());
			strProc.addInParameter(14, Types.VARCHAR, patientDetailVO.getWardCode());
			strProc.addInParameter(15, Types.VARCHAR, patientDetailVO.getPatCrNo());  // p_critiera_1  now used as patCrNo,  modified for CR No search,  date: 29.6.2016
			strProc.addInParameter(16, Types.VARCHAR, patientDetailVO.getPatAdmNo());// p_critiera_2 now used as patAdmNo, modified for Adm No Search by Shruti Shail, Date: 5.12.16 
			strProc.addInParameter(17, Types.VARCHAR, patientDetailVO.getPatName());// p_critiera_3 new addition, added for Patient Name Search by Shruti Shail, Date: 5.12.16
			strProc.addInParameter(18, Types.VARCHAR, patientDetailVO.getEpisodeDate());//new addition, added for Visit Date Search by Vasu, Date: 30.Jan.2019
			strProc.addOutParameter(19, Types.VARCHAR);
			strProc.addOutParameter(20, Types.VARCHAR);
			strProc.addOutParameter(21, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(19));
			System.out.println("2:"+strProc.getParameterAt(20));
			System.out.println("3:"+strProc.getParameterAt(21));
			errorMsg = (String) strProc.getParameterAt(19);
			//String count = (String) strProc.getParameterAt(18);
			rs = (ResultSet) strProc.getParameterAt(21);
			
			try
			{
				if(rs.next()) count=rs.getString(1); else count="0"; 
			}
			catch (SQLException e)
			{
				throw new HisDataAccessException("DailyPatientDAO.getTodayAllPatientList_AJAX()" + e);
			}
			
			System.out.println("count:"+count);
			/*
			System.out.println("p_page"+p_page);
			System.out.println("p_limit:"+p_limit);
			System.out.println("p_sidx:"+p_sidx);
			System.out.println("p_sord:"+p_sord);
			*/						
			
			/*$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			*/
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			if($start<0)$start=0;
			
		
			strProc = new Procedure(DynamicDeskConfig.PROC_FOR_ALL_PATIENT_LIST);
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, deskType);
			strProc.addInParameter(3, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(4, Types.VARCHAR, p_sidx);
			strProc.addInParameter(5, Types.VARCHAR, p_sord);
			strProc.addInParameter(6, Types.VARCHAR, $start+"");
			strProc.addInParameter(7, Types.VARCHAR, p_limit+"");
			strProc.addInParameter(8, Types.VARCHAR, p_where);
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(10, Types.VARCHAR, _userVO.getSeatId());
			strProc.addInParameter(11, Types.VARCHAR, patientDetailVO.getDepartmentUnitCode());
			strProc.addInParameter(12, Types.VARCHAR, patientDetailVO.getEpisodeVisitType());
			strProc.addInParameter(13, Types.VARCHAR, patientDetailVO.getRoomCode());
			strProc.addInParameter(14, Types.VARCHAR, patientDetailVO.getWardCode());
			strProc.addInParameter(15, Types.VARCHAR, patientDetailVO.getPatCrNo());//  p_critiera_1  now used as patCrNo,  modified for CR No search,  date: 29.6.2016
			strProc.addInParameter(16, Types.VARCHAR, patientDetailVO.getPatAdmNo());// p_critiera_2 now used as patAdmNo, modified for Adm No Search by Shruti Shail, Date: 5.12.16 
			strProc.addInParameter(17, Types.VARCHAR, patientDetailVO.getPatName());// p_critiera_3 new addition, added for Patient Name Search by Shruti Shail, Date: 5.12.16
			strProc.addInParameter(18, Types.VARCHAR, patientDetailVO.getEpisodeDate());//new addition, added for Visit Date Search by Vasu, Date: 30.Jan.2019
			strProc.addOutParameter(19, Types.VARCHAR);
			strProc.addOutParameter(20, Types.VARCHAR);
			strProc.addOutParameter(21, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(19));
			System.out.println("2:"+strProc.getParameterAt(20));
			System.out.println("3:"+strProc.getParameterAt(21));
			errorMsg = (String) strProc.getParameterAt(19);
			count = (String) strProc.getParameterAt(20);
			rs = (ResultSet) strProc.getParameterAt(21);
			
			System.out.println("getTodayAllPatientList_AJAX end");
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);	
			//alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWiseWithColumn(rs);	
			/*
			System.out.println("$count"+$count);
			System.out.println("$total_pages:"+$total_pages);
			System.out.println("p_page:"+p_page);
			*/			
			
			populateMAP.put("count", $count+"");
			populateMAP.put("total_pages", $total_pages+"");
			populateMAP.put("page", p_page+"");
			populateMAP.put("listObj", alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO.getTodayAllPatientList_AJAX()" + e);
		}
		
		return populateMAP;
	}
	

	public Map<String, Object> AJX_G_PATIENTS_COUNT_NEW(String _calMode, UserVO _userVO, int p_page, int p_limit, String p_sidx,	String p_sord, String p_where)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;		
		Map<String, Object> populateMAP = new HashMap<String, Object>();
		
		String errorMsg = null;
		
		int $total_pages = 0;
		int $count=0;
		
		String count="";
		
		List<List<String>> alRecord = new ArrayList<List<String>>();
		try
		{
			Procedure strProc = new Procedure(DynamicDeskConfig.PROC_FOR_ALL_PATIENT_LIST);
			strProc.addInParameter(1, Types.VARCHAR, _calMode);
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, "");
			strProc.addInParameter(4, Types.VARCHAR, "");
			strProc.addInParameter(5, Types.VARCHAR, "");
			strProc.addInParameter(6, Types.VARCHAR, "");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			//String count = (String) strProc.getParameterAt(12);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			try
			{
				if(rs.next()) count=rs.getString(1); else count="0"; 
			}
			catch (SQLException e)
			{
				throw new HisDataAccessException("DailyPatientDAO.AJX_G_PATIENTS_COUNT_NEW()" + e);
			}
			
			System.out.println("count:"+count);
			/*
			System.out.println("p_page"+p_page);
			System.out.println("p_limit:"+p_limit);
			System.out.println("p_sidx:"+p_sidx);
			System.out.println("p_sord:"+p_sord);
			*/						
			
			/*$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			*/
			$count = Integer.parseInt(count);			
			if($count>0){$total_pages=(int)Math.ceil(($count*1.0)/p_limit);}else{$total_pages=0;}
			if (p_page > $total_pages) p_page=$total_pages; 
			int $start= p_limit*p_page-p_limit; // do not put $limit*($page - 1) 
			if($start<0)$start=0;
			
		
			strProc = new Procedure(DynamicDeskConfig.PROC_FOR_ALL_PATIENT_LIST);
			strProc.addInParameter(1, Types.VARCHAR, "2");
			strProc.addInParameter(2, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, p_sidx);
			strProc.addInParameter(4, Types.VARCHAR, p_sord);
			strProc.addInParameter(5, Types.VARCHAR, $start+"");
			strProc.addInParameter(6, Types.VARCHAR, p_limit+"");
			strProc.addInParameter(7, Types.VARCHAR, p_where);
			strProc.addInParameter(8, Types.VARCHAR, _userVO.getUserId());
			strProc.addInParameter(9, Types.VARCHAR, _userVO.getSeatId());
			strProc.addOutParameter(10, Types.VARCHAR);
			strProc.addOutParameter(11, Types.VARCHAR);
			strProc.addOutParameter(12, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			
			System.out.println("1:"+strProc.getParameterAt(10));
			System.out.println("2:"+strProc.getParameterAt(11));
			System.out.println("3:"+strProc.getParameterAt(12));
			errorMsg = (String) strProc.getParameterAt(10);
			count = (String) strProc.getParameterAt(11);
			rs = (ResultSet) strProc.getParameterAt(12);
			
			System.out.println("getTodayAllPatientList_AJAX end");
			
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		
		try
		{
			alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);	
			//alRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWiseWithColumn(rs);	
			/*
			System.out.println("$count"+$count);
			System.out.println("$total_pages:"+$total_pages);
			System.out.println("p_page:"+p_page);
			*/			
			
			populateMAP.put("count", $count+"");
			populateMAP.put("total_pages", $total_pages+"");
			populateMAP.put("page", p_page+"");
			populateMAP.put("listObj", alRecord);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("DailyPatientDAO.AJX_G_PATIENTS_COUNT_NEW()" + e);
		}
		
		return populateMAP;
	}
	
}
