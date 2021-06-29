package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import ipd.IpdConfigUtil;

import javax.sql.rowset.WebRowSet;

public class PackAdmTransDAO {
	
	public static void admitdetailIPD(PackAdmTransVO VO) 
	{
		String strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		System.out.println("admitdetailIPD "+VO.getStrDeptCode()+" "+VO.getStrDeptUnitCode()+" "+VO.getStrWardCode()+" "+VO.getStrRoom()+" "+VO.getStrHospCode()+" "+VO.getStrCrNo());

		try 
		{
			dao = new HisDAO("ipd","transactions.PackAdmTransDAO.admitdetailIPD()");			

			  
				strproc_name = "{call  PKG_IPD_VIEW.Proc_tobe_accepted_Dtl(?,?,?,?,?,?,?,?,?)}";
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "deptcode", VO.getStrDeptCode(),1);
				dao.setProcInValue(nprocIndex, "mode_val", "5",2);
				dao.setProcInValue(nprocIndex, "deptunitcode", VO.getStrDeptUnitCode(),3);
				dao.setProcInValue(nprocIndex, "wardcode", VO.getStrWardCode(),4);
				dao.setProcInValue(nprocIndex, "roomcode", "0",5);
				dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospCode(),6);
				dao.setProcInValue(nprocIndex, "adm_no", VO.getStrCrNo(),7);
		     	dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
		     	dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
		     	dao.executeProcedureByPosition(nprocIndex);
		     	strerr = dao.getString(nprocIndex, "err");

