package com.best.web.model.order;

public class PhysicalOrder extends ServiceOrder{
	
	private String orderId;
	private String supplierId;
	private String supplierName;
	private String reserveOrgId;
	private String reserveOrgName;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String address;
	private String contactPhone;
	private String reserveCardNo;
	private String reserveCardCode;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getReserveOrgId() {
		return reserveOrgId;
	}
	public void setReserveOrgId(String reserveOrgId) {
		this.reserveOrgId = reserveOrgId;
	}
	public String getReserveOrgName() {
		return reserveOrgName;
	}
	public void setReserveOrgName(String reserveOrgName) {
		this.reserveOrgName = reserveOrgName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getReserveCardNo() {
		return reserveCardNo;
	}
	public void setReserveCardNo(String reserveCardNo) {
		this.reserveCardNo = reserveCardNo;
	}
	public String getReserveCardCode() {
		return reserveCardCode;
	}
	public void setReserveCardCode(String reserveCardCode) {
		this.reserveCardCode = reserveCardCode;
	}
}
