package registration.masters.controller.actionsupport;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class RenewalConfigSUP extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware
{
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;

	@SuppressWarnings("rawtypes")
	protected Map mapSession;

	protected String   strHospitalCode=null;
	protected String   chkCommonCode=null;
	
	protected String[] strRenewalGroup;
	protected String[] strRenewalGroupGen;
	protected String[] strRenewalGroupEmg;
	protected String[] strRenewalGroupSpl;
	protected String[] strRenewalType;
	protected String[] strRenewalMode;
	protected String[] strNoOfDays;
	protected String[] strIsMultipleMonth;
	protected String[] strMonth1;
	protected String[] strMonth2;
	protected String[] strMonth3;
	protected String[] strRenewalTypeGen;
	protected String[] strRenewalTypeSpl;
	protected String[] strRenewalTypeEmg;
	protected String[] strRenewalModeGen;
	protected String[] strRenewalModeSpl;
	protected String[] strRenewalModeEmg;

	protected String[] strNoOfDaysGen;
	protected String[] strNoOfDaysSpl;
	protected String[] strNoOfDaysEmg;
	
	protected String[] strMonth1Gen;
	protected String[] strMonth1Spl;
	protected String[] strMonth1Emg;
	
	protected String[] strIsMultipleMonthGen;
	protected String[] strIsMultipleMonthSpl;
	protected String[] strIsMultipleMonthEmg;

	protected String[] strMonth2Gen;
	protected String[] strMonth2Spl;
	protected String[] strMonth2Emg;

	protected String[] strMonth3Gen;
	protected String[] strMonth3Spl;
	protected String[] strMonth3Emg;
	
	protected String[] strDay1Gen;
	protected String[] strDay1Emg;
	protected String[] strDay1Spl;
	
	protected String[] strDay2Gen;
	protected String[] strDay2Emg;
	protected String[] strDay2Spl;
	
	protected String[] strDay3Gen;
	protected String[] strDay3Emg;
	protected String[] strDay3Spl;

	
	protected String[] strDay1;
	protected String[] strDay2;
	protected String[] strDay3;
	protected String[] strRenewalPatCat;
	protected String[] strRenewalPatCatGen;
	protected String[] strRenewalPatCatEmg;
	protected String[] strRenewalPatCatSpl;
	
	protected String strRenewalTypeCommonToAll;
	protected String strPatCatOptions;
	
	protected String flagAddMod=null;
	
	protected String strMsg=null;
	
	
	public String getStrMsg() {
		return strMsg;
	}


	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}


	public void clear()
	{
		strRenewalGroup=null;
		strRenewalGroupGen=null;
		strRenewalGroupEmg=null;
		strRenewalGroupSpl=null;
		strHospitalCode=null;
		strRenewalType=null;
		strRenewalMode=null;
		strNoOfDays=null;
		strIsMultipleMonth=null;
		strMonth1=null;
		strMonth2=null;
		strMonth3=null;
		chkCommonCode=null;
		strRenewalTypeGen=null;
		strRenewalTypeSpl=null;
		strRenewalTypeEmg=null;
		strRenewalModeGen=null;
		strRenewalModeSpl=null;
		strRenewalModeEmg=null;

		strNoOfDaysGen=null;
		strNoOfDaysSpl=null;
		strNoOfDaysEmg=null;
		
		strMonth1Gen=null;
		strMonth1Spl=null;
		strMonth1Emg=null;
		
		strIsMultipleMonthGen=null;
		strIsMultipleMonthSpl=null;
		strIsMultipleMonthEmg=null;

		strMonth2Gen=null;
		strMonth2Spl=null;
		strMonth2Emg=null;

		strMonth3Gen=null;
		strMonth3Spl=null;
		strMonth3Emg=null;
		
		strDay1Gen=null;
		strDay1Emg=null;
		strDay1Spl=null;
		
		strDay2Gen=null;
		strDay2Emg=null;
		strDay2Spl=null;
		
		strDay3Gen=null;
		strDay3Spl=null;

		strDay3Emg=null;
		
		strDay1=null;
		strDay2=null;
		strDay3=null;
		//strMsg="";
		
		
	}
	
	
	
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getChkCommonCode() {
		return chkCommonCode;
	}
	public void setChkCommonCode(String chkCommonCode) {
		this.chkCommonCode = chkCommonCode;
	}
	public String[] getStrRenewalGroup() {
		return strRenewalGroup;
	}
	public void setStrRenewalGroup(String[] strRenewalGroup) {
		this.strRenewalGroup = strRenewalGroup;
	}
	public String[] getStrRenewalGroupGen() {
		return strRenewalGroupGen;
	}
	public void setStrRenewalGroupGen(String[] strRenewalGroupGen) {
		this.strRenewalGroupGen = strRenewalGroupGen;
	}
	public String[] getStrRenewalGroupEmg() {
		return strRenewalGroupEmg;
	}
	public void setStrRenewalGroupEmg(String[] strRenewalGroupEmg) {
		this.strRenewalGroupEmg = strRenewalGroupEmg;
	}
	public String[] getStrRenewalGroupSpl() {
		return strRenewalGroupSpl;
	}
	public void setStrRenewalGroupSpl(String[] strRenewalGroupSpl) {
		this.strRenewalGroupSpl = strRenewalGroupSpl;
	}
	public String[] getStrRenewalType() {
		return strRenewalType;
	}
	public void setStrRenewalType(String[] strRenewalType) {
		this.strRenewalType = strRenewalType;
	}
	public String[] getStrRenewalMode() {
		return strRenewalMode;
	}
	public void setStrRenewalMode(String[] strRenewalMode) {
		this.strRenewalMode = strRenewalMode;
	}
	public String[] getStrNoOfDays() {
		return strNoOfDays;
	}
	public void setStrNoOfDays(String[] strNoOfDays) {
		this.strNoOfDays = strNoOfDays;
	}
	public String[] getStrIsMultipleMonth() {
		return strIsMultipleMonth;
	}
	public void setStrIsMultipleMonth(String[] strIsMultipleMonth) {
		this.strIsMultipleMonth = strIsMultipleMonth;
	}
	public String[] getStrMonth1() {
		return strMonth1;
	}
	public void setStrMonth1(String[] strMonth1) {
		this.strMonth1 = strMonth1;
	}
	public String[] getStrMonth2() {
		return strMonth2;
	}
	public void setStrMonth2(String[] strMonth2) {
		this.strMonth2 = strMonth2;
	}
	public String[] getStrMonth3() {
		return strMonth3;
	}
	public void setStrMonth3(String[] strMonth3) {
		this.strMonth3 = strMonth3;
	}
	public String[] getStrRenewalTypeGen() {
		return strRenewalTypeGen;
	}
	public void setStrRenewalTypeGen(String[] strRenewalTypeGen) {
		this.strRenewalTypeGen = strRenewalTypeGen;
	}
	public String[] getStrRenewalTypeSpl() {
		return strRenewalTypeSpl;
	}
	public void setStrRenewalTypeSpl(String[] strRenewalTypeSpl) {
		this.strRenewalTypeSpl = strRenewalTypeSpl;
	}
	public String[] getStrRenewalTypeEmg() {
		return strRenewalTypeEmg;
	}
	public void setStrRenewalTypeEmg(String[] strRenewalTypeEmg) {
		this.strRenewalTypeEmg = strRenewalTypeEmg;
	}
	public String[] getStrRenewalModeGen() {
		return strRenewalModeGen;
	}
	public void setStrRenewalModeGen(String[] strRenewalModeGen) {
		this.strRenewalModeGen = strRenewalModeGen;
	}
	public String[] getStrRenewalModeSpl() {
		return strRenewalModeSpl;
	}
	public void setStrRenewalModeSpl(String[] strRenewalModeSpl) {
		this.strRenewalModeSpl = strRenewalModeSpl;
	}
	public String[] getStrRenewalModeEmg() {
		return strRenewalModeEmg;
	}
	public void setStrRenewalModeEmg(String[] strRenewalModeEmg) {
		this.strRenewalModeEmg = strRenewalModeEmg;
	}
	public String[] getStrNoOfDaysGen() {
		return strNoOfDaysGen;
	}
	public void setStrNoOfDaysGen(String[] strNoOfDaysGen) {
		this.strNoOfDaysGen = strNoOfDaysGen;
	}
	public String[] getStrNoOfDaysSpl() {
		return strNoOfDaysSpl;
	}
	public void setStrNoOfDaysSpl(String[] strNoOfDaysSpl) {
		this.strNoOfDaysSpl = strNoOfDaysSpl;
	}
	public String[] getStrNoOfDaysEmg() {
		return strNoOfDaysEmg;
	}
	public void setStrNoOfDaysEmg(String[] strNoOfDaysEmg) {
		this.strNoOfDaysEmg = strNoOfDaysEmg;
	}
	public String[] getStrMonth1Gen() {
		return strMonth1Gen;
	}
	public void setStrMonth1Gen(String[] strMonth1Gen) {
		this.strMonth1Gen = strMonth1Gen;
	}
	public String[] getStrMonth1Spl() {
		return strMonth1Spl;
	}
	public void setStrMonth1Spl(String[] strMonth1Spl) {
		this.strMonth1Spl = strMonth1Spl;
	}
	public String[] getStrMonth1Emg() {
		return strMonth1Emg;
	}
	public void setStrMonth1Emg(String[] strMonth1Emg) {
		this.strMonth1Emg = strMonth1Emg;
	}
	public String[] getStrIsMultipleMonthGen() {
		return strIsMultipleMonthGen;
	}
	public void setStrIsMultipleMonthGen(String[] strIsMultipleMonthGen) {
		this.strIsMultipleMonthGen = strIsMultipleMonthGen;
	}
	public String[] getStrIsMultipleMonthSpl() {
		return strIsMultipleMonthSpl;
	}
	public void setStrIsMultipleMonthSpl(String[] strIsMultipleMonthSpl) {
		this.strIsMultipleMonthSpl = strIsMultipleMonthSpl;
	}
	public String[] getStrIsMultipleMonthEmg() {
		return strIsMultipleMonthEmg;
	}
	public void setStrIsMultipleMonthEmg(String[] strIsMultipleMonthEmg) {
		this.strIsMultipleMonthEmg = strIsMultipleMonthEmg;
	}
	public String[] getStrMonth2Gen() {
		return strMonth2Gen;
	}
	public void setStrMonth2Gen(String[] strMonth2Gen) {
		this.strMonth2Gen = strMonth2Gen;
	}
	public String[] getStrMonth2Spl() {
		return strMonth2Spl;
	}
	public void setStrMonth2Spl(String[] strMonth2Spl) {
		this.strMonth2Spl = strMonth2Spl;
	}
	public String[] getStrMonth2Emg() {
		return strMonth2Emg;
	}
	public void setStrMonth2Emg(String[] strMonth2Emg) {
		this.strMonth2Emg = strMonth2Emg;
	}
	public String[] getStrMonth3Gen() {
		return strMonth3Gen;
	}
	public void setStrMonth3Gen(String[] strMonth3Gen) {
		this.strMonth3Gen = strMonth3Gen;
	}
	public String[] getStrMonth3Spl() {
		return strMonth3Spl;
	}
	public void setStrMonth3Spl(String[] strMonth3Spl) {
		this.strMonth3Spl = strMonth3Spl;
	}
	public String[] getStrMonth3Emg() {
		return strMonth3Emg;
	}
	public void setStrMonth3Emg(String[] strMonth3Emg) {
		this.strMonth3Emg = strMonth3Emg;
	}
	public String[] getStrDay1Gen() {
		return strDay1Gen;
	}
	public void setStrDay1Gen(String[] strDay1Gen) {
		this.strDay1Gen = strDay1Gen;
	}
	public String[] getStrDay1Emg() {
		return strDay1Emg;
	}
	public void setStrDay1Emg(String[] strDay1Emg) {
		this.strDay1Emg = strDay1Emg;
	}
	public String[] getStrDay1Spl() {
		return strDay1Spl;
	}
	public void setStrDay1Spl(String[] strDay1Spl) {
		this.strDay1Spl = strDay1Spl;
	}
	public String[] getStrDay2Gen() {
		return strDay2Gen;
	}
	public void setStrDay2Gen(String[] strDay2Gen) {
		this.strDay2Gen = strDay2Gen;
	}
	public String[] getStrDay2Emg() {
		return strDay2Emg;
	}
	public void setStrDay2Emg(String[] strDay2Emg) {
		this.strDay2Emg = strDay2Emg;
	}
	public String[] getStrDay2Spl() {
		return strDay2Spl;
	}
	public void setStrDay2Spl(String[] strDay2Spl) {
		this.strDay2Spl = strDay2Spl;
	}
	public String[] getStrDay3Gen() {
		return strDay3Gen;
	}
	public void setStrDay3Gen(String[] strDay3Gen) {
		this.strDay3Gen = strDay3Gen;
	}
	public String[] getStrDay3Emg() {
		return strDay3Emg;
	}
	public void setStrDay3Emg(String[] strDay3Emg) {
		this.strDay3Emg = strDay3Emg;
	}
	public String[] getStrDay3Spl() {
		return strDay3Spl;
	}
	public void setStrDay3Spl(String[] strDay3Spl) {
		this.strDay3Spl = strDay3Spl;
	}
	public String[] getStrDay1() {
		return strDay1;
	}
	public void setStrDay1(String[] strDay1) {
		this.strDay1 = strDay1;
	}
	public String[] getStrDay2() {
		return strDay2;
	}
	public void setStrDay2(String[] strDay2) {
		this.strDay2 = strDay2;
	}
	public String[] getStrDay3() {
		return strDay3;
	}
	public void setStrDay3(String[] strDay3) {
		this.strDay3 = strDay3;
	}
	public String getFlagAddMod() {
		return flagAddMod;
	}
	public void setFlagAddMod(String flagAddMod) {
		this.flagAddMod = flagAddMod;
	}

	public String[] getStrRenewalPatCat() {
		return strRenewalPatCat;
	}


	public void setStrRenewalPatCat(String[] strRenewalPatCat) {
		this.strRenewalPatCat = strRenewalPatCat;
	}


	public String[] getStrRenewalPatCatGen() {
		return strRenewalPatCatGen;
	}


	public void setStrRenewalPatCatGen(String[] strRenewalPatCatGen) {
		this.strRenewalPatCatGen = strRenewalPatCatGen;
	}


	public String[] getStrRenewalPatCatEmg() {
		return strRenewalPatCatEmg;
	}


	public void setStrRenewalPatCatEmg(String[] strRenewalPatCatEmg) {
		this.strRenewalPatCatEmg = strRenewalPatCatEmg;
	}


	public String[] getStrRenewalPatCatSpl() {
		return strRenewalPatCatSpl;
	}


	public void setStrRenewalPatCatSpl(String[] strRenewalPatCatSpl) {
		this.strRenewalPatCatSpl = strRenewalPatCatSpl;
	}


	public String getStrRenewalTypeCommonToAll() {
		return strRenewalTypeCommonToAll;
	}


	public void setStrRenewalTypeCommonToAll(String strRenewalTypeCommonToAll) {
		this.strRenewalTypeCommonToAll = strRenewalTypeCommonToAll;
	}


	public String getStrPatCatOptions() {
		return strPatCatOptions;
	}


	public void setStrPatCatOptions(String strPatCatOptions) {
		this.strPatCatOptions = strPatCatOptions;
	}


	/////////////////////////////////////////////////////////////////
	public Map getMapSession() {
		return mapSession;
	}

	public void setMapSession(Map mapSession) {
		this.mapSession = mapSession;
	}

	public HttpServletRequest getObjRequest()
	{
		return objRequest;
	}

	public void setObjRequest(HttpServletRequest objRequest)
	{
		this.objRequest = objRequest;
	}

	public HttpServletResponse getObjResponse()
	{
		return objResponse;
	}

	public void setObjResponse(HttpServletResponse objResponse)
	{
		this.objResponse = objResponse;
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map sessionMap)
	{
		this.mapSession = sessionMap;
	}

	

}
