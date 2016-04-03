package com.best.web.validator.order;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.order.GeneOrder;

@Component
public class GeneOrderValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return GeneOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GeneOrder orderParam = (GeneOrder)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "custId","geneOrder.custId.required", "客户ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "custServiceId","geneOrder.custServiceId.required", "客户服务ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "supplierServiceId","geneOrder.supplierServiceId.required", "供应商服务ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","geneOrder.name.required", "姓名为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex","geneOrder.sex.required", "性别为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "geneOrder.mobile.required", "手机号码为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail", "geneOrder.mail.required", "邮箱为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "geneOrder.address.required", "收件地址为空");
		
		if(BasicUtil.isNotEmpty(orderParam.getMobile())){
			if(!BasicUtil.isMobile(orderParam.getMobile())){
				errors.rejectValue("mobile","geneOrder.mobile.error_mobile", "手机号格式错误");
			}
		}
		if(BasicUtil.isNotEmpty(orderParam.getMail())){
			if(!BasicUtil.isMail(orderParam.getMail())){
				errors.rejectValue("mobile","geneOrder.mail.error_mail", "邮箱格式错误");
			}
		}
	}
}