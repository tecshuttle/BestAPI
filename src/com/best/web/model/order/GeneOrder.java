package com.best.web.model.order;

public class GeneOrder extends ServiceOrder{
	
	private String orderId;
	private String address;
	private String shipExpress;
	private String shipExpressNo;
	private String custReceiveFlag;
	private String fetchExpress;
	private String fetchExpressNo;
	private String custSendFlag;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getShipExpress() {
		return shipExpress;
	}
	public void setShipExpress(String shipExpress) {
		this.shipExpress = shipExpress;
	}
	public String getShipExpressNo() {
		return shipExpressNo;
	}
	public void setShipExpressNo(String shipExpressNo) {
		this.shipExpressNo = shipExpressNo;
	}
	public String getCustReceiveFlag() {
		return custReceiveFlag;
	}
	public void setCustReceiveFlag(String custReceiveFlag) {
		this.custReceiveFlag = custReceiveFlag;
	}
	public String getFetchExpress() {
		return fetchExpress;
	}
	public void setFetchExpress(String fetchExpress) {
		this.fetchExpress = fetchExpress;
	}
	public String getFetchExpressNo() {
		return fetchExpressNo;
	}
	public void setFetchExpressNo(String fetchExpressNo) {
		this.fetchExpressNo = fetchExpressNo;
	}
	public String getCustSendFlag() {
		return custSendFlag;
	}
	public void setCustSendFlag(String custSendFlag) {
		this.custSendFlag = custSendFlag;
	}
	
}
