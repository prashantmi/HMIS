package webServiceReport.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import webServiceReport.dao.FetchReportDAO;

public class FetchReportBO implements FetchReportBOi{
	
	
	//report webservice
	public String  getReportFileName(String crNo,String reqNo,String reqDno,String hosCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
          String filename="";
		try
		{
			tx.begin();
			FetchReportDAO onlinePatientDao = new FetchReportDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			filename=onlinePatientDao.getReportFileName(crNo, reqNo, reqDno, hosCode);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return filename;
	}
	
	
	public String  getPortalVal(String hosCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
          String isPortal="";
		try
		{
			tx.begin();
			FetchReportDAO onlinePatientDao = new FetchReportDAO(tx);
			//labMstDAOi.fetchLab(labMasterVO, _UserVO);

			isPortal=onlinePatientDao.getPortalVal(hosCode);


		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return isPortal;
	}

}
