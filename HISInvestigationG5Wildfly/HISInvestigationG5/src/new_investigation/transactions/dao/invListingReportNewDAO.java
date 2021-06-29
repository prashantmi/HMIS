package new_investigation.transactions.dao;

import java.util.List;

import new_investigation.vo.template.invListingReportNewVO;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import new_investigation.InvestigationConfig;
import new_investigation.InvestigationListingConfig;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;

public class invListingReportNewDAO extends DataAccessObject{

		public invListingReportNewDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}

		public String totalRowCount(invListingReportNewVO invListingVO,UserVO userVO) {
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;

			String count=null;
			String filename= InvestigationListingConfig.QUERY_FILE_FOR_INVESTIGATION_LISTING;
			String queryKey="SELECT_INV_LISTING_REPORT_NEW.HIVT_REQUISITION_DTL_COUNT";
			String queryKey_sys="SELECT_INV_LISTING_REPORT_NEW.HIVT_REQUISITION_DTL_SYSDATE_WISE_COUNT";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					query = HelperMethodsDAO.getQuery(filename,queryKey_sys);
				}else{
					query = HelperMethodsDAO.getQuery(filename,queryKey);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals("")){
				}else{
					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate
				}

				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					rs = HelperMethodsDAO.executeQuery(conn, query);
				}else{
					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				}


				if (!rs.next())
				{
					return count;
				}
				else
				{
					count=rs.getString(1);
				}

			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();
				}
				else
				 throw new HisDataAccessException("HisDataAccessException:: "+e);
			 }


			return count;
		}


		public List getinvListingReportNew_Server(invListingReportNewVO invListingVO,UserVO userVO) {
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List invListing=new ArrayList();
			String filename= InvestigationListingConfig.QUERY_FILE_FOR_INVESTIGATION_LISTING;
			String queryKey="SELECT_INV_LISTING_REPORT_NEW.HIVT_REQUISITION_DTL";
			String queryKey_sys="SELECT_INV_LISTING_REPORT_NEW.HIVT_REQUISITION_DTL_SYSDATE_WISE";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					query = HelperMethodsDAO.getQuery(filename,queryKey_sys);
				}else{
					query = HelperMethodsDAO.getQuery(filename,queryKey);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals("")){
					query+=" LIMIT "+invListingVO.getRowLimit()+" OFFSET "+invListingVO.getStartIndex();
				}else{
					query+=" LIMIT "+invListingVO.getRowLimit()+" OFFSET "+invListingVO.getStartIndex();

					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate

				}

				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					rs = HelperMethodsDAO.executeQuery(conn, query,populateMap);
				}else{
					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				}
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					while(rs.next()){
						invListingReportNewVO listingVO = new invListingReportNewVO();
						listingVO.setTotalRow(invListingVO.getTotalRow());
						HelperMethods.populateVOfrmRS(listingVO, rs);
						invListing.add(listingVO);
					}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();
				}
				else
				 throw new HisDataAccessException("HisDataAccessException:: "+e);
			 }


			return invListing;
		}


		public String totalRowCountRd(invListingReportNewVO invListingVO,UserVO userVO) {
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String count=null;
			String filename= InvestigationListingConfig.QUERY_FILE_FOR_INVESTIGATION_LISTING;
			String queryKey="SELECT_INV_LISTING_REPORT_RD_NEW.HIVT_REQUISITION_DTL_COUNT";
			String queryKey_sys="SELECT_INV_LISTING_REPORT_RD_NEW.HIVT_REQUISITION_DTL_SYSDATE_WISE_COUNT";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					query = HelperMethodsDAO.getQuery(filename,queryKey_sys);
				}else{
					query = HelperMethodsDAO.getQuery(filename,queryKey);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals("")){

				}else{
					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate

				}

				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					rs = HelperMethodsDAO.executeQuery(conn, query,populateMap);
				}else{
					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				}
				if (!rs.next())
				{
					return count;
				}
				else
				{
					count=rs.getString(1);
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();
				}
				else
				 throw new HisDataAccessException("HisDataAccessException:: "+e);
			 }


			return count;
		}


		public List getinvListingReportRdNew_Server(invListingReportNewVO invListingVO,UserVO userVO) {
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List invListing=new ArrayList();
			String filename= InvestigationListingConfig.QUERY_FILE_FOR_INVESTIGATION_LISTING;
			String queryKey="SELECT_INV_LISTING_REPORT_RD_NEW.HIVT_REQUISITION_DTL";
			String queryKey_sys="SELECT_INV_LISTING_REPORT_RD_NEW.HIVT_REQUISITION_DTL_SYSDATE_WISE";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					query = HelperMethodsDAO.getQuery(filename,queryKey_sys);
				}else{
					query = HelperMethodsDAO.getQuery(filename,queryKey);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals("")){
					query+=" LIMIT "+invListingVO.getRowLimit()+" OFFSET "+invListingVO.getStartIndex();
				}else{
					query+=" LIMIT "+invListingVO.getRowLimit()+" OFFSET "+invListingVO.getStartIndex();

					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate

				}

				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					rs = HelperMethodsDAO.executeQuery(conn, query);
				}else{
					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				}
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					while(rs.next()){
						invListingReportNewVO listingVO = new invListingReportNewVO();
						listingVO.setTotalRow(invListingVO.getTotalRow());
						HelperMethods.populateVOfrmRS(listingVO, rs);
						invListing.add(listingVO);
					}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();
				}
				else
				 throw new HisDataAccessException("HisDataAccessException:: "+e);
			 }


			return invListing;
		}

		public List getinvListingReportRdNew_Client(invListingReportNewVO invListingVO,UserVO userVO) {
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List invListing=new ArrayList();
			String filename= InvestigationListingConfig.QUERY_FILE_FOR_INVESTIGATION_LISTING;
			String queryKey="SELECT_INV_LISTING_REPORT_RD_NEW.HIVT_REQUISITION_DTL";
			String queryKey_sys="SELECT_INV_LISTING_REPORT_RD_NEW.HIVT_REQUISITION_DTL_SYSDATE_WISE";
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					query = HelperMethodsDAO.getQuery(filename,queryKey_sys);
				}else{
					query = HelperMethodsDAO.getQuery(filename,queryKey);
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals("")){
				}else{
					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate

				/*
				 * populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
				 * populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate
				 */
				}

				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					rs = HelperMethodsDAO.executeQuery(conn, query);
				}else{
					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				}
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					while(rs.next()){
						invListingReportNewVO listingVO = new invListingReportNewVO();
						listingVO.setTotalRow(invListingVO.getTotalRow());
						HelperMethods.populateVOfrmRS(listingVO, rs);
						invListing.add(listingVO);
					}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();
				}
				else
				 throw new HisDataAccessException("HisDataAccessException:: "+e);
			 }


			return invListing;
		}

		public List getinvListingReportNew_Client(invListingReportNewVO invListingVO,UserVO userVO) {
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List invListing=new ArrayList();
			String filename= InvestigationListingConfig.QUERY_FILE_FOR_INVESTIGATION_LISTING;
			String queryKey1="SELECT_INV_LISTING_REPORT_NEW.HIVT_REQUISITION_DTL";
			String queryKey_sys1="SELECT_INV_LISTING_REPORT_NEW.HIVT_REQUISITION_DTL_SYSDATE_WISE";

			String queryKey2="SELECT_INV_LISTING_REPORT_NEW.BYSAMPLE_COLL_DATE.HIVT_REQUISITION_DTL";
			String queryKey_sys2="SELECT_INV_LISTING_REPORT_NEW.BYSAMPLE_COLL_DATE.HIVT_REQUISITION_DTL_SYSDATE_WISE";

			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();

			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					if(invListingVO.getSearchDateType().equals("1")){
						query = HelperMethodsDAO.getQuery(filename,queryKey_sys1);
					}else if (invListingVO.getSearchDateType().equals("2")){
						query = HelperMethodsDAO.getQuery(filename,queryKey_sys2);
					}
				}else{
					if(invListingVO.getSearchDateType().equals("1")){
						query = HelperMethodsDAO.getQuery(filename,queryKey1);
					}else if (invListingVO.getSearchDateType().equals("2")){
						query = HelperMethodsDAO.getQuery(filename,queryKey2);
					}
				}
			}
			catch(Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
			}
			try
			{
				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals("")){
				}else{
					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate

//					populateMap.put(sq.next(),invListingVO.getFromDate());//fromdate
//					populateMap.put(sq.next(), invListingVO.getToDate().toString());//todate


				}

				if(invListingVO.getFromDate()==null || invListingVO.getFromDate().equals("") ||
						invListingVO.getToDate()==null || invListingVO.getToDate().equals(""))
				{
					rs = HelperMethodsDAO.executeQuery(conn, query,populateMap);
				}else{
					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				}
				if (!rs.next())
				{
					throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					while(rs.next()){
						invListingReportNewVO listingVO = new invListingReportNewVO();
						listingVO.setTotalRow(invListingVO.getTotalRow());
						HelperMethods.populateVOfrmRS(listingVO, rs);
						invListing.add(listingVO);
					}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException();
				}
				else
				 throw new HisDataAccessException("HisDataAccessException:: "+e);
			 }


			return invListing;
		}
}
