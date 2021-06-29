package registration.bo;

//last updated on july 06-07-06>>>priya

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisPatientNotReferredException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRegistrationTimingExpiredException;
import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalClinicalDAO;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.tools.Tree;
import hisglobal.utility.Entry;
import hisglobal.vo.CityLocationMasterVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.LocationMasterVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.RoomMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipEntryDtlVO;
import hisglobal.vo.YellowSlipMonitoringVO;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import registration.RegistrationConfig;
 
import registration.dao.EssentialDAO;
import registration.vo.BPLDetailsVO;
import registration.vo.DSSEpisodeVO;
import registration.vo.DSSRegistrationVO;
import registration.vo.DSSReportVO;

 

/**
 * @author vinita1
 * 
 */
public class EssentialBO implements EssentialBOi {

	 
	
	
	
	
	
	
	
	//get essential for search 
		public Map getEssentialForSearch(UserVO userVO){
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map essentialMap=new HashMap();
			List genderOptionList=null;
			List locationOptionList=null;
			List ageRangeList=null;
			List hospitalList=null;

			try
			{
				tx.begin();
				EssentialDAO essentialDAO = new EssentialDAO(tx);
				genderOptionList=essentialDAO.getGender(userVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_GENDER,genderOptionList);
				
				if(Config.CLIENT.equals(Config.CLIENT_GNCTD))
				{
				locationOptionList=essentialDAO.getLocation(userVO);
				essentialMap.put(RegistrationConfig.ESSENTIALBO_OPTION_LOCATION,locationOptionList);
				}
				
				//Added by Singaravelan on 17-Jul-2014 
				//hospitalList=essentialDAO.getAllHospitalsSeatIDWise(userVO);
				hospitalList=essentialDAO.getAllHospitals(userVO);
				essentialMap.put(RegistrationConfig.HOSPITAL_COMBO_LIST, hospitalList);
				/*
				 * hardcoded values are being used
				 * ageRangeList=essentialDAO.getAgeRangeList(userVO);
				essentialMap.put(RegistrationConfig.AGE_RANGE_OPTION_LIST,ageRangeList);*/
			}
			
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisDataAccessException(e.getMessage());
			}
			catch (Exception e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisException(e.getMessage());
			}
			finally
			{
				tx.close();
			}
			
			return essentialMap;
		}
	
}//end class