				if (strerr == null)
					strerr = "";
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				if (strerr.equals("")) 
				{
					VO.setStrAdmitDetailWs(ws);
				} 
				else
				{
					throw new Exception(strerr);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			VO.setStrMsgString("PackAdmTransDAO.admitdetailIPD() --> "	+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
	}
	public static void setEpisodeDtl(PackAdmTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String ageSex = "";
		String ageUnit = "";
		String sex = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		
		try
		{
			daoObj = new HisDAO("ADT","PackAdmTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "3",1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "dept_code", "",3);
			daoObj.setProcInValue(nProcIndex, "unit_code", "",4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			if(ws.size()==0)
			{
				vo.setStrCrNoValid("1");
			}
			else
			{
				vo.setStrCrNoValid("0");
			}
			if (strErr.equals("")) 
			{					
					if (ws.next())
					{
						vo.setStrEpisodeCode(ws.getString(1));
						vo.setStrVisitNo(ws.getString(2));
						//vo.setStrMlcNo(ws.getString(3));
						vo.setStrDeptCode(ws.getString(3));
						vo.setStrDeptUnitCode(ws.getString(4));
						vo.setStrTreatmentCategoryCode(ws.getString(5));
						ageSex=ws.getString(6);
						
						ageUnit=ageSex.replace("/","#").split("#")[0].replace(" ","#").split("#")[1];
						sex=ageSex.replace("/","#").split("#")[1];
						vo.setStrAge(ageSex.replace("/","#").split("#")[0].replace(" ","#").split("#")[0]);
						
						if(ageUnit.toLowerCase().equals("yr") || ageUnit.toLowerCase().equals("y"))
							vo.setStrAgeUnit("3");
						else if(ageUnit.toLowerCase().equals("m") || ageUnit.toLowerCase().equals("mth"))
							vo.setStrAgeUnit("2");
						else if(ageUnit.toLowerCase().equals("w") || ageUnit.toLowerCase().equals("wk"))
							vo.setStrAgeUnit("4");
						else if(ageUnit.toLowerCase().equals("d"))
							vo.setStrAgeUnit("1");
						else if(ageUnit.toLowerCase().equals("hr"))
							vo.setStrAgeUnit("5");
						else 
							vo.setStrAgeUnit("3");
						
						if(sex.toLowerCase().equals("m") || sex.toLowerCase().equals("male"))
							vo.setStrSexCode("1");
						else if(sex.toLowerCase().equals("f") || sex.toLowerCase().equals("female"))
							vo.setStrSexCode("2");
						else
							vo.setStrSexCode("3");						
												
						vo.setStrPatIsUnknown(ws.getString(7));
						vo.setStrPatCatCode(ws.getString(8));
						vo.setStrIsUrban(ws.getString(9));
						vo.setStrPatStatusCode(ws.getString(10));
						vo.setStrPrimaryCategoryCode(ws.getString(11));
						vo.setStrPatCategoryName(ws.getString(12));
						vo.setStrHospDtl(ws.getString(13));
						
						
					}

			} else {
				throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PackAdmTransDAO.setEpisodeDtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally
		{
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void checkStatusWhetherAdvanceAmountGiven(PackAdmTransVO vo)
	{
		String strProcName = "{? = call BILL_INTERFACE.FUNC_ONLINE_ADVANCE_CHECK(?::character varying,?::character varying,?::character varying)}";
		int nProcIndex = 0;
		String val="0";
		
		HisDAO daoObj = null;
		try 
		{
			daoObj = new HisDAO("ADT","PackAdmTransDAO");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "1");
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrCrNo());
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			val=daoObj.getFuncString(nProcIndex);
			vo.setStrIsAdvanceAmountAtAdmissionTaken(val);	
		} 
		catch (Exception e) 
		{			
			vo.setStrMsgString("PackAdmTransDAO.checkStatusWhetherAdvanceAmountGiven() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void setPatientStatus(PackAdmTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		
		try 
		{
				daoObj = new HisDAO("ADT","PackAdmTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) 
				{
					if (ws.next()) 
					{						
						vo.setStrPatCatCode(ws.getString(1));
						vo.setStrIsUrban(ws.getString(2));
						vo.setStrPatStatusCode(ws.getString(3));
						vo.setStrPrimaryCategoryCode(ws.getString(4));
						vo.setStrIsDead(ws.getString(6));
					}
				} 
				else 
				{
					throw new Exception(strErr);
				}
		} 
		catch (Exception e) 
		{			
			vo.setStrMsgString("PackAdmTransDAO.setPatientStatus() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void setAdviceAdmNo(PackAdmTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_admission_advice_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try
		{
			
			daoObj = new HisDAO("ADT","PackAdmTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "adv_frm_validity", vo.getStrAdmissionAdviceValidityTo(),4);
			daoObj.setProcInValue(nProcIndex, "adv_to_validity", vo.getStrAdmissionAdviceValidityFrom(),5);
			daoObj.setProcInValue(nProcIndex, "patListType", "0",6); // --ONLINE ADVICE PATIENT LISTING
			daoObj.setProcInValue(nProcIndex, "searchStr", "",7);
			daoObj.setProcInValue(nProcIndex, "searchType", "1",8);
			daoObj.setProcInValue(nProcIndex, "toRows", "0",9);
			daoObj.setProcInValue(nProcIndex, "frmRows", "0",10);
			daoObj.setProcOutValue(nProcIndex, "err", 1,11);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
		
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			if(ws.size()==0 && vo.getStrIsAdmissionOnline().equals("1"))
			{
				vo.setStrAdviceStatus("0");
			}
			else
				vo.setStrAdviceStatus("1");
			if (strErr.equals("")) 
			{
				if (ws.next())
				{
					vo.setStrAdviceAdmNo(ws.getString(1));
					vo.setStrBookingDate(ws.getString(2));
					vo.setStrNewBorn(ws.getString(3));
					vo.setStrDeptName(ws.getString(4));
					vo.setStrUnitName(ws.getString(5));
					vo.setStrDeptCode(ws.getString(6));
					vo.setStrDeptUnitCode(ws.getString(7));
					vo.setStrTreatmentCategoryName(ws.getString(8));
					vo.setStrTreatmentCategoryCode(ws.getString(9));
					vo.setStrWardCode(ws.getString(10));						
				}
			} 
			else 
			{
					vo.setStrAdviceStatus("0");	
					throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PackAdmTransDAO.setAdviceAdmNo() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void getBillingPackageNames(PackAdmTransVO _PackAdmTransVO) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_bill_view.proc_hblt_tariff_mst(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("ADT","PackAdmTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_PackAdmTransVO.getStrHospCode(), 1);
			daoObj.setProcInValue(nProcIndex, "group_id", "0", 2);
			daoObj.setProcInValue(nProcIndex, "modeval", "2", 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				_PackAdmTransVO.setPackageComboValuesWs(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			_PackAdmTransVO.setStrMsgString("PackAdmTransDAO.getBillingPackageNames() --> "+ e.getMessage());
			_PackAdmTransVO.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

}
