package uploadfilesonserver;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.BagTypeMstVO;
import hisglobal.vo.BloodBagComponentDtlVO;
import hisglobal.vo.BloodBagDtlVO;
import hisglobal.vo.BloodBankMasterVO;
import hisglobal.vo.BloodDonationVO;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.BloodRequisitionDtlVO;
import hisglobal.vo.BloodRequisitionPatientDtlVO;
import hisglobal.vo.BloodTestResultVO;
import hisglobal.vo.CampDetailVO;
import hisglobal.vo.CellGroupingValidationVO;
import hisglobal.vo.UserVO;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
/**
 * @author : C-DAC, Noida
 * Project : AHIMS
 * Module  : Blood Bank
 * Created On : 18 Aug, 2008
 * Developed By : For Common Use
 * Purpose : This Class should be used for the only Business Layer Methods 
 * 			regarding Process 'Blood Investigation'
 * 
 */

public class BloodInvestigationBO implements BloodInvestigationBOi
{
	//The method getBagNoDetail is called to get Bag Nos corresponding to the Donation date during Blood Bag Investigation
		//Added by hemant on 14-11-2018 for gettingpath data
		public String getPathData(UserVO userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			BloodRequisitionDtlDAOi bloodRequistionDtlDAOi=new BloodRequisitionDtlDAO(tx);
			String Path = "";
			//BloodRequisitionDtlVO[] BloodRequisitionDtlVOs=null;
			try
			{
				tx.begin();
				//BloodRequisitionDtlVOs=bloodRequistionDtlDAOi.fetchInternalRequisitionCancellationDetail(userVO,bloodRequisitionDtlVO);
				Path = bloodRequistionDtlDAOi.getPathData(userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisDataAccessException();
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException();
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
			return Path;
		}
		//Added bY Hemant for saving file Data
		public void saveDetailsOfFile(FileUploadedVO vo ,UserVO userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			BloodRequisitionDtlDAOi bloodRequistionDtlDAOi=new BloodRequisitionDtlDAO(tx);
			
			//BloodRequisitionDtlVO[] BloodRequisitionDtlVOs=null;
			try
			{
				tx.begin();
				//BloodRequisitionDtlVOs=bloodRequistionDtlDAOi.fetchInternalRequisitionCancellationDetail(userVO,bloodRequisitionDtlVO);
				bloodRequistionDtlDAOi.saveDetailsOfFile(vo,userVO);
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisDataAccessException();
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				System.out.println(e.getMessage());
				throw new HisApplicationExecutionException();
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
			
		}
}

