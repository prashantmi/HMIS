/**
 * Developer : Anurudra Goel
 * Version : 1.0
 * Date : 17/July/2009
 *  
*/
package mms.reports.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.reports.vo.IssueToPatientDetailRptVO;

public class IssueToPatientDetailRptDAO {
	
	/**
	 * This function is used to fetch Store Combo Detail
	 * @param _IssueToPatientDetailRptVO
	 */
	public static void setStoreCombo(IssueToPatientDetailRptVO _IssueToPatientDetailRptVO){
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
				dao = new HisDAO("mms", "IssueToPatientDetailRptDAO");
			//	dao.setConnType("2");
				
			/*	(modeval character varying DEFAULT '1'::character varying,
						seatid character varying DEFAULT '0'::character varying, 
						hosp_code character varying DEFAULT '0'::character varying,
						item_category character varying DEFAULT '0'::character varying, 
						OUT err character varying, OUT resultset ahiscl.ahis_type.refcursor) IS*/
				
								strproc_name = "{call pkg_mms_rpt.rptm_hstt_store_mst(?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "modeval","1",1);
				dao.setProcInValue(nprocIndex, "hosp_code", _IssueToPatientDetailRptVO.getStrHospCode(),3);
				dao.setProcInValue(nprocIndex, "seatid",_IssueToPatientDetailRptVO.getStrSeatId(),2);
				dao.setProcInValue(nprocIndex, "item_category","10",4);

				dao.setProcOutValue(nprocIndex, "err",1,5);
				dao.setProcOutValue(nprocIndex, "resultset",2,6); 
				dao.executeProcedureByPosition(nprocIndex);
				strerr = dao.getString(nprocIndex, "err");
				if (strerr == null)
					strerr = "";
				
				wb = dao.getWebRowSet(nprocIndex, "resultset");
				
				if (strerr.equals("")) {
				
					_IssueToPatientDetailRptVO.setStrWS(wb);
             
				} else {
				throw new Exception(strerr);
				}
		} catch (Exception e) {
			_IssueToPatientDetailRptVO.setStrMsgString("IssueToPatientDetailRptDAO.setStoreCombo() --> "
					+ e.getMessage());
			_IssueToPatientDetailRptVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
				wb=null;
			}
		}
	
	}
	/**
	 * This function is used to fetch ItemCategory Combo Detail
	 * @param _IssueToPatientDetailRptVO
	 */
	public static void setItemCategCombo(IssueToPatientDetailRptVO _IssueToPatientDetailRptVO){
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			
			
			
				dao = new HisDAO("mms", "IssueToPatientDetailRptDAO");
			//	dao.setConnType("2");
				strproc_name = "{call pkg_mms_rpt.Rptm_getItemCategoryCombo(?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
			/*	(modeval character varying DEFAULT '1'::character varying, 
						hosp_code character varying DEFAULT '0'::character varying, 
						storeid character varying DEFAULT '0'::character varying, 
						seatid character varying DEFAULT '0'::character varying,
						OUT err character varying,
						OUT resultset ahiscl.ahis_type.refcursor) IS*/
				dao.setProcInValue(nprocIndex, "modeval","1",1);
				dao.setProcInValue(nprocIndex, "hosp_code", _IssueToPatientDetailRptVO.getStrHospCode(),2);
				dao.setProcInValue(nprocIndex, " storeId",_IssueToPatientDetailRptVO.getStrStoreId(),3);
				dao.setProcInValue(nprocIndex, "seatId",_IssueToPatientDetailRptVO.getStrSeatId(),4);
				dao.setProcOutValue(nprocIndex, "err", 1,5);
				dao.setProcOutValue(nprocIndex, "resultset", 2,6); 
				dao.executeProcedureByPosition(nprocIndex);
				strerr = dao.getString(nprocIndex, "err");
				if (strerr == null)
					strerr = "";
				
				wb = dao.getWebRowSet(nprocIndex, "resultset");
				
				if (strerr.equals("")) {
				
					_IssueToPatientDetailRptVO.setItemCategWS(wb);
             
				} else {
				throw new Exception(strerr);
				}
		} catch (Exception e) {
			_IssueToPatientDetailRptVO.setStrMsgString("IssueToPatientDetailRptDAO.setItemCategCombo() --> "
					+ e.getMessage());
			_IssueToPatientDetailRptVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
				wb=null;
			}
		}
	
	}


}
