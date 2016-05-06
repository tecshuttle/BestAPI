package com.best.web.model.admin;


/**
 * XXX: 为避免取名冲突,取名为ProdService. 表示产品管理下的服务类产品.
 */
public class ProdServiceMap {

    private String id;
    private String creator;
    private String create_date;
    private String modifier;
    private String modify_date;
    private String active_flag;
    private String xzh_service_id;
    private String supplier_service_id;
    private String status;
    private String seq;
    private String supplier_id;           //关联supplier
    private String supplier_service_name; //关联supplier
    private String service_type;     //关联supplier_service
    private String supplier_name;    //关联supplier_service
    private String market_price;     //关联supplier_service
    private String cost_price;       //关联supplier_service

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

    public String getXzh_service_id() {
        return xzh_service_id;
    }

    public void setXzh_service_id(String value) {
        this.xzh_service_id = value;
    }

    public String getSupplier_service_id() {
        return supplier_service_id;
    }

    public void setSupplier_service_id(String value) {
        this.supplier_service_id = value;
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

    public String getSeq() {
        return seq;
    }

    public void setSeq(String value) {
        this.seq = value;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String value) {
        this.supplier_id = value;
    }

    public String getSupplier_service_name() {
        return supplier_service_name;
    }

    public void setSupplier_service_name(String value) {
        this.supplier_service_name = value;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String value) {
        this.supplier_name = value;
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
}

//end file