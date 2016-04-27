package com.best.web.model.cust;

import java.math.BigDecimal;
import java.util.List;

public class CardPackage {
    private String id;
    private String creator;
    private String create_date;
    private String modifier;
    private String modify_date;
    private String active_flag;
    private String card_id;
    private String package_name;
    private String package_code;
    private String is_required;
    private String sex_select;
    private String city_select;
    private String dtl_count;
    private String use_rule;
    private String seq;
    private String cardId;
    private String packageId;
    private String packageName;
    private String hasSelected;
    private String isRequired;
    private String sexSelect;
    private String citySelect;
    private Integer serviceTotal;
    private BigDecimal price;
    private String intro;
    private String useRule;
    private String card_name; //关联xzh_card

    private List<PackageService> serviceList;

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

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String value) {
        this.card_id = value;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String value) {
        this.package_name = value;
    }

    public String getPackage_code() {
        return package_code;
    }

    public void setPackage_code(String value) {
        this.package_code = value;
    }

    public String getIs_required() {
        return is_required;
    }

    public void setIs_required(String value) {
        this.is_required = value;
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

    public String getDtl_count() {
        return dtl_count;
    }

    public void setDtl_count(String value) {
        this.dtl_count = value;
    }

    public String getUse_rule() {
        return use_rule;
    }

    public void setUse_rule(String value) {
        this.use_rule = value;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String value) {
        this.seq = value;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getHasSelected() {
        return hasSelected;
    }

    public void setHasSelected(String hasSelected) {
        this.hasSelected = hasSelected;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getSexSelect() {
        return sexSelect;
    }

    public void setSexSelect(String sexSelect) {
        this.sexSelect = sexSelect;
    }

    public String getCitySelect() {
        return citySelect;
    }

    public void setCitySelect(String citySelect) {
        this.citySelect = citySelect;
    }

    public Integer getServiceTotal() {
        return serviceTotal;
    }

    public void setServiceTotal(Integer serviceTotal) {
        this.serviceTotal = serviceTotal;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUseRule() {
        return useRule;
    }

    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String value) {
        this.card_name = value;
    }

    public List<PackageService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<PackageService> serviceList) {
        this.serviceList = serviceList;
    }
}

//end file
