package com.best.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

@Repository
public class OrderDaoImpl implements OrderDao{
	@Resource
	private SqlSession sqlSession;
	
	public String checkCardCodeExists(String cardCode){
		return sqlSession.selectOne("orderSql.checkCardCodeExists",cardCode);
	}
	
	public String checkServiceCodeExists(String serviceCode){
		return sqlSession.selectOne("orderSql.checkServiceCodeExists",serviceCode);
	}
	
	public int findAvailableCardNoTotal(OrderParam orderParam){
		return sqlSession.selectOne("orderSql.findAvailableCardNoTotal",orderParam);
	}
	
	public List<String> checkCardNoOrderExists(String orderFrom,String orderNo,String orderCode){
		OrderParam cardNo = new OrderParam();
		cardNo.setOrderFrom(orderFrom);
		cardNo.setOrderNo(orderNo);
		cardNo.setOrderCode(orderCode);
		return sqlSession.selectList("orderSql.checkCardNoOrderExists", cardNo);
	}
	
	public OrderParam findCardNoForSale(OrderParam orderParam){
		return sqlSession.selectOne("orderSql.findCardNoForSale", orderParam);
	}
	
	public int updateCardNoForSold(OrderParam cardNo){
		return sqlSession.update("orderSql.updateCardNoForSold", cardNo);
	}
	
	public void restoreCardNoForSold(List<String> cardNoIdList){
		sqlSession.update("orderSql.restoreCardNoForSold", cardNoIdList);
	}
	
	public void insertRelOrder(OrderParam cardNo){
		sqlSession.insert("orderSql.insertRelOrder", cardNo);
	}
	
	public void restoreRelOrderForSold(OrderParam cardNo){
		sqlSession.update("orderSql.restoreRelOrderForSold", cardNo);
	}
	
	public void insertCustServiceByOrder(OrderParam cardNo){
		sqlSession.insert("orderSql.insertCustServiceByOrder", cardNo);
	}
	
	public List<CardNo> findCardNoList(String orderDtlId){
		return sqlSession.selectList("orderSql.findCardNoList",orderDtlId);
	}
	
	public List<String> checkCardOrderAvailable(String orderDtlId){
		return sqlSession.selectList("orderSql.checkCardOrderAvailable",orderDtlId);
	}
	
	public List<String> checkServiceOrderAvailable(String orderDtlId){
		return sqlSession.selectList("orderSql.checkServiceOrderAvailable",orderDtlId);
	}
	
	public ServiceInfo findServiceInfo(String custServiceId){
		return sqlSession.selectOne("orderSql.findServiceInfo",custServiceId);
	}
	
	public List<SupplierInfo> findServiceSupplierList(String custServiceId){
		return sqlSession.selectList("orderSql.findServiceSupplierList",custServiceId);
	}
	public List<SupplierOrgInfo> findSupplierOrgList(HashMap<String,String> paramMap){
		return sqlSession.selectList("orderSql.findSupplierOrgList",paramMap);
	}
	public List<ServiceOrderQuantity> findServiceOrderQuantityList(String custId){
		return sqlSession.selectList("orderSql.findServiceOrderQuantityList",custId);
	}
	public boolean checkServiceOrderResubmit(ServiceOrder order){
		List<String> ids = sqlSession.selectList("orderSql.checkServiceOrderResubmit",order);
		if(ids!=null && ids.size()>0){
			return true;
		}
		return false;
	}
	public boolean checkDoctorOrderResubmit(DoctorOrder order){
		List<String> ids = sqlSession.selectList("orderSql.checkDoctorOrderResubmit",order);
		if(ids!=null && ids.size()>0){
			return true;
		}
		return false;
	}
	public int findTodayCustServiceOrderQuantity(String custId){
		return sqlSession.selectOne("orderSql.findTodayCustServiceOrderQuantity",custId);
	}
	public List<ServiceOrder> findServiceOrderList(HashMap<String,String> paramMap){
		return sqlSession.selectList("orderSql.findServiceOrderList",paramMap);
	}
	public ServiceOrder findServiceOrder(String id){
		return sqlSession.selectOne("orderSql.findServiceOrder",id);
	}
	public PhysicalOrder findPhysicalOrder(String id){
		return sqlSession.selectOne("orderSql.findPhysicalOrder",id);
	}
	public DoctorOrder findDoctorOrder(String id){
		return sqlSession.selectOne("orderSql.findDoctorOrder", id);
	}
	public GeneOrder findGeneOrder(String id){
		return sqlSession.selectOne("orderSql.findGeneOrder", id);
	}
	public int addCustServiceUsedQuantity(ServiceInfo model){
		return sqlSession.update("orderSql.addCustServiceUsedQuantity", model);
	}
	public int reduceServiceUsedQuantity(ServiceInfo model){
		return sqlSession.update("orderSql.reduceServiceUsedQuantity", model);
	}
	public void insertServiceOrder(ServiceOrder model){
		sqlSession.insert("orderSql.insertServiceOrder", model);
	}
	public void updateServiceOrder(ServiceOrder model){
		sqlSession.update("orderSql.updateServiceOrder", model);
	}
	public void createOrderPayment(OrderPayment model){
		sqlSession.insert("orderSql.createOrderPayment", model);
	}
	public void updateOrderStatus(ServiceOrder model){
		sqlSession.update("orderSql.updateOrderStatus", model);
	}
	public void insertPhysicalOrder(PhysicalOrder model){
		sqlSession.insert("orderSql.insertPhysicalOrder", model);
	}
	public void insertDoctorOrder(DoctorOrder model){
		sqlSession.insert("orderSql.insertDoctorOrder", model);
	}
	public void insertGeneOrder(GeneOrder model){
		sqlSession.insert("orderSql.insertGeneOrder", model);
	}
	public void comfirmReceiveGeneBox(String orderId){
	    sqlSession.update("orderSql.comfirmReceiveGeneBox", orderId);
	}
	public void comfirmSendGeneBox(String orderId){
		sqlSession.update("orderSql.comfirmSendGeneBox", orderId);
	}
}
