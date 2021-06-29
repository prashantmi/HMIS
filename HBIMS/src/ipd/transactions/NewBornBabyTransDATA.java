package ipd.transactions;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.ADTPrintingTransHLP;
import hisglobal.utility.HisPrinter;
import ipd.HLPOccupationDetails;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewBornBabyTransDATA
{
	public static String DATE_FORMAT_NOW = "dd-MMM-yyyy/HH:mm:ss";

	public static String now()
	{
		HisUtil util=null;
		String a="";
		util=new HisUtil("transaction","PatientTransferTransHLP");
		try
		{
			a= util.getASDate(DATE_FORMAT_NOW);
		}
	catch(Exception e)
	{
	}
	/*Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	return sdf.format(cal.getTime());*/
	return a;
	}
	/**
	 * This function is used to set initial parameters required to display on main page
	 * @param formBean
	 */
	public static void initPatientAdmission(NewBornBabyTransFB formBean)
	{
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		NewBornBabyTransVO voObj= null;
		NewBornBabyTransBO bo= null;
		HisUtil util = null;
		String temp="";
		try
		 {
			icu   =  new IpdConfigUtil(formBean.getStrHospCode());
			voObj =  new NewBornBabyTransVO();
			bo    =  new NewBornBabyTransBO();
			util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			String dept=icu.getStrNewBornBabyDefaultDept(); //vo.getStrMotherDeptCode();
			formBean.setStrDeptNameNewBorn(dept);
			voObj.setStrDeptNameNewBorn(dept);
			bo.setPatientDtl(voObj);
			if(voObj.getStrNumberOfChildrenBorn()==null)
            	voObj.setStrNumberOfChildrenBorn("0");
            if(voObj.getStrMaxBabyAllowed()==null)
            	voObj.setStrMaxBabyAllowed("9");
            if(icu.getStrMaxNoOfBabyMotherCanBorn()==null)
            {
            	formBean.setStrCrNo("");
				formBean.setStrMsgString("Configuration for Maximum No.of Baby Mother can born is not set. Please set from Inpatient Config Master.");
				throw new Exception("");
            }
			if(voObj.getWrsAdmissionTypeValues()!=null && voObj.getWrsAdmissionTypeValues().size()>0){
				formBean.setStrAdmissionTypeValues(util.getOptionValue(voObj.getWrsAdmissionTypeValues(), voObj.getStrAdmissionType(),"0^Select Value", false));
			}else{
				formBean.setStrAdmissionTypeValues("<option value='0'>Select Value</option>");				
			}
			if(voObj.getWrsReliefFundValues()!=null && voObj.getWrsReliefFundValues().size()>0){
				formBean.setStrReliefFundValues(util.getOptionValue(voObj.getWrsReliefFundValues(), voObj.getStrReliefFund(),"0^Select Value", false));
			}else{
				formBean.setStrAdmissionTypeValues("<option value='0'>Select Value</option>");				
			}
			
			//System.out.println("icu.getStrMaxNoOfBabyMotherCanBorn()"+icu.getStrMaxNoOfBabyMotherCanBorn()+"voObj.getStrMaxBabyAllowed()"+voObj.getStrMaxBabyAllowed());
			if(voObj.getStrMsgType().equals("6")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already gave birth to "
						+(Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())-Integer.parseInt(voObj.getStrMaxBabyAllowed()))+" " +
								"Babies no more Baby is Allowed.");
				throw new Exception("");
			}else if(voObj.getStrMsgType().equals("7")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient age is too small for Baby Born./Baby not Allowed");
				throw new Exception("");
			}
			else if(voObj.getStrMsgType().equals("4")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Age is too Small to be a Mother.");
		        throw new Exception("");
			}else if(voObj.getStrMsgType().equals("8")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has not been accepted.");
				throw new Exception("");
			}
		 	else if(voObj.getStrMsgType().equals("9")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is not Admitted.");
				throw new Exception("");
			}
			if (Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) == ((Integer
					.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn()))-(Integer.parseInt(voObj.getStrMaxBabyAllowed()))) && Integer.parseInt(voObj.getStrNumberOfChildrenBorn())>0) {
				formBean.setStrCrNo("");
				formBean
						.setStrMsgString("This Patient has already given birth to "
								+ voObj.getStrNumberOfChildrenBorn()
								+ " babies.");
				voObj.setStrMsgType("10");
				throw new Exception("");

			}
			int maxBaby = Integer.parseInt(voObj.getStrMaxBabyAllowed());
			formBean.setStrMaxBabyAllowed(Integer.toString(maxBaby));
			int maxBabyInConfig = Integer.parseInt(icu
					.getStrMaxNoOfBabyMotherCanBorn());
			if (maxBaby < maxBabyInConfig) {
				formBean.setStrIsGivenBirth("1");
			}
			formBean.setStrNumberOfChildrenBorn(voObj
					.getStrNumberOfChildrenBorn());
			formBean.setStrOnlineOrNot(icu.getStrNewBornBabyProcessType());
			String strAdmitted = IpdTransConfig.getOpdCode();
			
			
			//System.out.println(voObj.getStrValidCrNo());
			//System.out.println(voObj.getStrGenderCode());
			//System.out.println(IpdTransConfig.getFemaleCode());
			
			if(voObj.getStrValidCrNo().equals("1")||!(voObj.getStrGenderCode().equals(IpdTransConfig.getFemaleCode())))
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No./Data not found");
			}
			else if((voObj.getStrPatStatusCode().equals(strAdmitted)))
			{
				formBean.setStrCrNo("");
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrMsgString("Patient is in OPD");
			}
			else
			{
			
			temp = util.getOptionValue(voObj.getDepartWS(), dept,"0^Select Value", false);
			formBean.setStrDeptValue(temp);
			temp = util.getOptionValue(voObj.getUnitWS(), voObj.getStrMotherUnitCode(),"0^Select Value", false);
			formBean.setStrUnitValue(temp);
			formBean.setStrTreatmentCategVal(temp);
			/*temp = util.getOptionValue(voObj.getConsWS(), "0","0^Select Value", false);
			formBean.setStrConsValue(temp);*/
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			formBean.setStrAdmissionCharge(ipdConfig.getStrNewBornBabyAdmissionCharge());
			formBean.setStrNewBornRegistrationCharge(ipdConfig.getStrNewBornBabyRegistrationCharge());
			formBean.setStrAdmissionAdvance(ipdConfig.getStrAdvanceAmountNewBornBaby());
			formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
			formBean.setStrNewBornRegistrationChargeVal(voObj.getStrNewBornRegistrationChargeVal());
			formBean.setStrAddressModi(NewBornBabyTransHLP.getPatientDetailModi(voObj));
			formBean.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
			strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
			formBean.setOccupationDetailValues(strOccDtlsview);
			formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
			formBean.setStrDeptUnitName(voObj.getStrMotherDeptName()+"/"+voObj.getStrMotherUnitName());
			formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
			formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
			formBean.setStrMotherName(voObj.getStrMotherName());
			formBean.setStrMotherNationality(voObj.getStrMotherNationality());
			formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
			formBean.setStrMotherReligion(voObj.getStrMotherReligion());
			formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
			formBean.setStrMotherDeptCode(voObj.getStrMotherDeptCode());
			formBean.setStrMotherUnitCode(voObj.getStrMotherUnitCode());
			formBean.setStrMotherWardCode(voObj.getStrMotherWardCode());
			formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
			formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
			formBean.setStrMotherRoomTypeCode(voObj.getStrMotherRoomTypeCode());
			formBean.setStrMotherBedTypeTypeCode(voObj.getStrMotherBedTypeTypeCode());
			formBean.setStrMotherWardName(voObj.getStrMotherWardName());
			formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
			formBean.setStrWardName(voObj.getStrMotherWardName());
			formBean.setStrRoom(voObj.getStrRoom());
			formBean.setStrCrNo(voObj.getStrCrNo());
			formBean.setStrRoomCode(voObj.getStrRoomCode());
			formBean.setStrBedCode(voObj.getStrBedCode());
			formBean.setStrPatCatCode(voObj.getStrPatCatCode());
			formBean.setStrConsultantCode(voObj.getStrConsultantCode());
			formBean.setStrConsultantName(voObj.getStrConsultantName());
			formBean.setStrRegistrationChargeHidden(voObj.getStrNewBornRegistrationChargeVal());
			formBean.setStrMotherDeptName(voObj.getStrMotherDeptName());
			formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
			formBean.setStrAdmissionChargeHidden(voObj.getStrAdmissionChargeValue());
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrIsBedSharable(voObj.getStrIsBedSharable());
			formBean.setSetStrMotherCatgrp(voObj.getSetStrMotherCatgrp());
			formBean.setStrMotherRoomCode(voObj.getStrMotherRoomCode());
			formBean.setStrCtDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrMothAdmDate(voObj.getStrMothAdmDate());
			//formBean.setStrDistrictName("0");
			formBean.setPrintMode("1");
				if(voObj.getStrMsgType().equals("1"))
					throw new Exception(voObj.getStrMsgString());
			}
			if(icu.getStrNewBornBabyProcessType().equals("1")) //online case
				formBean.setStrOnlineBabyList(NewBornBabyTransHLP.onlineBabyBornEntery(voObj));

			if(voObj.getStrMsgType().equals("1"))
				throw new Exception(voObj.getStrMsgString());

		} /*catch (SQLException e) {

			formBean.setStrMsgString("No Data in OCC Table");
			formBean.setStrMsgType("1");

			new HisException("Patient Admission",
					"PatientAdmissionDATA.initPatientAdmission()->", e
							.getMessage());

		} */catch (Exception e) {
			e.printStackTrace();
			if((!voObj.getStrMsgType().equals("6"))&&(!voObj.getStrMsgType().equals("7"))&&(!voObj.getStrMsgType().equals("8"))&&(!voObj.getStrMsgType().equals("9"))&&(!voObj.getStrMsgType().equals("10"))&& (!voObj.getStrMsgType().equals("4"))){
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->initAdmissionAdvice()", strmsgText);
				formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
				     //formBean.setStrMsgString("Please Check! Patient Must Be Admitted Patient");
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			}
		}
	}
	
	public static void initPatientAdmission_BS(NewBornBabyTransFB formBean)
	{
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		NewBornBabyTransVO voObj= null;
		NewBornBabyTransBO bo= null;
		HisUtil util = null;
		String temp="";
		try
		 {
			icu   =  new IpdConfigUtil(formBean.getStrHospCode());
			voObj =  new NewBornBabyTransVO();
			bo    =  new NewBornBabyTransBO();
			util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			String dept=icu.getStrNewBornBabyDefaultDept(); //vo.getStrMotherDeptCode();
			formBean.setStrDeptNameNewBorn(dept);
			voObj.setStrDeptNameNewBorn(dept);
			bo.setPatientDtl(voObj);
			if(voObj.getStrNumberOfChildrenBorn()==null)
            	voObj.setStrNumberOfChildrenBorn("0");
            if(voObj.getStrMaxBabyAllowed()==null)
            	voObj.setStrMaxBabyAllowed("9");
            if(icu.getStrMaxNoOfBabyMotherCanBorn()==null)
            {
            	formBean.setStrCrNo("");
				formBean.setStrMsgString("Configuration for Maximum No.of Baby Mother can born is not set. Please set from Inpatient Config Master.");
				throw new Exception("");
            }
			if(voObj.getWrsAdmissionTypeValues()!=null && voObj.getWrsAdmissionTypeValues().size()>0){
				formBean.setStrAdmissionTypeValues(util.getOptionValue(voObj.getWrsAdmissionTypeValues(), voObj.getStrAdmissionType(),"0^Select Value", false));
			}else{
				formBean.setStrAdmissionTypeValues("<option value='0'>Select Value</option>");				
			}
			if(voObj.getWrsReliefFundValues()!=null && voObj.getWrsReliefFundValues().size()>0){
				formBean.setStrReliefFundValues(util.getOptionValue(voObj.getWrsReliefFundValues(), voObj.getStrReliefFund(),"0^Select Value", false));
			}else{
				formBean.setStrAdmissionTypeValues("<option value='0'>Select Value</option>");				
			}
			
			//System.out.println("icu.getStrMaxNoOfBabyMotherCanBorn()"+icu.getStrMaxNoOfBabyMotherCanBorn()+"voObj.getStrMaxBabyAllowed()"+voObj.getStrMaxBabyAllowed());
			if(voObj.getStrMsgType().equals("6")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already gave birth to "
						+(Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())-Integer.parseInt(voObj.getStrMaxBabyAllowed()))+" " +
								"Babies no more Baby is Allowed.");
				throw new Exception("");
			}else if(voObj.getStrMsgType().equals("7")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient age is too small for Baby Born./Baby not Allowed");
				throw new Exception("");
			}
			else if(voObj.getStrMsgType().equals("4")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Age is too Small to be a Mother.");
		        throw new Exception("");
			}else if(voObj.getStrMsgType().equals("8")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has not been accepted.");
				throw new Exception("");
			}
		 	else if(voObj.getStrMsgType().equals("9")){
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is not Admitted.");
				throw new Exception("");
			}
			if (Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) == ((Integer
					.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn()))-(Integer.parseInt(voObj.getStrMaxBabyAllowed()))) && Integer.parseInt(voObj.getStrNumberOfChildrenBorn())>0) {
				formBean.setStrCrNo("");
				formBean
						.setStrMsgString("This Patient has already given birth to "
								+ voObj.getStrNumberOfChildrenBorn()
								+ " babies.");
				voObj.setStrMsgType("10");
				throw new Exception("");

			}
			int maxBaby = Integer.parseInt(voObj.getStrMaxBabyAllowed());
			formBean.setStrMaxBabyAllowed(Integer.toString(maxBaby));
			int maxBabyInConfig = Integer.parseInt(icu
					.getStrMaxNoOfBabyMotherCanBorn());
			if (maxBaby < maxBabyInConfig) {
				formBean.setStrIsGivenBirth("1");
			}
			formBean.setStrNumberOfChildrenBorn(voObj
					.getStrNumberOfChildrenBorn());
			formBean.setStrOnlineOrNot(icu.getStrNewBornBabyProcessType());
			String strAdmitted = IpdTransConfig.getOpdCode();
			
			
			//System.out.println(voObj.getStrValidCrNo());
			//System.out.println(voObj.getStrGenderCode());
			//System.out.println(IpdTransConfig.getFemaleCode());
			
			if(voObj.getStrValidCrNo().equals("1")||!(voObj.getStrGenderCode().equals(IpdTransConfig.getFemaleCode())))
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No./Data not found");
			}
			else if((voObj.getStrPatStatusCode().equals(strAdmitted)))
			{
				formBean.setStrCrNo("");
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrMsgString("Patient is in OPD");
			}
			else
			{
			
			temp = util.getOptionValue(voObj.getDepartWS(), dept,"0^Select Value", false);
			formBean.setStrDeptValue(temp);
			temp = util.getOptionValue(voObj.getUnitWS(), "","0^Select Value", false);
			formBean.setStrUnitValue(temp);
			formBean.setStrTreatmentCategVal(temp);
			/*temp = util.getOptionValue(voObj.getConsWS(), "0","0^Select Value", false);
			formBean.setStrConsValue(temp);*/
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			formBean.setStrAdmissionCharge(ipdConfig.getStrNewBornBabyAdmissionCharge());
			formBean.setStrNewBornRegistrationCharge(ipdConfig.getStrNewBornBabyRegistrationCharge());
			formBean.setStrAdmissionAdvance(ipdConfig.getStrAdvanceAmountNewBornBaby());
			formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
			formBean.setStrNewBornRegistrationChargeVal(voObj.getStrNewBornRegistrationChargeVal());
			formBean.setStrAddressModi(NewBornBabyTransHLP.getPatientDetailModi_BS(voObj));
			formBean.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
			strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
			formBean.setOccupationDetailValues(strOccDtlsview);
			formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
			formBean.setStrDeptUnitName(voObj.getStrMotherDeptName()+"/"+voObj.getStrMotherUnitName());
			formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
			formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
			formBean.setStrMotherName(voObj.getStrMotherName());
			formBean.setStrMotherNationality(voObj.getStrMotherNationality());
			formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
			formBean.setStrMotherReligion(voObj.getStrMotherReligion());
			formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
			formBean.setStrMotherDeptCode(voObj.getStrMotherDeptCode());
			formBean.setStrMotherUnitCode(voObj.getStrMotherUnitCode());
			formBean.setStrMotherWardCode(voObj.getStrMotherWardCode());
			formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
			formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
			formBean.setStrMotherRoomTypeCode(voObj.getStrMotherRoomTypeCode());
			formBean.setStrMotherBedTypeTypeCode(voObj.getStrMotherBedTypeTypeCode());
			formBean.setStrMotherWardName(voObj.getStrMotherWardName());
			formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
			formBean.setStrWardName(voObj.getStrMotherWardName());
			formBean.setStrRoom(voObj.getStrRoom());
			formBean.setStrCrNo(voObj.getStrCrNo());
			formBean.setStrRoomCode(voObj.getStrRoomCode());
			formBean.setStrBedCode(voObj.getStrBedCode());
			formBean.setStrPatCatCode(voObj.getStrPatCatCode());
			formBean.setStrConsultantCode(voObj.getStrConsultantCode());
			formBean.setStrConsultantName(voObj.getStrConsultantName());
			formBean.setStrRegistrationChargeHidden(voObj.getStrNewBornRegistrationChargeVal());
			formBean.setStrMotherDeptName(voObj.getStrMotherDeptName());
			formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
			formBean.setStrAdmissionChargeHidden(voObj.getStrAdmissionChargeValue());
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrIsBedSharable(voObj.getStrIsBedSharable());
			formBean.setSetStrMotherCatgrp(voObj.getSetStrMotherCatgrp());
			formBean.setStrMotherRoomCode(voObj.getStrMotherRoomCode());
			formBean.setStrCtDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrMothAdmDate(voObj.getStrMothAdmDate());
			//formBean.setStrDistrictName("0");
			formBean.setPrintMode("1");
				if(voObj.getStrMsgType().equals("1"))
					throw new Exception(voObj.getStrMsgString());
			}
			if(icu.getStrNewBornBabyProcessType().equals("1")) //online case
				formBean.setStrOnlineBabyList(NewBornBabyTransHLP.onlineBabyBornEntery(voObj));

			if(voObj.getStrMsgType().equals("1"))
				throw new Exception(voObj.getStrMsgString());

		} /*catch (SQLException e) {

			formBean.setStrMsgString("No Data in OCC Table");
			formBean.setStrMsgType("1");

			new HisException("Patient Admission",
					"PatientAdmissionDATA.initPatientAdmission()->", e
							.getMessage());

		} */catch (Exception e) {
			e.printStackTrace();
			if((!voObj.getStrMsgType().equals("6"))&&(!voObj.getStrMsgType().equals("7"))&&(!voObj.getStrMsgType().equals("8"))&&(!voObj.getStrMsgType().equals("9"))&&(!voObj.getStrMsgType().equals("10"))&& (!voObj.getStrMsgType().equals("4"))){
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->initAdmissionAdvice()", strmsgText);
				formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
				     //formBean.setStrMsgString("Please Check! Patient Must Be Admitted Patient");
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			}
		}
	}
	
	public static void initPatAdmSameBedAsMom(NewBornBabyTransFB formBean) {
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		NewBornBabyTransVO voObj = null;
		NewBornBabyTransBO bo = null;
		HisUtil util = null;
		String temp = "";
		try {
			icu = new IpdConfigUtil(formBean.getStrHospCode());
			voObj = new NewBornBabyTransVO();
			bo = new NewBornBabyTransBO();
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			bo.setPatientDtl(voObj);
			if(voObj.getStrNumberOfChildrenBorn()==null)
            	voObj.setStrNumberOfChildrenBorn("0");
            if(voObj.getStrMaxBabyAllowed()==null)
            	voObj.setStrMaxBabyAllowed("9");
            if(icu.getStrMaxNoOfBabyMotherCanBorn()==null)
            {
            	formBean.setStrCrNo("");
				formBean.setStrMsgString("Configuration for Maximum No.of Baby Mother can born is not set. Please set from Inpatient Config Master.");
				throw new Exception("");
            }
			if (voObj.getStrMsgType().equals("6")) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already given born to "
						+ (Integer.parseInt(icu
								.getStrMaxNoOfBabyMotherCanBorn()) - Integer
								.parseInt(voObj.getStrMaxBabyAllowed())) + " "
						+ "Babies no more Born is Allowed.");
				throw new Exception("");
			} else if (voObj.getStrMsgType().equals("7")) {
				formBean.setStrCrNo("");
				formBean
						.setStrMsgString("Patient age is too small for Baby Born./Baby not Allowed");
				throw new Exception("");
			} else if (voObj.getStrMsgType().equals("8")) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has not been accepted.");
				throw new Exception("");
			} else if (voObj.getStrMsgType().equals("9")) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is not Admitted.");
				throw new Exception("");
			}
			if ((Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())
					- Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) >= Integer
					.parseInt(voObj.getStrMaxBabyAllowed()))
					&& Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) > 0) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already given born to "
						+ voObj.getStrNumberOfChildrenBorn() + " babies.");
				voObj.setStrMsgType("10");
				throw new Exception("");
			}
			int maxBaby = Integer.parseInt(voObj.getStrMaxBabyAllowed());
			formBean.setStrMaxBabyAllowed(Integer.toString(maxBaby));
			int maxBabyInConfig = Integer.parseInt(icu
					.getStrMaxNoOfBabyMotherCanBorn());
			if (maxBaby < maxBabyInConfig) {
				formBean.setStrIsGivenBirth("1");
			}
			formBean.setStrNumberOfChildrenBorn(voObj
					.getStrNumberOfChildrenBorn());
			formBean.setStrOnlineOrNot(icu.getStrNewBornBabyProcessType());
			String strAdmitted = IpdTransConfig.getOpdCode();
			if (voObj.getStrValidCrNo().equals("1")
					|| !(voObj.getStrGenderCode().equals(IpdTransConfig
							.getFemaleCode()))) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No./Data not found");
			} else if ((voObj.getStrPatStatusCode().equals(strAdmitted))) {
				formBean.setStrCrNo("");
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrMsgString("Patient is in OPD");
			} else {
				util = new HisUtil("New Born Baby Trans",
						"NewBornBabyTransDATA");
				temp = util.getOptionValue(voObj.getDepartWS(), voObj
						.getStrMotherDeptCode(), "0^Select Value", false);
				formBean.setStrDeptValue(temp);
				temp = util.getOptionValue(voObj.getUnitWS(), voObj
						.getStrMotherUnitCode(), "0^Select Value", false);
				formBean.setStrUnitValue(temp);

				formBean.setStrTreatmentCategVal(temp);
				IpdConfigUtil ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
				formBean.setStrAdmissionCharge(ipdConfig
						.getStrNewBornBabyAdmissionCharge());
				formBean.setStrNewBornRegistrationCharge(ipdConfig
						.getStrNewBornBabyRegistrationCharge());
				formBean.setStrAdmissionAdvance(ipdConfig
						.getStrAdvanceAmountNewBornBaby());
				formBean.setStrAdmissionChargeValue(voObj
						.getStrAdmissionChargeValue());
				formBean.setStrNewBornRegistrationChargeVal(voObj
						.getStrNewBornRegistrationChargeVal());
				voObj.setStrCurrentDateTime(now());
				formBean.setStrAddressModi(NewBornBabyTransHLP
						.getPatientDetailModi(voObj));
				formBean.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
				strOccDtlsview = HLPOccupationDetails.getOccupationDetails(
						voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu
								.getStaffCategory(), voObj.getStrHospCode());
				formBean.setOccupationDetailValues(strOccDtlsview);
				formBean.setStrTreatmentCategoryName(voObj
						.getStrTreatmentCategoryName());
				formBean.setStrDeptUnitName(voObj.getStrMotherDeptName() + "/"
						+ voObj.getStrMotherUnitName());
				formBean.setStrMotherAdmissionNo(voObj
						.getStrMotherAdmissionNo());
				formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
				formBean.setStrMotherName(voObj.getStrMotherName());
				formBean.setStrMotherNationality(voObj
						.getStrMotherNationality());
				formBean.setStrMotherNationalityCode(voObj
						.getStrMotherNationalityCode());
				formBean.setStrMotherReligion(voObj.getStrMotherReligion());
				formBean.setStrMotherReligionCode(voObj
						.getStrMotherReligionCode());
				formBean.setStrMotherDeptCode(voObj.getStrMotherDeptCode());
				formBean.setStrMotherUnitCode(voObj.getStrMotherUnitCode());
				formBean.setStrMotherWardCode(voObj.getStrMotherWardCode());
				formBean.setStrMotherWardTypeCode(voObj
						.getStrMotherWardTypeCode());
				formBean.setStrMotherWardTypeCode(voObj
						.getStrMotherWardTypeCode());
				formBean.setStrMotherRoomTypeCode(voObj
						.getStrMotherRoomTypeCode());
				formBean.setStrMotherBedTypeTypeCode(voObj
						.getStrMotherBedTypeTypeCode());
				formBean.setStrMotherWardName(voObj.getStrMotherWardName());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrWardName(voObj.getStrMotherWardName());
				formBean.setStrRoom(voObj.getStrRoom());
				formBean.setStrCrNo(voObj.getStrCrNo());
				formBean.setStrRoomCode(voObj.getStrRoomCode());
				formBean.setStrBedCode(voObj.getStrBedCode());
				formBean.setStrPatCatCode(voObj.getStrPatCatCode());
				formBean.setStrConsultantCode(voObj.getStrConsultantCode());
				formBean.setStrConsultantName(voObj.getStrConsultantName());
				formBean.setStrRegistrationChargeHidden(voObj
						.getStrNewBornRegistrationChargeVal());
				formBean.setStrMotherDeptName(voObj.getStrMotherDeptName());
				formBean.setStrTreatmentCategoryCode(voObj
						.getStrTreatmentCategoryCode());
				formBean.setStrAdmissionChargeHidden(voObj
						.getStrAdmissionChargeValue());
				formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());

				if (voObj.getStrMsgType().equals("1"))
					throw new Exception(voObj.getStrMsgString());
			}
			if (icu.getStrNewBornBabyProcessType().equals("1")) // online case
				formBean.setStrOnlineBabyList(NewBornBabyTransHLP
						.onlineBabyBornEntery(voObj));

			if (voObj.getStrMsgType().equals("1"))
				throw new Exception(voObj.getStrMsgString());

		} catch (Exception e) {
			if ((!voObj.getStrMsgType().equals("6"))
					&& (!voObj.getStrMsgType().equals("7"))
					&& (!voObj.getStrMsgType().equals("8"))
					&& (!voObj.getStrMsgType().equals("9"))
					&& (!voObj.getStrMsgType().equals("10"))) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("ADT",
						"NewBornBabyTransDATA->initAdmissionAdvice()",
						strmsgText);
				formBean
						.setStrMsgString("Application Error [ERROR ID:"
								+ eObj.getErrorID()
								+ "],Contact System Administrator!");
				eObj = null;
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			}
		}
	}
	public static void initPatAdmSameBedAsMom_BS(NewBornBabyTransFB formBean) {
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		NewBornBabyTransVO voObj = null;
		NewBornBabyTransBO bo = null;
		HisUtil util = null;
		String temp = "";
		try {
			icu = new IpdConfigUtil(formBean.getStrHospCode());
			voObj = new NewBornBabyTransVO();
			bo = new NewBornBabyTransBO();
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			bo.setPatientDtl(voObj);
			if(voObj.getStrNumberOfChildrenBorn()==null)
            	voObj.setStrNumberOfChildrenBorn("0");
            if(voObj.getStrMaxBabyAllowed()==null)
            	voObj.setStrMaxBabyAllowed("9");
            if(icu.getStrMaxNoOfBabyMotherCanBorn()==null)
            {
            	formBean.setStrCrNo("");
				formBean.setStrMsgString("Configuration for Maximum No.of Baby Mother can born is not set. Please set from Inpatient Config Master.");
				throw new Exception("");
            }
			if (voObj.getStrMsgType().equals("6")) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already given born to "
						+ (Integer.parseInt(icu
								.getStrMaxNoOfBabyMotherCanBorn()) - Integer
								.parseInt(voObj.getStrMaxBabyAllowed())) + " "
						+ "Babies no more Born is Allowed.");
				throw new Exception("");
			} else if (voObj.getStrMsgType().equals("7")) {
				formBean.setStrCrNo("");
				formBean
						.setStrMsgString("Patient age is too small for Baby Born./Baby not Allowed");
				throw new Exception("");
			} else if (voObj.getStrMsgType().equals("8")) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has not been accepted.");
				throw new Exception("");
			} else if (voObj.getStrMsgType().equals("9")) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is not Admitted.");
				throw new Exception("");
			}
			if ((Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())
					- Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) >= Integer
					.parseInt(voObj.getStrMaxBabyAllowed()))
					&& Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) > 0) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already given born to "
						+ voObj.getStrNumberOfChildrenBorn() + " babies.");
				voObj.setStrMsgType("10");
				throw new Exception("");
			}
			int maxBaby = Integer.parseInt(voObj.getStrMaxBabyAllowed());
			formBean.setStrMaxBabyAllowed(Integer.toString(maxBaby));
			int maxBabyInConfig = Integer.parseInt(icu
					.getStrMaxNoOfBabyMotherCanBorn());
			if (maxBaby < maxBabyInConfig) {
				formBean.setStrIsGivenBirth("1");
			}
			formBean.setStrNumberOfChildrenBorn(voObj
					.getStrNumberOfChildrenBorn());
			formBean.setStrOnlineOrNot(icu.getStrNewBornBabyProcessType());
			String strAdmitted = IpdTransConfig.getOpdCode();
			if (voObj.getStrValidCrNo().equals("1")
					|| !(voObj.getStrGenderCode().equals(IpdTransConfig
							.getFemaleCode()))) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No./Data not found");
			} else if ((voObj.getStrPatStatusCode().equals(strAdmitted))) {
				formBean.setStrCrNo("");
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrMsgString("Patient is in OPD");
			} else {
				util = new HisUtil("New Born Baby Trans",
						"NewBornBabyTransDATA");
				temp = util.getOptionValue(voObj.getDepartWS(), voObj
						.getStrMotherDeptCode(), "0^Select Value", false);
				formBean.setStrDeptValue(temp);
				temp = util.getOptionValue(voObj.getUnitWS(), voObj
						.getStrMotherUnitCode(), "0^Select Value", false);
				formBean.setStrUnitValue(temp);

				formBean.setStrTreatmentCategVal(temp);
				IpdConfigUtil ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
				formBean.setStrAdmissionCharge(ipdConfig
						.getStrNewBornBabyAdmissionCharge());
				formBean.setStrNewBornRegistrationCharge(ipdConfig
						.getStrNewBornBabyRegistrationCharge());
				formBean.setStrAdmissionAdvance(ipdConfig
						.getStrAdvanceAmountNewBornBaby());
				formBean.setStrAdmissionChargeValue(voObj
						.getStrAdmissionChargeValue());
				formBean.setStrNewBornRegistrationChargeVal(voObj
						.getStrNewBornRegistrationChargeVal());
				voObj.setStrCurrentDateTime(now());
				formBean.setStrAddressModi(NewBornBabyTransHLP
						.getPatientDetailModi(voObj));
				formBean.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
				strOccDtlsview = HLPOccupationDetails.getOccupationDetails(
						voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu
								.getStaffCategory(), voObj.getStrHospCode());
				formBean.setOccupationDetailValues(strOccDtlsview);
				formBean.setStrTreatmentCategoryName(voObj
						.getStrTreatmentCategoryName());
				formBean.setStrDeptUnitName(voObj.getStrMotherDeptName() + "/"
						+ voObj.getStrMotherUnitName());
				formBean.setStrMotherAdmissionNo(voObj
						.getStrMotherAdmissionNo());
				formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
				formBean.setStrMotherName(voObj.getStrMotherName());
				formBean.setStrMotherNationality(voObj
						.getStrMotherNationality());
				formBean.setStrMotherNationalityCode(voObj
						.getStrMotherNationalityCode());
				formBean.setStrMotherReligion(voObj.getStrMotherReligion());
				formBean.setStrMotherReligionCode(voObj
						.getStrMotherReligionCode());
				formBean.setStrMotherDeptCode(voObj.getStrMotherDeptCode());
				formBean.setStrMotherUnitCode(voObj.getStrMotherUnitCode());
				formBean.setStrMotherWardCode(voObj.getStrMotherWardCode());
				formBean.setStrMotherWardTypeCode(voObj
						.getStrMotherWardTypeCode());
				formBean.setStrMotherWardTypeCode(voObj
						.getStrMotherWardTypeCode());
				formBean.setStrMotherRoomTypeCode(voObj
						.getStrMotherRoomTypeCode());
				formBean.setStrMotherBedTypeTypeCode(voObj
						.getStrMotherBedTypeTypeCode());
				formBean.setStrMotherWardName(voObj.getStrMotherWardName());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrWardName(voObj.getStrMotherWardName());
				formBean.setStrRoom(voObj.getStrRoom());
				formBean.setStrCrNo(voObj.getStrCrNo());
				formBean.setStrRoomCode(voObj.getStrRoomCode());
				formBean.setStrBedCode(voObj.getStrBedCode());
				formBean.setStrPatCatCode(voObj.getStrPatCatCode());
				formBean.setStrConsultantCode(voObj.getStrConsultantCode());
				formBean.setStrConsultantName(voObj.getStrConsultantName());
				formBean.setStrRegistrationChargeHidden(voObj
						.getStrNewBornRegistrationChargeVal());
				formBean.setStrMotherDeptName(voObj.getStrMotherDeptName());
				formBean.setStrTreatmentCategoryCode(voObj
						.getStrTreatmentCategoryCode());
				formBean.setStrAdmissionChargeHidden(voObj
						.getStrAdmissionChargeValue());
				formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());

				if (voObj.getStrMsgType().equals("1"))
					throw new Exception(voObj.getStrMsgString());
			}
			if (icu.getStrNewBornBabyProcessType().equals("1")) // online case
				formBean.setStrOnlineBabyList(NewBornBabyTransHLP
						.onlineBabyBornEntery(voObj));

			if (voObj.getStrMsgType().equals("1"))
				throw new Exception(voObj.getStrMsgString());

		} catch (Exception e) {
			if ((!voObj.getStrMsgType().equals("6"))
					&& (!voObj.getStrMsgType().equals("7"))
					&& (!voObj.getStrMsgType().equals("8"))
					&& (!voObj.getStrMsgType().equals("9"))
					&& (!voObj.getStrMsgType().equals("10"))) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("ADT",
						"NewBornBabyTransDATA->initAdmissionAdvice()",
						strmsgText);
				formBean
						.setStrMsgString("Application Error [ERROR ID:"
								+ eObj.getErrorID()
								+ "],Contact System Administrator!");
				eObj = null;
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			}
		}
	}
	
	public static void matchWardRoomCriteria(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		String isMatch="";
		try {
				NewBornBabyTransVO vo=new NewBornBabyTransVO();
				NewBornBabyTransBO bo=new NewBornBabyTransBO();
				vo.setStrWardCode(request.getParameter("wardCode"));
				vo.setStrRoomCode(request.getParameter("roomNo"));
				vo.setStrHospCode(formBean.getStrHospCode());
				vo.setStrSexCode(request.getParameter("sexCode"));
				vo.setStrTreatmentCategoryCode(request.getParameter("strTreatment"));
				vo.setStrDobTime(request.getParameter("dob"));
				
				if(vo.getStrSexCode().equals("F"))
					vo.setStrSexCode("2");
				else if(vo.getStrSexCode().equals("M"))
					vo.setStrSexCode("1");
				else
					vo.setStrSexCode("3");
				
				bo.matchWardRoomCriteria(vo);
				formBean.setStrIsWardRoomCriteriaMatch(vo.getStrIsWardRoomCriteriaMatch());
				isMatch=formBean.getStrIsWardRoomCriteriaMatch();
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(isMatch);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				
		}
		catch (Exception e) 
		{
			String strmsgText = e.getMessage();
			e.printStackTrace();
			response.setHeader("Cache-Control", "no-cache");
			try 
			{
				HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->matchWardRoomCriteria()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				eObj = null;
			}
			catch (Exception e1) 
			{

			//System.out.println("Inside ::::"+e1.getMessage());

			}
		}
	}
	/**
	 * This function is used to initial parameters for bed details pop up window
	 * @param formBean
	 * @param request
	 */
	public static void initBedStatus(NewBornBabyTransFB formBean,HttpServletRequest request) {
		String temp="";

		HisUtil util = null;
		try {
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
			vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));
			vo.setStrCrNo(request.getParameter("crNo"));
			vo.setStrDeptName(request.getParameter("deptName"));
			vo.setStrRoomCode(request.getParameter("roomCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			
			if(vo.getStrSexCode().equals("F"))
				vo.setStrSexCode("2");
			else if(vo.getStrSexCode().equals("M"))
				vo.setStrSexCode("1");
			else
				vo.setStrSexCode("3");

			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategory"));
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			formBean.setStrTreatmentCategoryCode(request.getParameter("treatmentCategory"));
			formBean.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			bo.setBedStatusDtl(vo);
			formBean.setStrDepartmentName(vo.getStrDeptName());
			formBean.setStrDeptCode(vo.getStrDeptCode());
			util = new HisUtil("New Born Baby Trans",
			"NewBornBabyTransDATA");
				temp=util.getOptionValue(vo.getWardTypeWS(), vo.getStrWardTypeCode(),
					"0^Select Value", false);
				formBean.setStrwardType(temp);
				temp=util.getOptionValue(vo.getWardWS(), vo.getStrWardCode(),
						"0^Select Value", false);
					formBean.setStrWard(temp);
				//	System.out.println("temp"+temp);
				temp=util.getOptionValue(vo.getRoomTypeWS(), vo.getStrRoomTypeCode(),
					"0^Select Value", false);
				formBean.setStrRoomType(temp);
				temp=util.getOptionValue(vo.getRoomWs(),vo.getStrRoomCode(),
					"0^Select Value", false);
				formBean.setStrRoom(temp);
				temp=util.getOptionValue(vo.getBedTypeWS(),vo.getStrBedTypeCode(),
					"0^Select Value", false);
				formBean.setStrBedType(temp);

		} catch (Exception e) {
			e.printStackTrace();
			String strmsgText = e.getMessage();
			 HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->initBedStatus()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
	}
	/**
	 * This function is used to set initial parameters to search beds in particular ward
	 * @param formBean
	 * @param request
	 * @param response
	 */
	public static void initBedDetails(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response) {


		try {
				NewBornBabyTransVO vo=new NewBornBabyTransVO();
				NewBornBabyTransBO bo=new NewBornBabyTransBO();
				vo.setStrWardCode(request.getParameter("wardCode"));
				vo.setStrRoom(request.getParameter("roomCode"));
				vo.setStrBedTypeCode(request.getParameter("bedTypeCode"));
				vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
				vo.setStrHospCode(formBean.getStrHospCode());
				bo.setBedDetails(vo);
				String res=NewBornBabyTransHLP.getBedDetails(vo);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(res+"^"+vo.getStrMsApprovalFlag());
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}

		} catch (Exception e) {
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (Exception e1)
			{

			//System.out.println("Inside IInd Else::::"+e1.getMessage());

			}
		}
	}
