package com.intuit.order.bo;

import com.intuit.order.bo.exception.BOException;
import com.intuit.order.dto.Order;

public interface OrderBO {
		boolean placeOrder(Order order) throws BOException;
		boolean cancelOrder(int id)throws BOException;
		boolean deleteOrder(int id)throws BOException;
}
