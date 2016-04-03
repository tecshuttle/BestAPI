package com.best.web.model.cust;

import java.math.BigDecimal;

public class PackageService {
	
	private String serviceId;
	private String serviceName;
	private String serviceType;
	private BigDecimal price;
	private Integer quantity;
	private String logoImg;
	private String sexSelect;
	private String citySelect;
	private String intro;
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
}
