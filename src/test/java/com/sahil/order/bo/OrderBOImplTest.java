package com.sahil.order.bo;

import java.sql.SQLException;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import com.intuit.order.bo.OrderBOImpl;
import com.intuit.order.bo.exception.BOException;
import com.intuit.order.dao.OrderDAO;
import com.intuit.order.dto.Order;

public class OrderBOImplTest {

	
	@Mock
	OrderDAO dao;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void placeOrderShouldCreateAnOrder() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.create(any(Order.class))).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);
	//	assertTrue(result);
		verify(dao).create(order);
		verify(dao,atLeast(1)).create(order);
		verify(dao,times(1)).create(order);
		
	}
	@Test
	public void placeOrderShouldNotCreateAnOrder() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);
	//	assertFalse(result);
		verify(dao).create(order);
	}
	@Test(expectedExceptions=BOException.class)
	public void placeOrderShouldThrowBOException() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		boolean result = bo.placeOrder(order);
		
	}
	@Test
	public void cancelOrderShouldCancelTheOrder() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(123);
	//	assertTrue(result);
		verify(dao).read(123);
		verify(dao).update(order);
	}
	@Test(enabled=false)
	public void cancelOrderShouldNotCancelTheOrder() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(123);
	//	assertFalse(result);
		verify(dao).read(123);
		verify(dao).update(order);
	}
	@Test(expectedExceptions=BOException.class)
	public void cancelOrderShouldThrowAnBOExceptionOnRead() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		
		when(dao.read(123)).thenThrow(SQLException.class);
		
		bo.cancelOrder(123);
		
	}
	@Test(expectedExceptions=BOException.class, enabled=false)
	public void cancelOrderShouldThrowAnBOExceptionOnUpdate() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(123);
		
	}
	@Test
	public void deleteOrderDeletesTheOrder() throws SQLException, BOException {
		OrderBOImpl bo= new OrderBOImpl();
		bo.setDao(dao);
	
		when(dao.delete(anyInt())).thenReturn(1);
		boolean result=bo.deleteOrder(anyInt());
	//	assertTrue(result);
		
	}
}
