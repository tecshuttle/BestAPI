package com.best.web.model.cust;

import java.math.BigDecimal;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;

public class Card {
	private String custCardId;
	private String cardNoId;
	private String cardNo;
	private String bindDate;
	private String validDateBegin;
	private String validDateEnd;
	private String cardId;
	private String cardName;
	private String companyName;
	private String companyAbbr;
	private String citySelect;
	private String intro;
	private String logoImg;
	private String useRule;
	private BigDecimal price;
	private Integer packageTotal;
	private Integer selectCount;
	private Integer allowChangeCount;
	private Integer changePackageCount;
	private Integer selectPackageTotal;
	private String changePackageFlag;
	
	private List<CardPackage> packageList;
	private List<CardService> bindServiceList;
	
	public String getCustCardId() {
		return custCardId;
	}
	public void setCustCardId(String custCardId) {
		this.custCardId = custCardId;
	}
	public String getCardNoId() {
		return cardNoId;
	}
	public void setCardNoId(String cardNoId) {
		this.cardNoId = cardNoId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBindDate() {
		return bindDate;
	}
	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}
	public String getValidDateBegin() {
		return validDateBegin;
	}
	public void setValidDateBegin(String validDateBegin) {
		this.validDateBegin = validDateBegin;
	}
	public String getValidDateEnd() {
		return validDateEnd;
	}
	public void setValidDateEnd(String validDateEnd) {
		this.validDateEnd = validDateEnd;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public String getUseRule() {
		return useRule;
	}
	public void setUseRule(String useRule) {
		this.useRule = useRule;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getPackageTotal() {
		return packageTotal;
	}
	public void setPackageTotal(Integer packageTotal) {
		this.packageTotal = packageTotal;
	}
	public Integer getSelectCount() {
		return selectCount;
	}
	public void setSelectCount(Integer selectCount) {
		this.selectCount = selectCount;
	}
	public Integer getAllowChangeCount() {
		return allowChangeCount;
	}
	public void setAllowChangeCount(Integer allowChangeCount) {
		this.allowChangeCount = allowChangeCount;
	}
	public Integer getChangePackageCount() {
		return changePackageCount;
	}
	public void setChangePackageCount(Integer changePackageCount) {
		this.changePackageCount = changePackageCount;
	}
	public String getChangePackageFlag() {
		return changePackageFlag;
	}
	public void setChangePackageFlag(String changePackageFlag) {
		this.changePackageFlag = changePackageFlag;
	}
	public List<CardPackage> getPackageList() {
		return packageList;
	}
	public void setPackageList(List<CardPackage> packageList) {
		this.packageList = packageList;
	}
	
	public List<CardService> getBindServiceList() {
		return bindServiceList;
	}
	public void setBindServiceList(List<CardService> bindServiceList) {
		this.bindServiceList = bindServiceList;
	}
	public Integer getSelectPackageTotal() {
		return selectPackageTotal;
	}
	public void setSelectPackageTotal(Integer selectPackageTotal) {
		this.selectPackageTotal = selectPackageTotal;
	}
	public String getCompanyAbbr() {
		return companyAbbr;
	}
	public void setCompanyAbbr(String companyAbbr) {
		this.companyAbbr = companyAbbr;
	}
	public String getCitySelect() {
		return citySelect;
	}
	public void setCitySelect(String citySelect) {
		this.citySelect = citySelect;
	}
	
}
