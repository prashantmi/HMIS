package uploadfilesonserver;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.BloodBagDtlVO;

import hisglobal.vo.BloodBankMasterVO;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.BloodRequisitionDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class BloodRequisitionDtlDAO extends DataAccessObject implements BloodRequisitionDtlDAOi
{
	
	/**
	 * Creates a new BloodRequistionDtlDAO object.
	 * 
	 * @param _transactionContext Provides the lock on a transaction.
	 */
	public BloodRequisitionDtlDAO(TransactionContext _tx)
	{
		super(_tx);
		
	}
	
	/**
	 * Creates a new Requisition Detail  entry in DB for a Patient. 
	 * @param _bldReqDtlVO	Provides Donor Requisition details to be entered.
	 * @param _userVO	Provides User details.
	 */
	
		//Added by Hemant for fetching path data
	public String getPathData(UserVO userVO)
	{
		BloodRequisitionDtlVO[] bloodRequisitionDtlVOs=null;
		ValueObject[] vo=null;
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= "uploadfilesonserver.bloodBankTransactionQuery";
		String queryKey="SELECT_PATH.HIVT_STANDARDS_MST";
		Sequence sq = new Sequence();
		String Path = "";
		Connection conn=super.getTransactionContext().getConnection();
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMap.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodRequistionDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			while(rs.next())
				Path =  rs.getString(1);
			
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }
		return Path;
	}
//Added bY hemant for saving fileUploaded Data
	public void saveDetailsOfFile(FileUploadedVO VO, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = "uploadfilesonserver.bloodBankTransactionQuery";
		String queryKey ="INSERT.HIVT_DICOM_UPLOAD_DTL";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			//populateMAP.put(sq.next(),"1");
			populateMAP.put(sq.next(),VO.getFileName());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),VO.getCrno());
			populateMAP.put(sq.next(),VO.getStoragePath());
				
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While ADDING");
		}

	}
	
 }

	
	


