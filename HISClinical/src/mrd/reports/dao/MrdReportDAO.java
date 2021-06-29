/**
 * 
 */
package mrd.reports.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mrd.MrdConfig;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;

/**
 * @author Pragya Sharma
 * Report Data Access Object Creation Date: 20-Feb-2012
 * 
 */
public class MrdReportDAO
{
	/**
	 * Fetching Service Area Report Data
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voMenu_p
	 * @param voUser_p
	 * @throws Exception
	 * @author Pragya Sahrma on 20-Feb-2012
	 */
	public List<MrdReportDataVO> getServiceAreaCountReportData(HisDAO hisDAO_p, String strMode_p, MrdReportVO voReport_p, UserVO voUser_p)
			throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROC_RPT_SERVICE_AREA_COUNTS);

			// Setting and Registering In and Out Parameters
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", (voReport_p.getFromDate() == null) ? "" : voReport_p.getFromDate(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", (voReport_p.getToDate() == null) ? "" : voReport_p.getToDate(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_para_name", (voReport_p.getStrImmunizationType() == null) ? "" : voReport_p
					.getStrImmunizationType(),5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor

			// Executing Procedure
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required.
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDAO.getServiceAreaCountReportData()::hisDAO_p.executeProcedure"
					+ MrdConfig.PROC_RPT_SERVICE_AREA_COUNTS + ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}
		List<MrdReportDataVO> lst = new ArrayList<MrdReportDataVO>();
		ValueObject[] arrVOs =
		{};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(MrdReportDataVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((MrdReportDataVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDataVO()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Fetching ADT Counts Report Data
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voMenu_p
	 * @param voUser_p
	 * @throws Exception
	 * @author Pragya Sharma on 21-Feb-2012
	 */
	public List<MrdReportDataVO> getAdmittedCountReportData(HisDAO hisDAO_p, String strMode_p, MrdReportVO voReport_p, UserVO voUser_p)
			throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROC_RPT_ADMITTED_COUNTS);

			// Setting and Registering In and Out Parameters
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", (voReport_p.getFromDate() == null) ? "" : voReport_p.getFromDate(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", (voReport_p.getToDate() == null) ? "" : voReport_p.getToDate(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // Cursor

			// Executing Procedure
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required.
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDAO.getAdmittedCountReportData()::hisDAO_p.executeProcedure"
					+ MrdConfig.PROC_RPT_ADMITTED_COUNTS + ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}
		
		List<MrdReportDataVO> lst = new ArrayList<MrdReportDataVO>();
		ValueObject[] arrVOs =
		{};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(MrdReportDataVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((MrdReportDataVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDataVO()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Fetching OPD Registration Counts Report Data
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voMenu_p
	 * @param voUser_p
	 * @throws Exception
	 * @author Pragya Sharma on 22-Feb-2012
	 */
	public List<MrdReportDataVO> getOPDRegCountReportData(HisDAO hisDAO_p, String strMode_p, MrdReportVO voReport_p, UserVO voUser_p)
			throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROC_RPT_OPD_REG_COUNTS);

			// Setting and Registering In and Out Parameters
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", (voReport_p.getFromDate() == null) ? "" : voReport_p.getFromDate(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", (voReport_p.getToDate() == null) ? "" : voReport_p.getToDate(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required.
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDAO.getOPDRegCountReportData()::hisDAO_p.executeProcedure"
					+ MrdConfig.PROC_RPT_OPD_REG_COUNTS + ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}
		List<MrdReportDataVO> lst = new ArrayList<MrdReportDataVO>();
		ValueObject[] arrVOs =
		{};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(MrdReportDataVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((MrdReportDataVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDataVO()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Fetching OPD Registration Counts Report Data
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voMenu_p
	 * @param voUser_p
	 * @throws Exception
	 * @author Pragya Sharma on 23-Feb-2012
	 */
	public List<MrdReportDataVO> getInvestigationCountReportData(HisDAO hisDAO_p, String strMode_p, MrdReportVO voReport_p, UserVO voUser_p)
			throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROC_RPT_INVESTIGATION_COUNTS);

			// Setting and Registering In and Out Parameters
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", (voReport_p.getFromDate() == null) ? "" : voReport_p.getFromDate(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", (voReport_p.getToDate() == null) ? "" : voReport_p.getToDate(), 4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required.
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDAO.getInvestigationCountReportData()::hisDAO_p.executeProcedure"
					+ MrdConfig.PROC_RPT_OPD_REG_COUNTS + ") -> " + e.getMessage());
		}
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}
		List<MrdReportDataVO> lst = new ArrayList<MrdReportDataVO>();
		ValueObject[] arrVOs =
		{};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(MrdReportDataVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((MrdReportDataVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDataVO()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	/**
	 * Fetching OPD Registration Counts Report Data
	 * 
	 * @param hisDAO_p
	 * @param strMode_p
	 * @param voMenu_p
	 * @param voUser_p
	 * @throws Exception
	 * @author Pragya Sharma on 23-Feb-2012
	 */
	public List<MrdReportDataVO> getOTCountReportData(HisDAO hisDAO_p, String strMode_p, MrdReportVO voReport_p, UserVO voUser_p)
			throws Exception
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROC_RPT_OT_COUNTS);

			// Setting and Registering In and Out Parameters
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", strMode_p, 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", voUser_p.getHospitalCode(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_from_date", (voReport_p.getFromDate() == null) ? "" : voReport_p.getFromDate(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_to_date", (voReport_p.getToDate() == null) ? "" : voReport_p.getToDate(), 4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 5); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 6); // Cursor

			// Executing Procedure
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required.
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDAO.getOTCountReportData()::hisDAO_p.executeProcedure"
					+ MrdConfig.PROC_RPT_OPD_REG_COUNTS + ") -> " + e.getMessage());
		}
		
		finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}

		List<MrdReportDataVO> lst = new ArrayList<MrdReportDataVO>();
		ValueObject[] arrVOs =
		{};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(MrdReportDataVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((MrdReportDataVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdReportDataVO()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}
}
