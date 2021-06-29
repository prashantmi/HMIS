package ipd.transactions;



public class PatientTrackingTransBO {

	public void setMovementDetail(PatientTrackingTransVO vo)
	{
		try
		{
			PatientTrackingTransDAO.setMovementDetail(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("PatientTrackingTransBO.setMovementDetail---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientTrackingTransBO.setMovementDetail---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void setpatStatusCode(PatientTrackingTransVO vo)
	{
	 try
		{
			PatientTrackingTransDAO.setpatStatusCode(vo);
			//PatientTrackingTransDAO.setpatAdmnCode(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("PatientTrackingTransBO.setpatStatusCode---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientTrackingTransBO.setpatStatusCode---->"+e.getMessage());
			vo.setStrMsgType("1");
		}

	}
	public void admissionList(PatientTrackingTransVO vo)
	{
		try
		{
			PatientTrackingTransDAO.admissionList(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("PatientTrackingTransBO.setMovementDetail---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientTrackingTransBO.setMovementDetail---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
}
