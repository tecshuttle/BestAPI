package com.best.web.validator.order;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.best.util.BasicUtil;
import com.best.web.model.order.OrderPayment;

@Component
public class OrderPaymentValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return OrderPayment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrderPayment orderParam = (OrderPayment)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderId","doctorOrder.orderId.required", "订单ID为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentDate","doctorOrder.paymentDate.required", "付款日期为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentAmount", "doctorOrder.paymentAmount.required", "付款金额为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentNo", "doctorOrder.paymentNo.required", "付款单号为空");
		
		if(BasicUtil.isNotEmpty(orderParam.getPaymentDate())){
			if(!BasicUtil.isValidDate(orderParam.getPaymentDate(),"yyyyMMddHHmmss")){
				errors.rejectValue("paymentDate","doctorOrder.paymentDate.error_date", "付款日期格式错误");
			} 
		}
		
		if(BasicUtil.isNotEmpty(orderParam.getPaymentAmount())){
			if(!BasicUtil.isNumber(orderParam.getPaymentAmount())){
				errors.rejectValue("paymentAmount","doctorOrder.paymentAmount.error_number", "付款金额非数字");
			}
		}
		
		
	}
}