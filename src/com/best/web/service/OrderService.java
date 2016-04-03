package com.best.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.best.util.BasicUtil;
import com.best.web.dao.OrderDao;
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

@Service
public class OrderService {
	@Resource
	private OrderDao orderDao;
	
	public boolean checkCardCodeExists(String cardCode){
		if(BasicUtil.isEmpty(orderDao.checkCardCodeExists(cardCode))){
			return false;
		}
		return true;
	}
	
	public boolean checkServiceCodeExists(String cardCode){
		if(BasicUtil.isEmpty(orderDao.checkServiceCodeExists(cardCode))){
			return false;
		}
		return true;
	}
	
	public String sendCardOrder(OrderParam orderParam){
		String resultMsg = null;
		int quantity = Integer.parseInt(orderParam.getQuantity());
		if(BasicUtil.isNotEmpty(orderDao.checkCardNoOrderExists(orderParam.getOrderFrom(),orderParam.getOrderNo(),orderParam.getOrderCode()))){
			return "重复提交订单";
		}
		if(quantity > orderDao.findAvailableCardNoTotal(orderParam)){
			return "卡号库存不足";
		}
		orderParam.setRelOrderId(BasicUtil.generateId());
		orderParam.setOrderType("CARD");
		orderDao.insertRelOrder(orderParam);
		//递归售卡
		List<String> cardNoIdList = new ArrayList<String>();
		resultMsg = sendCardOrder(cardNoIdList, orderParam, quantity);
		if(BasicUtil.isNotEmpty(resultMsg)){//卡号库存不足,还原卡号状态
			orderDao.restoreRelOrderForSold(orderParam);
			if(BasicUtil.isNotEmpty(cardNoIdList)){
				orderDao.restoreCardNoForSold(cardNoIdList);
			}
		}
		return resultMsg;
	}
	
	public String sendCardOrder(List<String> cardNoIdList,OrderParam orderParam,int quantity){
		String resultMsg = null;
		for(int i=0;i<quantity;i++){
			OrderParam cardNoMoldel = orderDao.findCardNoForSale(orderParam);
			if(cardNoMoldel!=null && cardNoMoldel.getCardNoId()!=null){
				cardNoMoldel.setRelOrderId(orderParam.getRelOrderId());
				cardNoMoldel.setOrderFrom(orderParam.getOrderFrom());
				cardNoMoldel.setStatus("2");//1未使用,2已发出,3已使用,4已失效
				if(orderDao.updateCardNoForSold(cardNoMoldel)==1){//更新成功
					cardNoIdList.add(cardNoMoldel.getCardNoId());
				}
			}else{
				resultMsg = "卡号库存不足";
				break;
			}
		}
		if(resultMsg==null && cardNoIdList.size()<quantity){
			resultMsg = sendCardOrder(cardNoIdList, orderParam, quantity-cardNoIdList.size());
		}
		return resultMsg;
	}
	
	public String sendServiceOrder(OrderParam orderParam){
		String resultMsg = null;
		if(BasicUtil.isNotEmpty(orderDao.checkCardNoOrderExists(orderParam.getOrderFrom(),orderParam.getOrderNo(),orderParam.getOrderCode()))){
			return "重复提交订单";
		}
		orderParam.setRelOrderId(BasicUtil.generateId());
		orderParam.setOrderType("SERVICE");
		orderDao.insertRelOrder(orderParam);
		orderDao.insertCustServiceByOrder(orderParam);
		return resultMsg;
	}
	
	public List<CardNo> findCardNoList(String orderDtlId){
		return orderDao.findCardNoList(orderDtlId);
	}
	
	public boolean checkCardOrderAvailable(String orderDtlId){
		List<String> list = orderDao.checkCardOrderAvailable(orderDtlId);
		if(BasicUtil.isEmpty(list)){
			return false;
		}
		return true;
	}
	
