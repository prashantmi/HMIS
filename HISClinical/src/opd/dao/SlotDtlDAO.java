package opd.dao;

//import java.sql.ResultSet;
//import java.util.HashMap;

//import opd.OpdConfig;

import hisglobal.persistence.TransactionContext;
//import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.DataAccessObject;
//import hisglobal.persistence.HelperMethodsDAO;
//import hisglobal.utility.Sequence;
//import hisglobal.vo.Apt_appointmentDtlVO;
//import hisglobal.vo.Apt_slotDtlVO;
//import hisglobal.vo.UserVO;

public class SlotDtlDAO extends DataAccessObject
{

	private static Object lock = new Object();

	public SlotDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public Object getLock()
	{
		return lock;
	}

	/*public void UpdateSlotAptPersonDtl(Apt_appointmentDtlVO _appointmentDtlVO, Apt_slotDtlVO _slotDtlVO, UserVO userVO)
	{
		HashMap _populateMap = new HashMap();
		//ResultSet rs = null;
		//String query = "";
		//String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		//String queryKey = "UPDATE.SLOT.APPOINTMENT.PERSONS.HAPT_SLOT_DTL";
		int aptPerson = Integer.parseInt(getSlotAptPersonDtl(_appointmentDtlVO, _slotDtlVO));

		aptPerson += 1;
		try
		{
			//query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), aptPerson + "");
		//_populateMap.put(sq.next(), _slotDtlVO.getAptActCode());
		_populateMap.put(sq.next(), _slotDtlVO.getSlotCode());
		_populateMap.put(sq.next(), _slotDtlVO.getSlotDate());
		try
		{
			//int statusexecute = HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, _populateMap);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
	}

	public String getSlotAptPersonDtl(Apt_appointmentDtlVO _appointmentDtlVO, Apt_slotDtlVO _slotDtlVO)
	{
		HashMap _populateMap = new HashMap();
		ResultSet rs = null;
		String aptPersons = "";
		String query = "";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.SLOT.APPOINTMENT.PERSONS.HAPT_SLOT_DTL";
		Sequence sq = new Sequence();
		//_populateMap.put(sq.next(), _slotDtlVO.getAptActCode());
		_populateMap.put(sq.next(), _slotDtlVO.getSlotCode());
		_populateMap.put(sq.next(), _slotDtlVO.getSlotDate());

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		try
		{
			rs = (ResultSet) HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);

			if (rs.first())
			{
				aptPersons = rs.getString(1);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		if (aptPersons != null && aptPersons == "") aptPersons = "0";
		return aptPersons;
	}*/

}
