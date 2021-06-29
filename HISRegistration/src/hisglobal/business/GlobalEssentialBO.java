package hisglobal.business;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;


public class GlobalEssentialBO
{
	public HospitalMstVO getHospitalDetail(String hospitalCode) {
		JDBCTransactionContext tx = new JDBCTransactionContext();
		HospitalMstVO hospitalMstVO=new HospitalMstVO();
		try
		{
			tx.begin();
			GlobalEssentialDAO dao = new GlobalEssentialDAO(tx);
			hospitalMstVO = dao.getHospitalDetail(hospitalCode);
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println("HisDataAccessException:: " + e);
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println("HisApplicationExecutionException:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			System.out.println("Exception:: " + e);
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return hospitalMstVO;
	}
	public List getSystemDateAndFormat(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List dateAndFormat = new ArrayList();
		try
		{
			tx.begin();
			GlobalEssentialDAO dao = new GlobalEssentialDAO(tx);
			dateAndFormat = dao.getSystemDateAndFormat(_userVO);
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			e.printStackTrace();
			//throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return dateAndFormat;
	}

	
}