	public String  checkServiceOrderAvailable(String orderDtlId){
		List<String> list = orderDao.checkServiceOrderAvailable(orderDtlId);
		if(BasicUtil.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	public ServiceInfo findServiceInfo(String custServiceId){
		return orderDao.findServiceInfo(custServiceId);
	}
	
	public List<SupplierInfo> findServiceSupplierList(String custServiceId){
		return orderDao.findServiceSupplierList(custServiceId);
	}
	
	public List<SupplierOrgInfo> findSupplierOrgList(String supplierId,String cityName,String orderType){
		HashMap<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("supplierId", supplierId);
		paramMap.put("cityName", cityName);
		paramMap.put("orderType", orderType);
		return orderDao.findSupplierOrgList(paramMap);
	}
	
	public List<ServiceOrderQuantity> findServiceOrderQuantityList(String custId){
		return orderDao.findServiceOrderQuantityList(custId);
	}
	
	public List<ServiceOrder> findServiceOrderList(String custId,String orderType){
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("custId", custId);
		param.put("orderType", orderType);
		return orderDao.findServiceOrderList(param);
	}
	
	public ServiceOrder findServiceOrder(String id){
		return orderDao.findServiceOrder(id);
	}
	
	public PhysicalOrder findPhysicalOrder(String id){
		return orderDao.findPhysicalOrder(id);
	}
	
	public DoctorOrder findDoctorOrder(String id){
		return orderDao.findDoctorOrder(id);
	}
	
	public GeneOrder findGeneOrder(String id){
		return orderDao.findGeneOrder(id);
	}
	
	private String validateService(ServiceInfo serviceInfo,ServiceOrder serviceOrder){
		//
		if(BasicUtil.isEmpty(serviceOrder.getCustServiceId())){
			return "客服服务ID为空";
		}
		//有效日期validFlag
		if(serviceInfo.getValidDateBegin()!=null && serviceInfo.getValidFlag().equals("N")){
			return "服务已过有效期";
		}
		//数量
		if(!(serviceInfo.getQuantity()>serviceInfo.getUsedQuantity())){
			return "服务已用完";
		}
		//性别
		if(!serviceInfo.getSexSelect().equals("ALL") && !serviceInfo.getSexSelect().equals(serviceOrder.getSex())){
			return "使用人性别与本服务性别要求不符";
		}
		return null;
	}
	
	public String insertPhysicalOrder(PhysicalOrder model){
		if(orderDao.checkServiceOrderResubmit(model)){
			return "请勿重复提交订单";
		}
		if(orderDao.findTodayCustServiceOrderQuantity(model.getCustId())>=100){
			return "订单提交过于频繁，今天不再受理订单";
		}
		//check custService valid
		ServiceInfo serviceInfo = this.findServiceInfo(model.getCustServiceId());
		if(serviceInfo==null || serviceInfo.getCustServiceId()==null){
			return "客户服务ID为空";
		}
		String msg = validateService(serviceInfo,model);
		if(msg!=null){
			return msg;
		}
		//update custService usedQuantity
		int updateQty = orderDao.addCustServiceUsedQuantity(serviceInfo);
		if(updateQty==0){
			return "服务已使用完";
		}
		//insert service&order order
		model.setCustId(serviceInfo.getCustId());
		model.setId(BasicUtil.generateId());
		model.setOrderId(model.getId());
		model.setOrderType(serviceInfo.getServiceType());
		model.setOrderStatus("1");
		model.setOrderPaymentStatus("1");
		model.setPaymentType(serviceInfo.getServiceFrom());
		model.setPaymentNo(serviceInfo.getPaymentType());
		orderDao.insertServiceOrder(model);
		orderDao.insertPhysicalOrder(model);
		return null;
	}
	
	public String insertDoctorOrderByService(DoctorOrder model,String doctorOrderType){
		if(orderDao.checkServiceOrderResubmit(model)){
			return "请勿重复提交订单";
		}
		if(orderDao.findTodayCustServiceOrderQuantity(model.getCustId())>=100){
			return "订单提交过于频繁，今天不再受理订单";
		}
		//check custService valid
		ServiceInfo serviceInfo = this.findServiceInfo(model.getCustServiceId());
		if(serviceInfo==null || serviceInfo.getCustServiceId()==null){
			return "客户服务ID为空";
		}
		String msg = validateService(serviceInfo,model);
		if(msg!=null){
			return msg;
		}
		//update custService usedQuantity
		int updateQty = orderDao.addCustServiceUsedQuantity(serviceInfo);
		if(updateQty==0){
			return "服务已使用完";
		}
		//insert service&doctor order
		model.setCustId(serviceInfo.getCustId());
		model.setId(BasicUtil.generateId());
		model.setOrderId(model.getId());
		model.setOrderType(doctorOrderType);
		model.setOrderStatus("1");
		model.setOrderPaymentStatus("1");
		model.setPaymentType(serviceInfo.getServiceFrom());
		model.setPaymentNo(serviceInfo.getPaymentType());
		orderDao.insertServiceOrder(model);
		orderDao.insertDoctorOrder(model);
		return null;
	}
	
	public String insertDoctorOrder(DoctorOrder model){
		if(orderDao.checkDoctorOrderResubmit(model)){
			return "请勿重复提交订单";
		}
		//检查是否恶意提交订单
		if(orderDao.findTodayCustServiceOrderQuantity(model.getCustId())>=100){
			return "订单提交过于频繁，今天不再受理订单";
		}
		//insert service&doctor order
		model.setId(BasicUtil.generateId());
		model.setOrderId(model.getId());
		model.setOrderType("DOCTOR_RESERVE");
		model.setOrderStatus("1");
		model.setOrderPaymentStatus("0");
		model.setPaymentType("ONLINE");
		orderDao.insertServiceOrder(model);
		orderDao.insertDoctorOrder(model);
		return null;
	}
	
	public String insertGeneOrder(GeneOrder model){
		if(orderDao.checkServiceOrderResubmit(model)){
			return "请勿重复提交订单";
		}
		if(orderDao.findTodayCustServiceOrderQuantity(model.getCustId())>=100){
			return "订单提交过于频繁，今天不再受理订单";
		}
		//check custService valid
		ServiceInfo serviceInfo = this.findServiceInfo(model.getCustServiceId());
		if(serviceInfo==null || serviceInfo.getCustServiceId()==null){
			return "客户服务ID为空";
		}
		String msg = validateService(serviceInfo,model);
		if(msg!=null){
			return msg;
		}
		//update custService usedQuantity
		int updateQty = orderDao.addCustServiceUsedQuantity(serviceInfo);
		if(updateQty==0){
			return "服务已使用完";
		}
		//insert service&gene order
		model.setCustId(serviceInfo.getCustId());
		model.setId(BasicUtil.generateId());
		model.setOrderId(model.getId());
		model.setOrderType("GENE");
		model.setOrderStatus("1");
		model.setOrderPaymentStatus("1");
		model.setPaymentType(serviceInfo.getServiceFrom());
		model.setPaymentNo(serviceInfo.getPaymentType());
		orderDao.insertServiceOrder(model);
		orderDao.insertGeneOrder(model);
		return null;
	}
	
	public void comfirmReceiveGeneBox(String orderId){
		orderDao.comfirmReceiveGeneBox(orderId);
	}
	
	public void comfirmSendGeneBox(String orderId){
		orderDao.comfirmSendGeneBox(orderId);
	}
	
	public void updateServiceOrder(ServiceOrder model){
		orderDao.updateServiceOrder(model);
	}
	
	public String createOrderPayment(OrderPayment model){
		ServiceOrder serviceOrder = this.findServiceOrder(model.getOrderId());
		if(serviceOrder==null || serviceOrder.getId()==null){
			return "订单ID不存在";
		}
		if(!serviceOrder.getOrderPaymentStatus().equals("0")){
			return "订单非未支付状态，不允许支付";
		}
		if(!serviceOrder.getOrderStatus().equals("2")){
			return "订单未确认，不允许支付";
		}
		if(!(new BigDecimal(model.getPaymentAmount())).equals(serviceOrder.getOrderAmount())){
			return "付款金额与订单金额不符";
		}
		orderDao.createOrderPayment(model);
		serviceOrder.setOrderStatus("2");
		serviceOrder.setOrderPaymentStatus("1");
		orderDao.updateOrderStatus(serviceOrder);
		return null;
	}
	
	public String cancelServiceOrder(String orderId){
		ServiceOrder serviceOrder = this.findServiceOrder(orderId);
		if(serviceOrder==null || serviceOrder.getId()==null){
			return "订单ID不存在";
		}
		//0暂存 1待确认 2已确认 3客户取消 4客服取消 5已预约 6已完成
		if("3,4,6".contains(serviceOrder.getOrderStatus())){
			return "当前订单状态不允许取消";
		}
		if("ONLINE,CASH".contains(serviceOrder.getPaymentType())){
			if(serviceOrder.getOrderPaymentStatus().equals("1")){
				return "订单已支付，请联络客服取消订单";
			}
		}
		
		//归还客户服务
		if("CARD,MALL,XZH_WEB_MALL,XZH_WX_MALL,XZH_APP_MALL".contains(serviceOrder.getPaymentType())){
			ServiceInfo serviceInfo = orderDao.findServiceInfo(serviceOrder.getCustServiceId());
			int updateQty = orderDao.reduceServiceUsedQuantity(serviceInfo);
			if(updateQty==0){
				return "订单不能重复取消";
			}
		}
		serviceOrder.setOrderStatus("3");
		orderDao.updateOrderStatus(serviceOrder);
		return null;
	}
	
}
