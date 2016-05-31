package com.best.web.model.admin;


/**
 * XXX: 为避免取名冲突,取名为ProdService. 表示产品管理下的服务类产品.
 */
public class ProdService {

    private String id;
    private String creator;
    private String create_date;
    private String modifier;
    private String modify_date;
    private String active_flag;
    private String service_name;
    private String service_name_combo; //用于表单显示
    private String service_code;
    private String service_type;
    private String rel_type;
    private String status;
    private String logo_img;
    private String sex_select;
    private String city_select;
    private String price;
    private String intro;
    private String count_service_map; //所属产品数
    private String package_id;        //关联xzh_card_package_dtl
    private String dtl_id;            //关联xzh_card_package_dtl

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String value) {
        this.creator = value;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String value) {
        this.create_date = value;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String value) {
        this.modifier = value;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setModify_date(String value) {
        this.modify_date = value;
    }

    public String getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(String value) {
        this.active_flag = value;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String value) {
        this.service_name = value;
    }

    public String getService_name_combo() {
        return service_name_combo;
    }

    public void setService_name_combo(String value) {
        this.service_name_combo = value;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String value) {
        this.service_code = value;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String value) {
        this.service_type = value;
    }

    public String getRel_type() {
        return rel_type;
    }

    public void setRel_type(String value) {
        this.rel_type = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(String value) {
        this.logo_img = value;
    }

    public String getSex_select() {
        return sex_select;
    }

    public void setSex_select(String value) {
        this.sex_select = value;
    }

    public String getCity_select() {
        return city_select;
    }

    public void setCity_select(String value) {
        this.city_select = value;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String value) {
        this.intro = value;
    }

    public String getCount_service_map() {
        return count_service_map;
    }

    public void setCount_service_map(String value) {
        this.count_service_map = value;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String value) {
        this.package_id = value;
    }

    public String getDtl_id() {
        return dtl_id;
    }

    public void setDtl_id(String value) {
        this.dtl_id = value;
    }
}

//end file