/**
 * This function set initial parameters for ward combo when user change ward type on bed details pop up window
 * @param formBean
 * @param request
 * @param response
 */
	public static void initWardDetails(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
				NewBornBabyTransVO vo=new NewBornBabyTransVO();
				NewBornBabyTransBO bo=new NewBornBabyTransBO();
				String temp="";
				vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
				vo.setStrDeptCode(request.getParameter("deptCode"));
				vo.setStrHospCode(formBean.getStrHospCode());
				vo.setStrCrNo(request.getParameter("crNo"));
				vo.setStrDeptName(request.getParameter("deptName"));
				vo.setStrRoomCode(request.getParameter("roomCode"));
				vo.setStrSexCode(request.getParameter("sexCode"));
				vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategory"));
				vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));

				bo.setWardDetails(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
				temp=util.getOptionValue(vo.getWardWS(), "","0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
		}
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (Exception e1)
			{

			//System.out.println("Inside IInd Else::::"+e1.getMessage());

			}
		}
	}
	public static void initWardDetailsSh(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
				NewBornBabyTransVO vo=new NewBornBabyTransVO();
				NewBornBabyTransBO bo=new NewBornBabyTransBO();
				String temp="";
				vo.setStrWardTypeCode(request.getParameter("wardTypeCode"));
				vo.setStrDeptCode(request.getParameter("deptCode"));
				vo.setStrHospCode(formBean.getStrHospCode());
				vo.setStrCrNo(request.getParameter("crNo"));
				vo.setStrDeptName(request.getParameter("deptName"));
				vo.setStrRoomCode(request.getParameter("roomCode"));
				vo.setStrSexCode(request.getParameter("sexCode"));
				vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategory"));
				vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
				vo.setStrMotherWardCode(request.getParameter("mothward"));


				bo.setWardDetails(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
				//System.out.println("vo.getStrMotherWardCode()"+vo.getStrMotherWardCode());
				temp=util.getOptionValue(vo.getWardWS(), vo.getStrMotherWardCode(),"0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
		}
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
			try
			{
				HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
				response.getWriter().print(response1);
				eObj = null;
			}
			catch (Exception e1)
			{

			//System.out.println("Inside IInd Else::::"+e1.getMessage());

			}
		}
	}
