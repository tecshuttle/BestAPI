package com.best.web.validator.cust;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.cust.Family;

@Component
public class FamilyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Family.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Family family = (Family)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "custId","family.custId.required", "会员ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","family.name.required", "姓名为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "family.sex.required", "性别为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "family.birthday.required", "出生日期为空");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretType", "family.cretType.required", "证件类型为空");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretNo", "family.cretNo.required", "证件号码为空");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "family.mobile.required", "手机号为空");
		
		if(BasicUtil.isNotEmpty(family.getCretType()) && family.getCretType().equals("1")){
			if(BasicUtil.isNotEmpty(family.getCretNo()) && !BasicUtil.isIdcard(family.getCretNo())){
				errors.rejectValue("cretNo","family.cretNo.error_cretNo", "身份证格式错误");
			}
		}
		
		if(BasicUtil.isNotEmpty(family.getBirthday())){
			if(!BasicUtil.isValidDate(family.getBirthday(),"yyyy/MM/dd")){
				errors.rejectValue("birthday","family.birthday.error_date", "日期格式错误");
			} 
		}
		if(BasicUtil.isNotEmpty(family.getMobile())){
			if(!BasicUtil.isMobile(family.getMobile())){
				errors.rejectValue("mobile","family.mobile.error_mobile", "手机号格式错误");
			}
		}
	}

}
