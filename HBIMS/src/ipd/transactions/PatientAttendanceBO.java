package ipd.transactions;
/*
 * Patient Attendance BO
 * 
 * author: Debashis Sardar
 * 
 * dated: 16/02/2012 
 */
public class PatientAttendanceBO {
	
	public void getServiceType(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.getServiceType(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.getServiceType---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void getServiceName(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.getServiceName(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.getServiceName---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void getPatientList(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.getPatientList(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.getPatientList---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void getPatientStatusView(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.getPatientStatusView(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.getPatientStatusView---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void department(PatientAttendanceVO VO) {
		
		try 
		{
			
			PatientAttendanceDAO.department(VO);
			if (VO.getStrMsgType().equals("1")) 
			{
				throw new Exception(VO.getStrErrMsgString());
			}
		}
		catch (Exception e) 
		{
			VO.setStrErrMsgString(" PatientAttendanceBO.department() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
			
		}
	}
public void unit(PatientAttendanceVO VO) {
		
		try {
			
			PatientAttendanceDAO.unit(VO);
			if (VO.getStrMsgType().equals("1")) {
				throw new Exception(VO.getStrErrMsgString());
			}
		} catch (Exception e) {
			VO.setStrErrMsgString(" PatientAttendanceBO.unit() --> "
					+ e.getMessage());
			VO.setStrMsgType("1");
			
		}
	}
public void ward(PatientAttendanceVO VO) {
	
	try {
		
		PatientAttendanceDAO.ward(VO);
		if (VO.getStrMsgType().equals("1")) {
			throw new Exception(VO.getStrErrMsgString());
		}
	} catch (Exception e) {
		VO.setStrErrMsgString(" PatientAttendanceBO.ward() --> "
				+ e.getMessage());
		VO.setStrMsgType("1");
		
	}
}

public void room(PatientAttendanceVO VO) {
	
	try {
		
		PatientAttendanceDAO.room(VO);
		if (VO.getStrMsgType().equals("1")) {
			throw new Exception(VO.getStrErrMsgString());
		}
	} catch (Exception e) {
		VO.setStrErrMsgString(" PatientAttendanceBO.room() --> "
				+ e.getMessage());
		VO.setStrMsgType("1");
		
	}
}

	public void accept(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.accept(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.accept---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void reject(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.reject(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.reject---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public void transfer(PatientAttendanceVO  vo)
	{
		try
		{
			PatientAttendanceDAO.transfer(vo);
				
				if(vo.getStrMsgType().equals("1"))
				{
					throw new Exception(vo.getStrMsgString());
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAttendanceBO.transfer---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	public String getBedBlkHrs(String obj) {
		// TODO Auto-generated method stub
		try{
			return (String )PatientAttendanceDAO.getBedBlkHrs(obj);
		}catch(Exception e){e.printStackTrace();return "";}
	}
}
