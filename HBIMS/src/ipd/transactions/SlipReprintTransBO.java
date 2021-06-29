package ipd.transactions;




public class SlipReprintTransBO {
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void setPatientDtl(SlipReprintTransVO vo)
	{
		try
		{
			
			SlipReprintTransDAO.setEpisodeDtl(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception();
				}
				if(vo.getStrValidCrNo().equals("0"))
				{
					SlipReprintTransDAO.setAdmDtl(vo);
					if(vo.getStrMsgType().equals("1"))
					{
							throw new Exception();
					}
					if(vo.getStrModifyTimeStatus().equals("0"))
					{
						/*if(vo.getStrIsIntegratedWithBilling().equals("1") && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
						{
							SlipReprintTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
						}*/
						
						SlipReprintTransDAO.getStatusWhetherAdvanceAmountGiven(vo);

						/*
						 * set consultant name
						 */
						
							// Getting Consultant List (updated at 31-Mar-2011 by Pragya)
						//SlipReprintTransDAO.setConsultantName(vo);
						SlipReprintTransDAO.setConsultantNameDtl(vo);
						
						/*
						 * for department combo
						 */
						SlipReprintTransDAO.department(vo);
						/*
						 * for unit combo
						 */
						SlipReprintTransDAO.unit(vo);
						/*
						 * for Treatment category combo
						 */
						SlipReprintTransDAO.setTreatmentCatDtl(vo);
						/*
						 * To bring Ms approval status details
						 */
						SlipReprintTransDAO.getMsApprovalStatus(vo);
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
	public void setBedStatusDtl(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.setWardTypeDtl(vo);
			SlipReprintTransDAO.getWardValues(vo);
			SlipReprintTransDAO.setRoomTypeDtl(vo);
			SlipReprintTransDAO.getRoomValues(vo);
			SlipReprintTransDAO.setBedTypeDtl(vo);
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
	public void setBedDetails(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.getBedValues(vo);
			SlipReprintTransDAO.countBed_in_ward(vo);
			SlipReprintTransDAO.getMsApprovalStatus(vo);
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
	public void setWardDetails(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.getWardValues(vo);
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
	 * This function is used to invoke PatientAdmissionTransDAO.getRoomValues(vo) 
	 * @param vo
	 */
	public void setRoomDetails(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.getRoomValues(vo);
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
	public void insert(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.insert(vo);
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
	public void setPatientAddModi(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.setReligion(vo);
			SlipReprintTransDAO.setState(vo);
			SlipReprintTransDAO.getPatientAdd(vo);
			SlipReprintTransDAO.setCountry(vo);
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
	public void setUnitValue(SlipReprintTransVO vo)
	{
		try
		{
			SlipReprintTransDAO.unit(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransBO.setUnitValue---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
}
