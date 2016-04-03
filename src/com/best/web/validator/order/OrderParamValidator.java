package com.best.web.validator.order;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.order.OrderParam;

@Component
public class OrderParamValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return OrderParam.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderParam orderParam = (OrderParam)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderFrom","orderParam.orderFrom.required", "订单来源为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderNo","orderParam.orderNo.required", "订单号为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDate", "orderParam.orderDate.required", "订单日期为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDtlId", "orderParam.orderDtlId.required", "订单明细ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderCode", "orderParam.orderCode.required", "商品代码为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "orderParam.quantity.required", "订单数量为空");
		
		if(BasicUtil.isNotEmpty(orderParam.getOrderFrom())){
			if(!BasicUtil.checkOrderFrom(orderParam.getOrderFrom())){
				errors.rejectValue("channel","orderParam.orderFrom.not_exists", "接入渠道不存在");
			}
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getOrderDate())){
			if(!BasicUtil.isValidDate(orderParam.getOrderDate(),"yyyyMMddHHmmss")){
				errors.rejectValue("orderDate","orderParam.orderDate.error_date", "订单日期格式错误");
			} 
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getQuantity())){
			if(!BasicUtil.isNumber(orderParam.getQuantity())){
				errors.rejectValue("orderDate","orderParam.quantity.error_number", "订单数量格式错误");
			} 
		}
	}
}