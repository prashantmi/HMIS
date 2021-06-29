package ipd.transactions;

public class PatBelongingTransBO {
	
	/**
	 * set all the belongings of patient through CR no department unit code and ward code.
	 * @param vo
	 */
	public void setPatAdmStatus(PatBelongingTransVO vo)
	{
		try
		{
			PatBelongingTransDAO.setPatAdmCodeDtl(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransBO.setPatAdmStatus---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}	
	
	
	
	/**
	 * set all the belongings of patient through CR no department unit code and ward code.
	 * @param vo
	 */
	public void setPatientblngDtl(PatBelongingTransVO vo)
	{
		try
		{

			PatBelongingTransDAO.setAdmissionDtl(vo);
			PatBelongingTransDAO.setPatBelongingName(vo);
			PatBelongingTransDAO.setPatientblngName(vo);//For Admission Details Don't confuse with belonging
			PatBelongingTransDAO.setRelationList(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransBO.setPatientblngDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	
	}
	/**
	 * set all the belongings of patient through CR no department unit code and ward code.
	 * @param vo
	 */
	public void setPatientblngDtlRow(PatBelongingTransVO vo)
	{
		try
		{
			PatBelongingTransDAO.setPatBelongingRow(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransBO.setPatientblngDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	/**
	 * set all the belongings of patient through CR no department unit code and ward code.
	 * @param vo
	 */
	public void setPatientIssuedItemDtlRow(PatBelongingTransVO vo)
	{
		try
		{
			PatBelongingTransDAO.setPatIssuedItemsRow(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransBO.setPatientblngDtl---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * call DAO class method to insert the patient belonging details
	 * @param vo
	 */
	public void insert(PatBelongingTransVO vo)
	{
		try
		{
			PatBelongingTransDAO.insertpatbelongdtl(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransBO.insert---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	/**
	 * call DAO class method to update the patient belonging details
	 * @param vo
	 */
	public void update(PatBelongingTransVO vo)
	{
		try
		{
			PatBelongingTransDAO.updatepatbelongdtl(vo);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatBelongingTransBO.update---->"+e.getMessage());
			vo.setStrMsgType("1");
		}
	}
}
