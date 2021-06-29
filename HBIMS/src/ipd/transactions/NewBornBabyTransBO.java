package ipd.transactions;

import ipd.IpdConfigUtil;

public class NewBornBabyTransBO {
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's
	 * setDeptName,setUnitName,getBedStatusDtl
	 * 
	 * @param vo
	 */
	public void setPatientDtl(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setNewBornBabyDtl(vo);
			String s=vo.getStrAge();
			if(s !=null)
				s=s.substring(0,2).trim();
			
			IpdConfigUtil ipd=new IpdConfigUtil(vo.getStrHospCode());
			if(vo.getStrMaxBabyAllowed().equals("0"))
				throw new Exception("Baby Limit Exceeded");
			else if(vo.getStrMaxBabyAllowed().equals("100"))
				throw new Exception("Baby Not Allowed");
			if(vo.getStrAge() != null && (vo.getStrAge().endsWith("Wk") || vo.getStrAge().endsWith("Mth") || vo.getStrAge().endsWith("D")))
			{
				throw new Exception("Age is too Small to be a Mother");
			}
			else if(s != null && Integer.parseInt(s) < Integer.parseInt(ipd.getStrMinAgeToBeMother()))
			throw new Exception("Age is too Small to be a Mother");
			NewBornBabyTransDAO.setPatientStatus(vo);
			if(vo.getStrPatientIsNotAccepted()!=null)
			{
			if(vo.getStrPatientIsNotAccepted().equals("0"))
				throw new Exception("Patient is not accepted.");
			}
			else
				throw new Exception("Patient is not Admitted.");
			//NewBornBabyTransDAO.setConsultantName(vo);
			NewBornBabyTransDAO.unit(vo);
			NewBornBabyTransDAO.department(vo);
			NewBornBabyTransDAO.setTreatmentCatDtl(vo);
			//NewBornBabyTransDAO.setChargeVal(vo);
			//NewBornBabyTransDAO.setRegistrationVal(vo);
			NewBornBabyTransDAO.setAdvanceAmountVal(vo);
			//NewBornBabyTransDAO.setGenderCode(vo);
			NewBornBabyTransDAO.getAdmissionType(vo);
			NewBornBabyTransDAO.getReliefFund(vo);
			//NewBornBabyTransDAO.setConsultantNameDtl(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().equals("Baby Limit Exceeded")){
				vo.setStrMsgType("6");
			}else if(e.getMessage().equals("Baby Not Allowed")){
				vo.setStrMsgType("7");
			}else if(e.getMessage().equals("Patient is not accepted.")){
				vo.setStrMsgType("8");
			}else if(e.getMessage().equals("Patient is not Admitted.")){
				vo.setStrMsgType("9");
			}else if(e.getMessage().equals("Age is too Small to be a Mother"))
			{
				vo.setStrMsgType("4");
			}else{
				vo.setStrMsgString("NewBornBabyTransBO.setPatientDtl"+ e.getMessage());
				vo.setStrMsgType("1");
			}
		}

	}

	/**
	 * This function invoke
	 * PatientAdmissionTransDAO.setWardTypeDtl(),PatientAdmissionTransDAO.setRoomTypeDtl(),PatientAdmissionTransDAO.getWardValues(vo),PatientAdmissionTransDAO.getRoomValues(vo),PatientAdmissionTransDAO.setBedTypeDtl(vo)
	 * to bring data on bed status window
	 * 
	 * @param vo
	 */
	public void setBedStatusDtl(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setWardTypeDtl(vo);
			NewBornBabyTransDAO.getWardValues(vo);
			NewBornBabyTransDAO.setRoomTypeDtl(vo);
			NewBornBabyTransDAO.getRoomValues(vo);
			NewBornBabyTransDAO.setBedTypeDtl(vo);
			// NewBornBabyTransDAO.setDeptName(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setBedStatusDtl---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}
	public void matchWardRoomCriteria(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.matchWardRoomCriteria(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.matchWardRoomCriteria---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getBedValues(vo)
	 * to search beds in a ward and count the no of vacant beds
	 * 
	 * @param vo
	 */
	public void setBedDetails(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.getBedValues(vo);
			NewBornBabyTransDAO.countBed_in_ward(vo);
			NewBornBabyTransDAO.getMsApprovalStatus(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setBedStatusDtl---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/**
	 * This function is used to invoke
	 * PatientAdmissionTransDAO.getWardValues(vo) to bring details in ward combo
	 * on bed details pop up window
	 * 
	 * @param vo
	 */
	public void setWardDetails(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.getWardValues(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setBedStatusDtl---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/**
	 * This function is used to invoke
	 * PatientAdmissionTransDAO.getRoomValues(vo)
	 * 
	 * @param vo
	 */
	public void setRoomDetails(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.getRoomValues(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setBedStatusDtl---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	public void insert(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.insert(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.insert---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/**
	 * This function is used to set initials parameters to bring patient address
	 * on main page in modifiable form.it will invoke PatientAdmissionTransDAO's
	 * setReligion(),setState(),getPatientAdd().
	 * 
	 * @param vo
	 */
	public void setPatientAddModi(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setReligion(vo);
			NewBornBabyTransDAO.setCountry(vo);
			NewBornBabyTransDAO.getPatientAdd(vo);
			NewBornBabyTransDAO.setState(vo);
			NewBornBabyTransDAO.setDistrict(vo);
			NewBornBabyTransDAO.setGenderDtl(vo);
			NewBornBabyTransDAO.setPatientCaste(vo);
			
			
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setPatientAddModi---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void setUnitValue(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.unit(vo);
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setUnitValue---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void getAdvanceAmount(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setAdvanceAmountVal(vo);
			NewBornBabyTransDAO.setChargeVal(vo);
			NewBornBabyTransDAO.setRegistrationVal(vo);
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.getAdvanceAmount---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void onlineBabyBornEntery(NewBornBabyTransVO _NewBornBabyTransVO) {
		NewBornBabyTransDAO.onlineBabyBornEntery(_NewBornBabyTransVO);
		if (_NewBornBabyTransVO.getStrMsgType().equals("1"))
			_NewBornBabyTransVO
					.setStrMsgString("NewBornBabyTransBO.setBedStatusDtl---->"
							+ _NewBornBabyTransVO.getStrMsgString());
	}
	
	public void setAdmittedBabyCountModi(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setReligion(vo);
			NewBornBabyTransDAO.setState(vo);
			NewBornBabyTransDAO.getPatientAdd(vo);
			NewBornBabyTransDAO.setCountry(vo);
			NewBornBabyTransDAO.setGenderDtl(vo);
			NewBornBabyTransDAO.setNumberOfBabiesWhoseDetailEntered(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setPatientAddModi---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	public void setAdmittedBabyDetailsModi(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setDetailOfBabyBorn(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.setPatientAddModi---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	
	public void update(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.updatePatAdmission(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransBO.update---->"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void setPatientDtlNewBornUpdateMother(NewBornBabyTransVO vo) {
		try {
			NewBornBabyTransDAO.setNewBornBabyDtl(vo);
			
			if(vo.getStrNumberOfChildrenBorn().equals(""))
			{
				throw new Exception("Mother has not given birth to any baby,thus details cannot be updated.");
			}
			
			if(vo.getStrMaxBabyAllowed().equals("0"))
				throw new Exception("Baby Limit Exceeded");
			else if(vo.getStrMaxBabyAllowed().equals("100"))
				throw new Exception("Baby Not Allowed");
			
			NewBornBabyTransDAO.setPatientStatus(vo);
			
			if(vo.getStrPatStatusCode().equals("12"))
			{
				throw new Exception("Patient is not Admitted.");
			}
			else if(vo.getStrPatientIsNotAccepted()!=null)
			{
			if(vo.getStrPatientIsNotAccepted().equals("0"))
				throw new Exception("Patient is not accepted.");
			}
			//NewBornBabyTransDAO.setConsultantName(vo);
			NewBornBabyTransDAO.unitNewBornDefault(vo);
			NewBornBabyTransDAO.department(vo);
			NewBornBabyTransDAO.setTreatmentCatDtl(vo);
			NewBornBabyTransDAO.setChargeVal(vo);
			//NewBornBabyTransDAO.setRegistrationVal(vo);
			NewBornBabyTransDAO.setAdvanceAmountVal(vo);
			//NewBornBabyTransDAO.setGenderCode(vo);
			NewBornBabyTransDAO.setDetailOfBabyBorn(vo);
			
		
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().equals("Baby Limit Exceeded")){
				vo.setStrMsgType("6");
			}else if(e.getMessage().equals("Baby Not Allowed")){
				vo.setStrMsgType("7");
			}else if(e.getMessage().equals("Patient is not accepted.")){
				vo.setStrMsgType("8");
			}else if(e.getMessage().equals("Patient is not Admitted.")){
				vo.setStrMsgType("9");
			}else if(e.getMessage().equals("Mother has not given birth to any baby,thus details cannot be updated.")){
				vo.setStrMsgType("0Baby");
			}else{
				vo.setStrMsgString("NewBornBabyTransBO.setPatientDtl"+ e.getMessage());
				vo.setStrMsgType("1");
			}
		}

	}
	public void getDistrict(NewBornBabyTransVO vo)
	{
		try
		{
			NewBornBabyTransDAO.setDistrict(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("NewBornBabyTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	
	public void setsharable(NewBornBabyTransVO vo)
	{
		try
		{
			NewBornBabyTransDAO.setsharable(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("NewBornBabyTransBO.setsharable---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void getCons(NewBornBabyTransVO vo)
	{
		try
		{
			NewBornBabyTransDAO.setConsultantNameDtl(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("NewBornBabyTransBO.getCons---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	
	
}
