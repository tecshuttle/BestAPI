package com.best.web.validator.cust;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.cust.CustBaseInfo;

@Component
public class CustBaseInfoValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return CustBaseInfo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustBaseInfo custBaseInfo = (CustBaseInfo)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "custId","custBaseInfo.custId.required", "会员ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","custBaseInfo.id.required", "姓名为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "custBaseInfo.sex.required", "性别为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "custBaseInfo.birthday.required", "出生日期为空");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretType", "custBaseInfo.cretType.required", "证件类型为空");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cretNo", "custBaseInfo.cretNo.required", "证件号码为空");
		
		if(BasicUtil.isNotEmpty(custBaseInfo.getBirthday())){
			if(!BasicUtil.isValidDate(custBaseInfo.getBirthday(),"yyyy/MM/dd")){
				errors.rejectValue("birthday","custBaseInfo.birthday.error_date", "日期格式错误");
			} 
		}
		if(BasicUtil.isNotEmpty(custBaseInfo.getCretType())&&"1".equals(custBaseInfo.getCretType())){
			if(BasicUtil.isNotEmpty(custBaseInfo.getCretNo()) && !BasicUtil.isIdcard(custBaseInfo.getCretNo())){
				errors.rejectValue("cretNo","custBaseInfo.cretNo.error_idno", "身份证号格式错误");
			}
		}
		
	}
}
