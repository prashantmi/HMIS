package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import hisglobal.persistence.TransactionContext;
import opd.OpdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConsultationDtlDAO extends DataAccessObject implements CosultationDtlDAOi
{
	Logger log;

	public ConsultationDtlDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	public ConsultationDtlVO[] getAllDetailsBySeatId(UserVO _userVO, String ackStatus1, String ackStatus2)
	{
		ConsultationDtlVO[] consultationVO = null;

		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.CONSULTATION.HOPT_CONSULTATION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getSeatId());
		//populateMAP.put(sq.next(), _userVO.getUserEmpID());
		populateMAP.put(sq.next(), ackStatus1);
		populateMAP.put(sq.next(), ackStatus2);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Sorry no mails found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(ConsultationDtlVO.class, rs);
			consultationVO = new ConsultationDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				consultationVO[i] = (ConsultationDtlVO) vo[i];
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

		return consultationVO;
	}

	public ConsultationDtlVO create(ConsultationDtlVO consultaionDtlVO, UserVO _userVO)
	{
		//System.out.println("inside create of EpisodeRefDtlVO");
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HOPT_CONSULTATION_DTL";

		// call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMap(consultaionDtlVO, populateMAP, _userVO);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(":populateMap(,populateMAP)::" + e);
		}

		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("INSRET FAILED::HelperMethodsDAO.excecuteUpdate" + e);
		}
		return consultaionDtlVO;

	}

	public void populateMap(ConsultationDtlVO consultaionDtlVO, Map _populateMap, UserVO _userVO) throws SQLException
	{
		Sequence sequence = new Sequence();
		_populateMap.put(sequence.next(), consultaionDtlVO.getPatCrNo());
		_populateMap.put(sequence.next(), consultaionDtlVO.getEpisodeCode());
		_populateMap.put(sequence.next(), consultaionDtlVO.getEpisodeVisitNo());
		_populateMap.put(sequence.next(), consultaionDtlVO.getMailRequestId());
		_populateMap.put(sequence.next(), consultaionDtlVO.getSubject());
		_populateMap.put(sequence.next(), consultaionDtlVO.getMailParentRequestId());
		_populateMap.put(sequence.next(), consultaionDtlVO.getContent());
		_populateMap.put(sequence.next(), consultaionDtlVO.getMailType());
		_populateMap.put(sequence.next(), consultaionDtlVO.getFromDoctorCode());
		_populateMap.put(sequence.next(), consultaionDtlVO.getFromDoctorSeatId());
		// _populateMap.put(sequence.next(),consultaionDtlVO.getSentDate());
		_populateMap.put(sequence.next(), consultaionDtlVO.getReceiveDate());
		_populateMap.put(sequence.next(), consultaionDtlVO.getAckStatus());
		_populateMap.put(sequence.next(), _userVO.getHospitalCode());
		if((consultaionDtlVO.getToDoctorSeatId()!=null)&&(!(consultaionDtlVO.getToDoctorSeatId().equals("-1"))))
		{
		_populateMap.put(sequence.next(), consultaionDtlVO.getUserType());
		_populateMap.put(sequence.next(), consultaionDtlVO.getToDoctName());
		_populateMap.put(sequence.next(), _userVO.getUsrName());
		_populateMap.put(sequence.next(), consultaionDtlVO.getToDoctorSeatId());
		_populateMap.put(sequence.next(), consultaionDtlVO.getToDoctorCode());
		}
		else
		{
			_populateMap.put(sequence.next(), consultaionDtlVO.getUserType());
			_populateMap.put(sequence.next(), consultaionDtlVO.getDepartmentName());
			_populateMap.put(sequence.next(), _userVO.getUsrName());
			_populateMap.put(sequence.next(), consultaionDtlVO.getToDoctorSeatId());
			_populateMap.put(sequence.next(), consultaionDtlVO.getDepartmentCode());

		
		}




	}

	public String getMaxRequestId(UserVO _userVO)
	{
		String maxRequestId = "";
		Sequence seq = new Sequence();
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.MAX_REQUEST_ID.HOPT_CONSULTATION_DTL";
		Connection conn = super.getTransactionContext().getConnection();
		Map populate = new HashMap();
		// _populateMapPatientDtl.put(sq.next(),unitCode);
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
			populate.put(seq.next(), _userVO.getHospitalCode());
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populate);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				maxRequestId = rs.getString(1);
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
		return maxRequestId;
	}

	public int updateConsultationAckStatus(String mailId, String ackStatus, UserVO _userVO)
	{
		//System.out.println("inside updateRefEpisodeDtlAtREG()in Episode refer dao");
		int i = 0;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.HOPT_CONSULTATION_DTL.ACK_STATUS";
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sequence = new Sequence();

		_populateMap.put(sequence.next(), ackStatus);
		_populateMap.put(sequence.next(), mailId);

		try
		{
			i = HelperMethodsDAO.excecuteUpdate(conn, query, _populateMap);

			// if(i!=1){
			if (i < 1)
			{ // -----changes made on 11/oct/2006
				throw new HisUpdateUnsuccesfullException();
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisUpdateUnsuccesfullException.class)
			{
				throw new HisUpdateUnsuccesfullException();
			}
			else throw new HisDataAccessException("EpisodeReferDtlDAO::while updating data " + e);
		}
		return i;
	}

	public String getNoOfNewMailsBySeatId(String seatId, String ackStatus)
	{
		String count = "0";
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.COUNT_NEW_MAIL.HOPT_CONSULTATION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), seatId);
		populateMAP.put(sq.next(), ackStatus);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				count = "0";
			}
			else
			{
				rs.beforeFirst();
				rs.next();
				count = rs.getString(1);
				System.out.println("");
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

		return count;
	}

	public ConsultationDtlVO getMailDetailsByMailId(UserVO _userVO, String _mailRequestId)
	{
		ConsultationDtlVO consultationVO = null;

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_HOPT_CONSULTATION_DTL_BY_MAIL_ID";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _mailRequestId);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Mail not exist");
			}
			rs.beforeFirst();
			consultationVO = new ConsultationDtlVO();
			HelperMethods.populateVOfrmRS(consultationVO, rs);

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return consultationVO;
	}

}
