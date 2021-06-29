package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import ipd.DAOIpd;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import ipd.IpdVO;
import ipd.setup.IPDConfig;




public class PatientAdmissionTransBO {
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void setPatientDtl(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setEpisodeDtl(vo);
			PatientAdmissionTransDAO.getAdmissionType(vo);
			PatientAdmissionTransDAO.getPatDtl_Msapprovalstat(vo);
			PatientAdmissionTransDAO.getIcuPvtBillStatus(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			if(vo.getStrCrNoValid().equals("0"))//CR No Valid
			{
				if(vo.getStrIsIntegratedWithBilling().equals("1") && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
				{
					PatientAdmissionTransDAO.checkStatusWhetherAdvanceAmountGiven(vo);
					//if(!vo.getStrIsAdvanceAmountAtAdmissionTaken().equals("0"))
							//PatientAdmissionTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
				}
				//PatientAdmissionTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
				PatientAdmissionTransDAO.setPatientStatus(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
				
				if((vo.getStrPatStatusCode().equals(IpdConfigUtil.strOpdCode) || vo.getStrPatStatusCode().equals(IpdConfigUtil.strEmgCode)) && !(vo.getStrIsDead().equals("1")))
				{
					/*
					 * This function is used to bring admission advice details from admission advice table. 
					 */
					//if(vo.getStrIsAdmissionOnline().equals("1"))
					//{
						if(vo.getStrDeptWardChangeChk().equals("1"))
						{
							vo.setStrAdviceStatus("0");
							vo.setStrIsAdmissionOnline("2");//If Department Ward Change Then Set Admission As Offline
						}						
						else							
							PatientAdmissionTransDAO.setAdviceAdmNo(vo);
					//}
					if(vo.getStrMsgType().equals("1"))
					{
						throw new Exception(vo.getStrMsgString());
					}
				}
				
				if(vo.getStrAdmissionCharge().equals("1"))//Admission Charges Required
				{					
					PatientAdmissionTransDAO.setChargeVal(vo);
				}
				if(vo.getStrAdviceStatus().equals("1"))///Active Advice Generated
				{
				
					if(vo.getStrIsAdmissionOnline().equals("1"))
					{
						PatientAdmissionTransDAO.getBedStatusDtl(vo);
					}
					PatientAdmissionTransDAO.setConsultantName(vo);
					//PatientAdmissionTransDAO.setChargeVal(vo);
					PatientAdmissionTransDAO.getPatDtl_Msapproval(vo);
					if(vo.getStrMsgType().equals("1"))
					{
						throw new Exception(vo.getStrMsgString());
					}
				}
				
				
					PatientAdmissionTransDAO.getPaymentMode(vo);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransBO.setPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function invoke PatientAdmissionTransDAO.setWardTypeDtl(),
	 * PatientAdmissionTransDAO.setRoomTypeDtl(),PatientAdmissionTransDAO.getWardValues(vo),
	 * PatientAdmissionTransDAO.getRoomValues(vo),PatientAdmissionTransDAO.setBedTypeDtl(vo) 
	 * to bring data on bed status window
	 * @param vo
	 */
	public void setBedStatusDtl(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setWardTypeDtl(vo);
			PatientAdmissionTransDAO.setRoomTypeDtl(vo);
			if(!(vo.getStrMsApprovalFlag().equals("6") && vo.getStrMsApprovalStatus().equals("0")))
				PatientAdmissionTransDAO.getWardValuesBedStatus(vo);
			PatientAdmissionTransDAO.getRoomValues(vo);
			PatientAdmissionTransDAO.setBedTypeDtl(vo);
			//PatientAdmissionTransDAO.setDeptName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	
	
	
	public void getDataForBillingslip(PatientAdmissionTransVO vo)
	{
		try
		{   
			
			PatientAdmissionTransDAO.getDataForBillingslip(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.getDataForBillingslip---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getBedValues(vo) to search beds in a ward and count the 
	 * no of vacant beds
	 * @param vo
	 */
	public void setBedDetails(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.getBedValues(vo);
			PatientAdmissionTransDAO.countBed_in_ward(vo);
			PatientAdmissionTransDAO.getMsApprovalStatus(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setBedDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getWardValues(vo) to bring details 
	 * in ward combo on bed details pop up window
	 * @param vo
	 */
	public void setWardDetails(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.getWardValues(vo);
			PatientAdmissionTransDAO.setChargeVal(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setWardDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getRoomValues(vo) 
	 * @param vo
	 */
	public void setRoomDetails(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.getRoomValues(vo);
			PatientAdmissionTransDAO.setChargeVal(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void setUnitDetails(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.unit(vo);
			PatientAdmissionTransDAO.getWardName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void setTreatCat(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setTreatmentCatDtl(vo);
			PatientAdmissionTransDAO.setTreatCat(vo);
			PatientAdmissionTransDAO.setConsultantNameDtl(vo);
			PatientAdmissionTransDAO.getWardName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void insert(PatientAdmissionTransVO vo,VisitorPassTransVO _VisitorPassTransVO)
	{
		//IpdConfigUtil ipdC=new IpdConfigUtil(vo.getStrHospCode());
		try
		{
			PatientAdmissionTransDAO.insert(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			_VisitorPassTransVO.setStrAdmnNo(vo.getStrAdmNo());
			//if(ipdC.getStrAttendentPass().equals("1") && Integer.parseInt(ipdC.getStrNoOfFreePass())>0 && Integer.parseInt(ipdC.getStrNoOfPaidPass())>0)
			VisitorPassTransDAO.getVisitorPassDtl(_VisitorPassTransVO);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	public void printSlip(PatientAdmissionTransVO vo)
	{
		try
		{
			IpdConfigUtil ipdConfig=new IpdConfigUtil(vo.getStrHospCode());
			IpdVO voObj=new IpdVO();
			voObj.setStrCRNo(vo.getStrCrNo());			
			voObj.setStrValue1(vo.getStrCrNo());
			voObj.setStrValue2(vo.getStrAdmNo());
			voObj.setStrHospCode(vo.getStrHospCode());
			DAOIpd.setAdmissionDtlSlip(voObj);
			WebRowSet ws=voObj.getGblWs1();
			if (ws!=null && ws.size()>0) 
			{
				if (ws.next()) 
				{
					vo.setStrPatName(ws.getString(3));
					vo.setStrFatherName(ws.getString(4));
					vo.setStrHusbandName(ws.getString(10));
					vo.setStrAge(ws.getString(2));//Age/Sex
					vo.setStrAddress(ws.getString(8));
					if(!ws.getString(9).equals("0"))
					{
						vo.setStrMlcNo(ws.getString(9).replace("^", "#").split("#")[0]);
						vo.setStrIdenMark(ws.getString(9).replace("^","#").split("#")[1]+","+ws.getString(9).replace("^","#").split("#")[2]);
						vo.setStrRefRem(ws.getString(9).replace("^","#").split("#")[3]);
					}
					else
					{
						vo.setStrMlcNo("0");
						vo.setStrIdenMark("--");
						vo.setStrRefRem("--");
					}
					vo.setStrPhoneNo(ws.getString(13));
					vo.setStrPinCode(ws.getString(14));
					vo.setStrMonthlyIncome(ws.getString(15));
					vo.setStrIsNewBorn(ws.getString(17));
					vo.setStrMotherAdmissionNo(ws.getString(18));
					vo.setStrMotherName(ws.getString(11));
					
					vo.setStrAdmNo(ws.getString(19));
					vo.setStrAdmDateTime(ws.getString(20));
					vo.setStrWardName(ws.getString(27));
					vo.setStrDeptUnitName(ws.getString(28)+"/"+ws.getString(29));
					vo.setStrConsultantName(ws.getString(32));	
					vo.setStrBed(ws.getString(36));
					vo.setStrMaritalStatus(ws.getString(37));
					vo.setStrHospName(ws.getString(38).replace("^", "#").split("#")[0]);
					vo.setStrHospDtl(ws.getString(38).replace("^", "#").split("#")[1]);
					if(!ws.getString(39).equals("0"))
					{
						vo.setStrEmgAddressFlag("1");
						vo.setStrFirstPersonName(ws.getString(39).replace("^", "#").split("#")[0]);
						vo.setStrEmgAddress1(ws.getString(39).replace("^","#").split("#")[1]);
						vo.setStrFirstpersonphone(ws.getString(39).replace("^","#").split("#")[2]);
					}
					vo.setStrPatCategoryName(ws.getString(6));
					vo.setStrPatientIdNumber(ws.getString(40));
					vo.setStrPatientAdhaarNo(ws.getString(41));
					vo.setStrAdmissionChargeValue(ws.getString(42));
					vo.setStrAdmissionCharge(ws.getString(43));
					vo.setStrPatCatGrp(ws.getString(44));
					vo.setStrDobTime(ws.getString(45));
					vo.setStrMaritalStatus(ws.getString(46));
					vo.setStrHouseNo(ws.getString(47));
					vo.setStrStreet(ws.getString(48));
					vo.setStrCity(ws.getString(49));
					String address=ws.getString(8);
					
					if(ws.getString(50)==null)
					{
						vo.setStrDistrict(address.split(",")[2]);
					}else{
						vo.setStrDistrict(ws.getString(50));
					}
					if(ws.getString(51)==null)
					{
						vo.setStrStateName(address.split(",")[3]);
					}else{
					
						vo.setStrStateName(ws.getString(51));
					}
					
					vo.setStrPinCode(ws.getString(52));
					/*vo.setStrAdvanceAmount(ws.getString(53));
					vo.setStrAdvanceAmountReceiptNo(ws.getString(55)+"/"+ws.getString(54));//Account No/Bill No
					vo.setStrAdvanceAmountDate(ws.getString(56));//Account No/Bill No*/
					vo.setStrMobileNo(ws.getString(53));
					vo.setStrCaseSheetNo(ws.getString(54));
					vo.setStrTehsil(ws.getString(55));
					if(!ws.getString(56).equals("-"))
					{
						vo.setStrNamInf(ws.getString(56).replace("^", "#").split("#")[0]);
						vo.setStrPolInfo(ws.getString(56).replace("^", "#").split("#")[1]);
						vo.setStrDetPs(ws.getString(56).replace("^", "#").split("#")[2]);
					}
					else
					{
						vo.setStrPolInfo("--");
						vo.setStrNamInf("--");
						vo.setStrDetPs("--");
					}
					/*vo.setStrIsCreditAdvanceBilling(ws.getString(61));
					vo.setStrCreditLetterRefNo(ws.getString(62));
					vo.setStrCreditLetterDate(ws.getString(63));
					vo.setStrClientName(ws.getString(65));
					vo.setStrStaffNo(ws.getString(66));
					vo.setStrStaffName(ws.getString(67));
					vo.setStrRelationship(ws.getString(68));*/
					
					vo.setDisDateTime(ws.getString(57));
					vo.setDaysStay(ws.getString(58));
					vo.setAdmissionTypeName(ws.getString(59));
					vo.setStrCountryName(ws.getString(60));
					vo.setStrRegChg(ws.getString(61));
					/*commented it is based on Configuration..*/
					//vo.setStrIsAdvanceAmountAtAdmission(ws.getString(43));
					vo.setStrIsAdvanceAmountAtAdmission(ipdConfig.getStrAdvanceAmountAdmission());
					
				}
			}					
		}
		catch(Exception e)
		{
				vo.setStrMsgString("PatientAdmissionTransBO.setPatientDtl---->"+e.getMessage());
				vo.setStrMsgType("1");
		}	
	}
	/**
	 * This function is used to set initials parameters to bring patient address on main page in
	 *  modifiable  form.it will invoke PatientAdmissionTransDAO's setReligion(),setState(),getPatientAdd().
	 * @param vo
	 */
	public void setPatientAddModi(PatientAdmissionTransVO vo)
	{
		try
		{
			
			PatientAdmissionTransDAO.setReligion(vo);
			PatientAdmissionTransDAO.setPatientCaste(vo);
			PatientAdmissionTransDAO.setMaritalStatus(vo);
			PatientAdmissionTransDAO.setState(vo);
			PatientAdmissionTransDAO.getPatientAdd(vo);
			PatientAdmissionTransDAO.setDistrict(vo);
			PatientAdmissionTransDAO.setCountry(vo);
			PatientAdmissionTransDAO.department(vo);
			PatientAdmissionTransDAO.unit(vo);
			//PatientAdmissionTransDAO.getRoomValues(vo);
			PatientAdmissionTransDAO.getWardName(vo);
			PatientAdmissionTransDAO.setTreatmentCatDtl(vo);
			PatientAdmissionTransDAO.setConsultantNameDtl(vo);
			PatientAdmissionTransDAO.getAdmissionType(vo);
			PatientAdmissionTransDAO.getReliefFund(vo);
			PatientAdmissionTransDAO.setRelation(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setPatientAddModi---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	public void setChargeVal(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setChargeVal(vo);
			PatientAdmissionTransDAO.setAdvanceAmount(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setChargeVal---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void setWardName(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.getWardName(vo);
			//PatientAdmissionTransDAO.setChargeVal(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setWardDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	
	public void setWardConsultant(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setConsultantNameDept(vo); 
			PatientAdmissionTransDAO.getWardName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void setTreatmentCatWard(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.getWardName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void setinitWardonUnit(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.getWardonUnit(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void getDistrict(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setDistrict(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/*public void setStateInputForeign(PatientAdmissionTransVO vo)
	{
		try
		{
			PatientAdmissionTransDAO.setStateInputForeign(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
		}catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransBO.setStateInputForeign---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}*/
	public void getPayMode(PatientAdmissionTransVO vo) {
		// TODO Auto-generated method stub
		try{
				PatientAdmissionTransDAO.getPaymentMode(vo);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
