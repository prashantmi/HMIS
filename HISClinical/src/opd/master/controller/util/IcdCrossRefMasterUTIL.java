package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.IcdCrossRefMasterVO;
import hisglobal.vo.IcdIndexLevelMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.data.IcdCrossRefMasterDATA;
import opd.master.controller.fb.IcdCrossRefMasterFB;

public class IcdCrossRefMasterUTIL extends ControllerUTIL
{

	public static void getInitializeIndexTerm(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();
		try
		{	
			UserVO userVO = getUserVO(request);
			
			Map<String,Object> mapEssentialIndex = IcdCrossRefMasterDATA.getInitializeIndexTerm(userVO);

			WebUTIL.setMapInSession(mapEssentialIndex, request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "No Index Term Found");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}	
	
	/*
	 * Populating the Icd Cross Ref Modifier Level One
	 */	
	public static void getInitializeModifier(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{		
		Status objStatus = new Status();
		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getModifier(fb.getIndexCode(),userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("1");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Index Term" );
			}
					
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}	
	}
	
	
	// add Page
	public static void getInitializeRefModifier(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getModifier(fb.getRefIndexCode(),userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_REFTERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheckSee("1");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Reference Index Term" );
			}					
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}	
	}
	
	public static void getSeeTerms(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();
		try
		{	
			UserVO userVO = getUserVO(request);
			List<IcdIndexLevelMasterVO> listSeeTerms = IcdCrossRefMasterDATA.getSeeTerms(fb.getIndexCode(),userVO);
			
			if(listSeeTerms!=null && listSeeTerms.size()>0)
			{
				IcdIndexLevelMasterVO vo = listSeeTerms.get(0);
				if((vo.getSeeTermCode()!=null && !vo.getSeeTermCode().trim().equals("")) 
						|| (vo.getSeeAlsoTermCode()!=null && !vo.getSeeAlsoTermCode().trim().equals("")))
				{
					fb.setSeeTermsFlag("1");
					fb.setSeeTerm(vo.getSeeTerm());
					fb.setSeeTermCode(vo.getSeeTermCode());
					fb.setSeeAlsoTerm(vo.getSeeAlsoTerm());
					fb.setSeeAlsoTermCode(vo.getSeeAlsoTermCode());
				}
				else
				{
					fb.setSeeTermsFlag("0");
					fb.setSeeTerm("");
					fb.setSeeTermCode("");
					fb.setSeeAlsoTerm("");
					fb.setSeeAlsoTermCode("");
				}
			}
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_SEE_TERMS, listSeeTerms);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}	
	}
	
	// Populating the Icd Cross Ref Modifier for Next Level
	public static void getInitializeModifierTwo(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();
		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier1(),"2",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL2_TERM, listModifier);
			
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("2");
				objStatus.add(Status.NEW);
			}
			else
			{
				fb.setStrCheck("1");
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	
	public static void getInitializeModifierThree(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier2(),"3",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL3_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("3");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}						
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	public static void getInitializeModifierFour(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier3(),"4",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL4_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("4");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}									
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	
	public static void getInitializeModifierFive(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier4(),"5",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL5_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("5");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}										
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	
	public static void getInitializeModifierSix(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier5(),"6",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL6_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("6");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}												
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	
	public static void getInitializeModifierSeven(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier6(),"7",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL7_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("7");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}											
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	public static void getInitializeModifierEight(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier7(),"8",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL8_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("8");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}													
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	public static void getInitializeModifierNine(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		
		Status objStatus = new Status();

		try
		{	
			UserVO userVO = getUserVO(request);
			
			List<Entry> listModifier = IcdCrossRefMasterDATA.getInitializeModifierNext(fb.getStrModifier8(),"9",userVO);
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL9_TERM, listModifier);
			if(listModifier!=null && listModifier.size()!=0)
			{
				fb.setStrCheck("9");
				objStatus.add(Status.NEW);
			}
			else
			{
				objStatus.add(Status.NEW, "","No Modifier Found for Parent" );
			}											
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	
	
	}
	
	// Populating see terms for Modifier
	public static void getSeeTermsForModi(IcdCrossRefMasterFB fb,HttpServletRequest request)
	{	
		Status objStatus = new Status();
		try
		{	
			UserVO userVO = getUserVO(request);
			String modiID = null;
			if(!fb.getStrCheck().equals("0"))
			{
				switch(Integer.parseInt(fb.getStrCheck()))
				{
					case 1:	modiID = fb.getStrModifier1(); break;
					case 2:	modiID = fb.getStrModifier2(); break;
					case 3:	modiID = fb.getStrModifier3(); break;
					case 4:	modiID = fb.getStrModifier4(); break;
					case 5:	modiID = fb.getStrModifier5(); break;
					case 6:	modiID = fb.getStrModifier6(); break;
					case 7:	modiID = fb.getStrModifier7(); break;
					case 8:	modiID = fb.getStrModifier8(); break;
					case 9:	modiID = fb.getStrModifier9(); break;
				}
			}
			List<IcdIndexLevelMasterVO> listSeeTerms = IcdCrossRefMasterDATA.getSeeTermsForModi(modiID,userVO);
			
			if(listSeeTerms!=null && listSeeTerms.size()>0)
			{
				IcdIndexLevelMasterVO vo = listSeeTerms.get(0);
				if((vo.getSeeTermCode()!=null && !vo.getSeeTermCode().trim().equals("")) 
						|| (vo.getSeeAlsoTermCode()!=null && !vo.getSeeAlsoTermCode().trim().equals("")))
				{
					fb.setSeeTermsFlag("1");
					fb.setSeeTerm(vo.getSeeTerm());
					fb.setSeeTermCode(vo.getSeeTermCode());
					fb.setSeeAlsoTerm(vo.getSeeAlsoTerm());
					fb.setSeeAlsoTermCode(vo.getSeeAlsoTermCode());
				}
				else
				{
					fb.setSeeTermsFlag("0");
					fb.setSeeTerm("");
					fb.setSeeTermCode("");
					fb.setSeeAlsoTerm("");
					fb.setSeeAlsoTermCode("");
				}
			}
			WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_SEE_TERMS, listSeeTerms);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
		/*
		 * To Save Data on Add Page
		 */
		public static void saveRecord(IcdCrossRefMasterFB fb, HttpServletRequest request)
		{
			Status objStatus = new Status();
			try
			{
				UserVO userVO = getUserVO(request);

				IcdCrossRefMasterVO icdCrossRefMasterVO = new IcdCrossRefMasterVO();
				
				
				if(!fb.getStrCheck().equals("0"))
				{
					switch(Integer.parseInt(fb.getStrCheck()))
					{
						case 1:	fb.setIndexModifierID(fb.getStrModifier1()); break;
						case 2:	fb.setIndexModifierID(fb.getStrModifier2()); break;
						case 3:	fb.setIndexModifierID(fb.getStrModifier3()); break;
						case 4:	fb.setIndexModifierID(fb.getStrModifier4()); break;
						case 5:	fb.setIndexModifierID(fb.getStrModifier5()); break;
						case 6:	fb.setIndexModifierID(fb.getStrModifier6()); break;
						case 7:	fb.setIndexModifierID(fb.getStrModifier7()); break;
						case 8:	fb.setIndexModifierID(fb.getStrModifier8()); break;
						case 9:	fb.setIndexModifierID(fb.getStrModifier9()); break;
					}
				}
				
				HelperMethods.populate(icdCrossRefMasterVO, fb);
				IcdCrossRefMasterDATA.saveRecord(icdCrossRefMasterVO, userVO);
				
				objStatus.add(Status.NEW, "Record added successfully", "");
				
		
			}
			catch (HisRecordNotFoundException e)
			{
				
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			}
			catch (HisDataAccessException e)
			{
				
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				
				e.printStackTrace();
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			catch (Exception e)
			{
				
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}			
		}
		
		/*
		 * To get Modify Page Components
		 */
		public static void getModifyPageComponent(IcdCrossRefMasterFB fb, HttpServletRequest request)
		{
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			try
			{	
				UserVO userVO = getUserVO(request);
				List<IcdIndexLevelMasterVO> listSeeTerms = ((List<IcdIndexLevelMasterVO>)session.getAttribute(OpdConfig.OPD_ESSENTIAL_LIST_SEE_TERMS));
				
				fb.setStrCheckSee("0");
				fb.setRefIndexCode(null);
				fb.setStrRefModifier1(null);
									
				if(listSeeTerms!=null && listSeeTerms.size()>0)
				{
					IcdIndexLevelMasterVO vo = listSeeTerms.get(0);
					if(fb.getSeeTermRadio().equals("0"))
					{
						if((vo.getSeeTermCode()!=null && !vo.getSeeTermCode().trim().equals("")))
						{
							fb.setRefIndexCode(vo.getSeeTermCode());
							if((vo.getSeeIndexModifierId()!=null && !vo.getSeeIndexModifierId().trim().equals("")))
							{
								fb.setStrRefModifier1(vo.getSeeIndexModifierId());
								
								List<Entry> listModifier = IcdCrossRefMasterDATA.getModifier(fb.getRefIndexCode(),userVO);
								WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_REFTERM, listModifier);
								fb.setStrCheckSee("1");
							}		
						}
					}	
					else if(fb.getSeeTermRadio().equals("1"))
					{
						if ((vo.getSeeAlsoTermCode()!=null && !vo.getSeeAlsoTermCode().trim().equals("")))
						{
							fb.setRefIndexCode(vo.getSeeAlsoTermCode());
							if((vo.getSeeAlsoIndexModifierId()!=null && !vo.getSeeAlsoIndexModifierId().trim().equals("")))
							{
								fb.setStrRefModifier1(vo.getSeeAlsoIndexModifierId());

								List<Entry> listModifier = IcdCrossRefMasterDATA.getModifier(fb.getRefIndexCode(),userVO);
								WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_ICD_MODIFIER_LEVEL1_REFTERM, listModifier);
								fb.setStrCheckSee("1");
							}							
						}	
					}
			
				}
				WebUTIL.setAttributeInSession(request, OpdConfig.OPD_ESSENTIAL_LIST_SEE_TERMS, listSeeTerms);
			}
			catch (HisRecordNotFoundException e)
			{
				objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, "", "Error");
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}	
		}
		
		
}
