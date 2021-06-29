
/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: BillSetup Master DATA
 * 
 * Created on: 16-09-2011
 */
package billing.masters.controller.data;

import javax.sql.rowset.WebRowSet;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import billing.BillConfigUtil;
import billing.masters.bo.BillSetupMstBO;
import billing.masters.controller.fb.BillSetupMstFB;
import billing.masters.controller.hlp.BillSetupMstHLP;
import billing.masters.vo.BillSetupMstVO;

public class BillSetupMstDATA {
	
	/**
	 * returns Emergency MultiRow Html Contents based on the index passed.
	 * 
	 * @param formBean
	 * @param index
	 * @return Emergency MultiRow Contests.
	 */
	public String getEmgMultiRow(BillSetupMstFB fb, int index) {

		BillSetupMstHLP hlp = new BillSetupMstHLP();
		
		String emgMultiRow=null;
		try
		{
			emgMultiRow = hlp.getIpdMultiRow(fb, 1);
		} catch (Exception e) {

			new HisException("Bill Setup Master", "BillSetupMstDATA.getMultiRow",
					e.getMessage());
		}
		return emgMultiRow;
		
		
	}

	/**
	 * returns IPD MultiRow Html Contents based on the index passed.
	 * 
	 * @param vo
	 * @param index
	 * @return Ipd MultiRow Contests.
	 */
	public String getIpdMultiRow(BillSetupMstFB fb, int index) {

		BillSetupMstHLP hlp = new BillSetupMstHLP();
		
		String ipdMultiRow=null;
		try
		{
			ipdMultiRow = hlp.getIpdMultiRow(fb, 1);
		} catch (Exception e) {

			new HisException("Bill Setup Master", "BillSetupMstDATA.getMultiRow",
					e.getMessage());
		}
		return ipdMultiRow;
	}

	/**
	 * returns IPDSecMultiRow Html Contents based on the index passed.
	 * 
	 * @param vo
	 * @param index
	 * @return IPDSecMultiRow MultiRow Contests.
	 */
	public String getIpdSecMultiRow(BillSetupMstFB fb, int index) {

		BillSetupMstHLP hlp = new BillSetupMstHLP();
		
		String ipdSecMultiRow=null;
		try
		{
			ipdSecMultiRow = hlp.getIpdSecMultiRow(fb, 2);
		} catch (Exception e) {

			new HisException("Bill Setup Master", "BillSetupMstDATA.getIpdSecMultiRow",
					e.getMessage());
		}
		return ipdSecMultiRow;
	}

	/**
	 * returns IpdThirdMultiRow Html Contents based on the index passed.
	 * 
	 * @param vo
	 * @param index
	 * @return IpdThirdMultiRow MultiRow Contests.
	 */
	public String getIpdThirdMultiRow(BillSetupMstFB fb, int index) {
		BillSetupMstHLP hlp = new BillSetupMstHLP();
		
		String ipdThirdMultiRow=null;
		try
		{
			ipdThirdMultiRow = hlp.getIpdThirdMultiRow(fb, 3);
		} catch (Exception e) {

			new HisException("Bill Setup Master", "BillSetupMstDATA.getIpdThirdMultiRow",
					e.getMessage());
		}
		return ipdThirdMultiRow;

	}

	
	
	public String getIpdComplChrgWardValues(BillSetupMstFB fb) {

		String ipdComplChrgWardValues=null;
		HisUtil hisUtil =null;
		try {
			hisUtil = new HisUtil("Bill Setup",
			"BillSetupMstVO.getIpdComplChrgWardValues");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.sec_wardtype").replace("?",
					fb.getStrHospitalCode());
			ipdComplChrgWardValues = hisUtil.getOptionValue(qry, fb
					.getIpdSecWrdTmpName(), "0^Select Value");
			hisUtil = null;

		} catch (Exception e) {
			e.printStackTrace();
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdComplChrgWardValues", e.getMessage());
		}

