package com.best.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.best.msg.BaseResponse;
import com.best.msg.GenResponse;
import com.best.msg.ListResponse;
import com.best.util.AjaxUtil;
import com.best.util.BasicUtil;
import com.best.util.ResultCodes;
import com.best.web.model.order.CardNo;
import com.best.web.model.order.DoctorOrder;
import com.best.web.model.order.GeneOrder;
import com.best.web.model.order.OrderParam;
import com.best.web.model.order.OrderPayment;
import com.best.web.model.order.PhysicalOrder;
import com.best.web.model.order.ServiceInfo;
import com.best.web.model.order.ServiceOrder;
import com.best.web.model.order.ServiceOrderQuantity;
import com.best.web.model.order.SupplierInfo;
import com.best.web.model.order.SupplierOrgInfo;
import com.best.web.service.CustService;
import com.best.web.service.OrderService;
import com.best.web.validator.order.DoctorOrderValidator;
import com.best.web.validator.order.GeneOrderValidator;
import com.best.web.validator.order.OrderParamValidator;
import com.best.web.validator.order.OrderPaymentValidator;
import com.best.web.validator.order.PhysicalOrderValidator;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	StringBuffer errorMsg;
	
	@Resource
	OrderService service;
	@Resource
	private CustService custService;
	@Resource  
	OrderParamValidator orderParamValidator;
	@Resource  
	DoctorOrderValidator doctorOrderValidator;
	@Resource
	PhysicalOrderValidator physicalOrderValidator;
	@Resource
	OrderPaymentValidator orderPaymentValidator;
	@Resource  
	GeneOrderValidator geneOrderValidator;
	/**
	 * 商城订单
	 */
	
	@RequestMapping(value="/sendCardOrder",method=RequestMethod.POST)
	public void sendCardOrder(HttpServletRequest request,HttpServletResponse response, OrderParam orderParam,BindingResult result) throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		this.orderParamValidator.validate(orderParam, result);
		if(result.hasErrors()){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		if(BasicUtil.isNotEmpty(orderParam.getCardNoType())){
			if(!"REAL,VIRTUAL".contains(orderParam.getCardNoType())){
				baseResponse.setResultCode(ResultCodes.ERROR);
				baseResponse.setMsg("卡形态错误");
				AjaxUtil.sendJSON(response, baseResponse);
				return;
			}
		}else{
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg("卡形态为空");
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		if(orderParam.getOrderFrom().contains("XZH_")){
			if(BasicUtil.isEmpty(orderParam.getCustId())){
				baseResponse.setResultCode(ResultCodes.ERROR);
				baseResponse.setMsg("客户ID为空");
				AjaxUtil.sendJSON(response, baseResponse);
				return;
			}else if(!custService.checkCustExists(orderParam.getCustId())){
				baseResponse.setResultCode(ResultCodes.ERROR);
				baseResponse.setMsg("客户ID不存在");
				AjaxUtil.sendJSON(response, baseResponse);
				return;
			}
		}
		
		if(!service.checkCardCodeExists(orderParam.getOrderCode())){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg("卡类代码不存在");
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		
		String msg = service.sendCardOrder(orderParam);
		if(BasicUtil.isNotEmpty(msg)){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	@RequestMapping(value="/sendServiceOrder",method=RequestMethod.POST)
	public void sendServiceOrder(HttpServletRequest request,HttpServletResponse response, OrderParam orderParam,BindingResult result) throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		this.orderParamValidator.validate(orderParam, result);
		if(result.hasErrors()){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		
		if(orderParam.getOrderFrom().contains("XZH_")){
			if(BasicUtil.isEmpty(orderParam.getCustId())){
				baseResponse.setResultCode(ResultCodes.ERROR);
				baseResponse.setMsg("客户ID为空");
				AjaxUtil.sendJSON(response, baseResponse);
				return;
			}else if(!custService.checkCustExists(orderParam.getCustId())){
				baseResponse.setResultCode(ResultCodes.ERROR);
				baseResponse.setMsg("客户ID不存在");
				AjaxUtil.sendJSON(response, baseResponse);
				return;
			}
		}
		
		if(!service.checkServiceCodeExists(orderParam.getOrderCode())){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg("服务代码不存在");
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		
		String msg = service.sendServiceOrder(orderParam);
		if(BasicUtil.isNotEmpty(msg)){
			baseResponse.setResultCode(ResultCodes.ERROR);
			baseResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, baseResponse);
			return;
		}
		AjaxUtil.sendJSON(response, baseResponse);
	}
	
	@RequestMapping(value="/findCardNoList",method=RequestMethod.POST)
	public void findCardNoList(HttpServletRequest request,HttpServletResponse response, String orderDtlId) throws Exception {
		GenResponse<List<CardNo>> genResponse = new GenResponse<List<CardNo>>();
		genResponse.setResponse(service.findCardNoList(orderDtlId));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/checkCardOrderAvailable",method=RequestMethod.POST)
	public void checkCardOrderAvailable(HttpServletRequest request,HttpServletResponse response, String orderDtlId) throws Exception {
		GenResponse<Boolean> genResponse = new GenResponse<Boolean>();
		genResponse.setResponse(service.checkCardOrderAvailable(orderDtlId));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/checkServiceOrderAvailable",method=RequestMethod.POST)
	public void checkServiceOrderAvailable(HttpServletRequest request,HttpServletResponse response, String orderDtlId) throws Exception {
		GenResponse<Boolean> genResponse = new GenResponse<Boolean>();
		String custServiceId = service.checkServiceOrderAvailable(orderDtlId);
		if(custServiceId==null){
			genResponse.setResponse(false);
		}else{
			genResponse.setResponse(true);
			genResponse.setMsg(custServiceId);
		}
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	/**
	 * 服务订单
	 */
	@RequestMapping(value="/findServiceInfo",method=RequestMethod.POST)
	public void findServiceInfo(HttpServletRequest request,HttpServletResponse response, String custServiceId) throws Exception {
		GenResponse<ServiceInfo> genResponse = new GenResponse<ServiceInfo>();
		genResponse.setResponse(service.findServiceInfo(custServiceId));
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/findServiceSupplierList",method=RequestMethod.POST)
	public void findServiceSupplierList(HttpServletRequest request,HttpServletResponse response, String custServiceId) throws Exception {
		ListResponse<SupplierInfo> listResponse = new ListResponse<SupplierInfo>();
		listResponse.setResponse(service.findServiceSupplierList(custServiceId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findSupplierOrgList",method=RequestMethod.POST)
	public void findSupplierOrgList(HttpServletRequest request,HttpServletResponse response, String supplierId,String cityName,String orderType) throws Exception {
		ListResponse<SupplierOrgInfo> listResponse = new ListResponse<SupplierOrgInfo>();
		if(BasicUtil.isEmpty(supplierId)){
			listResponse.setResultCode(ResultCodes.ERROR);
			listResponse.setMsg("supplierId为空");
			AjaxUtil.sendJSON(response, listResponse);
			return;
		}
		if(BasicUtil.isEmpty(cityName)){
			listResponse.setResultCode(ResultCodes.ERROR);
			listResponse.setMsg("cityName为空");
			AjaxUtil.sendJSON(response, listResponse);
			return;
		}
		if(BasicUtil.isEmpty(orderType)){
			listResponse.setResultCode(ResultCodes.ERROR);
			listResponse.setMsg("orderType为空");
			AjaxUtil.sendJSON(response, listResponse);
			return;
		}
		listResponse.setResponse(service.findSupplierOrgList(supplierId,cityName,orderType));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findServiceOrderQuantityList",method=RequestMethod.POST)
	public void findServiceOrderList(HttpServletRequest request,HttpServletResponse response, String custId) throws Exception {
		ListResponse<ServiceOrderQuantity> listResponse = new ListResponse<ServiceOrderQuantity>();
		listResponse.setResponse(service.findServiceOrderQuantityList(custId));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findServiceOrderList",method=RequestMethod.POST)
	public void findServiceOrderList(HttpServletRequest request,HttpServletResponse response, String custId,String orderType) throws Exception {
		ListResponse<ServiceOrder> listResponse = new ListResponse<ServiceOrder>();
		listResponse.setResponse(service.findServiceOrderList(custId,orderType));
		AjaxUtil.sendJSON(response, listResponse);
	}
	
	@RequestMapping(value="/findPhysicalOrder",method=RequestMethod.POST)
	public void findPhysicalOrder(HttpServletRequest request,HttpServletResponse response, String id) throws Exception {
		GenResponse<PhysicalOrder> genResponse = new GenResponse<PhysicalOrder>();
		genResponse.setResponse(service.findPhysicalOrder(id));
		AjaxUtil.sendJSON(response, genResponse);
	}
		
	@RequestMapping(value="/findDoctorOrder",method=RequestMethod.POST)
	public void findDoctorOrder(HttpServletRequest request,HttpServletResponse response, String id) throws Exception {
		GenResponse<DoctorOrder> genResponse = new GenResponse<DoctorOrder>();
		genResponse.setResponse(service.findDoctorOrder(id));
		AjaxUtil.sendJSON(response, genResponse);	
	}
	
	@RequestMapping(value="/findGeneOrder",method=RequestMethod.POST)
	public void findGeneOrder(HttpServletRequest request,HttpServletResponse response, String id) throws Exception {
		GenResponse<GeneOrder> genResponse = new GenResponse<GeneOrder>();
		genResponse.setResponse(service.findGeneOrder(id));
		AjaxUtil.sendJSON(response, genResponse);	
	}
	
	@RequestMapping(value="/addPhysicalOrder",method=RequestMethod.POST)
	public void addPhysicalOrder(HttpServletRequest request,HttpServletResponse response, PhysicalOrder physicalOrder,BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.physicalOrderValidator.validate(physicalOrder, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.insertPhysicalOrder(physicalOrder);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(physicalOrder.getId());
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/addDoctorOrderByService",method=RequestMethod.POST)
	public void addDoctorOrderByService(HttpServletRequest request,HttpServletResponse response,DoctorOrder doctorOrder,BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.doctorOrderValidator.validate(doctorOrder, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.insertDoctorOrderByService(doctorOrder,"DOCTOR_RESERVE");
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(doctorOrder.getId());
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/addAccompanyDoctorOrderByService",method=RequestMethod.POST)
	public void addAccompanyDoctorOrderByService(HttpServletRequest request,HttpServletResponse response,DoctorOrder doctorOrder,BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.doctorOrderValidator.validate(doctorOrder, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		doctorOrder.setIsNeedAssist("Y");
		String msg = service.insertDoctorOrderByService(doctorOrder,"DOCTOR_ACCOMPANY");
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(doctorOrder.getId());
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/addDoctorOrder",method=RequestMethod.POST)
	public void addDoctorOrder(HttpServletRequest request,HttpServletResponse response,DoctorOrder doctorOrder,BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.doctorOrderValidator.validate(doctorOrder, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		if(BasicUtil.isEmpty(doctorOrder.getCustId())){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("客户ID为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.insertDoctorOrder(doctorOrder);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(doctorOrder.getId());
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/addGeneOrder",method=RequestMethod.POST)
	public void addGeneOrder(HttpServletRequest request,HttpServletResponse response,GeneOrder geneOrder,BindingResult result) throws Exception {
		GenResponse<String> genResponse = new GenResponse<String>();
		this.geneOrderValidator.validate(geneOrder, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.insertGeneOrder(geneOrder);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		genResponse.setResponse(geneOrder.getId());
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/comfirmReceiveGeneBox",method=RequestMethod.POST)
	public void comfirmReceiveGeneBox(HttpServletRequest request,HttpServletResponse response,String orderId) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		if(BasicUtil.isEmpty(orderId)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("orderId为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		service.comfirmReceiveGeneBox(orderId);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/comfirmSendGeneBox",method=RequestMethod.POST)
	public void comfirmSendGeneBox(HttpServletRequest request,HttpServletResponse response,String orderId) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		if(BasicUtil.isEmpty(orderId)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("orderId为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		service.comfirmSendGeneBox(orderId);
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/createOrderPayment",method=RequestMethod.POST)
	public void createOrderPayment(HttpServletRequest request,HttpServletResponse response,OrderPayment orderPayment,BindingResult result) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		this.orderPaymentValidator.validate(orderPayment, result);
		if(result.hasErrors()){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(BasicUtil.extractErrors(result.getFieldErrors()));
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.createOrderPayment(orderPayment);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
		}
		AjaxUtil.sendJSON(response, genResponse);
	}
	
	@RequestMapping(value="/cancelServiceOrder",method=RequestMethod.POST)
	public void cancelServiceOrder(HttpServletRequest request,HttpServletResponse response,String orderId) throws Exception {
		BaseResponse genResponse = new BaseResponse();
		if(BasicUtil.isEmpty(orderId)){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg("订单ID为空");
			AjaxUtil.sendJSON(response, genResponse);
			return;
		}
		String msg = service.cancelServiceOrder(orderId);
		if(msg!=null){
			genResponse.setResultCode(ResultCodes.ERROR);
			genResponse.setMsg(msg);
		}
		AjaxUtil.sendJSON(response, genResponse);
	}
}
