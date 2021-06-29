package hisglobal.presentation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ControllerUTIL
{
	/**
	 * @roseuid 45C2F539034B
	 */
	public ControllerUTIL()
	{
	}
	/**
	 * @param request
	 * @roseuid 45C1AAA20186
	 */
	public static void initialize(HttpServletRequest request)
	{
		WebUTIL.refreshTransState(request);
	}

	public static UserVO getUserVO(HttpServletRequest request)
	{
		UserVO userVO=null;
		try
		{
			userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			HospitalMstVO objHospitalMstVO=(HospitalMstVO)request.getSession().getAttribute(HISConfig.HOSPITAL_VO);
			userVO.setStrHospitalMstVo(objHospitalMstVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userVO;

	}

	/*public static void setSysdate(HttpServletRequest _request)
	{
		System.out.println("inside setAllChangePrimaryCategoryEssentials()");
		Status objStatus = new Status();
		try
		{
			// String[] dateAndCrNoFormat=ControllerDATA.getSysDate(getUserVO(_request));

			// Date dateObj=ControllerDATA.getSysDateAsDate();
			List dateAndCrNoFormat = ControllerDATA.getSystemDateAndFormat(getUserVO(_request));
			String sysdate = (String) dateAndCrNoFormat.get(0);
			String defalutCrNoFormat = (String) dateAndCrNoFormat.get(1);
			Date dateObj = (Date) dateAndCrNoFormat.get(2);

			_request.getSession().setAttribute(Config.SYSDATE, sysdate);
			_request.getSession().setAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT, defalutCrNoFormat);
			_request.getSession().setAttribute(Config.SYSDATEOBJECT, dateObj);

			// isRegistrationAllowed(registration, _hospitalCode);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "e.getMessage", "Records Not Found");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}*/

	public static String settingThePageString(List sessionList, String _recordPerPage, String _pagesPerBlock)
	{
		String Str = "";
		int TotalNopages = (sessionList.size() % Integer.parseInt(_recordPerPage) == 0) ? sessionList.size()
				/ Integer.parseInt(_recordPerPage) : sessionList.size() / Integer.parseInt(_recordPerPage) + 1;
		// int recordsPerPage = Integer.parseInt(_recordPerPage);
		int pagesperblock = Integer.parseInt(_pagesPerBlock);
		int totalNoBlock = (TotalNopages % pagesperblock == 0) ? TotalNopages / pagesperblock : TotalNopages / pagesperblock
				+ 1;
		int block = 1;
		int page = 1;
		System.out.println("TotalNopages  =" + TotalNopages + "  totalNoBlock=" + totalNoBlock + " block=" + block
				+ " page=" + page);
		for (int i = 1; i <= TotalNopages; i++)
		{
			if ((i % pagesperblock) == 1)
			{
				if (((i / pagesperblock) + 1) == block) Str += "<div id='block" + ((i / pagesperblock) + 1)
						+ "' style='display:block' >";
				else Str += "<div id='block" + ((i / pagesperblock) + 1) + "' style='display:none' >";
				if (((i / pagesperblock) + 1) > 1)
				{
					Str += "<a name='prev'  onClick='showPrevious()'><font Style='cursor:pointer'>  Prev  </font></a>";
				}
			}
			if (i == page)
			{
				Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
						+ "\",\"GO\")' style='color:red'><font Style='cursor:default'>&nbsp;" + i + "</font></a>";
			}
			else Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
					+ "\",\"GO\")' style='color:blue'><font Style='cursor:pointer'>&nbsp;" + i + "</font></a>";
			if ((i % pagesperblock) == 0)
			{
				if (((i / pagesperblock)) + 1 <= totalNoBlock)
				{
					Str += "<a name='next'  onClick='showNext()'><font Style='cursor:pointer'>  next  </font></a>";
				}
				Str += "</div>";
			}
		}
		Str += "</div>";
		System.out.println("Str   =" + Str);
		return Str;

	}

	public static String settingThePageString(ValueObject[] _VO, String _recordPerPage, String _pagesPerBlock)
	{
		String Str = "";
		int TotalNopages = (_VO.length % Integer.parseInt(_recordPerPage) == 0) ? _VO.length
				/ Integer.parseInt(_recordPerPage) : _VO.length / Integer.parseInt(_recordPerPage) + 1;
		// int recordsPerPage = Integer.parseInt(_recordPerPage);
		int pagesperblock = Integer.parseInt(_pagesPerBlock);
		int totalNoBlock = (TotalNopages % pagesperblock == 0) ? TotalNopages / pagesperblock : TotalNopages / pagesperblock
				+ 1;
		int block = 1;
		int page = 1;
		System.out.println("TotalNopages  =" + TotalNopages + "  totalNoBlock=" + totalNoBlock + " block=" + block
				+ " page=" + page);
		for (int i = 1; i <= TotalNopages; i++)
		{
			if ((i % pagesperblock) == 1)
			{
				if (((i / pagesperblock) + 1) == block) Str += "<div id='block" + ((i / pagesperblock) + 1)
						+ "' style='display:block' >";
				else Str += "<div id='block" + ((i / pagesperblock) + 1) + "' style='display:none' >";
				if (((i / pagesperblock) + 1) > 1)
				{
					Str += "<a name='prev'  onClick='showPrevious()'><font Style='cursor:pointer'>  Prev  </font></a>";
				}
			}
			if (i == page)
			{
				Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
						+ "\",\"GO\")' style='color:red'><font Style='cursor:default'>&nbsp;" + i + "</font></a>";
			}
			else Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
					+ "\",\"GO\")' style='color:blue'><font Style='cursor:pointer'>&nbsp;" + i + "</font></a>";
			if ((i % pagesperblock) == 0)
			{
				if (((i / pagesperblock)) + 1 <= totalNoBlock)
				{
					Str += "<a name='next'  onClick='showNext()'><font Style='cursor:pointer'>  next  </font></a>";
				}
				Str += "</div>";
			}
		}
		Str += "</div>";
		System.out.println("Str   =" + Str);
		return Str;

	}
	
	public static HospitalMstVO getHospitalVO(HttpServletRequest _request)
	{
		HospitalMstVO objHospitalMstVO = null;
		try
		{
			objHospitalMstVO=(HospitalMstVO)_request.getSession().getAttribute(HISConfig.HOSPITAL_VO);
		}
		catch (Exception e)
		{
			e.printStackTrace();	
		}
		return objHospitalMstVO;
	}
	

	

	/*
	 * public static String isRegistrationAllowed(String _categoryCode,HttpServletRequest _request){ String isAllowed=null;
	 * Status status=new Status(); try{ isAllowed=ControllerDATA.isRegistrationAllowed(_categoryCode, getUserVO(_request)); }
	 * catch (HisRegistrationTimingExpiredException e){ e.printStackTrace(); WebUTIL.removeFromStatus(_request,Status.NEW);
	 * status.add(Status.ERROR_AE,e.getMessage(),""); } catch(HisRecordNotFoundException e){
	 * 
	 * e.printStackTrace(); WebUTIL.removeFromStatus(_request,Status.NEW); status.add(Status.ERROR_AE,e.getMessage(),""); }
	 * catch(HisDataAccessException e){ e.printStackTrace(); WebUTIL.removeFromStatus(_request,Status.NEW);
	 * status.add(Status.ERROR_AE,e.getMessage(),""); } catch(HisException e){ e.printStackTrace();
	 * WebUTIL.removeFromStatus(_request,Status.NEW); status.add(Status.ERROR_AE,e.getMessage(),""); } finally{
	 * WebUTIL.setStatus(_request,status); } return isAllowed; }
	 */
	/***
	 * 
	 * @deprecated description : following function to be used against this function :: setSysdateAndDefaultCrNoFormat
	 * {@code setSysdateAndDefaultCrNoFormat} method that sets initial Detail like sysdate, Default Cr No Format {@link 
     * setSysdateAndDefaultCrNoFormat}
	 */
	
	@Deprecated
	public static void setSysdate(HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			List dateAndCrNoFormat = ControllerDATA.getSystemDateAndFormat(getUserVO(request));
			
			String sysdate = (String) dateAndCrNoFormat.get(0);
			String defalutCrNoFormat = (String) dateAndCrNoFormat.get(1);
			Date dateObj = (Date) dateAndCrNoFormat.get(2);

			request.getSession().setAttribute(Config.SYSDATE, sysdate);
			request.getSession().setAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT, defalutCrNoFormat);
			request.getSession().setAttribute(Config.SYSDATEOBJECT, dateObj);
			
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "e.getMessage", "Records Not Found");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (NullPointerException e)
		{
			objStatus.add(Status.ERROR, e.getMessage()+"Date Not fetched from DB", "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);			
		}
	}
	
	public static void setSysdateAndDefaultCrNoFormat(HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			List dateAndCrNoFormat = ControllerDATA.getSystemDateAndFormat(getUserVO(_request));
			
			String sysdate = (String) dateAndCrNoFormat.get(0);
			String defalutCrNoFormat = (String) dateAndCrNoFormat.get(1);
			Date dateObj = (Date) dateAndCrNoFormat.get(2);

			_request.getSession().setAttribute(Config.SYSDATE, sysdate);
			_request.getSession().setAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT, defalutCrNoFormat);
			_request.getSession().setAttribute(Config.SYSDATEOBJECT, dateObj);
			
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "e.getMessage", "Records Not Found");
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (NullPointerException e)
		{
			objStatus.add(Status.ERROR, e.getMessage()+"Date Not fetched from DB", "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);			
		}
	}

}
