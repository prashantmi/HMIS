package new_investigation.reports.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.UserVO;
import new_investigation.reports.dao.InvTrackingReportDAO;
import new_investigation.vo.InvTrackingReportVO;

@SuppressWarnings( "rawtypes")
public class InvTrackingReportBO implements InvTrackingReportBOI{
	
	
	public Map AjaxGetPatDetails(InvTrackingReportVO vo, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, InvTrackingReportVO> mp = new HashMap<String, InvTrackingReportVO>();
		InvTrackingReportVO vo2 = new InvTrackingReportVO();
		
		try {
			tx.begin();
			InvTrackingReportDAO invTrackingReportDAO = new InvTrackingReportDAO(tx);
			vo2=invTrackingReportDAO.AjaxGetPatDetails(vo, userVO);
			
			mp.put("patDetails", vo2);
		}
		catch (HisRecordNotFoundException e)
		{
			
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

		return mp;
	}
	

	public Map AjaxGetPatReqnList(InvTrackingReportVO vo, UserVO userVO) { 
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, List<InvTrackingReportVO>> mp = new HashMap<String, List<InvTrackingReportVO>>();
		List<InvTrackingReportVO> sampleBasedReqnListInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
		List<InvTrackingReportVO> patientBasedReqnListInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
		
		try {
			tx.begin();
			
			InvTrackingReportDAO invTrackingReportDAO = new InvTrackingReportDAO(tx);
			sampleBasedReqnListInvTrackingReportVO = invTrackingReportDAO.AjaxGetPatSampleReqnList(vo, userVO);
			patientBasedReqnListInvTrackingReportVO = invTrackingReportDAO.AjaxGetPatPatientReqnList(vo, userVO);
			
			mp.put("sampleBasedReqnList", sampleBasedReqnListInvTrackingReportVO);
			mp.put("patientBasedReqnList", patientBasedReqnListInvTrackingReportVO);
		}
		catch (HisRecordNotFoundException e)
		{
			
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

		return mp;
	}
	
	
	public Map AjaxGetReqnTestParamList(InvTrackingReportVO vo, UserVO userVO) { 
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, List<InvTrackingReportVO>> mp = new HashMap<String, List<InvTrackingReportVO>>();
		List<InvTrackingReportVO> reqnTestParamListInvTrackingReportVO = new ArrayList<InvTrackingReportVO>();
		
		try {
			tx.begin();
			
			InvTrackingReportDAO invTrackingReportDAO = new InvTrackingReportDAO(tx);
			reqnTestParamListInvTrackingReportVO = invTrackingReportDAO.AjaxGetReqnTestParamList(vo, userVO);
			
			mp.put("reqnTestParamList", reqnTestParamListInvTrackingReportVO);
		}
		catch (HisRecordNotFoundException e)
		{
			
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

		return mp;
	}
	
	
	public Map GetTestTurnAroundTime(InvTrackingReportVO vo, UserVO userVO) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, String> mp = new HashMap<String, String>();
		
		try {
			tx.begin();
			
			InvTrackingReportDAO invTrackingReportDAO = new InvTrackingReportDAO(tx);
			mp = invTrackingReportDAO.GetTestTurnAroundTime(vo, userVO);
			
		}
		catch (HisRecordNotFoundException e)
		{
			
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			
			System.out.println(e.getMessage());
			throw new HisException();
		}
		catch (Exception e)
		{
			
			System.out.println(e.getMessage());
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}

		return mp;
	}
	
	public String  isfromFTPorMONGO( UserVO _UserVO)
{
	JDBCTransactionContext tx = new JDBCTransactionContext();
	Map mp=new HashMap();
	String labcombo="";

	try
	{
		tx.begin();
		InvTrackingReportDAO invTrackingReportDAO = new InvTrackingReportDAO(tx);

		labcombo=invTrackingReportDAO.isfromFTPorMONGO(_UserVO);
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
	return labcombo;
}	
}
