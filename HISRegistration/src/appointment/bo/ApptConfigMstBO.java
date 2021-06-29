package appointment.bo;

/**
 * Created By 	: Neha Sharma
 * Date			: 3-Jan-2014
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import vo.appointment.ApptConfigMstVO;
import appointment.dao.ApptConfigMstDao;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.UserVO;

public class ApptConfigMstBO {

	@SuppressWarnings("rawtypes")
	public List getApptTypeList() 
	{
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBO");
		List apptTypeList = null;
		try 
		{
			apptTypeList = ApptConfigMstDao.getApptTypeList(hisDAO);
		} 
		catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} 
		catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		} 
		catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		} 
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		}
		return apptTypeList;

	}
	
	public List getApptTypeModList(ApptConfigMstVO appConfMstVo) {
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBO");
		List apptTypeList = null;
		try 
		{
			apptTypeList = ApptConfigMstDao.getApptTypeModList(hisDAO,appConfMstVo);
		} catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		} catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		}
		return apptTypeList;

	}
	
	public List getApptTypeSelModList(ApptConfigMstVO appConfMstVo) {
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBO");
		List apptTypeList = null;
		try 
		{
			apptTypeList = ApptConfigMstDao.getApptTypeSelModList(hisDAO,appConfMstVo);
		} catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		} catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		}
		return apptTypeList;

	}
	
	public List getDefaultApptTypeModList(ApptConfigMstVO appConfMstVo) {
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBO");
		List apptTypeList = null;
		try 
		{
			apptTypeList = ApptConfigMstDao.getDefaultApptTypeModList(hisDAO,appConfMstVo);
		} catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		} catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		}
		return apptTypeList;

	}
	
	@SuppressWarnings("rawtypes")
	public List getApptDurationList() 
	{
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBO");
		List apptTypeList = null;
		try 
		{
			apptTypeList = ApptConfigMstDao.getApptDurationList(hisDAO);
		} 
		catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} 
		catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		} 
		catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		} 
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		}
		return apptTypeList;

	}
	
	@SuppressWarnings("rawtypes")
	public List getApptShiftList() 
	{
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBO");
		List apptTypeList = null;
		try 
		{
			apptTypeList = ApptConfigMstDao.getApptShiftList(hisDAO);
		} 
		catch (HisDataAccessException e) 
		{
			throw new HisDataAccessException();
		} 
		catch (HisRecordNotFoundException e) 
		{
			throw new HisRecordNotFoundException(e.getMessage());
		} 
		catch (HisApplicationExecutionException e) 
		{
			throw new HisApplicationExecutionException();
		} 
		catch (Exception e) 
		{
			throw new HisApplicationExecutionException();
		}
		return apptTypeList;

	}


	public Boolean saveConfigDtl(ApptConfigMstVO configMstVo_p, UserVO userVo) 
	{		
		HisDAO hisDAO = new HisDAO("Appointment", "ApptConfigMstBo");
		Boolean retVal=false;
		
		try 
		{
			retVal=ApptConfigMstDao.saveConfigDetails(configMstVo_p, hisDAO, userVo);
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		if (configMstVo_p.getStrMsgType() != null && configMstVo_p.getStrMsgType().equals("1")) 
		{
			configMstVo_p.setStrMsgString("ApptConfigMstBO.InsertRecord(vo) -->"	+ configMstVo_p.getStrMsgString());
			

		}
		
		return retVal;

	}
	
	public ApptConfigMstVO modifyRecord(ApptConfigMstVO vo,UserVO userVo,HttpServletRequest request) {
		ApptConfigMstVO apptConfMstVo = null;
		try {
			HisDAO hisDAO = new HisDAO("Appointment", "apptBo");
			
			apptConfMstVo = ApptConfigMstDao.modifyRecord(vo, hisDAO,userVo,request);
			
			if (vo.getStrMsgType() != null &&(vo.getStrMsgType().equals("1")) ) 
			{
				vo.setStrMsgString("apptConfMstBO.modifyRecord(vo) --> "
						+ vo.getStrMsgString());
			}
		} catch (HisRecordNotFoundException e) {
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			throw new HisApplicationExecutionException();
		} finally {
		}
		return apptConfMstVo;
	}

	public boolean update(ApptConfigMstVO appConfMstVo,UserVO uservo)
	{
		HisDAO hisDAO = new HisDAO("Appointment", "appConfMstBo");
		
		boolean bExistStatus = true;

		try
		{
			ApptConfigMstDao.modifyConfigDetails(appConfMstVo, hisDAO, uservo);
			
		}catch(Exception e)
		{
			bExistStatus = false;
		}
		

		if (appConfMstVo.getStrMsgType() != null && appConfMstVo.getStrMsgType().equals("1")) 
		{
			String strErr = appConfMstVo.getStrMsgString();

			appConfMstVo.setStrMsgString("ApptConfMstBO.update() --> "	+ strErr);
		}
		
		
		
		return bExistStatus;
	}

	public String getOPDRosterSchedule(String finalParaId, UserVO uservo) {
		HisDAO hisDAO = new HisDAO("Appointment", "appConfMstBo");
		String strResult=null;
		try
		{
			strResult=ApptConfigMstDao.getOPDRosterSchedule(finalParaId, hisDAO, uservo);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return strResult;
	}
	}
