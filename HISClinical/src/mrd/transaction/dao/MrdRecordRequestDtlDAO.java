package mrd.transaction.dao;

import hisglobal.backutil.HisDAO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import mrd.MrdConfig;

public class MrdRecordRequestDtlDAO extends DataAccessObject implements MrdRecordRequestDtlDAOi
{
	public MrdRecordRequestDtlDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	// Offline
	public void create(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getMrdCode());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRecordType());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRemarks());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqPurposeId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqStatus());
			// populateMAP.put(sq.next(),
			// mrdRecordRequestDtlVO.getRequestId());//added by sandip naik on
			// 26/7/17
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqMode());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getForDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getIssueUpto());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestBy());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getCancelReason());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), userVO.getIpAddress());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestByName());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestByFlag());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	// Online
	public void onlineInsert(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "";
		if (mrdRecordRequestDtlVO.getDESIGNATED_APPROVED_BY().equalsIgnoreCase("Self Approved"))
		{
			queryKey = "INSERT_ONLINE_APPROVED.HPMRT_MRDRECORD_REQ_DTL";
		} else
		{
			queryKey = "INSERT_ONLINE.HPMRT_MRDRECORD_REQ_DTL";
		}

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getMrdCode());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRecordType());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRemarks());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqPurposeId());
			// populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqStatus());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqMode());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getForDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			// populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestBy());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getIpAddress());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getDESIGNATED_APPROVED_BY());
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	// Icd index details insertion
	// added by swati
	// date:27-02-2019

	public void icdIndexDtlInsert(MrdIcdDtlVO mrdIcdVo, UserVO userVO, String mode)
	{
		HisDAO daoObj = null;
		String strProcName = " {call pkg_mrd_dml.proc_Insert_icd_index_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex;
		String strErr = "";
		WebRowSet rs = null;

		try
		{
			// String pmode="1";
			daoObj = new HisDAO("MRD", "EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);


			daoObj.setProcInValue(nProcIndex, "pmode", mode, 1);
			daoObj.setProcInValue(nProcIndex, "patcr", mrdIcdVo.getPatCrNo(), 2);
			daoObj.setProcInValue(nProcIndex, "episodecode", mrdIcdVo.getEpisodeCode(), 3);
			daoObj.setProcInValue(nProcIndex, "episodevisitcode", mrdIcdVo.getEpisodeVisitNo(), 4);
			daoObj.setProcInValue(nProcIndex, "adm_num", mrdIcdVo.getAddmissionNo(), 5);
			daoObj.setProcInValue(nProcIndex, "dept_code", mrdIcdVo.getDepartmentCode(), 6);
			daoObj.setProcInValue(nProcIndex, "deptname", mrdIcdVo.getDepartmentName(), 7);
			daoObj.setProcInValue(nProcIndex, "dep_unit_code", mrdIcdVo.getDepartmentUnitCode(), 8);
			daoObj.setProcInValue(nProcIndex, "dep_unit_name", mrdIcdVo.getDepartmentUnitName(),9);

			daoObj.setProcInValue(nProcIndex, "seatid", userVO.getSeatId(), 10);
			daoObj.setProcInValue(nProcIndex, "wardcode", mrdIcdVo.getWardCode(), 11);
			daoObj.setProcInValue(nProcIndex, "wardname", mrdIcdVo.getWardName(), 12);
			daoObj.setProcInValue(nProcIndex, "roomcode", mrdIcdVo.getIpdRoomCode(), 13);
			daoObj.setProcInValue(nProcIndex, "hospitalcode", userVO.getHospitalCode(), 14);
			daoObj.setProcInValue(nProcIndex, "roomname", mrdIcdVo.getIpdRoomName(),15);

			daoObj.setProcInValue(nProcIndex, "diseasecode", mrdIcdVo.getIcdCodeList(), 16);
			daoObj.setProcInValue(nProcIndex, "disease", mrdIcdVo.getDiagnosisName(), 17);

			daoObj.setProcInValue(nProcIndex, "patname", mrdIcdVo.getPatName(), 18);

		

			daoObj.setProcOutValue(nProcIndex, "err", 1,19);

			daoObj.executeProcedureByPosition(nProcIndex);
			// Getting out parameters 
			strErr = daoObj.getString(nProcIndex, "err");
		

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((strErr != null ? "" : strErr) + e);
		}


	}

	public String generateRequestId(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs;
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GENERATE_REQUEST_ID.HPMRT_MRDRECORD_REQ_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
				throw new HisRecordNotFoundException("");
			rs.first();
			return rs.getString(1);
		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	// Update the record status and approval detail
	public void update(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			// populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqStatus());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getIssueUpto());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getCancelReason());
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	// Update the record status and approval detail
	public void updateRejectDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REJECT_DETAIL.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqStatus());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getCancelReason());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public void updateRequestStatus(MrdRecordIssueDtlVO mrdRecordIssueDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REQUEST_STATUS_REJECTED.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), mrdRecordIssueDtlVO.getReqStatus()); // mrdRecordRequestDtlVO.getReqStatus());

			populateMAP.put(sq.next(), userVO.getUsrName());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), mrdRecordIssueDtlVO.getRejectRemarks());
			populateMAP.put(sq.next(), mrdRecordIssueDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public void updateRequestStatus(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REQUEST_STATUS.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			// if(mrdRecordRequestDtlVO.getReqStatus() != null &&
			// mrdRecordRequestDtlVO.getReqStatus() != "" &&
			// mrdRecordRequestDtlVO.getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED))
			// {
			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getReqStatus());
			// //mrdRecordRequestDtlVO.getReqStatus());
			// }
			// else
			// {
			// populateMAP.put(sq.next(),MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);
			// //mrdRecordRequestDtlVO.getReqStatus());
			// }

			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getForDays());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public void updateRecordRequestStatus(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REQUEST_STATUS.HPMRT_REQ_RECORD_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			/*
			 * if(mrdRecordRequestDtlVO.getReqStatus() != null &&
			 * mrdRecordRequestDtlVO.getReqStatus() != "" &&
			 * mrdRecordRequestDtlVO
			 * .getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED
			 * )) {
			 * populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getReqStatus());
			 * //mrdRecordRequestDtlVO.getReqStatus()); } else {
			 * populateMAP.put(
			 * sq.next(),MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);
			 * //mrdRecordRequestDtlVO.getReqStatus()); }
			 */

			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getForDays());
			populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_RETURN);
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRecordId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	/*
	 * //ADDED BY SWATI SAGAR //DATE:06-03-2019 public List<MrdIcdDtlVO>
	 * getIcdIndexDtl(MrdIcdDtlVO mrdIcdVo,UserVO userVO) { String query = "";
	 * Map populateMAP =new HashMap(); Sequence sq=new Sequence(); String
	 * filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO; String
	 * queryKey="SELECT.BY_STATUS.HPMRT_MRDRECORD_REQ_DTL"; List
	 * <MrdRecordRequestDtlVO> mrdRecordRequestDtlVOList=null; ResultSet
	 * rs=null; try { query =HelperMethodsDAO.getQuery(filename,queryKey); }
	 * catch(Exception e) { throw new HisApplicationExecutionException(
	 * "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
	 * +e); }
	 * 
	 * try { populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //
	 * Added by Pawan Kumar B N on 05-Jul-2012 populateMAP.put(sq.next(),
	 * Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on
	 * 05-Jul-2012 populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	 * // Added by Pawan Kumar B N on 05-Jul-2012 populateMAP.put(sq.next(),
	 * userVO.getHospitalCode()); //populateMAP.put(sq.next(),
	 * Config.IS_VALID_ACTIVE);commented by sandip naik on 17/7/17
	 * populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqStatus());
	 * //populateMAP.put(sq.next(), userVO.getUserEmpID());//edited by sandip
	 * naik on 20/7/17 // Commented by Dheeraj on 05-Nov-2018 } catch(Exception
	 * e) { throw new
	 * HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::"
	 * +e); } try {
	 * rs=HelperMethodsDAO.executeQuery(super.getTransactionContext(
	 * ).getConnection(),query,populateMAP); if(!rs.next()){ throw new
	 * HisRecordNotFoundException("No Record Found"); } else { rs.beforeFirst();
	 * mrdRecordRequestDtlVOList=new ArrayList<MrdRecordRequestDtlVO>(); while
	 * (rs.next()) { mrdRecordRequestDtlVO=new MrdRecordRequestDtlVO();
	 * HelperMethods.populateVOfrmRS(mrdRecordRequestDtlVO, rs);
	 * mrdRecordRequestDtlVOList.add(mrdRecordRequestDtlVO); } }
	 * 
	 * } catch(Exception e) {
	 * if(e.getClass()==HisRecordNotFoundException.class){ throw new
	 * HisRecordNotFoundException(e.getMessage()); } else{ e.printStackTrace();
	 * throw new HisDataAccessException("HisDataAccessException"); } } return
	 * MrdIcdDtlVO; }
	 */

	// select All record request by request status
	public List<MrdRecordRequestDtlVO> selectAllRecordByRequestStatus(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.BY_STATUS.HPMRT_MRDRECORD_REQ_DTL";
		List<MrdRecordRequestDtlVO> mrdRecordRequestDtlVOList = null;
		ResultSet rs = null;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);commented by
			// sandip naik on 17/7/17
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getReqStatus());
			// populateMAP.put(sq.next(), userVO.getUserEmpID());//edited by
			// sandip naik on 20/7/17 // Commented by Dheeraj on 05-Nov-2018
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			} else
			{
				rs.beforeFirst();
				mrdRecordRequestDtlVOList = new ArrayList<MrdRecordRequestDtlVO>();
				while (rs.next())
				{
					mrdRecordRequestDtlVO = new MrdRecordRequestDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordRequestDtlVO, rs);
					mrdRecordRequestDtlVOList.add(mrdRecordRequestDtlVO);
				}
			}

		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException");
			}
		}
		return mrdRecordRequestDtlVOList;
	}

	// select All request which has been approved
	public List<MrdRecordRequestDtlVO> selectRecordRequestForIssue(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.RECORD_TO_ISSUE.HPMRT_MRDRECORD_REQ_DTL";
		List<MrdRecordRequestDtlVO> mrdRecordRequestDtlVOList = null;
		ResultSet rs = null;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);
			// populateMAP.put(sq.next(),
			// MrdConfig.REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT);

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			} else
			{
				rs.beforeFirst();
				mrdRecordRequestDtlVOList = new ArrayList<MrdRecordRequestDtlVO>();
				while (rs.next())
				{
					mrdRecordRequestDtlVO = new MrdRecordRequestDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordRequestDtlVO, rs);
					mrdRecordRequestDtlVOList.add(mrdRecordRequestDtlVO);
				}
			}

		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException");
			}
		}
		return mrdRecordRequestDtlVOList;
	}

	// select All request which has been approved
	public List<MrdRecordRequestDtlVO> selectRecordForReturn(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.RECORD_TO_RETURN.HPMRT_MRDRECORD_REQ_DTL";
		List<MrdRecordRequestDtlVO> mrdRecordRequestDtlVOList = null;
		ResultSet rs = null;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			} else
			{
				rs.beforeFirst();
				mrdRecordRequestDtlVOList = new ArrayList<MrdRecordRequestDtlVO>();
				while (rs.next())
				{
					mrdRecordRequestDtlVO = new MrdRecordRequestDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordRequestDtlVO, rs);
					mrdRecordRequestDtlVOList.add(mrdRecordRequestDtlVO);
				}
			}

		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException");
			}
		}
		return mrdRecordRequestDtlVOList;
	}

	public List<MrdRecordRequestDtlVO> getRequestListForReturn(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ISSUED_MRD_RECORD_REQUEST.HPMRT_MRDRECORD_REQ_DTL";
		ResultSet rs;
		List<MrdRecordRequestDtlVO> mrdRecordIssueDtlVOList = null;

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			// populateMAP.put(sq.next(),
			// MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);
			populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_ISSUED);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			} else
			{
				rs.beforeFirst();
				mrdRecordIssueDtlVOList = new ArrayList<MrdRecordRequestDtlVO>();
				while (rs.next())
				{
					MrdRecordRequestDtlVO mrdRecordIssueDtlVO = new MrdRecordRequestDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordIssueDtlVO, rs);
					mrdRecordIssueDtlVOList.add(mrdRecordIssueDtlVO);
				}
			}
		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException While ADDING");
			}
		}

		return mrdRecordIssueDtlVOList;
	}

	public List<MrdRecordRequestDtlVO> getPendingRequestListing(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_PENDING_MRD_RECORD_REQUEST.HPMRT_MRDRECORD_REQ_DTL";
		ResultSet rs;
		List<MrdRecordRequestDtlVO> mrdRecordIssueDtlVOList = null;

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added
																			// by
																			// Pawan
																			// Kumar
																			// B
																			// N
																			// on
																			// 05-Jul-2012
			// populateMAP.put(sq.next(),
			// MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordIssueDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			} else
			{
				rs.beforeFirst();
				mrdRecordIssueDtlVOList = new ArrayList<MrdRecordRequestDtlVO>();
				while (rs.next())
				{
					MrdRecordRequestDtlVO mrdRecordIssueDtlVO = new MrdRecordRequestDtlVO();
					HelperMethods.populateVOfrmRS(mrdRecordIssueDtlVO, rs);
					mrdRecordIssueDtlVOList.add(mrdRecordIssueDtlVO);
				}
			}
		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HisDataAccessException While ADDING");
			}
		}

		return mrdRecordIssueDtlVOList;
	}

	// Updating the Rejected Request///set GNUM_ISVALID=Config.IS_VALID_DELETED
	public void deleteRejectedRecordDetail(String requestId, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REJECT_REQUEST_INACTIVE.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), requestId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public MrdRecordRequestDtlVO getLoginUserRequestByDetails(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		ResultSet rs;
		MrdRecordRequestDtlVO mrdRecordVO = new MrdRecordRequestDtlVO();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "REQUEST_BY_LOGIN_USER_DETAILS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			populateMAP.put(sq.next(), userVO.getUserEmpID());
			// populateMAP.put(sq.next(),userVO.getHospitalCode());
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
			}
			// throw new HisRecordNotFoundException("");
			else
			{
				rs.beforeFirst();
				rs.next();
				mrdRecordVO.setLoginRequestByDept(rs.getString(1));
				if (rs.getString(2) != null)
				{
					mrdRecordVO.setLoginRequestByDeptHod(rs.getString(2).toUpperCase());
				}

			}

		} catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return mrdRecordVO;
	}

	public void updateForDays(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.FOR_DAYS.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getReqStatus());
			// //mrdRecordRequestDtlVO.getReqStatus());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getForDays());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	@Override
	public void setStatusIssueAgain(MrdRecordIssueDtlVO mrdRecordIssueDtlVO, UserVO userVO)
	{
		// TODO Auto-generated method stub

	}

	public void updateExtensionDetails(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.ISSUE.HPMRT_MRDRECORD_ISS_EXTEND_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getReqStatus());
			// //mrdRecordRequestDtlVO.getReqStatus());
			populateMAP.put(sq.next(), userVO.getUsrName());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRejectRemarks());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public void updateExtension_SLNo(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.EXTENSION_SL_NO.HPMRT_MRDRECORD_REQ_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getReqStatus());
			// //mrdRecordRequestDtlVO.getReqStatus());

			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public void updateExtensionDetailsRejected(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REJECTED.HPMRT_MRDRECORD_ISS_EXTEND_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getReqStatus());
			// //mrdRecordRequestDtlVO.getReqStatus());
			populateMAP.put(sq.next(), userVO.getUsrName());
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRejectRemarks());
			populateMAP.put(sq.next(), mrdRecordRequestDtlVO.getRequestId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}

	public void updateRequestStatusForReturn(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, MrdRecordReturnDtlVO mrdRecordReturnDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REQUEST_STATUS.HPMRT_REQ_RECORD_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			if (mrdRecordRequestDtlVO.getReqStatus() != null && mrdRecordRequestDtlVO.getReqStatus() != "" && mrdRecordRequestDtlVO.getReqStatus().equals(MrdConfig.MRD_RECORD_REQUEST_STATUS_CLOSED))
			{
				populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_RETURN); // mrdRecordRequestDtlVO.getReqStatus());
			} else
			{
				populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_ISSUED); // mrdRecordRequestDtlVO.getReqStatus());
			}

			// populateMAP.put(sq.next(),mrdRecordRequestDtlVO.getForDays());
			populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getRequestId());
			populateMAP.put(sq.next(), mrdRecordReturnDtlVO.getMrdRecordId());
			populateMAP.put(sq.next(), "1");
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			// populateMAP.put(sq.next(), userVO.getUsrName());

		} catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdRecordRequestDtlDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}
}
