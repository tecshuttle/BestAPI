package com.best.web.model.cust;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.best.web.model.Model;

public class CardParam extends Model{
	//cust card
	private String custId;
	private String operator;
	private String bindType;
	private String bindDate;
	private Integer changePackageCount;
	
	private String custCardId;
	private String custCardPackageId;
	
	//card
	private String cardNoId;
	private String cardId;
	private String cardName;
	private String cardNo;
	private String cardCode;
	private String cardType;
	private String status;
	private String cardNoMemo;
	
	private String companyId;
	private String intro;
	private String logoImg;
	private String useRule;
	private Timestamp openDate;
	private Timestamp closeDate;
	private Timestamp validDateBegin;
	private Timestamp validDateEnd;
	private BigDecimal price;
	private Integer packageTotal;
	private Integer selectCount;
	private Integer allowChangeCount;
	private Integer cardNoLength;
	private Integer cardCodeLength;
	private String cardNoPrefix;
	private Integer cardNoSnLength;
	private String validDateType;
	private String validDateValue;
	private int verifyCount;
	
	//bind package
	private String packageId;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getBindType() {
		return bindType;
	}
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	public String getBindDate() {
		return bindDate;
	}
	public void setBindDate(String bindDate) {
		this.bindDate = bindDate;
	}
	public Integer getChangePackageCount() {
		return changePackageCount;
	}
	public void setChangePackageCount(Integer changePackageCount) {
		this.changePackageCount = changePackageCount;
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
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public Timestamp getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}
	public Timestamp getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}
	public Timestamp getValidDateBegin() {
		return validDateBegin;
	}
	public void setValidDateBegin(Timestamp validDateBegin) {
		this.validDateBegin = validDateBegin;
	}
	public Timestamp getValidDateEnd() {
		return validDateEnd;
	}
	public void setValidDateEnd(Timestamp validDateEnd) {
		this.validDateEnd = validDateEnd;
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
	public Integer getCardNoLength() {
		return cardNoLength;
	}
	public void setCardNoLength(Integer cardNoLength) {
		this.cardNoLength = cardNoLength;
	}
	public Integer getCardCodeLength() {
		return cardCodeLength;
	}
	public void setCardCodeLength(Integer cardCodeLength) {
		this.cardCodeLength = cardCodeLength;
	}
	public String getCardNoPrefix() {
		return cardNoPrefix;
	}
	public void setCardNoPrefix(String cardNoPrefix) {
		this.cardNoPrefix = cardNoPrefix;
	}
	public Integer getCardNoSnLength() {
		return cardNoSnLength;
	}
	public void setCardNoSnLength(Integer cardNoSnLength) {
		this.cardNoSnLength = cardNoSnLength;
	}
	public String getValidDateType() {
		return validDateType;
	}
	public void setValidDateType(String validDateType) {
		this.validDateType = validDateType;
	}
	public String getValidDateValue() {
		return validDateValue;
	}
	public void setValidDateValue(String validDateValue) {
		this.validDateValue = validDateValue;
	}
	public String getCustCardId() {
		return custCardId;
	}
	public void setCustCardId(String custCardId) {
		this.custCardId = custCardId;
	}
	public String getCustCardPackageId() {
		return custCardPackageId;
	}
	public void setCustCardPackageId(String custCardPackageId) {
		this.custCardPackageId = custCardPackageId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardNoId() {
		return cardNoId;
	}
	public void setCardNoId(String cardNoId) {
		this.cardNoId = cardNoId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public int getVerifyCount() {
		return verifyCount;
	}
	public void setVerifyCount(int verifyCount) {
		this.verifyCount = verifyCount;
	}
	public String getCardNoMemo() {
		return cardNoMemo;
	}
	public void setCardNoMemo(String cardNoMemo) {
		this.cardNoMemo = cardNoMemo;
	}
	
}
