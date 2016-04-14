package com.best.web.model.admin;


/**
 * XXX: 为避免取名冲突,取名为ProdService. 表示产品管理下的服务类产品.
 */
public class ProdService {

    private String id;
    private String card_name;
    private String card_code;
    private String price;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getCard_code() {
        return card_code;
    }

    public void setCard_code(String card_code) {
        this.card_code = card_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

//end file