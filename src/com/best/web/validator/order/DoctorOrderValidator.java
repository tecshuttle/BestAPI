package com.best.web.validator.order;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.order.DoctorOrder;

@Component
public class DoctorOrderValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return DoctorOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DoctorOrder orderParam = (DoctorOrder)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","doctorOrder.name.required", "姓名为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex","doctorOrder.sex.required", "性别为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "marriage", "doctorOrder.marriage.required", "婚姻状况为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretType", "doctorOrder.cretType.required", "证件类型为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretNo", "doctorOrder.cretNo.required", "证件号为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "doctorOrder.mobile.required", "手机号码为空");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serviceCity", "doctorOrder.serviceCity.required", "服务城市为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chiefComplaint", "doctorOrder.chiefComplaint.required", "病情描述");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applyDateStart", "doctorOrder.applyDateStart.required", "预约日期起为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applyDateEnd", "doctorOrder.applyDateEnd.required", "预约日期止为空");
		
		if(BasicUtil.isNotEmpty(orderParam.getApplyDateStart())){
			if(!BasicUtil.isValidDate(orderParam.getApplyDateStart(),"yyyy/MM/dd")){
				errors.rejectValue("birthday","doctorOrder.applyDateStart.error_date", "预约日期起日期格式错误");
			} 
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getApplyDateEnd())){
			if(!BasicUtil.isValidDate(orderParam.getApplyDateEnd(),"yyyy/MM/dd")){
				errors.rejectValue("birthday","doctorOrder.applyDateEnd.error_date", "预约日期止日期格式错误");
			} 
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getMobile())){
			if(!BasicUtil.isMobile(orderParam.getMobile())){
				errors.rejectValue("mobile","doctorOrder.mobile.error_mobile", "手机号格式错误");
			}
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getCretType())&&"1".equals(orderParam.getCretType())){
			if(BasicUtil.isNotEmpty(orderParam.getCretNo()) && !BasicUtil.isIdcard(orderParam.getCretNo())){
				errors.rejectValue("cretNo","doctorOrder.cretNo.error_idno", "身份证号格式错误");
			}
		}
	}
}