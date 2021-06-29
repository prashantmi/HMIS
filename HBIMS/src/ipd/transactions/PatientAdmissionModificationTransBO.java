package ipd.transactions;




public class PatientAdmissionModificationTransBO {
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void setPatientDtl(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			
			PatientAdmissionModificationTransDAO.setEpisodeDtl(vo);
			PatientAdmissionModificationTransDAO.getAdmissionType(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception();
				}
				if(vo.getStrValidCrNo().equals("0"))
				{
					PatientAdmissionModificationTransDAO.setAdmDtl(vo);
					if(vo.getStrMsgType().equals("1"))
					{
							throw new Exception();
					}
					if(vo.getStrModifyTimeStatus().equals("0"))
					{
						/*if(vo.getStrIsIntegratedWithBilling().equals("1") && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
						{
							PatientAdmissionModificationTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
						}*/
						
						//PatientAdmissionModificationTransDAO.getStatusWhetherAdvanceAmountGiven(vo);

						/*
						 * set consultant name
						 */
						
						
							// Getting Consultant List (updated at 31-Mar-2011 by Pragya)
						//PatientAdmissionModificationTransDAO.setConsultantName(vo);
						PatientAdmissionModificationTransDAO.setConsultantNameDtl(vo);
						
						/*
						 * for department combo
						 */
						PatientAdmissionModificationTransDAO.department(vo);
						/*
						 * for unit combo
						 */
						if(vo.getStrUnitReq().equals("1"))
						PatientAdmissionModificationTransDAO.unit(vo);
						/*
						 * for ward combo
						 */
						PatientAdmissionModificationTransDAO.getWardValues(vo);
						/*
						 * for room combo
						 */
						if(vo.getStrRoomReq().equals("1"))
						PatientAdmissionModificationTransDAO.getRoomValues(vo);
						/*
						 * for Treatment category combo
						 */
						PatientAdmissionModificationTransDAO.setTreatmentCatDtl(vo);
						/*
						 * To bring Ms approval status details
						 */
						PatientAdmissionModificationTransDAO.getMsApprovalStatus(vo);
						if(vo.getStrMsgType().equals("1"))
						{
							throw new Exception();
						}
					}
				}
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function invoke PatientAdmissionTransDAO.setWardTypeDtl(),PatientAdmissionTransDAO.setRoomTypeDtl(),PatientAdmissionTransDAO.getWardValues(vo),PatientAdmissionTransDAO.getRoomValues(vo),PatientAdmissionTransDAO.setBedTypeDtl(vo) to bring data on bed status window
	 * @param vo
	 */
	public void setBedStatusDtl(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.setWardTypeDtl(vo);
			PatientAdmissionModificationTransDAO.getWardValues(vo);
			PatientAdmissionModificationTransDAO.setRoomTypeDtl(vo);
			PatientAdmissionModificationTransDAO.getRoomValues(vo);
			PatientAdmissionModificationTransDAO.setBedTypeDtl(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getBedValues(vo) to search beds in a ward and count the no of vacant beds
	 * @param vo
	 */
	public void setBedDetails(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.getBedValues(vo);
			PatientAdmissionModificationTransDAO.countBed_in_ward(vo);
			PatientAdmissionModificationTransDAO.getMsApprovalStatus(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getWardValues(vo) to bring details in ward combo on bed details pop up window
	 * @param vo
	 */
	public void setWardDetails(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.getWardValues(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void getWardCatConsultant(PatientAdmissionModificationTransVO vo)
	{
		try
		{
						// Getting Consultant List (updated at 31-Mar-2011 by Pragya)
						//PatientAdmissionModificationTransDAO.setConsultantName(vo);
						PatientAdmissionModificationTransDAO.setConsultantNameDtl(vo);
						/*
						 * for ward combo
						 */
						PatientAdmissionModificationTransDAO.getWardValues(vo);
						
						if(vo.getStrMsgType().equals("1"))
						{
							throw new Exception();
						}
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getRoomValues(vo) 
	 * @param vo
	 */
	public void setRoomDetails(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.getRoomValues(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void insert(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.insert(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	/**
	 * This function is used to set initials parameters to bring patient address on main page in modifiable  form.it will invoke PatientAdmissionTransDAO's setReligion(),setState(),getPatientAdd().
	 * @param vo
	 */
	public void setPatientAddModi(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.setReligion(vo);
			PatientAdmissionModificationTransDAO.setPatientCaste(vo);
			PatientAdmissionModificationTransDAO.setMaritalStatus(vo);
			PatientAdmissionModificationTransDAO.setState(vo);
			PatientAdmissionModificationTransDAO.getPatientAdd(vo);
			PatientAdmissionModificationTransDAO.setDistrict(vo);
			PatientAdmissionModificationTransDAO.setCountry(vo);
			PatientAdmissionModificationTransDAO.getAdmissionType(vo);
			PatientAdmissionModificationTransDAO.getReliefFund(vo);
			PatientAdmissionModificationTransDAO.setRelation(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setPatientAddModi---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void setUnitValue(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.unit(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setUnitValue---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void getDistrict(PatientAdmissionModificationTransVO vo)
	{
		try
		{
			PatientAdmissionModificationTransDAO.setDistrict(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
}
