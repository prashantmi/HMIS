package ipd.transactions;

public class VisitorPassTransBO {
	
	public void setvisitPatientDtl(VisitorPassTransVO vo)
	{
		try
		{
			VisitorPassTransDAO.setvisitPatientName(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("VisitorPassTransBO.setvisitPatientDtl---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
			else{
				VisitorPassTransDAO.setvisitorcount(vo);
				if(vo.getStrMsgType().equals("1"))
				{
				vo.setStrMsgString("VisitorPassTransBO.setvisitPatientDtl---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
				}
			else{
				vo.setStrRemainderFreePass(Integer.parseInt
					(vo.getStrNoFreePass()) - Integer.parseInt(vo.getStrcountFree()));
			
				vo.setStrRemainderPaidPass(Integer.parseInt
					(vo.getStrNoPaidPass())-Integer.parseInt(vo.getStrcountPass()));
				}
			}
			
		}
			catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransBO.setvisitPatientDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
		
	public void insert(VisitorPassTransVO vo)
	{
		try
		{
			VisitorPassTransDAO.insertvisitorpassdtl(vo);
		
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("VisitorPassTransBO.insert---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
				
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	
	public void getVisitorPassDtl(VisitorPassTransVO vo)
	{
		try
		{
			
			VisitorPassTransDAO.getVisitorPassDtl(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("VisitorPassTransBO.getVisitorPassDtl---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
				
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransBO.getVisitorPassDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	public void setvisitorPassdtl(VisitorPassTransVO vo)
	{
		try
		{
			VisitorPassTransDAO.setvisitPatientName(vo);  
			VisitorPassTransDAO.setvisitorPassdtl(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("VisitorPassTransBO.setvisitorPassdtl()"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransBO.setvisitorPassdtl()->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	
	public void setvisitorcount(VisitorPassTransVO vo)
	{
		try
		{
			VisitorPassTransDAO.setvisitorcount(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("VisitorPassTransBO.setvisitorcount---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransBO.setvisitorcount---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
		
	}
	
	public void setpatStatusCode(VisitorPassTransVO vo)
	{
		try
		{
			VisitorPassTransDAO.setpatStatusCode(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrMsgString("VisitorPassTransBO.setpatStatusCode---->"+vo.getStrMsgString());
				vo.setStrMsgType("1");
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("VisitorPassTransBO.setpatStatusCode---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
		
	}
	
}
