package com.best.web.model.admin;

public class Company {

    private String id;
    private String company_name;
    private String company_type;
    private String active_flag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String value) {
        this.company_name = value;
    }

    public String getCompany_type() { return company_type; }

    public void setCompany_type(String value) {
        this.company_type = value;
    }

    public String getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(String value) {
        this.active_flag = value;
    }
}

//end file
