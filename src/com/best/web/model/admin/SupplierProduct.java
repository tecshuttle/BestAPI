package com.best.web.model.admin;

/**
 * XXX: model取名为SupplierService有冲突，改名为SupplierProduct
 */
public class SupplierProduct {

    private String id;
    private String creator;
    private String create_date;
    private String modifier;
    private String modify_date;
    private String active_flag;
    private String supplier_id;
    private String supplier_name; //关联xzh_supplier
    private String service_code;
    private String service_name;
    private String service_type;
    private String status;
    private String market_price;
    private String cost_price;
    private String is_use_verify_code;
    private String logo_img;
    private String sex_select;
    private String city_select;
    private String business_hours;
    private String reserve_period_start;
    private String reserve_period_end;
    private String intro;
    private String memo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setModify_date(String modify_date) {
        this.modify_date = modify_date;
    }

    public String getModify_date() {
        return modify_date;
    }

    public void setActive_flag(String active_flag) {
        this.active_flag = active_flag;
    }

    public String getActive_flag() {
        return active_flag;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() { return supplier_name; }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String value) {
        this.service_code = value;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String value) {
        this.service_name = value;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String value) {
        this.service_type = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String value) {
        this.market_price = value;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String value) {
        this.cost_price = value;
    }

    public String getIs_use_verify_code() {
        return is_use_verify_code;
    }

    public void setIs_use_verify_code(String value) {
        this.is_use_verify_code = value;
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

    public void setBusiness_hours(String business_hours) {
        this.business_hours = business_hours;
    }

    public String getBusiness_hours() {
        return business_hours;
    }

    public void setReserve_period_start(String reserve_period_start) { this.reserve_period_start = reserve_period_start; }

    public String getReserve_period_start() {
        return reserve_period_start;
    }

    public void setReserve_period_end(String reserve_period_end) {
        this.reserve_period_end = reserve_period_end;
    }

    public String getReserve_period_end() {
        return reserve_period_end;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String value) {
        this.memo = value;
    }
}

//end file