		return ipdComplChrgWardValues;
	}
	public String getThirdWardTypeValue(BillSetupMstFB fb) {
		
	
		String ipdComplChrgWardValues=null;
		HisUtil hisUtil =null;
		try {
			hisUtil = new HisUtil("Bill Setup",
				"BillSetupMstVO.getThirdWardTypeValue");

			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.combo.wardtype").replace("?",
							fb.getStrHospitalCode());
			ipdComplChrgWardValues = hisUtil.getOptionValue(qry, fb
					.getIpdThirdWardTmpName(), "0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdComplChrgWardValues", e.getMessage());
		} finally {

			hisUtil = null;
		}

		return ipdComplChrgWardValues;
	}
	
public String getIpdComplChrgGroupValues(BillSetupMstFB fb) {
		
		
		String ipdComplChrgGroupValues=null;
		HisUtil hisUtil =null;
		try {
		    hisUtil = new HisUtil("Bill Setup","BillSetupMstVO.getIpdComplChrgGroupValues");
			String qry = billing.qryHandler_billing.getQuery(1,"select.billsetup.1").replace("?",fb.getStrHospitalCode());
			ipdComplChrgGroupValues = hisUtil.getOptionValue(qry, fb.getIpdSecGrpTmpName(), "0^Select Value");
			hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdComplChrgGroupValues", e.getMessage());
		}
		return ipdComplChrgGroupValues;
	}

	public String getIpdComplChrgTariffValues(BillSetupMstFB fb) {
		
		String ipdComplChrgTariffValues=null;
		HisUtil hisUtil =null;
		try {
		 hisUtil = new HisUtil("Bill Setup","BillSetupMstVO.getGroupValues");
		 String qry = billing.qryHandler_billing.getQuery(1,"select.billsetup.2");
	  	 qry=qry.replace("?",fb.getIpdSecGrpTmpName());
		 ipdComplChrgTariffValues = hisUtil.getOptionValue(qry.replace("#",fb.getStrHospitalCode()),fb.getIpdSecTrfTmpName(),"0^Select Value");
			 hisUtil = null;
	
		} catch (Exception e) {
			new HisException("Bill Setup", "BillSetupMstDATA.getTariffValues", e.getMessage());
		}
	
		return ipdComplChrgTariffValues;
	}
	
