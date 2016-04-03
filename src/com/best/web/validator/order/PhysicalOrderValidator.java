package com.best.web.validator.order;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.order.PhysicalOrder;

@Component
public class PhysicalOrderValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return PhysicalOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PhysicalOrder orderParam = (PhysicalOrder)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","physicalOrder.name.required", "姓名为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex","physicalOrder.sex.required", "性别为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "marriage", "physicalOrder.marriage.required", "婚姻状况为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretType", "physicalOrder.cretType.required", "证件类型为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretNo", "physicalOrder.cretNo.required", "证件号为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "physicalOrder.mobile.required", "手机号码为空");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serviceCity", "physicalOrder.serviceCity.required", "服务城市为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applyDateStart", "physicalOrder.applyDateStart.required", "预约日期起为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applyDateEnd", "physicalOrder.applyDateEnd.required", "预约日期止为空");
		
		if(BasicUtil.isNotEmpty(orderParam.getApplyDateStart())){
			if(!BasicUtil.isValidDate(orderParam.getApplyDateStart(),"yyyy/MM/dd")){
				errors.rejectValue("birthday","physicalOrder.applyDateStart.error_date", "预约日期起日期格式错误");
			} 
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getApplyDateEnd())){
			if(!BasicUtil.isValidDate(orderParam.getApplyDateEnd(),"yyyy/MM/dd")){
				errors.rejectValue("birthday","physicalOrder.applyDateEnd.error_date", "预约日期止日期格式错误");
			} 
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getMobile())){
			if(!BasicUtil.isMobile(orderParam.getMobile())){
				errors.rejectValue("mobile","physicalOrder.mobile.error_mobile", "手机号格式错误");
			}
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getCretType())&&"1".equals(orderParam.getCretType())){
			if(BasicUtil.isNotEmpty(orderParam.getCretNo()) && !BasicUtil.isIdcard(orderParam.getCretNo())){
				errors.rejectValue("cretNo","physicalOrder.cretNo.error_idno", "身份证号格式错误");
			}
		}
	}
}