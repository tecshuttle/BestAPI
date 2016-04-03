package com.best.web.model.cust;

import java.math.BigDecimal;

public class CardService {
	private String id;
	private String custServiceId;
	private String strCreateDate;
	private String custId;
	private String custServiceStatus;
	private BigDecimal price;
	private Integer quantity;
	private Integer usedQuantity;
	private BigDecimal amount;
	private String paymentStatus;
	private String paymentType;
	private String serviceFrom;
	private String validDateBegin;
	private String validDateEnd;
	private String validFlag;
	private String serviceId;
	private String serviceName;
	private String serviceType;
	private String logoImg;
	private String sexSelect;
	private String citySelect;
	private String intro;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustServiceId() {
		return custServiceId;
	}
	public void setCustServiceId(String custServiceId) {
		this.custServiceId = custServiceId;
	}
	public String getStrCreateDate() {
		return strCreateDate;
	}
	public void setStrCreateDate(String strCreateDate) {
		this.strCreateDate = strCreateDate;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustServiceStatus() {
		return custServiceStatus;
	}
	public void setCustServiceStatus(String custServiceStatus) {
		this.custServiceStatus = custServiceStatus;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getServiceFrom() {
		return serviceFrom;
	}
	public void setServiceFrom(String serviceFrom) {
		this.serviceFrom = serviceFrom;
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
	public Integer getUsedQuantity() {
		return usedQuantity;
	}
	public void setUsedQuantity(Integer usedQuantity) {
		this.usedQuantity = usedQuantity;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	
}
