


/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Client Master DAO
 * 
 * Created on: 30-08-2011
 */
package billing.masters.dao;

import java.sql.ResultSet;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.masters.vo.ClientMstVO;

public class ClientMstDAO {
	
	public static boolean insertQuery(ClientMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int nqryIndex;
		boolean fretvalue = true;
		String strquery = new String();
		try {
			dao = new HisDAO("billing", "ClientMstDAO");
			strquery = billing.qryHandler_billing.getQuery(1,"insert.ClientMst.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
		    dao.setQryValue(nqryIndex, 2, vo.getStrClientName());
			dao.setQryValue(nqryIndex, 3, vo.getStrClientTypeId());
			dao.setQryValue(nqryIndex, 4, vo.getStrRegistrationNo());
			dao.setQryValue(nqryIndex, 5, vo.getStrContactPerson());
			dao.setQryValue(nqryIndex, 6, vo.getStrClientAddress());
			dao.setQryValue(nqryIndex, 7, vo.getStrContactNo());
			dao.setQryValue(nqryIndex, 8, vo.getStrEmail());
			dao.setQryValue(nqryIndex, 9, vo.getStrNearestOff());
			dao.setQryValue(nqryIndex, 10, vo.getStrPaymentType());
			dao.setQryValue(nqryIndex, 11, vo.getIsOPD());
			dao.setQryValue(nqryIndex, 12, vo.getIsIPD());
			dao.setQryValue(nqryIndex, 13, vo.getIsEME());
			dao.setQryValue(nqryIndex, 14, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 15, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 16, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 17, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 18, vo.getStrFaxNo());
			dao.setQryValue(nqryIndex, 19, vo.getStrSelCat());
			if(!vo.getStrIpdPayRule().equals(""))
			    dao.setQryValue(nqryIndex, 20, vo.getStrIpdPayRule());
			else
				dao.setQryValue(nqryIndex, 20, "0");
			dao.setQryValue(nqryIndex, 21, vo.getStrClientCode());
			
			dao.execute(nqryIndex, 0);

			synchronized (dao) 
			{
				dao.fire();
				fretvalue = true;
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			fretvalue = false;
			throw new Exception("ClientMstDAO.insertQuery() --> "
					+ e.getMessage());
		} finally 
		{
			dao.free();
			dao = null;
		}
		
		return fretvalue;

	}

	
	public static boolean initialAddQuery(ClientMstVO vo) throws Exception 
	{
		HisDAO dao = new HisDAO("billing", "ClientMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,"unique.ClientMst.0");
		try 
		{
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrClientName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

			wb = dao.executeQry(nqryIndex);

			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			
			if (ncount == 0) 
			{
				freturnValue = true;
			} else 
			{
				freturnValue = false;
			}
		} catch (Exception e) 
		{
			freturnValue = false;
			throw new Exception("ClientMstDAO.initialAddQuery() --> "+ e.getMessage());
		} finally 
		{
			dao.free();
			dao = null;
		}

		return freturnValue;
	}

	
	public static void modifyQuery(String chk1, ClientMstVO vo)	throws Exception 
	{

		HisDAO dao = null;
		String strtemp[] = null;
		String strtemp2[] = null;
		int nqryIndex;
		String strquery = new String();
		strtemp = chk1.replace('@', '#').split("#");
		strtemp2 = strtemp[1].replace('$', '#').split("#");
		strtemp[1] = strtemp2[0];

		try 
		{
			dao = new HisDAO("billing", "ClientMstDAO");
			strquery = billing.qryHandler_billing.getQuery(1, "get.ClientMst.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, strtemp[1]);
			dao.setQryValue(nqryIndex, 2, strtemp[0]);
			
			
			WebRowSet web = dao.executeQry(nqryIndex);
			
			while (web.next()) 
			{
				vo.setStrClientName(web.getString(1));
				vo.setStrClientType(web.getString(2));
				vo.setStrRegistrationNo(web.getString(3));
				vo.setStrContactPerson(web.getString(4));
				vo.setStrClientAddress(web.getString(5));
				vo.setStrContactNo(web.getString(6));
				vo.setStrEmail(web.getString(7));
				vo.setStrNearestOff(web.getString(8));
				vo.setStrPaymentType(web.getString(9));
				vo.setIsOPD(web.getString(10));
				vo.setIsIPD(web.getString(11));
				vo.setIsEME(web.getString(12));
				vo.setStrRemarks(web.getString(13));
				vo.setStrFaxNo(web.getString(14));
				vo.setStrSelCat(web.getString(15));
				vo.setStrIpdPayRule(web.getString(16));
				vo.setStrClientTypeId(web.getString(17));
				vo.setStrClientId(strtemp[0]);
				vo.setStrIsValid(web.getString(18));
				vo.setStrClientCode(web.getString(19));
				
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw new Exception("ClientMstDAO.modifyQuery(chk,vo) --> "	+ e.getMessage());
		} finally 
		{
			dao.free();
			dao = null;
		}
	}

	
	public static boolean initialUpdateQuery(String chk, ClientMstVO vo) throws Exception 
	{

		String strtemp[] = null;
		String strtemp2[] = null;
		String strquery = new String();
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;

		HisDAO dao = new HisDAO("billing", "ClientMstDAO");
		strquery = billing.qryHandler_billing.getQuery(1, "unique.ClientMst.2");

		try {
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrClientName());
			dao.setQryValue(nqryIndex, 2, vo.getStrClientId());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());

			wb = dao.executeQry(nqryIndex);
			
			while (wb.next()) 
			{
				ncount = Integer.parseInt(wb.getString(1));
			}
			
			if (ncount == 0) 
			{
				freturnValue = true;
			} else 
			{
				freturnValue = false;
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			freturnValue = false;
			throw new Exception("ClientMstDAO.initialUpdateQuery(chk,vo) --> "+ e.getMessage());
		} finally 
		{
			dao.free();
			dao = null;
		}
		
		return freturnValue;
	}

	
	public static boolean updateQuery(String chk, ClientMstVO vo) throws Exception 
	{
		boolean fretValue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		String strtemp2[] = null;
		int nqryIndex;
		String strquery = new String();
		
		try 
		{
			dao = new HisDAO("billing", "ClientMstDAO");
			strquery = billing.qryHandler_billing.getQuery(1,"update.ClientMst.1");
			nqryIndex = dao.setQuery(strquery);
						
			dao.setQryValue(nqryIndex, 1, vo.getStrClientName());
			dao.setQryValue(nqryIndex, 2, vo.getStrRegistrationNo());
			dao.setQryValue(nqryIndex, 3, vo.getStrContactPerson());
			dao.setQryValue(nqryIndex, 4, vo.getStrClientAddress());
			dao.setQryValue(nqryIndex, 5, vo.getStrContactNo());
			dao.setQryValue(nqryIndex, 6, vo.getStrEmail());
			dao.setQryValue(nqryIndex, 7, vo.getStrNearestOff());
			dao.setQryValue(nqryIndex, 8, vo.getStrPaymentType());
			dao.setQryValue(nqryIndex, 9, vo.getIsOPD());
			dao.setQryValue(nqryIndex, 10, vo.getIsIPD());
			dao.setQryValue(nqryIndex, 11, vo.getIsEME());
			dao.setQryValue(nqryIndex, 12, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 13, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 14, vo.getStrFaxNo());
			dao.setQryValue(nqryIndex, 15, vo.getStrSelCat());
			if(!vo.getStrIpdPayRule().equals(""))
			    dao.setQryValue(nqryIndex, 16, vo.getStrIpdPayRule());
			else
				dao.setQryValue(nqryIndex, 16, "0");
			dao.setQryValue(nqryIndex, 17, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 18, vo.getStrClientCode());
			dao.setQryValue(nqryIndex, 19, vo.getStrClientId());
			dao.setQryValue(nqryIndex, 20, vo.getStrClientTypeId());
			dao.setQryValue(nqryIndex, 21, vo.getStrHospitalCode());
		
			dao.execute(nqryIndex, 0);
			
			synchronized (dao) 
			{
				dao.fire();
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			fretValue = false;
			throw new Exception("ClientMstDAO.updateQuery(chk,vo) --> "	+ e.getMessage());
		} finally 
		{
			dao.free();
			dao = null;
		}
		
		return fretValue;
	}
	
	//this is to get the client name for the selected client id..
	public static ClientMstVO setClientTypeId(ClientMstVO clientMstVo,HisDAO hisDAO_p) 
	{
		String abc="";
		ResultSet rs = null;
		final String strProcName = "{call pkg_bill_view.proc_client_details(?,?,?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		WebRowSet webRowSet = null;
		
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			hisDAO_p.setProcInValue(nProcedureIndex, "modeval","3",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "client_type_id",clientMstVo.getStrClientTypeId(),2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,3); 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,4);
			
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			clientMstVo.setStrMsgString("clientMstDao.setClientTypeId() --> "	+ e.getMessage());
			clientMstVo.setStrMsgType("1");
		}
		
			try
			{
				webRowSet.next();
				clientMstVo.setStrClientType(webRowSet.getString(1));
				clientMstVo.setStrClientTypeId(clientMstVo.getStrClientTypeId());
				
											
			}
			catch (Exception e)
			{
				clientMstVo.setStrMsgType("1");
				throw new HisException("clientMstDao-->setClientTypeId()" + e);
			}
			finally 
			{
				if (hisDAO_p != null) 
				{
					hisDAO_p.free();
					hisDAO_p = null;
				}
			}
			
		return clientMstVo;
		
	}
	

}
