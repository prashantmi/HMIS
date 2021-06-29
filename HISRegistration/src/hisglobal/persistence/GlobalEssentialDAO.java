package hisglobal.persistence;


import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;




public class GlobalEssentialDAO extends DataAccessObject
{
	Logger log;
	public GlobalEssentialDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}
	
	


	public HospitalMstVO getHospitalDetail(String hospitalCode) {
		
		HospitalMstVO hospitalMstVo=new HospitalMstVO();
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_clinical_util_view.PROC_GBLT_HOSPITAL_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
			daoObj = new HisDAO("Registration","EssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "p_modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_hospCode", hospitalCode,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
			webRs.beforeFirst();
			HelperMethods.populateVOfrmRS(hospitalMstVo, webRs);
		} 
		catch (Exception e) 
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
			{
				throw new HisRecordNotFoundException(e.getMessage());
			} 
			else
				throw new HisDataAccessException("GlobalDAO:getHospitalDetail:HelperMethods :: " + e);
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}		
		return hospitalMstVo;
	}

	public List getSystemDateAndFormat(UserVO _userVO)
	{
		WebRowSet webRs = null;
		HisDAO daoObj = null;
		List alRecord = new ArrayList();
		String strProcName = "{call PKG_REG_VIEW.PROC_SYSTEM_DATE_CR_FORMAT(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String date = "";
		List dateAndFormat = new ArrayList();
		boolean retValue = false;
		

		try 
		{
			daoObj = new HisDAO("Registration","GlobalEssentialDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "p_crno_size", Config.CR_NO_FORMAT_SIZE,2);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code",_userVO.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			retValue=daoObj.executeProcedureByPosition(super.getTransactionContext().getConnection(),nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			webRs = daoObj.getWebRowSet(nProcIndex, "resultset");
	
			if (strErr.equals("")) 
			{
				ResultSetMetaData rsmd = webRs.getMetaData();
				int no_of_col = rsmd.getColumnCount();
				while (webRs.next())
				{
					for (int i = 1; i <= no_of_col; i++)
					{
						alRecord.add(webRs.getString(i));
					}
				}
				Calendar cl = Calendar.getInstance();

				cl.set(Calendar.DATE, Integer.parseInt((String) alRecord.get(0)));
				cl.set(Calendar.MONTH, Integer.parseInt((String) alRecord.get(1)) - 1);
				cl.set(Calendar.YEAR, Integer.parseInt((String) alRecord.get(2)));
				cl.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) alRecord.get(3)));
				cl.set(Calendar.MINUTE, Integer.parseInt((String) alRecord.get(4)));
				cl.set(Calendar.SECOND, Integer.parseInt((String) alRecord.get(5)));
				Date dt = cl.getTime();
				SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
				sf.applyPattern("dd/MM/yyyy HH:mm");
				//TimeZone tz = cl.getTimeZone();
				date = sf.format(dt);
				String defaultCrNoFormat = (String) alRecord.get(6);

				dateAndFormat.add(0, date);
				dateAndFormat.add(1, defaultCrNoFormat);
				dateAndFormat.add(2, dt);
			} 
			else 
			{
				retValue=false;
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			retValue=false;
			//voObj.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "	+ e.getMessage());
			//voObj.setStrMsgType("1");
			e.printStackTrace();
			
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
			
			}
		return dateAndFormat;
	}
	


}
