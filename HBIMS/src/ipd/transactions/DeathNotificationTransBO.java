package ipd.transactions;

public class DeathNotificationTransBO {
	
	/**
	 * set all the belongings of patient through CR no department unit code and ward code.
	 * @param vo
	 */
	public void setPatAdmStatus(DeathNotificationTransVO vo)
	{
		try
		{
			DeathNotificationTransDAO.setPatAdmCodeDtl(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("DeathNotificationTransBO.setPatAdmStatus---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}	
	
	
	/**
	 * 
	 * @param voObj
	 */
	public void setRequiredValues(DeathNotificationTransVO voObj) {

		try {
				DeathNotificationTransDAO.setPatAdmDtl(voObj);
				DeathNotificationTransDAO.setConsultantNameDtl(voObj);
				DeathNotificationTransDAO.setDeathMannerDtl(voObj);
				DeathNotificationTransDAO.setDeathCauseDtl(voObj);
				if(voObj.getStrMsgType().equals("1"))
				{
					throw new Exception(voObj.getStrMsgString());
				}
				

		} catch (Exception e) {

			voObj.setStrMsgString("DeathNotificationTransBO.setRequiredValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		}

	}
	public  void insert(DeathNotificationTransVO vo)
	{
		try
		{
			DeathNotificationTransDAO.insert(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("DeathNotificationTransBO.insert() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	public  void isPregnant(DeathNotificationTransVO vo)
	{
		try
		{
			DeathNotificationTransDAO.isPregnant(vo);
			if(vo.getStrMsgType().equals("1"))
			{
				throw new Exception(vo.getStrMsgString());
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("DeathNotificationTransBO.isPregnant() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

}
