package ipd.transactions;

public class AdmissionReprintTransBO {



	public void setpatStatusCode(AdmissionReprintTransVO vo)
	{
	 try
		{
			AdmissionReprintTransDAO.setpatAdmnCode(vo);
			AdmissionReprintTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
			

			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("AdmissionReprintTransBO.setpatStatusCode---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
			else
			{
				/*if(vo.getStrIsIntegratedWithBilling().equals("1") && vo.getStrIsAdvanceAmountAtAdmission().equals("1"))
				{
					AdmissionReprintTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
				}*/
				//AdmissionReprintTransDAO.getStatusWhetherAdvanceAmountGiven(vo);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("AdmissionReprintTransBO.setpatStatusCode---->"+e.getMessage());
			vo.setStrMsgType("1");
		}

	}
	
	public void getConsulatant(AdmissionReprintTransVO vo){
		
		 try
			{

				AdmissionReprintTransDAO.setConsultantName(vo);
			 
			   if(vo.getStrMsgType().equals("1"))
				{
					vo.setStrMsgString("AdmissionReprintTransBO.getConsulatant---->"+vo.getStrMsgString());
					vo.setStrMsgType("1");
				}
			}
			catch(Exception e)
			{
				vo.setStrMsgString("AdmissionReprintTransBO.getConsulatant---->"+e.getMessage());
				vo.setStrMsgType("1");
			}
		
	
		
	}
	
	public void visitPass(AdmissionReprintTransVO vo)
	{
		 try
			{
			 AdmissionReprintTransDAO.setvisitorPassdtl(vo);
			 
			   if(vo.getStrMsgType().equals("1"))
				{
					vo.setStrMsgString("AdmissionReprintTransBO.visitPass---->"+vo.getStrMsgString());
					vo.setStrMsgType("1");
				}
			}
			catch(Exception e)
			{
				vo.setStrMsgString("AdmissionReprintTransBO.visitPass---->"+e.getMessage());
				vo.setStrMsgType("1");
			}
	}
	public void insert(AdmissionReprintTransVO vo)
	{
		try
		{
			AdmissionReprintTransDAO.insert(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrErrMsgString("AdmissionReprintTransBO.insert---->"+vo.getStrErrMsgString());
				vo.setStrMsgType("1");

			}
		}
		catch(Exception e)
		{
			vo.setStrErrMsgString("AdmissionReprintTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public void admissionList(AdmissionReprintTransVO vo)
	{
		try
		{
			AdmissionReprintTransDAO.admissionList(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("AdmissionReprintTransBO.setpatStatusCode---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("AdmissionReprintTransBO.setpatStatusCode---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
}
