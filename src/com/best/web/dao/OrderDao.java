package com.best.web.dao;

import java.util.HashMap;
import java.util.List;

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

public interface OrderDao {
	public String checkCardCodeExists(String cardCode);
	public String checkServiceCodeExists(String serviceCode);
	public int findAvailableCardNoTotal(OrderParam orderParam);
	public List<String> checkCardNoOrderExists(String orderFrom,String orderNo,String orderCode);
	public OrderParam findCardNoForSale(OrderParam orderParam);
	public int updateCardNoForSold(OrderParam cardNo);
	public void restoreCardNoForSold(List<String> cardNoIdList);
	public void insertRelOrder(OrderParam cardNo);
	public void restoreRelOrderForSold(OrderParam cardNo);
	public void insertCustServiceByOrder(OrderParam cardNo);
	
	public List<CardNo> findCardNoList(String orderDtlId);
	public List<String> checkCardOrderAvailable(String orderDtlId);
	public List<String> checkServiceOrderAvailable(String orderDtlId);
	
	public ServiceInfo findServiceInfo(String custServiceId);
	public List<SupplierInfo> findServiceSupplierList(String custServiceId);
	public List<SupplierOrgInfo> findSupplierOrgList(HashMap<String,String> paramMap);
	public List<ServiceOrderQuantity> findServiceOrderQuantityList(String custId);
	public boolean checkServiceOrderResubmit(ServiceOrder order);
	public boolean checkDoctorOrderResubmit(DoctorOrder order);
	
	public int findTodayCustServiceOrderQuantity(String custId);
	public List<ServiceOrder> findServiceOrderList(HashMap<String,String> paramMap);
	public ServiceOrder findServiceOrder(String id);
	public PhysicalOrder findPhysicalOrder(String id);
	public DoctorOrder findDoctorOrder(String id);
	public GeneOrder findGeneOrder(String id);
	public int addCustServiceUsedQuantity(ServiceInfo model);
	public int reduceServiceUsedQuantity(ServiceInfo model);
	public void insertServiceOrder(ServiceOrder model);
	public void updateServiceOrder(ServiceOrder model);
	public void createOrderPayment(OrderPayment model);
	public void updateOrderStatus(ServiceOrder model);
	public void insertPhysicalOrder(PhysicalOrder model);
	public void insertDoctorOrder(DoctorOrder model);
	public void insertGeneOrder(GeneOrder model);
	public void comfirmReceiveGeneBox(String orderId);
	public void comfirmSendGeneBox(String orderId);
}
