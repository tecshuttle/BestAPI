package com.best.web.model.cust;

import java.math.BigDecimal;
import java.util.List;

public class CardPackage {
	private String id;
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
	
	private List<PackageService> serviceList;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public List<PackageService> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<PackageService> serviceList) {
		this.serviceList = serviceList;
	}
	
	
}
