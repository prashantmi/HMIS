package mrd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mrd.transaction.bo.MrdEssentialBO;
import mrd.vo.BulletinGenerationVO;
import mrd.vo.BulletinHeadDataVO;
import mrd.vo.BulletinHeadVO;

public class BulletinGenerationDATA extends ControllerDATA
{
	// All Desk Type List
	public static Map getBulletinelist(UserVO _UserVO)
	{
		MrdEssentialBO essentialDelegate = new MrdEssentialBO();
		return essentialDelegate.getBulletinelist(_UserVO);
	}

	public static List<BulletinHeadVO> getMRDBulletinTemplatePrintList(BulletinGenerationVO bulletinVO, UserVO userVO)
	{

		MrdEssentialBO essentialDelegate = new MrdEssentialBO();
		List<BulletinHeadVO> lstBulletinTemplatePrint = new ArrayList<BulletinHeadVO>();
		lstBulletinTemplatePrint = essentialDelegate.getClinicalSectionTemplatePrintList(bulletinVO, userVO);
		return lstBulletinTemplatePrint;
	}

	public static List<BulletinHeadDataVO> getDataFromQuery(String query, UserVO voUser)
	{

		MrdEssentialBO essentialDelegate = new MrdEssentialBO();
		return essentialDelegate.getDataFromQuery(query, voUser);
	}
	//insertHtmlData
	public static void insertHtmlData(BulletinGenerationVO bulletinVO)
	{

		MrdEssentialBO essentialDelegate = new MrdEssentialBO();
		essentialDelegate.insertHtmlData(bulletinVO);
		
	}
}