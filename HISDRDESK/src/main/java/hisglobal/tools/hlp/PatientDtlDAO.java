package hisglobal.tools.hlp;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class PatientDtlDAO {

	public static void setPatientDtl(GlobalVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.PROC_HRGT_PATIENT_DTL(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		String strCrNum = voObj.getStrValue1();

		try 
		{
			daoObj = new HisDAO("Global Patient Details","PatientDtlDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && !strCrNum.equals("")) 
			{
				daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcOutValue(nProcIndex, "err", 1,3);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) 
				{
					voObj.setGblWs1(ws);
				} 
				else 
				{
					throw new Exception(strErr);
				}
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("PatientDtlDAO.setPatientDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
	
	public static void setAdmissionDtl(GlobalVO voObj) 
	{
		//Currently Admitted Patient-Not Discharged,Admission Not Cancelled(14), Admission Not Reported False(13)
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_PATADMISSION_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		int nProcIndex = 0;
		String strErr = "";

		String strCrNum = voObj.getStrValue1();
		String strHosptialCode = voObj.getStrValue2();
		
		try 
		{
			daoObj = new HisDAO("HisGlobal","PatientDtlDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && strHosptialCode != null) 
			{
				daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "seatid", "",3);
				daoObj.setProcInValue(nProcIndex, "modifytime", "",4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHosptialCode,5);				
				daoObj.setProcInValue(nProcIndex, "patlisttype", "",6);
				daoObj.setProcInValue(nProcIndex, "searchstr", "",7);
				daoObj.setProcInValue(nProcIndex, "searchtype", "",8);
				daoObj.setProcInValue(nProcIndex, "torows", "",9);
				daoObj.setProcInValue(nProcIndex, "frmrows", "",10);
				daoObj.setProcInValue(nProcIndex, "onlinedis", "",11);
				daoObj.setProcInValue(nProcIndex, "deptUnitCode", "",12);
				daoObj.setProcInValue(nProcIndex, "wardCode", "",13);
				daoObj.setProcOutValue(nProcIndex, "err", 1,14);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,15);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) 
				{
					voObj.setGblWs1(ws);
				} 
				else 
				{
					throw new Exception(strErr);
				}
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("PatientDtlDAO.setAdmissionDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
//added by shefali on 26-Aug-2014 for patient treatment detail in issu eto patient
	public static void setPatientTreatmentDtl(GlobalVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.PROC_HRGT_PATIENT_TREATMENT_DTL(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		String strCrNum = voObj.getStrValue1();
		String strHospcode = voObj.getStrValue2();
		try {
			daoObj = new HisDAO("Global Patient Details","PatientDtlDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && !strCrNum.equals("")) {
				
				daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
			//	System.out.println("strHospcode"+strHospcode);
				daoObj.setProcInValue(nProcIndex, "hosp_code",strHospcode,3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
            	if (strErr.equals("")) {
					voObj.setGblWs2(ws);
				} 
            	else 
            	{
					throw new Exception(strErr);
				}
				
			 } 

			} catch (Exception e) 
			{
			 System.out.println("Error Msg is-->"+e.getMessage());
			 voObj.setStrMsgString("PatientDtlDAO.setPatientTreatmentDtl() --> "+ e.getMessage());
			 voObj.setStrMsgType("1");

			} 
			finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
				}
			}

	}
	public static void setPatientDtlWithoutCatExpiryCheck(GlobalVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_simple_view.PROC_HRGT_PATIENT_DTL(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		String strCrNum = voObj.getStrValue1();

		try 
		{
			daoObj = new HisDAO("HisGlobal","PatientDtlDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && !strCrNum.equals("")) 
			{
				daoObj.setProcInValue(nProcIndex, "modeVal", "2",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcOutValue(nProcIndex, "err", 1,3);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) 
				{
					voObj.setGblWs1(ws);
				} 
				else 
				{
					throw new Exception(strErr);
				}
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("PatientDtlDAO.setPatientDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
	//added by shalini to view patient admission dtl even if patient is not admitted
	public static void setAdmissionDtlView(GlobalVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_PATADMISSION_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		int nProcIndex = 0;

		String strErr = "";

		String strCrNum = voObj.getStrValue1();
		String strHosptialCode = voObj.getStrValue2();
		
		try {
			daoObj = new HisDAO("Global Patient Details ","PatientDtlDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && strHosptialCode != null) 
			{

				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", strHosptialCode,5);
				
				daoObj.setProcInValue(nProcIndex, "modeval", "11",1);
				daoObj.setProcInValue(nProcIndex, "seatid", "",3);
				daoObj.setProcInValue(nProcIndex, "modifytime", "",4);
				daoObj.setProcInValue(nProcIndex, "patlisttype", "",6);
				daoObj.setProcInValue(nProcIndex, "searchstr", "",7);
				daoObj.setProcInValue(nProcIndex, "searchtype", "",8);
				daoObj.setProcInValue(nProcIndex, "torows", "",9);
				daoObj.setProcInValue(nProcIndex, "frmrows", "",10);
				daoObj.setProcInValue(nProcIndex, "onlinedis", "",11);
				daoObj.setProcInValue(nProcIndex, "deptUnitCode", "",12);
				daoObj.setProcInValue(nProcIndex, "wardCode", "",13);
				daoObj.setProcOutValue(nProcIndex, "err", 1,14);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,15);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					voObj.setGblWs1(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj.setStrMsgString("PatientDtlDAO.setAdmissionDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
}
