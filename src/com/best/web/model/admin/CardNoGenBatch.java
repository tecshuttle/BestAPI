package com.best.web.model.admin;

public class CardNoGenBatch {

    private String id;
    private String creator;
    private String create_date;
    private String modifier;
    private String modify_date;
    private String active_flag;
    private String card_id;
    private String batch_no;
    private String proposer;
    private String memo;
    private String card_no_area;
    private Integer gen_quantity;
    private String card_type;
    private String card_name;    //关联xzh_card表
    private String company_name; //关联pf_company2表
    private String company_id;   //用于表单取值
    private String card_no;      //用于表单取值
    private String dept_id;
    private String dept2_id;
    private String status;
    private String card_code_gen_type;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String value) {
        this.card_id = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
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

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String value) {
        this.batch_no = value;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String value) {
        this.proposer = value;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String value) {
        this.memo = value;
    }

    public String getCard_no_area() {
        return card_no_area;
    }

    public void setCard_no_area(String value) {
        this.card_no_area = value;
    }

    public Integer getGen_quantity() {
        return gen_quantity;
    }

    public void setGen_quantity(Integer value) {
        this.gen_quantity = value;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String value) {
        this.card_type = value;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String value) {
        this.company_name = value;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String value) {
        this.card_name = value;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String value) {
        this.company_id = value;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String value) {
        this.card_no = value;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String value) {
        this.dept_id = value;
    }

    public String getDept2_id() {
        return dept2_id;
    }

    public void setDept2_id(String value) {
        this.dept2_id = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    public String getCard_code_gen_type() {
        return card_code_gen_type;
    }

    public void setCard_code_gen_type(String value) {
        this.card_code_gen_type = value;
    }
}

//end file