public String getIpdComplChrgSecTariffValues(BillSetupMstFB fb) {
		
		String ipdComplChrgSecTariffValuesI=null;
		HisUtil hisUtil =null;
		try {
		hisUtil = new HisUtil("Bill Setup","BillSetupMstVO.getIpdComplChrgSecTariffValues");
 		String qry = billing.qryHandler_billing.getQuery(1,"select.billsetup.2");
		qry=qry.replace("?",fb.getIpdSecGrpTmpName());
		ipdComplChrgSecTariffValuesI = hisUtil.getOptionValue(qry.replace("#", fb.getStrHospitalCode()), fb.getIpdSecTrfTmpName(), "0^Select Value");
		hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdComplChrgSecTariffValues", e
							.getMessage());
		}

		return ipdComplChrgSecTariffValuesI;
	}
	
	public String getIpdComplChrgThirdGroupValues(BillSetupMstFB fb) {
		String ipdComplChrgThirdGroupValues=null;
		HisUtil hisUtil =null;
		try {
		hisUtil = new HisUtil("Bill Setup","BillSetupMstVO.getIpdComplChrgThirdGroupValues");
		String qry = billing.qryHandler_billing.getQuery(1,	"select.billsetup.1").replace("?", fb.getStrHospitalCode());
		ipdComplChrgThirdGroupValues = hisUtil.getOptionValue(qry, fb.getIpdThirdGrpTmpName(), "0^Select Value");
		hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup","BillSetupMstDATA.getIpdComplChrgThirdGroupValues", e.getMessage());
		}

		return ipdComplChrgThirdGroupValues;
	}
	
	public String getIpdComplChrgThirdTariffValues(BillSetupMstFB fb) {
		String ipdComplChrgThirdTariffValues=null;
		HisUtil hisUtil =null;
		try {
		hisUtil = new HisUtil("Bill Setup","BillSetupMstVO.getIpdComplChrgThirdTariffValues");
		String qry = billing.qryHandler_billing.getQuery(1,	"select.billsetup.2");
		qry=qry.replace("?", fb.getIpdThirdGrpTmpName());
		ipdComplChrgThirdTariffValues = hisUtil.getOptionValue(qry.replace("#", fb.getStrHospitalCode()), fb.getIpdThirdTrfTmpName(), "0^Select Value");
		hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup","BillSetupMstDATA.getIpdComplChrgThirdTariffValues", e.getMessage());
		}

		return ipdComplChrgThirdTariffValues;
		}
	
	public String getIpdBedCalcWardValues(BillSetupMstFB fb) {
		String ipdBedCalcWardValues=null;
		HisUtil hisUtil =null;
		try {
		 hisUtil = new HisUtil("Bill Setup",
				"BillSetupMstVO.getIpdBedCalcWardValues");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.0");
			ipdBedCalcWardValues = hisUtil.getOptionValue(qry, fb
					.getIpdBedCalcWard(), "0^Select Value");

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdBedCalcWardValues", e.getMessage());
		} finally {

			if (hisUtil != null)
				hisUtil = null;

		}

		return ipdBedCalcWardValues;
	}
	public String getIpdComplChrgSecGroupValues(BillSetupMstFB fb) {
		String ipdComplChrgSecGroupValues=null;
		HisUtil hisUtil =null;
		try {
		hisUtil = new HisUtil("Bill Setup","BillSetupMstVO.getIpdComplChrgSecGroupValues");
		String qry = billing.qryHandler_billing.getQuery(1,	"select.billsetup.1").replace("?", fb.getStrHospitalCode());
			ipdComplChrgSecGroupValues = hisUtil.getOptionValue(qry, fb.getIpdSecGrpTmpName(), "0^Select Value");
			hisUtil = null;
		} catch (Exception e) {
			new HisException("Bill Setup","BillSetupMstDATA.getIpdComplChrgSecGroupValues", e.getMessage());
		}
		return ipdComplChrgSecGroupValues;
	}
	
	public String getEmgGroupValues(BillSetupMstFB fb) {
		String emgGroupValues=null;
		HisUtil hisUtil =null;
		try {

		 hisUtil = new HisUtil("Bill Setup",
				"BillSetupMstVO.getIpdComplChrgGroupValues");
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.1");

			emgGroupValues = hisUtil.getOptionValue(qry, fb
					.getEmgGrpTmpName(), "0^Select Value");
			hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdComplChrgGroupValues", e.getMessage());
		}

		return emgGroupValues;
	}

	public String getEmgTariffValues(BillSetupMstFB fb) {
		String emgTariffValues=null;
		HisUtil hisUtil =null;
		try {
		 hisUtil = new HisUtil("Bill Setup",
				"BillSetupMstVO.getGroupValues");

			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.2");

			emgTariffValues = hisUtil.getOptionValue(qry.replace("?", fb
					.getEmgGrpTmpName()), fb.getEmgTrfTmpName(),
					"0^Select Value");
			hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup", "BillSetupMstDATA.getTariffValues", e
					.getMessage());
		}

		return emgTariffValues;
	}
	public String getIpdComplChrgSecTreatmentCategoryValues(BillSetupMstFB fb) {
		String ipdComplChrgSecTreatmentCategoryValues=null;
		HisUtil hisUtil =null;
		try {

		hisUtil = new HisUtil("Bill Setup",
				"BillSetupMstVO.getGroupValues");

			String qry = billing.qryHandler_billing.getQuery(1,
					"select.billsetup.5").replace("?",
					BillConfigUtil.SUPER_HOSPITAL_CODE);

			ipdComplChrgSecTreatmentCategoryValues = hisUtil.getOptionValue(
					qry, fb.getIpdSecTreatCatTempName(), "0^Select Value");
			hisUtil = null;

		} catch (Exception e) {
			new HisException("Bill Setup",
					"BillSetupMstDATA.getIpdComplChrgSecTreatmentCategoryValues", e
							.getMessage());
		}

		return ipdComplChrgSecTreatmentCategoryValues;
	}
	
	public void genInsert(BillSetupMstFB fb) 
	{
		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		try 
		{
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			vo.setStrPaidCategoryValue(fb.getStrPaidCategoryValue());
			vo.setStrArogyaShreeCategoryvalue(fb.getStrArogyaShreeCategoryValue());
			vo.setStrArogyaShreeTSCategoryvalue(fb.getStrArogyaShreeTSCategoryValue());
			vo.setStrCGSHCategoryvalue(fb.getStrCGSHCategoryValue());
			vo.setStrWSHCategoryValue(fb.getStrWSHCategoryValue());
			vo.setStrOSTFCategoryvalue(fb.getStrOSTFCategoryvalue());
			vo.setNiramayaValue(fb.getNiramayaValue());
			vo.setHindiReq(fb.getHindiReq());
			vo.setHeaderReq(fb.getHeaderReq());
			//vo.setStrUrgTrfSur(fb.getStrUrgTrfSur());
			bo.genInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
			fb.setStrPaidCategoryValue(vo.getStrPaidCategoryValue());
			fb.setStrArogyaShreeCategoryValue(vo.getStrArogyaShreeCategoryvalue());
			fb.setStrArogyaShreeTSCategoryValue(vo.getStrArogyaShreeTSCategoryvalue());
			fb.setStrCGSHCategoryValue(vo.getStrCGSHCategoryvalue());
			fb.setStrWSHCategoryValue(vo.getStrWSHCategoryValue());
			fb.setStrOSTFCategoryvalue(vo.getStrOSTFCategoryvalue());
			fb.setNiramayaValue(vo.getNiramayaValue());
			fb.setHindiReq(vo.getHindiReq());
			fb.setHeaderReq(vo.getHeaderReq());
			//fb.setStrUrgTrfSur(vo.getStrUrgTrfSur());
		} 
		catch (Exception e) 
		{
			new HisException("Bill Setup","BillSetupMstDATA.genInsert", e.getMessage());
		}
	}
	
	public void surInsert(BillSetupMstFB fb) 
	{
		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		try 
		{
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			vo.setStrSurCc(fb.getStrSurCc());
			vo.setStrSurDc(fb.getStrSurDc());
			vo.setStrSurIc(fb.getStrSurIc());
			vo.setStrSurId(fb.getStrSurId());
			vo.setStrSurCc1(fb.getStrSurCc1());
			vo.setStrSurDc1(fb.getStrSurDc1());
			vo.setStrSurIc1(fb.getStrSurIc1());
			vo.setStrSurId1(fb.getStrSurId1());
			bo.surInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
			fb.setStrSurCc(vo.getStrSurCc());
			fb.setStrSurDc(vo.getStrSurDc());
			fb.setStrSurIc(vo.getStrSurIc());
			fb.setStrSurId(vo.getStrSurId());
			fb.setStrSurCc1(vo.getStrSurCc1());
			fb.setStrSurDc1(vo.getStrSurDc1());
			fb.setStrSurIc1(vo.getStrSurIc1());
			fb.setStrSurId1(vo.getStrSurId1());
		} 
		catch (Exception e) 
		{
			new HisException("Bill Setup","BillSetupMstDATA.surInsert", e.getMessage());
		}
	}

	/*public boolean genDBInsert(BillSetupMstFB fb) {

		boolean retVal = false;
		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.genDBInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.genDBInsert()", e
					.getMessage());
		}

		return retVal;
	}*/

	/*public void genPropInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.genPropInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.genPropInsert()", e
					.getMessage());
		}

	}*/

	public void setGenPropValues(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		//select.billsetup.gentariff.1
		
		String strGeneralTariffValues=null;
		HisUtil hisUtil =null;
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			hisUtil= new HisUtil("Bill Setup","BillSetupMstVO.setGenPropValues");
			bo.setGenPropValues(vo);
			String qry = billing.qryHandler_billing.getQuery(1,"select.billsetup.gentariff.1").replace("?",BillConfigUtil.GROUP_ID_GENERAL_CHARGES);
			strGeneralTariffValues = hisUtil.getOptionValue(qry, vo.getStrDefaultTariffCode(), "0^Select Value");
			vo.setStrGeneralTariffValues(strGeneralTariffValues);
			fb.setStrPaidCategoryValue(vo.getStrPaidCategoryValue());
			fb.setStrArogyaShreeCategoryValue(vo.getStrArogyaShreeCategoryvalue());
			fb.setStrArogyaShreeTSCategoryValue(vo.getStrArogyaShreeTSCategoryvalue());
			fb.setStrOSTFCategoryvalue(vo.getStrOSTFCategoryvalue());
			System.out.println("------------------------------------------>>>>>>>>>>>>>>>>>OSTF= "+vo.getStrOSTFCategoryvalue());
			fb.setNiramayaValue(vo.getNiramayaValue());
			System.out.println("==========================>>>>>>>>>>>>>>>>>>>>fb niramaya category= "+fb.getNiramayaValue());
			System.out.println("==========================>>>>>>>>>>>>>>>>>>>>vo niramaya category= "+vo.getNiramayaValue());
			fb.setStrGenCategoryValue(vo.getStrGenCategoryValue());
			fb.setStrWSHCategoryValue(vo.getStrWSHCategoryValue());
			fb.setStrCGSHCategoryValue(vo.getStrCGSHCategoryvalue());
			//fb.setStrUrgTrfSur(vo.getStrUrgTrfSur());
			TransferObjectFactory.populateData(fb,vo);
			//System.out.println("dayend time bound flag"+vo.getStrDayEndTimeBoundFlag()+"-----"+vo.getStrDayEndTime());
			String[] strDayEndTIme= vo.getStrDayEndTime().replace(":", "#").split("#");
			fb.setStrDayEndTimeHour(strDayEndTIme[0]);
			fb.setStrDayEndTimeMin(strDayEndTIme[1]);
			fb.setStrDayEndTimeAMPM(strDayEndTIme[2]);
		    
		} 
		catch (Exception e) 
		{
			vo.setStrErr("Record Not Found!!!");
			new HisException("Bill Setup", "BOBillSetup.setGenPropValues()", e.getMessage());
		}
	}

	public void opdInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		BillConfigUtil bcu=null;
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.opdInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
			bcu=new BillConfigUtil(vo.getStrHospitalCode());
			System.out.println("vo.getOpdDiscount()= "+vo.getOpdDiscount());
			System.out.println("bcu.getOpdDiscount()= "+bcu.getOpdDiscount());
			System.out.println("bcu.getIpdDiscount()= "+bcu.getIpdDiscount());
			System.out.println("vo.getOpdDiscountClerk()= "+vo.getOpdDiscountClerk());
			System.out.println("bcu.getIpdDiscountClerk()= "+bcu.getIpdDiscountClerk());
			System.out.println("bcu.getOpdDiscountClerk()= "+bcu.getOpdDiscountClerk());
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.opdInsert()", e
					.getMessage());
		}

	}

	public void setOpdValues(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setOpdValues(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setOpdValues()", e
					.getMessage());
		}

	}

	public void billFormatInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.billFormatInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.billFormatInsert()", e
					.getMessage());
		}
	}

	public void setBillFormatValues(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setBillFormatValues(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setBillFormatValues()", e
					.getMessage());
		}
	}
	
	public void setSurchargeValues(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setSurchargeValues(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setSurchargeValues()", e
					.getMessage());
		}
	}

	public void jobsInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.jobsInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.jobsInsert()", e
					.getMessage());
		}
	}

	public void setJobsValues(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setJobsValues(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setJobsValues()", e
					.getMessage());
		}
	}

	public void ipdBedCalcInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.ipdBedCalcInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.ipdBedCalcInsert()", e
					.getMessage());
		}
	}

	public void setIpdBedCalc(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setIpdBedCalc(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setIpdBedCalc()", e
					.getMessage());
		}

	}

	public void ipdGenInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		BillConfigUtil bcu=null;
		try {
			
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.ipdGenInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
			bcu=new BillConfigUtil(vo.getStrHospitalCode());
		System.out.println("vo.getOpdDiscount()= "+vo.getOpdDiscount());
		System.out.println("bcu.getIpdDiscount()= "+bcu.getIpdDiscount());
		System.out.println("vo.getOpdDiscountClerk()= "+vo.getOpdDiscountClerk());
		System.out.println("bcu.getIpdDiscountClerk()= "+bcu.getIpdDiscountClerk());
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.ipdGenInsert()", e
					.getMessage());
		}
	}

	/*public boolean ipdGenDBInsert(BillSetupMstFB fb) {

		boolean retVal = false;
		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			retVal=bo.ipdGenDBInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.ipdGenInsert()", e
					.getMessage());
		}

		return retVal;
	}*/

	/*public void ipdGenPropInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.ipdGenPropInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.ipdGenPropInsert()", e
					.getMessage());
		}
	}*/

	public void setIpdGen(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setIpdGen(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setIpdGen()", e
					.getMessage());
		}
	}

	public void emgInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.emgInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.emgInsert()", e
					.getMessage());
		}

	}

	/*public boolean emgDBInsert(BillSetupMstFB fb) {

		boolean retVal = false;
		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			retVal=bo.emgDBInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.emgDBInsert()", e
					.getMessage());
		}


		return retVal;
	}

	public void emgPropInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.emgPropInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.emgPropInsert()", e
					.getMessage());
		}

	}*/

	public void setEmgValues(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setEmgValues(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setEmgValues()", e
					.getMessage());
		}
	}

	public void ipdComplChargeInsert(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {
			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.ipdComplChargeInsert(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {
					
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.ipdComplChargeInsert()", e
					.getMessage());
		}

	}

	public void setIpdComplCharge(BillSetupMstFB fb) {

		BillSetupMstVO vo=null;
		BillSetupMstBO bo=null;
		
		
		try {

			vo=new BillSetupMstVO();
			bo=new BillSetupMstBO();
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", fb);
			bo.setIpdComplCharge(vo);
			TransferObjectFactory.populateData(fb,vo);
			vo.setIpdComplChrgGroupValues(this.getIpdComplChrgGroupValues(fb));//Used For making Group Combo
			vo.setIpdComplChrgWardValues(this.getIpdComplChrgWardValues(fb));//Used For making Ward Type Combo
			vo.setIpdComplChrgTariffValues(this.getIpdComplChrgTariffValues(fb));//Used For making Tariff combo according the Group
			vo.setIpdComplChrgSecTariffValuesI(this.getIpdComplChrgSecTariffValues(fb));
			vo.setIpdComplChrgThirdGroupValues(this.getIpdComplChrgThirdGroupValues(fb));
			vo.setIpdComplChrgThirdTariffValues(this.getIpdComplChrgThirdTariffValues(fb));
			vo.setIpdComplChrgSecGroupValues(this.getIpdComplChrgSecGroupValues(fb));
			vo.setIpdComplChrgSecTreatmentCategoryValues(this.getIpdComplChrgSecTreatmentCategoryValues(fb));
			TransferObjectFactory.populateData(fb,vo);
			vo.setIpdMultiRow(this.getIpdMultiRow(fb,1));
			vo.setIpdSecMultiRow(this.getIpdSecMultiRow(fb,2));
			vo.setIpdThirdMultiRow(this.getIpdThirdMultiRow(fb,3));
			
			
			TransferObjectFactory.populateData(fb,vo);
			
			
		} catch (Exception e) {
					
			e.printStackTrace();
			vo.setStrErr("Record Not Updated");
			new HisException("Bill Setup", "BOBillSetup.setIpdComplCharge()", e.getMessage());
		}
	}

	/**
	 * to get Consumable Charges Tariff Combo.
	 * 
	 * @param formBean
	 * @return String
	 */
	public String getConsumableChargesTariffCombo(BillSetupMstFB formBean) {
		// TODO Auto-generated method stub
		String strTariffCmb = "";
		HisUtil hisutil = new HisUtil("Billing", "BillSetupMstVO");
		
         BillSetupMstBO bo = new BillSetupMstBO();
		
		BillSetupMstVO vo = null;
		
		WebRowSet wb = null;

		try {
            vo = new BillSetupMstVO();
			
			vo = (BillSetupMstVO) TransferObjectFactory.populateData("billing.masters.vo.BillSetupMstVO", formBean);
			wb = bo.getConsumableChargesTariffCombo(vo);
			if(wb!=null && wb.size()>0){
				strTariffCmb = hisutil.getOptionValue(wb, vo.getStrConsumableChargesTariffCode(),"0^Select Value", true, false);
			}else{
				strTariffCmb = "<option value='0'>Select Value</option>";
			}
			
			
			HelperMethods.populate(formBean,vo);
		} catch (Exception e) {
			new HisException("billing", "BillSetupMstDATA.getConsumableChargesTariffCombo()", e.getMessage());
		}

		return strTariffCmb;
	}

	
	
	

}
