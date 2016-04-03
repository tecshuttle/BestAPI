package com.best.web.model.order;

public class DoctorOrder extends ServiceOrder{
	
	private String orderId;
	private String chiefComplaint;
	private String applyHospitalName;
	private String applyDepartmentName;
	private String applyDoctorName;
	private String isNeedAssist;
	private String reserveType;
	private String reserveHospitalName;
	private String reserveDepartmentName;
	private String reserveDoctorName;
	private String assistName;
	private String assistSex;
	private String assistMobile;
	private String insuranceCard;
	private String hospitalCard;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getChiefComplaint() {
		return chiefComplaint;
	}
	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}
	public String getApplyHospitalName() {
		return applyHospitalName;
	}
	public void setApplyHospitalName(String applyHospitalName) {
		this.applyHospitalName = applyHospitalName;
	}
	public String getApplyDepartmentName() {
		return applyDepartmentName;
	}
	public void setApplyDepartmentName(String applyDepartmentName) {
		this.applyDepartmentName = applyDepartmentName;
	}
	public String getApplyDoctorName() {
		return applyDoctorName;
	}
	public void setApplyDoctorName(String applyDoctorName) {
		this.applyDoctorName = applyDoctorName;
	}
	public String getReserveType() {
		return reserveType;
	}
	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}
	public String getReserveHospitalName() {
		return reserveHospitalName;
	}
	public void setReserveHospitalName(String reserveHospitalName) {
		this.reserveHospitalName = reserveHospitalName;
	}
	public String getReserveDepartmentName() {
		return reserveDepartmentName;
	}
	public void setReserveDepartmentName(String reserveDepartmentName) {
		this.reserveDepartmentName = reserveDepartmentName;
	}
	public String getReserveDoctorName() {
		return reserveDoctorName;
	}
	public void setReserveDoctorName(String reserveDoctorName) {
		this.reserveDoctorName = reserveDoctorName;
	}
	public String getIsNeedAssist() {
		return isNeedAssist;
	}
	public void setIsNeedAssist(String isNeedAssist) {
		this.isNeedAssist = isNeedAssist;
	}
	public String getAssistName() {
		return assistName;
	}
	public void setAssistName(String assistName) {
		this.assistName = assistName;
	}
	public String getAssistSex() {
		return assistSex;
	}
	public void setAssistSex(String assistSex) {
		this.assistSex = assistSex;
	}
	public String getAssistMobile() {
		return assistMobile;
	}
	public void setAssistMobile(String assistMobile) {
		this.assistMobile = assistMobile;
	}
	public String getInsuranceCard() {
		return insuranceCard;
	}
	public void setInsuranceCard(String insuranceCard) {
		this.insuranceCard = insuranceCard;
	}
	public String getHospitalCard() {
		return hospitalCard;
	}
	public void setHospitalCard(String hospitalCard) {
		this.hospitalCard = hospitalCard;
	}
	
}
