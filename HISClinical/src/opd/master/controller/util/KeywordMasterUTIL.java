package opd.master.controller.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.KeywordMasterDATA;
import opd.master.controller.fb.KeywordMasterFB;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.EpisodeKeywordsMasterVO;
import hisglobal.vo.UserVO;

public class KeywordMasterUTIL extends ControllerUTIL
{
	public static boolean saveKeywordMstDetail(KeywordMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			EpisodeKeywordsMasterVO keywordMstVO = new EpisodeKeywordsMasterVO();
			
			keywordMstVO.setEpisodeKeyword(_fb.getKeyword().trim());
			keywordMstVO.setSlNo("1");
						
			hasFlag=KeywordMasterDATA.saveKeywordMstDetail(keywordMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {	hasFlag=false;
				 objStatus.add(Status.NEW, "Keyword Already Exists", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			hasFlag=false;
			objStatus.add(Status.ERROR, "", "Keyword already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(KeywordMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		EpisodeKeywordsMasterVO keywordMstVO = new EpisodeKeywordsMasterVO();
		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sKeywordId = concatid[0];
			String sKeywordSlno = " ";
			String shospitalCode = " ";
			
			// putting the selected Record Primary Key into Vo
			
			keywordMstVO.setSlNo(sKeywordSlno);
			keywordMstVO.setEpisodeKeywordID(sKeywordId);
			keywordMstVO.setHospitalCode(shospitalCode);
			
					
			mp = KeywordMasterDATA.getDataForModifyKeywordMst(keywordMstVO,userVO);
			
			keywordMstVO=(EpisodeKeywordsMasterVO)mp.get(OpdConfig.KEYWORD_MST_VO_FOR_MODIFY);
		    
			
			fb.setKeyword(keywordMstVO.getEpisodeKeyword());
			fb.setKeywordId(keywordMstVO.getEpisodeKeywordID());
			fb.setSlNo(keywordMstVO.getSlNo());
			fb.setIsValid(keywordMstVO.getIsValid());
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean saveModKeywordMaster(KeywordMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			EpisodeKeywordsMasterVO keywordMstVO = new EpisodeKeywordsMasterVO();

			
			keywordMstVO.setEpisodeKeyword(_fb.getKeyword().trim());
			keywordMstVO.setEpisodeKeywordID(_fb.getKeywordId());
			keywordMstVO.setSlNo(_fb.getSlNo());
			keywordMstVO.setIsValid(_fb.getIsValid());
						
			flag=KeywordMasterDATA.saveModKeywordMstDetail(keywordMstVO,userVO);
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Keyword already exists");
				
			}
			
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Keyword already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
}
