package com.best.web.model.cust;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.best.web.model.Model;

public class CustReginfo extends Model{
	private String siteId;
	private String custId;
	private Timestamp recDate;
	private String caseType;
	private String belongType;
	private Timestamp startDate;
	private Timestamp endDate;
	private String closeUser;
	private Timestamp closeDate;
	private String closeReason;
	private String availableFlag;
	private String serviceId;
	private BigDecimal serviceFq;
	private String closeType;
	private String checkFlag;
	private String memo;
	

	//Extend
	private String  siteName;
	private String  custName;
	private String  cretNo;
	private String  sex;
	
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public Timestamp getRecDate() {
		return recDate;
	}
	public void setRecDate(Timestamp recDate) {
		this.recDate = recDate;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getBelongType() {
		return belongType;
	}
	public void setBelongType(String belongType) {
		this.belongType = belongType;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getCloseUser() {
		return closeUser;
	}
	public void setCloseUser(String closeUser) {
		this.closeUser = closeUser;
	}
	public Timestamp getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}
	public String getCloseReason() {
		return closeReason;
	}
	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}
	public String getAvailableFlag() {
		return availableFlag;
	}
	public void setAvailableFlag(String availableFlag) {
		this.availableFlag = availableFlag;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public BigDecimal getServiceFq() {
		return serviceFq;
	}
	public void setServiceFq(BigDecimal serviceFq) {
		this.serviceFq = serviceFq;
	}
	public String getCloseType() {
		return closeType;
	}
	public void setCloseType(String closeType) {
		this.closeType = closeType;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCretNo() {
		return cretNo;
	}
	public void setCretNo(String cretNo) {
		this.cretNo = cretNo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
