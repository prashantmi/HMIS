/* Global Remarks Master DAO
 * author: Pawan Kumar B N
 * Created on : 26-Aug-2011
 */
package billing.masters.dao;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.masters.vo.GlobalRemarksMstVO;

public class GlobalRemarksMstDAO
{

	/**
	 * retrieves insert Query and executes it.
	 * 
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertQuery(GlobalRemarksMstVO vo)
	{

		boolean fReturnValue = true;
		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;

		dao = new HisDAO("Billing", "GlobalRemarksMstDAO.insertQuery()");
		strQuery = billing.qryHandler_billing.getQuery(1, "insert.remarksMst.0");
		nQueryIndex = dao.setQuery(strQuery);

		try
		{
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrRemarksType());
			dao.setQryValue(nQueryIndex, 3, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 4, vo.getStrRemarksDesc());
			dao.setQryValue(nQueryIndex, 5, vo.getStrRemarksType());
			dao.setQryValue(nQueryIndex, 6, vo.getStrEffectiveFrom());
			dao.setQryValue(nQueryIndex, 7, vo.getStrSeatId());
			dao.setQryValue(nQueryIndex, 8, vo.getStrRemarksFor());
			dao.setQryValue(nQueryIndex, 9, vo.getStrValid());
			dao.execute(nQueryIndex, 0);

			synchronized (dao)
			{
				dao.fire();
			}

		} catch (Exception e)
		{

			new HisException("Billing", "GlobalRemarksMstDAO.insertQuery()", e.getMessage());
			vo.setStrErr("Record Not Inserted");
			fReturnValue = false;
		} finally
		{
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	/**
	 * retrieves and executes Modify Query
	 * 
	 * @param request
	 * @param strChk
	 * @param vo
	 */
	public static void modifyQuery(String strChk, GlobalRemarksMstVO vo)
	{

		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex;
		String strTemp[] = null;
		String strTemp2[] = null;
		try
		{

			dao = new HisDAO("billing", "GlobalRemarksMstDAO.modifyQuery()");

			strQuery = billing.qryHandler_billing.getQuery(1, "select.remarksMst.1");

			strTemp = strChk.replace('@', '#').split("#");
			strTemp2 = strTemp[1].replace("$", "#").split("#");
			strTemp[1] = strTemp2[0];
			nQryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQryIndex, 1, strTemp[0]);
			dao.setQryValue(nQryIndex, 2, strTemp[1]);

			WebRowSet web = dao.executeQry(nQryIndex);

			while (web.next())
			{

				vo.setStrRemarksDesc(web.getString(1));
				vo.setStrEffectiveFrom(web.getString(2));
				vo.setStrRemarksType(web.getString(3));
				vo.setStrRemarksFor(web.getString(4));
				vo.setStrSeatId(web.getString(5));
				vo.setStrValid(web.getString(6));

			}

		} catch (Exception e)
		{

			new HisException("billing", "GlobalRemarksMstDAO.modifyQuery()", e.getMessage());
		} finally
		{
			dao.free();
			dao = null;
		}
	}

	/**
	 * retrieves and executes update Query
	 * 
	 * @param request
	 * @param strChk
	 * @param vo
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */
	public static boolean updateQuery(String strChk, GlobalRemarksMstVO vo)
	{

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strTemp[] = null;
		String strTemp2[] = null;
		String strQuery = null;
		int nQryIndex;

		try
		{

			dao = new HisDAO("billing ", "GlobalRemarksMstDAO.updateQuery()");

			strQuery = billing.qryHandler_billing.getQuery(1, "update.remarksMst.0");
			nQryIndex = dao.setQuery(strQuery);
			strTemp = strChk.replace('@', '#').split("#");
			strTemp2 = strTemp[1].replace("$", "#").split("#");
			strTemp[1] = strTemp2[0];

			dao.setQryValue(nQryIndex, 1, vo.getStrRemarksDesc());
			// dao.setQryValue(nQryIndex, 2, vo.getStrEffectiveFrom());
			dao.setQryValue(nQryIndex, 2, vo.getStrSeatId());
			dao.setQryValue(nQryIndex, 3, vo.getStrRemarksFor());
			dao.setQryValue(nQryIndex, 4, vo.getStrSeatId());
			dao.setQryValue(nQryIndex, 5, vo.getStrValid());
			dao.setQryValue(nQryIndex, 6, strTemp[0]);
			dao.setQryValue(nQryIndex, 7, strTemp[1]);
			dao.execute(nQryIndex, 0);

			synchronized (dao)
			{
				dao.fire();
			}
			fReturnValue = true;
		}

		catch (Exception e)
		{
			vo.setStrErr("Row Not Updated");
			fReturnValue = false;
			new HisException("billing", "GlobalRemarksMstDAO.updateQuery()", e.getMessage());

		} finally
		{
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

}
