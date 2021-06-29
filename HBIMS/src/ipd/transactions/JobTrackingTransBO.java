package ipd.transactions;




public class JobTrackingTransBO {
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void setPatientDtl(JobTrackingTransVO vo)
	{
		try
		{
			
			JobTrackingTransDAO.setEpisodeDtl(vo);
			JobTrackingTransDAO.getAdmissionType(vo);
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception();
				}
				if(vo.getStrValidCrNo().equals("0"))
				{
					JobTrackingTransDAO.setAdmDtl(vo);
					if(vo.getStrMsgType().equals("1"))
					{
							throw new Exception();
					}
					if(vo.getStrModifyTimeStatus().equals("0"))
					{
						/*if(vo.getStrIsIntegratedWithBilling().equals("1") && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
						{
							JobTrackingTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
						}*/
						
						//JobTrackingTransDAO.getStatusWhetherAdvanceAmountGiven(vo);

						/*
						 * set consultant name
						 */
						
						
							// Getting Consultant List (updated at 31-Mar-2011 by Pragya)
						//JobTrackingTransDAO.setConsultantName(vo);
						JobTrackingTransDAO.setConsultantNameDtl(vo);
						
						/*
						 * for department combo
						 */
						JobTrackingTransDAO.department(vo);
						/*
						 * for unit combo
						 */
						if(vo.getStrUnitReq().equals("1"))
						JobTrackingTransDAO.unit(vo);
						/*
						 * for ward combo
						 */
						JobTrackingTransDAO.getWardValues(vo);
						/*
						 * for room combo
						 */
						if(vo.getStrRoomReq().equals("1"))
						JobTrackingTransDAO.getRoomValues(vo);
						/*
						 * for Treatment category combo
						 */
						JobTrackingTransDAO.setTreatmentCatDtl(vo);
						/*
						 * To bring Ms approval status details
						 */
						JobTrackingTransDAO.getMsApprovalStatus(vo);
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
			
			vo.setStrMsgString("JobTrackingTransBO.setPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function invoke PatientAdmissionTransDAO.setWardTypeDtl(),PatientAdmissionTransDAO.setRoomTypeDtl(),PatientAdmissionTransDAO.getWardValues(vo),PatientAdmissionTransDAO.getRoomValues(vo),PatientAdmissionTransDAO.setBedTypeDtl(vo) to bring data on bed status window
	 * @param vo
	 */
	public void setBedStatusDtl(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.setWardTypeDtl(vo);
			JobTrackingTransDAO.getWardValues(vo);
			JobTrackingTransDAO.setRoomTypeDtl(vo);
			JobTrackingTransDAO.getRoomValues(vo);
			JobTrackingTransDAO.setBedTypeDtl(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getBedValues(vo) to search beds in a ward and count the no of vacant beds
	 * @param vo
	 */
	public void setBedDetails(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.getBedValues(vo);
			JobTrackingTransDAO.countBed_in_ward(vo);
			JobTrackingTransDAO.getMsApprovalStatus(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getWardValues(vo) to bring details in ward combo on bed details pop up window
	 * @param vo
	 */
	public void setWardDetails(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.getWardValues(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void getWardCatConsultant(JobTrackingTransVO vo)
	{
		try
		{
						// Getting Consultant List (updated at 31-Mar-2011 by Pragya)
						//JobTrackingTransDAO.setConsultantName(vo);
						JobTrackingTransDAO.setConsultantNameDtl(vo);
						/*
						 * for ward combo
						 */
						JobTrackingTransDAO.getWardValues(vo);
						
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
			
			vo.setStrMsgString("JobTrackingTransBO.setPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getRoomValues(vo) 
	 * @param vo
	 */
	public void setRoomDetails(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.getRoomValues(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void insert(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.insert(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	/**
	 * This function is used to set initials parameters to bring patient address on main page in modifiable  form.it will invoke PatientAdmissionTransDAO's setReligion(),setState(),getPatientAdd().
	 * @param vo
	 */
	public void setPatientAddModi(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.setReligion(vo);
			JobTrackingTransDAO.setPatientCaste(vo);
			JobTrackingTransDAO.setMaritalStatus(vo);
			JobTrackingTransDAO.setState(vo);
			JobTrackingTransDAO.getPatientAdd(vo);
			JobTrackingTransDAO.setDistrict(vo);
			JobTrackingTransDAO.setCountry(vo);
			JobTrackingTransDAO.getAdmissionType(vo);
			JobTrackingTransDAO.getReliefFund(vo);
			JobTrackingTransDAO.setRelation(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setPatientAddModi---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void setUnitValue(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.unit(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setUnitValue---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void getDistrict(JobTrackingTransVO vo)
	{
		try
		{
			JobTrackingTransDAO.setDistrict(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("JobTrackingTransBO.setRoomDetails---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
}
