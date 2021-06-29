package mortuary.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;

public class DeceasedDetailDAO extends DataAccessObject implements DeceasedDetailDAOi
{
	public DeceasedDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Generating Deceased No
	public String generateDeceasedNo(UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="GENERATE_DECEASED_NO.HMRT_DECEASED_DTL";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	//Inserting Record
	public void create(DeceasedDetailVO deceasedDtlVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_DTL";
	    
	   
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), deceasedDtlVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatCrNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getEntryMode());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatFirstName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatMiddleName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatLastName());
	    	populateMAP.put(sq.next(), userVO.getUserEmpID());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatGenderCode());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getDeathDate());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getBodyStatus());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatDOB());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIsActualDob());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getStorageUpto());//storage upto
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIssueDateTime());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatMaritalStatusCode());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getUnidentifiedBody());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatGuardianName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIsBroughtDead());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatHusbandName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getAdd1());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIsMlcCase());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getAdd2());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getContactNo());//CONTACT NO
	    	populateMAP.put(sq.next(), deceasedDtlVO.getDiagnosis());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getDeathHistory());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIsPregnant());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getObstetricHistory());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatMotherName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatIdMark1());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPatIdMark2());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getEpisodeCode());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getEpisodeVisitNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIsDecomposition());
	    	
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtHospitalName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtDeptName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtPatCrNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtPatAdmNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtUnitName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtWardName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtBedNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getDoctorName());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getExtHospitalContactNo());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getIsClaimed());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getPostmortemReq());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getItemDesc());
	    	populateMAP.put(sq.next(), deceasedDtlVO.getRemarks());
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedAcceptanceDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HisDataAccessException While ADDING");
	    }
	}
	
	public void updatePostmortemReq(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_POSTMORTEM_REQ.HMRT_DECEASED_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_REQUEST_WAVEOFF);
		populateMAP.put(sq.next(), postmortemWaveoffDtlVO.getDeceasedNo());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public DeceasedDetailVO getDeceasedDtlByDeceasedNo(String deceasedNo,UserVO userVO)
	{
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_DECEASED_DETAIL_BY_DECEASED_NO.HMRT_DECEASED_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedDtlVO,rs);
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
		return deceasedDtlVO;
	}
	
	public void updateUnknownToKnownDetail(DeceasedDetailVO deceasedDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_UNKNOWN_TO_KNOWN_DETAIL.HMRT_DECEASED_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), deceasedDtlVO.getPatFirstName());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatMiddleName());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatLastName());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatGenderCode());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatDOB());
		populateMAP.put(sq.next(), deceasedDtlVO.getIsActualDob());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatMaritalStatusCode());
		populateMAP.put(sq.next(), deceasedDtlVO.getUnidentifiedBody());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatGuardianName());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatHusbandName());
		populateMAP.put(sq.next(), deceasedDtlVO.getAdd1());
		populateMAP.put(sq.next(), deceasedDtlVO.getAdd2());
		populateMAP.put(sq.next(), deceasedDtlVO.getContactNo());
		populateMAP.put(sq.next(), deceasedDtlVO.getPatMotherName());
		populateMAP.put(sq.next(), deceasedDtlVO.getIsClaimed());
		
		populateMAP.put(sq.next(), deceasedDtlVO.getDeceasedNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void updateDeceasedClaimedStatus(DeceasedDetailVO deceasedDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_IS_CLAIMED_FLAG.HMRT_DECEASED_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), deceasedDtlVO.getIsClaimed());
		populateMAP.put(sq.next(), deceasedDtlVO.getDeceasedNo());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public void updateDeceasedGeneralAppearance(DeceasedDetailVO deceasedDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "UPDATE_GENERAL_APPEARANCE.HMRT_DECEASED_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), deceasedDtlVO.getComplexion());
		populateMAP.put(sq.next(), deceasedDtlVO.getHairColorLength());
		populateMAP.put(sq.next(), deceasedDtlVO.getEyeColor());
		populateMAP.put(sq.next(), deceasedDtlVO.getLength());
		populateMAP.put(sq.next(), deceasedDtlVO.getWeight());
		populateMAP.put(sq.next(), deceasedDtlVO.getClothDetails());
		populateMAP.put(sq.next(), deceasedDtlVO.getBodyLooks());
		populateMAP.put(sq.next(), deceasedDtlVO.getIsDecomposition());
		populateMAP.put(sq.next(), deceasedDtlVO.getDeceasedNo());
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
	}
	
	public DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO)
	{
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_DECEASED_GENERL_APPEARANCE_BY_DECEASED_NO.HMRT_DECEASED_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedDtlVO,rs);
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
		return deceasedDtlVO;
	}
	
	public DeceasedDetailVO[] searchDeceasedNo(String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		

		try
		{
			query="SELECT A.HMRNUM_DECEASED_NO AS deceasedNo, A.HMRSTR_FNAME AS patFirstName, A.HMRSTR_MNAME AS patFirstName, "
					+"A.HMRSTR_LNAME AS patLastName, A.GNUM_GENDER_CODE AS patGenderCode, TO_CHAR(A.HMRDT_DEATH_DATETIME,'dd-Mon-yyyy HH24:MI') AS deathDate, "
					+"A.HMRSTR_ADD1 AS add1, A.HMRSTR_ADD2 AS add2, AHIS_UTIL.DOB_AGE_ON(A.HMRDT_DOB,SYSDATE) as patAge, "
					+"(SELECT C.GSTR_GENDER_NAME FROM GBLT_GENDER_MST C WHERE C.GSTR_GENDER_CODE =A.GNUM_GENDER_CODE  AND C.GNUM_ISVALID = A.GNUM_ISVALID) as patGender "
					+"FROM HMRT_DECEASED_DTL A WHERE A.GNUM_ISVALID = ? AND A.GNUM_HOSPITAL_CODE = ? ";
			
			
			if(fName!=null && !fName.equals(""))
				query=query+" AND UPPER(HMRSTR_FNAME) LIKE UPPER('"+fName+"%') ";
			if(mName!=null && !mName.equals(""))
				query=query+" AND UPPER(HMRSTR_MNAME) LIKE UPPER('"+mName+"%') ";
			if(lName!=null && !lName.equals(""))
				query=query+" AND UPPER(HMRSTR_LNAME) LIKE UPPER('"+lName+"%') ";
			if(deathDate!=null && !deathDate.equals(""))
				query=query+" AND TRUNC(HMRDT_DEATH_DATETIME) =TO_DATE('"+deathDate+"','dd-Mon-yyyy') ";
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//arrDeceasedDtlVO = null;
				throw new HisRecordNotFoundException("No Record Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedDtlVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedDtlVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return arrDeceasedDtlVO;
	}
	
	public DeceasedDetailVO[] searchPostmortemNo(String decNo, String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		

		try
		{
			query="SELECT A.HMRNUM_POSTMORTEM_ID AS postmortemId, TO_CHAR(HMRDT_REQ_DATETIME,'dd-Mon-yyyy hh24:mi') AS requestDateTime,"
					+"A.HMRNUM_DECEASED_NO AS deceasedNo,B.HMRSTR_FNAME AS patFirstName, "
					+"B.HMRSTR_MNAME AS patMiddleName, B.HMRSTR_LNAME AS patLastName, B.GNUM_GENDER_CODE AS patGenderCode, "
					+"TO_CHAR(B.HMRDT_DEATH_DATETIME,'dd-Mon-yyyy HH24:MI') AS deathDate,AHIS_UTIL.DOB_AGE_ON(B.HMRDT_DOB,SYSDATE) AS patAge, "
					+"DECODE(b.gnum_gender_code,1,'M',2,'F','O') AS patGender " 
					+"FROM HMRT_POSTMORTEM_REQ_DTL A, HMRT_DECEASED_DTL B "
					+"WHERE A.HMRNUM_DECEASED_NO=B.HMRNUM_DECEASED_NO "
					+"AND A.GNUM_ISVALID = B.GNUM_ISVALID AND A.GNUM_HOSPITAL_CODE = B.GNUM_HOSPITAL_CODE "
					+"AND B.GNUM_ISVALID = ? AND B.GNUM_HOSPITAL_CODE = ? " 
					+"AND A.HRGNUM_SRNO=(SELECT MAX(C.HRGNUM_SRNO) FROM HMRT_POSTMORTEM_REQ_DTL C WHERE C.HMRNUM_POSTMORTEM_ID=A.HMRNUM_POSTMORTEM_ID) "
					+"AND A.HMRNUM_POSTMORTEM_STATUS IN (?,?,?) ";
			
			
			if(decNo!=null && !decNo.equals(""))
				query=query+" AND A.HMRNUM_DECEASED_NO = "+decNo + " ";
			if(fName!=null && !fName.equals(""))
				query=query+" AND UPPER(B.HMRSTR_FNAME) LIKE UPPER('"+fName+"%') ";
			if(mName!=null && !mName.equals(""))
				query=query+" AND UPPER(B.HMRSTR_MNAME) LIKE UPPER('"+mName+"%') ";
			if(lName!=null && !lName.equals(""))
				query=query+" AND UPPER(B.HMRSTR_LNAME) LIKE UPPER('"+lName+"%') ";
			if(deathDate!=null && !deathDate.equals(""))
				query=query+" AND TRUNC(B.HMRDT_DEATH_DATETIME) = TO_DATE('"+deathDate+"','dd-Mon-yyyy') ";
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_APPROVED);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_ENTRY);
		populateMAP.put(sq.next(), MortuaryConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//arrDeceasedDtlVO = null;
				throw new HisRecordNotFoundException("No Record Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedDtlVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedDtlVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return arrDeceasedDtlVO;
	}
	
	
	public DeceasedHandoverDtlVO getDeceasedDtlAfterHandover(String deceasedNo,UserVO userVO)
	{
		DeceasedHandoverDtlVO deceasedHandOverDtlVO=new DeceasedHandoverDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="SELECT_BODY_HANDOVER_DTL.HMRT_DECEASED_HANDOVER_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),deceasedNo);
		
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedHandOverDtlVO,rs);
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
		return deceasedHandOverDtlVO;
	}
	
	//added for the purpose of duplicate MLC Check at External Deceased Entry By Shruti Shail on 11-May-2017
	public Boolean chkDuplicateMLC(String mlcNo,String HospCode)
	{
		Boolean chk=false;
		String query="";
		Map populateMap= new HashMap();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="CHK_DUPLICATE_MLC.HMRT_DECEASED_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),mlcNo);
		populateMap.put(sq.next(),HospCode);
		
		
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
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			rs.next();
			if(rs.getInt(1)==0)
			{
				chk=true;
			}
			else
			{
				chk=false;
			}
			return chk;

		}
		catch(Exception e)
		{
					throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		
	}
}
