package mrd.transaction.controller.action;

import hisglobal.exceptions.HisReportDataNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFileControlUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.transaction.controller.fb.BulletinGeneartionFB;
import mrd.transaction.controller.utl.BulletinGenerationUTL;
import mrd.vo.BulletinGenerationVO;
import mrd.vo.BulletinHeadVO;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.EHRConfig;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.dataentry.spp.presentation.util.UniPagePrescriptionUTL;
import emr.vo.EHR_PatientProfileDtlVO;

public class BulletinGenerationACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BulletinGeneartionFB fb = (BulletinGeneartionFB) form;
		//fb.reset(mapping, request);
		// WebUTIL.refreshTransState(request);
		BulletinGenerationUTL.getBulletinelist(fb, request);

		return mapping.findForward("NEW");
	}

	public ActionForward BULLETIN_DETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		BulletinGeneartionFB fb = (BulletinGeneartionFB) form;
		HttpSession session = request.getSession();
		fb.reset(mapping, request);
		if(BulletinGenerationUTL.getPrintDataForBulletin(fb, request,response))
		{
			Status objStatus = new Status();
			BulletinGenerationUTL.getPdfDoc(request, response);
			objStatus.add(Status.TRANSINPROCESS, "Data Saved Successfully", "");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}

		return null;
	}

}