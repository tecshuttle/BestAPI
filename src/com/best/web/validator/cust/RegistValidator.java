package com.best.web.validator.cust;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.cust.Regist;

@Component
public class RegistValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Regist.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Regist regist = (Regist)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "channel","regist.channel.required", "接入渠道为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "regist.mobile.required", "手机号为空");
		if( target instanceof com.best.web.model.cust.RegistCard ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNo","regist.cardNo.required", "卡号为空");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardCode", "regist.cardCode.required", "卡密为空");
		}
		
		if(BasicUtil.isNotEmpty(regist.getChannel())){
			if(!BasicUtil.checkChannel(regist.getChannel())){
				errors.rejectValue("channel","regist.channel.not_exists", "接入渠道不存在");
			}
		}
		if(BasicUtil.isNotEmpty(regist.getMobile())){
			if(!BasicUtil.isMobile(regist.getMobile())){
				errors.rejectValue("mobile","regist.mobile.not_mobile", "手机号格式错误");
			}
		}
	}

}
