package usermgmt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import usermgmt.model.GbltHospitalMst;
import usermgmt.model.GbltMenuMst;
//import usermgmt.model.GbltUserLog;
//import usermgmt.model.GbltUserLogPK;
import usermgmt.model.GbltUserMst;
import vo.usermgmt.HospitalMasterVO;
import vo.usermgmt.MenuMasterVO;
import vo.usermgmt.UserLoginLogVO;
import vo.usermgmt.UserMasterVO;
import application.config.HISApplicationConfig;

public class UserMgmtDAO
{
	EntityManagerFactory entityManagerFactory = null;
	EntityManager em = null;
	{
		String persistentUnit = HISApplicationConfig.JNDI_LOOKUP_ID_DATASOURCE_OLTP;
		entityManagerFactory = Persistence.createEntityManagerFactory(persistentUnit);
		em = entityManagerFactory.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<UserMasterVO> getUserDtl(UserMasterVO voUser_p)
	{
		List<UserMasterVO> lstUserMasterVO = new ArrayList<UserMasterVO>();
		EntityTransaction userTransaction = null;
		try
		{
			userTransaction = em.getTransaction();
			userTransaction.begin();
			Query q = em.createQuery("SELECT x FROM GbltUserMst x where x.gstrUserName LIKE '" + voUser_p.getVarUserName() + "'");

			List<GbltUserMst> lstUserModel = (List<GbltUserMst>) q.getResultList();

			if (lstUserModel != null && lstUserModel.size() > 0)
			{
				for (GbltUserMst mdlUser : lstUserModel)
				{
					UserMasterVO userVO = new UserMasterVO();
					populateData(userVO, mdlUser);
					
					HospitalMasterVO voHospital = new HospitalMasterVO();
					populateData(voHospital, mdlUser.getGbltHospitalMst());
					
					userVO.setVoHospital(voHospital);
					lstUserMasterVO.add(userVO);
				}
			}
		}
		catch (Exception e)
		{
			//em.getTransaction().rollback();
			lstUserMasterVO = null;
			// throw Exception here
		}
		finally
		{
			//em.getTransaction().commit();
		}
		return lstUserMasterVO;
	}

	public HospitalMasterVO getUserHospitalDetail(UserMasterVO voUser_p)
	{
		HospitalMasterVO voHospital = null;
		EntityTransaction userTransaction = null;
		// Get HospitalVO
		try
		{
			userTransaction = em.getTransaction();
			userTransaction.begin();
			Query q = em.createQuery("SELECT x FROM GbltHospitalMst x where x.gnumHospitalCode LIKE '" + voUser_p.getVoHospital().getVarHospitalCode()
					+ "' AND x.gnumIsvalid=1");
			GbltHospitalMst mdlHospital = (GbltHospitalMst) q.getSingleResult();

			voHospital = new HospitalMasterVO();
			populateData(voHospital, mdlHospital);
		}
		catch (Exception e)
		{
			//em.getTransaction().rollback();
			voHospital = null;
			// throw Exception here
		}
		finally
		{
			//em.getTransaction().commit();
		}
		return voHospital;
	}

	/*@SuppressWarnings("unchecked")
	public List<UserLogVO> getUserLogDetailList(UserMasterVO voUser_p, String strMode_p)
	{
		List<UserLogVO> lstUserLogVO = new ArrayList<UserLogVO>();
		// UserLogVO voUserLogVO=null;
		String strQueryString = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-Mon-yyyy HH:mm:ss");
		//Date date = new Date();
		EntityTransaction userTransaction = null;
		// Get HospitalVO
		try
		{
			userTransaction = em.getTransaction();
			userTransaction.begin();
			strQueryString = "SELECT x FROM GbltUserLog x where x.gstrUserId=" + voUser_p.getVarUserId() + " x.gnumHospitalCode LIKE '"
					+ voUser_p.getVarHospitalCode() + "' AND x.gnumIsvalid=1 AND X.gnumSeatId =" + voUser_p.getVarSeatId();

			if (strMode_p.equals("2")) strQueryString += " and x.gdtLogoutDate!= null";

			Query q = em.createQuery(strQueryString);

			List<GbltUserLog> lstGbltUsrLog = (List<GbltUserLog>) q.getResultList();

			if (lstGbltUsrLog != null && lstGbltUsrLog.size() > 0)
			{
				for (GbltUserLog objGbltUsrLog : lstGbltUsrLog)
				{

					// Get User VO
					UserLogVO voUserLogVo = new UserLogVO();

					voUserLogVo.setUserId(Long.toString(objGbltUsrLog.getId().getGnumUserid()));
					// voUserLogVo.setHospitalCode(objGbltUsrLog.getGnumHospitalCode().toString());
					// voUserLogVo.setUserName(objGbltUsrLog.getGstrUsrName()); // Name of the User
					voUserLogVo.setUserLoginDt(formatter.format(objGbltUsrLog.getId().getGdtLoginDate()));
					voUserLogVo.setUserLogoutDt(formatter.format(objGbltUsrLog.getGdtLoguttDate()));
					//voUserLogVo.setUserLastRefreshTime(formatter.format(objGbltUsrLog.getGdtLastRereshTime()));

					lstUserLogVO.add(voUserLogVo);
				}
			}

		}
		catch (Exception e)
		{
			//em.getTransaction().rollback();
			lstUserLogVO = null;
			// throw Exception here
		}
		finally
		{
			//em.getTransaction().commit();
		}
		return lstUserLogVO;
	}

	public UserLogVO getLastUserLogDetail(UserMasterVO voUser_p, String strMode_p)
	{

		UserLogVO voUserLogVo = null;
		String strQueryString = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-Mon-yyyy HH:mm:ss");
		// Get HospitalVO
		try
		{

			strQueryString = "SELECT x FROM GbltUserLog x where x.gstrUserId=" + voUser_p.getUserId() + " x.gnumHospitalCode LIKE '"
					+ voUser_p.getHospitalCode() + "' AND x.gnumIsvalid=1 AND X.gnumSeatId =" + voUser_p.getSeatId()
					+ "gdt_login_date = (select max(gdt_login_date) from gblt_usr_log_jpa y)";

			if (strMode_p.equals("2")) strQueryString += " and x.gdtLogoutDate!= null";

			Query q = em.createQuery(strQueryString);

			GbltUserLog objGbltUsrLog = (GbltUserLog) q.getSingleResult();

			// Get User Log VO
			voUserLogVo = new UserLogVO();

			voUserLogVo.setUserId(Long.toString(objGbltUsrLog.getGbltUserMst().getGnumUserid()));
			// voUserLogVo.setHospitalCode(objGbltUsrLog.getGnumHospitalCode().toString());
			// voUserLogVo.setUserName(objGbltUsrLog.getGstrUsrName()); // Name of the User
			voUserLogVo.setUserLoginDt(formatter.format(objGbltUsrLog.getId().getGdtLoginDate()));
			voUserLogVo.setUserLogoutDt(formatter.format(objGbltUsrLog.getGdtLoguttDate()));
			//voUserLogVo.setUserLastRefreshTime(formatter.format(objGbltUsrLog.getGdtLastRereshTime()));

		}
		catch (Exception e)
		{
			//em.getTransaction().rollback();
			voUserLogVo = null;
			// throw Exception here
		}
		finally
		{
			//em.getTransaction().commit();
		}
		return voUserLogVo;
	}*/

	public boolean logUserDetail(UserLoginLogVO voUserLog)
	{
		boolean flagSuccess = false;
		EntityTransaction userTransaction = null;
		try
		{
			userTransaction = em.getTransaction();
			userTransaction.begin();
			
			/*GbltUserLogPK pkUserLog = new GbltUserLogPK();
			populateData(pkUserLog, voUserLog);

			GbltUserLog mdlUserLog = em.find(GbltUserLog.class, pkUserLog);
			if (mdlUserLog == null)
			{
				mdlUserLog = new GbltUserLog();
				mdlUserLog.setId(pkUserLog);
				populateData(mdlUserLog, voUserLog);

				em.getTransaction().begin();
				em.persist(mdlUserLog);
				em.getTransaction().commit();
				flagSuccess = true;
			}
			else
			{
				em.getTransaction().begin();
				mdlUserLog.setGdtLoguttDate(Timestamp.valueOf(voUserLog.getVarUserLogoutDate()));
				em.getTransaction().commit();
				flagSuccess = true;
			}*/

		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			flagSuccess = false;
			// throw Exception here
		}
		finally
		{
			em.getTransaction().commit();
		}
		return flagSuccess;
	}
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try{
			String URL = "jdbc:oracle:thin:@10.0.2.103:1521:ahis";//"jdbc:edb://10.0.2.103:5444/phdm";
	        Class.forName("oracle.jdbc.OracleDriver");//"com.edb.Driver");
	        conn = DriverManager.getConnection(URL, "raol","raol");//"phdm", "phdm");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	public static List<MenuMasterVO> getMenuMstDtl(UserMasterVO voUser_p, String strMode_p) throws Exception
	{
		String strDBErr;
		ResultSet objResSet=null;
		//HisDAO hisDAO_p=null;
		CallableStatement cstmt=null;

		Connection conn = null;
		try
		{
			conn=getConnection();
			Float hospCode=Float.valueOf(voUser_p.getVarHospitalCode());
			Float seatId=Float.valueOf(voUser_p.getVarUserSeatId());
			cstmt = conn.prepareCall("{call pkg_usermgmt.proc_menu (?,?,?,?)}");
			cstmt.setFloat(1, hospCode==null?101:hospCode);
			cstmt.setFloat(2, seatId==null?10001:seatId);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.REF);
			cstmt.execute();
			

			// Getting out parameters 
			strDBErr = cstmt.getString(3);		
			objResSet = cstmt.getResultSet();	

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}

		List<MenuMasterVO> lstMenuMasterVO = new ArrayList<MenuMasterVO>();
		while(objResSet.next()){
			MenuMasterVO voMenuMasterVO = new MenuMasterVO();
			voMenuMasterVO.setVarMenuName(objResSet.getString(1));
			voMenuMasterVO.setVarURL(objResSet.getString(2));
			lstMenuMasterVO.add(voMenuMasterVO);
		}
		if(lstMenuMasterVO !=null)
			System.out.println("lstMenuMasterVO.size :"+lstMenuMasterVO.size());
		
		
		return lstMenuMasterVO;
	}
	public List<GbltMenuMst> getMenuMstDtl1(UserMasterVO voUser_p, String strMode_p)
	{

		List<GbltMenuMst> lstGbltMenuMst= null;
		String strQueryString = "";
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Get HospitalVO
		try
		{

			/*StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_usermgmt.proc_menu");
			query.registerStoredProcedureParameter("hcode", Float.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("seatid", Float.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("err", String.class, ParameterMode.OUT);
			query.registerStoredProcedureParameter("refcursor", String.class, ParameterMode.OUT);
			query.setParameter("hcode", voUser_p.getHospitalCode()==null?100:voUser_p.getHospitalCode());
			query.setParameter("seatid", voUser_p.getSeatId()==null?10001:voUser_p.getSeatId());
			
			lstGbltMenuMst= query.getResultList();*/

			System.out.println("lstGbltMenuMst :");
			if(lstGbltMenuMst!=null){
				System.out.println("lstGbltMenuMst.size() :"+ lstGbltMenuMst.size());
			}
			//boolean queryResult = query.execute();
			//String result = String.valueOf(query.getOutputParameterValue("o_output_1"));
			
			/*ReadAllQuery databaseQuery = new ReadAllQuery(GbltMenuMst.class);
			StoredProcedureCall call = new StoredProcedureCall();
			call.setProcedureName("EMP_READ_ALL");
			call.useNamedCursorOutputAsResultSet("RESULT_CURSOR");
			databaseQuery.setCall(call);
			 
			Query query =((JpaEntityManager)em.getDelegate()).createQuery(databaseQuery);
			List<Employee> result = query.getResultList();

			// Get User Log VO
			voUserLogVo = new UserLogVO();

			voUserLogVo.setUserId(objGbltUsrLogJpa.getGstrUserId());
			// voUserLogVo.setHospitalCode(objGbltUsrLogJpa.getGnumHospitalCode().toString());
			// voUserLogVo.setUserName(objGbltUsrLogJpa.getGstrUsrName()); // Name of the User
			voUserLogVo.setUserLoginDt(formatter.format(objGbltUsrLogJpa.getGdtLoginDate()));
			voUserLogVo.setUserLogoutDt(formatter.format(objGbltUsrLogJpa.getGdtLogoutDate()));
			voUserLogVo.setUserLastRefreshTime(formatter.format(objGbltUsrLogJpa.getGdtLastRereshTime()));*/

		}
		catch (Exception e)
		{
			e.printStackTrace();
			//em.getTransaction().rollback();
			// throw Exception here
		}
		finally
		{
			//em.getTransaction().commit();
		}
		return lstGbltMenuMst;
	}
	
	private void populateData(Object objTarget, Object objSource)
	{
		if(objSource instanceof GbltUserMst && objTarget instanceof UserMasterVO)
		{
			GbltUserMst objSrc = (GbltUserMst) objSource;
			UserMasterVO objTar = (UserMasterVO) objTarget;
			objTar.setVarUserId(Long.toString(objSrc.getGnumUserid()));
			objTar.setVarHospitalCode(Long.toString(objSrc.getGbltHospitalMst().getGnumHospitalCode()));
			objTar.setVarDesignation(objSrc.getGnumDesignation());
			objTar.setVarEmpNo(objSrc.getPsrstrEmpNo());
			objTar.setVarPassword(objSrc.getGstrPassword());
			objTar.setVarUserSeatId(objSrc.getGnumUserSeatid().toString());
			objTar.setVarUserName(objSrc.getGstrUserName());
			objTar.setVarUsrName(objSrc.getGstrUsrName());
		}
		else if(objSource instanceof GbltHospitalMst && objTarget instanceof HospitalMasterVO)
		{
			GbltHospitalMst objSrc = (GbltHospitalMst) objSource;
			HospitalMasterVO objTar = (HospitalMasterVO) objTarget;
			objTar.setVarHospitalCode(Long.toString(objSrc.getGnumHospitalCode()));
			objTar.setVarHospitalName(objSrc.getGstrHospitalName());
			objTar.setVarHospitalShortName(objSrc.getGstrHospShortName());
			objTar.setVarHospitalAddress1(objSrc.getGstrHospitalAdd1());
			objTar.setVarHospitalAddress2(objSrc.getGstrHospitalAdd2());
		}
		/*else if(objSource instanceof UserLogVO && objTarget instanceof GbltUserLogPK)
		{
			UserLogVO objSrc = (UserLogVO) objSource;
			GbltUserLogPK objTar = (GbltUserLogPK) objTarget;
			objTar.setGnumUserid(Long.valueOf(objSrc.getVarUserId()));
			objTar.setGdtLoginDate(DateHelperMethods.getDateObject(objSrc.getVarUserLoginDate()));
		}
		else if(objSource instanceof UserLogVO && objTarget instanceof GbltUserLog)
		{
			UserLogVO objSrc = (UserLogVO) objSource;
			GbltUserLog objTar = (GbltUserLog) objTarget;
			objTar.setGnumHospitalCode(new BigDecimal(objSrc.getVarHospitalCode()));
			objTar.setGnumSeatId(new BigDecimal(objSrc.getVarSeatId()));
			objTar.setGstrIpNumber(objSrc.getVarIPAddress());
		}*/
	}
}
