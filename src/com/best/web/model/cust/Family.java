package com.best.web.model.cust;

public class Family extends FamilyBaseInfo{
	
	private String custId;
	private String sex;
	private String birthday;
	private String cretType;
	private String cretNo;
	private String insuranceType;
	private String insuranceNo;
	private String mobile;
	private String marriage;
	 
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getCretType() {
		return cretType;
	}
	public void setCretType(String cretType) {
		this.cretType = cretType;
	}
	public String getCretNo() {
		return cretNo;
	}
	public void setCretNo(String cretNo) {
		this.cretNo = cretNo;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public String getInsuranceNo() {
		return insuranceNo;
	}
	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	
	
}
