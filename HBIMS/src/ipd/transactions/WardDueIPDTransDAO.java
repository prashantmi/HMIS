/**
 * 
 */
package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

/**
 * @author pankaj kumar
 * Last Modified By:Amit Kumar Ateria (06-Aug-2010)
 * Changes:Procedure Parameters Added 
 *
 */
public class WardDueIPDTransDAO {
	public static void getPatientDueList(WardDueIPDTransVO _WardDueIPDTransVO) {
		String strProcName = "{call Pkg_Ipd_View.Proc_patBelonging_Dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO hisDAO = null;
		WebRowSet wsResult = null;
		try {
			hisDAO = new HisDAO("WardDueIPDTransDAO", "getPatientDueList()");
			nProcIndex = hisDAO.setProcedure(strProcName);
			hisDAO.setProcInValue(nProcIndex, "mode_val", "2");
			hisDAO.setProcInValue(nProcIndex, "admNo", _WardDueIPDTransVO.getStrAdmNo());
			hisDAO.setProcInValue(nProcIndex, "dept_unit", _WardDueIPDTransVO.getStrDeptUnit());
			hisDAO.setProcInValue(nProcIndex, "item_type", _WardDueIPDTransVO.getStrItemType());
			hisDAO.setProcInValue(nProcIndex, "Ward", _WardDueIPDTransVO.getStrWard());
			hisDAO.setProcInValue(nProcIndex, "hosp_code", _WardDueIPDTransVO.getStrHospCode());
			hisDAO.setProcOutValue(nProcIndex, "err", 1);
			hisDAO.setProcOutValue(nProcIndex, "resultset", 2);
			hisDAO.executeProcedure(nProcIndex);
			strErr = hisDAO.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";
			wsResult = hisDAO.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
				_WardDueIPDTransVO.setWBPatientDueList(wsResult);
			else
				throw new Exception(strErr);
			
		} catch (Exception _Err) {
			_WardDueIPDTransVO.setStrMsg("WardDueIPDTransDAO.getPatientDueList() --> "
							+ _Err.getMessage());
			_WardDueIPDTransVO.setStrMsgType("1");
		} finally {
			if (hisDAO != null) {
				hisDAO.free();
				hisDAO = null;
			}
		}
	}
	
	public static void getPatienteList(WardDueIPDTransVO _WardDueIPDTransVO) {
		String strProcName = "{call Pkg_Ipd_View.Proc_patBelonging_Dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO hisDAO = null;
		WebRowSet wsResult = null;
		try {
			hisDAO = new HisDAO("WardDueIPDTransDAO", "getPatientDueList()");
			nProcIndex = hisDAO.setProcedure(strProcName);
			hisDAO.setProcInValue(nProcIndex, "mode_val", "3");
			hisDAO.setProcInValue(nProcIndex, "dept_unit", _WardDueIPDTransVO.getStrDeptUnit());
			hisDAO.setProcInValue(nProcIndex, "Ward", _WardDueIPDTransVO.getStrWard());
			hisDAO.setProcInValue(nProcIndex, "hosp_code", _WardDueIPDTransVO.getStrHospCode());
			hisDAO.setProcInValue(nProcIndex, "admno", "");
			hisDAO.setProcInValue(nProcIndex, "item_type", "");
			hisDAO.setProcOutValue(nProcIndex, "err", 1);
			hisDAO.setProcOutValue(nProcIndex, "resultset", 2);
			hisDAO.executeProcedure(nProcIndex);
			strErr = hisDAO.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";
			wsResult = hisDAO.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
				_WardDueIPDTransVO.setWBPatientList(wsResult);
			else
				throw new Exception(strErr);
			
		} catch (Exception _Err) {
			_WardDueIPDTransVO.setStrMsg("WardDueIPDTransDAO.getPatienteList() --> "
							+ _Err.getMessage());
			_WardDueIPDTransVO.setStrMsgType("1");
		} finally {
			if (hisDAO != null) {
				hisDAO.free();
				hisDAO = null;
			}
		}
	}
	
