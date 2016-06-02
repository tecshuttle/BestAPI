package com.best.web.model.admin;

import com.best.web.model.cust.PackageService;

import java.math.BigDecimal;
import java.util.List;

public class CardPackageDtl {
    private String id;
    private String creator;
    private String create_date;
    private String modifier;
    private String modify_date;
    private String active_flag;
    private String package_id;
    private String service_id;
    private String service_alias;
    private String quantity;
    private String status;
    private String seq;
    private String valid_date_begin;
    private String valid_date_end;
    private String service_type; //关联xzh_service
    private String service_name; //关联xzh_service
    private String rel_type;     //关联xzh_service
    private String sex_select;   //关联xzh_service
    private String price;        //关联xzh_service
    private String cp_code_name; //关联cp_code

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

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String value) {
        this.package_id = value;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String value) {
        this.service_id = value;
    }

    public String getService_alias() {
        return service_alias;
    }

    public void setService_alias(String value) {
        this.service_alias = value;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String value) {
        this.quantity = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String value) {
        this.seq = value;
    }

    public String getValid_date_begin() {
        return valid_date_begin;
    }

    public void setValid_date_begin(String value) {
        this.valid_date_begin = value;
    }

    public String getValid_date_end() {
        return valid_date_end;
    }

    public void setValid_date_end(String value) {
        this.valid_date_end = value;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String value) {
        this.service_type = value;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String value) {
        this.service_name = value;
    }

    public String getRel_type() {
        return rel_type;
    }

    public void setRel_type(String value) {
        this.rel_type = value;
    }

    public String getSex_select() {
        return sex_select;
    }

    public void setSex_select(String value) {
        this.sex_select = value;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String value) {
        this.price = value;
    }

    public String getCp_code_name() {
        return cp_code_name;
    }

    public void setCp_code_name(String value) {
        this.cp_code_name = value;
    }
}

//end file