/**
 * This function is used to set initial parameters to bring room details on the basis of room type code
 * @param formBean
 * @param request
 * @param response
 */
	public static void initRoomDetails(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response)
	{
		HisUtil util = null;
		try
		{
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			String temp="";
			vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("crNo"));

			bo.setRoomDetails(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			else
			{
				util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
				temp=util.getOptionValue(vo.getRoomWs(),"","0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
			}

	}
	catch (Exception e)
	{
		String strmsgText = e.getMessage();
		response.setHeader("Cache-Control", "no-cache");
		try
		{
			HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			response.getWriter().print(response1);
			eObj = null;
		}
		catch (Exception e1)
		{

		//System.out.println("Inside IInd Else::::"+e1.getMessage());

		}
	}
	}
	public static void initRoomDetailsSh(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response)
	{
		HisUtil util = null;
		try
		{
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			String temp="";
			vo.setStrRoomTypeCode(request.getParameter("roomTypeCode"));
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrTreatmentCategoryCode(request.getParameter("treatmentCategCode"));
			vo.setStrDeptUnitCode(request.getParameter("deptUnitCode"));
			vo.setStrAgeUnit(request.getParameter("ageCode"));
			vo.setStrSexCode(request.getParameter("sexCode"));
			vo.setStrAge(request.getParameter("age"));
			vo.setStrCrNo(request.getParameter("crNo"));
			vo.setStrMotherRoomCode(request.getParameter("mothroom"));

			bo.setRoomDetails(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			else
			{
				util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
				//System.out.println("vo.getStrMotherRoomCode()"+vo.getStrMotherRoomCode());
				temp=util.getOptionValue(vo.getRoomWs(),vo.getStrMotherRoomCode(),"0^Select Value", false);
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(temp);
			}

	}
	catch (Exception e)
	{
		String strmsgText = e.getMessage();
		response.setHeader("Cache-Control", "no-cache");
		try
		{
			HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			response.getWriter().print(response1);
			eObj = null;
		}
		catch (Exception e1)
		{

		//System.out.println("Inside IInd Else::::"+e1.getMessage());

		}
	}
	}
/**
 * This function invoke TransferObjectFactory.populateData() to transfer the values of all attributes of form bean into vo and the invoke bo insert method
 * @param formBean
 */
	public static void insert(NewBornBabyTransFB formBean,HttpServletRequest request)
	{
		try
		{
			NewBornBabyTransVO vo=(NewBornBabyTransVO) TransferObjectFactory.populateData("ipd.transactions.NewBornBabyTransVO", formBean);
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			HisPrinter _hisPrinter=null;
			String printHLP="";
			IpdConfigUtil ipd=new IpdConfigUtil(formBean.getStrHospCode());
			vo.setStrNoOfFreePass(ipd.getStrNoOfFreePass());
			vo.setStrNoOfFreePassValidity(ipd.getStrNewFreePassValidity());
			vo.setStrCurrentDate(now());
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrNumberOfChildrenBorn(formBean.getStrNumberOfChildrenBorn());
			vo.setStrCaseSheetNo(formBean.getStrCaseSheetNo());
			vo.setStrCity(formBean.getStrCity());
			vo.setStrCountryCode(formBean.getStrCountry());
			if((!vo.getStrCountryCode().equals("IND")))
			{
				vo.setStrStateCode("0");
				vo.setStrState("0");
				vo.setStrDistrictCode("0");
				vo.setStrDistrict(formBean.getStrDistrictName());

			}else{
				vo.setStrStateCode(formBean.getStrState());
			//	vo.setStrDistrictCode(vo.getStrDistrictCode());
				vo.setStrDistrict(formBean.getStrDistrictName());
				vo.setStrDistrictCode(formBean.getStrDistrict());
			}
			vo.setStrCountryName(formBean.getStrCountryName());
			
			vo.setStrPinCode(formBean.getStrPinCode());
			vo.setStrStateName(formBean.getStrStateName());
			vo.setStrMobileNo(formBean.getStrMobileNo());
			vo.setStrPhoneNo(formBean.getStrPhoneNo());
            //System.out.println("vo.setStrNumberOfChildrenBorn"+vo.getStrNumberOfChildrenBorn());
			bo.insert(vo);
			if(vo.getStrMsgType().equals("0"))
			{
				formBean.setStrSaveFlag("1");
				formBean.setStrCrNo(vo.getStrCrNo());
				formBean.setStrPatientCrNo(formBean.getStrCrNo());
				formBean.setStrAdmNo(vo.getStrAdmNo());
				formBean.setStrMsg("Patient is successfully admitted \t Admission No:"+vo.getStrAdmNo()+" and \t CR No:"+vo.getStrCrNo());

			}
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			else
			{
				/*StringBuilder sbAdd = new StringBuilder("");
				if(vo.getStrHouseNo()!=null && !vo.getStrHouseNo().trim().equals(""))		sbAdd.append(vo.getStrHouseNo());
				if(vo.getStrStreet()!=null && !vo.getStrStreet().trim().equals(""))		{	sbAdd.append(", ");		sbAdd.append(vo.getStrStreet());	}
				if(vo.getStrCity()!=null && !vo.getStrCity().trim().equals(""))			{	sbAdd.append(", ");		sbAdd.append(vo.getStrCity());	}
				if(vo.getStrDistrict()!=null && !vo.getStrDistrict().trim().equals(""))	{	sbAdd.append(", ");		sbAdd.append(vo.getStrDistrict());		}
					//Now available here only Code is available
				if(vo.getStrStateName()!=null && !vo.getStrStateName().trim().equals(""))		{	sbAdd.append(", ");		sbAdd.append(vo.getStrStateName());	}
				if(vo.getStrCountryName()!=null && !vo.getStrCountryName().trim().equals(""))	{	sbAdd.append(", ");		sbAdd.append(vo.getStrCountryName());	}
				if(vo.getStrPinCode()!=null && !vo.getStrPinCode().trim().equals(""))			{	sbAdd.append(" - ");	sbAdd.append(vo.getStrPinCode());		}
				if(sbAdd.charAt(0) == ',') sbAdd.delete(0, 1);
				vo.setStrAddress(sbAdd.toString());
					printHLP=ADTPrintingTransHLP.NewBorn(vo,request);
					System.out.println("DATA To Be Printed : - -\n"+printHLP);
					String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH") + "/" + "ADT"+request.getSession().getAttribute("SEATID").toString()+".dat";
					formBean.setFilePath(fileName);
					request.setAttribute("filePath", fileName);
					formBean.setIsOpenPopUp("1");

			//	System.out.println("printHLPNewBorn->\n"+printHLP);
				try{
					_hisPrinter.printFile(printHLP, "ADT", request);
				}catch(Exception _Err){
					//formBean.setStrMsgString("Application Error [Either Printer is not working or not connected]");
				}*/
			}
		}
		catch(Exception e)
		{

			String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->insert()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
		}
	}
	public static void initUnitDtl(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response)
	{
	NewBornBabyTransVO vo=null;
	NewBornBabyTransBO bo=null;
	HisUtil util = null;
	String temp;
	try
	{
		vo=new NewBornBabyTransVO();
		bo=new NewBornBabyTransBO();
		//vo.setStrMotherDeptCode(request.getParameter("deptCode"));
		vo.setStrDeptNameNewBorn(request.getParameter("deptCode"));
		vo.setStrHospCode(formBean.getStrHospCode());
		bo.setUnitValue(vo);
		util = new HisUtil("New Born Baby Trans","NewBornBabyTransDATA");
		temp = util.getOptionValue(vo.getUnitWS(), "0","0^Select Value", false);
		response.getWriter().write(temp);
	}
	catch(Exception e)
	{
		   String strmsgText = e.getMessage();
		   HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->initUnitDtl()", strmsgText);
		   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		     eObj = null;
	}
	}
	public static void getAdvanceAmount(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			String temp="";
			vo.setStrMotherWardCode(request.getParameter("wardCode"));
			vo.setStrTreatmentCategoryCode(request.getParameter("teatmentCategory"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrUnitNewBorn(request.getParameter("unit_code"));
			
			bo.getAdvanceAmount(vo);
			temp=vo.getStrAdmissionChargeValue()+"^"+vo.getStrAdvanceAmount()+"^"+vo.getStrNewBornRegistrationChargeVal();
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			response.getWriter().print(temp);
		}
		catch (Exception e)
		{
			String strmsgText = e.getMessage();
			response.setHeader("Cache-Control", "no-cache");
		try
		{
			HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			response.getWriter().print(response1);
			eObj = null;
		}
		catch (Exception e1)
		{
		}
		}
	}
	public static void initPatAdmUpdate(NewBornBabyTransFB formBean) 
	{
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		NewBornBabyTransVO voObj = null;
		NewBornBabyTransBO bo = null;
		HisUtil util = null;
		String temp = "";

		try 
		{
			icu = new IpdConfigUtil(formBean.getStrHospCode());
			voObj = new NewBornBabyTransVO();
			bo = new NewBornBabyTransBO();
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());

			bo.setPatientDtlNewBornUpdateMother(voObj);
			if(voObj.getStrNumberOfChildrenBorn()==null)
            	voObj.setStrNumberOfChildrenBorn("0");
			if(voObj.getStrMaxBabyAllowed()==null)
            	voObj.setStrMaxBabyAllowed("9");
            if(icu.getStrMaxNoOfBabyMotherCanBorn()==null)
            {
            	formBean.setStrCrNo("");
				formBean.setStrMsgString("Configuration for Maximum No.of Baby Mother can born is not set. Please set from Inpatient Config Master.");
				throw new Exception("");
            }
			if (voObj.getStrMsgType().equals("6")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already given birth to "+ (Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn()) - Integer.parseInt(voObj.getStrMaxBabyAllowed())) + " "
						+ "No more baby birth is Allowed.");
				throw new Exception("");
			} 
			else if (voObj.getStrMsgType().equals("7")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is under age for Baby birth/Baby not Allowed");
				throw new Exception("");
			} 
			else if (voObj.getStrMsgType().equals("8")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has not been accepted.");
				throw new Exception("");
			} 
			else if (voObj.getStrMsgType().equals("9")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is not Admitted.");
				throw new Exception("");
			}
			if (Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) == Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient has already given birth to "+ voObj.getStrNumberOfChildrenBorn()+ " babies.");
				voObj.setStrMsgType("10");
				throw new Exception("");
			}
			
			int maxBaby = Integer.parseInt(voObj.getStrMaxBabyAllowed());
			formBean.setStrMaxBabyAllowed(Integer.toString(maxBaby));
			int maxBabyInConfig = Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn());
			
			if (maxBaby < maxBabyInConfig) 
			{
				formBean.setStrIsGivenBirth("1");
			}
			formBean.setStrNumberOfChildrenBorn(voObj.getStrNumberOfChildrenBorn());
			formBean.setStrOnlineOrNot(icu.getStrNewBornBabyProcessType());
			String strAdmitted = IpdTransConfig.getOpdCode();

			if (voObj.getStrValidCrNo().equals("1")|| !(voObj.getStrGenderCode().equals(IpdTransConfig.getFemaleCode()))) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No./Data not found");
			} 
			else if ((voObj.getStrPatStatusCode().equals(strAdmitted))) 
			{
				formBean.setStrCrNo("");
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrMsgString("Patient is in OPD");
			} 
			else 
			{
				String strDefDeptCode = icu.getStrNewBornBabyDefaultDept();
				if (strDefDeptCode == null)
					strDefDeptCode = "0";
				if (strDefDeptCode.equals(""))
					strDefDeptCode = "0";
				util = new HisUtil("IPD","NewBornBabyTransDATA");
				temp = util.getOptionValue(voObj.getDepartWS(), strDefDeptCode,"0^Select Value", false);
				formBean.setStrDeptValue(temp);
				temp = util.getOptionValue(voObj.getUnitWS(), "0","0^Select Value", false);
				formBean.setStrUnitValue(temp);


				formBean.setStrTreatmentCategVal(temp);
				IpdConfigUtil ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
				formBean.setStrAdmissionCharge(ipdConfig.getStrNewBornBabyAdmissionCharge());
				formBean.setStrNewBornRegistrationCharge(ipdConfig.getStrNewBornBabyRegistrationCharge());
				formBean.setStrAdmissionAdvance(ipdConfig.getStrAdvanceAmountNewBornBaby());
				formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
				formBean.setStrNewBornRegistrationChargeVal(voObj.getStrNewBornRegistrationChargeVal());
				voObj.setStrCurrentDateTime(now());
				formBean.setStrAddressModi(NewBornBabyTransHLP.getPatDtlModi(voObj));// changed..
				formBean.setStrAdmittedBabyDetails(NewBornBabyTransHLP.getAdmittedBabyDtlModi(voObj));// changed...
				formBean.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
				strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(), voObj.getStrHospCode());
				formBean.setOccupationDetailValues(strOccDtlsview);
				formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
				formBean.setStrDeptUnitName(voObj.getStrMotherDeptName() + "/"+ voObj.getStrMotherUnitName());
				formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
				formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
				formBean.setStrMotherName(voObj.getStrMotherName());
				formBean.setStrMotherNationality(voObj.getStrMotherNationality());
				formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
				formBean.setStrMotherReligion(voObj.getStrMotherReligion());
				formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
				formBean.setStrMotherDeptCode(voObj.getStrMotherDeptCode());
				formBean.setStrMotherUnitCode(voObj.getStrMotherUnitCode());
				formBean.setStrMotherWardCode(voObj.getStrMotherWardCode());
				formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
				formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
				formBean.setStrMotherRoomTypeCode(voObj.getStrMotherRoomTypeCode());
				formBean.setStrMotherBedTypeTypeCode(voObj.getStrMotherBedTypeTypeCode());
				formBean.setStrMotherWardName(voObj.getStrMotherWardName());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrWardName(voObj.getStrMotherWardName());
				formBean.setStrRoom(voObj.getStrRoom());
				formBean.setStrCrNo(voObj.getStrCrNo());
				formBean.setStrRoomCode(voObj.getStrRoomCode());
				formBean.setStrBedCode(voObj.getStrBedCode());
				formBean.setStrPatCatCode(voObj.getStrPatCatCode());
				formBean.setStrConsultantCode(voObj.getStrConsultantCode());
				formBean.setStrConsultantName(voObj.getStrConsultantName());
				formBean.setStrRegistrationChargeHidden(voObj.getStrNewBornRegistrationChargeVal());
				formBean.setStrMotherDeptName(voObj.getStrMotherDeptName());
				formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
				formBean.setStrAdmissionChargeHidden(voObj.getStrAdmissionChargeValue());
				formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());

				if (voObj.getStrMsgType().equals("1"))
					throw new Exception(voObj.getStrMsgString());
			}

			// if (icu.getStrNewBornBabyProcessType().equals("1")) // online
			// case
			// formBean.setStrOnlineBabyList(NewBornBabyTransHLP.onlineBabyBornEntery(voObj));

			if (voObj.getStrMsgType().equals("1"))
				throw new Exception(voObj.getStrMsgString());

		} 
		catch (Exception e) 
		{
			if (voObj.getStrMsgType().equalsIgnoreCase("0Baby")) 
			{
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("ADT","NewBornBabyTransDATA->initPatAdmUpdate()",strmsgText);
				formBean.setStrMsgString("Mother has not given birth to any baby,thus details cannot be updated.");
				eObj = null;
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
				return;
			}
			if ((!voObj.getStrMsgType().equals("6")) && (!voObj.getStrMsgType().equals("7")) && (!voObj.getStrMsgType().equals("8"))&& (!voObj.getStrMsgType().equals("9"))
					&& (!voObj.getStrMsgType().equals("10"))&& (!voObj.getStrMsgType().equals("0Baby"))) 
			{
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("ADT","NewBornBabyTransDATA->initPatAdmUpdate()",strmsgText);
				formBean.setStrMsgString("Application Error [ERROR ID:"+ eObj.getErrorID()+ "],Contact System Administrator!");
				eObj = null;
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			}
		}
	}
	
	public static void initPatAdmUpdate_BS(NewBornBabyTransFB formBean) 
	{
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		NewBornBabyTransVO voObj = null;
		NewBornBabyTransBO bo = null;
		HisUtil util = null;
		String temp = "";

		try 
		{
			icu = new IpdConfigUtil(formBean.getStrHospCode());
			voObj = new NewBornBabyTransVO();
			bo = new NewBornBabyTransBO();
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());

			bo.setPatientDtlNewBornUpdateMother(voObj);
			if(voObj.getStrNumberOfChildrenBorn()==null)
            	voObj.setStrNumberOfChildrenBorn("0");
			if(voObj.getStrMaxBabyAllowed()==null)
            	voObj.setStrMaxBabyAllowed("9");
            if(icu.getStrMaxNoOfBabyMotherCanBorn()==null)
            {
            	formBean.setStrCrNo("");
				formBean.setStrMsgString("Configuration for Maximum No.of Baby Mother can born is not set. Please set from Inpatient Config Master.");
				throw new Exception("");
            }
			if (voObj.getStrMsgType().equals("6")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient already given birth to "+ (Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn()) - Integer.parseInt(voObj.getStrMaxBabyAllowed())) + " "
						+ "No more baby birth is Allowed.");
				throw new Exception("");
			} 
			else if (voObj.getStrMsgType().equals("7")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is under age for Baby birth/Baby not Allowed");
				throw new Exception("");
			} 
			else if (voObj.getStrMsgType().equals("8")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient has not been accepted.");
				throw new Exception("");
			} 
			else if (voObj.getStrMsgType().equals("9")) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Patient is not Admitted.");
				throw new Exception("");
			}
			if (Integer.parseInt(voObj.getStrNumberOfChildrenBorn()) == Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())) 
			{
				formBean.setStrCrNo("");
				formBean.setStrMsgString("This Patient has already given birth to "+ voObj.getStrNumberOfChildrenBorn()+ " babies.");
				voObj.setStrMsgType("10");
				throw new Exception("");
			}
			
			int maxBaby = Integer.parseInt(voObj.getStrMaxBabyAllowed());
			formBean.setStrMaxBabyAllowed(Integer.toString(maxBaby));
			int maxBabyInConfig = Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn());
			
			if (maxBaby < maxBabyInConfig) 
			{
				formBean.setStrIsGivenBirth("1");
			}
			formBean.setStrNumberOfChildrenBorn(voObj.getStrNumberOfChildrenBorn());
			formBean.setStrOnlineOrNot(icu.getStrNewBornBabyProcessType());
			String strAdmitted = IpdTransConfig.getOpdCode();

			if (voObj.getStrValidCrNo().equals("1")|| !(voObj.getStrGenderCode().equals(IpdTransConfig.getFemaleCode()))) {
				formBean.setStrCrNo("");
				formBean.setStrMsgString("Invalid CR No./Data not found");
			} 
			else if ((voObj.getStrPatStatusCode().equals(strAdmitted))) 
			{
				formBean.setStrCrNo("");
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrMsgString("Patient is in OPD");
			} 
			else 
			{
				String strDefDeptCode = icu.getStrNewBornBabyDefaultDept();
				if (strDefDeptCode == null)
					strDefDeptCode = "0";
				if (strDefDeptCode.equals(""))
					strDefDeptCode = "0";
				util = new HisUtil("IPD","NewBornBabyTransDATA");
				temp = util.getOptionValue(voObj.getDepartWS(), strDefDeptCode,"0^Select Value", false);
				formBean.setStrDeptValue(temp);
				temp = util.getOptionValue(voObj.getUnitWS(), "0","0^Select Value", false);
				formBean.setStrUnitValue(temp);


				formBean.setStrTreatmentCategVal(temp);
				IpdConfigUtil ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
				formBean.setStrAdmissionCharge(ipdConfig.getStrNewBornBabyAdmissionCharge());
				formBean.setStrNewBornRegistrationCharge(ipdConfig.getStrNewBornBabyRegistrationCharge());
				formBean.setStrAdmissionAdvance(ipdConfig.getStrAdvanceAmountNewBornBaby());
				formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
				formBean.setStrNewBornRegistrationChargeVal(voObj.getStrNewBornRegistrationChargeVal());
				voObj.setStrCurrentDateTime(now());
				formBean.setStrAddressModi(NewBornBabyTransHLP.getPatDtlModi_BS(voObj));// changed..
				formBean.setStrAdmittedBabyDetails(NewBornBabyTransHLP.getAdmittedBabyDtlModi(voObj));// changed...
				formBean.setStrBillFlag(ipdConfig.getStrIntegrationBilling());
				strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(), voObj.getStrHospCode());
				formBean.setOccupationDetailValues(strOccDtlsview);
				formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
				formBean.setStrDeptUnitName(voObj.getStrMotherDeptName() + "/"+ voObj.getStrMotherUnitName());
				formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
				formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
				formBean.setStrMotherName(voObj.getStrMotherName());
				formBean.setStrMotherNationality(voObj.getStrMotherNationality());
				formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
				formBean.setStrMotherReligion(voObj.getStrMotherReligion());
				formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
				formBean.setStrMotherDeptCode(voObj.getStrMotherDeptCode());
				formBean.setStrMotherUnitCode(voObj.getStrMotherUnitCode());
				formBean.setStrMotherWardCode(voObj.getStrMotherWardCode());
				formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
				formBean.setStrMotherWardTypeCode(voObj.getStrMotherWardTypeCode());
				formBean.setStrMotherRoomTypeCode(voObj.getStrMotherRoomTypeCode());
				formBean.setStrMotherBedTypeTypeCode(voObj.getStrMotherBedTypeTypeCode());
				formBean.setStrMotherWardName(voObj.getStrMotherWardName());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrWardName(voObj.getStrMotherWardName());
				formBean.setStrRoom(voObj.getStrRoom());
				formBean.setStrCrNo(voObj.getStrCrNo());
				formBean.setStrRoomCode(voObj.getStrRoomCode());
				formBean.setStrBedCode(voObj.getStrBedCode());
				formBean.setStrPatCatCode(voObj.getStrPatCatCode());
				formBean.setStrConsultantCode(voObj.getStrConsultantCode());
				formBean.setStrConsultantName(voObj.getStrConsultantName());
				formBean.setStrRegistrationChargeHidden(voObj.getStrNewBornRegistrationChargeVal());
				formBean.setStrMotherDeptName(voObj.getStrMotherDeptName());
				formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
				formBean.setStrAdmissionChargeHidden(voObj.getStrAdmissionChargeValue());
				formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());

				if (voObj.getStrMsgType().equals("1"))
					throw new Exception(voObj.getStrMsgString());
			}

			// if (icu.getStrNewBornBabyProcessType().equals("1")) // online
			// case
			// formBean.setStrOnlineBabyList(NewBornBabyTransHLP.onlineBabyBornEntery(voObj));

			if (voObj.getStrMsgType().equals("1"))
				throw new Exception(voObj.getStrMsgString());

		} 
		catch (Exception e) 
		{
			if (voObj.getStrMsgType().equalsIgnoreCase("0Baby")) 
			{
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("ADT","NewBornBabyTransDATA->initPatAdmUpdate()",strmsgText);
				formBean.setStrMsgString("Mother has not given birth to any baby,thus details cannot be updated.");
				eObj = null;
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
				return;
			}
			if ((!voObj.getStrMsgType().equals("6")) && (!voObj.getStrMsgType().equals("7")) && (!voObj.getStrMsgType().equals("8"))&& (!voObj.getStrMsgType().equals("9"))
					&& (!voObj.getStrMsgType().equals("10"))&& (!voObj.getStrMsgType().equals("0Baby"))) 
			{
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("ADT","NewBornBabyTransDATA->initPatAdmUpdate()",strmsgText);
				formBean.setStrMsgString("Application Error [ERROR ID:"+ eObj.getErrorID()+ "],Contact System Administrator!");
				eObj = null;
				formBean.setStrMsgType("1");
				formBean.setStrCrNo("");
			}
		}
	}

	
	public static void update(NewBornBabyTransFB formBean) {

		NewBornBabyTransVO vo = (NewBornBabyTransVO) TransferObjectFactory
				.populateData("ipd.transactions.NewBornBabyTransVO", formBean);
		NewBornBabyTransBO bo = new NewBornBabyTransBO();
		String printHLP = "";
		IpdConfigUtil ipd = new IpdConfigUtil(formBean.getStrHospCode());
		vo.setStrCurrentDate(now());
		bo.update(vo);

		try {
			vo = new NewBornBabyTransVO();

			if (vo.getStrMsgType().equals("1"))
				throw new Exception(vo.getStrMsgString());

			formBean.setStrMsg("Mother Delivery Details Succesfully Updated.");
		} catch (Exception e) {
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("ADT",
					"NewBornBabyTransDATA->initAdmissionAdvice()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID:"
					+ eObj.getErrorID() + "],Contact System Administrator!");
			eObj = null;
			formBean.setStrMsgType("1");
			formBean.setStrCrNo("");
		}
	}

	public static void initDistrict(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
			
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			String temp="";
			vo.setStrHospCode(formBean.getStrHospCode());
			
			
			vo.setStrStateCode(request.getParameter("stateCode"));
			bo.getDistrict(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			util = new HisUtil("New Born Baby Trans",
					"NewBornBabyTransDATA");
					
					
					temp=util.getOptionValue(vo.getDistrictWS(),vo.getStrDistrictCode(),"0^Select Value", false);
					//formBean.setStrDistrict(vo.getStrDistrict());
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(temp);	
				
				
			
		} catch (Exception e) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->initDistrict()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			   try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {}  
		}
}
	public static void getsharable(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response)
	{
		HisUtil util = null;
		try
		{
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			
			vo.setStrRoomCode(request.getParameter("roomCode"));
			vo.setStrWardCode(request.getParameter("wardCode"));
			vo.setStrHospCode(formBean.getStrHospCode());
			vo.setStrDeptUnitCode(request.getParameter("unitcode"));
			vo.setStrBedCode(request.getParameter("bedCode"));
			vo.setStrDeptCode(request.getParameter("deptCode"));

			bo.setsharable(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception();
			}
			else
			{
				
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(vo.getSharableCnt());
				System.out.println("SharableCnt"+vo.getSharableCnt());
			}

	}
	catch (Exception e)
	{
		String strmsgText = e.getMessage();
		response.setHeader("Cache-Control", "no-cache");
		try
		{
			HisException eObj = new HisException("ADT", "NewBornBabyTransDATA->initBedDetails()", strmsgText);
			String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			response.getWriter().print(response1);
			eObj = null;
		}
		catch (Exception e1)
		{

		//System.out.println("Inside IInd Else::::"+e1.getMessage());

		}
	}
	}
	public static void initCons(NewBornBabyTransFB formBean,HttpServletRequest request,HttpServletResponse response) {
		HisUtil util = null;
		try {
			
			NewBornBabyTransVO vo=new NewBornBabyTransVO();
			NewBornBabyTransBO bo=new NewBornBabyTransBO();
			String temp="";
			vo.setStrHospCode(formBean.getStrHospCode());
			
			
			vo.setStrUnitNewBorn(request.getParameter("unit_code"));
			bo.getCons(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			util = new HisUtil("New Born Baby Trans",
					"NewBornBabyTransDATA");
					
					
					temp=util.getOptionValue(vo.getConsWS(),"0","0^Select Value", false);
					//formBean.setStrDistrict(vo.getStrDistrict());
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(temp);	
				
				
			
		} catch (Exception e) {
				String strmsgText = e.getMessage();
				HisException eObj = new HisException("IPD", "NewBornBabyTransDATA->initCons()", strmsgText);
				String response1 = "ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator!";
			   try
			    {
			    	response.getWriter().print(response1);
			    	eObj=null;
			    }
			    catch(Exception e1)
			    {}  
		}
}
}