	public static void save(WardDueIPDTransVO _WardDueIPDTransVO) {
		String strProcName = null;
		int nProcIndex = 0;
		HisDAO hisDAO = null;
		try {
			hisDAO = new HisDAO("WardDueIPDTransDAO", "save()");
			for(int nTmpI = 0; nTmpI<_WardDueIPDTransVO.getStrItemTypes().length; nTmpI++)
			{
				if(_WardDueIPDTransVO.getStrItemTypes()[nTmpI].equals("1"))
				{
					strProcName = "{call Pkg_Ipd_Dml.Proc_Hipt_Pat_Belonging_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					nProcIndex = hisDAO.setProcedure(strProcName);
					hisDAO.setProcInValue(nProcIndex, "modval", "3");
					hisDAO.setProcInValue(nProcIndex, "hgnum_deptunitcode", _WardDueIPDTransVO.getStrDeptUnit());
					hisDAO.setProcInValue(nProcIndex, "hipnum_ward_code", _WardDueIPDTransVO.getStrWard());
					hisDAO.setProcInValue(nProcIndex, "gnum_seatid", _WardDueIPDTransVO.getStrSeatId());
					hisDAO.setProcInValue(nProcIndex, "hipdt_return_datetime", _WardDueIPDTransVO.getStrItemReturnDate()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "hipnum_admno", _WardDueIPDTransVO.getStrAdmNo());
					hisDAO.setProcInValue(nProcIndex, "hipnum_belong_slno", _WardDueIPDTransVO.getStrSlno()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "gnum_hospital_code", _WardDueIPDTransVO.getStrHospCode());
					hisDAO.setProcInValue(nProcIndex, "item_type", _WardDueIPDTransVO.getStrItemTypes()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "status", _WardDueIPDTransVO.getStrStatus()[nTmpI]);
					hisDAO.setProcOutValue(nProcIndex, "err", 1);
					hisDAO.setProcOutValue(nProcIndex, "RESULT", 1);
					hisDAO.setProcOutValue(nProcIndex, "dml_count", 1);
					
					hisDAO.setProcInValue(nProcIndex, "hipstr_belong_desc","");
					hisDAO.setProcInValue(nProcIndex, "hipstr_belong_qty","");
					hisDAO.setProcInValue(nProcIndex, "hipstr_benlog_remark","");
					hisDAO.setProcInValue(nProcIndex, "to_deptunitcode","");
					hisDAO.setProcInValue(nProcIndex, "to_ward_code","");
					HisUtil util=new HisUtil("ADT","save");					
					hisDAO.setProcInValue(nProcIndex, "gdt_entry_date",util.getASDate("dd/MM/yyyy"));
					hisDAO.setProcInValue(nProcIndex, "gnum_isvalid","1");
					hisDAO.setProcInValue(nProcIndex, "hrgnum_puk","");
					hisDAO.setProcInValue(nProcIndex, "return_rmks","");
					hisDAO.setProcInValue(nProcIndex, "gnum_relation_code","0");
					hisDAO.setProcInValue(nProcIndex, "item_id","");
					hisDAO.setProcInValue(nProcIndex, "hipstr_return_to","");
					
				}
				else if(_WardDueIPDTransVO.getStrItemTypes()[nTmpI].equals("2"))
				{
					strProcName = "{call Pkg_Ipd_Dml.Proc_Hipt_Pat_Belonging_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
					nProcIndex = hisDAO.setProcedure(strProcName);
					hisDAO.setProcInValue(nProcIndex, "modval", "3");
					hisDAO.setProcInValue(nProcIndex, "hgnum_deptunitcode", _WardDueIPDTransVO.getStrDeptUnit());
					hisDAO.setProcInValue(nProcIndex, "hipnum_ward_code", _WardDueIPDTransVO.getStrWard());
					hisDAO.setProcInValue(nProcIndex, "gnum_seatid", _WardDueIPDTransVO.getStrSeatId());
					hisDAO.setProcInValue(nProcIndex, "hipdt_return_datetime", _WardDueIPDTransVO.getStrItemReturnDate()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "hipstr_return_to", _WardDueIPDTransVO.getStrItemReturnTo()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "hipnum_admno", _WardDueIPDTransVO.getStrAdmNo());
					hisDAO.setProcInValue(nProcIndex, "hipnum_belong_slno", _WardDueIPDTransVO.getStrSlno()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "gnum_hospital_code", _WardDueIPDTransVO.getStrHospCode());
					hisDAO.setProcInValue(nProcIndex, "gnum_relation_code", _WardDueIPDTransVO.getStrRelation()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "item_type", _WardDueIPDTransVO.getStrItemTypes()[nTmpI]);
					hisDAO.setProcInValue(nProcIndex, "status", _WardDueIPDTransVO.getStrStatus()[nTmpI]);
					hisDAO.setProcOutValue(nProcIndex, "err", 1);
					hisDAO.setProcOutValue(nProcIndex, "RESULT", 1);
					hisDAO.setProcOutValue(nProcIndex, "dml_count", 1);
					
					hisDAO.setProcInValue(nProcIndex, "hipstr_belong_desc","");
					hisDAO.setProcInValue(nProcIndex, "hipstr_belong_qty","");
					hisDAO.setProcInValue(nProcIndex, "hipstr_benlog_remark","");
					hisDAO.setProcInValue(nProcIndex, "to_deptunitcode","");
					hisDAO.setProcInValue(nProcIndex, "to_ward_code","");
					HisUtil util=new HisUtil("ADT","save");					
					hisDAO.setProcInValue(nProcIndex, "gdt_entry_date",util.getASDate("dd/MM/yyyy"));
					hisDAO.setProcInValue(nProcIndex, "gnum_isvalid","1");
					hisDAO.setProcInValue(nProcIndex, "hrgnum_puk","");
					hisDAO.setProcInValue(nProcIndex, "return_rmks","");
					hisDAO.setProcInValue(nProcIndex, "item_id","");
				}
				hisDAO.execute(nProcIndex, 1);	
			}
			synchronized(hisDAO){
				hisDAO.fire();
			}
		} catch (Exception _Err) {
			_Err.printStackTrace();
			_WardDueIPDTransVO.setStrMsg("WardDueIPDTransDAO.save() --> "
							+ _Err.getMessage());
			_WardDueIPDTransVO.setStrMsgType("1");
		} finally {
			if (hisDAO != null) {
				hisDAO.free();
				hisDAO = null;
			}
		}
	}
}