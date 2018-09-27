package pers.will.lab.data.structure.collection;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
//        String strs = "asyn_task, wms_function_list, bas_distr_url, bas_export_task, ads_company, asyn_task, sys_shop, bas_region, bi_company, batch, batch_db_instance, bi_company_menu, bi_config, dash_trade_count, db_alias, db_instance, db_instance_status, db_instance_status_detail, db_tab_version, db_property, dms_task, dms_task_record, eclp_catagory, dms_task_sql, es_source, ex_order, h_company, ads_company, bas_distr_url, bas_exprot_task";
        String strs = "121.43.18.0/24,120.25.115.0/24,101.200.106.0/24,120.55.177.0/24,120.27.173.0/24,120.55.107.0/24,118.178.15.0/24,123.57.117.0/24,120.76.16.0/24,182.92.253.32/27,60.205.193.64/27,60.205.193.96/27,120.78.44.128/26,118.178.15.224/27,39.106.237.192/26,106.15.101.96/27,47.101.16.64/27,47.106.31.128/26,112.124.159.192/27,112.124.159.96/27,112.124.159.128/27,47.106.31.192/26,47.98.74.0/25,47.97.242.96/27";
        String[] arr = strs.split(",");
        Arrays.sort(arr);
        for (String s : arr) {
//            System.out.printf(s.trim() + ",");
            System.out.println(s.trim());
        }
    }
}
