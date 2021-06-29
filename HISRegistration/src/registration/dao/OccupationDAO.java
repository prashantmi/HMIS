package registration.dao;
/**
 * Created By 	: Aadilsi
 * Date			: Dec 2013
 */

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;


import javax.sql.rowset.WebRowSet;

import vo.registration.OccupationVO;

public class OccupationDAO 
{
	public static String saveOccupationDetails(OccupationVO objVOOccupation,String strMode_p, HisDAO hisDAO_p,UserVO uservo)
	{
		final String strProcName = "{call pkg_gbl_dml.dml_Gblt_Occupation_Mst(?,?,?, ?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		String strReuestNo="";
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(objVOOccupation);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_occupationcode",objVOOccupation.getStrOccupationId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_occupationname",objVOOccupation.getStrOccupationName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),4);
			//hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid","1",5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5); // 1 for
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			System.out.println("Data Inserted Successussfully");
			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
			objVOOccupation.setStrMsgString("RequestdetailDAO.getStoreName() --> "	+ e.getMessage());
			objVOOccupation.setStrMsgType("1");
		}
		return strReuestNo;
	}



	/**
	 * to get data for modify page.
	 * 
	 * @param vo the vo
	 */
	public static OccupationVO modifyRecord(OccupationVO vo,HisDAO hisDAO_p) 
	{
		final String strProcName = "{call pkg_gbl_view.proc_gblt_occupation_mst (?,?,?,?)}";
		final int nProcedureIndex;

		final String strDbErr;
		WebRowSet webRowSet = null;

		try

		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(vo);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "1",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_occupationId",
					vo.getStrOccupationId(),2);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 3); // 1 for
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,4); // 2 for
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

		}
		catch(HisRecordNotFoundException e)
		{	
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("RequestdetailDAO.getStoreName() --> "	+ e.getMessage());
			vo.setStrMsgType("1");
		}


		try
		{
			//List<Entry>listMachineName =(List<Entry>) HelperMethodsDAO.getAlOfEntryObjects(webRowSet);
			webRowSet.beforeFirst();
			webRowSet.next();
			vo.setStrOccupationId(webRowSet.getString(1));

			vo.setStrOccupationName(webRowSet.getString(2));

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RequestdetailDAO:getStoreName():HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return vo;

	}


	public static void updateRequestStatus(OccupationVO vo,HisDAO hisDAO_p,UserVO uservo )
	{
		final String strProcName = "{call pkg_gbl_dml.dml_Gblt_Occupation_Mst(?,?,?, ?,?)}";
		final int nProcedureIndex;
		final String strDbErr;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			HisUtil.replaceNullValueWithEmptyString(vo);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode","2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_occupationcode",vo.getStrOccupationId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gstr_occupationname",vo.getStrOccupationName(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",uservo.getSeatId(),4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 5); // 1 for
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");


			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) 
			{
				throw new Exception("Data Base Error:" + strDbErr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisException(e.getMessage());
		}
	}

	public static boolean chkOccupationDuplicate(OccupationVO objModelOcup,String strMode_p,HisDAO hisDAO_p,UserVO uservo) 
	{
		WebRowSet webRowSet = null;
		String strDbErr = "";
		boolean bExistStatus=false;
		int ncount=0;
		final String strProcName = "{call pkg_reg_view.proc_gblt_occupation_mst(?,?,?,?,?)}";
		final int nProcedureIndex;

		try		
		{
			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(objModelOcup);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", "2",1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_occname",objModelOcup.getStrOccupationName(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",Config.IS_VALID_ACTIVE,3);			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1, 4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,5); 

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
			objModelOcup.setStrMsgString("OccupationDAO.chkOccupationDuplicate() --> " + e.getMessage());
			objModelOcup.setStrMsgType("1");
		} 
		try
		{
			webRowSet.beforeFirst();
			webRowSet.next();
			ncount=Integer.parseInt(webRowSet.getString(1));
			System.out.println("------"+ncount+"-----");	
			if (strMode_p.equalsIgnoreCase("1") &&  ncount == 0) {
				bExistStatus=true;
			} 
			else if(strMode_p.equalsIgnoreCase("2")) {
				if(objModelOcup.getStrOldOccupationName().equalsIgnoreCase(objModelOcup.getStrOccupationName()) && (ncount == 1))
				{
					bExistStatus=true;
				}
				if(!(objModelOcup.getStrOldOccupationName().equalsIgnoreCase(objModelOcup.getStrOccupationName())) && (ncount == 0))
				{
					bExistStatus=true;
				}
			}  
			else 
			{
				bExistStatus=false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			objModelOcup.setStrMsgString("OccupationDAO.chkOccupationDuplicate() --> " + e.getMessage());
			objModelOcup.setStrMsgType("1");

		} 
		finally 
		{

		}
		return bExistStatus;
	}

}

