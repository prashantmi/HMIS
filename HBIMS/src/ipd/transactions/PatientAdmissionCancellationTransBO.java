package ipd.transactions;




public class PatientAdmissionCancellationTransBO {
	/**
	 * This function is used to invoke PatientAdmissionTransDAO's  setDeptName,setUnitName,getBedStatusDtl
	 * @param vo
	 */
	public void setPatientDtl(PatientAdmissionCancellationTransVO  vo)
	{
		try
		{
				PatientAdmissionCancellationTransDAO.setPatientStatus(vo);
				if(vo.getStrValidCrNo().equals("0"))
				{
					PatientAdmissionCancellationTransDAO.setAdmDtl(vo);
					PatientAdmissionCancellationTransDAO.setConsultantNameDtl(vo);
					PatientAdmissionCancellationTransDAO.setAdvanceDtl(vo);
				}
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
			
			
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function invoke PatientAdmissionTransDAO.setWardTypeDtl(),PatientAdmissionTransDAO.setRoomTypeDtl(),PatientAdmissionTransDAO.getWardValues(vo),PatientAdmissionTransDAO.getRoomValues(vo),PatientAdmissionTransDAO.setBedTypeDtl(vo) to bring data on bed status window
	 * @param vo
	 */
	public void setBedStatusDtl(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.setWardTypeDtl(vo);
			PatientAdmissionCancellationTransDAO.getWardValues(vo);
			PatientAdmissionCancellationTransDAO.setRoomTypeDtl(vo);
			PatientAdmissionCancellationTransDAO.getRoomValues(vo);
			PatientAdmissionCancellationTransDAO.setBedTypeDtl(vo);
			PatientAdmissionCancellationTransDAO.setDeptName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getBedValues(vo) to search beds in a ward and count the no of vacant beds
	 * @param vo
	 */
	public void setBedDetails(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.getBedValues(vo);
			PatientAdmissionCancellationTransDAO.countBed_in_ward(vo);
			PatientAdmissionCancellationTransDAO.getMsApprovalStatus(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getWardValues(vo) to bring details in ward combo on bed details pop up window
	 * @param vo
	 */
	public void setWardDetails(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.getWardValues(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * This function is used to invoke PatientAdmissionTransDAO.getRoomValues(vo) 
	 * @param vo
	 */
	public void setRoomDetails(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.getRoomValues(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void insert(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.insert(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
			
			
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	/**
	 * This function is used to set initials parameters to bring patient address on main page in modifiable  form.it will invoke PatientAdmissionTransDAO's setReligion(),setState(),getPatientAdd().
	 * @param vo
	 */
	public void setPatientAddModi(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.setReligion(vo);
			PatientAdmissionCancellationTransDAO.setState(vo);
			PatientAdmissionCancellationTransDAO.getPatientAdd(vo);
			PatientAdmissionCancellationTransDAO.setCountry(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setPatientAddModi---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void setUnitValue(PatientAdmissionCancellationTransVO vo)
	{
		try
		{
			PatientAdmissionCancellationTransDAO.unit(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransBO.setUnitValue---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
}
