package ipd.transactions;

import ipd.HLPOccupationDetails;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PackAdmTransDATA {
	
	public static void initPatientAdmission(PackAdmTransFB formBean) 
	{		
		IpdConfigUtil icu = null;
		String strOccDtlsview;
		PackAdmTransVO voObj= null;
		PackAdmTransBO bo= null;
		String strAddressDepUbit="";
		String strEmgAddress="";
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(formBean.getStrHospCode());
		HisUtil util = new HisUtil("ipd", "PackAdmTransDATA");
		
		try 
		 {
			formBean.setStrFatherNameMandatoryFlag(IpdTransConfig.getMandatoryFatherNameAddmision());
			icu   =  new IpdConfigUtil(formBean.getStrHospCode());
			voObj =  new PackAdmTransVO();
			bo    =  new PackAdmTransBO();
			
			voObj.setStrCrNo(formBean.getStrCrNo());
			voObj.setStrHospCode(formBean.getStrHospCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			IpdConfigUtil ipdConfig=new IpdConfigUtil(formBean.getStrHospCode());
			voObj.setStrAdmissionAdviceValidityFrom(ipdConfig.getStrAdmissionAdviceValidityFrom());
			voObj.setStrAdmissionAdviceValidityTo(ipdConfig.getStrAdmissionAdviceValidityTo());
			voObj.setStrIsAdmissionOnline(ipdConfig.getStrAdmissionOnline());
			voObj.setStrIsIntegratedWithBilling(ipdConfig.getStrIntegrationBilling());
			voObj.setStrIsAdvanceAmountAtAdmission(ipdConfig.getStrAdvanceAmountAdmission());
			voObj.setStrAdmissionCharge(ipdConfig.getStrAdmissionCharge());
		//	voObj.setStrDeptUnitCode("0");
			bo.setPatientDtl(voObj);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrIsIntegratedWithBilling(voObj.getStrIsIntegratedWithBilling());
			formBean.setStrIsAdvanceAmountAtAdmission(voObj.getStrIsAdvanceAmountAtAdmission());
			formBean.setStrIsAdvanceAmountAtAdmissionTaken(voObj.getStrIsAdvanceAmountAtAdmissionTaken());
			formBean.setStrAdvanceAmount(voObj.getStrAdvanceAmount());
			formBean.setStrAdvanceAmountDate(voObj.getStrAdvanceAmountDate());
			formBean.setStrAdvanceAmountReceiptNo(voObj.getStrAdvanceAmountReceiptNo());
			formBean.setStrAgeUnit(voObj.getStrAgeUnit());
			formBean.setStrSexCode(voObj.getStrSexCode());
			formBean.setStrAge(voObj.getStrAge());
			formBean.setStrAdmissionChargeAtCounter(ipdConfig.getStrAdmissionCharge());
			
			if(ipdConfigUtil.getStrUnitNameReq().equals("1"))
			{
				formBean.setStrHiddenUnit("1");
			}
			else
				formBean.setStrHiddenUnit("0");
			if(ipdConfigUtil.getStrRoomNoReq().equals("1"))
			formBean.setStrHiddenRoom("1");
			else
				formBean.setStrHiddenRoom("0");
			if(voObj.getStrCrNoValid().equals("1"))
			{
				formBean.setStrMsgString("Invalid CR No./Data not found");
				formBean.setStrCrNo("");
			}
			/*else if(voObj.getStrIsAdvanceAmountAtAdmissionTaken().equals("0"))
			{
				formBean.setStrMsgString("Advance Amount is not deposited");
				formBean.setStrCrNo("");
			}*/
			else
			{
			
			
			if(voObj.getStrCrNoValid().equals("0"))
			{
				formBean.setStrAdmissionCharge(ipdConfig.getStrAdmissionChargeTakenAtCounter());
				formBean.setStrNoOfFreePass(ipdConfig.getStrNoOfFreePass());
				formBean.setStrAdviceStatus(voObj.getStrAdviceStatus());
				formBean.setStrPatStatusCode(voObj.getStrPatStatusCode());
				formBean.setStrIsDead(voObj.getStrIsDead());
				formBean.setStrFreePassValid(ipdConfig.getStrNoOfFreePassAdmisssionTime());
				
				/*if(!formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()))
				{
					formBean.setStrMsgString("Patient is not Admitted");
					formBean.setStrCrNo("");
				}
				if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode()))
				{
					formBean.setStrMsgString("Patient is Dead");
					formBean.setStrCrNo("");
				}*/
				/*if(!(formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()) || formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode())))
				{
					voObj.setStrAdmissionChargeAtCounter(formBean.getStrAdmissionChargeAtCounter());
					strAddressDepUbit=PatientAdmissionTransHLP.getPatientDetailModi(voObj);
					
					formBean.setStrAddressModi(strAddressDepUbit.replace("^","#").split("#")[0]);
					formBean.setStrWardBedModi(strAddressDepUbit.replace("^","#").split("#")[1]);
					formBean.setStrEmgAddress(strAddressDepUbit.replace("^", "#").split("#")[2]);
					
					formBean.setStrDeptUnitName(voObj.getStrDeptName()+"/"+voObj.getStrUnitName());
					
					//formBean.setStrWardBedModi(PatientAdmissionTransHLP.getPatientWardDetailModi(voObj));
					if(voObj.getStrNewBorn().equals("1"))
					{
						strOccDtlsview = HLPOccupationDetails.getOccupationDetails(voObj.getStrMotherCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
						formBean.setStrBedCode(voObj.getStrBedCode());
					}
					
					else
					{
						strOccDtlsview = HLPOccupationDetails.getOccupationDetails(formBean.getStrCrNo(), voObj.getStrPatCatCode(), icu.getStaffCategory(),voObj.getStrHospCode());
					}
					formBean.setStrIsAdmissionOnline(voObj.getStrIsAdmissionOnline());
					formBean.setOccupationDetailValues(strOccDtlsview);
					formBean.setStrWardTypeCode(voObj.getStrWardTypeCode());
					formBean.setStrWardCode(voObj.getStrWardCode());
					formBean.setStrBedTypeCode(voObj.getStrBedTypeCode());
					formBean.setStrRoomTypeCode(voObj.getStrRoomTypeCode());
					formBean.setStrWardName(voObj.getStrWardName());
					formBean.setStrDeptCode(voObj.getStrDeptCode());
					formBean.setStrTreatmentCategoryName(voObj.getStrTreatmentCategoryName());
					formBean.setStrTreatmentCategoryCode(voObj.getStrTreatmentCategoryCode());
					formBean.setStrDeptUnitCode(voObj.getStrDeptUnitCode());
					formBean.setStrIsUrban(voObj.getStrIsUrban());
					formBean.setStrConsultantName(voObj.getStrConsultantName());
					formBean.setStrConsultantCode(voObj.getStrConsultantCode());
					formBean.setStrEpisodeCode(voObj.getStrEpisodeCode());
					formBean.setStrVisitNo(voObj.getStrVisitNo());
					formBean.setStrMlcNo(voObj.getStrMlcNo());
					formBean.setStrAdviceAdmNo(voObj.getStrAdviceAdmNo());
					formBean.setStrBookingDate(voObj.getStrBookingDate());
					formBean.setStrNewBorn(voObj.getStrNewBorn());
					formBean.setStrMsApprovalFlag(voObj.getStrMsApprovalFlag());
					formBean.setStrBedCode(voObj.getStrBedCode());
					formBean.setStrRoomCode(voObj.getStrRoomCode());
					formBean.setStrRoom(voObj.getStrRoom());
					formBean.setStrMotherAdmissionNo(voObj.getStrMotherAdmissionNo());
					formBean.setStrMotherCrNo(voObj.getStrMotherCrNo());
					formBean.setStrMotherName(voObj.getStrMotherName());
					formBean.setStrMotherNationality(voObj.getStrMotherNationality());
					formBean.setStrMotherNationalityCode(voObj.getStrMotherNationalityCode());
					formBean.setStrMotherReligion(voObj.getStrMotherReligion());
					formBean.setStrMotherReligionCode(voObj.getStrMotherReligionCode());
					formBean.setStrMsApprovalStatus(voObj.getStrMsApprovalStatus());
					formBean.setStrDepartmentName(voObj.getStrDeptName());
					formBean.setStrDeptUnitName(voObj.getStrUnitName());
					formBean.setStrPrimaryCategoryCode(voObj.getStrPrimaryCategoryCode());
					
					//checking if Admission charge required
					formBean.setStrAdmissionChargeAtCounter(ipdConfigUtil.getStrAdmissionCharge());
					//Setting the Admission charge for that Patient (Depends on patient category)
					formBean.setStrAdmissionChargeValue(voObj.getStrAdmissionChargeValue());
					
					if(voObj.getStrMsgType().equals("1"))
					{
						throw new Exception(voObj.getStrMsgString());
					}
			  }*/
			 if(voObj.getStrMsgType().equals("1"))
			 {
				throw new Exception(voObj.getStrMsgString());
			 }
		  }
			else
			{
				
				formBean.setStrMsgString("Invalid C.R No./Episode/Data not found");
				formBean.setStrCrNo("");
			}
			if(voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			 String strmsgText = e.getMessage();
			   HisException eObj = new HisException("IPD", "PackAdmTransDATA->initAdmissionAdvice()", strmsgText);
			   formBean.setStrMsgString("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			     eObj = null;
			     formBean.setStrCrNo("");
		}
	}
	public static void admitlist(PackAdmTransFB beanObj,HttpServletRequest request, HttpServletResponse response) 
	{
		PackAdmTransVO VO = null;
		PackAdmTransBO bo = null;
		try 
		{
			String temp = "";
			VO = new PackAdmTransVO();
			bo = new PackAdmTransBO();
			VO.setStrHospCode(beanObj.getStrHospCode());
			HisUtil util = new HisUtil("ipd", "PackAdmTransDATA");
			
			VO.setStrDeptCode(beanObj.getStrDeptCode());
			VO.setStrWardCode(beanObj.getStrWardCode());
			/*VO.setStrRoom(beanObj.getStrRoom());
			VO.setStrCrNo(beanObj.getStrCrNo());
			VO.setStrUnit(beanObj.getStrUnit());
			
			VO.setStrhtransinflag(beanObj.getStrhtransinflag());
			VO.setStrWadType(beanObj.getStrWadType());
			*/
			VO.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			//VO.setStrAdmNo(request.getParameter("chk").replace("@", "#").split("#")[1]);
			VO.setStrAdmNo(beanObj.getStrAdmNo());
			VO.setStrCrNo(beanObj.getStrCrNo());
			/*HisUtil util = new HisUtil("ipd", "NursingDeskTransDATA");
			String strunit = util.getOptionValue(VO.getStrUnitWs(), "", "0^Select Value", false);
			//VO.setStrUnit(beanObj.getStrUnit());
			beanObj.setStrunitproperty(strunit);
			VO.setStrunitproperty(beanObj.getStrunitproperty());*/
			//bo.admitedbed(VO);
		/*	if (beanObj.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
			//bo.beddetailcombo(VO);
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(VO.getStrMsgString());
			}
*/          bo.getBillingPackageNames(VO);
            if(VO.getPackageComboValuesWs()!=null && VO.getPackageComboValuesWs().size()>0)
	            temp = util.getOptionValue(VO.getPackageComboValuesWs(), "0","0^Select Value", false);
            else
	            temp = util.getOptionValue(VO.getPackageComboValuesWs(), "0","0^Select Value", false);

            beanObj.setStrPackageComboValues(temp);
			String stradmitlist = PackAdmTransHLP.getAdmitDetailNr(VO,temp);
			beanObj.setStrAdmitDetailProperty(stradmitlist);
			beanObj.setStrDepartmentName(VO.getStrDeptName());
			beanObj.setStrWardName(VO.getStrWardName());
			beanObj.setStrPreviousOccupiedbed(VO.getStrPreviousOccupiedbed());
			if (VO.getStrMsgType().equals("1")) 
			{ 
				throw new Exception(beanObj.getStrMsgString());
			}
			beanObj.setStrMsgString(VO.getStrMsgString());
			beanObj.setStrMsgType(VO.getStrMsgType());
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString(e.getMessage());
			VO.setStrMsgType("1");
			new HisException("IPD->Transactions->PackAdmTransDATA","PackAdmTransDATA.admitlist() --> -->", VO.getStrMsgString());
		} 
		finally 
		{
			if (VO != null)
				VO = null;
			if (bo != null)
				bo = null;
		}
	}
	public static void insert(PackAdmTransFB formBean,HttpServletRequest request) 
	{
		IpdConfigUtil ipdConfig = null;
		
		try 
		{
			ipdConfig = new IpdConfigUtil(formBean.getStrHospCode());
			PackAdmTransVO voObj = (PackAdmTransVO) TransferObjectFactory.populateData("ipd.transactions.PackAdmTransVO",formBean);
			PackAdmTransBO businessObj = new PackAdmTransBO();

			businessObj.insert(voObj);
			formBean.setStrMsgType(voObj.getStrMsgType());

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			} 
			else 
			{
				formBean.setStrMsgString("Package is Successfully Applied.");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("IPD","PackAdmTransDATA->insert()", strmsgText);
			formBean.setStrMsgString("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
	}

}
