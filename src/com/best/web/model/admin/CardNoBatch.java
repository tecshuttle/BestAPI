package com.best.web.model.admin;

public class CardNoBatch {

    private String dept_id;
    private String dept2_id;
    private String card_no_type;
    private String card_code_type;
    private int amount;
    private String batch_id;
    private String card_id;

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

    public String getCard_no_type() {
        return card_no_type;
    }

    public void setCard_no_type(String value) {
        this.card_no_type = value;
    }

    public String getCard_code_type() {
        return card_code_type;
    }

    public void setCard_code_type(String value) {
        this.card_code_type = value;
    }

    public int getAmount() { return amount; }

    public void setAmount(int value) {
        this.amount = value;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String value) {
        this.batch_id = value;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String value) {
        this.card_id = value;
    }
}

//end file