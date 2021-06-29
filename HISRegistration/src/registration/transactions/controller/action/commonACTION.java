package registration.transactions.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;

public class commonACTION extends DispatchAction {
	/**
	 * the default action called when a page is loaded for the first time
	* @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "UNSPECIFIEDMODE"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("UNSPECIFIEDMODE");
	}
	/**
	 * sets the view to CHANGE PATIENT CATEGORY 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "CHANGEPAT"
	 */
	 
	public ActionForward CHANGEPATCAT (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("CHANGEPAT");
	}
	
	/**
	 * sets the view to CHANGE SECONDARY CATEGORY 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "CHANGEPAT"
	 */
	 
	public ActionForward SECONDARYCAT (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("SECONDARYCAT");
	}
	
	public ActionForward FILENO (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("FILENO");
	}
	
	
	public ActionForward RENEWAL (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("RENEWAL");
	}
	
	
	public ActionForward NEWSPECIALCLINICVISIT (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("NEWSPECIALCLINICVISIT");
	}
	
	public ActionForward NEWSPECIALCLINICVISITWITHAPPPOINTMENT (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("NEWSPECIALCLINICVISITWITHAPPPOINTMENT");
	}
	
	/**
	 * sets the view to OLD PATIENT VISIT
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest*
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "OLDPATVISIT"
	 */
	public ActionForward OLDPATVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("OLDPATVISIT"); 
	}	
	/**
	 * sets the view to UNIT TRANSFER
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "UNITTRANSFER"
	 */
	public ActionForward UNITTRANSFER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("UNITTRANSFER");
	}	
	/**
	 * sets the view to EMERGENCY CMO REGISTER
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "EMGCMOREGISTER"
	 */
	public ActionForward CMOREGISTER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("EMGCMOREGISTER");
	}
	/**
	 * sets the view to OPD DOCTOR DESK
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "OPDDOCTORDESK"
	 */
	public ActionForward OPDDOCTORDESK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("OPDDOCTORDESK");
	}
	/**
	 * sets the view to EMERGENCY NEW REGISTRATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "EMGNEWREG"
	 */
	public ActionForward EMGNEWREG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("EMGNEWREG");
	}
	/**
	 * sets the view to ADDRESS MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "ADDRESSMODIFICATION"
	 */
	public ActionForward ADDRESSMODIFICATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("ADDRESSMODIFICATION");
	}
	/**
	 * sets the view to DUPLICATE CARD GENERATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "DUPLICATECARDGENERATION"
	 */
	public ActionForward DUPLICATECARDGENERATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("DUPLICATECARDGENERATION");
	}

	/**
	 * sets the view to MLC DETAILS
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "MLCDTL"
	 */
	public ActionForward MLCDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("MLCDTL");	 	
	}
	/**
	 * sets the view to MLC DETAILS MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "MLCDTLMOD"
	 */
	public ActionForward MLCDTLMOD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("MLCDTLMOD");	 	
	}	
	/**
	 * sets the view to OLD PATIENT VISIT DETAIL
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "OldPatientVisitDtl"
	 */
	public ActionForward OldPatientVisitDtl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("OldPatientVisitDtl");
	}
	/**
	 * sets the view to EMERGENCY OLD PATIENT VISIT STAMP
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "EMGOLDPATVISIT"
	 */
	public ActionForward EMGOLDPATVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("EMGOLDPATVISIT");
	}
	/**
	 * sets the view to EMERGENCY PATIENT DETAIL MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "EMGPATDTLMOD"
	 */
	public ActionForward EMGPATDTLMOD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("EMGPATDTLMOD");
	}
	/**
	 * sets the view to MRD PATIENT DETAIL MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "MRDPATDTLMOD"
	 */
	public ActionForward MRDPATDTLMOD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("MRDPATDTLMOD");
	}
	/**
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "FILEUPLOAD"
	 */
	public ActionForward FILEUPLOAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("FILEUPLOAD");
	}
	/**
	 * sets the view to UNKNOWN TO KNOWN CONVERSION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "UNKNOWNTOKNOWN"
	 */
	public ActionForward UNKNOWNTOKNOWN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("UNKNOWNTOKNOWN");
	}
	/**
	 * sets the view to REFER DETAIL MODIFICATION
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "REFDTLMOD"
	 */
	public ActionForward REFDTLMOD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("REFDTLMOD");
	}
	/**
	 * sets the view to REFER DEPARTMENT VISIT STAMP
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "REFDEPTVISITSTAMP"
	 */
	public ActionForward REFDEPTVISITSTAMP (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	 	return mapping.findForward("REFDEPTVISITSTAMP");
	}
	/**
	 * sets the view to MLC DOCUMENT UPLOAD
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "MLCDOCUPLOAD"
	 */
	public ActionForward MLCDOCUPLOAD (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("MLCDOCUPLOAD");
	}
	
	public ActionForward PATIENTMOD (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("PATIENTMOD");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	    WebUTIL.refreshTransState(request,"commonACTION");		
		return mapping.findForward("CANCEL");
    }
	public ActionForward SPLCLINICNEWREG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("SPLCLINICNEWREG");
	}
	
	public ActionForward EMPWITHDEPENDENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	return mapping.findForward("EMPWITHDEPENDENT");
	}
	
	public ActionForward YELLOWSLIP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	 	return mapping.findForward("YELLOWSLIP");
	}
	public ActionForward OPENEPISODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("OPENEPISODE");
	}
	
	public ActionForward PATIENTREFERRAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("PATIENTREFERRAL");
	}
	
	public ActionForward PARICHITREGISTRATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("PARICHITREGISTRATION");
	}
	
	public ActionForward MLCTONONMLC(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("MLCTONONMLC");
	}
	
	public ActionForward ROOMWISENEWREG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("ROOMWISENEWREG");
	}
	
	public ActionForward PATDTLMODTIMEBOUND(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("PATDTLMODTIMEBOUND");
	}
	public ActionForward EMGPATIENTMODIFICATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("EMGPATIENTMODIFICATION");
	}
	public ActionForward NEWPATREG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("NEWPATREG");
	}
	
	public ActionForward NEWDEPTVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("NEWDEPTVISIT");
	}
	
	public ActionForward EMGNEWPATREG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("EMGNEWPATREG");
	}
	
	public ActionForward EMGNEWDEPTVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("EMGNEWDEPTVISIT");
	}
	/*OPD Patient Visit CR dated 14 july 2011*/
	public ActionForward PATIENTVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	 	
		return mapping.findForward("PATIENTVISIT");
	}
	
	/*Emergency Patient Visit CR dated 06 Aug 2011*/
	public ActionForward EMERGENCYPATIENTVISIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
	 	
		return mapping.findForward("EMERGENCYPATIENTVISIT");
	}
	
	/**
	 * sets the view to MCTS Registration 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "CHANGEPAT"
	 */
	 
	public ActionForward MCTSREG (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("inside MCTSREG ");		
	 	return mapping.findForward("MCTSREG");
	}
	
	public ActionForward SPLCLINICNEWREGWITHAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	 	CharacterEncoding.setCharacterEncoding(request);
		return mapping.findForward("SPLCLINICNEWREGWITHAPPOINTMENT");
	}
}