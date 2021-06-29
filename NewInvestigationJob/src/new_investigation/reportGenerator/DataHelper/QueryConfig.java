/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_investigation.reportGenerator.DataHelper;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public interface QueryConfig {

    /**
     * Convention Prefix P_ before Procedure Calls Prefix Q_ before Queries
     */
    public String P_GET_CRNO_XML_JOBS = "{call pkg_inv_report_job.getpatientforjobprocessing(?,?,?,?)}";
    
    public String GET_CR_DTL_XML_JOBS = "select hrgnum_puk_reqd as patCRNoorrequestno, gnum_bill_no AS billNo, hivnum_req_no requisitionNo ,hivtnum_req_dno requisitionDNo ,DECODE(a.hivnum_reqdtl_status, 2, 'Requisition Raised', 3, 'Sample Collected', 4, 'Packing List Generated', 5, 'Requisition Raised', 6, 'Sample Accepted', 7, 'Result Entered', 8, 'Result Validated', 9, 'Patient Rejected', 10, 'Test Resceduled', 11, 'Test Rescheduled', 12, 'Sample Cancelled', 13, 'Waiting For Report', 14, 'Result Printed', 15, 'Test Cancelled', 16, 'Test Deleted', 26, 'Report Generated', 55, 'Test Advised', 17, 'Machine Processing', 18, 'Machine Processing', 27, ' Draft- Result Entry', 28, 'Draft-Result Validation') AS requisitionStatus, hivnum_reqdtl_status AS requisitionStatusCode, 1 updationType ,0 as GNUM_STATUS_CODE ,hivdt_result_date as HIVDT_ENTRY_DATE ,0 as HIVTNUM_AS_NO ,gnum_hospital_code as hospitalcode ,gnum_episode_code_reqd as patEpisodeCode ,hrgnum_puk_reqd as patCRNo ,hgnum_visitno_reqd as patVisitNo ,hivdt_requisition_date_reqd as requisitionDate ,2 as requisitionTypeCode ,hgnum_visit_date_reqd as visitDate ,hgnum_dept_code_reqd as deptCode ,hgnum_deptunitcode_reqd as detpUnitCode ,gnum_test_code as testcode ,(	SELECT b.gstr_test_name FROM hivt_test_mst b WHERE b.gnum_isvalid = 1 AND b.gnum_test_code = a.gnum_test_code ) AS testName ,hgnum_group_code AS groupCode ,hgstr_daily_labno AS labNo ,gnum_lab_code AS laboratoryCode ,(	SELECT GSTR_LAB_NAME FROM HIVT_LABORATORY_MST c WHERE c.gnum_isvalid = 1 AND c.GNUM_HOSPITAL_CODE = a.GNUM_HOSPITAL_CODE AND c.GNUM_LAB_CODE = a.GNUM_LAB_CODE ) AS laboratoryName ,hivnum_temp_sample_no as tempSampleNo ,hivnum_sample_no as samplecode ,gnum_sample_code AS sampleTypecode ,gstr_gender_code_reqd AS patGender ,(	SELECT gstr_Sample_name FROM hivt_sample_mst WHERE gnum_isvalid = 1 AND gnum_sample_code = a.gnum_sample_code ) AS sampleName ,(hivnum_temp_sample_no|| '/' ||hgstr_daily_labno)::character varying(50) AS sampleNo ,0 AS sessionId ,1 AS updateType ,hivstr_age_reqd AS patAge ,0 AS isDeptEntry ,decode(( SELECT (gnum_test_seq_no) FROM hivt_test_group_info_mst cb WHERE cb.gnum_test_code = a.gnum_test_code AND cb.gnum_hospital_code = a.gnum_hospital_code AND gnum_is_valid = 1 AND cb.hgnum_group_code = a.hgnum_group_code ), NULL, 1 ,( SELECT (gnum_test_seq_no) FROM hivt_test_group_info_mst cba WHERE cba.gnum_test_code = a.gnum_test_code AND cba.gnum_hospital_code = a.gnum_hospital_code AND gnum_is_valid = 1 AND cba.hgnum_group_code = a.hgnum_group_code ))::NUMERIC AS grp from hivt_requisition_dtl a WHERE gnum_hospital_code=? AND hrgnum_puk_reqd=? AND hivnum_reqdtl_status in (13,26,14, 7,8,27,28) AND hivnum_req_no=? AND gnum_isvalid_reqd = 1 ORDER BY grp";
    
    //public String Q_GET_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code FROM hivt_parameter_dtl hpd WHERE hpd.hivtnum_req_dno =?";
   
    public String Q_GET_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code,hivstr_loinc_string, hivstr_ref_range,hivtnum_test_code FROM hivt_parameter_dtl hpd WHERE gnum_isvalid = 1 and hpd.hivtnum_req_dno = ? and gnum_hospital_code=?";
    
    /*public String Q_GET_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code,hivstr_loinc_string, hivstr_ref_range,hivtnum_test_code FROM hivt_parameter_dtl hpd,hivt_test_parameter_mst WHERE hpd.gnum_isvalid = 1 and hpd.hivtnum_req_dno = ? and gnum_hospital_code=? and hivtnum_test_code=gnum_test_code and hivnum_parameter_code=gnum_parameter_code and gstr_element_type not in ('J') ";
    */
    // I think it would be better to pass a String 
    public String Q_DELETE_XML_JOBS = "delete from hivt_jobworkorder_data where  GNUM_STATUS_CODE = 0 and  HIVNUM_REQORCR_NO=? and HIVNUM_REQ_DNO in (?)";

    public String Q_GET_PRINTED_WITHSTANDARD_RANGES = "select hivtnum_test_printing_type from hivt_laboratory_test_mst where gnum_test_code=? and gnum_labcode=? and gnum_hospital_code=? and gnum_isvalid=1";
    // Queries for XML Job
    //  public String Q_GET_XML_WORKORDERLIST = "select crnos,sl from (SELECT HJWD.HIVNUM_REQORCR_NO as crnos, string_agg (HJWD.HIVNUM_REQ_DNO, ',' ORDER BY HJWD.HIVNUM_REQ_DNO) sl FROM hivt_jobworkorder_data hjwd   WHERE   HIVDT_ENTRY_DATE<=(SYSDATE-(1/1440)) AND HJWD.GNUM_STATUS_CODE = 1 GROUP BY HJWD.HIVNUM_REQORCR_NO) where rownum=1";
  //  public String Q_GET_XML_WORKORDERDATALIST = "SELECT HIVNUM_REQORCR_NO as patCRNoorrequestno,HIVNUM_REQ_DNO as requisitionDNo,HIVNUM_UPDATION_TYPE as updationType,GNUM_STATUS_CODE,HIVDT_ENTRY_DATE,HIVTNUM_AS_NO,GNUM_HOSPITAL_CODE as hospitalcode,HIVNUM_REQ_NO as requisitionno,HRGNUM_EPISODE_CODE as patEpisodeCode,HRGNUM_PUK as patCRNo,HGNUM_VISITNO as patVisitNo,HIVT_REQUISITION_DATE as requisitionDate,HRGSTR_EPISODE_CODE as patEpisodeCode,HBNUM_REQUISITION_TYPE as requisitionTypeCode,HGNUM_VISIT_DATE as visitDate,HGNUM_DEPT_CODE as deptCode, HGNUM_DEPTUNITCODE as detpUnitCode,GNUM_TEST_CODE as testcode,HGNUM_GROUP_CODE as groupcode,HGNUM_LAB_NO as labNo, GNUM_CURRENT_LAB_CODE as laboratoryCode,0 as sessionId,HIVNUM_UPDATION_TYPE as updateType,HIVNUM_SAMPLE_CODE AS sampleNo,HIVSTR_PAT_AGE AS patAge,GNUM_GENDER_CODE as patGender FROM hivt_jobworkorder_data WHERE HIVNUM_REQ_DNO = ANY(?)";
    // get stylesheet
    public String Q_GET_STYLESHEET = "SELECT hssm.hivtclob_stylesheet FROM hivt_stylesheet_store_mst hssm WHERE hssm.hivtnum_stylesheet_id = ?";

    // get test template
    public String Q_GET_TESTTEMPLATE_XML = "SELECT hssm.hivtclob_document FROM hivt_document_store_mst hssm WHERE hssm.hivtnum_document_id = 1";

    // get test template status for dynamic printing
    public String Q_GET_DYNAMIC_TESTTEMPLATE_STATUS = "SELECT gnum_isdynamic_template, gnum_dynamictemplate_id FROM hivt_test_mst WHERE gnum_test_code = ? and gnum_isvalid = 1 and gnum_isdynamic_template=1";
    
    // get the dynamic test template
    
    public String Q_GET_DYNAMIC_TESTTEMPLATE = "SELECT hivtstr_template_xml  FROM hivt_printing_template WHERE hivt_dynamictemplate_id=? and gnum_isvalid=1";
    public String Q_GET_DYNAMIC_GROUPTEMPLATE_STATUS =  "SELECT gnum_isdynamic_template, gnum_dynamictemplate_id FROM hivt_test_group_info_mst WHERE hgnum_group_code = ? and gnum_hospital_code = ? and gnum_lab_code = ? and gnum_is_valid = 1 LIMIT 1";
    /**
     * Queries and Procedures for generating PDF
     */
    public String Q_GET_PDF_WORKORDERLIST = "SELECT hivnum_req_no, gnum_lab_code, gnum_hospital_code FROM (SELECT   hivnum_req_no, gnum_lab_code, gnum_hospital_code FROM hivt_job_data WHERE gnum_status_code = 1 ORDER BY hivdt_entry_date, sno ASC) WHERE ROWNUM = 1";
    public String P_GET_PDF_WORKORDERLIST = "{call pkg_inv_report_job.SingleDataforPDFjob(?,?,?,?)}";
    public String P_GET_PDF_WORKORDERLISTDATA = "{call pkg_inv_report_job.getworkorderlistforpdfjob(?,?,?,?,?,?,?,?)}";
    public String P_GET_PDF_WORKORDERLISTDATA_PM = "{call pkg_inv_report_job.getworkorderlistforpdfjobpm(?,?,?,?,?,?,?,?,?)}";
    
    public String P_GET_PDF_FILE_WORKORDERLISTDATA = "{call pkg_inv_report_job.getworkorderlistwihfilename(?,?,?,?,?)}";
    public String P_GET_PDF_UNIQUE_CODE = "{call pkg_inv_report_job.getmaxtransactionnoforpdf(?,?,?,?,?,?)}";
    public String Q_PUT_PDF_JOB = "INSERT INTO HIVT_JOB_DATA(GNUM_LAB_CODE, HIVNUM_REQ_NO,GNUM_STATUS_CODE,HIVTNUM_AS_NO, GNUM_HOSPITAL_CODE, SNO,gnum_isdept_entry) VALUES(?,?,?,?,?,?,?)";
    public String Q_SAVE_PDF_WORKORDERS = "INSERT INTO hivt_pdf_workorder_dtl(hrgnum_puk, hivtnum_req_dno, hivtstr_pdf_url, gnum_hospital_code,gnum_isdept_entry) VALUES (?,?,?,?,?)";
    public String Q_UPDATE_REQ_STATUS = "UPDATE HIVT_REQUISITION_DTL SET HIVNUM_REQDTL_STATUS = 26, HIVDT_REPORT_DATE=SYSDATE, HIVSTR_REPORT_URL=? WHERE HIVTNUM_REQ_DNO=?";
    public String Q_GET_REQ_DTL_STATUS_HIVT_REQ_DTL = "select hivnum_reqdtl_status from hivt_requisition_dtl where hivtnum_req_dno=?";
    
    //query for sms service
    public String Q_GET_REQ_SMS_DATA = "select hivnum_req_no,hrgstr_mobile_no,(select count(*) from hivt_requisition_dtl where hivnum_req_no=a.hivnum_req_no and gnum_hospital_code=a.gnum_hospital_code),(select count(*) from hivt_requisition_dtl where hivnum_req_no=a.hivnum_req_no and gnum_hospital_code=a.gnum_hospital_code and hivnum_reqdtl_status in (26,13,14)),(select INITCAP(gstr_lab_name) from hivt_laboratory_mst where gnum_isvalid=1 and gnum_lab_code=a.gnum_lab_code and gnum_hospital_code=a.gnum_hospital_code),INITCAP(hrgstr_patname),( select distinct(gnum_report_change) from hivt_requisition_dtl where hivnum_req_no=a.hivnum_req_no and gnum_hospital_code=a.gnum_hospital_code and hivnum_reqdtl_status in (26,13,14) ),TO_CHAR (a.hivdt_requisition_date, 'DD-Mon-YYYY')  from hivt_requisition_header a where gnum_isvalid=1 and hivnum_sms_flag=0  group by hivnum_req_no,hrgstr_mobile_no,gnum_hospital_code";
    public String Q_UPDATE_REQ_SMS_DATA="update hivt_requisition_header set hivnum_sms_flag=1 where gnum_isvalid=1 and hivnum_req_no=? ";
    
    //query to get dept test template id
    public String Q_GET_DEPT_TESTTEMPLATE_STATUS = "SELECT gnum_isdept_template, gnum_depttemplate_id FROM hivt_test_mst WHERE gnum_test_code = ? and gnum_isvalid = 1 and gnum_isdept_template=1";
    public String Q_GET_DEPT_RESULT_FORXML = "SELECT concat(HIVTNUM_TEST_CODE,hivnum_parameter_code), HIVTSTR_VALUE , hivtnum_parent_parameter_code,hivstr_loinc_string, hivstr_ref_range FROM hivt_dept_parameter_dtl hpd WHERE gnum_isvalid = 1 and hpd.hivtnum_req_dno = ? and gnum_hospital_code=? order by gnum_seq_no";
    public String Q_UPDATE_DEPT_REQ_STATUS = "UPDATE HIVT_REQUISITION_DTL SET hivnum_dept_reqdtl_status = 33, hivdt_dept_report_date=SYSDATE, hivstr_dept_report_url=? WHERE HIVTNUM_REQ_DNO=?";
    
    // query to get n save report history
public String FETCH_SERVICE_ID_REPORT_HISTORY = "select max(gnum_service_id+1) from hivt_report_service_dtl ";
    
    public String INSERT_HIVT_REPORT_SERVICE_DTL = "insert into hivt_report_service_dtl(gnum_service_id,gnum_isvalid,gdt_update_date,gdt_insert_date,gnum_frequency,gstr_mongo_db_name,gstr_mongo_uri,gstr_pg_uri,gstr_pg_username,gstr_report_path,gstr_report_service_path) values(?,1,sysdate,sysdate,?,?,?,?,?,?,?)";
    
    public String UPDATE_HIVT_REPORT_SERVICE_DTL = "update  hivt_report_service_dtl set gdt_update_date=sysdate where gnum_service_id=? and  gnum_isvalid=1 ";
    
    public String DELETE_HIVT_REPORT_SERVICE_DTL = "update  hivt_report_service_dtl set gdt_update_date=sysdate,gdt_lst_date=sysdate,gnum_isvalid=0 where gnum_service_id=? and  gnum_isvalid=1  ";


    public String FETCH_TEST_NAMES_ACCREDITED = "select array_to_string(array_agg(distinct gstr_test_name), ',') from hivt_test_mst where  gnum_isvalid=1 ";
    
    public String FETCH_TEST_CODES_ACCREDITED = " (select array_to_string(array_agg(distinct gnum_test_code), ',') as testCodeAccrebited from hivt_laboratory_test_mst where gnum_isvalid=1 and gnum_is_nabl_accredited_test=1 and gnum_hospital_code=?)";

    public String FETCH_IS_PORTAL = "select hivnum_isportal from hivt_header_dtl where gnum_isvalid=1 and gnum_hospital_code=?";
    
    public String FETCH_DATA = "select * from hrgt_patient_dtl where hrgnum_puk=?";
    
    public String FETCH_PDFS_HIVT_REQUISITION_HEADER = "select distinct a.hivstr_report_url  ,b.hrgnum_puk  from hivt_requisition_dtl a LEFT JOIN hivt_requisition_header b ON  a.hivnum_req_no=b.hivnum_req_no where a.hivnum_req_no=b.hivnum_req_no and  a.gnum_hospital_code=b.gnum_hospital_code and b.gnum_isvalid=1 and a.hivstr_report_url is not null ";

    public String INSERT_HIVT_SCHEDULER_CONFIG_DTL = "insert into hivt_scheduler_config_dtl(gdt_entry_date,is_run,scheduler_runningtime_hour,scheduler_runningtime_min) values(sysdate,?,?,?)";

    public String Q_UPDATE_REQ_STATUS_ALL = "UPDATE HIVT_REQUISITION_DTL SET  HIVSTR_REPORT_URL=? WHERE HIVNUM_REQ_NO=? and HIVNUM_REQDTL_STATUS in (26,14) and hivnum_sample_no=?";

    public String FETCH_PDFS_rquisitionno_samplecodewise = "select distinct a.hivstr_report_url    from hivt_requisition_dtl a where a.hivnum_req_no=? and hivnum_sample_no=? AND HIVNUM_REQDTL_STATUS in (26,14) and a.hivstr_report_url is not null ";

    public String P_GET_PDF_WORKORDERLISTDATA_NEW = "{call pkg_inv_report_job.getworkorderlistforpdfjobnew(?,?,?,?,?,?,?,?,?)}";
    
    public String P_GET_PDF_WORKORDERLISTDATA_NEW_PM = "{call pkg_inv_report_job.getworkorderlistforpdfjobnewpm(?,?,?,?,?,?,?,?,?)}";

    
    public String P_GET_PDF_WORKORDERLISTDATA_NEW_GROUP = "{call pkg_inv_report_job.getworkorderlistforpdfjobnewgroup(?,?,?,?,?,?,?,?,?)}";

    //public String SELECT_TEST_REPORT_SEPERATION = "select String_agg(gnum_test_code,'#')   from hivt_report_test where gnum_isvalid=1";
    public String SELECT_TEST_REPORT_SEPERATION ="select String_agg(gnum_test_code,'#')   from hivt_test_mst where gnum_isvalid=1 and issepratedpagerequired=1";

    public String Q_SAVE_INV_DTL_LOG = "INSERT INTO hint_phrms_upload_inv_dtl_log(gnum_isvalid,hrgnum_puk, gstr_ip_address,gnum_hospital_code,hivnum_req_no,hivtnum_req_dno,hgnum_visitno,hivtnum_lab_code,hivtstr_lab_name,hivtnum_test_code,hivtstr_test_name, gstr_base_64_inv_result ,gstr_lstmod_reason,hrgstr_mobile_no,hrgstr_email_id,hrgstr_national_id,hivdt_result_date,hivdt_requisition_date,gdt_entry_date) VALUES (1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'DD-Mon-YYYY HH24:MI:SS'),to_date(?,'DD-Mon-YYYY HH24:MI:SS'),sysdate)";
    public String Q_SAVE_INV_DTL_GROUP_LOG = "INSERT INTO hint_phrms_upload_inv_dtl_log(gnum_isvalid,hrgnum_puk, gstr_ip_address,gnum_hospital_code,hivnum_req_no,hivtnum_req_dno,hgnum_visitno,hivtnum_lab_code,hivtstr_lab_name,hivtnum_test_code,hivtstr_test_name, gstr_base_64_inv_result ,gstr_lstmod_reason,hrgstr_mobile_no,hrgstr_email_id,hrgstr_national_id,hivdt_result_date,hivdt_requisition_date,gdt_entry_date) VALUES (1,?,?,?,?,?,?,?,?,?,pkg_inv_fun.getgroupname(?,?),?,?,?,?,?,to_date(?,'DD-Mon-YYYY HH24:MI:SS'),to_date(?,'DD-Mon-YYYY HH24:MI:SS'),sysdate)";
    //fetch patient detail
    public String FETCH_PATIENT_DTL = "select hrgstr_mobile_no,hrgstr_email_id,hrgstr_national_id,hrgstr_fname from hrgt_patient_dtl  where hrgnum_puk  = ? AND gnum_isvalid=1";

    public String FETCH_CR_NOS = "SELECT DISTINCT hrgnum_puk FROM hivt_requisition_header where hivdt_entry_date <= ?::date and  hivdt_entry_date >= ?::date GROUP BY hrgnum_puk";
    
    public String Q_GET_TESTTEMPLATE_XML_TESTWISE = "SELECT hssm.hivtclob_document FROM hivt_document_store_mst_testwise hssm WHERE hssm.hivnum_test_code = ? and hssm.gnum_isvalid=1";

    public String FETCH_BILL_DETAILS = " select gnum_hospital_code||'***'||gnum_bill_no||'***'||gdt_bill_date||'***'||hivnum_req_no||'***'||hivnum_testorgroupcode from hivt_testbill_dtl where gnum_isvalid=1 and hivnum_issync=0  ";

    //public String PAT_DETAILS_ON_BASIS_OF_FETCH_BILL_DETAILS = "select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select pkg_inv_fun.fun_test_name(gnum_test_code) from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?)||'***'||(select hivtnum_req_dno from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?) from hivt_requisition_header where hivnum_req_no=? and gnum_hospital_code=? ";
    //public String PAT_DETAILS_ON_BASIS_OF_FETCH_BILL_DETAILS = "select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select pkg_inv_fun.fun_test_name(gnum_test_code) from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?)||'***'||(select hivtnum_req_dno from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?)||'***'||gnum_test_code from hivt_requisition_header where hivnum_req_no=? and gnum_hospital_code=? ";
  //  public String PAT_DETAILS_ON_BASIS_OF_FETCH_BILL_DETAILS = "select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select pkg_inv_fun.fun_test_name(gnum_test_code) from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?)||'***'||(select hivtnum_req_dno from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?)||'***'||(select gnum_test_code from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?) from hivt_requisition_header where hivnum_req_no=? and gnum_hospital_code=?  ";
    
    public String PAT_DETAILS_ON_BASIS_OF_FETCH_BILL_DETAILS = "select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select pkg_inv_fun.fun_test_name(gnum_test_code) from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=? limit 1)||'***'||(select hivtnum_req_dno from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=? limit 1)||'***'||(select gnum_test_code from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=? limit 1) from hivt_requisition_header where hivnum_req_no=? and gnum_hospital_code=?  ";



    
    public String IS_TEST_EXIST = "select count(*) from hivt_requisition_dtl where hivnum_req_no=? and gnum_test_code=? and gnum_hospital_code=?";

    public String IS_GROUP_EXIST = "select count(*) from hivt_requisition_dtl a where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=? and exists (select 1 from hivt_test_group_mst b where b.hgnum_group_code=a.hgnum_group_code and b.gnum_hospital_code=a.gnum_hospital_code and b.gnum_isvalid=1)";

    //public String GROUP_DETAILS ="select (select String_agg(((select gstr_test_name from hivt_test_mst g where g.gnum_test_code=v.gnum_test_code and gnum_isvalid=1)),',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?),(select pkg_inv_fun.getgroupname(hgnum_group_code,gnum_hospital_code) from hivt_requisition_dtl where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=? limit 1),(select String_agg(hivtnum_req_dno,',') from hivt_requisition_dtl where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?),hrgnum_puk,hrgstr_patname,hivstr_age,gstr_gender_code,DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ,hgnum_visit_date,hgstr_ordered_by_doc_name,hipstr_bed_name,gstr_dept_name,pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code) from hivt_requisition_header f where hivnum_req_no=? and gnum_hospital_code=? ";
    //public String GROUP_DETAILS ="select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select String_agg(((select gstr_test_name from hivt_test_mst g where g.gnum_test_code=v.gnum_test_code and gnum_isvalid=1)),',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(hivtnum_req_dno,',') from hivt_requisition_dtl where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select hgstr_group_name from hivt_test_group_mst where hgnum_group_code=? and gnum_hospital_code=? and gnum_isvalid=1) from hivt_requisition_header f where hivnum_req_no=? and gnum_hospital_code=?";
//    public String GROUP_DETAILS ="select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select String_agg(((select nvl(gstr_test_name,'NA') from hivt_test_mst g where g.gnum_test_code=v.gnum_test_code and gnum_isvalid=1)),',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(hivtnum_req_dno,',') from hivt_requisition_dtl where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(gnum_test_code,',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||nvl((select hgstr_group_name from hivt_test_group_mst where hgnum_group_code=? and gnum_hospital_code=? and gnum_isvalid=1),'NA') from hivt_requisition_header f where hivnum_req_no=? and gnum_hospital_code=?";
   // public String GROUP_DETAILS ="select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select String_agg(((select nvl(gstr_test_name,'NA') from hivt_test_mst g where g.gnum_test_code=v.gnum_test_code and gnum_isvalid=1)),',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(hivtnum_req_dno,',') from hivt_requisition_dtl where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(gnum_test_code,',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||nvl((select hgstr_group_name from hivt_test_group_mst where hgnum_group_code=? and gnum_hospital_code=? and gnum_isvalid=1),'NA') from hivt_requisition_header f where hivnum_req_no=? and gnum_hospital_code=? and exists (select 1 fom hivt_test_mst e where e.gnum_test_code=v.gnum_test_code and e.gnum_isvalid=1)";
    public String GROUP_DETAILS ="select hrgnum_puk||'***'||hrgstr_patname||'***'||hivstr_age||'***'||gstr_gender_code||'***'||DECODE(HBNUM_REQUISITION_TYPE, 2, 'IPD', 1, 'OPD', 3, 'Emergency', 4, 'Special') ||'***'||hgnum_visit_date||'***'||hgstr_ordered_by_doc_name||'***'||hipstr_bed_name||'***'||gstr_dept_name||'***'||pkg_inv_fun.fun_lab_name(gnum_lab_code,gnum_hospital_code)||'***'||(select String_agg(((select nvl(gstr_test_name,'NA') from hivt_test_mst g where g.gnum_test_code=v.gnum_test_code and g.gnum_isvalid=1)),',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(hivtnum_req_dno,',') from hivt_requisition_dtl where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=?)||'***'||(select String_agg(gnum_test_code,',') from hivt_requisition_dtl v where hivnum_req_no=? and hgnum_group_code=? and gnum_hospital_code=? and exists (select 1 from hivt_test_mst q where q.gnum_test_code=v.gnum_test_code and q.gnum_isvalid=1))||'***'||nvl((select hgstr_group_name from hivt_test_group_mst where hgnum_group_code=? and gnum_hospital_code=? and gnum_isvalid=1),'NA') from hivt_requisition_header f where hivnum_req_no=? and gnum_hospital_code=?";

    
    public String INSERT_TESTABLE = "insert into testtable(crno,patientname,age,gender,ipopemgstatus,visitdate,doctorname,bed,department,labname,testname,gnum_bill_no,billdate,requisitiondno,isgroup,testcode,entrydate) values(?,?,?,?,?,TO_DATE(?),?,?,?,?,?,?,TO_DATE(?),?,?,?,sysdate)";
    
    public String INSERT_TESTABLE_GROUP = "insert into testtable(crno,patientname,age,gender,ipopemgstatus,visitdate,doctorname,bed,department,labname,testname,gnum_bill_no,billdate,requisitiondno,isgroup,testcode,groupname,entrydate) values(?,?,?,?,?,TO_DATE(?),?,?,?,?,?,?,TO_DATE(?),?,?,?,?,sysdate)";

    public String UPDATE_BILL_TBL = "update hivt_testbill_dtl set hivnum_issync=1 where  hivnum_req_no=? and hivnum_testorgroupcode=? and gnum_isvalid=1";

    public String Q_UPDATE_REQ_STATUS_DAILY_TABLE = "INSERT INTO hivt_requisition_dtl_daily SELECT * FROM hivt_requisition_dtl WHERE hivtnum_req_dno =?";

    public String Q_UPDATE_REQ_STATUS_DAILY_TABLE_CRNO = "update hivt_requisition_dtl_daily set hrgnum_puk=? WHERE hivtnum_req_dno =?";

    public String Q_UPDATE_REPORT_DAILY_TABLE_CRNO = "update hivt_report_dtl_hst set hrgnum_puk=? WHERE hivtnum_req_dno =?";


    /*public String Q_UPDATE_REQ_STATUS_DAILY_TABLE = "INSERT INTO hivt_requisition_dtl_daily(gnum_hospital_code, hivtnum_req_dno, hivnum_isappointment, gnum_lab_code, gnum_test_code, hivnum_isconfidential, gbnum_priority_code, hivdt_result_date,hivnum_result_seatid, hivt_entry_date, hivnum_reqdtl_status,gnum_seat_id, hivnum_temp_sample_no, hgnum_group_code, hgnum_group_type,"
                    + "hivtdt_cancellation_date, hivtnum_cancellation_seatid, gnum_bill_no,hivtnum_res_val_seatid, hivtdt_res_val_date, hivtnum_res_reval_seatid,gnum_sample_code, hivtdt_res_reval_date, hivtnum_res_print_seatid,hgstr_daily_labno, hivtdt_pat_accpt_date_time, hivtdt_res_print_date,hivnum_pat_rejection_reason, hivtnum_res_modify_seatid, hivtdt_res_modify_date,"
                    +"hivnum_pat_rejection_action, hivnum_reshedule_seatid, hivtnum_workordersequence, hivnum_sam_rejection_action, hivnum_sam_rejection_reason, hivtnum_test_delete_seatid,hivdt_reshedule_date, hivtdt_test_delete_date, hivtnum_typeofcomponent,hivnum_req_no, hivnum_isaccepted, hivnum_packing_list_no, gnum_machine_code,hivdt_acceptance_date_time, hivdt_acceptance_seatid, hivdt_coll_date_time,"
                    +"hivnum_collection_seatid, hivnum_sample_col_area_code, hivdt_appointment_date, hivstr_appointment_time, hivnum_packing_list_seat_id, hivtdt_packinglist_date_time,hivnum_issample_received, hivnum_sample_no, hivnum_uom, hivnum_collected_volume,hivnum_containercode, hapnum_app_ref_no, hivstr_report_url, hivdt_report_date,gnum_film_flag, hivtnum_priority_all, gnum_report_change, hivnum_reportchange_seatid,"
                    +"hivdt_reportchange_date, gnum_change_count, hivdt_dept_report_date,hivstr_dept_report_url, hivnum_dept_reqdtl_status, hivdt_dept_result_date,hivnum_dept_result_seatid, hivtdt_dept_res_modify_date, hivtnum_dept_res_modify_seatid,hivtnum_dept_res_print_seatid, hivtdt_dept_res_print_date, is_addendum,hivnum_validator, hivt_is_repeat_sample, hivnum_repeat_request_seatid,hivdt_repeat_req_datetime,"
                    +"hivt_repeat_counter, hivt_retest_repeat_mode,hivstr_final_remark, hivt_is_draft_req, hivnum_draft_req_seatid,hivdt_draft_req_datetime, hivt_draft_req_count, hivstr_site,has_permission, hrgnum_puk, hgnum_visitno, hivdt_requisition_date,gnum_episode_code, hrgstr_patname, hgstr_ordered_by_doc, gstr_gender_code, hivstr_address, hrgstr_mobile_no, hrgstr_email_id, hipnum_bed_code,"
                    +"hrgstr_mlc_no, hipstr_bed_name, hipstr_room_name, hipstr_ward_name,gstr_dept_name, hipnum_admno, hipnum_wardcode, hipnum_room_code, hivtnum_request_delete_seatid, hgnum_dept_code, hgnum_deptunitcode,hivtnum_isautomated_request, hivnum_req_raised_through, gnum_reffered_hospital_code,gnum_reffered_lab_code, hgnum_patient_cat_code, hivnum_sms_flag,gnum_isvalid, "
                    +"hbnum_requisition_type, hivnum_reqheader_status,hrgstr_visit_reason, hivstr_addendum_remark, hivstr_delete_reason, hivstr_ext_crnumber, hivstr_ext_hosporlab_name, gnum_reffered_dept_unit_name,hgstr_ordered_by_doc_name, hgstr_unit_name, gnum_associated_reqno, hrgdt_dob, hgnum_visit_date, hivtdt_request_delete_date, hivdt_xml_date, gnum_isassociated_test, hivstr_age)"
                    +"select a.gnum_hospital_code, a.hivtnum_req_dno, a.hivnum_isappointment, a.gnum_lab_code,a.gnum_test_code, a.hivnum_isconfidential, a.gbnum_priority_code, a.hivdt_result_date,a.hivnum_result_seatid, a.hivt_entry_date, a.hivnum_reqdtl_status,a.gnum_seat_id, a.hivnum_temp_sample_no, a.hgnum_group_code, a.hgnum_group_type,a.hivtdt_cancellation_date, a.hivtnum_cancellation_seatid, a.gnum_bill_no," 
            +"a.hivtnum_res_val_seatid, a.hivtdt_res_val_date, a.hivtnum_res_reval_seatid,a.gnum_sample_code, a.hivtdt_res_reval_date, a.hivtnum_res_print_seatid,a.hgstr_daily_labno, a.hivtdt_pat_accpt_date_time, a.hivtdt_res_print_date,a.hivnum_pat_rejection_reason, a.hivtnum_res_modify_seatid, a.hivtdt_res_modify_date,a.hivnum_pat_rejection_action, a.hivnum_reshedule_seatid, a.hivtnum_workordersequence,"
                    +"a.hivnum_sam_rejection_action, a.hivnum_sam_rejection_reason, a.hivtnum_test_delete_seatid, a.hivdt_reshedule_date, a.hivtdt_test_delete_date, a.hivtnum_typeofcomponent," 
            +"a.hivnum_req_no, a.hivnum_isaccepted, a.hivnum_packing_list_no, a.gnum_machine_code, a.hivdt_acceptance_date_time, a.hivdt_acceptance_seatid, a.hivdt_coll_date_time, a.hivnum_collection_seatid, a.hivnum_sample_col_area_code, a.hivdt_appointment_date, a.hivstr_appointment_time, a.hivnum_packing_list_seat_id, a.hivtdt_packinglist_date_time, a.hivnum_issample_received, a.hivnum_sample_no, a.hivnum_uom, a.hivnum_collected_volume," 
            +"a.hivnum_containercode, a.hapnum_app_ref_no, a.hivstr_report_url, a.hivdt_report_date, a.gnum_film_flag, a.hivtnum_priority_all, a.gnum_report_change, a.hivnum_reportchange_seatid,a.hivdt_reportchange_date, a.gnum_change_count, a.hivdt_dept_report_date, a.hivstr_dept_report_url, a.hivnum_dept_reqdtl_status, a.hivdt_dept_result_date,a.hivnum_dept_result_seatid, a.hivtdt_dept_res_modify_date, a.hivtnum_dept_res_modify_seatid," 
            +"a.hivtnum_dept_res_print_seatid, a.hivtdt_dept_res_print_date, a.is_addendum,a.hivnum_validator, a.hivt_is_repeat_sample, a.hivnum_repeat_request_seatid,a.hivdt_repeat_req_datetime, a.hivt_repeat_counter, a.hivt_retest_repeat_mode, a.hivstr_final_remark, a.hivt_is_draft_req, a.hivnum_draft_req_seatid,a.hivdt_draft_req_datetime, a.hivt_draft_req_count, a.hivstr_site,a.has_permission, b.hrgnum_puk, b.hgnum_visitno, b.hivdt_requisition_date," 
            +"b.gnum_episode_code, b.hrgstr_patname, b.hgstr_ordered_by_doc, b.gstr_gender_code, b.hivstr_address, b.hrgstr_mobile_no, b.hrgstr_email_id, b.hipnum_bed_code, b.hrgstr_mlc_no, b.hipstr_bed_name, b.hipstr_room_name, b.hipstr_ward_name,b.gstr_dept_name, b.hipnum_admno, b.hipnum_wardcode, b.hipnum_room_code,b.hivtnum_request_delete_seatid, b.hgnum_dept_code, b.hgnum_deptunitcode,b.hivtnum_isautomated_request, b.hivnum_req_raised_through,"
            +"b.gnum_reffered_hospital_code, b.gnum_reffered_lab_code, b.hgnum_patient_cat_code, b.hivnum_sms_flag, b.gnum_isvalid, b.hbnum_requisition_type, b.hivnum_reqheader_status,b.hrgstr_visit_reason, b.hivstr_addendum_remark, b.hivstr_delete_reason,b.hivstr_ext_crnumber, b.hivstr_ext_hosporlab_name, b.gnum_reffered_dept_unit_name,b.hgstr_ordered_by_doc_name, b.hgstr_unit_name, b.gnum_associated_reqno," 
            +"b.hrgdt_dob, b.hgnum_visit_date, b.hivtdt_request_delete_date, b.hivdt_xml_date, b.gnum_isassociated_test, b.hivstr_age from hivt_requisition_dtl a,hivt_requisition_header b where b.gnum_isvalid=1 and a.gnum_hospital_code = b.gnum_hospital_code and a.hivnum_req_no = b.hivnum_req_no and a.hivtnum_req_dno = ? and a.hivnum_req_no = ?";
*/
    
    public String Q_UPDATE_REQ_STATUS_DAILY_TABLE_DELETE_DNO_DUPLICATE = "delete from  hivt_requisition_dtl_daily  WHERE hivtnum_req_dno =?";

    public String ISEXIST_TESTABLE = "delete from testtable where requisitiondno=?";

    
    
    public String Q_UPDATE_hivt_report_dtl_daily_DELETE_DNO_DUPLICATE = "delete from  hivt_report_dtl_daily  WHERE hivtnum_req_dno =?";
    
    public String Q_UPDATE_hivt_report_dtl_hst_DELETE_DNO_DUPLICATE = "delete from  hivt_report_dtl_hst  WHERE hivtnum_req_dno =?";

    
    public String Q_hivt_report_dtl_daily_TABLE = "INSERT INTO hivt_report_dtl_daily SELECT * FROM hivt_requisition_dtl WHERE hivtnum_req_dno =?";

    public String Q_UPDATE_hivt_report_dtl_hst_TABLE = "INSERT INTO hivt_report_dtl_hst SELECT * FROM hivt_requisition_dtl WHERE hivtnum_req_dno =?";

    public String IS_TEST_EXIST_TEST_MST = "SELECT count(*) FROM hivt_requisition_dtl a WHERE hivnum_req_no =? and  exists(select 1 from hivt_test_mst b where b.gnum_test_code=a.gnum_test_code and b.gnum_isvalid=1) and  exists (select 1 from hivt_laboratory_test_mst c where c.gnum_test_code=a.gnum_test_code and c.gnum_labcode=a.gnum_lab_code and c.gnum_isvalid=1 and c.gnum_hospital_code=a.gnum_hospital_code)";

    public String Q_UPDATE_hivt_report_dtl_daily_HISTBL = "update  hivt_report_dtl_daily set hrgnum_puk=? WHERE hivtnum_req_dno =?";

    public String Q_UPDATE_hivt_report_dtl_hst_HISTBL = "update  hivt_report_dtl_hst set hrgnum_puk=? WHERE hivtnum_req_dno =?";

    
    public String UPDATE_BILL_NO_DTLTBL = "update hivt_requisition_dtl set gnum_bill_no=?";


    
    public String INSERT_TESTABLE_KPRAN = "insert into kopran.testtable(crno,patientname,age,gender,ipopemgstatus,visitdate,doctorname,bed,department,labname,testname,gnum_bill_no,billdate,requisitiondno,isgroup,testcode,entrydate) values(?,?,?,?,?,TO_DATE(?),?,?,?,?,?,?,TO_DATE(?),?,?,?,sysdate)";
    
    public String INSERT_TESTABLE_GROUP_KPRAN = "insert into kopran.testtable(crno,patientname,age,gender,ipopemgstatus,visitdate,doctorname,bed,department,labname,testname,gnum_bill_no,billdate,requisitiondno,isgroup,testcode,groupname,entrydate) values(?,?,?,?,?,TO_DATE(?),?,?,?,?,?,?,TO_DATE(?),?,?,?,?,sysdate)";

    public String ISEXIST_TESTABLE_KPRAN = "delete from kopran.testtable where requisitiondno=?";

    public String Q_hivt_ADDENDUM_HST_TABLE = "INSERT INTO hivt_addendum_report (hivtnum_req_dno,hivstr_report_url,hivt_entry_date,gnum_status,hrgnum_puk) values (?,?,sysdate,?,?) ";

    public String SELECT_COUNT_RESULT_ENTRY_DETAIL_HIVT_PARAMETER_DTL= "select count(*) from hivt_parameter_dtl where gnum_isvalid=1 and hivtnum_req_dno=? and hivtnum_test_code=? and hivnum_parameter_code=? and hivtnum_parent_parameter_code = ? and gnum_hospital_code=? ";
    
    public String SELECT_COUNT_PARAMETER_DTL_TEXTAREA= "select count(*) from hivt_test_parameter_mst where gnum_isvalid=1 and gnum_test_code=? and gnum_parameter_code=? and gstr_element_type='H' ";
    
    public String SELECT_DEFAULT_PARAMETER_DTL_TEXTAREA= "select nvl(HIVTSTR_DEFAULT_VALUE,'--') from hivt_test_parameter_mst where gnum_isvalid=1 and gnum_test_code=? and gnum_parameter_code=? and gstr_element_type='H' ";

        
    
}
