package ipd.transactions;

import ipd.IpdConfigUtil;

public class DischargeCancelTransBO {

	/*public void setPatientDtl(DischargeCancelTransVO vo)
	{
		try
		{
			DischargeCancelTransDAO.setPatientName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				System.out.println("vo.getStrMsgString()"+vo.getStrMsgString());
				vo.setStrMsgString("VisitorPassTransBO.setvisitPatientDtl---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("VisitorPassTransBO.setvisitPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}

	}*/

	public void getRsnRmk(DischargeCancelTransVO vo)
	{
		try
		 {
			DischargeCancelTransDAO Dao = new DischargeCancelTransDAO();
		    Dao.getRsnRmk(vo);
		    if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("DischargeCancelTransBO.getRsnRmk---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
	     }
		catch(Exception e)
		{
			vo.setStrMsgString("DischargeCancelTransBO.getRsnRmk---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public void setBedStatusDtl(DischargeCancelTransVO vo)
	{
		try
		{
			DischargeCancelTransDAO.setRoomTypeDtl(vo);
			DischargeCancelTransDAO.setBedTypeDtl(vo);

			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("DischargeCancelTransBO.setBedStatusDtl---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
	     }
		catch(Exception e)
		{
			vo.setStrMsgString("DischargeCancelTransBO.setBedStatusDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	
//call dao function for room combo

	public void room(DischargeCancelTransVO vo)
	{
		IpdConfigUtil ipdC=new IpdConfigUtil(vo.getStrHospitalCode());
		try
		{
			/* Changed By Amit Kumar Ateria on 19 Jan 2011 to check MS Approval for Private Ward*/
			if(vo.getStrWardTypeCode().equals(ipdC.getStrPrivateWardType()) && vo.getStrMsApprovalFlag().equals("1"))
			{
		      	DischargeCancelTransDAO.getPatDtl_Msapproval(vo);
			}
			else
			{
				DischargeCancelTransDAO.getRoomValues(vo);
			}
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("DischargeCancelTransBO.room---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		} catch (Exception e) {
			vo.setStrMsgString(" DischargeCancelTransBO.room() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

public void bed(DischargeCancelTransVO vo) {

		try {
			DischargeCancelTransDAO.getBed(vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("DischargeCancelTransBO.bed---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		} catch (Exception e) {
			vo.setStrMsgString(" DischargeCancelTransBO.bed() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

public void setBedDetails(DischargeCancelTransVO vo)
{
	try
	{
		DischargeCancelTransDAO.getBedValues(vo);
		if(vo.getStrMsgType().equals("1"))
		{
			vo.setStrMsgString("DischargeCancelTransBO.setBedDetails---->"+vo.getStrMsgString());
			vo.setStrMsgType("1");
		}
	}
	catch(Exception e)
	{
		vo.setStrMsgString("DischargeCancelTransBO.setBedStatusDtl---->"+e.getMessage());
		vo.setStrMsgType("1");
	}

}

	public void setpatStatusCode(DischargeCancelTransVO vo)
	{
	 try
		{
			DischargeCancelTransDAO.setpatStatusCode(vo);
			//DischargeCancelTransDAO.setpatAdmnCode(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("DischargeCancelTransBO.setpatStatusCode---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("DischargeCancelTransBO.setpatStatusCode---->"+e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	public void insert(DischargeCancelTransVO vo)
	{
		try
		{
			DischargeCancelTransDAO.insertdischargecancel(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrErrMsgString("DischargeCancelTransBO.insert---->"+vo.getStrErrMsgString());
				vo.setStrMsgType("1");

			}
		}
		catch(Exception e)
		{
			vo.setStrErrMsgString("DischargeCancelTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}

}
