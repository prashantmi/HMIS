package new_investigation.transactions.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.InvestigationListingConfig;
import new_investigation.transactions.dao.invListingReportNewDAO;
import new_investigation.vo.template.invListingReportNewVO;

public class InvListingReportNewBO implements InvListingReportNewBOi {
	
	/* Not in Use PrashantMi */
	public Map getInvestigationListingNew(invListingReportNewVO invListingVO, UserVO userVO) 
	{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List invListing=new ArrayList();

			try
			{
				tx.begin();
				invListingReportNewDAO invListingReportDao = new invListingReportNewDAO(tx);
				if (invListingVO.getIsRadioDignoProcess()!=null && invListingVO.getIsRadioDignoProcess().equals("1") ) {
					invListing = invListingReportDao.getinvListingReportRdNew_Client(invListingVO,userVO);
				} else {
					invListing = invListingReportDao.getinvListingReportNew_Client(invListingVO,userVO);
				}
				mp.put(InvestigationListingConfig.LIST_INV_LISTING_REPORT_NEW, invListing);
			
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
			return mp;
	}

	
	public List getInvestigationListingNew_Ajax_Server(invListingReportNewVO invListingVO, UserVO userVO) 
	{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List invListing=new ArrayList();

			try
			{
				tx.begin();
				invListingReportNewDAO invListingReportDao = new invListingReportNewDAO(tx);
				if (invListingVO.getIsRadioDignoProcess()!=null && invListingVO.getIsRadioDignoProcess().equals("1") ) {
					invListingVO.setTotalRow(invListingReportDao.totalRowCountRd(invListingVO,userVO));
					invListing = invListingReportDao.getinvListingReportRdNew_Server(invListingVO,userVO);
				} else {
					invListingVO.setTotalRow(invListingReportDao.totalRowCount(invListingVO,userVO));
					invListing = invListingReportDao.getinvListingReportNew_Server(invListingVO,userVO);
				}
			//	mp.put(InvestigationListingConfig.LIST_INV_LISTING_REPORT_NEW, invListing);
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
			return invListing;
	}

	
	public List getInvestigationListingNew_Ajax_Client(invListingReportNewVO invListingVO, UserVO userVO) 
	{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map mp=new HashMap();
			List invListing=new ArrayList();

			try
			{
				tx.begin();
				invListingReportNewDAO invListingReportDao = new invListingReportNewDAO(tx);
				if (invListingVO.getIsRadioDignoProcess()!=null && invListingVO.getIsRadioDignoProcess().equals("1") ) {
					invListing = invListingReportDao.getinvListingReportRdNew_Client(invListingVO,userVO);
				} else {
					invListing = invListingReportDao.getinvListingReportNew_Client(invListingVO,userVO);

				}
			//	mp.put(InvestigationListingConfig.LIST_INV_LISTING_REPORT_NEW, invListing);
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
			return invListing;
	}

	
}
